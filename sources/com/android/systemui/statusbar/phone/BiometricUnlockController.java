package com.android.systemui.statusbar.phone;

import android.content.res.Resources;
import android.hardware.biometrics.BiometricSourceType;
import android.metrics.LogMaker;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Trace;
import com.android.compose.animation.scene.SceneKey;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.logging.UiEventLoggerImpl;
import com.android.internal.util.LatencyTracker;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.BiometricUnlockLogger;
import com.android.systemui.Dumpable;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.domain.interactor.BiometricUnlockInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.BiometricUnlockSource;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionState;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowState;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BiometricUnlockController extends KeyguardUpdateMonitorCallback implements Dumpable {
    public static final UiEventLogger UI_EVENT_LOGGER = new UiEventLoggerImpl();
    public final AuthController mAuthController;
    public BiometricSourceType mBiometricType;
    public final BiometricUnlockInteractor mBiometricUnlockInteractor;
    public final int mConsecutiveFpFailureThreshold;
    public final DozeScrimController mDozeScrimController;
    public boolean mFadedAwayAfterWakeAndUnlock;
    public final Handler mHandler;
    public final KeyguardBypassController mKeyguardBypassController;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardTransitionInteractor mKeyguardTransitionInteractor;
    public StatusBarKeyguardViewManager mKeyguardViewController;
    public final KeyguardViewMediator mKeyguardViewMediator;
    public long mLastFpFailureUptimeMillis;
    public final LatencyTracker mLatencyTracker;
    public final BiometricUnlockLogger mLogger;
    public final MetricsLogger mMetricsLogger;
    public int mMode;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public int mNumConsecutiveFpFailures;
    public final boolean mOrderUnlockAndWake;
    public final PowerManager mPowerManager;
    public final ScreenOffAnimationController mScreenOffAnimationController;
    public final Lazy mSelectedUserInteractor;
    public final SessionTracker mSessionTracker;
    public final StatusBarStateController mStatusBarStateController;
    public final SystemClock mSystemClock;
    public final KeyguardUpdateMonitor mUpdateMonitor;
    public final VibratorHelper mVibratorHelper;
    public PowerManager.WakeLock mWakeLock;
    final WakefulnessLifecycle.Observer mWakefulnessObserver;
    public PendingAuthenticated mPendingAuthenticated = null;
    public final Set mBiometricUnlockEventsListeners = new HashSet();
    public final AnonymousClass1 mReleaseBiometricWakeLockRunnable = new AnonymousClass1(this, 0);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.BiometricUnlockController$1, reason: invalid class name */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ BiometricUnlockController this$0;

        public /* synthetic */ AnonymousClass1(BiometricUnlockController biometricUnlockController, int i) {
            this.$r8$classId = i;
            this.this$0 = biometricUnlockController;
        }

        @Override // java.lang.Runnable
        public final void run() {
            switch (this.$r8$classId) {
                case 0:
                    BiometricUnlockLogger biometricUnlockLogger = this.this$0.mLogger;
                    biometricUnlockLogger.getClass();
                    biometricUnlockLogger.logBuffer.log("BiometricUnlockLogger", LogLevel.INFO, "biometric wakelock: TIMEOUT!!", null);
                    this.this$0.releaseBiometricWakeLock();
                    break;
                default:
                    NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) this.this$0.mNotificationShadeWindowController;
                    NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                    if (notificationShadeWindowState.forceDozeBrightness) {
                        notificationShadeWindowState.forceDozeBrightness = false;
                        notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
                        break;
                    }
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.BiometricUnlockController$3, reason: invalid class name */
    public final class AnonymousClass3 implements WakefulnessLifecycle.Observer {
        public AnonymousClass3() {
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onFinishedGoingToSleep$1() {
            Trace.beginSection("BiometricUnlockController#onFinishedGoingToSleep");
            BiometricUnlockController biometricUnlockController = BiometricUnlockController.this;
            if (biometricUnlockController.mPendingAuthenticated != null) {
                BiometricUnlockLogger biometricUnlockLogger = biometricUnlockController.mLogger;
                biometricUnlockLogger.getClass();
                biometricUnlockLogger.logBuffer.log("BiometricUnlockLogger", LogLevel.DEBUG, "onFinishedGoingToSleep with pendingAuthenticated != null", null);
                final PendingAuthenticated pendingAuthenticated = biometricUnlockController.mPendingAuthenticated;
                biometricUnlockController.mHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.phone.BiometricUnlockController$3$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        BiometricUnlockController.AnonymousClass3 anonymousClass3 = BiometricUnlockController.AnonymousClass3.this;
                        BiometricUnlockController.PendingAuthenticated pendingAuthenticated2 = pendingAuthenticated;
                        anonymousClass3.getClass();
                        BiometricUnlockController.this.onBiometricAuthenticated(pendingAuthenticated2.userId, pendingAuthenticated2.biometricSourceType, pendingAuthenticated2.isStrongBiometric);
                    }
                });
                biometricUnlockController.mPendingAuthenticated = null;
            }
            Trace.endSection();
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onStartedGoingToSleep() {
            UiEventLogger uiEventLogger = BiometricUnlockController.UI_EVENT_LOGGER;
            BiometricUnlockController biometricUnlockController = BiometricUnlockController.this;
            biometricUnlockController.resetMode();
            biometricUnlockController.mFadedAwayAfterWakeAndUnlock = false;
            biometricUnlockController.mPendingAuthenticated = null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.BiometricUnlockController$4, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass4 {
        public static final /* synthetic */ int[] $SwitchMap$android$hardware$biometrics$BiometricSourceType;

        static {
            int[] iArr = new int[BiometricSourceType.values().length];
            $SwitchMap$android$hardware$biometrics$BiometricSourceType = iArr;
            try {
                iArr[BiometricSourceType.FINGERPRINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$hardware$biometrics$BiometricSourceType[BiometricSourceType.FACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$hardware$biometrics$BiometricSourceType[BiometricSourceType.IRIS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r11v0 com.android.systemui.statusbar.phone.BiometricUnlockController$BiometricUiEvent, still in use, count: 1, list:
      (r11v0 com.android.systemui.statusbar.phone.BiometricUnlockController$BiometricUiEvent) from 0x00b2: INVOKE 
      (r9v2 android.hardware.biometrics.BiometricSourceType)
      (r11v0 com.android.systemui.statusbar.phone.BiometricUnlockController$BiometricUiEvent)
      (r10v2 android.hardware.biometrics.BiometricSourceType)
      (r14v0 com.android.systemui.statusbar.phone.BiometricUnlockController$BiometricUiEvent)
      (r16v1 android.hardware.biometrics.BiometricSourceType)
      (r9v0 com.android.systemui.statusbar.phone.BiometricUnlockController$BiometricUiEvent)
     STATIC call: java.util.Map.of(java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object, java.lang.Object):java.util.Map A[MD:<K, V>:(K, V, K, V, K, V):java.util.Map<K, V> (c), WRAPPED] (LINE:179)
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:252)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:180)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BiometricUiEvent implements UiEventLogger.UiEventEnum {
        /* JADX INFO: Fake field, exist only in values array */
        BIOMETRIC_FINGERPRINT_SUCCESS(396),
        /* JADX INFO: Fake field, exist only in values array */
        BIOMETRIC_FINGERPRINT_FAILURE(397),
        /* JADX INFO: Fake field, exist only in values array */
        BIOMETRIC_FINGERPRINT_ERROR(398),
        /* JADX INFO: Fake field, exist only in values array */
        BIOMETRIC_FACE_SUCCESS(399),
        /* JADX INFO: Fake field, exist only in values array */
        BIOMETRIC_FACE_FAILURE(400),
        /* JADX INFO: Fake field, exist only in values array */
        BIOMETRIC_FACE_ERROR(401),
        /* JADX INFO: Fake field, exist only in values array */
        BIOMETRIC_IRIS_SUCCESS(402),
        /* JADX INFO: Fake field, exist only in values array */
        BIOMETRIC_IRIS_FAILURE(403),
        /* JADX INFO: Fake field, exist only in values array */
        BIOMETRIC_IRIS_ERROR(404),
        BIOMETRIC_BOUNCER_SHOWN(916),
        STARTED_WAKING_UP(1378);

        public static final Map ERROR_EVENT_BY_SOURCE_TYPE;
        public static final Map FAILURE_EVENT_BY_SOURCE_TYPE;
        public static final Map SUCCESS_EVENT_BY_SOURCE_TYPE;
        private final int mId;

        static {
            BiometricSourceType biometricSourceType = BiometricSourceType.FINGERPRINT;
            BiometricSourceType biometricSourceType2 = BiometricSourceType.FACE;
            BiometricSourceType biometricSourceType3 = BiometricSourceType.IRIS;
            ERROR_EVENT_BY_SOURCE_TYPE = Map.of(biometricSourceType, r13, biometricSourceType2, r10, biometricSourceType3, r7);
            SUCCESS_EVENT_BY_SOURCE_TYPE = Map.of(biometricSourceType, r11, biometricSourceType2, r14, biometricSourceType3, r9);
            FAILURE_EVENT_BY_SOURCE_TYPE = Map.of(biometricSourceType, r12, biometricSourceType2, r15, biometricSourceType3, r8);
        }

        public BiometricUiEvent(int i) {
            this.mId = i;
        }

        public static BiometricUiEvent valueOf(String str) {
            return (BiometricUiEvent) Enum.valueOf(BiometricUiEvent.class, str);
        }

        public static BiometricUiEvent[] values() {
            return (BiometricUiEvent[]) $VALUES.clone();
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PendingAuthenticated {
        public final BiometricSourceType biometricSourceType;
        public final boolean isStrongBiometric;
        public final int userId;

        public PendingAuthenticated(int i, BiometricSourceType biometricSourceType, boolean z) {
            this.userId = i;
            this.biometricSourceType = biometricSourceType;
            this.isStrongBiometric = z;
        }
    }

    public BiometricUnlockController(DozeScrimController dozeScrimController, KeyguardViewMediator keyguardViewMediator, NotificationShadeWindowController notificationShadeWindowController, KeyguardStateController keyguardStateController, Handler handler, KeyguardUpdateMonitor keyguardUpdateMonitor, Resources resources, KeyguardBypassController keyguardBypassController, MetricsLogger metricsLogger, DumpManager dumpManager, PowerManager powerManager, BiometricUnlockLogger biometricUnlockLogger, WakefulnessLifecycle wakefulnessLifecycle, AuthController authController, StatusBarStateController statusBarStateController, SessionTracker sessionTracker, LatencyTracker latencyTracker, ScreenOffAnimationController screenOffAnimationController, VibratorHelper vibratorHelper, SystemClock systemClock, Lazy lazy, BiometricUnlockInteractor biometricUnlockInteractor, JavaAdapter javaAdapter, KeyguardTransitionInteractor keyguardTransitionInteractor) {
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        this.mWakefulnessObserver = anonymousClass3;
        this.mPowerManager = powerManager;
        this.mUpdateMonitor = keyguardUpdateMonitor;
        keyguardUpdateMonitor.registerCallback(this);
        this.mLatencyTracker = latencyTracker;
        wakefulnessLifecycle.mObservers.add(anonymousClass3);
        this.mBiometricUnlockInteractor = biometricUnlockInteractor;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mDozeScrimController = dozeScrimController;
        this.mKeyguardViewMediator = keyguardViewMediator;
        this.mKeyguardStateController = keyguardStateController;
        this.mHandler = handler;
        this.mConsecutiveFpFailureThreshold = resources.getInteger(R.integer.fp_consecutive_failure_time_ms);
        this.mKeyguardBypassController = keyguardBypassController;
        keyguardBypassController.unlockController = this;
        this.mMetricsLogger = metricsLogger;
        this.mAuthController = authController;
        this.mStatusBarStateController = statusBarStateController;
        this.mSessionTracker = sessionTracker;
        this.mScreenOffAnimationController = screenOffAnimationController;
        this.mVibratorHelper = vibratorHelper;
        this.mLogger = biometricUnlockLogger;
        this.mSystemClock = systemClock;
        this.mOrderUnlockAndWake = resources.getBoolean(android.R.bool.config_powerDecoupleInteractiveModeFromDisplay);
        this.mSelectedUserInteractor = lazy;
        this.mKeyguardTransitionInteractor = keyguardTransitionInteractor;
        SceneKey sceneKey = Scenes.Bouncer;
        Edge.Companion companion = Edge.Companion;
        Edge.StateToState stateToState = new Edge.StateToState(KeyguardState.GONE, null);
        keyguardTransitionInteractor.getClass();
        javaAdapter.alwaysCollectFlow(keyguardTransitionInteractor.transition(stateToState), new BiometricUnlockController$$ExternalSyntheticLambda0(this, 2));
        dumpManager.registerDumpable(this);
    }

    public static int toSubtype(BiometricSourceType biometricSourceType) {
        int i = AnonymousClass4.$SwitchMap$android$hardware$biometrics$BiometricSourceType[biometricSourceType.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i != 2) {
            return i != 3 ? 3 : 2;
        }
        return 1;
    }

    public void consumeFromGoneTransitions(TransitionStep transitionStep) {
        if (transitionStep.transitionState == TransitionState.STARTED) {
            this.mBiometricUnlockInteractor.setBiometricUnlockState(0, null);
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(" BiometricUnlockController:");
        printWriter.print("   mMode=");
        printWriter.println(this.mMode);
        printWriter.print("   mWakeLock=");
        printWriter.println(this.mWakeLock);
        if (this.mUpdateMonitor.mAuthController.isUdfpsSupported()) {
            printWriter.print("   mNumConsecutiveFpFailures=");
            printWriter.println(this.mNumConsecutiveFpFailures);
            printWriter.print("   time since last failure=");
            ((SystemClockImpl) this.mSystemClock).getClass();
            printWriter.println(android.os.SystemClock.uptimeMillis() - this.mLastFpFailureUptimeMillis);
        }
    }

    public final boolean hasPendingAuthentication() {
        PendingAuthenticated pendingAuthenticated = this.mPendingAuthenticated;
        return pendingAuthenticated != null && this.mUpdateMonitor.isUnlockingWithBiometricAllowed(pendingAuthenticated.isStrongBiometric) && this.mPendingAuthenticated.userId == ((SelectedUserInteractor) this.mSelectedUserInteractor.get()).getSelectedUserId();
    }

    public final boolean isWakeAndUnlock() {
        int i = this.mMode;
        return i == 1 || i == 2 || i == 6;
    }

    @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
    public final void onBiometricAcquired(BiometricSourceType biometricSourceType, int i) {
        if (BiometricSourceType.FINGERPRINT != biometricSourceType || i == 0) {
            BiometricSourceType biometricSourceType2 = BiometricSourceType.FACE;
            if (biometricSourceType2 != biometricSourceType || i == 0) {
                Trace.beginSection("BiometricUnlockController#onBiometricAcquired");
                releaseBiometricWakeLock();
                if (this.mStatusBarStateController.isDozing()) {
                    if (this.mLatencyTracker.isEnabled()) {
                        this.mLatencyTracker.onActionStart(biometricSourceType == biometricSourceType2 ? 7 : 2);
                    }
                    this.mWakeLock = this.mPowerManager.newWakeLock(1, "wake-and-unlock:wakelock");
                    Trace.beginSection("acquire wake-and-unlock");
                    this.mWakeLock.acquire();
                    Trace.endSection();
                    BiometricUnlockLogger biometricUnlockLogger = this.mLogger;
                    biometricUnlockLogger.getClass();
                    biometricUnlockLogger.logBuffer.log("BiometricUnlockLogger", LogLevel.INFO, "biometric acquired, grabbing biometric wakelock", null);
                    this.mHandler.postDelayed(this.mReleaseBiometricWakeLockRunnable, 15000L);
                }
                Trace.endSection();
            }
        }
    }

    @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
    public final void onBiometricAuthFailed(BiometricSourceType biometricSourceType) {
        this.mMetricsLogger.write(new LogMaker(1697).setType(11).setSubtype(toSubtype(biometricSourceType)));
        Optional.ofNullable((BiometricUiEvent) BiometricUiEvent.FAILURE_EVENT_BY_SOURCE_TYPE.get(biometricSourceType)).ifPresent(new BiometricUnlockController$$ExternalSyntheticLambda0(this, 3));
        if (this.mLatencyTracker.isEnabled()) {
            this.mLatencyTracker.onActionCancel(biometricSourceType == BiometricSourceType.FACE ? 7 : 2);
        }
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mUpdateMonitor;
        boolean z = keyguardUpdateMonitor.mDeviceInteractive;
        boolean hasVibrator = this.mVibratorHelper.hasVibrator();
        BiometricUnlockLogger biometricUnlockLogger = this.mLogger;
        if (!hasVibrator && !z) {
            biometricUnlockLogger.getClass();
            biometricUnlockLogger.logBuffer.log("BiometricUnlockLogger", LogLevel.DEBUG, "wakeup device on authentication failure (device doesn't have a vibrator)", null);
            BiometricUnlockSource.Companion.getClass();
            startWakeAndUnlock(4, BiometricUnlockSource.Companion.fromBiometricSourceType(biometricSourceType));
        } else if (biometricSourceType == BiometricSourceType.FINGERPRINT && keyguardUpdateMonitor.mAuthController.isUdfpsSupported()) {
            ((SystemClockImpl) this.mSystemClock).getClass();
            long uptimeMillis = android.os.SystemClock.uptimeMillis();
            if (uptimeMillis - this.mLastFpFailureUptimeMillis < this.mConsecutiveFpFailureThreshold) {
                this.mNumConsecutiveFpFailures++;
            } else {
                this.mNumConsecutiveFpFailures = 1;
            }
            this.mLastFpFailureUptimeMillis = uptimeMillis;
            int i = this.mNumConsecutiveFpFailures;
            if (i >= 3) {
                biometricUnlockLogger.logUdfpsAttemptThresholdMet(i);
                BiometricUnlockSource.Companion.getClass();
                startWakeAndUnlock(3, BiometricUnlockSource.Companion.fromBiometricSourceType(biometricSourceType));
                UI_EVENT_LOGGER.log(BiometricUiEvent.BIOMETRIC_BOUNCER_SHOWN, this.mSessionTracker.getSessionId(1));
                this.mNumConsecutiveFpFailures = 0;
            }
        }
        releaseBiometricWakeLock();
    }

    @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
    public final void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType, boolean z) {
        Trace.beginSection("BiometricUnlockController#onBiometricUnlocked");
        boolean z2 = this.mUpdateMonitor.mGoingToSleep;
        BiometricUnlockLogger biometricUnlockLogger = this.mLogger;
        if (z2) {
            biometricUnlockLogger.deferringAuthenticationDueToSleep(i, biometricSourceType, this.mPendingAuthenticated != null);
            this.mPendingAuthenticated = new PendingAuthenticated(i, biometricSourceType, z);
            Trace.endSection();
            return;
        }
        this.mBiometricType = biometricSourceType;
        this.mMetricsLogger.write(new LogMaker(1697).setType(10).setSubtype(toSubtype(biometricSourceType)));
        Optional.ofNullable((BiometricUiEvent) BiometricUiEvent.SUCCESS_EVENT_BY_SOURCE_TYPE.get(biometricSourceType)).ifPresent(new BiometricUnlockController$$ExternalSyntheticLambda0(this, 0));
        if (((KeyguardStateControllerImpl) this.mKeyguardStateController).mOccluded || this.mKeyguardBypassController.onBiometricAuthenticated(biometricSourceType, z)) {
            this.mKeyguardViewMediator.userActivity();
            startWakeAndUnlock(biometricSourceType, z);
        } else {
            biometricUnlockLogger.getClass();
            biometricUnlockLogger.logBuffer.log("BiometricUnlockLogger", LogLevel.DEBUG, "onBiometricUnlocked aborted by bypass controller", null);
        }
        Trace.endSection();
    }

    @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
    public final void onBiometricDetected(BiometricSourceType biometricSourceType) {
        Trace.beginSection("BiometricUnlockController#onBiometricDetected");
        if (!this.mUpdateMonitor.mGoingToSleep) {
            BiometricUnlockSource.Companion.getClass();
            startWakeAndUnlock(3, BiometricUnlockSource.Companion.fromBiometricSourceType(biometricSourceType));
        }
        Trace.endSection();
    }

    @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
    public final void onBiometricError(int i, String str, BiometricSourceType biometricSourceType) {
        this.mMetricsLogger.write(new LogMaker(1697).setType(15).setSubtype(toSubtype(biometricSourceType)).addTaggedData(1741, Integer.valueOf(i)));
        Optional.ofNullable((BiometricUiEvent) BiometricUiEvent.ERROR_EVENT_BY_SOURCE_TYPE.get(biometricSourceType)).ifPresent(new BiometricUnlockController$$ExternalSyntheticLambda0(this, 1));
        if (biometricSourceType == BiometricSourceType.FINGERPRINT && (i == 7 || i == 9)) {
            BiometricUnlockLogger biometricUnlockLogger = this.mLogger;
            biometricUnlockLogger.getClass();
            biometricUnlockLogger.logBuffer.log("BiometricUnlockLogger", LogLevel.DEBUG, "fingerprint locked out", null);
            BiometricUnlockSource.Companion.getClass();
            startWakeAndUnlock(3, BiometricUnlockSource.Companion.fromBiometricSourceType(biometricSourceType));
            UI_EVENT_LOGGER.log(BiometricUiEvent.BIOMETRIC_BOUNCER_SHOWN, this.mSessionTracker.getSessionId(1));
        }
        releaseBiometricWakeLock();
    }

    @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
    public final void onKeyguardBouncerStateChanged(boolean z) {
        if (z) {
            return;
        }
        resetMode();
    }

    public final void releaseBiometricWakeLock() {
        if (this.mWakeLock != null) {
            Trace.beginSection("release wake-and-unlock");
            this.mHandler.removeCallbacks(this.mReleaseBiometricWakeLockRunnable);
            BiometricUnlockLogger biometricUnlockLogger = this.mLogger;
            biometricUnlockLogger.getClass();
            biometricUnlockLogger.logBuffer.log("BiometricUnlockLogger", LogLevel.INFO, "releasing biometric wakelock", null);
            this.mWakeLock.release();
            this.mWakeLock = null;
            Trace.endSection();
        }
    }

    public final void resetMode() {
        this.mMode = 0;
        this.mBiometricType = null;
        NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController;
        NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
        if (notificationShadeWindowState.forceDozeBrightness) {
            notificationShadeWindowState.forceDozeBrightness = false;
            notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
        }
        Iterator it = ((HashSet) this.mBiometricUnlockEventsListeners).iterator();
        while (it.hasNext()) {
            ((BiometricUnlockEventsListener) it.next()).onResetMode();
        }
        this.mNumConsecutiveFpFailures = 0;
        this.mLastFpFailureUptimeMillis = 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00f9 A[LOOP:0: B:33:0x00f3->B:35:0x00f9, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void startWakeAndUnlock(int r12, com.android.systemui.keyguard.shared.model.BiometricUnlockSource r13) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.BiometricUnlockController.startWakeAndUnlock(int, com.android.systemui.keyguard.shared.model.BiometricUnlockSource):void");
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface BiometricUnlockEventsListener {
        default void onBiometricUnlockedWithKeyguardDismissal() {
        }

        default void onModeChanged(int i) {
        }

        default void onResetMode() {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0076, code lost:
    
        if (r8.isUnlocked() != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x008c, code lost:
    
        if (r13.mSecure != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00b4, code lost:
    
        if (r21.mKeyguardViewController.isBouncerShowing() == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x011e, code lost:
    
        if (r19 != false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0124, code lost:
    
        if (r19 != false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x013d, code lost:
    
        if (r19 != false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0167, code lost:
    
        if (r19 != false) goto L27;
     */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void startWakeAndUnlock(android.hardware.biometrics.BiometricSourceType r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 412
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.BiometricUnlockController.startWakeAndUnlock(android.hardware.biometrics.BiometricSourceType, boolean):void");
    }
}

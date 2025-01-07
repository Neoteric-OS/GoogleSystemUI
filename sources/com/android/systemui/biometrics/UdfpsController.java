package com.android.systemui.biometrics;

import android.R;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.hardware.biometrics.BiometricFingerprintConstants;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.display.DisplayManager;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import android.hardware.fingerprint.IUdfpsOverlayController;
import android.hardware.fingerprint.IUdfpsOverlayControllerCallback;
import android.hardware.fingerprint.IUdfpsRefreshRateRequestCallback;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.Trace;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.util.LatencyTracker;
import com.android.internal.util.Preconditions;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.biometrics.BiometricDisplayListener;
import com.android.systemui.biometrics.UdfpsController;
import com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor;
import com.android.systemui.biometrics.shared.model.UdfpsOverlayParams;
import com.android.systemui.biometrics.udfps.SinglePointerTouchProcessor;
import com.android.systemui.biometrics.ui.binder.UdfpsTouchOverlayBinder;
import com.android.systemui.biometrics.ui.view.UdfpsTouchOverlay;
import com.android.systemui.biometrics.ui.viewmodel.UdfpsTouchOverlayViewModel;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.doze.DozeReceiver;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.LockscreenShadeTransitionController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.SystemUIDialogManager;
import com.android.systemui.statusbar.phone.UnlockedScreenOffAnimationController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutionImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class UdfpsController implements DozeReceiver, Dumpable {
    public final AccessibilityManager mAccessibilityManager;
    public final ActivityTransitionAnimator mActivityTransitionAnimator;
    public final AlternateBouncerInteractor mAlternateBouncerInteractor;
    public UdfpsController$$ExternalSyntheticLambda2 mAodInterruptRunnable;
    public boolean mAttemptedToDismissKeyguard;
    public AuthController$$ExternalSyntheticLambda2 mAuthControllerUpdateUdfpsLocation;
    public final AnonymousClass2 mBroadcastReceiver;
    public ExecutorImpl.ExecutionToken mCancelAodFingerUpAction;
    public final ConfigurationController mConfigurationController;
    public final Context mContext;
    public final Lazy mDefaultUdfpsTouchOverlayViewModel;
    public final DeviceEntryFaceAuthInteractor mDeviceEntryFaceAuthInteractor;
    public final Lazy mDeviceEntryUdfpsTouchOverlayViewModel;
    public final SystemUIDialogManager mDialogManager;
    public final DumpManager mDumpManager;
    public final ExecutionImpl mExecution;
    public final FalsingManager mFalsingManager;
    public final DelayableExecutor mFgExecutor;
    public final FingerprintManager mFingerprintManager;
    public final boolean mIgnoreRefreshRate;
    public final LayoutInflater mInflater;
    public final InputManager mInputManager;
    public boolean mIsAodInterruptActive;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardTransitionInteractor mKeyguardTransitionInteractor;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final StatusBarKeyguardViewManager mKeyguardViewManager;
    public long mLastTouchInteractionTime;
    public final LatencyTracker mLatencyTracker;
    public final LockscreenShadeTransitionController mLockscreenShadeTransitionController;
    public boolean mOnFingerDown;
    final BiometricDisplayListener mOrientationListener;
    public UdfpsControllerOverlay mOverlay;
    public final PowerInteractor mPowerInteractor;
    public final PowerManager mPowerManager;
    public final PrimaryBouncerInteractor mPrimaryBouncerInteractor;
    public final CoroutineScope mScope;
    public final AnonymousClass1 mScreenObserver;
    public boolean mScreenOn;
    public final SelectedUserInteractor mSelectedUserInteractor;
    FingerprintSensorPropertiesInternal mSensorProps;
    public final SessionTracker mSessionTracker;
    public final ShadeInteractor mShadeInteractor;
    public final StatusBarStateController mStatusBarStateController;
    public final SystemClock mSystemClock;
    public final SinglePointerTouchProcessor mTouchProcessor;
    public UdfpsDisplayMode mUdfpsDisplayMode;
    public final UdfpsKeyguardAccessibilityDelegate mUdfpsKeyguardAccessibilityDelegate;
    public final UdfpsOverlayInteractor mUdfpsOverlayInteractor;
    public final UnlockedScreenOffAnimationController mUnlockedScreenOffAnimationController;
    public final VibratorHelper mVibrator;
    public final ViewCaptureAwareWindowManager mWindowManager;
    public static final VibrationAttributes UDFPS_VIBRATION_ATTRIBUTES = new VibrationAttributes.Builder().setUsage(65).build();
    public static final VibrationAttributes LOCK_ICON_VIBRATION_ATTRIBUTES = new VibrationAttributes.Builder().setUsage(18).build();
    UdfpsOverlayParams mOverlayParams = new UdfpsOverlayParams();
    public int mActivePointerId = -1;
    public boolean mPointerPilfered = false;
    public final Set mCallbacks = new HashSet();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        void onFingerDown();

        void onFingerUp();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x047b  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0491  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0493 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0457  */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r3v15, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v25, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r4v32 */
    /* JADX WARN: Type inference failed for: r4v33 */
    /* JADX WARN: Type inference failed for: r4v34 */
    /* JADX WARN: Type inference failed for: r4v35, types: [int] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v41 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v16, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v15, types: [com.android.systemui.log.SessionTracker] */
    /* renamed from: -$$Nest$monTouch, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean m784$$Nest$monTouch(com.android.systemui.biometrics.UdfpsController r26, long r27, android.view.MotionEvent r29, boolean r30) {
        /*
            Method dump skipped, instructions count: 1200
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.UdfpsController.m784$$Nest$monTouch(com.android.systemui.biometrics.UdfpsController, long, android.view.MotionEvent, boolean):boolean");
    }

    static {
        VibrationEffect.get(0);
    }

    public UdfpsController(Context context, ExecutionImpl executionImpl, LayoutInflater layoutInflater, FingerprintManager fingerprintManager, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, StatusBarStateController statusBarStateController, DelayableExecutor delayableExecutor, StatusBarKeyguardViewManager statusBarKeyguardViewManager, DumpManager dumpManager, KeyguardUpdateMonitor keyguardUpdateMonitor, FalsingManager falsingManager, PowerManager powerManager, AccessibilityManager accessibilityManager, LockscreenShadeTransitionController lockscreenShadeTransitionController, ScreenLifecycle screenLifecycle, VibratorHelper vibratorHelper, UdfpsHapticsSimulator udfpsHapticsSimulator, UdfpsShell udfpsShell, KeyguardStateController keyguardStateController, DisplayManager displayManager, Handler handler, ConfigurationController configurationController, SystemClock systemClock, UnlockedScreenOffAnimationController unlockedScreenOffAnimationController, SystemUIDialogManager systemUIDialogManager, LatencyTracker latencyTracker, ActivityTransitionAnimator activityTransitionAnimator, PrimaryBouncerInteractor primaryBouncerInteractor, ShadeInteractor shadeInteractor, SinglePointerTouchProcessor singlePointerTouchProcessor, SessionTracker sessionTracker, AlternateBouncerInteractor alternateBouncerInteractor, InputManager inputManager, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, UdfpsKeyguardAccessibilityDelegate udfpsKeyguardAccessibilityDelegate, SelectedUserInteractor selectedUserInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor, Lazy lazy, Lazy lazy2, UdfpsOverlayInteractor udfpsOverlayInteractor, PowerInteractor powerInteractor, CoroutineScope coroutineScope) {
        ScreenLifecycle.Observer observer = new ScreenLifecycle.Observer() { // from class: com.android.systemui.biometrics.UdfpsController.1
            @Override // com.android.systemui.keyguard.ScreenLifecycle.Observer
            public final void onScreenTurnedOff() {
                UdfpsController.this.mScreenOn = false;
            }

            @Override // com.android.systemui.keyguard.ScreenLifecycle.Observer
            public final void onScreenTurnedOn() {
                UdfpsController udfpsController = UdfpsController.this;
                udfpsController.mScreenOn = true;
                UdfpsController$$ExternalSyntheticLambda2 udfpsController$$ExternalSyntheticLambda2 = udfpsController.mAodInterruptRunnable;
                if (udfpsController$$ExternalSyntheticLambda2 != null) {
                    udfpsController$$ExternalSyntheticLambda2.run();
                    udfpsController.mAodInterruptRunnable = null;
                }
            }
        };
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.biometrics.UdfpsController.2
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                UdfpsControllerOverlay udfpsControllerOverlay = UdfpsController.this.mOverlay;
                if (udfpsControllerOverlay == null || udfpsControllerOverlay.requestReason == 4 || !"android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction())) {
                    return;
                }
                String stringExtra = intent.getStringExtra("reason");
                if (stringExtra == null) {
                    stringExtra = "unknown";
                }
                LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(ActivityResultRegistry$$ExternalSyntheticOutline0.m("ACTION_CLOSE_SYSTEM_DIALOGS received, reason: ", stringExtra, ", mRequestReason: "), UdfpsController.this.mOverlay.requestReason, "UdfpsController");
                UdfpsControllerOverlay udfpsControllerOverlay2 = UdfpsController.this.mOverlay;
                udfpsControllerOverlay2.getClass();
                try {
                    udfpsControllerOverlay2.controllerCallback.onUserCanceled();
                } catch (RemoteException e) {
                    Log.e("UdfpsControllerOverlay", "Remote exception", e);
                }
                UdfpsController.this.hideUdfpsOverlay();
            }
        };
        this.mContext = context;
        this.mExecution = executionImpl;
        this.mVibrator = vibratorHelper;
        this.mInflater = layoutInflater;
        this.mIgnoreRefreshRate = context.getResources().getBoolean(R.bool.config_isPreApprovalRequestAvailable);
        FingerprintManager fingerprintManager2 = (FingerprintManager) Preconditions.checkNotNull(fingerprintManager);
        this.mFingerprintManager = fingerprintManager2;
        this.mWindowManager = viewCaptureAwareWindowManager;
        this.mFgExecutor = delayableExecutor;
        this.mStatusBarStateController = statusBarStateController;
        this.mKeyguardStateController = keyguardStateController;
        this.mKeyguardViewManager = statusBarKeyguardViewManager;
        this.mDumpManager = dumpManager;
        this.mDialogManager = systemUIDialogManager;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mFalsingManager = falsingManager;
        this.mPowerManager = powerManager;
        this.mAccessibilityManager = accessibilityManager;
        this.mLockscreenShadeTransitionController = lockscreenShadeTransitionController;
        screenLifecycle.mObservers.add(observer);
        this.mScreenOn = screenLifecycle.mScreenState == 2;
        this.mConfigurationController = configurationController;
        this.mSystemClock = systemClock;
        this.mUnlockedScreenOffAnimationController = unlockedScreenOffAnimationController;
        this.mLatencyTracker = latencyTracker;
        this.mActivityTransitionAnimator = activityTransitionAnimator;
        this.mSensorProps = new FingerprintSensorPropertiesInternal(-1, 0, 0, new ArrayList(), 0, false);
        this.mPrimaryBouncerInteractor = primaryBouncerInteractor;
        this.mShadeInteractor = shadeInteractor;
        this.mAlternateBouncerInteractor = alternateBouncerInteractor;
        this.mUdfpsOverlayInteractor = udfpsOverlayInteractor;
        this.mPowerInteractor = powerInteractor;
        this.mScope = coroutineScope;
        this.mInputManager = inputManager;
        this.mUdfpsKeyguardAccessibilityDelegate = udfpsKeyguardAccessibilityDelegate;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mKeyguardTransitionInteractor = keyguardTransitionInteractor;
        this.mTouchProcessor = singlePointerTouchProcessor;
        this.mSessionTracker = sessionTracker;
        this.mDeviceEntryUdfpsTouchOverlayViewModel = lazy;
        this.mDefaultUdfpsTouchOverlayViewModel = lazy2;
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "UdfpsController", this);
        this.mOrientationListener = new BiometricDisplayListener(context, displayManager, handler, BiometricDisplayListener.SensorType.UnderDisplayFingerprint.INSTANCE, new Function0() { // from class: com.android.systemui.biometrics.UdfpsController$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AuthController$$ExternalSyntheticLambda2 authController$$ExternalSyntheticLambda2 = UdfpsController.this.mAuthControllerUpdateUdfpsLocation;
                if (authController$$ExternalSyntheticLambda2 != null) {
                    authController$$ExternalSyntheticLambda2.run();
                }
                return Unit.INSTANCE;
            }
        });
        this.mDeviceEntryFaceAuthInteractor = deviceEntryFaceAuthInteractor;
        UdfpsOverlayController udfpsOverlayController = new UdfpsOverlayController();
        fingerprintManager2.setUdfpsOverlayController(udfpsOverlayController);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        context.registerReceiver(broadcastReceiver, intentFilter, 2);
        udfpsHapticsSimulator.udfpsController = this;
        udfpsShell.udfpsOverlayController = udfpsOverlayController;
    }

    public void cancelAodSendFingerUpAction() {
        this.mIsAodInterruptActive = false;
        ExecutorImpl.ExecutionToken executionToken = this.mCancelAodFingerUpAction;
        if (executionToken != null) {
            executionToken.run();
            this.mCancelAodFingerUpAction = null;
        }
    }

    public final void dispatchOnUiReady(long j) {
        this.mFingerprintManager.onUdfpsUiEvent(2, j, this.mSensorProps.sensorId);
        this.mLatencyTracker.onActionEnd(14);
    }

    @Override // com.android.systemui.doze.DozeReceiver
    public final void dozeTimeTick() {
        UdfpsControllerOverlay udfpsControllerOverlay = this.mOverlay;
        if (udfpsControllerOverlay != null) {
            UdfpsTouchOverlay udfpsTouchOverlay = udfpsControllerOverlay.overlayTouchView;
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        int integer = this.mContext.getResources().getInteger(R.integer.config_shutdownBatteryTemperature);
        printWriter.println("mSensorProps=(" + this.mSensorProps + ")");
        StringBuilder sb = new StringBuilder("touchConfigId: ");
        sb.append(integer);
        printWriter.println(sb.toString());
    }

    public final void hideUdfpsOverlay() {
        this.mExecution.assertIsMainThread();
        UdfpsControllerOverlay udfpsControllerOverlay = this.mOverlay;
        if (udfpsControllerOverlay != null) {
            if (udfpsControllerOverlay.overlayTouchView != null) {
                onFingerUp(udfpsControllerOverlay.requestId, -1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, 0L, false);
            }
            UdfpsControllerOverlay udfpsControllerOverlay2 = this.mOverlay;
            UdfpsTouchOverlay udfpsTouchOverlay = udfpsControllerOverlay2.overlayTouchView;
            udfpsControllerOverlay2.udfpsDisplayModeProvider.disable();
            UdfpsTouchOverlay udfpsTouchOverlay2 = udfpsControllerOverlay2.overlayTouchView;
            if (udfpsTouchOverlay2 != null) {
                if (udfpsTouchOverlay2.getParent() != null) {
                    udfpsControllerOverlay2.windowManager.removeView(udfpsTouchOverlay2);
                }
                Trace.setCounter("UdfpsAddView", 0L);
                udfpsTouchOverlay2.setOnTouchListener(null);
                udfpsTouchOverlay2.setOnHoverListener(null);
                UdfpsControllerOverlay$show$3$1 udfpsControllerOverlay$show$3$1 = udfpsControllerOverlay2.overlayTouchListener;
                if (udfpsControllerOverlay$show$3$1 != null) {
                    udfpsControllerOverlay2.accessibilityManager.removeTouchExplorationStateChangeListener(udfpsControllerOverlay$show$3$1);
                }
            }
            udfpsControllerOverlay2.overlayTouchView = null;
            udfpsControllerOverlay2.overlayTouchListener = null;
            StandaloneCoroutine standaloneCoroutine = udfpsControllerOverlay2.listenForCurrentKeyguardState;
            if (standaloneCoroutine != null) {
                standaloneCoroutine.cancel(null);
            }
            this.mKeyguardViewManager.hideAlternateBouncer(true);
        }
        this.mOverlay = null;
        BiometricDisplayListener biometricDisplayListener = this.mOrientationListener;
        biometricDisplayListener.displayManager.unregisterDisplayListener(biometricDisplayListener);
    }

    public final boolean isOptical() {
        return this.mSensorProps.sensorType == 3;
    }

    public final void onFingerDown(long j, int i, float f, float f2, float f3, float f4, float f5, long j2, long j3, boolean z) {
        this.mExecution.assertIsMainThread();
        UdfpsControllerOverlay udfpsControllerOverlay = this.mOverlay;
        if (udfpsControllerOverlay == null) {
            Log.w("UdfpsController", "Null request in onFingerDown");
            return;
        }
        long j4 = udfpsControllerOverlay.requestId;
        if (j4 != -1 && j4 != j) {
            Log.w("UdfpsController", "Mismatched fingerDown: " + j + " current: " + this.mOverlay.requestId);
            return;
        }
        if (isOptical()) {
            this.mLatencyTracker.onActionStart(14);
        }
        PowerManager powerManager = this.mPowerManager;
        ((SystemClockImpl) this.mSystemClock).getClass();
        powerManager.userActivity(android.os.SystemClock.uptimeMillis(), 2, 0);
        if (!this.mOnFingerDown) {
            playStartHaptic();
            this.mDeviceEntryFaceAuthInteractor.onUdfpsSensorTouched();
        }
        this.mOnFingerDown = true;
        this.mFingerprintManager.onPointerDown(j, this.mSensorProps.sensorId, i, f, f2, f3, f4, f5, j2, j3, z);
        Trace.endAsyncSection("UdfpsController.e2e.onPointerDown", 0);
        if (this.mOverlay.overlayTouchView != null && isOptical()) {
            if (this.mIgnoreRefreshRate) {
                dispatchOnUiReady(j);
            } else {
                UdfpsDisplayMode udfpsDisplayMode = this.mUdfpsDisplayMode;
                udfpsDisplayMode.execution.mainLooper.isCurrentThread();
                UdfpsLogger udfpsLogger = udfpsDisplayMode.logger;
                udfpsLogger.getClass();
                LogLevel logLevel = LogLevel.VERBOSE;
                LogBuffer logBuffer = udfpsLogger.logBuffer;
                logBuffer.log("UdfpsDisplayMode", logLevel, "enable", null);
                if (udfpsDisplayMode.currentRequest != null) {
                    logBuffer.log("UdfpsDisplayMode", LogLevel.ERROR, "enable | already requested", null);
                } else {
                    AuthController authController = udfpsDisplayMode.authController;
                    if (authController.mUdfpsRefreshRateRequestCallback == null) {
                        logBuffer.log("UdfpsDisplayMode", LogLevel.ERROR, "enable | mDisplayManagerCallback is null", null);
                    } else {
                        Trace.beginSection("UdfpsDisplayMode.enable");
                        int displayId = udfpsDisplayMode.context.getDisplayId();
                        udfpsDisplayMode.currentRequest = new Request(displayId);
                        try {
                            IUdfpsRefreshRateRequestCallback iUdfpsRefreshRateRequestCallback = authController.mUdfpsRefreshRateRequestCallback;
                            Intrinsics.checkNotNull(iUdfpsRefreshRateRequestCallback);
                            iUdfpsRefreshRateRequestCallback.onRequestEnabled(displayId);
                            logBuffer.log("UdfpsDisplayMode", logLevel, "enable | requested optimal refresh rate for UDFPS", null);
                        } catch (RemoteException e) {
                            logBuffer.commit(logBuffer.obtain("UdfpsDisplayMode", LogLevel.ERROR, new UdfpsLogger$e$2("enable"), e));
                        }
                        dispatchOnUiReady(j);
                        Trace.endSection();
                    }
                }
            }
        }
        if (isOptical()) {
            Iterator it = ((HashSet) this.mCallbacks).iterator();
            while (it.hasNext()) {
                ((Callback) it.next()).onFingerDown();
            }
        }
    }

    public final void onFingerUp(long j, int i, float f, float f2, float f3, float f4, float f5, long j2, long j3, boolean z) {
        UdfpsDisplayMode udfpsDisplayMode;
        this.mExecution.assertIsMainThread();
        this.mActivePointerId = -1;
        if (this.mOnFingerDown) {
            this.mFingerprintManager.onPointerUp(j, this.mSensorProps.sensorId, i, f, f2, f3, f4, f5, j2, j3, z);
            if (isOptical()) {
                Iterator it = ((HashSet) this.mCallbacks).iterator();
                while (it.hasNext()) {
                    ((Callback) it.next()).onFingerUp();
                }
            }
        }
        this.mOnFingerDown = false;
        if (isOptical() && (udfpsDisplayMode = this.mUdfpsDisplayMode) != null) {
            udfpsDisplayMode.disable();
        }
        cancelAodSendFingerUpAction();
    }

    public void playStartHaptic() {
        UdfpsTouchOverlay udfpsTouchOverlay;
        if (this.mAccessibilityManager.isTouchExplorationEnabled()) {
            UdfpsControllerOverlay udfpsControllerOverlay = this.mOverlay;
            if (udfpsControllerOverlay == null || (udfpsTouchOverlay = udfpsControllerOverlay.overlayTouchView) == null) {
                Log.e("UdfpsController", "No haptics played. Could not obtain overlay view to performvibration. Either the controller overlay is null or has no view");
            } else {
                this.mVibrator.getClass();
                udfpsTouchOverlay.performHapticFeedback(6);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v9, types: [android.view.accessibility.AccessibilityManager$TouchExplorationStateChangeListener, com.android.systemui.biometrics.UdfpsControllerOverlay$show$3$1] */
    public final void showUdfpsOverlay(final UdfpsControllerOverlay udfpsControllerOverlay) {
        this.mExecution.assertIsMainThread();
        this.mOverlay = udfpsControllerOverlay;
        if (udfpsControllerOverlay.requestReason == 4 && !this.mKeyguardUpdateMonitor.isFingerprintDetectionRunning()) {
            Log.d("UdfpsController", "Attempting to showUdfpsOverlay when fingerprint detection isn't running on keyguard. Skip show.");
            return;
        }
        UdfpsOverlayParams udfpsOverlayParams = this.mOverlayParams;
        if (udfpsControllerOverlay.overlayTouchView == null) {
            udfpsControllerOverlay.overlayParams = udfpsOverlayParams;
            udfpsControllerOverlay.sensorBounds = new Rect(udfpsOverlayParams.sensorBounds);
            try {
                final UdfpsTouchOverlay udfpsTouchOverlay = (UdfpsTouchOverlay) udfpsControllerOverlay.inflater.inflate(com.android.wm.shell.R.layout.udfps_touch_overlay, (ViewGroup) null, false);
                int i = udfpsControllerOverlay.requestReason;
                if (i == 1 || i == 2 || i == 3) {
                    udfpsTouchOverlay.setImportantForAccessibility(2);
                }
                udfpsControllerOverlay.addViewNowOrLater(udfpsTouchOverlay);
                UdfpsOverlayInteractor udfpsOverlayInteractor = udfpsControllerOverlay.udfpsOverlayInteractor;
                if (i == 4) {
                    UdfpsTouchOverlayBinder.bind(udfpsTouchOverlay, (UdfpsTouchOverlayViewModel) udfpsControllerOverlay.deviceEntryUdfpsTouchOverlayViewModel.get(), udfpsOverlayInteractor);
                } else {
                    UdfpsTouchOverlayBinder.bind(udfpsTouchOverlay, (UdfpsTouchOverlayViewModel) udfpsControllerOverlay.defaultUdfpsTouchOverlayViewModel.get(), udfpsOverlayInteractor);
                }
                udfpsControllerOverlay.overlayTouchView = udfpsTouchOverlay;
                udfpsControllerOverlay.accessibilityManager.isTouchExplorationEnabled();
                ?? r1 = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: com.android.systemui.biometrics.UdfpsControllerOverlay$show$3$1
                    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
                    public final void onTouchExplorationStateChanged(boolean z) {
                        if (UdfpsControllerOverlay.this.accessibilityManager.isTouchExplorationEnabled()) {
                            UdfpsTouchOverlay udfpsTouchOverlay2 = udfpsTouchOverlay;
                            final UdfpsControllerOverlay udfpsControllerOverlay2 = UdfpsControllerOverlay.this;
                            udfpsTouchOverlay2.setOnHoverListener(new View.OnHoverListener() { // from class: com.android.systemui.biometrics.UdfpsControllerOverlay$show$3$1.1
                                @Override // android.view.View.OnHoverListener
                                public final boolean onHover(View view, MotionEvent motionEvent) {
                                    UdfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6 udfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6 = UdfpsControllerOverlay.this.onTouch;
                                    Intrinsics.checkNotNull(view);
                                    Intrinsics.checkNotNull(motionEvent);
                                    return ((Boolean) udfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6.invoke(view, motionEvent, Boolean.TRUE)).booleanValue();
                                }
                            });
                            udfpsTouchOverlay.setOnTouchListener(null);
                            UdfpsControllerOverlay.this.getClass();
                            return;
                        }
                        udfpsTouchOverlay.setOnHoverListener(null);
                        UdfpsTouchOverlay udfpsTouchOverlay3 = udfpsTouchOverlay;
                        final UdfpsControllerOverlay udfpsControllerOverlay3 = UdfpsControllerOverlay.this;
                        udfpsTouchOverlay3.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.biometrics.UdfpsControllerOverlay$show$3$1.2
                            @Override // android.view.View.OnTouchListener
                            public final boolean onTouch(View view, MotionEvent motionEvent) {
                                UdfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6 udfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6 = UdfpsControllerOverlay.this.onTouch;
                                Intrinsics.checkNotNull(view);
                                Intrinsics.checkNotNull(motionEvent);
                                return ((Boolean) udfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6.invoke(view, motionEvent, Boolean.TRUE)).booleanValue();
                            }
                        });
                        UdfpsControllerOverlay.this.getClass();
                    }
                };
                udfpsControllerOverlay.overlayTouchListener = r1;
                udfpsControllerOverlay.accessibilityManager.addTouchExplorationStateChangeListener(r1);
                UdfpsControllerOverlay$show$3$1 udfpsControllerOverlay$show$3$1 = udfpsControllerOverlay.overlayTouchListener;
                if (udfpsControllerOverlay$show$3$1 != null) {
                    udfpsControllerOverlay$show$3$1.onTouchExplorationStateChanged(true);
                }
            } catch (RuntimeException e) {
                Log.e("UdfpsControllerOverlay", "showUdfpsOverlay | failed to add window", e);
            }
            this.mOnFingerDown = false;
            this.mAttemptedToDismissKeyguard = false;
            BiometricDisplayListener biometricDisplayListener = this.mOrientationListener;
            Display display = biometricDisplayListener.context.getDisplay();
            if (display != null) {
                display.getDisplayInfo(biometricDisplayListener.cachedDisplayInfo);
            }
            biometricDisplayListener.displayManager.registerDisplayListener(biometricDisplayListener, biometricDisplayListener.handler, 4L);
            FingerprintManager fingerprintManager = this.mFingerprintManager;
            if (fingerprintManager != null) {
                fingerprintManager.onUdfpsUiEvent(1, udfpsControllerOverlay.requestId, this.mSensorProps.sensorId);
            }
        }
    }

    public void tryAodSendFingerUp() {
        if (this.mIsAodInterruptActive) {
            cancelAodSendFingerUpAction();
            UdfpsControllerOverlay udfpsControllerOverlay = this.mOverlay;
            if (udfpsControllerOverlay == null || udfpsControllerOverlay.overlayTouchView == null) {
                return;
            }
            onFingerUp(udfpsControllerOverlay.requestId, -1, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0L, 0L, false);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UdfpsOverlayController extends IUdfpsOverlayController.Stub {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.biometrics.UdfpsController$UdfpsOverlayController$1, reason: invalid class name */
        public final class AnonymousClass1 extends BiometricPrompt.AuthenticationCallback {
        }

        public UdfpsOverlayController() {
        }

        public final void hideUdfpsOverlay(int i) {
            ((ExecutorImpl) UdfpsController.this.mFgExecutor).execute(new UdfpsController$$ExternalSyntheticLambda0(1, this));
        }

        public final void onAcquired(final int i, final int i2) {
            UdfpsController udfpsController = UdfpsController.this;
            if (udfpsController.mSensorProps.sensorType == 2) {
                DelayableExecutor delayableExecutor = udfpsController.mFgExecutor;
                if (i2 == 7) {
                    ((ExecutorImpl) delayableExecutor).execute(new UdfpsController$$ExternalSyntheticLambda0(2, this));
                } else {
                    ((ExecutorImpl) delayableExecutor).execute(new UdfpsController$$ExternalSyntheticLambda0(3, this));
                }
            }
            if (BiometricFingerprintConstants.shouldDisableUdfpsDisplayMode(i2)) {
                ((ExecutorImpl) UdfpsController.this.mFgExecutor).execute(new Runnable() { // from class: com.android.systemui.biometrics.UdfpsController$UdfpsOverlayController$$ExternalSyntheticLambda4
                    @Override // java.lang.Runnable
                    public final void run() {
                        UdfpsDisplayMode udfpsDisplayMode;
                        UdfpsController.UdfpsOverlayController udfpsOverlayController = UdfpsController.UdfpsOverlayController.this;
                        int i3 = i;
                        int i4 = i2;
                        UdfpsController udfpsController2 = UdfpsController.this;
                        if (udfpsController2.mOverlay != null) {
                            if (udfpsController2.isOptical() && (udfpsDisplayMode = udfpsController2.mUdfpsDisplayMode) != null) {
                                udfpsDisplayMode.disable();
                            }
                            UdfpsController.this.tryAodSendFingerUp();
                            return;
                        }
                        Log.e("UdfpsController", "Null request when onAcquired for sensorId: " + i3 + " acquiredInfo=" + i4);
                    }
                });
            }
        }

        public final void setDebugMessage(int i, String str) {
            ((ExecutorImpl) UdfpsController.this.mFgExecutor).execute(new UdfpsController$$ExternalSyntheticLambda0(this, str));
        }

        public final void showUdfpsOverlay(final long j, int i, final int i2, final IUdfpsOverlayControllerCallback iUdfpsOverlayControllerCallback) {
            StateFlowImpl stateFlowImpl = UdfpsController.this.mUdfpsOverlayInteractor._requestId;
            Long valueOf = Long.valueOf(j);
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, valueOf);
            ((ExecutorImpl) UdfpsController.this.mFgExecutor).execute(new Runnable() { // from class: com.android.systemui.biometrics.UdfpsController$UdfpsOverlayController$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    UdfpsController.UdfpsOverlayController udfpsOverlayController = UdfpsController.UdfpsOverlayController.this;
                    long j2 = j;
                    int i3 = i2;
                    IUdfpsOverlayControllerCallback iUdfpsOverlayControllerCallback2 = iUdfpsOverlayControllerCallback;
                    UdfpsController udfpsController = UdfpsController.this;
                    udfpsController.showUdfpsOverlay(new UdfpsControllerOverlay(udfpsController.mContext, udfpsController.mInflater, udfpsController.mWindowManager, udfpsController.mAccessibilityManager, udfpsController.mStatusBarStateController, udfpsController.mKeyguardViewManager, udfpsController.mKeyguardUpdateMonitor, udfpsController.mDialogManager, udfpsController.mDumpManager, udfpsController.mLockscreenShadeTransitionController, udfpsController.mConfigurationController, udfpsController.mKeyguardStateController, udfpsController.mUnlockedScreenOffAnimationController, udfpsController.mUdfpsDisplayMode, j2, i3, iUdfpsOverlayControllerCallback2, new UdfpsController$UdfpsOverlayController$$ExternalSyntheticLambda6(udfpsOverlayController, j2), udfpsController.mActivityTransitionAnimator, udfpsController.mPrimaryBouncerInteractor, udfpsController.mAlternateBouncerInteractor, udfpsController.mUdfpsKeyguardAccessibilityDelegate, udfpsController.mKeyguardTransitionInteractor, udfpsController.mSelectedUserInteractor, udfpsController.mDeviceEntryUdfpsTouchOverlayViewModel, udfpsController.mDefaultUdfpsTouchOverlayViewModel, udfpsController.mShadeInteractor, udfpsController.mUdfpsOverlayInteractor, udfpsController.mPowerInteractor, udfpsController.mScope));
                }
            });
        }

        public final void onEnrollmentHelp(int i) {
        }

        public final void onEnrollmentProgress(int i, int i2) {
        }
    }
}

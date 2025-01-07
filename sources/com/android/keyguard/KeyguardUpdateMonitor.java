package com.android.keyguard;

import android.R;
import android.app.ActivityTaskManager;
import android.app.IActivityTaskManager;
import android.app.admin.DevicePolicyManager;
import android.app.trust.TrustManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.UserInfo;
import android.database.ContentObserver;
import android.hardware.SensorPrivacyManager;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricSourceType;
import android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback;
import android.hardware.biometrics.SensorPropertiesInternal;
import android.hardware.biometrics.common.AuthenticateReason;
import android.hardware.fingerprint.FingerprintAuthenticateOptions;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import android.hardware.fingerprint.IFingerprintAuthenticatorsRegisteredCallback;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.Trace;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.service.dreams.IDreamManager;
import android.telephony.CarrierConfigManager;
import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import com.android.compose.animation.scene.ObservableTransitionState;
import com.android.internal.foldables.FoldGracePeriodProvider;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.ActiveUnlockConfig;
import com.android.keyguard.KeyguardActiveUnlockModel;
import com.android.keyguard.KeyguardFingerprintListenModel;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger;
import com.android.settingslib.Utils;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.android.systemui.CoreStartable;
import com.android.systemui.Dumpable;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfig;
import com.android.systemui.deviceentry.data.repository.FaceWakeUpTriggersConfigImpl;
import com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor;
import com.android.systemui.deviceentry.shared.FaceAuthUiEvent;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.dump.DumpsysTableLogger;
import com.android.systemui.keyguard.shared.constants.TrustAgentUiEvent;
import com.android.systemui.keyguard.shared.model.DevicePosture;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.clocks.WeatherData;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.statusbar.StatusBarState;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DevicePostureControllerImpl;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.Assert;
import com.google.android.collect.Lists;
import com.google.android.systemui.fingerprint.FingerprintInteractiveToAuthProviderGoogle;
import com.google.hardware.biometrics.parcelables.fingerprint.PressToAuthParcelable;
import dalvik.annotation.optimization.NeverCompile;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.inject.Provider;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.TransformingSequence;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class KeyguardUpdateMonitor implements TrustManager.TrustListener, Dumpable, CoreStartable {
    public static final int BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED = -1;
    protected static final int BIOMETRIC_STATE_CANCELLING = 2;
    protected static final int BIOMETRIC_STATE_CANCELLING_RESTARTING = 3;
    protected static final int BIOMETRIC_STATE_STOPPED = 0;
    protected static final int DEFAULT_CANCEL_SIGNAL_TIMEOUT = 3000;
    protected static final int HAL_POWER_PRESS_TIMEOUT = 50;
    public int mActiveMobileDataSubscription;
    public final ActiveUnlockConfig mActiveUnlockConfig;
    public final KeyguardActiveUnlockModel.Buffer mActiveUnlockTriggerBuffer;
    public final IActivityTaskManager mActivityTaskManager;
    public boolean mAllowFingerprintOnCurrentOccludingActivity;
    public final Set mAllowFingerprintOnOccludingActivitiesFromPackage;
    public boolean mAlternateBouncerShowing;
    public boolean mAssistantVisible;
    public final AuthController mAuthController;
    public boolean mAuthInterruptActive;
    public final Executor mBackgroundExecutor;
    BatteryStatus mBatteryStatus;
    public final AnonymousClass2 mBiometricEnabledCallback;
    public final SparseBooleanArray mBiometricEnabledForUser;
    public final BiometricManager mBiometricManager;
    public boolean mBiometricPromptShowing;
    protected final BroadcastReceiver mBroadcastAllReceiver;
    public final BroadcastDispatcher mBroadcastDispatcher;
    protected final BroadcastReceiver mBroadcastReceiver;
    public final ArrayList mCallbacks;
    protected int mConfigFaceAuthSupportedPosture;
    public final Context mContext;
    public boolean mCredentialAttempted;
    public boolean mDeviceInteractive;
    public final DevicePolicyManager mDevicePolicyManager;
    public final DevicePostureController mDevicePostureController;
    public boolean mDeviceProvisioned;
    public AnonymousClass14 mDeviceProvisionedObserver;
    public final IDreamManager mDreamManager;
    public SystemUIDeviceEntryFaceAuthInteractor mFaceAuthInteractor;
    public final AnonymousClass6 mFaceAuthenticationListener;
    public final FaceWakeUpTriggersConfig mFaceWakeUpTriggersConfig;
    final FingerprintManager.AuthenticationCallback mFingerprintAuthenticationCallback;
    CancellationSignal mFingerprintCancelSignal;
    public boolean mFingerprintDetectRunning;
    public final AnonymousClass11 mFingerprintDetectionCallback;
    public final FingerprintInteractiveToAuthProviderGoogle mFingerprintInteractiveToAuthProvider;
    public final KeyguardFingerprintListenModel.Buffer mFingerprintListenBuffer;
    public boolean mFingerprintLockedOut;
    public boolean mFingerprintLockedOutPermanent;
    public final AnonymousClass9 mFingerprintLockoutResetCallback;
    protected int mFingerprintRunningState;
    public List mFingerprintSensorProperties;
    protected FoldGracePeriodProvider mFoldGracePeriodProvider;
    public boolean mForceIsDismissible;
    protected final Runnable mFpCancelNotReceived;
    public final FingerprintManager mFpm;
    public boolean mGoingToSleep;
    public final AnonymousClass13 mHandler;
    public int mHardwareFingerprintUnavailableRetryCount;
    boolean mIncompatibleCharger;
    public final InteractionJankMonitor mInteractionJankMonitor;
    public boolean mIsDreaming;
    public final boolean mIsSystemUser;
    public KeyguardBypassController mKeyguardBypassController;
    public boolean mKeyguardGoingAway;
    public boolean mKeyguardOccluded;
    public boolean mKeyguardShowing;
    public final LatencyTracker mLatencyTracker;
    public final LockPatternUtils mLockPatternUtils;
    public final KeyguardUpdateMonitorLogger mLogger;
    public boolean mLogoutEnabled;
    public final Executor mMainExecutor;
    public boolean mNeedsSlowUnlockTransition;
    public boolean mOccludingAppRequestingFp;
    public final PackageManager mPackageManager;
    public TelephonyCallback.ActiveDataSubscriptionIdListener mPhoneStateListener;
    final DevicePostureController.Callback mPostureCallback;
    public boolean mPrimaryBouncerFullyShown;
    public boolean mPrimaryBouncerIsOrWillBeShowing;
    public final AnonymousClass5 mRetryFingerprintAuthenticationAfterHwUnavailable;
    public final Map mSecondaryLockscreenRequirement;
    public boolean mSecureCameraLaunched;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public final SensorPrivacyManager mSensorPrivacyManager;
    public final HashMap mServiceStates;
    public final Provider mSessionTrackerProvider;
    public final HashMap mSimDatas;
    public int mStatusBarState;
    public final StatusBarStateController mStatusBarStateController;
    public final AnonymousClass1 mStatusBarStateControllerListener;
    public StrongAuthTracker mStrongAuthTracker;
    public List mSubscriptionInfo;
    final SubscriptionManager.OnSubscriptionsChangedListener mSubscriptionListener;
    public final SubscriptionManager mSubscriptionManager;
    public boolean mSwitchingUser;
    public final TaskStackChangeListeners mTaskStackChangeListeners;
    public final AnonymousClass19 mTaskStackListener;
    protected boolean mTelephonyCapable;
    public final TelephonyListenerManager mTelephonyListenerManager;
    public final TelephonyManager mTelephonyManager;
    public final AnonymousClass14 mTimeFormatChangeObserver;
    public final TrustManager mTrustManager;
    public final UiEventLogger mUiEventLogger;
    public final UserTracker.Callback mUserChangedCallback;
    SparseArray mUserFingerprintAuthenticated;
    public final SparseBooleanArray mUserHasTrust;
    public final SparseBooleanArray mUserIsUnlocked;
    public final UserManager mUserManager;
    public final UserTracker mUserTracker;
    public final SparseBooleanArray mUserTrustIsManaged;
    public final SparseBooleanArray mUserTrustIsUsuallyManaged;
    public static final ComponentName FALLBACK_HOME_COMPONENT = new ComponentName("com.android.settings", "com.android.settings.FallbackHome");
    public static final List ABSENT_SIM_STATE_LIST = Arrays.asList(1, 0, 6);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardUpdateMonitor$14, reason: invalid class name */
    public final class AnonymousClass14 extends ContentObserver {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ KeyguardUpdateMonitor this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass14(KeyguardUpdateMonitor keyguardUpdateMonitor, AnonymousClass13 anonymousClass13, int i) {
            super(anonymousClass13);
            this.$r8$classId = i;
            this.this$0 = keyguardUpdateMonitor;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            switch (this.$r8$classId) {
                case 0:
                    KeyguardUpdateMonitor keyguardUpdateMonitor = this.this$0;
                    AnonymousClass13 anonymousClass13 = keyguardUpdateMonitor.mHandler;
                    anonymousClass13.sendMessage(anonymousClass13.obtainMessage(344, Settings.System.getString(keyguardUpdateMonitor.mContext.getContentResolver(), "time_12_24")));
                    break;
                default:
                    super.onChange(z);
                    KeyguardUpdateMonitor keyguardUpdateMonitor2 = this.this$0;
                    int i = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                    keyguardUpdateMonitor2.mDeviceProvisioned = keyguardUpdateMonitor2.isDeviceProvisionedInSettingsDb();
                    KeyguardUpdateMonitor keyguardUpdateMonitor3 = this.this$0;
                    if (keyguardUpdateMonitor3.mDeviceProvisioned) {
                        keyguardUpdateMonitor3.mHandler.sendEmptyMessage(308);
                    }
                    KeyguardUpdateMonitor keyguardUpdateMonitor4 = this.this$0;
                    keyguardUpdateMonitor4.mLogger.logDeviceProvisionedState(keyguardUpdateMonitor4.mDeviceProvisioned);
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardUpdateMonitor$16, reason: invalid class name */
    public final class AnonymousClass16 implements AuthController.Callback {
        public AnonymousClass16() {
        }

        @Override // com.android.systemui.biometrics.AuthController.Callback
        public final void onAllAuthenticatorsRegistered(int i) {
            KeyguardUpdateMonitor.this.mMainExecutor.execute(new KeyguardUpdateMonitor$16$$ExternalSyntheticLambda0(this, 0));
        }

        @Override // com.android.systemui.biometrics.AuthController.Callback
        public final void onBiometricPromptDismissed() {
            KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
            keyguardUpdateMonitor.mBiometricPromptShowing = false;
            keyguardUpdateMonitor.updateFingerprintListeningState(0);
        }

        @Override // com.android.systemui.biometrics.AuthController.Callback
        public final void onBiometricPromptShown() {
            KeyguardUpdateMonitor.this.mBiometricPromptShowing = true;
        }

        @Override // com.android.systemui.biometrics.AuthController.Callback
        public final void onEnrollmentsChanged(int i) {
            KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
            keyguardUpdateMonitor.mHandler.obtainMessage(348, i, 0).sendToTarget();
            keyguardUpdateMonitor.mMainExecutor.execute(new KeyguardUpdateMonitor$16$$ExternalSyntheticLambda0(this, 1));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardUpdateMonitor$2, reason: invalid class name */
    public final class AnonymousClass2 extends IBiometricEnabledOnKeyguardCallback.Stub {
        public AnonymousClass2() {
        }

        public final void onChanged(boolean z, int i) {
            post(new KeyguardUpdateMonitor$$ExternalSyntheticLambda2(this, i, z));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardUpdateMonitor$20, reason: invalid class name */
    public abstract /* synthetic */ class AnonymousClass20 {
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
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardUpdateMonitor$6, reason: invalid class name */
    public final class AnonymousClass6 {
        public AnonymousClass6() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class BiometricAuthenticated {
        public final boolean mIsStrongBiometric;

        public BiometricAuthenticated(boolean z) {
            this.mIsStrongBiometric = z;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SimData {
        public int simState;
        public int slotId;
        public int subId;

        public SimData(int i, int i2, int i3) {
            this.simState = i;
            this.slotId = i2;
            this.subId = i3;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("SimData{state=");
            sb.append(this.simState);
            sb.append(",slotId=");
            sb.append(this.slotId);
            sb.append(",subId=");
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.subId, "}");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StrongAuthTracker extends LockPatternUtils.StrongAuthTracker {
        public StrongAuthTracker(Context context) {
            super(context);
        }

        public final boolean hasUserAuthenticatedSinceBoot() {
            return (getStrongAuthForUser(KeyguardUpdateMonitor.this.mSelectedUserInteractor.getSelectedUserId()) & 1) == 0;
        }

        public final void onIsNonStrongBiometricAllowedChanged(int i) {
            KeyguardUpdateMonitor.this.notifyNonStrongBiometricAllowedChanged(i);
        }

        public final void onStrongAuthRequiredChanged(int i) {
            KeyguardUpdateMonitor.this.notifyStrongAuthAllowedChanged(i);
        }
    }

    /* renamed from: -$$Nest$mhandleBiometricDetected, reason: not valid java name */
    public static void m753$$Nest$mhandleBiometricDetected(KeyguardUpdateMonitor keyguardUpdateMonitor, int i, BiometricSourceType biometricSourceType, boolean z) {
        Trace.beginSection("KeyGuardUpdateMonitor#handlerBiometricDetected");
        Assert.isMainThread();
        Trace.beginSection("KeyGuardUpdateMonitor#onBiometricDetected");
        for (int i2 = 0; i2 < keyguardUpdateMonitor.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onBiometricDetected(biometricSourceType);
            }
        }
        Trace.endSection();
        if (biometricSourceType == BiometricSourceType.FINGERPRINT) {
            keyguardUpdateMonitor.mLogger.logFingerprintDetected(i, z);
        } else if (biometricSourceType == BiometricSourceType.FACE) {
            keyguardUpdateMonitor.mLogger.logFaceDetected(i, z);
        }
        Trace.endSection();
    }

    /* renamed from: -$$Nest$mhandleFingerprintError, reason: not valid java name */
    public static void m754$$Nest$mhandleFingerprintError(KeyguardUpdateMonitor keyguardUpdateMonitor, int i, String str) {
        boolean z;
        Assert.isMainThread();
        if (keyguardUpdateMonitor.mHandler.hasCallbacks(keyguardUpdateMonitor.mFpCancelNotReceived)) {
            keyguardUpdateMonitor.mHandler.removeCallbacks(keyguardUpdateMonitor.mFpCancelNotReceived);
        }
        keyguardUpdateMonitor.mFingerprintCancelSignal = null;
        if (i == 5 && keyguardUpdateMonitor.mFingerprintRunningState == 3) {
            keyguardUpdateMonitor.setFingerprintRunningState(0);
            keyguardUpdateMonitor.updateFingerprintListeningState(2);
        } else {
            keyguardUpdateMonitor.setFingerprintRunningState(0);
        }
        if (i == 1) {
            keyguardUpdateMonitor.mLogger.logRetryAfterFpErrorWithDelay(str, i, 500);
            keyguardUpdateMonitor.mHandler.postDelayed(keyguardUpdateMonitor.mRetryFingerprintAuthenticationAfterHwUnavailable, 500L);
        }
        if (i == 19) {
            keyguardUpdateMonitor.mLogger.logRetryAfterFpErrorWithDelay(str, i, HAL_POWER_PRESS_TIMEOUT);
            keyguardUpdateMonitor.mHandler.postDelayed(new KeyguardUpdateMonitor$$ExternalSyntheticLambda3(keyguardUpdateMonitor, 4), 50L);
        }
        if (i == 9) {
            z = !keyguardUpdateMonitor.mFingerprintLockedOutPermanent;
            keyguardUpdateMonitor.mFingerprintLockedOutPermanent = true;
            keyguardUpdateMonitor.mLogger.d("Fingerprint permanently locked out - requiring stronger auth");
            keyguardUpdateMonitor.mLockPatternUtils.requireStrongAuth(8, keyguardUpdateMonitor.mSelectedUserInteractor.getSelectedUserId());
        } else {
            z = false;
        }
        if (i == 7 || i == 9) {
            z |= !keyguardUpdateMonitor.mFingerprintLockedOut;
            keyguardUpdateMonitor.mFingerprintLockedOut = true;
            keyguardUpdateMonitor.mLogger.d("Fingerprint temporarily locked out - requiring stronger auth");
            if (keyguardUpdateMonitor.isUdfpsEnrolled()) {
                keyguardUpdateMonitor.updateFingerprintListeningState(2);
            }
        }
        keyguardUpdateMonitor.mLogger.logFingerprintError(i, str);
        for (int i2 = 0; i2 < keyguardUpdateMonitor.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onBiometricError(i, str, BiometricSourceType.FINGERPRINT);
            }
        }
        if (z) {
            keyguardUpdateMonitor.notifyLockedOutStateChanged(BiometricSourceType.FINGERPRINT);
        }
    }

    /* renamed from: -$$Nest$mnotifyAboutEnrollmentChange, reason: not valid java name */
    public static void m755$$Nest$mnotifyAboutEnrollmentChange(KeyguardUpdateMonitor keyguardUpdateMonitor, int i) {
        BiometricSourceType biometricSourceType;
        if (i == 2) {
            biometricSourceType = BiometricSourceType.FINGERPRINT;
        } else if (i != 8) {
            return;
        } else {
            biometricSourceType = BiometricSourceType.FACE;
        }
        keyguardUpdateMonitor.mLogger.notifyAboutEnrollmentsChanged(biometricSourceType);
        Assert.isMainThread();
        for (int i2 = 0; i2 < keyguardUpdateMonitor.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onBiometricEnrollmentStateChanged(biometricSourceType);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v14, types: [com.android.keyguard.KeyguardUpdateMonitor$13] */
    /* JADX WARN: Type inference failed for: r5v14, types: [com.android.keyguard.KeyguardUpdateMonitor$5] */
    /* JADX WARN: Type inference failed for: r5v18, types: [com.android.keyguard.KeyguardUpdateMonitor$9] */
    /* JADX WARN: Type inference failed for: r5v20, types: [com.android.keyguard.KeyguardUpdateMonitor$11] */
    /* JADX WARN: Type inference failed for: r5v24, types: [com.android.keyguard.KeyguardUpdateMonitor$19] */
    public KeyguardUpdateMonitor(Context context, UserTracker userTracker, Looper looper, BroadcastDispatcher broadcastDispatcher, DumpManager dumpManager, Executor executor, Executor executor2, StatusBarStateController statusBarStateController, LockPatternUtils lockPatternUtils, AuthController authController, TelephonyListenerManager telephonyListenerManager, InteractionJankMonitor interactionJankMonitor, LatencyTracker latencyTracker, ActiveUnlockConfig activeUnlockConfig, KeyguardUpdateMonitorLogger keyguardUpdateMonitorLogger, UiEventLogger uiEventLogger, Provider provider, TrustManager trustManager, SubscriptionManager subscriptionManager, UserManager userManager, IDreamManager iDreamManager, DevicePolicyManager devicePolicyManager, SensorPrivacyManager sensorPrivacyManager, TelephonyManager telephonyManager, PackageManager packageManager, FingerprintManager fingerprintManager, BiometricManager biometricManager, FaceWakeUpTriggersConfig faceWakeUpTriggersConfig, DevicePostureController devicePostureController, Optional optional, TaskStackChangeListeners taskStackChangeListeners, SelectedUserInteractor selectedUserInteractor, IActivityTaskManager iActivityTaskManager, Provider provider2, Provider provider3, Provider provider4) {
        StatusBarStateController.StateListener stateListener = new StatusBarStateController.StateListener() { // from class: com.android.keyguard.KeyguardUpdateMonitor.1
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onExpandedChanged(boolean z) {
                int i = 0;
                while (true) {
                    KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                    if (i >= keyguardUpdateMonitor.mCallbacks.size()) {
                        return;
                    }
                    KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i)).get();
                    if (keyguardUpdateMonitorCallback != null) {
                        keyguardUpdateMonitorCallback.onShadeExpandedChanged(z);
                    }
                    i++;
                }
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onStateChanged(int i) {
                KeyguardUpdateMonitor.this.mStatusBarState = i;
            }
        };
        this.mSimDatas = new HashMap();
        this.mServiceStates = new HashMap();
        this.mCallbacks = Lists.newArrayList();
        this.mFoldGracePeriodProvider = new FoldGracePeriodProvider();
        final int i = 0;
        this.mFingerprintRunningState = 0;
        this.mActiveMobileDataSubscription = -1;
        this.mHardwareFingerprintUnavailableRetryCount = 0;
        this.mFpCancelNotReceived = new KeyguardUpdateMonitor$$ExternalSyntheticLambda3(this, i);
        this.mBiometricEnabledCallback = new AnonymousClass2();
        this.mPhoneStateListener = new TelephonyCallback.ActiveDataSubscriptionIdListener() { // from class: com.android.keyguard.KeyguardUpdateMonitor.3
            @Override // android.telephony.TelephonyCallback.ActiveDataSubscriptionIdListener
            public final void onActiveDataSubscriptionIdChanged(int i2) {
                KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                keyguardUpdateMonitor.mActiveMobileDataSubscription = i2;
                keyguardUpdateMonitor.mHandler.sendEmptyMessage(328);
            }
        };
        this.mSubscriptionListener = new SubscriptionManager.OnSubscriptionsChangedListener() { // from class: com.android.keyguard.KeyguardUpdateMonitor.4
            @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
            public final void onSubscriptionsChanged() {
                sendEmptyMessage(328);
            }
        };
        this.mUserIsUnlocked = new SparseBooleanArray();
        this.mUserHasTrust = new SparseBooleanArray();
        this.mUserTrustIsManaged = new SparseBooleanArray();
        this.mUserTrustIsUsuallyManaged = new SparseBooleanArray();
        this.mBiometricEnabledForUser = new SparseBooleanArray();
        this.mSecondaryLockscreenRequirement = new HashMap();
        this.mFingerprintListenBuffer = new KeyguardFingerprintListenModel.Buffer();
        this.mActiveUnlockTriggerBuffer = new KeyguardActiveUnlockModel.Buffer();
        this.mUserFingerprintAuthenticated = new SparseArray();
        this.mRetryFingerprintAuthenticationAfterHwUnavailable = new Runnable() { // from class: com.android.keyguard.KeyguardUpdateMonitor.5
            @Override // java.lang.Runnable
            public final void run() {
                KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                keyguardUpdateMonitor.mLogger.logRetryAfterFpHwUnavailable(keyguardUpdateMonitor.mHardwareFingerprintUnavailableRetryCount);
                if (!KeyguardUpdateMonitor.this.mFingerprintSensorProperties.isEmpty()) {
                    KeyguardUpdateMonitor.this.updateFingerprintListeningState(2);
                    return;
                }
                KeyguardUpdateMonitor keyguardUpdateMonitor2 = KeyguardUpdateMonitor.this;
                int i2 = keyguardUpdateMonitor2.mHardwareFingerprintUnavailableRetryCount;
                if (i2 < 20) {
                    keyguardUpdateMonitor2.mHardwareFingerprintUnavailableRetryCount = i2 + 1;
                    keyguardUpdateMonitor2.mHandler.postDelayed(keyguardUpdateMonitor2.mRetryFingerprintAuthenticationAfterHwUnavailable, 500L);
                }
            }
        };
        this.mFaceAuthenticationListener = new AnonymousClass6();
        this.mBroadcastReceiver = new BroadcastReceiver(this) { // from class: com.android.keyguard.KeyguardUpdateMonitor.7
            public final /* synthetic */ KeyguardUpdateMonitor this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                int i2;
                switch (i) {
                    case 0:
                        String action = intent.getAction();
                        this.this$0.mLogger.logBroadcastReceived(action);
                        if ("android.intent.action.TIME_TICK".equals(action) || "android.intent.action.TIME_SET".equals(action)) {
                            sendEmptyMessage(301);
                            return;
                        }
                        if ("android.intent.action.TIMEZONE_CHANGED".equals(action)) {
                            sendMessage(obtainMessage(339, intent.getStringExtra("time-zone")));
                            return;
                        }
                        if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
                            KeyguardUpdateMonitor keyguardUpdateMonitor = this.this$0;
                            sendMessage(keyguardUpdateMonitor.mHandler.obtainMessage(302, new BatteryStatus(intent, keyguardUpdateMonitor.mIncompatibleCharger)));
                            return;
                        }
                        if ("android.hardware.usb.action.USB_PORT_COMPLIANCE_CHANGED".equals(action)) {
                            this.this$0.mIncompatibleCharger = Utils.containsIncompatibleChargers(context2, "KeyguardUpdateMonitor");
                            boolean z = this.this$0.mIncompatibleCharger;
                            Intent registerReceiver = context2.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                            BatteryStatus batteryStatus = registerReceiver != null ? new BatteryStatus(registerReceiver, z) : null;
                            if (batteryStatus != null) {
                                AnonymousClass13 anonymousClass13 = this.this$0.mHandler;
                                anonymousClass13.sendMessage(anonymousClass13.obtainMessage(302, batteryStatus));
                                return;
                            }
                            return;
                        }
                        if (!"android.intent.action.SIM_STATE_CHANGED".equals(action)) {
                            if ("android.intent.action.PHONE_STATE".equals(action)) {
                                String stringExtra = intent.getStringExtra(WeatherData.STATE_KEY);
                                AnonymousClass13 anonymousClass132 = this.this$0.mHandler;
                                anonymousClass132.sendMessage(anonymousClass132.obtainMessage(306, stringExtra));
                                return;
                            }
                            if ("android.telephony.action.SERVICE_PROVIDERS_UPDATED".equals(action)) {
                                obtainMessage(347, intent).sendToTarget();
                                return;
                            }
                            if ("android.intent.action.AIRPLANE_MODE".equals(action)) {
                                sendEmptyMessage(329);
                                return;
                            }
                            if ("android.intent.action.SERVICE_STATE".equals(action)) {
                                ServiceState newFromBundle = ServiceState.newFromBundle(intent.getExtras());
                                int intExtra = intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_INDEX", -1);
                                this.this$0.mLogger.logServiceStateIntent(action, newFromBundle, intExtra);
                                AnonymousClass13 anonymousClass133 = this.this$0.mHandler;
                                anonymousClass133.sendMessage(anonymousClass133.obtainMessage(330, intExtra, 0, newFromBundle));
                                return;
                            }
                            if ("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED".equals(action)) {
                                sendEmptyMessage(328);
                                return;
                            } else {
                                if ("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED".equals(action)) {
                                    sendEmptyMessage(337);
                                    return;
                                }
                                return;
                            }
                        }
                        if (!"android.intent.action.SIM_STATE_CHANGED".equals(intent.getAction())) {
                            throw new IllegalArgumentException("only handles intent ACTION_SIM_STATE_CHANGED");
                        }
                        String stringExtra2 = intent.getStringExtra("ss");
                        int intExtra2 = intent.getIntExtra("android.telephony.extra.SLOT_INDEX", 0);
                        int intExtra3 = intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_INDEX", -1);
                        if ("ABSENT".equals(stringExtra2)) {
                            i2 = 1;
                        } else if ("LOCKED".equals(stringExtra2)) {
                            String stringExtra3 = intent.getStringExtra("reason");
                            if ("PIN".equals(stringExtra3)) {
                                i2 = 2;
                            } else if ("PUK".equals(stringExtra3)) {
                                i2 = 3;
                            } else if ("NETWORK".equals(stringExtra3)) {
                                i2 = 4;
                            } else {
                                if ("PERM_DISABLED".equals(stringExtra3)) {
                                    i2 = 7;
                                }
                                i2 = 0;
                            }
                        } else if ("CARD_IO_ERROR".equals(stringExtra2)) {
                            i2 = 8;
                        } else if ("CARD_RESTRICTED".equals(stringExtra2)) {
                            i2 = 9;
                        } else if ("NOT_READY".equals(stringExtra2)) {
                            i2 = 6;
                        } else {
                            if ("READY".equals(stringExtra2) || "LOADED".equals(stringExtra2) || "IMSI".equals(stringExtra2)) {
                                i2 = 5;
                            }
                            i2 = 0;
                        }
                        if (intent.getBooleanExtra("rebroadcastOnUnlock", false)) {
                            if (i2 == 1) {
                                obtainMessage(338, Boolean.TRUE).sendToTarget();
                                return;
                            }
                            return;
                        } else {
                            this.this$0.mLogger.logSimStateFromIntent(action, intExtra2, intExtra3, intent.getStringExtra("ss"));
                            if (intExtra2 == -1) {
                                return;
                            }
                            obtainMessage(304, intExtra3, intExtra2, Integer.valueOf(i2)).sendToTarget();
                            return;
                        }
                    default:
                        String action2 = intent.getAction();
                        if ("android.app.action.NEXT_ALARM_CLOCK_CHANGED".equals(action2)) {
                            sendEmptyMessage(301);
                            return;
                        }
                        if ("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED".equals(action2)) {
                            AnonymousClass13 anonymousClass134 = this.this$0.mHandler;
                            anonymousClass134.sendMessage(anonymousClass134.obtainMessage(309, getSendingUserId(), 0));
                            return;
                        }
                        if ("android.intent.action.USER_UNLOCKED".equals(action2)) {
                            AnonymousClass13 anonymousClass135 = this.this$0.mHandler;
                            anonymousClass135.sendMessage(anonymousClass135.obtainMessage(334, getSendingUserId(), 0));
                            return;
                        }
                        if ("android.intent.action.USER_STOPPED".equals(action2)) {
                            AnonymousClass13 anonymousClass136 = this.this$0.mHandler;
                            anonymousClass136.sendMessage(anonymousClass136.obtainMessage(340, intent.getIntExtra("android.intent.extra.user_handle", -1), 0));
                            return;
                        } else if ("android.intent.action.USER_REMOVED".equals(action2)) {
                            AnonymousClass13 anonymousClass137 = this.this$0.mHandler;
                            anonymousClass137.sendMessage(anonymousClass137.obtainMessage(341, intent.getIntExtra("android.intent.extra.user_handle", -1), 0));
                            return;
                        } else {
                            if ("android.nfc.action.REQUIRE_UNLOCK_FOR_NFC".equals(action2)) {
                                sendEmptyMessage(345);
                                return;
                            }
                            return;
                        }
                }
            }
        };
        final int i2 = 1;
        this.mBroadcastAllReceiver = new BroadcastReceiver(this) { // from class: com.android.keyguard.KeyguardUpdateMonitor.7
            public final /* synthetic */ KeyguardUpdateMonitor this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                int i22;
                switch (i2) {
                    case 0:
                        String action = intent.getAction();
                        this.this$0.mLogger.logBroadcastReceived(action);
                        if ("android.intent.action.TIME_TICK".equals(action) || "android.intent.action.TIME_SET".equals(action)) {
                            sendEmptyMessage(301);
                            return;
                        }
                        if ("android.intent.action.TIMEZONE_CHANGED".equals(action)) {
                            sendMessage(obtainMessage(339, intent.getStringExtra("time-zone")));
                            return;
                        }
                        if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
                            KeyguardUpdateMonitor keyguardUpdateMonitor = this.this$0;
                            sendMessage(keyguardUpdateMonitor.mHandler.obtainMessage(302, new BatteryStatus(intent, keyguardUpdateMonitor.mIncompatibleCharger)));
                            return;
                        }
                        if ("android.hardware.usb.action.USB_PORT_COMPLIANCE_CHANGED".equals(action)) {
                            this.this$0.mIncompatibleCharger = Utils.containsIncompatibleChargers(context2, "KeyguardUpdateMonitor");
                            boolean z = this.this$0.mIncompatibleCharger;
                            Intent registerReceiver = context2.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                            BatteryStatus batteryStatus = registerReceiver != null ? new BatteryStatus(registerReceiver, z) : null;
                            if (batteryStatus != null) {
                                AnonymousClass13 anonymousClass13 = this.this$0.mHandler;
                                anonymousClass13.sendMessage(anonymousClass13.obtainMessage(302, batteryStatus));
                                return;
                            }
                            return;
                        }
                        if (!"android.intent.action.SIM_STATE_CHANGED".equals(action)) {
                            if ("android.intent.action.PHONE_STATE".equals(action)) {
                                String stringExtra = intent.getStringExtra(WeatherData.STATE_KEY);
                                AnonymousClass13 anonymousClass132 = this.this$0.mHandler;
                                anonymousClass132.sendMessage(anonymousClass132.obtainMessage(306, stringExtra));
                                return;
                            }
                            if ("android.telephony.action.SERVICE_PROVIDERS_UPDATED".equals(action)) {
                                obtainMessage(347, intent).sendToTarget();
                                return;
                            }
                            if ("android.intent.action.AIRPLANE_MODE".equals(action)) {
                                sendEmptyMessage(329);
                                return;
                            }
                            if ("android.intent.action.SERVICE_STATE".equals(action)) {
                                ServiceState newFromBundle = ServiceState.newFromBundle(intent.getExtras());
                                int intExtra = intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_INDEX", -1);
                                this.this$0.mLogger.logServiceStateIntent(action, newFromBundle, intExtra);
                                AnonymousClass13 anonymousClass133 = this.this$0.mHandler;
                                anonymousClass133.sendMessage(anonymousClass133.obtainMessage(330, intExtra, 0, newFromBundle));
                                return;
                            }
                            if ("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED".equals(action)) {
                                sendEmptyMessage(328);
                                return;
                            } else {
                                if ("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED".equals(action)) {
                                    sendEmptyMessage(337);
                                    return;
                                }
                                return;
                            }
                        }
                        if (!"android.intent.action.SIM_STATE_CHANGED".equals(intent.getAction())) {
                            throw new IllegalArgumentException("only handles intent ACTION_SIM_STATE_CHANGED");
                        }
                        String stringExtra2 = intent.getStringExtra("ss");
                        int intExtra2 = intent.getIntExtra("android.telephony.extra.SLOT_INDEX", 0);
                        int intExtra3 = intent.getIntExtra("android.telephony.extra.SUBSCRIPTION_INDEX", -1);
                        if ("ABSENT".equals(stringExtra2)) {
                            i22 = 1;
                        } else if ("LOCKED".equals(stringExtra2)) {
                            String stringExtra3 = intent.getStringExtra("reason");
                            if ("PIN".equals(stringExtra3)) {
                                i22 = 2;
                            } else if ("PUK".equals(stringExtra3)) {
                                i22 = 3;
                            } else if ("NETWORK".equals(stringExtra3)) {
                                i22 = 4;
                            } else {
                                if ("PERM_DISABLED".equals(stringExtra3)) {
                                    i22 = 7;
                                }
                                i22 = 0;
                            }
                        } else if ("CARD_IO_ERROR".equals(stringExtra2)) {
                            i22 = 8;
                        } else if ("CARD_RESTRICTED".equals(stringExtra2)) {
                            i22 = 9;
                        } else if ("NOT_READY".equals(stringExtra2)) {
                            i22 = 6;
                        } else {
                            if ("READY".equals(stringExtra2) || "LOADED".equals(stringExtra2) || "IMSI".equals(stringExtra2)) {
                                i22 = 5;
                            }
                            i22 = 0;
                        }
                        if (intent.getBooleanExtra("rebroadcastOnUnlock", false)) {
                            if (i22 == 1) {
                                obtainMessage(338, Boolean.TRUE).sendToTarget();
                                return;
                            }
                            return;
                        } else {
                            this.this$0.mLogger.logSimStateFromIntent(action, intExtra2, intExtra3, intent.getStringExtra("ss"));
                            if (intExtra2 == -1) {
                                return;
                            }
                            obtainMessage(304, intExtra3, intExtra2, Integer.valueOf(i22)).sendToTarget();
                            return;
                        }
                    default:
                        String action2 = intent.getAction();
                        if ("android.app.action.NEXT_ALARM_CLOCK_CHANGED".equals(action2)) {
                            sendEmptyMessage(301);
                            return;
                        }
                        if ("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED".equals(action2)) {
                            AnonymousClass13 anonymousClass134 = this.this$0.mHandler;
                            anonymousClass134.sendMessage(anonymousClass134.obtainMessage(309, getSendingUserId(), 0));
                            return;
                        }
                        if ("android.intent.action.USER_UNLOCKED".equals(action2)) {
                            AnonymousClass13 anonymousClass135 = this.this$0.mHandler;
                            anonymousClass135.sendMessage(anonymousClass135.obtainMessage(334, getSendingUserId(), 0));
                            return;
                        }
                        if ("android.intent.action.USER_STOPPED".equals(action2)) {
                            AnonymousClass13 anonymousClass136 = this.this$0.mHandler;
                            anonymousClass136.sendMessage(anonymousClass136.obtainMessage(340, intent.getIntExtra("android.intent.extra.user_handle", -1), 0));
                            return;
                        } else if ("android.intent.action.USER_REMOVED".equals(action2)) {
                            AnonymousClass13 anonymousClass137 = this.this$0.mHandler;
                            anonymousClass137.sendMessage(anonymousClass137.obtainMessage(341, intent.getIntExtra("android.intent.extra.user_handle", -1), 0));
                            return;
                        } else {
                            if ("android.nfc.action.REQUIRE_UNLOCK_FOR_NFC".equals(action2)) {
                                sendEmptyMessage(345);
                                return;
                            }
                            return;
                        }
                }
            }
        };
        this.mFingerprintLockoutResetCallback = new FingerprintManager.LockoutResetCallback() { // from class: com.android.keyguard.KeyguardUpdateMonitor.9
            public final void onLockoutReset(int i3) {
                KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                int i4 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                keyguardUpdateMonitor.handleFingerprintLockoutReset(0);
            }
        };
        this.mFingerprintAuthenticationCallback = new FingerprintManager.AuthenticationCallback() { // from class: com.android.keyguard.KeyguardUpdateMonitor.10
            public final void onAuthenticationAcquired(int i3) {
                Trace.beginSection("KeyguardUpdateMonitor#onAuthenticationAcquired");
                KeyguardUpdateMonitor.this.mLogger.logFingerprintAcquired(i3);
                KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                Assert.isMainThread();
                for (int i4 = 0; i4 < keyguardUpdateMonitor.mCallbacks.size(); i4++) {
                    KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                    if (keyguardUpdateMonitorCallback != null) {
                        keyguardUpdateMonitorCallback.onBiometricAcquired(BiometricSourceType.FINGERPRINT, i3);
                    }
                }
                Trace.endSection();
            }

            @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
            public final void onAuthenticationError(int i3, CharSequence charSequence) {
                Trace.beginSection("KeyguardUpdateMonitor#onAuthenticationError");
                KeyguardUpdateMonitor.m754$$Nest$mhandleFingerprintError(KeyguardUpdateMonitor.this, i3, charSequence.toString());
                Trace.endSection();
            }

            @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
            public final void onAuthenticationFailed() {
                KeyguardUpdateMonitor.this.requestActiveUnlock(ActiveUnlockConfig.ActiveUnlockRequestOrigin.BIOMETRIC_FAIL, "fingerprintFailure-dismissKeyguard", true);
                KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                Assert.isMainThread();
                if (keyguardUpdateMonitor.mHandler.hasCallbacks(keyguardUpdateMonitor.mFpCancelNotReceived)) {
                    keyguardUpdateMonitor.mLogger.d("handleFingerprintAuthFailed() triggered while waiting for cancellation, removing watchdog");
                    keyguardUpdateMonitor.mHandler.removeCallbacks(keyguardUpdateMonitor.mFpCancelNotReceived);
                }
                keyguardUpdateMonitor.mLogger.d("handleFingerprintAuthFailed");
                for (int i3 = 0; i3 < keyguardUpdateMonitor.mCallbacks.size(); i3++) {
                    KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i3)).get();
                    if (keyguardUpdateMonitorCallback != null) {
                        keyguardUpdateMonitorCallback.onBiometricAuthFailed(BiometricSourceType.FINGERPRINT);
                    }
                }
                if (keyguardUpdateMonitor.mAuthController.isUdfpsSupported()) {
                    keyguardUpdateMonitor.handleFingerprintHelp(-1, keyguardUpdateMonitor.mContext.getString(R.string.font_family_display_4_material));
                } else {
                    keyguardUpdateMonitor.handleFingerprintHelp(-1, keyguardUpdateMonitor.mContext.getString(R.string.fingerprint_recalibrate_notification_content));
                }
            }

            @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
            public final void onAuthenticationHelp(int i3, CharSequence charSequence) {
                Trace.beginSection("KeyguardUpdateMonitor#onAuthenticationHelp");
                KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                String charSequence2 = charSequence.toString();
                int i4 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                keyguardUpdateMonitor.handleFingerprintHelp(i3, charSequence2);
                Trace.endSection();
            }

            @Override // android.hardware.fingerprint.FingerprintManager.AuthenticationCallback
            public final void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult authenticationResult) {
                Trace.beginSection("KeyguardUpdateMonitor#onAuthenticationSucceeded");
                KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                int userId = authenticationResult.getUserId();
                boolean isStrongBiometric = authenticationResult.isStrongBiometric();
                int i3 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                Trace.beginSection("KeyGuardUpdateMonitor#handlerFingerPrintAuthenticated");
                if (keyguardUpdateMonitor.mHandler.hasCallbacks(keyguardUpdateMonitor.mFpCancelNotReceived)) {
                    keyguardUpdateMonitor.mLogger.d("handleFingerprintAuthenticated() triggered while waiting for cancellation, removing watchdog");
                    keyguardUpdateMonitor.mHandler.removeCallbacks(keyguardUpdateMonitor.mFpCancelNotReceived);
                }
                try {
                    int selectedUserId = keyguardUpdateMonitor.mSelectedUserInteractor.getSelectedUserId();
                    if (selectedUserId != userId) {
                        keyguardUpdateMonitor.mLogger.logFingerprintAuthForWrongUser(userId);
                    } else {
                        if (!keyguardUpdateMonitor.isFingerprintDisabled(selectedUserId)) {
                            keyguardUpdateMonitor.onFingerprintAuthenticated(selectedUserId, isStrongBiometric);
                            keyguardUpdateMonitor.setFingerprintRunningState(0);
                            Trace.endSection();
                            Trace.endSection();
                        }
                        keyguardUpdateMonitor.mLogger.logFingerprintDisabledForUser(selectedUserId);
                    }
                    Trace.endSection();
                } finally {
                    keyguardUpdateMonitor.setFingerprintRunningState(0);
                }
            }

            public final void onUdfpsPointerDown(int i3) {
                KeyguardUpdateMonitor.this.mLogger.logUdfpsPointerDown(i3);
            }

            public final void onUdfpsPointerUp(int i3) {
                KeyguardUpdateMonitor.this.mLogger.logUdfpsPointerUp(i3);
            }
        };
        this.mFingerprintDetectionCallback = new FingerprintManager.FingerprintDetectionCallback() { // from class: com.android.keyguard.KeyguardUpdateMonitor.11
            public final void onDetectionError(int i3) {
                KeyguardUpdateMonitor.m754$$Nest$mhandleFingerprintError(KeyguardUpdateMonitor.this, i3, "");
            }

            public final void onFingerprintDetected(int i3, int i4, boolean z) {
                KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                if (keyguardUpdateMonitor.mHandler.hasCallbacks(keyguardUpdateMonitor.mFpCancelNotReceived)) {
                    KeyguardUpdateMonitor.this.mLogger.d("onFingerprintDetected() triggered while waiting for cancellation, removing watchdog");
                    KeyguardUpdateMonitor keyguardUpdateMonitor2 = KeyguardUpdateMonitor.this;
                    keyguardUpdateMonitor2.mHandler.removeCallbacks(keyguardUpdateMonitor2.mFpCancelNotReceived);
                }
                KeyguardUpdateMonitor keyguardUpdateMonitor3 = KeyguardUpdateMonitor.this;
                keyguardUpdateMonitor3.mFingerprintCancelSignal = null;
                keyguardUpdateMonitor3.setFingerprintRunningState(0);
                KeyguardUpdateMonitor.m753$$Nest$mhandleBiometricDetected(KeyguardUpdateMonitor.this, i4, BiometricSourceType.FINGERPRINT, z);
            }
        };
        this.mPostureCallback = new DevicePostureController.Callback() { // from class: com.android.keyguard.KeyguardUpdateMonitor.12
            @Override // com.android.systemui.statusbar.policy.DevicePostureController.Callback
            public final void onPostureChanged(int i3) {
                if (i3 == 3) {
                    KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                    keyguardUpdateMonitor.mLogger.d("Posture changed to open - attempting to request active unlock and run face auth");
                    SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = keyguardUpdateMonitor.mFaceAuthInteractor;
                    if (systemUIDeviceEntryFaceAuthInteractor.facePropertyRepository.supportedPostures.contains(DevicePosture.OPENED)) {
                        systemUIDeviceEntryFaceAuthInteractor.runFaceAuth(FaceAuthUiEvent.FACE_AUTH_UPDATED_POSTURE_CHANGED, true);
                    }
                    keyguardUpdateMonitor.requestActiveUnlockFromWakeReason(12, false);
                }
            }
        };
        this.mFingerprintSensorProperties = Collections.emptyList();
        this.mUserChangedCallback = new UserTracker.Callback() { // from class: com.android.keyguard.KeyguardUpdateMonitor.17
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i3, Context context2) {
                AnonymousClass13 anonymousClass13 = KeyguardUpdateMonitor.this.mHandler;
                anonymousClass13.sendMessage(anonymousClass13.obtainMessage(314, i3, 0));
            }

            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanging(int i3, Context context2, Runnable runnable) {
                AnonymousClass13 anonymousClass13 = KeyguardUpdateMonitor.this.mHandler;
                anonymousClass13.sendMessage(anonymousClass13.obtainMessage(310, i3, 0, runnable));
            }
        };
        this.mTaskStackListener = new TaskStackChangeListener() { // from class: com.android.keyguard.KeyguardUpdateMonitor.19
            @Override // com.android.systemui.shared.system.TaskStackChangeListener
            public final void onTaskStackChangedBackground() {
                ComponentName componentName;
                KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                try {
                    boolean z = true;
                    ActivityTaskManager.RootTaskInfo rootTaskInfo = keyguardUpdateMonitor.mActivityTaskManager.getRootTaskInfo(1, 1);
                    boolean z2 = keyguardUpdateMonitor.mAllowFingerprintOnCurrentOccludingActivity;
                    if (rootTaskInfo == null || (componentName = rootTaskInfo.topActivity) == null || TextUtils.isEmpty(componentName.getPackageName()) || !keyguardUpdateMonitor.mAllowFingerprintOnOccludingActivitiesFromPackage.contains(rootTaskInfo.topActivity.getPackageName()) || !rootTaskInfo.visible) {
                        z = false;
                    }
                    keyguardUpdateMonitor.mAllowFingerprintOnCurrentOccludingActivity = z;
                    if (z != z2) {
                        keyguardUpdateMonitor.mLogger.allowFingerprintOnCurrentOccludingActivityChanged(z);
                        keyguardUpdateMonitor.updateFingerprintListeningState(2);
                    }
                    ActivityTaskManager.RootTaskInfo rootTaskInfo2 = keyguardUpdateMonitor.mActivityTaskManager.getRootTaskInfo(0, 4);
                    if (rootTaskInfo2 == null) {
                        return;
                    }
                    keyguardUpdateMonitor.mLogger.logTaskStackChangedForAssistant(rootTaskInfo2.visible);
                    AnonymousClass13 anonymousClass13 = keyguardUpdateMonitor.mHandler;
                    anonymousClass13.sendMessage(anonymousClass13.obtainMessage(335, Boolean.valueOf(rootTaskInfo2.visible)));
                } catch (RemoteException e) {
                    keyguardUpdateMonitor.mLogger.logException(e, "unable to check task stack ");
                }
            }
        };
        this.mContext = context;
        this.mSubscriptionManager = subscriptionManager;
        this.mUserTracker = userTracker;
        this.mTelephonyListenerManager = telephonyListenerManager;
        this.mDeviceProvisioned = isDeviceProvisionedInSettingsDb();
        this.mStrongAuthTracker = new StrongAuthTracker(context);
        this.mBackgroundExecutor = executor;
        this.mMainExecutor = executor2;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mInteractionJankMonitor = interactionJankMonitor;
        this.mLatencyTracker = latencyTracker;
        this.mStatusBarStateController = statusBarStateController;
        statusBarStateController.addCallback(stateListener);
        this.mStatusBarState = statusBarStateController.getState();
        this.mLockPatternUtils = lockPatternUtils;
        this.mAuthController = authController;
        dumpManager.registerDumpable(this);
        this.mSensorPrivacyManager = sensorPrivacyManager;
        this.mActiveUnlockConfig = activeUnlockConfig;
        this.mLogger = keyguardUpdateMonitorLogger;
        this.mUiEventLogger = uiEventLogger;
        this.mSessionTrackerProvider = provider;
        this.mTrustManager = trustManager;
        this.mUserManager = userManager;
        this.mDreamManager = iDreamManager;
        this.mTelephonyManager = telephonyManager;
        this.mDevicePolicyManager = devicePolicyManager;
        this.mPackageManager = packageManager;
        this.mFpm = fingerprintManager;
        this.mBiometricManager = biometricManager;
        this.mConfigFaceAuthSupportedPosture = context.getResources().getInteger(com.android.wm.shell.R.integer.config_face_auth_supported_posture);
        this.mFaceWakeUpTriggersConfig = faceWakeUpTriggersConfig;
        this.mAllowFingerprintOnOccludingActivitiesFromPackage = (Set) Arrays.stream(context.getResources().getStringArray(com.android.wm.shell.R.array.config_fingerprint_listen_on_occluding_activity_packages)).collect(Collectors.toSet());
        this.mDevicePostureController = devicePostureController;
        this.mTaskStackChangeListeners = taskStackChangeListeners;
        this.mActivityTaskManager = iActivityTaskManager;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mFingerprintInteractiveToAuthProvider = (FingerprintInteractiveToAuthProviderGoogle) optional.orElse(null);
        this.mIsSystemUser = userManager.isSystemUser();
        ?? r1 = new Handler(looper) { // from class: com.android.keyguard.KeyguardUpdateMonitor.13
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i3 = message.what;
                KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                int i4 = 0;
                switch (i3) {
                    case 301:
                        int i5 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                            if (keyguardUpdateMonitorCallback != null) {
                                keyguardUpdateMonitorCallback.onTimeChanged();
                            }
                            i4++;
                        }
                        break;
                    case 302:
                        BatteryStatus batteryStatus = (BatteryStatus) message.obj;
                        int i6 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        BatteryStatus batteryStatus2 = keyguardUpdateMonitor.mBatteryStatus;
                        boolean isPluggedIn = BatteryStatus.isPluggedIn(batteryStatus.plugged);
                        boolean isPluggedIn2 = BatteryStatus.isPluggedIn(batteryStatus2.plugged);
                        boolean z = (isPluggedIn2 == isPluggedIn && !(isPluggedIn2 && isPluggedIn && batteryStatus2.status != batteryStatus.status) && batteryStatus2.level == batteryStatus.level && (!isPluggedIn || batteryStatus.maxChargingWattage == batteryStatus2.maxChargingWattage) && batteryStatus2.present == batteryStatus.present && batteryStatus2.incompatibleCharger.equals(batteryStatus.incompatibleCharger) && batteryStatus.chargingStatus == batteryStatus2.chargingStatus) ? false : true;
                        keyguardUpdateMonitor.mBatteryStatus = batteryStatus;
                        if (z) {
                            keyguardUpdateMonitor.mLogger.logHandleBatteryUpdate(batteryStatus);
                            while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                                KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback2 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                                if (keyguardUpdateMonitorCallback2 != null) {
                                    keyguardUpdateMonitorCallback2.onRefreshBatteryInfo(batteryStatus);
                                }
                                i4++;
                            }
                            break;
                        }
                        break;
                    case 303:
                    case 305:
                    case 307:
                    case 311:
                    case 313:
                    case 315:
                    case 316:
                    case 317:
                    case 323:
                    case 324:
                    case 325:
                    case 326:
                    case 327:
                    case 331:
                    case 343:
                    default:
                        super.handleMessage(message);
                        break;
                    case 304:
                        keyguardUpdateMonitor.handleSimStateChange(message.arg1, message.arg2, ((Integer) message.obj).intValue());
                        break;
                    case 306:
                        String str = (String) message.obj;
                        int i7 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        keyguardUpdateMonitor.mLogger.logPhoneStateChanged(str);
                        if (!TelephonyManager.EXTRA_STATE_IDLE.equals(str) && !TelephonyManager.EXTRA_STATE_OFFHOOK.equals(str)) {
                            TelephonyManager.EXTRA_STATE_RINGING.equals(str);
                        }
                        while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback3 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                            if (keyguardUpdateMonitorCallback3 != null) {
                                keyguardUpdateMonitorCallback3.onPhoneStateChanged();
                            }
                            i4++;
                        }
                        break;
                    case 308:
                        int i8 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback4 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                            if (keyguardUpdateMonitorCallback4 != null) {
                                keyguardUpdateMonitorCallback4.onDeviceProvisioned();
                            }
                            i4++;
                        }
                        if (keyguardUpdateMonitor.mDeviceProvisionedObserver != null) {
                            keyguardUpdateMonitor.mContext.getContentResolver().unregisterContentObserver(keyguardUpdateMonitor.mDeviceProvisionedObserver);
                            keyguardUpdateMonitor.mDeviceProvisionedObserver = null;
                            break;
                        }
                        break;
                    case 309:
                        int i9 = message.arg1;
                        int i10 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        keyguardUpdateMonitor.updateFingerprintListeningState(2);
                        keyguardUpdateMonitor.updateSecondaryLockscreenRequirement(i9);
                        while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback5 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                            if (keyguardUpdateMonitorCallback5 != null) {
                                keyguardUpdateMonitorCallback5.onDevicePolicyManagerStateChanged();
                            }
                            i4++;
                        }
                        break;
                    case 310:
                        keyguardUpdateMonitor.handleUserSwitching(message.arg1, (Runnable) message.obj);
                        break;
                    case 312:
                        keyguardUpdateMonitor.handleKeyguardReset();
                        break;
                    case 314:
                        keyguardUpdateMonitor.handleUserSwitchComplete(message.arg1);
                        break;
                    case 318:
                        int i11 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        keyguardUpdateMonitor.handleReportEmergencyCallAction();
                        break;
                    case 319:
                        Trace.beginSection("KeyguardUpdateMonitor#handler MSG_STARTED_WAKING_UP");
                        int i12 = message.arg1;
                        Trace.beginSection("KeyguardUpdateMonitor#handleStartedWakingUp");
                        Assert.isMainThread();
                        keyguardUpdateMonitor.updateFingerprintListeningState(2);
                        keyguardUpdateMonitor.requestActiveUnlockFromWakeReason(i12, true);
                        while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback6 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                            if (keyguardUpdateMonitorCallback6 != null) {
                                keyguardUpdateMonitorCallback6.onStartedWakingUp();
                            }
                            i4++;
                        }
                        Trace.endSection();
                        Trace.endSection();
                        break;
                    case 320:
                        int i13 = message.arg1;
                        Assert.isMainThread();
                        keyguardUpdateMonitor.mGoingToSleep = false;
                        while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback7 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                            if (keyguardUpdateMonitorCallback7 != null) {
                                keyguardUpdateMonitorCallback7.onFinishedGoingToSleep(i13);
                            }
                            i4++;
                        }
                        keyguardUpdateMonitor.updateFingerprintListeningState(2);
                        break;
                    case 321:
                        Assert.isMainThread();
                        keyguardUpdateMonitor.setForceIsDismissibleKeyguard(false);
                        keyguardUpdateMonitor.clearFingerprintRecognized(-10000);
                        for (int i14 = 0; i14 < keyguardUpdateMonitor.mCallbacks.size(); i14++) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback8 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i14)).get();
                            if (keyguardUpdateMonitorCallback8 != null) {
                                keyguardUpdateMonitorCallback8.onStartedGoingToSleep$1();
                            }
                        }
                        keyguardUpdateMonitor.mGoingToSleep = true;
                        keyguardUpdateMonitor.mAssistantVisible = false;
                        keyguardUpdateMonitor.mLogger.d("Started going to sleep, mGoingToSleep=true, mAssistantVisible=false");
                        keyguardUpdateMonitor.updateFingerprintListeningState(2);
                        break;
                    case 322:
                        int i15 = message.arg1;
                        int i16 = message.arg2;
                        int i17 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        boolean z2 = keyguardUpdateMonitor.mPrimaryBouncerIsOrWillBeShowing;
                        boolean z3 = keyguardUpdateMonitor.mPrimaryBouncerFullyShown;
                        boolean z4 = i15 == 1;
                        keyguardUpdateMonitor.mPrimaryBouncerIsOrWillBeShowing = z4;
                        boolean z5 = i16 == 1;
                        keyguardUpdateMonitor.mPrimaryBouncerFullyShown = z5;
                        keyguardUpdateMonitor.mLogger.logPrimaryKeyguardBouncerChanged(z4, z5);
                        if (keyguardUpdateMonitor.mPrimaryBouncerFullyShown) {
                            keyguardUpdateMonitor.mSecureCameraLaunched = false;
                        } else {
                            keyguardUpdateMonitor.mCredentialAttempted = false;
                        }
                        if (z2 != keyguardUpdateMonitor.mPrimaryBouncerIsOrWillBeShowing) {
                            for (int i18 = 0; i18 < keyguardUpdateMonitor.mCallbacks.size(); i18++) {
                                KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback9 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i18)).get();
                                if (keyguardUpdateMonitorCallback9 != null) {
                                    keyguardUpdateMonitorCallback9.onKeyguardBouncerStateChanged(keyguardUpdateMonitor.mPrimaryBouncerIsOrWillBeShowing);
                                }
                            }
                            keyguardUpdateMonitor.updateFingerprintListeningState(2);
                        }
                        boolean z6 = keyguardUpdateMonitor.mPrimaryBouncerFullyShown;
                        if (z3 != z6) {
                            if (z6) {
                                keyguardUpdateMonitor.requestActiveUnlock(ActiveUnlockConfig.ActiveUnlockRequestOrigin.UNLOCK_INTENT, "bouncerFullyShown");
                            }
                            while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                                KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback10 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                                if (keyguardUpdateMonitorCallback10 != null) {
                                    keyguardUpdateMonitorCallback10.onKeyguardBouncerFullyShowingChanged(keyguardUpdateMonitor.mPrimaryBouncerFullyShown);
                                }
                                i4++;
                            }
                            break;
                        }
                        break;
                    case 328:
                        int i19 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        keyguardUpdateMonitor.mLogger.v("onSubscriptionInfoChanged()");
                        List subscriptionInfo = keyguardUpdateMonitor.getSubscriptionInfo(true);
                        if (subscriptionInfo.isEmpty()) {
                            keyguardUpdateMonitor.mLogger.v("onSubscriptionInfoChanged: list is null");
                        } else {
                            Iterator it = subscriptionInfo.iterator();
                            while (it.hasNext()) {
                                keyguardUpdateMonitor.mLogger.logSubInfo((SubscriptionInfo) it.next());
                            }
                        }
                        ArrayList arrayList = new ArrayList();
                        HashSet hashSet = new HashSet();
                        int i20 = 0;
                        while (true) {
                            ArrayList arrayList2 = (ArrayList) subscriptionInfo;
                            if (i20 >= arrayList2.size()) {
                                Iterator it2 = keyguardUpdateMonitor.mSimDatas.entrySet().iterator();
                                while (it2.hasNext()) {
                                    Map.Entry entry = (Map.Entry) it2.next();
                                    if (!hashSet.contains(entry.getKey())) {
                                        keyguardUpdateMonitor.mLogger.logInvalidSubId(((Integer) entry.getKey()).intValue());
                                        it2.remove();
                                        SimData simData = (SimData) entry.getValue();
                                        for (int i21 = 0; i21 < keyguardUpdateMonitor.mCallbacks.size(); i21++) {
                                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback11 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i21)).get();
                                            if (keyguardUpdateMonitorCallback11 != null) {
                                                keyguardUpdateMonitorCallback11.onSimStateChanged(simData.subId, simData.slotId, simData.simState);
                                            }
                                        }
                                    }
                                }
                                for (int i22 = 0; i22 < arrayList.size(); i22++) {
                                    SimData simData2 = (SimData) keyguardUpdateMonitor.mSimDatas.get(Integer.valueOf(((SubscriptionInfo) arrayList.get(i22)).getSubscriptionId()));
                                    for (int i23 = 0; i23 < keyguardUpdateMonitor.mCallbacks.size(); i23++) {
                                        KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback12 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i23)).get();
                                        if (keyguardUpdateMonitorCallback12 != null) {
                                            keyguardUpdateMonitorCallback12.onSimStateChanged(simData2.subId, simData2.slotId, simData2.simState);
                                        }
                                    }
                                }
                                keyguardUpdateMonitor.callbacksRefreshCarrierInfo();
                                break;
                            } else {
                                SubscriptionInfo subscriptionInfo2 = (SubscriptionInfo) arrayList2.get(i20);
                                hashSet.add(Integer.valueOf(subscriptionInfo2.getSubscriptionId()));
                                if (keyguardUpdateMonitor.refreshSimState(subscriptionInfo2.getSubscriptionId(), subscriptionInfo2.getSimSlotIndex())) {
                                    arrayList.add(subscriptionInfo2);
                                }
                                i20++;
                            }
                        }
                    case 329:
                        int i24 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        keyguardUpdateMonitor.callbacksRefreshCarrierInfo();
                        break;
                    case 330:
                        keyguardUpdateMonitor.handleServiceStateChange(message.arg1, (ServiceState) message.obj);
                        break;
                    case 332:
                        Trace.beginSection("KeyguardUpdateMonitor#handler MSG_SCREEN_TURNED_OFF");
                        int i25 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        keyguardUpdateMonitor.mHardwareFingerprintUnavailableRetryCount = 0;
                        Trace.endSection();
                        break;
                    case 333:
                        int i26 = message.arg1;
                        int i27 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        keyguardUpdateMonitor.mIsDreaming = i26 == 1;
                        while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback13 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                            if (keyguardUpdateMonitorCallback13 != null) {
                                keyguardUpdateMonitorCallback13.onDreamingStateChanged(keyguardUpdateMonitor.mIsDreaming);
                            }
                            i4++;
                        }
                        keyguardUpdateMonitor.updateFingerprintListeningState(2);
                        break;
                    case 334:
                        int i28 = message.arg1;
                        int i29 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        keyguardUpdateMonitor.mLogger.logUserUnlocked(i28);
                        keyguardUpdateMonitor.mUserIsUnlocked.put(i28, true);
                        keyguardUpdateMonitor.mNeedsSlowUnlockTransition = keyguardUpdateMonitor.resolveNeedsSlowUnlockTransition();
                        while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback14 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                            if (keyguardUpdateMonitorCallback14 != null) {
                                keyguardUpdateMonitorCallback14.onUserUnlocked();
                            }
                            i4++;
                        }
                        break;
                    case 335:
                        keyguardUpdateMonitor.setAssistantVisible(((Boolean) message.obj).booleanValue());
                        break;
                    case 336:
                        int i30 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        keyguardUpdateMonitor.updateFingerprintListeningState(2);
                        break;
                    case 337:
                        int i31 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        boolean isLogoutEnabled = keyguardUpdateMonitor.mDevicePolicyManager.isLogoutEnabled();
                        if (keyguardUpdateMonitor.mLogoutEnabled != isLogoutEnabled) {
                            keyguardUpdateMonitor.mLogoutEnabled = isLogoutEnabled;
                            while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                                KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback15 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                                if (keyguardUpdateMonitorCallback15 != null) {
                                    keyguardUpdateMonitorCallback15.onLogoutEnabledChanged();
                                }
                                i4++;
                            }
                            break;
                        }
                        break;
                    case 338:
                        keyguardUpdateMonitor.updateTelephonyCapable(((Boolean) message.obj).booleanValue());
                        break;
                    case 339:
                        String str2 = (String) message.obj;
                        int i32 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        keyguardUpdateMonitor.mLogger.d("handleTimeZoneUpdate");
                        while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback16 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                            if (keyguardUpdateMonitorCallback16 != null) {
                                keyguardUpdateMonitorCallback16.onTimeZoneChanged(TimeZone.getTimeZone(str2));
                                keyguardUpdateMonitorCallback16.onTimeChanged();
                            }
                            i4++;
                        }
                        break;
                    case 340:
                        int i33 = message.arg1;
                        int i34 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        boolean isUserUnlocked = keyguardUpdateMonitor.mUserManager.isUserUnlocked(i33);
                        keyguardUpdateMonitor.mLogger.logUserStopped(i33, isUserUnlocked);
                        keyguardUpdateMonitor.mUserIsUnlocked.put(i33, isUserUnlocked);
                        break;
                    case 341:
                        keyguardUpdateMonitor.handleUserRemoved(message.arg1);
                        break;
                    case 342:
                        boolean booleanValue = ((Boolean) message.obj).booleanValue();
                        int i35 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        keyguardUpdateMonitor.setKeyguardGoingAway(booleanValue);
                        break;
                    case 344:
                        String str3 = (String) message.obj;
                        int i36 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        keyguardUpdateMonitor.mLogger.logTimeFormatChanged(str3);
                        while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback17 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                            if (keyguardUpdateMonitorCallback17 != null) {
                                keyguardUpdateMonitorCallback17.onTimeFormatChanged();
                            }
                            i4++;
                        }
                        break;
                    case 345:
                        int i37 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback18 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                            if (keyguardUpdateMonitorCallback18 != null) {
                                keyguardUpdateMonitorCallback18.onRequireUnlockForNfc();
                            }
                            i4++;
                        }
                        break;
                    case 346:
                        int i38 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                        Assert.isMainThread();
                        while (i4 < keyguardUpdateMonitor.mCallbacks.size()) {
                            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback19 = (KeyguardUpdateMonitorCallback) ((WeakReference) keyguardUpdateMonitor.mCallbacks.get(i4)).get();
                            if (keyguardUpdateMonitorCallback19 != null) {
                                keyguardUpdateMonitorCallback19.onKeyguardDismissAnimationFinished();
                            }
                            i4++;
                        }
                        break;
                    case 347:
                        keyguardUpdateMonitor.mLogger.logServiceProvidersUpdated((Intent) message.obj);
                        keyguardUpdateMonitor.callbacksRefreshCarrierInfo();
                        break;
                    case 348:
                        KeyguardUpdateMonitor.m755$$Nest$mnotifyAboutEnrollmentChange(keyguardUpdateMonitor, message.arg1);
                        break;
                }
            }
        };
        this.mHandler = r1;
        this.mTimeFormatChangeObserver = new AnonymousClass14(this, r1, i);
    }

    public final void callbacksRefreshCarrierInfo() {
        Assert.isMainThread();
        for (int i = 0; i < this.mCallbacks.size(); i++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onRefreshCarrierInfo();
            }
        }
    }

    public final void clearFingerprintRecognized(int i) {
        Assert.isMainThread();
        this.mUserFingerprintAuthenticated.clear();
        this.mTrustManager.clearAllBiometricRecognized(BiometricSourceType.FINGERPRINT, i);
        this.mLogger.d("clearFingerprintRecognized");
        for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onFingerprintsCleared();
            }
        }
    }

    @Override // com.android.systemui.Dumpable
    @NeverCompile
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "KeyguardUpdateMonitor state:", "  forceIsDismissible="), this.mForceIsDismissible, printWriter, "  forceIsDismissibleIsKeepingDeviceUnlocked=");
        m.append(forceIsDismissibleIsKeepingDeviceUnlocked());
        printWriter.println(m.toString());
        printWriter.println("  getUserHasTrust()=" + getUserHasTrust(this.mSelectedUserInteractor.getSelectedUserId()));
        printWriter.println("  getUserUnlockedWithBiometric()=" + getUserUnlockedWithBiometric(this.mSelectedUserInteractor.getSelectedUserId()));
        printWriter.println("  SIM States:");
        Iterator it = this.mSimDatas.values().iterator();
        while (it.hasNext()) {
            printWriter.println("    " + ((SimData) it.next()).toString());
        }
        printWriter.println("  Subs:");
        if (this.mSubscriptionInfo != null) {
            for (int i = 0; i < this.mSubscriptionInfo.size(); i++) {
                printWriter.println("    " + this.mSubscriptionInfo.get(i));
            }
        }
        printWriter.println("  Current active data subId=" + this.mActiveMobileDataSubscription);
        printWriter.println("  Service states:");
        for (Integer num : this.mServiceStates.keySet()) {
            StringBuilder m2 = MutableObjectList$$ExternalSyntheticOutline0.m("    ", "=", num.intValue());
            m2.append(this.mServiceStates.get(num));
            printWriter.println(m2.toString());
        }
        if (isFingerprintSupported()) {
            int selectedUserId = this.mSelectedUserInteractor.getSelectedUserId();
            int strongAuthForUser = this.mStrongAuthTracker.getStrongAuthForUser(selectedUserId);
            BiometricAuthenticated biometricAuthenticated = (BiometricAuthenticated) this.mUserFingerprintAuthenticated.get(selectedUserId);
            printWriter.println("  Fingerprint state (user=" + selectedUserId + ")");
            StringBuilder sb = new StringBuilder("    isFingerprintClass3=");
            sb.append(isFingerprintClass3());
            printWriter.println(sb.toString());
            StringBuilder m3 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("    areAllFpAuthenticatorsRegistered="), this.mAuthController.mAllFingerprintAuthenticatorsRegistered, printWriter, "    allowed="), biometricAuthenticated != null && isUnlockingWithBiometricAllowed(biometricAuthenticated.mIsStrongBiometric), printWriter, "    auth'd="), biometricAuthenticated != null, printWriter, "    authSinceBoot=");
            m3.append(this.mStrongAuthTracker.hasUserAuthenticatedSinceBoot());
            printWriter.println(m3.toString());
            printWriter.println("    disabled(DPM)=" + isFingerprintDisabled(selectedUserId));
            printWriter.println("    possible=" + isUnlockWithFingerprintPossible(selectedUserId));
            printWriter.println("    listening: actual=" + this.mFingerprintRunningState + " expected=" + (shouldListenForFingerprint(isUdfpsEnrolled()) ? 1 : 0));
            StringBuilder sb2 = new StringBuilder("    strongAuthFlags=");
            sb2.append(Integer.toHexString(strongAuthForUser));
            printWriter.println(sb2.toString());
            printWriter.println("    trustManaged=" + getUserTrustIsManaged(selectedUserId));
            StringBuilder m4 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("    mFingerprintLockedOut="), this.mFingerprintLockedOut, printWriter, "    mFingerprintLockedOutPermanent="), this.mFingerprintLockedOutPermanent, printWriter, "    enabledByUser=");
            m4.append(this.mBiometricEnabledForUser.get(selectedUserId));
            printWriter.println(m4.toString());
            StringBuilder m5 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("    mKeyguardOccluded="), this.mKeyguardOccluded, printWriter, "    mIsDreaming="), this.mIsDreaming, printWriter, "    mFingerprintListenOnOccludingActivitiesFromPackage=");
            m5.append(this.mAllowFingerprintOnOccludingActivitiesFromPackage);
            printWriter.println(m5.toString());
            if (this.mAuthController.isUdfpsSupported()) {
                printWriter.println("        udfpsEnrolled=" + isUdfpsEnrolled());
                printWriter.println("        shouldListenForUdfps=" + shouldListenForFingerprint(true));
                StringBuilder m6 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("        isPrimaryBouncerShowingOrWillBeShowing="), this.mPrimaryBouncerIsOrWillBeShowing, printWriter, "        mStatusBarState=");
                m6.append(StatusBarState.toString(this.mStatusBarState));
                printWriter.println(m6.toString());
                KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("        isAlternateBouncerShowing="), this.mAlternateBouncerShowing, printWriter);
            } else {
                List list = this.mAuthController.mSidefpsProps;
                if (list != null && !list.isEmpty()) {
                    StringBuilder sb3 = new StringBuilder("        sfpsEnrolled=");
                    AuthController authController = this.mAuthController;
                    StringBuilder m7 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(sb3, authController.mSidefpsProps == null ? false : authController.mSfpsEnrolledForUser.get(this.mSelectedUserInteractor.getSelectedUserId()), printWriter, "        shouldListenForSfps=");
                    m7.append(shouldListenForFingerprint(false));
                    printWriter.println(m7.toString());
                }
            }
            new DumpsysTableLogger("KeyguardFingerprintListen", KeyguardFingerprintListenModel.TABLE_HEADERS, SequencesKt.toList(new TransformingSequence(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(this.mFingerprintListenBuffer.buffer), new Function1() { // from class: com.android.keyguard.KeyguardFingerprintListenModel$Buffer$toList$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return (List) ((KeyguardFingerprintListenModel) obj).asStringList$delegate.getValue();
                }
            }))).printTableData(printWriter);
        } else if (this.mFpm != null && this.mFingerprintSensorProperties.isEmpty()) {
            printWriter.println("  Fingerprint state (user=" + this.mSelectedUserInteractor.getSelectedUserId() + ")");
            StringBuilder sb4 = new StringBuilder("    mFingerprintSensorProperties.isEmpty=");
            sb4.append(this.mFingerprintSensorProperties.isEmpty());
            printWriter.println(sb4.toString());
            printWriter.println("    mFpm.isHardwareDetected=" + this.mFpm.isHardwareDetected());
            new DumpsysTableLogger("KeyguardFingerprintListen", KeyguardFingerprintListenModel.TABLE_HEADERS, SequencesKt.toList(new TransformingSequence(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(this.mFingerprintListenBuffer.buffer), new Function1() { // from class: com.android.keyguard.KeyguardFingerprintListenModel$Buffer$toList$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return (List) ((KeyguardFingerprintListenModel) obj).asStringList$delegate.getValue();
                }
            }))).printTableData(printWriter);
        }
        int selectedUserId2 = this.mSelectedUserInteractor.getSelectedUserId();
        int strongAuthForUser2 = this.mStrongAuthTracker.getStrongAuthForUser(selectedUserId2);
        printWriter.println("    authSinceBoot=" + this.mStrongAuthTracker.hasUserAuthenticatedSinceBoot());
        printWriter.println("    strongAuthFlags=" + Integer.toHexString(strongAuthForUser2));
        printWriter.println("ActiveUnlockRunning=" + this.mTrustManager.isActiveUnlockRunning(this.mSelectedUserInteractor.getSelectedUserId()));
        printWriter.println("userUnlockedCache[userid=" + selectedUserId2 + "]=" + this.mUserIsUnlocked.get(selectedUserId2));
        printWriter.println("actualUserUnlocked[userid=" + selectedUserId2 + "]=" + this.mUserManager.isUserUnlocked(selectedUserId2));
        new DumpsysTableLogger("KeyguardActiveUnlockTriggers", KeyguardActiveUnlockModel.TABLE_HEADERS, SequencesKt.toList(new TransformingSequence(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(this.mActiveUnlockTriggerBuffer.buffer), new Function1() { // from class: com.android.keyguard.KeyguardActiveUnlockModel$Buffer$toList$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return (List) ((KeyguardActiveUnlockModel) obj).asStringList$delegate.getValue();
            }
        }))).printTableData(printWriter);
    }

    public final boolean forceIsDismissibleIsKeepingDeviceUnlocked() {
        return this.mFoldGracePeriodProvider.isEnabled() && this.mForceIsDismissible && isUnlockingWithBiometricAllowed(false);
    }

    public final List getFilteredSubscriptionInfo() {
        List subscriptionInfo = getSubscriptionInfo(false);
        ArrayList arrayList = (ArrayList) subscriptionInfo;
        if (arrayList.size() == 2) {
            SubscriptionInfo subscriptionInfo2 = (SubscriptionInfo) arrayList.get(0);
            SubscriptionInfo subscriptionInfo3 = (SubscriptionInfo) arrayList.get(1);
            if (subscriptionInfo2.getGroupUuid() != null && subscriptionInfo2.getGroupUuid().equals(subscriptionInfo3.getGroupUuid())) {
                if (!subscriptionInfo2.isOpportunistic() && !subscriptionInfo3.isOpportunistic()) {
                    return subscriptionInfo;
                }
                if (CarrierConfigManager.getDefaultConfig().getBoolean("always_show_primary_signal_bar_in_opportunistic_network_boolean")) {
                    if (!subscriptionInfo2.isOpportunistic()) {
                        subscriptionInfo2 = subscriptionInfo3;
                    }
                    subscriptionInfo.remove(subscriptionInfo2);
                } else {
                    if (subscriptionInfo2.getSubscriptionId() == this.mActiveMobileDataSubscription) {
                        subscriptionInfo2 = subscriptionInfo3;
                    }
                    subscriptionInfo.remove(subscriptionInfo2);
                }
            }
        }
        return subscriptionInfo;
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    public final boolean getIsFaceAuthenticated() {
        SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = this.mFaceAuthInteractor;
        return systemUIDeviceEntryFaceAuthInteractor != null && ((Boolean) systemUIDeviceEntryFaceAuthInteractor.isAuthenticated.getValue()).booleanValue();
    }

    public final int getNextSubIdForState(int i) {
        int i2 = 0;
        List subscriptionInfo = getSubscriptionInfo(false);
        int i3 = -1;
        int i4 = Integer.MAX_VALUE;
        while (true) {
            ArrayList arrayList = (ArrayList) subscriptionInfo;
            if (i2 >= arrayList.size()) {
                return i3;
            }
            int subscriptionId = ((SubscriptionInfo) arrayList.get(i2)).getSubscriptionId();
            int slotId = getSlotId(subscriptionId);
            if (i == getSimState(subscriptionId) && i4 > slotId) {
                i3 = subscriptionId;
                i4 = slotId;
            }
            i2++;
        }
    }

    public final int getSimState(int i) {
        if (this.mSimDatas.containsKey(Integer.valueOf(i))) {
            return ((SimData) this.mSimDatas.get(Integer.valueOf(i))).simState;
        }
        return 0;
    }

    public final int getSlotId(int i) {
        if (!this.mSimDatas.containsKey(Integer.valueOf(i))) {
            refreshSimState(i, SubscriptionManager.getSlotIndex(i));
        }
        SimData simData = (SimData) this.mSimDatas.get(Integer.valueOf(i));
        if (simData != null) {
            return simData.slotId;
        }
        return -1;
    }

    public final List getSubscriptionInfo(boolean z) {
        if (this.mSubscriptionInfo == null || z) {
            this.mSubscriptionInfo = this.mSubscriptionManager.getCompleteActiveSubscriptionInfoList().stream().filter(new KeyguardUpdateMonitor$$ExternalSyntheticLambda4()).toList();
        }
        return new ArrayList(this.mSubscriptionInfo);
    }

    public final SubscriptionInfo getSubscriptionInfoForSubId(int i) {
        int i2 = 0;
        List subscriptionInfo = getSubscriptionInfo(false);
        while (true) {
            ArrayList arrayList = (ArrayList) subscriptionInfo;
            if (i2 >= arrayList.size()) {
                return null;
            }
            SubscriptionInfo subscriptionInfo2 = (SubscriptionInfo) arrayList.get(i2);
            if (i == subscriptionInfo2.getSubscriptionId()) {
                return subscriptionInfo2;
            }
            i2++;
        }
    }

    public final boolean getUserCanSkipBouncer(int i) {
        return getUserHasTrust(i) || getUserUnlockedWithBiometric(i) || forceIsDismissibleIsKeepingDeviceUnlocked();
    }

    public final boolean getUserHasTrust(int i) {
        return !isSimPinSecure() && this.mUserHasTrust.get(i) && isUnlockingWithBiometricAllowed(true);
    }

    public final boolean getUserTrustIsManaged(int i) {
        return this.mUserTrustIsManaged.get(i) && !isSimPinSecure();
    }

    public final boolean getUserUnlockedWithBiometric(int i) {
        BiometricAuthenticated biometricAuthenticated = (BiometricAuthenticated) this.mUserFingerprintAuthenticated.get(i);
        return (biometricAuthenticated != null && isUnlockingWithBiometricAllowed(biometricAuthenticated.mIsStrongBiometric)) || (isCurrentUserUnlockedWithFace() && isUnlockingWithBiometricAllowed(BiometricSourceType.FACE));
    }

    public final boolean getUserUnlockedWithBiometricAndIsBypassing(int i) {
        BiometricAuthenticated biometricAuthenticated = (BiometricAuthenticated) this.mUserFingerprintAuthenticated.get(i);
        return (biometricAuthenticated != null && isUnlockingWithBiometricAllowed(biometricAuthenticated.mIsStrongBiometric)) || (isCurrentUserUnlockedWithFace() && this.mKeyguardBypassController.canBypass());
    }

    public final void handleFaceHelp(int i, String str) {
        Assert.isMainThread();
        for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onBiometricHelp(i, str, BiometricSourceType.FACE);
            }
        }
    }

    public final void handleFingerprintHelp(int i, String str) {
        Assert.isMainThread();
        for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onBiometricHelp(i, str, BiometricSourceType.FINGERPRINT);
            }
        }
    }

    public final void handleFingerprintLockoutReset(int i) {
        this.mLogger.logFingerprintLockoutReset(i);
        boolean z = this.mFingerprintLockedOut;
        boolean z2 = this.mFingerprintLockedOutPermanent;
        boolean z3 = i == 1 || i == 2;
        this.mFingerprintLockedOut = z3;
        boolean z4 = i == 2;
        this.mFingerprintLockedOutPermanent = z4;
        boolean z5 = (z3 == z && z4 == z2) ? false : true;
        if (isUdfpsEnrolled()) {
            postDelayed(new KeyguardUpdateMonitor$$ExternalSyntheticLambda3(this, 1), 600);
        } else {
            if (z && !this.mFingerprintLockedOut) {
                this.mLogger.d("temporaryLockoutReset - stopListeningForFingerprint() to stop detectFingerprint");
                stopListeningForFingerprint();
            }
            updateFingerprintListeningState(2);
        }
        if (z5) {
            notifyLockedOutStateChanged(BiometricSourceType.FINGERPRINT);
        }
    }

    public void handleKeyguardReset() {
        this.mLogger.d("handleKeyguardReset");
        updateFingerprintListeningState(2);
        this.mNeedsSlowUnlockTransition = resolveNeedsSlowUnlockTransition();
    }

    public final void handleReportEmergencyCallAction() {
        Assert.isMainThread();
        for (int i = 0; i < this.mCallbacks.size(); i++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onEmergencyCallAction();
            }
        }
    }

    public void handleServiceStateChange(int i, ServiceState serviceState) {
        this.mLogger.logServiceStateChange(i, serviceState);
        if (SubscriptionManager.isValidSubscriptionId(i)) {
            updateTelephonyCapable(true);
            this.mServiceStates.put(Integer.valueOf(i), serviceState);
            callbacksRefreshCarrierInfo();
        } else {
            KeyguardUpdateMonitorLogger keyguardUpdateMonitorLogger = this.mLogger;
            keyguardUpdateMonitorLogger.getClass();
            keyguardUpdateMonitorLogger.logBuffer.log("KeyguardUpdateMonitorLog", LogLevel.WARNING, "invalid subId in handleServiceStateChange()", null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleSimStateChange(int r8, int r9, int r10) {
        /*
            r7 = this;
            com.android.systemui.util.Assert.isMainThread()
            com.android.keyguard.logging.KeyguardUpdateMonitorLogger r0 = r7.mLogger
            r0.logSimState(r8, r9, r10)
            boolean r0 = android.telephony.SubscriptionManager.isValidSubscriptionId(r8)
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L4c
            com.android.keyguard.logging.KeyguardUpdateMonitorLogger r0 = r7.mLogger
            r0.getClass()
            com.android.systemui.log.core.LogLevel r3 = com.android.systemui.log.core.LogLevel.WARNING
            java.lang.String r4 = "KeyguardUpdateMonitorLog"
            r5 = 0
            com.android.systemui.log.LogBuffer r0 = r0.logBuffer
            java.lang.String r6 = "invalid subId in handleSimStateChange()"
            r0.log(r4, r3, r6, r5)
            if (r10 != r1) goto L45
            r7.updateTelephonyCapable(r1)
            java.util.HashMap r0 = r7.mSimDatas
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        L30:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L43
            java.lang.Object r3 = r0.next()
            com.android.keyguard.KeyguardUpdateMonitor$SimData r3 = (com.android.keyguard.KeyguardUpdateMonitor.SimData) r3
            int r4 = r3.slotId
            if (r4 != r9) goto L30
            r3.simState = r1
            goto L30
        L43:
            r0 = r1
            goto L4d
        L45:
            r0 = 8
            if (r10 != r0) goto L4c
            r7.updateTelephonyCapable(r1)
        L4c:
            r0 = r2
        L4d:
            java.util.List r3 = com.android.keyguard.KeyguardUpdateMonitor.ABSENT_SIM_STATE_LIST
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            boolean r3 = r3.contains(r4)
            r0 = r0 | r3
            java.util.HashMap r3 = r7.mSimDatas
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)
            java.lang.Object r3 = r3.get(r4)
            com.android.keyguard.KeyguardUpdateMonitor$SimData r3 = (com.android.keyguard.KeyguardUpdateMonitor.SimData) r3
            if (r3 != 0) goto L75
            com.android.keyguard.KeyguardUpdateMonitor$SimData r3 = new com.android.keyguard.KeyguardUpdateMonitor$SimData
            r3.<init>(r10, r9, r8)
            java.util.HashMap r4 = r7.mSimDatas
            java.lang.Integer r5 = java.lang.Integer.valueOf(r8)
            r4.put(r5, r3)
            goto L89
        L75:
            int r4 = r3.simState
            if (r4 != r10) goto L83
            int r4 = r3.subId
            if (r4 != r8) goto L83
            int r4 = r3.slotId
            if (r4 == r9) goto L82
            goto L83
        L82:
            r1 = r2
        L83:
            r3.simState = r10
            r3.subId = r8
            r3.slotId = r9
        L89:
            if (r1 != 0) goto L8d
            if (r0 == 0) goto Lab
        L8d:
            java.util.ArrayList r0 = r7.mCallbacks
            int r0 = r0.size()
            if (r2 >= r0) goto Lab
            java.util.ArrayList r0 = r7.mCallbacks
            java.lang.Object r0 = r0.get(r2)
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            java.lang.Object r0 = r0.get()
            com.android.keyguard.KeyguardUpdateMonitorCallback r0 = (com.android.keyguard.KeyguardUpdateMonitorCallback) r0
            if (r0 == 0) goto La8
            r0.onSimStateChanged(r8, r9, r10)
        La8:
            int r2 = r2 + 1
            goto L8d
        Lab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.keyguard.KeyguardUpdateMonitor.handleSimStateChange(int, int, int):void");
    }

    public void handleUserRemoved(int i) {
        Assert.isMainThread();
        this.mLogger.logUserRemoved(i);
        this.mUserIsUnlocked.delete(i);
        this.mUserTrustIsUsuallyManaged.delete(i);
    }

    public void handleUserSwitchComplete(int i) {
        this.mLogger.logUserSwitchComplete(i, "from UserTracker");
        Assert.isMainThread();
        for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onUserSwitchComplete(i);
            }
        }
        if (isFingerprintSupported()) {
            stopListeningForFingerprint();
            handleFingerprintLockoutReset(this.mFpm.getLockoutModeForUser(((FingerprintSensorPropertiesInternal) this.mFingerprintSensorProperties.get(0)).sensorId, i));
        }
        this.mInteractionJankMonitor.end(37);
        this.mLatencyTracker.onActionEnd(12);
    }

    public void handleUserSwitching(int i, Runnable runnable) {
        this.mLogger.logUserSwitching(i, "from UserTracker");
        Assert.isMainThread();
        setForceIsDismissibleKeyguard(false);
        clearFingerprintRecognized(-10000);
        boolean isTrustUsuallyManaged = this.mTrustManager.isTrustUsuallyManaged(i);
        this.mLogger.logTrustUsuallyManagedUpdated(i, "userSwitching", this.mUserTrustIsUsuallyManaged.get(i), isTrustUsuallyManaged);
        this.mUserTrustIsUsuallyManaged.put(i, isTrustUsuallyManaged);
        for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onUserSwitching(i);
            }
        }
        runnable.run();
    }

    public final boolean isCurrentUserUnlockedWithFace() {
        SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = this.mFaceAuthInteractor;
        return systemUIDeviceEntryFaceAuthInteractor != null && ((Boolean) systemUIDeviceEntryFaceAuthInteractor.isAuthenticated.getValue()).booleanValue();
    }

    public final boolean isDeviceProvisionedInSettingsDb() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "device_provisioned", 0) != 0;
    }

    public final boolean isEncryptedOrLockdown(int i) {
        int strongAuthForUser = this.mStrongAuthTracker.getStrongAuthForUser(i);
        return (strongAuthForUser & 1) != 0 || (((strongAuthForUser & 2) != 0) || (strongAuthForUser & 32) != 0);
    }

    public final boolean isFaceDetectionRunning() {
        SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = this.mFaceAuthInteractor;
        return systemUIDeviceEntryFaceAuthInteractor != null && systemUIDeviceEntryFaceAuthInteractor.isRunning();
    }

    public final boolean isFaceEnabledAndEnrolled() {
        SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = this.mFaceAuthInteractor;
        return systemUIDeviceEntryFaceAuthInteractor != null && systemUIDeviceEntryFaceAuthInteractor.isFaceAuthEnabledAndEnrolled();
    }

    public boolean isFingerprintClass3() {
        return isFingerprintSupported() && ((SensorPropertiesInternal) this.mFingerprintSensorProperties.get(0)).sensorStrength == 2;
    }

    public final boolean isFingerprintDetectionRunning() {
        return this.mFingerprintRunningState == 1;
    }

    public final boolean isFingerprintDisabled(int i) {
        return (this.mDevicePolicyManager.getKeyguardDisabledFeatures(null, i) & 32) != 0 || isSimPinSecure();
    }

    public final boolean isFingerprintLockedOut() {
        return this.mFingerprintLockedOut || this.mFingerprintLockedOutPermanent;
    }

    public final boolean isFingerprintSupported() {
        return (this.mFpm == null || this.mFingerprintSensorProperties.isEmpty()) ? false : true;
    }

    public final boolean isKeyguardVisible() {
        return this.mKeyguardShowing && !this.mKeyguardOccluded;
    }

    public final boolean isSimPinSecure() {
        Iterator it = getSubscriptionInfo(false).iterator();
        while (it.hasNext()) {
            int simState = getSimState(((SubscriptionInfo) it.next()).getSubscriptionId());
            if (simState == 2 || simState == 3 || simState == 7) {
                return true;
            }
        }
        return false;
    }

    public final boolean isUdfpsEnrolled() {
        return this.mAuthController.isUdfpsEnrolled(this.mSelectedUserInteractor.getSelectedUserId());
    }

    @Deprecated
    public boolean isUnlockWithFacePossible() {
        SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = this.mFaceAuthInteractor;
        return systemUIDeviceEntryFaceAuthInteractor != null && systemUIDeviceEntryFaceAuthInteractor.isFaceAuthEnabledAndEnrolled();
    }

    public final boolean isUnlockWithFingerprintPossible(int i) {
        if (isFingerprintSupported() && !isFingerprintDisabled(i)) {
            if (((Boolean) ((HashMap) this.mAuthController.mFpEnrolledForUser).getOrDefault(Integer.valueOf(i), Boolean.FALSE)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isUnlockingWithBiometricAllowed(boolean z) {
        StrongAuthTracker strongAuthTracker = this.mStrongAuthTracker;
        if (!strongAuthTracker.isBiometricAllowedForUser(z, KeyguardUpdateMonitor.this.mSelectedUserInteractor.getSelectedUserId())) {
            return false;
        }
        SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = this.mFaceAuthInteractor;
        boolean z2 = systemUIDeviceEntryFaceAuthInteractor != null && ((Boolean) systemUIDeviceEntryFaceAuthInteractor.isLockedOut.getValue()).booleanValue();
        SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor2 = this.mFaceAuthInteractor;
        boolean z3 = systemUIDeviceEntryFaceAuthInteractor2 != null && systemUIDeviceEntryFaceAuthInteractor2.isFaceAuthStrong();
        boolean isFingerprintLockedOut = isFingerprintLockedOut();
        if ((isFingerprintClass3() && isFingerprintLockedOut) || (z3 && z2)) {
            return false;
        }
        return (z2 && isFingerprintLockedOut) ? false : true;
    }

    public final boolean isUserInLockdown(int i) {
        return (this.mStrongAuthTracker.getStrongAuthForUser(i) & 32) != 0;
    }

    public final void logListenerModelData(KeyguardListenModel keyguardListenModel) {
        this.mLogger.logKeyguardListenerModel(keyguardListenModel);
        if (!(keyguardListenModel instanceof KeyguardFingerprintListenModel)) {
            if (keyguardListenModel instanceof KeyguardActiveUnlockModel) {
                KeyguardActiveUnlockModel keyguardActiveUnlockModel = (KeyguardActiveUnlockModel) keyguardListenModel;
                KeyguardActiveUnlockModel keyguardActiveUnlockModel2 = (KeyguardActiveUnlockModel) this.mActiveUnlockTriggerBuffer.buffer.advance();
                keyguardActiveUnlockModel2.timeMillis = keyguardActiveUnlockModel.timeMillis;
                keyguardActiveUnlockModel2.userId = keyguardActiveUnlockModel.userId;
                keyguardActiveUnlockModel2.listening = keyguardActiveUnlockModel.listening;
                keyguardActiveUnlockModel2.awakeKeyguard = keyguardActiveUnlockModel.awakeKeyguard;
                keyguardActiveUnlockModel2.authInterruptActive = keyguardActiveUnlockModel.authInterruptActive;
                keyguardActiveUnlockModel2.fpLockedOut = keyguardActiveUnlockModel.fpLockedOut;
                keyguardActiveUnlockModel2.primaryAuthRequired = keyguardActiveUnlockModel.primaryAuthRequired;
                keyguardActiveUnlockModel2.switchingUser = keyguardActiveUnlockModel.switchingUser;
                keyguardActiveUnlockModel2.triggerActiveUnlockForAssistant = keyguardActiveUnlockModel.triggerActiveUnlockForAssistant;
                keyguardActiveUnlockModel2.userCanDismissLockScreen = keyguardActiveUnlockModel.userCanDismissLockScreen;
                return;
            }
            return;
        }
        KeyguardFingerprintListenModel keyguardFingerprintListenModel = (KeyguardFingerprintListenModel) keyguardListenModel;
        KeyguardFingerprintListenModel keyguardFingerprintListenModel2 = (KeyguardFingerprintListenModel) this.mFingerprintListenBuffer.buffer.advance();
        keyguardFingerprintListenModel2.timeMillis = keyguardFingerprintListenModel.timeMillis;
        keyguardFingerprintListenModel2.userId = keyguardFingerprintListenModel.userId;
        keyguardFingerprintListenModel2.listening = keyguardFingerprintListenModel.listening;
        keyguardFingerprintListenModel2.allowOnCurrentOccludingActivity = keyguardFingerprintListenModel.allowOnCurrentOccludingActivity;
        keyguardFingerprintListenModel2.alternateBouncerShowing = keyguardFingerprintListenModel.alternateBouncerShowing;
        keyguardFingerprintListenModel2.biometricEnabledForUser = keyguardFingerprintListenModel.biometricEnabledForUser;
        keyguardFingerprintListenModel2.biometricPromptShowing = keyguardFingerprintListenModel.biometricPromptShowing;
        keyguardFingerprintListenModel2.bouncerIsOrWillShow = keyguardFingerprintListenModel.bouncerIsOrWillShow;
        keyguardFingerprintListenModel2.canSkipBouncer = keyguardFingerprintListenModel.canSkipBouncer;
        keyguardFingerprintListenModel2.credentialAttempted = keyguardFingerprintListenModel.credentialAttempted;
        keyguardFingerprintListenModel2.deviceInteractive = keyguardFingerprintListenModel.deviceInteractive;
        keyguardFingerprintListenModel2.dreaming = keyguardFingerprintListenModel.dreaming;
        keyguardFingerprintListenModel2.fingerprintDisabled = keyguardFingerprintListenModel.fingerprintDisabled;
        keyguardFingerprintListenModel2.fingerprintLockedOut = keyguardFingerprintListenModel.fingerprintLockedOut;
        keyguardFingerprintListenModel2.goingToSleep = keyguardFingerprintListenModel.goingToSleep;
        keyguardFingerprintListenModel2.keyguardGoingAway = keyguardFingerprintListenModel.keyguardGoingAway;
        keyguardFingerprintListenModel2.keyguardIsVisible = keyguardFingerprintListenModel.keyguardIsVisible;
        keyguardFingerprintListenModel2.keyguardOccluded = keyguardFingerprintListenModel.keyguardOccluded;
        keyguardFingerprintListenModel2.occludingAppRequestingFp = keyguardFingerprintListenModel.occludingAppRequestingFp;
        keyguardFingerprintListenModel2.shouldListenForFingerprintAssistant = keyguardFingerprintListenModel.shouldListenForFingerprintAssistant;
        keyguardFingerprintListenModel2.strongerAuthRequired = keyguardFingerprintListenModel.strongerAuthRequired;
        keyguardFingerprintListenModel2.switchingUser = keyguardFingerprintListenModel.switchingUser;
        keyguardFingerprintListenModel2.systemUser = keyguardFingerprintListenModel.systemUser;
        keyguardFingerprintListenModel2.udfps = keyguardFingerprintListenModel.udfps;
        keyguardFingerprintListenModel2.userDoesNotHaveTrust = keyguardFingerprintListenModel.userDoesNotHaveTrust;
    }

    public final void notifyLockedOutStateChanged(BiometricSourceType biometricSourceType) {
        Assert.isMainThread();
        for (int i = 0; i < this.mCallbacks.size(); i++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onLockedOutStateChanged(biometricSourceType);
            }
        }
    }

    public void notifyNonStrongBiometricAllowedChanged(int i) {
        Assert.isMainThread();
        for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
        }
        if (i == this.mSelectedUserInteractor.getSelectedUserId()) {
            updateFingerprintListeningState(1);
        }
    }

    public void notifyStrongAuthAllowedChanged(int i) {
        Assert.isMainThread();
        for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onStrongAuthStateChanged(i);
            }
        }
        if (i == this.mSelectedUserInteractor.getSelectedUserId()) {
            updateFingerprintListeningState(1);
        }
    }

    public void onAlternateBouncerVisibilityChange(boolean z) {
        this.mAlternateBouncerShowing = z;
        if (z) {
            requestActiveUnlock(ActiveUnlockConfig.ActiveUnlockRequestOrigin.UNLOCK_INTENT, "alternateBouncer");
        }
        updateFingerprintListeningState(2);
    }

    public final void onEnabledTrustAgentsChanged(int i) {
        Assert.isMainThread();
        for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onEnabledTrustAgentsChanged(i);
            }
        }
    }

    public void onFaceAuthenticated(int i, boolean z) {
        Trace.beginSection("KeyGuardUpdateMonitor#onFaceAuthenticated");
        Assert.isMainThread();
        if (getUserCanSkipBouncer(i)) {
            this.mTrustManager.unlockedByBiometricForUser(i, BiometricSourceType.FACE);
        }
        updateFingerprintListeningState(2);
        this.mLogger.d("onFaceAuthenticated");
        for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onBiometricAuthenticated(i, BiometricSourceType.FACE, z);
            }
        }
        this.mAssistantVisible = false;
        this.mBackgroundExecutor.execute(new KeyguardUpdateMonitor$$ExternalSyntheticLambda2(this, z, i));
        Trace.endSection();
    }

    public void onFingerprintAuthenticated(int i, boolean z) {
        Assert.isMainThread();
        Trace.beginSection("KeyGuardUpdateMonitor#onFingerPrintAuthenticated");
        this.mUserFingerprintAuthenticated.put(i, new BiometricAuthenticated(z));
        if (getUserCanSkipBouncer(i)) {
            this.mTrustManager.unlockedByBiometricForUser(i, BiometricSourceType.FINGERPRINT);
        }
        this.mFingerprintCancelSignal = null;
        this.mLogger.logFingerprintSuccess(i, z);
        updateFingerprintListeningState(2);
        for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onBiometricAuthenticated(i, BiometricSourceType.FINGERPRINT, z);
            }
        }
        AnonymousClass13 anonymousClass13 = this.mHandler;
        anonymousClass13.sendMessageDelayed(anonymousClass13.obtainMessage(336), 500L);
        this.mAssistantVisible = false;
        this.mBackgroundExecutor.execute(new KeyguardUpdateMonitor$$ExternalSyntheticLambda2(this, z, i));
        Trace.endSection();
    }

    public void onTransitionStateChanged(ObservableTransitionState observableTransitionState) {
        throw new IllegalStateException("New code path not supported when SceneContainerFlag is disabled.");
    }

    public final void onTrustChanged(boolean z, boolean z2, int i, int i2, List list) {
        Assert.isMainThread();
        boolean z3 = this.mUserHasTrust.get(i, false);
        this.mUserHasTrust.put(i, z);
        if (z3 == z || z) {
            updateFingerprintListeningState(1);
        } else {
            updateFingerprintListeningState(0);
        }
        this.mLogger.logTrustChanged(i, z3, z);
        for (int i3 = 0; i3 < this.mCallbacks.size(); i3++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i3)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onTrustChanged(i);
            }
        }
        if (z) {
            String str = null;
            if (this.mSelectedUserInteractor.getSelectedUserId() == i && list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    str = (String) it.next();
                    if (!TextUtils.isEmpty(str)) {
                        break;
                    }
                }
            }
            this.mLogger.logTrustGrantedWithFlags(i2, i, str, z2);
            if (i == this.mSelectedUserInteractor.getSelectedUserId()) {
                if (z2) {
                    this.mUiEventLogger.log(TrustAgentUiEvent.TRUST_AGENT_NEWLY_UNLOCKED, ((SessionTracker) this.mSessionTrackerProvider.get()).getSessionId(1));
                }
                TrustGrantFlags trustGrantFlags = new TrustGrantFlags(i2);
                for (int i4 = 0; i4 < this.mCallbacks.size(); i4++) {
                    KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback2 = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i4)).get();
                    if (keyguardUpdateMonitorCallback2 != null) {
                        boolean z4 = this.mPrimaryBouncerIsOrWillBeShowing || this.mAlternateBouncerShowing;
                        int i5 = trustGrantFlags.mFlags;
                        keyguardUpdateMonitorCallback2.onTrustGrantedForCurrentUser(((i5 & 1) != 0 || trustGrantFlags.dismissKeyguardRequested()) && (this.mDeviceInteractive || (i5 & 4) != 0) && (z4 || trustGrantFlags.dismissKeyguardRequested()), z2, trustGrantFlags, str);
                    }
                }
            }
        }
    }

    public final void onTrustError(CharSequence charSequence) {
        Assert.isMainThread();
        for (int i = 0; i < this.mCallbacks.size(); i++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onTrustAgentErrorMessage(charSequence);
            }
        }
    }

    public final void onTrustManagedChanged(boolean z, int i) {
        Assert.isMainThread();
        this.mUserTrustIsManaged.put(i, z);
        boolean isTrustUsuallyManaged = this.mTrustManager.isTrustUsuallyManaged(i);
        this.mLogger.logTrustUsuallyManagedUpdated(i, "onTrustManagedChanged", this.mUserTrustIsUsuallyManaged.get(i), isTrustUsuallyManaged);
        this.mUserTrustIsUsuallyManaged.put(i, isTrustUsuallyManaged);
        for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onTrustManagedChanged();
            }
        }
    }

    public final boolean refreshSimState(int i, int i2) {
        int simState = this.mTelephonyManager.getSimState(i2);
        SimData simData = (SimData) this.mSimDatas.get(Integer.valueOf(i));
        if (simData == null) {
            this.mSimDatas.put(Integer.valueOf(i), new SimData(simState, i2, i));
        } else {
            r2 = simData.simState != simState;
            simData.simState = simState;
        }
        return r2;
    }

    public final void registerCallback(KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback) {
        Assert.isMainThread();
        this.mLogger.logRegisterCallback(keyguardUpdateMonitorCallback);
        for (int i = 0; i < this.mCallbacks.size(); i++) {
            if (((WeakReference) this.mCallbacks.get(i)).get() == keyguardUpdateMonitorCallback) {
                this.mLogger.logException(new Exception("Called by"), "Object tried to add another callback");
                return;
            }
        }
        this.mCallbacks.add(new WeakReference(keyguardUpdateMonitorCallback));
        removeCallback(null);
        keyguardUpdateMonitorCallback.onRefreshBatteryInfo(this.mBatteryStatus);
        keyguardUpdateMonitorCallback.onTimeChanged();
        keyguardUpdateMonitorCallback.onPhoneStateChanged();
        keyguardUpdateMonitorCallback.onRefreshCarrierInfo();
        keyguardUpdateMonitorCallback.onKeyguardVisibilityChanged(isKeyguardVisible());
        keyguardUpdateMonitorCallback.onTelephonyCapable(this.mTelephonyCapable);
        Iterator it = this.mSimDatas.entrySet().iterator();
        while (it.hasNext()) {
            SimData simData = (SimData) ((Map.Entry) it.next()).getValue();
            keyguardUpdateMonitorCallback.onSimStateChanged(simData.subId, simData.slotId, simData.simState);
        }
    }

    public final void removeCallback(final KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback) {
        Assert.isMainThread();
        this.mLogger.logUnregisterCallback(keyguardUpdateMonitorCallback);
        this.mCallbacks.removeIf(new Predicate() { // from class: com.android.keyguard.KeyguardUpdateMonitor$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback2 = KeyguardUpdateMonitorCallback.this;
                int i = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                return ((WeakReference) obj).get() == keyguardUpdateMonitorCallback2;
            }
        });
    }

    public final void reportSimUnlocked(int i) {
        this.mLogger.logSimUnlocked(i);
        handleSimStateChange(i, getSlotId(i), 5);
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x003a, code lost:
    
        if (r0.requestActiveUnlockOnUnlockIntentLegacy == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x004b, code lost:
    
        if (r0.requestActiveUnlockOnWakeup == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x005f, code lost:
    
        if (r0.shouldRequestActiveUnlockOnUnlockIntentFromBiometricEnrollment() == false) goto L46;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void requestActiveUnlock(com.android.keyguard.ActiveUnlockConfig.ActiveUnlockRequestOrigin r7, java.lang.String r8, boolean r9) {
        /*
            r6 = this;
            com.android.keyguard.KeyguardUpdateMonitor$13 r0 = r6.mHandler
            r1 = 336(0x150, float:4.71E-43)
            boolean r0 = r0.hasMessages(r1)
            if (r0 == 0) goto Lb
            return
        Lb:
            com.android.keyguard.ActiveUnlockConfig r0 = r6.mActiveUnlockConfig
            r0.getClass()
            int r2 = r7.ordinal()
            r3 = 1
            if (r2 == 0) goto L62
            r4 = 0
            if (r2 == r3) goto L4f
            r5 = 2
            if (r2 == r5) goto L3d
            r5 = 3
            if (r2 == r5) goto L2c
            r4 = 4
            if (r2 != r4) goto L26
            boolean r4 = r0.requestActiveUnlockOnUnlockIntentLegacy
            goto L64
        L26:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        L2c:
            boolean r2 = r0.requestActiveUnlockOnWakeup
            if (r2 != 0) goto L4d
            boolean r2 = r0.requestActiveUnlockOnUnlockIntent
            if (r2 != 0) goto L4d
            boolean r2 = r0.requestActiveUnlockOnBioFail
            if (r2 != 0) goto L4d
            boolean r0 = r0.requestActiveUnlockOnUnlockIntentLegacy
            if (r0 == 0) goto L64
            goto L4d
        L3d:
            boolean r2 = r0.requestActiveUnlockOnBioFail
            if (r2 != 0) goto L4d
            boolean r2 = r0.requestActiveUnlockOnUnlockIntentLegacy
            if (r2 != 0) goto L4d
            boolean r2 = r0.requestActiveUnlockOnUnlockIntent
            if (r2 != 0) goto L4d
            boolean r0 = r0.requestActiveUnlockOnWakeup
            if (r0 == 0) goto L64
        L4d:
            r4 = r3
            goto L64
        L4f:
            boolean r2 = r0.requestActiveUnlockOnUnlockIntent
            if (r2 != 0) goto L4d
            boolean r2 = r0.requestActiveUnlockOnUnlockIntentLegacy
            if (r2 != 0) goto L4d
            boolean r2 = r0.requestActiveUnlockOnWakeup
            if (r2 != 0) goto L4d
            boolean r0 = r0.shouldRequestActiveUnlockOnUnlockIntentFromBiometricEnrollment()
            if (r0 == 0) goto L64
            goto L4d
        L62:
            boolean r4 = r0.requestActiveUnlockOnWakeup
        L64:
            com.android.keyguard.ActiveUnlockConfig$ActiveUnlockRequestOrigin r0 = com.android.keyguard.ActiveUnlockConfig.ActiveUnlockRequestOrigin.WAKE
            if (r7 != r0) goto L9c
            if (r4 != 0) goto L9c
            com.android.keyguard.ActiveUnlockConfig r0 = r6.mActiveUnlockConfig
            boolean r2 = r0.requestActiveUnlockOnWakeup
            if (r2 != 0) goto L7c
            boolean r2 = r0.requestActiveUnlockOnUnlockIntent
            if (r2 != 0) goto L7c
            boolean r2 = r0.requestActiveUnlockOnBioFail
            if (r2 != 0) goto L7c
            boolean r0 = r0.requestActiveUnlockOnUnlockIntentLegacy
            if (r0 == 0) goto L9c
        L7c:
            com.android.keyguard.KeyguardUpdateMonitor$13 r7 = r6.mHandler
            boolean r7 = r7.hasMessages(r1)
            if (r7 == 0) goto L85
            goto L9b
        L85:
            boolean r7 = r6.shouldTriggerActiveUnlock(r3)
            if (r7 == 0) goto L9b
            com.android.keyguard.logging.KeyguardUpdateMonitorLogger r7 = r6.mLogger
            r7.logActiveUnlockTriggered(r8)
            android.app.trust.TrustManager r7 = r6.mTrustManager
            com.android.systemui.user.domain.interactor.SelectedUserInteractor r6 = r6.mSelectedUserInteractor
            int r6 = r6.getSelectedUserId()
            r7.reportUserMayRequestUnlock(r6)
        L9b:
            return
        L9c:
            if (r4 == 0) goto Lb4
            boolean r0 = r6.shouldTriggerActiveUnlock(r3)
            if (r0 == 0) goto Lb4
            com.android.keyguard.logging.KeyguardUpdateMonitorLogger r0 = r6.mLogger
            r0.logUserRequestedUnlock(r7, r8, r9)
            android.app.trust.TrustManager r7 = r6.mTrustManager
            com.android.systemui.user.domain.interactor.SelectedUserInteractor r6 = r6.mSelectedUserInteractor
            int r6 = r6.getSelectedUserId()
            r7.reportUserRequestedUnlock(r6, r9)
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.keyguard.KeyguardUpdateMonitor.requestActiveUnlock(com.android.keyguard.ActiveUnlockConfig$ActiveUnlockRequestOrigin, java.lang.String, boolean):void");
    }

    public final void requestActiveUnlockFromWakeReason(int i, boolean z) {
        if (!((FaceWakeUpTriggersConfigImpl) this.mFaceWakeUpTriggersConfig).triggerFaceAuthOnWakeUpFrom.contains(Integer.valueOf(i))) {
            this.mLogger.logActiveUnlockRequestSkippedForWakeReasonDueToFaceConfig(i);
            return;
        }
        ActiveUnlockConfig.ActiveUnlockRequestOrigin activeUnlockRequestOrigin = this.mActiveUnlockConfig.wakeupsConsideredUnlockIntents.contains(Integer.valueOf(i)) ? ActiveUnlockConfig.ActiveUnlockRequestOrigin.UNLOCK_INTENT : ActiveUnlockConfig.ActiveUnlockRequestOrigin.WAKE;
        String str = "wakingUp - " + PowerManager.wakeReasonToString(i) + " powerManagerWakeup=" + z;
        if (!this.mActiveUnlockConfig.wakeupsToForceDismissKeyguard.contains(Integer.valueOf(i))) {
            requestActiveUnlock(activeUnlockRequestOrigin, str);
            return;
        }
        requestActiveUnlock(activeUnlockRequestOrigin, str + "-dismissKeyguard", true);
    }

    public void resetBiometricListeningState() {
        this.mFingerprintRunningState = 0;
        this.mFingerprintDetectRunning = false;
    }

    public final boolean resolveNeedsSlowUnlockTransition() {
        if (this.mUserIsUnlocked.get(this.mSelectedUserInteractor.getSelectedUserId())) {
            return false;
        }
        ResolveInfo resolveActivityAsUser = this.mPackageManager.resolveActivityAsUser(new Intent("android.intent.action.MAIN").addCategory("android.intent.category.HOME"), 0, this.mSelectedUserInteractor.getSelectedUserId());
        if (resolveActivityAsUser != null) {
            return FALLBACK_HOME_COMPONENT.equals(resolveActivityAsUser.getComponentInfo().getComponentName());
        }
        KeyguardUpdateMonitorLogger keyguardUpdateMonitorLogger = this.mLogger;
        keyguardUpdateMonitorLogger.getClass();
        keyguardUpdateMonitorLogger.logBuffer.log("KeyguardUpdateMonitorLog", LogLevel.WARNING, "resolveNeedsSlowUnlockTransition: returning false since activity could not be resolved.", null);
        return false;
    }

    public void setAssistantVisible(boolean z) {
        this.mAssistantVisible = z;
        this.mLogger.logAssistantVisible(z);
        updateFingerprintListeningState(2);
        if (this.mAssistantVisible) {
            SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = this.mFaceAuthInteractor;
            if (systemUIDeviceEntryFaceAuthInteractor != null) {
                systemUIDeviceEntryFaceAuthInteractor.runFaceAuth(FaceAuthUiEvent.FACE_AUTH_UPDATED_ASSISTANT_VISIBILITY_CHANGED, true);
            }
            requestActiveUnlock(ActiveUnlockConfig.ActiveUnlockRequestOrigin.ASSISTANT, "assistant", true);
        }
    }

    public final void setFingerprintRunningState(int i) {
        boolean z = this.mFingerprintRunningState == 1;
        boolean z2 = i == 1;
        this.mFingerprintRunningState = i;
        if (i == 0) {
            this.mFingerprintDetectRunning = false;
        }
        this.mLogger.logFingerprintRunningState(i);
        if (z != z2) {
            Assert.isMainThread();
            for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
                KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i2)).get();
                if (keyguardUpdateMonitorCallback != null) {
                    keyguardUpdateMonitorCallback.onBiometricRunningStateChanged(BiometricSourceType.FINGERPRINT, isFingerprintDetectionRunning());
                }
            }
        }
    }

    public final void setForceIsDismissibleKeyguard(boolean z) {
        Assert.isMainThread();
        if (this.mFoldGracePeriodProvider.isEnabled()) {
            if (this.mKeyguardShowing && z) {
                this.mLogger.d("Skip setting forceIsDismissibleKeyguard to true. Keyguard already showing.");
                return;
            }
            if (this.mForceIsDismissible != z) {
                this.mForceIsDismissible = z;
                this.mLogger.logForceIsDismissibleKeyguard(z);
                for (int i = 0; i < this.mCallbacks.size(); i++) {
                    KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i)).get();
                    if (keyguardUpdateMonitorCallback != null) {
                        keyguardUpdateMonitorCallback.onForceIsDismissibleChanged(forceIsDismissibleIsKeepingDeviceUnlocked());
                    }
                }
            }
        }
    }

    public final void setKeyguardGoingAway(boolean z) {
        this.mKeyguardGoingAway = z;
        if (z) {
            for (int i = 0; i < this.mCallbacks.size(); i++) {
                KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i)).get();
                if (keyguardUpdateMonitorCallback != null) {
                    keyguardUpdateMonitorCallback.onKeyguardGoingAway();
                }
            }
        }
        updateFingerprintListeningState(2);
    }

    public void setStrongAuthTracker(StrongAuthTracker strongAuthTracker) {
        StrongAuthTracker strongAuthTracker2 = this.mStrongAuthTracker;
        if (strongAuthTracker2 != null) {
            this.mLockPatternUtils.unregisterStrongAuthTracker(strongAuthTracker2);
        }
        this.mStrongAuthTracker = strongAuthTracker;
        this.mLockPatternUtils.registerStrongAuthTracker(strongAuthTracker);
    }

    public boolean shouldListenForFingerprint(boolean z) {
        boolean z2;
        int selectedUserId = this.mSelectedUserInteractor.getSelectedUserId();
        boolean userHasTrust = getUserHasTrust(selectedUserId);
        boolean z3 = !userHasTrust;
        boolean z4 = this.mAssistantVisible && this.mKeyguardOccluded && ((BiometricAuthenticated) this.mUserFingerprintAuthenticated.get(this.mSelectedUserInteractor.getSelectedUserId())) == null && !this.mUserHasTrust.get(this.mSelectedUserInteractor.getSelectedUserId(), false);
        boolean z5 = isKeyguardVisible() || !this.mDeviceInteractive || (this.mPrimaryBouncerIsOrWillBeShowing && !this.mKeyguardGoingAway) || this.mGoingToSleep || z4 || (((z2 = this.mKeyguardOccluded) && this.mIsDreaming) || (z2 && !userHasTrust && this.mKeyguardShowing && (this.mOccludingAppRequestingFp || z || this.mAlternateBouncerShowing || this.mAllowFingerprintOnCurrentOccludingActivity)));
        boolean z6 = this.mBiometricEnabledForUser.get(selectedUserId);
        boolean userCanSkipBouncer = getUserCanSkipBouncer(selectedUserId);
        boolean isFingerprintDisabled = isFingerprintDisabled(selectedUserId);
        boolean z7 = (this.mSwitchingUser || isFingerprintDisabled || (this.mKeyguardGoingAway && this.mDeviceInteractive) || !this.mIsSystemUser || !z6 || isUserInLockdown(selectedUserId)) ? false : true;
        boolean isUnlockingWithBiometricAllowed = isUnlockingWithBiometricAllowed(BiometricSourceType.FINGERPRINT);
        boolean z8 = !isUnlockingWithBiometricAllowed;
        boolean z9 = z5 && z7 && (isUnlockingWithBiometricAllowed || !this.mPrimaryBouncerIsOrWillBeShowing) && (!z || (!userCanSkipBouncer && isUnlockingWithBiometricAllowed && !userHasTrust)) && !this.mBiometricPromptShowing;
        logListenerModelData(new KeyguardFingerprintListenModel(System.currentTimeMillis(), selectedUserId, z9, this.mAllowFingerprintOnCurrentOccludingActivity, this.mAlternateBouncerShowing, z6, this.mBiometricPromptShowing, this.mPrimaryBouncerIsOrWillBeShowing, userCanSkipBouncer, this.mCredentialAttempted, this.mDeviceInteractive, this.mIsDreaming, isFingerprintDisabled, this.mFingerprintLockedOut, this.mGoingToSleep, this.mKeyguardGoingAway, isKeyguardVisible(), this.mKeyguardOccluded, this.mOccludingAppRequestingFp, z4, z8, this.mSwitchingUser, this.mIsSystemUser, z, z3));
        return z9;
    }

    public final boolean shouldTriggerActiveUnlock(boolean z) {
        boolean z2 = this.mAssistantVisible && this.mKeyguardOccluded && !this.mUserHasTrust.get(this.mSelectedUserInteractor.getSelectedUserId(), false);
        boolean z3 = this.mPrimaryBouncerFullyShown || this.mAlternateBouncerShowing || !(!isKeyguardVisible() || this.mGoingToSleep || this.mStatusBarState == 2);
        int selectedUserId = this.mSelectedUserInteractor.getSelectedUserId();
        boolean z4 = getUserCanSkipBouncer(selectedUserId) || !this.mLockPatternUtils.isSecure(selectedUserId);
        boolean isFingerprintLockedOut = isFingerprintLockedOut();
        boolean isUnlockingWithBiometricAllowed = isUnlockingWithBiometricAllowed(true);
        boolean z5 = !isUnlockingWithBiometricAllowed;
        boolean z6 = (!(this.mAuthInterruptActive || z2 || z3) || this.mSwitchingUser || z4 || isFingerprintLockedOut || !isUnlockingWithBiometricAllowed || this.mKeyguardGoingAway || this.mSecureCameraLaunched) ? false : true;
        if (z) {
            logListenerModelData(new KeyguardActiveUnlockModel(System.currentTimeMillis(), selectedUserId, z6, z3, this.mAuthInterruptActive, isFingerprintLockedOut, z5, this.mSwitchingUser, z2, z4));
        }
        return z6;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (!this.mDeviceProvisioned) {
            this.mDeviceProvisionedObserver = new AnonymousClass14(this, this.mHandler, 1);
            this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("device_provisioned"), false, this.mDeviceProvisionedObserver);
            boolean isDeviceProvisionedInSettingsDb = isDeviceProvisionedInSettingsDb();
            if (isDeviceProvisionedInSettingsDb != this.mDeviceProvisioned) {
                this.mDeviceProvisioned = isDeviceProvisionedInSettingsDb;
                if (isDeviceProvisionedInSettingsDb) {
                    sendEmptyMessage(308);
                }
            }
        }
        this.mBatteryStatus = new BatteryStatus();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");
        intentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
        intentFilter.addAction("android.intent.action.SERVICE_STATE");
        intentFilter.addAction("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED");
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        intentFilter.addAction("android.telephony.action.SERVICE_PROVIDERS_UPDATED");
        intentFilter.addAction("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED");
        intentFilter.addAction("android.hardware.usb.action.USB_PORT_COMPLIANCE_CHANGED");
        this.mBroadcastDispatcher.registerReceiverWithHandler(this.mBroadcastReceiver, intentFilter, this.mHandler);
        this.mBackgroundExecutor.execute(new KeyguardUpdateMonitor$$ExternalSyntheticLambda3(this, 3));
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.app.action.NEXT_ALARM_CLOCK_CHANGED");
        intentFilter2.addAction("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED");
        intentFilter2.addAction("android.intent.action.USER_UNLOCKED");
        intentFilter2.addAction("android.intent.action.USER_STOPPED");
        intentFilter2.addAction("android.intent.action.USER_REMOVED");
        intentFilter2.addAction("android.nfc.action.REQUIRE_UNLOCK_FOR_NFC");
        BroadcastDispatcher broadcastDispatcher = this.mBroadcastDispatcher;
        BroadcastReceiver broadcastReceiver = this.mBroadcastAllReceiver;
        AnonymousClass13 anonymousClass13 = this.mHandler;
        UserHandle userHandle = UserHandle.ALL;
        broadcastDispatcher.getClass();
        BroadcastDispatcher.registerReceiverWithHandler$default(broadcastDispatcher, broadcastReceiver, intentFilter2, anonymousClass13, userHandle, 48);
        this.mSubscriptionManager.addOnSubscriptionsChangedListener(this.mSubscriptionListener);
        ((UserTrackerImpl) this.mUserTracker).addCallback(this.mUserChangedCallback, this.mMainExecutor);
        this.mTrustManager.registerTrustListener(this);
        setStrongAuthTracker(this.mStrongAuthTracker);
        FingerprintManager fingerprintManager = this.mFpm;
        if (fingerprintManager != null) {
            fingerprintManager.addAuthenticatorsRegisteredCallback(new IFingerprintAuthenticatorsRegisteredCallback.Stub() { // from class: com.android.keyguard.KeyguardUpdateMonitor.15
                public final void onAllAuthenticatorsRegistered(List list) {
                    KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardUpdateMonitor.this;
                    keyguardUpdateMonitor.mFingerprintSensorProperties = list;
                    keyguardUpdateMonitor.updateFingerprintListeningState(2);
                    KeyguardUpdateMonitor.this.mLogger.d("FingerprintManager onAllAuthenticatorsRegistered");
                }
            });
            this.mFpm.addLockoutResetCallback(this.mFingerprintLockoutResetCallback);
        }
        BiometricManager biometricManager = this.mBiometricManager;
        if (biometricManager != null) {
            biometricManager.registerEnabledOnKeyguardCallback(this.mBiometricEnabledCallback);
        }
        this.mAuthController.addCallback(new AnonymousClass16());
        ((DevicePostureControllerImpl) this.mDevicePostureController).addCallback(this.mPostureCallback);
        updateFingerprintListeningState(2);
        this.mTaskStackChangeListeners.registerTaskStackListener(this.mTaskStackListener);
        int selectedUserId = this.mSelectedUserInteractor.getSelectedUserId();
        boolean isUserUnlocked = this.mUserManager.isUserUnlocked(selectedUserId);
        this.mLogger.logUserUnlockedInitialState(selectedUserId, isUserUnlocked);
        this.mUserIsUnlocked.put(selectedUserId, isUserUnlocked);
        this.mLogoutEnabled = this.mDevicePolicyManager.isLogoutEnabled();
        updateSecondaryLockscreenRequirement(selectedUserId);
        for (UserInfo userInfo : this.mUserManager.getUsers()) {
            boolean isTrustUsuallyManaged = this.mTrustManager.isTrustUsuallyManaged(userInfo.id);
            KeyguardUpdateMonitorLogger keyguardUpdateMonitorLogger = this.mLogger;
            int i = userInfo.id;
            keyguardUpdateMonitorLogger.logTrustUsuallyManagedUpdated(i, "init from constructor", this.mUserTrustIsUsuallyManaged.get(i), isTrustUsuallyManaged);
            this.mUserTrustIsUsuallyManaged.put(userInfo.id, isTrustUsuallyManaged);
        }
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) != 0 && !hasMessages(329)) {
            sendEmptyMessage(329);
        }
        TelephonyListenerManager telephonyListenerManager = this.mTelephonyListenerManager;
        telephonyListenerManager.mTelephonyCallback.mActiveDataSubscriptionIdListeners.add(this.mPhoneStateListener);
        telephonyListenerManager.updateListening();
        for (int i2 = 0; i2 < this.mTelephonyManager.getActiveModemCount(); i2++) {
            int simState = this.mTelephonyManager.getSimState(i2);
            int[] subscriptionIds = this.mSubscriptionManager.getSubscriptionIds(i2);
            if (subscriptionIds != null) {
                for (int i3 : subscriptionIds) {
                    obtainMessage(304, i3, i2, Integer.valueOf(simState)).sendToTarget();
                }
            }
        }
        this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("time_12_24"), false, this.mTimeFormatChangeObserver, -1);
        UserTrackerImpl userTrackerImpl = (UserTrackerImpl) this.mUserTracker;
        if (userTrackerImpl.isUserSwitching) {
            handleUserSwitching(userTrackerImpl.getUserId(), new KeyguardUpdateMonitor$$ExternalSyntheticLambda6());
        }
    }

    public final void startListeningForFingerprint(boolean z) {
        int selectedUserId = this.mSelectedUserInteractor.getSelectedUserId();
        boolean isUnlockWithFingerprintPossible = isUnlockWithFingerprintPossible(selectedUserId);
        if (this.mFingerprintCancelSignal != null) {
            this.mLogger.logUnexpectedFpCancellationSignalState(this.mFingerprintRunningState, isUnlockWithFingerprintPossible);
        }
        int i = this.mFingerprintRunningState;
        if (i == 2) {
            setFingerprintRunningState(3);
            return;
        }
        if (i != 3 && isUnlockWithFingerprintPossible) {
            this.mFingerprintCancelSignal = new CancellationSignal();
            FingerprintAuthenticateOptions build = new FingerprintAuthenticateOptions.Builder().setUserId(selectedUserId).build();
            FingerprintInteractiveToAuthProviderGoogle fingerprintInteractiveToAuthProviderGoogle = this.mFingerprintInteractiveToAuthProvider;
            if (fingerprintInteractiveToAuthProviderGoogle != null) {
                AuthenticateReason.Vendor vendor2 = new AuthenticateReason.Vendor();
                PressToAuthParcelable pressToAuthParcelable = new PressToAuthParcelable();
                pressToAuthParcelable.pressToAuthEnabled = fingerprintInteractiveToAuthProviderGoogle.secureSettings.getIntForUser("sfps_performant_auth_enabled_v2", fingerprintInteractiveToAuthProviderGoogle.context.getResources().getBoolean(R.bool.config_profcollectReportUploaderEnabled) ? 1 : 0, selectedUserId) == 0;
                vendor2.extension.setParcelable(pressToAuthParcelable);
                build.setVendorReason(vendor2);
            }
            if (z) {
                this.mLogger.v("startListeningForFingerprint - detect");
                this.mFpm.detectFingerprint(this.mFingerprintCancelSignal, this.mFingerprintDetectionCallback, build);
                this.mFingerprintDetectRunning = true;
            } else {
                this.mLogger.v("startListeningForFingerprint");
                this.mFpm.authenticate((FingerprintManager.CryptoObject) null, this.mFingerprintCancelSignal, this.mFingerprintAuthenticationCallback, (Handler) null, build);
                this.mFingerprintDetectRunning = false;
            }
            setFingerprintRunningState(1);
        }
    }

    public final void stopListeningForFingerprint() {
        this.mLogger.v("stopListeningForFingerprint()");
        if (this.mFingerprintRunningState == 1) {
            CancellationSignal cancellationSignal = this.mFingerprintCancelSignal;
            if (cancellationSignal != null) {
                cancellationSignal.cancel();
                this.mFingerprintCancelSignal = null;
                removeCallbacks(this.mFpCancelNotReceived);
                postDelayed(this.mFpCancelNotReceived, 3000L);
            }
            setFingerprintRunningState(2);
        }
        if (this.mFingerprintRunningState == 3) {
            setFingerprintRunningState(2);
        }
    }

    public final void updateFingerprintListeningState(int i) {
        if (hasMessages(336)) {
            this.mLogger.logHandlerHasAuthContinueMsgs(i);
            return;
        }
        AuthController authController = this.mAuthController;
        if (!authController.mAllFingerprintAuthenticatorsRegistered) {
            this.mLogger.d("All FP authenticators not registered, skipping FP listening state update");
            return;
        }
        boolean shouldListenForFingerprint = shouldListenForFingerprint(authController.isUdfpsSupported());
        int i2 = this.mFingerprintRunningState;
        boolean z = i2 == 1;
        boolean z2 = z || i2 == 3;
        boolean isUnlockingWithBiometricAllowed = isUnlockingWithBiometricAllowed(BiometricSourceType.FINGERPRINT);
        boolean z3 = !isUnlockingWithBiometricAllowed;
        if (z2 && !shouldListenForFingerprint) {
            if (i == 0) {
                this.mLogger.v("Ignoring stopListeningForFingerprint()");
                return;
            } else {
                stopListeningForFingerprint();
                return;
            }
        }
        if (!z2 && shouldListenForFingerprint) {
            if (i == 1) {
                this.mLogger.v("Ignoring startListeningForFingerprint()");
                return;
            } else {
                startListeningForFingerprint(z3);
                return;
            }
        }
        if (!z || z3 == this.mFingerprintDetectRunning) {
            return;
        }
        if (i == 1) {
            if (isUnlockingWithBiometricAllowed) {
                this.mLogger.v("Ignoring startListeningForFingerprint() switch detect -> auth");
                return;
            }
            this.mLogger.v("Allowing startListeningForFingerprint(detect) despite BIOMETRIC_ACTION_STOP since auth was running before.");
        }
        startListeningForFingerprint(z3);
    }

    public final void updateSecondaryLockscreenRequirement(int i) {
        Intent intent = (Intent) this.mSecondaryLockscreenRequirement.get(Integer.valueOf(i));
        boolean isSecondaryLockscreenEnabled = this.mDevicePolicyManager.isSecondaryLockscreenEnabled(UserHandle.of(i));
        if (isSecondaryLockscreenEnabled && intent == null) {
            ComponentName profileOwnerOrDeviceOwnerSupervisionComponent = this.mDevicePolicyManager.getProfileOwnerOrDeviceOwnerSupervisionComponent(UserHandle.of(i));
            if (profileOwnerOrDeviceOwnerSupervisionComponent == null) {
                this.mLogger.logMissingSupervisorAppError(i);
                return;
            }
            ResolveInfo resolveService = this.mPackageManager.resolveService(new Intent("android.app.action.BIND_SECONDARY_LOCKSCREEN_SERVICE").setPackage(profileOwnerOrDeviceOwnerSupervisionComponent.getPackageName()), 0);
            if (resolveService == null || resolveService.serviceInfo == null) {
                return;
            }
            this.mSecondaryLockscreenRequirement.put(Integer.valueOf(i), new Intent().setComponent(resolveService.serviceInfo.getComponentName()));
        } else if (isSecondaryLockscreenEnabled || intent == null) {
            return;
        } else {
            this.mSecondaryLockscreenRequirement.put(Integer.valueOf(i), null);
        }
        for (int i2 = 0; i2 < this.mCallbacks.size(); i2++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i2)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onSecondaryLockscreenRequirementChanged(i);
            }
        }
    }

    public void updateTelephonyCapable(boolean z) {
        Assert.isMainThread();
        if (z == this.mTelephonyCapable) {
            return;
        }
        this.mTelephonyCapable = z;
        for (int i = 0; i < this.mCallbacks.size(); i++) {
            KeyguardUpdateMonitorCallback keyguardUpdateMonitorCallback = (KeyguardUpdateMonitorCallback) ((WeakReference) this.mCallbacks.get(i)).get();
            if (keyguardUpdateMonitorCallback != null) {
                keyguardUpdateMonitorCallback.onTelephonyCapable(this.mTelephonyCapable);
            }
        }
    }

    public final boolean isUnlockingWithBiometricAllowed(BiometricSourceType biometricSourceType) {
        int i = AnonymousClass20.$SwitchMap$android$hardware$biometrics$BiometricSourceType[biometricSourceType.ordinal()];
        if (i == 1) {
            return isUnlockingWithBiometricAllowed(isFingerprintClass3());
        }
        if (i != 2) {
            return false;
        }
        SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor = this.mFaceAuthInteractor;
        return systemUIDeviceEntryFaceAuthInteractor != null && isUnlockingWithBiometricAllowed(systemUIDeviceEntryFaceAuthInteractor.isFaceAuthStrong());
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0024, code lost:
    
        if ((r0 == null ? false : r0.mOnFingerDown) != false) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void requestActiveUnlock(com.android.keyguard.ActiveUnlockConfig.ActiveUnlockRequestOrigin r3, java.lang.String r4) {
        /*
            r2 = this;
            boolean r0 = r2.isFaceEnabledAndEnrolled()
            if (r0 == 0) goto L11
            com.android.systemui.statusbar.phone.KeyguardBypassController r0 = r2.mKeyguardBypassController
            if (r0 == 0) goto L11
            boolean r0 = r0.canBypass()
            if (r0 == 0) goto L11
            goto L26
        L11:
            boolean r0 = r2.mAlternateBouncerShowing
            if (r0 != 0) goto L26
            boolean r0 = r2.mPrimaryBouncerFullyShown
            if (r0 != 0) goto L26
            com.android.systemui.biometrics.AuthController r0 = r2.mAuthController
            com.android.systemui.biometrics.UdfpsController r0 = r0.mUdfpsController
            r1 = 0
            if (r0 != 0) goto L22
            r0 = r1
            goto L24
        L22:
            boolean r0 = r0.mOnFingerDown
        L24:
            if (r0 == 0) goto L27
        L26:
            r1 = 1
        L27:
            r2.requestActiveUnlock(r3, r4, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.keyguard.KeyguardUpdateMonitor.requestActiveUnlock(com.android.keyguard.ActiveUnlockConfig$ActiveUnlockRequestOrigin, java.lang.String):void");
    }

    public final void onIsActiveUnlockRunningChanged(boolean z, int i) {
    }
}

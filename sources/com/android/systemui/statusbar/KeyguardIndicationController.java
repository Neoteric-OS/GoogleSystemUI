package com.android.systemui.statusbar;

import android.animation.Animator;
import android.app.AlarmManager;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.hardware.biometrics.BiometricSourceType;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserManager;
import android.text.TextUtils;
import android.util.Pair;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import com.android.internal.app.IBatteryStats;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.TrustGrantFlags;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.systemui.DejankUtils;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.FaceHelpMessageDeferral;
import com.android.systemui.biometrics.FaceHelpMessageDeferralFactory;
import com.android.systemui.biometrics.UdfpsController;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractorKt;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFingerprintAuthInteractor;
import com.android.systemui.deviceentry.domain.interactor.SystemUIDeviceEntryFaceAuthInteractor;
import com.android.systemui.dock.DockManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.keyguard.KeyguardIndication;
import com.android.systemui.keyguard.KeyguardIndicationRotateTextViewController;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.util.IndicationHelper;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.KeyguardIndicationTextView;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.AlarmTimeout;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.android.systemui.util.wakelock.SettableWakeLock;
import com.android.systemui.util.wakelock.WakeLock;
import com.android.wm.shell.R;
import com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle;
import java.util.Set;
import java.util.function.Consumer;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class KeyguardIndicationController {
    public final AccessibilityManager mAccessibilityManager;
    public String mAlignmentIndication;
    public final AlternateBouncerInteractor mAlternateBouncerInteractor;
    public final AuthController mAuthController;
    public final DelayableExecutor mBackgroundExecutor;
    public boolean mBatteryDefender;
    public final IBatteryStats mBatteryInfo;
    public int mBatteryLevel;
    public boolean mBatteryPresent = true;
    public Pair mBiometricErrorMessageToShowOnScreenOn;
    public CharSequence mBiometricMessage;
    public CharSequence mBiometricMessageFollowUp;
    public final BiometricMessageInteractor mBiometricMessageInteractor;
    public BiometricSourceType mBiometricMessageSource;
    public final BouncerMessageInteractor mBouncerMessageInteractor;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public AnonymousClass3 mBroadcastReceiver;
    public int mChargingSpeed;
    public long mChargingTimeRemaining;
    public int mChargingWattage;
    final Consumer mCoExAcquisitionMsgIdsToShowCallback;
    public Set mCoExFaceAcquisitionMsgIdsToShow;
    public final Context mContext;
    public final DeviceEntryFaceAuthInteractor mDeviceEntryFaceAuthInteractor;
    public final DeviceEntryFingerprintAuthInteractor mDeviceEntryFingerprintAuthInteractor;
    public final DevicePolicyManager mDevicePolicyManager;
    public final DockManager mDockManager;
    public boolean mDozing;
    public boolean mEnableBatteryDefender;
    public final DelayableExecutor mExecutor;
    public final FaceHelpMessageDeferral mFaceAcquiredMessageDeferral;
    public boolean mFaceLockedOutThisAuthSession;
    public final FalsingManager mFalsingManager;
    public final FeatureFlags mFeatureFlags;
    public boolean mForceIsDismissible;
    public final AnonymousClass2 mHandler;
    public final AlarmTimeout mHideBiometricMessageHandler;
    public final AlarmTimeout mHideTransientMessageHandler;
    public boolean mIncompatibleCharger;
    public ViewGroup mIndicationArea;
    public final IndicationHelper mIndicationHelper;
    public boolean mInited;
    public ColorStateList mInitialTextColorState;
    public boolean mIsActiveDreamLockscreenHosted;
    final Consumer mIsActiveDreamLockscreenHostedCallback;
    final Consumer mIsFingerprintEngagedCallback;
    public final KeyguardBypassController mKeyguardBypassController;
    public final KeyguardLogger mKeyguardLogger;
    public final AnonymousClass5 mKeyguardStateCallback;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final LockPatternUtils mLockPatternUtils;
    public KeyguardIndicationTextView mLockScreenIndicationView;
    public boolean mOrganizationOwnedDevice;
    public String mPersistentUnlockMessage;
    public boolean mPowerCharged;
    public boolean mPowerPluggedIn;
    public boolean mPowerPluggedInDock;
    public boolean mPowerPluggedInWired;
    public boolean mPowerPluggedInWireless;
    public KeyguardIndicationRotateTextViewController mRotateTextViewController;
    public final ScreenLifecycle mScreenLifecycle;
    public final AnonymousClass1 mScreenObserver;
    public StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public final StatusBarStateController mStatusBarStateController;
    public final AnonymousClass4 mStatusBarStateListener;
    public KeyguardIndicationTextView mTopIndicationView;
    public CharSequence mTransientIndication;
    public CharSequence mTrustAgentErrorMessage;
    public CharSequence mTrustGrantedIndication;
    public KeyguardUpdateMonitorCallback mUpdateMonitorCallback;
    public final UserManager mUserManager;
    public final UserTracker mUserTracker;
    public boolean mVisible;
    public final SettableWakeLock mWakeLock;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.KeyguardIndicationController$4, reason: invalid class name */
    public final class AnonymousClass4 implements StatusBarStateController.StateListener {
        public AnonymousClass4() {
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onDozingChanged(boolean z) {
            KeyguardIndicationController keyguardIndicationController = KeyguardIndicationController.this;
            if (keyguardIndicationController.mDozing == z) {
                return;
            }
            keyguardIndicationController.mDozing = z;
            if (z) {
                keyguardIndicationController.hideBiometricMessage();
            }
            keyguardIndicationController.updateDeviceEntryIndication(false);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class BaseKeyguardCallback extends KeyguardUpdateMonitorCallback {
        public final /* synthetic */ KeyguardIndicationControllerGoogle this$0;

        public BaseKeyguardCallback(KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle) {
            this.this$0 = keyguardIndicationControllerGoogle;
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricAcquired(BiometricSourceType biometricSourceType, int i) {
            if (biometricSourceType == BiometricSourceType.FACE) {
                KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
                if (i == 20) {
                    keyguardIndicationControllerGoogle.hideBiometricMessage();
                    keyguardIndicationControllerGoogle.mBiometricErrorMessageToShowOnScreenOn = null;
                }
                keyguardIndicationControllerGoogle.mFaceAcquiredMessageDeferral.processFrame(i);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricAuthFailed(BiometricSourceType biometricSourceType) {
            if (biometricSourceType == BiometricSourceType.FACE) {
                this.this$0.mFaceAcquiredMessageDeferral.reset$1();
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricAuthenticated(int i, BiometricSourceType biometricSourceType, boolean z) {
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            keyguardIndicationControllerGoogle.hideBiometricMessage();
            if (biometricSourceType == BiometricSourceType.FACE) {
                keyguardIndicationControllerGoogle.mFaceAcquiredMessageDeferral.reset$1();
                if (keyguardIndicationControllerGoogle.mKeyguardBypassController.canBypass()) {
                    return;
                }
                keyguardIndicationControllerGoogle.showActionToUnlock();
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricError(int i, String str, BiometricSourceType biometricSourceType) {
            BiometricSourceType biometricSourceType2 = BiometricSourceType.FACE;
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            if (biometricSourceType != biometricSourceType2) {
                BiometricSourceType biometricSourceType3 = BiometricSourceType.FINGERPRINT;
                if (biometricSourceType == biometricSourceType3) {
                    if (keyguardIndicationControllerGoogle.mIndicationHelper.shouldSuppressErrorMsg(biometricSourceType3, i)) {
                        keyguardIndicationControllerGoogle.mKeyguardLogger.logBiometricMessage("KIC suppressingFingerprintError", str, Integer.valueOf(i));
                        return;
                    } else {
                        keyguardIndicationControllerGoogle.showErrorMessageNowOrLater(str, null, biometricSourceType3);
                        return;
                    }
                }
                return;
            }
            CharSequence deferredMessage = keyguardIndicationControllerGoogle.mFaceAcquiredMessageDeferral.getDeferredMessage();
            keyguardIndicationControllerGoogle.mFaceAcquiredMessageDeferral.reset$1();
            boolean shouldSuppressErrorMsg = keyguardIndicationControllerGoogle.mIndicationHelper.shouldSuppressErrorMsg(biometricSourceType2, i);
            KeyguardLogger keyguardLogger = keyguardIndicationControllerGoogle.mKeyguardLogger;
            if (shouldSuppressErrorMsg) {
                keyguardLogger.logBiometricMessage("KIC suppressingFaceError", str, Integer.valueOf(i));
                return;
            }
            int i2 = R.string.keyguard_unlock;
            if (i == 3) {
                keyguardLogger.logBiometricMessage("deferred message after face auth timeout", String.valueOf(deferredMessage), null);
                if (keyguardIndicationControllerGoogle.canUnlockWithFingerprint()) {
                    if (deferredMessage == null || keyguardIndicationControllerGoogle.mStatusBarKeyguardViewManager.isBouncerShowing()) {
                        keyguardLogger.logBiometricMessage("skip showing FACE_ERROR_TIMEOUT due to co-ex logic", null, null);
                        return;
                    } else {
                        keyguardIndicationControllerGoogle.showBiometricMessage(deferredMessage, ((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.keyguard_suggest_fingerprint), biometricSourceType2, false);
                        return;
                    }
                }
                if (deferredMessage == null) {
                    keyguardIndicationControllerGoogle.showActionToUnlock();
                    return;
                } else {
                    keyguardIndicationControllerGoogle.mBouncerMessageInteractor.setFaceAcquisitionMessage(deferredMessage.toString());
                    keyguardIndicationControllerGoogle.showBiometricMessage(deferredMessage, ((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.keyguard_unlock), biometricSourceType2, false);
                    return;
                }
            }
            if (i != 7 && i != 9) {
                keyguardIndicationControllerGoogle.showErrorMessageNowOrLater(str, null, biometricSourceType2);
                return;
            }
            if (keyguardIndicationControllerGoogle.canUnlockWithFingerprint()) {
                i2 = R.string.keyguard_suggest_fingerprint;
            }
            String string = ((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(i2);
            if (!keyguardIndicationControllerGoogle.mFaceLockedOutThisAuthSession) {
                keyguardIndicationControllerGoogle.mFaceLockedOutThisAuthSession = true;
                keyguardIndicationControllerGoogle.showErrorMessageNowOrLater(str, string, biometricSourceType2);
            } else {
                UdfpsController udfpsController = keyguardIndicationControllerGoogle.mAuthController.mUdfpsController;
                if (udfpsController != null ? udfpsController.mOnFingerDown : false) {
                    return;
                }
                keyguardIndicationControllerGoogle.showErrorMessageNowOrLater(((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.keyguard_face_unlock_unavailable), string, biometricSourceType2);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricHelp(int i, String str, BiometricSourceType biometricSourceType) {
            BiometricSourceType biometricSourceType2 = BiometricSourceType.FACE;
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            if (biometricSourceType == biometricSourceType2) {
                keyguardIndicationControllerGoogle.mFaceAcquiredMessageDeferral.updateMessage(i, str);
                if (keyguardIndicationControllerGoogle.mFaceAcquiredMessageDeferral.messagesToDefer.contains(Integer.valueOf(i))) {
                    return;
                }
            }
            boolean z = biometricSourceType == biometricSourceType2 && i == -3;
            if (keyguardIndicationControllerGoogle.mKeyguardUpdateMonitor.isUnlockingWithBiometricAllowed(true) || z) {
                boolean z2 = (biometricSourceType != biometricSourceType2 || i == -2 || i == -3) ? false : true;
                boolean z3 = biometricSourceType == biometricSourceType2 && i == -2;
                KeyguardLogger keyguardLogger = keyguardIndicationControllerGoogle.mKeyguardLogger;
                if (z3 && keyguardIndicationControllerGoogle.mFaceLockedOutThisAuthSession) {
                    keyguardLogger.logBiometricMessage("skipped showing faceAuthFailed message due to lockout", str, Integer.valueOf(i));
                    return;
                }
                BiometricSourceType biometricSourceType3 = BiometricSourceType.FINGERPRINT;
                boolean z4 = biometricSourceType == biometricSourceType3 && i == -1;
                boolean canUnlockWithFingerprint = keyguardIndicationControllerGoogle.canUnlockWithFingerprint();
                boolean z5 = z2 && canUnlockWithFingerprint;
                if (z5 && !keyguardIndicationControllerGoogle.mCoExFaceAcquisitionMsgIdsToShow.contains(Integer.valueOf(i))) {
                    keyguardLogger.logBiometricMessage("skipped showing help message due to co-ex logic", str, Integer.valueOf(i));
                    return;
                }
                if (keyguardIndicationControllerGoogle.mStatusBarKeyguardViewManager.isBouncerShowing()) {
                    BouncerMessageInteractor bouncerMessageInteractor = keyguardIndicationControllerGoogle.mBouncerMessageInteractor;
                    if (biometricSourceType == biometricSourceType3 && !z4) {
                        bouncerMessageInteractor.repository.setMessage(BouncerMessageInteractorKt.access$defaultMessage(bouncerMessageInteractor.getCurrentSecurityMode(), str, ((Boolean) ((StateFlowImpl) bouncerMessageInteractor.isFingerprintAuthCurrentlyAllowedOnBouncer.$$delegate_0).getValue()).booleanValue()), biometricSourceType3);
                    } else if (z2) {
                        bouncerMessageInteractor.setFaceAcquisitionMessage(str);
                    }
                    keyguardIndicationControllerGoogle.mStatusBarKeyguardViewManager.setKeyguardMessage(str, keyguardIndicationControllerGoogle.mInitialTextColorState);
                    return;
                }
                if (keyguardIndicationControllerGoogle.mScreenLifecycle.mScreenState != 2) {
                    AnonymousClass2 anonymousClass2 = keyguardIndicationControllerGoogle.mHandler;
                    if (z3) {
                        anonymousClass2.sendMessageDelayed(anonymousClass2.obtainMessage(1), 1300L);
                        return;
                    } else {
                        keyguardIndicationControllerGoogle.mBiometricErrorMessageToShowOnScreenOn = new Pair(str, biometricSourceType);
                        anonymousClass2.sendMessageDelayed(anonymousClass2.obtainMessage(2), 1000L);
                        return;
                    }
                }
                if (z5 && i == 3) {
                    keyguardIndicationControllerGoogle.showBiometricMessage(str, ((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.keyguard_suggest_fingerprint), biometricSourceType, false);
                    return;
                }
                if (z3 && canUnlockWithFingerprint) {
                    keyguardIndicationControllerGoogle.showBiometricMessage(((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.keyguard_face_failed), ((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.keyguard_suggest_fingerprint), biometricSourceType, false);
                    return;
                }
                KeyguardUpdateMonitor keyguardUpdateMonitor = keyguardIndicationControllerGoogle.mKeyguardUpdateMonitor;
                if (z4 && keyguardUpdateMonitor.isCurrentUserUnlockedWithFace()) {
                    keyguardIndicationControllerGoogle.showBiometricMessage(((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.keyguard_face_successful_unlock), ((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.keyguard_unlock), null, true);
                    return;
                }
                if (z4 && keyguardUpdateMonitor.getUserHasTrust(keyguardIndicationControllerGoogle.getCurrentUser())) {
                    keyguardIndicationControllerGoogle.showBiometricMessage(keyguardIndicationControllerGoogle.getTrustGrantedIndication(), ((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.keyguard_unlock), null, true);
                } else if (z) {
                    keyguardIndicationControllerGoogle.showBiometricMessage(str, canUnlockWithFingerprint ? ((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.keyguard_suggest_fingerprint) : ((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.keyguard_unlock), biometricSourceType, false);
                } else {
                    keyguardIndicationControllerGoogle.showBiometricMessage(str, null, biometricSourceType, false);
                }
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onBiometricRunningStateChanged(BiometricSourceType biometricSourceType, boolean z) {
            if (z || biometricSourceType != BiometricSourceType.FACE) {
                return;
            }
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            keyguardIndicationControllerGoogle.showTrustAgentErrorMessage(keyguardIndicationControllerGoogle.mTrustAgentErrorMessage);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onForceIsDismissibleChanged(boolean z) {
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            keyguardIndicationControllerGoogle.mForceIsDismissible = z;
            keyguardIndicationControllerGoogle.updateDeviceEntryIndication(false);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onLockedOutStateChanged(BiometricSourceType biometricSourceType) {
            SystemUIDeviceEntryFaceAuthInteractor systemUIDeviceEntryFaceAuthInteractor;
            BiometricSourceType biometricSourceType2 = BiometricSourceType.FACE;
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            if (biometricSourceType == biometricSourceType2 && ((systemUIDeviceEntryFaceAuthInteractor = keyguardIndicationControllerGoogle.mKeyguardUpdateMonitor.mFaceAuthInteractor) == null || !((Boolean) systemUIDeviceEntryFaceAuthInteractor.isLockedOut.getValue()).booleanValue())) {
                keyguardIndicationControllerGoogle.mFaceLockedOutThisAuthSession = false;
            } else if (biometricSourceType == BiometricSourceType.FINGERPRINT) {
                keyguardIndicationControllerGoogle.mPersistentUnlockMessage = keyguardIndicationControllerGoogle.mKeyguardUpdateMonitor.isFingerprintLockedOut() ? ((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.keyguard_unlock) : "";
                keyguardIndicationControllerGoogle.updateDeviceEntryIndication(false);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onLogoutEnabledChanged() {
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            if (keyguardIndicationControllerGoogle.mVisible) {
                keyguardIndicationControllerGoogle.updateDeviceEntryIndication(false);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onRequireUnlockForNfc() {
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            keyguardIndicationControllerGoogle.mTransientIndication = ((KeyguardIndicationController) keyguardIndicationControllerGoogle).mContext.getString(R.string.require_unlock_for_nfc);
            AlarmTimeout alarmTimeout = keyguardIndicationControllerGoogle.mHideTransientMessageHandler;
            alarmTimeout.schedule(4100L, 2);
            keyguardIndicationControllerGoogle.updateTransient();
            alarmTimeout.schedule(4100L, 2);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onTimeChanged() {
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            if (keyguardIndicationControllerGoogle.mVisible) {
                keyguardIndicationControllerGoogle.updateDeviceEntryIndication(false);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onTrustAgentErrorMessage(CharSequence charSequence) {
            this.this$0.showTrustAgentErrorMessage(charSequence);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onTrustChanged(int i) {
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            if (keyguardIndicationControllerGoogle.getCurrentUser() == i) {
                keyguardIndicationControllerGoogle.updateDeviceEntryIndication(false);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onTrustGrantedForCurrentUser(boolean z, boolean z2, TrustGrantFlags trustGrantFlags, String str) {
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            if (str == null || (keyguardIndicationControllerGoogle.mGlobalSettings.getInt(0, "chip_all_watch_unlocks") == 0 && !z)) {
                keyguardIndicationControllerGoogle.mTrustGrantedIndication = str;
                keyguardIndicationControllerGoogle.updateDeviceEntryIndication(false);
            } else {
                keyguardIndicationControllerGoogle.mTrustGrantedIndication = "";
                keyguardIndicationControllerGoogle.updateDeviceEntryIndication(false);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onUserSwitchComplete(int i) {
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            if (keyguardIndicationControllerGoogle.mVisible) {
                keyguardIndicationControllerGoogle.updateDeviceEntryIndication(false);
            }
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onUserUnlocked() {
            KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.this$0;
            if (keyguardIndicationControllerGoogle.mVisible) {
                keyguardIndicationControllerGoogle.updateDeviceEntryIndication(false);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v11, types: [android.os.Handler, com.android.systemui.statusbar.KeyguardIndicationController$2] */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.android.systemui.statusbar.KeyguardIndicationController$5] */
    public KeyguardIndicationController(Context context, Looper looper, WakeLock.Builder builder, KeyguardStateController keyguardStateController, StatusBarStateController statusBarStateController, KeyguardUpdateMonitor keyguardUpdateMonitor, DockManager dockManager, BroadcastDispatcher broadcastDispatcher, DevicePolicyManager devicePolicyManager, IBatteryStats iBatteryStats, UserManager userManager, DelayableExecutor delayableExecutor, DelayableExecutor delayableExecutor2, FalsingManager falsingManager, AuthController authController, LockPatternUtils lockPatternUtils, ScreenLifecycle screenLifecycle, KeyguardBypassController keyguardBypassController, AccessibilityManager accessibilityManager, FaceHelpMessageDeferralFactory faceHelpMessageDeferralFactory, KeyguardLogger keyguardLogger, AlternateBouncerInteractor alternateBouncerInteractor, AlarmManager alarmManager, UserTracker userTracker, BouncerMessageInteractor bouncerMessageInteractor, FeatureFlags featureFlags, IndicationHelper indicationHelper, KeyguardInteractor keyguardInteractor, BiometricMessageInteractor biometricMessageInteractor, DeviceEntryFingerprintAuthInteractor deviceEntryFingerprintAuthInteractor, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor) {
        final int i = 0;
        this.mIsActiveDreamLockscreenHostedCallback = new Consumer(this) { // from class: com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda0
            public final /* synthetic */ KeyguardIndicationController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i2 = i;
                KeyguardIndicationController keyguardIndicationController = this.f$0;
                switch (i2) {
                    case 0:
                        Boolean bool = (Boolean) obj;
                        if (keyguardIndicationController.mIsActiveDreamLockscreenHosted != bool.booleanValue()) {
                            keyguardIndicationController.mIsActiveDreamLockscreenHosted = bool.booleanValue();
                            keyguardIndicationController.updateDeviceEntryIndication(false);
                            break;
                        }
                        break;
                    case 1:
                        keyguardIndicationController.mCoExFaceAcquisitionMsgIdsToShow = (Set) obj;
                        break;
                    default:
                        keyguardIndicationController.getClass();
                        if (!((Boolean) obj).booleanValue()) {
                            keyguardIndicationController.showTrustAgentErrorMessage(keyguardIndicationController.mTrustAgentErrorMessage);
                            break;
                        }
                        break;
                }
            }
        };
        final int i2 = 1;
        this.mCoExAcquisitionMsgIdsToShowCallback = new Consumer(this) { // from class: com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda0
            public final /* synthetic */ KeyguardIndicationController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i2;
                KeyguardIndicationController keyguardIndicationController = this.f$0;
                switch (i22) {
                    case 0:
                        Boolean bool = (Boolean) obj;
                        if (keyguardIndicationController.mIsActiveDreamLockscreenHosted != bool.booleanValue()) {
                            keyguardIndicationController.mIsActiveDreamLockscreenHosted = bool.booleanValue();
                            keyguardIndicationController.updateDeviceEntryIndication(false);
                            break;
                        }
                        break;
                    case 1:
                        keyguardIndicationController.mCoExFaceAcquisitionMsgIdsToShow = (Set) obj;
                        break;
                    default:
                        keyguardIndicationController.getClass();
                        if (!((Boolean) obj).booleanValue()) {
                            keyguardIndicationController.showTrustAgentErrorMessage(keyguardIndicationController.mTrustAgentErrorMessage);
                            break;
                        }
                        break;
                }
            }
        };
        final int i3 = 2;
        this.mIsFingerprintEngagedCallback = new Consumer(this) { // from class: com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda0
            public final /* synthetic */ KeyguardIndicationController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i3;
                KeyguardIndicationController keyguardIndicationController = this.f$0;
                switch (i22) {
                    case 0:
                        Boolean bool = (Boolean) obj;
                        if (keyguardIndicationController.mIsActiveDreamLockscreenHosted != bool.booleanValue()) {
                            keyguardIndicationController.mIsActiveDreamLockscreenHosted = bool.booleanValue();
                            keyguardIndicationController.updateDeviceEntryIndication(false);
                            break;
                        }
                        break;
                    case 1:
                        keyguardIndicationController.mCoExFaceAcquisitionMsgIdsToShow = (Set) obj;
                        break;
                    default:
                        keyguardIndicationController.getClass();
                        if (!((Boolean) obj).booleanValue()) {
                            keyguardIndicationController.showTrustAgentErrorMessage(keyguardIndicationController.mTrustAgentErrorMessage);
                            break;
                        }
                        break;
                }
            }
        };
        ScreenLifecycle.Observer observer = new ScreenLifecycle.Observer() { // from class: com.android.systemui.statusbar.KeyguardIndicationController.1
            @Override // com.android.systemui.keyguard.ScreenLifecycle.Observer
            public final void onScreenTurnedOn() {
                String str;
                KeyguardIndicationController keyguardIndicationController = KeyguardIndicationController.this;
                keyguardIndicationController.mHandler.removeMessages(2);
                if (keyguardIndicationController.mBiometricErrorMessageToShowOnScreenOn != null) {
                    if (keyguardIndicationController.mFaceLockedOutThisAuthSession) {
                        str = keyguardIndicationController.mContext.getString(keyguardIndicationController.canUnlockWithFingerprint() ? R.string.keyguard_suggest_fingerprint : R.string.keyguard_unlock);
                    } else {
                        str = null;
                    }
                    Pair pair = keyguardIndicationController.mBiometricErrorMessageToShowOnScreenOn;
                    keyguardIndicationController.showBiometricMessage((CharSequence) pair.first, str, (BiometricSourceType) pair.second, false);
                    keyguardIndicationController.mHideBiometricMessageHandler.schedule(4100L, 2);
                    keyguardIndicationController.mBiometricErrorMessageToShowOnScreenOn = null;
                }
            }
        };
        this.mStatusBarStateListener = new AnonymousClass4();
        this.mKeyguardStateCallback = new KeyguardStateController.Callback() { // from class: com.android.systemui.statusbar.KeyguardIndicationController.5
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardShowingChanged() {
                KeyguardIndicationController keyguardIndicationController = KeyguardIndicationController.this;
                if (((KeyguardStateControllerImpl) keyguardIndicationController.mKeyguardStateController).mShowing) {
                    keyguardIndicationController.updateDeviceEntryIndication(false);
                    return;
                }
                keyguardIndicationController.mKeyguardLogger.buffer.log("KeyguardIndication", LogLevel.DEBUG, "clear messages", null);
                KeyguardIndicationTextView keyguardIndicationTextView = keyguardIndicationController.mTopIndicationView;
                Animator animator = keyguardIndicationTextView.mLastAnimator;
                if (animator != null) {
                    animator.cancel();
                }
                keyguardIndicationTextView.mMessage = "";
                keyguardIndicationTextView.setText("");
                KeyguardIndicationRotateTextViewController keyguardIndicationRotateTextViewController = keyguardIndicationController.mRotateTextViewController;
                keyguardIndicationRotateTextViewController.mCurrIndicationType = -1;
                keyguardIndicationRotateTextViewController.mIndicationQueue.clear();
                keyguardIndicationRotateTextViewController.mIndicationMessages.clear();
                KeyguardIndicationTextView keyguardIndicationTextView2 = (KeyguardIndicationTextView) keyguardIndicationRotateTextViewController.mView;
                Animator animator2 = keyguardIndicationTextView2.mLastAnimator;
                if (animator2 != null) {
                    animator2.cancel();
                }
                keyguardIndicationTextView2.mMessage = "";
                keyguardIndicationTextView2.setText("");
                keyguardIndicationController.mTrustAgentErrorMessage = null;
            }

            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onUnlockedChanged() {
                KeyguardIndicationController keyguardIndicationController = KeyguardIndicationController.this;
                keyguardIndicationController.mTrustAgentErrorMessage = null;
                keyguardIndicationController.updateDeviceEntryIndication(false);
            }
        };
        this.mContext = context;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mDevicePolicyManager = devicePolicyManager;
        this.mKeyguardStateController = keyguardStateController;
        this.mStatusBarStateController = statusBarStateController;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mDockManager = dockManager;
        builder.mTag = "Doze:KeyguardIndication";
        this.mWakeLock = new SettableWakeLock(builder.build(), "KeyguardIndication");
        this.mBatteryInfo = iBatteryStats;
        this.mUserManager = userManager;
        this.mExecutor = delayableExecutor;
        this.mBackgroundExecutor = delayableExecutor2;
        this.mLockPatternUtils = lockPatternUtils;
        this.mAuthController = authController;
        this.mFalsingManager = falsingManager;
        this.mKeyguardBypassController = keyguardBypassController;
        this.mAccessibilityManager = accessibilityManager;
        this.mScreenLifecycle = screenLifecycle;
        this.mKeyguardLogger = keyguardLogger;
        screenLifecycle.mObservers.add(observer);
        this.mAlternateBouncerInteractor = alternateBouncerInteractor;
        this.mUserTracker = userTracker;
        this.mBouncerMessageInteractor = bouncerMessageInteractor;
        this.mFeatureFlags = featureFlags;
        this.mIndicationHelper = indicationHelper;
        this.mBiometricMessageInteractor = biometricMessageInteractor;
        this.mDeviceEntryFingerprintAuthInteractor = deviceEntryFingerprintAuthInteractor;
        this.mDeviceEntryFaceAuthInteractor = deviceEntryFaceAuthInteractor;
        this.mFaceAcquiredMessageDeferral = faceHelpMessageDeferralFactory.create();
        ?? r1 = new Handler(looper) { // from class: com.android.systemui.statusbar.KeyguardIndicationController.2
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                int i4 = message.what;
                KeyguardIndicationController keyguardIndicationController = KeyguardIndicationController.this;
                if (i4 == 1) {
                    keyguardIndicationController.showActionToUnlock();
                } else if (i4 == 2) {
                    keyguardIndicationController.mBiometricErrorMessageToShowOnScreenOn = null;
                }
            }
        };
        this.mHandler = r1;
        final int i4 = 0;
        this.mHideTransientMessageHandler = new AlarmTimeout(alarmManager, new AlarmManager.OnAlarmListener(this) { // from class: com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda3
            public final /* synthetic */ KeyguardIndicationController f$0;

            {
                this.f$0 = this;
            }

            @Override // android.app.AlarmManager.OnAlarmListener
            public final void onAlarm() {
                int i5 = i4;
                KeyguardIndicationController keyguardIndicationController = this.f$0;
                switch (i5) {
                    case 0:
                        if (keyguardIndicationController.mTransientIndication != null) {
                            keyguardIndicationController.mTransientIndication = null;
                            keyguardIndicationController.mHideTransientMessageHandler.cancel();
                            keyguardIndicationController.updateTransient();
                            break;
                        }
                        break;
                    default:
                        keyguardIndicationController.hideBiometricMessage();
                        break;
                }
            }
        }, "KeyguardIndication", r1);
        final int i5 = 1;
        this.mHideBiometricMessageHandler = new AlarmTimeout(alarmManager, new AlarmManager.OnAlarmListener(this) { // from class: com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda3
            public final /* synthetic */ KeyguardIndicationController f$0;

            {
                this.f$0 = this;
            }

            @Override // android.app.AlarmManager.OnAlarmListener
            public final void onAlarm() {
                int i52 = i5;
                KeyguardIndicationController keyguardIndicationController = this.f$0;
                switch (i52) {
                    case 0:
                        if (keyguardIndicationController.mTransientIndication != null) {
                            keyguardIndicationController.mTransientIndication = null;
                            keyguardIndicationController.mHideTransientMessageHandler.cancel();
                            keyguardIndicationController.updateTransient();
                            break;
                        }
                        break;
                    default:
                        keyguardIndicationController.hideBiometricMessage();
                        break;
                }
            }
        }, "KeyguardIndication", r1);
    }

    public final boolean canUnlockWithFingerprint() {
        int currentUser = getCurrentUser();
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
        if (keyguardUpdateMonitor.isUnlockWithFingerprintPossible(currentUser)) {
            keyguardUpdateMonitor.getClass();
            if (keyguardUpdateMonitor.isUnlockingWithBiometricAllowed(BiometricSourceType.FINGERPRINT)) {
                return true;
            }
        }
        return false;
    }

    public abstract String computePowerIndication();

    public final int getCurrentUser() {
        return ((UserTrackerImpl) this.mUserTracker).getUserId();
    }

    public String getTrustGrantedIndication() {
        CharSequence charSequence = this.mTrustGrantedIndication;
        return charSequence == null ? this.mContext.getString(R.string.keyguard_indication_trust_unlocked) : ((String) charSequence).toString();
    }

    public final void hideBiometricMessage() {
        if (this.mBiometricMessage == null && this.mBiometricMessageFollowUp == null) {
            return;
        }
        this.mBiometricMessage = null;
        this.mBiometricMessageFollowUp = null;
        this.mBiometricMessageSource = null;
        this.mHideBiometricMessageHandler.cancel();
        updateBiometricMessage();
    }

    public abstract void init();

    /* JADX WARN: Type inference failed for: r10v18, types: [com.android.systemui.statusbar.KeyguardIndicationController$3] */
    public final void setIndicationArea(ViewGroup viewGroup) {
        this.mIndicationArea = viewGroup;
        this.mTopIndicationView = (KeyguardIndicationTextView) viewGroup.findViewById(R.id.keyguard_indication_text);
        this.mLockScreenIndicationView = (KeyguardIndicationTextView) viewGroup.findViewById(R.id.keyguard_indication_text_bottom);
        KeyguardIndicationTextView keyguardIndicationTextView = this.mTopIndicationView;
        this.mInitialTextColorState = keyguardIndicationTextView != null ? keyguardIndicationTextView.getTextColors() : ColorStateList.valueOf(-1);
        KeyguardIndicationRotateTextViewController keyguardIndicationRotateTextViewController = this.mRotateTextViewController;
        if (keyguardIndicationRotateTextViewController != null) {
            keyguardIndicationRotateTextViewController.mView.removeOnAttachStateChangeListener(keyguardIndicationRotateTextViewController.mOnAttachStateListener);
            keyguardIndicationRotateTextViewController.onViewDetached();
        }
        this.mRotateTextViewController = new KeyguardIndicationRotateTextViewController(this.mLockScreenIndicationView, this.mExecutor, this.mStatusBarStateController, this.mKeyguardLogger, this.mFeatureFlags);
        updateDeviceEntryIndication(false);
        this.mOrganizationOwnedDevice = ((Boolean) DejankUtils.whitelistIpcs(new KeyguardIndicationController$$ExternalSyntheticLambda5(0, this))).booleanValue();
        updateDeviceEntryIndication(false);
        if (this.mBroadcastReceiver == null) {
            this.mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.systemui.statusbar.KeyguardIndicationController.3
                @Override // android.content.BroadcastReceiver
                public final void onReceive(Context context, Intent intent) {
                    KeyguardIndicationController keyguardIndicationController = KeyguardIndicationController.this;
                    keyguardIndicationController.getClass();
                    keyguardIndicationController.mOrganizationOwnedDevice = ((Boolean) DejankUtils.whitelistIpcs(new KeyguardIndicationController$$ExternalSyntheticLambda5(0, keyguardIndicationController))).booleanValue();
                    keyguardIndicationController.updateDeviceEntryIndication(false);
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.app.action.DEVICE_POLICY_MANAGER_STATE_CHANGED");
            intentFilter.addAction("android.intent.action.USER_REMOVED");
            this.mBroadcastDispatcher.registerReceiver(this.mBroadcastReceiver, intentFilter);
        }
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        this.mFeatureFlags.getClass();
        JavaAdapterKt.collectFlow(this.mIndicationArea, this.mBiometricMessageInteractor.coExFaceAcquisitionMsgIdsToShow, this.mCoExAcquisitionMsgIdsToShowCallback);
        JavaAdapterKt.collectFlow(this.mIndicationArea, this.mDeviceEntryFingerprintAuthInteractor.isEngaged, this.mIsFingerprintEngagedCallback);
    }

    public void setPowerPluggedIn(boolean z) {
        this.mPowerPluggedIn = z;
    }

    public final void setVisible(boolean z) {
        this.mVisible = z;
        this.mIndicationArea.setVisibility(z ? 0 : 8);
        AlarmTimeout alarmTimeout = this.mHideTransientMessageHandler;
        if (!z) {
            if (this.mTransientIndication != null) {
                this.mTransientIndication = null;
                alarmTimeout.cancel();
                updateTransient();
                return;
            }
            return;
        }
        if (!alarmTimeout.mScheduled && this.mTransientIndication != null) {
            this.mTransientIndication = null;
            alarmTimeout.cancel();
            updateTransient();
        }
        updateDeviceEntryIndication(false);
    }

    public final void showActionToUnlock() {
        boolean z = this.mDozing;
        KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
        if (!z || keyguardUpdateMonitor.getUserCanSkipBouncer(getCurrentUser())) {
            if (this.mStatusBarKeyguardViewManager.isBouncerShowing()) {
                if (this.mAlternateBouncerInteractor.isVisibleState() || !keyguardUpdateMonitor.isFaceEnabledAndEnrolled() || keyguardUpdateMonitor.getIsFaceAuthenticated()) {
                    return;
                }
                this.mStatusBarKeyguardViewManager.setKeyguardMessage((this.mAccessibilityManager.isEnabled() || this.mAccessibilityManager.isTouchExplorationEnabled()) ? this.mContext.getString(R.string.accesssibility_keyguard_retry) : this.mContext.getString(R.string.keyguard_retry), this.mInitialTextColorState);
                return;
            }
            if (!keyguardUpdateMonitor.getUserCanSkipBouncer(getCurrentUser())) {
                showBiometricMessage(this.mContext.getString(R.string.keyguard_unlock), null, null, false);
                return;
            }
            boolean isFaceAuthenticated = keyguardUpdateMonitor.getIsFaceAuthenticated();
            boolean isUdfpsSupported = keyguardUpdateMonitor.mAuthController.isUdfpsSupported();
            boolean z2 = this.mAccessibilityManager.isEnabled() || this.mAccessibilityManager.isTouchExplorationEnabled();
            if (isUdfpsSupported && isFaceAuthenticated) {
                if (z2) {
                    showBiometricMessage(this.mContext.getString(R.string.keyguard_face_successful_unlock), this.mContext.getString(R.string.keyguard_unlock), BiometricSourceType.FACE, true);
                    return;
                } else {
                    showBiometricMessage(this.mContext.getString(R.string.keyguard_face_successful_unlock), this.mContext.getString(R.string.keyguard_unlock_press), BiometricSourceType.FACE, true);
                    return;
                }
            }
            if (isFaceAuthenticated) {
                showBiometricMessage(this.mContext.getString(R.string.keyguard_face_successful_unlock), this.mContext.getString(R.string.keyguard_unlock), BiometricSourceType.FACE, true);
                return;
            }
            if (!isUdfpsSupported) {
                showBiometricMessage(this.mContext.getString(R.string.keyguard_unlock), null, null, true);
            } else if (z2) {
                showBiometricMessage(this.mContext.getString(R.string.keyguard_unlock), null, null, true);
            } else {
                showBiometricMessage(this.mContext.getString(R.string.keyguard_unlock_press), null, null, true);
            }
        }
    }

    public final void showBiometricMessage(CharSequence charSequence, CharSequence charSequence2, BiometricSourceType biometricSourceType, boolean z) {
        if (TextUtils.equals(charSequence, this.mBiometricMessage) && biometricSourceType == this.mBiometricMessageSource && TextUtils.equals(charSequence2, this.mBiometricMessageFollowUp)) {
            return;
        }
        if (!z && this.mBiometricMessageSource == BiometricSourceType.FINGERPRINT && biometricSourceType == BiometricSourceType.FACE) {
            this.mKeyguardLogger.logDropFaceMessage(charSequence, charSequence2);
            return;
        }
        if (this.mBiometricMessageSource == null || biometricSourceType != null) {
            this.mBiometricMessage = charSequence;
            this.mBiometricMessageFollowUp = charSequence2;
            this.mBiometricMessageSource = biometricSourceType;
        } else {
            this.mBiometricMessageFollowUp = charSequence;
        }
        removeMessages(1);
        this.mHideBiometricMessageHandler.schedule((TextUtils.isEmpty(this.mBiometricMessage) || TextUtils.isEmpty(this.mBiometricMessageFollowUp)) ? 4100L : 5200L, 2);
        updateBiometricMessage();
    }

    public final void showErrorMessageNowOrLater(String str, String str2, BiometricSourceType biometricSourceType) {
        if (this.mStatusBarKeyguardViewManager.isBouncerShowing()) {
            this.mStatusBarKeyguardViewManager.setKeyguardMessage(str, this.mInitialTextColorState);
        } else if (this.mScreenLifecycle.mScreenState == 2) {
            showBiometricMessage(str, str2, biometricSourceType, false);
        } else {
            this.mBiometricErrorMessageToShowOnScreenOn = new Pair(str, biometricSourceType);
        }
    }

    public final void showTransientIndication(int i) {
        this.mTransientIndication = this.mContext.getResources().getString(i);
        this.mHideTransientMessageHandler.schedule(4100L, 2);
        updateTransient();
    }

    public final void showTrustAgentErrorMessage(CharSequence charSequence) {
        if (charSequence == null) {
            this.mTrustAgentErrorMessage = null;
            return;
        }
        boolean booleanValue = ((Boolean) this.mDeviceEntryFingerprintAuthInteractor.isEngaged.getValue()).booleanValue();
        boolean isRunning = this.mDeviceEntryFaceAuthInteractor.isRunning();
        if (booleanValue || isRunning) {
            this.mKeyguardLogger.delayShowingTrustAgentError(charSequence, booleanValue, isRunning);
            this.mTrustAgentErrorMessage = charSequence;
        } else {
            this.mTrustAgentErrorMessage = null;
            showBiometricMessage(charSequence, null, null, false);
        }
    }

    public final void updateBiometricMessage() {
        if (this.mDozing) {
            updateDeviceEntryIndication(false);
            return;
        }
        if (TextUtils.isEmpty(this.mBiometricMessage)) {
            this.mRotateTextViewController.hideIndication(11);
        } else {
            KeyguardIndicationRotateTextViewController keyguardIndicationRotateTextViewController = this.mRotateTextViewController;
            CharSequence charSequence = this.mBiometricMessage;
            ColorStateList colorStateList = this.mInitialTextColorState;
            if (TextUtils.isEmpty(charSequence)) {
                throw new IllegalStateException("message or icon must be set");
            }
            if (colorStateList == null) {
                throw new IllegalStateException("text color must be set");
            }
            keyguardIndicationRotateTextViewController.updateIndication(11, new KeyguardIndication(charSequence, colorStateList, null, null, null, 2600L, Boolean.TRUE), true);
        }
        if (TextUtils.isEmpty(this.mBiometricMessageFollowUp)) {
            this.mRotateTextViewController.hideIndication(12);
            return;
        }
        KeyguardIndicationRotateTextViewController keyguardIndicationRotateTextViewController2 = this.mRotateTextViewController;
        CharSequence charSequence2 = this.mBiometricMessageFollowUp;
        ColorStateList colorStateList2 = this.mInitialTextColorState;
        if (TextUtils.isEmpty(charSequence2)) {
            throw new IllegalStateException("message or icon must be set");
        }
        if (colorStateList2 == null) {
            throw new IllegalStateException("text color must be set");
        }
        keyguardIndicationRotateTextViewController2.updateIndication(12, new KeyguardIndication(charSequence2, colorStateList2, null, null, null, 2600L, Boolean.FALSE), true);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00d3 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Type inference failed for: r15v5, types: [com.android.systemui.statusbar.KeyguardIndicationController$$ExternalSyntheticLambda8] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateDeviceEntryIndication(boolean r30) {
        /*
            Method dump skipped, instructions count: 926
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.KeyguardIndicationController.updateDeviceEntryIndication(boolean):void");
    }

    public final void updateTransient() {
        if (this.mDozing) {
            updateDeviceEntryIndication(false);
            return;
        }
        if (TextUtils.isEmpty(this.mTransientIndication)) {
            this.mRotateTextViewController.hideIndication(5);
            return;
        }
        KeyguardIndicationRotateTextViewController keyguardIndicationRotateTextViewController = this.mRotateTextViewController;
        CharSequence charSequence = this.mTransientIndication;
        ColorStateList colorStateList = keyguardIndicationRotateTextViewController.mInitialTextColorState;
        if (TextUtils.isEmpty(charSequence)) {
            throw new IllegalStateException("message or icon must be set");
        }
        if (colorStateList == null) {
            throw new IllegalStateException("text color must be set");
        }
        keyguardIndicationRotateTextViewController.updateIndication(5, new KeyguardIndication(charSequence, colorStateList, null, null, null, 2600L, Boolean.FALSE), true);
    }
}

package com.android.keyguard;

import android.app.AlertDialog;
import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.metrics.LogMaker;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.os.Trace;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Slog;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.AdminSecondaryLockScreenController;
import com.android.keyguard.KeyguardSecurityContainer;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardSecurityViewFlipperController;
import com.android.settingslib.Utils;
import com.android.settingslib.utils.ThreadUtils;
import com.android.systemui.DejankUtils;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.authentication.shared.model.AuthenticationMethodModel;
import com.android.systemui.biometrics.FaceAuthAccessibilityDelegate;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor$onPrimaryAuthLockedOut$callback$1;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractorKt;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.bouncer.shared.model.BouncerMessageModel;
import com.android.systemui.bouncer.shared.model.BouncerMessageStrings;
import com.android.systemui.bouncer.shared.model.Message;
import com.android.systemui.classifier.FalsingA11yDelegate;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.domain.interactor.KeyguardDismissTransitionInteractor;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowState;
import com.android.systemui.shared.system.SysUiStatsLog;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.wm.shell.R;
import dagger.Lazy;
import dagger.internal.DelegateFactory;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;
import javax.inject.Provider;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.math.MathKt;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSecurityContainerController extends ViewController implements KeyguardSecurityView {
    public static final boolean DEBUG = KeyguardConstants.DEBUG;
    public final AdminSecondaryLockScreenController mAdminSecondaryLockScreenController;
    public final AudioManager mAudioManager;
    public final BouncerMessageInteractor mBouncerMessageInteractor;
    public Runnable mCancelAction;
    public final ConfigurationController mConfigurationController;
    public final AnonymousClass5 mConfigurationListener;
    public KeyguardSecurityModel.SecurityMode mCurrentSecurityMode;
    public int mCurrentUser;
    public final DeviceEntryFaceAuthInteractor mDeviceEntryFaceAuthInteractor;
    public final DelegateFactory mDeviceEntryInteractor;
    public final DevicePolicyManager mDevicePolicyManager;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public ActivityStarter.OnDismissAction mDismissAction;
    public final FalsingA11yDelegate mFalsingA11yDelegate;
    public final FalsingCollector mFalsingCollector;
    public final FalsingManager mFalsingManager;
    public final FeatureFlags mFeatureFlags;
    public final GlobalSettings mGlobalSettings;
    final Gefingerpoken mGlobalTouchListener;
    public final AnonymousClass3 mKeyguardSecurityCallback;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitorCallback mKeyguardUpdateMonitorCallback;
    public int mLastOrientation;
    public final LockPatternUtils mLockPatternUtils;
    public final MetricsLogger mMetricsLogger;
    public final KeyguardSecurityContainerController$$ExternalSyntheticLambda8 mOnKeyListener;
    public final Lazy mPrimaryBouncerInteractor;
    public final KeyguardSecurityModel mSecurityModel;
    public final KeyguardSecurityViewFlipperController mSecurityViewFlipperController;
    public final SelectedUserInteractor mSelectedUserInteractor;
    public final SessionTracker mSessionTracker;
    public final AnonymousClass3 mSwipeListener;
    public final TelephonyManager mTelephonyManager;
    public int mTranslationY;
    public final UiEventLogger mUiEventLogger;
    public final KeyguardUpdateMonitor mUpdateMonitor;
    public final AnonymousClass1 mUserSwitchCallback;
    public final UserSwitcherController mUserSwitcherController;
    public final KeyguardViewMediator.AnonymousClass4 mViewMediatorCallback;
    public boolean mWillRunDismissFromKeyguard;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardSecurityContainerController$3, reason: invalid class name */
    public final class AnonymousClass3 implements KeyguardSecurityCallback {
        public /* synthetic */ AnonymousClass3() {
        }

        @Override // com.android.keyguard.KeyguardSecurityCallback
        public void dismiss(int i, KeyguardSecurityModel.SecurityMode securityMode) {
            dismiss(true, i, false, securityMode);
        }

        public void finish(int i) {
            KeyguardSecurityContainerController keyguardSecurityContainerController = KeyguardSecurityContainerController.this;
            boolean z = false;
            keyguardSecurityContainerController.mWillRunDismissFromKeyguard = false;
            ActivityStarter.OnDismissAction onDismissAction = keyguardSecurityContainerController.mDismissAction;
            if (onDismissAction != null) {
                z = onDismissAction.onDismiss();
                keyguardSecurityContainerController.mWillRunDismissFromKeyguard = keyguardSecurityContainerController.mDismissAction.willRunAnimationOnKeyguard();
                keyguardSecurityContainerController.mDismissAction = null;
                keyguardSecurityContainerController.mCancelAction = null;
            }
            KeyguardViewMediator.AnonymousClass4 anonymousClass4 = keyguardSecurityContainerController.mViewMediatorCallback;
            if (anonymousClass4 != null) {
                if (!z) {
                    KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                    if (i != keyguardViewMediator.mSelectedUserInteractor.getSelectedUserId()) {
                        return;
                    }
                    if (KeyguardViewMediator.DEBUG) {
                        Log.d("KeyguardViewMediator", "keyguardDone");
                    }
                    keyguardViewMediator.tryKeyguardDone();
                    return;
                }
                anonymousClass4.getClass();
                Trace.beginSection("KeyguardViewMediator.mViewMediatorCallback#keyguardDonePending");
                if (KeyguardViewMediator.DEBUG) {
                    Log.d("KeyguardViewMediator", "keyguardDonePending");
                }
                KeyguardViewMediator keyguardViewMediator2 = KeyguardViewMediator.this;
                if (i != keyguardViewMediator2.mSelectedUserInteractor.getSelectedUserId()) {
                    Trace.endSection();
                    return;
                }
                keyguardViewMediator2.mKeyguardDonePending = true;
                keyguardViewMediator2.mHideAnimationRun = true;
                keyguardViewMediator2.mHideAnimationRunning = true;
                ((StatusBarKeyguardViewManager) keyguardViewMediator2.mKeyguardViewControllerLazy.get()).startPreHideAnimation(keyguardViewMediator2.mHideAnimationFinishedRunnable);
                keyguardViewMediator2.mHandler.sendEmptyMessageDelayed(13, 3000L);
                Trace.endSection();
            }
        }

        @Override // com.android.keyguard.KeyguardSecurityCallback
        public void onAttemptLockoutStart(long j) {
            BouncerMessageInteractor bouncerMessageInteractor = KeyguardSecurityContainerController.this.mBouncerMessageInteractor;
            bouncerMessageInteractor.getClass();
            final BouncerMessageInteractor$onPrimaryAuthLockedOut$callback$1 bouncerMessageInteractor$onPrimaryAuthLockedOut$callback$1 = new BouncerMessageInteractor$onPrimaryAuthLockedOut$callback$1(bouncerMessageInteractor);
            final long j2 = j * 1000;
            bouncerMessageInteractor.countDownTimerUtil.getClass();
            new CountDownTimer(j2) { // from class: com.android.systemui.bouncer.domain.interactor.CountDownTimerUtil$startNewTimer$1
                @Override // android.os.CountDownTimer
                public final void onFinish() {
                    BouncerMessageInteractor bouncerMessageInteractor2 = bouncerMessageInteractor$onPrimaryAuthLockedOut$callback$1.this$0;
                    bouncerMessageInteractor2.repository.setMessage(bouncerMessageInteractor2.getDefaultMessage(), null);
                }

                @Override // android.os.CountDownTimer
                public final void onTick(long j3) {
                    BouncerMessageInteractor$onPrimaryAuthLockedOut$callback$1 bouncerMessageInteractor$onPrimaryAuthLockedOut$callback$12 = bouncerMessageInteractor$onPrimaryAuthLockedOut$callback$1;
                    int roundToInt = MathKt.roundToInt(j3 / 1000.0d);
                    Pair pair = BouncerMessageStrings.EmptyMessage;
                    BouncerMessageInteractor bouncerMessageInteractor2 = bouncerMessageInteractor$onPrimaryAuthLockedOut$callback$12.this$0;
                    AuthenticationMethodModel authModel = BouncerMessageInteractorKt.toAuthModel(bouncerMessageInteractor2.getCurrentSecurityMode());
                    BouncerMessageModel message = BouncerMessageInteractorKt.toMessage(authModel.equals(AuthenticationMethodModel.Pattern.INSTANCE) ? new Pair(Integer.valueOf(R.string.kg_too_many_failed_attempts_countdown), Integer.valueOf(R.string.kg_primary_auth_locked_out_pattern)) : authModel.equals(AuthenticationMethodModel.Password.INSTANCE) ? new Pair(Integer.valueOf(R.string.kg_too_many_failed_attempts_countdown), Integer.valueOf(R.string.kg_primary_auth_locked_out_password)) : authModel.equals(AuthenticationMethodModel.Pin.INSTANCE) ? new Pair(Integer.valueOf(R.string.kg_too_many_failed_attempts_countdown), Integer.valueOf(R.string.kg_primary_auth_locked_out_pin)) : BouncerMessageStrings.EmptyMessage);
                    Message message2 = message.message;
                    if (message2 != null) {
                        message2.formatterArgs = MapsKt.mutableMapOf(new Pair("count", Integer.valueOf(roundToInt)));
                    }
                    bouncerMessageInteractor2.repository.setMessage(message, null);
                }
            }.start();
        }

        @Override // com.android.keyguard.KeyguardSecurityCallback
        public void onCancelClicked() {
            ((StatusBarKeyguardViewManager) KeyguardViewMediator.this.mKeyguardViewControllerLazy.get()).getClass();
        }

        @Override // com.android.keyguard.KeyguardSecurityCallback
        public void onUserInput() {
            KeyguardSecurityContainerController keyguardSecurityContainerController = KeyguardSecurityContainerController.this;
            BouncerMessageInteractor bouncerMessageInteractor = keyguardSecurityContainerController.mBouncerMessageInteractor;
            bouncerMessageInteractor.repository.setMessage(bouncerMessageInteractor.getDefaultMessage(), null);
            keyguardSecurityContainerController.mDeviceEntryFaceAuthInteractor.onPrimaryBouncerUserInput();
        }

        @Override // com.android.keyguard.KeyguardSecurityCallback
        public void reportUnlockAttempt(int i, int i2, boolean z) {
            KeyguardSecurityContainerController keyguardSecurityContainerController = KeyguardSecurityContainerController.this;
            if (i2 == 0 && !z) {
                BouncerMessageInteractor bouncerMessageInteractor = keyguardSecurityContainerController.mBouncerMessageInteractor;
                bouncerMessageInteractor.getClass();
                Pair pair = BouncerMessageStrings.EmptyMessage;
                AuthenticationMethodModel authModel = BouncerMessageInteractorKt.toAuthModel(bouncerMessageInteractor.getCurrentSecurityMode());
                int i3 = ((Boolean) ((StateFlowImpl) bouncerMessageInteractor.isFingerprintAuthCurrentlyAllowedOnBouncer.$$delegate_0).getValue()).booleanValue() ? R.string.kg_wrong_input_try_fp_suggestion : 0;
                bouncerMessageInteractor.repository.setMessage(BouncerMessageInteractorKt.toMessage(authModel.equals(AuthenticationMethodModel.Pattern.INSTANCE) ? new Pair(Integer.valueOf(R.string.kg_wrong_pattern_try_again), Integer.valueOf(i3)) : authModel.equals(AuthenticationMethodModel.Password.INSTANCE) ? new Pair(Integer.valueOf(R.string.kg_wrong_password_try_again), Integer.valueOf(i3)) : authModel.equals(AuthenticationMethodModel.Pin.INSTANCE) ? new Pair(Integer.valueOf(R.string.kg_wrong_pin_try_again), Integer.valueOf(i3)) : BouncerMessageStrings.EmptyMessage), null);
            }
            KeyguardSecurityContainer.ViewMode viewMode = ((KeyguardSecurityContainer) keyguardSecurityContainerController.mView).mViewMode;
            int i4 = viewMode instanceof KeyguardSecurityContainer.SidedSecurityMode ? ((viewMode instanceof KeyguardSecurityContainer.SidedSecurityMode) && ((KeyguardSecurityContainer.SidedSecurityMode) viewMode).isLeftAligned()) ? 1 : 2 : 0;
            if (z) {
                SysUiStatsLog.write(64, 2, i4);
                keyguardSecurityContainerController.mLockPatternUtils.reportSuccessfulPasswordAttempt(i);
                ThreadUtils.postOnBackgroundThread(new KeyguardSecurityContainerController$3$$ExternalSyntheticLambda0());
            } else {
                SysUiStatsLog.write(64, 1, i4);
                int currentFailedPasswordAttempts = keyguardSecurityContainerController.mLockPatternUtils.getCurrentFailedPasswordAttempts(i) + 1;
                if (KeyguardSecurityContainerController.DEBUG) {
                    ExifInterface$$ExternalSyntheticOutline0.m("reportFailedPatternAttempt: #", "KeyguardSecurityView", currentFailedPasswordAttempts);
                }
                int maximumFailedPasswordsForWipe = keyguardSecurityContainerController.mDevicePolicyManager.getMaximumFailedPasswordsForWipe(null, i);
                int i5 = maximumFailedPasswordsForWipe > 0 ? maximumFailedPasswordsForWipe - currentFailedPasswordAttempts : Integer.MAX_VALUE;
                if (i5 < 5) {
                    keyguardSecurityContainerController.showMessageForFailedUnlockAttempt(i, keyguardSecurityContainerController.mDevicePolicyManager.getProfileWithMinimumFailedPasswordsForWipe(i), Integer.valueOf(keyguardSecurityContainerController.mSelectedUserInteractor.repository.mainUserId), i5, currentFailedPasswordAttempts);
                }
                keyguardSecurityContainerController.mLockPatternUtils.reportFailedPasswordAttempt(i);
                if (i2 > 0) {
                    keyguardSecurityContainerController.mLockPatternUtils.reportPasswordLockout(i2, i);
                }
            }
            keyguardSecurityContainerController.mMetricsLogger.write(new LogMaker(197).setType(z ? 10 : 11));
            keyguardSecurityContainerController.mUiEventLogger.log(z ? KeyguardSecurityContainer.BouncerUiEvent.BOUNCER_PASSWORD_SUCCESS : KeyguardSecurityContainer.BouncerUiEvent.BOUNCER_PASSWORD_FAILURE, keyguardSecurityContainerController.mSessionTracker.getSessionId(1));
        }

        @Override // com.android.keyguard.KeyguardSecurityCallback
        public void reset() {
            KeyguardViewMediator.this.resetStateLocked(true);
        }

        @Override // com.android.keyguard.KeyguardSecurityCallback
        public void showCurrentSecurityScreen() {
            KeyguardSecurityContainerController.this.showPrimarySecurityScreen();
        }

        @Override // com.android.keyguard.KeyguardSecurityCallback
        public void userActivity() {
            KeyguardViewMediator.this.userActivity();
        }

        public boolean dismiss(boolean z, int i, boolean z2, KeyguardSecurityModel.SecurityMode securityMode) {
            int i2;
            KeyguardSecurityContainer.BouncerUiEvent bouncerUiEvent;
            boolean z3;
            Intent intent;
            KeyguardSecurityContainerController keyguardSecurityContainerController = KeyguardSecurityContainerController.this;
            keyguardSecurityContainerController.getClass();
            if (KeyguardSecurityContainerController.DEBUG) {
                Log.d("KeyguardSecurityView", "showNextSecurityScreenOrFinish(" + z + ")");
            }
            if (securityMode != KeyguardSecurityModel.SecurityMode.Invalid && securityMode != keyguardSecurityContainerController.mCurrentSecurityMode) {
                Log.w("KeyguardSecurityView", "Attempted to invoke showNextSecurityScreenOrFinish with securityMode " + securityMode + ", but current mode is " + keyguardSecurityContainerController.mCurrentSecurityMode);
                return false;
            }
            KeyguardSecurityContainer.BouncerUiEvent bouncerUiEvent2 = KeyguardSecurityContainer.BouncerUiEvent.UNKNOWN;
            KeyguardUpdateMonitor keyguardUpdateMonitor = keyguardSecurityContainerController.mUpdateMonitor;
            if (keyguardUpdateMonitor.forceIsDismissibleIsKeepingDeviceUnlocked()) {
                bouncerUiEvent = bouncerUiEvent2;
                z3 = true;
                i2 = 5;
            } else if (keyguardUpdateMonitor.getUserHasTrust(i)) {
                bouncerUiEvent = KeyguardSecurityContainer.BouncerUiEvent.BOUNCER_DISMISS_EXTENDED_ACCESS;
                z3 = true;
                i2 = 3;
            } else {
                i2 = 2;
                if (keyguardUpdateMonitor.getUserUnlockedWithBiometric(i)) {
                    bouncerUiEvent = KeyguardSecurityContainer.BouncerUiEvent.BOUNCER_DISMISS_BIOMETRIC;
                } else {
                    KeyguardSecurityModel.SecurityMode securityMode2 = KeyguardSecurityModel.SecurityMode.None;
                    KeyguardSecurityModel.SecurityMode securityMode3 = keyguardSecurityContainerController.mCurrentSecurityMode;
                    KeyguardSecurityModel keyguardSecurityModel = keyguardSecurityContainerController.mSecurityModel;
                    if (securityMode2 == securityMode3) {
                        KeyguardSecurityModel.SecurityMode securityMode4 = keyguardSecurityModel.getSecurityMode(i);
                        if (securityMode2 == securityMode4) {
                            bouncerUiEvent = KeyguardSecurityContainer.BouncerUiEvent.BOUNCER_DISMISS_NONE_SECURITY;
                            i2 = 0;
                        } else {
                            keyguardSecurityContainerController.showSecurityScreen(securityMode4);
                        }
                    } else if (z) {
                        int ordinal = securityMode3.ordinal();
                        if (ordinal == 2 || ordinal == 3 || ordinal == 4) {
                            bouncerUiEvent = KeyguardSecurityContainer.BouncerUiEvent.BOUNCER_DISMISS_PASSWORD;
                            z3 = true;
                            i2 = 1;
                        } else if (ordinal == 5 || ordinal == 6) {
                            KeyguardSecurityModel.SecurityMode securityMode5 = keyguardSecurityModel.getSecurityMode(i);
                            boolean z4 = keyguardSecurityContainerController.mLockPatternUtils.isLockScreenDisabled(keyguardSecurityContainerController.mSelectedUserInteractor.getSelectedUserId()) || !((DeviceProvisionedControllerImpl) keyguardSecurityContainerController.mDeviceProvisionedController).isUserSetup(i);
                            if (securityMode5 == securityMode2 && z4) {
                                bouncerUiEvent = KeyguardSecurityContainer.BouncerUiEvent.BOUNCER_DISMISS_SIM;
                                z3 = true;
                                i2 = 4;
                            } else if (Arrays.asList(KeyguardSecurityModel.SecurityMode.SimPin, KeyguardSecurityModel.SecurityMode.SimPuk).contains(securityMode5)) {
                                keyguardSecurityContainerController.showSecurityScreen(securityMode5);
                            }
                        } else {
                            Objects.toString(keyguardSecurityContainerController.mCurrentSecurityMode);
                            keyguardSecurityContainerController.showPrimarySecurityScreen();
                        }
                    }
                    bouncerUiEvent = bouncerUiEvent2;
                    z3 = false;
                    i2 = -1;
                }
                z3 = true;
            }
            if (!z3 || z2 || (intent = (Intent) keyguardUpdateMonitor.mSecondaryLockscreenRequirement.get(Integer.valueOf(i))) == null) {
                if (i2 != -1) {
                    keyguardSecurityContainerController.mMetricsLogger.write(new LogMaker(197).setType(5).setSubtype(i2));
                }
                if (bouncerUiEvent != bouncerUiEvent2) {
                    keyguardSecurityContainerController.mUiEventLogger.log(bouncerUiEvent, keyguardSecurityContainerController.mSessionTracker.getSessionId(1));
                }
                if (z3) {
                    keyguardSecurityContainerController.mKeyguardSecurityCallback.finish(i);
                }
                return z3;
            }
            AdminSecondaryLockScreenController adminSecondaryLockScreenController = keyguardSecurityContainerController.mAdminSecondaryLockScreenController;
            if (adminSecondaryLockScreenController.mClient == null) {
                adminSecondaryLockScreenController.mContext.bindService(intent, adminSecondaryLockScreenController.mConnection, 1);
            }
            AdminSecondaryLockScreenController.AdminSecurityView adminSecurityView = adminSecondaryLockScreenController.mView;
            if (adminSecurityView.isAttachedToWindow()) {
                return false;
            }
            KeyguardSecurityContainer keyguardSecurityContainer = adminSecondaryLockScreenController.mParent;
            keyguardSecurityContainer.addView(adminSecurityView);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(keyguardSecurityContainer);
            constraintSet.connect(adminSecurityView.getId(), 3, 0, 3);
            constraintSet.connect(adminSecurityView.getId(), 6, 0, 6);
            constraintSet.connect(adminSecurityView.getId(), 7, 0, 7);
            constraintSet.connect(adminSecurityView.getId(), 4, 0, 4);
            constraintSet.constrainHeight(adminSecurityView.getId(), 0);
            constraintSet.constrainWidth(adminSecurityView.getId(), 0);
            constraintSet.applyTo(keyguardSecurityContainer);
            return false;
        }
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [com.android.keyguard.KeyguardSecurityContainerController$$ExternalSyntheticLambda8] */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.android.keyguard.KeyguardSecurityContainerController$1] */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.keyguard.KeyguardSecurityContainerController$5] */
    public KeyguardSecurityContainerController(KeyguardSecurityContainer keyguardSecurityContainer, AdminSecondaryLockScreenController.Factory factory, LockPatternUtils lockPatternUtils, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardSecurityModel keyguardSecurityModel, MetricsLogger metricsLogger, UiEventLogger uiEventLogger, KeyguardStateController keyguardStateController, KeyguardSecurityViewFlipperController keyguardSecurityViewFlipperController, ConfigurationController configurationController, FalsingCollector falsingCollector, FalsingManager falsingManager, UserSwitcherController userSwitcherController, FeatureFlags featureFlags, GlobalSettings globalSettings, SessionTracker sessionTracker, FalsingA11yDelegate falsingA11yDelegate, TelephonyManager telephonyManager, KeyguardViewMediator.AnonymousClass4 anonymousClass4, AudioManager audioManager, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, BouncerMessageInteractor bouncerMessageInteractor, Provider provider, SelectedUserInteractor selectedUserInteractor, DeviceProvisionedController deviceProvisionedController, FaceAuthAccessibilityDelegate faceAuthAccessibilityDelegate, DevicePolicyManager devicePolicyManager, KeyguardDismissTransitionInteractor keyguardDismissTransitionInteractor, Lazy lazy, DelegateFactory delegateFactory) {
        super(keyguardSecurityContainer);
        this.mOnKeyListener = new View.OnKeyListener() { // from class: com.android.keyguard.KeyguardSecurityContainerController$$ExternalSyntheticLambda8
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return KeyguardSecurityContainerController.this.interceptMediaKey(keyEvent);
            }
        };
        this.mCurrentSecurityMode = KeyguardSecurityModel.SecurityMode.Invalid;
        this.mCurrentUser = -10000;
        this.mUserSwitchCallback = new UserSwitcherController.UserSwitchCallback() { // from class: com.android.keyguard.KeyguardSecurityContainerController.1
            @Override // com.android.systemui.statusbar.policy.UserSwitcherController.UserSwitchCallback
            public final void onUserSwitched() {
                KeyguardSecurityContainerController keyguardSecurityContainerController = KeyguardSecurityContainerController.this;
                int i = keyguardSecurityContainerController.mCurrentUser;
                SelectedUserInteractor selectedUserInteractor2 = keyguardSecurityContainerController.mSelectedUserInteractor;
                if (i == selectedUserInteractor2.getSelectedUserId()) {
                    return;
                }
                keyguardSecurityContainerController.mCurrentUser = selectedUserInteractor2.getSelectedUserId();
                keyguardSecurityContainerController.showPrimarySecurityScreen();
            }
        };
        this.mGlobalTouchListener = new Gefingerpoken() { // from class: com.android.keyguard.KeyguardSecurityContainerController.2
            public MotionEvent mTouchDown;

            @Override // com.android.systemui.Gefingerpoken
            public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
                return false;
            }

            @Override // com.android.systemui.Gefingerpoken
            public final boolean onTouchEvent(MotionEvent motionEvent) {
                if (motionEvent.getActionMasked() != 0) {
                    if (this.mTouchDown == null) {
                        return false;
                    }
                    if (motionEvent.getActionMasked() != 1 && motionEvent.getActionMasked() != 3) {
                        return false;
                    }
                    this.mTouchDown.recycle();
                    this.mTouchDown = null;
                    return false;
                }
                KeyguardSecurityContainerController keyguardSecurityContainerController = KeyguardSecurityContainerController.this;
                KeyguardSecurityContainer.ViewMode viewMode = ((KeyguardSecurityContainer) keyguardSecurityContainerController.mView).mViewMode;
                if (viewMode instanceof KeyguardSecurityContainer.SidedSecurityMode) {
                    KeyguardSecurityContainer.SidedSecurityMode sidedSecurityMode = (KeyguardSecurityContainer.SidedSecurityMode) viewMode;
                    if (sidedSecurityMode.isTouchOnTheOtherSideOfSecurity(motionEvent, sidedSecurityMode.isLeftAligned())) {
                        keyguardSecurityContainerController.mFalsingCollector.avoidGesture();
                    }
                }
                MotionEvent motionEvent2 = this.mTouchDown;
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                    this.mTouchDown = null;
                }
                this.mTouchDown = MotionEvent.obtain(motionEvent);
                return false;
            }
        };
        AnonymousClass3 anonymousClass3 = new AnonymousClass3();
        this.mKeyguardSecurityCallback = anonymousClass3;
        this.mSwipeListener = new AnonymousClass3();
        this.mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.keyguard.KeyguardSecurityContainerController.5
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                KeyguardSecurityContainerController.this.configureMode();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onDensityOrFontScaleChanged() {
                ((KeyguardSecurityContainer) KeyguardSecurityContainerController.this.mView).mViewMode.onDensityOrFontScaleChanged();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onOrientationChanged() {
                FeatureFlags featureFlags2 = KeyguardSecurityContainerController.this.mFeatureFlags;
                UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
                featureFlags2.getClass();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                KeyguardSecurityContainer keyguardSecurityContainer2 = (KeyguardSecurityContainer) KeyguardSecurityContainerController.this.mView;
                keyguardSecurityContainer2.mViewMode.reloadColors();
                keyguardSecurityContainer2.setBackgroundColor(Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorSurfaceDim, 0, keyguardSecurityContainer2.getContext()));
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onUiModeChanged() {
                KeyguardSecurityContainer keyguardSecurityContainer2 = (KeyguardSecurityContainer) KeyguardSecurityContainerController.this.mView;
                keyguardSecurityContainer2.mViewMode.reloadColors();
                keyguardSecurityContainer2.setBackgroundColor(Utils.getColorAttrDefaultColor(android.R.^attr-private.materialColorSurfaceDim, 0, keyguardSecurityContainer2.getContext()));
            }
        };
        this.mKeyguardUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.keyguard.KeyguardSecurityContainerController.6
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onDevicePolicyManagerStateChanged() {
                KeyguardSecurityContainerController.this.showPrimarySecurityScreen();
            }

            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onTrustGrantedForCurrentUser(boolean z, boolean z2, TrustGrantFlags trustGrantFlags, String str) {
                KeyguardSecurityContainerController keyguardSecurityContainerController = KeyguardSecurityContainerController.this;
                if (z) {
                    if (!((KeyguardSecurityContainer) keyguardSecurityContainerController.mView).isVisibleToUser()) {
                        Log.i("KeyguardSecurityView", "TrustAgent dismissed Keyguard.");
                    }
                    keyguardSecurityContainerController.mKeyguardSecurityCallback.dismiss(false, keyguardSecurityContainerController.mSelectedUserInteractor.getSelectedUserId(), false, KeyguardSecurityModel.SecurityMode.Invalid);
                } else {
                    if ((trustGrantFlags.mFlags & 1) == 0 && !trustGrantFlags.dismissKeyguardRequested()) {
                        return;
                    }
                    KeyguardViewMediator keyguardViewMediator = KeyguardViewMediator.this;
                    keyguardViewMediator.playSound(keyguardViewMediator.mTrustedSoundId);
                }
            }
        };
        keyguardSecurityContainer.setAccessibilityDelegate(faceAuthAccessibilityDelegate);
        this.mLockPatternUtils = lockPatternUtils;
        this.mUpdateMonitor = keyguardUpdateMonitor;
        this.mSecurityModel = keyguardSecurityModel;
        this.mMetricsLogger = metricsLogger;
        this.mUiEventLogger = uiEventLogger;
        this.mKeyguardStateController = keyguardStateController;
        this.mSecurityViewFlipperController = keyguardSecurityViewFlipperController;
        this.mAdminSecondaryLockScreenController = new AdminSecondaryLockScreenController(factory.mContext, factory.mParent, factory.mUpdateMonitor, anonymousClass3, factory.mHandler, factory.mSelectedUserInteractor);
        this.mConfigurationController = configurationController;
        this.mLastOrientation = keyguardSecurityContainer.getResources().getConfiguration().orientation;
        this.mFalsingCollector = falsingCollector;
        this.mFalsingManager = falsingManager;
        this.mUserSwitcherController = userSwitcherController;
        this.mFeatureFlags = featureFlags;
        this.mGlobalSettings = globalSettings;
        this.mSessionTracker = sessionTracker;
        this.mFalsingA11yDelegate = falsingA11yDelegate;
        this.mTelephonyManager = telephonyManager;
        this.mViewMediatorCallback = anonymousClass4;
        this.mAudioManager = audioManager;
        this.mDeviceEntryFaceAuthInteractor = deviceEntryFaceAuthInteractor;
        this.mBouncerMessageInteractor = bouncerMessageInteractor;
        this.mSelectedUserInteractor = selectedUserInteractor;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mPrimaryBouncerInteractor = lazy;
        this.mDevicePolicyManager = devicePolicyManager;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.keyguard.KeyguardSecurityContainer$UserSwitcherViewMode$$ExternalSyntheticLambda0] */
    public final void configureMode() {
        int i;
        KeyguardSecurityModel.SecurityMode securityMode = this.mCurrentSecurityMode;
        int i2 = 0;
        boolean z = securityMode == KeyguardSecurityModel.SecurityMode.SimPin || securityMode == KeyguardSecurityModel.SecurityMode.SimPuk;
        if (!this.mView.getContext().getResources().getBoolean(R.bool.config_enableBouncerUserSwitcher) || z) {
            int ordinal = this.mCurrentSecurityMode.ordinal();
            i = (ordinal == 2 || ordinal == 4 || ordinal == 5 || ordinal == 6) ? this.mView.getResources().getBoolean(R.bool.can_use_one_handed_bouncer) : false ? 1 : 0;
        } else {
            i = 2;
        }
        KeyguardSecurityContainer keyguardSecurityContainer = (KeyguardSecurityContainer) this.mView;
        KeyguardSecurityContainerController$$ExternalSyntheticLambda5 keyguardSecurityContainerController$$ExternalSyntheticLambda5 = new KeyguardSecurityContainerController$$ExternalSyntheticLambda5(i2, this);
        if (keyguardSecurityContainer.mCurrentMode == i) {
            return;
        }
        Log.i("KeyguardSecurityView", "Switching mode from " + KeyguardSecurityContainer.modeToString(keyguardSecurityContainer.mCurrentMode) + " to " + KeyguardSecurityContainer.modeToString(i));
        keyguardSecurityContainer.mCurrentMode = i;
        keyguardSecurityContainer.mViewMode.onDestroy();
        if (i == 1) {
            keyguardSecurityContainer.mViewMode = new KeyguardSecurityContainer.OneHandedViewMode();
        } else if (i != 2) {
            keyguardSecurityContainer.mViewMode = new KeyguardSecurityContainer.DefaultViewMode();
        } else {
            final KeyguardSecurityContainer.UserSwitcherViewMode userSwitcherViewMode = new KeyguardSecurityContainer.UserSwitcherViewMode();
            userSwitcherViewMode.mUserSwitchCallback = new UserSwitcherController.UserSwitchCallback() { // from class: com.android.keyguard.KeyguardSecurityContainer$UserSwitcherViewMode$$ExternalSyntheticLambda0
                @Override // com.android.systemui.statusbar.policy.UserSwitcherController.UserSwitchCallback
                public final void onUserSwitched() {
                    KeyguardSecurityContainer.UserSwitcherViewMode.this.setupUserSwitcher();
                }
            };
            userSwitcherViewMode.mUserSwitcherCallback = keyguardSecurityContainerController$$ExternalSyntheticLambda5;
            keyguardSecurityContainer.mViewMode = userSwitcherViewMode;
        }
        GlobalSettings globalSettings = this.mGlobalSettings;
        keyguardSecurityContainer.mGlobalSettings = globalSettings;
        FalsingManager falsingManager = this.mFalsingManager;
        keyguardSecurityContainer.mFalsingManager = falsingManager;
        FalsingA11yDelegate falsingA11yDelegate = this.mFalsingA11yDelegate;
        keyguardSecurityContainer.mFalsingA11yDelegate = falsingA11yDelegate;
        UserSwitcherController userSwitcherController = this.mUserSwitcherController;
        keyguardSecurityContainer.mUserSwitcherController = userSwitcherController;
        KeyguardSecurityViewFlipper keyguardSecurityViewFlipper = keyguardSecurityContainer.mSecurityViewFlipper;
        if (keyguardSecurityViewFlipper == null || globalSettings == null || falsingManager == null || userSwitcherController == null) {
            return;
        }
        keyguardSecurityContainer.mViewMode.init(keyguardSecurityContainer, globalSettings, keyguardSecurityViewFlipper, falsingManager, userSwitcherController, falsingA11yDelegate);
    }

    public final void getCurrentSecurityController(KeyguardSecurityViewFlipperController.OnViewInflatedCallback onViewInflatedCallback) {
        this.mSecurityViewFlipperController.getSecurityView(this.mCurrentSecurityMode, this.mKeyguardSecurityCallback, onViewInflatedCallback);
    }

    public final CharSequence getTitle() {
        KeyguardInputView securityView = ((KeyguardSecurityContainer) this.mView).mSecurityViewFlipper.getSecurityView();
        return securityView != null ? securityView.getTitle() : "";
    }

    public final boolean interceptMediaKey(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyEvent.getAction() == 0) {
            if (keyCode != 79 && keyCode != 130 && keyCode != 222) {
                if (keyCode != 126 && keyCode != 127) {
                    switch (keyCode) {
                    }
                }
                TelephonyManager telephonyManager = this.mTelephonyManager;
                return (telephonyManager == null || telephonyManager.getCallState() == 0) ? false : true;
            }
            this.mAudioManager.dispatchMediaKeyEvent(keyEvent);
            return true;
        }
        if (keyEvent.getAction() == 1) {
            if (keyCode != 79 && keyCode != 130 && keyCode != 222 && keyCode != 126 && keyCode != 127) {
                switch (keyCode) {
                }
            }
            this.mAudioManager.dispatchMediaKeyEvent(keyEvent);
            return true;
        }
        return false;
    }

    @Override // com.android.keyguard.KeyguardSecurityView
    public final boolean needsInput() {
        throw null;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        this.mSecurityViewFlipperController.init$9();
        updateResources();
        configureMode();
    }

    public final void onPause() {
        if (DEBUG) {
            Log.d("KeyguardSecurityView", "screen off, instance " + Integer.toHexString(hashCode()) + " at " + SystemClock.uptimeMillis());
        }
        showPrimarySecurityScreen();
        this.mAdminSecondaryLockScreenController.hide();
        if (this.mCurrentSecurityMode != KeyguardSecurityModel.SecurityMode.None) {
            getCurrentSecurityController(new KeyguardSecurityContainerController$$ExternalSyntheticLambda3(1));
        }
        KeyguardSecurityContainer keyguardSecurityContainer = (KeyguardSecurityContainer) this.mView;
        AlertDialog alertDialog = keyguardSecurityContainer.mAlertDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            keyguardSecurityContainer.mAlertDialog = null;
        }
        keyguardSecurityContainer.mSecurityViewFlipper.setWindowInsetsAnimationCallback(null);
        keyguardSecurityContainer.mViewMode.reset();
        ((KeyguardSecurityContainer) this.mView).clearFocus();
    }

    public final void onResume(int i) {
        if (DEBUG) {
            Log.d("KeyguardSecurityView", "screen on, instance " + Integer.toHexString(hashCode()));
        }
        ((KeyguardSecurityContainer) this.mView).clearFocus();
        ((KeyguardSecurityContainer) this.mView).clearAccessibilityFocus();
        ((KeyguardSecurityContainer) this.mView).requestFocus();
        ((KeyguardSecurityContainer) this.mView).requestAccessibilityFocus();
        KeyguardSecurityModel.SecurityMode securityMode = this.mCurrentSecurityMode;
        KeyguardSecurityModel.SecurityMode securityMode2 = KeyguardSecurityModel.SecurityMode.None;
        if (securityMode != securityMode2) {
            KeyguardSecurityContainer.ViewMode viewMode = ((KeyguardSecurityContainer) this.mView).mViewMode;
            SysUiStatsLog.write(63, viewMode instanceof KeyguardSecurityContainer.SidedSecurityMode ? ((viewMode instanceof KeyguardSecurityContainer.SidedSecurityMode) && ((KeyguardSecurityContainer.SidedSecurityMode) viewMode).isLeftAligned()) ? 3 : 4 : 2);
            getCurrentSecurityController(new KeyguardSecurityContainerController$$ExternalSyntheticLambda3(2));
        }
        KeyguardSecurityContainer keyguardSecurityContainer = (KeyguardSecurityContainer) this.mView;
        KeyguardSecurityModel.SecurityMode securityMode3 = this.mSecurityModel.getSecurityMode(this.mSelectedUserInteractor.getSelectedUserId());
        boolean z = ((KeyguardStateControllerImpl) this.mKeyguardStateController).mFaceEnrolledAndEnabled;
        keyguardSecurityContainer.mSecurityViewFlipper.setWindowInsetsAnimationCallback(keyguardSecurityContainer.mWindowInsetsAnimationCallback);
        keyguardSecurityContainer.mSwipeUpToRetry = (!z || securityMode3 == KeyguardSecurityModel.SecurityMode.SimPin || securityMode3 == KeyguardSecurityModel.SecurityMode.SimPuk || securityMode3 == securityMode2) ? false : true;
    }

    @Override // com.android.keyguard.KeyguardSecurityView
    public final void onStartingToHide() {
        if (this.mCurrentSecurityMode != KeyguardSecurityModel.SecurityMode.None) {
            getCurrentSecurityController(new KeyguardSecurityContainerController$$ExternalSyntheticLambda3(4));
        }
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        this.mUpdateMonitor.registerCallback(this.mKeyguardUpdateMonitorCallback);
        KeyguardSecurityContainer keyguardSecurityContainer = (KeyguardSecurityContainer) this.mView;
        keyguardSecurityContainer.mSwipeListener = this.mSwipeListener;
        keyguardSecurityContainer.mMotionEventListeners.add(this.mGlobalTouchListener);
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this.mConfigurationListener);
        this.mUserSwitcherController.addUserSwitchCallback(this.mUserSwitchCallback);
        KeyguardSecurityContainer keyguardSecurityContainer2 = (KeyguardSecurityContainer) this.mView;
        KeyguardViewMediator.AnonymousClass4 anonymousClass4 = this.mViewMediatorCallback;
        keyguardSecurityContainer2.mViewMediatorCallback = anonymousClass4;
        NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) ((StatusBarKeyguardViewManager) KeyguardViewMediator.this.mKeyguardViewControllerLazy.get()).mNotificationShadeWindowController;
        NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
        notificationShadeWindowState.keyguardNeedsInput = false;
        notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
        ((KeyguardSecurityContainer) this.mView).setOnKeyListener(this.mOnKeyListener);
        showPrimarySecurityScreen();
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.mUpdateMonitor.removeCallback(this.mKeyguardUpdateMonitorCallback);
        ((ConfigurationControllerImpl) this.mConfigurationController).removeCallback(this.mConfigurationListener);
        KeyguardSecurityContainer keyguardSecurityContainer = (KeyguardSecurityContainer) this.mView;
        keyguardSecurityContainer.mMotionEventListeners.remove(this.mGlobalTouchListener);
        AnonymousClass1 anonymousClass1 = this.mUserSwitchCallback;
        UserSwitcherController userSwitcherController = this.mUserSwitcherController;
        UserSwitcherInteractor.UserCallback userCallback = (UserSwitcherInteractor.UserCallback) userSwitcherController.callbackCompatMap.remove(anonymousClass1);
        if (userCallback != null) {
            userSwitcherController.getMUserSwitcherInteractor().removeCallback(userCallback);
        }
    }

    public void showMessageForFailedUnlockAttempt(int i, int i2, Integer num, int i3, int i4) {
        int i5 = 1;
        if (i2 == i) {
            if (i2 != (num != null ? num.intValue() : 0)) {
                i5 = 3;
            }
        } else if (i2 != -10000) {
            i5 = 2;
        }
        if (i3 > 0) {
            ((KeyguardSecurityContainer) this.mView).showAlmostAtWipeDialog(i4, i3, i5);
            return;
        }
        Slog.i("KeyguardSecurityView", "Too many unlock attempts; user " + i2 + " will be wiped!");
        ((KeyguardSecurityContainer) this.mView).showWipeDialog(i4, i5);
    }

    public final void showPrimarySecurityScreen() {
        if (DEBUG) {
            Log.d("KeyguardSecurityView", "show()");
        }
        KeyguardSecurityModel.SecurityMode securityMode = (KeyguardSecurityModel.SecurityMode) DejankUtils.whitelistIpcs(new Supplier() { // from class: com.android.keyguard.KeyguardSecurityContainerController$$ExternalSyntheticLambda7
            @Override // java.util.function.Supplier
            public final Object get() {
                KeyguardSecurityContainerController keyguardSecurityContainerController = KeyguardSecurityContainerController.this;
                return keyguardSecurityContainerController.mSecurityModel.getSecurityMode(keyguardSecurityContainerController.mSelectedUserInteractor.getSelectedUserId());
            }
        });
        StateFlowImpl stateFlowImpl = ((PrimaryBouncerInteractor) this.mPrimaryBouncerInteractor.get()).repository._lastShownSecurityMode;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, securityMode);
        showSecurityScreen(securityMode);
    }

    public void showSecurityScreen(KeyguardSecurityModel.SecurityMode securityMode) {
        if (DEBUG) {
            Log.d("KeyguardSecurityView", "showSecurityScreen(" + securityMode + ")");
        }
        if (securityMode == KeyguardSecurityModel.SecurityMode.Invalid || securityMode == this.mCurrentSecurityMode) {
            return;
        }
        getCurrentSecurityController(new KeyguardSecurityContainerController$$ExternalSyntheticLambda3(0));
        this.mCurrentSecurityMode = securityMode;
        getCurrentSecurityController(new KeyguardSecurityContainerController$$ExternalSyntheticLambda5(this, securityMode));
    }

    public final boolean startDisappearAnimation(Runnable runnable) {
        KeyguardSecurityModel.SecurityMode securityMode = this.mCurrentSecurityMode;
        if (securityMode != KeyguardSecurityModel.SecurityMode.None) {
            KeyguardSecurityContainer keyguardSecurityContainer = (KeyguardSecurityContainer) this.mView;
            keyguardSecurityContainer.mDisappearAnimRunning = true;
            if (securityMode == KeyguardSecurityModel.SecurityMode.Password && (keyguardSecurityContainer.mSecurityViewFlipper.getSecurityView() instanceof KeyguardPasswordView)) {
                ((KeyguardPasswordView) keyguardSecurityContainer.mSecurityViewFlipper.getSecurityView()).mDisappearAnimationListener = new KeyguardSecurityContainer$$ExternalSyntheticLambda0(keyguardSecurityContainer);
            } else {
                keyguardSecurityContainer.mViewMode.startDisappearAnimation(securityMode);
            }
            getCurrentSecurityController(new KeyguardSecurityContainerController$$ExternalSyntheticLambda5(2, runnable));
        }
        return true;
    }

    public final void updateResources() {
        Resources resources = ((KeyguardSecurityContainer) this.mView).getResources();
        int integer = resources.getBoolean(R.bool.can_use_one_handed_bouncer) ? resources.getInteger(R.integer.keyguard_host_view_one_handed_gravity) : resources.getInteger(R.integer.keyguard_host_view_gravity);
        this.mTranslationY = resources.getDimensionPixelSize(R.dimen.keyguard_host_view_translation_y);
        if (((KeyguardSecurityContainer) this.mView).getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) ((KeyguardSecurityContainer) this.mView).getLayoutParams();
            if (layoutParams.gravity != integer) {
                layoutParams.gravity = integer;
                ((KeyguardSecurityContainer) this.mView).setLayoutParams(layoutParams);
            }
        }
        int i = this.mView.getResources().getConfiguration().orientation;
        if (i != this.mLastOrientation) {
            this.mLastOrientation = i;
            configureMode();
        }
    }
}

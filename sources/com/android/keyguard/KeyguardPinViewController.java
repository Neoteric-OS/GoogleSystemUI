package com.android.keyguard;

import android.view.View;
import android.view.animation.Interpolator;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardInputView;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.domain.interactor.KeyguardKeyboardInteractor;
import com.android.settingslib.animation.AppearAnimationUtils;
import com.android.settingslib.animation.DisappearAnimationUtils;
import com.android.systemui.bouncer.ui.helper.BouncerHapticPlayer;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DevicePostureControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardPinViewController extends KeyguardPinBasedInputViewController {
    public final NumPadButton mBackspaceKey;
    public boolean mDisabledAutoConfirmation;
    public final FeatureFlags mFeatureFlags;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final LockPatternUtils mLockPatternUtils;
    public final View mOkButton;
    public final long mPinLength;
    public final KeyguardPinViewController$$ExternalSyntheticLambda0 mPostureCallback;
    public final DevicePostureController mPostureController;
    public final UiEventLogger mUiEventLogger;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class PinBouncerUiEvent implements UiEventLogger.UiEventEnum {
        public static final /* synthetic */ PinBouncerUiEvent[] $VALUES;
        public static final PinBouncerUiEvent ATTEMPT_UNLOCK_WITH_AUTO_CONFIRM_FEATURE;
        private final int mId = 1547;

        static {
            PinBouncerUiEvent pinBouncerUiEvent = new PinBouncerUiEvent();
            ATTEMPT_UNLOCK_WITH_AUTO_CONFIRM_FEATURE = pinBouncerUiEvent;
            $VALUES = new PinBouncerUiEvent[]{pinBouncerUiEvent};
        }

        public static PinBouncerUiEvent valueOf(String str) {
            return (PinBouncerUiEvent) Enum.valueOf(PinBouncerUiEvent.class, str);
        }

        public static PinBouncerUiEvent[] values() {
            return (PinBouncerUiEvent[]) $VALUES.clone();
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.keyguard.KeyguardPinViewController$$ExternalSyntheticLambda0] */
    public KeyguardPinViewController(KeyguardPINView keyguardPINView, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardSecurityModel.SecurityMode securityMode, LockPatternUtils lockPatternUtils, KeyguardSecurityCallback keyguardSecurityCallback, KeyguardMessageAreaController.Factory factory, LatencyTracker latencyTracker, LiftToActivateListener liftToActivateListener, EmergencyButtonController emergencyButtonController, FalsingCollector falsingCollector, DevicePostureController devicePostureController, FeatureFlags featureFlags, SelectedUserInteractor selectedUserInteractor, UiEventLogger uiEventLogger, KeyguardKeyboardInteractor keyguardKeyboardInteractor, BouncerHapticPlayer bouncerHapticPlayer, UserActivityNotifier userActivityNotifier) {
        super(keyguardPINView, keyguardUpdateMonitor, securityMode, lockPatternUtils, keyguardSecurityCallback, factory, latencyTracker, liftToActivateListener, emergencyButtonController, falsingCollector, selectedUserInteractor, keyguardKeyboardInteractor, bouncerHapticPlayer, userActivityNotifier);
        this.mPostureCallback = new DevicePostureController.Callback() { // from class: com.android.keyguard.KeyguardPinViewController$$ExternalSyntheticLambda0
            @Override // com.android.systemui.statusbar.policy.DevicePostureController.Callback
            public final void onPostureChanged(int i) {
                ((KeyguardPINView) KeyguardPinViewController.this.mView).onDevicePostureChanged(i);
            }
        };
        this.mOkButton = keyguardPINView.findViewById(R.id.key_enter);
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mPostureController = devicePostureController;
        this.mLockPatternUtils = lockPatternUtils;
        this.mFeatureFlags = featureFlags;
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        featureFlags.getClass();
        keyguardPINView.mContainerConstraintLayout = (ConstraintLayout) keyguardPINView.findViewById(R.id.pin_container);
        this.mBackspaceKey = (NumPadButton) keyguardPINView.findViewById(R.id.delete_button);
        this.mPinLength = lockPatternUtils.getPinLength(selectedUserInteractor.getSelectedUserId());
        this.mUiEventLogger = uiEventLogger;
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputViewController
    public final void handleAttemptLockout(long j) {
        super.handleAttemptLockout(j);
        updateAutoConfirmationState();
    }

    public final boolean isAutoPinConfirmEnabledInSettings() {
        return this.mLockPatternUtils.isAutoPinConfirmEnabled(this.mSelectedUserInteractor.getSelectedUserId()) && this.mPinLength != -1;
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputViewController
    public final void onUserInput() {
        super.onUserInput();
        if (isAutoPinConfirmEnabledInSettings()) {
            updateAutoConfirmationState();
            if (this.mPasswordEntry.mText.length() == this.mPinLength && this.mOkButton.getVisibility() == 4) {
                this.mUiEventLogger.log(PinBouncerUiEvent.ATTEMPT_UNLOCK_WITH_AUTO_CONFIRM_FEATURE);
                verifyPasswordAndUnlock();
            }
        }
    }

    @Override // com.android.keyguard.KeyguardPinBasedInputViewController, com.android.keyguard.KeyguardAbsKeyInputViewController, com.android.keyguard.KeyguardInputViewController, com.android.systemui.util.ViewController
    public final void onViewAttached() {
        super.onViewAttached();
        View findViewById = ((KeyguardPINView) this.mView).findViewById(R.id.cancel_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.keyguard.KeyguardPinViewController$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    KeyguardPinViewController keyguardPinViewController = KeyguardPinViewController.this;
                    keyguardPinViewController.getKeyguardSecurityCallback().reset();
                    keyguardPinViewController.getKeyguardSecurityCallback().onCancelClicked();
                }
            });
        }
        BasePasswordTextView$UserActivityListener basePasswordTextView$UserActivityListener = new BasePasswordTextView$UserActivityListener() { // from class: com.android.keyguard.KeyguardPinViewController$$ExternalSyntheticLambda2
            @Override // com.android.keyguard.BasePasswordTextView$UserActivityListener
            public final void onUserActivity() {
                KeyguardPinViewController.this.onUserInput();
            }
        };
        PasswordTextView passwordTextView = this.mPasswordEntry;
        passwordTextView.mUserActivityListener = basePasswordTextView$UserActivityListener;
        KeyguardPINView keyguardPINView = (KeyguardPINView) this.mView;
        DevicePostureControllerImpl devicePostureControllerImpl = (DevicePostureControllerImpl) this.mPostureController;
        keyguardPINView.onDevicePostureChanged(devicePostureControllerImpl.getDevicePosture());
        devicePostureControllerImpl.addCallback(this.mPostureCallback);
        if (((FeatureFlagsClassicRelease) this.mFeatureFlags).isEnabled(Flags.AUTO_PIN_CONFIRMATION)) {
            passwordTextView.mUsePinShapes = true;
            updateAutoConfirmationState();
        }
    }

    @Override // com.android.keyguard.KeyguardPinBasedInputViewController, com.android.systemui.util.ViewController
    public final void onViewDetached() {
        super.onViewDetached();
        ((DevicePostureControllerImpl) this.mPostureController).removeCallback(this.mPostureCallback);
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final boolean startDisappearAnimation(final Runnable runnable) {
        KeyguardPINView keyguardPINView = (KeyguardPINView) this.mView;
        boolean z = this.mKeyguardUpdateMonitor.mNeedsSlowUnlockTransition;
        if (keyguardPINView.mAppearAnimator.isRunning()) {
            keyguardPINView.mAppearAnimator.cancel();
        }
        keyguardPINView.setTranslationY(0.0f);
        DisappearAnimationUtils disappearAnimationUtils = z ? keyguardPINView.mDisappearAnimationUtilsLocked : keyguardPINView.mDisappearAnimationUtils;
        float f = keyguardPINView.mDisappearYTranslation;
        Interpolator interpolator = keyguardPINView.mDisappearAnimationUtils.mInterpolator;
        Runnable runnable2 = new Runnable() { // from class: com.android.keyguard.KeyguardPINView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Runnable runnable3 = runnable;
                int i = KeyguardPINView.$r8$clinit;
                if (runnable3 != null) {
                    runnable3.run();
                }
            }
        };
        KeyguardInputView.AnonymousClass1 anonymousClass1 = new KeyguardInputView.AnonymousClass1(22);
        disappearAnimationUtils.getClass();
        AppearAnimationUtils.createAnimation(keyguardPINView, 0L, 200L, f, false, interpolator, runnable2, anonymousClass1);
        return true;
    }

    public final void updateAutoConfirmationState() {
        this.mDisabledAutoConfirmation = this.mLockPatternUtils.getCurrentFailedPasswordAttempts(this.mSelectedUserInteractor.getSelectedUserId()) >= 5;
        if (!isAutoPinConfirmEnabledInSettings() || this.mDisabledAutoConfirmation) {
            this.mOkButton.setVisibility(0);
        } else {
            this.mOkButton.setVisibility(4);
        }
        boolean isAutoPinConfirmEnabledInSettings = isAutoPinConfirmEnabledInSettings();
        boolean z = isAutoPinConfirmEnabledInSettings && !this.mDisabledAutoConfirmation;
        NumPadButton numPadButton = this.mBackspaceKey;
        if (numPadButton.mIsTransparentMode != z) {
            numPadButton.mIsTransparentMode = z;
            if (z) {
                numPadButton.setBackgroundColor(numPadButton.getResources().getColor(android.R.color.transparent));
            } else {
                numPadButton.setBackground(numPadButton.getContext().getDrawable(R.drawable.num_pad_key_background));
            }
            numPadButton.setupAnimator();
            numPadButton.reloadColors();
            numPadButton.requestLayout();
        }
        PasswordTextView passwordTextView = this.mPasswordEntry;
        if (isAutoPinConfirmEnabledInSettings) {
            if (passwordTextView.mText.length() > 0 || this.mDisabledAutoConfirmation) {
                numPadButton.setVisibility(0);
            } else {
                numPadButton.setVisibility(4);
            }
        }
        passwordTextView.setIsPinHinting(isAutoPinConfirmEnabledInSettings() && this.mPinLength == 6 && !this.mDisabledAutoConfirmation);
    }
}

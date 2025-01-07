package com.android.keyguard;

import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.PluralsMessageFormatter;
import android.view.MotionEvent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternChecker;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import com.android.internal.widget.LockscreenCredential;
import com.android.keyguard.EmergencyButtonController;
import com.android.keyguard.KeyguardInputView;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.settingslib.animation.AppearAnimationUtils;
import com.android.settingslib.animation.DisappearAnimationUtils;
import com.android.systemui.bouncer.ui.helper.BouncerHapticPlayer;
import com.android.systemui.classifier.FalsingClassifier;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DevicePostureControllerImpl;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.wm.shell.R;
import java.util.HashMap;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardPatternViewController extends KeyguardInputViewController {
    public final AnonymousClass2 mCancelPatternRunnable;
    public CountDownTimer mCountdownTimer;
    public final AnonymousClass1 mEmergencyButtonCallback;
    public final EmergencyButtonController mEmergencyButtonController;
    public final KeyguardPatternViewController$$ExternalSyntheticLambda1 mExternalHapticsPlayer;
    public final FalsingCollector mFalsingCollector;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final LatencyTracker mLatencyTracker;
    public final LockPatternUtils mLockPatternUtils;
    public final LockPatternView mLockPatternView;
    public AsyncTask mPendingLockCheck;
    public final KeyguardPatternViewController$$ExternalSyntheticLambda0 mPostureCallback;
    public final DevicePostureController mPostureController;

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.keyguard.KeyguardPatternViewController$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.keyguard.KeyguardPatternViewController$1] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.android.keyguard.KeyguardPatternViewController$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.android.keyguard.KeyguardPatternViewController$2] */
    public KeyguardPatternViewController(KeyguardPatternView keyguardPatternView, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardSecurityModel.SecurityMode securityMode, LockPatternUtils lockPatternUtils, KeyguardSecurityCallback keyguardSecurityCallback, LatencyTracker latencyTracker, FalsingCollector falsingCollector, EmergencyButtonController emergencyButtonController, KeyguardMessageAreaController.Factory factory, DevicePostureController devicePostureController, FeatureFlags featureFlags, SelectedUserInteractor selectedUserInteractor, BouncerHapticPlayer bouncerHapticPlayer) {
        super(keyguardPatternView, securityMode, keyguardSecurityCallback, emergencyButtonController, factory, selectedUserInteractor, bouncerHapticPlayer);
        this.mPostureCallback = new DevicePostureController.Callback() { // from class: com.android.keyguard.KeyguardPatternViewController$$ExternalSyntheticLambda0
            @Override // com.android.systemui.statusbar.policy.DevicePostureController.Callback
            public final void onPostureChanged(int i) {
                ((KeyguardPatternView) KeyguardPatternViewController.this.mView).onDevicePostureChanged(i);
            }
        };
        this.mEmergencyButtonCallback = new EmergencyButtonController.EmergencyButtonCallback() { // from class: com.android.keyguard.KeyguardPatternViewController.1
            @Override // com.android.keyguard.EmergencyButtonController.EmergencyButtonCallback
            public final void onEmergencyButtonClickedWhenInCall() {
                KeyguardPatternViewController.this.getKeyguardSecurityCallback().reset();
            }
        };
        this.mExternalHapticsPlayer = new LockPatternView.ExternalHapticsPlayer() { // from class: com.android.keyguard.KeyguardPatternViewController$$ExternalSyntheticLambda1
            public final void performCellAddedFeedback() {
                KeyguardPatternViewController keyguardPatternViewController = KeyguardPatternViewController.this;
                BouncerHapticPlayer bouncerHapticPlayer2 = keyguardPatternViewController.mBouncerHapticPlayer;
                View view = keyguardPatternViewController.mView;
                bouncerHapticPlayer2.getClass();
                if (view != null) {
                    view.performHapticFeedback(1, 1);
                }
            }
        };
        this.mCancelPatternRunnable = new Runnable() { // from class: com.android.keyguard.KeyguardPatternViewController.2
            @Override // java.lang.Runnable
            public final void run() {
                KeyguardPatternViewController.this.mLockPatternView.clearPattern();
            }
        };
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mLockPatternUtils = lockPatternUtils;
        this.mLatencyTracker = latencyTracker;
        this.mFalsingCollector = falsingCollector;
        this.mEmergencyButtonController = emergencyButtonController;
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        featureFlags.getClass();
        keyguardPatternView.getClass();
        keyguardPatternView.mContainerConstraintLayout = (ConstraintLayout) keyguardPatternView.findViewById(R.id.pattern_container);
        this.mLockPatternView = keyguardPatternView.findViewById(R.id.lockPatternView);
        this.mPostureController = devicePostureController;
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final int getInitialMessageResId() {
        return R.string.keyguard_enter_your_pattern;
    }

    public final void handleAttemptLockout$1(long j) {
        this.mLockPatternView.clearPattern();
        this.mLockPatternView.setEnabled(false);
        long ceil = (long) Math.ceil((j - SystemClock.elapsedRealtime()) / 1000.0d);
        getKeyguardSecurityCallback().onAttemptLockoutStart(ceil);
        this.mCountdownTimer = new CountDownTimer(ceil * 1000) { // from class: com.android.keyguard.KeyguardPatternViewController.3
            @Override // android.os.CountDownTimer
            public final void onFinish() {
                KeyguardPatternViewController.this.mLockPatternView.setEnabled(true);
                KeyguardPatternViewController.this.mMessageAreaController.setMessage(R.string.keyguard_enter_your_pattern);
            }

            @Override // android.os.CountDownTimer
            public final void onTick(long j2) {
                int round = (int) Math.round(j2 / 1000.0d);
                HashMap hashMap = new HashMap();
                hashMap.put("count", Integer.valueOf(round));
                KeyguardPatternViewController keyguardPatternViewController = KeyguardPatternViewController.this;
                keyguardPatternViewController.mMessageAreaController.setMessage(PluralsMessageFormatter.format(((KeyguardPatternView) keyguardPatternViewController.mView).getResources(), hashMap, R.string.kg_too_many_failed_attempts_countdown), false);
            }
        }.start();
    }

    @Override // com.android.keyguard.KeyguardSecurityView
    public final boolean needsInput() {
        return false;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        super.mEmergencyButtonController.init$9();
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final void onPause() {
        this.mPaused = true;
        CountDownTimer countDownTimer = this.mCountdownTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.mCountdownTimer = null;
        }
        AsyncTask asyncTask = this.mPendingLockCheck;
        if (asyncTask != null) {
            asyncTask.cancel(false);
            this.mPendingLockCheck = null;
        }
        this.mMessageAreaController.setMessage(R.string.keyguard_enter_your_pattern);
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final void onResume(int i) {
        this.mPaused = false;
    }

    @Override // com.android.keyguard.KeyguardInputViewController, com.android.systemui.util.ViewController
    public final void onViewAttached() {
        super.onViewAttached();
        this.mLockPatternView.setOnPatternListener(new UnlockPatternListener());
        this.mLockPatternView.setSaveEnabled(false);
        LockPatternView lockPatternView = this.mLockPatternView;
        LockPatternUtils lockPatternUtils = this.mLockPatternUtils;
        SelectedUserInteractor selectedUserInteractor = this.mSelectedUserInteractor;
        lockPatternView.setInStealthMode(!lockPatternUtils.isVisiblePatternEnabled(selectedUserInteractor.getSelectedUserId()));
        this.mLockPatternView.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.keyguard.KeyguardPatternViewController$$ExternalSyntheticLambda2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                KeyguardPatternViewController keyguardPatternViewController = KeyguardPatternViewController.this;
                keyguardPatternViewController.getClass();
                if (motionEvent.getActionMasked() != 0) {
                    return false;
                }
                keyguardPatternViewController.mFalsingCollector.avoidGesture();
                return false;
            }
        });
        this.mEmergencyButtonController.mEmergencyButtonCallback = this.mEmergencyButtonCallback;
        View findViewById = ((KeyguardPatternView) this.mView).findViewById(R.id.cancel_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.android.keyguard.KeyguardPatternViewController$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    KeyguardPatternViewController keyguardPatternViewController = KeyguardPatternViewController.this;
                    keyguardPatternViewController.getKeyguardSecurityCallback().reset();
                    keyguardPatternViewController.getKeyguardSecurityCallback().onCancelClicked();
                }
            });
        }
        KeyguardPatternView keyguardPatternView = (KeyguardPatternView) this.mView;
        DevicePostureControllerImpl devicePostureControllerImpl = (DevicePostureControllerImpl) this.mPostureController;
        keyguardPatternView.onDevicePostureChanged(devicePostureControllerImpl.getDevicePosture());
        devicePostureControllerImpl.addCallback(this.mPostureCallback);
        long lockoutAttemptDeadline = this.mLockPatternUtils.getLockoutAttemptDeadline(selectedUserInteractor.getSelectedUserId());
        if (lockoutAttemptDeadline != 0) {
            handleAttemptLockout$1(lockoutAttemptDeadline);
        }
        this.mLockPatternView.setExternalHapticsPlayer(this.mExternalHapticsPlayer);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.mLockPatternView.setOnPatternListener((LockPatternView.OnPatternListener) null);
        this.mLockPatternView.setOnTouchListener((View.OnTouchListener) null);
        this.mEmergencyButtonController.mEmergencyButtonCallback = null;
        View findViewById = ((KeyguardPatternView) this.mView).findViewById(R.id.cancel_button);
        if (findViewById != null) {
            findViewById.setOnClickListener(null);
        }
        ((DevicePostureControllerImpl) this.mPostureController).removeCallback(this.mPostureCallback);
        this.mLockPatternView.setExternalHapticsPlayer((LockPatternView.ExternalHapticsPlayer) null);
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final void reset$1() {
        this.mLockPatternView.setInStealthMode(!this.mLockPatternUtils.isVisiblePatternEnabled(this.mSelectedUserInteractor.getSelectedUserId()));
        this.mLockPatternView.enableInput();
        this.mLockPatternView.setEnabled(true);
        this.mLockPatternView.clearPattern();
        this.mMessageAreaController.setMessage(R.string.keyguard_enter_your_pattern);
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final void showMessage(CharSequence charSequence, ColorStateList colorStateList, boolean z) {
        KeyguardMessageAreaController keyguardMessageAreaController = this.mMessageAreaController;
        if (keyguardMessageAreaController == null) {
            return;
        }
        if (colorStateList != null) {
            ((KeyguardMessageArea) keyguardMessageAreaController.mView).setNextMessageColor(colorStateList);
        }
        keyguardMessageAreaController.setMessage(charSequence, z);
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final void showPromptReason(int i) {
        int i2;
        if (i == 0) {
            i2 = 0;
        } else if (i != 1) {
            i2 = R.string.kg_prompt_reason_timeout_pattern;
            if (i != 2) {
                if (i == 3) {
                    i2 = R.string.kg_prompt_reason_device_admin;
                } else if (i == 4) {
                    i2 = R.string.kg_prompt_after_user_lockdown_pattern;
                } else if (i == 16) {
                    i2 = R.string.kg_prompt_after_update_pattern;
                } else if (i == 6) {
                    i2 = R.string.kg_prompt_added_security_pattern;
                } else if (i == 9) {
                    i2 = R.string.kg_prompt_after_adaptive_auth_lock;
                }
            }
        } else {
            i2 = R.string.kg_prompt_reason_restart_pattern;
        }
        if (i2 != 0) {
            this.mMessageAreaController.setMessage(this.mView.getResources().getText(i2), false);
        }
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final boolean startDisappearAnimation(final Runnable runnable) {
        final KeyguardPatternView keyguardPatternView = (KeyguardPatternView) this.mView;
        boolean z = this.mKeyguardUpdateMonitor.mNeedsSlowUnlockTransition;
        keyguardPatternView.getClass();
        float f = z ? 1.5f : 1.0f;
        keyguardPatternView.mLockPatternView.clearPattern();
        keyguardPatternView.enableClipping(false);
        keyguardPatternView.setTranslationY(0.0f);
        DisappearAnimationUtils disappearAnimationUtils = keyguardPatternView.mDisappearAnimationUtils;
        AppearAnimationUtils.startTranslationYAnimation(keyguardPatternView, 0L, (long) (300.0f * f), -disappearAnimationUtils.mStartTranslation, disappearAnimationUtils.mInterpolator, new KeyguardInputView.AnonymousClass1(21));
        (z ? keyguardPatternView.mDisappearAnimationUtilsLocked : keyguardPatternView.mDisappearAnimationUtils).startAnimation2d(keyguardPatternView.mLockPatternView.getCellStates(), new Runnable() { // from class: com.android.keyguard.KeyguardPatternView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                KeyguardPatternView keyguardPatternView2 = KeyguardPatternView.this;
                Runnable runnable2 = runnable;
                int i = KeyguardPatternView.$r8$clinit;
                keyguardPatternView2.enableClipping(true);
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        }, keyguardPatternView);
        if (TextUtils.isEmpty(keyguardPatternView.mSecurityMessageDisplay.getText())) {
            return true;
        }
        DisappearAnimationUtils disappearAnimationUtils2 = keyguardPatternView.mDisappearAnimationUtils;
        AppearAnimationUtils.createAnimation(keyguardPatternView.mSecurityMessageDisplay, 0L, (long) (f * 200.0f), (-disappearAnimationUtils2.mStartTranslation) * 3.0f, false, disappearAnimationUtils2.mInterpolator, null, null);
        return true;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UnlockPatternListener implements LockPatternView.OnPatternListener {
        public UnlockPatternListener() {
        }

        public final void onPatternCellAdded(List list) {
            KeyguardPatternViewController.this.getKeyguardSecurityCallback().userActivity();
            KeyguardPatternViewController.this.getKeyguardSecurityCallback().onUserInput();
        }

        public final void onPatternChecked(int i, int i2, boolean z, boolean z2) {
            boolean z3 = KeyguardPatternViewController.this.mSelectedUserInteractor.getSelectedUserId() == i;
            if (z) {
                KeyguardPatternViewController.this.mBouncerHapticPlayer.getClass();
                KeyguardPatternViewController.this.getKeyguardSecurityCallback().reportUnlockAttempt(i, 0, true);
                if (z3) {
                    KeyguardPatternViewController.this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Correct);
                    KeyguardPatternViewController.this.mLatencyTracker.onActionStart(11);
                    KeyguardPatternViewController.this.getKeyguardSecurityCallback().dismiss(i, KeyguardSecurityModel.SecurityMode.Pattern);
                    return;
                }
                return;
            }
            KeyguardPatternViewController.this.mBouncerHapticPlayer.getClass();
            KeyguardPatternViewController.this.mLockPatternView.setDisplayMode(LockPatternView.DisplayMode.Wrong);
            if (z2) {
                KeyguardPatternViewController.this.getKeyguardSecurityCallback().reportUnlockAttempt(i, i2, false);
                if (i2 > 0) {
                    KeyguardPatternViewController.this.handleAttemptLockout$1(KeyguardPatternViewController.this.mLockPatternUtils.setLockoutAttemptDeadline(i, i2));
                }
            }
            if (i2 == 0) {
                KeyguardPatternViewController.this.mMessageAreaController.setMessage(R.string.kg_wrong_pattern);
                KeyguardPatternViewController keyguardPatternViewController = KeyguardPatternViewController.this;
                keyguardPatternViewController.mLockPatternView.postDelayed(keyguardPatternViewController.mCancelPatternRunnable, 2000L);
            }
        }

        public final void onPatternDetected(List list) {
            KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardPatternViewController.this.mKeyguardUpdateMonitor;
            keyguardUpdateMonitor.mCredentialAttempted = true;
            keyguardUpdateMonitor.updateFingerprintListeningState(2);
            KeyguardPatternViewController.this.mLockPatternView.disableInput();
            AsyncTask asyncTask = KeyguardPatternViewController.this.mPendingLockCheck;
            if (asyncTask != null) {
                asyncTask.cancel(false);
            }
            final int selectedUserId = KeyguardPatternViewController.this.mSelectedUserInteractor.getSelectedUserId();
            if (list.size() < 4) {
                if (list.size() == 1) {
                    KeyguardPatternViewController.this.mFalsingCollector.updateFalseConfidence(new FalsingClassifier.Result(true, 0.7d, UnlockPatternListener.class.getSimpleName(), "empty pattern input"));
                }
                KeyguardPatternViewController.this.mLockPatternView.enableInput();
                onPatternChecked(selectedUserId, 0, false, false);
                return;
            }
            KeyguardPatternViewController.this.mLatencyTracker.onActionStart(3);
            KeyguardPatternViewController.this.mLatencyTracker.onActionStart(4);
            KeyguardPatternViewController keyguardPatternViewController = KeyguardPatternViewController.this;
            keyguardPatternViewController.mPendingLockCheck = LockPatternChecker.checkCredential(keyguardPatternViewController.mLockPatternUtils, LockscreenCredential.createPattern(list), selectedUserId, new LockPatternChecker.OnCheckCallback() { // from class: com.android.keyguard.KeyguardPatternViewController.UnlockPatternListener.1
                public final void onCancelled() {
                    KeyguardPatternViewController.this.mLatencyTracker.onActionEnd(4);
                }

                public final void onChecked(boolean z, int i) {
                    KeyguardPatternViewController.this.mLatencyTracker.onActionEnd(4);
                    KeyguardPatternViewController.this.mLockPatternView.enableInput();
                    UnlockPatternListener unlockPatternListener = UnlockPatternListener.this;
                    KeyguardPatternViewController.this.mPendingLockCheck = null;
                    if (z) {
                        return;
                    }
                    unlockPatternListener.onPatternChecked(selectedUserId, i, false, true);
                }

                public final void onEarlyMatched() {
                    KeyguardPatternViewController.this.mLatencyTracker.onActionEnd(3);
                    UnlockPatternListener.this.onPatternChecked(selectedUserId, 0, true, true);
                }
            });
            if (list.size() > 2) {
                KeyguardPatternViewController.this.getKeyguardSecurityCallback().userActivity();
                KeyguardPatternViewController.this.getKeyguardSecurityCallback().onUserInput();
            }
        }

        public final void onPatternStart() {
            KeyguardPatternViewController keyguardPatternViewController = KeyguardPatternViewController.this;
            keyguardPatternViewController.mLockPatternView.removeCallbacks(keyguardPatternViewController.mCancelPatternRunnable);
            KeyguardPatternViewController.this.mMessageAreaController.setMessage("", true);
        }

        public final void onPatternCleared() {
        }
    }
}

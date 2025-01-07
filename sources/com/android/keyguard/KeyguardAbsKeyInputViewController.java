package com.android.keyguard;

import android.content.res.ColorStateList;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.PluralsMessageFormatter;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternChecker;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockscreenCredential;
import com.android.keyguard.EmergencyButtonController;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.systemui.bouncer.ui.helper.BouncerHapticPlayer;
import com.android.systemui.classifier.FalsingClassifier;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.wm.shell.R;
import java.util.HashMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardAbsKeyInputViewController extends KeyguardInputViewController {
    public CountDownTimer mCountdownTimer;
    public boolean mDismissing;
    public final AnonymousClass1 mEmergencyButtonCallback;
    public final EmergencyButtonController mEmergencyButtonController;
    public final FalsingCollector mFalsingCollector;
    public final KeyguardAbsKeyInputViewController$$ExternalSyntheticLambda0 mKeyDownListener;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final LatencyTracker mLatencyTracker;
    public final LockPatternUtils mLockPatternUtils;
    public boolean mLockedOut;
    public AsyncTask mPendingLockCheck;
    public boolean mResumed;

    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.keyguard.KeyguardAbsKeyInputViewController$1] */
    public KeyguardAbsKeyInputViewController(KeyguardAbsKeyInputView keyguardAbsKeyInputView, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardSecurityModel.SecurityMode securityMode, LockPatternUtils lockPatternUtils, KeyguardSecurityCallback keyguardSecurityCallback, KeyguardMessageAreaController.Factory factory, LatencyTracker latencyTracker, FalsingCollector falsingCollector, EmergencyButtonController emergencyButtonController, SelectedUserInteractor selectedUserInteractor, BouncerHapticPlayer bouncerHapticPlayer, UserActivityNotifier userActivityNotifier) {
        super(keyguardAbsKeyInputView, securityMode, keyguardSecurityCallback, emergencyButtonController, factory, selectedUserInteractor, bouncerHapticPlayer);
        this.mKeyDownListener = new KeyguardAbsKeyInputViewController$$ExternalSyntheticLambda0(this);
        this.mEmergencyButtonCallback = new EmergencyButtonController.EmergencyButtonCallback() { // from class: com.android.keyguard.KeyguardAbsKeyInputViewController.1
            @Override // com.android.keyguard.EmergencyButtonController.EmergencyButtonCallback
            public final void onEmergencyButtonClickedWhenInCall() {
                KeyguardAbsKeyInputViewController.this.getKeyguardSecurityCallback().reset();
            }
        };
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mLockPatternUtils = lockPatternUtils;
        this.mLatencyTracker = latencyTracker;
        this.mFalsingCollector = falsingCollector;
        this.mEmergencyButtonController = emergencyButtonController;
    }

    public void handleAttemptLockout(long j) {
        ((KeyguardAbsKeyInputView) this.mView).setPasswordEntryEnabled(false);
        ((KeyguardAbsKeyInputView) this.mView).setPasswordEntryInputEnabled(false);
        this.mLockedOut = true;
        long ceil = (long) Math.ceil((j - SystemClock.elapsedRealtime()) / 1000.0d);
        getKeyguardSecurityCallback().onAttemptLockoutStart(ceil);
        this.mCountdownTimer = new CountDownTimer(ceil * 1000) { // from class: com.android.keyguard.KeyguardAbsKeyInputViewController.2
            @Override // android.os.CountDownTimer
            public final void onFinish() {
                KeyguardAbsKeyInputViewController.this.mMessageAreaController.setMessage("", true);
                KeyguardAbsKeyInputViewController keyguardAbsKeyInputViewController = KeyguardAbsKeyInputViewController.this;
                keyguardAbsKeyInputViewController.mLockedOut = false;
                keyguardAbsKeyInputViewController.resetState();
            }

            @Override // android.os.CountDownTimer
            public final void onTick(long j2) {
                int round = (int) Math.round(j2 / 1000.0d);
                HashMap hashMap = new HashMap();
                hashMap.put("count", Integer.valueOf(round));
                KeyguardAbsKeyInputViewController keyguardAbsKeyInputViewController = KeyguardAbsKeyInputViewController.this;
                keyguardAbsKeyInputViewController.mMessageAreaController.setMessage(PluralsMessageFormatter.format(((KeyguardAbsKeyInputView) keyguardAbsKeyInputViewController.mView).getResources(), hashMap, R.string.kg_too_many_failed_attempts_countdown), false);
            }
        }.start();
    }

    @Override // com.android.keyguard.KeyguardSecurityView
    public boolean needsInput() {
        return this instanceof KeyguardPasswordViewController;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        super.mEmergencyButtonController.init$9();
    }

    public final void onPasswordChecked(int i, int i2, boolean z, boolean z2) {
        boolean z3 = this.mSelectedUserInteractor.getSelectedUserId() == i;
        BouncerHapticPlayer bouncerHapticPlayer = this.mBouncerHapticPlayer;
        if (z) {
            bouncerHapticPlayer.getClass();
            getKeyguardSecurityCallback().reportUnlockAttempt(i, 0, true);
            if (z3) {
                this.mDismissing = true;
                this.mLatencyTracker.onActionStart(11);
                getKeyguardSecurityCallback().dismiss(i, this.mSecurityMode);
                return;
            }
            return;
        }
        bouncerHapticPlayer.getClass();
        ((KeyguardAbsKeyInputView) this.mView).resetPasswordText(true, false);
        if (z2) {
            getKeyguardSecurityCallback().reportUnlockAttempt(i, i2, false);
            if (i2 > 0) {
                handleAttemptLockout(this.mLockPatternUtils.setLockoutAttemptDeadline(i, i2));
            }
        }
        if (i2 == 0) {
            this.mMessageAreaController.setMessage(((KeyguardAbsKeyInputView) this.mView).getWrongPasswordStringId());
        }
        startErrorAnimation();
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public void onPause() {
        this.mResumed = false;
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
        reset$1();
    }

    public void onUserInput() {
        this.mFalsingCollector.updateFalseConfidence(FalsingClassifier.Result.passed(0.6d));
        getKeyguardSecurityCallback().userActivity();
        getKeyguardSecurityCallback().onUserInput();
        this.mMessageAreaController.setMessage("", true);
    }

    @Override // com.android.keyguard.KeyguardInputViewController, com.android.systemui.util.ViewController
    public void onViewAttached() {
        super.onViewAttached();
        ((KeyguardAbsKeyInputView) this.mView).mKeyDownListener = this.mKeyDownListener;
        this.mEmergencyButtonController.mEmergencyButtonCallback = this.mEmergencyButtonCallback;
        long lockoutAttemptDeadline = this.mLockPatternUtils.getLockoutAttemptDeadline(this.mSelectedUserInteractor.getSelectedUserId());
        if (shouldLockout(lockoutAttemptDeadline)) {
            handleAttemptLockout(lockoutAttemptDeadline);
        }
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final void reset$1() {
        this.mMessageAreaController.setMessage("", false);
        this.mDismissing = false;
        ((KeyguardAbsKeyInputView) this.mView).resetPasswordText(false, false);
        resetState();
    }

    public abstract void resetState();

    public boolean shouldLockout(long j) {
        return j != 0;
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
        int promptReasonStringRes;
        if (i == 0 || (promptReasonStringRes = ((KeyguardAbsKeyInputView) this.mView).getPromptReasonStringRes(i)) == 0) {
            return;
        }
        this.mMessageAreaController.setMessage(((KeyguardAbsKeyInputView) this.mView).getResources().getString(promptReasonStringRes), false);
    }

    public void verifyPasswordAndUnlock() {
        if (this.mDismissing || this.mLockedOut) {
            return;
        }
        final LockscreenCredential enteredCredential = ((KeyguardAbsKeyInputView) this.mView).getEnteredCredential();
        ((KeyguardAbsKeyInputView) this.mView).setPasswordEntryInputEnabled(false);
        AsyncTask asyncTask = this.mPendingLockCheck;
        if (asyncTask != null) {
            asyncTask.cancel(false);
        }
        final int selectedUserId = this.mSelectedUserInteractor.getSelectedUserId();
        if (enteredCredential.size() <= 3) {
            ((KeyguardAbsKeyInputView) this.mView).setPasswordEntryInputEnabled(true);
            onPasswordChecked(selectedUserId, 0, false, false);
            enteredCredential.zeroize();
        } else {
            this.mLatencyTracker.onActionStart(3);
            this.mLatencyTracker.onActionStart(4);
            KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
            keyguardUpdateMonitor.mCredentialAttempted = true;
            keyguardUpdateMonitor.updateFingerprintListeningState(2);
            this.mPendingLockCheck = LockPatternChecker.checkCredential(this.mLockPatternUtils, enteredCredential, selectedUserId, new LockPatternChecker.OnCheckCallback() { // from class: com.android.keyguard.KeyguardAbsKeyInputViewController.3
                public final void onCancelled() {
                    KeyguardAbsKeyInputViewController.this.mLatencyTracker.onActionEnd(4);
                    enteredCredential.zeroize();
                }

                public final void onChecked(boolean z, int i) {
                    KeyguardAbsKeyInputViewController.this.mLatencyTracker.onActionEnd(4);
                    ((KeyguardAbsKeyInputView) KeyguardAbsKeyInputViewController.this.mView).setPasswordEntryInputEnabled(true);
                    KeyguardAbsKeyInputViewController keyguardAbsKeyInputViewController = KeyguardAbsKeyInputViewController.this;
                    keyguardAbsKeyInputViewController.mPendingLockCheck = null;
                    if (!z) {
                        keyguardAbsKeyInputViewController.onPasswordChecked(selectedUserId, i, false, true);
                    }
                    enteredCredential.zeroize();
                }

                public final void onEarlyMatched() {
                    KeyguardAbsKeyInputViewController.this.mLatencyTracker.onActionEnd(3);
                    KeyguardAbsKeyInputViewController.this.onPasswordChecked(selectedUserId, 0, true, true);
                    enteredCredential.zeroize();
                }
            });
        }
    }

    public void startErrorAnimation() {
    }
}

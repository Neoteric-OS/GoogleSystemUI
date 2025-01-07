package com.android.keyguard;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.telephony.PinResult;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.domain.interactor.KeyguardKeyboardInteractor;
import com.android.systemui.bouncer.ui.helper.BouncerHapticPlayer;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.PluralMessageFormaterKt;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSimPinViewController extends KeyguardPinBasedInputViewController {
    public AnonymousClass2 mCheckSimPinThread;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public int mRemainingAttempts;
    public AlertDialog mRemainingAttemptsDialog;
    public boolean mShowDefaultMessage;
    public final ImageView mSimImageView;
    public ProgressDialog mSimUnlockProgressDialog;
    public int mSubId;
    public final TelephonyManager mTelephonyManager;
    public final KeyguardUpdateMonitorCallback mUpdateMonitorCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardSimPinViewController$2, reason: invalid class name */
    public final class AnonymousClass2 extends Thread {
        public final /* synthetic */ int $r8$classId;
        public final String mPin;
        public final int mSubId;
        public final /* synthetic */ KeyguardSimPinViewController this$0;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(KeyguardSimPinViewController keyguardSimPinViewController, String str, int i) {
            this(str, i, (byte) 0);
            this.$r8$classId = 0;
            this.this$0 = keyguardSimPinViewController;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            PinResult supplyIccLockPin = KeyguardSimPinViewController.this.mTelephonyManager.createForSubscriptionId(this.mSubId).supplyIccLockPin(this.mPin);
            supplyIccLockPin.toString();
            ((KeyguardSimPinView) KeyguardSimPinViewController.this.mView).post(new KeyguardSimPinViewController$2$$ExternalSyntheticLambda0(this, supplyIccLockPin, 1));
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(KeyguardSimPinViewController keyguardSimPinViewController, int i) {
            this("", i, (byte) 0);
            this.$r8$classId = 1;
            this.this$0 = keyguardSimPinViewController;
        }

        public AnonymousClass2(String str, int i, byte b) {
            this.mPin = str;
            this.mSubId = i;
        }
    }

    public KeyguardSimPinViewController(KeyguardSimPinView keyguardSimPinView, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardSecurityModel.SecurityMode securityMode, LockPatternUtils lockPatternUtils, KeyguardSecurityCallback keyguardSecurityCallback, KeyguardMessageAreaController.Factory factory, LatencyTracker latencyTracker, LiftToActivateListener liftToActivateListener, TelephonyManager telephonyManager, FalsingCollector falsingCollector, EmergencyButtonController emergencyButtonController, SelectedUserInteractor selectedUserInteractor, KeyguardKeyboardInteractor keyguardKeyboardInteractor, BouncerHapticPlayer bouncerHapticPlayer, UserActivityNotifier userActivityNotifier) {
        super(keyguardSimPinView, keyguardUpdateMonitor, securityMode, lockPatternUtils, keyguardSecurityCallback, factory, latencyTracker, liftToActivateListener, emergencyButtonController, falsingCollector, selectedUserInteractor, keyguardKeyboardInteractor, bouncerHapticPlayer, userActivityNotifier);
        this.mRemainingAttempts = -1;
        this.mSubId = -1;
        this.mUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.keyguard.KeyguardSimPinViewController.1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onSimStateChanged(int i, int i2, int i3) {
                KeyguardSimPinViewController keyguardSimPinViewController = KeyguardSimPinViewController.this;
                if (i == keyguardSimPinViewController.mSubId && i3 == 3) {
                    keyguardSimPinViewController.getKeyguardSecurityCallback().showCurrentSecurityScreen();
                } else if (i3 != 5) {
                    keyguardSimPinViewController.resetState();
                } else {
                    keyguardSimPinViewController.mRemainingAttempts = -1;
                    keyguardSimPinViewController.resetState();
                }
            }
        };
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mTelephonyManager = telephonyManager;
        this.mSimImageView = (ImageView) keyguardSimPinView.findViewById(R.id.keyguard_sim);
    }

    public final String getPinPasswordErrorMessage(int i) {
        String string = i == 0 ? ((KeyguardSimPinView) this.mView).getResources().getString(R.string.kg_password_wrong_pin_code_pukked) : i > 0 ? PluralMessageFormaterKt.icuMessageFormat(((KeyguardSimPinView) this.mView).getResources(), R.string.kg_password_wrong_pin_code, i) : ((KeyguardSimPinView) this.mView).getResources().getString(R.string.kg_password_pin_failed);
        if (KeyguardEsimArea.isEsimLocked(this.mSubId, ((KeyguardSimPinView) this.mView).getContext())) {
            string = ((KeyguardSimPinView) this.mView).getResources().getString(R.string.kg_sim_lock_esim_instructions, string);
        }
        Log.d("KeyguardSimPinView", "getPinPasswordErrorMessage: attemptsRemaining=" + i + " displayMessage=" + string);
        return string;
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputViewController, com.android.keyguard.KeyguardInputViewController
    public final void onPause() {
        super.onPause();
        ProgressDialog progressDialog = this.mSimUnlockProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.mSimUnlockProgressDialog = null;
        }
    }

    @Override // com.android.keyguard.KeyguardPinBasedInputViewController, com.android.keyguard.KeyguardInputViewController
    public final void onResume(int i) {
        super.onResume(i);
        ((KeyguardSimPinView) this.mView).getClass();
    }

    @Override // com.android.keyguard.KeyguardPinBasedInputViewController, com.android.keyguard.KeyguardAbsKeyInputViewController, com.android.keyguard.KeyguardInputViewController, com.android.systemui.util.ViewController
    public final void onViewAttached() {
        super.onViewAttached();
        this.mKeyguardUpdateMonitor.registerCallback(this.mUpdateMonitorCallback);
    }

    @Override // com.android.keyguard.KeyguardPinBasedInputViewController, com.android.systemui.util.ViewController
    public final void onViewDetached() {
        super.onViewDetached();
        this.mKeyguardUpdateMonitor.removeCallback(this.mUpdateMonitorCallback);
    }

    @Override // com.android.keyguard.KeyguardPinBasedInputViewController, com.android.keyguard.KeyguardAbsKeyInputViewController
    public final void resetState() {
        super.resetState();
        int nextSubIdForState = this.mKeyguardUpdateMonitor.getNextSubIdForState(2);
        if (nextSubIdForState != this.mSubId && SubscriptionManager.isValidSubscriptionId(nextSubIdForState)) {
            this.mSubId = nextSubIdForState;
            this.mShowDefaultMessage = true;
            this.mRemainingAttempts = -1;
        }
        this.mMessageAreaController.setMessage("", true);
        if (this.mShowDefaultMessage) {
            setLockedSimMessage();
            if (this.mRemainingAttempts < 0) {
                new AnonymousClass2(this, this.mSubId).start();
            }
        }
        KeyguardSimPinView keyguardSimPinView = (KeyguardSimPinView) this.mView;
        keyguardSimPinView.setESimLocked(this.mSubId, KeyguardEsimArea.isEsimLocked(this.mSubId, keyguardSimPinView.getContext()));
    }

    public final void setLockedSimMessage() {
        String str;
        boolean isEsimLocked = KeyguardEsimArea.isEsimLocked(this.mSubId, ((KeyguardSimPinView) this.mView).getContext());
        TelephonyManager telephonyManager = this.mTelephonyManager;
        int activeModemCount = telephonyManager != null ? telephonyManager.getActiveModemCount() : 1;
        Resources resources = ((KeyguardSimPinView) this.mView).getResources();
        TypedArray obtainStyledAttributes = ((KeyguardSimPinView) this.mView).getContext().obtainStyledAttributes(new int[]{android.R.attr.textColor});
        int color = obtainStyledAttributes.getColor(0, -1);
        obtainStyledAttributes.recycle();
        if (activeModemCount < 2) {
            str = resources.getString(R.string.kg_sim_pin_instructions);
        } else {
            SubscriptionInfo subscriptionInfoForSubId = this.mKeyguardUpdateMonitor.getSubscriptionInfoForSubId(this.mSubId);
            String displayName = subscriptionInfoForSubId != null ? subscriptionInfoForSubId.getDisplayName() : "";
            String string = !TextUtils.isEmpty(displayName) ? resources.getString(R.string.kg_sim_pin_instructions_multi, displayName) : resources.getString(R.string.kg_sim_pin_instructions);
            if (subscriptionInfoForSubId != null) {
                color = subscriptionInfoForSubId.getIconTint();
            }
            str = string;
        }
        if (isEsimLocked) {
            str = resources.getString(R.string.kg_sim_lock_esim_instructions, str);
        }
        if (((KeyguardSimPinView) this.mView).getVisibility() == 0) {
            this.mMessageAreaController.setMessage(str, true);
        }
        this.mSimImageView.setImageTintList(ColorStateList.valueOf(color));
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final boolean startDisappearAnimation(Runnable runnable) {
        return false;
    }

    @Override // com.android.keyguard.KeyguardInputViewController
    public final void updateMessageAreaVisibility() {
        KeyguardMessageAreaController keyguardMessageAreaController = this.mMessageAreaController;
        if (keyguardMessageAreaController == null) {
            return;
        }
        keyguardMessageAreaController.setIsVisible(true);
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputViewController
    public final void verifyPasswordAndUnlock() {
        PasswordTextView passwordTextView = this.mPasswordEntry;
        String str = passwordTextView.mText;
        if (str.length() < 4 || str.length() > 8) {
            ((KeyguardSimPinView) this.mView).resetPasswordText(true, true);
            getKeyguardSecurityCallback().userActivity();
            this.mMessageAreaController.setMessage(R.string.kg_invalid_sim_pin_hint);
            return;
        }
        if (this.mSimUnlockProgressDialog == null) {
            ProgressDialog progressDialog = new ProgressDialog(((KeyguardSimPinView) this.mView).getContext());
            this.mSimUnlockProgressDialog = progressDialog;
            progressDialog.setMessage(((KeyguardSimPinView) this.mView).getResources().getString(R.string.kg_sim_unlock_progress_dialog_message));
            this.mSimUnlockProgressDialog.setIndeterminate(true);
            this.mSimUnlockProgressDialog.setCancelable(false);
            this.mSimUnlockProgressDialog.getWindow().setType(2009);
        }
        this.mSimUnlockProgressDialog.show();
        if (this.mCheckSimPinThread == null) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this, passwordTextView.mText, this.mSubId);
            this.mCheckSimPinThread = anonymousClass2;
            anonymousClass2.start();
        }
    }
}

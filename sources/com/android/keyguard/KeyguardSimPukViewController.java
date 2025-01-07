package com.android.keyguard;

import android.app.Activity;
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
import android.widget.ImageView;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.domain.interactor.KeyguardKeyboardInteractor;
import com.android.systemui.bouncer.ui.helper.BouncerHapticPlayer;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardSimPukViewController extends KeyguardPinBasedInputViewController {
    public static final boolean DEBUG = KeyguardConstants.DEBUG;
    public AnonymousClass2 mCheckSimPukThread;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public String mPinText;
    public String mPukText;
    public int mRemainingAttempts;
    public AlertDialog mRemainingAttemptsDialog;
    public boolean mShowDefaultMessage;
    public final ImageView mSimImageView;
    public ProgressDialog mSimUnlockProgressDialog;
    public final StateMachine mStateMachine;
    public int mSubId;
    public final TelephonyManager mTelephonyManager;
    public final KeyguardUpdateMonitorCallback mUpdateMonitorCallback;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.keyguard.KeyguardSimPukViewController$2, reason: invalid class name */
    public final class AnonymousClass2 extends Thread {
        public final /* synthetic */ int $r8$classId;
        public final String mPin;
        public final String mPuk;
        public final int mSubId;
        public final /* synthetic */ KeyguardSimPukViewController this$0;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(KeyguardSimPukViewController keyguardSimPukViewController, int i) {
            this("", "", i, (byte) 0);
            this.$r8$classId = 0;
            this.this$0 = keyguardSimPukViewController;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            boolean z = KeyguardSimPukViewController.DEBUG;
            PinResult supplyIccLockPuk = KeyguardSimPukViewController.this.mTelephonyManager.createForSubscriptionId(this.mSubId).supplyIccLockPuk(this.mPuk, this.mPin);
            if (z) {
                supplyIccLockPuk.toString();
            }
            ((KeyguardSimPukView) KeyguardSimPukViewController.this.mView).post(new KeyguardSimPukViewController$3$$ExternalSyntheticLambda0(this, supplyIccLockPuk, 1));
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(KeyguardSimPukViewController keyguardSimPukViewController, String str, String str2, int i) {
            this(str, str2, i, (byte) 0);
            this.$r8$classId = 1;
            this.this$0 = keyguardSimPukViewController;
        }

        public AnonymousClass2(String str, String str2, int i, byte b) {
            this.mPuk = str;
            this.mPin = str2;
            this.mSubId = i;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StateMachine {
        public int mState = 0;

        public StateMachine() {
        }

        public final void reset() {
            KeyguardSimPukViewController keyguardSimPukViewController = KeyguardSimPukViewController.this;
            keyguardSimPukViewController.mPinText = "";
            keyguardSimPukViewController.mPukText = "";
            this.mState = 0;
            int nextSubIdForState = keyguardSimPukViewController.mKeyguardUpdateMonitor.getNextSubIdForState(3);
            if (nextSubIdForState != keyguardSimPukViewController.mSubId && SubscriptionManager.isValidSubscriptionId(nextSubIdForState)) {
                keyguardSimPukViewController.mSubId = nextSubIdForState;
                keyguardSimPukViewController.mShowDefaultMessage = true;
                keyguardSimPukViewController.mRemainingAttempts = -1;
            }
            if (keyguardSimPukViewController.mShowDefaultMessage) {
                keyguardSimPukViewController.showDefaultMessage$1();
            }
            KeyguardSimPukView keyguardSimPukView = (KeyguardSimPukView) keyguardSimPukViewController.mView;
            keyguardSimPukView.setESimLocked(keyguardSimPukViewController.mSubId, KeyguardEsimArea.isEsimLocked(keyguardSimPukViewController.mSubId, keyguardSimPukView.getContext()));
            keyguardSimPukViewController.mPasswordEntry.requestFocus();
        }
    }

    public KeyguardSimPukViewController(KeyguardSimPukView keyguardSimPukView, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardSecurityModel.SecurityMode securityMode, LockPatternUtils lockPatternUtils, KeyguardSecurityCallback keyguardSecurityCallback, KeyguardMessageAreaController.Factory factory, LatencyTracker latencyTracker, LiftToActivateListener liftToActivateListener, TelephonyManager telephonyManager, FalsingCollector falsingCollector, EmergencyButtonController emergencyButtonController, SelectedUserInteractor selectedUserInteractor, KeyguardKeyboardInteractor keyguardKeyboardInteractor, BouncerHapticPlayer bouncerHapticPlayer, UserActivityNotifier userActivityNotifier) {
        super(keyguardSimPukView, keyguardUpdateMonitor, securityMode, lockPatternUtils, keyguardSecurityCallback, factory, latencyTracker, liftToActivateListener, emergencyButtonController, falsingCollector, selectedUserInteractor, keyguardKeyboardInteractor, bouncerHapticPlayer, userActivityNotifier);
        this.mStateMachine = new StateMachine();
        this.mSubId = -1;
        this.mUpdateMonitorCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.keyguard.KeyguardSimPukViewController.1
            @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
            public final void onSimStateChanged(int i, int i2, int i3) {
                boolean z = KeyguardSimPukViewController.DEBUG;
                KeyguardSimPukViewController keyguardSimPukViewController = KeyguardSimPukViewController.this;
                if (i3 != 5) {
                    keyguardSimPukViewController.resetState();
                    return;
                }
                keyguardSimPukViewController.mRemainingAttempts = -1;
                keyguardSimPukViewController.mShowDefaultMessage = true;
                keyguardSimPukViewController.getKeyguardSecurityCallback().dismiss(keyguardSimPukViewController.mSelectedUserInteractor.getSelectedUserId(), KeyguardSecurityModel.SecurityMode.SimPuk);
            }
        };
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mTelephonyManager = telephonyManager;
        this.mSimImageView = (ImageView) keyguardSimPukView.findViewById(R.id.keyguard_sim);
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputViewController, com.android.keyguard.KeyguardInputViewController
    public final void onPause() {
        ProgressDialog progressDialog = this.mSimUnlockProgressDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
            this.mSimUnlockProgressDialog = null;
        }
    }

    @Override // com.android.keyguard.KeyguardPinBasedInputViewController, com.android.keyguard.KeyguardInputViewController
    public final void onResume(int i) {
        super.onResume(i);
        if (this.mShowDefaultMessage) {
            showDefaultMessage$1();
        }
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
        this.mStateMachine.reset();
    }

    @Override // com.android.keyguard.KeyguardAbsKeyInputViewController
    public final boolean shouldLockout(long j) {
        return false;
    }

    public final void showDefaultMessage$1() {
        String str;
        int i = this.mRemainingAttempts;
        KeyguardMessageAreaController keyguardMessageAreaController = this.mMessageAreaController;
        if (i >= 0) {
            KeyguardSimPukView keyguardSimPukView = (KeyguardSimPukView) this.mView;
            keyguardMessageAreaController.setMessage(keyguardSimPukView.getPukPasswordErrorMessage(i, true, KeyguardEsimArea.isEsimLocked(this.mSubId, keyguardSimPukView.getContext())), true);
            return;
        }
        boolean isEsimLocked = KeyguardEsimArea.isEsimLocked(this.mSubId, ((KeyguardSimPukView) this.mView).getContext());
        TelephonyManager telephonyManager = this.mTelephonyManager;
        int activeModemCount = telephonyManager != null ? telephonyManager.getActiveModemCount() : 1;
        Resources resources = ((KeyguardSimPukView) this.mView).getResources();
        TypedArray obtainStyledAttributes = ((KeyguardSimPukView) this.mView).getContext().obtainStyledAttributes(new int[]{android.R.attr.textColor});
        int color = obtainStyledAttributes.getColor(0, -1);
        obtainStyledAttributes.recycle();
        if (activeModemCount < 2) {
            str = resources.getString(R.string.kg_puk_enter_puk_hint);
        } else {
            SubscriptionInfo subscriptionInfoForSubId = this.mKeyguardUpdateMonitor.getSubscriptionInfoForSubId(this.mSubId);
            String displayName = subscriptionInfoForSubId != null ? subscriptionInfoForSubId.getDisplayName() : "";
            String string = !TextUtils.isEmpty(displayName) ? resources.getString(R.string.kg_puk_enter_puk_hint_multi, displayName) : resources.getString(R.string.kg_puk_enter_puk_hint);
            if (subscriptionInfoForSubId != null) {
                color = subscriptionInfoForSubId.getIconTint();
            }
            str = string;
        }
        if (isEsimLocked) {
            str = resources.getString(R.string.kg_sim_lock_esim_instructions, str);
        }
        keyguardMessageAreaController.setMessage(str, true);
        this.mSimImageView.setImageTintList(ColorStateList.valueOf(color));
        new AnonymousClass2(this, this.mSubId).start();
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
        int i;
        StateMachine stateMachine = this.mStateMachine;
        int i2 = stateMachine.mState;
        KeyguardSimPukViewController keyguardSimPukViewController = KeyguardSimPukViewController.this;
        if (i2 == 0) {
            PasswordTextView passwordTextView = keyguardSimPukViewController.mPasswordEntry;
            if (passwordTextView.mText.length() >= 8) {
                keyguardSimPukViewController.mPukText = passwordTextView.mText;
                stateMachine.mState = 1;
                i = R.string.kg_puk_enter_pin_hint;
            } else {
                i = R.string.kg_invalid_sim_puk_hint;
            }
        } else if (i2 == 1) {
            PasswordTextView passwordTextView2 = keyguardSimPukViewController.mPasswordEntry;
            int length = passwordTextView2.mText.length();
            if (length < 4 || length > 8) {
                i = R.string.kg_invalid_sim_pin_hint;
            } else {
                keyguardSimPukViewController.mPinText = passwordTextView2.mText;
                stateMachine.mState = 2;
                i = R.string.kg_enter_confirm_pin_hint;
            }
        } else if (i2 != 2) {
            i = 0;
        } else if (keyguardSimPukViewController.mPinText.equals(keyguardSimPukViewController.mPasswordEntry.mText)) {
            stateMachine.mState = 3;
            if (keyguardSimPukViewController.mSimUnlockProgressDialog == null) {
                ProgressDialog progressDialog = new ProgressDialog(((KeyguardSimPukView) keyguardSimPukViewController.mView).getContext());
                keyguardSimPukViewController.mSimUnlockProgressDialog = progressDialog;
                progressDialog.setMessage(((KeyguardSimPukView) keyguardSimPukViewController.mView).getResources().getString(R.string.kg_sim_unlock_progress_dialog_message));
                keyguardSimPukViewController.mSimUnlockProgressDialog.setIndeterminate(true);
                keyguardSimPukViewController.mSimUnlockProgressDialog.setCancelable(false);
                if (!(((KeyguardSimPukView) keyguardSimPukViewController.mView).getContext() instanceof Activity)) {
                    keyguardSimPukViewController.mSimUnlockProgressDialog.getWindow().setType(2009);
                }
            }
            keyguardSimPukViewController.mSimUnlockProgressDialog.show();
            if (keyguardSimPukViewController.mCheckSimPukThread == null) {
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(keyguardSimPukViewController, keyguardSimPukViewController.mPukText, keyguardSimPukViewController.mPinText, keyguardSimPukViewController.mSubId);
                keyguardSimPukViewController.mCheckSimPukThread = anonymousClass2;
                anonymousClass2.start();
            }
            i = R.string.keyguard_sim_unlock_progress_dialog_message;
        } else {
            stateMachine.mState = 1;
            i = R.string.kg_invalid_confirm_pin_hint;
        }
        ((KeyguardSimPukView) keyguardSimPukViewController.mView).resetPasswordText(true, true);
        if (i != 0) {
            keyguardSimPukViewController.mMessageAreaController.setMessage(i);
        }
    }
}

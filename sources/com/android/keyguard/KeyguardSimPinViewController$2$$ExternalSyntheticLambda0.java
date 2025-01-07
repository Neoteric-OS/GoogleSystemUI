package com.android.keyguard;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.telephony.PinResult;
import android.util.Log;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardSimPinViewController;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardSimPinViewController$2$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardSimPinViewController.AnonymousClass2 f$0;
    public final /* synthetic */ PinResult f$1;

    public /* synthetic */ KeyguardSimPinViewController$2$$ExternalSyntheticLambda0(KeyguardSimPinViewController.AnonymousClass2 anonymousClass2, PinResult pinResult, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass2;
        this.f$1 = pinResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 0;
        switch (this.$r8$classId) {
            case 0:
                KeyguardSimPinViewController.AnonymousClass2 anonymousClass2 = this.f$0;
                PinResult pinResult = this.f$1;
                anonymousClass2.this$0.mRemainingAttempts = pinResult.getAttemptsRemaining();
                ProgressDialog progressDialog = anonymousClass2.this$0.mSimUnlockProgressDialog;
                if (progressDialog != null) {
                    progressDialog.hide();
                }
                ((KeyguardSimPinView) anonymousClass2.this$0.mView).resetPasswordText(true, pinResult.getResult() != 0);
                if (pinResult.getResult() == 0) {
                    KeyguardSimPinViewController keyguardSimPinViewController = anonymousClass2.this$0;
                    keyguardSimPinViewController.mKeyguardUpdateMonitor.reportSimUnlocked(keyguardSimPinViewController.mSubId);
                    KeyguardSimPinViewController keyguardSimPinViewController2 = anonymousClass2.this$0;
                    keyguardSimPinViewController2.mRemainingAttempts = -1;
                    keyguardSimPinViewController2.mShowDefaultMessage = true;
                    keyguardSimPinViewController2.getKeyguardSecurityCallback().dismiss(anonymousClass2.this$0.mSelectedUserInteractor.getSelectedUserId(), KeyguardSecurityModel.SecurityMode.SimPin);
                } else {
                    anonymousClass2.this$0.mShowDefaultMessage = false;
                    if (pinResult.getResult() != 1) {
                        KeyguardSimPinViewController keyguardSimPinViewController3 = anonymousClass2.this$0;
                        keyguardSimPinViewController3.mMessageAreaController.setMessage(((KeyguardSimPinView) keyguardSimPinViewController3.mView).getResources().getString(R.string.kg_password_pin_failed), true);
                    } else if (pinResult.getAttemptsRemaining() <= 2) {
                        KeyguardSimPinViewController keyguardSimPinViewController4 = anonymousClass2.this$0;
                        String pinPasswordErrorMessage = keyguardSimPinViewController4.getPinPasswordErrorMessage(pinResult.getAttemptsRemaining());
                        AlertDialog alertDialog = keyguardSimPinViewController4.mRemainingAttemptsDialog;
                        if (alertDialog == null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(((KeyguardSimPinView) keyguardSimPinViewController4.mView).getContext());
                            builder.setMessage(pinPasswordErrorMessage);
                            builder.setCancelable(false);
                            builder.setNeutralButton(R.string.ok, (DialogInterface.OnClickListener) null);
                            AlertDialog create = builder.create();
                            keyguardSimPinViewController4.mRemainingAttemptsDialog = create;
                            create.getWindow().setType(2009);
                        } else {
                            alertDialog.setMessage(pinPasswordErrorMessage);
                        }
                        keyguardSimPinViewController4.mRemainingAttemptsDialog.show();
                    } else {
                        KeyguardSimPinViewController keyguardSimPinViewController5 = anonymousClass2.this$0;
                        keyguardSimPinViewController5.mMessageAreaController.setMessage(keyguardSimPinViewController5.getPinPasswordErrorMessage(pinResult.getAttemptsRemaining()), true);
                    }
                    Log.d("KeyguardSimPinView", "verifyPasswordAndUnlock  CheckSimPin.onSimCheckResponse: " + pinResult + " attemptsRemaining=" + pinResult.getAttemptsRemaining());
                }
                anonymousClass2.this$0.getKeyguardSecurityCallback().userActivity();
                anonymousClass2.this$0.mCheckSimPinThread = null;
                break;
            default:
                KeyguardSimPinViewController.AnonymousClass2 anonymousClass22 = this.f$0;
                PinResult pinResult2 = this.f$1;
                switch (anonymousClass22.$r8$classId) {
                    case 0:
                        ((KeyguardSimPinView) anonymousClass22.this$0.mView).post(new KeyguardSimPinViewController$2$$ExternalSyntheticLambda0(anonymousClass22, pinResult2, i));
                        break;
                    default:
                        Log.d("KeyguardSimPinView", "onSimCheckResponse  empty One result " + pinResult2.toString());
                        if (pinResult2.getAttemptsRemaining() >= 0) {
                            anonymousClass22.this$0.mRemainingAttempts = pinResult2.getAttemptsRemaining();
                            anonymousClass22.this$0.setLockedSimMessage();
                            break;
                        }
                        break;
                }
        }
    }
}

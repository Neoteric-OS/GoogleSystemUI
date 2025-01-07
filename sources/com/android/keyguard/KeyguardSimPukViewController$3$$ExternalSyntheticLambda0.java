package com.android.keyguard;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.telephony.PinResult;
import android.util.Log;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardSimPukViewController;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class KeyguardSimPukViewController$3$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ KeyguardSimPukViewController.AnonymousClass2 f$0;
    public final /* synthetic */ PinResult f$1;

    public /* synthetic */ KeyguardSimPukViewController$3$$ExternalSyntheticLambda0(KeyguardSimPukViewController.AnonymousClass2 anonymousClass2, PinResult pinResult, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass2;
        this.f$1 = pinResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = 0;
        switch (this.$r8$classId) {
            case 0:
                KeyguardSimPukViewController.AnonymousClass2 anonymousClass2 = this.f$0;
                PinResult pinResult = this.f$1;
                ProgressDialog progressDialog = anonymousClass2.this$0.mSimUnlockProgressDialog;
                if (progressDialog != null) {
                    progressDialog.hide();
                }
                ((KeyguardSimPukView) anonymousClass2.this$0.mView).resetPasswordText(true, pinResult.getResult() != 0);
                if (pinResult.getResult() == 0) {
                    KeyguardSimPukViewController keyguardSimPukViewController = anonymousClass2.this$0;
                    keyguardSimPukViewController.mKeyguardUpdateMonitor.reportSimUnlocked(keyguardSimPukViewController.mSubId);
                    KeyguardSimPukViewController keyguardSimPukViewController2 = anonymousClass2.this$0;
                    keyguardSimPukViewController2.mRemainingAttempts = -1;
                    keyguardSimPukViewController2.mShowDefaultMessage = true;
                    keyguardSimPukViewController2.getKeyguardSecurityCallback().dismiss(anonymousClass2.this$0.mSelectedUserInteractor.getSelectedUserId(), KeyguardSecurityModel.SecurityMode.SimPuk);
                } else {
                    anonymousClass2.this$0.mShowDefaultMessage = false;
                    if (pinResult.getResult() == 1) {
                        KeyguardSimPukViewController keyguardSimPukViewController3 = anonymousClass2.this$0;
                        keyguardSimPukViewController3.mMessageAreaController.setMessage(((KeyguardSimPukView) keyguardSimPukViewController3.mView).getPukPasswordErrorMessage(pinResult.getAttemptsRemaining(), false, KeyguardEsimArea.isEsimLocked(anonymousClass2.this$0.mSubId, ((KeyguardSimPukView) anonymousClass2.this$0.mView).getContext())), true);
                        if (pinResult.getAttemptsRemaining() <= 2) {
                            KeyguardSimPukViewController keyguardSimPukViewController4 = anonymousClass2.this$0;
                            int attemptsRemaining = pinResult.getAttemptsRemaining();
                            KeyguardSimPukView keyguardSimPukView = (KeyguardSimPukView) keyguardSimPukViewController4.mView;
                            String pukPasswordErrorMessage = keyguardSimPukView.getPukPasswordErrorMessage(attemptsRemaining, false, KeyguardEsimArea.isEsimLocked(keyguardSimPukViewController4.mSubId, keyguardSimPukView.getContext()));
                            AlertDialog alertDialog = keyguardSimPukViewController4.mRemainingAttemptsDialog;
                            if (alertDialog == null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(((KeyguardSimPukView) keyguardSimPukViewController4.mView).getContext());
                                builder.setMessage(pukPasswordErrorMessage);
                                builder.setCancelable(false);
                                builder.setNeutralButton(R.string.ok, (DialogInterface.OnClickListener) null);
                                AlertDialog create = builder.create();
                                keyguardSimPukViewController4.mRemainingAttemptsDialog = create;
                                create.getWindow().setType(2009);
                            } else {
                                alertDialog.setMessage(pukPasswordErrorMessage);
                            }
                            keyguardSimPukViewController4.mRemainingAttemptsDialog.show();
                        } else {
                            KeyguardSimPukViewController keyguardSimPukViewController5 = anonymousClass2.this$0;
                            keyguardSimPukViewController5.mMessageAreaController.setMessage(((KeyguardSimPukView) keyguardSimPukViewController5.mView).getPukPasswordErrorMessage(pinResult.getAttemptsRemaining(), false, KeyguardEsimArea.isEsimLocked(anonymousClass2.this$0.mSubId, ((KeyguardSimPukView) anonymousClass2.this$0.mView).getContext())), true);
                        }
                    } else {
                        KeyguardSimPukViewController keyguardSimPukViewController6 = anonymousClass2.this$0;
                        keyguardSimPukViewController6.mMessageAreaController.setMessage(((KeyguardSimPukView) keyguardSimPukViewController6.mView).getResources().getString(R.string.kg_password_puk_failed), true);
                    }
                    if (KeyguardSimPukViewController.DEBUG) {
                        Log.d("KeyguardSimPukView", "verifyPasswordAndUnlock  UpdateSim.onSimCheckResponse:  attemptsRemaining=" + pinResult.getAttemptsRemaining());
                    }
                }
                anonymousClass2.this$0.mStateMachine.reset();
                anonymousClass2.this$0.mCheckSimPukThread = null;
                break;
            default:
                KeyguardSimPukViewController.AnonymousClass2 anonymousClass22 = this.f$0;
                PinResult pinResult2 = this.f$1;
                switch (anonymousClass22.$r8$classId) {
                    case 0:
                        if (pinResult2 != null) {
                            Log.d("KeyguardSimPukView", "onSimCheckResponse  empty One result " + pinResult2.toString());
                            if (pinResult2.getAttemptsRemaining() >= 0) {
                                anonymousClass22.this$0.mRemainingAttempts = pinResult2.getAttemptsRemaining();
                                KeyguardSimPukViewController keyguardSimPukViewController7 = anonymousClass22.this$0;
                                keyguardSimPukViewController7.mMessageAreaController.setMessage(((KeyguardSimPukView) keyguardSimPukViewController7.mView).getPukPasswordErrorMessage(pinResult2.getAttemptsRemaining(), true, KeyguardEsimArea.isEsimLocked(anonymousClass22.this$0.mSubId, ((KeyguardSimPukView) anonymousClass22.this$0.mView).getContext())), true);
                                break;
                            }
                        } else {
                            Log.e("KeyguardSimPukView", "onSimCheckResponse, pin result is NULL");
                            break;
                        }
                        break;
                    default:
                        ((KeyguardSimPukView) anonymousClass22.this$0.mView).post(new KeyguardSimPukViewController$3$$ExternalSyntheticLambda0(anonymousClass22, pinResult2, i));
                        break;
                }
        }
    }
}

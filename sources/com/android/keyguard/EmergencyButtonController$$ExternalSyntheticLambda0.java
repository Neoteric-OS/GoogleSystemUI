package com.android.keyguard;

import android.R;
import android.app.ActivityOptions;
import android.os.UserHandle;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.View;
import com.android.keyguard.EmergencyButtonController;
import com.android.systemui.util.Assert;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class EmergencyButtonController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ EmergencyButtonController f$0;

    public /* synthetic */ EmergencyButtonController$$ExternalSyntheticLambda0(EmergencyButtonController emergencyButtonController, int i) {
        this.$r8$classId = i;
        this.f$0 = emergencyButtonController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        final EmergencyButtonController emergencyButtonController = this.f$0;
        switch (i) {
            case 0:
                emergencyButtonController.updateEmergencyCallButton();
                break;
            case 1:
                TelecomManager telecomManager = emergencyButtonController.mTelecomManager;
                final boolean z = telecomManager != null && telecomManager.isInCall();
                final boolean isSecure = emergencyButtonController.mLockPatternUtils.isSecure(emergencyButtonController.mSelectedUserInteractor.getSelectedUserId());
                emergencyButtonController.mMainExecutor.execute(new Runnable() { // from class: com.android.keyguard.EmergencyButtonController$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        EmergencyButtonController emergencyButtonController2 = EmergencyButtonController.this;
                        boolean z2 = z;
                        boolean z3 = isSecure;
                        View view = emergencyButtonController2.mView;
                        EmergencyButton emergencyButton = (EmergencyButton) view;
                        boolean hasSystemFeature = view.getContext().getPackageManager().hasSystemFeature("android.hardware.telephony");
                        boolean isSimPinSecure = emergencyButtonController2.mKeyguardUpdateMonitor.isSimPinSecure();
                        emergencyButton.getClass();
                        if (!hasSystemFeature) {
                            z3 = false;
                        } else if (z2) {
                            z3 = true;
                        } else if (isSimPinSecure) {
                            z3 = emergencyButton.mEnableEmergencyCallWhileSimLocked;
                        }
                        if (!z3) {
                            emergencyButton.setVisibility(8);
                        } else {
                            emergencyButton.setVisibility(0);
                            emergencyButton.setText(z2 ? R.string.managed_profile_app_label : R.string.lockscreen_permanent_disabled_sim_instructions);
                        }
                    }
                });
                break;
            default:
                TelecomManager telecomManager2 = emergencyButtonController.mTelecomManager;
                final boolean z2 = telecomManager2 != null && telecomManager2.isInCall();
                emergencyButtonController.mMainExecutor.execute(new Runnable() { // from class: com.android.keyguard.EmergencyButtonController$$ExternalSyntheticLambda5
                    @Override // java.lang.Runnable
                    public final void run() {
                        EmergencyButtonController emergencyButtonController2 = EmergencyButtonController.this;
                        if (z2) {
                            emergencyButtonController2.mTelecomManager.showInCallScreen(false);
                            EmergencyButtonController.EmergencyButtonCallback emergencyButtonCallback = emergencyButtonController2.mEmergencyButtonCallback;
                            if (emergencyButtonCallback != null) {
                                emergencyButtonCallback.onEmergencyButtonClickedWhenInCall();
                                return;
                            }
                            return;
                        }
                        KeyguardUpdateMonitor keyguardUpdateMonitor = emergencyButtonController2.mKeyguardUpdateMonitor;
                        keyguardUpdateMonitor.getClass();
                        Assert.isMainThread();
                        keyguardUpdateMonitor.handleReportEmergencyCallAction();
                        TelecomManager telecomManager3 = emergencyButtonController2.mTelecomManager;
                        if (telecomManager3 == null) {
                            Log.wtf("EmergencyButton", "TelecomManager was null, cannot launch emergency dialer");
                        } else {
                            emergencyButtonController2.mView.getContext().startActivityAsUser(telecomManager3.createLaunchEmergencyDialerIntent(null).setFlags(343932928).putExtra("com.android.phone.EmergencyDialer.extra.ENTRY_TYPE", 1), ActivityOptions.makeCustomAnimation(emergencyButtonController2.mView.getContext(), 0, 0).toBundle(), new UserHandle(emergencyButtonController2.mSelectedUserInteractor.getSelectedUserId()));
                        }
                    }
                });
                break;
        }
    }
}

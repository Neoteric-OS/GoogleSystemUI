package com.google.android.systemui.reversecharging;

import android.text.TextUtils;
import android.util.Log;
import com.android.settingslib.Utils;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.wm.shell.R;
import com.google.android.systemui.keyguard.domain.interactor.AmbientIndicationInteractor;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ReverseChargingViewController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ReverseChargingViewController f$0;

    public /* synthetic */ ReverseChargingViewController$$ExternalSyntheticLambda0(ReverseChargingViewController reverseChargingViewController) {
        this.f$0 = reverseChargingViewController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ReverseChargingViewController reverseChargingViewController = this.f$0;
        if (reverseChargingViewController.mReverse || !((BatteryControllerImpl) reverseChargingViewController.mBatteryController).mWirelessCharging || TextUtils.isEmpty(reverseChargingViewController.mName)) {
            AmbientIndicationInteractor ambientIndicationInteractor = reverseChargingViewController.mAmbientIndicationInteractor;
            String str = reverseChargingViewController.mProvidingBattery ? reverseChargingViewController.mReverseCharging : "";
            StateFlowImpl stateFlowImpl = ambientIndicationInteractor.ambientIndicationRepository.reverseChargingMessage;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, str);
            reverseChargingViewController.mKeyguardIndicationController.setReverseChargingMessage(reverseChargingViewController.mProvidingBattery ? reverseChargingViewController.mReverseCharging : "");
            if (ReverseChargingViewController.DEBUG) {
                StringBuilder sb = new StringBuilder("updateMessage(): rtx=");
                sb.append(reverseChargingViewController.mReverse ? 1 : 0);
                sb.append(" rtxString=");
                sb.append(reverseChargingViewController.mProvidingBattery ? reverseChargingViewController.mReverseCharging : "");
                Log.d("ReverseChargingViewCtrl", sb.toString());
            }
        } else {
            String string = reverseChargingViewController.mContext.getResources().getString(R.string.reverse_charging_device_providing_charge_text, reverseChargingViewController.mName, Utils.formatPercentage(reverseChargingViewController.mLevel));
            if (ReverseChargingViewController.DEBUG) {
                Log.d("ReverseChargingViewCtrl", "updateMessage(): rtx=" + (reverseChargingViewController.mReverse ? 1 : 0) + " wlcString=" + string);
            }
            StateFlowImpl stateFlowImpl2 = reverseChargingViewController.mAmbientIndicationInteractor.ambientIndicationRepository.wirelessChargingMessage;
            stateFlowImpl2.getClass();
            stateFlowImpl2.updateState(null, string);
            reverseChargingViewController.mKeyguardIndicationController.setReverseChargingMessage(string);
        }
        ((StatusBarIconControllerImpl) reverseChargingViewController.mStatusBarIconController).setIcon(reverseChargingViewController.mContentDescription, reverseChargingViewController.mSlotReverseCharging, R.drawable.ic_qs_reverse_charging);
        ((StatusBarIconControllerImpl) reverseChargingViewController.mStatusBarIconController).setIconVisibility(reverseChargingViewController.mSlotReverseCharging, reverseChargingViewController.mProvidingBattery);
    }
}

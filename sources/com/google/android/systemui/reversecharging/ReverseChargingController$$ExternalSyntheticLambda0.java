package com.google.android.systemui.reversecharging;

import android.nfc.NfcAdapter;
import android.util.Log;
import vendor.google.wireless_charger.IWirelessCharger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ReverseChargingController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ReverseChargingController f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ ReverseChargingController$$ExternalSyntheticLambda0(ReverseChargingController reverseChargingController, boolean z, int i) {
        this.$r8$classId = i;
        this.f$0 = reverseChargingController;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ReverseChargingController reverseChargingController = this.f$0;
                boolean z = this.f$1;
                reverseChargingController.getClass();
                Log.i("ReverseChargingControl", "setRtxMode(): rtx=" + (z ? 1 : 0));
                ReverseWirelessCharger reverseWirelessCharger = (ReverseWirelessCharger) reverseChargingController.mRtxChargerManagerOptional.get();
                if (reverseWirelessCharger.initHALInterface()) {
                    try {
                        ((IWirelessCharger.Stub.Proxy) reverseWirelessCharger.mWirelessCharger).setRtxMode(z);
                        break;
                    } catch (Exception e) {
                        Log.w("ReverseWirelessCharger", "setRtxMode fail: ", e);
                        return;
                    }
                }
                break;
            default:
                ReverseChargingController reverseChargingController2 = this.f$0;
                boolean z2 = this.f$1;
                reverseChargingController2.getClass();
                try {
                    NfcAdapter.getDefaultAdapter(reverseChargingController2.mContext).setReaderModePollingEnabled(z2);
                    break;
                } catch (Exception e2) {
                    Log.e("ReverseChargingControl", "Could not change NFC reader mode, exception: " + e2);
                }
        }
    }
}

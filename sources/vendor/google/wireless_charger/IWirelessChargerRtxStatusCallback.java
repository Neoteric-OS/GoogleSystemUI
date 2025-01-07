package vendor.google.wireless_charger;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.util.Log;
import com.google.android.systemui.reversecharging.ReverseChargingController$$ExternalSyntheticLambda9;
import com.google.android.systemui.reversecharging.ReverseWirelessCharger;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface IWirelessChargerRtxStatusCallback extends IInterface {
    public static final String DESCRIPTOR = "vendor$google$wireless_charger$IWirelessChargerRtxStatusCallback".replace('$', '.');

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Stub extends Binder implements IWirelessChargerRtxStatusCallback {
        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            ArrayList arrayList;
            String str = IWirelessChargerRtxStatusCallback.DESCRIPTOR;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface(str);
            }
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            if (i == 16777215) {
                parcel2.writeNoException();
                parcel2.writeInt(2);
                return true;
            }
            if (i == 16777214) {
                parcel2.writeNoException();
                parcel2.writeString("dfeae26730e4bd7209e33722e100616f4d3b6c41");
                return true;
            }
            if (i != 1) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            RtxStatusInfo rtxStatusInfo = (RtxStatusInfo) parcel.readTypedObject(RtxStatusInfo.CREATOR);
            parcel.enforceNoDataAvail();
            ReverseWirelessCharger reverseWirelessCharger = (ReverseWirelessCharger) this;
            synchronized (reverseWirelessCharger.mLock) {
                arrayList = new ArrayList(reverseWirelessCharger.mRtxStatusCallbacks);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ReverseChargingController$$ExternalSyntheticLambda9 reverseChargingController$$ExternalSyntheticLambda9 = (ReverseChargingController$$ExternalSyntheticLambda9) it.next();
                reverseChargingController$$ExternalSyntheticLambda9.getClass();
                if (ReverseWirelessCharger.DEBUG) {
                    Log.d("ReverseWirelessCharger", "onRtxStatusChanged() RtxStatusInfo : " + rtxStatusInfo.toString());
                }
                Bundle bundle = new Bundle();
                bundle.putInt("key_rtx_mode", rtxStatusInfo.mode);
                bundle.putInt("key_accessory_type", rtxStatusInfo.acctype);
                bundle.putBoolean("key_rtx_connection", rtxStatusInfo.chgConnected);
                bundle.putInt("key_rtx_iout", rtxStatusInfo.iout);
                bundle.putInt("key_rtx_vout", rtxStatusInfo.vout);
                bundle.putInt("key_rtx_level", rtxStatusInfo.level);
                bundle.putInt("key_reason_type", rtxStatusInfo.reason);
                reverseChargingController$$ExternalSyntheticLambda9.f$0.onReverseStateChanged(bundle);
            }
            return true;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}

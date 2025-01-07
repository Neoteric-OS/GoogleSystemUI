package com.google.android.systemui.power;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryEventClient$listener$1 extends Binder implements IBatteryEventsListener {
    public final /* synthetic */ BatteryEventClient this$0;

    public BatteryEventClient$listener$1(BatteryEventClient batteryEventClient) {
        this.this$0 = batteryEventClient;
        attachInterface(this, "com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener");
    }

    @Override // com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener
    public final void onBatteryEventChanged(int i, int i2, List list) {
        this.this$0.logWithCaller.d("onBatteryEventChanged: " + list + ", batteryLevel: " + i);
        Function3 function3 = this.this$0.onBatteryEventUpdate;
        if (list == null) {
            list = EmptyList.INSTANCE;
        }
        function3.invoke(list, Integer.valueOf(i), Integer.valueOf(i2));
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener");
            return true;
        }
        if (i != 1) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        ArrayList createTypedArrayList = parcel.createTypedArrayList(BatteryEventType.CREATOR);
        int readInt = parcel.readInt();
        int readInt2 = parcel.readInt();
        parcel.enforceNoDataAvail();
        onBatteryEventChanged(readInt, readInt2, createTypedArrayList);
        parcel2.writeNoException();
        return true;
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}

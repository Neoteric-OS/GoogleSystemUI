package com.google.android.systemui.power.batteryevent.aidl;

import android.os.IBinder;
import android.os.Parcel;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IBatteryEventsListener$Stub$Proxy implements IBatteryEventsListener {
    public IBinder mRemote;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.mRemote;
    }

    @Override // com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener
    public final void onBatteryEventChanged(int i, int i2, List list) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener");
            obtain.writeTypedList(list, 0);
            obtain.writeInt(i);
            obtain.writeInt(i2);
            this.mRemote.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}

package com.google.android.systemui.power.batteryevent.aidl;

import android.os.IBinder;
import android.os.Parcel;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IBatteryEventService$Stub$Proxy implements IBatteryEventService {
    public IBinder mRemote;

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.mRemote;
    }

    @Override // com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService
    public final void registerBatteryEventsCallback(IBatteryEventsListener iBatteryEventsListener, List list, SurfaceType surfaceType) {
        Parcel obtain = Parcel.obtain(this.mRemote);
        try {
            obtain.writeInterfaceToken("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService");
            obtain.writeStrongInterface(iBatteryEventsListener);
            obtain.writeTypedList(list, 0);
            obtain.writeTypedObject(surfaceType, 0);
            this.mRemote.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }
}

package com.google.android.systemui.power.batteryevent.domain;

import android.content.ComponentName;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.util.Log;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener;
import com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener$Stub$Proxy;
import com.google.android.systemui.power.batteryevent.aidl.SurfaceType;
import com.google.android.systemui.power.batteryevent.domain.BatteryEventService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptySet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryEventService$binder$1 extends Binder implements IBatteryEventService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final /* synthetic */ BatteryEventService this$0;

    public BatteryEventService$binder$1(BatteryEventService batteryEventService) {
        this.this$0 = batteryEventService;
        attachInterface(this, "com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService");
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        BatteryEventService batteryEventService = this.this$0;
        Set set = BatteryEventService.supportedCallers;
        batteryEventService.getClass();
        int callingUid = Binder.getCallingUid();
        Log.d("BatteryEventService", "ensureSupportedCallers: uid=" + callingUid);
        String[] packagesForUid = batteryEventService.getApplicationContext().getPackageManager().getPackagesForUid(callingUid);
        if (packagesForUid != null) {
            for (String str : packagesForUid) {
                if (!BatteryEventService.supportedCallers.contains(str)) {
                }
            }
            throw new SecurityException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("ensureSupportedCallers: ", Arrays.toString(packagesForUid)));
        }
        return onTransact$com$google$android$systemui$power$batteryevent$aidl$IBatteryEventService$Stub(i, parcel, parcel2, i2);
    }

    public final boolean onTransact$com$google$android$systemui$power$batteryevent$aidl$IBatteryEventService$Stub(int i, Parcel parcel, Parcel parcel2, int i2) {
        Set set;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService");
            return true;
        }
        IBatteryEventsListener iBatteryEventsListener = null;
        Object obj = null;
        IBatteryEventsListener iBatteryEventsListener2 = null;
        if (i == 1) {
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof IBatteryEventsListener)) {
                    IBatteryEventsListener$Stub$Proxy iBatteryEventsListener$Stub$Proxy = new IBatteryEventsListener$Stub$Proxy();
                    iBatteryEventsListener$Stub$Proxy.mRemote = readStrongBinder;
                    iBatteryEventsListener = iBatteryEventsListener$Stub$Proxy;
                } else {
                    iBatteryEventsListener = (IBatteryEventsListener) queryLocalInterface;
                }
            }
            ArrayList createTypedArrayList = parcel.createTypedArrayList(BatteryEventType.CREATOR);
            SurfaceType surfaceType = (SurfaceType) parcel.readTypedObject(SurfaceType.CREATOR);
            parcel.enforceNoDataAvail();
            registerBatteryEventsCallback(iBatteryEventsListener, createTypedArrayList, surfaceType);
        } else if (i == 2) {
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            if (readStrongBinder2 != null) {
                IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.systemui.power.batteryevent.aidl.IBatteryEventsListener");
                if (queryLocalInterface2 == null || !(queryLocalInterface2 instanceof IBatteryEventsListener)) {
                    IBatteryEventsListener$Stub$Proxy iBatteryEventsListener$Stub$Proxy2 = new IBatteryEventsListener$Stub$Proxy();
                    iBatteryEventsListener$Stub$Proxy2.mRemote = readStrongBinder2;
                    iBatteryEventsListener2 = iBatteryEventsListener$Stub$Proxy2;
                } else {
                    iBatteryEventsListener2 = (IBatteryEventsListener) queryLocalInterface2;
                }
            }
            parcel.enforceNoDataAvail();
            this.this$0.aidlBatteryEventsCallbackListener.unregister(iBatteryEventsListener2);
        } else if (i == 3) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            ArrayList createTypedArrayList2 = parcel.createTypedArrayList(BatteryEventType.CREATOR);
            int readInt = parcel.readInt();
            parcel.enforceNoDataAvail();
            if (readString == null || readString2 == null) {
                Log.w("BatteryEventService", "registerBatteryEventsUpdate failed: packageName=" + readString + ", className=" + readString2);
            } else if (createTypedArrayList2 == null || !createTypedArrayList2.isEmpty()) {
                ComponentName componentName = new ComponentName(readString, readString2);
                if (createTypedArrayList2 == null || (set = CollectionsKt.toSet(createTypedArrayList2)) == null) {
                    set = EmptySet.INSTANCE;
                }
                this.this$0.broadcastIntentBatteryEventsListener.add(new BatteryEventService.BatteryEventsBroadcastData(componentName, set, readInt, readString + "/" + readString2 + "-" + readInt));
            } else {
                Log.w("BatteryEventService", "no battery events to subscribe");
            }
        } else if (i == 4) {
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            int readInt2 = parcel.readInt();
            parcel.enforceNoDataAvail();
            if (readString3 == null || readString4 == null) {
                Log.w("BatteryEventService", "unregisterBatteryEventsUpdate failed. packageName=" + readString3 + ", className=" + readString4);
            } else {
                Iterator it = this.this$0.broadcastIntentBatteryEventsListener.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    BatteryEventService.BatteryEventsBroadcastData batteryEventsBroadcastData = (BatteryEventService.BatteryEventsBroadcastData) next;
                    if (Intrinsics.areEqual(batteryEventsBroadcastData.componentName.getPackageName(), readString3) && Intrinsics.areEqual(batteryEventsBroadcastData.componentName.getClassName(), readString4) && batteryEventsBroadcastData.userId == readInt2) {
                        obj = next;
                        break;
                    }
                }
                BatteryEventService.BatteryEventsBroadcastData batteryEventsBroadcastData2 = (BatteryEventService.BatteryEventsBroadcastData) obj;
                if (batteryEventsBroadcastData2 != null) {
                    BatteryEventService batteryEventService = this.this$0;
                    batteryEventService.broadcastIntentBatteryEventsListener.remove(batteryEventsBroadcastData2);
                }
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("unregisterBatteryEventsUpdate:packageName: ", readString3, ", className: ", readString4, ", userId: ");
                m.append(readInt2);
                Log.i("BatteryEventService", m.toString());
            }
        } else {
            if (i != 5) {
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceNoDataAvail();
        }
        return true;
    }

    @Override // com.google.android.systemui.power.batteryevent.aidl.IBatteryEventService
    public final void registerBatteryEventsCallback(IBatteryEventsListener iBatteryEventsListener, List list, SurfaceType surfaceType) {
        Set set;
        if (list == null || (set = CollectionsKt.toSet(list)) == null) {
            set = EmptySet.INSTANCE;
        }
        this.this$0.aidlBatteryEventsCallbackListener.register(iBatteryEventsListener, new BatteryEventService.BatteryEventsCallbackData(set, surfaceType));
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}

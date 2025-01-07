package com.google.android.systemui.power.batteryhealth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HealthService$binder$1 extends Binder implements IInterface {
    public final /* synthetic */ HealthService this$0;

    public HealthService$binder$1(HealthService healthService) {
        this.this$0 = healthService;
        attachInterface(this, "com.google.android.systemui.power.batteryhealth.IHealthService");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [com.google.android.systemui.power.batteryhealth.IHealthListener] */
    /* JADX WARN: Type inference failed for: r7v8, types: [com.google.android.systemui.power.batteryhealth.IHealthListener] */
    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        IHealthListener$Stub$Proxy iHealthListener$Stub$Proxy;
        IHealthListener$Stub$Proxy iHealthListener$Stub$Proxy2;
        if (i >= 1 && i <= 16777215) {
            parcel.enforceInterface("com.google.android.systemui.power.batteryhealth.IHealthService");
        }
        if (i == 1598968902) {
            parcel2.writeString("com.google.android.systemui.power.batteryhealth.IHealthService");
            return true;
        }
        switch (i) {
            case 1:
                HealthData healthData = (HealthData) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new HealthService$binder$1$getHealthData$1(HealthService.access$ensureSupportedCallers(this.this$0), this.this$0, null));
                parcel2.writeNoException();
                parcel2.writeTypedObject(healthData, 1);
                return true;
            case 2:
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    iHealthListener$Stub$Proxy = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.systemui.power.batteryhealth.IHealthListener");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IHealthListener)) {
                        IHealthListener$Stub$Proxy iHealthListener$Stub$Proxy3 = new IHealthListener$Stub$Proxy();
                        iHealthListener$Stub$Proxy3.mRemote = readStrongBinder;
                        iHealthListener$Stub$Proxy = iHealthListener$Stub$Proxy3;
                    } else {
                        iHealthListener$Stub$Proxy = (IHealthListener) queryLocalInterface;
                    }
                }
                parcel.enforceNoDataAvail();
                String[] access$ensureSupportedCallers = HealthService.access$ensureSupportedCallers(this.this$0);
                HealthService healthService = this.this$0;
                BuildersKt.launch$default(healthService.mainScope, null, null, new HealthService$binder$1$registerHealthListener$1(access$ensureSupportedCallers, healthService, iHealthListener$Stub$Proxy, null), 3);
                return true;
            case 3:
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 == null) {
                    iHealthListener$Stub$Proxy2 = null;
                } else {
                    IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.google.android.systemui.power.batteryhealth.IHealthListener");
                    if (queryLocalInterface2 == null || !(queryLocalInterface2 instanceof IHealthListener)) {
                        IHealthListener$Stub$Proxy iHealthListener$Stub$Proxy4 = new IHealthListener$Stub$Proxy();
                        iHealthListener$Stub$Proxy4.mRemote = readStrongBinder2;
                        iHealthListener$Stub$Proxy2 = iHealthListener$Stub$Proxy4;
                    } else {
                        iHealthListener$Stub$Proxy2 = (IHealthListener) queryLocalInterface2;
                    }
                }
                parcel.enforceNoDataAvail();
                String[] access$ensureSupportedCallers2 = HealthService.access$ensureSupportedCallers(this.this$0);
                HealthService healthService2 = this.this$0;
                BuildersKt.launch$default(healthService2.mainScope, null, null, new HealthService$binder$1$unregisterHealthListener$1(access$ensureSupportedCallers2, healthService2, iHealthListener$Stub$Proxy2, null), 3);
                return true;
            case 4:
                IncompatibleChargerData incompatibleChargerData = (IncompatibleChargerData) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new HealthService$binder$1$getIncompatibleChargerData$1(HealthService.access$ensureSupportedCallers(this.this$0), this.this$0, null));
                parcel2.writeNoException();
                parcel2.writeTypedObject(incompatibleChargerData, 1);
                return true;
            case 5:
                int readInt = parcel.readInt();
                parcel.enforceNoDataAvail();
                HealthData healthData2 = (HealthData) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new HealthService$binder$1$getHealthDataWithAlgo$1(readInt, HealthService.access$ensureSupportedCallers(this.this$0), this.this$0, null));
                parcel2.writeNoException();
                parcel2.writeTypedObject(healthData2, 1);
                return true;
            case 6:
                int readInt2 = parcel.readInt();
                parcel.enforceNoDataAvail();
                boolean booleanValue = ((Boolean) BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new HealthService$binder$1$setChargingPolicy$1(readInt2, HealthService.access$ensureSupportedCallers(this.this$0), this.this$0, null))).booleanValue();
                parcel2.writeNoException();
                parcel2.writeBoolean(booleanValue);
                return true;
            default:
                return super.onTransact(i, parcel, parcel2, i2);
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }
}

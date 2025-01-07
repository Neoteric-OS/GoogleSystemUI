package com.google.android.systemui.power;

import android.os.Parcel;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import vendor.google.google_battery.IGoogleBattery;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class BatteryDefenderNotificationHandler$onActionBootCompleted$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BatteryDefenderNotificationHandler this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryDefenderNotificationHandler$onActionBootCompleted$1(BatteryDefenderNotificationHandler batteryDefenderNotificationHandler, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryDefenderNotificationHandler;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryDefenderNotificationHandler$onActionBootCompleted$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryDefenderNotificationHandler$onActionBootCompleted$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BatteryDefenderNotificationHandler batteryDefenderNotificationHandler = this.this$0;
            this.label = 1;
            int i2 = BatteryDefenderNotificationHandler.$r8$clinit;
            BatteryDefenderNotificationHandler$enableDockDefenderFeature$2 batteryDefenderNotificationHandler$enableDockDefenderFeature$2 = new Function1() { // from class: com.google.android.systemui.power.BatteryDefenderNotificationHandler$enableDockDefenderFeature$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Log.d("BatteryDefenderNotification", "enable dock defend");
                    IGoogleBattery.Stub.Proxy proxy = (IGoogleBattery.Stub.Proxy) ((IGoogleBattery) obj2);
                    Parcel obtain = Parcel.obtain(proxy.mRemote);
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken(IGoogleBattery.DESCRIPTOR);
                        obtain.writeInt(7);
                        obtain.writeBoolean(true);
                        if (!proxy.mRemote.transact(1, obtain, obtain2, 0)) {
                            throw new RemoteException("Method setEnable is unimplemented.");
                        }
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return Unit.INSTANCE;
                    } catch (Throwable th) {
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                }
            };
            batteryDefenderNotificationHandler.getClass();
            Object withContext = BuildersKt.withContext(batteryDefenderNotificationHandler.backgroundDispatcher, new BatteryDefenderNotificationHandler$withGoogleBattery$2(batteryDefenderNotificationHandler, batteryDefenderNotificationHandler$enableDockDefenderFeature$2, null), this);
            if (withContext != coroutineSingletons) {
                withContext = unit;
            }
            if (withContext != coroutineSingletons) {
                withContext = unit;
            }
            if (withContext == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Settings.Global.putInt(this.this$0.context.getContentResolver(), "dock_defender_bypass", 0);
        return unit;
    }
}

package com.google.android.systemui.power.batteryhealth;

import android.content.SharedPreferences;
import android.os.Parcel;
import android.util.Log;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.reflect.KProperty;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.CancellableFlow;
import kotlinx.coroutines.flow.CancellableFlowImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class HealthService$registeredListenerNum$2$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ HealthService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HealthService$registeredListenerNum$2$1(HealthService healthService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = healthService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new HealthService$registeredListenerNum$2$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((HealthService$registeredListenerNum$2$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final HealthService healthService = this.this$0;
            this.label = 1;
            KProperty[] kPropertyArr = HealthService.$$delegatedProperties;
            healthService.getClass();
            Log.d("HealthService", "Subscribe listeners");
            HealthManager healthManager = healthService.healthManager;
            healthManager.getClass();
            Flow flowOn = FlowKt.flowOn(FlowKt.callbackFlow(new HealthManager$getHealthDataFlow$1(healthManager, null)), healthManager.bgDispatcher);
            if (!(flowOn instanceof CancellableFlow)) {
                flowOn = new CancellableFlowImpl(flowOn);
            }
            Object collect = ((CancellableFlow) flowOn).collect(new FlowCollector() { // from class: com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2
                /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    final Pair pair = (Pair) obj2;
                    String str = (String) pair.getSecond();
                    if (str != null) {
                        if (((SharedPreferences) pair.getFirst()).contains(str)) {
                            Log.i("HealthService", "Notify data update for key: ".concat(str));
                            int hashCode = str.hashCode();
                            HealthService healthService2 = HealthService.this;
                            switch (hashCode) {
                                case -2076922611:
                                    if (str.equals("capacity_index")) {
                                        Object access$notifyListeners = HealthService.access$notifyListeners(healthService2, new Function1() { // from class: com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2$1$3
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj3) {
                                                int i2 = ((SharedPreferences) Pair.this.getFirst()).getInt("capacity_index", -1);
                                                IHealthListener$Stub$Proxy iHealthListener$Stub$Proxy = (IHealthListener$Stub$Proxy) ((IHealthListener) obj3);
                                                Parcel obtain = Parcel.obtain(iHealthListener$Stub$Proxy.mRemote);
                                                try {
                                                    obtain.writeInterfaceToken("com.google.android.systemui.power.batteryhealth.IHealthListener");
                                                    obtain.writeInt(i2);
                                                    iHealthListener$Stub$Proxy.mRemote.transact(3, obtain, null, 1);
                                                    obtain.recycle();
                                                    return Unit.INSTANCE;
                                                } catch (Throwable th) {
                                                    obtain.recycle();
                                                    throw th;
                                                }
                                            }
                                        }, continuation);
                                        if (access$notifyListeners == CoroutineSingletons.COROUTINE_SUSPENDED) {
                                            return access$notifyListeners;
                                        }
                                    }
                                    break;
                                case 851466543:
                                    if (str.equals("health_index")) {
                                        Object access$notifyListeners2 = HealthService.access$notifyListeners(healthService2, new Function1() { // from class: com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2$1$1
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj3) {
                                                int i2 = ((SharedPreferences) Pair.this.getFirst()).getInt("health_index", -1);
                                                IHealthListener$Stub$Proxy iHealthListener$Stub$Proxy = (IHealthListener$Stub$Proxy) ((IHealthListener) obj3);
                                                Parcel obtain = Parcel.obtain(iHealthListener$Stub$Proxy.mRemote);
                                                try {
                                                    obtain.writeInterfaceToken("com.google.android.systemui.power.batteryhealth.IHealthListener");
                                                    obtain.writeInt(i2);
                                                    iHealthListener$Stub$Proxy.mRemote.transact(1, obtain, null, 1);
                                                    obtain.recycle();
                                                    return Unit.INSTANCE;
                                                } catch (Throwable th) {
                                                    obtain.recycle();
                                                    throw th;
                                                }
                                            }
                                        }, continuation);
                                        if (access$notifyListeners2 == CoroutineSingletons.COROUTINE_SUSPENDED) {
                                            return access$notifyListeners2;
                                        }
                                    }
                                    break;
                                case 917416757:
                                    if (str.equals("health_status")) {
                                        Object access$notifyListeners3 = HealthService.access$notifyListeners(healthService2, new Function1() { // from class: com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2$1$4
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj3) {
                                                int i2 = ((SharedPreferences) Pair.this.getFirst()).getInt("health_status", -1);
                                                IHealthListener$Stub$Proxy iHealthListener$Stub$Proxy = (IHealthListener$Stub$Proxy) ((IHealthListener) obj3);
                                                Parcel obtain = Parcel.obtain(iHealthListener$Stub$Proxy.mRemote);
                                                try {
                                                    obtain.writeInterfaceToken("com.google.android.systemui.power.batteryhealth.IHealthListener");
                                                    obtain.writeInt(i2);
                                                    iHealthListener$Stub$Proxy.mRemote.transact(4, obtain, null, 1);
                                                    obtain.recycle();
                                                    return Unit.INSTANCE;
                                                } catch (Throwable th) {
                                                    obtain.recycle();
                                                    throw th;
                                                }
                                            }
                                        }, continuation);
                                        if (access$notifyListeners3 == CoroutineSingletons.COROUTINE_SUSPENDED) {
                                            return access$notifyListeners3;
                                        }
                                    }
                                    break;
                                case 1564109820:
                                    if (str.equals("perf_index")) {
                                        Object access$notifyListeners4 = HealthService.access$notifyListeners(healthService2, new Function1() { // from class: com.google.android.systemui.power.batteryhealth.HealthService$subscribeListeners$2$1$2
                                            {
                                                super(1);
                                            }

                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj3) {
                                                int i2 = ((SharedPreferences) Pair.this.getFirst()).getInt("perf_index", -1);
                                                IHealthListener$Stub$Proxy iHealthListener$Stub$Proxy = (IHealthListener$Stub$Proxy) ((IHealthListener) obj3);
                                                Parcel obtain = Parcel.obtain(iHealthListener$Stub$Proxy.mRemote);
                                                try {
                                                    obtain.writeInterfaceToken("com.google.android.systemui.power.batteryhealth.IHealthListener");
                                                    obtain.writeInt(i2);
                                                    iHealthListener$Stub$Proxy.mRemote.transact(2, obtain, null, 1);
                                                    obtain.recycle();
                                                    return Unit.INSTANCE;
                                                } catch (Throwable th) {
                                                    obtain.recycle();
                                                    throw th;
                                                }
                                            }
                                        }, continuation);
                                        if (access$notifyListeners4 == CoroutineSingletons.COROUTINE_SUSPENDED) {
                                            return access$notifyListeners4;
                                        }
                                    }
                                    break;
                            }
                            Log.i("HealthService", "Unknown prefs key");
                        } else {
                            Log.i("HealthService", "Key: " + str + ", not from prefs");
                        }
                    }
                    return Unit.INSTANCE;
                }
            }, this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}

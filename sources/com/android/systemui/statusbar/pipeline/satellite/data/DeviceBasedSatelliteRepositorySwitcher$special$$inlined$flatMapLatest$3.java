package com.android.systemui.statusbar.pipeline.satellite.data;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceBasedSatelliteRepositorySwitcher$special$$inlined$flatMapLatest$3 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DeviceBasedSatelliteRepositorySwitcher$special$$inlined$flatMapLatest$3 deviceBasedSatelliteRepositorySwitcher$special$$inlined$flatMapLatest$3 = new DeviceBasedSatelliteRepositorySwitcher$special$$inlined$flatMapLatest$3(3, (Continuation) obj3);
        deviceBasedSatelliteRepositorySwitcher$special$$inlined$flatMapLatest$3.L$0 = (FlowCollector) obj;
        deviceBasedSatelliteRepositorySwitcher$special$$inlined$flatMapLatest$3.L$1 = obj2;
        return deviceBasedSatelliteRepositorySwitcher$special$$inlined$flatMapLatest$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            StateFlow signalStrength = ((DeviceBasedSatelliteRepository) this.L$1).getSignalStrength();
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, signalStrength, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}

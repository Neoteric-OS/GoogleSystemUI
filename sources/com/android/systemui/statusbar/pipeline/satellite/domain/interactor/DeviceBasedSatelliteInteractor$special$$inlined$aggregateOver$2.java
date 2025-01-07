package com.android.systemui.statusbar.pipeline.satellite.domain.interactor;

import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ Object $defaultValue$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$2(Object obj, Continuation continuation) {
        super(3, continuation);
        this.$defaultValue$inlined = obj;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$2 deviceBasedSatelliteInteractor$special$$inlined$aggregateOver$2 = new DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$2(this.$defaultValue$inlined, (Continuation) obj3);
        deviceBasedSatelliteInteractor$special$$inlined$aggregateOver$2.L$0 = (FlowCollector) obj;
        deviceBasedSatelliteInteractor$special$$inlined$aggregateOver$2.L$1 = obj2;
        return deviceBasedSatelliteInteractor$special$$inlined$aggregateOver$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow deviceBasedSatelliteInteractor$special$$inlined$map$1;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            List list = (List) this.L$1;
            if (list.isEmpty()) {
                deviceBasedSatelliteInteractor$special$$inlined$map$1 = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(this.$defaultValue$inlined);
            } else {
                deviceBasedSatelliteInteractor$special$$inlined$map$1 = new DeviceBasedSatelliteInteractor$special$$inlined$map$1(2, (Flow[]) CollectionsKt.toList(list).toArray(new Flow[0]));
            }
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, deviceBasedSatelliteInteractor$special$$inlined$map$1, this) == coroutineSingletons) {
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

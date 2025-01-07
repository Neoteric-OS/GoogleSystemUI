package com.google.android.systemui.power.batteryevent.domain;

import android.util.Log;
import androidx.compose.foundation.text.input.internal.AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0;
import com.google.android.systemui.power.batteryevent.common.BatteryEvents;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class BatteryEventService$onCreate$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ BatteryEventService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryEventService$onCreate$1(BatteryEventService batteryEventService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = batteryEventService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryEventService$onCreate$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((BatteryEventService$onCreate$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw AndroidLegacyPlatformTextInputServiceAdapter$startInput$2$1$1$$ExternalSyntheticOutline0.m(obj);
        }
        ResultKt.throwOnFailure(obj);
        final BatteryEventService batteryEventService = this.this$0;
        StateFlowImpl stateFlowImpl = batteryEventService.eventStateController.mutableBatteryEventsFlow;
        FlowCollector flowCollector = new FlowCollector() { // from class: com.google.android.systemui.power.batteryevent.domain.BatteryEventService$onCreate$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public final Object emit(Object obj2, Continuation continuation) {
                BatteryEvents batteryEvents = (BatteryEvents) obj2;
                Log.d("BatteryEventService", "collect BatteryEvents: " + batteryEvents);
                Unit unit = Unit.INSTANCE;
                if (batteryEvents == null) {
                    return unit;
                }
                BatteryEventService batteryEventService2 = BatteryEventService.this;
                Object withContext = BuildersKt.withContext(batteryEventService2.backgroundDispatcher, new BatteryEventService$notifyForBatteryEventsUpdate$2(batteryEvents, batteryEventService2, null), continuation);
                CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (withContext != coroutineSingletons2) {
                    withContext = unit;
                }
                return withContext == coroutineSingletons2 ? withContext : unit;
            }
        };
        this.label = 1;
        stateFlowImpl.collect(flowCollector, this);
        return coroutineSingletons;
    }
}

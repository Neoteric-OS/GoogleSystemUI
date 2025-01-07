package com.google.android.systemui.power.batteryevent.domain;

import android.util.Log;
import com.google.android.systemui.power.batteryevent.common.BatteryEvents;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class BatteryEventService$notifyForBatteryEventsUpdate$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ BatteryEvents $events;
    int label;
    final /* synthetic */ BatteryEventService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryEventService$notifyForBatteryEventsUpdate$2(BatteryEvents batteryEvents, BatteryEventService batteryEventService, Continuation continuation) {
        super(2, continuation);
        this.$events = batteryEvents;
        this.this$0 = batteryEventService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new BatteryEventService$notifyForBatteryEventsUpdate$2(this.$events, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryEventService$notifyForBatteryEventsUpdate$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Log.d("BatteryEventService", "notifyForBatteryEventsUpdate: " + this.$events);
            BatteryEventService batteryEventService = this.this$0;
            BatteryEvents batteryEvents = this.$events;
            this.label = 1;
            if (BuildersKt.withContext(batteryEventService.backgroundDispatcher, new BatteryEventService$notifyAidlBatteryEventsCallbacks$2(batteryEvents, batteryEventService, null), this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        BatteryEventService batteryEventService2 = this.this$0;
        BatteryEvents batteryEvents2 = this.$events;
        this.label = 2;
        Object withContext = BuildersKt.withContext(batteryEventService2.backgroundDispatcher, new BatteryEventService$notifyBroadcastBatteryEventsUpdate$2(batteryEvents2, batteryEventService2, null), this);
        if (withContext != coroutineSingletons) {
            withContext = unit;
        }
        return withContext == coroutineSingletons ? coroutineSingletons : unit;
    }
}

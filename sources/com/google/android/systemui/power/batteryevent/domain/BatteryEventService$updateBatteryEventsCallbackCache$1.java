package com.google.android.systemui.power.batteryevent.domain;

import java.util.Set;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class BatteryEventService$updateBatteryEventsCallbackCache$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BatteryEventService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryEventService$updateBatteryEventsCallbackCache$1(BatteryEventService batteryEventService, ContinuationImpl continuationImpl) {
        super(continuationImpl);
        this.this$0 = batteryEventService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        BatteryEventService batteryEventService = this.this$0;
        Set set = BatteryEventService.supportedCallers;
        return batteryEventService.updateBatteryEventsCallbackCache(null, null, null, this);
    }
}

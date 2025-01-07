package com.android.systemui.util.kotlin;

import com.android.systemui.statusbar.policy.BatteryController;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class BatteryControllerExtKt$isExtremePowerSaverEnabled$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ BatteryController $this_isExtremePowerSaverEnabled;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BatteryControllerExtKt$isExtremePowerSaverEnabled$2(BatteryController batteryController, Continuation continuation) {
        super(2, continuation);
        this.$this_isExtremePowerSaverEnabled = batteryController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BatteryControllerExtKt$isExtremePowerSaverEnabled$2 batteryControllerExtKt$isExtremePowerSaverEnabled$2 = new BatteryControllerExtKt$isExtremePowerSaverEnabled$2(this.$this_isExtremePowerSaverEnabled, continuation);
        batteryControllerExtKt$isExtremePowerSaverEnabled$2.L$0 = obj;
        return batteryControllerExtKt$isExtremePowerSaverEnabled$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BatteryControllerExtKt$isExtremePowerSaverEnabled$2) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Boolean valueOf = Boolean.valueOf(this.$this_isExtremePowerSaverEnabled.isExtremeSaverOn());
            this.label = 1;
            if (flowCollector.emit(valueOf, this) == coroutineSingletons) {
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

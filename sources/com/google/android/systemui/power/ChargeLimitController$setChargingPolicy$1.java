package com.google.android.systemui.power;

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
final class ChargeLimitController$setChargingPolicy$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $policy;
    int label;
    final /* synthetic */ ChargeLimitController this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChargeLimitController$setChargingPolicy$1(ChargeLimitController chargeLimitController, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = chargeLimitController;
        this.$policy = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ChargeLimitController$setChargingPolicy$1(this.this$0, this.$policy, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ChargeLimitController$setChargingPolicy$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ChargeLimitController chargeLimitController = this.this$0;
            final int i2 = this.$policy;
            Function1 function1 = new Function1() { // from class: com.google.android.systemui.power.ChargeLimitController$setChargingPolicy$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    ((IGoogleBattery.Stub.Proxy) ((IGoogleBattery) obj2)).setChargingPolicy(i2);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            chargeLimitController.getClass();
            Object withContext = BuildersKt.withContext(chargeLimitController.backgroundDispatcher, new ChargeLimitController$withGoogleBattery$2(chargeLimitController, function1, null), this);
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
        return unit;
    }
}

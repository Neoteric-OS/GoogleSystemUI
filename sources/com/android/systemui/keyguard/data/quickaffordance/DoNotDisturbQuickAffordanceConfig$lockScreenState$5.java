package com.android.systemui.keyguard.data.quickaffordance;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DoNotDisturbQuickAffordanceConfig$lockScreenState$5 extends SuspendLambda implements Function2 {
    /* synthetic */ int I$0;
    int label;
    final /* synthetic */ DoNotDisturbQuickAffordanceConfig this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DoNotDisturbQuickAffordanceConfig$lockScreenState$5(DoNotDisturbQuickAffordanceConfig doNotDisturbQuickAffordanceConfig, Continuation continuation) {
        super(2, continuation);
        this.this$0 = doNotDisturbQuickAffordanceConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DoNotDisturbQuickAffordanceConfig$lockScreenState$5 doNotDisturbQuickAffordanceConfig$lockScreenState$5 = new DoNotDisturbQuickAffordanceConfig$lockScreenState$5(this.this$0, continuation);
        doNotDisturbQuickAffordanceConfig$lockScreenState$5.I$0 = ((Number) obj).intValue();
        return doNotDisturbQuickAffordanceConfig$lockScreenState$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DoNotDisturbQuickAffordanceConfig$lockScreenState$5 doNotDisturbQuickAffordanceConfig$lockScreenState$5 = (DoNotDisturbQuickAffordanceConfig$lockScreenState$5) create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        doNotDisturbQuickAffordanceConfig$lockScreenState$5.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.settingsValue = this.I$0;
        return Unit.INSTANCE;
    }
}

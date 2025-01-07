package com.android.systemui.keyguard.data.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StrongAuthTracker$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ StrongAuthTracker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StrongAuthTracker$special$$inlined$flatMapLatest$2(Continuation continuation, StrongAuthTracker strongAuthTracker) {
        super(3, continuation);
        this.this$0 = strongAuthTracker;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        StrongAuthTracker$special$$inlined$flatMapLatest$2 strongAuthTracker$special$$inlined$flatMapLatest$2 = new StrongAuthTracker$special$$inlined$flatMapLatest$2((Continuation) obj3, this.this$0);
        strongAuthTracker$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        strongAuthTracker$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return strongAuthTracker$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            int intValue = ((Number) this.L$1).intValue();
            FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new StrongAuthTracker$isNonStrongBiometricAllowed$1$4(intValue, this.this$0, null), new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new StrongAuthTracker$special$$inlined$map$1(new StrongAuthTracker$isNonStrongBiometricAllowed$lambda$6$$inlined$filter$1(this.this$0._nonStrongBiometricAllowed, intValue), 1), new StrongAuthTracker$isNonStrongBiometricAllowed$1$3(2, null), 0));
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, this) == coroutineSingletons) {
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

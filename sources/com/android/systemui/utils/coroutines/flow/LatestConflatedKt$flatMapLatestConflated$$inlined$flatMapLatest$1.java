package com.android.systemui.utils.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LatestConflatedKt$flatMapLatestConflated$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Function2 $transform;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LatestConflatedKt$flatMapLatestConflated$$inlined$flatMapLatest$1(Continuation continuation, Function2 function2) {
        super(3, continuation);
        this.$transform = function2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        LatestConflatedKt$flatMapLatestConflated$$inlined$flatMapLatest$1 latestConflatedKt$flatMapLatestConflated$$inlined$flatMapLatest$1 = new LatestConflatedKt$flatMapLatestConflated$$inlined$flatMapLatest$1((Continuation) obj3, this.$transform);
        latestConflatedKt$flatMapLatestConflated$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        latestConflatedKt$flatMapLatestConflated$$inlined$flatMapLatest$1.L$1 = obj2;
        return latestConflatedKt$flatMapLatestConflated$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = (FlowCollector) this.L$0;
            Object obj2 = this.L$1;
            Function2 function2 = this.$transform;
            this.L$0 = flowCollector;
            this.label = 1;
            obj = function2.invoke(obj2, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        this.L$0 = null;
        this.label = 2;
        if (FlowKt.emitAll(flowCollector, (Flow) obj, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }
}

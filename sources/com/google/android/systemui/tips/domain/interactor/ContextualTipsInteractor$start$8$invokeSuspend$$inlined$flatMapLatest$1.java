package com.google.android.systemui.tips.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ContextualTipsInteractor$start$8$invokeSuspend$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ ContextualTipsInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContextualTipsInteractor$start$8$invokeSuspend$$inlined$flatMapLatest$1(ContextualTipsInteractor contextualTipsInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = contextualTipsInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ContextualTipsInteractor$start$8$invokeSuspend$$inlined$flatMapLatest$1 contextualTipsInteractor$start$8$invokeSuspend$$inlined$flatMapLatest$1 = new ContextualTipsInteractor$start$8$invokeSuspend$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        contextualTipsInteractor$start$8$invokeSuspend$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        contextualTipsInteractor$start$8$invokeSuspend$$inlined$flatMapLatest$1.L$1 = obj2;
        return contextualTipsInteractor$start$8$invokeSuspend$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow flow = ((Boolean) this.L$1).booleanValue() ? this.this$0.globalActionsInteractor.isVisible : EmptyFlow.INSTANCE;
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flow, this) == coroutineSingletons) {
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

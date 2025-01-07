package com.google.android.systemui.tips.domain.interactor;

import com.google.android.systemui.tips.data.repository.ContextualTipsRepository;
import com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor;
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
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ContextualTipsInteractor$start$6$invokeSuspend$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ ContextualTipsInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContextualTipsInteractor$start$6$invokeSuspend$$inlined$flatMapLatest$1(ContextualTipsInteractor contextualTipsInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = contextualTipsInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ContextualTipsInteractor$start$6$invokeSuspend$$inlined$flatMapLatest$1 contextualTipsInteractor$start$6$invokeSuspend$$inlined$flatMapLatest$1 = new ContextualTipsInteractor$start$6$invokeSuspend$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        contextualTipsInteractor$start$6$invokeSuspend$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        contextualTipsInteractor$start$6$invokeSuspend$$inlined$flatMapLatest$1.L$1 = obj2;
        return contextualTipsInteractor$start$6$invokeSuspend$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow flow;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Boolean bool = (Boolean) this.L$1;
            boolean booleanValue = bool.booleanValue();
            StateFlowImpl stateFlowImpl = this.this$0._eligibleForPowerOffTip;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, bool);
            if (booleanValue) {
                this.this$0.logger.log(ContextualTipsInteractor.LogEvent.CONTEXTUAL_POWER_OFF_TIP_ELIGIBLE);
                ContextualTipsRepository contextualTipsRepository = this.this$0.repository;
                flow = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(contextualTipsRepository.assistantDismissals, contextualTipsRepository.approxAssistantDismissals, new ContextualTipsInteractor$start$6$2$1(3, null));
            } else {
                flow = EmptyFlow.INSTANCE;
            }
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

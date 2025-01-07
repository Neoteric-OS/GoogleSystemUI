package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.domain.interactor.FromDreamingTransitionInteractor;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DreamingToAodTransitionViewModel$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ DreamingToAodTransitionViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DreamingToAodTransitionViewModel$special$$inlined$flatMapLatest$1(Continuation continuation, DreamingToAodTransitionViewModel dreamingToAodTransitionViewModel) {
        super(3, continuation);
        this.this$0 = dreamingToAodTransitionViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DreamingToAodTransitionViewModel$special$$inlined$flatMapLatest$1 dreamingToAodTransitionViewModel$special$$inlined$flatMapLatest$1 = new DreamingToAodTransitionViewModel$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        dreamingToAodTransitionViewModel$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        dreamingToAodTransitionViewModel$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return dreamingToAodTransitionViewModel$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow m825sharedFlow74qcysc$default = ((Boolean) this.L$1).booleanValue() ? KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(this.this$0.transitionAnimation, FromDreamingTransitionInteractor.TO_AOD_DURATION, new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToAodTransitionViewModel$deviceEntryParentViewAlpha$1$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Float.valueOf(((Number) obj2).floatValue());
                }
            }, 0L, null, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToAodTransitionViewModel$deviceEntryParentViewAlpha$1$2
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Float.valueOf(1.0f);
                }
            }, new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.DreamingToAodTransitionViewModel$deviceEntryParentViewAlpha$1$3
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Float.valueOf(1.0f);
                }
            }, null, null, 204) : EmptyFlow.INSTANCE;
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, m825sharedFlow74qcysc$default, this) == coroutineSingletons) {
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

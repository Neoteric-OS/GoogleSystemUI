package com.android.systemui.keyguard.ui.viewmodel;

import android.view.animation.Interpolator;
import com.android.app.animation.Interpolators;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GlanceableHubToDreamingTransitionViewModel$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ GlanceableHubToDreamingTransitionViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlanceableHubToDreamingTransitionViewModel$special$$inlined$flatMapLatest$1(Continuation continuation, GlanceableHubToDreamingTransitionViewModel glanceableHubToDreamingTransitionViewModel) {
        super(3, continuation);
        this.this$0 = glanceableHubToDreamingTransitionViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        GlanceableHubToDreamingTransitionViewModel$special$$inlined$flatMapLatest$1 glanceableHubToDreamingTransitionViewModel$special$$inlined$flatMapLatest$1 = new GlanceableHubToDreamingTransitionViewModel$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        glanceableHubToDreamingTransitionViewModel$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        glanceableHubToDreamingTransitionViewModel$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return glanceableHubToDreamingTransitionViewModel$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            final int intValue = ((Number) this.L$1).intValue();
            KeyguardTransitionAnimationFlow.FlowBuilder flowBuilder = this.this$0.transitionAnimation;
            long j = GlanceableHubToDreamingTransitionViewModel.FROM_GLANCEABLE_HUB_DURATION;
            Interpolator interpolator = Interpolators.EMPHASIZED;
            Function1 function1 = new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.GlanceableHubToDreamingTransitionViewModel$dreamOverlayTranslationX$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Float.valueOf((((Number) obj2).floatValue() * intValue) + (-r1));
                }
            };
            Function0 function0 = new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.GlanceableHubToDreamingTransitionViewModel$dreamOverlayTranslationX$1$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Float.valueOf(-intValue);
                }
            };
            Intrinsics.checkNotNull(interpolator);
            KeyguardTransitionAnimationFlow$FlowBuilder$sharedFlow74qcysc$$inlined$mapNotNull$1 m825sharedFlow74qcysc$default = KeyguardTransitionAnimationFlow.FlowBuilder.m825sharedFlow74qcysc$default(flowBuilder, j, function1, 0L, null, function0, null, interpolator, "GLANCEABLE_HUB->LOCKSCREEN: dreamOverlayTranslationX", 44);
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

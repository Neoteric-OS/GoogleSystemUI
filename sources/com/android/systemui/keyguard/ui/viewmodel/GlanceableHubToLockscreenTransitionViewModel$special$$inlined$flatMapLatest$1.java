package com.android.systemui.keyguard.ui.viewmodel;

import android.view.animation.Interpolator;
import com.android.app.animation.Interpolators;
import com.android.systemui.keyguard.domain.interactor.FromGlanceableHubTransitionInteractor;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GlanceableHubToLockscreenTransitionViewModel$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ GlanceableHubToLockscreenTransitionViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GlanceableHubToLockscreenTransitionViewModel$special$$inlined$flatMapLatest$1(Continuation continuation, GlanceableHubToLockscreenTransitionViewModel glanceableHubToLockscreenTransitionViewModel) {
        super(3, continuation);
        this.this$0 = glanceableHubToLockscreenTransitionViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        GlanceableHubToLockscreenTransitionViewModel$special$$inlined$flatMapLatest$1 glanceableHubToLockscreenTransitionViewModel$special$$inlined$flatMapLatest$1 = new GlanceableHubToLockscreenTransitionViewModel$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0);
        glanceableHubToLockscreenTransitionViewModel$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        glanceableHubToLockscreenTransitionViewModel$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return glanceableHubToLockscreenTransitionViewModel$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
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
            long j = FromGlanceableHubTransitionInteractor.TO_LOCKSCREEN_DURATION;
            Interpolator interpolator = Interpolators.EMPHASIZED;
            Function1 function1 = new Function1() { // from class: com.android.systemui.keyguard.ui.viewmodel.GlanceableHubToLockscreenTransitionViewModel$keyguardTranslationX$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return Float.valueOf((((Number) obj2).floatValue() * intValue) + (-r1));
                }
            };
            GlanceableHubToLockscreenTransitionViewModel$keyguardTranslationX$1$2 glanceableHubToLockscreenTransitionViewModel$keyguardTranslationX$1$2 = new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.GlanceableHubToLockscreenTransitionViewModel$keyguardTranslationX$1$2
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Float.valueOf(0.0f);
                }
            };
            GlanceableHubToLockscreenTransitionViewModel$keyguardTranslationX$1$3 glanceableHubToLockscreenTransitionViewModel$keyguardTranslationX$1$3 = new Function0() { // from class: com.android.systemui.keyguard.ui.viewmodel.GlanceableHubToLockscreenTransitionViewModel$keyguardTranslationX$1$3
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Float.valueOf(0.0f);
                }
            };
            Intrinsics.checkNotNull(interpolator);
            Flow m826sharedFlowWithState74qcysc$default = KeyguardTransitionAnimationFlow.FlowBuilder.m826sharedFlowWithState74qcysc$default(flowBuilder, j, function1, 0L, null, glanceableHubToLockscreenTransitionViewModel$keyguardTranslationX$1$2, glanceableHubToLockscreenTransitionViewModel$keyguardTranslationX$1$3, interpolator, "GLANCEABLE_HUB->LOCKSCREEN: keyguardTranslationX", 12);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, m826sharedFlowWithState74qcysc$default, this) == coroutineSingletons) {
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

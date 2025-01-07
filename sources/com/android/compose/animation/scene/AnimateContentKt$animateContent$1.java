package com.android.compose.animation.scene;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AnimateContentKt$animateContent$1 extends SuspendLambda implements Function1 {
    final /* synthetic */ OneOffAnimation $oneOffAnimation;
    final /* synthetic */ float $targetProgress;
    final /* synthetic */ TransitionState.Transition $transition;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AnimateContentKt$animateContent$1(TransitionState.Transition transition, float f, OneOffAnimation oneOffAnimation, Continuation continuation) {
        super(1, continuation);
        this.$transition = transition;
        this.$targetProgress = f;
        this.$oneOffAnimation = oneOffAnimation;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new AnimateContentKt$animateContent$1(this.$transition, this.$targetProgress, this.$oneOffAnimation, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Float f;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FiniteAnimationSpec finiteAnimationSpec = this.$transition.transformationSpec.progressSpec;
            SpringSpec springSpec = finiteAnimationSpec instanceof SpringSpec ? (SpringSpec) finiteAnimationSpec : null;
            float floatValue = (springSpec == null || (f = (Float) springSpec.visibilityThreshold) == null) ? 0.001f : f.floatValue();
            TransitionState.Transition transition = this.$transition.replacedTransition;
            float progress = transition != null ? transition.getProgress() : 0.0f;
            float progressVelocity = transition != null ? transition.getProgressVelocity() : 0.0f;
            Animatable Animatable = AnimatableKt.Animatable(progress, floatValue);
            this.$oneOffAnimation.animatable = Animatable;
            Float f2 = new Float(this.$targetProgress);
            Float f3 = new Float(progressVelocity);
            this.label = 1;
            if (Animatable.animateTo$default(Animatable, f2, finiteAnimationSpec, f3, null, this, 8) == coroutineSingletons) {
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

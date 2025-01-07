package com.android.compose.animation.scene.content.state;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.SpringSpec;
import com.android.compose.animation.scene.SceneTransitionLayoutImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TransitionState$Transition$interruptionProgress$create$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Animatable $animatable;
    final /* synthetic */ SceneTransitionLayoutImpl $layoutImpl;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransitionState$Transition$interruptionProgress$create$1(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Animatable animatable, Continuation continuation) {
        super(2, continuation);
        this.$layoutImpl = sceneTransitionLayoutImpl;
        this.$animatable = animatable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new TransitionState$Transition$interruptionProgress$create$1(this.$layoutImpl, this.$animatable, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((TransitionState$Transition$interruptionProgress$create$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SpringSpec springSpec = this.$layoutImpl.state.transitions.defaultSwipeSpec;
            SpringSpec springSpec2 = new SpringSpec(springSpec.dampingRatio, springSpec.stiffness, new Float(0.001f));
            Animatable animatable = this.$animatable;
            Float f = new Float(0.0f);
            this.label = 1;
            if (Animatable.animateTo$default(animatable, f, springSpec2, null, null, this, 12) == coroutineSingletons) {
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

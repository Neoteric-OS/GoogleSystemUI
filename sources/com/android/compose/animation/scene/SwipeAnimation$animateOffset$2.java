package com.android.compose.animation.scene;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationState;
import com.android.compose.animation.scene.SwipeAnimation;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SwipeAnimation$animateOffset$2 extends SuspendLambda implements Function1 {
    final /* synthetic */ Animatable $animatable;
    final /* synthetic */ float $initialVelocity;
    final /* synthetic */ boolean $isTargetGreater;
    final /* synthetic */ boolean $startedWhenOvercrollingTargetContent;
    final /* synthetic */ AnimationSpec $swipeSpec;
    final /* synthetic */ ContentKey $targetContent;
    final /* synthetic */ float $targetOffset;
    int label;
    final /* synthetic */ SwipeAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SwipeAnimation$animateOffset$2(Animatable animatable, float f, AnimationSpec animationSpec, float f2, SwipeAnimation swipeAnimation, boolean z, boolean z2, ContentKey contentKey, Continuation continuation) {
        super(1, continuation);
        this.$animatable = animatable;
        this.$targetOffset = f;
        this.$swipeSpec = animationSpec;
        this.$initialVelocity = f2;
        this.this$0 = swipeAnimation;
        this.$isTargetGreater = z;
        this.$startedWhenOvercrollingTargetContent = z2;
        this.$targetContent = contentKey;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new SwipeAnimation$animateOffset$2(this.$animatable, this.$targetOffset, this.$swipeSpec, this.$initialVelocity, this.this$0, this.$isTargetGreater, this.$startedWhenOvercrollingTargetContent, this.$targetContent, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Animatable animatable = this.$animatable;
                Float f = new Float(this.$targetOffset);
                AnimationSpec animationSpec = this.$swipeSpec;
                Float f2 = new Float(this.$initialVelocity);
                final SwipeAnimation swipeAnimation = this.this$0;
                final boolean z = this.$isTargetGreater;
                final boolean z2 = this.$startedWhenOvercrollingTargetContent;
                final float f3 = this.$targetOffset;
                final ContentKey contentKey = this.$targetContent;
                Function1 function1 = new Function1() { // from class: com.android.compose.animation.scene.SwipeAnimation$animateOffset$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        Animatable animatable2 = (Animatable) obj2;
                        if (SwipeAnimation.this.bouncingContent == null) {
                            boolean z3 = z;
                            AnimationState animationState = animatable2.internalState;
                            if (!z3 ? !(!z2 ? ((Number) animationState.getValue()).floatValue() >= f3 : ((Number) animationState.getValue()).floatValue() > f3) : !(!z2 ? ((Number) animationState.getValue()).floatValue() <= f3 : ((Number) animationState.getValue()).floatValue() < f3)) {
                                SwipeAnimation swipeAnimation2 = SwipeAnimation.this;
                                swipeAnimation2.bouncingContent = contentKey;
                                TransitionState.Transition transition = swipeAnimation2.contentTransition;
                                if (transition == null) {
                                    transition = null;
                                }
                                if (!transition.isWithinProgressRange$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout(swipeAnimation2.getProgress())) {
                                    throw new SwipeAnimation.SnapException();
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (animatable.animateTo(f, animationSpec, f2, function1, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
        } catch (SwipeAnimation.SnapException unused) {
        }
        return Unit.INSTANCE;
    }
}

package com.android.compose.animation.scene.transition;

import androidx.compose.animation.core.AnimationSpec;
import com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl;
import com.android.compose.animation.scene.SwipeAnimation;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SeekKt$animateProgress$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ SwipeAnimation $animation;
    final /* synthetic */ AnimationSpec $cancelSpec;
    final /* synthetic */ AnimationSpec $commitSpec;
    final /* synthetic */ Flow $progress;
    final /* synthetic */ MutableSceneTransitionLayoutStateImpl $state;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SeekKt$animateProgress$2(AnimationSpec animationSpec, AnimationSpec animationSpec2, MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl, SwipeAnimation swipeAnimation, Continuation continuation, Flow flow) {
        super(2, continuation);
        this.$state = mutableSceneTransitionLayoutStateImpl;
        this.$animation = swipeAnimation;
        this.$progress = flow;
        this.$commitSpec = animationSpec;
        this.$cancelSpec = animationSpec2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SeekKt$animateProgress$2 seekKt$animateProgress$2 = new SeekKt$animateProgress$2(this.$commitSpec, this.$cancelSpec, this.$state, this.$animation, continuation, this.$progress);
        seekKt$animateProgress$2.L$0 = obj;
        return seekKt$animateProgress$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SeekKt$animateProgress$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Job job;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            StandaloneCoroutine launch$default = BuildersKt.launch$default((CoroutineScope) this.L$0, null, null, new SeekKt$animateProgress$2$collectionJob$1(this.$commitSpec, this.$cancelSpec, this.$state, this.$animation, null, this.$progress), 3);
            MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl = this.$state;
            TransitionState.Transition transition = this.$animation.contentTransition;
            if (transition == null) {
                transition = null;
            }
            this.L$0 = launch$default;
            this.label = 1;
            if (mutableSceneTransitionLayoutStateImpl.startTransition(transition, true, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
            job = launch$default;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            job = (Job) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (job.isActive()) {
            job.cancel(null);
        }
        return Unit.INSTANCE;
    }
}

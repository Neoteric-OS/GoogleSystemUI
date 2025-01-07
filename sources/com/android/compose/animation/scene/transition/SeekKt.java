package com.android.compose.animation.scene.transition;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.SnapSpec;
import com.android.compose.animation.scene.ContentKey;
import com.android.compose.animation.scene.MutableSceneTransitionLayoutStateImpl;
import com.android.compose.animation.scene.PredictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1;
import com.android.compose.animation.scene.SwipeAnimation;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SeekKt {
    public static final void access$animateProgress$animateOffset(MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl, SwipeAnimation swipeAnimation, ContentKey contentKey, AnimationSpec animationSpec) {
        TransitionState transitionState = mutableSceneTransitionLayoutStateImpl.getTransitionState();
        TransitionState.Transition transition = swipeAnimation.contentTransition;
        if (transition == null) {
            transition = null;
        }
        if (!Intrinsics.areEqual(transitionState, transition) || swipeAnimation.isAnimatingOffset()) {
            return;
        }
        if (animationSpec == null) {
            TransitionState.Transition transition2 = swipeAnimation.contentTransition;
            animationSpec = (transition2 != null ? transition2 : null).transformationSpec.progressSpec;
        }
        swipeAnimation.animateOffset(0.0f, contentKey, animationSpec);
    }

    public static final Object animateProgress(MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl, SwipeAnimation swipeAnimation, PredictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1 predictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1, SnapSpec snapSpec, Continuation continuation) {
        Object coroutineScope = CoroutineScopeKt.coroutineScope(continuation, new SeekKt$animateProgress$2(null, snapSpec, mutableSceneTransitionLayoutStateImpl, swipeAnimation, null, predictiveBackHandlerKt$PredictiveBackHandler$1$1$invokeSuspend$$inlined$map$1));
        return coroutineScope == CoroutineSingletons.COROUTINE_SUSPENDED ? coroutineScope : Unit.INSTANCE;
    }
}

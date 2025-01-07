package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ChangeSceneSwipeTransition extends TransitionState.Transition.ChangeScene implements TransitionState.HasOverscrollProperties {
    public final boolean isInitiatedByUserInput;
    public final TransitionKey key;
    public final MutableSceneTransitionLayoutStateImpl layoutState;
    public final SwipeAnimation swipeAnimation;

    public ChangeSceneSwipeTransition(MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl, SwipeAnimation swipeAnimation, TransitionKey transitionKey, ChangeSceneSwipeTransition changeSceneSwipeTransition) {
        super((SceneKey) swipeAnimation.fromContent, (SceneKey) swipeAnimation.toContent, changeSceneSwipeTransition);
        this.layoutState = mutableSceneTransitionLayoutStateImpl;
        this.swipeAnimation = swipeAnimation;
        this.key = transitionKey;
        swipeAnimation.contentTransition = this;
        this.isInitiatedByUserInput = true;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final void freezeAndAnimateToCurrentState() {
        this.swipeAnimation.freezeAndAnimateToCurrentState();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.HasOverscrollProperties
    public final ContentKey getBouncingContent() {
        return this.swipeAnimation.bouncingContent;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState
    public final SceneKey getCurrentScene() {
        return (SceneKey) this.swipeAnimation.getCurrentContent();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final TransitionKey getKey() {
        return this.key;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.HasOverscrollProperties
    public final Orientation getOrientation() {
        return this.swipeAnimation.orientation;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final float getPreviewProgress$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout() {
        return this.swipeAnimation.getPreviewProgress();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final float getProgress() {
        return this.swipeAnimation.getProgress();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final float getProgressVelocity() {
        return this.swipeAnimation.getProgressVelocity();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final boolean isInPreviewStage$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout() {
        return this.swipeAnimation.isInPreviewStage();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final boolean isInitiatedByUserInput() {
        return this.isInitiatedByUserInput;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.HasOverscrollProperties
    public final boolean isUpOrLeft() {
        return this.swipeAnimation.isUpOrLeft;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final boolean isUserInputOngoing() {
        return this.swipeAnimation.getOffsetAnimation() == null;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final Object run(Continuation continuation) {
        Object run = this.swipeAnimation.run(continuation);
        return run == CoroutineSingletons.COROUTINE_SUSPENDED ? run : Unit.INSTANCE;
    }
}

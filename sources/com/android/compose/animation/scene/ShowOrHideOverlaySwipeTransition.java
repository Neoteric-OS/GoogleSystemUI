package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShowOrHideOverlaySwipeTransition extends TransitionState.Transition.ShowOrHideOverlay implements TransitionState.HasOverscrollProperties {
    public final boolean isInitiatedByUserInput;
    public final TransitionKey key;
    public final MutableSceneTransitionLayoutStateImpl layoutState;
    public final SwipeAnimation swipeAnimation;

    public ShowOrHideOverlaySwipeTransition(MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl, SwipeAnimation swipeAnimation, OverlayKey overlayKey, SceneKey sceneKey, TransitionKey transitionKey, ShowOrHideOverlaySwipeTransition showOrHideOverlaySwipeTransition) {
        super(overlayKey, sceneKey, swipeAnimation.fromContent, swipeAnimation.toContent, showOrHideOverlaySwipeTransition);
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

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition.ShowOrHideOverlay
    public final boolean isEffectivelyShown() {
        return Intrinsics.areEqual(this.swipeAnimation.getCurrentContent(), this.overlay);
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

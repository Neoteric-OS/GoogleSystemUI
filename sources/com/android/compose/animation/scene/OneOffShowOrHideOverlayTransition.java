package com.android.compose.animation.scene;

import androidx.compose.animation.core.Animatable;
import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OneOffShowOrHideOverlayTransition extends TransitionState.Transition.ShowOrHideOverlay {
    public final boolean isEffectivelyShown;
    public final OneOffAnimation oneOffAnimation;

    public OneOffShowOrHideOverlayTransition(OverlayKey overlayKey, SceneKey sceneKey, ContentKey contentKey, ContentKey contentKey2, boolean z, TransitionState.Transition.ShowOrHideOverlay showOrHideOverlay, OneOffAnimation oneOffAnimation) {
        super(overlayKey, sceneKey, contentKey, contentKey2, showOrHideOverlay);
        this.isEffectivelyShown = z;
        this.oneOffAnimation = oneOffAnimation;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final void freezeAndAnimateToCurrentState() {
        this.oneOffAnimation.getClass();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final TransitionKey getKey() {
        return null;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final float getProgress() {
        return this.oneOffAnimation.getProgress();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final float getProgressVelocity() {
        Animatable animatable = this.oneOffAnimation.animatable;
        if (animatable == null) {
            animatable = null;
        }
        return ((Number) animatable.getVelocity()).floatValue();
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition.ShowOrHideOverlay
    public final boolean isEffectivelyShown() {
        return this.isEffectivelyShown;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final boolean isInitiatedByUserInput() {
        return false;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final boolean isUserInputOngoing() {
        return false;
    }

    @Override // com.android.compose.animation.scene.content.state.TransitionState.Transition
    public final Object run(Continuation continuation) {
        Object run = this.oneOffAnimation.run(continuation);
        return run == CoroutineSingletons.COROUTINE_SUSPENDED ? run : Unit.INSTANCE;
    }
}

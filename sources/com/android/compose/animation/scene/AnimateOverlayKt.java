package com.android.compose.animation.scene;

import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.Pair;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AnimateOverlayKt {
    public static final void showOrHideOverlay(CoroutineScope coroutineScope, MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl, OverlayKey overlayKey, SceneKey sceneKey, boolean z, TransitionState.Transition.ShowOrHideOverlay showOrHideOverlay, boolean z2) {
        float f = z2 ? 0.0f : 1.0f;
        Pair pair = z ^ z2 ? new Pair(sceneKey, overlayKey) : new Pair(overlayKey, sceneKey);
        ContentKey contentKey = (ContentKey) pair.component1();
        ContentKey contentKey2 = (ContentKey) pair.component2();
        OneOffAnimation oneOffAnimation = new OneOffAnimation();
        OneOffShowOrHideOverlayTransition oneOffShowOrHideOverlayTransition = new OneOffShowOrHideOverlayTransition(overlayKey, sceneKey, contentKey, contentKey2, z, showOrHideOverlay, oneOffAnimation);
        oneOffAnimation.onRun = new AnimateContentKt$animateContent$1(oneOffShowOrHideOverlayTransition, f, oneOffAnimation, null);
        mutableSceneTransitionLayoutStateImpl.startTransitionImmediately(coroutineScope, oneOffShowOrHideOverlayTransition, true);
    }
}

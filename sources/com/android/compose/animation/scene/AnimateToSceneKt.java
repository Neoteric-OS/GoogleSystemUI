package com.android.compose.animation.scene;

import com.android.compose.animation.scene.content.state.TransitionState;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AnimateToSceneKt {
    public static final Pair animateToScene(CoroutineScope coroutineScope, MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl, SceneKey sceneKey, TransitionKey transitionKey) {
        TransitionState transitionState = mutableSceneTransitionLayoutStateImpl.getTransitionState();
        if (Intrinsics.areEqual(transitionState.getCurrentScene(), sceneKey)) {
            return null;
        }
        if (transitionState instanceof TransitionState.Idle ? true : transitionState instanceof TransitionState.Transition.ShowOrHideOverlay ? true : transitionState instanceof ReplaceOverlaySwipeTransition) {
            return animateToScene$default(coroutineScope, mutableSceneTransitionLayoutStateImpl, sceneKey, transitionKey, false, null, transitionState.getCurrentScene(), 160);
        }
        if (!(transitionState instanceof TransitionState.Transition.ChangeScene)) {
            throw new NoWhenBranchMatchedException();
        }
        TransitionState.Transition.ChangeScene changeScene = (TransitionState.Transition.ChangeScene) transitionState;
        boolean isInitiatedByUserInput = changeScene.isInitiatedByUserInput();
        SceneKey sceneKey2 = changeScene.toScene;
        boolean areEqual = Intrinsics.areEqual(sceneKey2, sceneKey);
        SceneKey sceneKey3 = changeScene.fromScene;
        if (areEqual) {
            if (Intrinsics.areEqual(sceneKey3, transitionState.getCurrentScene())) {
                return animateToScene$default(coroutineScope, mutableSceneTransitionLayoutStateImpl, sceneKey, transitionKey, isInitiatedByUserInput, (TransitionState.Transition) transitionState, null, 224);
            }
            throw new IllegalStateException("Check failed.");
        }
        if (Intrinsics.areEqual(sceneKey3, sceneKey)) {
            if (Intrinsics.areEqual(sceneKey2, transitionState.getCurrentScene())) {
                return animateToScene$default(coroutineScope, mutableSceneTransitionLayoutStateImpl, sceneKey, transitionKey, isInitiatedByUserInput, (TransitionState.Transition) transitionState, null, 192);
            }
            throw new IllegalStateException("Check failed.");
        }
        mutableSceneTransitionLayoutStateImpl.transitions.interruptionHandler.getClass();
        SceneKey currentScene = changeScene.getCurrentScene();
        if (Intrinsics.areEqual(currentScene, sceneKey2) || Intrinsics.areEqual(currentScene, sceneKey3)) {
            if (!Intrinsics.areEqual(currentScene, transitionState.getCurrentScene())) {
                animateToScene(coroutineScope, mutableSceneTransitionLayoutStateImpl, currentScene, null);
            }
            return animateToScene$default(coroutineScope, mutableSceneTransitionLayoutStateImpl, sceneKey, transitionKey, isInitiatedByUserInput, null, currentScene, 32);
        }
        throw new IllegalStateException(("InterruptionResult.animateFrom must be either the fromScene (" + sceneKey3.debugName + ") or the toScene (" + sceneKey2.debugName + ") of the interrupted transition.").toString());
    }

    public static Pair animateToScene$default(CoroutineScope coroutineScope, MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl, SceneKey sceneKey, TransitionKey transitionKey, boolean z, TransitionState.Transition transition, SceneKey sceneKey2, int i) {
        boolean z2 = (i & 32) == 0;
        SceneKey currentScene = (i & 64) != 0 ? mutableSceneTransitionLayoutStateImpl.getTransitionState().getCurrentScene() : sceneKey2;
        OneOffAnimation oneOffAnimation = new OneOffAnimation();
        float f = z2 ? 0.0f : 1.0f;
        OneOffSceneTransition oneOffSceneTransition = z2 ? new OneOffSceneTransition(transitionKey, sceneKey, currentScene, sceneKey, z, transition, oneOffAnimation) : new OneOffSceneTransition(transitionKey, currentScene, sceneKey, sceneKey, z, transition, oneOffAnimation);
        oneOffAnimation.onRun = new AnimateContentKt$animateContent$1(oneOffSceneTransition, f, oneOffAnimation, null);
        return new Pair(oneOffSceneTransition, mutableSceneTransitionLayoutStateImpl.startTransitionImmediately(coroutineScope, oneOffSceneTransition, true));
    }
}

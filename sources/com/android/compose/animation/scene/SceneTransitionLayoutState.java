package com.android.compose.animation.scene;

import com.android.compose.animation.scene.content.state.TransitionState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface SceneTransitionLayoutState {
    static boolean isTransitioning$default(SceneTransitionLayoutState sceneTransitionLayoutState, SceneKey sceneKey, int i) {
        if ((i & 2) != 0) {
            sceneKey = null;
        }
        TransitionState.Transition currentTransition = ((MutableSceneTransitionLayoutStateImpl) sceneTransitionLayoutState).getCurrentTransition();
        if (currentTransition == null) {
            return false;
        }
        return currentTransition.isTransitioning(null, sceneKey);
    }

    default TransitionState.Transition getCurrentTransition() {
        TransitionState transitionState = ((MutableSceneTransitionLayoutStateImpl) this).getTransitionState();
        if (transitionState instanceof TransitionState.Transition) {
            return (TransitionState.Transition) transitionState;
        }
        return null;
    }
}

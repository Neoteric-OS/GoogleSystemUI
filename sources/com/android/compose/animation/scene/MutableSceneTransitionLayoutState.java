package com.android.compose.animation.scene;

import com.android.compose.animation.scene.content.state.TransitionState;
import java.util.Collections;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface MutableSceneTransitionLayoutState extends SceneTransitionLayoutState {
    static void snapToScene$default(MutableSceneTransitionLayoutState mutableSceneTransitionLayoutState, SceneKey sceneKey) {
        Set currentOverlays = ((MutableSceneTransitionLayoutStateImpl) mutableSceneTransitionLayoutState).getTransitionState().getCurrentOverlays();
        MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl = (MutableSceneTransitionLayoutStateImpl) mutableSceneTransitionLayoutState;
        mutableSceneTransitionLayoutStateImpl.checkThread$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout();
        while (!mutableSceneTransitionLayoutStateImpl.getCurrentTransitions().isEmpty()) {
            mutableSceneTransitionLayoutStateImpl.finishTransition((TransitionState.Transition) mutableSceneTransitionLayoutStateImpl.getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().get(0));
        }
        if (mutableSceneTransitionLayoutStateImpl.getTransitionStates$frameworks__base__packages__SystemUI__compose__scene__android_common__PlatformComposeSceneTransitionLayout().size() != 1) {
            throw new IllegalStateException("Check failed.");
        }
        mutableSceneTransitionLayoutStateImpl.setTransitionStates(Collections.singletonList(new TransitionState.Idle(sceneKey, currentOverlays)));
    }
}

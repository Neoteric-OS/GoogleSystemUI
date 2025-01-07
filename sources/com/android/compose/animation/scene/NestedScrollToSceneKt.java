package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NestedScrollToSceneKt {
    public static final Modifier nestedScrollToScene(Modifier modifier, SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Orientation orientation, NestedScrollBehavior nestedScrollBehavior, NestedScrollBehavior nestedScrollBehavior2, Function0 function0) {
        return modifier.then(new NestedScrollToSceneElement(sceneTransitionLayoutImpl, orientation, nestedScrollBehavior, nestedScrollBehavior2, function0));
    }
}

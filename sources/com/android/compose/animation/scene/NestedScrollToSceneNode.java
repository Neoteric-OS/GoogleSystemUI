package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.node.DelegatingNode;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NestedScrollToSceneNode extends DelegatingNode {
    public NestedScrollBehavior bottomOrRightBehavior;
    public Function0 isExternalOverscrollGesture;
    public SceneTransitionLayoutImpl layoutImpl;
    public Orientation orientation;
    public ScrollBehaviorOwnerNode scrollBehaviorOwner;
    public NestedScrollBehavior topOrLeftBehavior;

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        this.scrollBehaviorOwner = null;
    }
}

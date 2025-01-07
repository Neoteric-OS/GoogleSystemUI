package com.android.compose.animation.scene;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.TraversableNode;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScrollBehaviorOwnerNode extends Modifier.Node implements TraversableNode {
    public final NestedScrollHandlerImpl nestedScrollHandlerImpl;
    public final Object traverseKey;

    public ScrollBehaviorOwnerNode(Object obj, NestedScrollHandlerImpl nestedScrollHandlerImpl) {
        this.traverseKey = obj;
        this.nestedScrollHandlerImpl = nestedScrollHandlerImpl;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public final Object getTraverseKey() {
        return this.traverseKey;
    }
}

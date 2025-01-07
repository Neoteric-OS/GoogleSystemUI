package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NodeCoordinatorKt {
    /* renamed from: access$nextUntil-hw7D004, reason: not valid java name */
    public static final Modifier.Node m541access$nextUntilhw7D004(DelegatableNode delegatableNode, int i) {
        Modifier.Node node = ((Modifier.Node) delegatableNode).node.child;
        if (node == null || (node.aggregateChildKindSet & i) == 0) {
            return null;
        }
        while (node != null) {
            int i2 = node.kindSet;
            if ((i2 & 2) != 0) {
                return null;
            }
            if ((i2 & i) != 0) {
                return node;
            }
            node = node.child;
        }
        return null;
    }
}

package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.NodeCoordinator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NodeCoordinator$Companion$PointerInputSource$1 implements NodeCoordinator.HitTestSource {
    @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
    /* renamed from: childHitTest-YqVAtuI, reason: not valid java name */
    public final void mo539childHitTestYqVAtuI(LayoutNode layoutNode, long j, HitTestResult hitTestResult, boolean z, boolean z2) {
        layoutNode.m508hitTestM_7yMNQ$ui_release(j, hitTestResult, z, z2);
    }

    @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
    /* renamed from: entityType-OLwlOKw, reason: not valid java name */
    public final int mo540entityTypeOLwlOKw() {
        return 16;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r7v0, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
    public final boolean interceptOutOfBoundsChildEvents(Modifier.Node node) {
        ?? r0 = 0;
        while (true) {
            int i = 0;
            if (node == 0) {
                return false;
            }
            if (node instanceof PointerInputModifierNode) {
                ((PointerInputModifierNode) node).interceptOutOfBoundsChildEvents();
            } else if ((node.kindSet & 16) != 0 && (node instanceof DelegatingNode)) {
                Modifier.Node node2 = node.delegate;
                r0 = r0;
                node = node;
                while (node2 != null) {
                    if ((node2.kindSet & 16) != 0) {
                        i++;
                        r0 = r0;
                        if (i == 1) {
                            node = node2;
                        } else {
                            if (r0 == 0) {
                                r0 = new MutableVector(new Modifier.Node[16]);
                            }
                            if (node != 0) {
                                r0.add(node);
                                node = 0;
                            }
                            r0.add(node2);
                        }
                    }
                    node2 = node2.child;
                    r0 = r0;
                    node = node;
                }
                if (i == 1) {
                }
            }
            node = DelegatableNodeKt.access$pop(r0);
        }
    }

    @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
    public final boolean shouldHitTestChildren(LayoutNode layoutNode) {
        return true;
    }
}

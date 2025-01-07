package androidx.compose.ui.modifier;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ModifierLocalModifierNode extends ModifierLocalReadScope, DelegatableNode {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v14, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r8v0, types: [androidx.compose.ui.modifier.ModifierLocalModifierNode, androidx.compose.ui.node.DelegatableNode] */
    /* JADX WARN: Type inference failed for: r8v4, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.ui.modifier.ModifierLocalReadScope
    default Object getCurrent(ProvidableModifierLocal providableModifierLocal) {
        NodeChain nodeChain;
        Modifier.Node node = (Modifier.Node) this;
        if (!node.node.isAttached) {
            InlineClassHelperKt.throwIllegalArgumentException("ModifierLocal accessed from an unattached node");
        }
        Modifier.Node node2 = node.node;
        if (!node2.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node3 = node2.parent;
        LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(this);
        while (requireLayoutNode != null) {
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 32) != 0) {
                while (node3 != null) {
                    if ((node3.kindSet & 32) != 0) {
                        DelegatingNode delegatingNode = node3;
                        ?? r3 = 0;
                        while (delegatingNode != 0) {
                            if (delegatingNode instanceof ModifierLocalModifierNode) {
                                ModifierLocalModifierNode modifierLocalModifierNode = (ModifierLocalModifierNode) delegatingNode;
                                if (modifierLocalModifierNode.getProvidedValues().contains$ui_release(providableModifierLocal)) {
                                    return modifierLocalModifierNode.getProvidedValues().get$ui_release(providableModifierLocal);
                                }
                            } else if ((delegatingNode.kindSet & 32) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                Modifier.Node node4 = delegatingNode.delegate;
                                int i = 0;
                                delegatingNode = delegatingNode;
                                r3 = r3;
                                while (node4 != null) {
                                    if ((node4.kindSet & 32) != 0) {
                                        i++;
                                        r3 = r3;
                                        if (i == 1) {
                                            delegatingNode = node4;
                                        } else {
                                            if (r3 == 0) {
                                                r3 = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (delegatingNode != 0) {
                                                r3.add(delegatingNode);
                                                delegatingNode = 0;
                                            }
                                            r3.add(node4);
                                        }
                                    }
                                    node4 = node4.child;
                                    delegatingNode = delegatingNode;
                                    r3 = r3;
                                }
                                if (i == 1) {
                                }
                            }
                            delegatingNode = DelegatableNodeKt.access$pop(r3);
                        }
                    }
                    node3 = node3.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node3 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
        }
        return providableModifierLocal.defaultFactory.invoke();
    }

    default ModifierLocalMap getProvidedValues() {
        return EmptyMap.INSTANCE;
    }
}

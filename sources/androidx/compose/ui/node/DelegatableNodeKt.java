package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DelegatableNodeKt {
    public static final void access$addLayoutNodeChildren(MutableVector mutableVector, Modifier.Node node) {
        MutableVector mutableVector2 = requireLayoutNode(node).get_children$ui_release();
        int i = mutableVector2.size;
        if (i > 0) {
            int i2 = i - 1;
            Object[] objArr = mutableVector2.content;
            do {
                mutableVector.add(((LayoutNode) objArr[i2]).nodes.head);
                i2--;
            } while (i2 >= 0);
        }
    }

    public static final Modifier.Node access$pop(MutableVector mutableVector) {
        int i;
        if (mutableVector == null || (i = mutableVector.size) == 0) {
            return null;
        }
        return (Modifier.Node) mutableVector.removeAt(i - 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final LayoutModifierNode asLayoutModifierNode(Modifier.Node node) {
        if ((node.kindSet & 2) != 0) {
            if (node instanceof LayoutModifierNode) {
                return (LayoutModifierNode) node;
            }
            if (node instanceof DelegatingNode) {
                Modifier.Node node2 = ((DelegatingNode) node).delegate;
                while (node2 != 0) {
                    if (node2 instanceof LayoutModifierNode) {
                        return (LayoutModifierNode) node2;
                    }
                    node2 = (!(node2 instanceof DelegatingNode) || (node2.kindSet & 2) == 0) ? node2.child : ((DelegatingNode) node2).delegate;
                }
            }
        }
        return null;
    }

    /* renamed from: requireCoordinator-64DMado, reason: not valid java name */
    public static final NodeCoordinator m503requireCoordinator64DMado(DelegatableNode delegatableNode, int i) {
        NodeCoordinator nodeCoordinator = ((Modifier.Node) delegatableNode).node.coordinator;
        Intrinsics.checkNotNull(nodeCoordinator);
        if (nodeCoordinator.getTail() != delegatableNode || !NodeKindKt.m542getIncludeSelfInTraversalH91voCI(i)) {
            return nodeCoordinator;
        }
        NodeCoordinator nodeCoordinator2 = nodeCoordinator.wrapped;
        Intrinsics.checkNotNull(nodeCoordinator2);
        return nodeCoordinator2;
    }

    public static final NodeCoordinator requireLayoutCoordinates(DelegatableNode delegatableNode) {
        if (!((Modifier.Node) delegatableNode).node.isAttached) {
            InlineClassHelperKt.throwIllegalStateException("Cannot get LayoutCoordinates, Modifier.Node is not attached.");
        }
        NodeCoordinator m503requireCoordinator64DMado = m503requireCoordinator64DMado(delegatableNode, 2);
        if (!m503requireCoordinator64DMado.getTail().isAttached) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinates is not attached.");
        }
        return m503requireCoordinator64DMado;
    }

    public static final LayoutNode requireLayoutNode(DelegatableNode delegatableNode) {
        NodeCoordinator nodeCoordinator = ((Modifier.Node) delegatableNode).node.coordinator;
        if (nodeCoordinator != null) {
            return nodeCoordinator.layoutNode;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Cannot obtain node coordinator. Is the Modifier.Node attached?");
        throw null;
    }

    public static final Owner requireOwner(DelegatableNode delegatableNode) {
        AndroidComposeView androidComposeView = requireLayoutNode(delegatableNode).owner;
        if (androidComposeView != null) {
            return androidComposeView;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("This node does not have an owner.");
        throw null;
    }
}

package androidx.compose.ui.focus;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FocusTraversalKt {
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0035, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.focus.FocusTargetNode findActiveFocusNode(androidx.compose.ui.focus.FocusTargetNode r8) {
        /*
            androidx.compose.ui.focus.FocusStateImpl r0 = r8.getFocusState()
            int r0 = r0.ordinal()
            if (r0 == 0) goto Laa
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L1b
            r1 = 2
            if (r0 == r1) goto Laa
            r8 = 3
            if (r0 != r8) goto L15
            return r2
        L15:
            kotlin.NoWhenBranchMatchedException r8 = new kotlin.NoWhenBranchMatchedException
            r8.<init>()
            throw r8
        L1b:
            androidx.compose.ui.Modifier$Node r8 = r8.node
            boolean r0 = r8.isAttached
            if (r0 == 0) goto La2
            androidx.compose.runtime.collection.MutableVector r0 = new androidx.compose.runtime.collection.MutableVector
            r3 = 16
            androidx.compose.ui.Modifier$Node[] r4 = new androidx.compose.ui.Modifier.Node[r3]
            r0.<init>(r4)
            androidx.compose.ui.Modifier$Node r4 = r8.child
            if (r4 != 0) goto L32
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r0, r8)
            goto L35
        L32:
            r0.add(r4)
        L35:
            int r8 = r0.size
            if (r8 == 0) goto La1
            int r8 = r8 + (-1)
            java.lang.Object r8 = r0.removeAt(r8)
            androidx.compose.ui.Modifier$Node r8 = (androidx.compose.ui.Modifier.Node) r8
            int r4 = r8.aggregateChildKindSet
            r4 = r4 & 1024(0x400, float:1.435E-42)
            if (r4 != 0) goto L4b
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r0, r8)
            goto L35
        L4b:
            if (r8 == 0) goto L35
            int r4 = r8.kindSet
            r4 = r4 & 1024(0x400, float:1.435E-42)
            if (r4 == 0) goto L9e
            r4 = r2
        L54:
            if (r8 == 0) goto L35
            boolean r5 = r8 instanceof androidx.compose.ui.focus.FocusTargetNode
            if (r5 == 0) goto L63
            androidx.compose.ui.focus.FocusTargetNode r8 = (androidx.compose.ui.focus.FocusTargetNode) r8
            androidx.compose.ui.focus.FocusTargetNode r8 = findActiveFocusNode(r8)
            if (r8 == 0) goto L99
            return r8
        L63:
            int r5 = r8.kindSet
            r5 = r5 & 1024(0x400, float:1.435E-42)
            if (r5 == 0) goto L99
            boolean r5 = r8 instanceof androidx.compose.ui.node.DelegatingNode
            if (r5 == 0) goto L99
            r5 = r8
            androidx.compose.ui.node.DelegatingNode r5 = (androidx.compose.ui.node.DelegatingNode) r5
            androidx.compose.ui.Modifier$Node r5 = r5.delegate
            r6 = 0
        L73:
            if (r5 == 0) goto L96
            int r7 = r5.kindSet
            r7 = r7 & 1024(0x400, float:1.435E-42)
            if (r7 == 0) goto L93
            int r6 = r6 + 1
            if (r6 != r1) goto L81
            r8 = r5
            goto L93
        L81:
            if (r4 != 0) goto L8a
            androidx.compose.runtime.collection.MutableVector r4 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r7 = new androidx.compose.ui.Modifier.Node[r3]
            r4.<init>(r7)
        L8a:
            if (r8 == 0) goto L90
            r4.add(r8)
            r8 = r2
        L90:
            r4.add(r5)
        L93:
            androidx.compose.ui.Modifier$Node r5 = r5.child
            goto L73
        L96:
            if (r6 != r1) goto L99
            goto L54
        L99:
            androidx.compose.ui.Modifier$Node r8 = androidx.compose.ui.node.DelegatableNodeKt.access$pop(r4)
            goto L54
        L9e:
            androidx.compose.ui.Modifier$Node r8 = r8.child
            goto L4b
        La1:
            return r2
        La2:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "visitChildren called on an unattached node"
            r8.<init>(r0)
            throw r8
        Laa:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTraversalKt.findActiveFocusNode(androidx.compose.ui.focus.FocusTargetNode):androidx.compose.ui.focus.FocusTargetNode");
    }

    public static final Rect focusRect(FocusTargetNode focusTargetNode) {
        NodeCoordinator nodeCoordinator = focusTargetNode.coordinator;
        return nodeCoordinator != null ? LayoutCoordinatesKt.findRootCoordinates(nodeCoordinator).localBoundingBoxOf(nodeCoordinator, false) : Rect.Zero;
    }

    /* JADX WARN: Code restructure failed: missing block: B:67:0x001e, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.focus.FocusTargetNode getActiveChild(androidx.compose.ui.focus.FocusTargetNode r8) {
        /*
            androidx.compose.ui.Modifier$Node r8 = r8.node
            boolean r0 = r8.isAttached
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            if (r0 == 0) goto L9c
            androidx.compose.runtime.collection.MutableVector r0 = new androidx.compose.runtime.collection.MutableVector
            r2 = 16
            androidx.compose.ui.Modifier$Node[] r3 = new androidx.compose.ui.Modifier.Node[r2]
            r0.<init>(r3)
            androidx.compose.ui.Modifier$Node r3 = r8.child
            if (r3 != 0) goto L1b
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r0, r8)
            goto L1e
        L1b:
            r0.add(r3)
        L1e:
            int r8 = r0.size
            if (r8 == 0) goto L9b
            int r8 = r8 + (-1)
            java.lang.Object r8 = r0.removeAt(r8)
            androidx.compose.ui.Modifier$Node r8 = (androidx.compose.ui.Modifier.Node) r8
            int r3 = r8.aggregateChildKindSet
            r3 = r3 & 1024(0x400, float:1.435E-42)
            if (r3 != 0) goto L34
            androidx.compose.ui.node.DelegatableNodeKt.access$addLayoutNodeChildren(r0, r8)
            goto L1e
        L34:
            if (r8 == 0) goto L1e
            int r3 = r8.kindSet
            r3 = r3 & 1024(0x400, float:1.435E-42)
            if (r3 == 0) goto L98
            r3 = r1
        L3d:
            if (r8 == 0) goto L1e
            boolean r4 = r8 instanceof androidx.compose.ui.focus.FocusTargetNode
            r5 = 1
            if (r4 == 0) goto L5d
            androidx.compose.ui.focus.FocusTargetNode r8 = (androidx.compose.ui.focus.FocusTargetNode) r8
            androidx.compose.ui.Modifier$Node r4 = r8.node
            boolean r4 = r4.isAttached
            if (r4 == 0) goto L93
            androidx.compose.ui.focus.FocusStateImpl r4 = r8.getFocusState()
            int r4 = r4.ordinal()
            if (r4 == 0) goto L5c
            if (r4 == r5) goto L5c
            r5 = 2
            if (r4 == r5) goto L5c
            goto L93
        L5c:
            return r8
        L5d:
            int r4 = r8.kindSet
            r4 = r4 & 1024(0x400, float:1.435E-42)
            if (r4 == 0) goto L93
            boolean r4 = r8 instanceof androidx.compose.ui.node.DelegatingNode
            if (r4 == 0) goto L93
            r4 = r8
            androidx.compose.ui.node.DelegatingNode r4 = (androidx.compose.ui.node.DelegatingNode) r4
            androidx.compose.ui.Modifier$Node r4 = r4.delegate
            r6 = 0
        L6d:
            if (r4 == 0) goto L90
            int r7 = r4.kindSet
            r7 = r7 & 1024(0x400, float:1.435E-42)
            if (r7 == 0) goto L8d
            int r6 = r6 + 1
            if (r6 != r5) goto L7b
            r8 = r4
            goto L8d
        L7b:
            if (r3 != 0) goto L84
            androidx.compose.runtime.collection.MutableVector r3 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r7 = new androidx.compose.ui.Modifier.Node[r2]
            r3.<init>(r7)
        L84:
            if (r8 == 0) goto L8a
            r3.add(r8)
            r8 = r1
        L8a:
            r3.add(r4)
        L8d:
            androidx.compose.ui.Modifier$Node r4 = r4.child
            goto L6d
        L90:
            if (r6 != r5) goto L93
            goto L3d
        L93:
            androidx.compose.ui.Modifier$Node r8 = androidx.compose.ui.node.DelegatableNodeKt.access$pop(r3)
            goto L3d
        L98:
            androidx.compose.ui.Modifier$Node r8 = r8.child
            goto L34
        L9b:
            return r1
        L9c:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "visitChildren called on an unattached node"
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTraversalKt.getActiveChild(androidx.compose.ui.focus.FocusTargetNode):androidx.compose.ui.focus.FocusTargetNode");
    }

    public static final boolean isEligibleForFocusSearch(FocusTargetNode focusTargetNode) {
        LayoutNode layoutNode;
        NodeCoordinator nodeCoordinator;
        LayoutNode layoutNode2;
        NodeCoordinator nodeCoordinator2 = focusTargetNode.coordinator;
        return (nodeCoordinator2 == null || (layoutNode = nodeCoordinator2.layoutNode) == null || !layoutNode.isPlaced() || (nodeCoordinator = focusTargetNode.coordinator) == null || (layoutNode2 = nodeCoordinator.layoutNode) == null || !layoutNode2.isAttached()) ? false : true;
    }
}

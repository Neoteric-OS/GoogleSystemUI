package androidx.compose.ui.node;

import androidx.collection.MutableObjectIntMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Actual_jvmKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import java.util.HashSet;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NodeChain {
    public MutableVector buffer;
    public Differ cachedDiffer;
    public MutableVector current;
    public Modifier.Node head;
    public final InnerNodeCoordinator innerCoordinator;
    public final LayoutNode layoutNode;
    public NodeCoordinator outerCoordinator;
    public final TailModifierNode tail;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class Differ {
        public MutableVector after;
        public MutableVector before;
        public Modifier.Node node;
        public int offset;
        public boolean shouldAttachOnInsert;

        public Differ(Modifier.Node node, int i, MutableVector mutableVector, MutableVector mutableVector2, boolean z) {
            this.node = node;
            this.offset = i;
            this.before = mutableVector;
            this.after = mutableVector2;
            this.shouldAttachOnInsert = z;
        }

        public final boolean areItemsTheSame(int i, int i2) {
            MutableVector mutableVector = this.before;
            int i3 = this.offset;
            Modifier.Element element = (Modifier.Element) mutableVector.content[i + i3];
            Modifier.Element element2 = (Modifier.Element) this.after.content[i3 + i2];
            NodeChainKt$SentinelHead$1 nodeChainKt$SentinelHead$1 = NodeChainKt.SentinelHead;
            return Intrinsics.areEqual(element, element2) || Actual_jvmKt.areObjectsOfSameType(element, element2);
        }
    }

    public NodeChain(LayoutNode layoutNode) {
        this.layoutNode = layoutNode;
        InnerNodeCoordinator innerNodeCoordinator = new InnerNodeCoordinator(layoutNode);
        this.innerCoordinator = innerNodeCoordinator;
        this.outerCoordinator = innerNodeCoordinator;
        TailModifierNode tailModifierNode = innerNodeCoordinator.tail;
        this.tail = tailModifierNode;
        this.head = tailModifierNode;
    }

    public static final void access$propagateCoordinator(NodeChain nodeChain, Modifier.Node node, NodeCoordinator nodeCoordinator) {
        nodeChain.getClass();
        for (Modifier.Node node2 = node.parent; node2 != null; node2 = node2.parent) {
            if (node2 == NodeChainKt.SentinelHead) {
                LayoutNode parent$ui_release = nodeChain.layoutNode.getParent$ui_release();
                nodeCoordinator.wrappedBy = parent$ui_release != null ? parent$ui_release.nodes.innerCoordinator : null;
                nodeChain.outerCoordinator = nodeCoordinator;
                return;
            } else {
                if ((node2.kindSet & 2) != 0) {
                    return;
                }
                node2.updateCoordinator$ui_release(nodeCoordinator);
            }
        }
    }

    public static Modifier.Node createAndInsertNodeAsChild(Modifier.Element element, Modifier.Node node) {
        Modifier.Node node2;
        if (element instanceof ModifierNodeElement) {
            node2 = ((ModifierNodeElement) element).create();
            node2.kindSet = NodeKindKt.calculateNodeKindSetFromIncludingDelegates(node2);
        } else {
            BackwardsCompatNode backwardsCompatNode = new BackwardsCompatNode();
            backwardsCompatNode.kindSet = NodeKindKt.calculateNodeKindSetFrom(element);
            backwardsCompatNode.element = element;
            backwardsCompatNode.readValues = new HashSet();
            node2 = backwardsCompatNode;
        }
        if (node2.isAttached) {
            InlineClassHelperKt.throwIllegalStateException("A ModifierNodeElement cannot return an already attached node from create() ");
        }
        node2.insertedNodeAwaitingAttachForInvalidation = true;
        Modifier.Node node3 = node.child;
        if (node3 != null) {
            node3.parent = node2;
            node2.child = node3;
        }
        node.child = node2;
        node2.parent = node;
        return node2;
    }

    public static Modifier.Node detachAndRemoveNode(Modifier.Node node) {
        boolean z = node.isAttached;
        if (z) {
            MutableObjectIntMap mutableObjectIntMap = NodeKindKt.classToKindSetMap;
            if (!z) {
                InlineClassHelperKt.throwIllegalStateException("autoInvalidateRemovedNode called on unattached node");
            }
            NodeKindKt.autoInvalidateNodeIncludingDelegates(node, -1, 2);
            node.runDetachLifecycle$ui_release();
            node.markAsDetached$ui_release();
        }
        Modifier.Node node2 = node.child;
        Modifier.Node node3 = node.parent;
        if (node2 != null) {
            node2.parent = node3;
            node.child = null;
        }
        if (node3 != null) {
            node3.child = node2;
            node.parent = null;
        }
        Intrinsics.checkNotNull(node3);
        return node3;
    }

    public static void updateNode(Modifier.Element element, Modifier.Element element2, Modifier.Node node) {
        if ((element instanceof ModifierNodeElement) && (element2 instanceof ModifierNodeElement)) {
            NodeChainKt$SentinelHead$1 nodeChainKt$SentinelHead$1 = NodeChainKt.SentinelHead;
            ((ModifierNodeElement) element2).update(node);
            if (node.isAttached) {
                NodeKindKt.autoInvalidateUpdatedNode(node);
                return;
            } else {
                node.updatedNodeAwaitingAttachForInvalidation = true;
                return;
            }
        }
        if (!(node instanceof BackwardsCompatNode)) {
            throw new IllegalStateException("Unknown Modifier.Node type");
        }
        BackwardsCompatNode backwardsCompatNode = (BackwardsCompatNode) node;
        if (backwardsCompatNode.isAttached) {
            backwardsCompatNode.unInitializeModifier();
        }
        backwardsCompatNode.element = element2;
        backwardsCompatNode.kindSet = NodeKindKt.calculateNodeKindSetFrom(element2);
        if (backwardsCompatNode.isAttached) {
            backwardsCompatNode.initializeModifier(false);
        }
        if (node.isAttached) {
            NodeKindKt.autoInvalidateUpdatedNode(node);
        } else {
            node.updatedNodeAwaitingAttachForInvalidation = true;
        }
    }

    /* renamed from: has-H91voCI$ui_release, reason: not valid java name */
    public final boolean m525hasH91voCI$ui_release(int i) {
        return (this.head.aggregateChildKindSet & i) != 0;
    }

    public final void runAttachLifecycle() {
        for (Modifier.Node node = this.head; node != null; node = node.child) {
            node.runAttachLifecycle$ui_release();
            if (node.insertedNodeAwaitingAttachForInvalidation) {
                MutableObjectIntMap mutableObjectIntMap = NodeKindKt.classToKindSetMap;
                if (!node.isAttached) {
                    InlineClassHelperKt.throwIllegalStateException("autoInvalidateInsertedNode called on unattached node");
                }
                NodeKindKt.autoInvalidateNodeIncludingDelegates(node, -1, 1);
            }
            if (node.updatedNodeAwaitingAttachForInvalidation) {
                NodeKindKt.autoInvalidateUpdatedNode(node);
            }
            node.insertedNodeAwaitingAttachForInvalidation = false;
            node.updatedNodeAwaitingAttachForInvalidation = false;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x01d5, code lost:
    
        r10[0] = r7;
        r1 = 1;
        r10[1] = r4;
        r10[2] = r5;
        r2 = 3;
        r10[3] = r24;
        r10[4] = 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0254, code lost:
    
        r2 = r2 + 2;
        r21 = r21;
        r26 = r26;
        r12 = r25;
        r14 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x01bf, code lost:
    
        r25 = r12;
        r27 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x01a2, code lost:
    
        r24 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x018a, code lost:
    
        r5 = r9[(r2 + 1) + r18];
        r7 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x0267, code lost:
    
        r3 = r3 + 1;
        r7 = r32;
        r13 = r33;
        r5 = r21;
        r4 = r26;
        r12 = r12;
        r14 = r14;
        r1 = r22;
        r2 = r23;
        r15 = r34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0169, code lost:
    
        r1 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00ce, code lost:
    
        if (r8[(r15 + 1) + r18] > r8[(r15 - 1) + r18]) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x015a, code lost:
    
        r22 = r1;
        r23 = r2;
        r26 = r4;
        r21 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0165, code lost:
    
        if ((r20 % 2) != 0) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0167, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x016a, code lost:
    
        r2 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x016b, code lost:
    
        if (r2 > r3) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x016e, code lost:
    
        if (r2 == r13) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x0170, code lost:
    
        if (r2 == r3) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x017e, code lost:
    
        if (r9[(r2 + 1) + r18] >= r9[(r2 - 1) + r18]) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0181, code lost:
    
        r5 = r9[(r2 - 1) + r18];
        r7 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0191, code lost:
    
        r4 = r12 - ((r14 - r7) - r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0196, code lost:
    
        if (r3 == 0) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0198, code lost:
    
        if (r7 == r5) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x019c, code lost:
    
        r24 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01a4, code lost:
    
        if (r7 <= r11) goto L176;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x01a6, code lost:
    
        if (r4 <= r6) goto L178;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01a8, code lost:
    
        r25 = r12;
        r27 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01b4, code lost:
    
        if (r0.areItemsTheSame(r7 - 1, r4 - 1) == false) goto L177;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x01b6, code lost:
    
        r7 = r7 - 1;
        r4 = r4 - 1;
        r12 = r25;
        r14 = r27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01c3, code lost:
    
        r9[r18 + r2] = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x01c7, code lost:
    
        if (r1 == false) goto L172;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01c9, code lost:
    
        r12 = r20 - r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x01cb, code lost:
    
        if (r12 < r13) goto L173;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x01cd, code lost:
    
        if (r12 > r3) goto L174;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x01d3, code lost:
    
        if (r8[r18 + r12] < r7) goto L175;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void structuralUpdate(int r30, androidx.compose.runtime.collection.MutableVector r31, androidx.compose.runtime.collection.MutableVector r32, androidx.compose.ui.Modifier.Node r33, boolean r34) {
        /*
            Method dump skipped, instructions count: 973
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeChain.structuralUpdate(int, androidx.compose.runtime.collection.MutableVector, androidx.compose.runtime.collection.MutableVector, androidx.compose.ui.Modifier$Node, boolean):void");
    }

    public final void syncCoordinators() {
        LayoutNode layoutNode;
        LayoutModifierNodeCoordinator layoutModifierNodeCoordinator;
        Modifier.Node node = this.tail.parent;
        NodeCoordinator nodeCoordinator = this.innerCoordinator;
        Modifier.Node node2 = node;
        while (true) {
            layoutNode = this.layoutNode;
            if (node2 == null) {
                break;
            }
            LayoutModifierNode asLayoutModifierNode = DelegatableNodeKt.asLayoutModifierNode(node2);
            if (asLayoutModifierNode != null) {
                NodeCoordinator nodeCoordinator2 = node2.coordinator;
                if (nodeCoordinator2 != null) {
                    LayoutModifierNodeCoordinator layoutModifierNodeCoordinator2 = (LayoutModifierNodeCoordinator) nodeCoordinator2;
                    LayoutModifierNode layoutModifierNode = layoutModifierNodeCoordinator2.layoutModifierNode;
                    layoutModifierNodeCoordinator2.setLayoutModifierNode$ui_release(asLayoutModifierNode);
                    layoutModifierNodeCoordinator = layoutModifierNodeCoordinator2;
                    if (layoutModifierNode != node2) {
                        OwnedLayer ownedLayer = layoutModifierNodeCoordinator2.layer;
                        layoutModifierNodeCoordinator = layoutModifierNodeCoordinator2;
                        if (ownedLayer != null) {
                            ownedLayer.invalidate();
                            layoutModifierNodeCoordinator = layoutModifierNodeCoordinator2;
                        }
                    }
                } else {
                    LayoutModifierNodeCoordinator layoutModifierNodeCoordinator3 = new LayoutModifierNodeCoordinator(layoutNode, asLayoutModifierNode);
                    node2.updateCoordinator$ui_release(layoutModifierNodeCoordinator3);
                    layoutModifierNodeCoordinator = layoutModifierNodeCoordinator3;
                }
                nodeCoordinator.wrappedBy = layoutModifierNodeCoordinator;
                layoutModifierNodeCoordinator.wrapped = nodeCoordinator;
                nodeCoordinator = layoutModifierNodeCoordinator;
            } else {
                node2.updateCoordinator$ui_release(nodeCoordinator);
            }
            node2 = node2.parent;
        }
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        nodeCoordinator.wrappedBy = parent$ui_release != null ? parent$ui_release.nodes.innerCoordinator : null;
        this.outerCoordinator = nodeCoordinator;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        Modifier.Node node = this.head;
        TailModifierNode tailModifierNode = this.tail;
        if (node != tailModifierNode) {
            while (true) {
                if (node == null || node == tailModifierNode) {
                    break;
                }
                sb.append(String.valueOf(node));
                if (node.child == tailModifierNode) {
                    sb.append("]");
                    break;
                }
                sb.append(",");
                node = node.child;
            }
        } else {
            sb.append("]");
        }
        return sb.toString();
    }
}

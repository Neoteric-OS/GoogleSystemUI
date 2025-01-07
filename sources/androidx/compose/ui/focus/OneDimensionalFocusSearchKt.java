package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class OneDimensionalFocusSearchKt {
    public static final boolean backwardFocusSearch(FocusTargetNode focusTargetNode, Function1 function1) {
        int ordinal = focusTargetNode.getFocusState().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
                if (activeChild == null) {
                    throw new IllegalStateException("ActiveParent must have a focusedChild");
                }
                int ordinal2 = activeChild.getFocusState().ordinal();
                if (ordinal2 != 0) {
                    if (ordinal2 != 1) {
                        if (ordinal2 != 2) {
                            if (ordinal2 != 3) {
                                throw new NoWhenBranchMatchedException();
                            }
                            throw new IllegalStateException("ActiveParent must have a focusedChild");
                        }
                    } else if (!backwardFocusSearch(activeChild, function1) && !m297generateAndSearchChildren4C6V_qg(focusTargetNode, activeChild, 2, function1) && (!activeChild.fetchFocusProperties$ui_release().canFocus || !((Boolean) function1.invoke(activeChild)).booleanValue())) {
                        return false;
                    }
                }
                return m297generateAndSearchChildren4C6V_qg(focusTargetNode, activeChild, 2, function1);
            }
            if (ordinal != 2) {
                if (ordinal != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                if (!pickChildForBackwardSearch(focusTargetNode, function1)) {
                    if (!(focusTargetNode.fetchFocusProperties$ui_release().canFocus ? ((Boolean) function1.invoke(focusTargetNode)).booleanValue() : false)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return pickChildForBackwardSearch(focusTargetNode, function1);
    }

    public static final boolean forwardFocusSearch(FocusTargetNode focusTargetNode, Function1 function1) {
        int ordinal = focusTargetNode.getFocusState().ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
                if (activeChild != null) {
                    return forwardFocusSearch(activeChild, function1) || m297generateAndSearchChildren4C6V_qg(focusTargetNode, activeChild, 1, function1);
                }
                throw new IllegalStateException("ActiveParent must have a focusedChild");
            }
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return focusTargetNode.fetchFocusProperties$ui_release().canFocus ? ((Boolean) function1.invoke(focusTargetNode)).booleanValue() : pickChildForForwardSearch(focusTargetNode, function1);
                }
                throw new NoWhenBranchMatchedException();
            }
        }
        return pickChildForForwardSearch(focusTargetNode, function1);
    }

    /* renamed from: generateAndSearchChildren-4C6V_qg, reason: not valid java name */
    public static final boolean m297generateAndSearchChildren4C6V_qg(final FocusTargetNode focusTargetNode, final FocusTargetNode focusTargetNode2, final int i, final Function1 function1) {
        if (m298searchChildren4C6V_qg(focusTargetNode, focusTargetNode2, i, function1)) {
            return true;
        }
        Boolean bool = (Boolean) BeyondBoundsLayoutKt.m283searchBeyondBoundsOMvw8(focusTargetNode, i, new Function1() { // from class: androidx.compose.ui.focus.OneDimensionalFocusSearchKt$generateAndSearchChildren$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                BeyondBoundsLayout.BeyondBoundsScope beyondBoundsScope = (BeyondBoundsLayout.BeyondBoundsScope) obj;
                boolean m298searchChildren4C6V_qg = OneDimensionalFocusSearchKt.m298searchChildren4C6V_qg(FocusTargetNode.this, focusTargetNode2, i, function1);
                Boolean valueOf = Boolean.valueOf(m298searchChildren4C6V_qg);
                if (m298searchChildren4C6V_qg || !beyondBoundsScope.getHasMoreContent()) {
                    return valueOf;
                }
                return null;
            }
        });
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static final boolean pickChildForBackwardSearch(FocusTargetNode focusTargetNode, Function1 function1) {
        MutableVector mutableVector = new MutableVector(new FocusTargetNode[16]);
        Modifier.Node node = focusTargetNode.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16]);
        Modifier.Node node2 = node.child;
        if (node2 == null) {
            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node);
        } else {
            mutableVector2.add(node2);
        }
        while (true) {
            int i = mutableVector2.size;
            if (i == 0) {
                break;
            }
            Modifier.Node node3 = (Modifier.Node) mutableVector2.removeAt(i - 1);
            if ((node3.aggregateChildKindSet & 1024) == 0) {
                DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node3);
            } else {
                while (true) {
                    if (node3 == null) {
                        break;
                    }
                    if ((node3.kindSet & 1024) != 0) {
                        MutableVector mutableVector3 = null;
                        while (node3 != null) {
                            if (node3 instanceof FocusTargetNode) {
                                mutableVector.add((FocusTargetNode) node3);
                            } else if ((node3.kindSet & 1024) != 0 && (node3 instanceof DelegatingNode)) {
                                int i2 = 0;
                                for (Modifier.Node node4 = ((DelegatingNode) node3).delegate; node4 != null; node4 = node4.child) {
                                    if ((node4.kindSet & 1024) != 0) {
                                        i2++;
                                        if (i2 == 1) {
                                            node3 = node4;
                                        } else {
                                            if (mutableVector3 == null) {
                                                mutableVector3 = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (node3 != null) {
                                                mutableVector3.add(node3);
                                                node3 = null;
                                            }
                                            mutableVector3.add(node4);
                                        }
                                    }
                                }
                                if (i2 == 1) {
                                }
                            }
                            node3 = DelegatableNodeKt.access$pop(mutableVector3);
                        }
                    } else {
                        node3 = node3.child;
                    }
                }
            }
        }
        mutableVector.sortWith(FocusableChildrenComparator.INSTANCE);
        int i3 = mutableVector.size;
        if (i3 > 0) {
            int i4 = i3 - 1;
            Object[] objArr = mutableVector.content;
            do {
                FocusTargetNode focusTargetNode2 = (FocusTargetNode) objArr[i4];
                if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetNode2) && backwardFocusSearch(focusTargetNode2, function1)) {
                    return true;
                }
                i4--;
            } while (i4 >= 0);
        }
        return false;
    }

    public static final boolean pickChildForForwardSearch(FocusTargetNode focusTargetNode, Function1 function1) {
        MutableVector mutableVector = new MutableVector(new FocusTargetNode[16]);
        Modifier.Node node = focusTargetNode.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16]);
        Modifier.Node node2 = node.child;
        if (node2 == null) {
            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node);
        } else {
            mutableVector2.add(node2);
        }
        while (true) {
            int i = mutableVector2.size;
            if (i == 0) {
                break;
            }
            Modifier.Node node3 = (Modifier.Node) mutableVector2.removeAt(i - 1);
            if ((node3.aggregateChildKindSet & 1024) == 0) {
                DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node3);
            } else {
                while (true) {
                    if (node3 == null) {
                        break;
                    }
                    if ((node3.kindSet & 1024) != 0) {
                        MutableVector mutableVector3 = null;
                        while (node3 != null) {
                            if (node3 instanceof FocusTargetNode) {
                                mutableVector.add((FocusTargetNode) node3);
                            } else if ((node3.kindSet & 1024) != 0 && (node3 instanceof DelegatingNode)) {
                                int i2 = 0;
                                for (Modifier.Node node4 = ((DelegatingNode) node3).delegate; node4 != null; node4 = node4.child) {
                                    if ((node4.kindSet & 1024) != 0) {
                                        i2++;
                                        if (i2 == 1) {
                                            node3 = node4;
                                        } else {
                                            if (mutableVector3 == null) {
                                                mutableVector3 = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (node3 != null) {
                                                mutableVector3.add(node3);
                                                node3 = null;
                                            }
                                            mutableVector3.add(node4);
                                        }
                                    }
                                }
                                if (i2 == 1) {
                                }
                            }
                            node3 = DelegatableNodeKt.access$pop(mutableVector3);
                        }
                    } else {
                        node3 = node3.child;
                    }
                }
            }
        }
        mutableVector.sortWith(FocusableChildrenComparator.INSTANCE);
        int i3 = mutableVector.size;
        if (i3 > 0) {
            Object[] objArr = mutableVector.content;
            int i4 = 0;
            do {
                FocusTargetNode focusTargetNode2 = (FocusTargetNode) objArr[i4];
                if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetNode2) && forwardFocusSearch(focusTargetNode2, function1)) {
                    return true;
                }
                i4++;
            } while (i4 < i3);
        }
        return false;
    }

    /* renamed from: searchChildren-4C6V_qg, reason: not valid java name */
    public static final boolean m298searchChildren4C6V_qg(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2, int i, Function1 function1) {
        Modifier.Node node;
        NodeChain nodeChain;
        if (focusTargetNode.getFocusState() != FocusStateImpl.ActiveParent) {
            throw new IllegalStateException("This function should only be used within a parent that has focus.");
        }
        MutableVector mutableVector = new MutableVector(new FocusTargetNode[16]);
        Modifier.Node node2 = focusTargetNode.node;
        if (!node2.isAttached) {
            throw new IllegalStateException("visitChildren called on an unattached node");
        }
        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16]);
        Modifier.Node node3 = node2.child;
        if (node3 == null) {
            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node2);
        } else {
            mutableVector2.add(node3);
        }
        while (true) {
            int i2 = mutableVector2.size;
            node = null;
            if (i2 == 0) {
                break;
            }
            Modifier.Node node4 = (Modifier.Node) mutableVector2.removeAt(i2 - 1);
            if ((node4.aggregateChildKindSet & 1024) == 0) {
                DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node4);
            } else {
                while (true) {
                    if (node4 == null) {
                        break;
                    }
                    if ((node4.kindSet & 1024) != 0) {
                        MutableVector mutableVector3 = null;
                        while (node4 != null) {
                            if (node4 instanceof FocusTargetNode) {
                                mutableVector.add((FocusTargetNode) node4);
                            } else if ((node4.kindSet & 1024) != 0 && (node4 instanceof DelegatingNode)) {
                                int i3 = 0;
                                for (Modifier.Node node5 = ((DelegatingNode) node4).delegate; node5 != null; node5 = node5.child) {
                                    if ((node5.kindSet & 1024) != 0) {
                                        i3++;
                                        if (i3 == 1) {
                                            node4 = node5;
                                        } else {
                                            if (mutableVector3 == null) {
                                                mutableVector3 = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (node4 != null) {
                                                mutableVector3.add(node4);
                                                node4 = null;
                                            }
                                            mutableVector3.add(node5);
                                        }
                                    }
                                }
                                if (i3 == 1) {
                                }
                            }
                            node4 = DelegatableNodeKt.access$pop(mutableVector3);
                        }
                    } else {
                        node4 = node4.child;
                    }
                }
            }
        }
        mutableVector.sortWith(FocusableChildrenComparator.INSTANCE);
        if (FocusDirection.m284equalsimpl0(i, 1)) {
            IntRange until = RangesKt.until(0, mutableVector.size);
            int i4 = until.first;
            int i5 = until.last;
            if (i4 <= i5) {
                boolean z = false;
                while (true) {
                    if (z) {
                        FocusTargetNode focusTargetNode3 = (FocusTargetNode) mutableVector.content[i4];
                        if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetNode3) && forwardFocusSearch(focusTargetNode3, function1)) {
                            return true;
                        }
                    }
                    if (Intrinsics.areEqual(mutableVector.content[i4], focusTargetNode2)) {
                        z = true;
                    }
                    if (i4 == i5) {
                        break;
                    }
                    i4++;
                }
            }
        } else {
            if (!FocusDirection.m284equalsimpl0(i, 2)) {
                throw new IllegalStateException("This function should only be used for 1-D focus search");
            }
            IntRange until2 = RangesKt.until(0, mutableVector.size);
            int i6 = until2.first;
            int i7 = until2.last;
            if (i6 <= i7) {
                boolean z2 = false;
                while (true) {
                    if (z2) {
                        FocusTargetNode focusTargetNode4 = (FocusTargetNode) mutableVector.content[i7];
                        if (FocusTraversalKt.isEligibleForFocusSearch(focusTargetNode4) && backwardFocusSearch(focusTargetNode4, function1)) {
                            return true;
                        }
                    }
                    if (Intrinsics.areEqual(mutableVector.content[i7], focusTargetNode2)) {
                        z2 = true;
                    }
                    if (i7 == i6) {
                        break;
                    }
                    i7--;
                }
            }
        }
        if (!FocusDirection.m284equalsimpl0(i, 1) && focusTargetNode.fetchFocusProperties$ui_release().canFocus) {
            Modifier.Node node6 = focusTargetNode.node;
            if (!node6.isAttached) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node7 = node6.parent;
            LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
            loop5: while (true) {
                if (requireLayoutNode == null) {
                    break;
                }
                if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 1024) != 0) {
                    while (node7 != null) {
                        if ((node7.kindSet & 1024) != 0) {
                            Modifier.Node node8 = node7;
                            MutableVector mutableVector4 = null;
                            while (node8 != null) {
                                if (node8 instanceof FocusTargetNode) {
                                    node = node8;
                                    break loop5;
                                }
                                if ((node8.kindSet & 1024) != 0 && (node8 instanceof DelegatingNode)) {
                                    int i8 = 0;
                                    for (Modifier.Node node9 = ((DelegatingNode) node8).delegate; node9 != null; node9 = node9.child) {
                                        if ((node9.kindSet & 1024) != 0) {
                                            i8++;
                                            if (i8 == 1) {
                                                node8 = node9;
                                            } else {
                                                if (mutableVector4 == null) {
                                                    mutableVector4 = new MutableVector(new Modifier.Node[16]);
                                                }
                                                if (node8 != null) {
                                                    mutableVector4.add(node8);
                                                    node8 = null;
                                                }
                                                mutableVector4.add(node9);
                                            }
                                        }
                                    }
                                    if (i8 == 1) {
                                    }
                                }
                                node8 = DelegatableNodeKt.access$pop(mutableVector4);
                            }
                        }
                        node7 = node7.parent;
                    }
                }
                requireLayoutNode = requireLayoutNode.getParent$ui_release();
                node7 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
            }
            if (node != null) {
                return ((Boolean) function1.invoke(focusTargetNode)).booleanValue();
            }
        }
        return false;
    }
}

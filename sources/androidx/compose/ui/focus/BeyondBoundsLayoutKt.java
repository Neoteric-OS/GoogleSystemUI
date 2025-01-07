package androidx.compose.ui.focus;

import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierNode;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BeyondBoundsLayoutKt {
    /* renamed from: searchBeyondBounds--OM-vw8, reason: not valid java name */
    public static final Object m283searchBeyondBoundsOMvw8(FocusTargetNode focusTargetNode, int i, Function1 function1) {
        final int i2;
        Object obj;
        Modifier.Node node;
        final LazyLayoutBeyondBoundsModifierNode lazyLayoutBeyondBoundsModifierNode;
        NodeChain nodeChain;
        Modifier.Node node2 = focusTargetNode.node;
        if (!node2.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node3 = node2.parent;
        LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
        loop0: while (true) {
            i2 = 1;
            obj = null;
            if (requireLayoutNode == null) {
                node = null;
                break;
            }
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 1024) != 0) {
                while (node3 != null) {
                    if ((node3.kindSet & 1024) != 0) {
                        node = node3;
                        MutableVector mutableVector = null;
                        while (node != null) {
                            if (node instanceof FocusTargetNode) {
                                break loop0;
                            }
                            if ((node.kindSet & 1024) != 0 && (node instanceof DelegatingNode)) {
                                int i3 = 0;
                                for (Modifier.Node node4 = ((DelegatingNode) node).delegate; node4 != null; node4 = node4.child) {
                                    if ((node4.kindSet & 1024) != 0) {
                                        i3++;
                                        if (i3 == 1) {
                                            node = node4;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (node != null) {
                                                mutableVector.add(node);
                                                node = null;
                                            }
                                            mutableVector.add(node4);
                                        }
                                    }
                                }
                                if (i3 == 1) {
                                }
                            }
                            node = DelegatableNodeKt.access$pop(mutableVector);
                        }
                    }
                    node3 = node3.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node3 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
        }
        FocusTargetNode focusTargetNode2 = (FocusTargetNode) node;
        if (focusTargetNode2 != null) {
            ProvidableModifierLocal providableModifierLocal = androidx.compose.ui.layout.BeyondBoundsLayoutKt.ModifierLocalBeyondBoundsLayout;
            if (Intrinsics.areEqual((BeyondBoundsLayout) focusTargetNode2.getCurrent(providableModifierLocal), (BeyondBoundsLayout) focusTargetNode.getCurrent(providableModifierLocal))) {
                return null;
            }
        }
        BeyondBoundsLayout beyondBoundsLayout = (BeyondBoundsLayout) focusTargetNode.getCurrent(androidx.compose.ui.layout.BeyondBoundsLayoutKt.ModifierLocalBeyondBoundsLayout);
        if (beyondBoundsLayout == null) {
            return null;
        }
        int i4 = 5;
        if (!FocusDirection.m284equalsimpl0(i, 5)) {
            i4 = 6;
            if (!FocusDirection.m284equalsimpl0(i, 6)) {
                i4 = 3;
                if (!FocusDirection.m284equalsimpl0(i, 3)) {
                    i4 = 4;
                    if (!FocusDirection.m284equalsimpl0(i, 4)) {
                        if (FocusDirection.m284equalsimpl0(i, 1)) {
                            i2 = 2;
                        } else if (!FocusDirection.m284equalsimpl0(i, 2)) {
                            throw new IllegalStateException("Unsupported direction for beyond bounds layout");
                        }
                        lazyLayoutBeyondBoundsModifierNode = (LazyLayoutBeyondBoundsModifierNode) beyondBoundsLayout;
                        if (lazyLayoutBeyondBoundsModifierNode.state.getItemCount() > 0 || !lazyLayoutBeyondBoundsModifierNode.state.getHasVisibleItems() || !lazyLayoutBeyondBoundsModifierNode.isAttached) {
                            return function1.invoke(LazyLayoutBeyondBoundsModifierNode.emptyBeyondBoundsScope);
                        }
                        int lastPlacedIndex = lazyLayoutBeyondBoundsModifierNode.m132isForward4vf7U8o(i2) ? lazyLayoutBeyondBoundsModifierNode.state.getLastPlacedIndex() : lazyLayoutBeyondBoundsModifierNode.state.getFirstPlacedIndex();
                        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                        LazyLayoutBeyondBoundsInfo lazyLayoutBeyondBoundsInfo = lazyLayoutBeyondBoundsModifierNode.beyondBoundsInfo;
                        lazyLayoutBeyondBoundsInfo.getClass();
                        LazyLayoutBeyondBoundsInfo.Interval interval = new LazyLayoutBeyondBoundsInfo.Interval(lastPlacedIndex, lastPlacedIndex);
                        lazyLayoutBeyondBoundsInfo.beyondBoundsItems.add(interval);
                        ref$ObjectRef.element = interval;
                        while (obj == null && lazyLayoutBeyondBoundsModifierNode.m131hasMoreContentFR3nfPY((LazyLayoutBeyondBoundsInfo.Interval) ref$ObjectRef.element, i2)) {
                            LazyLayoutBeyondBoundsInfo.Interval interval2 = (LazyLayoutBeyondBoundsInfo.Interval) ref$ObjectRef.element;
                            int i5 = interval2.start;
                            boolean m132isForward4vf7U8o = lazyLayoutBeyondBoundsModifierNode.m132isForward4vf7U8o(i2);
                            int i6 = interval2.end;
                            if (m132isForward4vf7U8o) {
                                i6++;
                            } else {
                                i5--;
                            }
                            LazyLayoutBeyondBoundsInfo lazyLayoutBeyondBoundsInfo2 = lazyLayoutBeyondBoundsModifierNode.beyondBoundsInfo;
                            lazyLayoutBeyondBoundsInfo2.getClass();
                            LazyLayoutBeyondBoundsInfo.Interval interval3 = new LazyLayoutBeyondBoundsInfo.Interval(i5, i6);
                            lazyLayoutBeyondBoundsInfo2.beyondBoundsItems.add(interval3);
                            lazyLayoutBeyondBoundsModifierNode.beyondBoundsInfo.beyondBoundsItems.remove((LazyLayoutBeyondBoundsInfo.Interval) ref$ObjectRef.element);
                            ref$ObjectRef.element = interval3;
                            DelegatableNodeKt.requireLayoutNode(lazyLayoutBeyondBoundsModifierNode).forceRemeasure();
                            obj = function1.invoke(new BeyondBoundsLayout.BeyondBoundsScope() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierNode$layout$2
                                @Override // androidx.compose.ui.layout.BeyondBoundsLayout.BeyondBoundsScope
                                public final boolean getHasMoreContent() {
                                    return LazyLayoutBeyondBoundsModifierNode.this.m131hasMoreContentFR3nfPY((LazyLayoutBeyondBoundsInfo.Interval) ref$ObjectRef.element, i2);
                                }
                            });
                        }
                        lazyLayoutBeyondBoundsModifierNode.beyondBoundsInfo.beyondBoundsItems.remove((LazyLayoutBeyondBoundsInfo.Interval) ref$ObjectRef.element);
                        DelegatableNodeKt.requireLayoutNode(lazyLayoutBeyondBoundsModifierNode).forceRemeasure();
                        return obj;
                    }
                }
            }
        }
        i2 = i4;
        lazyLayoutBeyondBoundsModifierNode = (LazyLayoutBeyondBoundsModifierNode) beyondBoundsLayout;
        if (lazyLayoutBeyondBoundsModifierNode.state.getItemCount() > 0) {
        }
        return function1.invoke(LazyLayoutBeyondBoundsModifierNode.emptyBeyondBoundsScope);
    }
}

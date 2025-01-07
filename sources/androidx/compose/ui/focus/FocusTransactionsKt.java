package androidx.compose.ui.focus;

import android.os.Trace;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FocusTransactionsKt {
    public static final boolean clearFocus(FocusTargetNode focusTargetNode, boolean z) {
        int ordinal = focusTargetNode.getFocusState().ordinal();
        FocusStateImpl focusStateImpl = FocusStateImpl.Inactive;
        if (ordinal == 0) {
            focusTargetNode.setFocusState(focusStateImpl);
            focusTargetNode.dispatchFocusCallbacks$ui_release();
        } else if (ordinal == 1) {
            FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
            if (!(activeChild != null ? clearFocus(activeChild, z) : true)) {
                return false;
            }
            focusTargetNode.setFocusState(focusStateImpl);
            focusTargetNode.dispatchFocusCallbacks$ui_release();
        } else {
            if (ordinal == 2) {
                if (!z) {
                    return z;
                }
                focusTargetNode.setFocusState(focusStateImpl);
                focusTargetNode.dispatchFocusCallbacks$ui_release();
                return z;
            }
            if (ordinal != 3) {
                throw new NoWhenBranchMatchedException();
            }
        }
        return true;
    }

    public static final void grantFocus(final FocusTargetNode focusTargetNode) {
        ObserverModifierNodeKt.observeReads(focusTargetNode, new Function0() { // from class: androidx.compose.ui.focus.FocusTransactionsKt$grantFocus$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                FocusTargetNode.this.fetchFocusProperties$ui_release();
                return Unit.INSTANCE;
            }
        });
        int ordinal = focusTargetNode.getFocusState().ordinal();
        if (ordinal == 1 || ordinal == 3) {
            focusTargetNode.setFocusState(FocusStateImpl.Active);
        }
    }

    /* renamed from: performCustomClearFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m293performCustomClearFocusMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        int ordinal = focusTargetNode.getFocusState().ordinal();
        CustomDestinationResult customDestinationResult = CustomDestinationResult.None;
        if (ordinal == 0) {
            return customDestinationResult;
        }
        CustomDestinationResult customDestinationResult2 = CustomDestinationResult.Cancelled;
        if (ordinal == 1) {
            FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
            if (activeChild == null) {
                throw new IllegalArgumentException("ActiveParent with no focused child");
            }
            CustomDestinationResult m293performCustomClearFocusMxy_nc0 = m293performCustomClearFocusMxy_nc0(activeChild, i);
            if (m293performCustomClearFocusMxy_nc0 == customDestinationResult) {
                m293performCustomClearFocusMxy_nc0 = null;
            }
            if (m293performCustomClearFocusMxy_nc0 != null) {
                return m293performCustomClearFocusMxy_nc0;
            }
            if (focusTargetNode.isProcessingCustomExit) {
                return customDestinationResult;
            }
            focusTargetNode.isProcessingCustomExit = true;
            try {
                FocusRequester focusRequester = (FocusRequester) focusTargetNode.fetchFocusProperties$ui_release().exit.invoke(new FocusDirection(i));
                if (focusRequester != FocusRequester.Default) {
                    if (focusRequester == FocusRequester.Cancel) {
                        focusTargetNode.isProcessingCustomExit = false;
                    } else {
                        customDestinationResult = focusRequester.findFocusTargetNode$ui_release(FocusRequester$focus$1.INSTANCE) ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                    }
                }
                return customDestinationResult;
            } finally {
                focusTargetNode.isProcessingCustomExit = false;
            }
        }
        if (ordinal != 2) {
            if (ordinal == 3) {
                return customDestinationResult;
            }
            throw new NoWhenBranchMatchedException();
        }
        return customDestinationResult2;
    }

    /* renamed from: performCustomEnter-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m294performCustomEnterMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        if (!focusTargetNode.isProcessingCustomEnter) {
            focusTargetNode.isProcessingCustomEnter = true;
            try {
                FocusRequester focusRequester = (FocusRequester) focusTargetNode.fetchFocusProperties$ui_release().enter.invoke(new FocusDirection(i));
                if (focusRequester != FocusRequester.Default) {
                    if (focusRequester == FocusRequester.Cancel) {
                        return CustomDestinationResult.Cancelled;
                    }
                    return focusRequester.findFocusTargetNode$ui_release(FocusRequester$focus$1.INSTANCE) ? CustomDestinationResult.Redirected : CustomDestinationResult.RedirectCancelled;
                }
            } finally {
                focusTargetNode.isProcessingCustomEnter = false;
            }
        }
        return CustomDestinationResult.None;
    }

    /* renamed from: performCustomRequestFocus-Mxy_nc0, reason: not valid java name */
    public static final CustomDestinationResult m295performCustomRequestFocusMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        Modifier.Node node;
        NodeChain nodeChain;
        int ordinal = focusTargetNode.getFocusState().ordinal();
        CustomDestinationResult customDestinationResult = CustomDestinationResult.None;
        if (ordinal != 0) {
            if (ordinal == 1) {
                FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
                if (activeChild != null) {
                    return m293performCustomClearFocusMxy_nc0(activeChild, i);
                }
                throw new IllegalArgumentException("ActiveParent with no focused child");
            }
            if (ordinal != 2) {
                if (ordinal != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                Modifier.Node node2 = focusTargetNode.node;
                if (!node2.isAttached) {
                    throw new IllegalStateException("visitAncestors called on an unattached node");
                }
                Modifier.Node node3 = node2.parent;
                LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
                loop0: while (true) {
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
                                        int i2 = 0;
                                        for (Modifier.Node node4 = ((DelegatingNode) node).delegate; node4 != null; node4 = node4.child) {
                                            if ((node4.kindSet & 1024) != 0) {
                                                i2++;
                                                if (i2 == 1) {
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
                                        if (i2 == 1) {
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
                if (focusTargetNode2 == null) {
                    return customDestinationResult;
                }
                int ordinal2 = focusTargetNode2.getFocusState().ordinal();
                if (ordinal2 == 0) {
                    return m294performCustomEnterMxy_nc0(focusTargetNode2, i);
                }
                if (ordinal2 == 1) {
                    return m295performCustomRequestFocusMxy_nc0(focusTargetNode2, i);
                }
                if (ordinal2 == 2) {
                    return CustomDestinationResult.Cancelled;
                }
                if (ordinal2 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                CustomDestinationResult m295performCustomRequestFocusMxy_nc0 = m295performCustomRequestFocusMxy_nc0(focusTargetNode2, i);
                CustomDestinationResult customDestinationResult2 = m295performCustomRequestFocusMxy_nc0 != customDestinationResult ? m295performCustomRequestFocusMxy_nc0 : null;
                return customDestinationResult2 == null ? m294performCustomEnterMxy_nc0(focusTargetNode2, i) : customDestinationResult2;
            }
        }
        return customDestinationResult;
    }

    public static final boolean performRequestFocus(FocusTargetNode focusTargetNode) {
        Modifier.Node node;
        NodeChain nodeChain;
        int ordinal = focusTargetNode.getFocusState().ordinal();
        boolean z = true;
        if (ordinal != 0) {
            if (ordinal == 1) {
                FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
                if (activeChild != null ? clearFocus(activeChild, false) : true) {
                    grantFocus(focusTargetNode);
                }
                z = false;
            } else if (ordinal != 2) {
                if (ordinal != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                Modifier.Node node2 = focusTargetNode.node;
                if (!node2.isAttached) {
                    throw new IllegalStateException("visitAncestors called on an unattached node");
                }
                Modifier.Node node3 = node2.parent;
                LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
                loop0: while (true) {
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
                                        int i = 0;
                                        for (Modifier.Node node4 = ((DelegatingNode) node).delegate; node4 != null; node4 = node4.child) {
                                            if ((node4.kindSet & 1024) != 0) {
                                                i++;
                                                if (i == 1) {
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
                                        if (i == 1) {
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
                    FocusStateImpl focusState = focusTargetNode2.getFocusState();
                    z = requestFocusForChild(focusTargetNode2, focusTargetNode);
                    if (z && focusState != focusTargetNode2.getFocusState()) {
                        focusTargetNode2.dispatchFocusCallbacks$ui_release();
                    }
                } else {
                    if (((Boolean) ((AndroidComposeView) DelegatableNodeKt.requireOwner(focusTargetNode)).focusOwner.onRequestFocusForOwner.invoke(null, null)).booleanValue()) {
                        grantFocus(focusTargetNode);
                    }
                    z = false;
                }
            }
        }
        if (z) {
            focusTargetNode.dispatchFocusCallbacks$ui_release();
        }
        return z;
    }

    /* renamed from: requestFocus-Mxy_nc0, reason: not valid java name */
    public static final Boolean m296requestFocusMxy_nc0(final FocusTargetNode focusTargetNode, int i) {
        Boolean valueOf;
        Trace.beginSection("FocusTransactions:requestFocus");
        try {
            if (!focusTargetNode.fetchFocusProperties$ui_release().canFocus) {
                return Boolean.FALSE;
            }
            FocusTransactionManager requireTransactionManager = FocusTargetNodeKt.requireTransactionManager(focusTargetNode);
            Function0 function0 = new Function0() { // from class: androidx.compose.ui.focus.FocusTransactionsKt$requestFocus$1$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    FocusTargetNode focusTargetNode2 = FocusTargetNode.this;
                    if (focusTargetNode2.node.isAttached) {
                        focusTargetNode2.dispatchFocusCallbacks$ui_release();
                    }
                    return Unit.INSTANCE;
                }
            };
            try {
                if (requireTransactionManager.ongoingTransaction) {
                    FocusTransactionManager.access$cancelTransaction(requireTransactionManager);
                }
                requireTransactionManager.ongoingTransaction = true;
                requireTransactionManager.cancellationListener.add(function0);
                int ordinal = m295performCustomRequestFocusMxy_nc0(focusTargetNode, i).ordinal();
                if (ordinal != 0) {
                    if (ordinal != 1) {
                        if (ordinal == 2) {
                            valueOf = Boolean.TRUE;
                        } else if (ordinal != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                    }
                    valueOf = null;
                } else {
                    valueOf = Boolean.valueOf(performRequestFocus(focusTargetNode));
                }
                return valueOf;
            } finally {
                FocusTransactionManager.access$commitTransaction(requireTransactionManager);
            }
        } finally {
            Trace.endSection();
        }
    }

    public static final boolean requestFocusForChild(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2) {
        Modifier.Node node;
        Modifier.Node node2;
        NodeChain nodeChain;
        NodeChain nodeChain2;
        Modifier.Node node3 = focusTargetNode2.node;
        if (!node3.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node4 = node3.parent;
        LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode2);
        loop0: while (true) {
            if (requireLayoutNode == null) {
                node = null;
                break;
            }
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 1024) != 0) {
                while (node4 != null) {
                    if ((node4.kindSet & 1024) != 0) {
                        node = node4;
                        MutableVector mutableVector = null;
                        while (node != null) {
                            if (node instanceof FocusTargetNode) {
                                break loop0;
                            }
                            if ((node.kindSet & 1024) != 0 && (node instanceof DelegatingNode)) {
                                int i = 0;
                                for (Modifier.Node node5 = ((DelegatingNode) node).delegate; node5 != null; node5 = node5.child) {
                                    if ((node5.kindSet & 1024) != 0) {
                                        i++;
                                        if (i == 1) {
                                            node = node5;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (node != null) {
                                                mutableVector.add(node);
                                                node = null;
                                            }
                                            mutableVector.add(node5);
                                        }
                                    }
                                }
                                if (i == 1) {
                                }
                            }
                            node = DelegatableNodeKt.access$pop(mutableVector);
                        }
                    }
                    node4 = node4.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node4 = (requireLayoutNode == null || (nodeChain2 = requireLayoutNode.nodes) == null) ? null : nodeChain2.tail;
        }
        if (!Intrinsics.areEqual(node, focusTargetNode)) {
            throw new IllegalStateException("Non child node cannot request focus.");
        }
        int ordinal = focusTargetNode.getFocusState().ordinal();
        FocusStateImpl focusStateImpl = FocusStateImpl.ActiveParent;
        if (ordinal == 0) {
            grantFocus(focusTargetNode2);
            focusTargetNode.setFocusState(focusStateImpl);
        } else if (ordinal != 1) {
            if (ordinal == 2) {
                return false;
            }
            if (ordinal != 3) {
                throw new NoWhenBranchMatchedException();
            }
            Modifier.Node node6 = focusTargetNode.node;
            if (!node6.isAttached) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            Modifier.Node node7 = node6.parent;
            LayoutNode requireLayoutNode2 = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
            loop4: while (true) {
                if (requireLayoutNode2 == null) {
                    node2 = null;
                    break;
                }
                if ((requireLayoutNode2.nodes.head.aggregateChildKindSet & 1024) != 0) {
                    while (node7 != null) {
                        if ((node7.kindSet & 1024) != 0) {
                            node2 = node7;
                            MutableVector mutableVector2 = null;
                            while (node2 != null) {
                                if (node2 instanceof FocusTargetNode) {
                                    break loop4;
                                }
                                if ((node2.kindSet & 1024) != 0 && (node2 instanceof DelegatingNode)) {
                                    int i2 = 0;
                                    for (Modifier.Node node8 = ((DelegatingNode) node2).delegate; node8 != null; node8 = node8.child) {
                                        if ((node8.kindSet & 1024) != 0) {
                                            i2++;
                                            if (i2 == 1) {
                                                node2 = node8;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    mutableVector2 = new MutableVector(new Modifier.Node[16]);
                                                }
                                                if (node2 != null) {
                                                    mutableVector2.add(node2);
                                                    node2 = null;
                                                }
                                                mutableVector2.add(node8);
                                            }
                                        }
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                node2 = DelegatableNodeKt.access$pop(mutableVector2);
                            }
                        }
                        node7 = node7.parent;
                    }
                }
                requireLayoutNode2 = requireLayoutNode2.getParent$ui_release();
                node7 = (requireLayoutNode2 == null || (nodeChain = requireLayoutNode2.nodes) == null) ? null : nodeChain.tail;
            }
            FocusTargetNode focusTargetNode3 = (FocusTargetNode) node2;
            if (focusTargetNode3 != null || !((Boolean) ((AndroidComposeView) DelegatableNodeKt.requireOwner(focusTargetNode)).focusOwner.onRequestFocusForOwner.invoke(null, null)).booleanValue()) {
                if (focusTargetNode3 == null || !requestFocusForChild(focusTargetNode3, focusTargetNode)) {
                    return false;
                }
                boolean requestFocusForChild = requestFocusForChild(focusTargetNode, focusTargetNode2);
                if (focusTargetNode.getFocusState() != focusStateImpl) {
                    throw new IllegalStateException("Deactivated node is focused");
                }
                if (!requestFocusForChild) {
                    return requestFocusForChild;
                }
                focusTargetNode3.dispatchFocusCallbacks$ui_release();
                return requestFocusForChild;
            }
            grantFocus(focusTargetNode2);
            focusTargetNode.setFocusState(focusStateImpl);
        } else {
            if (FocusTraversalKt.getActiveChild(focusTargetNode) == null) {
                throw new IllegalArgumentException("ActiveParent with no focused child");
            }
            FocusTargetNode activeChild = FocusTraversalKt.getActiveChild(focusTargetNode);
            if (!(activeChild != null ? clearFocus(activeChild, false) : true)) {
                return false;
            }
            grantFocus(focusTargetNode2);
        }
        return true;
    }
}

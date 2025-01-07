package androidx.compose.ui.focus;

import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.InputMode;
import androidx.compose.ui.input.InputModeManager;
import androidx.compose.ui.input.InputModeManagerImpl;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.CompositionLocalsKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FocusTargetNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, FocusTargetModifierNode, ObserverModifierNode, ModifierLocalModifierNode {
    public FocusStateImpl committedFocusState;
    public final int focusability;
    public boolean isProcessingCustomEnter;
    public boolean isProcessingCustomExit;
    public final Function2 onFocusChange;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FocusTargetElement extends ModifierNodeElement {
        public static final FocusTargetElement INSTANCE = new FocusTargetElement();

        private FocusTargetElement() {
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public final Modifier.Node create() {
            return new FocusTargetNode(3);
        }

        public final boolean equals(Object obj) {
            return obj == this;
        }

        public final int hashCode() {
            return "focusTarget".hashCode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public final /* bridge */ /* synthetic */ void update(Modifier.Node node) {
        }
    }

    public /* synthetic */ FocusTargetNode(int i) {
        this((i & 1) == 0 ? 2 : 1, null);
    }

    public static final boolean initializeFocusState$hasActiveChild(FocusTargetNode focusTargetNode) {
        if (!focusTargetNode.node.isAttached) {
            InlineClassHelperKt.throwIllegalStateException("visitSubtreeIf called on an unattached node");
        }
        MutableVector mutableVector = new MutableVector(new Modifier.Node[16]);
        Modifier.Node node = focusTargetNode.node;
        Modifier.Node node2 = node.child;
        if (node2 == null) {
            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector, node);
        } else {
            mutableVector.add(node2);
        }
        while (true) {
            int i = mutableVector.size;
            if (i == 0) {
                return false;
            }
            Modifier.Node node3 = (Modifier.Node) mutableVector.removeAt(i - 1);
            if ((node3.aggregateChildKindSet & 1024) != 0) {
                for (Modifier.Node node4 = node3; node4 != null; node4 = node4.child) {
                    if ((node4.kindSet & 1024) != 0) {
                        Modifier.Node node5 = node4;
                        MutableVector mutableVector2 = null;
                        while (node5 != null) {
                            if (node5 instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) node5;
                                if (focusTargetNode2.committedFocusState != null) {
                                    int ordinal = focusTargetNode2.getFocusState().ordinal();
                                    if (ordinal == 0 || ordinal == 1 || ordinal == 2) {
                                        break;
                                    }
                                    if (ordinal != 3) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                }
                            } else if ((node5.kindSet & 1024) != 0 && (node5 instanceof DelegatingNode)) {
                                int i2 = 0;
                                for (Modifier.Node node6 = ((DelegatingNode) node5).delegate; node6 != null; node6 = node6.child) {
                                    if ((node6.kindSet & 1024) != 0) {
                                        i2++;
                                        if (i2 == 1) {
                                            node5 = node6;
                                        } else {
                                            if (mutableVector2 == null) {
                                                mutableVector2 = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (node5 != null) {
                                                mutableVector2.add(node5);
                                                node5 = null;
                                            }
                                            mutableVector2.add(node6);
                                        }
                                    }
                                }
                                if (i2 == 1) {
                                }
                            }
                            node5 = DelegatableNodeKt.access$pop(mutableVector2);
                        }
                    }
                }
            }
            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector, node3);
        }
    }

    public static final boolean initializeFocusState$isInActiveSubTree(FocusTargetNode focusTargetNode) {
        NodeChain nodeChain;
        Modifier.Node node = focusTargetNode.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        Modifier.Node node2 = node.parent;
        LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(focusTargetNode);
        while (requireLayoutNode != null) {
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 1024) != 0) {
                while (node2 != null) {
                    if ((node2.kindSet & 1024) != 0) {
                        Modifier.Node node3 = node2;
                        MutableVector mutableVector = null;
                        while (node3 != null) {
                            if (node3 instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) node3;
                                if (focusTargetNode2.committedFocusState != null) {
                                    int ordinal = focusTargetNode2.getFocusState().ordinal();
                                    if (ordinal == 0) {
                                        return false;
                                    }
                                    if (ordinal == 1) {
                                        return true;
                                    }
                                    if (ordinal == 2 || ordinal == 3) {
                                        return false;
                                    }
                                    throw new NoWhenBranchMatchedException();
                                }
                            } else if ((node3.kindSet & 1024) != 0 && (node3 instanceof DelegatingNode)) {
                                int i = 0;
                                for (Modifier.Node node4 = ((DelegatingNode) node3).delegate; node4 != null; node4 = node4.child) {
                                    if ((node4.kindSet & 1024) != 0) {
                                        i++;
                                        if (i == 1) {
                                            node3 = node4;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (node3 != null) {
                                                mutableVector.add(node3);
                                                node3 = null;
                                            }
                                            mutableVector.add(node4);
                                        }
                                    }
                                }
                                if (i == 1) {
                                }
                            }
                            node3 = DelegatableNodeKt.access$pop(mutableVector);
                        }
                    }
                    node2 = node2.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node2 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [androidx.compose.runtime.collection.MutableVector] */
    public final void dispatchFocusCallbacks$ui_release() {
        NodeChain nodeChain;
        Function2 function2;
        FocusStateImpl focusStateImpl = this.committedFocusState;
        if (focusStateImpl == null) {
            focusStateImpl = FocusStateImpl.Inactive;
        }
        FocusStateImpl focusState = getFocusState();
        if (focusStateImpl != focusState && (function2 = this.onFocusChange) != null) {
            function2.invoke(focusStateImpl, focusState);
        }
        Modifier.Node node = this.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(this);
        Modifier.Node node2 = node;
        while (requireLayoutNode != null) {
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 5120) != 0) {
                while (node2 != null) {
                    int i = node2.kindSet;
                    if ((i & 5120) != 0) {
                        if (node2 != node && (i & 1024) != 0) {
                            return;
                        }
                        if ((i & 4096) != 0) {
                            DelegatingNode delegatingNode = node2;
                            ?? r4 = 0;
                            while (delegatingNode != 0) {
                                if (delegatingNode instanceof FocusEventModifierNode) {
                                    FocusEventModifierNode focusEventModifierNode = (FocusEventModifierNode) delegatingNode;
                                    focusEventModifierNode.onFocusEvent(FocusEventModifierNodeKt.getFocusState(focusEventModifierNode));
                                } else if ((delegatingNode.kindSet & 4096) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                    Modifier.Node node3 = delegatingNode.delegate;
                                    int i2 = 0;
                                    delegatingNode = delegatingNode;
                                    r4 = r4;
                                    while (node3 != null) {
                                        if ((node3.kindSet & 4096) != 0) {
                                            i2++;
                                            r4 = r4;
                                            if (i2 == 1) {
                                                delegatingNode = node3;
                                            } else {
                                                if (r4 == 0) {
                                                    r4 = new MutableVector(new Modifier.Node[16]);
                                                }
                                                if (delegatingNode != 0) {
                                                    r4.add(delegatingNode);
                                                    delegatingNode = 0;
                                                }
                                                r4.add(node3);
                                            }
                                        }
                                        node3 = node3.child;
                                        delegatingNode = delegatingNode;
                                        r4 = r4;
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                delegatingNode = DelegatableNodeKt.access$pop(r4);
                            }
                        }
                    }
                    node2 = node2.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node2 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v10, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [androidx.compose.runtime.collection.MutableVector] */
    public final FocusPropertiesImpl fetchFocusProperties$ui_release() {
        boolean z;
        NodeChain nodeChain;
        FocusPropertiesImpl focusPropertiesImpl = new FocusPropertiesImpl();
        focusPropertiesImpl.canFocus = true;
        FocusRequester focusRequester = FocusRequester.Default;
        focusPropertiesImpl.next = focusRequester;
        focusPropertiesImpl.previous = focusRequester;
        focusPropertiesImpl.up = focusRequester;
        focusPropertiesImpl.down = focusRequester;
        focusPropertiesImpl.left = focusRequester;
        focusPropertiesImpl.right = focusRequester;
        focusPropertiesImpl.start = focusRequester;
        focusPropertiesImpl.end = focusRequester;
        focusPropertiesImpl.enter = new Function1() { // from class: androidx.compose.ui.focus.FocusPropertiesImpl$enter$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int i = ((FocusDirection) obj).value;
                return FocusRequester.Default;
            }
        };
        focusPropertiesImpl.exit = new Function1() { // from class: androidx.compose.ui.focus.FocusPropertiesImpl$exit$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                int i = ((FocusDirection) obj).value;
                return FocusRequester.Default;
            }
        };
        int i = this.focusability;
        if (i == 1) {
            z = true;
        } else if (i == 0) {
            z = !(((InputMode) ((SnapshotMutableStateImpl) ((InputModeManagerImpl) ((InputModeManager) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.LocalInputModeManager))).inputMode$delegate).getValue()).value == 1);
        } else {
            if (i != 2) {
                throw new IllegalStateException("Unknown Focusability");
            }
            z = false;
        }
        focusPropertiesImpl.canFocus = z;
        Modifier.Node node = this.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(this);
        Modifier.Node node2 = node;
        loop0: while (requireLayoutNode != null) {
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 3072) != 0) {
                while (node2 != null) {
                    int i2 = node2.kindSet;
                    if ((i2 & 3072) != 0) {
                        if (node2 != node && (i2 & 1024) != 0) {
                            break loop0;
                        }
                        if ((i2 & 2048) != 0) {
                            DelegatingNode delegatingNode = node2;
                            ?? r7 = 0;
                            while (delegatingNode != 0) {
                                if (delegatingNode instanceof FocusPropertiesModifierNode) {
                                    ((FocusPropertiesModifierNode) delegatingNode).applyFocusProperties(focusPropertiesImpl);
                                } else if ((delegatingNode.kindSet & 2048) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                    Modifier.Node node3 = delegatingNode.delegate;
                                    int i3 = 0;
                                    delegatingNode = delegatingNode;
                                    r7 = r7;
                                    while (node3 != null) {
                                        if ((node3.kindSet & 2048) != 0) {
                                            i3++;
                                            r7 = r7;
                                            if (i3 == 1) {
                                                delegatingNode = node3;
                                            } else {
                                                if (r7 == 0) {
                                                    r7 = new MutableVector(new Modifier.Node[16]);
                                                }
                                                if (delegatingNode != 0) {
                                                    r7.add(delegatingNode);
                                                    delegatingNode = 0;
                                                }
                                                r7.add(node3);
                                            }
                                        }
                                        node3 = node3.child;
                                        delegatingNode = delegatingNode;
                                        r7 = r7;
                                    }
                                    if (i3 == 1) {
                                    }
                                }
                                delegatingNode = DelegatableNodeKt.access$pop(r7);
                            }
                        }
                    }
                    node2 = node2.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node2 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
        }
        return focusPropertiesImpl;
    }

    public final FocusStateImpl getFocusState() {
        FocusStateImpl focusStateImpl;
        LayoutNode layoutNode;
        AndroidComposeView androidComposeView;
        FocusOwnerImpl focusOwnerImpl;
        NodeCoordinator nodeCoordinator = this.node.coordinator;
        FocusTransactionManager focusTransactionManager = (nodeCoordinator == null || (layoutNode = nodeCoordinator.layoutNode) == null || (androidComposeView = layoutNode.owner) == null || (focusOwnerImpl = androidComposeView.focusOwner) == null) ? null : focusOwnerImpl.focusTransactionManager;
        if (focusTransactionManager != null && (focusStateImpl = (FocusStateImpl) focusTransactionManager.states.get(this)) != null) {
            return focusStateImpl;
        }
        FocusStateImpl focusStateImpl2 = this.committedFocusState;
        return focusStateImpl2 == null ? FocusStateImpl.Inactive : focusStateImpl2;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final boolean getShouldAutoInvalidate() {
        return false;
    }

    public final void initializeFocusState$ui_release(FocusStateImpl focusStateImpl) {
        if (this.committedFocusState != null) {
            throw new IllegalStateException("Re-initializing focus target node.");
        }
        FocusTransactionManager requireTransactionManager = FocusTargetNodeKt.requireTransactionManager(this);
        try {
            if (requireTransactionManager.ongoingTransaction) {
                FocusTransactionManager.access$cancelTransaction(requireTransactionManager);
            }
            requireTransactionManager.ongoingTransaction = true;
            if (focusStateImpl == null) {
                focusStateImpl = (initializeFocusState$isInActiveSubTree(this) && initializeFocusState$hasActiveChild(this)) ? FocusStateImpl.ActiveParent : FocusStateImpl.Inactive;
            }
            setFocusState(focusStateImpl);
            FocusTransactionManager.access$commitTransaction(requireTransactionManager);
        } catch (Throwable th) {
            FocusTransactionManager.access$commitTransaction(requireTransactionManager);
            throw th;
        }
    }

    public final void invalidateFocus$ui_release() {
        if (!(this.committedFocusState != null)) {
            initializeFocusState$ui_release(null);
        }
        int ordinal = getFocusState().ordinal();
        if (ordinal == 0 || ordinal == 2) {
            final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.ui.focus.FocusTargetNode$invalidateFocus$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Ref$ObjectRef.this.element = this.fetchFocusProperties$ui_release();
                    return Unit.INSTANCE;
                }
            });
            Object obj = ref$ObjectRef.element;
            if ((obj != null ? (FocusProperties) obj : null).getCanFocus()) {
                return;
            }
            ((AndroidComposeView) DelegatableNodeKt.requireOwner(this)).focusOwner.m288clearFocusI7lrPNg(8, true, true);
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        FocusTargetNodeKt.invalidateFocusTarget(this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
    
        if (r0 != 2) goto L19;
     */
    @Override // androidx.compose.ui.Modifier.Node
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onDetach() {
        /*
            r4 = this;
            androidx.compose.ui.focus.FocusStateImpl r0 = r4.getFocusState()
            int r0 = r0.ordinal()
            r1 = 1
            if (r0 == 0) goto L2e
            if (r0 == r1) goto L11
            r2 = 2
            if (r0 == r2) goto L2e
            goto L3f
        L11:
            androidx.compose.ui.focus.FocusTransactionManager r0 = androidx.compose.ui.focus.FocusTargetNodeKt.requireTransactionManager(r4)
            boolean r2 = r0.ongoingTransaction     // Catch: java.lang.Throwable -> L1d
            if (r2 == 0) goto L1f
            androidx.compose.ui.focus.FocusTransactionManager.access$cancelTransaction(r0)     // Catch: java.lang.Throwable -> L1d
            goto L1f
        L1d:
            r4 = move-exception
            goto L2a
        L1f:
            r0.ongoingTransaction = r1     // Catch: java.lang.Throwable -> L1d
            androidx.compose.ui.focus.FocusStateImpl r1 = androidx.compose.ui.focus.FocusStateImpl.Inactive     // Catch: java.lang.Throwable -> L1d
            r4.setFocusState(r1)     // Catch: java.lang.Throwable -> L1d
            androidx.compose.ui.focus.FocusTransactionManager.access$commitTransaction(r0)
            goto L3f
        L2a:
            androidx.compose.ui.focus.FocusTransactionManager.access$commitTransaction(r0)
            throw r4
        L2e:
            androidx.compose.ui.node.Owner r0 = androidx.compose.ui.node.DelegatableNodeKt.requireOwner(r4)
            androidx.compose.ui.platform.AndroidComposeView r0 = (androidx.compose.ui.platform.AndroidComposeView) r0
            androidx.compose.ui.focus.FocusOwnerImpl r0 = r0.focusOwner
            r2 = 8
            r3 = 0
            r0.m288clearFocusI7lrPNg(r2, r1, r3)
            androidx.compose.ui.focus.FocusTargetNodeKt.invalidateFocusTarget(r4)
        L3f:
            r0 = 0
            r4.committedFocusState = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusTargetNode.onDetach():void");
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public final void onObservedReadsChanged() {
        FocusStateImpl focusState = getFocusState();
        invalidateFocus$ui_release();
        if (focusState != getFocusState()) {
            dispatchFocusCallbacks$ui_release();
        }
    }

    public final void setFocusState(FocusStateImpl focusStateImpl) {
        FocusTargetNodeKt.requireTransactionManager(this).states.set(this, focusStateImpl);
    }

    public FocusTargetNode(int i, Function2 function2) {
        this.onFocusChange = function2;
        this.focusability = i;
    }
}

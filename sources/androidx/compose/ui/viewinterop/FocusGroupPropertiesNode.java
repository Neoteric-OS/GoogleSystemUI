package androidx.compose.ui.viewinterop;

import android.view.View;
import android.view.ViewTreeObserver;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.focus.FocusTransactionManager;
import androidx.compose.ui.focus.FocusTransactionsKt;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FocusGroupPropertiesNode extends Modifier.Node implements FocusPropertiesModifierNode, ViewTreeObserver.OnGlobalFocusChangeListener, View.OnAttachStateChangeListener {
    public View focusedChild;

    @Override // androidx.compose.ui.focus.FocusPropertiesModifierNode
    public final void applyFocusProperties(FocusProperties focusProperties) {
        focusProperties.setCanFocus(false);
        focusProperties.setEnter(new FocusGroupPropertiesNode$applyFocusProperties$1(1, this, FocusGroupPropertiesNode.class, "onEnter", "onEnter-3ESFkO8(I)Landroidx/compose/ui/focus/FocusRequester;", 0));
        focusProperties.setExit(new FocusGroupPropertiesNode$applyFocusProperties$2(1, this, FocusGroupPropertiesNode.class, "onExit", "onExit-3ESFkO8(I)Landroidx/compose/ui/focus/FocusRequester;", 0));
    }

    public final FocusTargetNode getFocusTargetOfEmbeddedViewWrapper() {
        if (!this.node.isAttached) {
            InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
        }
        Modifier.Node node = this.node;
        if ((node.aggregateChildKindSet & 1024) != 0) {
            boolean z = false;
            for (Modifier.Node node2 = node.child; node2 != null; node2 = node2.child) {
                if ((node2.kindSet & 1024) != 0) {
                    Modifier.Node node3 = node2;
                    MutableVector mutableVector = null;
                    while (node3 != null) {
                        if (node3 instanceof FocusTargetNode) {
                            FocusTargetNode focusTargetNode = (FocusTargetNode) node3;
                            if (z) {
                                return focusTargetNode;
                            }
                            z = true;
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
            }
        }
        throw new IllegalStateException("Could not find focus target of embedded view wrapper");
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        FocusGroupNode_androidKt.access$getView(this).addOnAttachStateChangeListener(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        FocusGroupNode_androidKt.access$getView(this).removeOnAttachStateChangeListener(this);
        this.focusedChild = null;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
    public final void onGlobalFocusChanged(View view, View view2) {
        if (DelegatableNodeKt.requireLayoutNode(this).owner == null) {
            return;
        }
        View access$getView = FocusGroupNode_androidKt.access$getView(this);
        FocusOwnerImpl focusOwnerImpl = ((AndroidComposeView) DelegatableNodeKt.requireOwner(this)).focusOwner;
        Owner requireOwner = DelegatableNodeKt.requireOwner(this);
        boolean z = (view == null || view.equals(requireOwner) || !FocusGroupNode_androidKt.access$containsDescendant(access$getView, view)) ? false : true;
        boolean z2 = (view2 == null || view2.equals(requireOwner) || !FocusGroupNode_androidKt.access$containsDescendant(access$getView, view2)) ? false : true;
        if (z && z2) {
            this.focusedChild = view2;
            return;
        }
        if (!z2) {
            if (!z) {
                this.focusedChild = null;
                return;
            }
            this.focusedChild = null;
            if (getFocusTargetOfEmbeddedViewWrapper().getFocusState().isFocused()) {
                focusOwnerImpl.m288clearFocusI7lrPNg(8, false, false);
                return;
            }
            return;
        }
        this.focusedChild = view2;
        FocusTargetNode focusTargetOfEmbeddedViewWrapper = getFocusTargetOfEmbeddedViewWrapper();
        if (focusTargetOfEmbeddedViewWrapper.getFocusState().getHasFocus()) {
            return;
        }
        FocusTransactionManager focusTransactionManager = focusOwnerImpl.focusTransactionManager;
        try {
            if (focusTransactionManager.ongoingTransaction) {
                FocusTransactionManager.access$cancelTransaction(focusTransactionManager);
            }
            focusTransactionManager.ongoingTransaction = true;
            FocusTransactionsKt.performRequestFocus(focusTargetOfEmbeddedViewWrapper);
            FocusTransactionManager.access$commitTransaction(focusTransactionManager);
        } catch (Throwable th) {
            FocusTransactionManager.access$commitTransaction(focusTransactionManager);
            throw th;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        view.getViewTreeObserver().addOnGlobalFocusChangeListener(this);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        view.getViewTreeObserver().removeOnGlobalFocusChangeListener(this);
    }
}

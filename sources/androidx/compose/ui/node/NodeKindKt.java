package androidx.compose.ui.node;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusInvalidationManager;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.focus.FocusTargetNodeKt;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.key.SoftKeyboardInterceptionModifierNode;
import androidx.compose.ui.input.pointer.PointerInputModifier;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.ApproachLayoutModifierNode;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.OnGloballyPositionedModifier;
import androidx.compose.ui.layout.ParentDataModifier;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.semantics.SemanticsModifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NodeKindKt {
    public static final MutableObjectIntMap classToKindSetMap;

    static {
        MutableObjectIntMap mutableObjectIntMap = ObjectIntMapKt.EmptyObjectIntMap;
        classToKindSetMap = new MutableObjectIntMap();
    }

    public static final void autoInvalidateNodeIncludingDelegates(Modifier.Node node, int i, int i2) {
        if (!(node instanceof DelegatingNode)) {
            autoInvalidateNodeSelf(node, i & node.kindSet, i2);
            return;
        }
        DelegatingNode delegatingNode = (DelegatingNode) node;
        autoInvalidateNodeSelf(node, delegatingNode.selfKindSet & i, i2);
        int i3 = (~delegatingNode.selfKindSet) & i;
        for (Modifier.Node node2 = delegatingNode.delegate; node2 != null; node2 = node2.child) {
            autoInvalidateNodeIncludingDelegates(node2, i3, i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final void autoInvalidateNodeSelf(Modifier.Node node, int i, int i2) {
        if (i2 != 0 || node.getShouldAutoInvalidate()) {
            if ((i & 2) != 0 && (node instanceof LayoutModifierNode)) {
                LayoutModifierNodeKt.invalidateMeasurement((LayoutModifierNode) node);
                if (i2 == 2) {
                    NodeCoordinator m503requireCoordinator64DMado = DelegatableNodeKt.m503requireCoordinator64DMado(node, 2);
                    m503requireCoordinator64DMado.released = true;
                    ((NodeCoordinator$invalidateParentLayer$1) m503requireCoordinator64DMado.invalidateParentLayer).invoke();
                    if (m503requireCoordinator64DMado.layer != null) {
                        if (m503requireCoordinator64DMado.explicitLayer != null) {
                            m503requireCoordinator64DMado.explicitLayer = null;
                        }
                        m503requireCoordinator64DMado.updateLayerBlock(null, false);
                        m503requireCoordinator64DMado.layoutNode.requestRelayout$ui_release(false);
                    }
                }
            }
            if ((i & 128) != 0 && (node instanceof LayoutAwareModifierNode) && i2 != 2) {
                DelegatableNodeKt.requireLayoutNode(node).invalidateMeasurements$ui_release();
            }
            if ((i & 256) != 0 && (node instanceof GlobalPositionAwareModifierNode) && i2 != 2) {
                LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(node);
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = requireLayoutNode.layoutDelegate;
                if (!layoutNodeLayoutDelegate.layoutPending && !layoutNodeLayoutDelegate.measurePending && !requireLayoutNode.needsOnPositionedDispatch) {
                    AndroidComposeView androidComposeView = (AndroidComposeView) LayoutNodeKt.requireOwner(requireLayoutNode);
                    androidComposeView.measureAndLayoutDelegate.onPositionedDispatcher.layoutNodes.add(requireLayoutNode);
                    requireLayoutNode.needsOnPositionedDispatch = true;
                    androidComposeView.scheduleMeasureAndLayout(null);
                }
            }
            if ((i & 4) != 0 && (node instanceof DrawModifierNode)) {
                DrawModifierNodeKt.invalidateDraw((DrawModifierNode) node);
            }
            if ((i & 8) != 0 && (node instanceof SemanticsModifierNode)) {
                SemanticsModifierNodeKt.invalidateSemantics((SemanticsModifierNode) node);
            }
            if ((i & 64) != 0 && (node instanceof ParentDataModifierNode)) {
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = DelegatableNodeKt.requireLayoutNode((ParentDataModifierNode) node).layoutDelegate;
                layoutNodeLayoutDelegate2.measurePassDelegate.parentDataDirty = true;
                LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate2.lookaheadPassDelegate;
                if (lookaheadPassDelegate != null) {
                    lookaheadPassDelegate.parentDataDirty = true;
                }
            }
            if ((i & 2048) != 0 && (node instanceof FocusPropertiesModifierNode)) {
                FocusPropertiesModifierNode focusPropertiesModifierNode = (FocusPropertiesModifierNode) node;
                CanFocusChecker.canFocusValue = null;
                focusPropertiesModifierNode.applyFocusProperties(CanFocusChecker.INSTANCE);
                if (CanFocusChecker.canFocusValue != null) {
                    if (i2 == 2) {
                        Modifier.Node node2 = ((Modifier.Node) focusPropertiesModifierNode).node;
                        if (!node2.isAttached) {
                            throw new IllegalStateException("visitChildren called on an unattached node");
                        }
                        MutableVector mutableVector = new MutableVector(new Modifier.Node[16]);
                        Modifier.Node node3 = node2.child;
                        if (node3 == null) {
                            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector, node2);
                        } else {
                            mutableVector.add(node3);
                        }
                        while (true) {
                            int i3 = mutableVector.size;
                            if (i3 == 0) {
                                break;
                            }
                            Modifier.Node node4 = (Modifier.Node) mutableVector.removeAt(i3 - 1);
                            if ((node4.aggregateChildKindSet & 1024) == 0) {
                                DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector, node4);
                            } else {
                                while (true) {
                                    if (node4 == null) {
                                        break;
                                    }
                                    if ((node4.kindSet & 1024) != 0) {
                                        MutableVector mutableVector2 = null;
                                        while (node4 != null) {
                                            if (node4 instanceof FocusTargetNode) {
                                                FocusTargetNodeKt.invalidateFocusTarget((FocusTargetNode) node4);
                                            } else if ((node4.kindSet & 1024) != 0 && (node4 instanceof DelegatingNode)) {
                                                int i4 = 0;
                                                for (Modifier.Node node5 = ((DelegatingNode) node4).delegate; node5 != null; node5 = node5.child) {
                                                    if ((node5.kindSet & 1024) != 0) {
                                                        i4++;
                                                        if (i4 == 1) {
                                                            node4 = node5;
                                                        } else {
                                                            if (mutableVector2 == null) {
                                                                mutableVector2 = new MutableVector(new Modifier.Node[16]);
                                                            }
                                                            if (node4 != null) {
                                                                mutableVector2.add(node4);
                                                                node4 = null;
                                                            }
                                                            mutableVector2.add(node5);
                                                        }
                                                    }
                                                }
                                                if (i4 == 1) {
                                                }
                                            }
                                            node4 = DelegatableNodeKt.access$pop(mutableVector2);
                                        }
                                    } else {
                                        node4 = node4.child;
                                    }
                                }
                            }
                        }
                    } else {
                        FocusInvalidationManager focusInvalidationManager = ((AndroidComposeView) DelegatableNodeKt.requireOwner(focusPropertiesModifierNode)).focusOwner.focusInvalidationManager;
                        focusInvalidationManager.scheduleInvalidation(focusInvalidationManager.focusPropertiesNodes, focusPropertiesModifierNode);
                    }
                }
            }
            if ((i & 4096) == 0 || !(node instanceof FocusEventModifierNode)) {
                return;
            }
            FocusEventModifierNode focusEventModifierNode = (FocusEventModifierNode) node;
            FocusInvalidationManager focusInvalidationManager2 = ((AndroidComposeView) DelegatableNodeKt.requireOwner(focusEventModifierNode)).focusOwner.focusInvalidationManager;
            focusInvalidationManager2.scheduleInvalidation(focusInvalidationManager2.focusEventNodes, focusEventModifierNode);
        }
    }

    public static final void autoInvalidateUpdatedNode(Modifier.Node node) {
        if (!node.isAttached) {
            InlineClassHelperKt.throwIllegalStateException("autoInvalidateUpdatedNode called on unattached node");
        }
        autoInvalidateNodeIncludingDelegates(node, -1, 0);
    }

    public static final int calculateNodeKindSetFrom(Modifier.Element element) {
        int i = element instanceof LayoutModifier ? 3 : 1;
        if (element instanceof DrawModifier) {
            i |= 4;
        }
        if (element instanceof SemanticsModifier) {
            i |= 8;
        }
        if (element instanceof PointerInputModifier) {
            i |= 16;
        }
        if ((element instanceof ModifierLocalConsumer) || (element instanceof ModifierLocalProvider)) {
            i |= 32;
        }
        if (element instanceof OnGloballyPositionedModifier) {
            i |= 256;
        }
        return element instanceof ParentDataModifier ? i | 64 : i;
    }

    public static final int calculateNodeKindSetFromIncludingDelegates(Modifier.Node node) {
        if (!(node instanceof DelegatingNode)) {
            return calculateNodeKindSetFrom(node);
        }
        DelegatingNode delegatingNode = (DelegatingNode) node;
        int i = delegatingNode.selfKindSet;
        for (Modifier.Node node2 = delegatingNode.delegate; node2 != null; node2 = node2.child) {
            i |= calculateNodeKindSetFromIncludingDelegates(node2);
        }
        return i;
    }

    /* renamed from: getIncludeSelfInTraversal-H91voCI, reason: not valid java name */
    public static final boolean m542getIncludeSelfInTraversalH91voCI(int i) {
        return (i & 128) != 0;
    }

    public static final int calculateNodeKindSetFrom(Modifier.Node node) {
        int i = node.kindSet;
        if (i != 0) {
            return i;
        }
        Class<?> cls = node.getClass();
        MutableObjectIntMap mutableObjectIntMap = classToKindSetMap;
        int findKeyIndex = mutableObjectIntMap.findKeyIndex(cls);
        if (findKeyIndex >= 0) {
            return mutableObjectIntMap.values[findKeyIndex];
        }
        int i2 = node instanceof LayoutModifierNode ? 3 : 1;
        if (node instanceof DrawModifierNode) {
            i2 |= 4;
        }
        if (node instanceof SemanticsModifierNode) {
            i2 |= 8;
        }
        if (node instanceof PointerInputModifierNode) {
            i2 |= 16;
        }
        if (node instanceof ModifierLocalModifierNode) {
            i2 |= 32;
        }
        if (node instanceof ParentDataModifierNode) {
            i2 |= 64;
        }
        if (node instanceof LayoutAwareModifierNode) {
            i2 |= 128;
        }
        if (node instanceof GlobalPositionAwareModifierNode) {
            i2 |= 256;
        }
        if (node instanceof ApproachLayoutModifierNode) {
            i2 |= 512;
        }
        if (node instanceof FocusTargetNode) {
            i2 |= 1024;
        }
        if (node instanceof FocusPropertiesModifierNode) {
            i2 |= 2048;
        }
        if (node instanceof FocusEventModifierNode) {
            i2 |= 4096;
        }
        if (node instanceof KeyInputModifierNode) {
            i2 |= 8192;
        }
        if (node instanceof RotaryInputModifierNode) {
            i2 |= 16384;
        }
        if (node instanceof CompositionLocalConsumerModifierNode) {
            i2 |= 32768;
        }
        if (node instanceof SoftKeyboardInterceptionModifierNode) {
            i2 |= 131072;
        }
        int i3 = node instanceof TraversableNode ? 262144 | i2 : i2;
        mutableObjectIntMap.set(i3, cls);
        return i3;
    }
}

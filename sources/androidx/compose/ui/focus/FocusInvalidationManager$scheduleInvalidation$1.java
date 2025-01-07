package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import java.util.ArrayList;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.PropertyReference0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class FocusInvalidationManager$scheduleInvalidation$1 extends FunctionReferenceImpl implements Function0 {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        ArrayList arrayList;
        int i;
        char c;
        ArrayList arrayList2;
        ArrayList arrayList3;
        FocusInvalidationManager focusInvalidationManager = (FocusInvalidationManager) this.receiver;
        boolean hasFocus = ((FocusStateImpl) ((FocusState) ((PropertyReference0) focusInvalidationManager.rootFocusStateFetcher).get())).getHasFocus();
        FocusStateImpl focusStateImpl = FocusStateImpl.Inactive;
        Function0 function0 = focusInvalidationManager.invalidateOwnerFocusState;
        if (hasFocus) {
            ArrayList arrayList4 = (ArrayList) focusInvalidationManager.focusPropertiesNodes;
            int size = arrayList4.size();
            int i2 = 0;
            while (true) {
                int i3 = 1;
                char c2 = 16;
                if (i2 < size) {
                    Modifier.Node node = (Modifier.Node) ((FocusPropertiesModifierNode) arrayList4.get(i2));
                    Modifier.Node node2 = node.node;
                    if (node2.isAttached) {
                        MutableVector mutableVector = null;
                        while (node2 != null) {
                            if (node2 instanceof FocusTargetNode) {
                                focusInvalidationManager.focusTargetNodes.add((FocusTargetNode) node2);
                            } else if ((node2.kindSet & 1024) != 0 && (node2 instanceof DelegatingNode)) {
                                int i4 = 0;
                                for (Modifier.Node node3 = ((DelegatingNode) node2).delegate; node3 != null; node3 = node3.child) {
                                    if ((node3.kindSet & 1024) != 0) {
                                        i4++;
                                        if (i4 == 1) {
                                            node2 = node3;
                                        } else {
                                            if (mutableVector == null) {
                                                mutableVector = new MutableVector(new Modifier.Node[16]);
                                            }
                                            if (node2 != null) {
                                                mutableVector.add(node2);
                                                node2 = null;
                                            }
                                            mutableVector.add(node3);
                                        }
                                    }
                                }
                                if (i4 == 1) {
                                }
                            }
                            node2 = DelegatableNodeKt.access$pop(mutableVector);
                        }
                        Modifier.Node node4 = node.node;
                        if (!node4.isAttached) {
                            throw new IllegalStateException("visitChildren called on an unattached node");
                        }
                        MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16]);
                        Modifier.Node node5 = node4.child;
                        if (node5 == null) {
                            DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node4);
                        } else {
                            mutableVector2.add(node5);
                        }
                        while (true) {
                            int i5 = mutableVector2.size;
                            if (i5 != 0) {
                                Modifier.Node node6 = (Modifier.Node) mutableVector2.removeAt(i5 - 1);
                                if ((node6.aggregateChildKindSet & 1024) == 0) {
                                    DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector2, node6);
                                } else {
                                    while (true) {
                                        if (node6 == null) {
                                            break;
                                        }
                                        if ((node6.kindSet & 1024) != 0) {
                                            MutableVector mutableVector3 = null;
                                            while (node6 != null) {
                                                if (node6 instanceof FocusTargetNode) {
                                                    focusInvalidationManager.focusTargetNodes.add((FocusTargetNode) node6);
                                                } else if ((node6.kindSet & 1024) != 0 && (node6 instanceof DelegatingNode)) {
                                                    int i6 = 0;
                                                    for (Modifier.Node node7 = ((DelegatingNode) node6).delegate; node7 != null; node7 = node7.child) {
                                                        if ((node7.kindSet & 1024) != 0) {
                                                            i6++;
                                                            if (i6 == 1) {
                                                                node6 = node7;
                                                            } else {
                                                                if (mutableVector3 == null) {
                                                                    mutableVector3 = new MutableVector(new Modifier.Node[16]);
                                                                }
                                                                if (node6 != null) {
                                                                    mutableVector3.add(node6);
                                                                    node6 = null;
                                                                }
                                                                mutableVector3.add(node7);
                                                            }
                                                        }
                                                    }
                                                    if (i6 == 1) {
                                                    }
                                                }
                                                node6 = DelegatableNodeKt.access$pop(mutableVector3);
                                            }
                                        } else {
                                            node6 = node6.child;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    i2++;
                } else {
                    focusInvalidationManager.focusPropertiesNodes.clear();
                    ArrayList arrayList5 = (ArrayList) focusInvalidationManager.focusEventNodes;
                    int size2 = arrayList5.size();
                    int i7 = 0;
                    while (i7 < size2) {
                        FocusEventModifierNode focusEventModifierNode = (FocusEventModifierNode) arrayList5.get(i7);
                        Modifier.Node node8 = (Modifier.Node) focusEventModifierNode;
                        Modifier.Node node9 = node8.node;
                        if (node9.isAttached) {
                            int i8 = i3;
                            FocusTargetNode focusTargetNode = null;
                            MutableVector mutableVector4 = null;
                            int i9 = 0;
                            while (node9 != null) {
                                if (node9 instanceof FocusTargetNode) {
                                    FocusTargetNode focusTargetNode2 = (FocusTargetNode) node9;
                                    if (focusTargetNode != null) {
                                        i9 = i3;
                                    }
                                    if (focusInvalidationManager.focusTargetNodes.contains(focusTargetNode2)) {
                                        focusInvalidationManager.focusTargetsWithInvalidatedFocusEvents.add(focusTargetNode2);
                                        i8 = 0;
                                    }
                                    arrayList2 = arrayList5;
                                    focusTargetNode = focusTargetNode2;
                                } else if ((node9.kindSet & 1024) == 0 || !(node9 instanceof DelegatingNode)) {
                                    arrayList2 = arrayList5;
                                } else {
                                    Modifier.Node node10 = ((DelegatingNode) node9).delegate;
                                    int i10 = 0;
                                    while (node10 != null) {
                                        if ((node10.kindSet & 1024) != 0) {
                                            i10++;
                                            if (i10 == 1) {
                                                arrayList3 = arrayList5;
                                                node9 = node10;
                                            } else {
                                                if (mutableVector4 == null) {
                                                    arrayList3 = arrayList5;
                                                    mutableVector4 = new MutableVector(new Modifier.Node[16]);
                                                } else {
                                                    arrayList3 = arrayList5;
                                                }
                                                if (node9 != null) {
                                                    mutableVector4.add(node9);
                                                    node9 = null;
                                                }
                                                mutableVector4.add(node10);
                                            }
                                        } else {
                                            arrayList3 = arrayList5;
                                        }
                                        node10 = node10.child;
                                        arrayList5 = arrayList3;
                                        i3 = 1;
                                    }
                                    arrayList2 = arrayList5;
                                    int i11 = i3;
                                    if (i10 == i11) {
                                        i3 = i11;
                                        arrayList5 = arrayList2;
                                    }
                                }
                                node9 = DelegatableNodeKt.access$pop(mutableVector4);
                                arrayList5 = arrayList2;
                                i3 = 1;
                            }
                            arrayList = arrayList5;
                            Modifier.Node node11 = node8.node;
                            if (!node11.isAttached) {
                                throw new IllegalStateException("visitChildren called on an unattached node");
                            }
                            MutableVector mutableVector5 = new MutableVector(new Modifier.Node[16]);
                            Modifier.Node node12 = node11.child;
                            if (node12 == null) {
                                DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector5, node11);
                            } else {
                                mutableVector5.add(node12);
                            }
                            while (true) {
                                int i12 = mutableVector5.size;
                                if (i12 == 0) {
                                    break;
                                }
                                Modifier.Node node13 = (Modifier.Node) mutableVector5.removeAt(i12 - 1);
                                if ((node13.aggregateChildKindSet & 1024) == 0) {
                                    DelegatableNodeKt.access$addLayoutNodeChildren(mutableVector5, node13);
                                } else {
                                    while (node13 != null) {
                                        if ((node13.kindSet & 1024) != 0) {
                                            MutableVector mutableVector6 = null;
                                            while (node13 != null) {
                                                if (node13 instanceof FocusTargetNode) {
                                                    FocusTargetNode focusTargetNode3 = (FocusTargetNode) node13;
                                                    if (focusTargetNode != null) {
                                                        i9 = 1;
                                                    }
                                                    if (focusInvalidationManager.focusTargetNodes.contains(focusTargetNode3)) {
                                                        focusInvalidationManager.focusTargetsWithInvalidatedFocusEvents.add(focusTargetNode3);
                                                        i8 = 0;
                                                    }
                                                    focusTargetNode = focusTargetNode3;
                                                } else if ((node13.kindSet & 1024) != 0 && (node13 instanceof DelegatingNode)) {
                                                    int i13 = 0;
                                                    for (Modifier.Node node14 = ((DelegatingNode) node13).delegate; node14 != null; node14 = node14.child) {
                                                        if ((node14.kindSet & 1024) != 0) {
                                                            i13++;
                                                            if (i13 == 1) {
                                                                node13 = node14;
                                                            } else {
                                                                if (mutableVector6 == null) {
                                                                    mutableVector6 = new MutableVector(new Modifier.Node[16]);
                                                                }
                                                                if (node13 != null) {
                                                                    mutableVector6.add(node13);
                                                                    node13 = null;
                                                                }
                                                                mutableVector6.add(node14);
                                                            }
                                                        }
                                                    }
                                                    if (i13 != 1) {
                                                        node13 = DelegatableNodeKt.access$pop(mutableVector6);
                                                    }
                                                }
                                                node13 = DelegatableNodeKt.access$pop(mutableVector6);
                                            }
                                        } else {
                                            node13 = node13.child;
                                        }
                                    }
                                }
                            }
                            i = 1;
                            c = 16;
                            if (i8 != 0) {
                                focusEventModifierNode.onFocusEvent(i9 != 0 ? FocusEventModifierNodeKt.getFocusState(focusEventModifierNode) : focusTargetNode != null ? focusTargetNode.getFocusState() : focusStateImpl);
                            }
                        } else {
                            focusEventModifierNode.onFocusEvent(focusStateImpl);
                            arrayList = arrayList5;
                            i = i3;
                            c = c2;
                        }
                        i7++;
                        i3 = i;
                        c2 = c;
                        arrayList5 = arrayList;
                    }
                    focusInvalidationManager.focusEventNodes.clear();
                    ArrayList arrayList6 = (ArrayList) focusInvalidationManager.focusTargetNodes;
                    int size3 = arrayList6.size();
                    for (int i14 = 0; i14 < size3; i14++) {
                        FocusTargetNode focusTargetNode4 = (FocusTargetNode) arrayList6.get(i14);
                        if (focusTargetNode4.isAttached) {
                            FocusStateImpl focusState = focusTargetNode4.getFocusState();
                            focusTargetNode4.invalidateFocus$ui_release();
                            if (focusState != focusTargetNode4.getFocusState() || focusInvalidationManager.focusTargetsWithInvalidatedFocusEvents.contains(focusTargetNode4)) {
                                focusTargetNode4.dispatchFocusCallbacks$ui_release();
                            }
                        }
                    }
                    focusInvalidationManager.focusTargetNodes.clear();
                    focusInvalidationManager.focusTargetsWithInvalidatedFocusEvents.clear();
                    ((FocusOwnerImpl$focusInvalidationManager$1) function0).invoke();
                    if (!focusInvalidationManager.focusPropertiesNodes.isEmpty()) {
                        InlineClassHelperKt.throwIllegalStateException("Unprocessed FocusProperties nodes");
                    }
                    if (!focusInvalidationManager.focusEventNodes.isEmpty()) {
                        InlineClassHelperKt.throwIllegalStateException("Unprocessed FocusEvent nodes");
                    }
                    if (!focusInvalidationManager.focusTargetNodes.isEmpty()) {
                        InlineClassHelperKt.throwIllegalStateException("Unprocessed FocusTarget nodes");
                    }
                }
            }
        } else {
            ArrayList arrayList7 = (ArrayList) focusInvalidationManager.focusEventNodes;
            int size4 = arrayList7.size();
            for (int i15 = 0; i15 < size4; i15++) {
                ((FocusEventModifierNode) arrayList7.get(i15)).onFocusEvent(focusStateImpl);
            }
            ArrayList arrayList8 = (ArrayList) focusInvalidationManager.focusTargetNodes;
            int size5 = arrayList8.size();
            for (int i16 = 0; i16 < size5; i16++) {
                FocusTargetNode focusTargetNode5 = (FocusTargetNode) arrayList8.get(i16);
                if (focusTargetNode5.isAttached && focusTargetNode5.committedFocusState == null) {
                    focusTargetNode5.initializeFocusState$ui_release(focusStateImpl);
                }
            }
            focusInvalidationManager.focusTargetNodes.clear();
            focusInvalidationManager.focusEventNodes.clear();
            focusInvalidationManager.focusPropertiesNodes.clear();
            focusInvalidationManager.focusTargetsWithInvalidatedFocusEvents.clear();
            ((FocusOwnerImpl$focusInvalidationManager$1) function0).invoke();
        }
        return Unit.INSTANCE;
    }
}

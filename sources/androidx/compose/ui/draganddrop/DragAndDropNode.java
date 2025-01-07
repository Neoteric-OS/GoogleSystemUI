package androidx.compose.ui.draganddrop;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.InnerNodeCoordinator;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNode$Companion$TraverseDescendantsAction;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DragAndDropNode extends Modifier.Node implements TraversableNode, DragAndDropModifierNode, LayoutAwareModifierNode, DragAndDropTargetModifierNode, DragAndDropTarget {
    public DragAndDropNode lastChildDragAndDropModifierNode;
    public final Function1 onDropTargetValidate;
    public Function2 onStartTransfer;
    public long size;
    public DragAndDropTarget thisDragAndDropTarget;
    public final Object traverseKey;

    public DragAndDropNode(int i, Function1 function1) {
        function1 = (i & 2) != 0 ? null : function1;
        this.onStartTransfer = null;
        this.onDropTargetValidate = function1;
        this.traverseKey = DragAndDropNode$Companion$DragAndDropTraversableKey.INSTANCE;
        this.size = 0L;
    }

    /* renamed from: drag-12SF9DM, reason: not valid java name */
    public final void m277drag12SF9DM(final DragAndDropTransferData dragAndDropTransferData, final long j, final Function1 function1) {
        if (this.onStartTransfer != null) {
            InlineClassHelperKt.throwIllegalStateException("Check failed.");
        }
        this.onStartTransfer = new Function2() { // from class: androidx.compose.ui.draganddrop.DragAndDropNode$drag$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                long j2 = ((Offset) obj2).packedValue;
                DragAndDropTransferData dragAndDropTransferData2 = DragAndDropTransferData.this;
                long j3 = j;
                AndroidDragAndDropManager$requestDragAndDropTransfer$dragAndDropSourceScope$1 androidDragAndDropManager$requestDragAndDropTransfer$dragAndDropSourceScope$1 = (AndroidDragAndDropManager$requestDragAndDropTransfer$dragAndDropSourceScope$1) ((DragAndDropStartTransferScope) obj);
                androidDragAndDropManager$requestDragAndDropTransfer$dragAndDropSourceScope$1.$isTransferStarted.element = ((Boolean) androidDragAndDropManager$requestDragAndDropTransfer$dragAndDropSourceScope$1.this$0.startDrag.invoke(dragAndDropTransferData2, new Size(j3), function1)).booleanValue();
                return Unit.INSTANCE;
            }
        };
        AndroidDragAndDropManager androidDragAndDropManager = ((AndroidComposeView) DelegatableNodeKt.requireOwner(this)).dragAndDropManager;
        androidDragAndDropManager.getClass();
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        final AndroidDragAndDropManager$requestDragAndDropTransfer$dragAndDropSourceScope$1 androidDragAndDropManager$requestDragAndDropTransfer$dragAndDropSourceScope$1 = new AndroidDragAndDropManager$requestDragAndDropTransfer$dragAndDropSourceScope$1(ref$BooleanRef, androidDragAndDropManager);
        final Function0 function0 = new Function0() { // from class: androidx.compose.ui.draganddrop.AndroidDragAndDropManager$requestDragAndDropTransfer$1$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(Ref$BooleanRef.this.element);
            }
        };
        final InnerNodeCoordinator innerNodeCoordinator = DelegatableNodeKt.requireLayoutNode(this).nodes.innerCoordinator;
        Function1 function12 = new Function1() { // from class: androidx.compose.ui.draganddrop.DragAndDropNode$startDragAndDropTransfer$1
            final /* synthetic */ long $offset = 9205357640488583168L;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                DragAndDropNode dragAndDropNode = (DragAndDropNode) obj;
                if (!dragAndDropNode.isAttached) {
                    return TraversableNode$Companion$TraverseDescendantsAction.SkipSubtreeAndContinueTraversal;
                }
                Function2 function2 = dragAndDropNode.onStartTransfer;
                TraversableNode$Companion$TraverseDescendantsAction traversableNode$Companion$TraverseDescendantsAction = TraversableNode$Companion$TraverseDescendantsAction.ContinueTraversal;
                if (function2 != null) {
                    if (Offset.m310equalsimpl0(this.$offset, 9205357640488583168L)) {
                        function2.invoke(androidDragAndDropManager$requestDragAndDropTransfer$dragAndDropSourceScope$1, new Offset(9205357640488583168L));
                    } else {
                        long mo483localPositionOfS_NoaFU = DelegatableNodeKt.requireLayoutNode(dragAndDropNode).nodes.innerCoordinator.mo483localPositionOfS_NoaFU(LayoutCoordinates.this, this.$offset);
                        if (RectKt.m324Recttz77jQw(0L, IntSizeKt.m685toSizeozmzZPI(dragAndDropNode.size)).m319containsk4lQ0M(mo483localPositionOfS_NoaFU)) {
                            function2.invoke(androidDragAndDropManager$requestDragAndDropTransfer$dragAndDropSourceScope$1, new Offset(mo483localPositionOfS_NoaFU));
                        }
                    }
                    if (((Boolean) function0.invoke()).booleanValue()) {
                        return TraversableNode$Companion$TraverseDescendantsAction.CancelTraversal;
                    }
                }
                return traversableNode$Companion$TraverseDescendantsAction;
            }
        };
        if (function12.invoke(this) == TraversableNode$Companion$TraverseDescendantsAction.ContinueTraversal) {
            TraversableNodeKt.traverseDescendants(this, function12);
        }
        this.onStartTransfer = null;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public final Object getTraverseKey() {
        return this.traverseKey;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        this.thisDragAndDropTarget = null;
        this.lastChildDragAndDropModifierNode = null;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public final boolean onDrop(DragAndDropEvent dragAndDropEvent) {
        DragAndDropNode dragAndDropNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropNode != null) {
            return dragAndDropNode.onDrop(dragAndDropEvent);
        }
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            return dragAndDropTarget.onDrop(dragAndDropEvent);
        }
        return false;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public final void onEnded(final DragAndDropEvent dragAndDropEvent) {
        Function1 function1 = new Function1() { // from class: androidx.compose.ui.draganddrop.DragAndDropNode$onEnded$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                DragAndDropNode dragAndDropNode = (DragAndDropNode) obj;
                if (!dragAndDropNode.node.isAttached) {
                    return TraversableNode$Companion$TraverseDescendantsAction.SkipSubtreeAndContinueTraversal;
                }
                DragAndDropTarget dragAndDropTarget = dragAndDropNode.thisDragAndDropTarget;
                if (dragAndDropTarget != null) {
                    dragAndDropTarget.onEnded(DragAndDropEvent.this);
                }
                dragAndDropNode.thisDragAndDropTarget = null;
                dragAndDropNode.lastChildDragAndDropModifierNode = null;
                return TraversableNode$Companion$TraverseDescendantsAction.ContinueTraversal;
            }
        };
        if (function1.invoke(this) != TraversableNode$Companion$TraverseDescendantsAction.ContinueTraversal) {
            return;
        }
        TraversableNodeKt.traverseDescendants(this, function1);
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public final void onEntered(DragAndDropEvent dragAndDropEvent) {
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            dragAndDropTarget.onEntered(dragAndDropEvent);
            return;
        }
        DragAndDropNode dragAndDropNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropNode != null) {
            dragAndDropNode.onEntered(dragAndDropEvent);
        }
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public final void onExited(DragAndDropEvent dragAndDropEvent) {
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            dragAndDropTarget.onExited(dragAndDropEvent);
        }
        DragAndDropNode dragAndDropNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropNode != null) {
            dragAndDropNode.onExited(dragAndDropEvent);
        }
        this.lastChildDragAndDropModifierNode = null;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public final void onMoved(final DragAndDropEvent dragAndDropEvent) {
        TraversableNode traversableNode;
        DragAndDropNode dragAndDropNode;
        DragAndDropNode dragAndDropNode2 = this.lastChildDragAndDropModifierNode;
        if (dragAndDropNode2 == null || !DragAndDropNodeKt.m278access$containsUv8p0NA(dragAndDropNode2, DragAndDrop_androidKt.getPositionInRoot(dragAndDropEvent))) {
            if (this.node.isAttached) {
                final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                TraversableNodeKt.traverseDescendants(this, new Function1() { // from class: androidx.compose.ui.draganddrop.DragAndDropNode$onMoved$$inlined$firstDescendantOrNull$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        TraversableNode traversableNode2 = (TraversableNode) obj;
                        DragAndDropNode dragAndDropNode3 = (DragAndDropNode) traversableNode2;
                        DragAndDropNode dragAndDropNode4 = this;
                        dragAndDropNode4.getClass();
                        if (!((AndroidComposeView) DelegatableNodeKt.requireOwner(dragAndDropNode4)).dragAndDropManager.interestedTargets.contains(dragAndDropNode3) || !DragAndDropNodeKt.m278access$containsUv8p0NA(dragAndDropNode3, DragAndDrop_androidKt.getPositionInRoot(dragAndDropEvent))) {
                            return TraversableNode$Companion$TraverseDescendantsAction.ContinueTraversal;
                        }
                        Ref$ObjectRef.this.element = traversableNode2;
                        return TraversableNode$Companion$TraverseDescendantsAction.CancelTraversal;
                    }
                });
                traversableNode = (TraversableNode) ref$ObjectRef.element;
            } else {
                traversableNode = null;
            }
            dragAndDropNode = (DragAndDropNode) traversableNode;
        } else {
            dragAndDropNode = dragAndDropNode2;
        }
        if (dragAndDropNode != null && dragAndDropNode2 == null) {
            dragAndDropNode.onEntered(dragAndDropEvent);
            dragAndDropNode.onMoved(dragAndDropEvent);
            DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
            if (dragAndDropTarget != null) {
                dragAndDropTarget.onExited(dragAndDropEvent);
            }
        } else if (dragAndDropNode == null && dragAndDropNode2 != null) {
            DragAndDropTarget dragAndDropTarget2 = this.thisDragAndDropTarget;
            if (dragAndDropTarget2 != null) {
                dragAndDropTarget2.onEntered(dragAndDropEvent);
                dragAndDropTarget2.onMoved(dragAndDropEvent);
            }
            dragAndDropNode2.onExited(dragAndDropEvent);
        } else if (!Intrinsics.areEqual(dragAndDropNode, dragAndDropNode2)) {
            if (dragAndDropNode != null) {
                dragAndDropNode.onEntered(dragAndDropEvent);
                dragAndDropNode.onMoved(dragAndDropEvent);
            }
            if (dragAndDropNode2 != null) {
                dragAndDropNode2.onExited(dragAndDropEvent);
            }
        } else if (dragAndDropNode != null) {
            dragAndDropNode.onMoved(dragAndDropEvent);
        } else {
            DragAndDropTarget dragAndDropTarget3 = this.thisDragAndDropTarget;
            if (dragAndDropTarget3 != null) {
                dragAndDropTarget3.onMoved(dragAndDropEvent);
            }
        }
        this.lastChildDragAndDropModifierNode = dragAndDropNode;
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    /* renamed from: onRemeasured-ozmzZPI */
    public final void mo43onRemeasuredozmzZPI(long j) {
        this.size = j;
    }

    @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
    public final void onStarted(DragAndDropEvent dragAndDropEvent) {
        DragAndDropTarget dragAndDropTarget = this.thisDragAndDropTarget;
        if (dragAndDropTarget != null) {
            dragAndDropTarget.onStarted(dragAndDropEvent);
            return;
        }
        DragAndDropNode dragAndDropNode = this.lastChildDragAndDropModifierNode;
        if (dragAndDropNode != null) {
            dragAndDropNode.onStarted(dragAndDropEvent);
        }
    }
}

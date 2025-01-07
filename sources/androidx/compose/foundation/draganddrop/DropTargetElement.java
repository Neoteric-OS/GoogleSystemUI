package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.draganddrop.DragAndDropNode;
import androidx.compose.ui.draganddrop.DragAndDropNodeKt;
import androidx.compose.ui.draganddrop.DragAndDropTarget;
import androidx.compose.ui.draganddrop.DragAndDropTargetModifierNode;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DropTargetElement extends ModifierNodeElement {
    public final Lambda shouldStartDragAndDrop;
    public final DragAndDropTarget target;

    /* JADX WARN: Multi-variable type inference failed */
    public DropTargetElement(Function1 function1, DragAndDropTarget dragAndDropTarget) {
        this.shouldStartDragAndDrop = (Lambda) function1;
        this.target = dragAndDropTarget;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        DragAndDropTargetNode dragAndDropTargetNode = new DragAndDropTargetNode();
        dragAndDropTargetNode.shouldStartDragAndDrop = this.shouldStartDragAndDrop;
        dragAndDropTargetNode.target = this.target;
        return dragAndDropTargetNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DropTargetElement)) {
            return false;
        }
        DropTargetElement dropTargetElement = (DropTargetElement) obj;
        return Intrinsics.areEqual(this.target, dropTargetElement.target) && this.shouldStartDragAndDrop == dropTargetElement.shouldStartDragAndDrop;
    }

    public final int hashCode() {
        return this.shouldStartDragAndDrop.hashCode() + (this.target.hashCode() * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        DragAndDropTargetNode dragAndDropTargetNode = (DragAndDropTargetNode) node;
        dragAndDropTargetNode.shouldStartDragAndDrop = this.shouldStartDragAndDrop;
        DragAndDropTarget dragAndDropTarget = dragAndDropTargetNode.target;
        DragAndDropTarget dragAndDropTarget2 = this.target;
        if (Intrinsics.areEqual(dragAndDropTarget2, dragAndDropTarget)) {
            return;
        }
        DragAndDropTargetModifierNode dragAndDropTargetModifierNode = dragAndDropTargetNode.dragAndDropNode;
        if (dragAndDropTargetModifierNode != null) {
            dragAndDropTargetNode.undelegate(dragAndDropTargetModifierNode);
        }
        dragAndDropTargetNode.target = dragAndDropTarget2;
        DragAndDropNode DragAndDropTargetModifierNode = DragAndDropNodeKt.DragAndDropTargetModifierNode(new DragAndDropTargetNode$createAndAttachDragAndDropModifierNode$1(dragAndDropTargetNode), dragAndDropTarget2);
        dragAndDropTargetNode.delegate(DragAndDropTargetModifierNode);
        dragAndDropTargetNode.dragAndDropNode = DragAndDropTargetModifierNode;
    }
}

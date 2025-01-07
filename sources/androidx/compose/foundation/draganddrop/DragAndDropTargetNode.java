package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.draganddrop.DragAndDropNode;
import androidx.compose.ui.draganddrop.DragAndDropNodeKt;
import androidx.compose.ui.draganddrop.DragAndDropTarget;
import androidx.compose.ui.draganddrop.DragAndDropTargetModifierNode;
import androidx.compose.ui.node.DelegatingNode;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DragAndDropTargetNode extends DelegatingNode {
    public DragAndDropTargetModifierNode dragAndDropNode;
    public Lambda shouldStartDragAndDrop;
    public DragAndDropTarget target;

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        DragAndDropNode DragAndDropTargetModifierNode = DragAndDropNodeKt.DragAndDropTargetModifierNode(new DragAndDropTargetNode$createAndAttachDragAndDropModifierNode$1(this), this.target);
        delegate(DragAndDropTargetModifierNode);
        this.dragAndDropNode = DragAndDropTargetModifierNode;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        DragAndDropTargetModifierNode dragAndDropTargetModifierNode = this.dragAndDropNode;
        Intrinsics.checkNotNull(dragAndDropTargetModifierNode);
        undelegate(dragAndDropTargetModifierNode);
    }
}

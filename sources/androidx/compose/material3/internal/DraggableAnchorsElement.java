package androidx.compose.material3.internal;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DraggableAnchorsElement extends ModifierNodeElement {
    public final Function2 anchors;
    public final AnchoredDraggableState state;

    public DraggableAnchorsElement(AnchoredDraggableState anchoredDraggableState, Function2 function2) {
        this.state = anchoredDraggableState;
        this.anchors = function2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        DraggableAnchorsNode draggableAnchorsNode = new DraggableAnchorsNode();
        draggableAnchorsNode.state = this.state;
        draggableAnchorsNode.anchors = this.anchors;
        draggableAnchorsNode.orientation = Orientation.Vertical;
        return draggableAnchorsNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DraggableAnchorsElement)) {
            return false;
        }
        DraggableAnchorsElement draggableAnchorsElement = (DraggableAnchorsElement) obj;
        return Intrinsics.areEqual(this.state, draggableAnchorsElement.state) && this.anchors == draggableAnchorsElement.anchors;
    }

    public final int hashCode() {
        return Orientation.Vertical.hashCode() + ((this.anchors.hashCode() + (this.state.hashCode() * 31)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        DraggableAnchorsNode draggableAnchorsNode = (DraggableAnchorsNode) node;
        draggableAnchorsNode.state = this.state;
        draggableAnchorsNode.anchors = this.anchors;
        draggableAnchorsNode.orientation = Orientation.Vertical;
    }
}

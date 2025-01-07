package androidx.compose.foundation.gestures;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DraggableElement extends ModifierNodeElement {
    public static final Function1 CanDrag = new Function1() { // from class: androidx.compose.foundation.gestures.DraggableElement$Companion$CanDrag$1
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return Boolean.TRUE;
        }
    };
    public final boolean enabled;
    public final MutableInteractionSource interactionSource;
    public final Function3 onDragStarted;
    public final Function3 onDragStopped;
    public final Orientation orientation;
    public final boolean reverseDirection;
    public final boolean startDragImmediately;
    public final DraggableState state;

    public DraggableElement(DraggableState draggableState, Orientation orientation, boolean z, MutableInteractionSource mutableInteractionSource, boolean z2, Function3 function3, Function3 function32, boolean z3) {
        this.state = draggableState;
        this.orientation = orientation;
        this.enabled = z;
        this.interactionSource = mutableInteractionSource;
        this.startDragImmediately = z2;
        this.onDragStarted = function3;
        this.onDragStopped = function32;
        this.reverseDirection = z3;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        Function1 function1 = CanDrag;
        Orientation orientation = this.orientation;
        DraggableNode draggableNode = new DraggableNode(function1, this.enabled, this.interactionSource, orientation);
        draggableNode.state = this.state;
        draggableNode.orientation = orientation;
        draggableNode.startDragImmediately = this.startDragImmediately;
        draggableNode.onDragStarted = this.onDragStarted;
        draggableNode.onDragStopped = this.onDragStopped;
        draggableNode.reverseDirection = this.reverseDirection;
        return draggableNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DraggableElement.class != obj.getClass()) {
            return false;
        }
        DraggableElement draggableElement = (DraggableElement) obj;
        return Intrinsics.areEqual(this.state, draggableElement.state) && this.orientation == draggableElement.orientation && this.enabled == draggableElement.enabled && Intrinsics.areEqual(this.interactionSource, draggableElement.interactionSource) && this.startDragImmediately == draggableElement.startDragImmediately && Intrinsics.areEqual(this.onDragStarted, draggableElement.onDragStarted) && Intrinsics.areEqual(this.onDragStopped, draggableElement.onDragStopped) && this.reverseDirection == draggableElement.reverseDirection;
    }

    public final int hashCode() {
        int m = TransitionData$$ExternalSyntheticOutline0.m((this.orientation.hashCode() + (this.state.hashCode() * 31)) * 31, 31, this.enabled);
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        return Boolean.hashCode(this.reverseDirection) + ((this.onDragStopped.hashCode() + ((this.onDragStarted.hashCode() + TransitionData$$ExternalSyntheticOutline0.m((m + (mutableInteractionSource != null ? mutableInteractionSource.hashCode() : 0)) * 31, 31, this.startDragImmediately)) * 31)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        boolean z;
        boolean z2;
        DraggableNode draggableNode = (DraggableNode) node;
        Function1 function1 = CanDrag;
        DraggableState draggableState = draggableNode.state;
        DraggableState draggableState2 = this.state;
        if (Intrinsics.areEqual(draggableState, draggableState2)) {
            z = false;
        } else {
            draggableNode.state = draggableState2;
            z = true;
        }
        Orientation orientation = draggableNode.orientation;
        Orientation orientation2 = this.orientation;
        if (orientation != orientation2) {
            draggableNode.orientation = orientation2;
            z = true;
        }
        boolean z3 = draggableNode.reverseDirection;
        boolean z4 = this.reverseDirection;
        if (z3 != z4) {
            draggableNode.reverseDirection = z4;
            z2 = true;
        } else {
            z2 = z;
        }
        draggableNode.onDragStarted = this.onDragStarted;
        draggableNode.onDragStopped = this.onDragStopped;
        draggableNode.startDragImmediately = this.startDragImmediately;
        draggableNode.update(function1, this.enabled, this.interactionSource, orientation2, z2);
    }
}

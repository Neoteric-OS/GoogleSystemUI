package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl;
import androidx.compose.ui.node.ModifierNodeElement;
import com.android.compose.ui.util.SpaceVectorConverterKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SwipeToSceneElement extends ModifierNodeElement {
    public final DraggableHandlerImpl draggableHandler;
    public final SwipeDetector swipeDetector;

    public SwipeToSceneElement(DraggableHandlerImpl draggableHandlerImpl, SwipeDetector swipeDetector) {
        this.draggableHandler = draggableHandlerImpl;
        this.swipeDetector = swipeDetector;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new SwipeToSceneNode(this.draggableHandler, this.swipeDetector);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SwipeToSceneElement)) {
            return false;
        }
        SwipeToSceneElement swipeToSceneElement = (SwipeToSceneElement) obj;
        return Intrinsics.areEqual(this.draggableHandler, swipeToSceneElement.draggableHandler) && Intrinsics.areEqual(this.swipeDetector, swipeToSceneElement.swipeDetector);
    }

    public final int hashCode() {
        return this.swipeDetector.hashCode() + (this.draggableHandler.hashCode() * 31);
    }

    public final String toString() {
        return "SwipeToSceneElement(draggableHandler=" + this.draggableHandler + ", swipeDetector=" + this.swipeDetector + ")";
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        SwipeToSceneNode swipeToSceneNode = (SwipeToSceneNode) node;
        DraggableHandlerImpl draggableHandlerImpl = swipeToSceneNode._draggableHandler;
        DraggableHandlerImpl draggableHandlerImpl2 = this.draggableHandler;
        if (Intrinsics.areEqual(draggableHandlerImpl, draggableHandlerImpl2)) {
            return;
        }
        swipeToSceneNode._draggableHandler = draggableHandlerImpl2;
        MultiPointerDraggableNode multiPointerDraggableNode = swipeToSceneNode.multiPointerDraggableNode;
        Orientation orientation = multiPointerDraggableNode.orientation;
        Orientation orientation2 = draggableHandlerImpl2.orientation;
        if (orientation2 != orientation) {
            multiPointerDraggableNode.orientation = orientation2;
            multiPointerDraggableNode.converter = SpaceVectorConverterKt.SpaceVectorConverter(orientation2);
            ((SuspendingPointerInputModifierNodeImpl) multiPointerDraggableNode.pointerInput).resetPointerInputHandler();
        }
    }
}

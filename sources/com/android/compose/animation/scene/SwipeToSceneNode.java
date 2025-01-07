package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher;
import androidx.compose.ui.input.nestedscroll.NestedScrollNode;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import com.android.compose.animation.scene.Swipe;
import com.android.compose.animation.scene.UserAction;
import com.android.compose.animation.scene.content.Content;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SwipeToSceneNode extends DelegatingNode implements PointerInputModifierNode {
    public DraggableHandlerImpl _draggableHandler;
    public final MultiPointerDraggableNode multiPointerDraggableNode;
    public final NestedScrollHandlerImpl nestedScrollHandlerImpl;

    public SwipeToSceneNode(DraggableHandlerImpl draggableHandlerImpl, SwipeDetector swipeDetector) {
        NestedScrollDispatcher nestedScrollDispatcher = new NestedScrollDispatcher();
        MultiPointerDraggableNode multiPointerDraggableNode = new MultiPointerDraggableNode(draggableHandlerImpl.orientation, new SwipeToSceneNode$multiPointerDraggableNode$1(0, this, SwipeToSceneNode.class, "enabled", "enabled()Z", 0), new SwipeToSceneNode$multiPointerDraggableNode$2(1, this, SwipeToSceneNode.class, "startDragImmediately", "startDragImmediately-k-4lQ0M(J)Z", 0), new SwipeToSceneNode$multiPointerDraggableNode$3(3, draggableHandlerImpl, DraggableHandlerImpl.class, "onDragStarted", "onDragStarted-MjzGXtM(Landroidx/compose/ui/geometry/Offset;FI)Lcom/android/compose/animation/scene/DragController;", 0), new SwipeToSceneNode$multiPointerDraggableNode$4(0, this, SwipeToSceneNode.class, "onFirstPointerDown", "onFirstPointerDown()V", 0), swipeDetector, nestedScrollDispatcher);
        delegate(multiPointerDraggableNode);
        this.multiPointerDraggableNode = multiPointerDraggableNode;
        this._draggableHandler = draggableHandlerImpl;
        NestedScrollBehavior.Companion.getClass();
        NestedScrollBehavior nestedScrollBehavior = NestedScrollBehavior.Default;
        SwipeToSceneNode$nestedScrollHandlerImpl$1 swipeToSceneNode$nestedScrollHandlerImpl$1 = new Function0() { // from class: com.android.compose.animation.scene.SwipeToSceneNode$nestedScrollHandlerImpl$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return Boolean.FALSE;
            }
        };
        SwipeToSceneNode$nestedScrollHandlerImpl$2 swipeToSceneNode$nestedScrollHandlerImpl$2 = new SwipeToSceneNode$nestedScrollHandlerImpl$2(this);
        NestedScrollHandlerImpl nestedScrollHandlerImpl = new NestedScrollHandlerImpl(draggableHandlerImpl.layoutImpl, draggableHandlerImpl.orientation, nestedScrollBehavior, nestedScrollBehavior, swipeToSceneNode$nestedScrollHandlerImpl$1, swipeToSceneNode$nestedScrollHandlerImpl$2);
        this.nestedScrollHandlerImpl = nestedScrollHandlerImpl;
        delegate(new NestedScrollNode(nestedScrollHandlerImpl.connection, nestedScrollDispatcher));
        delegate(new ScrollBehaviorOwnerNode(draggableHandlerImpl.nestedScrollKey, nestedScrollHandlerImpl));
    }

    public static boolean shouldEnableSwipes(Content content, Orientation orientation) {
        Set<UserAction.Resolved> keySet = ((Map) ((SnapshotMutableStateImpl) content.userActions$delegate).getValue()).keySet();
        if ((keySet instanceof Collection) && keySet.isEmpty()) {
            return false;
        }
        for (UserAction.Resolved resolved : keySet) {
            if ((resolved instanceof Swipe.Resolved) && ((Swipe.Resolved) resolved).direction.getOrientation() == orientation) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final void onCancelPointerInput() {
        this.multiPointerDraggableNode.onCancelPointerInput();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        this.nestedScrollHandlerImpl.connection.m744onPriorityStopAH228Gc(0L);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY */
    public final void mo15onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        this.multiPointerDraggableNode.mo15onPointerEventH0pRuoY(pointerEvent, pointerEventPass, j);
    }
}

package com.android.compose.animation.scene;

import androidx.compose.ui.geometry.Offset;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SwipeToSceneNode$nestedScrollHandlerImpl$2 {
    public final /* synthetic */ SwipeToSceneNode this$0;

    public SwipeToSceneNode$nestedScrollHandlerImpl$2(SwipeToSceneNode swipeToSceneNode) {
        this.this$0 = swipeToSceneNode;
    }

    public final PointersInfo pointersInfo() {
        MultiPointerDraggableNode multiPointerDraggableNode = this.this$0.multiPointerDraggableNode;
        Offset offset = multiPointerDraggableNode.startedPosition;
        int i = multiPointerDraggableNode.pointersDown;
        if (i < 1) {
            i = 1;
        }
        return new PointersInfo(offset, i);
    }
}

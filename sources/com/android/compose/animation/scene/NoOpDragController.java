package com.android.compose.animation.scene;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoOpDragController implements DragController {
    public static final NoOpDragController INSTANCE = new NoOpDragController();

    @Override // com.android.compose.animation.scene.DragController
    public final float onDrag(float f) {
        return 0.0f;
    }

    @Override // com.android.compose.animation.scene.DragController
    public final float onStop(float f, boolean z) {
        return 0.0f;
    }
}

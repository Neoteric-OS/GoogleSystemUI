package com.android.compose.animation.scene;

import androidx.compose.ui.input.pointer.PointerInputChange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PassthroughSwipeDetector implements SwipeDetector {
    @Override // com.android.compose.animation.scene.SwipeDetector
    public final boolean detectSwipe(PointerInputChange pointerInputChange) {
        return true;
    }
}

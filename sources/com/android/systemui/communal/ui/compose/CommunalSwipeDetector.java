package com.android.systemui.communal.ui.compose;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.unit.Density;
import com.android.compose.animation.scene.Edge;
import com.android.compose.animation.scene.SwipeDetector;
import com.android.compose.animation.scene.SwipeSource;
import com.android.compose.animation.scene.SwipeSourceDetector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalSwipeDetector implements SwipeSourceDetector, SwipeDetector {
    public Edge.Resolved lastDirection;

    @Override // com.android.compose.animation.scene.SwipeDetector
    public final boolean detectSwipe(PointerInputChange pointerInputChange) {
        if (Float.intBitsToFloat((int) (PointerEventKt.positionChangeInternal(pointerInputChange, false) >> 32)) > 0.0f) {
            this.lastDirection = Edge.Resolved.Left;
        } else {
            this.lastDirection = Edge.Resolved.Right;
        }
        return Math.abs(Float.intBitsToFloat((int) (PointerEventKt.positionChangeInternal(pointerInputChange, false) >> 32)) / Float.intBitsToFloat((int) (PointerEventKt.positionChangeInternal(pointerInputChange, false) & 4294967295L))) > 0.5f;
    }

    @Override // com.android.compose.animation.scene.SwipeSourceDetector
    /* renamed from: source-NDhlJko */
    public final SwipeSource.Resolved mo733sourceNDhlJko(long j, long j2, Density density, Orientation orientation) {
        return this.lastDirection;
    }
}

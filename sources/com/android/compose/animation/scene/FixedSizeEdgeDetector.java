package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.unit.Density;
import com.android.compose.animation.scene.Edge;
import com.android.compose.animation.scene.SwipeSource;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FixedSizeEdgeDetector implements SwipeSourceDetector {
    public final float size;

    public FixedSizeEdgeDetector(float f) {
        this.size = f;
    }

    @Override // com.android.compose.animation.scene.SwipeSourceDetector
    /* renamed from: source-NDhlJko, reason: not valid java name */
    public final SwipeSource.Resolved mo733sourceNDhlJko(long j, long j2, Density density, Orientation orientation) {
        int i;
        int i2;
        Edge.Resolved resolved;
        Edge.Resolved resolved2;
        int ordinal = orientation.ordinal();
        if (ordinal == 0) {
            i = (int) (j & 4294967295L);
            i2 = (int) (j2 & 4294967295L);
            resolved = Edge.Resolved.Top;
            resolved2 = Edge.Resolved.Bottom;
        } else {
            if (ordinal != 1) {
                throw new NoWhenBranchMatchedException();
            }
            i = (int) (j >> 32);
            i2 = (int) (j2 >> 32);
            resolved = Edge.Resolved.Left;
            resolved2 = Edge.Resolved.Right;
        }
        float mo51toPx0680j_4 = density.mo51toPx0680j_4(this.size);
        float f = i2;
        if (f <= mo51toPx0680j_4) {
            return resolved;
        }
        if (f >= i - mo51toPx0680j_4) {
            return resolved2;
        }
        return null;
    }
}

package androidx.compose.foundation;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClipScrollableContainerKt$VerticalScrollableClipModifier$1 implements Shape {
    @Override // androidx.compose.ui.graphics.Shape
    /* renamed from: createOutline-Pq9zytI */
    public final Outline mo37createOutlinePq9zytI(long j, LayoutDirection layoutDirection, Density density) {
        float mo45roundToPx0680j_4 = density.mo45roundToPx0680j_4(ClipScrollableContainerKt.MaxSupportedElevation);
        return new Outline.Rectangle(new Rect(-mo45roundToPx0680j_4, 0.0f, Float.intBitsToFloat((int) (j >> 32)) + mo45roundToPx0680j_4, Float.intBitsToFloat((int) (j & 4294967295L))));
    }
}

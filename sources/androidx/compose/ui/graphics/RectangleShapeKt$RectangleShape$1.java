package androidx.compose.ui.graphics;

import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RectangleShapeKt$RectangleShape$1 implements Shape {
    @Override // androidx.compose.ui.graphics.Shape
    /* renamed from: createOutline-Pq9zytI */
    public final Outline mo37createOutlinePq9zytI(long j, LayoutDirection layoutDirection, Density density) {
        return new Outline.Rectangle(RectKt.m324Recttz77jQw(0L, j));
    }

    public final String toString() {
        return "RectangleShape";
    }
}

package androidx.compose.foundation.shape;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CornerBasedShape implements Shape {
    public final CornerSize bottomEnd;
    public final CornerSize bottomStart;
    public final CornerSize topEnd;
    public final CornerSize topStart;

    public CornerBasedShape(CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, CornerSize cornerSize4) {
        this.topStart = cornerSize;
        this.topEnd = cornerSize2;
        this.bottomEnd = cornerSize3;
        this.bottomStart = cornerSize4;
    }

    public static RoundedCornerShape copy$default(CornerBasedShape cornerBasedShape, CornerSize cornerSize, CornerSize cornerSize2, CornerSize cornerSize3, int i) {
        if ((i & 1) != 0) {
            cornerSize = cornerBasedShape.topStart;
        }
        CornerSize cornerSize4 = cornerBasedShape.topEnd;
        if ((i & 4) != 0) {
            cornerSize2 = cornerBasedShape.bottomEnd;
        }
        ((RoundedCornerShape) cornerBasedShape).getClass();
        return new RoundedCornerShape(cornerSize, cornerSize4, cornerSize2, cornerSize3);
    }

    @Override // androidx.compose.ui.graphics.Shape
    /* renamed from: createOutline-Pq9zytI */
    public final Outline mo37createOutlinePq9zytI(long j, LayoutDirection layoutDirection, Density density) {
        float mo146toPxTmRCtEA = this.topStart.mo146toPxTmRCtEA(j, density);
        float mo146toPxTmRCtEA2 = this.topEnd.mo146toPxTmRCtEA(j, density);
        float mo146toPxTmRCtEA3 = this.bottomEnd.mo146toPxTmRCtEA(j, density);
        float mo146toPxTmRCtEA4 = this.bottomStart.mo146toPxTmRCtEA(j, density);
        float m328getMinDimensionimpl = Size.m328getMinDimensionimpl(j);
        float f = mo146toPxTmRCtEA + mo146toPxTmRCtEA4;
        if (f > m328getMinDimensionimpl) {
            float f2 = m328getMinDimensionimpl / f;
            mo146toPxTmRCtEA *= f2;
            mo146toPxTmRCtEA4 *= f2;
        }
        float f3 = mo146toPxTmRCtEA2 + mo146toPxTmRCtEA3;
        if (f3 > m328getMinDimensionimpl) {
            float f4 = m328getMinDimensionimpl / f3;
            mo146toPxTmRCtEA2 *= f4;
            mo146toPxTmRCtEA3 *= f4;
        }
        if (mo146toPxTmRCtEA < 0.0f || mo146toPxTmRCtEA2 < 0.0f || mo146toPxTmRCtEA3 < 0.0f || mo146toPxTmRCtEA4 < 0.0f) {
            InlineClassHelperKt.throwIllegalArgumentException("Corner size in Px can't be negative(topStart = " + mo146toPxTmRCtEA + ", topEnd = " + mo146toPxTmRCtEA2 + ", bottomEnd = " + mo146toPxTmRCtEA3 + ", bottomStart = " + mo146toPxTmRCtEA4 + ")!");
        }
        if (mo146toPxTmRCtEA + mo146toPxTmRCtEA2 + mo146toPxTmRCtEA3 + mo146toPxTmRCtEA4 == 0.0f) {
            return new Outline.Rectangle(RectKt.m324Recttz77jQw(0L, j));
        }
        Rect m324Recttz77jQw = RectKt.m324Recttz77jQw(0L, j);
        LayoutDirection layoutDirection2 = LayoutDirection.Ltr;
        float f5 = layoutDirection == layoutDirection2 ? mo146toPxTmRCtEA : mo146toPxTmRCtEA2;
        long floatToRawIntBits = (Float.floatToRawIntBits(f5) << 32) | (Float.floatToRawIntBits(f5) & 4294967295L);
        if (layoutDirection == layoutDirection2) {
            mo146toPxTmRCtEA = mo146toPxTmRCtEA2;
        }
        long floatToRawIntBits2 = (Float.floatToRawIntBits(mo146toPxTmRCtEA) << 32) | (Float.floatToRawIntBits(mo146toPxTmRCtEA) & 4294967295L);
        float f6 = layoutDirection == layoutDirection2 ? mo146toPxTmRCtEA3 : mo146toPxTmRCtEA4;
        long floatToRawIntBits3 = (Float.floatToRawIntBits(f6) << 32) | (Float.floatToRawIntBits(f6) & 4294967295L);
        if (layoutDirection != layoutDirection2) {
            mo146toPxTmRCtEA4 = mo146toPxTmRCtEA3;
        }
        return new Outline.Rounded(new RoundRect(m324Recttz77jQw.left, m324Recttz77jQw.top, m324Recttz77jQw.right, m324Recttz77jQw.bottom, floatToRawIntBits, floatToRawIntBits2, floatToRawIntBits3, (Float.floatToRawIntBits(mo146toPxTmRCtEA4) << 32) | (Float.floatToRawIntBits(mo146toPxTmRCtEA4) & 4294967295L)));
    }
}

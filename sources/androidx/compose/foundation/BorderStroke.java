package androidx.compose.foundation;

import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.unit.Dp;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BorderStroke {
    public final SolidColor brush;
    public final float width;

    public BorderStroke(float f, SolidColor solidColor) {
        this.width = f;
        this.brush = solidColor;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BorderStroke)) {
            return false;
        }
        BorderStroke borderStroke = (BorderStroke) obj;
        return Dp.m668equalsimpl0(this.width, borderStroke.width) && this.brush.equals(borderStroke.brush);
    }

    public final int hashCode() {
        return this.brush.hashCode() + (Float.hashCode(this.width) * 31);
    }

    public final String toString() {
        return "BorderStroke(width=" + ((Object) Dp.m669toStringimpl(this.width)) + ", brush=" + this.brush + ')';
    }
}

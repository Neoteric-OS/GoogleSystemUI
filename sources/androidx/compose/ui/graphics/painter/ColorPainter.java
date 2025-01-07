package androidx.compose.ui.graphics.painter;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ColorPainter extends Painter {
    public final long color;
    public ColorFilter colorFilter;
    public float alpha = 1.0f;
    public final long intrinsicSize = 9205357640488583168L;

    public ColorPainter(long j) {
        this.color = j;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public final boolean applyAlpha(float f) {
        this.alpha = f;
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public final boolean applyColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ColorPainter) {
            return Color.m363equalsimpl0(this.color, ((ColorPainter) obj).color);
        }
        return false;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* renamed from: getIntrinsicSize-NH-jbRc */
    public final long mo436getIntrinsicSizeNHjbRc() {
        return this.intrinsicSize;
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Long.hashCode(this.color);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    public final void onDraw(DrawScope drawScope) {
        drawScope.mo415drawRectnJ9OG0(this.color, 0L, (r19 & 4) != 0 ? DrawScope.m430offsetSizePENXr5M(drawScope.mo432getSizeNHjbRc(), 0L) : 0L, (r19 & 8) != 0 ? 1.0f : this.alpha, (r19 & 32) != 0 ? null : this.colorFilter, (r19 & 64) != 0 ? 3 : 0);
    }

    public final String toString() {
        return "ColorPainter(color=" + ((Object) Color.m369toStringimpl(this.color)) + ')';
    }
}

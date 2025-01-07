package androidx.compose.ui.geometry;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RoundRect {
    public final float bottom;
    public final long bottomLeftCornerRadius;
    public final long bottomRightCornerRadius;
    public final float left;
    public final float right;
    public final float top;
    public final long topLeftCornerRadius;
    public final long topRightCornerRadius;

    static {
        RoundRectKt.m325RoundRectgG7oq9Y(0.0f, 0.0f, 0.0f, 0.0f, 0L);
    }

    public RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
        this.topLeftCornerRadius = j;
        this.topRightCornerRadius = j2;
        this.bottomRightCornerRadius = j3;
        this.bottomLeftCornerRadius = j4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RoundRect)) {
            return false;
        }
        RoundRect roundRect = (RoundRect) obj;
        return Float.compare(this.left, roundRect.left) == 0 && Float.compare(this.top, roundRect.top) == 0 && Float.compare(this.right, roundRect.right) == 0 && Float.compare(this.bottom, roundRect.bottom) == 0 && CornerRadius.m306equalsimpl0(this.topLeftCornerRadius, roundRect.topLeftCornerRadius) && CornerRadius.m306equalsimpl0(this.topRightCornerRadius, roundRect.topRightCornerRadius) && CornerRadius.m306equalsimpl0(this.bottomRightCornerRadius, roundRect.bottomRightCornerRadius) && CornerRadius.m306equalsimpl0(this.bottomLeftCornerRadius, roundRect.bottomLeftCornerRadius);
    }

    public final float getHeight() {
        return this.bottom - this.top;
    }

    public final float getWidth() {
        return this.right - this.left;
    }

    public final int hashCode() {
        return Long.hashCode(this.bottomLeftCornerRadius) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.left) * 31, this.top, 31), this.right, 31), this.bottom, 31), 31, this.topLeftCornerRadius), 31, this.topRightCornerRadius), 31, this.bottomRightCornerRadius);
    }

    public final String toString() {
        String str = GeometryUtilsKt.toStringAsFixed(this.left) + ", " + GeometryUtilsKt.toStringAsFixed(this.top) + ", " + GeometryUtilsKt.toStringAsFixed(this.right) + ", " + GeometryUtilsKt.toStringAsFixed(this.bottom);
        long j = this.topLeftCornerRadius;
        long j2 = this.topRightCornerRadius;
        boolean m306equalsimpl0 = CornerRadius.m306equalsimpl0(j, j2);
        long j3 = this.bottomRightCornerRadius;
        long j4 = this.bottomLeftCornerRadius;
        if (!m306equalsimpl0 || !CornerRadius.m306equalsimpl0(j2, j3) || !CornerRadius.m306equalsimpl0(j3, j4)) {
            StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m("RoundRect(rect=", str, ", topLeft=");
            m.append((Object) CornerRadius.m307toStringimpl(j));
            m.append(", topRight=");
            m.append((Object) CornerRadius.m307toStringimpl(j2));
            m.append(", bottomRight=");
            m.append((Object) CornerRadius.m307toStringimpl(j3));
            m.append(", bottomLeft=");
            m.append((Object) CornerRadius.m307toStringimpl(j4));
            m.append(')');
            return m.toString();
        }
        int i = (int) (j >> 32);
        int i2 = (int) (j & 4294967295L);
        if (Float.intBitsToFloat(i) == Float.intBitsToFloat(i2)) {
            StringBuilder m2 = ActivityResultRegistry$$ExternalSyntheticOutline0.m("RoundRect(rect=", str, ", radius=");
            m2.append(GeometryUtilsKt.toStringAsFixed(Float.intBitsToFloat(i)));
            m2.append(')');
            return m2.toString();
        }
        StringBuilder m3 = ActivityResultRegistry$$ExternalSyntheticOutline0.m("RoundRect(rect=", str, ", x=");
        m3.append(GeometryUtilsKt.toStringAsFixed(Float.intBitsToFloat(i)));
        m3.append(", y=");
        m3.append(GeometryUtilsKt.toStringAsFixed(Float.intBitsToFloat(i2)));
        m3.append(')');
        return m3.toString();
    }
}

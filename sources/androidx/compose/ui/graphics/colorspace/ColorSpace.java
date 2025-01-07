package androidx.compose.ui.graphics.colorspace;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ColorSpace {
    public final int id;
    public final long model;
    public final String name;

    public ColorSpace(int i, long j, String str) {
        this.name = str;
        this.model = j;
        this.id = i;
        if (str.length() == 0) {
            throw new IllegalArgumentException("The name of a color space cannot be null and must contain at least 1 character");
        }
        if (i < -1 || i > 63) {
            throw new IllegalArgumentException("The id must be between -1 and 63");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorSpace colorSpace = (ColorSpace) obj;
        if (this.id == colorSpace.id && Intrinsics.areEqual(this.name, colorSpace.name)) {
            return ColorModel.m402equalsimpl0(this.model, colorSpace.model);
        }
        return false;
    }

    public abstract float getMaxValue(int i);

    public abstract float getMinValue(int i);

    public int hashCode() {
        int hashCode = this.name.hashCode() * 31;
        int i = ColorModel.$r8$clinit;
        return Scale$$ExternalSyntheticOutline0.m(hashCode, 31, this.model) + this.id;
    }

    public boolean isSrgb() {
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(" (id=");
        sb.append(this.id);
        sb.append(", model=");
        long j = ColorModel.Rgb;
        long j2 = this.model;
        sb.append((Object) (ColorModel.m402equalsimpl0(j2, j) ? "Rgb" : ColorModel.m402equalsimpl0(j2, ColorModel.Xyz) ? "Xyz" : ColorModel.m402equalsimpl0(j2, ColorModel.Lab) ? "Lab" : ColorModel.m402equalsimpl0(j2, ColorModel.Cmyk) ? "Cmyk" : "Unknown"));
        sb.append(')');
        return sb.toString();
    }

    public abstract long toXy$ui_graphics_release(float f, float f2, float f3);

    public abstract float toZ$ui_graphics_release(float f, float f2, float f3);

    /* renamed from: xyzaToColor-JlNiLsg$ui_graphics_release, reason: not valid java name */
    public abstract long mo403xyzaToColorJlNiLsg$ui_graphics_release(float f, float f2, float f3, float f4, ColorSpace colorSpace);
}

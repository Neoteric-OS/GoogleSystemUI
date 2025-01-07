package androidx.compose.ui.graphics;

import androidx.compose.foundation.OverscrollConfiguration$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BlendModeColorFilter extends ColorFilter {
    public final int blendMode;
    public final long color;

    public BlendModeColorFilter(long j, int i) {
        super(new android.graphics.BlendModeColorFilter(ColorKt.m373toArgb8_81llA(j), AndroidBlendMode_androidKt.m333toAndroidBlendModes9anfk8(i)));
        this.color = j;
        this.blendMode = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BlendModeColorFilter)) {
            return false;
        }
        BlendModeColorFilter blendModeColorFilter = (BlendModeColorFilter) obj;
        return Color.m363equalsimpl0(this.color, blendModeColorFilter.color) && BlendMode.m357equalsimpl0(this.blendMode, blendModeColorFilter.blendMode);
    }

    public final int hashCode() {
        int i = Color.$r8$clinit;
        return Integer.hashCode(this.blendMode) + (Long.hashCode(this.color) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BlendModeColorFilter(color=");
        OverscrollConfiguration$$ExternalSyntheticOutline0.m(this.color, ", blendMode=", sb);
        int i = this.blendMode;
        sb.append((Object) (BlendMode.m357equalsimpl0(i, 0) ? "Clear" : BlendMode.m357equalsimpl0(i, 1) ? "Src" : BlendMode.m357equalsimpl0(i, 2) ? "Dst" : BlendMode.m357equalsimpl0(i, 3) ? "SrcOver" : BlendMode.m357equalsimpl0(i, 4) ? "DstOver" : BlendMode.m357equalsimpl0(i, 5) ? "SrcIn" : BlendMode.m357equalsimpl0(i, 6) ? "DstIn" : BlendMode.m357equalsimpl0(i, 7) ? "SrcOut" : BlendMode.m357equalsimpl0(i, 8) ? "DstOut" : BlendMode.m357equalsimpl0(i, 9) ? "SrcAtop" : BlendMode.m357equalsimpl0(i, 10) ? "DstAtop" : BlendMode.m357equalsimpl0(i, 11) ? "Xor" : BlendMode.m357equalsimpl0(i, 12) ? "Plus" : BlendMode.m357equalsimpl0(i, 13) ? "Modulate" : BlendMode.m357equalsimpl0(i, 14) ? "Screen" : BlendMode.m357equalsimpl0(i, 15) ? "Overlay" : BlendMode.m357equalsimpl0(i, 16) ? "Darken" : BlendMode.m357equalsimpl0(i, 17) ? "Lighten" : BlendMode.m357equalsimpl0(i, 18) ? "ColorDodge" : BlendMode.m357equalsimpl0(i, 19) ? "ColorBurn" : BlendMode.m357equalsimpl0(i, 20) ? "HardLight" : BlendMode.m357equalsimpl0(i, 21) ? "Softlight" : BlendMode.m357equalsimpl0(i, 22) ? "Difference" : BlendMode.m357equalsimpl0(i, 23) ? "Exclusion" : BlendMode.m357equalsimpl0(i, 24) ? "Multiply" : BlendMode.m357equalsimpl0(i, 25) ? "Hue" : BlendMode.m357equalsimpl0(i, 26) ? "Saturation" : BlendMode.m357equalsimpl0(i, 27) ? "Color" : BlendMode.m357equalsimpl0(i, 28) ? "Luminosity" : "Unknown"));
        sb.append(')');
        return sb.toString();
    }
}

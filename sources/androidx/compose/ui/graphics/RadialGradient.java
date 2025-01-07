package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RadialGradient extends ShaderBrush {
    public final long center;
    public final List colors;
    public final float radius;

    public RadialGradient(List list, long j, float f) {
        this.colors = list;
        this.center = j;
        this.radius = f;
    }

    @Override // androidx.compose.ui.graphics.ShaderBrush
    /* renamed from: createShader-uvyYCjk */
    public final Shader mo359createShaderuvyYCjk(long j) {
        float intBitsToFloat;
        float intBitsToFloat2;
        long j2 = this.center;
        if ((9223372034707292159L & j2) == 9205357640488583168L) {
            long m332getCenteruvyYCjk = SizeKt.m332getCenteruvyYCjk(j);
            intBitsToFloat = Float.intBitsToFloat((int) (m332getCenteruvyYCjk >> 32));
            intBitsToFloat2 = Float.intBitsToFloat((int) (m332getCenteruvyYCjk & 4294967295L));
        } else {
            int i = (int) (j2 >> 32);
            if (Float.intBitsToFloat(i) == Float.POSITIVE_INFINITY) {
                i = (int) (j >> 32);
            }
            intBitsToFloat = Float.intBitsToFloat(i);
            int i2 = (int) (j2 & 4294967295L);
            if (Float.intBitsToFloat(i2) == Float.POSITIVE_INFINITY) {
                i2 = (int) (j & 4294967295L);
            }
            intBitsToFloat2 = Float.intBitsToFloat(i2);
        }
        List list = this.colors;
        long floatToRawIntBits = (Float.floatToRawIntBits(intBitsToFloat) << 32) | (Float.floatToRawIntBits(intBitsToFloat2) & 4294967295L);
        float f = this.radius;
        if (f == Float.POSITIVE_INFINITY) {
            f = Size.m328getMinDimensionimpl(j) / 2;
        }
        float f2 = f;
        AndroidShader_androidKt.validateColorStops(list);
        float intBitsToFloat3 = Float.intBitsToFloat((int) (floatToRawIntBits >> 32));
        float intBitsToFloat4 = Float.intBitsToFloat((int) (floatToRawIntBits & 4294967295L));
        int size = list.size();
        int[] iArr = new int[size];
        for (int i3 = 0; i3 < size; i3++) {
            iArr[i3] = ColorKt.m373toArgb8_81llA(((Color) list.get(i3)).value);
        }
        return new android.graphics.RadialGradient(intBitsToFloat3, intBitsToFloat4, f2, iArr, (float[]) null, AndroidTileMode_androidKt.m356toAndroidTileMode0vamqd0());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RadialGradient)) {
            return false;
        }
        RadialGradient radialGradient = (RadialGradient) obj;
        return Intrinsics.areEqual(this.colors, radialGradient.colors) && Intrinsics.areEqual((Object) null, (Object) null) && Offset.m310equalsimpl0(this.center, radialGradient.center) && this.radius == radialGradient.radius && TileMode.m396equalsimpl0(0);
    }

    public final int hashCode() {
        return Integer.hashCode(0) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(this.colors.hashCode() * 961, 31, this.center), this.radius, 31);
    }

    public final String toString() {
        String str;
        long j = this.center;
        String str2 = "";
        if ((9223372034707292159L & j) != 9205357640488583168L) {
            str = "center=" + ((Object) Offset.m317toStringimpl(j)) + ", ";
        } else {
            str = "";
        }
        float f = this.radius;
        if ((Float.floatToRawIntBits(f) & Integer.MAX_VALUE) < 2139095040) {
            str2 = "radius=" + f + ", ";
        }
        return "RadialGradient(colors=" + this.colors + ", stops=null, " + str + str2 + "tileMode=" + ((Object) TileMode.m397toStringimpl()) + ')';
    }
}

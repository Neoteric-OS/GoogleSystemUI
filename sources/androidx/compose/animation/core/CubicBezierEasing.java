package androidx.compose.animation.core;

import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.ui.graphics.BezierKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CubicBezierEasing implements Easing {
    public final float a;
    public final float b;
    public final float c;
    public final float d;
    public final float max;
    public final float min;

    public CubicBezierEasing(float f, float f2, float f3, float f4) {
        int writeValidRootInUnitRange;
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
        if (!((Float.isNaN(f) || Float.isNaN(f2) || Float.isNaN(f3) || Float.isNaN(f4)) ? false : true)) {
            PreconditionsKt.throwIllegalArgumentException("Parameters to CubicBezierEasing cannot be NaN. Actual parameters are: " + f + ", " + f2 + ", " + f3 + ", " + f4 + '.');
        }
        float[] fArr = new float[5];
        float f5 = (f2 - 0.0f) * 3.0f;
        float f6 = (f4 - f2) * 3.0f;
        float f7 = (1.0f - f4) * 3.0f;
        double d = f5;
        double d2 = f6;
        double d3 = f7;
        double d4 = d2 * 2.0d;
        double d5 = (d - d4) + d3;
        if (d5 == 0.0d) {
            writeValidRootInUnitRange = d2 == d3 ? 0 : BezierKt.writeValidRootInUnitRange((float) ((d4 - d3) / (d4 - (d3 * 2.0d))), fArr, 0);
        } else {
            double d6 = -Math.sqrt((d2 * d2) - (d3 * d));
            double d7 = (-d) + d2;
            int writeValidRootInUnitRange2 = BezierKt.writeValidRootInUnitRange((float) ((-(d6 + d7)) / d5), fArr, 0);
            writeValidRootInUnitRange = BezierKt.writeValidRootInUnitRange((float) ((d6 - d7) / d5), fArr, writeValidRootInUnitRange2) + writeValidRootInUnitRange2;
            if (writeValidRootInUnitRange > 1) {
                float f8 = fArr[0];
                float f9 = fArr[1];
                if (f8 > f9) {
                    fArr[0] = f9;
                    fArr[1] = f8;
                } else if (f8 == f9) {
                    writeValidRootInUnitRange--;
                }
            }
        }
        float f10 = (f6 - f5) * 2.0f;
        int writeValidRootInUnitRange3 = BezierKt.writeValidRootInUnitRange((-f10) / (((f7 - f6) * 2.0f) - f10), fArr, writeValidRootInUnitRange) + writeValidRootInUnitRange;
        float min = Math.min(0.0f, 1.0f);
        float max = Math.max(0.0f, 1.0f);
        for (int i = 0; i < writeValidRootInUnitRange3; i++) {
            float f11 = fArr[i];
            float f12 = (((((((((f2 - f4) * 3.0f) + 1.0f) - 0.0f) * f11) + (((f4 - (f2 * 2.0f)) + 0.0f) * 3.0f)) * f11) + f5) * f11) + 0.0f;
            min = Math.min(min, f12);
            max = Math.max(max, f12);
        }
        long floatToRawIntBits = (Float.floatToRawIntBits(min) << 32) | (Float.floatToRawIntBits(max) & 4294967295L);
        this.min = Float.intBitsToFloat((int) (floatToRawIntBits >> 32));
        this.max = Float.intBitsToFloat((int) (floatToRawIntBits & 4294967295L));
    }

    public final boolean equals(Object obj) {
        if (obj instanceof CubicBezierEasing) {
            CubicBezierEasing cubicBezierEasing = (CubicBezierEasing) obj;
            if (this.a == cubicBezierEasing.a && this.b == cubicBezierEasing.b && this.c == cubicBezierEasing.c && this.d == cubicBezierEasing.d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(this.d) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.a) * 31, this.b, 31), this.c, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CubicBezierEasing(a=");
        sb.append(this.a);
        sb.append(", b=");
        sb.append(this.b);
        sb.append(", c=");
        sb.append(this.c);
        sb.append(", d=");
        return AndroidFlingSpline$$ExternalSyntheticOutline0.m(sb, this.d, ')');
    }

    /* JADX WARN: Code restructure failed: missing block: B:124:0x022c, code lost:
    
        if (java.lang.Math.abs(r3 - r2) > 1.0E-6f) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:135:0x025b, code lost:
    
        if (java.lang.Math.abs(r3 - r2) > 1.0E-6f) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0087, code lost:
    
        if (java.lang.Math.abs(r3 - r2) > 1.0E-6f) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x008b, code lost:
    
        r24 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00eb, code lost:
    
        if (java.lang.Math.abs(r3 - r2) > 1.0E-6f) goto L132;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x01d5, code lost:
    
        if (java.lang.Math.abs(r3 - r2) > 1.0E-6f) goto L132;
     */
    @Override // androidx.compose.animation.core.Easing
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float transform(float r26) {
        /*
            Method dump skipped, instructions count: 700
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.CubicBezierEasing.transform(float):float");
    }
}

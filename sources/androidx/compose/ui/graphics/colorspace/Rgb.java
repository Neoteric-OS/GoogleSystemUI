package androidx.compose.ui.graphics.colorspace;

import androidx.compose.ui.graphics.ColorKt;
import java.util.Arrays;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Rgb extends ColorSpace {
    public static final Rgb$$ExternalSyntheticLambda2 DoubleIdentity = new Rgb$$ExternalSyntheticLambda2();
    public final Function1 eotf;
    public final Rgb$$ExternalSyntheticLambda0 eotfFunc;
    public final DoubleFunction eotfOrig;
    public final float[] inverseTransform;
    public final boolean isSrgb;
    public final float max;
    public final float min;
    public final Function1 oetf;
    public final Rgb$$ExternalSyntheticLambda0 oetfFunc;
    public final DoubleFunction oetfOrig;
    public final float[] primaries;
    public final TransferParameters transferParameters;
    public final float[] transform;
    public final WhitePoint whitePoint;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static float area(float[] fArr) {
            if (fArr.length < 6) {
                return 0.0f;
            }
            float f = fArr[0];
            float f2 = fArr[1];
            float f3 = fArr[2];
            float f4 = fArr[3];
            float f5 = fArr[4];
            float f6 = fArr[5];
            float f7 = (((((f3 * f6) + ((f2 * f5) + (f * f4))) - (f4 * f5)) - (f2 * f3)) - (f * f6)) * 0.5f;
            return f7 < 0.0f ? -f7 : f7;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Rgb(java.lang.String r16, float[] r17, androidx.compose.ui.graphics.colorspace.WhitePoint r18, final androidx.compose.ui.graphics.colorspace.TransferParameters r19, int r20) {
        /*
            r15 = this;
            r9 = r19
            double r0 = r9.gamma
            r2 = -4609434218613702656(0xc008000000000000, double:-3.0)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto Lc
            r4 = 1
            goto Ld
        Lc:
            r4 = 0
        Ld:
            r5 = -4611686018427387904(0xc000000000000000, double:-2.0)
            r7 = 0
            double r10 = r9.f
            double r12 = r9.e
            if (r4 == 0) goto L1f
            androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0 r4 = new androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0
            r14 = 4
            r4.<init>()
        L1d:
            r14 = r4
            goto L40
        L1f:
            int r4 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r4 != 0) goto L2a
            androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0 r4 = new androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0
            r14 = 5
            r4.<init>()
            goto L1d
        L2a:
            int r4 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r4 != 0) goto L39
            int r4 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r4 != 0) goto L39
            androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0 r4 = new androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0
            r14 = 6
            r4.<init>()
            goto L1d
        L39:
            androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0 r4 = new androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0
            r14 = 7
            r4.<init>()
            goto L1d
        L40:
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 != 0) goto L4c
            androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0 r0 = new androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0
            r1 = 0
            r0.<init>()
        L4a:
            r6 = r0
            goto L6d
        L4c:
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 != 0) goto L57
            androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0 r0 = new androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0
            r1 = 1
            r0.<init>()
            goto L4a
        L57:
            int r0 = (r12 > r7 ? 1 : (r12 == r7 ? 0 : -1))
            if (r0 != 0) goto L66
            int r0 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r0 != 0) goto L66
            androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0 r0 = new androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0
            r1 = 2
            r0.<init>()
            goto L4a
        L66:
            androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0 r0 = new androidx.compose.ui.graphics.colorspace.Rgb$Companion$$ExternalSyntheticLambda0
            r1 = 3
            r0.<init>()
            goto L4a
        L6d:
            r8 = 1065353216(0x3f800000, float:1.0)
            r4 = 0
            r7 = 0
            r0 = r15
            r1 = r16
            r2 = r17
            r3 = r18
            r5 = r14
            r9 = r19
            r10 = r20
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Rgb.<init>(java.lang.String, float[], androidx.compose.ui.graphics.colorspace.WhitePoint, androidx.compose.ui.graphics.colorspace.TransferParameters, int):void");
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Rgb.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        Rgb rgb = (Rgb) obj;
        if (Float.compare(rgb.min, this.min) != 0 || Float.compare(rgb.max, this.max) != 0 || !Intrinsics.areEqual(this.whitePoint, rgb.whitePoint) || !Arrays.equals(this.primaries, rgb.primaries)) {
            return false;
        }
        TransferParameters transferParameters = rgb.transferParameters;
        TransferParameters transferParameters2 = this.transferParameters;
        if (transferParameters2 != null) {
            return Intrinsics.areEqual(transferParameters2, transferParameters);
        }
        if (transferParameters == null) {
            return true;
        }
        if (Intrinsics.areEqual(this.oetfOrig, rgb.oetfOrig)) {
            return Intrinsics.areEqual(this.eotfOrig, rgb.eotfOrig);
        }
        return false;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMaxValue(int i) {
        return this.max;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float getMinValue(int i) {
        return this.min;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final int hashCode() {
        int hashCode = (Arrays.hashCode(this.primaries) + ((this.whitePoint.hashCode() + (super.hashCode() * 31)) * 31)) * 31;
        float f = this.min;
        int floatToIntBits = (hashCode + (f == 0.0f ? 0 : Float.floatToIntBits(f))) * 31;
        float f2 = this.max;
        int floatToIntBits2 = (floatToIntBits + (f2 == 0.0f ? 0 : Float.floatToIntBits(f2))) * 31;
        TransferParameters transferParameters = this.transferParameters;
        int hashCode2 = floatToIntBits2 + (transferParameters != null ? transferParameters.hashCode() : 0);
        if (transferParameters != null) {
            return hashCode2;
        }
        return this.eotfOrig.hashCode() + ((this.oetfOrig.hashCode() + (hashCode2 * 31)) * 31);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final boolean isSrgb() {
        return this.isSrgb;
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final long toXy$ui_graphics_release(float f, float f2, float f3) {
        double d = f;
        Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda0 = this.eotfFunc;
        float invoke = (float) rgb$$ExternalSyntheticLambda0.invoke(d);
        float invoke2 = (float) rgb$$ExternalSyntheticLambda0.invoke(f2);
        float invoke3 = (float) rgb$$ExternalSyntheticLambda0.invoke(f3);
        float[] fArr = this.transform;
        if (fArr.length < 9) {
            return 0L;
        }
        float f4 = (fArr[6] * invoke3) + (fArr[3] * invoke2) + (fArr[0] * invoke);
        float f5 = (fArr[7] * invoke3) + (fArr[4] * invoke2) + (fArr[1] * invoke);
        return (Float.floatToRawIntBits(f4) << 32) | (4294967295L & Float.floatToRawIntBits(f5));
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    public final float toZ$ui_graphics_release(float f, float f2, float f3) {
        double d = f;
        Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda0 = this.eotfFunc;
        float invoke = (float) rgb$$ExternalSyntheticLambda0.invoke(d);
        float invoke2 = (float) rgb$$ExternalSyntheticLambda0.invoke(f2);
        float invoke3 = (float) rgb$$ExternalSyntheticLambda0.invoke(f3);
        float[] fArr = this.transform;
        return (fArr[8] * invoke3) + (fArr[5] * invoke2) + (fArr[2] * invoke);
    }

    @Override // androidx.compose.ui.graphics.colorspace.ColorSpace
    /* renamed from: xyzaToColor-JlNiLsg$ui_graphics_release */
    public final long mo403xyzaToColorJlNiLsg$ui_graphics_release(float f, float f2, float f3, float f4, ColorSpace colorSpace) {
        float[] fArr = this.inverseTransform;
        float f5 = (fArr[6] * f3) + (fArr[3] * f2) + (fArr[0] * f);
        float f6 = (fArr[7] * f3) + (fArr[4] * f2) + (fArr[1] * f);
        float f7 = (fArr[8] * f3) + (fArr[5] * f2) + (fArr[2] * f);
        Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda0 = this.oetfFunc;
        return ColorKt.Color((float) rgb$$ExternalSyntheticLambda0.invoke(f5), (float) rgb$$ExternalSyntheticLambda0.invoke(f6), (float) rgb$$ExternalSyntheticLambda0.invoke(f7), f4, colorSpace);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x01ef, code lost:
    
        if ((((r25 - r13) * r9) - ((r3 - r16) * r11)) >= 0.0f) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Rgb(java.lang.String r33, float[] r34, androidx.compose.ui.graphics.colorspace.WhitePoint r35, float[] r36, androidx.compose.ui.graphics.colorspace.DoubleFunction r37, androidx.compose.ui.graphics.colorspace.DoubleFunction r38, float r39, float r40, androidx.compose.ui.graphics.colorspace.TransferParameters r41, int r42) {
        /*
            Method dump skipped, instructions count: 686
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Rgb.<init>(java.lang.String, float[], androidx.compose.ui.graphics.colorspace.WhitePoint, float[], androidx.compose.ui.graphics.colorspace.DoubleFunction, androidx.compose.ui.graphics.colorspace.DoubleFunction, float, float, androidx.compose.ui.graphics.colorspace.TransferParameters, int):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Rgb(java.lang.String r18, float[] r19, androidx.compose.ui.graphics.colorspace.WhitePoint r20, final double r21, float r23, float r24, int r25) {
        /*
            r17 = this;
            r1 = r21
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda2 r3 = androidx.compose.ui.graphics.colorspace.Rgb.DoubleIdentity
            if (r0 != 0) goto Lc
            r11 = r3
            goto L13
        Lc:
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda3 r4 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda3
            r5 = 0
            r4.<init>()
            r11 = r4
        L13:
            if (r0 != 0) goto L17
        L15:
            r12 = r3
            goto L1e
        L17:
            androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda3 r3 = new androidx.compose.ui.graphics.colorspace.Rgb$$ExternalSyntheticLambda3
            r0 = 1
            r3.<init>()
            goto L15
        L1e:
            androidx.compose.ui.graphics.colorspace.TransferParameters r15 = new androidx.compose.ui.graphics.colorspace.TransferParameters
            r7 = 0
            r9 = 0
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r5 = 0
            r0 = r15
            r1 = r21
            r0.<init>(r1, r3, r5, r7, r9)
            r10 = 0
            r6 = r17
            r7 = r18
            r8 = r19
            r9 = r20
            r13 = r23
            r14 = r24
            r16 = r25
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Rgb.<init>(java.lang.String, float[], androidx.compose.ui.graphics.colorspace.WhitePoint, double, float, float, int):void");
    }
}

package androidx.compose.ui.graphics.colorspace;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class Connector {
    public final ColorSpace destination;
    public final float[] transform;
    public final ColorSpace transformDestination;
    public final ColorSpace transformSource;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RgbConnector extends Connector {
        public final Rgb mDestination;
        public final Rgb mSource;
        public final float[] mTransform;

        public RgbConnector(Rgb rgb, Rgb rgb2) {
            super(rgb2, rgb, rgb2, null);
            float[] mul3x3;
            this.mSource = rgb;
            this.mDestination = rgb2;
            WhitePoint whitePoint = rgb2.whitePoint;
            WhitePoint whitePoint2 = rgb.whitePoint;
            boolean compare = ColorSpaceKt.compare(whitePoint2, whitePoint);
            float[] fArr = rgb.transform;
            float[] fArr2 = rgb2.inverseTransform;
            if (compare) {
                mul3x3 = ColorSpaceKt.mul3x3(fArr2, fArr);
            } else {
                float[] xyz$ui_graphics_release = whitePoint2.toXyz$ui_graphics_release();
                WhitePoint whitePoint3 = rgb2.whitePoint;
                float[] xyz$ui_graphics_release2 = whitePoint3.toXyz$ui_graphics_release();
                WhitePoint whitePoint4 = Illuminant.D50;
                boolean compare2 = ColorSpaceKt.compare(whitePoint2, whitePoint4);
                float[] fArr3 = Illuminant.D50Xyz;
                float[] fArr4 = Adaptation.Bradford.transform;
                mul3x3 = ColorSpaceKt.mul3x3(ColorSpaceKt.compare(whitePoint3, whitePoint4) ? fArr2 : ColorSpaceKt.inverse3x3(ColorSpaceKt.mul3x3(ColorSpaceKt.chromaticAdaptation(fArr4, xyz$ui_graphics_release2, Arrays.copyOf(fArr3, 3)), rgb2.transform)), compare2 ? fArr : ColorSpaceKt.mul3x3(ColorSpaceKt.chromaticAdaptation(fArr4, xyz$ui_graphics_release, Arrays.copyOf(fArr3, 3)), fArr));
            }
            this.mTransform = mul3x3;
        }

        @Override // androidx.compose.ui.graphics.colorspace.Connector
        /* renamed from: transformToColor-l2rxGTc$ui_graphics_release */
        public final long mo405transformToColorl2rxGTc$ui_graphics_release(long j) {
            float m368getRedimpl = Color.m368getRedimpl(j);
            float m367getGreenimpl = Color.m367getGreenimpl(j);
            float m365getBlueimpl = Color.m365getBlueimpl(j);
            float m364getAlphaimpl = Color.m364getAlphaimpl(j);
            Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda0 = this.mSource.eotfFunc;
            float invoke = (float) rgb$$ExternalSyntheticLambda0.invoke(m368getRedimpl);
            float invoke2 = (float) rgb$$ExternalSyntheticLambda0.invoke(m367getGreenimpl);
            float invoke3 = (float) rgb$$ExternalSyntheticLambda0.invoke(m365getBlueimpl);
            float[] fArr = this.mTransform;
            float f = (fArr[6] * invoke3) + (fArr[3] * invoke2) + (fArr[0] * invoke);
            float f2 = (fArr[7] * invoke3) + (fArr[4] * invoke2) + (fArr[1] * invoke);
            float f3 = (fArr[8] * invoke3) + (fArr[5] * invoke2) + (fArr[2] * invoke);
            Rgb rgb = this.mDestination;
            float invoke4 = (float) rgb.oetfFunc.invoke(f);
            Rgb$$ExternalSyntheticLambda0 rgb$$ExternalSyntheticLambda02 = rgb.oetfFunc;
            return ColorKt.Color(invoke4, (float) rgb$$ExternalSyntheticLambda02.invoke(f2), (float) rgb$$ExternalSyntheticLambda02.invoke(f3), m364getAlphaimpl, rgb);
        }
    }

    public Connector(ColorSpace colorSpace, ColorSpace colorSpace2, ColorSpace colorSpace3, float[] fArr) {
        this.destination = colorSpace;
        this.transformSource = colorSpace2;
        this.transformDestination = colorSpace3;
        this.transform = fArr;
    }

    /* renamed from: transformToColor-l2rxGTc$ui_graphics_release, reason: not valid java name */
    public long mo405transformToColorl2rxGTc$ui_graphics_release(long j) {
        float m368getRedimpl = Color.m368getRedimpl(j);
        float m367getGreenimpl = Color.m367getGreenimpl(j);
        float m365getBlueimpl = Color.m365getBlueimpl(j);
        float m364getAlphaimpl = Color.m364getAlphaimpl(j);
        ColorSpace colorSpace = this.transformSource;
        long xy$ui_graphics_release = colorSpace.toXy$ui_graphics_release(m368getRedimpl, m367getGreenimpl, m365getBlueimpl);
        float intBitsToFloat = Float.intBitsToFloat((int) (xy$ui_graphics_release >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (xy$ui_graphics_release & 4294967295L));
        float z$ui_graphics_release = colorSpace.toZ$ui_graphics_release(m368getRedimpl, m367getGreenimpl, m365getBlueimpl);
        float[] fArr = this.transform;
        if (fArr != null) {
            intBitsToFloat *= fArr[0];
            intBitsToFloat2 *= fArr[1];
            z$ui_graphics_release *= fArr[2];
        }
        float f = intBitsToFloat;
        float f2 = intBitsToFloat2;
        return this.transformDestination.mo403xyzaToColorJlNiLsg$ui_graphics_release(f, f2, z$ui_graphics_release, m364getAlphaimpl, this.destination);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Connector(androidx.compose.ui.graphics.colorspace.ColorSpace r12, androidx.compose.ui.graphics.colorspace.ColorSpace r13, int r14) {
        /*
            r11 = this;
            r0 = 2
            r1 = 1
            r2 = 0
            r3 = 3
            long r4 = r12.model
            long r6 = androidx.compose.ui.graphics.colorspace.ColorModel.Rgb
            boolean r4 = androidx.compose.ui.graphics.colorspace.ColorModel.m402equalsimpl0(r4, r6)
            if (r4 == 0) goto L13
            androidx.compose.ui.graphics.colorspace.ColorSpace r4 = androidx.compose.ui.graphics.colorspace.ColorSpaceKt.adapt$default(r12)
            goto L14
        L13:
            r4 = r12
        L14:
            long r8 = r13.model
            boolean r5 = androidx.compose.ui.graphics.colorspace.ColorModel.m402equalsimpl0(r8, r6)
            if (r5 == 0) goto L21
            androidx.compose.ui.graphics.colorspace.ColorSpace r5 = androidx.compose.ui.graphics.colorspace.ColorSpaceKt.adapt$default(r13)
            goto L22
        L21:
            r5 = r13
        L22:
            r8 = 0
            if (r14 != r3) goto L69
            long r9 = r12.model
            boolean r14 = androidx.compose.ui.graphics.colorspace.ColorModel.m402equalsimpl0(r9, r6)
            long r9 = r13.model
            boolean r6 = androidx.compose.ui.graphics.colorspace.ColorModel.m402equalsimpl0(r9, r6)
            if (r14 == 0) goto L36
            if (r6 == 0) goto L36
            goto L69
        L36:
            if (r14 != 0) goto L3a
            if (r6 == 0) goto L69
        L3a:
            if (r14 == 0) goto L3d
            goto L3e
        L3d:
            r12 = r13
        L3e:
            androidx.compose.ui.graphics.colorspace.Rgb r12 = (androidx.compose.ui.graphics.colorspace.Rgb) r12
            float[] r7 = androidx.compose.ui.graphics.colorspace.Illuminant.D50Xyz
            androidx.compose.ui.graphics.colorspace.WhitePoint r12 = r12.whitePoint
            if (r14 == 0) goto L4b
            float[] r14 = r12.toXyz$ui_graphics_release()
            goto L4c
        L4b:
            r14 = r7
        L4c:
            if (r6 == 0) goto L52
            float[] r7 = r12.toXyz$ui_graphics_release()
        L52:
            r12 = r14[r2]
            r6 = r7[r2]
            float r12 = r12 / r6
            r6 = r14[r1]
            r8 = r7[r1]
            float r6 = r6 / r8
            r14 = r14[r0]
            r7 = r7[r0]
            float r14 = r14 / r7
            float[] r8 = new float[r3]
            r8[r2] = r12
            r8[r1] = r6
            r8[r0] = r14
        L69:
            r11.<init>(r13, r4, r5, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.colorspace.Connector.<init>(androidx.compose.ui.graphics.colorspace.ColorSpace, androidx.compose.ui.graphics.colorspace.ColorSpace, int):void");
    }
}

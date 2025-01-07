package androidx.compose.animation.core;

import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ArcSpline {
    public final Arc[][] arcs;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Arc {
        public final float arcDistance;
        public final float arcVelocity;
        public final float ellipseA;
        public final float ellipseB;
        public final float ellipseCenterX;
        public final float ellipseCenterY;
        public final boolean isLinear;
        public final float[] lut;
        public final float oneOverDeltaTime;
        public final float time1;
        public final float time2;
        public float tmpCosAngle;
        public float tmpSinAngle;
        public final float vertical;
        public final float x1;
        public final float x2;
        public final float y1;
        public final float y2;

        public Arc(float f, float f2, float f3, float f4, float f5, float f6, int i) {
            float f7;
            this.time1 = f;
            this.time2 = f2;
            this.x1 = f3;
            this.y1 = f4;
            this.x2 = f5;
            this.y2 = f6;
            float f8 = f5 - f3;
            float f9 = f6 - f4;
            boolean z = true;
            boolean z2 = i == 1 || (i == 4 ? f9 > 0.0f : !(i != 5 || f9 >= 0.0f));
            float f10 = z2 ? -1.0f : 1.0f;
            this.vertical = f10;
            float f11 = 1 / (f2 - f);
            this.oneOverDeltaTime = f11;
            this.lut = new float[101];
            boolean z3 = i == 3;
            if (z3 || Math.abs(f8) < 0.001f || Math.abs(f9) < 0.001f) {
                float hypot = (float) Math.hypot(f9, f8);
                this.arcDistance = hypot;
                this.arcVelocity = hypot * f11;
                this.ellipseCenterX = f8 * f11;
                this.ellipseCenterY = f9 * f11;
                this.ellipseA = Float.NaN;
                this.ellipseB = Float.NaN;
            } else {
                this.ellipseA = f8 * f10;
                this.ellipseB = f9 * (-f10);
                this.ellipseCenterX = z2 ? f5 : f3;
                this.ellipseCenterY = z2 ? f4 : f6;
                float f12 = f5 - f3;
                float f13 = f4 - f6;
                float[] fArr = ArcSplineKt.OurPercentCache;
                int i2 = 90;
                float f14 = 90;
                float f15 = f13;
                float f16 = 0.0f;
                float f17 = 0.0f;
                int i3 = 1;
                while (true) {
                    double radians = (float) Math.toRadians((i3 * 90.0d) / i2);
                    float sin = ((float) Math.sin(radians)) * f12;
                    float cos = ((float) Math.cos(radians)) * f13;
                    f7 = f14;
                    f16 += (float) Math.hypot(sin - f17, cos - f15);
                    fArr[i3] = f16;
                    i2 = 90;
                    if (i3 == 90) {
                        break;
                    }
                    i3++;
                    f15 = cos;
                    f14 = f7;
                    f17 = sin;
                }
                this.arcDistance = f16;
                int i4 = 1;
                while (true) {
                    fArr[i4] = fArr[i4] / f16;
                    if (i4 == 90) {
                        break;
                    } else {
                        i4++;
                    }
                }
                float[] fArr2 = this.lut;
                int length = fArr2.length;
                for (int i5 = 0; i5 < length; i5++) {
                    float f18 = i5 / 100.0f;
                    int binarySearch = Arrays.binarySearch(fArr, 0, 91, f18);
                    if (binarySearch >= 0) {
                        fArr2[i5] = binarySearch / f7;
                    } else if (binarySearch == -1) {
                        fArr2[i5] = 0.0f;
                    } else {
                        int i6 = -binarySearch;
                        int i7 = i6 - 2;
                        float f19 = i7;
                        float f20 = fArr[i7];
                        fArr2[i5] = (((f18 - f20) / (fArr[i6 - 1] - f20)) + f19) / f7;
                    }
                }
                this.arcVelocity = this.arcDistance * this.oneOverDeltaTime;
                z = z3;
            }
            this.isLinear = z;
        }

        public final float calcDX() {
            float f = this.ellipseA * this.tmpCosAngle;
            return f * this.vertical * (this.arcVelocity / ((float) Math.hypot(f, (-this.ellipseB) * this.tmpSinAngle)));
        }

        public final float calcDY() {
            float f = this.ellipseA * this.tmpCosAngle;
            float f2 = (-this.ellipseB) * this.tmpSinAngle;
            return f2 * this.vertical * (this.arcVelocity / ((float) Math.hypot(f, f2)));
        }

        public final void setPoint(float f) {
            float f2 = (this.vertical == -1.0f ? this.time2 - f : f - this.time1) * this.oneOverDeltaTime;
            float f3 = 0.0f;
            if (f2 > 0.0f) {
                f3 = 1.0f;
                if (f2 < 1.0f) {
                    float f4 = f2 * 100;
                    int i = (int) f4;
                    float[] fArr = this.lut;
                    float f5 = fArr[i];
                    f3 = AndroidFlingSpline$$ExternalSyntheticOutline0.m(fArr[i + 1], f5, f4 - i, f5);
                }
            }
            double d = f3 * 1.5707964f;
            this.tmpSinAngle = (float) Math.sin(d);
            this.tmpCosAngle = (float) Math.cos(d);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0020, code lost:
    
        if (r8 != 5) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0025, code lost:
    
        if (r6 == 1) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0043 A[LOOP:1: B:13:0x0041->B:14:0x0043, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ArcSpline(int[] r27, float[] r28, float[][] r29) {
        /*
            r26 = this;
            r0 = r28
            r26.<init>()
            int r1 = r0.length
            r2 = 1
            int r1 = r1 - r2
            androidx.compose.animation.core.ArcSpline$Arc[][] r3 = new androidx.compose.animation.core.ArcSpline.Arc[r1][]
            r4 = 0
            r6 = r2
            r7 = r6
            r5 = r4
        Le:
            if (r5 >= r1) goto L74
            r8 = r27[r5]
            r9 = 2
            r10 = 3
            if (r8 == 0) goto L23
            if (r8 == r2) goto L2c
            if (r8 == r9) goto L2a
            if (r8 == r10) goto L25
            r10 = 4
            if (r8 == r10) goto L23
            r10 = 5
            if (r8 == r10) goto L23
            goto L2e
        L23:
            r7 = r10
            goto L2e
        L25:
            if (r6 != r2) goto L2c
            goto L2a
        L28:
            r7 = r6
            goto L2e
        L2a:
            r6 = r9
            goto L28
        L2c:
            r6 = r2
            goto L28
        L2e:
            r8 = r29[r5]
            int r18 = r5 + 1
            r19 = r29[r18]
            r20 = r0[r5]
            r21 = r0[r18]
            int r10 = r8.length
            int r10 = r10 / r9
            int r11 = r8.length
            int r11 = r11 % r9
            int r9 = r11 + r10
            androidx.compose.animation.core.ArcSpline$Arc[] r15 = new androidx.compose.animation.core.ArcSpline.Arc[r9]
            r14 = r4
        L41:
            if (r14 >= r9) goto L6d
            int r10 = r14 * 2
            androidx.compose.animation.core.ArcSpline$Arc r22 = new androidx.compose.animation.core.ArcSpline$Arc
            r13 = r8[r10]
            int r11 = r10 + 1
            r16 = r8[r11]
            r17 = r19[r10]
            r23 = r19[r11]
            r10 = r22
            r11 = r20
            r12 = r21
            r24 = r14
            r14 = r16
            r25 = r15
            r15 = r17
            r16 = r23
            r17 = r7
            r10.<init>(r11, r12, r13, r14, r15, r16, r17)
            r25[r24] = r22
            int r14 = r24 + 1
            r15 = r25
            goto L41
        L6d:
            r25 = r15
            r3[r5] = r25
            r5 = r18
            goto Le
        L74:
            r5 = r26
            r5.arcs = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.ArcSpline.<init>(int[], float[], float[][]):void");
    }
}

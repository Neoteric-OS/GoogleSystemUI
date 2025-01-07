package com.google.ux.material.libmonet.hct;

import com.google.ux.material.libmonet.utils.ColorUtils;
import com.google.ux.material.libmonet.utils.MathUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Hct {
    public final int argb;
    public final double chroma;
    public final double hue;
    public final double tone;

    public Hct(int i) {
        this.argb = i;
        ViewingConditions viewingConditions = ViewingConditions.DEFAULT;
        int i2 = i & 255;
        double linearized = ColorUtils.linearized((16711680 & i) >> 16);
        double linearized2 = ColorUtils.linearized((65280 & i) >> 8);
        double linearized3 = ColorUtils.linearized(i2);
        double d = (0.18051042d * linearized3) + (0.35762064d * linearized2) + (0.41233895d * linearized);
        double d2 = (0.0722d * linearized3) + (0.7152d * linearized2) + (0.2126d * linearized);
        double d3 = (linearized3 * 0.95034478d) + (linearized2 * 0.11916382d) + (linearized * 0.01932141d);
        double[][] dArr = Cam16.XYZ_TO_CAM16RGB;
        double[] dArr2 = dArr[0];
        double d4 = (dArr2[2] * d3) + (dArr2[1] * d2) + (dArr2[0] * d);
        double[] dArr3 = dArr[1];
        double d5 = (dArr3[2] * d3) + (dArr3[1] * d2) + (dArr3[0] * d);
        double[] dArr4 = dArr[2];
        double d6 = (d3 * dArr4[2]) + (d2 * dArr4[1]) + (d * dArr4[0]);
        double[] dArr5 = viewingConditions.rgbD;
        double d7 = dArr5[0] * d4;
        double d8 = dArr5[1] * d5;
        double d9 = dArr5[2] * d6;
        double abs = Math.abs(d7);
        double d10 = viewingConditions.fl;
        double pow = Math.pow((abs * d10) / 100.0d, 0.42d);
        double pow2 = Math.pow((Math.abs(d8) * d10) / 100.0d, 0.42d);
        double pow3 = Math.pow((Math.abs(d9) * d10) / 100.0d, 0.42d);
        double signum = ((Math.signum(d7) * 400.0d) * pow) / (pow + 27.13d);
        double signum2 = ((Math.signum(d8) * 400.0d) * pow2) / (pow2 + 27.13d);
        double signum3 = ((Math.signum(d9) * 400.0d) * pow3) / (pow3 + 27.13d);
        double d11 = ((((-12.0d) * signum2) + (signum * 11.0d)) + signum3) / 11.0d;
        double d12 = ((signum + signum2) - (signum3 * 2.0d)) / 9.0d;
        double d13 = signum2 * 20.0d;
        double d14 = ((21.0d * signum3) + ((signum * 20.0d) + d13)) / 20.0d;
        double d15 = (((signum * 40.0d) + d13) + signum3) / 20.0d;
        double degrees = Math.toDegrees(Math.atan2(d12, d11));
        if (degrees < 0.0d) {
            degrees += 360.0d;
        } else if (degrees >= 360.0d) {
            degrees -= 360.0d;
        }
        double radians = Math.toRadians(degrees);
        double d16 = d15 * viewingConditions.nbb;
        double d17 = viewingConditions.aw;
        double d18 = viewingConditions.z;
        double d19 = viewingConditions.c;
        double pow4 = (Math.pow(d16 / d17, d18 * d19) * 100.0d) / 100.0d;
        Math.sqrt(pow4);
        double d20 = d17 + 4.0d;
        double d21 = degrees;
        double pow5 = Math.pow((Math.hypot(d11, d12) * (((((Math.cos(Math.toRadians(degrees < 20.14d ? 360.0d + degrees : degrees) + 2.0d) + 3.8d) * 0.25d) * 3846.153846153846d) * viewingConditions.nc) * viewingConditions.ncb)) / (d14 + 0.305d), 0.9d) * Math.pow(1.64d - Math.pow(0.29d, viewingConditions.n), 0.73d);
        double sqrt = Math.sqrt(pow4) * pow5;
        double d22 = viewingConditions.flRoot * sqrt;
        Math.sqrt((pow5 * d19) / d20);
        Math.log1p(d22 * 0.0228d);
        Math.cos(radians);
        Math.sin(radians);
        this.hue = d21;
        this.chroma = sqrt;
        this.tone = (ColorUtils.labF(MathUtils.matrixMultiply(new double[]{ColorUtils.linearized((i >> 16) & 255), ColorUtils.linearized((i >> 8) & 255), ColorUtils.linearized(i2)}, ColorUtils.SRGB_TO_XYZ)[1] / 100.0d) * 116.0d) - 16.0d;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x027e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.google.ux.material.libmonet.hct.Hct from(double r48, double r50, double r52) {
        /*
            Method dump skipped, instructions count: 1024
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ux.material.libmonet.hct.Hct.from(double, double, double):com.google.ux.material.libmonet.hct.Hct");
    }
}

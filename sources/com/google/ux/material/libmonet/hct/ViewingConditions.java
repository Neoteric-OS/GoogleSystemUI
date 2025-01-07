package com.google.ux.material.libmonet.hct;

import com.google.ux.material.libmonet.utils.ColorUtils;
import com.google.ux.material.libmonet.utils.MathUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ViewingConditions {
    public static final ViewingConditions DEFAULT;
    public final double aw;
    public final double c;
    public final double fl;
    public final double flRoot;
    public final double n;
    public final double nbb;
    public final double nc;
    public final double ncb;
    public final double[] rgbD;
    public final double z;

    static {
        double[] dArr = ColorUtils.WHITE_POINT_D65;
        double yFromLstar = (ColorUtils.yFromLstar(50.0d) * 63.66197723675813d) / 100.0d;
        double max = Math.max(0.1d, 50.0d);
        double[][] dArr2 = Cam16.XYZ_TO_CAM16RGB;
        double d = dArr[0];
        double[] dArr3 = dArr2[0];
        double d2 = dArr3[0] * d;
        double d3 = dArr[1];
        double d4 = (dArr3[1] * d3) + d2;
        double d5 = dArr[2];
        double d6 = (dArr3[2] * d5) + d4;
        double[] dArr4 = dArr2[1];
        double d7 = (dArr4[2] * d5) + (dArr4[1] * d3) + (dArr4[0] * d);
        double[] dArr5 = dArr2[2];
        double d8 = (d5 * dArr5[2]) + (d3 * dArr5[1]) + (d * dArr5[0]);
        double lerp = MathUtils.lerp(0.59d, 0.69d, 0.9999999999999998d);
        double exp = (1.0d - (Math.exp(((-yFromLstar) - 42.0d) / 92.0d) * 0.2777777777777778d)) * 1.0d;
        if (exp < 0.0d) {
            exp = 0.0d;
        } else if (exp > 1.0d) {
            exp = 1.0d;
        }
        double[] dArr6 = {(((100.0d / d6) * exp) + 1.0d) - exp, (((100.0d / d7) * exp) + 1.0d) - exp, (((100.0d / d8) * exp) + 1.0d) - exp};
        double d9 = 5.0d * yFromLstar;
        double d10 = 1.0d / (d9 + 1.0d);
        double d11 = d10 * d10 * d10 * d10;
        double d12 = 1.0d - d11;
        double cbrt = (Math.cbrt(d9) * 0.1d * d12 * d12) + (d11 * yFromLstar);
        double yFromLstar2 = ColorUtils.yFromLstar(max) / dArr[1];
        double sqrt = Math.sqrt(yFromLstar2) + 1.48d;
        double pow = 0.725d / Math.pow(yFromLstar2, 0.2d);
        double[] dArr7 = {Math.pow(((dArr6[0] * cbrt) * d6) / 100.0d, 0.42d), Math.pow(((dArr6[1] * cbrt) * d7) / 100.0d, 0.42d), Math.pow(((dArr6[2] * cbrt) * d8) / 100.0d, 0.42d)};
        double d13 = dArr7[0];
        double d14 = (d13 * 400.0d) / (d13 + 27.13d);
        double d15 = dArr7[1];
        double d16 = (d15 * 400.0d) / (d15 + 27.13d);
        double d17 = dArr7[2];
        double[] dArr8 = {d14, d16, (400.0d * d17) / (d17 + 27.13d)};
        DEFAULT = new ViewingConditions(yFromLstar2, ((dArr8[2] * 0.05d) + (dArr8[0] * 2.0d) + dArr8[1]) * pow, pow, pow, lerp, 1.0d, dArr6, cbrt, Math.pow(cbrt, 0.25d), sqrt);
    }

    public ViewingConditions(double d, double d2, double d3, double d4, double d5, double d6, double[] dArr, double d7, double d8, double d9) {
        this.n = d;
        this.aw = d2;
        this.nbb = d3;
        this.ncb = d4;
        this.c = d5;
        this.nc = d6;
        this.rgbD = dArr;
        this.fl = d7;
        this.flRoot = d8;
        this.z = d9;
    }
}

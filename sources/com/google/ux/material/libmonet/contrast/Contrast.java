package com.google.ux.material.libmonet.contrast;

import com.google.ux.material.libmonet.utils.ColorUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Contrast {
    public static double darker(double d, double d2) {
        if (d >= 0.0d && d <= 100.0d) {
            double yFromLstar = ColorUtils.yFromLstar(d);
            double d3 = ((yFromLstar + 5.0d) / d2) - 5.0d;
            if (d3 >= 0.0d && d3 <= 100.0d) {
                double ratioOfYs = ratioOfYs(yFromLstar, d3);
                double abs = Math.abs(ratioOfYs - d2);
                if (ratioOfYs < d2 && abs > 0.04d) {
                    return -1.0d;
                }
                double labF = ((ColorUtils.labF(d3 / 100.0d) * 116.0d) - 16.0d) - 0.4d;
                if (labF >= 0.0d && labF <= 100.0d) {
                    return labF;
                }
            }
        }
        return -1.0d;
    }

    public static double lighter(double d, double d2) {
        if (d >= 0.0d && d <= 100.0d) {
            double yFromLstar = ColorUtils.yFromLstar(d);
            double d3 = ((yFromLstar + 5.0d) * d2) - 5.0d;
            if (d3 >= 0.0d && d3 <= 100.0d) {
                double ratioOfYs = ratioOfYs(d3, yFromLstar);
                double abs = Math.abs(ratioOfYs - d2);
                if (ratioOfYs < d2 && abs > 0.04d) {
                    return -1.0d;
                }
                double labF = ((ColorUtils.labF(d3 / 100.0d) * 116.0d) - 16.0d) + 0.4d;
                if (labF >= 0.0d && labF <= 100.0d) {
                    return labF;
                }
            }
        }
        return -1.0d;
    }

    public static double ratioOfTones(double d, double d2) {
        return ratioOfYs(ColorUtils.yFromLstar(d), ColorUtils.yFromLstar(d2));
    }

    public static double ratioOfYs(double d, double d2) {
        double max = Math.max(d, d2);
        if (max != d2) {
            d = d2;
        }
        return (max + 5.0d) / (d + 5.0d);
    }
}

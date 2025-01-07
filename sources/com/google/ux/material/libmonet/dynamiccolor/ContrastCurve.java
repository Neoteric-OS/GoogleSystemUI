package com.google.ux.material.libmonet.dynamiccolor;

import com.google.ux.material.libmonet.utils.MathUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ContrastCurve {
    public final double high;
    public final double low;
    public final double medium;
    public final double normal;

    public ContrastCurve(double d, double d2, double d3, double d4) {
        this.low = d;
        this.normal = d2;
        this.medium = d3;
        this.high = d4;
    }

    public final double get(double d) {
        if (d <= -1.0d) {
            return this.low;
        }
        if (d < 0.0d) {
            return MathUtils.lerp(this.low, this.normal, (d - (-1.0d)) / 1.0d);
        }
        if (d < 0.5d) {
            return MathUtils.lerp(this.normal, this.medium, (d - 0.0d) / 0.5d);
        }
        if (d >= 1.0d) {
            return this.high;
        }
        return MathUtils.lerp(this.medium, this.high, (d - 0.5d) / 0.5d);
    }
}

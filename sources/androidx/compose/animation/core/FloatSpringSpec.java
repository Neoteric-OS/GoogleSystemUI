package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FloatSpringSpec implements FloatAnimationSpec {
    public final SpringSimulation spring;
    public final float visibilityThreshold;

    public FloatSpringSpec(float f, float f2, float f3) {
        this.visibilityThreshold = f3;
        SpringSimulation springSimulation = new SpringSimulation();
        springSimulation.finalPosition = 1.0f;
        springSimulation.naturalFreq = Math.sqrt(50.0d);
        springSimulation.dampingRatio = 1.0f;
        if (f < 0.0f) {
            PreconditionsKt.throwIllegalArgumentException("Damping ratio must be non-negative");
        }
        springSimulation.dampingRatio = f;
        double d = springSimulation.naturalFreq;
        if (((float) (d * d)) <= 0.0f) {
            PreconditionsKt.throwIllegalArgumentException("Spring stiffness constant must be positive.");
        }
        springSimulation.naturalFreq = Math.sqrt(f2);
        this.spring = springSimulation;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final long getDurationNanos(float f, float f2, float f3) {
        double d;
        long j;
        SpringSimulation springSimulation = this.spring;
        double d2 = springSimulation.naturalFreq;
        float f4 = (float) (d2 * d2);
        float f5 = springSimulation.dampingRatio;
        float f6 = this.visibilityThreshold;
        float f7 = (f - f2) / f6;
        float f8 = f3 / f6;
        if (f5 == 0.0f) {
            j = 9223372036854L;
        } else {
            double d3 = f4;
            double d4 = f5;
            double d5 = f8;
            double d6 = f7;
            double d7 = 1.0f;
            double sqrt = d4 * 2.0d * Math.sqrt(d3);
            double d8 = (sqrt * sqrt) - (d3 * 4.0d);
            double sqrt2 = d8 < 0.0d ? 0.0d : Math.sqrt(d8);
            double d9 = -sqrt;
            double d10 = (d9 + sqrt2) * 0.5d;
            double sqrt3 = (d8 < 0.0d ? Math.sqrt(Math.abs(d8)) : 0.0d) * 0.5d;
            double d11 = (d9 - sqrt2) * 0.5d;
            if (d6 == 0.0d && d5 == 0.0d) {
                j = 0;
            } else {
                if (d6 < 0.0d) {
                    d5 = -d5;
                }
                double abs = Math.abs(d6);
                double d12 = Double.MAX_VALUE;
                if (d4 > 1.0d) {
                    double d13 = (d10 * abs) - d5;
                    double d14 = d10 - d11;
                    double d15 = d13 / d14;
                    double d16 = abs - d15;
                    d = Math.log(Math.abs(d7 / d16)) / d10;
                    double log = Math.log(Math.abs(d7 / d15)) / d11;
                    if ((Double.doubleToRawLongBits(d) & Long.MAX_VALUE) >= 9218868437227405312L) {
                        d = log;
                    } else if ((Double.doubleToRawLongBits(log) & Long.MAX_VALUE) < 9218868437227405312L) {
                        d = Math.max(d, log);
                    }
                    double d17 = d16 * d10;
                    double log2 = Math.log(d17 / ((-d15) * d11)) / (d11 - d10);
                    if (Double.isNaN(log2) || log2 <= 0.0d) {
                        d7 = -d7;
                    } else {
                        if (log2 > 0.0d) {
                            if ((-((Math.exp(log2 * d11) * d15) + (Math.exp(d10 * log2) * d16))) < d7) {
                                d7 = -d7;
                                d = (d15 <= 0.0d || d16 >= 0.0d) ? d : 0.0d;
                            }
                        }
                        d = Math.log((-((d15 * d11) * d11)) / (d17 * d10)) / d14;
                    }
                    double d18 = d15 * d11;
                    if (Math.abs((Math.exp(d11 * d) * d18) + (Math.exp(d10 * d) * d17)) >= 1.0E-4d) {
                        int i = 0;
                        while (d12 > 0.001d && i < 100) {
                            i++;
                            double d19 = d10 * d;
                            double d20 = d11 * d;
                            double exp = d - ((((Math.exp(d20) * d15) + (Math.exp(d19) * d16)) + d7) / ((Math.exp(d20) * d18) + (Math.exp(d19) * d17)));
                            d12 = Math.abs(d - exp);
                            d = exp;
                        }
                    }
                } else if (d4 < 1.0d) {
                    double d21 = (d5 - (d10 * abs)) / sqrt3;
                    d = Math.log(d7 / Math.sqrt((d21 * d21) + (abs * abs))) / d10;
                } else {
                    double d22 = d10 * abs;
                    double d23 = d5 - d22;
                    double log3 = Math.log(Math.abs(d7 / abs)) / d10;
                    double log4 = Math.log(Math.abs(d7 / d23));
                    double d24 = log4;
                    for (int i2 = 0; i2 < 6; i2++) {
                        d24 = log4 - Math.log(Math.abs(d24 / d10));
                    }
                    double d25 = d24 / d10;
                    if ((Double.doubleToRawLongBits(log3) & Long.MAX_VALUE) >= 9218868437227405312L) {
                        log3 = d25;
                    } else if ((Double.doubleToRawLongBits(d25) & Long.MAX_VALUE) < 9218868437227405312L) {
                        log3 = Math.max(log3, d25);
                    }
                    double d26 = (-(d22 + d23)) / (d10 * d23);
                    double d27 = d10 * d26;
                    double exp2 = (Math.exp(d27) * d23 * d26) + (Math.exp(d27) * abs);
                    if (Double.isNaN(d26) || d26 <= 0.0d) {
                        d7 = -d7;
                    } else if (d26 <= 0.0d || (-exp2) >= d7) {
                        log3 = (-(2.0d / d10)) - (abs / d23);
                    } else {
                        d7 = -d7;
                        log3 = (d23 >= 0.0d || abs <= 0.0d) ? log3 : 0.0d;
                    }
                    d = log3;
                    int i3 = 0;
                    while (d12 > 0.001d && i3 < 100) {
                        i3++;
                        double d28 = d10 * d;
                        double exp3 = d - (((Math.exp(d28) * ((d23 * d) + abs)) + d7) / (Math.exp(d28) * (((1 + d28) * d23) + d22)));
                        d12 = Math.abs(d - exp3);
                        d = exp3;
                    }
                }
                j = (long) (d * 1000.0d);
            }
        }
        return j * 1000000;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getEndVelocity(float f, float f2, float f3) {
        return 0.0f;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getValueFromNanos(float f, float f2, float f3, long j) {
        SpringSimulation springSimulation = this.spring;
        springSimulation.finalPosition = f2;
        return Float.intBitsToFloat((int) (springSimulation.m11updateValuesIJZedt4$animation_core_release(f, f3, j / 1000000) >> 32));
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getVelocityFromNanos(float f, float f2, float f3, long j) {
        SpringSimulation springSimulation = this.spring;
        springSimulation.finalPosition = f2;
        return Float.intBitsToFloat((int) (springSimulation.m11updateValuesIJZedt4$animation_core_release(f, f3, j / 1000000) & 4294967295L));
    }
}

package androidx.constraintlayout.core.motion.utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SpringStopEngine implements StopEngine {
    public int mBoundaryMode;
    public double mDamping;
    public float mLastTime;
    public float mMass;
    public float mPos;
    public double mStiffness;
    public float mStopThreshold;
    public double mTargetPos;
    public float mV;

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public final float getInterpolation(float f) {
        SpringStopEngine springStopEngine;
        float f2;
        SpringStopEngine springStopEngine2 = this;
        double d = f - springStopEngine2.mLastTime;
        if (d <= 0.0d) {
            springStopEngine = springStopEngine2;
            f2 = f;
        } else {
            double d2 = springStopEngine2.mStiffness;
            double d3 = springStopEngine2.mDamping;
            int sqrt = (int) ((9.0d / ((Math.sqrt(d2 / springStopEngine2.mMass) * d) * 4.0d)) + 1.0d);
            double d4 = d / sqrt;
            int i = 0;
            while (i < sqrt) {
                float f3 = springStopEngine2.mPos;
                double d5 = f3;
                double d6 = springStopEngine2.mTargetPos;
                int i2 = sqrt;
                int i3 = i;
                double d7 = (-d2) * (d5 - d6);
                float f4 = springStopEngine2.mV;
                double d8 = d2;
                double d9 = f4;
                double d10 = springStopEngine2.mMass;
                double d11 = ((((d7 - (d3 * d9)) / d10) * d4) / 2.0d) + d9;
                double d12 = ((((-((((d4 * d11) / 2.0d) + d5) - d6)) * d8) - (d11 * d3)) / d10) * d4;
                float f5 = f4 + ((float) d12);
                this.mV = f5;
                float f6 = f3 + ((float) (((d12 / 2.0d) + d9) * d4));
                this.mPos = f6;
                int i4 = this.mBoundaryMode;
                if (i4 > 0) {
                    if (f6 < 0.0f && (i4 & 1) == 1) {
                        this.mPos = -f6;
                        this.mV = -f5;
                    }
                    float f7 = this.mPos;
                    if (f7 > 1.0f && (i4 & 2) == 2) {
                        this.mPos = 2.0f - f7;
                        this.mV = -this.mV;
                    }
                }
                i = i3 + 1;
                springStopEngine2 = this;
                sqrt = i2;
                d2 = d8;
            }
            springStopEngine = springStopEngine2;
            f2 = f;
        }
        springStopEngine.mLastTime = f2;
        if (isStopped()) {
            springStopEngine.mPos = (float) springStopEngine.mTargetPos;
        }
        return springStopEngine.mPos;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public final float getVelocity() {
        return 0.0f;
    }

    @Override // androidx.constraintlayout.core.motion.utils.StopEngine
    public final boolean isStopped() {
        double d = this.mPos - this.mTargetPos;
        double d2 = this.mStiffness;
        double d3 = this.mV;
        return Math.sqrt((((d2 * d) * d) + ((d3 * d3) * ((double) this.mMass))) / d2) <= ((double) this.mStopThreshold);
    }
}

package androidx.dynamicanimation.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SpringForce {
    public double mDampedFreq;
    public double mDampingRatio;
    public double mFinalPosition;
    public double mGammaMinus;
    public double mGammaPlus;
    public boolean mInitialized;
    public final DynamicAnimation.MassState mMassState;
    public double mNaturalFreq;
    public double mValueThreshold;
    public double mVelocityThreshold;

    public SpringForce() {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mInitialized = false;
        this.mFinalPosition = Double.MAX_VALUE;
        this.mMassState = new DynamicAnimation.MassState();
    }

    public final void setDampingRatio(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        this.mDampingRatio = f;
        this.mInitialized = false;
    }

    public final void setStiffness(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        this.mNaturalFreq = Math.sqrt(f);
        this.mInitialized = false;
    }

    public final DynamicAnimation.MassState updateValues(double d, double d2, long j) {
        double cos;
        double d3;
        if (!this.mInitialized) {
            if (this.mFinalPosition == Double.MAX_VALUE) {
                throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
            }
            double d4 = this.mDampingRatio;
            if (d4 > 1.0d) {
                double d5 = this.mNaturalFreq;
                this.mGammaPlus = (Math.sqrt((d4 * d4) - 1.0d) * d5) + ((-d4) * d5);
                double d6 = this.mDampingRatio;
                double d7 = this.mNaturalFreq;
                this.mGammaMinus = ((-d6) * d7) - (Math.sqrt((d6 * d6) - 1.0d) * d7);
            } else if (d4 >= 0.0d && d4 < 1.0d) {
                this.mDampedFreq = Math.sqrt(1.0d - (d4 * d4)) * this.mNaturalFreq;
            }
            this.mInitialized = true;
        }
        double d8 = j / 1000.0d;
        double d9 = d - this.mFinalPosition;
        double d10 = this.mDampingRatio;
        if (d10 > 1.0d) {
            double d11 = this.mGammaMinus;
            double d12 = ((d11 * d9) - d2) / (d11 - this.mGammaPlus);
            double d13 = d9 - d12;
            d3 = (Math.pow(2.718281828459045d, this.mGammaPlus * d8) * d12) + (Math.pow(2.718281828459045d, d11 * d8) * d13);
            double d14 = this.mGammaMinus;
            double pow = Math.pow(2.718281828459045d, d14 * d8) * d13 * d14;
            double d15 = this.mGammaPlus;
            cos = (Math.pow(2.718281828459045d, d15 * d8) * d12 * d15) + pow;
        } else if (d10 == 1.0d) {
            double d16 = this.mNaturalFreq;
            double d17 = (d16 * d9) + d2;
            double d18 = (d17 * d8) + d9;
            double pow2 = Math.pow(2.718281828459045d, (-d16) * d8) * d18;
            double pow3 = Math.pow(2.718281828459045d, (-this.mNaturalFreq) * d8) * d18;
            double d19 = -this.mNaturalFreq;
            cos = (Math.pow(2.718281828459045d, d19 * d8) * d17) + (pow3 * d19);
            d3 = pow2;
        } else {
            double d20 = 1.0d / this.mDampedFreq;
            double d21 = this.mNaturalFreq;
            double d22 = ((d10 * d21 * d9) + d2) * d20;
            double sin = ((Math.sin(this.mDampedFreq * d8) * d22) + (Math.cos(this.mDampedFreq * d8) * d9)) * Math.pow(2.718281828459045d, (-d10) * d21 * d8);
            double d23 = this.mNaturalFreq;
            double d24 = this.mDampingRatio;
            double d25 = (-d23) * sin * d24;
            double pow4 = Math.pow(2.718281828459045d, (-d24) * d23 * d8);
            double d26 = this.mDampedFreq;
            double sin2 = Math.sin(d26 * d8) * (-d26) * d9;
            double d27 = this.mDampedFreq;
            cos = (((Math.cos(d27 * d8) * d22 * d27) + sin2) * pow4) + d25;
            d3 = sin;
        }
        float f = (float) (d3 + this.mFinalPosition);
        DynamicAnimation.MassState massState = this.mMassState;
        massState.mValue = f;
        massState.mVelocity = (float) cos;
        return massState;
    }

    public SpringForce(float f) {
        this.mNaturalFreq = Math.sqrt(1500.0d);
        this.mDampingRatio = 0.5d;
        this.mInitialized = false;
        this.mMassState = new DynamicAnimation.MassState();
        this.mFinalPosition = f;
    }
}

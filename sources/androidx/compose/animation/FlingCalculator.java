package androidx.compose.animation;

import androidx.compose.ui.unit.Density;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlingCalculator {
    public final float friction;
    public final float magicPhysicalCoefficient;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FlingInfo {
        public final float distance;
        public final long duration;
        public final float initialVelocity;

        public FlingInfo(float f, float f2, long j) {
            this.initialVelocity = f;
            this.distance = f2;
            this.duration = j;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FlingInfo)) {
                return false;
            }
            FlingInfo flingInfo = (FlingInfo) obj;
            return Float.compare(this.initialVelocity, flingInfo.initialVelocity) == 0 && Float.compare(this.distance, flingInfo.distance) == 0 && this.duration == flingInfo.duration;
        }

        public final int hashCode() {
            return Long.hashCode(this.duration) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.initialVelocity) * 31, this.distance, 31);
        }

        public final String toString() {
            return "FlingInfo(initialVelocity=" + this.initialVelocity + ", distance=" + this.distance + ", duration=" + this.duration + ')';
        }
    }

    public FlingCalculator(float f, Density density) {
        this.friction = f;
        float density2 = density.getDensity();
        float f2 = FlingCalculatorKt.DecelerationRate;
        this.magicPhysicalCoefficient = density2 * 386.0878f * 160.0f * 0.84f;
    }

    public final FlingInfo flingInfo(float f) {
        double splineDeceleration = getSplineDeceleration(f);
        double d = FlingCalculatorKt.DecelerationRate;
        double d2 = d - 1.0d;
        return new FlingInfo(f, (float) (Math.exp((d / d2) * splineDeceleration) * this.friction * this.magicPhysicalCoefficient), (long) (Math.exp(splineDeceleration / d2) * 1000.0d));
    }

    public final double getSplineDeceleration(float f) {
        float[] fArr = AndroidFlingSpline.SplinePositions;
        return Math.log((Math.abs(f) * 0.35f) / (this.friction * this.magicPhysicalCoefficient));
    }
}

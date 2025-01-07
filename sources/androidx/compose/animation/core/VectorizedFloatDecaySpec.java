package androidx.compose.animation.core;

import androidx.compose.animation.AndroidFlingSpline;
import androidx.compose.animation.FlingCalculator;
import androidx.compose.animation.FlingCalculatorKt;
import androidx.compose.animation.SplineBasedFloatDecayAnimationSpec;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class VectorizedFloatDecaySpec implements VectorizedDecayAnimationSpec {
    public final SplineBasedFloatDecayAnimationSpec floatDecaySpec;
    public AnimationVector targetVector;
    public AnimationVector valueVector;
    public AnimationVector velocityVector;

    public VectorizedFloatDecaySpec(SplineBasedFloatDecayAnimationSpec splineBasedFloatDecayAnimationSpec) {
        this.floatDecaySpec = splineBasedFloatDecayAnimationSpec;
    }

    public final AnimationVector getTargetValue(AnimationVector animationVector, AnimationVector animationVector2) {
        if (this.targetVector == null) {
            this.targetVector = animationVector.newVector$animation_core_release();
        }
        AnimationVector animationVector3 = this.targetVector;
        if (animationVector3 == null) {
            animationVector3 = null;
        }
        int i = 0;
        for (int size$animation_core_release = animationVector3.getSize$animation_core_release(); i < size$animation_core_release; size$animation_core_release = size$animation_core_release) {
            AnimationVector animationVector4 = this.targetVector;
            if (animationVector4 == null) {
                animationVector4 = null;
            }
            float f = animationVector.get$animation_core_release(i);
            float f2 = animationVector2.get$animation_core_release(i);
            FlingCalculator flingCalculator = this.floatDecaySpec.flingCalculator;
            double splineDeceleration = flingCalculator.getSplineDeceleration(f2);
            double d = FlingCalculatorKt.DecelerationRate;
            float f3 = flingCalculator.friction * flingCalculator.magicPhysicalCoefficient;
            animationVector4.set$animation_core_release(i, (Math.signum(f2) * ((float) (Math.exp((d / (d - 1.0d)) * splineDeceleration) * f3))) + f);
            i++;
        }
        AnimationVector animationVector5 = this.targetVector;
        if (animationVector5 == null) {
            return null;
        }
        return animationVector5;
    }

    public final AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2) {
        if (this.velocityVector == null) {
            this.velocityVector = animationVector.newVector$animation_core_release();
        }
        AnimationVector animationVector3 = this.velocityVector;
        if (animationVector3 == null) {
            animationVector3 = null;
        }
        int size$animation_core_release = animationVector3.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            AnimationVector animationVector4 = this.velocityVector;
            if (animationVector4 == null) {
                animationVector4 = null;
            }
            animationVector.getClass();
            long j2 = j / 1000000;
            FlingCalculator.FlingInfo flingInfo = this.floatDecaySpec.flingCalculator.flingInfo(animationVector2.get$animation_core_release(i));
            long j3 = flingInfo.duration;
            animationVector4.set$animation_core_release(i, (((Math.signum(flingInfo.initialVelocity) * AndroidFlingSpline.flingPosition(j3 > 0 ? j2 / j3 : 1.0f).velocityCoefficient) * flingInfo.distance) / j3) * 1000.0f);
        }
        AnimationVector animationVector5 = this.velocityVector;
        if (animationVector5 == null) {
            return null;
        }
        return animationVector5;
    }
}

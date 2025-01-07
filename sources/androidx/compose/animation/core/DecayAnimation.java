package androidx.compose.animation.core;

import androidx.compose.animation.AndroidFlingSpline;
import androidx.compose.animation.FlingCalculator;
import androidx.compose.animation.FlingCalculatorKt;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DecayAnimation implements Animation {
    public final VectorizedDecayAnimationSpec animationSpec;
    public final long durationNanos;
    public final AnimationVector endVelocity;
    public final Object initialValue;
    public final AnimationVector initialValueVector;
    public final AnimationVector initialVelocityVector;
    public final Object targetValue;
    public final TwoWayConverter typeConverter;

    /* JADX WARN: Type inference failed for: r11v3, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r12v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public DecayAnimation(DecayAnimationSpec decayAnimationSpec, TwoWayConverter twoWayConverter, Object obj, AnimationVector animationVector) {
        VectorizedFloatDecaySpec vectorizedFloatDecaySpec = new VectorizedFloatDecaySpec(((DecayAnimationSpecImpl) decayAnimationSpec).floatDecaySpec);
        this.animationSpec = vectorizedFloatDecaySpec;
        this.typeConverter = twoWayConverter;
        this.initialValue = obj;
        TwoWayConverterImpl twoWayConverterImpl = (TwoWayConverterImpl) twoWayConverter;
        AnimationVector animationVector2 = (AnimationVector) twoWayConverterImpl.convertToVector.invoke(obj);
        this.initialValueVector = animationVector2;
        this.initialVelocityVector = AnimationVectorsKt.copy(animationVector);
        this.targetValue = twoWayConverterImpl.convertFromVector.invoke(vectorizedFloatDecaySpec.getTargetValue(animationVector2, animationVector));
        if (vectorizedFloatDecaySpec.velocityVector == null) {
            vectorizedFloatDecaySpec.velocityVector = animationVector2.newVector$animation_core_release();
        }
        AnimationVector animationVector3 = vectorizedFloatDecaySpec.velocityVector;
        int size$animation_core_release = (animationVector3 == null ? null : animationVector3).getSize$animation_core_release();
        long j = 0;
        for (int i = 0; i < size$animation_core_release; i++) {
            animationVector2.getClass();
            j = Math.max(j, ((long) (Math.exp(vectorizedFloatDecaySpec.floatDecaySpec.flingCalculator.getSplineDeceleration(animationVector.get$animation_core_release(i)) / (FlingCalculatorKt.DecelerationRate - 1.0d)) * 1000.0d)) * 1000000);
        }
        this.durationNanos = j;
        AnimationVector copy = AnimationVectorsKt.copy(((VectorizedFloatDecaySpec) this.animationSpec).getVelocityFromNanos(j, this.initialValueVector, animationVector));
        this.endVelocity = copy;
        int size$animation_core_release2 = copy.getSize$animation_core_release();
        for (int i2 = 0; i2 < size$animation_core_release2; i2++) {
            AnimationVector animationVector4 = this.endVelocity;
            float f = animationVector4.get$animation_core_release(i2);
            this.animationSpec.getClass();
            this.animationSpec.getClass();
            animationVector4.set$animation_core_release(i2, RangesKt.coerceIn(f, -0.0f, 0.0f));
        }
    }

    @Override // androidx.compose.animation.core.Animation
    public final long getDurationNanos() {
        return this.durationNanos;
    }

    @Override // androidx.compose.animation.core.Animation
    public final Object getTargetValue() {
        return this.targetValue;
    }

    @Override // androidx.compose.animation.core.Animation
    public final TwoWayConverter getTypeConverter() {
        return this.typeConverter;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.animation.core.Animation
    public final Object getValueFromNanos(long j) {
        if (isFinishedFromNanos(j)) {
            return this.targetValue;
        }
        ?? r1 = ((TwoWayConverterImpl) this.typeConverter).convertFromVector;
        VectorizedFloatDecaySpec vectorizedFloatDecaySpec = (VectorizedFloatDecaySpec) this.animationSpec;
        AnimationVector animationVector = vectorizedFloatDecaySpec.valueVector;
        AnimationVector animationVector2 = this.initialValueVector;
        if (animationVector == null) {
            vectorizedFloatDecaySpec.valueVector = animationVector2.newVector$animation_core_release();
        }
        AnimationVector animationVector3 = vectorizedFloatDecaySpec.valueVector;
        if (animationVector3 == null) {
            animationVector3 = null;
        }
        int size$animation_core_release = animationVector3.getSize$animation_core_release();
        int i = 0;
        while (i < size$animation_core_release) {
            AnimationVector animationVector4 = vectorizedFloatDecaySpec.valueVector;
            if (animationVector4 == null) {
                animationVector4 = null;
            }
            float f = animationVector2.get$animation_core_release(i);
            long j2 = j / 1000000;
            FlingCalculator.FlingInfo flingInfo = vectorizedFloatDecaySpec.floatDecaySpec.flingCalculator.flingInfo(this.initialVelocityVector.get$animation_core_release(i));
            int i2 = i;
            long j3 = flingInfo.duration;
            animationVector4.set$animation_core_release(i2, (Math.signum(flingInfo.initialVelocity) * flingInfo.distance * AndroidFlingSpline.flingPosition(j3 > 0 ? j2 / j3 : 1.0f).distanceCoefficient) + f);
            i = i2 + 1;
        }
        AnimationVector animationVector5 = vectorizedFloatDecaySpec.valueVector;
        return r1.invoke(animationVector5 == null ? null : animationVector5);
    }

    @Override // androidx.compose.animation.core.Animation
    public final AnimationVector getVelocityVectorFromNanos(long j) {
        if (isFinishedFromNanos(j)) {
            return this.endVelocity;
        }
        return ((VectorizedFloatDecaySpec) this.animationSpec).getVelocityFromNanos(j, this.initialValueVector, this.initialVelocityVector);
    }

    @Override // androidx.compose.animation.core.Animation
    public final boolean isInfinite() {
        return false;
    }
}

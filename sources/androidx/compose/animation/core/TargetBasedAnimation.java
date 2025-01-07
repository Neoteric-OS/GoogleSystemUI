package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TargetBasedAnimation implements Animation {
    public long _durationNanos;
    public AnimationVector _endVelocity;
    public final VectorizedAnimationSpec animationSpec;
    public final AnimationVector initialValueVector;
    public final AnimationVector initialVelocityVector;
    public final Object mutableInitialValue;
    public final Object mutableTargetValue;
    public final AnimationVector targetValueVector;
    public final TwoWayConverter typeConverter;

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r1v5, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public TargetBasedAnimation(AnimationSpec animationSpec, TwoWayConverter twoWayConverter, Object obj, Object obj2, AnimationVector animationVector) {
        this.animationSpec = animationSpec.vectorize(twoWayConverter);
        this.typeConverter = twoWayConverter;
        this.mutableTargetValue = obj2;
        this.mutableInitialValue = obj;
        TwoWayConverterImpl twoWayConverterImpl = (TwoWayConverterImpl) twoWayConverter;
        this.initialValueVector = (AnimationVector) twoWayConverterImpl.convertToVector.invoke(obj);
        ?? r1 = twoWayConverterImpl.convertToVector;
        this.targetValueVector = (AnimationVector) r1.invoke(obj2);
        this.initialVelocityVector = animationVector != null ? AnimationVectorsKt.copy(animationVector) : ((AnimationVector) r1.invoke(obj)).newVector$animation_core_release();
        this._durationNanos = -1L;
    }

    @Override // androidx.compose.animation.core.Animation
    public final long getDurationNanos() {
        if (this._durationNanos < 0) {
            this._durationNanos = this.animationSpec.getDurationNanos(this.initialValueVector, this.targetValueVector, this.initialVelocityVector);
        }
        return this._durationNanos;
    }

    @Override // androidx.compose.animation.core.Animation
    public final Object getTargetValue() {
        return this.mutableTargetValue;
    }

    @Override // androidx.compose.animation.core.Animation
    public final TwoWayConverter getTypeConverter() {
        return this.typeConverter;
    }

    /* JADX WARN: Type inference failed for: r7v4, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.animation.core.Animation
    public final Object getValueFromNanos(long j) {
        if (isFinishedFromNanos(j)) {
            return this.mutableTargetValue;
        }
        AnimationVector valueFromNanos = this.animationSpec.getValueFromNanos(j, this.initialValueVector, this.targetValueVector, this.initialVelocityVector);
        int size$animation_core_release = valueFromNanos.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            if (Float.isNaN(valueFromNanos.get$animation_core_release(i))) {
                PreconditionsKt.throwIllegalStateException("AnimationVector cannot contain a NaN. " + valueFromNanos + ". Animation: " + this + ", playTimeNanos: " + j);
            }
        }
        return ((TwoWayConverterImpl) this.typeConverter).convertFromVector.invoke(valueFromNanos);
    }

    @Override // androidx.compose.animation.core.Animation
    public final AnimationVector getVelocityVectorFromNanos(long j) {
        if (!isFinishedFromNanos(j)) {
            return this.animationSpec.getVelocityFromNanos(j, this.initialValueVector, this.targetValueVector, this.initialVelocityVector);
        }
        AnimationVector animationVector = this._endVelocity;
        if (animationVector == null) {
            animationVector = this.animationSpec.getEndVelocity(this.initialValueVector, this.targetValueVector, this.initialVelocityVector);
            this._endVelocity = animationVector;
        }
        return animationVector;
    }

    @Override // androidx.compose.animation.core.Animation
    public final boolean isInfinite() {
        return this.animationSpec.isInfinite();
    }

    public final String toString() {
        return "TargetBasedAnimation: " + this.mutableInitialValue + " -> " + this.mutableTargetValue + ",initial velocity: " + this.initialVelocityVector + ", duration: " + (getDurationNanos() / 1000000) + " ms,animationSpec: " + this.animationSpec;
    }
}

package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VectorizedFloatAnimationSpec implements VectorizedFiniteAnimationSpec {
    public final Animations anims;
    public AnimationVector endVelocityVector;
    public AnimationVector valueVector;
    public AnimationVector velocityVector;

    public VectorizedFloatAnimationSpec(Animations animations) {
        this.anims = animations;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        int size$animation_core_release = animationVector.getSize$animation_core_release();
        long j = 0;
        for (int i = 0; i < size$animation_core_release; i++) {
            j = Math.max(j, this.anims.get(i).getDurationNanos(animationVector.get$animation_core_release(i), animationVector2.get$animation_core_release(i), animationVector3.get$animation_core_release(i)));
        }
        return j;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final AnimationVector getEndVelocity(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        if (this.endVelocityVector == null) {
            this.endVelocityVector = animationVector3.newVector$animation_core_release();
        }
        AnimationVector animationVector4 = this.endVelocityVector;
        if (animationVector4 == null) {
            animationVector4 = null;
        }
        int size$animation_core_release = animationVector4.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            AnimationVector animationVector5 = this.endVelocityVector;
            if (animationVector5 == null) {
                animationVector5 = null;
            }
            animationVector5.set$animation_core_release(i, this.anims.get(i).getEndVelocity(animationVector.get$animation_core_release(i), animationVector2.get$animation_core_release(i), animationVector3.get$animation_core_release(i)));
        }
        AnimationVector animationVector6 = this.endVelocityVector;
        if (animationVector6 == null) {
            return null;
        }
        return animationVector6;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        if (this.valueVector == null) {
            this.valueVector = animationVector.newVector$animation_core_release();
        }
        AnimationVector animationVector4 = this.valueVector;
        if (animationVector4 == null) {
            animationVector4 = null;
        }
        int size$animation_core_release = animationVector4.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            AnimationVector animationVector5 = this.valueVector;
            if (animationVector5 == null) {
                animationVector5 = null;
            }
            animationVector5.set$animation_core_release(i, this.anims.get(i).getValueFromNanos(animationVector.get$animation_core_release(i), animationVector2.get$animation_core_release(i), animationVector3.get$animation_core_release(i), j));
        }
        AnimationVector animationVector6 = this.valueVector;
        if (animationVector6 == null) {
            return null;
        }
        return animationVector6;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        if (this.velocityVector == null) {
            this.velocityVector = animationVector3.newVector$animation_core_release();
        }
        AnimationVector animationVector4 = this.velocityVector;
        if (animationVector4 == null) {
            animationVector4 = null;
        }
        int size$animation_core_release = animationVector4.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            AnimationVector animationVector5 = this.velocityVector;
            if (animationVector5 == null) {
                animationVector5 = null;
            }
            animationVector5.set$animation_core_release(i, this.anims.get(i).getVelocityFromNanos(animationVector.get$animation_core_release(i), animationVector2.get$animation_core_release(i), animationVector3.get$animation_core_release(i), j));
        }
        AnimationVector animationVector6 = this.velocityVector;
        if (animationVector6 == null) {
            return null;
        }
        return animationVector6;
    }

    public VectorizedFloatAnimationSpec(final FloatAnimationSpec floatAnimationSpec) {
        this(new Animations() { // from class: androidx.compose.animation.core.VectorizedFloatAnimationSpec.1
            @Override // androidx.compose.animation.core.Animations
            public final FloatAnimationSpec get(int i) {
                return FloatAnimationSpec.this;
            }
        });
    }
}

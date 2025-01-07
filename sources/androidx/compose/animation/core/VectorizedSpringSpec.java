package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VectorizedSpringSpec implements VectorizedFiniteAnimationSpec {
    public final /* synthetic */ VectorizedFloatAnimationSpec $$delegate_0;

    public VectorizedSpringSpec(final float f, final float f2, final AnimationVector animationVector) {
        int[] iArr = VectorizedAnimationSpecKt.EmptyIntArray;
        this.$$delegate_0 = new VectorizedFloatAnimationSpec(animationVector != null ? new Animations(f, f2, animationVector) { // from class: androidx.compose.animation.core.VectorizedAnimationSpecKt$createSpringAnimations$1
            public final FloatSpringSpec[] anims;

            {
                int size$animation_core_release = animationVector.getSize$animation_core_release();
                FloatSpringSpec[] floatSpringSpecArr = new FloatSpringSpec[size$animation_core_release];
                for (int i = 0; i < size$animation_core_release; i++) {
                    floatSpringSpecArr[i] = new FloatSpringSpec(f, f2, animationVector.get$animation_core_release(i));
                }
                this.anims = floatSpringSpecArr;
            }

            @Override // androidx.compose.animation.core.Animations
            public final FloatAnimationSpec get(int i) {
                return this.anims[i];
            }
        } : new Animations(f, f2) { // from class: androidx.compose.animation.core.VectorizedAnimationSpecKt$createSpringAnimations$2
            public final FloatSpringSpec anim;

            {
                this.anim = new FloatSpringSpec(f, f2, 0.01f);
            }

            @Override // androidx.compose.animation.core.Animations
            public final FloatAnimationSpec get(int i) {
                return this.anim;
            }
        });
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return this.$$delegate_0.getDurationNanos(animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final AnimationVector getEndVelocity(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return this.$$delegate_0.getEndVelocity(animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return this.$$delegate_0.getValueFromNanos(j, animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return this.$$delegate_0.getVelocityFromNanos(j, animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec, androidx.compose.animation.core.VectorizedAnimationSpec
    public final boolean isInfinite() {
        this.$$delegate_0.getClass();
        return false;
    }
}

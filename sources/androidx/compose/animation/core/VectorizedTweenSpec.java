package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VectorizedTweenSpec implements VectorizedDurationBasedAnimationSpec {
    public final VectorizedFloatAnimationSpec anim;
    public final int delayMillis;
    public final int durationMillis;

    public VectorizedTweenSpec(int i, int i2, Easing easing) {
        this.durationMillis = i;
        this.delayMillis = i2;
        this.anim = new VectorizedFloatAnimationSpec(new FloatTweenSpec(i, i2, easing));
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public final int getDelayMillis() {
        return this.delayMillis;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public final int getDurationMillis() {
        return this.durationMillis;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return this.anim.getValueFromNanos(j, animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return this.anim.getVelocityFromNanos(j, animationVector, animationVector2, animationVector3);
    }
}

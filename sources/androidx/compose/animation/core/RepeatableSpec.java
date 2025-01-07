package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RepeatableSpec implements FiniteAnimationSpec {
    public final DurationBasedAnimationSpec animation;
    public final long initialStartOffset;
    public final int iterations;
    public final RepeatMode repeatMode;

    public RepeatableSpec(int i, DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, long j) {
        this.iterations = i;
        this.animation = durationBasedAnimationSpec;
        this.repeatMode = repeatMode;
        this.initialStartOffset = j;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof RepeatableSpec)) {
            return false;
        }
        RepeatableSpec repeatableSpec = (RepeatableSpec) obj;
        return repeatableSpec.iterations == this.iterations && repeatableSpec.animation.equals(this.animation) && repeatableSpec.repeatMode == this.repeatMode && repeatableSpec.initialStartOffset == this.initialStartOffset;
    }

    public final int hashCode() {
        return Long.hashCode(this.initialStartOffset) + ((this.repeatMode.hashCode() + ((this.animation.hashCode() + (this.iterations * 31)) * 31)) * 31);
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public final VectorizedFiniteAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        return new VectorizedRepeatableSpec(this.iterations, this.animation.vectorize(twoWayConverter), this.repeatMode, this.initialStartOffset);
    }
}

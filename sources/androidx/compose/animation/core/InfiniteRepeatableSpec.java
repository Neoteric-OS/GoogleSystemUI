package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InfiniteRepeatableSpec implements AnimationSpec {
    public final TweenSpec animation;
    public final long initialStartOffset;
    public final RepeatMode repeatMode;

    public InfiniteRepeatableSpec(TweenSpec tweenSpec, RepeatMode repeatMode, long j) {
        this.animation = tweenSpec;
        this.repeatMode = repeatMode;
        this.initialStartOffset = j;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof InfiniteRepeatableSpec)) {
            return false;
        }
        InfiniteRepeatableSpec infiniteRepeatableSpec = (InfiniteRepeatableSpec) obj;
        return infiniteRepeatableSpec.animation.equals(this.animation) && infiniteRepeatableSpec.repeatMode == this.repeatMode && infiniteRepeatableSpec.initialStartOffset == this.initialStartOffset;
    }

    public final int hashCode() {
        return Long.hashCode(this.initialStartOffset) + ((this.repeatMode.hashCode() + (this.animation.hashCode() * 31)) * 31);
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public final VectorizedAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        return new VectorizedInfiniteRepeatableSpec(this.animation.vectorize(twoWayConverter), this.repeatMode, this.initialStartOffset);
    }
}

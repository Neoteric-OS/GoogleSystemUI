package androidx.compose.animation.core;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class StartDelayAnimationSpec implements AnimationSpec {
    public final FiniteAnimationSpec animationSpec;
    public final long startDelayNanos;

    public StartDelayAnimationSpec(FiniteAnimationSpec finiteAnimationSpec, long j) {
        this.animationSpec = finiteAnimationSpec;
        this.startDelayNanos = j;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof StartDelayAnimationSpec)) {
            return false;
        }
        StartDelayAnimationSpec startDelayAnimationSpec = (StartDelayAnimationSpec) obj;
        return startDelayAnimationSpec.startDelayNanos == this.startDelayNanos && Intrinsics.areEqual(startDelayAnimationSpec.animationSpec, this.animationSpec);
    }

    public final int hashCode() {
        return Long.hashCode(this.startDelayNanos) + (this.animationSpec.hashCode() * 31);
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public final VectorizedAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        return new StartDelayVectorizedAnimationSpec(this.animationSpec.vectorize(twoWayConverter), this.startDelayNanos);
    }
}

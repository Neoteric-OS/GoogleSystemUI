package androidx.compose.animation.graphics.vector;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorizedFiniteAnimationSpec;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ReversedSpec implements FiniteAnimationSpec {
    public final int durationMillis;
    public final FiniteAnimationSpec spec;

    public ReversedSpec(FiniteAnimationSpec finiteAnimationSpec, int i) {
        this.spec = finiteAnimationSpec;
        this.durationMillis = i;
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public final VectorizedFiniteAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        return new VectorizedReversedSpec(this.spec.vectorize(twoWayConverter), this.durationMillis * 1000000);
    }
}

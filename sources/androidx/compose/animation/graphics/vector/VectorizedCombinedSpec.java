package androidx.compose.animation.graphics.vector;

import androidx.compose.animation.core.AnimationVector;
import androidx.compose.animation.core.VectorizedFiniteAnimationSpec;
import java.util.List;
import java.util.ListIterator;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class VectorizedCombinedSpec implements VectorizedFiniteAnimationSpec {
    public final List animations;

    public VectorizedCombinedSpec(List list) {
        this.animations = list;
    }

    public final Pair chooseAnimation(long j) {
        Object obj;
        List list = this.animations;
        ListIterator listIterator = list.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                obj = null;
                break;
            }
            obj = listIterator.previous();
            if (((Number) ((Pair) obj).component1()).longValue() <= j) {
                break;
            }
        }
        Pair pair = (Pair) obj;
        return pair == null ? (Pair) CollectionsKt.first(this.animations) : pair;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        Pair pair = (Pair) CollectionsKt.last(this.animations);
        return ((VectorizedFiniteAnimationSpec) pair.component2()).getDurationNanos(animationVector, animationVector2, animationVector3) + ((Number) pair.component1()).longValue();
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final AnimationVector getValueFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        Pair chooseAnimation = chooseAnimation(j);
        return ((VectorizedFiniteAnimationSpec) chooseAnimation.component2()).getValueFromNanos(j - ((Number) chooseAnimation.component1()).longValue(), animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final AnimationVector getVelocityFromNanos(long j, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        Pair chooseAnimation = chooseAnimation(j);
        return ((VectorizedFiniteAnimationSpec) chooseAnimation.component2()).getVelocityFromNanos(j - ((Number) chooseAnimation.component1()).longValue(), animationVector, animationVector2, animationVector3);
    }
}

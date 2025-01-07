package androidx.compose.animation.core;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SpringSpec implements FiniteAnimationSpec {
    public final float dampingRatio;
    public final float stiffness;
    public final Object visibilityThreshold;

    public SpringSpec(float f, float f2, Object obj) {
        this.dampingRatio = f;
        this.stiffness = f2;
        this.visibilityThreshold = obj;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SpringSpec)) {
            return false;
        }
        SpringSpec springSpec = (SpringSpec) obj;
        return springSpec.dampingRatio == this.dampingRatio && springSpec.stiffness == this.stiffness && Intrinsics.areEqual(springSpec.visibilityThreshold, this.visibilityThreshold);
    }

    public final int hashCode() {
        Object obj = this.visibilityThreshold;
        return Float.hashCode(this.stiffness) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m((obj != null ? obj.hashCode() : 0) * 31, this.dampingRatio, 31);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.animation.core.AnimationSpec
    public final VectorizedAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        Object obj = this.visibilityThreshold;
        return new VectorizedSpringSpec(this.dampingRatio, this.stiffness, obj == null ? null : (AnimationVector) ((TwoWayConverterImpl) twoWayConverter).convertToVector.invoke(obj));
    }

    public /* synthetic */ SpringSpec(int i, Object obj) {
        this(1.0f, 1500.0f, (i & 4) != 0 ? null : obj);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.animation.core.FiniteAnimationSpec, androidx.compose.animation.core.AnimationSpec
    public final VectorizedFiniteAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        Object obj = this.visibilityThreshold;
        return new VectorizedSpringSpec(this.dampingRatio, this.stiffness, obj == null ? null : (AnimationVector) ((TwoWayConverterImpl) twoWayConverter).convertToVector.invoke(obj));
    }
}

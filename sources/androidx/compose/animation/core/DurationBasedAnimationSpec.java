package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface DurationBasedAnimationSpec extends FiniteAnimationSpec {
    @Override // androidx.compose.animation.core.FiniteAnimationSpec, androidx.compose.animation.core.AnimationSpec
    VectorizedDurationBasedAnimationSpec vectorize(TwoWayConverter twoWayConverter);
}

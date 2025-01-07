package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface FloatAnimationSpec extends AnimationSpec {
    long getDurationNanos(float f, float f2, float f3);

    default float getEndVelocity(float f, float f2, float f3) {
        return getVelocityFromNanos(f, f2, f3, getDurationNanos(f, f2, f3));
    }

    float getValueFromNanos(float f, float f2, float f3, long j);

    float getVelocityFromNanos(float f, float f2, float f3, long j);

    @Override // androidx.compose.animation.core.AnimationSpec
    default VectorizedAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        return new VectorizedFloatAnimationSpec(this);
    }
}

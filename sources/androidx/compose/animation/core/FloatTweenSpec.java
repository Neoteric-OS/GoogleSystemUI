package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FloatTweenSpec implements FloatAnimationSpec {
    public final long delayNanos;
    public final int duration;
    public final long durationNanos;
    public final Easing easing;

    public FloatTweenSpec(int i, int i2, Easing easing) {
        this.duration = i;
        this.easing = easing;
        this.durationNanos = i * 1000000;
        this.delayNanos = i2 * 1000000;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final long getDurationNanos(float f, float f2, float f3) {
        return this.delayNanos + this.durationNanos;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getValueFromNanos(float f, float f2, float f3, long j) {
        long j2 = j - this.delayNanos;
        if (j2 < 0) {
            j2 = 0;
        }
        long j3 = this.durationNanos;
        if (j2 > j3) {
            j2 = j3;
        }
        float transform = this.easing.transform(this.duration == 0 ? 1.0f : j2 / j3);
        return (f2 * transform) + ((1 - transform) * f);
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getVelocityFromNanos(float f, float f2, float f3, long j) {
        long j2 = j - this.delayNanos;
        if (j2 < 0) {
            j2 = 0;
        }
        long j3 = this.durationNanos;
        long j4 = j2 > j3 ? j3 : j2;
        if (j4 == 0) {
            return f3;
        }
        return (getValueFromNanos(f, f2, f3, j4) - getValueFromNanos(f, f2, f3, j4 - 1000000)) * 1000.0f;
    }
}

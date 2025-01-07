package androidx.compose.animation.core;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AnimationSpecKt {
    /* renamed from: infiniteRepeatable-9IiC70o$default, reason: not valid java name */
    public static InfiniteRepeatableSpec m9infiniteRepeatable9IiC70o$default(TweenSpec tweenSpec, RepeatMode repeatMode, long j, int i) {
        if ((i & 2) != 0) {
            repeatMode = RepeatMode.Restart;
        }
        if ((i & 4) != 0) {
            j = 0;
        }
        return new InfiniteRepeatableSpec(tweenSpec, repeatMode, j);
    }

    /* renamed from: repeatable-91I0pcU$default, reason: not valid java name */
    public static RepeatableSpec m10repeatable91I0pcU$default(int i, DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, long j, int i2) {
        if ((i2 & 4) != 0) {
            repeatMode = RepeatMode.Restart;
        }
        RepeatMode repeatMode2 = repeatMode;
        if ((i2 & 8) != 0) {
            j = 0;
        }
        return new RepeatableSpec(i, durationBasedAnimationSpec, repeatMode2, j);
    }

    public static SnapSpec snap$default() {
        return new SnapSpec(0);
    }

    public static SpringSpec spring$default(float f, float f2, Object obj, int i) {
        if ((i & 1) != 0) {
            f = 1.0f;
        }
        if ((i & 2) != 0) {
            f2 = 1500.0f;
        }
        if ((i & 4) != 0) {
            obj = null;
        }
        return new SpringSpec(f, f2, obj);
    }

    public static TweenSpec tween$default(int i, int i2, Easing easing, int i3) {
        if ((i3 & 1) != 0) {
            i = 300;
        }
        if ((i3 & 2) != 0) {
            i2 = 0;
        }
        if ((i3 & 4) != 0) {
            easing = EasingKt.FastOutSlowInEasing;
        }
        return new TweenSpec(i, i2, easing);
    }
}

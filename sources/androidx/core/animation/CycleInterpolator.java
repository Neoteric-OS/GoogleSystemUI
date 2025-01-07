package androidx.core.animation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CycleInterpolator implements Interpolator {
    @Override // androidx.core.animation.Interpolator
    public final float getInterpolation(float f) {
        return (float) Math.sin(10.0f * 3.141592653589793d * f);
    }
}

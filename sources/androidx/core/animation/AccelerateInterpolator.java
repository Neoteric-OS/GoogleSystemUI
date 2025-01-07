package androidx.core.animation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AccelerateInterpolator implements Interpolator {
    public final double mDoubleFactor;
    public final float mFactor;

    public AccelerateInterpolator(float f) {
        this.mFactor = f;
        this.mDoubleFactor = f * 2.0f;
    }

    @Override // androidx.core.animation.Interpolator
    public final float getInterpolation(float f) {
        return this.mFactor == 1.0f ? f * f : (float) Math.pow(f, this.mDoubleFactor);
    }
}

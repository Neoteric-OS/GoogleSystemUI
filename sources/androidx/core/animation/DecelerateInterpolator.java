package androidx.core.animation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DecelerateInterpolator implements Interpolator {
    public final float mFactor;

    public DecelerateInterpolator(float f) {
        this.mFactor = f;
    }

    @Override // androidx.core.animation.Interpolator
    public final float getInterpolation(float f) {
        if (this.mFactor != 1.0f) {
            return (float) (1.0d - Math.pow(1.0f - f, r2 * 2.0f));
        }
        float f2 = 1.0f - f;
        return 1.0f - (f2 * f2);
    }
}

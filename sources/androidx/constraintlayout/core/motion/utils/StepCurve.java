package androidx.constraintlayout.core.motion.utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StepCurve extends Easing {
    public MonotonicCurveFit mCurveFit;

    @Override // androidx.constraintlayout.core.motion.utils.Easing
    public final double get(double d) {
        return this.mCurveFit.getPos(d);
    }

    @Override // androidx.constraintlayout.core.motion.utils.Easing
    public final double getDiff(double d) {
        return this.mCurveFit.getSlope(d);
    }
}

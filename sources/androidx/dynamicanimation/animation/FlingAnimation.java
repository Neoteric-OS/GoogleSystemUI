package androidx.dynamicanimation.animation;

import androidx.dynamicanimation.animation.DynamicAnimation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlingAnimation extends DynamicAnimation {
    public final DragForce mFlingForce;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DragForce {
        public float mFriction = -4.2f;
        public final DynamicAnimation.MassState mMassState = new DynamicAnimation.MassState();
        public float mVelocityThreshold;
    }

    public FlingAnimation(Object obj, FloatPropertyCompat floatPropertyCompat) {
        super(obj, floatPropertyCompat);
        DragForce dragForce = new DragForce();
        this.mFlingForce = dragForce;
        dragForce.mVelocityThreshold = this.mMinVisibleChange * 0.75f * 62.5f;
    }

    public final void setFriction(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Friction must be positive");
        }
        this.mFlingForce.mFriction = f * (-4.2f);
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public final void setValueThreshold(float f) {
        this.mFlingForce.mVelocityThreshold = f * 62.5f;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation
    public final boolean updateValueAndVelocity(long j) {
        float f = this.mValue;
        float f2 = this.mVelocity;
        DragForce dragForce = this.mFlingForce;
        float exp = (float) (Math.exp((j / 1000.0f) * dragForce.mFriction) * f2);
        DynamicAnimation.MassState massState = dragForce.mMassState;
        massState.mVelocity = exp;
        massState.mValue = ((exp - f2) / dragForce.mFriction) + f;
        if (Math.abs(exp) < dragForce.mVelocityThreshold) {
            massState.mVelocity = 0.0f;
        }
        float f3 = massState.mValue;
        this.mValue = f3;
        float f4 = massState.mVelocity;
        this.mVelocity = f4;
        float f5 = this.mMinValue;
        if (f3 < f5) {
            this.mValue = f5;
            return true;
        }
        float f6 = this.mMaxValue;
        if (f3 <= f6) {
            return f3 >= f6 || f3 <= f5 || Math.abs(f4) < dragForce.mVelocityThreshold;
        }
        this.mValue = f6;
        return true;
    }
}

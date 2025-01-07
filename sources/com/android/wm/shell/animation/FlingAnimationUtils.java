package com.android.wm.shell.animation;

import android.animation.Animator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.android.wm.shell.shared.animation.Interpolators;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FlingAnimationUtils {
    public final AnimatorProperties mAnimatorProperties;
    public float mCachedStartGradient;
    public float mCachedVelocityFactor;
    public final float mHighVelocityPxPerSecond;
    public PathInterpolator mInterpolator;
    public final float mLinearOutSlowInX2;
    public final float mMaxLengthSeconds;
    public final float mMinVelocityPxPerSecond;
    public final float mSpeedUpFactor;
    public final float mY2;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimatorProperties {
        public long mDuration;
        public Interpolator mInterpolator;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public final DisplayMetrics mDisplayMetrics;
        public float mMaxLengthSeconds;
        public float mSpeedUpFactor;
        public float mX2;
        public float mY2;

        public Builder(DisplayMetrics displayMetrics) {
            this.mDisplayMetrics = displayMetrics;
            reset();
        }

        public final FlingAnimationUtils build() {
            return new FlingAnimationUtils(this.mDisplayMetrics, this.mMaxLengthSeconds, this.mSpeedUpFactor, this.mX2, this.mY2);
        }

        public final void reset() {
            this.mMaxLengthSeconds = 0.0f;
            this.mSpeedUpFactor = 0.0f;
            this.mX2 = -1.0f;
            this.mY2 = 1.0f;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InterpolatorInterpolator implements Interpolator {
        public final Interpolator mCrossfader;
        public final VelocityInterpolator mInterpolator1;
        public final Interpolator mInterpolator2;

        public InterpolatorInterpolator(VelocityInterpolator velocityInterpolator, Interpolator interpolator, Interpolator interpolator2) {
            this.mInterpolator1 = velocityInterpolator;
            this.mInterpolator2 = interpolator;
            this.mCrossfader = interpolator2;
        }

        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            float interpolation = ((PathInterpolator) this.mCrossfader).getInterpolation(f);
            float interpolation2 = this.mInterpolator1.getInterpolation(f);
            return (this.mInterpolator2.getInterpolation(f) * interpolation) + (interpolation2 * (1.0f - interpolation));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VelocityInterpolator implements Interpolator {
        public final float mDiff;
        public final float mDurationSeconds;
        public final float mVelocity;

        public VelocityInterpolator(float f, float f2, float f3) {
            this.mDurationSeconds = f;
            this.mVelocity = f2;
            this.mDiff = f3;
        }

        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            return ((f * this.mDurationSeconds) * this.mVelocity) / this.mDiff;
        }
    }

    public FlingAnimationUtils(DisplayMetrics displayMetrics, float f) {
        this(displayMetrics, f, 0.0f, -1.0f, 1.0f);
    }

    public final void apply(Animator animator, float f, float f2, float f3, float f4) {
        AnimatorProperties properties = getProperties(f, f2, f3, f4);
        animator.setDuration(properties.mDuration);
        animator.setInterpolator(properties.mInterpolator);
    }

    public final void applyDismissing(Animator animator, float f, float f2, float f3, float f4) {
        float f5 = f2 - f;
        float pow = (float) (Math.pow(Math.abs(f5) / f4, 0.5d) * this.mMaxLengthSeconds);
        float abs = Math.abs(f5);
        float abs2 = Math.abs(f3);
        float f6 = this.mMinVelocityPxPerSecond;
        float max = Math.max(0.0f, Math.min(1.0f, (abs2 - f6) / (this.mHighVelocityPxPerSecond - f6)));
        float f7 = (max * 0.5f) + ((1.0f - max) * 0.4f);
        PathInterpolator pathInterpolator = new PathInterpolator(0.0f, 0.0f, 0.5f, f7);
        float f8 = ((f7 / 0.5f) * abs) / abs2;
        AnimatorProperties animatorProperties = this.mAnimatorProperties;
        if (f8 <= pow) {
            animatorProperties.mInterpolator = pathInterpolator;
            pow = f8;
        } else if (abs2 >= f6) {
            animatorProperties.mInterpolator = new InterpolatorInterpolator(new VelocityInterpolator(pow, abs2, abs), pathInterpolator, Interpolators.LINEAR_OUT_SLOW_IN);
        } else {
            animatorProperties.mInterpolator = Interpolators.FAST_OUT_LINEAR_IN;
        }
        long j = (long) (pow * 1000.0f);
        animatorProperties.mDuration = j;
        animator.setDuration(j);
        animator.setInterpolator(animatorProperties.mInterpolator);
    }

    public final AnimatorProperties getProperties(float f, float f2, float f3, float f4) {
        Interpolator interpolator;
        float f5 = f2 - f;
        float sqrt = (float) (Math.sqrt(Math.abs(f5) / f4) * this.mMaxLengthSeconds);
        float abs = Math.abs(f5);
        float abs2 = Math.abs(f3);
        float f6 = this.mSpeedUpFactor;
        float min = f6 == 0.0f ? 1.0f : Math.min(abs2 / 3000.0f, 1.0f);
        float f7 = this.mLinearOutSlowInX2;
        float f8 = this.mY2;
        float f9 = 1.0f - min;
        float f10 = ((f8 / f7) * min) + (0.75f * f9);
        float f11 = (f10 * abs) / abs2;
        if (Float.isNaN(min)) {
            Log.e("FlingAnimationUtils", "Invalid velocity factor", new Throwable());
            interpolator = Interpolators.LINEAR_OUT_SLOW_IN;
        } else {
            if (f10 != this.mCachedStartGradient || min != this.mCachedVelocityFactor) {
                float f12 = f9 * f6;
                float f13 = f12 * f10;
                try {
                    this.mInterpolator = new PathInterpolator(f12, f13, f7, f8);
                    this.mCachedStartGradient = f10;
                    this.mCachedVelocityFactor = min;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Illegal path with x1=" + f12 + " y1=" + f13 + " x2=" + f7 + " y2=" + f8, e);
                }
            }
            interpolator = this.mInterpolator;
        }
        AnimatorProperties animatorProperties = this.mAnimatorProperties;
        if (f11 <= sqrt) {
            animatorProperties.mInterpolator = interpolator;
            sqrt = f11;
        } else if (abs2 >= this.mMinVelocityPxPerSecond) {
            animatorProperties.mInterpolator = new InterpolatorInterpolator(new VelocityInterpolator(sqrt, abs2, abs), interpolator, Interpolators.LINEAR_OUT_SLOW_IN);
        } else {
            animatorProperties.mInterpolator = Interpolators.FAST_OUT_SLOW_IN;
        }
        animatorProperties.mDuration = (long) (sqrt * 1000.0f);
        return animatorProperties;
    }

    public FlingAnimationUtils(DisplayMetrics displayMetrics, float f, float f2, float f3, float f4) {
        this.mAnimatorProperties = new AnimatorProperties();
        this.mCachedStartGradient = -1.0f;
        this.mCachedVelocityFactor = -1.0f;
        this.mMaxLengthSeconds = f;
        this.mSpeedUpFactor = f2;
        if (f3 < 0.0f) {
            this.mLinearOutSlowInX2 = (0.68f * f2) + ((1.0f - f2) * 0.35f);
        } else {
            this.mLinearOutSlowInX2 = f3;
        }
        this.mY2 = f4;
        float f5 = displayMetrics.density;
        this.mMinVelocityPxPerSecond = 250.0f * f5;
        this.mHighVelocityPxPerSecond = f5 * 3000.0f;
    }
}

package com.airbnb.lottie.value;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.content.GradientColor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class Keyframe {
    public final LottieComposition composition;
    public Float endFrame;
    public float endProgress;
    public Object endValue;
    public float endValueFloat;
    public int endValueInt;
    public final Interpolator interpolator;
    public PointF pathCp1;
    public PointF pathCp2;
    public final float startFrame;
    public float startProgress;
    public final Object startValue;
    public float startValueFloat;
    public int startValueInt;
    public final Interpolator xInterpolator;
    public final Interpolator yInterpolator;

    public Keyframe(LottieComposition lottieComposition, Object obj, Object obj2, Interpolator interpolator, float f, Float f2) {
        this.startValueFloat = -3987645.8f;
        this.endValueFloat = -3987645.8f;
        this.startValueInt = 784923401;
        this.endValueInt = 784923401;
        this.startProgress = Float.MIN_VALUE;
        this.endProgress = Float.MIN_VALUE;
        this.pathCp1 = null;
        this.pathCp2 = null;
        this.composition = lottieComposition;
        this.startValue = obj;
        this.endValue = obj2;
        this.interpolator = interpolator;
        this.xInterpolator = null;
        this.yInterpolator = null;
        this.startFrame = f;
        this.endFrame = f2;
    }

    public final float getEndProgress() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 1.0f;
        }
        if (this.endProgress == Float.MIN_VALUE) {
            if (this.endFrame == null) {
                this.endProgress = 1.0f;
            } else {
                this.endProgress = ((this.endFrame.floatValue() - this.startFrame) / (lottieComposition.endFrame - lottieComposition.startFrame)) + getStartProgress();
            }
        }
        return this.endProgress;
    }

    public final float getStartProgress() {
        LottieComposition lottieComposition = this.composition;
        if (lottieComposition == null) {
            return 0.0f;
        }
        if (this.startProgress == Float.MIN_VALUE) {
            float f = lottieComposition.startFrame;
            this.startProgress = (this.startFrame - f) / (lottieComposition.endFrame - f);
        }
        return this.startProgress;
    }

    public final boolean isStatic() {
        return this.interpolator == null && this.xInterpolator == null && this.yInterpolator == null;
    }

    public final String toString() {
        return "Keyframe{startValue=" + this.startValue + ", endValue=" + this.endValue + ", startFrame=" + this.startFrame + ", endFrame=" + this.endFrame + ", interpolator=" + this.interpolator + '}';
    }

    public Keyframe(LottieComposition lottieComposition, Object obj, Object obj2, Interpolator interpolator, Interpolator interpolator2, float f) {
        this.startValueFloat = -3987645.8f;
        this.endValueFloat = -3987645.8f;
        this.startValueInt = 784923401;
        this.endValueInt = 784923401;
        this.startProgress = Float.MIN_VALUE;
        this.endProgress = Float.MIN_VALUE;
        this.pathCp1 = null;
        this.pathCp2 = null;
        this.composition = lottieComposition;
        this.startValue = obj;
        this.endValue = obj2;
        this.interpolator = null;
        this.xInterpolator = interpolator;
        this.yInterpolator = interpolator2;
        this.startFrame = f;
        this.endFrame = null;
    }

    public Keyframe(LottieComposition lottieComposition, Object obj, Object obj2, Interpolator interpolator, Interpolator interpolator2, Interpolator interpolator3, float f, Float f2) {
        this.startValueFloat = -3987645.8f;
        this.endValueFloat = -3987645.8f;
        this.startValueInt = 784923401;
        this.endValueInt = 784923401;
        this.startProgress = Float.MIN_VALUE;
        this.endProgress = Float.MIN_VALUE;
        this.pathCp1 = null;
        this.pathCp2 = null;
        this.composition = lottieComposition;
        this.startValue = obj;
        this.endValue = obj2;
        this.interpolator = interpolator;
        this.xInterpolator = interpolator2;
        this.yInterpolator = interpolator3;
        this.startFrame = f;
        this.endFrame = f2;
    }

    public Keyframe(Object obj) {
        this.startValueFloat = -3987645.8f;
        this.endValueFloat = -3987645.8f;
        this.startValueInt = 784923401;
        this.endValueInt = 784923401;
        this.startProgress = Float.MIN_VALUE;
        this.endProgress = Float.MIN_VALUE;
        this.pathCp1 = null;
        this.pathCp2 = null;
        this.composition = null;
        this.startValue = obj;
        this.endValue = obj;
        this.interpolator = null;
        this.xInterpolator = null;
        this.yInterpolator = null;
        this.startFrame = Float.MIN_VALUE;
        this.endFrame = Float.valueOf(Float.MAX_VALUE);
    }

    public Keyframe(GradientColor gradientColor, GradientColor gradientColor2) {
        this.startValueFloat = -3987645.8f;
        this.endValueFloat = -3987645.8f;
        this.startValueInt = 784923401;
        this.endValueInt = 784923401;
        this.startProgress = Float.MIN_VALUE;
        this.endProgress = Float.MIN_VALUE;
        this.pathCp1 = null;
        this.pathCp2 = null;
        this.composition = null;
        this.startValue = gradientColor;
        this.endValue = gradientColor2;
        this.interpolator = null;
        this.xInterpolator = null;
        this.yInterpolator = null;
        this.startFrame = Float.MIN_VALUE;
        this.endFrame = Float.valueOf(Float.MAX_VALUE);
    }
}

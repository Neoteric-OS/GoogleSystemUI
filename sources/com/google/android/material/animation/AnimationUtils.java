package com.google.android.material.animation;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AnimationUtils {
    public static final FastOutLinearInInterpolator FAST_OUT_LINEAR_IN_INTERPOLATOR;
    public static final FastOutSlowInInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = null;
    public static final LinearOutSlowInInterpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR;

    static {
        new LinearInterpolator();
        FAST_OUT_LINEAR_IN_INTERPOLATOR = new FastOutLinearInInterpolator();
        LINEAR_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();
        new DecelerateInterpolator();
    }

    public static float lerp(float f, float f2, float f3) {
        return AndroidFlingSpline$$ExternalSyntheticOutline0.m(f2, f, f3, f);
    }

    public static int lerp(int i, float f, int i2) {
        return Math.round(f * (i2 - i)) + i;
    }

    public static float lerp(float f, float f2, float f3, float f4, float f5) {
        return f5 <= f3 ? f : f5 >= f4 ? f2 : lerp(f, f2, (f5 - f3) / (f4 - f3));
    }
}

package com.android.systemui.animation.back;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.animation.Interpolator;
import android.window.BackEvent;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BackAnimationSpecKt$createFloatingSurfaceAnimationSpec$1 implements BackAnimationSpec {
    public final /* synthetic */ Function0 $displayMetricsProvider;
    public final /* synthetic */ Interpolator $scaleEasing;
    public final /* synthetic */ Interpolator $translateXEasing;
    public final /* synthetic */ Interpolator $translateYEasing;

    public BackAnimationSpecKt$createFloatingSurfaceAnimationSpec$1(Function0 function0, Interpolator interpolator, Interpolator interpolator2, Interpolator interpolator3) {
        this.$displayMetricsProvider = function0;
        this.$translateXEasing = interpolator;
        this.$translateYEasing = interpolator2;
        this.$scaleEasing = interpolator3;
    }

    @Override // com.android.systemui.animation.back.BackAnimationSpec
    public final void getBackTransformation(BackEvent backEvent, float f, BackTransformation backTransformation) {
        DisplayMetrics displayMetrics = (DisplayMetrics) this.$displayMetricsProvider.invoke();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        float f2 = i;
        float f3 = 2;
        float applyDimension = ((f2 - (f2 * 0.9f)) / f3) - TypedValue.applyDimension(1, 8.0f, displayMetrics);
        float f4 = i2;
        float applyDimension2 = ((f4 - (0.9f * f4)) / f3) - TypedValue.applyDimension(1, 8.0f, displayMetrics);
        int i3 = backEvent.getSwipeEdge() != 0 ? -1 : 1;
        float progress = backEvent.getProgress();
        float interpolation = this.$translateXEasing.getInterpolation(progress);
        float interpolation2 = this.$translateYEasing.getInterpolation(f);
        float interpolation3 = this.$scaleEasing.getInterpolation(progress);
        backTransformation.translateX = interpolation * i3 * applyDimension;
        backTransformation.translateY = interpolation2 * applyDimension2;
        backTransformation.scale = 1.0f - (interpolation3 * 0.100000024f);
    }
}

package com.android.dream.lowlight.util;

import android.view.animation.Interpolator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TruncatedInterpolator implements Interpolator {
    public final Interpolator baseInterpolator;
    public final float scaleFactor;

    public TruncatedInterpolator(Interpolator interpolator, float f, float f2) {
        this.baseInterpolator = interpolator;
        this.scaleFactor = f2 / f;
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        return this.baseInterpolator.getInterpolation(f * this.scaleFactor);
    }
}

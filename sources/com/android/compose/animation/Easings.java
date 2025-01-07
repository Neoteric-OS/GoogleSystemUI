package com.android.compose.animation;

import androidx.compose.animation.core.Easing;
import androidx.core.animation.Interpolator;
import androidx.core.animation.LinearInterpolator;
import androidx.core.animation.PathInterpolator;
import com.android.app.animation.InterpolatorsAndroidX;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Easings {
    public static final Easings$fromInterpolator$1 Emphasized;
    public static final Easings$fromInterpolator$1 Legacy = null;
    public static final Easings$fromInterpolator$1 LegacyDecelerate = null;
    public static final Easings$fromInterpolator$1 Linear;
    public static final Easings$fromInterpolator$1 Standard;
    public static final Easings$fromInterpolator$1 StandardAccelerate = null;
    public static final Easings$fromInterpolator$1 StandardDecelerate = null;

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.compose.animation.Easings$fromInterpolator$1] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.android.compose.animation.Easings$fromInterpolator$1] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.android.compose.animation.Easings$fromInterpolator$1] */
    static {
        final PathInterpolator pathInterpolator = InterpolatorsAndroidX.STANDARD;
        Standard = new Easing() { // from class: com.android.compose.animation.Easings$fromInterpolator$1
            @Override // androidx.compose.animation.core.Easing
            public final float transform(float f) {
                return Interpolator.this.getInterpolation(f);
            }
        };
        final PathInterpolator pathInterpolator2 = InterpolatorsAndroidX.STANDARD_ACCELERATE;
        new Easing() { // from class: com.android.compose.animation.Easings$fromInterpolator$1
            @Override // androidx.compose.animation.core.Easing
            public final float transform(float f) {
                return Interpolator.this.getInterpolation(f);
            }
        };
        final PathInterpolator pathInterpolator3 = InterpolatorsAndroidX.STANDARD_DECELERATE;
        new Easing() { // from class: com.android.compose.animation.Easings$fromInterpolator$1
            @Override // androidx.compose.animation.core.Easing
            public final float transform(float f) {
                return Interpolator.this.getInterpolation(f);
            }
        };
        final PathInterpolator pathInterpolator4 = InterpolatorsAndroidX.EMPHASIZED;
        Emphasized = new Easing() { // from class: com.android.compose.animation.Easings$fromInterpolator$1
            @Override // androidx.compose.animation.core.Easing
            public final float transform(float f) {
                return Interpolator.this.getInterpolation(f);
            }
        };
        final LinearInterpolator linearInterpolator = InterpolatorsAndroidX.LINEAR;
        Linear = new Easing() { // from class: com.android.compose.animation.Easings$fromInterpolator$1
            @Override // androidx.compose.animation.core.Easing
            public final float transform(float f) {
                return Interpolator.this.getInterpolation(f);
            }
        };
        final PathInterpolator pathInterpolator5 = InterpolatorsAndroidX.LEGACY;
        new Easing() { // from class: com.android.compose.animation.Easings$fromInterpolator$1
            @Override // androidx.compose.animation.core.Easing
            public final float transform(float f) {
                return Interpolator.this.getInterpolation(f);
            }
        };
        final PathInterpolator pathInterpolator6 = InterpolatorsAndroidX.LEGACY_DECELERATE;
        new Easing() { // from class: com.android.compose.animation.Easings$fromInterpolator$1
            @Override // androidx.compose.animation.core.Easing
            public final float transform(float f) {
                return Interpolator.this.getInterpolation(f);
            }
        };
    }
}

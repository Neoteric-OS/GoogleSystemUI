package com.android.app.animation;

import android.graphics.Path;
import androidx.core.animation.AccelerateDecelerateInterpolator;
import androidx.core.animation.AccelerateInterpolator;
import androidx.core.animation.DecelerateInterpolator;
import androidx.core.animation.Interpolator;
import androidx.core.animation.LinearInterpolator;
import androidx.core.animation.PathInterpolator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class InterpolatorsAndroidX {
    public static final AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = null;
    public static final PathInterpolator ALPHA_IN;
    public static final PathInterpolator ALPHA_OUT;
    public static final DecelerateInterpolator DECELERATE_3 = null;
    public static final DecelerateInterpolator DECELERATE_QUINT;
    public static final PathInterpolator EMPHASIZED;
    public static final PathInterpolator EMPHASIZED_ACCELERATE = null;
    public static final PathInterpolator EMPHASIZED_DECELERATE = null;
    public static final PathInterpolator FAST_OUT_SLOW_IN;
    public static final PathInterpolator LEGACY;
    public static final PathInterpolator LEGACY_ACCELERATE = null;
    public static final PathInterpolator LEGACY_DECELERATE;
    public static final LinearInterpolator LINEAR;
    public static final PathInterpolator LINEAR_OUT_SLOW_IN;
    public static final PathInterpolator STANDARD;
    public static final PathInterpolator STANDARD_ACCELERATE;
    public static final PathInterpolator STANDARD_DECELERATE;
    public static final PathInterpolator TOUCH_RESPONSE = null;
    public static final AnonymousClass2 ZOOM_OUT = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.app.animation.InterpolatorsAndroidX$2, reason: invalid class name */
    public final class AnonymousClass2 implements Interpolator {
        @Override // androidx.core.animation.Interpolator
        public final float getInterpolation(float f) {
            return (1.0f - (0.35f / (f + 0.35f))) / 0.7407408f;
        }
    }

    static {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(0.05f, 0.0f, 0.133333f, 0.06f, 0.166666f, 0.4f);
        path.cubicTo(0.208333f, 0.82f, 0.25f, 1.0f, 1.0f, 1.0f);
        PathInterpolator pathInterpolator = new PathInterpolator();
        pathInterpolator.initPath(path);
        EMPHASIZED = pathInterpolator;
        Path path2 = new Path();
        path2.moveTo(0.0f, 0.0f);
        path2.cubicTo(0.1217f, 0.0462f, 0.15f, 0.4686f, 0.1667f, 0.66f);
        path2.cubicTo(0.1834f, 0.8878f, 0.1667f, 1.0f, 1.0f, 1.0f);
        new PathInterpolator().initPath(path2);
        new PathInterpolator(0.3f, 0.0f, 0.8f, 0.15f);
        new PathInterpolator(0.05f, 0.7f, 0.1f, 1.0f);
        Path path3 = new Path();
        path3.moveTo(0.0f, 0.0f);
        path3.cubicTo(0.05f, 0.0f, 0.133333f, 0.08f, 0.166666f, 0.4f);
        path3.cubicTo(0.225f, 0.94f, 0.5f, 1.0f, 1.0f, 1.0f);
        new PathInterpolator().initPath(path3);
        STANDARD = new PathInterpolator(0.2f, 0.0f, 0.0f, 1.0f);
        STANDARD_ACCELERATE = new PathInterpolator(0.3f, 0.0f, 1.0f, 1.0f);
        STANDARD_DECELERATE = new PathInterpolator(0.0f, 0.0f, 0.0f, 1.0f);
        PathInterpolator pathInterpolator2 = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
        LEGACY = pathInterpolator2;
        new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f);
        PathInterpolator pathInterpolator3 = new PathInterpolator(0.0f, 0.0f, 0.2f, 1.0f);
        LEGACY_DECELERATE = pathInterpolator3;
        LINEAR = new LinearInterpolator();
        FAST_OUT_SLOW_IN = pathInterpolator2;
        LINEAR_OUT_SLOW_IN = pathInterpolator3;
        new PathInterpolator(0.8f, 0.0f, 0.6f, 1.0f);
        new PathInterpolator(0.8f, 0.0f, 1.0f, 1.0f);
        new PathInterpolator(0.2f, 0.0f, 0.0f, 1.0f);
        new PathInterpolator(0.6f, 0.0f, 0.4f, 1.0f);
        new PathInterpolator(0.0f, 0.0f, 0.2f, 1.0f);
        new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f);
        ALPHA_IN = new PathInterpolator(0.4f, 0.0f, 1.0f, 1.0f);
        ALPHA_OUT = new PathInterpolator(0.0f, 0.0f, 0.8f, 1.0f);
        new AccelerateInterpolator(0.5f);
        new AccelerateInterpolator(0.75f);
        new AccelerateInterpolator(1.5f);
        new AccelerateInterpolator(2.0f);
        DECELERATE_QUINT = new DecelerateInterpolator(2.5f);
        new DecelerateInterpolator(3.0f);
        new PathInterpolator(0.4f, 0.0f, 0.6f, 1.0f);
        new PathInterpolator(0.4f, 0.0f, 0.2f, 1.4f);
        new PathInterpolator(0.4f, 0.0f, 0.2f, 1.1f);
        new PathInterpolator(0.3f, 0.0f, 0.5f, 1.0f);
        new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
        new PathInterpolator(0.3f, 0.0f, 0.1f, 1.0f);
        new PathInterpolator(0.9f, 0.0f, 0.7f, 1.0f);
        new PathInterpolator(0.1f, 0.1f, 0.0f, 1.0f);
    }
}

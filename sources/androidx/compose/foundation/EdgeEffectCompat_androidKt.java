package androidx.compose.foundation;

import android.view.ViewConfiguration;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class EdgeEffectCompat_androidKt {
    public static final double DecelMinusOne;
    public static final double DecelerationRate;
    public static final float PlatformFlingScrollFriction = ViewConfiguration.getScrollFriction();

    static {
        double log = Math.log(0.78d) / Math.log(0.9d);
        DecelerationRate = log;
        DecelMinusOne = log - 1.0d;
    }
}

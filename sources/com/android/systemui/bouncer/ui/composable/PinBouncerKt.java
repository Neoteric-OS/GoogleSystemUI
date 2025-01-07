package com.android.systemui.bouncer.ui.composable;

import com.android.compose.animation.Easings;
import com.android.compose.animation.Easings$fromInterpolator$1;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PinBouncerKt {
    public static final float pinButtonMaxSize = 84;
    public static final Easings$fromInterpolator$1 pinButtonPressedEasing = null;
    public static final Easings$fromInterpolator$1 pinButtonReleasedEasing = null;

    static {
        int i = Duration.$r8$clinit;
        DurationUnit durationUnit = DurationUnit.MILLISECONDS;
        DurationKt.toDuration(100, durationUnit);
        Easings$fromInterpolator$1 easings$fromInterpolator$1 = Easings.Linear;
        DurationKt.toDuration(33, durationUnit);
        DurationKt.toDuration(420, durationUnit);
        Easings$fromInterpolator$1 easings$fromInterpolator$12 = Easings.Standard;
    }
}

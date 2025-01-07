package com.android.systemui.keyguard.ui.binder;

import android.os.VibrationEffect;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class KeyguardBottomAreaVibrations {
    public static final VibrationEffect Activated;
    public static final VibrationEffect Deactivated;
    public static final VibrationEffect Shake;
    public static final long ShakeAnimationDuration;

    static {
        int i = Duration.$r8$clinit;
        ShakeAnimationDuration = DurationKt.toDuration(300, DurationUnit.MILLISECONDS);
        VibrationEffect.Composition startComposition = VibrationEffect.startComposition();
        int m1777getInWholeMillisecondsimpl = (int) (Duration.m1777getInWholeMillisecondsimpl(r0) / 10.0f);
        for (int i2 = 0; i2 < 10; i2++) {
            startComposition.addPrimitive(7, 0.3f, m1777getInWholeMillisecondsimpl);
        }
        Shake = startComposition.compose();
        Activated = VibrationEffect.startComposition().addPrimitive(7, 0.6f, 0).addPrimitive(4, 0.1f, 0).compose();
        Deactivated = VibrationEffect.startComposition().addPrimitive(7, 0.6f, 0).addPrimitive(6, 0.1f, 0).compose();
    }
}

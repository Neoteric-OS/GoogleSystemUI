package com.android.systemui.util.animation;

import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AnimationUtil$Companion {
    public static long getFrames(int i) {
        if (i >= 0) {
            return MathKt.roundToLong((i * 1000.0f) / 60.0f);
        }
        throw new IllegalArgumentException("numFrames must be >= 0");
    }
}

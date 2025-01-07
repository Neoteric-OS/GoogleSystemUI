package com.google.android.systemui.screenshot.surfaceeffects.utils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MathUtils {
    public static float constrainedMap(float f, float f2) {
        float f3 = f == 1000.0f ? 0.0f : (f2 - f) / (1000.0f - f);
        return ((-1.0f) * (f3 >= 0.0f ? f3 > 1.0f ? 1.0f : f3 : 0.0f)) + 1.0f;
    }
}

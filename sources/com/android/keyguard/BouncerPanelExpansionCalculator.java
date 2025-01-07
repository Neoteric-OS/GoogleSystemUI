package com.android.keyguard;

import android.util.MathUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BouncerPanelExpansionCalculator {
    public static final float aboutToShowBouncerProgress(float f) {
        return MathUtils.constrain((f - 0.9f) / 0.1f, 0.0f, 1.0f);
    }

    public static final float showBouncerProgress(float f) {
        if (f >= 0.9f) {
            return 1.0f;
        }
        if (f < 0.6d) {
            return 0.0f;
        }
        return (f - 0.6f) / 0.3f;
    }
}

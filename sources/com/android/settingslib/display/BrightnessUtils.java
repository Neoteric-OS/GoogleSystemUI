package com.android.settingslib.display;

import android.util.MathUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BrightnessUtils {
    public static final float convertGammaToLinearFloat(int i, float f, float f2) {
        float norm = MathUtils.norm(0.0f, 65535.0f, i);
        return MathUtils.lerp(f, f2, MathUtils.constrain(norm <= 0.5f ? MathUtils.sq(norm / 0.5f) : MathUtils.exp((norm - 0.5599107f) / 0.17883277f) + 0.28466892f, 0.0f, 12.0f) / 12.0f);
    }

    public static final int convertLinearToGammaFloat(float f, float f2, float f3) {
        float norm = MathUtils.norm(f2, f3, f) * 12.0f;
        return Math.round(MathUtils.lerp(0, 65535, norm <= 1.0f ? MathUtils.sqrt(norm) * 0.5f : (MathUtils.log(norm - 0.28466892f) * 0.17883277f) + 0.5599107f));
    }
}

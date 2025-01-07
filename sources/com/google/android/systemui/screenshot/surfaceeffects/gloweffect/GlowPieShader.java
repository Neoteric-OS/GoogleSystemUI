package com.google.android.systemui.screenshot.surfaceeffects.gloweffect;

import android.graphics.RuntimeShader;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GlowPieShader extends RuntimeShader {
    public final void applyConfig(GlowPieEffectConfig glowPieEffectConfig) {
        setFloatUniform("in_center", glowPieEffectConfig.centerX, glowPieEffectConfig.centerY);
        setFloatUniform("in_size", glowPieEffectConfig.width, glowPieEffectConfig.height);
        setFloatUniform("in_cornerRad", glowPieEffectConfig.cornerRadius);
        int[] iArr = glowPieEffectConfig.colors;
        setColorUniform("in_colors0", iArr[0]);
        setColorUniform("in_colors1", iArr[1]);
        setColorUniform("in_colors2", iArr[2]);
    }
}

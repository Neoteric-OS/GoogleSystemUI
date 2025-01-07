package com.google.android.systemui.screenshot.surfaceeffects.revealeffect;

import android.graphics.RuntimeShader;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RippleRevealShader extends RuntimeShader {
    public final void applyConfig(RippleRevealEffectConfig rippleRevealEffectConfig) {
        setFloatUniform("in_center", rippleRevealEffectConfig.centerX, rippleRevealEffectConfig.centerY);
        setFloatUniform("in_innerRadius", rippleRevealEffectConfig.innerRadiusStart);
        setFloatUniform("in_outerRadius", rippleRevealEffectConfig.outerRadiusStart);
        setFloatUniform("in_blur", 0.5f);
        setFloatUniform("in_pixelDensity", rippleRevealEffectConfig.pixelDensity);
        setFloatUniform("in_sparkleScale", 0.5f);
        setFloatUniform("in_sparkleStrength", 0.3f);
        setColorUniform("in_innerColor", rippleRevealEffectConfig.innerColor);
        setColorUniform("in_outerColor", rippleRevealEffectConfig.outerColor);
    }
}

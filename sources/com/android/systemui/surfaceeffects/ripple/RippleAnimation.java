package com.android.systemui.surfaceeffects.ripple;

import android.animation.ValueAnimator;
import androidx.core.graphics.ColorUtils;
import com.android.systemui.surfaceeffects.ripple.RippleShader;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RippleAnimation {
    public final RippleAnimationConfig config;
    public final RippleShader rippleShader = new RippleShader(RippleShader.RippleShape.CIRCLE);
    public final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);

    public RippleAnimation(RippleAnimationConfig rippleAnimationConfig) {
        this.config = rippleAnimationConfig;
        applyConfigToShader();
    }

    public final void applyConfigToShader() {
        RippleAnimationConfig rippleAnimationConfig = this.config;
        RippleShader rippleShader = this.rippleShader;
        rippleShader.setFloatUniform("in_center", rippleAnimationConfig.centerX, rippleAnimationConfig.centerY);
        RippleShader.RippleSize rippleSize = rippleShader.rippleSize;
        rippleSize.getClass();
        rippleSize.setSizeAtProgresses(rippleSize.initialSize, new RippleShader.SizeAtProgress(1.0f, rippleAnimationConfig.maxWidth, rippleAnimationConfig.maxHeight));
        rippleShader.setPixelDensity(rippleAnimationConfig.pixelDensity);
        rippleShader.setColorUniform("in_color", ColorUtils.setAlphaComponent(rippleAnimationConfig.color, 100));
        rippleShader.setFloatUniform("in_sparkle_strength", 0.0f);
    }

    public static /* synthetic */ void getRippleShader$annotations() {
    }
}

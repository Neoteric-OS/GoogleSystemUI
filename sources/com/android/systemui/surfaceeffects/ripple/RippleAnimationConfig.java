package com.android.systemui.surfaceeffects.ripple;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.surfaceeffects.ripple.RippleShader;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RippleAnimationConfig {
    public final float centerX;
    public final float centerY;
    public int color;
    public final float maxHeight;
    public final float maxWidth;
    public final float pixelDensity;

    public RippleAnimationConfig(float f, float f2, float f3, float f4, float f5, int i) {
        RippleShader.RippleShape rippleShape = RippleShader.RippleShape.CIRCLE;
        this.centerX = f;
        this.centerY = f2;
        this.maxWidth = f3;
        this.maxHeight = f4;
        this.pixelDensity = f5;
        this.color = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RippleAnimationConfig)) {
            return false;
        }
        RippleAnimationConfig rippleAnimationConfig = (RippleAnimationConfig) obj;
        rippleAnimationConfig.getClass();
        RippleShader.RippleShape rippleShape = RippleShader.RippleShape.CIRCLE;
        return Float.compare(this.centerX, rippleAnimationConfig.centerX) == 0 && Float.compare(this.centerY, rippleAnimationConfig.centerY) == 0 && Float.compare(this.maxWidth, rippleAnimationConfig.maxWidth) == 0 && Float.compare(this.maxHeight, rippleAnimationConfig.maxHeight) == 0 && Float.compare(this.pixelDensity, rippleAnimationConfig.pixelDensity) == 0 && this.color == rippleAnimationConfig.color && Float.compare(0.0f, 0.0f) == 0 && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual((Object) null, (Object) null) && Intrinsics.areEqual((Object) null, (Object) null);
    }

    public final int hashCode() {
        return Boolean.hashCode(false) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(100, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.color, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(RippleShader.RippleShape.CIRCLE.hashCode() * 31, 31, 1500L), this.centerX, 31), this.centerY, 31), this.maxWidth, 31), this.maxHeight, 31), this.pixelDensity, 31), 31), 31), 0.0f, 923521);
    }

    public final String toString() {
        return "RippleAnimationConfig(rippleShape=" + RippleShader.RippleShape.CIRCLE + ", duration=1500, centerX=" + this.centerX + ", centerY=" + this.centerY + ", maxWidth=" + this.maxWidth + ", maxHeight=" + this.maxHeight + ", pixelDensity=" + this.pixelDensity + ", color=" + this.color + ", opacity=100, sparkleStrength=0.0, baseRingFadeParams=null, sparkleRingFadeParams=null, centerFillFadeParams=null, shouldDistort=false)";
    }
}

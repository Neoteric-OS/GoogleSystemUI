package com.google.android.systemui.screenshot.surfaceeffects.revealeffect;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RippleRevealEffectConfig {
    public final float centerX;
    public final float centerY;
    public final int innerColor;
    public final float innerRadiusEnd;
    public final float innerRadiusStart;
    public final int outerColor;
    public final float outerRadiusEnd;
    public final float outerRadiusStart;
    public final float pixelDensity;

    public RippleRevealEffectConfig(float f, float f2, float f3, float f4, float f5, float f6, float f7, int i, int i2) {
        this.centerX = f;
        this.centerY = f2;
        this.innerRadiusStart = f3;
        this.innerRadiusEnd = f4;
        this.outerRadiusStart = f5;
        this.outerRadiusEnd = f6;
        this.pixelDensity = f7;
        this.innerColor = i;
        this.outerColor = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RippleRevealEffectConfig)) {
            return false;
        }
        RippleRevealEffectConfig rippleRevealEffectConfig = (RippleRevealEffectConfig) obj;
        rippleRevealEffectConfig.getClass();
        return Float.compare(1000.0f, 1000.0f) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(130.0f, 130.0f) == 0 && Float.compare(this.centerX, rippleRevealEffectConfig.centerX) == 0 && Float.compare(this.centerY, rippleRevealEffectConfig.centerY) == 0 && Float.compare(this.innerRadiusStart, rippleRevealEffectConfig.innerRadiusStart) == 0 && Float.compare(this.innerRadiusEnd, rippleRevealEffectConfig.innerRadiusEnd) == 0 && Float.compare(this.outerRadiusStart, rippleRevealEffectConfig.outerRadiusStart) == 0 && Float.compare(this.outerRadiusEnd, rippleRevealEffectConfig.outerRadiusEnd) == 0 && Float.compare(this.pixelDensity, rippleRevealEffectConfig.pixelDensity) == 0 && Float.compare(0.5f, 0.5f) == 0 && this.innerColor == rippleRevealEffectConfig.innerColor && this.outerColor == rippleRevealEffectConfig.outerColor && Float.compare(0.3f, 0.3f) == 0 && Float.compare(0.5f, 0.5f) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(0.5f) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.outerColor, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.innerColor, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(1000.0f) * 31, 0.0f, 31), 130.0f, 31), this.centerX, 31), this.centerY, 31), this.innerRadiusStart, 31), this.innerRadiusEnd, 31), this.outerRadiusStart, 31), this.outerRadiusEnd, 31), this.pixelDensity, 31), 0.5f, 31), 31), 31), 0.3f, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("RippleRevealEffectConfig(duration=1000.0, innerFadeOutStart=0.0, outerFadeOutStart=130.0, centerX=");
        sb.append(this.centerX);
        sb.append(", centerY=");
        sb.append(this.centerY);
        sb.append(", innerRadiusStart=");
        sb.append(this.innerRadiusStart);
        sb.append(", innerRadiusEnd=");
        sb.append(this.innerRadiusEnd);
        sb.append(", outerRadiusStart=");
        sb.append(this.outerRadiusStart);
        sb.append(", outerRadiusEnd=");
        sb.append(this.outerRadiusEnd);
        sb.append(", pixelDensity=");
        sb.append(this.pixelDensity);
        sb.append(", blurAmount=0.5, innerColor=");
        sb.append(this.innerColor);
        sb.append(", outerColor=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.outerColor, ", sparkleStrength=0.3, sparkleScale=0.5)");
    }
}

package com.android.systemui.surfaceeffects.turbulencenoise;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.foundation.shape.DpCornerSize$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.DarkIconDispatcher;
import java.util.Random;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TurbulenceNoiseAnimationConfig {
    public final int color;
    public final float height;
    public final float noiseOffsetX;
    public final float noiseOffsetY;
    public final float noiseOffsetZ;
    public final float pixelDensity;
    public final float width;

    static {
        new Random();
    }

    public TurbulenceNoiseAnimationConfig(float f, float f2, float f3, float f4, float f5, float f6, int i) {
        this.noiseOffsetX = f;
        this.noiseOffsetY = f2;
        this.noiseOffsetZ = f3;
        this.color = i;
        this.width = f4;
        this.height = f5;
        this.pixelDensity = f6;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TurbulenceNoiseAnimationConfig)) {
            return false;
        }
        TurbulenceNoiseAnimationConfig turbulenceNoiseAnimationConfig = (TurbulenceNoiseAnimationConfig) obj;
        turbulenceNoiseAnimationConfig.getClass();
        return Float.compare(2.14f, 2.14f) == 0 && Float.compare(1.0f, 1.0f) == 0 && Float.compare(this.noiseOffsetX, turbulenceNoiseAnimationConfig.noiseOffsetX) == 0 && Float.compare(this.noiseOffsetY, turbulenceNoiseAnimationConfig.noiseOffsetY) == 0 && Float.compare(this.noiseOffsetZ, turbulenceNoiseAnimationConfig.noiseOffsetZ) == 0 && Float.compare(0.42f, 0.42f) == 0 && Float.compare(0.0f, 0.0f) == 0 && Float.compare(0.3f, 0.3f) == 0 && this.color == turbulenceNoiseAnimationConfig.color && Float.compare(this.width, turbulenceNoiseAnimationConfig.width) == 0 && Float.compare(this.height, turbulenceNoiseAnimationConfig.height) == 0 && Float.compare(30000.0f, 30000.0f) == 0 && Float.compare(1350.0f, 1350.0f) == 0 && Float.compare(1350.0f, 1350.0f) == 0 && Float.compare(this.pixelDensity, turbulenceNoiseAnimationConfig.pixelDensity) == 0 && Float.compare(0.26f, 0.26f) == 0 && Float.compare(0.09f, 0.09f) == 0;
    }

    public final int hashCode() {
        return Boolean.hashCode(false) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.color, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(2.14f) * 31, 1.0f, 31), this.noiseOffsetX, 31), this.noiseOffsetY, 31), this.noiseOffsetZ, 31), 0.42f, 31), 0.0f, 31), 0.3f, 31), 31), 31), this.width, 31), this.height, 31), 30000.0f, 31), 1350.0f, 31), 1350.0f, 31), this.pixelDensity, 31), 0.26f, 31), 0.09f, 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TurbulenceNoiseAnimationConfig(gridCount=2.14, luminosityMultiplier=1.0, noiseOffsetX=");
        sb.append(this.noiseOffsetX);
        sb.append(", noiseOffsetY=");
        sb.append(this.noiseOffsetY);
        sb.append(", noiseOffsetZ=");
        sb.append(this.noiseOffsetZ);
        sb.append(", noiseMoveSpeedX=0.42, noiseMoveSpeedY=0.0, noiseMoveSpeedZ=0.3, color=");
        sb.append(this.color);
        sb.append(", screenColor=-16777216, width=");
        sb.append(this.width);
        sb.append(", height=");
        sb.append(this.height);
        sb.append(", maxDuration=30000.0, easeInDuration=1350.0, easeOutDuration=1350.0, pixelDensity=");
        return DpCornerSize$$ExternalSyntheticOutline0.m(sb, this.pixelDensity, ", lumaMatteBlendFactor=0.26, lumaMatteOverallBrightness=0.09, shouldInverseNoiseLuminosity=false)");
    }
}

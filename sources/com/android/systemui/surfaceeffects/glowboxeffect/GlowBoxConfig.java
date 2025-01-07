package com.android.systemui.surfaceeffects.glowboxeffect;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GlowBoxConfig {
    public final int color;
    public final float endCenterX;
    public final float endCenterY;
    public final float height;
    public final float startCenterX;
    public final float startCenterY;
    public final float width;

    public GlowBoxConfig(float f, float f2, float f3, float f4, float f5, float f6, int i) {
        this.startCenterX = f;
        this.startCenterY = f2;
        this.endCenterX = f3;
        this.endCenterY = f4;
        this.width = f5;
        this.height = f6;
        this.color = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GlowBoxConfig)) {
            return false;
        }
        GlowBoxConfig glowBoxConfig = (GlowBoxConfig) obj;
        return Float.compare(this.startCenterX, glowBoxConfig.startCenterX) == 0 && Float.compare(this.startCenterY, glowBoxConfig.startCenterY) == 0 && Float.compare(this.endCenterX, glowBoxConfig.endCenterX) == 0 && Float.compare(this.endCenterY, glowBoxConfig.endCenterY) == 0 && Float.compare(this.width, glowBoxConfig.width) == 0 && Float.compare(this.height, glowBoxConfig.height) == 0 && this.color == glowBoxConfig.color && Float.compare(700.0f, 700.0f) == 0;
    }

    public final int hashCode() {
        return Long.hashCode(800L) + Scale$$ExternalSyntheticOutline0.m(Scale$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.color, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.startCenterX) * 31, this.startCenterY, 31), this.endCenterX, 31), this.endCenterY, 31), this.width, 31), this.height, 31), 31), 700.0f, 31), 31, 3000L), 31, 800L);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("GlowBoxConfig(startCenterX=");
        sb.append(this.startCenterX);
        sb.append(", startCenterY=");
        sb.append(this.startCenterY);
        sb.append(", endCenterX=");
        sb.append(this.endCenterX);
        sb.append(", endCenterY=");
        sb.append(this.endCenterY);
        sb.append(", width=");
        sb.append(this.width);
        sb.append(", height=");
        sb.append(this.height);
        sb.append(", color=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.color, ", blurAmount=700.0, duration=3000, easeInDuration=800, easeOutDuration=800)");
    }
}

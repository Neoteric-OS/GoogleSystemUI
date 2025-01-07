package com.google.android.systemui.screenshot.surfaceeffects.gloweffect;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GlowPieEffectConfig {
    public final float centerX;
    public final float centerY;
    public final int[] colors;
    public final float cornerRadius;
    public final float height;
    public final float width;

    public GlowPieEffectConfig(float f, float f2, float f3, float f4, float f5, int[] iArr) {
        this.centerX = f;
        this.centerY = f2;
        this.width = f3;
        this.height = f4;
        this.cornerRadius = f5;
        this.colors = iArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GlowPieEffectConfig)) {
            return false;
        }
        GlowPieEffectConfig glowPieEffectConfig = (GlowPieEffectConfig) obj;
        return Float.compare(this.centerX, glowPieEffectConfig.centerX) == 0 && Float.compare(this.centerY, glowPieEffectConfig.centerY) == 0 && Float.compare(this.width, glowPieEffectConfig.width) == 0 && Float.compare(this.height, glowPieEffectConfig.height) == 0 && Float.compare(this.cornerRadius, glowPieEffectConfig.cornerRadius) == 0 && Intrinsics.areEqual(this.colors, glowPieEffectConfig.colors);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.colors) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.centerX) * 31, this.centerY, 31), this.width, 31), this.height, 31), this.cornerRadius, 31);
    }

    public final String toString() {
        return "GlowPieEffectConfig(centerX=" + this.centerX + ", centerY=" + this.centerY + ", width=" + this.width + ", height=" + this.height + ", cornerRadius=" + this.cornerRadius + ", colors=" + Arrays.toString(this.colors) + ")";
    }
}

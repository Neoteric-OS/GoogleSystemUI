package com.android.systemui.statusbar;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CircleReveal implements LightRevealEffect {
    public final int centerX;
    public final int centerY;
    public final int endRadius;

    public CircleReveal(int i, int i2, int i3) {
        this.centerX = i;
        this.centerY = i2;
        this.endRadius = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CircleReveal)) {
            return false;
        }
        CircleReveal circleReveal = (CircleReveal) obj;
        return this.centerX == circleReveal.centerX && this.centerY == circleReveal.centerY && this.endRadius == circleReveal.endRadius;
    }

    public final int hashCode() {
        return Integer.hashCode(this.endRadius) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(0, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.centerY, Integer.hashCode(this.centerX) * 31, 31), 31);
    }

    @Override // com.android.systemui.statusbar.LightRevealEffect
    public final void setRevealAmountOnScrim(float f, LightRevealScrim lightRevealScrim) {
        float coerceAtLeast = RangesKt.coerceAtLeast(f - 0.5f, 0.0f) * 2.0f;
        float f2 = (this.endRadius * f) + 0;
        lightRevealScrim.interpolatedRevealAmount = f;
        lightRevealScrim.setRevealGradientEndColorAlpha(1.0f - coerceAtLeast);
        float f3 = this.centerX;
        float f4 = this.centerY;
        lightRevealScrim.setRevealGradientBounds(f3 - f2, f4 - f2, f3 + f2, f4 + f2);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CircleReveal(centerX=");
        sb.append(this.centerX);
        sb.append(", centerY=");
        sb.append(this.centerY);
        sb.append(", startRadius=0, endRadius=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.endRadius, ")");
    }
}

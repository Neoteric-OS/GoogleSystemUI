package com.android.systemui.statusbar;

import android.util.MathUtils;
import android.view.animation.PathInterpolator;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LinearLightRevealEffect implements LightRevealEffect {
    public final PathInterpolator interpolator = new PathInterpolator(0.4f, 0.0f, 0.2f, 1.0f);
    public final boolean isVertical;

    public LinearLightRevealEffect(boolean z) {
        this.isVertical = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LinearLightRevealEffect) && this.isVertical == ((LinearLightRevealEffect) obj).isVertical;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isVertical);
    }

    @Override // com.android.systemui.statusbar.LightRevealEffect
    public final void setRevealAmountOnScrim(float f, LightRevealScrim lightRevealScrim) {
        float interpolation = this.interpolator.getInterpolation(f);
        lightRevealScrim.interpolatedRevealAmount = interpolation;
        float coerceAtLeast = RangesKt.coerceAtLeast((1 - interpolation) - 0.7f, 0.0f) * 3.3333333f;
        if (lightRevealScrim.startColorAlpha != coerceAtLeast) {
            lightRevealScrim.startColorAlpha = coerceAtLeast;
            lightRevealScrim.invalidate();
        }
        lightRevealScrim.setRevealGradientEndColorAlpha(1.0f - (RangesKt.coerceAtLeast(interpolation - 0.6f, 0.0f) * 2.5000002f));
        float lerp = MathUtils.lerp(0.3f, 1.0f, interpolation);
        if (this.isVertical) {
            float f2 = lightRevealScrim.viewWidth / 2;
            float f3 = lerp * f2;
            lightRevealScrim.setRevealGradientBounds(f2 - f3, 0.0f, f3 + f2, lightRevealScrim.viewHeight);
        } else {
            float f4 = lightRevealScrim.viewHeight / 2;
            float f5 = lerp * f4;
            lightRevealScrim.setRevealGradientBounds(0.0f, f4 - f5, lightRevealScrim.viewWidth, f5 + f4);
        }
    }

    public final String toString() {
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("LinearLightRevealEffect(isVertical="), this.isVertical, ")");
    }
}

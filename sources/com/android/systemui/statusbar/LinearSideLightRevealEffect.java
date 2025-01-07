package com.android.systemui.statusbar;

import android.util.MathUtils;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LinearSideLightRevealEffect implements LightRevealEffect {
    public final boolean isVertical;

    public LinearSideLightRevealEffect(boolean z) {
        this.isVertical = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LinearSideLightRevealEffect) && this.isVertical == ((LinearSideLightRevealEffect) obj).isVertical;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isVertical);
    }

    @Override // com.android.systemui.statusbar.LightRevealEffect
    public final void setRevealAmountOnScrim(float f, LightRevealScrim lightRevealScrim) {
        lightRevealScrim.interpolatedRevealAmount = f;
        float coerceAtLeast = RangesKt.coerceAtLeast((1 - f) - 0.0f, 0.0f) * 1.0f;
        if (lightRevealScrim.startColorAlpha != coerceAtLeast) {
            lightRevealScrim.startColorAlpha = coerceAtLeast;
            lightRevealScrim.invalidate();
        }
        lightRevealScrim.setRevealGradientEndColorAlpha(1.0f - (RangesKt.coerceAtLeast(f - 0.95f, 0.0f) * 19.999996f));
        float lerp = MathUtils.lerp(0.95f, 1.0f, f);
        if (this.isVertical) {
            int i = lightRevealScrim.viewWidth;
            float f2 = lightRevealScrim.viewHeight;
            lightRevealScrim.setRevealGradientBounds((-i) * lerp, (-r1) * lerp, i * lerp, (lerp * f2) + f2);
            return;
        }
        int i2 = lightRevealScrim.viewWidth;
        float f3 = i2;
        lightRevealScrim.setRevealGradientBounds((-i2) * lerp, (-r1) * lerp, (f3 * lerp) + f3, lightRevealScrim.viewHeight * lerp);
    }

    public final String toString() {
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("LinearSideLightRevealEffect(isVertical="), this.isVertical, ")");
    }
}

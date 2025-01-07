package com.android.systemui.statusbar;

import android.view.animation.PathInterpolator;
import com.android.app.animation.Interpolators;
import com.android.systemui.util.leak.RotationUtils;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PowerButtonReveal implements LightRevealEffect {
    public final float powerButtonY;

    public PowerButtonReveal(float f) {
        this.powerButtonY = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PowerButtonReveal) && Float.compare(this.powerButtonY, ((PowerButtonReveal) obj).powerButtonY) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.powerButtonY);
    }

    @Override // com.android.systemui.statusbar.LightRevealEffect
    public final void setRevealAmountOnScrim(float f, LightRevealScrim lightRevealScrim) {
        float interpolation = ((PathInterpolator) Interpolators.FAST_OUT_SLOW_IN_REVERSE).getInterpolation(f);
        lightRevealScrim.setRevealGradientEndColorAlpha(1.0f - ((1.0f / (1.0f - 0.5f)) * RangesKt.coerceAtLeast(interpolation - 0.5f, 0.0f)));
        lightRevealScrim.interpolatedRevealAmount = interpolation;
        int rotation = RotationUtils.getRotation(lightRevealScrim.getContext());
        float f2 = this.powerButtonY;
        if (rotation == 0) {
            lightRevealScrim.setRevealGradientBounds((lightRevealScrim.getWidth() * 1.05f) - ((lightRevealScrim.getWidth() * 1.25f) * interpolation), f2 - (lightRevealScrim.getHeight() * interpolation), (lightRevealScrim.getWidth() * 1.25f * interpolation) + (lightRevealScrim.getWidth() * 1.05f), (lightRevealScrim.getHeight() * interpolation) + f2);
            return;
        }
        if (rotation == 1) {
            lightRevealScrim.setRevealGradientBounds(f2 - (lightRevealScrim.getWidth() * interpolation), ((-lightRevealScrim.getHeight()) * 0.05f) - ((lightRevealScrim.getHeight() * 1.25f) * interpolation), (lightRevealScrim.getWidth() * interpolation) + f2, (lightRevealScrim.getHeight() * 1.25f * interpolation) + ((-lightRevealScrim.getHeight()) * 0.05f));
            return;
        }
        lightRevealScrim.setRevealGradientBounds((lightRevealScrim.getWidth() - f2) - (lightRevealScrim.getWidth() * interpolation), (lightRevealScrim.getHeight() * 1.05f) - ((lightRevealScrim.getHeight() * 1.25f) * interpolation), (lightRevealScrim.getWidth() * interpolation) + (lightRevealScrim.getWidth() - f2), (lightRevealScrim.getHeight() * 1.25f * interpolation) + (lightRevealScrim.getHeight() * 1.05f));
    }

    public final String toString() {
        return "PowerButtonReveal(powerButtonY=" + this.powerButtonY + ")";
    }
}

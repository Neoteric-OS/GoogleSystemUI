package com.android.systemui.statusbar;

import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.android.app.animation.Interpolators;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LiftReveal implements LightRevealEffect {
    public static final LiftReveal INSTANCE = new LiftReveal();
    public static final Interpolator INTERPOLATOR = Interpolators.FAST_OUT_SLOW_IN_REVERSE;

    @Override // com.android.systemui.statusbar.LightRevealEffect
    public final void setRevealAmountOnScrim(float f, LightRevealScrim lightRevealScrim) {
        float interpolation = ((PathInterpolator) INTERPOLATOR).getInterpolation(f);
        float coerceAtLeast = (1.0f / (1.0f - 0.35f)) * RangesKt.coerceAtLeast(interpolation - 0.35f, 0.0f);
        lightRevealScrim.setRevealGradientEndColorAlpha(1.0f - ((1.0f / (1.0f - 0.85f)) * RangesKt.coerceAtLeast(f - 0.85f, 0.0f)));
        lightRevealScrim.setRevealGradientBounds(((-lightRevealScrim.getWidth()) * coerceAtLeast) + (lightRevealScrim.getWidth() * 0.25f), (lightRevealScrim.getHeight() * 1.1f) - (lightRevealScrim.getHeight() * interpolation), (lightRevealScrim.getWidth() * coerceAtLeast) + (lightRevealScrim.getWidth() * 0.75f), (lightRevealScrim.getHeight() * interpolation) + (lightRevealScrim.getHeight() * 1.2f));
    }
}

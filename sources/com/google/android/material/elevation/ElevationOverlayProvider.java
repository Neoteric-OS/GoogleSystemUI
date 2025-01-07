package com.google.android.material.elevation;

import android.content.Context;
import com.android.wm.shell.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialAttributes;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ElevationOverlayProvider {
    public static final int OVERLAY_ACCENT_COLOR_ALPHA = (int) Math.round(5.1000000000000005d);
    public final int colorSurface;
    public final float displayDensity;
    public final int elevationOverlayAccentColor;
    public final int elevationOverlayColor;
    public final boolean elevationOverlayEnabled;

    public ElevationOverlayProvider(Context context) {
        boolean resolveBoolean = MaterialAttributes.resolveBoolean(context, R.attr.elevationOverlayEnabled, false);
        int color = MaterialColors.getColor(R.attr.elevationOverlayColor, context);
        int color2 = MaterialColors.getColor(R.attr.elevationOverlayAccentColor, context);
        int color3 = MaterialColors.getColor(R.attr.colorSurface, context);
        float f = context.getResources().getDisplayMetrics().density;
        this.elevationOverlayEnabled = resolveBoolean;
        this.elevationOverlayColor = color;
        this.elevationOverlayAccentColor = color2;
        this.colorSurface = color3;
        this.displayDensity = f;
    }
}

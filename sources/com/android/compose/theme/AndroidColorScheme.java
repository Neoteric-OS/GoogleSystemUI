package com.android.compose.theme;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidColorScheme {
    public final DeprecatedValues deprecated;
    public final long onPrimary;
    public final long onPrimaryContainer;
    public final long onPrimaryFixed;
    public final long onPrimaryFixedVariant;
    public final long onSecondary;
    public final long onSecondaryFixed;
    public final long onSecondaryFixedVariant;
    public final long onSurface;
    public final long onSurfaceVariant;
    public final long onTertiary;
    public final long onTertiaryFixed;
    public final long onTertiaryFixedVariant;
    public final long primary;
    public final long primaryContainer;
    public final long primaryFixedDim;
    public final long secondary;
    public final long secondaryFixedDim;
    public final long surfaceBright;
    public final long surfaceContainer;
    public final long surfaceDim;
    public final long tertiary;
    public final long tertiaryFixedDim;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        /* renamed from: getColor-WaAFU9c, reason: not valid java name */
        public static long m745getColorWaAFU9c(int i, Context context) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
            int color = obtainStyledAttributes.getColor(0, 0);
            obtainStyledAttributes.recycle();
            return androidx.compose.ui.graphics.ColorKt.Color(color);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DeprecatedValues {
        public final long colorAccentPrimary;
        public final long colorAccentPrimaryVariant;
        public final long colorBackground;
        public final long colorSurface;
        public final long textColorOnAccent;
        public final long textColorPrimary;

        public DeprecatedValues(Context context) {
            Companion.m745getColorWaAFU9c(R.attr.colorPrimary, context);
            Companion.m745getColorWaAFU9c(R.attr.colorPrimaryDark, context);
            Companion.m745getColorWaAFU9c(R.attr.colorAccent, context);
            this.colorAccentPrimary = Companion.m745getColorWaAFU9c(R.^attr-private.colorAccentPrimary, context);
            Companion.m745getColorWaAFU9c(R.^attr-private.colorAccentSecondary, context);
            Companion.m745getColorWaAFU9c(R.^attr-private.colorAccentTertiary, context);
            this.colorAccentPrimaryVariant = Companion.m745getColorWaAFU9c(R.^attr-private.colorAccentPrimaryVariant, context);
            Companion.m745getColorWaAFU9c(R.^attr-private.colorAccentSecondaryVariant, context);
            Companion.m745getColorWaAFU9c(R.^attr-private.colorAccentTertiaryVariant, context);
            this.colorSurface = Companion.m745getColorWaAFU9c(R.^attr-private.colorSurface, context);
            Companion.m745getColorWaAFU9c(R.^attr-private.colorSurfaceHighlight, context);
            Companion.m745getColorWaAFU9c(R.^attr-private.colorSurfaceVariant, context);
            Companion.m745getColorWaAFU9c(R.^attr-private.colorSurfaceHeader, context);
            Companion.m745getColorWaAFU9c(R.attr.colorError, context);
            this.colorBackground = Companion.m745getColorWaAFU9c(R.attr.colorBackground, context);
            Companion.m745getColorWaAFU9c(R.attr.colorBackgroundFloating, context);
            Companion.m745getColorWaAFU9c(R.attr.panelColorBackground, context);
            this.textColorPrimary = Companion.m745getColorWaAFU9c(R.attr.textColorPrimary, context);
            Companion.m745getColorWaAFU9c(R.attr.textColorSecondary, context);
            Companion.m745getColorWaAFU9c(R.attr.textColorTertiary, context);
            Companion.m745getColorWaAFU9c(R.attr.textColorPrimaryInverse, context);
            Companion.m745getColorWaAFU9c(R.attr.textColorSecondaryInverse, context);
            Companion.m745getColorWaAFU9c(R.attr.textColorTertiaryInverse, context);
            this.textColorOnAccent = Companion.m745getColorWaAFU9c(R.^attr-private.textUnderlineThickness, context);
            Companion.m745getColorWaAFU9c(R.attr.colorForeground, context);
            Companion.m745getColorWaAFU9c(R.attr.colorForegroundInverse, context);
        }
    }

    public AndroidColorScheme(Context context) {
        this.onSecondaryFixedVariant = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnSecondaryFixedVariant, context);
        this.onTertiaryFixedVariant = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnTertiaryFixedVariant, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSurfaceContainerLowest, context);
        this.onPrimaryFixedVariant = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnPrimaryFixedVariant, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnSecondaryContainer, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnTertiaryContainer, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSurfaceContainerLow, context);
        this.onPrimaryContainer = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnPrimaryContainer, context);
        this.secondaryFixedDim = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSecondaryFixedDim, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnErrorContainer, context);
        this.onSecondaryFixed = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnSecondaryFixed, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnSurfaceInverse, context);
        this.tertiaryFixedDim = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorTertiaryFixedDim, context);
        this.onTertiaryFixed = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnTertiaryFixed, context);
        this.primaryFixedDim = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorPrimaryFixedDim, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSecondaryContainer, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorErrorContainer, context);
        this.onPrimaryFixed = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnPrimaryFixed, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorPrimaryInverse, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSecondaryFixed, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSurfaceInverse, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSurfaceVariant, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorTertiaryContainer, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorTertiaryFixed, context);
        this.primaryContainer = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorPrimaryContainer, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnBackground, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorPrimaryFixed, context);
        this.onSecondary = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnSecondary, context);
        this.onTertiary = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnTertiary, context);
        this.surfaceDim = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSurfaceDim, context);
        this.surfaceBright = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSurfaceBright, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorError, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnError, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSurface, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSurfaceContainerHigh, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSurfaceContainerHighest, context);
        this.onSurfaceVariant = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnSurfaceVariant, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOutline, context);
        Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOutlineVariant, context);
        this.onPrimary = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnPrimary, context);
        this.onSurface = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorOnSurface, context);
        this.surfaceContainer = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSurfaceContainer, context);
        this.primary = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorPrimary, context);
        this.secondary = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorSecondary, context);
        this.tertiary = Companion.m745getColorWaAFU9c(R.^attr-private.materialColorTertiary, context);
        this.deprecated = new DeprecatedValues(context);
    }
}

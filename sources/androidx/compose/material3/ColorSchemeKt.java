package androidx.compose.material3;

import androidx.compose.material3.tokens.ColorLightTokens;
import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.Dp;
import com.android.app.viewcapture.data.ViewNode;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ColorSchemeKt {
    public static final StaticProvidableCompositionLocal LocalColorScheme = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.material3.ColorSchemeKt$LocalColorScheme$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return ColorSchemeKt.m204lightColorSchemeCXl9yA$default(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -1, 15);
        }
    });
    public static final StaticProvidableCompositionLocal LocalTonalElevationEnabled = new StaticProvidableCompositionLocal(new Function0() { // from class: androidx.compose.material3.ColorSchemeKt$LocalTonalElevationEnabled$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return Boolean.TRUE;
        }
    });

    /* renamed from: contentColorFor-4WTKRHQ, reason: not valid java name */
    public static final long m202contentColorFor4WTKRHQ(ColorScheme colorScheme, long j) {
        if (Color.m363equalsimpl0(j, colorScheme.primary)) {
            return colorScheme.onPrimary;
        }
        if (Color.m363equalsimpl0(j, colorScheme.secondary)) {
            return colorScheme.onSecondary;
        }
        if (Color.m363equalsimpl0(j, colorScheme.tertiary)) {
            return colorScheme.onTertiary;
        }
        if (Color.m363equalsimpl0(j, colorScheme.background)) {
            return colorScheme.onBackground;
        }
        if (Color.m363equalsimpl0(j, colorScheme.error)) {
            return colorScheme.onError;
        }
        if (Color.m363equalsimpl0(j, colorScheme.primaryContainer)) {
            return colorScheme.onPrimaryContainer;
        }
        if (Color.m363equalsimpl0(j, colorScheme.secondaryContainer)) {
            return colorScheme.onSecondaryContainer;
        }
        if (Color.m363equalsimpl0(j, colorScheme.tertiaryContainer)) {
            return colorScheme.onTertiaryContainer;
        }
        if (Color.m363equalsimpl0(j, colorScheme.errorContainer)) {
            return colorScheme.onErrorContainer;
        }
        if (Color.m363equalsimpl0(j, colorScheme.inverseSurface)) {
            return colorScheme.inverseOnSurface;
        }
        boolean m363equalsimpl0 = Color.m363equalsimpl0(j, colorScheme.surface);
        long j2 = colorScheme.onSurface;
        if (!m363equalsimpl0) {
            if (Color.m363equalsimpl0(j, colorScheme.surfaceVariant)) {
                return colorScheme.onSurfaceVariant;
            }
            if (!Color.m363equalsimpl0(j, colorScheme.surfaceBright) && !Color.m363equalsimpl0(j, colorScheme.surfaceContainer) && !Color.m363equalsimpl0(j, colorScheme.surfaceContainerHigh) && !Color.m363equalsimpl0(j, colorScheme.surfaceContainerHighest) && !Color.m363equalsimpl0(j, colorScheme.surfaceContainerLow) && !Color.m363equalsimpl0(j, colorScheme.surfaceContainerLowest)) {
                int i = Color.$r8$clinit;
                return Color.Unspecified;
            }
        }
        return j2;
    }

    /* renamed from: contentColorFor-ek8zF_U, reason: not valid java name */
    public static final long m203contentColorForek8zF_U(long j, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceGroup(-1680918192);
        long m202contentColorFor4WTKRHQ = m202contentColorFor4WTKRHQ(MaterialTheme.getColorScheme(composerImpl), j);
        if (m202contentColorFor4WTKRHQ == 16) {
            m202contentColorFor4WTKRHQ = ((Color) composerImpl.consume(ContentColorKt.LocalContentColor)).value;
        }
        composerImpl.end(false);
        return m202contentColorFor4WTKRHQ;
    }

    public static final long fromToken(ColorScheme colorScheme, ColorSchemeKeyTokens colorSchemeKeyTokens) {
        switch (colorSchemeKeyTokens.ordinal()) {
            case 0:
                return colorScheme.background;
            case 1:
                return colorScheme.error;
            case 2:
                return colorScheme.errorContainer;
            case 3:
                return colorScheme.inverseOnSurface;
            case 4:
                return colorScheme.inversePrimary;
            case 5:
                return colorScheme.inverseSurface;
            case 6:
                return colorScheme.onBackground;
            case 7:
                return colorScheme.onError;
            case 8:
                return colorScheme.onErrorContainer;
            case 9:
                return colorScheme.onPrimary;
            case 10:
                return colorScheme.onPrimaryContainer;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
            case 15:
            case 16:
            case 21:
            case 22:
            case 27:
            case 28:
            case 32:
            case 33:
            default:
                int i = Color.$r8$clinit;
                return Color.Unspecified;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                return colorScheme.onSecondary;
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                return colorScheme.onSecondaryContainer;
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                return colorScheme.onSurface;
            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                return colorScheme.onSurfaceVariant;
            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                return colorScheme.onTertiary;
            case 20:
                return colorScheme.onTertiaryContainer;
            case 23:
                return colorScheme.outline;
            case 24:
                return colorScheme.outlineVariant;
            case 25:
                return colorScheme.primary;
            case 26:
                return colorScheme.primaryContainer;
            case 29:
                return colorScheme.scrim;
            case 30:
                return colorScheme.secondary;
            case 31:
                return colorScheme.secondaryContainer;
            case 34:
                return colorScheme.surface;
            case 35:
                return colorScheme.surfaceBright;
            case 36:
                return colorScheme.surfaceContainer;
            case 37:
                return colorScheme.surfaceContainerHigh;
            case 38:
                return colorScheme.surfaceContainerHighest;
            case 39:
                return colorScheme.surfaceContainerLow;
            case 40:
                return colorScheme.surfaceContainerLowest;
            case 41:
                return colorScheme.surfaceDim;
            case 42:
                return colorScheme.surfaceTint;
            case 43:
                return colorScheme.surfaceVariant;
            case 44:
                return colorScheme.tertiary;
            case 45:
                return colorScheme.tertiaryContainer;
        }
    }

    public static final long getValue(ColorSchemeKeyTokens colorSchemeKeyTokens, Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return fromToken(MaterialTheme.getColorScheme(composer), colorSchemeKeyTokens);
    }

    /* renamed from: lightColorScheme-C-Xl9yA$default, reason: not valid java name */
    public static ColorScheme m204lightColorSchemeCXl9yA$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29, long j30, long j31, long j32, int i, int i2) {
        long j33 = (i & 1) != 0 ? ColorLightTokens.Primary : j;
        return new ColorScheme(j33, (i & 2) != 0 ? ColorLightTokens.OnPrimary : j2, (i & 4) != 0 ? ColorLightTokens.PrimaryContainer : j3, (i & 8) != 0 ? ColorLightTokens.OnPrimaryContainer : j4, (i & 16) != 0 ? ColorLightTokens.InversePrimary : j5, (i & 32) != 0 ? ColorLightTokens.Secondary : j6, (i & 64) != 0 ? ColorLightTokens.OnSecondary : j7, (i & 128) != 0 ? ColorLightTokens.SecondaryContainer : j8, (i & 256) != 0 ? ColorLightTokens.OnSecondaryContainer : j9, (i & 512) != 0 ? ColorLightTokens.Tertiary : j10, (i & 1024) != 0 ? ColorLightTokens.OnTertiary : j11, (i & 2048) != 0 ? ColorLightTokens.TertiaryContainer : j12, (i & 4096) != 0 ? ColorLightTokens.OnTertiaryContainer : j13, (i & 8192) != 0 ? ColorLightTokens.Background : j14, (i & 16384) != 0 ? ColorLightTokens.OnBackground : j15, (32768 & i) != 0 ? ColorLightTokens.Surface : j16, (65536 & i) != 0 ? ColorLightTokens.OnSurface : j17, (131072 & i) != 0 ? ColorLightTokens.SurfaceVariant : j18, (262144 & i) != 0 ? ColorLightTokens.OnSurfaceVariant : j19, (524288 & i) != 0 ? j33 : j20, (1048576 & i) != 0 ? ColorLightTokens.InverseSurface : j21, (2097152 & i) != 0 ? ColorLightTokens.InverseOnSurface : j22, ColorLightTokens.Error, ColorLightTokens.OnError, ColorLightTokens.ErrorContainer, ColorLightTokens.OnErrorContainer, (67108864 & i) != 0 ? ColorLightTokens.Outline : j23, (134217728 & i) != 0 ? ColorLightTokens.OutlineVariant : j24, (268435456 & i) != 0 ? ColorLightTokens.Scrim : j25, (536870912 & i) != 0 ? ColorLightTokens.SurfaceBright : j26, (i2 & 8) != 0 ? ColorLightTokens.SurfaceDim : j32, (1073741824 & i) != 0 ? ColorLightTokens.SurfaceContainer : j27, (i & Integer.MIN_VALUE) != 0 ? ColorLightTokens.SurfaceContainerHigh : j28, (i2 & 1) != 0 ? ColorLightTokens.SurfaceContainerHighest : j29, (i2 & 2) != 0 ? ColorLightTokens.SurfaceContainerLow : j30, (i2 & 4) != 0 ? ColorLightTokens.SurfaceContainerLowest : j31);
    }

    /* renamed from: surfaceColorAtElevation-3ABfNKs, reason: not valid java name */
    public static final long m205surfaceColorAtElevation3ABfNKs(ColorScheme colorScheme, float f) {
        long Color;
        boolean m668equalsimpl0 = Dp.m668equalsimpl0(f, 0);
        long j = colorScheme.surface;
        if (m668equalsimpl0) {
            return j;
        }
        Color = ColorKt.Color(Color.m368getRedimpl(r3), Color.m367getGreenimpl(r3), Color.m365getBlueimpl(r3), ((((float) Math.log(f + 1)) * 4.5f) + 2.0f) / 100.0f, Color.m366getColorSpaceimpl(colorScheme.surfaceTint));
        return ColorKt.m371compositeOverOWjLjI(Color, j);
    }
}

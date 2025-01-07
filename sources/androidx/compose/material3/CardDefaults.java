package androidx.compose.material3;

import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.material3.tokens.FilledCardTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CardDefaults {
    /* renamed from: cardColors-ro_MJ88, reason: not valid java name */
    public static CardColors m200cardColorsro_MJ88(long j, long j2, Composer composer, int i, int i2) {
        long Color;
        long j3 = j;
        long m203contentColorForek8zF_U = (i2 & 2) != 0 ? ColorSchemeKt.m203contentColorForek8zF_U(j3, composer) : j2;
        long j4 = Color.Unspecified;
        Color = ColorKt.Color(Color.m368getRedimpl(m203contentColorForek8zF_U), Color.m367getGreenimpl(m203contentColorForek8zF_U), Color.m365getBlueimpl(m203contentColorForek8zF_U), 0.38f, Color.m366getColorSpaceimpl(m203contentColorForek8zF_U));
        OpaqueKey opaqueKey = ComposerKt.invocation;
        CardColors defaultCardColors$material3_release = getDefaultCardColors$material3_release(MaterialTheme.getColorScheme(composer));
        if (j3 == 16) {
            j3 = defaultCardColors$material3_release.containerColor;
        }
        long j5 = j3;
        if (m203contentColorForek8zF_U == 16) {
            m203contentColorForek8zF_U = defaultCardColors$material3_release.contentColor;
        }
        long j6 = m203contentColorForek8zF_U;
        if (j4 == 16) {
            j4 = defaultCardColors$material3_release.disabledContainerColor;
        }
        long j7 = j4;
        if (Color == 16) {
            Color = defaultCardColors$material3_release.disabledContentColor;
        }
        return new CardColors(j5, j6, j7, Color);
    }

    public static CardColors getDefaultCardColors$material3_release(ColorScheme colorScheme) {
        long Color;
        long Color2;
        CardColors cardColors = colorScheme.defaultCardColorsCached;
        if (cardColors != null) {
            return cardColors;
        }
        float f = FilledCardTokens.ContainerElevation;
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.SurfaceContainerHighest;
        long fromToken = ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens);
        long m202contentColorFor4WTKRHQ = ColorSchemeKt.m202contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens));
        Color = ColorKt.Color(Color.m368getRedimpl(r6), Color.m367getGreenimpl(r6), Color.m365getBlueimpl(r6), FilledCardTokens.DisabledContainerOpacity, Color.m366getColorSpaceimpl(ColorSchemeKt.fromToken(colorScheme, FilledCardTokens.DisabledContainerColor)));
        long m371compositeOverOWjLjI = ColorKt.m371compositeOverOWjLjI(Color, ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens));
        Color2 = ColorKt.Color(Color.m368getRedimpl(r8), Color.m367getGreenimpl(r8), Color.m365getBlueimpl(r8), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.m202contentColorFor4WTKRHQ(colorScheme, ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens))));
        CardColors cardColors2 = new CardColors(fromToken, m202contentColorFor4WTKRHQ, m371compositeOverOWjLjI, Color2);
        colorScheme.defaultCardColorsCached = cardColors2;
        return cardColors2;
    }
}

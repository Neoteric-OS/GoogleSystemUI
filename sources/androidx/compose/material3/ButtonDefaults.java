package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValuesImpl;
import androidx.compose.material3.tokens.BaselineButtonTokens;
import androidx.compose.material3.tokens.ButtonSmallTokens;
import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.material3.tokens.FilledButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ButtonDefaults {
    public static final PaddingValuesImpl ContentPadding;
    public static final float IconSpacing;
    public static final float MinHeight;
    public static final float MinWidth;
    public static final PaddingValuesImpl TextButtonContentPadding;

    static {
        float f = BaselineButtonTokens.LeadingSpace;
        float f2 = BaselineButtonTokens.TrailingSpace;
        float f3 = 16;
        float f4 = ButtonSmallTokens.ContainerHeight;
        float f5 = 8;
        ContentPadding = new PaddingValuesImpl(f, f5, f2, f5);
        PaddingKt.m97PaddingValuesa9UjIt4(f3, f5, f2, f5);
        float f6 = 12;
        TextButtonContentPadding = new PaddingValuesImpl(f6, f5, f6, f5);
        PaddingKt.m97PaddingValuesa9UjIt4(f6, f5, f3, f5);
        MinWidth = 58;
        MinHeight = ButtonSmallTokens.ContainerHeight;
        IconSpacing = f5;
    }

    /* renamed from: buttonColors-ro_MJ88, reason: not valid java name */
    public static ButtonColors m198buttonColorsro_MJ88(long j, long j2, Composer composer, int i) {
        if ((i & 1) != 0) {
            j = Color.Unspecified;
        }
        long j3 = j;
        if ((i & 2) != 0) {
            j2 = Color.Unspecified;
        }
        long j4 = Color.Unspecified;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return getDefaultButtonColors$material3_release(MaterialTheme.getColorScheme(composer)).m197copyjRlVdoo(j3, j2, j4, j4);
    }

    public static ButtonColors getDefaultButtonColors$material3_release(ColorScheme colorScheme) {
        long Color;
        long Color2;
        ButtonColors buttonColors = colorScheme.defaultButtonColorsCached;
        if (buttonColors != null) {
            return buttonColors;
        }
        float f = FilledButtonTokens.ContainerElevation;
        long fromToken = ColorSchemeKt.fromToken(colorScheme, ColorSchemeKeyTokens.Primary);
        long fromToken2 = ColorSchemeKt.fromToken(colorScheme, FilledButtonTokens.LabelTextColor);
        Color = ColorKt.Color(Color.m368getRedimpl(r6), Color.m367getGreenimpl(r6), Color.m365getBlueimpl(r6), FilledButtonTokens.DisabledContainerOpacity, Color.m366getColorSpaceimpl(ColorSchemeKt.fromToken(colorScheme, FilledButtonTokens.DisabledContainerColor)));
        Color2 = ColorKt.Color(Color.m368getRedimpl(r8), Color.m367getGreenimpl(r8), Color.m365getBlueimpl(r8), FilledButtonTokens.DisabledLabelTextOpacity, Color.m366getColorSpaceimpl(ColorSchemeKt.fromToken(colorScheme, FilledButtonTokens.DisabledLabelTextColor)));
        ButtonColors buttonColors2 = new ButtonColors(fromToken, fromToken2, Color, Color2);
        colorScheme.defaultButtonColorsCached = buttonColors2;
        return buttonColors2;
    }

    public static ButtonColors getDefaultOutlinedButtonColors$material3_release(ColorScheme colorScheme) {
        long Color;
        ButtonColors buttonColors = colorScheme.defaultOutlinedButtonColorsCached;
        if (buttonColors != null) {
            return buttonColors;
        }
        long j = Color.Transparent;
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurfaceVariant;
        long fromToken = ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens);
        Color = ColorKt.Color(Color.m368getRedimpl(r1), Color.m367getGreenimpl(r1), Color.m365getBlueimpl(r1), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens)));
        ButtonColors buttonColors2 = new ButtonColors(j, fromToken, j, Color);
        colorScheme.defaultOutlinedButtonColorsCached = buttonColors2;
        return buttonColors2;
    }

    /* renamed from: outlinedButtonColors-ro_MJ88, reason: not valid java name */
    public static ButtonColors m199outlinedButtonColorsro_MJ88(long j, Composer composer) {
        long j2 = Color.Unspecified;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return getDefaultOutlinedButtonColors$material3_release(MaterialTheme.getColorScheme(composer)).m197copyjRlVdoo(j2, j, j2, j2);
    }
}

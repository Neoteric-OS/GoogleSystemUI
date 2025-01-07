package androidx.compose.material3;

import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.material3.tokens.SmallIconButtonTokens;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.unit.DpKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IconButtonDefaults {
    public static final IconButtonDefaults INSTANCE = null;

    static {
        float f = SmallIconButtonTokens.ContainerHeight;
    }

    /* renamed from: defaultIconButtonColors-0Yiz4hI$material3_release, reason: not valid java name */
    public static IconButtonColors m210defaultIconButtonColors0Yiz4hI$material3_release(ColorScheme colorScheme, Color color) {
        IconButtonColors iconButtonColors = colorScheme.defaultIconButtonColorsCached;
        if (iconButtonColors == null) {
            long j = Color.Transparent;
            ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSurfaceVariant;
            iconButtonColors = new IconButtonColors(j, color != null ? color.value : ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens), j, color != null ? ColorKt.Color(Color.m368getRedimpl(r8), Color.m367getGreenimpl(r8), Color.m365getBlueimpl(r8), 0.38f, Color.m366getColorSpaceimpl(color.value)) : ColorKt.Color(Color.m368getRedimpl(r8), Color.m367getGreenimpl(r8), Color.m365getBlueimpl(r8), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.fromToken(colorScheme, colorSchemeKeyTokens))));
            colorScheme.defaultIconButtonColorsCached = iconButtonColors;
        }
        return iconButtonColors;
    }

    public static IconButtonColors getDefaultFilledIconButtonColors$material3_release(ColorScheme colorScheme) {
        long Color;
        long Color2;
        IconButtonColors iconButtonColors = colorScheme.defaultFilledIconButtonColorsCached;
        if (iconButtonColors != null) {
            return iconButtonColors;
        }
        long fromToken = ColorSchemeKt.fromToken(colorScheme, ColorSchemeKeyTokens.Primary);
        long fromToken2 = ColorSchemeKt.fromToken(colorScheme, ColorSchemeKeyTokens.OnPrimary);
        Color = ColorKt.Color(Color.m368getRedimpl(r6), Color.m367getGreenimpl(r6), Color.m365getBlueimpl(r6), 0.1f, Color.m366getColorSpaceimpl(ColorSchemeKt.fromToken(colorScheme, ColorSchemeKeyTokens.OnSurface)));
        Color2 = ColorKt.Color(Color.m368getRedimpl(r8), Color.m367getGreenimpl(r8), Color.m365getBlueimpl(r8), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.fromToken(colorScheme, ColorSchemeKeyTokens.OnSurfaceVariant)));
        IconButtonColors iconButtonColors2 = new IconButtonColors(fromToken, fromToken2, Color, Color2);
        colorScheme.defaultFilledIconButtonColorsCached = iconButtonColors2;
        return iconButtonColors2;
    }

    /* renamed from: smallContainerSize-N-wlBFI$default, reason: not valid java name */
    public static long m211smallContainerSizeNwlBFI$default() {
        float f = SmallIconButtonTokens.UniformLeadingSpace;
        return DpKt.m670DpSizeYgX7TsA(SmallIconButtonTokens.IconSize + f + f, SmallIconButtonTokens.ContainerHeight);
    }
}

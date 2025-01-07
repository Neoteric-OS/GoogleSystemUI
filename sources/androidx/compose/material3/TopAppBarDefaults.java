package androidx.compose.material3;

import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.material3.tokens.TopAppBarLargeTokens;
import androidx.compose.material3.tokens.TopAppBarMediumTokens;
import androidx.compose.material3.tokens.TopAppBarSmallCenteredTokens;
import androidx.compose.material3.tokens.TopAppBarSmallTokens;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TopAppBarDefaults {
    public static final float TopAppBarExpandedHeight = TopAppBarSmallTokens.ContainerHeight;

    static {
        int i = TopAppBarMediumTokens.$r8$clinit;
        int i2 = TopAppBarLargeTokens.$r8$clinit;
    }

    public static TopAppBarColors getDefaultCenterAlignedTopAppBarColors$material3_release(ColorScheme colorScheme) {
        TopAppBarColors topAppBarColors = colorScheme.defaultCenterAlignedTopAppBarColorsCached;
        if (topAppBarColors != null) {
            return topAppBarColors;
        }
        ColorSchemeKeyTokens colorSchemeKeyTokens = TopAppBarSmallCenteredTokens.HeadlineColor;
        TopAppBarColors topAppBarColors2 = new TopAppBarColors(ColorSchemeKt.fromToken(colorScheme, ColorSchemeKeyTokens.Surface), ColorSchemeKt.fromToken(colorScheme, TopAppBarSmallCenteredTokens.OnScrollContainerColor), ColorSchemeKt.fromToken(colorScheme, TopAppBarSmallCenteredTokens.LeadingIconColor), ColorSchemeKt.fromToken(colorScheme, TopAppBarSmallCenteredTokens.HeadlineColor), ColorSchemeKt.fromToken(colorScheme, TopAppBarSmallCenteredTokens.TrailingIconColor));
        colorScheme.defaultCenterAlignedTopAppBarColorsCached = topAppBarColors2;
        return topAppBarColors2;
    }
}

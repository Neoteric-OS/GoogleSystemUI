package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValuesImpl;
import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.material3.tokens.NavigationDrawerTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NavigationDrawerItemDefaults {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        float f = 12;
        float f2 = 0;
        new PaddingValuesImpl(f, f2, f, f2);
    }

    /* renamed from: colors-oq7We08, reason: not valid java name */
    public static NavigationDrawerItemColors m218colorsoq7We08(long j, Composer composer) {
        int i = NavigationDrawerTokens.$r8$clinit;
        long value = ColorSchemeKt.getValue(ColorSchemeKeyTokens.SecondaryContainer, composer);
        ColorSchemeKeyTokens colorSchemeKeyTokens = ColorSchemeKeyTokens.OnSecondaryContainer;
        long value2 = ColorSchemeKt.getValue(colorSchemeKeyTokens, composer);
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurfaceVariant;
        long value3 = ColorSchemeKt.getValue(colorSchemeKeyTokens2, composer);
        long value4 = ColorSchemeKt.getValue(colorSchemeKeyTokens, composer);
        long value5 = ColorSchemeKt.getValue(colorSchemeKeyTokens2, composer);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return new DefaultDrawerItemsColor(value2, value3, value4, value5, value, j, value4, value5);
    }
}

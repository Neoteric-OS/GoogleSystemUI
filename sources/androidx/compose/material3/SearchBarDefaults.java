package androidx.compose.material3;

import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.foundation.text.selection.TextSelectionColorsKt;
import androidx.compose.material3.tokens.ColorSchemeKeyTokens;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.SearchBarTokens;
import androidx.compose.material3.tokens.SearchViewTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SearchBarDefaults {
    public static final SearchBarDefaults INSTANCE = new SearchBarDefaults();
    public static final float InputFieldHeight;
    public static final float ShadowElevation;
    public static final float TonalElevation;

    static {
        float f = ElevationTokens.Level0;
        float f2 = ElevationTokens.Level0;
        TonalElevation = f2;
        ShadowElevation = f2;
        float f3 = SearchBarTokens.ContainerHeight;
        InputFieldHeight = SearchBarTokens.ContainerHeight;
    }

    /* renamed from: colors-Klgx-Pg, reason: not valid java name */
    public static SearchBarColors m222colorsKlgxPg(long j, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            float f = SearchBarTokens.ContainerHeight;
            j = ColorSchemeKt.getValue(ColorSchemeKeyTokens.SurfaceContainerHigh, composer);
        }
        long value = ColorSchemeKt.getValue(SearchViewTokens.DividerColor, composer);
        TextFieldColors m223inputFieldColorsJVEmHcM = m223inputFieldColorsJVEmHcM(j, j, j, composer, 1048575);
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return new SearchBarColors(j, value, m223inputFieldColorsJVEmHcM);
    }

    public static Shape getInputFieldShape(Composer composer) {
        OpaqueKey opaqueKey = ComposerKt.invocation;
        return ShapesKt.getValue(SearchBarTokens.ContainerShape, composer);
    }

    /* renamed from: inputFieldColors-JVEmHcM, reason: not valid java name */
    public static TextFieldColors m223inputFieldColorsJVEmHcM(long j, long j2, long j3, Composer composer, int i) {
        long Color;
        long Color2;
        long Color3;
        long Color4;
        long Color5;
        long Color6;
        ColorSchemeKeyTokens colorSchemeKeyTokens = SearchBarTokens.InputTextColor;
        long value = ColorSchemeKt.getValue(colorSchemeKeyTokens, composer);
        long value2 = ColorSchemeKt.getValue(colorSchemeKeyTokens, composer);
        ColorSchemeKeyTokens colorSchemeKeyTokens2 = ColorSchemeKeyTokens.OnSurface;
        Color = ColorKt.Color(Color.m368getRedimpl(r7), Color.m367getGreenimpl(r7), Color.m365getBlueimpl(r7), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.getValue(colorSchemeKeyTokens2, composer)));
        long value3 = ColorSchemeKt.getValue(ColorSchemeKeyTokens.Primary, composer);
        TextSelectionColors textSelectionColors = (TextSelectionColors) ((ComposerImpl) composer).consume(TextSelectionColorsKt.LocalTextSelectionColors);
        ColorSchemeKeyTokens colorSchemeKeyTokens3 = SearchBarTokens.LeadingIconColor;
        long value4 = ColorSchemeKt.getValue(colorSchemeKeyTokens3, composer);
        long value5 = ColorSchemeKt.getValue(colorSchemeKeyTokens3, composer);
        Color2 = ColorKt.Color(Color.m368getRedimpl(r9), Color.m367getGreenimpl(r9), Color.m365getBlueimpl(r9), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.getValue(colorSchemeKeyTokens2, composer)));
        ColorSchemeKeyTokens colorSchemeKeyTokens4 = SearchBarTokens.TrailingIconColor;
        long value6 = ColorSchemeKt.getValue(colorSchemeKeyTokens4, composer);
        long value7 = ColorSchemeKt.getValue(colorSchemeKeyTokens4, composer);
        Color3 = ColorKt.Color(Color.m368getRedimpl(r9), Color.m367getGreenimpl(r9), Color.m365getBlueimpl(r9), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.getValue(colorSchemeKeyTokens2, composer)));
        ColorSchemeKeyTokens colorSchemeKeyTokens5 = SearchBarTokens.SupportingTextColor;
        long value8 = ColorSchemeKt.getValue(colorSchemeKeyTokens5, composer);
        long value9 = ColorSchemeKt.getValue(colorSchemeKeyTokens5, composer);
        Color4 = ColorKt.Color(Color.m368getRedimpl(r9), Color.m367getGreenimpl(r9), Color.m365getBlueimpl(r9), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.getValue(colorSchemeKeyTokens2, composer)));
        ColorSchemeKeyTokens colorSchemeKeyTokens6 = ColorSchemeKeyTokens.OnSurfaceVariant;
        long value10 = ColorSchemeKt.getValue(colorSchemeKeyTokens6, composer);
        long value11 = ColorSchemeKt.getValue(colorSchemeKeyTokens6, composer);
        Color5 = ColorKt.Color(Color.m368getRedimpl(r9), Color.m367getGreenimpl(r9), Color.m365getBlueimpl(r9), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.getValue(colorSchemeKeyTokens6, composer)));
        long value12 = ColorSchemeKt.getValue(colorSchemeKeyTokens6, composer);
        long value13 = ColorSchemeKt.getValue(colorSchemeKeyTokens6, composer);
        Color6 = ColorKt.Color(Color.m368getRedimpl(r9), Color.m367getGreenimpl(r9), Color.m365getBlueimpl(r9), 0.38f, Color.m366getColorSpaceimpl(ColorSchemeKt.getValue(colorSchemeKeyTokens6, composer)));
        int i2 = i & 1048576;
        ColorSchemeKeyTokens colorSchemeKeyTokens7 = ColorSchemeKeyTokens.SurfaceContainerHigh;
        long value14 = i2 != 0 ? ColorSchemeKt.getValue(colorSchemeKeyTokens7, composer) : j;
        long value15 = (i & 2097152) != 0 ? ColorSchemeKt.getValue(colorSchemeKeyTokens7, composer) : j2;
        long value16 = (i & 4194304) != 0 ? ColorSchemeKt.getValue(colorSchemeKeyTokens7, composer) : j3;
        OpaqueKey opaqueKey = ComposerKt.invocation;
        TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
        long j4 = Color.Unspecified;
        return TextFieldDefaults.getDefaultTextFieldColors(MaterialTheme.getColorScheme(composer), composer).m236copyejIjP34(value, value2, Color, j4, value14, value15, value16, j4, value3, j4, textSelectionColors, j4, j4, j4, j4, value4, value5, Color2, j4, value6, value7, Color3, j4, j4, j4, j4, j4, value8, value9, Color4, j4, j4, j4, j4, j4, value10, value11, Color5, j4, value12, value13, Color6, j4);
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x03cc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x03e8  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01d5  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01e3  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01f9  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01df  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:60:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x02c8 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x02f2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x034c A[ADDED_TO_REGION] */
    /* JADX WARN: Type inference failed for: r2v11, types: [androidx.compose.material3.SearchBarDefaults$InputField$12, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void InputField(final java.lang.String r54, final kotlin.jvm.functions.Function1 r55, final kotlin.jvm.functions.Function1 r56, final boolean r57, final kotlin.jvm.functions.Function1 r58, androidx.compose.ui.Modifier r59, boolean r60, kotlin.jvm.functions.Function2 r61, kotlin.jvm.functions.Function2 r62, kotlin.jvm.functions.Function2 r63, androidx.compose.material3.TextFieldColors r64, androidx.compose.foundation.interaction.MutableInteractionSource r65, androidx.compose.runtime.Composer r66, final int r67, final int r68, final int r69) {
        /*
            Method dump skipped, instructions count: 1072
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBarDefaults.InputField(java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.Modifier, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.material3.TextFieldColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int, int):void");
    }
}

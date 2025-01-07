package com.android.compose.theme.typography;

import androidx.compose.ui.text.font.DeviceFontFamilyNameFontKt;
import androidx.compose.ui.text.font.Font;
import androidx.compose.ui.text.font.FontListFontFamily;
import androidx.compose.ui.text.font.FontWeight;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TypefaceTokens {
    public static final FontWeight WeightMedium = FontWeight.Medium;
    public static final FontWeight WeightRegular = FontWeight.Normal;
    public final FontListFontFamily brand;
    public final FontListFontFamily plain;

    public TypefaceTokens(TypefaceNames typefaceNames) {
        String str = typefaceNames.brand;
        if (str.length() <= 0) {
            throw new IllegalArgumentException("name may not be empty");
        }
        String str2 = typefaceNames.plain;
        if (str2.length() <= 0) {
            throw new IllegalArgumentException("name may not be empty");
        }
        FontWeight fontWeight = WeightMedium;
        Font m607Fontvxs03AY$default = DeviceFontFamilyNameFontKt.m607Fontvxs03AY$default(str, fontWeight);
        FontWeight fontWeight2 = WeightRegular;
        this.brand = new FontListFontFamily(Arrays.asList(m607Fontvxs03AY$default, DeviceFontFamilyNameFontKt.m607Fontvxs03AY$default(str, fontWeight2)));
        this.plain = new FontListFontFamily(Arrays.asList(DeviceFontFamilyNameFontKt.m607Fontvxs03AY$default(str2, fontWeight), DeviceFontFamilyNameFontKt.m607Fontvxs03AY$default(str2, fontWeight2)));
    }
}

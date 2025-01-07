package androidx.compose.ui.text.font;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GenericFontFamily extends SystemFontFamily {
    public final String fontFamilyName;
    public final String name;

    public GenericFontFamily(String str, String str2) {
        this.name = str;
        this.fontFamilyName = str2;
    }

    public final String toString() {
        return this.fontFamilyName;
    }
}

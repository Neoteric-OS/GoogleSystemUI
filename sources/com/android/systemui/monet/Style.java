package com.android.systemui.monet;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Style {
    public static final /* synthetic */ Style[] $VALUES;
    public static final Style CONTENT;
    public static final Style EXPRESSIVE;
    public static final Style FRUIT_SALAD;
    public static final Style MONOCHROMATIC;
    public static final Style RAINBOW;
    public static final Style SPRITZ;
    public static final Style TONAL_SPOT;
    public static final Style VIBRANT;

    static {
        Style style = new Style("SPRITZ", 0);
        SPRITZ = style;
        Style style2 = new Style("TONAL_SPOT", 1);
        TONAL_SPOT = style2;
        Style style3 = new Style("VIBRANT", 2);
        VIBRANT = style3;
        Style style4 = new Style("EXPRESSIVE", 3);
        EXPRESSIVE = style4;
        Style style5 = new Style("RAINBOW", 4);
        RAINBOW = style5;
        Style style6 = new Style("FRUIT_SALAD", 5);
        FRUIT_SALAD = style6;
        Style style7 = new Style("CONTENT", 6);
        CONTENT = style7;
        Style style8 = new Style("MONOCHROMATIC", 7);
        MONOCHROMATIC = style8;
        $VALUES = new Style[]{style, style2, style3, style4, style5, style6, style7, style8, new Style("CLOCK", 8), new Style("CLOCK_VIBRANT", 9)};
    }

    public static Style valueOf(String str) {
        return (Style) Enum.valueOf(Style.class, str);
    }

    public static Style[] values() {
        return (Style[]) $VALUES.clone();
    }
}

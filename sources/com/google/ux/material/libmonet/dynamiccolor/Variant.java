package com.google.ux.material.libmonet.dynamiccolor;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class Variant {
    public static final /* synthetic */ Variant[] $VALUES;
    public static final Variant CONTENT;
    public static final Variant EXPRESSIVE;
    public static final Variant FIDELITY;
    public static final Variant FRUIT_SALAD;
    public static final Variant MONOCHROME;
    public static final Variant NEUTRAL;
    public static final Variant RAINBOW;
    public static final Variant TONAL_SPOT;
    public static final Variant VIBRANT;

    static {
        Variant variant = new Variant("MONOCHROME", 0);
        MONOCHROME = variant;
        Variant variant2 = new Variant("NEUTRAL", 1);
        NEUTRAL = variant2;
        Variant variant3 = new Variant("TONAL_SPOT", 2);
        TONAL_SPOT = variant3;
        Variant variant4 = new Variant("VIBRANT", 3);
        VIBRANT = variant4;
        Variant variant5 = new Variant("EXPRESSIVE", 4);
        EXPRESSIVE = variant5;
        Variant variant6 = new Variant("FIDELITY", 5);
        FIDELITY = variant6;
        Variant variant7 = new Variant("CONTENT", 6);
        CONTENT = variant7;
        Variant variant8 = new Variant("RAINBOW", 7);
        RAINBOW = variant8;
        Variant variant9 = new Variant("FRUIT_SALAD", 8);
        FRUIT_SALAD = variant9;
        $VALUES = new Variant[]{variant, variant2, variant3, variant4, variant5, variant6, variant7, variant8, variant9};
    }

    public static Variant valueOf(String str) {
        return (Variant) Enum.valueOf(Variant.class, str);
    }

    public static Variant[] values() {
        return (Variant[]) $VALUES.clone();
    }
}

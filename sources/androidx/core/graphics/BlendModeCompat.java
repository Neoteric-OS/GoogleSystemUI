package androidx.core.graphics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BlendModeCompat {
    public static final /* synthetic */ BlendModeCompat[] $VALUES;
    public static final BlendModeCompat DARKEN;
    public static final BlendModeCompat LIGHTEN;
    public static final BlendModeCompat OVERLAY;
    public static final BlendModeCompat PLUS;
    public static final BlendModeCompat SCREEN;

    /* JADX INFO: Fake field, exist only in values array */
    BlendModeCompat EF0;

    static {
        BlendModeCompat blendModeCompat = new BlendModeCompat("CLEAR", 0);
        BlendModeCompat blendModeCompat2 = new BlendModeCompat("SRC", 1);
        BlendModeCompat blendModeCompat3 = new BlendModeCompat("DST", 2);
        BlendModeCompat blendModeCompat4 = new BlendModeCompat("SRC_OVER", 3);
        BlendModeCompat blendModeCompat5 = new BlendModeCompat("DST_OVER", 4);
        BlendModeCompat blendModeCompat6 = new BlendModeCompat("SRC_IN", 5);
        BlendModeCompat blendModeCompat7 = new BlendModeCompat("DST_IN", 6);
        BlendModeCompat blendModeCompat8 = new BlendModeCompat("SRC_OUT", 7);
        BlendModeCompat blendModeCompat9 = new BlendModeCompat("DST_OUT", 8);
        BlendModeCompat blendModeCompat10 = new BlendModeCompat("SRC_ATOP", 9);
        BlendModeCompat blendModeCompat11 = new BlendModeCompat("DST_ATOP", 10);
        BlendModeCompat blendModeCompat12 = new BlendModeCompat("XOR", 11);
        BlendModeCompat blendModeCompat13 = new BlendModeCompat("PLUS", 12);
        PLUS = blendModeCompat13;
        BlendModeCompat blendModeCompat14 = new BlendModeCompat("MODULATE", 13);
        BlendModeCompat blendModeCompat15 = new BlendModeCompat("SCREEN", 14);
        SCREEN = blendModeCompat15;
        BlendModeCompat blendModeCompat16 = new BlendModeCompat("OVERLAY", 15);
        OVERLAY = blendModeCompat16;
        BlendModeCompat blendModeCompat17 = new BlendModeCompat("DARKEN", 16);
        DARKEN = blendModeCompat17;
        BlendModeCompat blendModeCompat18 = new BlendModeCompat("LIGHTEN", 17);
        LIGHTEN = blendModeCompat18;
        $VALUES = new BlendModeCompat[]{blendModeCompat, blendModeCompat2, blendModeCompat3, blendModeCompat4, blendModeCompat5, blendModeCompat6, blendModeCompat7, blendModeCompat8, blendModeCompat9, blendModeCompat10, blendModeCompat11, blendModeCompat12, blendModeCompat13, blendModeCompat14, blendModeCompat15, blendModeCompat16, blendModeCompat17, blendModeCompat18, new BlendModeCompat("COLOR_DODGE", 18), new BlendModeCompat("COLOR_BURN", 19), new BlendModeCompat("HARD_LIGHT", 20), new BlendModeCompat("SOFT_LIGHT", 21), new BlendModeCompat("DIFFERENCE", 22), new BlendModeCompat("EXCLUSION", 23), new BlendModeCompat("MULTIPLY", 24), new BlendModeCompat("HUE", 25), new BlendModeCompat("SATURATION", 26), new BlendModeCompat("COLOR", 27), new BlendModeCompat("LUMINOSITY", 28)};
    }

    public static BlendModeCompat valueOf(String str) {
        return (BlendModeCompat) Enum.valueOf(BlendModeCompat.class, str);
    }

    public static BlendModeCompat[] values() {
        return (BlendModeCompat[]) $VALUES.clone();
    }
}

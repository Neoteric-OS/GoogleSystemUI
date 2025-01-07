package com.google.ux.material.libmonet.dynamiccolor;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TonePolarity {
    public static final /* synthetic */ TonePolarity[] $VALUES;
    public static final TonePolarity DARKER;
    public static final TonePolarity LIGHTER;
    public static final TonePolarity NEARER;

    static {
        TonePolarity tonePolarity = new TonePolarity("DARKER", 0);
        DARKER = tonePolarity;
        TonePolarity tonePolarity2 = new TonePolarity("LIGHTER", 1);
        LIGHTER = tonePolarity2;
        TonePolarity tonePolarity3 = new TonePolarity("NEARER", 2);
        NEARER = tonePolarity3;
        $VALUES = new TonePolarity[]{tonePolarity, tonePolarity2, tonePolarity3, new TonePolarity("FARTHER", 3)};
    }

    public static TonePolarity valueOf(String str) {
        return (TonePolarity) Enum.valueOf(TonePolarity.class, str);
    }

    public static TonePolarity[] values() {
        return (TonePolarity[]) $VALUES.clone();
    }
}

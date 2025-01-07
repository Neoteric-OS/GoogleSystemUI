package com.google.android.systemui.tips.data.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TipType {
    public static final /* synthetic */ TipType[] $VALUES;
    public static final TipType MUTE_VOLUME;
    public static final TipType POWER_OFF;
    public static final TipType SCREENSHOT;

    static {
        TipType tipType = new TipType("UNKNOWN", 0);
        TipType tipType2 = new TipType("SCREENSHOT", 1);
        SCREENSHOT = tipType2;
        TipType tipType3 = new TipType("POWER_OFF", 2);
        POWER_OFF = tipType3;
        TipType tipType4 = new TipType("MUTE_VOLUME", 3);
        MUTE_VOLUME = tipType4;
        TipType[] tipTypeArr = {tipType, tipType2, tipType3, tipType4};
        $VALUES = tipTypeArr;
        EnumEntriesKt.enumEntries(tipTypeArr);
    }

    public static TipType valueOf(String str) {
        return (TipType) Enum.valueOf(TipType.class, str);
    }

    public static TipType[] values() {
        return (TipType[]) $VALUES.clone();
    }
}

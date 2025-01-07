package com.android.systemui.plugins.clocks;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClockTickRate {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ClockTickRate[] $VALUES;
    private final int value;
    public static final ClockTickRate PER_MINUTE = new ClockTickRate("PER_MINUTE", 0, 2);
    public static final ClockTickRate PER_SECOND = new ClockTickRate("PER_SECOND", 1, 1);
    public static final ClockTickRate PER_FRAME = new ClockTickRate("PER_FRAME", 2, 0);

    private static final /* synthetic */ ClockTickRate[] $values() {
        return new ClockTickRate[]{PER_MINUTE, PER_SECOND, PER_FRAME};
    }

    static {
        ClockTickRate[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private ClockTickRate(String str, int i, int i2) {
        this.value = i2;
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static ClockTickRate valueOf(String str) {
        return (ClockTickRate) Enum.valueOf(ClockTickRate.class, str);
    }

    public static ClockTickRate[] values() {
        return (ClockTickRate[]) $VALUES.clone();
    }

    public final int getValue() {
        return this.value;
    }
}

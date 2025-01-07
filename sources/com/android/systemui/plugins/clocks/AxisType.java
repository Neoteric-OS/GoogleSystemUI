package com.android.systemui.plugins.clocks;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AxisType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ AxisType[] $VALUES;
    public static final AxisType Toggle = new AxisType("Toggle", 0);
    public static final AxisType Slider = new AxisType("Slider", 1);

    private static final /* synthetic */ AxisType[] $values() {
        return new AxisType[]{Toggle, Slider};
    }

    static {
        AxisType[] $values = $values();
        $VALUES = $values;
        $ENTRIES = EnumEntriesKt.enumEntries($values);
    }

    private AxisType(String str, int i) {
    }

    public static EnumEntries getEntries() {
        return $ENTRIES;
    }

    public static AxisType valueOf(String str) {
        return (AxisType) Enum.valueOf(AxisType.class, str);
    }

    public static AxisType[] values() {
        return (AxisType[]) $VALUES.clone();
    }
}

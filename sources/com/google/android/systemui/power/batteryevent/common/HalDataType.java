package com.google.android.systemui.power.batteryevent.common;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HalDataType implements EventDataType {
    public static final /* synthetic */ HalDataType[] $VALUES;
    public static final HalDataType GOOGLE_BATTERY_DOCK_DEFEND_STATUS;
    public static final HalDataType GOOGLE_BATTERY_DWELL_DEFEND_STATUS;
    public static final HalDataType GOOGLE_BATTERY_TEMP_DEFEND_STATUS;

    static {
        HalDataType halDataType = new HalDataType("GOOGLE_BATTERY_DOCK_DEFEND_STATUS", 0);
        GOOGLE_BATTERY_DOCK_DEFEND_STATUS = halDataType;
        HalDataType halDataType2 = new HalDataType("GOOGLE_BATTERY_TEMP_DEFEND_STATUS", 1);
        GOOGLE_BATTERY_TEMP_DEFEND_STATUS = halDataType2;
        HalDataType halDataType3 = new HalDataType("GOOGLE_BATTERY_DWELL_DEFEND_STATUS", 2);
        GOOGLE_BATTERY_DWELL_DEFEND_STATUS = halDataType3;
        HalDataType[] halDataTypeArr = {halDataType, halDataType2, halDataType3};
        $VALUES = halDataTypeArr;
        EnumEntriesKt.enumEntries(halDataTypeArr);
    }

    public static HalDataType valueOf(String str) {
        return (HalDataType) Enum.valueOf(HalDataType.class, str);
    }

    public static HalDataType[] values() {
        return (HalDataType[]) $VALUES.clone();
    }
}

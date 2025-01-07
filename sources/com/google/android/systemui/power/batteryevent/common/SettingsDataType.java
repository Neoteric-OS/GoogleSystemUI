package com.google.android.systemui.power.batteryevent.common;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SettingsDataType implements EventDataType {
    public static final /* synthetic */ SettingsDataType[] $VALUES;
    public static final SettingsDataType AIRPLANE_STATE;
    public static final SettingsDataType CHARGING_LIMIT_SETTINGS;
    public static final SettingsDataType DND_STATE;
    public static final SettingsDataType DOCK_DEFENDER_BYPASS;

    static {
        SettingsDataType settingsDataType = new SettingsDataType("AIRPLANE_STATE", 0);
        AIRPLANE_STATE = settingsDataType;
        SettingsDataType settingsDataType2 = new SettingsDataType("CHARGING_LIMIT_SETTINGS", 1);
        CHARGING_LIMIT_SETTINGS = settingsDataType2;
        SettingsDataType settingsDataType3 = new SettingsDataType("DND_STATE", 2);
        DND_STATE = settingsDataType3;
        SettingsDataType settingsDataType4 = new SettingsDataType("DOCK_DEFENDER_BYPASS", 3);
        DOCK_DEFENDER_BYPASS = settingsDataType4;
        SettingsDataType[] settingsDataTypeArr = {settingsDataType, settingsDataType2, settingsDataType3, settingsDataType4};
        $VALUES = settingsDataTypeArr;
        EnumEntriesKt.enumEntries(settingsDataTypeArr);
    }

    public static SettingsDataType valueOf(String str) {
        return (SettingsDataType) Enum.valueOf(SettingsDataType.class, str);
    }

    public static SettingsDataType[] values() {
        return (SettingsDataType[]) $VALUES.clone();
    }
}

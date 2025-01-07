package com.google.android.systemui.power.batteryevent.common;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FrameworkApiDataType implements EventDataType {
    public static final /* synthetic */ FrameworkApiDataType[] $VALUES;

    static {
        FrameworkApiDataType[] frameworkApiDataTypeArr = {new FrameworkApiDataType("BATTERY_SAVER_STATE", 0), new FrameworkApiDataType("EXTREME_BATTERY_SAVER_STATE", 1)};
        $VALUES = frameworkApiDataTypeArr;
        EnumEntriesKt.enumEntries(frameworkApiDataTypeArr);
    }

    public static FrameworkApiDataType valueOf(String str) {
        return (FrameworkApiDataType) Enum.valueOf(FrameworkApiDataType.class, str);
    }

    public static FrameworkApiDataType[] values() {
        return (FrameworkApiDataType[]) $VALUES.clone();
    }
}

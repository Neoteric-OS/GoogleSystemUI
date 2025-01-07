package com.android.systemui.inputdevice.tutorial.data.repository;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceType {
    public static final /* synthetic */ DeviceType[] $VALUES;
    public static final DeviceType KEYBOARD;
    public static final DeviceType TOUCHPAD;

    static {
        DeviceType deviceType = new DeviceType("KEYBOARD", 0);
        KEYBOARD = deviceType;
        DeviceType deviceType2 = new DeviceType("TOUCHPAD", 1);
        TOUCHPAD = deviceType2;
        DeviceType[] deviceTypeArr = {deviceType, deviceType2};
        $VALUES = deviceTypeArr;
        EnumEntriesKt.enumEntries(deviceTypeArr);
    }

    public static DeviceType valueOf(String str) {
        return (DeviceType) Enum.valueOf(DeviceType.class, str);
    }

    public static DeviceType[] values() {
        return (DeviceType[]) $VALUES.clone();
    }
}

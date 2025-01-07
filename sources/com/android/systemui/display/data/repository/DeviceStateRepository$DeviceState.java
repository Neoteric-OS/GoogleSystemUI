package com.android.systemui.display.data.repository;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceStateRepository$DeviceState {
    public static final /* synthetic */ DeviceStateRepository$DeviceState[] $VALUES;
    public static final DeviceStateRepository$DeviceState CONCURRENT_DISPLAY;
    public static final DeviceStateRepository$DeviceState FOLDED;
    public static final DeviceStateRepository$DeviceState HALF_FOLDED;
    public static final DeviceStateRepository$DeviceState REAR_DISPLAY;
    public static final DeviceStateRepository$DeviceState UNFOLDED;
    public static final DeviceStateRepository$DeviceState UNKNOWN;

    static {
        DeviceStateRepository$DeviceState deviceStateRepository$DeviceState = new DeviceStateRepository$DeviceState("FOLDED", 0);
        FOLDED = deviceStateRepository$DeviceState;
        DeviceStateRepository$DeviceState deviceStateRepository$DeviceState2 = new DeviceStateRepository$DeviceState("HALF_FOLDED", 1);
        HALF_FOLDED = deviceStateRepository$DeviceState2;
        DeviceStateRepository$DeviceState deviceStateRepository$DeviceState3 = new DeviceStateRepository$DeviceState("UNFOLDED", 2);
        UNFOLDED = deviceStateRepository$DeviceState3;
        DeviceStateRepository$DeviceState deviceStateRepository$DeviceState4 = new DeviceStateRepository$DeviceState("REAR_DISPLAY", 3);
        REAR_DISPLAY = deviceStateRepository$DeviceState4;
        DeviceStateRepository$DeviceState deviceStateRepository$DeviceState5 = new DeviceStateRepository$DeviceState("CONCURRENT_DISPLAY", 4);
        CONCURRENT_DISPLAY = deviceStateRepository$DeviceState5;
        DeviceStateRepository$DeviceState deviceStateRepository$DeviceState6 = new DeviceStateRepository$DeviceState("UNKNOWN", 5);
        UNKNOWN = deviceStateRepository$DeviceState6;
        DeviceStateRepository$DeviceState[] deviceStateRepository$DeviceStateArr = {deviceStateRepository$DeviceState, deviceStateRepository$DeviceState2, deviceStateRepository$DeviceState3, deviceStateRepository$DeviceState4, deviceStateRepository$DeviceState5, deviceStateRepository$DeviceState6};
        $VALUES = deviceStateRepository$DeviceStateArr;
        EnumEntriesKt.enumEntries(deviceStateRepository$DeviceStateArr);
    }

    public static DeviceStateRepository$DeviceState valueOf(String str) {
        return (DeviceStateRepository$DeviceState) Enum.valueOf(DeviceStateRepository$DeviceState.class, str);
    }

    public static DeviceStateRepository$DeviceState[] values() {
        return (DeviceStateRepository$DeviceState[]) $VALUES.clone();
    }
}

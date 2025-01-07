package com.google.android.systemui.biometrics;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceEntryUnlockStage {
    public static final /* synthetic */ DeviceEntryUnlockStage[] $VALUES;
    public static final DeviceEntryUnlockStage CANCELED;
    public static final DeviceEntryUnlockStage EXIT_KEYGUARD;
    public static final DeviceEntryUnlockStage HAL_ACQUISITION;
    public static final DeviceEntryUnlockStage HAL_AUTHENTICATED;
    public static final DeviceEntryUnlockStage STARTED;
    public static final DeviceEntryUnlockStage STOPPED;
    public static final DeviceEntryUnlockStage UNKNOWN;
    public static final DeviceEntryUnlockStage UNLOCKED;

    static {
        DeviceEntryUnlockStage deviceEntryUnlockStage = new DeviceEntryUnlockStage("CANCELED", 0);
        CANCELED = deviceEntryUnlockStage;
        DeviceEntryUnlockStage deviceEntryUnlockStage2 = new DeviceEntryUnlockStage("HAL_ACQUISITION", 1);
        HAL_ACQUISITION = deviceEntryUnlockStage2;
        DeviceEntryUnlockStage deviceEntryUnlockStage3 = new DeviceEntryUnlockStage("HAL_AUTHENTICATED", 2);
        HAL_AUTHENTICATED = deviceEntryUnlockStage3;
        DeviceEntryUnlockStage deviceEntryUnlockStage4 = new DeviceEntryUnlockStage("EXIT_KEYGUARD", 3);
        EXIT_KEYGUARD = deviceEntryUnlockStage4;
        DeviceEntryUnlockStage deviceEntryUnlockStage5 = new DeviceEntryUnlockStage("STARTED", 4);
        STARTED = deviceEntryUnlockStage5;
        DeviceEntryUnlockStage deviceEntryUnlockStage6 = new DeviceEntryUnlockStage("STOPPED", 5);
        STOPPED = deviceEntryUnlockStage6;
        DeviceEntryUnlockStage deviceEntryUnlockStage7 = new DeviceEntryUnlockStage("UNKNOWN", 6);
        UNKNOWN = deviceEntryUnlockStage7;
        DeviceEntryUnlockStage deviceEntryUnlockStage8 = new DeviceEntryUnlockStage("UNLOCKED", 7);
        UNLOCKED = deviceEntryUnlockStage8;
        DeviceEntryUnlockStage[] deviceEntryUnlockStageArr = {deviceEntryUnlockStage, deviceEntryUnlockStage2, deviceEntryUnlockStage3, deviceEntryUnlockStage4, deviceEntryUnlockStage5, deviceEntryUnlockStage6, deviceEntryUnlockStage7, deviceEntryUnlockStage8};
        $VALUES = deviceEntryUnlockStageArr;
        EnumEntriesKt.enumEntries(deviceEntryUnlockStageArr);
    }

    public static DeviceEntryUnlockStage valueOf(String str) {
        return (DeviceEntryUnlockStage) Enum.valueOf(DeviceEntryUnlockStage.class, str);
    }

    public static DeviceEntryUnlockStage[] values() {
        return (DeviceEntryUnlockStage[]) $VALUES.clone();
    }
}

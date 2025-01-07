package com.android.systemui.keyguard.shared.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DevicePosture {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ DevicePosture[] $VALUES;
    public static final DevicePosture CLOSED;
    public static final Companion Companion;
    public static final DevicePosture FLIPPED;
    public static final DevicePosture HALF_OPENED;
    public static final DevicePosture OPENED;
    public static final DevicePosture UNKNOWN;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static DevicePosture toPosture(int i) {
            return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? DevicePosture.UNKNOWN : DevicePosture.FLIPPED : DevicePosture.OPENED : DevicePosture.HALF_OPENED : DevicePosture.CLOSED : DevicePosture.UNKNOWN;
        }
    }

    static {
        DevicePosture devicePosture = new DevicePosture("UNKNOWN", 0);
        UNKNOWN = devicePosture;
        DevicePosture devicePosture2 = new DevicePosture("CLOSED", 1);
        CLOSED = devicePosture2;
        DevicePosture devicePosture3 = new DevicePosture("HALF_OPENED", 2);
        HALF_OPENED = devicePosture3;
        DevicePosture devicePosture4 = new DevicePosture("OPENED", 3);
        OPENED = devicePosture4;
        DevicePosture devicePosture5 = new DevicePosture("FLIPPED", 4);
        FLIPPED = devicePosture5;
        DevicePosture[] devicePostureArr = {devicePosture, devicePosture2, devicePosture3, devicePosture4, devicePosture5};
        $VALUES = devicePostureArr;
        $ENTRIES = EnumEntriesKt.enumEntries(devicePostureArr);
        Companion = new Companion();
    }

    public static DevicePosture valueOf(String str) {
        return (DevicePosture) Enum.valueOf(DevicePosture.class, str);
    }

    public static DevicePosture[] values() {
        return (DevicePosture[]) $VALUES.clone();
    }
}

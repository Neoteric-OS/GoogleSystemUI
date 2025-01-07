package com.android.systemui.bluetooth.qsdialog;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceFetchTrigger {
    public static final /* synthetic */ DeviceFetchTrigger[] $VALUES;
    public static final DeviceFetchTrigger BLUETOOTH_CALLBACK_RECEIVED;
    public static final DeviceFetchTrigger BLUETOOTH_STATE_CHANGE_RECEIVED;
    public static final DeviceFetchTrigger FIRST_LOAD;

    static {
        DeviceFetchTrigger deviceFetchTrigger = new DeviceFetchTrigger("FIRST_LOAD", 0);
        FIRST_LOAD = deviceFetchTrigger;
        DeviceFetchTrigger deviceFetchTrigger2 = new DeviceFetchTrigger("BLUETOOTH_STATE_CHANGE_RECEIVED", 1);
        BLUETOOTH_STATE_CHANGE_RECEIVED = deviceFetchTrigger2;
        DeviceFetchTrigger deviceFetchTrigger3 = new DeviceFetchTrigger("BLUETOOTH_CALLBACK_RECEIVED", 2);
        BLUETOOTH_CALLBACK_RECEIVED = deviceFetchTrigger3;
        DeviceFetchTrigger[] deviceFetchTriggerArr = {deviceFetchTrigger, deviceFetchTrigger2, deviceFetchTrigger3};
        $VALUES = deviceFetchTriggerArr;
        EnumEntriesKt.enumEntries(deviceFetchTriggerArr);
    }

    public static DeviceFetchTrigger valueOf(String str) {
        return (DeviceFetchTrigger) Enum.valueOf(DeviceFetchTrigger.class, str);
    }

    public static DeviceFetchTrigger[] values() {
        return (DeviceFetchTrigger[]) $VALUES.clone();
    }
}

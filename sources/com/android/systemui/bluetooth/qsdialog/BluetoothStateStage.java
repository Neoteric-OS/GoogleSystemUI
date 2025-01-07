package com.android.systemui.bluetooth.qsdialog;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothStateStage {
    public static final /* synthetic */ BluetoothStateStage[] $VALUES;
    public static final BluetoothStateStage BLUETOOTH_STATE_CHANGE_RECEIVED;
    public static final BluetoothStateStage BLUETOOTH_STATE_VALUE_SET;
    public static final BluetoothStateStage USER_TOGGLED;

    static {
        BluetoothStateStage bluetoothStateStage = new BluetoothStateStage("USER_TOGGLED", 0);
        USER_TOGGLED = bluetoothStateStage;
        BluetoothStateStage bluetoothStateStage2 = new BluetoothStateStage("BLUETOOTH_STATE_VALUE_SET", 1);
        BLUETOOTH_STATE_VALUE_SET = bluetoothStateStage2;
        BluetoothStateStage bluetoothStateStage3 = new BluetoothStateStage("BLUETOOTH_STATE_CHANGE_RECEIVED", 2);
        BLUETOOTH_STATE_CHANGE_RECEIVED = bluetoothStateStage3;
        BluetoothStateStage[] bluetoothStateStageArr = {bluetoothStateStage, bluetoothStateStage2, bluetoothStateStage3};
        $VALUES = bluetoothStateStageArr;
        EnumEntriesKt.enumEntries(bluetoothStateStageArr);
    }

    public static BluetoothStateStage valueOf(String str) {
        return (BluetoothStateStage) Enum.valueOf(BluetoothStateStage.class, str);
    }

    public static BluetoothStateStage[] values() {
        return (BluetoothStateStage[]) $VALUES.clone();
    }
}

package com.android.systemui.bluetooth.qsdialog;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothTileDialogUiEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ BluetoothTileDialogUiEvent[] $VALUES;
    public static final BluetoothTileDialogUiEvent ACTIVE_DEVICE_DISCONNECT;
    public static final BluetoothTileDialogUiEvent AUDIO_SHARING_DEVICE_CLICKED;
    public static final BluetoothTileDialogUiEvent AVAILABLE_AUDIO_SHARING_DEVICE_CLICKED;
    public static final BluetoothTileDialogUiEvent BLUETOOTH_AUDIO_SHARING_BUTTON_CLICKED;
    public static final BluetoothTileDialogUiEvent BLUETOOTH_AUTO_ON_TOGGLE_CLICKED;
    public static final BluetoothTileDialogUiEvent BLUETOOTH_TILE_DIALOG_SHOWN;
    public static final BluetoothTileDialogUiEvent BLUETOOTH_TOGGLE_CLICKED;
    public static final BluetoothTileDialogUiEvent CONNECTED_DEVICE_SET_ACTIVE;
    public static final BluetoothTileDialogUiEvent CONNECTED_OTHER_DEVICE_DISCONNECT;
    public static final BluetoothTileDialogUiEvent DEVICE_CLICKED;
    public static final BluetoothTileDialogUiEvent DEVICE_GEAR_CLICKED;
    public static final BluetoothTileDialogUiEvent LAUNCH_SETTINGS_IN_SHARING_LE_DEVICE_CLICKED = null;
    public static final BluetoothTileDialogUiEvent LAUNCH_SETTINGS_IN_SHARING_NON_LE_DEVICE_CLICKED = null;
    public static final BluetoothTileDialogUiEvent LAUNCH_SETTINGS_NOT_SHARING_ACTIVE_LE_DEVICE_CLICKED = null;
    public static final BluetoothTileDialogUiEvent LAUNCH_SETTINGS_NOT_SHARING_SAVED_LE_DEVICE_CLICKED = null;
    public static final BluetoothTileDialogUiEvent PAIR_NEW_DEVICE_CLICKED;
    public static final BluetoothTileDialogUiEvent SAVED_DEVICE_CONNECT;
    public static final BluetoothTileDialogUiEvent SEE_ALL_CLICKED;
    private final int metricId;

    static {
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent = new BluetoothTileDialogUiEvent("BLUETOOTH_TILE_DIALOG_SHOWN", 0, 1493);
        BLUETOOTH_TILE_DIALOG_SHOWN = bluetoothTileDialogUiEvent;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent2 = new BluetoothTileDialogUiEvent("BLUETOOTH_TOGGLE_CLICKED", 1, 1494);
        BLUETOOTH_TOGGLE_CLICKED = bluetoothTileDialogUiEvent2;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent3 = new BluetoothTileDialogUiEvent("PAIR_NEW_DEVICE_CLICKED", 2, 1495);
        PAIR_NEW_DEVICE_CLICKED = bluetoothTileDialogUiEvent3;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent4 = new BluetoothTileDialogUiEvent("SEE_ALL_CLICKED", 3, 1496);
        SEE_ALL_CLICKED = bluetoothTileDialogUiEvent4;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent5 = new BluetoothTileDialogUiEvent("DEVICE_GEAR_CLICKED", 4, 1497);
        DEVICE_GEAR_CLICKED = bluetoothTileDialogUiEvent5;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent6 = new BluetoothTileDialogUiEvent("DEVICE_CLICKED", 5, 1498);
        DEVICE_CLICKED = bluetoothTileDialogUiEvent6;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent7 = new BluetoothTileDialogUiEvent("CONNECTED_DEVICE_SET_ACTIVE", 6, 1499);
        CONNECTED_DEVICE_SET_ACTIVE = bluetoothTileDialogUiEvent7;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent8 = new BluetoothTileDialogUiEvent("SAVED_DEVICE_CONNECT", 7, 1500);
        SAVED_DEVICE_CONNECT = bluetoothTileDialogUiEvent8;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent9 = new BluetoothTileDialogUiEvent("ACTIVE_DEVICE_DISCONNECT", 8, 1507);
        ACTIVE_DEVICE_DISCONNECT = bluetoothTileDialogUiEvent9;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent10 = new BluetoothTileDialogUiEvent("AUDIO_SHARING_DEVICE_CLICKED", 9, 1699);
        AUDIO_SHARING_DEVICE_CLICKED = bluetoothTileDialogUiEvent10;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent11 = new BluetoothTileDialogUiEvent("AVAILABLE_AUDIO_SHARING_DEVICE_CLICKED", 10, 1880);
        AVAILABLE_AUDIO_SHARING_DEVICE_CLICKED = bluetoothTileDialogUiEvent11;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent12 = new BluetoothTileDialogUiEvent("CONNECTED_OTHER_DEVICE_DISCONNECT", 11, 1508);
        CONNECTED_OTHER_DEVICE_DISCONNECT = bluetoothTileDialogUiEvent12;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent13 = new BluetoothTileDialogUiEvent("BLUETOOTH_AUTO_ON_TOGGLE_CLICKED", 12, 1617);
        BLUETOOTH_AUTO_ON_TOGGLE_CLICKED = bluetoothTileDialogUiEvent13;
        BluetoothTileDialogUiEvent bluetoothTileDialogUiEvent14 = new BluetoothTileDialogUiEvent("BLUETOOTH_AUDIO_SHARING_BUTTON_CLICKED", 13, 1700);
        BLUETOOTH_AUDIO_SHARING_BUTTON_CLICKED = bluetoothTileDialogUiEvent14;
        BluetoothTileDialogUiEvent[] bluetoothTileDialogUiEventArr = {bluetoothTileDialogUiEvent, bluetoothTileDialogUiEvent2, bluetoothTileDialogUiEvent3, bluetoothTileDialogUiEvent4, bluetoothTileDialogUiEvent5, bluetoothTileDialogUiEvent6, bluetoothTileDialogUiEvent7, bluetoothTileDialogUiEvent8, bluetoothTileDialogUiEvent9, bluetoothTileDialogUiEvent10, bluetoothTileDialogUiEvent11, bluetoothTileDialogUiEvent12, bluetoothTileDialogUiEvent13, bluetoothTileDialogUiEvent14, new BluetoothTileDialogUiEvent("LAUNCH_SETTINGS_IN_SHARING_LE_DEVICE_CLICKED", 14, 1717), new BluetoothTileDialogUiEvent("LAUNCH_SETTINGS_IN_SHARING_NON_LE_DEVICE_CLICKED", 15, 1718), new BluetoothTileDialogUiEvent("LAUNCH_SETTINGS_NOT_SHARING_SAVED_LE_DEVICE_CLICKED", 16, 1719), new BluetoothTileDialogUiEvent("LAUNCH_SETTINGS_NOT_SHARING_CONNECTED_LE_DEVICE_CLICKED", 17, 1720), new BluetoothTileDialogUiEvent("LAUNCH_SETTINGS_NOT_SHARING_ACTIVE_LE_DEVICE_CLICKED", 18, 1881)};
        $VALUES = bluetoothTileDialogUiEventArr;
        EnumEntriesKt.enumEntries(bluetoothTileDialogUiEventArr);
    }

    public BluetoothTileDialogUiEvent(String str, int i, int i2) {
        this.metricId = i2;
    }

    public static BluetoothTileDialogUiEvent valueOf(String str) {
        return (BluetoothTileDialogUiEvent) Enum.valueOf(BluetoothTileDialogUiEvent.class, str);
    }

    public static BluetoothTileDialogUiEvent[] values() {
        return (BluetoothTileDialogUiEvent[]) $VALUES.clone();
    }

    public final int getId() {
        return this.metricId;
    }
}

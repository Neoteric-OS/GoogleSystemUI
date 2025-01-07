package com.android.settingslib.bluetooth;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface BluetoothCallback {
    default void onAudioModeChanged() {
    }

    default void onAutoOnStateChanged(int i) {
    }

    default void onBluetoothStateChanged(int i) {
    }

    default void onDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice) {
    }

    default void onDeviceDeleted(CachedBluetoothDevice cachedBluetoothDevice) {
    }

    default void onAclConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
    }

    default void onActiveDeviceChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
    }

    default void onConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
    }

    default void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
    }

    default void onProfileConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i, int i2) {
    }
}

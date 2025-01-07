package com.android.systemui.bluetooth.qsdialog;

import android.bluetooth.BluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothTileDialogRepository {
    public final BluetoothAdapter bluetoothAdapter;
    public final LocalBluetoothManager localBluetoothManager;

    public BluetoothTileDialogRepository(LocalBluetoothManager localBluetoothManager, BluetoothAdapter bluetoothAdapter) {
        this.localBluetoothManager = localBluetoothManager;
        this.bluetoothAdapter = bluetoothAdapter;
    }
}

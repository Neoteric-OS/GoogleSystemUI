package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LocalBluetoothAdapter {
    public static LocalBluetoothAdapter sInstance;
    public final BluetoothAdapter mAdapter;
    public LocalBluetoothProfileManager mProfileManager;
    public int mState = Integer.MIN_VALUE;

    public LocalBluetoothAdapter(BluetoothAdapter bluetoothAdapter) {
        this.mAdapter = bluetoothAdapter;
    }

    public final void setBluetoothStateInt(int i) {
        LocalBluetoothProfileManager localBluetoothProfileManager;
        synchronized (this) {
            try {
                if (this.mState == i) {
                    return;
                }
                this.mState = i;
                if (i != 12 || (localBluetoothProfileManager = this.mProfileManager) == null) {
                    return;
                }
                localBluetoothProfileManager.updateLocalProfiles();
                localBluetoothProfileManager.mEventManager.readPairedDevices();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

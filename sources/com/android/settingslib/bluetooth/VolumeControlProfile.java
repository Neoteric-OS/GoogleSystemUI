package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothVolumeControl;
import android.content.Context;
import android.util.Log;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VolumeControlProfile implements LocalBluetoothProfile {
    public final CachedBluetoothDeviceManager mDeviceManager;
    public boolean mIsProfileReady;
    public final LocalBluetoothProfileManager mProfileManager;
    public BluetoothVolumeControl mService;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class VolumeControlProfileServiceListener implements BluetoothProfile.ServiceListener {
        public VolumeControlProfileServiceListener() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public final void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            Log.d("VolumeControlProfile", "Bluetooth service connected");
            BluetoothVolumeControl bluetoothVolumeControl = (BluetoothVolumeControl) bluetoothProfile;
            VolumeControlProfile.this.mService = bluetoothVolumeControl;
            List connectedDevices = bluetoothVolumeControl.getConnectedDevices();
            while (!connectedDevices.isEmpty()) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) connectedDevices.remove(0);
                CachedBluetoothDevice findDevice = VolumeControlProfile.this.mDeviceManager.findDevice(bluetoothDevice);
                if (findDevice == null) {
                    Log.d("VolumeControlProfile", "VolumeControlProfile found new device: " + bluetoothDevice);
                    findDevice = VolumeControlProfile.this.mDeviceManager.addDevice(bluetoothDevice);
                }
                findDevice.onProfileStateChanged(VolumeControlProfile.this, 2);
                findDevice.refresh();
            }
            VolumeControlProfile.this.mProfileManager.callServiceConnectedListeners();
            VolumeControlProfile.this.mIsProfileReady = true;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public final void onServiceDisconnected(int i) {
            Log.d("VolumeControlProfile", "Bluetooth service disconnected");
            VolumeControlProfile.this.mProfileManager.callServiceDisconnectedListeners();
            VolumeControlProfile.this.mIsProfileReady = false;
        }
    }

    public VolumeControlProfile(Context context, CachedBluetoothDeviceManager cachedBluetoothDeviceManager, LocalBluetoothProfileManager localBluetoothProfileManager) {
        this.mDeviceManager = cachedBluetoothDeviceManager;
        this.mProfileManager = localBluetoothProfileManager;
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(context, new VolumeControlProfileServiceListener(), 23);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final boolean accessProfileEnabled() {
        return false;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final int getConnectionPolicy(BluetoothDevice bluetoothDevice) {
        BluetoothVolumeControl bluetoothVolumeControl = this.mService;
        if (bluetoothVolumeControl == null || bluetoothDevice == null) {
            return 0;
        }
        return bluetoothVolumeControl.getConnectionPolicy(bluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final int getConnectionStatus(BluetoothDevice bluetoothDevice) {
        BluetoothVolumeControl bluetoothVolumeControl = this.mService;
        if (bluetoothVolumeControl == null) {
            return 0;
        }
        return bluetoothVolumeControl.getConnectionState(bluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final int getDrawableResource(BluetoothClass bluetoothClass) {
        return 0;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final int getProfileId() {
        return 23;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final boolean isEnabled(BluetoothDevice bluetoothDevice) {
        BluetoothVolumeControl bluetoothVolumeControl = this.mService;
        return (bluetoothVolumeControl == null || bluetoothDevice == null || bluetoothVolumeControl.getConnectionPolicy(bluetoothDevice) <= 0) ? false : true;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final boolean isProfileReady() {
        return this.mIsProfileReady;
    }

    public final String toString() {
        return "VCP";
    }
}

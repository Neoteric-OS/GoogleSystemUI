package com.android.systemui.bluetooth.qsdialog;

import android.content.Context;
import android.media.AudioManager;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SavedHearingDeviceItemFactory extends SavedDeviceItemFactory {
    @Override // com.android.systemui.bluetooth.qsdialog.SavedDeviceItemFactory, com.android.systemui.bluetooth.qsdialog.DeviceItemFactory
    public final boolean isFilterMatched(Context context, CachedBluetoothDevice cachedBluetoothDevice, AudioManager audioManager) {
        return !BluetoothUtils.isExclusivelyManagedBluetoothDevice(context, cachedBluetoothDevice.mDevice) && cachedBluetoothDevice.isHearingAidDevice() && cachedBluetoothDevice.mDevice.getBondState() == 12 && !cachedBluetoothDevice.isConnected();
    }
}

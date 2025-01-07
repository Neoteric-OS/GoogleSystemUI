package com.android.systemui.bluetooth.qsdialog;

import android.content.Context;
import android.media.AudioManager;
import android.util.Pair;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ActiveMediaDeviceItemFactory extends DeviceItemFactory {
    @Override // com.android.systemui.bluetooth.qsdialog.DeviceItemFactory
    public final DeviceItem create(Context context, CachedBluetoothDevice cachedBluetoothDevice) {
        DeviceItemType deviceItemType = DeviceItemType.ACTIVE_MEDIA_BLUETOOTH_DEVICE;
        String connectionSummary = cachedBluetoothDevice.getConnectionSummary();
        if (connectionSummary == null) {
            connectionSummary = "";
        }
        String str = connectionSummary;
        String string = context.getString(R.string.accessibility_quick_settings_bluetooth_device_tap_to_disconnect);
        String name = cachedBluetoothDevice.getName();
        Pair btClassDrawableWithDescription = BluetoothUtils.getBtClassDrawableWithDescription(context, cachedBluetoothDevice);
        return new DeviceItem(deviceItemType, cachedBluetoothDevice, name, str, new kotlin.Pair(btClassDrawableWithDescription.first, btClassDrawableWithDescription.second), Integer.valueOf(R.drawable.settingslib_switch_bar_bg_on), !cachedBluetoothDevice.isBusy(), string, true);
    }

    @Override // com.android.systemui.bluetooth.qsdialog.DeviceItemFactory
    public boolean isFilterMatched(Context context, CachedBluetoothDevice cachedBluetoothDevice, AudioManager audioManager) {
        return BluetoothUtils.isActiveMediaDevice(cachedBluetoothDevice) && BluetoothUtils.isAvailableMediaBluetoothDevice(cachedBluetoothDevice, audioManager);
    }
}

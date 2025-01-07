package com.android.systemui.bluetooth.qsdialog;

import android.content.Context;
import android.util.Pair;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConnectedDeviceItemFactory extends DeviceItemFactory {
    @Override // com.android.systemui.bluetooth.qsdialog.DeviceItemFactory
    public final DeviceItem create(Context context, CachedBluetoothDevice cachedBluetoothDevice) {
        DeviceItemType deviceItemType = DeviceItemType.CONNECTED_BLUETOOTH_DEVICE;
        String connectionSummary = cachedBluetoothDevice.getConnectionSummary();
        if (connectionSummary == null || connectionSummary.length() == 0) {
            connectionSummary = null;
        }
        if (connectionSummary == null) {
            connectionSummary = context.getString(R.string.quick_settings_bluetooth_device_connected);
        }
        String str = connectionSummary;
        int i = cachedBluetoothDevice.isBusy() ? R.drawable.bluetooth_tile_dialog_bg_off_busy : R.drawable.bluetooth_tile_dialog_bg_off;
        String string = context.getString(R.string.accessibility_quick_settings_bluetooth_device_tap_to_disconnect);
        String name = cachedBluetoothDevice.getName();
        Pair btClassDrawableWithDescription = BluetoothUtils.getBtClassDrawableWithDescription(context, cachedBluetoothDevice);
        return new DeviceItem(deviceItemType, cachedBluetoothDevice, name, str, new kotlin.Pair(btClassDrawableWithDescription.first, btClassDrawableWithDescription.second), Integer.valueOf(i), !cachedBluetoothDevice.isBusy(), string, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x003e, code lost:
    
        if (r2.getConnectionStatus(r4.mDevice) == 2) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0040, code lost:
    
        r2 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0051, code lost:
    
        if (r2.getConnectionStatus(r4.mDevice) == 2) goto L26;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    @Override // com.android.systemui.bluetooth.qsdialog.DeviceItemFactory
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isFilterMatched(android.content.Context r3, com.android.settingslib.bluetooth.CachedBluetoothDevice r4, android.media.AudioManager r5) {
        /*
            r2 = this;
            android.bluetooth.BluetoothDevice r2 = r4.mDevice
            boolean r2 = com.android.settingslib.bluetooth.BluetoothUtils.isExclusivelyManagedBluetoothDevice(r3, r2)
            r3 = 0
            if (r2 != 0) goto L58
            int r2 = r5.getMode()
            r5 = 1
            r0 = 2
            if (r2 == r5) goto L19
            if (r2 == r0) goto L19
            r1 = 3
            if (r2 != r1) goto L17
            goto L19
        L17:
            r2 = r0
            goto L1a
        L19:
            r2 = r5
        L1a:
            boolean r1 = com.android.settingslib.bluetooth.BluetoothUtils.isDeviceConnected(r4)
            if (r1 == 0) goto L54
            boolean r1 = r4.isConnectedAshaHearingAidDevice()
            if (r1 != 0) goto L54
            boolean r1 = r4.isConnectedLeAudioDevice()
            if (r1 == 0) goto L2d
            goto L54
        L2d:
            if (r2 == r5) goto L45
            if (r2 == r0) goto L32
            goto L54
        L32:
            com.android.settingslib.bluetooth.LocalBluetoothProfileManager r2 = r4.mProfileManager
            com.android.settingslib.bluetooth.A2dpProfile r2 = r2.mA2dpProfile
            if (r2 == 0) goto L42
            android.bluetooth.BluetoothDevice r4 = r4.mDevice
            int r2 = r2.getConnectionStatus(r4)
            if (r2 != r0) goto L42
        L40:
            r2 = r5
            goto L43
        L42:
            r2 = r3
        L43:
            r2 = r2 ^ r5
            goto L55
        L45:
            com.android.settingslib.bluetooth.LocalBluetoothProfileManager r2 = r4.mProfileManager
            com.android.settingslib.bluetooth.HeadsetProfile r2 = r2.mHeadsetProfile
            if (r2 == 0) goto L42
            android.bluetooth.BluetoothDevice r4 = r4.mDevice
            int r2 = r2.getConnectionStatus(r4)
            if (r2 != r0) goto L42
            goto L40
        L54:
            r2 = r3
        L55:
            if (r2 == 0) goto L58
            r3 = r5
        L58:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bluetooth.qsdialog.ConnectedDeviceItemFactory.isFilterMatched(android.content.Context, com.android.settingslib.bluetooth.CachedBluetoothDevice, android.media.AudioManager):boolean");
    }
}

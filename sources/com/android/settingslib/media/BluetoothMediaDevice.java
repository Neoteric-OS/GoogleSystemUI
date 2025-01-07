package com.android.settingslib.media;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaRoute2Info;
import android.media.RouteListingPreference;
import android.provider.DeviceConfig;
import android.util.Log;
import com.android.settingslib.bluetooth.BluetoothUtils;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.systemui.keyboard.KeyboardUI;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothMediaDevice extends MediaDevice {
    public final AudioManager mAudioManager;
    public final CachedBluetoothDevice mCachedDevice;

    public BluetoothMediaDevice(Context context, CachedBluetoothDevice cachedBluetoothDevice, MediaRoute2Info mediaRoute2Info, RouteListingPreference.Item item) {
        super(context, mediaRoute2Info, item);
        this.mCachedDevice = cachedBluetoothDevice;
        this.mAudioManager = (AudioManager) context.getSystemService(AudioManager.class);
        initDeviceRecord();
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final Drawable getIcon() {
        BluetoothDevice bluetoothDevice = this.mCachedDevice.mDevice;
        KeyboardUI.BluetoothErrorListener bluetoothErrorListener = BluetoothUtils.sErrorListener;
        if (!DeviceConfig.getBoolean("settings_ui", "bt_advanced_header_enabled", true)) {
            Log.d("BluetoothUtils", "isAdvancedDetailsHeader: advancedEnabled is false");
        } else if (BluetoothUtils.getBooleanMetaData(bluetoothDevice)) {
            Log.d("BluetoothUtils", "isAdvancedDetailsHeader: untetheredHeadset is true");
            return this.mContext.getDrawable(R.drawable.ic_earbuds_advanced);
        }
        return (Drawable) BluetoothUtils.getBtClassDrawableWithDescription(this.mContext, this.mCachedDevice).first;
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final Drawable getIconWithoutBackground() {
        BluetoothDevice bluetoothDevice = this.mCachedDevice.mDevice;
        KeyboardUI.BluetoothErrorListener bluetoothErrorListener = BluetoothUtils.sErrorListener;
        if (!DeviceConfig.getBoolean("settings_ui", "bt_advanced_header_enabled", true)) {
            Log.d("BluetoothUtils", "isAdvancedDetailsHeader: advancedEnabled is false");
        } else if (BluetoothUtils.getBooleanMetaData(bluetoothDevice)) {
            Log.d("BluetoothUtils", "isAdvancedDetailsHeader: untetheredHeadset is true");
            return this.mContext.getDrawable(R.drawable.ic_earbuds_advanced);
        }
        return (Drawable) BluetoothUtils.getBtClassDrawableWithDescription(this.mContext, this.mCachedDevice).first;
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final String getId() {
        return (!this.mCachedDevice.isHearingAidDevice() || this.mCachedDevice.getHiSyncId() == 0) ? this.mCachedDevice.mDevice.getAddress() : Long.toString(this.mCachedDevice.getHiSyncId());
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final String getName() {
        return this.mCachedDevice.getName();
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final int getSelectionBehavior() {
        return 1;
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final boolean isCarKitDevice() {
        BluetoothClass bluetoothClass = this.mCachedDevice.mDevice.getBluetoothClass();
        if (bluetoothClass == null) {
            return false;
        }
        int deviceClass = bluetoothClass.getDeviceClass();
        return deviceClass == 1032 || deviceClass == 1056;
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final boolean isConnected() {
        return this.mCachedDevice.mDevice.getBondState() == 12 && this.mCachedDevice.isConnected();
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final boolean isFastPairDevice() {
        CachedBluetoothDevice cachedBluetoothDevice = this.mCachedDevice;
        return cachedBluetoothDevice != null && BluetoothUtils.getBooleanMetaData(cachedBluetoothDevice.mDevice);
    }

    @Override // com.android.settingslib.media.MediaDevice
    public final boolean isMutingExpectedDevice() {
        return this.mAudioManager.getMutingExpectedDevice() != null && this.mCachedDevice.mDevice.getAddress().equals(this.mAudioManager.getMutingExpectedDevice().getAddress());
    }
}

package com.android.systemui.accessibility.hearingaid;

import android.content.Context;
import com.android.settingslib.bluetooth.LocalBluetoothManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HearingDevicesChecker {
    public final Context mContext;
    public final LocalBluetoothManager mLocalBluetoothManager;

    public HearingDevicesChecker(Context context, LocalBluetoothManager localBluetoothManager) {
        this.mContext = context;
        this.mLocalBluetoothManager = localBluetoothManager;
    }

    public final boolean isAnyPairedHearingDevice() {
        LocalBluetoothManager localBluetoothManager = this.mLocalBluetoothManager;
        if (localBluetoothManager != null && localBluetoothManager.mLocalAdapter.mAdapter.isEnabled()) {
            return localBluetoothManager.mCachedDeviceManager.getCachedDevicesCopy().stream().anyMatch(new HearingDevicesChecker$$ExternalSyntheticLambda0(this, 0));
        }
        return false;
    }
}

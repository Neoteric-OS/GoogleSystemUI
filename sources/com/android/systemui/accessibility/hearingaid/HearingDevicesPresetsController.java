package com.android.systemui.accessibility.hearingaid;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHapClient;
import android.util.Log;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.HapClientProfile;
import com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.LocalBluetoothProfileManager;
import com.android.settingslib.utils.ThreadUtils;
import com.android.systemui.accessibility.hearingaid.HearingDevicesDialogDelegate;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HearingDevicesPresetsController implements LocalBluetoothProfileManager.ServiceListener, BluetoothHapClient.Callback {
    public CachedBluetoothDevice mActiveHearingDevice;
    public final HapClientProfile mHapClientProfile;
    public final HearingDevicesDialogDelegate.AnonymousClass1 mPresetCallback;
    public final LocalBluetoothProfileManager mProfileManager;
    public int mSelectedPresetIndex;

    public HearingDevicesPresetsController(LocalBluetoothProfileManager localBluetoothProfileManager, HearingDevicesDialogDelegate.AnonymousClass1 anonymousClass1) {
        this.mProfileManager = localBluetoothProfileManager;
        this.mHapClientProfile = localBluetoothProfileManager.mHapClientProfile;
        this.mPresetCallback = anonymousClass1;
    }

    public final int getActivePresetIndex() {
        HapClientProfile hapClientProfile;
        CachedBluetoothDevice cachedBluetoothDevice = this.mActiveHearingDevice;
        if (cachedBluetoothDevice == null || (hapClientProfile = this.mHapClientProfile) == null) {
            return 0;
        }
        BluetoothDevice bluetoothDevice = cachedBluetoothDevice.mDevice;
        BluetoothHapClient bluetoothHapClient = hapClientProfile.mService;
        if (bluetoothHapClient != null) {
            return bluetoothHapClient.getActivePresetIndex(bluetoothDevice);
        }
        Log.w("HapClientProfile", "Proxy not attached to service. Cannot get active preset index.");
        return 0;
    }

    public final List getAllPresetInfo() {
        HapClientProfile hapClientProfile;
        List allPresetInfo;
        CachedBluetoothDevice cachedBluetoothDevice = this.mActiveHearingDevice;
        if (cachedBluetoothDevice == null || (hapClientProfile = this.mHapClientProfile) == null) {
            return Collections.emptyList();
        }
        BluetoothDevice bluetoothDevice = cachedBluetoothDevice.mDevice;
        BluetoothHapClient bluetoothHapClient = hapClientProfile.mService;
        if (bluetoothHapClient == null) {
            Log.w("HapClientProfile", "Proxy not attached to service. Cannot get all preset info.");
            allPresetInfo = new ArrayList();
        } else {
            allPresetInfo = bluetoothHapClient.getAllPresetInfo(bluetoothDevice);
        }
        return allPresetInfo.stream().filter(new HearingDevicesPresetsController$$ExternalSyntheticLambda0(1)).toList();
    }

    public final void onPresetInfoChanged(BluetoothDevice bluetoothDevice, List list, int i) {
        CachedBluetoothDevice cachedBluetoothDevice = this.mActiveHearingDevice;
        if (cachedBluetoothDevice != null && bluetoothDevice.equals(cachedBluetoothDevice.mDevice)) {
            Log.d("HearingDevicesPresetsController", "onPresetInfoChanged, device: " + bluetoothDevice.getAddress() + ", reason: " + i + ", infoList: " + list);
            HearingDevicesDialogDelegate.AnonymousClass1 anonymousClass1 = this.mPresetCallback;
            HearingDevicesDialogDelegate.this.mMainHandler.post(new HearingDevicesDialogDelegate$1$$ExternalSyntheticLambda0(anonymousClass1, getAllPresetInfo(), getActivePresetIndex(), 1));
        }
    }

    public final void onPresetSelected(BluetoothDevice bluetoothDevice, int i, int i2) {
        CachedBluetoothDevice cachedBluetoothDevice = this.mActiveHearingDevice;
        if (cachedBluetoothDevice != null && bluetoothDevice.equals(cachedBluetoothDevice.mDevice)) {
            StringBuilder sb = new StringBuilder("onPresetSelected, device: ");
            sb.append(bluetoothDevice.getAddress());
            sb.append(", presetIndex: ");
            sb.append(i);
            sb.append(", reason: ");
            LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, i2, "HearingDevicesPresetsController");
            HearingDevicesDialogDelegate.AnonymousClass1 anonymousClass1 = this.mPresetCallback;
            HearingDevicesDialogDelegate.this.mMainHandler.post(new HearingDevicesDialogDelegate$1$$ExternalSyntheticLambda0(anonymousClass1, getAllPresetInfo(), getActivePresetIndex(), 1));
        }
    }

    public final void onPresetSelectionFailed(BluetoothDevice bluetoothDevice, int i) {
        CachedBluetoothDevice cachedBluetoothDevice = this.mActiveHearingDevice;
        if (cachedBluetoothDevice != null && bluetoothDevice.equals(cachedBluetoothDevice.mDevice)) {
            Log.w("HearingDevicesPresetsController", "onPresetSelectionFailed, device: " + bluetoothDevice.getAddress() + ", reason: " + i);
            this.mPresetCallback.onPresetCommandFailed();
        }
    }

    public final void onPresetSelectionForGroupFailed(int i, int i2) {
        HapClientProfile hapClientProfile;
        int hapGroup;
        CachedBluetoothDevice cachedBluetoothDevice = this.mActiveHearingDevice;
        if (cachedBluetoothDevice == null || (hapClientProfile = this.mHapClientProfile) == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = cachedBluetoothDevice.mDevice;
        BluetoothHapClient bluetoothHapClient = hapClientProfile.mService;
        if (bluetoothHapClient == null) {
            Log.w("HapClientProfile", "Proxy not attached to service. Cannot get hap group.");
            hapGroup = -1;
        } else {
            hapGroup = bluetoothHapClient.getHapGroup(bluetoothDevice);
        }
        if (i == hapGroup) {
            Log.w("HearingDevicesPresetsController", "onPresetSelectionForGroupFailed, group: " + i + ", reason: " + i2);
            selectPresetIndependently(this.mSelectedPresetIndex);
        }
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfileManager.ServiceListener
    public final void onServiceConnected() {
        HapClientProfile hapClientProfile = this.mHapClientProfile;
        if (hapClientProfile == null || !hapClientProfile.mIsProfileReady) {
            return;
        }
        ((CopyOnWriteArrayList) this.mProfileManager.mServiceListeners).remove(this);
        registerHapCallback();
        HearingDevicesDialogDelegate.AnonymousClass1 anonymousClass1 = this.mPresetCallback;
        HearingDevicesDialogDelegate.this.mMainHandler.post(new HearingDevicesDialogDelegate$1$$ExternalSyntheticLambda0(anonymousClass1, getAllPresetInfo(), getActivePresetIndex(), 1));
    }

    public final void onSetPresetNameFailed(BluetoothDevice bluetoothDevice, int i) {
        CachedBluetoothDevice cachedBluetoothDevice = this.mActiveHearingDevice;
        if (cachedBluetoothDevice != null && bluetoothDevice.equals(cachedBluetoothDevice.mDevice)) {
            Log.w("HearingDevicesPresetsController", "onSetPresetNameFailed, device: " + bluetoothDevice.getAddress() + ", reason: " + i);
            this.mPresetCallback.onPresetCommandFailed();
        }
    }

    public final void onSetPresetNameForGroupFailed(int i, int i2) {
        HapClientProfile hapClientProfile;
        int hapGroup;
        CachedBluetoothDevice cachedBluetoothDevice = this.mActiveHearingDevice;
        if (cachedBluetoothDevice == null || (hapClientProfile = this.mHapClientProfile) == null) {
            return;
        }
        BluetoothDevice bluetoothDevice = cachedBluetoothDevice.mDevice;
        BluetoothHapClient bluetoothHapClient = hapClientProfile.mService;
        if (bluetoothHapClient == null) {
            Log.w("HapClientProfile", "Proxy not attached to service. Cannot get hap group.");
            hapGroup = -1;
        } else {
            hapGroup = bluetoothHapClient.getHapGroup(bluetoothDevice);
        }
        if (i == hapGroup) {
            Log.w("HearingDevicesPresetsController", "onSetPresetNameForGroupFailed, group: " + i + ", reason: " + i2);
        }
        this.mPresetCallback.onPresetCommandFailed();
    }

    public final void registerHapCallback() {
        HapClientProfile hapClientProfile = this.mHapClientProfile;
        if (hapClientProfile != null) {
            try {
                ListeningExecutorService backgroundExecutor = ThreadUtils.getBackgroundExecutor();
                BluetoothHapClient bluetoothHapClient = hapClientProfile.mService;
                if (bluetoothHapClient == null) {
                    Log.w("HapClientProfile", "Proxy not attached to service. Cannot register callback.");
                } else {
                    bluetoothHapClient.registerCallback(backgroundExecutor, this);
                }
            } catch (IllegalArgumentException e) {
                Log.w("HearingDevicesPresetsController", "Cannot register callback: " + e.getMessage());
            }
        }
    }

    public final void selectPresetIndependently(int i) {
        if (this.mActiveHearingDevice == null || this.mHapClientProfile == null) {
            return;
        }
        StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("selectPresetIndependently, presetIndex: ", ", device: ", i);
        m.append(this.mActiveHearingDevice.mDevice.getAddress());
        Log.d("HearingDevicesPresetsController", m.toString());
        this.mHapClientProfile.selectPreset(this.mActiveHearingDevice.mDevice, i);
        CachedBluetoothDevice cachedBluetoothDevice = this.mActiveHearingDevice.mSubDevice;
        if (cachedBluetoothDevice != null) {
            Log.d("HearingDevicesPresetsController", "selectPreset for subDevice, device: " + cachedBluetoothDevice);
            this.mHapClientProfile.selectPreset(cachedBluetoothDevice.mDevice, i);
        }
        Iterator it = ((HashSet) this.mActiveHearingDevice.mMemberDevices).iterator();
        while (it.hasNext()) {
            CachedBluetoothDevice cachedBluetoothDevice2 = (CachedBluetoothDevice) it.next();
            Log.d("HearingDevicesPresetsController", "selectPreset for memberDevice, device: " + cachedBluetoothDevice2);
            this.mHapClientProfile.selectPreset(cachedBluetoothDevice2.mDevice, i);
        }
    }

    public final void setHearingDeviceIfSupportHap(CachedBluetoothDevice cachedBluetoothDevice) {
        if (this.mHapClientProfile == null || cachedBluetoothDevice == null) {
            this.mActiveHearingDevice = null;
        } else if (cachedBluetoothDevice.getProfiles().stream().anyMatch(new HearingDevicesPresetsController$$ExternalSyntheticLambda0(0))) {
            this.mActiveHearingDevice = cachedBluetoothDevice;
        } else {
            this.mActiveHearingDevice = null;
        }
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfileManager.ServiceListener
    public final void onServiceDisconnected() {
    }
}

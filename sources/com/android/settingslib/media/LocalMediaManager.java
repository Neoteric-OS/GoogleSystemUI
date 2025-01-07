package com.android.settingslib.media;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.media.AudioDeviceAttributes;
import android.media.AudioManager;
import android.media.MediaRoute2Info;
import android.media.RouteListingPreference;
import android.media.RoutingSessionInfo;
import android.media.session.MediaController;
import android.text.TextUtils;
import android.util.Log;
import com.android.settingslib.bluetooth.A2dpProfile;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.CachedBluetoothDeviceManager;
import com.android.settingslib.bluetooth.HearingAidProfile;
import com.android.settingslib.bluetooth.LeAudioProfile;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfile;
import com.android.settingslib.bluetooth.LocalBluetoothProfileManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LocalMediaManager implements BluetoothCallback {
    AudioManager mAudioManager;
    public final Context mContext;
    MediaDevice mCurrentConnectedDevice;
    public final InfoMediaManager mInfoMediaManager;
    public final LocalBluetoothManager mLocalBluetoothManager;
    public MediaDevice mOnTransferBluetoothDevice;
    public final Collection mCallbacks = new CopyOnWriteArrayList();
    public final Object mMediaDevicesLock = new Object();
    final MediaDeviceCallback mMediaDeviceCallback = new MediaDeviceCallback();
    List mMediaDevices = new CopyOnWriteArrayList();
    List mDisconnectedMediaDevices = new CopyOnWriteArrayList();
    DeviceAttributeChangeCallback mDeviceAttributeChangeCallback = new DeviceAttributeChangeCallback();
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class DeviceAttributeChangeCallback implements CachedBluetoothDevice.Callback {
        public DeviceAttributeChangeCallback() {
        }

        @Override // com.android.settingslib.bluetooth.CachedBluetoothDevice.Callback
        public final void onDeviceAttributesChanged() {
            LocalMediaManager localMediaManager = LocalMediaManager.this;
            MediaDevice mediaDevice = localMediaManager.mOnTransferBluetoothDevice;
            if (mediaDevice != null && !((BluetoothMediaDevice) mediaDevice).mCachedDevice.isBusy() && !localMediaManager.mOnTransferBluetoothDevice.isConnected()) {
                localMediaManager.mOnTransferBluetoothDevice.mState = 3;
                localMediaManager.mOnTransferBluetoothDevice = null;
                Iterator it = ((CopyOnWriteArrayList) localMediaManager.getCallbacks()).iterator();
                while (it.hasNext()) {
                    ((DeviceCallback) it.next()).onRequestFailed(0);
                }
            }
            Iterator it2 = ((CopyOnWriteArrayList) localMediaManager.getCallbacks()).iterator();
            while (it2.hasNext()) {
                ((DeviceCallback) it2.next()).onDeviceAttributesChanged();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MediaDeviceCallback {
        public MediaDeviceCallback() {
        }

        public static boolean isMediaDevice(CachedBluetoothDevice cachedBluetoothDevice) {
            for (LocalBluetoothProfile localBluetoothProfile : cachedBluetoothDevice.getUiAccessibleProfiles()) {
                if ((localBluetoothProfile instanceof A2dpProfile) || (localBluetoothProfile instanceof HearingAidProfile) || (localBluetoothProfile instanceof LeAudioProfile)) {
                    return true;
                }
            }
            return false;
        }

        public final List buildDisconnectedBluetoothDevice() {
            LocalMediaManager localMediaManager = LocalMediaManager.this;
            BluetoothAdapter bluetoothAdapter = localMediaManager.mBluetoothAdapter;
            if (bluetoothAdapter == null) {
                Log.w("LocalMediaManager", "buildDisconnectedBluetoothDevice() BluetoothAdapter is null");
                return new ArrayList();
            }
            List mostRecentlyConnectedDevices = bluetoothAdapter.getMostRecentlyConnectedDevices();
            CachedBluetoothDeviceManager cachedBluetoothDeviceManager = localMediaManager.mLocalBluetoothManager.mCachedDeviceManager;
            ArrayList<CachedBluetoothDevice> arrayList = new ArrayList();
            Iterator it = mostRecentlyConnectedDevices.iterator();
            int i = 0;
            while (it.hasNext()) {
                CachedBluetoothDevice findDevice = cachedBluetoothDeviceManager.findDevice((BluetoothDevice) it.next());
                if (findDevice != null && findDevice.mDevice.getBondState() == 12 && !findDevice.isConnected() && isMediaDevice(findDevice)) {
                    i++;
                    arrayList.add(findDevice);
                    if (i >= 5) {
                        break;
                    }
                }
            }
            localMediaManager.unRegisterDeviceAttributeChangeCallback();
            localMediaManager.mDisconnectedMediaDevices.clear();
            for (CachedBluetoothDevice cachedBluetoothDevice : arrayList) {
                BluetoothMediaDevice bluetoothMediaDevice = new BluetoothMediaDevice(localMediaManager.mContext, cachedBluetoothDevice, null, null);
                if (!localMediaManager.mMediaDevices.contains(bluetoothMediaDevice)) {
                    ((CopyOnWriteArrayList) cachedBluetoothDevice.mCallbacks).add(localMediaManager.mDeviceAttributeChangeCallback);
                    localMediaManager.mDisconnectedMediaDevices.add(bluetoothMediaDevice);
                }
            }
            return new ArrayList(localMediaManager.mDisconnectedMediaDevices);
        }

        public final BluetoothMediaDevice getMutingExpectedDevice() {
            LocalMediaManager localMediaManager = LocalMediaManager.this;
            if (localMediaManager.mBluetoothAdapter != null && localMediaManager.mAudioManager.getMutingExpectedDevice() != null) {
                List mostRecentlyConnectedDevices = localMediaManager.mBluetoothAdapter.getMostRecentlyConnectedDevices();
                CachedBluetoothDeviceManager cachedBluetoothDeviceManager = localMediaManager.mLocalBluetoothManager.mCachedDeviceManager;
                Iterator it = mostRecentlyConnectedDevices.iterator();
                while (it.hasNext()) {
                    CachedBluetoothDevice findDevice = cachedBluetoothDeviceManager.findDevice((BluetoothDevice) it.next());
                    if (findDevice != null && findDevice.mDevice.getBondState() == 12 && !findDevice.isConnected() && isMediaDevice(findDevice)) {
                        AudioDeviceAttributes mutingExpectedDevice = localMediaManager.mAudioManager.getMutingExpectedDevice();
                        if (mutingExpectedDevice != null ? findDevice.mDevice.getAddress().equals(mutingExpectedDevice.getAddress()) : false) {
                            return new BluetoothMediaDevice(localMediaManager.mContext, findDevice, null, null);
                        }
                    }
                }
            }
            return null;
        }
    }

    public LocalMediaManager(Context context, LocalBluetoothManager localBluetoothManager, InfoMediaManager infoMediaManager, String str) {
        this.mContext = context;
        this.mLocalBluetoothManager = localBluetoothManager;
        this.mInfoMediaManager = infoMediaManager;
        this.mAudioManager = (AudioManager) context.getSystemService(AudioManager.class);
    }

    public final void connectDevice(MediaDevice mediaDevice) {
        MediaDevice mediaDeviceById = getMediaDeviceById(mediaDevice.getId());
        if (mediaDeviceById == null) {
            Log.w("LocalMediaManager", "connectDevice() connectDevice not in the list!");
            return;
        }
        if (mediaDeviceById instanceof BluetoothMediaDevice) {
            CachedBluetoothDevice cachedBluetoothDevice = ((BluetoothMediaDevice) mediaDeviceById).mCachedDevice;
            if (!cachedBluetoothDevice.isConnected() && !cachedBluetoothDevice.isBusy()) {
                this.mOnTransferBluetoothDevice = mediaDevice;
                mediaDeviceById.mState = 1;
                cachedBluetoothDevice.connect$1();
                return;
            }
        }
        if (mediaDeviceById.equals(this.mCurrentConnectedDevice)) {
            Log.d("LocalMediaManager", "connectDevice() this device is already connected! : " + mediaDeviceById.getName());
            return;
        }
        mediaDeviceById.mState = 1;
        InfoMediaManager infoMediaManager = this.mInfoMediaManager;
        infoMediaManager.getClass();
        if (mediaDeviceById.mRouteInfo == null) {
            Log.w("InfoMediaManager", "Unable to connect. RouteInfo is empty");
            return;
        }
        mediaDeviceById.mConnectedRecord++;
        ConnectionRecordManager connectionRecordManager = ConnectionRecordManager.getInstance();
        Context context = mediaDeviceById.mContext;
        String id = mediaDeviceById.getId();
        int i = mediaDeviceById.mConnectedRecord;
        synchronized (connectionRecordManager) {
            SharedPreferences.Editor edit = context.getSharedPreferences("seamless_transfer_record", 0).edit();
            connectionRecordManager.mLastSelectedDevice = id;
            edit.putInt(id, i);
            edit.putString("last_selected_device", connectionRecordManager.mLastSelectedDevice);
            edit.apply();
        }
        infoMediaManager.transferToRoute(mediaDeviceById.mRouteInfo);
    }

    public final Collection getCallbacks() {
        return new CopyOnWriteArrayList(this.mCallbacks);
    }

    public final MediaDevice getCurrentConnectedDevice() {
        return this.mCurrentConnectedDevice;
    }

    public final MediaDevice getMediaDeviceById(String str) {
        synchronized (this.mMediaDevicesLock) {
            try {
                for (MediaDevice mediaDevice : this.mMediaDevices) {
                    if (TextUtils.equals(mediaDevice.getId(), str)) {
                        return mediaDevice;
                    }
                }
                Log.i("LocalMediaManager", "getMediaDeviceById() failed to find device with id: " + str);
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final List getSelectedMediaDevice() {
        InfoMediaManager infoMediaManager = this.mInfoMediaManager;
        RoutingSessionInfo activeRoutingSession = infoMediaManager.getActiveRoutingSession();
        ArrayList arrayList = new ArrayList();
        for (MediaRoute2Info mediaRoute2Info : infoMediaManager.getSelectedRoutes(activeRoutingSession)) {
            arrayList.add(new InfoMediaDevice(infoMediaManager.mContext, mediaRoute2Info, (RouteListingPreference.Item) infoMediaManager.mPreferenceItemMap.get(mediaRoute2Info.getId())));
        }
        return arrayList;
    }

    public final boolean isActiveDevice(CachedBluetoothDevice cachedBluetoothDevice) {
        boolean z;
        boolean z2;
        LeAudioProfile leAudioProfile;
        HearingAidProfile hearingAidProfile;
        LocalBluetoothManager localBluetoothManager = this.mLocalBluetoothManager;
        A2dpProfile a2dpProfile = localBluetoothManager.mProfileManager.mA2dpProfile;
        boolean equals = a2dpProfile != null ? cachedBluetoothDevice.mDevice.equals(a2dpProfile.getActiveDevice()) : false;
        LocalBluetoothProfileManager localBluetoothProfileManager = localBluetoothManager.mProfileManager;
        if (equals || (hearingAidProfile = localBluetoothProfileManager.mHearingAidProfile) == null) {
            z = false;
        } else {
            BluetoothAdapter bluetoothAdapter = hearingAidProfile.mBluetoothAdapter;
            z = (bluetoothAdapter == null ? new ArrayList() : bluetoothAdapter.getActiveDevices(21)).contains(cachedBluetoothDevice.mDevice);
        }
        if (equals || z || (leAudioProfile = localBluetoothProfileManager.mLeAudioProfile) == null) {
            z2 = false;
        } else {
            BluetoothAdapter bluetoothAdapter2 = leAudioProfile.mBluetoothAdapter;
            z2 = (bluetoothAdapter2 == null ? new ArrayList() : bluetoothAdapter2.getActiveDevices(22)).contains(cachedBluetoothDevice.mDevice);
        }
        return equals || z || z2;
    }

    public final void registerCallback(DeviceCallback deviceCallback) {
        boolean isEmpty = ((CopyOnWriteArrayList) this.mCallbacks).isEmpty();
        if (((CopyOnWriteArrayList) this.mCallbacks).contains(deviceCallback)) {
            return;
        }
        ((CopyOnWriteArrayList) this.mCallbacks).add(deviceCallback);
        if (isEmpty) {
            MediaDeviceCallback mediaDeviceCallback = this.mMediaDeviceCallback;
            InfoMediaManager infoMediaManager = this.mInfoMediaManager;
            boolean isEmpty2 = ((CopyOnWriteArrayList) infoMediaManager.mCallbacks).isEmpty();
            if (((CopyOnWriteArrayList) infoMediaManager.mCallbacks).contains(mediaDeviceCallback)) {
                return;
            }
            ((CopyOnWriteArrayList) infoMediaManager.mCallbacks).add(mediaDeviceCallback);
            if (isEmpty2) {
                ((CopyOnWriteArrayList) infoMediaManager.mMediaDevices).clear();
                infoMediaManager.registerRouter();
                MediaController mediaController = infoMediaManager.mMediaController;
                if (mediaController != null) {
                    mediaController.registerCallback(infoMediaManager.mMediaControllerCallback);
                }
                RouteListingPreference routeListingPreference = infoMediaManager.getRouteListingPreference();
                Map map = infoMediaManager.mPreferenceItemMap;
                map.clear();
                if (routeListingPreference != null) {
                    routeListingPreference.getItems().forEach(new InfoMediaManager$Api34Impl$$ExternalSyntheticLambda3(map));
                }
                infoMediaManager.refreshDevices();
            }
        }
    }

    public final void unRegisterDeviceAttributeChangeCallback() {
        Iterator it = this.mDisconnectedMediaDevices.iterator();
        while (it.hasNext()) {
            CachedBluetoothDevice cachedBluetoothDevice = ((BluetoothMediaDevice) ((MediaDevice) it.next())).mCachedDevice;
            DeviceAttributeChangeCallback deviceAttributeChangeCallback = this.mDeviceAttributeChangeCallback;
            ((CopyOnWriteArrayList) cachedBluetoothDevice.mCallbacks).remove(deviceAttributeChangeCallback);
            cachedBluetoothDevice.mCallbackExecutorMap.remove(deviceAttributeChangeCallback);
        }
    }

    public final void unregisterCallback(DeviceCallback deviceCallback) {
        if (((CopyOnWriteArrayList) this.mCallbacks).remove(deviceCallback) && ((CopyOnWriteArrayList) this.mCallbacks).isEmpty()) {
            MediaDeviceCallback mediaDeviceCallback = this.mMediaDeviceCallback;
            InfoMediaManager infoMediaManager = this.mInfoMediaManager;
            if (((CopyOnWriteArrayList) infoMediaManager.mCallbacks).remove(mediaDeviceCallback) && ((CopyOnWriteArrayList) infoMediaManager.mCallbacks).isEmpty()) {
                MediaController mediaController = infoMediaManager.mMediaController;
                if (mediaController != null) {
                    mediaController.unregisterCallback(infoMediaManager.mMediaControllerCallback);
                }
                infoMediaManager.unregisterRouter();
            }
            unRegisterDeviceAttributeChangeCallback();
        }
    }

    public MediaDevice updateCurrentConnectedDevice() {
        synchronized (this.mMediaDevicesLock) {
            try {
                MediaDevice mediaDevice = null;
                for (MediaDevice mediaDevice2 : this.mMediaDevices) {
                    if (mediaDevice2 instanceof BluetoothMediaDevice) {
                        if (isActiveDevice(((BluetoothMediaDevice) mediaDevice2).mCachedDevice) && mediaDevice2.isConnected()) {
                            return mediaDevice2;
                        }
                    } else if (mediaDevice2 instanceof PhoneMediaDevice) {
                        mediaDevice = mediaDevice2;
                    }
                }
                return mediaDevice;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface DeviceCallback {
        void onDeviceListUpdate(List list);

        void onSelectedDeviceStateChanged(MediaDevice mediaDevice);

        default void onAboutToConnectDeviceRemoved() {
        }

        default void onDeviceAttributesChanged() {
        }

        default void onRequestFailed(int i) {
        }

        default void onAboutToConnectDeviceAdded(String str, String str2, Drawable drawable) {
        }
    }
}

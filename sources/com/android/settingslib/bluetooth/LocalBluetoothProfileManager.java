package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothCsipSetCoordinator;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHapClient;
import android.bluetooth.BluetoothHearingAid;
import android.bluetooth.BluetoothLeAudio;
import android.bluetooth.BluetoothPbap;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.content.Intent;
import android.os.ParcelUuid;
import android.util.Log;
import android.util.SparseIntArray;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.CollectionUtils;
import com.android.settingslib.bluetooth.BluetoothEventManager;
import com.android.settingslib.bluetooth.PbapServerProfile.PbapServiceListener;
import com.android.systemui.keyboard.KeyboardUI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LocalBluetoothProfileManager {
    public A2dpProfile mA2dpProfile;
    public A2dpSinkProfile mA2dpSinkProfile;
    public final Context mContext;
    public CsipSetCoordinatorProfile mCsipSetCoordinatorProfile;
    public final CachedBluetoothDeviceManager mDeviceManager;
    public final BluetoothEventManager mEventManager;
    public HapClientProfile mHapClientProfile;
    public HeadsetProfile mHeadsetProfile;
    public HearingAidProfile mHearingAidProfile;
    public HfpClientProfile mHfpClientProfile;
    public HidDeviceProfile mHidDeviceProfile;
    public HidProfile mHidProfile;
    public LocalBluetoothLeBroadcast mLeAudioBroadcast;
    public LocalBluetoothLeBroadcastAssistant mLeAudioBroadcastAssistant;
    public LeAudioProfile mLeAudioProfile;
    public MapClientProfile mMapClientProfile;
    public MapProfile mMapProfile;
    public OppProfile mOppProfile;
    public PanProfile mPanProfile;
    public PbapClientProfile mPbapClientProfile;
    public PbapServerProfile mPbapProfile;
    public SapProfile mSapProfile;
    public VolumeControlProfile mVolumeControlProfile;
    public final Map mProfileNameMap = new HashMap();
    public final Collection mServiceListeners = new CopyOnWriteArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PanStateChangedHandler extends StateChangedHandler {
        public final /* synthetic */ int $r8$classId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ PanStateChangedHandler(LocalBluetoothProfileManager localBluetoothProfileManager, LocalBluetoothProfile localBluetoothProfile, int i) {
            super(localBluetoothProfile);
            this.$r8$classId = i;
        }

        @Override // com.android.settingslib.bluetooth.LocalBluetoothProfileManager.StateChangedHandler, com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            switch (this.$r8$classId) {
                case 0:
                    ((PanProfile) this.mProfile).mDeviceRoleMap.put(bluetoothDevice, Integer.valueOf(intent.getIntExtra("android.bluetooth.pan.extra.LOCAL_ROLE", 0)));
                    super.onReceive(context, intent, bluetoothDevice);
                    break;
                default:
                    super.onReceive(context, intent, bluetoothDevice);
                    break;
            }
        }

        @Override // com.android.settingslib.bluetooth.LocalBluetoothProfileManager.StateChangedHandler
        public void onReceiveInternal(Intent intent, CachedBluetoothDevice cachedBluetoothDevice) {
            switch (this.$r8$classId) {
                case 1:
                    if (!"android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED".equals(intent.getAction())) {
                        super.onReceiveInternal(intent, cachedBluetoothDevice);
                        break;
                    } else {
                        if (intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0) != 10) {
                            cachedBluetoothDevice.onProfileStateChanged(this.mProfile, 2);
                        }
                        cachedBluetoothDevice.refresh();
                        break;
                    }
                default:
                    super.onReceiveInternal(intent, cachedBluetoothDevice);
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ServiceListener {
        void onServiceConnected();

        void onServiceDisconnected();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class StateChangedHandler implements BluetoothEventManager.Handler {
        public final LocalBluetoothProfile mProfile;

        public StateChangedHandler(LocalBluetoothProfile localBluetoothProfile) {
            this.mProfile = localBluetoothProfile;
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            LocalBluetoothProfileManager localBluetoothProfileManager = LocalBluetoothProfileManager.this;
            CachedBluetoothDevice findDevice = localBluetoothProfileManager.mDeviceManager.findDevice(bluetoothDevice);
            if (findDevice == null) {
                Log.w("LocalBluetoothProfileManager", "StateChangedHandler found new device: " + bluetoothDevice);
                findDevice = localBluetoothProfileManager.mDeviceManager.addDevice(bluetoothDevice);
            }
            onReceiveInternal(intent, findDevice);
        }

        public void onReceiveInternal(Intent intent, CachedBluetoothDevice cachedBluetoothDevice) {
            int deviceSide;
            int deviceMode;
            boolean z = false;
            int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
            int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.PREVIOUS_STATE", 0);
            boolean z2 = true;
            if (intExtra == 0 && intExtra2 == 1) {
                Log.i("LocalBluetoothProfileManager", "Failed to connect " + this.mProfile + " device");
            }
            if (LocalBluetoothProfileManager.this.mHearingAidProfile != null && (this.mProfile instanceof HearingAidProfile) && intExtra == 2) {
                if (cachedBluetoothDevice.getHiSyncId() == 0) {
                    HearingAidProfile hearingAidProfile = LocalBluetoothProfileManager.this.mHearingAidProfile;
                    BluetoothDevice bluetoothDevice = cachedBluetoothDevice.mDevice;
                    BluetoothHearingAid bluetoothHearingAid = hearingAidProfile.mService;
                    long hiSyncId = (bluetoothHearingAid == null || bluetoothDevice == null) ? 0L : bluetoothHearingAid.getHiSyncId(bluetoothDevice);
                    if (hiSyncId != 0) {
                        BluetoothDevice bluetoothDevice2 = cachedBluetoothDevice.mDevice;
                        BluetoothHearingAid bluetoothHearingAid2 = LocalBluetoothProfileManager.this.mHearingAidProfile.mService;
                        if (bluetoothHearingAid2 == null) {
                            Log.w("HearingAidProfile", "Proxy not attached to HearingAidService");
                            deviceSide = -1;
                        } else {
                            deviceSide = bluetoothHearingAid2.getDeviceSide(bluetoothDevice2);
                        }
                        int i = HearingAidInfo.ASHA_DEVICE_SIDE_TO_INTERNAL_SIDE_MAPPING.get(deviceSide, -1);
                        BluetoothHearingAid bluetoothHearingAid3 = LocalBluetoothProfileManager.this.mHearingAidProfile.mService;
                        if (bluetoothHearingAid3 == null) {
                            Log.w("HearingAidProfile", "Proxy not attached to HearingAidService");
                            deviceMode = -1;
                        } else {
                            deviceMode = bluetoothHearingAid3.getDeviceMode(bluetoothDevice2);
                        }
                        cachedBluetoothDevice.mHearingAidInfo = new HearingAidInfo(i, HearingAidInfo.ASHA_DEVICE_MODE_TO_INTERNAL_MODE_MAPPING.get(deviceMode, -1), hiSyncId);
                        cachedBluetoothDevice.dispatchAttributesChanged();
                    }
                }
                HearingAidStatsLogUtils.logHearingAidInfo(cachedBluetoothDevice);
            }
            LocalBluetoothProfileManager localBluetoothProfileManager = LocalBluetoothProfileManager.this;
            boolean z3 = localBluetoothProfileManager.mHapClientProfile != null && (this.mProfile instanceof HapClientProfile);
            boolean z4 = localBluetoothProfileManager.mLeAudioProfile != null && (this.mProfile instanceof LeAudioProfile);
            if ((z3 || z4) && intExtra == 2 && cachedBluetoothDevice.isConnectedHapClientDevice() && cachedBluetoothDevice.isConnectedLeAudioDevice()) {
                BluetoothDevice bluetoothDevice3 = cachedBluetoothDevice.mDevice;
                BluetoothLeAudio bluetoothLeAudio = LocalBluetoothProfileManager.this.mLeAudioProfile.mService;
                int audioLocation = (bluetoothLeAudio == null || bluetoothDevice3 == null) ? 0 : bluetoothLeAudio.getAudioLocation(bluetoothDevice3);
                SparseIntArray sparseIntArray = HearingAidInfo.ASHA_DEVICE_SIDE_TO_INTERNAL_SIDE_MAPPING;
                boolean z5 = (88413265 & audioLocation) != 0;
                boolean z6 = (audioLocation & 176826530) != 0;
                int i2 = (z5 && z6) ? 2 : z5 ? 0 : z6 ? 1 : -1;
                BluetoothHapClient bluetoothHapClient = LocalBluetoothProfileManager.this.mHapClientProfile.mService;
                cachedBluetoothDevice.mHearingAidInfo = new HearingAidInfo(i2, HearingAidInfo.HAP_DEVICE_TYPE_TO_INTERNAL_MODE_MAPPING.get(bluetoothHapClient == null ? -1 : bluetoothHapClient.getHearingAidType(bluetoothDevice3), -1), 0L);
                cachedBluetoothDevice.dispatchAttributesChanged();
                HearingAidStatsLogUtils.logHearingAidInfo(cachedBluetoothDevice);
            }
            CsipSetCoordinatorProfile csipSetCoordinatorProfile = LocalBluetoothProfileManager.this.mCsipSetCoordinatorProfile;
            if (csipSetCoordinatorProfile != null && (this.mProfile instanceof CsipSetCoordinatorProfile) && intExtra == 2 && cachedBluetoothDevice.mGroupId == -1) {
                BluetoothDevice bluetoothDevice4 = cachedBluetoothDevice.mDevice;
                BluetoothCsipSetCoordinator bluetoothCsipSetCoordinator = csipSetCoordinatorProfile.mService;
                Map groupUuidMapByDevice = (bluetoothCsipSetCoordinator == null || bluetoothDevice4 == null) ? null : bluetoothCsipSetCoordinator.getGroupUuidMapByDevice(bluetoothDevice4);
                if (groupUuidMapByDevice != null) {
                    Iterator it = groupUuidMapByDevice.entrySet().iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Map.Entry entry = (Map.Entry) it.next();
                        if (((ParcelUuid) entry.getValue()).equals(BluetoothUuid.CAP)) {
                            cachedBluetoothDevice.setGroupId(((Integer) entry.getKey()).intValue());
                            break;
                        }
                    }
                }
            }
            cachedBluetoothDevice.onProfileStateChanged(this.mProfile, intExtra);
            if (cachedBluetoothDevice.getHiSyncId() != 0 || cachedBluetoothDevice.mGroupId != -1) {
                CachedBluetoothDeviceManager cachedBluetoothDeviceManager = LocalBluetoothProfileManager.this.mDeviceManager;
                int profileId = this.mProfile.getProfileId();
                synchronized (cachedBluetoothDeviceManager) {
                    if ((profileId == 28 || profileId == 21 || profileId == 25) && intExtra == 2) {
                        cachedBluetoothDeviceManager.mHearingAidDeviceManager.syncDeviceIfNeeded(cachedBluetoothDevice);
                    }
                }
                CachedBluetoothDeviceManager cachedBluetoothDeviceManager2 = LocalBluetoothProfileManager.this.mDeviceManager;
                int profileId2 = this.mProfile.getProfileId();
                synchronized (cachedBluetoothDeviceManager2) {
                    if (profileId2 == 21) {
                        z = cachedBluetoothDeviceManager2.mHearingAidDeviceManager.onProfileConnectionStateChangedIfProcessed(cachedBluetoothDevice, intExtra);
                    } else if (profileId2 == 1 || profileId2 == 2 || profileId2 == 22 || profileId2 == 25) {
                        CsipDeviceManager csipDeviceManager = cachedBluetoothDeviceManager2.mCsipDeviceManager;
                        csipDeviceManager.getClass();
                        CsipDeviceManager.log("onProfileConnectionStateChangedIfProcessed: " + cachedBluetoothDevice + ", state: " + intExtra);
                        if (intExtra == 2 || intExtra == 0) {
                            z = csipDeviceManager.updateRelationshipOfGroupDevices(cachedBluetoothDevice.mGroupId);
                        }
                    }
                }
                z2 = true ^ z;
            }
            if (z2) {
                cachedBluetoothDevice.refresh();
                BluetoothEventManager bluetoothEventManager = LocalBluetoothProfileManager.this.mEventManager;
                int profileId3 = this.mProfile.getProfileId();
                Iterator it2 = ((CopyOnWriteArrayList) bluetoothEventManager.mCallbacks).iterator();
                while (it2.hasNext()) {
                    ((BluetoothCallback) it2.next()).onProfileConnectionStateChanged(cachedBluetoothDevice, intExtra, profileId3);
                }
                if (profileId3 == 29 && intExtra == 0) {
                    KeyboardUI.BluetoothErrorListener bluetoothErrorListener = BluetoothUtils.sErrorListener;
                    BluetoothAdapter.getDefaultAdapter();
                }
            }
        }
    }

    public LocalBluetoothProfileManager(Context context, LocalBluetoothAdapter localBluetoothAdapter, CachedBluetoothDeviceManager cachedBluetoothDeviceManager, BluetoothEventManager bluetoothEventManager) {
        this.mContext = context;
        this.mDeviceManager = cachedBluetoothDeviceManager;
        this.mEventManager = bluetoothEventManager;
        localBluetoothAdapter.mProfileManager = this;
        Log.d("LocalBluetoothProfileManager", "LocalBluetoothProfileManager construction complete");
    }

    public final void addProfile(LocalBluetoothProfile localBluetoothProfile, String str, String str2) {
        this.mEventManager.addProfileHandler(str2, new StateChangedHandler(localBluetoothProfile));
        this.mProfileNameMap.put(str, localBluetoothProfile);
    }

    public final void callServiceConnectedListeners() {
        Iterator it = new ArrayList(this.mServiceListeners).iterator();
        while (it.hasNext()) {
            ((ServiceListener) it.next()).onServiceConnected();
        }
    }

    public final void callServiceDisconnectedListeners() {
        Iterator it = new ArrayList(this.mServiceListeners).iterator();
        while (it.hasNext()) {
            ((ServiceListener) it.next()).onServiceDisconnected();
        }
    }

    public HidDeviceProfile getHidDeviceProfile() {
        return this.mHidDeviceProfile;
    }

    public final synchronized void updateLocalProfiles() {
        List supportedProfiles = BluetoothAdapter.getDefaultAdapter().getSupportedProfiles();
        if (CollectionUtils.isEmpty(supportedProfiles)) {
            Log.d("LocalBluetoothProfileManager", "supportedList is null");
            return;
        }
        if (this.mA2dpProfile == null && supportedProfiles.contains(2)) {
            Log.d("LocalBluetoothProfileManager", "Adding local A2DP profile");
            A2dpProfile a2dpProfile = new A2dpProfile(this.mContext, this.mDeviceManager, this);
            this.mA2dpProfile = a2dpProfile;
            addProfile(a2dpProfile, "A2DP", "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mA2dpSinkProfile == null && supportedProfiles.contains(11)) {
            Log.d("LocalBluetoothProfileManager", "Adding local A2DP SINK profile");
            A2dpSinkProfile a2dpSinkProfile = new A2dpSinkProfile(this.mContext, this.mDeviceManager);
            this.mA2dpSinkProfile = a2dpSinkProfile;
            addProfile(a2dpSinkProfile, "A2DPSink", "android.bluetooth.a2dp-sink.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mHeadsetProfile == null && supportedProfiles.contains(1)) {
            Log.d("LocalBluetoothProfileManager", "Adding local HEADSET profile");
            HeadsetProfile headsetProfile = new HeadsetProfile(this.mContext, this.mDeviceManager, this);
            this.mHeadsetProfile = headsetProfile;
            PanStateChangedHandler panStateChangedHandler = new PanStateChangedHandler(this, headsetProfile, 1);
            BluetoothEventManager bluetoothEventManager = this.mEventManager;
            bluetoothEventManager.addProfileHandler("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED", panStateChangedHandler);
            bluetoothEventManager.addProfileHandler("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED", panStateChangedHandler);
            this.mProfileNameMap.put("HEADSET", headsetProfile);
        }
        if (this.mHfpClientProfile == null && supportedProfiles.contains(16)) {
            Log.d("LocalBluetoothProfileManager", "Adding local HfpClient profile");
            HfpClientProfile hfpClientProfile = new HfpClientProfile(this.mContext, this.mDeviceManager);
            this.mHfpClientProfile = hfpClientProfile;
            addProfile(hfpClientProfile, "HEADSET_CLIENT", "android.bluetooth.headsetclient.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mMapClientProfile == null && supportedProfiles.contains(18)) {
            Log.d("LocalBluetoothProfileManager", "Adding local MAP CLIENT profile");
            MapClientProfile mapClientProfile = new MapClientProfile(this.mContext, this.mDeviceManager, this);
            this.mMapClientProfile = mapClientProfile;
            addProfile(mapClientProfile, "MAP Client", "android.bluetooth.mapmce.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mMapProfile == null && supportedProfiles.contains(9)) {
            Log.d("LocalBluetoothProfileManager", "Adding local MAP profile");
            MapProfile mapProfile = new MapProfile(this.mContext, this.mDeviceManager, this);
            this.mMapProfile = mapProfile;
            addProfile(mapProfile, "MAP", "android.bluetooth.map.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mOppProfile == null && supportedProfiles.contains(20)) {
            Log.d("LocalBluetoothProfileManager", "Adding local OPP profile");
            OppProfile oppProfile = new OppProfile();
            this.mOppProfile = oppProfile;
            this.mProfileNameMap.put("OPP", oppProfile);
        }
        if (this.mHearingAidProfile == null && supportedProfiles.contains(21)) {
            Log.d("LocalBluetoothProfileManager", "Adding local Hearing Aid profile");
            HearingAidProfile hearingAidProfile = new HearingAidProfile(this.mContext, this.mDeviceManager, this);
            this.mHearingAidProfile = hearingAidProfile;
            addProfile(hearingAidProfile, "HearingAid", "android.bluetooth.hearingaid.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mHapClientProfile == null && supportedProfiles.contains(28)) {
            Log.d("LocalBluetoothProfileManager", "Adding local HAP_CLIENT profile");
            HapClientProfile hapClientProfile = new HapClientProfile(this.mContext, this.mDeviceManager, this);
            this.mHapClientProfile = hapClientProfile;
            addProfile(hapClientProfile, "HapClient", "android.bluetooth.action.HAP_CONNECTION_STATE_CHANGED");
        }
        if (this.mHidProfile == null && supportedProfiles.contains(4)) {
            Log.d("LocalBluetoothProfileManager", "Adding local HID_HOST profile");
            HidProfile hidProfile = new HidProfile(this.mContext, this.mDeviceManager);
            this.mHidProfile = hidProfile;
            addProfile(hidProfile, "HID", "android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mHidDeviceProfile == null && supportedProfiles.contains(19)) {
            Log.d("LocalBluetoothProfileManager", "Adding local HID_DEVICE profile");
            HidDeviceProfile hidDeviceProfile = new HidDeviceProfile(this.mContext, this.mDeviceManager);
            this.mHidDeviceProfile = hidDeviceProfile;
            addProfile(hidDeviceProfile, "HID DEVICE", "android.bluetooth.hiddevice.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mPanProfile == null && supportedProfiles.contains(5)) {
            Log.d("LocalBluetoothProfileManager", "Adding local PAN profile");
            PanProfile panProfile = new PanProfile(this.mContext);
            this.mPanProfile = panProfile;
            this.mEventManager.addProfileHandler("android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED", new PanStateChangedHandler(this, panProfile, 0));
            this.mProfileNameMap.put("PAN", panProfile);
        }
        if (this.mPbapProfile == null && supportedProfiles.contains(6)) {
            Log.d("LocalBluetoothProfileManager", "Adding local PBAP profile");
            Context context = this.mContext;
            PbapServerProfile pbapServerProfile = new PbapServerProfile();
            BluetoothAdapter.getDefaultAdapter().getProfileProxy(context, pbapServerProfile.new PbapServiceListener(), 6);
            this.mPbapProfile = pbapServerProfile;
            addProfile(pbapServerProfile, PbapServerProfile.NAME, "android.bluetooth.pbap.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mPbapClientProfile == null && supportedProfiles.contains(17)) {
            Log.d("LocalBluetoothProfileManager", "Adding local PBAP Client profile");
            PbapClientProfile pbapClientProfile = new PbapClientProfile(this.mContext, this.mDeviceManager);
            this.mPbapClientProfile = pbapClientProfile;
            addProfile(pbapClientProfile, "PbapClient", "android.bluetooth.pbapclient.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mSapProfile == null && supportedProfiles.contains(10)) {
            Log.d("LocalBluetoothProfileManager", "Adding local SAP profile");
            SapProfile sapProfile = new SapProfile(this.mContext, this.mDeviceManager, this);
            this.mSapProfile = sapProfile;
            addProfile(sapProfile, "SAP", "android.bluetooth.sap.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mVolumeControlProfile == null && supportedProfiles.contains(23)) {
            Log.d("LocalBluetoothProfileManager", "Adding local Volume Control profile");
            VolumeControlProfile volumeControlProfile = new VolumeControlProfile(this.mContext, this.mDeviceManager, this);
            this.mVolumeControlProfile = volumeControlProfile;
            addProfile(volumeControlProfile, "VCP", "android.bluetooth.volume-control.profile.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mLeAudioProfile == null && supportedProfiles.contains(22)) {
            Log.d("LocalBluetoothProfileManager", "Adding local LE_AUDIO profile");
            LeAudioProfile leAudioProfile = new LeAudioProfile(this.mContext, this.mDeviceManager, this);
            this.mLeAudioProfile = leAudioProfile;
            addProfile(leAudioProfile, "LE_AUDIO", "android.bluetooth.action.LE_AUDIO_CONNECTION_STATE_CHANGED");
        }
        if (this.mLeAudioBroadcast == null && supportedProfiles.contains(26)) {
            Log.d("LocalBluetoothProfileManager", "Adding local LE_AUDIO_BROADCAST profile");
            LocalBluetoothLeBroadcast localBluetoothLeBroadcast = new LocalBluetoothLeBroadcast(this.mContext, this.mDeviceManager);
            this.mLeAudioBroadcast = localBluetoothLeBroadcast;
            this.mProfileNameMap.put("LE_AUDIO_BROADCAST", localBluetoothLeBroadcast);
        }
        if (this.mLeAudioBroadcastAssistant == null && supportedProfiles.contains(29)) {
            Log.d("LocalBluetoothProfileManager", "Adding local LE_AUDIO_BROADCAST_ASSISTANT profile");
            LocalBluetoothLeBroadcastAssistant localBluetoothLeBroadcastAssistant = new LocalBluetoothLeBroadcastAssistant(this.mContext, this.mDeviceManager, this);
            this.mLeAudioBroadcastAssistant = localBluetoothLeBroadcastAssistant;
            addProfile(localBluetoothLeBroadcastAssistant, "LE_AUDIO_BROADCAST", "android.bluetooth.action.CONNECTION_STATE_CHANGED");
        }
        if (this.mCsipSetCoordinatorProfile == null && supportedProfiles.contains(25)) {
            Log.d("LocalBluetoothProfileManager", "Adding local CSIP set coordinator profile");
            CsipSetCoordinatorProfile csipSetCoordinatorProfile = new CsipSetCoordinatorProfile(this.mContext, this.mDeviceManager, this);
            this.mCsipSetCoordinatorProfile = csipSetCoordinatorProfile;
            addProfile(csipSetCoordinatorProfile, "CSIP Set Coordinator", "android.bluetooth.action.CSIS_CONNECTION_STATE_CHANGED");
        }
        this.mEventManager.registerProfileIntentReceiver();
    }

    public final synchronized void updateProfiles(ParcelUuid[] parcelUuidArr, ParcelUuid[] parcelUuidArr2, Collection collection, Collection collection2, boolean z, BluetoothDevice bluetoothDevice) {
        HidProfile hidProfile;
        HearingAidProfile hearingAidProfile;
        OppProfile oppProfile;
        A2dpSinkProfile a2dpSinkProfile;
        A2dpProfile a2dpProfile;
        LeAudioProfile leAudioProfile;
        try {
            CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) collection2;
            copyOnWriteArrayList.clear();
            copyOnWriteArrayList.addAll(collection);
            Log.d("LocalBluetoothProfileManager", "Current Profiles" + collection.toString());
            CopyOnWriteArrayList copyOnWriteArrayList2 = (CopyOnWriteArrayList) collection;
            copyOnWriteArrayList2.clear();
            if (ArrayUtils.contains(parcelUuidArr, BluetoothUuid.LE_AUDIO) && (leAudioProfile = this.mLeAudioProfile) != null) {
                copyOnWriteArrayList2.add(leAudioProfile);
                copyOnWriteArrayList.remove(this.mLeAudioProfile);
            }
            if (this.mHeadsetProfile != null && ((ArrayUtils.contains(parcelUuidArr2, BluetoothUuid.HSP_AG) && ArrayUtils.contains(parcelUuidArr, BluetoothUuid.HSP)) || (ArrayUtils.contains(parcelUuidArr2, BluetoothUuid.HFP_AG) && ArrayUtils.contains(parcelUuidArr, BluetoothUuid.HFP)))) {
                copyOnWriteArrayList2.add(this.mHeadsetProfile);
                copyOnWriteArrayList.remove(this.mHeadsetProfile);
            }
            if (this.mHfpClientProfile != null && ArrayUtils.contains(parcelUuidArr, BluetoothUuid.HFP_AG) && ArrayUtils.contains(parcelUuidArr2, BluetoothUuid.HFP)) {
                copyOnWriteArrayList2.add(this.mHfpClientProfile);
                copyOnWriteArrayList.remove(this.mHfpClientProfile);
            }
            if (BluetoothUuid.containsAnyUuid(parcelUuidArr, A2dpProfile.SINK_UUIDS) && (a2dpProfile = this.mA2dpProfile) != null) {
                copyOnWriteArrayList2.add(a2dpProfile);
                copyOnWriteArrayList.remove(this.mA2dpProfile);
            }
            if (BluetoothUuid.containsAnyUuid(parcelUuidArr, A2dpSinkProfile.SRC_UUIDS) && (a2dpSinkProfile = this.mA2dpSinkProfile) != null) {
                copyOnWriteArrayList2.add(a2dpSinkProfile);
                copyOnWriteArrayList.remove(this.mA2dpSinkProfile);
            }
            if (ArrayUtils.contains(parcelUuidArr, BluetoothUuid.OBEX_OBJECT_PUSH) && (oppProfile = this.mOppProfile) != null) {
                copyOnWriteArrayList2.add(oppProfile);
                copyOnWriteArrayList.remove(this.mOppProfile);
            }
            if ((ArrayUtils.contains(parcelUuidArr, BluetoothUuid.HID) || ArrayUtils.contains(parcelUuidArr, BluetoothUuid.HOGP)) && (hidProfile = this.mHidProfile) != null) {
                copyOnWriteArrayList2.add(hidProfile);
                copyOnWriteArrayList.remove(this.mHidProfile);
            }
            HidDeviceProfile hidDeviceProfile = this.mHidDeviceProfile;
            if (hidDeviceProfile != null && hidDeviceProfile.getConnectionStatus(bluetoothDevice) != 0) {
                copyOnWriteArrayList2.add(this.mHidDeviceProfile);
                copyOnWriteArrayList.remove(this.mHidDeviceProfile);
            }
            if (z) {
                Log.d("LocalBluetoothProfileManager", "Valid PAN-NAP connection exists.");
            }
            if ((ArrayUtils.contains(parcelUuidArr, BluetoothUuid.NAP) && this.mPanProfile != null) || z) {
                copyOnWriteArrayList2.add(this.mPanProfile);
                copyOnWriteArrayList.remove(this.mPanProfile);
            }
            MapProfile mapProfile = this.mMapProfile;
            if (mapProfile != null && mapProfile.getConnectionStatus(bluetoothDevice) == 2) {
                copyOnWriteArrayList2.add(this.mMapProfile);
                copyOnWriteArrayList.remove(this.mMapProfile);
                this.mMapProfile.setEnabled(bluetoothDevice, true);
            }
            PbapServerProfile pbapServerProfile = this.mPbapProfile;
            if (pbapServerProfile != null && pbapServerProfile.getConnectionStatus(bluetoothDevice) == 2) {
                copyOnWriteArrayList2.add(this.mPbapProfile);
                copyOnWriteArrayList.remove(this.mPbapProfile);
                BluetoothPbap bluetoothPbap = this.mPbapProfile.mService;
            }
            if (this.mMapClientProfile != null && BluetoothUuid.containsAnyUuid(parcelUuidArr, MapClientProfile.UUIDS)) {
                copyOnWriteArrayList2.add(this.mMapClientProfile);
                copyOnWriteArrayList.remove(this.mMapClientProfile);
            }
            if (this.mPbapClientProfile != null && BluetoothUuid.containsAnyUuid(parcelUuidArr, PbapClientProfile.SRC_UUIDS)) {
                copyOnWriteArrayList2.add(this.mPbapClientProfile);
                copyOnWriteArrayList.remove(this.mPbapClientProfile);
            }
            if (ArrayUtils.contains(parcelUuidArr, BluetoothUuid.HEARING_AID) && (hearingAidProfile = this.mHearingAidProfile) != null) {
                copyOnWriteArrayList2.add(hearingAidProfile);
                copyOnWriteArrayList.remove(this.mHearingAidProfile);
            }
            if (this.mHapClientProfile != null && ArrayUtils.contains(parcelUuidArr, BluetoothUuid.HAS)) {
                copyOnWriteArrayList2.add(this.mHapClientProfile);
                copyOnWriteArrayList.remove(this.mHapClientProfile);
            }
            if (this.mSapProfile != null && ArrayUtils.contains(parcelUuidArr, BluetoothUuid.SAP)) {
                copyOnWriteArrayList2.add(this.mSapProfile);
                copyOnWriteArrayList.remove(this.mSapProfile);
            }
            if (this.mVolumeControlProfile != null && ArrayUtils.contains(parcelUuidArr, BluetoothUuid.VOLUME_CONTROL)) {
                copyOnWriteArrayList2.add(this.mVolumeControlProfile);
                copyOnWriteArrayList.remove(this.mVolumeControlProfile);
            }
            if (this.mCsipSetCoordinatorProfile != null && ArrayUtils.contains(parcelUuidArr, BluetoothUuid.COORDINATED_SET)) {
                copyOnWriteArrayList2.add(this.mCsipSetCoordinatorProfile);
                copyOnWriteArrayList.remove(this.mCsipSetCoordinatorProfile);
            }
            Log.d("LocalBluetoothProfileManager", "New Profiles" + collection.toString());
        } catch (Throwable th) {
            throw th;
        }
    }
}

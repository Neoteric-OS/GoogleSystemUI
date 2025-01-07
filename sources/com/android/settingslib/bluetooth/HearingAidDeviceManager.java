package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHapClient;
import android.bluetooth.BluetoothHearingAid;
import android.bluetooth.BluetoothLeAudio;
import android.content.ContentResolver;
import android.content.Context;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.media.AudioAttributes;
import android.media.AudioDeviceAttributes;
import android.media.AudioDeviceInfo;
import android.media.audiopolicy.AudioProductStrategy;
import android.provider.Settings;
import android.util.FeatureFlagUtils;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class HearingAidDeviceManager {
    public final LocalBluetoothManager mBtManager;
    public final List mCachedDevices;
    public final ContentResolver mContentResolver;
    public final Context mContext;
    public final HearingAidAudioRoutingHelper mRoutingHelper;

    public HearingAidDeviceManager(Context context, LocalBluetoothManager localBluetoothManager, List list) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mBtManager = localBluetoothManager;
        this.mCachedDevices = list;
        this.mRoutingHelper = new HearingAidAudioRoutingHelper(context);
    }

    public static boolean isValidHiSyncId(long j) {
        return j != 0;
    }

    public final CachedBluetoothDevice findMainDevice(CachedBluetoothDevice cachedBluetoothDevice) {
        CachedBluetoothDevice cachedBluetoothDevice2;
        List<CachedBluetoothDevice> list = this.mCachedDevices;
        if (list == null) {
            return null;
        }
        for (CachedBluetoothDevice cachedBluetoothDevice3 : list) {
            if (cachedBluetoothDevice3.mGroupId != -1) {
                Iterator it = ((HashSet) cachedBluetoothDevice3.mMemberDevices).iterator();
                while (it.hasNext()) {
                    CachedBluetoothDevice cachedBluetoothDevice4 = (CachedBluetoothDevice) it.next();
                    if (cachedBluetoothDevice4 != null && cachedBluetoothDevice4.equals(cachedBluetoothDevice)) {
                        return cachedBluetoothDevice3;
                    }
                }
            }
            if (isValidHiSyncId(cachedBluetoothDevice3.getHiSyncId()) && (cachedBluetoothDevice2 = cachedBluetoothDevice3.mSubDevice) != null && cachedBluetoothDevice2.equals(cachedBluetoothDevice)) {
                return cachedBluetoothDevice3;
            }
        }
        return null;
    }

    public final HearingAidInfo generateHearingAidInfo(CachedBluetoothDevice cachedBluetoothDevice) {
        int deviceSide;
        int deviceMode;
        int i = 0;
        LocalBluetoothProfileManager localBluetoothProfileManager = this.mBtManager.mProfileManager;
        HearingAidProfile hearingAidProfile = localBluetoothProfileManager.mHearingAidProfile;
        if (hearingAidProfile == null) {
            Log.w("HearingAidDeviceManager", "HearingAidProfile is not supported on this device");
        } else {
            BluetoothDevice bluetoothDevice = cachedBluetoothDevice.mDevice;
            BluetoothHearingAid bluetoothHearingAid = hearingAidProfile.mService;
            long hiSyncId = (bluetoothHearingAid == null || bluetoothDevice == null) ? 0L : bluetoothHearingAid.getHiSyncId(bluetoothDevice);
            if (isValidHiSyncId(hiSyncId)) {
                BluetoothDevice bluetoothDevice2 = cachedBluetoothDevice.mDevice;
                BluetoothHearingAid bluetoothHearingAid2 = hearingAidProfile.mService;
                if (bluetoothHearingAid2 == null) {
                    Log.w("HearingAidProfile", "Proxy not attached to HearingAidService");
                    deviceSide = -1;
                } else {
                    deviceSide = bluetoothHearingAid2.getDeviceSide(bluetoothDevice2);
                }
                int i2 = HearingAidInfo.ASHA_DEVICE_SIDE_TO_INTERNAL_SIDE_MAPPING.get(deviceSide, -1);
                BluetoothDevice bluetoothDevice3 = cachedBluetoothDevice.mDevice;
                BluetoothHearingAid bluetoothHearingAid3 = hearingAidProfile.mService;
                if (bluetoothHearingAid3 == null) {
                    Log.w("HearingAidProfile", "Proxy not attached to HearingAidService");
                    deviceMode = -1;
                } else {
                    deviceMode = bluetoothHearingAid3.getDeviceMode(bluetoothDevice3);
                }
                HearingAidInfo hearingAidInfo = new HearingAidInfo(i2, HearingAidInfo.ASHA_DEVICE_MODE_TO_INTERNAL_MODE_MAPPING.get(deviceMode, -1), hiSyncId);
                Log.d("HearingAidDeviceManager", "generateHearingAidInfo, " + cachedBluetoothDevice + ", info=" + hearingAidInfo);
                return hearingAidInfo;
            }
        }
        HapClientProfile hapClientProfile = localBluetoothProfileManager.mHapClientProfile;
        LeAudioProfile leAudioProfile = localBluetoothProfileManager.mLeAudioProfile;
        if (hapClientProfile == null || leAudioProfile == null) {
            Log.w("HearingAidDeviceManager", "HapClientProfile or LeAudioProfile is not supported on this device");
            return null;
        }
        if (!cachedBluetoothDevice.getProfiles().stream().anyMatch(new HearingAidDeviceManager$$ExternalSyntheticLambda0(i))) {
            return null;
        }
        BluetoothDevice bluetoothDevice4 = cachedBluetoothDevice.mDevice;
        BluetoothLeAudio bluetoothLeAudio = leAudioProfile.mService;
        int audioLocation = (bluetoothLeAudio == null || bluetoothDevice4 == null) ? 0 : bluetoothLeAudio.getAudioLocation(bluetoothDevice4);
        BluetoothDevice bluetoothDevice5 = cachedBluetoothDevice.mDevice;
        BluetoothHapClient bluetoothHapClient = hapClientProfile.mService;
        int hearingAidType = bluetoothHapClient == null ? -1 : bluetoothHapClient.getHearingAidType(bluetoothDevice5);
        if (audioLocation == 0 || hearingAidType == -1) {
            return null;
        }
        SparseIntArray sparseIntArray = HearingAidInfo.ASHA_DEVICE_SIDE_TO_INTERNAL_SIDE_MAPPING;
        boolean z = (88413265 & audioLocation) != 0;
        boolean z2 = (audioLocation & 176826530) != 0;
        if (z && z2) {
            i = 2;
        } else if (!z) {
            i = z2 ? 1 : -1;
        }
        HearingAidInfo hearingAidInfo2 = new HearingAidInfo(i, HearingAidInfo.HAP_DEVICE_TYPE_TO_INTERNAL_MODE_MAPPING.get(hearingAidType, -1), 0L);
        Log.d("HearingAidDeviceManager", "generateHearingAidInfo, " + cachedBluetoothDevice + ", info=" + hearingAidInfo2);
        return hearingAidInfo2;
    }

    public final void onActiveDeviceChanged(CachedBluetoothDevice cachedBluetoothDevice) {
        if (FeatureFlagUtils.isEnabled(this.mContext, "settings_audio_routing")) {
            AudioDeviceAttributes audioDeviceAttributes = null;
            if (!cachedBluetoothDevice.isActiveDevice(21) && !cachedBluetoothDevice.isActiveDevice(22)) {
                setPreferredDeviceRoutingStrategies(0, null, HearingAidAudioRoutingConstants.CALL_ROUTING_ATTRIBUTES);
                setPreferredDeviceRoutingStrategies(0, null, HearingAidAudioRoutingConstants.MEDIA_ROUTING_ATTRIBUTES);
                setPreferredDeviceRoutingStrategies(0, null, HearingAidAudioRoutingConstants.RINGTONE_ROUTING_ATTRIBUTES);
                setPreferredDeviceRoutingStrategies(0, null, HearingAidAudioRoutingConstants.NOTIFICATION_ROUTING_ATTRIBUTES);
                return;
            }
            HearingAidAudioRoutingHelper hearingAidAudioRoutingHelper = this.mRoutingHelper;
            hearingAidAudioRoutingHelper.getClass();
            if (cachedBluetoothDevice.isHearingAidDevice()) {
                for (AudioDeviceInfo audioDeviceInfo : hearingAidAudioRoutingHelper.mAudioManager.getDevices(2)) {
                    if (audioDeviceInfo.getType() == 23 || audioDeviceInfo.getType() == 26) {
                        final String address = audioDeviceInfo.getAddress();
                        CachedBluetoothDevice cachedBluetoothDevice2 = cachedBluetoothDevice.mSubDevice;
                        Set set = cachedBluetoothDevice.mMemberDevices;
                        if (cachedBluetoothDevice.mDevice.getAddress().equals(address) || ((cachedBluetoothDevice2 != null && cachedBluetoothDevice2.mDevice.getAddress().equals(address)) || (!set.isEmpty() && set.stream().anyMatch(new Predicate() { // from class: com.android.settingslib.bluetooth.HearingAidAudioRoutingHelper$$ExternalSyntheticLambda0
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                return ((CachedBluetoothDevice) obj).mDevice.getAddress().equals(address);
                            }
                        })))) {
                            audioDeviceAttributes = new AudioDeviceAttributes(audioDeviceInfo);
                            break;
                        }
                    }
                }
            }
            if (audioDeviceAttributes == null) {
                Log.w("HearingAidDeviceManager", "Can not find expected AudioDeviceAttributes for hearing device: " + cachedBluetoothDevice.mDevice.getAnonymizedAddress());
                return;
            }
            int i = Settings.Secure.getInt(this.mContentResolver, "hearing_aid_call_routing", 0);
            int i2 = Settings.Secure.getInt(this.mContentResolver, "hearing_aid_media_routing", 0);
            int i3 = Settings.Secure.getInt(this.mContentResolver, "hearing_aid_ringtone_routing", 0);
            int i4 = Settings.Secure.getInt(this.mContentResolver, "hearing_aid_notification_routing", 0);
            setPreferredDeviceRoutingStrategies(i, audioDeviceAttributes, HearingAidAudioRoutingConstants.CALL_ROUTING_ATTRIBUTES);
            setPreferredDeviceRoutingStrategies(i2, audioDeviceAttributes, HearingAidAudioRoutingConstants.MEDIA_ROUTING_ATTRIBUTES);
            setPreferredDeviceRoutingStrategies(i3, audioDeviceAttributes, HearingAidAudioRoutingConstants.RINGTONE_ROUTING_ATTRIBUTES);
            setPreferredDeviceRoutingStrategies(i4, audioDeviceAttributes, HearingAidAudioRoutingConstants.NOTIFICATION_ROUTING_ATTRIBUTES);
        }
    }

    public void onHiSyncIdChanged(long j) {
        CachedBluetoothDevice cachedBluetoothDevice;
        int size = this.mCachedDevices.size() - 1;
        int i = -1;
        while (size >= 0) {
            CachedBluetoothDevice cachedBluetoothDevice2 = (CachedBluetoothDevice) this.mCachedDevices.get(size);
            if (cachedBluetoothDevice2.getHiSyncId() == j && !cachedBluetoothDevice2.getProfiles().stream().anyMatch(new HearingAidDeviceManager$$ExternalSyntheticLambda0(1))) {
                if (i != -1) {
                    if (cachedBluetoothDevice2.isConnected()) {
                        cachedBluetoothDevice = (CachedBluetoothDevice) this.mCachedDevices.get(i);
                        size = i;
                    } else {
                        cachedBluetoothDevice2 = (CachedBluetoothDevice) this.mCachedDevices.get(i);
                        cachedBluetoothDevice = cachedBluetoothDevice2;
                    }
                    cachedBluetoothDevice2.mSubDevice = cachedBluetoothDevice;
                    this.mCachedDevices.remove(size);
                    Log.d("HearingAidDeviceManager", "onHiSyncIdChanged: removed from UI device =" + cachedBluetoothDevice + ", with hiSyncId=" + j);
                    this.mBtManager.mEventManager.dispatchDeviceRemoved(cachedBluetoothDevice);
                    return;
                }
                i = size;
            }
            size--;
        }
    }

    public final boolean onProfileConnectionStateChangedIfProcessed(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        if (i != 0) {
            if (i != 2) {
                return false;
            }
            onHiSyncIdChanged(cachedBluetoothDevice.getHiSyncId());
            CachedBluetoothDevice findMainDevice = findMainDevice(cachedBluetoothDevice);
            if (findMainDevice == null) {
                return false;
            }
            if (findMainDevice.isConnected()) {
                findMainDevice.refresh();
            } else {
                switchDeviceContent(findMainDevice, cachedBluetoothDevice);
            }
            return true;
        }
        if (cachedBluetoothDevice.mUnpairing) {
            return true;
        }
        CachedBluetoothDevice findMainDevice2 = findMainDevice(cachedBluetoothDevice);
        if (findMainDevice2 != null) {
            findMainDevice2.refresh();
            return true;
        }
        CachedBluetoothDevice cachedBluetoothDevice2 = cachedBluetoothDevice.mSubDevice;
        CachedBluetoothDevice cachedBluetoothDevice3 = (cachedBluetoothDevice2 == null || !cachedBluetoothDevice2.isConnected()) ? (CachedBluetoothDevice) cachedBluetoothDevice.mMemberDevices.stream().filter(new HearingAidDeviceManager$$ExternalSyntheticLambda0(2)).findAny().orElse(null) : cachedBluetoothDevice.mSubDevice;
        if (cachedBluetoothDevice3 == null) {
            return false;
        }
        switchDeviceContent(cachedBluetoothDevice, cachedBluetoothDevice3);
        return true;
    }

    public final void setPreferredDeviceRoutingStrategies(int i, AudioDeviceAttributes audioDeviceAttributes, int[] iArr) {
        boolean removePreferredDeviceForStrategies;
        HearingAidAudioRoutingHelper hearingAidAudioRoutingHelper = this.mRoutingHelper;
        hearingAidAudioRoutingHelper.getClass();
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i2 : iArr) {
            arrayList.add(new AudioAttributes.Builder().setUsage(i2).build());
        }
        List<AudioProductStrategy> audioProductStrategies = hearingAidAudioRoutingHelper.getAudioProductStrategies();
        ArrayList arrayList2 = new ArrayList();
        for (AudioProductStrategy audioProductStrategy : audioProductStrategies) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (audioProductStrategy.supportsAudioAttributes((AudioAttributes) it.next())) {
                    arrayList2.add(audioProductStrategy);
                }
            }
        }
        List list = (List) arrayList2.stream().distinct().collect(Collectors.toList());
        if (i != 0) {
            boolean z = true;
            if (i == 1) {
                boolean removePreferredDeviceForStrategies2 = hearingAidAudioRoutingHelper.removePreferredDeviceForStrategies(list);
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    z &= hearingAidAudioRoutingHelper.mAudioManager.setPreferredDeviceForStrategy((AudioProductStrategy) it2.next(), audioDeviceAttributes);
                }
                removePreferredDeviceForStrategies = removePreferredDeviceForStrategies2 & z;
            } else {
                if (i != 2) {
                    throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unexpected routingValue: "));
                }
                boolean removePreferredDeviceForStrategies3 = hearingAidAudioRoutingHelper.removePreferredDeviceForStrategies(list);
                AudioDeviceAttributes audioDeviceAttributes2 = HearingAidAudioRoutingConstants.DEVICE_SPEAKER_OUT;
                Iterator it3 = list.iterator();
                while (it3.hasNext()) {
                    z &= hearingAidAudioRoutingHelper.mAudioManager.setPreferredDeviceForStrategy((AudioProductStrategy) it3.next(), audioDeviceAttributes2);
                }
                removePreferredDeviceForStrategies = removePreferredDeviceForStrategies3 & z;
            }
        } else {
            removePreferredDeviceForStrategies = hearingAidAudioRoutingHelper.removePreferredDeviceForStrategies(list);
        }
        if (removePreferredDeviceForStrategies) {
            return;
        }
        Log.w("HearingAidDeviceManager", "routingStrategies: " + list.toString() + "routingValue: " + i + " fail to configure AudioProductStrategy");
    }

    public final void switchDeviceContent(CachedBluetoothDevice cachedBluetoothDevice, CachedBluetoothDevice cachedBluetoothDevice2) {
        LocalBluetoothManager localBluetoothManager = this.mBtManager;
        localBluetoothManager.mEventManager.dispatchDeviceRemoved(cachedBluetoothDevice);
        CachedBluetoothDevice cachedBluetoothDevice3 = cachedBluetoothDevice.mSubDevice;
        if (cachedBluetoothDevice3 == null || !cachedBluetoothDevice3.equals(cachedBluetoothDevice2)) {
            cachedBluetoothDevice.switchMemberDeviceContent(cachedBluetoothDevice2);
        } else {
            BluetoothDevice bluetoothDevice = cachedBluetoothDevice.mDevice;
            short s = cachedBluetoothDevice.mRssi;
            boolean z = cachedBluetoothDevice.mJustDiscovered;
            HearingAidInfo hearingAidInfo = cachedBluetoothDevice.mHearingAidInfo;
            cachedBluetoothDevice.release();
            CachedBluetoothDevice cachedBluetoothDevice4 = cachedBluetoothDevice.mSubDevice;
            cachedBluetoothDevice.mDevice = cachedBluetoothDevice4.mDevice;
            cachedBluetoothDevice.mRssi = cachedBluetoothDevice4.mRssi;
            cachedBluetoothDevice.mJustDiscovered = cachedBluetoothDevice4.mJustDiscovered;
            cachedBluetoothDevice.mHearingAidInfo = cachedBluetoothDevice4.mHearingAidInfo;
            cachedBluetoothDevice4.release();
            CachedBluetoothDevice cachedBluetoothDevice5 = cachedBluetoothDevice.mSubDevice;
            cachedBluetoothDevice5.mDevice = bluetoothDevice;
            cachedBluetoothDevice5.mRssi = s;
            cachedBluetoothDevice5.mJustDiscovered = z;
            cachedBluetoothDevice5.mHearingAidInfo = hearingAidInfo;
            cachedBluetoothDevice.fetchActiveDevices();
        }
        cachedBluetoothDevice.refresh();
        localBluetoothManager.mEventManager.dispatchDeviceAdded(cachedBluetoothDevice);
    }

    public final void syncDeviceIfNeeded(CachedBluetoothDevice cachedBluetoothDevice) {
        CachedBluetoothDevice findMainDevice;
        int activePresetIndex;
        HapClientProfile hapClientProfile = this.mBtManager.mProfileManager.mHapClientProfile;
        if (hapClientProfile != null) {
            BluetoothDevice bluetoothDevice = cachedBluetoothDevice.mDevice;
            BluetoothHapClient bluetoothHapClient = hapClientProfile.mService;
            int i = 0;
            if ((bluetoothHapClient == null ? false : bluetoothHapClient.supportsSynchronizedPresets(bluetoothDevice)) || (findMainDevice = findMainDevice(cachedBluetoothDevice)) == null) {
                return;
            }
            BluetoothDevice bluetoothDevice2 = findMainDevice.mDevice;
            BluetoothHapClient bluetoothHapClient2 = hapClientProfile.mService;
            if (bluetoothHapClient2 == null) {
                Log.w("HapClientProfile", "Proxy not attached to service. Cannot get active preset index.");
                activePresetIndex = 0;
            } else {
                activePresetIndex = bluetoothHapClient2.getActivePresetIndex(bluetoothDevice2);
            }
            BluetoothDevice bluetoothDevice3 = cachedBluetoothDevice.mDevice;
            BluetoothHapClient bluetoothHapClient3 = hapClientProfile.mService;
            if (bluetoothHapClient3 == null) {
                Log.w("HapClientProfile", "Proxy not attached to service. Cannot get active preset index.");
            } else {
                i = bluetoothHapClient3.getActivePresetIndex(bluetoothDevice3);
            }
            if (activePresetIndex == 0 || activePresetIndex == i) {
                return;
            }
            StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i, activePresetIndex, "syncing preset from ", "->", ", device=");
            m.append(cachedBluetoothDevice);
            Log.d("HearingAidDeviceManager", m.toString());
            hapClientProfile.selectPreset(cachedBluetoothDevice.mDevice, activePresetIndex);
        }
    }

    public final void updateHearingAidsDevices() {
        HearingAidInfo generateHearingAidInfo;
        HashSet hashSet = new HashSet();
        for (CachedBluetoothDevice cachedBluetoothDevice : this.mCachedDevices) {
            if (!isValidHiSyncId(cachedBluetoothDevice.getHiSyncId()) && (generateHearingAidInfo = generateHearingAidInfo(cachedBluetoothDevice)) != null) {
                cachedBluetoothDevice.mHearingAidInfo = generateHearingAidInfo;
                cachedBluetoothDevice.dispatchAttributesChanged();
                long j = generateHearingAidInfo.mHiSyncId;
                if (isValidHiSyncId(j)) {
                    hashSet.add(Long.valueOf(j));
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            onHiSyncIdChanged(((Long) it.next()).longValue());
        }
    }

    public HearingAidDeviceManager(Context context, LocalBluetoothManager localBluetoothManager, List list, HearingAidAudioRoutingHelper hearingAidAudioRoutingHelper) {
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mBtManager = localBluetoothManager;
        this.mCachedDevices = list;
        this.mRoutingHelper = hearingAidAudioRoutingHelper;
    }
}

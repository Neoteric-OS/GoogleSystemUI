package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothLeAudio;
import android.bluetooth.BluetoothLeAudioContentMetadata;
import android.bluetooth.BluetoothLeBroadcast;
import android.bluetooth.BluetoothLeBroadcastAssistant;
import android.bluetooth.BluetoothLeBroadcastMetadata;
import android.bluetooth.BluetoothLeBroadcastReceiveState;
import android.bluetooth.BluetoothLeBroadcastSubgroup;
import android.bluetooth.BluetoothProfile;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import androidx.fragment.app.FragmentManagerViewModel$$ExternalSyntheticOutline0;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LocalBluetoothLeBroadcast implements LocalBluetoothProfile {
    public static final Uri[] SETTINGS_URIS = {Settings.Secure.getUriFor("bluetooth_le_broadcast_name"), Settings.Secure.getUriFor("bluetooth_le_broadcast_program_info"), Settings.Secure.getUriFor("bluetooth_le_broadcast_code"), Settings.Secure.getUriFor("bluetooth_le_broadcast_app_source_name"), Settings.Secure.getUriFor("bluetooth_le_broadcast_improve_compatibility")};
    public BluetoothLeBroadcastMetadata mBluetoothLeBroadcastMetadata;
    public final AnonymousClass3 mBroadcastAssistantCallback;
    public final AnonymousClass2 mBroadcastCallback;
    public byte[] mBroadcastCode;
    public String mBroadcastName;
    public final BluetoothLeAudioContentMetadata.Builder mBuilder;
    public final ContentResolver mContentResolver;
    public final Context mContext;
    public final CachedBluetoothDeviceManager mDeviceManager;
    public final Executor mExecutor;
    public String mProgramInfo;
    public BluetoothLeBroadcast mServiceBroadcast;
    public BluetoothLeBroadcastAssistant mServiceBroadcastAssistant;
    public final AnonymousClass1 mServiceListener;
    public final BroadcastSettingsObserver mSettingsObserver;
    public int mBroadcastId = -1;
    public String mAppSourceName = "";
    public String mNewAppSourceName = "";
    public boolean mIsBroadcastProfileReady = false;
    public boolean mIsBroadcastAssistantProfileReady = false;
    public boolean mImproveCompatibility = false;
    public final Map mCachedBroadcastCallbackExecutorMap = new ConcurrentHashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$1, reason: invalid class name */
    public final class AnonymousClass1 implements BluetoothProfile.ServiceListener {
        public AnonymousClass1() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public final void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            List allBroadcastMetadata;
            ExifInterface$$ExternalSyntheticOutline0.m("Bluetooth service connected: ", "LocalBluetoothLeBroadcast", i);
            if (i == 26) {
                LocalBluetoothLeBroadcast localBluetoothLeBroadcast = LocalBluetoothLeBroadcast.this;
                if (!localBluetoothLeBroadcast.mIsBroadcastProfileReady) {
                    localBluetoothLeBroadcast.mServiceBroadcast = (BluetoothLeBroadcast) bluetoothProfile;
                    localBluetoothLeBroadcast.mIsBroadcastProfileReady = true;
                    localBluetoothLeBroadcast.registerServiceCallBack(localBluetoothLeBroadcast.mExecutor, localBluetoothLeBroadcast.mBroadcastCallback);
                    BluetoothLeBroadcast bluetoothLeBroadcast = LocalBluetoothLeBroadcast.this.mServiceBroadcast;
                    if (bluetoothLeBroadcast == null) {
                        Log.d("LocalBluetoothLeBroadcast", "The BluetoothLeBroadcast is null.");
                        allBroadcastMetadata = Collections.emptyList();
                    } else {
                        allBroadcastMetadata = bluetoothLeBroadcast.getAllBroadcastMetadata();
                    }
                    if (!allBroadcastMetadata.isEmpty()) {
                        LocalBluetoothLeBroadcast.this.updateBroadcastInfoFromBroadcastMetadata((BluetoothLeBroadcastMetadata) allBroadcastMetadata.get(0));
                    }
                    LocalBluetoothLeBroadcast localBluetoothLeBroadcast2 = LocalBluetoothLeBroadcast.this;
                    if (localBluetoothLeBroadcast2.mContentResolver == null) {
                        Log.d("LocalBluetoothLeBroadcast", "mContentResolver is null");
                    } else {
                        for (Uri uri : LocalBluetoothLeBroadcast.SETTINGS_URIS) {
                            localBluetoothLeBroadcast2.mContentResolver.registerContentObserver(uri, false, localBluetoothLeBroadcast2.mSettingsObserver);
                        }
                    }
                    Log.d("LocalBluetoothLeBroadcast", "onServiceConnected: register mCachedBroadcastCallbackExecutorMap = " + LocalBluetoothLeBroadcast.this.mCachedBroadcastCallbackExecutorMap);
                    ((ConcurrentHashMap) LocalBluetoothLeBroadcast.this.mCachedBroadcastCallbackExecutorMap).forEach(new BiConsumer() { // from class: com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$1$$ExternalSyntheticLambda0
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            LocalBluetoothLeBroadcast localBluetoothLeBroadcast3 = LocalBluetoothLeBroadcast.this;
                            localBluetoothLeBroadcast3.registerServiceCallBack((Executor) obj2, (BluetoothLeBroadcast.Callback) obj);
                        }
                    });
                    return;
                }
            }
            if (i == 29) {
                LocalBluetoothLeBroadcast localBluetoothLeBroadcast3 = LocalBluetoothLeBroadcast.this;
                if (localBluetoothLeBroadcast3.mIsBroadcastAssistantProfileReady) {
                    return;
                }
                localBluetoothLeBroadcast3.mIsBroadcastAssistantProfileReady = true;
                BluetoothLeBroadcastAssistant bluetoothLeBroadcastAssistant = (BluetoothLeBroadcastAssistant) bluetoothProfile;
                localBluetoothLeBroadcast3.mServiceBroadcastAssistant = bluetoothLeBroadcastAssistant;
                Executor executor = localBluetoothLeBroadcast3.mExecutor;
                if (bluetoothLeBroadcastAssistant == null) {
                    Log.d("LocalBluetoothLeBroadcast", "registerBroadcastAssistantCallback failed, the BluetoothLeBroadcastAssistant is null.");
                } else {
                    bluetoothLeBroadcastAssistant.registerCallback(executor, localBluetoothLeBroadcast3.mBroadcastAssistantCallback);
                }
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public final void onServiceDisconnected(int i) {
            ExifInterface$$ExternalSyntheticOutline0.m("Bluetooth service disconnected: ", "LocalBluetoothLeBroadcast", i);
            if (i == 26) {
                LocalBluetoothLeBroadcast localBluetoothLeBroadcast = LocalBluetoothLeBroadcast.this;
                if (localBluetoothLeBroadcast.mIsBroadcastProfileReady) {
                    localBluetoothLeBroadcast.mIsBroadcastProfileReady = false;
                    LocalBluetoothLeBroadcast.m757$$Nest$mnotifyBroadcastStateChange(localBluetoothLeBroadcast, 2);
                    LocalBluetoothLeBroadcast localBluetoothLeBroadcast2 = LocalBluetoothLeBroadcast.this;
                    localBluetoothLeBroadcast2.unregisterServiceCallBack(localBluetoothLeBroadcast2.mBroadcastCallback);
                    LocalBluetoothLeBroadcast.this.mCachedBroadcastCallbackExecutorMap.clear();
                }
            }
            if (i == 29) {
                LocalBluetoothLeBroadcast localBluetoothLeBroadcast3 = LocalBluetoothLeBroadcast.this;
                if (localBluetoothLeBroadcast3.mIsBroadcastAssistantProfileReady) {
                    localBluetoothLeBroadcast3.mIsBroadcastAssistantProfileReady = false;
                    BluetoothLeBroadcastAssistant bluetoothLeBroadcastAssistant = localBluetoothLeBroadcast3.mServiceBroadcastAssistant;
                    if (bluetoothLeBroadcastAssistant == null) {
                        Log.d("LocalBluetoothLeBroadcast", "unregisterBroadcastAssistantCallback, the BluetoothLeBroadcastAssistant is null.");
                    } else {
                        bluetoothLeBroadcastAssistant.unregisterCallback(localBluetoothLeBroadcast3.mBroadcastAssistantCallback);
                    }
                }
            }
            LocalBluetoothLeBroadcast localBluetoothLeBroadcast4 = LocalBluetoothLeBroadcast.this;
            if (localBluetoothLeBroadcast4.mIsBroadcastAssistantProfileReady || localBluetoothLeBroadcast4.mIsBroadcastProfileReady) {
                return;
            }
            ContentResolver contentResolver = localBluetoothLeBroadcast4.mContentResolver;
            if (contentResolver == null) {
                Log.d("LocalBluetoothLeBroadcast", "mContentResolver is null");
            } else {
                contentResolver.unregisterContentObserver(localBluetoothLeBroadcast4.mSettingsObserver);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BroadcastSettingsObserver extends ContentObserver {
        public BroadcastSettingsObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            Log.d("LocalBluetoothLeBroadcast", "BroadcastSettingsObserver: onChange");
            LocalBluetoothLeBroadcast.this.updateBroadcastInfoFromContentProvider();
        }
    }

    /* renamed from: -$$Nest$mnotifyBroadcastStateChange, reason: not valid java name */
    public static void m757$$Nest$mnotifyBroadcastStateChange(LocalBluetoothLeBroadcast localBluetoothLeBroadcast, int i) {
        if (!localBluetoothLeBroadcast.mContext.getPackageName().equals("com.android.settings")) {
            Log.d("LocalBluetoothLeBroadcast", "Skip notifyBroadcastStateChange, not triggered by Settings.");
            return;
        }
        UserManager userManager = (UserManager) localBluetoothLeBroadcast.mContext.getSystemService(UserManager.class);
        if (userManager != null && userManager.isManagedProfile()) {
            Log.d("LocalBluetoothLeBroadcast", "Skip notifyBroadcastStateChange, not triggered for work profile.");
            return;
        }
        Intent intent = new Intent("com.android.settings.action.BLUETOOTH_LE_AUDIO_SHARING_STATE_CHANGE");
        intent.putExtra("BLUETOOTH_LE_AUDIO_SHARING_STATE", i);
        intent.setPackage(localBluetoothLeBroadcast.mContext.getPackageName());
        Log.d("LocalBluetoothLeBroadcast", "notifyBroadcastStateChange for state = " + i);
        localBluetoothLeBroadcast.mContext.sendBroadcast(intent);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$2] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3] */
    public LocalBluetoothLeBroadcast(Context context, CachedBluetoothDeviceManager cachedBluetoothDeviceManager) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.mBroadcastCallback = new BluetoothLeBroadcast.Callback() { // from class: com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast.2
            public final void onBroadcastMetadataChanged(int i, BluetoothLeBroadcastMetadata bluetoothLeBroadcastMetadata) {
                ExifInterface$$ExternalSyntheticOutline0.m("onBroadcastMetadataChanged(), broadcastId = ", "LocalBluetoothLeBroadcast", i);
                LocalBluetoothLeBroadcast localBluetoothLeBroadcast = LocalBluetoothLeBroadcast.this;
                localBluetoothLeBroadcast.getClass();
                if (bluetoothLeBroadcastMetadata != null && bluetoothLeBroadcastMetadata.getBroadcastId() == localBluetoothLeBroadcast.mBroadcastId) {
                    localBluetoothLeBroadcast.mBluetoothLeBroadcastMetadata = bluetoothLeBroadcastMetadata;
                    localBluetoothLeBroadcast.updateBroadcastInfoFromBroadcastMetadata(bluetoothLeBroadcastMetadata);
                }
                LocalBluetoothLeBroadcast.m757$$Nest$mnotifyBroadcastStateChange(LocalBluetoothLeBroadcast.this, 1);
            }

            public final void onBroadcastStartFailed(int i) {
                ExifInterface$$ExternalSyntheticOutline0.m("onBroadcastStartFailed(), reason = ", "LocalBluetoothLeBroadcast", i);
            }

            public final void onBroadcastStarted(int i, int i2) {
                Log.d("LocalBluetoothLeBroadcast", "onBroadcastStarted(), reason = " + i + ", broadcastId = " + i2);
                LocalBluetoothLeBroadcast.this.setLatestBroadcastId(i2);
                LocalBluetoothLeBroadcast localBluetoothLeBroadcast = LocalBluetoothLeBroadcast.this;
                localBluetoothLeBroadcast.setAppSourceName(localBluetoothLeBroadcast.mNewAppSourceName, true);
            }

            public final void onBroadcastStopFailed(int i) {
                ExifInterface$$ExternalSyntheticOutline0.m("onBroadcastStopFailed(), reason = ", "LocalBluetoothLeBroadcast", i);
            }

            public final void onBroadcastStopped(int i, int i2) {
                Log.d("LocalBluetoothLeBroadcast", "onBroadcastStopped(), reason = " + i + ", broadcastId = " + i2);
                LocalBluetoothLeBroadcast.m757$$Nest$mnotifyBroadcastStateChange(LocalBluetoothLeBroadcast.this, 2);
                LocalBluetoothLeBroadcast localBluetoothLeBroadcast = LocalBluetoothLeBroadcast.this;
                localBluetoothLeBroadcast.getClass();
                Log.d("LocalBluetoothLeBroadcast", "stopLocalSourceReceivers()");
                for (BluetoothDevice bluetoothDevice : localBluetoothLeBroadcast.mServiceBroadcastAssistant.getConnectedDevices()) {
                    for (BluetoothLeBroadcastReceiveState bluetoothLeBroadcastReceiveState : localBluetoothLeBroadcast.mServiceBroadcastAssistant.getAllSources(bluetoothDevice)) {
                        if (bluetoothLeBroadcastReceiveState.getBroadcastId() == localBluetoothLeBroadcast.mBroadcastId) {
                            localBluetoothLeBroadcast.mServiceBroadcastAssistant.removeSource(bluetoothDevice, bluetoothLeBroadcastReceiveState.getSourceId());
                        }
                    }
                }
                LocalBluetoothLeBroadcast localBluetoothLeBroadcast2 = LocalBluetoothLeBroadcast.this;
                localBluetoothLeBroadcast2.getClass();
                Log.d("LocalBluetoothLeBroadcast", "resetCacheInfo:");
                localBluetoothLeBroadcast2.setAppSourceName("", true);
                localBluetoothLeBroadcast2.mBluetoothLeBroadcastMetadata = null;
                localBluetoothLeBroadcast2.mBroadcastId = -1;
            }

            public final void onBroadcastUpdateFailed(int i, int i2) {
                Log.d("LocalBluetoothLeBroadcast", "onBroadcastUpdateFailed(), reason = " + i + ", broadcastId = " + i2);
            }

            public final void onBroadcastUpdated(int i, int i2) {
                Log.d("LocalBluetoothLeBroadcast", "onBroadcastUpdated(), reason = " + i + ", broadcastId = " + i2);
                LocalBluetoothLeBroadcast.this.setLatestBroadcastId(i2);
                LocalBluetoothLeBroadcast localBluetoothLeBroadcast = LocalBluetoothLeBroadcast.this;
                localBluetoothLeBroadcast.setAppSourceName(localBluetoothLeBroadcast.mNewAppSourceName, true);
            }

            public final void onPlaybackStarted(int i, int i2) {
            }

            public final void onPlaybackStopped(int i, int i2) {
            }
        };
        this.mBroadcastAssistantCallback = new BluetoothLeBroadcastAssistant.Callback() { // from class: com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast.3
            public final void onReceiveStateChanged(BluetoothDevice bluetoothDevice, int i, BluetoothLeBroadcastReceiveState bluetoothLeBroadcastReceiveState) {
                int indexOf;
                Log.d("LocalBluetoothLeBroadcast", "onReceiveStateChanged(), sink = " + bluetoothDevice + ", sourceId = " + i + ", state = " + bluetoothLeBroadcastReceiveState);
                if (BluetoothUtils.isConnected(bluetoothLeBroadcastReceiveState)) {
                    LocalBluetoothLeBroadcast localBluetoothLeBroadcast = LocalBluetoothLeBroadcast.this;
                    BluetoothLeBroadcast bluetoothLeBroadcast = localBluetoothLeBroadcast.mServiceBroadcast;
                    if (bluetoothLeBroadcast == null) {
                        Log.d("LocalBluetoothLeBroadcast", "Skip updateFallbackActiveDeviceIfNeeded due to broadcast profile is null");
                        return;
                    }
                    if (bluetoothLeBroadcast.getAllBroadcastMetadata().stream().noneMatch(new LocalBluetoothLeBroadcast$$ExternalSyntheticLambda0(localBluetoothLeBroadcast, 0))) {
                        Log.d("LocalBluetoothLeBroadcast", "Skip updateFallbackActiveDeviceIfNeeded due to no broadcast ongoing");
                        return;
                    }
                    BluetoothLeBroadcastAssistant bluetoothLeBroadcastAssistant = localBluetoothLeBroadcast.mServiceBroadcastAssistant;
                    if (bluetoothLeBroadcastAssistant == null) {
                        Log.d("LocalBluetoothLeBroadcast", "Skip updateFallbackActiveDeviceIfNeeded due to assistant profile is null");
                        return;
                    }
                    List<BluetoothDevice> list = (List) bluetoothLeBroadcastAssistant.getConnectedDevices().stream().filter(new LocalBluetoothLeBroadcast$$ExternalSyntheticLambda0(localBluetoothLeBroadcast, 1)).collect(Collectors.toList());
                    if (list.isEmpty()) {
                        Log.d("LocalBluetoothLeBroadcast", "Skip updateFallbackActiveDeviceIfNeeded due to no sinks in broadcast");
                        return;
                    }
                    List mostRecentlyConnectedDevices = BluetoothAdapter.getDefaultAdapter().getMostRecentlyConnectedDevices();
                    int i2 = -1;
                    BluetoothDevice bluetoothDevice2 = null;
                    int i3 = -1;
                    for (BluetoothDevice bluetoothDevice3 : list) {
                        if (mostRecentlyConnectedDevices.contains(bluetoothDevice3) && (indexOf = mostRecentlyConnectedDevices.indexOf(bluetoothDevice3)) > i3) {
                            bluetoothDevice2 = bluetoothDevice3;
                            i3 = indexOf;
                        }
                    }
                    if (bluetoothDevice2 == null) {
                        Log.d("LocalBluetoothLeBroadcast", "Skip updateFallbackActiveDeviceIfNeeded, target is null");
                        return;
                    }
                    Log.d("LocalBluetoothLeBroadcast", "updateFallbackActiveDeviceIfNeeded, set active device: " + bluetoothDevice2.getAnonymizedAddress());
                    CachedBluetoothDevice findDevice = localBluetoothLeBroadcast.mDeviceManager.findDevice(bluetoothDevice2);
                    if (findDevice == null) {
                        Log.d("LocalBluetoothLeBroadcast", "Skip updateFallbackActiveDeviceIfNeeded, fail to find cached bt device");
                        return;
                    }
                    int i4 = Settings.Secure.getInt(localBluetoothLeBroadcast.mContext.getContentResolver(), "bluetooth_le_broadcast_fallback_active_group_id", -1);
                    if (i4 != -1) {
                        int i5 = findDevice.mGroupId;
                        String anonymizedAddress = findDevice.mDevice.getAnonymizedAddress();
                        if (i5 == -1) {
                            Iterator it = findDevice.getProfiles().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    FragmentManagerViewModel$$ExternalSyntheticOutline0.m("getGroupId return invalid id for device: ", anonymizedAddress, "LocalBluetoothLeBroadcast");
                                    break;
                                }
                                LocalBluetoothProfile localBluetoothProfile = (LocalBluetoothProfile) it.next();
                                if (localBluetoothProfile instanceof LeAudioProfile) {
                                    FragmentManagerViewModel$$ExternalSyntheticOutline0.m("getGroupId by LEA profile for device: ", anonymizedAddress, "LocalBluetoothLeBroadcast");
                                    BluetoothDevice bluetoothDevice4 = findDevice.mDevice;
                                    BluetoothLeAudio bluetoothLeAudio = ((LeAudioProfile) localBluetoothProfile).mService;
                                    if (bluetoothLeAudio != null) {
                                        i2 = bluetoothLeAudio.getGroupId(bluetoothDevice4);
                                    }
                                }
                            }
                        } else {
                            FragmentManagerViewModel$$ExternalSyntheticOutline0.m("getGroupId by CSIP profile for device: ", anonymizedAddress, "LocalBluetoothLeBroadcast");
                            i2 = i5;
                        }
                        if (i2 == i4) {
                            ExifInterface$$ExternalSyntheticOutline0.m("Skip updateFallbackActiveDeviceIfNeeded, already is fallback: ", "LocalBluetoothLeBroadcast", i4);
                            return;
                        }
                    }
                    findDevice.setActive();
                }
            }

            public final void onSourceAddFailed(BluetoothDevice bluetoothDevice, BluetoothLeBroadcastMetadata bluetoothLeBroadcastMetadata, int i) {
                Log.d("LocalBluetoothLeBroadcast", "onSourceAddFailed(), sink = " + bluetoothDevice + ", reason = " + i + ", source = " + bluetoothLeBroadcastMetadata);
            }

            public final void onSourceAdded(BluetoothDevice bluetoothDevice, int i, int i2) {
                StringBuilder sb = new StringBuilder("onSourceAdded(), sink = ");
                sb.append(bluetoothDevice);
                sb.append(", reason = ");
                sb.append(i2);
                sb.append(", sourceId = ");
                LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, i, "LocalBluetoothLeBroadcast");
            }

            public final void onSourceRemoveFailed(BluetoothDevice bluetoothDevice, int i, int i2) {
                StringBuilder sb = new StringBuilder("onSourceRemoveFailed(), sink = ");
                sb.append(bluetoothDevice);
                sb.append(", reason = ");
                sb.append(i2);
                sb.append(", sourceId = ");
                LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, i, "LocalBluetoothLeBroadcast");
            }

            public final void onSourceRemoved(BluetoothDevice bluetoothDevice, int i, int i2) {
                StringBuilder sb = new StringBuilder("onSourceRemoved(), sink = ");
                sb.append(bluetoothDevice);
                sb.append(", reason = ");
                sb.append(i2);
                sb.append(", sourceId = ");
                LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(sb, i, "LocalBluetoothLeBroadcast");
            }

            public final void onSearchStartFailed(int i) {
            }

            public final void onSearchStarted(int i) {
            }

            public final void onSearchStopFailed(int i) {
            }

            public final void onSearchStopped(int i) {
            }

            public final void onSourceFound(BluetoothLeBroadcastMetadata bluetoothLeBroadcastMetadata) {
            }

            public final void onSourceModified(BluetoothDevice bluetoothDevice, int i, int i2) {
            }

            public final void onSourceModifyFailed(BluetoothDevice bluetoothDevice, int i, int i2) {
            }
        };
        this.mContext = context;
        this.mDeviceManager = cachedBluetoothDeviceManager;
        this.mExecutor = Executors.newSingleThreadExecutor();
        this.mBuilder = new BluetoothLeAudioContentMetadata.Builder();
        this.mContentResolver = context.getContentResolver();
        this.mSettingsObserver = new BroadcastSettingsObserver(new Handler(Looper.getMainLooper()));
        updateBroadcastInfoFromContentProvider();
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(context, anonymousClass1, 26);
        BluetoothAdapter.getDefaultAdapter().getProfileProxy(context, anonymousClass1, 29);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final boolean accessProfileEnabled() {
        return false;
    }

    public final void finalize() {
        Log.d("LocalBluetoothLeBroadcast", "finalize()");
        if (this.mServiceBroadcast != null) {
            try {
                BluetoothAdapter.getDefaultAdapter().closeProfileProxy(26, this.mServiceBroadcast);
                this.mServiceBroadcast = null;
            } catch (Throwable th) {
                Log.w("LocalBluetoothLeBroadcast", "Error cleaning up LeAudio proxy", th);
            }
        }
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final int getConnectionPolicy(BluetoothDevice bluetoothDevice) {
        return 0;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final int getConnectionStatus(BluetoothDevice bluetoothDevice) {
        BluetoothLeBroadcast bluetoothLeBroadcast = this.mServiceBroadcast;
        if (bluetoothLeBroadcast == null) {
            return 0;
        }
        return bluetoothLeBroadcast.getConnectionState(bluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final int getDrawableResource(BluetoothClass bluetoothClass) {
        return 0;
    }

    public final BluetoothLeBroadcastMetadata getLatestBluetoothLeBroadcastMetadata() {
        BluetoothLeBroadcast bluetoothLeBroadcast = this.mServiceBroadcast;
        if (bluetoothLeBroadcast == null) {
            Log.d("LocalBluetoothLeBroadcast", "The BluetoothLeBroadcast is null");
            return null;
        }
        if (this.mBluetoothLeBroadcastMetadata == null) {
            this.mBluetoothLeBroadcastMetadata = (BluetoothLeBroadcastMetadata) bluetoothLeBroadcast.getAllBroadcastMetadata().stream().filter(new LocalBluetoothLeBroadcast$$ExternalSyntheticLambda0(this, 2)).findFirst().orElse(null);
        }
        return this.mBluetoothLeBroadcastMetadata;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final int getProfileId() {
        return 26;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final boolean isEnabled(BluetoothDevice bluetoothDevice) {
        if (this.mServiceBroadcast == null) {
            return false;
        }
        return !r0.getAllBroadcastMetadata().isEmpty();
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public final boolean isProfileReady() {
        return this.mIsBroadcastProfileReady;
    }

    public final void registerServiceCallBack(Executor executor, BluetoothLeBroadcast.Callback callback) {
        BluetoothLeBroadcast bluetoothLeBroadcast = this.mServiceBroadcast;
        if (bluetoothLeBroadcast == null) {
            Log.d("LocalBluetoothLeBroadcast", "registerServiceCallBack failed, the BluetoothLeBroadcast is null.");
            ((ConcurrentHashMap) this.mCachedBroadcastCallbackExecutorMap).putIfAbsent(callback, executor);
            return;
        }
        try {
            bluetoothLeBroadcast.registerCallback(executor, callback);
        } catch (IllegalArgumentException e) {
            Log.w("LocalBluetoothLeBroadcast", "registerServiceCallBack failed. " + e.getMessage());
        }
    }

    public final void setAppSourceName(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        String str2 = this.mAppSourceName;
        if (str2 != null && TextUtils.equals(str2, str)) {
            Log.d("LocalBluetoothLeBroadcast", "setAppSourceName: appSourceName is not changed");
            return;
        }
        this.mAppSourceName = str;
        this.mNewAppSourceName = "";
        if (z) {
            ContentResolver contentResolver = this.mContentResolver;
            if (contentResolver == null) {
                Log.d("LocalBluetoothLeBroadcast", "mContentResolver is null");
            } else {
                Settings.Secure.putString(contentResolver, "bluetooth_le_broadcast_app_source_name", str);
            }
        }
    }

    public final void setBroadcastCode(boolean z, byte[] bArr) {
        if (bArr == null) {
            Log.d("LocalBluetoothLeBroadcast", "setBroadcastCode: broadcastCode is null");
            return;
        }
        byte[] bArr2 = this.mBroadcastCode;
        if (bArr2 != null && Arrays.equals(bArr, bArr2)) {
            Log.d("LocalBluetoothLeBroadcast", "setBroadcastCode: broadcastCode is not changed");
            return;
        }
        this.mBroadcastCode = bArr;
        if (z) {
            ContentResolver contentResolver = this.mContentResolver;
            if (contentResolver == null) {
                Log.d("LocalBluetoothLeBroadcast", "mContentResolver is null");
            } else {
                Settings.Secure.putString(contentResolver, "bluetooth_le_broadcast_code", new String(bArr, StandardCharsets.UTF_8));
            }
        }
    }

    public final void setBroadcastName(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            Log.d("LocalBluetoothLeBroadcast", "setBroadcastName: broadcastName is null or empty");
            return;
        }
        String str2 = this.mBroadcastName;
        if (str2 != null && TextUtils.equals(str2, str)) {
            Log.d("LocalBluetoothLeBroadcast", "setBroadcastName: broadcastName is not changed");
            return;
        }
        FragmentManagerViewModel$$ExternalSyntheticOutline0.m("setBroadcastName: ", str, "LocalBluetoothLeBroadcast");
        this.mBroadcastName = str;
        if (z) {
            ContentResolver contentResolver = this.mContentResolver;
            if (contentResolver == null) {
                Log.d("LocalBluetoothLeBroadcast", "mContentResolver is null");
            } else {
                Settings.Secure.putString(contentResolver, "bluetooth_le_broadcast_name", str);
            }
        }
    }

    public final void setLatestBroadcastId(int i) {
        ExifInterface$$ExternalSyntheticOutline0.m("setLatestBroadcastId: mBroadcastId is ", "LocalBluetoothLeBroadcast", i);
        this.mBroadcastId = i;
    }

    public final void setProgramInfo(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            Log.d("LocalBluetoothLeBroadcast", "setProgramInfo: programInfo is null or empty");
            return;
        }
        String str2 = this.mProgramInfo;
        if (str2 != null && TextUtils.equals(str2, str)) {
            Log.d("LocalBluetoothLeBroadcast", "setProgramInfo: programInfo is not changed");
            return;
        }
        FragmentManagerViewModel$$ExternalSyntheticOutline0.m("setProgramInfo: ", str, "LocalBluetoothLeBroadcast");
        this.mProgramInfo = str;
        if (z) {
            ContentResolver contentResolver = this.mContentResolver;
            if (contentResolver == null) {
                Log.d("LocalBluetoothLeBroadcast", "mContentResolver is null");
            } else {
                Settings.Secure.putString(contentResolver, "bluetooth_le_broadcast_program_info", str);
            }
        }
    }

    public final void startBroadcast(String str) {
        this.mNewAppSourceName = str;
        if (this.mServiceBroadcast == null) {
            Log.d("LocalBluetoothLeBroadcast", "The BluetoothLeBroadcast is null when starting the broadcast.");
            return;
        }
        String str2 = this.mProgramInfo;
        FragmentManagerViewModel$$ExternalSyntheticOutline0.m("startBroadcast: language = null ,programInfo = ", str2, "LocalBluetoothLeBroadcast");
        byte[] bArr = null;
        BluetoothLeAudioContentMetadata build = this.mBuilder.setLanguage((String) null).setProgramInfo(str2).build();
        BluetoothLeBroadcast bluetoothLeBroadcast = this.mServiceBroadcast;
        byte[] bArr2 = this.mBroadcastCode;
        if (bArr2 != null && bArr2.length > 0) {
            bArr = bArr2;
        }
        bluetoothLeBroadcast.startBroadcast(build, bArr);
    }

    public final String toString() {
        return "LE_AUDIO_BROADCAST";
    }

    public final void unregisterServiceCallBack(BluetoothLeBroadcast.Callback callback) {
        this.mCachedBroadcastCallbackExecutorMap.remove(callback);
        BluetoothLeBroadcast bluetoothLeBroadcast = this.mServiceBroadcast;
        if (bluetoothLeBroadcast == null) {
            Log.d("LocalBluetoothLeBroadcast", "unregisterServiceCallBack failed, the BluetoothLeBroadcast is null.");
            return;
        }
        try {
            bluetoothLeBroadcast.unregisterCallback(callback);
        } catch (IllegalArgumentException e) {
            Log.w("LocalBluetoothLeBroadcast", "unregisterServiceCallBack failed. " + e.getMessage());
        }
    }

    public final void updateBroadcastInfoFromBroadcastMetadata(BluetoothLeBroadcastMetadata bluetoothLeBroadcastMetadata) {
        if (bluetoothLeBroadcastMetadata == null) {
            Log.d("LocalBluetoothLeBroadcast", "The bluetoothLeBroadcastMetadata is null");
            return;
        }
        setBroadcastName(bluetoothLeBroadcastMetadata.getBroadcastName(), true);
        setBroadcastCode(true, bluetoothLeBroadcastMetadata.getBroadcastCode());
        setLatestBroadcastId(bluetoothLeBroadcastMetadata.getBroadcastId());
        List subgroups = bluetoothLeBroadcastMetadata.getSubgroups();
        if (subgroups == null || subgroups.size() < 1) {
            Log.d("LocalBluetoothLeBroadcast", "The subgroup is not valid value");
        } else {
            setProgramInfo(((BluetoothLeBroadcastSubgroup) subgroups.get(0)).getContentMetadata().getProgramInfo(), true);
            setAppSourceName(this.mAppSourceName, true);
        }
    }

    public final void updateBroadcastInfoFromContentProvider() {
        byte[] bytes;
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver == null) {
            Log.d("LocalBluetoothLeBroadcast", "updateBroadcastInfoFromContentProvider: mContentResolver is null");
            return;
        }
        String string = Settings.Secure.getString(contentResolver, "bluetooth_le_broadcast_program_info");
        if (string == null) {
            string = BluetoothAdapter.getDefaultAdapter().getName() + "_" + ThreadLocalRandom.current().nextInt(1000, 9999);
        }
        setProgramInfo(string, false);
        String string2 = Settings.Secure.getString(this.mContentResolver, "bluetooth_le_broadcast_name");
        if (string2 == null) {
            string2 = BluetoothAdapter.getDefaultAdapter().getName() + "_" + ThreadLocalRandom.current().nextInt(1000, 9999);
        }
        setBroadcastName(string2, false);
        String string3 = Settings.Secure.getString(this.mContentResolver, "bluetooth_le_broadcast_code");
        if (string3 == null) {
            String obj = UUID.randomUUID().toString();
            bytes = (obj.substring(0, 8) + obj.substring(9, 13)).getBytes(StandardCharsets.UTF_8);
        } else {
            bytes = string3.getBytes(StandardCharsets.UTF_8);
        }
        setBroadcastCode(false, bytes);
        setAppSourceName(Settings.Secure.getString(this.mContentResolver, "bluetooth_le_broadcast_app_source_name"), false);
        String string4 = Settings.Secure.getString(this.mContentResolver, "bluetooth_le_broadcast_improve_compatibility");
        boolean equals = string4 != null ? string4.equals("1") : false;
        if (this.mImproveCompatibility == equals) {
            Log.d("LocalBluetoothLeBroadcast", "setImproveCompatibility: improveCompatibility is not changed");
        } else {
            this.mImproveCompatibility = equals;
        }
    }
}

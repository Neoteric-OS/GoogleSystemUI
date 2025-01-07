package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHidHost;
import android.bluetooth.BluetoothPbap;
import android.bluetooth.BluetoothUuid;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelUuid;
import android.os.SystemClock;
import android.provider.DeviceConfig;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;
import android.util.Pair;
import androidx.compose.foundation.text.input.internal.RecordingInputConnection$$ExternalSyntheticOutline0;
import com.android.settingslib.utils.ThreadUtils;
import com.android.systemui.keyboard.KeyboardUI;
import com.android.wm.shell.R;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CachedBluetoothDevice implements Comparable {
    public long mConnectAttempted;
    public final Context mContext;
    public BluetoothDevice mDevice;
    LruCache mDrawableCache;
    public int mGroupId;
    public HearingAidInfo mHearingAidInfo;
    public boolean mJustDiscovered;
    public boolean mLocalNapRoleConnected;
    public final LocalBluetoothProfileManager mProfileManager;
    public short mRssi;
    public CachedBluetoothDevice mSubDevice;
    public boolean mUnpairing;
    public final Object mProfileLock = new Object();
    public final Collection mProfiles = new CopyOnWriteArrayList();
    public final Collection mRemovedProfiles = new CopyOnWriteArrayList();
    public final Collection mCallbacks = new CopyOnWriteArrayList();
    public final Map mCallbackExecutorMap = new ConcurrentHashMap();
    public boolean mIsActiveDeviceA2dp = false;
    public boolean mIsActiveDeviceHeadset = false;
    public boolean mIsActiveDeviceHearingAid = false;
    public boolean mIsActiveDeviceLeAudio = false;
    public boolean mIsA2dpProfileConnectedFail = false;
    public boolean mIsHeadsetProfileConnectedFail = false;
    public boolean mIsHearingAidProfileConnectedFail = false;
    public boolean mIsLeAudioProfileConnectedFail = false;
    public final Set mMemberDevices = new HashSet();
    public final AnonymousClass1 mHandler = new Handler(Looper.getMainLooper()) { // from class: com.android.settingslib.bluetooth.CachedBluetoothDevice.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            CachedBluetoothDevice cachedBluetoothDevice = CachedBluetoothDevice.this;
            if (i == 1) {
                cachedBluetoothDevice.mIsHeadsetProfileConnectedFail = true;
            } else if (i == 2) {
                cachedBluetoothDevice.mIsA2dpProfileConnectedFail = true;
            } else if (i == 21) {
                cachedBluetoothDevice.mIsHearingAidProfileConnectedFail = true;
            } else if (i != 22) {
                Log.w("CachedBluetoothDevice", "handleMessage(): unknown message : " + message.what);
            } else {
                cachedBluetoothDevice.mIsLeAudioProfileConnectedFail = true;
            }
            Log.w("CachedBluetoothDevice", "Connect to profile : " + message.what + " timeout, show error message !");
            cachedBluetoothDevice.refresh();
        }
    };
    public final BluetoothAdapter mLocalAdapter = BluetoothAdapter.getDefaultAdapter();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.settingslib.bluetooth.CachedBluetoothDevice$2, reason: invalid class name */
    public final class AnonymousClass2 extends LruCache {
        @Override // android.util.LruCache
        public final int sizeOf(Object obj, Object obj2) {
            return ((BitmapDrawable) obj2).getBitmap().getByteCount() / 1024;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        void onDeviceAttributesChanged();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.settingslib.bluetooth.CachedBluetoothDevice$1] */
    public CachedBluetoothDevice(Context context, LocalBluetoothProfileManager localBluetoothProfileManager, BluetoothDevice bluetoothDevice) {
        this.mContext = context;
        this.mProfileManager = localBluetoothProfileManager;
        this.mDevice = bluetoothDevice;
        fillData();
        this.mGroupId = -1;
        this.mDrawableCache = new AnonymousClass2(((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8);
        this.mUnpairing = false;
    }

    public final void addMemberDevice(CachedBluetoothDevice cachedBluetoothDevice) {
        Log.d("CachedBluetoothDevice", this + " addMemberDevice = " + cachedBluetoothDevice);
        this.mMemberDevices.add(cachedBluetoothDevice);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        CachedBluetoothDevice cachedBluetoothDevice = (CachedBluetoothDevice) obj;
        int i = (cachedBluetoothDevice.isConnected() ? 1 : 0) - (isConnected() ? 1 : 0);
        if (i != 0) {
            return i;
        }
        int i2 = (cachedBluetoothDevice.mDevice.getBondState() == 12 ? 1 : 0) - (this.mDevice.getBondState() == 12 ? 1 : 0);
        if (i2 != 0) {
            return i2;
        }
        int i3 = (cachedBluetoothDevice.mJustDiscovered ? 1 : 0) - (this.mJustDiscovered ? 1 : 0);
        if (i3 != 0) {
            return i3;
        }
        int i4 = cachedBluetoothDevice.mRssi - this.mRssi;
        return i4 != 0 ? i4 : getName().compareTo(cachedBluetoothDevice.getName());
    }

    public final void connect$1() {
        if (this.mDevice.getBondState() == 10) {
            startPairing();
        } else {
            this.mConnectAttempted = SystemClock.elapsedRealtime();
            connectDevice();
        }
    }

    public final void connectDevice() {
        synchronized (this.mProfileLock) {
            try {
                if (((CopyOnWriteArrayList) this.mProfiles).isEmpty()) {
                    Log.d("CachedBluetoothDevice", "No profiles. Maybe we will connect later for device " + this.mDevice);
                    return;
                }
                Log.d("CachedBluetoothDevice", "connect " + this);
                this.mDevice.connect();
                if (this.mGroupId != -1) {
                    Iterator it = ((HashSet) this.mMemberDevices).iterator();
                    while (it.hasNext()) {
                        CachedBluetoothDevice cachedBluetoothDevice = (CachedBluetoothDevice) it.next();
                        Log.d("CachedBluetoothDevice", "connect the member:" + cachedBluetoothDevice);
                        cachedBluetoothDevice.connect$1();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void disconnect() {
        synchronized (this.mProfileLock) {
            try {
                if (this.mGroupId != -1) {
                    Iterator it = ((HashSet) this.mMemberDevices).iterator();
                    while (it.hasNext()) {
                        CachedBluetoothDevice cachedBluetoothDevice = (CachedBluetoothDevice) it.next();
                        Log.d("CachedBluetoothDevice", "Disconnect the member:" + cachedBluetoothDevice);
                        cachedBluetoothDevice.disconnect();
                    }
                }
                Log.d("CachedBluetoothDevice", "Disconnect " + this);
                this.mDevice.disconnect();
            } catch (Throwable th) {
                throw th;
            }
        }
        PbapServerProfile pbapServerProfile = this.mProfileManager.mPbapProfile;
        if (pbapServerProfile == null || !isConnectedProfile(pbapServerProfile)) {
            return;
        }
        BluetoothDevice bluetoothDevice = this.mDevice;
        BluetoothPbap bluetoothPbap = pbapServerProfile.mService;
        if (bluetoothPbap == null) {
            return;
        }
        bluetoothPbap.setConnectionPolicy(bluetoothDevice, 0);
    }

    public final void dispatchAttributesChanged() {
        Iterator it = this.mCallbacks.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onDeviceAttributesChanged();
        }
        this.mCallbackExecutorMap.forEach(new CachedBluetoothDevice$$ExternalSyntheticLambda1());
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof CachedBluetoothDevice)) {
            return false;
        }
        return this.mDevice.equals(((CachedBluetoothDevice) obj).mDevice);
    }

    public final void fetchActiveDevices() {
        A2dpProfile a2dpProfile = this.mProfileManager.mA2dpProfile;
        if (a2dpProfile != null) {
            this.mIsActiveDeviceA2dp = this.mDevice.equals(a2dpProfile.getActiveDevice());
        }
        HeadsetProfile headsetProfile = this.mProfileManager.mHeadsetProfile;
        if (headsetProfile != null) {
            BluetoothDevice bluetoothDevice = this.mDevice;
            BluetoothAdapter bluetoothAdapter = headsetProfile.mBluetoothAdapter;
            BluetoothDevice bluetoothDevice2 = null;
            if (bluetoothAdapter != null) {
                List activeDevices = bluetoothAdapter.getActiveDevices(1);
                if (activeDevices.size() > 0) {
                    bluetoothDevice2 = (BluetoothDevice) activeDevices.get(0);
                }
            }
            this.mIsActiveDeviceHeadset = bluetoothDevice.equals(bluetoothDevice2);
        }
        HearingAidProfile hearingAidProfile = this.mProfileManager.mHearingAidProfile;
        if (hearingAidProfile != null) {
            BluetoothAdapter bluetoothAdapter2 = hearingAidProfile.mBluetoothAdapter;
            this.mIsActiveDeviceHearingAid = (bluetoothAdapter2 == null ? new ArrayList() : bluetoothAdapter2.getActiveDevices(21)).contains(this.mDevice);
        }
        LeAudioProfile leAudioProfile = this.mProfileManager.mLeAudioProfile;
        if (leAudioProfile != null) {
            BluetoothAdapter bluetoothAdapter3 = leAudioProfile.mBluetoothAdapter;
            this.mIsActiveDeviceLeAudio = (bluetoothAdapter3 == null ? new ArrayList() : bluetoothAdapter3.getActiveDevices(22)).contains(this.mDevice);
        }
    }

    public final void fillData() {
        updateProfiles();
        fetchActiveDevices();
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("bluetooth_phonebook_permission", 0);
        if (sharedPreferences.contains(this.mDevice.getAddress())) {
            if (this.mDevice.getPhonebookAccessPermission() == 0) {
                int i = sharedPreferences.getInt(this.mDevice.getAddress(), 0);
                if (i == 1) {
                    this.mDevice.setPhonebookAccessPermission(1);
                } else if (i == 2) {
                    this.mDevice.setPhonebookAccessPermission(2);
                }
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove(this.mDevice.getAddress());
            edit.commit();
        }
        SharedPreferences sharedPreferences2 = this.mContext.getSharedPreferences("bluetooth_message_permission", 0);
        if (sharedPreferences2.contains(this.mDevice.getAddress())) {
            if (this.mDevice.getMessageAccessPermission() == 0) {
                int i2 = sharedPreferences2.getInt(this.mDevice.getAddress(), 0);
                if (i2 == 1) {
                    this.mDevice.setMessageAccessPermission(1);
                } else if (i2 == 2) {
                    this.mDevice.setMessageAccessPermission(2);
                }
            }
            SharedPreferences.Editor edit2 = sharedPreferences2.edit();
            edit2.remove(this.mDevice.getAddress());
            edit2.commit();
        }
        dispatchAttributesChanged();
    }

    public final Optional getConnectedHearingAidSide(final int i) {
        return Stream.concat(Stream.of((Object[]) new CachedBluetoothDevice[]{this, this.mSubDevice}), this.mMemberDevices.stream()).filter(new CachedBluetoothDevice$$ExternalSyntheticLambda5(1)).filter(new Predicate() { // from class: com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticLambda11
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                int i2 = i;
                HearingAidInfo hearingAidInfo = ((CachedBluetoothDevice) obj).mHearingAidInfo;
                if ((hearingAidInfo != null ? hearingAidInfo.mSide : -1) != i2) {
                    if ((hearingAidInfo != null ? hearingAidInfo.mSide : -1) != 2) {
                        return false;
                    }
                }
                return true;
            }
        }).filter(new CachedBluetoothDevice$$ExternalSyntheticLambda5(2)).findAny();
    }

    /* JADX WARN: Code restructure failed: missing block: B:140:0x026d, code lost:
    
        if (r3 < 0) goto L180;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x026f, code lost:
    
        r0 = r16.mContext.getString(r15, com.android.settingslib.Utils.formatPercentage(r8), com.android.settingslib.Utils.formatPercentage(r3));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getConnectionSummary() {
        /*
            Method dump skipped, instructions count: 713
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.bluetooth.CachedBluetoothDevice.getConnectionSummary():java.lang.String");
    }

    public final long getHiSyncId() {
        HearingAidInfo hearingAidInfo = this.mHearingAidInfo;
        if (hearingAidInfo != null) {
            return hearingAidInfo.mHiSyncId;
        }
        return 0L;
    }

    public final int getMaxConnectionState() {
        int i;
        synchronized (this.mProfileLock) {
            try {
                i = 0;
                for (LocalBluetoothProfile localBluetoothProfile : getProfiles()) {
                    int connectionStatus = localBluetoothProfile != null ? localBluetoothProfile.getConnectionStatus(this.mDevice) : 0;
                    if (connectionStatus > i) {
                        i = connectionStatus;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i;
    }

    public final int getMinBatteryLevelWithMemberDevices() {
        return Stream.concat(Stream.of(this), this.mMemberDevices.stream()).mapToInt(new CachedBluetoothDevice$$ExternalSyntheticLambda2()).filter(new CachedBluetoothDevice$$ExternalSyntheticLambda3()).min().orElse(-1);
    }

    public final String getName() {
        String alias = this.mDevice.getAlias();
        return TextUtils.isEmpty(alias) ? this.mDevice.getAddress() : alias;
    }

    public final List getProfiles() {
        return new ArrayList(this.mProfiles);
    }

    public final List getUiAccessibleProfiles() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mProfileLock) {
            try {
                Iterator it = ((CopyOnWriteArrayList) this.mProfiles).iterator();
                while (it.hasNext()) {
                    LocalBluetoothProfile localBluetoothProfile = (LocalBluetoothProfile) it.next();
                    if (localBluetoothProfile.accessProfileEnabled()) {
                        arrayList.add(localBluetoothProfile);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return arrayList;
    }

    public final int hashCode() {
        return this.mDevice.getAddress().hashCode();
    }

    public boolean isActiveDevice(int i) {
        if (i == 1) {
            return this.mIsActiveDeviceHeadset;
        }
        if (i == 2) {
            return this.mIsActiveDeviceA2dp;
        }
        if (i == 21) {
            return this.mIsActiveDeviceHearingAid;
        }
        if (i == 22) {
            return this.mIsActiveDeviceLeAudio;
        }
        RecordingInputConnection$$ExternalSyntheticOutline0.m("getActiveDevice: unknown profile ", "CachedBluetoothDevice", i);
        return false;
    }

    public final boolean isBusy() {
        int connectionStatus;
        synchronized (this.mProfileLock) {
            try {
                Iterator it = ((CopyOnWriteArrayList) this.mProfiles).iterator();
                do {
                    if (!it.hasNext()) {
                        return this.mDevice.getBondState() == 11;
                    }
                    LocalBluetoothProfile localBluetoothProfile = (LocalBluetoothProfile) it.next();
                    connectionStatus = localBluetoothProfile != null ? localBluetoothProfile.getConnectionStatus(this.mDevice) : 0;
                    if (connectionStatus == 1) {
                        break;
                    }
                } while (connectionStatus != 3);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean isConnected() {
        LocalBluetoothProfile localBluetoothProfile;
        synchronized (this.mProfileLock) {
            try {
                Iterator it = ((CopyOnWriteArrayList) this.mProfiles).iterator();
                do {
                    if (!it.hasNext()) {
                        return false;
                    }
                    localBluetoothProfile = (LocalBluetoothProfile) it.next();
                } while ((localBluetoothProfile != null ? localBluetoothProfile.getConnectionStatus(this.mDevice) : 0) != 2);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean isConnectedAshaHearingAidDevice() {
        HearingAidProfile hearingAidProfile = this.mProfileManager.mHearingAidProfile;
        return hearingAidProfile != null && hearingAidProfile.getConnectionStatus(this.mDevice) == 2;
    }

    public final boolean isConnectedHapClientDevice() {
        HapClientProfile hapClientProfile = this.mProfileManager.mHapClientProfile;
        return hapClientProfile != null && hapClientProfile.getConnectionStatus(this.mDevice) == 2;
    }

    public final boolean isConnectedLeAudioDevice() {
        LeAudioProfile leAudioProfile = this.mProfileManager.mLeAudioProfile;
        return leAudioProfile != null && leAudioProfile.getConnectionStatus(this.mDevice) == 2;
    }

    public final boolean isConnectedProfile(LocalBluetoothProfile localBluetoothProfile) {
        return (localBluetoothProfile != null ? localBluetoothProfile.getConnectionStatus(this.mDevice) : 0) == 2;
    }

    public final boolean isHearingAidDevice() {
        return this.mHearingAidInfo != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00d3 A[Catch: all -> 0x0054, TryCatch #0 {all -> 0x0054, blocks: (B:9:0x0040, B:11:0x0047, B:13:0x004b, B:15:0x004f, B:19:0x00d3, B:21:0x00d7, B:22:0x00df, B:24:0x00e9, B:26:0x00fb, B:28:0x0108, B:32:0x0119, B:33:0x011b, B:35:0x011f, B:36:0x0172, B:38:0x0176, B:39:0x0179, B:40:0x017e, B:44:0x0123, B:47:0x0129, B:48:0x0132, B:50:0x0136, B:52:0x013a, B:54:0x0147, B:59:0x015b, B:61:0x0057, B:67:0x0067, B:68:0x0079, B:70:0x0085, B:71:0x008f, B:72:0x0099, B:73:0x00a6, B:75:0x00b2, B:77:0x00c3), top: B:8:0x0040 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0176 A[Catch: all -> 0x0054, TryCatch #0 {all -> 0x0054, blocks: (B:9:0x0040, B:11:0x0047, B:13:0x004b, B:15:0x004f, B:19:0x00d3, B:21:0x00d7, B:22:0x00df, B:24:0x00e9, B:26:0x00fb, B:28:0x0108, B:32:0x0119, B:33:0x011b, B:35:0x011f, B:36:0x0172, B:38:0x0176, B:39:0x0179, B:40:0x017e, B:44:0x0123, B:47:0x0129, B:48:0x0132, B:50:0x0136, B:52:0x013a, B:54:0x0147, B:59:0x015b, B:61:0x0057, B:67:0x0067, B:68:0x0079, B:70:0x0085, B:71:0x008f, B:72:0x0099, B:73:0x00a6, B:75:0x00b2, B:77:0x00c3), top: B:8:0x0040 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0123 A[Catch: all -> 0x0054, TryCatch #0 {all -> 0x0054, blocks: (B:9:0x0040, B:11:0x0047, B:13:0x004b, B:15:0x004f, B:19:0x00d3, B:21:0x00d7, B:22:0x00df, B:24:0x00e9, B:26:0x00fb, B:28:0x0108, B:32:0x0119, B:33:0x011b, B:35:0x011f, B:36:0x0172, B:38:0x0176, B:39:0x0179, B:40:0x017e, B:44:0x0123, B:47:0x0129, B:48:0x0132, B:50:0x0136, B:52:0x013a, B:54:0x0147, B:59:0x015b, B:61:0x0057, B:67:0x0067, B:68:0x0079, B:70:0x0085, B:71:0x008f, B:72:0x0099, B:73:0x00a6, B:75:0x00b2, B:77:0x00c3), top: B:8:0x0040 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onProfileStateChanged(com.android.settingslib.bluetooth.LocalBluetoothProfile r9, int r10) {
        /*
            Method dump skipped, instructions count: 389
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.bluetooth.CachedBluetoothDevice.onProfileStateChanged(com.android.settingslib.bluetooth.LocalBluetoothProfile, int):void");
    }

    public final void refresh() {
        ListenableFuture submit = ((MoreExecutors.ListeningDecorator) ThreadUtils.getBackgroundExecutor()).submit(new Callable() { // from class: com.android.settingslib.bluetooth.CachedBluetoothDevice$$ExternalSyntheticLambda4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                byte[] metadata;
                byte[] metadata2;
                byte[] metadata3;
                byte[] metadata4;
                Bitmap bitmap;
                Pair pair;
                CachedBluetoothDevice cachedBluetoothDevice = CachedBluetoothDevice.this;
                BluetoothDevice bluetoothDevice = cachedBluetoothDevice.mDevice;
                KeyboardUI.BluetoothErrorListener bluetoothErrorListener = BluetoothUtils.sErrorListener;
                if (DeviceConfig.getBoolean("settings_ui", "bt_advanced_header_enabled", true)) {
                    if (BluetoothUtils.getBooleanMetaData(bluetoothDevice)) {
                        Log.d("BluetoothUtils", "isAdvancedDetailsHeader: untetheredHeadset is true");
                    } else {
                        String str = (bluetoothDevice == null || (metadata = bluetoothDevice.getMetadata(5)) == null) ? null : new String(metadata);
                        if ((str == null ? null : Uri.parse(str)) != null) {
                            Log.d("BluetoothUtils", "isAdvancedDetailsHeader is true with main icon uri");
                        }
                    }
                    BluetoothDevice bluetoothDevice2 = cachedBluetoothDevice.mDevice;
                    String str2 = (bluetoothDevice2 == null || (metadata2 = bluetoothDevice2.getMetadata(5)) == null) ? null : new String(metadata2);
                    Uri parse = str2 == null ? null : Uri.parse(str2);
                    if (parse != null && cachedBluetoothDevice.mDrawableCache.get(parse.toString()) == null) {
                        LruCache lruCache = cachedBluetoothDevice.mDrawableCache;
                        String uri = parse.toString();
                        Context context = cachedBluetoothDevice.mContext;
                        Pair btClassDrawableWithDescription = BluetoothUtils.getBtClassDrawableWithDescription(context, cachedBluetoothDevice);
                        BluetoothDevice bluetoothDevice3 = cachedBluetoothDevice.mDevice;
                        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.bt_nearby_icon_size);
                        Resources resources = context.getResources();
                        if (DeviceConfig.getBoolean("settings_ui", "bt_advanced_header_enabled", true)) {
                            if (BluetoothUtils.getBooleanMetaData(bluetoothDevice3)) {
                                Log.d("BluetoothUtils", "isAdvancedDetailsHeader: untetheredHeadset is true");
                            } else {
                                String str3 = (bluetoothDevice3 == null || (metadata3 = bluetoothDevice3.getMetadata(5)) == null) ? null : new String(metadata3);
                                if ((str3 == null ? null : Uri.parse(str3)) != null) {
                                    Log.d("BluetoothUtils", "isAdvancedDetailsHeader is true with main icon uri");
                                }
                            }
                            String str4 = (bluetoothDevice3 == null || (metadata4 = bluetoothDevice3.getMetadata(5)) == null) ? null : new String(metadata4);
                            Uri parse2 = str4 == null ? null : Uri.parse(str4);
                            if (parse2 != null) {
                                try {
                                    context.getContentResolver().takePersistableUriPermission(parse2, 1);
                                } catch (SecurityException e) {
                                    Log.e("BluetoothUtils", "Failed to take persistable permission for: " + parse2, e);
                                }
                                try {
                                    bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), parse2);
                                } catch (IOException e2) {
                                    Log.e("BluetoothUtils", "Failed to get drawable for: " + parse2, e2);
                                } catch (SecurityException e3) {
                                    Log.e("BluetoothUtils", "Failed to get permission for: " + parse2, e3);
                                }
                                if (bitmap != null) {
                                    Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, dimensionPixelSize, dimensionPixelSize, false);
                                    bitmap.recycle();
                                    pair = new Pair(new BitmapDrawable(resources, createScaledBitmap), (String) btClassDrawableWithDescription.second);
                                    lruCache.put(uri, (BitmapDrawable) pair.first);
                                }
                            }
                        } else {
                            Log.d("BluetoothUtils", "isAdvancedDetailsHeader: advancedEnabled is false");
                        }
                        pair = new Pair((Drawable) btClassDrawableWithDescription.first, (String) btClassDrawableWithDescription.second);
                        lruCache.put(uri, (BitmapDrawable) pair.first);
                    }
                } else {
                    Log.d("BluetoothUtils", "isAdvancedDetailsHeader: advancedEnabled is false");
                }
                return null;
            }
        });
        FutureCallback futureCallback = new FutureCallback() { // from class: com.android.settingslib.bluetooth.CachedBluetoothDevice.3
            @Override // com.google.common.util.concurrent.FutureCallback
            public final void onSuccess(Object obj) {
                CachedBluetoothDevice.this.dispatchAttributesChanged();
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public final void onFailure(Throwable th) {
            }
        };
        submit.addListener(new Futures.CallbackListener(submit, futureCallback), this.mContext.getMainExecutor());
    }

    public final void release() {
        removeCallbacksAndMessages(null);
    }

    public final void setActive() {
        boolean removeActiveDevice;
        A2dpProfile a2dpProfile = this.mProfileManager.mA2dpProfile;
        boolean z = false;
        if (a2dpProfile != null && isConnectedProfile(a2dpProfile)) {
            BluetoothDevice bluetoothDevice = this.mDevice;
            BluetoothAdapter bluetoothAdapter = a2dpProfile.mBluetoothAdapter;
            if (bluetoothAdapter == null ? false : bluetoothDevice == null ? bluetoothAdapter.removeActiveDevice(0) : bluetoothAdapter.setActiveDevice(bluetoothDevice, 0)) {
                Log.i("CachedBluetoothDevice", "OnPreferenceClickListener: A2DP active device=" + this);
            }
        }
        HeadsetProfile headsetProfile = this.mProfileManager.mHeadsetProfile;
        int i = 1;
        if (headsetProfile != null && isConnectedProfile(headsetProfile)) {
            BluetoothDevice bluetoothDevice2 = this.mDevice;
            BluetoothAdapter bluetoothAdapter2 = headsetProfile.mBluetoothAdapter;
            if (bluetoothAdapter2 == null ? false : bluetoothDevice2 == null ? bluetoothAdapter2.removeActiveDevice(1) : bluetoothAdapter2.setActiveDevice(bluetoothDevice2, 1)) {
                Log.i("CachedBluetoothDevice", "OnPreferenceClickListener: Headset active device=" + this);
            }
        }
        HearingAidProfile hearingAidProfile = this.mProfileManager.mHearingAidProfile;
        if (hearingAidProfile != null && isConnectedProfile(hearingAidProfile)) {
            BluetoothDevice bluetoothDevice3 = this.mDevice;
            if (hearingAidProfile.mBluetoothAdapter == null) {
                removeActiveDevice = false;
            } else {
                int mode = ((AudioManager) hearingAidProfile.mContext.getSystemService(AudioManager.class)).getMode();
                if (mode != 1 && mode != 2 && mode != 3) {
                    i = 0;
                }
                removeActiveDevice = bluetoothDevice3 == null ? hearingAidProfile.mBluetoothAdapter.removeActiveDevice(i) : hearingAidProfile.mBluetoothAdapter.setActiveDevice(bluetoothDevice3, i);
            }
            if (removeActiveDevice) {
                Log.i("CachedBluetoothDevice", "OnPreferenceClickListener: Hearing Aid active device=" + this);
            }
        }
        LeAudioProfile leAudioProfile = this.mProfileManager.mLeAudioProfile;
        if (leAudioProfile == null || !isConnectedProfile(leAudioProfile)) {
            return;
        }
        BluetoothDevice bluetoothDevice4 = this.mDevice;
        BluetoothAdapter bluetoothAdapter3 = leAudioProfile.mBluetoothAdapter;
        if (bluetoothAdapter3 != null) {
            z = bluetoothDevice4 == null ? bluetoothAdapter3.removeActiveDevice(2) : bluetoothAdapter3.setActiveDevice(bluetoothDevice4, 2);
        }
        if (z) {
            Log.i("CachedBluetoothDevice", "OnPreferenceClickListener: LeAudio active device=" + this);
        }
    }

    public final void setGroupId(int i) {
        Log.d("CachedBluetoothDevice", this.mDevice.getAnonymizedAddress() + " set GroupId " + i);
        this.mGroupId = i;
    }

    public final void setJustDiscovered(boolean z) {
        if (this.mJustDiscovered != z) {
            this.mJustDiscovered = z;
            dispatchAttributesChanged();
        }
    }

    public final void setName(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.equals(str, getName())) {
            return;
        }
        this.mDevice.setAlias(str);
        dispatchAttributesChanged();
        Iterator it = ((HashSet) this.mMemberDevices).iterator();
        while (it.hasNext()) {
            ((CachedBluetoothDevice) it.next()).setName(str);
        }
        CachedBluetoothDevice cachedBluetoothDevice = this.mSubDevice;
        if (cachedBluetoothDevice != null) {
            cachedBluetoothDevice.setName(str);
        }
    }

    public void setProfileConnectedStatus(int i, boolean z) {
        if (i == 1) {
            this.mIsHeadsetProfileConnectedFail = z;
            return;
        }
        if (i == 2) {
            this.mIsA2dpProfileConnectedFail = z;
            return;
        }
        if (i == 21) {
            this.mIsHearingAidProfileConnectedFail = z;
        } else if (i != 22) {
            RecordingInputConnection$$ExternalSyntheticOutline0.m("setProfileConnectedStatus(): unknown profile id : ", "CachedBluetoothDevice", i);
        } else {
            this.mIsLeAudioProfileConnectedFail = z;
        }
    }

    public final void startPairing() {
        if (this.mLocalAdapter.isDiscovering()) {
            this.mLocalAdapter.cancelDiscovery();
        }
        this.mDevice.createBond();
    }

    public final void switchMemberDeviceContent(CachedBluetoothDevice cachedBluetoothDevice) {
        cachedBluetoothDevice.release();
        this.mMemberDevices.remove(cachedBluetoothDevice);
        BluetoothDevice bluetoothDevice = this.mDevice;
        short s = this.mRssi;
        boolean z = this.mJustDiscovered;
        HearingAidInfo hearingAidInfo = this.mHearingAidInfo;
        release();
        this.mDevice = cachedBluetoothDevice.mDevice;
        this.mRssi = cachedBluetoothDevice.mRssi;
        this.mJustDiscovered = cachedBluetoothDevice.mJustDiscovered;
        this.mHearingAidInfo = cachedBluetoothDevice.mHearingAidInfo;
        fillData();
        cachedBluetoothDevice.release();
        cachedBluetoothDevice.mDevice = bluetoothDevice;
        cachedBluetoothDevice.mRssi = s;
        cachedBluetoothDevice.mJustDiscovered = z;
        cachedBluetoothDevice.mHearingAidInfo = hearingAidInfo;
        cachedBluetoothDevice.fillData();
        addMemberDevice(cachedBluetoothDevice);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("CachedBluetoothDevice{anonymizedAddress=");
        sb.append(this.mDevice.getAnonymizedAddress());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", groupId=");
        sb.append(this.mGroupId);
        sb.append(", member=");
        sb.append(this.mMemberDevices);
        if (isHearingAidDevice()) {
            sb.append(", hearingAidInfo=");
            sb.append(this.mHearingAidInfo);
            sb.append(", subDevice=");
            sb.append(this.mSubDevice);
        }
        sb.append("}");
        return sb.toString();
    }

    public final void unpair() {
        BluetoothDevice bluetoothDevice;
        int bondState = this.mDevice.getBondState();
        if (bondState == 11) {
            this.mDevice.cancelBondProcess();
        }
        if (bondState == 10 || (bluetoothDevice = this.mDevice) == null) {
            return;
        }
        this.mUnpairing = true;
        if (bluetoothDevice.removeBond()) {
            this.mDrawableCache.evictAll();
            StringBuilder sb = new StringBuilder("Command sent successfully:REMOVE_BOND ");
            sb.append("Address:" + this.mDevice);
            Log.d("CachedBluetoothDevice", sb.toString());
        }
    }

    public final void updatePreferredTransport() {
        if (this.mProfiles.stream().noneMatch(new CachedBluetoothDevice$$ExternalSyntheticLambda5(0)) || this.mProfiles.stream().noneMatch(new CachedBluetoothDevice$$ExternalSyntheticLambda5(4))) {
            return;
        }
        LocalBluetoothProfileManager localBluetoothProfileManager = this.mProfileManager;
        HidProfile hidProfile = localBluetoothProfileManager.mHidProfile;
        BluetoothDevice bluetoothDevice = this.mDevice;
        int i = localBluetoothProfileManager.mLeAudioProfile.isEnabled(bluetoothDevice) ? 2 : 1;
        BluetoothHidHost bluetoothHidHost = hidProfile.mService;
        if (bluetoothHidHost != null) {
            bluetoothHidHost.setPreferredTransport(bluetoothDevice, i);
        }
        Log.w("CachedBluetoothDevice", "Fail to set preferred transport");
    }

    public final void updateProfiles() {
        ParcelUuid[] uuids = this.mDevice.getUuids();
        if (uuids == null) {
            return;
        }
        List uuidsList = this.mLocalAdapter.getUuidsList();
        ParcelUuid[] parcelUuidArr = new ParcelUuid[uuidsList.size()];
        uuidsList.toArray(parcelUuidArr);
        if (this.mDevice.getBondState() == 12 && BluetoothUuid.containsAnyUuid(this.mDevice.getUuids(), PbapServerProfile.PBAB_CLIENT_UUIDS)) {
            this.mDevice.getPhonebookAccessPermission();
        }
        synchronized (this.mProfileLock) {
            this.mProfileManager.updateProfiles(uuids, parcelUuidArr, this.mProfiles, this.mRemovedProfiles, this.mLocalNapRoleConnected, this.mDevice);
        }
        Log.d("CachedBluetoothDevice", "updating profiles for " + this.mDevice.getAnonymizedAddress());
        BluetoothClass bluetoothClass = this.mDevice.getBluetoothClass();
        if (bluetoothClass != null) {
            bluetoothClass.toString();
        }
        for (ParcelUuid parcelUuid : uuids) {
            Objects.toString(parcelUuid);
        }
    }

    public void setLocalBluetoothManager(LocalBluetoothManager localBluetoothManager) {
    }
}

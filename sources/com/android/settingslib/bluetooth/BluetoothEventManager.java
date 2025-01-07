package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.ParcelUuid;
import android.os.SystemClock;
import android.os.UserHandle;
import android.util.Log;
import com.android.internal.util.ArrayUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BluetoothEventManager {
    public static final boolean DEBUG = Log.isLoggable("BluetoothEventManager", 3);
    public final Context mContext;
    public final CachedBluetoothDeviceManager mDeviceManager;
    public final LocalBluetoothAdapter mLocalAdapter;
    public final android.os.Handler mReceiverHandler;
    public final UserHandle mUserHandle;
    public final BluetoothBroadcastReceiver mBroadcastReceiver = new BluetoothBroadcastReceiver();
    public final BluetoothBroadcastReceiver mProfileBroadcastReceiver = new BluetoothBroadcastReceiver();
    public final Collection mCallbacks = new CopyOnWriteArrayList();
    public final IntentFilter mAdapterIntentFilter = new IntentFilter();
    public final IntentFilter mProfileIntentFilter = new IntentFilter();
    public final Map mHandlerMap = new HashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class BluetoothBroadcastReceiver extends BroadcastReceiver {
        public BluetoothBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            Handler handler = (Handler) BluetoothEventManager.this.mHandlerMap.get(action);
            if (handler != null) {
                handler.onReceive(context, intent, bluetoothDevice);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DeviceFoundHandler implements Handler {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ BluetoothEventManager this$0;

        public /* synthetic */ DeviceFoundHandler(BluetoothEventManager bluetoothEventManager, int i) {
            this.$r8$classId = i;
            this.this$0 = bluetoothEventManager;
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x0080 A[LOOP:0: B:32:0x007a->B:34:0x0080, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x008f  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x00b2  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00d3  */
        /* JADX WARN: Removed duplicated region for block: B:99:0x01c3 A[ORIG_RETURN, RETURN] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final void onReceive$com$android$settingslib$bluetooth$BluetoothEventManager$BondStateChangedHandler(android.content.Context r9, android.content.Intent r10, android.bluetooth.BluetoothDevice r11) {
            /*
                Method dump skipped, instructions count: 476
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.bluetooth.BluetoothEventManager.DeviceFoundHandler.onReceive$com$android$settingslib$bluetooth$BluetoothEventManager$BondStateChangedHandler(android.content.Context, android.content.Intent, android.bluetooth.BluetoothDevice):void");
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public final void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            char c;
            int i;
            switch (this.$r8$classId) {
                case 0:
                    short shortExtra = intent.getShortExtra("android.bluetooth.device.extra.RSSI", Short.MIN_VALUE);
                    intent.getStringExtra("android.bluetooth.device.extra.NAME");
                    intent.getBooleanExtra("android.bluetooth.extra.IS_COORDINATED_SET_MEMBER", false);
                    BluetoothEventManager bluetoothEventManager = this.this$0;
                    CachedBluetoothDevice findDevice = bluetoothEventManager.mDeviceManager.findDevice(bluetoothDevice);
                    if (findDevice == null) {
                        findDevice = bluetoothEventManager.mDeviceManager.addDevice(bluetoothDevice);
                        Log.d("BluetoothEventManager", "DeviceFoundHandler created new CachedBluetoothDevice " + findDevice.mDevice.getAnonymizedAddress());
                    } else if (findDevice.mDevice.getBondState() == 12 && !findDevice.mDevice.isConnected()) {
                        bluetoothEventManager.dispatchDeviceAdded(findDevice);
                    }
                    if (findDevice.mRssi != shortExtra) {
                        findDevice.mRssi = shortExtra;
                        findDevice.dispatchAttributesChanged();
                    }
                    findDevice.setJustDiscovered(true);
                    return;
                case 1:
                    if (bluetoothDevice == null) {
                        Log.w("BluetoothEventManager", "AclStateChangedHandler: device is null");
                        return;
                    }
                    CachedBluetoothDeviceManager cachedBluetoothDeviceManager = this.this$0.mDeviceManager;
                    synchronized (cachedBluetoothDeviceManager) {
                        for (CachedBluetoothDevice cachedBluetoothDevice : cachedBluetoothDeviceManager.mCachedDevices) {
                            if (!cachedBluetoothDevice.mDevice.equals(bluetoothDevice)) {
                                Set set = cachedBluetoothDevice.mMemberDevices;
                                if (set.isEmpty()) {
                                    CachedBluetoothDevice cachedBluetoothDevice2 = cachedBluetoothDevice.mSubDevice;
                                    if (cachedBluetoothDevice2 != null && cachedBluetoothDevice2.mDevice.equals(bluetoothDevice)) {
                                        return;
                                    }
                                } else {
                                    Iterator it = ((HashSet) set).iterator();
                                    while (it.hasNext()) {
                                        if (((CachedBluetoothDevice) it.next()).mDevice.equals(bluetoothDevice)) {
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                        String action = intent.getAction();
                        if (action == null) {
                            Log.w("BluetoothEventManager", "AclStateChangedHandler: action is null");
                            return;
                        }
                        CachedBluetoothDevice findDevice2 = this.this$0.mDeviceManager.findDevice(bluetoothDevice);
                        if (findDevice2 == null) {
                            Log.w("BluetoothEventManager", "AclStateChangedHandler: activeDevice is null");
                            return;
                        }
                        int hashCode = action.hashCode();
                        int i2 = 0;
                        if (hashCode != -301431627) {
                            if (hashCode == 1821585647 && action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                                c = 1;
                            }
                            c = 65535;
                        } else {
                            if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
                                c = 0;
                            }
                            c = 65535;
                        }
                        if (c == 0) {
                            i2 = 2;
                        } else if (c != 1) {
                            Log.w("BluetoothEventManager", "ActiveDeviceChangedHandler: unknown action ".concat(action));
                            return;
                        }
                        Iterator it2 = ((CopyOnWriteArrayList) this.this$0.mCallbacks).iterator();
                        while (it2.hasNext()) {
                            ((BluetoothCallback) it2.next()).onAclConnectionStateChanged(findDevice2, i2);
                        }
                        return;
                    }
                case 2:
                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                    this.this$0.mLocalAdapter.setBluetoothStateInt(intExtra);
                    Iterator it3 = ((CopyOnWriteArrayList) this.this$0.mCallbacks).iterator();
                    while (it3.hasNext()) {
                        ((BluetoothCallback) it3.next()).onBluetoothStateChanged(intExtra);
                    }
                    CachedBluetoothDeviceManager cachedBluetoothDeviceManager2 = this.this$0.mDeviceManager;
                    synchronized (cachedBluetoothDeviceManager2) {
                        try {
                            synchronized (cachedBluetoothDeviceManager2) {
                                if (intExtra == 13) {
                                    try {
                                        for (int size = cachedBluetoothDeviceManager2.mCachedDevices.size() - 1; size >= 0; size--) {
                                            CachedBluetoothDevice cachedBluetoothDevice3 = (CachedBluetoothDevice) cachedBluetoothDeviceManager2.mCachedDevices.get(size);
                                            Set set2 = cachedBluetoothDevice3.mMemberDevices;
                                            if (set2.isEmpty()) {
                                                CachedBluetoothDevice cachedBluetoothDevice4 = cachedBluetoothDevice3.mSubDevice;
                                                if (cachedBluetoothDevice4 != null && cachedBluetoothDevice4.mDevice.getBondState() != 12) {
                                                    cachedBluetoothDevice3.mSubDevice = null;
                                                }
                                            } else {
                                                Iterator it4 = ((HashSet) set2).iterator();
                                                while (it4.hasNext()) {
                                                    CachedBluetoothDevice cachedBluetoothDevice5 = (CachedBluetoothDevice) it4.next();
                                                    if (cachedBluetoothDevice5.mDevice.getBondState() != 12) {
                                                        cachedBluetoothDevice5.release();
                                                        cachedBluetoothDevice3.mMemberDevices.remove(cachedBluetoothDevice5);
                                                    }
                                                }
                                            }
                                            if (cachedBluetoothDevice3.mDevice.getBondState() != 12) {
                                                cachedBluetoothDevice3.setJustDiscovered(false);
                                                cachedBluetoothDevice3.release();
                                                cachedBluetoothDeviceManager2.mCachedDevices.remove(size);
                                            }
                                        }
                                    } finally {
                                    }
                                }
                            }
                            return;
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    return;
                case 3:
                    onReceive$com$android$settingslib$bluetooth$BluetoothEventManager$BondStateChangedHandler(context, intent, bluetoothDevice);
                    return;
                case 4:
                    String action2 = intent.getAction();
                    if (action2 == null) {
                        Log.w("BluetoothEventManager", "ActiveDeviceChangedHandler: action is null");
                        return;
                    }
                    BluetoothEventManager bluetoothEventManager2 = this.this$0;
                    CachedBluetoothDevice findDevice3 = bluetoothEventManager2.mDeviceManager.findDevice(bluetoothDevice);
                    if (action2.equals("android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED")) {
                        i = 2;
                    } else if (action2.equals("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED")) {
                        i = 1;
                    } else if (action2.equals("android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED")) {
                        i = 21;
                    } else {
                        if (!action2.equals("android.bluetooth.action.LE_AUDIO_ACTIVE_DEVICE_CHANGED")) {
                            Log.w("BluetoothEventManager", "ActiveDeviceChangedHandler: unknown action ".concat(action2));
                            return;
                        }
                        i = 22;
                    }
                    bluetoothEventManager2.dispatchActiveDeviceChanged(findDevice3, i);
                    return;
                case 5:
                    if (intent.getAction() == null) {
                        Log.w("BluetoothEventManager", "AudioModeChangedHandler() action is null");
                        return;
                    }
                    BluetoothEventManager bluetoothEventManager3 = this.this$0;
                    Iterator it5 = bluetoothEventManager3.mDeviceManager.getCachedDevicesCopy().iterator();
                    while (it5.hasNext()) {
                        ((CachedBluetoothDevice) it5.next()).dispatchAttributesChanged();
                    }
                    Iterator it6 = ((CopyOnWriteArrayList) bluetoothEventManager3.mCallbacks).iterator();
                    while (it6.hasNext()) {
                        ((BluetoothCallback) it6.next()).onAudioModeChanged();
                    }
                    return;
                case 6:
                    if (intent.getAction() == null) {
                        Log.w("BluetoothEventManager", "AutoOnStateChangedHandler() action is null");
                        return;
                    }
                    int intExtra2 = intent.getIntExtra("android.bluetooth.extra.AUTO_ON_STATE", Integer.MIN_VALUE);
                    Iterator it7 = ((CopyOnWriteArrayList) this.this$0.mCallbacks).iterator();
                    while (it7.hasNext()) {
                        ((BluetoothCallback) it7.next()).onAutoOnStateChanged(intExtra2);
                    }
                    return;
                case 7:
                    CachedBluetoothDevice findDevice4 = this.this$0.mDeviceManager.findDevice(bluetoothDevice);
                    if (findDevice4 != null) {
                        findDevice4.refresh();
                        return;
                    }
                    return;
                case 8:
                    CachedBluetoothDevice findDevice5 = this.this$0.mDeviceManager.findDevice(bluetoothDevice);
                    if (findDevice5 != null) {
                        findDevice5.refresh();
                        return;
                    }
                    return;
                case 9:
                    BluetoothEventManager bluetoothEventManager4 = this.this$0;
                    CachedBluetoothDevice findDevice6 = bluetoothEventManager4.mDeviceManager.findDevice(bluetoothDevice);
                    int intExtra3 = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", Integer.MIN_VALUE);
                    Iterator it8 = ((CopyOnWriteArrayList) bluetoothEventManager4.mCallbacks).iterator();
                    while (it8.hasNext()) {
                        ((BluetoothCallback) it8.next()).onConnectionStateChanged(findDevice6, intExtra3);
                    }
                    return;
                case 10:
                    CachedBluetoothDevice findDevice7 = this.this$0.mDeviceManager.findDevice(bluetoothDevice);
                    if (findDevice7 != null) {
                        Log.d("CachedBluetoothDevice", "Device name: " + findDevice7.getName());
                        findDevice7.dispatchAttributesChanged();
                        return;
                    }
                    return;
                default:
                    CachedBluetoothDevice findDevice8 = this.this$0.mDeviceManager.findDevice(bluetoothDevice);
                    if (findDevice8 != null) {
                        findDevice8.updateProfiles();
                        ParcelUuid[] uuids = findDevice8.mDevice.getUuids();
                        long j = 30000;
                        if (!ArrayUtils.contains(uuids, BluetoothUuid.HOGP)) {
                            if (ArrayUtils.contains(uuids, BluetoothUuid.HEARING_AID)) {
                                j = 15000;
                            } else if (!ArrayUtils.contains(uuids, BluetoothUuid.LE_AUDIO)) {
                                j = 5000;
                            }
                        }
                        Log.d("CachedBluetoothDevice", "onUuidChanged: Time since last connect=" + (SystemClock.elapsedRealtime() - findDevice8.mConnectAttempted));
                        if (findDevice8.mConnectAttempted + j > SystemClock.elapsedRealtime()) {
                            Log.d("CachedBluetoothDevice", "onUuidChanged: triggering connectDevice");
                            findDevice8.connectDevice();
                        }
                        findDevice8.dispatchAttributesChanged();
                        return;
                    }
                    return;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Handler {
        void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ScanningStateChangedHandler implements Handler {
        public final boolean mStarted;

        public ScanningStateChangedHandler(boolean z) {
            this.mStarted = z;
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public final void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Iterator it = ((CopyOnWriteArrayList) BluetoothEventManager.this.mCallbacks).iterator();
            while (it.hasNext()) {
                ((BluetoothCallback) it.next()).getClass();
            }
            CachedBluetoothDeviceManager cachedBluetoothDeviceManager = BluetoothEventManager.this.mDeviceManager;
            boolean z = this.mStarted;
            synchronized (cachedBluetoothDeviceManager) {
                if (z) {
                    for (int size = cachedBluetoothDeviceManager.mCachedDevices.size() - 1; size >= 0; size--) {
                        CachedBluetoothDevice cachedBluetoothDevice = (CachedBluetoothDevice) cachedBluetoothDeviceManager.mCachedDevices.get(size);
                        cachedBluetoothDevice.setJustDiscovered(false);
                        Set set = cachedBluetoothDevice.mMemberDevices;
                        if (!set.isEmpty()) {
                            Iterator it2 = ((HashSet) set).iterator();
                            while (it2.hasNext()) {
                                ((CachedBluetoothDevice) it2.next()).setJustDiscovered(false);
                            }
                            return;
                        } else {
                            CachedBluetoothDevice cachedBluetoothDevice2 = cachedBluetoothDevice.mSubDevice;
                            if (cachedBluetoothDevice2 != null) {
                                cachedBluetoothDevice2.setJustDiscovered(false);
                            }
                        }
                    }
                }
            }
        }
    }

    public BluetoothEventManager(LocalBluetoothAdapter localBluetoothAdapter, LocalBluetoothManager localBluetoothManager, CachedBluetoothDeviceManager cachedBluetoothDeviceManager, Context context, android.os.Handler handler, UserHandle userHandle) {
        this.mLocalAdapter = localBluetoothAdapter;
        this.mDeviceManager = cachedBluetoothDeviceManager;
        this.mContext = context;
        this.mUserHandle = userHandle;
        this.mReceiverHandler = handler;
        addHandler("android.bluetooth.adapter.action.STATE_CHANGED", new DeviceFoundHandler(this, 2));
        addHandler("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED", new DeviceFoundHandler(this, 9));
        addHandler("android.bluetooth.adapter.action.DISCOVERY_STARTED", new ScanningStateChangedHandler(true));
        addHandler("android.bluetooth.adapter.action.DISCOVERY_FINISHED", new ScanningStateChangedHandler(false));
        addHandler("android.bluetooth.device.action.FOUND", new DeviceFoundHandler(this, 0));
        addHandler("android.bluetooth.device.action.NAME_CHANGED", new DeviceFoundHandler(this, 10));
        addHandler("android.bluetooth.device.action.ALIAS_CHANGED", new DeviceFoundHandler(this, 10));
        addHandler("android.bluetooth.device.action.BOND_STATE_CHANGED", new DeviceFoundHandler(this, 3));
        addHandler("android.bluetooth.device.action.CLASS_CHANGED", new DeviceFoundHandler(this, 8));
        addHandler("android.bluetooth.device.action.UUID", new DeviceFoundHandler(this, 11));
        addHandler("android.bluetooth.device.action.BATTERY_LEVEL_CHANGED", new DeviceFoundHandler(this, 7));
        addHandler("android.bluetooth.a2dp.profile.action.ACTIVE_DEVICE_CHANGED", new DeviceFoundHandler(this, 4));
        addHandler("android.bluetooth.headset.profile.action.ACTIVE_DEVICE_CHANGED", new DeviceFoundHandler(this, 4));
        addHandler("android.bluetooth.hearingaid.profile.action.ACTIVE_DEVICE_CHANGED", new DeviceFoundHandler(this, 4));
        addHandler("android.bluetooth.action.LE_AUDIO_ACTIVE_DEVICE_CHANGED", new DeviceFoundHandler(this, 4));
        addHandler("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED", new DeviceFoundHandler(this, 5));
        addHandler("android.intent.action.PHONE_STATE", new DeviceFoundHandler(this, 5));
        addHandler("android.bluetooth.device.action.ACL_CONNECTED", new DeviceFoundHandler(this, 1));
        addHandler("android.bluetooth.device.action.ACL_DISCONNECTED", new DeviceFoundHandler(this, 1));
        addHandler("android.bluetooth.action.AUTO_ON_STATE_CHANGED", new DeviceFoundHandler(this, 6));
        registerAdapterIntentReceiver();
    }

    public void addHandler(String str, Handler handler) {
        this.mHandlerMap.put(str, handler);
        this.mAdapterIntentFilter.addAction(str);
    }

    public void addProfileHandler(String str, Handler handler) {
        this.mHandlerMap.put(str, handler);
        this.mProfileIntentFilter.addAction(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00e2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void dispatchActiveDeviceChanged(final com.android.settingslib.bluetooth.CachedBluetoothDevice r8, int r9) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.bluetooth.BluetoothEventManager.dispatchActiveDeviceChanged(com.android.settingslib.bluetooth.CachedBluetoothDevice, int):void");
    }

    public final void dispatchDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice) {
        Iterator it = ((CopyOnWriteArrayList) this.mCallbacks).iterator();
        while (it.hasNext()) {
            ((BluetoothCallback) it.next()).onDeviceAdded(cachedBluetoothDevice);
        }
    }

    public final void dispatchDeviceRemoved(CachedBluetoothDevice cachedBluetoothDevice) {
        Iterator it = ((CopyOnWriteArrayList) this.mCallbacks).iterator();
        while (it.hasNext()) {
            ((BluetoothCallback) it.next()).onDeviceDeleted(cachedBluetoothDevice);
        }
    }

    public final void readPairedDevices() {
        Set<BluetoothDevice> bondedDevices = this.mLocalAdapter.mAdapter.getBondedDevices();
        if (bondedDevices == null) {
            return;
        }
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            CachedBluetoothDeviceManager cachedBluetoothDeviceManager = this.mDeviceManager;
            if (cachedBluetoothDeviceManager.findDevice(bluetoothDevice) == null) {
                cachedBluetoothDeviceManager.addDevice(bluetoothDevice);
            }
        }
    }

    public void registerAdapterIntentReceiver() {
        IntentFilter intentFilter = this.mAdapterIntentFilter;
        UserHandle userHandle = this.mUserHandle;
        BluetoothBroadcastReceiver bluetoothBroadcastReceiver = this.mBroadcastReceiver;
        if (userHandle == null) {
            this.mContext.registerReceiver(bluetoothBroadcastReceiver, intentFilter, null, this.mReceiverHandler, 2);
        } else {
            this.mContext.registerReceiverAsUser(bluetoothBroadcastReceiver, userHandle, intentFilter, null, this.mReceiverHandler, 2);
        }
    }

    public final void registerCallback(BluetoothCallback bluetoothCallback) {
        ((CopyOnWriteArrayList) this.mCallbacks).add(bluetoothCallback);
    }

    public void registerProfileIntentReceiver() {
        IntentFilter intentFilter = this.mProfileIntentFilter;
        UserHandle userHandle = this.mUserHandle;
        BluetoothBroadcastReceiver bluetoothBroadcastReceiver = this.mProfileBroadcastReceiver;
        if (userHandle == null) {
            this.mContext.registerReceiver(bluetoothBroadcastReceiver, intentFilter, null, this.mReceiverHandler, 2);
        } else {
            this.mContext.registerReceiverAsUser(bluetoothBroadcastReceiver, userHandle, intentFilter, null, this.mReceiverHandler, 2);
        }
    }

    public final void unregisterCallback(BluetoothCallback bluetoothCallback) {
        ((CopyOnWriteArrayList) this.mCallbacks).remove(bluetoothCallback);
    }
}

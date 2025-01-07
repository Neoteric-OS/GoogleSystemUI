package com.android.systemui.statusbar.policy;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.UserManager;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import com.android.settingslib.bluetooth.BluetoothCallback;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.bluetooth.LocalBluetoothProfile;
import com.android.settingslib.bluetooth.LocalBluetoothProfileManager;
import com.android.systemui.Dumpable;
import com.android.systemui.bluetooth.BluetoothLogger;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.statusbar.policy.bluetooth.BluetoothRepositoryImpl;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BluetoothControllerImpl implements BluetoothCallback, CachedBluetoothDevice.Callback, LocalBluetoothProfileManager.ServiceListener, CallbackController, Dumpable {
    public final BluetoothAdapter mAdapter;
    public boolean mAudioProfileOnly;
    public final Executor mBackgroundExecutor;
    public final BluetoothRepositoryImpl mBluetoothRepository;
    public final List mConnectedDevices = new ArrayList();
    public int mConnectionState = 0;
    public final int mCurrentUser;
    public boolean mEnabled;
    public final H mHandler;
    public boolean mIsActive;
    public final LocalBluetoothManager mLocalBluetoothManager;
    public final BluetoothLogger mLogger;
    public int mState;
    public final UserManager mUserManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class H extends Handler {
        public final ArrayList mCallbacks;

        public H(Looper looper) {
            super(looper);
            this.mCallbacks = new ArrayList();
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                Iterator it = this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((BluetoothController$Callback) it.next()).onBluetoothDevicesChanged();
                }
            } else {
                if (i == 2) {
                    Iterator it2 = this.mCallbacks.iterator();
                    while (it2.hasNext()) {
                        BluetoothController$Callback bluetoothController$Callback = (BluetoothController$Callback) it2.next();
                        boolean z = BluetoothControllerImpl.this.mEnabled;
                        bluetoothController$Callback.onBluetoothStateChange();
                    }
                    return;
                }
                if (i == 3) {
                    this.mCallbacks.add((BluetoothController$Callback) message.obj);
                } else {
                    if (i != 4) {
                        return;
                    }
                    this.mCallbacks.remove((BluetoothController$Callback) message.obj);
                }
            }
        }
    }

    public BluetoothControllerImpl(Context context, UserTracker userTracker, DumpManager dumpManager, BluetoothLogger bluetoothLogger, BluetoothRepositoryImpl bluetoothRepositoryImpl, Executor executor, Looper looper, LocalBluetoothManager localBluetoothManager, BluetoothAdapter bluetoothAdapter) {
        int i;
        this.mLogger = bluetoothLogger;
        this.mBluetoothRepository = bluetoothRepositoryImpl;
        this.mLocalBluetoothManager = localBluetoothManager;
        this.mHandler = new H(looper);
        this.mBackgroundExecutor = executor;
        if (localBluetoothManager != null) {
            localBluetoothManager.mEventManager.registerCallback(this);
            ((CopyOnWriteArrayList) localBluetoothManager.mProfileManager.mServiceListeners).add(this);
            LocalBluetoothAdapter localBluetoothAdapter = localBluetoothManager.mLocalAdapter;
            synchronized (localBluetoothAdapter) {
                if (localBluetoothAdapter.mAdapter.getState() != localBluetoothAdapter.mState) {
                    localBluetoothAdapter.setBluetoothStateInt(localBluetoothAdapter.mAdapter.getState());
                }
                i = localBluetoothAdapter.mState;
            }
            onBluetoothStateChanged(i);
        }
        this.mUserManager = (UserManager) context.getSystemService("user");
        this.mCurrentUser = ((UserTrackerImpl) userTracker).getUserId();
        dumpManager.getClass();
        DumpManager.registerDumpable$default(dumpManager, "BluetoothController", this);
        this.mAdapter = bluetoothAdapter;
    }

    public static String connectionStateToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? GenericDocument$$ExternalSyntheticOutline0.m("UNKNOWN(", ")", i) : "DISCONNECTING" : "CONNECTED" : "CONNECTING" : "DISCONNECTED";
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void addCallback(Object obj) {
        H h = this.mHandler;
        h.obtainMessage(3, (BluetoothController$Callback) obj).sendToTarget();
        h.sendEmptyMessage(2);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        ArrayList arrayList;
        printWriter.println("BluetoothController state:");
        printWriter.print("  mLocalBluetoothManager=");
        LocalBluetoothManager localBluetoothManager = this.mLocalBluetoothManager;
        printWriter.println(localBluetoothManager);
        if (localBluetoothManager == null) {
            return;
        }
        printWriter.print("  mEnabled=");
        printWriter.println(this.mEnabled);
        printWriter.print("  mConnectionState=");
        printWriter.println(connectionStateToString(this.mConnectionState));
        printWriter.print("  mAudioProfileOnly=");
        printWriter.println(this.mAudioProfileOnly);
        printWriter.print("  mIsActive=");
        printWriter.println(this.mIsActive);
        printWriter.print("  mConnectedDevices=");
        synchronized (this.mConnectedDevices) {
            arrayList = new ArrayList(this.mConnectedDevices);
        }
        printWriter.println(arrayList);
        printWriter.print("  mCallbacks.size=");
        printWriter.println(this.mHandler.mCallbacks.size());
        printWriter.println("  Bluetooth Devices:");
        for (CachedBluetoothDevice cachedBluetoothDevice : getDevices()) {
            StringBuilder sb = new StringBuilder("    ");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(cachedBluetoothDevice.getName());
            sb2.append(" profiles=");
            ArrayList arrayList2 = new ArrayList();
            Iterator it = cachedBluetoothDevice.getProfiles().iterator();
            while (it.hasNext()) {
                arrayList2.add(String.valueOf(((LocalBluetoothProfile) it.next()).getProfileId()));
            }
            sb2.append("[" + String.join(",", arrayList2) + "]");
            sb2.append(" connected=");
            sb2.append(cachedBluetoothDevice.isConnected());
            sb2.append(" active[A2DP]=");
            sb2.append(cachedBluetoothDevice.isActiveDevice(2));
            sb2.append(" active[HEADSET]=");
            sb2.append(cachedBluetoothDevice.isActiveDevice(1));
            sb2.append(" active[HEARING_AID]=");
            sb2.append(cachedBluetoothDevice.isActiveDevice(21));
            sb2.append(" active[LE_AUDIO]=");
            sb2.append(cachedBluetoothDevice.isActiveDevice(22));
            sb.append(sb2.toString());
            printWriter.println(sb.toString());
        }
    }

    public final String getConnectedDeviceName() {
        CachedBluetoothDevice cachedBluetoothDevice;
        synchronized (this.mConnectedDevices) {
            try {
                cachedBluetoothDevice = ((ArrayList) this.mConnectedDevices).size() == 1 ? (CachedBluetoothDevice) ((ArrayList) this.mConnectedDevices).get(0) : null;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (cachedBluetoothDevice != null) {
            return cachedBluetoothDevice.getName();
        }
        return null;
    }

    public final Collection getDevices() {
        LocalBluetoothManager localBluetoothManager = this.mLocalBluetoothManager;
        return localBluetoothManager != null ? localBluetoothManager.mCachedDeviceManager.getCachedDevicesCopy() : Collections.emptyList();
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public final void onAclConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        this.mLogger.logAclConnectionStateChanged(cachedBluetoothDevice.mDevice.getAddress(), connectionStateToString(i));
        updateConnected();
        this.mHandler.sendEmptyMessage(2);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public final void onActiveDeviceChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        this.mLogger.logActiveDeviceChanged(i, cachedBluetoothDevice == null ? null : cachedBluetoothDevice.mDevice.getAddress());
        boolean z = false;
        for (CachedBluetoothDevice cachedBluetoothDevice2 : getDevices()) {
            boolean z2 = true;
            if (!cachedBluetoothDevice2.isActiveDevice(1) && !cachedBluetoothDevice2.isActiveDevice(2) && !cachedBluetoothDevice2.isActiveDevice(21) && !cachedBluetoothDevice2.isActiveDevice(22)) {
                z2 = false;
            }
            z |= z2;
        }
        boolean z3 = this.mIsActive;
        H h = this.mHandler;
        if (z3 != z) {
            this.mIsActive = z;
            h.sendEmptyMessage(2);
        }
        h.sendEmptyMessage(2);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public final void onBluetoothStateChanged(int i) {
        this.mLogger.logStateChange(BluetoothAdapter.nameForState(i));
        this.mEnabled = i == 12 || i == 11;
        this.mState = i;
        updateConnected();
        this.mHandler.sendEmptyMessage(2);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public final void onConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        this.mLogger.logDeviceConnectionStateChanged(cachedBluetoothDevice == null ? null : cachedBluetoothDevice.mDevice.getAddress(), connectionStateToString(i));
        updateConnected();
        this.mHandler.sendEmptyMessage(2);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public final void onDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice) {
        this.mLogger.logDeviceAdded(cachedBluetoothDevice.mDevice.getAddress());
        ((CopyOnWriteArrayList) cachedBluetoothDevice.mCallbacks).add(this);
        updateConnected();
        this.mHandler.sendEmptyMessage(1);
    }

    @Override // com.android.settingslib.bluetooth.CachedBluetoothDevice.Callback
    public final void onDeviceAttributesChanged() {
        this.mLogger.logDeviceAttributesChanged();
        updateConnected();
        this.mHandler.sendEmptyMessage(1);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public final void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        this.mLogger.logBondStateChange(i, cachedBluetoothDevice.mDevice.getAddress());
        updateConnected();
        this.mHandler.sendEmptyMessage(1);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public final void onDeviceDeleted(CachedBluetoothDevice cachedBluetoothDevice) {
        this.mLogger.logDeviceDeleted(cachedBluetoothDevice.mDevice.getAddress());
        updateConnected();
        this.mHandler.sendEmptyMessage(1);
    }

    @Override // com.android.settingslib.bluetooth.BluetoothCallback
    public final void onProfileConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i, int i2) {
        this.mLogger.logProfileConnectionStateChanged(cachedBluetoothDevice.mDevice.getAddress(), connectionStateToString(i), i2);
        updateConnected();
        this.mHandler.sendEmptyMessage(2);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfileManager.ServiceListener
    public final void onServiceConnected() {
        updateConnected();
        this.mHandler.sendEmptyMessage(1);
    }

    @Override // com.android.systemui.statusbar.policy.CallbackController
    public final void removeCallback(Object obj) {
        this.mHandler.obtainMessage(4, (BluetoothController$Callback) obj).sendToTarget();
    }

    public final void updateConnected() {
        this.mBluetoothRepository.fetchConnectionStatusInBackground(getDevices(), new BluetoothControllerImpl$$ExternalSyntheticLambda0(this));
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfileManager.ServiceListener
    public final void onServiceDisconnected() {
    }
}

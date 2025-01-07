package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CachedBluetoothDeviceManager {
    static int sLateBondingTimeoutMillis = 10000;
    public final LocalBluetoothManager mBtManager;
    final List mCachedDevices;
    public final Context mContext;
    CsipDeviceManager mCsipDeviceManager;
    HearingAidDeviceManager mHearingAidDeviceManager;

    public CachedBluetoothDeviceManager(Context context, LocalBluetoothManager localBluetoothManager) {
        ArrayList arrayList = new ArrayList();
        this.mCachedDevices = arrayList;
        this.mContext = context;
        this.mBtManager = localBluetoothManager;
        this.mHearingAidDeviceManager = new HearingAidDeviceManager(context, localBluetoothManager, arrayList);
        this.mCsipDeviceManager = new CsipDeviceManager(localBluetoothManager, arrayList);
    }

    public final CachedBluetoothDevice addDevice(BluetoothDevice bluetoothDevice) {
        CachedBluetoothDevice findDevice;
        CachedBluetoothDevice cachedBluetoothDevice;
        LocalBluetoothProfileManager localBluetoothProfileManager = this.mBtManager.mProfileManager;
        synchronized (this) {
            try {
                findDevice = findDevice(bluetoothDevice);
                if (findDevice == null) {
                    findDevice = new CachedBluetoothDevice(this.mContext, localBluetoothProfileManager, bluetoothDevice);
                    this.mCsipDeviceManager.initCsipDeviceIfNeeded(findDevice);
                    HearingAidInfo generateHearingAidInfo = this.mHearingAidDeviceManager.generateHearingAidInfo(findDevice);
                    if (generateHearingAidInfo != null) {
                        findDevice.mHearingAidInfo = generateHearingAidInfo;
                        findDevice.dispatchAttributesChanged();
                    }
                    if (!this.mCsipDeviceManager.setMemberDeviceIfNeeded(findDevice)) {
                        HearingAidDeviceManager hearingAidDeviceManager = this.mHearingAidDeviceManager;
                        hearingAidDeviceManager.getClass();
                        long hiSyncId = findDevice.getHiSyncId();
                        if (HearingAidDeviceManager.isValidHiSyncId(hiSyncId)) {
                            int size = hearingAidDeviceManager.mCachedDevices.size() - 1;
                            while (true) {
                                if (size < 0) {
                                    cachedBluetoothDevice = null;
                                    break;
                                }
                                cachedBluetoothDevice = (CachedBluetoothDevice) hearingAidDeviceManager.mCachedDevices.get(size);
                                if (cachedBluetoothDevice.getHiSyncId() == hiSyncId) {
                                    break;
                                }
                                size--;
                            }
                            if (cachedBluetoothDevice != null) {
                                cachedBluetoothDevice.mSubDevice = findDevice;
                                findDevice.setName(cachedBluetoothDevice.getName());
                            }
                        }
                        this.mCachedDevices.add(findDevice);
                        this.mBtManager.mEventManager.dispatchDeviceAdded(findDevice);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return findDevice;
    }

    public final synchronized void clearNonBondedDevices() {
        clearNonBondedSubDevices();
        final ArrayList arrayList = new ArrayList();
        this.mCachedDevices.stream().filter(new CachedBluetoothDeviceManager$$ExternalSyntheticLambda0()).forEach(new Consumer() { // from class: com.android.settingslib.bluetooth.CachedBluetoothDeviceManager$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                List list = arrayList;
                CachedBluetoothDevice cachedBluetoothDevice = (CachedBluetoothDevice) obj;
                cachedBluetoothDevice.release();
                list.add(cachedBluetoothDevice);
            }
        });
        this.mCachedDevices.removeAll(arrayList);
    }

    public final void clearNonBondedSubDevices() {
        for (int size = this.mCachedDevices.size() - 1; size >= 0; size--) {
            CachedBluetoothDevice cachedBluetoothDevice = (CachedBluetoothDevice) this.mCachedDevices.get(size);
            Set set = cachedBluetoothDevice.mMemberDevices;
            if (!set.isEmpty()) {
                Object[] array = set.toArray();
                for (Object obj : array) {
                    CachedBluetoothDevice cachedBluetoothDevice2 = (CachedBluetoothDevice) obj;
                    if (cachedBluetoothDevice2.mDevice.getBondState() == 10) {
                        cachedBluetoothDevice2.release();
                        cachedBluetoothDevice.mMemberDevices.remove(cachedBluetoothDevice2);
                    }
                }
                return;
            }
            CachedBluetoothDevice cachedBluetoothDevice3 = cachedBluetoothDevice.mSubDevice;
            if (cachedBluetoothDevice3 != null && cachedBluetoothDevice3.mDevice.getBondState() == 10) {
                cachedBluetoothDevice3.release();
                cachedBluetoothDevice.mSubDevice = null;
            }
        }
    }

    public final synchronized CachedBluetoothDevice findDevice(BluetoothDevice bluetoothDevice) {
        for (CachedBluetoothDevice cachedBluetoothDevice : this.mCachedDevices) {
            if (cachedBluetoothDevice.mDevice.equals(bluetoothDevice)) {
                return cachedBluetoothDevice;
            }
            Set set = cachedBluetoothDevice.mMemberDevices;
            if (!set.isEmpty()) {
                Iterator it = ((HashSet) set).iterator();
                while (it.hasNext()) {
                    CachedBluetoothDevice cachedBluetoothDevice2 = (CachedBluetoothDevice) it.next();
                    if (cachedBluetoothDevice2.mDevice.equals(bluetoothDevice)) {
                        return cachedBluetoothDevice2;
                    }
                }
            }
            CachedBluetoothDevice cachedBluetoothDevice3 = cachedBluetoothDevice.mSubDevice;
            if (cachedBluetoothDevice3 != null && cachedBluetoothDevice3.mDevice.equals(bluetoothDevice)) {
                return cachedBluetoothDevice3;
            }
        }
        return null;
    }

    public final synchronized Collection getCachedDevicesCopy() {
        return new ArrayList(this.mCachedDevices);
    }
}

package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothCsipSetCoordinator;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothUuid;
import android.os.ParcelUuid;
import android.util.Log;
import com.android.systemui.keyboard.KeyboardUI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CsipDeviceManager {
    public final LocalBluetoothManager mBtManager;
    public final List mCachedDevices;

    public CsipDeviceManager(LocalBluetoothManager localBluetoothManager, List list) {
        this.mBtManager = localBluetoothManager;
        this.mCachedDevices = list;
    }

    public static boolean isDeviceConnected(CachedBluetoothDevice cachedBluetoothDevice) {
        if (cachedBluetoothDevice == null) {
            return false;
        }
        BluetoothDevice bluetoothDevice = cachedBluetoothDevice.mDevice;
        return cachedBluetoothDevice.isConnected() && bluetoothDevice.getBondState() == 12 && bluetoothDevice.isConnected();
    }

    public static boolean isValidGroupId(int i) {
        return i != -1;
    }

    public static void log(String str) {
        Log.d("CsipDeviceManager", str);
    }

    public boolean addMemberDevicesIntoMainDevice(final int i, CachedBluetoothDevice cachedBluetoothDevice) {
        boolean z;
        if (cachedBluetoothDevice == null) {
            log("addMemberDevicesIntoMainDevice: No main device. Do nothing.");
            return false;
        }
        BluetoothDevice bluetoothDevice = cachedBluetoothDevice.mDevice;
        CachedBluetoothDevice findMainDevice = findMainDevice(cachedBluetoothDevice);
        LocalBluetoothManager localBluetoothManager = this.mBtManager;
        if (findMainDevice == null) {
            z = false;
        } else {
            log("addMemberDevicesIntoMainDevice: The PreferredMainDevice have the mainDevice. Do switch relationship between the mainDeviceOfPreferredMainDevice and PreferredMainDevice");
            localBluetoothManager.mEventManager.dispatchDeviceRemoved(findMainDevice);
            findMainDevice.switchMemberDeviceContent(cachedBluetoothDevice);
            findMainDevice.refresh();
            localBluetoothManager.mEventManager.dispatchDeviceAdded(findMainDevice);
            z = true;
        }
        List<CachedBluetoothDevice> list = (List) this.mCachedDevices.stream().filter(new Predicate() { // from class: com.android.settingslib.bluetooth.CsipDeviceManager$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((CachedBluetoothDevice) obj).mGroupId == i;
            }
        }).collect(Collectors.toList());
        boolean z2 = list.size() > 1;
        CachedBluetoothDevice findDevice = localBluetoothManager.mCachedDeviceManager.findDevice(bluetoothDevice);
        if (z2) {
            log("addMemberDevicesIntoMainDevice: haveMultiMainDevicesInAllOfDevicesList. Combine them and also keep the preferred main device as main device.");
            for (CachedBluetoothDevice cachedBluetoothDevice2 : list) {
                BluetoothDevice bluetoothDevice2 = cachedBluetoothDevice2.mDevice;
                if (bluetoothDevice2 != null && !bluetoothDevice2.equals(bluetoothDevice)) {
                    HashSet hashSet = (HashSet) cachedBluetoothDevice2.mMemberDevices;
                    Iterator it = hashSet.iterator();
                    while (it.hasNext()) {
                        CachedBluetoothDevice cachedBluetoothDevice3 = (CachedBluetoothDevice) it.next();
                        if (!cachedBluetoothDevice3.equals(findDevice)) {
                            findDevice.addMemberDevice(cachedBluetoothDevice3);
                        }
                    }
                    hashSet.clear();
                    findDevice.addMemberDevice(cachedBluetoothDevice2);
                    this.mCachedDevices.remove(cachedBluetoothDevice2);
                    localBluetoothManager.mEventManager.dispatchDeviceRemoved(cachedBluetoothDevice2);
                    findDevice.refresh();
                    z = true;
                }
            }
            KeyboardUI.BluetoothErrorListener bluetoothErrorListener = BluetoothUtils.sErrorListener;
            BluetoothAdapter.getDefaultAdapter();
        }
        if (z) {
            log("addMemberDevicesIntoMainDevice: After changed, CachedBluetoothDevice list: " + this.mCachedDevices);
        }
        return z;
    }

    public final CachedBluetoothDevice findMainDevice(CachedBluetoothDevice cachedBluetoothDevice) {
        for (CachedBluetoothDevice cachedBluetoothDevice2 : this.mCachedDevices) {
            if (isValidGroupId(cachedBluetoothDevice2.mGroupId)) {
                Set set = cachedBluetoothDevice2.mMemberDevices;
                if (set.isEmpty()) {
                    continue;
                } else {
                    Iterator it = ((HashSet) set).iterator();
                    while (it.hasNext()) {
                        CachedBluetoothDevice cachedBluetoothDevice3 = (CachedBluetoothDevice) it.next();
                        if (cachedBluetoothDevice3 != null && cachedBluetoothDevice3.equals(cachedBluetoothDevice)) {
                            return cachedBluetoothDevice2;
                        }
                    }
                }
            }
        }
        return null;
    }

    public final int getBaseGroupId(BluetoothDevice bluetoothDevice) {
        CsipSetCoordinatorProfile csipSetCoordinatorProfile = this.mBtManager.mProfileManager.mCsipSetCoordinatorProfile;
        if (csipSetCoordinatorProfile != null) {
            BluetoothCsipSetCoordinator bluetoothCsipSetCoordinator = csipSetCoordinatorProfile.mService;
            Map groupUuidMapByDevice = (bluetoothCsipSetCoordinator == null || bluetoothDevice == null) ? null : bluetoothCsipSetCoordinator.getGroupUuidMapByDevice(bluetoothDevice);
            if (groupUuidMapByDevice == null) {
                return -1;
            }
            for (Map.Entry entry : groupUuidMapByDevice.entrySet()) {
                if (((ParcelUuid) entry.getValue()).equals(BluetoothUuid.CAP)) {
                    return ((Integer) entry.getKey()).intValue();
                }
            }
        }
        return -1;
    }

    public List getGroupDevicesFromAllOfDevicesList(int i) {
        ArrayList arrayList = new ArrayList();
        if (!isValidGroupId(i)) {
            return arrayList;
        }
        for (CachedBluetoothDevice cachedBluetoothDevice : this.mCachedDevices) {
            if (i == cachedBluetoothDevice.mGroupId) {
                arrayList.add(cachedBluetoothDevice);
                arrayList.addAll(cachedBluetoothDevice.mMemberDevices);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.android.settingslib.bluetooth.CachedBluetoothDevice getPreferredMainDevice(int r7, java.util.List r8) {
        /*
            r6 = this;
            r0 = 0
            if (r8 == 0) goto Lc7
            boolean r1 = r8.isEmpty()
            if (r1 == 0) goto Lb
            goto Lc7
        Lb:
            java.util.stream.Stream r1 = r8.stream()
            com.android.settingslib.bluetooth.CsipDeviceManager$$ExternalSyntheticLambda1 r2 = new com.android.settingslib.bluetooth.CsipDeviceManager$$ExternalSyntheticLambda1
            r3 = 0
            r2.<init>(r3)
            java.util.stream.Stream r1 = r1.filter(r2)
            com.android.settingslib.bluetooth.CsipDeviceManager$$ExternalSyntheticLambda1 r2 = new com.android.settingslib.bluetooth.CsipDeviceManager$$ExternalSyntheticLambda1
            r3 = 1
            r2.<init>(r3)
            java.util.stream.Stream r1 = r1.filter(r2)
            java.util.Optional r1 = r1.findFirst()
            java.lang.Object r1 = r1.orElse(r0)
            com.android.settingslib.bluetooth.CachedBluetoothDevice r1 = (com.android.settingslib.bluetooth.CachedBluetoothDevice) r1
            boolean r2 = isDeviceConnected(r1)
            if (r2 == 0) goto L39
            java.lang.String r6 = "getPreferredMainDevice: The connected DUAL mode device"
            log(r6)
            return r1
        L39:
            com.android.settingslib.bluetooth.LocalBluetoothManager r2 = r6.mBtManager
            com.android.settingslib.bluetooth.LocalBluetoothProfileManager r3 = r2.mProfileManager
            com.android.settingslib.bluetooth.LeAudioProfile r3 = r3.mLeAudioProfile
            if (r3 == 0) goto L57
            java.lang.String r4 = "getConnectedGroupLeadDevice"
            java.lang.String r5 = "LeAudioProfile"
            android.util.Log.d(r5, r4)
            android.bluetooth.BluetoothLeAudio r3 = r3.mService
            if (r3 != 0) goto L52
            java.lang.String r7 = "No service."
            android.util.Log.e(r5, r7)
            goto L57
        L52:
            android.bluetooth.BluetoothDevice r7 = r3.getConnectedGroupLeadDevice(r7)
            goto L58
        L57:
            r7 = r0
        L58:
            if (r7 == 0) goto L6f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "getPreferredMainDevice: The LeadDevice from LE profile is "
            r3.<init>(r4)
            java.lang.String r4 = r7.getAnonymizedAddress()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            log(r3)
        L6f:
            if (r7 == 0) goto L78
            com.android.settingslib.bluetooth.CachedBluetoothDeviceManager r2 = r2.mCachedDeviceManager
            com.android.settingslib.bluetooth.CachedBluetoothDevice r7 = r2.findDevice(r7)
            goto L79
        L78:
            r7 = r0
        L79:
            if (r7 != 0) goto L81
            java.lang.String r7 = "getPreferredMainDevice: The LeadDevice is not in the all of devices list"
            log(r7)
            goto L8d
        L81:
            boolean r2 = isDeviceConnected(r7)
            if (r2 == 0) goto L8d
            java.lang.String r6 = "getPreferredMainDevice: The connected LeadDevice from LE profile"
            log(r6)
            return r7
        L8d:
            java.util.stream.Stream r7 = r8.stream()
            com.android.settingslib.bluetooth.CsipDeviceManager$$ExternalSyntheticLambda3 r2 = new com.android.settingslib.bluetooth.CsipDeviceManager$$ExternalSyntheticLambda3
            r2.<init>(r6)
            java.util.stream.Stream r6 = r7.filter(r2)
            java.util.Optional r6 = r6.findFirst()
            java.lang.Object r6 = r6.orElse(r0)
            com.android.settingslib.bluetooth.CachedBluetoothDevice r6 = (com.android.settingslib.bluetooth.CachedBluetoothDevice) r6
            if (r6 == 0) goto Lac
            java.lang.String r7 = "getPreferredMainDevice: One of the connected devices."
            log(r7)
            return r6
        Lac:
            if (r1 == 0) goto Lb4
            java.lang.String r6 = "getPreferredMainDevice: The DUAL mode device."
            log(r6)
            return r1
        Lb4:
            boolean r6 = r8.isEmpty()
            if (r6 != 0) goto Lc7
            java.lang.String r6 = "getPreferredMainDevice: One of the group devices."
            log(r6)
            r6 = 0
            java.lang.Object r6 = r8.get(r6)
            com.android.settingslib.bluetooth.CachedBluetoothDevice r6 = (com.android.settingslib.bluetooth.CachedBluetoothDevice) r6
            return r6
        Lc7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.bluetooth.CsipDeviceManager.getPreferredMainDevice(int, java.util.List):com.android.settingslib.bluetooth.CachedBluetoothDevice");
    }

    public final void initCsipDeviceIfNeeded(CachedBluetoothDevice cachedBluetoothDevice) {
        int baseGroupId = getBaseGroupId(cachedBluetoothDevice.mDevice);
        if (isValidGroupId(baseGroupId)) {
            log("initCsipDeviceIfNeeded: " + cachedBluetoothDevice + " (group: " + baseGroupId + ")");
            cachedBluetoothDevice.setGroupId(baseGroupId);
        }
    }

    public void onGroupIdChanged(int i) {
        if (isValidGroupId(i)) {
            updateRelationshipOfGroupDevices(i);
        } else {
            log("onGroupIdChanged: groupId is invalid");
        }
    }

    public final boolean setMemberDeviceIfNeeded(CachedBluetoothDevice cachedBluetoothDevice) {
        CachedBluetoothDevice cachedBluetoothDevice2;
        int i = cachedBluetoothDevice.mGroupId;
        if (!isValidGroupId(i)) {
            return false;
        }
        log("getCachedDevice: groupId: " + i);
        int size = ((ArrayList) this.mCachedDevices).size() - 1;
        while (true) {
            if (size < 0) {
                cachedBluetoothDevice2 = null;
                break;
            }
            cachedBluetoothDevice2 = (CachedBluetoothDevice) ((ArrayList) this.mCachedDevices).get(size);
            if (cachedBluetoothDevice2.mGroupId == i) {
                log("getCachedDevice: found cachedDevice with the groupId: " + cachedBluetoothDevice2.mDevice.getAnonymizedAddress());
                break;
            }
            size--;
        }
        log("setMemberDeviceIfNeeded, main: " + cachedBluetoothDevice2 + ", member: " + cachedBluetoothDevice);
        if (cachedBluetoothDevice2 == null) {
            return false;
        }
        cachedBluetoothDevice2.addMemberDevice(cachedBluetoothDevice);
        cachedBluetoothDevice.setName(cachedBluetoothDevice2.getName());
        return true;
    }

    public final void updateCsipDevices() {
        HashSet hashSet = new HashSet();
        for (CachedBluetoothDevice cachedBluetoothDevice : this.mCachedDevices) {
            if (!isValidGroupId(cachedBluetoothDevice.mGroupId)) {
                int baseGroupId = getBaseGroupId(cachedBluetoothDevice.mDevice);
                if (isValidGroupId(baseGroupId)) {
                    cachedBluetoothDevice.setGroupId(baseGroupId);
                    hashSet.add(Integer.valueOf(baseGroupId));
                }
            }
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            onGroupIdChanged(((Integer) it.next()).intValue());
        }
    }

    public boolean updateRelationshipOfGroupDevices(int i) {
        if (!isValidGroupId(i)) {
            log("The device is not group.");
            return false;
        }
        log("updateRelationshipOfGroupDevices: mCachedDevices list =" + this.mCachedDevices.toString());
        List groupDevicesFromAllOfDevicesList = getGroupDevicesFromAllOfDevicesList(i);
        CachedBluetoothDevice preferredMainDevice = getPreferredMainDevice(i, groupDevicesFromAllOfDevicesList);
        log("The preferredMainDevice= " + preferredMainDevice + " and the groupDevicesList of groupId= " + i + " =" + groupDevicesFromAllOfDevicesList);
        return addMemberDevicesIntoMainDevice(i, preferredMainDevice);
    }
}

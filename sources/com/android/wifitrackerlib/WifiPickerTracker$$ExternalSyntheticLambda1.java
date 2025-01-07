package com.android.wifitrackerlib;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.net.wifi.sharedconnectivity.app.HotspotNetwork;
import android.net.wifi.sharedconnectivity.app.KnownNetwork;
import com.android.wifitrackerlib.StandardWifiEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WifiPickerTracker$$ExternalSyntheticLambda1 implements Function {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ WifiPickerTracker$$ExternalSyntheticLambda1(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return new StandardWifiEntry.ScanResultKey((ScanResult) obj);
            case 1:
                return ((List) obj).stream();
            case 2:
                return Integer.valueOf(((WifiConfiguration) obj).networkId);
            case 3:
                return ((StandardWifiEntry) obj).mKey.mScanResultKey;
            case 4:
                KnownNetwork knownNetwork = (KnownNetwork) obj;
                return new StandardWifiEntry.ScanResultKey(knownNetwork.getSsid(), new ArrayList(knownNetwork.getSecurityTypes()));
            case 5:
                return Long.valueOf(((HotspotNetwork) obj).getDeviceId());
            case 6:
                return (HotspotNetwork) obj;
            case 7:
                return new StandardWifiEntry.StandardWifiEntryKey((WifiConfiguration) obj, false);
            case 8:
                return (KnownNetwork) obj;
            case 9:
                return PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(((PasspointConfiguration) obj).getUniqueId());
            default:
                return Integer.valueOf(((WifiConfiguration) obj).networkId);
        }
    }
}

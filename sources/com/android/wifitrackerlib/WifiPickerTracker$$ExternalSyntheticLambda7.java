package com.android.wifitrackerlib;

import android.net.wifi.hotspot2.PasspointConfiguration;
import android.net.wifi.sharedconnectivity.app.HotspotNetwork;
import android.util.ArrayMap;
import com.android.wifitrackerlib.HotspotNetworkEntry;
import com.android.wifitrackerlib.StandardWifiEntry;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WifiPickerTracker$$ExternalSyntheticLambda7 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Map f$1;

    public /* synthetic */ WifiPickerTracker$$ExternalSyntheticLambda7(Object obj, Map map, int i) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = map;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                Set set = (Set) this.f$0;
                Map map = this.f$1;
                KnownNetworkEntry knownNetworkEntry = (KnownNetworkEntry) obj;
                StandardWifiEntry.ScanResultKey scanResultKey = knownNetworkEntry.mKey.mScanResultKey;
                set.remove(scanResultKey);
                knownNetworkEntry.updateScanResultInfo((List) map.get(scanResultKey));
                return;
            case 1:
                Set set2 = (Set) this.f$0;
                Map map2 = this.f$1;
                HotspotNetworkEntry hotspotNetworkEntry = (HotspotNetworkEntry) obj;
                Long valueOf = Long.valueOf(hotspotNetworkEntry.mKey.mDeviceId);
                set2.remove(valueOf);
                HotspotNetwork hotspotNetwork = (HotspotNetwork) map2.get(valueOf);
                synchronized (hotspotNetworkEntry) {
                    hotspotNetworkEntry.mHotspotNetworkData = hotspotNetwork;
                    hotspotNetworkEntry.mKey = new HotspotNetworkEntry.HotspotNetworkEntryKey(hotspotNetwork);
                    hotspotNetworkEntry.notifyOnUpdated();
                }
                return;
            case 2:
                Set set3 = (Set) this.f$0;
                Map map3 = this.f$1;
                StandardWifiEntry standardWifiEntry = (StandardWifiEntry) obj;
                StandardWifiEntry.ScanResultKey scanResultKey2 = standardWifiEntry.mKey.mScanResultKey;
                set3.remove(scanResultKey2);
                standardWifiEntry.updateScanResultInfo((List) map3.get(scanResultKey2));
                return;
            default:
                WifiPickerTracker wifiPickerTracker = (WifiPickerTracker) this.f$0;
                Map map4 = this.f$1;
                OsuWifiEntry osuWifiEntry = (OsuWifiEntry) obj;
                wifiPickerTracker.getClass();
                PasspointConfiguration passpointConfiguration = (PasspointConfiguration) map4.get(osuWifiEntry.mOsuProvider);
                if (passpointConfiguration == null) {
                    synchronized (osuWifiEntry) {
                        osuWifiEntry.mIsAlreadyProvisioned = false;
                    }
                    return;
                }
                synchronized (osuWifiEntry) {
                    osuWifiEntry.mIsAlreadyProvisioned = true;
                }
                PasspointWifiEntry passpointWifiEntry = (PasspointWifiEntry) ((ArrayMap) wifiPickerTracker.mPasspointWifiEntryCache).get(PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(passpointConfiguration.getUniqueId()));
                if (passpointWifiEntry == null) {
                    return;
                }
                synchronized (passpointWifiEntry) {
                    passpointWifiEntry.mOsuWifiEntry = osuWifiEntry;
                    synchronized (osuWifiEntry) {
                        osuWifiEntry.mListener = passpointWifiEntry;
                    }
                }
                return;
        }
    }
}

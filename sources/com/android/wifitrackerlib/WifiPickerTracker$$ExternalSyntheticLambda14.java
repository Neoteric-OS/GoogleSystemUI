package com.android.wifitrackerlib;

import android.net.wifi.sharedconnectivity.app.HotspotNetworkConnectionStatus;
import android.net.wifi.sharedconnectivity.app.KnownNetworkConnectionStatus;
import android.util.ArrayMap;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WifiPickerTracker$$ExternalSyntheticLambda14 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ WifiPickerTracker$$ExternalSyntheticLambda14(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                StandardWifiEntry standardWifiEntry = (StandardWifiEntry) obj;
                standardWifiEntry.updateConfig((List) ((ArrayMap) ((WifiPickerTracker) obj2).mStandardWifiConfigCache).get(standardWifiEntry.mKey));
                break;
            case 1:
                KnownNetworkEntry knownNetworkEntry = (KnownNetworkEntry) obj;
                if (((KnownNetworkConnectionStatus) obj2).getStatus() != 2) {
                    knownNetworkEntry.getClass();
                    break;
                } else if (knownNetworkEntry.mConnectCallback != null) {
                    knownNetworkEntry.mCallbackHandler.post(new KnownNetworkEntry$$ExternalSyntheticLambda0(0, knownNetworkEntry));
                    break;
                }
                break;
            default:
                HotspotNetworkConnectionStatus hotspotNetworkConnectionStatus = (HotspotNetworkConnectionStatus) obj2;
                HotspotNetworkEntry hotspotNetworkEntry = (HotspotNetworkEntry) obj;
                if (!hotspotNetworkConnectionStatus.getExtras().getBoolean("connection_status_connected", false)) {
                    hotspotNetworkEntry.onConnectionStatusChanged(hotspotNetworkConnectionStatus.getStatus());
                    break;
                } else {
                    hotspotNetworkEntry.onConnectionStatusChanged(10);
                    break;
                }
        }
    }
}

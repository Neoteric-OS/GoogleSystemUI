package com.android.wifitrackerlib;

import android.net.wifi.sharedconnectivity.app.HotspotNetwork;
import com.android.app.viewcapture.data.ViewNode;
import java.net.InetAddress;
import java.util.function.Function;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WifiEntry$$ExternalSyntheticLambda0 implements Function {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ WifiEntry$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return Boolean.valueOf(!((WifiEntry) obj).isPrimaryNetwork());
            case 1:
                return Boolean.valueOf(!((WifiEntry) obj).isSuggestion());
            case 2:
                return Integer.valueOf(-((WifiEntry) obj).getLevel());
            case 3:
                return ((InetAddress) obj).getHostAddress();
            case 4:
                return ((WifiEntry) obj).getTitle();
            case 5:
                return ((WifiEntry) obj).getTitle();
            case 6:
                return Boolean.valueOf(((WifiEntry) obj).getConnectedState() != 2);
            case 7:
                return Boolean.valueOf(!(((WifiEntry) obj) instanceof KnownNetworkEntry));
            case 8:
                return Boolean.valueOf(!(((WifiEntry) obj) instanceof HotspotNetworkEntry));
            case 9:
                WifiEntry wifiEntry = (WifiEntry) obj;
                int i = 0;
                if (wifiEntry instanceof HotspotNetworkEntry) {
                    HotspotNetworkEntry hotspotNetworkEntry = (HotspotNetworkEntry) wifiEntry;
                    synchronized (hotspotNetworkEntry) {
                        HotspotNetwork hotspotNetwork = hotspotNetworkEntry.mHotspotNetworkData;
                        if (hotspotNetwork != null) {
                            i = hotspotNetwork.getNetworkProviderInfo().getConnectionStrength();
                        }
                    }
                    i = -i;
                }
                return Integer.valueOf(i);
            case 10:
                return Boolean.valueOf(!((WifiEntry) obj).canConnect());
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return Boolean.valueOf(!((WifiEntry) obj).isSubscription());
            default:
                return Boolean.valueOf(!((WifiEntry) obj).isSaved());
        }
    }
}

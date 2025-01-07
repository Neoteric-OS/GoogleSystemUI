package com.android.wifitrackerlib;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.text.TextUtils;
import com.android.app.viewcapture.data.ViewNode;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WifiPickerTracker$$ExternalSyntheticLambda0 implements Predicate {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ WifiPickerTracker$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        boolean z;
        boolean z2;
        switch (this.$r8$classId) {
            case 0:
                return !TextUtils.isEmpty(((ScanResult) obj).SSID);
            case 1:
                return !((WifiConfiguration) obj).isEphemeral();
            case 2:
                return ((WifiEntry) obj).getConnectedState() == 0;
            case 3:
                return ((HotspotNetworkEntry) obj).getConnectedState() == 0;
            case 4:
                StandardWifiEntry standardWifiEntry = (StandardWifiEntry) obj;
                if (standardWifiEntry.getConnectedState() == 0) {
                    synchronized (standardWifiEntry) {
                        z = standardWifiEntry.mIsUserShareable;
                    }
                    if (z) {
                        return true;
                    }
                }
                return false;
            case 5:
                return ((PasspointWifiEntry) obj).getConnectedState() == 0;
            case 6:
                OsuWifiEntry osuWifiEntry = (OsuWifiEntry) obj;
                if (osuWifiEntry.getConnectedState() == 0) {
                    synchronized (osuWifiEntry) {
                        z2 = osuWifiEntry.mIsAlreadyProvisioned;
                    }
                    if (!z2) {
                        return true;
                    }
                }
                return false;
            case 7:
                return ((WifiEntry) obj).getConnectedState() == 0;
            case 8:
                return !TextUtils.isEmpty(((ScanResult) obj).SSID);
            case 9:
                return ((StandardWifiEntry) obj).getLevel() == -1;
            case 10:
                return ((OsuWifiEntry) ((Map.Entry) obj).getValue()).getLevel() == -1;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                return !TextUtils.isEmpty(((ScanResult) obj).SSID);
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return ((StandardWifiEntry) obj).getLevel() == -1;
            default:
                return ((KnownNetworkEntry) obj).getLevel() == -1;
        }
    }
}

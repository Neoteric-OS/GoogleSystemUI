package com.android.wifitrackerlib;

import android.net.wifi.sharedconnectivity.app.HotspotNetworkConnectionStatus;
import java.util.List;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WifiPickerTracker$$ExternalSyntheticLambda18 implements Predicate {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ WifiPickerTracker$$ExternalSyntheticLambda18(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        boolean z;
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                return ((HotspotNetworkEntry) obj).mKey.mDeviceId == ((HotspotNetworkConnectionStatus) obj2).getHotspotNetwork().getDeviceId();
            default:
                List list = (List) obj2;
                StandardWifiEntry standardWifiEntry = (StandardWifiEntry) obj;
                synchronized (standardWifiEntry) {
                    z = standardWifiEntry.mIsUserShareable;
                }
                if (z) {
                    return true;
                }
                return list.contains(standardWifiEntry);
        }
    }
}

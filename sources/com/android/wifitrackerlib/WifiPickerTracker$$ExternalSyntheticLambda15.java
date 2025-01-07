package com.android.wifitrackerlib;

import android.net.wifi.hotspot2.PasspointConfiguration;
import android.util.ArrayMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WifiPickerTracker$$ExternalSyntheticLambda15 implements Predicate {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ WifiPickerTracker f$0;

    public /* synthetic */ WifiPickerTracker$$ExternalSyntheticLambda15(WifiPickerTracker wifiPickerTracker, int i) {
        this.$r8$classId = i;
        this.f$0 = wifiPickerTracker;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        int i = this.$r8$classId;
        WifiPickerTracker wifiPickerTracker = this.f$0;
        switch (i) {
            case 0:
                StandardWifiEntry standardWifiEntry = (StandardWifiEntry) obj;
                standardWifiEntry.updateConfig((List) ((ArrayMap) wifiPickerTracker.mSuggestedConfigCache).get(standardWifiEntry.mKey));
                return !standardWifiEntry.isSuggestion();
            default:
                wifiPickerTracker.getClass();
                PasspointWifiEntry passpointWifiEntry = (PasspointWifiEntry) ((Map.Entry) obj).getValue();
                PasspointConfiguration passpointConfiguration = (PasspointConfiguration) ((ArrayMap) wifiPickerTracker.mPasspointConfigCache).get(passpointWifiEntry.mKey);
                synchronized (passpointWifiEntry) {
                    try {
                        passpointWifiEntry.mPasspointConfig = passpointConfiguration;
                        if (passpointConfiguration != null) {
                            passpointWifiEntry.mSubscriptionExpirationTimeInMillis = passpointConfiguration.getSubscriptionExpirationTimeMillis();
                            passpointWifiEntry.mMeteredOverride = passpointConfiguration.getMeteredOverride();
                        }
                        passpointWifiEntry.notifyOnUpdated();
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return (passpointWifiEntry.isSubscription() || passpointWifiEntry.isSuggestion()) ? false : true;
        }
    }
}

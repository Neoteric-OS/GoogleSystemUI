package com.android.wifitrackerlib;

import com.android.wifitrackerlib.WifiPickerTracker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BaseWifiTracker$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ WifiPickerTracker.WifiPickerTrackerCallback f$0;

    public /* synthetic */ BaseWifiTracker$$ExternalSyntheticLambda3(WifiPickerTracker.WifiPickerTrackerCallback wifiPickerTrackerCallback, int i) {
        this.$r8$classId = i;
        this.f$0 = wifiPickerTrackerCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        WifiPickerTracker.WifiPickerTrackerCallback wifiPickerTrackerCallback = this.f$0;
        switch (i) {
            case 0:
                wifiPickerTrackerCallback.onWifiStateChanged();
                break;
            default:
                wifiPickerTrackerCallback.onScanRequested();
                break;
        }
    }
}

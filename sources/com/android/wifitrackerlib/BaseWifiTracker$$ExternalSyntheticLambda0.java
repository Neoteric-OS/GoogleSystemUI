package com.android.wifitrackerlib;

import android.content.Intent;
import android.net.wifi.WifiScanner;
import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import com.android.wifitrackerlib.BaseWifiTracker$Scanner;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BaseWifiTracker$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ BaseWifiTracker$$ExternalSyntheticLambda0(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ((Lifecycle) this.f$1).addObserver(((WifiPickerTracker) this.f$0).mLifecycleObserver);
                break;
            default:
                BaseWifiTracker$Scanner.AnonymousClass1 anonymousClass1 = (BaseWifiTracker$Scanner.AnonymousClass1) this.f$0;
                WifiScanner.ScanData[] scanDataArr = (WifiScanner.ScanData[]) this.f$1;
                if (BaseWifiTracker$Scanner.this.shouldScan()) {
                    if (BaseWifiTracker$Scanner.this.this$0.isVerboseLoggingEnabled()) {
                        WifiPickerTracker wifiPickerTracker = BaseWifiTracker$Scanner.this.this$0;
                    }
                    ArrayList arrayList = new ArrayList();
                    if (scanDataArr != null) {
                        for (WifiScanner.ScanData scanData : scanDataArr) {
                            arrayList.addAll(List.of((Object[]) scanData.getResults()));
                        }
                    }
                    BaseWifiTracker$Scanner.this.this$0.mScanResultUpdater.update(arrayList);
                    WifiPickerTracker wifiPickerTracker2 = BaseWifiTracker$Scanner.this.this$0;
                    Intent putExtra = new Intent("android.net.wifi.SCAN_RESULTS").putExtra("resultsUpdated", true);
                    Preconditions.checkNotNull(putExtra, "Intent cannot be null!");
                    wifiPickerTracker2.conditionallyUpdateScanResults(putExtra.getBooleanExtra("resultsUpdated", true));
                    wifiPickerTracker2.updateWifiEntries(1);
                    BaseWifiTracker$Scanner.this.scanLoop();
                    break;
                }
                break;
        }
    }
}

package com.android.wifitrackerlib;

import android.util.Log;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BaseWifiTracker$Scanner$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BaseWifiTracker$Scanner f$0;

    public /* synthetic */ BaseWifiTracker$Scanner$$ExternalSyntheticLambda0(BaseWifiTracker$Scanner baseWifiTracker$Scanner, int i) {
        this.$r8$classId = i;
        this.f$0 = baseWifiTracker$Scanner;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        BaseWifiTracker$Scanner baseWifiTracker$Scanner = this.f$0;
        switch (i) {
            case 0:
                Log.i(baseWifiTracker$Scanner.this$0.mTag, "Scanning stopped");
                baseWifiTracker$Scanner.removeCallbacksAndMessages(null);
                break;
            case 1:
                baseWifiTracker$Scanner.possiblyStartScanning();
                break;
            default:
                baseWifiTracker$Scanner.scanLoop();
                break;
        }
    }
}

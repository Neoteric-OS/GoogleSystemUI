package com.android.settingslib.wifi;

import com.android.settingslib.wifi.WifiStatusTracker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class WifiStatusTracker$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ WifiStatusTracker.AnonymousClass1 f$0;

    public /* synthetic */ WifiStatusTracker$1$$ExternalSyntheticLambda0(WifiStatusTracker.AnonymousClass1 anonymousClass1, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        WifiStatusTracker.AnonymousClass1 anonymousClass1 = this.f$0;
        switch (i) {
            case 0:
                anonymousClass1.this$0.postResults();
                break;
            default:
                anonymousClass1.this$0.postResults();
                break;
        }
    }
}

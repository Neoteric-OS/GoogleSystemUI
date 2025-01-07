package com.android.wifitrackerlib;

import com.android.systemui.qs.tiles.dialog.InternetDialogController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class HotspotNetworkEntry$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ HotspotNetworkEntry$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                InternetDialogController.WifiEntryConnectCallback wifiEntryConnectCallback = ((HotspotNetworkEntry) obj).mConnectCallback;
                if (wifiEntryConnectCallback != null) {
                    wifiEntryConnectCallback.onConnectResult(2);
                    break;
                }
                break;
            case 1:
                InternetDialogController.WifiEntryConnectCallback wifiEntryConnectCallback2 = ((HotspotNetworkEntry) obj).mConnectCallback;
                if (wifiEntryConnectCallback2 != null) {
                    wifiEntryConnectCallback2.onConnectResult(0);
                    break;
                }
                break;
            default:
                ((InternetDialogController.WifiEntryConnectCallback) obj).onConnectResult(2);
                break;
        }
    }
}

package com.android.wifitrackerlib;

import com.android.systemui.qs.tiles.dialog.InternetDialogController;
import com.android.wifitrackerlib.WifiEntry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WifiEntry$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ WifiEntry$$ExternalSyntheticLambda12(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                WifiEntry.WifiEntryCallback wifiEntryCallback = ((WifiEntry) obj).mListener;
                if (wifiEntryCallback != null) {
                    wifiEntryCallback.onUpdated();
                    break;
                }
                break;
            case 1:
                InternetDialogController.WifiEntryConnectCallback wifiEntryConnectCallback = ((WifiEntry) obj).mConnectCallback;
                if (wifiEntryConnectCallback != null) {
                    wifiEntryConnectCallback.onConnectResult(0);
                    break;
                }
                break;
            default:
                InternetDialogController.WifiEntryConnectCallback wifiEntryConnectCallback2 = WifiEntry.this.mConnectCallback;
                if (wifiEntryConnectCallback2 != null) {
                    wifiEntryConnectCallback2.onConnectResult(2);
                    break;
                }
                break;
        }
    }
}

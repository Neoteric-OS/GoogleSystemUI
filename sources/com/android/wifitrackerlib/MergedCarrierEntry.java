package com.android.wifitrackerlib;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.systemui.qs.tiles.dialog.InternetDialogController;
import com.android.wm.shell.R;
import java.util.StringJoiner;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MergedCarrierEntry extends WifiEntry {
    public final String mKey;
    public final int mSubscriptionId;

    public MergedCarrierEntry(WifiTrackerInjector wifiTrackerInjector, Handler handler, WifiManager wifiManager, int i) {
        super(wifiTrackerInjector, handler, wifiManager);
        this.mSubscriptionId = i;
        this.mKey = AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "MergedCarrierEntry:");
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean canConnect() {
        boolean z;
        synchronized (this) {
            z = getConnectedState() == 0;
        }
        return z;
        return z;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final boolean canDisconnect() {
        return getConnectedState() == 2;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized void connect(InternetDialogController.WifiEntryConnectCallback wifiEntryConnectCallback) {
        connect(wifiEntryConnectCallback, true);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final boolean connectionInfoMatches(WifiInfo wifiInfo) {
        return wifiInfo.isCarrierMerged() && this.mSubscriptionId == wifiInfo.getSubscriptionId();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String getKey() {
        return this.mKey;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final synchronized String getSsid() {
        WifiInfo wifiInfo = this.mWifiInfo;
        if (wifiInfo == null) {
            return null;
        }
        return WifiInfo.sanitizeSsid(wifiInfo.getSSID());
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String getSummary(boolean z) {
        StringJoiner stringJoiner = new StringJoiner(this.mContext.getString(R.string.wifitrackerlib_summary_separator));
        if (!z && isVerboseSummaryEnabled()) {
            String verboseSummary = Utils.getVerboseSummary(this);
            if (!TextUtils.isEmpty(verboseSummary)) {
                stringJoiner.add(verboseSummary);
            }
        }
        return stringJoiner.toString();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public final String toString() {
        StringJoiner stringJoiner = new StringJoiner("][", "[", "]");
        stringJoiner.add("SubId:" + this.mSubscriptionId);
        return super.toString() + stringJoiner;
    }

    public final synchronized void connect(InternetDialogController.WifiEntryConnectCallback wifiEntryConnectCallback, boolean z) {
        try {
            this.mConnectCallback = wifiEntryConnectCallback;
            this.mWifiManager.startRestrictingAutoJoinToSubscriptionId(this.mSubscriptionId);
            if (z) {
                Toast.makeText(this.mContext, R.string.wifitrackerlib_wifi_wont_autoconnect_for_now, 0).show();
            }
            if (this.mConnectCallback != null) {
                this.mCallbackHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.MergedCarrierEntry$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        InternetDialogController.WifiEntryConnectCallback wifiEntryConnectCallback2 = MergedCarrierEntry.this.mConnectCallback;
                        if (wifiEntryConnectCallback2 != null) {
                            wifiEntryConnectCallback2.onConnectResult(0);
                        }
                    }
                });
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}

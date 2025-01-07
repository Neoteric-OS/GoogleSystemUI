package com.android.wifitrackerlib;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.sharedconnectivity.app.KnownNetwork;
import android.net.wifi.sharedconnectivity.app.SharedConnectivityManager;
import android.os.Handler;
import android.text.BidiFormatter;
import com.android.systemui.qs.tiles.dialog.InternetDialogController;
import com.android.wifitrackerlib.StandardWifiEntry;
import com.android.wm.shell.R;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class KnownNetworkEntry extends StandardWifiEntry {
    public final KnownNetwork mKnownNetworkData;
    public final SharedConnectivityManager mSharedConnectivityManager;

    public KnownNetworkEntry(WifiTrackerInjector wifiTrackerInjector, Handler handler, StandardWifiEntry.StandardWifiEntryKey standardWifiEntryKey, List list, WifiManager wifiManager, SharedConnectivityManager sharedConnectivityManager, KnownNetwork knownNetwork) {
        super(wifiTrackerInjector, handler, standardWifiEntryKey, null, list, wifiManager);
        this.mSharedConnectivityManager = sharedConnectivityManager;
        this.mKnownNetworkData = knownNetwork;
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public final synchronized void connect(InternetDialogController.WifiEntryConnectCallback wifiEntryConnectCallback) {
        this.mConnectCallback = wifiEntryConnectCallback;
        SharedConnectivityManager sharedConnectivityManager = this.mSharedConnectivityManager;
        if (sharedConnectivityManager == null) {
            this.mCallbackHandler.post(new KnownNetworkEntry$$ExternalSyntheticLambda0(1, wifiEntryConnectCallback));
        } else {
            sharedConnectivityManager.connectKnownNetwork(this.mKnownNetworkData);
        }
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean connectionInfoMatches(WifiInfo wifiInfo) {
        if (!wifiInfo.isPasspointAp() && !wifiInfo.isOsuAp()) {
            return Objects.equals(this.mKey.mScanResultKey, new StandardWifiEntry.ScanResultKey(WifiInfo.sanitizeSsid(wifiInfo.getSSID()), Collections.singletonList(Integer.valueOf(wifiInfo.getCurrentSecurityType()))));
        }
        return false;
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public final synchronized String getSummary(boolean z) {
        return this.mContext.getString(R.string.wifitrackerlib_known_network_summary, BidiFormatter.getInstance().unicodeWrap(this.mKnownNetworkData.getNetworkProviderInfo().getDeviceName()));
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean isSaved() {
        return false;
    }

    @Override // com.android.wifitrackerlib.StandardWifiEntry, com.android.wifitrackerlib.WifiEntry
    public final synchronized boolean isSuggestion() {
        return false;
    }
}

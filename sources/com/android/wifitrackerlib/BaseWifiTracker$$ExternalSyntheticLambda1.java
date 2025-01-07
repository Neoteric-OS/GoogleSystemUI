package com.android.wifitrackerlib;

import android.content.Context;
import android.content.IntentFilter;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.sharedconnectivity.app.SharedConnectivityManager;
import android.os.Handler;
import android.telephony.SubscriptionManager;
import android.util.Log;
import androidx.core.os.BuildCompat;
import com.android.wifitrackerlib.WifiPickerTracker;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BaseWifiTracker$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ WifiPickerTracker f$0;

    public /* synthetic */ BaseWifiTracker$$ExternalSyntheticLambda1(WifiPickerTracker wifiPickerTracker, int i) {
        this.$r8$classId = i;
        this.f$0 = wifiPickerTracker;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BaseWifiTracker$7 baseWifiTracker$7;
        BaseWifiTracker$7 baseWifiTracker$72;
        int i = this.$r8$classId;
        WifiPickerTracker wifiPickerTracker = this.f$0;
        switch (i) {
            case 0:
                try {
                    wifiPickerTracker.mContext.unregisterReceiver(wifiPickerTracker.mBroadcastReceiver);
                    wifiPickerTracker.mConnectivityManager.unregisterNetworkCallback(wifiPickerTracker.mNetworkCallback);
                    wifiPickerTracker.mConnectivityManager.unregisterNetworkCallback(wifiPickerTracker.mDefaultNetworkCallback);
                    wifiPickerTracker.mConnectivityDiagnosticsManager.unregisterConnectivityDiagnosticsCallback(wifiPickerTracker.mConnectivityDiagnosticsCallback);
                    SharedConnectivityManager sharedConnectivityManager = wifiPickerTracker.mSharedConnectivityManager;
                    if (sharedConnectivityManager != null && (baseWifiTracker$7 = wifiPickerTracker.mSharedConnectivityCallback) != null) {
                        int i2 = BuildCompat.$r8$clinit;
                        if (!sharedConnectivityManager.unregisterCallback(baseWifiTracker$7)) {
                            Log.e(wifiPickerTracker.mTag, "onStop: unregisterCallback failed");
                            break;
                        }
                    }
                } catch (IllegalArgumentException unused) {
                    return;
                }
                break;
            default:
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                if (!wifiPickerTracker.mIsScanningDisabled) {
                    intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
                }
                intentFilter.addAction("android.net.wifi.CONFIGURED_NETWORKS_CHANGE");
                intentFilter.addAction("android.net.wifi.STATE_CHANGE");
                if (wifiPickerTracker.isVerboseLoggingEnabled()) {
                    intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
                }
                intentFilter.addAction("android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED");
                intentFilter.setPriority(1000);
                Context context = wifiPickerTracker.mContext;
                BaseWifiTracker$1 baseWifiTracker$1 = wifiPickerTracker.mBroadcastReceiver;
                Handler handler = wifiPickerTracker.mWorkerHandler;
                context.registerReceiver(baseWifiTracker$1, intentFilter, null, handler);
                wifiPickerTracker.mConnectivityManager.registerNetworkCallback(wifiPickerTracker.mNetworkRequest, wifiPickerTracker.mNetworkCallback, handler);
                wifiPickerTracker.mConnectivityManager.registerDefaultNetworkCallback(wifiPickerTracker.mDefaultNetworkCallback, handler);
                wifiPickerTracker.mConnectivityDiagnosticsManager.registerConnectivityDiagnosticsCallback(wifiPickerTracker.mNetworkRequest, wifiPickerTracker.mConnectivityDiagnosticsExecutor, wifiPickerTracker.mConnectivityDiagnosticsCallback);
                SharedConnectivityManager sharedConnectivityManager2 = wifiPickerTracker.mSharedConnectivityManager;
                if (sharedConnectivityManager2 != null && (baseWifiTracker$72 = wifiPickerTracker.mSharedConnectivityCallback) != null) {
                    int i3 = BuildCompat.$r8$clinit;
                    sharedConnectivityManager2.registerCallback(wifiPickerTracker.mSharedConnectivityExecutor, baseWifiTracker$72);
                }
                Iterator it = wifiPickerTracker.getAllWifiEntries().iterator();
                while (it.hasNext()) {
                    ((WifiEntry) it.next()).clearConnectionInfo();
                }
                wifiPickerTracker.updateWifiConfigurationsInternal();
                wifiPickerTracker.updatePasspointConfigurations(wifiPickerTracker.mWifiManager.getPasspointConfigurations());
                wifiPickerTracker.mScanResultUpdater.update(wifiPickerTracker.mWifiManager.getScanResults());
                wifiPickerTracker.conditionallyUpdateScanResults(true);
                wifiPickerTracker.handleDefaultSubscriptionChanged(SubscriptionManager.getDefaultDataSubscriptionId());
                Network currentNetwork = wifiPickerTracker.mWifiManager.getCurrentNetwork();
                if (currentNetwork != null) {
                    NetworkCapabilities networkCapabilities = wifiPickerTracker.mConnectivityManager.getNetworkCapabilities(currentNetwork);
                    if (networkCapabilities != null) {
                        wifiPickerTracker.handleNetworkCapabilitiesChanged(currentNetwork, new NetworkCapabilities.Builder(networkCapabilities).setTransportInfo(wifiPickerTracker.mWifiManager.getConnectionInfo()).build());
                    }
                    LinkProperties linkProperties = wifiPickerTracker.mConnectivityManager.getLinkProperties(currentNetwork);
                    if (linkProperties != null) {
                        Iterator it2 = wifiPickerTracker.getAllWifiEntries().iterator();
                        while (it2.hasNext()) {
                            ((WifiEntry) it2.next()).updateLinkProperties(currentNetwork, linkProperties);
                        }
                    }
                }
                Handler handler2 = wifiPickerTracker.mMainHandler;
                WifiPickerTracker.WifiPickerTrackerCallback wifiPickerTrackerCallback = wifiPickerTracker.mListener;
                if (wifiPickerTrackerCallback != null) {
                    handler2.post(new WifiPickerTracker$$ExternalSyntheticLambda37(wifiPickerTrackerCallback));
                }
                if (wifiPickerTrackerCallback != null) {
                    handler2.post(new WifiPickerTracker$$ExternalSyntheticLambda37(wifiPickerTrackerCallback));
                }
                wifiPickerTracker.updateWifiEntries(0);
                wifiPickerTracker.mIsInitialized = true;
                break;
        }
    }
}

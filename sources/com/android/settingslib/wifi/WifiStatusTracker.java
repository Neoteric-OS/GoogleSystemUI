package com.android.settingslib.wifi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkKey;
import android.net.NetworkRequest;
import android.net.NetworkScoreManager;
import android.net.ScoredNetwork;
import android.net.vcn.VcnTransportInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.android.systemui.statusbar.connectivity.WifiSignalController$$ExternalSyntheticLambda0;
import com.android.wm.shell.R;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WifiStatusTracker {
    public static final SimpleDateFormat SSDF = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
    public boolean connected;
    public boolean enabled;
    public boolean isCaptivePortal;
    public boolean isCarrierMerged;
    public boolean isDefaultNetwork;
    public int level;
    public final AnonymousClass3 mCacheListener;
    public final WifiSignalController$$ExternalSyntheticLambda0 mCallback;
    public final ConnectivityManager mConnectivityManager;
    public final Context mContext;
    public final Handler mHandler;
    public int mHistoryIndex;
    public final Handler mMainThreadHandler;
    public final NetworkScoreManager mNetworkScoreManager;
    public int mPrimaryNetworkId;
    public WifiInfo mWifiInfo;
    public final WifiManager mWifiManager;
    public final WifiNetworkScoreCache mWifiNetworkScoreCache;
    public int rssi;
    public String ssid;
    public String statusLabel;
    public int subId;
    public final Set mNetworks = new HashSet();
    public final String[] mHistory = new String[32];
    public final NetworkRequest mNetworkRequest = new NetworkRequest.Builder().clearCapabilities().addCapability(15).addTransportType(1).addTransportType(0).build();
    public final AnonymousClass1 mNetworkCallback = new AnonymousClass1(this, 0);
    public final AnonymousClass1 mDefaultNetworkCallback = new AnonymousClass1(this, 1);
    public NetworkCapabilities mDefaultNetworkCapabilities = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.settingslib.wifi.WifiStatusTracker$1, reason: invalid class name */
    public final class AnonymousClass1 extends ConnectivityManager.NetworkCallback {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ WifiStatusTracker this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass1(WifiStatusTracker wifiStatusTracker, int i) {
            super(1);
            this.$r8$classId = i;
            this.this$0 = wifiStatusTracker;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            switch (this.$r8$classId) {
                case 0:
                    WifiInfo mainOrUnderlyingWifiInfo = this.this$0.getMainOrUnderlyingWifiInfo(networkCapabilities);
                    this.this$0.getClass();
                    if (networkCapabilities != null && (mainOrUnderlyingWifiInfo != null || networkCapabilities.hasTransport(1))) {
                        String str = WifiStatusTracker.SSDF.format(Long.valueOf(System.currentTimeMillis())) + ",onCapabilitiesChanged: network=" + network + ",networkCapabilities=" + networkCapabilities;
                        WifiStatusTracker wifiStatusTracker = this.this$0;
                        int i = wifiStatusTracker.mHistoryIndex;
                        wifiStatusTracker.mHistory[i] = str;
                        wifiStatusTracker.mHistoryIndex = (i + 1) % 32;
                    }
                    if (mainOrUnderlyingWifiInfo != null && mainOrUnderlyingWifiInfo.isPrimary()) {
                        if (!this.this$0.mNetworks.contains(Integer.valueOf(network.getNetId()))) {
                            this.this$0.mNetworks.add(Integer.valueOf(network.getNetId()));
                        }
                        this.this$0.mPrimaryNetworkId = network.getNetId();
                        WifiStatusTracker.m774$$Nest$mupdateWifiInfo(this.this$0, mainOrUnderlyingWifiInfo);
                        this.this$0.updateStatusLabel();
                        this.this$0.mMainThreadHandler.post(new WifiStatusTracker$1$$ExternalSyntheticLambda0(this, 0));
                        break;
                    } else if (this.this$0.mNetworks.contains(Integer.valueOf(network.getNetId()))) {
                        this.this$0.mNetworks.remove(Integer.valueOf(network.getNetId()));
                        break;
                    }
                    break;
                default:
                    this.this$0.getClass();
                    WifiStatusTracker wifiStatusTracker2 = this.this$0;
                    wifiStatusTracker2.mDefaultNetworkCapabilities = networkCapabilities;
                    wifiStatusTracker2.updateStatusLabel();
                    this.this$0.mMainThreadHandler.post(new WifiStatusTracker$2$$ExternalSyntheticLambda0(this, 0));
                    break;
            }
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLost(Network network) {
            switch (this.$r8$classId) {
                case 0:
                    String str = WifiStatusTracker.SSDF.format(Long.valueOf(System.currentTimeMillis())) + ",onLost: network=" + network;
                    WifiStatusTracker wifiStatusTracker = this.this$0;
                    int i = wifiStatusTracker.mHistoryIndex;
                    wifiStatusTracker.mHistory[i] = str;
                    wifiStatusTracker.mHistoryIndex = (i + 1) % 32;
                    if (wifiStatusTracker.mNetworks.contains(Integer.valueOf(network.getNetId()))) {
                        this.this$0.mNetworks.remove(Integer.valueOf(network.getNetId()));
                    }
                    int netId = network.getNetId();
                    WifiStatusTracker wifiStatusTracker2 = this.this$0;
                    if (netId == wifiStatusTracker2.mPrimaryNetworkId) {
                        WifiStatusTracker.m774$$Nest$mupdateWifiInfo(wifiStatusTracker2, null);
                        this.this$0.updateStatusLabel();
                        this.this$0.mMainThreadHandler.post(new WifiStatusTracker$1$$ExternalSyntheticLambda0(this, 1));
                        break;
                    }
                    break;
                default:
                    this.this$0.getClass();
                    WifiStatusTracker wifiStatusTracker3 = this.this$0;
                    wifiStatusTracker3.mDefaultNetworkCapabilities = null;
                    wifiStatusTracker3.updateStatusLabel();
                    this.this$0.mMainThreadHandler.post(new WifiStatusTracker$2$$ExternalSyntheticLambda0(this, 1));
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.settingslib.wifi.WifiStatusTracker$3, reason: invalid class name */
    public final class AnonymousClass3 extends WifiNetworkScoreCache.CacheListener {
        public AnonymousClass3(Handler handler) {
            super(handler);
        }

        public final void networkCacheUpdated(List list) {
            WifiStatusTracker.this.updateStatusLabel();
            WifiStatusTracker.this.mMainThreadHandler.post(new WifiStatusTracker$$ExternalSyntheticLambda0(1, this));
        }
    }

    /* renamed from: -$$Nest$mupdateWifiInfo, reason: not valid java name */
    public static void m774$$Nest$mupdateWifiInfo(WifiStatusTracker wifiStatusTracker, WifiInfo wifiInfo) {
        wifiStatusTracker.updateWifiState();
        wifiStatusTracker.connected = wifiInfo != null;
        wifiStatusTracker.mWifiInfo = wifiInfo;
        String str = null;
        wifiStatusTracker.ssid = null;
        if (wifiInfo != null) {
            if (wifiInfo.isPasspointAp() || wifiStatusTracker.mWifiInfo.isOsuAp()) {
                wifiStatusTracker.ssid = wifiStatusTracker.mWifiInfo.getPasspointProviderFriendlyName();
            } else {
                String ssid = wifiStatusTracker.mWifiInfo.getSSID();
                if (ssid != null && !"<unknown ssid>".equals(ssid)) {
                    str = ssid;
                }
                wifiStatusTracker.ssid = str;
            }
            wifiStatusTracker.isCarrierMerged = wifiStatusTracker.mWifiInfo.isCarrierMerged();
            wifiStatusTracker.subId = wifiStatusTracker.mWifiInfo.getSubscriptionId();
            int rssi = wifiStatusTracker.mWifiInfo.getRssi();
            wifiStatusTracker.rssi = rssi;
            wifiStatusTracker.level = wifiStatusTracker.mWifiManager.calculateSignalLevel(rssi);
            NetworkKey createFromWifiInfo = NetworkKey.createFromWifiInfo(wifiStatusTracker.mWifiInfo);
            if (wifiStatusTracker.mWifiNetworkScoreCache.getScoredNetwork(createFromWifiInfo) == null) {
                wifiStatusTracker.mNetworkScoreManager.requestScores(new NetworkKey[]{createFromWifiInfo});
            }
        }
    }

    public WifiStatusTracker(Context context, WifiManager wifiManager, NetworkScoreManager networkScoreManager, ConnectivityManager connectivityManager, WifiSignalController$$ExternalSyntheticLambda0 wifiSignalController$$ExternalSyntheticLambda0, Handler handler, Handler handler2) {
        this.mContext = context;
        this.mWifiManager = wifiManager;
        this.mWifiNetworkScoreCache = new WifiNetworkScoreCache(context);
        this.mNetworkScoreManager = networkScoreManager;
        this.mConnectivityManager = connectivityManager;
        this.mCallback = wifiSignalController$$ExternalSyntheticLambda0;
        this.mHandler = handler2;
        this.mMainThreadHandler = handler == null ? new Handler(Looper.getMainLooper()) : handler;
        this.mCacheListener = new AnonymousClass3(handler2);
    }

    public static WifiInfo getMainWifiInfo(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities == null) {
            return null;
        }
        if (!networkCapabilities.hasTransport(1) && !networkCapabilities.hasTransport(0)) {
            return null;
        }
        VcnTransportInfo transportInfo = networkCapabilities.getTransportInfo();
        if (transportInfo instanceof VcnTransportInfo) {
            return transportInfo.getWifiInfo();
        }
        if (transportInfo instanceof WifiInfo) {
            return (WifiInfo) transportInfo;
        }
        return null;
    }

    public final WifiInfo getMainOrUnderlyingWifiInfo(NetworkCapabilities networkCapabilities) {
        if (networkCapabilities == null) {
            return null;
        }
        WifiInfo mainWifiInfo = getMainWifiInfo(networkCapabilities);
        if (mainWifiInfo != null) {
            return mainWifiInfo;
        }
        if (!networkCapabilities.hasTransport(0)) {
            return mainWifiInfo;
        }
        List underlyingNetworks = networkCapabilities.getUnderlyingNetworks();
        if (underlyingNetworks == null) {
            return null;
        }
        Iterator it = underlyingNetworks.iterator();
        while (it.hasNext()) {
            WifiInfo mainWifiInfo2 = getMainWifiInfo(this.mConnectivityManager.getNetworkCapabilities((Network) it.next()));
            if (mainWifiInfo2 != null) {
                return mainWifiInfo2;
            }
        }
        return null;
    }

    public final void postResults() {
        this.mCallback.run();
    }

    public final void updateStatusLabel() {
        String speedLabel;
        NetworkCapabilities networkCapabilities;
        if (this.mWifiManager == null) {
            return;
        }
        NetworkCapabilities networkCapabilities2 = this.mDefaultNetworkCapabilities;
        boolean z = networkCapabilities2 != null && (getMainOrUnderlyingWifiInfo(networkCapabilities2) != null || networkCapabilities2.hasTransport(1));
        this.isDefaultNetwork = z;
        NetworkCapabilities networkCapabilities3 = z ? this.mDefaultNetworkCapabilities : this.mConnectivityManager.getNetworkCapabilities(this.mWifiManager.getCurrentNetwork());
        this.isCaptivePortal = false;
        if (networkCapabilities3 != null) {
            if (networkCapabilities3.hasCapability(17)) {
                this.statusLabel = this.mContext.getString(R.string.wifi_status_sign_in_required);
                this.isCaptivePortal = true;
                return;
            }
            if (networkCapabilities3.hasCapability(24)) {
                this.statusLabel = this.mContext.getString(R.string.wifi_limited_connection);
                return;
            }
            if (!networkCapabilities3.hasCapability(16)) {
                Settings.Global.getString(this.mContext.getContentResolver(), "private_dns_mode");
                if (networkCapabilities3.isPrivateDnsBroken()) {
                    this.statusLabel = this.mContext.getString(R.string.private_dns_broken);
                    return;
                } else {
                    this.statusLabel = this.mContext.getString(R.string.wifi_status_no_internet);
                    return;
                }
            }
            if (!this.isDefaultNetwork && (networkCapabilities = this.mDefaultNetworkCapabilities) != null && networkCapabilities.hasTransport(0)) {
                this.statusLabel = this.mContext.getString(R.string.wifi_connected_low_quality);
                return;
            }
        }
        ScoredNetwork scoredNetwork = this.mWifiNetworkScoreCache.getScoredNetwork(NetworkKey.createFromWifiInfo(this.mWifiInfo));
        if (scoredNetwork == null) {
            speedLabel = null;
        } else {
            Context context = this.mContext;
            int i = this.rssi;
            int i2 = AccessPoint.$r8$clinit;
            int calculateBadge = scoredNetwork.calculateBadge(i);
            speedLabel = AccessPoint.getSpeedLabel(calculateBadge >= 5 ? calculateBadge < 7 ? 5 : calculateBadge < 15 ? 10 : calculateBadge < 25 ? 20 : 30 : 0, context);
        }
        this.statusLabel = speedLabel;
    }

    public final void updateWifiState() {
        this.enabled = this.mWifiManager.getWifiState() == 3;
    }
}

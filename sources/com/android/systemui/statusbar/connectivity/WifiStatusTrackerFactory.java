package com.android.systemui.statusbar.connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkScoreManager;
import android.net.wifi.WifiManager;
import android.os.Handler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiStatusTrackerFactory {
    public final ConnectivityManager mConnectivityManager;
    public final Context mContext;
    public final Handler mMainHandler;
    public final NetworkScoreManager mNetworkScoreManager;
    public final WifiManager mWifiManager;

    public WifiStatusTrackerFactory(Context context, WifiManager wifiManager, NetworkScoreManager networkScoreManager, ConnectivityManager connectivityManager, Handler handler) {
        this.mContext = context;
        this.mWifiManager = wifiManager;
        this.mNetworkScoreManager = networkScoreManager;
        this.mConnectivityManager = connectivityManager;
        this.mMainHandler = handler;
    }
}

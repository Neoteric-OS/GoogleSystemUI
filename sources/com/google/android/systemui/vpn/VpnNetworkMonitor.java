package com.google.android.systemui.vpn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.util.Assert;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VpnNetworkMonitor {
    public static final boolean DEBUG = Log.isLoggable("VpnNetworkMonitor", 3);
    public final BroadcastDispatcher broadcastDispatcher;
    public final BroadcastSender broadcastSender;
    public final ConnectivityManager connectivityManager;
    public final Executor executor;
    public boolean isRegistered;
    public final Executor uiExecutor;
    public final UserFileManager userFileManager;
    public final UserTracker userTracker;
    public final VpnNetworkMonitor$broadcastReceiver$1 broadcastReceiver = new BroadcastReceiver() { // from class: com.google.android.systemui.vpn.VpnNetworkMonitor$broadcastReceiver$1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "com.google.android.wildlife.action.UPDATE_NETWORK_MONITOR")) {
                boolean booleanExtra = intent.getBooleanExtra("com.google.android.wildlife.extra.UPDATE_NETWORK_MONITOR_STATUS", true);
                VpnNetworkMonitor vpnNetworkMonitor = VpnNetworkMonitor.this;
                vpnNetworkMonitor.getClass();
                Assert.isMainThread();
                Log.i("VpnNetworkMonitor", "setNetworkMonitorEnabled " + booleanExtra);
                if (booleanExtra) {
                    vpnNetworkMonitor.registerNetworkCallback();
                } else {
                    vpnNetworkMonitor.unregisterNetworkCallback();
                }
                ((UserFileManagerImpl) vpnNetworkMonitor.userFileManager).getSharedPreferences$1(((UserTrackerImpl) vpnNetworkMonitor.userTracker).getUserId(), "network_monitor_index").edit().putBoolean("network_monitor_enabled", booleanExtra).apply();
            }
        }
    };
    public final VpnNetworkMonitor$userTrackerCallback$1 userTrackerCallback = new UserTracker.Callback() { // from class: com.google.android.systemui.vpn.VpnNetworkMonitor$userTrackerCallback$1
        @Override // com.android.systemui.settings.UserTracker.Callback
        public final void onUserChanged(int i, Context context) {
            VpnNetworkMonitor vpnNetworkMonitor = VpnNetworkMonitor.this;
            if (((UserFileManagerImpl) vpnNetworkMonitor.userFileManager).getSharedPreferences$1(((UserTrackerImpl) vpnNetworkMonitor.userTracker).getUserId(), "network_monitor_index").getBoolean("network_monitor_enabled", true)) {
                vpnNetworkMonitor.registerNetworkCallback();
            } else {
                vpnNetworkMonitor.unregisterNetworkCallback();
            }
        }
    };
    public final MyNetworkStatusCallback networkCallback = new MyNetworkStatusCallback();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MyNetworkStatusCallback extends ConnectivityManager.NetworkCallback {
        public MyNetworkStatusCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            VpnNetworkMonitor.access$notifyNetworkChange(VpnNetworkMonitor.this);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public final void onLost(Network network) {
            VpnNetworkMonitor.access$notifyNetworkChange(VpnNetworkMonitor.this);
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.vpn.VpnNetworkMonitor$broadcastReceiver$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.systemui.vpn.VpnNetworkMonitor$userTrackerCallback$1] */
    public VpnNetworkMonitor(ConnectivityManager connectivityManager, BroadcastSender broadcastSender, BroadcastDispatcher broadcastDispatcher, Executor executor, Executor executor2, UserFileManager userFileManager, UserTracker userTracker) {
        this.connectivityManager = connectivityManager;
        this.broadcastSender = broadcastSender;
        this.broadcastDispatcher = broadcastDispatcher;
        this.uiExecutor = executor;
        this.executor = executor2;
        this.userFileManager = userFileManager;
        this.userTracker = userTracker;
    }

    public static final void access$notifyNetworkChange(VpnNetworkMonitor vpnNetworkMonitor) {
        vpnNetworkMonitor.getClass();
        if (DEBUG) {
            Log.d("VpnNetworkMonitor", "notifyNetworkChange");
        }
        vpnNetworkMonitor.broadcastSender.sendBroadcast(new Intent("com.google.android.apps.privacy.wildlife.WIFI_STATE_CHANGED").setClassName("com.google.android.apps.privacy.wildlife", "com.google.android.apps.privacy.wildlife.receiver.WildlifeVpnReceiver"));
    }

    public final void registerNetworkCallback() {
        Assert.isMainThread();
        if (this.isRegistered) {
            return;
        }
        this.executor.execute(new VpnNetworkMonitor$registerNetworkCallback$1(this, 0));
        this.isRegistered = true;
    }

    public final void unregisterNetworkCallback() {
        Assert.isMainThread();
        if (this.isRegistered) {
            this.executor.execute(new VpnNetworkMonitor$registerNetworkCallback$1(this, 1));
            this.isRegistered = false;
        }
    }
}

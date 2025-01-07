package com.google.android.systemui.vpn;

import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.UserHandle;
import android.util.Log;
import com.android.systemui.CoreStartable;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.PendingRemovalStore;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AdaptivePPNService implements CoreStartable {
    public final boolean isVpnFeatureEnabled;
    public final Executor uiExecutor;
    public VpnNetworkMonitor vpnNetworkMonitor;
    public final Lazy vpnNetworkMonitorWrapper;
    public VpnPackageMonitor vpnPackageMonitor;
    public final Lazy vpnPackageMonitorWrapper;

    public AdaptivePPNService(Resources resources, Executor executor, Lazy lazy, Lazy lazy2) {
        this.uiExecutor = executor;
        this.vpnNetworkMonitorWrapper = lazy;
        this.vpnPackageMonitorWrapper = lazy2;
        boolean z = resources.getBoolean(R.bool.config_pixel_vpn_enabled);
        this.isVpnFeatureEnabled = z;
        Log.i("AdaptivePPNService", "Wildlife feature enabled is " + z);
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (this.isVpnFeatureEnabled) {
            this.uiExecutor.execute(new Runnable() { // from class: com.google.android.systemui.vpn.AdaptivePPNService$startMonitorState$1
                @Override // java.lang.Runnable
                public final void run() {
                    Log.i("AdaptivePPNService", "start monitors");
                    AdaptivePPNService adaptivePPNService = AdaptivePPNService.this;
                    adaptivePPNService.vpnNetworkMonitor = (VpnNetworkMonitor) adaptivePPNService.vpnNetworkMonitorWrapper.get();
                    AdaptivePPNService adaptivePPNService2 = AdaptivePPNService.this;
                    adaptivePPNService2.vpnPackageMonitor = (VpnPackageMonitor) adaptivePPNService2.vpnPackageMonitorWrapper.get();
                    AdaptivePPNService adaptivePPNService3 = AdaptivePPNService.this;
                    VpnNetworkMonitor vpnNetworkMonitor = adaptivePPNService3.vpnNetworkMonitor;
                    if (vpnNetworkMonitor == null) {
                        vpnNetworkMonitor = null;
                    }
                    ((UserTrackerImpl) vpnNetworkMonitor.userTracker).removeCallback(vpnNetworkMonitor.userTrackerCallback);
                    vpnNetworkMonitor.unregisterNetworkCallback();
                    UserHandle userHandle = new UserHandle(-1);
                    BroadcastDispatcher broadcastDispatcher = vpnNetworkMonitor.broadcastDispatcher;
                    VpnNetworkMonitor$broadcastReceiver$1 vpnNetworkMonitor$broadcastReceiver$1 = vpnNetworkMonitor.broadcastReceiver;
                    PendingRemovalStore pendingRemovalStore = broadcastDispatcher.removalPendingStore;
                    int identifier = userHandle.getIdentifier();
                    pendingRemovalStore.logger.logTagForRemoval(identifier, vpnNetworkMonitor$broadcastReceiver$1);
                    synchronized (pendingRemovalStore.pendingRemoval) {
                        pendingRemovalStore.pendingRemoval.add(identifier, vpnNetworkMonitor$broadcastReceiver$1);
                    }
                    broadcastDispatcher.handler.obtainMessage(2, userHandle.getIdentifier(), 0, vpnNetworkMonitor$broadcastReceiver$1).sendToTarget();
                    Log.i("VpnNetworkMonitor", "NetworkMonitor - destroy, enabled is " + ((UserFileManagerImpl) vpnNetworkMonitor.userFileManager).getSharedPreferences$1(((UserTrackerImpl) vpnNetworkMonitor.userTracker).getUserId(), "network_monitor_index").getBoolean("network_monitor_enabled", true));
                    VpnPackageMonitor vpnPackageMonitor = adaptivePPNService3.vpnPackageMonitor;
                    if (vpnPackageMonitor == null) {
                        vpnPackageMonitor = null;
                    }
                    vpnPackageMonitor.getClass();
                    UserHandle userHandle2 = new UserHandle(-1);
                    BroadcastDispatcher broadcastDispatcher2 = vpnPackageMonitor.broadcastDispatcher;
                    VpnPackageMonitor$packageMonitorBroadcastReceiver$1 vpnPackageMonitor$packageMonitorBroadcastReceiver$1 = vpnPackageMonitor.wildlifeFeatureBroadcastReceiver;
                    PendingRemovalStore pendingRemovalStore2 = broadcastDispatcher2.removalPendingStore;
                    int identifier2 = userHandle2.getIdentifier();
                    pendingRemovalStore2.logger.logTagForRemoval(identifier2, vpnPackageMonitor$packageMonitorBroadcastReceiver$1);
                    synchronized (pendingRemovalStore2.pendingRemoval) {
                        pendingRemovalStore2.pendingRemoval.add(identifier2, vpnPackageMonitor$packageMonitorBroadcastReceiver$1);
                    }
                    broadcastDispatcher2.handler.obtainMessage(2, userHandle2.getIdentifier(), 0, vpnPackageMonitor$packageMonitorBroadcastReceiver$1).sendToTarget();
                    if (vpnPackageMonitor.isRegistered) {
                        vpnPackageMonitor.context.unregisterReceiver(vpnPackageMonitor.packageMonitorBroadcastReceiver);
                        vpnPackageMonitor.isRegistered = false;
                    }
                    AdaptivePPNService adaptivePPNService4 = AdaptivePPNService.this;
                    VpnNetworkMonitor vpnNetworkMonitor2 = adaptivePPNService4.vpnNetworkMonitor;
                    if (vpnNetworkMonitor2 == null) {
                        vpnNetworkMonitor2 = null;
                    }
                    vpnNetworkMonitor2.getClass();
                    vpnNetworkMonitor2.broadcastDispatcher.registerReceiver(vpnNetworkMonitor2.broadcastReceiver, new IntentFilter("com.google.android.wildlife.action.UPDATE_NETWORK_MONITOR"), vpnNetworkMonitor2.uiExecutor, new UserHandle(-1), 2, "com.google.android.wildlife.permission.UPDATE_NETWORK_MONITOR");
                    boolean z = ((UserFileManagerImpl) vpnNetworkMonitor2.userFileManager).getSharedPreferences$1(((UserTrackerImpl) vpnNetworkMonitor2.userTracker).getUserId(), "network_monitor_index").getBoolean("network_monitor_enabled", true);
                    Log.i("VpnNetworkMonitor", "NetworkMonitor - create, enabled is " + z);
                    if (z) {
                        vpnNetworkMonitor2.registerNetworkCallback();
                    }
                    ((UserTrackerImpl) vpnNetworkMonitor2.userTracker).addCallback(vpnNetworkMonitor2.userTrackerCallback, vpnNetworkMonitor2.uiExecutor);
                    VpnPackageMonitor vpnPackageMonitor2 = adaptivePPNService4.vpnPackageMonitor;
                    VpnPackageMonitor vpnPackageMonitor3 = vpnPackageMonitor2 != null ? vpnPackageMonitor2 : null;
                    vpnPackageMonitor3.getClass();
                    vpnPackageMonitor3.broadcastDispatcher.registerReceiver(vpnPackageMonitor3.wildlifeFeatureBroadcastReceiver, new IntentFilter("com.google.android.wildlife.action.UPDATE_PACKAGE_MONITOR"), null, new UserHandle(-1), 2, "com.google.android.wildlife.permission.UPDATE_PACKAGE_MONITOR");
                    boolean z2 = ((UserFileManagerImpl) vpnPackageMonitor3.userFileManager).getSharedPreferences$1(((UserTrackerImpl) vpnPackageMonitor3.userTracker).getUserId(), "package_monitor_index").getBoolean("package_monitor_enabled", true);
                    Log.i("VpnPackageMonitor", "isPackageMonitorEnabled is " + z2);
                    if (z2) {
                        vpnPackageMonitor3.registerPackageMonitorReceiver();
                    }
                }
            });
        } else {
            Log.d("AdaptivePPNService", "System config is off");
        }
    }
}

package com.google.android.systemui.vpn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VpnPackageMonitor {
    public static final boolean DEBUG = Log.isLoggable("VpnPackageMonitor", 3);
    public final BroadcastDispatcher broadcastDispatcher;
    public final BroadcastSender broadcastSender;
    public final Context context;
    public boolean isRegistered;
    public final VpnPackageMonitor$packageMonitorBroadcastReceiver$1 packageMonitorBroadcastReceiver;
    public final UserFileManager userFileManager;
    public final UserTracker userTracker;
    public final VpnPackageMonitor$packageMonitorBroadcastReceiver$1 wildlifeFeatureBroadcastReceiver;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.android.systemui.vpn.VpnPackageMonitor$packageMonitorBroadcastReceiver$1] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.google.android.systemui.vpn.VpnPackageMonitor$packageMonitorBroadcastReceiver$1] */
    public VpnPackageMonitor(Context context, BroadcastSender broadcastSender, BroadcastDispatcher broadcastDispatcher, UserFileManager userFileManager, UserTracker userTracker) {
        this.context = context;
        this.broadcastSender = broadcastSender;
        this.broadcastDispatcher = broadcastDispatcher;
        this.userFileManager = userFileManager;
        this.userTracker = userTracker;
        final int i = 1;
        this.wildlifeFeatureBroadcastReceiver = new BroadcastReceiver(this) { // from class: com.google.android.systemui.vpn.VpnPackageMonitor$packageMonitorBroadcastReceiver$1
            public final /* synthetic */ VpnPackageMonitor this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                Uri data;
                String schemeSpecificPart;
                switch (i) {
                    case 0:
                        if (TextUtils.equals(intent.getAction(), "android.intent.action.PACKAGE_ADDED") && (data = intent.getData()) != null && (schemeSpecificPart = data.getSchemeSpecificPart()) != null) {
                            VpnPackageMonitor vpnPackageMonitor = this.this$0;
                            vpnPackageMonitor.getClass();
                            if (VpnPackageMonitor.DEBUG) {
                                Log.d("VpnPackageMonitor", "notifyPackageAdded");
                            }
                            vpnPackageMonitor.broadcastSender.sendBroadcast(new Intent("com.google.android.settings.action.NOTIFY_PACKAGE_ADDED").setPackage("com.android.settings").putExtra("com.google.android.wildlife.extra.NEW_PACKAGE_NAME", schemeSpecificPart), "com.google.android.wildlife.permission.VPN_APP_EXCLUSION_LAUNCH");
                            break;
                        }
                        break;
                    default:
                        if (TextUtils.equals(intent.getAction(), "com.google.android.wildlife.action.UPDATE_PACKAGE_MONITOR")) {
                            boolean booleanExtra = intent.getBooleanExtra("com.google.android.wildlife.extra.UPDATE_PACKAGE_MONITOR_STATUS", true);
                            VpnPackageMonitor vpnPackageMonitor2 = this.this$0;
                            vpnPackageMonitor2.getClass();
                            Log.i("VpnPackageMonitor", "setPackageMonitorEnabled " + booleanExtra);
                            if (booleanExtra) {
                                vpnPackageMonitor2.registerPackageMonitorReceiver();
                            } else if (vpnPackageMonitor2.isRegistered) {
                                vpnPackageMonitor2.context.unregisterReceiver(vpnPackageMonitor2.packageMonitorBroadcastReceiver);
                                vpnPackageMonitor2.isRegistered = false;
                            }
                            ((UserFileManagerImpl) vpnPackageMonitor2.userFileManager).getSharedPreferences$1(((UserTrackerImpl) vpnPackageMonitor2.userTracker).getUserId(), "package_monitor_index").edit().putBoolean("package_monitor_enabled", booleanExtra).apply();
                            break;
                        }
                        break;
                }
            }
        };
        final int i2 = 0;
        this.packageMonitorBroadcastReceiver = new BroadcastReceiver(this) { // from class: com.google.android.systemui.vpn.VpnPackageMonitor$packageMonitorBroadcastReceiver$1
            public final /* synthetic */ VpnPackageMonitor this$0;

            {
                this.this$0 = this;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                Uri data;
                String schemeSpecificPart;
                switch (i2) {
                    case 0:
                        if (TextUtils.equals(intent.getAction(), "android.intent.action.PACKAGE_ADDED") && (data = intent.getData()) != null && (schemeSpecificPart = data.getSchemeSpecificPart()) != null) {
                            VpnPackageMonitor vpnPackageMonitor = this.this$0;
                            vpnPackageMonitor.getClass();
                            if (VpnPackageMonitor.DEBUG) {
                                Log.d("VpnPackageMonitor", "notifyPackageAdded");
                            }
                            vpnPackageMonitor.broadcastSender.sendBroadcast(new Intent("com.google.android.settings.action.NOTIFY_PACKAGE_ADDED").setPackage("com.android.settings").putExtra("com.google.android.wildlife.extra.NEW_PACKAGE_NAME", schemeSpecificPart), "com.google.android.wildlife.permission.VPN_APP_EXCLUSION_LAUNCH");
                            break;
                        }
                        break;
                    default:
                        if (TextUtils.equals(intent.getAction(), "com.google.android.wildlife.action.UPDATE_PACKAGE_MONITOR")) {
                            boolean booleanExtra = intent.getBooleanExtra("com.google.android.wildlife.extra.UPDATE_PACKAGE_MONITOR_STATUS", true);
                            VpnPackageMonitor vpnPackageMonitor2 = this.this$0;
                            vpnPackageMonitor2.getClass();
                            Log.i("VpnPackageMonitor", "setPackageMonitorEnabled " + booleanExtra);
                            if (booleanExtra) {
                                vpnPackageMonitor2.registerPackageMonitorReceiver();
                            } else if (vpnPackageMonitor2.isRegistered) {
                                vpnPackageMonitor2.context.unregisterReceiver(vpnPackageMonitor2.packageMonitorBroadcastReceiver);
                                vpnPackageMonitor2.isRegistered = false;
                            }
                            ((UserFileManagerImpl) vpnPackageMonitor2.userFileManager).getSharedPreferences$1(((UserTrackerImpl) vpnPackageMonitor2.userTracker).getUserId(), "package_monitor_index").edit().putBoolean("package_monitor_enabled", booleanExtra).apply();
                            break;
                        }
                        break;
                }
            }
        };
    }

    public final void registerPackageMonitorReceiver() {
        if (this.isRegistered) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        this.context.registerReceiver(this.packageMonitorBroadcastReceiver, intentFilter);
        this.isRegistered = true;
    }
}

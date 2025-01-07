package com.google.android.systemui.vpn;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VpnNetworkMonitor$registerNetworkCallback$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ VpnNetworkMonitor this$0;

    public /* synthetic */ VpnNetworkMonitor$registerNetworkCallback$1(VpnNetworkMonitor vpnNetworkMonitor, int i) {
        this.$r8$classId = i;
        this.this$0 = vpnNetworkMonitor;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                VpnNetworkMonitor vpnNetworkMonitor = this.this$0;
                vpnNetworkMonitor.connectivityManager.registerDefaultNetworkCallback(vpnNetworkMonitor.networkCallback);
                break;
            default:
                VpnNetworkMonitor vpnNetworkMonitor2 = this.this$0;
                vpnNetworkMonitor2.connectivityManager.unregisterNetworkCallback(vpnNetworkMonitor2.networkCallback);
                break;
        }
    }
}

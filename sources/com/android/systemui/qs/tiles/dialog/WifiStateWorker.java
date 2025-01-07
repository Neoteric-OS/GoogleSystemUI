package com.android.systemui.qs.tiles.dialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.Log;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiStateWorker extends BroadcastReceiver {
    public final DelayableExecutor mBackgroundExecutor;
    public final WifiManager mWifiManager;
    public int mWifiState = 1;

    public WifiStateWorker(BroadcastDispatcher broadcastDispatcher, DelayableExecutor delayableExecutor, WifiManager wifiManager) {
        this.mWifiManager = wifiManager;
        this.mBackgroundExecutor = delayableExecutor;
        broadcastDispatcher.registerReceiver(this, new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"));
        ((ExecutorImpl) delayableExecutor).execute(new Runnable() { // from class: com.android.systemui.qs.tiles.dialog.WifiStateWorker$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                WifiStateWorker wifiStateWorker = WifiStateWorker.this;
                WifiManager wifiManager2 = wifiStateWorker.mWifiManager;
                if (wifiManager2 == null) {
                    return;
                }
                wifiStateWorker.mWifiState = wifiManager2.getWifiState();
                Log.i("WifiStateWorker", "WifiManager.getWifiState():" + wifiStateWorker.mWifiState);
            }
        });
    }

    public final boolean isWifiEnabled() {
        int i = this.mWifiState;
        return i == 3 || i == 2;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        int intExtra;
        if (intent == null || !"android.net.wifi.WIFI_STATE_CHANGED".equals(intent.getAction()) || (intExtra = intent.getIntExtra("wifi_state", 1)) == 4) {
            return;
        }
        this.mWifiState = intExtra;
    }

    public final void setWifiEnabled(final boolean z) {
        ((ExecutorImpl) this.mBackgroundExecutor).execute(new Runnable() { // from class: com.android.systemui.qs.tiles.dialog.WifiStateWorker$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                WifiStateWorker wifiStateWorker = WifiStateWorker.this;
                boolean z2 = z;
                WifiManager wifiManager = wifiStateWorker.mWifiManager;
                if (wifiManager == null) {
                    return;
                }
                wifiStateWorker.mWifiState = z2 ? 2 : 0;
                if (wifiManager.setWifiEnabled(z2)) {
                    return;
                }
                Log.e("WifiStateWorker", "Failed to WifiManager.setWifiEnabled(" + z2 + ");");
            }
        });
    }
}

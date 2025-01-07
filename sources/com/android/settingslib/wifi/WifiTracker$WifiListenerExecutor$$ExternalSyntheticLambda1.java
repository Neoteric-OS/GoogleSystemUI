package com.android.settingslib.wifi;

import com.android.settingslib.utils.ThreadUtils;
import com.android.settingslib.wifi.WifiTracker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class WifiTracker$WifiListenerExecutor$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ WifiTracker.WifiListenerExecutor f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ WifiTracker$WifiListenerExecutor$$ExternalSyntheticLambda1(WifiTracker.WifiListenerExecutor wifiListenerExecutor, int i) {
        this.f$0 = wifiListenerExecutor;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        WifiTracker.WifiListenerExecutor wifiListenerExecutor = this.f$0;
        int i = this.f$1;
        WifiTracker.WifiListenerExecutor wifiListenerExecutor2 = (WifiTracker.WifiListenerExecutor) wifiListenerExecutor.mDelegatee;
        wifiListenerExecutor2.getClass();
        ThreadUtils.postOnMainThread(new WifiTracker$WifiListenerExecutor$$ExternalSyntheticLambda0(wifiListenerExecutor2, String.format("Invoking onWifiStateChanged callback with state %d", Integer.valueOf(i)), new WifiTracker$WifiListenerExecutor$$ExternalSyntheticLambda1(wifiListenerExecutor2, i)));
    }
}

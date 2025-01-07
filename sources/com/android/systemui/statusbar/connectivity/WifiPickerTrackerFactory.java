package com.android.systemui.statusbar.connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SimpleClock;
import androidx.lifecycle.Lifecycle;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wifitrackerlib.WifiPickerTracker;
import com.android.wifitrackerlib.WifiTrackerInjector;
import java.time.ZoneOffset;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiPickerTrackerFactory {
    public final WifiPickerTrackerFactory$clock$1 clock;
    public final ConnectivityManager connectivityManager;
    public final Context context;
    public final Handler mainHandler;
    public final SystemClock systemClock;
    public final ThreadFactoryImpl threadFactory;
    public final WifiManager wifiManager;

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.systemui.statusbar.connectivity.WifiPickerTrackerFactory$clock$1] */
    public WifiPickerTrackerFactory(Context context, WifiManager wifiManager, ConnectivityManager connectivityManager, SystemClock systemClock, Handler handler, ThreadFactoryImpl threadFactoryImpl) {
        this.context = context;
        this.wifiManager = wifiManager;
        this.connectivityManager = connectivityManager;
        this.systemClock = systemClock;
        this.mainHandler = handler;
        final ZoneOffset zoneOffset = ZoneOffset.UTC;
        this.clock = new SimpleClock(zoneOffset) { // from class: com.android.systemui.statusbar.connectivity.WifiPickerTrackerFactory$clock$1
            public final long millis() {
                ((SystemClockImpl) WifiPickerTrackerFactory.this.systemClock).getClass();
                return android.os.SystemClock.elapsedRealtime();
            }
        };
    }

    public final WifiPickerTracker create(Lifecycle lifecycle, WifiPickerTracker.WifiPickerTrackerCallback wifiPickerTrackerCallback, String str) {
        WifiManager wifiManager = this.wifiManager;
        if (wifiManager == null) {
            return null;
        }
        Context context = this.context;
        ConnectivityManager connectivityManager = this.connectivityManager;
        HandlerThread handlerThread = new HandlerThread("WifiPickerTracker-".concat(str));
        handlerThread.start();
        return new WifiPickerTracker(new WifiTrackerInjector(context), lifecycle, context, wifiManager, connectivityManager, this.mainHandler, new Handler(handlerThread.getLooper()), this.clock, 15000L, 10000L, wifiPickerTrackerCallback);
    }
}

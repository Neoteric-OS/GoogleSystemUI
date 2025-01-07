package com.android.systemui.util.concurrency;

import android.os.HandlerThread;
import android.os.Looper;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SysUIConcurrencyModule_ProvideLongRunningLooperFactory implements Provider {
    public static Looper provideLongRunningLooper() {
        HandlerThread handlerThread = new HandlerThread("SysUiLng", 10);
        handlerThread.start();
        handlerThread.getLooper().setSlowLogThresholdMs(2500L, 2500L);
        return handlerThread.getLooper();
    }
}

package com.android.systemui.util.concurrency;

import android.os.HandlerThread;
import android.os.Looper;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SysUIConcurrencyModule_ProvideBgLooperFactory implements Provider {
    public static Looper provideBgLooper() {
        HandlerThread handlerThread = new HandlerThread("SysUiBg", 10);
        handlerThread.start();
        handlerThread.getLooper().setSlowLogThresholdMs(1000L, 1000L);
        return handlerThread.getLooper();
    }
}

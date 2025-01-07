package com.android.wm.shell.dagger;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.android.wm.shell.common.HandlerExecutor;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellConcurrencyModule_ProvideShellAnimationExecutorFactory implements Provider {
    public static HandlerExecutor provideShellAnimationExecutor() {
        HandlerThread handlerThread = new HandlerThread("wmshell.anim", -4);
        handlerThread.start();
        if (Build.IS_DEBUGGABLE) {
            handlerThread.getLooper().setTraceTag(32L);
            handlerThread.getLooper().setSlowLogThresholdMs(30L, 30L);
        }
        return new HandlerExecutor(Handler.createAsync(handlerThread.getLooper()));
    }
}

package com.android.wm.shell.dagger;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import com.android.wm.shell.R;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellConcurrencyModule_ProvideShellMainHandlerFactory implements Provider {
    public static Handler provideShellMainHandler(Context context, HandlerThread handlerThread, Handler handler) {
        if (context.getResources().getBoolean(R.bool.config_enableShellMainThread)) {
            if (handlerThread == null) {
                handlerThread = new HandlerThread("wmshell.main", -4);
                handlerThread.start();
            }
            if (Build.IS_DEBUGGABLE) {
                handlerThread.getLooper().setTraceTag(32L);
                handlerThread.getLooper().setSlowLogThresholdMs(30L, 30L);
            }
            handler = Handler.createAsync(handlerThread.getLooper());
        }
        Preconditions.checkNotNullFromProvides(handler);
        return handler;
    }
}

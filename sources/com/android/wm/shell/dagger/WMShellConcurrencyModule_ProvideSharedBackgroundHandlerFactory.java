package com.android.wm.shell.dagger;

import android.os.Handler;
import android.os.HandlerThread;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellConcurrencyModule_ProvideSharedBackgroundHandlerFactory implements Provider {
    public static Handler provideSharedBackgroundHandler() {
        HandlerThread handlerThread = new HandlerThread("wmshell.background", 10);
        handlerThread.start();
        Handler threadHandler = handlerThread.getThreadHandler();
        Preconditions.checkNotNullFromProvides(threadHandler);
        return threadHandler;
    }
}

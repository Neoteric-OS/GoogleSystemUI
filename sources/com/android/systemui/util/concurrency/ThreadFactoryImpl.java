package com.android.systemui.util.concurrency;

import android.os.HandlerThread;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ThreadFactoryImpl implements ThreadFactory {
    public final ExecutorImpl buildExecutorOnNewThread(String str) {
        HandlerThread handlerThread = new HandlerThread(str);
        handlerThread.start();
        return new ExecutorImpl(handlerThread.getLooper());
    }
}

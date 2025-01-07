package com.android.settingslib.utils;

import android.os.Handler;
import android.os.Looper;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ThreadUtils {
    public static volatile ListeningExecutorService sListeningService;
    public static volatile Thread sMainThread;
    public static volatile Handler sMainThreadHandler;

    public static synchronized ListeningExecutorService getBackgroundExecutor() {
        ListeningExecutorService listeningExecutorService;
        ListeningExecutorService scheduledListeningDecorator;
        synchronized (ThreadUtils.class) {
            try {
                if (sListeningService == null) {
                    ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
                    if (newFixedThreadPool instanceof ListeningExecutorService) {
                        scheduledListeningDecorator = (ListeningExecutorService) newFixedThreadPool;
                    } else {
                        scheduledListeningDecorator = newFixedThreadPool instanceof ScheduledExecutorService ? new MoreExecutors.ScheduledListeningDecorator((ScheduledExecutorService) newFixedThreadPool) : new MoreExecutors.ListeningDecorator(newFixedThreadPool);
                    }
                    sListeningService = scheduledListeningDecorator;
                }
                listeningExecutorService = sListeningService;
            } catch (Throwable th) {
                throw th;
            }
        }
        return listeningExecutorService;
    }

    public static ListenableFuture postOnBackgroundThread(Runnable runnable) {
        return ((MoreExecutors.ListeningDecorator) getBackgroundExecutor()).submit(runnable);
    }

    public static void postOnMainThread(Runnable runnable) {
        if (sMainThreadHandler == null) {
            sMainThreadHandler = new Handler(Looper.getMainLooper());
        }
        sMainThreadHandler.post(runnable);
    }
}

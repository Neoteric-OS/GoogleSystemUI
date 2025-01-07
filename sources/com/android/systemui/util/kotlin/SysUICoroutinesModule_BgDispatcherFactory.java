package com.android.systemui.util.kotlin;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import dagger.internal.Provider;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.ExecutorCoroutineDispatcherImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SysUICoroutinesModule_BgDispatcherFactory implements Provider {
    public static ExecutorCoroutineDispatcherImpl bgDispatcher(SysUICoroutinesModule sysUICoroutinesModule) {
        sysUICoroutinesModule.getClass();
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (availableProcessors < 1) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Expected at least one thread, but ", " specified", availableProcessors).toString());
        }
        final AtomicInteger atomicInteger = new AtomicInteger();
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(availableProcessors, new ThreadFactory() { // from class: kotlinx.coroutines.ThreadPoolDispatcherKt__ThreadPoolDispatcherKt$newFixedThreadPoolContext$executor$1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, availableProcessors == 1 ? "SystemUIBg" : AnnotationValue$1$$ExternalSyntheticOutline0.m(atomicInteger.incrementAndGet(), "SystemUIBg-"));
                thread.setDaemon(true);
                return thread;
            }
        });
        Intrinsics.checkNotNull(newScheduledThreadPool);
        return new ExecutorCoroutineDispatcherImpl(newScheduledThreadPool);
    }
}

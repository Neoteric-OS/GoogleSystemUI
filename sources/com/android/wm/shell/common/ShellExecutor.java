package com.android.wm.shell.common;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ShellExecutor extends Executor {
    default void executeBlocking(final Runnable runnable) {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ((HandlerExecutor) this).execute(new Runnable() { // from class: com.android.wm.shell.common.ShellExecutor$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                Runnable runnable2 = runnable;
                CountDownLatch countDownLatch2 = countDownLatch;
                runnable2.run();
                countDownLatch2.countDown();
            }
        });
        countDownLatch.await(2, timeUnit);
    }
}

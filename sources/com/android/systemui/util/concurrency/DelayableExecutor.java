package com.android.systemui.util.concurrency;

import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface DelayableExecutor extends Executor {
    default ExecutorImpl.ExecutionToken executeDelayed(Runnable runnable, long j) {
        return ((ExecutorImpl) this).executeDelayed(runnable, j, TimeUnit.MILLISECONDS);
    }
}

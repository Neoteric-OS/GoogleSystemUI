package com.android.systemui.dump;

import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import java.util.concurrent.TimeUnit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LogBufferFreezer {
    public final DumpManager dumpManager;
    public final DelayableExecutor executor;
    public final long freezeDuration;
    public ExecutorImpl.ExecutionToken pendingToken;

    public LogBufferFreezer(DumpManager dumpManager, DelayableExecutor delayableExecutor) {
        long millis = TimeUnit.MINUTES.toMillis(5L);
        this.dumpManager = dumpManager;
        this.executor = delayableExecutor;
        this.freezeDuration = millis;
    }
}

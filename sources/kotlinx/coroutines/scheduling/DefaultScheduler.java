package kotlinx.coroutines.scheduling;

import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.internal.LimitedDispatcherKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DefaultScheduler extends SchedulerCoroutineDispatcher {
    public static final DefaultScheduler INSTANCE;

    static {
        int i = TasksKt.CORE_POOL_SIZE;
        int i2 = TasksKt.MAX_POOL_SIZE;
        long j = TasksKt.IDLE_WORKER_KEEP_ALIVE_NS;
        String str = TasksKt.DEFAULT_SCHEDULER_NAME;
        DefaultScheduler defaultScheduler = new DefaultScheduler();
        defaultScheduler.coroutineScheduler = new CoroutineScheduler(i, i2, j, str);
        INSTANCE = defaultScheduler;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final CoroutineDispatcher limitedParallelism(int i) {
        LimitedDispatcherKt.checkParallelism(1);
        return 1 >= TasksKt.CORE_POOL_SIZE ? this : super.limitedParallelism(1);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final String toString() {
        return "Dispatchers.Default";
    }
}

package kotlinx.coroutines;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ThreadLocalEventLoop {
    public static final ThreadLocal ref = new ThreadLocal();

    public static EventLoopImplBase getEventLoop$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        ThreadLocal threadLocal = ref;
        EventLoopImplBase eventLoopImplBase = (EventLoopImplBase) threadLocal.get();
        if (eventLoopImplBase != null) {
            return eventLoopImplBase;
        }
        BlockingEventLoop blockingEventLoop = new BlockingEventLoop(Thread.currentThread());
        threadLocal.set(blockingEventLoop);
        return blockingEventLoop;
    }
}

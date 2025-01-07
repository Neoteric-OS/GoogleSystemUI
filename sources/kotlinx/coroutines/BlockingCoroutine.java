package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BlockingCoroutine extends AbstractCoroutine {
    public final Thread blockedThread;
    public final EventLoopImplBase eventLoop;

    public BlockingCoroutine(CoroutineContext coroutineContext, Thread thread, EventLoopImplBase eventLoopImplBase) {
        super(coroutineContext, true);
        this.blockedThread = thread;
        this.eventLoop = eventLoopImplBase;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void afterCompletion(Object obj) {
        if (Intrinsics.areEqual(Thread.currentThread(), this.blockedThread)) {
            return;
        }
        LockSupport.unpark(this.blockedThread);
    }
}

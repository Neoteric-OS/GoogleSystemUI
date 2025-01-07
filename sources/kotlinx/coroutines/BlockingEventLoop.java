package kotlinx.coroutines;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BlockingEventLoop extends EventLoopImplBase {
    public final Thread thread;

    public BlockingEventLoop(Thread thread) {
        this.thread = thread;
    }

    @Override // kotlinx.coroutines.EventLoopImplBase
    public final Thread getThread() {
        return this.thread;
    }
}

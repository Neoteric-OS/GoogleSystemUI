package kotlinx.coroutines;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ResumeUndispatchedRunnable implements Runnable {
    public final CancellableContinuationImpl continuation;
    public final ExecutorCoroutineDispatcherImpl dispatcher;

    public ResumeUndispatchedRunnable(ExecutorCoroutineDispatcherImpl executorCoroutineDispatcherImpl, CancellableContinuationImpl cancellableContinuationImpl) {
        this.dispatcher = executorCoroutineDispatcherImpl;
        this.continuation = cancellableContinuationImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.continuation.resumeUndispatched(this.dispatcher);
    }
}

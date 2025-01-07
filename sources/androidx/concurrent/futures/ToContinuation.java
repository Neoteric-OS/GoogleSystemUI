package androidx.concurrent.futures;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import kotlin.Result;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ToContinuation implements Runnable {
    public final CancellableContinuationImpl continuation;
    public final ListenableFuture futureToObserve;

    public ToContinuation(ListenableFuture listenableFuture, CancellableContinuationImpl cancellableContinuationImpl) {
        this.futureToObserve = listenableFuture;
        this.continuation = cancellableContinuationImpl;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.futureToObserve.isCancelled()) {
            this.continuation.cancel(null);
            return;
        }
        try {
            this.continuation.resumeWith(AbstractResolvableFuture.getUninterruptibly(this.futureToObserve));
        } catch (ExecutionException e) {
            CancellableContinuationImpl cancellableContinuationImpl = this.continuation;
            Throwable cause = e.getCause();
            Intrinsics.checkNotNull(cause);
            cancellableContinuationImpl.resumeWith(new Result.Failure(cause));
        }
    }
}

package kotlinx.coroutines;

import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DispatchedTaskKt {
    public static final boolean isCancellableMode(int i) {
        return i == 1 || i == 2;
    }

    public static final void resume(CancellableContinuationImpl cancellableContinuationImpl, Continuation continuation, boolean z) {
        Object obj = cancellableContinuationImpl._state.value;
        Throwable exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = cancellableContinuationImpl.getExceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(obj);
        Object failure = exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host != null ? new Result.Failure(exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host) : cancellableContinuationImpl.getSuccessfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(obj);
        if (!z) {
            continuation.resumeWith(failure);
            return;
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        ContinuationImpl continuationImpl = dispatchedContinuation.continuation;
        Object obj2 = dispatchedContinuation.countOrElement;
        CoroutineContext context = continuationImpl.getContext();
        Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj2);
        UndispatchedCoroutine updateUndispatchedCompletion = updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS ? CoroutineContextKt.updateUndispatchedCompletion(continuationImpl, context, updateThreadContext) : null;
        try {
            dispatchedContinuation.continuation.resumeWith(failure);
        } finally {
            if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            }
        }
    }
}

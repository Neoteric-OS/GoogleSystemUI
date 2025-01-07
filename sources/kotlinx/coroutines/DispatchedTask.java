package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TaskContextImpl;
import kotlinx.coroutines.scheduling.TasksKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DispatchedTask extends Task {
    public int resumeMode;

    public DispatchedTask(int i) {
        super(0L, TasksKt.NonBlockingContext);
        this.resumeMode = i;
    }

    public abstract void cancelCompletedResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj, Throwable th);

    public abstract Continuation getDelegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();

    public Throwable getExceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        if (completedExceptionally != null) {
            return completedExceptionally.cause;
        }
        return null;
    }

    public final void handleFatalException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Throwable th, Throwable th2) {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            kotlin.ExceptionsKt.addSuppressed(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        Intrinsics.checkNotNull(th);
        CoroutineExceptionHandlerKt.handleCoroutineException(new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th), getDelegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host().getContext());
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj = Unit.INSTANCE;
        TaskContextImpl taskContextImpl = this.taskContext;
        try {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) getDelegate$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            ContinuationImpl continuationImpl = dispatchedContinuation.continuation;
            Object obj2 = dispatchedContinuation.countOrElement;
            CoroutineContext context = continuationImpl.getContext();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj2);
            UndispatchedCoroutine updateUndispatchedCompletion = updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS ? CoroutineContextKt.updateUndispatchedCompletion(continuationImpl, context, updateThreadContext) : null;
            try {
                CoroutineContext context2 = continuationImpl.getContext();
                Object takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
                Throwable exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = getExceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host);
                Job job = (exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host == null && DispatchedTaskKt.isCancellableMode(this.resumeMode)) ? (Job) context2.get(Job.Key.$$INSTANCE) : null;
                if (job != null && !job.isActive()) {
                    CancellationException cancellationException = job.getCancellationException();
                    cancelCompletedResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host, cancellationException);
                    continuationImpl.resumeWith(new Result.Failure(cancellationException));
                } else if (exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host != null) {
                    continuationImpl.resumeWith(new Result.Failure(exceptionalResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host));
                } else {
                    continuationImpl.resumeWith(getSuccessfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host));
                }
                if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                    ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                }
                try {
                    taskContextImpl.getClass();
                } catch (Throwable th) {
                    obj = new Result.Failure(th);
                }
                handleFatalException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(null, Result.m1771exceptionOrNullimpl(obj));
            } catch (Throwable th2) {
                if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                    ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                }
                throw th2;
            }
        } catch (Throwable th3) {
            try {
                taskContextImpl.getClass();
            } catch (Throwable th4) {
                obj = new Result.Failure(th4);
            }
            handleFatalException$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(th3, Result.m1771exceptionOrNullimpl(obj));
        }
    }

    public abstract Object takeState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();

    public Object getSuccessfulResult$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(Object obj) {
        return obj;
    }
}

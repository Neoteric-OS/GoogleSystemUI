package kotlinx.coroutines;

import kotlin.Result;
import kotlin.Unit;
import kotlinx.coroutines.JobSupport;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ResumeAwaitOnCompletion extends JobNode {
    public final JobSupport.AwaitContinuation continuation;

    public ResumeAwaitOnCompletion(JobSupport.AwaitContinuation awaitContinuation) {
        this.continuation = awaitContinuation;
    }

    @Override // kotlinx.coroutines.JobNode
    public final void invoke(Throwable th) {
        JobSupport jobSupport = this.job;
        if (jobSupport == null) {
            jobSupport = null;
        }
        Object state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host = jobSupport.getState$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
        boolean z = state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host instanceof CompletedExceptionally;
        JobSupport.AwaitContinuation awaitContinuation = this.continuation;
        if (z) {
            awaitContinuation.resumeWith(new Result.Failure(((CompletedExceptionally) state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host).cause));
        } else {
            awaitContinuation.resumeWith(JobSupportKt.unboxState(state$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host));
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }
}

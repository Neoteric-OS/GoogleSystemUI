package kotlinx.coroutines;

import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.internal.ScopeCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TimeoutCoroutine extends ScopeCoroutine implements Runnable {
    public final long time;

    public TimeoutCoroutine(long j, ContinuationImpl continuationImpl) {
        super(continuationImpl, continuationImpl.getContext());
        this.time = j;
    }

    @Override // kotlinx.coroutines.AbstractCoroutine, kotlinx.coroutines.JobSupport
    public final String nameString$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        return DebugStringsKt.getClassSimpleName(this) + "(timeMillis=" + this.time + ")";
    }

    @Override // java.lang.Runnable
    public final void run() {
        long j = this.time;
        DelayKt.getDelay(this.context);
        cancelImpl$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(new TimeoutCancellationException("Timed out waiting for " + j + " ms", this));
    }
}

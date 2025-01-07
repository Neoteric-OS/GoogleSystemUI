package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChildContinuation extends JobCancellingNode {
    public final CancellableContinuationImpl child;

    public ChildContinuation(CancellableContinuationImpl cancellableContinuationImpl) {
        this.child = cancellableContinuationImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.JobNode
    public final void invoke(Throwable th) {
        CancellableContinuationImpl cancellableContinuationImpl = this.child;
        JobSupport jobSupport = this.job;
        if (jobSupport == null) {
            jobSupport = null;
        }
        Throwable continuationCancellationCause = cancellableContinuationImpl.getContinuationCancellationCause(jobSupport);
        if (cancellableContinuationImpl.isReusable()) {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) cancellableContinuationImpl.delegate;
            AtomicRef atomicRef = dispatchedContinuation._reusableCancellableContinuation;
            while (true) {
                Object obj = atomicRef.value;
                Symbol symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
                if (Intrinsics.areEqual(obj, symbol)) {
                    AtomicRef atomicRef2 = dispatchedContinuation._reusableCancellableContinuation;
                    atomicRef2.getClass();
                    if (AtomicRef.FU.compareAndSet(atomicRef2, symbol, continuationCancellationCause)) {
                        return;
                    }
                } else {
                    if (obj instanceof Throwable) {
                        return;
                    }
                    AtomicRef atomicRef3 = dispatchedContinuation._reusableCancellableContinuation;
                    atomicRef3.getClass();
                    if (AtomicRef.FU.compareAndSet(atomicRef3, obj, null)) {
                        break;
                    }
                }
            }
        }
        cancellableContinuationImpl.cancel(continuationCancellationCause);
        if (cancellableContinuationImpl.isReusable()) {
            return;
        }
        cancellableContinuationImpl.detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
    }
}

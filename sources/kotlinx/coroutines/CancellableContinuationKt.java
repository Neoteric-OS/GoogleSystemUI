package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CancellableContinuationKt {
    public static final CancellableContinuationImpl getOrCreateCancellableContinuation(Continuation continuation) {
        CancellableContinuationImpl cancellableContinuationImpl;
        CancellableContinuationImpl cancellableContinuationImpl2;
        if (!(continuation instanceof DispatchedContinuation)) {
            return new CancellableContinuationImpl(1, continuation);
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        AtomicRef atomicRef = dispatchedContinuation._reusableCancellableContinuation;
        while (true) {
            Object obj = atomicRef.value;
            Symbol symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
            cancellableContinuationImpl = null;
            if (obj == null) {
                dispatchedContinuation._reusableCancellableContinuation.value = symbol;
                cancellableContinuationImpl2 = null;
                break;
            }
            if (obj instanceof CancellableContinuationImpl) {
                AtomicRef atomicRef2 = dispatchedContinuation._reusableCancellableContinuation;
                atomicRef2.getClass();
                if (AtomicRef.FU.compareAndSet(atomicRef2, obj, symbol)) {
                    cancellableContinuationImpl2 = (CancellableContinuationImpl) obj;
                    break;
                }
            } else if (obj != symbol && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
        if (cancellableContinuationImpl2 != null) {
            Object obj2 = cancellableContinuationImpl2._state.value;
            if (!(obj2 instanceof CompletedContinuation) || ((CompletedContinuation) obj2).idempotentResume == null) {
                cancellableContinuationImpl2._decisionAndIndex.value = 536870911;
                cancellableContinuationImpl2._state.value = Active.INSTANCE;
                cancellableContinuationImpl = cancellableContinuationImpl2;
            } else {
                cancellableContinuationImpl2.detachChild$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            }
            if (cancellableContinuationImpl != null) {
                return cancellableContinuationImpl;
            }
        }
        return new CancellableContinuationImpl(2, continuation);
    }
}

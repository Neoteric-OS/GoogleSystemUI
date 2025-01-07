package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.ScopeCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DispatchedCoroutine extends ScopeCoroutine {
    public final AtomicInt _decision;

    public DispatchedCoroutine(Continuation continuation, CoroutineContext coroutineContext) {
        super(continuation, coroutineContext);
        this._decision = AtomicFU.atomic(0);
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.JobSupport
    public final void afterCompletion(Object obj) {
        afterResume(obj);
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.JobSupport
    public final void afterResume(Object obj) {
        AtomicInt atomicInt;
        AtomicInt atomicInt2 = this._decision;
        do {
            int i = atomicInt2.value;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("Already resumed");
                }
                DispatchedContinuationKt.resumeCancellableWith(CompletionStateKt.recoverResult(obj), IntrinsicsKt__IntrinsicsJvmKt.intercepted(this.uCont));
                return;
            }
            atomicInt = this._decision;
            atomicInt.getClass();
        } while (!AtomicInt.FU.compareAndSet(atomicInt, 0, 2));
    }
}

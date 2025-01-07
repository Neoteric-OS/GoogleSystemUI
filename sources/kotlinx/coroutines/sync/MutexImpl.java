package kotlinx.coroutines.sync;

import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.sync.MutexImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MutexImpl extends SemaphoreImpl implements Mutex {
    public final AtomicRef owner;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CancellableContinuationWithOwner implements CancellableContinuation, Waiter {
        public final CancellableContinuationImpl cont;

        public CancellableContinuationWithOwner(CancellableContinuationImpl cancellableContinuationImpl) {
            this.cont = cancellableContinuationImpl;
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public final boolean cancel(Throwable th) {
            return this.cont.cancel(th);
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public final void completeResume(Object obj) {
            this.cont.completeResume(obj);
        }

        @Override // kotlin.coroutines.Continuation
        public final CoroutineContext getContext() {
            return this.cont.context;
        }

        @Override // kotlinx.coroutines.Waiter
        public final void invokeOnCancellation(Segment segment, int i) {
            this.cont.invokeOnCancellation(segment, i);
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public final void resume(Object obj, Function1 function1) {
            Unit unit = Unit.INSTANCE;
            final MutexImpl mutexImpl = MutexImpl.this;
            mutexImpl.owner.value = null;
            this.cont.resume(unit, new Function1() { // from class: kotlinx.coroutines.sync.MutexImpl$CancellableContinuationWithOwner$resume$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    MutexImpl mutexImpl2 = MutexImpl.this;
                    this.getClass();
                    mutexImpl2.unlock(null);
                    return Unit.INSTANCE;
                }
            });
        }

        @Override // kotlin.coroutines.Continuation
        public final void resumeWith(Object obj) {
            this.cont.resumeWith(obj);
        }

        @Override // kotlinx.coroutines.CancellableContinuation
        public final Symbol tryResume(Object obj, Function1 function1) {
            final MutexImpl mutexImpl = MutexImpl.this;
            Function1 function12 = new Function1() { // from class: kotlinx.coroutines.sync.MutexImpl$CancellableContinuationWithOwner$tryResume$token$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    MutexImpl mutexImpl2 = MutexImpl.this;
                    MutexImpl.CancellableContinuationWithOwner cancellableContinuationWithOwner = this;
                    AtomicRef atomicRef = mutexImpl2.owner;
                    cancellableContinuationWithOwner.getClass();
                    atomicRef.value = null;
                    MutexImpl mutexImpl3 = MutexImpl.this;
                    this.getClass();
                    mutexImpl3.unlock(null);
                    return Unit.INSTANCE;
                }
            };
            Symbol tryResumeImpl = this.cont.tryResumeImpl((Unit) obj, function12);
            if (tryResumeImpl != null) {
                mutexImpl.owner.value = null;
            }
            return tryResumeImpl;
        }
    }

    public MutexImpl(boolean z) {
        super(z ? 1 : 0);
        this.owner = AtomicFU.atomic(z ? null : MutexKt.NO_OWNER);
    }

    public final boolean isLocked() {
        return Math.max(this._availablePermits.value, 0) == 0;
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public final Object lock(Continuation continuation) {
        boolean tryLock = tryLock();
        Unit unit = Unit.INSTANCE;
        if (tryLock) {
            return unit;
        }
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
        try {
            acquire(new CancellableContinuationWithOwner(orCreateCancellableContinuation));
            Object result = orCreateCancellableContinuation.getResult();
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (result != coroutineSingletons) {
                result = unit;
            }
            return result == coroutineSingletons ? result : unit;
        } catch (Throwable th) {
            orCreateCancellableContinuation.releaseClaimedReusableContinuation$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host();
            throw th;
        }
    }

    public final String toString() {
        String hexAddress = DebugStringsKt.getHexAddress(this);
        boolean isLocked = isLocked();
        Object obj = this.owner.value;
        StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("Mutex@", hexAddress, "[isLocked=", isLocked, ",owner=");
        m.append(obj);
        m.append("]");
        return m.toString();
    }

    public final boolean tryLock() {
        int i;
        AtomicInt atomicInt;
        char c;
        while (true) {
            int i2 = this._availablePermits.value;
            if (i2 > 1) {
                do {
                    i = this._availablePermits.value;
                    if (i > 1) {
                        atomicInt = this._availablePermits;
                        atomicInt.getClass();
                    }
                } while (!AtomicInt.FU.compareAndSet(atomicInt, i, 1));
            } else {
                if (i2 <= 0) {
                    c = 1;
                    break;
                }
                AtomicInt atomicInt2 = this._availablePermits;
                atomicInt2.getClass();
                if (AtomicInt.FU.compareAndSet(atomicInt2, i2, i2 - 1)) {
                    this.owner.value = null;
                    c = 0;
                    break;
                }
            }
        }
        if (c == 0) {
            return true;
        }
        if (c == 1) {
            return false;
        }
        if (c != 2) {
            throw new IllegalStateException("unexpected");
        }
        throw new IllegalStateException("This mutex is already locked by the specified owner: null".toString());
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public final void unlock(Object obj) {
        while (isLocked()) {
            Object obj2 = this.owner.value;
            Symbol symbol = MutexKt.NO_OWNER;
            if (obj2 != symbol) {
                if (obj2 != obj && obj != null) {
                    throw new IllegalStateException(("This mutex is locked by " + obj2 + ", but " + obj + " is expected").toString());
                }
                AtomicRef atomicRef = this.owner;
                atomicRef.getClass();
                if (AtomicRef.FU.compareAndSet(atomicRef, obj2, symbol)) {
                    release();
                    return;
                }
            }
        }
        throw new IllegalStateException("This mutex is not locked");
    }
}

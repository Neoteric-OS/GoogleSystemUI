package androidx.compose.runtime;

import androidx.compose.runtime.BroadcastFrameClock;
import androidx.compose.runtime.internal.AtomicInt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BroadcastFrameClock implements MonotonicFrameClock {
    public Throwable failureCause;
    public final Function0 onNewAwaiters;
    public final Object lock = new Object();
    public List awaiters = new ArrayList();
    public List spareList = new ArrayList();
    public final AtomicInt hasAwaitersUnlocked = new AtomicInt(0);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class FrameAwaiter {
        public final CancellableContinuationImpl continuation;
        public final Function1 onFrame;

        public FrameAwaiter(Function1 function1, CancellableContinuationImpl cancellableContinuationImpl) {
            this.onFrame = function1;
            this.continuation = cancellableContinuationImpl;
        }
    }

    public BroadcastFrameClock(Function0 function0) {
        this.onNewAwaiters = function0;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final Object fold(Object obj, Function2 function2) {
        return function2.invoke(obj, this);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        return CoroutineContext.DefaultImpls.get(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        return CoroutineContext.DefaultImpls.minusKey(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext plus(CoroutineContext coroutineContext) {
        return CoroutineContext.DefaultImpls.plus(this, coroutineContext);
    }

    public final void sendFrame(long j) {
        Object failure;
        synchronized (this.lock) {
            try {
                List list = this.awaiters;
                this.awaiters = this.spareList;
                this.spareList = list;
                this.hasAwaitersUnlocked.set(0);
                ArrayList arrayList = (ArrayList) list;
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    FrameAwaiter frameAwaiter = (FrameAwaiter) arrayList.get(i);
                    frameAwaiter.getClass();
                    try {
                        failure = frameAwaiter.onFrame.invoke(Long.valueOf(j));
                    } catch (Throwable th) {
                        failure = new Result.Failure(th);
                    }
                    frameAwaiter.continuation.resumeWith(failure);
                }
                arrayList.clear();
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // androidx.compose.runtime.MonotonicFrameClock
    public final Object withFrameNanos(Function1 function1, ContinuationImpl continuationImpl) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuationImpl));
        cancellableContinuationImpl.initCancellability();
        final FrameAwaiter frameAwaiter = new FrameAwaiter(function1, cancellableContinuationImpl);
        synchronized (this.lock) {
            Throwable th = this.failureCause;
            if (th != null) {
                cancellableContinuationImpl.resumeWith(new Result.Failure(th));
            } else {
                boolean isEmpty = this.awaiters.isEmpty();
                this.awaiters.add(frameAwaiter);
                if (isEmpty) {
                    this.hasAwaitersUnlocked.set(1);
                }
                cancellableContinuationImpl.invokeOnCancellation(new Function1() { // from class: androidx.compose.runtime.BroadcastFrameClock$withFrameNanos$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        BroadcastFrameClock broadcastFrameClock = BroadcastFrameClock.this;
                        Object obj2 = broadcastFrameClock.lock;
                        BroadcastFrameClock.FrameAwaiter frameAwaiter2 = frameAwaiter;
                        synchronized (obj2) {
                            broadcastFrameClock.awaiters.remove(frameAwaiter2);
                            if (broadcastFrameClock.awaiters.isEmpty()) {
                                broadcastFrameClock.hasAwaitersUnlocked.set(0);
                            }
                        }
                        return Unit.INSTANCE;
                    }
                });
                if (isEmpty) {
                    try {
                        ((Recomposer$broadcastFrameClock$1) this.onNewAwaiters).invoke();
                    } catch (Throwable th2) {
                        synchronized (this.lock) {
                            try {
                                if (this.failureCause == null) {
                                    this.failureCause = th2;
                                    ArrayList arrayList = (ArrayList) this.awaiters;
                                    int size = arrayList.size();
                                    for (int i = 0; i < size; i++) {
                                        ((FrameAwaiter) arrayList.get(i)).continuation.resumeWith(new Result.Failure(th2));
                                    }
                                    this.awaiters.clear();
                                    this.hasAwaitersUnlocked.set(0);
                                }
                            } catch (Throwable th3) {
                                throw th3;
                            }
                        }
                    }
                }
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return result;
    }
}

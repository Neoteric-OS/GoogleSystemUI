package kotlinx.coroutines;

import java.util.Arrays;
import java.util.concurrent.locks.LockSupport;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.internal.LimitedDispatcherKt;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class EventLoopImplBase extends CoroutineDispatcher implements Delay {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean shared;
    public ArrayDeque unconfinedQueue;
    public long useCount;
    public final AtomicRef _queue = AtomicFU.atomic((Object) null);
    public final AtomicRef _delayed = AtomicFU.atomic((Object) null);
    public final AtomicBoolean _isCompleted = AtomicFU.atomic(false);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DelayedResumeTask extends DelayedTask {
        public final CancellableContinuationImpl cont;

        public DelayedResumeTask(long j, CancellableContinuationImpl cancellableContinuationImpl) {
            super(j);
            this.cont = cancellableContinuationImpl;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.cont.resumeUndispatched(EventLoopImplBase.this);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public final String toString() {
            return super.toString() + this.cont;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DelayedRunnableTask extends DelayedTask {
        public final Runnable block;

        public DelayedRunnableTask(Runnable runnable, long j) {
            super(j);
            this.block = runnable;
        }

        @Override // java.lang.Runnable
        public final void run() {
            this.block.run();
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public final String toString() {
            return super.toString() + this.block;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class DelayedTask implements Runnable, Comparable, DisposableHandle {
        private volatile Object _heap;
        public int index = -1;
        public long nanoTime;

        public DelayedTask(long j) {
            this.nanoTime = j;
        }

        @Override // java.lang.Comparable
        public final int compareTo(Object obj) {
            long j = this.nanoTime - ((DelayedTask) obj).nanoTime;
            if (j > 0) {
                return 1;
            }
            return j < 0 ? -1 : 0;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            synchronized (this) {
                try {
                    Object obj = this._heap;
                    Symbol symbol = EventLoop_commonKt.DISPOSED_TASK;
                    if (obj == symbol) {
                        return;
                    }
                    DelayedTaskQueue delayedTaskQueue = obj instanceof DelayedTaskQueue ? (DelayedTaskQueue) obj : null;
                    if (delayedTaskQueue != null) {
                        synchronized (delayedTaskQueue) {
                            Object obj2 = this._heap;
                            if ((obj2 instanceof DelayedTaskQueue ? (DelayedTaskQueue) obj2 : null) != null) {
                                delayedTaskQueue.removeAtImpl(this.index);
                            }
                        }
                    }
                    this._heap = symbol;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public final int scheduleTask(long j, DelayedTaskQueue delayedTaskQueue, EventLoopImplBase eventLoopImplBase) {
            synchronized (this) {
                if (this._heap == EventLoop_commonKt.DISPOSED_TASK) {
                    return 2;
                }
                synchronized (delayedTaskQueue) {
                    try {
                        DelayedTask[] delayedTaskArr = delayedTaskQueue.a;
                        DelayedTask delayedTask = delayedTaskArr != null ? delayedTaskArr[0] : null;
                        if (eventLoopImplBase._isCompleted.getValue()) {
                            return 1;
                        }
                        if (delayedTask == null) {
                            delayedTaskQueue.timeNow = j;
                        } else {
                            long j2 = delayedTask.nanoTime;
                            if (j2 - j < 0) {
                                j = j2;
                            }
                            if (j - delayedTaskQueue.timeNow > 0) {
                                delayedTaskQueue.timeNow = j;
                            }
                        }
                        long j3 = this.nanoTime;
                        long j4 = delayedTaskQueue.timeNow;
                        if (j3 - j4 < 0) {
                            this.nanoTime = j4;
                        }
                        delayedTaskQueue.addImpl(this);
                        return 0;
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }

        public final void setHeap(DelayedTaskQueue delayedTaskQueue) {
            if (this._heap == EventLoop_commonKt.DISPOSED_TASK) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            this._heap = delayedTaskQueue;
        }

        public String toString() {
            return "Delayed[nanos=" + this.nanoTime + "]";
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DelayedTaskQueue {
        public final AtomicInt _size = AtomicFU.atomic(0);
        public DelayedTask[] a;
        public long timeNow;

        public DelayedTaskQueue(long j) {
            this.timeNow = j;
        }

        public final void addImpl(DelayedTask delayedTask) {
            delayedTask.setHeap(this);
            DelayedTask[] delayedTaskArr = this.a;
            if (delayedTaskArr == null) {
                delayedTaskArr = new DelayedTask[4];
                this.a = delayedTaskArr;
            } else if (this._size.value >= delayedTaskArr.length) {
                delayedTaskArr = (DelayedTask[]) Arrays.copyOf(delayedTaskArr, this._size.value * 2);
                this.a = delayedTaskArr;
            }
            int i = this._size.value;
            this._size.value = i + 1;
            delayedTaskArr[i] = delayedTask;
            delayedTask.index = i;
            siftUpFrom(i);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x005f, code lost:
        
            if (r5.compareTo(r6) < 0) goto L18;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final kotlinx.coroutines.EventLoopImplBase.DelayedTask removeAtImpl(int r8) {
            /*
                r7 = this;
                kotlinx.coroutines.EventLoopImplBase$DelayedTask[] r0 = r7.a
                kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                kotlinx.atomicfu.AtomicInt r1 = r7._size
                int r1 = r1.value
                r2 = -1
                int r1 = r1 + r2
                kotlinx.atomicfu.AtomicInt r3 = r7._size
                r3.value = r1
                kotlinx.atomicfu.AtomicInt r1 = r7._size
                int r1 = r1.value
                if (r8 >= r1) goto L79
                kotlinx.atomicfu.AtomicInt r1 = r7._size
                int r1 = r1.value
                r7.swap(r8, r1)
                int r1 = r8 + (-1)
                int r1 = r1 / 2
                if (r8 <= 0) goto L39
                r3 = r0[r8]
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                r4 = r0[r1]
                kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
                int r3 = r3.compareTo(r4)
                if (r3 >= 0) goto L39
                r7.swap(r8, r1)
                r7.siftUpFrom(r1)
                goto L79
            L39:
                int r1 = r8 * 2
                int r3 = r1 + 1
                kotlinx.atomicfu.AtomicInt r4 = r7._size
                int r4 = r4.value
                if (r3 < r4) goto L44
                goto L79
            L44:
                kotlinx.coroutines.EventLoopImplBase$DelayedTask[] r4 = r7.a
                kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
                int r1 = r1 + 2
                kotlinx.atomicfu.AtomicInt r5 = r7._size
                int r5 = r5.value
                if (r1 >= r5) goto L62
                r5 = r4[r1]
                kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                r6 = r4[r3]
                kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
                int r5 = r5.compareTo(r6)
                if (r5 >= 0) goto L62
                goto L63
            L62:
                r1 = r3
            L63:
                r3 = r4[r8]
                kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                r4 = r4[r1]
                kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
                int r3 = r3.compareTo(r4)
                if (r3 > 0) goto L74
                goto L79
            L74:
                r7.swap(r8, r1)
                r8 = r1
                goto L39
            L79:
                kotlinx.atomicfu.AtomicInt r8 = r7._size
                int r8 = r8.value
                r8 = r0[r8]
                kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
                r1 = 0
                r8.setHeap(r1)
                r8.index = r2
                kotlinx.atomicfu.AtomicInt r7 = r7._size
                int r7 = r7.value
                r0[r7] = r1
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.EventLoopImplBase.DelayedTaskQueue.removeAtImpl(int):kotlinx.coroutines.EventLoopImplBase$DelayedTask");
        }

        public final void siftUpFrom(int i) {
            while (i > 0) {
                DelayedTask[] delayedTaskArr = this.a;
                Intrinsics.checkNotNull(delayedTaskArr);
                int i2 = (i - 1) / 2;
                DelayedTask delayedTask = delayedTaskArr[i2];
                Intrinsics.checkNotNull(delayedTask);
                DelayedTask delayedTask2 = delayedTaskArr[i];
                Intrinsics.checkNotNull(delayedTask2);
                if (delayedTask.compareTo(delayedTask2) <= 0) {
                    return;
                }
                swap(i, i2);
                i = i2;
            }
        }

        public final void swap(int i, int i2) {
            DelayedTask[] delayedTaskArr = this.a;
            Intrinsics.checkNotNull(delayedTaskArr);
            DelayedTask delayedTask = delayedTaskArr[i2];
            Intrinsics.checkNotNull(delayedTask);
            DelayedTask delayedTask2 = delayedTaskArr[i];
            Intrinsics.checkNotNull(delayedTask2);
            delayedTaskArr[i] = delayedTask;
            delayedTaskArr[i2] = delayedTask2;
            delayedTask.index = i;
            delayedTask2.index = i2;
        }
    }

    public final void decrementUseCount(boolean z) {
        long j = this.useCount - (z ? 4294967296L : 1L);
        this.useCount = j;
        if (j <= 0 && this.shared) {
            shutdown();
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        enqueue(runnable);
    }

    public final void dispatchUnconfined(DispatchedTask dispatchedTask) {
        ArrayDeque arrayDeque = this.unconfinedQueue;
        if (arrayDeque == null) {
            arrayDeque = new ArrayDeque();
            this.unconfinedQueue = arrayDeque;
        }
        arrayDeque.addLast(dispatchedTask);
    }

    public void enqueue(Runnable runnable) {
        if (!enqueueImpl(runnable)) {
            DefaultExecutor.INSTANCE.enqueue(runnable);
            return;
        }
        Thread thread = getThread();
        if (Thread.currentThread() != thread) {
            LockSupport.unpark(thread);
        }
    }

    public final boolean enqueueImpl(Runnable runnable) {
        AtomicRef atomicRef = this._queue;
        while (true) {
            Object obj = atomicRef.value;
            if (this._isCompleted.getValue()) {
                return false;
            }
            if (obj == null) {
                AtomicRef atomicRef2 = this._queue;
                atomicRef2.getClass();
                if (AtomicRef.FU.compareAndSet(atomicRef2, null, runnable)) {
                    return true;
                }
            } else if (obj instanceof LockFreeTaskQueueCore) {
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                int addLast = lockFreeTaskQueueCore.addLast(runnable);
                if (addLast == 0) {
                    return true;
                }
                if (addLast == 1) {
                    this._queue.compareAndSet(obj, lockFreeTaskQueueCore.next());
                } else if (addLast == 2) {
                    return false;
                }
            } else {
                if (obj == EventLoop_commonKt.CLOSED_EMPTY) {
                    return false;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore2 = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore2.addLast((Runnable) obj);
                lockFreeTaskQueueCore2.addLast(runnable);
                AtomicRef atomicRef3 = this._queue;
                atomicRef3.getClass();
                if (AtomicRef.FU.compareAndSet(atomicRef3, obj, lockFreeTaskQueueCore2)) {
                    return true;
                }
            }
        }
    }

    public abstract Thread getThread();

    public final void incrementUseCount(boolean z) {
        this.useCount = (z ? 4294967296L : 1L) + this.useCount;
        if (z) {
            return;
        }
        this.shared = true;
    }

    public DisposableHandle invokeOnTimeout(long j, Runnable runnable, CoroutineContext coroutineContext) {
        return DefaultExecutorKt.DefaultDelay.invokeOnTimeout(j, runnable, coroutineContext);
    }

    public final boolean isEmpty() {
        ArrayDeque arrayDeque = this.unconfinedQueue;
        if (!(arrayDeque != null ? arrayDeque.isEmpty() : true)) {
            return false;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed.value;
        if (delayedTaskQueue != null && delayedTaskQueue._size.value != 0) {
            return false;
        }
        Object obj = this._queue.value;
        if (obj == null) {
            return true;
        }
        if (obj instanceof LockFreeTaskQueueCore) {
            long j = ((LockFreeTaskQueueCore) obj)._state.value;
            if (((int) (1073741823 & j)) == ((int) ((j & 1152921503533105152L) >> 30))) {
                return true;
            }
        } else if (obj == EventLoop_commonKt.CLOSED_EMPTY) {
            return true;
        }
        return false;
    }

    public final boolean isUnconfinedLoopActive() {
        return this.useCount >= 4294967296L;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final CoroutineDispatcher limitedParallelism(int i) {
        LimitedDispatcherKt.checkParallelism(1);
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:95:0x0050, code lost:
    
        r7 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long processNextEvent() {
        /*
            Method dump skipped, instructions count: 243
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.EventLoopImplBase.processNextEvent():long");
    }

    public final boolean processUnconfinedEvent() {
        ArrayDeque arrayDeque = this.unconfinedQueue;
        if (arrayDeque == null) {
            return false;
        }
        DispatchedTask dispatchedTask = (DispatchedTask) (arrayDeque.isEmpty() ? null : arrayDeque.removeFirst());
        if (dispatchedTask == null) {
            return false;
        }
        dispatchedTask.run();
        return true;
    }

    public void reschedule(long j, DelayedTask delayedTask) {
        DefaultExecutor.INSTANCE.schedule(j, delayedTask);
    }

    public final void schedule(long j, DelayedTask delayedTask) {
        int scheduleTask;
        Thread thread;
        if (this._isCompleted.getValue()) {
            scheduleTask = 1;
        } else {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed.value;
            if (delayedTaskQueue == null) {
                this._delayed.compareAndSet(null, new DelayedTaskQueue(j));
                Object obj = this._delayed.value;
                Intrinsics.checkNotNull(obj);
                delayedTaskQueue = (DelayedTaskQueue) obj;
            }
            scheduleTask = delayedTask.scheduleTask(j, delayedTaskQueue, this);
        }
        if (scheduleTask != 0) {
            if (scheduleTask == 1) {
                reschedule(j, delayedTask);
                return;
            } else {
                if (scheduleTask != 2) {
                    throw new IllegalStateException("unexpected result");
                }
                return;
            }
        }
        DelayedTaskQueue delayedTaskQueue2 = (DelayedTaskQueue) this._delayed.value;
        if (delayedTaskQueue2 != null) {
            synchronized (delayedTaskQueue2) {
                DelayedTask[] delayedTaskArr = delayedTaskQueue2.a;
                r1 = delayedTaskArr != null ? delayedTaskArr[0] : null;
            }
        }
        if (r1 != delayedTask || Thread.currentThread() == (thread = getThread())) {
            return;
        }
        LockSupport.unpark(thread);
    }

    @Override // kotlinx.coroutines.Delay
    public final void scheduleResumeAfterDelay(long j, CancellableContinuationImpl cancellableContinuationImpl) {
        long j2 = j > 0 ? j >= 9223372036854L ? Long.MAX_VALUE : 1000000 * j : 0L;
        if (j2 < 4611686018427387903L) {
            long nanoTime = System.nanoTime();
            DelayedResumeTask delayedResumeTask = new DelayedResumeTask(j2 + nanoTime, cancellableContinuationImpl);
            schedule(nanoTime, delayedResumeTask);
            cancellableContinuationImpl.invokeOnCancellation(new DisposeOnCancel(delayedResumeTask));
        }
    }

    public void shutdown() {
        DelayedTask removeAtImpl;
        ThreadLocalEventLoop.ref.set(null);
        this._isCompleted._value = 1;
        AtomicRef atomicRef = this._queue;
        while (true) {
            Object obj = atomicRef.value;
            Symbol symbol = EventLoop_commonKt.CLOSED_EMPTY;
            if (obj == null) {
                AtomicRef atomicRef2 = this._queue;
                atomicRef2.getClass();
                if (AtomicRef.FU.compareAndSet(atomicRef2, null, symbol)) {
                    break;
                }
            } else {
                if (obj instanceof LockFreeTaskQueueCore) {
                    ((LockFreeTaskQueueCore) obj).close();
                    break;
                }
                if (obj == symbol) {
                    break;
                }
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore.addLast((Runnable) obj);
                AtomicRef atomicRef3 = this._queue;
                atomicRef3.getClass();
                if (AtomicRef.FU.compareAndSet(atomicRef3, obj, lockFreeTaskQueueCore)) {
                    break;
                }
            }
        }
        while (processNextEvent() <= 0) {
        }
        long nanoTime = System.nanoTime();
        while (true) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed.value;
            if (delayedTaskQueue == null) {
                return;
            }
            synchronized (delayedTaskQueue) {
                removeAtImpl = delayedTaskQueue._size.value > 0 ? delayedTaskQueue.removeAtImpl(0) : null;
            }
            if (removeAtImpl == null) {
                return;
            } else {
                reschedule(nanoTime, removeAtImpl);
            }
        }
    }
}

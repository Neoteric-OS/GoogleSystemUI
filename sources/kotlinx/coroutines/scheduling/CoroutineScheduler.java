package kotlinx.coroutines.scheduling;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.LockSupport;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.random.Random;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.internal.ResizableAtomicArray;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CoroutineScheduler implements Executor, Closeable {
    public static final Symbol NOT_IN_STACK = new Symbol("NOT_IN_STACK");
    public final AtomicBoolean _isTerminated;
    public final AtomicLong controlState;
    public final int corePoolSize;
    public final GlobalQueue globalBlockingQueue;
    public final GlobalQueue globalCpuQueue;
    public final long idleWorkerKeepAliveNs;
    public final int maxPoolSize;
    public final AtomicLong parkedWorkersStack;
    public final String schedulerName;
    public final ResizableAtomicArray workers;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Worker extends Thread {
        private volatile int indexInArray;
        public final WorkQueue localQueue;
        public boolean mayHaveLocalTasks;
        public long minDelayUntilStealableTaskNs;
        private volatile Object nextParkedWorker;
        public int rngState;
        public WorkerState state;
        public final Ref$ObjectRef stolenTask;
        public long terminationDeadline;
        public final AtomicInt workerCtl;

        public Worker(int i) {
            setDaemon(true);
            this.localQueue = new WorkQueue();
            this.stolenTask = new Ref$ObjectRef();
            this.state = WorkerState.DORMANT;
            this.workerCtl = AtomicFU.atomic(0);
            this.nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
            Random.Default.getClass();
            this.rngState = Random.defaultRandom.nextInt();
            setIndexInArray(i);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
        /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final kotlinx.coroutines.scheduling.Task findTask(boolean r11) {
            /*
                r10 = this;
                kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r0 = r10.state
                kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r1 = kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState.CPU_ACQUIRED
                r2 = 0
                r3 = 1
                if (r0 != r1) goto La
                goto L82
            La:
                kotlinx.coroutines.scheduling.CoroutineScheduler r0 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                kotlinx.atomicfu.AtomicLong r1 = r0.controlState
            Le:
                long r6 = r1.value
                r4 = 9223367638808264704(0x7ffffc0000000000, double:NaN)
                long r4 = r4 & r6
                r8 = 42
                long r4 = r4 >> r8
                int r4 = (int) r4
                if (r4 != 0) goto L6a
                kotlinx.coroutines.scheduling.WorkQueue r11 = r10.localQueue
            L1e:
                kotlinx.atomicfu.AtomicRef r0 = r11.lastScheduledTask
                java.lang.Object r0 = r0.value
                kotlinx.coroutines.scheduling.Task r0 = (kotlinx.coroutines.scheduling.Task) r0
                if (r0 != 0) goto L27
                goto L3c
            L27:
                kotlinx.coroutines.scheduling.TaskContextImpl r1 = r0.taskContext
                int r1 = r1.taskMode
                if (r1 != r3) goto L3c
                kotlinx.atomicfu.AtomicRef r1 = r11.lastScheduledTask
                r1.getClass()
                java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = kotlinx.atomicfu.AtomicRef.FU
                boolean r1 = r4.compareAndSet(r1, r0, r2)
                if (r1 == 0) goto L1e
                r2 = r0
                goto L56
            L3c:
                kotlinx.atomicfu.AtomicInt r0 = r11.consumerIndex
                int r0 = r0.value
                kotlinx.atomicfu.AtomicInt r1 = r11.producerIndex
                int r1 = r1.value
            L44:
                if (r0 == r1) goto L56
                kotlinx.atomicfu.AtomicInt r4 = r11.blockingTasksInBuffer
                int r4 = r4.value
                if (r4 != 0) goto L4d
                goto L56
            L4d:
                int r1 = r1 + (-1)
                kotlinx.coroutines.scheduling.Task r4 = r11.tryExtractFromTheMiddle(r1, r3)
                if (r4 == 0) goto L44
                r2 = r4
            L56:
                if (r2 != 0) goto L69
                kotlinx.coroutines.scheduling.CoroutineScheduler r11 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                kotlinx.coroutines.scheduling.GlobalQueue r11 = r11.globalBlockingQueue
                java.lang.Object r11 = r11.removeFirstOrNull()
                r2 = r11
                kotlinx.coroutines.scheduling.Task r2 = (kotlinx.coroutines.scheduling.Task) r2
                if (r2 != 0) goto L69
                kotlinx.coroutines.scheduling.Task r2 = r10.trySteal(r3)
            L69:
                return r2
            L6a:
                r4 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
                long r8 = r6 - r4
                kotlinx.atomicfu.AtomicLong r5 = r0.controlState
                r5.getClass()
                java.util.concurrent.atomic.AtomicLongFieldUpdater r4 = kotlinx.atomicfu.AtomicLong.FU
                boolean r4 = r4.compareAndSet(r5, r6, r8)
                if (r4 == 0) goto Le
                kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r0 = kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState.CPU_ACQUIRED
                r10.state = r0
            L82:
                if (r11 == 0) goto Lbe
                kotlinx.coroutines.scheduling.CoroutineScheduler r11 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                int r11 = r11.corePoolSize
                int r11 = r11 * 2
                int r11 = r10.nextInt(r11)
                if (r11 != 0) goto L91
                goto L92
            L91:
                r3 = 0
            L92:
                if (r3 == 0) goto L9b
                kotlinx.coroutines.scheduling.Task r11 = r10.pollGlobalQueues()
                if (r11 == 0) goto L9b
                goto Lca
            L9b:
                kotlinx.coroutines.scheduling.WorkQueue r11 = r10.localQueue
                kotlinx.atomicfu.AtomicRef r0 = r11.lastScheduledTask
                r0.getClass()
                java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.atomicfu.AtomicRef.FU
                java.lang.Object r0 = r1.getAndSet(r0, r2)
                kotlinx.coroutines.scheduling.Task r0 = (kotlinx.coroutines.scheduling.Task) r0
                if (r0 != 0) goto Lb1
                kotlinx.coroutines.scheduling.Task r11 = r11.pollBuffer()
                goto Lb2
            Lb1:
                r11 = r0
            Lb2:
                if (r11 == 0) goto Lb5
                goto Lca
            Lb5:
                if (r3 != 0) goto Lc5
                kotlinx.coroutines.scheduling.Task r11 = r10.pollGlobalQueues()
                if (r11 == 0) goto Lc5
                goto Lca
            Lbe:
                kotlinx.coroutines.scheduling.Task r11 = r10.pollGlobalQueues()
                if (r11 == 0) goto Lc5
                goto Lca
            Lc5:
                r11 = 3
                kotlinx.coroutines.scheduling.Task r11 = r10.trySteal(r11)
            Lca:
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.Worker.findTask(boolean):kotlinx.coroutines.scheduling.Task");
        }

        public final int getIndexInArray() {
            return this.indexInArray;
        }

        public final Object getNextParkedWorker() {
            return this.nextParkedWorker;
        }

        public final int nextInt(int i) {
            int i2 = this.rngState;
            int i3 = i2 ^ (i2 << 13);
            int i4 = i3 ^ (i3 >> 17);
            int i5 = i4 ^ (i4 << 5);
            this.rngState = i5;
            int i6 = i - 1;
            return (i6 & i) == 0 ? i6 & i5 : (Integer.MAX_VALUE & i5) % i;
        }

        public final Task pollGlobalQueues() {
            if (nextInt(2) == 0) {
                Task task = (Task) CoroutineScheduler.this.globalCpuQueue.removeFirstOrNull();
                return task != null ? task : (Task) CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
            }
            Task task2 = (Task) CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
            return task2 != null ? task2 : (Task) CoroutineScheduler.this.globalCpuQueue.removeFirstOrNull();
        }

        /* JADX WARN: Code restructure failed: missing block: B:68:0x0004, code lost:
        
            continue;
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void run() {
            /*
                Method dump skipped, instructions count: 415
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.Worker.run():void");
        }

        public final void setIndexInArray(int i) {
            setName(CoroutineScheduler.this.schedulerName + "-worker-" + (i == 0 ? "TERMINATED" : String.valueOf(i)));
            this.indexInArray = i;
        }

        public final void setNextParkedWorker(Object obj) {
            this.nextParkedWorker = obj;
        }

        public final boolean tryReleaseCpu(WorkerState workerState) {
            WorkerState workerState2 = this.state;
            boolean z = workerState2 == WorkerState.CPU_ACQUIRED;
            if (z) {
                CoroutineScheduler.this.controlState.addAndGet(4398046511104L);
            }
            if (workerState2 != workerState) {
                this.state = workerState;
            }
            return z;
        }

        public final Task trySteal(int i) {
            Task task;
            long j;
            long j2;
            long j3;
            int i2 = (int) (CoroutineScheduler.this.controlState.value & 2097151);
            if (i2 < 2) {
                return null;
            }
            int nextInt = nextInt(i2);
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            long j4 = Long.MAX_VALUE;
            for (int i3 = 0; i3 < i2; i3++) {
                nextInt++;
                if (nextInt > i2) {
                    nextInt = 1;
                }
                Worker worker = (Worker) coroutineScheduler.workers.get(nextInt);
                if (worker != null && worker != this) {
                    WorkQueue workQueue = worker.localQueue;
                    Ref$ObjectRef ref$ObjectRef = this.stolenTask;
                    if (i == 3) {
                        task = workQueue.pollBuffer();
                    } else {
                        int i4 = workQueue.consumerIndex.value;
                        int i5 = workQueue.producerIndex.value;
                        boolean z = i == 1;
                        while (i4 != i5 && (!z || workQueue.blockingTasksInBuffer.value != 0)) {
                            int i6 = i4 + 1;
                            task = workQueue.tryExtractFromTheMiddle(i4, z);
                            if (task != null) {
                                break;
                            }
                            i4 = i6;
                        }
                        task = null;
                    }
                    if (task != null) {
                        ref$ObjectRef.element = task;
                        j3 = -1;
                        j2 = -1;
                    } else {
                        while (true) {
                            Task task2 = (Task) workQueue.lastScheduledTask.value;
                            j = -2;
                            if (task2 == null) {
                                break;
                            }
                            if (((task2.taskContext.taskMode == 1 ? 1 : 2) & i) == 0) {
                                break;
                            }
                            TasksKt.schedulerTimeSource.getClass();
                            long nanoTime = System.nanoTime() - task2.submissionTime;
                            long j5 = TasksKt.WORK_STEALING_TIME_RESOLUTION_NS;
                            if (nanoTime < j5) {
                                j = j5 - nanoTime;
                                break;
                            }
                            AtomicRef atomicRef = workQueue.lastScheduledTask;
                            atomicRef.getClass();
                            if (AtomicRef.FU.compareAndSet(atomicRef, task2, null)) {
                                ref$ObjectRef.element = task2;
                                j = -1;
                                break;
                            }
                        }
                        j2 = j;
                        j3 = -1;
                    }
                    if (j2 == j3) {
                        Ref$ObjectRef ref$ObjectRef2 = this.stolenTask;
                        Task task3 = (Task) ref$ObjectRef2.element;
                        ref$ObjectRef2.element = null;
                        return task3;
                    }
                    if (j2 > 0) {
                        j4 = Math.min(j4, j2);
                    }
                }
            }
            if (j4 == Long.MAX_VALUE) {
                j4 = 0;
            }
            this.minDelayUntilStealableTaskNs = j4;
            return null;
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WorkerState {
        public static final /* synthetic */ WorkerState[] $VALUES;
        public static final WorkerState BLOCKING;
        public static final WorkerState CPU_ACQUIRED;
        public static final WorkerState DORMANT;
        public static final WorkerState PARKING;
        public static final WorkerState TERMINATED;

        static {
            WorkerState workerState = new WorkerState("CPU_ACQUIRED", 0);
            CPU_ACQUIRED = workerState;
            WorkerState workerState2 = new WorkerState("BLOCKING", 1);
            BLOCKING = workerState2;
            WorkerState workerState3 = new WorkerState("PARKING", 2);
            PARKING = workerState3;
            WorkerState workerState4 = new WorkerState("DORMANT", 3);
            DORMANT = workerState4;
            WorkerState workerState5 = new WorkerState("TERMINATED", 4);
            TERMINATED = workerState5;
            WorkerState[] workerStateArr = {workerState, workerState2, workerState3, workerState4, workerState5};
            $VALUES = workerStateArr;
            EnumEntriesKt.enumEntries(workerStateArr);
        }

        public static WorkerState valueOf(String str) {
            return (WorkerState) Enum.valueOf(WorkerState.class, str);
        }

        public static WorkerState[] values() {
            return (WorkerState[]) $VALUES.clone();
        }
    }

    public CoroutineScheduler(int i, int i2, long j, String str) {
        this.corePoolSize = i;
        this.maxPoolSize = i2;
        this.idleWorkerKeepAliveNs = j;
        this.schedulerName = str;
        if (i < 1) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Core pool size ", " should be at least 1", i).toString());
        }
        if (i2 < i) {
            throw new IllegalArgumentException(ListImplementation$$ExternalSyntheticOutline0.m("Max pool size ", i2, i, " should be greater than or equals to core pool size ").toString());
        }
        if (i2 > 2097150) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Max pool size ", " should not exceed maximal supported number of threads 2097150", i2).toString());
        }
        if (j <= 0) {
            throw new IllegalArgumentException(("Idle worker keep alive time " + j + " must be positive").toString());
        }
        this.globalCpuQueue = new GlobalQueue();
        this.globalBlockingQueue = new GlobalQueue();
        this.parkedWorkersStack = AtomicFU.atomic(0L);
        this.workers = new ResizableAtomicArray((i + 1) * 2);
        this.controlState = AtomicFU.atomic(i << 42);
        this._isTerminated = AtomicFU.atomic(false);
    }

    public static /* synthetic */ void dispatch$default(CoroutineScheduler coroutineScheduler, Runnable runnable, int i) {
        coroutineScheduler.dispatch(runnable, TasksKt.NonBlockingContext, (i & 4) == 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x009e, code lost:
    
        if (r1 == null) goto L44;
     */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void close() {
        /*
            Method dump skipped, instructions count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.close():void");
    }

    public final int createNewWorker() {
        synchronized (this.workers) {
            try {
                if (this._isTerminated.getValue()) {
                    return -1;
                }
                long j = this.controlState.value;
                int i = (int) (j & 2097151);
                int i2 = i - ((int) ((j & 4398044413952L) >> 21));
                if (i2 < 0) {
                    i2 = 0;
                }
                if (i2 >= this.corePoolSize) {
                    return 0;
                }
                if (i >= this.maxPoolSize) {
                    return 0;
                }
                int i3 = ((int) (this.controlState.value & 2097151)) + 1;
                if (i3 <= 0 || this.workers.get(i3) != null) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                Worker worker = new Worker(i3);
                this.workers.setSynchronized(i3, worker);
                AtomicLong atomicLong = this.controlState;
                atomicLong.getClass();
                if (i3 != ((int) (2097151 & AtomicLong.FU.incrementAndGet(atomicLong)))) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                int i4 = i2 + 1;
                worker.start();
                return i4;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void dispatch(Runnable runnable, TaskContextImpl taskContextImpl, boolean z) {
        Task taskImpl;
        long j;
        WorkerState workerState;
        TasksKt.schedulerTimeSource.getClass();
        long nanoTime = System.nanoTime();
        if (runnable instanceof Task) {
            taskImpl = (Task) runnable;
            taskImpl.submissionTime = nanoTime;
            taskImpl.taskContext = taskContextImpl;
        } else {
            taskImpl = new TaskImpl(runnable, nanoTime, taskContextImpl);
        }
        boolean z2 = false;
        boolean z3 = taskImpl.taskContext.taskMode == 1;
        if (z3) {
            AtomicLong atomicLong = this.controlState;
            atomicLong.getClass();
            j = AtomicLong.FU.addAndGet(atomicLong, 2097152L);
        } else {
            j = 0;
        }
        Thread currentThread = Thread.currentThread();
        Worker worker = currentThread instanceof Worker ? (Worker) currentThread : null;
        if (worker == null || !Intrinsics.areEqual(CoroutineScheduler.this, this)) {
            worker = null;
        }
        if (worker != null && (workerState = worker.state) != WorkerState.TERMINATED && (taskImpl.taskContext.taskMode != 0 || workerState != WorkerState.BLOCKING)) {
            worker.mayHaveLocalTasks = true;
            WorkQueue workQueue = worker.localQueue;
            if (z) {
                taskImpl = workQueue.addLast(taskImpl);
            } else {
                AtomicRef atomicRef = workQueue.lastScheduledTask;
                atomicRef.getClass();
                Task task = (Task) AtomicRef.FU.getAndSet(atomicRef, taskImpl);
                taskImpl = task == null ? null : workQueue.addLast(task);
            }
        }
        if (taskImpl != null) {
            if (!(taskImpl.taskContext.taskMode == 1 ? this.globalBlockingQueue.addLast(taskImpl) : this.globalCpuQueue.addLast(taskImpl))) {
                throw new RejectedExecutionException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(this.schedulerName, " was terminated"));
            }
        }
        if (z && worker != null) {
            z2 = true;
        }
        if (z3) {
            if (z2 || tryUnpark() || tryCreateWorker(j)) {
                return;
            }
            tryUnpark();
            return;
        }
        if (z2 || tryUnpark() || tryCreateWorker(this.controlState.value)) {
            return;
        }
        tryUnpark();
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        dispatch$default(this, runnable, 6);
    }

    public final void parkedWorkersStackTopUpdate(Worker worker, int i, int i2) {
        AtomicLong atomicLong = this.parkedWorkersStack;
        while (true) {
            long j = atomicLong.value;
            int i3 = (int) (2097151 & j);
            long j2 = (2097152 + j) & (-2097152);
            if (i3 == i) {
                if (i2 == 0) {
                    Object nextParkedWorker = worker.getNextParkedWorker();
                    while (true) {
                        if (nextParkedWorker == NOT_IN_STACK) {
                            i3 = -1;
                            break;
                        }
                        if (nextParkedWorker == null) {
                            i3 = 0;
                            break;
                        }
                        Worker worker2 = (Worker) nextParkedWorker;
                        int indexInArray = worker2.getIndexInArray();
                        if (indexInArray != 0) {
                            i3 = indexInArray;
                            break;
                        }
                        nextParkedWorker = worker2.getNextParkedWorker();
                    }
                } else {
                    i3 = i2;
                }
            }
            if (i3 >= 0) {
                AtomicLong atomicLong2 = this.parkedWorkersStack;
                long j3 = j2 | i3;
                atomicLong2.getClass();
                if (AtomicLong.FU.compareAndSet(atomicLong2, j, j3)) {
                    return;
                }
            }
        }
    }

    public final String toString() {
        ArrayList arrayList = new ArrayList();
        int currentLength = this.workers.currentLength();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 1; i6 < currentLength; i6++) {
            Worker worker = (Worker) this.workers.get(i6);
            if (worker != null) {
                WorkQueue workQueue = worker.localQueue;
                int i7 = workQueue.lastScheduledTask.value != null ? (workQueue.producerIndex.value - workQueue.consumerIndex.value) + 1 : workQueue.producerIndex.value - workQueue.consumerIndex.value;
                int ordinal = worker.state.ordinal();
                if (ordinal == 0) {
                    i++;
                    arrayList.add(i7 + "c");
                } else if (ordinal == 1) {
                    i2++;
                    arrayList.add(i7 + "b");
                } else if (ordinal == 2) {
                    i3++;
                } else if (ordinal == 3) {
                    i4++;
                    if (i7 > 0) {
                        arrayList.add(i7 + "d");
                    }
                } else if (ordinal == 4) {
                    i5++;
                }
            }
        }
        long j = this.controlState.value;
        return this.schedulerName + "@" + DebugStringsKt.getHexAddress(this) + "[Pool Size {core = " + this.corePoolSize + ", max = " + this.maxPoolSize + "}, Worker States {CPU = " + i + ", blocking = " + i2 + ", parked = " + i3 + ", dormant = " + i4 + ", terminated = " + i5 + "}, running workers queues = " + arrayList + ", global CPU queue size = " + this.globalCpuQueue.getSize() + ", global blocking queue size = " + this.globalBlockingQueue.getSize() + ", Control State {created workers= " + ((int) (j & 2097151)) + ", blocking tasks = " + ((int) ((4398044413952L & j) >> 21)) + ", CPUs acquired = " + (this.corePoolSize - ((int) ((j & 9223367638808264704L) >> 42))) + "}]";
    }

    public final boolean tryCreateWorker(long j) {
        int i = ((int) (2097151 & j)) - ((int) ((j & 4398044413952L) >> 21));
        if (i < 0) {
            i = 0;
        }
        if (i < this.corePoolSize) {
            int createNewWorker = createNewWorker();
            if (createNewWorker == 1 && this.corePoolSize > 1) {
                createNewWorker();
            }
            if (createNewWorker > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean tryUnpark() {
        Worker worker;
        AtomicInt atomicInt;
        Symbol symbol;
        int i;
        do {
            AtomicLong atomicLong = this.parkedWorkersStack;
            while (true) {
                long j = atomicLong.value;
                worker = (Worker) this.workers.get((int) (2097151 & j));
                if (worker == null) {
                    worker = null;
                    break;
                }
                long j2 = (2097152 + j) & (-2097152);
                Object nextParkedWorker = worker.getNextParkedWorker();
                while (true) {
                    symbol = NOT_IN_STACK;
                    if (nextParkedWorker == symbol) {
                        i = -1;
                        break;
                    }
                    if (nextParkedWorker == null) {
                        i = 0;
                        break;
                    }
                    Worker worker2 = (Worker) nextParkedWorker;
                    i = worker2.getIndexInArray();
                    if (i != 0) {
                        break;
                    }
                    nextParkedWorker = worker2.getNextParkedWorker();
                }
                if (i >= 0) {
                    AtomicLong atomicLong2 = this.parkedWorkersStack;
                    long j3 = i | j2;
                    atomicLong2.getClass();
                    if (AtomicLong.FU.compareAndSet(atomicLong2, j, j3)) {
                        worker.setNextParkedWorker(symbol);
                        break;
                    }
                }
            }
            if (worker == null) {
                return false;
            }
            atomicInt = worker.workerCtl;
            atomicInt.getClass();
        } while (!AtomicInt.FU.compareAndSet(atomicInt, -1, 0));
        LockSupport.unpark(worker);
        return true;
    }
}

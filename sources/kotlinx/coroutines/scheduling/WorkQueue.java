package kotlinx.coroutines.scheduling;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WorkQueue {
    public final AtomicReferenceArray buffer = new AtomicReferenceArray(128);
    public final AtomicRef lastScheduledTask = AtomicFU.atomic((Object) null);
    public final AtomicInt producerIndex = AtomicFU.atomic(0);
    public final AtomicInt consumerIndex = AtomicFU.atomic(0);
    public final AtomicInt blockingTasksInBuffer = AtomicFU.atomic(0);

    public final Task addLast(Task task) {
        if (this.producerIndex.value - this.consumerIndex.value == 127) {
            return task;
        }
        if (task.taskContext.taskMode == 1) {
            this.blockingTasksInBuffer.incrementAndGet();
        }
        int i = this.producerIndex.value & 127;
        while (this.buffer.get(i) != null) {
            Thread.yield();
        }
        this.buffer.lazySet(i, task);
        this.producerIndex.incrementAndGet();
        return null;
    }

    public final Task pollBuffer() {
        Task task;
        while (true) {
            int i = this.consumerIndex.value;
            if (i - this.producerIndex.value == 0) {
                return null;
            }
            int i2 = i & 127;
            AtomicInt atomicInt = this.consumerIndex;
            atomicInt.getClass();
            if (AtomicInt.FU.compareAndSet(atomicInt, i, i + 1) && (task = (Task) this.buffer.getAndSet(i2, null)) != null) {
                if (task.taskContext.taskMode == 1) {
                    this.blockingTasksInBuffer.decrementAndGet();
                }
                return task;
            }
        }
    }

    public final Task tryExtractFromTheMiddle(int i, boolean z) {
        int i2 = i & 127;
        Task task = (Task) this.buffer.get(i2);
        if (task != null) {
            if ((task.taskContext.taskMode == 1) == z && this.buffer.compareAndSet(i2, task, null)) {
                if (z) {
                    this.blockingTasksInBuffer.decrementAndGet();
                }
                return task;
            }
        }
        return null;
    }
}

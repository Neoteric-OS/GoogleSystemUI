package kotlinx.coroutines.sync;

import kotlin.coroutines.CoroutineContext;
import kotlinx.atomicfu.AtomicArray;
import kotlinx.coroutines.internal.Segment;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SemaphoreSegment extends Segment {
    public final AtomicArray acquirers;

    public SemaphoreSegment(long j, SemaphoreSegment semaphoreSegment, int i) {
        super(j, semaphoreSegment, i);
        this.acquirers = new AtomicArray(SemaphoreKt.SEGMENT_SIZE);
    }

    @Override // kotlinx.coroutines.internal.Segment
    public final int getNumberOfSlots() {
        return SemaphoreKt.SEGMENT_SIZE;
    }

    @Override // kotlinx.coroutines.internal.Segment
    public final void onCancellation(int i, CoroutineContext coroutineContext) {
        this.acquirers.array[i].value = SemaphoreKt.CANCELLED;
        onSlotCleaned();
    }

    public final String toString() {
        return "SemaphoreSegment[id=" + this.id + ", hashCode=" + hashCode() + "]";
    }
}

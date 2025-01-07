package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectImplementation;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.sync.MutexImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SemaphoreImpl {
    public final AtomicInt _availablePermits;
    public final AtomicLong deqIdx = AtomicFU.atomic(0L);
    public final AtomicLong enqIdx = AtomicFU.atomic(0L);
    public final AtomicRef head;
    public final Function1 onCancellationRelease;
    public final AtomicRef tail;

    public SemaphoreImpl(int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("The number of acquired permits should be in 0..1".toString());
        }
        SemaphoreSegment semaphoreSegment = new SemaphoreSegment(0L, null, 2);
        this.head = AtomicFU.atomic(semaphoreSegment);
        this.tail = AtomicFU.atomic(semaphoreSegment);
        this._availablePermits = AtomicFU.atomic(1 - i);
        this.onCancellationRelease = new Function1() { // from class: kotlinx.coroutines.sync.SemaphoreImpl$onCancellationRelease$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SemaphoreImpl.this.release();
                return Unit.INSTANCE;
            }
        };
    }

    public final void acquire(MutexImpl.CancellableContinuationWithOwner cancellableContinuationWithOwner) {
        Object findSegmentInternal;
        long j;
        while (true) {
            AtomicInt atomicInt = this._availablePermits;
            atomicInt.getClass();
            int andDecrement = AtomicInt.FU.getAndDecrement(atomicInt);
            if (andDecrement <= 1) {
                Object obj = Unit.INSTANCE;
                if (andDecrement > 0) {
                    cancellableContinuationWithOwner.resume(obj, this.onCancellationRelease);
                    return;
                }
                SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.tail.value;
                AtomicLong atomicLong = this.enqIdx;
                atomicLong.getClass();
                long andIncrement = AtomicLong.FU.getAndIncrement(atomicLong);
                SemaphoreImpl$addAcquireToQueue$createNewSegment$1 semaphoreImpl$addAcquireToQueue$createNewSegment$1 = SemaphoreImpl$addAcquireToQueue$createNewSegment$1.INSTANCE;
                AtomicRef atomicRef = this.tail;
                long j2 = andIncrement / SemaphoreKt.SEGMENT_SIZE;
                while (true) {
                    findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(semaphoreSegment, j2, semaphoreImpl$addAcquireToQueue$createNewSegment$1);
                    if (!SegmentOrClosed.m1795isClosedimpl(findSegmentInternal)) {
                        Segment m1794getSegmentimpl = SegmentOrClosed.m1794getSegmentimpl(findSegmentInternal);
                        while (true) {
                            Segment segment = (Segment) atomicRef.value;
                            j = j2;
                            if (segment.id >= m1794getSegmentimpl.id) {
                                break;
                            }
                            if (!m1794getSegmentimpl.tryIncPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                                break;
                            }
                            if (!AtomicRef.FU.compareAndSet(atomicRef, segment, m1794getSegmentimpl)) {
                                if (m1794getSegmentimpl.decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                                    m1794getSegmentimpl.remove();
                                }
                                j2 = j;
                            } else if (segment.decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                                segment.remove();
                            }
                        }
                    } else {
                        break;
                    }
                    j2 = j;
                }
                SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) SegmentOrClosed.m1794getSegmentimpl(findSegmentInternal);
                int i = (int) (andIncrement % SemaphoreKt.SEGMENT_SIZE);
                AtomicRef atomicRef2 = semaphoreSegment2.acquirers.array[i];
                atomicRef2.getClass();
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = AtomicRef.FU;
                if (atomicReferenceFieldUpdater.compareAndSet(atomicRef2, null, cancellableContinuationWithOwner)) {
                    cancellableContinuationWithOwner.invokeOnCancellation(semaphoreSegment2, i);
                    return;
                }
                Symbol symbol = SemaphoreKt.PERMIT;
                Symbol symbol2 = SemaphoreKt.TAKEN;
                AtomicRef atomicRef3 = semaphoreSegment2.acquirers.array[i];
                atomicRef3.getClass();
                if (atomicReferenceFieldUpdater.compareAndSet(atomicRef3, symbol, symbol2)) {
                    cancellableContinuationWithOwner.resume(obj, this.onCancellationRelease);
                    return;
                }
            }
        }
    }

    public final void release() {
        boolean z;
        int i;
        AtomicInt atomicInt;
        Object findSegmentInternal;
        do {
            AtomicInt atomicInt2 = this._availablePermits;
            atomicInt2.getClass();
            int andIncrement = AtomicInt.FU.getAndIncrement(atomicInt2);
            z = true;
            if (andIncrement >= 1) {
                do {
                    i = this._availablePermits.value;
                    if (i <= 1) {
                        break;
                    }
                    atomicInt = this._availablePermits;
                    atomicInt.getClass();
                } while (!AtomicInt.FU.compareAndSet(atomicInt, i, 1));
                throw new IllegalStateException("The number of released permits cannot be greater than 1".toString());
            }
            if (andIncrement >= 0) {
                return;
            }
            SemaphoreSegment semaphoreSegment = (SemaphoreSegment) this.head.value;
            AtomicLong atomicLong = this.deqIdx;
            atomicLong.getClass();
            long andIncrement2 = AtomicLong.FU.getAndIncrement(atomicLong);
            long j = andIncrement2 / SemaphoreKt.SEGMENT_SIZE;
            SemaphoreImpl$tryResumeNextFromQueue$createNewSegment$1 semaphoreImpl$tryResumeNextFromQueue$createNewSegment$1 = SemaphoreImpl$tryResumeNextFromQueue$createNewSegment$1.INSTANCE;
            AtomicRef atomicRef = this.head;
            while (true) {
                findSegmentInternal = ConcurrentLinkedListKt.findSegmentInternal(semaphoreSegment, j, semaphoreImpl$tryResumeNextFromQueue$createNewSegment$1);
                if (!SegmentOrClosed.m1795isClosedimpl(findSegmentInternal)) {
                    Segment m1794getSegmentimpl = SegmentOrClosed.m1794getSegmentimpl(findSegmentInternal);
                    while (true) {
                        Segment segment = (Segment) atomicRef.value;
                        if (segment.id >= m1794getSegmentimpl.id) {
                            break;
                        }
                        if (!m1794getSegmentimpl.tryIncPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                            break;
                        }
                        if (AtomicRef.FU.compareAndSet(atomicRef, segment, m1794getSegmentimpl)) {
                            if (segment.decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                                segment.remove();
                            }
                        } else if (m1794getSegmentimpl.decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host()) {
                            m1794getSegmentimpl.remove();
                        }
                    }
                } else {
                    break;
                }
            }
            SemaphoreSegment semaphoreSegment2 = (SemaphoreSegment) SegmentOrClosed.m1794getSegmentimpl(findSegmentInternal);
            semaphoreSegment2.cleanPrev();
            int i2 = 0;
            if (semaphoreSegment2.id <= j) {
                int i3 = (int) (andIncrement2 % SemaphoreKt.SEGMENT_SIZE);
                Symbol symbol = SemaphoreKt.PERMIT;
                AtomicRef atomicRef2 = semaphoreSegment2.acquirers.array[i3];
                atomicRef2.getClass();
                Object andSet = AtomicRef.FU.getAndSet(atomicRef2, symbol);
                if (andSet == null) {
                    int i4 = SemaphoreKt.MAX_SPIN_CYCLES;
                    while (true) {
                        if (i2 >= i4) {
                            Symbol symbol2 = SemaphoreKt.PERMIT;
                            Symbol symbol3 = SemaphoreKt.BROKEN;
                            AtomicRef atomicRef3 = semaphoreSegment2.acquirers.array[i3];
                            atomicRef3.getClass();
                            z = true ^ AtomicRef.FU.compareAndSet(atomicRef3, symbol2, symbol3);
                            break;
                        }
                        if (semaphoreSegment2.acquirers.array[i3].value == SemaphoreKt.TAKEN) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                } else if (andSet != SemaphoreKt.CANCELLED) {
                    boolean z2 = andSet instanceof CancellableContinuation;
                    Unit unit = Unit.INSTANCE;
                    if (z2) {
                        CancellableContinuation cancellableContinuation = (CancellableContinuation) andSet;
                        Symbol tryResume = cancellableContinuation.tryResume(unit, this.onCancellationRelease);
                        if (tryResume != null) {
                            cancellableContinuation.completeResume(tryResume);
                        }
                    } else {
                        if (!(andSet instanceof SelectInstance)) {
                            throw new IllegalStateException(("unexpected: " + andSet).toString());
                        }
                        if (((SelectImplementation) ((SelectInstance) andSet)).trySelectInternal(this, unit) == 0) {
                        }
                    }
                }
            }
            z = false;
        } while (!z);
    }
}

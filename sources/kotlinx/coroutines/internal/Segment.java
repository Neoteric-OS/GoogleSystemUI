package kotlinx.coroutines.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.NotCompleted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Segment implements NotCompleted {
    public final AtomicRef _next = AtomicFU.atomic((Object) null);
    public final AtomicRef _prev;
    public final AtomicInt cleanedAndPointers;
    public final long id;

    public Segment(long j, Segment segment, int i) {
        this._prev = AtomicFU.atomic(segment);
        this.id = j;
        this.cleanedAndPointers = AtomicFU.atomic(i << 16);
    }

    public final void cleanPrev() {
        this._prev.lazySet(null);
    }

    public final boolean decPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        AtomicInt atomicInt = this.cleanedAndPointers;
        atomicInt.getClass();
        return AtomicInt.FU.addAndGet(atomicInt, -65536) == getNumberOfSlots() && getNext() != null;
    }

    public final Segment getNext() {
        Object obj = this._next.value;
        if (obj == ConcurrentLinkedListKt.CLOSED) {
            return null;
        }
        return (Segment) obj;
    }

    public abstract int getNumberOfSlots();

    public final boolean isRemoved() {
        return this.cleanedAndPointers.value == getNumberOfSlots() && getNext() != null;
    }

    public abstract void onCancellation(int i, CoroutineContext coroutineContext);

    public final void onSlotCleaned() {
        AtomicInt atomicInt = this.cleanedAndPointers;
        atomicInt.getClass();
        if (AtomicInt.FU.incrementAndGet(atomicInt) == getNumberOfSlots()) {
            remove();
        }
    }

    public final void remove() {
        Object obj;
        Segment next;
        if (getNext() == null) {
            return;
        }
        while (true) {
            Segment segment = (Segment) this._prev.value;
            while (segment != null && segment.isRemoved()) {
                segment = (Segment) segment._prev.value;
            }
            Segment next2 = getNext();
            Intrinsics.checkNotNull(next2);
            while (next2.isRemoved() && (next = next2.getNext()) != null) {
                next2 = next;
            }
            AtomicRef atomicRef = next2._prev;
            do {
                obj = atomicRef.value;
            } while (!AtomicRef.FU.compareAndSet(atomicRef, obj, ((Segment) obj) == null ? null : segment));
            if (segment != null) {
                segment._next.value = next2;
            }
            if (!next2.isRemoved() || next2.getNext() == null) {
                if (segment == null || !segment.isRemoved()) {
                    return;
                }
            }
        }
    }

    public final boolean tryIncPointers$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host() {
        int i;
        AtomicInt atomicInt = this.cleanedAndPointers;
        do {
            i = atomicInt.value;
            if (i == getNumberOfSlots() && getNext() != null) {
                return false;
            }
        } while (!AtomicInt.FU.compareAndSet(atomicInt, i, 65536 + i));
        return true;
    }
}

package kotlinx.coroutines.internal;

import kotlin.jvm.functions.Function2;
import kotlinx.atomicfu.AtomicRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ConcurrentLinkedListKt {
    public static final Symbol CLOSED = new Symbol("CLOSED");

    public static final Object findSegmentInternal(Segment segment, long j, Function2 function2) {
        while (true) {
            if (segment.id >= j && !segment.isRemoved()) {
                return segment;
            }
            Object obj = segment._next.value;
            Symbol symbol = CLOSED;
            if (obj == symbol) {
                return symbol;
            }
            Segment segment2 = (Segment) obj;
            if (segment2 == null) {
                segment2 = (Segment) function2.invoke(Long.valueOf(segment.id + 1), segment);
                AtomicRef atomicRef = segment._next;
                atomicRef.getClass();
                if (AtomicRef.FU.compareAndSet(atomicRef, null, segment2)) {
                    if (segment.isRemoved()) {
                        segment.remove();
                    }
                }
            }
            segment = segment2;
        }
    }
}

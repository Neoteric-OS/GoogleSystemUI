package kotlinx.coroutines.internal;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SegmentOrClosed {
    /* renamed from: getSegment-impl, reason: not valid java name */
    public static final Segment m1794getSegmentimpl(Object obj) {
        if (obj != ConcurrentLinkedListKt.CLOSED) {
            return (Segment) obj;
        }
        throw new IllegalStateException("Does not contain segment");
    }

    /* renamed from: isClosed-impl, reason: not valid java name */
    public static final boolean m1795isClosedimpl(Object obj) {
        return obj == ConcurrentLinkedListKt.CLOSED;
    }
}

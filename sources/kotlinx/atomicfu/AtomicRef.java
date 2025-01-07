package kotlinx.atomicfu;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AtomicRef {
    public static final AtomicReferenceFieldUpdater FU = AtomicReferenceFieldUpdater.newUpdater(AtomicRef.class, Object.class, "value");
    public volatile Object value;

    public final boolean compareAndSet(Object obj, Object obj2) {
        return FU.compareAndSet(this, obj, obj2);
    }

    public final void lazySet(Object obj) {
        FU.lazySet(this, obj);
    }

    public final String toString() {
        return String.valueOf(this.value);
    }
}

package kotlinx.atomicfu;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AtomicLong {
    public static final AtomicLongFieldUpdater FU = AtomicLongFieldUpdater.newUpdater(AtomicLong.class, "value");
    public volatile long value;

    public final long addAndGet(long j) {
        return FU.addAndGet(this, j);
    }

    public final boolean compareAndSet(long j, long j2) {
        return FU.compareAndSet(this, j, j2);
    }

    public final String toString() {
        return String.valueOf(this.value);
    }
}

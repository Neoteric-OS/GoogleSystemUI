package kotlinx.atomicfu;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AtomicInt {
    public static final AtomicIntegerFieldUpdater FU = AtomicIntegerFieldUpdater.newUpdater(AtomicInt.class, "value");
    public volatile int value;

    public final int decrementAndGet() {
        return FU.decrementAndGet(this);
    }

    public final int incrementAndGet() {
        return FU.incrementAndGet(this);
    }

    public final String toString() {
        return String.valueOf(this.value);
    }
}

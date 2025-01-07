package androidx.room.concurrent;

import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CloseBarrier {
    public final AtomicInt blockers = AtomicFU.atomic(0);
    public final AtomicBoolean closeInitiated = AtomicFU.atomic(false);

    public final boolean block$room_runtime_release() {
        synchronized (this) {
            if (this.closeInitiated.getValue()) {
                return false;
            }
            this.blockers.incrementAndGet();
            return true;
        }
    }

    public final void unblock$room_runtime_release() {
        synchronized (this) {
            this.blockers.decrementAndGet();
            if (this.blockers.value < 0) {
                throw new IllegalStateException("Unbalanced call to unblock() detected.");
            }
        }
    }
}

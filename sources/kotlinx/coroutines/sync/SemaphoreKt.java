package kotlinx.coroutines.sync;

import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.SystemPropsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SemaphoreKt {
    public static final int MAX_SPIN_CYCLES = SystemPropsKt.systemProp$default("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 12);
    public static final Symbol PERMIT = new Symbol("PERMIT");
    public static final Symbol TAKEN = new Symbol("TAKEN");
    public static final Symbol BROKEN = new Symbol("BROKEN");
    public static final Symbol CANCELLED = new Symbol("CANCELLED");
    public static final int SEGMENT_SIZE = SystemPropsKt.systemProp$default("kotlinx.coroutines.semaphore.segmentSize", 16, 12);
}

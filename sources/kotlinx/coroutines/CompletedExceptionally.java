package kotlinx.coroutines;

import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class CompletedExceptionally {
    public final AtomicBoolean _handled;
    public final Throwable cause;

    public CompletedExceptionally(Throwable th, boolean z) {
        this.cause = th;
        this._handled = AtomicFU.atomic(z);
    }

    public final String toString() {
        return DebugStringsKt.getClassSimpleName(this) + "[" + this.cause + "]";
    }
}

package kotlinx.coroutines;

import java.util.concurrent.Future;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisposableFutureHandle implements DisposableHandle {
    public final Future future;

    public DisposableFutureHandle(Future future) {
        this.future = future;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public final void dispose() {
        this.future.cancel(false);
    }

    public final String toString() {
        return "DisposableFutureHandle[" + this.future + "]";
    }
}

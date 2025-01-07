package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CancelFutureOnCancel extends CancelHandler {
    public final Future future;

    public CancelFutureOnCancel(Future future) {
        this.future = future;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final String toString() {
        return "CancelFutureOnCancel[" + this.future + "]";
    }

    @Override // kotlinx.coroutines.CancelHandler
    public final void invoke(Throwable th) {
        if (th != null) {
            this.future.cancel(false);
        }
    }
}

package kotlinx.coroutines;

import kotlin.Unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisposeOnCancel extends CancelHandler {
    public final DisposableHandle handle;

    public DisposeOnCancel(DisposableHandle disposableHandle) {
        this.handle = disposableHandle;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final String toString() {
        return "DisposeOnCancel[" + this.handle + "]";
    }

    @Override // kotlinx.coroutines.CancelHandler
    public final void invoke(Throwable th) {
        this.handle.dispose();
    }
}

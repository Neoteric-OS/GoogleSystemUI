package kotlinx.coroutines;

import androidx.constraintlayout.motion.widget.MotionLayout$$ExternalSyntheticOutline0;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class InvokeOnCancel extends CancelHandler {
    public final Function1 handler;

    public InvokeOnCancel(Function1 function1) {
        this.handler = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final String toString() {
        return MotionLayout$$ExternalSyntheticOutline0.m("InvokeOnCancel[", DebugStringsKt.getClassSimpleName(this.handler), "@", DebugStringsKt.getHexAddress(this), "]");
    }

    @Override // kotlinx.coroutines.CancelHandler
    public final void invoke(Throwable th) {
        this.handler.invoke(th);
    }
}

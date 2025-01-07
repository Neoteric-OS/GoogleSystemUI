package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ProduceKt$awaitClose$4$1 extends Lambda implements Function1 {
    final /* synthetic */ CancellableContinuation $cont;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProduceKt$awaitClose$4$1(CancellableContinuationImpl cancellableContinuationImpl) {
        super(1);
        this.$cont = cancellableContinuationImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        CancellableContinuation cancellableContinuation = this.$cont;
        Unit unit = Unit.INSTANCE;
        cancellableContinuation.resumeWith(unit);
        return unit;
    }
}

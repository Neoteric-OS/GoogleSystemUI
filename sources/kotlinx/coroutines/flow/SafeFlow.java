package kotlinx.coroutines.flow;

import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SafeFlow extends AbstractFlow {
    public final SuspendLambda block;

    /* JADX WARN: Multi-variable type inference failed */
    public SafeFlow(Function2 function2) {
        this.block = (SuspendLambda) function2;
    }
}

package kotlinx.coroutines.flow.internal;

import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SafeCollectorKt {
    public static final Function3 emitFun;

    static {
        SafeCollectorKt$emitFun$1 safeCollectorKt$emitFun$1 = SafeCollectorKt$emitFun$1.INSTANCE;
        TypeIntrinsics.beforeCheckcastToFunctionOfArity(3, safeCollectorKt$emitFun$1);
        emitFun = safeCollectorKt$emitFun$1;
    }
}

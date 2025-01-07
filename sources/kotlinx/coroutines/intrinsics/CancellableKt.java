package kotlinx.coroutines.intrinsics;

import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CancellableKt {
    public static void startCoroutineCancellable$default(Function2 function2, AbstractCoroutine abstractCoroutine, AbstractCoroutine abstractCoroutine2) {
        try {
            DispatchedContinuationKt.resumeCancellableWith(Unit.INSTANCE, IntrinsicsKt__IntrinsicsJvmKt.intercepted(IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted(abstractCoroutine, abstractCoroutine2, function2)));
        } catch (Throwable th) {
            abstractCoroutine2.resumeWith(new Result.Failure(th));
            throw th;
        }
    }
}

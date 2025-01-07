package kotlinx.coroutines;

import kotlin.Result;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CompletionStateKt {
    public static final Object recoverResult(Object obj) {
        return obj instanceof CompletedExceptionally ? new Result.Failure(((CompletedExceptionally) obj).cause) : obj;
    }
}

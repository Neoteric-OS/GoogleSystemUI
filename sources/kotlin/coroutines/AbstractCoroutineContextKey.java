package kotlin.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AbstractCoroutineContextKey implements CoroutineContext.Key {
    public final Lambda safeCast;
    public final CoroutineContext.Key topmostKey;

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractCoroutineContextKey(CoroutineContext.Key key, Function1 function1) {
        this.safeCast = (Lambda) function1;
        this.topmostKey = key instanceof AbstractCoroutineContextKey ? ((AbstractCoroutineContextKey) key).topmostKey : key;
    }
}

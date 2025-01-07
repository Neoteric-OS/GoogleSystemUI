package kotlinx.coroutines.internal;

import androidx.room.coroutines.PooledConnectionImpl;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.ThreadContextElement;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ThreadLocalElement implements ThreadContextElement {
    public final ThreadLocalKey key;
    public final ThreadLocal threadLocal;
    public final PooledConnectionImpl value;

    public ThreadLocalElement(PooledConnectionImpl pooledConnectionImpl, ThreadLocal threadLocal) {
        this.value = pooledConnectionImpl;
        this.threadLocal = threadLocal;
        this.key = new ThreadLocalKey(threadLocal);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final Object fold(Object obj, Function2 function2) {
        return function2.invoke(obj, this);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        if (this.key.equals(key)) {
            return this;
        }
        return null;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key getKey() {
        return this.key;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        return this.key.equals(key) ? EmptyCoroutineContext.INSTANCE : this;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext plus(CoroutineContext coroutineContext) {
        return CoroutineContext.DefaultImpls.plus(this, coroutineContext);
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    public final void restoreThreadContext(Object obj) {
        this.threadLocal.set(obj);
    }

    public final String toString() {
        return "ThreadLocal(value=" + this.value + ", threadLocal = " + this.threadLocal + ")";
    }

    @Override // kotlinx.coroutines.ThreadContextElement
    public final Object updateThreadContext(CoroutineContext coroutineContext) {
        Object obj = this.threadLocal.get();
        this.threadLocal.set(this.value);
        return obj;
    }
}

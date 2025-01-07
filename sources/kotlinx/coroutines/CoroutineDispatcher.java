package kotlinx.coroutines;

import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.LimitedDispatcher;
import kotlinx.coroutines.internal.LimitedDispatcherKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CoroutineDispatcher extends AbstractCoroutineContextElement implements ContinuationInterceptor {
    public static final Key Key = new Key(ContinuationInterceptor.Key.$$INSTANCE, new Function1() { // from class: kotlinx.coroutines.CoroutineDispatcher.Key.1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            CoroutineContext.Element element = (CoroutineContext.Element) obj;
            if (element instanceof CoroutineDispatcher) {
                return (CoroutineDispatcher) element;
            }
            return null;
        }
    });

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Key extends AbstractCoroutineContextKey {
    }

    public CoroutineDispatcher() {
        super(ContinuationInterceptor.Key.$$INSTANCE);
    }

    public abstract void dispatch(CoroutineContext coroutineContext, Runnable runnable);

    public void dispatchYield(CoroutineContext coroutineContext, Runnable runnable) {
        dispatch(coroutineContext, runnable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r4v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // kotlin.coroutines.AbstractCoroutineContextElement, kotlin.coroutines.CoroutineContext
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.coroutines.CoroutineContext.Element get(kotlin.coroutines.CoroutineContext.Key r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof kotlin.coroutines.AbstractCoroutineContextKey
            r1 = 0
            if (r0 == 0) goto L23
            kotlin.coroutines.AbstractCoroutineContextKey r4 = (kotlin.coroutines.AbstractCoroutineContextKey) r4
            kotlin.coroutines.CoroutineContext$Key r0 = r3.key
            if (r0 == r4) goto L12
            kotlin.coroutines.CoroutineContext$Key r2 = r4.topmostKey
            if (r2 != r0) goto L10
            goto L15
        L10:
            r0 = 0
            goto L16
        L12:
            r4.getClass()
        L15:
            r0 = 1
        L16:
            if (r0 == 0) goto L2a
            kotlin.jvm.internal.Lambda r4 = r4.safeCast
            java.lang.Object r3 = r4.invoke(r3)
            kotlin.coroutines.CoroutineContext$Element r3 = (kotlin.coroutines.CoroutineContext.Element) r3
            if (r3 == 0) goto L2a
            goto L29
        L23:
            kotlin.coroutines.ContinuationInterceptor$Key r0 = kotlin.coroutines.ContinuationInterceptor.Key.$$INSTANCE
            if (r0 != r4) goto L28
            goto L29
        L28:
            r3 = r1
        L29:
            r1 = r3
        L2a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CoroutineDispatcher.get(kotlin.coroutines.CoroutineContext$Key):kotlin.coroutines.CoroutineContext$Element");
    }

    public boolean isDispatchNeeded() {
        return true;
    }

    public CoroutineDispatcher limitedParallelism(int i) {
        LimitedDispatcherKt.checkParallelism(i);
        return new LimitedDispatcher(this, i);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // kotlin.coroutines.AbstractCoroutineContextElement, kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        boolean z;
        if (!(key instanceof AbstractCoroutineContextKey)) {
            return ContinuationInterceptor.Key.$$INSTANCE == key ? EmptyCoroutineContext.INSTANCE : this;
        }
        AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
        CoroutineContext.Key key2 = this.key;
        if (key2 == abstractCoroutineContextKey) {
            abstractCoroutineContextKey.getClass();
        } else if (abstractCoroutineContextKey.topmostKey != key2) {
            z = false;
            return (!z || ((CoroutineContext.Element) abstractCoroutineContextKey.safeCast.invoke(this)) == null) ? this : EmptyCoroutineContext.INSTANCE;
        }
        z = true;
        if (z) {
            return this;
        }
    }

    public String toString() {
        return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(DebugStringsKt.getClassSimpleName(this), "@", DebugStringsKt.getHexAddress(this));
    }
}

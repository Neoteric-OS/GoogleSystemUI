package com.android.app.tracing.coroutines;

import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BaseTraceElement implements CoroutineContext.Element {
    public static final Key Key = new Key();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Key implements CoroutineContext.Key {
    }

    @Override // kotlin.coroutines.CoroutineContext
    public final Object fold(Object obj, Function2 function2) {
        return function2.invoke(obj, this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r4v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // kotlin.coroutines.CoroutineContext
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlin.coroutines.CoroutineContext.Element get(kotlin.coroutines.CoroutineContext.Key r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof kotlin.coroutines.AbstractCoroutineContextKey
            com.android.app.tracing.coroutines.BaseTraceElement$Key r1 = com.android.app.tracing.coroutines.BaseTraceElement.Key
            r2 = 0
            if (r0 == 0) goto L23
            kotlin.coroutines.AbstractCoroutineContextKey r4 = (kotlin.coroutines.AbstractCoroutineContextKey) r4
            if (r1 == r4) goto L12
            kotlin.coroutines.CoroutineContext$Key r0 = r4.topmostKey
            if (r0 != r1) goto L10
            goto L15
        L10:
            r0 = 0
            goto L16
        L12:
            r4.getClass()
        L15:
            r0 = 1
        L16:
            if (r0 == 0) goto L28
            kotlin.jvm.internal.Lambda r4 = r4.safeCast
            java.lang.Object r3 = r4.invoke(r3)
            kotlin.coroutines.CoroutineContext$Element r3 = (kotlin.coroutines.CoroutineContext.Element) r3
            if (r3 == 0) goto L28
            goto L27
        L23:
            if (r1 != r4) goto L26
            goto L27
        L26:
            r3 = r2
        L27:
            r2 = r3
        L28:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.app.tracing.coroutines.BaseTraceElement.get(kotlin.coroutines.CoroutineContext$Key):kotlin.coroutines.CoroutineContext$Element");
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key getKey() {
        return Key;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext minusKey(CoroutineContext.Key key) {
        boolean z;
        boolean z2 = key instanceof AbstractCoroutineContextKey;
        Key key2 = Key;
        if (!z2) {
            return key2 == key ? EmptyCoroutineContext.INSTANCE : this;
        }
        AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
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

    @Override // kotlin.coroutines.CoroutineContext
    public final CoroutineContext plus(CoroutineContext coroutineContext) {
        return CoroutineContext.DefaultImpls.plus(this, coroutineContext);
    }
}

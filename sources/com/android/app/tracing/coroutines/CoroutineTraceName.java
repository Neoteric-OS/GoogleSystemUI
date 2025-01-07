package com.android.app.tracing.coroutines;

import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CoroutineTraceName extends BaseTraceElement {
    public static final Key Key = new Key(BaseTraceElement.Key, new Function1() { // from class: com.android.app.tracing.coroutines.CoroutineTraceName.Key.1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            CoroutineContext.Element element = (CoroutineContext.Element) obj;
            if (element instanceof CoroutineTraceName) {
                return (CoroutineTraceName) element;
            }
            return null;
        }
    });

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Key extends AbstractCoroutineContextKey {
    }
}

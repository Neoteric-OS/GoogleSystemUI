package androidx.datastore.core;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferredImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Message$Update {
    public final CompletableDeferredImpl ack;
    public final CoroutineContext callerContext;
    public final State lastState;
    public final Function2 transform;

    public Message$Update(Function2 function2, CompletableDeferredImpl completableDeferredImpl, State state, CoroutineContext coroutineContext) {
        this.transform = function2;
        this.ack = completableDeferredImpl;
        this.lastState = state;
        this.callerContext = coroutineContext;
    }
}

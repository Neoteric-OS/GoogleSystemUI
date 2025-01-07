package androidx.room;

import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface PooledConnection {
    Object usePrepared(String str, Function1 function1, ContinuationImpl continuationImpl);
}

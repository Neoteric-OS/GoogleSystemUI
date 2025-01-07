package androidx.room;

import androidx.room.InvalidationTracker;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class InvalidationTracker$notifyObserversByTableNames$1 extends Lambda implements Function1 {
    public static final InvalidationTracker$notifyObserversByTableNames$1 INSTANCE = new InvalidationTracker$notifyObserversByTableNames$1();

    public InvalidationTracker$notifyObserversByTableNames$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return Boolean.valueOf(!(((InvalidationTracker.Observer) obj) instanceof MultiInstanceInvalidationClient$observer$1));
    }
}

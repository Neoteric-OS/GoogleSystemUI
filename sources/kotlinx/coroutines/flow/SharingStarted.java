package kotlinx.coroutines.flow;

import kotlinx.coroutines.flow.internal.SubscriptionCountStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SharingStarted {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final /* synthetic */ Companion $$INSTANCE = null;
        public static final StartedEagerly Eagerly = new StartedEagerly();
        public static final StartedLazily Lazily = new StartedLazily();

        public static StartedWhileSubscribed WhileSubscribed$default(int i) {
            return new StartedWhileSubscribed(0L, (i & 2) != 0 ? Long.MAX_VALUE : 0L);
        }
    }

    Flow command(SubscriptionCountStateFlow subscriptionCountStateFlow);
}

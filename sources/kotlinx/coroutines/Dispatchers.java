package kotlinx.coroutines;

import kotlinx.coroutines.scheduling.DefaultIoScheduler;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class Dispatchers {
    public static final DefaultScheduler Default = DefaultScheduler.INSTANCE;
    public static final Unconfined Unconfined = Unconfined.INSTANCE;
    public static final DefaultIoScheduler IO = DefaultIoScheduler.INSTANCE;
}

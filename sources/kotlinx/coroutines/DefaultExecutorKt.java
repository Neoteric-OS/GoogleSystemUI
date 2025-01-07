package kotlinx.coroutines;

import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.internal.SystemPropsKt__SystemPropsKt;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DefaultExecutorKt {
    public static final Delay DefaultDelay;

    static {
        String str;
        Delay delay;
        int i = SystemPropsKt__SystemPropsKt.AVAILABLE_PROCESSORS;
        try {
            str = System.getProperty("kotlinx.coroutines.main.delay");
        } catch (SecurityException unused) {
            str = null;
        }
        if (str != null ? Boolean.parseBoolean(str) : false) {
            DefaultScheduler defaultScheduler = Dispatchers.Default;
            delay = MainDispatcherLoader.dispatcher;
            if (delay == null) {
                delay = DefaultExecutor.INSTANCE;
            }
        } else {
            delay = DefaultExecutor.INSTANCE;
        }
        DefaultDelay = delay;
    }
}

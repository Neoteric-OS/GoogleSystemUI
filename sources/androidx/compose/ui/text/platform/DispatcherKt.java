package androidx.compose.ui.text.platform;

import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DispatcherKt {
    public static final HandlerContext FontCacheManagementDispatcher;

    static {
        DefaultScheduler defaultScheduler = Dispatchers.Default;
        FontCacheManagementDispatcher = MainDispatcherLoader.dispatcher;
    }
}

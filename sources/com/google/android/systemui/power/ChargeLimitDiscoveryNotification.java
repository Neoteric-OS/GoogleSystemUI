package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.util.settings.SecureSettings;
import java.util.concurrent.Executor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChargeLimitDiscoveryNotification {
    public final ActivityStarter activityStarter;
    public final ContextScope backgroundCoroutineScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final ChargeLimitController chargeLimitController;
    public final Context context;
    public boolean isPluggedIn;
    public final CoroutineDispatcher mainDispatcher;
    public final NotificationManager notificationManager;
    public final SecureSettings secureSettings;
    public final Lazy sharedPreferences$delegate;
    public final UiEventLogger uiEventLogger;
    public final UserTracker userTracker;

    public ChargeLimitDiscoveryNotification(Context context, Executor executor, ActivityStarter activityStarter, UiEventLogger uiEventLogger, ChargeLimitController chargeLimitController, SecureSettings secureSettings, UserTracker userTracker) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
        DefaultScheduler defaultScheduler = Dispatchers.Default;
        HandlerContext handlerContext = MainDispatcherLoader.dispatcher;
        this.context = context;
        this.activityStarter = activityStarter;
        this.uiEventLogger = uiEventLogger;
        this.chargeLimitController = chargeLimitController;
        this.secureSettings = secureSettings;
        this.userTracker = userTracker;
        this.notificationManager = notificationManager;
        this.mainDispatcher = handlerContext;
        CoroutineDispatcher from = ExecutorsKt.from(executor);
        this.backgroundDispatcher = from;
        this.backgroundCoroutineScope = CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(from, SupervisorKt.SupervisorJob$default()));
        this.sharedPreferences$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.google.android.systemui.power.ChargeLimitDiscoveryNotification$sharedPreferences$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ChargeLimitDiscoveryNotification.this.context.getApplicationContext().getSharedPreferences("charge_limit_shared_prefs", 0);
            }
        });
    }

    public static /* synthetic */ void getSharedPreferences$annotations() {
    }
}

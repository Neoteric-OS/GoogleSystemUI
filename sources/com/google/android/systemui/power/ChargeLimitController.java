package com.google.android.systemui.power;

import com.android.systemui.settings.UserTracker;
import com.android.systemui.util.settings.SecureSettings;
import com.google.android.systemui.power.batteryevent.repository.GoogleBatteryManagerWrapperImpl;
import java.util.concurrent.Executor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChargeLimitController {
    public final ContextScope backgroundCoroutineScope;
    public final CoroutineDispatcher backgroundDispatcher;
    public final GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapper;
    public final SecureSettings secureSettings;
    public final UserTracker userTracker;

    public ChargeLimitController(Executor executor, SecureSettings secureSettings, UserTracker userTracker) {
        GoogleBatteryManagerWrapperImpl googleBatteryManagerWrapperImpl = new GoogleBatteryManagerWrapperImpl();
        this.secureSettings = secureSettings;
        this.userTracker = userTracker;
        this.googleBatteryManagerWrapper = googleBatteryManagerWrapperImpl;
        CoroutineDispatcher from = ExecutorsKt.from(executor);
        this.backgroundDispatcher = from;
        this.backgroundCoroutineScope = CoroutineScopeKt.CoroutineScope(CoroutineContext.DefaultImpls.plus(from, SupervisorKt.SupervisorJob$default()));
    }
}

package com.android.systemui.statusbar.pipeline.airplane.data.repository;

import android.net.ConnectivityManager;
import android.os.Handler;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AirplaneModeRepositoryImpl implements AirplaneModeRepository {
    public final CoroutineContext backgroundContext;
    public final Handler bgHandler;
    public final ConnectivityManager connectivityManager;
    public final GlobalSettings globalSettings;
    public final ReadonlyStateFlow isAirplaneMode;

    public AirplaneModeRepositoryImpl(ConnectivityManager connectivityManager, Handler handler, CoroutineContext coroutineContext, GlobalSettings globalSettings, TableLogBuffer tableLogBuffer, CoroutineScope coroutineScope) {
        this.connectivityManager = connectivityManager;
        this.bgHandler = handler;
        this.backgroundContext = coroutineContext;
        this.globalSettings = globalSettings;
        this.isAirplaneMode = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new AirplaneModeRepositoryImpl$isAirplaneMode$1(this, null))), tableLogBuffer, "", "isAirplaneMode", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
    }

    public final Object setIsAirplaneMode(boolean z, Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.backgroundContext, new AirplaneModeRepositoryImpl$setIsAirplaneMode$2(this, z, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}

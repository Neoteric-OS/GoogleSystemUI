package com.android.systemui.qs.tiles.base.viewmodel;

import com.android.systemui.Dumpable;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.qs.tiles.base.analytics.QSTileAnalytics;
import com.android.systemui.qs.tiles.base.interactor.DisabledByPolicyInteractorImpl;
import com.android.systemui.qs.tiles.base.logging.QSTileLogger;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.util.time.SystemClock;
import java.io.PrintWriter;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSTileViewModelImpl implements Dumpable {
    public final CoroutineDispatcher backgroundDispatcher;
    public final QSTileConfig config;
    public final DisabledByPolicyInteractorImpl disabledByPolicyInteractor;
    public final FalsingManager falsingManager;
    public final SharedFlowImpl forceUpdates;
    public final ReadonlyStateFlow isAvailable;
    public final Function0 mapper;
    public final QSTileAnalytics qsTileAnalytics;
    public final QSTileLogger qsTileLogger;
    public final ReadonlyStateFlow state;
    public final SystemClock systemClock;
    public final ReadonlySharedFlow tileData;
    public final Function0 tileDataInteractor;
    public final CoroutineScope tileScope;
    public final Function0 userActionInteractor;
    public final SharedFlowImpl userInputs;
    public final StateFlowImpl users;

    public QSTileViewModelImpl(QSTileConfig qSTileConfig, Function0 function0, Function0 function02, Function0 function03, DisabledByPolicyInteractorImpl disabledByPolicyInteractorImpl, UserRepositoryImpl userRepositoryImpl, FalsingManager falsingManager, QSTileAnalytics qSTileAnalytics, QSTileLogger qSTileLogger, SystemClock systemClock, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, CoroutineScope coroutineScope) {
        this.config = qSTileConfig;
        this.userActionInteractor = function0;
        this.tileDataInteractor = function02;
        this.mapper = function03;
        this.disabledByPolicyInteractor = disabledByPolicyInteractorImpl;
        this.falsingManager = falsingManager;
        this.qsTileAnalytics = qSTileAnalytics;
        this.qsTileLogger = qSTileLogger;
        this.systemClock = systemClock;
        this.backgroundDispatcher = coroutineDispatcher;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(userRepositoryImpl.getSelectedUserInfo().getUserHandle());
        this.userInputs = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7);
        this.forceUpdates = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7);
        ReadonlySharedFlow shareIn = FlowKt.shareIn(FlowKt.distinctUntilChanged(FlowKt.transformLatest(MutableStateFlow, new QSTileViewModelImpl$createTileDataFlow$1(this, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 1);
        this.tileData = shareIn;
        this.state = FlowKt.stateIn(new QSTileViewModelImpl$special$$inlined$map$1(shareIn, coroutineDispatcher2, this), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        FlowKt.stateIn(FlowKt.flowOn(FlowKt.transformLatest(MutableStateFlow, new QSTileViewModelImpl$special$$inlined$flatMapLatest$1(this, null)), coroutineDispatcher), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.TRUE);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(this.config.tileSpec.getSpec() + ":");
        printWriter.print("    ");
        printWriter.println(String.valueOf(CollectionsKt.lastOrNull(this.state.getReplayCache())));
    }
}

package com.android.systemui.statusbar.pipeline.airplane.ui.viewmodel;

import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AirplaneModeViewModelImpl {
    public final ReadonlyStateFlow isAirplaneModeIconVisible;

    public AirplaneModeViewModelImpl(AirplaneModeInteractor airplaneModeInteractor, TableLogBuffer tableLogBuffer, CoroutineScope coroutineScope) {
        this.isAirplaneModeIconVisible = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(airplaneModeInteractor.isAirplaneMode, airplaneModeInteractor.isForceHidden, new AirplaneModeViewModelImpl$isAirplaneModeIconVisible$1(3, null))), tableLogBuffer, "", "isAirplaneModeIconVisible", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
    }
}

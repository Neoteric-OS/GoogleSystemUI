package com.android.systemui.statusbar.pipeline.satellite.ui.viewmodel;

import android.content.Context;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.airplane.data.repository.AirplaneModeRepository;
import com.android.systemui.statusbar.pipeline.satellite.domain.interactor.DeviceBasedSatelliteInteractor;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceBasedSatelliteViewModelImpl implements DeviceBasedSatelliteViewModel {
    public static final long DELAY_DURATION;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 canShowIcon;
    public final ReadonlyStateFlow carrierText;
    public final ReadonlyStateFlow icon;
    public final ReadonlyStateFlow shouldShowIconForOosAfterHysteresis;
    public final ReadonlyStateFlow showIcon;

    static {
        int i = Duration.$r8$clinit;
        DELAY_DURATION = DurationKt.toDuration(10, DurationUnit.SECONDS);
    }

    public DeviceBasedSatelliteViewModelImpl(Context context, DeviceBasedSatelliteInteractor deviceBasedSatelliteInteractor, CoroutineScope coroutineScope, AirplaneModeRepository airplaneModeRepository, LogBuffer logBuffer, TableLogBuffer tableLogBuffer) {
        SafeFlow logDiffsForTable = DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(FlowKt.transformLatest(deviceBasedSatelliteInteractor.areAllConnectionsOutOfService, new DeviceBasedSatelliteViewModelImpl$special$$inlined$flatMapLatest$1(logBuffer, null))), tableLogBuffer, "vm", "visibleForOos", false);
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        Boolean bool = Boolean.FALSE;
        this.shouldShowIconForOosAfterHysteresis = FlowKt.stateIn(logDiffsForTable, coroutineScope, WhileSubscribed$default, bool);
        DeviceBasedSatelliteViewModelImpl$canShowIcon$1 deviceBasedSatelliteViewModelImpl$canShowIcon$1 = new DeviceBasedSatelliteViewModelImpl$canShowIcon$1(3, null);
        ReadonlyStateFlow stateIn = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(FlowKt.transformLatest(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(deviceBasedSatelliteInteractor.isSatelliteAllowed, deviceBasedSatelliteInteractor.isSatelliteProvisioned, deviceBasedSatelliteViewModelImpl$canShowIcon$1), new DeviceBasedSatelliteViewModelImpl$special$$inlined$flatMapLatest$2(null, this, deviceBasedSatelliteInteractor, airplaneModeRepository))), tableLogBuffer, "vm", "visible", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        DeviceBasedSatelliteViewModelImpl$icon$1 deviceBasedSatelliteViewModelImpl$icon$1 = new DeviceBasedSatelliteViewModelImpl$icon$1(4, null);
        ReadonlyStateFlow readonlyStateFlow = deviceBasedSatelliteInteractor.connectionState;
        this.icon = FlowKt.stateIn(FlowKt.combine(stateIn, readonlyStateFlow, deviceBasedSatelliteInteractor.signalStrength, deviceBasedSatelliteViewModelImpl$icon$1), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        this.carrierText = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(stateIn, readonlyStateFlow, new DeviceBasedSatelliteViewModelImpl$carrierText$1(logBuffer, context, null))), tableLogBuffer, "vm", "carrierText", (String) null), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
    }
}

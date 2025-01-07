package com.android.systemui.statusbar.pipeline.satellite.domain.interactor;

import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl;
import com.android.systemui.statusbar.pipeline.satellite.data.DeviceBasedSatelliteRepository;
import com.android.systemui.statusbar.pipeline.satellite.shared.model.SatelliteConnectionState;
import com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceBasedSatelliteInteractor {
    public final SafeFlow allConnectionsOos;
    public final ReadonlyStateFlow areAllConnectionsOutOfService;
    public final ReadonlyStateFlow connectionState;
    public final ReadonlyStateFlow isSatelliteAllowed;
    public final StateFlow isSatelliteProvisioned;
    public final DeviceBasedSatelliteInteractor$special$$inlined$map$1 isWifiActive;
    public final LogBuffer logBuffer;
    public final ReadonlyStateFlow signalStrength;

    public DeviceBasedSatelliteInteractor(DeviceBasedSatelliteRepository deviceBasedSatelliteRepository, MobileIconsInteractorImpl mobileIconsInteractorImpl, WifiInteractorImpl wifiInteractorImpl, CoroutineScope coroutineScope, LogBuffer logBuffer, TableLogBuffer tableLogBuffer) {
        this.logBuffer = logBuffer;
        this.isSatelliteAllowed = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(deviceBasedSatelliteRepository.isSatelliteAllowedForCurrentLocation()), tableLogBuffer, "", "allowed", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.FALSE);
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(deviceBasedSatelliteRepository.getConnectionState());
        SatelliteConnectionState satelliteConnectionState = SatelliteConnectionState.Off;
        this.connectionState = FlowKt.stateIn(DiffableKt.logDiffsForTable(distinctUntilChanged, tableLogBuffer, "", satelliteConnectionState), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), satelliteConnectionState);
        this.signalStrength = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(deviceBasedSatelliteRepository.getSignalStrength()), tableLogBuffer, "", "level", 0), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
        this.isSatelliteProvisioned = deviceBasedSatelliteRepository.isSatelliteProvisioned();
        this.isWifiActive = new DeviceBasedSatelliteInteractor$special$$inlined$map$1(0, wifiInteractorImpl.wifiNetwork);
        ReadonlyStateFlow readonlyStateFlow = mobileIconsInteractorImpl.icons;
        Boolean bool = Boolean.TRUE;
        this.areAllConnectionsOutOfService = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(FlowKt.transformLatest(new DeviceBasedSatelliteInteractor$special$$inlined$map$1(1, readonlyStateFlow), new DeviceBasedSatelliteInteractor$special$$inlined$aggregateOver$2(bool, null))), tableLogBuffer, "", "allConnsOOS", true), mobileIconsInteractorImpl.isDeviceInEmergencyCallsOnlyMode, new DeviceBasedSatelliteInteractor$areAllConnectionsOutOfService$1(this, null))), tableLogBuffer, "", "allOosAndNoEmer", true), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
    }
}

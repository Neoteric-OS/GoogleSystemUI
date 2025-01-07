package com.android.systemui.statusbar.pipeline.satellite.data;

import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.statusbar.pipeline.satellite.data.demo.DemoDeviceBasedSatelliteRepository;
import com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceBasedSatelliteRepositorySwitcher implements DeviceBasedSatelliteRepository {
    public final ReadonlyStateFlow activeRepo;
    public final ReadonlyStateFlow connectionState;
    public final DemoDeviceBasedSatelliteRepository demoImpl;
    public final DemoModeController demoModeController;
    public final ReadonlyStateFlow isDemoMode;
    public final ReadonlyStateFlow isSatelliteAllowedForCurrentLocation;
    public final ReadonlyStateFlow isSatelliteProvisioned;
    public final DeviceBasedSatelliteRepositoryImpl realImpl;
    public final ReadonlyStateFlow signalStrength;

    public DeviceBasedSatelliteRepositorySwitcher(DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl, DemoDeviceBasedSatelliteRepository demoDeviceBasedSatelliteRepository, DemoModeController demoModeController, CoroutineScope coroutineScope) {
        this.realImpl = deviceBasedSatelliteRepositoryImpl;
        this.demoImpl = demoDeviceBasedSatelliteRepository;
        this.demoModeController = demoModeController;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(FlowKt.mapLatest(new DeviceBasedSatelliteRepositorySwitcher$activeRepo$1(this, null), FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new DeviceBasedSatelliteRepositorySwitcher$isDemoMode$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(demoModeController.isInDemoMode))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), deviceBasedSatelliteRepositoryImpl);
        this.isSatelliteProvisioned = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new DeviceBasedSatelliteRepositorySwitcher$special$$inlined$flatMapLatest$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), deviceBasedSatelliteRepositoryImpl.isSatelliteProvisioned.getValue());
        this.connectionState = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new DeviceBasedSatelliteRepositorySwitcher$special$$inlined$flatMapLatest$2(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), deviceBasedSatelliteRepositoryImpl.connectionState.getValue());
        this.signalStrength = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new DeviceBasedSatelliteRepositorySwitcher$special$$inlined$flatMapLatest$3(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), deviceBasedSatelliteRepositoryImpl.signalStrength.getValue());
        this.isSatelliteAllowedForCurrentLocation = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new DeviceBasedSatelliteRepositorySwitcher$special$$inlined$flatMapLatest$4(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), deviceBasedSatelliteRepositoryImpl.isSatelliteAllowedForCurrentLocation.getValue());
    }

    @Override // com.android.systemui.statusbar.pipeline.satellite.data.DeviceBasedSatelliteRepository
    public final StateFlow getConnectionState() {
        return this.connectionState;
    }

    @Override // com.android.systemui.statusbar.pipeline.satellite.data.DeviceBasedSatelliteRepository
    public final StateFlow getSignalStrength() {
        return this.signalStrength;
    }

    @Override // com.android.systemui.statusbar.pipeline.satellite.data.DeviceBasedSatelliteRepository
    public final StateFlow isSatelliteAllowedForCurrentLocation() {
        return this.isSatelliteAllowedForCurrentLocation;
    }

    @Override // com.android.systemui.statusbar.pipeline.satellite.data.DeviceBasedSatelliteRepository
    public final StateFlow isSatelliteProvisioned() {
        return this.isSatelliteProvisioned;
    }

    public static /* synthetic */ void getActiveRepo$annotations() {
    }
}

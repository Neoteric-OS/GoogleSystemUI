package com.android.systemui.statusbar.pipeline.satellite.data.demo;

import com.android.systemui.statusbar.pipeline.satellite.data.DeviceBasedSatelliteRepository;
import com.android.systemui.statusbar.pipeline.satellite.shared.model.SatelliteConnectionState;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DemoDeviceBasedSatelliteRepository implements DeviceBasedSatelliteRepository {
    public final StateFlowImpl connectionState;
    public final DemoDeviceBasedSatelliteDataSource dataSource;
    public StandaloneCoroutine demoCommandJob;
    public final StateFlowImpl isSatelliteAllowedForCurrentLocation;
    public final StateFlowImpl isSatelliteProvisioned;
    public final CoroutineScope scope;
    public final StateFlowImpl signalStrength;

    public DemoDeviceBasedSatelliteRepository(DemoDeviceBasedSatelliteDataSource demoDeviceBasedSatelliteDataSource, CoroutineScope coroutineScope) {
        this.dataSource = demoDeviceBasedSatelliteDataSource;
        this.scope = coroutineScope;
        Boolean bool = Boolean.TRUE;
        this.isSatelliteProvisioned = StateFlowKt.MutableStateFlow(bool);
        this.connectionState = StateFlowKt.MutableStateFlow(SatelliteConnectionState.Unknown);
        this.signalStrength = StateFlowKt.MutableStateFlow(0);
        this.isSatelliteAllowedForCurrentLocation = StateFlowKt.MutableStateFlow(bool);
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

    public final void startProcessingCommands() {
        this.demoCommandJob = BuildersKt.launch$default(this.scope, null, null, new DemoDeviceBasedSatelliteRepository$startProcessingCommands$1(this, null), 3);
    }
}

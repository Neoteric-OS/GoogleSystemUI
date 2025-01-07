package com.android.systemui.statusbar.pipeline.wifi.data.repository;

import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.DemoWifiRepository;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiRepositorySwitcher implements WifiRepository {
    public final ReadonlyStateFlow activeRepo;
    public final DemoWifiRepository demoImpl;
    public final DemoModeController demoModeController;
    public final ReadonlyStateFlow isDemoMode;
    public final ReadonlyStateFlow isWifiDefault;
    public final ReadonlyStateFlow isWifiEnabled;
    public final RealWifiRepository realImpl;
    public final ReadonlyStateFlow secondaryNetworks;
    public final ReadonlyStateFlow wifiActivity;
    public final ReadonlyStateFlow wifiNetwork;
    public final ReadonlyStateFlow wifiScanResults;

    public WifiRepositorySwitcher(RealWifiRepository realWifiRepository, DemoWifiRepository demoWifiRepository, DemoModeController demoModeController, CoroutineScope coroutineScope) {
        this.realImpl = realWifiRepository;
        this.demoImpl = demoWifiRepository;
        this.demoModeController = demoModeController;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(FlowKt.mapLatest(new WifiRepositorySwitcher$activeRepo$1(this, null), FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new WifiRepositorySwitcher$isDemoMode$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(demoModeController.isInDemoMode))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), realWifiRepository);
        this.isWifiEnabled = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new WifiRepositorySwitcher$special$$inlined$flatMapLatest$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), realWifiRepository.isWifiEnabled().getValue());
        this.isWifiDefault = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new WifiRepositorySwitcher$special$$inlined$flatMapLatest$2(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), realWifiRepository.isWifiDefault().getValue());
        this.wifiNetwork = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new WifiRepositorySwitcher$special$$inlined$flatMapLatest$3(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), realWifiRepository.getWifiNetwork().getValue());
        this.secondaryNetworks = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new WifiRepositorySwitcher$special$$inlined$flatMapLatest$4(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), realWifiRepository.getSecondaryNetworks().getValue());
        this.wifiActivity = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new WifiRepositorySwitcher$special$$inlined$flatMapLatest$5(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), realWifiRepository.getWifiActivity().getValue());
        this.wifiScanResults = FlowKt.stateIn(FlowKt.transformLatest(stateIn, new WifiRepositorySwitcher$special$$inlined$flatMapLatest$6(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), realWifiRepository.getWifiScanResults().getValue());
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository
    public final StateFlow getSecondaryNetworks() {
        return this.secondaryNetworks;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository
    public final StateFlow getWifiActivity() {
        return this.wifiActivity;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository
    public final StateFlow getWifiNetwork() {
        return this.wifiNetwork;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository
    public final StateFlow getWifiScanResults() {
        return this.wifiScanResults;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository
    public final StateFlow isWifiDefault() {
        return this.isWifiDefault;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository
    public final StateFlow isWifiEnabled() {
        return this.isWifiEnabled;
    }

    public static /* synthetic */ void getActiveRepo$annotations() {
    }
}

package com.android.systemui.statusbar.pipeline.wifi.data.repository.demo;

import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import kotlin.collections.EmptyList;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DemoWifiRepository implements WifiRepository {
    public final StateFlowImpl _isWifiDefault;
    public final StateFlowImpl _isWifiEnabled;
    public final StateFlowImpl _secondaryNetworks;
    public final StateFlowImpl _wifiActivity;
    public final StateFlowImpl _wifiNetwork;
    public final StateFlowImpl _wifiScanResults;
    public final DemoModeWifiDataSource dataSource;
    public StandaloneCoroutine demoCommandJob;
    public final StateFlowImpl isWifiDefault;
    public final StateFlowImpl isWifiEnabled;
    public final CoroutineScope scope;
    public final StateFlowImpl secondaryNetworks;
    public final StateFlowImpl wifiActivity;
    public final StateFlowImpl wifiNetwork;
    public final StateFlowImpl wifiScanResults;

    public DemoWifiRepository(DemoModeWifiDataSource demoModeWifiDataSource, CoroutineScope coroutineScope) {
        this.dataSource = demoModeWifiDataSource;
        this.scope = coroutineScope;
        Boolean bool = Boolean.FALSE;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(bool);
        this._isWifiEnabled = MutableStateFlow;
        this.isWifiEnabled = MutableStateFlow;
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._isWifiDefault = MutableStateFlow2;
        this.isWifiDefault = MutableStateFlow2;
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(new WifiNetworkModel.Inactive(null));
        this._wifiNetwork = MutableStateFlow3;
        this.wifiNetwork = MutableStateFlow3;
        EmptyList emptyList = EmptyList.INSTANCE;
        this.secondaryNetworks = StateFlowKt.MutableStateFlow(emptyList);
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(new DataActivityModel(false, false));
        this._wifiActivity = MutableStateFlow4;
        this.wifiActivity = MutableStateFlow4;
        this.wifiScanResults = StateFlowKt.MutableStateFlow(emptyList);
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

    public final void startProcessingCommands() {
        this.demoCommandJob = BuildersKt.launch$default(this.scope, null, null, new DemoWifiRepository$startProcessingCommands$1(this, null), 3);
    }
}

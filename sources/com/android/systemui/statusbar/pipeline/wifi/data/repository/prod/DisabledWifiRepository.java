package com.android.systemui.statusbar.pipeline.wifi.data.repository.prod;

import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.RealWifiRepository;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import kotlin.collections.EmptyList;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DisabledWifiRepository implements RealWifiRepository {
    public static final DataActivityModel ACTIVITY = new DataActivityModel(false, false);
    public final ReadonlyStateFlow isWifiDefault;
    public final ReadonlyStateFlow isWifiEnabled;
    public final ReadonlyStateFlow secondaryNetworks;
    public final ReadonlyStateFlow wifiActivity;
    public final ReadonlyStateFlow wifiNetwork;
    public final ReadonlyStateFlow wifiScanResults;

    public DisabledWifiRepository() {
        Boolean bool = Boolean.FALSE;
        this.isWifiEnabled = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(bool));
        this.isWifiDefault = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(bool));
        this.wifiNetwork = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(WifiNetworkModel.Unavailable.INSTANCE));
        EmptyList emptyList = EmptyList.INSTANCE;
        this.secondaryNetworks = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(emptyList));
        this.wifiActivity = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(ACTIVITY));
        this.wifiScanResults = new ReadonlyStateFlow(StateFlowKt.MutableStateFlow(emptyList));
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
}

package com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel;

import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class LocationBasedWifiViewModel implements WifiViewModelCommon {
    public final WifiViewModelCommon commonImpl;

    public LocationBasedWifiViewModel(WifiViewModelCommon wifiViewModelCommon) {
        this.commonImpl = wifiViewModelCommon;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModelCommon
    public final StateFlow getWifiIcon() {
        return this.commonImpl.getWifiIcon();
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModelCommon
    public final Flow isActivityContainerVisible() {
        return this.commonImpl.isActivityContainerVisible();
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModelCommon
    public final Flow isActivityInViewVisible() {
        return this.commonImpl.isActivityInViewVisible();
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModelCommon
    public final Flow isActivityOutViewVisible() {
        return this.commonImpl.isActivityOutViewVisible();
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModelCommon
    public final Flow isAirplaneSpacerVisible() {
        return this.commonImpl.isAirplaneSpacerVisible();
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModelCommon
    public final Flow isSignalSpacerVisible() {
        return this.commonImpl.isSignalSpacerVisible();
    }
}

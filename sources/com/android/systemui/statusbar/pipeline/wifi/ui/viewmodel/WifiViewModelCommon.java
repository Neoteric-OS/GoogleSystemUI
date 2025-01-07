package com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel;

import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface WifiViewModelCommon {
    StateFlow getWifiIcon();

    Flow isActivityContainerVisible();

    Flow isActivityInViewVisible();

    Flow isActivityOutViewVisible();

    Flow isAirplaneSpacerVisible();

    Flow isSignalSpacerVisible();
}

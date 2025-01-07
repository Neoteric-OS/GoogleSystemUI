package com.android.systemui.statusbar.pipeline.wifi.ui;

import android.view.ViewGroup;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.HomeWifiViewModel;
import com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.KeyguardWifiViewModel;
import com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.LocationBasedWifiViewModel;
import com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.QsWifiViewModel;
import com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel;
import kotlin.NoWhenBranchMatchedException;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiUiAdapter {
    public final StatusBarIconController iconController;
    public final WifiViewModel wifiViewModel;

    public WifiUiAdapter(StatusBarIconController statusBarIconController, WifiViewModel wifiViewModel) {
        this.iconController = statusBarIconController;
        this.wifiViewModel = wifiViewModel;
    }

    public final LocationBasedWifiViewModel bindGroup(ViewGroup viewGroup, StatusBarLocation statusBarLocation) {
        LocationBasedWifiViewModel homeWifiViewModel;
        int ordinal = statusBarLocation.ordinal();
        WifiViewModel wifiViewModel = this.wifiViewModel;
        if (ordinal == 0) {
            homeWifiViewModel = new HomeWifiViewModel(wifiViewModel);
        } else if (ordinal == 1) {
            homeWifiViewModel = new KeyguardWifiViewModel(wifiViewModel);
        } else {
            if (ordinal != 2) {
                if (ordinal != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                throw new IllegalArgumentException("invalid location for WifiViewModel: " + statusBarLocation);
            }
            homeWifiViewModel = new QsWifiViewModel(wifiViewModel);
        }
        RepeatWhenAttachedKt.repeatWhenAttached(viewGroup, EmptyCoroutineContext.INSTANCE, new WifiUiAdapter$bindGroup$1(homeWifiViewModel, this, null));
        return homeWifiViewModel;
    }
}

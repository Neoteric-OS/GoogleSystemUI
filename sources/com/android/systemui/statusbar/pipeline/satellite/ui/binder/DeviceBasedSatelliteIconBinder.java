package com.android.systemui.statusbar.pipeline.satellite.ui.binder;

import com.android.systemui.statusbar.pipeline.satellite.ui.viewmodel.DeviceBasedSatelliteViewModel;
import com.android.systemui.statusbar.pipeline.satellite.ui.viewmodel.DeviceBasedSatelliteViewModelImpl;
import com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView;
import com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView$Companion$withDefaultBinding$2;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class DeviceBasedSatelliteIconBinder {
    public static SingleBindableStatusBarIconView$Companion$withDefaultBinding$2 bind(SingleBindableStatusBarIconView singleBindableStatusBarIconView, final DeviceBasedSatelliteViewModel deviceBasedSatelliteViewModel) {
        int i = SingleBindableStatusBarIconView.$r8$clinit;
        return SingleBindableStatusBarIconView.Companion.withDefaultBinding(singleBindableStatusBarIconView, new Function0() { // from class: com.android.systemui.statusbar.pipeline.satellite.ui.binder.DeviceBasedSatelliteIconBinder$bind$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(((StateFlowImpl) ((DeviceBasedSatelliteViewModelImpl) DeviceBasedSatelliteViewModel.this).icon.$$delegate_0).getValue() != null);
            }
        }, new DeviceBasedSatelliteIconBinder$bind$2(deviceBasedSatelliteViewModel, singleBindableStatusBarIconView, null));
    }
}

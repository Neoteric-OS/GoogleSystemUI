package com.android.systemui.statusbar.pipeline.satellite.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.android.systemui.statusbar.pipeline.satellite.ui.binder.DeviceBasedSatelliteIconBinder;
import com.android.systemui.statusbar.pipeline.satellite.ui.viewmodel.DeviceBasedSatelliteViewModel;
import com.android.systemui.statusbar.pipeline.shared.ui.view.SingleBindableStatusBarIconView;
import com.android.wm.shell.R;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceBasedSatelliteBindableIcon$initializer$1 {
    public final /* synthetic */ DeviceBasedSatelliteViewModel $viewModel;
    public final /* synthetic */ DeviceBasedSatelliteBindableIcon this$0;

    public DeviceBasedSatelliteBindableIcon$initializer$1(DeviceBasedSatelliteBindableIcon deviceBasedSatelliteBindableIcon, DeviceBasedSatelliteViewModel deviceBasedSatelliteViewModel) {
        this.this$0 = deviceBasedSatelliteBindableIcon;
        this.$viewModel = deviceBasedSatelliteViewModel;
    }

    public final SingleBindableStatusBarIconView createAndBind(Context context) {
        int i = SingleBindableStatusBarIconView.$r8$clinit;
        final SingleBindableStatusBarIconView singleBindableStatusBarIconView = (SingleBindableStatusBarIconView) LayoutInflater.from(context).inflate(R.layout.bindable_status_bar_icon, (ViewGroup) null);
        String str = this.this$0.slot;
        final DeviceBasedSatelliteViewModel deviceBasedSatelliteViewModel = this.$viewModel;
        singleBindableStatusBarIconView.initView(str, new Function0() { // from class: com.android.systemui.statusbar.pipeline.satellite.ui.DeviceBasedSatelliteBindableIcon$initializer$1$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DeviceBasedSatelliteIconBinder.bind(SingleBindableStatusBarIconView.this, deviceBasedSatelliteViewModel);
            }
        });
        return singleBindableStatusBarIconView;
    }
}

package com.android.systemui.statusbar.pipeline.satellite.ui;

import android.R;
import android.content.Context;
import com.android.systemui.statusbar.pipeline.satellite.ui.viewmodel.DeviceBasedSatelliteViewModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceBasedSatelliteBindableIcon {
    public final DeviceBasedSatelliteBindableIcon$initializer$1 initializer;
    public final boolean shouldBindIcon = true;
    public final String slot;

    public DeviceBasedSatelliteBindableIcon(Context context, DeviceBasedSatelliteViewModel deviceBasedSatelliteViewModel) {
        this.slot = context.getString(R.string.sync_too_many_deletes);
        this.initializer = new DeviceBasedSatelliteBindableIcon$initializer$1(this, deviceBasedSatelliteViewModel);
    }
}

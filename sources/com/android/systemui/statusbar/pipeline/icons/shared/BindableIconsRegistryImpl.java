package com.android.systemui.statusbar.pipeline.icons.shared;

import com.android.systemui.statusbar.pipeline.satellite.ui.DeviceBasedSatelliteBindableIcon;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BindableIconsRegistryImpl {
    public final List bindableIcons;

    public BindableIconsRegistryImpl(DeviceBasedSatelliteBindableIcon deviceBasedSatelliteBindableIcon) {
        this.bindableIcons = Collections.singletonList(deviceBasedSatelliteBindableIcon);
    }
}

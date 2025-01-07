package com.android.systemui.statusbar.pipeline.satellite.ui.model;

import com.android.systemui.common.shared.model.ContentDescription;
import com.android.systemui.common.shared.model.Icon;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SatelliteIconModel {
    public static Icon.Resource fromSignalStrength(int i) {
        if (i == 0) {
            return new Icon.Resource(R.drawable.ic_satellite_connected_0, new ContentDescription.Resource(R.string.accessibility_status_bar_satellite_no_connection));
        }
        if (i == 1 || i == 2) {
            return new Icon.Resource(R.drawable.ic_satellite_connected_1, new ContentDescription.Resource(R.string.accessibility_status_bar_satellite_poor_connection));
        }
        if (i == 3 || i == 4) {
            return new Icon.Resource(R.drawable.ic_satellite_connected_2, new ContentDescription.Resource(R.string.accessibility_status_bar_satellite_good_connection));
        }
        return null;
    }
}

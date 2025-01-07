package com.android.systemui.controls.controller;

import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlsTileResourceConfigurationImpl implements ControlsTileResourceConfiguration {
    @Override // com.android.systemui.controls.controller.ControlsTileResourceConfiguration
    public final String getPackageName() {
        return null;
    }

    @Override // com.android.systemui.controls.controller.ControlsTileResourceConfiguration
    public final int getTileImageId() {
        return R.drawable.controls_icon;
    }

    @Override // com.android.systemui.controls.controller.ControlsTileResourceConfiguration
    public final int getTileTitleId() {
        return R.string.quick_controls_title;
    }
}

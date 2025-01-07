package com.google.android.systemui.columbus.legacy.sensors.config;

import android.content.Context;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SensorConfiguration {
    public final float defaultSensitivityValue;
    public final float lowSensitivityValue;

    public SensorConfiguration(Context context) {
        this.defaultSensitivityValue = context.getResources().getInteger(R.integer.columbus_default_sensitivity_percent) * 0.01f;
        this.lowSensitivityValue = context.getResources().getInteger(R.integer.columbus_low_sensitivity_percent) * 0.01f;
    }
}

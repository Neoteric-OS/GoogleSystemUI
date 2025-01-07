package com.google.android.systemui.columbus.legacy.sensors.config;

import android.provider.Settings;
import com.android.systemui.settings.UserTrackerImpl;
import com.google.android.systemui.columbus.legacy.ColumbusSettings;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LowSensitivitySettingAdjustment {
    public Function1 callback;
    public final SensorConfiguration sensorConfiguration;
    public boolean useLowSensitivity;

    public LowSensitivitySettingAdjustment(ColumbusSettings columbusSettings, SensorConfiguration sensorConfiguration) {
        this.sensorConfiguration = sensorConfiguration;
        columbusSettings.registerColumbusSettingsChangeListener(new ColumbusSettings.ColumbusSettingsChangeListener() { // from class: com.google.android.systemui.columbus.legacy.sensors.config.LowSensitivitySettingAdjustment$settingsChangeListener$1
            @Override // com.google.android.systemui.columbus.legacy.ColumbusSettings.ColumbusSettingsChangeListener
            public final void onLowSensitivityChange(boolean z) {
                LowSensitivitySettingAdjustment lowSensitivitySettingAdjustment = LowSensitivitySettingAdjustment.this;
                if (lowSensitivitySettingAdjustment.useLowSensitivity != z) {
                    lowSensitivitySettingAdjustment.useLowSensitivity = z;
                    Function1 function1 = lowSensitivitySettingAdjustment.callback;
                    if (function1 != null) {
                        ((GestureConfiguration$adjustmentCallback$1) function1).invoke(lowSensitivitySettingAdjustment);
                    }
                }
            }
        });
        this.useLowSensitivity = Settings.Secure.getIntForUser(columbusSettings.contentResolver, "columbus_low_sensitivity", 0, ((UserTrackerImpl) columbusSettings.userTracker).getUserId()) != 0;
        Function1 function1 = this.callback;
        if (function1 != null) {
            ((GestureConfiguration$adjustmentCallback$1) function1).invoke(this);
        }
    }
}

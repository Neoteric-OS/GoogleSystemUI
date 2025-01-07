package com.android.settingslib.fuelgauge;

import android.os.SystemProperties;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BatteryUtils {
    public static Boolean sChargingStringV2Enabled;

    public static void setChargingStringV2Enabled(Boolean bool) {
        setChargingStringV2Enabled(bool, true);
    }

    public static void setChargingStringV2Enabled(Boolean bool, boolean z) {
        if (z) {
            SystemProperties.set("charging_string.apply_v2", bool == null ? "" : String.valueOf(bool));
        }
        sChargingStringV2Enabled = bool;
    }
}

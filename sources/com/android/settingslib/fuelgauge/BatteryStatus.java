package com.android.settingslib.fuelgauge;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.SystemProperties;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.wm.shell.R;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BatteryStatus {
    public final int chargingStatus;
    public final Optional incompatibleCharger;
    public final int level;
    public final int maxChargingWattage;
    public final int plugged;
    public final boolean present;
    public final int status;

    public BatteryStatus() {
        this.status = 1;
        this.level = 100;
        this.plugged = 0;
        this.chargingStatus = 1;
        this.maxChargingWattage = 0;
        this.present = true;
        this.incompatibleCharger = Optional.empty();
    }

    public static int calculateChargingSpeed(int i, int i2, Context context) {
        if (i2 <= 0) {
            i2 = 5000000;
        }
        int round = i > 0 ? (int) Math.round(i * 0.001d * i2 * 0.001d) : -1;
        if (round <= 0) {
            return -1;
        }
        if (round < context.getResources().getInteger(R.integer.config_chargingSlowlyThreshold)) {
            return 0;
        }
        Resources resources = context.getResources();
        if (BatteryUtils.sChargingStringV2Enabled == null) {
            BatteryUtils.sChargingStringV2Enabled = Boolean.valueOf(SystemProperties.getBoolean("charging_string.apply_v2", false));
        }
        return round > resources.getInteger(BatteryUtils.sChargingStringV2Enabled.booleanValue() ? R.integer.config_chargingFastThreshold_v2 : R.integer.config_chargingFastThreshold) ? 2 : 1;
    }

    public static int getBatteryLevel(Intent intent) {
        if (intent == null) {
            return -1;
        }
        return getBatteryLevel(intent.getIntExtra("level", -1), intent.getIntExtra("scale", 0));
    }

    public static boolean isCharged(int i, int i2) {
        return i == 5 || i2 >= 100;
    }

    public static boolean isPluggedIn(int i) {
        return i == 1 || i == 2 || i == 4 || i == 8;
    }

    public final int getChargingSpeed(Context context) {
        int integer = context.getResources().getInteger(R.integer.config_chargingSlowlyThreshold);
        Resources resources = context.getResources();
        if (BatteryUtils.sChargingStringV2Enabled == null) {
            BatteryUtils.sChargingStringV2Enabled = Boolean.valueOf(SystemProperties.getBoolean("charging_string.apply_v2", false));
        }
        int integer2 = resources.getInteger(BatteryUtils.sChargingStringV2Enabled.booleanValue() ? R.integer.config_chargingFastThreshold_v2 : R.integer.config_chargingFastThreshold);
        int i = this.maxChargingWattage;
        if (i <= 0) {
            return -1;
        }
        if (i < integer) {
            return 0;
        }
        return i > integer2 ? 2 : 1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BatteryStatus{status=");
        sb.append(this.status);
        sb.append(",level=");
        sb.append(this.level);
        sb.append(",plugged=");
        sb.append(this.plugged);
        sb.append(",chargingStatus=");
        sb.append(this.chargingStatus);
        sb.append(",maxChargingWattage=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.maxChargingWattage, "}");
    }

    public static int getBatteryLevel(int i, int i2) {
        if (i2 == 0) {
            return -1;
        }
        return Math.round((i / i2) * 100.0f);
    }

    public BatteryStatus(Intent intent, boolean z) {
        Optional of = Optional.of(Boolean.valueOf(z));
        this.status = intent.getIntExtra("status", 1);
        this.plugged = intent.getIntExtra("plugged", 0);
        this.level = getBatteryLevel(intent);
        this.chargingStatus = intent.getIntExtra("android.os.extra.CHARGING_STATUS", 1);
        this.present = intent.getBooleanExtra("present", true);
        this.incompatibleCharger = of;
        int intExtra = intent.getIntExtra("max_charging_current", -1);
        this.maxChargingWattage = intExtra > 0 ? (int) Math.round(intExtra * 0.001d * (intent.getIntExtra("max_charging_voltage", -1) <= 0 ? 5000000 : r5) * 0.001d) : -1;
    }
}

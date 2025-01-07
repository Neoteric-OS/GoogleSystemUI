package com.android.settingslib.fuelgauge;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Estimate {
    public final long averageDischargeTime;
    public final long estimateMillis;
    public final boolean isBasedOnUsage;

    public Estimate(long j, long j2, boolean z) {
        this.estimateMillis = j;
        this.isBasedOnUsage = z;
        this.averageDischargeTime = j2;
    }

    public static final void storeCachedEstimate(Context context, Estimate estimate) {
        ContentResolver contentResolver = context.getContentResolver();
        Settings.Global.putLong(contentResolver, "time_remaining_estimate_millis", estimate.estimateMillis);
        Settings.Global.putInt(contentResolver, "time_remaining_estimate_based_on_usage", estimate.isBasedOnUsage ? 1 : 0);
        Settings.Global.putLong(contentResolver, "average_time_to_discharge", estimate.averageDischargeTime);
        Settings.Global.putLong(contentResolver, "battery_estimates_last_update_time", System.currentTimeMillis());
    }
}

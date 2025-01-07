package com.google.android.systemui.power;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Settings;
import android.util.KeyValueListParser;
import android.util.Log;
import com.android.settingslib.fuelgauge.Estimate;
import com.android.settingslib.utils.PowerUtil;
import com.android.systemui.power.EnhancedEstimates;
import java.time.Duration;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EnhancedEstimatesGoogleImpl implements EnhancedEstimates {
    public final Context mContext;
    public final KeyValueListParser mParser = new KeyValueListParser(',');

    public EnhancedEstimatesGoogleImpl(Context context) {
        this.mContext = context;
    }

    public final Estimate getEstimate() {
        long j;
        try {
            Cursor query = this.mContext.getContentResolver().query(new Uri.Builder().scheme("content").authority("com.google.android.apps.turbo.estimated_time_remaining").appendPath("time_remaining").build(), null, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        boolean z = true;
                        if (query.getColumnIndex("is_based_on_usage") != -1 && query.getInt(query.getColumnIndex("is_based_on_usage")) == 0) {
                            z = false;
                        }
                        boolean z2 = z;
                        int columnIndex = query.getColumnIndex("average_battery_life");
                        if (columnIndex != -1) {
                            long j2 = query.getLong(columnIndex);
                            if (j2 != -1) {
                                long millis = Duration.ofMinutes(15L).toMillis();
                                if (Duration.ofMillis(j2).compareTo(Duration.ofDays(1L)) >= 0) {
                                    millis = Duration.ofHours(1L).toMillis();
                                }
                                j = PowerUtil.roundTimeToNearestThreshold(j2, millis);
                                Estimate estimate = new Estimate(query.getLong(query.getColumnIndex("battery_estimate")), j, z2);
                                query.close();
                                return estimate;
                            }
                        }
                        j = -1;
                        Estimate estimate2 = new Estimate(query.getLong(query.getColumnIndex("battery_estimate")), j, z2);
                        query.close();
                        return estimate2;
                    }
                } finally {
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Exception e) {
            Log.d("EnhancedEstimates", "Something went wrong when getting an estimate from Turbo", e);
        }
        return new Estimate(-1L, -1L, false);
    }

    public final boolean isHybridNotificationEnabled() {
        try {
            if (!this.mContext.getPackageManager().getPackageInfo("com.google.android.apps.turbo", 512).applicationInfo.enabled) {
                return false;
            }
            updateFlags();
            return this.mParser.getBoolean("hybrid_enabled", true);
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public final void updateFlags() {
        try {
            this.mParser.setString(Settings.Global.getString(this.mContext.getContentResolver(), "hybrid_sysui_battery_warning_flags"));
        } catch (IllegalArgumentException unused) {
            Log.e("EnhancedEstimates", "Bad hybrid sysui warning flags");
        }
    }
}

package com.android.settingslib.notification.modes;

import android.content.Context;
import com.android.internal.logging.MetricsLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class ZenModeDialogMetricsLogger {
    public final Context mContext;

    public ZenModeDialogMetricsLogger(Context context) {
        this.mContext = context;
    }

    public void logOnClickTimeButton(boolean z) {
        MetricsLogger.action(this.mContext, 163, z);
    }

    public void logOnConditionSelected() {
        MetricsLogger.action(this.mContext, 164);
    }

    public void logOnEnableZenModeForever() {
        MetricsLogger.action(this.mContext, 1259);
    }

    public void logOnEnableZenModeUntilAlarm() {
        MetricsLogger.action(this.mContext, 1261);
    }

    public void logOnEnableZenModeUntilCountdown() {
        MetricsLogger.action(this.mContext, 1260);
    }
}

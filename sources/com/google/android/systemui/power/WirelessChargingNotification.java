package com.google.android.systemui.power;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.plugins.ActivityStarter;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WirelessChargingNotification {
    static final long WIRELESS_CHARGING_DEFAULT_TIMESTAMP = -1;
    public final ActivityStarter mActivityStarter;
    public final Context mContext;
    public final Executor mExecutor;
    public boolean mIsNotificationActive = false;
    boolean mIsWirelessCharging;
    NotificationManager mNotificationManager;
    public final UiEventLogger mUiEventLogger;

    public WirelessChargingNotification(Context context, Executor executor, ActivityStarter activityStarter, UiEventLogger uiEventLogger) {
        this.mContext = context;
        this.mExecutor = executor;
        this.mActivityStarter = activityStarter;
        this.mUiEventLogger = uiEventLogger;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }

    public static String getKey() {
        return ActivityManager.getCurrentUser() + "|wireless_charging_notification_timestamp";
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext().getSharedPreferences("wireless_charging_notification_shared_prefs", 0);
    }

    public static void putWirelessChargingNotificationTimestamp(Context context, String str) {
        getSharedPreferences(context).edit().putLong(str, System.currentTimeMillis()).apply();
    }
}

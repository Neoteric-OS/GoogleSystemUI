package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IncompatibleChargerNotification {
    public static final String KEY_COMPATIBLE_CHARGER_TIME = "last_compatible_charger_time";
    public static final String KEY_INCOMPATIBLE_CHARGER_TIME = "last_incompatible_charger_time";
    public final Context mContext;
    public final NotificationManager mNotificationManager;
    public final UiEventLogger mUiEventLogger;
    public final Handler mMainHandler = new Handler(Looper.getMainLooper());
    public boolean mNotificationActive = false;
    public boolean mContainsIncompatibleChargers = false;

    public IncompatibleChargerNotification(Context context, UiEventLogger uiEventLogger) {
        this.mContext = context;
        this.mUiEventLogger = uiEventLogger;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext().getSharedPreferences("incompatible_charger_shared_prefs", 0);
    }
}

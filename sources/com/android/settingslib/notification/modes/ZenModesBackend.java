package com.android.settingslib.notification.modes;

import android.app.NotificationManager;
import android.content.Context;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ZenModesBackend {
    public static ZenModesBackend sInstance;
    public final Context mContext;
    public final NotificationManager mNotificationManager;

    public ZenModesBackend(Context context) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }

    public static void setInstance(ZenModesBackend zenModesBackend) {
        sInstance = zenModesBackend;
    }
}

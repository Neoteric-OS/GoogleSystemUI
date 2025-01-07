package com.google.android.systemui.power;

import android.app.NotificationManager;
import android.content.Context;
import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ExtremeLowBatteryNotification {
    public final Context mContext;
    NotificationManager mNotificationManager;
    public final UiEventLogger mUiEventLogger;

    public ExtremeLowBatteryNotification(Context context, UiEventLogger uiEventLogger) {
        this.mContext = context;
        this.mUiEventLogger = uiEventLogger;
        this.mNotificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
    }
}

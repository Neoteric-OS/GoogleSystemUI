package com.android.systemui.statusbar.notification;

import android.service.notification.StatusBarNotification;
import com.android.systemui.statusbar.notification.collection.ListEntry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationUtilsKt {
    public static final String getLogKey(ListEntry listEntry) {
        if (listEntry != null) {
            return NotificationUtils.logKey(listEntry);
        }
        return null;
    }

    public static final String getLogKey(StatusBarNotification statusBarNotification) {
        String key;
        if (statusBarNotification == null || (key = statusBarNotification.getKey()) == null) {
            return null;
        }
        return key.replace("\n", "");
    }
}

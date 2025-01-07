package com.android.systemui.statusbar.notification.icon.ui.viewmodel;

import android.graphics.drawable.Icon;
import com.android.systemui.statusbar.notification.shared.ActiveNotificationModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationIconsViewDataKt {
    public static final NotificationIconInfo toIconInfo(ActiveNotificationModel activeNotificationModel, Icon icon) {
        String str;
        if (icon == null || (str = activeNotificationModel.groupKey) == null) {
            return null;
        }
        return new NotificationIconInfo(icon, activeNotificationModel.key, str);
    }
}

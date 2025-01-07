package com.android.systemui.statusbar.notification.logging;

import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public enum NotificationPanelLogger$NotificationPanelEvent implements UiEventLogger.UiEventEnum {
    NOTIFICATION_PANEL_OPEN_STATUS_BAR(200),
    NOTIFICATION_PANEL_OPEN_LOCKSCREEN(201),
    NOTIFICATION_DRAG(1226);

    private final int mId;

    NotificationPanelLogger$NotificationPanelEvent(int i) {
        this.mId = i;
    }

    public final int getId() {
        return this.mId;
    }
}

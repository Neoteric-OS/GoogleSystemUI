package com.android.systemui.statusbar.notification.row;

import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
enum NotificationControlsEvent implements UiEventLogger.UiEventEnum {
    NOTIFICATION_CONTROLS_OPEN(594),
    NOTIFICATION_CONTROLS_SAVE_IMPORTANCE(595),
    NOTIFICATION_CONTROLS_CLOSE(596);

    private final int mId;

    NotificationControlsEvent(int i) {
        this.mId = i;
    }

    public final int getId() {
        return this.mId;
    }
}

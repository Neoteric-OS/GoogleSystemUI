package com.android.systemui.statusbar.notification.row;

import com.android.systemui.log.LogBuffer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationRowLogger {
    public final LogBuffer buffer;
    public final LogBuffer notificationRenderBuffer;

    public NotificationRowLogger(LogBuffer logBuffer, LogBuffer logBuffer2) {
        this.buffer = logBuffer;
        this.notificationRenderBuffer = logBuffer2;
    }
}

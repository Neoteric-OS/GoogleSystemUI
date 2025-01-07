package com.android.systemui.statusbar.notification.stack;

import com.android.systemui.log.LogBuffer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationStackScrollLogger {
    public final LogBuffer buffer;
    public final LogBuffer notificationRenderBuffer;
    public final LogBuffer shadeLogBuffer;

    public NotificationStackScrollLogger(LogBuffer logBuffer, LogBuffer logBuffer2, LogBuffer logBuffer3) {
        this.buffer = logBuffer;
        this.notificationRenderBuffer = logBuffer2;
        this.shadeLogBuffer = logBuffer3;
    }
}

package com.android.systemui.statusbar.notification.dagger;

import com.android.systemui.statusbar.notification.logging.NotificationPanelLoggerImpl;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationsModule_ProvideNotificationPanelLoggerFactory implements Provider {
    public static NotificationPanelLoggerImpl provideNotificationPanelLogger() {
        return new NotificationPanelLoggerImpl();
    }
}

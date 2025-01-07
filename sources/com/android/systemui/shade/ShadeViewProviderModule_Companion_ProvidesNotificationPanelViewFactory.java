package com.android.systemui.shade;

import com.android.wm.shell.R;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ShadeViewProviderModule_Companion_ProvidesNotificationPanelViewFactory implements Provider {
    public static NotificationPanelView providesNotificationPanelView(NotificationShadeWindowView notificationShadeWindowView) {
        return (NotificationPanelView) notificationShadeWindowView.requireViewById(R.id.notification_panel);
    }
}

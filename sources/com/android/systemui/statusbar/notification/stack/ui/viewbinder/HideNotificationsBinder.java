package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import com.android.internal.view.OneShotPreDrawListener;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HideNotificationsBinder {
    public static final HideNotificationsBinder INSTANCE = null;

    public static final void access$bindHideState(final NotificationStackScrollLayoutController notificationStackScrollLayoutController, boolean z) {
        if (z) {
            notificationStackScrollLayoutController.updateNotificationsContainerVisibility(false, false);
            notificationStackScrollLayoutController.mView.mSuppressChildrenMeasureAndLayout = true;
            return;
        }
        NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
        notificationStackScrollLayout.mSuppressChildrenMeasureAndLayout = false;
        notificationStackScrollLayout.requestLayout();
        OneShotPreDrawListener.add(notificationStackScrollLayoutController.mView, new Runnable() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.HideNotificationsBinder$bindHideState$1
            @Override // java.lang.Runnable
            public final void run() {
                NotificationStackScrollLayoutController.this.updateNotificationsContainerVisibility(true, true);
            }
        });
    }
}

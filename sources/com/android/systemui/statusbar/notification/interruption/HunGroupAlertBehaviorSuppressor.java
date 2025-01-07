package com.android.systemui.statusbar.notification.interruption;

import android.service.notification.StatusBarNotification;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HunGroupAlertBehaviorSuppressor extends VisualInterruptionFilter {
    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter
    public final boolean shouldSuppress(NotificationEntry notificationEntry) {
        StatusBarNotification statusBarNotification = notificationEntry.mSbn;
        return statusBarNotification.isGroup() && statusBarNotification.getNotification().suppressAlertingDueToGrouping();
    }
}

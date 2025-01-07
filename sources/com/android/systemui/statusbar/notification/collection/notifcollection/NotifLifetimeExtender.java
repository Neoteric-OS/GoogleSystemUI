package com.android.systemui.statusbar.notification.collection.notifcollection;

import com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda12;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface NotifLifetimeExtender {
    void cancelLifetimeExtension$1(NotificationEntry notificationEntry);

    String getName();

    boolean maybeExtendLifetime(NotificationEntry notificationEntry, int i);

    void setCallback(NotifCollection$$ExternalSyntheticLambda12 notifCollection$$ExternalSyntheticLambda12);
}

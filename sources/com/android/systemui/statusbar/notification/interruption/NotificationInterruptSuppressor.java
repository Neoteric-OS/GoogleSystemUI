package com.android.systemui.statusbar.notification.interruption;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface NotificationInterruptSuppressor {
    String getName();

    default boolean suppressAwakeHeadsUp(NotificationEntry notificationEntry) {
        return false;
    }

    default boolean suppressAwakeInterruptions() {
        return false;
    }

    boolean suppressInterruptions();
}

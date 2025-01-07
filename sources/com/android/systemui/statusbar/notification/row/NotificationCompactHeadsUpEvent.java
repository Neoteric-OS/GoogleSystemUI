package com.android.systemui.statusbar.notification.row;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationCompactHeadsUpEvent implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ NotificationCompactHeadsUpEvent[] $VALUES;
    public static final NotificationCompactHeadsUpEvent NOTIFICATION_COMPACT_HUN_SHOWN;
    private final int eventId = 1857;

    static {
        NotificationCompactHeadsUpEvent notificationCompactHeadsUpEvent = new NotificationCompactHeadsUpEvent();
        NOTIFICATION_COMPACT_HUN_SHOWN = notificationCompactHeadsUpEvent;
        NotificationCompactHeadsUpEvent[] notificationCompactHeadsUpEventArr = {notificationCompactHeadsUpEvent};
        $VALUES = notificationCompactHeadsUpEventArr;
        EnumEntriesKt.enumEntries(notificationCompactHeadsUpEventArr);
    }

    public static NotificationCompactHeadsUpEvent valueOf(String str) {
        return (NotificationCompactHeadsUpEvent) Enum.valueOf(NotificationCompactHeadsUpEvent.class, str);
    }

    public static NotificationCompactHeadsUpEvent[] values() {
        return (NotificationCompactHeadsUpEvent[]) $VALUES.clone();
    }

    public final int getId() {
        return this.eventId;
    }
}

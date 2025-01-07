package com.android.systemui.statusbar.notification.row;

import android.app.Notification;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.shared.RichOngoingContentModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NoOpRichOngoingNotificationContentExtractor implements RichOngoingNotificationContentExtractor {
    @Override // com.android.systemui.statusbar.notification.row.RichOngoingNotificationContentExtractor
    public final RichOngoingContentModel extractContentModel(NotificationEntry notificationEntry, Notification.Builder builder) {
        return null;
    }
}

package com.android.systemui.statusbar.notification;

import android.app.Notification;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import com.android.systemui.statusbar.notification.ConversationNotificationManager;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.NotificationRowContentBinderLogger;
import java.util.function.BiFunction;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConversationNotificationProcessor {
    public final ConversationNotificationManager conversationNotificationManager;
    public final LauncherApps launcherApps;

    public ConversationNotificationProcessor(LauncherApps launcherApps, ConversationNotificationManager conversationNotificationManager) {
        this.launcherApps = launcherApps;
        this.conversationNotificationManager = conversationNotificationManager;
    }

    public final Notification.MessagingStyle processNotification(final NotificationEntry notificationEntry, final Notification.Builder builder, NotificationRowContentBinderLogger notificationRowContentBinderLogger) {
        Notification.Style style = builder.getStyle();
        Notification.MessagingStyle messagingStyle = style instanceof Notification.MessagingStyle ? (Notification.MessagingStyle) style : null;
        if (messagingStyle == null) {
            return null;
        }
        messagingStyle.setConversationType(notificationEntry.mRanking.getChannel().isImportantConversation() ? 2 : 1);
        ShortcutInfo conversationShortcutInfo = notificationEntry.mRanking.getConversationShortcutInfo();
        if (conversationShortcutInfo != null) {
            notificationRowContentBinderLogger.logAsyncTaskProgress(notificationEntry, "getting shortcut icon");
            messagingStyle.setShortcutIcon(this.launcherApps.getShortcutIcon(conversationShortcutInfo));
            CharSequence label = conversationShortcutInfo.getLabel();
            if (label != null) {
                messagingStyle.setConversationTitle(label);
            }
        }
        final ConversationNotificationManager conversationNotificationManager = this.conversationNotificationManager;
        Object compute = conversationNotificationManager.states.compute(notificationEntry.mKey, new BiFunction() { // from class: com.android.systemui.statusbar.notification.ConversationNotificationManager$getUnreadCount$1
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                int i;
                ConversationNotificationManager.ConversationState conversationState = (ConversationNotificationManager.ConversationState) obj2;
                if (conversationState != null) {
                    ConversationNotificationManager conversationNotificationManager2 = conversationNotificationManager;
                    Notification.Builder builder2 = builder;
                    conversationNotificationManager2.getClass();
                    Notification notification = conversationState.notification;
                    boolean areStyledNotificationsVisiblyDifferent = (notification.flags & 8) != 0 ? false : Notification.areStyledNotificationsVisiblyDifferent(Notification.Builder.recoverBuilder(conversationNotificationManager2.context, notification), builder2);
                    i = conversationState.unreadCount;
                    if (areStyledNotificationsVisiblyDifferent) {
                        i++;
                    }
                } else {
                    i = 1;
                }
                return new ConversationNotificationManager.ConversationState(i, NotificationEntry.this.mSbn.getNotification());
            }
        });
        Intrinsics.checkNotNull(compute);
        messagingStyle.setUnreadMessageCount(((ConversationNotificationManager.ConversationState) compute).unreadCount);
        return messagingStyle;
    }
}

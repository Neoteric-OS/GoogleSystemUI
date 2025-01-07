package com.android.systemui.statusbar.notification.people;

import android.app.NotificationChannel;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import com.android.systemui.plugins.NotificationPersonExtractorPlugin;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PeopleNotificationIdentifierImpl {
    public final GroupMembershipManagerImpl groupManager;
    public final NotificationPersonExtractorPluginBoundary personExtractor;

    public PeopleNotificationIdentifierImpl(NotificationPersonExtractorPluginBoundary notificationPersonExtractorPluginBoundary, GroupMembershipManagerImpl groupMembershipManagerImpl) {
        this.personExtractor = notificationPersonExtractorPluginBoundary;
        this.groupManager = groupMembershipManagerImpl;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [int] */
    /* JADX WARN: Type inference failed for: r0v8 */
    public final int getPeopleNotificationType(NotificationEntry notificationEntry) {
        int i;
        List children;
        NotificationListenerService.Ranking ranking = notificationEntry.mRanking;
        int i2 = 0;
        if (ranking.isConversation()) {
            i = 1;
            if (ranking.getConversationShortcutInfo() != null) {
                NotificationChannel channel = ranking.getChannel();
                i = (channel == null || !channel.isImportantConversation()) ? 2 : 3;
            }
        } else {
            i = 0;
        }
        if (i == 3) {
            return 3;
        }
        StatusBarNotification statusBarNotification = notificationEntry.mSbn;
        NotificationPersonExtractorPlugin notificationPersonExtractorPlugin = this.personExtractor.plugin;
        int max = Math.max(i, (int) (notificationPersonExtractorPlugin != null ? notificationPersonExtractorPlugin.isPersonNotification(statusBarNotification) : 0));
        if (max == 3) {
            return 3;
        }
        this.groupManager.getClass();
        if (GroupMembershipManagerImpl.isGroupSummary(notificationEntry) && (children = GroupMembershipManagerImpl.getChildren(notificationEntry)) != null) {
            Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.notification.people.PeopleNotificationIdentifierImpl$getPeopleTypeOfSummary$childTypes$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    NotificationEntry notificationEntry2 = (NotificationEntry) obj;
                    PeopleNotificationIdentifierImpl peopleNotificationIdentifierImpl = PeopleNotificationIdentifierImpl.this;
                    Intrinsics.checkNotNull(notificationEntry2);
                    return Integer.valueOf(peopleNotificationIdentifierImpl.getPeopleNotificationType(notificationEntry2));
                }
            };
            Iterator it = children.iterator();
            while (it.hasNext() && (i2 = Math.max(i2, ((Number) function1.invoke(it.next())).intValue())) != 3) {
            }
        }
        return Math.max(max, i2);
    }
}

package com.android.systemui.statusbar.notification.collection.provider;

import android.app.Notification;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl;
import com.android.systemui.statusbar.notification.people.PeopleNotificationIdentifierImpl;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HighPriorityProvider {
    public final GroupMembershipManagerImpl mGroupMembershipManager;
    public final PeopleNotificationIdentifierImpl mPeopleNotificationIdentifier;

    public HighPriorityProvider(PeopleNotificationIdentifierImpl peopleNotificationIdentifierImpl, GroupMembershipManagerImpl groupMembershipManagerImpl) {
        this.mPeopleNotificationIdentifier = peopleNotificationIdentifierImpl;
        this.mGroupMembershipManager = groupMembershipManagerImpl;
    }

    public final boolean isHighPriority(ListEntry listEntry, boolean z) {
        NotificationEntry representativeEntry;
        if (listEntry == null || (representativeEntry = listEntry.getRepresentativeEntry()) == null) {
            return false;
        }
        if (representativeEntry.mRanking.getImportance() < 3 && (!z || ((representativeEntry.mRanking.getChannel() != null && representativeEntry.mRanking.getChannel().hasUserSetImportance()) || (!representativeEntry.mSbn.getNotification().isMediaNotification() && this.mPeopleNotificationIdentifier.getPeopleNotificationType(representativeEntry) == 0 && !representativeEntry.mSbn.getNotification().isStyle(Notification.MessagingStyle.class))))) {
            boolean z2 = listEntry instanceof NotificationEntry;
            GroupMembershipManagerImpl groupMembershipManagerImpl = this.mGroupMembershipManager;
            if (z2) {
                groupMembershipManagerImpl.getClass();
                if (!GroupMembershipManagerImpl.isGroupSummary((NotificationEntry) listEntry)) {
                    return false;
                }
            }
            groupMembershipManagerImpl.getClass();
            List<NotificationEntry> children = GroupMembershipManagerImpl.getChildren(listEntry);
            if (children == null) {
                return false;
            }
            for (NotificationEntry notificationEntry : children) {
                if (notificationEntry == listEntry || !isHighPriority(notificationEntry, z)) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean isHighPriorityConversation(ListEntry listEntry) {
        NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
        if (representativeEntry == null || this.mPeopleNotificationIdentifier.getPeopleNotificationType(representativeEntry) == 0) {
            return false;
        }
        if (representativeEntry.mRanking.getImportance() >= 3) {
            return true;
        }
        if (listEntry instanceof GroupEntry) {
            return ((GroupEntry) listEntry).mUnmodifiableChildren.stream().anyMatch(new HighPriorityProvider$$ExternalSyntheticLambda0());
        }
        return false;
    }
}

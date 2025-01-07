package com.android.systemui.statusbar.notification.collection.render;

import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GroupMembershipManagerImpl {
    public static List getChildren(ListEntry listEntry) {
        GroupEntry groupEntry;
        if (listEntry instanceof GroupEntry) {
            return ((GroupEntry) listEntry).mUnmodifiableChildren;
        }
        NotificationEntry representativeEntry = listEntry.getRepresentativeEntry();
        if (representativeEntry == null || !isGroupSummary(representativeEntry) || (groupEntry = representativeEntry.mAttachState.parent) == null) {
            return null;
        }
        return groupEntry.mUnmodifiableChildren;
    }

    public static NotificationEntry getGroupSummary(NotificationEntry notificationEntry) {
        GroupEntry groupEntry = notificationEntry.mAttachState.parent;
        if (groupEntry == GroupEntry.ROOT_ENTRY || groupEntry == null) {
            return null;
        }
        return groupEntry.mSummary;
    }

    public static boolean isGroupSummary(NotificationEntry notificationEntry) {
        GroupEntry groupEntry = notificationEntry.mAttachState.parent;
        return groupEntry != null && groupEntry.mSummary == notificationEntry;
    }
}

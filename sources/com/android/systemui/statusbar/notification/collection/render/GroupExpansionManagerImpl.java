package com.android.systemui.statusbar.notification.collection.render;

import android.util.Log;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.ListEntry;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$$ExternalSyntheticLambda4;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GroupExpansionManagerImpl implements Dumpable {
    public final DumpManager mDumpManager;
    public final GroupMembershipManagerImpl mGroupMembershipManager;
    public final Set mOnGroupChangeListeners = new HashSet();
    public final Set mExpandedGroups = new HashSet();
    public final GroupExpansionManagerImpl$$ExternalSyntheticLambda0 mNotifTracker = new OnBeforeRenderListListener() { // from class: com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl$$ExternalSyntheticLambda0
        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeRenderListListener
        public final void onBeforeRenderList$1(List list) {
            HashSet hashSet;
            GroupExpansionManagerImpl groupExpansionManagerImpl = GroupExpansionManagerImpl.this;
            if (groupExpansionManagerImpl.mExpandedGroups.isEmpty()) {
                return;
            }
            HashSet hashSet2 = new HashSet();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ListEntry listEntry = (ListEntry) it.next();
                if (listEntry instanceof GroupEntry) {
                    hashSet2.add(((GroupEntry) listEntry).mSummary);
                }
            }
            Set set = groupExpansionManagerImpl.mExpandedGroups;
            if (set == null || set.isEmpty()) {
                hashSet = new HashSet();
            } else if (hashSet2.isEmpty()) {
                hashSet = new HashSet(set);
            } else {
                HashSet hashSet3 = new HashSet();
                Iterator it2 = ((HashSet) set).iterator();
                while (it2.hasNext()) {
                    NotificationEntry notificationEntry = (NotificationEntry) it2.next();
                    if (!hashSet2.contains(notificationEntry)) {
                        hashSet3.add(notificationEntry);
                    }
                }
                hashSet = hashSet3;
            }
            Iterator it3 = hashSet.iterator();
            while (it3.hasNext()) {
                groupExpansionManagerImpl.setGroupExpanded((NotificationEntry) it3.next(), false);
            }
        }
    };

    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl$$ExternalSyntheticLambda0] */
    public GroupExpansionManagerImpl(DumpManager dumpManager, GroupMembershipManagerImpl groupMembershipManagerImpl) {
        this.mDumpManager = dumpManager;
        this.mGroupMembershipManager = groupMembershipManagerImpl;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "NotificationEntryExpansion state:", "  mExpandedGroups: ");
        m.append(((HashSet) this.mExpandedGroups).size());
        printWriter.println(m.toString());
        Iterator it = ((HashSet) this.mExpandedGroups).iterator();
        while (it.hasNext()) {
            printWriter.println("  * " + ((NotificationEntry) it.next()).mKey);
        }
    }

    public final boolean isGroupExpanded(NotificationEntry notificationEntry) {
        Set set = this.mExpandedGroups;
        this.mGroupMembershipManager.getClass();
        return set.contains(GroupMembershipManagerImpl.getGroupSummary(notificationEntry));
    }

    public final void setGroupExpanded(NotificationEntry notificationEntry, boolean z) {
        this.mGroupMembershipManager.getClass();
        NotificationEntry groupSummary = GroupMembershipManagerImpl.getGroupSummary(notificationEntry);
        if (notificationEntry.mAttachState.parent == null) {
            if (z) {
                Log.wtf("GroupExpansionaManagerImpl", "Cannot expand group that is not attached");
            } else {
                groupSummary = notificationEntry;
            }
        }
        if (z ? this.mExpandedGroups.add(groupSummary) : this.mExpandedGroups.remove(groupSummary)) {
            Iterator it = ((HashSet) this.mOnGroupChangeListeners).iterator();
            while (it.hasNext()) {
                NotificationStackScrollLayoutController$$ExternalSyntheticLambda4 notificationStackScrollLayoutController$$ExternalSyntheticLambda4 = (NotificationStackScrollLayoutController$$ExternalSyntheticLambda4) it.next();
                final ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
                NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController$$ExternalSyntheticLambda4.f$0.mView;
                if (notificationStackScrollLayout.mAnimationsEnabled && (notificationStackScrollLayout.mIsExpanded || expandableNotificationRow.mIsPinned)) {
                    notificationStackScrollLayout.mExpandedGroupView = expandableNotificationRow;
                    notificationStackScrollLayout.mNeedsAnimation = true;
                }
                expandableNotificationRow.setChildrenExpanded(z);
                notificationStackScrollLayout.onChildHeightChanged(expandableNotificationRow, false);
                notificationStackScrollLayout.mAnimationFinishedRunnables.add(new Runnable() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout.10
                    public AnonymousClass10() {
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        ExpandableNotificationRow expandableNotificationRow2 = ExpandableNotificationRow.this;
                        expandableNotificationRow2.mGroupExpansionChanging = false;
                        expandableNotificationRow2.updateBackgroundForGroupState();
                    }
                });
            }
        }
    }
}

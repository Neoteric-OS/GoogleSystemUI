package com.android.systemui.statusbar.notification;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConversationNotificationManager$onEntryViewBound$1 {
    public final /* synthetic */ NotificationEntry $entry;
    public final /* synthetic */ ConversationNotificationManager this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.ConversationNotificationManager$onEntryViewBound$1$1, reason: invalid class name */
    public final class AnonymousClass1 implements Runnable {
        public final /* synthetic */ NotificationEntry $entry;
        public final /* synthetic */ boolean $isExpanded;
        public final /* synthetic */ ConversationNotificationManager this$0;

        public AnonymousClass1(ConversationNotificationManager conversationNotificationManager, NotificationEntry notificationEntry, boolean z) {
            this.$isExpanded = z;
            this.this$0 = conversationNotificationManager;
            this.$entry = notificationEntry;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ConversationNotificationManager.onEntryViewBound$updateCount(this.this$0, this.$entry, this.$isExpanded);
        }
    }

    public ConversationNotificationManager$onEntryViewBound$1(NotificationEntry notificationEntry, ConversationNotificationManager conversationNotificationManager) {
        this.$entry = notificationEntry;
        this.this$0 = conversationNotificationManager;
    }

    public final void onExpansionChanged(boolean z) {
        NotificationEntry notificationEntry = this.$entry;
        ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
        ConversationNotificationManager conversationNotificationManager = this.this$0;
        if (expandableNotificationRow == null || !expandableNotificationRow.isShown() || !z) {
            ConversationNotificationManager.onEntryViewBound$updateCount(conversationNotificationManager, notificationEntry, z);
            return;
        }
        ExpandableNotificationRow expandableNotificationRow2 = notificationEntry.row;
        expandableNotificationRow2.mOnIntrinsicHeightReachedRunnable = new AnonymousClass1(conversationNotificationManager, notificationEntry, z);
        if (expandableNotificationRow2.mActualHeight == expandableNotificationRow2.getIntrinsicHeight()) {
            expandableNotificationRow2.mOnIntrinsicHeightReachedRunnable.run();
            expandableNotificationRow2.mOnIntrinsicHeightReachedRunnable = null;
        }
    }
}

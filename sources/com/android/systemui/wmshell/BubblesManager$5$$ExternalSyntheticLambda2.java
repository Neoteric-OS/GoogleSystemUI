package com.android.systemui.wmshell;

import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator;
import com.android.systemui.statusbar.notification.collection.notifcollection.DismissedByUserStats;
import com.android.systemui.statusbar.notification.collection.provider.NotificationVisibilityProviderImpl;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.wmshell.BubblesManager;
import com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda5;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubblesManager$5$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubblesManager.AnonymousClass5 f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ BubblesManager$5$$ExternalSyntheticLambda2(BubblesManager.AnonymousClass5 anonymousClass5, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass5;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ExpandableNotificationRow expandableNotificationRow;
        switch (this.$r8$classId) {
            case 0:
                NotificationEntry entry = ((NotifPipeline) BubblesManager.this.mCommonNotifCollection).mNotifCollection.getEntry((String) this.f$1);
                if (entry != null && entry.mRanking.getImportance() >= 4) {
                    entry.interruption = true;
                    break;
                }
                break;
            case 1:
                BubblesManager.AnonymousClass5 anonymousClass5 = this.f$0;
                String str = (String) this.f$1;
                BubblesManager bubblesManager = BubblesManager.this;
                NotificationEntry entry2 = ((NotifPipeline) bubblesManager.mCommonNotifCollection).mNotifCollection.getEntry(str);
                if (entry2 != null) {
                    Iterator it = bubblesManager.mCallbacks.iterator();
                    while (it.hasNext()) {
                        ((BubbleCoordinator.AnonymousClass3) it.next()).removeNotification(entry2, new DismissedByUserStats(4, ((NotificationVisibilityProviderImpl) bubblesManager.mVisibilityProvider).obtain(entry2)));
                    }
                    break;
                }
                break;
            case 2:
                BubblesManager.AnonymousClass5 anonymousClass52 = this.f$0;
                String str2 = (String) this.f$1;
                Iterator it2 = BubblesManager.this.mCallbacks.iterator();
                while (it2.hasNext()) {
                    BubbleCoordinator.this.mNotifFilter.invalidateList(str2);
                }
                break;
            case 3:
                NotificationEntry entry3 = ((NotifPipeline) BubblesManager.this.mCommonNotifCollection).mNotifCollection.getEntry((String) this.f$1);
                if (entry3 != null && (expandableNotificationRow = entry3.row) != null) {
                    expandableNotificationRow.updateBubbleButton();
                    break;
                }
                break;
            case 4:
                BubblesManager.AnonymousClass5 anonymousClass53 = this.f$0;
                String str3 = (String) this.f$1;
                BubblesManager bubblesManager2 = BubblesManager.this;
                NotificationEntry entry4 = ((NotifPipeline) bubblesManager2.mCommonNotifCollection).mNotifCollection.getEntry(str3);
                if (entry4 != null) {
                    bubblesManager2.onUserChangedBubble(entry4, false);
                    break;
                }
                break;
            default:
                ((BubbleController$$ExternalSyntheticLambda5) this.f$1).accept(Boolean.valueOf(((NotificationShadeWindowControllerImpl) BubblesManager.this.mNotificationShadeWindowController).mCurrentState.shadeOrQsExpanded));
                break;
        }
    }
}

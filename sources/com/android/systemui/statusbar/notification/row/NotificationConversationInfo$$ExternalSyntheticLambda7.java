package com.android.systemui.statusbar.notification.row;

import android.os.RemoteException;
import android.util.Log;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.NotificationConversationInfo;
import com.android.systemui.wmshell.BubblesManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationConversationInfo$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ NotificationConversationInfo$$ExternalSyntheticLambda7(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                NotificationConversationInfo notificationConversationInfo = (NotificationConversationInfo) obj;
                notificationConversationInfo.mOnUserInteractionCallback.onImportanceChanged(notificationConversationInfo.mEntry);
                break;
            default:
                NotificationConversationInfo.UpdateChannelRunnable updateChannelRunnable = (NotificationConversationInfo.UpdateChannelRunnable) obj;
                BubblesManager bubblesManager = (BubblesManager) NotificationConversationInfo.this.mBubblesManagerOptional.get();
                NotificationEntry notificationEntry = NotificationConversationInfo.this.mEntry;
                bubblesManager.getClass();
                if (notificationEntry.mBubbleMetadata != null) {
                    try {
                        bubblesManager.mBarService.onNotificationBubbleChanged(notificationEntry.mKey, true, 2);
                    } catch (RemoteException e) {
                        Log.e("Bubbles", e.getMessage());
                    }
                    bubblesManager.mShadeController.collapseShade(true);
                    ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
                    if (expandableNotificationRow != null) {
                        expandableNotificationRow.updateBubbleButton();
                        break;
                    }
                }
                break;
        }
    }
}

package com.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.wmshell.BubblesManager;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda5;
import com.android.wm.shell.bubbles.BubbleEntry;
import com.android.wm.shell.common.HandlerExecutor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StatusBarNotificationActivityStarter$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ StatusBarNotificationActivityStarter f$0;
    public final /* synthetic */ NotificationEntry f$1;

    public /* synthetic */ StatusBarNotificationActivityStarter$$ExternalSyntheticLambda2(StatusBarNotificationActivityStarter statusBarNotificationActivityStarter, NotificationEntry notificationEntry, int i) {
        this.$r8$classId = i;
        this.f$0 = statusBarNotificationActivityStarter;
        this.f$1 = notificationEntry;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = this.f$0;
                final NotificationEntry notificationEntry = this.f$1;
                statusBarNotificationActivityStarter.mBubblesManagerOptional.ifPresent(new Consumer() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$$ExternalSyntheticLambda6
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        ((BubblesManager) obj).onUserChangedBubble(NotificationEntry.this, !r1.isBubble());
                    }
                });
                ((BaseHeadsUpManager) statusBarNotificationActivityStarter.mHeadsUpManager).removeNotification$1(notificationEntry.mKey, "onNotificationBubbleIconClicked", true);
                break;
            default:
                StatusBarNotificationActivityStarter statusBarNotificationActivityStarter2 = this.f$0;
                NotificationEntry notificationEntry2 = this.f$1;
                BubblesManager bubblesManager = (BubblesManager) statusBarNotificationActivityStarter2.mBubblesManagerOptional.get();
                BubbleEntry notifToBubbleEntry = bubblesManager.notifToBubbleEntry(notificationEntry2);
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) bubblesManager.mBubbles;
                ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda5(bubblesImpl, notifToBubbleEntry, 0));
                statusBarNotificationActivityStarter2.mShadeController.collapseShade();
                break;
        }
    }
}

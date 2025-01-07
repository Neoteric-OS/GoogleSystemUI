package com.android.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.HeadsUpAppearanceController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationStackScrollLayoutController$NotificationListContainerImpl$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ NotificationStackScrollLayoutController.NotificationListContainerImpl f$0;
    public final /* synthetic */ ExpandableNotificationRow f$1;

    public /* synthetic */ NotificationStackScrollLayoutController$NotificationListContainerImpl$$ExternalSyntheticLambda0(NotificationStackScrollLayoutController.NotificationListContainerImpl notificationListContainerImpl, ExpandableNotificationRow expandableNotificationRow) {
        this.f$0 = notificationListContainerImpl;
        this.f$1 = expandableNotificationRow;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        NotificationStackScrollLayoutController.NotificationListContainerImpl notificationListContainerImpl = this.f$0;
        ExpandableNotificationRow expandableNotificationRow = this.f$1;
        notificationListContainerImpl.getClass();
        NotificationEntry notificationEntry = expandableNotificationRow.mEntry;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
        notificationStackScrollLayoutController.mHeadsUpAppearanceController.getClass();
        HeadsUpAppearanceController.updateHeader(notificationEntry);
        notificationStackScrollLayoutController.mHeadsUpAppearanceController.updateHeadsUpAndPulsingRoundness(notificationEntry);
    }
}

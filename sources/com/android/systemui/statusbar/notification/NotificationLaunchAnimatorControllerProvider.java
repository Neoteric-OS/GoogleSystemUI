package com.android.systemui.statusbar.notification;

import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationLaunchAnimationInteractor;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.HeadsUpManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationLaunchAnimatorControllerProvider {
    public final HeadsUpManager headsUpManager;
    public final InteractionJankMonitor jankMonitor;
    public final NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor;
    public final NotificationStackScrollLayoutController.NotificationListContainerImpl notificationListContainer;

    public NotificationLaunchAnimatorControllerProvider(NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor, NotificationStackScrollLayoutController.NotificationListContainerImpl notificationListContainerImpl, HeadsUpManager headsUpManager, InteractionJankMonitor interactionJankMonitor) {
        this.notificationLaunchAnimationInteractor = notificationLaunchAnimationInteractor;
        this.notificationListContainer = notificationListContainerImpl;
        this.headsUpManager = headsUpManager;
        this.jankMonitor = interactionJankMonitor;
    }

    public final NotificationTransitionAnimatorController getAnimatorController(ExpandableNotificationRow expandableNotificationRow) {
        return new NotificationTransitionAnimatorController(this.notificationLaunchAnimationInteractor, this.notificationListContainer, this.headsUpManager, expandableNotificationRow, this.jankMonitor);
    }
}

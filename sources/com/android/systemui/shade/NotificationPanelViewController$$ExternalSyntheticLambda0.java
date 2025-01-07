package com.android.systemui.shade;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationPanelViewController$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ NotificationPanelViewController f$0;

    public /* synthetic */ NotificationPanelViewController$$ExternalSyntheticLambda0(NotificationPanelViewController notificationPanelViewController, int i) {
        this.$r8$classId = i;
        this.f$0 = notificationPanelViewController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        NotificationPanelViewController notificationPanelViewController = this.f$0;
        switch (i) {
            case 0:
                notificationPanelViewController.instantCollapse();
                return;
            case 1:
                notificationPanelViewController.fling(0.0f, notificationPanelViewController.mNextCollapseSpeedUpFactor, false, false);
                return;
            case 2:
                notificationPanelViewController.getClass();
                throw null;
            case 3:
                notificationPanelViewController.mHeadsUpAnimatingAway = false;
                notificationPanelViewController.mNotificationStackScrollLayoutController.mView.setHeadsUpAnimatingAway(false);
                notificationPanelViewController.updateVisibility$6();
                notificationPanelViewController.updateExpansionAndVisibility();
                return;
            case 4:
                if (notificationPanelViewController.mExpandedFraction == 0.0f) {
                    notificationPanelViewController.mView.post(notificationPanelViewController.mHideExpandedRunnable);
                    return;
                }
                return;
            case 5:
                notificationPanelViewController.collapse(1.0f, false);
                return;
            case 6:
                notificationPanelViewController.setListening$1(false);
                return;
            case 7:
                NotificationPanelView notificationPanelView = notificationPanelViewController.mView;
                notificationPanelView.getParent().invalidateChild(notificationPanelView, NotificationPanelViewController.M_DUMMY_DIRTY_RECT);
                return;
            case 8:
                notificationPanelViewController.mLatencyTracker.onActionEnd(0);
                return;
            default:
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) notificationPanelViewController.mNotificationShadeWindowController;
                NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                notificationShadeWindowState.forceWindowCollapsed = false;
                notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
                return;
        }
    }
}

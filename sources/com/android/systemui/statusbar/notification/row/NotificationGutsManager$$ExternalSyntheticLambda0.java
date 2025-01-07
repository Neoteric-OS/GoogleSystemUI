package com.android.systemui.statusbar.notification.row;

import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinator$mGutsListener$1;
import com.android.systemui.statusbar.notification.row.NotificationGuts;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationGutsManager$$ExternalSyntheticLambda0 {
    public final /* synthetic */ NotificationGutsManager f$0;
    public final /* synthetic */ ExpandableNotificationRow f$1;
    public final /* synthetic */ NotificationEntry f$2;

    public /* synthetic */ NotificationGutsManager$$ExternalSyntheticLambda0(NotificationGutsManager notificationGutsManager, ExpandableNotificationRow expandableNotificationRow, NotificationEntry notificationEntry) {
        this.f$0 = notificationGutsManager;
        this.f$1 = expandableNotificationRow;
        this.f$2 = notificationEntry;
    }

    public final void onGutsClosed(NotificationGuts notificationGuts) {
        NotificationGutsManager notificationGutsManager = this.f$0;
        ExpandableNotificationRow expandableNotificationRow = this.f$1;
        expandableNotificationRow.updateContentAccessibilityImportanceForGuts(true);
        expandableNotificationRow.mIsSnoozed = false;
        NotificationGuts.GutsContent gutsContent = notificationGuts.mGutsContent;
        if (!(gutsContent != null ? gutsContent.willBeRemoved() : false)) {
            notificationGutsManager.mListContainer.onHeightChanged(expandableNotificationRow, true ^ notificationGutsManager.mPresenter.mPanelExpansionInteractor.isFullyCollapsed());
        }
        if (notificationGutsManager.mNotificationGutsExposed == notificationGuts) {
            notificationGutsManager.mNotificationGutsExposed = null;
            notificationGutsManager.mGutsMenuItem = null;
        }
        GutsCoordinator$mGutsListener$1 gutsCoordinator$mGutsListener$1 = notificationGutsManager.mGutsListener;
        if (gutsCoordinator$mGutsListener$1 != null) {
            gutsCoordinator$mGutsListener$1.onGutsClose(this.f$2);
        }
        ((HeadsUpManagerPhone) notificationGutsManager.mHeadsUpManager).setGutsShown(expandableNotificationRow.mEntry, false);
    }
}

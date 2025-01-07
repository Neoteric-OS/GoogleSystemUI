package com.android.systemui.statusbar.phone;

import android.view.View;
import android.view.ViewParent;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StatusBarRemoteInputCallback$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ StatusBarRemoteInputCallback$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((View) obj).callOnClick();
                break;
            default:
                final StatusBarRemoteInputCallback statusBarRemoteInputCallback = (StatusBarRemoteInputCallback) obj;
                View view = statusBarRemoteInputCallback.mPendingWorkRemoteInputView;
                if (view != null) {
                    ViewParent parent = view.getParent();
                    while (!(parent instanceof ExpandableNotificationRow)) {
                        if (parent == null) {
                            break;
                        } else {
                            parent = parent.getParent();
                        }
                    }
                    final ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) parent;
                    ViewParent parent2 = expandableNotificationRow.getParent();
                    if (parent2 instanceof NotificationStackScrollLayout) {
                        final NotificationStackScrollLayout notificationStackScrollLayout = (NotificationStackScrollLayout) parent2;
                        expandableNotificationRow.setUserExpanded(true, true);
                        if (expandableNotificationRow.isChildInGroup()) {
                            expandableNotificationRow.mGroupExpansionManager.setGroupExpanded(expandableNotificationRow.mEntry, true);
                        }
                        expandableNotificationRow.notifyHeightChanged(false);
                        expandableNotificationRow.post(new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBarRemoteInputCallback$$ExternalSyntheticLambda3
                            @Override // java.lang.Runnable
                            public final void run() {
                                final StatusBarRemoteInputCallback statusBarRemoteInputCallback2 = StatusBarRemoteInputCallback.this;
                                final NotificationStackScrollLayout notificationStackScrollLayout2 = notificationStackScrollLayout;
                                ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow;
                                statusBarRemoteInputCallback2.getClass();
                                Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBarRemoteInputCallback$$ExternalSyntheticLambda4
                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        StatusBarRemoteInputCallback statusBarRemoteInputCallback3 = StatusBarRemoteInputCallback.this;
                                        NotificationStackScrollLayout notificationStackScrollLayout3 = notificationStackScrollLayout2;
                                        statusBarRemoteInputCallback3.mPendingWorkRemoteInputView.callOnClick();
                                        statusBarRemoteInputCallback3.mPendingWorkRemoteInputView = null;
                                        notificationStackScrollLayout3.mFinishScrollingCallback = null;
                                    }
                                };
                                if (notificationStackScrollLayout2.scrollTo(expandableNotificationRow2)) {
                                    notificationStackScrollLayout2.mFinishScrollingCallback = runnable;
                                } else {
                                    runnable.run();
                                }
                            }
                        });
                        break;
                    }
                }
                break;
        }
    }
}

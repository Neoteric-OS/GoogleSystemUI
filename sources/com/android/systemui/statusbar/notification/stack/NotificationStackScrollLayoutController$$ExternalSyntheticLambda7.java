package com.android.systemui.statusbar.notification.stack;

import com.android.systemui.statusbar.notification.init.NotificationsController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationStackScrollLayoutController$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ NotificationStackScrollLayoutController$$ExternalSyntheticLambda7(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = (NotificationStackScrollLayoutController) obj;
                notificationStackScrollLayoutController.mView.postDelayed(new NotificationStackScrollLayoutController$$ExternalSyntheticLambda7(1, notificationStackScrollLayoutController), 200L);
                break;
            case 1:
                ((NotificationStackScrollLayoutController) obj).mShadeController.animateCollapseShade(0);
                break;
            default:
                ((NotificationsController) obj).resetUserExpandedStates();
                break;
        }
    }
}

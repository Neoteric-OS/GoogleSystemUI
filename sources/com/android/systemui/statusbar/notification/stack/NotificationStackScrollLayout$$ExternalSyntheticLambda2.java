package com.android.systemui.statusbar.notification.stack;

import com.android.internal.jank.InteractionJankMonitor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotificationStackScrollLayout$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ NotificationStackScrollLayout f$0;

    public /* synthetic */ NotificationStackScrollLayout$$ExternalSyntheticLambda2(NotificationStackScrollLayout notificationStackScrollLayout, int i) {
        this.$r8$classId = i;
        this.f$0 = notificationStackScrollLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        NotificationStackScrollLayout notificationStackScrollLayout = this.f$0;
        switch (i) {
            case 0:
                int i2 = NotificationStackScrollLayout.$r8$clinit;
                notificationStackScrollLayout.animateScroll();
                break;
            default:
                notificationStackScrollLayout.mFlingAfterUpEvent = false;
                InteractionJankMonitor.getInstance().end(2);
                notificationStackScrollLayout.mFinishScrollingCallback = null;
                break;
        }
    }
}

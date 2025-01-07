package com.android.systemui.statusbar.phone;

import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StatusBarKeyguardViewManager$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ StatusBarKeyguardViewManager f$0;

    public /* synthetic */ StatusBarKeyguardViewManager$$ExternalSyntheticLambda0(StatusBarKeyguardViewManager statusBarKeyguardViewManager) {
        this.f$0 = statusBarKeyguardViewManager;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.f$0;
        switch (i) {
            case 0:
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) statusBarKeyguardViewManager.mNotificationShadeWindowController;
                NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                notificationShadeWindowState.keyguardFadingAway = false;
                notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
                break;
            default:
                statusBarKeyguardViewManager.reset(true, false);
                break;
        }
    }

    public /* synthetic */ StatusBarKeyguardViewManager$$ExternalSyntheticLambda0(StatusBarKeyguardViewManager statusBarKeyguardViewManager, boolean z) {
        this.f$0 = statusBarKeyguardViewManager;
    }
}

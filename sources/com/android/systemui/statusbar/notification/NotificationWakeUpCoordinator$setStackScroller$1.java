package com.android.systemui.statusbar.notification;

import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationWakeUpCoordinator$setStackScroller$1 implements Runnable {
    public final /* synthetic */ NotificationWakeUpCoordinator this$0;

    public NotificationWakeUpCoordinator$setStackScroller$1(NotificationWakeUpCoordinator notificationWakeUpCoordinator) {
        this.this$0 = notificationWakeUpCoordinator;
    }

    @Override // java.lang.Runnable
    public final void run() {
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.this$0.stackScrollerController;
        if (notificationStackScrollLayoutController == null) {
            notificationStackScrollLayoutController = null;
        }
        boolean isPulseExpanding = notificationStackScrollLayoutController.mView.mAmbientState.isPulseExpanding();
        NotificationWakeUpCoordinator notificationWakeUpCoordinator = this.this$0;
        boolean z = isPulseExpanding != notificationWakeUpCoordinator.pulseExpanding;
        notificationWakeUpCoordinator.pulseExpanding = isPulseExpanding;
        if (z) {
            Iterator it = notificationWakeUpCoordinator.wakeUpListeners.iterator();
            while (it.hasNext()) {
                NotificationWakeUpCoordinator.WakeUpListener wakeUpListener = (NotificationWakeUpCoordinator.WakeUpListener) it.next();
                boolean z2 = this.this$0.pulseExpanding;
                wakeUpListener.getClass();
            }
            NotificationWakeUpCoordinator notificationWakeUpCoordinator2 = this.this$0;
            AuthContainerView$$ExternalSyntheticOutline0.m(notificationWakeUpCoordinator2.pulseExpanding, notificationWakeUpCoordinator2.notifsKeyguardInteractor.repository.isPulseExpanding, null);
        }
    }
}

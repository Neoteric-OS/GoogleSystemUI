package com.android.systemui.statusbar.phone;

import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DozeServiceHost$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ DozeServiceHost f$0;
    public final /* synthetic */ NotificationEntry f$1;

    public /* synthetic */ DozeServiceHost$$ExternalSyntheticLambda1(DozeServiceHost dozeServiceHost, NotificationEntry notificationEntry) {
        this.f$0 = dozeServiceHost;
        this.f$1 = notificationEntry;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DozeServiceHost dozeServiceHost = this.f$0;
        NotificationEntry notificationEntry = this.f$1;
        dozeServiceHost.getClass();
        ((HeadsUpManagerPhone) dozeServiceHost.mHeadsUpManager).removeNotification(notificationEntry.mKey, "fireNotificationPulse", false);
    }
}

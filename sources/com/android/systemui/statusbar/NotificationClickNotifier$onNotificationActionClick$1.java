package com.android.systemui.statusbar;

import android.app.Notification;
import android.os.RemoteException;
import com.android.internal.statusbar.NotificationVisibility;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationClickNotifier$onNotificationActionClick$1 implements Runnable {
    public final /* synthetic */ Notification.Action $action;
    public final /* synthetic */ int $actionIndex;
    public final /* synthetic */ boolean $generatedByAssistant;
    public final /* synthetic */ String $key;
    public final /* synthetic */ NotificationVisibility $visibility;
    public final /* synthetic */ NotificationClickNotifier this$0;

    public NotificationClickNotifier$onNotificationActionClick$1(NotificationClickNotifier notificationClickNotifier, String str, int i, Notification.Action action, NotificationVisibility notificationVisibility, boolean z) {
        this.this$0 = notificationClickNotifier;
        this.$key = str;
        this.$actionIndex = i;
        this.$action = action;
        this.$visibility = notificationVisibility;
        this.$generatedByAssistant = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.this$0.barService.onNotificationActionClick(this.$key, this.$actionIndex, this.$action, this.$visibility, this.$generatedByAssistant);
        } catch (RemoteException unused) {
        }
    }
}

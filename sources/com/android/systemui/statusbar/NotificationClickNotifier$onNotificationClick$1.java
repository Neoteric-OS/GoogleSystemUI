package com.android.systemui.statusbar;

import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationClickNotifier$onNotificationClick$1 implements Runnable {
    public final /* synthetic */ String $key;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ NotificationClickNotifier this$0;

    public /* synthetic */ NotificationClickNotifier$onNotificationClick$1(NotificationClickNotifier notificationClickNotifier, String str, int i) {
        this.$r8$classId = i;
        this.this$0 = notificationClickNotifier;
        this.$key = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                NotificationClickNotifier notificationClickNotifier = this.this$0;
                String str = this.$key;
                Iterator it = notificationClickNotifier.listeners.iterator();
                while (it.hasNext()) {
                    ((NotificationInteractionTracker) it.next()).interactions.put(str, Boolean.TRUE);
                }
                break;
            default:
                NotificationClickNotifier notificationClickNotifier2 = this.this$0;
                String str2 = this.$key;
                Iterator it2 = notificationClickNotifier2.listeners.iterator();
                while (it2.hasNext()) {
                    ((NotificationInteractionTracker) it2.next()).interactions.put(str2, Boolean.TRUE);
                }
                break;
        }
    }
}

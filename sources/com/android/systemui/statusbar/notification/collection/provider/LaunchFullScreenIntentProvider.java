package com.android.systemui.statusbar.notification.collection.provider;

import android.util.Log;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.phone.StatusBarNotificationActivityStarter$$ExternalSyntheticLambda9;
import com.android.systemui.util.ListenerSet;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LaunchFullScreenIntentProvider {
    public final ListenerSet listeners = new ListenerSet();

    public final void launchFullScreenIntent(NotificationEntry notificationEntry) {
        ListenerSet listenerSet = this.listeners;
        if (listenerSet.listeners.isEmpty()) {
            Log.wtf("LaunchFullScreenIntentProvider", "no listeners found when launchFullScreenIntent requested");
        }
        Iterator it = listenerSet.listeners.iterator();
        while (it.hasNext()) {
            ((StatusBarNotificationActivityStarter$$ExternalSyntheticLambda9) it.next()).f$0.launchFullScreenIntent(notificationEntry);
        }
    }
}

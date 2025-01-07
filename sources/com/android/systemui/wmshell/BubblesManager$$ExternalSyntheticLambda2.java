package com.android.systemui.wmshell;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator;
import com.android.systemui.statusbar.notification.collection.notifcollection.DismissedByUserStats;
import com.android.systemui.statusbar.notification.collection.provider.NotificationVisibilityProviderImpl;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.IntConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubblesManager$$ExternalSyntheticLambda2 implements IntConsumer {
    public final /* synthetic */ BubblesManager f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ NotificationEntry f$2;

    public /* synthetic */ BubblesManager$$ExternalSyntheticLambda2(BubblesManager bubblesManager, List list, NotificationEntry notificationEntry) {
        this.f$0 = bubblesManager;
        this.f$1 = list;
        this.f$2 = notificationEntry;
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i) {
        BubblesManager bubblesManager = this.f$0;
        List list = this.f$1;
        NotificationEntry notificationEntry = this.f$2;
        NotificationVisibilityProvider notificationVisibilityProvider = bubblesManager.mVisibilityProvider;
        if (i < 0) {
            Iterator it = bubblesManager.mCallbacks.iterator();
            while (it.hasNext()) {
                ((BubbleCoordinator.AnonymousClass3) it.next()).removeNotification(notificationEntry, new DismissedByUserStats(4, ((NotificationVisibilityProviderImpl) notificationVisibilityProvider).obtain(notificationEntry)));
            }
        } else {
            Iterator it2 = bubblesManager.mCallbacks.iterator();
            while (it2.hasNext()) {
                ArrayList arrayList = (ArrayList) list;
                ((BubbleCoordinator.AnonymousClass3) it2.next()).removeNotification((NotificationEntry) arrayList.get(i), new DismissedByUserStats(4, ((NotificationVisibilityProviderImpl) notificationVisibilityProvider).obtain((NotificationEntry) arrayList.get(i))));
            }
        }
    }
}

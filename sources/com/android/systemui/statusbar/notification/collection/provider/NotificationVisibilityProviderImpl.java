package com.android.systemui.statusbar.notification.collection.provider;

import com.android.internal.statusbar.NotificationVisibility;
import com.android.systemui.statusbar.notification.collection.NotifLiveDataStoreImpl;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.logging.NotificationLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationVisibilityProviderImpl implements NotificationVisibilityProvider {
    public final CommonNotifCollection notifCollection;
    public final NotifLiveDataStoreImpl notifDataStore;

    public NotificationVisibilityProviderImpl(ActiveNotificationsInteractor activeNotificationsInteractor, NotifLiveDataStoreImpl notifLiveDataStoreImpl, CommonNotifCollection commonNotifCollection) {
        this.notifDataStore = notifLiveDataStoreImpl;
        this.notifCollection = commonNotifCollection;
    }

    public final NotificationVisibility obtain(NotificationEntry notificationEntry) {
        return NotificationVisibility.obtain(notificationEntry.mKey, notificationEntry.mRanking.getRank(), ((Number) this.notifDataStore.activeNotifCount.atomicValue.get()).intValue(), notificationEntry.row != null, NotificationLogger.getNotificationLocation(notificationEntry));
    }
}

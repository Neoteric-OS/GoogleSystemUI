package com.android.systemui.statusbar.notification.row;

import android.util.ArrayMap;
import android.util.SparseArray;
import android.widget.RemoteViews;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifRemoteViewCacheImpl implements NotifRemoteViewCache {
    public final AnonymousClass1 mCollectionListener;
    public final Map mNotifCachedContentViews = new ArrayMap();

    public NotifRemoteViewCacheImpl(CommonNotifCollection commonNotifCollection) {
        ((NotifPipeline) commonNotifCollection).addCollectionListener(new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.row.NotifRemoteViewCacheImpl.1
            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryCleanUp(NotificationEntry notificationEntry) {
                ((ArrayMap) NotifRemoteViewCacheImpl.this.mNotifCachedContentViews).remove(notificationEntry);
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryInit(NotificationEntry notificationEntry) {
                ((ArrayMap) NotifRemoteViewCacheImpl.this.mNotifCachedContentViews).put(notificationEntry, new SparseArray());
            }
        });
    }

    public final RemoteViews getCachedView(NotificationEntry notificationEntry, int i) {
        SparseArray sparseArray = (SparseArray) ((ArrayMap) this.mNotifCachedContentViews).get(notificationEntry);
        if (sparseArray == null) {
            return null;
        }
        return (RemoteViews) sparseArray.get(i);
    }

    public final boolean hasCachedView(NotificationEntry notificationEntry, int i) {
        return getCachedView(notificationEntry, i) != null;
    }

    public final void putCachedView(NotificationEntry notificationEntry, int i, RemoteViews remoteViews) {
        SparseArray sparseArray = (SparseArray) ((ArrayMap) this.mNotifCachedContentViews).get(notificationEntry);
        if (sparseArray == null) {
            return;
        }
        sparseArray.put(i, remoteViews);
    }

    public final void removeCachedView(NotificationEntry notificationEntry, int i) {
        SparseArray sparseArray = (SparseArray) ((ArrayMap) this.mNotifCachedContentViews).get(notificationEntry);
        if (sparseArray == null) {
            return;
        }
        sparseArray.remove(i);
    }
}

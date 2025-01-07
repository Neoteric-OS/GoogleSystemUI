package com.android.systemui.statusbar.notification.collection;

import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.collection.notifcollection.BindEntryEvent;
import com.android.systemui.statusbar.notification.collection.notifcollection.EntryUpdatedEvent;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender;
import com.android.systemui.util.Assert;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class NotifCollection$$ExternalSyntheticLambda12 {
    public final /* synthetic */ NotifCollection f$0;

    public /* synthetic */ NotifCollection$$ExternalSyntheticLambda12(NotifCollection notifCollection) {
        this.f$0 = notifCollection;
    }

    public void onEndLifetimeExtension(NotificationEntry notificationEntry, NotifLifetimeExtender notifLifetimeExtender) {
        NotifCollection notifCollection = this.f$0;
        notifCollection.getClass();
        Assert.isMainThread();
        if (notifCollection.mAttached) {
            notifCollection.checkForReentrantCall();
            NotificationEntry entry = notifCollection.getEntry(notificationEntry.mKey);
            String logKey = NotificationUtils.logKey(notificationEntry);
            String str = entry == null ? "null" : notificationEntry == entry ? "same" : "different";
            NotifCollectionLogger notifCollectionLogger = notifCollection.mLogger;
            if (notificationEntry != entry) {
                notifCollectionLogger.logEntryBeingExtendedNotInCollection(notificationEntry, notifLifetimeExtender, str);
            }
            if (!notificationEntry.mLifetimeExtenders.remove(notifLifetimeExtender)) {
                IllegalStateException illegalStateException = new IllegalStateException(ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("Cannot end lifetime extension for extender \"", notifLifetimeExtender.getName(), "\" of entry ", logKey, " (collection entry is "), str, ")"));
                notifCollection.mEulogizer.record(illegalStateException);
                throw illegalStateException;
            }
            notifCollectionLogger.logLifetimeExtensionEnded(notificationEntry, notifLifetimeExtender, ((ArrayList) notificationEntry.mLifetimeExtenders).size());
            if (((ArrayList) notificationEntry.mLifetimeExtenders).size() <= 0 && notifCollection.tryRemoveNotification(notificationEntry)) {
                notifCollection.dispatchEventsAndRebuildList("onEndLifetimeExtension");
            }
        }
    }

    public void onInternalNotificationUpdate(final StatusBarNotification statusBarNotification, final String str) {
        final NotifCollection notifCollection = this.f$0;
        notifCollection.getClass();
        notifCollection.mMainHandler.post(new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                NotifCollection notifCollection2 = NotifCollection.this;
                StatusBarNotification statusBarNotification2 = statusBarNotification;
                String str2 = str;
                notifCollection2.getClass();
                Assert.isMainThread();
                notifCollection2.checkForReentrantCall();
                NotificationEntry notificationEntry = (NotificationEntry) ((ArrayMap) notifCollection2.mNotificationSet).get(statusBarNotification2.getKey());
                NotifCollectionLogger notifCollectionLogger = notifCollection2.mLogger;
                if (notificationEntry == null) {
                    notifCollectionLogger.logNotifInternalUpdateFailed(statusBarNotification2, str2);
                    return;
                }
                notifCollectionLogger.logNotifInternalUpdate(notificationEntry, str2);
                notificationEntry.setSbn(statusBarNotification2);
                notifCollection2.mEventQueue.add(new BindEntryEvent(notificationEntry, statusBarNotification2));
                notifCollectionLogger.logNotifUpdated(notificationEntry);
                notifCollection2.mEventQueue.add(new EntryUpdatedEvent(notificationEntry, false));
                notifCollection2.dispatchEventsAndRebuildList("updateNotificationInternally");
            }
        });
    }
}

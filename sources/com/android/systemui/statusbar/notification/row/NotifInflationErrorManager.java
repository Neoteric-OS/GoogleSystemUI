package com.android.systemui.statusbar.notification.row;

import android.os.RemoteException;
import android.service.notification.StatusBarNotification;
import androidx.collection.ArraySet;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifInflationErrorManager {
    public ArraySet mErroredNotifs;
    public List mListeners;

    public final void clearInflationError(NotificationEntry notificationEntry) {
        if (this.mErroredNotifs.contains(notificationEntry.mKey)) {
            this.mErroredNotifs.remove(notificationEntry.mKey);
            for (int i = 0; i < ((ArrayList) this.mListeners).size(); i++) {
                PreparationCoordinator.this.mNotifInflationErrorFilter.invalidateList("onNotifInflationErrorCleared for " + NotificationUtils.logKey(notificationEntry));
            }
        }
    }

    public final void setInflationError(NotificationEntry notificationEntry, Exception exc) {
        this.mErroredNotifs.add(notificationEntry.mKey);
        for (int i = 0; i < ((ArrayList) this.mListeners).size(); i++) {
            PreparationCoordinator preparationCoordinator = PreparationCoordinator.this;
            preparationCoordinator.mViewBarn.rowMap.remove(notificationEntry.mKey);
            preparationCoordinator.mInflationStates.put(notificationEntry, -1);
            try {
                StatusBarNotification statusBarNotification = notificationEntry.mSbn;
                preparationCoordinator.mStatusBarService.onNotificationError(statusBarNotification.getPackageName(), statusBarNotification.getTag(), statusBarNotification.getId(), statusBarNotification.getUid(), statusBarNotification.getInitialPid(), exc.getMessage(), statusBarNotification.getUser().getIdentifier());
            } catch (RemoteException unused) {
            }
            preparationCoordinator.mNotifInflationErrorFilter.invalidateList("onNotifInflationError for " + NotificationUtils.logKey(notificationEntry));
        }
    }
}

package com.android.systemui.statusbar.notification.logging;

import android.service.notification.StatusBarNotification;
import com.android.app.viewcapture.data.ViewNode;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.logging.nano.Notifications$Notification;
import com.android.systemui.statusbar.notification.logging.nano.Notifications$NotificationList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationPanelLoggerImpl {
    public static Notifications$NotificationList toNotificationProto(List list) {
        int i;
        Notifications$NotificationList notifications$NotificationList = new Notifications$NotificationList();
        if (list == null) {
            return notifications$NotificationList;
        }
        Notifications$Notification[] notifications$NotificationArr = new Notifications$Notification[list.size()];
        Iterator it = list.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            NotificationEntry notificationEntry = (NotificationEntry) it.next();
            StatusBarNotification statusBarNotification = notificationEntry.mSbn;
            if (statusBarNotification != null) {
                Notifications$Notification notifications$Notification = new Notifications$Notification();
                notifications$Notification.uid = statusBarNotification.getUid();
                notifications$Notification.packageName = statusBarNotification.getPackageName();
                if (statusBarNotification.getInstanceId() != null) {
                    notifications$Notification.instanceId = statusBarNotification.getInstanceId().getId();
                }
                if (statusBarNotification.getNotification() != null) {
                    notifications$Notification.isGroupSummary = statusBarNotification.getNotification().isGroupSummary();
                }
                switch (notificationEntry.mBucket) {
                    case 1:
                        i = 2;
                        break;
                    case 2:
                        i = 1;
                        break;
                    case 3:
                        i = 6;
                        break;
                    case 4:
                    case 7:
                        i = 3;
                        break;
                    case 5:
                        i = 4;
                        break;
                    case 6:
                        i = 5;
                        break;
                    case 8:
                    case 9:
                    default:
                        i = 0;
                        break;
                    case 10:
                        i = 10;
                        break;
                    case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                        i = 11;
                        break;
                    case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                        i = 12;
                        break;
                    case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                        i = 13;
                        break;
                }
                notifications$Notification.section = i;
                notifications$NotificationArr[i2] = notifications$Notification;
            }
            i2++;
        }
        notifications$NotificationList.notifications = notifications$NotificationArr;
        return notifications$NotificationList;
    }
}

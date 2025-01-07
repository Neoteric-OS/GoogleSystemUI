package com.android.systemui.people;

import android.app.Notification;
import android.app.Person;
import android.os.Bundle;
import android.os.Parcelable;
import android.service.notification.StatusBarNotification;
import com.android.internal.util.ArrayUtils;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NotificationHelper {
    public static final AnonymousClass1 notificationEntryComparator = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.people.NotificationHelper$1, reason: invalid class name */
    public final class AnonymousClass1 implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            Notification notification = ((NotificationEntry) obj).mSbn.getNotification();
            Notification notification2 = ((NotificationEntry) obj2).mSbn.getNotification();
            boolean isMissedCall = NotificationHelper.isMissedCall(notification);
            boolean isMissedCall2 = NotificationHelper.isMissedCall(notification2);
            if (isMissedCall && !isMissedCall2) {
                return -1;
            }
            if (isMissedCall || !isMissedCall2) {
                List messagingStyleMessages = NotificationHelper.getMessagingStyleMessages(notification);
                List messagingStyleMessages2 = NotificationHelper.getMessagingStyleMessages(notification2);
                if (messagingStyleMessages != null && messagingStyleMessages2 != null) {
                    return (int) (((Notification.MessagingStyle.Message) messagingStyleMessages2.get(0)).getTimestamp() - ((Notification.MessagingStyle.Message) messagingStyleMessages.get(0)).getTimestamp());
                }
                if (messagingStyleMessages != null) {
                    if (messagingStyleMessages2 == null) {
                        return -1;
                    }
                    return (int) (notification2.getWhen() - notification.getWhen());
                }
            }
            return 1;
        }
    }

    public static String getContactUri(StatusBarNotification statusBarNotification) {
        Person senderPerson;
        String uri;
        ArrayList parcelableArrayList = statusBarNotification.getNotification().extras.getParcelableArrayList("android.people.list");
        if (parcelableArrayList != null && parcelableArrayList.get(0) != null && (uri = ((Person) parcelableArrayList.get(0)).getUri()) != null && !uri.isEmpty()) {
            return uri;
        }
        List messagingStyleMessages = getMessagingStyleMessages(statusBarNotification.getNotification());
        if (messagingStyleMessages == null || messagingStyleMessages.isEmpty() || (senderPerson = ((Notification.MessagingStyle.Message) messagingStyleMessages.get(0)).getSenderPerson()) == null || senderPerson.getUri() == null || senderPerson.getUri().isEmpty()) {
            return null;
        }
        return senderPerson.getUri();
    }

    public static List getMessagingStyleMessages(Notification notification) {
        Bundle bundle;
        if (notification != null && notification.isStyle(Notification.MessagingStyle.class) && (bundle = notification.extras) != null) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("android.messages");
            if (!ArrayUtils.isEmpty(parcelableArray)) {
                List<Notification.MessagingStyle.Message> messagesFromBundleArray = Notification.MessagingStyle.Message.getMessagesFromBundleArray(parcelableArray);
                messagesFromBundleArray.sort(Collections.reverseOrder(Comparator.comparing(new NotificationHelper$$ExternalSyntheticLambda1())));
                return messagesFromBundleArray;
            }
        }
        return null;
    }

    public static boolean isMissedCall(Notification notification) {
        return notification != null && Objects.equals(notification.category, "missed_call");
    }

    public static boolean isMissedCallOrHasContent(NotificationEntry notificationEntry) {
        List messagingStyleMessages;
        return ((notificationEntry == null || notificationEntry.mSbn.getNotification() == null || !isMissedCall(notificationEntry.mSbn.getNotification())) && (notificationEntry == null || (messagingStyleMessages = getMessagingStyleMessages(notificationEntry.mSbn.getNotification())) == null || messagingStyleMessages.isEmpty())) ? false : true;
    }
}

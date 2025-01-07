package com.android.systemui.statusbar.notification.collection;

import java.util.Comparator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ShadeListBuilder$$ExternalSyntheticLambda2 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        NotificationEntry notificationEntry = (NotificationEntry) obj;
        NotificationEntry notificationEntry2 = (NotificationEntry) obj2;
        notificationEntry.getClass();
        int rank = notificationEntry.mRanking.getRank();
        notificationEntry2.getClass();
        int compare = Integer.compare(rank, notificationEntry2.mRanking.getRank());
        return compare != 0 ? compare : Long.compare(notificationEntry.mSbn.getNotification().getWhen(), notificationEntry2.mSbn.getNotification().getWhen()) * (-1);
    }
}

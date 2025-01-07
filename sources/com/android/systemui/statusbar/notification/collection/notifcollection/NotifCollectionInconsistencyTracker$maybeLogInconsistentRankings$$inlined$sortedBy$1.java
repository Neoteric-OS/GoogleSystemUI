package com.android.systemui.statusbar.notification.collection.notifcollection;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifCollectionInconsistencyTracker$maybeLogInconsistentRankings$$inlined$sortedBy$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        return ComparisonsKt___ComparisonsJvmKt.compareValues(((NotificationEntry) obj).mKey, ((NotificationEntry) obj2).mKey);
    }
}

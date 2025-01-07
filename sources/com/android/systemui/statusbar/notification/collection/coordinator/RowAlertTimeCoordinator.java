package com.android.systemui.statusbar.notification.collection.coordinator;

import android.util.ArrayMap;
import com.android.systemui.statusbar.notification.collection.GroupEntry;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderEntryListener;
import com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeFinalizeFilterListener;
import com.android.systemui.statusbar.notification.collection.render.NotifViewController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRowController;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.FilteringSequence$iterator$1;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RowAlertTimeCoordinator implements Coordinator {
    public final ArrayMap latestAlertTimeBySummary = new ArrayMap();

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        notifPipeline.addOnBeforeFinalizeFilterListener(new OnBeforeFinalizeFilterListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.RowAlertTimeCoordinator$attach$1
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnBeforeFinalizeFilterListener
            public final void onBeforeFinalizeFilter(List list) {
                Long l;
                RowAlertTimeCoordinator rowAlertTimeCoordinator = RowAlertTimeCoordinator.this;
                rowAlertTimeCoordinator.latestAlertTimeBySummary.clear();
                FilteringSequence$iterator$1 filteringSequence$iterator$1 = new FilteringSequence$iterator$1(SequencesKt.filter(new CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(list), new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.RowAlertTimeCoordinator$onBeforeFinalizeFilterListener$$inlined$filterIsInstance$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(obj instanceof GroupEntry);
                    }
                }));
                while (filteringSequence$iterator$1.hasNext()) {
                    GroupEntry groupEntry = (GroupEntry) filteringSequence$iterator$1.next();
                    NotificationEntry notificationEntry = groupEntry.mSummary;
                    if (notificationEntry == null) {
                        throw new IllegalStateException("Required value was null.");
                    }
                    ArrayMap arrayMap = rowAlertTimeCoordinator.latestAlertTimeBySummary;
                    Iterator it = groupEntry.mUnmodifiableChildren.iterator();
                    if (it.hasNext()) {
                        Long valueOf = Long.valueOf(((NotificationEntry) it.next()).mRanking.getLastAudiblyAlertedMillis());
                        while (it.hasNext()) {
                            Long valueOf2 = Long.valueOf(((NotificationEntry) it.next()).mRanking.getLastAudiblyAlertedMillis());
                            if (valueOf.compareTo(valueOf2) < 0) {
                                valueOf = valueOf2;
                            }
                        }
                        l = valueOf;
                    } else {
                        l = null;
                    }
                    long longValue = l != null ? l.longValue() : 0L;
                    NotificationEntry notificationEntry2 = groupEntry.mSummary;
                    if (notificationEntry2 == null) {
                        throw new IllegalStateException("Required value was null.");
                    }
                    arrayMap.put(notificationEntry, Long.valueOf(Math.max(longValue, notificationEntry2.mRanking.getLastAudiblyAlertedMillis())));
                }
            }
        });
        notifPipeline.mRenderStageManager.onAfterRenderEntryListeners.add(new OnAfterRenderEntryListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.RowAlertTimeCoordinator$attach$2
            @Override // com.android.systemui.statusbar.notification.collection.listbuilder.OnAfterRenderEntryListener
            public final void onAfterRenderEntry(NotificationEntry notificationEntry, NotifViewController notifViewController) {
                Long l = (Long) RowAlertTimeCoordinator.this.latestAlertTimeBySummary.get(notificationEntry);
                if (l == null) {
                    l = Long.valueOf(notificationEntry.mRanking.getLastAudiblyAlertedMillis());
                }
                long longValue = l.longValue();
                ExpandableNotificationRow expandableNotificationRow = ((ExpandableNotificationRowController) notifViewController).mView;
                expandableNotificationRow.getClass();
                long currentTimeMillis = System.currentTimeMillis() - longValue;
                long j = ExpandableNotificationRow.RECENTLY_ALERTED_THRESHOLD_MS;
                boolean z = currentTimeMillis < j;
                expandableNotificationRow.applyAudiblyAlertedRecently(z);
                expandableNotificationRow.removeCallbacks(expandableNotificationRow.mExpireRecentlyAlertedFlag);
                if (z) {
                    expandableNotificationRow.postDelayed(expandableNotificationRow.mExpireRecentlyAlertedFlag, j - currentTimeMillis);
                }
            }
        });
    }
}

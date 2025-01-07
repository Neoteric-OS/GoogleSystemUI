package com.android.systemui.statusbar.notification.collection.notifcollection;

import android.service.notification.NotificationListenerService;
import android.util.ArrayMap;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda4;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptySet;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifCollectionInconsistencyTracker {
    public boolean attached;
    public NotifCollection$$ExternalSyntheticLambda4 coalescedKeySetAccessor;
    public NotifCollection$$ExternalSyntheticLambda4 collectedKeySetAccessor;
    public final NotifCollectionLogger logger;
    public Set missingNotifications;
    public Set notificationsWithoutRankings;

    public NotifCollectionInconsistencyTracker(NotifCollectionLogger notifCollectionLogger) {
        this.logger = notifCollectionLogger;
        EmptySet emptySet = EmptySet.INSTANCE;
        this.notificationsWithoutRankings = emptySet;
        this.missingNotifications = emptySet;
    }

    public final void logNewMissingNotifications(NotificationListenerService.RankingMap rankingMap) {
        NotifCollection$$ExternalSyntheticLambda4 notifCollection$$ExternalSyntheticLambda4 = this.collectedKeySetAccessor;
        if (notifCollection$$ExternalSyntheticLambda4 == null) {
            notifCollection$$ExternalSyntheticLambda4 = null;
        }
        final Set keySet = ((ArrayMap) ((Map) notifCollection$$ExternalSyntheticLambda4.f$0)).keySet();
        NotifCollection$$ExternalSyntheticLambda4 notifCollection$$ExternalSyntheticLambda42 = this.coalescedKeySetAccessor;
        final Set set = (Set) (notifCollection$$ExternalSyntheticLambda42 != null ? notifCollection$$ExternalSyntheticLambda42 : null).invoke();
        Set set2 = SequencesKt.toSet(SequencesKt.filter(SequencesKt.filter(ArraysKt.asSequence(rankingMap.getOrderedKeys()), new Function1() { // from class: com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionInconsistencyTracker$logNewMissingNotifications$newMissingNotifications$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(!keySet.contains((String) obj));
            }
        }), new Function1() { // from class: com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionInconsistencyTracker$logNewMissingNotifications$newMissingNotifications$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(!set.contains((String) obj));
            }
        }));
        maybeLogMissingNotifications(this.missingNotifications, set2);
        this.missingNotifications = set2;
    }

    public final void maybeLogInconsistentRankings(Set set, Map map, NotificationListenerService.RankingMap rankingMap) {
        if ((set.isEmpty() && map.isEmpty()) || set.equals(map.keySet())) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = set.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String str = (String) it.next();
            String str2 = ArraysKt.contains(rankingMap.getOrderedKeys(), str) ? !map.containsKey(str) ? str : null : null;
            if (str2 != null) {
                arrayList.add(str2);
            }
        }
        List sorted = CollectionsKt.sorted(arrayList);
        boolean isEmpty = sorted.isEmpty();
        LogBuffer logBuffer = this.logger.buffer;
        if (!isEmpty) {
            int size = map.size();
            LogMessage obtain = logBuffer.obtain("NotifCollection", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger$logRecoveredRankings$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return "Ranking update now contains rankings for " + logMessage.getInt1() + " previously inconsistent entries: " + logMessage.getStr1();
                }
            }, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.int1 = size;
            logMessageImpl.int1 = sorted.size();
            logMessageImpl.str1 = CollectionsKt.joinToString$default(sorted, null, null, null, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger$logRecoveredRankings$1$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String logKey = NotificationUtils.logKey((String) obj);
                    return logKey != null ? logKey : "null";
                }
            }, 31);
            logBuffer.commit(obtain);
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            String str3 = (String) entry.getKey();
            NotificationEntry notificationEntry = (NotificationEntry) entry.getValue();
            if (set.contains(str3)) {
                notificationEntry = null;
            }
            if (notificationEntry != null) {
                arrayList2.add(notificationEntry);
            }
        }
        List sortedWith = CollectionsKt.sortedWith(arrayList2, new NotifCollectionInconsistencyTracker$maybeLogInconsistentRankings$$inlined$sortedBy$1());
        if (sortedWith.isEmpty()) {
            return;
        }
        int size2 = map.size();
        LogMessage obtain2 = logBuffer.obtain("NotifCollection", LogLevel.WARNING, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger$logMissingRankings$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                int int1 = logMessage.getInt1();
                int int2 = logMessage.getInt2();
                String str1 = logMessage.getStr1();
                StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(int1, int2, "Ranking update is missing ranking for ", " entries (", " new): ");
                m.append(str1);
                return m.toString();
            }
        }, null);
        LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
        logMessageImpl2.int1 = size2;
        logMessageImpl2.int2 = sortedWith.size();
        logMessageImpl2.str1 = CollectionsKt.joinToString$default(sortedWith, null, null, null, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger$logMissingRankings$1$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String logKey = NotificationUtilsKt.getLogKey((NotificationEntry) obj);
                return logKey != null ? logKey : "null";
            }
        }, 31);
        logBuffer.commit(obtain2);
        LogMessage obtain3 = logBuffer.obtain("NotifCollection", LogLevel.DEBUG, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger$logMissingRankings$4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Ranking map contents: ", ((LogMessage) obj).getStr1());
            }
        }, null);
        String[] orderedKeys = rankingMap.getOrderedKeys();
        ArrayList arrayList3 = new ArrayList(orderedKeys.length);
        for (String str4 : orderedKeys) {
            String logKey = NotificationUtils.logKey(str4);
            if (logKey == null) {
                logKey = "null";
            }
            arrayList3.add(logKey);
        }
        ((LogMessageImpl) obtain3).str1 = arrayList3.toString();
        logBuffer.commit(obtain3);
    }

    public final void maybeLogMissingNotifications(Set set, Set set2) {
        if ((set.isEmpty() && set2.isEmpty()) || set.equals(set2)) {
            return;
        }
        List sorted = CollectionsKt.sorted(SetsKt.minus(set, (Iterable) set2));
        boolean isEmpty = sorted.isEmpty();
        LogBuffer logBuffer = this.logger.buffer;
        if (!isEmpty) {
            int size = set2.size();
            LogMessage obtain = logBuffer.obtain("NotifCollection", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger$logFoundNotifications$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    int int1 = logMessage.getInt1();
                    int int2 = logMessage.getInt2();
                    String str1 = logMessage.getStr1();
                    StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(int1, int2, "Collection missing ", " entries in ranking update. Just found ", ": ");
                    m.append(str1);
                    return m.toString();
                }
            }, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.int1 = size;
            logMessageImpl.int2 = sorted.size();
            logMessageImpl.str1 = CollectionsKt.joinToString$default(sorted, null, null, null, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger$logFoundNotifications$1$1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    String logKey = NotificationUtils.logKey((String) obj);
                    return logKey != null ? logKey : "null";
                }
            }, 31);
            logBuffer.commit(obtain);
        }
        List sorted2 = CollectionsKt.sorted(SetsKt.minus(set2, (Iterable) set));
        if (sorted2.isEmpty()) {
            return;
        }
        int size2 = set2.size();
        LogMessage obtain2 = logBuffer.obtain("NotifCollection", LogLevel.WARNING, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger$logMissingNotifications$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                int int1 = logMessage.getInt1();
                int int2 = logMessage.getInt2();
                String str1 = logMessage.getStr1();
                StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(int1, int2, "Collection missing ", " entries in ranking update. Just lost ", ": ");
                m.append(str1);
                return m.toString();
            }
        }, null);
        LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
        logMessageImpl2.int1 = size2;
        logMessageImpl2.int2 = sorted2.size();
        logMessageImpl2.str1 = CollectionsKt.joinToString$default(sorted2, null, null, null, new Function1() { // from class: com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger$logMissingNotifications$1$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                String logKey = NotificationUtils.logKey((String) obj);
                return logKey != null ? logKey : "null";
            }
        }, 31);
        logBuffer.commit(obtain2);
    }
}

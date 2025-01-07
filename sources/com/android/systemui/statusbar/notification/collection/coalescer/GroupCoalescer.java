package com.android.systemui.statusbar.notification.collection.coalescer;

import android.app.NotificationChannel;
import android.os.UserHandle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import android.util.ArrayMap;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.PipelineDumpable;
import com.android.systemui.statusbar.notification.collection.PipelineDumper;
import com.android.systemui.util.Assert;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GroupCoalescer implements Dumpable, PipelineDumpable {
    public final SystemClock mClock;
    public NotifCollection.AnonymousClass1 mHandler;
    public final GroupCoalescerLogger mLogger;
    public final DelayableExecutor mMainExecutor;
    public final Map mCoalescedEvents = new ArrayMap();
    public final Map mBatches = new ArrayMap();
    public final AnonymousClass1 mListener = new NotificationListener.NotificationHandler() { // from class: com.android.systemui.statusbar.notification.collection.coalescer.GroupCoalescer.1
        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationChannelModified(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) {
            GroupCoalescer.this.mHandler.onNotificationChannelModified(str, userHandle, notificationChannel, i);
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
            final GroupCoalescer groupCoalescer = GroupCoalescer.this;
            GroupCoalescer.m876$$Nest$mmaybeEmitBatch(groupCoalescer, statusBarNotification);
            GroupCoalescer.m875$$Nest$mapplyRanking(groupCoalescer, rankingMap);
            if (((ArrayMap) groupCoalescer.mCoalescedEvents).containsKey(statusBarNotification.getKey())) {
                throw new IllegalStateException("Notification has already been coalesced: " + statusBarNotification.getKey());
            }
            if (!statusBarNotification.isGroup()) {
                groupCoalescer.mHandler.onNotificationPosted(statusBarNotification, rankingMap);
                return;
            }
            String groupKey = statusBarNotification.getGroupKey();
            final EventBatch eventBatch = (EventBatch) ((ArrayMap) groupCoalescer.mBatches).get(groupKey);
            if (eventBatch == null) {
                ((SystemClockImpl) groupCoalescer.mClock).getClass();
                eventBatch = new EventBatch(android.os.SystemClock.uptimeMillis(), groupKey);
                ((ArrayMap) groupCoalescer.mBatches).put(groupKey, eventBatch);
            }
            String key = statusBarNotification.getKey();
            int size = ((ArrayList) eventBatch.mMembers).size();
            String key2 = statusBarNotification.getKey();
            NotificationListenerService.Ranking ranking = new NotificationListenerService.Ranking();
            if (!rankingMap.getRanking(key2, ranking)) {
                throw new IllegalArgumentException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Ranking map does not contain key ", key2));
            }
            CoalescedEvent coalescedEvent = new CoalescedEvent(key, size, statusBarNotification, ranking, eventBatch);
            ((ArrayMap) groupCoalescer.mCoalescedEvents).put(key, coalescedEvent);
            eventBatch.mMembers.add(coalescedEvent);
            ExecutorImpl.ExecutionToken executionToken = eventBatch.mCancelShortTimeout;
            if (executionToken != null) {
                executionToken.run();
            }
            eventBatch.mCancelShortTimeout = groupCoalescer.mMainExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.coalescer.GroupCoalescer$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    GroupCoalescer groupCoalescer2 = GroupCoalescer.this;
                    EventBatch eventBatch2 = eventBatch;
                    groupCoalescer2.getClass();
                    eventBatch2.mCancelShortTimeout = null;
                    groupCoalescer2.emitBatch(eventBatch2);
                }
            }, groupCoalescer.mMinGroupLingerDuration);
            String key3 = statusBarNotification.getKey();
            GroupCoalescerLogger groupCoalescerLogger = groupCoalescer.mLogger;
            groupCoalescerLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            GroupCoalescerLogger$logEventCoalesced$2 groupCoalescerLogger$logEventCoalesced$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coalescer.GroupCoalescerLogger$logEventCoalesced$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("COALESCED: ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = groupCoalescerLogger.buffer;
            LogMessage obtain = logBuffer.obtain("GroupCoalescer", logLevel, groupCoalescerLogger$logEventCoalesced$2, null);
            ((LogMessageImpl) obtain).str1 = key3;
            logBuffer.commit(obtain);
            groupCoalescer.mHandler.onNotificationRankingUpdate(rankingMap);
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationRankingUpdate(NotificationListenerService.RankingMap rankingMap) {
            GroupCoalescer groupCoalescer = GroupCoalescer.this;
            GroupCoalescer.m875$$Nest$mapplyRanking(groupCoalescer, rankingMap);
            groupCoalescer.mHandler.onNotificationRankingUpdate(rankingMap);
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationRemoved(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap, int i) {
            GroupCoalescer groupCoalescer = GroupCoalescer.this;
            GroupCoalescer.m876$$Nest$mmaybeEmitBatch(groupCoalescer, statusBarNotification);
            GroupCoalescer.m875$$Nest$mapplyRanking(groupCoalescer, rankingMap);
            groupCoalescer.mHandler.onNotificationRemoved(statusBarNotification, rankingMap, i);
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationsInitialized() {
            GroupCoalescer.this.mHandler.onNotificationsInitialized();
        }
    };
    public final GroupCoalescer$$ExternalSyntheticLambda0 mEventComparator = new GroupCoalescer$$ExternalSyntheticLambda0();
    public final long mMinGroupLingerDuration = 200;
    public final long mMaxGroupLingerDuration = 500;

    /* renamed from: -$$Nest$mapplyRanking, reason: not valid java name */
    public static void m875$$Nest$mapplyRanking(GroupCoalescer groupCoalescer, NotificationListenerService.RankingMap rankingMap) {
        for (CoalescedEvent coalescedEvent : ((ArrayMap) groupCoalescer.mCoalescedEvents).values()) {
            NotificationListenerService.Ranking ranking = new NotificationListenerService.Ranking();
            if (rankingMap.getRanking(coalescedEvent.key, ranking)) {
                coalescedEvent.ranking = ranking;
            } else {
                GroupCoalescerLogger groupCoalescerLogger = groupCoalescer.mLogger;
                groupCoalescerLogger.getClass();
                LogLevel logLevel = LogLevel.WARNING;
                GroupCoalescerLogger$logMissingRanking$2 groupCoalescerLogger$logMissingRanking$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coalescer.GroupCoalescerLogger$logMissingRanking$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("RankingMap is missing an entry for coalesced notification ", ((LogMessage) obj).getStr1());
                    }
                };
                LogBuffer logBuffer = groupCoalescerLogger.buffer;
                LogMessage obtain = logBuffer.obtain("GroupCoalescer", logLevel, groupCoalescerLogger$logMissingRanking$2, null);
                ((LogMessageImpl) obtain).str1 = coalescedEvent.key;
                logBuffer.commit(obtain);
            }
        }
    }

    /* renamed from: -$$Nest$mmaybeEmitBatch, reason: not valid java name */
    public static void m876$$Nest$mmaybeEmitBatch(GroupCoalescer groupCoalescer, StatusBarNotification statusBarNotification) {
        CoalescedEvent coalescedEvent = (CoalescedEvent) ((ArrayMap) groupCoalescer.mCoalescedEvents).get(statusBarNotification.getKey());
        EventBatch eventBatch = (EventBatch) ((ArrayMap) groupCoalescer.mBatches).get(statusBarNotification.getGroupKey());
        GroupCoalescerLogger groupCoalescerLogger = groupCoalescer.mLogger;
        if (coalescedEvent != null) {
            String key = statusBarNotification.getKey();
            EventBatch eventBatch2 = coalescedEvent.batch;
            Objects.requireNonNull(eventBatch2);
            groupCoalescerLogger.getClass();
            LogLevel logLevel = LogLevel.DEBUG;
            GroupCoalescerLogger$logEarlyEmit$2 groupCoalescerLogger$logEarlyEmit$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coalescer.GroupCoalescerLogger$logEarlyEmit$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Modification of notif ", logMessage.getStr1(), " triggered early emit of batched group ", logMessage.getStr2());
                }
            };
            LogBuffer logBuffer = groupCoalescerLogger.buffer;
            LogMessage obtain = logBuffer.obtain("GroupCoalescer", logLevel, groupCoalescerLogger$logEarlyEmit$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = key;
            logMessageImpl.str2 = eventBatch2.mGroupKey;
            logBuffer.commit(obtain);
            EventBatch eventBatch3 = coalescedEvent.batch;
            Objects.requireNonNull(eventBatch3);
            groupCoalescer.emitBatch(eventBatch3);
            return;
        }
        if (eventBatch != null) {
            ((SystemClockImpl) groupCoalescer.mClock).getClass();
            if (android.os.SystemClock.uptimeMillis() - eventBatch.mCreatedTimestamp >= groupCoalescer.mMaxGroupLingerDuration) {
                String key2 = statusBarNotification.getKey();
                groupCoalescerLogger.getClass();
                LogLevel logLevel2 = LogLevel.INFO;
                GroupCoalescerLogger$logMaxBatchTimeout$2 groupCoalescerLogger$logMaxBatchTimeout$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coalescer.GroupCoalescerLogger$logMaxBatchTimeout$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("Modification of notif ", logMessage.getStr1(), " triggered TIMEOUT emit of batched group ", logMessage.getStr2());
                    }
                };
                LogBuffer logBuffer2 = groupCoalescerLogger.buffer;
                LogMessage obtain2 = logBuffer2.obtain("GroupCoalescer", logLevel2, groupCoalescerLogger$logMaxBatchTimeout$2, null);
                LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
                logMessageImpl2.str1 = key2;
                logMessageImpl2.str2 = eventBatch.mGroupKey;
                logBuffer2.commit(obtain2);
                groupCoalescer.emitBatch(eventBatch);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.statusbar.notification.collection.coalescer.GroupCoalescer$1] */
    public GroupCoalescer(DelayableExecutor delayableExecutor, SystemClock systemClock, GroupCoalescerLogger groupCoalescerLogger) {
        this.mMainExecutor = delayableExecutor;
        this.mClock = systemClock;
        this.mLogger = groupCoalescerLogger;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        ((SystemClockImpl) this.mClock).getClass();
        long uptimeMillis = android.os.SystemClock.uptimeMillis();
        printWriter.println();
        printWriter.println("Coalesced notifications:");
        int i = 0;
        for (EventBatch eventBatch : ((ArrayMap) this.mBatches).values()) {
            printWriter.println("   Batch " + eventBatch.mGroupKey + ":");
            printWriter.println("       Created " + (uptimeMillis - eventBatch.mCreatedTimestamp) + "ms ago");
            Iterator it = eventBatch.mMembers.iterator();
            while (it.hasNext()) {
                printWriter.println("       " + ((CoalescedEvent) it.next()).key);
                i++;
            }
        }
        if (i != ((ArrayMap) this.mCoalescedEvents).size()) {
            printWriter.println("    ERROR: batches contain " + ((ArrayMap) this.mCoalescedEvents).size() + " events but am tracking " + ((ArrayMap) this.mCoalescedEvents).size() + " total events");
            printWriter.println("    All tracked events:");
            Iterator it2 = ((ArrayMap) this.mCoalescedEvents).values().iterator();
            while (it2.hasNext()) {
                printWriter.println("        " + ((CoalescedEvent) it2.next()).key);
            }
        }
    }

    @Override // com.android.systemui.statusbar.notification.collection.PipelineDumpable
    public final void dumpPipeline(PipelineDumper pipelineDumper) {
        pipelineDumper.dump(this.mHandler, "handler");
    }

    public final void emitBatch(EventBatch eventBatch) {
        Object obj = ((ArrayMap) this.mBatches).get(eventBatch.mGroupKey);
        String str = eventBatch.mGroupKey;
        if (eventBatch != obj) {
            throw new IllegalStateException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Cannot emit out-of-date batch ", str));
        }
        if (eventBatch.mMembers.isEmpty()) {
            throw new IllegalStateException(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("Batch ", str, " cannot be empty"));
        }
        ExecutorImpl.ExecutionToken executionToken = eventBatch.mCancelShortTimeout;
        if (executionToken != null) {
            executionToken.run();
            eventBatch.mCancelShortTimeout = null;
        }
        ((ArrayMap) this.mBatches).remove(str);
        ArrayList<CoalescedEvent> arrayList = new ArrayList(eventBatch.mMembers);
        for (CoalescedEvent coalescedEvent : arrayList) {
            ((ArrayMap) this.mCoalescedEvents).remove(coalescedEvent.key);
            coalescedEvent.batch = null;
        }
        arrayList.sort(this.mEventComparator);
        ((SystemClockImpl) this.mClock).getClass();
        long uptimeMillis = android.os.SystemClock.uptimeMillis() - eventBatch.mCreatedTimestamp;
        int size = ((ArrayList) eventBatch.mMembers).size();
        GroupCoalescerLogger groupCoalescerLogger = this.mLogger;
        groupCoalescerLogger.getClass();
        LogLevel logLevel = LogLevel.DEBUG;
        GroupCoalescerLogger$logEmitBatch$2 groupCoalescerLogger$logEmitBatch$2 = new Function1() { // from class: com.android.systemui.statusbar.notification.collection.coalescer.GroupCoalescerLogger$logEmitBatch$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                LogMessage logMessage = (LogMessage) obj2;
                return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(logMessage.getLong1(), "ms", GenericDocument$$ExternalSyntheticOutline0.m("Emitting batch for group ", logMessage.getStr1(), " size=", logMessage.getInt1(), " age="));
            }
        };
        LogBuffer logBuffer = groupCoalescerLogger.buffer;
        LogMessage obtain = logBuffer.obtain("GroupCoalescer", logLevel, groupCoalescerLogger$logEmitBatch$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = str;
        logMessageImpl.int1 = size;
        logMessageImpl.long1 = uptimeMillis;
        logBuffer.commit(obtain);
        NotifCollection notifCollection = NotifCollection.this;
        notifCollection.getClass();
        Assert.isMainThread();
        String groupKey = ((CoalescedEvent) arrayList.get(0)).sbn.getGroupKey();
        notifCollection.mLogger.logNotifGroupPosted(arrayList.size(), groupKey);
        for (CoalescedEvent coalescedEvent2 : arrayList) {
            notifCollection.postNotification(coalescedEvent2.sbn, coalescedEvent2.ranking);
        }
        notifCollection.dispatchEventsAndRebuildList("onNotificationGroupPosted");
    }
}

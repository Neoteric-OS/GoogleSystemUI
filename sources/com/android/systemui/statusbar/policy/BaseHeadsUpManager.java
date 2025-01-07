package com.android.systemui.statusbar.policy;

import android.app.Notification;
import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.EventLog;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.appsearch.platformstorage.converter.GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0;
import androidx.room.TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.logging.CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone$HeadsUpEntryPhone$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.NotificationUtilsKt;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.provider.VisualStabilityProvider;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.util.ListenerSet;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class BaseHeadsUpManager implements HeadsUpManager {
    public final AccessibilityManagerWrapper mAccessibilityMgr;
    public final int mAutoDismissTime;
    public final AvalancheController mAvalancheController;
    public final Context mContext;
    public final DelayableExecutor mExecutor;
    public boolean mHasPinnedNotification;
    public final HeadsUpManagerLogger mLogger;
    public final int mMinimumDisplayTime;
    public int mSnoozeLengthMs;
    public final ArrayMap mSnoozedPackages;
    public final int mStickyForSomeTimeAutoDismissTime;
    public final SystemClock mSystemClock;
    public final int mTouchAcceptanceDelay;
    public final UiEventLogger mUiEventLogger;
    public int mUser;
    public final ListenerSet mListeners = new ListenerSet();
    public final ArrayMap mHeadsUpEntryMap = new ArrayMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class HeadsUpEntry implements Comparable {
        public ExecutorImpl.ExecutionToken mCancelRemoveRunnable;
        public long mEarliestRemovalTime;
        public NotificationEntry mEntry;
        public boolean mExpanded;
        public long mPostTime;
        public boolean mRemoteInputActive;
        public HeadsUpManagerPhone$HeadsUpEntryPhone$$ExternalSyntheticLambda0 mRemoveRunnable;
        public boolean mUserActionMayIndirectlyRemove;
        public boolean mWasUnpinned;
        public final /* synthetic */ HeadsUpManagerPhone this$0;

        public HeadsUpEntry(HeadsUpManagerPhone headsUpManagerPhone) {
            this.this$0 = headsUpManagerPhone;
        }

        public final void cancelAutoRemovalCallbacks(final String str) {
            Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.policy.BaseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    BaseHeadsUpManager.HeadsUpEntry headsUpEntry = BaseHeadsUpManager.HeadsUpEntry.this;
                    String str2 = str;
                    ExecutorImpl.ExecutionToken executionToken = headsUpEntry.mCancelRemoveRunnable;
                    boolean z = executionToken != null;
                    if (z) {
                        executionToken.run();
                        headsUpEntry.mCancelRemoveRunnable = null;
                    }
                    if (z) {
                        HeadsUpManagerLogger headsUpManagerLogger = headsUpEntry.this$0.mLogger;
                        NotificationEntry notificationEntry = headsUpEntry.mEntry;
                        headsUpManagerLogger.getClass();
                        LogLevel logLevel = LogLevel.INFO;
                        HeadsUpManagerLogger$logAutoRemoveCanceled$2 headsUpManagerLogger$logAutoRemoveCanceled$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logAutoRemoveCanceled$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                LogMessage logMessage = (LogMessage) obj;
                                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(logMessage.getStr2(), " => cancel auto remove: ", logMessage.getStr1());
                            }
                        };
                        LogBuffer logBuffer = headsUpManagerLogger.buffer;
                        LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logAutoRemoveCanceled$2, null);
                        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
                        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
                        logMessageImpl.str2 = str2;
                        logBuffer.commit(obtain);
                    }
                }
            };
            NotificationEntry notificationEntry = this.mEntry;
            if (notificationEntry == null || !this.this$0.isHeadsUpEntry(notificationEntry.mKey)) {
                runnable.run();
                return;
            }
            HeadsUpManagerLogger headsUpManagerLogger = this.this$0.mLogger;
            NotificationEntry notificationEntry2 = this.mEntry;
            headsUpManagerLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            HeadsUpManagerLogger$logAutoRemoveCancelRequest$2 headsUpManagerLogger$logAutoRemoveCancelRequest$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logAutoRemoveCancelRequest$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(logMessage.getStr2(), " => request: cancelAutoRemovalCallbacks: ", logMessage.getStr1());
                }
            };
            LogBuffer logBuffer = headsUpManagerLogger.buffer;
            LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logAutoRemoveCancelRequest$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry2);
            logMessageImpl.str2 = str;
            logBuffer.commit(obtain);
            this.this$0.mAvalancheController.update(this, runnable, str.concat(" cancelAutoRemovalCallbacks"));
        }

        public final boolean equals(Object obj) {
            NotificationEntry notificationEntry;
            if (this == obj) {
                return true;
            }
            if (obj != null && (obj instanceof HeadsUpEntry)) {
                HeadsUpEntry headsUpEntry = (HeadsUpEntry) obj;
                NotificationEntry notificationEntry2 = this.mEntry;
                if (notificationEntry2 != null && (notificationEntry = headsUpEntry.mEntry) != null) {
                    return notificationEntry2.mKey.equals(notificationEntry.mKey);
                }
            }
            return false;
        }

        public final int hashCode() {
            NotificationEntry notificationEntry = this.mEntry;
            return notificationEntry == null ? super.hashCode() : notificationEntry.mKey.hashCode() * 31;
        }

        public abstract boolean isSticky();

        public final void scheduleAutoRemovalCallback(BaseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0 baseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0, String str) {
            HeadsUpManagerLogger headsUpManagerLogger = this.this$0.mLogger;
            NotificationEntry notificationEntry = this.mEntry;
            headsUpManagerLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            HeadsUpManagerLogger$logAutoRemoveRequest$2 headsUpManagerLogger$logAutoRemoveRequest$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logAutoRemoveRequest$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("request: reschedule auto remove of ", logMessage.getStr1(), " reason: ", logMessage.getStr2());
                }
            };
            LogBuffer logBuffer = headsUpManagerLogger.buffer;
            LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logAutoRemoveRequest$2, null);
            LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
            logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
            logMessageImpl.str2 = str;
            logBuffer.commit(obtain);
            this.this$0.mAvalancheController.update(this, new BaseHeadsUpManager$$ExternalSyntheticLambda0(this, baseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0, str), str.concat(" scheduleAutoRemovalCallback"));
        }

        public abstract void updateEntry(String str, boolean z);

        /* JADX WARN: Code restructure failed: missing block: B:54:0x007c, code lost:
        
            if (r7.mRemoteInputActive != false) goto L29;
         */
        @Override // java.lang.Comparable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final int compareTo(com.android.systemui.statusbar.policy.BaseHeadsUpManager.HeadsUpEntry r7) {
            /*
                r6 = this;
                com.android.systemui.statusbar.notification.collection.NotificationEntry r0 = r6.mEntry
                r1 = 0
                if (r0 != 0) goto La
                com.android.systemui.statusbar.notification.collection.NotificationEntry r2 = r7.mEntry
                if (r2 != 0) goto La
                return r1
            La:
                com.android.systemui.statusbar.notification.collection.NotificationEntry r2 = r7.mEntry
                r3 = -1
                if (r2 != 0) goto L10
                return r3
            L10:
                r2 = 1
                if (r0 != 0) goto L14
                return r2
            L14:
                boolean r0 = r0.isRowPinned()
                com.android.systemui.statusbar.notification.collection.NotificationEntry r4 = r7.mEntry
                boolean r4 = r4.isRowPinned()
                if (r0 == 0) goto L23
                if (r4 != 0) goto L23
                return r3
            L23:
                if (r0 != 0) goto L28
                if (r4 == 0) goto L28
                return r2
            L28:
                com.android.systemui.statusbar.notification.collection.NotificationEntry r0 = r6.mEntry
                if (r0 != 0) goto L31
                com.android.systemui.statusbar.notification.collection.NotificationEntry r4 = r7.mEntry
                if (r4 != 0) goto L31
                goto L7f
            L31:
                com.android.systemui.statusbar.notification.collection.NotificationEntry r4 = r7.mEntry
                if (r4 != 0) goto L37
            L35:
                r1 = r3
                goto L7f
            L37:
                if (r0 != 0) goto L3b
            L39:
                r1 = r2
                goto L7f
            L3b:
                com.android.systemui.statusbar.notification.HeadsUpManagerPhone r4 = r6.this$0
                r4.getClass()
                boolean r0 = com.android.systemui.statusbar.policy.BaseHeadsUpManager.hasFullScreenIntent(r0)
                com.android.systemui.statusbar.notification.HeadsUpManagerPhone r4 = r6.this$0
                com.android.systemui.statusbar.notification.collection.NotificationEntry r5 = r7.mEntry
                r4.getClass()
                boolean r4 = com.android.systemui.statusbar.policy.BaseHeadsUpManager.hasFullScreenIntent(r5)
                if (r0 == 0) goto L54
                if (r4 != 0) goto L54
                goto L35
            L54:
                if (r0 != 0) goto L59
                if (r4 == 0) goto L59
                goto L39
            L59:
                com.android.systemui.statusbar.notification.collection.NotificationEntry r0 = r6.mEntry
                boolean r0 = com.android.systemui.statusbar.policy.BaseHeadsUpManager.m884$$Nest$smisCriticalCallNotif(r0)
                com.android.systemui.statusbar.notification.collection.NotificationEntry r4 = r7.mEntry
                boolean r4 = com.android.systemui.statusbar.policy.BaseHeadsUpManager.m884$$Nest$smisCriticalCallNotif(r4)
                if (r0 == 0) goto L6a
                if (r4 != 0) goto L6a
                goto L35
            L6a:
                if (r0 != 0) goto L6f
                if (r4 == 0) goto L6f
                goto L39
            L6f:
                boolean r0 = r6.mRemoteInputActive
                if (r0 == 0) goto L78
                boolean r4 = r7.mRemoteInputActive
                if (r4 != 0) goto L78
                goto L35
            L78:
                if (r0 != 0) goto L7f
                boolean r0 = r7.mRemoteInputActive
                if (r0 == 0) goto L7f
                goto L39
            L7f:
                if (r1 == 0) goto L82
                return r1
            L82:
                long r0 = r6.mPostTime
                long r4 = r7.mPostTime
                int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                if (r0 <= 0) goto L8b
                return r3
            L8b:
                if (r0 != 0) goto L9a
                com.android.systemui.statusbar.notification.collection.NotificationEntry r6 = r6.mEntry
                java.lang.String r6 = r6.mKey
                com.android.systemui.statusbar.notification.collection.NotificationEntry r7 = r7.mEntry
                java.lang.String r7 = r7.mKey
                int r6 = r6.compareTo(r7)
                return r6
            L9a:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.policy.BaseHeadsUpManager.HeadsUpEntry.compareTo(com.android.systemui.statusbar.policy.BaseHeadsUpManager$HeadsUpEntry):int");
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class NotificationPeekEvent implements UiEventLogger.UiEventEnum {
        public static final /* synthetic */ NotificationPeekEvent[] $VALUES;
        public static final NotificationPeekEvent NOTIFICATION_PEEK;
        private final int mId = 801;

        static {
            NotificationPeekEvent notificationPeekEvent = new NotificationPeekEvent();
            NOTIFICATION_PEEK = notificationPeekEvent;
            $VALUES = new NotificationPeekEvent[]{notificationPeekEvent};
        }

        public static NotificationPeekEvent valueOf(String str) {
            return (NotificationPeekEvent) Enum.valueOf(NotificationPeekEvent.class, str);
        }

        public static NotificationPeekEvent[] values() {
            return (NotificationPeekEvent[]) $VALUES.clone();
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* renamed from: -$$Nest$smisCriticalCallNotif, reason: not valid java name */
    public static boolean m884$$Nest$smisCriticalCallNotif(NotificationEntry notificationEntry) {
        Notification notification = notificationEntry.mSbn.getNotification();
        if (notification.isStyle(Notification.CallStyle.class) && notification.extras.getInt("android.callType") == 1) {
            return true;
        }
        return notificationEntry.mSbn.isOngoing() && "call".equals(notification.category);
    }

    public BaseHeadsUpManager(Context context, HeadsUpManagerLogger headsUpManagerLogger, Handler handler, final GlobalSettings globalSettings, SystemClock systemClock, DelayableExecutor delayableExecutor, AccessibilityManagerWrapper accessibilityManagerWrapper, UiEventLogger uiEventLogger, AvalancheController avalancheController) {
        this.mLogger = headsUpManagerLogger;
        this.mExecutor = delayableExecutor;
        this.mSystemClock = systemClock;
        this.mContext = context;
        this.mAccessibilityMgr = accessibilityManagerWrapper;
        this.mUiEventLogger = uiEventLogger;
        this.mAvalancheController = avalancheController;
        avalancheController.baseEntryMapStr = new Function0() { // from class: com.android.systemui.statusbar.policy.BaseHeadsUpManager$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                BaseHeadsUpManager baseHeadsUpManager = BaseHeadsUpManager.this;
                if (baseHeadsUpManager.mHeadsUpEntryMap.isEmpty()) {
                    return "EMPTY";
                }
                StringBuilder sb = new StringBuilder();
                for (BaseHeadsUpManager.HeadsUpEntry headsUpEntry : baseHeadsUpManager.mHeadsUpEntryMap.values()) {
                    sb.append("\n\t");
                    NotificationEntry notificationEntry = headsUpEntry.mEntry;
                    sb.append(notificationEntry == null ? "null" : notificationEntry.mKey);
                }
                return sb.toString();
            }
        };
        Resources resources = context.getResources();
        this.mMinimumDisplayTime = resources.getInteger(R.integer.heads_up_notification_minimum_time);
        this.mStickyForSomeTimeAutoDismissTime = resources.getInteger(R.integer.sticky_heads_up_notification_time);
        this.mAutoDismissTime = resources.getInteger(R.integer.heads_up_notification_decay);
        this.mTouchAcceptanceDelay = resources.getInteger(R.integer.touch_acceptance_delay);
        this.mSnoozedPackages = new ArrayMap();
        this.mSnoozeLengthMs = globalSettings.getInt(resources.getInteger(R.integer.heads_up_default_snooze_length_ms), "heads_up_snooze_length_ms");
        globalSettings.registerContentObserverSync(Settings.Global.getUriFor("heads_up_snooze_length_ms"), false, new ContentObserver(handler) { // from class: com.android.systemui.statusbar.policy.BaseHeadsUpManager.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                int i = globalSettings.getInt(-1, "heads_up_snooze_length_ms");
                if (i > -1) {
                    BaseHeadsUpManager baseHeadsUpManager = BaseHeadsUpManager.this;
                    if (i != baseHeadsUpManager.mSnoozeLengthMs) {
                        baseHeadsUpManager.mSnoozeLengthMs = i;
                        HeadsUpManagerLogger headsUpManagerLogger2 = baseHeadsUpManager.mLogger;
                        headsUpManagerLogger2.getClass();
                        LogLevel logLevel = LogLevel.INFO;
                        HeadsUpManagerLogger$logSnoozeLengthChange$2 headsUpManagerLogger$logSnoozeLengthChange$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logSnoozeLengthChange$2
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return GenericDocument$$ExternalSyntheticOutline0.m("snooze length changed: ", "ms", ((LogMessage) obj).getInt1());
                            }
                        };
                        LogBuffer logBuffer = headsUpManagerLogger2.buffer;
                        LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logSnoozeLengthChange$2, null);
                        ((LogMessageImpl) obtain).int1 = i;
                        logBuffer.commit(obtain);
                    }
                }
            }
        });
    }

    public static boolean hasFullScreenIntent(NotificationEntry notificationEntry) {
        StatusBarNotification statusBarNotification;
        return (notificationEntry == null || (statusBarNotification = notificationEntry.mSbn) == null || statusBarNotification.getNotification() == null || notificationEntry.mSbn.getNotification().fullScreenIntent == null) ? false : true;
    }

    public final void addListener(OnHeadsUpChangedListener onHeadsUpChangedListener) {
        this.mListeners.addIfAbsent(onHeadsUpChangedListener);
    }

    public abstract boolean canRemoveImmediately(String str);

    public final Stream getAllEntries() {
        return getHeadsUpEntryList().stream().map(new BaseHeadsUpManager$$ExternalSyntheticLambda2());
    }

    public final HeadsUpEntry getHeadsUpEntry(String str) {
        if (this.mHeadsUpEntryMap.containsKey(str)) {
            return (HeadsUpEntry) this.mHeadsUpEntryMap.get(str);
        }
        this.mAvalancheController.getClass();
        return null;
    }

    public final List getHeadsUpEntryList() {
        ArrayList arrayList = new ArrayList(this.mHeadsUpEntryMap.values());
        this.mAvalancheController.getClass();
        arrayList.addAll(new ArrayList());
        return arrayList;
    }

    public final HeadsUpEntry getTopHeadsUpEntry() {
        HeadsUpEntry headsUpEntry = null;
        if (this.mHeadsUpEntryMap.isEmpty()) {
            return null;
        }
        for (HeadsUpEntry headsUpEntry2 : this.mHeadsUpEntryMap.values()) {
            if (headsUpEntry == null || headsUpEntry2.compareTo(headsUpEntry) < 0) {
                headsUpEntry = headsUpEntry2;
            }
        }
        return headsUpEntry;
    }

    public final boolean hasNotifications$1() {
        if (this.mHeadsUpEntryMap.isEmpty()) {
            this.mAvalancheController.getClass();
            if (new ArrayList().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public final boolean isHeadsUpEntry(String str) {
        if (this.mHeadsUpEntryMap.containsKey(str)) {
            return true;
        }
        this.mAvalancheController.getClass();
        return false;
    }

    public final boolean isSnoozed(String str) {
        String str2 = this.mUser + "," + str;
        Long l = (Long) this.mSnoozedPackages.get(str2);
        if (l == null) {
            return false;
        }
        long longValue = l.longValue();
        ((SystemClockImpl) this.mSystemClock).getClass();
        long elapsedRealtime = android.os.SystemClock.elapsedRealtime();
        HeadsUpManagerLogger headsUpManagerLogger = this.mLogger;
        if (longValue > elapsedRealtime) {
            headsUpManagerLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            HeadsUpManagerLogger$logIsSnoozedReturned$2 headsUpManagerLogger$logIsSnoozedReturned$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logIsSnoozedReturned$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("package snoozed when queried ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = headsUpManagerLogger.buffer;
            LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logIsSnoozedReturned$2, null);
            ((LogMessageImpl) obtain).str1 = str2;
            logBuffer.commit(obtain);
            return true;
        }
        headsUpManagerLogger.getClass();
        LogLevel logLevel2 = LogLevel.INFO;
        HeadsUpManagerLogger$logPackageUnsnoozed$2 headsUpManagerLogger$logPackageUnsnoozed$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logPackageUnsnoozed$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("package unsnoozed ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer2 = headsUpManagerLogger.buffer;
        LogMessage obtain2 = logBuffer2.obtain("HeadsUpManager", logLevel2, headsUpManagerLogger$logPackageUnsnoozed$2, null);
        ((LogMessageImpl) obtain2).str1 = str2;
        logBuffer2.commit(obtain2);
        this.mSnoozedPackages.remove(str2);
        return false;
    }

    public void onEntryRemoved(HeadsUpEntry headsUpEntry) {
        NotificationEntry notificationEntry = headsUpEntry.mEntry;
        notificationEntry.setHeadsUp(false);
        setEntryPinned(headsUpEntry, false, "onEntryRemoved");
        EventLog.writeEvent(36001, notificationEntry.mKey, 0);
        HeadsUpManagerLogger headsUpManagerLogger = this.mLogger;
        headsUpManagerLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        HeadsUpManagerLogger$logNotificationActuallyRemoved$2 headsUpManagerLogger$logNotificationActuallyRemoved$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logNotificationActuallyRemoved$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m("removed: ", ((LogMessage) obj).getStr1(), " ");
            }
        };
        LogBuffer logBuffer = headsUpManagerLogger.buffer;
        LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logNotificationActuallyRemoved$2, null);
        ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain);
        Iterator it = this.mListeners.listeners.iterator();
        while (it.hasNext()) {
            ((OnHeadsUpChangedListener) it.next()).onHeadsUpStateChanged(notificationEntry, false);
        }
    }

    public final void releaseAllImmediately() {
        HeadsUpManagerLogger headsUpManagerLogger = this.mLogger;
        headsUpManagerLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        HeadsUpManagerLogger$logReleaseAllImmediately$2 headsUpManagerLogger$logReleaseAllImmediately$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logReleaseAllImmediately$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return "release all immediately";
            }
        };
        LogBuffer logBuffer = headsUpManagerLogger.buffer;
        logBuffer.commit(logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logReleaseAllImmediately$2, null));
        ArraySet arraySet = new ArraySet(this.mHeadsUpEntryMap.keySet());
        this.mAvalancheController.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            removeEntry((String) it.next(), "releaseAllImmediately (keysToRemove)");
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            removeEntry((String) it2.next(), "releaseAllImmediately (waitingKeysToRemove)");
        }
    }

    public final void removeEntry(final String str, final String str2) {
        final boolean z;
        HeadsUpEntry headsUpEntry = (HeadsUpEntry) this.mHeadsUpEntryMap.get(str);
        AvalancheController avalancheController = this.mAvalancheController;
        if (headsUpEntry == null) {
            avalancheController.getClass();
            z = true;
            headsUpEntry = null;
        } else {
            z = false;
        }
        HeadsUpManagerLogger headsUpManagerLogger = this.mLogger;
        headsUpManagerLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logRemoveEntryRequest$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str22 = logMessage.getStr2();
                String str1 = logMessage.getStr1();
                boolean z2 = z;
                StringBuilder m = TriggerBasedInvalidationTracker$$ExternalSyntheticOutline0.m("request: ", str22, " => removeEntry: ", str1, " isWaiting: ");
                m.append(z2);
                return m.toString();
            }
        };
        LogBuffer logBuffer = headsUpManagerLogger.buffer;
        LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, function1, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtils.logKey(str);
        logMessageImpl.str2 = str2;
        logMessageImpl.bool1 = z;
        logBuffer.commit(obtain);
        final HeadsUpEntry headsUpEntry2 = headsUpEntry;
        avalancheController.delete(headsUpEntry, new Runnable() { // from class: com.android.systemui.statusbar.policy.BaseHeadsUpManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BaseHeadsUpManager baseHeadsUpManager = BaseHeadsUpManager.this;
                String str3 = str;
                String str4 = str2;
                final boolean z2 = z;
                BaseHeadsUpManager.HeadsUpEntry headsUpEntry3 = headsUpEntry2;
                HeadsUpManagerLogger headsUpManagerLogger2 = baseHeadsUpManager.mLogger;
                headsUpManagerLogger2.getClass();
                LogLevel logLevel2 = LogLevel.INFO;
                Function1 function12 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logRemoveEntry$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        LogMessage logMessage = (LogMessage) obj;
                        return logMessage.getStr2() + " => removeEntry: " + logMessage.getStr1() + " isWaiting: " + z2;
                    }
                };
                LogBuffer logBuffer2 = headsUpManagerLogger2.buffer;
                LogMessage obtain2 = logBuffer2.obtain("HeadsUpManager", logLevel2, function12, null);
                LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
                logMessageImpl2.str1 = NotificationUtils.logKey(str3);
                logMessageImpl2.str2 = str4;
                logMessageImpl2.bool1 = z2;
                logBuffer2.commit(obtain2);
                if (headsUpEntry3 == null) {
                    return;
                }
                NotificationEntry notificationEntry = headsUpEntry3.mEntry;
                if (notificationEntry == null || !notificationEntry.mExpandAnimationRunning) {
                    notificationEntry.mIsDemoted = true;
                    baseHeadsUpManager.mHeadsUpEntryMap.remove(str3);
                    baseHeadsUpManager.onEntryRemoved(headsUpEntry3);
                    ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
                    if (expandableNotificationRow != null) {
                        expandableNotificationRow.sendAccessibilityEvent(2048);
                    }
                    HeadsUpManagerPhone.HeadsUpEntryPhone headsUpEntryPhone = (HeadsUpManagerPhone.HeadsUpEntryPhone) headsUpEntry3;
                    headsUpEntryPhone.cancelAutoRemovalCallbacks("reset()");
                    headsUpEntryPhone.mEntry = null;
                    headsUpEntryPhone.mRemoveRunnable = null;
                    headsUpEntryPhone.mExpanded = false;
                    headsUpEntryPhone.mRemoteInputActive = false;
                    headsUpEntryPhone.mGutsShownPinned = false;
                    headsUpEntryPhone.extended = false;
                }
            }
        }, "removeEntry");
    }

    public final boolean removeNotification$1(String str, String str2, boolean z) {
        this.mAvalancheController.getClass();
        HeadsUpManagerLogger headsUpManagerLogger = this.mLogger;
        headsUpManagerLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        HeadsUpManagerLogger$logRemoveNotification$2 headsUpManagerLogger$logRemoveNotification$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logRemoveNotification$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                String str22 = logMessage.getStr2();
                StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("remove notification ", str1, " releaseImmediately: ", bool1, " isWaiting: ");
                m.append(bool2);
                m.append(" reason: ");
                m.append(str22);
                return m.toString();
            }
        };
        LogBuffer logBuffer = headsUpManagerLogger.buffer;
        LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logRemoveNotification$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtils.logKey(str);
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = false;
        logMessageImpl.str2 = str2;
        logBuffer.commit(obtain);
        HeadsUpEntry headsUpEntry = (HeadsUpEntry) this.mHeadsUpEntryMap.get(str);
        if (headsUpEntry == null) {
            LogMessage obtain2 = logBuffer.obtain("HeadsUpManager", logLevel, new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logNullEntry$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    LogMessage logMessage = (LogMessage) obj;
                    return GenericDocumentToPlatformConverter$$ExternalSyntheticOutline0.m("remove notif ", logMessage.getStr1(), " when headsUpEntry is null, reason: ", logMessage.getStr2());
                }
            }, null);
            LogMessageImpl logMessageImpl2 = (LogMessageImpl) obtain2;
            logMessageImpl2.str1 = NotificationUtils.logKey(str);
            logMessageImpl2.str2 = str2;
            logBuffer.commit(obtain2);
            return true;
        }
        if (z) {
            removeEntry(str, "removeNotification (releaseImmediately)");
            return true;
        }
        if (canRemoveImmediately(str)) {
            removeEntry(str, "removeNotification (canRemoveImmediately)");
            return true;
        }
        if (headsUpEntry.mRemoveRunnable != null) {
            headsUpEntry.scheduleAutoRemovalCallback(new BaseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0(headsUpEntry, 0), "removeAsSoonAsPossible");
        }
        return false;
    }

    public final void setEntryPinned(HeadsUpEntry headsUpEntry, boolean z, String str) {
        StatusBarNotification statusBarNotification;
        ExpandableNotificationRow expandableNotificationRow;
        NotificationEntry notificationEntry = headsUpEntry.mEntry;
        HeadsUpManagerLogger headsUpManagerLogger = this.mLogger;
        headsUpManagerLogger.getClass();
        LogLevel logLevel = LogLevel.VERBOSE;
        HeadsUpManagerLogger$logSetEntryPinned$2 headsUpManagerLogger$logSetEntryPinned$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logSetEntryPinned$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                return logMessage.getStr2() + " => setEntryPinned[" + logMessage.getBool1() + "]: " + logMessage.getStr1();
            }
        };
        LogBuffer logBuffer = headsUpManagerLogger.buffer;
        LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logSetEntryPinned$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logMessageImpl.bool1 = z;
        logMessageImpl.str2 = str;
        logBuffer.commit(obtain);
        NotificationEntry notificationEntry2 = headsUpEntry.mEntry;
        if (!z) {
            headsUpEntry.mWasUnpinned = true;
        }
        boolean z2 = false;
        if ((notificationEntry2 != null && notificationEntry2.isRowPinned()) != z) {
            HeadsUpManagerPhone.HeadsUpEntryPhone headsUpEntryPhone = (HeadsUpManagerPhone.HeadsUpEntryPhone) headsUpEntry;
            NotificationEntry notificationEntry3 = headsUpEntryPhone.mEntry;
            if (notificationEntry3 != null && (expandableNotificationRow = notificationEntry3.row) != null) {
                int intrinsicHeight = expandableNotificationRow.getIntrinsicHeight();
                boolean isAboveShelf = expandableNotificationRow.isAboveShelf();
                expandableNotificationRow.mIsPinned = z;
                if (intrinsicHeight != expandableNotificationRow.getIntrinsicHeight()) {
                    expandableNotificationRow.notifyHeightChanged(false);
                }
                if (z) {
                    expandableNotificationRow.setAnimationRunning(true);
                    expandableNotificationRow.mExpandedWhenPinned = false;
                } else if (expandableNotificationRow.mExpandedWhenPinned) {
                    expandableNotificationRow.setUserExpanded(true, false);
                }
                expandableNotificationRow.setChronometerRunning(expandableNotificationRow.mLastChronometerRunning);
                if (expandableNotificationRow.isAboveShelf() != isAboveShelf) {
                    expandableNotificationRow.mAboveShelfChangedListener.onAboveShelfStateChanged(!isAboveShelf);
                }
            }
            AuthContainerView$$ExternalSyntheticOutline0.m(z, headsUpEntryPhone.mIsPinned, null);
            Iterator it = this.mHeadsUpEntryMap.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                NotificationEntry notificationEntry4 = getHeadsUpEntry((String) it.next()).mEntry;
                if (notificationEntry4 != null && notificationEntry4.isRowPinned()) {
                    z2 = true;
                    break;
                }
            }
            boolean z3 = this.mHasPinnedNotification;
            ListenerSet listenerSet = this.mListeners;
            if (z2 != z3) {
                LogMessage obtain2 = logBuffer.obtain("HeadsUpManager", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logUpdatePinnedMode$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return "hasPinnedNotification[" + ((LogMessage) obj).getBool1() + "]";
                    }
                }, null);
                ((LogMessageImpl) obtain2).bool1 = z2;
                logBuffer.commit(obtain2);
                this.mHasPinnedNotification = z2;
                if (z2) {
                    MetricsLogger.count(this.mContext, "note_peek", 1);
                }
                Iterator it2 = listenerSet.listeners.iterator();
                while (it2.hasNext()) {
                    ((OnHeadsUpChangedListener) it2.next()).onHeadsUpPinnedModeChanged(z2);
                }
            }
            if (z && (statusBarNotification = notificationEntry2.mSbn) != null) {
                this.mUiEventLogger.logWithInstanceId(NotificationPeekEvent.NOTIFICATION_PEEK, statusBarNotification.getUid(), notificationEntry2.mSbn.getPackageName(), notificationEntry2.mSbn.getInstanceId());
            }
            Iterator it3 = listenerSet.listeners.iterator();
            while (it3.hasNext()) {
                OnHeadsUpChangedListener onHeadsUpChangedListener = (OnHeadsUpChangedListener) it3.next();
                if (z) {
                    onHeadsUpChangedListener.onHeadsUpPinned(notificationEntry2);
                } else {
                    onHeadsUpChangedListener.onHeadsUpUnPinned(notificationEntry2);
                }
            }
        }
    }

    public abstract boolean shouldHeadsUpBecomePinned(NotificationEntry notificationEntry);

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.systemui.statusbar.notification.HeadsUpManagerPhone$HeadsUpEntryPhone$$ExternalSyntheticLambda0] */
    public final void showNotification(final NotificationEntry notificationEntry) {
        final HeadsUpManagerPhone.HeadsUpEntryPhone headsUpEntryPhone = (HeadsUpManagerPhone.HeadsUpEntryPhone) ((HeadsUpManagerPhone) this).mEntryPool.acquire();
        headsUpEntryPhone.getClass();
        ?? r1 = new Runnable() { // from class: com.android.systemui.statusbar.notification.HeadsUpManagerPhone$HeadsUpEntryPhone$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ExpandableNotificationRow expandableNotificationRow;
                ExpandableNotificationRow expandableNotificationRow2;
                HeadsUpManagerPhone.HeadsUpEntryPhone headsUpEntryPhone2 = HeadsUpManagerPhone.HeadsUpEntryPhone.this;
                NotificationEntry notificationEntry2 = notificationEntry;
                if (!HeadsUpManagerPhone.this.mVisualStabilityProvider.isReorderingAllowed && ((expandableNotificationRow2 = notificationEntry2.row) == null || !expandableNotificationRow2.showingPulsing())) {
                    HeadsUpManagerPhone.this.mEntriesToRemoveWhenReorderingAllowed.add(notificationEntry2);
                    HeadsUpManagerPhone headsUpManagerPhone = HeadsUpManagerPhone.this;
                    VisualStabilityProvider visualStabilityProvider = headsUpManagerPhone.mVisualStabilityProvider;
                    CopyOnWriteArrayList copyOnWriteArrayList = visualStabilityProvider.allListeners.listeners;
                    HeadsUpManagerPhone$$ExternalSyntheticLambda0 headsUpManagerPhone$$ExternalSyntheticLambda0 = headsUpManagerPhone.mOnReorderingAllowedListener;
                    if (copyOnWriteArrayList.addIfAbsent(headsUpManagerPhone$$ExternalSyntheticLambda0)) {
                        visualStabilityProvider.temporaryListeners.add(headsUpManagerPhone$$ExternalSyntheticLambda0);
                        return;
                    }
                    return;
                }
                HeadsUpManagerPhone headsUpManagerPhone2 = HeadsUpManagerPhone.this;
                if (headsUpManagerPhone2.mTrackingHeadsUp) {
                    headsUpManagerPhone2.mEntriesToRemoveAfterExpand.add(notificationEntry2);
                    HeadsUpManagerPhone.this.mLogger.logRemoveEntryAfterExpand(notificationEntry2);
                } else if (headsUpManagerPhone2.mVisualStabilityProvider.isReorderingAllowed || ((expandableNotificationRow = notificationEntry2.row) != null && expandableNotificationRow.showingPulsing())) {
                    HeadsUpManagerPhone.this.removeEntry(notificationEntry2.mKey, "createRemoveRunnable");
                }
            }
        };
        headsUpEntryPhone.mEntry = notificationEntry;
        headsUpEntryPhone.mRemoveRunnable = r1;
        ((SystemClockImpl) ((HeadsUpEntry) headsUpEntryPhone).this$0.mSystemClock).getClass();
        headsUpEntryPhone.mPostTime = android.os.SystemClock.elapsedRealtime() + ((HeadsUpEntry) headsUpEntryPhone).this$0.mTouchAcceptanceDelay;
        headsUpEntryPhone.updateEntry("setEntry", true);
        HeadsUpManagerLogger headsUpManagerLogger = this.mLogger;
        headsUpManagerLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        HeadsUpManagerLogger$logShowNotificationRequest$2 headsUpManagerLogger$logShowNotificationRequest$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logShowNotificationRequest$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("request: show notification ", ((LogMessage) obj).getStr1());
            }
        };
        LogBuffer logBuffer = headsUpManagerLogger.buffer;
        LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logShowNotificationRequest$2, null);
        ((LogMessageImpl) obtain).str1 = NotificationUtilsKt.getLogKey(notificationEntry);
        logBuffer.commit(obtain);
        this.mAvalancheController.update(headsUpEntryPhone, new BaseHeadsUpManager$$ExternalSyntheticLambda0(this, notificationEntry, headsUpEntryPhone, 1), "showNotification");
    }

    public void snooze() {
        ArrayList arrayList = new ArrayList(this.mHeadsUpEntryMap.keySet());
        this.mAvalancheController.getClass();
        arrayList.addAll(new ArrayList());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            NotificationEntry notificationEntry = getHeadsUpEntry((String) it.next()).mEntry;
            if (notificationEntry != null) {
                String packageName = notificationEntry.mSbn.getPackageName();
                String str = this.mUser + "," + packageName;
                HeadsUpManagerLogger headsUpManagerLogger = this.mLogger;
                headsUpManagerLogger.getClass();
                LogLevel logLevel = LogLevel.INFO;
                HeadsUpManagerLogger$logPackageSnoozed$2 headsUpManagerLogger$logPackageSnoozed$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logPackageSnoozed$2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("package snoozed ", ((LogMessage) obj).getStr1());
                    }
                };
                LogBuffer logBuffer = headsUpManagerLogger.buffer;
                LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logPackageSnoozed$2, null);
                ((LogMessageImpl) obtain).str1 = str;
                logBuffer.commit(obtain);
                ArrayMap arrayMap = this.mSnoozedPackages;
                ((SystemClockImpl) this.mSystemClock).getClass();
                arrayMap.put(str, Long.valueOf(android.os.SystemClock.elapsedRealtime() + this.mSnoozeLengthMs));
            }
        }
    }

    public void unpinAll() {
        for (String str : this.mHeadsUpEntryMap.keySet()) {
            HeadsUpEntry headsUpEntry = getHeadsUpEntry(str);
            HeadsUpManagerLogger headsUpManagerLogger = this.mLogger;
            headsUpManagerLogger.getClass();
            LogLevel logLevel = LogLevel.INFO;
            HeadsUpManagerLogger$logUnpinEntryRequest$2 headsUpManagerLogger$logUnpinEntryRequest$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logUnpinEntryRequest$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("request: unpin entry ", ((LogMessage) obj).getStr1());
                }
            };
            LogBuffer logBuffer = headsUpManagerLogger.buffer;
            LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logUnpinEntryRequest$2, null);
            ((LogMessageImpl) obtain).str1 = NotificationUtils.logKey(str);
            logBuffer.commit(obtain);
            this.mAvalancheController.delete(headsUpEntry, new BaseHeadsUpManager$$ExternalSyntheticLambda0((HeadsUpManagerPhone) this, str, headsUpEntry, 0), "unpinAll");
        }
    }

    public final void updateNotification(String str, boolean z) {
        HeadsUpEntry headsUpEntry = (HeadsUpEntry) this.mHeadsUpEntryMap.get(str);
        boolean z2 = headsUpEntry != null;
        HeadsUpManagerLogger headsUpManagerLogger = this.mLogger;
        headsUpManagerLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        HeadsUpManagerLogger$logUpdateNotificationRequest$2 headsUpManagerLogger$logUpdateNotificationRequest$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logUpdateNotificationRequest$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("request: update notif ", str1, " alert: ", bool1, " hasEntry: ");
                m.append(bool2);
                return m.toString();
            }
        };
        LogBuffer logBuffer = headsUpManagerLogger.buffer;
        LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logUpdateNotificationRequest$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtils.logKey(str);
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = z2;
        logBuffer.commit(obtain);
        this.mAvalancheController.update(headsUpEntry, new BaseHeadsUpManager$$ExternalSyntheticLambda5(this, str, z), "updateNotification");
    }

    public final void updateNotificationInternal(String str, boolean z) {
        ExpandableNotificationRow expandableNotificationRow;
        HeadsUpEntry headsUpEntry = (HeadsUpEntry) this.mHeadsUpEntryMap.get(str);
        boolean z2 = headsUpEntry != null;
        HeadsUpManagerLogger headsUpManagerLogger = this.mLogger;
        headsUpManagerLogger.getClass();
        LogLevel logLevel = LogLevel.INFO;
        HeadsUpManagerLogger$logUpdateNotification$2 headsUpManagerLogger$logUpdateNotification$2 = new Function1() { // from class: com.android.systemui.statusbar.policy.HeadsUpManagerLogger$logUpdateNotification$2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                LogMessage logMessage = (LogMessage) obj;
                String str1 = logMessage.getStr1();
                boolean bool1 = logMessage.getBool1();
                boolean bool2 = logMessage.getBool2();
                StringBuilder m = CarrierTextManagerLogger$logUpdate$2$$ExternalSyntheticOutline0.m("update notif ", str1, " alert: ", bool1, " hasEntry: ");
                m.append(bool2);
                return m.toString();
            }
        };
        LogBuffer logBuffer = headsUpManagerLogger.buffer;
        LogMessage obtain = logBuffer.obtain("HeadsUpManager", logLevel, headsUpManagerLogger$logUpdateNotification$2, null);
        LogMessageImpl logMessageImpl = (LogMessageImpl) obtain;
        logMessageImpl.str1 = NotificationUtils.logKey(str);
        logMessageImpl.bool1 = z;
        logMessageImpl.bool2 = z2;
        logBuffer.commit(obtain);
        if (headsUpEntry == null) {
            return;
        }
        NotificationEntry notificationEntry = headsUpEntry.mEntry;
        if (notificationEntry != null && (expandableNotificationRow = notificationEntry.row) != null) {
            expandableNotificationRow.sendAccessibilityEvent(2048);
        }
        if (z) {
            headsUpEntry.updateEntry("updateNotification", true);
            setEntryPinned(headsUpEntry, shouldHeadsUpBecomePinned(headsUpEntry.mEntry), "updateNotificationInternal");
        }
    }
}

package com.android.systemui.statusbar.notification.collection;

import android.app.NotificationChannel;
import android.os.Handler;
import android.os.RemoteException;
import android.os.Trace;
import android.os.UserHandle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.ArrayMap;
import android.util.IndentingPrintWriter;
import android.util.Log;
import android.util.Pair;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.dump.LogBufferEulogizer;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.notification.NotificationUtils;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.ShadeListBuilder;
import com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator;
import com.android.systemui.statusbar.notification.collection.inflation.OnUserInteractionCallbackImpl$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.notification.collection.notifcollection.BindEntryEvent;
import com.android.systemui.statusbar.notification.collection.notifcollection.ChannelChangedEvent;
import com.android.systemui.statusbar.notification.collection.notifcollection.CleanUpEntryEvent;
import com.android.systemui.statusbar.notification.collection.notifcollection.DismissedByUserStats;
import com.android.systemui.statusbar.notification.collection.notifcollection.EntryAddedEvent;
import com.android.systemui.statusbar.notification.collection.notifcollection.EntryRemovedEvent;
import com.android.systemui.statusbar.notification.collection.notifcollection.EntryUpdatedEvent;
import com.android.systemui.statusbar.notification.collection.notifcollection.InitEntryEvent;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionInconsistencyTracker;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLoggerKt;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifEvent;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifLifetimeExtender;
import com.android.systemui.statusbar.notification.collection.notifcollection.RankingAppliedEvent;
import com.android.systemui.statusbar.notification.collection.notifcollection.RankingUpdatedEvent;
import com.android.systemui.statusbar.notification.collection.provider.NotificationDismissibilityProviderImpl;
import com.android.systemui.util.Assert;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.NamedListenerSet;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.systemui.wmshell.BubblesManager;
import com.android.systemui.wmshell.BubblesManager$$ExternalSyntheticLambda2;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda0;
import com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda1;
import com.android.wm.shell.bubbles.BubbleEntry;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import kotlin.collections.EmptySet;
import kotlin.collections.MapsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifCollection implements Dumpable, PipelineDumpable {
    public boolean mAmDispatchingToOtherCode;
    public boolean mAttached;
    public final Executor mBgExecutor;
    public ShadeListBuilder.AnonymousClass1 mBuildListener;
    public final SystemClock mClock;
    public final List mDismissInterceptors;
    public final NotificationDismissibilityProviderImpl mDismissibilityProvider;
    public final DumpManager mDumpManager;
    public final LogBufferEulogizer mEulogizer;
    public final Queue mEventQueue;
    public final HashMap mFutureDismissals;
    public final NotifCollectionInconsistencyTracker mInconsistencyTracker;
    public final List mLifetimeExtenders;
    public final NotifCollectionLogger mLogger;
    public final Handler mMainHandler;
    public final NamedListenerSet mNotifCollectionListeners;
    public final AnonymousClass1 mNotifHandler;
    public final Map mNotificationSet;
    public final Collection mReadOnlyNotificationSet;
    public final NotifCollection$$ExternalSyntheticLambda0 mRebuildListRunnable;
    public final IStatusBarService mStatusBarService;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.collection.NotifCollection$1, reason: invalid class name */
    public final class AnonymousClass1 implements NotificationListener.NotificationHandler {
        public AnonymousClass1() {
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationChannelModified(String str, UserHandle userHandle, NotificationChannel notificationChannel, int i) {
            NotifCollection notifCollection = NotifCollection.this;
            notifCollection.getClass();
            Assert.isMainThread();
            notifCollection.mEventQueue.add(new ChannelChangedEvent(str, userHandle, notificationChannel, i));
            Trace.beginSection("NotifCollection.dispatchEventsAndAsynchronouslyRebuildList");
            notifCollection.dispatchEvents();
            Handler handler = notifCollection.mMainHandler;
            NotifCollection$$ExternalSyntheticLambda0 notifCollection$$ExternalSyntheticLambda0 = notifCollection.mRebuildListRunnable;
            if (!handler.hasCallbacks(notifCollection$$ExternalSyntheticLambda0)) {
                handler.postDelayed(notifCollection$$ExternalSyntheticLambda0, 1000L);
            }
            Trace.endSection();
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationPosted(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap) {
            NotifCollection notifCollection = NotifCollection.this;
            notifCollection.getClass();
            Assert.isMainThread();
            String key = statusBarNotification.getKey();
            NotificationListenerService.Ranking ranking = new NotificationListenerService.Ranking();
            if (!rankingMap.getRanking(key, ranking)) {
                throw new IllegalArgumentException(AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Ranking map doesn't contain key: ", key));
            }
            notifCollection.postNotification(statusBarNotification, ranking);
            notifCollection.applyRanking(rankingMap);
            notifCollection.dispatchEventsAndRebuildList("onNotificationPosted");
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationRankingUpdate(NotificationListenerService.RankingMap rankingMap) {
            NotifCollection notifCollection = NotifCollection.this;
            notifCollection.getClass();
            Assert.isMainThread();
            notifCollection.mEventQueue.add(new RankingUpdatedEvent(rankingMap));
            notifCollection.applyRanking(rankingMap);
            notifCollection.dispatchEventsAndRebuildList("onNotificationRankingUpdate");
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationRemoved(StatusBarNotification statusBarNotification, NotificationListenerService.RankingMap rankingMap, int i) {
            NotifCollection notifCollection = NotifCollection.this;
            notifCollection.getClass();
            Assert.isMainThread();
            NotifCollectionLogger notifCollectionLogger = notifCollection.mLogger;
            notifCollectionLogger.logNotifRemoved(statusBarNotification, i);
            NotificationEntry notificationEntry = (NotificationEntry) ((ArrayMap) notifCollection.mNotificationSet).get(statusBarNotification.getKey());
            if (notificationEntry == null) {
                notifCollectionLogger.logNoNotificationToRemoveWithKey(statusBarNotification, i);
                return;
            }
            notificationEntry.mCancellationReason = i;
            notifCollection.tryRemoveNotification(notificationEntry);
            notifCollection.applyRanking(rankingMap);
            notifCollection.dispatchEventsAndRebuildList("onNotificationRemoved");
        }

        @Override // com.android.systemui.statusbar.NotificationListener.NotificationHandler
        public final void onNotificationsInitialized() {
            ((SystemClockImpl) NotifCollection.this.mClock).getClass();
            android.os.SystemClock.uptimeMillis();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class FutureDismissal implements Runnable {
        public boolean mDidRun;
        public boolean mDidSystemServerCancel;
        public final NotificationEntry mEntry;
        public final String mLabel;
        public final OnUserInteractionCallbackImpl$$ExternalSyntheticLambda0 mStatsCreator;
        public final NotificationEntry mSummaryToDismiss;

        public FutureDismissal(NotificationEntry notificationEntry, int i, OnUserInteractionCallbackImpl$$ExternalSyntheticLambda0 onUserInteractionCallbackImpl$$ExternalSyntheticLambda0) {
            this.mEntry = notificationEntry;
            this.mStatsCreator = onUserInteractionCallbackImpl$$ExternalSyntheticLambda0;
            NotifCollection.this.getClass();
            final String groupKey = notificationEntry.mSbn.getGroupKey();
            NotificationEntry notificationEntry2 = null;
            if (((ArrayMap) NotifCollection.this.mNotificationSet).get(notificationEntry.mKey) == notificationEntry) {
                final int i2 = 0;
                final int i3 = 0;
                if (((ArrayMap) NotifCollection.this.mNotificationSet).values().stream().filter(new Predicate() { // from class: com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda7
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        int i4 = i2;
                        String str = groupKey;
                        NotificationEntry notificationEntry3 = (NotificationEntry) obj;
                        switch (i4) {
                        }
                        return Objects.equals(notificationEntry3.mSbn.getGroupKey(), str);
                    }
                }).filter(new Predicate() { // from class: com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda8
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        NotificationEntry notificationEntry3 = (NotificationEntry) obj;
                        switch (i3) {
                            case 0:
                                return !notificationEntry3.mSbn.getNotification().isGroupSummary();
                            default:
                                return notificationEntry3.mSbn.getNotification().isGroupSummary();
                        }
                    }
                }).count() == 1) {
                    final String groupKey2 = notificationEntry.mSbn.getGroupKey();
                    final int i4 = 1;
                    final int i5 = 1;
                    NotificationEntry notificationEntry3 = (NotificationEntry) ((ArrayMap) NotifCollection.this.mNotificationSet).values().stream().filter(new Predicate() { // from class: com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda7
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            int i42 = i4;
                            String str = groupKey2;
                            NotificationEntry notificationEntry32 = (NotificationEntry) obj;
                            switch (i42) {
                            }
                            return Objects.equals(notificationEntry32.mSbn.getGroupKey(), str);
                        }
                    }).filter(new Predicate() { // from class: com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda8
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            NotificationEntry notificationEntry32 = (NotificationEntry) obj;
                            switch (i5) {
                                case 0:
                                    return !notificationEntry32.mSbn.getNotification().isGroupSummary();
                                default:
                                    return notificationEntry32.mSbn.getNotification().isGroupSummary();
                            }
                        }
                    }).findFirst().orElse(null);
                    if (notificationEntry3 != null && !NotifCollection.this.mDismissibilityProvider.nonDismissableEntryKeys.contains(notificationEntry3.mKey)) {
                        notificationEntry2 = notificationEntry3;
                    }
                }
            }
            this.mSummaryToDismiss = notificationEntry2;
            this.mLabel = "<FutureDismissal@" + Integer.toHexString(hashCode()) + " entry=" + NotificationUtils.logKey(notificationEntry) + " reason=" + NotifCollectionLoggerKt.cancellationReasonDebugString(i) + " summary=" + NotificationUtils.logKey(notificationEntry2) + ">";
        }

        @Override // java.lang.Runnable
        public final void run() {
            Assert.isMainThread();
            if (this.mDidRun) {
                NotifCollection.this.mLogger.logFutureDismissalDoubleRun(this);
                return;
            }
            this.mDidRun = true;
            NotifCollection.this.mFutureDismissals.remove(this.mEntry.mKey);
            NotificationEntry entry = NotifCollection.this.getEntry(this.mEntry.mKey);
            DismissedByUserStats createDismissedByUserStats = this.mStatsCreator.createDismissedByUserStats(this.mEntry);
            NotificationEntry notificationEntry = this.mSummaryToDismiss;
            if (notificationEntry != null) {
                NotificationEntry entry2 = NotifCollection.this.getEntry(notificationEntry.mKey);
                if (entry2 == this.mSummaryToDismiss) {
                    NotifCollection.this.mLogger.logFutureDismissalDismissing(this, "summary");
                    NotifCollection notifCollection = NotifCollection.this;
                    NotificationEntry notificationEntry2 = this.mSummaryToDismiss;
                    notifCollection.dismissNotification(notificationEntry2, this.mStatsCreator.createDismissedByUserStats(notificationEntry2));
                } else {
                    NotifCollection.this.mLogger.logFutureDismissalMismatchedEntry(this, "summary", entry2);
                }
            }
            if (this.mDidSystemServerCancel) {
                NotifCollection.this.mLogger.logFutureDismissalAlreadyCancelledByServer(this);
            } else if (entry != this.mEntry) {
                NotifCollection.this.mLogger.logFutureDismissalMismatchedEntry(this, "entry", entry);
            } else {
                NotifCollection.this.mLogger.logFutureDismissalDismissing(this, "entry");
                NotifCollection.this.dismissNotification(this.mEntry, createDismissedByUserStats);
            }
        }
    }

    static {
        TimeUnit.SECONDS.toMillis(5L);
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda0] */
    public NotifCollection(IStatusBarService iStatusBarService, SystemClock systemClock, NotifCollectionLogger notifCollectionLogger, Handler handler, Executor executor, LogBufferEulogizer logBufferEulogizer, DumpManager dumpManager, NotificationDismissibilityProviderImpl notificationDismissibilityProviderImpl) {
        ArrayMap arrayMap = new ArrayMap();
        this.mNotificationSet = arrayMap;
        this.mReadOnlyNotificationSet = Collections.unmodifiableCollection(arrayMap.values());
        this.mFutureDismissals = new HashMap();
        this.mNotifCollectionListeners = new NamedListenerSet();
        this.mLifetimeExtenders = new ArrayList();
        this.mDismissInterceptors = new ArrayList();
        this.mEventQueue = new ArrayDeque();
        this.mRebuildListRunnable = new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                NotifCollection notifCollection = NotifCollection.this;
                ShadeListBuilder.AnonymousClass1 anonymousClass1 = notifCollection.mBuildListener;
                if (anonymousClass1 != null) {
                    Collection collection = notifCollection.mReadOnlyNotificationSet;
                    Assert.isMainThread();
                    ArrayList arrayList = new ArrayList(collection);
                    ShadeListBuilder shadeListBuilder = ShadeListBuilder.this;
                    shadeListBuilder.mPendingEntries = arrayList;
                    shadeListBuilder.mLogger.logOnBuildList("asynchronousUpdate");
                    shadeListBuilder.rebuildListIfBefore(1);
                }
            }
        };
        this.mAttached = false;
        this.mNotifHandler = new AnonymousClass1();
        this.mStatusBarService = iStatusBarService;
        this.mClock = systemClock;
        this.mLogger = notifCollectionLogger;
        this.mMainHandler = handler;
        this.mBgExecutor = executor;
        this.mEulogizer = logBufferEulogizer;
        this.mDumpManager = dumpManager;
        this.mInconsistencyTracker = new NotifCollectionInconsistencyTracker(notifCollectionLogger);
        this.mDismissibilityProvider = notificationDismissibilityProviderImpl;
    }

    public static boolean hasFlag(NotificationEntry notificationEntry, int i) {
        return (notificationEntry.mSbn.getNotification().flags & i) != 0;
    }

    public static boolean shouldAutoDismissChildren(NotificationEntry notificationEntry, String str) {
        return (!notificationEntry.mSbn.getGroupKey().equals(str) || notificationEntry.mSbn.getNotification().isGroupSummary() || hasFlag(notificationEntry, 2) || hasFlag(notificationEntry, 4096) || hasFlag(notificationEntry, 32) || (notificationEntry.mRanking.getChannel() != null && notificationEntry.mRanking.getChannel().isImportantConversation()) || notificationEntry.mDismissState == NotificationEntry.DismissState.DISMISSED) ? false : true;
    }

    public final void applyRanking(NotificationListenerService.RankingMap rankingMap) {
        ArrayMap arrayMap = null;
        for (NotificationEntry notificationEntry : ((ArrayMap) this.mNotificationSet).values()) {
            if (!notificationEntry.isCanceled()) {
                NotificationListenerService.Ranking ranking = new NotificationListenerService.Ranking();
                String str = notificationEntry.mKey;
                if (rankingMap.getRanking(str, ranking)) {
                    notificationEntry.setRanking(ranking);
                    String overrideGroupKey = ranking.getOverrideGroupKey();
                    if (!Objects.equals(notificationEntry.mSbn.getOverrideGroupKey(), overrideGroupKey)) {
                        notificationEntry.mSbn.setOverrideGroupKey(overrideGroupKey);
                    }
                } else {
                    if (arrayMap == null) {
                        arrayMap = new ArrayMap();
                    }
                    arrayMap.put(str, notificationEntry);
                }
            }
        }
        NotifCollectionInconsistencyTracker notifCollectionInconsistencyTracker = this.mInconsistencyTracker;
        notifCollectionInconsistencyTracker.logNewMissingNotifications(rankingMap);
        notifCollectionInconsistencyTracker.maybeLogInconsistentRankings(notifCollectionInconsistencyTracker.notificationsWithoutRankings, arrayMap != null ? arrayMap : MapsKt.emptyMap(), rankingMap);
        Set keySet = arrayMap != null ? arrayMap.keySet() : null;
        if (keySet == null) {
            keySet = EmptySet.INSTANCE;
        }
        notifCollectionInconsistencyTracker.notificationsWithoutRankings = keySet;
        if (arrayMap != null) {
            for (NotificationEntry notificationEntry2 : arrayMap.values()) {
                notificationEntry2.mCancellationReason = 0;
                tryRemoveNotification(notificationEntry2);
            }
        }
        this.mEventQueue.add(new RankingAppliedEvent("onRankingApplied"));
    }

    public final void cancelDismissInterception(NotificationEntry notificationEntry) {
        this.mAmDispatchingToOtherCode = true;
        Iterator it = notificationEntry.mDismissInterceptors.iterator();
        while (it.hasNext()) {
            BubbleCoordinator.this.mInterceptedDismissalEntries.remove(notificationEntry.mKey);
        }
        this.mAmDispatchingToOtherCode = false;
        notificationEntry.mDismissInterceptors.clear();
    }

    public final void cancelLifetimeExtension(NotificationEntry notificationEntry) {
        this.mAmDispatchingToOtherCode = true;
        Iterator it = notificationEntry.mLifetimeExtenders.iterator();
        while (it.hasNext()) {
            ((NotifLifetimeExtender) it.next()).cancelLifetimeExtension$1(notificationEntry);
        }
        this.mAmDispatchingToOtherCode = false;
        notificationEntry.mLifetimeExtenders.clear();
    }

    public final void checkForReentrantCall() {
        if (this.mAmDispatchingToOtherCode) {
            IllegalStateException illegalStateException = new IllegalStateException("Reentrant call detected");
            this.mEulogizer.record(illegalStateException);
            throw illegalStateException;
        }
    }

    public final void dismissNotification(NotificationEntry notificationEntry, DismissedByUserStats dismissedByUserStats) {
        dismissNotifications(List.of(new Pair(notificationEntry, dismissedByUserStats)));
    }

    public final void dismissNotifications(List list) {
        Assert.isMainThread();
        checkForReentrantCall();
        final int size = list.size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            final NotificationEntry notificationEntry = (NotificationEntry) ((Pair) list.get(i)).first;
            final DismissedByUserStats dismissedByUserStats = (DismissedByUserStats) ((Pair) list.get(i)).second;
            Objects.requireNonNull(dismissedByUserStats);
            NotificationEntry notificationEntry2 = (NotificationEntry) ((ArrayMap) this.mNotificationSet).get(notificationEntry.mKey);
            NotifCollectionLogger notifCollectionLogger = this.mLogger;
            if (notificationEntry2 == null) {
                notifCollectionLogger.logDismissNonExistentNotif(notificationEntry, i, size);
            } else {
                if (notificationEntry != notificationEntry2) {
                    IllegalStateException illegalStateException = new IllegalStateException("Invalid entry: different stored and dismissed entries for " + NotificationUtils.logKey(notificationEntry) + " (" + i + "/" + size + ") dismissed=@" + Integer.toHexString(notificationEntry.hashCode()) + " stored=@" + Integer.toHexString(notificationEntry2.hashCode()));
                    this.mEulogizer.record(illegalStateException);
                    throw illegalStateException;
                }
                NotificationEntry.DismissState dismissState = notificationEntry.mDismissState;
                if (dismissState == NotificationEntry.DismissState.DISMISSED) {
                    notifCollectionLogger.logDismissAlreadyDismissedNotif(notificationEntry, i, size);
                } else {
                    if (dismissState == NotificationEntry.DismissState.PARENT_DISMISSED) {
                        notifCollectionLogger.logDismissAlreadyParentDismissedNotif(notificationEntry, i, size);
                    }
                    updateDismissInterceptors(notificationEntry);
                    if (((ArrayList) notificationEntry.mDismissInterceptors).size() > 0) {
                        notifCollectionLogger.logNotifDismissedIntercepted(notificationEntry, i, size);
                    } else {
                        arrayList.add(notificationEntry);
                        if (!notificationEntry.isCanceled()) {
                            final int i2 = i;
                            this.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.notification.collection.NotifCollection$$ExternalSyntheticLambda3
                                @Override // java.lang.Runnable
                                public final void run() {
                                    NotifCollection notifCollection = NotifCollection.this;
                                    NotificationEntry notificationEntry3 = notificationEntry;
                                    DismissedByUserStats dismissedByUserStats2 = dismissedByUserStats;
                                    int i3 = i2;
                                    int i4 = size;
                                    notifCollection.getClass();
                                    try {
                                        notifCollection.mStatusBarService.onNotificationClear(notificationEntry3.mSbn.getPackageName(), notificationEntry3.mSbn.getUser().getIdentifier(), notificationEntry3.mSbn.getKey(), dismissedByUserStats2.dismissalSurface, 1, dismissedByUserStats2.notificationVisibility);
                                    } catch (RemoteException e) {
                                        notifCollection.mLogger.logRemoteExceptionOnNotificationClear(notificationEntry3, i3, i4, e);
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
        locallyDismissNotifications(arrayList);
        dispatchEventsAndRebuildList("dismissNotifications");
    }

    public final void dispatchEvents() {
        Trace.beginSection("NotifCollection.dispatchEvents");
        this.mAmDispatchingToOtherCode = true;
        while (!this.mEventQueue.isEmpty()) {
            NotifEvent notifEvent = (NotifEvent) ((ArrayDeque) this.mEventQueue).remove();
            NamedListenerSet namedListenerSet = this.mNotifCollectionListeners;
            notifEvent.getClass();
            boolean isEnabled = Trace.isEnabled();
            if (isEnabled) {
                TraceUtilsKt.beginSlice(notifEvent.traceName);
            }
            try {
                Iterator it = namedListenerSet.listeners.iterator();
                while (it.hasNext()) {
                    NamedListenerSet.NamedListener namedListener = (NamedListenerSet.NamedListener) it.next();
                    String str = namedListener.name;
                    Object obj = namedListener.listener;
                    isEnabled = Trace.isEnabled();
                    if (isEnabled) {
                        TraceUtilsKt.beginSlice(str);
                    }
                    try {
                        notifEvent.dispatchToListener((NotifCollectionListener) obj);
                        if (isEnabled) {
                            TraceUtilsKt.endSlice();
                        }
                    } finally {
                        if (isEnabled) {
                            TraceUtilsKt.endSlice();
                        }
                    }
                }
                if (isEnabled) {
                    TraceUtilsKt.endSlice();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.mAmDispatchingToOtherCode = false;
        Trace.endSection();
    }

    public final void dispatchEventsAndRebuildList(String str) {
        Trace.beginSection("NotifCollection.dispatchEventsAndRebuildList");
        Handler handler = this.mMainHandler;
        NotifCollection$$ExternalSyntheticLambda0 notifCollection$$ExternalSyntheticLambda0 = this.mRebuildListRunnable;
        if (handler.hasCallbacks(notifCollection$$ExternalSyntheticLambda0)) {
            handler.removeCallbacks(notifCollection$$ExternalSyntheticLambda0);
        }
        dispatchEvents();
        ShadeListBuilder.AnonymousClass1 anonymousClass1 = this.mBuildListener;
        if (anonymousClass1 != null) {
            Collection collection = this.mReadOnlyNotificationSet;
            Assert.isMainThread();
            ArrayList arrayList = new ArrayList(collection);
            ShadeListBuilder shadeListBuilder = ShadeListBuilder.this;
            shadeListBuilder.mPendingEntries = arrayList;
            shadeListBuilder.mLogger.logOnBuildList(str);
            shadeListBuilder.rebuildListIfBefore(1);
        }
        Trace.endSection();
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        Assert.isMainThread();
        ArrayList arrayList = new ArrayList(this.mReadOnlyNotificationSet);
        arrayList.sort(Comparator.comparing(new NotifCollection$$ExternalSyntheticLambda1()));
        printWriter.println("\tNotifCollection unsorted/unfiltered notifications: " + arrayList.size());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            ListDumper.dumpEntry((ListEntry) arrayList.get(i), Integer.toString(i), "\t\t", sb, false, false);
        }
        printWriter.println(sb.toString());
        NotifCollectionInconsistencyTracker notifCollectionInconsistencyTracker = this.mInconsistencyTracker;
        notifCollectionInconsistencyTracker.getClass();
        IndentingPrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        Set set = notifCollectionInconsistencyTracker.notificationsWithoutRankings;
        asIndenting.append("notificationsWithoutRankings").append((CharSequence) ": ").println(set.size());
        asIndenting.increaseIndent();
        try {
            Iterator it = set.iterator();
            while (it.hasNext()) {
                asIndenting.println(it.next());
            }
            asIndenting.decreaseIndent();
            Set set2 = notifCollectionInconsistencyTracker.missingNotifications;
            asIndenting.append("missingNotifications").append((CharSequence) ": ").println(set2.size());
            asIndenting.increaseIndent();
            try {
                Iterator it2 = set2.iterator();
                while (it2.hasNext()) {
                    asIndenting.println(it2.next());
                }
            } finally {
            }
        } finally {
        }
    }

    @Override // com.android.systemui.statusbar.notification.collection.PipelineDumpable
    public final void dumpPipeline(PipelineDumper pipelineDumper) {
        pipelineDumper.dump(this.mNotifCollectionListeners, "notifCollectionListeners");
        pipelineDumper.dump(this.mLifetimeExtenders, "lifetimeExtenders");
        pipelineDumper.dump(this.mDismissInterceptors, "dismissInterceptors");
        pipelineDumper.dump(this.mBuildListener, "buildListener");
    }

    public final NotificationEntry getEntry(String str) {
        return (NotificationEntry) ((ArrayMap) this.mNotificationSet).get(str);
    }

    public final void locallyDismissNotifications(List list) {
        NotifCollectionLogger notifCollectionLogger;
        ArrayList<NotificationEntry> arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) list;
        int size = arrayList2.size();
        int i = 0;
        while (true) {
            int size2 = arrayList2.size();
            notifCollectionLogger = this.mLogger;
            if (i >= size2) {
                break;
            }
            NotificationEntry notificationEntry = (NotificationEntry) arrayList2.get(i);
            NotificationEntry notificationEntry2 = (NotificationEntry) ((ArrayMap) this.mNotificationSet).get(notificationEntry.mKey);
            if (notificationEntry2 == null) {
                notifCollectionLogger.logLocallyDismissNonExistentNotif(notificationEntry, i, size);
            } else if (notificationEntry2 != notificationEntry) {
                notifCollectionLogger.logLocallyDismissMismatchedEntry(i, size, notificationEntry, notificationEntry2);
            }
            NotificationEntry.DismissState dismissState = notificationEntry.mDismissState;
            NotificationEntry.DismissState dismissState2 = NotificationEntry.DismissState.DISMISSED;
            NotificationEntry.DismissState dismissState3 = NotificationEntry.DismissState.PARENT_DISMISSED;
            if (dismissState == dismissState2) {
                notifCollectionLogger.logLocallyDismissAlreadyDismissedNotif(notificationEntry, i, size);
            } else if (dismissState == dismissState3) {
                notifCollectionLogger.logLocallyDismissAlreadyParentDismissedNotif(notificationEntry, i, size);
            }
            notificationEntry.mDismissState = dismissState2;
            notifCollectionLogger.logLocallyDismissed(notificationEntry, i, size);
            if (notificationEntry.isCanceled()) {
                arrayList.add(notificationEntry);
            } else if (notificationEntry.mSbn.getNotification().isGroupSummary()) {
                for (NotificationEntry notificationEntry3 : ((ArrayMap) this.mNotificationSet).values()) {
                    if (shouldAutoDismissChildren(notificationEntry3, notificationEntry.mSbn.getGroupKey())) {
                        NotificationEntry.DismissState dismissState4 = notificationEntry3.mDismissState;
                        if (dismissState4 == dismissState2) {
                            notifCollectionLogger.logLocallyDismissAlreadyDismissedChild(i, size, notificationEntry3, notificationEntry);
                        } else if (dismissState4 == dismissState3) {
                            notifCollectionLogger.logLocallyDismissAlreadyParentDismissedChild(i, size, notificationEntry3, notificationEntry);
                        }
                        notificationEntry3.mDismissState = dismissState3;
                        notifCollectionLogger.logLocallyDismissedChild(i, size, notificationEntry3, notificationEntry);
                        if (notificationEntry3.isCanceled()) {
                            arrayList.add(notificationEntry3);
                        }
                    }
                }
            }
            i++;
        }
        for (NotificationEntry notificationEntry4 : arrayList) {
            notifCollectionLogger.logLocallyDismissedAlreadyCanceledEntry(notificationEntry4);
            tryRemoveNotification(notificationEntry4);
        }
    }

    public final void postNotification(StatusBarNotification statusBarNotification, NotificationListenerService.Ranking ranking) {
        NotificationEntry notificationEntry = (NotificationEntry) ((ArrayMap) this.mNotificationSet).get(statusBarNotification.getKey());
        NotifCollectionLogger notifCollectionLogger = this.mLogger;
        if (notificationEntry == null) {
            ((SystemClockImpl) this.mClock).getClass();
            NotificationEntry notificationEntry2 = new NotificationEntry(statusBarNotification, ranking, android.os.SystemClock.uptimeMillis());
            this.mEventQueue.add(new InitEntryEvent(notificationEntry2));
            this.mEventQueue.add(new BindEntryEvent(notificationEntry2, statusBarNotification));
            ((ArrayMap) this.mNotificationSet).put(statusBarNotification.getKey(), notificationEntry2);
            notifCollectionLogger.logNotifPosted(notificationEntry2);
            this.mEventQueue.add(new EntryAddedEvent(notificationEntry2));
            return;
        }
        NotificationEntry.DismissState dismissState = notificationEntry.mDismissState;
        NotificationEntry.DismissState dismissState2 = NotificationEntry.DismissState.NOT_DISMISSED;
        if (dismissState == dismissState2) {
            notifCollectionLogger.logCancelLocalDismissalNotDismissedNotif(notificationEntry);
        } else {
            notificationEntry.mDismissState = dismissState2;
            if (notificationEntry.mSbn.getNotification().isGroupSummary()) {
                for (NotificationEntry notificationEntry3 : ((ArrayMap) this.mNotificationSet).values()) {
                    if (notificationEntry3.mSbn.getGroupKey().equals(notificationEntry.mSbn.getGroupKey()) && notificationEntry3.mDismissState == NotificationEntry.DismissState.PARENT_DISMISSED) {
                        notificationEntry3.mDismissState = dismissState2;
                    }
                }
            }
        }
        cancelLifetimeExtension(notificationEntry);
        cancelDismissInterception(notificationEntry);
        notificationEntry.mCancellationReason = -1;
        notificationEntry.setSbn(statusBarNotification);
        this.mEventQueue.add(new BindEntryEvent(notificationEntry, statusBarNotification));
        notifCollectionLogger.logNotifUpdated(notificationEntry);
        this.mEventQueue.add(new EntryUpdatedEvent(notificationEntry, true));
    }

    public final boolean tryRemoveNotification(NotificationEntry notificationEntry) {
        NotificationEntry notificationEntry2 = (NotificationEntry) ((ArrayMap) this.mNotificationSet).get(notificationEntry.mKey);
        if (notificationEntry2 == null) {
            Log.wtf("NotifCollection", "TRY REMOVE non-existent notification " + NotificationUtils.logKey(notificationEntry));
            return false;
        }
        LogBufferEulogizer logBufferEulogizer = this.mEulogizer;
        if (notificationEntry2 != notificationEntry) {
            IllegalStateException illegalStateException = new IllegalStateException("Mismatched stored and tryRemoved entries for key " + NotificationUtils.logKey(notificationEntry) + ": stored=@" + Integer.toHexString(notificationEntry2.hashCode()) + " tryRemoved=@" + Integer.toHexString(notificationEntry.hashCode()));
            logBufferEulogizer.record(illegalStateException);
            throw illegalStateException;
        }
        if (!notificationEntry.isCanceled()) {
            IllegalStateException illegalStateException2 = new IllegalStateException("Cannot remove notification " + NotificationUtils.logKey(notificationEntry) + ": has not been marked for removal");
            logBufferEulogizer.record(illegalStateException2);
            throw illegalStateException2;
        }
        boolean z = notificationEntry.mDismissState != NotificationEntry.DismissState.NOT_DISMISSED;
        int i = notificationEntry.mCancellationReason;
        boolean z2 = i == 1 || i == 2;
        NotifCollectionLogger notifCollectionLogger = this.mLogger;
        if (z || z2) {
            cancelLifetimeExtension(notificationEntry);
        } else {
            notificationEntry.mLifetimeExtenders.clear();
            this.mAmDispatchingToOtherCode = true;
            for (NotifLifetimeExtender notifLifetimeExtender : this.mLifetimeExtenders) {
                if (notifLifetimeExtender.maybeExtendLifetime(notificationEntry, notificationEntry.mCancellationReason)) {
                    notifCollectionLogger.logLifetimeExtended(notificationEntry, notifLifetimeExtender);
                    notificationEntry.mLifetimeExtenders.add(notifLifetimeExtender);
                }
            }
            this.mAmDispatchingToOtherCode = false;
        }
        if (((ArrayList) notificationEntry.mLifetimeExtenders).size() > 0) {
            return false;
        }
        notifCollectionLogger.logNotifReleased(notificationEntry);
        ArrayMap arrayMap = (ArrayMap) this.mNotificationSet;
        String str = notificationEntry.mKey;
        arrayMap.remove(str);
        cancelDismissInterception(notificationEntry);
        this.mEventQueue.add(new EntryRemovedEvent(notificationEntry, notificationEntry.mCancellationReason));
        this.mEventQueue.add(new CleanUpEntryEvent(notificationEntry));
        FutureDismissal futureDismissal = (FutureDismissal) this.mFutureDismissals.remove(str);
        if (futureDismissal != null) {
            int i2 = notificationEntry.mCancellationReason;
            Assert.isMainThread();
            if (futureDismissal.mDidSystemServerCancel) {
                NotifCollection.this.mLogger.logFutureDismissalDoubleCancelledByServer(futureDismissal);
            } else {
                NotifCollection.this.mLogger.logFutureDismissalGotSystemServerCancel(futureDismissal, i2);
                futureDismissal.mDidSystemServerCancel = true;
            }
        }
        return true;
    }

    public final void updateDismissInterceptors(NotificationEntry notificationEntry) {
        ArrayList arrayList;
        notificationEntry.mDismissInterceptors.clear();
        this.mAmDispatchingToOtherCode = true;
        for (BubbleCoordinator.AnonymousClass2 anonymousClass2 : this.mDismissInterceptors) {
            BubbleCoordinator bubbleCoordinator = BubbleCoordinator.this;
            boolean isPresent = bubbleCoordinator.mBubblesManagerOptional.isPresent();
            String str = notificationEntry.mKey;
            if (isPresent) {
                BubblesManager bubblesManager = (BubblesManager) bubbleCoordinator.mBubblesManagerOptional.get();
                bubblesManager.getClass();
                List attachedNotifChildren = notificationEntry.getAttachedNotifChildren();
                Object obj = null;
                if (attachedNotifChildren != null) {
                    arrayList = new ArrayList();
                    int i = 0;
                    while (true) {
                        ArrayList arrayList2 = (ArrayList) attachedNotifChildren;
                        if (i >= arrayList2.size()) {
                            break;
                        }
                        arrayList.add(bubblesManager.notifToBubbleEntry((NotificationEntry) arrayList2.get(i)));
                        i++;
                    }
                } else {
                    arrayList = null;
                }
                BubbleEntry notifToBubbleEntry = bubblesManager.notifToBubbleEntry(notificationEntry);
                BubblesManager$$ExternalSyntheticLambda2 bubblesManager$$ExternalSyntheticLambda2 = new BubblesManager$$ExternalSyntheticLambda2(bubblesManager, attachedNotifChildren, notificationEntry);
                Executor executor = bubblesManager.mSysuiMainExecutor;
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) bubblesManager.mBubbles;
                bubblesImpl.getClass();
                BubbleController$BubblesImpl$$ExternalSyntheticLambda0 bubbleController$BubblesImpl$$ExternalSyntheticLambda0 = new BubbleController$BubblesImpl$$ExternalSyntheticLambda0(executor, bubblesManager$$ExternalSyntheticLambda2);
                ShellExecutor shellExecutor = BubbleController.this.mMainExecutor;
                final BubbleController$BubblesImpl$$ExternalSyntheticLambda1 bubbleController$BubblesImpl$$ExternalSyntheticLambda1 = new BubbleController$BubblesImpl$$ExternalSyntheticLambda1(bubblesImpl, notifToBubbleEntry, arrayList, bubbleController$BubblesImpl$$ExternalSyntheticLambda0);
                shellExecutor.getClass();
                final Object[] objArr = (Object[]) Array.newInstance((Class<?>) Boolean.class, 1);
                final CountDownLatch countDownLatch = new CountDownLatch(1);
                ((HandlerExecutor) shellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.common.ShellExecutor$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Object[] objArr2 = objArr;
                        BubbleController$BubblesImpl$$ExternalSyntheticLambda1 bubbleController$BubblesImpl$$ExternalSyntheticLambda12 = bubbleController$BubblesImpl$$ExternalSyntheticLambda1;
                        CountDownLatch countDownLatch2 = countDownLatch;
                        objArr2[0] = bubbleController$BubblesImpl$$ExternalSyntheticLambda12.get();
                        countDownLatch2.countDown();
                    }
                });
                try {
                    countDownLatch.await();
                    obj = objArr[0];
                } catch (InterruptedException unused) {
                }
                if (((Boolean) obj).booleanValue()) {
                    bubbleCoordinator.mInterceptedDismissalEntries.add(str);
                    notificationEntry.mDismissInterceptors.add(anonymousClass2);
                }
            }
            bubbleCoordinator.mInterceptedDismissalEntries.remove(str);
        }
        this.mAmDispatchingToOtherCode = false;
    }
}

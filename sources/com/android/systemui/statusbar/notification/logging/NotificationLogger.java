package com.android.systemui.statusbar.notification.logging;

import android.os.Handler;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.statusbar.NotificationVisibility;
import com.android.systemui.CoreStartable;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.scene.domain.interactor.WindowRootViewVisibilityInteractor;
import com.android.systemui.shared.system.SysUiStatsLog;
import com.android.systemui.statusbar.NotificationListener;
import com.android.systemui.statusbar.notification.collection.NotifLiveDataStoreImpl;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.collection.provider.NotificationVisibilityProviderImpl;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.statusbar.notification.logging.nano.Notifications$NotificationList;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.ExpandableViewState;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.ui.view.NotificationRowStatsLogger;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.google.protobuf.nano.MessageNano;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationLogger implements StatusBarStateController.StateListener, CoreStartable, NotificationRowStatsLogger {
    public final ExpansionStateLogger mExpansionStateLogger;
    public final JavaAdapter mJavaAdapter;
    public long mLastVisibilityReportUptimeMs;
    public NotificationStackScrollLayoutController.NotificationListContainerImpl mListContainer;
    public final NotifLiveDataStoreImpl mNotifLiveDataStore;
    public final NotificationListener mNotificationListener;
    public final NotificationPanelLoggerImpl mNotificationPanelLogger;
    public final Executor mUiBgExecutor;
    public final NotificationVisibilityProvider mVisibilityProvider;
    public final WindowRootViewVisibilityInteractor mWindowRootViewVisibilityInteractor;
    public final ArraySet mCurrentlyVisibleNotifications = new ArraySet();
    public final Handler mHandler = new Handler();
    public final Object mDozingLock = new Object();
    public Boolean mLockscreen = null;
    public boolean mLogging = false;
    public Runnable mVisibilityReporter = new Runnable() { // from class: com.android.systemui.statusbar.notification.logging.NotificationLogger.1
        public final ArraySet mTmpNewlyVisibleNotifications = new ArraySet();
        public final ArraySet mTmpCurrentlyVisibleNotifications = new ArraySet();
        public final ArraySet mTmpNoLongerVisibleNotifications = new ArraySet();

        @Override // java.lang.Runnable
        public final void run() {
            NotificationLogger.this.mLastVisibilityReportUptimeMs = SystemClock.uptimeMillis();
            List list = (List) NotificationLogger.this.mNotifLiveDataStore.activeNotifList.atomicValue.get();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                NotificationEntry notificationEntry = (NotificationEntry) list.get(i);
                String key = notificationEntry.mSbn.getKey();
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
                boolean isInVisibleLocation$1 = NotificationStackScrollLayoutController.isInVisibleLocation$1(notificationEntry);
                NotificationVisibility obtain = NotificationVisibility.obtain(key, i, size, isInVisibleLocation$1, NotificationLogger.getNotificationLocation(notificationEntry));
                boolean contains = NotificationLogger.this.mCurrentlyVisibleNotifications.contains(obtain);
                if (isInVisibleLocation$1) {
                    this.mTmpCurrentlyVisibleNotifications.add(obtain);
                    if (!contains) {
                        this.mTmpNewlyVisibleNotifications.add(obtain);
                    }
                } else {
                    obtain.recycle();
                }
            }
            this.mTmpNoLongerVisibleNotifications.addAll(NotificationLogger.this.mCurrentlyVisibleNotifications);
            this.mTmpNoLongerVisibleNotifications.removeAll(this.mTmpCurrentlyVisibleNotifications);
            NotificationLogger.this.logNotificationVisibilityChanges(this.mTmpNewlyVisibleNotifications, this.mTmpNoLongerVisibleNotifications);
            NotificationLogger.recycleAllVisibilityObjects(NotificationLogger.this.mCurrentlyVisibleNotifications);
            NotificationLogger.this.mCurrentlyVisibleNotifications.addAll(this.mTmpCurrentlyVisibleNotifications);
            ExpansionStateLogger expansionStateLogger = NotificationLogger.this.mExpansionStateLogger;
            ArraySet arraySet = this.mTmpCurrentlyVisibleNotifications;
            expansionStateLogger.onVisibilityChanged(arraySet, arraySet);
            Trace.traceCounter(4096L, "Notifications [Active]", size);
            Trace.traceCounter(4096L, "Notifications [Visible]", NotificationLogger.this.mCurrentlyVisibleNotifications.size());
            NotificationLogger notificationLogger = NotificationLogger.this;
            ArraySet arraySet2 = this.mTmpNoLongerVisibleNotifications;
            notificationLogger.getClass();
            NotificationLogger.recycleAllVisibilityObjects(arraySet2);
            this.mTmpCurrentlyVisibleNotifications.clear();
            this.mTmpNewlyVisibleNotifications.clear();
            this.mTmpNoLongerVisibleNotifications.clear();
        }
    };
    public final IStatusBarService mBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ExpansionStateLogger {
        public final Executor mUiBgExecutor;
        public final Map mExpansionStates = new ArrayMap();
        public final Map mLoggedExpansionState = new ArrayMap();
        IStatusBarService mBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class State {
            public Boolean mIsExpanded;
            public Boolean mIsUserAction;
            public Boolean mIsVisible;
            public NotificationVisibility.NotificationLocation mLocation;
        }

        public ExpansionStateLogger(Executor executor) {
            this.mUiBgExecutor = executor;
        }

        public final State getState(String str) {
            State state = (State) ((ArrayMap) this.mExpansionStates).get(str);
            if (state != null) {
                return state;
            }
            State state2 = new State();
            ((ArrayMap) this.mExpansionStates).put(str, state2);
            return state2;
        }

        public final void maybeNotifyOnNotificationExpansionChanged(String str, State state) {
            Boolean bool;
            if (state.mIsUserAction == null || state.mIsExpanded == null || (bool = state.mIsVisible) == null || state.mLocation == null || !bool.booleanValue()) {
                return;
            }
            Boolean bool2 = (Boolean) ((ArrayMap) this.mLoggedExpansionState).get(str);
            if (bool2 != null || state.mIsExpanded.booleanValue()) {
                if (bool2 == null || !Objects.equals(state.mIsExpanded, bool2)) {
                    ((ArrayMap) this.mLoggedExpansionState).put(str, state.mIsExpanded);
                    State state2 = new State();
                    state2.mIsUserAction = state.mIsUserAction;
                    state2.mIsExpanded = state.mIsExpanded;
                    state2.mIsVisible = state.mIsVisible;
                    state2.mLocation = state.mLocation;
                    this.mUiBgExecutor.execute(new NotificationLogger$$ExternalSyntheticLambda1(this, str, state2));
                }
            }
        }

        public void onEntryRemoved(String str) {
            ((ArrayMap) this.mExpansionStates).remove(str);
            ((ArrayMap) this.mLoggedExpansionState).remove(str);
        }

        public void onEntryUpdated(String str) {
            ((ArrayMap) this.mLoggedExpansionState).remove(str);
        }

        public void onExpansionChanged(String str, boolean z, boolean z2, NotificationVisibility.NotificationLocation notificationLocation) {
            State state = getState(str);
            state.mIsUserAction = Boolean.valueOf(z);
            state.mIsExpanded = Boolean.valueOf(z2);
            state.mLocation = notificationLocation;
            maybeNotifyOnNotificationExpansionChanged(str, state);
        }

        public void onVisibilityChanged(Collection collection, Collection collection2) {
            NotificationVisibility[] cloneVisibilitiesAsArr = NotificationLogger.cloneVisibilitiesAsArr(collection);
            NotificationVisibility[] cloneVisibilitiesAsArr2 = NotificationLogger.cloneVisibilitiesAsArr(collection2);
            for (NotificationVisibility notificationVisibility : cloneVisibilitiesAsArr) {
                State state = getState(notificationVisibility.key);
                state.mIsVisible = Boolean.TRUE;
                state.mLocation = notificationVisibility.location;
                maybeNotifyOnNotificationExpansionChanged(notificationVisibility.key, state);
            }
            for (NotificationVisibility notificationVisibility2 : cloneVisibilitiesAsArr2) {
                getState(notificationVisibility2.key).mIsVisible = Boolean.FALSE;
            }
        }
    }

    public NotificationLogger(NotificationListener notificationListener, Executor executor, NotifLiveDataStoreImpl notifLiveDataStoreImpl, NotificationVisibilityProvider notificationVisibilityProvider, NotifPipeline notifPipeline, StatusBarStateController statusBarStateController, WindowRootViewVisibilityInteractor windowRootViewVisibilityInteractor, JavaAdapter javaAdapter, ExpansionStateLogger expansionStateLogger, NotificationPanelLoggerImpl notificationPanelLoggerImpl) {
        this.mNotificationListener = notificationListener;
        this.mUiBgExecutor = executor;
        this.mNotifLiveDataStore = notifLiveDataStoreImpl;
        this.mVisibilityProvider = notificationVisibilityProvider;
        this.mExpansionStateLogger = expansionStateLogger;
        this.mNotificationPanelLogger = notificationPanelLoggerImpl;
        this.mWindowRootViewVisibilityInteractor = windowRootViewVisibilityInteractor;
        this.mJavaAdapter = javaAdapter;
        statusBarStateController.addCallback(this);
        notifPipeline.addCollectionListener(new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.logging.NotificationLogger.2
            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryRemoved(NotificationEntry notificationEntry, int i) {
                NotificationLogger.this.mExpansionStateLogger.onEntryRemoved(notificationEntry.mKey);
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryUpdated(NotificationEntry notificationEntry, boolean z) {
                NotificationLogger.this.mExpansionStateLogger.onEntryUpdated(notificationEntry.mKey);
            }
        });
    }

    public static NotificationVisibility[] cloneVisibilitiesAsArr(Collection collection) {
        NotificationVisibility[] notificationVisibilityArr = new NotificationVisibility[collection.size()];
        Iterator it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            NotificationVisibility notificationVisibility = (NotificationVisibility) it.next();
            if (notificationVisibility != null) {
                notificationVisibilityArr[i] = notificationVisibility.clone();
            }
            i++;
        }
        return notificationVisibilityArr;
    }

    public static NotificationVisibility.NotificationLocation getNotificationLocation(NotificationEntry notificationEntry) {
        ExpandableNotificationRow expandableNotificationRow;
        ExpandableViewState expandableViewState;
        if (notificationEntry == null || (expandableNotificationRow = notificationEntry.row) == null || (expandableViewState = expandableNotificationRow.mViewState) == null) {
            return NotificationVisibility.NotificationLocation.LOCATION_UNKNOWN;
        }
        int i = expandableViewState.location;
        return i != 1 ? i != 2 ? i != 4 ? i != 8 ? i != 16 ? i != 64 ? NotificationVisibility.NotificationLocation.LOCATION_UNKNOWN : NotificationVisibility.NotificationLocation.LOCATION_GONE : NotificationVisibility.NotificationLocation.LOCATION_BOTTOM_STACK_HIDDEN : NotificationVisibility.NotificationLocation.LOCATION_BOTTOM_STACK_PEEKING : NotificationVisibility.NotificationLocation.LOCATION_MAIN_AREA : NotificationVisibility.NotificationLocation.LOCATION_HIDDEN_TOP : NotificationVisibility.NotificationLocation.LOCATION_FIRST_HEADS_UP;
    }

    public static void recycleAllVisibilityObjects(ArraySet arraySet) {
        int size = arraySet.size();
        for (int i = 0; i < size; i++) {
            ((NotificationVisibility) arraySet.valueAt(i)).recycle();
        }
        arraySet.clear();
    }

    public Runnable getVisibilityReporter() {
        return this.mVisibilityReporter;
    }

    public final void logNotificationVisibilityChanges(Collection collection, Collection collection2) {
        if (collection.isEmpty() && collection2.isEmpty()) {
            return;
        }
        this.mUiBgExecutor.execute(new NotificationLogger$$ExternalSyntheticLambda1(this, cloneVisibilitiesAsArr(collection), cloneVisibilitiesAsArr(collection2)));
    }

    public void onChildLocationsChanged() {
        Handler handler = this.mHandler;
        if (handler.hasCallbacks(this.mVisibilityReporter)) {
            return;
        }
        handler.postAtTime(this.mVisibilityReporter, this.mLastVisibilityReportUptimeMs + 500);
    }

    @Override // com.android.systemui.statusbar.notification.stack.ui.view.NotificationRowStatsLogger
    public final void onNotificationExpansionChanged(int i, String str, boolean z, boolean z2) {
        this.mExpansionStateLogger.onExpansionChanged(str, z2, z, getNotificationLocation(((NotifPipeline) ((NotificationVisibilityProviderImpl) this.mVisibilityProvider).notifCollection).mNotifCollection.getEntry(str)));
    }

    @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
    public final void onStateChanged(int i) {
        synchronized (this.mDozingLock) {
            boolean z = true;
            if (i != 1 && i != 2) {
                z = false;
            }
            this.mLockscreen = Boolean.valueOf(z);
        }
    }

    public void setVisibilityReporter(Runnable runnable) {
        this.mVisibilityReporter = runnable;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mJavaAdapter.alwaysCollectFlow(this.mWindowRootViewVisibilityInteractor.isLockscreenOrShadeVisibleAndInteractive, new Consumer() { // from class: com.android.systemui.statusbar.notification.logging.NotificationLogger$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NotificationLogger notificationLogger = NotificationLogger.this;
                boolean z = false;
                if (!((Boolean) obj).booleanValue()) {
                    if (notificationLogger.mLogging) {
                        notificationLogger.mLogging = false;
                        if (!notificationLogger.mCurrentlyVisibleNotifications.isEmpty()) {
                            notificationLogger.logNotificationVisibilityChanges(Collections.emptyList(), notificationLogger.mCurrentlyVisibleNotifications);
                            NotificationLogger.recycleAllVisibilityObjects(notificationLogger.mCurrentlyVisibleNotifications);
                        }
                        notificationLogger.mHandler.removeCallbacks(notificationLogger.mVisibilityReporter);
                        NotificationStackScrollLayoutController.this.mView.mLegacyLocationsChangedListener = null;
                        return;
                    }
                    return;
                }
                if (notificationLogger.mLogging) {
                    return;
                }
                notificationLogger.mLogging = true;
                synchronized (notificationLogger.mDozingLock) {
                    try {
                        Boolean bool = notificationLogger.mLockscreen;
                        if (bool != null && bool.booleanValue()) {
                            z = true;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                NotificationPanelLoggerImpl notificationPanelLoggerImpl = notificationLogger.mNotificationPanelLogger;
                List list = (List) notificationLogger.mNotifLiveDataStore.activeNotifList.atomicValue.get();
                notificationPanelLoggerImpl.getClass();
                Notifications$NotificationList notificationProto = NotificationPanelLoggerImpl.toNotificationProto(list);
                SysUiStatsLog.write(MessageNano.toByteArray(notificationProto), (z ? NotificationPanelLogger$NotificationPanelEvent.NOTIFICATION_PANEL_OPEN_LOCKSCREEN : NotificationPanelLogger$NotificationPanelEvent.NOTIFICATION_PANEL_OPEN_STATUS_BAR).getId(), notificationProto.notifications.length);
                NotificationStackScrollLayoutController.NotificationListContainerImpl notificationListContainerImpl = notificationLogger.mListContainer;
                if (notificationListContainerImpl != null) {
                    NotificationStackScrollLayoutController.this.mView.mLegacyLocationsChangedListener = new NotificationLogger$$ExternalSyntheticLambda2(notificationLogger);
                }
                notificationLogger.onChildLocationsChanged();
            }
        });
    }
}

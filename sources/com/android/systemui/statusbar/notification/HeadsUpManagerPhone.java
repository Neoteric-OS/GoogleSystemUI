package com.android.systemui.statusbar.notification;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Region;
import android.os.Handler;
import android.util.Pools;
import android.view.View;
import androidx.collection.ArraySet;
import androidx.collection.ArraySet.ElementIterator;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.provider.OnReorderingAllowedListener;
import com.android.systemui.statusbar.notification.collection.provider.VisualStabilityProvider;
import com.android.systemui.statusbar.notification.collection.render.GroupMembershipManagerImpl;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$$ExternalSyntheticLambda5;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager;
import com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.policy.AccessibilityManagerWrapper;
import com.android.systemui.statusbar.policy.AvalancheController;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager$$ExternalSyntheticLambda5;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.HeadsUpManagerLogger;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HeadsUpManagerPhone extends BaseHeadsUpManager implements OnHeadsUpChangedListener {
    public NotificationStackScrollLayoutController$$ExternalSyntheticLambda5 mAnimationStateHandler;
    public final KeyguardBypassController mBypassController;
    public final HashSet mEntriesToRemoveAfterExpand;
    public final ArraySet mEntriesToRemoveWhenReorderingAllowed;
    public final AnonymousClass1 mEntryPool;
    public final int mExtensionTime;
    public final GroupMembershipManagerImpl mGroupMembershipManager;
    public final StateFlowImpl mHeadsUpAnimatingAway;
    public int mHeadsUpInset;
    public final StateFlowImpl mHeadsUpNotificationRows;
    public final List mHeadsUpPhoneListeners;
    public boolean mIsShadeOrQsExpanded;
    public final HeadsUpManagerPhone$$ExternalSyntheticLambda0 mOnReorderingAllowedListener;
    public boolean mReleaseOnExpandFinish;
    public int mStatusBarState;
    public final AnonymousClass3 mStatusBarStateListener;
    public final HashSet mSwipedOutKeys;
    public final StateFlowImpl mTopHeadsUpRow;
    public final Region mTouchableRegion;
    public boolean mTrackingHeadsUp;
    public final VisualStabilityProvider mVisualStabilityProvider;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.HeadsUpManagerPhone$1, reason: invalid class name */
    public final class AnonymousClass1 implements Pools.Pool {
        public final Stack mPoolObjects = new Stack();

        public AnonymousClass1() {
        }

        public final Object acquire() {
            return !this.mPoolObjects.isEmpty() ? (HeadsUpEntryPhone) this.mPoolObjects.pop() : HeadsUpManagerPhone.this.new HeadsUpEntryPhone();
        }

        public final boolean release(Object obj) {
            this.mPoolObjects.push((HeadsUpEntryPhone) obj);
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class HeadsUpEntryPhone extends BaseHeadsUpManager.HeadsUpEntry {
        public boolean extended;
        public boolean mGutsShownPinned;
        public final StateFlowImpl mIsPinned;

        public HeadsUpEntryPhone() {
            super(HeadsUpManagerPhone.this);
            this.mIsPinned = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x001e, code lost:
        
            if (com.android.systemui.statusbar.policy.BaseHeadsUpManager.hasFullScreenIntent(r1) != false) goto L18;
         */
        @Override // com.android.systemui.statusbar.policy.BaseHeadsUpManager.HeadsUpEntry
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean isSticky() {
            /*
                r2 = this;
                com.android.systemui.statusbar.notification.collection.NotificationEntry r0 = r2.mEntry
                if (r0 != 0) goto L5
                goto L21
            L5:
                boolean r0 = r0.isRowPinned()
                if (r0 == 0) goto Lf
                boolean r0 = r2.mExpanded
                if (r0 != 0) goto L28
            Lf:
                boolean r0 = r2.mRemoteInputActive
                if (r0 != 0) goto L28
                com.android.systemui.statusbar.notification.HeadsUpManagerPhone r0 = r2.this$0
                com.android.systemui.statusbar.notification.collection.NotificationEntry r1 = r2.mEntry
                r0.getClass()
                boolean r0 = com.android.systemui.statusbar.policy.BaseHeadsUpManager.hasFullScreenIntent(r1)
                if (r0 == 0) goto L21
                goto L28
            L21:
                boolean r2 = r2.mGutsShownPinned
                if (r2 == 0) goto L26
                goto L28
            L26:
                r2 = 0
                goto L29
            L28:
                r2 = 1
            L29:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.HeadsUpManagerPhone.HeadsUpEntryPhone.isSticky():boolean");
        }

        @Override // com.android.systemui.statusbar.policy.BaseHeadsUpManager.HeadsUpEntry
        public final void updateEntry(String str, boolean z) {
            super.this$0.mAvalancheController.update(this, new BaseHeadsUpManager$$ExternalSyntheticLambda5(this, z, str), "updateEntry (updatePostTime)");
            if (isSticky()) {
                cancelAutoRemovalCallbacks("updateEntry (sticky)");
            } else {
                scheduleAutoRemovalCallback(new BaseHeadsUpManager$HeadsUpEntry$$ExternalSyntheticLambda0(this, 1), "updateEntry (not sticky)");
                super.this$0.updateTopHeadsUpFlow();
            }
            if (HeadsUpManagerPhone.this.mEntriesToRemoveAfterExpand.contains(this.mEntry)) {
                HeadsUpManagerPhone.this.mEntriesToRemoveAfterExpand.remove(this.mEntry);
            }
            if (HeadsUpManagerPhone.this.mEntriesToRemoveWhenReorderingAllowed.contains(this.mEntry)) {
                HeadsUpManagerPhone.this.mEntriesToRemoveWhenReorderingAllowed.remove(this.mEntry);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [com.android.systemui.statusbar.notification.HeadsUpManagerPhone$$ExternalSyntheticLambda0] */
    public HeadsUpManagerPhone(Context context, HeadsUpManagerLogger headsUpManagerLogger, StatusBarStateController statusBarStateController, KeyguardBypassController keyguardBypassController, GroupMembershipManagerImpl groupMembershipManagerImpl, VisualStabilityProvider visualStabilityProvider, ConfigurationController configurationController, Handler handler, GlobalSettings globalSettings, SystemClock systemClock, DelayableExecutor delayableExecutor, AccessibilityManagerWrapper accessibilityManagerWrapper, UiEventLogger uiEventLogger, JavaAdapter javaAdapter, ShadeInteractor shadeInteractor, AvalancheController avalancheController) {
        super(context, headsUpManagerLogger, handler, globalSettings, systemClock, delayableExecutor, accessibilityManagerWrapper, uiEventLogger, avalancheController);
        this.mHeadsUpPhoneListeners = new ArrayList();
        this.mTopHeadsUpRow = StateFlowKt.MutableStateFlow(null);
        this.mHeadsUpNotificationRows = StateFlowKt.MutableStateFlow(new HashSet());
        this.mHeadsUpAnimatingAway = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.mSwipedOutKeys = new HashSet();
        this.mEntriesToRemoveAfterExpand = new HashSet();
        this.mEntriesToRemoveWhenReorderingAllowed = new ArraySet(0);
        this.mTouchableRegion = new Region();
        this.mEntryPool = new AnonymousClass1();
        this.mOnReorderingAllowedListener = new OnReorderingAllowedListener() { // from class: com.android.systemui.statusbar.notification.HeadsUpManagerPhone$$ExternalSyntheticLambda0
            @Override // com.android.systemui.statusbar.notification.collection.provider.OnReorderingAllowedListener
            public final void onReorderingAllowed() {
                HeadsUpManagerPhone headsUpManagerPhone = HeadsUpManagerPhone.this;
                ((NotificationStackScrollLayout) headsUpManagerPhone.mAnimationStateHandler.f$0).mHeadsUpGoingAwayAnimationsAllowed = false;
                ArraySet arraySet = headsUpManagerPhone.mEntriesToRemoveWhenReorderingAllowed;
                arraySet.getClass();
                ArraySet.ElementIterator elementIterator = arraySet.new ElementIterator();
                while (elementIterator.hasNext()) {
                    NotificationEntry notificationEntry = (NotificationEntry) elementIterator.next();
                    if (headsUpManagerPhone.isHeadsUpEntry(notificationEntry.mKey)) {
                        headsUpManagerPhone.removeEntry(notificationEntry.mKey, "allowReorder");
                    }
                }
                headsUpManagerPhone.mEntriesToRemoveWhenReorderingAllowed.clear();
                ((NotificationStackScrollLayout) headsUpManagerPhone.mAnimationStateHandler.f$0).mHeadsUpGoingAwayAnimationsAllowed = true;
            }
        };
        StatusBarStateController.StateListener stateListener = new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.notification.HeadsUpManagerPhone.3
            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onDozingChanged(boolean z) {
                if (z) {
                    return;
                }
                Iterator it = HeadsUpManagerPhone.this.getHeadsUpEntryList().iterator();
                while (it.hasNext()) {
                    ((BaseHeadsUpManager.HeadsUpEntry) it.next()).updateEntry("onDozingChanged(false)", true);
                }
            }

            @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
            public final void onStateChanged(int i) {
                HeadsUpManagerPhone headsUpManagerPhone = HeadsUpManagerPhone.this;
                boolean z = headsUpManagerPhone.mStatusBarState == 1;
                boolean z2 = i == 1;
                headsUpManagerPhone.mStatusBarState = i;
                if (z && !z2 && headsUpManagerPhone.mBypassController.getBypassEnabled()) {
                    ArrayList arrayList = new ArrayList();
                    for (BaseHeadsUpManager.HeadsUpEntry headsUpEntry : headsUpManagerPhone.getHeadsUpEntryList()) {
                        NotificationEntry notificationEntry = headsUpEntry.mEntry;
                        if (notificationEntry != null && notificationEntry.isBubble() && !headsUpEntry.isSticky()) {
                            arrayList.add(headsUpEntry.mEntry.mKey);
                        }
                    }
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        headsUpManagerPhone.removeEntry((String) it.next(), "mStatusBarStateListener");
                    }
                }
            }
        };
        this.mExtensionTime = this.mContext.getResources().getInteger(R.integer.ambient_notification_extension_time);
        statusBarStateController.addCallback(stateListener);
        this.mBypassController = keyguardBypassController;
        this.mGroupMembershipManager = groupMembershipManagerImpl;
        this.mVisualStabilityProvider = visualStabilityProvider;
        updateResources$2();
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.notification.HeadsUpManagerPhone.2
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onDensityOrFontScaleChanged() {
                HeadsUpManagerPhone.this.updateResources$2();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                HeadsUpManagerPhone.this.updateResources$2();
            }
        });
        javaAdapter.alwaysCollectFlow(((ShadeInteractorImpl) shadeInteractor).baseShadeInteractor.isAnyExpanded(), new Consumer() { // from class: com.android.systemui.statusbar.notification.HeadsUpManagerPhone$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                HeadsUpManagerPhone headsUpManagerPhone = HeadsUpManagerPhone.this;
                Boolean bool = (Boolean) obj;
                headsUpManagerPhone.getClass();
                if (bool.booleanValue() != headsUpManagerPhone.mIsShadeOrQsExpanded) {
                    headsUpManagerPhone.mIsShadeOrQsExpanded = bool.booleanValue();
                    if (bool.booleanValue()) {
                        Boolean bool2 = Boolean.FALSE;
                        StateFlowImpl stateFlowImpl = headsUpManagerPhone.mHeadsUpAnimatingAway;
                        stateFlowImpl.getClass();
                        stateFlowImpl.updateState(null, bool2);
                    }
                }
            }
        });
    }

    @Override // com.android.systemui.statusbar.policy.BaseHeadsUpManager
    public final boolean canRemoveImmediately(String str) {
        ExpandableNotificationRow expandableNotificationRow;
        if (this.mSwipedOutKeys.contains(str)) {
            this.mSwipedOutKeys.remove(str);
            return true;
        }
        HeadsUpEntryPhone headsUpEntryPhone = (HeadsUpEntryPhone) this.mHeadsUpEntryMap.get(str);
        HeadsUpEntryPhone headsUpEntryPhone2 = (HeadsUpEntryPhone) getTopHeadsUpEntry();
        if (headsUpEntryPhone == null || headsUpEntryPhone != headsUpEntryPhone2) {
            return true;
        }
        BaseHeadsUpManager.HeadsUpEntry headsUpEntry = getHeadsUpEntry(str);
        if ((headsUpEntry != null && headsUpEntry.mUserActionMayIndirectlyRemove) || headsUpEntry == null) {
            return true;
        }
        long j = headsUpEntry.mEarliestRemovalTime;
        ((SystemClockImpl) headsUpEntry.this$0.mSystemClock).getClass();
        if (j < android.os.SystemClock.elapsedRealtime()) {
            return true;
        }
        NotificationEntry notificationEntry = headsUpEntry.mEntry;
        return (notificationEntry == null || (expandableNotificationRow = notificationEntry.row) == null || !expandableNotificationRow.mDismissed) ? false : true;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("HeadsUpManagerPhone state:");
        printWriter.print("  mTouchAcceptanceDelay=");
        printWriter.println(this.mTouchAcceptanceDelay);
        printWriter.print("  mSnoozeLengthMs=");
        printWriter.println(this.mSnoozeLengthMs);
        printWriter.print("  now=");
        ((SystemClockImpl) this.mSystemClock).getClass();
        printWriter.println(android.os.SystemClock.elapsedRealtime());
        printWriter.print("  mUser=");
        printWriter.println(this.mUser);
        Iterator it = this.mHeadsUpEntryMap.values().iterator();
        while (it.hasNext()) {
            Object obj = ((BaseHeadsUpManager.HeadsUpEntry) it.next()).mEntry;
            if (obj == null) {
                obj = "null";
            }
            printWriter.println(obj);
        }
        int size = this.mSnoozedPackages.size();
        printWriter.println("  snoozed packages: " + size);
        for (int i = 0; i < size; i++) {
            printWriter.print("    ");
            printWriter.print(this.mSnoozedPackages.valueAt(i));
            printWriter.print(", ");
            printWriter.println((String) this.mSnoozedPackages.keyAt(i));
        }
        printWriter.print("  mBarState=");
        printWriter.println(this.mStatusBarState);
        printWriter.print("  mTouchableRegion=");
        printWriter.println(this.mTouchableRegion);
    }

    @Override // com.android.systemui.statusbar.policy.BaseHeadsUpManager
    public final void onEntryRemoved(BaseHeadsUpManager.HeadsUpEntry headsUpEntry) {
        super.onEntryRemoved(headsUpEntry);
        this.mEntryPool.release((HeadsUpEntryPhone) headsUpEntry);
        updateTopHeadsUpFlow();
        HashSet hashSet = new HashSet(this.mHeadsUpEntryMap.values());
        StateFlowImpl stateFlowImpl = this.mHeadsUpNotificationRows;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, hashSet);
    }

    public final boolean removeNotification(String str, String str2, boolean z) {
        if (z) {
            return removeNotification$1(str, "removeNotification(animate: true), reason: " + str2, true);
        }
        ((NotificationStackScrollLayout) this.mAnimationStateHandler.f$0).mHeadsUpGoingAwayAnimationsAllowed = false;
        boolean removeNotification$1 = removeNotification$1(str, "removeNotification(animate: false), reason: " + str2, true);
        ((NotificationStackScrollLayout) this.mAnimationStateHandler.f$0).mHeadsUpGoingAwayAnimationsAllowed = true;
        return removeNotification$1;
    }

    public final void setGutsShown(NotificationEntry notificationEntry, boolean z) {
        BaseHeadsUpManager.HeadsUpEntry headsUpEntry = getHeadsUpEntry(notificationEntry.mKey);
        if (headsUpEntry instanceof HeadsUpEntryPhone) {
            HeadsUpEntryPhone headsUpEntryPhone = (HeadsUpEntryPhone) headsUpEntry;
            if ((notificationEntry.isRowPinned() || !z) && headsUpEntryPhone.mGutsShownPinned != z) {
                headsUpEntryPhone.mGutsShownPinned = z;
                if (z) {
                    headsUpEntryPhone.cancelAutoRemovalCallbacks("setGutsShownPinned(true)");
                } else {
                    headsUpEntryPhone.updateEntry("setGutsShownPinned(false)", false);
                }
            }
        }
    }

    public final void setHeadsUpAnimatingAway(boolean z) {
        StateFlowImpl stateFlowImpl = this.mHeadsUpAnimatingAway;
        if (z != ((Boolean) stateFlowImpl.getValue()).booleanValue()) {
            Iterator it = this.mHeadsUpPhoneListeners.iterator();
            while (it.hasNext()) {
                final StatusBarTouchableRegionManager statusBarTouchableRegionManager = ((StatusBarTouchableRegionManager$$ExternalSyntheticLambda0) it.next()).f$0;
                if (z) {
                    statusBarTouchableRegionManager.updateTouchableRegion();
                } else {
                    View view = statusBarTouchableRegionManager.mNotificationPanelView;
                    if (view != null) {
                        statusBarTouchableRegionManager.mForceCollapsedUntilLayout = true;
                        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager.3
                            @Override // android.view.View.OnLayoutChangeListener
                            public final void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                                if (StatusBarTouchableRegionManager.this.mNotificationPanelView.isVisibleToUser()) {
                                    return;
                                }
                                StatusBarTouchableRegionManager.this.mNotificationPanelView.removeOnLayoutChangeListener(this);
                                StatusBarTouchableRegionManager statusBarTouchableRegionManager2 = StatusBarTouchableRegionManager.this;
                                statusBarTouchableRegionManager2.mForceCollapsedUntilLayout = false;
                                statusBarTouchableRegionManager2.updateTouchableRegion();
                            }
                        });
                    }
                }
            }
            AuthContainerView$$ExternalSyntheticOutline0.m(z, stateFlowImpl, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    @Override // com.android.systemui.statusbar.policy.BaseHeadsUpManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean shouldHeadsUpBecomePinned(com.android.systemui.statusbar.notification.collection.NotificationEntry r5) {
        /*
            r4 = this;
            int r0 = r4.mStatusBarState
            r1 = 0
            r2 = 1
            if (r0 != 0) goto Lc
            boolean r0 = r4.mIsShadeOrQsExpanded
            if (r0 != 0) goto Lc
            r0 = r2
            goto Ld
        Lc:
            r0 = r1
        Ld:
            com.android.systemui.statusbar.phone.KeyguardBypassController r3 = r4.mBypassController
            boolean r3 = r3.getBypassEnabled()
            if (r3 == 0) goto L1d
            int r3 = r4.mStatusBarState
            if (r3 != r2) goto L1b
            r3 = r2
            goto L1c
        L1b:
            r3 = r1
        L1c:
            r0 = r0 | r3
        L1d:
            if (r0 != 0) goto L3d
            if (r5 != 0) goto L23
        L21:
            r4 = r1
            goto L3b
        L23:
            java.lang.String r0 = r5.mKey
            com.android.systemui.statusbar.policy.BaseHeadsUpManager$HeadsUpEntry r4 = r4.getHeadsUpEntry(r0)
            if (r4 != 0) goto L30
            boolean r4 = com.android.systemui.statusbar.policy.BaseHeadsUpManager.hasFullScreenIntent(r5)
            goto L3b
        L30:
            boolean r5 = com.android.systemui.statusbar.policy.BaseHeadsUpManager.hasFullScreenIntent(r5)
            if (r5 == 0) goto L21
            boolean r4 = r4.mWasUnpinned
            if (r4 != 0) goto L21
            r4 = r2
        L3b:
            if (r4 == 0) goto L3e
        L3d:
            r1 = r2
        L3e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.HeadsUpManagerPhone.shouldHeadsUpBecomePinned(com.android.systemui.statusbar.notification.collection.NotificationEntry):boolean");
    }

    @Override // com.android.systemui.statusbar.policy.BaseHeadsUpManager
    public final void snooze() {
        super.snooze();
        this.mReleaseOnExpandFinish = true;
    }

    public final void updateResources$2() {
        Resources resources = this.mContext.getResources();
        this.mHeadsUpInset = resources.getDimensionPixelSize(R.dimen.heads_up_status_bar_padding) + SystemBarUtils.getStatusBarHeight(this.mContext);
    }

    public final void updateTopHeadsUpFlow() {
        this.mTopHeadsUpRow.setValue((HeadsUpEntryPhone) getTopHeadsUpEntry());
    }
}

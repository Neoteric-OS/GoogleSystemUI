package com.android.systemui.wmshell;

import android.app.INotificationManager;
import android.app.NotificationChannel;
import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.Trace;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.dreams.IDreamManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.android.app.tracing.TraceUtilsKt;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.model.SysUiState;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.notification.NotifPipelineFlags;
import com.android.systemui.statusbar.notification.NotificationChannelHelper;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProviderImpl;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.phone.StatusBarWindowCallback;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionController;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionControllerImpl;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import com.android.wm.shell.bubbles.BubbleController;
import com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda0;
import com.android.wm.shell.bubbles.BubbleController$$ExternalSyntheticLambda6;
import com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda11;
import com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda15;
import com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda5;
import com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda7;
import com.android.wm.shell.bubbles.BubbleEntry;
import com.android.wm.shell.bubbles.Bubbles;
import com.android.wm.shell.common.HandlerExecutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class BubblesManager {
    public final IStatusBarService mBarService;
    public final Bubbles mBubbles;
    public final List mCallbacks = new ArrayList();
    public final CommonNotifCollection mCommonNotifCollection;
    public final Context mContext;
    public final IDreamManager mDreamManager;
    public boolean mDreamingOrInPreview;
    public boolean mKeyguardShowing;
    public final KeyguardStateController mKeyguardStateController;
    public final NotificationLockscreenUserManager mNotifUserManager;
    public final INotificationManager mNotificationManager;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public boolean mPanelExpanded;
    public final SensitiveNotificationProtectionController mSensitiveNotifProtectionController;
    public final AnonymousClass4 mSensitiveStateChangedListener;
    public final ShadeController mShadeController;
    public final StatusBarWindowCallback mStatusBarWindowCallback;
    public final Executor mSysuiMainExecutor;
    public final Executor mSysuiUiBgExecutor;
    public final NotificationVisibilityProvider mVisibilityProvider;
    public final VisualInterruptionDecisionProvider mVisualInterruptionDecisionProvider;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.wmshell.BubblesManager$5, reason: invalid class name */
    public final class AnonymousClass5 {
        public final /* synthetic */ SysUiState val$sysUiState;
        public final /* synthetic */ Executor val$sysuiMainExecutor;

        public AnonymousClass5(Executor executor, SysUiState sysUiState) {
            this.val$sysuiMainExecutor = executor;
            this.val$sysUiState = sysUiState;
        }
    }

    public BubblesManager(Context context, final Bubbles bubbles, NotificationShadeWindowController notificationShadeWindowController, KeyguardStateController keyguardStateController, ShadeController shadeController, IStatusBarService iStatusBarService, INotificationManager iNotificationManager, IDreamManager iDreamManager, NotificationVisibilityProvider notificationVisibilityProvider, VisualInterruptionDecisionProvider visualInterruptionDecisionProvider, ZenModeController zenModeController, NotificationLockscreenUserManager notificationLockscreenUserManager, SensitiveNotificationProtectionController sensitiveNotificationProtectionController, CommonNotifCollection commonNotifCollection, NotifPipeline notifPipeline, SysUiState sysUiState, FeatureFlags featureFlags, NotifPipelineFlags notifPipelineFlags, Executor executor, Executor executor2) {
        this.mContext = context;
        this.mBubbles = bubbles;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mKeyguardStateController = keyguardStateController;
        this.mShadeController = shadeController;
        this.mNotificationManager = iNotificationManager;
        this.mDreamManager = iDreamManager;
        this.mVisibilityProvider = notificationVisibilityProvider;
        this.mVisualInterruptionDecisionProvider = visualInterruptionDecisionProvider;
        this.mNotifUserManager = notificationLockscreenUserManager;
        this.mSensitiveNotifProtectionController = sensitiveNotificationProtectionController;
        this.mCommonNotifCollection = commonNotifCollection;
        this.mSysuiMainExecutor = executor;
        this.mSysuiUiBgExecutor = executor2;
        this.mBarService = iStatusBarService == null ? IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar")) : iStatusBarService;
        notifPipeline.addCollectionListener(new NotifCollectionListener() { // from class: com.android.systemui.wmshell.BubblesManager.6
            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryAdded(NotificationEntry notificationEntry) {
                BubblesManager bubblesManager = BubblesManager.this;
                VisualInterruptionDecisionProviderImpl visualInterruptionDecisionProviderImpl = (VisualInterruptionDecisionProviderImpl) bubblesManager.mVisualInterruptionDecisionProvider;
                visualInterruptionDecisionProviderImpl.getClass();
                boolean isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice("VisualInterruptionDecisionProviderImpl#makeAndLogBubbleDecision");
                }
                try {
                    if (!visualInterruptionDecisionProviderImpl.started) {
                        throw new IllegalStateException("Check failed.");
                    }
                    VisualInterruptionType visualInterruptionType = VisualInterruptionType.BUBBLE;
                    VisualInterruptionDecisionProviderImpl.LoggableDecision checkConditions = visualInterruptionDecisionProviderImpl.checkConditions(visualInterruptionType);
                    if (checkConditions == null && (checkConditions = visualInterruptionDecisionProviderImpl.checkFilters(visualInterruptionType, notificationEntry)) == null && (checkConditions = visualInterruptionDecisionProviderImpl.checkSuppressInterruptions(notificationEntry)) == null && (checkConditions = visualInterruptionDecisionProviderImpl.checkSuppressAwakeInterruptions(notificationEntry)) == null) {
                        checkConditions = VisualInterruptionDecisionProviderImpl.LoggableDecision.unsuppressed;
                    }
                    visualInterruptionDecisionProviderImpl.logDecision(visualInterruptionType, notificationEntry, checkConditions);
                    if (checkConditions.decision.shouldInterrupt && notificationEntry.isBubble()) {
                        BubbleEntry notifToBubbleEntry = bubblesManager.notifToBubbleEntry(notificationEntry);
                        BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) bubblesManager.mBubbles;
                        ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda5(bubblesImpl, notifToBubbleEntry, 1));
                    }
                } finally {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryRemoved(NotificationEntry notificationEntry, int i) {
                if (i == 8 || i == 9 || i == 7) {
                    BubblesManager bubblesManager = BubblesManager.this;
                    BubbleEntry notifToBubbleEntry = bubblesManager.notifToBubbleEntry(notificationEntry);
                    BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) bubblesManager.mBubbles;
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda5(bubblesImpl, notifToBubbleEntry, 2));
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryUpdated(NotificationEntry notificationEntry, final boolean z) {
                BubblesManager bubblesManager = BubblesManager.this;
                final BubbleEntry notifToBubbleEntry = bubblesManager.notifToBubbleEntry(notificationEntry);
                VisualInterruptionDecisionProviderImpl visualInterruptionDecisionProviderImpl = (VisualInterruptionDecisionProviderImpl) bubblesManager.mVisualInterruptionDecisionProvider;
                visualInterruptionDecisionProviderImpl.getClass();
                boolean isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    TraceUtilsKt.beginSlice("VisualInterruptionDecisionProviderImpl#makeAndLogBubbleDecision");
                }
                try {
                    if (!visualInterruptionDecisionProviderImpl.started) {
                        throw new IllegalStateException("Check failed.");
                    }
                    VisualInterruptionType visualInterruptionType = VisualInterruptionType.BUBBLE;
                    VisualInterruptionDecisionProviderImpl.LoggableDecision checkConditions = visualInterruptionDecisionProviderImpl.checkConditions(visualInterruptionType);
                    if (checkConditions == null && (checkConditions = visualInterruptionDecisionProviderImpl.checkFilters(visualInterruptionType, notificationEntry)) == null && (checkConditions = visualInterruptionDecisionProviderImpl.checkSuppressInterruptions(notificationEntry)) == null && (checkConditions = visualInterruptionDecisionProviderImpl.checkSuppressAwakeInterruptions(notificationEntry)) == null) {
                        checkConditions = VisualInterruptionDecisionProviderImpl.LoggableDecision.unsuppressed;
                    }
                    visualInterruptionDecisionProviderImpl.logDecision(visualInterruptionType, notificationEntry, checkConditions);
                    final boolean z2 = checkConditions.decision.shouldInterrupt;
                    final BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) bubblesManager.mBubbles;
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda13
                        @Override // java.lang.Runnable
                        public final void run() {
                            BubbleController.BubblesImpl bubblesImpl2 = BubbleController.BubblesImpl.this;
                            BubbleController.this.onEntryUpdated(notifToBubbleEntry, z2, z);
                        }
                    });
                } finally {
                    if (isEnabled) {
                        TraceUtilsKt.endSlice();
                    }
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onNotificationChannelModified(final String str, final UserHandle userHandle, final NotificationChannel notificationChannel, final int i) {
                final BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) BubblesManager.this.mBubbles;
                bubblesImpl.getClass();
                if (i == 2 || i == 3) {
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.bubbles.BubbleController$BubblesImpl$$ExternalSyntheticLambda10
                        @Override // java.lang.Runnable
                        public final void run() {
                            BubbleController.BubblesImpl bubblesImpl2 = BubbleController.BubblesImpl.this;
                            BubbleController.this.onNotificationChannelModified(str, userHandle, notificationChannel, i);
                        }
                    });
                }
            }

            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onRankingUpdate(NotificationListenerService.RankingMap rankingMap) {
                boolean z;
                BubblesManager bubblesManager = BubblesManager.this;
                String[] orderedKeys = rankingMap.getOrderedKeys();
                HashMap hashMap = new HashMap();
                for (String str : orderedKeys) {
                    NotificationEntry entry = ((NotifPipeline) bubblesManager.mCommonNotifCollection).mNotifCollection.getEntry(str);
                    BubbleEntry notifToBubbleEntry = entry != null ? bubblesManager.notifToBubbleEntry(entry) : null;
                    if (entry != null) {
                        VisualInterruptionDecisionProviderImpl visualInterruptionDecisionProviderImpl = (VisualInterruptionDecisionProviderImpl) bubblesManager.mVisualInterruptionDecisionProvider;
                        visualInterruptionDecisionProviderImpl.getClass();
                        boolean isEnabled = Trace.isEnabled();
                        if (isEnabled) {
                            TraceUtilsKt.beginSlice("VisualInterruptionDecisionProviderImpl#makeAndLogBubbleDecision");
                        }
                        try {
                            if (!visualInterruptionDecisionProviderImpl.started) {
                                throw new IllegalStateException("Check failed.");
                            }
                            VisualInterruptionType visualInterruptionType = VisualInterruptionType.BUBBLE;
                            VisualInterruptionDecisionProviderImpl.LoggableDecision checkConditions = visualInterruptionDecisionProviderImpl.checkConditions(visualInterruptionType);
                            if (checkConditions == null && (checkConditions = visualInterruptionDecisionProviderImpl.checkFilters(visualInterruptionType, entry)) == null && (checkConditions = visualInterruptionDecisionProviderImpl.checkSuppressInterruptions(entry)) == null && (checkConditions = visualInterruptionDecisionProviderImpl.checkSuppressAwakeInterruptions(entry)) == null) {
                                checkConditions = VisualInterruptionDecisionProviderImpl.LoggableDecision.unsuppressed;
                            }
                            visualInterruptionDecisionProviderImpl.logDecision(visualInterruptionType, entry, checkConditions);
                            z = checkConditions.decision.shouldInterrupt;
                        } finally {
                            if (isEnabled) {
                                TraceUtilsKt.endSlice();
                            }
                        }
                    } else {
                        z = false;
                    }
                    hashMap.put(str, new Pair(notifToBubbleEntry, Boolean.valueOf(z)));
                }
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) bubblesManager.mBubbles;
                ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$$ExternalSyntheticLambda6(bubblesImpl, rankingMap, 1, hashMap));
            }
        });
        ((KeyguardStateControllerImpl) keyguardStateController).addCallback(new KeyguardStateController.Callback() { // from class: com.android.systemui.wmshell.BubblesManager.1
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardShowingChanged() {
                BubblesManager bubblesManager = BubblesManager.this;
                bubblesManager.mSysuiUiBgExecutor.execute(new BubblesManager$$ExternalSyntheticLambda1(bubblesManager));
            }
        });
        ((ZenModeControllerImpl) zenModeController).addCallback(new ZenModeController.Callback() { // from class: com.android.systemui.wmshell.BubblesManager.2
            @Override // com.android.systemui.statusbar.policy.ZenModeController.Callback
            public final void onConfigChanged$1() {
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) BubblesManager.this.mBubbles;
                ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda15(bubblesImpl, 0));
            }

            @Override // com.android.systemui.statusbar.policy.ZenModeController.Callback
            public final void onZenChanged(int i) {
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) BubblesManager.this.mBubbles;
                ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda15(bubblesImpl, 0));
            }
        });
        ((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager).mListeners.add(new NotificationLockscreenUserManager.UserChangedListener() { // from class: com.android.systemui.wmshell.BubblesManager.3
            @Override // com.android.systemui.statusbar.NotificationLockscreenUserManager.UserChangedListener
            public final void onCurrentProfilesChanged(SparseArray sparseArray) {
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) BubblesManager.this.mBubbles;
                ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$$ExternalSyntheticLambda0(3, bubblesImpl, sparseArray));
            }

            @Override // com.android.systemui.statusbar.NotificationLockscreenUserManager.UserChangedListener
            public final void onUserChanged(int i) {
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) BubblesManager.this.mBubbles;
                ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda11(bubblesImpl, i, 1));
            }

            @Override // com.android.systemui.statusbar.NotificationLockscreenUserManager.UserChangedListener
            public final void onUserRemoved(int i) {
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) BubblesManager.this.mBubbles;
                ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda11(bubblesImpl, i, 0));
            }
        });
        StatusBarWindowCallback statusBarWindowCallback = new StatusBarWindowCallback() { // from class: com.android.systemui.wmshell.BubblesManager$$ExternalSyntheticLambda0
            @Override // com.android.systemui.statusbar.phone.StatusBarWindowCallback
            public final void onStateChanged(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
                BubblesManager bubblesManager = BubblesManager.this;
                if (z6 != bubblesManager.mPanelExpanded) {
                    bubblesManager.mPanelExpanded = z6;
                    BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) bubblesManager.mBubbles;
                    ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda7(bubblesImpl, z6, 3));
                }
                if (bubblesManager.mKeyguardShowing || !bubblesManager.mDreamingOrInPreview || z7) {
                    return;
                }
                bubblesManager.mSysuiUiBgExecutor.execute(new BubblesManager$$ExternalSyntheticLambda1(bubblesManager));
            }
        };
        this.mStatusBarWindowCallback = statusBarWindowCallback;
        ((NotificationShadeWindowControllerImpl) notificationShadeWindowController).registerCallback(statusBarWindowCallback);
        ((SensitiveNotificationProtectionControllerImpl) sensitiveNotificationProtectionController).mListeners.addIfAbsent(new Runnable() { // from class: com.android.systemui.wmshell.BubblesManager.4
            @Override // java.lang.Runnable
            public final void run() {
                Bubbles bubbles2 = bubbles;
                boolean isSensitiveStateActive = ((SensitiveNotificationProtectionControllerImpl) BubblesManager.this.mSensitiveNotifProtectionController).isSensitiveStateActive();
                BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) bubbles2;
                ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$BubblesImpl$$ExternalSyntheticLambda7(bubblesImpl, isSensitiveStateActive, 0));
            }
        });
        BubbleController.BubblesImpl bubblesImpl = (BubbleController.BubblesImpl) bubbles;
        ((HandlerExecutor) BubbleController.this.mMainExecutor).execute(new BubbleController$$ExternalSyntheticLambda0(5, bubblesImpl, new AnonymousClass5(executor, sysUiState)));
    }

    public static boolean areBubblesEnabled(Context context, UserHandle userHandle) {
        return userHandle.getIdentifier() < 0 ? Settings.Secure.getInt(context.getContentResolver(), "notification_bubbles", 0) == 1 : Settings.Secure.getIntForUser(context.getContentResolver(), "notification_bubbles", 0, userHandle.getIdentifier()) == 1;
    }

    public BubbleEntry notifToBubbleEntry(NotificationEntry notificationEntry) {
        boolean z;
        StatusBarNotification statusBarNotification = notificationEntry.mSbn;
        NotificationListenerService.Ranking ranking = notificationEntry.mRanking;
        if (statusBarNotification.isNonDismissable()) {
            z = false;
        } else {
            notificationEntry.mSbn.isOngoing();
            z = true;
        }
        boolean shouldSuppressVisualEffect = notificationEntry.shouldSuppressVisualEffect(64);
        boolean shouldSuppressVisualEffect2 = notificationEntry.shouldSuppressVisualEffect(256);
        boolean shouldSuppressVisualEffect3 = notificationEntry.shouldSuppressVisualEffect(16);
        BubbleEntry bubbleEntry = new BubbleEntry();
        bubbleEntry.mSbn = statusBarNotification;
        bubbleEntry.mRanking = ranking;
        bubbleEntry.mIsDismissable = z;
        bubbleEntry.mShouldSuppressNotificationDot = shouldSuppressVisualEffect;
        bubbleEntry.mShouldSuppressNotificationList = shouldSuppressVisualEffect2;
        bubbleEntry.mShouldSuppressPeek = shouldSuppressVisualEffect3;
        return bubbleEntry;
    }

    public final void onUserChangedBubble(NotificationEntry notificationEntry, boolean z) {
        NotificationChannel channel = notificationEntry.mRanking.getChannel();
        String packageName = notificationEntry.mSbn.getPackageName();
        int uid = notificationEntry.mSbn.getUid();
        if (channel == null || packageName == null) {
            return;
        }
        notificationEntry.isBubble();
        if (!z) {
            notificationEntry.mSbn.getNotification().flags &= -4097;
        } else if (notificationEntry.mBubbleMetadata != null && notificationEntry.mRanking.canBubble()) {
            notificationEntry.mSbn.getNotification().flags |= 4096;
        }
        notificationEntry.isBubble();
        try {
            this.mBarService.onNotificationBubbleChanged(notificationEntry.mKey, z, 3);
        } catch (RemoteException unused) {
        }
        NotificationChannel createConversationChannelIfNeeded = NotificationChannelHelper.createConversationChannelIfNeeded(this.mContext, this.mNotificationManager, notificationEntry, channel);
        createConversationChannelIfNeeded.setAllowBubbles(z);
        try {
            int bubblePreferenceForPackage = this.mNotificationManager.getBubblePreferenceForPackage(packageName, uid);
            if (z && bubblePreferenceForPackage == 0) {
                this.mNotificationManager.setBubblesAllowed(packageName, uid, 2);
            }
            this.mNotificationManager.updateNotificationChannelForPackage(packageName, uid, createConversationChannelIfNeeded);
        } catch (RemoteException e) {
            Log.e("Bubbles", e.getMessage());
        }
        if (z) {
            this.mShadeController.collapseShade(true);
            ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
            if (expandableNotificationRow != null) {
                expandableNotificationRow.updateBubbleButton();
            }
        }
    }
}

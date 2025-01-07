package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.service.notification.StatusBarNotification;
import android.service.vr.IVrManager;
import android.service.vr.IVrStateCallbacks;
import android.text.TextUtils;
import android.util.Log;
import android.util.Slog;
import com.android.internal.statusbar.IStatusBarService;
import com.android.systemui.InitController;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.QuickSettingsController;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.LockscreenShadeTransitionController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationMediaManager$$ExternalSyntheticLambda5;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.disableflags.data.model.DisableFlagsModel;
import com.android.systemui.statusbar.notification.AboveShelfObserver;
import com.android.systemui.statusbar.notification.DynamicPrivacyController;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinator$setRemoteInputController$1;
import com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinatorKt;
import com.android.systemui.statusbar.notification.collection.coordinator.ShadeEventCoordinator;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationAlertsInteractor;
import com.android.systemui.statusbar.notification.interruption.NotificationInterruptSuppressor;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProviderImpl;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionType;
import com.android.systemui.statusbar.notification.logging.NotificationLogger;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController.AnonymousClass9;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.wm.shell.R;
import java.util.Objects;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarNotificationPresenter implements CommandQueue.Callbacks {
    public final AboveShelfObserver mAboveShelfObserver;
    public final ActivityStarter mActivityStarter;
    public final AnonymousClass4 mAlertsDisabledCondition;
    public final IStatusBarService mBarService;
    public final CommandQueue mCommandQueue;
    public final DozeScrimController mDozeScrimController;
    public final DynamicPrivacyController mDynamicPrivacyController;
    public final NotificationGutsManager mGutsManager;
    public final HeadsUpManager mHeadsUpManager;
    public final AnonymousClass3 mInterruptSuppressor;
    public final KeyguardStateController mKeyguardStateController;
    public final NotificationLockscreenUserManager mLockscreenUserManager;
    public final NotificationMediaManager mMediaManager;
    public final AnonymousClass6 mNeedsRedactionFilter;
    public final NotificationStackScrollLayoutController.NotificationListContainerImpl mNotifListContainer;
    public final ShadeEventCoordinator mNotifShadeEventSource;
    public final NotificationAlertsInteractor mNotificationAlertsInteractor;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public final NotificationStackScrollLayoutController mNsslController;
    public final AnonymousClass2 mOnSettingsClickListener;
    public final PanelExpansionInteractor mPanelExpansionInteractor;
    public final AnonymousClass4 mPanelsDisabledCondition;
    public final PowerInteractor mPowerInteractor;
    public final QuickSettingsController mQsController;
    public final LockscreenShadeTransitionController mShadeTransitionController;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public boolean mVrMode;
    public final AnonymousClass4 mVrModeCondition;
    public final AnonymousClass1 mVrStateCallbacks;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.StatusBarNotificationPresenter$2, reason: invalid class name */
    public final class AnonymousClass2 {
        public AnonymousClass2() {
        }
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [com.android.systemui.statusbar.phone.StatusBarNotificationPresenter$4] */
    /* JADX WARN: Type inference failed for: r5v3, types: [com.android.systemui.statusbar.phone.StatusBarNotificationPresenter$4] */
    /* JADX WARN: Type inference failed for: r5v4, types: [com.android.systemui.statusbar.phone.StatusBarNotificationPresenter$6] */
    /* JADX WARN: Type inference failed for: r5v5, types: [com.android.systemui.statusbar.phone.StatusBarNotificationPresenter$4] */
    public StatusBarNotificationPresenter(Context context, PanelExpansionInteractor panelExpansionInteractor, QuickSettingsController quickSettingsController, HeadsUpManager headsUpManager, NotificationShadeWindowView notificationShadeWindowView, ActivityStarter activityStarter, NotificationStackScrollLayoutController notificationStackScrollLayoutController, DozeScrimController dozeScrimController, NotificationShadeWindowController notificationShadeWindowController, DynamicPrivacyController dynamicPrivacyController, KeyguardStateController keyguardStateController, NotificationAlertsInteractor notificationAlertsInteractor, LockscreenShadeTransitionController lockscreenShadeTransitionController, PowerInteractor powerInteractor, CommandQueue commandQueue, NotificationLockscreenUserManager notificationLockscreenUserManager, SysuiStatusBarStateController sysuiStatusBarStateController, ShadeEventCoordinator shadeEventCoordinator, NotificationMediaManager notificationMediaManager, NotificationGutsManager notificationGutsManager, InitController initController, final VisualInterruptionDecisionProvider visualInterruptionDecisionProvider, final NotificationRemoteInputManager notificationRemoteInputManager, StatusBarRemoteInputCallback statusBarRemoteInputCallback, NotificationStackScrollLayoutController.NotificationListContainerImpl notificationListContainerImpl) {
        IVrStateCallbacks.Stub stub = new IVrStateCallbacks.Stub() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationPresenter.1
            public final void onVrStateChanged(boolean z) {
                StatusBarNotificationPresenter.this.mVrMode = z;
            }
        };
        this.mOnSettingsClickListener = new AnonymousClass2();
        new NotificationInterruptSuppressor() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationPresenter.3
            @Override // com.android.systemui.statusbar.notification.interruption.NotificationInterruptSuppressor
            public final String getName() {
                return "StatusBarNotificationPresenter";
            }

            @Override // com.android.systemui.statusbar.notification.interruption.NotificationInterruptSuppressor
            public final boolean suppressAwakeHeadsUp(NotificationEntry notificationEntry) {
                boolean z;
                StatusBarNotification statusBarNotification = notificationEntry.mSbn;
                StatusBarNotificationPresenter statusBarNotificationPresenter = StatusBarNotificationPresenter.this;
                if (((KeyguardStateControllerImpl) statusBarNotificationPresenter.mKeyguardStateController).mOccluded) {
                    NotificationLockscreenUserManager notificationLockscreenUserManager2 = statusBarNotificationPresenter.mLockscreenUserManager;
                    NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = (NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager2;
                    if (!notificationLockscreenUserManagerImpl.isLockscreenPublicMode(notificationLockscreenUserManagerImpl.mCurrentUserId)) {
                        if (!((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager2).isLockscreenPublicMode(statusBarNotification.getUserId())) {
                            z = false;
                            boolean needsRedaction = ((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager2).needsRedaction(notificationEntry);
                            if (z && needsRedaction) {
                                return true;
                            }
                        }
                    }
                    z = true;
                    boolean needsRedaction2 = ((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager2).needsRedaction(notificationEntry);
                    if (z) {
                        return true;
                    }
                }
                return !statusBarNotificationPresenter.mCommandQueue.panelsEnabled();
            }

            @Override // com.android.systemui.statusbar.notification.interruption.NotificationInterruptSuppressor
            public final boolean suppressAwakeInterruptions() {
                return StatusBarNotificationPresenter.this.mVrMode;
            }

            @Override // com.android.systemui.statusbar.notification.interruption.NotificationInterruptSuppressor
            public final boolean suppressInterruptions() {
                return !((((DisableFlagsModel) StatusBarNotificationPresenter.this.mNotificationAlertsInteractor.disableFlagsRepository.disableFlags.getValue()).disable1 & 262144) == 0);
            }
        };
        VisualInterruptionType visualInterruptionType = VisualInterruptionType.PEEK;
        VisualInterruptionType visualInterruptionType2 = VisualInterruptionType.PULSE;
        VisualInterruptionType visualInterruptionType3 = VisualInterruptionType.BUBBLE;
        this.mAlertsDisabledCondition = new VisualInterruptionCondition(this, Set.of(visualInterruptionType, visualInterruptionType2, visualInterruptionType3), 0) { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationPresenter.4
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ StatusBarNotificationPresenter this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super("notification alerts disabled", r2);
                this.$r8$classId = r3;
                switch (r3) {
                    case 1:
                        this.this$0 = this;
                        super("device is in VR mode", r2);
                        break;
                    case 2:
                        this.this$0 = this;
                        super("disabled panel", r2);
                        break;
                    default:
                        this.this$0 = this;
                        break;
                }
            }

            @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition
            public final boolean shouldSuppress() {
                switch (this.$r8$classId) {
                    case 0:
                        return !((((DisableFlagsModel) this.this$0.mNotificationAlertsInteractor.disableFlagsRepository.disableFlags.getValue()).disable1 & 262144) == 0);
                    case 1:
                        return this.this$0.mVrMode;
                    default:
                        return !this.this$0.mCommandQueue.panelsEnabled();
                }
            }
        };
        this.mVrModeCondition = new VisualInterruptionCondition(this, Set.of(visualInterruptionType, visualInterruptionType3), 1) { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationPresenter.4
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ StatusBarNotificationPresenter this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super("notification alerts disabled", r2);
                this.$r8$classId = r3;
                switch (r3) {
                    case 1:
                        this.this$0 = this;
                        super("device is in VR mode", r2);
                        break;
                    case 2:
                        this.this$0 = this;
                        super("disabled panel", r2);
                        break;
                    default:
                        this.this$0 = this;
                        break;
                }
            }

            @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition
            public final boolean shouldSuppress() {
                switch (this.$r8$classId) {
                    case 0:
                        return !((((DisableFlagsModel) this.this$0.mNotificationAlertsInteractor.disableFlagsRepository.disableFlags.getValue()).disable1 & 262144) == 0);
                    case 1:
                        return this.this$0.mVrMode;
                    default:
                        return !this.this$0.mCommandQueue.panelsEnabled();
                }
            }
        };
        this.mNeedsRedactionFilter = new VisualInterruptionFilter(Set.of(visualInterruptionType)) { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationPresenter.6
            @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter
            public final boolean shouldSuppress(NotificationEntry notificationEntry) {
                StatusBarNotificationPresenter statusBarNotificationPresenter = StatusBarNotificationPresenter.this;
                if (!((KeyguardStateControllerImpl) statusBarNotificationPresenter.mKeyguardStateController).mOccluded) {
                    return false;
                }
                NotificationLockscreenUserManager notificationLockscreenUserManager2 = statusBarNotificationPresenter.mLockscreenUserManager;
                if (!((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager2).needsRedaction(notificationEntry)) {
                    return false;
                }
                NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = (NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager2;
                return notificationLockscreenUserManagerImpl.isLockscreenPublicMode(notificationLockscreenUserManagerImpl.mCurrentUserId) || ((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager2).isLockscreenPublicMode(notificationEntry.mSbn.getUserId());
            }
        };
        this.mPanelsDisabledCondition = new VisualInterruptionCondition(this, Set.of(visualInterruptionType), 2) { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationPresenter.4
            public final /* synthetic */ int $r8$classId;
            public final /* synthetic */ StatusBarNotificationPresenter this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super("notification alerts disabled", r2);
                this.$r8$classId = r3;
                switch (r3) {
                    case 1:
                        this.this$0 = this;
                        super("device is in VR mode", r2);
                        break;
                    case 2:
                        this.this$0 = this;
                        super("disabled panel", r2);
                        break;
                    default:
                        this.this$0 = this;
                        break;
                }
            }

            @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition
            public final boolean shouldSuppress() {
                switch (this.$r8$classId) {
                    case 0:
                        return !((((DisableFlagsModel) this.this$0.mNotificationAlertsInteractor.disableFlagsRepository.disableFlags.getValue()).disable1 & 262144) == 0);
                    case 1:
                        return this.this$0.mVrMode;
                    default:
                        return !this.this$0.mCommandQueue.panelsEnabled();
                }
            }
        };
        this.mActivityStarter = activityStarter;
        this.mKeyguardStateController = keyguardStateController;
        this.mPanelExpansionInteractor = panelExpansionInteractor;
        this.mQsController = quickSettingsController;
        this.mHeadsUpManager = headsUpManager;
        this.mDynamicPrivacyController = dynamicPrivacyController;
        this.mNotificationAlertsInteractor = notificationAlertsInteractor;
        this.mNsslController = notificationStackScrollLayoutController;
        this.mShadeTransitionController = lockscreenShadeTransitionController;
        this.mPowerInteractor = powerInteractor;
        this.mCommandQueue = commandQueue;
        this.mLockscreenUserManager = notificationLockscreenUserManager;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mNotifShadeEventSource = shadeEventCoordinator;
        this.mMediaManager = notificationMediaManager;
        this.mGutsManager = notificationGutsManager;
        AboveShelfObserver aboveShelfObserver = new AboveShelfObserver(notificationStackScrollLayoutController.mView);
        this.mAboveShelfObserver = aboveShelfObserver;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        aboveShelfObserver.mListener = (AboveShelfObserver.HasViewAboveShelfChangedListener) notificationShadeWindowView.findViewById(R.id.notification_container_parent);
        this.mDozeScrimController = dozeScrimController;
        this.mBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
        this.mNotifListContainer = notificationListContainerImpl;
        IVrManager asInterface = IVrManager.Stub.asInterface(ServiceManager.getService("vrmanager"));
        if (asInterface != null) {
            try {
                asInterface.registerListener(stub);
            } catch (RemoteException e) {
                Slog.e("StatusBarNotificationPresenter", "Failed to register VR mode state listener: " + e);
            }
        }
        NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = this.mNsslController;
        notificationStackScrollLayoutController2.getClass();
        NotificationStackScrollLayoutController.AnonymousClass9 anonymousClass9 = notificationStackScrollLayoutController2.new AnonymousClass9();
        notificationRemoteInputManager.mCallback = statusBarRemoteInputCallback;
        notificationRemoteInputManager.mRemoteInputController = new RemoteInputController(anonymousClass9, notificationRemoteInputManager.mRemoteInputUriController, notificationRemoteInputManager.mRemoteInputControllerLogger);
        RemoteInputCoordinator remoteInputCoordinator = notificationRemoteInputManager.mRemoteInputListener;
        if (remoteInputCoordinator != null) {
            remoteInputCoordinator.mSmartReplyController.mCallback = new RemoteInputCoordinator$setRemoteInputController$1(remoteInputCoordinator);
        }
        for (RemoteInputController.Callback callback : notificationRemoteInputManager.mControllerCallbacks) {
            RemoteInputController remoteInputController = notificationRemoteInputManager.mRemoteInputController;
            remoteInputController.getClass();
            Objects.requireNonNull(callback);
            remoteInputController.mCallbacks.add(callback);
        }
        notificationRemoteInputManager.mControllerCallbacks.clear();
        RemoteInputController remoteInputController2 = notificationRemoteInputManager.mRemoteInputController;
        RemoteInputController.Callback callback2 = new RemoteInputController.Callback() { // from class: com.android.systemui.statusbar.NotificationRemoteInputManager.2
            @Override // com.android.systemui.statusbar.RemoteInputController.Callback
            public final void onRemoteInputSent(NotificationEntry notificationEntry) {
                boolean booleanValue;
                NotificationRemoteInputManager notificationRemoteInputManager2 = NotificationRemoteInputManager.this;
                RemoteInputCoordinator remoteInputCoordinator2 = notificationRemoteInputManager2.mRemoteInputListener;
                if (remoteInputCoordinator2 != null) {
                    booleanValue = ((Boolean) RemoteInputCoordinatorKt.DEBUG$delegate.getValue()).booleanValue();
                    String str = notificationEntry.mKey;
                    if (booleanValue) {
                        Log.d("RemoteInputCoordinator", "onRemoteInputSent(entry=" + str + ")");
                    }
                    remoteInputCoordinator2.mRemoteInputHistoryExtender.endLifetimeExtension(str);
                    remoteInputCoordinator2.mSmartReplyHistoryExtender.endLifetimeExtension(str);
                    remoteInputCoordinator2.mRemoteInputActiveExtender.endLifetimeExtensionAfterDelay(500L, str);
                }
                try {
                    notificationRemoteInputManager2.mBarService.onNotificationDirectReplied(notificationEntry.mSbn.getKey());
                    NotificationEntry.EditedSuggestionInfo editedSuggestionInfo = notificationEntry.editedSuggestionInfo;
                    if (editedSuggestionInfo != null) {
                        boolean z = !TextUtils.equals(notificationEntry.remoteInputText, editedSuggestionInfo.originalText);
                        IStatusBarService iStatusBarService = notificationRemoteInputManager2.mBarService;
                        String key = notificationEntry.mSbn.getKey();
                        NotificationEntry.EditedSuggestionInfo editedSuggestionInfo2 = notificationEntry.editedSuggestionInfo;
                        iStatusBarService.onNotificationSmartReplySent(key, editedSuggestionInfo2.index, editedSuggestionInfo2.originalText, NotificationLogger.getNotificationLocation(notificationEntry).toMetricsEventEnum(), z);
                    }
                } catch (RemoteException unused) {
                }
            }
        };
        remoteInputController2.getClass();
        remoteInputController2.mCallbacks.add(callback2);
        Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationPresenter$$ExternalSyntheticLambda1
            /* JADX WARN: Type inference failed for: r1v0, types: [com.android.systemui.statusbar.phone.StatusBarNotificationPresenter$$ExternalSyntheticLambda3] */
            /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.statusbar.phone.StatusBarNotificationPresenter$$ExternalSyntheticLambda3] */
            @Override // java.lang.Runnable
            public final void run() {
                final StatusBarNotificationPresenter statusBarNotificationPresenter = StatusBarNotificationPresenter.this;
                VisualInterruptionDecisionProvider visualInterruptionDecisionProvider2 = visualInterruptionDecisionProvider;
                statusBarNotificationPresenter.getClass();
                final int i = 0;
                ?? r1 = new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationPresenter$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        int i2 = i;
                        StatusBarNotificationPresenter statusBarNotificationPresenter2 = statusBarNotificationPresenter;
                        switch (i2) {
                            case 0:
                                if (!statusBarNotificationPresenter2.mPanelExpansionInteractor.isTracking() && !statusBarNotificationPresenter2.mQsController.getExpanded()) {
                                    SysuiStatusBarStateController sysuiStatusBarStateController2 = statusBarNotificationPresenter2.mStatusBarStateController;
                                    if (((StatusBarStateControllerImpl) sysuiStatusBarStateController2).mState == 2 && !statusBarNotificationPresenter2.isCollapsing()) {
                                        sysuiStatusBarStateController2.getClass();
                                        ((StatusBarStateControllerImpl) sysuiStatusBarStateController2).setState(1, false);
                                        break;
                                    }
                                }
                                break;
                            default:
                                if (NotificationStackScrollLayoutController.this.mView.mPulsing && !((BaseHeadsUpManager) statusBarNotificationPresenter2.mHeadsUpManager).hasNotifications$1()) {
                                    statusBarNotificationPresenter2.mDozeScrimController.mPulseOut.run();
                                    break;
                                }
                                break;
                        }
                    }
                };
                ShadeEventCoordinator shadeEventCoordinator2 = statusBarNotificationPresenter.mNotifShadeEventSource;
                if (shadeEventCoordinator2.mShadeEmptiedCallback != null) {
                    throw new IllegalStateException("mShadeEmptiedCallback already set");
                }
                shadeEventCoordinator2.mShadeEmptiedCallback = r1;
                final int i2 = 1;
                ?? r12 = new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBarNotificationPresenter$$ExternalSyntheticLambda3
                    @Override // java.lang.Runnable
                    public final void run() {
                        int i22 = i2;
                        StatusBarNotificationPresenter statusBarNotificationPresenter2 = statusBarNotificationPresenter;
                        switch (i22) {
                            case 0:
                                if (!statusBarNotificationPresenter2.mPanelExpansionInteractor.isTracking() && !statusBarNotificationPresenter2.mQsController.getExpanded()) {
                                    SysuiStatusBarStateController sysuiStatusBarStateController2 = statusBarNotificationPresenter2.mStatusBarStateController;
                                    if (((StatusBarStateControllerImpl) sysuiStatusBarStateController2).mState == 2 && !statusBarNotificationPresenter2.isCollapsing()) {
                                        sysuiStatusBarStateController2.getClass();
                                        ((StatusBarStateControllerImpl) sysuiStatusBarStateController2).setState(1, false);
                                        break;
                                    }
                                }
                                break;
                            default:
                                if (NotificationStackScrollLayoutController.this.mView.mPulsing && !((BaseHeadsUpManager) statusBarNotificationPresenter2.mHeadsUpManager).hasNotifications$1()) {
                                    statusBarNotificationPresenter2.mDozeScrimController.mPulseOut.run();
                                    break;
                                }
                                break;
                        }
                    }
                };
                if (shadeEventCoordinator2.mNotifRemovedByUserCallback != null) {
                    throw new IllegalStateException("mNotifRemovedByUserCallback already set");
                }
                shadeEventCoordinator2.mNotifRemovedByUserCallback = r12;
                VisualInterruptionDecisionProviderImpl visualInterruptionDecisionProviderImpl = (VisualInterruptionDecisionProviderImpl) visualInterruptionDecisionProvider2;
                visualInterruptionDecisionProviderImpl.addCondition(statusBarNotificationPresenter.mAlertsDisabledCondition);
                visualInterruptionDecisionProviderImpl.addCondition(statusBarNotificationPresenter.mVrModeCondition);
                visualInterruptionDecisionProviderImpl.addFilter(statusBarNotificationPresenter.mNeedsRedactionFilter);
                visualInterruptionDecisionProviderImpl.addCondition(statusBarNotificationPresenter.mPanelsDisabledCondition);
                NotificationLockscreenUserManagerImpl notificationLockscreenUserManagerImpl = (NotificationLockscreenUserManagerImpl) statusBarNotificationPresenter.mLockscreenUserManager;
                notificationLockscreenUserManagerImpl.mPresenter = statusBarNotificationPresenter;
                NotificationGutsManager notificationGutsManager2 = statusBarNotificationPresenter.mGutsManager;
                notificationGutsManager2.mPresenter = statusBarNotificationPresenter;
                notificationGutsManager2.mListContainer = statusBarNotificationPresenter.mNotifListContainer;
                notificationGutsManager2.mOnSettingsClickListener = statusBarNotificationPresenter.mOnSettingsClickListener;
                ((BaseHeadsUpManager) statusBarNotificationPresenter.mHeadsUpManager).mUser = notificationLockscreenUserManagerImpl.mCurrentUserId;
                statusBarNotificationPresenter.mCommandQueue.animateCollapsePanels();
                NotificationMediaManager notificationMediaManager2 = statusBarNotificationPresenter.mMediaManager;
                notificationMediaManager2.mBackgroundExecutor.execute(new NotificationMediaManager$$ExternalSyntheticLambda5(notificationMediaManager2));
            }
        };
        if (initController.mTasksExecuted) {
            throw new IllegalStateException("post init tasks have already been executed!");
        }
        initController.mTasks.add(runnable);
    }

    public final boolean isCollapsing() {
        return this.mPanelExpansionInteractor.isCollapsing() || ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).mCurrentState.launchingActivityFromNotification;
    }

    public final void onExpandClicked(NotificationEntry notificationEntry, boolean z) {
        BaseHeadsUpManager baseHeadsUpManager = (BaseHeadsUpManager) this.mHeadsUpManager;
        baseHeadsUpManager.getClass();
        BaseHeadsUpManager.HeadsUpEntry headsUpEntry = baseHeadsUpManager.getHeadsUpEntry(notificationEntry.mKey);
        if (headsUpEntry != null && notificationEntry.isRowPinned()) {
            HeadsUpManagerPhone.HeadsUpEntryPhone headsUpEntryPhone = (HeadsUpManagerPhone.HeadsUpEntryPhone) headsUpEntry;
            if (headsUpEntryPhone.mExpanded != z) {
                headsUpEntryPhone.mExpanded = z;
                if (z) {
                    headsUpEntryPhone.cancelAutoRemovalCallbacks("setExpanded(true)");
                } else {
                    headsUpEntryPhone.updateEntry("setExpanded(false)", false);
                }
            }
        }
        this.mPowerInteractor.wakeUpIfDozing(4, "NOTIFICATION_CLICK");
        if (z) {
            StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) this.mStatusBarStateController;
            if (statusBarStateControllerImpl.mState == 1) {
                ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
                LockscreenShadeTransitionController lockscreenShadeTransitionController = this.mShadeTransitionController;
                lockscreenShadeTransitionController.getClass();
                lockscreenShadeTransitionController.goToLockedShade(expandableNotificationRow, true);
                return;
            }
            if (((Boolean) notificationEntry.mSensitive.getValue()).booleanValue() && this.mDynamicPrivacyController.isInLockedDownShade()) {
                statusBarStateControllerImpl.mLeaveOpenOnKeyguardHide = true;
                this.mActivityStarter.dismissKeyguardThenExecute(new StatusBarNotificationPresenter$$ExternalSyntheticLambda2(), null, false);
            }
        }
    }
}

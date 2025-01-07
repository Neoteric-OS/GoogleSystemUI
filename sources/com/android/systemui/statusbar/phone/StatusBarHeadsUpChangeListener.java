package com.android.systemui.statusbar.phone;

import com.android.systemui.CoreStartable;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowState;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarHeadsUpChangeListener implements OnHeadsUpChangedListener, CoreStartable {
    public final HeadsUpManager mHeadsUpManager;
    public final KeyguardBypassController mKeyguardBypassController;
    public final NotificationRemoteInputManager mNotificationRemoteInputManager;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public final NotificationStackScrollLayoutController mNsslController;
    public final PanelExpansionInteractor mPanelExpansionInteractor;
    public final ShadeViewController mShadeViewController;
    public final StatusBarStateController mStatusBarStateController;
    public final StatusBarWindowControllerImpl mStatusBarWindowController;

    public StatusBarHeadsUpChangeListener(NotificationShadeWindowController notificationShadeWindowController, StatusBarWindowControllerImpl statusBarWindowControllerImpl, ShadeViewController shadeViewController, PanelExpansionInteractor panelExpansionInteractor, NotificationStackScrollLayoutController notificationStackScrollLayoutController, KeyguardBypassController keyguardBypassController, HeadsUpManager headsUpManager, StatusBarStateController statusBarStateController, NotificationRemoteInputManager notificationRemoteInputManager) {
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mStatusBarWindowController = statusBarWindowControllerImpl;
        this.mShadeViewController = shadeViewController;
        this.mPanelExpansionInteractor = panelExpansionInteractor;
        this.mNsslController = notificationStackScrollLayoutController;
        this.mKeyguardBypassController = keyguardBypassController;
        this.mHeadsUpManager = headsUpManager;
        this.mStatusBarStateController = statusBarStateController;
        this.mNotificationRemoteInputManager = notificationRemoteInputManager;
    }

    @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
    public final void onHeadsUpPinnedModeChanged(boolean z) {
        StatusBarWindowControllerImpl statusBarWindowControllerImpl = this.mStatusBarWindowController;
        NotificationShadeWindowController notificationShadeWindowController = this.mNotificationShadeWindowController;
        PanelExpansionInteractor panelExpansionInteractor = this.mPanelExpansionInteractor;
        if (z) {
            NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) notificationShadeWindowController;
            NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
            notificationShadeWindowState.headsUpNotificationShowing = true;
            notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
            StatusBarWindowControllerImpl.State state = statusBarWindowControllerImpl.mCurrentState;
            state.mForceStatusBarVisible = true;
            statusBarWindowControllerImpl.apply(state);
            if (panelExpansionInteractor.isFullyCollapsed()) {
                this.mShadeViewController.updateTouchableRegion();
                return;
            }
            return;
        }
        boolean z2 = this.mKeyguardBypassController.getBypassEnabled() && this.mStatusBarStateController.getState() == 1;
        if (panelExpansionInteractor.isFullyCollapsed() && !panelExpansionInteractor.isTracking() && !z2) {
            ((HeadsUpManagerPhone) this.mHeadsUpManager).setHeadsUpAnimatingAway(true);
            this.mNsslController.mView.mAnimationFinishedRunnables.add(new Runnable() { // from class: com.android.systemui.statusbar.phone.StatusBarHeadsUpChangeListener$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    StatusBarHeadsUpChangeListener statusBarHeadsUpChangeListener = StatusBarHeadsUpChangeListener.this;
                    if (!((BaseHeadsUpManager) statusBarHeadsUpChangeListener.mHeadsUpManager).mHasPinnedNotification) {
                        NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl2 = (NotificationShadeWindowControllerImpl) statusBarHeadsUpChangeListener.mNotificationShadeWindowController;
                        NotificationShadeWindowState notificationShadeWindowState2 = notificationShadeWindowControllerImpl2.mCurrentState;
                        notificationShadeWindowState2.headsUpNotificationShowing = false;
                        notificationShadeWindowControllerImpl2.apply(notificationShadeWindowState2);
                        ((HeadsUpManagerPhone) statusBarHeadsUpChangeListener.mHeadsUpManager).setHeadsUpAnimatingAway(false);
                    }
                    statusBarHeadsUpChangeListener.mNotificationRemoteInputManager.onPanelCollapsed();
                }
            });
            return;
        }
        NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl2 = (NotificationShadeWindowControllerImpl) notificationShadeWindowController;
        NotificationShadeWindowState notificationShadeWindowState2 = notificationShadeWindowControllerImpl2.mCurrentState;
        notificationShadeWindowState2.headsUpNotificationShowing = false;
        notificationShadeWindowControllerImpl2.apply(notificationShadeWindowState2);
        if (z2) {
            StatusBarWindowControllerImpl.State state2 = statusBarWindowControllerImpl.mCurrentState;
            state2.mForceStatusBarVisible = false;
            statusBarWindowControllerImpl.apply(state2);
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        ((BaseHeadsUpManager) this.mHeadsUpManager).addListener(this);
    }
}

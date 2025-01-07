package com.android.systemui.shade;

import android.os.Looper;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewTreeObserver;
import android.view.WindowManagerGlobal;
import com.android.systemui.DejankUtils;
import com.android.systemui.ExpandHelper;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.scene.domain.interactor.WindowRootViewVisibilityInteractor;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarNotificationPresenter;
import com.android.systemui.statusbar.phone.StatusBarRemoteInputCallback$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.google.android.systemui.assist.AssistManagerGoogle;
import dagger.Lazy;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadeControllerImpl extends BaseShadeControllerImpl {
    public final Lazy mAssistManagerLazy;
    public final CommandQueue mCommandQueue;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public final int mDisplayId;
    public boolean mExpandedVisible;
    public final Lazy mGutsManager;
    public final KeyguardStateController mKeyguardStateController;
    public boolean mLockscreenOrShadeVisible;
    public final Executor mMainExecutor;
    public final Lazy mNotifShadeWindowViewController;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public final Lazy mNpvc;
    public CentralSurfacesImpl.AnonymousClass4 mShadeVisibilityListener;
    public final StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public final StatusBarStateController mStatusBarStateController;
    public final StatusBarWindowControllerImpl mStatusBarWindowController;
    public final WindowRootViewVisibilityInteractor mWindowRootViewVisibilityInteractor;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shade.ShadeControllerImpl$2, reason: invalid class name */
    public final class AnonymousClass2 {
        public AnonymousClass2() {
        }
    }

    public ShadeControllerImpl(CommandQueue commandQueue, Executor executor, WindowRootViewVisibilityInteractor windowRootViewVisibilityInteractor, KeyguardStateController keyguardStateController, StatusBarStateController statusBarStateController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, StatusBarWindowControllerImpl statusBarWindowControllerImpl, DeviceProvisionedController deviceProvisionedController, NotificationShadeWindowController notificationShadeWindowController, int i, Lazy lazy, Lazy lazy2, Lazy lazy3, Lazy lazy4) {
        super(commandQueue, statusBarKeyguardViewManager, notificationShadeWindowController, lazy3);
        this.mCommandQueue = commandQueue;
        this.mMainExecutor = executor;
        this.mWindowRootViewVisibilityInteractor = windowRootViewVisibilityInteractor;
        this.mNpvc = lazy2;
        this.mStatusBarStateController = statusBarStateController;
        this.mStatusBarWindowController = statusBarWindowControllerImpl;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mGutsManager = lazy4;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mNotifShadeWindowViewController = lazy;
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.mDisplayId = i;
        this.mKeyguardStateController = keyguardStateController;
        this.mAssistManagerLazy = lazy3;
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void animateCollapseShade(int i, boolean z, boolean z2, float f) {
        int state = this.mStatusBarStateController.getState();
        if (!z && state != 0 && state != 2) {
            runPostCollapseActions();
            return;
        }
        Lazy lazy = this.mNotifShadeWindowViewController;
        if (((NotificationShadeWindowViewController) lazy.get()).mView != null && getNpvc().canBeCollapsed() && (i & 4) == 0) {
            ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).setNotificationShadeFocusable(false);
            NotificationStackScrollLayout notificationStackScrollLayout = ((NotificationShadeWindowViewController) lazy.get()).mStackScrollLayout;
            if (notificationStackScrollLayout != null) {
                ExpandHelper expandHelper = notificationStackScrollLayout.mExpandHelper;
                expandHelper.finishExpanding(0.0f, true, true);
                expandHelper.mResizedView = null;
                expandHelper.mSGD = new ScaleGestureDetector(expandHelper.mContext, expandHelper.mScaleGestureListener);
            }
            getNpvc().collapse(f, true, z2);
        }
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void cancelExpansionAndCollapseShade() {
        if (getNpvc().isTracking()) {
            ((NotificationShadeWindowViewController) this.mNotifShadeWindowViewController.get()).cancelCurrentTouch();
        }
        if (getNpvc().isPanelExpanded() && this.mStatusBarStateController.getState() == 0) {
            animateCollapseShade(0);
        }
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void closeShadeIfOpen() {
        if (getNpvc().isFullyCollapsed()) {
            return;
        }
        this.commandQueue.animateCollapsePanels(2, true);
        notifyVisibilityChanged(false);
        ((AssistManagerGoogle) this.mAssistManagerLazy.get()).hideAssist();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void collapseOnMainThread() {
        if (Looper.getMainLooper().isCurrentThread()) {
            collapseShadeInternal();
        } else {
            this.mMainExecutor.execute(new ShadeControllerImpl$$ExternalSyntheticLambda0(this, 3));
        }
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void collapseShade() {
        collapseShadeInternal();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void collapseShadeForActivityStart() {
        if (!this.mExpandedVisible || this.mStatusBarKeyguardViewManager.isBouncerShowing()) {
            this.mMainExecutor.execute(new ShadeControllerImpl$$ExternalSyntheticLambda0(this, 2));
        } else {
            animateCollapseShade(2, true, true, 1.0f);
        }
    }

    public final boolean collapseShadeInternal() {
        if (getNpvc().isFullyCollapsed()) {
            return false;
        }
        animateCollapseShade(2, true, true, 1.0f);
        notifyVisibilityChanged(false);
        return true;
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void collapseWithDuration(int i) {
        NotificationPanelViewController notificationPanelViewController = (NotificationPanelViewController) this.mNpvc.get();
        notificationPanelViewController.mFixedDuration = i;
        notificationPanelViewController.collapse(1.0f, false);
        notificationPanelViewController.mFixedDuration = -1;
    }

    @Override // com.android.systemui.shade.BaseShadeControllerImpl
    public final void expandToNotifications() {
        getNpvc().expandToNotifications();
    }

    @Override // com.android.systemui.shade.BaseShadeControllerImpl
    public final void expandToQs() {
        getNpvc().expandToQs();
    }

    public final NotificationPanelViewController getNpvc() {
        return (NotificationPanelViewController) this.mNpvc.get();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void instantCollapseShade() {
        getNpvc().instantCollapse();
        runPostCollapseActions();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void instantExpandShade() {
        makeExpandedVisible(true);
        getNpvc().expand(false);
        this.commandQueue.recomputeDisableFlags(this.mDisplayId, false);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final boolean isExpandedVisible() {
        return this.mExpandedVisible;
    }

    @Override // com.android.systemui.shade.ShadeController
    public final boolean isExpandingOrCollapsing() {
        return getNpvc().isExpandingOrCollapsing();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final boolean isShadeEnabled() {
        return this.mCommandQueue.panelsEnabled() && ((DeviceProvisionedControllerImpl) this.mDeviceProvisionedController).isCurrentUserSetup();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final boolean isShadeFullyOpen() {
        return getNpvc().isShadeFullyExpanded();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void makeExpandedInvisible() {
        if (!this.mExpandedVisible || ((NotificationShadeWindowViewController) this.mNotifShadeWindowViewController.get()).mView == null) {
            return;
        }
        getNpvc().collapse(1.0f, false, false);
        this.mExpandedVisible = false;
        notifyVisibilityChanged(false);
        ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).setPanelVisible(false);
        StatusBarWindowControllerImpl statusBarWindowControllerImpl = this.mStatusBarWindowController;
        StatusBarWindowControllerImpl.State state = statusBarWindowControllerImpl.mCurrentState;
        state.mForceStatusBarVisible = false;
        statusBarWindowControllerImpl.apply(state);
        ((NotificationGutsManager) this.mGutsManager.get()).closeAndSaveGuts(true, true, true, true);
        runPostCollapseActions();
        this.mShadeVisibilityListener.expandedVisibleChanged(false);
        this.commandQueue.recomputeDisableFlags(this.mDisplayId, getNpvc().shouldHideStatusBarIconsWhenExpanded());
        if (((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing) {
            return;
        }
        WindowManagerGlobal.getInstance().trimMemory(20);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void makeExpandedVisible(boolean z) {
        CommandQueue commandQueue = this.commandQueue;
        if (z || (!this.mExpandedVisible && commandQueue.panelsEnabled())) {
            this.mExpandedVisible = true;
            ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).setPanelVisible(true);
            notifyVisibilityChanged(true);
            commandQueue.recomputeDisableFlags(this.mDisplayId, !z);
            this.mShadeVisibilityListener.expandedVisibleChanged(true);
        }
    }

    public final void notifyVisibilityChanged(boolean z) {
        AuthContainerView$$ExternalSyntheticOutline0.m(z, this.mWindowRootViewVisibilityInteractor.windowRootViewVisibilityRepository._isLockscreenOrShadeVisible, null);
        if (this.mLockscreenOrShadeVisible != z) {
            this.mLockscreenOrShadeVisible = z;
            if (z) {
                DejankUtils.notifyRendererOfExpensiveFrame(((NotificationShadeWindowViewController) this.mNotifShadeWindowViewController.get()).mView, "onShadeVisibilityChanged");
            }
        }
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void onStatusBarTouch(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1 && this.mExpandedVisible) {
            animateCollapseShade(0);
        }
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void performHapticFeedback() {
        NotificationPanelViewController npvc = getNpvc();
        npvc.mVibratorHelper.getClass();
        npvc.mView.performHapticFeedback(12);
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void postAnimateCollapseShade() {
        this.mMainExecutor.execute(new ShadeControllerImpl$$ExternalSyntheticLambda0(this, 1));
    }

    @Override // com.android.systemui.shade.BaseShadeControllerImpl, com.android.systemui.shade.ShadeController
    public final void postAnimateExpandQs() {
        this.mMainExecutor.execute(new ShadeControllerImpl$$ExternalSyntheticLambda0(this, 4));
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void postAnimateForceCollapseShade() {
        this.mMainExecutor.execute(new ShadeControllerImpl$$ExternalSyntheticLambda0(this, 0));
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void postOnShadeExpanded(final StatusBarRemoteInputCallback$$ExternalSyntheticLambda0 statusBarRemoteInputCallback$$ExternalSyntheticLambda0) {
        NotificationPanelViewController npvc = getNpvc();
        npvc.mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.systemui.shade.ShadeControllerImpl.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                if (((NotificationShadeWindowViewController) ShadeControllerImpl.this.mNotifShadeWindowViewController.get()).mView.isVisibleToUser()) {
                    ShadeControllerImpl.this.getNpvc().mView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    NotificationPanelViewController npvc2 = ShadeControllerImpl.this.getNpvc();
                    npvc2.mView.post(statusBarRemoteInputCallback$$ExternalSyntheticLambda0);
                }
            }
        });
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void setVisibilityListener(CentralSurfacesImpl.AnonymousClass4 anonymousClass4) {
        this.mShadeVisibilityListener = anonymousClass4;
    }

    @Override // com.android.systemui.shade.BaseShadeControllerImpl, com.android.systemui.CoreStartable
    public final void start() {
        getNpvc().mTrackingStartedListener = new ShadeControllerImpl$$ExternalSyntheticLambda1(this);
        getNpvc().mOpenCloseListener = new AnonymousClass2();
    }

    @Override // com.android.systemui.shade.ShadeController
    public final void collapseShade(boolean z) {
        if (z) {
            if (collapseShadeInternal()) {
                return;
            }
            runPostCollapseActions();
            return;
        }
        StatusBarNotificationPresenter statusBarNotificationPresenter = this.notifPresenter;
        if (statusBarNotificationPresenter == null) {
            statusBarNotificationPresenter = null;
        }
        if (statusBarNotificationPresenter.mPanelExpansionInteractor.isFullyCollapsed()) {
            runPostCollapseActions();
        } else {
            instantCollapseShade();
            notifyVisibilityChanged(false);
        }
    }
}

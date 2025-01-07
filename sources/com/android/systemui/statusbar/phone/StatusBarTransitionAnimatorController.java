package com.android.systemui.statusbar.phone;

import android.graphics.PorterDuffXfermode;
import android.view.View;
import android.view.ViewGroup;
import android.window.WindowAnimationState;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.shade.BaseShadeControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationShadeWindowController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarTransitionAnimatorController implements ActivityTransitionAnimator.Controller {
    public static final long ANIMATION_DELAY_ICON_FADE_IN;
    public final CommandQueue commandQueue;
    public final ActivityTransitionAnimator.Controller delegate;
    public final int displayId;
    public boolean hideIconsDuringLaunchAnimation = true;
    public final boolean isLaunchForActivity;
    public final NotificationShadeWindowController notificationShadeWindowController;
    public final ShadeAnimationInteractor shadeAnimationInteractor;
    public final ShadeController shadeController;

    static {
        TransitionAnimator.Timings timings = ActivityTransitionAnimator.TIMINGS;
        ANIMATION_DELAY_ICON_FADE_IN = ((500 - 320) - 50) - 48;
    }

    public StatusBarTransitionAnimatorController(ActivityTransitionAnimator.Controller controller, ShadeAnimationInteractor shadeAnimationInteractor, ShadeController shadeController, NotificationShadeWindowController notificationShadeWindowController, CommandQueue commandQueue, int i, boolean z) {
        this.delegate = controller;
        this.shadeAnimationInteractor = shadeAnimationInteractor;
        this.shadeController = shadeController;
        this.notificationShadeWindowController = notificationShadeWindowController;
        this.commandQueue = commandQueue;
        this.displayId = i;
        this.isLaunchForActivity = z;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final TransitionAnimator.State createAnimatorState() {
        return this.delegate.createAnimatorState();
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final View getOpeningWindowSyncView() {
        return ((NotificationShadeWindowControllerImpl) this.notificationShadeWindowController).mWindowRootView;
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final ViewGroup getTransitionContainer() {
        return this.delegate.getTransitionContainer();
    }

    @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final void getTransitionCookie() {
        this.delegate.getTransitionCookie();
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final WindowAnimationState getWindowAnimatorState() {
        return this.delegate.getWindowAnimatorState();
    }

    @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final boolean isBelowAnimatingWindow() {
        return this.delegate.isBelowAnimatingWindow();
    }

    @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final boolean isDialogLaunch() {
        return this.delegate.isDialogLaunch();
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final boolean isLaunching() {
        return this.delegate.isLaunching();
    }

    @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final void onIntentStarted(boolean z) {
        this.delegate.onIntentStarted(z);
        if (z) {
            this.shadeAnimationInteractor.setIsLaunchingActivity(true);
        } else {
            this.shadeController.collapseOnMainThread();
        }
    }

    @Override // com.android.systemui.animation.ActivityTransitionAnimator.Controller
    public final void onTransitionAnimationCancelled() {
        this.delegate.onTransitionAnimationCancelled();
        this.shadeAnimationInteractor.setIsLaunchingActivity(false);
        BaseShadeControllerImpl baseShadeControllerImpl = (BaseShadeControllerImpl) this.shadeController;
        StatusBarNotificationPresenter statusBarNotificationPresenter = baseShadeControllerImpl.notifPresenter;
        if (statusBarNotificationPresenter == null) {
            statusBarNotificationPresenter = null;
        }
        if (statusBarNotificationPresenter.mPanelExpansionInteractor.isFullyCollapsed()) {
            StatusBarNotificationPresenter statusBarNotificationPresenter2 = baseShadeControllerImpl.notifPresenter;
            if (!(statusBarNotificationPresenter2 != null ? statusBarNotificationPresenter2 : null).isCollapsing() && this.isLaunchForActivity) {
                baseShadeControllerImpl.onClosingFinished();
                return;
            }
        }
        baseShadeControllerImpl.collapseShade(true);
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationEnd(boolean z) {
        this.delegate.onTransitionAnimationEnd(z);
        this.shadeAnimationInteractor.setIsLaunchingActivity(false);
        BaseShadeControllerImpl baseShadeControllerImpl = (BaseShadeControllerImpl) this.shadeController;
        StatusBarNotificationPresenter statusBarNotificationPresenter = baseShadeControllerImpl.notifPresenter;
        if (statusBarNotificationPresenter == null) {
            statusBarNotificationPresenter = null;
        }
        if (!statusBarNotificationPresenter.isCollapsing()) {
            baseShadeControllerImpl.onClosingFinished();
        }
        if (z) {
            baseShadeControllerImpl.instantCollapseShade();
        }
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationProgress(TransitionAnimator.State state, float f, float f2) {
        this.delegate.onTransitionAnimationProgress(state, f, f2);
        PorterDuffXfermode porterDuffXfermode = TransitionAnimator.SRC_MODE;
        boolean z = TransitionAnimator.Companion.getProgress(ActivityTransitionAnimator.TIMINGS, f2, ANIMATION_DELAY_ICON_FADE_IN, 100L) == 0.0f;
        if (z != this.hideIconsDuringLaunchAnimation) {
            this.hideIconsDuringLaunchAnimation = z;
            if (z) {
                return;
            }
            this.commandQueue.recomputeDisableFlags(this.displayId, true);
        }
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void onTransitionAnimationStart(boolean z) {
        this.delegate.onTransitionAnimationStart(z);
        this.shadeAnimationInteractor.setIsLaunchingActivity(true);
        if (z) {
            return;
        }
        TransitionAnimator.Timings timings = ActivityTransitionAnimator.TIMINGS;
        this.shadeController.collapseWithDuration((int) 500);
    }

    @Override // com.android.systemui.animation.TransitionAnimator.Controller
    public final void setTransitionContainer(ViewGroup viewGroup) {
        this.delegate.setTransitionContainer(viewGroup);
    }
}

package com.android.systemui.shade;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.app.animation.Interpolators;
import com.android.internal.policy.SystemBarUtils;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.plugins.qs.QSContainerController;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.shade.ShadeHeaderController.CustomizerAnimationListener;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.wm.shell.R;
import dagger.Lazy;
import java.util.ArrayList;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationsQSContainerController extends ViewController implements QSContainerController {
    public int bottomCutoutInsets;
    public int bottomStableInsets;
    public final DelayableExecutor delayableExecutor;
    public final NotificationsQSContainerController$delayedInsetSetter$1 delayedInsetSetter;
    public int footerActionsOffset;
    public final FragmentService fragmentService;
    public boolean isGestureNavigation;
    public boolean isQSCustomizerAnimating;
    public boolean isQSCustomizing;
    public boolean isQSDetailShowing;
    public final Lazy largeScreenHeaderHelperLazy;
    public boolean largeScreenShadeHeaderActive;
    public int largeScreenShadeHeaderHeight;
    public final NavigationModeController navigationModeController;
    public final NotificationStackScrollLayoutController notificationStackScrollLayoutController;
    public int notificationsBottomMargin;
    public final OverviewProxyService overviewProxyService;
    public int panelMarginHorizontal;
    public int scrimShadeBottomMargin;
    public final ShadeHeaderController shadeHeaderController;
    public int shadeHeaderHeight;
    public final ShadeInteractor shadeInteractor;
    public boolean splitShadeEnabled;
    public final SplitShadeStateControllerImpl splitShadeStateController;
    public final NotificationsQSContainerController$taskbarVisibilityListener$1 taskbarVisibilityListener;
    public boolean taskbarVisible;
    public int topMargin;

    /* JADX WARN: Type inference failed for: r1v2, types: [com.android.systemui.shade.NotificationsQSContainerController$taskbarVisibilityListener$1] */
    public NotificationsQSContainerController(NotificationsQuickSettingsContainer notificationsQuickSettingsContainer, NavigationModeController navigationModeController, OverviewProxyService overviewProxyService, ShadeHeaderController shadeHeaderController, ShadeInteractor shadeInteractor, FragmentService fragmentService, DelayableExecutor delayableExecutor, NotificationStackScrollLayoutController notificationStackScrollLayoutController, SplitShadeStateControllerImpl splitShadeStateControllerImpl, Lazy lazy) {
        super(notificationsQuickSettingsContainer);
        this.navigationModeController = navigationModeController;
        this.overviewProxyService = overviewProxyService;
        this.shadeHeaderController = shadeHeaderController;
        this.shadeInteractor = shadeInteractor;
        this.fragmentService = fragmentService;
        this.delayableExecutor = delayableExecutor;
        this.notificationStackScrollLayoutController = notificationStackScrollLayoutController;
        this.splitShadeStateController = splitShadeStateControllerImpl;
        this.largeScreenHeaderHelperLazy = lazy;
        this.isGestureNavigation = true;
        this.taskbarVisibilityListener = new OverviewProxyService.OverviewProxyListener() { // from class: com.android.systemui.shade.NotificationsQSContainerController$taskbarVisibilityListener$1
            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void onTaskbarStatusUpdated$1(boolean z, boolean z2) {
                NotificationsQSContainerController.this.taskbarVisible = z;
            }
        };
        this.delayedInsetSetter = new NotificationsQSContainerController$delayedInsetSetter$1(this);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        RepeatWhenAttachedKt.repeatWhenAttached(this.mView, EmptyCoroutineContext.INSTANCE, new NotificationsQSContainerController$onInit$1(this, null));
        this.isGestureNavigation = QuickStepContract.isGesturalMode(this.navigationModeController.addListener(new NavigationModeController.ModeChangedListener() { // from class: com.android.systemui.shade.NotificationsQSContainerController$onInit$currentMode$1
            @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
            public final void onNavigationModeChanged(int i) {
                NotificationsQSContainerController.this.isGestureNavigation = QuickStepContract.isGesturalMode(i);
            }
        }));
        ((NotificationsQuickSettingsContainer) this.mView).mStackScroller = this.notificationStackScrollLayoutController.mView;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        updateResources();
        this.overviewProxyService.addCallback((OverviewProxyService.OverviewProxyListener) this.taskbarVisibilityListener);
        NotificationsQuickSettingsContainer notificationsQuickSettingsContainer = (NotificationsQuickSettingsContainer) this.mView;
        notificationsQuickSettingsContainer.mInsetsChangedListener = this.delayedInsetSetter;
        NotificationsQSContainerController$onViewAttached$1 notificationsQSContainerController$onViewAttached$1 = new NotificationsQSContainerController$onViewAttached$1(this, 0);
        notificationsQuickSettingsContainer.mQSFragmentAttachedListener = notificationsQSContainerController$onViewAttached$1;
        QS qs = notificationsQuickSettingsContainer.mQs;
        if (qs != null) {
            notificationsQSContainerController$onViewAttached$1.accept(qs);
        }
        View view = this.mView;
        ((NotificationsQuickSettingsContainer) view).mConfigurationChangedListener = new NotificationsQSContainerController$onViewAttached$1(this, 1);
        this.fragmentService.getFragmentHostManager(view).addTagListener(QS.TAG, (FragmentHostManager.FragmentListener) this.mView);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        this.overviewProxyService.removeCallback((OverviewProxyService.OverviewProxyListener) this.taskbarVisibilityListener);
        NotificationsQuickSettingsContainer notificationsQuickSettingsContainer = (NotificationsQuickSettingsContainer) this.mView;
        notificationsQuickSettingsContainer.getClass();
        notificationsQuickSettingsContainer.mInsetsChangedListener = new NotificationsQuickSettingsContainer$$ExternalSyntheticLambda1(0);
        NotificationsQuickSettingsContainer notificationsQuickSettingsContainer2 = (NotificationsQuickSettingsContainer) this.mView;
        notificationsQuickSettingsContainer2.getClass();
        notificationsQuickSettingsContainer2.mQSFragmentAttachedListener = new NotificationsQuickSettingsContainer$$ExternalSyntheticLambda1(1);
        View view = this.mView;
        ((NotificationsQuickSettingsContainer) view).mConfigurationChangedListener = null;
        FragmentHostManager fragmentHostManager = this.fragmentService.getFragmentHostManager(view);
        FragmentHostManager.FragmentListener fragmentListener = (FragmentHostManager.FragmentListener) this.mView;
        ArrayList arrayList = (ArrayList) fragmentHostManager.mListeners.get(QS.TAG);
        if (arrayList != null && arrayList.remove(fragmentListener) && arrayList.size() == 0) {
            fragmentHostManager.mListeners.remove(QS.TAG);
        }
    }

    @Override // com.android.systemui.plugins.qs.QSContainerController
    public final void setCustomizerAnimating(boolean z) {
        if (this.isQSCustomizerAnimating != z) {
            this.isQSCustomizerAnimating = z;
            ((NotificationsQuickSettingsContainer) this.mView).invalidate();
        }
    }

    @Override // com.android.systemui.plugins.qs.QSContainerController
    public final void setCustomizerShowing(boolean z) {
        QSContainerController.DefaultImpls.setCustomizerShowing(this, z);
    }

    @Override // com.android.systemui.plugins.qs.QSContainerController
    public final void setDetailShowing(boolean z) {
        this.isQSDetailShowing = z;
        updateBottomSpacing();
    }

    public final void updateBottomSpacing() {
        int i;
        int i2;
        boolean z = this.splitShadeEnabled;
        if (!z && (this.isQSCustomizing || this.isQSDetailShowing)) {
            i2 = 0;
            i = 0;
        } else if (this.isGestureNavigation) {
            i2 = this.bottomCutoutInsets;
            i = this.notificationsBottomMargin;
        } else if (this.taskbarVisible) {
            i2 = this.bottomStableInsets;
            i = this.notificationsBottomMargin;
        } else {
            i = this.notificationsBottomMargin + this.bottomStableInsets;
            i2 = 0;
        }
        int i3 = !this.isQSDetailShowing ? z ? (i - this.scrimShadeBottomMargin) - this.footerActionsOffset : this.bottomStableInsets : 0;
        ((NotificationsQuickSettingsContainer) this.mView).setPadding(0, 0, 0, i2);
        NotificationsQuickSettingsContainer notificationsQuickSettingsContainer = (NotificationsQuickSettingsContainer) this.mView;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) notificationsQuickSettingsContainer.mStackScroller.getLayoutParams();
        marginLayoutParams.bottomMargin = i;
        notificationsQuickSettingsContainer.mStackScroller.setLayoutParams(marginLayoutParams);
        NotificationsQuickSettingsContainer notificationsQuickSettingsContainer2 = (NotificationsQuickSettingsContainer) this.mView;
        notificationsQuickSettingsContainer2.mLastQSPaddingBottom = i3;
        View view = notificationsQuickSettingsContainer2.mQSContainer;
        if (view != null) {
            view.setPadding(view.getPaddingLeft(), notificationsQuickSettingsContainer2.mQSContainer.getPaddingTop(), notificationsQuickSettingsContainer2.mQSContainer.getPaddingRight(), i3);
        }
    }

    public final void updateResources() {
        boolean z = true;
        boolean shouldUseSplitNotificationShade = this.splitShadeStateController.shouldUseSplitNotificationShade(this.mView.getResources());
        boolean z2 = shouldUseSplitNotificationShade != this.splitShadeEnabled;
        this.splitShadeEnabled = shouldUseSplitNotificationShade;
        this.largeScreenShadeHeaderActive = this.mView.getResources().getBoolean(R.bool.config_use_large_screen_shade_header);
        this.notificationsBottomMargin = this.mView.getResources().getDimensionPixelSize(R.dimen.notification_panel_margin_bottom);
        Context context = ((LargeScreenHeaderHelper) this.largeScreenHeaderHelperLazy.get()).context;
        this.largeScreenShadeHeaderHeight = Math.max(context.getResources().getDimensionPixelSize(R.dimen.large_screen_shade_header_height), SystemBarUtils.getStatusBarHeight(context));
        int dimensionPixelSize = this.mView.getResources().getDimensionPixelSize(R.dimen.qs_header_height);
        int dimensionPixelSize2 = this.mView.getResources().getDimensionPixelSize(R.dimen.new_qs_header_non_clickable_element_height) + (this.mView.getResources().getDimensionPixelSize(R.dimen.large_screen_shade_header_min_height) * 2);
        if (dimensionPixelSize2 >= dimensionPixelSize) {
            dimensionPixelSize = dimensionPixelSize2;
        }
        this.shadeHeaderHeight = dimensionPixelSize;
        this.panelMarginHorizontal = this.mView.getResources().getDimensionPixelSize(R.dimen.notification_panel_margin_horizontal);
        this.topMargin = this.largeScreenShadeHeaderActive ? this.largeScreenShadeHeaderHeight : this.mView.getResources().getDimensionPixelSize(R.dimen.notification_panel_margin_top);
        ViewGroup viewGroup = (ViewGroup) this.mView;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.getId() == -1) {
                childAt.setId(View.generateViewId());
            }
        }
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) this.mView);
        int dimensionPixelSize3 = this.mView.getResources().getDimensionPixelSize(R.dimen.status_view_margin_horizontal);
        constraintSet.setMargin(R.id.keyguard_status_view, 6, dimensionPixelSize3);
        constraintSet.setMargin(R.id.keyguard_status_view, 7, dimensionPixelSize3);
        constraintSet.connect(R.id.qs_frame, 7, this.splitShadeEnabled ? R.id.qs_edge_guideline : 0, 7);
        constraintSet.setMargin(R.id.qs_frame, 6, this.splitShadeEnabled ? 0 : this.panelMarginHorizontal);
        constraintSet.setMargin(R.id.qs_frame, 7, this.splitShadeEnabled ? 0 : this.panelMarginHorizontal);
        constraintSet.setMargin(R.id.qs_frame, 3, this.topMargin);
        if (this.largeScreenShadeHeaderActive) {
            constraintSet.constrainHeight(R.id.split_shade_status_bar, this.largeScreenShadeHeaderHeight);
        } else {
            constraintSet.constrainHeight(R.id.split_shade_status_bar, this.shadeHeaderHeight);
        }
        NotificationsQuickSettingsContainer notificationsQuickSettingsContainer = (NotificationsQuickSettingsContainer) this.mView;
        notificationsQuickSettingsContainer.getClass();
        constraintSet.applyTo(notificationsQuickSettingsContainer);
        NotificationsQSContainerController$updateResources$scrimMarginChanged$1 notificationsQSContainerController$updateResources$scrimMarginChanged$1 = new NotificationsQSContainerController$updateResources$scrimMarginChanged$1(this, NotificationsQSContainerController.class, "scrimShadeBottomMargin", "getScrimShadeBottomMargin()I", 0);
        int dimensionPixelSize4 = this.mView.getResources().getDimensionPixelSize(R.dimen.split_shade_notifications_scrim_margin_bottom);
        int intValue = ((Number) notificationsQSContainerController$updateResources$scrimMarginChanged$1.get()).intValue();
        notificationsQSContainerController$updateResources$scrimMarginChanged$1.set(Integer.valueOf(dimensionPixelSize4));
        boolean z3 = intValue != dimensionPixelSize4;
        NotificationsQSContainerController$updateResources$footerOffsetChanged$1 notificationsQSContainerController$updateResources$footerOffsetChanged$1 = new NotificationsQSContainerController$updateResources$footerOffsetChanged$1(this, NotificationsQSContainerController.class, "footerActionsOffset", "getFooterActionsOffset()I", 0);
        int dimensionPixelSize5 = this.mView.getResources().getDimensionPixelSize(R.dimen.qs_footer_actions_bottom_padding) + this.mView.getResources().getDimensionPixelSize(R.dimen.qs_footer_action_inset);
        int intValue2 = ((Number) notificationsQSContainerController$updateResources$footerOffsetChanged$1.get()).intValue();
        notificationsQSContainerController$updateResources$footerOffsetChanged$1.set(Integer.valueOf(dimensionPixelSize5));
        boolean z4 = intValue2 != dimensionPixelSize5;
        if (!z3 && !z4) {
            z = false;
        }
        if (z2 || z) {
            updateBottomSpacing();
        }
    }

    @Override // com.android.systemui.plugins.qs.QSContainerController
    public final void setCustomizerShowing(boolean z, long j) {
        if (z != this.isQSCustomizing) {
            this.isQSCustomizing = z;
            ShadeHeaderController shadeHeaderController = this.shadeHeaderController;
            shadeHeaderController.header.animate().setDuration(j).alpha(z ? 0.0f : 1.0f).setInterpolator(z ? Interpolators.ALPHA_OUT : Interpolators.ALPHA_IN).setListener(shadeHeaderController.new CustomizerAnimationListener(z)).start();
            updateBottomSpacing();
        }
    }
}

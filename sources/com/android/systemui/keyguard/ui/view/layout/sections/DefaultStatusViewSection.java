package com.android.systemui.keyguard.ui.view.layout.sections;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.android.keyguard.KeyguardStatusView;
import com.android.keyguard.KeyguardStatusViewController;
import com.android.systemui.keyguard.KeyguardViewConfigurator;
import com.android.systemui.keyguard.shared.model.KeyguardSection;
import com.android.systemui.media.controls.ui.controller.KeyguardMediaController;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.util.Utils;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultStatusViewSection extends KeyguardSection {
    public final Context context;
    public final KeyguardMediaController keyguardMediaController;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory keyguardStatusViewComponentFactory;
    public final Lazy keyguardViewConfigurator;
    public final NotificationPanelView notificationPanelView;
    public final Lazy notificationPanelViewController;
    public final SplitShadeStateControllerImpl splitShadeStateController;

    public DefaultStatusViewSection(Context context, NotificationPanelView notificationPanelView, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, Lazy lazy, Lazy lazy2, KeyguardMediaController keyguardMediaController, SplitShadeStateControllerImpl splitShadeStateControllerImpl) {
        this.context = context;
        this.notificationPanelView = notificationPanelView;
        this.keyguardStatusViewComponentFactory = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
        this.keyguardViewConfigurator = lazy;
        this.notificationPanelViewController = lazy2;
        this.keyguardMediaController = keyguardMediaController;
        this.splitShadeStateController = splitShadeStateControllerImpl;
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void addViews(ConstraintLayout constraintLayout) {
        NotificationPanelView notificationPanelView = this.notificationPanelView;
        View findViewById = notificationPanelView.findViewById(R.id.keyguard_status_view);
        if (findViewById != null) {
            notificationPanelView.removeView(findViewById);
        }
        KeyguardStatusView keyguardStatusView = (KeyguardStatusView) LayoutInflater.from(this.context).inflate(R.layout.keyguard_status_view, (ViewGroup) constraintLayout, false);
        keyguardStatusView.setClipChildren(false);
        View findViewById2 = keyguardStatusView.findViewById(R.id.left_aligned_notification_icon_container);
        if (findViewById2 != null) {
            findViewById2.setVisibility(8);
        }
        constraintLayout.addView(keyguardStatusView);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void applyConstraints(ConstraintSet constraintSet) {
        int statusBarHeaderHeightKeyguard;
        constraintSet.constrainWidth(R.id.keyguard_status_view, 0);
        constraintSet.constrainHeight(R.id.keyguard_status_view, -2);
        constraintSet.connect(R.id.keyguard_status_view, 3, 0, 3);
        constraintSet.connect(R.id.keyguard_status_view, 6, 0, 6);
        constraintSet.connect(R.id.keyguard_status_view, 7, 0, 7);
        if (this.splitShadeStateController.shouldUseSplitNotificationShade(this.context.getResources())) {
            statusBarHeaderHeightKeyguard = this.context.getResources().getDimensionPixelSize(R.dimen.keyguard_split_shade_top_margin);
        } else {
            statusBarHeaderHeightKeyguard = Utils.getStatusBarHeaderHeightKeyguard(this.context) + this.context.getResources().getDimensionPixelSize(R.dimen.keyguard_clock_top_margin);
        }
        constraintSet.setMargin(R.id.keyguard_status_view, 3, statusBarHeaderHeightKeyguard);
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void bindData(ConstraintLayout constraintLayout) {
        KeyguardStatusView keyguardStatusView = (KeyguardStatusView) constraintLayout.findViewById(R.id.keyguard_status_view);
        if (keyguardStatusView != null) {
            Display display = this.context.getDisplay();
            DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = this.keyguardStatusViewComponentFactory;
            display.getClass();
            KeyguardStatusViewController keyguardStatusViewController = new DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl, keyguardStatusView, display).getKeyguardStatusViewController();
            keyguardStatusViewController.init$9();
            ViewGroup viewGroup = (ViewGroup) keyguardStatusView.requireViewById(R.id.status_view_media_container);
            KeyguardMediaController keyguardMediaController = this.keyguardMediaController;
            keyguardMediaController.splitShadeContainer = viewGroup;
            keyguardMediaController.reattachHostView();
            keyguardMediaController.refreshMediaPosition("attachSplitShadeContainer");
            ((KeyguardViewConfigurator) this.keyguardViewConfigurator.get()).keyguardStatusViewController = keyguardStatusViewController;
            ((NotificationPanelViewController) this.notificationPanelViewController.get()).updateStatusViewController();
        }
    }

    @Override // com.android.systemui.keyguard.shared.model.KeyguardSection
    public final void removeViews(ConstraintLayout constraintLayout) {
        ExtensionsKt.removeView(constraintLayout, R.id.keyguard_status_view);
        ((KeyguardViewConfigurator) this.keyguardViewConfigurator.get()).keyguardStatusViewController = null;
    }
}

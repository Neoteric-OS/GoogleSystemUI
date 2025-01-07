package com.android.systemui.back.domain.interactor;

import android.window.OnBackInvokedCallback;
import com.android.systemui.CoreStartable;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.scene.domain.interactor.WindowRootViewVisibilityInteractor;
import com.android.systemui.shade.QuickSettingsController;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.domain.interactor.ShadeBackActionInteractor;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BackActionInteractor implements CoreStartable {
    public final BackActionInteractor$callback$2 callback = new OnBackInvokedCallback() { // from class: com.android.systemui.back.domain.interactor.BackActionInteractor$callback$2
        @Override // android.window.OnBackInvokedCallback
        public final void onBackInvoked() {
            BackActionInteractor.this.onBackRequested();
        }
    };
    public boolean isCallbackRegistered;
    public final NotificationShadeWindowController notificationShadeWindowController;
    public QuickSettingsController qsController;
    public final CoroutineScope scope;
    public ShadeBackActionInteractor shadeBackActionInteractor;
    public final ShadeController shadeController;
    public final StatusBarKeyguardViewManager statusBarKeyguardViewManager;
    public final StatusBarStateController statusBarStateController;
    public final WindowRootViewVisibilityInteractor windowRootViewVisibilityInteractor;

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.back.domain.interactor.BackActionInteractor$callback$2] */
    public BackActionInteractor(CoroutineScope coroutineScope, StatusBarStateController statusBarStateController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, ShadeController shadeController, NotificationShadeWindowController notificationShadeWindowController, WindowRootViewVisibilityInteractor windowRootViewVisibilityInteractor) {
        this.scope = coroutineScope;
        this.statusBarStateController = statusBarStateController;
        this.statusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.shadeController = shadeController;
        this.notificationShadeWindowController = notificationShadeWindowController;
        this.windowRootViewVisibilityInteractor = windowRootViewVisibilityInteractor;
    }

    public final boolean onBackRequested() {
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.statusBarKeyguardViewManager;
        if (statusBarKeyguardViewManager.mPrimaryBouncerInteractor.isFullyShowing()) {
            statusBarKeyguardViewManager.onBackPressed();
            return true;
        }
        QuickSettingsController quickSettingsController = this.qsController;
        if (quickSettingsController == null) {
            quickSettingsController = null;
        }
        if (quickSettingsController.isCustomizing()) {
            QuickSettingsController quickSettingsController2 = this.qsController;
            (quickSettingsController2 != null ? quickSettingsController2 : null).closeQsCustomizer();
            return true;
        }
        QuickSettingsController quickSettingsController3 = this.qsController;
        if (quickSettingsController3 == null) {
            quickSettingsController3 = null;
        }
        if (quickSettingsController3.getExpanded()) {
            ShadeBackActionInteractor shadeBackActionInteractor = this.shadeBackActionInteractor;
            (shadeBackActionInteractor != null ? shadeBackActionInteractor : null).animateCollapseQs(false);
            return true;
        }
        ShadeBackActionInteractor shadeBackActionInteractor2 = this.shadeBackActionInteractor;
        if (shadeBackActionInteractor2 == null) {
            shadeBackActionInteractor2 = null;
        }
        if (shadeBackActionInteractor2.closeUserSwitcherIfOpen()) {
            return true;
        }
        StatusBarStateController statusBarStateController = this.statusBarStateController;
        if (statusBarStateController.getState() == 1 || statusBarStateController.getState() == 2 || statusBarKeyguardViewManager.mBouncerShowingOverDream) {
            return false;
        }
        ShadeBackActionInteractor shadeBackActionInteractor3 = this.shadeBackActionInteractor;
        if (shadeBackActionInteractor3 == null) {
            shadeBackActionInteractor3 = null;
        }
        if (shadeBackActionInteractor3.canBeCollapsed()) {
            ShadeBackActionInteractor shadeBackActionInteractor4 = this.shadeBackActionInteractor;
            (shadeBackActionInteractor4 != null ? shadeBackActionInteractor4 : null).onBackPressed();
            this.shadeController.animateCollapseShade(0);
        }
        return true;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        BuildersKt.launch$default(this.scope, null, null, new BackActionInteractor$start$1(this, null), 3);
    }
}

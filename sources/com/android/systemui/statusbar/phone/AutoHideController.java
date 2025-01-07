package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.os.Handler;
import android.view.IWindowManager;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityManager;
import com.android.systemui.statusbar.AutoHideUiElement;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AutoHideController {
    public final AccessibilityManager mAccessibilityManager;
    public final AutoHideController$$ExternalSyntheticLambda0 mAutoHide = new AutoHideController$$ExternalSyntheticLambda0(this, 0);
    public boolean mAutoHideSuspended;
    public final int mDisplayId;
    public final Handler mHandler;
    public AutoHideUiElement mNavigationBar;
    public CentralSurfacesImpl.AnonymousClass4 mStatusBar;
    public final IWindowManager mWindowManagerService;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final Handler mHandler;
        public final IWindowManager mIWindowManager;

        public Factory(Handler handler, IWindowManager iWindowManager) {
            this.mHandler = handler;
            this.mIWindowManager = iWindowManager;
        }
    }

    public AutoHideController(Context context, Handler handler, IWindowManager iWindowManager) {
        this.mAccessibilityManager = (AccessibilityManager) context.getSystemService(AccessibilityManager.class);
        this.mHandler = handler;
        this.mWindowManagerService = iWindowManager;
        this.mDisplayId = context.getDisplayId();
    }

    public final void checkUserAutoHide(MotionEvent motionEvent) {
        boolean z = isAnyTransientBarShown() && motionEvent.getAction() == 4 && motionEvent.getX() == 0.0f && motionEvent.getY() == 0.0f;
        CentralSurfacesImpl.AnonymousClass4 anonymousClass4 = this.mStatusBar;
        if (anonymousClass4 != null) {
            z &= anonymousClass4.shouldHideOnTouch();
        }
        AutoHideUiElement autoHideUiElement = this.mNavigationBar;
        if (autoHideUiElement != null) {
            z &= autoHideUiElement.shouldHideOnTouch();
        }
        if (z) {
            this.mAutoHideSuspended = false;
            AutoHideController$$ExternalSyntheticLambda0 autoHideController$$ExternalSyntheticLambda0 = this.mAutoHide;
            Handler handler = this.mHandler;
            handler.removeCallbacks(autoHideController$$ExternalSyntheticLambda0);
            handler.postDelayed(autoHideController$$ExternalSyntheticLambda0, this.mAccessibilityManager.getRecommendedTimeoutMillis(350, 4));
        }
    }

    public final boolean isAnyTransientBarShown() {
        CentralSurfacesImpl.AnonymousClass4 anonymousClass4 = this.mStatusBar;
        if (anonymousClass4 != null && anonymousClass4.isVisible()) {
            return true;
        }
        AutoHideUiElement autoHideUiElement = this.mNavigationBar;
        return autoHideUiElement != null && autoHideUiElement.isVisible();
    }

    public final void touchAutoHide() {
        boolean isAnyTransientBarShown = isAnyTransientBarShown();
        Handler handler = this.mHandler;
        AutoHideController$$ExternalSyntheticLambda0 autoHideController$$ExternalSyntheticLambda0 = this.mAutoHide;
        if (!isAnyTransientBarShown) {
            this.mAutoHideSuspended = false;
            handler.removeCallbacks(autoHideController$$ExternalSyntheticLambda0);
        } else {
            this.mAutoHideSuspended = false;
            handler.removeCallbacks(autoHideController$$ExternalSyntheticLambda0);
            handler.postDelayed(autoHideController$$ExternalSyntheticLambda0, this.mAccessibilityManager.getRecommendedTimeoutMillis(2250, 4));
        }
    }
}

package com.android.systemui.accessibility.floatingmenu;

import android.content.Context;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.util.settings.SecureSettings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MenuViewLayerController implements IAccessibilityFloatingMenu {
    public boolean mIsShowing;
    public final MenuViewLayer mMenuViewLayer;
    public final ViewCaptureAwareWindowManager mWindowManager;

    public MenuViewLayerController(Context context, WindowManager windowManager, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, AccessibilityManager accessibilityManager, SecureSettings secureSettings, NavigationModeController navigationModeController) {
        this.mWindowManager = viewCaptureAwareWindowManager;
        MenuViewModel menuViewModel = new MenuViewModel(context, accessibilityManager, secureSettings);
        MenuViewAppearance menuViewAppearance = new MenuViewAppearance(context, windowManager);
        this.mMenuViewLayer = new MenuViewLayer(context, windowManager, accessibilityManager, menuViewModel, menuViewAppearance, new MenuView(context, menuViewModel, menuViewAppearance), this, secureSettings, navigationModeController);
    }
}

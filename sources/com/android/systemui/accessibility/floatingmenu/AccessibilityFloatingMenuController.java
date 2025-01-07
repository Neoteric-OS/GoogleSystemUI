package com.android.systemui.accessibility.floatingmenu;

import android.R;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.IUserInitializationCompleteCallback;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.systemui.accessibility.AccessibilityButtonModeObserver;
import com.android.systemui.accessibility.AccessibilityButtonTargetsObserver;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.util.settings.SecureSettings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AccessibilityFloatingMenuController implements AccessibilityButtonModeObserver.ModeChangedListener, AccessibilityButtonTargetsObserver.TargetsChangedListener {
    public final AccessibilityButtonModeObserver mAccessibilityButtonModeObserver;
    public final AccessibilityButtonTargetsObserver mAccessibilityButtonTargetsObserver;
    public final AccessibilityManager mAccessibilityManager;
    public int mBtnMode;
    public String mBtnTargets;
    public Context mContext;
    public final DisplayManager mDisplayManager;
    public final DisplayTracker mDisplayTracker;
    IAccessibilityFloatingMenu mFloatingMenu;
    Handler mHandler;
    public boolean mIsUserInInitialization;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final NavigationModeController mNavigationModeController;
    public final SecureSettings mSecureSettings;
    public final ViewCaptureAwareWindowManager mViewCaptureAwareWindowManager;
    public final WindowManager mWindowManager;
    final KeyguardUpdateMonitorCallback mKeyguardCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.accessibility.floatingmenu.AccessibilityFloatingMenuController.1
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onKeyguardVisibilityChanged(boolean z) {
            AccessibilityFloatingMenuController accessibilityFloatingMenuController = AccessibilityFloatingMenuController.this;
            accessibilityFloatingMenuController.mIsKeyguardVisible = z;
            accessibilityFloatingMenuController.handleFloatingMenuVisibility(accessibilityFloatingMenuController.mBtnMode, accessibilityFloatingMenuController.mBtnTargets, z);
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onUserSwitching(int i) {
            AccessibilityFloatingMenuController accessibilityFloatingMenuController = AccessibilityFloatingMenuController.this;
            IAccessibilityFloatingMenu iAccessibilityFloatingMenu = accessibilityFloatingMenuController.mFloatingMenu;
            if (iAccessibilityFloatingMenu != null) {
                MenuViewLayerController menuViewLayerController = (MenuViewLayerController) iAccessibilityFloatingMenu;
                if (menuViewLayerController.mIsShowing) {
                    menuViewLayerController.mIsShowing = false;
                    menuViewLayerController.mWindowManager.removeView(menuViewLayerController.mMenuViewLayer);
                }
                accessibilityFloatingMenuController.mFloatingMenu = null;
            }
            accessibilityFloatingMenuController.mIsUserInInitialization = true;
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onUserUnlocked() {
            AccessibilityFloatingMenuController accessibilityFloatingMenuController = AccessibilityFloatingMenuController.this;
            accessibilityFloatingMenuController.handleFloatingMenuVisibility(accessibilityFloatingMenuController.mBtnMode, accessibilityFloatingMenuController.mBtnTargets, accessibilityFloatingMenuController.mIsKeyguardVisible);
        }
    };
    final UserInitializationCompleteCallback mUserInitializationCompleteCallback = new UserInitializationCompleteCallback();
    public boolean mIsKeyguardVisible = false;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UserInitializationCompleteCallback extends IUserInitializationCompleteCallback.Stub {
        public UserInitializationCompleteCallback() {
        }

        public final void onUserInitializationComplete(int i) {
            AccessibilityFloatingMenuController accessibilityFloatingMenuController = AccessibilityFloatingMenuController.this;
            int i2 = 0;
            accessibilityFloatingMenuController.mIsUserInInitialization = false;
            accessibilityFloatingMenuController.mContext = accessibilityFloatingMenuController.mContext.createContextAsUser(UserHandle.of(i), 0);
            AccessibilityFloatingMenuController accessibilityFloatingMenuController2 = AccessibilityFloatingMenuController.this;
            try {
                i2 = Integer.parseInt(accessibilityFloatingMenuController2.mAccessibilityButtonModeObserver.getSettingsValue());
            } catch (NumberFormatException e) {
                Log.e("A11yButtonModeObserver", "Invalid string for  " + e);
            }
            accessibilityFloatingMenuController2.mBtnMode = i2;
            AccessibilityFloatingMenuController accessibilityFloatingMenuController3 = AccessibilityFloatingMenuController.this;
            accessibilityFloatingMenuController3.mBtnTargets = accessibilityFloatingMenuController3.mAccessibilityButtonTargetsObserver.getSettingsValue();
            AccessibilityFloatingMenuController.this.mHandler.post(new Runnable() { // from class: com.android.systemui.accessibility.floatingmenu.AccessibilityFloatingMenuController$UserInitializationCompleteCallback$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AccessibilityFloatingMenuController accessibilityFloatingMenuController4 = AccessibilityFloatingMenuController.this;
                    accessibilityFloatingMenuController4.handleFloatingMenuVisibility(accessibilityFloatingMenuController4.mBtnMode, accessibilityFloatingMenuController4.mBtnTargets, accessibilityFloatingMenuController4.mIsKeyguardVisible);
                }
            });
        }
    }

    public AccessibilityFloatingMenuController(Context context, WindowManager windowManager, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, DisplayManager displayManager, AccessibilityManager accessibilityManager, AccessibilityButtonTargetsObserver accessibilityButtonTargetsObserver, AccessibilityButtonModeObserver accessibilityButtonModeObserver, KeyguardUpdateMonitor keyguardUpdateMonitor, SecureSettings secureSettings, DisplayTracker displayTracker, NavigationModeController navigationModeController, Handler handler) {
        this.mContext = context;
        this.mWindowManager = windowManager;
        this.mViewCaptureAwareWindowManager = viewCaptureAwareWindowManager;
        this.mDisplayManager = displayManager;
        this.mAccessibilityManager = accessibilityManager;
        this.mAccessibilityButtonTargetsObserver = accessibilityButtonTargetsObserver;
        this.mAccessibilityButtonModeObserver = accessibilityButtonModeObserver;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mSecureSettings = secureSettings;
        this.mDisplayTracker = displayTracker;
        this.mNavigationModeController = navigationModeController;
        this.mHandler = handler;
    }

    public final void handleFloatingMenuVisibility(int i, String str, boolean z) {
        if (z || this.mIsUserInInitialization) {
            IAccessibilityFloatingMenu iAccessibilityFloatingMenu = this.mFloatingMenu;
            if (iAccessibilityFloatingMenu == null) {
                return;
            }
            MenuViewLayerController menuViewLayerController = (MenuViewLayerController) iAccessibilityFloatingMenu;
            if (menuViewLayerController.mIsShowing) {
                menuViewLayerController.mIsShowing = false;
                menuViewLayerController.mWindowManager.removeView(menuViewLayerController.mMenuViewLayer);
            }
            this.mFloatingMenu = null;
            return;
        }
        if (i != 1 || TextUtils.isEmpty(str)) {
            IAccessibilityFloatingMenu iAccessibilityFloatingMenu2 = this.mFloatingMenu;
            if (iAccessibilityFloatingMenu2 == null) {
                return;
            }
            MenuViewLayerController menuViewLayerController2 = (MenuViewLayerController) iAccessibilityFloatingMenu2;
            if (menuViewLayerController2.mIsShowing) {
                menuViewLayerController2.mIsShowing = false;
                menuViewLayerController2.mWindowManager.removeView(menuViewLayerController2.mMenuViewLayer);
            }
            this.mFloatingMenu = null;
            return;
        }
        if (this.mFloatingMenu == null) {
            DisplayManager displayManager = this.mDisplayManager;
            this.mDisplayTracker.getClass();
            this.mFloatingMenu = new MenuViewLayerController(this.mContext.createWindowContext(displayManager.getDisplay(0), 2024, null), this.mWindowManager, this.mViewCaptureAwareWindowManager, this.mAccessibilityManager, this.mSecureSettings, this.mNavigationModeController);
        }
        MenuViewLayerController menuViewLayerController3 = (MenuViewLayerController) this.mFloatingMenu;
        if (menuViewLayerController3.mIsShowing) {
            return;
        }
        menuViewLayerController3.mIsShowing = true;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2024, 8, -3);
        layoutParams.receiveInsetsIgnoringZOrder = true;
        layoutParams.privateFlags |= 2097152;
        layoutParams.windowAnimations = R.style.Animation.Translucent;
        layoutParams.setFitInsetsTypes(0);
        layoutParams.layoutInDisplayCutoutMode = 3;
        menuViewLayerController3.mWindowManager.addView(menuViewLayerController3.mMenuViewLayer, layoutParams);
    }

    public final void init$9() {
        int i;
        AccessibilityButtonModeObserver accessibilityButtonModeObserver = this.mAccessibilityButtonModeObserver;
        try {
            i = Integer.parseInt(accessibilityButtonModeObserver.getSettingsValue());
        } catch (NumberFormatException e) {
            Log.e("A11yButtonModeObserver", "Invalid string for  " + e);
            i = 0;
        }
        this.mBtnMode = i;
        AccessibilityButtonTargetsObserver accessibilityButtonTargetsObserver = this.mAccessibilityButtonTargetsObserver;
        this.mBtnTargets = accessibilityButtonTargetsObserver.getSettingsValue();
        accessibilityButtonModeObserver.addListener(this);
        accessibilityButtonTargetsObserver.addListener(this);
        this.mKeyguardUpdateMonitor.registerCallback(this.mKeyguardCallback);
        this.mAccessibilityManager.registerUserInitializationCompleteCallback(this.mUserInitializationCompleteCallback);
    }

    @Override // com.android.systemui.accessibility.AccessibilityButtonModeObserver.ModeChangedListener
    public final void onAccessibilityButtonModeChanged(int i) {
        this.mBtnMode = i;
        handleFloatingMenuVisibility(i, this.mBtnTargets, this.mIsKeyguardVisible);
    }

    @Override // com.android.systemui.accessibility.AccessibilityButtonTargetsObserver.TargetsChangedListener
    public final void onAccessibilityButtonTargetsChanged(String str) {
        this.mBtnTargets = str;
        handleFloatingMenuVisibility(this.mBtnMode, str, this.mIsKeyguardVisible);
    }
}

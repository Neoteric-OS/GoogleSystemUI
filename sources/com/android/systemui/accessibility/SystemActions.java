package com.android.systemui.accessibility;

import android.R;
import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.view.IWindowManager;
import android.view.KeyEvent;
import android.view.WindowManagerGlobal;
import android.view.accessibility.AccessibilityManager;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.accessibility.util.AccessibilityUtils;
import com.android.internal.util.ScreenshotHelper;
import com.android.systemui.CoreStartable;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.shade.BaseShadeControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.phone.StatusBarWindowCallback;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.Assert;
import dagger.Lazy;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SystemActions implements CoreStartable, ConfigurationController.ConfigurationListener {
    public final AccessibilityManager mA11yManager;
    public final Context mContext;
    public boolean mDismissNotificationShadeActionRegistered;
    public final DisplayTracker mDisplayTracker;
    public final KeyguardStateController mKeyguardStateController;
    public Locale mLocale;
    public final NotificationShadeWindowController mNotificationShadeController;
    public final Lazy mPanelExpansionInteractor;
    public final Optional mRecentsOptional;
    public final ScreenshotHelper mScreenshotHelper;
    public final ShadeController mShadeController;
    public final UserTracker mUserTracker;
    public final SystemActionsBroadcastReceiver mReceiver = new SystemActionsBroadcastReceiver();
    public final StatusBarWindowCallback mNotificationShadeCallback = new StatusBarWindowCallback() { // from class: com.android.systemui.accessibility.SystemActions$$ExternalSyntheticLambda0
        @Override // com.android.systemui.statusbar.phone.StatusBarWindowCallback
        public final void onStateChanged(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
            SystemActions.this.registerOrUnregisterDismissNotificationShadeAction();
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SystemActionsBroadcastReceiver extends BroadcastReceiver {
        public static final /* synthetic */ int $r8$clinit = 0;

        public SystemActionsBroadcastReceiver() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            char c;
            String action = intent.getAction();
            switch (action.hashCode()) {
                case -1103811776:
                    if (action.equals("SYSTEM_ACTION_BACK")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1103619272:
                    if (action.equals("SYSTEM_ACTION_HOME")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -1103479880:
                    if (action.equals("SYSTEM_ACTION_MENU")) {
                        c = 18;
                        break;
                    }
                    c = 65535;
                    break;
                case -768644801:
                    if (action.equals("SYSTEM_ACTION_MEDIA_PLAY_PAUSE")) {
                        c = 19;
                        break;
                    }
                    c = 65535;
                    break;
                case -720484549:
                    if (action.equals("SYSTEM_ACTION_POWER_DIALOG")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -535129457:
                    if (action.equals("SYSTEM_ACTION_NOTIFICATIONS")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -181386672:
                    if (action.equals("SYSTEM_ACTION_ACCESSIBILITY_SHORTCUT")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                case -153384569:
                    if (action.equals("SYSTEM_ACTION_LOCK_SCREEN")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 42571871:
                    if (action.equals("SYSTEM_ACTION_RECENTS")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 526987266:
                    if (action.equals("SYSTEM_ACTION_ACCESSIBILITY_BUTTON_MENU")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 689657964:
                    if (action.equals("SYSTEM_ACTION_DPAD_CENTER")) {
                        c = 17;
                        break;
                    }
                    c = 65535;
                    break;
                case 815482418:
                    if (action.equals("SYSTEM_ACTION_DPAD_UP")) {
                        c = '\r';
                        break;
                    }
                    c = 65535;
                    break;
                case 1245940668:
                    if (action.equals("SYSTEM_ACTION_ACCESSIBILITY_BUTTON")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case 1493428793:
                    if (action.equals("SYSTEM_ACTION_HEADSET_HOOK")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 1579999269:
                    if (action.equals("SYSTEM_ACTION_TAKE_SCREENSHOT")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 1668921710:
                    if (action.equals("SYSTEM_ACTION_QUICK_SETTINGS")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1698779909:
                    if (action.equals("SYSTEM_ACTION_DPAD_RIGHT")) {
                        c = 16;
                        break;
                    }
                    c = 65535;
                    break;
                case 1894867256:
                    if (action.equals("SYSTEM_ACTION_ACCESSIBILITY_DISMISS_NOTIFICATION_SHADE")) {
                        c = '\f';
                        break;
                    }
                    c = 65535;
                    break;
                case 1994051193:
                    if (action.equals("SYSTEM_ACTION_DPAD_DOWN")) {
                        c = 14;
                        break;
                    }
                    c = 65535;
                    break;
                case 1994279390:
                    if (action.equals("SYSTEM_ACTION_DPAD_LEFT")) {
                        c = 15;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    SystemActions.this.sendDownAndUpKeyEvents(4);
                    break;
                case 1:
                    SystemActions.this.sendDownAndUpKeyEvents(3);
                    break;
                case 2:
                    SystemActions.this.mRecentsOptional.ifPresent(new SystemActions$$ExternalSyntheticLambda1());
                    break;
                case 3:
                    ((BaseShadeControllerImpl) SystemActions.this.mShadeController).animateExpandShade();
                    break;
                case 4:
                    ((BaseShadeControllerImpl) SystemActions.this.mShadeController).animateExpandQs();
                    break;
                case 5:
                    SystemActions.this.getClass();
                    try {
                        WindowManagerGlobal.getWindowManagerService().showGlobalActions();
                        break;
                    } catch (RemoteException unused) {
                        Log.e("SystemActions", "failed to display power dialog.");
                        return;
                    }
                case 6:
                    SystemActions systemActions = SystemActions.this;
                    systemActions.getClass();
                    IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();
                    ((PowerManager) systemActions.mContext.getSystemService(PowerManager.class)).goToSleep(SystemClock.uptimeMillis(), 7, 0);
                    try {
                        windowManagerService.lockNow((Bundle) null);
                        break;
                    } catch (RemoteException unused2) {
                        Log.e("SystemActions", "failed to lock screen.");
                        return;
                    }
                case 7:
                    SystemActions.this.mScreenshotHelper.takeScreenshot(4, new Handler(Looper.getMainLooper()), (Consumer) null);
                    break;
                case '\b':
                    SystemActions.this.handleHeadsetHook();
                    break;
                case '\t':
                    SystemActions systemActions2 = SystemActions.this;
                    AccessibilityManager accessibilityManager = systemActions2.mA11yManager;
                    systemActions2.mDisplayTracker.getClass();
                    accessibilityManager.notifyAccessibilityButtonClicked(0);
                    break;
                case '\n':
                    SystemActions systemActions3 = SystemActions.this;
                    AccessibilityManager accessibilityManager2 = systemActions3.mA11yManager;
                    systemActions3.mDisplayTracker.getClass();
                    accessibilityManager2.notifyAccessibilityButtonLongClicked(0);
                    break;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    SystemActions.this.mA11yManager.performAccessibilityShortcut();
                    break;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    SystemActions.this.mShadeController.animateCollapseShade(0);
                    break;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    SystemActions.this.sendDownAndUpKeyEvents(19);
                    break;
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    SystemActions.this.sendDownAndUpKeyEvents(20);
                    break;
                case 15:
                    SystemActions.this.sendDownAndUpKeyEvents(21);
                    break;
                case 16:
                    SystemActions.this.sendDownAndUpKeyEvents(22);
                    break;
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    SystemActions.this.sendDownAndUpKeyEvents(23);
                    break;
            }
        }
    }

    public SystemActions(Context context, NotificationShadeWindowController notificationShadeWindowController, KeyguardStateController keyguardStateController, ShadeController shadeController, Lazy lazy, Optional optional, DisplayTracker displayTracker) {
        this.mContext = context;
        this.mKeyguardStateController = keyguardStateController;
        this.mShadeController = shadeController;
        this.mPanelExpansionInteractor = lazy;
        this.mRecentsOptional = optional;
        this.mDisplayTracker = displayTracker;
        this.mLocale = context.getResources().getConfiguration().getLocales().get(0);
        this.mA11yManager = (AccessibilityManager) context.getSystemService("accessibility");
        this.mNotificationShadeController = notificationShadeWindowController;
        this.mScreenshotHelper = new ScreenshotHelper(context);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public final RemoteAction createRemoteAction(int i, String str) {
        char c;
        PendingIntent broadcast;
        Icon createWithResource = Icon.createWithResource(this.mContext, R.drawable.ic_info);
        String string = this.mContext.getString(i);
        String string2 = this.mContext.getString(i);
        Context context = this.mContext;
        int i2 = SystemActionsBroadcastReceiver.$r8$clinit;
        this.mReceiver.getClass();
        switch (str.hashCode()) {
            case -1103811776:
                if (str.equals("SYSTEM_ACTION_BACK")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1103619272:
                if (str.equals("SYSTEM_ACTION_HOME")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1103479880:
                if (str.equals("SYSTEM_ACTION_MENU")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -768644801:
                if (str.equals("SYSTEM_ACTION_MEDIA_PLAY_PAUSE")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -720484549:
                if (str.equals("SYSTEM_ACTION_POWER_DIALOG")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -535129457:
                if (str.equals("SYSTEM_ACTION_NOTIFICATIONS")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -181386672:
                if (str.equals("SYSTEM_ACTION_ACCESSIBILITY_SHORTCUT")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -153384569:
                if (str.equals("SYSTEM_ACTION_LOCK_SCREEN")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 42571871:
                if (str.equals("SYSTEM_ACTION_RECENTS")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 526987266:
                if (str.equals("SYSTEM_ACTION_ACCESSIBILITY_BUTTON_MENU")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 689657964:
                if (str.equals("SYSTEM_ACTION_DPAD_CENTER")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 815482418:
                if (str.equals("SYSTEM_ACTION_DPAD_UP")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case 1245940668:
                if (str.equals("SYSTEM_ACTION_ACCESSIBILITY_BUTTON")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1493428793:
                if (str.equals("SYSTEM_ACTION_HEADSET_HOOK")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1579999269:
                if (str.equals("SYSTEM_ACTION_TAKE_SCREENSHOT")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1668921710:
                if (str.equals("SYSTEM_ACTION_QUICK_SETTINGS")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1698779909:
                if (str.equals("SYSTEM_ACTION_DPAD_RIGHT")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 1894867256:
                if (str.equals("SYSTEM_ACTION_ACCESSIBILITY_DISMISS_NOTIFICATION_SHADE")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1994051193:
                if (str.equals("SYSTEM_ACTION_DPAD_DOWN")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case 1994279390:
                if (str.equals("SYSTEM_ACTION_DPAD_LEFT")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
            case '\n':
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
            case 15:
            case 16:
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                Intent intent = new Intent(str);
                intent.setPackage(context.getPackageName());
                intent.addFlags(268435456);
                broadcast = PendingIntent.getBroadcast(context, 0, intent, 67108864);
                break;
            default:
                broadcast = null;
                break;
        }
        return new RemoteAction(createWithResource, string, string2, broadcast);
    }

    public void handleHeadsetHook() {
        if (AccessibilityUtils.interceptHeadsetHookForActiveCall(this.mContext)) {
            return;
        }
        sendDownAndUpKeyEvents(79);
    }

    public void handleMediaPlayPause() {
        sendDownAndUpKeyEvents(85);
    }

    public void handleMenu() {
        sendDownAndUpKeyEvents(82);
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        Locale locale = this.mContext.getResources().getConfiguration().getLocales().get(0);
        if (locale.equals(this.mLocale)) {
            return;
        }
        this.mLocale = locale;
        registerActions();
    }

    public final void registerActions() {
        RemoteAction createRemoteAction = createRemoteAction(R.string.accessibility_shortcut_warning_dialog_title, "SYSTEM_ACTION_BACK");
        RemoteAction createRemoteAction2 = createRemoteAction(R.string.accessibility_system_action_headset_hook_label, "SYSTEM_ACTION_HOME");
        RemoteAction createRemoteAction3 = createRemoteAction(R.string.accessibility_system_action_screenshot_label, "SYSTEM_ACTION_RECENTS");
        RemoteAction createRemoteAction4 = createRemoteAction(R.string.accessibility_system_action_on_screen_a11y_shortcut_chooser_label, "SYSTEM_ACTION_NOTIFICATIONS");
        RemoteAction createRemoteAction5 = createRemoteAction(R.string.accessibility_system_action_recents_label, "SYSTEM_ACTION_QUICK_SETTINGS");
        RemoteAction createRemoteAction6 = createRemoteAction(R.string.accessibility_system_action_quick_settings_label, "SYSTEM_ACTION_POWER_DIALOG");
        RemoteAction createRemoteAction7 = createRemoteAction(R.string.accessibility_system_action_home_label, "SYSTEM_ACTION_LOCK_SCREEN");
        RemoteAction createRemoteAction8 = createRemoteAction(R.string.accessibility_uncheck_legacy_item_warning, "SYSTEM_ACTION_TAKE_SCREENSHOT");
        RemoteAction createRemoteAction9 = createRemoteAction(R.string.accessibility_system_action_hardware_a11y_shortcut_label, "SYSTEM_ACTION_HEADSET_HOOK");
        RemoteAction createRemoteAction10 = createRemoteAction(R.string.accessibility_system_action_dpad_up_label, "SYSTEM_ACTION_ACCESSIBILITY_SHORTCUT");
        RemoteAction createRemoteAction11 = createRemoteAction(R.string.accessibility_system_action_dpad_right_label, "SYSTEM_ACTION_DPAD_UP");
        RemoteAction createRemoteAction12 = createRemoteAction(R.string.accessibility_system_action_dpad_center_label, "SYSTEM_ACTION_DPAD_DOWN");
        RemoteAction createRemoteAction13 = createRemoteAction(R.string.accessibility_system_action_dpad_down_label, "SYSTEM_ACTION_DPAD_LEFT");
        RemoteAction createRemoteAction14 = createRemoteAction(R.string.accessibility_system_action_dpad_left_label, "SYSTEM_ACTION_DPAD_RIGHT");
        RemoteAction createRemoteAction15 = createRemoteAction(R.string.accessibility_system_action_dismiss_notification_shade, "SYSTEM_ACTION_DPAD_CENTER");
        RemoteAction createRemoteAction16 = createRemoteAction(R.string.accessibility_system_action_notifications_label, "SYSTEM_ACTION_MENU");
        RemoteAction createRemoteAction17 = createRemoteAction(R.string.accessibility_system_action_lock_screen_label, "SYSTEM_ACTION_MEDIA_PLAY_PAUSE");
        this.mA11yManager.registerSystemAction(createRemoteAction, 1);
        this.mA11yManager.registerSystemAction(createRemoteAction2, 2);
        this.mA11yManager.registerSystemAction(createRemoteAction3, 3);
        if (this.mShadeController.isShadeEnabled()) {
            this.mA11yManager.registerSystemAction(createRemoteAction4, 4);
            this.mA11yManager.registerSystemAction(createRemoteAction5, 5);
        }
        this.mA11yManager.registerSystemAction(createRemoteAction6, 6);
        this.mA11yManager.registerSystemAction(createRemoteAction7, 8);
        this.mA11yManager.registerSystemAction(createRemoteAction8, 9);
        this.mA11yManager.registerSystemAction(createRemoteAction9, 10);
        this.mA11yManager.registerSystemAction(createRemoteAction10, 13);
        this.mA11yManager.registerSystemAction(createRemoteAction11, 16);
        this.mA11yManager.registerSystemAction(createRemoteAction12, 17);
        this.mA11yManager.registerSystemAction(createRemoteAction13, 18);
        this.mA11yManager.registerSystemAction(createRemoteAction14, 19);
        this.mA11yManager.registerSystemAction(createRemoteAction15, 20);
        this.mA11yManager.registerSystemAction(createRemoteAction16, 21);
        this.mA11yManager.registerSystemAction(createRemoteAction17, 22);
        registerOrUnregisterDismissNotificationShadeAction();
    }

    public final void registerOrUnregisterDismissNotificationShadeAction() {
        Assert.isMainThread();
        if (!((PanelExpansionInteractor) this.mPanelExpansionInteractor.get()).isPanelExpanded() || ((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing) {
            if (this.mDismissNotificationShadeActionRegistered) {
                this.mA11yManager.unregisterSystemAction(15);
                this.mDismissNotificationShadeActionRegistered = false;
                return;
            }
            return;
        }
        if (this.mDismissNotificationShadeActionRegistered) {
            return;
        }
        this.mA11yManager.registerSystemAction(createRemoteAction(R.string.accessibility_system_action_back_label, "SYSTEM_ACTION_ACCESSIBILITY_DISMISS_NOTIFICATION_SHADE"), 15);
        this.mDismissNotificationShadeActionRegistered = true;
    }

    public final void sendDownAndUpKeyEvents(int i) {
        long uptimeMillis = SystemClock.uptimeMillis();
        sendKeyEventIdentityCleared(i, 0, uptimeMillis, uptimeMillis);
        sendKeyEventIdentityCleared(i, 1, uptimeMillis, SystemClock.uptimeMillis());
    }

    public final void sendKeyEventIdentityCleared(int i, int i2, long j, long j2) {
        KeyEvent obtain = KeyEvent.obtain(j, j2, i2, i, 0, 0, -1, 0, 8, 257, null);
        ((InputManager) this.mContext.getSystemService(InputManager.class)).injectInputEvent(obtain, 0);
        obtain.recycle();
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        ((NotificationShadeWindowControllerImpl) this.mNotificationShadeController).registerCallback(this.mNotificationShadeCallback);
        Context context = this.mContext;
        int i = SystemActionsBroadcastReceiver.$r8$clinit;
        SystemActionsBroadcastReceiver systemActionsBroadcastReceiver = this.mReceiver;
        systemActionsBroadcastReceiver.getClass();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("SYSTEM_ACTION_BACK");
        intentFilter.addAction("SYSTEM_ACTION_HOME");
        intentFilter.addAction("SYSTEM_ACTION_RECENTS");
        intentFilter.addAction("SYSTEM_ACTION_NOTIFICATIONS");
        intentFilter.addAction("SYSTEM_ACTION_QUICK_SETTINGS");
        intentFilter.addAction("SYSTEM_ACTION_POWER_DIALOG");
        intentFilter.addAction("SYSTEM_ACTION_LOCK_SCREEN");
        intentFilter.addAction("SYSTEM_ACTION_TAKE_SCREENSHOT");
        intentFilter.addAction("SYSTEM_ACTION_HEADSET_HOOK");
        intentFilter.addAction("SYSTEM_ACTION_ACCESSIBILITY_BUTTON");
        intentFilter.addAction("SYSTEM_ACTION_ACCESSIBILITY_BUTTON_MENU");
        intentFilter.addAction("SYSTEM_ACTION_ACCESSIBILITY_SHORTCUT");
        intentFilter.addAction("SYSTEM_ACTION_ACCESSIBILITY_DISMISS_NOTIFICATION_SHADE");
        intentFilter.addAction("SYSTEM_ACTION_DPAD_UP");
        intentFilter.addAction("SYSTEM_ACTION_DPAD_DOWN");
        intentFilter.addAction("SYSTEM_ACTION_DPAD_LEFT");
        intentFilter.addAction("SYSTEM_ACTION_DPAD_RIGHT");
        intentFilter.addAction("SYSTEM_ACTION_DPAD_CENTER");
        intentFilter.addAction("SYSTEM_ACTION_MENU");
        intentFilter.addAction("SYSTEM_ACTION_MEDIA_PLAY_PAUSE");
        context.registerReceiverForAllUsers(systemActionsBroadcastReceiver, intentFilter, "com.android.systemui.permission.SELF", null, 2);
        registerActions();
    }
}

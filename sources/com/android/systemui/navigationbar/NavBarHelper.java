package com.android.systemui.navigationbar;

import android.R;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.ArraySet;
import android.util.Log;
import android.view.IRotationWatcher;
import android.view.IWallpaperVisibilityListener;
import android.view.IWindowManager;
import android.view.ViewConfiguration;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import com.android.app.viewcapture.data.ViewNode;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.accessibility.AccessibilityButtonModeObserver;
import com.android.systemui.accessibility.AccessibilityButtonTargetsObserver;
import com.android.systemui.accessibility.AccessibilityGestureTargetsObserver;
import com.android.systemui.accessibility.SystemActions;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.NavBarHelper;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shared.rotation.RotationPolicyUtil;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.concurrency.UiThreadContext;
import com.android.systemui.util.concurrency.UiThreadContextKt$runWithScissors$1;
import com.google.android.systemui.assist.AssistManagerGoogle;
import dagger.Lazy;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NavBarHelper implements AccessibilityManager.AccessibilityServicesStateChangeListener, AccessibilityButtonModeObserver.ModeChangedListener, AccessibilityButtonTargetsObserver.TargetsChangedListener, AccessibilityGestureTargetsObserver.TargetsChangedListener, OverviewProxyService.OverviewProxyListener, NavigationModeController.ModeChangedListener, Dumpable, CommandQueue.Callbacks, ConfigurationController.ConfigurationListener {
    public long mA11yButtonState;
    public final AccessibilityButtonModeObserver mAccessibilityButtonModeObserver;
    public final AccessibilityButtonTargetsObserver mAccessibilityButtonTargetsObserver;
    public final AccessibilityGestureTargetsObserver mAccessibilityGestureTargetsObserver;
    public final AccessibilityManager mAccessibilityManager;
    public final AnonymousClass1 mAssistContentObserver;
    public final Lazy mAssistManagerLazy;
    public boolean mAssistantAvailable;
    public boolean mAssistantTouchGestureEnabled;
    public final Handler mBgHandler;
    public final CommandQueue mCommandQueue;
    public final ContentResolver mContentResolver;
    public final Context mContext;
    public final EdgeBackGestureHandler mEdgeBackGestureHandler;
    public final Handler mHandler;
    public final KeyguardStateController mKeyguardStateController;
    public boolean mLongPressHomeEnabled;
    public final Executor mMainExecutor;
    public int mNavBarMode;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public final AnonymousClass3 mRotationWatcher;
    public int mRotationWatcherRotation;
    public final List mStateListeners;
    public final SystemActions mSystemActions;
    public boolean mTogglingNavbarTaskbar;
    public final UserTracker mUserTracker;
    public final AnonymousClass2 mWallpaperVisibilityListener;
    public boolean mWallpaperVisible;
    public int mWindowState;
    public int mWindowStateDisplayId;
    public final IWindowManager mWm;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.navigationbar.NavBarHelper$2, reason: invalid class name */
    public final class AnonymousClass2 extends IWallpaperVisibilityListener.Stub {
        public AnonymousClass2() {
        }

        public final void onWallpaperVisibilityChanged(final boolean z, final int i) {
            NavBarHelper.this.mHandler.post(new Runnable() { // from class: com.android.systemui.navigationbar.NavBarHelper$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    NavBarHelper.AnonymousClass2 anonymousClass2 = NavBarHelper.AnonymousClass2.this;
                    boolean z2 = z;
                    int i2 = i;
                    NavBarHelper navBarHelper = NavBarHelper.this;
                    navBarHelper.mWallpaperVisible = z2;
                    Iterator it = navBarHelper.mStateListeners.iterator();
                    while (it.hasNext()) {
                        ((NavBarHelper.NavbarTaskbarStateUpdater) it.next()).updateWallpaperVisibility(i2, z2);
                    }
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.navigationbar.NavBarHelper$3, reason: invalid class name */
    public final class AnonymousClass3 extends IRotationWatcher.Stub {
        public AnonymousClass3() {
        }

        public final void onRotationChanged(final int i) {
            final Boolean isRotationLocked = RotationPolicyUtil.isRotationLocked(NavBarHelper.this.mContext);
            NavBarHelper.this.mHandler.postAtFrontOfQueue(new Runnable() { // from class: com.android.systemui.navigationbar.NavBarHelper$3$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    NavBarHelper.AnonymousClass3 anonymousClass3 = NavBarHelper.AnonymousClass3.this;
                    int i2 = i;
                    Boolean bool = isRotationLocked;
                    NavBarHelper navBarHelper = NavBarHelper.this;
                    navBarHelper.mRotationWatcherRotation = i2;
                    Iterator it = navBarHelper.mStateListeners.iterator();
                    while (it.hasNext()) {
                        ((NavBarHelper.NavbarTaskbarStateUpdater) it.next()).updateRotationWatcherState(i2, bool);
                    }
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r5v2, types: [com.android.systemui.navigationbar.NavBarHelper$1] */
    public NavBarHelper(final Context context, AccessibilityManager accessibilityManager, AccessibilityButtonModeObserver accessibilityButtonModeObserver, AccessibilityButtonTargetsObserver accessibilityButtonTargetsObserver, AccessibilityGestureTargetsObserver accessibilityGestureTargetsObserver, SystemActions systemActions, OverviewProxyService overviewProxyService, Lazy lazy, KeyguardStateController keyguardStateController, NavigationModeController navigationModeController, final EdgeBackGestureHandler.Factory factory, IWindowManager iWindowManager, UserTracker userTracker, DisplayTracker displayTracker, NotificationShadeWindowController notificationShadeWindowController, ConfigurationController configurationController, DumpManager dumpManager, CommandQueue commandQueue, Executor executor, Handler handler) {
        Handler handler2 = new Handler(Looper.getMainLooper());
        this.mHandler = handler2;
        this.mStateListeners = new ArrayList();
        this.mAssistContentObserver = new ContentObserver(handler2) { // from class: com.android.systemui.navigationbar.NavBarHelper.1
            @Override // android.database.ContentObserver
            public final void onChange(boolean z, Uri uri) {
                NavBarHelper.this.updateAssistantAvailability();
            }
        };
        this.mWallpaperVisibilityListener = new AnonymousClass2();
        this.mRotationWatcher = new AnonymousClass3();
        if (!Process.myUserHandle().equals(UserHandle.SYSTEM)) {
            Log.wtf("NavBarHelper", "Unexpected initialization for non-primary user", new Throwable());
        }
        this.mContext = context;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mCommandQueue = commandQueue;
        this.mContentResolver = context.getContentResolver();
        this.mAccessibilityManager = accessibilityManager;
        this.mAssistManagerLazy = lazy;
        this.mKeyguardStateController = keyguardStateController;
        this.mUserTracker = userTracker;
        this.mSystemActions = systemActions;
        this.mAccessibilityButtonModeObserver = accessibilityButtonModeObserver;
        this.mAccessibilityButtonTargetsObserver = accessibilityButtonTargetsObserver;
        this.mAccessibilityGestureTargetsObserver = accessibilityGestureTargetsObserver;
        this.mWm = iWindowManager;
        displayTracker.getClass();
        Function0 function0 = new Function0() { // from class: com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$Factory$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Context context2 = context;
                EdgeBackGestureHandler.Factory factory2 = EdgeBackGestureHandler.Factory.this;
                Executor executor2 = factory2.mBackgroundExecutor;
                ViewConfiguration viewConfiguration = factory2.mViewConfiguration;
                WindowManager windowManager = factory2.mWindowManager;
                IWindowManager iWindowManager2 = factory2.mWindowManagerService;
                InputManager inputManager = factory2.mInputManager;
                Optional optional = factory2.mPipOptional;
                Optional optional2 = factory2.mDesktopModeOptional;
                GestureInteractor gestureInteractor = factory2.mGestureInteractor;
                return new EdgeBackGestureHandler(context2, factory2.mOverviewProxyService, factory2.mSysUiState, factory2.mPluginManager, factory2.mUiThreadContext, executor2, factory2.mBgHandler, factory2.mUserTracker, factory2.mNavigationModeController, factory2.mBackPanelControllerFactory, viewConfiguration, windowManager, iWindowManager2, inputManager, optional, optional2, factory2.mFalsingManager, factory2.mBackGestureTfClassifierProviderProvider, factory2.mLightBarControllerProvider, factory2.mNotificationShadeWindowController, gestureInteractor, factory2.mJavaAdapter);
            }
        };
        Handler handler3 = factory.mUiThreadContext.handler;
        AtomicReference atomicReference = new AtomicReference();
        handler3.runWithScissors(new UiThreadContextKt$runWithScissors$1(atomicReference, function0), 150L);
        Object obj = atomicReference.get();
        Intrinsics.checkNotNull(obj);
        this.mEdgeBackGestureHandler = (EdgeBackGestureHandler) obj;
        this.mMainExecutor = executor;
        this.mBgHandler = handler;
        this.mNavBarMode = navigationModeController.addListener(this);
        commandQueue.addCallback((CommandQueue.Callbacks) this);
        ((ConfigurationControllerImpl) configurationController).addCallback(this);
        overviewProxyService.addCallback((OverviewProxyService.OverviewProxyListener) this);
        dumpManager.registerDumpable(this);
    }

    public static int transitionMode(int i, boolean z) {
        if (z) {
            return 1;
        }
        if ((i & 6) == 6) {
            return 3;
        }
        if ((i & 4) != 0) {
            return 6;
        }
        if ((i & 2) != 0) {
            return 4;
        }
        return (i & 64) != 0 ? 1 : 0;
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "NavbarTaskbarFriendster", "  longPressHomeEnabled="), this.mLongPressHomeEnabled, printWriter, "  mAssistantTouchGestureEnabled="), this.mAssistantTouchGestureEnabled, printWriter, "  mAssistantAvailable="), this.mAssistantAvailable, printWriter, "  mNavBarMode=");
        m.append(this.mNavBarMode);
        printWriter.println(m.toString());
    }

    public final boolean isImeShown(int i) {
        NotificationShadeWindowView notificationShadeWindowView = ((NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController).mWindowRootView;
        return (notificationShadeWindowView != null && notificationShadeWindowView.isAttachedToWindow() && notificationShadeWindowView.getRootWindowInsets().isVisible(WindowInsets.Type.ime())) || !(((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing || (i & 2) == 0);
    }

    @Override // com.android.systemui.accessibility.AccessibilityButtonModeObserver.ModeChangedListener
    public final void onAccessibilityButtonModeChanged(int i) {
        updateA11yState();
    }

    @Override // com.android.systemui.accessibility.AccessibilityButtonTargetsObserver.TargetsChangedListener
    public final void onAccessibilityButtonTargetsChanged(String str) {
        updateA11yState();
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityServicesStateChangeListener
    public final void onAccessibilityServicesStateChanged(AccessibilityManager accessibilityManager) {
        updateA11yState();
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        EdgeBackGestureHandler edgeBackGestureHandler = this.mEdgeBackGestureHandler;
        if (edgeBackGestureHandler.mStartingQuickstepRotation > -1) {
            int rotation = configuration.windowConfiguration.getRotation();
            int i = edgeBackGestureHandler.mStartingQuickstepRotation;
            edgeBackGestureHandler.mDisabledForQuickstep = i > -1 && i != rotation;
        }
        Log.i("NoBackGesture", "Config changed: newConfig=" + configuration + " lastReportedConfig=" + edgeBackGestureHandler.mLastReportedConfig);
        int diff = configuration.diff(edgeBackGestureHandler.mLastReportedConfig);
        if ((1073741824 & diff) != 0 || (diff & 4096) != 0) {
            edgeBackGestureHandler.updateCurrentUserResources();
        }
        edgeBackGestureHandler.mLastReportedConfig.updateFrom(configuration);
        edgeBackGestureHandler.updateDisplaySize();
    }

    @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
    public final void onConnectionChanged(boolean z) {
        if (z) {
            this.mMainExecutor.execute(new Runnable() { // from class: com.android.systemui.navigationbar.NavBarHelper$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    NavBarHelper.this.updateAssistantAvailability();
                }
            });
        }
    }

    @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
    public final void onNavigationModeChanged(int i) {
        this.mNavBarMode = i;
        updateAssistantAvailability();
    }

    public final void registerNavTaskStateUpdater(final NavbarTaskbarStateUpdater navbarTaskbarStateUpdater) {
        this.mStateListeners.add(navbarTaskbarStateUpdater);
        if (this.mTogglingNavbarTaskbar || ((ArrayList) this.mStateListeners).size() != 1) {
            navbarTaskbarStateUpdater.updateAccessibilityServicesState();
            navbarTaskbarStateUpdater.updateAssistantAvailable(this.mAssistantAvailable, this.mLongPressHomeEnabled);
        } else {
            this.mAccessibilityManager.addAccessibilityServicesStateChangeListener(this);
            this.mAccessibilityButtonModeObserver.addListener(this);
            this.mAccessibilityButtonTargetsObserver.addListener(this);
            this.mAccessibilityGestureTargetsObserver.addListener(this);
            this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("assistant"), false, this.mAssistContentObserver, -1);
            this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("assist_long_press_home_enabled"), false, this.mAssistContentObserver, -1);
            this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("search_all_entrypoints_enabled"), false, this.mAssistContentObserver, -1);
            this.mContentResolver.registerContentObserver(Settings.Secure.getUriFor("assist_touch_gesture_enabled"), false, this.mAssistContentObserver, -1);
            try {
                this.mWm.watchRotation(this.mRotationWatcher, 0);
            } catch (Exception e) {
                Log.w("NavBarHelper", "Failed to register rotation watcher", e);
            }
            try {
                this.mWallpaperVisible = this.mWm.registerWallpaperVisibilityListener(this.mWallpaperVisibilityListener, 0);
            } catch (Exception e2) {
                Log.w("NavBarHelper", "Failed to register wallpaper visibility listener", e2);
            }
            EdgeBackGestureHandler edgeBackGestureHandler = this.mEdgeBackGestureHandler;
            edgeBackGestureHandler.mIsAttached = true;
            edgeBackGestureHandler.mOverviewProxyService.addCallback((OverviewProxyService.OverviewProxyListener) edgeBackGestureHandler.mQuickSwitchListener);
            SysUiState sysUiState = edgeBackGestureHandler.mSysUiState;
            List list = sysUiState.mCallbacks;
            EdgeBackGestureHandler.AnonymousClass6 anonymousClass6 = edgeBackGestureHandler.mSysUiStateCallback;
            list.add(anonymousClass6);
            anonymousClass6.onSystemUiStateChanged(sysUiState.mFlags);
            InputManager inputManager = edgeBackGestureHandler.mInputManager;
            UiThreadContext uiThreadContext = edgeBackGestureHandler.mUiThreadContext;
            Handler handler = uiThreadContext.handler;
            EdgeBackGestureHandler.AnonymousClass8 anonymousClass8 = edgeBackGestureHandler.mInputDeviceListener;
            inputManager.registerInputDeviceListener(anonymousClass8, handler);
            for (int i : edgeBackGestureHandler.mInputManager.getInputDeviceIds()) {
                anonymousClass8.onInputDeviceAdded(i);
            }
            edgeBackGestureHandler.updateIsEnabled();
            ((UserTrackerImpl) edgeBackGestureHandler.mUserTracker).addCallback(edgeBackGestureHandler.mUserChangedCallback, uiThreadContext.executor);
            updateAssistantAvailability();
            updateA11yState();
            this.mCommandQueue.recomputeDisableFlags(this.mContext.getDisplayId(), false);
        }
        navbarTaskbarStateUpdater.updateWallpaperVisibility(0, this.mWallpaperVisible);
        this.mBgHandler.post(new Runnable() { // from class: com.android.systemui.navigationbar.NavBarHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                final NavBarHelper navBarHelper = NavBarHelper.this;
                final NavBarHelper.NavbarTaskbarStateUpdater navbarTaskbarStateUpdater2 = navbarTaskbarStateUpdater;
                final Boolean isRotationLocked = RotationPolicyUtil.isRotationLocked(navBarHelper.mContext);
                navBarHelper.mMainExecutor.execute(new Runnable() { // from class: com.android.systemui.navigationbar.NavBarHelper$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        NavBarHelper navBarHelper2 = NavBarHelper.this;
                        navbarTaskbarStateUpdater2.updateRotationWatcherState(navBarHelper2.mRotationWatcherRotation, isRotationLocked);
                    }
                });
            }
        });
    }

    public final void removeNavTaskStateUpdater(NavbarTaskbarStateUpdater navbarTaskbarStateUpdater) {
        this.mStateListeners.remove(navbarTaskbarStateUpdater);
        if (this.mTogglingNavbarTaskbar || !this.mStateListeners.isEmpty()) {
            return;
        }
        this.mAccessibilityManager.removeAccessibilityServicesStateChangeListener(this);
        this.mAccessibilityButtonModeObserver.removeListener(this);
        this.mAccessibilityButtonTargetsObserver.removeListener(this);
        this.mAccessibilityGestureTargetsObserver.removeListener(this);
        this.mContentResolver.unregisterContentObserver(this.mAssistContentObserver);
        try {
            this.mWm.removeRotationWatcher(this.mRotationWatcher);
        } catch (Exception e) {
            Log.w("NavBarHelper", "Failed to unregister rotation watcher", e);
        }
        try {
            this.mWm.unregisterWallpaperVisibilityListener(this.mWallpaperVisibilityListener, 0);
        } catch (Exception e2) {
            Log.w("NavBarHelper", "Failed to register wallpaper visibility listener", e2);
        }
        EdgeBackGestureHandler edgeBackGestureHandler = this.mEdgeBackGestureHandler;
        edgeBackGestureHandler.mIsAttached = false;
        edgeBackGestureHandler.mOverviewProxyService.removeCallback((OverviewProxyService.OverviewProxyListener) edgeBackGestureHandler.mQuickSwitchListener);
        edgeBackGestureHandler.mSysUiState.mCallbacks.remove(edgeBackGestureHandler.mSysUiStateCallback);
        edgeBackGestureHandler.mInputManager.unregisterInputDeviceListener(edgeBackGestureHandler.mInputDeviceListener);
        ((ArraySet) edgeBackGestureHandler.mTrackpadsConnected).clear();
        edgeBackGestureHandler.updateIsEnabled();
        ((UserTrackerImpl) edgeBackGestureHandler.mUserTracker).removeCallback(edgeBackGestureHandler.mUserChangedCallback);
    }

    @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
    public final void setAssistantOverridesRequested(int[] iArr) {
        AssistManagerGoogle assistManagerGoogle = (AssistManagerGoogle) this.mAssistManagerLazy.get();
        assistManagerGoogle.mAssistOverrideInvocationTypes = iArr;
        assistManagerGoogle.mOpaEnabledReceiver.mAssistOverrideInvocationTypes = iArr;
        updateAssistantAvailability();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void setWindowState(int i, int i2, int i3) {
        if (i2 != 2) {
            return;
        }
        this.mWindowStateDisplayId = i;
        this.mWindowState = i3;
    }

    @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
    public final void startAssistant(Bundle bundle) {
        ((AssistManagerGoogle) this.mAssistManagerLazy.get()).startAssist(bundle);
    }

    public void updateA11yState() {
        int i;
        long j = this.mA11yButtonState;
        try {
            i = Integer.parseInt(this.mAccessibilityButtonModeObserver.getSettingsValue());
        } catch (NumberFormatException e) {
            Log.e("A11yButtonModeObserver", "Invalid string for  " + e);
            i = 0;
        }
        int size = this.mAccessibilityManager.getAccessibilityShortcutTargets(i != 1 ? 1 : 0).size();
        boolean z = size >= 1;
        boolean z2 = size >= 2;
        long j2 = (z2 ? 32L : 0L) | (z ? 16L : 0L);
        this.mA11yButtonState = j2;
        if (j != j2) {
            updateSystemAction(11, z);
            updateSystemAction(12, z2);
        }
        Iterator it = this.mStateListeners.iterator();
        while (it.hasNext()) {
            ((NavbarTaskbarStateUpdater) it.next()).updateAccessibilityServicesState();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateAssistantAvailability() {
        boolean z = false;
        Object[] objArr = ((AssistManagerGoogle) this.mAssistManagerLazy.get()).mAssistUtils.getAssistComponentForUser(((UserTrackerImpl) this.mUserTracker).getUserId()) != null;
        boolean shouldOverrideAssist = ((AssistManagerGoogle) this.mAssistManagerLazy.get()).shouldOverrideAssist(5);
        this.mLongPressHomeEnabled = Settings.Secure.getIntForUser(this.mContentResolver, shouldOverrideAssist ? "search_all_entrypoints_enabled" : "assist_long_press_home_enabled", this.mContext.getResources().getBoolean(shouldOverrideAssist ? R.bool.config_shortPressEarlyOnStemPrimary : R.bool.config_assistLongPressHomeEnabledDefault) ? 1 : 0, ((UserTrackerImpl) this.mUserTracker).getUserId()) != 0;
        this.mAssistantTouchGestureEnabled = Settings.Secure.getIntForUser(this.mContentResolver, "assist_touch_gesture_enabled", this.mContext.getResources().getBoolean(R.bool.config_assistTouchGestureEnabledDefault) ? 1 : 0, ((UserTrackerImpl) this.mUserTracker).getUserId()) != 0;
        boolean isGesturalMode = QuickStepContract.isGesturalMode(this.mNavBarMode);
        if (objArr != false && this.mAssistantTouchGestureEnabled && isGesturalMode) {
            z = true;
        }
        this.mAssistantAvailable = z;
        boolean z2 = this.mLongPressHomeEnabled;
        Iterator it = this.mStateListeners.iterator();
        while (it.hasNext()) {
            ((NavbarTaskbarStateUpdater) it.next()).updateAssistantAvailable(z, z2);
        }
    }

    public final void updateSystemAction(int i, boolean z) {
        int i2;
        String str;
        if (!z) {
            this.mSystemActions.mA11yManager.unregisterSystemAction(i);
            return;
        }
        SystemActions systemActions = this.mSystemActions;
        systemActions.getClass();
        switch (i) {
            case 1:
                i2 = R.string.accessibility_shortcut_warning_dialog_title;
                str = "SYSTEM_ACTION_BACK";
                break;
            case 2:
                i2 = R.string.accessibility_system_action_headset_hook_label;
                str = "SYSTEM_ACTION_HOME";
                break;
            case 3:
                i2 = R.string.accessibility_system_action_screenshot_label;
                str = "SYSTEM_ACTION_RECENTS";
                break;
            case 4:
                i2 = R.string.accessibility_system_action_on_screen_a11y_shortcut_chooser_label;
                str = "SYSTEM_ACTION_NOTIFICATIONS";
                break;
            case 5:
                i2 = R.string.accessibility_system_action_recents_label;
                str = "SYSTEM_ACTION_QUICK_SETTINGS";
                break;
            case 6:
                i2 = R.string.accessibility_system_action_quick_settings_label;
                str = "SYSTEM_ACTION_POWER_DIALOG";
                break;
            case 7:
            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
            default:
                return;
            case 8:
                i2 = R.string.accessibility_system_action_home_label;
                str = "SYSTEM_ACTION_LOCK_SCREEN";
                break;
            case 9:
                i2 = R.string.accessibility_uncheck_legacy_item_warning;
                str = "SYSTEM_ACTION_TAKE_SCREENSHOT";
                break;
            case 10:
                i2 = R.string.accessibility_system_action_hardware_a11y_shortcut_label;
                str = "SYSTEM_ACTION_HEADSET_HOOK";
                break;
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                i2 = R.string.accessibility_system_action_power_dialog_label;
                str = "SYSTEM_ACTION_ACCESSIBILITY_BUTTON";
                break;
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                i2 = R.string.accessibility_system_action_on_screen_a11y_shortcut_label;
                str = "SYSTEM_ACTION_ACCESSIBILITY_BUTTON_MENU";
                break;
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                i2 = R.string.accessibility_system_action_dpad_up_label;
                str = "SYSTEM_ACTION_ACCESSIBILITY_SHORTCUT";
                break;
            case 15:
                i2 = R.string.accessibility_system_action_back_label;
                str = "SYSTEM_ACTION_ACCESSIBILITY_DISMISS_NOTIFICATION_SHADE";
                break;
            case 16:
                i2 = R.string.accessibility_system_action_dpad_right_label;
                str = "SYSTEM_ACTION_DPAD_UP";
                break;
            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                i2 = R.string.accessibility_system_action_dpad_center_label;
                str = "SYSTEM_ACTION_DPAD_DOWN";
                break;
            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                i2 = R.string.accessibility_system_action_dpad_down_label;
                str = "SYSTEM_ACTION_DPAD_LEFT";
                break;
            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                i2 = R.string.accessibility_system_action_dpad_left_label;
                str = "SYSTEM_ACTION_DPAD_RIGHT";
                break;
            case 20:
                i2 = R.string.accessibility_system_action_dismiss_notification_shade;
                str = "SYSTEM_ACTION_DPAD_CENTER";
                break;
            case 21:
                i2 = R.string.accessibility_system_action_notifications_label;
                str = "SYSTEM_ACTION_MENU";
                break;
            case 22:
                i2 = R.string.accessibility_system_action_lock_screen_label;
                str = "SYSTEM_ACTION_MEDIA_PLAY_PAUSE";
                break;
        }
        systemActions.mA11yManager.registerSystemAction(systemActions.createRemoteAction(i2, str), i);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface NavbarTaskbarStateUpdater {
        void updateAccessibilityServicesState();

        void updateAssistantAvailable(boolean z, boolean z2);

        void updateWallpaperVisibility(int i, boolean z);

        default void updateRotationWatcherState(int i, Boolean bool) {
        }
    }
}

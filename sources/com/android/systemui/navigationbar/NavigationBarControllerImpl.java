package com.android.systemui.navigationbar;

import android.animation.ValueAnimator;
import android.app.StatusBarManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.Trace;
import android.provider.DeviceConfig;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.Display;
import android.view.View;
import android.view.WindowManagerGlobal;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import com.android.internal.statusbar.RegisterStatusBarResult;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.settingslib.applications.InterestingConfigChanges;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.TaskbarDelegate.AnonymousClass3;
import com.android.systemui.navigationbar.views.NavigationBar;
import com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda0;
import com.android.systemui.navigationbar.views.NavigationBarInflaterView;
import com.android.systemui.navigationbar.views.NavigationBarTransitions;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.navigationbar.views.buttons.ButtonDispatcher;
import com.android.systemui.navigationbar.views.buttons.ContextualButton;
import com.android.systemui.navigationbar.views.buttons.ContextualButtonGroup;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.shared.recents.IOverviewProxy;
import com.android.systemui.shared.recents.utilities.Utilities;
import com.android.systemui.shared.statusbar.phone.BarTransitions;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.phone.AutoHideController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.LightBarTransitionsController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.wm.shell.R;
import com.android.wm.shell.back.BackAnimationController;
import com.android.wm.shell.shared.handles.RegionSamplingHelper;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl;
import dalvik.annotation.optimization.NeverCompile;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NavigationBarControllerImpl implements ConfigurationController.ConfigurationListener, NavigationModeController.ModeChangedListener, Dumpable {
    public final AnonymousClass1 mCommandQueueCallbacks;
    public final InterestingConfigChanges mConfigChanges;
    public final Context mContext;
    public final DisplayManager mDisplayManager;
    public final DisplayTracker mDisplayTracker;
    public final Executor mExecutor;
    boolean mIsLargeScreen;
    boolean mIsPhone;
    public final NavBarHelper mNavBarHelper;
    public int mNavMode;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory mNavigationBarComponentFactory;
    public final TaskbarDelegate mTaskbarDelegate;
    SparseArray mNavigationBars = new SparseArray();
    public final SparseBooleanArray mHasNavBar = new SparseBooleanArray();

    public NavigationBarControllerImpl(Context context, OverviewProxyService overviewProxyService, NavigationModeController navigationModeController, SysUiState sysUiState, CommandQueue commandQueue, Executor executor, ConfigurationController configurationController, NavBarHelper navBarHelper, TaskbarDelegate taskbarDelegate, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, DumpManager dumpManager, AutoHideController autoHideController, LightBarController lightBarController, TaskStackChangeListeners taskStackChangeListeners, Optional optional, Optional optional2, DisplayTracker displayTracker) {
        InterestingConfigChanges interestingConfigChanges = new InterestingConfigChanges(1073742336);
        this.mConfigChanges = interestingConfigChanges;
        CommandQueue.Callbacks callbacks = new CommandQueue.Callbacks() { // from class: com.android.systemui.navigationbar.NavigationBarControllerImpl.1
            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void onDisplayReady(int i) {
                NavigationBarControllerImpl navigationBarControllerImpl = NavigationBarControllerImpl.this;
                Display display = navigationBarControllerImpl.mDisplayManager.getDisplay(i);
                navigationBarControllerImpl.mIsLargeScreen = Utilities.isLargeScreen(navigationBarControllerImpl.mContext);
                navigationBarControllerImpl.createNavigationBar(display, null, null);
            }

            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void onDisplayRemoved(int i) {
                NavigationBarControllerImpl navigationBarControllerImpl = NavigationBarControllerImpl.this;
                navigationBarControllerImpl.removeNavigationBar(i);
                navigationBarControllerImpl.mHasNavBar.delete(i);
            }

            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void setNavigationBarLumaSamplingEnabled(int i, boolean z) {
                NavigationBar navigationBar = (NavigationBar) NavigationBarControllerImpl.this.mNavigationBars.get(i);
                if (navigationBar != null) {
                    RegionSamplingHelper regionSamplingHelper = navigationBar.mRegionSamplingHelper;
                    if (z) {
                        regionSamplingHelper.start(navigationBar.mSamplingBounds);
                    } else {
                        regionSamplingHelper.stop();
                    }
                }
            }

            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void showPinningEnterExitToast(boolean z) {
                NavigationBarControllerImpl navigationBarControllerImpl = NavigationBarControllerImpl.this;
                NavigationBarView navigationBarView = navigationBarControllerImpl.getNavigationBarView(navigationBarControllerImpl.mContext.getDisplayId());
                if (navigationBarView != null) {
                    if (z) {
                        navigationBarView.mScreenPinningNotify.makeAllUserToastAndShow(R.string.screen_pinning_start);
                    } else {
                        navigationBarView.mScreenPinningNotify.makeAllUserToastAndShow(R.string.screen_pinning_exit);
                    }
                }
            }

            @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
            public final void showPinningEscapeToast() {
                NavigationBarControllerImpl navigationBarControllerImpl = NavigationBarControllerImpl.this;
                NavigationBarView navigationBarView = navigationBarControllerImpl.getNavigationBarView(navigationBarControllerImpl.mContext.getDisplayId());
                if (navigationBarView != null) {
                    navigationBarView.mScreenPinningNotify.showEscapeToast(navigationBarView.mNavBarMode == 2, navigationBarView.getRecentsButton().getVisibility() == 0);
                }
            }
        };
        this.mContext = context;
        this.mExecutor = executor;
        this.mNavigationBarComponentFactory = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
        this.mDisplayTracker = displayTracker;
        this.mDisplayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
        commandQueue.addCallback(callbacks);
        ((ConfigurationControllerImpl) configurationController).addCallback(this);
        interestingConfigChanges.applyNewConfig(context.getResources());
        this.mNavMode = navigationModeController.addListener(this);
        this.mNavBarHelper = navBarHelper;
        this.mTaskbarDelegate = taskbarDelegate;
        BackAnimationController.BackAnimationImpl backAnimationImpl = (BackAnimationController.BackAnimationImpl) optional2.orElse(null);
        taskbarDelegate.mCommandQueue = commandQueue;
        taskbarDelegate.mOverviewProxyService = overviewProxyService;
        taskbarDelegate.mNavBarHelper = navBarHelper;
        taskbarDelegate.mNavigationModeController = navigationModeController;
        taskbarDelegate.mSysUiState = sysUiState;
        dumpManager.registerDumpable(taskbarDelegate);
        taskbarDelegate.mAutoHideController = autoHideController;
        taskbarDelegate.mLightBarController = lightBarController;
        taskbarDelegate.mPipOptional = optional;
        taskbarDelegate.mBackAnimation = backAnimationImpl;
        taskbarDelegate.mLightBarTransitionsController = taskbarDelegate.mLightBarTransitionsControllerFactory.create(taskbarDelegate.new AnonymousClass3());
        taskbarDelegate.mTaskStackChangeListeners = taskStackChangeListeners;
        taskbarDelegate.mEdgeBackGestureHandler = navBarHelper.mEdgeBackGestureHandler;
        this.mIsLargeScreen = Utilities.isLargeScreen(context);
        this.mIsPhone = context.getResources().getIntArray(android.R.array.config_fontManagerServiceCerts).length == 0;
        dumpManager.registerDumpable(this);
    }

    public final void checkNavBarModes(int i) {
        NavigationBar navigationBar = (NavigationBar) this.mNavigationBars.get(i);
        if (navigationBar != null) {
            navigationBar.checkNavBarModes();
        } else {
            this.mTaskbarDelegate.checkNavBarModes();
        }
    }

    public void createNavigationBar(final Display display, Bundle bundle, final RegisterStatusBarResult registerStatusBarResult) {
        if (display == null) {
            return;
        }
        int displayId = display.getDisplayId();
        this.mDisplayTracker.getClass();
        boolean z = displayId == 0;
        if (shouldCreateNavBarAndTaskBar(displayId)) {
            if (z && initializeTaskbarIfNecessary()) {
                return;
            }
            Context createDisplayContext = z ? this.mContext : this.mContext.createDisplayContext(display);
            DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = this.mNavigationBarComponentFactory;
            daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.getClass();
            createDisplayContext.getClass();
            final NavigationBar navigationBar = (NavigationBar) new DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl(daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory.sysUIGoogleSysUIComponentImpl, createDisplayContext, bundle).navigationBarProvider.get();
            navigationBar.init$9();
            this.mNavigationBars.put(displayId, navigationBar);
            View.OnAttachStateChangeListener onAttachStateChangeListener = new View.OnAttachStateChangeListener() { // from class: com.android.systemui.navigationbar.NavigationBarControllerImpl.2
                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewAttachedToWindow(View view) {
                    if (registerStatusBarResult != null) {
                        NavigationBar navigationBar2 = navigationBar;
                        int displayId2 = display.getDisplayId();
                        RegisterStatusBarResult registerStatusBarResult2 = registerStatusBarResult;
                        navigationBar2.setImeWindowStatus(displayId2, registerStatusBarResult2.mImeWindowVis, registerStatusBarResult2.mImeBackDisposition, registerStatusBarResult2.mShowImeSwitcher);
                    }
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public final void onViewDetachedFromWindow(View view) {
                    view.removeOnAttachStateChangeListener(this);
                }
            };
            View view = navigationBar.mView;
            if (view != null) {
                view.addOnAttachStateChangeListener(onAttachStateChangeListener);
            }
        }
    }

    public final void disableAnimationsDuringHide(long j, int i) {
        NavigationBar navigationBar = (NavigationBar) this.mNavigationBars.get(i);
        if (navigationBar != null) {
            NavigationBarView navigationBarView = (NavigationBarView) navigationBar.mView;
            navigationBarView.mLayoutTransitionsEnabled = false;
            navigationBarView.updateLayoutTransitionsEnabled();
            navigationBar.mHandler.postDelayed(navigationBar.mEnableLayoutTransitions, j + 448);
        }
    }

    @Override // com.android.systemui.Dumpable
    @NeverCompile
    public final void dump(PrintWriter printWriter, String[] strArr) {
        String str;
        ContextualButton contextualButton;
        StringBuilder m = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("mIsLargeScreen="), this.mIsLargeScreen, printWriter, "mNavMode=");
        m.append(this.mNavMode);
        printWriter.println(m.toString());
        for (int i = 0; i < this.mNavigationBars.size(); i++) {
            if (i > 0) {
                printWriter.println();
            }
            NavigationBar navigationBar = (NavigationBar) this.mNavigationBars.valueAt(i);
            printWriter.println("NavigationBar (displayId=" + navigationBar.mDisplayId + "):");
            StringBuilder m2 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("  mStartingQuickSwitchRotation="), navigationBar.mStartingQuickSwitchRotation, printWriter, "  mCurrentRotation="), navigationBar.mCurrentRotation, printWriter, "  mHomeButtonLongPressDurationMs=");
            m2.append(navigationBar.mHomeButtonLongPressDurationMs);
            printWriter.println(m2.toString());
            printWriter.println("  mOverrideHomeButtonLongPressDurationMs=" + navigationBar.mOverrideHomeButtonLongPressDurationMs);
            printWriter.println("  mOverrideHomeButtonLongPressSlopMultiplier=" + navigationBar.mOverrideHomeButtonLongPressSlopMultiplier);
            StringBuilder m3 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  mLongPressHomeEnabled="), navigationBar.mLongPressHomeEnabled, printWriter, "  mNavigationBarWindowState=");
            m3.append(StatusBarManager.windowStateToString(navigationBar.mNavigationBarWindowState));
            printWriter.println(m3.toString());
            printWriter.println("  mTransitionMode=".concat(BarTransitions.modeToString$1(navigationBar.mTransitionMode)));
            KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  mTransientShown="), navigationBar.mTransientShown, printWriter, "  mTransientShownFromGestureOnSystemBar="), navigationBar.mTransientShownFromGestureOnSystemBar, printWriter, "  mScreenPinningActive="), navigationBar.mScreenPinningActive, printWriter);
            CentralSurfaces.dumpBarTransitions(printWriter, "mNavigationBarView", navigationBar.mNavigationBarTransitions);
            printWriter.println("  mOrientedHandleSamplingRegion: " + navigationBar.mOrientedHandleSamplingRegion);
            NavigationBarView navigationBarView = (NavigationBarView) navigationBar.mView;
            navigationBarView.getClass();
            Rect rect = new Rect();
            Point point = new Point();
            navigationBarView.getContext().getDisplay().getRealSize(point);
            printWriter.println("NavigationBarView:");
            printWriter.println(String.format("      this: " + CentralSurfaces.viewInfo(navigationBarView) + " " + NavigationBarView.visibilityToString(navigationBarView.getVisibility()), new Object[0]));
            navigationBarView.getWindowVisibleDisplayFrame(rect);
            boolean z = rect.right > point.x || rect.bottom > point.y;
            StringBuilder sb = new StringBuilder("      window: ");
            sb.append(rect.toShortString());
            sb.append(" ");
            sb.append(NavigationBarView.visibilityToString(navigationBarView.getWindowVisibility()));
            sb.append(z ? " OFFSCREEN!" : "");
            printWriter.println(sb.toString());
            int id = navigationBarView.mCurrentView.getId();
            if (id != 0) {
                try {
                    str = navigationBarView.getContext().getResources().getResourceName(id);
                } catch (Resources.NotFoundException unused) {
                    str = "(unknown)";
                }
            } else {
                str = "(null)";
            }
            printWriter.println(String.format("      mCurrentView: id=%s (%dx%d) %s %f", str, Integer.valueOf(navigationBarView.mCurrentView.getWidth()), Integer.valueOf(navigationBarView.mCurrentView.getHeight()), NavigationBarView.visibilityToString(navigationBarView.mCurrentView.getVisibility()), Float.valueOf(navigationBarView.mCurrentView.getAlpha())));
            printWriter.println(String.format("      disabled=0x%08x vertical=%s darkIntensity=%.2f", Integer.valueOf(navigationBarView.mDisabledFlags), navigationBarView.mIsVertical ? "true" : "false", Float.valueOf(navigationBarView.mBarTransitions.mLightTransitionsController.mDarkIntensity)));
            printWriter.println("    mScreenOn: " + navigationBarView.mScreenOn);
            NavigationBarView.dumpButton(printWriter, "back", navigationBarView.getBackButton());
            NavigationBarView.dumpButton(printWriter, BcSmartspaceDataPlugin.UI_SURFACE_HOME_SCREEN, navigationBarView.getHomeButton());
            NavigationBarView.dumpButton(printWriter, "handle", (ButtonDispatcher) navigationBarView.mButtonDispatchers.get(R.id.home_handle));
            NavigationBarView.dumpButton(printWriter, "rcnt", navigationBarView.getRecentsButton());
            NavigationBarView.dumpButton(printWriter, "a11y", (ButtonDispatcher) navigationBarView.mButtonDispatchers.get(R.id.accessibility_button));
            NavigationBarView.dumpButton(printWriter, "ime", (ButtonDispatcher) navigationBarView.mButtonDispatchers.get(R.id.ime_switcher));
            NavigationBarInflaterView navigationBarInflaterView = navigationBarView.mNavigationInflaterView;
            if (navigationBarInflaterView != null) {
                StringBuilder m4 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "NavigationBarInflaterView", "  mCurrentLayout: ");
                m4.append(navigationBarInflaterView.mCurrentLayout);
                printWriter.println(m4.toString());
            }
            NavigationBarTransitions navigationBarTransitions = navigationBarView.mBarTransitions;
            navigationBarTransitions.getClass();
            printWriter.println("NavigationBarTransitions:");
            printWriter.println("  mMode: " + navigationBarTransitions.mMode);
            printWriter.println("  mAlwaysOpaque: false");
            StringBuilder m5 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  mAllowAutoDimWallpaperNotVisible: "), navigationBarTransitions.mAllowAutoDimWallpaperNotVisible, printWriter, "  mWallpaperVisible: "), navigationBarTransitions.mWallpaperVisible, printWriter, "  mLightsOut: "), navigationBarTransitions.mLightsOut, printWriter, "  mAutoDim: "), navigationBarTransitions.mAutoDim, printWriter, "  bg overrideAlpha: ");
            BarTransitions.BarBackgroundDrawable barBackgroundDrawable = navigationBarTransitions.mBarBackground;
            StringBuilder m6 = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(m5, barBackgroundDrawable.mOverrideAlpha, printWriter, "  bg color: "), barBackgroundDrawable.mColor, printWriter, "  bg frame: ");
            m6.append(barBackgroundDrawable.mFrame);
            printWriter.println(m6.toString());
            ContextualButtonGroup contextualButtonGroup = navigationBarView.mContextualButtonGroup;
            View view = contextualButtonGroup.mCurrentView;
            StringBuilder m7 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "ContextualButtonGroup", "  getVisibleContextButton(): ");
            int size = ((ArrayList) contextualButtonGroup.mButtonData).size() - 1;
            while (true) {
                if (size < 0) {
                    contextualButton = null;
                    break;
                } else {
                    if (((ContextualButtonGroup.ButtonData) ((ArrayList) contextualButtonGroup.mButtonData).get(size)).markedVisible) {
                        contextualButton = ((ContextualButtonGroup.ButtonData) ((ArrayList) contextualButtonGroup.mButtonData).get(size)).button;
                        break;
                    }
                    size--;
                }
            }
            m7.append(contextualButton);
            printWriter.println(m7.toString());
            StringBuilder m8 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("  isVisible(): "), contextualButtonGroup.getVisibility() == 0, printWriter, "  attached(): ");
            m8.append(view != null && view.isAttachedToWindow());
            printWriter.println(m8.toString());
            printWriter.println("  mButtonData [ ");
            for (int size2 = ((ArrayList) contextualButtonGroup.mButtonData).size() - 1; size2 >= 0; size2--) {
                ContextualButtonGroup.ButtonData buttonData = (ContextualButtonGroup.ButtonData) ((ArrayList) contextualButtonGroup.mButtonData).get(size2);
                View view2 = buttonData.button.mCurrentView;
                StringBuilder m9 = MutableObjectList$$ExternalSyntheticOutline0.m("    ", ": markedVisible=", size2);
                m9.append(buttonData.markedVisible);
                m9.append(" visible=");
                ContextualButton contextualButton2 = buttonData.button;
                m9.append(contextualButton2.getVisibility());
                m9.append(" attached=");
                m9.append(view2 != null && view2.isAttachedToWindow());
                m9.append(" alpha=");
                m9.append(contextualButton2.getAlpha());
                printWriter.println(m9.toString());
            }
            printWriter.println("  ]");
            navigationBarView.mEdgeBackGestureHandler.dump(printWriter);
            RegionSamplingHelper regionSamplingHelper = navigationBar.mRegionSamplingHelper;
            regionSamplingHelper.getClass();
            printWriter.println("RegionSamplingHelper:");
            printWriter.println("\tsampleView isAttached: " + regionSamplingHelper.mSampledView.isAttachedToWindow());
            StringBuilder sb2 = new StringBuilder("\tsampleView isScValid: ");
            sb2.append(regionSamplingHelper.mSampledView.isAttachedToWindow() ? Boolean.valueOf(regionSamplingHelper.mSampledView.getViewRootImpl().getSurfaceControl().isValid()) : "notAttached");
            printWriter.println(sb2.toString());
            StringBuilder m10 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("\tmSamplingEnabled: "), regionSamplingHelper.mSamplingEnabled, printWriter, "\tmSamplingListenerRegistered: "), regionSamplingHelper.mSamplingListenerRegistered, printWriter, "\tmSamplingRequestBounds: ");
            m10.append(regionSamplingHelper.mSamplingRequestBounds);
            printWriter.println(m10.toString());
            printWriter.println("\tmRegisteredSamplingBounds: " + regionSamplingHelper.mRegisteredSamplingBounds);
            StringBuilder m11 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("\tmLastMedianLuma: "), regionSamplingHelper.mLastMedianLuma, printWriter, "\tmCurrentMedianLuma: "), regionSamplingHelper.mCurrentMedianLuma, printWriter, "\tmWindowVisible: "), regionSamplingHelper.mWindowVisible, printWriter, "\tmWindowHasBlurs: "), regionSamplingHelper.mWindowHasBlurs, printWriter, "\tmWaitingOnDraw: "), regionSamplingHelper.mWaitingOnDraw, printWriter, "\tmRegisteredStopLayer: ");
            m11.append(regionSamplingHelper.mRegisteredStopLayer);
            printWriter.println(m11.toString());
            printWriter.println("\tmWrappedStopLayer: " + regionSamplingHelper.mWrappedStopLayer);
            KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(new StringBuilder("\tmIsDestroyed: "), regionSamplingHelper.mIsDestroyed, printWriter);
            AutoHideController autoHideController = navigationBar.mAutoHideController;
            if (autoHideController != null) {
                StringBuilder m12 = KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(printWriter, "AutoHideController:", "\tmAutoHideSuspended="), autoHideController.mAutoHideSuspended, printWriter, "\tisAnyTransientBarShown=");
                m12.append(autoHideController.isAnyTransientBarShown());
                printWriter.println(m12.toString());
                printWriter.println("\thasPendingAutoHide=" + autoHideController.mHandler.hasCallbacks(autoHideController.mAutoHide));
                printWriter.println("\tgetAutoHideTimeout=" + autoHideController.mAccessibilityManager.getRecommendedTimeoutMillis(2250, 4));
                printWriter.println("\tgetUserAutoHideTimeout=" + autoHideController.mAccessibilityManager.getRecommendedTimeoutMillis(350, 4));
            }
        }
    }

    public final void finishBarAnimations(int i) {
        NavigationBar navigationBar = (NavigationBar) this.mNavigationBars.get(i);
        if (navigationBar != null) {
            BarTransitions.BarBackgroundDrawable barBackgroundDrawable = navigationBar.mNavigationBarTransitions.mBarBackground;
            if (barBackgroundDrawable.mAnimating) {
                barBackgroundDrawable.mAnimating = false;
                barBackgroundDrawable.invalidateSelf();
                return;
            }
            return;
        }
        IOverviewProxy iOverviewProxy = this.mTaskbarDelegate.mOverviewProxyService.mOverviewProxy;
        if (iOverviewProxy == null) {
            return;
        }
        try {
            IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
            Parcel obtain = Parcel.obtain(proxy.mRemote);
            try {
                obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                proxy.mRemote.transact(32, obtain, null, 1);
                obtain.recycle();
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } catch (RemoteException e) {
            Log.e("TaskbarDelegate", "finishBarAnimations() failed", e);
        }
    }

    public final NavigationBar getDefaultNavigationBar() {
        SparseArray sparseArray = this.mNavigationBars;
        this.mDisplayTracker.getClass();
        return (NavigationBar) sparseArray.get(0);
    }

    public final NavigationBarView getNavigationBarView(int i) {
        NavigationBar navigationBar = (NavigationBar) this.mNavigationBars.get(i);
        if (navigationBar == null) {
            return null;
        }
        return (NavigationBarView) navigationBar.mView;
    }

    public final boolean initializeTaskbarIfNecessary() {
        boolean z = supportsTaskbar() && shouldCreateNavBarAndTaskBar(this.mContext.getDisplayId());
        TaskbarDelegate taskbarDelegate = this.mTaskbarDelegate;
        if (z) {
            Trace.beginSection("NavigationBarController#initializeTaskbarIfNecessary");
            int displayId = this.mContext.getDisplayId();
            boolean contains = this.mNavigationBars.contains(displayId);
            NavBarHelper navBarHelper = this.mNavBarHelper;
            navBarHelper.mTogglingNavbarTaskbar = contains;
            removeNavigationBar(displayId);
            taskbarDelegate.getClass();
            Trace.beginSection("TaskbarDelegate#init");
            try {
                if (!taskbarDelegate.mInitialized) {
                    taskbarDelegate.mDisplayId = displayId;
                    NavBarHelper navBarHelper2 = taskbarDelegate.mNavBarHelper;
                    navBarHelper2.getClass();
                    int i = navBarHelper2.mWindowStateDisplayId;
                    int i2 = navBarHelper2.mWindowState;
                    if (i == taskbarDelegate.mDisplayId) {
                        taskbarDelegate.mTaskBarWindowState = i2;
                    }
                    taskbarDelegate.mCommandQueue.addCallback((CommandQueue.Callbacks) taskbarDelegate);
                    taskbarDelegate.mOverviewProxyService.addCallback((OverviewProxyService.OverviewProxyListener) taskbarDelegate);
                    taskbarDelegate.onNavigationModeChanged(taskbarDelegate.mNavigationModeController.addListener(taskbarDelegate));
                    taskbarDelegate.mNavBarHelper.registerNavTaskStateUpdater(taskbarDelegate.mNavbarTaskbarStateUpdater);
                    taskbarDelegate.mScreenPinningNotify = new ScreenPinningNotify(taskbarDelegate.mContext.createWindowContext(taskbarDelegate.mDisplayManager.getDisplay(displayId), 2, null));
                    taskbarDelegate.updateSysuiFlags();
                    taskbarDelegate.mAutoHideController.mNavigationBar = taskbarDelegate.mAutoHideUiElement;
                    LightBarController lightBarController = taskbarDelegate.mLightBarController;
                    lightBarController.mNavigationBarController = taskbarDelegate.mLightBarTransitionsController;
                    lightBarController.updateNavigation();
                    taskbarDelegate.mPipOptional.ifPresent(new TaskbarDelegate$$ExternalSyntheticLambda0(taskbarDelegate, 2));
                    taskbarDelegate.mEdgeBackGestureHandler.setBackAnimation(taskbarDelegate.mBackAnimation);
                    taskbarDelegate.mTaskStackChangeListeners.registerTaskStackListener(taskbarDelegate.mTaskStackListener);
                    taskbarDelegate.mInitialized = true;
                }
                Trace.endSection();
                navBarHelper.mTogglingNavbarTaskbar = false;
            } finally {
                Trace.endSection();
            }
        } else if (taskbarDelegate.mInitialized) {
            taskbarDelegate.mCommandQueue.removeCallback((CommandQueue.Callbacks) taskbarDelegate);
            taskbarDelegate.mOverviewProxyService.removeCallback((OverviewProxyService.OverviewProxyListener) taskbarDelegate);
            taskbarDelegate.mNavigationModeController.removeListener(taskbarDelegate);
            taskbarDelegate.mNavBarHelper.removeNavTaskStateUpdater(taskbarDelegate.mNavbarTaskbarStateUpdater);
            taskbarDelegate.mScreenPinningNotify = null;
            taskbarDelegate.mAutoHideController.mNavigationBar = null;
            LightBarTransitionsController lightBarTransitionsController = taskbarDelegate.mLightBarTransitionsController;
            CommandQueue commandQueue = lightBarTransitionsController.mCommandQueue;
            LightBarTransitionsController.Callback callback = lightBarTransitionsController.mCallback;
            commandQueue.removeCallback((CommandQueue.Callbacks) callback);
            lightBarTransitionsController.mStatusBarStateController.removeCallback(callback);
            lightBarTransitionsController.mGestureNavigationSettingsObserver.unregister();
            LightBarController lightBarController2 = taskbarDelegate.mLightBarController;
            lightBarController2.mNavigationBarController = null;
            lightBarController2.updateNavigation();
            taskbarDelegate.mPipOptional.ifPresent(new TaskbarDelegate$$ExternalSyntheticLambda0(taskbarDelegate, 0));
            taskbarDelegate.mTaskStackChangeListeners.unregisterTaskStackListener(taskbarDelegate.mTaskStackListener);
            taskbarDelegate.mInitialized = false;
        }
        return z;
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        boolean z = this.mIsLargeScreen;
        this.mIsLargeScreen = Utilities.isLargeScreen(this.mContext);
        boolean applyNewConfig = this.mConfigChanges.applyNewConfig(this.mContext.getResources());
        int i = 0;
        boolean z2 = this.mIsLargeScreen != z;
        StringBuilder sb = new StringBuilder("NavbarController: newConfig=");
        sb.append(configuration);
        sb.append(" mTaskbarDelegate initialized=");
        BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(sb, this.mTaskbarDelegate.mInitialized, " willApplyConfigToNavbars=", applyNewConfig, " navBarCount=");
        sb.append(this.mNavigationBars.size());
        Log.i("NoBackGesture", sb.toString());
        if (z2) {
            boolean initializeTaskbarIfNecessary = initializeTaskbarIfNecessary();
            if (!initializeTaskbarIfNecessary && this.mNavigationBars.get(this.mContext.getDisplayId()) == null) {
                createNavigationBar(this.mContext.getDisplay(), null, null);
            }
            if (initializeTaskbarIfNecessary) {
                return;
            }
        }
        if (applyNewConfig) {
            while (i < this.mNavigationBars.size()) {
                int keyAt = this.mNavigationBars.keyAt(i);
                Bundle bundle = new Bundle();
                NavigationBar navigationBar = (NavigationBar) this.mNavigationBars.get(keyAt);
                if (navigationBar != null) {
                    bundle.putInt("disabled_state", navigationBar.mDisabledFlags1);
                    bundle.putInt("disabled2_state", navigationBar.mDisabledFlags2);
                    bundle.putInt("appearance", navigationBar.mAppearance);
                    bundle.putInt("behavior", navigationBar.mBehavior);
                    bundle.putBoolean("transient_state", navigationBar.mTransientShown);
                    LightBarTransitionsController lightBarTransitionsController = navigationBar.mNavigationBarTransitions.mLightTransitionsController;
                    ValueAnimator valueAnimator = lightBarTransitionsController.mTintAnimator;
                    bundle.putFloat("dark_intensity", (valueAnimator == null || !valueAnimator.isRunning()) ? lightBarTransitionsController.mDarkIntensity : lightBarTransitionsController.mNextDarkIntensity);
                }
                removeNavigationBar(keyAt);
                createNavigationBar(this.mDisplayManager.getDisplay(keyAt), bundle, null);
                i++;
            }
            return;
        }
        while (i < this.mNavigationBars.size()) {
            NavigationBar navigationBar2 = (NavigationBar) this.mNavigationBars.valueAt(i);
            navigationBar2.getClass();
            int rotation = configuration.windowConfiguration.getRotation();
            Locale locale = navigationBar2.mContext.getResources().getConfiguration().locale;
            int layoutDirectionFromLocale = TextUtils.getLayoutDirectionFromLocale(locale);
            if (!locale.equals(navigationBar2.mLocale) || layoutDirectionFromLocale != navigationBar2.mLayoutDirection) {
                navigationBar2.mLocale = locale;
                navigationBar2.mLayoutDirection = layoutDirectionFromLocale;
                ((NavigationBarView) navigationBar2.mView).setLayoutDirection(layoutDirectionFromLocale);
            }
            navigationBar2.repositionNavigationBar(rotation);
            if (navigationBar2.mNavBarMode == 2 && navigationBar2.mOrientationHandle != null && rotation != navigationBar2.mCurrentRotation) {
                navigationBar2.mCurrentRotation = rotation;
                navigationBar2.orientSecondaryHomeHandle();
            }
            i++;
        }
    }

    @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
    public final void onNavigationModeChanged(int i) {
        final int i2 = this.mNavMode;
        if (i2 == i) {
            return;
        }
        this.mNavMode = i;
        this.mExecutor.execute(new Runnable() { // from class: com.android.systemui.navigationbar.NavigationBarControllerImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                NavigationBarControllerImpl navigationBarControllerImpl = NavigationBarControllerImpl.this;
                if (i2 != navigationBarControllerImpl.mNavMode && !navigationBarControllerImpl.initializeTaskbarIfNecessary() && navigationBarControllerImpl.mNavigationBars.get(navigationBarControllerImpl.mContext.getDisplayId()) == null) {
                    navigationBarControllerImpl.createNavigationBar(navigationBarControllerImpl.mContext.getDisplay(), null, null);
                }
                for (int i3 = 0; i3 < navigationBarControllerImpl.mNavigationBars.size(); i3++) {
                    NavigationBar navigationBar = (NavigationBar) navigationBarControllerImpl.mNavigationBars.valueAt(i3);
                    if (navigationBar != null) {
                        ((NavigationBarView) navigationBar.mView).updateStates();
                    }
                }
            }
        });
    }

    public final void removeNavigationBar(int i) {
        NavigationBar navigationBar = (NavigationBar) this.mNavigationBars.get(i);
        if (navigationBar != null) {
            Trace.beginSection("NavigationBar#destroyView");
            try {
                navigationBar.mAutoHideController = null;
                ((NavigationBarView) navigationBar.mView).mAutoHideController = null;
                navigationBar.mCommandQueue.removeCallback((CommandQueue.Callbacks) navigationBar);
                Trace.beginSection("NavigationBar#removeViewImmediate");
                try {
                    navigationBar.mViewCaptureAwareWindowManager.removeViewImmediate(((NavigationBarView) navigationBar.mView).getRootView());
                    Trace.endSection();
                    navigationBar.mNavigationModeController.removeListener(navigationBar.mModeChangedListener);
                    navigationBar.mEdgeBackGestureHandler.mStateChangeCallback = null;
                    navigationBar.mNavBarHelper.removeNavTaskStateUpdater(navigationBar.mNavbarTaskbarStateUpdater);
                    NotificationShadeDepthController notificationShadeDepthController = navigationBar.mNotificationShadeDepthController;
                    notificationShadeDepthController.listeners.remove(navigationBar.mDepthListener);
                    DeviceConfigProxy deviceConfigProxy = navigationBar.mDeviceConfigProxy;
                    NavigationBar.AnonymousClass5 anonymousClass5 = navigationBar.mOnPropertiesChangedListener;
                    deviceConfigProxy.getClass();
                    DeviceConfig.removeOnPropertiesChangedListener(anonymousClass5);
                    navigationBar.mTaskStackChangeListeners.unregisterTaskStackListener(navigationBar.mTaskStackListener);
                    Trace.endSection();
                    this.mNavigationBars.remove(i);
                } finally {
                    Trace.endSection();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean shouldCreateNavBarAndTaskBar(int i) {
        if (this.mHasNavBar.indexOfKey(i) > -1) {
            return this.mHasNavBar.get(i);
        }
        try {
            boolean hasNavigationBar = WindowManagerGlobal.getWindowManagerService().hasNavigationBar(i);
            this.mHasNavBar.put(i, hasNavigationBar);
            return hasNavigationBar;
        } catch (RemoteException unused) {
            Log.w("NavigationBarControllerImpl", "Cannot get WindowManager.");
            return false;
        }
    }

    public boolean supportsTaskbar() {
        return this.mIsLargeScreen || !this.mIsPhone;
    }

    public final void touchAutoDim(int i) {
        NavigationBar navigationBar = (NavigationBar) this.mNavigationBars.get(i);
        boolean z = false;
        if (navigationBar != null) {
            navigationBar.mNavigationBarTransitions.setAutoDim(false);
            Handler handler = navigationBar.mHandler;
            NavigationBar$$ExternalSyntheticLambda0 navigationBar$$ExternalSyntheticLambda0 = navigationBar.mAutoDim;
            handler.removeCallbacks(navigationBar$$ExternalSyntheticLambda0);
            int state = navigationBar.mStatusBarStateController.getState();
            if (state == 1 || state == 2) {
                return;
            }
            handler.postDelayed(navigationBar$$ExternalSyntheticLambda0, 2250L);
            return;
        }
        TaskbarDelegate taskbarDelegate = this.mTaskbarDelegate;
        if (taskbarDelegate.mOverviewProxyService.mOverviewProxy == null) {
            return;
        }
        try {
            int state2 = taskbarDelegate.mStatusBarStateController.getState();
            if (state2 != 1 && state2 != 2) {
                z = true;
            }
            IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) taskbarDelegate.mOverviewProxyService.mOverviewProxy;
            Parcel obtain = Parcel.obtain(proxy.mRemote);
            try {
                obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                obtain.writeBoolean(z);
                proxy.mRemote.transact(33, obtain, null, 1);
                obtain.recycle();
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } catch (RemoteException e) {
            Log.e("TaskbarDelegate", "touchAutoDim() failed", e);
        }
    }

    public final void transitionTo(int i, int i2) {
        NavigationBar navigationBar = (NavigationBar) this.mNavigationBars.get(i);
        if (navigationBar != null) {
            NavigationBarTransitions navigationBarTransitions = navigationBar.mNavigationBarTransitions;
            int i3 = navigationBarTransitions.mMode;
            if (i3 == i2) {
                return;
            }
            navigationBarTransitions.mMode = i2;
            navigationBarTransitions.onTransition(i3, i2, true);
            return;
        }
        IOverviewProxy iOverviewProxy = this.mTaskbarDelegate.mOverviewProxyService.mOverviewProxy;
        if (iOverviewProxy == null) {
            return;
        }
        try {
            IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
            Parcel obtain = Parcel.obtain(proxy.mRemote);
            try {
                obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                obtain.writeInt(i2);
                obtain.writeBoolean(true);
                proxy.mRemote.transact(34, obtain, null, 1);
                obtain.recycle();
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } catch (RemoteException e) {
            Log.e("TaskbarDelegate", "transitionTo() failed, barMode: " + i2, e);
        }
    }
}

package com.android.systemui.navigationbar;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.WindowInsets;
import com.android.internal.statusbar.LetterboxDetails;
import com.android.internal.view.AppearanceRegion;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.NavBarHelper;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.shared.recents.IOverviewProxy;
import com.android.systemui.shared.recents.utilities.Utilities;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.statusbar.AutoHideUiElement;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.AutoHideController;
import com.android.systemui.statusbar.phone.AutoHideController$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.LightBarTransitionsController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.wm.shell.R;
import com.android.wm.shell.back.BackAnimationController;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15;
import java.io.PrintWriter;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TaskbarDelegate implements CommandQueue.Callbacks, OverviewProxyService.OverviewProxyListener, NavigationModeController.ModeChangedListener, Dumpable {
    public int mAppearance;
    public AutoHideController mAutoHideController;
    public BackAnimationController.BackAnimationImpl mBackAnimation;
    public int mBehavior;
    public CommandQueue mCommandQueue;
    public final Context mContext;
    public int mDisabledFlags;
    public int mDisplayId;
    public final DisplayManager mDisplayManager;
    public EdgeBackGestureHandler mEdgeBackGestureHandler;
    public boolean mInitialized;
    public LightBarController mLightBarController;
    public LightBarTransitionsController mLightBarTransitionsController;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15 mLightBarTransitionsControllerFactory;
    public NavBarHelper mNavBarHelper;
    public int mNavigationIconHints;
    public NavigationModeController mNavigationModeController;
    public OverviewProxyService mOverviewProxyService;
    public Optional mPipOptional;
    public ScreenPinningNotify mScreenPinningNotify;
    public final StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public final StatusBarStateController mStatusBarStateController;
    public SysUiState mSysUiState;
    public TaskStackChangeListeners mTaskStackChangeListeners;
    public boolean mTaskbarTransientShowing;
    public int mTransitionMode;
    public final AnonymousClass1 mNavbarTaskbarStateUpdater = new NavBarHelper.NavbarTaskbarStateUpdater() { // from class: com.android.systemui.navigationbar.TaskbarDelegate.1
        @Override // com.android.systemui.navigationbar.NavBarHelper.NavbarTaskbarStateUpdater
        public final void updateAccessibilityServicesState() {
            TaskbarDelegate.this.updateSysuiFlags();
        }

        @Override // com.android.systemui.navigationbar.NavBarHelper.NavbarTaskbarStateUpdater
        public final void updateAssistantAvailable(boolean z, boolean z2) {
            IOverviewProxy iOverviewProxy = TaskbarDelegate.this.mOverviewProxyService.mOverviewProxy;
            if (iOverviewProxy == null) {
                return;
            }
            try {
                ((IOverviewProxy.Stub.Proxy) iOverviewProxy).onAssistantAvailable(z, z2);
            } catch (RemoteException e) {
                Log.e("TaskbarDelegate", "onAssistantAvailable() failed, available: " + z, e);
            }
        }

        @Override // com.android.systemui.navigationbar.NavBarHelper.NavbarTaskbarStateUpdater
        public final void updateWallpaperVisibility(int i, boolean z) {
            IOverviewProxy iOverviewProxy = TaskbarDelegate.this.mOverviewProxyService.mOverviewProxy;
            if (iOverviewProxy == null) {
                return;
            }
            try {
                IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
                Parcel obtain = Parcel.obtain(proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                    obtain.writeInt(i);
                    obtain.writeBoolean(z);
                    proxy.mRemote.transact(30, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (RemoteException e) {
                Log.e("TaskbarDelegate", "updateWallpaperVisibility() failed, visible: " + z, e);
            }
        }
    };
    public int mTaskBarWindowState = 0;
    public final AnonymousClass2 mTaskStackListener = new TaskStackChangeListener() { // from class: com.android.systemui.navigationbar.TaskbarDelegate.2
        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public final void onLockTaskModeChanged(int i) {
            TaskbarDelegate taskbarDelegate = TaskbarDelegate.this;
            SysUiState sysUiState = taskbarDelegate.mSysUiState;
            sysUiState.setFlag(1L, i == 2);
            sysUiState.commitUpdate(taskbarDelegate.mDisplayId);
        }
    };
    public int mNavigationMode = -1;
    public final AnonymousClass3 mAutoHideUiElement = new AnonymousClass3();
    public final TaskbarDelegate$$ExternalSyntheticLambda0 mPipListener = new TaskbarDelegate$$ExternalSyntheticLambda0(this, 1);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.navigationbar.TaskbarDelegate$3, reason: invalid class name */
    public final class AnonymousClass3 implements AutoHideUiElement, LightBarTransitionsController.DarkIntensityApplier {
        public /* synthetic */ AnonymousClass3() {
        }

        @Override // com.android.systemui.statusbar.phone.LightBarTransitionsController.DarkIntensityApplier
        public void applyDarkIntensity(float f) {
            OverviewProxyService overviewProxyService = TaskbarDelegate.this.mOverviewProxyService;
            overviewProxyService.getClass();
            try {
                IOverviewProxy iOverviewProxy = overviewProxyService.mOverviewProxy;
                if (iOverviewProxy == null) {
                    Log.e("OverviewProxyService", "Failed to get overview proxy to update nav buttons dark intensity");
                    return;
                }
                IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
                Parcel obtain = Parcel.obtain(proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                    obtain.writeFloat(f);
                    proxy.mRemote.transact(23, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (RemoteException e) {
                Log.e("OverviewProxyService", "Failed to call onNavButtonsDarkIntensityChanged()", e);
            }
        }

        @Override // com.android.systemui.statusbar.phone.LightBarTransitionsController.DarkIntensityApplier
        public int getTintAnimationDuration() {
            return 120;
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public void hide() {
            TaskbarDelegate taskbarDelegate = TaskbarDelegate.this;
            if (taskbarDelegate.mTaskbarTransientShowing) {
                taskbarDelegate.mTaskbarTransientShowing = false;
                taskbarDelegate.mEdgeBackGestureHandler.mIsNavBarShownTransiently = false;
                int transitionMode = NavBarHelper.transitionMode(taskbarDelegate.mAppearance, false);
                if (taskbarDelegate.updateTransitionMode$1(transitionMode)) {
                    LightBarController lightBarController = taskbarDelegate.mLightBarController;
                    lightBarController.mHasLightNavigationBar = LightBarController.isLight(lightBarController.mAppearance, transitionMode, 16);
                }
            }
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public boolean isVisible() {
            return TaskbarDelegate.this.mTaskbarTransientShowing;
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public void synchronizeState() {
            TaskbarDelegate.this.checkNavBarModes();
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.navigationbar.TaskbarDelegate$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.android.systemui.navigationbar.TaskbarDelegate$2] */
    public TaskbarDelegate(Context context, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15, StatusBarKeyguardViewManager statusBarKeyguardViewManager, StatusBarStateController statusBarStateController) {
        this.mLightBarTransitionsControllerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15;
        this.mContext = context;
        this.mDisplayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
        statusBarKeyguardViewManager.mTaskbarDelegate = this;
        this.mStatusBarStateController = statusBarStateController;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void abortTransient(int i, int i2) {
        if (i == this.mDisplayId && (WindowInsets.Type.navigationBars() & i2) != 0 && this.mTaskbarTransientShowing) {
            this.mTaskbarTransientShowing = false;
            this.mEdgeBackGestureHandler.mIsNavBarShownTransiently = false;
            int transitionMode = NavBarHelper.transitionMode(this.mAppearance, false);
            if (updateTransitionMode$1(transitionMode)) {
                LightBarController lightBarController = this.mLightBarController;
                lightBarController.mHasLightNavigationBar = LightBarController.isLight(lightBarController.mAppearance, transitionMode, 16);
            }
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void appTransitionCancelled(int i) {
        appTransitionPending(false);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void appTransitionFinished(int i) {
        appTransitionPending(false);
    }

    public final void appTransitionPending(boolean z) {
        IOverviewProxy iOverviewProxy = this.mOverviewProxyService.mOverviewProxy;
        if (iOverviewProxy == null) {
            return;
        }
        try {
            IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
            Parcel obtain = Parcel.obtain(proxy.mRemote);
            try {
                obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                obtain.writeBoolean(z);
                proxy.mRemote.transact(35, obtain, null, 1);
                obtain.recycle();
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } catch (RemoteException e) {
            Log.e("TaskbarDelegate", "appTransitionPending() failed, pending: " + z, e);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void appTransitionStarting(int i, long j, long j2, boolean z) {
        appTransitionPending(false);
    }

    public final void checkNavBarModes() {
        IOverviewProxy iOverviewProxy = this.mOverviewProxyService.mOverviewProxy;
        if (iOverviewProxy == null) {
            return;
        }
        try {
            IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
            Parcel obtain = Parcel.obtain(proxy.mRemote);
            try {
                obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                proxy.mRemote.transact(31, obtain, null, 1);
                obtain.recycle();
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } catch (RemoteException e) {
            Log.e("TaskbarDelegate", "checkNavBarModes() failed", e);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void disable(int i, int i2, int i3, boolean z) {
        this.mDisabledFlags = i2;
        updateSysuiFlags();
        OverviewProxyService overviewProxyService = this.mOverviewProxyService;
        overviewProxyService.getClass();
        try {
            IOverviewProxy iOverviewProxy = overviewProxyService.mOverviewProxy;
            if (iOverviewProxy == null) {
                Log.e("OverviewProxyService", "Failed to get overview proxy for disable flags.");
                return;
            }
            IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
            Parcel obtain = Parcel.obtain(proxy.mRemote);
            try {
                obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                obtain.writeInt(i);
                obtain.writeInt(i2);
                obtain.writeInt(i3);
                obtain.writeBoolean(z);
                proxy.mRemote.transact(20, obtain, null, 1);
                obtain.recycle();
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } catch (RemoteException e) {
            Log.e("OverviewProxyService", "Failed to call disable()", e);
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("TaskbarDelegate (displayId=" + this.mDisplayId + "):");
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("  mNavigationIconHints="), this.mNavigationIconHints, printWriter, "  mNavigationMode="), this.mNavigationMode, printWriter, "  mDisabledFlags="), this.mDisabledFlags, printWriter, "  mTaskBarWindowState="), this.mTaskBarWindowState, printWriter, "  mBehavior="), this.mBehavior, printWriter, "  mTaskbarTransientShowing="), this.mTaskbarTransientShowing, printWriter);
        this.mEdgeBackGestureHandler.dump(printWriter);
    }

    public int getNavigationMode() {
        return this.mNavigationMode;
    }

    @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
    public final void onNavigationModeChanged(int i) {
        this.mNavigationMode = i;
        this.mEdgeBackGestureHandler.onNavigationModeChanged(i);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void onRotationProposal(int i, boolean z) {
        OverviewProxyService overviewProxyService = this.mOverviewProxyService;
        overviewProxyService.getClass();
        try {
            IOverviewProxy iOverviewProxy = overviewProxyService.mOverviewProxy;
            if (iOverviewProxy == null) {
                Log.e("OverviewProxyService", "Failed to get overview proxy for proposing rotation.");
                return;
            }
            IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
            Parcel obtain = Parcel.obtain(proxy.mRemote);
            try {
                obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                obtain.writeInt(i);
                obtain.writeBoolean(z);
                proxy.mRemote.transact(19, obtain, null, 1);
                obtain.recycle();
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } catch (RemoteException e) {
            Log.e("OverviewProxyService", "Failed to call onRotationProposal()", e);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void onSystemBarAttributesChanged(int i, int i2, AppearanceRegion[] appearanceRegionArr, boolean z, int i3, int i4, String str, LetterboxDetails[] letterboxDetailsArr) {
        boolean z2;
        OverviewProxyService overviewProxyService = this.mOverviewProxyService;
        overviewProxyService.getClass();
        try {
            IOverviewProxy iOverviewProxy = overviewProxyService.mOverviewProxy;
            if (iOverviewProxy != null) {
                IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
                Parcel obtain = Parcel.obtain(proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                    obtain.writeInt(i);
                    obtain.writeInt(i3);
                    proxy.mRemote.transact(21, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } else {
                Log.e("OverviewProxyService", "Failed to get overview proxy for system bar attr change.");
            }
        } catch (RemoteException e) {
            Log.e("OverviewProxyService", "Failed to call onSystemBarAttributesChanged()", e);
        }
        if (this.mAppearance != i2) {
            this.mAppearance = i2;
            z2 = updateTransitionMode$1(NavBarHelper.transitionMode(i2, this.mTaskbarTransientShowing));
        } else {
            z2 = false;
        }
        if (i == this.mDisplayId) {
            this.mLightBarController.onNavigationBarAppearanceChanged(i2, this.mTransitionMode, z2, z);
        }
        if (this.mBehavior != i3) {
            this.mBehavior = i3;
            updateSysuiFlags();
        }
    }

    @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
    public final void onTaskbarAutohideSuspend(boolean z) {
        AutoHideController$$ExternalSyntheticLambda0 autoHideController$$ExternalSyntheticLambda0 = null;
        if (z) {
            AutoHideController autoHideController = this.mAutoHideController;
            AutoHideController$$ExternalSyntheticLambda0 autoHideController$$ExternalSyntheticLambda02 = autoHideController.mAutoHide;
            Handler handler = autoHideController.mHandler;
            handler.removeCallbacks(autoHideController$$ExternalSyntheticLambda02);
            if (autoHideController.mStatusBar != null) {
                autoHideController$$ExternalSyntheticLambda0 = new AutoHideController$$ExternalSyntheticLambda0(autoHideController, 1);
            } else if (autoHideController.mNavigationBar != null) {
                autoHideController$$ExternalSyntheticLambda0 = new AutoHideController$$ExternalSyntheticLambda0(autoHideController, 2);
            }
            if (autoHideController$$ExternalSyntheticLambda0 != null) {
                handler.removeCallbacks(autoHideController$$ExternalSyntheticLambda0);
            }
            autoHideController.mAutoHideSuspended = autoHideController.isAnyTransientBarShown();
            return;
        }
        AutoHideController autoHideController2 = this.mAutoHideController;
        if (autoHideController2.mAutoHideSuspended) {
            autoHideController2.mAutoHideSuspended = false;
            AutoHideController$$ExternalSyntheticLambda0 autoHideController$$ExternalSyntheticLambda03 = autoHideController2.mAutoHide;
            Handler handler2 = autoHideController2.mHandler;
            handler2.removeCallbacks(autoHideController$$ExternalSyntheticLambda03);
            handler2.postDelayed(autoHideController$$ExternalSyntheticLambda03, autoHideController2.mAccessibilityManager.getRecommendedTimeoutMillis(2250, 4));
            if (autoHideController2.mStatusBar != null) {
                autoHideController$$ExternalSyntheticLambda0 = new AutoHideController$$ExternalSyntheticLambda0(autoHideController2, 1);
            } else if (autoHideController2.mNavigationBar != null) {
                autoHideController$$ExternalSyntheticLambda0 = new AutoHideController$$ExternalSyntheticLambda0(autoHideController2, 2);
            }
            if (autoHideController$$ExternalSyntheticLambda0 != null) {
                handler2.postDelayed(autoHideController$$ExternalSyntheticLambda0, 500L);
            }
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void setImeWindowStatus(int i, int i2, int i3, boolean z) {
        boolean isImeShown = this.mNavBarHelper.isImeShown(i2);
        boolean z2 = false;
        if (!isImeShown) {
            isImeShown = (i2 & 4) != 0;
        }
        if (isImeShown && z) {
            z2 = true;
        }
        int calculateBackDispositionHints = Utilities.calculateBackDispositionHints(this.mNavigationIconHints, i3, isImeShown, z2);
        if (calculateBackDispositionHints != this.mNavigationIconHints) {
            this.mNavigationIconHints = calculateBackDispositionHints;
            updateSysuiFlags();
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void setNavigationBarLumaSamplingEnabled(int i, boolean z) {
        OverviewProxyService overviewProxyService = this.mOverviewProxyService;
        overviewProxyService.getClass();
        try {
            IOverviewProxy iOverviewProxy = overviewProxyService.mOverviewProxy;
            if (iOverviewProxy == null) {
                Log.e("OverviewProxyService", "Failed to get overview proxy to enable/disable nav bar lumasampling");
                return;
            }
            IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
            Parcel obtain = Parcel.obtain(proxy.mRemote);
            try {
                obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                obtain.writeInt(i);
                obtain.writeBoolean(z);
                proxy.mRemote.transact(24, obtain, null, 1);
                obtain.recycle();
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } catch (RemoteException e) {
            Log.e("OverviewProxyService", "Failed to call onNavigationBarLumaSamplingEnabled()", e);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void setWindowState(int i, int i2, int i3) {
        if (i == this.mDisplayId && i2 == 2 && this.mTaskBarWindowState != i3) {
            this.mTaskBarWindowState = i3;
            updateSysuiFlags();
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void showPinningEnterExitToast(boolean z) {
        updateSysuiFlags();
        ScreenPinningNotify screenPinningNotify = this.mScreenPinningNotify;
        if (screenPinningNotify == null) {
            return;
        }
        if (z) {
            screenPinningNotify.makeAllUserToastAndShow(R.string.screen_pinning_start);
        } else {
            screenPinningNotify.makeAllUserToastAndShow(R.string.screen_pinning_exit);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void showPinningEscapeToast() {
        updateSysuiFlags();
        ScreenPinningNotify screenPinningNotify = this.mScreenPinningNotify;
        if (screenPinningNotify == null) {
            return;
        }
        screenPinningNotify.showEscapeToast(QuickStepContract.isGesturalMode(this.mNavigationMode), !QuickStepContract.isGesturalMode(this.mNavigationMode));
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void showTransient(int i, int i2, boolean z) {
        if (i != this.mDisplayId || (WindowInsets.Type.navigationBars() & i2) == 0 || this.mTaskbarTransientShowing) {
            return;
        }
        this.mTaskbarTransientShowing = true;
        this.mEdgeBackGestureHandler.mIsNavBarShownTransiently = true;
        int transitionMode = NavBarHelper.transitionMode(this.mAppearance, true);
        if (updateTransitionMode$1(transitionMode)) {
            LightBarController lightBarController = this.mLightBarController;
            lightBarController.mHasLightNavigationBar = LightBarController.isLight(lightBarController.mAppearance, transitionMode, 16);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void toggleTaskbar() {
        IOverviewProxy iOverviewProxy = this.mOverviewProxyService.mOverviewProxy;
        if (iOverviewProxy == null) {
            return;
        }
        try {
            IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
            Parcel obtain = Parcel.obtain(proxy.mRemote);
            try {
                obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                proxy.mRemote.transact(28, obtain, null, 1);
                obtain.recycle();
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } catch (RemoteException e) {
            Log.e("TaskbarDelegate", "onTaskbarToggled() failed", e);
        }
    }

    public final void updateSysuiFlags() {
        long j = this.mNavBarHelper.mA11yButtonState;
        boolean z = (j & 16) != 0;
        boolean z2 = (j & 32) != 0;
        SysUiState sysUiState = this.mSysUiState;
        sysUiState.setFlag(16L, z);
        sysUiState.setFlag(32L, z2);
        sysUiState.setFlag(262144L, (this.mNavigationIconHints & 1) != 0);
        sysUiState.setFlag(1048576L, (this.mNavigationIconHints & 4) != 0);
        sysUiState.setFlag(128L, (this.mDisabledFlags & 16777216) != 0);
        sysUiState.setFlag(256L, (this.mDisabledFlags & 2097152) != 0);
        sysUiState.setFlag(4194304L, (this.mDisabledFlags & 4194304) != 0);
        sysUiState.setFlag(2L, !(this.mTaskBarWindowState == 0));
        sysUiState.setFlag(131072L, this.mBehavior != 2);
        sysUiState.commitUpdate(this.mDisplayId);
    }

    public final boolean updateTransitionMode$1(int i) {
        if (this.mTransitionMode == i) {
            return false;
        }
        this.mTransitionMode = i;
        IOverviewProxy iOverviewProxy = this.mOverviewProxyService.mOverviewProxy;
        if (iOverviewProxy != null) {
            try {
                IOverviewProxy.Stub.Proxy proxy = (IOverviewProxy.Stub.Proxy) iOverviewProxy;
                Parcel obtain = Parcel.obtain(proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.systemui.shared.recents.IOverviewProxy");
                    obtain.writeInt(i);
                    obtain.writeBoolean(true);
                    proxy.mRemote.transact(22, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (RemoteException e) {
                Log.e("TaskbarDelegate", "onTransitionModeUpdated() failed, barMode: " + i, e);
            }
        }
        AutoHideController autoHideController = this.mAutoHideController;
        if (autoHideController != null) {
            autoHideController.touchAutoHide();
        }
        return true;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void appTransitionPending(int i, boolean z) {
        appTransitionPending(true);
    }
}

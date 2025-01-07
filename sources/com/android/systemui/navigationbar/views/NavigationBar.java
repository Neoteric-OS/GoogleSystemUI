package com.android.systemui.navigationbar.views;

import android.animation.ObjectAnimator;
import android.app.ActivityTaskManager;
import android.app.IActivityTaskManager;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.util.Log;
import android.util.MathUtils;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import androidx.emoji2.text.ConcurrencyHelpers$$ExternalSyntheticLambda0;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.statusbar.LetterboxDetails;
import com.android.internal.util.LatencyTracker;
import com.android.internal.view.AppearanceRegion;
import com.android.internal.view.RotationPolicy;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.NavBarHelper;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.navigationbar.gestural.QuickswitchOrientedNavHandle;
import com.android.systemui.navigationbar.views.NavigationBarTransitions;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.navigationbar.views.buttons.ButtonDispatcher;
import com.android.systemui.navigationbar.views.buttons.ButtonInterface;
import com.android.systemui.navigationbar.views.buttons.DeadZone;
import com.android.systemui.navigationbar.views.buttons.KeyButtonView;
import com.android.systemui.navigationbar.views.buttons.NavBarButtonClickLogger;
import com.android.systemui.navigationbar.views.buttons.NavbarOrientationTrackingLogger;
import com.android.systemui.navigationbar.views.buttons.NearestTouchFrame;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.shared.recents.IOverviewProxy;
import com.android.systemui.shared.recents.utilities.Utilities;
import com.android.systemui.shared.rotation.FloatingRotationButton;
import com.android.systemui.shared.rotation.FloatingRotationButtonPositionCalculator;
import com.android.systemui.shared.rotation.RotationButtonController;
import com.android.systemui.shared.rotation.RotationButtonController$$ExternalSyntheticLambda0;
import com.android.systemui.shared.rotation.RotationPolicyUtil;
import com.android.systemui.shared.statusbar.phone.BarTransitions;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.shared.system.SysUiStatsLog;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.statusbar.AutoHideUiElement;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.phone.AutoHideController;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.LightBarTransitionsController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.Utils;
import com.android.systemui.util.ViewController;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.handles.RegionSamplingHelper;
import com.google.android.systemui.assist.AssistManagerGoogle;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NavigationBar extends ViewController implements CommandQueue.Callbacks {
    public final AccessibilityManager mAccessibilityManager;
    public int mAppearance;
    public final Lazy mAssistManagerLazy;
    public final NavigationBar$$ExternalSyntheticLambda0 mAutoDim;
    public AutoHideController mAutoHideController;
    public final AutoHideController.Factory mAutoHideControllerFactory;
    public final AnonymousClass1 mAutoHideUiElement;
    public final Optional mBackAnimation;
    public int mBehavior;
    public final Lazy mCentralSurfacesOptionalLazy;
    public final CommandQueue mCommandQueue;
    public final Context mContext;
    public MotionEvent mCurrentDownEvent;
    public int mCurrentRotation;
    public final DeadZone mDeadZone;
    public final AnonymousClass6 mDepthListener;
    public final DeviceConfigProxy mDeviceConfigProxy;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public int mDisabledFlags1;
    public int mDisabledFlags2;
    public int mDisplayId;
    public final DisplayTracker mDisplayTracker;
    public final EdgeBackGestureHandler mEdgeBackGestureHandler;
    public final NavigationBar$$ExternalSyntheticLambda0 mEnableLayoutTransitions;
    public NavigationBarFrame mFrame;
    public final Handler mHandler;
    public boolean mHomeBlockedThisTouch;
    public Optional mHomeButtonLongPressDurationMs;
    public boolean mHomeButtonLongPressHapticEnabled;
    public boolean mImeVisible;
    public final InputMethodManager mInputMethodManager;
    public final Binder mInsetsSourceOwner;
    public boolean mIsOnDefaultDisplay;
    public final KeyguardStateController mKeyguardStateController;
    public long mLastLockToAppLongPress;
    public int mLayoutDirection;
    public LightBarController mLightBarController;
    public final LightBarController.Factory mLightBarControllerFactory;
    public Locale mLocale;
    public boolean mLongPressHomeEnabled;
    public final AutoHideController mMainAutoHideController;
    public final LightBarController mMainLightBarController;
    public final MetricsLogger mMetricsLogger;
    public final AnonymousClass11 mModeChangedListener;
    public final NavBarButtonClickLogger mNavBarButtonClickLogger;
    public final NavBarHelper mNavBarHelper;
    public int mNavBarMode;
    public final int mNavColorSampleMargin;
    public final NavbarOrientationTrackingLogger mNavbarOrientationTrackingLogger;
    public final AnonymousClass2 mNavbarTaskbarStateUpdater;
    public final NavigationBarTransitions mNavigationBarTransitions;
    public int mNavigationBarWindowState;
    public int mNavigationIconHints;
    public final NavigationModeController mNavigationModeController;
    public final NotificationRemoteInputManager mNotificationRemoteInputManager;
    public final NotificationShadeDepthController mNotificationShadeDepthController;
    public final NavigationBar$$ExternalSyntheticLambda25 mOnComputeInternalInsetsListener;
    public final AnonymousClass5 mOnPropertiesChangedListener;
    public final NavigationBar$$ExternalSyntheticLambda0 mOnVariableDurationHomeLongClick;
    public QuickswitchOrientedNavHandle mOrientationHandle;
    public NavigationBar$$ExternalSyntheticLambda28 mOrientationHandleGlobalLayoutListener;
    public final AnonymousClass4 mOrientationHandleIntensityListener;
    public WindowManager.LayoutParams mOrientationParams;
    public Rect mOrientedHandleSamplingRegion;
    public Optional mOverrideHomeButtonLongPressDurationMs;
    public Optional mOverrideHomeButtonLongPressSlopMultiplier;
    public final AnonymousClass3 mOverviewProxyListener;
    public final OverviewProxyService mOverviewProxyService;
    public final PanelExpansionInteractor mPanelExpansionInteractor;
    public final Optional mPipOptional;
    public final Optional mRecentsOptional;
    public final RegionSamplingHelper mRegionSamplingHelper;
    public final Rect mSamplingBounds;
    public final Bundle mSavedState;
    public boolean mScreenPinningActive;
    public final ShadeViewController mShadeViewController;
    public boolean mShowOrientedHandleForImmersiveMode;
    public int mStartingQuickSwitchRotation;
    public final StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public final StatusBarStateController mStatusBarStateController;
    public final SysUiState mSysUiFlagsContainer;
    public final TaskStackChangeListeners mTaskStackChangeListeners;
    public final AnonymousClass8 mTaskStackListener;
    public final Optional mTelecomManagerOptional;
    public final AnonymousClass12 mTouchHandler;
    public boolean mTransientShown;
    public boolean mTransientShownFromGestureOnSystemBar;
    public int mTransitionMode;
    public final UiEventLogger mUiEventLogger;
    public final UserTracker.Callback mUserChangedCallback;
    public final UserContextProvider mUserContextProvider;
    public final UserTracker mUserTracker;
    public final ViewCaptureAwareWindowManager mViewCaptureAwareWindowManager;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public final AnonymousClass7 mWakefulnessObserver;
    public final WindowManager mWindowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.navigationbar.views.NavigationBar$1, reason: invalid class name */
    public final class AnonymousClass1 implements AutoHideUiElement, RegionSamplingHelper.SamplingCallback {
        public /* synthetic */ AnonymousClass1() {
        }

        @Override // com.android.wm.shell.shared.handles.RegionSamplingHelper.SamplingCallback
        public Rect getSampledRegion() {
            NavigationBar navigationBar = NavigationBar.this;
            Rect rect = navigationBar.mOrientedHandleSamplingRegion;
            if (rect != null) {
                return rect;
            }
            navigationBar.mSamplingBounds.setEmpty();
            View view = ((ButtonDispatcher) ((NavigationBarView) navigationBar.mView).mButtonDispatchers.get(R.id.home_handle)).mCurrentView;
            if (view != null) {
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                Point point = new Point();
                view.getContext().getDisplay().getRealSize(point);
                int i = iArr[0];
                int i2 = navigationBar.mNavColorSampleMargin;
                int i3 = i - i2;
                int i4 = point.y;
                NavigationBarView navigationBarView = (NavigationBarView) navigationBar.mView;
                navigationBar.mSamplingBounds.set(new Rect(i3, i4 - (navigationBarView.mIsVertical ? navigationBarView.getResources().getDimensionPixelSize(android.R.dimen.notification_action_disabled_content_alpha) : navigationBarView.getResources().getDimensionPixelSize(android.R.dimen.notification_action_disabled_alpha)), view.getWidth() + iArr[0] + i2, point.y));
            }
            return navigationBar.mSamplingBounds;
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public void hide() {
            NavigationBar navigationBar = NavigationBar.this;
            if (navigationBar.mTransientShown) {
                navigationBar.mTransientShown = false;
                navigationBar.mTransientShownFromGestureOnSystemBar = false;
                navigationBar.mEdgeBackGestureHandler.mIsNavBarShownTransiently = false;
                int transitionMode = NavBarHelper.transitionMode(navigationBar.mAppearance, false);
                if (navigationBar.mTransitionMode != transitionMode) {
                    navigationBar.mTransitionMode = transitionMode;
                    navigationBar.checkNavBarModes();
                    AutoHideController autoHideController = navigationBar.mAutoHideController;
                    if (autoHideController != null) {
                        autoHideController.touchAutoHide();
                    }
                    LightBarController lightBarController = navigationBar.mLightBarController;
                    if (lightBarController != null) {
                        lightBarController.mHasLightNavigationBar = LightBarController.isLight(lightBarController.mAppearance, transitionMode, 16);
                    }
                }
            }
        }

        @Override // com.android.wm.shell.shared.handles.RegionSamplingHelper.SamplingCallback
        public boolean isSamplingEnabled() {
            NavigationBar navigationBar = NavigationBar.this;
            return Utils.isGesturalModeOnDefaultDisplay(navigationBar.mView.getContext(), navigationBar.mDisplayTracker, navigationBar.mNavBarMode);
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public boolean isVisible() {
            return NavigationBar.this.mTransientShown;
        }

        @Override // com.android.wm.shell.shared.handles.RegionSamplingHelper.SamplingCallback
        public void onRegionDarknessChanged(boolean z) {
            NavigationBar.this.mNavigationBarTransitions.mLightTransitionsController.setIconsDark(!z, true);
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public boolean shouldHideOnTouch() {
            return !NavigationBar.this.mNotificationRemoteInputManager.isRemoteInputActive();
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public void synchronizeState() {
            NavigationBar.this.checkNavBarModes();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.navigationbar.views.NavigationBar$12, reason: invalid class name */
    public final class AnonymousClass12 implements Gefingerpoken {
        public boolean mDeadZoneConsuming;

        public AnonymousClass12() {
        }

        @Override // com.android.systemui.Gefingerpoken
        public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            NavigationBar navigationBar = NavigationBar.this;
            if (QuickStepContract.isGesturalMode(navigationBar.mNavBarMode) && navigationBar.mImeVisible && motionEvent.getAction() == 0) {
                SysUiStatsLog.write(304, (int) motionEvent.getX(), (int) motionEvent.getY());
            }
            return shouldDeadZoneConsumeTouchEvents(motionEvent);
        }

        @Override // com.android.systemui.Gefingerpoken
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            shouldDeadZoneConsumeTouchEvents(motionEvent);
            return false;
        }

        public final boolean shouldDeadZoneConsumeTouchEvents(MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                this.mDeadZoneConsuming = false;
            }
            NavigationBar navigationBar = NavigationBar.this;
            if (!navigationBar.mDeadZone.onTouchEvent(motionEvent) && !this.mDeadZoneConsuming) {
                return false;
            }
            if (actionMasked == 0) {
                ((NavigationBarView) navigationBar.mView).setSlippery(true);
                this.mDeadZoneConsuming = true;
            } else if (actionMasked == 1 || actionMasked == 3) {
                ((NavigationBarView) navigationBar.mView).updateSlippery();
                this.mDeadZoneConsuming = false;
            }
            return true;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.navigationbar.views.NavigationBar$6, reason: invalid class name */
    public final class AnonymousClass6 {
        public boolean mHasBlurs;

        public AnonymousClass6() {
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NavBarActionEvent implements UiEventLogger.UiEventEnum {
        public static final /* synthetic */ NavBarActionEvent[] $VALUES;
        public static final NavBarActionEvent NAVBAR_ASSIST_LONGPRESS;
        private final int mId = 550;

        static {
            NavBarActionEvent navBarActionEvent = new NavBarActionEvent();
            NAVBAR_ASSIST_LONGPRESS = navBarActionEvent;
            $VALUES = new NavBarActionEvent[]{navBarActionEvent};
        }

        public static NavBarActionEvent valueOf(String str) {
            return (NavBarActionEvent) Enum.valueOf(NavBarActionEvent.class, str);
        }

        public static NavBarActionEvent[] values() {
            return (NavBarActionEvent[]) $VALUES.clone();
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.android.systemui.navigationbar.views.NavigationBar$8] */
    /* JADX WARN: Type inference failed for: r6v3, types: [com.android.systemui.navigationbar.NavigationModeController$ModeChangedListener, com.android.systemui.navigationbar.views.NavigationBar$11] */
    /* JADX WARN: Type inference failed for: r7v5, types: [com.android.systemui.navigationbar.views.NavigationBar$5] */
    /* JADX WARN: Type inference failed for: r7v51, types: [com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda25] */
    /* JADX WARN: Type inference failed for: r7v7, types: [com.android.systemui.navigationbar.views.NavigationBar$7] */
    /* JADX WARN: Type inference failed for: r8v4, types: [com.android.systemui.navigationbar.views.NavigationBar$2] */
    /* JADX WARN: Type inference failed for: r8v5, types: [com.android.systemui.navigationbar.views.NavigationBar$3] */
    /* JADX WARN: Type inference failed for: r8v6, types: [com.android.systemui.navigationbar.views.NavigationBar$4] */
    public NavigationBar(NavigationBarView navigationBarView, NavigationBarFrame navigationBarFrame, Bundle bundle, Context context, WindowManager windowManager, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager, Lazy lazy, AccessibilityManager accessibilityManager, DeviceProvisionedController deviceProvisionedController, MetricsLogger metricsLogger, OverviewProxyService overviewProxyService, NavigationModeController navigationModeController, StatusBarStateController statusBarStateController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, SysUiState sysUiState, UserTracker userTracker, CommandQueue commandQueue, Optional optional, Optional optional2, Lazy lazy2, KeyguardStateController keyguardStateController, ShadeViewController shadeViewController, PanelExpansionInteractor panelExpansionInteractor, NotificationRemoteInputManager notificationRemoteInputManager, NotificationShadeDepthController notificationShadeDepthController, Handler handler, Executor executor, Executor executor2, UiEventLogger uiEventLogger, NavBarHelper navBarHelper, LightBarController lightBarController, LightBarController.Factory factory, AutoHideController autoHideController, AutoHideController.Factory factory2, Optional optional3, InputMethodManager inputMethodManager, DeadZone deadZone, DeviceConfigProxy deviceConfigProxy, NavigationBarTransitions navigationBarTransitions, Optional optional4, UserContextProvider userContextProvider, WakefulnessLifecycle wakefulnessLifecycle, TaskStackChangeListeners taskStackChangeListeners, DisplayTracker displayTracker, NavBarButtonClickLogger navBarButtonClickLogger, NavbarOrientationTrackingLogger navbarOrientationTrackingLogger) {
        super(navigationBarView);
        this.mNavigationBarWindowState = 0;
        this.mNavigationIconHints = 0;
        this.mOverrideHomeButtonLongPressDurationMs = Optional.empty();
        this.mOverrideHomeButtonLongPressSlopMultiplier = Optional.empty();
        this.mHomeButtonLongPressHapticEnabled = true;
        this.mNavBarMode = 0;
        this.mStartingQuickSwitchRotation = -1;
        this.mSamplingBounds = new Rect();
        this.mInsetsSourceOwner = new Binder();
        this.mAutoHideUiElement = new AnonymousClass1();
        this.mNavbarTaskbarStateUpdater = new NavBarHelper.NavbarTaskbarStateUpdater() { // from class: com.android.systemui.navigationbar.views.NavigationBar.2
            @Override // com.android.systemui.navigationbar.NavBarHelper.NavbarTaskbarStateUpdater
            public final void updateAccessibilityServicesState() {
                NavigationBar.this.updateAccessibilityStateFlags();
            }

            @Override // com.android.systemui.navigationbar.NavBarHelper.NavbarTaskbarStateUpdater
            public final void updateAssistantAvailable(boolean z, boolean z2) {
                NavigationBar navigationBar = NavigationBar.this;
                if (navigationBar.mView == null) {
                    return;
                }
                navigationBar.mLongPressHomeEnabled = z2;
                IOverviewProxy iOverviewProxy = navigationBar.mOverviewProxyService.mOverviewProxy;
                if (iOverviewProxy != null) {
                    try {
                        ((IOverviewProxy.Stub.Proxy) iOverviewProxy).onAssistantAvailable(z, z2);
                    } catch (RemoteException unused) {
                        Log.w("NavigationBar", "Unable to send assistant availability data to launcher");
                    }
                }
                navigationBar.reconfigureHomeLongClick();
            }

            @Override // com.android.systemui.navigationbar.NavBarHelper.NavbarTaskbarStateUpdater
            public final void updateRotationWatcherState(int i, Boolean bool) {
                View view;
                NavigationBar navigationBar = NavigationBar.this;
                if (!navigationBar.mIsOnDefaultDisplay || (view = navigationBar.mView) == null) {
                    return;
                }
                ((NavigationBarView) view).mRotationButtonController.onRotationWatcherChanged(i, bool);
                if (((NavigationBarView) navigationBar.mView).mCurrentRotation != i) {
                    navigationBar.repositionNavigationBar(i);
                }
            }

            @Override // com.android.systemui.navigationbar.NavBarHelper.NavbarTaskbarStateUpdater
            public final void updateWallpaperVisibility(int i, boolean z) {
                NavigationBarTransitions navigationBarTransitions2 = NavigationBar.this.mNavigationBarTransitions;
                navigationBarTransitions2.mWallpaperVisible = z;
                navigationBarTransitions2.applyLightsOut(true, false);
            }
        };
        this.mOverviewProxyListener = new OverviewProxyService.OverviewProxyListener() { // from class: com.android.systemui.navigationbar.views.NavigationBar.3
            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void animateNavBarLongPress(boolean z, boolean z2, long j) {
                ButtonDispatcher buttonDispatcher = (ButtonDispatcher) ((NavigationBarView) NavigationBar.this.mView).mButtonDispatchers.get(R.id.home_handle);
                for (int i = 0; i < buttonDispatcher.mViews.size(); i++) {
                    if (buttonDispatcher.mViews.get(i) instanceof ButtonInterface) {
                        ((ButtonInterface) buttonDispatcher.mViews.get(i)).animateLongPress(z, z2, j);
                    }
                }
            }

            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void onConnectionChanged(boolean z) {
                NavigationBar navigationBar = NavigationBar.this;
                NavigationBarView navigationBarView2 = (NavigationBarView) navigationBar.mView;
                OverviewProxyService overviewProxyService2 = navigationBar.mOverviewProxyService;
                navigationBarView2.mOverviewProxyEnabled = overviewProxyService2.mIsEnabled;
                navigationBarView2.mShowSwipeUpUi = overviewProxyService2.shouldShowSwipeUpUI();
                navigationBarView2.updateStates();
                navigationBar.updateScreenPinningGestures();
            }

            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void onHomeRotationEnabled(boolean z) {
                RotationButtonController rotationButtonController = ((NavigationBarView) NavigationBar.this.mView).mRotationButtonController;
                rotationButtonController.mHomeRotationEnabled = z;
                if (!rotationButtonController.mIsRecentsAnimationRunning || z) {
                    return;
                }
                rotationButtonController.setRotateSuggestionButtonState(false, true);
            }

            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void onOverviewShown() {
                ((NavigationBarView) NavigationBar.this.mView).mRotationButtonController.mSkipOverrideUserLockPrefsOnce = !r1.mIsRecentsAnimationRunning;
            }

            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void onPrioritizedRotation(int i) {
                NavigationBar navigationBar = NavigationBar.this;
                navigationBar.mStartingQuickSwitchRotation = i;
                if (i == -1) {
                    navigationBar.mShowOrientedHandleForImmersiveMode = false;
                }
                navigationBar.orientSecondaryHomeHandle();
            }

            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void onTaskbarStatusUpdated$1(boolean z, boolean z2) {
                FloatingRotationButton floatingRotationButton = ((NavigationBarView) NavigationBar.this.mView).mFloatingRotationButton;
                floatingRotationButton.mIsTaskbarVisible = z;
                floatingRotationButton.mIsTaskbarStashed = z2;
                if (floatingRotationButton.mIsShowing) {
                    FloatingRotationButtonPositionCalculator.Position calculatePosition = floatingRotationButton.mPositionCalculator.calculatePosition(floatingRotationButton.mDisplayRotation, z, z2);
                    FloatingRotationButtonPositionCalculator.Position position = floatingRotationButton.mPosition;
                    if (calculatePosition.translationX == position.translationX && calculatePosition.translationY == position.translationY) {
                        return;
                    }
                    floatingRotationButton.updateTranslation(calculatePosition, true);
                    floatingRotationButton.mPosition = calculatePosition;
                }
            }

            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void onToggleRecentApps() {
                ((NavigationBarView) NavigationBar.this.mView).mRotationButtonController.mSkipOverrideUserLockPrefsOnce = !r1.mIsRecentsAnimationRunning;
            }

            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void setAssistantOverridesRequested(int[] iArr) {
                AssistManagerGoogle assistManagerGoogle = (AssistManagerGoogle) NavigationBar.this.mAssistManagerLazy.get();
                assistManagerGoogle.mAssistOverrideInvocationTypes = iArr;
                assistManagerGoogle.mOpaEnabledReceiver.mAssistOverrideInvocationTypes = iArr;
            }

            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void setOverrideHomeButtonLongPress(float f, long j, boolean z) {
                Log.d("NavigationBar", "setOverrideHomeButtonLongPress receives: " + j + ";" + f + ";" + z);
                Optional filter = Optional.of(Long.valueOf(j)).filter(new NavigationBar$$ExternalSyntheticLambda3(1));
                NavigationBar navigationBar = NavigationBar.this;
                navigationBar.mOverrideHomeButtonLongPressDurationMs = filter;
                navigationBar.mOverrideHomeButtonLongPressSlopMultiplier = Optional.of(Float.valueOf(f)).filter(new NavigationBar$$ExternalSyntheticLambda3(2));
                navigationBar.mHomeButtonLongPressHapticEnabled = z;
                navigationBar.mOverrideHomeButtonLongPressDurationMs.ifPresent(new NavigationBar$$ExternalSyntheticLambda5(2));
                navigationBar.mOverrideHomeButtonLongPressSlopMultiplier.ifPresent(new NavigationBar$$ExternalSyntheticLambda5(3));
                if (navigationBar.mView != null) {
                    navigationBar.reconfigureHomeLongClick();
                }
            }

            @Override // com.android.systemui.recents.OverviewProxyService.OverviewProxyListener
            public final void startAssistant(Bundle bundle2) {
                ((AssistManagerGoogle) NavigationBar.this.mAssistManagerLazy.get()).startAssist(bundle2);
            }
        };
        this.mOrientationHandleIntensityListener = new NavigationBarTransitions.DarkIntensityListener() { // from class: com.android.systemui.navigationbar.views.NavigationBar.4
            @Override // com.android.systemui.navigationbar.views.NavigationBarTransitions.DarkIntensityListener
            public final void onDarkIntensity(float f) {
                NavigationBar.this.mOrientationHandle.setDarkIntensity(f);
            }
        };
        this.mAutoDim = new NavigationBar$$ExternalSyntheticLambda0(1, this);
        this.mEnableLayoutTransitions = new NavigationBar$$ExternalSyntheticLambda0(2, this);
        this.mOnVariableDurationHomeLongClick = new NavigationBar$$ExternalSyntheticLambda0(3, this);
        this.mOnPropertiesChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: com.android.systemui.navigationbar.views.NavigationBar.5
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                if (properties.getKeyset().contains("home_button_long_press_duration_ms")) {
                    NavigationBar.this.mHomeButtonLongPressDurationMs = Optional.of(Long.valueOf(properties.getLong("home_button_long_press_duration_ms", 0L))).filter(new NavigationBar$$ExternalSyntheticLambda3(3));
                    NavigationBar navigationBar = NavigationBar.this;
                    if (navigationBar.mView != null) {
                        navigationBar.reconfigureHomeLongClick();
                    }
                }
            }
        };
        this.mDepthListener = new AnonymousClass6();
        this.mWakefulnessObserver = new WakefulnessLifecycle.Observer() { // from class: com.android.systemui.navigationbar.views.NavigationBar.7
            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onFinishedGoingToSleep$1() {
                NavigationBar navigationBar = NavigationBar.this;
                ((NavigationBarView) navigationBar.mView).updateNavButtonIcons();
                ((NavigationBarView) navigationBar.mView).mScreenOn = false;
                navigationBar.mRegionSamplingHelper.stop();
            }

            @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
            public final void onStartedWakingUp() {
                NavigationBar navigationBar = NavigationBar.this;
                ((NavigationBarView) navigationBar.mView).updateNavButtonIcons();
                ((NavigationBarView) navigationBar.mView).mScreenOn = true;
                if (Utils.isGesturalModeOnDefaultDisplay(navigationBar.mView.getContext(), navigationBar.mDisplayTracker, navigationBar.mNavBarMode)) {
                    navigationBar.mRegionSamplingHelper.start(navigationBar.mSamplingBounds);
                }
            }
        };
        this.mScreenPinningActive = false;
        this.mTaskStackListener = new TaskStackChangeListener() { // from class: com.android.systemui.navigationbar.views.NavigationBar.8
            @Override // com.android.systemui.shared.system.TaskStackChangeListener
            public final void onLockTaskModeChanged(int i) {
                boolean z = i == 2;
                NavigationBar navigationBar = NavigationBar.this;
                navigationBar.mScreenPinningActive = z;
                SysUiState sysUiState2 = navigationBar.mSysUiFlagsContainer;
                sysUiState2.setFlag(1L, z);
                sysUiState2.commitUpdate(navigationBar.mDisplayId);
                ((NavigationBarView) navigationBar.mView).mScreenPinningActive = navigationBar.mScreenPinningActive;
                navigationBar.updateScreenPinningGestures();
            }
        };
        this.mUserChangedCallback = new UserTracker.Callback() { // from class: com.android.systemui.navigationbar.views.NavigationBar.10
            @Override // com.android.systemui.settings.UserTracker.Callback
            public final void onUserChanged(int i, Context context2) {
                NavigationBar.this.updateAccessibilityStateFlags();
            }
        };
        ?? r6 = new NavigationModeController.ModeChangedListener() { // from class: com.android.systemui.navigationbar.views.NavigationBar.11
            @Override // com.android.systemui.navigationbar.NavigationModeController.ModeChangedListener
            public final void onNavigationModeChanged(int i) {
                NavigationBarTransitions navigationBarTransitions2;
                NavigationBar navigationBar = NavigationBar.this;
                navigationBar.mNavBarMode = i;
                if (!QuickStepContract.isGesturalMode(i) && (navigationBarTransitions2 = navigationBar.mNavigationBarTransitions) != null) {
                    BarTransitions.BarBackgroundDrawable barBackgroundDrawable = navigationBarTransitions2.mBarBackground;
                    barBackgroundDrawable.mOverrideAlpha = 1.0f;
                    barBackgroundDrawable.invalidateSelf();
                }
                navigationBar.setNavBarMode(i);
                navigationBar.repositionNavigationBar(navigationBar.mCurrentRotation);
                if (navigationBar.mNavBarMode != 2 || navigationBar.mOrientationHandle == null) {
                    navigationBar.resetSecondaryHandle();
                }
                NavigationBarView navigationBarView2 = (NavigationBarView) navigationBar.mView;
                navigationBarView2.mShowSwipeUpUi = navigationBar.mOverviewProxyService.shouldShowSwipeUpUI();
                navigationBarView2.updateStates();
            }
        };
        this.mModeChangedListener = r6;
        this.mTouchHandler = new AnonymousClass12();
        this.mFrame = navigationBarFrame;
        this.mContext = context;
        this.mSavedState = bundle;
        this.mWindowManager = windowManager;
        this.mViewCaptureAwareWindowManager = viewCaptureAwareWindowManager;
        this.mAccessibilityManager = accessibilityManager;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mStatusBarStateController = statusBarStateController;
        this.mMetricsLogger = metricsLogger;
        this.mAssistManagerLazy = lazy;
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.mSysUiFlagsContainer = sysUiState;
        this.mCentralSurfacesOptionalLazy = lazy2;
        this.mKeyguardStateController = keyguardStateController;
        this.mShadeViewController = shadeViewController;
        this.mPanelExpansionInteractor = panelExpansionInteractor;
        this.mNotificationRemoteInputManager = notificationRemoteInputManager;
        this.mOverviewProxyService = overviewProxyService;
        this.mNavigationModeController = navigationModeController;
        this.mUserTracker = userTracker;
        this.mCommandQueue = commandQueue;
        this.mPipOptional = optional;
        this.mRecentsOptional = optional2;
        this.mDeadZone = deadZone;
        this.mDeviceConfigProxy = deviceConfigProxy;
        this.mNavigationBarTransitions = navigationBarTransitions;
        this.mBackAnimation = optional4;
        this.mHandler = handler;
        this.mUiEventLogger = uiEventLogger;
        this.mNavBarHelper = navBarHelper;
        this.mNotificationShadeDepthController = notificationShadeDepthController;
        this.mMainLightBarController = lightBarController;
        this.mLightBarControllerFactory = factory;
        this.mMainAutoHideController = autoHideController;
        this.mAutoHideControllerFactory = factory2;
        this.mTelecomManagerOptional = optional3;
        this.mInputMethodManager = inputMethodManager;
        this.mUserContextProvider = userContextProvider;
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        this.mTaskStackChangeListeners = taskStackChangeListeners;
        this.mDisplayTracker = displayTracker;
        EdgeBackGestureHandler edgeBackGestureHandler = navBarHelper.mEdgeBackGestureHandler;
        this.mEdgeBackGestureHandler = edgeBackGestureHandler;
        this.mNavBarButtonClickLogger = navBarButtonClickLogger;
        this.mNavbarOrientationTrackingLogger = navbarOrientationTrackingLogger;
        this.mNavColorSampleMargin = navigationBarView.getResources().getDimensionPixelSize(R.dimen.navigation_handle_sample_horizontal_margin);
        this.mOnComputeInternalInsetsListener = new ViewTreeObserver.OnComputeInternalInsetsListener() { // from class: com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda25
            public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
                NavigationBar navigationBar = NavigationBar.this;
                if (!navigationBar.mEdgeBackGestureHandler.isHandlingGestures()) {
                    if (!navigationBar.mImeVisible) {
                        internalInsetsInfo.setTouchableInsets(0);
                        return;
                    } else if (!((NavigationBarView) navigationBar.mView).isImeRenderingNavButtons()) {
                        internalInsetsInfo.setTouchableInsets(0);
                        return;
                    }
                }
                internalInsetsInfo.setTouchableInsets(3);
                internalInsetsInfo.touchableRegion.set(navigationBar.getButtonLocations(false, false));
            }
        };
        this.mRegionSamplingHelper = new RegionSamplingHelper(navigationBarView, new AnonymousClass1(), executor, executor2);
        navigationBarView.mBgExecutor = executor2;
        navigationBarView.mRotationButtonController.mBgExecutor = executor2;
        navigationBarView.mEdgeBackGestureHandler = edgeBackGestureHandler;
        navigationBarView.mDisplayTracker = displayTracker;
        this.mNavBarMode = navigationModeController.addListener(r6);
    }

    public static void updateButtonLocation(Region region, Map map, ButtonDispatcher buttonDispatcher, boolean z, boolean z2) {
        View view;
        if (buttonDispatcher == null || (view = buttonDispatcher.mCurrentView) == null || buttonDispatcher.getVisibility() != 0) {
            return;
        }
        if (z2 && map.containsKey(view)) {
            region.op((Rect) map.get(view), Region.Op.UNION);
        } else {
            updateButtonLocation(region, view, z);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void abortTransient(int i, int i2) {
        if (i == this.mDisplayId && (WindowInsets.Type.navigationBars() & i2) != 0 && this.mTransientShown) {
            this.mTransientShown = false;
            this.mTransientShownFromGestureOnSystemBar = false;
            this.mEdgeBackGestureHandler.mIsNavBarShownTransiently = false;
            int transitionMode = NavBarHelper.transitionMode(this.mAppearance, false);
            if (this.mTransitionMode != transitionMode) {
                this.mTransitionMode = transitionMode;
                checkNavBarModes();
                AutoHideController autoHideController = this.mAutoHideController;
                if (autoHideController != null) {
                    autoHideController.touchAutoHide();
                }
                LightBarController lightBarController = this.mLightBarController;
                if (lightBarController != null) {
                    lightBarController.mHasLightNavigationBar = LightBarController.isLight(lightBarController.mAppearance, transitionMode, 16);
                }
            }
        }
    }

    public final void checkNavBarModes() {
        boolean z = ((Boolean) ((Optional) this.mCentralSurfacesOptionalLazy.get()).map(new NavigationBar$$ExternalSyntheticLambda13()).orElse(Boolean.FALSE)).booleanValue() && this.mNavigationBarWindowState != 2;
        int i = this.mTransitionMode;
        NavigationBarTransitions navigationBarTransitions = this.mNavigationBarTransitions;
        int i2 = navigationBarTransitions.mMode;
        if (i2 == i) {
            return;
        }
        navigationBarTransitions.mMode = i;
        navigationBarTransitions.onTransition(i2, i, z);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void disable(int i, int i2, int i3, boolean z) {
        int i4;
        if (i != this.mDisplayId) {
            return;
        }
        int i5 = 56623104 & i2;
        if (i5 != this.mDisabledFlags1) {
            this.mDisabledFlags1 = i5;
            ((NavigationBarView) this.mView).setDisabledFlags(i2, this.mSysUiFlagsContainer);
            updateScreenPinningGestures();
        }
        if (!this.mIsOnDefaultDisplay || (i4 = i3 & 16) == this.mDisabledFlags2) {
            return;
        }
        this.mDisabledFlags2 = i4;
        RotationButtonController rotationButtonController = ((NavigationBarView) this.mView).mRotationButtonController;
        boolean z2 = RotationButtonController.OEM_DISALLOW_ROTATION_IN_SUW;
        if ((i3 & 16) == 0) {
            rotationButtonController.getClass();
        } else {
            rotationButtonController.setRotateSuggestionButtonState(false, true);
            rotationButtonController.mMainThreadHandler.removeCallbacks(rotationButtonController.mRemoveRotationProposal);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x011a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.WindowManager.LayoutParams getBarLayoutParamsForRotation(int r20) {
        /*
            Method dump skipped, instructions count: 425
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.navigationbar.views.NavigationBar.getBarLayoutParamsForRotation(int):android.view.WindowManager$LayoutParams");
    }

    public final Region getButtonLocations(boolean z, boolean z2) {
        if (z2 && !z) {
            z2 = false;
        }
        Region region = new Region();
        NavigationBarView navigationBarView = (NavigationBarView) this.mView;
        NearestTouchFrame nearestTouchFrame = (NearestTouchFrame) (navigationBarView.mIsVertical ? navigationBarView.mNavigationInflaterView.mVertical : navigationBarView.mNavigationInflaterView.mHorizontal).findViewById(R.id.nav_buttons);
        nearestTouchFrame.getClass();
        HashMap hashMap = new HashMap(nearestTouchFrame.mTouchableRegions.size());
        nearestTouchFrame.getLocationOnScreen(nearestTouchFrame.mTmpInt);
        for (Map.Entry entry : ((HashMap) nearestTouchFrame.mTouchableRegions).entrySet()) {
            View view = (View) entry.getKey();
            Rect rect = new Rect((Rect) entry.getValue());
            int[] iArr = nearestTouchFrame.mTmpInt;
            rect.offset(iArr[0], iArr[1]);
            hashMap.put(view, rect);
        }
        updateButtonLocation(region, hashMap, ((NavigationBarView) this.mView).getBackButton(), z, z2);
        updateButtonLocation(region, hashMap, ((NavigationBarView) this.mView).getHomeButton(), z, z2);
        updateButtonLocation(region, hashMap, ((NavigationBarView) this.mView).getRecentsButton(), z, z2);
        updateButtonLocation(region, hashMap, (ButtonDispatcher) ((NavigationBarView) this.mView).mButtonDispatchers.get(R.id.ime_switcher), z, z2);
        updateButtonLocation(region, hashMap, (ButtonDispatcher) ((NavigationBarView) this.mView).mButtonDispatchers.get(R.id.accessibility_button), z, z2);
        FloatingRotationButton floatingRotationButton = ((NavigationBarView) this.mView).mFloatingRotationButton;
        if (floatingRotationButton.mIsShowing) {
            updateButtonLocation(region, floatingRotationButton.mKeyButtonView, z);
        }
        return region;
    }

    public int getNavigationIconHints() {
        return this.mNavigationIconHints;
    }

    public final void logNavbarOrientation(String str) {
        View view = this.mView;
        boolean z = view != null && ((NavigationBarView) view).getVisibility() == 0;
        QuickswitchOrientedNavHandle quickswitchOrientedNavHandle = this.mOrientationHandle;
        this.mNavbarOrientationTrackingLogger.logPrimaryAndSecondaryVisibility(str, z, this.mShowOrientedHandleForImmersiveMode, quickswitchOrientedNavHandle != null && quickswitchOrientedNavHandle.getVisibility() == 0, this.mCurrentRotation, this.mStartingQuickSwitchRotation);
    }

    /* JADX WARN: Type inference failed for: r2v5, types: [com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda4] */
    public boolean onHomeLongClick(View view) {
        int i = 0;
        if (((NavigationBarView) this.mView).getRecentsButton().getVisibility() != 0 && this.mScreenPinningActive) {
            return onLongPressNavigationButtons(view, R.id.home);
        }
        if (!((DeviceProvisionedControllerImpl) this.mDeviceProvisionedController).deviceProvisioned.get() || (this.mDisabledFlags1 & 33554432) != 0) {
            return false;
        }
        this.mMetricsLogger.action(239);
        this.mUiEventLogger.log(NavBarActionEvent.NAVBAR_ASSIST_LONGPRESS);
        final Bundle bundle = new Bundle();
        bundle.putInt("invocation_type", 5);
        Lazy lazy = this.mAssistManagerLazy;
        if (((AssistManagerGoogle) lazy.get()).shouldOverrideAssist(5) && (view instanceof KeyButtonView)) {
            ((KeyButtonView) view).mRipple.mOnInvisibleRunnable = new Runnable() { // from class: com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda4
                @Override // java.lang.Runnable
                public final void run() {
                    NavigationBar navigationBar = NavigationBar.this;
                    ((AssistManagerGoogle) navigationBar.mAssistManagerLazy.get()).startAssist(bundle);
                }
            };
        } else {
            ((AssistManagerGoogle) lazy.get()).startAssist(bundle);
        }
        ((Optional) this.mCentralSurfacesOptionalLazy.get()).ifPresent(new NavigationBar$$ExternalSyntheticLambda5(i));
        ((NavigationBarView) this.mView).abortCurrentGesture();
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0027, code lost:
    
        if (r1 != 3) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onHomeTouch(android.view.View r7, android.view.MotionEvent r8) {
        /*
            Method dump skipped, instructions count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.navigationbar.views.NavigationBar.onHomeTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void onImeSwitcherClick(View view) {
        this.mNavBarButtonClickLogger.logImeSwitcherClick();
        this.mInputMethodManager.onImeSwitchButtonClickFromSystem(this.mDisplayId);
        this.mUiEventLogger.log(KeyButtonView.NavBarButtonEvent.NAVBAR_IME_SWITCHER_BUTTON_TAP);
    }

    public boolean onImeSwitcherLongClick(View view) {
        this.mNavBarButtonClickLogger.logImeSwitcherClick();
        this.mInputMethodManager.showInputMethodPickerFromSystem(true, this.mDisplayId);
        this.mUiEventLogger.log(KeyButtonView.NavBarButtonEvent.NAVBAR_IME_SWITCHER_BUTTON_LONGPRESS);
        return true;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        NavigationBarView navigationBarView = (NavigationBarView) this.mView;
        NavigationBarTransitions navigationBarTransitions = this.mNavigationBarTransitions;
        navigationBarView.mBarTransitions = navigationBarTransitions;
        navigationBarView.mTouchHandler = this.mTouchHandler;
        setNavBarMode(this.mNavBarMode);
        NavigationBarView navigationBarView2 = (NavigationBarView) this.mView;
        Objects.requireNonNull(navigationBarView2);
        NavigationBar$$ExternalSyntheticLambda0 navigationBar$$ExternalSyntheticLambda0 = new NavigationBar$$ExternalSyntheticLambda0(0, navigationBarView2);
        EdgeBackGestureHandler edgeBackGestureHandler = this.mEdgeBackGestureHandler;
        edgeBackGestureHandler.mStateChangeCallback = navigationBar$$ExternalSyntheticLambda0;
        edgeBackGestureHandler.mButtonForcedVisibleCallback = new NavigationBar$$ExternalSyntheticLambda1(this, 0);
        navigationBarTransitions.mListeners.add(new NavigationBar$$ExternalSyntheticLambda2(this));
        ((NavigationBarView) this.mView).updateRotationButton();
        ((NavigationBarView) this.mView).setVisibility(this.mStatusBarKeyguardViewManager.isNavBarVisible() ? 0 : 4);
        NavigationBarFrame navigationBarFrame = this.mFrame;
        WindowManager.LayoutParams barLayoutParamsForRotation = getBarLayoutParamsForRotation(this.mContext.getResources().getConfiguration().windowConfiguration.getRotation());
        barLayoutParamsForRotation.paramsForRotation = new WindowManager.LayoutParams[4];
        for (int i = 0; i <= 3; i++) {
            barLayoutParamsForRotation.paramsForRotation[i] = getBarLayoutParamsForRotation(i);
        }
        this.mViewCaptureAwareWindowManager.addView(navigationBarFrame, barLayoutParamsForRotation);
        int displayId = this.mContext.getDisplayId();
        this.mDisplayId = displayId;
        this.mDisplayTracker.getClass();
        this.mIsOnDefaultDisplay = displayId == 0;
        NavBarHelper navBarHelper = this.mNavBarHelper;
        navBarHelper.getClass();
        int i2 = navBarHelper.mWindowStateDisplayId;
        int i3 = navBarHelper.mWindowState;
        if (i2 == this.mDisplayId) {
            this.mNavigationBarWindowState = i3;
        }
        CommandQueue commandQueue = this.mCommandQueue;
        commandQueue.addCallback((CommandQueue.Callbacks) this);
        this.mDeviceConfigProxy.getClass();
        this.mHomeButtonLongPressDurationMs = Optional.of(Long.valueOf(DeviceConfig.getLong("systemui", "home_button_long_press_duration_ms", 0L))).filter(new NavigationBar$$ExternalSyntheticLambda3(0));
        navBarHelper.registerNavTaskStateUpdater(this.mNavbarTaskbarStateUpdater);
        Handler handler = this.mHandler;
        Objects.requireNonNull(handler);
        DeviceConfig.addOnPropertiesChangedListener("systemui", new ConcurrencyHelpers$$ExternalSyntheticLambda0(handler), this.mOnPropertiesChangedListener);
        Bundle bundle = this.mSavedState;
        if (bundle != null) {
            this.mDisabledFlags1 = bundle.getInt("disabled_state", 0);
            this.mDisabledFlags2 = this.mSavedState.getInt("disabled2_state", 0);
            this.mAppearance = this.mSavedState.getInt("appearance", 0);
            this.mBehavior = this.mSavedState.getInt("behavior", 0);
            this.mTransientShown = this.mSavedState.getBoolean("transient_state", false);
        }
        commandQueue.recomputeDisableFlags(this.mDisplayId, false);
        this.mNotificationShadeDepthController.listeners.add(this.mDepthListener);
        this.mTaskStackChangeListeners.registerTaskStackListener(this.mTaskStackListener);
    }

    public final boolean onLongPressNavigationButtons(View view, int i) {
        boolean z;
        try {
            IActivityTaskManager service = ActivityTaskManager.getService();
            boolean isTouchExplorationEnabled = this.mAccessibilityManager.isTouchExplorationEnabled();
            boolean isInLockTaskMode = service.isInLockTaskMode();
            try {
                if (isInLockTaskMode && !isTouchExplorationEnabled) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - this.mLastLockToAppLongPress < 200) {
                        service.stopSystemLockTaskMode();
                        ((NavigationBarView) this.mView).updateNavButtonIcons();
                        return true;
                    }
                    if (view.getId() == R.id.back) {
                        if (!(i == R.id.recent_apps ? ((NavigationBarView) this.mView).getRecentsButton() : ((NavigationBarView) this.mView).getHomeButton()).mCurrentView.isPressed()) {
                            z = true;
                            this.mLastLockToAppLongPress = currentTimeMillis;
                        }
                    }
                    z = false;
                    this.mLastLockToAppLongPress = currentTimeMillis;
                } else if (view.getId() == R.id.back) {
                    z = true;
                } else {
                    if (isTouchExplorationEnabled && isInLockTaskMode) {
                        service.stopSystemLockTaskMode();
                        ((NavigationBarView) this.mView).updateNavButtonIcons();
                        return true;
                    }
                    if (view.getId() == i) {
                        if (i == R.id.recent_apps) {
                            return false;
                        }
                        return onHomeLongClick(((NavigationBarView) this.mView).getHomeButton().mCurrentView);
                    }
                    z = false;
                }
                if (z) {
                    KeyButtonView keyButtonView = (KeyButtonView) view;
                    keyButtonView.sendEvent(0, 128);
                    keyButtonView.sendAccessibilityEvent(2);
                    return true;
                }
            } finally {
            }
        } catch (RemoteException e) {
            Log.d("NavigationBar", "Unable to reach activity manager", e);
        }
        return false;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void onRecentsAnimationStateChanged(boolean z) {
        RotationButtonController rotationButtonController = ((NavigationBarView) this.mView).mRotationButtonController;
        rotationButtonController.mIsRecentsAnimationRunning = z;
        if (!z || rotationButtonController.mHomeRotationEnabled) {
            return;
        }
        rotationButtonController.setRotateSuggestionButtonState(false, true);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void onRotationProposal(int i, boolean z) {
        if (((NavigationBarView) this.mView).isAttachedToWindow()) {
            int i2 = this.mDisabledFlags2;
            boolean z2 = RotationButtonController.OEM_DISALLOW_ROTATION_IN_SUW;
            boolean z3 = false;
            boolean z4 = (i2 & 16) != 0;
            RotationButtonController rotationButtonController = ((NavigationBarView) this.mView).mRotationButtonController;
            if (z4) {
                return;
            }
            if (Settings.Secure.getInt(rotationButtonController.mContext.getContentResolver(), "user_setup_complete", 0) == 0 && RotationButtonController.OEM_DISALLOW_ROTATION_IN_SUW) {
                return;
            }
            int intValue = ((Integer) rotationButtonController.mWindowRotationProvider.get()).intValue();
            if (rotationButtonController.mRotationButton.mKeyButtonView != null) {
                if (rotationButtonController.mHomeRotationEnabled || !rotationButtonController.mIsRecentsAnimationRunning) {
                    if (!z) {
                        rotationButtonController.setRotateSuggestionButtonState(false, false);
                        return;
                    }
                    Handler handler = rotationButtonController.mMainThreadHandler;
                    if (i == intValue) {
                        handler.removeCallbacks(rotationButtonController.mRemoveRotationProposal);
                        rotationButtonController.setRotateSuggestionButtonState(false, false);
                        return;
                    }
                    Log.i("RotationButtonController", "onRotationProposal(rotation=" + i + ")");
                    rotationButtonController.mLastRotationSuggestion = i;
                    if ((intValue != 0 || i != 1) && ((intValue == 0 && i == 2) || ((intValue == 0 && i == 3) || ((intValue == 1 && i == 0) || ((intValue != 1 || i != 2) && ((intValue == 1 && i == 3) || ((intValue == 2 && i == 0) || ((intValue == 2 && i == 1) || ((intValue != 2 || i != 3) && ((intValue != 3 || i != 0) && ((intValue == 3 && i == 1) || (intValue == 3 && i == 2)))))))))))) {
                        z3 = true;
                    }
                    if (intValue == 0 || intValue == 2) {
                        rotationButtonController.mIconResId = z3 ? rotationButtonController.mIconCcwStart0ResId : rotationButtonController.mIconCwStart0ResId;
                    } else {
                        rotationButtonController.mIconResId = z3 ? rotationButtonController.mIconCcwStart90ResId : rotationButtonController.mIconCwStart90ResId;
                    }
                    rotationButtonController.mRotationButton.updateIcon(rotationButtonController.mLightIconColor, rotationButtonController.mDarkIconColor);
                    if (rotationButtonController.canShowRotationButton()) {
                        rotationButtonController.showAndLogRotationSuggestion();
                        return;
                    }
                    rotationButtonController.mPendingRotationSuggestion = true;
                    RotationButtonController$$ExternalSyntheticLambda0 rotationButtonController$$ExternalSyntheticLambda0 = rotationButtonController.mCancelPendingRotationProposal;
                    handler.removeCallbacks(rotationButtonController$$ExternalSyntheticLambda0);
                    handler.postDelayed(rotationButtonController$$ExternalSyntheticLambda0, 20000L);
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void onSystemBarAttributesChanged(int i, int i2, AppearanceRegion[] appearanceRegionArr, boolean z, int i3, int i4, String str, LetterboxDetails[] letterboxDetailsArr) {
        if (i != this.mDisplayId) {
            return;
        }
        boolean z2 = false;
        if (this.mAppearance != i2) {
            this.mAppearance = i2;
            int transitionMode = NavBarHelper.transitionMode(i2, this.mTransientShown);
            if (this.mTransitionMode != transitionMode) {
                this.mTransitionMode = transitionMode;
                checkNavBarModes();
                AutoHideController autoHideController = this.mAutoHideController;
                if (autoHideController != null) {
                    autoHideController.touchAutoHide();
                }
                z2 = true;
            }
        }
        LightBarController lightBarController = this.mLightBarController;
        if (lightBarController != null) {
            lightBarController.onNavigationBarAppearanceChanged(i2, this.mTransitionMode, z2, z);
        }
        if (this.mBehavior != i3) {
            this.mBehavior = i3;
            NavigationBarView navigationBarView = (NavigationBarView) this.mView;
            RotationButtonController rotationButtonController = navigationBarView.mRotationButtonController;
            navigationBarView.mDisplayTracker.getClass();
            if (rotationButtonController.mBehavior != i3) {
                rotationButtonController.mBehavior = i3;
                if (rotationButtonController.canShowRotationButton() && rotationButtonController.mPendingRotationSuggestion) {
                    rotationButtonController.showAndLogRotationSuggestion();
                }
            }
            updateSystemUiStateFlags();
        }
    }

    /* JADX WARN: Type inference failed for: r2v28, types: [com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda28] */
    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        LightBarController lightBarController;
        AutoHideController autoHideController;
        int i = 0;
        int i2 = 1;
        Display display = ((NavigationBarView) this.mView).getDisplay();
        ((NavigationBarView) this.mView).mRecentsOptional = this.mRecentsOptional;
        Lazy lazy = this.mCentralSurfacesOptionalLazy;
        boolean isPresent = ((Optional) lazy.get()).isPresent();
        ShadeViewController shadeViewController = this.mShadeViewController;
        if (isPresent) {
            NavigationBarView navigationBarView = (NavigationBarView) this.mView;
            navigationBarView.getClass();
            navigationBarView.mPanelExpansionInteractor = this.mPanelExpansionInteractor;
            if (shadeViewController != null) {
                shadeViewController.updateSystemUiStateFlags();
            }
        }
        ((NavigationBarView) this.mView).setDisabledFlags(this.mDisabledFlags1, this.mSysUiFlagsContainer);
        NavigationBarView navigationBarView2 = (NavigationBarView) this.mView;
        navigationBarView2.mOnVerticalChangedListener = new NavigationBar$$ExternalSyntheticLambda17(this);
        boolean z = navigationBarView2.mIsVertical;
        if (((Optional) lazy.get()).isPresent()) {
            shadeViewController.setQsScrimEnabled(!z);
        }
        ((NavigationBarView) this.mView).setOnTouchListener(new NavigationBar$$ExternalSyntheticLambda7(this, i2));
        Bundle bundle = this.mSavedState;
        NavigationBarTransitions navigationBarTransitions = this.mNavigationBarTransitions;
        if (bundle != null) {
            LightBarTransitionsController lightBarTransitionsController = navigationBarTransitions.mLightTransitionsController;
            lightBarTransitionsController.getClass();
            float f = bundle.getFloat("dark_intensity", 0.0f);
            lightBarTransitionsController.mDarkIntensity = f;
            lightBarTransitionsController.mApplier.applyDarkIntensity(MathUtils.lerp(f, 0.0f, lightBarTransitionsController.mDozeAmount));
            lightBarTransitionsController.mNextDarkIntensity = lightBarTransitionsController.mDarkIntensity;
        }
        setNavigationIconHints(this.mNavigationIconHints);
        boolean z2 = this.mNavigationBarWindowState == 0;
        RegionSamplingHelper regionSamplingHelper = this.mRegionSamplingHelper;
        regionSamplingHelper.mWindowVisible = z2;
        regionSamplingHelper.updateSamplingListener();
        RotationButtonController rotationButtonController = ((NavigationBarView) this.mView).mRotationButtonController;
        if (rotationButtonController.mIsNavigationBarShowing != z2) {
            rotationButtonController.mIsNavigationBarShowing = z2;
            if (rotationButtonController.canShowRotationButton() && rotationButtonController.mPendingRotationSuggestion) {
                rotationButtonController.showAndLogRotationSuggestion();
            }
        }
        NavigationBarView navigationBarView3 = (NavigationBarView) this.mView;
        int i3 = this.mBehavior;
        RotationButtonController rotationButtonController2 = navigationBarView3.mRotationButtonController;
        navigationBarView3.mDisplayTracker.getClass();
        if (rotationButtonController2.mBehavior != i3) {
            rotationButtonController2.mBehavior = i3;
            if (rotationButtonController2.canShowRotationButton() && rotationButtonController2.mPendingRotationSuggestion) {
                rotationButtonController2.showAndLogRotationSuggestion();
            }
        }
        setNavBarMode(this.mNavBarMode);
        repositionNavigationBar(this.mCurrentRotation);
        NavigationBarView navigationBarView4 = (NavigationBarView) this.mView;
        navigationBarView4.mUpdateActiveTouchRegionsCallback = new NavigationBar$$ExternalSyntheticLambda17(this);
        navigationBarView4.notifyActiveTouchRegions();
        ((NavigationBarView) this.mView).getViewTreeObserver().addOnComputeInternalInsetsListener(this.mOnComputeInternalInsetsListener);
        Optional optional = this.mPipOptional;
        NavigationBarView navigationBarView5 = (NavigationBarView) this.mView;
        Objects.requireNonNull(navigationBarView5);
        optional.ifPresent(new NavigationBar$$ExternalSyntheticLambda20(navigationBarView5, i));
        Optional optional2 = this.mBackAnimation;
        NavigationBarView navigationBarView6 = (NavigationBarView) this.mView;
        Objects.requireNonNull(navigationBarView6);
        optional2.ifPresent(new NavigationBar$$ExternalSyntheticLambda20(navigationBarView6, i2));
        prepareNavigationBarView();
        checkNavBarModes();
        ((UserTrackerImpl) this.mUserTracker).addCallback(this.mUserChangedCallback, this.mContext.getMainExecutor());
        this.mWakefulnessLifecycle.addObserver(this.mWakefulnessObserver);
        ((NavigationBarView) this.mView).updateNavButtonIcons();
        this.mOverviewProxyService.addCallback((OverviewProxyService.OverviewProxyListener) this.mOverviewProxyListener);
        updateSystemUiStateFlags();
        if (this.mIsOnDefaultDisplay) {
            RotationButtonController rotationButtonController3 = ((NavigationBarView) this.mView).mRotationButtonController;
            Boolean isRotationLocked = RotationPolicyUtil.isRotationLocked(this.mContext);
            if (display != null && isRotationLocked.booleanValue()) {
                RotationPolicy.setRotationLockAtAngle(rotationButtonController3.mContext, isRotationLocked.booleanValue(), display.getRotation(), "NavigationBar#onViewAttached");
            }
        } else {
            this.mDisabledFlags2 |= 16;
        }
        int i4 = this.mDisabledFlags2;
        RotationButtonController rotationButtonController4 = ((NavigationBarView) this.mView).mRotationButtonController;
        boolean z3 = RotationButtonController.OEM_DISALLOW_ROTATION_IN_SUW;
        if ((i4 & 16) != 0) {
            rotationButtonController4.setRotateSuggestionButtonState(false, true);
            rotationButtonController4.mMainThreadHandler.removeCallbacks(rotationButtonController4.mRemoveRotationProposal);
        } else {
            rotationButtonController4.getClass();
        }
        if (this.mNavBarMode == 2) {
            QuickswitchOrientedNavHandle quickswitchOrientedNavHandle = new QuickswitchOrientedNavHandle(this.mContext);
            this.mOrientationHandle = quickswitchOrientedNavHandle;
            quickswitchOrientedNavHandle.setId(R.id.secondary_home_handle);
            navigationBarTransitions.mDarkIntensityListeners.add(this.mOrientationHandleIntensityListener);
            float f2 = navigationBarTransitions.mLightTransitionsController.mDarkIntensity;
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(0, 0, 2024, 536871224, -3);
            this.mOrientationParams = layoutParams;
            layoutParams.setTitle("SecondaryHomeHandle" + this.mContext.getDisplayId());
            WindowManager.LayoutParams layoutParams2 = this.mOrientationParams;
            layoutParams2.privateFlags = layoutParams2.privateFlags | 4160;
            this.mViewCaptureAwareWindowManager.addView(this.mOrientationHandle, layoutParams2);
            this.mOrientationHandle.setVisibility(8);
            logNavbarOrientation("initSecondaryHomeHandleForRotation");
            this.mOrientationParams.setFitInsetsTypes(0);
            this.mOrientationHandleGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda28
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    NavigationBar navigationBar = NavigationBar.this;
                    if (navigationBar.mStartingQuickSwitchRotation == -1) {
                        return;
                    }
                    RectF computeHomeHandleBounds = navigationBar.mOrientationHandle.computeHomeHandleBounds();
                    navigationBar.mOrientationHandle.mapRectFromViewToScreenCoords(computeHomeHandleBounds, true);
                    Rect rect = new Rect();
                    computeHomeHandleBounds.roundOut(rect);
                    navigationBar.mOrientedHandleSamplingRegion = rect;
                    RegionSamplingHelper regionSamplingHelper2 = navigationBar.mRegionSamplingHelper;
                    Rect sampledRegion = regionSamplingHelper2.mCallback.getSampledRegion();
                    if (regionSamplingHelper2.mSamplingRequestBounds.equals(sampledRegion)) {
                        return;
                    }
                    regionSamplingHelper2.mSamplingRequestBounds.set(sampledRegion);
                    regionSamplingHelper2.updateSamplingListener();
                }
            };
            this.mOrientationHandle.getViewTreeObserver().addOnGlobalLayoutListener(this.mOrientationHandleGlobalLayoutListener);
        }
        if (this.mIsOnDefaultDisplay) {
            lightBarController = this.mMainLightBarController;
        } else {
            Context context = this.mContext;
            LightBarController.Factory factory = this.mLightBarControllerFactory;
            lightBarController = new LightBarController(context, factory.mJavaAdapter, factory.mDarkIconDispatcher, factory.mBatteryController, factory.mNavModeController, factory.mStatusBarModeRepository, factory.mDumpManager, factory.mDisplayTracker);
        }
        this.mLightBarController = lightBarController;
        if (lightBarController != null) {
            lightBarController.mNavigationBarController = navigationBarTransitions.mLightTransitionsController;
            lightBarController.updateNavigation();
        }
        if (this.mIsOnDefaultDisplay) {
            autoHideController = this.mMainAutoHideController;
        } else {
            Context context2 = this.mContext;
            AutoHideController.Factory factory2 = this.mAutoHideControllerFactory;
            autoHideController = new AutoHideController(context2, factory2.mHandler, factory2.mIWindowManager);
        }
        this.mAutoHideController = autoHideController;
        if (autoHideController != null) {
            autoHideController.mNavigationBar = this.mAutoHideUiElement;
        }
        ((NavigationBarView) this.mView).mAutoHideController = autoHideController;
        int transitionMode = NavBarHelper.transitionMode(this.mAppearance, this.mTransientShown);
        this.mTransitionMode = transitionMode;
        checkNavBarModes();
        AutoHideController autoHideController2 = this.mAutoHideController;
        if (autoHideController2 != null) {
            autoHideController2.touchAutoHide();
        }
        LightBarController lightBarController2 = this.mLightBarController;
        if (lightBarController2 != null) {
            lightBarController2.onNavigationBarAppearanceChanged(this.mAppearance, transitionMode, true, false);
        }
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        NavigationBarView navigationBarView = (NavigationBarView) this.mView;
        navigationBarView.mUpdateActiveTouchRegionsCallback = null;
        navigationBarView.notifyActiveTouchRegions();
        NavigationBarTransitions navigationBarTransitions = this.mNavigationBarTransitions;
        LightBarTransitionsController lightBarTransitionsController = navigationBarTransitions.mLightTransitionsController;
        CommandQueue commandQueue = lightBarTransitionsController.mCommandQueue;
        LightBarTransitionsController.Callback callback = lightBarTransitionsController.mCallback;
        commandQueue.removeCallback((CommandQueue.Callbacks) callback);
        lightBarTransitionsController.mStatusBarStateController.removeCallback(callback);
        lightBarTransitionsController.mGestureNavigationSettingsObserver.unregister();
        this.mOverviewProxyService.removeCallback((OverviewProxyService.OverviewProxyListener) this.mOverviewProxyListener);
        ((UserTrackerImpl) this.mUserTracker).removeCallback(this.mUserChangedCallback);
        this.mWakefulnessLifecycle.removeObserver(this.mWakefulnessObserver);
        if (this.mOrientationHandle != null) {
            resetSecondaryHandle();
            navigationBarTransitions.mDarkIntensityListeners.remove(this.mOrientationHandleIntensityListener);
            this.mViewCaptureAwareWindowManager.removeView(this.mOrientationHandle);
            this.mOrientationHandle.getViewTreeObserver().removeOnGlobalLayoutListener(this.mOrientationHandleGlobalLayoutListener);
        }
        ((NavigationBarView) this.mView).getViewTreeObserver().removeOnComputeInternalInsetsListener(this.mOnComputeInternalInsetsListener);
        Handler handler = this.mHandler;
        handler.removeCallbacks(this.mAutoDim);
        handler.removeCallbacks(this.mOnVariableDurationHomeLongClick);
        handler.removeCallbacks(this.mEnableLayoutTransitions);
        this.mNavBarHelper.removeNavTaskStateUpdater(this.mNavbarTaskbarStateUpdater);
        Optional optional = this.mPipOptional;
        NavigationBarView navigationBarView2 = (NavigationBarView) this.mView;
        Objects.requireNonNull(navigationBarView2);
        optional.ifPresent(new NavigationBar$$ExternalSyntheticLambda20(navigationBarView2, 2));
        this.mFrame = null;
        this.mOrientationHandle = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void orientSecondaryHomeHandle() {
        /*
            r7 = this;
            int r0 = r7.mNavBarMode
            r1 = 2
            if (r0 != r1) goto La0
            com.android.systemui.navigationbar.gestural.QuickswitchOrientedNavHandle r0 = r7.mOrientationHandle
            if (r0 == 0) goto La0
            int r0 = r7.mStartingQuickSwitchRotation
            r2 = -1
            if (r0 != r2) goto L13
            r7.resetSecondaryHandle()
            goto La0
        L13:
            int r3 = r7.mCurrentRotation
            int r3 = r0 - r3
            if (r3 >= 0) goto L1b
            int r3 = r3 + 4
        L1b:
            if (r0 == r2) goto L1f
            if (r3 != r2) goto L38
        L1f:
            java.lang.String r0 = "secondary nav delta rotation: "
            java.lang.String r2 = " current: "
            java.lang.StringBuilder r0 = androidx.collection.MutableObjectList$$ExternalSyntheticOutline0.m(r0, r2, r3)
            int r2 = r7.mCurrentRotation
            r0.append(r2)
            java.lang.String r2 = " starting: "
            r0.append(r2)
            int r2 = r7.mStartingQuickSwitchRotation
            java.lang.String r4 = "NavigationBar"
            com.android.settingslib.bluetooth.LocalBluetoothLeBroadcast$3$$ExternalSyntheticOutline0.m(r0, r2, r4)
        L38:
            android.view.WindowManager r0 = r7.mWindowManager
            android.view.WindowMetrics r0 = r0.getCurrentWindowMetrics()
            android.graphics.Rect r0 = r0.getBounds()
            com.android.systemui.navigationbar.gestural.QuickswitchOrientedNavHandle r2 = r7.mOrientationHandle
            r2.mDeltaRotation = r3
            r2 = 3
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L61
            if (r3 == r4) goto L54
            if (r3 == r1) goto L61
            if (r3 == r2) goto L54
            r0 = r5
            r1 = r0
            goto L75
        L54:
            int r0 = r0.height()
            android.view.View r1 = r7.mView
            com.android.systemui.navigationbar.views.NavigationBarView r1 = (com.android.systemui.navigationbar.views.NavigationBarView) r1
            int r1 = r1.getHeight()
            goto L75
        L61:
            boolean r1 = r7.mShowOrientedHandleForImmersiveMode
            if (r1 != 0) goto L69
            r7.resetSecondaryHandle()
            return
        L69:
            int r1 = r0.width()
            android.view.View r0 = r7.mView
            com.android.systemui.navigationbar.views.NavigationBarView r0 = (com.android.systemui.navigationbar.views.NavigationBarView) r0
            int r0 = r0.getHeight()
        L75:
            android.view.WindowManager$LayoutParams r6 = r7.mOrientationParams
            if (r3 != 0) goto L7c
            r2 = 80
            goto L80
        L7c:
            if (r3 != r4) goto L7f
            goto L80
        L7f:
            r2 = 5
        L80:
            r6.gravity = r2
            r6.height = r0
            r6.width = r1
            android.view.WindowManager r0 = r7.mWindowManager
            com.android.systemui.navigationbar.gestural.QuickswitchOrientedNavHandle r1 = r7.mOrientationHandle
            r0.updateViewLayout(r1, r6)
            android.view.View r0 = r7.mView
            com.android.systemui.navigationbar.views.NavigationBarView r0 = (com.android.systemui.navigationbar.views.NavigationBarView) r0
            r1 = 8
            r0.setVisibility(r1)
            com.android.systemui.navigationbar.gestural.QuickswitchOrientedNavHandle r0 = r7.mOrientationHandle
            r0.setVisibility(r5)
            java.lang.String r0 = "orientSecondaryHomeHandle"
            r7.logNavbarOrientation(r0)
        La0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.navigationbar.views.NavigationBar.orientSecondaryHomeHandle():void");
    }

    public final void prepareNavigationBarView() {
        final int i = 2;
        final int i2 = 1;
        ((NavigationBarView) this.mView).reorient();
        ButtonDispatcher recentsButton = ((NavigationBarView) this.mView).getRecentsButton();
        final int i3 = 0;
        recentsButton.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda6
            public final /* synthetic */ NavigationBar f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i4;
                int i5 = i3;
                NavigationBar navigationBar = this.f$0;
                switch (i5) {
                    case 0:
                        navigationBar.mNavBarButtonClickLogger.logRecentsButtonClick();
                        if (LatencyTracker.isEnabled(navigationBar.mContext)) {
                            LatencyTracker.getInstance(navigationBar.mContext).onActionStart(1);
                        }
                        ((Optional) navigationBar.mCentralSurfacesOptionalLazy.get()).ifPresent(new NavigationBar$$ExternalSyntheticLambda5(0));
                        navigationBar.mCommandQueue.toggleRecentApps();
                        break;
                    case 1:
                        navigationBar.onImeSwitcherClick(view);
                        break;
                    default:
                        navigationBar.mNavBarButtonClickLogger.logAccessibilityButtonClick();
                        Display display = view.getDisplay();
                        AccessibilityManager accessibilityManager = navigationBar.mAccessibilityManager;
                        if (display != null) {
                            i4 = display.getDisplayId();
                        } else {
                            navigationBar.mDisplayTracker.getClass();
                            i4 = 0;
                        }
                        accessibilityManager.notifyAccessibilityButtonClicked(i4);
                        break;
                }
            }
        });
        recentsButton.mTouchListener = new NavigationBar$$ExternalSyntheticLambda7(this, i3);
        int size = recentsButton.mViews.size();
        for (int i4 = 0; i4 < size; i4++) {
            ((View) recentsButton.mViews.get(i4)).setOnTouchListener(recentsButton.mTouchListener);
        }
        ButtonDispatcher homeButton = ((NavigationBarView) this.mView).getHomeButton();
        homeButton.mTouchListener = new NavigationBar$$ExternalSyntheticLambda7(this, i);
        int size2 = homeButton.mViews.size();
        for (int i5 = 0; i5 < size2; i5++) {
            ((View) homeButton.mViews.get(i5)).setOnTouchListener(homeButton.mTouchListener);
        }
        NavBarButtonClickLogger navBarButtonClickLogger = this.mNavBarButtonClickLogger;
        if (navBarButtonClickLogger != null) {
            homeButton.mNavBarButtonClickLogger = navBarButtonClickLogger;
            int size3 = homeButton.mViews.size();
            for (int i6 = 0; i6 < size3; i6++) {
                homeButton.setNavBarButtonClickLoggerForViewChildren((View) homeButton.mViews.get(i6));
            }
        }
        ButtonDispatcher backButton = ((NavigationBarView) this.mView).getBackButton();
        if (navBarButtonClickLogger != null) {
            backButton.mNavBarButtonClickLogger = navBarButtonClickLogger;
            int size4 = backButton.mViews.size();
            for (int i7 = 0; i7 < size4; i7++) {
                backButton.setNavBarButtonClickLoggerForViewChildren((View) backButton.mViews.get(i7));
            }
        } else {
            backButton.getClass();
        }
        reconfigureHomeLongClick();
        ButtonDispatcher buttonDispatcher = (ButtonDispatcher) ((NavigationBarView) this.mView).mButtonDispatchers.get(R.id.accessibility_button);
        buttonDispatcher.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda6
            public final /* synthetic */ NavigationBar f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i42;
                int i52 = i;
                NavigationBar navigationBar = this.f$0;
                switch (i52) {
                    case 0:
                        navigationBar.mNavBarButtonClickLogger.logRecentsButtonClick();
                        if (LatencyTracker.isEnabled(navigationBar.mContext)) {
                            LatencyTracker.getInstance(navigationBar.mContext).onActionStart(1);
                        }
                        ((Optional) navigationBar.mCentralSurfacesOptionalLazy.get()).ifPresent(new NavigationBar$$ExternalSyntheticLambda5(0));
                        navigationBar.mCommandQueue.toggleRecentApps();
                        break;
                    case 1:
                        navigationBar.onImeSwitcherClick(view);
                        break;
                    default:
                        navigationBar.mNavBarButtonClickLogger.logAccessibilityButtonClick();
                        Display display = view.getDisplay();
                        AccessibilityManager accessibilityManager = navigationBar.mAccessibilityManager;
                        if (display != null) {
                            i42 = display.getDisplayId();
                        } else {
                            navigationBar.mDisplayTracker.getClass();
                            i42 = 0;
                        }
                        accessibilityManager.notifyAccessibilityButtonClicked(i42);
                        break;
                }
            }
        });
        buttonDispatcher.setOnLongClickListener(new NavigationBar$$ExternalSyntheticLambda10(this, 0));
        updateAccessibilityStateFlags();
        ButtonDispatcher buttonDispatcher2 = (ButtonDispatcher) ((NavigationBarView) this.mView).mButtonDispatchers.get(R.id.ime_switcher);
        buttonDispatcher2.setOnClickListener(new View.OnClickListener(this) { // from class: com.android.systemui.navigationbar.views.NavigationBar$$ExternalSyntheticLambda6
            public final /* synthetic */ NavigationBar f$0;

            {
                this.f$0 = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i42;
                int i52 = i2;
                NavigationBar navigationBar = this.f$0;
                switch (i52) {
                    case 0:
                        navigationBar.mNavBarButtonClickLogger.logRecentsButtonClick();
                        if (LatencyTracker.isEnabled(navigationBar.mContext)) {
                            LatencyTracker.getInstance(navigationBar.mContext).onActionStart(1);
                        }
                        ((Optional) navigationBar.mCentralSurfacesOptionalLazy.get()).ifPresent(new NavigationBar$$ExternalSyntheticLambda5(0));
                        navigationBar.mCommandQueue.toggleRecentApps();
                        break;
                    case 1:
                        navigationBar.onImeSwitcherClick(view);
                        break;
                    default:
                        navigationBar.mNavBarButtonClickLogger.logAccessibilityButtonClick();
                        Display display = view.getDisplay();
                        AccessibilityManager accessibilityManager = navigationBar.mAccessibilityManager;
                        if (display != null) {
                            i42 = display.getDisplayId();
                        } else {
                            navigationBar.mDisplayTracker.getClass();
                            i42 = 0;
                        }
                        accessibilityManager.notifyAccessibilityButtonClicked(i42);
                        break;
                }
            }
        });
        buttonDispatcher2.setOnLongClickListener(new NavigationBar$$ExternalSyntheticLambda10(this, 1));
        updateScreenPinningGestures();
    }

    public final void reconfigureHomeLongClick() {
        if (((NavigationBarView) this.mView).getHomeButton().mCurrentView == null) {
            return;
        }
        if (this.mHomeButtonLongPressDurationMs.isPresent() || this.mOverrideHomeButtonLongPressDurationMs.isPresent() || this.mOverrideHomeButtonLongPressSlopMultiplier.isPresent() || !this.mLongPressHomeEnabled) {
            ((NavigationBarView) this.mView).getHomeButton().mCurrentView.setLongClickable(false);
            ((NavigationBarView) this.mView).getHomeButton().mCurrentView.setHapticFeedbackEnabled(false);
            ((NavigationBarView) this.mView).getHomeButton().setOnLongClickListener(null);
        } else {
            ((NavigationBarView) this.mView).getHomeButton().mCurrentView.setLongClickable(true);
            ((NavigationBarView) this.mView).getHomeButton().mCurrentView.setHapticFeedbackEnabled(this.mHomeButtonLongPressHapticEnabled);
            ((NavigationBarView) this.mView).getHomeButton().setOnLongClickListener(new NavigationBar$$ExternalSyntheticLambda10(this, 4));
        }
    }

    public final void repositionNavigationBar(int i) {
        View view = this.mView;
        if (view == null || !((NavigationBarView) view).isAttachedToWindow()) {
            return;
        }
        prepareNavigationBarView();
        WindowManager windowManager = this.mWindowManager;
        NavigationBarFrame navigationBarFrame = this.mFrame;
        WindowManager.LayoutParams barLayoutParamsForRotation = getBarLayoutParamsForRotation(i);
        barLayoutParamsForRotation.paramsForRotation = new WindowManager.LayoutParams[4];
        for (int i2 = 0; i2 <= 3; i2++) {
            barLayoutParamsForRotation.paramsForRotation[i2] = getBarLayoutParamsForRotation(i2);
        }
        windowManager.updateViewLayout(navigationBarFrame, barLayoutParamsForRotation);
    }

    public final void resetSecondaryHandle() {
        QuickswitchOrientedNavHandle quickswitchOrientedNavHandle = this.mOrientationHandle;
        if (quickswitchOrientedNavHandle != null) {
            quickswitchOrientedNavHandle.setVisibility(8);
        }
        ((NavigationBarView) this.mView).setVisibility(0);
        logNavbarOrientation("resetSecondaryHandle");
        this.mOrientedHandleSamplingRegion = null;
        RegionSamplingHelper regionSamplingHelper = this.mRegionSamplingHelper;
        Rect sampledRegion = regionSamplingHelper.mCallback.getSampledRegion();
        if (regionSamplingHelper.mSamplingRequestBounds.equals(sampledRegion)) {
            return;
        }
        regionSamplingHelper.mSamplingRequestBounds.set(sampledRegion);
        regionSamplingHelper.updateSamplingListener();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void setImeWindowStatus(int i, int i2, int i3, boolean z) {
        if (i != this.mDisplayId) {
            return;
        }
        boolean isImeShown = this.mNavBarHelper.isImeShown(i2);
        int calculateBackDispositionHints = Utilities.calculateBackDispositionHints(this.mNavigationIconHints, i3, isImeShown, isImeShown && z);
        if (calculateBackDispositionHints == this.mNavigationIconHints) {
            return;
        }
        setNavigationIconHints(calculateBackDispositionHints);
        if (this.mIsOnDefaultDisplay) {
            ((Optional) this.mCentralSurfacesOptionalLazy.get()).ifPresent(new NavigationBar$$ExternalSyntheticLambda5(1));
        } else {
            checkNavBarModes();
        }
        updateSystemUiStateFlags();
    }

    public final void setNavBarMode(int i) {
        NavigationBarView navigationBarView = (NavigationBarView) this.mView;
        boolean z = this.mNavigationModeController.mCurrentUserContext.getResources().getBoolean(android.R.bool.config_isWindowManagerCameraCompatTreatmentEnabled);
        navigationBarView.mNavBarMode = i;
        navigationBarView.mImeDrawsImeNavBar = z;
        navigationBarView.mBarTransitions.mNavBarMode = i;
        navigationBarView.mEdgeBackGestureHandler.onNavigationModeChanged(i);
        navigationBarView.mRotationButtonController.mNavBarMode = navigationBarView.mNavBarMode;
        navigationBarView.updateRotationButton();
        boolean isGesturalMode = QuickStepContract.isGesturalMode(i);
        RegionSamplingHelper regionSamplingHelper = this.mRegionSamplingHelper;
        if (isGesturalMode) {
            regionSamplingHelper.start(this.mSamplingBounds);
        } else {
            regionSamplingHelper.stop();
        }
    }

    public final void setNavigationIconHints(int i) {
        if (i == this.mNavigationIconHints) {
            return;
        }
        if (!Utilities.isLargeScreen(this.mContext)) {
            boolean z = (i & 1) != 0;
            if (z != ((this.mNavigationIconHints & 1) != 0)) {
                NavigationBarView navigationBarView = (NavigationBarView) this.mView;
                if (z) {
                    navigationBarView.getClass();
                } else {
                    NavigationBarView.NavTransitionListener navTransitionListener = navigationBarView.mTransitionListener;
                    ButtonDispatcher backButton = NavigationBarView.this.getBackButton();
                    if (!navTransitionListener.mBackTransitioning && backButton.getVisibility() == 0 && navTransitionListener.mHomeAppearing && NavigationBarView.this.getHomeButton().getAlpha() == 0.0f) {
                        NavigationBarView.this.getBackButton().setAlpha(0.0f, true);
                        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(backButton, "alpha", 0.0f, 1.0f);
                        ofFloat.setStartDelay(navTransitionListener.mStartDelay);
                        ofFloat.setDuration(navTransitionListener.mDuration);
                        ofFloat.setInterpolator(navTransitionListener.mInterpolator);
                        ofFloat.start();
                    }
                }
                this.mImeVisible = z;
            }
            NavigationBarView navigationBarView2 = (NavigationBarView) this.mView;
            if (i != navigationBarView2.mNavigationIconHints) {
                navigationBarView2.mNavigationIconHints = i;
                navigationBarView2.updateNavButtonIcons();
            }
        }
        this.mNavigationIconHints = i;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void setWindowState(int i, int i2, int i3) {
        if (i == this.mDisplayId && i2 == 2 && this.mNavigationBarWindowState != i3) {
            this.mNavigationBarWindowState = i3;
            updateSystemUiStateFlags();
            this.mShowOrientedHandleForImmersiveMode = i3 == 2;
            if (this.mOrientationHandle != null && this.mStartingQuickSwitchRotation != -1) {
                orientSecondaryHomeHandle();
            }
            boolean z = this.mNavigationBarWindowState == 0;
            RegionSamplingHelper regionSamplingHelper = this.mRegionSamplingHelper;
            regionSamplingHelper.mWindowVisible = z;
            regionSamplingHelper.updateSamplingListener();
            RotationButtonController rotationButtonController = ((NavigationBarView) this.mView).mRotationButtonController;
            if (rotationButtonController.mIsNavigationBarShowing != z) {
                rotationButtonController.mIsNavigationBarShowing = z;
                if (rotationButtonController.canShowRotationButton() && rotationButtonController.mPendingRotationSuggestion) {
                    rotationButtonController.showAndLogRotationSuggestion();
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void showTransient(int i, int i2, boolean z) {
        if (i != this.mDisplayId || (WindowInsets.Type.navigationBars() & i2) == 0 || this.mTransientShown) {
            return;
        }
        this.mTransientShown = true;
        this.mTransientShownFromGestureOnSystemBar = z;
        this.mEdgeBackGestureHandler.mIsNavBarShownTransiently = true;
        int transitionMode = NavBarHelper.transitionMode(this.mAppearance, true);
        if (this.mTransitionMode != transitionMode) {
            this.mTransitionMode = transitionMode;
            checkNavBarModes();
            AutoHideController autoHideController = this.mAutoHideController;
            if (autoHideController != null) {
                autoHideController.touchAutoHide();
            }
            LightBarController lightBarController = this.mLightBarController;
            if (lightBarController != null) {
                lightBarController.mHasLightNavigationBar = LightBarController.isLight(lightBarController.mAppearance, transitionMode, 16);
            }
        }
    }

    public final void updateAccessibilityStateFlags() {
        NavBarHelper navBarHelper = this.mNavBarHelper;
        this.mLongPressHomeEnabled = navBarHelper.mLongPressHomeEnabled;
        View view = this.mView;
        if (view != null) {
            long j = navBarHelper.mA11yButtonState;
            boolean z = (16 & j) != 0;
            NavigationBarView navigationBarView = (NavigationBarView) view;
            ((ButtonDispatcher) navigationBarView.mButtonDispatchers.get(R.id.accessibility_button)).setLongClickable((j & 32) != 0);
            navigationBarView.mContextualButtonGroup.setButtonVisibility(R.id.accessibility_button, z);
        }
        updateSystemUiStateFlags();
    }

    public final void updateScreenPinningGestures() {
        ButtonDispatcher backButton = ((NavigationBarView) this.mView).getBackButton();
        ButtonDispatcher recentsButton = ((NavigationBarView) this.mView).getRecentsButton();
        if (this.mScreenPinningActive) {
            backButton.setOnLongClickListener(((NavigationBarView) this.mView).getRecentsButton().getVisibility() == 0 ? new NavigationBar$$ExternalSyntheticLambda10(this, 2) : new NavigationBar$$ExternalSyntheticLambda10(this, 3));
            recentsButton.setOnLongClickListener(new NavigationBar$$ExternalSyntheticLambda10(this, 2));
        } else {
            backButton.setOnLongClickListener(null);
            recentsButton.setOnLongClickListener(null);
        }
        backButton.setLongClickable(this.mScreenPinningActive);
        recentsButton.setLongClickable(this.mScreenPinningActive);
    }

    public final void updateSystemUiStateFlags() {
        long j = this.mNavBarHelper.mA11yButtonState;
        boolean z = (j & 16) != 0;
        boolean z2 = (j & 32) != 0;
        SysUiState sysUiState = this.mSysUiFlagsContainer;
        sysUiState.setFlag(16L, z);
        sysUiState.setFlag(32L, z2);
        sysUiState.setFlag(2L, !(this.mNavigationBarWindowState == 0));
        sysUiState.setFlag(262144L, (this.mNavigationIconHints & 1) != 0);
        sysUiState.setFlag(1048576L, (this.mNavigationIconHints & 4) != 0);
        sysUiState.setFlag(131072L, this.mBehavior != 2);
        sysUiState.commitUpdate(this.mDisplayId);
    }

    public static void updateButtonLocation(Region region, View view, boolean z) {
        Rect rect = new Rect();
        if (z) {
            view.getBoundsOnScreen(rect);
        } else {
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            int i = iArr[0];
            rect.set(i, iArr[1], view.getWidth() + i, view.getHeight() + iArr[1]);
        }
        region.op(rect, Region.Op.UNION);
    }
}

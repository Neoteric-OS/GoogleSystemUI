package com.android.systemui.statusbar.phone;

import android.animation.ValueAnimator;
import android.app.Fragment;
import android.app.IWallpaperManager;
import android.app.NotificationManager;
import android.app.StatusBarManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.hardware.devicestate.DeviceStateManager;
import android.metrics.LogMaker;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.dreams.IDreamManager;
import android.telecom.TelecomManager;
import android.util.ArraySet;
import android.util.DisplayMetrics;
import android.util.EventLog;
import android.util.Log;
import android.util.Slog;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ThreadedRenderer;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.widget.DateTimeView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import com.android.app.tracing.coroutines.CoroutineTracingKt;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.compose.animation.scene.SceneKey;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.logging.UiEventLoggerImpl;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.statusbar.RegisterStatusBarResult;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.keyguard.logging.BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0;
import com.android.systemui.AutoReinflateContainer;
import com.android.systemui.CoreStartable;
import com.android.systemui.DejankUtils;
import com.android.systemui.InitController;
import com.android.systemui.Prefs;
import com.android.systemui.accessibility.floatingmenu.AccessibilityFloatingMenuController;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.back.domain.interactor.BackActionInteractor;
import com.android.systemui.biometrics.AuthRippleController;
import com.android.systemui.biometrics.UdfpsKeyguardViewControllerLegacy$occludingAppBiometricUI$1;
import com.android.systemui.biometrics.UdfpsKeyguardViewControllerLegacy$statusBarKeyguardViewManagerCallback$1;
import com.android.systemui.bouncer.data.repository.KeyguardBouncerRepositoryImpl;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.camera.CameraIntents$Companion;
import com.android.systemui.charging.WiredChargingRippleController;
import com.android.systemui.charging.WirelessChargingAnimation;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.demomode.DemoMode;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$State;
import com.android.systemui.dock.DockManager;
import com.android.systemui.doze.DozeHost$Callback;
import com.android.systemui.emergency.EmergencyGestureModule$emergencyGestureIntentFactory$1;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.fragments.ExtensionFragmentListener;
import com.android.systemui.fragments.FragmentHostManager;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.OverlayPlugin;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginDependencyProvider;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.qs.QS;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.privacy.PrivacyType;
import com.android.systemui.qs.QSFragmentLegacy;
import com.android.systemui.qs.QSImpl;
import com.android.systemui.qs.QSPanel;
import com.android.systemui.qs.QSPanelController;
import com.android.systemui.scene.domain.interactor.WindowRootViewVisibilityInteractor;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.scrim.ScrimDrawable;
import com.android.systemui.scrim.ScrimView;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.DisplayTrackerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.settings.brightness.BrightnessMirrorHandler;
import com.android.systemui.settings.brightness.BrightnessMirrorHandler$brightnessMirrorListener$1;
import com.android.systemui.settings.brightness.BrightnessSliderController;
import com.android.systemui.settings.brightness.domain.interactor.BrightnessMirrorShowingInteractor;
import com.android.systemui.shade.BaseShadeControllerImpl;
import com.android.systemui.shade.CameraLauncher;
import com.android.systemui.shade.GlanceableHubContainerController;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowState;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.NotificationShadeWindowViewController;
import com.android.systemui.shade.NotificationShadeWindowViewController$$ExternalSyntheticLambda5;
import com.android.systemui.shade.NotificationShadeWindowViewController.AnonymousClass1;
import com.android.systemui.shade.QuickSettingsController;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeExpansionChangeEvent;
import com.android.systemui.shade.ShadeExpansionListener;
import com.android.systemui.shade.ShadeExpansionStateManager;
import com.android.systemui.shade.ShadeLogger;
import com.android.systemui.shade.ShadeSurface;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.shared.recents.utilities.Utilities;
import com.android.systemui.shared.statusbar.phone.BarTransitions;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.AutoHideUiElement;
import com.android.systemui.statusbar.CircleReveal;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.KeyboardShortcutListSearch;
import com.android.systemui.statusbar.KeyboardShortcuts;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.LiftReveal;
import com.android.systemui.statusbar.LightRevealScrim;
import com.android.systemui.statusbar.LockscreenShadeTransitionController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.PowerButtonReveal;
import com.android.systemui.statusbar.PulseExpansionHandler;
import com.android.systemui.statusbar.RemoteInputController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.connectivity.NetworkControllerImpl;
import com.android.systemui.statusbar.core.StatusBarInitializerImpl;
import com.android.systemui.statusbar.data.model.StatusBarMode;
import com.android.systemui.statusbar.data.repository.StatusBarModeRepositoryImpl;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.NotificationLaunchAnimatorControllerProvider;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.init.NotificationsController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.phone.PhoneStatusBarTransitions;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.android.systemui.statusbar.phone.PhoneStatusBarViewController;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragment;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconControllerImpl;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.BrightnessMirrorController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl$ExtensionBuilder$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl.ExtensionImpl;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl.ExtensionImpl.PluginItem;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.HotspotControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.policy.LocationControllerImpl;
import com.android.systemui.statusbar.policy.NextAlarmControllerImpl;
import com.android.systemui.statusbar.policy.RotationLockControllerImpl;
import com.android.systemui.statusbar.policy.SecurityControllerImpl;
import com.android.systemui.statusbar.policy.SensorPrivacyControllerImpl;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.android.systemui.statusbar.window.StatusBarWindowStateController;
import com.android.systemui.statusbar.window.StatusBarWindowStateListener;
import com.android.systemui.surfaceeffects.ripple.RippleShader;
import com.android.systemui.unfold.FoldAodAnimationController;
import com.android.systemui.util.Assert;
import com.android.systemui.util.DumpUtilsKt;
import com.android.systemui.util.WallpaperController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.MessageRouterImpl;
import com.android.systemui.util.kotlin.BooleanFlowOperators$not$$inlined$map$1;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.systemui.volume.VolumeComponent;
import com.android.systemui.volume.VolumeDialogComponent;
import com.android.wm.shell.R;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
import com.google.android.systemui.dreamliner.DockObserver;
import com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle;
import dagger.Lazy;
import dagger.internal.Provider;
import dalvik.annotation.optimization.NeverCompile;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIterator;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CentralSurfacesImpl implements CoreStartable, CentralSurfaces {
    public static final UiEventLogger sUiEventLogger = new UiEventLoggerImpl();
    public final AccessibilityFloatingMenuController mAccessibilityFloatingMenuController;
    public final ActivityStarter mActivityStarter;
    public final ActivityTransitionAnimator mActivityTransitionAnimator;
    public final AlternateBouncerInteractor mAlternateBouncerInteractor;
    public View mAmbientIndicationContainer;
    public final Lazy mAssistManagerLazy;
    public final AuthRippleController mAuthRippleController;
    public final AutoHideController mAutoHideController;
    public final BackActionInteractor mBackActionInteractor;
    public IStatusBarService mBarService;
    public final BatteryController mBatteryController;
    public BiometricUnlockController mBiometricUnlockController;
    public final Lazy mBiometricUnlockControllerLazy;
    public boolean mBouncerShowing;
    public BrightnessMirrorController mBrightnessMirrorController;
    public boolean mBrightnessMirrorVisible;
    public final BrightnessSliderController.Factory mBrightnessSliderFactory;
    public final BroadcastDispatcher mBroadcastDispatcher;
    public final Optional mBubblesOptional;
    public final Lazy mCameraLauncherLazy;
    public boolean mCloseQsBeforeScreenOff;
    public final SysuiColorExtractor mColorExtractor;
    public final CommandQueue mCommandQueue;
    public CentralSurfacesCommandQueueCallbacks mCommandQueueCallbacks;
    public final Lazy mCommandQueueCallbacksLazy;
    public final CommunalInteractor mCommunalInteractor;
    public final ConfigurationController mConfigurationController;
    public final Context mContext;
    public final DemoModeController mDemoModeController;
    public boolean mDeviceInteractive;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public final DeviceStateManager mDeviceStateManager;
    public boolean mDismissingShadeForActivityLaunch;
    public Display mDisplay;
    public int mDisplayId;
    public final DisplayMetrics mDisplayMetrics;
    public final DozeParameters mDozeParameters;
    public final DozeScrimController mDozeScrimController;
    DozeServiceHost mDozeServiceHost;
    public boolean mDozing;
    public final IDreamManager mDreamManager;
    public final EmergencyGestureModule$emergencyGestureIntentFactory$1 mEmergencyGestureIntentFactory;
    public final ExtensionControllerImpl mExtensionController;
    public final FalsingCollector mFalsingCollector;
    public final FalsingManager mFalsingManager;
    public final FeatureFlagsClassic mFeatureFlags;
    public final Provider mFingerprintManager;
    public final FragmentService mFragmentService;
    public PowerManager.WakeLock mGestureWakeLock;
    public final GlanceableHubContainerController mGlanceableHubContainerController;
    public final NotificationGutsManager mGutsManager;
    public final HeadsUpManager mHeadsUpManager;
    public final PhoneStatusBarPolicy mIconPolicy;
    public final InitController mInitController;
    public int mInteractingWindows;
    public boolean mIsLaunchingActivityOverLockscreen;
    public final JavaAdapter mJavaAdapter;
    public final KeyguardBypassController mKeyguardBypassController;
    public final KeyguardIndicationControllerGoogle mKeyguardIndicationController;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUnlockAnimationController mKeyguardUnlockAnimationController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final KeyguardViewMediator mKeyguardViewMediator;
    public final KeyguardViewMediator.AnonymousClass4 mKeyguardViewMediatorCallback;
    public int mLastCameraLaunchSource;
    public int mLastLoggedStateFingerprint;
    public boolean mLaunchCameraOnFinishedGoingToSleep;
    public boolean mLaunchCameraWhenFinishedWaking;
    public boolean mLaunchEmergencyActionOnFinishedGoingToSleep;
    public boolean mLaunchEmergencyActionWhenFinishedWaking;
    public StatusBarKeyguardViewManager.AnonymousClass6 mLaunchTransitionCancelRunnable;
    public StatusBarKeyguardViewManager.AnonymousClass6 mLaunchTransitionEndRunnable;
    public final LightBarController mLightBarController;
    public final LightRevealScrim mLightRevealScrim;
    public final LockscreenShadeTransitionController mLockscreenShadeTransitionController;
    public final NotificationLockscreenUserManager mLockscreenUserManager;
    public final DelayableExecutor mMainExecutor;
    public final NotificationMediaManager mMediaManager;
    public final MessageRouterImpl mMessageRouter;
    public final MetricsLogger mMetricsLogger;
    public final NavigationBarControllerImpl mNavigationBarController;
    public boolean mNoAnimationOnNextBarModeChange;
    public final Lazy mNoteTaskControllerLazy;
    public final NotificationStackScrollLayoutController.NotificationListContainerImpl mNotifListContainer;
    public final Lazy mNotificationActivityStarterLazy;
    public final NotificationLaunchAnimatorControllerProvider mNotificationAnimationProvider;
    public final Lazy mNotificationShadeDepthControllerLazy;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public final Lazy mNotificationShadeWindowViewControllerLazy;
    public final NotificationsController mNotificationsController;
    public PhoneStatusBarViewController mPhoneStatusBarViewController;
    public final PluginDependencyProvider mPluginDependencyProvider;
    public final PluginManager mPluginManager;
    public PowerButtonReveal mPowerButtonReveal;
    public final PowerInteractor mPowerInteractor;
    public final PowerManager mPowerManager;
    public final Lazy mPresenterLazy;
    public final PulseExpansionHandler mPulseExpansionHandler;
    public QSPanelController mQSPanelController;
    public final QuickSettingsController mQsController;
    public final NotificationRemoteInputManager mRemoteInputManager;
    public View mReportRejectedTouch;
    public final ScreenLifecycle mScreenLifecycle;
    public final ScreenOffAnimationController mScreenOffAnimationController;
    public final ScrimController mScrimController;
    public final ShadeController mShadeController;
    public final ShadeExpansionStateManager mShadeExpansionStateManager;
    public final ShadeLogger mShadeLogger;
    public final ShadeSurface mShadeSurface;
    public final NotificationStackScrollLayout mStackScroller;
    public final NotificationStackScrollLayoutController mStackScrollerController;
    public final Optional mStartingSurfaceOptional;
    public int mState;
    public final StatusBarHideIconsForBouncerManager mStatusBarHideIconsForBouncerManager;
    public final StatusBarInitializerImpl mStatusBarInitializer;
    public StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public final StatusBarModeRepositoryImpl mStatusBarModeRepository;
    public final StatusBarSignalPolicy mStatusBarSignalPolicy;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public LogMaker mStatusBarStateLog;
    public final StatusBarTouchableRegionManager mStatusBarTouchableRegionManager;
    public PhoneStatusBarTransitions mStatusBarTransitions;
    public final StatusBarWindowControllerImpl mStatusBarWindowController;
    public final Executor mUiBgExecutor;
    public UiModeManager mUiModeManager;
    public final UserInfoControllerImpl mUserInfoControllerImpl;
    public final UserTracker mUserTracker;
    public final ViewCaptureAwareWindowManager mViewCaptureAwareWindowManager;
    public final VolumeComponent mVolumeComponent;
    public final NotificationWakeUpCoordinator mWakeUpCoordinator;
    public final WakefulnessLifecycle mWakefulnessLifecycle;
    public final WallpaperController mWallpaperController;
    public final WallpaperManager mWallpaperManager;
    public boolean mWallpaperSupported;
    public final WindowRootViewVisibilityInteractor mWindowRootViewVisibilityInteractor;
    public float mTransitionToFullShadeProgress = 0.0f;
    public final AnonymousClass1 mKeyguardStateControllerCallback = new KeyguardStateController.Callback() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl.1
        @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
        public final void onKeyguardShowingChanged() {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            boolean z = ((KeyguardStateControllerImpl) centralSurfacesImpl.mKeyguardStateController).mOccluded;
            StatusBarHideIconsForBouncerManager statusBarHideIconsForBouncerManager = centralSurfacesImpl.mStatusBarHideIconsForBouncerManager;
            statusBarHideIconsForBouncerManager.isOccluded = z;
            statusBarHideIconsForBouncerManager.updateHideIconsForBouncer(false);
            ScrimController scrimController = centralSurfacesImpl.mScrimController;
            if (scrimController.mKeyguardOccluded == z) {
                return;
            }
            scrimController.mKeyguardOccluded = z;
            scrimController.updateScrims();
        }
    };
    public final Point mCurrentDisplaySize = new Point();
    public int mStatusBarWindowState = 0;
    public boolean mShouldDelayLockscreenTransitionFromAod = false;
    public final Object mQueueLock = new Object();
    protected boolean mUserSetup = false;
    public final LifecycleRegistry mLifecycle = new LifecycleRegistry(this);
    public boolean mIsIdleOnCommunal = false;
    public final CentralSurfacesImpl$$ExternalSyntheticLambda2 mIdleOnCommunalConsumer = new CentralSurfacesImpl$$ExternalSyntheticLambda2(this, 0);
    public final CentralSurfacesImpl$$ExternalSyntheticLambda3 mOnColorsChangedListener = new ColorExtractor.OnColorsChangedListener() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda3
        public final void onColorsChanged(ColorExtractor colorExtractor, int i) {
            CentralSurfacesImpl.this.updateTheme();
        }
    };
    public final CentralSurfacesImpl$$ExternalSyntheticLambda1 mCheckBarModes = new CentralSurfacesImpl$$ExternalSyntheticLambda1(this, 4);
    public final AnonymousClass8 mBroadcastReceiver = new AnonymousClass8(this, 0);
    final WakefulnessLifecycle.Observer mWakefulnessObserver = new AnonymousClass9();
    public final AnonymousClass10 mScreenObserver = new ScreenLifecycle.Observer() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl.10
        @Override // com.android.systemui.keyguard.ScreenLifecycle.Observer
        public final void onScreenTurnedOff() {
            Trace.beginSection("CentralSurfaces#onScreenTurnedOff");
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            centralSurfacesImpl.mFalsingCollector.onScreenOff();
            centralSurfacesImpl.mScrimController.mScreenOn = false;
            if (centralSurfacesImpl.mCloseQsBeforeScreenOff) {
                centralSurfacesImpl.mQsController.closeQs();
                centralSurfacesImpl.mCloseQsBeforeScreenOff = false;
            }
            centralSurfacesImpl.updateIsKeyguard(false);
            Trace.endSection();
        }

        @Override // com.android.systemui.keyguard.ScreenLifecycle.Observer
        public final void onScreenTurnedOn() {
            ScrimController scrimController = CentralSurfacesImpl.this.mScrimController;
            scrimController.mScreenOn = true;
            if (scrimController.mHandler.hasCallbacks(scrimController.mBlankingTransitionRunnable)) {
                if (ScrimController.DEBUG) {
                    Log.d("ScrimController", "Shorter blanking because screen turned on. All good.");
                }
                scrimController.mHandler.removeCallbacks(scrimController.mBlankingTransitionRunnable);
                scrimController.mBlankingTransitionRunnable.run();
            }
        }

        @Override // com.android.systemui.keyguard.ScreenLifecycle.Observer
        public final void onScreenTurningOn() {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            centralSurfacesImpl.mFalsingCollector.onScreenTurningOn();
            centralSurfacesImpl.mShadeSurface.getClass();
        }
    };
    public final AnonymousClass8 mBannerActionBroadcastReceiver = new AnonymousClass8(this, 1);
    public final KeyguardUpdateMonitorCallback mUpdateCallback = new KeyguardUpdateMonitorCallback() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl.13
        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onDreamingStateChanged(boolean z) {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            centralSurfacesImpl.updateScrimController();
            if (z) {
                CentralSurfacesImpl.m881$$Nest$mmaybeEscalateHeadsUp(centralSurfacesImpl);
            }
        }
    };
    public final AnonymousClass14 mFalsingBeliefListener = new FalsingManager.FalsingBeliefListener() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl.14
        @Override // com.android.systemui.plugins.FalsingManager.FalsingBeliefListener
        public final void onFalse() {
            CentralSurfacesImpl.this.mStatusBarKeyguardViewManager.reset(true, true);
        }
    };
    public final AnonymousClass4 mUnlockScrimCallback = new AnonymousClass4();
    public final AnonymousClass16 mUserSetupObserver = new AnonymousClass16();
    public final AnonymousClass17 mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl.17
        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
        public final void onConfigChanged(Configuration configuration) {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            centralSurfacesImpl.updateResources();
            centralSurfacesImpl.mDisplay.getMetrics(centralSurfacesImpl.mDisplayMetrics);
            centralSurfacesImpl.mDisplay.getSize(centralSurfacesImpl.mCurrentDisplaySize);
            centralSurfacesImpl.mContext.getApplicationInfo().setEnableOnBackInvokedCallback(true);
        }

        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
        public final void onDensityOrFontScaleChanged() {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            BrightnessMirrorController brightnessMirrorController = centralSurfacesImpl.mBrightnessMirrorController;
            if (brightnessMirrorController != null) {
                brightnessMirrorController.reinflate$1();
            }
            centralSurfacesImpl.mUserInfoControllerImpl.reloadUserInfo();
        }

        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
        public final void onThemeChanged() {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            BrightnessMirrorController brightnessMirrorController = centralSurfacesImpl.mBrightnessMirrorController;
            if (brightnessMirrorController != null) {
                brightnessMirrorController.reinflate$1();
            }
            centralSurfacesImpl.mShadeSurface.onThemeChanged();
            StatusBarKeyguardViewManager statusBarKeyguardViewManager = centralSurfacesImpl.mStatusBarKeyguardViewManager;
            if (statusBarKeyguardViewManager != null) {
                KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl = statusBarKeyguardViewManager.mPrimaryBouncerInteractor.repository;
                Boolean bool = Boolean.TRUE;
                StateFlowImpl stateFlowImpl = keyguardBouncerRepositoryImpl._resourceUpdateRequests;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, bool);
            }
            View view = centralSurfacesImpl.mAmbientIndicationContainer;
            if (view instanceof AutoReinflateContainer) {
                ((AutoReinflateContainer) view).inflateLayout();
            }
        }

        @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
        public final void onUiModeChanged() {
            BrightnessMirrorController brightnessMirrorController = CentralSurfacesImpl.this.mBrightnessMirrorController;
            if (brightnessMirrorController != null) {
                brightnessMirrorController.reinflate$1();
            }
        }
    };
    public final AnonymousClass18 mStateListener = new StatusBarStateController.StateListener() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl.18
        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onDozeAmountChanged(float f, float f2) {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            if ((centralSurfacesImpl.mLightRevealScrim.revealEffect instanceof CircleReveal) || centralSurfacesImpl.mBiometricUnlockController.isWakeAndUnlock()) {
                return;
            }
            centralSurfacesImpl.mLightRevealScrim.setRevealAmount(1.0f - f);
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onDozingChanged(boolean z) {
            Trace.beginSection("CentralSurfaces#updateDozing");
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            centralSurfacesImpl.mDozing = z;
            centralSurfacesImpl.mShadeSurface.resetViews((centralSurfacesImpl.mDozeServiceHost.mDozingRequested && centralSurfacesImpl.mDozeParameters.mControlScreenOffAnimation) && z);
            boolean z2 = centralSurfacesImpl.mDozing;
            KeyguardViewMediator keyguardViewMediator = centralSurfacesImpl.mKeyguardViewMediator;
            if (z2 != keyguardViewMediator.mDozing) {
                keyguardViewMediator.mDozing = z2;
                if (!z2) {
                    keyguardViewMediator.mAnimatingScreenOff = false;
                }
                if (keyguardViewMediator.mShowing || !keyguardViewMediator.mPendingLock || !keyguardViewMediator.mDozeParameters.canControlUnlockedScreenOff()) {
                    keyguardViewMediator.setShowingLocked("setDozing", keyguardViewMediator.mShowing, false);
                }
            }
            centralSurfacesImpl.updateDozingState();
            centralSurfacesImpl.mDozeServiceHost.updateDozing();
            centralSurfacesImpl.updateScrimController();
            if (centralSurfacesImpl.mBiometricUnlockController.isWakeAndUnlock()) {
                centralSurfacesImpl.updateIsKeyguard(false);
            }
            centralSurfacesImpl.updateReportRejectedTouchVisibility();
            Trace.endSection();
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onStateChanged(int i) {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            centralSurfacesImpl.mState = i;
            centralSurfacesImpl.updateReportRejectedTouchVisibility();
            centralSurfacesImpl.mDozeServiceHost.updateDozing();
            centralSurfacesImpl.updateTheme();
            centralSurfacesImpl.mNavigationBarController.touchAutoDim(centralSurfacesImpl.mDisplayId);
            Trace.beginSection("CentralSurfaces#updateKeyguardState");
            if (centralSurfacesImpl.mState == 1) {
                centralSurfacesImpl.mShadeSurface.cancelPendingCollapse();
            }
            centralSurfacesImpl.updateDozingState();
            centralSurfacesImpl.checkBarModes$1();
            centralSurfacesImpl.updateScrimController();
            Trace.endSection();
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onStatePreChange(int i, int i2) {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            if (((Boolean) ((StateFlowImpl) centralSurfacesImpl.mWindowRootViewVisibilityInteractor.isLockscreenOrShadeVisible.$$delegate_0).getValue()).booleanValue() && (i2 == 2 || ((StatusBarStateControllerImpl) centralSurfacesImpl.mStatusBarStateController).goingToFullShade())) {
                try {
                    centralSurfacesImpl.mBarService.clearNotificationEffects();
                } catch (RemoteException unused) {
                }
            }
            if (i2 == 1) {
                centralSurfacesImpl.mRemoteInputManager.onPanelCollapsed();
                CentralSurfacesImpl.m881$$Nest$mmaybeEscalateHeadsUp(centralSurfacesImpl);
            }
        }
    };
    public final AnonymousClass19 mBatteryStateChangeCallback = new BatteryController.BatteryStateChangeCallback() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl.19
        @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
        public final void onPowerSaveChanged(boolean z) {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            ((ExecutorImpl) centralSurfacesImpl.mMainExecutor).execute(centralSurfacesImpl.mCheckBarModes);
            DozeServiceHost dozeServiceHost = centralSurfacesImpl.mDozeServiceHost;
            if (dozeServiceHost == null) {
                return;
            }
            Assert.isMainThread();
            Iterator it = dozeServiceHost.mCallbacks.iterator();
            while (true) {
                ArrayIterator arrayIterator = (ArrayIterator) it;
                if (!arrayIterator.hasNext()) {
                    return;
                } else {
                    ((DozeHost$Callback) arrayIterator.next()).onPowerSaveChanged();
                }
            }
        }
    };
    public final AnonymousClass4 mActivityTransitionAnimatorCallback = new AnonymousClass4();
    public final AnonymousClass21 mActivityTransitionAnimatorListener = new ActivityTransitionAnimator.Listener() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl.21
        @Override // com.android.systemui.animation.ActivityTransitionAnimator.Listener
        public final void onTransitionAnimationEnd() {
            CentralSurfacesImpl.this.mKeyguardViewMediator.setBlursDisabledForAppLaunch(false);
        }

        @Override // com.android.systemui.animation.ActivityTransitionAnimator.Listener
        public final void onTransitionAnimationStart() {
            CentralSurfacesImpl.this.mKeyguardViewMediator.setBlursDisabledForAppLaunch(true);
        }
    };
    public final AnonymousClass22 mDemoModeCallback = new DemoMode() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl.22
        @Override // com.android.systemui.demomode.DemoModeCommandReceiver
        public final void onDemoModeFinished() {
            CentralSurfacesImpl.this.checkBarModes$1();
        }

        @Override // com.android.systemui.demomode.DemoModeCommandReceiver
        public final void dispatchDemoCommand(Bundle bundle, String str) {
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.CentralSurfacesImpl$16, reason: invalid class name */
    public final class AnonymousClass16 implements DeviceProvisionedController.DeviceProvisionedListener {
        public AnonymousClass16() {
        }

        @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
        public final void onUserSetupChanged() {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            boolean isCurrentUserSetup = ((DeviceProvisionedControllerImpl) centralSurfacesImpl.mDeviceProvisionedController).isCurrentUserSetup();
            Log.d("CentralSurfaces", "mUserSetupObserver - DeviceProvisionedListener called for current user");
            if (isCurrentUserSetup != centralSurfacesImpl.mUserSetup) {
                centralSurfacesImpl.mUserSetup = isCurrentUserSetup;
                if (isCurrentUserSetup || centralSurfacesImpl.mState != 0) {
                    return;
                }
                centralSurfacesImpl.mShadeController.animateCollapseShade(0);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.CentralSurfacesImpl$4, reason: invalid class name */
    public final class AnonymousClass4 implements ScrimController.Callback, ShadeController.ShadeVisibilityListener, AutoHideUiElement {
        public /* synthetic */ AnonymousClass4() {
        }

        public void expandedVisibleChanged(boolean z) {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            if (z) {
                centralSurfacesImpl.setInteracting(1, true);
                return;
            }
            centralSurfacesImpl.setInteracting(1, false);
            if (((StatusBarNotificationActivityStarter) centralSurfacesImpl.mNotificationActivityStarterLazy.get()).mIsCollapsingToShowActivityOverLockscreen || centralSurfacesImpl.mKeyguardViewMediator.mHiding || centralSurfacesImpl.mKeyguardUpdateMonitor.mKeyguardGoingAway) {
                return;
            }
            int i = centralSurfacesImpl.mState;
            StatusBarKeyguardViewManager statusBarKeyguardViewManager = centralSurfacesImpl.mStatusBarKeyguardViewManager;
            if (i == 2) {
                statusBarKeyguardViewManager.reset(true, false);
                return;
            }
            if (i != 1 || statusBarKeyguardViewManager.primaryBouncerIsOrWillBeShowing()) {
                return;
            }
            statusBarKeyguardViewManager.mKeyguardSecurityModel.getSecurityMode(statusBarKeyguardViewManager.mSelectedUserInteractor.getSelectedUserId());
            if (statusBarKeyguardViewManager.needsFullscreenBouncer()) {
                Log.d("CentralSurfaces", "showBouncerOrLockScreenIfKeyguard, showingBouncer");
                AlternateBouncerInteractor alternateBouncerInteractor = statusBarKeyguardViewManager.mAlternateBouncerInteractor;
                if (!((Boolean) alternateBouncerInteractor.canShowAlternateBouncer.getValue()).booleanValue()) {
                    statusBarKeyguardViewManager.showPrimaryBouncer(true);
                    return;
                }
                Log.d("StatusBarKeyguardViewManager", "showBouncer:alternateBouncer.forceShow()");
                alternateBouncerInteractor.bouncerRepository.setAlternateVisible(true);
                statusBarKeyguardViewManager.updateAlternateBouncerShowing(alternateBouncerInteractor.isVisibleState());
            }
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public void hide() {
            StateFlowImpl stateFlowImpl = CentralSurfacesImpl.this.mStatusBarModeRepository.defaultDisplay._isTransientShown;
            Boolean bool = Boolean.FALSE;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, bool);
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public boolean isVisible() {
            return ((Boolean) CentralSurfacesImpl.this.mStatusBarModeRepository.defaultDisplay.isTransientShown.getValue()).booleanValue();
        }

        @Override // com.android.systemui.statusbar.phone.ScrimController.Callback
        public void onCancelled() {
            onFinished();
        }

        @Override // com.android.systemui.statusbar.phone.ScrimController.Callback
        public void onFinished() {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            if (((KeyguardStateControllerImpl) centralSurfacesImpl.mKeyguardStateController).mKeyguardFadingAway) {
                centralSurfacesImpl.mStatusBarKeyguardViewManager.onKeyguardFadedAway$1();
            }
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public boolean shouldHideOnTouch() {
            return !CentralSurfacesImpl.this.mRemoteInputManager.isRemoteInputActive();
        }

        @Override // com.android.systemui.statusbar.AutoHideUiElement
        public void synchronizeState() {
            CentralSurfacesImpl.this.checkBarModes$1();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.CentralSurfacesImpl$8, reason: invalid class name */
    public final class AnonymousClass8 extends BroadcastReceiver {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ CentralSurfacesImpl this$0;

        public /* synthetic */ AnonymousClass8(CentralSurfacesImpl centralSurfacesImpl, int i) {
            this.$r8$classId = i;
            this.this$0 = centralSurfacesImpl;
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            switch (this.$r8$classId) {
                case 0:
                    Trace.beginSection("CentralSurfaces#onReceive");
                    String action = intent.getAction();
                    String stringExtra = intent.getStringExtra("reason");
                    if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(action)) {
                        if (this.this$0.shouldUseTabletKeyboardShortcuts()) {
                            KeyboardShortcutListSearch.dismiss();
                        } else {
                            KeyboardShortcuts.dismiss();
                        }
                        this.this$0.mRemoteInputManager.closeRemoteInputs();
                        if (((NotificationLockscreenUserManagerImpl) this.this$0.mLockscreenUserManager).isCurrentProfile(getSendingUserId())) {
                            this.this$0.mShadeLogger.d("ACTION_CLOSE_SYSTEM_DIALOGS intent: closing shade");
                            if (stringExtra != null) {
                                r1 = stringExtra.equals("recentapps") ? 2 : 0;
                                if (stringExtra.equals(BcSmartspaceDataPlugin.UI_SURFACE_DREAM) && this.this$0.mScreenOffAnimationController.shouldExpandNotifications()) {
                                    r1 |= 4;
                                }
                            }
                            this.this$0.mShadeController.animateCollapseShade(r1);
                        } else {
                            this.this$0.mShadeLogger.d("ACTION_CLOSE_SYSTEM_DIALOGS intent: non-matching user ID");
                        }
                    } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                        NotificationShadeWindowController notificationShadeWindowController = this.this$0.mNotificationShadeWindowController;
                        if (notificationShadeWindowController != null) {
                            NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) notificationShadeWindowController;
                            NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
                            notificationShadeWindowState.getClass();
                            notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
                        }
                        CentralSurfacesImpl centralSurfacesImpl = this.this$0;
                        PhoneStatusBarTransitions phoneStatusBarTransitions = centralSurfacesImpl.mStatusBarTransitions;
                        if (phoneStatusBarTransitions != null) {
                            BarTransitions.BarBackgroundDrawable barBackgroundDrawable = phoneStatusBarTransitions.mBarBackground;
                            if (barBackgroundDrawable.mAnimating) {
                                barBackgroundDrawable.mAnimating = false;
                                barBackgroundDrawable.invalidateSelf();
                            }
                        }
                        centralSurfacesImpl.mNavigationBarController.finishBarAnimations(centralSurfacesImpl.mDisplayId);
                        this.this$0.mNotificationsController.resetUserExpandedStates();
                    }
                    Trace.endSection();
                    break;
                default:
                    String action2 = intent.getAction();
                    if ("com.android.systemui.statusbar.banner_action_cancel".equals(action2) || "com.android.systemui.statusbar.banner_action_setup".equals(action2)) {
                        ((NotificationManager) this.this$0.mContext.getSystemService("notification")).cancel(5);
                        Settings.Secure.putInt(this.this$0.mContext.getContentResolver(), "show_note_about_notification_hiding", 0);
                        if ("com.android.systemui.statusbar.banner_action_setup".equals(action2)) {
                            this.this$0.mShadeController.animateCollapseShade(0, true, false, 1.0f);
                            this.this$0.mContext.startActivity(new Intent("android.settings.ACTION_APP_NOTIFICATION_REDACTION").addFlags(268435456));
                            break;
                        }
                    }
                    break;
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.CentralSurfacesImpl$9, reason: invalid class name */
    public final class AnonymousClass9 implements WakefulnessLifecycle.Observer {
        public AnonymousClass9() {
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onFinishedGoingToSleep$1() {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            ((CameraLauncher) centralSurfacesImpl.mCameraLauncherLazy.get()).mKeyguardBypassController.launchingAffordance = false;
            centralSurfacesImpl.releaseGestureWakeLock();
            centralSurfacesImpl.mLaunchCameraWhenFinishedWaking = false;
            centralSurfacesImpl.mDeviceInteractive = false;
            centralSurfacesImpl.updateNotificationPanelTouchState();
            centralSurfacesImpl.getNotificationShadeWindowViewController().cancelCurrentTouch();
            boolean z = centralSurfacesImpl.mLaunchCameraOnFinishedGoingToSleep;
            DelayableExecutor delayableExecutor = centralSurfacesImpl.mMainExecutor;
            if (z) {
                centralSurfacesImpl.mLaunchCameraOnFinishedGoingToSleep = false;
                ((ExecutorImpl) delayableExecutor).execute(new CentralSurfacesImpl$9$$ExternalSyntheticLambda0(this, 0));
            }
            if (centralSurfacesImpl.mLaunchEmergencyActionOnFinishedGoingToSleep) {
                centralSurfacesImpl.mLaunchEmergencyActionOnFinishedGoingToSleep = false;
                ((ExecutorImpl) delayableExecutor).execute(new CentralSurfacesImpl$9$$ExternalSyntheticLambda0(this, 1));
            }
            centralSurfacesImpl.updateIsKeyguard(false);
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onFinishedWakingUp() {
            UserHandle userHandle;
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            if (centralSurfacesImpl.mShouldDelayLockscreenTransitionFromAod) {
                ((NotificationShadeWindowControllerImpl) centralSurfacesImpl.mNotificationShadeWindowController).batchApplyWindowLayoutParams(new CentralSurfacesImpl$9$$ExternalSyntheticLambda0(this, 3));
            }
            NotificationWakeUpCoordinator notificationWakeUpCoordinator = centralSurfacesImpl.mWakeUpCoordinator;
            notificationWakeUpCoordinator.fullyAwake = true;
            notificationWakeUpCoordinator.setWakingUp(false);
            if (((KeyguardStateControllerImpl) centralSurfacesImpl.mKeyguardStateController).mOccluded && !centralSurfacesImpl.mDozeParameters.canControlUnlockedScreenOff()) {
                ((NotificationLockscreenUserManagerImpl) centralSurfacesImpl.mLockscreenUserManager).updatePublicMode();
                centralSurfacesImpl.mStackScrollerController.updateSensitivenessWithAnimation(false);
            }
            if (centralSurfacesImpl.mLaunchCameraWhenFinishedWaking) {
                centralSurfacesImpl.mMessageRouter.sendMessageDelayed(5000L, 1003);
                CameraLauncher cameraLauncher = (CameraLauncher) centralSurfacesImpl.mCameraLauncherLazy.get();
                int i = centralSurfacesImpl.mLastCameraLaunchSource;
                if (!centralSurfacesImpl.mShadeSurface.isFullyCollapsed()) {
                    cameraLauncher.mKeyguardBypassController.launchingAffordance = true;
                }
                cameraLauncher.mCameraGestureHelper.launchCamera(i);
                centralSurfacesImpl.mLaunchCameraWhenFinishedWaking = false;
            }
            if (centralSurfacesImpl.mLaunchEmergencyActionWhenFinishedWaking) {
                centralSurfacesImpl.mLaunchEmergencyActionWhenFinishedWaking = false;
                Intent invoke = centralSurfacesImpl.mEmergencyGestureIntentFactory.invoke();
                if (invoke != null) {
                    Context context = centralSurfacesImpl.mContext;
                    for (String str : context.getResources().getStringArray(R.array.system_ui_packages)) {
                        if (invoke.getComponent() == null) {
                            break;
                        }
                        if (str.equals(invoke.getComponent().getPackageName())) {
                            userHandle = new UserHandle(UserHandle.myUserId());
                            break;
                        }
                    }
                    userHandle = ((UserTrackerImpl) centralSurfacesImpl.mUserTracker).getUserHandle();
                    context.startActivityAsUser(invoke, userHandle);
                }
            }
            centralSurfacesImpl.updateScrimController();
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onStartedGoingToSleep() {
            DejankUtils.startDetectingBlockingIpcs("CentralSurfaces#onStartedGoingToSleep");
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            StatusBarKeyguardViewManager.AnonymousClass6 anonymousClass6 = centralSurfacesImpl.mLaunchTransitionCancelRunnable;
            if (anonymousClass6 != null) {
                anonymousClass6.run();
            }
            centralSurfacesImpl.mLaunchTransitionEndRunnable = null;
            centralSurfacesImpl.mLaunchTransitionCancelRunnable = null;
            CentralSurfacesImpl.m882$$Nest$mupdateRevealEffect(centralSurfacesImpl, false);
            centralSurfacesImpl.updateNotificationPanelTouchState();
            CentralSurfacesImpl.m881$$Nest$mmaybeEscalateHeadsUp(centralSurfacesImpl);
            VolumeComponent volumeComponent = centralSurfacesImpl.mVolumeComponent;
            if (volumeComponent != null) {
                ((VolumeDialogComponent) volumeComponent).mController.mCallbacks.onDismissRequested(2);
            }
            centralSurfacesImpl.mWakeUpCoordinator.fullyAwake = false;
            centralSurfacesImpl.mKeyguardBypassController.pendingUnlock = null;
            centralSurfacesImpl.mStatusBarTouchableRegionManager.updateTouchableRegion();
            if (centralSurfacesImpl.mDozeParameters.shouldShowLightRevealScrim()) {
                centralSurfacesImpl.mShadeController.makeExpandedVisible(true);
            }
            DejankUtils.stopDetectingBlockingIpcs("CentralSurfaces#onStartedGoingToSleep");
        }

        @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
        public final void onStartedWakingUp() {
            DejankUtils.startDetectingBlockingIpcs("CentralSurfaces#onStartedWakingUp");
            ((NotificationShadeWindowControllerImpl) CentralSurfacesImpl.this.mNotificationShadeWindowController).batchApplyWindowLayoutParams(new CentralSurfacesImpl$9$$ExternalSyntheticLambda0(this, 2));
            DejankUtils.stopDetectingBlockingIpcs("CentralSurfaces#onStartedWakingUp");
        }

        public final void startLockscreenTransitionFromAod() {
            CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
            DozeServiceHost dozeServiceHost = centralSurfacesImpl.mDozeServiceHost;
            if (dozeServiceHost.mDozingRequested) {
                dozeServiceHost.mDozingRequested = false;
                dozeServiceHost.updateDozing();
                dozeServiceHost.mDozeLog.traceDozing(((StatusBarStateControllerImpl) dozeServiceHost.mStatusBarStateController).mIsDozing);
            }
            CentralSurfacesImpl.m882$$Nest$mupdateRevealEffect(centralSurfacesImpl, true);
            centralSurfacesImpl.updateNotificationPanelTouchState();
            centralSurfacesImpl.mStatusBarTouchableRegionManager.updateTouchableRegion();
            List list = centralSurfacesImpl.mScreenOffAnimationController.animations;
            if (list == null || !list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((ScreenOffAnimation) it.next()).shouldHideScrimOnWakeUp()) {
                        centralSurfacesImpl.mShadeController.makeExpandedInvisible();
                        return;
                    }
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class AnimateExpandSettingsPanelMessage {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public enum StatusBarUiEvent implements UiEventLogger.UiEventEnum {
        /* JADX INFO: Fake field, exist only in values array */
        LOCKSCREEN_OPEN_SECURE(405),
        /* JADX INFO: Fake field, exist only in values array */
        LOCKSCREEN_OPEN_INSECURE(406),
        /* JADX INFO: Fake field, exist only in values array */
        LOCKSCREEN_CLOSE_SECURE(407),
        /* JADX INFO: Fake field, exist only in values array */
        LOCKSCREEN_CLOSE_INSECURE(408),
        /* JADX INFO: Fake field, exist only in values array */
        BOUNCER_OPEN_SECURE(409),
        /* JADX INFO: Fake field, exist only in values array */
        BOUNCER_OPEN_INSECURE(410),
        /* JADX INFO: Fake field, exist only in values array */
        BOUNCER_CLOSE_SECURE(411),
        /* JADX INFO: Fake field, exist only in values array */
        BOUNCER_CLOSE_INSECURE(412);

        private final int mId;

        StatusBarUiEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* renamed from: -$$Nest$mmaybeEscalateHeadsUp, reason: not valid java name */
    public static void m881$$Nest$mmaybeEscalateHeadsUp(CentralSurfacesImpl centralSurfacesImpl) {
        BaseHeadsUpManager baseHeadsUpManager = (BaseHeadsUpManager) centralSurfacesImpl.mHeadsUpManager;
        baseHeadsUpManager.getAllEntries().forEach(new CentralSurfacesImpl$$ExternalSyntheticLambda2(centralSurfacesImpl, 8));
        baseHeadsUpManager.releaseAllImmediately();
    }

    /* renamed from: -$$Nest$mupdateRevealEffect, reason: not valid java name */
    public static void m882$$Nest$mupdateRevealEffect(CentralSurfacesImpl centralSurfacesImpl, boolean z) {
        LightRevealScrim lightRevealScrim = centralSurfacesImpl.mLightRevealScrim;
        if (lightRevealScrim == null) {
            return;
        }
        boolean z2 = false;
        WakefulnessLifecycle wakefulnessLifecycle = centralSurfacesImpl.mWakefulnessLifecycle;
        boolean z3 = z && !(lightRevealScrim.revealEffect instanceof CircleReveal) && wakefulnessLifecycle.mLastWakeReason == 1;
        if (!z && wakefulnessLifecycle.mLastSleepReason == 4) {
            z2 = true;
        }
        SysuiStatusBarStateController sysuiStatusBarStateController = centralSurfacesImpl.mStatusBarStateController;
        if (z3 || z2) {
            lightRevealScrim.setRevealEffect(centralSurfacesImpl.mPowerButtonReveal);
            lightRevealScrim.setRevealAmount(1.0f - ((StatusBarStateControllerImpl) sysuiStatusBarStateController).mDozeAmount);
        } else {
            if (z && (lightRevealScrim.revealEffect instanceof CircleReveal)) {
                return;
            }
            lightRevealScrim.setRevealEffect(LiftReveal.INSTANCE);
            lightRevealScrim.setRevealAmount(1.0f - ((StatusBarStateControllerImpl) sysuiStatusBarStateController).mDozeAmount);
        }
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [com.android.systemui.statusbar.phone.CentralSurfacesImpl$1] */
    /* JADX WARN: Type inference failed for: r6v12, types: [com.android.systemui.statusbar.phone.CentralSurfacesImpl$14] */
    /* JADX WARN: Type inference failed for: r6v15, types: [com.android.systemui.statusbar.phone.CentralSurfacesImpl$17] */
    /* JADX WARN: Type inference failed for: r6v16, types: [com.android.systemui.statusbar.phone.CentralSurfacesImpl$18] */
    /* JADX WARN: Type inference failed for: r6v17, types: [com.android.systemui.statusbar.phone.CentralSurfacesImpl$19] */
    /* JADX WARN: Type inference failed for: r6v19, types: [com.android.systemui.statusbar.phone.CentralSurfacesImpl$21] */
    /* JADX WARN: Type inference failed for: r6v20, types: [com.android.systemui.statusbar.phone.CentralSurfacesImpl$22] */
    /* JADX WARN: Type inference failed for: r6v5, types: [com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r6v9, types: [com.android.systemui.statusbar.phone.CentralSurfacesImpl$10] */
    public CentralSurfacesImpl(Context context, NotificationsController notificationsController, FragmentService fragmentService, LightBarController lightBarController, AutoHideController autoHideController, StatusBarInitializerImpl statusBarInitializerImpl, StatusBarWindowControllerImpl statusBarWindowControllerImpl, StatusBarWindowStateController statusBarWindowStateController, StatusBarModeRepositoryImpl statusBarModeRepositoryImpl, KeyguardUpdateMonitor keyguardUpdateMonitor, StatusBarSignalPolicy statusBarSignalPolicy, PulseExpansionHandler pulseExpansionHandler, NotificationWakeUpCoordinator notificationWakeUpCoordinator, KeyguardBypassController keyguardBypassController, KeyguardStateController keyguardStateController, HeadsUpManager headsUpManager, FalsingManager falsingManager, FalsingCollector falsingCollector, BroadcastDispatcher broadcastDispatcher, NotificationGutsManager notificationGutsManager, ShadeExpansionStateManager shadeExpansionStateManager, KeyguardViewMediator keyguardViewMediator, DisplayMetrics displayMetrics, MetricsLogger metricsLogger, ShadeLogger shadeLogger, JavaAdapter javaAdapter, Executor executor, ShadeSurface shadeSurface, NotificationMediaManager notificationMediaManager, NotificationLockscreenUserManager notificationLockscreenUserManager, NotificationRemoteInputManager notificationRemoteInputManager, QuickSettingsController quickSettingsController, BatteryController batteryController, SysuiColorExtractor sysuiColorExtractor, ScreenLifecycle screenLifecycle, WakefulnessLifecycle wakefulnessLifecycle, PowerInteractor powerInteractor, CommunalInteractor communalInteractor, SysuiStatusBarStateController sysuiStatusBarStateController, Optional optional, Lazy lazy, DeviceProvisionedController deviceProvisionedController, NavigationBarControllerImpl navigationBarControllerImpl, AccessibilityFloatingMenuController accessibilityFloatingMenuController, Lazy lazy2, ConfigurationController configurationController, NotificationShadeWindowController notificationShadeWindowController, Lazy lazy3, NotificationStackScrollLayoutController notificationStackScrollLayoutController, Lazy lazy4, Lazy lazy5, NotificationLaunchAnimatorControllerProvider notificationLaunchAnimatorControllerProvider, DozeParameters dozeParameters, ScrimController scrimController, Lazy lazy6, AuthRippleController authRippleController, DozeServiceHost dozeServiceHost, BackActionInteractor backActionInteractor, PowerManager powerManager, DozeScrimController dozeScrimController, VolumeComponent volumeComponent, CommandQueue commandQueue, Lazy lazy7, PluginManager pluginManager, ShadeController shadeController, WindowRootViewVisibilityInteractor windowRootViewVisibilityInteractor, StatusBarKeyguardViewManager statusBarKeyguardViewManager, KeyguardViewMediator.AnonymousClass4 anonymousClass4, InitController initController, Handler handler, PluginDependencyProvider pluginDependencyProvider, ExtensionControllerImpl extensionControllerImpl, UserInfoControllerImpl userInfoControllerImpl, PhoneStatusBarPolicy phoneStatusBarPolicy, KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle, DemoModeController demoModeController, Lazy lazy8, StatusBarTouchableRegionManager statusBarTouchableRegionManager, BrightnessSliderController.Factory factory, ScreenOffAnimationController screenOffAnimationController, WallpaperController wallpaperController, StatusBarHideIconsForBouncerManager statusBarHideIconsForBouncerManager, LockscreenShadeTransitionController lockscreenShadeTransitionController, FeatureFlagsClassic featureFlagsClassic, KeyguardUnlockAnimationController keyguardUnlockAnimationController, DelayableExecutor delayableExecutor, MessageRouterImpl messageRouterImpl, WallpaperManager wallpaperManager, Optional optional2, ActivityTransitionAnimator activityTransitionAnimator, DeviceStateManager deviceStateManager, final WiredChargingRippleController wiredChargingRippleController, IDreamManager iDreamManager, Lazy lazy9, Lazy lazy10, LightRevealScrim lightRevealScrim, AlternateBouncerInteractor alternateBouncerInteractor, UserTracker userTracker, Provider provider, ActivityStarter activityStarter, BrightnessMirrorShowingInteractor brightnessMirrorShowingInteractor, GlanceableHubContainerController glanceableHubContainerController, EmergencyGestureModule$emergencyGestureIntentFactory$1 emergencyGestureModule$emergencyGestureIntentFactory$1, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        this.mContext = context;
        this.mNotificationsController = notificationsController;
        this.mFragmentService = fragmentService;
        this.mLightBarController = lightBarController;
        this.mAutoHideController = autoHideController;
        this.mStatusBarInitializer = statusBarInitializerImpl;
        this.mStatusBarWindowController = statusBarWindowControllerImpl;
        this.mStatusBarModeRepository = statusBarModeRepositoryImpl;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mPulseExpansionHandler = pulseExpansionHandler;
        this.mWakeUpCoordinator = notificationWakeUpCoordinator;
        this.mKeyguardBypassController = keyguardBypassController;
        this.mKeyguardStateController = keyguardStateController;
        this.mHeadsUpManager = headsUpManager;
        this.mBackActionInteractor = backActionInteractor;
        this.mKeyguardIndicationController = keyguardIndicationControllerGoogle;
        this.mStatusBarTouchableRegionManager = statusBarTouchableRegionManager;
        this.mFalsingCollector = falsingCollector;
        this.mFalsingManager = falsingManager;
        this.mBroadcastDispatcher = broadcastDispatcher;
        this.mGutsManager = notificationGutsManager;
        this.mShadeExpansionStateManager = shadeExpansionStateManager;
        this.mKeyguardViewMediator = keyguardViewMediator;
        this.mDisplayMetrics = displayMetrics;
        this.mMetricsLogger = metricsLogger;
        this.mShadeLogger = shadeLogger;
        this.mJavaAdapter = javaAdapter;
        this.mUiBgExecutor = executor;
        this.mShadeSurface = shadeSurface;
        this.mMediaManager = notificationMediaManager;
        this.mLockscreenUserManager = notificationLockscreenUserManager;
        this.mRemoteInputManager = notificationRemoteInputManager;
        this.mQsController = quickSettingsController;
        this.mBatteryController = batteryController;
        this.mColorExtractor = sysuiColorExtractor;
        this.mScreenLifecycle = screenLifecycle;
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        this.mPowerInteractor = powerInteractor;
        this.mCommunalInteractor = communalInteractor;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mBubblesOptional = optional;
        this.mNoteTaskControllerLazy = lazy;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mNavigationBarController = navigationBarControllerImpl;
        this.mAccessibilityFloatingMenuController = accessibilityFloatingMenuController;
        this.mAssistManagerLazy = lazy2;
        this.mConfigurationController = configurationController;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mNotificationShadeWindowViewControllerLazy = lazy3;
        this.mStackScrollerController = notificationStackScrollLayoutController;
        this.mStackScroller = notificationStackScrollLayoutController.mView;
        this.mNotifListContainer = notificationStackScrollLayoutController.mNotificationListContainer;
        this.mPresenterLazy = lazy4;
        this.mNotificationActivityStarterLazy = lazy5;
        this.mNotificationAnimationProvider = notificationLaunchAnimatorControllerProvider;
        this.mDozeServiceHost = dozeServiceHost;
        this.mPowerManager = powerManager;
        this.mDozeParameters = dozeParameters;
        this.mScrimController = scrimController;
        this.mDozeScrimController = dozeScrimController;
        this.mBiometricUnlockControllerLazy = lazy6;
        this.mAuthRippleController = authRippleController;
        this.mNotificationShadeDepthControllerLazy = lazy8;
        this.mVolumeComponent = volumeComponent;
        this.mCommandQueue = commandQueue;
        this.mCommandQueueCallbacksLazy = lazy7;
        this.mPluginManager = pluginManager;
        this.mShadeController = shadeController;
        this.mWindowRootViewVisibilityInteractor = windowRootViewVisibilityInteractor;
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.mKeyguardViewMediatorCallback = anonymousClass4;
        this.mInitController = initController;
        this.mPluginDependencyProvider = pluginDependencyProvider;
        this.mExtensionController = extensionControllerImpl;
        this.mUserInfoControllerImpl = userInfoControllerImpl;
        this.mIconPolicy = phoneStatusBarPolicy;
        this.mDemoModeController = demoModeController;
        this.mBrightnessSliderFactory = factory;
        this.mWallpaperController = wallpaperController;
        this.mStatusBarSignalPolicy = statusBarSignalPolicy;
        this.mStatusBarHideIconsForBouncerManager = statusBarHideIconsForBouncerManager;
        this.mFeatureFlags = featureFlagsClassic;
        this.mKeyguardUnlockAnimationController = keyguardUnlockAnimationController;
        this.mMainExecutor = delayableExecutor;
        this.mMessageRouter = messageRouterImpl;
        this.mWallpaperManager = wallpaperManager;
        this.mCameraLauncherLazy = lazy9;
        this.mAlternateBouncerInteractor = alternateBouncerInteractor;
        this.mUserTracker = userTracker;
        this.mActivityStarter = activityStarter;
        this.mGlanceableHubContainerController = glanceableHubContainerController;
        this.mEmergencyGestureIntentFactory = emergencyGestureModule$emergencyGestureIntentFactory$1;
        this.mLockscreenShadeTransitionController = lockscreenShadeTransitionController;
        this.mStartingSurfaceOptional = optional2;
        this.mDreamManager = iDreamManager;
        lockscreenShadeTransitionController.centralSurfaces = this;
        statusBarWindowStateController.listeners.add(new StatusBarWindowStateListener() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda5
            @Override // com.android.systemui.statusbar.window.StatusBarWindowStateListener
            public final void onStatusBarWindowStateChanged(int i) {
                CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
                centralSurfacesImpl.mStatusBarWindowState = i;
                centralSurfacesImpl.mBubblesOptional.ifPresent(new CentralSurfacesImpl$$ExternalSyntheticLambda16(centralSurfacesImpl, (StatusBarMode) centralSurfacesImpl.mStatusBarModeRepository.defaultDisplay.statusBarMode.getValue()));
            }
        });
        this.mScreenOffAnimationController = screenOffAnimationController;
        ShadeExpansionListener shadeExpansionListener = new ShadeExpansionListener() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda6
            @Override // com.android.systemui.shade.ShadeExpansionListener
            public final void onPanelExpansionChanged(ShadeExpansionChangeEvent shadeExpansionChangeEvent) {
                CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
                centralSurfacesImpl.getClass();
                KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) centralSurfacesImpl.mKeyguardStateController;
                boolean z = keyguardStateControllerImpl.mShowing;
                float f = shadeExpansionChangeEvent.fraction;
                ShadeSurface shadeSurface2 = centralSurfacesImpl.mShadeSurface;
                if (z && !centralSurfacesImpl.mStatusBarKeyguardViewManager.primaryBouncerIsOrWillBeShowing() && !keyguardStateControllerImpl.mOccluded && keyguardStateControllerImpl.mCanDismissLockScreen) {
                    int i = 0;
                    while (true) {
                        KeyguardViewMediator keyguardViewMediator2 = centralSurfacesImpl.mKeyguardViewMediator;
                        if (i < keyguardViewMediator2.mLastSimStates.size()) {
                            int i2 = keyguardViewMediator2.mLastSimStates.get(keyguardViewMediator2.mLastSimStates.keyAt(i));
                            int i3 = KeyguardUpdateMonitor.BIOMETRIC_HELP_FINGERPRINT_NOT_RECOGNIZED;
                            if (i2 == 2 || i2 == 3 || i2 == 7) {
                                break;
                            } else {
                                i++;
                            }
                        } else {
                            boolean expanded = centralSurfacesImpl.mQsController.getExpanded();
                            boolean z2 = shadeExpansionChangeEvent.tracking;
                            if ((!expanded || !z2) && shadeSurface2.getBarState() != 2) {
                                float f2 = 1.0f - f;
                                if (f2 != 0.0f && (z2 || keyguardViewMediator2.isAnimatingBetweenKeyguardAndSurfaceBehindOrWillBe() || centralSurfacesImpl.mKeyguardUnlockAnimationController.willUnlockWithSmartspaceTransition)) {
                                    keyguardStateControllerImpl.mDismissAmount = f2;
                                    keyguardStateControllerImpl.mDismissingFromTouch = z2;
                                    keyguardStateControllerImpl.invokeForEachCallback(new KeyguardStateControllerImpl$$ExternalSyntheticLambda0(4));
                                }
                            }
                        }
                    }
                }
                if (f == 0.0f || f == 1.0f) {
                    if (centralSurfacesImpl.getNavigationBarView() != null) {
                        centralSurfacesImpl.getNavigationBarView().updateSlippery();
                    }
                    if (shadeSurface2 != null) {
                        shadeSurface2.updateSystemUiStateFlags();
                    }
                }
            }
        };
        shadeExpansionListener.onPanelExpansionChanged(shadeExpansionStateManager.addExpansionListener(shadeExpansionListener));
        context.getPackageManager();
        this.mActivityTransitionAnimator = activityTransitionAnimator;
        DateTimeView.setReceiverHandler(handler);
        messageRouterImpl.subscribeTo(CentralSurfaces.KeyboardShortcutsMessage.class, new CentralSurfacesImpl$$ExternalSyntheticLambda7(this, 0));
        messageRouterImpl.subscribeTo(1027, new CentralSurfacesImpl$$ExternalSyntheticLambda8(this, 0));
        messageRouterImpl.subscribeTo(AnimateExpandSettingsPanelMessage.class, new CentralSurfacesImpl$$ExternalSyntheticLambda7(this, 1));
        messageRouterImpl.subscribeTo(1003, new CentralSurfacesImpl$$ExternalSyntheticLambda8(this, 1));
        this.mDeviceStateManager = deviceStateManager;
        wiredChargingRippleController.getClass();
        wiredChargingRippleController.batteryController.addCallback(new BatteryController.BatteryStateChangeCallback() { // from class: com.android.systemui.charging.WiredChargingRippleController$registerCallbacks$batteryStateChangeCallback$1
            @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
            public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
                WiredChargingRippleController wiredChargingRippleController2 = WiredChargingRippleController.this;
                BatteryController batteryController2 = wiredChargingRippleController2.batteryController;
                if (((BatteryControllerImpl) batteryController2).mPluggedChargingSource == 4 || ((BatteryControllerImpl) batteryController2).mPluggedChargingSource == 8) {
                    return;
                }
                if (!wiredChargingRippleController2.pluggedIn && z) {
                    ((SystemClockImpl) wiredChargingRippleController2.systemClock).getClass();
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (wiredChargingRippleController2.lastTriggerTime != null) {
                        if (elapsedRealtime - r11.longValue() <= Math.pow(2.0d, wiredChargingRippleController2.debounceLevel) * 2000) {
                            wiredChargingRippleController2.debounceLevel = Math.min(3, wiredChargingRippleController2.debounceLevel + 1);
                            wiredChargingRippleController2.lastTriggerTime = Long.valueOf(elapsedRealtime);
                        }
                    }
                    wiredChargingRippleController2.startRipple();
                    wiredChargingRippleController2.debounceLevel = 0;
                    wiredChargingRippleController2.lastTriggerTime = Long.valueOf(elapsedRealtime);
                }
                wiredChargingRippleController2.pluggedIn = z;
            }
        });
        ((ConfigurationControllerImpl) wiredChargingRippleController.configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.charging.WiredChargingRippleController$registerCallbacks$configurationChangedListener$1
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                WiredChargingRippleController wiredChargingRippleController2 = WiredChargingRippleController.this;
                wiredChargingRippleController2.normalizedPortPosX = wiredChargingRippleController2.context.getResources().getFloat(R.dimen.physical_charger_port_location_normalized_x);
                wiredChargingRippleController2.normalizedPortPosY = wiredChargingRippleController2.context.getResources().getFloat(R.dimen.physical_charger_port_location_normalized_y);
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                WiredChargingRippleController.this.updateRippleColor();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onUiModeChanged() {
                WiredChargingRippleController.this.updateRippleColor();
            }
        });
        this.mLightRevealScrim = lightRevealScrim;
        this.mViewCaptureAwareWindowManager = viewCaptureAwareWindowManager;
        context.getApplicationInfo().setEnableOnBackInvokedCallback(true);
    }

    public final void awakenDreams() {
        this.mUiBgExecutor.execute(new CentralSurfacesImpl$$ExternalSyntheticLambda1(this, 0));
    }

    public final void checkBarModes$1() {
        if (this.mDemoModeController.isInDemoMode) {
            return;
        }
        if (this.mStatusBarTransitions != null) {
            StatusBarMode statusBarMode = (StatusBarMode) ((StateFlowImpl) this.mStatusBarModeRepository.defaultDisplay.statusBarMode.$$delegate_0).getValue();
            int i = this.mStatusBarWindowState;
            PhoneStatusBarTransitions phoneStatusBarTransitions = this.mStatusBarTransitions;
            boolean z = (this.mNoAnimationOnNextBarModeChange || !this.mDeviceInteractive || i == 2) ? false : true;
            int transitionModeInt = statusBarMode.toTransitionModeInt();
            int i2 = phoneStatusBarTransitions.mMode;
            if (i2 != transitionModeInt) {
                phoneStatusBarTransitions.mMode = transitionModeInt;
                phoneStatusBarTransitions.onTransition(i2, transitionModeInt, z);
            }
        }
        this.mNavigationBarController.checkNavBarModes(this.mDisplayId);
        this.mNoAnimationOnNextBarModeChange = false;
    }

    @Override // com.android.systemui.CoreStartable, com.android.systemui.Dumpable
    @NeverCompile
    public final void dump(PrintWriter printWriter, String[] strArr) {
        PrintWriter asIndenting = DumpUtilsKt.asIndenting(printWriter);
        synchronized (this.mQueueLock) {
            asIndenting.println("Current Status Bar state:");
            asIndenting.println("  mExpandedVisible=" + this.mShadeController.isExpandedVisible());
            asIndenting.println("  mDisplayMetrics=" + this.mDisplayMetrics);
            asIndenting.print("  mStackScroller: " + CentralSurfaces.viewInfo(this.mStackScroller));
            asIndenting.print(" scroll " + this.mStackScroller.getScrollX() + "," + this.mStackScroller.getScrollY());
            StringBuilder sb = new StringBuilder(" translationX ");
            sb.append(this.mStackScroller.getTranslationX());
            asIndenting.println(sb.toString());
        }
        asIndenting.print("  mInteractingWindows=");
        asIndenting.println(this.mInteractingWindows);
        asIndenting.print("  mStatusBarWindowState=");
        asIndenting.println(StatusBarManager.windowStateToString(this.mStatusBarWindowState));
        asIndenting.print("  mDozing=");
        asIndenting.println(this.mDozing);
        asIndenting.print("  mWallpaperSupported= ");
        asIndenting.println(this.mWallpaperSupported);
        CentralSurfaces.dumpBarTransitions(asIndenting, "PhoneStatusBarTransitions", this.mStatusBarTransitions);
        asIndenting.println("  mMediaManager: ");
        NotificationMediaManager notificationMediaManager = this.mMediaManager;
        if (notificationMediaManager != null) {
            notificationMediaManager.dump(asIndenting, strArr);
        }
        asIndenting.println("  Panels: ");
        asIndenting.println("  mStackScroller: " + this.mStackScroller + " (dump moved)");
        asIndenting.println("  Theme:");
        asIndenting.println("    dark theme: " + (this.mUiModeManager == null ? "null" : this.mUiModeManager.getNightMode() + "") + " (auto: 0, yes: 2, no: 1)");
        asIndenting.println("    light wallpaper theme: " + (this.mContext.getThemeResId() == 2132018487));
        KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.mKeyguardIndicationController;
        if (keyguardIndicationControllerGoogle != null) {
            asIndenting.println("KeyguardIndicationController:");
            asIndenting.println("  mInitialTextColorState: " + keyguardIndicationControllerGoogle.mInitialTextColorState);
            asIndenting.println("  mPowerPluggedInWired: " + keyguardIndicationControllerGoogle.mPowerPluggedInWired);
            asIndenting.println("  mPowerPluggedIn: " + keyguardIndicationControllerGoogle.mPowerPluggedIn);
            asIndenting.println("  mPowerCharged: " + keyguardIndicationControllerGoogle.mPowerCharged);
            asIndenting.println("  mChargingSpeed: " + keyguardIndicationControllerGoogle.mChargingSpeed);
            asIndenting.println("  mChargingWattage: " + keyguardIndicationControllerGoogle.mChargingWattage);
            asIndenting.println("  mMessageToShowOnScreenOn: " + keyguardIndicationControllerGoogle.mBiometricErrorMessageToShowOnScreenOn);
            asIndenting.println("  mDozing: " + keyguardIndicationControllerGoogle.mDozing);
            asIndenting.println("  mTransientIndication: " + ((Object) keyguardIndicationControllerGoogle.mTransientIndication));
            asIndenting.println("  mBiometricMessage: " + ((Object) keyguardIndicationControllerGoogle.mBiometricMessage));
            asIndenting.println("  mBiometricMessageFollowUp: " + ((Object) keyguardIndicationControllerGoogle.mBiometricMessageFollowUp));
            asIndenting.println("  mBatteryLevel: " + ((KeyguardIndicationController) keyguardIndicationControllerGoogle).mBatteryLevel);
            asIndenting.println("  mBatteryPresent: " + keyguardIndicationControllerGoogle.mBatteryPresent);
            asIndenting.println("  mIsActiveDreamLockscreenHosted: " + keyguardIndicationControllerGoogle.mIsActiveDreamLockscreenHosted);
            StringBuilder sb2 = new StringBuilder("  AOD text: ");
            KeyguardIndicationTextView keyguardIndicationTextView = keyguardIndicationControllerGoogle.mTopIndicationView;
            sb2.append((Object) (keyguardIndicationTextView != null ? keyguardIndicationTextView.getText() : null));
            asIndenting.println(sb2.toString());
            asIndenting.println("  computePowerIndication(): " + keyguardIndicationControllerGoogle.computePowerIndication());
            asIndenting.println("  trustGrantedIndication: " + keyguardIndicationControllerGoogle.getTrustGrantedIndication());
            asIndenting.println("    mCoExFaceHelpMsgIdsToShow=" + keyguardIndicationControllerGoogle.mCoExFaceAcquisitionMsgIdsToShow);
            keyguardIndicationControllerGoogle.mRotateTextViewController.dump(asIndenting, strArr);
            asIndenting.println("KeyguardIndicationControllerGoogle:");
            asIndenting.println("\tisChargingStringV2Enabled: " + keyguardIndicationControllerGoogle.isChargingStringV2Enabled());
        }
        ScrimController scrimController = this.mScrimController;
        if (scrimController != null) {
            scrimController.dump(asIndenting, strArr);
        }
        if (this.mLightRevealScrim != null) {
            asIndenting.println("mLightRevealScrim.getRevealEffect(): " + this.mLightRevealScrim.revealEffect);
            asIndenting.println("mLightRevealScrim.getRevealAmount(): " + this.mLightRevealScrim.revealAmount);
        }
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.mStatusBarKeyguardViewManager;
        if (statusBarKeyguardViewManager != null) {
            asIndenting.println("StatusBarKeyguardViewManager:");
            asIndenting.println("  mRemoteInputActive: " + statusBarKeyguardViewManager.mRemoteInputActive);
            asIndenting.println("  mDozing: " + statusBarKeyguardViewManager.mDozing);
            asIndenting.println("  mAfterKeyguardGoneAction: " + statusBarKeyguardViewManager.mAfterKeyguardGoneAction);
            asIndenting.println("  mAfterKeyguardGoneRunnables: " + statusBarKeyguardViewManager.mAfterKeyguardGoneRunnables);
            asIndenting.println("  mPendingWakeupAction: " + statusBarKeyguardViewManager.mPendingWakeupAction);
            asIndenting.println("  isBouncerShowing(): " + statusBarKeyguardViewManager.isBouncerShowing());
            asIndenting.println("  bouncerIsOrWillBeShowing(): " + statusBarKeyguardViewManager.primaryBouncerIsOrWillBeShowing());
            asIndenting.println("  Registered KeyguardViewManagerCallbacks:");
            asIndenting.println(" SceneContainerFlag enabled:false");
            asIndenting.println(" ComposeBouncerFlags enabled:false");
            Iterator it = ((HashSet) statusBarKeyguardViewManager.mCallbacks).iterator();
            while (it.hasNext()) {
                asIndenting.println("      " + ((UdfpsKeyguardViewControllerLegacy$statusBarKeyguardViewManagerCallback$1) it.next()));
            }
            UdfpsKeyguardViewControllerLegacy$occludingAppBiometricUI$1 udfpsKeyguardViewControllerLegacy$occludingAppBiometricUI$1 = statusBarKeyguardViewManager.mOccludingAppBiometricUI;
        }
        HeadsUpManager headsUpManager = this.mHeadsUpManager;
        if (headsUpManager != null) {
            ((HeadsUpManagerPhone) headsUpManager).dump(asIndenting, strArr);
        } else {
            asIndenting.println("  mHeadsUpManager: null");
        }
        StatusBarTouchableRegionManager statusBarTouchableRegionManager = this.mStatusBarTouchableRegionManager;
        if (statusBarTouchableRegionManager != null) {
            statusBarTouchableRegionManager.dump(asIndenting, strArr);
        } else {
            asIndenting.println("  mStatusBarTouchableRegionManager: null");
        }
        LightBarController lightBarController = this.mLightBarController;
        if (lightBarController != null) {
            lightBarController.dump(asIndenting, strArr);
        }
        asIndenting.println("SharedPreferences:");
        for (Map.Entry<String, ?> entry : Prefs.get(this.mContext).getAll().entrySet()) {
            asIndenting.print("  ");
            asIndenting.print(entry.getKey());
            asIndenting.print("=");
            asIndenting.println(entry.getValue());
        }
        asIndenting.println("Camera gesture intents:");
        StringBuilder sb3 = new StringBuilder("   Insecure camera: ");
        Context context = this.mContext;
        int userId = ((UserTrackerImpl) this.mUserTracker).getUserId();
        Intent intent = new Intent("android.media.action.STILL_IMAGE_CAMERA");
        String overrideCameraPackage = CameraIntents$Companion.getOverrideCameraPackage(userId, context);
        if (overrideCameraPackage != null) {
            intent.setPackage(overrideCameraPackage);
        }
        sb3.append(intent);
        asIndenting.println(sb3.toString());
        StringBuilder sb4 = new StringBuilder("   Secure camera: ");
        Context context2 = this.mContext;
        int userId2 = ((UserTrackerImpl) this.mUserTracker).getUserId();
        Intent intent2 = new Intent("android.media.action.STILL_IMAGE_CAMERA_SECURE");
        String overrideCameraPackage2 = CameraIntents$Companion.getOverrideCameraPackage(userId2, context2);
        if (overrideCameraPackage2 != null) {
            intent2.setPackage(overrideCameraPackage2);
        }
        sb4.append(intent2.addFlags(8388608));
        asIndenting.println(sb4.toString());
        asIndenting.println("   Override package: " + CameraIntents$Companion.getOverrideCameraPackage(((UserTrackerImpl) this.mUserTracker).getUserId(), this.mContext));
    }

    public final void finishKeyguardFadingAway() {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        keyguardStateControllerImpl.notifyKeyguardGoingAway(false);
        if (keyguardStateControllerImpl.mKeyguardFadingAway) {
            Trace.traceCounter(4096L, "keyguardFadingAway", 0);
            keyguardStateControllerImpl.mKeyguardFadingAway = false;
            keyguardStateControllerImpl.invokeForEachCallback(new KeyguardStateControllerImpl$$ExternalSyntheticLambda0(0));
        }
        this.mScrimController.mExpansionAffectsAlpha = true;
        this.mKeyguardViewMediator.maybeHandlePendingLock();
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.mLifecycle;
    }

    public final NavigationBarView getNavigationBarView() {
        return this.mNavigationBarController.getNavigationBarView(this.mDisplayId);
    }

    public final NotificationShadeWindowViewController getNotificationShadeWindowViewController() {
        return (NotificationShadeWindowViewController) this.mNotificationShadeWindowViewControllerLazy.get();
    }

    public final boolean hideKeyguard() {
        ((StatusBarStateControllerImpl) this.mStatusBarStateController).mKeyguardRequested = false;
        return updateIsKeyguard(false);
    }

    @Deprecated
    public void initShadeVisibilityListener() {
        this.mShadeController.setVisibilityListener(new AnonymousClass4());
    }

    public final boolean isGoingToSleep() {
        return this.mWakefulnessLifecycle.mWakefulness == 3;
    }

    public final void logStateToEventlog() {
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        boolean z = keyguardStateControllerImpl.mShowing;
        boolean z2 = keyguardStateControllerImpl.mOccluded;
        boolean isBouncerShowing = this.mStatusBarKeyguardViewManager.isBouncerShowing();
        boolean z3 = keyguardStateControllerImpl.mSecure;
        boolean z4 = keyguardStateControllerImpl.mCanDismissLockScreen;
        int i = (this.mState & 255) | ((z ? 1 : 0) << 8) | ((z2 ? 1 : 0) << 9) | ((isBouncerShowing ? 1 : 0) << 10) | ((z3 ? 1 : 0) << 11) | ((z4 ? 1 : 0) << 12);
        if (i != this.mLastLoggedStateFingerprint) {
            if (this.mStatusBarStateLog == null) {
                this.mStatusBarStateLog = new LogMaker(0);
            }
            this.mMetricsLogger.write(this.mStatusBarStateLog.setCategory(isBouncerShowing ? 197 : 196).setType(z ? 1 : 2).setSubtype(z3 ? 1 : 0));
            EventLog.writeEvent(36004, Integer.valueOf(this.mState), Integer.valueOf(z ? 1 : 0), Integer.valueOf(z2 ? 1 : 0), Integer.valueOf(isBouncerShowing ? 1 : 0), Integer.valueOf(z3 ? 1 : 0), Integer.valueOf(z4 ? 1 : 0));
            this.mLastLoggedStateFingerprint = i;
            StringBuilder sb = new StringBuilder();
            sb.append(isBouncerShowing ? "BOUNCER" : "LOCKSCREEN");
            sb.append(z ? "_OPEN" : "_CLOSE");
            sb.append(z3 ? "_SECURE" : "_INSECURE");
            sUiEventLogger.log(StatusBarUiEvent.valueOf(sb.toString()));
        }
    }

    public final void notifyBiometricAuthModeChanged() {
        this.mDozeServiceHost.updateDozing();
        if (this.mBiometricUnlockController.mMode == 7) {
            return;
        }
        updateScrimController();
    }

    public final void onLaunchTransitionFadingEnded() {
        this.mShadeSurface.resetAlpha();
        ((CameraLauncher) this.mCameraLauncherLazy.get()).mKeyguardBypassController.launchingAffordance = false;
        releaseGestureWakeLock();
        this.mLaunchTransitionCancelRunnable = null;
        StatusBarKeyguardViewManager.AnonymousClass6 anonymousClass6 = this.mLaunchTransitionEndRunnable;
        if (anonymousClass6 != null) {
            this.mLaunchTransitionEndRunnable = null;
            anonymousClass6.run();
        }
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        keyguardStateControllerImpl.mLaunchTransitionFadingAway = false;
        keyguardStateControllerImpl.invokeForEachCallback(new KeyguardStateControllerImpl$$ExternalSyntheticLambda0(6));
    }

    public void registerBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        this.mBroadcastDispatcher.registerReceiver(this.mBroadcastReceiver, intentFilter, null, UserHandle.ALL);
    }

    public void registerCallbacks() {
        this.mDeviceStateManager.registerCallback(this.mMainExecutor, new FoldStateListener(this.mContext, new CentralSurfacesImpl$$ExternalSyntheticLambda0(this)));
        this.mJavaAdapter.alwaysCollectFlow(this.mCommunalInteractor.isIdleOnCommunal, this.mIdleOnCommunalConsumer);
    }

    public final void releaseGestureWakeLock() {
        if (this.mGestureWakeLock.isHeld()) {
            this.mGestureWakeLock.release();
        }
    }

    @Override // com.android.systemui.statusbar.phone.CentralSurfaces
    public void setBarStateForTest(int i) {
        this.mState = i;
    }

    public final void setInteracting(int i, boolean z) {
        int i2;
        if (z) {
            i2 = i | this.mInteractingWindows;
        } else {
            i2 = (~i) & this.mInteractingWindows;
        }
        this.mInteractingWindows = i2;
        AutoHideController$$ExternalSyntheticLambda0 autoHideController$$ExternalSyntheticLambda0 = null;
        AutoHideController autoHideController = this.mAutoHideController;
        if (i2 != 0) {
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
        } else if (autoHideController.mAutoHideSuspended) {
            autoHideController.mAutoHideSuspended = false;
            AutoHideController$$ExternalSyntheticLambda0 autoHideController$$ExternalSyntheticLambda03 = autoHideController.mAutoHide;
            Handler handler2 = autoHideController.mHandler;
            handler2.removeCallbacks(autoHideController$$ExternalSyntheticLambda03);
            handler2.postDelayed(autoHideController$$ExternalSyntheticLambda03, autoHideController.mAccessibilityManager.getRecommendedTimeoutMillis(2250, 4));
            if (autoHideController.mStatusBar != null) {
                autoHideController$$ExternalSyntheticLambda0 = new AutoHideController$$ExternalSyntheticLambda0(autoHideController, 1);
            } else if (autoHideController.mNavigationBar != null) {
                autoHideController$$ExternalSyntheticLambda0 = new AutoHideController$$ExternalSyntheticLambda0(autoHideController, 2);
            }
            if (autoHideController$$ExternalSyntheticLambda0 != null) {
                handler2.postDelayed(autoHideController$$ExternalSyntheticLambda0, 500L);
            }
        }
        checkBarModes$1();
    }

    public final void setIsLaunchingActivityOverLockscreen(boolean z, boolean z2) {
        this.mIsLaunchingActivityOverLockscreen = z;
        this.mDismissingShadeForActivityLaunch = z2;
        this.mKeyguardViewMediator.mKeyguardTransitions.setLaunchingActivityOverLockscreen(z);
    }

    public final void setPrimaryBouncerHiddenFraction(float f) {
        ScrimController scrimController = this.mScrimController;
        if (scrimController.mBouncerHiddenFraction == f) {
            return;
        }
        scrimController.mBouncerHiddenFraction = f;
        ScrimState scrimState = scrimController.mState;
        if (scrimState == ScrimState.DREAMING || scrimState == ScrimState.GLANCEABLE_HUB || scrimState == ScrimState.GLANCEABLE_HUB_OVER_DREAM) {
            scrimController.applyAndDispatchState();
        }
    }

    public final boolean shouldIgnoreTouch() {
        if (!((StatusBarStateControllerImpl) this.mStatusBarStateController).mIsDozing || !this.mDozeServiceHost.mIgnoreTouchWhilePulsing) {
            List list = this.mScreenOffAnimationController.animations;
            if (list == null || !list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (((ScreenOffAnimation) it.next()).isAnimationPlaying()) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final boolean shouldUseTabletKeyboardShortcuts() {
        return ((FeatureFlagsClassicRelease) this.mFeatureFlags).isEnabled(Flags.SHORTCUT_LIST_SEARCH_LAYOUT) && Utilities.isLargeScreen(this.mContext);
    }

    public final void showChargingAnimation(int i, int i2, long j) {
        Context context = this.mContext;
        AnonymousClass4 anonymousClass4 = new AnonymousClass4();
        RippleShader.RippleShape rippleShape = RippleShader.RippleShape.CIRCLE;
        WirelessChargingAnimation.WirelessChargingView wirelessChargingView = new WirelessChargingAnimation(context, i2, i, anonymousClass4, sUiEventLogger, this.mViewCaptureAwareWindowManager).mCurrentWirelessChargingView;
        if (wirelessChargingView == null || wirelessChargingView.mNextView == null) {
            throw new RuntimeException("setView must have been called");
        }
        WirelessChargingAnimation.WirelessChargingView wirelessChargingView2 = WirelessChargingAnimation.mPreviousWirelessChargingView;
        if (wirelessChargingView2 != null) {
            wirelessChargingView2.hide(0L);
        }
        WirelessChargingAnimation.mPreviousWirelessChargingView = wirelessChargingView;
        if (WirelessChargingAnimation.DEBUG) {
            Slog.d("WirelessChargingView", "SHOW: " + wirelessChargingView);
        }
        WirelessChargingAnimation.WirelessChargingView.AnonymousClass1 anonymousClass1 = wirelessChargingView.mHandler;
        anonymousClass1.sendMessageDelayed(Message.obtain(anonymousClass1, 0), j);
        wirelessChargingView.hide(j + 1500);
    }

    public final void showKeyguard() {
        StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) this.mStatusBarStateController;
        statusBarStateControllerImpl.mKeyguardRequested = true;
        statusBarStateControllerImpl.mLeaveOpenOnKeyguardHide = false;
        updateIsKeyguard(false);
        final AssistManagerGoogle assistManagerGoogle = (AssistManagerGoogle) this.mAssistManagerLazy.get();
        assistManagerGoogle.getClass();
        AsyncTask.execute(new Runnable() { // from class: com.android.systemui.assist.AssistManager$6
            @Override // java.lang.Runnable
            public final void run() {
                AssistManagerGoogle.this.mAssistUtils.onLockscreenShown();
            }
        });
    }

    @Override // com.android.systemui.CoreStartable
    public void start() {
        RegisterStatusBarResult registerStatusBarResult;
        int i;
        boolean z;
        int i2 = 4;
        final int i3 = 3;
        final int i4 = 0;
        final int i5 = 2;
        this.mScreenLifecycle.addObserver(this.mScreenObserver);
        this.mWakefulnessLifecycle.addObserver(this.mWakefulnessObserver);
        this.mUiModeManager = (UiModeManager) this.mContext.getSystemService(UiModeManager.class);
        final int i6 = 1;
        this.mBubblesOptional.ifPresent(new CentralSurfacesImpl$$ExternalSyntheticLambda2(this, i6));
        KeyguardBypassController keyguardBypassController = this.mKeyguardBypassController;
        keyguardBypassController.getClass();
        CoroutineTracingKt.launch$default(keyguardBypassController.applicationScope, null, new KeyguardBypassController$listenForQsExpandedChange$1(keyguardBypassController, null), 6);
        StatusBarSignalPolicy statusBarSignalPolicy = this.mStatusBarSignalPolicy;
        if (!statusBarSignalPolicy.mInitialized) {
            statusBarSignalPolicy.mInitialized = true;
            statusBarSignalPolicy.mTunerService.addTunable(statusBarSignalPolicy, "icon_blacklist");
            ((NetworkControllerImpl) statusBarSignalPolicy.mNetworkController).addCallback(statusBarSignalPolicy);
            ((SecurityControllerImpl) statusBarSignalPolicy.mSecurityController).addCallback(statusBarSignalPolicy);
        }
        this.mKeyguardIndicationController.init();
        this.mColorExtractor.addOnColorsChangedListener(this.mOnColorsChangedListener);
        Display display = this.mContext.getDisplay();
        this.mDisplay = display;
        this.mDisplayId = display.getDisplayId();
        this.mDisplay.getMetrics(this.mDisplayMetrics);
        this.mDisplay.getSize(this.mCurrentDisplaySize);
        this.mStatusBarHideIconsForBouncerManager.displayId = this.mDisplayId;
        initShadeVisibilityListener();
        WindowManagerGlobal.getWindowManagerService();
        this.mKeyguardUpdateMonitor.mKeyguardBypassController = this.mKeyguardBypassController;
        this.mBarService = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
        this.mWallpaperSupported = this.mWallpaperManager.isWallpaperSupported();
        try {
            registerStatusBarResult = this.mBarService.registerStatusBar(this.mCommandQueue);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            registerStatusBarResult = null;
        }
        this.mDisplay.getMetrics(this.mDisplayMetrics);
        this.mDisplay.getSize(this.mCurrentDisplaySize);
        updateResources();
        updateTheme();
        final NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl = (NotificationShadeWindowControllerImpl) this.mNotificationShadeWindowController;
        NotificationShadeWindowView notificationShadeWindowView = (NotificationShadeWindowView) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) notificationShadeWindowControllerImpl.mWindowRootViewComponentFactory.sysUIGoogleSysUIComponentImpl).providesWindowRootViewProvider.get();
        notificationShadeWindowControllerImpl.mWindowRootView = notificationShadeWindowView;
        Lazy lazy = notificationShadeWindowControllerImpl.mShadeInteractorLazy;
        JavaAdapterKt.collectFlow(notificationShadeWindowView, ((ShadeInteractorImpl) ((ShadeInteractor) lazy.get())).baseShadeInteractor.isAnyExpanded(), new Consumer() { // from class: com.android.systemui.shade.NotificationShadeWindowControllerImpl$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i7 = i4;
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl2 = notificationShadeWindowControllerImpl;
                Boolean bool = (Boolean) obj;
                switch (i7) {
                    case 0:
                        notificationShadeWindowControllerImpl2.onShadeOrQsExpanded(bool);
                        break;
                    case 1:
                        notificationShadeWindowControllerImpl2.getClass();
                        boolean booleanValue = bool.booleanValue();
                        NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl2.mCurrentState;
                        notificationShadeWindowState.qsExpanded = booleanValue;
                        notificationShadeWindowControllerImpl2.apply(notificationShadeWindowState);
                        break;
                    case 2:
                        notificationShadeWindowControllerImpl2.onCommunalVisibleChanged(bool);
                        break;
                    default:
                        boolean booleanValue2 = bool.booleanValue();
                        NotificationShadeWindowState notificationShadeWindowState2 = notificationShadeWindowControllerImpl2.mCurrentState;
                        notificationShadeWindowState2.keyguardOccluded = booleanValue2;
                        notificationShadeWindowControllerImpl2.apply(notificationShadeWindowState2);
                        break;
                }
            }
        });
        JavaAdapterKt.collectFlow(notificationShadeWindowControllerImpl.mWindowRootView, ((ShadeInteractorImpl) ((ShadeInteractor) lazy.get())).baseShadeInteractor.isQsExpanded(), new Consumer() { // from class: com.android.systemui.shade.NotificationShadeWindowControllerImpl$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i7 = i6;
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl2 = notificationShadeWindowControllerImpl;
                Boolean bool = (Boolean) obj;
                switch (i7) {
                    case 0:
                        notificationShadeWindowControllerImpl2.onShadeOrQsExpanded(bool);
                        break;
                    case 1:
                        notificationShadeWindowControllerImpl2.getClass();
                        boolean booleanValue = bool.booleanValue();
                        NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl2.mCurrentState;
                        notificationShadeWindowState.qsExpanded = booleanValue;
                        notificationShadeWindowControllerImpl2.apply(notificationShadeWindowState);
                        break;
                    case 2:
                        notificationShadeWindowControllerImpl2.onCommunalVisibleChanged(bool);
                        break;
                    default:
                        boolean booleanValue2 = bool.booleanValue();
                        NotificationShadeWindowState notificationShadeWindowState2 = notificationShadeWindowControllerImpl2.mCurrentState;
                        notificationShadeWindowState2.keyguardOccluded = booleanValue2;
                        notificationShadeWindowControllerImpl2.apply(notificationShadeWindowState2);
                        break;
                }
            }
        });
        JavaAdapterKt.collectFlow(notificationShadeWindowControllerImpl.mWindowRootView, ((CommunalInteractor) notificationShadeWindowControllerImpl.mCommunalInteractor.get()).isCommunalVisible, new Consumer() { // from class: com.android.systemui.shade.NotificationShadeWindowControllerImpl$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i7 = i5;
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl2 = notificationShadeWindowControllerImpl;
                Boolean bool = (Boolean) obj;
                switch (i7) {
                    case 0:
                        notificationShadeWindowControllerImpl2.onShadeOrQsExpanded(bool);
                        break;
                    case 1:
                        notificationShadeWindowControllerImpl2.getClass();
                        boolean booleanValue = bool.booleanValue();
                        NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl2.mCurrentState;
                        notificationShadeWindowState.qsExpanded = booleanValue;
                        notificationShadeWindowControllerImpl2.apply(notificationShadeWindowState);
                        break;
                    case 2:
                        notificationShadeWindowControllerImpl2.onCommunalVisibleChanged(bool);
                        break;
                    default:
                        boolean booleanValue2 = bool.booleanValue();
                        NotificationShadeWindowState notificationShadeWindowState2 = notificationShadeWindowControllerImpl2.mCurrentState;
                        notificationShadeWindowState2.keyguardOccluded = booleanValue2;
                        notificationShadeWindowControllerImpl2.apply(notificationShadeWindowState2);
                        break;
                }
            }
        });
        JavaAdapterKt.collectFlow(notificationShadeWindowControllerImpl.mWindowRootView, notificationShadeWindowControllerImpl.mNotificationShadeWindowModel.isKeyguardOccluded, new Consumer() { // from class: com.android.systemui.shade.NotificationShadeWindowControllerImpl$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i7 = i3;
                NotificationShadeWindowControllerImpl notificationShadeWindowControllerImpl2 = notificationShadeWindowControllerImpl;
                Boolean bool = (Boolean) obj;
                switch (i7) {
                    case 0:
                        notificationShadeWindowControllerImpl2.onShadeOrQsExpanded(bool);
                        break;
                    case 1:
                        notificationShadeWindowControllerImpl2.getClass();
                        boolean booleanValue = bool.booleanValue();
                        NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl2.mCurrentState;
                        notificationShadeWindowState.qsExpanded = booleanValue;
                        notificationShadeWindowControllerImpl2.apply(notificationShadeWindowState);
                        break;
                    case 2:
                        notificationShadeWindowControllerImpl2.onCommunalVisibleChanged(bool);
                        break;
                    default:
                        boolean booleanValue2 = bool.booleanValue();
                        NotificationShadeWindowState notificationShadeWindowState2 = notificationShadeWindowControllerImpl2.mCurrentState;
                        notificationShadeWindowState2.keyguardOccluded = booleanValue2;
                        notificationShadeWindowControllerImpl2.apply(notificationShadeWindowState2);
                        break;
                }
            }
        });
        final NotificationShadeWindowViewController notificationShadeWindowViewController = getNotificationShadeWindowViewController();
        NotificationShadeWindowView notificationShadeWindowView2 = notificationShadeWindowViewController.mView;
        notificationShadeWindowViewController.mStackScrollLayout = (NotificationStackScrollLayout) notificationShadeWindowView2.findViewById(R.id.notification_stack_scroller);
        notificationShadeWindowViewController.mPulsingWakeupGestureHandler = new GestureDetector(notificationShadeWindowView2.getContext(), notificationShadeWindowViewController.mPulsingGestureListener);
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        notificationShadeWindowViewController.mFeatureFlagsClassic.getClass();
        notificationShadeWindowView2.layoutInsetsController = notificationShadeWindowViewController.mNotificationInsetsController;
        notificationShadeWindowView2.mInteractionEventHandler = notificationShadeWindowViewController.new AnonymousClass1();
        notificationShadeWindowView2.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() { // from class: com.android.systemui.shade.NotificationShadeWindowViewController.2
            public AnonymousClass2() {
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public final void onChildViewAdded(View view, View view2) {
                if (view2.getId() == R.id.brightness_mirror_container) {
                    NotificationShadeWindowViewController.this.mBrightnessMirror = view2;
                }
            }

            @Override // android.view.ViewGroup.OnHierarchyChangeListener
            public final void onChildViewRemoved(View view, View view2) {
            }
        });
        notificationShadeWindowViewController.setDragDownHelper(notificationShadeWindowViewController.mLockscreenShadeTransitionController.touchHelper);
        NotificationShadeDepthController notificationShadeDepthController = notificationShadeWindowViewController.mDepthController;
        notificationShadeDepthController.root = notificationShadeWindowView2;
        notificationShadeDepthController.onPanelExpansionChanged(notificationShadeWindowViewController.mShadeExpansionStateManager.addExpansionListener(notificationShadeDepthController));
        NotificationShadeWindowViewController notificationShadeWindowViewController2 = getNotificationShadeWindowViewController();
        CommunalInteractor communalInteractor = notificationShadeWindowViewController2.mGlanceableHubContainerController.communalInteractor;
        JavaAdapterKt.collectFlow(notificationShadeWindowViewController2.mView, FlowKt.distinctUntilChanged(new BooleanFlowOperators$not$$inlined$map$1(i5, (Flow[]) CollectionsKt.toList(ArraysKt.asIterable(new Flow[]{communalInteractor.isCommunalAvailable, communalInteractor.editModeOpen})).toArray(new Flow[0]))), new NotificationShadeWindowViewController$$ExternalSyntheticLambda5(notificationShadeWindowViewController2, i4));
        BackActionInteractor backActionInteractor = this.mBackActionInteractor;
        backActionInteractor.qsController = this.mQsController;
        ShadeSurface shadeSurface = this.mShadeSurface;
        backActionInteractor.shadeBackActionInteractor = shadeSurface;
        getNotificationShadeWindowViewController().mView.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda31
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
                centralSurfacesImpl.mAutoHideController.checkUserAutoHide(motionEvent);
                NotificationRemoteInputManager notificationRemoteInputManager = centralSurfacesImpl.mRemoteInputManager;
                notificationRemoteInputManager.getClass();
                if (motionEvent.getAction() == 4 && motionEvent.getX() == 0.0f && motionEvent.getY() == 0.0f && notificationRemoteInputManager.isRemoteInputActive()) {
                    notificationRemoteInputManager.closeRemoteInputs();
                }
                if (centralSurfacesImpl.mQsController.isCustomizing()) {
                    centralSurfacesImpl.mShadeController.onStatusBarTouch(motionEvent);
                }
                return centralSurfacesImpl.getNotificationShadeWindowViewController().mView.onTouchEvent(motionEvent);
            }
        });
        this.mWallpaperController.rootView = getNotificationShadeWindowViewController().mView;
        this.mDemoModeController.addCallback((DemoMode) this.mDemoModeCallback);
        StatusBarModeRepositoryImpl statusBarModeRepositoryImpl = this.mStatusBarModeRepository;
        ReadonlyStateFlow readonlyStateFlow = statusBarModeRepositoryImpl.defaultDisplay.isTransientShown;
        CentralSurfacesImpl$$ExternalSyntheticLambda2 centralSurfacesImpl$$ExternalSyntheticLambda2 = new CentralSurfacesImpl$$ExternalSyntheticLambda2(this, i3);
        JavaAdapter javaAdapter = this.mJavaAdapter;
        javaAdapter.alwaysCollectFlow(readonlyStateFlow, centralSurfacesImpl$$ExternalSyntheticLambda2);
        javaAdapter.alwaysCollectFlow(statusBarModeRepositoryImpl.defaultDisplay.statusBarMode, new CentralSurfacesImpl$$ExternalSyntheticLambda2(this, i2));
        CentralSurfacesCommandQueueCallbacks centralSurfacesCommandQueueCallbacks = (CentralSurfacesCommandQueueCallbacks) this.mCommandQueueCallbacksLazy.get();
        this.mCommandQueueCallbacks = centralSurfacesCommandQueueCallbacks;
        this.mCommandQueue.addCallback((CommandQueue.Callbacks) centralSurfacesCommandQueueCallbacks);
        ShadeExpansionStateManager shadeExpansionStateManager = this.mShadeExpansionStateManager;
        NotificationWakeUpCoordinator notificationWakeUpCoordinator = this.mWakeUpCoordinator;
        notificationWakeUpCoordinator.onPanelExpansionChanged(shadeExpansionStateManager.addExpansionListener(notificationWakeUpCoordinator));
        PluginDependencyProvider pluginDependencyProvider = this.mPluginDependencyProvider;
        pluginDependencyProvider.allowPluginDependency(DarkIconDispatcher.class);
        pluginDependencyProvider.allowPluginDependency(StatusBarStateController.class);
        CentralSurfacesImpl$$ExternalSyntheticLambda0 centralSurfacesImpl$$ExternalSyntheticLambda0 = new CentralSurfacesImpl$$ExternalSyntheticLambda0(this);
        final StatusBarInitializerImpl statusBarInitializerImpl = this.mStatusBarInitializer;
        statusBarInitializerImpl.statusBarViewUpdatedListener = centralSurfacesImpl$$ExternalSyntheticLambda0;
        StatusBarWindowControllerImpl statusBarWindowControllerImpl = statusBarInitializerImpl.windowController;
        FragmentHostManager fragmentHostManager = statusBarWindowControllerImpl.mFragmentService.getFragmentHostManager(statusBarWindowControllerImpl.mStatusBarWindowView);
        fragmentHostManager.addTagListener("CollapsedStatusBarFragment", new FragmentHostManager.FragmentListener() { // from class: com.android.systemui.statusbar.core.StatusBarInitializerImpl$initializeStatusBar$1
            @Override // com.android.systemui.fragments.FragmentHostManager.FragmentListener
            public final void onFragmentViewCreated(Fragment fragment) {
                DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl = ((CollapsedStatusBarFragment) fragment).mStatusBarFragmentComponent;
                if (daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl == null) {
                    throw new IllegalStateException();
                }
                StatusBarInitializerImpl statusBarInitializerImpl2 = StatusBarInitializerImpl.this;
                CentralSurfacesImpl$$ExternalSyntheticLambda0 centralSurfacesImpl$$ExternalSyntheticLambda02 = statusBarInitializerImpl2.statusBarViewUpdatedListener;
                if (centralSurfacesImpl$$ExternalSyntheticLambda02 != null) {
                    PhoneStatusBarViewController phoneStatusBarViewController = (PhoneStatusBarViewController) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.providePhoneStatusBarViewControllerProvider.get();
                    PhoneStatusBarTransitions phoneStatusBarTransitions = (PhoneStatusBarTransitions) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.providePhoneStatusBarTransitionsProvider.get();
                    CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) centralSurfacesImpl$$ExternalSyntheticLambda02.f$0;
                    centralSurfacesImpl.mPhoneStatusBarViewController = phoneStatusBarViewController;
                    centralSurfacesImpl.mStatusBarTransitions = phoneStatusBarTransitions;
                    centralSurfacesImpl.getNotificationShadeWindowViewController().mStatusBarViewController = centralSurfacesImpl.mPhoneStatusBarViewController;
                    ShadeSurface shadeSurface2 = centralSurfacesImpl.mShadeSurface;
                    shadeSurface2.updateExpansionAndVisibility();
                    boolean z2 = centralSurfacesImpl.mBouncerShowing;
                    int i7 = z2 ? 4 : 0;
                    PhoneStatusBarViewController phoneStatusBarViewController2 = centralSurfacesImpl.mPhoneStatusBarViewController;
                    if (phoneStatusBarViewController2 != null) {
                        ((PhoneStatusBarView) phoneStatusBarViewController2.mView).setImportantForAccessibility(i7);
                    }
                    shadeSurface2.setImportantForAccessibility(i7);
                    shadeSurface2.setBouncerShowing(z2);
                    centralSurfacesImpl.checkBarModes$1();
                }
                Iterator it = statusBarInitializerImpl2.creationListeners.iterator();
                while (it.hasNext()) {
                    ((StatusBarInitializer$OnStatusBarViewInitializedListener) it.next()).onStatusBarViewInitialized(daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl);
                }
            }

            @Override // com.android.systemui.fragments.FragmentHostManager.FragmentListener
            public final void onFragmentViewDestroyed(Fragment fragment) {
            }
        });
        fragmentHostManager.mFragments.getFragmentManager().beginTransaction().replace(R.id.status_bar_container, (Fragment) statusBarInitializerImpl.collapsedStatusBarFragmentProvider.get(), "CollapsedStatusBarFragment").commit();
        NotificationShadeWindowView notificationShadeWindowView3 = getNotificationShadeWindowViewController().mView;
        StatusBarTouchableRegionManager statusBarTouchableRegionManager = this.mStatusBarTouchableRegionManager;
        statusBarTouchableRegionManager.mNotificationShadeWindowView = notificationShadeWindowView3;
        statusBarTouchableRegionManager.mNotificationPanelView = notificationShadeWindowView3.findViewById(R.id.notification_panel);
        NavigationBarControllerImpl navigationBarControllerImpl = this.mNavigationBarController;
        boolean initializeTaskbarIfNecessary = navigationBarControllerImpl.initializeTaskbarIfNecessary();
        DisplayTracker displayTracker = navigationBarControllerImpl.mDisplayTracker;
        for (Display display2 : ((DisplayTrackerImpl) displayTracker).displayManager.getDisplays()) {
            if (initializeTaskbarIfNecessary) {
                int displayId = display2.getDisplayId();
                displayTracker.getClass();
                i = displayId == 0 ? i + 1 : 0;
            }
            navigationBarControllerImpl.createNavigationBar(display2, null, registerStatusBarResult);
        }
        this.mAmbientIndicationContainer = getNotificationShadeWindowViewController().mView.findViewById(R.id.ambient_indication_container);
        this.mAutoHideController.mStatusBar = new AnonymousClass4();
        ScrimView scrimView = (ScrimView) getNotificationShadeWindowViewController().mView.findViewById(R.id.scrim_behind);
        ScrimView scrimView2 = (ScrimView) getNotificationShadeWindowViewController().mView.findViewById(R.id.scrim_notifications);
        ScrimView scrimView3 = (ScrimView) getNotificationShadeWindowViewController().mView.findViewById(R.id.scrim_in_front);
        CentralSurfacesImpl$$ExternalSyntheticLambda2 centralSurfacesImpl$$ExternalSyntheticLambda22 = new CentralSurfacesImpl$$ExternalSyntheticLambda2(this, 5);
        ScrimController scrimController = this.mScrimController;
        scrimController.mScrimVisibleListener = centralSurfacesImpl$$ExternalSyntheticLambda22;
        scrimController.mNotificationsScrim = scrimView2;
        scrimController.mScrimBehind = scrimView;
        scrimController.mScrimInFront = scrimView3;
        scrimController.updateThemeColors();
        ScrimView scrimView4 = scrimController.mNotificationsScrim;
        scrimView4.mScrimName = scrimController.getScrimName(scrimView4);
        ScrimView scrimView5 = scrimController.mScrimBehind;
        scrimView5.mScrimName = scrimController.getScrimName(scrimView5);
        ScrimView scrimView6 = scrimController.mScrimInFront;
        scrimView6.mScrimName = scrimController.getScrimName(scrimView6);
        scrimView.enableBottomEdgeConcave(scrimController.mClipsQsScrim);
        Drawable drawable = scrimController.mNotificationsScrim.mDrawable;
        if (drawable instanceof ScrimDrawable) {
            ScrimDrawable scrimDrawable = (ScrimDrawable) drawable;
            if (!scrimDrawable.mCornerRadiusEnabled) {
                scrimDrawable.mCornerRadiusEnabled = true;
                scrimDrawable.invalidateSelf();
            }
        }
        ScrimState[] values = ScrimState.values();
        for (int i7 = 0; i7 < values.length; i7++) {
            ScrimState scrimState = values[i7];
            ScrimView scrimView7 = scrimController.mScrimInFront;
            ScrimView scrimView8 = scrimController.mScrimBehind;
            DozeParameters dozeParameters = scrimController.mDozeParameters;
            DockManager dockManager = scrimController.mDockManager;
            scrimState.getClass();
            scrimState.mBackgroundColor = scrimView8.getContext().getColor(R.color.shade_scrim_background_dark);
            scrimState.mScrimInFront = scrimView7;
            scrimState.mScrimBehind = scrimView8;
            scrimState.mDozeParameters = dozeParameters;
            scrimState.mDockManager = dockManager;
            scrimState.mDisplayRequiresBlanking = dozeParameters.getDisplayNeedsBlanking();
            ScrimState scrimState2 = values[i7];
            scrimState2.mScrimBehindAlphaKeyguard = scrimController.mScrimBehindAlphaKeyguard;
            scrimState2.mDefaultScrimAlpha = scrimController.mDefaultScrimAlpha;
        }
        scrimController.mTransparentScrimBackground = scrimView2.getResources().getBoolean(R.bool.notification_scrim_transparent);
        scrimController.updateScrims();
        scrimController.mKeyguardUpdateMonitor.registerCallback(scrimController.mKeyguardVisibilityCallback);
        for (ScrimState scrimState3 : ScrimState.values()) {
            scrimState3.prepare(scrimState3);
        }
        scrimController.mBouncerToGoneTransition = new ScrimController$$ExternalSyntheticLambda1(scrimController, 2);
        KeyguardTransitionInteractor keyguardTransitionInteractor = scrimController.mKeyguardTransitionInteractor;
        Edge.Companion companion = Edge.Companion;
        KeyguardState keyguardState = KeyguardState.PRIMARY_BOUNCER;
        KeyguardState keyguardState2 = KeyguardState.GONE;
        Edge.StateToState stateToState = new Edge.StateToState(keyguardState, keyguardState2);
        keyguardTransitionInteractor.getClass();
        JavaAdapterKt.collectFlow(scrimView, keyguardTransitionInteractor.transition(stateToState), scrimController.mBouncerToGoneTransition, scrimController.mMainDispatcher);
        JavaAdapterKt.collectFlow(scrimView, scrimController.mPrimaryBouncerToGoneTransitionViewModel.scrimAlpha, scrimController.mScrimAlphaConsumer, scrimController.mMainDispatcher);
        KeyguardTransitionInteractor keyguardTransitionInteractor2 = scrimController.mKeyguardTransitionInteractor;
        KeyguardState keyguardState3 = KeyguardState.ALTERNATE_BOUNCER;
        SceneKey sceneKey = Scenes.Bouncer;
        Edge.StateToState stateToState2 = new Edge.StateToState(keyguardState3, keyguardState2);
        keyguardTransitionInteractor2.getClass();
        JavaAdapterKt.collectFlow(scrimView, keyguardTransitionInteractor2.transition(stateToState2), scrimController.mBouncerToGoneTransition, scrimController.mMainDispatcher);
        JavaAdapterKt.collectFlow(scrimView, scrimController.mAlternateBouncerToGoneTransitionViewModel.scrimAlpha, scrimController.mScrimAlphaConsumer, scrimController.mMainDispatcher);
        KeyguardTransitionInteractor keyguardTransitionInteractor3 = scrimController.mKeyguardTransitionInteractor;
        KeyguardState keyguardState4 = KeyguardState.LOCKSCREEN;
        KeyguardState keyguardState5 = KeyguardState.GLANCEABLE_HUB;
        Edge.StateToState stateToState3 = new Edge.StateToState(keyguardState4, keyguardState5);
        keyguardTransitionInteractor3.getClass();
        JavaAdapterKt.collectFlow(scrimView, keyguardTransitionInteractor3.transition(stateToState3), scrimController.mGlanceableHubConsumer, scrimController.mMainDispatcher);
        KeyguardTransitionInteractor keyguardTransitionInteractor4 = scrimController.mKeyguardTransitionInteractor;
        Edge.StateToState stateToState4 = new Edge.StateToState(keyguardState5, keyguardState4);
        keyguardTransitionInteractor4.getClass();
        JavaAdapterKt.collectFlow(scrimView, keyguardTransitionInteractor4.transition(stateToState4), scrimController.mGlanceableHubConsumer, scrimController.mMainDispatcher);
        CentralSurfacesImpl$$ExternalSyntheticLambda2 centralSurfacesImpl$$ExternalSyntheticLambda23 = new CentralSurfacesImpl$$ExternalSyntheticLambda2(this, 6);
        LightRevealScrim lightRevealScrim = this.mLightRevealScrim;
        lightRevealScrim.isScrimOpaqueChangedListener = centralSurfacesImpl$$ExternalSyntheticLambda23;
        ScreenOffAnimationController screenOffAnimationController = this.mScreenOffAnimationController;
        Iterator it = screenOffAnimationController.animations.iterator();
        while (it.hasNext()) {
            ((ScreenOffAnimation) it.next()).initialize(this, shadeSurface, lightRevealScrim);
        }
        screenOffAnimationController.wakefulnessLifecycle.mObservers.add(screenOffAnimationController);
        LightRevealScrim lightRevealScrim2 = this.mLightRevealScrim;
        if (lightRevealScrim2 != null) {
            lightRevealScrim2.setAlpha(this.mScrimController.mState.getMaxLightRevealScrimAlpha());
        }
        ShadeController shadeController = this.mShadeController;
        Objects.requireNonNull(shadeController);
        shadeSurface.initDependencies(this, new CentralSurfacesImpl$$ExternalSyntheticLambda25(0, shadeController), this.mHeadsUpManager);
        View findViewById = getNotificationShadeWindowViewController().mView.findViewById(R.id.qs_frame);
        if (findViewById != null) {
            FragmentHostManager fragmentHostManager2 = this.mFragmentService.getFragmentHostManager(findViewById);
            ExtensionControllerImpl extensionControllerImpl = this.mExtensionController;
            extensionControllerImpl.getClass();
            ExtensionControllerImpl.ExtensionImpl extensionImpl = extensionControllerImpl.new ExtensionImpl();
            extensionImpl.mProducers.add(extensionImpl.new PluginItem(PluginManager.Helper.getAction(QS.class), QS.class));
            extensionImpl.mProducers.add(new ExtensionControllerImpl.ExtensionImpl.Default(new Supplier() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda26
                @Override // java.util.function.Supplier
                public final Object get() {
                    CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
                    centralSurfacesImpl.getClass();
                    FragmentHostManager fragmentHostManager3 = centralSurfacesImpl.mFragmentService.getFragmentHostManager(centralSurfacesImpl.getNotificationShadeWindowViewController().mView);
                    return (QS) fragmentHostManager3.mPlugins.instantiate(fragmentHostManager3.mContext, QSFragmentLegacy.class.getName(), null);
                }
            }));
            Collections.sort(extensionImpl.mProducers, Comparator.comparingInt(new ExtensionControllerImpl$ExtensionBuilder$$ExternalSyntheticLambda0()));
            ExtensionControllerImpl.ExtensionImpl.m887$$Nest$mnotifyChanged(extensionImpl);
            extensionImpl.mCallbacks.add(new ExtensionFragmentListener(this.mFragmentService, findViewById, extensionImpl));
            this.mBrightnessMirrorController = new BrightnessMirrorController(getNotificationShadeWindowViewController().mView, this.mShadeSurface, (NotificationShadeDepthController) this.mNotificationShadeDepthControllerLazy.get(), this.mBrightnessSliderFactory, new CentralSurfacesImpl$$ExternalSyntheticLambda2(this, 7));
            fragmentHostManager2.addTagListener(QS.TAG, new FragmentHostManager.FragmentListener() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda28
                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.android.systemui.fragments.FragmentHostManager.FragmentListener
                public final void onFragmentViewCreated(Fragment fragment) {
                    CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
                    centralSurfacesImpl.getClass();
                    QS qs = (QS) fragment;
                    if (qs instanceof QSFragmentLegacy) {
                        QSImpl qSImpl = ((QSFragmentLegacy) qs).mQsImpl;
                        centralSurfacesImpl.mQSPanelController = qSImpl != null ? qSImpl.mQSPanelController : null;
                        BrightnessMirrorController brightnessMirrorController = centralSurfacesImpl.mBrightnessMirrorController;
                        if (qSImpl != null) {
                            BrightnessMirrorHandler brightnessMirrorHandler = qSImpl.mQSPanelController.mBrightnessMirrorHandler;
                            BrightnessMirrorController brightnessMirrorController2 = brightnessMirrorHandler.mirrorController;
                            BrightnessMirrorHandler$brightnessMirrorListener$1 brightnessMirrorHandler$brightnessMirrorListener$1 = brightnessMirrorHandler.brightnessMirrorListener;
                            if (brightnessMirrorController2 != null) {
                                brightnessMirrorController2.removeCallback(brightnessMirrorHandler$brightnessMirrorListener$1);
                            }
                            brightnessMirrorHandler.mirrorController = brightnessMirrorController;
                            if (brightnessMirrorController != null) {
                                brightnessMirrorController.addCallback(brightnessMirrorHandler$brightnessMirrorListener$1);
                            }
                            brightnessMirrorHandler.updateBrightnessMirror();
                        }
                    }
                }
            });
        }
        View findViewById2 = getNotificationShadeWindowViewController().mView.findViewById(R.id.report_rejected_touch);
        this.mReportRejectedTouch = findViewById2;
        if (findViewById2 != null) {
            updateReportRejectedTouchVisibility();
            this.mReportRejectedTouch.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda29
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
                    Uri reportRejectedTouch = centralSurfacesImpl.mFalsingManager.reportRejectedTouch();
                    if (reportRejectedTouch == null) {
                        return;
                    }
                    StringWriter stringWriter = new StringWriter();
                    stringWriter.write("Build info: ");
                    stringWriter.write(SystemProperties.get("ro.build.description"));
                    stringWriter.write("\nSerial number: ");
                    stringWriter.write(SystemProperties.get("ro.serialno"));
                    stringWriter.write("\n");
                    centralSurfacesImpl.mActivityStarter.startActivityDismissingKeyguard(Intent.createChooser(new Intent("android.intent.action.SEND").setType("*/*").putExtra("android.intent.extra.SUBJECT", "Rejected touch report").putExtra("android.intent.extra.STREAM", reportRejectedTouch).putExtra("android.intent.extra.TEXT", stringWriter.toString()), "Share rejected touch report").addFlags(268435456), true, true, null);
                }
            });
        }
        if (!this.mPowerManager.isInteractive()) {
            this.mBroadcastReceiver.onReceive(this.mContext, new Intent("android.intent.action.SCREEN_OFF"));
        }
        this.mGestureWakeLock = this.mPowerManager.newWakeLock(10, "sysui:GestureWakeLock");
        registerBroadcastReceiver();
        AnonymousClass16 anonymousClass16 = this.mUserSetupObserver;
        ((DeviceProvisionedControllerImpl) this.mDeviceProvisionedController).addCallback(anonymousClass16);
        anonymousClass16.onUserSetupChanged();
        ThreadedRenderer.overrideProperty("disableProfileBars", "true");
        ThreadedRenderer.overrideProperty("ambientRatio", String.valueOf(1.5f));
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 2040, -2138832824, -3);
        notificationShadeWindowControllerImpl.mLp = layoutParams;
        layoutParams.token = new Binder();
        WindowManager.LayoutParams layoutParams2 = notificationShadeWindowControllerImpl.mLp;
        layoutParams2.gravity = 48;
        layoutParams2.setFitInsetsTypes(0);
        notificationShadeWindowControllerImpl.mLp.setTitle("NotificationShade");
        notificationShadeWindowControllerImpl.mLp.packageName = notificationShadeWindowControllerImpl.mContext.getPackageName();
        WindowManager.LayoutParams layoutParams3 = notificationShadeWindowControllerImpl.mLp;
        layoutParams3.layoutInDisplayCutoutMode = 3;
        layoutParams3.privateFlags |= 512;
        notificationShadeWindowControllerImpl.mWindowManager.addView(notificationShadeWindowControllerImpl.mWindowRootView, layoutParams3);
        if (notificationShadeWindowControllerImpl.mWindowRootView.getWindowInsetsController() != null) {
            notificationShadeWindowControllerImpl.mWindowRootView.getWindowInsetsController().setSystemBarsBehavior(2);
        }
        notificationShadeWindowControllerImpl.mLpChanged.copyFrom(notificationShadeWindowControllerImpl.mLp);
        notificationShadeWindowControllerImpl.onThemeChanged();
        if (notificationShadeWindowControllerImpl.mKeyguardViewMediator.isShowingAndNotOccluded()) {
            NotificationShadeWindowState notificationShadeWindowState = notificationShadeWindowControllerImpl.mCurrentState;
            notificationShadeWindowState.keyguardShowing = true;
            notificationShadeWindowControllerImpl.apply(notificationShadeWindowState);
        }
        final StatusBarWindowControllerImpl statusBarWindowControllerImpl2 = this.mStatusBarWindowController;
        statusBarWindowControllerImpl2.getClass();
        Trace.beginSection("StatusBarWindowController.getBarLayoutParams");
        WindowManager.LayoutParams barLayoutParamsForRotation = statusBarWindowControllerImpl2.getBarLayoutParamsForRotation(statusBarWindowControllerImpl2.mContext.getDisplay().getRotation());
        barLayoutParamsForRotation.paramsForRotation = new WindowManager.LayoutParams[4];
        for (int i8 = 0; i8 <= 3; i8++) {
            barLayoutParamsForRotation.paramsForRotation[i8] = statusBarWindowControllerImpl2.getBarLayoutParamsForRotation(i8);
        }
        statusBarWindowControllerImpl2.mLp = barLayoutParamsForRotation;
        Trace.endSection();
        statusBarWindowControllerImpl2.mWindowManager.addView(statusBarWindowControllerImpl2.mStatusBarWindowView, statusBarWindowControllerImpl2.mLp);
        statusBarWindowControllerImpl2.mLpChanged.copyFrom(statusBarWindowControllerImpl2.mLp);
        statusBarWindowControllerImpl2.mContentInsetsProvider.listeners.add(new StatusBarContentInsetsChangedListener() { // from class: com.android.systemui.statusbar.window.StatusBarWindowControllerImpl$$ExternalSyntheticLambda2
            @Override // com.android.systemui.statusbar.phone.StatusBarContentInsetsChangedListener
            public final void onStatusBarContentInsetsChanged() {
                StatusBarWindowControllerImpl.this.calculateStatusBarLocationsForAllRotations();
            }
        });
        statusBarWindowControllerImpl2.calculateStatusBarLocationsForAllRotations();
        statusBarWindowControllerImpl2.mIsAttached = true;
        statusBarWindowControllerImpl2.apply(statusBarWindowControllerImpl2.mCurrentState);
        ActivityTransitionAnimator activityTransitionAnimator = this.mActivityTransitionAnimator;
        activityTransitionAnimator.callback = this.mActivityTransitionAnimatorCallback;
        activityTransitionAnimator.listeners.add(this.mActivityTransitionAnimatorListener);
        NotificationRemoteInputManager notificationRemoteInputManager = this.mRemoteInputManager;
        NotificationShadeWindowController notificationShadeWindowController = this.mNotificationShadeWindowController;
        RemoteInputController remoteInputController = notificationRemoteInputManager.mRemoteInputController;
        if (remoteInputController != null) {
            Objects.requireNonNull(notificationShadeWindowController);
            remoteInputController.mCallbacks.add(notificationShadeWindowController);
        } else {
            notificationRemoteInputManager.mControllerCallbacks.add(notificationShadeWindowController);
        }
        Lazy lazy2 = this.mNotificationActivityStarterLazy;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.mStackScrollerController;
        notificationStackScrollLayoutController.getClass();
        this.mGutsManager.mNotificationActivityStarter = (StatusBarNotificationActivityStarter) lazy2.get();
        Lazy lazy3 = this.mPresenterLazy;
        ((BaseShadeControllerImpl) this.mShadeController).notifPresenter = (StatusBarNotificationPresenter) lazy3.get();
        StatusBarNotificationPresenter statusBarNotificationPresenter = (StatusBarNotificationPresenter) lazy3.get();
        NotificationStackScrollLayoutController.NotifStackControllerImpl notifStackControllerImpl = notificationStackScrollLayoutController.mNotifStackController;
        StatusBarNotificationActivityStarter statusBarNotificationActivityStarter = (StatusBarNotificationActivityStarter) lazy2.get();
        NotificationsController notificationsController = this.mNotificationsController;
        notificationsController.initialize(statusBarNotificationPresenter, this.mNotifListContainer, notifStackControllerImpl, statusBarNotificationActivityStarter);
        StatusBarNotificationPresenter statusBarNotificationPresenter2 = (StatusBarNotificationPresenter) lazy3.get();
        WindowRootViewVisibilityInteractor windowRootViewVisibilityInteractor = this.mWindowRootViewVisibilityInteractor;
        windowRootViewVisibilityInteractor.notificationPresenter = statusBarNotificationPresenter2;
        windowRootViewVisibilityInteractor.notificationsController = notificationsController;
        if ((registerStatusBarResult.mTransientBarTypes & WindowInsets.Type.statusBars()) != 0) {
            StateFlowImpl stateFlowImpl = this.mStatusBarModeRepository.defaultDisplay._isTransientShown;
            Boolean bool = Boolean.TRUE;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, bool);
        }
        this.mCommandQueueCallbacks.getClass();
        this.mCommandQueueCallbacks.getClass();
        int size = registerStatusBarResult.mIcons.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.mCommandQueue.setIcon((String) registerStatusBarResult.mIcons.keyAt(i9), (StatusBarIcon) registerStatusBarResult.mIcons.valueAt(i9));
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.android.systemui.statusbar.banner_action_cancel");
        intentFilter.addAction("com.android.systemui.statusbar.banner_action_setup");
        this.mContext.registerReceiver(this.mBannerActionBroadcastReceiver, intentFilter, "com.android.systemui.permission.SELF", null, 2);
        if (this.mWallpaperSupported) {
            try {
                IWallpaperManager.Stub.asInterface(ServiceManager.getService("wallpaper")).setInAmbientMode(false, 0L);
            } catch (RemoteException unused) {
            }
        }
        final PhoneStatusBarPolicy phoneStatusBarPolicy = this.mIconPolicy;
        phoneStatusBarPolicy.getClass();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.HEADSET_PLUG");
        intentFilter2.addAction("android.intent.action.SIM_STATE_CHANGED");
        intentFilter2.addAction("android.telecom.action.CURRENT_TTY_MODE_CHANGED");
        intentFilter2.addAction("android.intent.action.MANAGED_PROFILE_AVAILABLE");
        intentFilter2.addAction("android.intent.action.MANAGED_PROFILE_UNAVAILABLE");
        intentFilter2.addAction("android.intent.action.PROFILE_REMOVED");
        intentFilter2.addAction("android.intent.action.PROFILE_ACCESSIBLE");
        intentFilter2.addAction("android.intent.action.PROFILE_INACCESSIBLE");
        phoneStatusBarPolicy.mBroadcastDispatcher.registerReceiverWithHandler(phoneStatusBarPolicy.mIntentReceiver, intentFilter2, phoneStatusBarPolicy.mHandler);
        Observer observer = new Observer() { // from class: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                PhoneStatusBarPolicy phoneStatusBarPolicy2 = PhoneStatusBarPolicy.this;
                phoneStatusBarPolicy2.mHandler.post(new PhoneStatusBarPolicy$$ExternalSyntheticLambda0(phoneStatusBarPolicy2, 1));
            }
        };
        phoneStatusBarPolicy.mRingerModeTracker.ringerMode.observeForever(observer);
        phoneStatusBarPolicy.mRingerModeTracker.ringerModeInternal.observeForever(observer);
        ((UserTrackerImpl) phoneStatusBarPolicy.mUserTracker).addCallback(phoneStatusBarPolicy.mUserSwitchListener, phoneStatusBarPolicy.mMainExecutor);
        TelecomManager telecomManager = phoneStatusBarPolicy.mTelecomManager;
        if (telecomManager == null) {
            phoneStatusBarPolicy.updateTTY(0);
        } else {
            phoneStatusBarPolicy.updateTTY(telecomManager.getCurrentTtyMode());
        }
        phoneStatusBarPolicy.updateBluetooth();
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(null, phoneStatusBarPolicy.mSlotAlarmClock, R.drawable.stat_sys_alarm);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotAlarmClock, false);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(null, phoneStatusBarPolicy.mSlotZen, R.drawable.stat_sys_dnd);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotZen, false);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(phoneStatusBarPolicy.mResources.getString(R.string.accessibility_ringer_vibrate), phoneStatusBarPolicy.mSlotVibrate, R.drawable.stat_sys_ringer_vibrate);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotVibrate, false);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(phoneStatusBarPolicy.mResources.getString(R.string.accessibility_ringer_silent), phoneStatusBarPolicy.mSlotMute, R.drawable.stat_sys_ringer_silent);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotMute, false);
        phoneStatusBarPolicy.updateVolumeZen();
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(null, phoneStatusBarPolicy.mSlotCast, R.drawable.stat_sys_cast);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotCast, false);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(phoneStatusBarPolicy.mResources.getString(R.string.connected_display_icon_desc), phoneStatusBarPolicy.mSlotConnectedDisplay, R.drawable.stat_sys_connected_display);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotConnectedDisplay, false);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(phoneStatusBarPolicy.mResources.getString(R.string.accessibility_status_bar_hotspot), phoneStatusBarPolicy.mSlotHotspot, R.drawable.stat_sys_hotspot);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotHotspot, ((HotspotControllerImpl) phoneStatusBarPolicy.mHotspot).isHotspotEnabled());
        phoneStatusBarPolicy.updateProfileIcon();
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(phoneStatusBarPolicy.mResources.getString(R.string.accessibility_data_saver_on), phoneStatusBarPolicy.mSlotDataSaver, R.drawable.stat_sys_data_saver);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotDataSaver, false);
        Resources resources = phoneStatusBarPolicy.mResources;
        PrivacyType privacyType = PrivacyType.TYPE_MICROPHONE;
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(phoneStatusBarPolicy.mResources.getString(R.string.ongoing_privacy_chip_content_multiple_apps, resources.getString(privacyType.getNameId())), phoneStatusBarPolicy.mSlotMicrophone, privacyType.getIconId());
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotMicrophone, false);
        Resources resources2 = phoneStatusBarPolicy.mResources;
        PrivacyType privacyType2 = PrivacyType.TYPE_CAMERA;
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(phoneStatusBarPolicy.mResources.getString(R.string.ongoing_privacy_chip_content_multiple_apps, resources2.getString(privacyType2.getNameId())), phoneStatusBarPolicy.mSlotCamera, privacyType2.getIconId());
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotCamera, false);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(phoneStatusBarPolicy.mResources.getString(R.string.accessibility_location_active), phoneStatusBarPolicy.mSlotLocation, PhoneStatusBarPolicy.LOCATION_STATUS_ICON_ID);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotLocation, false);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(phoneStatusBarPolicy.mResources.getString(R.string.accessibility_sensors_off_active), phoneStatusBarPolicy.mSlotSensorsOff, R.drawable.stat_sys_sensors_off);
        StatusBarIconController statusBarIconController = phoneStatusBarPolicy.mIconController;
        String str = phoneStatusBarPolicy.mSlotSensorsOff;
        SensorPrivacyControllerImpl sensorPrivacyControllerImpl = phoneStatusBarPolicy.mSensorPrivacyController;
        synchronized (sensorPrivacyControllerImpl.mLock) {
            z = sensorPrivacyControllerImpl.mSensorPrivacyEnabled;
        }
        ((StatusBarIconControllerImpl) statusBarIconController).setIconVisibility(str, z);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIcon(null, phoneStatusBarPolicy.mSlotScreenRecord, R.drawable.stat_sys_screen_record);
        ((StatusBarIconControllerImpl) phoneStatusBarPolicy.mIconController).setIconVisibility(phoneStatusBarPolicy.mSlotScreenRecord, false);
        ((RotationLockControllerImpl) phoneStatusBarPolicy.mRotationLockController).addCallback(phoneStatusBarPolicy);
        phoneStatusBarPolicy.mBluetooth.addCallback(phoneStatusBarPolicy);
        ((DeviceProvisionedControllerImpl) phoneStatusBarPolicy.mProvisionedController).addCallback(phoneStatusBarPolicy);
        phoneStatusBarPolicy.mCurrentUserSetup = ((DeviceProvisionedControllerImpl) phoneStatusBarPolicy.mProvisionedController).isCurrentUserSetup();
        ((ZenModeControllerImpl) phoneStatusBarPolicy.mZenController).addCallback(phoneStatusBarPolicy.mZenControllerCallback);
        ((HotspotControllerImpl) phoneStatusBarPolicy.mHotspot).addCallback(phoneStatusBarPolicy.mHotspotCallback);
        ((NextAlarmControllerImpl) phoneStatusBarPolicy.mNextAlarmController).addCallback(phoneStatusBarPolicy.mNextAlarmCallback);
        phoneStatusBarPolicy.mDataSaver.addCallback(phoneStatusBarPolicy);
        ((KeyguardStateControllerImpl) phoneStatusBarPolicy.mKeyguardStateController).addCallback(phoneStatusBarPolicy);
        phoneStatusBarPolicy.mPrivacyItemController.addCallback(phoneStatusBarPolicy);
        phoneStatusBarPolicy.mSensorPrivacyController.addCallback(phoneStatusBarPolicy.mSensorPrivacyListener);
        ((LocationControllerImpl) phoneStatusBarPolicy.mLocationController).addCallback(phoneStatusBarPolicy);
        phoneStatusBarPolicy.mJavaAdapter.alwaysCollectFlow(phoneStatusBarPolicy.mConnectedDisplayInteractor.connectedDisplayState, new Consumer() { // from class: com.android.systemui.statusbar.phone.PhoneStatusBarPolicy$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PhoneStatusBarPolicy phoneStatusBarPolicy2 = PhoneStatusBarPolicy.this;
                ConnectedDisplayInteractor$State connectedDisplayInteractor$State = (ConnectedDisplayInteractor$State) obj;
                boolean z2 = PhoneStatusBarPolicy.DEBUG;
                phoneStatusBarPolicy2.getClass();
                boolean z3 = connectedDisplayInteractor$State != ConnectedDisplayInteractor$State.DISCONNECTED;
                if (PhoneStatusBarPolicy.DEBUG) {
                    StringBuilder sb = new StringBuilder("connected_display: ");
                    sb.append(z3 ? "showing" : "hiding");
                    sb.append(" icon");
                    Log.d("PhoneStatusBarPolicy", sb.toString());
                }
                ((StatusBarIconControllerImpl) phoneStatusBarPolicy2.mIconController).setIconVisibility(phoneStatusBarPolicy2.mSlotConnectedDisplay, z3);
            }
        });
        phoneStatusBarPolicy.mCommandQueue.addCallback((CommandQueue.Callbacks) phoneStatusBarPolicy);
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).addCallback(new KeyguardStateController.Callback() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl.2
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardGoingAwayChanged() {
                CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
                if (((KeyguardStateControllerImpl) centralSurfacesImpl.mKeyguardStateController).mKeyguardGoingAway) {
                    return;
                }
                LightRevealScrim lightRevealScrim3 = centralSurfacesImpl.mLightRevealScrim;
                if (lightRevealScrim3.revealAmount != 1.0f) {
                    Log.e("CentralSurfaces", "Keyguard is done going away, but someone left the light reveal scrim at reveal amount: " + lightRevealScrim3.revealAmount);
                }
                ValueAnimator valueAnimator = centralSurfacesImpl.mAuthRippleController.lightRevealScrimAnimator;
                if (valueAnimator != null ? valueAnimator.isRunning() : false) {
                    return;
                }
                lightRevealScrim3.setRevealAmount(1.0f);
            }

            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onUnlockedChanged() {
                CentralSurfacesImpl.this.logStateToEventlog();
            }
        });
        Trace.beginSection("CentralSurfaces#startKeyguard");
        AnonymousClass18 anonymousClass18 = this.mStateListener;
        StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) this.mStatusBarStateController;
        synchronized (statusBarStateControllerImpl.mListeners) {
            statusBarStateControllerImpl.addListenerInternalLocked(anonymousClass18, 0);
        }
        BiometricUnlockController biometricUnlockController = (BiometricUnlockController) this.mBiometricUnlockControllerLazy.get();
        this.mBiometricUnlockController = biometricUnlockController;
        biometricUnlockController.mBiometricUnlockEventsListeners.add(new BiometricUnlockController.BiometricUnlockEventsListener() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl.6
            @Override // com.android.systemui.statusbar.phone.BiometricUnlockController.BiometricUnlockEventsListener
            public final void onModeChanged(int i10) {
                if (i10 == 1 || i10 == 2 || i10 == 6) {
                    setWakeAndUnlocking(true);
                }
                CentralSurfacesImpl.this.notifyBiometricAuthModeChanged();
            }

            @Override // com.android.systemui.statusbar.phone.BiometricUnlockController.BiometricUnlockEventsListener
            public final void onResetMode() {
                setWakeAndUnlocking(false);
                CentralSurfacesImpl.this.notifyBiometricAuthModeChanged();
            }

            public final void setWakeAndUnlocking(boolean z2) {
                CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
                if (centralSurfacesImpl.getNavigationBarView() != null) {
                    NavigationBarView navigationBarView = centralSurfacesImpl.getNavigationBarView();
                    WindowManager.LayoutParams layoutParams4 = (WindowManager.LayoutParams) ((ViewGroup) navigationBarView.getParent()).getLayoutParams();
                    if (layoutParams4 != null) {
                        boolean z3 = layoutParams4.windowAnimations != 0;
                        if (!z3 && z2) {
                            layoutParams4.windowAnimations = R.style.Animation_NavigationBarFadeIn;
                        } else if (z3 && !z2) {
                            layoutParams4.windowAnimations = 0;
                        }
                        ((WindowManager) navigationBarView.getContext().getSystemService(WindowManager.class)).updateViewLayout((View) navigationBarView.getParent(), layoutParams4);
                    }
                    navigationBarView.mWakeAndUnlocking = z2;
                    navigationBarView.updateLayoutTransitionsEnabled();
                }
            }
        });
        BiometricUnlockController biometricUnlockController2 = this.mBiometricUnlockController;
        KeyguardViewMediator keyguardViewMediator = this.mKeyguardViewMediator;
        keyguardViewMediator.mCentralSurfaces = this;
        Lazy lazy4 = keyguardViewMediator.mKeyguardViewControllerLazy;
        final StatusBarKeyguardViewManager statusBarKeyguardViewManager = (StatusBarKeyguardViewManager) lazy4.get();
        statusBarKeyguardViewManager.mCentralSurfaces = this;
        statusBarKeyguardViewManager.mBiometricUnlockController = biometricUnlockController2;
        StatusBarKeyguardViewManager.AnonymousClass1 anonymousClass1 = statusBarKeyguardViewManager.mExpansionCallback;
        PrimaryBouncerCallbackInteractor primaryBouncerCallbackInteractor = statusBarKeyguardViewManager.mPrimaryBouncerCallbackInteractor;
        if (!primaryBouncerCallbackInteractor.expansionCallbacks.contains(anonymousClass1)) {
            primaryBouncerCallbackInteractor.expansionCallbacks.add(anonymousClass1);
        }
        statusBarKeyguardViewManager.mShadeLockscreenInteractor = this.mShadeSurface;
        ShadeExpansionStateManager shadeExpansionStateManager2 = this.mShadeExpansionStateManager;
        if (shadeExpansionStateManager2 != null) {
            statusBarKeyguardViewManager.onPanelExpansionChanged(shadeExpansionStateManager2.addExpansionListener(statusBarKeyguardViewManager));
        }
        statusBarKeyguardViewManager.mNotificationContainer = this.mStackScroller;
        statusBarKeyguardViewManager.mCentralSurfacesRegistered = true;
        statusBarKeyguardViewManager.mKeyguardUpdateManager.registerCallback(statusBarKeyguardViewManager.mUpdateMonitorCallback);
        ((StatusBarStateControllerImpl) statusBarKeyguardViewManager.mStatusBarStateController).addCallback((StatusBarStateController.StateListener) statusBarKeyguardViewManager);
        ((ConfigurationControllerImpl) statusBarKeyguardViewManager.mConfigurationController).addCallback(statusBarKeyguardViewManager);
        statusBarKeyguardViewManager.mGesturalNav = QuickStepContract.isGesturalMode(statusBarKeyguardViewManager.mNavigationModeController.addListener(statusBarKeyguardViewManager));
        FoldAodAnimationController foldAodAnimationController = statusBarKeyguardViewManager.mFoldAodAnimationController;
        if (foldAodAnimationController != null) {
            foldAodAnimationController.statusListeners.add(statusBarKeyguardViewManager);
        }
        DockManager dockManager2 = statusBarKeyguardViewManager.mDockManager;
        if (dockManager2 != null) {
            DockObserver dockObserver = (DockObserver) dockManager2;
            dockObserver.addListener(statusBarKeyguardViewManager.mDockEventListener);
            statusBarKeyguardViewManager.mIsDocked = dockObserver.isDocked();
        }
        StandaloneCoroutine standaloneCoroutine = statusBarKeyguardViewManager.mListenForCanShowAlternateBouncer;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(null);
        }
        statusBarKeyguardViewManager.mListenForCanShowAlternateBouncer = null;
        statusBarKeyguardViewManager.mListenForCanShowAlternateBouncer = statusBarKeyguardViewManager.mJavaAdapter.alwaysCollectFlow(statusBarKeyguardViewManager.mAlternateBouncerInteractor.canShowAlternateBouncer, new Consumer() { // from class: com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                StatusBarKeyguardViewManager statusBarKeyguardViewManager2 = StatusBarKeyguardViewManager.this;
                if (((Boolean) obj).booleanValue()) {
                    return;
                }
                Log.d("StatusBarKeyguardViewManager", "canShowAlternateBouncer turned false, maybe try hiding the alternate bouncer if it is already visible");
                AlternateBouncerInteractor alternateBouncerInteractor = statusBarKeyguardViewManager2.mAlternateBouncerInteractor;
                if (!alternateBouncerInteractor.isVisibleState() || ((Boolean) alternateBouncerInteractor.canShowAlternateBouncer.getValue()).booleanValue()) {
                    return;
                }
                alternateBouncerInteractor.hide();
            }
        });
        ((KeyguardStateControllerImpl) this.mKeyguardStateController).addCallback(this.mKeyguardStateControllerCallback);
        KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle = this.mKeyguardIndicationController;
        StatusBarKeyguardViewManager statusBarKeyguardViewManager2 = this.mStatusBarKeyguardViewManager;
        keyguardIndicationControllerGoogle.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager2;
        this.mBiometricUnlockController.mKeyguardViewController = statusBarKeyguardViewManager2;
        NotificationRemoteInputManager notificationRemoteInputManager2 = this.mRemoteInputManager;
        RemoteInputController remoteInputController2 = notificationRemoteInputManager2.mRemoteInputController;
        if (remoteInputController2 != null) {
            Objects.requireNonNull(statusBarKeyguardViewManager2);
            remoteInputController2.mCallbacks.add(statusBarKeyguardViewManager2);
        } else {
            notificationRemoteInputManager2.mControllerCallbacks.add(statusBarKeyguardViewManager2);
        }
        this.mLightBarController.mBiometricUnlockController = this.mBiometricUnlockController;
        Trace.endSection();
        this.mKeyguardUpdateMonitor.registerCallback(this.mUpdateCallback);
        DozeServiceHost dozeServiceHost = this.mDozeServiceHost;
        StatusBarKeyguardViewManager statusBarKeyguardViewManager3 = this.mStatusBarKeyguardViewManager;
        NotificationShadeWindowViewController notificationShadeWindowViewController3 = getNotificationShadeWindowViewController();
        View view = this.mAmbientIndicationContainer;
        dozeServiceHost.mCentralSurfaces = this;
        dozeServiceHost.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager3;
        dozeServiceHost.mNotificationShadeWindowViewController = notificationShadeWindowViewController3;
        dozeServiceHost.mAmbientIndicationContainer = view;
        LightRevealScrim lightRevealScrim3 = this.mLightRevealScrim;
        if (lightRevealScrim3 != null) {
            lightRevealScrim3.setAlpha(this.mScrimController.mState.getMaxLightRevealScrimAlpha());
        }
        ((ConfigurationControllerImpl) this.mConfigurationController).addCallback(this.mConfigurationListener);
        this.mBatteryController.observe(this.mLifecycle, this.mBatteryStateChangeCallback);
        this.mLifecycle.setCurrentState(Lifecycle.State.RESUMED);
        this.mAccessibilityFloatingMenuController.init$9();
        final int i10 = registerStatusBarResult.mDisabledFlags1;
        final int i11 = registerStatusBarResult.mDisabledFlags2;
        InitController initController = this.mInitController;
        Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda14
            @Override // java.lang.Runnable
            public final void run() {
                CentralSurfacesImpl centralSurfacesImpl = CentralSurfacesImpl.this;
                int i12 = i10;
                int i13 = i11;
                centralSurfacesImpl.mCommandQueue.disable(centralSurfacesImpl.mDisplayId, i12, i13, false);
                try {
                    Binder binder = new Binder();
                    centralSurfacesImpl.mBarService.disable(2097152, binder, centralSurfacesImpl.mContext.getPackageName());
                    centralSurfacesImpl.mBarService.disable(0, binder, centralSurfacesImpl.mContext.getPackageName());
                } catch (RemoteException e2) {
                    e2.rethrowFromSystemServer();
                }
            }
        };
        if (initController.mTasksExecuted) {
            throw new IllegalStateException("post init tasks have already been executed!");
        }
        initController.mTasks.add(runnable);
        registerCallbacks();
        this.mFalsingManager.addFalsingBeliefListener(this.mFalsingBeliefListener);
        this.mPluginManager.addPluginListener((PluginListener) new AnonymousClass3(this), OverlayPlugin.class, true);
        this.mStartingSurfaceOptional.ifPresent(new CentralSurfacesImpl$$ExternalSyntheticLambda2(this, 2));
    }

    public final void updateDozingState() {
        boolean z = false;
        if (Trace.isTagEnabled(4096L)) {
            Trace.asyncTraceForTrackEnd(4096L, "Dozing", 0);
            Trace.asyncTraceForTrackBegin(4096L, "Dozing", String.valueOf(this.mDozing), 0);
        }
        Trace.beginSection("CentralSurfaces#updateDozingState");
        KeyguardStateController keyguardStateController = this.mKeyguardStateController;
        keyguardStateController.getClass();
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) keyguardStateController;
        boolean z2 = keyguardStateControllerImpl.mShowing && !keyguardStateControllerImpl.mOccluded;
        DozeParameters dozeParameters = this.mDozeParameters;
        boolean z3 = z2 || (this.mDozing && dozeParameters.mScreenOffAnimationController.shouldDelayKeyguardShow());
        boolean z4 = this.mDozing;
        if ((!z4 && this.mDozeServiceHost.mAnimateWakeup && this.mBiometricUnlockController.mMode != 1) || (z4 && dozeParameters.mControlScreenOffAnimation && z3)) {
            z = true;
        }
        this.mShadeSurface.setDozing(z4, z);
        Trace.endSection();
    }

    /* JADX WARN: Type inference failed for: r1v12, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final boolean updateIsKeyguard(boolean z) {
        boolean z2;
        int i;
        boolean isWakeAndUnlock = this.mBiometricUnlockController.isWakeAndUnlock();
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
        boolean z3 = keyguardStateControllerImpl.mShowing && !keyguardStateControllerImpl.mOccluded;
        boolean z4 = this.mDozeServiceHost.mDozingRequested;
        ScreenLifecycle screenLifecycle = this.mScreenLifecycle;
        boolean z5 = z4 && (!this.mDeviceInteractive || (isGoingToSleep() && (screenLifecycle.mScreenState == 0 || z3)));
        boolean z6 = keyguardStateControllerImpl.mOccluded && ((i = this.mWakefulnessLifecycle.mWakefulness) == 1 || i == 2);
        SysuiStatusBarStateController sysuiStatusBarStateController = this.mStatusBarStateController;
        StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) sysuiStatusBarStateController;
        boolean z7 = ((!statusBarStateControllerImpl.mKeyguardRequested && !z5) || isWakeAndUnlock || z6) ? false : true;
        if (z5) {
            updatePanelExpansionForKeyguard();
        }
        LockscreenShadeTransitionController lockscreenShadeTransitionController = this.mLockscreenShadeTransitionController;
        MessageRouterImpl messageRouterImpl = this.mMessageRouter;
        ShadeSurface shadeSurface = this.mShadeSurface;
        ScreenOffAnimationController screenOffAnimationController = this.mScreenOffAnimationController;
        if (z7) {
            if (screenOffAnimationController.isKeyguardShowDelayed()) {
                return false;
            }
            if (isGoingToSleep() && screenLifecycle.mScreenState == 3) {
                return false;
            }
            Trace.beginSection("CentralSurfaces#showKeyguard");
            if (keyguardStateControllerImpl.mLaunchTransitionFadingAway) {
                shadeSurface.cancelAnimation();
                onLaunchTransitionFadingEnded();
            }
            messageRouterImpl.cancelMessages(1003);
            if (!lockscreenShadeTransitionController.isWakingToShadeLocked) {
                sysuiStatusBarStateController.getClass();
                ((StatusBarStateControllerImpl) sysuiStatusBarStateController).setState(1, false);
            }
            updatePanelExpansionForKeyguard();
            Trace.endSection();
            return false;
        }
        StringBuilder sb = new StringBuilder("!shouldBeKeyguard mStatusBarStateController.isKeyguardRequested() ");
        BiometricUnlockLogger$logCalculateModeForFingerprintUnlockingAllowed$2$$ExternalSyntheticOutline0.m(sb, statusBarStateControllerImpl.mKeyguardRequested, " keyguardForDozing ", z5, " wakeAndUnlocking ");
        sb.append(isWakeAndUnlock);
        sb.append(" isWakingAndOccluded ");
        sb.append(z6);
        Log.d("CentralSurfaces", sb.toString());
        List list = screenOffAnimationController.animations;
        if (list == null || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (((ScreenOffAnimation) it.next()).isKeyguardHideDelayed()) {
                    return false;
                }
            }
        }
        if (this.mKeyguardViewMediator.mOccludeAnimationPlaying) {
            return false;
        }
        Log.d("CentralSurfaces", "hideKeyguardImpl " + z);
        Trace.beginSection("CentralSurfaces#hideKeyguard");
        boolean z8 = statusBarStateControllerImpl.mLeaveOpenOnKeyguardHide;
        int i2 = statusBarStateControllerImpl.mState;
        if (!statusBarStateControllerImpl.setState(0, z)) {
            ((NotificationLockscreenUserManagerImpl) this.mLockscreenUserManager).updatePublicMode();
        }
        if (statusBarStateControllerImpl.mLeaveOpenOnKeyguardHide) {
            long j = keyguardStateControllerImpl.mKeyguardFadingAwayDelay + keyguardStateControllerImpl.mKeyguardFadingAwayDuration;
            LSShadeTransitionLogger lSShadeTransitionLogger = lockscreenShadeTransitionController.logger;
            LogLevel logLevel = LogLevel.INFO;
            LSShadeTransitionLogger$logOnHideKeyguard$2 lSShadeTransitionLogger$logOnHideKeyguard$2 = new Function1() { // from class: com.android.systemui.statusbar.phone.LSShadeTransitionLogger$logOnHideKeyguard$2
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return "Notified that the keyguard is being hidden";
                }
            };
            LogBuffer logBuffer = lSShadeTransitionLogger.buffer;
            logBuffer.commit(logBuffer.obtain("LockscreenShadeTransitionController", logLevel, lSShadeTransitionLogger$logOnHideKeyguard$2, null));
            ?? r1 = lockscreenShadeTransitionController.animationHandlerOnKeyguardDismiss;
            if (r1 != 0) {
                r1.invoke(Long.valueOf(j));
                lockscreenShadeTransitionController.animationHandlerOnKeyguardDismiss = null;
                z2 = false;
            } else if (lockscreenShadeTransitionController.nextHideKeyguardNeedsNoAnimation) {
                z2 = false;
                lockscreenShadeTransitionController.nextHideKeyguardNeedsNoAnimation = false;
            } else {
                z2 = false;
                if (i2 != 2) {
                    lockscreenShadeTransitionController.performDefaultGoToFullShadeAnimation(j);
                }
            }
            NotificationEntry notificationEntry = lockscreenShadeTransitionController.draggedDownEntry;
            if (notificationEntry != null) {
                ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
                if (expandableNotificationRow != null) {
                    expandableNotificationRow.setUserLocked(z2);
                }
                lockscreenShadeTransitionController.draggedDownEntry = null;
            }
            this.mNavigationBarController.disableAnimationsDuringHide(j, this.mDisplayId);
        } else if (!shadeSurface.isCollapsing()) {
            this.mShadeController.instantCollapseShade();
        }
        QSPanelController qSPanelController = this.mQSPanelController;
        if (qSPanelController != null) {
            qSPanelController.refreshAllTiles();
        }
        messageRouterImpl.cancelMessages(1003);
        releaseGestureWakeLock();
        ((CameraLauncher) this.mCameraLauncherLazy.get()).mKeyguardBypassController.launchingAffordance = false;
        shadeSurface.resetAlpha();
        shadeSurface.resetTranslation();
        shadeSurface.resetViewGroupFade();
        updateDozingState();
        updateScrimController();
        Trace.endSection();
        return z8;
    }

    public final void updateNotificationPanelTouchState() {
        boolean z = !(this.mDeviceInteractive || this.mDozeServiceHost.mPulsing) || (isGoingToSleep() && !this.mDozeParameters.mControlScreenOffAnimation);
        this.mShadeLogger.logUpdateNotificationPanelTouchState(z, isGoingToSleep(), !r2.mControlScreenOffAnimation, !this.mDeviceInteractive, !this.mDozeServiceHost.mPulsing);
        this.mShadeSurface.setTouchAndAnimationDisabled(z);
    }

    public final void updatePanelExpansionForKeyguard() {
        if (this.mState != 1 || this.mBiometricUnlockController.mMode == 1 || this.mBouncerShowing) {
            return;
        }
        this.mShadeController.instantExpandShade();
    }

    public final void updateReportRejectedTouchVisibility() {
        View view = this.mReportRejectedTouch;
        if (view == null) {
            return;
        }
        if (this.mState == 1 && !this.mDozing) {
            this.mFalsingCollector.getClass();
        }
        view.setVisibility(4);
    }

    public final void updateResources() {
        QSPanelController qSPanelController = this.mQSPanelController;
        if (qSPanelController != null) {
            ((QSPanel) qSPanelController.mView).updateResources();
        }
        ShadeSurface shadeSurface = this.mShadeSurface;
        if (shadeSurface != null) {
            shadeSurface.updateResources();
        }
        BrightnessMirrorController brightnessMirrorController = this.mBrightnessMirrorController;
        if (brightnessMirrorController != null) {
            brightnessMirrorController.updateResources();
        }
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.mStatusBarKeyguardViewManager;
        if (statusBarKeyguardViewManager != null) {
            KeyguardBouncerRepositoryImpl keyguardBouncerRepositoryImpl = statusBarKeyguardViewManager.mPrimaryBouncerInteractor.repository;
            Boolean bool = Boolean.TRUE;
            StateFlowImpl stateFlowImpl = keyguardBouncerRepositoryImpl._resourceUpdateRequests;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, bool);
        }
        this.mPowerButtonReveal = new PowerButtonReveal(this.mContext.getResources().getDimensionPixelSize(R.dimen.physical_power_button_center_screen_location_y));
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0027, code lost:
    
        if (r1.mSurfaceBehindRemoteAnimationRunning == false) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:21:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0070  */
    @Override // com.android.systemui.statusbar.phone.CentralSurfaces
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateScrimController() {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.CentralSurfacesImpl.updateScrimController():void");
    }

    public final void updateTheme() {
        ArrayList<ConfigurationController.ConfigurationListener> arrayList;
        this.mUiBgExecutor.execute(new CentralSurfacesImpl$$ExternalSyntheticLambda1(this, 1));
        int i = this.mColorExtractor.mNeutralColorsLock.supportsDarkText() ? R.style.Theme_SystemUI_LightWallpaper : R.style.Theme_SystemUI;
        if (this.mContext.getThemeResId() != i) {
            this.mContext.setTheme(i);
            ConfigurationControllerImpl configurationControllerImpl = (ConfigurationControllerImpl) this.mConfigurationController;
            synchronized (configurationControllerImpl.listeners) {
                arrayList = new ArrayList(configurationControllerImpl.listeners);
            }
            for (ConfigurationController.ConfigurationListener configurationListener : arrayList) {
                if (configurationControllerImpl.listeners.contains(configurationListener)) {
                    configurationListener.onThemeChanged();
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.CentralSurfacesImpl$3, reason: invalid class name */
    public final class AnonymousClass3 implements OverlayPlugin.Callback, PluginListener {
        public final Object mOverlays;
        public final /* synthetic */ Object this$0;

        public AnonymousClass3(CentralSurfacesImpl centralSurfacesImpl) {
            this.this$0 = centralSurfacesImpl;
            this.mOverlays = new ArraySet();
        }

        @Override // com.android.systemui.plugins.OverlayPlugin.Callback
        public void onHoldStatusBarOpenChange() {
            OverlayPlugin overlayPlugin = (OverlayPlugin) this.mOverlays;
            boolean holdStatusBarOpen = overlayPlugin.holdStatusBarOpen();
            AnonymousClass3 anonymousClass3 = (AnonymousClass3) this.this$0;
            if (holdStatusBarOpen) {
                ((ArraySet) anonymousClass3.mOverlays).add(overlayPlugin);
            } else {
                ((ArraySet) anonymousClass3.mOverlays).remove(overlayPlugin);
            }
            ((ExecutorImpl) ((CentralSurfacesImpl) anonymousClass3.this$0).mMainExecutor).execute(new CentralSurfacesImpl$$ExternalSyntheticLambda25(1, this));
        }

        @Override // com.android.systemui.plugins.PluginListener
        public void onPluginConnected(Plugin plugin, Context context) {
            ExecutorImpl executorImpl = (ExecutorImpl) ((CentralSurfacesImpl) this.this$0).mMainExecutor;
            executorImpl.execute(new CentralSurfacesImpl$3$$ExternalSyntheticLambda0(0, this, (OverlayPlugin) plugin));
        }

        @Override // com.android.systemui.plugins.PluginListener
        public void onPluginDisconnected(Plugin plugin) {
            ExecutorImpl executorImpl = (ExecutorImpl) ((CentralSurfacesImpl) this.this$0).mMainExecutor;
            executorImpl.execute(new CentralSurfacesImpl$3$$ExternalSyntheticLambda0(1, this, (OverlayPlugin) plugin));
        }

        public AnonymousClass3(AnonymousClass3 anonymousClass3, OverlayPlugin overlayPlugin) {
            this.this$0 = anonymousClass3;
            this.mOverlays = overlayPlugin;
        }
    }
}

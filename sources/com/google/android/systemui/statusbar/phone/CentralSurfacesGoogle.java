package com.google.android.systemui.statusbar.phone;

import android.app.AlarmManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.IntentFilter;
import android.hardware.devicestate.DeviceStateManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.service.dreams.IDreamManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.viewpager.widget.ViewPager$$ExternalSyntheticOutline0;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.InitController;
import com.android.systemui.accessibility.floatingmenu.AccessibilityFloatingMenuController;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.back.domain.interactor.BackActionInteractor;
import com.android.systemui.biometrics.AuthRippleController;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.charging.WiredChargingRippleController;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.colorextraction.SysuiColorExtractor;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.emergency.EmergencyGestureModule$emergencyGestureIntentFactory$1;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.fragments.FragmentService;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.PluginDependencyProvider;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.scene.domain.interactor.WindowRootViewVisibilityInteractor;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.brightness.BrightnessSliderController;
import com.android.systemui.settings.brightness.domain.interactor.BrightnessMirrorShowingInteractor;
import com.android.systemui.shade.GlanceableHubContainerController;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.QuickSettingsController;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeExpansionStateManager;
import com.android.systemui.shade.ShadeLogger;
import com.android.systemui.shade.ShadeSurface;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.LightRevealScrim;
import com.android.systemui.statusbar.LockscreenShadeTransitionController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.PulseExpansionHandler;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.core.StatusBarInitializerImpl;
import com.android.systemui.statusbar.data.repository.StatusBarModeRepositoryImpl;
import com.android.systemui.statusbar.notification.NotificationLaunchAnimatorControllerProvider;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.init.NotificationsController;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.AutoHideController;
import com.android.systemui.statusbar.phone.CentralSurfacesImpl;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.DozeScrimController;
import com.android.systemui.statusbar.phone.DozeServiceHost;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.PhoneStatusBarPolicy;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.StatusBarHideIconsForBouncerManager;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarSignalPolicy;
import com.android.systemui.statusbar.phone.StatusBarTouchableRegionManager;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.android.systemui.statusbar.window.StatusBarWindowStateController;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.util.WallpaperController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.MessageRouterImpl;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.volume.VolumeComponent;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17;
import com.google.android.systemui.dreamliner.DockAlignmentController;
import com.google.android.systemui.dreamliner.DockAlignmentController$$ExternalSyntheticLambda0;
import com.google.android.systemui.dreamliner.DockIndicationController;
import com.google.android.systemui.dreamliner.DockObserver;
import com.google.android.systemui.dreamliner.DockObserver$$ExternalSyntheticLambda3;
import com.google.android.systemui.dreamliner.WirelessCharger;
import com.google.android.systemui.reversecharging.ReverseChargingViewController;
import com.google.android.systemui.statusbar.KeyguardIndicationControllerGoogle;
import com.google.android.systemui.statusbar.phone.WallpaperNotifier;
import dagger.Lazy;
import dagger.internal.Provider;
import java.util.Optional;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CentralSurfacesGoogle extends CentralSurfacesImpl {
    public static final boolean DEBUG = Log.isLoggable("CentralSurfacesGoogle", 3);
    public final ActivityStarter mActivityStarter;
    public long mAnimStartTime;
    public final AnonymousClass1 mBatteryStateChangeCallback;
    public final BroadcastSender mBroadcastSender;
    public boolean mChargingAnimShown;
    public final Context mContext;
    public final DockObserver mDockObserver;
    public final Optional mHealthManagerOptional;
    public final KeyguardIndicationControllerGoogle mKeyguardIndicationController;
    public final NotificationShadeWindowController mNotificationShadeWindowController;
    public int mReceivingBatteryLevel;
    public boolean mReverseChargingAnimShown;
    public final Optional mReverseChargingViewControllerOptional;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public final WallpaperNotifier mWallpaperNotifier;

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.systemui.statusbar.phone.CentralSurfacesGoogle$1] */
    public CentralSurfacesGoogle(WallpaperNotifier wallpaperNotifier, Optional optional, DockObserver dockObserver, Optional optional2, Context context, NotificationsController notificationsController, FragmentService fragmentService, LightBarController lightBarController, AutoHideController autoHideController, StatusBarInitializerImpl statusBarInitializerImpl, StatusBarWindowControllerImpl statusBarWindowControllerImpl, StatusBarWindowStateController statusBarWindowStateController, StatusBarModeRepositoryImpl statusBarModeRepositoryImpl, KeyguardUpdateMonitor keyguardUpdateMonitor, StatusBarSignalPolicy statusBarSignalPolicy, PulseExpansionHandler pulseExpansionHandler, NotificationWakeUpCoordinator notificationWakeUpCoordinator, KeyguardBypassController keyguardBypassController, KeyguardStateController keyguardStateController, HeadsUpManager headsUpManager, FalsingManager falsingManager, FalsingCollector falsingCollector, BroadcastDispatcher broadcastDispatcher, BroadcastSender broadcastSender, NotificationGutsManager notificationGutsManager, ShadeExpansionStateManager shadeExpansionStateManager, KeyguardViewMediator keyguardViewMediator, DisplayMetrics displayMetrics, MetricsLogger metricsLogger, ShadeLogger shadeLogger, JavaAdapter javaAdapter, Executor executor, ShadeSurface shadeSurface, NotificationMediaManager notificationMediaManager, NotificationLockscreenUserManager notificationLockscreenUserManager, NotificationRemoteInputManager notificationRemoteInputManager, QuickSettingsController quickSettingsController, BatteryController batteryController, SysuiColorExtractor sysuiColorExtractor, ScreenLifecycle screenLifecycle, WakefulnessLifecycle wakefulnessLifecycle, PowerInteractor powerInteractor, CommunalInteractor communalInteractor, SysuiStatusBarStateController sysuiStatusBarStateController, Optional optional3, Lazy lazy, DeviceProvisionedController deviceProvisionedController, NavigationBarControllerImpl navigationBarControllerImpl, AccessibilityFloatingMenuController accessibilityFloatingMenuController, Lazy lazy2, ConfigurationController configurationController, NotificationShadeWindowController notificationShadeWindowController, Lazy lazy3, NotificationStackScrollLayoutController notificationStackScrollLayoutController, Lazy lazy4, Lazy lazy5, NotificationLaunchAnimatorControllerProvider notificationLaunchAnimatorControllerProvider, DozeParameters dozeParameters, ScrimController scrimController, Lazy lazy6, AuthRippleController authRippleController, Lazy lazy7, DozeServiceHost dozeServiceHost, BackActionInteractor backActionInteractor, PowerManager powerManager, DozeScrimController dozeScrimController, VolumeComponent volumeComponent, CommandQueue commandQueue, Lazy lazy8, PluginManager pluginManager, ShadeController shadeController, WindowRootViewVisibilityInteractor windowRootViewVisibilityInteractor, StatusBarKeyguardViewManager statusBarKeyguardViewManager, KeyguardViewMediator.AnonymousClass4 anonymousClass4, InitController initController, Handler handler, PluginDependencyProvider pluginDependencyProvider, ExtensionControllerImpl extensionControllerImpl, UserInfoControllerImpl userInfoControllerImpl, PhoneStatusBarPolicy phoneStatusBarPolicy, KeyguardIndicationControllerGoogle keyguardIndicationControllerGoogle, DemoModeController demoModeController, StatusBarTouchableRegionManager statusBarTouchableRegionManager, BrightnessSliderController.Factory factory, ScreenOffAnimationController screenOffAnimationController, WallpaperController wallpaperController, StatusBarHideIconsForBouncerManager statusBarHideIconsForBouncerManager, LockscreenShadeTransitionController lockscreenShadeTransitionController, FeatureFlagsClassic featureFlagsClassic, KeyguardUnlockAnimationController keyguardUnlockAnimationController, DelayableExecutor delayableExecutor, MessageRouterImpl messageRouterImpl, WallpaperManager wallpaperManager, Optional optional4, ActivityTransitionAnimator activityTransitionAnimator, AlarmManager alarmManager, DeviceStateManager deviceStateManager, WiredChargingRippleController wiredChargingRippleController, IDreamManager iDreamManager, Lazy lazy9, Lazy lazy10, LightRevealScrim lightRevealScrim, AlternateBouncerInteractor alternateBouncerInteractor, UserTracker userTracker, Provider provider, ActivityStarter activityStarter, SelectedUserInteractor selectedUserInteractor, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17, BrightnessMirrorShowingInteractor brightnessMirrorShowingInteractor, GlanceableHubContainerController glanceableHubContainerController, EmergencyGestureModule$emergencyGestureIntentFactory$1 emergencyGestureModule$emergencyGestureIntentFactory$1, ViewCaptureAwareWindowManager viewCaptureAwareWindowManager) {
        super(context, notificationsController, fragmentService, lightBarController, autoHideController, statusBarInitializerImpl, statusBarWindowControllerImpl, statusBarWindowStateController, statusBarModeRepositoryImpl, keyguardUpdateMonitor, statusBarSignalPolicy, pulseExpansionHandler, notificationWakeUpCoordinator, keyguardBypassController, keyguardStateController, headsUpManager, falsingManager, falsingCollector, broadcastDispatcher, notificationGutsManager, shadeExpansionStateManager, keyguardViewMediator, displayMetrics, metricsLogger, shadeLogger, javaAdapter, executor, shadeSurface, notificationMediaManager, notificationLockscreenUserManager, notificationRemoteInputManager, quickSettingsController, batteryController, sysuiColorExtractor, screenLifecycle, wakefulnessLifecycle, powerInteractor, communalInteractor, sysuiStatusBarStateController, optional3, lazy, deviceProvisionedController, navigationBarControllerImpl, accessibilityFloatingMenuController, lazy2, configurationController, notificationShadeWindowController, lazy3, notificationStackScrollLayoutController, lazy4, lazy5, notificationLaunchAnimatorControllerProvider, dozeParameters, scrimController, lazy6, authRippleController, dozeServiceHost, backActionInteractor, powerManager, dozeScrimController, volumeComponent, commandQueue, lazy8, pluginManager, shadeController, windowRootViewVisibilityInteractor, statusBarKeyguardViewManager, anonymousClass4, initController, handler, pluginDependencyProvider, extensionControllerImpl, userInfoControllerImpl, phoneStatusBarPolicy, keyguardIndicationControllerGoogle, demoModeController, lazy7, statusBarTouchableRegionManager, factory, screenOffAnimationController, wallpaperController, statusBarHideIconsForBouncerManager, lockscreenShadeTransitionController, featureFlagsClassic, keyguardUnlockAnimationController, delayableExecutor, messageRouterImpl, wallpaperManager, optional4, activityTransitionAnimator, deviceStateManager, wiredChargingRippleController, iDreamManager, lazy9, lazy10, lightRevealScrim, alternateBouncerInteractor, userTracker, provider, activityStarter, brightnessMirrorShowingInteractor, glanceableHubContainerController, emergencyGestureModule$emergencyGestureIntentFactory$1, viewCaptureAwareWindowManager);
        this.mBatteryStateChangeCallback = new BatteryController.BatteryStateChangeCallback() { // from class: com.google.android.systemui.statusbar.phone.CentralSurfacesGoogle.1
            @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
            public final void onBatteryLevelChanged(int i, boolean z, boolean z2) {
                CentralSurfacesGoogle centralSurfacesGoogle = CentralSurfacesGoogle.this;
                centralSurfacesGoogle.mReceivingBatteryLevel = i;
                BatteryController batteryController2 = centralSurfacesGoogle.mBatteryController;
                if (!((BatteryControllerImpl) batteryController2).mWirelessCharging) {
                    if (SystemClock.uptimeMillis() - centralSurfacesGoogle.mAnimStartTime > 1500) {
                        centralSurfacesGoogle.mChargingAnimShown = false;
                    }
                    centralSurfacesGoogle.mReverseChargingAnimShown = false;
                }
                if (CentralSurfacesGoogle.DEBUG) {
                    StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("onBatteryLevelChanged(): level=", ",wlc=", i);
                    m.append(((BatteryControllerImpl) batteryController2).mWirelessCharging ? 1 : 0);
                    m.append(",wlcs=");
                    m.append(centralSurfacesGoogle.mChargingAnimShown);
                    m.append(",rtxs=");
                    m.append(centralSurfacesGoogle.mReverseChargingAnimShown);
                    m.append(",this=");
                    m.append(this);
                    Log.d("CentralSurfacesGoogle", m.toString());
                }
            }

            @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
            public final void onReverseChanged(int i, String str, boolean z) {
                CentralSurfacesGoogle centralSurfacesGoogle = CentralSurfacesGoogle.this;
                if (!z && i >= 0 && !TextUtils.isEmpty(str) && ((BatteryControllerImpl) centralSurfacesGoogle.mBatteryController).mWirelessCharging && centralSurfacesGoogle.mChargingAnimShown && !centralSurfacesGoogle.mReverseChargingAnimShown) {
                    centralSurfacesGoogle.mReverseChargingAnimShown = true;
                    long uptimeMillis = SystemClock.uptimeMillis() - centralSurfacesGoogle.mAnimStartTime;
                    centralSurfacesGoogle.showChargingAnimation(centralSurfacesGoogle.mReceivingBatteryLevel, i, uptimeMillis > 1500 ? 0L : 1500 - uptimeMillis);
                }
                if (CentralSurfacesGoogle.DEBUG) {
                    StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("onReverseChanged(): rtx=", ",rxlevel=", z ? 1 : 0);
                    ViewPager$$ExternalSyntheticOutline0.m(m, centralSurfacesGoogle.mReceivingBatteryLevel, ",level=", i, ",name=");
                    m.append(str);
                    m.append(",wlc=");
                    m.append(((BatteryControllerImpl) centralSurfacesGoogle.mBatteryController).mWirelessCharging ? 1 : 0);
                    m.append(",wlcs=");
                    m.append(centralSurfacesGoogle.mChargingAnimShown);
                    m.append(",rtxs=");
                    m.append(centralSurfacesGoogle.mReverseChargingAnimShown);
                    m.append(",this=");
                    m.append(this);
                    Log.d("CentralSurfacesGoogle", m.toString());
                }
            }
        };
        this.mContext = context;
        this.mBroadcastSender = broadcastSender;
        this.mWallpaperNotifier = wallpaperNotifier;
        this.mReverseChargingViewControllerOptional = optional;
        this.mHealthManagerOptional = optional2;
        this.mKeyguardIndicationController = keyguardIndicationControllerGoogle;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mNotificationShadeWindowController = notificationShadeWindowController;
        this.mDockObserver = dockObserver;
        this.mActivityStarter = activityStarter;
    }

    @Override // com.android.systemui.statusbar.phone.CentralSurfacesImpl, com.android.systemui.CoreStartable
    public final void start() {
        super.start();
        this.mBatteryController.observe(this.mLifecycle, this.mBatteryStateChangeCallback);
        if (!this.mContext.getResources().getBoolean(R.bool.config_show_low_light_clock_when_docked)) {
            ImageView imageView = (ImageView) getNotificationShadeWindowViewController().mView.findViewById(R.id.dreamliner_gear);
            DockObserver dockObserver = this.mDockObserver;
            if (imageView == null) {
                dockObserver.getClass();
                Log.e("DLObserver", "set null for dreamlinerGear");
            } else {
                dockObserver.mDreamlinerGear = imageView;
            }
            dockObserver.mPhotoPreview = (FrameLayout) getNotificationShadeWindowViewController().mView.findViewById(R.id.photo_preview);
            DockIndicationController dockIndicationController = new DockIndicationController(this.mContext, this.mBroadcastSender, this.mKeyguardIndicationController, this.mStatusBarStateController, this.mNotificationShadeWindowController);
            dockObserver.mIndicationController = dockIndicationController;
            ((ConfigurationControllerImpl) dockObserver.mConfigurationController).addCallback(dockIndicationController);
            if (dockObserver.mWirelessCharger.isEmpty()) {
                Log.w("DLObserver", "wirelessCharger is not present");
            } else {
                DockAlignmentController dockAlignmentController = dockObserver.mDockAlignmentController;
                if (dockAlignmentController.mWirelessCharger.isPresent()) {
                    ((WirelessCharger) dockAlignmentController.mWirelessCharger.get()).registerAlignInfo(new DockAlignmentController$$ExternalSyntheticLambda0(dockAlignmentController));
                } else {
                    Log.w("DockAlignmentController", "wirelessCharger is null");
                }
                dockAlignmentController.mDockAlignmentStateChangeListeners.add(new DockObserver$$ExternalSyntheticLambda3(dockObserver));
            }
        }
        this.mHealthManagerOptional.ifPresent(new CentralSurfacesGoogle$$ExternalSyntheticLambda0());
        if (this.mReverseChargingViewControllerOptional.isPresent()) {
            ReverseChargingViewController reverseChargingViewController = (ReverseChargingViewController) this.mReverseChargingViewControllerOptional.get();
            reverseChargingViewController.mBatteryController.observe(reverseChargingViewController.mLifecycle, reverseChargingViewController);
            LifecycleRegistry lifecycleRegistry = reverseChargingViewController.mLifecycle;
            Lifecycle.State state = Lifecycle.State.RESUMED;
            lifecycleRegistry.enforceMainThreadIfNeeded("markState");
            lifecycleRegistry.setCurrentState(state);
            NotificationShadeWindowView notificationShadeWindowView = ((NotificationShadeWindowControllerImpl) reverseChargingViewController.mNotificationShadeWindowController).mWindowRootView;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.LOCALE_CHANGED");
            reverseChargingViewController.mBroadcastDispatcher.registerReceiver(reverseChargingViewController, intentFilter);
        }
        WallpaperNotifier wallpaperNotifier = this.mWallpaperNotifier;
        ((NotifPipeline) wallpaperNotifier.mNotifCollection).addCollectionListener(wallpaperNotifier.mNotifListener);
        IntentFilter intentFilter2 = new IntentFilter("android.intent.action.WALLPAPER_CHANGED");
        WallpaperNotifier.AnonymousClass2 anonymousClass2 = wallpaperNotifier.mWallpaperChangedReceiver;
        Executor executor = wallpaperNotifier.mBgExecutor;
        BroadcastDispatcher broadcastDispatcher = wallpaperNotifier.mBroadcastDispatcher;
        broadcastDispatcher.getClass();
        BroadcastDispatcher.registerReceiver$default(broadcastDispatcher, anonymousClass2, intentFilter2, executor, null, 0, 56);
        wallpaperNotifier.mBgExecutor.execute(new WallpaperNotifier$$ExternalSyntheticLambda0(0, wallpaperNotifier));
    }
}

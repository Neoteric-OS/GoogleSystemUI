package com.google.android.systemui.dagger;

import android.app.WallpaperManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.hardware.display.AmbientDisplayConfiguration;
import android.util.DisplayMetrics;
import com.android.systemui.dagger.AndroidInternalsModule;
import com.android.systemui.dagger.FrameworkServicesModule;
import com.android.systemui.dagger.GlobalModule;
import com.android.systemui.unfold.FoldStateProviderModule;
import com.android.systemui.unfold.HingeAngleProviderInternalModule;
import com.android.systemui.unfold.UnfoldRotationProviderInternalModule;
import com.android.systemui.unfold.UnfoldSharedInternalModule;
import com.android.systemui.unfold.UnfoldSharedModule;
import com.android.systemui.unfold.UnfoldTransitionModule;
import com.android.systemui.user.utils.UserScopedServiceImpl;
import com.android.systemui.util.kotlin.GlobalCoroutinesModule;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl {
    public final AndroidInternalsModule androidInternalsModule;
    public final Context context;
    public final Provider deviceStateRepositoryImplProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider executionImplProvider;
    public final Provider factoryProvider;
    public final Provider factoryProvider2;
    public final Provider factoryProvider3;
    public final Provider factoryProvider4;
    public final Provider factoryProvider5;
    public final Provider factoryProvider6;
    public final Provider factoryProvider7;
    public final Provider factoryProvider8;
    public final DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider fixedTimingTransitionProgressProvider;
    public final FoldStateProviderModule foldStateProviderModule;
    public final FrameworkServicesModule frameworkServicesModule;
    public final GlobalCoroutinesModule globalCoroutinesModule;
    public final GlobalModule globalModule;
    public final HingeAngleProviderInternalModule hingeAngleProviderInternalModule;
    public final Boolean instrumentationTest;
    public final Provider lowLightTransitionCoordinatorProvider;
    public final Provider opaEnabledSettingsProvider;
    public final Provider pluginDependencyProvider;
    public final Provider provideAmbientContextManagerProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider provideApplicationContextProvider;
    public final Provider provideAsyncLayoutInflaterProvider;
    public final Provider provideBluetoothAdapterProvider;
    public final Provider provideBluetoothManagerProvider;
    public final Provider provideColorDisplayManagerProvider;
    public final Provider provideCrossWindowBlurListenersProvider;
    public final Provider provideDeviceIdleControllerProvider;
    public final Provider provideDisplaySwitchLatencyLoggerProvider;
    public final Provider provideExecutionProvider;
    public final Provider provideFoldLockSettingAvailabilityProvider;
    public final Provider provideIActivityTaskManagerProvider;
    public final Provider provideIAudioServiceProvider;
    public final Provider provideIMediaProjectionManagerProvider;
    public final Provider provideINotificationManagerProvider;
    public final Provider provideIPackageManagerProvider;
    public final Provider provideIUriGrantsManagerProvider;
    public final Provider provideIVrManagerProvider;
    public final Provider provideInputManagerProvider;
    public final Provider provideJobSchedulerProvider;
    public final Provider provideLocationManagerProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider provideMainHandlerProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider provideMediaRouter2ManagerProvider;
    public final Provider provideNearbyManagerProvider;
    public final Provider provideNetworkScoreManagerProvider;
    public final Provider provideNotificationManagerCompatProvider;
    public final Provider provideOptionalTelecomManagerProvider;
    public final Provider provideOptionalVibratorProvider;
    public final Provider provideOverlayManagerProvider;
    public final Provider providePackageManagerWrapperProvider;
    public final Provider providePermissionManagerProvider;
    public final Provider provideProgressForwarderProvider;
    public final Provider provideRoleManagerProvider;
    public final Provider provideSafetyCenterManagerProvider;
    public final Provider provideSatelliteManagerProvider;
    public final Provider provideScopedColorDisplayManagerProvider;
    public final Provider provideSensorPrivacyManagerProvider;
    public final Provider provideShortcutManagerProvider;
    public final Provider provideStatsManagerProvider;
    public final Provider provideStorageManagerProvider;
    public final Provider provideTextClassificationManagerProvider;
    public final Provider provideUiBackgroundExecutorServiceProvider;
    public final Provider provideUiModeManagerProvider;
    public final Provider provideUsageStatsManagerProvider;
    public final Provider provideUserScopedCaptioningManagerProvider;
    public final Provider provideVirtualDeviceManagerProvider;
    public final Provider providesFoldStateLoggerProvider;
    public final Provider providesFoldStateLoggingProvider;
    public final Provider qSExpansionPathInterpolatorProvider;
    public final Provider unfoldBgDispatcherProvider;
    public final UnfoldRotationProviderInternalModule unfoldRotationProviderInternalModule;
    public final UnfoldSharedInternalModule unfoldSharedInternalModule;
    public final UnfoldSharedModule unfoldSharedModule;
    public final UnfoldTransitionModule unfoldTransitionModule;
    public final DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider unfoldTransitionProgressForwarderProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl = this;
    public final Provider systemPropertiesHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 0);
    public final Provider provideIWindowManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 1);
    public final Provider provideAccessibilityManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 2);
    public final Provider resourceUnfoldTransitionConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 4);
    public final Provider provideDeviceStateManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 6);
    public final Provider deviceStateManagerFoldProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 5);
    public final Provider provideContentResolverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 9);
    public final Provider dumpManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 18);
    public final Provider screenLifecycleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 17);
    public final Provider lifecycleScreenStatusProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 16);
    public final Provider provideActivityManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 20);
    public final Provider activityManagerActivityTypeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 19);
    public final Provider unfoldKeyguardVisibilityManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 22);
    public final Provider unfoldKeyguardVisibilityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 21);
    public final Provider providesSensorManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 25);
    public final Provider provideUiBackgroundExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 26);
    public final Provider provideDisplayManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 29);
    public final Provider provideBgLooperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 31);
    public final Provider unfoldBgProgressHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 30);
    public final Provider provideRotationChangeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 27);
    public final Provider provideFoldStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 14);
    public final Provider provideBgRotationChangeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 35);
    public final Provider provideBgFoldStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 34);
    public final Provider unfoldBgTransitionProgressProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 33);
    public final Provider unfoldBgProgressFlagProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 36);
    public final Provider unfoldTransitionProgressProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 7);
    public final Provider provideMainExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 38);
    public final Provider provideUnfoldOnlyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 37);
    public final Provider provideShellProgressProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 3);
    public final Provider provideUiEventLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 39);
    public final Provider provideActivityTaskManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 40);
    public final Provider provideKeyguardManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 41);
    public final Provider provideInteractionJankMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 42);
    public final Provider provideWindowManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 43);
    public final Provider provideIStatusBarServiceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 44);
    public final Provider provideUserManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 45);
    public final Provider provideLauncherAppsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 46);
    public final Provider providePackageManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 47);
    public final Provider provideIsTestHarnessProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 49);
    public final Provider providePowerManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 50);
    public final Provider mainCoroutineContextProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 52);
    public final Provider applicationScopeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 51);
    public final Provider provideAlarmManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 53);
    public final Provider providesFoldStateListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 54);
    public final Provider provideLockPatternUtilsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 55);
    public final Provider providesFingerprintManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 57);
    public final Provider provideFaceManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 58);
    public final Provider providerLayoutInflaterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 59);
    public final Provider provideViewCaptureProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 61);
    public final Provider provideViewCaptureAwareWindowManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 60);
    public final Provider provideMainDelayableExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 62);
    public final Provider provideTrustManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 63);
    public final Provider mainDispatcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 64);
    public final Provider providesChoreographerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 65);
    public final Provider provideIActivityManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 66);
    public final Provider provideDevicePolicyManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 67);
    public final Provider providesBiometricManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 68);
    public final Provider provideCameraManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 69);
    public final Provider provideConnectivityManagagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 70);
    public final Provider provideSubscriptionManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 71);
    public final Provider provideTelephonyManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 72);
    public final Provider provideWifiManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 73);
    public final Provider provideCarrierConfigManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 74);
    public final Provider providesPluginExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 77);
    public final Provider provideNotificationManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 78);
    public final Provider pluginEnablerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 79);
    public final Provider providesPluginInstanceFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 80);
    public final Provider providePluginInstanceManagerFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 76);
    public final Provider uncaughtExceptionPreHandlerManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 81);
    public final Provider providesPluginManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 75);
    public final Provider provideMetricsLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 82);
    public final Provider provideViewConfigurationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 83);
    public final Provider provideTelecomManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 84);
    public final Provider provideInputMethodManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 85);
    public final Provider provideIBatteryStatsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 86);
    public final Provider provideAppWidgetManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 87);
    public final Provider providePackageInstallerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 88);
    public final Provider provideSmartspaceManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 90);
    public final Provider provideStatusBarManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 91);
    public final Provider provideIDreamManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 92);
    public final Provider provideAudioManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 93);
    public final Provider providePowerExemptionManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 94);
    public final Provider provideVibratorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 95);
    public final Provider provideLatencyTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 96);
    public final Provider provideNaturalRotationProgressProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 97);
    public final Provider provideStatusBarScopedTransitionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(this, 98);

    /* renamed from: -$$Nest$mambientDisplayConfiguration, reason: not valid java name */
    public static AmbientDisplayConfiguration m973$$Nest$mambientDisplayConfiguration(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.frameworkServicesModule.getClass();
        return new AmbientDisplayConfiguration(context);
    }

    /* renamed from: -$$Nest$mapplicationAssetManager, reason: not valid java name */
    public static AssetManager m974$$Nest$mapplicationAssetManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        AssetManager assets = ((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get()).getAssets();
        Preconditions.checkNotNullFromProvides(assets);
        return assets;
    }

    /* renamed from: -$$Nest$mdisplayMetrics, reason: not valid java name */
    public static DisplayMetrics m976$$Nest$mdisplayMetrics(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.globalModule.getClass();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        context.getDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /* renamed from: -$$Nest$mmainResources, reason: not valid java name */
    public static Resources m977$$Nest$mmainResources(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        Resources resources = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        return resources;
    }

    /* renamed from: -$$Nest$muserScopedServiceOfClipboardManager, reason: not valid java name */
    public static UserScopedServiceImpl m978$$Nest$muserScopedServiceOfClipboardManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        return new UserScopedServiceImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, ClipboardManager.class);
    }

    /* renamed from: -$$Nest$mwallpaperManager, reason: not valid java name */
    public static WallpaperManager m979$$Nest$mwallpaperManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) {
        WallpaperManager wallpaperManager = (WallpaperManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(WallpaperManager.class);
        Preconditions.checkNotNullFromProvides(wallpaperManager);
        return wallpaperManager;
    }

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl(GlobalModule globalModule, AndroidInternalsModule androidInternalsModule, FrameworkServicesModule frameworkServicesModule, GlobalCoroutinesModule globalCoroutinesModule, UnfoldTransitionModule unfoldTransitionModule, UnfoldSharedModule unfoldSharedModule, UnfoldSharedInternalModule unfoldSharedInternalModule, UnfoldRotationProviderInternalModule unfoldRotationProviderInternalModule, HingeAngleProviderInternalModule hingeAngleProviderInternalModule, FoldStateProviderModule foldStateProviderModule, Context context, Boolean bool) {
        this.instrumentationTest = bool;
        this.context = context;
        this.unfoldTransitionModule = unfoldTransitionModule;
        this.unfoldSharedInternalModule = unfoldSharedInternalModule;
        this.foldStateProviderModule = foldStateProviderModule;
        this.unfoldSharedModule = unfoldSharedModule;
        this.unfoldRotationProviderInternalModule = unfoldRotationProviderInternalModule;
        this.globalModule = globalModule;
        this.globalCoroutinesModule = globalCoroutinesModule;
        this.androidInternalsModule = androidInternalsModule;
        this.frameworkServicesModule = frameworkServicesModule;
        int i = 1;
        this.factoryProvider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(8, i, this));
        this.factoryProvider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(10, i, this));
        this.factoryProvider3 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(12, i, this));
        this.factoryProvider4 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(11, i, this));
        this.factoryProvider5 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(15, i, this));
        this.provideMainHandlerProvider = new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(23, i, this);
        this.factoryProvider6 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(24, i, this));
        this.factoryProvider7 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(28, i, this));
        this.fixedTimingTransitionProgressProvider = new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(13, i, this);
        this.factoryProvider8 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(32, i, this));
        this.provideApplicationContextProvider = new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(48, i, this);
        this.provideExecutionProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(56, i, this));
        this.provideMediaRouter2ManagerProvider = new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(89, i, this);
        int i2 = 1;
        this.provideCrossWindowBlurListenersProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(99, i2, this));
        this.provideJobSchedulerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(100, i2, this));
        this.provideNearbyManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(101, i2, this));
        this.provideINotificationManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(102, i2, this));
        this.provideShortcutManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(103, i2, this));
        this.provideSatelliteManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(104, i2, this));
        this.provideNetworkScoreManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(105, i2, this));
        this.provideSensorPrivacyManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(106, i2, this));
        this.providePermissionManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(107, i2, this));
        this.provideLocationManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(108, i2, this));
        this.provideSafetyCenterManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(109, i2, this));
        this.providePackageManagerWrapperProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(110, i2, this));
        this.provideUiBackgroundExecutorServiceProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(111, i2, this));
        this.opaEnabledSettingsProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(112, i2, this));
        this.provideIMediaProjectionManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(113, i2, this));
        this.provideIActivityTaskManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(114, i2, this));
        this.provideRoleManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(115, i2, this));
        this.provideInputManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(116, i2, this));
        this.provideBluetoothManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(118, i2, this));
        this.provideBluetoothAdapterProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(117, i2, this));
        this.provideUsageStatsManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(119, i2, this));
        this.provideIUriGrantsManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(120, i2, this));
        this.provideUiModeManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(121, i2, this));
        this.provideColorDisplayManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(122, i2, this));
        this.provideScopedColorDisplayManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(123, i2, this));
        this.provideDeviceIdleControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(124, i2, this));
        this.provideOverlayManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(125, i2, this));
        this.unfoldTransitionProgressForwarderProvider = new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(127, i2, this);
        this.provideProgressForwarderProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(126, i2, this));
        this.provideVirtualDeviceManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(128, i2, this));
        this.provideIAudioServiceProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(129, i2, this));
        this.pluginDependencyProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(130, i2, this));
        this.provideOptionalVibratorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(131, i2, this));
        this.provideIVrManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(132, i2, this));
        this.lowLightTransitionCoordinatorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(133, i2, this));
        this.provideStatsManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(134, i2, this));
        this.provideTextClassificationManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(135, i2, this));
        this.provideDisplaySwitchLatencyLoggerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(136, i2, this));
        this.provideStorageManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(137, i2, this));
        this.provideNotificationManagerCompatProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(138, i2, this));
        this.provideAmbientContextManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(139, i2, this));
        this.providesFoldStateLoggingProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(140, i2, this));
        this.providesFoldStateLoggerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(141, i2, this));
        this.deviceStateRepositoryImplProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(142, i2, this));
        this.unfoldBgDispatcherProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(143, i2, this));
        this.provideFoldLockSettingAvailabilityProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(144, i2, this));
        DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(145, i2, this));
        this.qSExpansionPathInterpolatorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(146, i2, this));
        this.provideIPackageManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(147, i2, this));
        this.provideAsyncLayoutInflaterProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(148, i2, this));
        this.provideOptionalTelecomManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(149, i2, this));
        this.provideUserScopedCaptioningManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider(150, i2, this));
    }
}

package com.google.android.systemui.dagger;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.AlarmManager;
import android.app.IActivityTaskManager;
import android.app.INotificationManager;
import android.app.IUriGrantsManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.StatsManager;
import android.app.StatusBarManager;
import android.app.UiModeManager;
import android.app.admin.DevicePolicyManager;
import android.app.ambientcontext.AmbientContextManager;
import android.app.job.JobScheduler;
import android.app.role.RoleManager;
import android.app.smartspace.SmartspaceManager;
import android.app.trust.TrustManager;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.companion.virtual.VirtualDeviceManager;
import android.content.Context;
import android.content.om.OverlayManager;
import android.content.pm.IPackageManager;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutManager;
import android.graphics.Path;
import android.hardware.SensorManager;
import android.hardware.SensorPrivacyManager;
import android.hardware.biometrics.BiometricManager;
import android.hardware.camera2.CameraManager;
import android.hardware.devicestate.DeviceStateManager;
import android.hardware.display.ColorDisplayManager;
import android.hardware.display.DisplayManager;
import android.hardware.face.FaceManager;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.input.InputManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.IAudioService;
import android.media.MediaRouter2Manager;
import android.media.projection.IMediaProjectionManager;
import android.nearby.NearbyManager;
import android.net.ConnectivityManager;
import android.net.NetworkScoreManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IDeviceIdleController;
import android.os.Looper;
import android.os.PowerExemptionManager;
import android.os.PowerManager;
import android.os.ServiceManager;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.permission.PermissionManager;
import android.safetycenter.SafetyCenterManager;
import android.service.dreams.IDreamManager;
import android.service.vr.IVrManager;
import android.telecom.TelecomManager;
import android.telephony.CarrierConfigManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.satellite.SatelliteManager;
import android.view.Choreographer;
import android.view.CrossWindowBlurListeners;
import android.view.LayoutInflater;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textclassifier.TextClassificationManager;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.core.app.NotificationManagerCompat;
import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.app.viewcapture.ViewCaptureFactory;
import com.android.app.viewcapture.data.ViewNode;
import com.android.dream.lowlight.LowLightTransitionCoordinator;
import com.android.internal.app.IBatteryStats;
import com.android.internal.foldables.FoldLockSettingAvailabilityProvider;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLoggerImpl;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.CarrierText;
import com.android.systemui.battery.BatteryMeterView;
import com.android.systemui.dagger.AndroidInternalsModule;
import com.android.systemui.dagger.FrameworkServicesModule;
import com.android.systemui.dagger.GlobalModule;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.SystemPropertiesHelper;
import com.android.systemui.keyguard.LifecycleScreenStatusProvider;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.plugins.PluginDependencyProvider;
import com.android.systemui.plugins.PluginEnablerImpl;
import com.android.systemui.plugins.PluginsModule_ProvidePluginInstanceManagerFactoryFactory;
import com.android.systemui.plugins.PluginsModule_ProvidesPluginDebugFactory;
import com.android.systemui.plugins.PluginsModule_ProvidesPluginExecutorFactory;
import com.android.systemui.plugins.PluginsModule_ProvidesPluginInstanceFactoryFactory;
import com.android.systemui.plugins.PluginsModule_ProvidesPluginManagerFactory;
import com.android.systemui.plugins.PluginsModule_ProvidesPluginPrefsFactory;
import com.android.systemui.plugins.PluginsModule_ProvidesPrivilegedPluginsFactory;
import com.android.systemui.qs.PathInterpolatorBuilder;
import com.android.systemui.qs.QSExpansionPathInterpolator;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.shared.plugins.PluginActionManager;
import com.android.systemui.shared.plugins.PluginEnabler;
import com.android.systemui.shared.plugins.PluginInstance;
import com.android.systemui.shared.system.PackageManagerWrapper;
import com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager;
import com.android.systemui.statusbar.phone.KeyguardStatusBarView;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.unfold.DisplaySwitchLatencyLogger;
import com.android.systemui.unfold.FoldStateLoggingProviderImpl;
import com.android.systemui.unfold.FoldStateProviderModule;
import com.android.systemui.unfold.UnfoldProgressProvider;
import com.android.systemui.unfold.UnfoldRotationProviderInternalModule;
import com.android.systemui.unfold.UnfoldSharedInternalModule;
import com.android.systemui.unfold.UnfoldSharedModule;
import com.android.systemui.unfold.UnfoldTransitionModule;
import com.android.systemui.unfold.UnfoldTransitionModule$providesFoldStateLogger$1;
import com.android.systemui.unfold.UnfoldTransitionModuleKt;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig;
import com.android.systemui.unfold.progress.FixedTimingTransitionProgressProvider;
import com.android.systemui.unfold.progress.MainThreadUnfoldTransitionProgressProvider;
import com.android.systemui.unfold.progress.UnfoldTransitionProgressForwarder;
import com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider;
import com.android.systemui.unfold.system.DeviceStateManagerFoldProvider;
import com.android.systemui.unfold.system.DeviceStateRepositoryImpl;
import com.android.systemui.unfold.updates.DeviceFoldStateProvider;
import com.android.systemui.unfold.updates.FoldProvider;
import com.android.systemui.unfold.updates.RotationChangeProvider;
import com.android.systemui.unfold.updates.hinge.EmptyHingeAngleProvider;
import com.android.systemui.unfold.updates.hinge.HingeAngleProvider;
import com.android.systemui.unfold.updates.hinge.HingeSensorAngleProvider;
import com.android.systemui.unfold.util.NaturalRotationUnfoldProgressProvider;
import com.android.systemui.unfold.util.UnfoldKeyguardVisibilityManagerImpl;
import com.android.systemui.unfold.util.UnfoldOnlyProgressProvider;
import com.android.systemui.user.utils.UserScopedServiceImpl;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.systemui.util.concurrency.ExecutionImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.GlobalConcurrencyModule_ProvideMainLooperFactory;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import com.android.systemui.util.kotlin.GlobalCoroutinesModule;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import com.android.wm.shell.unfold.ShellUnfoldProgressProvider;
import com.google.android.systemui.assist.OpaEnabledSettings;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.android.HandlerDispatcherKt;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl {
    public final Provider getBatteryMeterViewProvider;
    public final Provider getCarrierTextProvider;
    public final Provider getStatusBarLocationProvider;
    public final NotificationPanelViewController.AnonymousClass10 shadeViewStateProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;
    public final KeyguardStatusBarView view;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SwitchingProvider implements Provider {
        public final /* synthetic */ int $r8$classId;
        public final int id;
        public final Object keyguardStatusBarViewComponentImpl;

        public /* synthetic */ SwitchingProvider(int i, int i2, Object obj) {
            this.$r8$classId = i2;
            this.keyguardStatusBarViewComponentImpl = obj;
            this.id = i;
        }

        @Override // javax.inject.Provider
        public final Object get() {
            CrossWindowBlurListeners systemPropertiesHelper;
            Optional optional;
            UnfoldTransitionProgressProvider unfoldTransitionProgressProvider;
            Object rotationChangeProvider;
            Object obj = this.keyguardStatusBarViewComponentImpl;
            final int i = 0;
            int i2 = this.id;
            final int i3 = 1;
            switch (this.$r8$classId) {
                case 0:
                    DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl daggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl) obj;
                    if (i2 == 0) {
                        CarrierText carrierText = (CarrierText) daggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.view.findViewById(R.id.keyguard_carrier_text);
                        Preconditions.checkNotNullFromProvides(carrierText);
                        return carrierText;
                    }
                    if (i2 != 1) {
                        if (i2 == 2) {
                            return StatusBarLocation.KEYGUARD;
                        }
                        throw new AssertionError(i2);
                    }
                    BatteryMeterView batteryMeterView = (BatteryMeterView) daggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.view.findViewById(R.id.battery);
                    Preconditions.checkNotNullFromProvides(batteryMeterView);
                    return batteryMeterView;
                default:
                    int i4 = i2 / 100;
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) obj;
                    if (i4 == 0) {
                        HingeAngleProvider hingeAngleProvider = EmptyHingeAngleProvider.INSTANCE;
                        FaceManager faceManager = null;
                        switch (i2) {
                            case 0:
                                systemPropertiesHelper = new SystemPropertiesHelper();
                                break;
                            case 1:
                                systemPropertiesHelper = WindowManagerGlobal.getWindowManagerService();
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 2:
                                systemPropertiesHelper = (AccessibilityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(AccessibilityManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 3:
                                UnfoldTransitionModule unfoldTransitionModule = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionModule;
                                ResourceUnfoldTransitionConfig resourceUnfoldTransitionConfig = (ResourceUnfoldTransitionConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.resourceUnfoldTransitionConfigProvider.get();
                                FoldProvider foldProvider = (FoldProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.deviceStateManagerFoldProvider.get();
                                Provider provider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionProgressProvider;
                                Provider provider2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUnfoldOnlyProvider;
                                if (!((Boolean) resourceUnfoldTransitionConfig.isEnabled$delegate.getValue()).booleanValue()) {
                                    provider = null;
                                } else if (!UnfoldTransitionModuleKt.ENABLE_FOLD_TASK_ANIMATIONS) {
                                    provider = provider2;
                                }
                                return (provider == null || (optional = (Optional) provider.get()) == null || (unfoldTransitionProgressProvider = (UnfoldTransitionProgressProvider) optional.orElse(null)) == null) ? ShellUnfoldProgressProvider.NO_PROVIDER : new UnfoldProgressProvider(unfoldTransitionProgressProvider, foldProvider);
                            case 4:
                                systemPropertiesHelper = new ResourceUnfoldTransitionConfig();
                                break;
                            case 5:
                                systemPropertiesHelper = new DeviceStateManagerFoldProvider((DeviceStateManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDeviceStateManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                                break;
                            case 6:
                                systemPropertiesHelper = (DeviceStateManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(DeviceStateManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 7:
                                UnfoldSharedInternalModule unfoldSharedInternalModule = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldSharedInternalModule;
                                ResourceUnfoldTransitionConfig resourceUnfoldTransitionConfig2 = (ResourceUnfoldTransitionConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.resourceUnfoldTransitionConfigProvider.get();
                                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider.get();
                                final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider2.get();
                                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider4.get();
                                SwitchingProvider switchingProvider = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.fixedTimingTransitionProgressProvider;
                                DeviceFoldStateProvider deviceFoldStateProvider = (DeviceFoldStateProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideFoldStateProvider.get();
                                Handler handler = (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get();
                                final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider8.get();
                                Provider provider3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldBgTransitionProgressProvider;
                                Optional of = Optional.of((Boolean) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldBgProgressFlagProvider.get());
                                Object obj2 = Boolean.FALSE;
                                if (of.isPresent()) {
                                    obj2 = of.get();
                                }
                                if (!((Boolean) obj2).booleanValue()) {
                                    return UnfoldSharedInternalModule.createOptionalUnfoldTransitionProgressProvider(resourceUnfoldTransitionConfig2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2.create("MainThread"), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3, switchingProvider, deviceFoldStateProvider, handler);
                                }
                                Optional map = ((Optional) provider3.get()).map(new Function() { // from class: com.android.systemui.unfold.UnfoldSharedInternalModule$unfoldTransitionProgressProvider$mainThreadProvider$1
                                    @Override // java.util.function.Function
                                    public final Object apply(Object obj3) {
                                        UnfoldTransitionProgressProvider unfoldTransitionProgressProvider2 = (UnfoldTransitionProgressProvider) obj3;
                                        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$82 = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8.this;
                                        Intrinsics.checkNotNull(unfoldTransitionProgressProvider2);
                                        return new MainThreadUnfoldTransitionProgressProvider((Handler) ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$82.this$0.keyguardStatusBarViewComponentImpl).provideMainHandlerProvider.get(), unfoldTransitionProgressProvider2);
                                    }
                                });
                                map.ifPresent(new Consumer() { // from class: com.android.systemui.unfold.UnfoldSharedInternalModule$unfoldTransitionProgressProvider$1
                                    @Override // java.util.function.Consumer
                                    public final void accept(Object obj3) {
                                        ((UnfoldTransitionProgressProvider) obj3).addCallback(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2.this.create("MainThreadFromBgProgress"));
                                    }
                                });
                                return map;
                            case 8:
                                return new DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1(this);
                            case 9:
                                systemPropertiesHelper = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getContentResolver();
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 10:
                                return new DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2(this);
                            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                                return new DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3(this);
                            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                                systemPropertiesHelper = new DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4();
                                break;
                            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                                systemPropertiesHelper = new FixedTimingTransitionProgressProvider((DeviceFoldStateProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideFoldStateProvider.get());
                                break;
                            case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                                FoldStateProviderModule foldStateProviderModule = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.foldStateProviderModule;
                                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider5.get();
                                ResourceUnfoldTransitionConfig resourceUnfoldTransitionConfig3 = (ResourceUnfoldTransitionConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.resourceUnfoldTransitionConfigProvider.get();
                                Handler handler2 = (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get();
                                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider6.get();
                                if (((Boolean) resourceUnfoldTransitionConfig3.isHingeAngleEnabled$delegate.getValue()).booleanValue()) {
                                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6.this$0.keyguardStatusBarViewComponentImpl;
                                    hingeAngleProvider = new HingeSensorAngleProvider((SensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.providesSensorManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideUiBackgroundExecutorProvider.get(), handler2);
                                }
                                systemPropertiesHelper = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5.create(hingeAngleProvider, (RotationChangeProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideRotationChangeProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get());
                                break;
                            case 15:
                                return new Object() { // from class: com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5
                                    public final DeviceFoldStateProvider create(HingeAngleProvider hingeAngleProvider2, RotationChangeProvider rotationChangeProvider2, Handler handler3) {
                                        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl.SwitchingProvider.this.keyguardStatusBarViewComponentImpl;
                                        return new DeviceFoldStateProvider((ResourceUnfoldTransitionConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.resourceUnfoldTransitionConfigProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.context, (LifecycleScreenStatusProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.lifecycleScreenStatusProvider.get(), (ActivityManagerActivityTypeProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.activityManagerActivityTypeProvider.get(), (UnfoldKeyguardVisibilityManagerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.unfoldKeyguardVisibilityProvider.get(), (FoldProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.deviceStateManagerFoldProvider.get(), hingeAngleProvider2, rotationChangeProvider2, handler3);
                                    }
                                };
                            case 16:
                                systemPropertiesHelper = new LifecycleScreenStatusProvider((ScreenLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.screenLifecycleProvider.get());
                                break;
                            case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                                DumpManager dumpManager = (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get();
                                systemPropertiesHelper = new ScreenLifecycle();
                                systemPropertiesHelper.mScreenState = 0;
                                dumpManager.getClass();
                                DumpManager.registerDumpable$default(dumpManager, "ScreenLifecycle", systemPropertiesHelper);
                                break;
                            case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                                systemPropertiesHelper = new DumpManager();
                                break;
                            case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                                systemPropertiesHelper = new ActivityManagerActivityTypeProvider((ActivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideActivityManagerProvider.get());
                                break;
                            case 20:
                                systemPropertiesHelper = (ActivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(ActivityManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 21:
                                UnfoldSharedModule unfoldSharedModule = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldSharedModule;
                                systemPropertiesHelper = (UnfoldKeyguardVisibilityManagerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldKeyguardVisibilityManagerImplProvider.get();
                                break;
                            case 22:
                                systemPropertiesHelper = new UnfoldKeyguardVisibilityManagerImpl();
                                break;
                            case 23:
                                return new Handler(GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper());
                            case 24:
                                return new DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6(this);
                            case 25:
                                systemPropertiesHelper = (SensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(SensorManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 26:
                                systemPropertiesHelper = Executors.newSingleThreadExecutor();
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 27:
                                UnfoldRotationProviderInternalModule unfoldRotationProviderInternalModule = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldRotationProviderInternalModule;
                                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider7.get();
                                Handler handler3 = (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get();
                                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7.this$0.keyguardStatusBarViewComponentImpl;
                                rotationChangeProvider = new RotationChangeProvider((DisplayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.provideDisplayManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.context, (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.unfoldBgProgressHandlerProvider.get(), handler3);
                                return rotationChangeProvider;
                            case 28:
                                return new DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7(this);
                            case 29:
                                systemPropertiesHelper = (DisplayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(DisplayManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 30:
                                return new Handler((Looper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideBgLooperProvider.get());
                            case 31:
                                HandlerThread handlerThread = new HandlerThread("UnfoldBg", -2);
                                handlerThread.start();
                                systemPropertiesHelper = handlerThread.getLooper();
                                break;
                            case 32:
                                return new DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$8(this);
                            case 33:
                                UnfoldSharedInternalModule unfoldSharedInternalModule2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldSharedInternalModule;
                                systemPropertiesHelper = UnfoldSharedInternalModule.createOptionalUnfoldTransitionProgressProvider((ResourceUnfoldTransitionConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.resourceUnfoldTransitionConfigProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$1) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider.get(), ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$2) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider2.get()).create("BgThread"), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$3) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider4.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.fixedTimingTransitionProgressProvider, (DeviceFoldStateProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideBgFoldStateProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldBgProgressHandlerProvider.get());
                                break;
                            case 34:
                                FoldStateProviderModule foldStateProviderModule2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.foldStateProviderModule;
                                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$52 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$5) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider5.get();
                                ResourceUnfoldTransitionConfig resourceUnfoldTransitionConfig4 = (ResourceUnfoldTransitionConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.resourceUnfoldTransitionConfigProvider.get();
                                Handler handler4 = (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldBgProgressHandlerProvider.get();
                                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$62 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$6) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider6.get();
                                if (((Boolean) resourceUnfoldTransitionConfig4.isHingeAngleEnabled$delegate.getValue()).booleanValue()) {
                                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$62.this$0.keyguardStatusBarViewComponentImpl;
                                    hingeAngleProvider = new HingeSensorAngleProvider((SensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4.providesSensorManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4.provideUiBackgroundExecutorProvider.get(), handler4);
                                }
                                systemPropertiesHelper = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$52.create(hingeAngleProvider, (RotationChangeProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideBgRotationChangeProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldBgProgressHandlerProvider.get());
                                break;
                            case 35:
                                UnfoldRotationProviderInternalModule unfoldRotationProviderInternalModule2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldRotationProviderInternalModule;
                                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$72 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$7) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.factoryProvider7.get();
                                Handler handler5 = (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldBgProgressHandlerProvider.get();
                                DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5 = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$72.this$0.keyguardStatusBarViewComponentImpl;
                                rotationChangeProvider = new RotationChangeProvider((DisplayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.provideDisplayManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.context, (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5.unfoldBgProgressHandlerProvider.get(), handler5);
                                return rotationChangeProvider;
                            case 36:
                                UnfoldTransitionModule unfoldTransitionModule2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionModule;
                                systemPropertiesHelper = Boolean.TRUE;
                                break;
                            case 37:
                                UnfoldTransitionModule unfoldTransitionModule3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionModule;
                                final FoldProvider foldProvider2 = (FoldProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.deviceStateManagerFoldProvider.get();
                                final Executor executor = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get();
                                systemPropertiesHelper = ((Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionProgressProvider.get()).map(new Function() { // from class: com.android.systemui.unfold.UnfoldTransitionModule$provideUnfoldOnlyProvider$1
                                    @Override // java.util.function.Function
                                    public final Object apply(Object obj3) {
                                        switch (i) {
                                            case 0:
                                                UnfoldTransitionProgressProvider unfoldTransitionProgressProvider2 = (UnfoldTransitionProgressProvider) obj3;
                                                FoldProvider foldProvider3 = (FoldProvider) foldProvider2;
                                                Executor executor2 = (Executor) executor;
                                                Intrinsics.checkNotNull(unfoldTransitionProgressProvider2);
                                                return new UnfoldOnlyProgressProvider(foldProvider3, executor2, unfoldTransitionProgressProvider2);
                                            default:
                                                UnfoldTransitionProgressProvider unfoldTransitionProgressProvider3 = (UnfoldTransitionProgressProvider) obj3;
                                                Context context = (Context) foldProvider2;
                                                RotationChangeProvider rotationChangeProvider2 = (RotationChangeProvider) executor;
                                                Intrinsics.checkNotNull(unfoldTransitionProgressProvider3);
                                                return new NaturalRotationUnfoldProgressProvider(context, rotationChangeProvider2, unfoldTransitionProgressProvider3);
                                        }
                                    }
                                });
                                break;
                            case 38:
                                systemPropertiesHelper = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getMainExecutor();
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 39:
                                systemPropertiesHelper = new UiEventLoggerImpl();
                                break;
                            case 40:
                                systemPropertiesHelper = ActivityTaskManager.getInstance();
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 41:
                                systemPropertiesHelper = (KeyguardManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(KeyguardManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 42:
                                systemPropertiesHelper = InteractionJankMonitor.getInstance();
                                systemPropertiesHelper.configDebugOverlay(-256, 0.75d);
                                break;
                            case 43:
                                systemPropertiesHelper = (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(WindowManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 44:
                                systemPropertiesHelper = IStatusBarService.Stub.asInterface(ServiceManager.getService("statusbar"));
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 45:
                                systemPropertiesHelper = (UserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(UserManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 46:
                                systemPropertiesHelper = (LauncherApps) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(LauncherApps.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 47:
                                systemPropertiesHelper = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getPackageManager();
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 48:
                                GlobalModule globalModule = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.globalModule;
                                systemPropertiesHelper = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getApplicationContext();
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 49:
                                systemPropertiesHelper = Boolean.valueOf(ActivityManager.isRunningInUserTestHarness());
                                break;
                            case 50:
                                systemPropertiesHelper = (PowerManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(PowerManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 51:
                                GlobalCoroutinesModule globalCoroutinesModule = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.globalCoroutinesModule;
                                CoroutineContext coroutineContext = (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.mainCoroutineContextProvider.get();
                                TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
                                systemPropertiesHelper = CoroutineScopeKt.CoroutineScope(coroutineContext.plus(EmptyCoroutineContext.INSTANCE));
                                break;
                            case 52:
                                GlobalCoroutinesModule globalCoroutinesModule2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.globalCoroutinesModule;
                                DefaultScheduler defaultScheduler = Dispatchers.Default;
                                systemPropertiesHelper = MainDispatcherLoader.dispatcher.immediate;
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 53:
                                systemPropertiesHelper = (AlarmManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(AlarmManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 54:
                                UnfoldTransitionModule unfoldTransitionModule4 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionModule;
                                DeviceStateManager deviceStateManager = (DeviceStateManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDeviceStateManagerProvider.get();
                                Context context = (Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get();
                                Executor executor2 = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get();
                                DeviceStateManager.FoldStateListener foldStateListener = new DeviceStateManager.FoldStateListener(context);
                                deviceStateManager.registerCallback(executor2, foldStateListener);
                                return foldStateListener;
                            case 55:
                                AndroidInternalsModule androidInternalsModule = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.androidInternalsModule;
                                return new LockPatternUtils(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                            case 56:
                                systemPropertiesHelper = new ExecutionImpl();
                                break;
                            case 57:
                                Context context2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
                                if (context2.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
                                    faceManager = (FingerprintManager) context2.getSystemService(FingerprintManager.class);
                                }
                                return faceManager;
                            case 58:
                                Context context3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
                                if (context3.getPackageManager().hasSystemFeature("android.hardware.biometrics.face")) {
                                    faceManager = (FaceManager) context3.getSystemService(FaceManager.class);
                                }
                                return faceManager;
                            case 59:
                                FrameworkServicesModule frameworkServicesModule = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.frameworkServicesModule;
                                systemPropertiesHelper = LayoutInflater.from(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 60:
                                rotationChangeProvider = new ViewCaptureAwareWindowManager((WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), ConvenienceExtensionsKt.toKotlinLazy(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideViewCaptureProvider)));
                                return rotationChangeProvider;
                            case 61:
                                systemPropertiesHelper = ViewCaptureFactory.Companion.getInstance(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                                break;
                            case 62:
                                return new ExecutorImpl(GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper());
                            case 63:
                                systemPropertiesHelper = (TrustManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(TrustManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 64:
                                GlobalCoroutinesModule globalCoroutinesModule3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.globalCoroutinesModule;
                                DefaultScheduler defaultScheduler2 = Dispatchers.Default;
                                systemPropertiesHelper = MainDispatcherLoader.dispatcher.immediate;
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 65:
                                FrameworkServicesModule frameworkServicesModule2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.frameworkServicesModule;
                                systemPropertiesHelper = Choreographer.getInstance();
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 66:
                                systemPropertiesHelper = ActivityManager.getService();
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 67:
                                systemPropertiesHelper = (DevicePolicyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(DevicePolicyManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 68:
                                Context context4 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
                                FaceManager faceManager2 = (FaceManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideFaceManagerProvider.get();
                                FingerprintManager fingerprintManager = (FingerprintManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providesFingerprintManagerProvider.get();
                                if (faceManager2 != null || fingerprintManager != null) {
                                    faceManager = (BiometricManager) context4.getSystemService(BiometricManager.class);
                                }
                                return faceManager;
                            case 69:
                                systemPropertiesHelper = (CameraManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(CameraManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 70:
                                systemPropertiesHelper = (ConnectivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(ConnectivityManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 71:
                                systemPropertiesHelper = ((SubscriptionManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(SubscriptionManager.class)).createForAllUserProfiles();
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 72:
                                systemPropertiesHelper = (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(TelephonyManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 73:
                                systemPropertiesHelper = (WifiManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(WifiManager.class);
                                break;
                            case 74:
                                systemPropertiesHelper = (CarrierConfigManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(CarrierConfigManager.class);
                                break;
                            case 75:
                                systemPropertiesHelper = PluginsModule_ProvidesPluginManagerFactory.providesPluginManager(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (PluginActionManager.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePluginInstanceManagerFactoryProvider.get(), PluginsModule_ProvidesPluginDebugFactory.providesPluginDebug(), (UncaughtExceptionPreHandlerManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.uncaughtExceptionPreHandlerManagerProvider.get(), (PluginEnabler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.pluginEnablerImplProvider.get(), PluginsModule_ProvidesPluginPrefsFactory.providesPluginPrefs(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context), PluginsModule_ProvidesPrivilegedPluginsFactory.providesPrivilegedPlugins(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context));
                                break;
                            case 76:
                                systemPropertiesHelper = PluginsModule_ProvidePluginInstanceManagerFactoryFactory.providePluginInstanceManagerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providesPluginExecutorProvider.get(), (NotificationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideNotificationManagerProvider.get(), (PluginEnabler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.pluginEnablerImplProvider.get(), PluginsModule_ProvidesPrivilegedPluginsFactory.providesPrivilegedPlugins(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context), (PluginInstance.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providesPluginInstanceFactoryProvider.get());
                                break;
                            case 77:
                                systemPropertiesHelper = PluginsModule_ProvidesPluginExecutorFactory.providesPluginExecutor(new ThreadFactoryImpl());
                                break;
                            case 78:
                                systemPropertiesHelper = (NotificationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(NotificationManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 79:
                                systemPropertiesHelper = new PluginEnablerImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get());
                                break;
                            case 80:
                                systemPropertiesHelper = PluginsModule_ProvidesPluginInstanceFactoryFactory.providesPluginInstanceFactory(PluginsModule_ProvidesPrivilegedPluginsFactory.providesPrivilegedPlugins(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context), PluginsModule_ProvidesPluginDebugFactory.providesPluginDebug());
                                break;
                            case 81:
                                systemPropertiesHelper = new UncaughtExceptionPreHandlerManager();
                                break;
                            case 82:
                                AndroidInternalsModule androidInternalsModule2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.androidInternalsModule;
                                systemPropertiesHelper = new MetricsLogger();
                                break;
                            case 83:
                                systemPropertiesHelper = ViewConfiguration.get(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 84:
                                systemPropertiesHelper = (TelecomManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(TelecomManager.class);
                                break;
                            case 85:
                                systemPropertiesHelper = (InputMethodManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(InputMethodManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 86:
                                systemPropertiesHelper = IBatteryStats.Stub.asInterface(ServiceManager.getService("batterystats"));
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 87:
                                systemPropertiesHelper = Optional.ofNullable(AppWidgetManager.getInstance((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get()));
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 88:
                                systemPropertiesHelper = ((PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get()).getPackageInstaller();
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 89:
                                systemPropertiesHelper = MediaRouter2Manager.getInstance(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 90:
                                systemPropertiesHelper = (SmartspaceManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(SmartspaceManager.class);
                                break;
                            case 91:
                                systemPropertiesHelper = (StatusBarManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(StatusBarManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 92:
                                systemPropertiesHelper = IDreamManager.Stub.asInterface(ServiceManager.getService("dreams"));
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 93:
                                systemPropertiesHelper = (AudioManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(AudioManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 94:
                                systemPropertiesHelper = (PowerExemptionManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(PowerExemptionManager.class);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 95:
                                systemPropertiesHelper = (Vibrator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(Vibrator.class);
                                break;
                            case 96:
                                systemPropertiesHelper = LatencyTracker.getInstance(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            case 97:
                                UnfoldTransitionModule unfoldTransitionModule5 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionModule;
                                final Context context5 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
                                final RotationChangeProvider rotationChangeProvider2 = (RotationChangeProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideRotationChangeProvider.get();
                                systemPropertiesHelper = ((Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionProgressProvider.get()).map(new Function() { // from class: com.android.systemui.unfold.UnfoldTransitionModule$provideUnfoldOnlyProvider$1
                                    @Override // java.util.function.Function
                                    public final Object apply(Object obj3) {
                                        switch (i3) {
                                            case 0:
                                                UnfoldTransitionProgressProvider unfoldTransitionProgressProvider2 = (UnfoldTransitionProgressProvider) obj3;
                                                FoldProvider foldProvider3 = (FoldProvider) context5;
                                                Executor executor22 = (Executor) rotationChangeProvider2;
                                                Intrinsics.checkNotNull(unfoldTransitionProgressProvider2);
                                                return new UnfoldOnlyProgressProvider(foldProvider3, executor22, unfoldTransitionProgressProvider2);
                                            default:
                                                UnfoldTransitionProgressProvider unfoldTransitionProgressProvider3 = (UnfoldTransitionProgressProvider) obj3;
                                                Context context6 = (Context) context5;
                                                RotationChangeProvider rotationChangeProvider22 = (RotationChangeProvider) rotationChangeProvider2;
                                                Intrinsics.checkNotNull(unfoldTransitionProgressProvider3);
                                                return new NaturalRotationUnfoldProgressProvider(context6, rotationChangeProvider22, unfoldTransitionProgressProvider3);
                                        }
                                    }
                                });
                                break;
                            case 98:
                                UnfoldTransitionModule unfoldTransitionModule6 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionModule;
                                systemPropertiesHelper = ((Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideNaturalRotationProgressProvider.get()).map(UnfoldTransitionModule$providesFoldStateLogger$1.INSTANCE$1);
                                break;
                            case 99:
                                systemPropertiesHelper = CrossWindowBlurListeners.getInstance();
                                Preconditions.checkNotNullFromProvides(systemPropertiesHelper);
                                break;
                            default:
                                throw new AssertionError(i2);
                        }
                    } else {
                        if (i4 != 1) {
                            throw new AssertionError(i2);
                        }
                        switch (i2) {
                            case 100:
                                JobScheduler jobScheduler = (JobScheduler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(JobScheduler.class);
                                Preconditions.checkNotNullFromProvides(jobScheduler);
                                return jobScheduler;
                            case 101:
                                NearbyManager nearbyManager = (NearbyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(NearbyManager.class);
                                Preconditions.checkNotNullFromProvides(nearbyManager);
                                return nearbyManager;
                            case 102:
                                FrameworkServicesModule frameworkServicesModule3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.frameworkServicesModule;
                                INotificationManager asInterface = INotificationManager.Stub.asInterface(ServiceManager.getService("notification"));
                                Preconditions.checkNotNullFromProvides(asInterface);
                                return asInterface;
                            case 103:
                                ShortcutManager shortcutManager = (ShortcutManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(ShortcutManager.class);
                                Preconditions.checkNotNullFromProvides(shortcutManager);
                                return shortcutManager;
                            case 104:
                                Optional ofNullable = Optional.ofNullable((SatelliteManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(SatelliteManager.class));
                                Preconditions.checkNotNullFromProvides(ofNullable);
                                return ofNullable;
                            case 105:
                                NetworkScoreManager networkScoreManager = (NetworkScoreManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(NetworkScoreManager.class);
                                Preconditions.checkNotNullFromProvides(networkScoreManager);
                                return networkScoreManager;
                            case 106:
                                SensorPrivacyManager sensorPrivacyManager = (SensorPrivacyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(SensorPrivacyManager.class);
                                Preconditions.checkNotNullFromProvides(sensorPrivacyManager);
                                return sensorPrivacyManager;
                            case 107:
                                PermissionManager permissionManager = (PermissionManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(PermissionManager.class);
                                if (permissionManager != null) {
                                    permissionManager.initializeUsageHelper();
                                }
                                Preconditions.checkNotNullFromProvides(permissionManager);
                                return permissionManager;
                            case 108:
                                LocationManager locationManager = (LocationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(LocationManager.class);
                                Preconditions.checkNotNullFromProvides(locationManager);
                                return locationManager;
                            case 109:
                                SafetyCenterManager safetyCenterManager = (SafetyCenterManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(SafetyCenterManager.class);
                                Preconditions.checkNotNullFromProvides(safetyCenterManager);
                                return safetyCenterManager;
                            case 110:
                                return PackageManagerWrapper.sInstance;
                            case 111:
                                ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
                                Preconditions.checkNotNullFromProvides(newSingleThreadExecutor);
                                return newSingleThreadExecutor;
                            case 112:
                                systemPropertiesHelper = new OpaEnabledSettings(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                                break;
                            case 113:
                                IMediaProjectionManager asInterface2 = IMediaProjectionManager.Stub.asInterface(ServiceManager.getService("media_projection"));
                                Preconditions.checkNotNullFromProvides(asInterface2);
                                return asInterface2;
                            case 114:
                                IActivityTaskManager service = ActivityTaskManager.getService();
                                Preconditions.checkNotNullFromProvides(service);
                                return service;
                            case 115:
                                RoleManager roleManager = (RoleManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(RoleManager.class);
                                Preconditions.checkNotNullFromProvides(roleManager);
                                return roleManager;
                            case 116:
                                InputManager inputManager = (InputManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(InputManager.class);
                                Preconditions.checkNotNullFromProvides(inputManager);
                                return inputManager;
                            case 117:
                                return ((BluetoothManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideBluetoothManagerProvider.get()).getAdapter();
                            case 118:
                                BluetoothManager bluetoothManager = (BluetoothManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(BluetoothManager.class);
                                Preconditions.checkNotNullFromProvides(bluetoothManager);
                                return bluetoothManager;
                            case 119:
                                UsageStatsManager usageStatsManager = (UsageStatsManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(UsageStatsManager.class);
                                Preconditions.checkNotNullFromProvides(usageStatsManager);
                                return usageStatsManager;
                            case 120:
                                IUriGrantsManager asInterface3 = IUriGrantsManager.Stub.asInterface(ServiceManager.getService("uri_grants"));
                                Preconditions.checkNotNullFromProvides(asInterface3);
                                return asInterface3;
                            case 121:
                                UiModeManager uiModeManager = (UiModeManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(UiModeManager.class);
                                Preconditions.checkNotNullFromProvides(uiModeManager);
                                return uiModeManager;
                            case 122:
                                ColorDisplayManager colorDisplayManager = (ColorDisplayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(ColorDisplayManager.class);
                                Preconditions.checkNotNullFromProvides(colorDisplayManager);
                                return colorDisplayManager;
                            case 123:
                                return new UserScopedServiceImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, ColorDisplayManager.class);
                            case 124:
                                IDeviceIdleController asInterface4 = IDeviceIdleController.Stub.asInterface(ServiceManager.getService("deviceidle"));
                                Preconditions.checkNotNullFromProvides(asInterface4);
                                return asInterface4;
                            case 125:
                                OverlayManager overlayManager = (OverlayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(OverlayManager.class);
                                Preconditions.checkNotNullFromProvides(overlayManager);
                                return overlayManager;
                            case 126:
                                UnfoldSharedInternalModule unfoldSharedInternalModule3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldSharedInternalModule;
                                ResourceUnfoldTransitionConfig resourceUnfoldTransitionConfig5 = (ResourceUnfoldTransitionConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.resourceUnfoldTransitionConfigProvider.get();
                                SwitchingProvider switchingProvider2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionProgressForwarderProvider;
                                if (!((Boolean) resourceUnfoldTransitionConfig5.isEnabled$delegate.getValue()).booleanValue()) {
                                    systemPropertiesHelper = Optional.empty();
                                    break;
                                } else {
                                    systemPropertiesHelper = Optional.of(switchingProvider2.get());
                                    break;
                                }
                            case 127:
                                UnfoldTransitionProgressForwarder unfoldTransitionProgressForwarder = new UnfoldTransitionProgressForwarder();
                                unfoldTransitionProgressForwarder.attachInterface(unfoldTransitionProgressForwarder, "com.android.systemui.unfold.progress.IUnfoldAnimation");
                                return unfoldTransitionProgressForwarder;
                            case 128:
                                VirtualDeviceManager virtualDeviceManager = (VirtualDeviceManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(VirtualDeviceManager.class);
                                Preconditions.checkNotNullFromProvides(virtualDeviceManager);
                                return virtualDeviceManager;
                            case 129:
                                IAudioService asInterface5 = IAudioService.Stub.asInterface(ServiceManager.getService("audio"));
                                Preconditions.checkNotNullFromProvides(asInterface5);
                                return asInterface5;
                            case 130:
                                systemPropertiesHelper = new PluginDependencyProvider(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providesPluginManagerProvider));
                                break;
                            case 131:
                                Optional ofNullable2 = Optional.ofNullable((Vibrator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(Vibrator.class));
                                Preconditions.checkNotNullFromProvides(ofNullable2);
                                return ofNullable2;
                            case 132:
                                return IVrManager.Stub.asInterface(ServiceManager.getService("vrmanager"));
                            case 133:
                                return new LowLightTransitionCoordinator();
                            case 134:
                                StatsManager statsManager = (StatsManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(StatsManager.class);
                                Preconditions.checkNotNullFromProvides(statsManager);
                                return statsManager;
                            case 135:
                                TextClassificationManager textClassificationManager = (TextClassificationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(TextClassificationManager.class);
                                Preconditions.checkNotNullFromProvides(textClassificationManager);
                                return textClassificationManager;
                            case 136:
                                UnfoldTransitionModule unfoldTransitionModule7 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionModule;
                                return new DisplaySwitchLatencyLogger();
                            case 137:
                                StorageManager storageManager = (StorageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(StorageManager.class);
                                Preconditions.checkNotNullFromProvides(storageManager);
                                return storageManager;
                            case 138:
                                return new NotificationManagerCompat(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                            case 139:
                                return (AmbientContextManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(AmbientContextManager.class);
                            case 140:
                                UnfoldTransitionModule unfoldTransitionModule8 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionModule;
                                ResourceUnfoldTransitionConfig resourceUnfoldTransitionConfig6 = (ResourceUnfoldTransitionConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.resourceUnfoldTransitionConfigProvider.get();
                                Lazy lazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideFoldStateProvider);
                                if (!((Boolean) resourceUnfoldTransitionConfig6.isHingeAngleEnabled$delegate.getValue()).booleanValue()) {
                                    systemPropertiesHelper = Optional.empty();
                                    Intrinsics.checkNotNull(systemPropertiesHelper);
                                    break;
                                } else {
                                    systemPropertiesHelper = Optional.of(new FoldStateLoggingProviderImpl((DeviceFoldStateProvider) lazy.get(), new SystemClockImpl()));
                                    Intrinsics.checkNotNull(systemPropertiesHelper);
                                    break;
                                }
                            case 141:
                                UnfoldTransitionModule unfoldTransitionModule9 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionModule;
                                return ((Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providesFoldStateLoggingProvider.get()).map(UnfoldTransitionModule$providesFoldStateLogger$1.INSTANCE);
                            case 142:
                                systemPropertiesHelper = new DeviceStateRepositoryImpl((FoldProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.deviceStateManagerFoldProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get());
                                break;
                            case 143:
                                Handler handler6 = (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldBgProgressHandlerProvider.get();
                                int i5 = HandlerDispatcherKt.$r8$clinit;
                                return new HandlerContext(handler6, "@UnfoldBg Dispatcher", false);
                            case 144:
                                UnfoldTransitionModule unfoldTransitionModule10 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionModule;
                                return new FoldLockSettingAvailabilityProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources());
                            case 145:
                                UnfoldSharedModule unfoldSharedModule2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldSharedModule;
                                return (UnfoldKeyguardVisibilityManagerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldKeyguardVisibilityManagerImplProvider.get();
                            case 146:
                                QSExpansionPathInterpolator qSExpansionPathInterpolator = new QSExpansionPathInterpolator();
                                PathInterpolatorBuilder pathInterpolatorBuilder = new PathInterpolatorBuilder();
                                Path path = new Path();
                                float f = 0.0f;
                                path.moveTo(0.0f, 0.0f);
                                path.cubicTo(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                                float[] approximate = path.approximate(0.002f);
                                int length = approximate.length / 3;
                                if (approximate[1] != 0.0f || approximate[2] != 0.0f || approximate[approximate.length - 2] != 1.0f || approximate[approximate.length - 1] != 1.0f) {
                                    throw new IllegalArgumentException("The Path must start at (0,0) and end at (1,1)");
                                }
                                pathInterpolatorBuilder.mX = new float[length];
                                pathInterpolatorBuilder.mY = new float[length];
                                pathInterpolatorBuilder.mDist = new float[length];
                                int i6 = 0;
                                int i7 = 0;
                                float f2 = 0.0f;
                                while (i6 < length) {
                                    float f3 = approximate[i7];
                                    int i8 = i7 + 2;
                                    float f4 = approximate[i7 + 1];
                                    i7 += 3;
                                    float f5 = approximate[i8];
                                    if (f3 == f && f4 != f2) {
                                        throw new IllegalArgumentException("The Path cannot have discontinuity in the X axis.");
                                    }
                                    if (f4 < f2) {
                                        throw new IllegalArgumentException("The Path cannot loop back on itself.");
                                    }
                                    float[] fArr = pathInterpolatorBuilder.mX;
                                    fArr[i6] = f4;
                                    float[] fArr2 = pathInterpolatorBuilder.mY;
                                    fArr2[i6] = f5;
                                    if (i6 > 0) {
                                        int i9 = i6 - 1;
                                        float f6 = fArr[i6] - fArr[i9];
                                        float f7 = f5 - fArr2[i9];
                                        float sqrt = (float) Math.sqrt((f7 * f7) + (f6 * f6));
                                        float[] fArr3 = pathInterpolatorBuilder.mDist;
                                        fArr3[i6] = fArr3[i9] + sqrt;
                                    }
                                    i6++;
                                    f2 = f4;
                                    f = f3;
                                }
                                float[] fArr4 = pathInterpolatorBuilder.mDist;
                                float f8 = fArr4[fArr4.length - 1];
                                while (i < length) {
                                    float[] fArr5 = pathInterpolatorBuilder.mDist;
                                    fArr5[i] = fArr5[i] / f8;
                                    i++;
                                }
                                qSExpansionPathInterpolator.pathInterpolatorBuilder = pathInterpolatorBuilder;
                                return qSExpansionPathInterpolator;
                            case 147:
                                IPackageManager asInterface6 = IPackageManager.Stub.asInterface(ServiceManager.getService("package"));
                                Preconditions.checkNotNullFromProvides(asInterface6);
                                return asInterface6;
                            case 148:
                                FrameworkServicesModule frameworkServicesModule4 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.frameworkServicesModule;
                                return new AsyncLayoutInflater(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                            case 149:
                                Optional ofNullable3 = Optional.ofNullable((TelecomManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getSystemService(TelecomManager.class));
                                Preconditions.checkNotNullFromProvides(ofNullable3);
                                return ofNullable3;
                            case 150:
                                return new UserScopedServiceImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, CaptioningManager.class);
                            default:
                                throw new AssertionError(i2);
                        }
                    }
                    return systemPropertiesHelper;
            }
        }
    }

    public DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusBarViewComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, KeyguardStatusBarView keyguardStatusBarView, NotificationPanelViewController.AnonymousClass10 anonymousClass10) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.view = keyguardStatusBarView;
        this.shadeViewStateProvider = anonymousClass10;
        int i = 0;
        this.getCarrierTextProvider = DoubleCheck.provider(new SwitchingProvider(0, i, this));
        this.getBatteryMeterViewProvider = DoubleCheck.provider(new SwitchingProvider(1, i, this));
        this.getStatusBarLocationProvider = DoubleCheck.provider(new SwitchingProvider(2, i, this));
    }
}

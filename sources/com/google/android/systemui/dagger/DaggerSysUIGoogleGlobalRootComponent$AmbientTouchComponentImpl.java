package com.google.android.systemui.dagger;

import android.app.DreamManager;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.Display;
import android.view.IWindowManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleCoroutineScopeImpl;
import androidx.lifecycle.LifecycleKt;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.ClockEventController;
import com.android.keyguard.KeyguardClockSwitch;
import com.android.keyguard.KeyguardClockSwitchController;
import com.android.keyguard.KeyguardSliceViewController;
import com.android.keyguard.KeyguardStatusView;
import com.android.keyguard.KeyguardStatusViewController;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.ambient.touch.BouncerSwipeTouchHandler;
import com.android.systemui.ambient.touch.ShadeTouchHandler;
import com.android.systemui.ambient.touch.TouchMonitor;
import com.android.systemui.ambient.touch.dagger.BouncerSwipeModule$$ExternalSyntheticLambda0;
import com.android.systemui.ambient.touch.scrim.ScrimManager;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor;
import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import com.android.systemui.dreams.DreamOverlayLifecycleOwner;
import com.android.systemui.dreams.DreamOverlayService$$ExternalSyntheticLambda2;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.ui.view.InWindowLauncherUnlockAnimationManager;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.shared.clocks.ClockRegistry;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.AlwaysOnDisplayNotificationIconViewStore;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerAlwaysOnDisplayViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarIconViewBindingFailureTracker;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.touch.TouchInsetManager;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.display.DisplayHelper;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import com.android.wm.shell.animation.FlingAnimationUtils;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetBuilder;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl {
    public Object lifecycleOwner;
    public Object loggingName;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final Object sysUIGoogleSysUIComponentImpl;
    public Object touchHandlers;

    public DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
    }

    public KeyguardStatusViewController getKeyguardStatusViewController() {
        KeyguardSliceViewController keyguardSliceViewController = (KeyguardSliceViewController) ((Provider) this.loggingName).get();
        KeyguardClockSwitch keyguardClockSwitch = (KeyguardClockSwitch) ((KeyguardStatusView) this.lifecycleOwner).findViewById(R.id.keyguard_clock_container);
        Preconditions.checkNotNullFromProvides(keyguardClockSwitch);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) this.sysUIGoogleSysUIComponentImpl;
        StatusBarStateController statusBarStateController = (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get();
        ClockRegistry clockRegistry = (ClockRegistry) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClockRegistryProvider.get();
        KeyguardSliceViewController keyguardSliceViewController2 = (KeyguardSliceViewController) ((Provider) this.loggingName).get();
        LockscreenSmartspaceController lockscreenSmartspaceController = (LockscreenSmartspaceController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenSmartspaceControllerProvider.get();
        NotificationIconContainerAlwaysOnDisplayViewBinder notificationIconContainerAlwaysOnDisplayViewBinder = new NotificationIconContainerAlwaysOnDisplayViewBinder((NotificationIconContainerAlwaysOnDisplayViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconContainerAlwaysOnDisplayViewModelProvider.get(), (KeyguardRootViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRootViewModelProvider.get(), (ConfigurationState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationStateProvider.get(), (StatusBarIconViewBindingFailureTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarIconViewBindingFailureTrackerProvider.get(), (ScreenOffAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenOffAnimationControllerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemBarUtilsState(), new AlwaysOnDisplayNotificationIconViewStore((NotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifCollectionProvider.get()));
        KeyguardUnlockAnimationController keyguardUnlockAnimationController = (KeyguardUnlockAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUnlockAnimationControllerProvider.get();
        SecureSettings secureSettings = (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DelayableExecutor delayableExecutor = (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get();
        Executor executor = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get();
        DumpManager dumpManager = (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get();
        KeyguardInteractor keyguardInteractor = (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get();
        KeyguardTransitionInteractor keyguardTransitionInteractor = (KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get();
        BroadcastDispatcher broadcastDispatcher = (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get();
        BatteryController batteryController = (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get();
        KeyguardUpdateMonitor keyguardUpdateMonitor = (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get();
        ConfigurationController configurationController = (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get();
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        Display display = (Display) Optional.of((Display) this.touchHandlers).orElse(null);
        if (display != null && context.getDisplayId() != display.getDisplayId()) {
            context = context.createDisplayContext(display);
            Intrinsics.checkNotNull(context);
        }
        return new KeyguardStatusViewController((KeyguardStatusView) this.lifecycleOwner, keyguardSliceViewController, new KeyguardClockSwitchController(keyguardClockSwitch, statusBarStateController, clockRegistry, keyguardSliceViewController2, lockscreenSmartspaceController, notificationIconContainerAlwaysOnDisplayViewBinder, keyguardUnlockAnimationController, secureSettings, delayableExecutor, executor, dumpManager, new ClockEventController(keyguardInteractor, keyguardTransitionInteractor, broadcastDispatcher, batteryController, keyguardUpdateMonitor, configurationController, context.getResources(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.clockMessageBuffers(), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (ZenModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.zenModeControllerImplProvider.get()), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardClockLogProvider.get(), (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get(), (KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockInteractorProvider.get(), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (InWindowLauncherUnlockAnimationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.inWindowLauncherUnlockAnimationManagerProvider.get()), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get(), (ScreenOffAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenOffAnimationControllerProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1581$$Nest$mkeyguardLogger(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (InteractionJankMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInteractionJankMonitorProvider.get(), (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get(), (PowerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerInteractorProvider.get());
    }

    public TouchMonitor getTouchMonitor() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        Executor executor = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) this.sysUIGoogleSysUIComponentImpl;
        Executor executor2 = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get();
        LifecycleOwner lifecycleOwner = (LifecycleOwner) this.lifecycleOwner;
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        Preconditions.checkNotNullFromProvides(lifecycle);
        DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory = new DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl);
        DisplayHelper displayHelper = new DisplayHelper();
        ConfigurationInteractor configurationInteractor = (ConfigurationInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationInteractorProvider.get();
        SetBuilder setBuilder = new SetBuilder(3);
        setBuilder.addAll((Set) this.touchHandlers);
        Lifecycle lifecycle2 = lifecycleOwner.getLifecycle();
        Preconditions.checkNotNullFromProvides(lifecycle2);
        LifecycleCoroutineScopeImpl coroutineScope = LifecycleKt.getCoroutineScope(lifecycle2);
        ScrimManager m1660$$Nest$mscrimManager = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1660$$Nest$mscrimManager(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl);
        Optional optional = (Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.optionalOfCentralSurfacesProvider.get();
        NotificationShadeWindowController notificationShadeWindowController = (NotificationShadeWindowController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationShadeWindowControllerImplProvider.get();
        BouncerSwipeModule$$ExternalSyntheticLambda0 bouncerSwipeModule$$ExternalSyntheticLambda0 = new BouncerSwipeModule$$ExternalSyntheticLambda0();
        BouncerSwipeModule$$ExternalSyntheticLambda0 bouncerSwipeModule$$ExternalSyntheticLambda02 = new BouncerSwipeModule$$ExternalSyntheticLambda0();
        CommunalViewModel communalViewModel = (CommunalViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalViewModelProvider.get();
        FlingAnimationUtils.Builder builder = (FlingAnimationUtils.Builder) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.builderProvider.get();
        builder.reset();
        builder.mMaxLengthSeconds = 0.6f;
        builder.mSpeedUpFactor = 0.6f;
        FlingAnimationUtils build = builder.build();
        FlingAnimationUtils.Builder builder2 = (FlingAnimationUtils.Builder) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.builderProvider.get();
        builder2.reset();
        builder2.mMaxLengthSeconds = 0.6f;
        builder2.mSpeedUpFactor = 0.6f;
        FlingAnimationUtils build2 = builder2.build();
        Resources resources = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        TypedValue typedValue = new TypedValue();
        resources.getValue(R.dimen.dream_overlay_bouncer_start_region_screen_percentage, typedValue, true);
        float f = typedValue.getFloat();
        Resources resources2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources();
        Preconditions.checkNotNullFromProvides(resources2);
        TypedValue typedValue2 = new TypedValue();
        resources2.getValue(R.dimen.dream_overlay_bouncer_min_region_screen_percentage, typedValue2, true);
        setBuilder.add(new BouncerSwipeTouchHandler(coroutineScope, m1660$$Nest$mscrimManager, optional, notificationShadeWindowController, bouncerSwipeModule$$ExternalSyntheticLambda0, bouncerSwipeModule$$ExternalSyntheticLambda02, communalViewModel, build, build2, f, typedValue2.getFloat(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get()));
        Lifecycle lifecycle3 = lifecycleOwner.getLifecycle();
        Preconditions.checkNotNullFromProvides(lifecycle3);
        LifecycleCoroutineScopeImpl coroutineScope2 = LifecycleKt.getCoroutineScope(lifecycle3);
        Optional optional2 = (Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.optionalOfCentralSurfacesProvider.get();
        ShadeViewController shadeViewController = (ShadeViewController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeSurfaceProvider.get();
        DreamManager dreamManager = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamManager();
        CommunalViewModel communalViewModel2 = (CommunalViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalViewModelProvider.get();
        CommunalSettingsInteractor communalSettingsInteractor = (CommunalSettingsInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalSettingsInteractorProvider.get();
        Resources resources3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources();
        Preconditions.checkNotNullFromProvides(resources3);
        setBuilder.add(new ShadeTouchHandler(coroutineScope2, optional2, shadeViewController, dreamManager, communalViewModel2, communalSettingsInteractor, resources3.getDimensionPixelSize(R.dimen.dream_overlay_status_bar_height)));
        return new TouchMonitor(executor, executor2, lifecycle, daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, displayHelper, configurationInteractor, setBuilder.build(), (IWindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIWindowManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getDisplayId(), (String) this.loggingName, (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommunalTouchLogBufferProvider.get());
    }

    public DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, KeyguardStatusView keyguardStatusView, Display display) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.lifecycleOwner = keyguardStatusView;
        this.touchHandlers = display;
        this.loggingName = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$KeyguardStatusViewComponentImpl$SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 0));
    }

    public DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, LifecycleOwner lifecycleOwner, Set set, String str) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.lifecycleOwner = lifecycleOwner;
        this.touchHandlers = set;
        this.loggingName = str;
    }

    public DaggerSysUIGoogleGlobalRootComponent$AmbientTouchComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, DreamOverlayLifecycleOwner dreamOverlayLifecycleOwner, DreamOverlayService$$ExternalSyntheticLambda2 dreamOverlayService$$ExternalSyntheticLambda2, ViewModelStore viewModelStore, TouchInsetManager touchInsetManager) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.lifecycleOwner = touchInsetManager;
        this.touchHandlers = dreamOverlayLifecycleOwner;
        this.loggingName = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 0, 2));
        this.sysUIGoogleSysUIComponentImpl = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 1, 2));
    }
}

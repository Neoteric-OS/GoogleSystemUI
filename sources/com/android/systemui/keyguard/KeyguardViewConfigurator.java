package com.android.systemui.keyguard;

import android.content.Context;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.keyguard.KeyguardStatusViewController;
import com.android.systemui.CoreStartable;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryHapticsInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.ui.binder.KeyguardBlueprintViewBinder;
import com.android.systemui.keyguard.ui.binder.KeyguardRootViewBinder;
import com.android.systemui.keyguard.ui.view.KeyguardIndicationArea;
import com.android.systemui.keyguard.ui.view.KeyguardRootView;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBlueprintViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardIndicationAreaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardSmartspaceViewModel;
import com.android.systemui.keyguard.ui.viewmodel.OccludingAppDeviceEntryMessageViewModel;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.phone.ScreenOffAnimationController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator;
import com.android.systemui.util.kotlin.DisposableHandles;
import com.google.android.msdl.domain.MSDLPlayer;
import com.google.android.systemui.biometrics.DeviceEntryUnlockTrackerViewBinderGoogle;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
import dagger.Lazy;
import java.util.Optional;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardViewConfigurator implements CoreStartable {
    public final ChipbarCoordinator chipbarCoordinator;
    public final KeyguardClockInteractor clockInteractor;
    public final ConfigurationState configuration;
    public final Context context;
    public final DeviceEntryHapticsInteractor deviceEntryHapticsInteractor;
    public final Optional deviceEntryUnlockTrackerViewBinder;
    public final FalsingManager falsingManager;
    public final InteractionJankMonitor interactionJankMonitor;
    public final KeyguardBlueprintViewModel keyguardBlueprintViewModel;
    public final KeyguardClockViewModel keyguardClockViewModel;
    public final KeyguardIndicationController keyguardIndicationController;
    public final KeyguardRootView keyguardRootView;
    public final KeyguardRootViewModel keyguardRootViewModel;
    public final DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory keyguardStatusViewComponentFactory;
    public KeyguardStatusViewController keyguardStatusViewController;
    public final KeyguardViewMediator keyguardViewMediator;
    public final CoroutineDispatcher mainDispatcher;
    public final MSDLPlayer msdlPlayer;
    public final OccludingAppDeviceEntryMessageViewModel occludingAppDeviceEntryMessageViewModel;
    public DisposableHandles rootViewHandle;
    public final ScreenOffAnimationController screenOffAnimationController;
    public final ShadeInteractor shadeInteractor;
    public final KeyguardSmartspaceViewModel smartspaceViewModel;
    public final StatusBarKeyguardViewManager statusBarKeyguardViewManager;
    public final VibratorHelper vibratorHelper;

    public KeyguardViewConfigurator(KeyguardRootView keyguardRootView, KeyguardRootViewModel keyguardRootViewModel, KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel, NotificationShadeWindowView notificationShadeWindowView, KeyguardIndicationController keyguardIndicationController, ScreenOffAnimationController screenOffAnimationController, OccludingAppDeviceEntryMessageViewModel occludingAppDeviceEntryMessageViewModel, ChipbarCoordinator chipbarCoordinator, KeyguardBlueprintViewModel keyguardBlueprintViewModel, DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory, ConfigurationState configurationState, Context context, KeyguardIndicationController keyguardIndicationController2, Lazy lazy, ShadeInteractor shadeInteractor, InteractionJankMonitor interactionJankMonitor, DeviceEntryHapticsInteractor deviceEntryHapticsInteractor, VibratorHelper vibratorHelper, FalsingManager falsingManager, KeyguardClockViewModel keyguardClockViewModel, KeyguardSmartspaceViewModel keyguardSmartspaceViewModel, KeyguardClockInteractor keyguardClockInteractor, KeyguardViewMediator keyguardViewMediator, Optional optional, StatusBarKeyguardViewManager statusBarKeyguardViewManager, CoroutineDispatcher coroutineDispatcher, MSDLPlayer mSDLPlayer) {
        this.keyguardRootView = keyguardRootView;
        this.keyguardRootViewModel = keyguardRootViewModel;
        this.screenOffAnimationController = screenOffAnimationController;
        this.occludingAppDeviceEntryMessageViewModel = occludingAppDeviceEntryMessageViewModel;
        this.chipbarCoordinator = chipbarCoordinator;
        this.keyguardBlueprintViewModel = keyguardBlueprintViewModel;
        this.keyguardStatusViewComponentFactory = daggerSysUIGoogleGlobalRootComponent$DozeComponentFactory;
        this.configuration = configurationState;
        this.context = context;
        this.keyguardIndicationController = keyguardIndicationController2;
        this.shadeInteractor = shadeInteractor;
        this.interactionJankMonitor = interactionJankMonitor;
        this.deviceEntryHapticsInteractor = deviceEntryHapticsInteractor;
        this.vibratorHelper = vibratorHelper;
        this.falsingManager = falsingManager;
        this.keyguardClockViewModel = keyguardClockViewModel;
        this.smartspaceViewModel = keyguardSmartspaceViewModel;
        this.clockInteractor = keyguardClockInteractor;
        this.keyguardViewMediator = keyguardViewMediator;
        this.deviceEntryUnlockTrackerViewBinder = optional;
        this.statusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.mainDispatcher = coroutineDispatcher;
        this.msdlPlayer = mSDLPlayer;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        DisposableHandles disposableHandles = this.rootViewHandle;
        if (disposableHandles != null) {
            disposableHandles.dispose();
        }
        InteractionJankMonitor interactionJankMonitor = this.interactionJankMonitor;
        KeyguardBlueprintViewModel keyguardBlueprintViewModel = this.keyguardBlueprintViewModel;
        this.rootViewHandle = KeyguardRootViewBinder.bind(this.keyguardRootView, this.keyguardRootViewModel, keyguardBlueprintViewModel, this.configuration, this.occludingAppDeviceEntryMessageViewModel, this.chipbarCoordinator, this.screenOffAnimationController, this.shadeInteractor, this.clockInteractor, this.keyguardClockViewModel, interactionJankMonitor, this.deviceEntryHapticsInteractor, this.vibratorHelper, this.falsingManager, this.keyguardViewMediator, this.statusBarKeyguardViewManager, this.mainDispatcher, this.msdlPlayer);
        this.keyguardIndicationController.setIndicationArea(new KeyguardIndicationArea(this.context));
        KeyguardRootView keyguardRootView = this.keyguardRootView;
        KeyguardBlueprintViewBinder.bind(keyguardBlueprintViewModel, keyguardRootView, this.keyguardClockViewModel, this.smartspaceViewModel);
        if (this.deviceEntryUnlockTrackerViewBinder.isPresent()) {
            ((DeviceEntryUnlockTrackerViewBinderGoogle) this.deviceEntryUnlockTrackerViewBinder.get()).bind(keyguardRootView);
        }
    }
}

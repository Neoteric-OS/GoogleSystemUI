package com.google.android.systemui.dagger;

import android.app.AlarmManager;
import android.app.IActivityTaskManager;
import android.app.IWallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.devicestate.DeviceStateManager;
import android.hardware.display.DisplayManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.UserManager;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.view.IWindowManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import androidx.asynclayoutinflater.view.AsyncLayoutInflater;
import androidx.lifecycle.LifecycleRegistry;
import com.android.app.tracing.coroutines.TraceContextElementKt;
import com.android.app.tracing.coroutines.TraceDataThreadLocal;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.app.viewcapture.data.ViewNode;
import com.android.dream.lowlight.LowLightTransitionCoordinator;
import com.android.internal.foldables.FoldLockSettingAvailabilityProvider;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.statusbar.IStatusBarService;
import com.android.internal.util.LatencyTracker;
import com.android.internal.widget.LockPatternUtils;
import com.android.keyguard.AdminSecondaryLockScreenController;
import com.android.keyguard.EmergencyButtonController;
import com.android.keyguard.KeyguardInputViewController;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardSecurityContainer;
import com.android.keyguard.KeyguardSecurityContainerController;
import com.android.keyguard.KeyguardSecurityModel;
import com.android.keyguard.KeyguardSecurityViewFlipper;
import com.android.keyguard.KeyguardSecurityViewFlipperController;
import com.android.keyguard.KeyguardUnfoldTransition;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.LiftToActivateListener;
import com.android.keyguard.UserActivityNotifier;
import com.android.keyguard.domain.interactor.KeyguardKeyboardInteractor;
import com.android.launcher3.icons.IconFactory;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.media.data.repository.SpatializerRepositoryImpl;
import com.android.settingslib.media.domain.interactor.SpatializerInteractor;
import com.android.settingslib.volume.data.repository.AudioRepository;
import com.android.settingslib.volume.data.repository.MediaControllerRepositoryImpl;
import com.android.settingslib.volume.domain.interactor.AudioModeInteractor;
import com.android.systemui.accessibility.domain.interactor.CaptioningInteractor;
import com.android.systemui.ambient.statusbar.ui.AmbientStatusBarView;
import com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController;
import com.android.systemui.ambient.touch.scrim.BouncerlessScrimController;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.battery.BatteryMeterView;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.FaceAuthAccessibilityDelegate;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor;
import com.android.systemui.bouncer.ui.helper.BouncerHapticPlayer;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.classifier.FalsingA11yDelegate;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.complication.ComplicationHostViewController;
import com.android.systemui.demomode.DemoModeController;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.dock.DockManager;
import com.android.systemui.doze.AlwaysOnDisplayPolicy;
import com.android.systemui.doze.DozeAuthRemover;
import com.android.systemui.doze.DozeBrightnessHostForwarder;
import com.android.systemui.doze.DozeDockHandler;
import com.android.systemui.doze.DozeFalsingManagerAdapter;
import com.android.systemui.doze.DozeLog;
import com.android.systemui.doze.DozeMachine;
import com.android.systemui.doze.DozePauser;
import com.android.systemui.doze.DozeScreenBrightness;
import com.android.systemui.doze.DozeScreenState;
import com.android.systemui.doze.DozeScreenStatePreventingAdapter;
import com.android.systemui.doze.DozeSensors;
import com.android.systemui.doze.DozeService;
import com.android.systemui.doze.DozeSuppressor;
import com.android.systemui.doze.DozeSuspendScreenStatePreventingAdapter;
import com.android.systemui.doze.DozeTransitionListener;
import com.android.systemui.doze.DozeTriggers;
import com.android.systemui.doze.DozeUi;
import com.android.systemui.doze.DozeWallpaperState;
import com.android.systemui.dreams.DreamOverlayAnimationsController;
import com.android.systemui.dreams.DreamOverlayContainerView;
import com.android.systemui.dreams.DreamOverlayContainerViewController;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.dreams.ui.viewmodel.DreamViewModel;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.systemui.haptics.qs.QSLongPressEffect;
import com.android.systemui.keyguard.ScreenLifecycle;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardDismissTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.ui.view.KeyguardRootView;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.media.controls.domain.pipeline.interactor.MediaCarouselInteractor;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.media.controls.util.MediaFeatureFlag;
import com.android.systemui.mediaprojection.MediaProjectionMetricsLogger;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorActivity;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorController;
import com.android.systemui.mediaprojection.appselector.MediaProjectionBlockerEmptyStateProvider;
import com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerLabelLoader;
import com.android.systemui.mediaprojection.appselector.data.ActivityTaskManagerThumbnailLoader;
import com.android.systemui.mediaprojection.appselector.data.BasicPackageManagerAppIconLoader;
import com.android.systemui.mediaprojection.appselector.data.ShellRecentTaskListProvider;
import com.android.systemui.mediaprojection.appselector.view.MediaProjectionRecentsViewController;
import com.android.systemui.mediaprojection.appselector.view.TaskPreviewSizeProvider;
import com.android.systemui.mediaprojection.appselector.view.WindowMetricsProviderImpl;
import com.android.systemui.mediaprojection.devicepolicy.ScreenCaptureDevicePolicyResolver;
import com.android.systemui.model.SysUiState;
import com.android.systemui.navigationbar.NavBarHelper;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.views.NavigationBar;
import com.android.systemui.navigationbar.views.NavigationBarFrame;
import com.android.systemui.navigationbar.views.NavigationBarTransitions;
import com.android.systemui.navigationbar.views.NavigationBarView;
import com.android.systemui.navigationbar.views.buttons.DeadZone;
import com.android.systemui.navigationbar.views.buttons.NavBarButtonClickLogger;
import com.android.systemui.navigationbar.views.buttons.NavbarOrientationTrackingLogger;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.qs.QSAnimator;
import com.android.systemui.qs.QSContainerImpl;
import com.android.systemui.qs.QSContainerImplController;
import com.android.systemui.qs.QSExpansionPathInterpolator;
import com.android.systemui.qs.QSFooterView;
import com.android.systemui.qs.QSFooterViewController;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSPanel;
import com.android.systemui.qs.QSPanelController;
import com.android.systemui.qs.QSPanelController_Factory;
import com.android.systemui.qs.QSSquishinessController;
import com.android.systemui.qs.QSTileRevealController;
import com.android.systemui.qs.QuickQSPanel;
import com.android.systemui.qs.QuickQSPanelController;
import com.android.systemui.qs.QuickStatusBarHeaderController;
import com.android.systemui.qs.customize.QSCustomizer;
import com.android.systemui.qs.customize.QSCustomizerController;
import com.android.systemui.qs.customize.TileAdapter;
import com.android.systemui.qs.customize.TileQueryHelper;
import com.android.systemui.qs.dagger.QSScopeModule_ProvidesQuickStatusBarHeaderFactory;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.recents.Recents;
import com.android.systemui.retail.domain.interactor.RetailModeInteractorImpl;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.brightness.BrightnessController;
import com.android.systemui.shade.NotificationPanelUnfoldAnimationController;
import com.android.systemui.shade.NotificationShadeWindowView;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeLogger;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.PackageManagerWrapper;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.statusbar.BlurUtils;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.HeadsUpStatusBarView;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeDepthController;
import com.android.systemui.statusbar.RemoteInputNotificationRebuilder;
import com.android.systemui.statusbar.SmartReplyController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.data.repository.StatusBarModeRepositoryImpl;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.systemui.statusbar.notification.AssistantFeedbackController;
import com.android.systemui.statusbar.notification.ColorUpdateLogger;
import com.android.systemui.statusbar.notification.DynamicPrivacyController;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotifLiveDataStoreImpl;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.coordinator.BubbleCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.BundleCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.ColorizedFgsCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.ConversationCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.DataStoreCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.DebugModeCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.DeviceProvisionedCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.DismissibilityCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.DreamCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.GroupCountCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.GroupWhenCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.GutsCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.HeadsUpCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.coordinator.HideLocallyDismissedNotifsCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.HideNotifsForOtherUsersCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.KeyguardCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.coordinator.LockScreenMinimalismCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.LockScreenMinimalismCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.coordinator.MediaCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.NotifCoordinatorsImpl;
import com.android.systemui.statusbar.notification.collection.coordinator.NotificationStatsLoggerCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.OriginalUnseenKeyguardCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.PreparationCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.coordinator.RankingCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.RemoteInputCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.RowAlertTimeCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.RowAppearanceCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.SensitiveContentCoordinatorImpl;
import com.android.systemui.statusbar.notification.collection.coordinator.ShadeEventCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.SmartspaceDedupingCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.StackCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.ViewConfigCoordinator;
import com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinator;
import com.android.systemui.statusbar.notification.collection.inflation.BindEventManagerImpl;
import com.android.systemui.statusbar.notification.collection.inflation.NotifInflater;
import com.android.systemui.statusbar.notification.collection.inflation.NotifUiAdjustmentProvider;
import com.android.systemui.statusbar.notification.collection.provider.DebugModeFilterProvider;
import com.android.systemui.statusbar.notification.collection.provider.HighPriorityProvider;
import com.android.systemui.statusbar.notification.collection.provider.LaunchFullScreenIntentProvider;
import com.android.systemui.statusbar.notification.collection.provider.NotificationDismissibilityProviderImpl;
import com.android.systemui.statusbar.notification.collection.provider.SectionHeaderVisibilityProvider;
import com.android.systemui.statusbar.notification.collection.provider.SectionStyleProvider;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl;
import com.android.systemui.statusbar.notification.collection.render.NodeController;
import com.android.systemui.statusbar.notification.collection.render.NotifViewBarn;
import com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl;
import com.android.systemui.statusbar.notification.data.repository.ActiveNotificationListRepository;
import com.android.systemui.statusbar.notification.data.repository.HeadsUpNotificationIconViewStateRepository;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationIconInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.RenderNotificationListInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.SeenNotificationsInteractor;
import com.android.systemui.statusbar.notification.icon.IconManager;
import com.android.systemui.statusbar.notification.interruption.HeadsUpViewBinder;
import com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProviderImpl;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.people.PeopleNotificationIdentifierImpl;
import com.android.systemui.statusbar.notification.row.NotifInflationErrorManager;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.stack.NotificationRoundnessManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.AutoHideController;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.DozeServiceHost;
import com.android.systemui.statusbar.phone.HeadsUpAppearanceController;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.LegacyLightsOutNotifController;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.PhoneStatusBarTransitions;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.android.systemui.statusbar.phone.PhoneStatusBarViewController;
import com.android.systemui.statusbar.phone.StatusBarBoundsProvider;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import com.android.systemui.statusbar.phone.StatusBarDemoMode;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarLocation;
import com.android.systemui.statusbar.phone.StatusBarMoveFromCenterAnimationController;
import com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionController;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.statusbar.policy.UserSwitcherController;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.android.systemui.statusbar.window.StatusBarWindowStateController;
import com.android.systemui.touch.TouchInsetManager;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.unfold.FoldAodAnimationController;
import com.android.systemui.unfold.FoldLightRevealOverlayAnimation;
import com.android.systemui.unfold.UnfoldHapticsPlayer;
import com.android.systemui.unfold.UnfoldLatencyTracker;
import com.android.systemui.unfold.UnfoldLightRevealOverlayAnimation;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.unfold.UnfoldTransitionWallpaperController;
import com.android.systemui.unfold.config.ResourceUnfoldTransitionConfig;
import com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider;
import com.android.systemui.unfold.updates.FoldProvider;
import com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider;
import com.android.systemui.user.domain.interactor.SelectedUserInteractor;
import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.systemui.user.ui.viewmodel.StatusBarUserChipViewModel;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.Utils;
import com.android.systemui.util.WallpaperController;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.sensors.AsyncSensorManager;
import com.android.systemui.util.sensors.ProximityCheck;
import com.android.systemui.util.sensors.ProximitySensor;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.view.ViewUtil;
import com.android.systemui.util.wakelock.WakeLock;
import com.android.systemui.volume.domain.interactor.AudioOutputInteractor;
import com.android.systemui.volume.domain.interactor.AudioSharingInteractor;
import com.android.systemui.volume.domain.interactor.DeviceIconInteractor;
import com.android.systemui.volume.domain.startable.AudioModeLoggerStartable;
import com.android.systemui.volume.panel.component.anc.data.repository.AncSliceRepositoryImpl;
import com.android.systemui.volume.panel.component.anc.domain.AncAvailabilityCriteria;
import com.android.systemui.volume.panel.component.anc.domain.interactor.AncSliceInteractor;
import com.android.systemui.volume.panel.component.anc.ui.composable.AncButtonComponent;
import com.android.systemui.volume.panel.component.anc.ui.composable.AncPopup;
import com.android.systemui.volume.panel.component.anc.ui.viewmodel.AncViewModel;
import com.android.systemui.volume.panel.component.bottombar.ui.BottomBarComponent;
import com.android.systemui.volume.panel.component.bottombar.ui.viewmodel.BottomBarViewModel;
import com.android.systemui.volume.panel.component.captioning.CaptioningModule_Companion_ProvideVolumePanelUiComponentFactory;
import com.android.systemui.volume.panel.component.captioning.domain.CaptioningAvailabilityCriteria;
import com.android.systemui.volume.panel.component.captioning.ui.viewmodel.CaptioningViewModel;
import com.android.systemui.volume.panel.component.mediaoutput.data.repository.LocalMediaRepositoryFactoryImpl;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaControllerInteractorImpl;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaDeviceSessionInteractor;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputActionsInteractor;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputComponentInteractor;
import com.android.systemui.volume.panel.component.mediaoutput.domain.interactor.MediaOutputInteractor;
import com.android.systemui.volume.panel.component.mediaoutput.ui.composable.MediaOutputComponent;
import com.android.systemui.volume.panel.component.mediaoutput.ui.viewmodel.MediaOutputViewModel;
import com.android.systemui.volume.panel.component.popup.ui.composable.VolumePanelPopup;
import com.android.systemui.volume.panel.component.spatial.domain.SpatialAudioAvailabilityCriteria;
import com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor;
import com.android.systemui.volume.panel.component.spatial.ui.viewmodel.SpatialAudioViewModel;
import com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioComponent;
import com.android.systemui.volume.panel.component.spatialaudio.ui.composable.SpatialAudioPopup;
import com.android.systemui.volume.panel.component.volume.domain.interactor.AudioSlidersInteractor;
import com.android.systemui.volume.panel.component.volume.ui.composable.VolumeSlidersComponent;
import com.android.systemui.volume.panel.component.volume.ui.viewmodel.AudioVolumeComponentViewModel;
import com.android.systemui.volume.panel.domain.AlwaysAvailableCriteria;
import com.android.systemui.volume.panel.domain.interactor.ComponentsInteractorImpl;
import com.android.systemui.volume.panel.shared.VolumePanelLogger;
import com.android.systemui.volume.panel.ui.composable.ComponentsFactory;
import com.android.systemui.volume.panel.ui.layout.DefaultComponentsLayoutManager;
import com.android.wm.shell.R;
import com.google.android.systemui.volume.domain.startable.MediaDeviceLoggerStartable;
import com.google.android.systemui.volume.panel.SpatializerWrapper;
import com.google.android.systemui.volume.panel.component.anc.domain.AncAvailabilityGoogleCriteria;
import com.google.android.systemui.volume.panel.component.clearcalling.ClearCallingModule_Companion_ProvideClearCallingComponentFactory;
import com.google.android.systemui.volume.panel.component.clearcalling.data.repository.ClearCallingRepositoryImpl;
import com.google.android.systemui.volume.panel.component.clearcalling.domain.ClearCallingAvailabilityCriteria;
import com.google.android.systemui.volume.panel.component.clearcalling.domain.interactor.ClearCallingInteractor;
import com.google.android.systemui.volume.panel.component.clearcalling.ui.viewmodel.ClearCallingViewModel;
import com.google.android.systemui.volume.panel.component.devicesettings.DeviceSettingsModule_Companion_ProvideDeviceSettingsComponentFactory;
import com.google.android.systemui.volume.panel.component.devicesettings.domain.DeviceSettingsAvailabilityCriteria;
import com.google.android.systemui.volume.panel.component.devicesettings.ui.viewmodel.DeviceSettingsViewModel;
import com.google.android.systemui.volume.panel.component.shared.availabilitycriteria.GoogleComponentAvailabilityCriteria;
import com.google.android.systemui.volume.panel.component.spatial.domain.SpatialAudioAvailabilityGoogleCriteria;
import com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor;
import dagger.Lazy;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl {
    public final Provider dozeAuthRemoverProvider;
    public final Provider dozeDockHandlerProvider;
    public final Provider dozeFalsingManagerAdapterProvider;
    public final Provider dozeMachineProvider;
    public final DozeService dozeMachineService;
    public final Provider dozePauserProvider;
    public final Provider dozeScreenBrightnessProvider;
    public final Provider dozeScreenStateProvider;
    public final Provider dozeSuppressorProvider;
    public final Provider dozeTriggersProvider;
    public final Provider dozeUiProvider;
    public final Provider dozeWallpaperStateProvider;
    public final Provider providesDozeWakeLockProvider;
    public final Provider providesWrappedServiceProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SwitchingProvider implements Provider {
        public final /* synthetic */ int $r8$classId;
        public final Object dozeComponentImpl;
        public final int id;
        public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
        public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl;

        public /* synthetic */ SwitchingProvider(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, Object obj, int i, int i2) {
            this.$r8$classId = i2;
            this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
            this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
            this.dozeComponentImpl = obj;
            this.id = i;
        }

        private final Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl) this.dozeComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    Collection collection = (Collection) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.provideEnabledComponentsProvider.get();
                    Provider provider = daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.alwaysAvailableCriteriaProvider;
                    ContextScope contextScope = daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope;
                    VolumePanelLogger volumePanelLogger = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.volumePanelLogger();
                    MapBuilder mapBuilder = new MapBuilder(8);
                    mapBuilder.contributions.put("bottom_bar", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.alwaysAvailableCriteriaProvider);
                    mapBuilder.contributions.put("anc", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.ancAvailabilityGoogleCriteriaProvider);
                    mapBuilder.contributions.put("spatial_audio", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.spatialAudioAvailabilityGoogleCriteriaProvider);
                    mapBuilder.contributions.put("device_settings", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.deviceSettingsAvailabilityCriteriaProvider);
                    mapBuilder.contributions.put("volume_sliders", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.alwaysAvailableCriteriaProvider);
                    mapBuilder.contributions.put("captioning", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.captioningAvailabilityCriteriaProvider);
                    mapBuilder.contributions.put("media_output", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.alwaysAvailableCriteriaProvider);
                    mapBuilder.contributions.put("clear_calling", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.clearCallingAvailabilityCriteriaProvider);
                    return new ComponentsInteractorImpl(collection, provider, contextScope, volumePanelLogger, mapBuilder.build());
                case 1:
                    Set of = SetsKt.setOf("bottom_bar", "anc", "spatial_audio", "captioning", "volume_sliders", "media_output", "clear_calling", "device_settings");
                    Preconditions.checkNotNullFromProvides(of);
                    return of;
                case 2:
                    return new AlwaysAvailableCriteria();
                case 3:
                    return new AncAvailabilityGoogleCriteria((AncAvailabilityCriteria) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.ancAvailabilityCriteriaProvider.get(), (GoogleComponentAvailabilityCriteria) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.googleComponentAvailabilityCriteriaProvider.get(), (PixelDeviceInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.pixelDeviceInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case 4:
                    return new AncAvailabilityCriteria((AncSliceInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.ancSliceInteractorProvider.get());
                case 5:
                    return new AncSliceInteractor((AudioOutputInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.audioOutputInteractorProvider.get(), (AncSliceRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAncSliceRepositoryProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope);
                case 6:
                    return new AudioOutputInteractor((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (AudioRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAudioRepositoryProvider.get(), (AudioModeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAudioModeInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (LocalBluetoothManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLocalBluetoothControllerProvider.get(), (BluetoothAdapter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideBluetoothAdapterProvider.get(), (DeviceIconInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.deviceIconInteractorProvider.get(), (MediaOutputInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.mediaOutputInteractorProvider.get());
                case 7:
                    return new DeviceIconInteractor((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get());
                case 8:
                    return new MediaOutputInteractor((LocalMediaRepositoryFactoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.localMediaRepositoryFactoryImplProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (MediaControllerRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideMediaDeviceSessionRepositoryProvider.get(), (MediaControllerInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaControllerInteractorImplProvider.get());
                case 9:
                    return new GoogleComponentAvailabilityCriteria();
                case 10:
                    return new PixelDeviceInteractor(daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (AudioOutputInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.audioOutputInteractorProvider.get());
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    return new SpatialAudioAvailabilityGoogleCriteria((SpatialAudioAvailabilityCriteria) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.spatialAudioAvailabilityCriteriaProvider.get(), (GoogleComponentAvailabilityCriteria) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.googleComponentAvailabilityCriteriaProvider.get(), (PixelDeviceInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.pixelDeviceInteractorProvider.get(), (AudioOutputInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.audioOutputInteractorProvider.get(), (AudioModeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAudioModeInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    return new SpatialAudioAvailabilityCriteria((SpatialAudioComponentInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.spatialAudioComponentInteractorProvider.get());
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    AudioOutputInteractor audioOutputInteractor = (AudioOutputInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.audioOutputInteractorProvider.get();
                    daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
                    return new SpatialAudioComponentInteractor(audioOutputInteractor, new SpatializerInteractor(new SpatializerRepositoryImpl(new SpatializerWrapper((AudioManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideAudioManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dcServiceClient(), (LocalBluetoothManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLocalBluetoothControllerProvider.get()), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get())), (AudioRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAudioRepositoryProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope);
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    return new DeviceSettingsAvailabilityCriteria((GoogleComponentAvailabilityCriteria) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.googleComponentAvailabilityCriteriaProvider.get(), (PixelDeviceInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.pixelDeviceInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case 15:
                    return new CaptioningAvailabilityCriteria((CaptioningInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.captioningInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case 16:
                    return new ClearCallingAvailabilityCriteria((GoogleComponentAvailabilityCriteria) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.googleComponentAvailabilityCriteriaProvider.get(), (AudioModeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAudioModeInteractorProvider.get(), (ClearCallingInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.clearCallingInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    return new ClearCallingInteractor((ClearCallingRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.clearCallingRepositoryImplProvider.get());
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                    return new ClearCallingRepositoryImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dcServiceClient(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get());
                case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                    daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.getClass();
                    MapBuilder mapBuilder2 = new MapBuilder(8);
                    mapBuilder2.contributions.put("device_settings", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.provideDeviceSettingsComponentProvider);
                    mapBuilder2.contributions.put("captioning", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.provideVolumePanelUiComponentProvider);
                    mapBuilder2.contributions.put("clear_calling", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.provideClearCallingComponentProvider);
                    mapBuilder2.contributions.put("bottom_bar", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.bottomBarComponentProvider);
                    mapBuilder2.contributions.put("anc", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.ancButtonComponentProvider);
                    mapBuilder2.contributions.put("spatial_audio", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.spatialAudioComponentProvider);
                    mapBuilder2.contributions.put("volume_sliders", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.volumeSlidersComponentProvider);
                    mapBuilder2.contributions.put("media_output", daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.mediaOutputComponentProvider);
                    return new ComponentsFactory(mapBuilder2.build());
                case 20:
                    return DeviceSettingsModule_Companion_ProvideDeviceSettingsComponentFactory.provideDeviceSettingsComponent((DeviceSettingsViewModel) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.deviceSettingsViewModelProvider.get());
                case 21:
                    return new DeviceSettingsViewModel(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.viewModel, (PixelDeviceInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.pixelDeviceInteractorProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case 22:
                    return CaptioningModule_Companion_ProvideVolumePanelUiComponentFactory.provideVolumePanelUiComponent((CaptioningViewModel) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.captioningViewModelProvider.get());
                case 23:
                    return new CaptioningViewModel(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (CaptioningInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.captioningInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case 24:
                    return ClearCallingModule_Companion_ProvideClearCallingComponentFactory.provideClearCallingComponent((ClearCallingViewModel) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.clearCallingViewModelProvider.get());
                case 25:
                    return new ClearCallingViewModel(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (ClearCallingInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.clearCallingInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case 26:
                    return new BottomBarComponent((BottomBarViewModel) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.bottomBarViewModelProvider.get());
                case 27:
                    return new BottomBarViewModel((ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.viewModel, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case 28:
                    AncViewModel ancViewModel = (AncViewModel) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.ancViewModelProvider.get();
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.sysUIGoogleSysUIComponentImpl;
                    return new AncButtonComponent(ancViewModel, new AncPopup(new VolumePanelPopup(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.systemUIDialogFactory(), (DialogTransitionAnimator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideDialogTransitionAnimatorProvider.get()), (AncViewModel) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.ancViewModelProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get()));
                case 29:
                    return new AncViewModel(daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (AncSliceInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.ancSliceInteractorProvider.get(), (AncAvailabilityCriteria) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.ancAvailabilityCriteriaProvider.get());
                case 30:
                    SpatialAudioViewModel spatialAudioViewModel = (SpatialAudioViewModel) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.spatialAudioViewModelProvider.get();
                    SpatialAudioViewModel spatialAudioViewModel2 = (SpatialAudioViewModel) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.spatialAudioViewModelProvider.get();
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3 = daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.sysUIGoogleSysUIComponentImpl;
                    return new SpatialAudioComponent(spatialAudioViewModel, new SpatialAudioPopup(spatialAudioViewModel2, new VolumePanelPopup(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3.systemUIDialogFactory(), (DialogTransitionAnimator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3.provideDialogTransitionAnimatorProvider.get()), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get()));
                case 31:
                    return new SpatialAudioViewModel((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (SpatialAudioAvailabilityCriteria) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.spatialAudioAvailabilityCriteriaProvider.get(), (SpatialAudioComponentInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.spatialAudioComponentInteractorProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case 32:
                    return new VolumeSlidersComponent((AudioVolumeComponentViewModel) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.audioVolumeComponentViewModelProvider.get());
                case 33:
                    return new AudioVolumeComponentViewModel(daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (MediaOutputInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.mediaOutputInteractorProvider.get(), (MediaDeviceSessionInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.mediaDeviceSessionInteractorProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$1) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.factoryProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$2) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.factoryProvider2.get(), (AudioModeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAudioModeInteractorProvider.get(), (AudioSlidersInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.audioSlidersInteractorProvider.get());
                case 34:
                    return new MediaDeviceSessionInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (MediaControllerInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaControllerInteractorImplProvider.get(), (MediaControllerRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideMediaDeviceSessionRepositoryProvider.get());
                case 35:
                    return new DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$1(this);
                case 36:
                    return new DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider$2(this);
                case 37:
                    return new AudioSlidersInteractor(daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (MediaOutputInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.mediaOutputInteractorProvider.get(), (AudioModeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAudioModeInteractorProvider.get());
                case 38:
                    return new MediaOutputComponent((MediaOutputViewModel) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.mediaOutputViewModelProvider.get());
                case 39:
                    return new MediaOutputViewModel(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (MediaOutputActionsInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.mediaOutputActionsInteractorProvider.get(), (MediaOutputComponentInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.mediaOutputComponentInteractorProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case 40:
                    return new MediaOutputActionsInteractor(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaOutputDialogManager());
                case 41:
                    return new MediaOutputComponentInteractor(daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (MediaDeviceSessionInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.mediaDeviceSessionInteractorProvider.get(), (AudioOutputInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.audioOutputInteractorProvider.get(), (AudioModeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAudioModeInteractorProvider.get(), (MediaOutputInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.mediaOutputInteractorProvider.get(), (AudioSharingInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAudioSharingInteractorProvider.get());
                case 42:
                    return new DefaultComponentsLayoutManager((String) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.provideBottomBarKeyProvider.get(), (Collection) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.provideHeaderComponentsProvider.get(), (Collection) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.provideFooterComponentsProvider.get());
                case 43:
                    return "bottom_bar";
                case 44:
                    return Collections.singleton("media_output");
                case 45:
                    List listOf = CollectionsKt__CollectionsKt.listOf("anc", "spatial_audio", "clear_calling", "device_settings", "captioning");
                    Preconditions.checkNotNullFromProvides(listOf);
                    return listOf;
                case 46:
                    return new AudioModeLoggerStartable(daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (AudioModeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAudioModeInteractorProvider.get());
                case 47:
                    return new MediaDeviceLoggerStartable(daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.scope, (PixelDeviceInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.pixelDeviceInteractorProvider.get(), (AudioOutputInteractor) daggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl.audioOutputInteractorProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                default:
                    throw new AssertionError(i);
            }
        }

        private final Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl) this.dozeComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    return new MediaProjectionAppSelectorController((ShellRecentTaskListProvider) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.bindRecentTaskListProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.view, (ScreenCaptureDevicePolicyResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenCaptureDevicePolicyResolverProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.hostUserHandle, (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.provideCoroutineScopeProvider.get(), (ComponentName) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.provideAppSelectorComponentNameProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.callingPackage, (ActivityTaskManagerThumbnailLoader) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.bindRecentTaskThumbnailLoaderProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.isFirstStart.booleanValue(), (MediaProjectionMetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaProjectionMetricsLoggerProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.hostUid.intValue());
                case 1:
                    return new ShellRecentTaskListProvider((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setRecentTasks, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (UserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUserManagerProvider.get());
                case 2:
                    CoroutineContext plus = ((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get()).getCoroutineContext().plus(SupervisorKt.SupervisorJob$default());
                    TraceDataThreadLocal traceDataThreadLocal = TraceContextElementKt.traceThreadLocal;
                    return CoroutineScopeKt.CoroutineScope(plus.plus(EmptyCoroutineContext.INSTANCE));
                case 3:
                    return new ComponentName(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (Class<?>) MediaProjectionAppSelectorActivity.class);
                case 4:
                    return new ActivityTaskManagerThumbnailLoader((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (ActivityManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideActivityManagerWrapperProvider.get());
                case 5:
                    return new MediaProjectionRecentsViewController((DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.factoryProvider2.get(), (TaskPreviewSizeProvider) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.taskPreviewSizeProvider.get(), (IActivityTaskManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIActivityTaskManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.resultHandler, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setSplitScreen);
                case 6:
                    return new DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$1(this);
                case 7:
                    return new DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider$2(this);
                case 8:
                    return new BasicPackageManagerAppIconLoader((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (PackageManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerWrapperProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get());
                case 9:
                    return IconFactory.obtain(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                case 10:
                    return new ActivityTaskManagerLabelLoader((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get());
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    return new TaskPreviewSizeProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, new WindowMetricsProviderImpl((WindowManager) daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get()), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get());
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    return new MediaProjectionBlockerEmptyStateProvider(daggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl.hostUserHandle, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1640$$Nest$mpersonalProfileUserHandle(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (ScreenCaptureDevicePolicyResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenCaptureDevicePolicyResolverProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
                default:
                    throw new AssertionError(i);
            }
        }

        private final Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl) this.dozeComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    NavigationBarView navigationBarView = (NavigationBarView) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideNavigationBarviewProvider.get();
                    NavigationBarFrame navigationBarFrame = (NavigationBarFrame) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideNavigationBarFrameProvider.get();
                    Bundle bundle = daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.savedState;
                    Context context = daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.context;
                    WindowManager windowManager = (WindowManager) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideWindowManagerProvider.get();
                    ViewCaptureAwareWindowManager viewCaptureAwareWindowManager = (ViewCaptureAwareWindowManager) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideViewCaptureAwareWindowManagerProvider.get();
                    Lazy lazy = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.assistManagerGoogleProvider);
                    AccessibilityManager accessibilityManager = (AccessibilityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAccessibilityManagerProvider.get();
                    DeviceProvisionedController deviceProvisionedController = (DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get();
                    MetricsLogger metricsLogger = (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get();
                    OverviewProxyService overviewProxyService = (OverviewProxyService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.overviewProxyServiceProvider.get();
                    NavigationModeController navigationModeController = (NavigationModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationModeControllerProvider.get();
                    StatusBarStateController statusBarStateController = (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get();
                    StatusBarKeyguardViewManager statusBarKeyguardViewManager = (StatusBarKeyguardViewManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarKeyguardViewManagerProvider.get();
                    SysUiState sysUiState = (SysUiState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSysUiStateProvider.get();
                    UserTracker userTracker = (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get();
                    CommandQueue commandQueue = (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get();
                    Optional optional = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setPip;
                    Optional of = Optional.of((Recents) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideRecentsProvider.get());
                    Lazy lazy2 = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.optionalOfCentralSurfacesProvider);
                    KeyguardStateController keyguardStateController = (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get();
                    ShadeViewController shadeViewController = (ShadeViewController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeSurfaceProvider.get();
                    PanelExpansionInteractor panelExpansionInteractor = (PanelExpansionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providePanelExpansionInteractorProvider.get();
                    NotificationRemoteInputManager notificationRemoteInputManager = (NotificationRemoteInputManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationRemoteInputManagerProvider.get();
                    NotificationShadeDepthController notificationShadeDepthController = (NotificationShadeDepthController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationShadeDepthControllerProvider.get();
                    Handler handler = (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get();
                    Executor executor = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get();
                    Executor executor2 = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get();
                    UiEventLogger uiEventLogger = (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get();
                    NavBarHelper navBarHelper = (NavBarHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navBarHelperProvider.get();
                    LightBarController lightBarController = (LightBarController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lightBarControllerProvider.get();
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.sysUIGoogleSysUIComponentImpl;
                    JavaAdapter javaAdapter = (JavaAdapter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.javaAdapterProvider.get();
                    DarkIconDispatcher darkIconDispatcher = (DarkIconDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.darkIconDispatcherImplProvider.get();
                    BatteryController batteryController = (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideBatteryControllerProvider.get();
                    NavigationModeController navigationModeController2 = (NavigationModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.navigationModeControllerProvider.get();
                    StatusBarModeRepositoryImpl statusBarModeRepositoryImpl = (StatusBarModeRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.statusBarModeRepositoryImplProvider.get();
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.sysUIGoogleGlobalRootComponentImpl;
                    return new NavigationBar(navigationBarView, navigationBarFrame, bundle, context, windowManager, viewCaptureAwareWindowManager, lazy, accessibilityManager, deviceProvisionedController, metricsLogger, overviewProxyService, navigationModeController, statusBarStateController, statusBarKeyguardViewManager, sysUiState, userTracker, commandQueue, optional, of, lazy2, keyguardStateController, shadeViewController, panelExpansionInteractor, notificationRemoteInputManager, notificationShadeDepthController, handler, executor, executor2, uiEventLogger, navBarHelper, lightBarController, new LightBarController.Factory(javaAdapter, darkIconDispatcher, batteryController, navigationModeController2, statusBarModeRepositoryImpl, (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.dumpManagerProvider.get(), (DisplayTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideDisplayTrackerProvider.get()), (AutoHideController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.autoHideControllerProvider.get(), new AutoHideController.Factory((Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideMainHandlerProvider.get(), (IWindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2.provideIWindowManagerProvider.get()), (Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideOptionalTelecomManagerProvider.get(), (InputMethodManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInputMethodManagerProvider.get(), new DeadZone((NavigationBarView) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideNavigationBarviewProvider.get()), (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get(), (NavigationBarTransitions) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.navigationBarTransitionsProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setBackAnimation, (UserContextProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (WakefulnessLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wakefulnessLifecycleProvider.get(), (TaskStackChangeListeners) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideTaskStackChangeListenersProvider.get(), (DisplayTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDisplayTrackerProvider.get(), new NavBarButtonClickLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideNavBarButtonClickLogBufferProvider.get()), new NavbarOrientationTrackingLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideNavbarOrientationTrackingLogBufferProvider.get()));
                case 1:
                    NavigationBarView navigationBarView2 = (NavigationBarView) ((LayoutInflater) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideLayoutInflaterProvider.get()).inflate(R.layout.navigation_bar, (NavigationBarFrame) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideNavigationBarFrameProvider.get()).findViewById(R.id.navigation_bar_view);
                    Preconditions.checkNotNullFromProvides(navigationBarView2);
                    return navigationBarView2;
                case 2:
                    LayoutInflater from = LayoutInflater.from(daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.context);
                    Preconditions.checkNotNullFromProvides(from);
                    return from;
                case 3:
                    NavigationBarFrame navigationBarFrame2 = (NavigationBarFrame) ((LayoutInflater) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideLayoutInflaterProvider.get()).inflate(R.layout.navigation_bar_window, (ViewGroup) null);
                    Preconditions.checkNotNullFromProvides(navigationBarFrame2);
                    return navigationBarFrame2;
                case 4:
                    WindowManager windowManager2 = (WindowManager) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.context.getSystemService(WindowManager.class);
                    Preconditions.checkNotNullFromProvides(windowManager2);
                    return windowManager2;
                case 5:
                    return new ViewCaptureAwareWindowManager((WindowManager) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideWindowManagerProvider.get(), ConvenienceExtensionsKt.toKotlinLazy(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideViewCaptureProvider)));
                case 6:
                    return new NavigationBarTransitions((NavigationBarView) daggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl.provideNavigationBarviewProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$15) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider17.get(), (DisplayTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDisplayTrackerProvider.get());
                default:
                    throw new AssertionError(i);
            }
        }

        private final Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$QSFragmentComponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) this.dozeComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    return QSPanelController_Factory.newInstance((QSPanel) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView.requireViewById(R.id.quick_settings_panel), (TunerService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerServiceImplProvider.get(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSCustomizerControllerProvider.get(), Utils.useQsMediaPlayer(daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.sysUIGoogleGlobalRootComponentImpl.context), (MediaHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesQSMediaHostProvider.get(), daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.factoryProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSLogger(), (BrightnessController.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider64.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1510$$Nest$mbrightnessSliderControllerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (StatusBarKeyguardViewManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarKeyguardViewManagerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get(), (SwitchingProvider) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSLongPressEffectProvider, (MediaCarouselInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaCarouselInteractorProvider.get());
                case 1:
                    return new QSCustomizerController((QSCustomizer) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.providesQSCutomizerProvider.get(), (TileQueryHelper) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.tileQueryHelperProvider.get(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (TileAdapter) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.tileAdapterProvider.get(), (ScreenLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.screenLifecycleProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (LightBarController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lightBarControllerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case 2:
                    return (QSCustomizer) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView.requireViewById(R.id.qs_customize);
                case 3:
                    return new TileQueryHelper(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get());
                case 4:
                    return new TileAdapter(daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView.getContext(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get());
                case 5:
                    return new QSTileRevealController.Factory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSCustomizerControllerProvider.get());
                case 6:
                    return new QSLongPressEffect((VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideQuickSettingsLogBufferProvider.get());
                case 7:
                    return new QuickQSPanelController((QuickQSPanel) QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView).requireViewById(R.id.quick_qs_panel), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSCustomizerControllerProvider.get(), Utils.useQsMediaPlayer(daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.sysUIGoogleGlobalRootComponentImpl.context), (MediaHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesQuickQSMediaHostProvider.get(), (SwitchingProvider) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.providesQSUsingCollapsedLandscapeMediaProvider, (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSLogger(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get(), (SwitchingProvider) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSLongPressEffectProvider, (MediaCarouselInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaCarouselInteractorProvider.get());
                case 8:
                    return Boolean.valueOf(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources().getBoolean(R.bool.config_quickSettingsMediaLandscapeCollapsed));
                case 9:
                    View view = daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView;
                    QuickQSPanel quickQSPanel = (QuickQSPanel) QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(view).requireViewById(R.id.quick_qs_panel);
                    QSPanelController qSPanelController = (QSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSPanelControllerProvider.get();
                    QuickQSPanelController quickQSPanelController = (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.quickQSPanelControllerProvider.get();
                    QSHost qSHost = (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get();
                    DelayableExecutor delayableExecutor = (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get();
                    return new QSAnimator(view, quickQSPanel, qSPanelController, quickQSPanelController, qSHost, delayableExecutor, (QSExpansionPathInterpolator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.qSExpansionPathInterpolatorProvider.get());
                case 10:
                    return new QSContainerImplController((QSContainerImpl) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView.requireViewById(R.id.quick_settings_container), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSPanelControllerProvider.get(), (QuickStatusBarHeaderController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.quickStatusBarHeaderControllerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get());
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    return new QuickStatusBarHeaderController(QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView), (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.quickQSPanelControllerProvider.get());
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    QSFooterViewController qSFooterViewController = (QSFooterViewController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSFooterViewControllerProvider.get();
                    qSFooterViewController.init$9();
                    return qSFooterViewController;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    return new QSFooterViewController((QSFooterView) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView.requireViewById(R.id.qs_footer), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSPanelControllerProvider.get(), (RetailModeInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.retailModeInteractorImplProvider.get());
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    return new QSSquishinessController((QSAnimator) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSAnimatorProvider.get(), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSPanelControllerProvider.get(), (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.quickQSPanelControllerProvider.get());
                default:
                    throw new AssertionError(i);
            }
        }

        private final Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl) this.dozeComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    QSPanel qSPanel = (QSPanel) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView.requireViewById(R.id.quick_settings_panel);
                    TunerService tunerService = (TunerService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerServiceImplProvider.get();
                    QSHost qSHost = (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get();
                    QSCustomizerController qSCustomizerController = (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSCustomizerControllerProvider.get();
                    Context context = daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
                    return QSPanelController_Factory.newInstance(qSPanel, tunerService, qSHost, qSCustomizerController, false, (MediaHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesQSMediaHostProvider.get(), daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.factoryProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSLogger(), (BrightnessController.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider64.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1510$$Nest$mbrightnessSliderControllerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (StatusBarKeyguardViewManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarKeyguardViewManagerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get(), (SwitchingProvider) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSLongPressEffectProvider, (MediaCarouselInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaCarouselInteractorProvider.get());
                case 1:
                    return new QSCustomizerController((QSCustomizer) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.providesQSCutomizerProvider.get(), (TileQueryHelper) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.tileQueryHelperProvider.get(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (TileAdapter) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.tileAdapterProvider.get(), (ScreenLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.screenLifecycleProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (LightBarController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lightBarControllerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
                case 2:
                    return (QSCustomizer) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView.requireViewById(R.id.qs_customize);
                case 3:
                    return new TileQueryHelper(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get());
                case 4:
                    return new TileAdapter(daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView.getContext(), (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get());
                case 5:
                    return new QSTileRevealController.Factory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSCustomizerControllerProvider.get());
                case 6:
                    return new QSLongPressEffect((VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideQuickSettingsLogBufferProvider.get());
                case 7:
                    QuickQSPanel quickQSPanel = (QuickQSPanel) QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView).requireViewById(R.id.quick_qs_panel);
                    QSHost qSHost2 = (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get();
                    QSCustomizerController qSCustomizerController2 = (QSCustomizerController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSCustomizerControllerProvider.get();
                    Context context2 = daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
                    return new QuickQSPanelController(quickQSPanel, qSHost2, qSCustomizerController2, false, (MediaHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesQuickQSMediaHostProvider.get(), (SwitchingProvider) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.providesQSUsingCollapsedLandscapeMediaProvider, (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSLogger(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get(), (SwitchingProvider) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSLongPressEffectProvider, (MediaCarouselInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaCarouselInteractorProvider.get());
                case 8:
                    Context context3 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
                    return Boolean.FALSE;
                case 9:
                    View view = daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView;
                    QuickQSPanel quickQSPanel2 = (QuickQSPanel) QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(view).requireViewById(R.id.quick_qs_panel);
                    QSPanelController qSPanelController = (QSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSPanelControllerProvider.get();
                    QuickQSPanelController quickQSPanelController = (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.quickQSPanelControllerProvider.get();
                    QSHost qSHost3 = (QSHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSHostAdapterProvider.get();
                    DelayableExecutor delayableExecutor = (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get();
                    return new QSAnimator(view, quickQSPanel2, qSPanelController, quickQSPanelController, qSHost3, delayableExecutor, (QSExpansionPathInterpolator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.qSExpansionPathInterpolatorProvider.get());
                case 10:
                    return new QSContainerImplController((QSContainerImpl) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView.requireViewById(R.id.quick_settings_container), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSPanelControllerProvider.get(), (QuickStatusBarHeaderController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.quickStatusBarHeaderControllerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get());
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    return new QuickStatusBarHeaderController(QSScopeModule_ProvidesQuickStatusBarHeaderFactory.providesQuickStatusBarHeader(daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView), (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.quickQSPanelControllerProvider.get());
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    QSFooterViewController qSFooterViewController = (QSFooterViewController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSFooterViewControllerProvider.get();
                    qSFooterViewController.init$9();
                    return qSFooterViewController;
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    return new QSFooterViewController((QSFooterView) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.rootView.requireViewById(R.id.qs_footer), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSPanelControllerProvider.get(), (RetailModeInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.retailModeInteractorImplProvider.get());
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    return new QSSquishinessController((QSAnimator) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSAnimatorProvider.get(), (QSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.qSPanelControllerProvider.get(), (QuickQSPanelController) daggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl.quickQSPanelControllerProvider.get());
                default:
                    throw new AssertionError(i);
            }
        }

        private final Object get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl$SwitchingProvider() {
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl) this.dozeComponentImpl;
            int i = this.id;
            switch (i) {
                case 0:
                    BatteryMeterView batteryMeterView = (BatteryMeterView) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(R.id.battery);
                    Preconditions.checkNotNullFromProvides(batteryMeterView);
                    return batteryMeterView;
                case 1:
                    return StatusBarLocation.HOME;
                case 2:
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.sysUIGoogleSysUIComponentImpl;
                    Optional optional = (Optional) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideStatusBarScopedTransitionProvider.get();
                    FeatureFlags featureFlags = (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.featureFlagsClassicReleaseProvider.get();
                    StatusBarUserChipViewModel statusBarUserChipViewModel = new StatusBarUserChipViewModel((UserSwitcherInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.userSwitcherInteractorProvider.get());
                    CentralSurfaces centralSurfaces = (CentralSurfaces) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.centralSurfacesGoogleProvider.get();
                    StatusBarWindowStateController statusBarWindowStateController = (StatusBarWindowStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.statusBarWindowStateControllerProvider.get();
                    ShadeController shadeController = (ShadeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideShadeControllerProvider.get();
                    ShadeViewController shadeViewController = (ShadeViewController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideShadeSurfaceProvider.get();
                    PanelExpansionInteractor panelExpansionInteractor = (PanelExpansionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.providePanelExpansionInteractorProvider.get();
                    DelegateFactory delegateFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.providesWindowRootViewProvider;
                    ShadeLogger shadeLogger = new ShadeLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideShadeLogBufferProvider.get());
                    ViewUtil viewUtil = (ViewUtil) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.viewUtilProvider.get();
                    ConfigurationController configurationController = (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.provideGlobalConfigurationControllerProvider.get();
                    StatusOverlayHoverListenerFactory m1695$$Nest$mstatusOverlayHoverListenerFactory = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1695$$Nest$mstatusOverlayHoverListenerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2);
                    DarkIconDispatcher darkIconDispatcher = (DarkIconDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.darkIconDispatcherImplProvider.get();
                    StatusBarContentInsetsProvider statusBarContentInsetsProvider = (StatusBarContentInsetsProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.statusBarContentInsetsProvider.get();
                    PhoneStatusBarView phoneStatusBarView = daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView;
                    UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
                    featureFlags.getClass();
                    return new PhoneStatusBarViewController(phoneStatusBarView, (ScopedUnfoldTransitionProgressProvider) optional.orElse(null), centralSurfaces, statusBarWindowStateController, shadeController, shadeViewController, panelExpansionInteractor, delegateFactory, shadeLogger, statusBarUserChipViewModel, viewUtil, configurationController, m1695$$Nest$mstatusOverlayHoverListenerFactory, darkIconDispatcher, statusBarContentInsetsProvider);
                case 3:
                    return new HeadsUpAppearanceController((HeadsUpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpManagerPhoneProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (PhoneStatusBarTransitions) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.providePhoneStatusBarTransitionsProvider.get(), (KeyguardBypassController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBypassControllerProvider.get(), (NotificationWakeUpCoordinator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationWakeUpCoordinatorProvider.get(), (DarkIconDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.darkIconDispatcherImplProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get(), (NotificationStackScrollLayoutController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationStackScrollLayoutControllerProvider.get(), (ShadeViewController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeSurfaceProvider.get(), (NotificationRoundnessManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationRoundnessManagerProvider.get(), (HeadsUpStatusBarView) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.providesHeasdUpStatusBarViewProvider.get(), (Clock) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.provideClockProvider.get(), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), new HeadsUpNotificationIconInteractor((HeadsUpNotificationIconViewStateRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpNotificationIconViewStateRepositoryProvider.get()), (Optional) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.provideOperatorFrameNameViewProvider.get());
                case 4:
                    return new PhoneStatusBarTransitions(daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView, ((StatusBarWindowControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarWindowControllerProvider.get()).mStatusBarWindowView.findViewById(R.id.status_bar_container));
                case 5:
                    HeadsUpStatusBarView headsUpStatusBarView = (HeadsUpStatusBarView) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(R.id.heads_up_status_bar_view);
                    Preconditions.checkNotNullFromProvides(headsUpStatusBarView);
                    return headsUpStatusBarView;
                case 6:
                    Clock clock = (Clock) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(R.id.clock);
                    Preconditions.checkNotNullFromProvides(clock);
                    return clock;
                case 7:
                    Optional ofNullable = Optional.ofNullable(daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(R.id.operator_name_frame));
                    Preconditions.checkNotNullFromProvides(ofNullable);
                    return ofNullable;
                case 8:
                    return new LegacyLightsOutNotifController((View) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.provideLightsOutNotifViewProvider.get(), (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), (NotifLiveDataStoreImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifLiveDataStoreImplProvider.get(), (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get());
                case 9:
                    View findViewById = daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(R.id.notification_lights_out);
                    Preconditions.checkNotNullFromProvides(findViewById);
                    return findViewById;
                case 10:
                    return new StatusBarDemoMode((Clock) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.provideClockProvider.get(), (View) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.provideOperatorNameViewProvider.get(), (DemoModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDemoModeControllerProvider.get(), (PhoneStatusBarTransitions) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.providePhoneStatusBarTransitionsProvider.get(), (NavigationBarControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationBarControllerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getDisplayId());
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    View inflate = ((ViewStub) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(R.id.operator_name_stub)).inflate();
                    inflate.setVisibility(8);
                    return inflate;
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                    return new StatusBarBoundsProvider((View) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.startSideContentProvider.get(), (View) daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.endSideContentProvider.get());
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                    View findViewById2 = daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(R.id.status_bar_start_side_content);
                    Preconditions.checkNotNullFromProvides(findViewById2);
                    return findViewById2;
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                    View findViewById3 = daggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl.phoneStatusBarView.findViewById(R.id.status_bar_end_side_content);
                    Preconditions.checkNotNullFromProvides(findViewById3);
                    return findViewById3;
                default:
                    throw new AssertionError(i);
            }
        }

        @Override // javax.inject.Provider
        public final Object get() {
            Optional[] optionalArr;
            Object obj;
            int i = 0;
            int i2 = this.id;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
            Object obj2 = this.dozeComponentImpl;
            switch (this.$r8$classId) {
                case 0:
                    DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl) obj2;
                    switch (i2) {
                        case 0:
                            return new DozeMachine((DozeMachine.Service) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesWrappedServiceProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m973$$Nest$mambientDisplayConfiguration(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (WakeLock) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesDozeWakeLockProvider.get(), (WakefulnessLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wakefulnessLifecycleProvider.get(), (DozeLog) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeLogProvider.get(), (DockManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dockObserverProvider.get(), (DozeServiceHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get(), new DozeMachine.Part[]{(DozePauser) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozePauserProvider.get(), (DozeFalsingManagerAdapter) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeFalsingManagerAdapterProvider.get(), (DozeTriggers) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeTriggersProvider.get(), (DozeUi) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeUiProvider.get(), (DozeScreenBrightness) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenBrightnessProvider.get(), (DozeScreenState) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenStateProvider.get(), (DozeWallpaperState) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeWallpaperStateProvider.get(), (DozeDockHandler) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeDockHandlerProvider.get(), (DozeAuthRemover) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeAuthRemoverProvider.get(), (DozeSuppressor) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeSuppressorProvider.get(), (DozeTransitionListener) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.sysUIGoogleSysUIComponentImpl.dozeTransitionListenerProvider.get()}, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get());
                        case 1:
                            DozeService dozeService = daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeMachineService;
                            DozeServiceHost dozeServiceHost = (DozeServiceHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get();
                            DozeParameters dozeParameters = (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get();
                            DozeMachine.Service dozeBrightnessHostForwarder = new DozeBrightnessHostForwarder(dozeService, dozeServiceHost);
                            DozeMachine.Service service = dozeBrightnessHostForwarder;
                            if (!SystemProperties.getBoolean("doze.display.supported", dozeParameters.mResources.getBoolean(R.bool.doze_display_state_supported))) {
                                service = new DozeScreenStatePreventingAdapter(dozeBrightnessHostForwarder);
                            }
                            obj = service;
                            if (!dozeParameters.mResources.getBoolean(R.bool.doze_suspend_display_state_supported)) {
                                return new DozeSuspendScreenStatePreventingAdapter(service);
                            }
                            break;
                        case 2:
                            return ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider18.get()).create("Doze");
                        case 3:
                            return new DozePauser((Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (AlarmManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAlarmManagerProvider.get(), (AlwaysOnDisplayPolicy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alwaysOnDisplayPolicyProvider.get());
                        case 4:
                            return new DozeFalsingManagerAdapter((FalsingCollector) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesFalsingCollectorLegacyProvider.get());
                        case 5:
                            return new DozeTriggers(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DozeServiceHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m973$$Nest$mambientDisplayConfiguration(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get(), (AsyncSensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.asyncSensorManagerProvider.get(), (WakeLock) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesDozeWakeLockProvider.get(), (DockManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dockObserverProvider.get(), (ProximitySensor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideProximitySensorProvider.get(), new ProximityCheck((ProximitySensor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideProximitySensorProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get()), (DozeLog) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeLogProvider.get(), (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get(), (AuthController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.authControllerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (SessionTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sessionTrackerProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (DevicePostureController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.devicePostureControllerImplProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get());
                        case 6:
                            return new DozeUi(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (AlarmManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAlarmManagerProvider.get(), (WakeLock) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesDozeWakeLockProvider.get(), (DozeServiceHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgHandlerProvider.get(), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundDelayableExecutorProvider.get(), (DozeLog) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeLogProvider.get());
                        case 7:
                            return new DozeScreenState((DozeMachine.Service) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesWrappedServiceProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (DozeServiceHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get(), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get(), (WakeLock) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesDozeWakeLockProvider.get(), (AuthController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.authControllerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.udfpsControllerProvider, (DozeLog) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeLogProvider.get(), (DozeScreenBrightness) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.dozeScreenBrightnessProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get());
                        case 8:
                            Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
                            DozeMachine.Service service2 = (DozeMachine.Service) daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.providesWrappedServiceProvider.get();
                            AsyncSensorManager asyncSensorManager = (AsyncSensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.asyncSensorManagerProvider.get();
                            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.sysUIGoogleSysUIComponentImpl;
                            AsyncSensorManager asyncSensorManager2 = (AsyncSensorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.asyncSensorManagerProvider.get();
                            Context context2 = daggerSysUIGoogleGlobalRootComponent$DozeComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
                            String[] stringArray = ((DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2.dozeParametersProvider.get()).mResources.getStringArray(R.array.doze_brightness_sensor_name_posture_mapping);
                            int length = stringArray.length;
                            int i3 = R.string.doze_brightness_sensor_type;
                            if (length != 0) {
                                optionalArr = new Optional[5];
                                Arrays.fill(optionalArr, Optional.empty());
                                HashMap hashMap = new HashMap();
                                while (i < stringArray.length) {
                                    String str = stringArray[i];
                                    if (!hashMap.containsKey(str)) {
                                        hashMap.put(str, Optional.ofNullable(DozeSensors.findSensor(asyncSensorManager2, context2.getString(i3), stringArray[i])));
                                    }
                                    optionalArr[i] = (Optional) hashMap.get(str);
                                    i++;
                                    i3 = R.string.doze_brightness_sensor_type;
                                }
                            } else {
                                optionalArr = new Optional[]{Optional.ofNullable(DozeSensors.findSensor(asyncSensorManager2, context2.getString(R.string.doze_brightness_sensor_type), null))};
                            }
                            return new DozeScreenBrightness(context, service2, asyncSensorManager, optionalArr, (DozeServiceHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get(), new Handler(), (AlwaysOnDisplayPolicy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alwaysOnDisplayPolicyProvider.get(), (WakefulnessLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wakefulnessLifecycleProvider.get(), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get(), (DevicePostureController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.devicePostureControllerImplProvider.get(), (DozeLog) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeLogProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1700$$Nest$msystemSettingsImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (DisplayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDisplayManagerProvider.get());
                        case 9:
                            return new DozeWallpaperState(IWallpaperManager.Stub.asInterface(ServiceManager.getService("wallpaper")), (BiometricUnlockController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.biometricUnlockControllerProvider.get(), (DozeParameters) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeParametersProvider.get());
                        case 10:
                            obj = new DozeDockHandler(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m973$$Nest$mambientDisplayConfiguration(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (DockManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dockObserverProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get());
                            break;
                        case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                            return new DozeAuthRemover((KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get());
                        case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                            return new DozeSuppressor((DozeServiceHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceHostProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m973$$Nest$mambientDisplayConfiguration(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (DozeLog) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeLogProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.biometricUnlockControllerProvider), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get());
                        default:
                            throw new AssertionError(i2);
                    }
                    return obj;
                case 1:
                    DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl = (DaggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl) obj2;
                    switch (i2) {
                        case 0:
                            SectionStyleProvider sectionStyleProvider = (SectionStyleProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sectionStyleProvider.get();
                            DataStoreCoordinator dataStoreCoordinator = (DataStoreCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.dataStoreCoordinatorProvider.get();
                            HideLocallyDismissedNotifsCoordinator hideLocallyDismissedNotifsCoordinator = (HideLocallyDismissedNotifsCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.hideLocallyDismissedNotifsCoordinatorProvider.get();
                            HideNotifsForOtherUsersCoordinator hideNotifsForOtherUsersCoordinator = (HideNotifsForOtherUsersCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.hideNotifsForOtherUsersCoordinatorProvider.get();
                            KeyguardCoordinator keyguardCoordinator = (KeyguardCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.keyguardCoordinatorProvider.get();
                            OriginalUnseenKeyguardCoordinator originalUnseenKeyguardCoordinator = (OriginalUnseenKeyguardCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.originalUnseenKeyguardCoordinatorProvider.get();
                            RankingCoordinator rankingCoordinator = (RankingCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.rankingCoordinatorProvider.get();
                            ColorizedFgsCoordinator colorizedFgsCoordinator = (ColorizedFgsCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.colorizedFgsCoordinatorProvider.get();
                            DeviceProvisionedCoordinator deviceProvisionedCoordinator = (DeviceProvisionedCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.deviceProvisionedCoordinatorProvider.get();
                            BubbleCoordinator bubbleCoordinator = (BubbleCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.bubbleCoordinatorProvider.get();
                            HeadsUpCoordinator headsUpCoordinator = (HeadsUpCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.headsUpCoordinatorProvider.get();
                            GutsCoordinator gutsCoordinator = (GutsCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.gutsCoordinatorProvider.get();
                            ConversationCoordinator conversationCoordinator = (ConversationCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.conversationCoordinatorProvider.get();
                            DebugModeCoordinator debugModeCoordinator = (DebugModeCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.debugModeCoordinatorProvider.get();
                            GroupCountCoordinator groupCountCoordinator = (GroupCountCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.groupCountCoordinatorProvider.get();
                            GroupWhenCoordinator groupWhenCoordinator = (GroupWhenCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.groupWhenCoordinatorProvider.get();
                            MediaCoordinator mediaCoordinator = (MediaCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.mediaCoordinatorProvider.get();
                            PreparationCoordinator preparationCoordinator = (PreparationCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.preparationCoordinatorProvider.get();
                            RemoteInputCoordinator remoteInputCoordinator = (RemoteInputCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.remoteInputCoordinatorProvider.get();
                            RowAlertTimeCoordinator rowAlertTimeCoordinator = (RowAlertTimeCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.rowAlertTimeCoordinatorProvider.get();
                            RowAppearanceCoordinator rowAppearanceCoordinator = (RowAppearanceCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.rowAppearanceCoordinatorProvider.get();
                            StackCoordinator stackCoordinator = (StackCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.stackCoordinatorProvider.get();
                            ShadeEventCoordinator shadeEventCoordinator = (ShadeEventCoordinator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeEventCoordinatorProvider.get();
                            SmartspaceDedupingCoordinator smartspaceDedupingCoordinator = (SmartspaceDedupingCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.smartspaceDedupingCoordinatorProvider.get();
                            ViewConfigCoordinator viewConfigCoordinator = (ViewConfigCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.viewConfigCoordinatorProvider.get();
                            VisualStabilityCoordinator visualStabilityCoordinator = (VisualStabilityCoordinator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.visualStabilityCoordinatorProvider.get();
                            SensitiveContentCoordinatorImpl sensitiveContentCoordinatorImpl = (SensitiveContentCoordinatorImpl) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.sensitiveContentCoordinatorImplProvider.get();
                            DismissibilityCoordinator dismissibilityCoordinator = (DismissibilityCoordinator) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.dismissibilityCoordinatorProvider.get();
                            return new NotifCoordinatorsImpl(sectionStyleProvider, dataStoreCoordinator, hideLocallyDismissedNotifsCoordinator, hideNotifsForOtherUsersCoordinator, keyguardCoordinator, originalUnseenKeyguardCoordinator, rankingCoordinator, colorizedFgsCoordinator, deviceProvisionedCoordinator, bubbleCoordinator, headsUpCoordinator, gutsCoordinator, conversationCoordinator, debugModeCoordinator, groupCountCoordinator, groupWhenCoordinator, mediaCoordinator, preparationCoordinator, remoteInputCoordinator, rowAlertTimeCoordinator, rowAppearanceCoordinator, stackCoordinator, shadeEventCoordinator, smartspaceDedupingCoordinator, viewConfigCoordinator, visualStabilityCoordinator, sensitiveContentCoordinatorImpl, dismissibilityCoordinator);
                        case 1:
                            return new DataStoreCoordinator((NotifLiveDataStoreImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifLiveDataStoreImplProvider.get());
                        case 2:
                            return new HideLocallyDismissedNotifsCoordinator();
                        case 3:
                            return new HideNotifsForOtherUsersCoordinator((NotificationLockscreenUserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationLockscreenUserManagerImplProvider.get());
                        case 4:
                            return new KeyguardCoordinator((KeyguardNotificationVisibilityProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardNotificationVisibilityProviderImplProvider.get(), (SectionHeaderVisibilityProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sectionHeaderVisibilityProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get());
                        case 5:
                            return new OriginalUnseenKeyguardCoordinator((DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (HeadsUpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpManagerPhoneProvider.get(), (KeyguardRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRepositoryImplProvider.get(), (KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get(), new KeyguardCoordinatorLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.sysUIGoogleSysUIComponentImpl.provideUnseenNotificationLogBufferProvider.get()), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (SeenNotificationsInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.seenNotificationsInteractorProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (SceneInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sceneInteractorProvider.get());
                        case 6:
                            return new LockScreenMinimalismCoordinator((DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpNotificationInteractor(), new LockScreenMinimalismCoordinatorLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.sysUIGoogleSysUIComponentImpl.provideUnseenNotificationLogBufferProvider.get()), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (SeenNotificationsInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.seenNotificationsInteractorProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get());
                        case 7:
                            StatusBarStateController statusBarStateController = (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get();
                            HighPriorityProvider highPriorityProvider = (HighPriorityProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.highPriorityProvider.get();
                            Preconditions.checkNotNullFromProvides(((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesAlertingHeaderSubcomponentProvider.get()).getNodeController());
                            SectionHeaderNodeControllerImpl headerController = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesSilentHeaderSubcomponentProvider.get()).getHeaderController();
                            Preconditions.checkNotNullFromProvides(headerController);
                            NodeController nodeController = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesSilentHeaderSubcomponentProvider.get()).getNodeController();
                            Preconditions.checkNotNullFromProvides(nodeController);
                            return new RankingCoordinator(statusBarStateController, highPriorityProvider, headerController, nodeController);
                        case 8:
                            return new ColorizedFgsCoordinator();
                        case 9:
                            DeviceProvisionedController deviceProvisionedController = (DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get();
                            return new DeviceProvisionedCoordinator(deviceProvisionedController);
                        case 10:
                            return new BubbleCoordinator((Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBubblesManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setBubbles, (NotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifCollectionProvider.get());
                        case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                            HeadsUpCoordinatorLogger headsUpCoordinatorLogger = new HeadsUpCoordinatorLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.sysUIGoogleSysUIComponentImpl.provideNotificationHeadsUpLogBufferProvider.get());
                            SystemClock systemClock = (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get();
                            HeadsUpManager headsUpManager = (HeadsUpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpManagerPhoneProvider.get();
                            HeadsUpViewBinder headsUpViewBinder = (HeadsUpViewBinder) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpViewBinderProvider.get();
                            VisualInterruptionDecisionProvider visualInterruptionDecisionProvider = (VisualInterruptionDecisionProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideVisualInterruptionDecisionProvider.get();
                            NotificationRemoteInputManager notificationRemoteInputManager = (NotificationRemoteInputManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationRemoteInputManagerProvider.get();
                            LaunchFullScreenIntentProvider launchFullScreenIntentProvider = (LaunchFullScreenIntentProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.launchFullScreenIntentProvider.get();
                            daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifPipelineFlags();
                            Preconditions.checkNotNullFromProvides(((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesIncomingHeaderSubcomponentProvider.get()).getNodeController());
                            return new HeadsUpCoordinator(headsUpCoordinatorLogger, systemClock, headsUpManager, headsUpViewBinder, visualInterruptionDecisionProvider, notificationRemoteInputManager, launchFullScreenIntentProvider, (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get());
                        case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                            return new GutsCoordinator((NotificationGutsManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationGutsManagerProvider.get(), new GutsCoordinatorLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.sysUIGoogleSysUIComponentImpl.provideNotificationsLogBufferProvider.get()), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get());
                        case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                            PeopleNotificationIdentifierImpl peopleNotificationIdentifierImpl = (PeopleNotificationIdentifierImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.peopleNotificationIdentifierImplProvider.get();
                            IconManager iconManager = (IconManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.iconManagerProvider.get();
                            HighPriorityProvider highPriorityProvider2 = (HighPriorityProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.highPriorityProvider.get();
                            Preconditions.checkNotNullFromProvides(((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesPeopleHeaderSubcomponentProvider.get()).getNodeController());
                            return new ConversationCoordinator(peopleNotificationIdentifierImpl, iconManager, highPriorityProvider2);
                        case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                            return new DebugModeCoordinator((DebugModeFilterProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.debugModeFilterProvider.get());
                        case 15:
                            return new GroupCountCoordinator();
                        case 16:
                            return new GroupWhenCoordinator((DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get());
                        case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                            return new MediaCoordinator(new MediaFeatureFlag(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context), (IStatusBarService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIStatusBarServiceProvider.get(), (IconManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.iconManagerProvider.get());
                        case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                            return new PreparationCoordinator(new PreparationCoordinatorLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.sysUIGoogleSysUIComponentImpl.provideNotificationsLogBufferProvider.get()), (NotifInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifInflaterImplProvider.get(), (NotifInflationErrorManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifInflationErrorManagerProvider.get(), (NotifViewBarn) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifViewBarnProvider.get(), (NotifUiAdjustmentProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifUiAdjustmentProvider.get(), (IStatusBarService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIStatusBarServiceProvider.get(), (BindEventManagerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindEventManagerImplProvider.get(), 9, 500L);
                        case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                            return new RemoteInputCoordinator((DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (RemoteInputNotificationRebuilder) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.remoteInputNotificationRebuilderProvider.get(), (NotificationRemoteInputManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationRemoteInputManagerProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (SmartReplyController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSmartReplyControllerProvider.get());
                        case 20:
                            return new RowAlertTimeCoordinator();
                        case 21:
                            return new RowAppearanceCoordinator(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (AssistantFeedbackController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.assistantFeedbackControllerProvider.get(), (SectionStyleProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sectionStyleProvider.get());
                        case 22:
                            GroupExpansionManagerImpl groupExpansionManagerImpl = (GroupExpansionManagerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.groupExpansionManagerImplProvider.get();
                            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3 = daggerSysUIGoogleGlobalRootComponent$CoordinatorsSubcomponentImpl.sysUIGoogleSysUIComponentImpl;
                            return new StackCoordinator(groupExpansionManagerImpl, new RenderNotificationListInteractor((ActiveNotificationListRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3.activeNotificationListRepositoryProvider.get(), (SectionStyleProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3.sectionStyleProvider.get()), (ActiveNotificationsInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activeNotificationsInteractorProvider.get(), (SensitiveNotificationProtectionController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sensitiveNotificationProtectionControllerImplProvider.get());
                        case 23:
                            return new SmartspaceDedupingCoordinator((SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (LockscreenSmartspaceController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenSmartspaceControllerProvider.get(), (NotifPipeline) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifPipelineProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get());
                        case 24:
                            return new ViewConfigCoordinator((ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (NotificationLockscreenUserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationLockscreenUserManagerImplProvider.get(), (NotificationGutsManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationGutsManagerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (ColorUpdateLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorUpdateLoggerProvider.get());
                        case 25:
                            return new SensitiveContentCoordinatorImpl((DynamicPrivacyController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dynamicPrivacyControllerProvider.get(), (NotificationLockscreenUserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationLockscreenUserManagerImplProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get(), (SensitiveNotificationProtectionController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sensitiveNotificationProtectionControllerImplProvider.get(), (DeviceEntryInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryInteractorProvider.get(), (SceneInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sceneInteractorProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
                        case 26:
                            return new DismissibilityCoordinator((KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (NotificationDismissibilityProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationDismissibilityProviderImplProvider.get());
                        case 27:
                            return new DreamCoordinator((SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (KeyguardRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRepositoryImplProvider.get());
                        case 28:
                            daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
                            Optional empty = Optional.empty();
                            Intrinsics.checkNotNull(empty);
                            return new NotificationStatsLoggerCoordinator(empty);
                        case 29:
                            NodeController nodeController2 = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNewsHeaderSubcomponentProvider.get()).getNodeController();
                            Preconditions.checkNotNullFromProvides(nodeController2);
                            NodeController nodeController3 = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesSocialHeaderSubcomponentProvider.get()).getNodeController();
                            Preconditions.checkNotNullFromProvides(nodeController3);
                            NodeController nodeController4 = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesRecsHeaderSubcomponentProvider.get()).getNodeController();
                            Preconditions.checkNotNullFromProvides(nodeController4);
                            NodeController nodeController5 = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesPromoHeaderSubcomponentProvider.get()).getNodeController();
                            Preconditions.checkNotNullFromProvides(nodeController5);
                            return new BundleCoordinator(nodeController2, nodeController3, nodeController4, nodeController5);
                        default:
                            throw new AssertionError(i2);
                    }
                case 2:
                    DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl) obj2;
                    switch (i2) {
                        case 0:
                            DreamOverlayContainerView dreamOverlayContainerView = (DreamOverlayContainerView) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesDreamOverlayContainerViewProvider.get();
                            ComplicationHostViewController complicationHostViewController = daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.complicationHostViewController;
                            ViewGroup viewGroup = (ViewGroup) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesDreamOverlayContentViewProvider.get();
                            AmbientStatusBarViewController ambientStatusBarViewController = (AmbientStatusBarViewController) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesStatusBarViewControllerProvider.get();
                            LowLightTransitionCoordinator lowLightTransitionCoordinator = (LowLightTransitionCoordinator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.lowLightTransitionCoordinatorProvider.get();
                            TouchInsetManager touchInsetManager = daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.touchInsetManager;
                            TouchInsetManager.TouchInsetSession touchInsetSession = new TouchInsetManager.TouchInsetSession(touchInsetManager, touchInsetManager.mExecutor);
                            BlurUtils blurUtils = (BlurUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.blurUtilsProvider.get();
                            Handler handler = (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get();
                            CoroutineDispatcher coroutineDispatcher = (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get();
                            Resources resources = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources();
                            Preconditions.checkNotNullFromProvides(resources);
                            int intValue = ((Integer) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesMaxBurnInOffsetProvider.get()).intValue();
                            DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.sysUIGoogleGlobalRootComponentImpl;
                            return new DreamOverlayContainerViewController(dreamOverlayContainerView, complicationHostViewController, viewGroup, ambientStatusBarViewController, lowLightTransitionCoordinator, touchInsetSession, blurUtils, handler, coroutineDispatcher, resources, intValue, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2).getInteger(R.integer.config_dreamOverlayBurnInProtectionUpdateIntervalMillis), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2).getInteger(R.integer.config_dreamOverlayMillisUntilFullJitter), (PrimaryBouncerCallbackInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerCallbackInteractorProvider.get(), (DreamOverlayAnimationsController) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.dreamOverlayAnimationsControllerProvider.get(), (DreamOverlayStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamOverlayStateControllerProvider.get(), (BouncerlessScrimController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bouncerlessScrimControllerProvider.get(), (KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get(), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get(), (CommunalInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamManager());
                        case 1:
                            DreamOverlayContainerView dreamOverlayContainerView2 = (DreamOverlayContainerView) com.android.internal.util.Preconditions.checkNotNull((DreamOverlayContainerView) ((LayoutInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providerLayoutInflaterProvider.get()).inflate(R.layout.dream_overlay_container, (ViewGroup) null), "R.layout.dream_layout_container could not be properly inflated");
                            Preconditions.checkNotNullFromProvides(dreamOverlayContainerView2);
                            return dreamOverlayContainerView2;
                        case 2:
                            ViewGroup viewGroup2 = (ViewGroup) com.android.internal.util.Preconditions.checkNotNull((ViewGroup) ((DreamOverlayContainerView) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesDreamOverlayContainerViewProvider.get()).findViewById(R.id.dream_overlay_content), "R.id.dream_overlay_content must not be null");
                            Preconditions.checkNotNullFromProvides(viewGroup2);
                            return viewGroup2;
                        case 3:
                            AmbientStatusBarView ambientStatusBarView = (AmbientStatusBarView) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesDreamOverlayStatusBarViewProvider.get();
                            ambientStatusBarView.getClass();
                            return new DaggerSysUIGoogleGlobalRootComponent$AmbientStatusBarComponentImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, ambientStatusBarView).getController();
                        case 4:
                            AmbientStatusBarView ambientStatusBarView2 = (AmbientStatusBarView) com.android.internal.util.Preconditions.checkNotNull((AmbientStatusBarView) ((DreamOverlayContainerView) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesDreamOverlayContainerViewProvider.get()).findViewById(R.id.dream_overlay_status_bar), "R.id.status_bar must not be null");
                            Preconditions.checkNotNullFromProvides(ambientStatusBarView2);
                            return ambientStatusBarView2;
                        case 5:
                            return Integer.valueOf(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl).getDimensionPixelSize(R.dimen.default_burn_in_prevention_offset));
                        case 6:
                            return new DreamOverlayAnimationsController((BlurUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.blurUtilsProvider.get(), daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.complicationHostViewController, (AmbientStatusBarViewController) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesStatusBarViewControllerProvider.get(), (DreamOverlayStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamOverlayStateControllerProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.sysUIGoogleGlobalRootComponentImpl).getDimensionPixelSize(R.dimen.dream_overlay_anim_blur_radius), (DreamViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamViewModelProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(r1).getInteger(R.integer.config_dreamOverlayInBlurDurationMs), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(r1).getInteger(R.integer.config_dreamOverlayInComplicationsDurationMs), ((Integer) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesDreamInComplicationsTranslationYProvider.get()).intValue(), ((Long) daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.providesDreamInComplicationsTranslationYDurationProvider.get()).longValue(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDreamLogBufferProvider.get());
                        case 7:
                            return Integer.valueOf(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl).getDimensionPixelSize(R.dimen.dream_overlay_entry_y_offset));
                        case 8:
                            return Long.valueOf(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl).getInteger(R.integer.config_dreamOverlayInTranslationYDurationMs));
                        case 9:
                            LifecycleRegistry lifecycleRegistry = daggerSysUIGoogleGlobalRootComponent$DreamOverlayComponentImpl.lifecycleOwner.registry;
                            Preconditions.checkNotNullFromProvides(lifecycleRegistry);
                            return lifecycleRegistry;
                        default:
                            throw new AssertionError(i2);
                    }
                case 3:
                    return get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$GoogleVolumePanelComponentImpl$SwitchingProvider();
                case 4:
                    DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl) obj2;
                    if (i2 == 0) {
                        return new KeyguardSecurityContainerController((KeyguardSecurityContainer) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.providesKeyguardSecurityContainerProvider.get(), (AdminSecondaryLockScreenController.Factory) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.factoryProvider.get(), (LockPatternUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideLockPatternUtilsProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (KeyguardSecurityModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardSecurityModelProvider.get(), (MetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMetricsLoggerProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (KeyguardSecurityViewFlipperController) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.keyguardSecurityViewFlipperControllerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (FalsingCollector) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesFalsingCollectorLegacyProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (UserSwitcherController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userSwitcherControllerProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.globalSettingsImpl(), (SessionTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sessionTrackerProvider.get(), new FalsingA11yDelegate((FalsingCollector) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.sysUIGoogleSysUIComponentImpl.providesFalsingCollectorLegacyProvider.get()), (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideTelephonyManagerProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1707$$Nest$mviewMediatorCallback(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (AudioManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAudioManagerProvider.get(), (DeviceEntryFaceAuthInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesFaceAuthInteractorInstanceProvider.get(), (BouncerMessageInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bouncerMessageInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.javaAdapterProvider, (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get(), (DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get(), (FaceAuthAccessibilityDelegate) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.faceAuthAccessibilityDelegateProvider.get(), (DevicePolicyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDevicePolicyManagerProvider.get(), (KeyguardDismissTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardDismissTransitionInteractorProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerInteractorProvider), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryInteractorProvider);
                    }
                    if (i2 == 1) {
                        ViewGroup viewGroup3 = daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.bouncerContainer;
                        KeyguardSecurityContainer keyguardSecurityContainer = (KeyguardSecurityContainer) ((LayoutInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providerLayoutInflaterProvider.get()).inflate(R.layout.keyguard_security_container_view, viewGroup3, false);
                        viewGroup3.addView(keyguardSecurityContainer);
                        Preconditions.checkNotNullFromProvides(keyguardSecurityContainer);
                        return keyguardSecurityContainer;
                    }
                    if (i2 == 2) {
                        return new AdminSecondaryLockScreenController.Factory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (KeyguardSecurityContainer) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.providesKeyguardSecurityContainerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.selectedUserInteractorProvider.get());
                    }
                    if (i2 != 3) {
                        if (i2 != 4) {
                            throw new AssertionError(i2);
                        }
                        KeyguardSecurityViewFlipper keyguardSecurityViewFlipper = (KeyguardSecurityViewFlipper) ((KeyguardSecurityContainer) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.providesKeyguardSecurityContainerProvider.get()).findViewById(R.id.view_flipper);
                        Preconditions.checkNotNullFromProvides(keyguardSecurityViewFlipper);
                        return keyguardSecurityViewFlipper;
                    }
                    KeyguardSecurityViewFlipper keyguardSecurityViewFlipper2 = (KeyguardSecurityViewFlipper) daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.providesKeyguardSecurityViewFlipperProvider.get();
                    AsyncLayoutInflater asyncLayoutInflater = (AsyncLayoutInflater) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAsyncLayoutInflaterProvider.get();
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4 = daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.sysUIGoogleSysUIComponentImpl;
                    KeyguardUpdateMonitor keyguardUpdateMonitor = (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4.keyguardUpdateMonitorProvider.get();
                    DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3 = daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.sysUIGoogleGlobalRootComponentImpl;
                    LockPatternUtils lockPatternUtils = (LockPatternUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.provideLockPatternUtilsProvider.get();
                    LatencyTracker latencyTracker = (LatencyTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.provideLatencyTrackerProvider.get();
                    KeyguardMessageAreaController.Factory m1583$$Nest$mkeyguardMessageAreaControllerFactory = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1583$$Nest$mkeyguardMessageAreaControllerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4);
                    InputMethodManager inputMethodManager = (InputMethodManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.provideInputMethodManagerProvider.get();
                    DelayableExecutor delayableExecutor = (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.provideMainDelayableExecutorProvider.get();
                    Resources resources2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.context.getResources();
                    Preconditions.checkNotNullFromProvides(resources2);
                    LiftToActivateListener liftToActivateListener = new LiftToActivateListener((AccessibilityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.provideAccessibilityManagerProvider.get());
                    TelephonyManager telephonyManager = (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.provideTelephonyManagerProvider.get();
                    FalsingCollector falsingCollector = (FalsingCollector) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4.providesFalsingCollectorLegacyProvider.get();
                    EmergencyButtonController.Factory emergencyButtonControllerFactory = daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.emergencyButtonControllerFactory();
                    DevicePostureController devicePostureController = (DevicePostureController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4.devicePostureControllerImplProvider.get();
                    StatusBarKeyguardViewManager statusBarKeyguardViewManager = (StatusBarKeyguardViewManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4.statusBarKeyguardViewManagerProvider.get();
                    FeatureFlags featureFlags = (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4.featureFlagsClassicReleaseProvider.get();
                    SelectedUserInteractor selectedUserInteractor = (SelectedUserInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4.selectedUserInteractorProvider.get();
                    UiEventLogger uiEventLogger = (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3.provideUiEventLoggerProvider.get();
                    KeyguardKeyboardInteractor keyguardKeyboardInteractor = (KeyguardKeyboardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4.keyguardKeyboardInteractorProvider.get();
                    BouncerHapticPlayer bouncerHapticPlayer = new BouncerHapticPlayer(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4.provideMSDLPlayerProvider));
                    KeyguardInputViewController.Factory factory = new KeyguardInputViewController.Factory(keyguardUpdateMonitor, lockPatternUtils, latencyTracker, m1583$$Nest$mkeyguardMessageAreaControllerFactory, inputMethodManager, delayableExecutor, resources2, liftToActivateListener, telephonyManager, falsingCollector, emergencyButtonControllerFactory, devicePostureController, statusBarKeyguardViewManager, featureFlags, selectedUserInteractor, uiEventLogger, keyguardKeyboardInteractor, bouncerHapticPlayer, new UserActivityNotifier());
                    daggerSysUIGoogleGlobalRootComponent$KeyguardBouncerComponentImpl.emergencyButtonControllerFactory();
                    return new KeyguardSecurityViewFlipperController(keyguardSecurityViewFlipper2, asyncLayoutInflater, factory, (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get());
                case 5:
                    return get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$MediaProjectionAppSelectorComponentImpl$SwitchingProvider();
                case 6:
                    return get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$NavigationBarComponentImpl$SwitchingProvider();
                case 7:
                    return get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$QSFragmentComponentImpl$SwitchingProvider();
                case 8:
                    return get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$QSSceneComponentImpl$SwitchingProvider();
                case 9:
                    return get$com$google$android$systemui$dagger$DaggerSysUIGoogleGlobalRootComponent$StatusBarFragmentComponentImpl$SwitchingProvider();
                default:
                    DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl = (DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl) obj2;
                    switch (i2) {
                        case 0:
                            return new KeyguardUnfoldTransition(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (KeyguardRootView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesKeyguardRootViewProvider.get(), (NotificationShadeWindowView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNotificationShadeWindowViewProvider.get(), (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (UnfoldTransitionProgressProvider) daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.bindNaturalRotationUnfoldProgressProvider.get());
                        case 1:
                            return new StatusBarMoveFromCenterAnimationController(daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.p3, (ActivityManagerActivityTypeProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.activityManagerActivityTypeProvider.get(), (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get());
                        case 2:
                            return new NotificationPanelUnfoldAnimationController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (StatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.p2);
                        case 3:
                            return new FoldAodAnimationController((DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DeviceStateManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDeviceStateManagerProvider.get(), (WakefulnessLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wakefulnessLifecycleProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.globalSettingsImpl(), (LatencyTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideLatencyTrackerProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.toAodFoldTransitionInteractorProvider));
                        case 4:
                            return new UnfoldLightRevealOverlayAnimation(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldBgProgressHandlerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.p4Provider, daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.p1Provider, (DeviceStateManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDeviceStateManagerProvider.get(), new ThreadFactoryImpl(), (DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1) daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.factoryProvider.get());
                        case 5:
                            return new DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1(this);
                        case 6:
                            return new FoldLightRevealOverlayAnimation((CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldBgDispatcherProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1546$$Nest$mdeviceStateRepositoryImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (PowerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerInteractorProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgApplicationScopeProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.m1502$$Nest$manimationStatusRepositoryImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl), (DaggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl$SwitchingProvider$1) daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.factoryProvider.get(), (FoldLockSettingAvailabilityProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideFoldLockSettingAvailabilityProvider.get(), (InteractionJankMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInteractionJankMonitorProvider.get());
                        case 7:
                            return new UnfoldTransitionWallpaperController(daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.p1Provider.instance, (WallpaperController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wallpaperControllerProvider.get());
                        case 8:
                            return new UnfoldHapticsPlayer(daggerSysUIGoogleGlobalRootComponent$SysUIUnfoldComponentImpl.p1Provider.instance, (FoldProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.deviceStateManagerFoldProvider.get(), (ResourceUnfoldTransitionConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.resourceUnfoldTransitionConfigProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Vibrator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideVibratorProvider.get());
                        case 9:
                            return new UnfoldLatencyTracker((LatencyTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideLatencyTrackerProvider.get(), (DeviceStateManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDeviceStateManagerProvider.get(), (Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.unfoldTransitionProgressProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiBackgroundExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get(), (ScreenLifecycle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.screenLifecycleProvider.get());
                        default:
                            throw new AssertionError(i2);
                    }
            }
        }
    }

    public DaggerSysUIGoogleGlobalRootComponent$DozeComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, DozeService dozeService) {
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUIGoogleSysUIComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl;
        this.dozeMachineService = dozeService;
        int i = 0;
        this.providesWrappedServiceProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 1, i));
        this.providesDozeWakeLockProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 2, i));
        this.dozePauserProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 3, i));
        this.dozeFalsingManagerAdapterProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 4, i));
        this.dozeTriggersProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 5, i));
        this.dozeUiProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 6, i));
        this.dozeScreenBrightnessProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 8, i));
        this.dozeScreenStateProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 7, i));
        this.dozeWallpaperStateProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 9, i));
        this.dozeDockHandlerProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 10, i));
        this.dozeAuthRemoverProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 11, i));
        this.dozeSuppressorProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 12, i));
        this.dozeMachineProvider = DoubleCheck.provider(new SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, this, 0, i));
    }
}

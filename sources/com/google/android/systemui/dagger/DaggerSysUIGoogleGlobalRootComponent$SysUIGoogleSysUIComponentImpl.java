package com.google.android.systemui.dagger;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.DreamManager;
import android.app.NotificationManager;
import android.app.UiModeManager;
import android.app.admin.DevicePolicyManager;
import android.app.role.RoleManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.UserInfo;
import android.content.res.Resources;
import android.hardware.devicestate.DeviceStateManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.nearby.NearbyManager;
import android.net.ConnectivityManager;
import android.net.NetworkScoreManager;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.permission.PermissionManager;
import android.provider.DeviceConfig;
import android.safetycenter.SafetyCenterManager;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.IWindowManager;
import android.view.LayoutInflater;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManagerGlobal;
import android.view.textclassifier.TextClassificationManager;
import com.android.app.motiontool.DdmHandleMotionTool;
import com.android.app.motiontool.MotionToolManager;
import com.android.app.viewcapture.ViewCaptureAwareWindowManager;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.InstanceId;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.util.LatencyTracker;
import com.android.keyguard.CarrierTextManager;
import com.android.keyguard.ClockEventController;
import com.android.keyguard.KeyguardBiometricLockoutLogger;
import com.android.keyguard.KeyguardDisplayManager;
import com.android.keyguard.KeyguardMessageAreaController;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.logging.CarrierTextManagerLogger;
import com.android.keyguard.logging.KeyguardLogger;
import com.android.keyguard.logging.KeyguardTransitionAnimationLogger;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl;
import com.android.settingslib.notification.modes.ZenIconLoader;
import com.android.settingslib.volume.data.repository.AudioRepository;
import com.android.systemui.ForegroundServicesDialog;
import com.android.systemui.GuestResetOrExitSessionReceiver;
import com.android.systemui.GuestSessionNotification;
import com.android.systemui.LatencyTester;
import com.android.systemui.Prefs;
import com.android.systemui.ScreenDecorations;
import com.android.systemui.SliceBroadcastRelayHandler;
import com.android.systemui.SystemUIAppComponentFactoryBase;
import com.android.systemui.SystemUISecondaryUserService;
import com.android.systemui.SystemUIService;
import com.android.systemui.accessibility.AccessibilityLogger;
import com.android.systemui.accessibility.MagnificationImpl;
import com.android.systemui.accessibility.SystemActions;
import com.android.systemui.accessibility.data.repository.ColorCorrectionRepositoryImpl;
import com.android.systemui.accessibility.data.repository.ColorInversionRepositoryImpl;
import com.android.systemui.accessibility.data.repository.NightDisplayRepository;
import com.android.systemui.accessibility.data.repository.OneHandedModeRepositoryImpl;
import com.android.systemui.accessibility.domain.interactor.AccessibilityInteractor;
import com.android.systemui.accessibility.extradim.ExtraDimDialogReceiver;
import com.android.systemui.accessibility.hearingaid.HearingDevicesDialogReceiver;
import com.android.systemui.ambient.touch.scrim.BouncerScrimController;
import com.android.systemui.ambient.touch.scrim.BouncerlessScrimController;
import com.android.systemui.ambient.touch.scrim.ScrimManager;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.appops.AppOpsController;
import com.android.systemui.back.domain.interactor.BackActionInteractor;
import com.android.systemui.biometrics.AuthController;
import com.android.systemui.biometrics.AuthRippleController;
import com.android.systemui.biometrics.BiometricNotificationService;
import com.android.systemui.biometrics.domain.interactor.FingerprintPropertyInteractor;
import com.android.systemui.biometrics.domain.interactor.UdfpsOverlayInteractor;
import com.android.systemui.biometrics.ui.binder.SideFpsOverlayViewBinder;
import com.android.systemui.bluetooth.qsdialog.BluetoothTileDialogLogger;
import com.android.systemui.bouncer.domain.interactor.AlternateBouncerInteractor;
import com.android.systemui.bouncer.domain.interactor.BouncerInteractorModule$emergencyDialerIntentFactory$1;
import com.android.systemui.bouncer.domain.interactor.BouncerMessageAuditLogger;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.bouncer.log.BouncerLoggerStartable;
import com.android.systemui.bouncer.ui.BouncerViewImpl;
import com.android.systemui.bouncer.ui.viewmodel.KeyguardBouncerViewModel;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.broadcast.BroadcastDispatcherStartable;
import com.android.systemui.broadcast.BroadcastSender;
import com.android.systemui.broadcast.PendingRemovalStore;
import com.android.systemui.broadcast.logging.BroadcastDispatcherLogger;
import com.android.systemui.camera.data.repository.CameraAutoRotateRepositoryImpl;
import com.android.systemui.camera.data.repository.CameraSensorPrivacyRepositoryImpl;
import com.android.systemui.classifier.DiagonalClassifier;
import com.android.systemui.classifier.DistanceClassifier;
import com.android.systemui.classifier.FalsingCoreStartable;
import com.android.systemui.classifier.FalsingDataProvider;
import com.android.systemui.classifier.PointerCountClassifier;
import com.android.systemui.classifier.ProximityClassifier;
import com.android.systemui.classifier.TypeClassifier;
import com.android.systemui.classifier.ZigZagClassifier;
import com.android.systemui.clipboardoverlay.ClipboardImageLoader;
import com.android.systemui.clipboardoverlay.ClipboardListener;
import com.android.systemui.clipboardoverlay.ClipboardOverlayUtils;
import com.android.systemui.clipboardoverlay.ClipboardOverlayView;
import com.android.systemui.clipboardoverlay.ClipboardOverlayWindow;
import com.android.systemui.clipboardoverlay.ClipboardToast;
import com.android.systemui.clipboardoverlay.ClipboardTransitionExecutor;
import com.android.systemui.common.ui.ConfigurationState;
import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.communal.CommunalBackupRestoreStartable;
import com.android.systemui.communal.CommunalDreamStartable;
import com.android.systemui.communal.CommunalMetricsStartable;
import com.android.systemui.communal.CommunalOngoingContentStartable;
import com.android.systemui.communal.CommunalSceneStartable;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSceneTransitionInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor;
import com.android.systemui.communal.log.CommunalLoggerStartable;
import com.android.systemui.communal.smartspace.SmartspaceInteractionHandler;
import com.android.systemui.communal.ui.compose.CommunalContent;
import com.android.systemui.communal.ui.compose.section.AmbientStatusBarSection;
import com.android.systemui.communal.ui.compose.section.CommunalPopupSection;
import com.android.systemui.communal.ui.view.layout.sections.CommunalAppWidgetSection;
import com.android.systemui.communal.ui.viewmodel.CommunalViewModel;
import com.android.systemui.communal.util.WidgetViewFactory;
import com.android.systemui.communal.widgets.CommunalAppWidgetHost;
import com.android.systemui.communal.widgets.CommunalAppWidgetHostStartable;
import com.android.systemui.communal.widgets.EditWidgetsActivity;
import com.android.systemui.communal.widgets.WidgetInteractionHandler;
import com.android.systemui.controls.management.ControlsEditingActivity;
import com.android.systemui.controls.management.ControlsFavoritingActivity;
import com.android.systemui.controls.management.ControlsProviderSelectorActivity;
import com.android.systemui.controls.management.ControlsRequestDialog;
import com.android.systemui.controls.management.PanelConfirmationDialogFactory;
import com.android.systemui.controls.panels.AuthorizedPanelsRepositoryImpl;
import com.android.systemui.controls.start.ControlsStartable;
import com.android.systemui.controls.ui.ControlsActivity;
import com.android.systemui.controls.ui.ControlsDialogsFactory;
import com.android.systemui.dagger.ContextComponentResolver;
import com.android.systemui.dagger.NightDisplayListenerModule$Builder;
import com.android.systemui.dagger.SharedLibraryModule;
import com.android.systemui.dagger.SystemUIModule$$ExternalSyntheticLambda0;
import com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryUdfpsInteractor;
import com.android.systemui.deviceentry.ui.binder.LiftToRunFaceAuthBinder;
import com.android.systemui.deviceentry.ui.viewmodel.DeviceEntryUdfpsAccessibilityOverlayViewModel;
import com.android.systemui.display.data.repository.DeviceStateRepositoryImpl;
import com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl;
import com.android.systemui.display.ui.viewmodel.ConnectingDisplayViewModel;
import com.android.systemui.doze.DozeService;
import com.android.systemui.doze.util.BurnInHelperWrapper;
import com.android.systemui.dreams.AssistantAttentionMonitor;
import com.android.systemui.dreams.DreamMonitor;
import com.android.systemui.dreams.DreamOverlayService;
import com.android.systemui.dreams.callbacks.DreamStatusBarStateCallback;
import com.android.systemui.dreams.conditions.AssistantAttentionCondition;
import com.android.systemui.dreams.conditions.DreamCondition;
import com.android.systemui.dreams.homecontrols.HomeControlsDreamService;
import com.android.systemui.dreams.homecontrols.HomeControlsDreamStartable;
import com.android.systemui.dump.DumpHandler;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.dump.LogBufferEulogizer;
import com.android.systemui.dump.SystemUIAuxiliaryDumpService;
import com.android.systemui.dump.SystemUIConfigDumpable;
import com.android.systemui.education.domain.interactor.ContextualEducationInteractor;
import com.android.systemui.education.domain.interactor.KeyboardTouchpadEduInteractor;
import com.android.systemui.education.ui.view.ContextualEduUiCoordinator;
import com.android.systemui.flags.ConditionalRestarter;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.FeatureFlagsReleaseStartable;
import com.android.systemui.flags.FlagDependencies;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.NotOccludedCondition;
import com.android.systemui.flags.PluggedInCondition;
import com.android.systemui.flags.ScreenIdleCondition;
import com.android.systemui.flags.SystemExitRestarter;
import com.android.systemui.globalactions.GlobalActionsComponent;
import com.android.systemui.globalactions.ShutdownUi;
import com.android.systemui.inputdevice.tutorial.InputDeviceTutorialLogger;
import com.android.systemui.inputdevice.tutorial.KeyboardTouchpadTutorialCoreStartable;
import com.android.systemui.inputdevice.tutorial.ui.view.KeyboardTouchpadTutorialActivity;
import com.android.systemui.keyboard.BluetoothDialogDelegate;
import com.android.systemui.keyboard.KeyboardUI;
import com.android.systemui.keyboard.PhysicalKeyboardCoreStartable;
import com.android.systemui.keyboard.data.repository.KeyboardRepositoryImpl;
import com.android.systemui.keyboard.shortcut.data.repository.ShortcutHelperStateRepository;
import com.android.systemui.keyboard.shortcut.domain.interactor.ShortcutHelperCategoriesInteractor;
import com.android.systemui.keyboard.shortcut.domain.interactor.ShortcutHelperStateInteractor;
import com.android.systemui.keyboard.shortcut.ui.ShortcutHelperActivityStarter;
import com.android.systemui.keyboard.shortcut.ui.view.ShortcutHelperActivity;
import com.android.systemui.keyboard.shortcut.ui.viewmodel.ShortcutHelperViewModel;
import com.android.systemui.keyboard.stickykeys.data.repository.StickyKeysRepository;
import com.android.systemui.keyboard.stickykeys.ui.viewmodel.StickyKeysIndicatorViewModel;
import com.android.systemui.keyguard.CustomizationProvider;
import com.android.systemui.keyguard.DismissCallbackRegistry;
import com.android.systemui.keyguard.KeyguardService;
import com.android.systemui.keyguard.KeyguardSliceProvider;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.KeyguardViewConfigurator;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.ResourceTrimmer;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.WorkLockActivity;
import com.android.systemui.keyguard.data.quickaffordance.CameraQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.DoNotDisturbQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.FlashlightQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.HomeControlsKeyguardQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.KeyguardQuickAffordanceProviderClientFactoryImpl;
import com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.MuteQuickAffordanceCoreStartable;
import com.android.systemui.keyguard.data.quickaffordance.QrCodeScannerKeyguardQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.QuickAccessWalletKeyguardQuickAffordanceConfig;
import com.android.systemui.keyguard.data.quickaffordance.VideoCameraQuickAffordanceConfig;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.BurnInInteractor;
import com.android.systemui.keyguard.domain.interactor.GlanceableHubTransitions;
import com.android.systemui.keyguard.domain.interactor.KeyguardBlueprintInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardBottomAreaInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardClockInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardQuickAffordanceInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTouchHandlingInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionCoreStartable;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardBlueprint;
import com.android.systemui.keyguard.ui.KeyguardTransitionAnimationFlow;
import com.android.systemui.keyguard.ui.binder.AlternateBouncerViewBinder;
import com.android.systemui.keyguard.ui.binder.KeyguardDismissActionBinder;
import com.android.systemui.keyguard.ui.binder.KeyguardDismissBinder;
import com.android.systemui.keyguard.ui.binder.KeyguardQuickAffordanceViewBinder;
import com.android.systemui.keyguard.ui.binder.SideFpsProgressBarViewBinder;
import com.android.systemui.keyguard.ui.composable.blueprint.CommunalBlueprint;
import com.android.systemui.keyguard.ui.composable.blueprint.ComposableLockscreenSceneBlueprint;
import com.android.systemui.keyguard.ui.composable.section.BottomAreaSection;
import com.android.systemui.keyguard.ui.composable.section.DefaultClockSection;
import com.android.systemui.keyguard.ui.composable.section.LockSection;
import com.android.systemui.keyguard.ui.composable.section.MediaCarouselSection;
import com.android.systemui.keyguard.ui.composable.section.SettingsMenuSection;
import com.android.systemui.keyguard.ui.composable.section.SmartSpaceSection;
import com.android.systemui.keyguard.ui.composable.section.TopAreaSection;
import com.android.systemui.keyguard.ui.composable.section.WeatherClockSection;
import com.android.systemui.keyguard.ui.preview.KeyguardRemotePreviewManager;
import com.android.systemui.keyguard.ui.transitions.DeviceEntryIconTransition;
import com.android.systemui.keyguard.ui.view.layout.KeyguardBlueprintCommandListener;
import com.android.systemui.keyguard.ui.view.layout.sections.AccessibilityActionsSection;
import com.android.systemui.keyguard.ui.view.layout.sections.AodNotificationIconsSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultDeviceEntrySection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultNotificationStackScrollLayoutSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultSettingsPopupMenuSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultShortcutsSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultStatusViewSection;
import com.android.systemui.keyguard.ui.view.layout.sections.DefaultUdfpsAccessibilityOverlaySection;
import com.android.systemui.keyguard.ui.view.layout.sections.KeyguardSliceViewSection;
import com.android.systemui.keyguard.ui.viewmodel.AccessibilityActionsViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerMessageAreaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerUdfpsIconViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AodAlphaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AodBurnInViewModel;
import com.android.systemui.keyguard.ui.viewmodel.AodToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.BouncerToGoneFlows;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryBackgroundViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryForegroundViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DeviceEntryIconViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DozingToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DreamingHostedToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.DreamingToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GlanceableHubToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.GoneToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardBottomAreaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardClockViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardIndicationAreaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardMediaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardQuickAffordancesCombinedViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardRootViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardSettingsMenuViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardSmartspaceViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardTouchHandlingViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToAodTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToDozingTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToDreamingHostedTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToDreamingTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToGlanceableHubTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToGoneTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToOccludedTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.LockscreenToPrimaryBouncerTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.OccludedToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.OffToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToLockscreenTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.ShadeDependentFlows;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.SessionTracker;
import com.android.systemui.log.core.Logger;
import com.android.systemui.log.table.TableLogBufferFactory;
import com.android.systemui.media.RingtonePlayer;
import com.android.systemui.media.controls.domain.pipeline.MediaDataProcessor;
import com.android.systemui.media.controls.domain.pipeline.MediaDeviceLogger;
import com.android.systemui.media.controls.domain.pipeline.MediaDeviceManager;
import com.android.systemui.media.controls.domain.pipeline.interactor.MediaCarouselInteractor;
import com.android.systemui.media.controls.ui.controller.KeyguardMediaController;
import com.android.systemui.media.controls.ui.controller.KeyguardMediaControllerLogger;
import com.android.systemui.media.controls.ui.controller.MediaCarouselController;
import com.android.systemui.media.controls.ui.view.MediaHost;
import com.android.systemui.media.controls.util.LocalMediaManagerFactory;
import com.android.systemui.media.controls.util.MediaControllerFactory;
import com.android.systemui.media.dialog.MediaOutputDialogManager;
import com.android.systemui.media.dialog.MediaOutputDialogReceiver;
import com.android.systemui.media.dialog.MediaOutputSwitcherDialogUI;
import com.android.systemui.media.muteawait.MediaMuteAwaitConnectionCli;
import com.android.systemui.media.muteawait.MediaMuteAwaitConnectionManagerFactory;
import com.android.systemui.media.nearby.NearbyMediaDevicesManager;
import com.android.systemui.media.taptotransfer.MediaTttCommandLineHelper;
import com.android.systemui.media.taptotransfer.receiver.MediaTttChipControllerReceiver;
import com.android.systemui.media.taptotransfer.receiver.MediaTttReceiverLogger;
import com.android.systemui.media.taptotransfer.receiver.MediaTttReceiverRippleController;
import com.android.systemui.media.taptotransfer.sender.MediaTttSenderCoordinator;
import com.android.systemui.mediaprojection.MediaProjectionMetricsLogger;
import com.android.systemui.mediaprojection.appselector.MediaProjectionAppSelectorActivity;
import com.android.systemui.mediaprojection.devicepolicy.MediaProjectionDevicePolicyModule;
import com.android.systemui.mediaprojection.permission.MediaProjectionPermissionActivity;
import com.android.systemui.mediaprojection.taskswitcher.MediaProjectionTaskSwitcherCoreStartable;
import com.android.systemui.mediaprojection.taskswitcher.domain.interactor.TaskSwitchInteractor;
import com.android.systemui.mediaprojection.taskswitcher.ui.viewmodel.TaskSwitcherNotificationViewModel;
import com.android.systemui.model.SysUiState;
import com.android.systemui.motiontool.MotionToolStartable;
import com.android.systemui.navigationbar.NavigationBarControllerImpl;
import com.android.systemui.navigationbar.NavigationModeController;
import com.android.systemui.navigationbar.gestural.BackPanelController;
import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler;
import com.android.systemui.navigationbar.gestural.data.respository.GestureRepositoryImpl;
import com.android.systemui.navigationbar.gestural.domain.GestureInteractor;
import com.android.systemui.notetask.LaunchNotesRoleSettingsTrampolineActivity;
import com.android.systemui.notetask.NoteTaskBubblesController;
import com.android.systemui.notetask.NoteTaskController;
import com.android.systemui.notetask.NoteTaskControllerUpdateService;
import com.android.systemui.notetask.NoteTaskEventLogger;
import com.android.systemui.notetask.NoteTaskInfoResolver;
import com.android.systemui.notetask.NoteTaskInitializer;
import com.android.systemui.notetask.quickaffordance.NoteTaskQuickAffordanceConfig;
import com.android.systemui.notetask.shortcut.CreateNoteTaskShortcutActivity;
import com.android.systemui.notetask.shortcut.LaunchNoteTaskActivity;
import com.android.systemui.people.PeopleProvider;
import com.android.systemui.people.PeopleSpaceActivity;
import com.android.systemui.people.widget.LaunchConversationActivity;
import com.android.systemui.people.widget.PeopleSpaceWidgetManager;
import com.android.systemui.people.widget.PeopleSpaceWidgetPinnedReceiver;
import com.android.systemui.people.widget.PeopleSpaceWidgetProvider;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.plugins.VolumeDialog;
import com.android.systemui.plugins.VolumeDialogController;
import com.android.systemui.plugins.clocks.ClockMessageBuffers;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.PowerUI;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.privacy.OngoingPrivacyChip;
import com.android.systemui.privacy.PrivacyDialogController;
import com.android.systemui.privacy.PrivacyDialogControllerV2;
import com.android.systemui.privacy.PrivacyItemController;
import com.android.systemui.privacy.logging.PrivacyLogger;
import com.android.systemui.process.ProcessWrapper;
import com.android.systemui.process.condition.SystemProcessCondition;
import com.android.systemui.qrcodescanner.controller.QRCodeScannerController;
import com.android.systemui.qs.HeaderPrivacyIconsController;
import com.android.systemui.qs.QSDisableFlagsLogger;
import com.android.systemui.qs.QSFragmentStartable;
import com.android.systemui.qs.QsEventLoggerImpl;
import com.android.systemui.qs.ReduceBrightColorsController;
import com.android.systemui.qs.logging.QSLogger;
import com.android.systemui.qs.panels.shared.model.InfiniteGridLayoutType;
import com.android.systemui.qs.panels.shared.model.PaginatedGridLayoutType;
import com.android.systemui.qs.panels.ui.compose.PaginatedGridLayout;
import com.android.systemui.qs.panels.ui.compose.infinitegrid.InfiniteGridLayout;
import com.android.systemui.qs.panels.ui.viewmodel.PaginatedGridViewModel;
import com.android.systemui.qs.pipeline.data.repository.MinimumTilesResourceRepository;
import com.android.systemui.qs.pipeline.domain.interactor.MinimumTilesInteractor;
import com.android.systemui.qs.pipeline.domain.interactor.PanelInteractor;
import com.android.systemui.qs.pipeline.domain.startable.QSPipelineCoreStartable;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.qs.pipeline.shared.logging.QSPipelineLogger;
import com.android.systemui.qs.shared.model.TileCategory;
import com.android.systemui.qs.tiles.base.actions.QSTileIntentUserInputHandlerImpl;
import com.android.systemui.qs.tiles.base.analytics.QSTileAnalytics;
import com.android.systemui.qs.tiles.base.interactor.DisabledByPolicyInteractorImpl;
import com.android.systemui.qs.tiles.base.interactor.RestrictedLockProxy;
import com.android.systemui.qs.tiles.base.logging.QSTileLogger;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileCoroutineScopeFactory;
import com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelFactory$Static;
import com.android.systemui.qs.tiles.dialog.InternetDialogManager;
import com.android.systemui.qs.tiles.dialog.WifiStateWorker;
import com.android.systemui.qs.tiles.impl.airplane.domain.AirplaneModeMapper;
import com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileDataInteractor;
import com.android.systemui.qs.tiles.impl.airplane.domain.interactor.AirplaneModeTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.alarm.domain.AlarmTileMapper;
import com.android.systemui.qs.tiles.impl.alarm.domain.interactor.AlarmTileDataInteractor;
import com.android.systemui.qs.tiles.impl.alarm.domain.interactor.AlarmTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.colorcorrection.domain.ColorCorrectionTileMapper;
import com.android.systemui.qs.tiles.impl.colorcorrection.domain.interactor.ColorCorrectionTileDataInteractor;
import com.android.systemui.qs.tiles.impl.colorcorrection.domain.interactor.ColorCorrectionUserActionInteractor;
import com.android.systemui.qs.tiles.impl.flashlight.domain.FlashlightMapper;
import com.android.systemui.qs.tiles.impl.flashlight.domain.interactor.FlashlightTileDataInteractor;
import com.android.systemui.qs.tiles.impl.flashlight.domain.interactor.FlashlightTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.fontscaling.domain.FontScalingTileMapper;
import com.android.systemui.qs.tiles.impl.fontscaling.domain.interactor.FontScalingTileDataInteractor;
import com.android.systemui.qs.tiles.impl.fontscaling.domain.interactor.FontScalingTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.internet.domain.InternetTileMapper;
import com.android.systemui.qs.tiles.impl.internet.domain.interactor.InternetTileDataInteractor;
import com.android.systemui.qs.tiles.impl.internet.domain.interactor.InternetTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.inversion.domain.ColorInversionTileMapper;
import com.android.systemui.qs.tiles.impl.inversion.domain.interactor.ColorInversionTileDataInteractor;
import com.android.systemui.qs.tiles.impl.inversion.domain.interactor.ColorInversionUserActionInteractor;
import com.android.systemui.qs.tiles.impl.irecording.IssueRecordingDataInteractor;
import com.android.systemui.qs.tiles.impl.irecording.IssueRecordingMapper;
import com.android.systemui.qs.tiles.impl.irecording.IssueRecordingUserActionInteractor;
import com.android.systemui.qs.tiles.impl.location.domain.LocationTileMapper;
import com.android.systemui.qs.tiles.impl.location.domain.interactor.LocationTileDataInteractor;
import com.android.systemui.qs.tiles.impl.location.domain.interactor.LocationTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.night.domain.interactor.NightDisplayTileDataInteractor;
import com.android.systemui.qs.tiles.impl.onehanded.domain.OneHandedModeTileDataInteractor;
import com.android.systemui.qs.tiles.impl.qr.domain.interactor.QRCodeScannerTileDataInteractor;
import com.android.systemui.qs.tiles.impl.qr.domain.interactor.QRCodeScannerTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.qr.ui.QRCodeScannerTileMapper;
import com.android.systemui.qs.tiles.impl.reducebrightness.domain.interactor.ReduceBrightColorsTileDataInteractor;
import com.android.systemui.qs.tiles.impl.rotation.domain.interactor.RotationLockTileDataInteractor;
import com.android.systemui.qs.tiles.impl.saver.domain.DataSaverTileMapper;
import com.android.systemui.qs.tiles.impl.saver.domain.interactor.DataSaverTileDataInteractor;
import com.android.systemui.qs.tiles.impl.saver.domain.interactor.DataSaverTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.screenrecord.domain.interactor.ScreenRecordTileDataInteractor;
import com.android.systemui.qs.tiles.impl.screenrecord.domain.interactor.ScreenRecordTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.screenrecord.domain.ui.ScreenRecordTileMapper;
import com.android.systemui.qs.tiles.impl.uimodenight.domain.UiModeNightTileMapper;
import com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileDataInteractor;
import com.android.systemui.qs.tiles.impl.uimodenight.domain.interactor.UiModeNightTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.work.domain.interactor.WorkModeTileDataInteractor;
import com.android.systemui.qs.tiles.impl.work.domain.interactor.WorkModeTileUserActionInteractor;
import com.android.systemui.qs.tiles.impl.work.ui.WorkModeTileMapper;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfig;
import com.android.systemui.qs.tiles.viewmodel.QSTileConfigProviderImpl;
import com.android.systemui.qs.tiles.viewmodel.QSTilePolicy;
import com.android.systemui.qs.tiles.viewmodel.QSTileUIConfig;
import com.android.systemui.reardisplay.RearDisplayDialogController;
import com.android.systemui.recents.OverviewProxyRecentsImpl;
import com.android.systemui.recents.OverviewProxyService;
import com.android.systemui.recents.Recents;
import com.android.systemui.recents.ScreenPinningRequest;
import com.android.systemui.recordissue.IssueRecordingService;
import com.android.systemui.recordissue.IssueRecordingState;
import com.android.systemui.scene.domain.interactor.WindowRootViewVisibilityInteractor;
import com.android.systemui.scene.domain.startable.KeyguardStateCallbackStartable;
import com.android.systemui.scene.domain.startable.SceneContainerStartable;
import com.android.systemui.scene.domain.startable.ScrimStartable;
import com.android.systemui.scene.domain.startable.StatusBarStartable;
import com.android.systemui.scene.shared.logger.SceneLogger;
import com.android.systemui.screenrecord.RecordingController;
import com.android.systemui.screenrecord.RecordingService;
import com.android.systemui.screenrecord.data.repository.ScreenRecordRepositoryImpl;
import com.android.systemui.screenshot.ImageExporter;
import com.android.systemui.screenshot.ScreenshotProxyService;
import com.android.systemui.screenshot.SmartActionsReceiver;
import com.android.systemui.screenshot.TakeScreenshotService;
import com.android.systemui.screenshot.appclips.AppClipsActivity;
import com.android.systemui.screenshot.appclips.AppClipsScreenshotHelperService;
import com.android.systemui.screenshot.appclips.AppClipsService;
import com.android.systemui.screenshot.appclips.AppClipsTrampolineActivity;
import com.android.systemui.screenshot.data.repository.ProfileTypeRepositoryImpl;
import com.android.systemui.screenshot.policy.PrivateProfilePolicy;
import com.android.systemui.screenshot.policy.WorkProfilePolicy;
import com.android.systemui.screenshot.scroll.LongScreenshotActivity;
import com.android.systemui.sensorprivacy.SensorUseStartedActivity;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.settings.UserContextProvider;
import com.android.systemui.settings.UserFileManager;
import com.android.systemui.settings.UserFileManagerImpl;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.brightness.BrightnessDialog;
import com.android.systemui.settings.brightness.BrightnessSliderController;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.shade.QsBatteryModeController;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeLogger;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.shade.ShadeWindowLogger;
import com.android.systemui.shade.carrier.ShadeCarrierGroupController;
import com.android.systemui.shade.carrier.ShadeCarrierGroupControllerLogger;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.startable.ShadeStartable;
import com.android.systemui.shared.notifications.data.repository.NotificationSettingsRepository;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.DevicePolicyManagerWrapper;
import com.android.systemui.shared.system.PackageManagerWrapper;
import com.android.systemui.shared.system.TaskStackChangeListeners;
import com.android.systemui.shortcut.ShortcutKeyDispatcher;
import com.android.systemui.smartspace.filters.LockscreenTargetFilter;
import com.android.systemui.smartspace.preconditions.LockscreenPrecondition;
import com.android.systemui.statusbar.BlurUtils;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.ImmersiveModeConfirmation;
import com.android.systemui.statusbar.KeyboardShortcutsReceiver;
import com.android.systemui.statusbar.KeyguardIndicationController;
import com.android.systemui.statusbar.LockscreenShadeScrimTransitionController;
import com.android.systemui.statusbar.NotificationMediaManager;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.OperatorNameViewController;
import com.android.systemui.statusbar.SmartReplyController;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.chips.ron.demo.ui.viewmodel.DemoRonChipViewModel;
import com.android.systemui.statusbar.connectivity.AccessPointController;
import com.android.systemui.statusbar.connectivity.NetworkController;
import com.android.systemui.statusbar.connectivity.WifiStatusTrackerFactory;
import com.android.systemui.statusbar.connectivity.ui.MobileContextProvider;
import com.android.systemui.statusbar.data.repository.NotificationListenerSettingsRepository;
import com.android.systemui.statusbar.data.repository.StatusBarModeRepositoryImpl;
import com.android.systemui.statusbar.disableflags.DisableFlagsLogger;
import com.android.systemui.statusbar.domain.interactor.RemoteInputInteractor;
import com.android.systemui.statusbar.events.SystemEventChipAnimationController;
import com.android.systemui.statusbar.gesture.GesturePointerEventListener;
import com.android.systemui.statusbar.lockscreen.LockscreenSmartspaceController;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.InstantAppNotifier;
import com.android.systemui.statusbar.notification.NotifPipelineFlags;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.coordinator.VisualStabilityCoordinatorLogger;
import com.android.systemui.statusbar.notification.collection.inflation.NotificationRowBinderLogger;
import com.android.systemui.statusbar.notification.collection.listbuilder.ShadeListBuilderLogger;
import com.android.systemui.statusbar.notification.collection.notifcollection.CommonNotifCollection;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionLogger;
import com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl;
import com.android.systemui.statusbar.notification.data.repository.HeadsUpNotificationIconViewStateRepository;
import com.android.systemui.statusbar.notification.data.repository.NotificationsKeyguardViewStateRepository;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationIconInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationsKeyguardInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.SeenNotificationsInteractor;
import com.android.systemui.statusbar.notification.icon.IconBuilder;
import com.android.systemui.statusbar.notification.icon.domain.interactor.AlwaysOnDisplayNotificationIconsInteractor;
import com.android.systemui.statusbar.notification.icon.domain.interactor.NotificationIconsInteractor;
import com.android.systemui.statusbar.notification.icon.domain.interactor.StatusBarNotificationIconsInteractor;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.AlwaysOnDisplayNotificationIconViewStore;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.NotificationIconContainerStatusBarViewBinder;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarIconViewBindingFailureTracker;
import com.android.systemui.statusbar.notification.icon.ui.viewbinder.StatusBarNotificationIconViewStore;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerAlwaysOnDisplayViewModel;
import com.android.systemui.statusbar.notification.icon.ui.viewmodel.NotificationIconContainerStatusBarViewModel;
import com.android.systemui.statusbar.notification.interruption.HeadsUpViewBinderLogger;
import com.android.systemui.statusbar.notification.interruption.KeyguardNotificationVisibilityProviderImpl;
import com.android.systemui.statusbar.notification.interruption.NotificationInterruptLogger;
import com.android.systemui.statusbar.notification.interruption.VisualInterruptionDecisionProvider;
import com.android.systemui.statusbar.notification.logging.NotificationLogger;
import com.android.systemui.statusbar.notification.logging.NotificationMemoryMonitor;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.row.RowInflaterTaskLogger;
import com.android.systemui.statusbar.notification.shelf.ui.viewmodel.NotificationShelfViewModel;
import com.android.systemui.statusbar.notification.stack.domain.interactor.NotificationStackInteractor;
import com.android.systemui.statusbar.notification.stack.ui.view.SharedNotificationContainer;
import com.android.systemui.statusbar.notification.stack.ui.viewbinder.SharedNotificationContainerBinder;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.HideListViewModel;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.NotificationListViewModel;
import com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.phone.ConfigurationControllerStartable;
import com.android.systemui.statusbar.phone.DarkIconDispatcherImpl;
import com.android.systemui.statusbar.phone.DozeParameters;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.phone.KeyguardClockPositionAlgorithm;
import com.android.systemui.statusbar.phone.KeyguardDismissUtil;
import com.android.systemui.statusbar.phone.LSShadeTransitionLogger;
import com.android.systemui.statusbar.phone.LetterboxBackgroundProvider;
import com.android.systemui.statusbar.phone.LightBarController;
import com.android.systemui.statusbar.phone.LockscreenGestureLogger;
import com.android.systemui.statusbar.phone.ManagedProfileController;
import com.android.systemui.statusbar.phone.NotificationListenerWithPlugins;
import com.android.systemui.statusbar.phone.PhoneStatusBarPolicy;
import com.android.systemui.statusbar.phone.ScrimController;
import com.android.systemui.statusbar.phone.StatusBarContentInsetsProvider;
import com.android.systemui.statusbar.phone.StatusBarHeadsUpChangeListener;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.phone.StatusBarSignalPolicy;
import com.android.systemui.statusbar.phone.StatusIconContainer;
import com.android.systemui.statusbar.phone.StatusOverlayHoverListenerFactory;
import com.android.systemui.statusbar.phone.SystemUIDialog;
import com.android.systemui.statusbar.phone.SystemUIDialogFactory;
import com.android.systemui.statusbar.phone.SystemUIDialogManager;
import com.android.systemui.statusbar.phone.data.repository.DarkIconRepositoryImpl;
import com.android.systemui.statusbar.phone.domain.interactor.DarkIconInteractor;
import com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragmentLogger;
import com.android.systemui.statusbar.phone.fragment.CollapsedStatusBarFragmentStartable;
import com.android.systemui.statusbar.phone.ongoingcall.OngoingCallController;
import com.android.systemui.statusbar.phone.ui.StatusBarIconController;
import com.android.systemui.statusbar.pipeline.StatusBarPipelineFlags;
import com.android.systemui.statusbar.pipeline.airplane.data.repository.AirplaneModeRepository;
import com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor;
import com.android.systemui.statusbar.pipeline.ethernet.domain.EthernetInteractor;
import com.android.systemui.statusbar.pipeline.mobile.data.MobileInputLogger;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigCoreStartable;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.CarrierConfigRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoModeMobileConnectionDataSource;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CarrierMergedConnectionRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.FullMobileConnectionRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl;
import com.android.systemui.statusbar.pipeline.mobile.ui.MobileUiAdapter;
import com.android.systemui.statusbar.pipeline.mobile.util.MobileMappingsProxyImpl;
import com.android.systemui.statusbar.pipeline.mobile.util.SubscriptionManagerProxyImpl;
import com.android.systemui.statusbar.pipeline.satellite.ui.viewmodel.DeviceBasedSatelliteViewModel;
import com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.DemoModeWifiDataSource;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.DemoWifiRepository;
import com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl;
import com.android.systemui.statusbar.policy.AccessibilityManagerWrapper;
import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerStartable;
import com.android.systemui.statusbar.policy.BatteryStateNotifier;
import com.android.systemui.statusbar.policy.BluetoothControllerImpl;
import com.android.systemui.statusbar.policy.CastControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DataSaverControllerImpl;
import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.FlashlightControllerImpl;
import com.android.systemui.statusbar.policy.HotspotController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.LocationController;
import com.android.systemui.statusbar.policy.NextAlarmController;
import com.android.systemui.statusbar.policy.RotationLockController;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionControllerLogger;
import com.android.systemui.statusbar.policy.SensorPrivacyControllerImpl;
import com.android.systemui.statusbar.policy.SmartActionInflaterImpl;
import com.android.systemui.statusbar.policy.SmartReplyConstants;
import com.android.systemui.statusbar.policy.SmartReplyInflaterImpl;
import com.android.systemui.statusbar.policy.SmartReplyStateInflaterImpl;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.statusbar.policy.UserInfoControllerImpl;
import com.android.systemui.statusbar.policy.VariableDateViewController;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.data.repository.DeviceProvisioningRepositoryImpl;
import com.android.systemui.statusbar.policy.data.repository.UserSetupRepositoryImpl;
import com.android.systemui.statusbar.policy.domain.interactor.UserSetupInteractor;
import com.android.systemui.statusbar.policy.domain.interactor.ZenModeInteractor;
import com.android.systemui.statusbar.ui.SystemBarUtilsProxyImpl;
import com.android.systemui.statusbar.ui.SystemBarUtilsState;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.android.systemui.stylus.StylusManager;
import com.android.systemui.stylus.StylusUsiPowerStartable;
import com.android.systemui.telephony.TelephonyListenerManager;
import com.android.systemui.telephony.ui.activity.SwitchToManagedProfileForCallActivity;
import com.android.systemui.temporarydisplay.chipbar.ChipbarCoordinator;
import com.android.systemui.theme.ThemeOverlayController;
import com.android.systemui.toast.ToastUI;
import com.android.systemui.touch.TouchInsetManager;
import com.android.systemui.touchpad.tutorial.ui.view.TouchpadTutorialActivity;
import com.android.systemui.tuner.TunerActivity;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.unfold.DisplaySwitchLatencyTracker;
import com.android.systemui.unfold.SysUIUnfoldModule;
import com.android.systemui.unfold.UnfoldInitializationStartable;
import com.android.systemui.unfold.UnfoldTraceLogger;
import com.android.systemui.usb.StorageNotification;
import com.android.systemui.usb.UsbAccessoryUriActivity;
import com.android.systemui.usb.UsbConfirmActivity;
import com.android.systemui.usb.UsbDebuggingActivity;
import com.android.systemui.usb.UsbDebuggingSecondaryUserActivity;
import com.android.systemui.usb.UsbPermissionActivity;
import com.android.systemui.user.CreateUserActivity;
import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.user.ui.dialog.UserSwitcherDialogCoordinator;
import com.android.systemui.user.utils.UserScopedServiceImpl;
import com.android.systemui.util.CarrierConfigTracker;
import com.android.systemui.util.ConvenienceExtensionsKt;
import com.android.systemui.util.DeviceConfigProxy;
import com.android.systemui.util.RingerModeTrackerImpl;
import com.android.systemui.util.animation.data.repository.AnimationStatusRepositoryImpl;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutionImpl;
import com.android.systemui.util.concurrency.GlobalConcurrencyModule_ProvideMainLooperFactory;
import com.android.systemui.util.concurrency.MessageRouterImpl;
import com.android.systemui.util.concurrency.UiThreadContext;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.kotlin.SysUICoroutinesModule;
import com.android.systemui.util.leak.LeakModule;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import com.android.systemui.util.settings.SecureSettings;
import com.android.systemui.util.settings.SystemSettingsImpl;
import com.android.systemui.util.time.DateFormatUtil;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.wakelock.WakeLock;
import com.android.systemui.util.wakelock.WakeLockLogger;
import com.android.systemui.volume.CsdWarningAction;
import com.android.systemui.volume.VolumeControllerAdapter;
import com.android.systemui.volume.VolumeDialogImpl;
import com.android.systemui.volume.VolumeUI;
import com.android.systemui.volume.domain.interactor.VolumeDialogInteractor;
import com.android.systemui.volume.domain.interactor.VolumePanelNavigationInteractor;
import com.android.systemui.volume.panel.domain.interactor.VolumePanelGlobalStateInteractor;
import com.android.systemui.volume.panel.shared.VolumePanelLogger;
import com.android.systemui.volume.panel.shared.flag.VolumePanelFlag;
import com.android.systemui.volume.panel.ui.viewmodel.VolumePanelViewModel;
import com.android.systemui.volume.ui.navigation.VolumeNavigator;
import com.android.systemui.wallet.controller.WalletContextualLocationsService;
import com.android.systemui.wallet.ui.WalletActivity;
import com.android.systemui.wallpapers.ImageWallpaper;
import com.android.systemui.wmshell.WMShell;
import com.android.wm.shell.R;
import com.android.wm.shell.keyguard.KeyguardTransitions;
import com.android.wm.shell.shared.ShellTransitions;
import com.android.wm.shell.sysui.ShellInterface;
import com.google.android.settingslib.dcservice.DcServiceClientImpl;
import com.google.android.systemui.GoogleServices;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.columbus.legacy.ColumbusTargetRequestService;
import com.google.android.systemui.columbus.legacy.feedback.HapticClick;
import com.google.android.systemui.columbus.legacy.feedback.UserActivity;
import com.google.android.systemui.columbus.legacy.gates.CameraVisibility;
import com.google.android.systemui.columbus.legacy.gates.ChargingState;
import com.google.android.systemui.columbus.legacy.gates.FlagEnabled;
import com.google.android.systemui.columbus.legacy.gates.KeyguardProximity;
import com.google.android.systemui.columbus.legacy.gates.PowerSaveState;
import com.google.android.systemui.columbus.legacy.gates.PowerState;
import com.google.android.systemui.columbus.legacy.gates.ScreenTouch;
import com.google.android.systemui.columbus.legacy.gates.SetupWizard;
import com.google.android.systemui.columbus.legacy.gates.SystemKeyPress;
import com.google.android.systemui.columbus.legacy.gates.TelephonyActivity;
import com.google.android.systemui.columbus.legacy.gates.UsbState;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl;
import com.google.android.systemui.dreams.LockscreenWallpaperDreamService;
import com.google.android.systemui.elmyra.actions.DismissTimer;
import com.google.android.systemui.elmyra.actions.SilenceCall;
import com.google.android.systemui.elmyra.actions.SnoozeAlarm;
import com.google.android.systemui.elmyra.feedback.NavUndimEffect;
import com.google.android.systemui.elmyra.feedback.SquishyNavigationButtons;
import com.google.android.systemui.elmyra.gates.KeyguardVisibility;
import com.google.android.systemui.keyguard.ActiveUnlockChipbarManager;
import com.google.android.systemui.keyguard.AmbientIndicationCoreStartable;
import com.google.android.systemui.keyguard.RefreshRateRequesterBinder;
import com.google.android.systemui.keyguard.domain.interactor.AmbientIndicationInteractor;
import com.google.android.systemui.keyguard.ui.composable.blueprint.GoogleDefaultBlueprintModule;
import com.google.android.systemui.keyguard.ui.composable.section.GoogleAmbientIndicationSection;
import com.google.android.systemui.keyguard.ui.sections.DefaultAmbientIndicationAreaSection;
import com.google.android.systemui.keyguard.ui.viewmodel.KeyguardAmbientIndicationViewModel;
import com.google.android.systemui.lowlightclock.LowLightClockAnimationProvider;
import com.google.android.systemui.lowlightclock.LowLightClockDreamService;
import com.google.android.systemui.power.batteryevent.domain.BatteryEventService;
import com.google.android.systemui.power.batteryhealth.HealthService;
import com.google.android.systemui.power.batteryhealth.HealthUpdateReceiver;
import com.google.android.systemui.qs.launcher.LauncherTileService;
import com.google.android.systemui.qs.tiles.impl.battery.domain.interactor.BatterySaverTileDataInteractorGoogle;
import com.google.android.systemui.qs.ui.activity.QSActivity;
import com.google.android.systemui.smartspace.KeyguardSmartspaceStartable;
import com.google.android.systemui.statusbar.phone.WallpaperNotifier;
import com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor;
import com.google.android.systemui.vpn.AdaptivePPNService;
import com.google.common.collect.ImmutableList;
import com.google.hardware.pixel.display.IDisplay;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SetBuilder;
import dagger.internal.SingleCheck;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl implements SysUIGoogleSysUIComponent {
    public final Provider accessibilityButtonModeObserverProvider;
    public final Provider accessibilityButtonTargetsObserverProvider;
    public Provider accessibilityFloatingMenuControllerProvider;
    public final Provider accessibilityGestureTargetsObserverProvider;
    public Provider accessibilityInteractorProvider;
    public Provider accessibilityManagerWrapperProvider;
    public Provider accessibilityQsShortcutsRepositoryImplProvider;
    public Provider accessibilityTilesInteractorProvider;
    public Provider actionFetcherProvider;
    public Provider actionIntentExecutorProvider;
    public final Provider activeNotificationListRepositoryProvider;
    public final Provider activeNotificationsInteractorProvider;
    public Provider activeUnlockChipbarManagerProvider;
    public Provider activeUnlockConfigProvider;
    public Provider activityIntentHelperProvider;
    public final DelegateFactory activityStarterImplProvider;
    public Provider activityStarterInternalImplProvider;
    public Provider activityTaskManagerProxyProvider;
    public Provider activityTaskManagerTasksRepositoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider adapterProvider;
    public Provider adaptivePPNServiceProvider;
    public Provider aiAiCHREGestureSensorProvider;
    public Provider airplaneModeInteractorProvider;
    public final Provider airplaneModeRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider airplaneModeTileProvider;
    public Provider airplaneModeViewModelImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider alarmTileProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider alternateBouncerDependenciesProvider;
    public final Provider alternateBouncerInteractorProvider;
    public Provider alternateBouncerToAodTransitionViewModelProvider;
    public Provider alternateBouncerToDozingTransitionViewModelProvider;
    public Provider alternateBouncerToGoneTransitionViewModelProvider;
    public Provider alternateBouncerToLockscreenTransitionViewModelProvider;
    public Provider alternateBouncerToOccludedTransitionViewModelProvider;
    public Provider alternateBouncerToPrimaryBouncerTransitionViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider alternateBouncerUdfpsAccessibilityOverlayViewModelProvider;
    public Provider alternateBouncerViewBinderProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider alternateBouncerWindowViewModelProvider;
    public final Provider alwaysOnDisplayPolicyProvider;
    public Provider ambientIndicationCoreStartableProvider;
    public Provider ambientIndicationInteractorProvider;
    public Provider ambientIndicationRepositoryProvider;
    public Provider ambientStateProvider;
    public Provider animatedImageNotificationManagerProvider;
    public Provider aodAlphaViewModelProvider;
    public Provider aodBurnInViewModelProvider;
    public Provider aodToGoneTransitionViewModelProvider;
    public Provider aodToLockscreenTransitionViewModelProvider;
    public Provider aodToOccludedTransitionViewModelProvider;
    public Provider aodToPrimaryBouncerTransitionViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider appClipsActivityProvider;
    public Provider appClipsCrossProcessHelperProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider appClipsScreenshotHelperServiceProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider appClipsServiceProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider appClipsTrampolineActivityProvider;
    public Provider appOpsControllerImplProvider;
    public Provider appOpsPrivacyItemMonitorProvider;
    public Provider assistContentRequesterProvider;
    public Provider assistInteractorProvider;
    public Provider assistInvocationEffectProvider;
    public Provider assistInvocationEffectProvider2;
    public DelegateFactory assistManagerGoogleProvider;
    public Provider assistRepositoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider assistantAttentionMonitorProvider;
    public Provider assistantFeedbackControllerProvider;
    public Provider assistantPresenceHandlerProvider;
    public Provider asyncSensorManagerProvider;
    public Provider audioSharingInteractorEmptyImplProvider;
    public Provider audioSharingInteractorImplProvider;
    public Provider audioSharingInteractorProvider;
    public final DelegateFactory authControllerProvider;
    public Provider authRippleControllerProvider;
    public Provider authRippleInteractorProvider;
    public final Provider authenticationInteractorProvider;
    public final Provider authenticationRepositoryImplProvider;
    public Provider autoAddInteractorProvider;
    public Provider autoAddSettingRepositoryProvider;
    public Provider autoHideControllerProvider;
    public Provider avalancheControllerProvider;
    public Provider avalancheProvider;
    public Provider backActionInteractorProvider;
    public Provider backlightDialogViewModelProvider;
    public Provider backupManagerProxyProvider;
    public final Provider batteryControllerLoggerProvider;
    public Provider batteryControllerStartableProvider;
    public Provider batteryEventModuleProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider batteryEventServiceProvider;
    public Provider batteryEventStateControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider batterySaverConfirmationDialogProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider batterySaverTileGoogleProvider;
    public final Provider bgApplicationScopeProvider;
    public final Provider bgCoroutineContextProvider;
    public final Provider bgDispatcherProvider;
    public Provider bindCaptioningRepositoryProvider;
    public Provider bindCapturePolicyListProvider;
    public Provider bindDisplayContentRepositoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider bindDndOrModesTileProvider;
    public Provider bindEventLogProvider;
    public Provider bindEventManagerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider bindInternetTileProvider;
    public Provider bindPackageLabelIconProvider;
    public Provider bindProfileFirstRunResourcesProvider;
    public Provider bindProfileFirstRunSettingsProvider;
    public Provider bindProfileTypeRepositoryProvider;
    public Provider bindRotationPolicyWrapperProvider;
    public Provider bindScreenshotRequestProcessorProvider;
    public final Provider bindShadeModeInteractorProvider;
    public final Provider bindSystemClockProvider;
    public Provider bindSystemStatusAnimationSchedulerProvider;
    public Provider bindSystemUiProxyProvider;
    public Provider bindableIconsRegistryImplProvider;
    public final Provider bindsReaderProvider;
    public final Provider biometricMessageInteractorProvider;
    public Provider biometricNotificationBroadcastReceiverProvider;
    public Provider biometricNotificationDialogFactoryProvider;
    public Provider biometricNotificationServiceProvider;
    public final Provider biometricSettingsRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider biometricStatusInteractorImplProvider;
    public Provider biometricStatusRepositoryImplProvider;
    public DelegateFactory biometricUnlockControllerProvider;
    public Provider biometricUnlockInteractorProvider;
    public Provider biometricUnlockLoggerProvider;
    public Provider bluetoothAutoOnInteractorProvider;
    public Provider bluetoothAutoOnRepositoryProvider;
    public Provider bluetoothControllerImplProvider;
    public Provider bluetoothDeviceMetadataInteractorProvider;
    public Provider bluetoothLoggerProvider;
    public Provider bluetoothRepositoryImplProvider;
    public Provider bluetoothStateInteractorProvider;
    public Provider bluetoothTileDialogRepositoryProvider;
    public Provider bluetoothTileDialogViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider bluetoothTileProvider;
    public Provider blurUtilsProvider;
    public final Provider bootCompleteCacheImplProvider;
    public final Provider bouncerActionButtonInteractorProvider;
    public final Provider bouncerDialogFactoryProvider;
    public final Provider bouncerInteractorProvider;
    public Provider bouncerLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider bouncerLoggerStartableProvider;
    public Provider bouncerMessageAuditLoggerProvider;
    public final Provider bouncerMessageInteractorProvider;
    public final Provider bouncerMessageRepositoryImplProvider;
    public final Provider bouncerRepositoryProvider;
    public final Provider bouncerSceneProvider;
    public Provider bouncerViewBinderProvider;
    public final Provider bouncerViewImplProvider;
    public Provider bouncerlessScrimControllerProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider brightLineFalsingManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider brightnessDialogProvider;
    public Provider brightnessMirrorShowingInteractorProvider;
    public Provider brightnessMirrorShowingRepositoryProvider;
    public Provider brightnessPolicyEnforcementInteractorProvider;
    public Provider brightnessPolicyRepositoryImplProvider;
    public Provider brightnessSliderViewModelProvider;
    public Provider broadcastDialogControllerProvider;
    public final Provider broadcastDispatcherProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider broadcastDispatcherStartableProvider;
    public Provider broadcastFetcherProvider;
    public final Provider broadcastSenderProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider builderProvider;
    public Provider builderProvider2;
    public Provider burnInInteractorProvider;
    public Provider cHREGestureSensorDelegatorProvider;
    public Provider cHREGestureSensorProvider;
    public Provider cHREGestureSensorProvider2;
    public Provider callChipInteractorProvider;
    public Provider callChipViewModelProvider;
    public Provider callbackHandlerProvider;
    public Provider cameraActionProvider;
    public Provider cameraAutoRotateRepositoryImplProvider;
    public Provider cameraGestureHelperProvider;
    public Provider cameraLauncherProvider;
    public Provider cameraQuickAffordanceConfigProvider;
    public Provider cameraSensorPrivacyRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider cameraToggleTileProvider;
    public Provider cameraVisibilityProvider;
    public Provider cameraVisibilityProvider2;
    public Provider capabilitiesCallbackHandlerProvider;
    public Provider captioningInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider captioningRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider carrierConfigCoreStartableProvider;
    public final Provider carrierConfigRepositoryProvider;
    public Provider carrierConfigTrackerProvider;
    public Provider castAutoAddableProvider;
    public Provider castControllerImplProvider;
    public Provider castControllerLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider castTileProvider;
    public Provider castToOtherDeviceChipViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider catwalkKeyguardBlueprintProvider;
    public Provider centralSurfacesCommandQueueCallbacksProvider;
    public DelegateFactory centralSurfacesGoogleProvider;
    public Provider channelEditorDialogControllerProvider;
    public Provider chargingStateProvider;
    public Provider chargingStateProvider2;
    public Provider chipbarAnimatorProvider;
    public Provider chipbarCoordinatorProvider;
    public Provider chipbarLoggerProvider;
    public Provider clipboardListenerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider clipboardOverlayControllerProvider;
    public Provider clockSectionProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider collapsedStatusBarFragmentProvider;
    public Provider collapsedStatusBarFragmentStartableProvider;
    public Provider collapsedStatusBarInteractorProvider;
    public Provider collapsedStatusBarViewBinderImplProvider;
    public Provider collapsedStatusBarViewModelImplProvider;
    public Provider colorCorrectionRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider colorCorrectionTileProvider;
    public Provider colorInversionRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider colorInversionTileProvider;
    public Provider colorUpdateLoggerProvider;
    public Provider columbusManagerProvider;
    public Provider columbusServiceProvider;
    public Provider columbusServiceWrapperProvider;
    public Provider columbusSettingsFetcherProvider;
    public Provider columbusSettingsProvider;
    public Provider columbusStarterImplProvider;
    public Provider columbusStructuredDataManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider columbusTargetRequestServiceProvider;
    public final Provider commandRegistryProvider;
    public Provider communalAppWidgetHostStartableProvider;
    public Provider communalBackupRestoreStartableProvider;
    public Provider communalColorsImplProvider;
    public Provider communalDreamStartableProvider;
    public Provider communalEditModeViewModelProvider;
    public final Provider communalInteractorProvider;
    public Provider communalLoggerStartableProvider;
    public final Provider communalMediaRepositoryImplProvider;
    public Provider communalMetricsLoggerProvider;
    public Provider communalMetricsStartableProvider;
    public Provider communalOngoingContentStartableProvider;
    public final Provider communalPrefsInteractorProvider;
    public final Provider communalPrefsRepositoryImplProvider;
    public final Provider communalSceneInteractorProvider;
    public Provider communalSceneProvider;
    public final Provider communalSceneRepositoryImplProvider;
    public Provider communalSceneStartableProvider;
    public Provider communalSceneTransitionInteractorProvider;
    public Provider communalSceneTransitionRepositoryProvider;
    public final Provider communalSettingsInteractorProvider;
    public final Provider communalSettingsRepositoryImplProvider;
    public final Provider communalSmartspaceControllerProvider;
    public final Provider communalSmartspaceRepositoryImplProvider;
    public Provider communalStatsLogProxyImplProvider;
    public Provider communalTransitionViewModelProvider;
    public final Provider communalTutorialDisabledRepositoryImplProvider;
    public final Provider communalTutorialInteractorProvider;
    public Provider communalViewModelProvider;
    public final Provider communalWidgetRepositoryImplProvider;
    public Provider composeBouncerDependenciesProvider;
    public Provider configurationControllerStartableProvider;
    public final Provider configurationInteractorProvider;
    public final Provider configurationRepositoryImplProvider;
    public Provider connectedDisplayInteractorImplProvider;
    public Provider connectingDisplayViewModelProvider;
    public Provider connectivityConstantsProvider;
    public final Provider connectivityInputLoggerProvider;
    public final Provider connectivityRepositoryImplProvider;
    public final Provider connectivitySlotsProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider containerConfigProvider;
    public Provider contentFetcherProvider;
    public Provider contentResolverWrapperProvider;
    public DelegateFactory contextComponentResolverProvider;
    public Provider contextualEduUiCoordinatorProvider;
    public Provider contextualEduViewModelProvider;
    public Provider contextualEducationInteractorProvider;
    public Provider contextualTipsInteractorProvider;
    public Provider contextualTipsRepositoryProvider;
    public Provider controlActionCoordinatorImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider controlsActivityProvider;
    public Provider controlsBindingControllerImplProvider;
    public DelegateFactory controlsComponentProvider;
    public DelegateFactory controlsControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider controlsEditingActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider controlsFavoritingActivityProvider;
    public DelegateFactory controlsListingControllerImplProvider;
    public Provider controlsMetricsLoggerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider controlsProviderSelectorActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider controlsRequestDialogProvider;
    public Provider controlsSettingsDialogManagerImplProvider;
    public Provider controlsSettingsRepositoryImplProvider;
    public Provider controlsStartableProvider;
    public Provider controlsUiControllerImplProvider;
    public Provider conversationNotificationManagerProvider;
    public Provider coroutineScopeCoreStartableProvider;
    public final Provider countDownTimerUtilProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider createNoteTaskShortcutActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider createUserActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider credentialInteractorImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider credentialViewModelProvider;
    public DelegateFactory currentTilesInteractorImplProvider;
    public Provider customIconCacheProvider;
    public Provider customTileAddedSharedPrefsRepositoryProvider;
    public Provider customTileMapperProvider;
    public Provider darkIconDispatcherImplProvider;
    public Provider darkIconRepositoryImplProvider;
    public Provider dataSaverAutoAddableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider dataSaverTileProvider;
    public Provider debugModeFilterProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider defaultBlueprintProvider;
    public Provider defaultKeyguardBlueprintProvider;
    public Provider defaultLargeTilesRepositoryImplProvider;
    public Provider defaultTilesQSHostRepositoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider defaultUdfpsTouchOverlayViewModelProvider;
    public Provider defaultUiControllerProvider;
    public final Provider defaultWidgetPopulationProvider;
    public Provider demoDeviceBasedSatelliteDataSourceProvider;
    public Provider demoDeviceBasedSatelliteRepositoryProvider;
    public final Provider demoModeMobileConnectionDataSourceProvider;
    public final Provider demoModeWifiDataSourceProvider;
    public Provider demoRonChipViewModelProvider;
    public Provider dependencyProvider;
    public Provider deviceBasedSatelliteBindableIconProvider;
    public Provider deviceBasedSatelliteInteractorProvider;
    public Provider deviceBasedSatelliteRepositoryImplProvider;
    public Provider deviceBasedSatelliteRepositorySwitcherProvider;
    public Provider deviceBasedSatelliteViewModelImplProvider;
    public Provider deviceConfigInteractorProvider;
    public final Provider deviceConfigProxyProvider;
    public Provider deviceConfigRepositoryProvider;
    public Provider deviceControlsAutoAddableProvider;
    public Provider deviceControlsControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider deviceControlsTileProvider;
    public Provider deviceEntryBackgroundViewModelProvider;
    public Provider deviceEntryBiometricAuthInteractorProvider;
    public final Provider deviceEntryBiometricSettingsInteractorProvider;
    public final Provider deviceEntryBiometricsAllowedInteractorProvider;
    public final Provider deviceEntryFaceAuthRepositoryImplProvider;
    public final Provider deviceEntryFaceAuthStatusInteractorProvider;
    public final Provider deviceEntryFingerprintAuthInteractorProvider;
    public final Provider deviceEntryFingerprintAuthRepositoryImplProvider;
    public Provider deviceEntryForegroundViewModelProvider;
    public Provider deviceEntryHapticsInteractorProvider;
    public Provider deviceEntryIconLoggerProvider;
    public Provider deviceEntryIconViewModelProvider;
    public DelegateFactory deviceEntryInteractorProvider;
    public final DelegateFactory deviceEntryRepositoryImplProvider;
    public Provider deviceEntrySideFpsOverlayInteractorProvider;
    public Provider deviceEntrySourceInteractorProvider;
    public final Provider deviceEntryUdfpsInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider deviceEntryUdfpsTouchOverlayViewModelProvider;
    public Provider deviceEntryUnlockTrackerInteractorGoogleProvider;
    public Provider deviceEntryUnlockTrackerRepositoryGoogleProvider;
    public Provider deviceEntryUnlockTrackerViewBinderGoogleProvider;
    public Provider deviceItemActionInteractorProvider;
    public Provider deviceItemInteractorProvider;
    public final Provider devicePostureControllerImplProvider;
    public final Provider devicePostureInteractorProvider;
    public final Provider devicePostureRepositoryImplProvider;
    public final Provider deviceProvisionedControllerImplProvider;
    public final Provider deviceProvisioningInteractorProvider;
    public Provider deviceStateRotationLockSettingControllerProvider;
    public final Provider deviceUnlockedInteractorProvider;
    public final Provider disableFlagsLoggerProvider;
    public final Provider disableFlagsRepositoryImplProvider;
    public Provider disabledByPolicyInteractorImplProvider;
    public final Provider disabledWifiRepositoryProvider;
    public final Provider dismissCallbackRegistryProvider;
    public Provider dismissTimerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider displayContentRepositoryImplProvider;
    public Provider displayMetricsRepositoryProvider;
    public final Provider displayRepositoryImplProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider displayStateInteractorImplProvider;
    public final Provider displayStateRepositoryImplProvider;
    public Provider displaySwitchLatencyTrackerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider dndTileProvider;
    public Provider doNotDisturbQuickAffordanceConfigProvider;
    public Provider dockAlignmentControllerProvider;
    public final DelegateFactory dockObserverProvider;
    public Provider dozeInteractorProvider;
    public Provider dozeLogProvider;
    public final DelegateFactory dozeParametersProvider;
    public Provider dozeScrimControllerProvider;
    public Provider dozeServiceHostProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider dozeServiceProvider;
    public Provider dozeTransitionListenerProvider;
    public Provider dozingToGlanceableHubTransitionViewModelProvider;
    public Provider dozingToGoneTransitionViewModelProvider;
    public Provider dozingToLockscreenTransitionViewModelProvider;
    public Provider dozingToOccludedTransitionViewModelProvider;
    public Provider dozingToPrimaryBouncerTransitionViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider dreamMonitorProvider;
    public Provider dreamOverlayCallbackControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider dreamOverlayServiceProvider;
    public Provider dreamOverlayStateControllerProvider;
    public Provider dreamOverlayStatusBarItemsProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider dreamTileProvider;
    public Provider dreamViewModelProvider;
    public Provider dreamingHostedToLockscreenTransitionViewModelProvider;
    public Provider dreamingToAodTransitionViewModelProvider;
    public Provider dreamingToGlanceableHubTransitionViewModelProvider;
    public Provider dreamingToGoneTransitionViewModelProvider;
    public Provider dreamingToLockscreenTransitionViewModelProvider;
    public Provider dynamicPrivacyControllerProvider;
    public Provider editModeViewModelProvider;
    public Provider editTilesListInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider editWidgetsActivityProvider;
    public final Provider emergencyAffordanceManagerProvider;
    public final Provider emergencyServicesRepositoryProvider;
    public Provider emptyLockIconViewControllerProvider;
    public Provider endMediaProjectionDialogHelperProvider;
    public final Provider enhancedEstimatesGoogleImplProvider;
    public Provider ethernetInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider eventLogImplProvider;
    public Provider extensionControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider extraDimDialogDelegateProvider;
    public Provider extraDimDialogManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider extraDimDialogReceiverProvider;
    public Provider faceAuthAccessibilityDelegateProvider;
    public final Provider faceAuthenticationLoggerProvider;
    public final Provider faceHelpMessageDeferralFactoryProvider;
    public final Provider faceHelpMessageDeferralInteractorProvider;
    public final Provider facePropertyRepositoryImplProvider;
    public Provider faceScanningProviderFactoryProvider;
    public Provider faceSettingsRepositoryImplProvider;
    public final Provider faceWakeUpTriggersConfigImplProvider;
    public final Provider factoryProvider;
    public final Provider factoryProvider10;
    public final Provider factoryProvider11;
    public final Provider factoryProvider12;
    public final Provider factoryProvider13;
    public final Provider factoryProvider14;
    public Provider factoryProvider15;
    public Provider factoryProvider16;
    public Provider factoryProvider17;
    public Provider factoryProvider18;
    public Provider factoryProvider19;
    public final Provider factoryProvider2;
    public Provider factoryProvider20;
    public Provider factoryProvider21;
    public Provider factoryProvider22;
    public Provider factoryProvider23;
    public Provider factoryProvider24;
    public Provider factoryProvider25;
    public Provider factoryProvider26;
    public Provider factoryProvider27;
    public Provider factoryProvider28;
    public Provider factoryProvider29;
    public final Provider factoryProvider3;
    public Provider factoryProvider30;
    public Provider factoryProvider31;
    public Provider factoryProvider32;
    public Provider factoryProvider33;
    public Provider factoryProvider34;
    public Provider factoryProvider35;
    public Provider factoryProvider36;
    public Provider factoryProvider37;
    public Provider factoryProvider38;
    public Provider factoryProvider39;
    public final Provider factoryProvider4;
    public Provider factoryProvider40;
    public Provider factoryProvider41;
    public Provider factoryProvider42;
    public Provider factoryProvider43;
    public Provider factoryProvider44;
    public Provider factoryProvider45;
    public Provider factoryProvider46;
    public Provider factoryProvider47;
    public Provider factoryProvider48;
    public Provider factoryProvider49;
    public final Provider factoryProvider5;
    public Provider factoryProvider50;
    public Provider factoryProvider51;
    public Provider factoryProvider52;
    public Provider factoryProvider53;
    public Provider factoryProvider54;
    public Provider factoryProvider55;
    public Provider factoryProvider56;
    public Provider factoryProvider57;
    public Provider factoryProvider58;
    public Provider factoryProvider59;
    public final DelegateFactory factoryProvider6;
    public Provider factoryProvider60;
    public Provider factoryProvider61;
    public Provider factoryProvider62;
    public Provider factoryProvider63;
    public Provider factoryProvider64;
    public Provider factoryProvider65;
    public Provider factoryProvider66;
    public Provider factoryProvider67;
    public Provider factoryProvider68;
    public Provider factoryProvider69;
    public final Provider factoryProvider7;
    public Provider factoryProvider70;
    public Provider factoryProvider71;
    public Provider factoryProvider72;
    public Provider factoryProvider73;
    public Provider factoryProvider74;
    public Provider factoryProvider75;
    public Provider factoryProvider76;
    public Provider factoryProvider77;
    public Provider factoryProvider78;
    public Provider factoryProvider79;
    public final Provider factoryProvider8;
    public Provider factoryProvider80;
    public Provider factoryProvider81;
    public Provider factoryProvider82;
    public Provider factoryProvider83;
    public final Provider factoryProvider9;
    public final DelegateFactory falsingCollectorImplProvider;
    public final Provider falsingCollectorNoOpProvider;
    public Provider falsingCoreStartableProvider;
    public final DelegateFactory falsingDataProvider;
    public final Provider falsingInteractorProvider;
    public final Provider falsingManagerProxyProvider;
    public final DelegateFactory featureFlagsClassicReleaseProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider featureFlagsReleaseStartableProvider;
    public Provider fgsManagerControllerImplProvider;
    public final Provider filesProvider;
    public final Provider fingerprintPropertyInteractorProvider;
    public final Provider fingerprintPropertyRepositoryImplProvider;
    public Provider fixedColumnsRepositoryProvider;
    public Provider fixedColumnsSizeInteractorProvider;
    public Provider fixedColumnsSizeViewModelImplProvider;
    public Provider flagDependenciesNotifierProvider;
    public Provider flagDependenciesProvider;
    public Provider flagEnabledProvider;
    public Provider flashlightControllerImplProvider;
    public Provider flashlightQuickAffordanceConfigProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider flashlightTileProvider;
    public Provider flingVelocityWrapperProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider fontScalingDialogDelegateProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider fontScalingTileProvider;
    public Provider footerActionsControllerProvider;
    public Provider footerActionsInteractorImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider foregroundServicesDialogProvider;
    public Provider foregroundServicesRepositoryImplProvider;
    public DelegateFactory fragmentServiceProvider;
    public Provider fromAlternateBouncerTransitionInteractorProvider;
    public Provider fromAodTransitionInteractorProvider;
    public Provider fromDozingTransitionInteractorProvider;
    public Provider fromDreamingLockscreenHostedTransitionInteractorProvider;
    public Provider fromDreamingTransitionInteractorProvider;
    public Provider fromGlanceableHubTransitionInteractorProvider;
    public Provider fromGoneTransitionInteractorProvider;
    public Provider fromLockscreenTransitionInteractorProvider;
    public Provider fromOccludedTransitionInteractorProvider;
    public Provider fromPrimaryBouncerTransitionInteractorProvider;
    public Provider gateFetcherProvider;
    public Provider gestureConfigurationProvider;
    public Provider gestureConfigurationProvider2;
    public Provider gestureControllerProvider;
    public Provider gestureControllerProvider2;
    public Provider gesturePointerEventDetectorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider gesturePointerEventListenerProvider;
    public Provider gestureRepositoryImplProvider;
    public Provider gestureSensorImplProvider;
    public Provider getClockRegistryProvider;
    public Provider glanceableHubContainerControllerProvider;
    public Provider glanceableHubToDreamingTransitionViewModelProvider;
    public Provider glanceableHubToLockscreenTransitionViewModelProvider;
    public Provider glanceableHubToOccludedTransitionViewModelProvider;
    public Provider globalActionsComponentProvider;
    public DelegateFactory globalActionsDialogLiteProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider globalActionsImplProvider;
    public Provider globalActionsInteractorProvider;
    public Provider globalActionsRepositoryProvider;
    public Provider goBackHandlerProvider;
    public Provider goneSceneProvider;
    public Provider goneToAodTransitionViewModelProvider;
    public Provider goneToDozingTransitionViewModelProvider;
    public Provider goneToDreamingLockscreenHostedTransitionViewModelProvider;
    public Provider goneToDreamingTransitionViewModelProvider;
    public Provider goneToGlanceableHubTransitionViewModelProvider;
    public Provider goneToLockscreenTransitionViewModelProvider;
    public Provider googleAssistLoggerProvider;
    public Provider googleControlsTileResourceConfigurationImplProvider;
    public final GoogleDefaultBlueprintModule googleDefaultBlueprintModule;
    public Provider googleDefaultUiControllerProvider;
    public Provider googleServicesProvider;
    public Provider gridLayoutSelectorViewModelProvider;
    public Provider gridLayoutTypeInteractorProvider;
    public Provider gridLayoutTypeRepositoryImplProvider;
    public Provider groupExpansionManagerImplProvider;
    public Provider groupMembershipManagerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider guestResetOrExitSessionReceiverProvider;
    public final Provider guestResumeSessionReceiverProvider;
    public final Provider guestUserInteractorProvider;
    public Provider hapticClickProvider;
    public Provider headlessSystemUserModeImplProvider;
    public Provider headsUpManagerPhoneProvider;
    public Provider headsUpNotificationIconViewStateRepositoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider headsUpStyleProviderImplProvider;
    public Provider headsUpViewBinderProvider;
    public Provider healthManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider healthServiceProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider healthUpdateReceiverProvider;
    public Provider hearingDevicesCheckerProvider;
    public Provider hearingDevicesDialogManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider hearingDevicesDialogReceiverProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider hearingDevicesTileProvider;
    public Provider hearingDevicesUiEventLoggerProvider;
    public Provider hideListViewModelProvider;
    public Provider hideNotificationsInteractorProvider;
    public Provider highPriorityProvider;
    public final Provider historyTrackerProvider;
    public Provider homeControlsComponentInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider homeControlsDreamServiceProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider homeControlsDreamStartableProvider;
    public Provider homeControlsKeyguardQuickAffordanceConfigProvider;
    public Provider homeSceneFamilyResolverProvider;
    public Provider hotspotAutoAddableProvider;
    public Provider hotspotControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider hotspotTileProvider;
    public Provider iconAndNameCustomRepositoryProvider;
    public Provider iconLabelVisibilityInteractorProvider;
    public Provider iconLabelVisibilityViewModelImplProvider;
    public Provider iconManagerProvider;
    public Provider iconTilesInteractorProvider;
    public Provider iconTilesViewModelImplProvider;
    public Provider imageCaptureImplProvider;
    public final Provider imageLoaderProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider imageWallpaperProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider immersiveModeConfirmationProvider;
    public Provider inWindowLauncherAnimationViewModelProvider;
    public Provider inWindowLauncherUnlockAnimationInteractorProvider;
    public Provider inWindowLauncherUnlockAnimationManagerProvider;
    public Provider inWindowLauncherUnlockAnimationRepositoryProvider;
    public final Provider indicationHelperProvider;
    public Provider infiniteGridLayoutProvider;
    public Provider initControllerProvider;
    public Provider inputDeviceRepositoryProvider;
    public final Provider inputMethodInteractorProvider;
    public final Provider inputMethodRepositoryImplProvider;
    public Provider installedTilesComponentRepositoryImplProvider;
    public Provider instantAppNotifierProvider;
    public Provider internalKeyguardTransitionInteractorProvider;
    public DelegateFactory internetDialogManagerProvider;
    public Provider internetTileViewModelProvider;
    public Provider isPMLiteEnabledProvider;
    public Provider isReduceBrightColorsAvailableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider issueRecordingServiceProvider;
    public Provider issueRecordingStateProvider;
    public Provider jNIGestureSensorProvider;
    public final Provider javaAdapterProvider;
    public Provider keyEventInteractorProvider;
    public Provider keyEventRepositoryImplProvider;
    public Provider keyboardBacklightDialogCoordinatorProvider;
    public Provider keyboardBacklightInteractorProvider;
    public Provider keyboardDockingIndicationInteractorProvider;
    public Provider keyboardDockingIndicationViewBinderProvider;
    public Provider keyboardDockingIndicationViewModelProvider;
    public Provider keyboardRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider keyboardShortcutsReceiverProvider;
    public Provider keyboardTouchpadConnectionInteractorProvider;
    public Provider keyboardTouchpadEduInteractorProvider;
    public Provider keyboardTouchpadEduStatsInteractorImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider keyboardTouchpadTutorialActivityProvider;
    public Provider keyboardTouchpadTutorialCoreStartableProvider;
    public Provider keyboardUIProvider;
    public Provider keyguardBiometricLockoutLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider keyguardBlueprintCommandListenerProvider;
    public DelegateFactory keyguardBlueprintInteractorProvider;
    public Provider keyguardBlueprintRepositoryProvider;
    public Provider keyguardBottomAreaInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider keyguardBottomAreaViewControllerProvider;
    public final Provider keyguardBouncerRepositoryImplProvider;
    public final DelegateFactory keyguardBypassControllerProvider;
    public Provider keyguardClockInteractorProvider;
    public Provider keyguardClockRepositoryImplProvider;
    public Provider keyguardClockViewModelProvider;
    public Provider keyguardDismissActionBinderProvider;
    public Provider keyguardDismissActionInteractorProvider;
    public Provider keyguardDismissBinderProvider;
    public Provider keyguardDismissInteractorProvider;
    public Provider keyguardDismissTransitionInteractorProvider;
    public Provider keyguardDismissUtilProvider;
    public Provider keyguardDisplayManagerProvider;
    public Provider keyguardEnabledInteractorProvider;
    public final Provider keyguardIndicationControllerGoogleProvider;
    public final DelegateFactory keyguardInteractorProvider;
    public Provider keyguardKeyEventInteractorProvider;
    public Provider keyguardKeyboardInteractorProvider;
    public Provider keyguardLifecyclesDispatcherProvider;
    public Provider keyguardMediaControllerProvider;
    public Provider keyguardNotificationVisibilityProviderImplProvider;
    public Provider keyguardOcclusionInteractorProvider;
    public Provider keyguardOcclusionRepositoryProvider;
    public Provider keyguardPreviewRendererFactoryProvider;
    public Provider keyguardProximityProvider;
    public Provider keyguardQuickAffordanceInteractorProvider;
    public Provider keyguardQuickAffordanceLegacySettingSyncerProvider;
    public Provider keyguardQuickAffordanceLocalUserSelectionManagerProvider;
    public Provider keyguardQuickAffordanceRemoteUserSelectionManagerProvider;
    public DelegateFactory keyguardQuickAffordanceRepositoryProvider;
    public Provider keyguardQuickAffordanceViewBinderProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider keyguardQuickAffordancesCombinedViewModelProvider;
    public Provider keyguardRemotePreviewManagerProvider;
    public final DelegateFactory keyguardRepositoryImplProvider;
    public Provider keyguardRootViewModelProvider;
    public final Provider keyguardSecurityModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider keyguardServiceProvider;
    public Provider keyguardSmartspaceInteractorProvider;
    public Provider keyguardSmartspaceRepositoryImplProvider;
    public Provider keyguardSmartspaceStartableProvider;
    public Provider keyguardSmartspaceViewModelProvider;
    public Provider keyguardStateCallbackInteractorProvider;
    public Provider keyguardStateCallbackStartableProvider;
    public final DelegateFactory keyguardStateControllerImplProvider;
    public Provider keyguardStatusBarInteractorProvider;
    public Provider keyguardStatusBarRepositoryImplProvider;
    public Provider keyguardStatusBarViewModelProvider;
    public Provider keyguardSurfaceBehindInteractorProvider;
    public Provider keyguardSurfaceBehindParamsApplierProvider;
    public Provider keyguardSurfaceBehindRepositoryImplProvider;
    public Provider keyguardSurfaceBehindViewModelProvider;
    public Provider keyguardTouchHandlingInteractorProvider;
    public Provider keyguardTouchHandlingViewModelProvider;
    public Provider keyguardTransitionAnimationFlowProvider;
    public Provider keyguardTransitionAuditLoggerProvider;
    public Provider keyguardTransitionBootInteractorProvider;
    public Provider keyguardTransitionCoreStartableProvider;
    public final Provider keyguardTransitionInteractorProvider;
    public final Provider keyguardTransitionRepositoryImplProvider;
    public Provider keyguardUnlockAnimationControllerProvider;
    public final DelegateFactory keyguardUpdateMonitorProvider;
    public DelegateFactory keyguardViewConfiguratorProvider;
    public Provider keyguardVisibilityProvider;
    public Provider keyguardWakeDirectlyToGoneInteractorProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider largeScreenHeaderHelperProvider;
    public Provider largeScreenShadeInterpolatorImplProvider;
    public Provider latencyTesterProvider;
    public Provider launchAppProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider launchConversationActivityProvider;
    public Provider launchFullScreenIntentProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider launchNoteTaskActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider launchNotesRoleSettingsTrampolineActivityProvider;
    public Provider launchOpaProvider;
    public Provider launchOpaProvider2;
    public Provider launchOverviewProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider launcherTileServiceProvider;
    public final LeakModule leakModule;
    public Provider legacyActivityStarterInternalImplProvider;
    public Provider legacyBouncerDependenciesProvider;
    public DelegateFactory legacyLockIconViewControllerProvider;
    public final Provider legacyMediaDataManagerImplProvider;
    public Provider letterboxAppearanceCalculatorProvider;
    public Provider letterboxBackgroundProvider;
    public Provider liftToRunFaceAuthBinderProvider;
    public Provider lightBarControllerProvider;
    public Provider lightRevealScrimInteractorProvider;
    public Provider lightRevealScrimRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider lightRevealScrimViewModelProvider;
    public Provider lightnessProvider;
    public Provider lightsOutInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider limitedEdgeToEdgeProvider;
    public Provider localMediaRepositoryFactoryImplProvider;
    public Provider locationControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider locationTileProvider;
    public Provider lockscreenGestureLoggerProvider;
    public Provider lockscreenHostedDreamGestureListenerProvider;
    public Provider lockscreenSceneProvider;
    public Provider lockscreenSceneTransitionInteractorProvider;
    public Provider lockscreenSceneTransitionRepositoryProvider;
    public Provider lockscreenShadeTransitionControllerProvider;
    public Provider lockscreenSmartspaceControllerProvider;
    public Provider lockscreenToAodTransitionViewModelProvider;
    public Provider lockscreenToDozingTransitionViewModelProvider;
    public Provider lockscreenToDreamingHostedTransitionViewModelProvider;
    public Provider lockscreenToDreamingTransitionViewModelProvider;
    public Provider lockscreenToGlanceableHubTransitionViewModelProvider;
    public Provider lockscreenToGoneTransitionViewModelProvider;
    public Provider lockscreenToOccludedTransitionViewModelProvider;
    public Provider lockscreenToPrimaryBouncerTransitionViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider lockscreenWallpaperDreamServiceProvider;
    public final Provider logBufferEulogizerProvider;
    public final Provider logBufferFactoryProvider;
    public Provider logContextInteractorImplProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider logcatEchoTrackerDebugProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider longScreenshotActivityProvider;
    public Provider longScreenshotDataProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider lowLightClockDreamServiceProvider;
    public Provider lowSensitivitySettingAdjustmentProvider;
    public Provider magnificationImplProvider;
    public Provider manageMediaProvider;
    public final Provider managedProfileControllerImplProvider;
    public Provider mediaCarouselControllerLoggerProvider;
    public DelegateFactory mediaCarouselControllerProvider;
    public final Provider mediaCarouselInteractorProvider;
    public Provider mediaCarouselViewModelProvider;
    public Provider mediaContainerControllerProvider;
    public Provider mediaControlInteractorFactoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mediaControlPanelProvider;
    public Provider mediaControllerInteractorImplProvider;
    public final Provider mediaDataFilterImplProvider;
    public final Provider mediaDataLoaderProvider;
    public final Provider mediaDataProcessorProvider;
    public final Provider mediaDataRepositoryProvider;
    public final Provider mediaFilterRepositoryProvider;
    public final Provider mediaFlagsProvider;
    public Provider mediaHierarchyManagerProvider;
    public final Provider mediaHostStatesManagerProvider;
    public final Provider mediaLoggerProvider;
    public Provider mediaMuteAwaitConnectionCliProvider;
    public final Provider mediaMuteAwaitConnectionManagerFactoryProvider;
    public final Provider mediaMuteAwaitLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mediaOutputDialogReceiverProvider;
    public Provider mediaOutputSwitcherDialogUIProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mediaProjectionAppSelectorActivityProvider;
    public Provider mediaProjectionChipInteractorProvider;
    public final MediaProjectionDevicePolicyModule mediaProjectionDevicePolicyModule;
    public Provider mediaProjectionManagerRepositoryProvider;
    public Provider mediaProjectionMetricsLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mediaProjectionPermissionActivityProvider;
    public Provider mediaProjectionTaskSwitcherCoreStartableProvider;
    public Provider mediaRecommendationsInteractorProvider;
    public Provider mediaRecommendationsViewModelProvider;
    public final Provider mediaResumeListenerProvider;
    public Provider mediaRouterChipInteractorProvider;
    public Provider mediaRouterRepositoryImplProvider;
    public Provider mediaSessionLegacyHelperWrapperProvider;
    public final Provider mediaSmartspaceLoggerProvider;
    public final Provider mediaTimeoutListenerProvider;
    public final Provider mediaTimeoutLoggerProvider;
    public Provider mediaTttChipControllerReceiverProvider;
    public Provider mediaTttCommandLineHelperProvider;
    public Provider mediaTttFlagsProvider;
    public Provider mediaTttReceiverLoggerProvider;
    public Provider mediaTttReceiverUiEventLoggerProvider;
    public Provider mediaTttSenderCoordinatorProvider;
    public Provider mediaTttSenderLoggerProvider;
    public Provider mediaTttSenderUiEventLoggerProvider;
    public final Provider mediaUiEventLoggerProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider mediaViewControllerProvider;
    public final Provider mediaViewLoggerProvider;
    public Provider messagesProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider microphoneToggleTileProvider;
    public Provider minimumTilesResourceRepositoryProvider;
    public final Provider mobileConnectionsRepositoryImplProvider;
    public Provider mobileContextProvider;
    public Provider mobileIconsInteractorImplProvider;
    public Provider mobileIconsViewModelProvider;
    public final Provider mobileInputLoggerProvider;
    public final Provider mobileRepositorySwitcherProvider;
    public Provider mobileSignalControllerFactoryProvider;
    public Provider mobileUiAdapterProvider;
    public Provider mobileViewLoggerProvider;
    public Provider modeSwitchesControllerProvider;
    public DelegateFactory modesDialogDelegateProvider;
    public Provider modesDialogViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider modesTileProvider;
    public Provider modesTileUserActionInteractorProvider;
    public Provider motionToolStartableProvider;
    public Provider muteQuickAffordanceConfigProvider;
    public Provider muteQuickAffordanceCoreStartableProvider;
    public Provider naturalScrollingSettingObserverProvider;
    public Provider navBarFadeControllerProvider;
    public Provider navBarFaderProvider;
    public Provider navBarHelperProvider;
    public DelegateFactory navigationBarControllerImplProvider;
    public Provider navigationInteractorProvider;
    public final Provider navigationModeControllerProvider;
    public Provider navigationRepositoryProvider;
    public final Provider nearbyMediaDevicesLoggerProvider;
    public final Provider nearbyMediaDevicesManagerProvider;
    public Provider networkControllerImplProvider;
    public DelegateFactory newKeyguardViewMediatorProvider;
    public Provider newQSTileFactoryProvider;
    public Provider newTilesAvailabilityInteractorProvider;
    public Provider nextAlarmControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider nfcTileProvider;
    public Provider ngaMessageHandlerProvider;
    public Provider ngaUiControllerProvider;
    public Provider nightDisplayAutoAddableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider nightDisplayTileProvider;
    public Provider noOpGestureSensorProvider;
    public final Provider noopDeviceEntryFaceAuthInteractorProvider;
    public Provider noteTaskBubblesControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider noteTaskBubblesServiceProvider;
    public Provider noteTaskControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider noteTaskControllerUpdateServiceProvider;
    public Provider notifBindPipelineProvider;
    public final Provider notifCollectionProvider;
    public Provider notifCoordinatorsProvider;
    public Provider notifInflaterImplProvider;
    public Provider notifInflationErrorManagerProvider;
    public final Provider notifLiveDataStoreImplProvider;
    public final Provider notifPipelineChoreographerImplProvider;
    public Provider notifPipelineInitializerProvider;
    public final Provider notifPipelineProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notifRemoteViewCacheImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notifRemoteViewsFactoryContainerImplProvider;
    public Provider notifUiAdjustmentProvider;
    public Provider notifViewBarnProvider;
    public Provider notificationAlertsInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notificationChannelsProvider;
    public final Provider notificationClickNotifierProvider;
    public Provider notificationContentInflaterProvider;
    public final Provider notificationDismissibilityProviderImplProvider;
    public Provider notificationGutsManagerProvider;
    public Provider notificationIconContainerAlwaysOnDisplayViewModelProvider;
    public Provider notificationInsetsImplProvider;
    public final Provider notificationInteractionTrackerProvider;
    public Provider notificationInterruptStateProviderImplProvider;
    public Provider notificationLaunchAnimationInteractorProvider;
    public Provider notificationLaunchAnimationRepositoryProvider;
    public Provider notificationListenerProvider;
    public Provider notificationListenerSettingsRepositoryProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notificationListenerWithPluginsProvider;
    public final DelegateFactory notificationLockscreenUserManagerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notificationLoggerViewModelProvider;
    public Provider notificationMemoryDumperProvider;
    public Provider notificationMemoryLoggerProvider;
    public Provider notificationMemoryMonitorProvider;
    public DelegateFactory notificationPanelViewControllerProvider;
    public Provider notificationPersonExtractorPluginBoundaryProvider;
    public Provider notificationPlaceholderRepositoryProvider;
    public Provider notificationRemoteInputManagerProvider;
    public Provider notificationRoundnessManagerProvider;
    public Provider notificationRowBinderImplProvider;
    public Provider notificationRowContentBinderImplProvider;
    public Provider notificationScrollViewBinderProvider;
    public Provider notificationSectionProvider;
    public Provider notificationSectionsFeatureManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notificationSectionsManagerProvider;
    public Provider notificationSettingsControllerProvider;
    public Provider notificationShadeDepthControllerProvider;
    public final DelegateFactory notificationShadeWindowControllerImplProvider;
    public Provider notificationShadeWindowModelProvider;
    public Provider notificationShadeWindowViewControllerProvider;
    public Provider notificationShelfInteractorProvider;
    public Provider notificationShelfViewModelProvider;
    public Provider notificationStackAppearanceInteractorProvider;
    public Provider notificationStackInteractorProvider;
    public DelegateFactory notificationStackScrollLayoutControllerProvider;
    public Provider notificationStackSizeCalculatorProvider;
    public Provider notificationStatsLoggerImplProvider;
    public Provider notificationTargetsHelperProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notificationViewFlipperFactoryProvider;
    public Provider notificationViewFlipperViewModelProvider;
    public Provider notificationViewHeightRepositoryProvider;
    public final Provider notificationVisibilityProviderImplProvider;
    public Provider notificationWakeUpCoordinatorProvider;
    public Provider notificationsControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider notificationsControllerStubProvider;
    public Provider notificationsKeyguardViewStateRepositoryProvider;
    public Provider notificationsQSContainerControllerProvider;
    public Provider notificationsShadeOverlayProvider;
    public Provider occludedToAlternateBouncerTransitionViewModelProvider;
    public Provider occludedToAodTransitionViewModelProvider;
    public Provider occludedToDozingTransitionViewModelProvider;
    public Provider occludedToGlanceableHubTransitionViewModelProvider;
    public Provider occludedToGoneTransitionViewModelProvider;
    public Provider occludedToLockscreenTransitionViewModelProvider;
    public Provider occludingAppDeviceEntryInteractorProvider;
    public Provider occludingAppDeviceEntryMessageViewModelProvider;
    public Provider offToLockscreenTransitionViewModelProvider;
    public Provider onUserInteractionCallbackImplProvider;
    public Provider oneHandedModeRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider oneHandedModeTileProvider;
    public Provider ongoingActivityChipsViewModelProvider;
    public Provider ongoingCallControllerProvider;
    public Provider ongoingCallRepositoryProvider;
    public Provider opaEnabledReceiverProvider;
    public Provider openNotificationShadeProvider;
    public DelegateFactory optionalOfCentralSurfacesProvider;
    public Provider overlappedElementControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider overlayToggleTileProvider;
    public Provider overlayUiHostProvider;
    public Provider overviewProxyRecentsImplProvider;
    public final DelegateFactory overviewProxyServiceProvider;
    public Provider packageChangeInteractorProvider;
    public final Provider packageChangeRepositoryImplProvider;
    public final Provider packageInstallerMonitorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider packageLabelIconProviderImplProvider;
    public final Provider packageUpdateLoggerProvider;
    public Provider paginatedGridInteractorProvider;
    public Provider paginatedGridRepositoryProvider;
    public Provider paginatedGridViewModelProvider;
    public Provider panelExpansionInteractorImplProvider;
    public Provider panelInteractorImplProvider;
    public Provider peopleNotificationIdentifierImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider peopleSpaceActivityProvider;
    public Provider peopleSpaceWidgetManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider peopleSpaceWidgetPinnedReceiverProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider peopleSpaceWidgetProvider;
    public Provider peopleTileRepositoryImplProvider;
    public Provider peopleWidgetRepositoryImplProvider;
    public Provider phoneStateMonitorProvider;
    public Provider physicalKeyboardCoreStartableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider postureDependentProximitySensorProvider;
    public final DelegateFactory powerInteractorProvider;
    public Provider powerManagerWrapperProvider;
    public Provider powerNotificationWarningsGoogleImplProvider;
    public final Provider powerRepositoryImplProvider;
    public Provider powerSaveStateProvider;
    public Provider powerStateProvider;
    public Provider powerUIProvider;
    public final Provider primaryBouncerCallbackInteractorProvider;
    public final Provider primaryBouncerInteractorProvider;
    public Provider primaryBouncerToAodTransitionViewModelProvider;
    public Provider primaryBouncerToDozingTransitionViewModelProvider;
    public Provider primaryBouncerToGlanceableHubTransitionViewModelProvider;
    public Provider primaryBouncerToGoneTransitionViewModelProvider;
    public Provider primaryBouncerToLockscreenTransitionViewModelProvider;
    public Provider privacyChipInteractorProvider;
    public Provider privacyChipRepositoryImplProvider;
    public Provider privacyConfigProvider;
    public Provider privacyDialogControllerProvider;
    public Provider privacyDialogControllerV2Provider;
    public Provider privacyDotDecorProviderFactoryProvider;
    public Provider privacyDotViewControllerProvider;
    public Provider privacyItemControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider profileFirstRunFileResourcesImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider profileFirstRunSettingsImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider profileTypeRepositoryImplProvider;
    public Provider promptRepositoryImplProvider;
    public Provider promptSelectorInteractorImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider promptViewModelProvider;
    public Provider provideAccessPointControllerImplProvider;
    public Provider provideActivityManagerWrapperProvider;
    public Provider provideActivityTransitionAnimatorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideAirplaneModeTileViewModelProvider;
    public final Provider provideAirplaneTableLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideAlarmTileViewModelProvider;
    public Provider provideAllowNotificationLongPressProvider;
    public Provider provideAncSliceRepositoryProvider;
    public final Provider provideAnimationFeatureFlagsProvider;
    public Provider provideAssistUtilsProvider;
    public Provider provideAudioManagerIntentsReceiverProvider;
    public Provider provideAudioModeInteractorProvider;
    public Provider provideAudioRepositoryProvider;
    public Provider provideAudioSharingInteractorProvider;
    public Provider provideAudioSharingRepositoryProvider;
    public Provider provideAudioVolumeInteractorProvider;
    public Provider provideAutoRotateSettingsManagerProvider;
    public Provider provideBackPanelUiThreadContextProvider;
    public final Provider provideBackgroundDelayableExecutorProvider;
    public final Provider provideBackgroundExecutorProvider;
    public final Provider provideBackgroundRepeatableExecutorProvider;
    public final Provider provideBackupManagerProvider;
    public final Provider provideBaseShadeInteractorProvider;
    public final Provider provideBatteryControllerLogProvider;
    public final Provider provideBatteryControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideBatterySaverTileViewModelProvider;
    public Provider provideBcSmartspaceConfigPluginProvider;
    public Provider provideBcSmartspaceDataPluginProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideBgHandlerProvider;
    public final Provider provideBgLooperProvider;
    public final Provider provideBiometricLogBufferProvider;
    public final Provider provideBouncerLogBufferProvider;
    public Provider provideBouncerLogProvider;
    public final Provider provideBroadcastDispatcherLogBufferProvider;
    public final Provider provideBroadcastRunningExecutorProvider;
    public final Provider provideBroadcastRunningLooperProvider;
    public Provider provideBubblesManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideCameraToggleTileViewModelProvider;
    public Provider provideCarrierTextManagerLogProvider;
    public Provider provideCastControllerLogProvider;
    public Provider provideChipbarLogBufferProvider;
    public Provider provideChipsLogBufferProvider;
    public Provider provideCollapsedSbFragmentLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideColorCorrectionTileViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideColorInversionTileViewModelProvider;
    public Provider provideColumbusActionsProvider;
    public Provider provideColumbusStarterProvider;
    public final Provider provideCommandQueueProvider;
    public final Provider provideCommunalAppWidgetHostProvider;
    public final Provider provideCommunalDatabaseProvider;
    public final Provider provideCommunalLogBufferProvider;
    public final Provider provideCommunalTableLogBufferProvider;
    public Provider provideCommunalTouchLogBufferProvider;
    public final DelegateFactory provideCommunalWidgetDaoProvider;
    public final Provider provideCommunalWidgetHostProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideContextualEduUiCoordinatorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideContextualEducationInteractorProvider;
    public Provider provideCoreStartableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideCoreStartableProvider2;
    public Provider provideDataSaverControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideDataSaverTileViewModelProvider;
    public Provider provideDateSmartspaceDataPluginProvider;
    public final Provider provideDemoModeControllerProvider;
    public Provider provideDeviceBasedSatelliteInputLogProvider;
    public Provider provideDeviceBasedSatelliteTableLogProvider;
    public Provider provideDeviceEntryIconLogBufferProvider;
    public Provider provideDevicePolicyManagerWrapperProvider;
    public final Provider provideDeviceProvisionedControllerProvider;
    public Provider provideDeviceStateAutoRotationLogBufferProvider;
    public final Provider provideDialogTransitionAnimatorProvider;
    public final Provider provideDisableFlagsRepositoryLogBufferProvider;
    public Provider provideDisplayMetricsRepoLogBufferProvider;
    public final Provider provideDisplayTrackerProvider;
    public final Provider provideDozeLogBufferProvider;
    public Provider provideDreamLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideEduDataStoreScopeProvider;
    public final Provider provideFaceAuthLogProvider;
    public final Provider provideFaceAuthTableLogProvider;
    public final Provider provideFaceDetectTableLogProvider;
    public Provider provideFingerprintInteractiveToAuthProvider;
    public Provider provideFingerprintReEnrollNotificationProvider;
    public Provider provideFirstMobileSubShowingNetworkTypeIconProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideFlashlightTileViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideFontScalingTileViewModelProvider;
    public Provider provideFullscreenActionsProvider;
    public Provider provideGestureAdjustmentsProvider;
    public Provider provideGestureSensorProvider;
    public Provider provideGestureSensorProvider2;
    public final Provider provideGlanceableHubBcSmartspaceDataPluginProvider;
    public final Provider provideGlobalConfigurationControllerProvider;
    public Provider provideGlobalConfigurationStateProvider;
    public Provider provideHalDataSourceProvider;
    public Provider provideHeadsUpStyleManagerProvider;
    public Provider provideHealthManagerProvider;
    public final Provider provideIThermalServiceProvider;
    public Provider provideIndividualSensorPrivacyControllerProvider;
    public Provider provideInputDeviceTutorialLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideInputMonitorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideInternetTileViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideIssueRecordingTileViewModelProvider;
    public Provider provideKeyboardLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideKeyboardTouchpadEduInteractorProvider;
    public Provider provideKeyguardClockLogProvider;
    public Provider provideKeyguardLargeClockLogProvider;
    public final Provider provideKeyguardLogBufferProvider;
    public Provider provideKeyguardMediaControllerLogBufferProvider;
    public Provider provideKeyguardQuickAffordancesCombinedViewModelProvider;
    public Provider provideKeyguardQuickAffordancesLogBufferProvider;
    public Provider provideKeyguardSmallClockLogProvider;
    public Provider provideKeyguardTransitionAnimationLogBufferProvider;
    public final Provider provideKeyguardUpdateMonitorLogBufferProvider;
    public Provider provideLSShadeTransitionControllerBufferProvider;
    public Provider provideLegacyLoggerOptionalProvider;
    public Provider provideListContainerProvider;
    public final Provider provideLocalBluetoothControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideLocationTileViewModelProvider;
    public DelegateFactory provideLockIconViewControllerProvider;
    public final Provider provideLogcatEchoTrackerProvider;
    public Provider provideLongRunningDelayableExecutorProvider;
    public Provider provideLongRunningExecutorProvider;
    public Provider provideLongRunningLooperProvider;
    public final Provider provideMSDLPlayerProvider;
    public final Provider provideMediaBrowserBufferProvider;
    public Provider provideMediaCarouselControllerBufferProvider;
    public Provider provideMediaDeviceSessionRepositoryProvider;
    public final Provider provideMediaMuteAwaitLogBufferProvider;
    public Provider provideMediaProjectionLogBufferProvider;
    public Provider provideMediaRouterLogBufferProvider;
    public Provider provideMediaTttReceiverLogBufferProvider;
    public Provider provideMediaTttSenderLogBufferProvider;
    public final Provider provideMediaViewLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideMicrophoneToggleTileViewModelProvider;
    public final Provider provideMobileInputLogBufferProvider;
    public final Provider provideMobileSummaryLogBufferProvider;
    public Provider provideMobileViewLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideModesTileViewModelProvider;
    public Provider provideMonitorTableLogBufferProvider;
    public Provider provideNavBarButtonClickLogBufferProvider;
    public Provider provideNavbarOrientationTrackingLogBufferProvider;
    public final Provider provideNearbyMediaDevicesLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideNightDisplayTileViewModelProvider;
    public Provider provideNotifInflationExecutorProvider;
    public Provider provideNotifInflationLogBufferProvider;
    public Provider provideNotifInflationLooperProvider;
    public Provider provideNotifInteractionLogBufferProvider;
    public Provider provideNotifRemoteViewCacheProvider;
    public Provider provideNotifRemoteViewsFactoryContainerProvider;
    public Provider provideNotificationHeadsUpLogBufferProvider;
    public Provider provideNotificationInterruptLogBufferProvider;
    public Provider provideNotificationLockScreenLogBufferProvider;
    public Provider provideNotificationMediaManagerProvider;
    public Provider provideNotificationPanelLoggerProvider;
    public Provider provideNotificationRemoteInputLogBufferProvider;
    public Provider provideNotificationRenderLogBufferProvider;
    public Provider provideNotificationRowContentBinderProvider;
    public Provider provideNotificationSettingsInteractorProvider;
    public Provider provideNotificationSettingsRepositoryProvider;
    public Provider provideNotificationTransitionAnimatorControllerProvider;
    public Provider provideNotificationsControllerProvider;
    public final Provider provideNotificationsLogBufferProvider;
    public Provider provideNotificationsSoundPolicyInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideOneHandedModeTileViewModelProvider;
    public Provider provideOngoingCallLogBufferProvider;
    public Provider provideOptionalProvider;
    public final Provider providePackageChangeRepoLogBufferProvider;
    public Provider providePanelExpansionInteractorProvider;
    public Provider provideParentViewGroupProvider;
    public Provider providePrivacyLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideProximitySensorProvider;
    public Provider provideQBluetoothTileDialogLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideQRCodeScannerTileViewModelProvider;
    public Provider provideQSAutoAddLogBufferProvider;
    public Provider provideQSConfigLogBufferProvider;
    public Provider provideQSFragmentDisableLogBufferProvider;
    public Provider provideQSTileListLogBufferProvider;
    public Provider provideQuickAccessWalletClientProvider;
    public Provider provideQuickSettingsControllerProvider;
    public Provider provideQuickSettingsLogBufferProvider;
    public Provider provideQuickTapActionsProvider;
    public Provider provideQuickTapWakeLockProvider;
    public final Provider provideRealWifiRepositoryProvider;
    public Provider provideRecentsProvider;
    public Provider provideRecordingControllerLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideReduceBrightColorsTileViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideResolverMapProvider;
    public Provider provideReverseChargingViewControllerOptionalProvider;
    public final Provider provideReverseWirelessChargerProvider;
    public Provider provideRonContentExtractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideRotationTileViewModelProvider;
    public final Provider provideSceneFrameworkLogBufferProvider;
    public Provider provideScreenDecorationsLogProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideScreenRecordTileViewModelProvider;
    public Provider provideScrimLogBufferProvider;
    public Provider provideSecureSettingsRepositoryProvider;
    public Provider provideSensitiveNotificationProtectionLogBufferProvider;
    public Provider provideSensorPrivacyControllerProvider;
    public Provider provideShadeAnimationInteractorProvider;
    public Provider provideShadeCarrierLogProvider;
    public DelegateFactory provideShadeControllerProvider;
    public DelegateFactory provideShadeLockscreenInteractorProvider;
    public Provider provideShadeLogBufferProvider;
    public Provider provideShadeSessionStorageProvider;
    public Provider provideShadeSurfaceProvider;
    public Provider provideShadeTouchLogBufferProvider;
    public Provider provideShadeWindowLogBufferProvider;
    public final Provider provideSharedConnectivityTableLogBufferProvider;
    public Provider provideSmartReplyControllerProvider;
    public Provider provideStatusBarIconListProvider;
    public Provider provideStatusBarNetworkControllerBufferProvider;
    public Provider provideSwipeUpLogBufferProvider;
    public Provider provideSysUIUnfoldComponentProvider;
    public final DelegateFactory provideSysUiStateProvider;
    public Provider provideSystemEventDataSourceProvider;
    public Provider provideSystemSettingsRepositoryProvider;
    public Provider provideSystemStatusAnimationSchedulerLogBufferProvider;
    public Provider provideSystemUserMonitorProvider;
    public Provider provideTaskStackChangeListenersProvider;
    public Provider provideTimeTickHandlerProvider;
    public Provider provideToastLogBufferProvider;
    public Provider provideUdfpsLogBufferProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideUiModeNightTileViewModelProvider;
    public Provider provideUnseenNotificationLogBufferProvider;
    public final Provider provideUsbManagerProvider;
    public Provider provideUserSelectedActionsProvider;
    public final DelegateFactory provideUserTrackerProvider;
    public Provider provideVerboseDeviceBasedSatelliteInputLogProvider;
    public Provider provideVerboseMobileViewLogBufferProvider;
    public Provider provideVisualInterruptionDecisionProvider;
    public Provider provideVisualStabilityLogBufferProvider;
    public final Provider provideVolumeLogBufferProvider;
    public final Provider provideWakeLockLogProvider;
    public Provider provideWeatherSmartspaceDataPluginProvider;
    public final Provider provideWifiLogBufferProvider;
    public final Provider provideWifiTableLogBufferProvider;
    public final Provider provideWirelessChargerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider provideWorkModeTileViewModelProvider;
    public Provider provideZenIconLoaderProvider;
    public Provider provideZenModeRepositoryProvider;
    public Provider providerBluetoothLogBufferProvider;
    public Provider providerProvider;
    public Provider providesAlertingHeaderSubcomponentProvider;
    public Provider providesAuthRippleViewProvider;
    public Provider providesBatteryMeterViewControllerProvider;
    public Provider providesBatteryMeterViewProvider;
    public Provider providesBiometricStatusInteractorProvider;
    public Provider providesBrightnessLogProvider;
    public Provider providesBrightnessTableLogProvider;
    public Provider providesCombinedShadeHeadersConstraintManagerProvider;
    public final Provider providesCommunalBackupUtilsProvider;
    public Provider providesCommunalMediaHostProvider;
    public final Provider providesCommunalSceneDataSourceDelegatorProvider;
    public Provider providesControlsFeatureEnabledProvider;
    public Provider providesCredentialInteractorProvider;
    public final Provider providesDisplayStateInteractorProvider;
    public Provider providesDreamOverlayNotificationCountProvider;
    public final DelegateFactory providesFaceAuthInteractorInstanceProvider;
    public final Provider providesFalsingCollectorLegacyProvider;
    public Provider providesGestureSensorProvider;
    public Provider providesIncomingHeaderSubcomponentProvider;
    public Provider providesKeyguardMediaHostProvider;
    public Provider providesKeyguardRootViewProvider;
    public final Provider providesLeakDetectorProvider;
    public Provider providesLightRevealScrimProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider providesLockscreenBlueprintsProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider providesLockscreenContentProvider;
    public Provider providesLongPressTouchLogProvider;
    public final Provider providesMediaDataManagerProvider;
    public final Provider providesMediaDeviceLogBufferProvider;
    public final Provider providesMediaLogBufferProvider;
    public final Provider providesMediaTimeoutListenerLogBufferProvider;
    public Provider providesNewsHeaderSubcomponentProvider;
    public Provider providesNotificationPanelViewProvider;
    public DelegateFactory providesNotificationShadeWindowViewProvider;
    public DelegateFactory providesNotificationStackScrollLayoutProvider;
    public Provider providesNotificationsQuickSettingsContainerProvider;
    public Provider providesOngoingPrivacyChipProvider;
    public Provider providesOverlapDetectorProvider;
    public Provider providesPanelsLogProvider;
    public Provider providesPeopleHeaderSubcomponentProvider;
    public Provider providesPluginExecutorProvider;
    public Provider providesPromoHeaderSubcomponentProvider;
    public Provider providesQSMediaHostProvider;
    public Provider providesQSRestoreLogBufferProvider;
    public Provider providesQuickQSMediaHostProvider;
    public Provider providesRecsHeaderSubcomponentProvider;
    public final Provider providesSceneDataSourceDelegatorProvider;
    public Provider providesScreenshotViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider providesScrnshtNotifSmartActionsProvider;
    public Provider providesShadeHeaderViewProvider;
    public Provider providesSharedNotificationContainerProvider;
    public Provider providesSilentHeaderSubcomponentProvider;
    public Provider providesSocialHeaderSubcomponentProvider;
    public Provider providesStatusBarWindowViewInflaterProvider;
    public Provider providesStatusIconContainerProvider;
    public Provider providesTapAgainViewProvider;
    public Provider providesThreadAssertProvider;
    public Provider providesThumbnailObserverProvider;
    public DelegateFactory providesWindowRootViewProvider;
    public Provider providsBackGestureTfClassifierProvider;
    public Provider proximityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider proximitySensorImplProvider;
    public Provider pulseExpansionHandlerProvider;
    public Provider pulsingGestureListenerProvider;
    public Provider qRCodeScannerControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider qRCodeScannerTileProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider qSActivityProvider;
    public Provider qSFactoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider qSFragmentComposeProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider qSFragmentLegacyProvider;
    public Provider qSFragmentStartableProvider;
    public Provider qSHostAdapterProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider qSImplProvider;
    public Provider qSPipelineCoreStartableProvider;
    public Provider qSPipelineFlagsRepositoryProvider;
    public Provider qSPreferencesInteractorProvider;
    public Provider qSPreferencesRepositoryProvider;
    public Provider qSSceneAdapterImplProvider;
    public Provider qSSecurityFooterUtilsProvider;
    public Provider qSSettingsRestoredBroadcastRepositoryProvider;
    public Provider qSTileAnalyticsProvider;
    public Provider qSTileConfigProviderImplProvider;
    public Provider qSTileIntentUserInputHandlerImplProvider;
    public Provider qSTileLoggerProvider;
    public Provider qrCodeScannerKeyguardQuickAffordanceConfigProvider;
    public Provider qsEventLoggerImplProvider;
    public Provider qsFrameTranslateImplProvider;
    public Provider quickAccessWalletControllerProvider;
    public Provider quickAccessWalletKeyguardQuickAffordanceConfigProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider quickAccessWalletTileProvider;
    public Provider quickQuickSettingsRowInteractorProvider;
    public Provider quickQuickSettingsRowRepositoryProvider;
    public Provider quickQuickSettingsViewModelProvider;
    public Provider quickSettingsContainerViewModelProvider;
    public Provider quickSettingsControllerImplProvider;
    public Provider quickSettingsControllerSceneImplProvider;
    public Provider quickSettingsSceneProvider;
    public Provider quickSettingsShadeOverlayProvider;
    public Provider rearDisplayDialogControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider recordIssueTileProvider;
    public Provider recordingControllerLoggerProvider;
    public Provider recordingControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider recordingServiceProvider;
    public Provider reduceBrightColorsAutoAddableProvider;
    public Provider reduceBrightColorsControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider reduceBrightColorsTileProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider refreshRateInteractorProvider;
    public Provider refreshRateRequesterBinderProvider;
    public final Provider refreshUsersSchedulerProvider;
    public Provider remoteInputControllerLoggerProvider;
    public Provider remoteInputInteractorProvider;
    public Provider remoteInputNotificationRebuilderProvider;
    public final Provider remoteInputQuickSettingsDisablerProvider;
    public Provider remoteInputRepositoryImplProvider;
    public Provider remoteInputUriControllerProvider;
    public final Provider renderStageManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider repoProvider;
    public Provider resourceTrimmerProvider;
    public Provider restartDozeListenerProvider;
    public Provider restoreReconciliationInteractorProvider;
    public final Provider resumeMediaBrowserLoggerProvider;
    public Provider retailModeInteractorImplProvider;
    public Provider retailModeSettingsRepositoryProvider;
    public Provider reverseChargingAutoAddableProvider;
    public final Provider reverseChargingControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider reverseChargingTileProvider;
    public Provider reverseChargingViewControllerProvider;
    public Provider richOngoingNotificationContentExtractorImplProvider;
    public Provider richOngoingNotificationViewInflaterImplProvider;
    public Provider ringerModeTrackerImplProvider;
    public Provider ringtonePlayerProvider;
    public Provider rotationLockControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider rotationLockTileGoogleProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider rotationPolicyWrapperImplProvider;
    public Provider rowContentBindStageProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider rowInflaterTaskProvider;
    public final Provider sceneBackInteractorProvider;
    public Provider sceneContainerOcclusionInteractorProvider;
    public Provider sceneContainerPluginProvider;
    public final Provider sceneContainerRepositoryProvider;
    public Provider sceneContainerStartableProvider;
    public final DelegateFactory sceneInteractorProvider;
    public Provider screenBrightnessDisplayManagerRepositoryProvider;
    public Provider screenBrightnessInteractorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider screenCaptureDevicePolicyResolverProvider;
    public Provider screenDecorationsLoggerProvider;
    public Provider screenDecorationsProvider;
    public DelegateFactory screenOffAnimationControllerProvider;
    public Provider screenOnCoordinatorProvider;
    public Provider screenPinningRequestProvider;
    public Provider screenRecordChipInteractorProvider;
    public Provider screenRecordChipViewModelProvider;
    public Provider screenRecordRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider screenRecordTileProvider;
    public Provider screenTouchProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider screenshotProxyServiceProvider;
    public Provider screenshotSmartActionsProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider screenshotSoundControllerImplProvider;
    public Provider screenshotSoundProviderImplProvider;
    public Provider scrimControllerProvider;
    public Provider scrimControllerProvider2;
    public Provider scrimShadeTransitionControllerProvider;
    public Provider scrimStartableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider sectionHeaderControllerSubcomponentBuilderProvider;
    public Provider sectionHeaderVisibilityProvider;
    public Provider sectionStyleProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider secureSettingsImplProvider;
    public Provider securityControllerImplProvider;
    public Provider securityRepositoryImplProvider;
    public Provider seenNotificationsInteractorProvider;
    public Provider selectedComponentRepositoryImplProvider;
    public final Provider selectedUserInteractorProvider;
    public Provider sensitiveNotificationProtectionControllerImplProvider;
    public Provider sensorConfigurationProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider sensorUseStartedActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider serviceConfigurationGoogleProvider;
    public final Provider sessionTrackerProvider;
    public final Optional setBackAnimation;
    public final Optional setBubbles;
    public final Optional setDesktopMode;
    public final Optional setDisplayAreaHelper;
    public final KeyguardTransitions setKeyguardTransitions;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider setOfOverlayProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider setOfSceneProvider;
    public final Optional setOneHanded;
    public final Optional setPip;
    public final Optional setRecentTasks;
    public final ShellInterface setShell;
    public final ShellTransitions setShellTransitions;
    public final Optional setSplitScreen;
    public final Optional setStartingSurface;
    public final Optional setTaskViewFactory;
    public Provider settingsActionProvider;
    public Provider settingsActionProvider2;
    public final Provider settingsBgDispatcherProvider;
    public Provider settingsDataSourceProvider;
    public Provider setupWizardActionProvider;
    public Provider setupWizardProvider;
    public Provider setupWizardProvider2;
    public Provider setupWizardRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider severeLowBatteryNotificationProvider;
    public Provider shadeAnimationInteractorLegacyImplProvider;
    public Provider shadeAnimationInteractorSceneContainerImplProvider;
    public Provider shadeAnimationRepositoryProvider;
    public Provider shadeCarrierGroupControllerLoggerProvider;
    public Provider shadeControllerImplProvider;
    public Provider shadeControllerSceneImplProvider;
    public Provider shadeEventCoordinatorProvider;
    public Provider shadeExpansionStateManagerProvider;
    public Provider shadeHeaderClockInteractorProvider;
    public Provider shadeHeaderClockRepositoryProvider;
    public Provider shadeHeaderControllerProvider;
    public final DelegateFactory shadeInteractorImplProvider;
    public final Provider shadeInteractorLegacyImplProvider;
    public final Provider shadeInteractorSceneContainerImplProvider;
    public final Provider shadeListBuilderProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider shadeLockscreenInteractorImplProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider shadeModeInteractorImplProvider;
    public final Provider shadeRepositoryImplProvider;
    public Provider shadeSceneProvider;
    public Provider shadeStartableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider shadeSurfaceImplProvider;
    public Provider shadeViewManagerFactoryProvider;
    public Provider shareToAppChipViewModelProvider;
    public final SharedLibraryModule sharedLibraryModule;
    public Provider sharedNotificationContainerBinderProvider;
    public final Provider sharedNotificationContainerInteractorProvider;
    public Provider sharedNotificationContainerViewModelProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider shortcutHelperActivityProvider;
    public Provider shortcutHelperActivityStarterProvider;
    public Provider shortcutHelperCategoriesInteractorProvider;
    public Provider shortcutHelperCategoriesRepositoryProvider;
    public Provider shortcutHelperStateInteractorProvider;
    public Provider shortcutHelperStateRepositoryProvider;
    public Provider shortcutKeyDispatcherProvider;
    public Provider sideFpsLoggerProvider;
    public Provider sideFpsOverlayViewBinderProvider;
    public Provider sideFpsProgressBarProvider;
    public Provider sideFpsProgressBarViewBinderProvider;
    public Provider sideFpsProgressBarViewModelProvider;
    public Provider sideFpsSensorInteractorProvider;
    public Provider silenceAlertsDisabledProvider;
    public Provider silenceCallProvider;
    public final Provider simBouncerInteractorProvider;
    public final Provider simBouncerRepositoryImplProvider;
    public Provider singlePointerTouchProcessorProvider;
    public Provider sliceBroadcastRelayHandlerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider smartActionsReceiverProvider;
    public Provider smartReplyConstantsProvider;
    public Provider smartspaceSectionProvider;
    public Provider snapshotConfigurationProvider;
    public Provider snoozeAlarmProvider;
    public Provider splitShadeKeyguardBlueprintProvider;
    public final Provider splitShadeStateControllerImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider starterProvider;
    public Provider statusBarContentInsetsProvider;
    public Provider statusBarDisableFlagsInteractorProvider;
    public Provider statusBarHeadsUpChangeListenerProvider;
    public Provider statusBarHideIconsForBouncerManagerProvider;
    public Provider statusBarIconControllerImplProvider;
    public Provider statusBarIconViewBindingFailureTrackerProvider;
    public Provider statusBarInitializerImplProvider;
    public Provider statusBarKeyguardViewManagerInteractorProvider;
    public final DelegateFactory statusBarKeyguardViewManagerProvider;
    public Provider statusBarLocationPublisherProvider;
    public Provider statusBarModePerDisplayRepositoryFactoryProvider;
    public Provider statusBarModeRepositoryImplProvider;
    public Provider statusBarNotificationActivityStarterProvider;
    public Provider statusBarNotificationPresenterProvider;
    public Provider statusBarPipelineFlagsProvider;
    public Provider statusBarRemoteInputCallbackProvider;
    public Provider statusBarSignalPolicyProvider;
    public Provider statusBarStartableProvider;
    public final DelegateFactory statusBarStateControllerImplProvider;
    public Provider statusBarTouchableRegionManagerProvider;
    public Provider statusBarWindowControllerProvider;
    public Provider statusBarWindowStateControllerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider statusBarWindowViewInflaterImplProvider;
    public Provider stickyKeyDialogFactoryProvider;
    public Provider stickyKeysIndicatorCoordinatorProvider;
    public Provider stickyKeysRepositoryImplProvider;
    public Provider stockTilesRepositoryProvider;
    public Provider storageNotificationProvider;
    public Provider strideAreaSectionProvider;
    public Provider strideLockscreenRepositoryProvider;
    public Provider stylusManagerProvider;
    public Provider stylusUsiPowerStartableProvider;
    public Provider stylusUsiPowerUIProvider;
    public Provider subscriptionManagerSlotIndexResolverProvider;
    public Provider swipeChipbarAwayGestureHandlerProvider;
    public Provider swipeHandlerProvider;
    public Provider swipeStatusBarAwayGestureHandlerProvider;
    public Provider swipeToDismissInteractorProvider;
    public Provider swipeUpAnywhereGestureHandlerProvider;
    public Provider swipeUpGestureLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider switchToManagedProfileForCallActivityProvider;
    public final SysUICoroutinesModule sysUICoroutinesModule;
    public Provider sysUICutoutProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl sysUIGoogleGlobalRootComponentImpl;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl sysUIGoogleSysUIComponentImpl = this;
    public Provider sysUIKeyEventHandlerProvider;
    public final SysUIUnfoldModule sysUIUnfoldModule;
    public Provider sysUiUnfoldComponentProvider;
    public Provider systemActionsProvider;
    public final DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider systemClockImplProvider;
    public Provider systemEventCoordinatorProvider;
    public Provider systemKeyPressProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider systemStatusAnimationSchedulerImplProvider;
    public Provider systemStatusAnimationSchedulerLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider systemUIAuxiliaryDumpServiceProvider;
    public final Provider systemUIConfigDumpableProvider;
    public final Provider systemUIDeviceEntryFaceAuthInteractorProvider;
    public final Provider systemUIDialogManagerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider systemUISecondaryUserServiceProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider systemUIServiceProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider systemUiProxyClientProvider;
    public Provider sysuiColorExtractorProvider;
    public final Provider tableLogBufferFactoryProvider;
    public Provider takeScreenshotExecutorImplProvider;
    public Provider takeScreenshotProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider takeScreenshotServiceProvider;
    public Provider tapAgainViewControllerProvider;
    public Provider tapGestureDetectorProvider;
    public Provider targetSdkResolverProvider;
    public Provider taskSwitchInteractorProvider;
    public Provider taskSwitcherNotificationCoordinatorProvider;
    public Provider taskbarDelegateProvider;
    public Provider telephonyActivityProvider;
    public final Provider telephonyInteractorProvider;
    public final Provider telephonyListenerManagerProvider;
    public final Provider telephonyRepositoryImplProvider;
    public Provider temporaryViewUiEventLoggerProvider;
    public Provider themeOverlayApplierProvider;
    public Provider themeOverlayControllerGoogleProvider;
    public Provider tileGridViewModelProvider;
    public Provider tileJavaAdapterProvider;
    public DelegateFactory tileServicesProvider;
    public Provider tileSpecSettingsRepositoryProvider;
    public Provider tilesAvailabilityInteractorProvider;
    public Provider timeoutManagerProvider;
    public Provider toAodFoldTransitionInteractorProvider;
    public Provider toastFactoryProvider;
    public Provider toastUIProvider;
    public Provider toggleFlashlightProvider;
    public Provider touchInsideHandlerProvider;
    public Provider touchpadGesturesInteractorProvider;
    public Provider touchpadRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider touchpadTutorialActivityProvider;
    public Provider traceurMessageSenderProvider;
    public final Provider trustInteractorProvider;
    public final Provider trustRepositoryImplProvider;
    public final Provider trustRepositoryLoggerProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider tunerActivityProvider;
    public final DelegateFactory tunerServiceImplProvider;
    public Provider tutorialNotificationCoordinatorProvider;
    public Provider tutorialSchedulerInteractorProvider;
    public Provider tutorialSchedulerRepositoryProvider;
    public DelegateFactory udfpsControllerProvider;
    public Provider udfpsHapticsSimulatorProvider;
    public Provider udfpsKeyguardAccessibilityDelegateProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider udfpsLoggerProvider;
    public final Provider udfpsOverlayInteractorProvider;
    public Provider udfpsShellProvider;
    public Provider uiBgCoroutineContextProvider;
    public Provider uiBgDispatcherProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider uiModeNightTileProvider;
    public Provider uiOffloadThreadProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider unfoldInitializationStartableProvider;
    public Provider unfoldTraceLoggerProvider;
    public Provider unfoldTransitionInteractorProvider;
    public DelegateFactory unlockedScreenOffAnimationControllerProvider;
    public Provider unpinNotificationsProvider;
    public Provider usageStatsInteractorProvider;
    public Provider usageStatsRepositoryImplProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider usbAccessoryUriActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider usbConfirmActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider usbDebuggingActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider usbDebuggingSecondaryUserActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider usbPermissionActivityProvider;
    public Provider usbStateProvider;
    public Provider usbStateProvider2;
    public Provider userActivityProvider;
    public Provider userAwareSecureSettingsRepositoryImplProvider;
    public Provider userContextualEducationRepositoryProvider;
    public Provider userFetcherProvider;
    public final Provider userFileManagerImplProvider;
    public Provider userInfoControllerImplProvider;
    public Provider userInputDeviceRepositoryProvider;
    public final Provider userRepositoryImplProvider;
    public Provider userSelectedActionProvider;
    public final Provider userSetupRepositoryImplProvider;
    public Provider userSwitchDialogControllerProvider;
    public final DelegateFactory userSwitcherControllerProvider;
    public Provider userSwitcherDialogCoordinatorProvider;
    public final DelegateFactory userSwitcherInteractorProvider;
    public Provider userSwitcherRepositoryImplProvider;
    public final Provider userSwitcherViewModelProvider;
    public Provider verboseMobileViewLoggerProvider;
    public Provider vibratorHelperProvider;
    public Provider videoCameraQuickAffordanceConfigProvider;
    public Provider viewModelFactoryAssistedProvider;
    public Provider viewUtilProvider;
    public Provider visibilityLocationProviderDelegatorProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider visualInterruptionDecisionProviderImplProvider;
    public Provider visualStabilityCoordinatorProvider;
    public Provider visualStabilityProvider;
    public Provider volumeDialogComponentProvider;
    public Provider volumeDialogControllerImplProvider;
    public Provider volumeDialogInteractorProvider;
    public Provider volumeDialogRepositoryProvider;
    public Provider volumeLoggerProvider;
    public Provider volumeNavigatorProvider;
    public Provider volumePanelFactoryProvider;
    public Provider volumePanelGlobalStateInteractorProvider;
    public Provider volumePanelGlobalStateRepositoryProvider;
    public Provider volumeUIProvider;
    public Provider vpnNetworkMonitorProvider;
    public Provider vpnPackageMonitorProvider;
    public Provider wMShellProvider;
    public Provider wakeModeProvider;
    public Provider wakefulnessLifecycleProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider walletActivityProvider;
    public Provider walletAutoAddableProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider walletContextualLocationsServiceProvider;
    public Provider walletContextualSuggestionsControllerProvider;
    public Provider walletControllerImplProvider;
    public Provider wallpaperControllerProvider;
    public Provider wallpaperRepositoryImplProvider;
    public Provider warpLockscreenRepositoryProvider;
    public Provider widgetInteractionHandlerProvider;
    public Provider widgetTrampolineInteractorProvider;
    public Provider wifiConstantsProvider;
    public Provider wifiInteractorImplProvider;
    public final Provider wifiPickerTrackerFactoryProvider;
    public final Provider wifiRepositorySwitcherProvider;
    public Provider wifiStateWorkerProvider;
    public Provider wifiUiAdapterProvider;
    public Provider wifiViewModelProvider;
    public Provider windowManagerLockscreenVisibilityInteractorProvider;
    public Provider windowManagerLockscreenVisibilityManagerProvider;
    public Provider windowManagerLockscreenVisibilityViewModelProvider;
    public Provider windowManagerOcclusionManagerProvider;
    public Provider windowRootViewVisibilityInteractorProvider;
    public Provider windowRootViewVisibilityRepositoryProvider;
    public Provider wiredChargingRippleControllerProvider;
    public final Provider wirelessChargerCommanderProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider workLockActivityProvider;
    public DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider workModeTileProvider;
    public Provider workTileAutoAddableProvider;
    public Provider workTileRestoreProcessorProvider;
    public Provider zenModeControllerImplProvider;

    /* renamed from: -$$Nest$maccessibilityActionsSection, reason: not valid java name */
    public static AccessibilityActionsSection m1489$$Nest$maccessibilityActionsSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AccessibilityActionsSection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (CommunalSettingsInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalSettingsInteractorProvider.get(), new AccessibilityActionsViewModel((CommunalInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalInteractorProvider.get(), (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get(), (KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get()));
    }

    /* renamed from: -$$Nest$maccessibilityLogger, reason: not valid java name */
    public static AccessibilityLogger m1490$$Nest$maccessibilityLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AccessibilityLogger((UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get());
    }

    /* renamed from: -$$Nest$mairplaneModeMapper, reason: not valid java name */
    public static AirplaneModeMapper m1491$$Nest$mairplaneModeMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AirplaneModeMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mairplaneModeTileDataInteractor, reason: not valid java name */
    public static AirplaneModeTileDataInteractor m1492$$Nest$mairplaneModeTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AirplaneModeTileDataInteractor((AirplaneModeRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.airplaneModeRepositoryImplProvider.get());
    }

    /* renamed from: -$$Nest$mairplaneModeTileUserActionInteractor, reason: not valid java name */
    public static AirplaneModeTileUserActionInteractor m1493$$Nest$mairplaneModeTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AirplaneModeTileUserActionInteractor((AirplaneModeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.airplaneModeInteractorProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$malarmTileDataInteractor, reason: not valid java name */
    public static AlarmTileDataInteractor m1494$$Nest$malarmTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AlarmTileDataInteractor((NextAlarmController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.nextAlarmControllerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dateFormatUtil());
    }

    /* renamed from: -$$Nest$malarmTileMapper, reason: not valid java name */
    public static AlarmTileMapper m1495$$Nest$malarmTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        return new AlarmTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get());
    }

    /* renamed from: -$$Nest$malarmTileUserActionInteractor, reason: not valid java name */
    public static AlarmTileUserActionInteractor m1496$$Nest$malarmTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AlarmTileUserActionInteractor((QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$malertingHeaderSectionHeaderController, reason: not valid java name */
    public static SectionHeaderNodeControllerImpl m1497$$Nest$malertingHeaderSectionHeaderController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        SectionHeaderNodeControllerImpl headerController = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesAlertingHeaderSubcomponentProvider.get()).getHeaderController();
        Preconditions.checkNotNullFromProvides(headerController);
        return headerController;
    }

    /* renamed from: -$$Nest$malternateBouncerMessageAreaViewModel, reason: not valid java name */
    public static AlternateBouncerMessageAreaViewModel m1498$$Nest$malternateBouncerMessageAreaViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AlternateBouncerMessageAreaViewModel((BiometricMessageInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.biometricMessageInteractorProvider.get(), (AlternateBouncerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerInteractorProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get());
    }

    /* renamed from: -$$Nest$malternateBouncerUdfpsIconViewModel, reason: not valid java name */
    public static AlternateBouncerUdfpsIconViewModel m1499$$Nest$malternateBouncerUdfpsIconViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AlternateBouncerUdfpsIconViewModel(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (ConfigurationInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.configurationInteractorProvider.get(), (DeviceEntryUdfpsInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryUdfpsInteractorProvider.get(), (DeviceEntryBackgroundViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryBackgroundViewModelProvider.get(), (FingerprintPropertyInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fingerprintPropertyInteractorProvider.get(), (UdfpsOverlayInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.udfpsOverlayInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerViewModel());
    }

    /* renamed from: -$$Nest$malwaysOnDisplayNotificationIconsInteractor, reason: not valid java name */
    public static AlwaysOnDisplayNotificationIconsInteractor m1501$$Nest$malwaysOnDisplayNotificationIconsInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AlwaysOnDisplayNotificationIconsInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (DeviceEntryInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconsInteractor());
    }

    /* renamed from: -$$Nest$manimationStatusRepositoryImpl, reason: not valid java name */
    public static AnimationStatusRepositoryImpl m1502$$Nest$manimationStatusRepositoryImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AnimationStatusRepositoryImpl((ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgHandlerProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get());
    }

    /* renamed from: -$$Nest$maodNotificationIconsSection, reason: not valid java name */
    public static AodNotificationIconsSection m1503$$Nest$maodNotificationIconsSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AodNotificationIconsSection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (ConfigurationState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationStateProvider.get(), (StatusBarIconViewBindingFailureTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarIconViewBindingFailureTrackerProvider.get(), (NotificationIconContainerAlwaysOnDisplayViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconContainerAlwaysOnDisplayViewModelProvider.get(), new AlwaysOnDisplayNotificationIconViewStore((NotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifCollectionProvider.get()), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemBarUtilsState(), (KeyguardRootViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRootViewModelProvider.get());
    }

    /* renamed from: -$$Nest$massistantAttentionCondition, reason: not valid java name */
    public static AssistantAttentionCondition m1504$$Nest$massistantAttentionCondition(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AssistantAttentionCondition((AssistManagerGoogle) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.assistManagerGoogleProvider.get());
    }

    /* renamed from: -$$Nest$mauthorizedPanelsRepositoryImpl, reason: not valid java name */
    public static AuthorizedPanelsRepositoryImpl m1505$$Nest$mauthorizedPanelsRepositoryImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new AuthorizedPanelsRepositoryImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (UserFileManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userFileManagerImplProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get());
    }

    /* renamed from: -$$Nest$mbatteryStateNotifier, reason: not valid java name */
    public static BatteryStateNotifier m1506$$Nest$mbatteryStateNotifier(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        BatteryController batteryController = (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new BatteryStateNotifier(batteryController, (NotificationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideNotificationManagerProvider.get(), (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundDelayableExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
    }

    /* renamed from: -$$Nest$mbluetoothDialogDelegate, reason: not valid java name */
    public static BluetoothDialogDelegate m1507$$Nest$mbluetoothDialogDelegate(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new BluetoothDialogDelegate((SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get());
    }

    /* renamed from: -$$Nest$mbluetoothTileDialogLogger, reason: not valid java name */
    public static BluetoothTileDialogLogger m1508$$Nest$mbluetoothTileDialogLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new BluetoothTileDialogLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideQBluetoothTileDialogLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mbouncerToGoneFlows, reason: not valid java name */
    public static BouncerToGoneFlows m1509$$Nest$mbouncerToGoneFlows(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new BouncerToGoneFlows((SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get(), (PrimaryBouncerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerInteractorProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardDismissActionInteractorProvider), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get(), (KeyguardTransitionAnimationFlow) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionAnimationFlowProvider.get());
    }

    /* renamed from: -$$Nest$mbrightnessSliderControllerFactory, reason: not valid java name */
    public static BrightnessSliderController.Factory m1510$$Nest$mbrightnessSliderControllerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new BrightnessSliderController.Factory((FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get());
    }

    /* renamed from: -$$Nest$mbroadcastDispatcherLogger, reason: not valid java name */
    public static BroadcastDispatcherLogger m1511$$Nest$mbroadcastDispatcherLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new BroadcastDispatcherLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBroadcastDispatcherLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mclipboardImageLoader, reason: not valid java name */
    public static ClipboardImageLoader m1512$$Nest$mclipboardImageLoader(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new ClipboardImageLoader(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
    }

    /* renamed from: -$$Nest$mclipboardOverlayUtils, reason: not valid java name */
    public static ClipboardOverlayUtils m1513$$Nest$mclipboardOverlayUtils(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ClipboardOverlayUtils((TextClassificationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideTextClassificationManagerProvider.get());
    }

    /* renamed from: -$$Nest$mclipboardOverlayView, reason: not valid java name */
    public static ClipboardOverlayView m1514$$Nest$mclipboardOverlayView(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        ClipboardOverlayView clipboardOverlayView = (ClipboardOverlayView) LayoutInflater.from(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.overlayWindowContextContext()).inflate(R.layout.clipboard_overlay, (ViewGroup) null);
        Preconditions.checkNotNullFromProvides(clipboardOverlayView);
        return clipboardOverlayView;
    }

    /* renamed from: -$$Nest$mclipboardOverlayWindow, reason: not valid java name */
    public static ClipboardOverlayWindow m1515$$Nest$mclipboardOverlayWindow(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Context overlayWindowContextContext = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.overlayWindowContextContext();
        WindowManager windowManager = (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.overlayWindowContextContext().getSystemService(WindowManager.class);
        Preconditions.checkNotNullFromProvides(windowManager);
        ViewCaptureAwareWindowManager viewCaptureAwareWindowManager = new ViewCaptureAwareWindowManager(windowManager, ConvenienceExtensionsKt.toKotlinLazy(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideViewCaptureProvider)));
        WindowManager windowManager2 = (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.overlayWindowContextContext().getSystemService(WindowManager.class);
        Preconditions.checkNotNullFromProvides(windowManager2);
        return new ClipboardOverlayWindow(overlayWindowContextContext, viewCaptureAwareWindowManager, windowManager2);
    }

    /* renamed from: -$$Nest$mclipboardToast, reason: not valid java name */
    public static ClipboardToast m1516$$Nest$mclipboardToast(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ClipboardToast(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context);
    }

    /* renamed from: -$$Nest$mclipboardTransitionExecutor, reason: not valid java name */
    public static ClipboardTransitionExecutor m1517$$Nest$mclipboardTransitionExecutor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ClipboardTransitionExecutor(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (DisplayTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDisplayTrackerProvider.get());
    }

    /* renamed from: -$$Nest$mclockEventController, reason: not valid java name */
    public static ClockEventController m1518$$Nest$mclockEventController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        KeyguardInteractor keyguardInteractor = (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get();
        KeyguardTransitionInteractor keyguardTransitionInteractor = (KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get();
        BroadcastDispatcher broadcastDispatcher = (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get();
        BatteryController batteryController = (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get();
        KeyguardUpdateMonitor keyguardUpdateMonitor = (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get();
        ConfigurationController configurationController = (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        Display display = (Display) Optional.empty().orElse(null);
        if (display != null && context.getDisplayId() != display.getDisplayId()) {
            context = context.createDisplayContext(display);
            Intrinsics.checkNotNull(context);
        }
        return new ClockEventController(keyguardInteractor, keyguardTransitionInteractor, broadcastDispatcher, batteryController, keyguardUpdateMonitor, configurationController, context.getResources(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.clockMessageBuffers(), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (ZenModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.zenModeControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mcollapsedStatusBarFragmentLogger, reason: not valid java name */
    public static CollapsedStatusBarFragmentLogger m1520$$Nest$mcollapsedStatusBarFragmentLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new CollapsedStatusBarFragmentLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCollapsedSbFragmentLogBufferProvider.get(), (DisableFlagsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disableFlagsLoggerProvider.get());
    }

    /* renamed from: -$$Nest$mcolorCorrectionTileDataInteractor, reason: not valid java name */
    public static ColorCorrectionTileDataInteractor m1521$$Nest$mcolorCorrectionTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ColorCorrectionTileDataInteractor((ColorCorrectionRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorCorrectionRepositoryImplProvider.get());
    }

    /* renamed from: -$$Nest$mcolorCorrectionTileMapper, reason: not valid java name */
    public static ColorCorrectionTileMapper m1522$$Nest$mcolorCorrectionTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ColorCorrectionTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mcolorCorrectionUserActionInteractor, reason: not valid java name */
    public static ColorCorrectionUserActionInteractor m1523$$Nest$mcolorCorrectionUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ColorCorrectionUserActionInteractor((ColorCorrectionRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorCorrectionRepositoryImplProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$mcolorInversionTileDataInteractor, reason: not valid java name */
    public static ColorInversionTileDataInteractor m1524$$Nest$mcolorInversionTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ColorInversionTileDataInteractor((ColorInversionRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorInversionRepositoryImplProvider.get());
    }

    /* renamed from: -$$Nest$mcolorInversionTileMapper, reason: not valid java name */
    public static ColorInversionTileMapper m1525$$Nest$mcolorInversionTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ColorInversionTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mcolorInversionUserActionInteractor, reason: not valid java name */
    public static ColorInversionUserActionInteractor m1526$$Nest$mcolorInversionUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ColorInversionUserActionInteractor((ColorInversionRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorInversionRepositoryImplProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$mcommunalContent, reason: not valid java name */
    public static CommunalContent m1527$$Nest$mcommunalContent(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new CommunalContent((CommunalViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalViewModelProvider.get(), new SmartspaceInteractionHandler((ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (CommunalSceneInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalSceneInteractorProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommunalLogBufferProvider.get()), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemUIDialogFactory(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockSection(), new BottomAreaSection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardQuickAffordancesCombinedViewModel(), (KeyguardIndicationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardIndicationControllerGoogleProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardIndicationAreaViewModel(), (KeyguardQuickAffordanceViewBinder) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardQuickAffordanceViewBinderProvider.get()), new AmbientStatusBarSection(new DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleSysUIComponentImpl)), new CommunalPopupSection((CommunalViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalViewModelProvider.get()), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalAppWidgetSection());
    }

    /* renamed from: -$$Nest$mconditionalRestarter, reason: not valid java name */
    public static ConditionalRestarter m1528$$Nest$mconditionalRestarter(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        SystemExitRestarter systemExitRestarter = new SystemExitRestarter();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new ScreenIdleCondition(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerInteractorProvider)));
        arrayList.add(new PluggedInCondition(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider)));
        arrayList.add(new NotOccludedCondition(DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider)));
        return new ConditionalRestarter(systemExitRestarter, arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList)), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get());
    }

    /* renamed from: -$$Nest$mcontrolsDialogsFactory, reason: not valid java name */
    public static ControlsDialogsFactory m1529$$Nest$mcontrolsDialogsFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ControlsDialogsFactory((SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get());
    }

    /* renamed from: -$$Nest$mcurrentUserIdProvider, reason: not valid java name */
    public static SystemUIModule$$ExternalSyntheticLambda0 m1530$$Nest$mcurrentUserIdProvider(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        UserTracker userTracker = (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get();
        Objects.requireNonNull(userTracker);
        return new SystemUIModule$$ExternalSyntheticLambda0(userTracker);
    }

    /* renamed from: -$$Nest$mdataSaverTileDataInteractor, reason: not valid java name */
    public static DataSaverTileDataInteractor m1531$$Nest$mdataSaverTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DataSaverTileDataInteractor((DataSaverControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDataSaverControllerProvider.get());
    }

    /* renamed from: -$$Nest$mdataSaverTileMapper, reason: not valid java name */
    public static DataSaverTileMapper m1532$$Nest$mdataSaverTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DataSaverTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mdataSaverTileUserActionInteractor, reason: not valid java name */
    public static DataSaverTileUserActionInteractor m1533$$Nest$mdataSaverTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new DataSaverTileUserActionInteractor((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.mainCoroutineContextProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (DataSaverControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDataSaverControllerProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get(), (DialogTransitionAnimator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDialogTransitionAnimatorProvider.get(), (SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get(), (UserFileManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userFileManagerImplProvider.get());
    }

    /* renamed from: -$$Nest$mddmHandleMotionTool, reason: not valid java name */
    public static DdmHandleMotionTool m1534$$Nest$mddmHandleMotionTool(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        MotionToolManager motionToolManager;
        DdmHandleMotionTool ddmHandleMotionTool;
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        WindowManagerGlobal windowManagerGlobal = WindowManagerGlobal.getInstance();
        synchronized (MotionToolManager.Companion) {
            motionToolManager = MotionToolManager.INSTANCE;
            if (motionToolManager == null) {
                motionToolManager = new MotionToolManager(windowManagerGlobal);
                MotionToolManager.INSTANCE = motionToolManager;
            }
        }
        synchronized (DdmHandleMotionTool.Companion) {
            ddmHandleMotionTool = DdmHandleMotionTool.INSTANCE;
            if (ddmHandleMotionTool == null) {
                ddmHandleMotionTool = new DdmHandleMotionTool(motionToolManager);
                DdmHandleMotionTool.INSTANCE = ddmHandleMotionTool;
            }
        }
        return ddmHandleMotionTool;
    }

    /* renamed from: -$$Nest$mdefaultAmbientIndicationAreaSection, reason: not valid java name */
    public static DefaultAmbientIndicationAreaSection m1535$$Nest$mdefaultAmbientIndicationAreaSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DefaultAmbientIndicationAreaSection((KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardAmbientIndicationViewModel(), (AodAlphaViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.aodAlphaViewModelProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeSurfaceProvider), (PowerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerInteractorProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider18.get());
    }

    /* renamed from: -$$Nest$mdefaultDeviceEntrySection, reason: not valid java name */
    public static DefaultDeviceEntrySection m1536$$Nest$mdefaultDeviceEntrySection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new DefaultDeviceEntrySection((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (AuthController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.authControllerProvider.get(), (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (NotificationPanelView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNotificationPanelViewProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLockIconViewControllerProvider), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryIconViewModelProvider), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryForegroundViewModelProvider), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryBackgroundViewModelProvider), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesLongPressTouchLogProvider.get());
    }

    /* renamed from: -$$Nest$mdefaultNotificationStackScrollLayoutSection, reason: not valid java name */
    public static DefaultNotificationStackScrollLayoutSection m1537$$Nest$mdefaultNotificationStackScrollLayoutSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DefaultNotificationStackScrollLayoutSection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (NotificationPanelView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNotificationPanelViewProvider.get(), (SharedNotificationContainer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesSharedNotificationContainerProvider.get(), (SharedNotificationContainerViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sharedNotificationContainerViewModelProvider.get(), (SharedNotificationContainerBinder) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sharedNotificationContainerBinderProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.largeScreenHeaderHelperProvider));
    }

    /* renamed from: -$$Nest$mdefaultSettingsPopupMenuSection, reason: not valid java name */
    public static DefaultSettingsPopupMenuSection m1538$$Nest$mdefaultSettingsPopupMenuSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DefaultSettingsPopupMenuSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), new KeyguardSettingsMenuViewModel((KeyguardTouchHandlingInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTouchHandlingInteractorProvider.get()), (KeyguardTouchHandlingViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTouchHandlingViewModelProvider.get(), (KeyguardRootViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardRootViewModelProvider.get(), (VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get());
    }

    /* renamed from: -$$Nest$mdefaultShortcutsSection, reason: not valid java name */
    public static DefaultShortcutsSection m1539$$Nest$mdefaultShortcutsSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Resources m977$$Nest$mmainResources = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl);
        KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel = (KeyguardQuickAffordancesCombinedViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardQuickAffordancesCombinedViewModelProvider.get();
        return new DefaultShortcutsSection(m977$$Nest$mmainResources, keyguardQuickAffordancesCombinedViewModel, (KeyguardIndicationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardIndicationControllerGoogleProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardBlueprintInteractorProvider), (KeyguardQuickAffordanceViewBinder) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardQuickAffordanceViewBinderProvider.get());
    }

    /* renamed from: -$$Nest$mdefaultStatusViewSection, reason: not valid java name */
    public static DefaultStatusViewSection m1540$$Nest$mdefaultStatusViewSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new DefaultStatusViewSection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (NotificationPanelView) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNotificationPanelViewProvider.get(), new DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleSysUIComponentImpl), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardViewConfiguratorProvider), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationPanelViewControllerProvider), (KeyguardMediaController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardMediaControllerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mdefaultUdfpsAccessibilityOverlaySection, reason: not valid java name */
    public static DefaultUdfpsAccessibilityOverlaySection m1541$$Nest$mdefaultUdfpsAccessibilityOverlaySection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DefaultUdfpsAccessibilityOverlaySection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, new DeviceEntryUdfpsAccessibilityOverlayViewModel((UdfpsOverlayInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.udfpsOverlayInteractorProvider.get(), (AccessibilityInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.accessibilityInteractorProvider.get(), (DeviceEntryIconViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryIconViewModelProvider.get(), (DeviceEntryForegroundViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceEntryForegroundViewModelProvider.get()));
    }

    /* renamed from: -$$Nest$mdemoMobileConnectionsRepository, reason: not valid java name */
    public static DemoMobileConnectionsRepository m1542$$Nest$mdemoMobileConnectionsRepository(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DemoModeMobileConnectionDataSource demoModeMobileConnectionDataSource = (DemoModeMobileConnectionDataSource) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.demoModeMobileConnectionDataSourceProvider.get();
        DemoModeWifiDataSource demoModeWifiDataSource = (DemoModeWifiDataSource) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.demoModeWifiDataSourceProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new DemoMobileConnectionsRepository(demoModeMobileConnectionDataSource, demoModeWifiDataSource, (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (TableLogBufferFactory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tableLogBufferFactoryProvider.get());
    }

    /* renamed from: -$$Nest$mdemoWifiRepository, reason: not valid java name */
    public static DemoWifiRepository m1543$$Nest$mdemoWifiRepository(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DemoWifiRepository((DemoModeWifiDataSource) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.demoModeWifiDataSourceProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
    }

    /* renamed from: -$$Nest$mdeviceProvisioningRepositoryImpl, reason: not valid java name */
    public static DeviceProvisioningRepositoryImpl m1544$$Nest$mdeviceProvisioningRepositoryImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DeviceProvisioningRepositoryImpl((DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get());
    }

    /* renamed from: -$$Nest$mdeviceStateHelper, reason: not valid java name */
    public static KeyguardDisplayManager.DeviceStateHelper m1545$$Nest$mdeviceStateHelper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new KeyguardDisplayManager.DeviceStateHelper(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DeviceStateManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDeviceStateManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mdeviceStateRepositoryImpl, reason: not valid java name */
    public static DeviceStateRepositoryImpl m1546$$Nest$mdeviceStateRepositoryImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new DeviceStateRepositoryImpl(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (DeviceStateManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDeviceStateManagerProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgApplicationScopeProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mdreamCondition, reason: not valid java name */
    public static DreamCondition m1547$$Nest$mdreamCondition(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DreamCondition(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamManager(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get());
    }

    /* renamed from: -$$Nest$mdreamStatusBarStateCallback, reason: not valid java name */
    public static DreamStatusBarStateCallback m1548$$Nest$mdreamStatusBarStateCallback(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DreamStatusBarStateCallback((SysuiStatusBarStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarStateControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mdumpHandler, reason: not valid java name */
    public static DumpHandler m1549$$Nest$mdumpHandler(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new DumpHandler((DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (LogBufferEulogizer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.logBufferEulogizerProvider.get(), (SystemUIConfigDumpable) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemUIConfigDumpableProvider.get());
    }

    /* renamed from: -$$Nest$medgeBackGestureHandlerFactory, reason: not valid java name */
    public static EdgeBackGestureHandler.Factory m1550$$Nest$medgeBackGestureHandlerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        OverviewProxyService overviewProxyService = (OverviewProxyService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.overviewProxyServiceProvider.get();
        SysUiState sysUiState = (SysUiState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSysUiStateProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new EdgeBackGestureHandler.Factory(overviewProxyService, sysUiState, (PluginManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providesPluginManagerProvider.get(), (UiThreadContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackPanelUiThreadContextProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgHandlerProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (NavigationModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationModeControllerProvider.get(), new BackPanelController.Factory((ViewCaptureAwareWindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideViewCaptureAwareWindowManagerProvider.get(), (ViewConfiguration) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideViewConfigurationProvider.get(), (UiThreadContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackPanelUiThreadContextProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (LatencyTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideLatencyTrackerProvider.get(), (InteractionJankMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInteractionJankMonitorProvider.get()), (ViewConfiguration) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideViewConfigurationProvider.get(), (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), (IWindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideIWindowManagerProvider.get(), (InputManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInputManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setPip, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setDesktopMode, (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providsBackGestureTfClassifierProvider, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lightBarControllerProvider, (NotificationShadeWindowController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationShadeWindowControllerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.gestureInteractor(), (JavaAdapter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.javaAdapterProvider.get());
    }

    /* renamed from: -$$Nest$memergencyDialerIntentFactory, reason: not valid java name */
    public static BouncerInteractorModule$emergencyDialerIntentFactory$1 m1551$$Nest$memergencyDialerIntentFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new BouncerInteractorModule$emergencyDialerIntentFactory$1((TelecomManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideTelecomManagerProvider.get());
    }

    /* renamed from: -$$Nest$mexpansionStateLogger, reason: not valid java name */
    public static NotificationLogger.ExpansionStateLogger m1552$$Nest$mexpansionStateLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationLogger.ExpansionStateLogger((Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideUiBackgroundExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mflashlightMapper, reason: not valid java name */
    public static FlashlightMapper m1553$$Nest$mflashlightMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new FlashlightMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mflashlightTileDataInteractor, reason: not valid java name */
    public static FlashlightTileDataInteractor m1554$$Nest$mflashlightTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new FlashlightTileDataInteractor((FlashlightControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.flashlightControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mflashlightTileUserActionInteractor, reason: not valid java name */
    public static FlashlightTileUserActionInteractor m1555$$Nest$mflashlightTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new FlashlightTileUserActionInteractor((FlashlightControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.flashlightControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mfontScalingTileMapper, reason: not valid java name */
    public static FontScalingTileMapper m1556$$Nest$mfontScalingTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new FontScalingTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mfontScalingTileUserActionInteractor, reason: not valid java name */
    public static FontScalingTileUserActionInteractor m1557$$Nest$mfontScalingTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new FontScalingTileUserActionInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.mainCoroutineContextProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.fontScalingDialogDelegateProvider, (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (DialogTransitionAnimator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDialogTransitionAnimatorProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get());
    }

    /* renamed from: -$$Nest$mfullMobileConnectionRepositoryFactory, reason: not valid java name */
    public static FullMobileConnectionRepository.Factory m1558$$Nest$mfullMobileConnectionRepositoryFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new FullMobileConnectionRepository.Factory((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (TableLogBufferFactory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tableLogBufferFactoryProvider.get(), new MobileConnectionRepositoryImpl.Factory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), (ConnectivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideConnectivityManagagerProvider.get(), (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideTelephonyManagerProvider.get(), (MobileInputLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mobileInputLoggerProvider.get(), (CarrierConfigRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.carrierConfigRepositoryProvider.get(), new MobileMappingsProxyImpl(), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get()), (CarrierMergedConnectionRepository.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider3.get());
    }

    /* renamed from: -$$Nest$mglanceableHubTransitions, reason: not valid java name */
    public static GlanceableHubTransitions m1559$$Nest$mglanceableHubTransitions(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new GlanceableHubTransitions((KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get(), (KeyguardTransitionRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionRepositoryImplProvider.get(), (CommunalInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.communalInteractorProvider.get());
    }

    /* renamed from: -$$Nest$mgoogleAmbientIndicationSection, reason: not valid java name */
    public static GoogleAmbientIndicationSection m1561$$Nest$mgoogleAmbientIndicationSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new GoogleAmbientIndicationSection(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardAmbientIndicationViewModel(), (AodAlphaViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.aodAlphaViewModelProvider.get(), (ShadeViewController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeSurfaceProvider.get(), (PowerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerInteractorProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider18.get());
    }

    /* renamed from: -$$Nest$mguestResetOrExitSessionReceiver, reason: not valid java name */
    public static GuestResetOrExitSessionReceiver m1562$$Nest$mguestResetOrExitSessionReceiver(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        UserTracker userTracker = (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get();
        BroadcastDispatcher broadcastDispatcher = (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get();
        SystemUIDialog.Factory factory = (SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new GuestResetOrExitSessionReceiver(userTracker, broadcastDispatcher, new GuestResetOrExitSessionReceiver.ExitSessionDialogFactory(factory, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$6) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider7.get()), new GuestResetOrExitSessionReceiver.ExitSessionDialogFactory((SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$7) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider8.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl)));
    }

    /* renamed from: -$$Nest$mguestSessionNotification, reason: not valid java name */
    public static GuestSessionNotification m1563$$Nest$mguestSessionNotification(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new GuestSessionNotification(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (NotificationManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideNotificationManagerProvider.get());
    }

    /* renamed from: -$$Nest$mheaderPrivacyIconsController, reason: not valid java name */
    public static HeaderPrivacyIconsController m1564$$Nest$mheaderPrivacyIconsController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        PrivacyItemController privacyItemController = (PrivacyItemController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.privacyItemControllerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new HeaderPrivacyIconsController(privacyItemController, (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (OngoingPrivacyChip) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesOngoingPrivacyChipProvider.get(), (PrivacyDialogController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.privacyDialogControllerProvider.get(), (PrivacyDialogControllerV2) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.privacyDialogControllerV2Provider.get(), new PrivacyLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providePrivacyLogBufferProvider.get()), (StatusIconContainer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesStatusIconContainerProvider.get(), (PermissionManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePermissionManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (AppOpsController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.appOpsControllerImplProvider.get(), (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), (SafetyCenterManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideSafetyCenterManagerProvider.get(), (DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get(), (FeatureFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get());
    }

    /* renamed from: -$$Nest$mheadsUpViewBinderLogger, reason: not valid java name */
    public static HeadsUpViewBinderLogger m1566$$Nest$mheadsUpViewBinderLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new HeadsUpViewBinderLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationHeadsUpLogBufferProvider.get());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.google.hardware.pixel.display.IDisplay] */
    /* renamed from: -$$Nest$miDisplay, reason: not valid java name */
    public static IDisplay m1567$$Nest$miDisplay(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        IDisplay.Stub.Proxy proxy;
        IBinder waitForDeclaredService = ServiceManager.waitForDeclaredService(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getString(R.string.config_lowLightDisplayHalService));
        if (waitForDeclaredService == null) {
            return null;
        }
        int i = IDisplay.Stub.$r8$clinit;
        IInterface queryLocalInterface = waitForDeclaredService.queryLocalInterface(IDisplay.DESCRIPTOR);
        if (queryLocalInterface == null || !(queryLocalInterface instanceof IDisplay)) {
            IDisplay.Stub.Proxy proxy2 = new IDisplay.Stub.Proxy();
            proxy2.mRemote = waitForDeclaredService;
            proxy = proxy2;
        } else {
            proxy = (IDisplay) queryLocalInterface;
        }
        return proxy;
    }

    /* renamed from: -$$Nest$miconBuilder, reason: not valid java name */
    public static IconBuilder m1568$$Nest$miconBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new IconBuilder(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context);
    }

    /* renamed from: -$$Nest$mincomingHeaderSectionHeaderController, reason: not valid java name */
    public static SectionHeaderNodeControllerImpl m1569$$Nest$mincomingHeaderSectionHeaderController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        SectionHeaderNodeControllerImpl headerController = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesIncomingHeaderSubcomponentProvider.get()).getHeaderController();
        Preconditions.checkNotNullFromProvides(headerController);
        return headerController;
    }

    /* renamed from: -$$Nest$minternetTileMapper, reason: not valid java name */
    public static InternetTileMapper m1571$$Nest$minternetTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new InternetTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
    }

    /* renamed from: -$$Nest$minternetTileUserActionInteractor, reason: not valid java name */
    public static InternetTileUserActionInteractor m1572$$Nest$minternetTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new InternetTileUserActionInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.mainCoroutineContextProvider.get(), (InternetDialogManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.internetDialogManagerProvider.get(), (WifiStateWorker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wifiStateWorkerProvider.get(), (AccessPointController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAccessPointControllerImplProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$missueRecordingDataInteractor, reason: not valid java name */
    public static IssueRecordingDataInteractor m1573$$Nest$missueRecordingDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new IssueRecordingDataInteractor((IssueRecordingState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.issueRecordingStateProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get());
    }

    /* renamed from: -$$Nest$missueRecordingMapper, reason: not valid java name */
    public static IssueRecordingMapper m1574$$Nest$missueRecordingMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new IssueRecordingMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$missueRecordingUserActionInteractor, reason: not valid java name */
    public static IssueRecordingUserActionInteractor m1575$$Nest$missueRecordingUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new IssueRecordingUserActionInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.mainCoroutineContextProvider.get(), (KeyguardDismissUtil) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardDismissUtilProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (DialogTransitionAnimator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDialogTransitionAnimatorProvider.get(), (PanelInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.panelInteractorImplProvider.get(), (UserContextProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$50) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider51.get(), (RecordingController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.recordingControllerProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardBouncerViewModel, reason: not valid java name */
    public static KeyguardBouncerViewModel m1578$$Nest$mkeyguardBouncerViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardBouncerViewModel((BouncerViewImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bouncerViewImplProvider.get(), (PrimaryBouncerInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerInteractorProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardClockPositionAlgorithm, reason: not valid java name */
    public static KeyguardClockPositionAlgorithm m1579$$Nest$mkeyguardClockPositionAlgorithm(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        LogBuffer logBuffer = (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardClockLogProvider.get();
        KeyguardClockPositionAlgorithm keyguardClockPositionAlgorithm = new KeyguardClockPositionAlgorithm();
        keyguardClockPositionAlgorithm.mCutoutTopInset = 0;
        new Logger(logBuffer, "KeyguardClockPositionAlgorithm");
        return keyguardClockPositionAlgorithm;
    }

    /* renamed from: -$$Nest$mkeyguardLogger, reason: not valid java name */
    public static KeyguardLogger m1581$$Nest$mkeyguardLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardMediaControllerLogger, reason: not valid java name */
    public static KeyguardMediaControllerLogger m1582$$Nest$mkeyguardMediaControllerLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardMediaControllerLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardMediaControllerLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardMessageAreaControllerFactory, reason: not valid java name */
    public static KeyguardMessageAreaController.Factory m1583$$Nest$mkeyguardMessageAreaControllerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardMessageAreaController.Factory((KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardQuickAffordanceProviderClientFactoryImpl, reason: not valid java name */
    public static KeyguardQuickAffordanceProviderClientFactoryImpl m1584$$Nest$mkeyguardQuickAffordanceProviderClientFactoryImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardQuickAffordanceProviderClientFactoryImpl((UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardSliceViewSection, reason: not valid java name */
    public static KeyguardSliceViewSection m1585$$Nest$mkeyguardSliceViewSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardSliceViewSection((LockscreenSmartspaceController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenSmartspaceControllerProvider.get());
    }

    /* renamed from: -$$Nest$mkeyguardTransitionAnimationLogger, reason: not valid java name */
    public static KeyguardTransitionAnimationLogger m1586$$Nest$mkeyguardTransitionAnimationLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new KeyguardTransitionAnimationLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideKeyguardTransitionAnimationLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mlSShadeTransitionLogger, reason: not valid java name */
    public static LSShadeTransitionLogger m1587$$Nest$mlSShadeTransitionLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new LSShadeTransitionLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLSShadeTransitionControllerBufferProvider.get(), (LockscreenGestureLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenGestureLoggerProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m976$$Nest$mdisplayMetrics(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl));
    }

    /* renamed from: -$$Nest$mlocationTileDataInteractor, reason: not valid java name */
    public static LocationTileDataInteractor m1588$$Nest$mlocationTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new LocationTileDataInteractor((LocationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.locationControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mlocationTileMapper, reason: not valid java name */
    public static LocationTileMapper m1589$$Nest$mlocationTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new LocationTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mlocationTileUserActionInteractor, reason: not valid java name */
    public static LocationTileUserActionInteractor m1590$$Nest$mlocationTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new LocationTileUserActionInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (LocationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.locationControllerImplProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mlockscreenPrecondition, reason: not valid java name */
    public static LockscreenPrecondition m1591$$Nest$mlockscreenPrecondition(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new LockscreenPrecondition((DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get(), (ExecutionImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideExecutionProvider.get());
    }

    /* renamed from: -$$Nest$mlockscreenShadeScrimTransitionController, reason: not valid java name */
    public static LockscreenShadeScrimTransitionController m1592$$Nest$mlockscreenShadeScrimTransitionController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        ScrimController scrimController = (ScrimController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.scrimControllerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new LockscreenShadeScrimTransitionController(scrimController, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), (SplitShadeStateControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeStateControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mlockscreenTargetFilter, reason: not valid java name */
    public static LockscreenTargetFilter m1593$$Nest$mlockscreenTargetFilter(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        SecureSettings secureSettings = (SecureSettings) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider.get();
        UserTracker userTracker = (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new LockscreenTargetFilter(secureSettings, userTracker, (ExecutionImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideExecutionProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get(), (ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mlowLightClockAnimationProvider, reason: not valid java name */
    public static LowLightClockAnimationProvider m1594$$Nest$mlowLightClockAnimationProvider(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        int dimensionPixelOffset = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl).getDimensionPixelOffset(R.dimen.low_light_clock_translate_animation_offset);
        Resources resources = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        long integer = resources.getInteger(R.integer.low_light_clock_translate_animation_duration_ms);
        Resources resources2 = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources();
        Preconditions.checkNotNullFromProvides(resources2);
        long integer2 = resources2.getInteger(R.integer.low_light_clock_alpha_animation_in_start_delay_ms);
        Preconditions.checkNotNullFromProvides(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources());
        return new LowLightClockAnimationProvider(dimensionPixelOffset, integer, integer2, r9.getInteger(R.integer.low_light_clock_alpha_animation_duration_ms));
    }

    /* renamed from: -$$Nest$mmainMessageRouter, reason: not valid java name */
    public static MessageRouterImpl m1595$$Nest$mmainMessageRouter(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new MessageRouterImpl((DelayableExecutor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideMainDelayableExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mmapOfClassOfAndProviderOfActivity, reason: not valid java name */
    public static Map m1596$$Nest$mmapOfClassOfAndProviderOfActivity(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        MapBuilder mapBuilder = new MapBuilder(33);
        mapBuilder.contributions.put(ControlsProviderSelectorActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.controlsProviderSelectorActivityProvider);
        mapBuilder.contributions.put(ControlsFavoritingActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.controlsFavoritingActivityProvider);
        mapBuilder.contributions.put(ControlsEditingActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.controlsEditingActivityProvider);
        mapBuilder.contributions.put(ControlsRequestDialog.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.controlsRequestDialogProvider);
        mapBuilder.contributions.put(ControlsActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.controlsActivityProvider);
        mapBuilder.contributions.put(MediaProjectionAppSelectorActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaProjectionAppSelectorActivityProvider);
        mapBuilder.contributions.put(MediaProjectionPermissionActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaProjectionPermissionActivityProvider);
        mapBuilder.contributions.put(LaunchNoteTaskActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.launchNoteTaskActivityProvider);
        mapBuilder.contributions.put(LaunchNotesRoleSettingsTrampolineActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.launchNotesRoleSettingsTrampolineActivityProvider);
        mapBuilder.contributions.put(CreateNoteTaskShortcutActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.createNoteTaskShortcutActivityProvider);
        mapBuilder.contributions.put(WalletActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.walletActivityProvider);
        mapBuilder.contributions.put(TunerActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerActivityProvider);
        mapBuilder.contributions.put(ForegroundServicesDialog.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.foregroundServicesDialogProvider);
        mapBuilder.contributions.put(WorkLockActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.workLockActivityProvider);
        mapBuilder.contributions.put(BrightnessDialog.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.brightnessDialogProvider);
        mapBuilder.contributions.put(UsbDebuggingActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbDebuggingActivityProvider);
        mapBuilder.contributions.put(UsbDebuggingSecondaryUserActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbDebuggingSecondaryUserActivityProvider);
        mapBuilder.contributions.put(UsbPermissionActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbPermissionActivityProvider);
        mapBuilder.contributions.put(UsbConfirmActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbConfirmActivityProvider);
        mapBuilder.contributions.put(UsbAccessoryUriActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbAccessoryUriActivityProvider);
        mapBuilder.contributions.put(CreateUserActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.createUserActivityProvider);
        mapBuilder.contributions.put(PeopleSpaceActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.peopleSpaceActivityProvider);
        mapBuilder.contributions.put(LongScreenshotActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.longScreenshotActivityProvider);
        mapBuilder.contributions.put(AppClipsTrampolineActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.appClipsTrampolineActivityProvider);
        mapBuilder.contributions.put(AppClipsActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.appClipsActivityProvider);
        mapBuilder.contributions.put(LaunchConversationActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.launchConversationActivityProvider);
        mapBuilder.contributions.put(SensorUseStartedActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sensorUseStartedActivityProvider);
        mapBuilder.contributions.put(EditWidgetsActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.editWidgetsActivityProvider);
        mapBuilder.contributions.put(SwitchToManagedProfileForCallActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.switchToManagedProfileForCallActivityProvider);
        mapBuilder.contributions.put(KeyboardTouchpadTutorialActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyboardTouchpadTutorialActivityProvider);
        mapBuilder.contributions.put(QSActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSActivityProvider);
        mapBuilder.contributions.put(TouchpadTutorialActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.touchpadTutorialActivityProvider);
        mapBuilder.contributions.put(ShortcutHelperActivity.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shortcutHelperActivityProvider);
        return mapBuilder.build();
    }

    /* renamed from: -$$Nest$mmapOfClassOfAndProviderOfBroadcastReceiver, reason: not valid java name */
    public static Map m1597$$Nest$mmapOfClassOfAndProviderOfBroadcastReceiver(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        MapBuilder mapBuilder = new MapBuilder(9);
        mapBuilder.contributions.put(SmartActionsReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartActionsReceiverProvider);
        mapBuilder.contributions.put(MediaOutputDialogReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaOutputDialogReceiverProvider);
        mapBuilder.contributions.put(PeopleSpaceWidgetPinnedReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.peopleSpaceWidgetPinnedReceiverProvider);
        mapBuilder.contributions.put(PeopleSpaceWidgetProvider.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.peopleSpaceWidgetProvider);
        mapBuilder.contributions.put(GuestResetOrExitSessionReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.guestResetOrExitSessionReceiverProvider);
        mapBuilder.contributions.put(HearingDevicesDialogReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.hearingDevicesDialogReceiverProvider);
        mapBuilder.contributions.put(ExtraDimDialogReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.extraDimDialogReceiverProvider);
        mapBuilder.contributions.put(KeyboardShortcutsReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyboardShortcutsReceiverProvider);
        mapBuilder.contributions.put(HealthUpdateReceiver.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.healthUpdateReceiverProvider);
        return mapBuilder.build();
    }

    /* renamed from: -$$Nest$mmapOfClassOfAndProviderOfService, reason: not valid java name */
    public static Map m1599$$Nest$mmapOfClassOfAndProviderOfService(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        MapBuilder mapBuilder = new MapBuilder(24);
        mapBuilder.contributions.put(SystemUISecondaryUserService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemUISecondaryUserServiceProvider);
        mapBuilder.contributions.put(HomeControlsDreamService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.homeControlsDreamServiceProvider);
        mapBuilder.contributions.put(TakeScreenshotService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.takeScreenshotServiceProvider);
        mapBuilder.contributions.put(AppClipsScreenshotHelperService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.appClipsScreenshotHelperServiceProvider);
        mapBuilder.contributions.put(AppClipsService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.appClipsServiceProvider);
        mapBuilder.contributions.put(ScreenshotProxyService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenshotProxyServiceProvider);
        mapBuilder.contributions.put(NoteTaskControllerUpdateService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.noteTaskControllerUpdateServiceProvider);
        mapBuilder.contributions.put(NoteTaskBubblesController.NoteTaskBubblesService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.noteTaskBubblesServiceProvider);
        mapBuilder.contributions.put(WalletContextualLocationsService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.walletContextualLocationsServiceProvider);
        mapBuilder.contributions.put(DozeService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozeServiceProvider);
        mapBuilder.contributions.put(ImageWallpaper.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.imageWallpaperProvider);
        mapBuilder.contributions.put(KeyguardService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardServiceProvider);
        mapBuilder.contributions.put(DreamOverlayService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamOverlayServiceProvider);
        mapBuilder.contributions.put(NotificationListenerWithPlugins.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationListenerWithPluginsProvider);
        mapBuilder.contributions.put(SystemUIService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemUIServiceProvider);
        mapBuilder.contributions.put(SystemUIAuxiliaryDumpService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemUIAuxiliaryDumpServiceProvider);
        mapBuilder.contributions.put(RecordingService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.recordingServiceProvider);
        mapBuilder.contributions.put(IssueRecordingService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.issueRecordingServiceProvider);
        mapBuilder.contributions.put(ColumbusTargetRequestService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.columbusTargetRequestServiceProvider);
        mapBuilder.contributions.put(LowLightClockDreamService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lowLightClockDreamServiceProvider);
        mapBuilder.contributions.put(LockscreenWallpaperDreamService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenWallpaperDreamServiceProvider);
        mapBuilder.contributions.put(LauncherTileService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.launcherTileServiceProvider);
        mapBuilder.contributions.put(HealthService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.healthServiceProvider);
        mapBuilder.contributions.put(BatteryEventService.class, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.batteryEventServiceProvider);
        return mapBuilder.build();
    }

    /* renamed from: -$$Nest$mmapOfStringAndProviderOfQSTileViewModel, reason: not valid java name */
    public static void m1600$$Nest$mmapOfStringAndProviderOfQSTileViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        MapBuilder mapBuilder = new MapBuilder(22);
        mapBuilder.contributions.put("airplane", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAirplaneModeTileViewModelProvider);
        mapBuilder.contributions.put("saver", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDataSaverTileViewModelProvider);
        mapBuilder.contributions.put("internet", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideInternetTileViewModelProvider);
        mapBuilder.contributions.put("flashlight", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideFlashlightTileViewModelProvider);
        mapBuilder.contributions.put("location", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLocationTileViewModelProvider);
        mapBuilder.contributions.put("alarm", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAlarmTileViewModelProvider);
        mapBuilder.contributions.put("dark", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUiModeNightTileViewModelProvider);
        mapBuilder.contributions.put("work", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideWorkModeTileViewModelProvider);
        mapBuilder.contributions.put("cameratoggle", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCameraToggleTileViewModelProvider);
        mapBuilder.contributions.put("mictoggle", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideMicrophoneToggleTileViewModelProvider);
        mapBuilder.contributions.put("dnd", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideModesTileViewModelProvider);
        mapBuilder.contributions.put("qr_code_scanner", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideQRCodeScannerTileViewModelProvider);
        mapBuilder.contributions.put("record_issue", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideIssueRecordingTileViewModelProvider);
        mapBuilder.contributions.put("screenrecord", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideScreenRecordTileViewModelProvider);
        mapBuilder.contributions.put("color_correction", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideColorCorrectionTileViewModelProvider);
        mapBuilder.contributions.put("inversion", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideColorInversionTileViewModelProvider);
        mapBuilder.contributions.put("font_scaling", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideFontScalingTileViewModelProvider);
        mapBuilder.contributions.put("reduce_brightness", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideReduceBrightColorsTileViewModelProvider);
        mapBuilder.contributions.put("onehanded", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideOneHandedModeTileViewModelProvider);
        mapBuilder.contributions.put("night", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNightDisplayTileViewModelProvider);
        mapBuilder.contributions.put("battery", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatterySaverTileViewModelProvider);
        mapBuilder.contributions.put("rotation", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideRotationTileViewModelProvider);
        mapBuilder.build();
    }

    /* renamed from: -$$Nest$mmapOfStringAndQSTileAvailabilityInteractor, reason: not valid java name */
    public static Map m1601$$Nest$mmapOfStringAndQSTileAvailabilityInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        MapBuilder mapBuilder = new MapBuilder(20);
        mapBuilder.contributions.put("cameratoggle", ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$48) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider49.get()).create(2));
        mapBuilder.contributions.put("mictoggle", ((DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$48) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider49.get()).create(1));
        mapBuilder.contributions.put("airplane", new AirplaneModeTileDataInteractor((AirplaneModeRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.airplaneModeRepositoryImplProvider.get()));
        mapBuilder.contributions.put("saver", new DataSaverTileDataInteractor((DataSaverControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDataSaverControllerProvider.get()));
        mapBuilder.contributions.put("internet", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.internetTileDataInteractor());
        mapBuilder.contributions.put("flashlight", new FlashlightTileDataInteractor((FlashlightControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.flashlightControllerImplProvider.get()));
        mapBuilder.contributions.put("location", new LocationTileDataInteractor((LocationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.locationControllerImplProvider.get()));
        mapBuilder.contributions.put("alarm", new AlarmTileDataInteractor((NextAlarmController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.nextAlarmControllerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dateFormatUtil()));
        mapBuilder.contributions.put("dark", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiModeNightTileDataInteractor());
        mapBuilder.contributions.put("work", new WorkModeTileDataInteractor((ManagedProfileController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.managedProfileControllerImplProvider.get()));
        mapBuilder.contributions.put("qr_code_scanner", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qRCodeScannerTileDataInteractor());
        mapBuilder.contributions.put("screenrecord", new ScreenRecordTileDataInteractor((ScreenRecordRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenRecordRepositoryImplProvider.get()));
        mapBuilder.contributions.put("color_correction", new ColorCorrectionTileDataInteractor((ColorCorrectionRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorCorrectionRepositoryImplProvider.get()));
        mapBuilder.contributions.put("inversion", new ColorCorrectionTileDataInteractor((ColorCorrectionRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.colorCorrectionRepositoryImplProvider.get()));
        mapBuilder.contributions.put("font_scaling", new FontScalingTileDataInteractor());
        mapBuilder.contributions.put("reduce_brightness", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.reduceBrightColorsTileDataInteractor());
        mapBuilder.contributions.put("onehanded", new OneHandedModeTileDataInteractor((OneHandedModeRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.oneHandedModeRepositoryImplProvider.get()));
        mapBuilder.contributions.put("night", new NightDisplayTileDataInteractor((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dateFormatUtil(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.nightDisplayRepository()));
        mapBuilder.contributions.put("battery", new BatterySaverTileDataInteractorGoogle((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (BatteryController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBatteryControllerProvider.get()));
        mapBuilder.contributions.put("rotation", daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.rotationLockTileDataInteractor());
        return mapBuilder.build();
    }

    /* renamed from: -$$Nest$mmapOfStringAndQSTileConfig, reason: not valid java name */
    public static Map m1602$$Nest$mmapOfStringAndQSTileConfig(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        MapBuilder mapBuilder = new MapBuilder(30);
        QsEventLoggerImpl qsEventLoggerImpl = (QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get();
        TileSpec create = TileSpec.Companion.create("airplane");
        QSTileUIConfig.Resource resource = new QSTileUIConfig.Resource(R.drawable.qs_airplane_icon_off, R.string.airplane_mode);
        InstanceId newInstanceId = qsEventLoggerImpl.sequence.newInstanceId();
        QSTilePolicy.Restricted restricted = new QSTilePolicy.Restricted(Collections.singletonList("no_airplane_mode"));
        TileCategory tileCategory = TileCategory.CONNECTIVITY;
        mapBuilder.contributions.put("airplane", new QSTileConfig(create, resource, newInstanceId, tileCategory, null, restricted, 80));
        mapBuilder.contributions.put("saver", new QSTileConfig(TileSpec.Companion.create("saver"), new QSTileUIConfig.Resource(R.drawable.qs_data_saver_icon_off, R.string.data_saver), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory, null, null, 112));
        mapBuilder.contributions.put("internet", new QSTileConfig(TileSpec.Companion.create("internet"), new QSTileUIConfig.Resource(R.drawable.ic_qs_no_internet_available, R.string.quick_settings_internet_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory, null, null, 112));
        mapBuilder.contributions.put("hotspot", new QSTileConfig(TileSpec.Companion.create("hotspot"), new QSTileUIConfig.Resource(R.drawable.ic_hotspot, R.string.quick_settings_hotspot_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory, null, null, 112));
        mapBuilder.contributions.put("cast", new QSTileConfig(TileSpec.Companion.create("cast"), new QSTileUIConfig.Resource(R.drawable.ic_cast, R.string.quick_settings_cast_title), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory, null, null, 112));
        mapBuilder.contributions.put("bt", new QSTileConfig(TileSpec.Companion.create("bt"), new QSTileUIConfig.Resource(R.drawable.qs_bluetooth_icon_off, R.string.quick_settings_bluetooth_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory, null, null, 112));
        QsEventLoggerImpl qsEventLoggerImpl2 = (QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get();
        TileSpec create2 = TileSpec.Companion.create("controls");
        QSTileUIConfig.Resource resource2 = new QSTileUIConfig.Resource(R.drawable.controls_icon, R.string.quick_controls_title);
        InstanceId newInstanceId2 = qsEventLoggerImpl2.sequence.newInstanceId();
        TileCategory tileCategory2 = TileCategory.UTILITIES;
        mapBuilder.contributions.put("controls", new QSTileConfig(create2, resource2, newInstanceId2, tileCategory2, null, null, 112));
        QsEventLoggerImpl qsEventLoggerImpl3 = (QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get();
        TileSpec create3 = TileSpec.Companion.create(BcSmartspaceDataPlugin.UI_SURFACE_DREAM);
        mapBuilder.contributions.put(BcSmartspaceDataPlugin.UI_SURFACE_DREAM, new QSTileConfig(create3, new QSTileUIConfig.Resource(R.drawable.ic_qs_screen_saver, R.string.quick_settings_screensaver_label), qsEventLoggerImpl3.sequence.newInstanceId(), create3.getSpec()));
        mapBuilder.contributions.put("flashlight", new QSTileConfig(TileSpec.Companion.create("flashlight"), new QSTileUIConfig.Resource(R.drawable.qs_flashlight_icon_off, R.string.quick_settings_flashlight_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory2, null, null, 112));
        QsEventLoggerImpl qsEventLoggerImpl4 = (QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get();
        TileSpec create4 = TileSpec.Companion.create("location");
        QSTileUIConfig.Resource resource3 = new QSTileUIConfig.Resource(R.drawable.qs_location_icon_off, R.string.quick_settings_location_label);
        InstanceId newInstanceId3 = qsEventLoggerImpl4.sequence.newInstanceId();
        QSTilePolicy.Restricted restricted2 = new QSTilePolicy.Restricted(CollectionsKt__CollectionsKt.listOf("no_share_location", "no_config_location"));
        TileCategory tileCategory3 = TileCategory.PRIVACY;
        mapBuilder.contributions.put("location", new QSTileConfig(create4, resource3, newInstanceId3, tileCategory3, null, restricted2, 80));
        mapBuilder.contributions.put("alarm", new QSTileConfig(TileSpec.Companion.create("alarm"), new QSTileUIConfig.Resource(R.drawable.ic_alarm, R.string.status_bar_alarm), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory2, null, null, 112));
        QsEventLoggerImpl qsEventLoggerImpl5 = (QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get();
        TileSpec create5 = TileSpec.Companion.create("dark");
        QSTileUIConfig.Resource resource4 = new QSTileUIConfig.Resource(R.drawable.qs_light_dark_theme_icon_off, R.string.quick_settings_ui_mode_night_label);
        InstanceId newInstanceId4 = qsEventLoggerImpl5.sequence.newInstanceId();
        TileCategory tileCategory4 = TileCategory.DISPLAY;
        mapBuilder.contributions.put("dark", new QSTileConfig(create5, resource4, newInstanceId4, tileCategory4, null, null, 112));
        mapBuilder.contributions.put("work", new QSTileConfig(TileSpec.Companion.create("work"), new QSTileUIConfig.Resource(android.R.drawable.statusbar_background, R.string.quick_settings_work_mode_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory3, null, null, 48));
        mapBuilder.contributions.put("cameratoggle", new QSTileConfig(TileSpec.Companion.create("cameratoggle"), new QSTileUIConfig.Resource(R.drawable.qs_camera_access_icon_off, R.string.quick_settings_camera_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory3, null, new QSTilePolicy.Restricted(Collections.singletonList("disallow_camera_toggle")), 80));
        mapBuilder.contributions.put("mictoggle", new QSTileConfig(TileSpec.Companion.create("mictoggle"), new QSTileUIConfig.Resource(R.drawable.qs_mic_access_off, R.string.quick_settings_mic_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory3, null, new QSTilePolicy.Restricted(Collections.singletonList("disallow_microphone_toggle")), 80));
        mapBuilder.contributions.put("dnd", new QSTileConfig(TileSpec.Companion.create("dnd"), new QSTileUIConfig.Resource(R.drawable.qs_dnd_icon_off, R.string.quick_settings_dnd_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory, null, null, 112));
        mapBuilder.contributions.put("qr_code_scanner", new QSTileConfig(TileSpec.Companion.create("qr_code_scanner"), new QSTileUIConfig.Resource(R.drawable.ic_qr_code_scanner, R.string.qr_code_scanner_title), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory2, null, null, 112));
        mapBuilder.contributions.put("record_issue", new QSTileConfig(TileSpec.Companion.create("record_issue"), new QSTileUIConfig.Resource(R.drawable.qs_record_issue_icon_off, R.string.qs_record_issue_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory2, null, null, 112));
        mapBuilder.contributions.put("screenrecord", new QSTileConfig(TileSpec.Companion.create("screenrecord"), new QSTileUIConfig.Resource(R.drawable.qs_screen_record_icon_off, R.string.quick_settings_screen_record_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory4, null, null, 112));
        QsEventLoggerImpl qsEventLoggerImpl6 = (QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get();
        TileSpec create6 = TileSpec.Companion.create("wallet");
        mapBuilder.contributions.put("wallet", new QSTileConfig(create6, new QSTileUIConfig.Resource(R.drawable.ic_wallet_lockscreen, R.string.wallet_title), qsEventLoggerImpl6.sequence.newInstanceId(), create6.getSpec()));
        QsEventLoggerImpl qsEventLoggerImpl7 = (QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get();
        TileSpec create7 = TileSpec.Companion.create("color_correction");
        QSTileUIConfig.Resource resource5 = new QSTileUIConfig.Resource(R.drawable.ic_qs_color_correction, R.string.quick_settings_color_correction_label);
        InstanceId newInstanceId5 = qsEventLoggerImpl7.sequence.newInstanceId();
        TileCategory tileCategory5 = TileCategory.ACCESSIBILITY;
        mapBuilder.contributions.put("color_correction", new QSTileConfig(create7, resource5, newInstanceId5, tileCategory5, null, null, 112));
        mapBuilder.contributions.put("inversion", new QSTileConfig(TileSpec.Companion.create("inversion"), new QSTileUIConfig.Resource(R.drawable.qs_invert_colors_icon_off, R.string.quick_settings_inversion_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory5, null, null, 112));
        mapBuilder.contributions.put("font_scaling", new QSTileConfig(TileSpec.Companion.create("font_scaling"), new QSTileUIConfig.Resource(R.drawable.ic_qs_font_scaling, R.string.quick_settings_font_scaling_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory4, null, null, 112));
        mapBuilder.contributions.put("reduce_brightness", new QSTileConfig(TileSpec.Companion.create("reduce_brightness"), new QSTileUIConfig.Resource(R.drawable.qs_extra_dim_icon_on, android.R.string.resolver_turn_on_work_apps), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory4, null, null, 112));
        mapBuilder.contributions.put("hearing_devices", new QSTileConfig(TileSpec.Companion.create("hearing_devices"), new QSTileUIConfig.Resource(R.drawable.qs_hearing_devices_icon, R.string.quick_settings_hearing_devices_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory5, null, null, 112));
        mapBuilder.contributions.put("onehanded", new QSTileConfig(TileSpec.Companion.create("onehanded"), new QSTileUIConfig.Resource(android.R.drawable.ic_qs_airplane, R.string.quick_settings_onehanded_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory5, null, null, 112));
        mapBuilder.contributions.put("night", new QSTileConfig(TileSpec.Companion.create("night"), new QSTileUIConfig.Resource(R.drawable.qs_nightlight_icon_off, R.string.quick_settings_night_display_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory4, null, null, 112));
        mapBuilder.contributions.put("battery", new QSTileConfig(TileSpec.Companion.create("battery"), new QSTileUIConfig.Resource(R.drawable.qs_battery_saver_icon_off, R.string.battery_detail_switch_title), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory2, null, null, 112));
        mapBuilder.contributions.put("reverse", new QSTileConfig(TileSpec.Companion.create("reverse"), new QSTileUIConfig.Resource(R.drawable.qs_battery_share_icon_off, R.string.reverse_charging_title), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory2, null, null, 112));
        mapBuilder.contributions.put("rotation", new QSTileConfig(TileSpec.Companion.create("rotation"), new QSTileUIConfig.Resource(R.drawable.qs_auto_rotate_icon_off, R.string.quick_settings_rotation_unlocked_label), ((QsEventLoggerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qsEventLoggerImplProvider.get()).sequence.newInstanceId(), tileCategory4, null, null, 112));
        return mapBuilder.build();
    }

    /* renamed from: -$$Nest$mmediaDeviceManager, reason: not valid java name */
    public static MediaDeviceManager m1603$$Nest$mmediaDeviceManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        return new MediaDeviceManager(context, new MediaControllerFactory(context), new LocalMediaManagerFactory(context, (LocalBluetoothManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLocalBluetoothControllerProvider.get()), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMediaRouter2ManagerProvider), (MediaMuteAwaitConnectionManagerFactory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaMuteAwaitConnectionManagerFactoryProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideLocalBluetoothControllerProvider), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), new MediaDeviceLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesMediaDeviceLogBufferProvider.get()));
    }

    /* renamed from: -$$Nest$mmediaTttReceiverRippleController, reason: not valid java name */
    public static MediaTttReceiverRippleController m1604$$Nest$mmediaTttReceiverRippleController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new MediaTttReceiverRippleController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), (MediaTttReceiverLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaTttReceiverLoggerProvider.get());
    }

    /* renamed from: -$$Nest$mminimumTilesInteractor, reason: not valid java name */
    public static MinimumTilesInteractor m1605$$Nest$mminimumTilesInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new MinimumTilesInteractor((MinimumTilesResourceRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.minimumTilesResourceRepositoryProvider.get());
    }

    /* renamed from: -$$Nest$mnamedBoolean, reason: not valid java name */
    public static Boolean m1606$$Nest$mnamedBoolean(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        try {
            return Boolean.valueOf(((PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get()).getServiceInfo(new ComponentName(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (Class<?>) DreamOverlayService.class), 128).enabled);
        } catch (PackageManager.NameNotFoundException unused) {
            return Boolean.FALSE;
        }
    }

    /* renamed from: -$$Nest$mnamedBoolean4, reason: not valid java name */
    public static boolean m1609$$Nest$mnamedBoolean4(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        try {
            return DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getIntArray(android.R.array.config_fontManagerServiceCerts).length != 0;
        } catch (Resources.NotFoundException unused) {
            return false;
        }
    }

    /* renamed from: -$$Nest$mnamedComponentName2, reason: not valid java name */
    public static ComponentName m1610$$Nest$mnamedComponentName2(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        String string = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context.getResources().getString(R.string.config_lowLightDreamComponent);
        if (string.length() == 0) {
            return null;
        }
        return ComponentName.unflattenFromString(string);
    }

    /* renamed from: -$$Nest$mnamedComponentName3, reason: not valid java name */
    public static ComponentName m1611$$Nest$mnamedComponentName3(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        String string = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context.getResources().getString(R.string.config_homePanelDreamComponent);
        if (string.isEmpty()) {
            return null;
        }
        return ComponentName.unflattenFromString(string);
    }

    /* renamed from: -$$Nest$mnamedListOfString, reason: not valid java name */
    public static List m1612$$Nest$mnamedListOfString(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        List list = ArraysKt.toList(((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get()).getResources().getStringArray(android.R.array.config_longPressOnPowerDurationSettings));
        Preconditions.checkNotNullFromProvides(list);
        return list;
    }

    /* renamed from: -$$Nest$mnamedSetOfFalsingClassifier, reason: not valid java name */
    public static Set m1613$$Nest$mnamedSetOfFalsingClassifier(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        HashSet hashSet = new HashSet(Arrays.asList(new PointerCountClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get()), new TypeClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get()), new DiagonalClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get()), new DistanceClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get()), new ProximityClassifier(new DistanceClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get()), (FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get()), new ZigZagClassifier((FalsingDataProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingDataProvider.get(), (DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get())));
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), "Set contributions cannot be null");
        }
        arrayList.addAll(hashSet);
        return arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$mnamedSetOfFeedbackEffect2, reason: not valid java name */
    public static Set m1614$$Nest$mnamedSetOfFeedbackEffect2(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        Set of = SetsKt.setOf((HapticClick) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.hapticClickProvider.get(), (UserActivity) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userActivityProvider.get());
        Preconditions.checkNotNullFromProvides(of);
        Set set = of;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), "Set contributions cannot be null");
        }
        arrayList.addAll(set);
        return arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$mnamedSetOfGate, reason: not valid java name */
    public static Set m1615$$Nest$mnamedSetOfGate(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        Set of = SetsKt.setOf((FlagEnabled) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.flagEnabledProvider.get(), (KeyguardProximity) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardProximityProvider.get(), (SetupWizard) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setupWizardProvider2.get(), (TelephonyActivity) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.telephonyActivityProvider.get(), (CameraVisibility) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.cameraVisibilityProvider2.get(), (PowerSaveState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerSaveStateProvider.get(), (PowerState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.powerStateProvider.get());
        Preconditions.checkNotNullFromProvides(of);
        Set set = of;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), "Set contributions cannot be null");
        }
        arrayList.addAll(set);
        return arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$mnamedSetOfGate2, reason: not valid java name */
    public static Set m1616$$Nest$mnamedSetOfGate2(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(1);
        Set of = SetsKt.setOf((ChargingState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.chargingStateProvider2.get(), (UsbState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.usbStateProvider2.get(), (SystemKeyPress) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemKeyPressProvider.get(), (ScreenTouch) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenTouchProvider.get());
        Preconditions.checkNotNullFromProvides(of);
        Set set = of;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), "Set contributions cannot be null");
        }
        arrayList.addAll(set);
        return arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$mnamedString, reason: not valid java name */
    public static String m1617$$Nest$mnamedString(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        ((DeviceConfigProxy) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.deviceConfigProxyProvider.get()).getClass();
        String string = DeviceConfig.getString("systemui", "back_gesture_ml_model_name", "backgesture");
        Preconditions.checkNotNullFromProvides(string);
        return string;
    }

    /* renamed from: -$$Nest$mnamedString3, reason: not valid java name */
    public static String m1618$$Nest$mnamedString3(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        String string = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getString(R.string.app_label);
        Preconditions.checkNotNullFromProvides(string);
        return string;
    }

    /* renamed from: -$$Nest$mnamedString4, reason: not valid java name */
    public static String m1619$$Nest$mnamedString4(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        String string = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getString(R.string.launcher_overlayable_package);
        Preconditions.checkNotNullFromProvides(string);
        return string;
    }

    /* renamed from: -$$Nest$mnamedString5, reason: not valid java name */
    public static String m1620$$Nest$mnamedString5(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        String string = DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl).getString(R.string.themepicker_overlayable_package);
        Preconditions.checkNotNullFromProvides(string);
        return string;
    }

    /* renamed from: -$$Nest$mnamedTouchInsetManager, reason: not valid java name */
    public static TouchInsetManager m1621$$Nest$mnamedTouchInsetManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new TouchInsetManager((Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mnewsHeaderSectionHeaderController, reason: not valid java name */
    public static SectionHeaderNodeControllerImpl m1622$$Nest$mnewsHeaderSectionHeaderController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        SectionHeaderNodeControllerImpl headerController = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesNewsHeaderSubcomponentProvider.get()).getHeaderController();
        Preconditions.checkNotNullFromProvides(headerController);
        return headerController;
    }

    /* renamed from: -$$Nest$mnoteTaskEventLogger, reason: not valid java name */
    public static NoteTaskEventLogger m1625$$Nest$mnoteTaskEventLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NoteTaskEventLogger((UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get());
    }

    /* renamed from: -$$Nest$mnoteTaskInfoResolver, reason: not valid java name */
    public static NoteTaskInfoResolver m1626$$Nest$mnoteTaskInfoResolver(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new NoteTaskInfoResolver((RoleManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideRoleManagerProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get());
    }

    /* renamed from: -$$Nest$mnoteTaskInitializer, reason: not valid java name */
    public static NoteTaskInitializer m1627$$Nest$mnoteTaskInitializer(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        NoteTaskController noteTaskController = (NoteTaskController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.noteTaskControllerProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new NoteTaskInitializer(noteTaskController, (RoleManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideRoleManagerProvider.get(), (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.setBubbles, (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (InputManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInputManagerProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.noteTaskEnabledKeyBoolean());
    }

    /* renamed from: -$$Nest$mnotifCollectionLogger, reason: not valid java name */
    public static NotifCollectionLogger m1628$$Nest$mnotifCollectionLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotifCollectionLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationsLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mnotificationIconContainerStatusBarViewBinder, reason: not valid java name */
    public static NotificationIconContainerStatusBarViewBinder m1630$$Nest$mnotificationIconContainerStatusBarViewBinder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationIconContainerStatusBarViewBinder(new NotificationIconContainerStatusBarViewModel((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), new DarkIconInteractor((DarkIconRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.darkIconRepositoryImplProvider.get()), new StatusBarNotificationIconsInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationIconsInteractor(), (NotificationListenerSettingsRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationListenerSettingsRepositoryProvider.get()), new HeadsUpNotificationIconInteractor((HeadsUpNotificationIconViewStateRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.headsUpNotificationIconViewStateRepositoryProvider.get()), (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get(), DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get()), (ConfigurationState) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationStateProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.systemBarUtilsState(), (StatusBarIconViewBindingFailureTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarIconViewBindingFailureTrackerProvider.get(), new StatusBarNotificationIconViewStore((NotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifCollectionProvider.get()));
    }

    /* renamed from: -$$Nest$mnotificationInterruptLogger, reason: not valid java name */
    public static NotificationInterruptLogger m1631$$Nest$mnotificationInterruptLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationInterruptLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationInterruptLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mnotificationRowBinderLogger, reason: not valid java name */
    public static NotificationRowBinderLogger m1632$$Nest$mnotificationRowBinderLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationRowBinderLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotifInflationLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mnotificationWakeUpCoordinatorLogger, reason: not valid java name */
    public static NotificationWakeUpCoordinatorLogger m1633$$Nest$mnotificationWakeUpCoordinatorLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new NotificationWakeUpCoordinatorLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationLockScreenLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$moperatorNameViewControllerFactory, reason: not valid java name */
    public static OperatorNameViewController.Factory m1634$$Nest$moperatorNameViewControllerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new OperatorNameViewController.Factory((DarkIconDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.darkIconDispatcherImplProvider.get(), (TunerService) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.tunerServiceImplProvider.get(), (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideTelephonyManagerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (CarrierConfigTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.carrierConfigTrackerProvider.get(), (AirplaneModeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.airplaneModeInteractorProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.subscriptionManagerProxyImpl(), (JavaAdapter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.javaAdapterProvider.get());
    }

    /* renamed from: -$$Nest$mpanelConfirmationDialogFactory, reason: not valid java name */
    public static PanelConfirmationDialogFactory m1637$$Nest$mpanelConfirmationDialogFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new PanelConfirmationDialogFactory((SystemUIDialog.Factory) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider6.get());
    }

    /* renamed from: -$$Nest$mpendingRemovalStore, reason: not valid java name */
    public static PendingRemovalStore m1638$$Nest$mpendingRemovalStore(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new PendingRemovalStore(new BroadcastDispatcherLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBroadcastDispatcherLogBufferProvider.get()));
    }

    /* renamed from: -$$Nest$mpeopleHeaderSectionHeaderController, reason: not valid java name */
    public static SectionHeaderNodeControllerImpl m1639$$Nest$mpeopleHeaderSectionHeaderController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        SectionHeaderNodeControllerImpl headerController = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesPeopleHeaderSubcomponentProvider.get()).getHeaderController();
        Preconditions.checkNotNullFromProvides(headerController);
        return headerController;
    }

    /* renamed from: -$$Nest$mpersonalProfileUserHandle, reason: not valid java name */
    public static UserHandle m1640$$Nest$mpersonalProfileUserHandle(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        try {
            UserInfo currentUser = ActivityManager.getService().getCurrentUser();
            return UserHandle.of(currentUser != null ? currentUser.id : 0);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* renamed from: -$$Nest$mphoneStatusBarPolicy, reason: not valid java name */
    public static PhoneStatusBarPolicy m1641$$Nest$mphoneStatusBarPolicy(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        StatusBarIconController statusBarIconController = (StatusBarIconController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarIconControllerImplProvider.get();
        CommandQueue commandQueue = (CommandQueue) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideCommandQueueProvider.get();
        BroadcastDispatcher broadcastDispatcher = (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Executor executor = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get();
        Executor executor2 = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiBackgroundExecutorProvider.get();
        Looper provideMainLooper = GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper();
        Resources resources = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        CastControllerImpl castControllerImpl = (CastControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.castControllerImplProvider.get();
        HotspotController hotspotController = (HotspotController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.hotspotControllerImplProvider.get();
        BluetoothControllerImpl bluetoothControllerImpl = (BluetoothControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bluetoothControllerImplProvider.get();
        NextAlarmController nextAlarmController = (NextAlarmController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.nextAlarmControllerImplProvider.get();
        UserInfoControllerImpl userInfoControllerImpl = (UserInfoControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userInfoControllerImplProvider.get();
        RotationLockController rotationLockController = (RotationLockController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.rotationLockControllerImplProvider.get();
        DataSaverControllerImpl dataSaverControllerImpl = (DataSaverControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDataSaverControllerProvider.get();
        ZenModeController zenModeController = (ZenModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.zenModeControllerImplProvider.get();
        DeviceProvisionedController deviceProvisionedController = (DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get();
        KeyguardStateController keyguardStateController = (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get();
        LocationController locationController = (LocationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.locationControllerImplProvider.get();
        SensorPrivacyControllerImpl sensorPrivacyControllerImpl = (SensorPrivacyControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSensorPrivacyControllerProvider.get();
        AlarmManager alarmManager = (AlarmManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAlarmManagerProvider.get();
        UserManager userManager = (UserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUserManagerProvider.get();
        UserTracker userTracker = (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get();
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDevicePolicyManagerProvider.get();
        RecordingController recordingController = (RecordingController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.recordingControllerProvider.get();
        TelecomManager telecomManager = (TelecomManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideTelecomManagerProvider.get();
        int displayId = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getDisplayId();
        SharedPreferences sharedPreferences = Prefs.get(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
        Preconditions.checkNotNullFromProvides(sharedPreferences);
        return new PhoneStatusBarPolicy(statusBarIconController, commandQueue, broadcastDispatcher, executor, executor2, provideMainLooper, resources, castControllerImpl, hotspotController, bluetoothControllerImpl, nextAlarmController, userInfoControllerImpl, rotationLockController, dataSaverControllerImpl, zenModeController, deviceProvisionedController, keyguardStateController, locationController, sensorPrivacyControllerImpl, alarmManager, userManager, userTracker, devicePolicyManager, recordingController, telecomManager, displayId, sharedPreferences, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dateFormatUtil(), (RingerModeTrackerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.ringerModeTrackerImplProvider.get(), (PrivacyItemController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.privacyItemControllerProvider.get(), new PrivacyLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providePrivacyLogBufferProvider.get()), (ConnectedDisplayInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.connectedDisplayInteractorImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.zenModeInteractor(), (JavaAdapter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.javaAdapterProvider.get());
    }

    /* renamed from: -$$Nest$mprivateProfilePolicy, reason: not valid java name */
    public static PrivateProfilePolicy m1642$$Nest$mprivateProfilePolicy(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new PrivateProfilePolicy((ProfileTypeRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindProfileTypeRepositoryProvider.get());
    }

    /* renamed from: -$$Nest$mpromoHeaderSectionHeaderController, reason: not valid java name */
    public static SectionHeaderNodeControllerImpl m1643$$Nest$mpromoHeaderSectionHeaderController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        SectionHeaderNodeControllerImpl headerController = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesPromoHeaderSubcomponentProvider.get()).getHeaderController();
        Preconditions.checkNotNullFromProvides(headerController);
        return headerController;
    }

    /* renamed from: -$$Nest$mqRCodeScannerTileMapper, reason: not valid java name */
    public static QRCodeScannerTileMapper m1645$$Nest$mqRCodeScannerTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QRCodeScannerTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mqRCodeScannerTileUserActionInteractor, reason: not valid java name */
    public static QRCodeScannerTileUserActionInteractor m1646$$Nest$mqRCodeScannerTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QRCodeScannerTileUserActionInteractor((QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$mqSDisableFlagsLogger, reason: not valid java name */
    public static QSDisableFlagsLogger m1647$$Nest$mqSDisableFlagsLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSDisableFlagsLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideQSFragmentDisableLogBufferProvider.get(), (DisableFlagsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disableFlagsLoggerProvider.get());
    }

    /* renamed from: -$$Nest$mqSPipelineLogger, reason: not valid java name */
    public static QSPipelineLogger m1649$$Nest$mqSPipelineLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSPipelineLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideQSTileListLogBufferProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideQSAutoAddLogBufferProvider.get(), (LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesQSRestoreLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mqSTileViewModelFactoryComponent, reason: not valid java name */
    public static void m1650$$Nest$mqSTileViewModelFactoryComponent(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
    }

    /* renamed from: -$$Nest$mqsBatteryModeController, reason: not valid java name */
    public static QsBatteryModeController m1651$$Nest$mqsBatteryModeController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QsBatteryModeController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (StatusBarContentInsetsProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarContentInsetsProvider.get());
    }

    /* renamed from: -$$Nest$mrecentsImplementation, reason: not valid java name */
    public static OverviewProxyRecentsImpl m1652$$Nest$mrecentsImplementation(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context;
        ContextComponentResolver contextComponentResolver = (ContextComponentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.contextComponentResolverProvider.get();
        String string = context.getString(R.string.config_recentsComponent);
        if (string == null || string.length() == 0) {
            throw new RuntimeException("No recents component configured", null);
        }
        OverviewProxyRecentsImpl overviewProxyRecentsImpl = (OverviewProxyRecentsImpl) ContextComponentResolver.resolve(string, contextComponentResolver.mRecentsCreators);
        if (overviewProxyRecentsImpl == null) {
            try {
                try {
                    overviewProxyRecentsImpl = (OverviewProxyRecentsImpl) context.getClassLoader().loadClass(string).newInstance();
                } catch (Throwable th) {
                    throw new RuntimeException("Error creating recents component: ".concat(string), th);
                }
            } catch (Throwable th2) {
                throw new RuntimeException("Error loading recents component: ".concat(string), th2);
            }
        }
        Preconditions.checkNotNullFromProvides(overviewProxyRecentsImpl);
        return overviewProxyRecentsImpl;
    }

    /* renamed from: -$$Nest$mrecsHeaderSectionHeaderController, reason: not valid java name */
    public static SectionHeaderNodeControllerImpl m1653$$Nest$mrecsHeaderSectionHeaderController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        SectionHeaderNodeControllerImpl headerController = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesRecsHeaderSubcomponentProvider.get()).getHeaderController();
        Preconditions.checkNotNullFromProvides(headerController);
        return headerController;
    }

    /* renamed from: -$$Nest$mrestrictedLockProxy, reason: not valid java name */
    public static RestrictedLockProxy m1654$$Nest$mrestrictedLockProxy(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new RestrictedLockProxy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context);
    }

    /* renamed from: -$$Nest$mrowInflaterTaskLogger, reason: not valid java name */
    public static RowInflaterTaskLogger m1655$$Nest$mrowInflaterTaskLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new RowInflaterTaskLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotifInflationLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$msceneLogger, reason: not valid java name */
    public static SceneLogger m1656$$Nest$msceneLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new SceneLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSceneFrameworkLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mscreenRecordTileDataInteractor, reason: not valid java name */
    public static ScreenRecordTileDataInteractor m1657$$Nest$mscreenRecordTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ScreenRecordTileDataInteractor((ScreenRecordRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenRecordRepositoryImplProvider.get());
    }

    /* renamed from: -$$Nest$mscreenRecordTileMapper, reason: not valid java name */
    public static ScreenRecordTileMapper m1658$$Nest$mscreenRecordTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ScreenRecordTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$mscreenRecordTileUserActionInteractor, reason: not valid java name */
    public static ScreenRecordTileUserActionInteractor m1659$$Nest$mscreenRecordTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new ScreenRecordTileUserActionInteractor((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.mainCoroutineContextProvider.get(), (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (ScreenRecordRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.screenRecordRepositoryImplProvider.get(), (RecordingController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.recordingControllerProvider.get(), (KeyguardInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardInteractorProvider.get(), (KeyguardDismissUtil) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardDismissUtilProvider.get(), (DialogTransitionAnimator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDialogTransitionAnimatorProvider.get(), (PanelInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.panelInteractorImplProvider.get(), (MediaProjectionMetricsLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaProjectionMetricsLoggerProvider.get(), (FeatureFlagsClassic) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.featureFlagsClassicReleaseProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get());
    }

    /* renamed from: -$$Nest$mscrimManager, reason: not valid java name */
    public static ScrimManager m1660$$Nest$mscrimManager(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        Executor executor = (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get();
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = (StatusBarKeyguardViewManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarKeyguardViewManagerProvider.get();
        BouncerScrimController bouncerScrimController = new BouncerScrimController();
        bouncerScrimController.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        BouncerlessScrimController bouncerlessScrimController = (BouncerlessScrimController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bouncerlessScrimControllerProvider.get();
        Preconditions.checkNotNullFromProvides(bouncerlessScrimController);
        return new ScrimManager(executor, bouncerScrimController, bouncerlessScrimController, (KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$msensitiveNotificationProtectionControllerLogger, reason: not valid java name */
    public static SensitiveNotificationProtectionControllerLogger m1661$$Nest$msensitiveNotificationProtectionControllerLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new SensitiveNotificationProtectionControllerLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSensitiveNotificationProtectionLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$msetOfComposableLockscreenSceneBlueprint, reason: not valid java name */
    public static Set m1662$$Nest$msetOfComposableLockscreenSceneBlueprint(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(2);
        DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.catwalkKeyguardBlueprintProvider);
        Object obj = DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.defaultBlueprintProvider).get();
        Intrinsics.checkNotNull(obj);
        arrayList.add((ComposableLockscreenSceneBlueprint) obj);
        arrayList.add(new CommunalBlueprint());
        return arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$msetOfDeviceEntryIconTransition, reason: not valid java name */
    public static Set m1663$$Nest$msetOfDeviceEntryIconTransition(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        SetBuilder setBuilder = new SetBuilder(39);
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerToAodTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerToDozingTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerToGoneTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerToOccludedTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.alternateBouncerToPrimaryBouncerTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.aodToGoneTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.aodToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.aodToOccludedTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.aodToPrimaryBouncerTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozingToGoneTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozingToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozingToOccludedTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozingToPrimaryBouncerTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamingToAodTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamingToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenToAodTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenToDozingTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenToDreamingTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenToOccludedTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenToPrimaryBouncerTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenToGoneTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.goneToAodTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.goneToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.goneToDozingTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.occludedToAodTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.occludedToDozingTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.occludedToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.offToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerToAodTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerToDozingTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerToLockscreenTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dreamingToGlanceableHubTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.glanceableHubToDreamingTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.glanceableHubToOccludedTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.occludedToGlanceableHubTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.goneToGlanceableHubTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.primaryBouncerToGlanceableHubTransitionViewModelProvider.get());
        setBuilder.add((DeviceEntryIconTransition) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.dozingToGlanceableHubTransitionViewModelProvider.get());
        return setBuilder.build();
    }

    /* renamed from: -$$Nest$msetOfFeedbackEffect, reason: not valid java name */
    public static Set m1664$$Nest$msetOfFeedbackEffect(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(4);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        arrayList.add(new com.google.android.systemui.elmyra.feedback.HapticClick(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), (Optional) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideOptionalVibratorProvider.get()));
        arrayList.add(new SquishyNavigationButtons(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (KeyguardViewMediator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.newKeyguardViewMediatorProvider.get(), (CentralSurfaces) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.centralSurfacesGoogleProvider.get(), (NavigationModeController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationModeControllerProvider.get()));
        arrayList.add(new NavUndimEffect((NavigationBarControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.navigationBarControllerImplProvider.get()));
        arrayList.add(new com.google.android.systemui.elmyra.feedback.UserActivity((KeyguardStateController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardStateControllerImplProvider.get(), (PowerManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePowerManagerProvider.get()));
        return arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$msetOfKeyguardBlueprint, reason: not valid java name */
    public static Set m1665$$Nest$msetOfKeyguardBlueprint(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(2);
        KeyguardBlueprint keyguardBlueprint = (KeyguardBlueprint) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.defaultKeyguardBlueprintProvider.get();
        Preconditions.checkNotNull(keyguardBlueprint, "Set contributions cannot be null");
        arrayList.add(keyguardBlueprint);
        KeyguardBlueprint keyguardBlueprint2 = (KeyguardBlueprint) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.splitShadeKeyguardBlueprintProvider.get();
        Preconditions.checkNotNull(keyguardBlueprint2, "Set contributions cannot be null");
        arrayList.add(keyguardBlueprint2);
        return arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$msetOfKeyguardQuickAffordanceConfig, reason: not valid java name */
    public static Set m1666$$Nest$msetOfKeyguardQuickAffordanceConfig(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        ArrayList arrayList = new ArrayList(2);
        Set of = SetsKt.setOf((CameraQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.cameraQuickAffordanceConfigProvider.get(), (DoNotDisturbQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.doNotDisturbQuickAffordanceConfigProvider.get(), (FlashlightQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.flashlightQuickAffordanceConfigProvider.get(), (HomeControlsKeyguardQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.homeControlsKeyguardQuickAffordanceConfigProvider.get(), (MuteQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.muteQuickAffordanceConfigProvider.get(), (QuickAccessWalletKeyguardQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.quickAccessWalletKeyguardQuickAffordanceConfigProvider.get(), (QrCodeScannerKeyguardQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qrCodeScannerKeyguardQuickAffordanceConfigProvider.get(), (VideoCameraQuickAffordanceConfig) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.videoCameraQuickAffordanceConfigProvider.get());
        Preconditions.checkNotNullFromProvides(of);
        Set set = of;
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Preconditions.checkNotNull(it.next(), "Set contributions cannot be null");
        }
        arrayList.addAll(set);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        arrayList.add(new NoteTaskQuickAffordanceConfig(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (NoteTaskController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.noteTaskControllerProvider.get(), new NoteTaskInfoResolver((RoleManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideRoleManagerProvider.get(), (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get()), (StylusManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.stylusManagerProvider.get(), (RoleManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideRoleManagerProvider.get(), (KeyguardUpdateMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUpdateMonitorProvider.get(), (UserManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUserManagerProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardQuickAffordanceRepositoryProvider), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.noteTaskEnabledKeyBoolean(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get()));
        return arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList));
    }

    /* renamed from: -$$Nest$msettingsMenuSection, reason: not valid java name */
    public static SettingsMenuSection m1669$$Nest$msettingsMenuSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new SettingsMenuSection(new KeyguardSettingsMenuViewModel((KeyguardTouchHandlingInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTouchHandlingInteractorProvider.get()), (KeyguardTouchHandlingViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTouchHandlingViewModelProvider.get(), (VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider.get(), (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get());
    }

    /* renamed from: -$$Nest$mshadeCarrierGroupControllerBuilder, reason: not valid java name */
    public static ShadeCarrierGroupController.Builder m1670$$Nest$mshadeCarrierGroupControllerBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ShadeCarrierGroupController.Builder((ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBgHandlerProvider.get(), GlobalConcurrencyModule_ProvideMainLooperFactory.provideMainLooper(), (ShadeCarrierGroupControllerLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeCarrierGroupControllerLoggerProvider.get(), (NetworkController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.networkControllerImplProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.carrierTextManagerBuilder(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (CarrierConfigTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.carrierConfigTrackerProvider.get(), (ShadeCarrierGroupController.SubscriptionManagerSlotIndexResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.subscriptionManagerSlotIndexResolverProvider.get(), (MobileUiAdapter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mobileUiAdapterProvider.get(), (MobileContextProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mobileContextProvider.get(), (StatusBarPipelineFlags) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarPipelineFlagsProvider.get());
    }

    /* renamed from: -$$Nest$mshadeDependentFlows, reason: not valid java name */
    public static ShadeDependentFlows m1671$$Nest$mshadeDependentFlows(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ShadeDependentFlows((KeyguardTransitionInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardTransitionInteractorProvider.get(), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get());
    }

    /* renamed from: -$$Nest$mshadeListBuilderLogger, reason: not valid java name */
    public static ShadeListBuilderLogger m1672$$Nest$mshadeListBuilderLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifPipelineFlags();
        return new ShadeListBuilderLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideNotificationsLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mshadeLogger, reason: not valid java name */
    public static ShadeLogger m1673$$Nest$mshadeLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ShadeLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mshadeWindowLogger, reason: not valid java name */
    public static ShadeWindowLogger m1674$$Nest$mshadeWindowLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ShadeWindowLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeWindowLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mshortcutHelperViewModel, reason: not valid java name */
    public static ShortcutHelperViewModel m1675$$Nest$mshortcutHelperViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new ShortcutHelperViewModel((RoleManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideRoleManagerProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgApplicationScopeProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (ShortcutHelperStateInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shortcutHelperStateInteractorProvider.get(), (ShortcutHelperCategoriesInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shortcutHelperCategoriesInteractorProvider.get());
    }

    /* renamed from: -$$Nest$mshutdownUi, reason: not valid java name */
    public static ShutdownUi m1676$$Nest$mshutdownUi(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        BlurUtils blurUtils = (BlurUtils) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.blurUtilsProvider.get();
        NearbyManager nearbyManager = (NearbyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideNearbyManagerProvider.get();
        ShutdownUi shutdownUi = new ShutdownUi();
        shutdownUi.mContext = context;
        shutdownUi.mBlurUtils = blurUtils;
        shutdownUi.mNearbyManager = nearbyManager;
        return shutdownUi;
    }

    /* renamed from: -$$Nest$msmartReplyStateInflaterImpl, reason: not valid java name */
    public static SmartReplyStateInflaterImpl m1677$$Nest$msmartReplyStateInflaterImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        SmartReplyConstants smartReplyConstants = (SmartReplyConstants) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartReplyConstantsProvider.get();
        ActivityManagerWrapper activityManagerWrapper = (ActivityManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideActivityManagerWrapperProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        PackageManagerWrapper packageManagerWrapper = (PackageManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerWrapperProvider.get();
        DevicePolicyManagerWrapper devicePolicyManagerWrapper = (DevicePolicyManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDevicePolicyManagerWrapperProvider.get();
        SmartReplyInflaterImpl smartReplyInflaterImpl = new SmartReplyInflaterImpl((SmartReplyConstants) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartReplyConstantsProvider.get(), (KeyguardDismissUtil) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardDismissUtilProvider.get(), (NotificationRemoteInputManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notificationRemoteInputManagerProvider.get(), (SmartReplyController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSmartReplyControllerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context);
        SmartReplyConstants smartReplyConstants2 = (SmartReplyConstants) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.smartReplyConstantsProvider.get();
        ActivityStarter activityStarter = (ActivityStarter) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.activityStarterImplProvider.get();
        SmartReplyController smartReplyController = (SmartReplyController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideSmartReplyControllerProvider.get();
        return new SmartReplyStateInflaterImpl(smartReplyConstants, activityManagerWrapper, packageManagerWrapper, devicePolicyManagerWrapper, smartReplyInflaterImpl, new SmartActionInflaterImpl(smartReplyConstants2, activityStarter, smartReplyController));
    }

    /* renamed from: -$$Nest$msocialHeaderSectionHeaderController, reason: not valid java name */
    public static SectionHeaderNodeControllerImpl m1678$$Nest$msocialHeaderSectionHeaderController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        SectionHeaderNodeControllerImpl headerController = ((DaggerSysUIGoogleGlobalRootComponent$SectionHeaderControllerSubcomponentImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesSocialHeaderSubcomponentProvider.get()).getHeaderController();
        Preconditions.checkNotNullFromProvides(headerController);
        return headerController;
    }

    /* renamed from: -$$Nest$mstaticOfAirplaneModeTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1679$$Nest$mstaticOfAirplaneModeTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfAlarmTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1680$$Nest$mstaticOfAlarmTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfColorCorrectionTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1681$$Nest$mstaticOfColorCorrectionTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfColorInversionTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1682$$Nest$mstaticOfColorInversionTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfDataSaverTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1683$$Nest$mstaticOfDataSaverTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfFlashlightTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1684$$Nest$mstaticOfFlashlightTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfFontScalingTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1685$$Nest$mstaticOfFontScalingTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfInternetTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1686$$Nest$mstaticOfInternetTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfIssueRecordingModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1687$$Nest$mstaticOfIssueRecordingModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfLocationTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1688$$Nest$mstaticOfLocationTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfModesTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1689$$Nest$mstaticOfModesTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfQRCodeScannerTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1690$$Nest$mstaticOfQRCodeScannerTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfScreenRecordModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1691$$Nest$mstaticOfScreenRecordModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfSensorPrivacyToggleTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1692$$Nest$mstaticOfSensorPrivacyToggleTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfUiModeNightTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1693$$Nest$mstaticOfUiModeNightTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstaticOfWorkModeTileModel, reason: not valid java name */
    public static QSTileViewModelFactory$Static m1694$$Nest$mstaticOfWorkModeTileModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new QSTileViewModelFactory$Static((DisabledByPolicyInteractorImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.disabledByPolicyInteractorImplProvider.get(), (UserRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.userRepositoryImplProvider.get(), (FalsingManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.falsingManagerProxyProvider.get(), (QSTileAnalytics) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileAnalyticsProvider.get(), (QSTileLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileLoggerProvider.get(), (QSTileConfigProviderImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileConfigProviderImplProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.uiBgDispatcherProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileCoroutineScopeFactory());
    }

    /* renamed from: -$$Nest$mstatusOverlayHoverListenerFactory, reason: not valid java name */
    public static StatusOverlayHoverListenerFactory m1695$$Nest$mstatusOverlayHoverListenerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new StatusOverlayHoverListenerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (DarkIconDispatcherImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.darkIconDispatcherImplProvider.get());
    }

    /* renamed from: -$$Nest$mstickyKeysIndicatorViewModel, reason: not valid java name */
    public static StickyKeysIndicatorViewModel m1696$$Nest$mstickyKeysIndicatorViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new StickyKeysIndicatorViewModel((StickyKeysRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.stickyKeysRepositoryImplProvider.get(), (KeyboardRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyboardRepositoryImplProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
    }

    /* renamed from: -$$Nest$msystemBarUtilsProxyImpl, reason: not valid java name */
    public static SystemBarUtilsProxyImpl m1697$$Nest$msystemBarUtilsProxyImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new SystemBarUtilsProxyImpl((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get());
    }

    /* renamed from: -$$Nest$msystemEventChipAnimationController, reason: not valid java name */
    public static SystemEventChipAnimationController m1698$$Nest$msystemEventChipAnimationController(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new SystemEventChipAnimationController(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, (StatusBarWindowControllerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarWindowControllerProvider.get(), (StatusBarContentInsetsProvider) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.statusBarContentInsetsProvider.get());
    }

    /* renamed from: -$$Nest$msystemProcessCondition, reason: not valid java name */
    public static SystemProcessCondition m1699$$Nest$msystemProcessCondition(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new SystemProcessCondition(new ProcessWrapper());
    }

    /* renamed from: -$$Nest$msystemSettingsImpl, reason: not valid java name */
    public static SystemSettingsImpl m1700$$Nest$msystemSettingsImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        ContentResolver contentResolver = (ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get();
        UserTracker userTracker = (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get();
        Objects.requireNonNull(userTracker);
        return new SystemSettingsImpl(contentResolver, new SystemUIModule$$ExternalSyntheticLambda0(userTracker), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.settingsBgDispatcherProvider.get());
    }

    /* renamed from: -$$Nest$mtaskSwitcherNotificationViewModel, reason: not valid java name */
    public static TaskSwitcherNotificationViewModel m1701$$Nest$mtaskSwitcherNotificationViewModel(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        return new TaskSwitcherNotificationViewModel((TaskSwitchInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.taskSwitchInteractorProvider.get(), (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgDispatcherProvider.get());
    }

    /* renamed from: -$$Nest$mtopAreaSection, reason: not valid java name */
    public static TopAreaSection m1702$$Nest$mtopAreaSection(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        KeyguardClockViewModel keyguardClockViewModel = (KeyguardClockViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockViewModelProvider.get();
        SmartSpaceSection smartSpaceSection = new SmartSpaceSection((LockscreenSmartspaceController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.lockscreenSmartspaceControllerProvider.get(), (KeyguardUnlockAnimationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardUnlockAnimationControllerProvider.get(), (KeyguardSmartspaceViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardSmartspaceViewModelProvider.get(), (AodBurnInViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.aodBurnInViewModelProvider.get());
        MediaCarouselSection mediaCarouselSection = new MediaCarouselSection((MediaCarouselController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaCarouselControllerProvider.get(), (MediaHost) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.providesKeyguardMediaHostProvider.get(), new KeyguardMediaViewModel((MediaCarouselInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaCarouselInteractorProvider.get()));
        DefaultClockSection defaultClockSection = new DefaultClockSection((KeyguardClockViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockViewModelProvider.get(), (AodBurnInViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.aodBurnInViewModelProvider.get());
        return new TopAreaSection(keyguardClockViewModel, smartSpaceSection, mediaCarouselSection, defaultClockSection, new WeatherClockSection((AodBurnInViewModel) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.aodBurnInViewModelProvider.get()), (KeyguardClockInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.keyguardClockInteractorProvider.get());
    }

    /* renamed from: -$$Nest$muiModeNightTileMapper, reason: not valid java name */
    public static UiModeNightTileMapper m1704$$Nest$muiModeNightTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        return new UiModeNightTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme());
    }

    /* renamed from: -$$Nest$muiModeNightTileUserActionInteractor, reason: not valid java name */
    public static UiModeNightTileUserActionInteractor m1705$$Nest$muiModeNightTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new UiModeNightTileUserActionInteractor((CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bgCoroutineContextProvider.get(), (UiModeManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.provideUiModeManagerProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$mvariableDateViewControllerFactory, reason: not valid java name */
    public static VariableDateViewController.Factory m1706$$Nest$mvariableDateViewControllerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new VariableDateViewController.Factory((SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), (ShadeInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.shadeInteractorImplProvider.get(), new ShadeLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideShadeLogBufferProvider.get()), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideTimeTickHandlerProvider.get());
    }

    /* renamed from: -$$Nest$mviewMediatorCallback, reason: not valid java name */
    public static KeyguardViewMediator.AnonymousClass4 m1707$$Nest$mviewMediatorCallback(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        KeyguardViewMediator.AnonymousClass4 anonymousClass4 = ((KeyguardViewMediator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.newKeyguardViewMediatorProvider.get()).mViewMediatorCallback;
        Preconditions.checkNotNullFromProvides(anonymousClass4);
        return anonymousClass4;
    }

    /* renamed from: -$$Nest$mvisualStabilityCoordinatorLogger, reason: not valid java name */
    public static VisualStabilityCoordinatorLogger m1708$$Nest$mvisualStabilityCoordinatorLogger(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new VisualStabilityCoordinatorLogger((LogBuffer) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideVisualStabilityLogBufferProvider.get());
    }

    /* renamed from: -$$Nest$mvolumeControllerAdapter, reason: not valid java name */
    public static VolumeControllerAdapter m1709$$Nest$mvolumeControllerAdapter(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new VolumeControllerAdapter((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (AudioRepository) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideAudioRepositoryProvider.get());
    }

    /* renamed from: -$$Nest$mvolumeDialog, reason: not valid java name */
    public static VolumeDialogImpl m1710$$Nest$mvolumeDialog(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        VolumeDialogImpl volumeDialogImpl = new VolumeDialogImpl(context, (VolumeDialogController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.volumeDialogControllerImplProvider.get(), (AccessibilityManagerWrapper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.accessibilityManagerWrapperProvider.get(), (DeviceProvisionedController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideDeviceProvisionedControllerProvider.get(), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.mediaOutputDialogManager(), (InteractionJankMonitor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideInteractionJankMonitorProvider.get(), new VolumePanelNavigationInteractor((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), new VolumePanelFlag()), (VolumeNavigator) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.volumeNavigatorProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$59) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.factoryProvider62.get(), (DevicePostureController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.devicePostureControllerImplProvider.get(), Looper.getMainLooper(), new VolumePanelFlag(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), DoubleCheck.lazy(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.secureSettingsImplProvider), (VibratorHelper) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.vibratorHelperProvider.get(), (SystemClock) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindSystemClockProvider.get(), (VolumeDialogInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.volumeDialogInteractorProvider.get());
        volumeDialogImpl.mHandler.obtainMessage(5, 1, 0).sendToTarget();
        if (!volumeDialogImpl.mAutomute) {
            volumeDialogImpl.mAutomute = true;
            volumeDialogImpl.mHandler.sendEmptyMessage(4);
        }
        if (volumeDialogImpl.mSilentMode) {
            volumeDialogImpl.mSilentMode = false;
            volumeDialogImpl.mHandler.sendEmptyMessage(4);
        }
        volumeDialogImpl.mCsdWarningNotificationActions = Optional.of(ImmutableList.construct(new CsdWarningAction(context.getString(R.string.learnmore_action_sounddose), new Intent("android.intent.action.VIEW", Uri.parse("https://support.google.com/pixelphone?p=pixel_sounddose")), true), new CsdWarningAction(context.getString(R.string.undo_action_sounddose), new Intent(VolumeDialog.ACTION_VOLUME_UNDO).setPackage(context.getPackageName()), false)));
        return volumeDialogImpl;
    }

    /* renamed from: -$$Nest$mvolumePanelViewModelFactory, reason: not valid java name */
    public static VolumePanelViewModel.Factory m1712$$Nest$mvolumePanelViewModelFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new VolumePanelViewModel.Factory((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), new DaggerSysUIGoogleGlobalRootComponent$DozeComponentFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleSysUIComponentImpl), (ConfigurationController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideGlobalConfigurationControllerProvider.get(), (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), (DumpManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.volumePanelLogger(), (VolumePanelGlobalStateInteractor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.volumePanelGlobalStateInteractorProvider.get());
    }

    /* renamed from: -$$Nest$mwakeLockBuilder, reason: not valid java name */
    public static WakeLock.Builder m1713$$Nest$mwakeLockBuilder(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new WakeLock.Builder(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.wakeLockLogger());
    }

    /* renamed from: -$$Nest$mwallpaperNotifier, reason: not valid java name */
    public static WallpaperNotifier m1714$$Nest$mwallpaperNotifier(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.getClass();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new WallpaperNotifier(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (CommonNotifCollection) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.notifPipelineProvider.get(), (BroadcastSender) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastSenderProvider.get(), (UserTracker) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideUserTrackerProvider.get(), (BroadcastDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.broadcastDispatcherProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiBackgroundExecutorProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.provideBackgroundExecutorProvider.get());
    }

    /* renamed from: -$$Nest$mwifiStatusTrackerFactory, reason: not valid java name */
    public static WifiStatusTrackerFactory m1715$$Nest$mwifiStatusTrackerFactory(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new WifiStatusTrackerFactory(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (WifiManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWifiManagerProvider.get(), (NetworkScoreManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideNetworkScoreManagerProvider.get(), (ConnectivityManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideConnectivityManagagerProvider.get(), (Handler) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainHandlerProvider.get());
    }

    /* renamed from: -$$Nest$mworkModeTileDataInteractor, reason: not valid java name */
    public static WorkModeTileDataInteractor m1716$$Nest$mworkModeTileDataInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new WorkModeTileDataInteractor((ManagedProfileController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.managedProfileControllerImplProvider.get());
    }

    /* renamed from: -$$Nest$mworkModeTileMapper, reason: not valid java name */
    public static WorkModeTileMapper m1717$$Nest$mworkModeTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl;
        return new WorkModeTileMapper(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.m977$$Nest$mmainResources(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.theme(), (DevicePolicyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDevicePolicyManagerProvider.get());
    }

    /* renamed from: -$$Nest$mworkModeTileUserActionInteractor, reason: not valid java name */
    public static WorkModeTileUserActionInteractor m1718$$Nest$mworkModeTileUserActionInteractor(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new WorkModeTileUserActionInteractor((ManagedProfileController) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.managedProfileControllerImplProvider.get(), (QSTileIntentUserInputHandlerImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.qSTileIntentUserInputHandlerImplProvider.get());
    }

    /* renamed from: -$$Nest$mworkProfilePolicy, reason: not valid java name */
    public static WorkProfilePolicy m1719$$Nest$mworkProfilePolicy(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl) {
        return new WorkProfilePolicy((ProfileTypeRepositoryImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.bindProfileTypeRepositoryProvider.get(), daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl.sysUIGoogleGlobalRootComponentImpl.context);
    }

    public DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl(DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, LeakModule leakModule, SharedLibraryModule sharedLibraryModule, MediaProjectionDevicePolicyModule mediaProjectionDevicePolicyModule, SysUICoroutinesModule sysUICoroutinesModule, GoogleDefaultBlueprintModule googleDefaultBlueprintModule, SysUIUnfoldModule sysUIUnfoldModule, ShellInterface shellInterface, Optional optional, Optional optional2, Optional optional3, Optional optional4, Optional optional5, ShellTransitions shellTransitions, KeyguardTransitions keyguardTransitions, Optional optional6, Optional optional7, Optional optional8, Optional optional9, Optional optional10) {
        Provider provider;
        Provider provider2;
        Provider provider3;
        Provider provider4;
        Provider provider5;
        Provider provider6;
        Provider provider7;
        Provider provider8;
        Provider provider9;
        Provider provider10;
        Provider provider11;
        Provider provider12;
        Provider provider13;
        Provider provider14;
        Provider provider15;
        Provider provider16;
        Provider provider17;
        Provider provider18;
        Provider provider19;
        this.sysUIGoogleGlobalRootComponentImpl = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl;
        this.sysUICoroutinesModule = sysUICoroutinesModule;
        this.leakModule = leakModule;
        this.setShell = shellInterface;
        this.sysUIUnfoldModule = sysUIUnfoldModule;
        this.setBubbles = optional4;
        this.sharedLibraryModule = sharedLibraryModule;
        this.setTaskViewFactory = optional5;
        this.setPip = optional;
        this.setDesktopMode = optional10;
        this.setBackAnimation = optional9;
        this.setStartingSurface = optional6;
        this.setKeyguardTransitions = keyguardTransitions;
        this.setShellTransitions = shellTransitions;
        this.setSplitScreen = optional2;
        this.setOneHanded = optional3;
        this.setRecentTasks = optional8;
        this.setDisplayAreaHelper = optional7;
        this.bootCompleteCacheImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 0);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 2, 1));
        this.factoryProvider = provider;
        this.provideGlobalConfigurationControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 1);
        this.provideBgLooperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 6);
        this.provideBackgroundExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 5);
        this.deviceConfigProxyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 11);
        this.bindsReaderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 10);
        int i = 1;
        this.bindSystemClockProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 14, i));
        this.provideBroadcastRunningLooperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 16);
        this.provideBroadcastRunningExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 17);
        this.bgDispatcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 22);
        this.provideBgHandlerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 24, i);
        this.settingsBgDispatcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 23);
        this.commandRegistryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 25);
        this.logcatEchoTrackerDebugProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 21, i);
        this.provideLogcatEchoTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 20);
        this.logBufferFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 19);
        this.provideBroadcastDispatcherLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 18);
        this.provideUserTrackerProvider = new DelegateFactory();
        this.broadcastDispatcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 15);
        this.powerRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 13);
        this.enhancedEstimatesGoogleImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 29);
        this.provideDemoModeControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 30);
        this.provideBatteryControllerLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 32);
        this.batteryControllerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 31);
        this.provideReverseWirelessChargerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 34);
        this.provideUsbManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 35);
        this.provideIThermalServiceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 36);
        this.reverseChargingControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 33);
        this.secureSettingsImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 37, 1);
        this.provideBatteryControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 28);
        this.provideWirelessChargerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 39);
        this.wirelessChargerCommanderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 40);
        this.javaAdapterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 42);
        this.statusBarStateControllerImplProvider = new DelegateFactory();
        this.provideDisplayTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 49);
        this.filesProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 51);
        this.logBufferEulogizerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 50);
        this.systemUIConfigDumpableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 52);
        this.powerInteractorProvider = new DelegateFactory();
        this.provideCommandQueueProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 48, 1));
        this.authControllerProvider = new DelegateFactory();
        this.keyguardUpdateMonitorProvider = new DelegateFactory();
        this.keyguardStateControllerImplProvider = new DelegateFactory();
        this.sessionTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 56);
        this.falsingCollectorImplProvider = new DelegateFactory();
        this.falsingCollectorNoOpProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 58);
        this.providesFalsingCollectorLegacyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 57);
        this.statusBarKeyguardViewManagerProvider = new DelegateFactory();
        this.dismissCallbackRegistryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 59);
        this.userRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 62);
        this.keyguardInteractorProvider = new DelegateFactory();
        this.featureFlagsClassicReleaseProvider = new DelegateFactory();
        this.provideNotificationsLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 69);
        this.notificationDismissibilityProviderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 70);
        this.notifCollectionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 68);
        this.notifPipelineChoreographerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 72);
        this.notificationClickNotifierProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 74);
        this.notificationInteractionTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 73);
        this.shadeListBuilderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 71);
        this.renderStageManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 75);
        this.notifPipelineProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 67);
        this.provideWakeLockLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 77);
        this.broadcastSenderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 76);
        this.alwaysOnDisplayPolicyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 82);
        this.providesLeakDetectorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 84);
        this.systemUIDialogManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 86);
        this.containerConfigProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 91, 1);
        this.providesSceneDataSourceDelegatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 92);
        this.sceneContainerRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 90);
        this.provideSceneFrameworkLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 93);
        this.tunerServiceImplProvider = new DelegateFactory();
        this.activeNotificationListRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 102);
        this.activeNotificationsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 101);
        this.notifLiveDataStoreImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 103);
        this.notificationVisibilityProviderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 100);
        this.overviewProxyServiceProvider = new DelegateFactory();
        this.navigationModeControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 106);
        this.provideSysUiStateProvider = new DelegateFactory();
        this.accessibilityButtonModeObserverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 108);
        this.accessibilityButtonTargetsObserverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 109);
        this.accessibilityGestureTargetsObserverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 110);
        this.notificationShadeWindowControllerImplProvider = new DelegateFactory();
        this.bgCoroutineContextProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 115);
        this.bgApplicationScopeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 114);
        this.deviceProvisionedControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 119);
        this.provideDeviceProvisionedControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 118);
        this.deviceProvisioningInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 117);
        this.splitShadeStateControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 122);
        this.remoteInputQuickSettingsDisablerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 121);
        this.provideDisableFlagsRepositoryLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 123);
        this.disableFlagsLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 124);
        this.disableFlagsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 120);
        this.dozeParametersProvider = new DelegateFactory();
        this.keyguardRepositoryImplProvider = new DelegateFactory();
        this.keyguardTransitionRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 126);
        this.sceneInteractorProvider = new DelegateFactory();
        this.keyguardTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 125);
        this.userSetupRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, this, 127);
        this.userSwitcherInteractorProvider = new DelegateFactory();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2 = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        int i2 = 1;
        this.shadeRepositoryImplProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 131, i2));
        this.bindShadeModeInteractorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 130, i2));
        this.shadeInteractorSceneContainerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 129);
        this.configurationRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 134);
        this.shadeInteractorImplProvider = new DelegateFactory();
        this.fingerprintPropertyRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 137);
        this.configurationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 138);
        this.displayRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 141);
        this.displayStateRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 140);
        int i3 = 1;
        this.providesDisplayStateInteractorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 139, i3));
        this.selectedUserInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 143);
        this.udfpsOverlayInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 142);
        this.fingerprintPropertyInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 136);
        this.deviceEntryFingerprintAuthRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 144);
        this.devicePostureControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 147);
        this.devicePostureRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 146);
        this.facePropertyRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 148);
        this.connectivitySlotsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 152);
        this.provideSharedConnectivityTableLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 154);
        this.connectivityInputLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 153);
        this.connectivityRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 151);
        this.provideMobileInputLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 156);
        this.mobileInputLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 155);
        this.tableLogBufferFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 158);
        this.provideMobileSummaryLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 157);
        this.provideAirplaneTableLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 160);
        this.airplaneModeRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 159);
        this.disabledWifiRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 163);
        this.wifiPickerTrackerFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 165);
        this.provideWifiLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 166);
        this.provideWifiTableLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 167);
        this.factoryProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 164);
        this.provideRealWifiRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 162);
        this.demoModeWifiDataSourceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 168);
        this.wifiRepositorySwitcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 161);
        this.carrierConfigRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 169);
        this.factoryProvider3 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 170);
        this.mobileConnectionsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 150);
        this.demoModeMobileConnectionDataSourceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 171);
        this.mobileRepositorySwitcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 149);
        this.biometricSettingsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 145);
        this.deviceEntryUdfpsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 135);
        this.largeScreenHeaderHelperProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 172, i3);
        this.sharedNotificationContainerInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 133);
        this.shadeInteractorLegacyImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 132);
        this.provideBaseShadeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 128);
        DelegateFactory.setDelegate(this.shadeInteractorImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 116, 1)));
        this.falsingDataProvider = new DelegateFactory();
        this.historyTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 180);
        this.brightLineFalsingManagerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 179, 1);
        this.falsingManagerProxyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 178);
        this.falsingInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 177);
        provider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 176, 1));
        this.factoryProvider4 = provider2;
        this.bouncerRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 185);
        this.keyguardSecurityModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 188);
        this.authenticationRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 187);
        this.authenticationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 186);
        this.keyguardBypassControllerProvider = new DelegateFactory();
        this.provideFaceAuthLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 193);
        this.faceAuthenticationLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 192);
        this.provideBouncerLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 196);
        this.keyguardBouncerRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 195);
        this.deviceEntryBiometricSettingsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 199);
        this.deviceEntryFingerprintAuthInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 198);
        this.providesFaceAuthInteractorInstanceProvider = new DelegateFactory();
        this.deviceEntryBiometricsAllowedInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 197);
        this.alternateBouncerInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 194);
        this.provideFaceDetectTableLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 200);
        this.provideFaceAuthTableLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 201);
        this.deviceEntryFaceAuthRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 191);
        this.bouncerViewImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 203);
        this.primaryBouncerCallbackInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 204);
        this.provideKeyguardUpdateMonitorLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 207);
        this.trustRepositoryLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 206);
        this.trustRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 205);
        this.primaryBouncerInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 202);
        this.faceWakeUpTriggersConfigImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 208);
        this.deviceEntryFaceAuthStatusInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 209);
        this.systemUIDeviceEntryFaceAuthInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 190);
        this.noopDeviceEntryFaceAuthInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 210);
        DelegateFactory.setDelegate(this.providesFaceAuthInteractorInstanceProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 189, 1)));
        this.sceneBackInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 211);
        this.bouncerInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 184);
        provider3 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 183, 1));
        this.factoryProvider5 = provider3;
        this.simBouncerRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 215);
        this.simBouncerInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 214);
        this.refreshUsersSchedulerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 218);
        this.factoryProvider6 = new DelegateFactory();
        this.userSwitcherControllerProvider = new DelegateFactory();
        provider4 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 220, 1));
        this.factoryProvider7 = provider4;
        this.guestResumeSessionReceiverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 219);
        provider5 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 221, 1));
        this.factoryProvider8 = provider5;
        this.guestUserInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 217);
        this.userSwitcherViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 216);
        this.provideBiometricLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 225);
        this.faceHelpMessageDeferralFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 224);
        this.faceHelpMessageDeferralInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl2, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 223);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3 = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2 = this.sysUIGoogleSysUIComponentImpl;
        this.devicePostureInteractorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 226, 1));
        this.biometricMessageInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 222);
        this.deviceEntryRepositoryImplProvider = new DelegateFactory();
        this.trustInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 228);
        this.deviceUnlockedInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 227);
        SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 213, 1));
        this.emergencyServicesRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 230);
        this.telephonyListenerManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 233);
        this.telephonyRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 232);
        this.telephonyInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 231);
        this.emergencyAffordanceManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 234);
        this.provideDozeLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 235);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 229);
        SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 236, 1));
        SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 237, 1));
        this.inputMethodRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 240);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 239);
        SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 238, 1));
        this.provideMSDLPlayerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 241);
        provider6 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 212, 1));
        this.factoryProvider13 = provider6;
        this.bouncerDialogFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 242);
        this.bouncerSceneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 182);
        this.dockObserverProvider = new DelegateFactory();
        this.provideBackgroundDelayableExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 246);
        this.provideKeyguardLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 247);
        this.bouncerMessageRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 249);
        this.countDownTimerUtilProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 250);
        this.bouncerMessageInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 248);
        this.indicationHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 251);
        this.keyguardIndicationControllerGoogleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 245);
        this.providesCommunalSceneDataSourceDelegatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 254);
        this.communalSceneRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 253);
        this.provideCommunalLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 255);
        this.communalSceneInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 252);
        this.provideCommunalAppWidgetHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 258);
        this.provideCommunalWidgetHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 259);
        this.provideCommunalWidgetDaoProvider = new DelegateFactory();
        this.defaultWidgetPopulationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 262);
        this.provideCommunalDatabaseProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 261);
        DelegateFactory.setDelegate(this.provideCommunalWidgetDaoProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 260, 1)));
        this.provideBackupManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 263);
        this.providesCommunalBackupUtilsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 264);
        this.providePackageChangeRepoLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 267);
        this.packageInstallerMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 266);
        this.packageUpdateLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 269);
        provider7 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 268, 1));
        this.factoryProvider14 = provider7;
        this.packageChangeRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 265);
        this.communalWidgetRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 257);
        this.userFileManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 272);
        this.communalPrefsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 271);
        this.provideCommunalTableLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 273);
        this.communalPrefsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 270);
        this.providesMediaTimeoutListenerLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 279);
        this.mediaTimeoutLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 278);
        this.mediaFlagsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 280);
        this.mediaTimeoutListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 277);
        this.provideMediaBrowserBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 283);
        this.resumeMediaBrowserLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 282);
        this.mediaResumeListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 281);
        this.provideLocalBluetoothControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 284);
        this.provideMediaMuteAwaitLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 287);
        this.mediaMuteAwaitLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 286);
        this.mediaMuteAwaitConnectionManagerFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 285);
        this.providesMediaDeviceLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 288);
        this.notificationLockscreenUserManagerImplProvider = new DelegateFactory();
        this.mediaUiEventLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 289);
        this.imageLoaderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 291);
        this.mediaDataLoaderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 290);
        this.providesMediaLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 293);
        this.mediaLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 292);
        this.legacyMediaDataManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 276);
        this.activityStarterImplProvider = new DelegateFactory();
        this.mediaDataRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 296);
        this.mediaDataProcessorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 295);
        this.mediaSmartspaceLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 299);
        this.mediaFilterRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 298);
        this.mediaDataFilterImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 297);
        this.mediaCarouselInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 294);
        this.providesMediaDataManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 275);
        this.communalMediaRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 274);
        this.provideGlanceableHubBcSmartspaceDataPluginProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 302);
        this.communalSmartspaceControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 301);
        this.communalSmartspaceRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 300);
        this.communalSettingsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 304);
        this.communalSettingsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 303);
        this.managedProfileControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 305);
        this.communalInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 256);
        this.communalTutorialDisabledRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 307);
        this.communalTutorialInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 306);
        this.mediaHostStatesManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 313);
        this.provideMediaViewLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 315);
        this.mediaViewLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 314);
        this.provideBackgroundRepeatableExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 316);
        this.mediaViewControllerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 312, 1);
        this.provideAnimationFeatureFlagsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 318);
        this.provideDialogTransitionAnimatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 317);
        this.provideNearbyMediaDevicesLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 322);
        this.nearbyMediaDevicesLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 321);
        this.nearbyMediaDevicesManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 320);
        this.provideVolumeLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl3, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl2, 325);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4 = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3 = this.sysUIGoogleSysUIComponentImpl;
        this.volumePanelGlobalStateRepositoryProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 324, 1));
        this.volumePanelGlobalStateInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 323);
        provider8 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 319, 1));
        this.factoryProvider15 = provider8;
        this.mediaCarouselControllerProvider = new DelegateFactory();
        this.activityIntentHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 326);
        provider9 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 328, 1));
        this.factoryProvider16 = provider9;
        this.broadcastDialogControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 327);
        this.mediaControlPanelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 311, 1);
        this.visualStabilityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 329);
        this.provideMediaCarouselControllerBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 331);
        this.mediaCarouselControllerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 330);
        provider10 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 333, 1));
        this.mediaControlInteractorFactoryProvider = provider10;
        this.mediaRecommendationsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 335);
        this.mediaRecommendationsViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 334);
        this.mediaCarouselViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 332);
        this.deviceEntryInteractorProvider = new DelegateFactory();
        DelegateFactory.setDelegate(this.mediaCarouselControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 310, 1)));
        this.provideDreamLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 337);
        this.dreamOverlayStateControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 336);
        this.communalColorsImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 339);
        this.provideKeyguardTransitionAnimationLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 342);
        this.keyguardTransitionAnimationFlowProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 341);
        this.glanceableHubToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 340);
        this.lockscreenToGlanceableHubTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 343);
        this.dreamingToGlanceableHubTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 344);
        this.glanceableHubToDreamingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 345);
        this.communalTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 338);
        this.wakefulnessLifecycleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 346);
        this.mediaHierarchyManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 309);
        this.providesCommunalMediaHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 308);
        this.communalStatsLogProxyImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 348);
        this.communalMetricsLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 347);
        this.communalViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 244);
        this.vibratorHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 351);
        this.udfpsControllerProvider = new DelegateFactory();
        this.dozeLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 355);
        this.dozeScrimControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 354);
        this.newKeyguardViewMediatorProvider = new DelegateFactory();
        this.biometricUnlockLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 356);
        this.provideNotificationMediaManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 357);
        this.provideSysUIUnfoldComponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 360);
        this.sysUiUnfoldComponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 359);
        this.provideLockIconViewControllerProvider = new DelegateFactory();
        this.shadeLockscreenInteractorImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 363, 1);
        this.providesNotificationShadeWindowViewProvider = new DelegateFactory();
        this.providesNotificationPanelViewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 365);
        this.provideNotificationHeadsUpLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 368);
        this.groupMembershipManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 369);
        this.accessibilityManagerWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 370);
        this.avalancheControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 371);
        this.headsUpManagerPhoneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 367);
        this.screenOffAnimationControllerProvider = new DelegateFactory();
        this.provideNotificationLockScreenLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 372);
        this.notificationsKeyguardViewStateRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 373);
        this.notificationWakeUpCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 366);
        this.provideLSShadeTransitionControllerBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 376);
        this.lockscreenGestureLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 377);
        this.providesKeyguardMediaHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 381);
        this.provideKeyguardMediaControllerLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 382);
        this.keyguardMediaControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 380);
        this.notificationSectionsFeatureManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 383);
        this.mediaContainerControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 384);
        this.notificationRoundnessManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 385);
        int i4 = 1;
        this.sectionHeaderControllerSubcomponentBuilderProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 387, i4);
        this.providesIncomingHeaderSubcomponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 386);
        this.providesPeopleHeaderSubcomponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 388);
        this.providesAlertingHeaderSubcomponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 389);
        this.providesSilentHeaderSubcomponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 390);
        this.providesNewsHeaderSubcomponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 391);
        this.providesSocialHeaderSubcomponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 392);
        this.providesRecsHeaderSubcomponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 393);
        this.providesPromoHeaderSubcomponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 394);
        this.notificationSectionsManagerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 379, i4);
        this.largeScreenShadeInterpolatorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 395);
        this.ambientStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 378);
        provider11 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 399, 1));
        this.factoryProvider17 = provider11;
        this.darkIconDispatcherImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 398);
        this.letterboxBackgroundProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 403);
        this.letterboxAppearanceCalculatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 402);
        this.provideOngoingCallLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 405);
        this.ongoingCallRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 404);
        provider12 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 401, 1));
        this.statusBarModePerDisplayRepositoryFactoryProvider = provider12;
        this.statusBarModeRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 400);
        this.lightBarControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 397);
        provider13 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 406, 1));
        this.factoryProvider18 = provider13;
        this.biometricUnlockControllerProvider = new DelegateFactory();
        this.keyguardUnlockAnimationControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 407);
        this.keyguardDismissInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 410);
        this.keyguardDismissActionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 409);
        this.primaryBouncerToGoneTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 408);
        this.alternateBouncerToGoneTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 411);
        this.wallpaperRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 412);
        this.scrimControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 396);
        provider14 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 413, 1));
        this.factoryProvider19 = provider14;
        this.blurUtilsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 415);
        this.wallpaperControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 416);
        this.notificationShadeDepthControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 414);
        provider15 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 417, 1));
        this.factoryProvider20 = provider15;
        provider16 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 418, 1));
        this.factoryProvider21 = provider16;
        provider17 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl4, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl3, 419, 1));
        this.factoryProvider22 = provider17;
        initialize5();
        initialize6();
        initialize7();
        initialize8();
        initialize9();
        initialize10();
        initialize11();
        initialize12();
        initialize13();
        initialize14();
        initialize15();
        initialize16();
        initialize17();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5 = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4 = this.sysUIGoogleSysUIComponentImpl;
        int i5 = 1;
        this.scrimStartableProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1619, i5));
        this.statusBarStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1620);
        this.keyguardSmartspaceStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1621);
        this.unfoldInitializationStartableProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1622, i5);
        this.provideShadeTouchLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1624);
        this.scrimShadeTransitionControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1625);
        this.shadeStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1623);
        this.unfoldTraceLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1626);
        this.notificationChannelsProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1627, i5);
        provider18 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1629, 1));
        this.keyguardPreviewRendererFactoryProvider = provider18;
        this.keyguardRemotePreviewManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1628);
        this.toAodFoldTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1630);
        this.retailModeInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1631);
        this.providesDreamOverlayNotificationCountProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1632);
        this.dreamOverlayStatusBarItemsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1633);
        this.sectionStyleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1634);
        this.provideUnseenNotificationLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1635);
        this.debugModeFilterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1636);
        this.notifUiAdjustmentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1637);
        this.remoteInputNotificationRebuilderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1638);
        this.notificationSettingsControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1639);
        DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1640, i5));
        this.userSwitchDialogControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1641);
        this.keyguardStatusBarRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1644);
        this.keyguardStatusBarInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1643);
        this.keyguardStatusBarViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1642);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1645);
        this.keyguardKeyboardInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1646);
        this.faceAuthAccessibilityDelegateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1647);
        this.provideNavBarButtonClickLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1648);
        this.provideNavbarOrientationTrackingLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1649);
        this.provideAudioModeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1650);
        this.localMediaRepositoryFactoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1651);
        this.provideMediaDeviceSessionRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1652);
        this.mediaControllerInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1653);
        provider19 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1655, 1));
        this.factoryProvider83 = provider19;
        this.provideAncSliceRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1654);
        this.bindCaptioningRepositoryProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1657, i5));
        this.captioningInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl5, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl4, 1656);
    }

    public final AlternateBouncerViewModel alternateBouncerViewModel() {
        return new AlternateBouncerViewModel((StatusBarKeyguardViewManager) this.statusBarKeyguardViewManagerProvider.get(), (KeyguardTransitionInteractor) this.keyguardTransitionInteractorProvider.get(), (DismissCallbackRegistry) this.dismissCallbackRegistryProvider.get(), DoubleCheck.lazy(this.alternateBouncerInteractorProvider), (PrimaryBouncerInteractor) this.primaryBouncerInteractorProvider.get());
    }

    public final CarrierTextManager.Builder carrierTextManagerBuilder() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        Resources resources = context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        return new CarrierTextManager.Builder(context, resources, (WifiRepository) this.wifiRepositorySwitcherProvider.get(), (DeviceBasedSatelliteViewModel) this.deviceBasedSatelliteViewModelImplProvider.get(), (JavaAdapter) this.javaAdapterProvider.get(), (TelephonyManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideTelephonyManagerProvider.get(), (TelephonyListenerManager) this.telephonyListenerManagerProvider.get(), (WakefulnessLifecycle) this.wakefulnessLifecycleProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (Executor) this.provideBackgroundExecutorProvider.get(), (KeyguardUpdateMonitor) this.keyguardUpdateMonitorProvider.get(), new CarrierTextManagerLogger((LogBuffer) this.provideCarrierTextManagerLogProvider.get()));
    }

    public final ClockMessageBuffers clockMessageBuffers() {
        return new ClockMessageBuffers((LogBuffer) this.provideKeyguardClockLogProvider.get(), (LogBuffer) this.provideKeyguardSmallClockLogProvider.get(), (LogBuffer) this.provideKeyguardLargeClockLogProvider.get());
    }

    public final CommunalAppWidgetSection communalAppWidgetSection() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new CommunalAppWidgetSection((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), new WidgetViewFactory((CoroutineContext) this.uiBgCoroutineContextProvider.get(), (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiBackgroundExecutorProvider.get(), (CommunalAppWidgetHost) this.provideCommunalAppWidgetHostProvider.get(), (WidgetInteractionHandler) this.widgetInteractionHandlerProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$35) this.factoryProvider36.get()));
    }

    public final DateFormatUtil dateFormatUtil() {
        return new DateFormatUtil(this.sysUIGoogleGlobalRootComponentImpl.context, (UserTracker) this.provideUserTrackerProvider.get());
    }

    public final DcServiceClientImpl dcServiceClient() {
        Context context = (Context) this.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get();
        LocalBluetoothManager localBluetoothManager = (LocalBluetoothManager) this.provideLocalBluetoothControllerProvider.get();
        Intrinsics.checkNotNull(localBluetoothManager);
        return new DcServiceClientImpl(context, localBluetoothManager);
    }

    public final DreamManager dreamManager() {
        Object systemService = this.sysUIGoogleGlobalRootComponentImpl.context.getSystemService((Class<Object>) DreamManager.class);
        if (systemService != null) {
            return (DreamManager) systemService;
        }
        throw new IllegalArgumentException("Required value was null.");
    }

    public final GestureInteractor gestureInteractor() {
        GestureRepositoryImpl gestureRepositoryImpl = (GestureRepositoryImpl) this.gestureRepositoryImplProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new GestureInteractor(gestureRepositoryImpl, (CoroutineDispatcher) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.mainDispatcherProvider.get(), (CoroutineContext) this.bgCoroutineContextProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (ActivityManagerWrapper) this.provideActivityManagerWrapperProvider.get(), (TaskStackChangeListeners) this.provideTaskStackChangeListenersProvider.get());
    }

    public final Map getStartables() {
        MapBuilder mapBuilder = new MapBuilder(127);
        mapBuilder.contributions.put(ContextualEducationInteractor.class, this.provideContextualEducationInteractorProvider);
        mapBuilder.contributions.put(KeyboardTouchpadEduInteractor.class, this.provideKeyboardTouchpadEduInteractorProvider);
        mapBuilder.contributions.put(ContextualEduUiCoordinator.class, this.provideContextualEduUiCoordinatorProvider);
        mapBuilder.contributions.put(NotificationSettingsRepository.class, this.provideCoreStartableProvider);
        mapBuilder.contributions.put(NotificationLogger.class, this.provideCoreStartableProvider2);
        mapBuilder.contributions.put(ShortcutHelperActivityStarter.class, this.starterProvider);
        mapBuilder.contributions.put(ShortcutHelperStateRepository.class, this.repoProvider);
        mapBuilder.contributions.put(CoroutineInitializer.class, this.coroutineScopeCoreStartableProvider);
        mapBuilder.contributions.put(BroadcastDispatcherStartable.class, this.broadcastDispatcherStartableProvider);
        mapBuilder.contributions.put(AuthController.class, this.authControllerProvider);
        mapBuilder.contributions.put(SideFpsOverlayViewBinder.class, this.sideFpsOverlayViewBinderProvider);
        mapBuilder.contributions.put(AlternateBouncerViewBinder.class, this.alternateBouncerViewBinderProvider);
        mapBuilder.contributions.put(CommunalSceneTransitionInteractor.class, this.communalSceneTransitionInteractorProvider);
        mapBuilder.contributions.put(CommunalLoggerStartable.class, this.communalLoggerStartableProvider);
        mapBuilder.contributions.put(CommunalSceneStartable.class, this.communalSceneStartableProvider);
        mapBuilder.contributions.put(CommunalDreamStartable.class, this.communalDreamStartableProvider);
        mapBuilder.contributions.put(CommunalAppWidgetHostStartable.class, this.communalAppWidgetHostStartableProvider);
        mapBuilder.contributions.put(CommunalBackupRestoreStartable.class, this.communalBackupRestoreStartableProvider);
        mapBuilder.contributions.put(CommunalOngoingContentStartable.class, this.communalOngoingContentStartableProvider);
        mapBuilder.contributions.put(CommunalMetricsStartable.class, this.communalMetricsStartableProvider);
        mapBuilder.contributions.put(ConfigurationControllerStartable.class, this.configurationControllerStartableProvider);
        mapBuilder.contributions.put(FalsingCoreStartable.class, this.falsingCoreStartableProvider);
        mapBuilder.contributions.put(FeatureFlagsReleaseStartable.class, this.featureFlagsReleaseStartableProvider);
        mapBuilder.contributions.put(FlagDependencies.class, this.flagDependenciesProvider);
        mapBuilder.contributions.put(LetterboxBackgroundProvider.class, this.letterboxBackgroundProvider);
        mapBuilder.contributions.put(MotionToolStartable.class, this.motionToolStartableProvider);
        mapBuilder.contributions.put(QSFragmentStartable.class, this.qSFragmentStartableProvider);
        mapBuilder.contributions.put(OngoingCallController.class, this.ongoingCallControllerProvider);
        mapBuilder.contributions.put(LightBarController.class, this.lightBarControllerProvider);
        mapBuilder.contributions.put(StatusBarSignalPolicy.class, this.statusBarSignalPolicyProvider);
        mapBuilder.contributions.put(StatusBarModeRepositoryImpl.class, this.statusBarModeRepositoryImplProvider);
        mapBuilder.contributions.put(DemoRonChipViewModel.class, this.demoRonChipViewModelProvider);
        mapBuilder.contributions.put(MobileUiAdapter.class, this.mobileUiAdapterProvider);
        mapBuilder.contributions.put(CarrierConfigCoreStartable.class, this.carrierConfigCoreStartableProvider);
        mapBuilder.contributions.put(StatusBarIconViewBindingFailureTracker.class, this.statusBarIconViewBindingFailureTrackerProvider);
        mapBuilder.contributions.put(UserSwitcherDialogCoordinator.class, this.userSwitcherDialogCoordinatorProvider);
        mapBuilder.contributions.put(BiometricNotificationService.class, this.biometricNotificationServiceProvider);
        mapBuilder.contributions.put(ClipboardListener.class, this.clipboardListenerProvider);
        mapBuilder.contributions.put(GlobalActionsComponent.class, this.globalActionsComponentProvider);
        mapBuilder.contributions.put(InstantAppNotifier.class, this.instantAppNotifierProvider);
        mapBuilder.contributions.put(KeyboardUI.class, this.keyboardUIProvider);
        mapBuilder.contributions.put(MediaProjectionTaskSwitcherCoreStartable.class, this.mediaProjectionTaskSwitcherCoreStartableProvider);
        mapBuilder.contributions.put(KeyguardBiometricLockoutLogger.class, this.keyguardBiometricLockoutLoggerProvider);
        mapBuilder.contributions.put(KeyguardViewMediator.class, this.newKeyguardViewMediatorProvider);
        mapBuilder.contributions.put(LatencyTester.class, this.latencyTesterProvider);
        mapBuilder.contributions.put(DisplaySwitchLatencyTracker.class, this.displaySwitchLatencyTrackerProvider);
        mapBuilder.contributions.put(ImmersiveModeConfirmation.class, this.immersiveModeConfirmationProvider);
        mapBuilder.contributions.put(RingtonePlayer.class, this.ringtonePlayerProvider);
        mapBuilder.contributions.put(GesturePointerEventListener.class, this.gesturePointerEventListenerProvider);
        mapBuilder.contributions.put(SessionTracker.class, this.sessionTrackerProvider);
        mapBuilder.contributions.put(ShortcutKeyDispatcher.class, this.shortcutKeyDispatcherProvider);
        mapBuilder.contributions.put(SliceBroadcastRelayHandler.class, this.sliceBroadcastRelayHandlerProvider);
        mapBuilder.contributions.put(StorageNotification.class, this.storageNotificationProvider);
        mapBuilder.contributions.put(ThemeOverlayController.class, this.themeOverlayControllerGoogleProvider);
        mapBuilder.contributions.put(MediaOutputSwitcherDialogUI.class, this.mediaOutputSwitcherDialogUIProvider);
        mapBuilder.contributions.put(MagnificationImpl.class, this.magnificationImplProvider);
        mapBuilder.contributions.put(WMShell.class, this.wMShellProvider);
        mapBuilder.contributions.put(MediaTttSenderCoordinator.class, this.mediaTttSenderCoordinatorProvider);
        mapBuilder.contributions.put(MediaTttChipControllerReceiver.class, this.mediaTttChipControllerReceiverProvider);
        mapBuilder.contributions.put(MediaTttCommandLineHelper.class, this.mediaTttCommandLineHelperProvider);
        mapBuilder.contributions.put(ChipbarCoordinator.class, this.chipbarCoordinatorProvider);
        mapBuilder.contributions.put(StylusUsiPowerStartable.class, this.stylusUsiPowerStartableProvider);
        mapBuilder.contributions.put(PhysicalKeyboardCoreStartable.class, this.physicalKeyboardCoreStartableProvider);
        mapBuilder.contributions.put(MuteQuickAffordanceCoreStartable.class, this.muteQuickAffordanceCoreStartableProvider);
        mapBuilder.contributions.put(DreamMonitor.class, this.dreamMonitorProvider);
        mapBuilder.contributions.put(AssistantAttentionMonitor.class, this.assistantAttentionMonitorProvider);
        mapBuilder.contributions.put(KeyguardViewConfigurator.class, this.keyguardViewConfiguratorProvider);
        mapBuilder.contributions.put(ScrimController.class, this.scrimControllerProvider);
        mapBuilder.contributions.put(StatusBarHeadsUpChangeListener.class, this.statusBarHeadsUpChangeListenerProvider);
        mapBuilder.contributions.put(BackActionInteractor.class, this.backActionInteractorProvider);
        mapBuilder.contributions.put(KeyguardDismissActionBinder.class, this.keyguardDismissActionBinderProvider);
        mapBuilder.contributions.put(KeyguardDismissBinder.class, this.keyguardDismissBinderProvider);
        mapBuilder.contributions.put(HomeControlsDreamStartable.class, this.homeControlsDreamStartableProvider);
        mapBuilder.contributions.put(BatteryControllerStartable.class, this.batteryControllerStartableProvider);
        mapBuilder.contributions.put(UserFileManagerImpl.class, this.userFileManagerImplProvider);
        mapBuilder.contributions.put(ControlsStartable.class, this.controlsStartableProvider);
        mapBuilder.contributions.put(GoogleServices.class, this.googleServicesProvider);
        mapBuilder.contributions.put(AdaptivePPNService.class, this.adaptivePPNServiceProvider);
        mapBuilder.contributions.put(ActiveUnlockChipbarManager.class, this.activeUnlockChipbarManagerProvider);
        mapBuilder.contributions.put(RefreshRateRequesterBinder.class, this.refreshRateRequesterBinderProvider);
        mapBuilder.contributions.put(AmbientIndicationCoreStartable.class, this.ambientIndicationCoreStartableProvider);
        mapBuilder.contributions.put(ContextualTipsInteractor.class, this.contextualTipsInteractorProvider);
        mapBuilder.contributions.put(Recents.class, this.provideRecentsProvider);
        mapBuilder.contributions.put(ScreenPinningRequest.class, this.screenPinningRequestProvider);
        mapBuilder.contributions.put(CentralSurfaces.class, this.centralSurfacesGoogleProvider);
        mapBuilder.contributions.put(NotificationRemoteInputManager.class, this.notificationRemoteInputManagerProvider);
        mapBuilder.contributions.put(SysuiStatusBarStateController.class, this.statusBarStateControllerImplProvider);
        mapBuilder.contributions.put(NotificationGutsManager.class, this.notificationGutsManagerProvider);
        mapBuilder.contributions.put(VisualInterruptionDecisionProvider.class, this.provideVisualInterruptionDecisionProvider);
        mapBuilder.contributions.put(KeyguardNotificationVisibilityProviderImpl.class, this.keyguardNotificationVisibilityProviderImplProvider);
        mapBuilder.contributions.put(NotificationMemoryMonitor.class, this.notificationMemoryMonitorProvider);
        mapBuilder.contributions.put(KeyguardUpdateMonitor.class, this.keyguardUpdateMonitorProvider);
        mapBuilder.contributions.put(SideFpsProgressBarViewBinder.class, this.sideFpsProgressBarViewBinderProvider);
        mapBuilder.contributions.put(BouncerMessageAuditLogger.class, this.bouncerMessageAuditLoggerProvider);
        mapBuilder.contributions.put(DeviceEntryFaceAuthInteractor.class, this.providesFaceAuthInteractorInstanceProvider);
        mapBuilder.contributions.put(LiftToRunFaceAuthBinder.class, this.liftToRunFaceAuthBinderProvider);
        mapBuilder.contributions.put(KeyguardTransitionCoreStartable.class, this.keyguardTransitionCoreStartableProvider);
        mapBuilder.contributions.put(LockscreenSceneTransitionInteractor.class, this.lockscreenSceneTransitionInteractorProvider);
        mapBuilder.contributions.put(ResourceTrimmer.class, this.resourceTrimmerProvider);
        mapBuilder.contributions.put(BouncerLoggerStartable.class, this.bouncerLoggerStartableProvider);
        mapBuilder.contributions.put(CollapsedStatusBarFragmentStartable.class, this.collapsedStatusBarFragmentStartableProvider);
        mapBuilder.contributions.put(ConnectingDisplayViewModel.class, this.connectingDisplayViewModelProvider);
        mapBuilder.contributions.put(VolumeUI.class, this.volumeUIProvider);
        mapBuilder.contributions.put(KeyboardTouchpadTutorialCoreStartable.class, this.keyboardTouchpadTutorialCoreStartableProvider);
        mapBuilder.contributions.put(KeyguardBlueprintInteractor.class, this.keyguardBlueprintInteractorProvider);
        mapBuilder.contributions.put(KeyguardBlueprintCommandListener.class, this.keyguardBlueprintCommandListenerProvider);
        mapBuilder.contributions.put(MediaCarouselInteractor.class, this.mediaCarouselInteractorProvider);
        mapBuilder.contributions.put(MediaDataProcessor.class, this.mediaDataProcessorProvider);
        mapBuilder.contributions.put(MediaMuteAwaitConnectionCli.class, this.mediaMuteAwaitConnectionCliProvider);
        mapBuilder.contributions.put(NearbyMediaDevicesManager.class, this.nearbyMediaDevicesManagerProvider);
        mapBuilder.contributions.put(PowerUI.class, this.powerUIProvider);
        mapBuilder.contributions.put(QSPipelineCoreStartable.class, this.qSPipelineCoreStartableProvider);
        mapBuilder.contributions.put(RearDisplayDialogController.class, this.rearDisplayDialogControllerProvider);
        mapBuilder.contributions.put(SceneContainerStartable.class, this.sceneContainerStartableProvider);
        mapBuilder.contributions.put(ScrimStartable.class, this.scrimStartableProvider);
        mapBuilder.contributions.put(StatusBarStartable.class, this.statusBarStartableProvider);
        mapBuilder.contributions.put(KeyguardStateCallbackStartable.class, this.keyguardStateCallbackStartableProvider);
        mapBuilder.contributions.put(WindowRootViewVisibilityInteractor.class, this.windowRootViewVisibilityInteractorProvider);
        mapBuilder.contributions.put(ScreenDecorations.class, this.screenDecorationsProvider);
        mapBuilder.contributions.put(KeyguardSmartspaceStartable.class, this.keyguardSmartspaceStartableProvider);
        mapBuilder.contributions.put(SystemActions.class, this.systemActionsProvider);
        mapBuilder.contributions.put(UnfoldInitializationStartable.class, this.unfoldInitializationStartableProvider);
        mapBuilder.contributions.put(ShadeController.class, this.provideShadeControllerProvider);
        mapBuilder.contributions.put(AuthRippleController.class, this.authRippleControllerProvider);
        mapBuilder.contributions.put(ShadeStartable.class, this.shadeStartableProvider);
        mapBuilder.contributions.put(ToastUI.class, this.toastUIProvider);
        mapBuilder.contributions.put(UnfoldTraceLogger.class, this.unfoldTraceLoggerProvider);
        return mapBuilder.build();
    }

    public final GlobalSettingsImpl globalSettingsImpl() {
        return new GlobalSettingsImpl((ContentResolver) this.sysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get(), (CoroutineDispatcher) this.settingsBgDispatcherProvider.get());
    }

    public final HeadsUpNotificationInteractor headsUpNotificationInteractor() {
        return new HeadsUpNotificationInteractor((HeadsUpManagerPhone) this.headsUpManagerPhoneProvider.get(), (DeviceEntryFaceAuthInteractor) this.providesFaceAuthInteractorInstanceProvider.get(), (KeyguardTransitionInteractor) this.keyguardTransitionInteractorProvider.get(), new NotificationsKeyguardInteractor((NotificationsKeyguardViewStateRepository) this.notificationsKeyguardViewStateRepositoryProvider.get()), (ShadeInteractor) this.shadeInteractorImplProvider.get());
    }

    public final ImageExporter imageExporter() {
        return new ImageExporter((ContentResolver) this.sysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get());
    }

    public final void initialize10() {
        Provider provider;
        Provider provider2;
        Provider provider3;
        Provider provider4;
        Provider provider5;
        Provider provider6;
        Provider provider7;
        Provider provider8;
        Provider provider9;
        Provider provider10;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        int i = 1;
        this.factoryProvider42 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 875, i));
        this.factoryProvider43 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 884);
        this.quickSettingsSceneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 872);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 886, 1));
        this.factoryProvider44 = provider;
        provider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 887, 1));
        this.factoryProvider45 = provider2;
        this.shadeSceneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 885);
        this.setOfSceneProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 181, i);
        this.providesBrightnessLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 895);
        this.providesBrightnessTableLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 896);
        this.screenBrightnessDisplayManagerRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 894);
        this.screenBrightnessInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 893);
        this.brightnessPolicyRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 898);
        this.brightnessPolicyEnforcementInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 897);
        this.brightnessSliderViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 892);
        this.gridLayoutTypeRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 901);
        this.gridLayoutTypeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 900);
        this.defaultLargeTilesRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 905);
        this.provideQSTileListLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 908);
        this.provideQSAutoAddLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 909);
        this.providesQSRestoreLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 910);
        this.retailModeSettingsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 911);
        this.defaultTilesQSHostRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 913);
        provider3 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 912, 1));
        this.factoryProvider46 = provider3;
        this.tileSpecSettingsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 907);
        this.installedTilesComponentRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 914);
        this.minimumTilesResourceRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 915);
        this.qsEventLoggerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 918);
        this.qSTileConfigProviderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 917);
        this.currentTilesInteractorImplProvider = new DelegateFactory();
        this.builderProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 921);
        this.qSHostAdapterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 920);
        provider4 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 919, 1));
        this.factoryProvider47 = provider4;
        this.disabledByPolicyInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 923);
        this.qSTileAnalyticsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 924);
        this.qSTileLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 925);
        this.qSTileIntentUserInputHandlerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 926);
        int i2 = 1;
        this.provideAirplaneModeTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 922, i2);
        this.provideDataSaverControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 928);
        this.provideDataSaverTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 927, i2);
        this.ethernetInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 930);
        this.provideInternetTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 929, i2);
        this.provideFlashlightTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 931, i2);
        this.provideLocationTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 932, i2);
        this.provideAlarmTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 933, i2);
        this.provideUiModeNightTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 934, i2);
        this.provideWorkModeTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 935, i2);
        provider5 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 937, 1));
        this.factoryProvider48 = provider5;
        provider6 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 938, 1));
        this.factoryProvider49 = provider6;
        provider7 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 939, 1));
        this.factoryProvider50 = provider7;
        this.provideCameraToggleTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 936, i2);
        this.provideMicrophoneToggleTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 940, i2);
        this.modesDialogDelegateProvider = new DelegateFactory();
        this.modesDialogViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 944);
        DelegateFactory.setDelegate(this.modesDialogDelegateProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 943, 1)));
        this.modesTileUserActionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 942);
        int i3 = 1;
        this.provideModesTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 941, i3);
        this.provideQRCodeScannerTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 945, i3);
        this.issueRecordingStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 947);
        this.panelInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 948);
        this.traceurMessageSenderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 950);
        provider8 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 949, 1));
        this.factoryProvider51 = provider8;
        this.provideIssueRecordingTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 946, i3);
        this.screenRecordRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 952);
        this.provideScreenRecordTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 951, i3);
        this.colorCorrectionRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 954);
        this.provideColorCorrectionTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 953, i3);
        this.colorInversionRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 956);
        this.provideColorInversionTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 955, i3);
        this.fontScalingDialogDelegateProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 958, i3);
        this.provideFontScalingTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 957, i3);
        this.isReduceBrightColorsAvailableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 960);
        this.reduceBrightColorsControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 961);
        this.extraDimDialogDelegateProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 963, i3);
        this.extraDimDialogManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 962);
        this.provideReduceBrightColorsTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 959, i3);
        this.oneHandedModeRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 965);
        this.provideOneHandedModeTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 964, i3);
        this.provideNightDisplayTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 966, i3);
        this.provideBatterySaverTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 967, i3);
        this.bindRotationPolicyWrapperProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 970, i3));
        this.provideAutoRotateSettingsManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 972);
        this.provideDeviceStateAutoRotationLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 973);
        this.deviceStateRotationLockSettingControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 971);
        this.rotationLockControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 969);
        this.cameraAutoRotateRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 974);
        this.cameraSensorPrivacyRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 975);
        this.provideRotationTileViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 968, i3);
        this.newQSTileFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 916);
        this.tileServicesProvider = new DelegateFactory();
        provider9 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 979, 1));
        this.factoryProvider52 = provider9;
        this.customTileAddedSharedPrefsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 980);
        DelegateFactory.setDelegate(this.tileServicesProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 978, 1)));
        provider10 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 977, 1));
        this.factoryProvider53 = provider10;
        this.internetTileViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 982);
        int i4 = 1;
        this.bindInternetTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 981, i4);
        this.dndTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 984, i4);
        new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 985, i4);
        this.bindDndOrModesTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 983, i4);
        this.providerBluetoothLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 989);
    }

    public final void initialize11() {
        Provider provider;
        Provider provider2;
        Provider provider3;
        Provider provider4;
        Provider provider5;
        Provider provider6;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        int i = 1;
        this.bluetoothLoggerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 988, i));
        this.bluetoothRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 990);
        this.bluetoothControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 987);
        this.bluetoothTileDialogRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 993);
        this.provideQBluetoothTileDialogLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 994);
        this.deviceItemInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 992);
        this.deviceItemActionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 995);
        this.bluetoothStateInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 996);
        this.bluetoothAutoOnRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 998);
        this.bluetoothAutoOnInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 997);
        this.audioSharingInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 999);
        this.bluetoothDeviceMetadataInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1000);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1001, 1));
        this.factoryProvider54 = provider;
        this.bluetoothTileDialogViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 991);
        this.bluetoothTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 986, i);
        this.hotspotControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1003);
        this.tileJavaAdapterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1004);
        this.castTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1002, i);
        this.hotspotTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1005, i);
        this.airplaneModeTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1006, i);
        this.dataSaverTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1007, i);
        this.nfcTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1008, i);
        this.deviceControlsTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1009, i);
        this.workModeTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1010, i);
        this.flashlightTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1011, i);
        this.locationTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1012, i);
        this.cameraToggleTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1013, i);
        this.microphoneToggleTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1014, i);
        this.alarmTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1015, i);
        this.uiModeNightTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1016, i);
        this.qRCodeScannerTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1017, i);
        this.recordIssueTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1018, i);
        this.screenRecordTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1019, i);
        this.quickAccessWalletTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1020, i);
        this.colorInversionTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1021, i);
        this.nightDisplayTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1022, i);
        this.reduceBrightColorsTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1023, i);
        this.oneHandedModeTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1024, i);
        this.colorCorrectionTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1025, i);
        this.dreamTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1026, i);
        this.fontScalingTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1027, i);
        this.hearingDevicesUiEventLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1031);
        provider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1030, 1));
        this.factoryProvider55 = provider2;
        this.hearingDevicesCheckerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1032);
        this.hearingDevicesDialogManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1029);
        this.hearingDevicesTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1028, i);
        this.batterySaverTileGoogleProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1033, i);
        this.overlayToggleTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1034, i);
        this.reverseChargingTileProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1035, i);
        this.rotationLockTileGoogleProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1036, i);
        this.qSFactoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 976);
        int i2 = 1;
        DelegateFactory.setDelegate(this.currentTilesInteractorImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 906, i2)));
        this.qSPreferencesRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1038);
        this.qSPreferencesInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1037);
        this.providesPanelsLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1039);
        this.iconTilesInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 904);
        this.iconTilesViewModelImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 903);
        this.fixedColumnsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1042);
        this.fixedColumnsSizeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1041);
        this.fixedColumnsSizeViewModelImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1040);
        this.infiniteGridLayoutProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 902);
        this.iconLabelVisibilityInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1045);
        this.iconLabelVisibilityViewModelImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1044);
        this.paginatedGridRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1047);
        this.paginatedGridInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1046);
        this.paginatedGridViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1043);
        this.tileGridViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 899);
        this.stockTilesRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1050);
        this.iconAndNameCustomRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1051);
        this.editTilesListInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1049);
        this.newTilesAvailabilityInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1053);
        this.tilesAvailabilityInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1052);
        this.editModeViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1048);
        this.quickQuickSettingsRowRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1056);
        this.quickQuickSettingsRowInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1055);
        this.quickQuickSettingsViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1054);
        this.quickSettingsContainerViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 891);
        provider3 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 890, 1));
        this.factoryProvider56 = provider3;
        provider4 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1057, 1));
        this.factoryProvider57 = provider4;
        this.quickSettingsShadeOverlayProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 889);
        provider5 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1059, 1));
        this.factoryProvider58 = provider5;
        provider6 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1060, 1));
        this.factoryProvider59 = provider6;
        this.notificationsShadeOverlayProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1058);
        int i3 = 1;
        this.setOfOverlayProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 888, i3);
        this.notificationInsetsImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1061);
        this.swipeUpAnywhereGestureHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1063);
        this.tapGestureDetectorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1064);
        this.alternateBouncerUdfpsAccessibilityOverlayViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1065, i3);
        this.alternateBouncerDependenciesProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1062, i3);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 175, this.providesWindowRootViewProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 174, this.providesNotificationShadeWindowViewProvider);
        DelegateFactory.setDelegate(this.providesNotificationStackScrollLayoutProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 173, i2)));
        this.shadeControllerSceneImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 113);
        this.statusBarWindowStateControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1068);
        this.dozeServiceHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1069);
        this.lockscreenHostedDreamGestureListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1070);
        this.provideCommunalTouchLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1072);
        this.glanceableHubContainerControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1071);
        this.backActionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1074);
        this.mediaSessionLegacyHelperWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1076);
    }

    public final void initialize12() {
        Provider provider;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.keyguardKeyEventInteractorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1075, 1));
        this.sysUIKeyEventHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1073);
        this.provideBouncerLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1080);
        this.bouncerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1079);
        this.legacyBouncerDependenciesProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1078);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1082, 1));
        this.factoryProvider60 = provider;
        this.composeBouncerDependenciesProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1081);
        this.bouncerViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1077);
        this.notificationShadeWindowViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1067);
        this.shadeControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1066);
        DelegateFactory.setDelegate(this.provideShadeControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 112, 1)));
        this.contextComponentResolverProvider = new DelegateFactory();
        this.provideRecentsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1083);
        this.systemActionsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 111);
        this.provideBackPanelUiThreadContextProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1084);
        this.providsBackGestureTfClassifierProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1085);
        this.gestureRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1086);
        this.navBarHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 107);
        this.taskbarDelegateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1087);
        this.autoHideControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1088);
        int i = 1;
        DelegateFactory.setDelegate(this.navigationBarControllerImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 105, i)));
        this.screenPinningRequestProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1089);
        this.inWindowLauncherUnlockAnimationRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1092);
        this.keyguardSurfaceBehindRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1093);
        this.inWindowLauncherUnlockAnimationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1091);
        this.inWindowLauncherAnimationViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1094);
        this.inWindowLauncherUnlockAnimationManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1090);
        this.provideEduDataStoreScopeProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1098, 1);
        this.userContextualEducationRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1097);
        this.contextualEducationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1096);
        this.inputDeviceRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1101);
        this.keyboardRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1100);
        this.touchpadRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1102);
        this.userInputDeviceRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1099);
        this.tutorialSchedulerRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1103);
        this.keyboardTouchpadEduStatsInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1095);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 104, this.overviewProxyServiceProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 99, this.notificationLockscreenUserManagerImplProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 98, this.keyguardBypassControllerProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 97, this.deviceEntryRepositoryImplProvider);
        DelegateFactory.setDelegate(this.deviceEntryInteractorProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 96, i)));
        this.swipeToDismissInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1107);
        this.fromLockscreenTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1106);
        this.fromPrimaryBouncerTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1108);
        this.keyguardWakeDirectlyToGoneInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1110);
        this.fromAodTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1109);
        this.fromAlternateBouncerTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1111);
        this.fromDozingTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1112);
        this.fromOccludedTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1113);
        this.keyguardDismissTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1105);
        this.keyguardEnabledInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1104);
        this.homeSceneFamilyResolverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 95);
        this.provideResolverMapProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 94, 1);
        DelegateFactory.setDelegate(this.sceneInteractorProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 89, i)));
        this.sceneContainerPluginProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 88);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 87, this.provideSysUiStateProvider);
        DelegateFactory.setDelegate(this.factoryProvider6, new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 85, i));
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 83, this.tunerServiceImplProvider);
        DelegateFactory.setDelegate(this.dozeParametersProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 81, i)));
        this.provideShadeWindowLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1114);
        DelegateFactory.setDelegate(this.notificationShadeWindowControllerImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 80, i)));
        this.reverseChargingViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 79);
        this.provideReverseChargingViewControllerOptionalProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 78);
        this.provideHealthManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1115);
        this.swipeStatusBarAwayGestureHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1119);
        this.ongoingCallControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1118);
        this.connectedDisplayInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1122);
        this.systemEventCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1121);
        this.provideSystemStatusAnimationSchedulerLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1124);
        this.systemStatusAnimationSchedulerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1123);
        int i2 = 1;
        this.bindSystemStatusAnimationSchedulerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1120, i2));
        this.statusBarLocationPublisherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1125);
        this.factoryProvider61 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1126);
        this.collapsedStatusBarInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1128);
        this.lightsOutInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1129);
        this.activityTaskManagerTasksRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1134);
        this.provideMediaProjectionLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1135);
        this.mediaProjectionManagerRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1133);
        this.provideChipsLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1136);
        this.screenRecordChipInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1132);
        this.mediaProjectionChipInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1138);
        this.endMediaProjectionDialogHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1139);
        this.shareToAppChipViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1137);
        this.screenRecordChipViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1131);
        this.provideMediaRouterLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1143);
        this.mediaRouterRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1142);
        this.mediaRouterChipInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1141);
        this.castToOtherDeviceChipViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1140);
        this.callChipInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1145);
        this.callChipViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1144);
        this.demoRonChipViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1146);
        this.ongoingActivityChipsViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1130);
        this.collapsedStatusBarViewModelImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1127);
        this.collapsedStatusBarViewBinderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1147);
        this.statusBarHideIconsForBouncerManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1148);
        this.darkIconRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1149);
        this.provideCollapsedSbFragmentLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1150);
        this.collapsedStatusBarFragmentProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1117, i2);
        this.statusBarInitializerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1116);
    }

    public final void initialize13() {
        Provider provider;
        Provider provider2;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        int i = 1;
        this.statusBarSignalPolicyProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1151, i));
        this.accessibilityFloatingMenuControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1152);
        this.provideAudioManagerIntentsReceiverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1156);
        this.volumeLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1157);
        this.provideAudioRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1155);
        this.provideNotificationsSoundPolicyInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1161);
        this.provideAudioVolumeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1160);
        this.provideAudioSharingRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1162);
        this.audioSharingInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1159);
        this.audioSharingInteractorEmptyImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1163);
        this.provideAudioSharingInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1158);
        this.volumeDialogControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1154);
        this.volumePanelFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1165);
        this.volumeNavigatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1164);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1166, 1));
        this.factoryProvider62 = provider;
        this.volumeDialogRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1168);
        this.volumeDialogInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1167);
        this.volumeDialogComponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1153);
        this.cameraLauncherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1170);
        this.centralSurfacesCommandQueueCallbacksProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1169);
        this.provideSensorPrivacyControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1171);
        this.wiredChargingRippleControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1172);
        this.lightRevealScrimRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1175);
        this.lightRevealScrimInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1174);
        this.lightRevealScrimViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1173, i);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 66, this.centralSurfacesGoogleProvider);
        int i2 = 1;
        DelegateFactory.setDelegate(this.optionalOfCentralSurfacesProvider, new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 65, i2));
        this.activityStarterInternalImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 64);
        this.legacyActivityStarterInternalImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1176);
        DelegateFactory.setDelegate(this.activityStarterImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 63, i2)));
        this.headlessSystemUserModeImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1177);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 61, this.userSwitcherInteractorProvider);
        DelegateFactory.setDelegate(this.userSwitcherControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 60, i2)));
        provider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1179, 1));
        this.factoryProvider63 = provider2;
        this.keyguardDisplayManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1178);
        this.screenOnCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1180);
        this.fromDreamingTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1182);
        this.dreamViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1181);
        this.keyguardSurfaceBehindInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1185);
        this.keyguardSurfaceBehindParamsApplierProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1184);
        this.windowManagerLockscreenVisibilityManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1183);
        this.windowManagerOcclusionManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1186);
        DelegateFactory.setDelegate(this.newKeyguardViewMediatorProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 55, i2)));
        this.windowManagerLockscreenVisibilityInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1188);
        this.statusBarKeyguardViewManagerInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1187);
        DelegateFactory.setDelegate(this.statusBarKeyguardViewManagerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 54, i2)));
        this.udfpsHapticsSimulatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1189);
        this.udfpsShellProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1190);
        this.providesPluginExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1191);
        this.providesOverlapDetectorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1193);
        this.singlePointerTouchProcessorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1192);
        this.udfpsKeyguardAccessibilityDelegateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1194);
        this.provideDeviceEntryIconLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1197);
        this.deviceEntryIconLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1196);
        int i3 = 1;
        this.deviceEntryUdfpsTouchOverlayViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1195, i3);
        this.defaultUdfpsTouchOverlayViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1198, i3);
        DelegateFactory.setDelegate(this.udfpsControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 53, i2)));
        this.provideUdfpsLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1200);
        int i4 = 1;
        this.udfpsLoggerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1199, i4);
        this.logContextInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1201);
        this.faceSettingsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1204);
        this.promptRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1203);
        this.promptSelectorInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1202);
        this.providesCredentialInteractorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1206, i4));
        this.credentialViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1205, i4);
        this.biometricStatusRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1209);
        this.providesBiometricStatusInteractorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1208, i4));
        this.promptViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1207, i4);
        DelegateFactory.setDelegate(this.authControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 47, i2)));
        this.activeUnlockConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1210);
        this.provideFingerprintInteractiveToAuthProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1211);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 46, this.keyguardUpdateMonitorProvider);
        DelegateFactory.setDelegate(this.keyguardStateControllerImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 45, i2)));
        this.dozeTransitionListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1212);
        this.dreamOverlayCallbackControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1213);
        DelegateFactory.setDelegate(this.keyguardRepositoryImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 44, i2)));
        this.fromGoneTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1214);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 43, this.keyguardInteractorProvider);
        DelegateFactory.setDelegate(this.statusBarStateControllerImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 41, i2)));
        this.dockAlignmentControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1215);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 38, this.dockObserverProvider);
        DelegateFactory.setDelegate(this.falsingDataProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 27, i2)));
        this.asyncSensorManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1218);
        int i5 = 1;
        this.postureDependentProximitySensorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1217, i5);
        this.proximitySensorImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1219, i5);
        this.provideProximitySensorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1216, i5);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 26, this.falsingCollectorImplProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 12, this.powerInteractorProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 9, this.featureFlagsClassicReleaseProvider);
        DelegateFactory.setDelegate(this.provideUserTrackerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 8, i2)));
        this.activityTaskManagerProxyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1220);
        DelegateFactory.setDelegate(this.controlsListingControllerImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 7, i2)));
        int i6 = 1;
        this.controlsProviderSelectorActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 4, i6);
        this.controlsFavoritingActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1221, i6);
        this.controlsEditingActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1222, i6);
        this.controlsRequestDialogProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1223, i6);
        this.controlsSettingsDialogManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1225);
        this.controlsActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1224, i6);
    }

    public final void initialize14() {
        Provider provider;
        Provider provider2;
        Provider provider3;
        Provider provider4;
        Provider provider5;
        Provider provider6;
        Provider provider7;
        Provider provider8;
        Provider provider9;
        Provider provider10;
        Provider provider11;
        Provider provider12;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        int i = 1;
        this.mediaProjectionAppSelectorActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1226, i);
        this.mediaProjectionPermissionActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1227, i);
        this.launchNoteTaskActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1228, i);
        this.launchNotesRoleSettingsTrampolineActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1229, i);
        this.createNoteTaskShortcutActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1230, i);
        this.walletActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1231, i);
        this.tunerActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1232, i);
        this.foregroundServicesDialogProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1233, i);
        this.workLockActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1234, i);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1236, 1));
        this.factoryProvider64 = provider;
        this.brightnessDialogProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1235, i);
        this.usbDebuggingActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1237, i);
        this.usbDebuggingSecondaryUserActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1238, i);
        this.usbPermissionActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1239, i);
        this.usbConfirmActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1240, i);
        this.usbAccessoryUriActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1241, i);
        this.createUserActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1242, i);
        this.peopleTileRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1244);
        this.peopleWidgetRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1245);
        this.peopleSpaceActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1243, i);
        this.longScreenshotDataProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1247);
        this.bindSystemUiProxyProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1249, i));
        this.actionIntentExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1248);
        this.longScreenshotActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1246, i);
        this.appClipsTrampolineActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1250, i);
        this.appClipsCrossProcessHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1252);
        this.assistContentRequesterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1253);
        this.appClipsActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1251, i);
        this.launchConversationActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1254, i);
        this.sensorUseStartedActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1255, i);
        this.communalEditModeViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1257);
        provider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1258, 1));
        this.factoryProvider65 = provider2;
        this.editWidgetsActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1256, i);
        this.switchToManagedProfileForCallActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1259, i);
        this.provideInputDeviceTutorialLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1263);
        this.touchpadGesturesInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1262);
        this.keyboardTouchpadConnectionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1264);
        provider3 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1261, 1));
        this.viewModelFactoryAssistedProvider = provider3;
        this.keyboardTouchpadTutorialActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1260, i);
        this.gridLayoutSelectorViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1266);
        this.qSActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1265, i);
        this.touchpadTutorialActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1267, i);
        this.shortcutHelperStateRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1270);
        this.shortcutHelperStateInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1269);
        this.shortcutHelperCategoriesRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1272);
        this.shortcutHelperCategoriesInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1271);
        this.shortcutHelperActivityProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1268, i);
        this.systemUISecondaryUserServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1273, i);
        provider4 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1275, 1));
        this.factoryProvider66 = provider4;
        this.packageChangeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1277);
        this.homeControlsComponentInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1276);
        this.homeControlsDreamServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1274, i);
        provider5 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1279, 1));
        this.factoryProvider67 = provider5;
        this.providesScreenshotViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1283);
        this.providesThumbnailObserverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1284);
        provider6 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1282, 1));
        this.factoryProvider68 = provider6;
        this.providesScrnshtNotifSmartActionsProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1286, i);
        this.screenshotSmartActionsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1285);
        this.imageCaptureImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1287);
        provider7 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1290, 1));
        this.factoryProvider69 = provider7;
        provider8 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1289, 1));
        this.factoryProvider70 = provider8;
        provider9 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1288, 1));
        this.factoryProvider71 = provider9;
        provider10 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1291, 1));
        this.factoryProvider72 = provider10;
        this.bindPackageLabelIconProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1292, i));
        this.bindProfileFirstRunResourcesProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1293, i));
        this.bindProfileFirstRunSettingsProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1294, i));
        this.bindProfileTypeRepositoryProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1295, i));
        this.screenshotSoundProviderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1297);
        this.screenshotSoundControllerImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1296, i);
        this.messagesProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1298);
        provider11 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1281, 1));
        this.factoryProvider73 = provider11;
        SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1300, 1));
        provider12 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1299, 1));
        this.factoryProvider75 = provider12;
        this.bindDisplayContentRepositoryProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1302, i));
        this.bindCapturePolicyListProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1303);
        this.bindScreenshotRequestProcessorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1301);
        this.takeScreenshotExecutorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1280);
        this.takeScreenshotServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1278, i);
        this.appClipsScreenshotHelperServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1304, i);
        this.appClipsServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1305, i);
        this.screenshotProxyServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1306, i);
        this.noteTaskControllerUpdateServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1307, i);
        this.noteTaskBubblesServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1308, i);
        this.walletContextualSuggestionsControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1310);
        this.walletContextualLocationsServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1309, i);
        this.dozeServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1311, i);
        this.provideLongRunningLooperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1314);
        this.provideLongRunningDelayableExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1313);
        this.imageWallpaperProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1312, i);
        this.keyguardLifecyclesDispatcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1316);
        this.windowManagerLockscreenVisibilityViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1317);
        this.keyguardSurfaceBehindViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1318);
        this.keyguardStateCallbackStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1319);
        this.keyguardStateCallbackInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1320);
    }

    public final void initialize15() {
        Provider provider;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        int i = 1;
        this.keyguardServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1315, i);
        this.bouncerlessScrimControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1322);
        this.dreamOverlayServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1321, i);
        this.notificationListenerWithPluginsProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1323, i);
        this.systemUIServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1324, i);
        this.systemUIAuxiliaryDumpServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1325, i);
        this.provideLongRunningExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1327);
        this.recordingServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1326, i);
        this.issueRecordingServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1328, i);
        this.contentResolverWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1332);
        this.factoryProvider76 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1331);
        this.columbusSettingsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1330);
        this.columbusStructuredDataManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1333);
        this.columbusTargetRequestServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1329, i);
        this.lowLightClockDreamServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1334, i);
        this.lockscreenWallpaperDreamServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1335, i);
        this.launcherTileServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1336, i);
        this.healthManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1338);
        this.healthServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1337, i);
        this.batteryEventModuleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1341);
        this.provideHalDataSourceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1343);
        this.settingsDataSourceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1344);
        this.provideSystemEventDataSourceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1342);
        this.batteryEventStateControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1340);
        this.batteryEventServiceProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1339, i);
        this.overviewProxyRecentsImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1345);
        this.smartActionsReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1346, i);
        this.mediaOutputDialogReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1347, i);
        this.peopleSpaceWidgetPinnedReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1348, i);
        this.peopleSpaceWidgetProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1349, i);
        this.guestResetOrExitSessionReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1350, i);
        this.hearingDevicesDialogReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1351, i);
        this.extraDimDialogReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1352, i);
        this.keyboardShortcutsReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1353, i);
        this.healthUpdateReceiverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1354, i);
        DelegateFactory.setDelegate(this.contextComponentResolverProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 3, 1)));
        this.uiOffloadThreadProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1356);
        this.dependencyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1355);
        int i2 = 1;
        this.provideContextualEducationInteractorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1357, i2);
        this.keyboardTouchpadEduInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1359);
        this.provideKeyboardTouchpadEduInteractorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1358, i2);
        this.contextualEduViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1362);
        this.contextualEduUiCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1361);
        this.provideContextualEduUiCoordinatorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1360, i2);
        this.provideCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1363);
        this.provideCoreStartableProvider2 = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1364, i2);
        this.shortcutHelperActivityStarterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1366);
        this.starterProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1365, i2);
        this.repoProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1367, i2);
        this.coroutineScopeCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1368);
        this.broadcastDispatcherStartableProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1369, i2);
        this.deviceEntrySideFpsOverlayInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1371);
        this.sideFpsLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1374);
        this.sideFpsSensorInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1373);
        this.sideFpsProgressBarViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1372);
        this.sideFpsOverlayViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1370);
        this.alternateBouncerWindowViewModelProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1376, i2);
        this.alternateBouncerViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1375);
        this.communalSceneTransitionRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1378);
        this.communalSceneTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1377);
        this.communalLoggerStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1379);
        this.communalSceneStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1380);
        this.communalDreamStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1381);
        this.communalAppWidgetHostStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1382);
        this.communalBackupRestoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1383);
        this.communalOngoingContentStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1384);
        this.communalMetricsStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1385);
        this.volumeUIProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1387);
        this.batterySaverConfirmationDialogProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1390, i2);
        this.severeLowBatteryNotificationProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1391, i2);
        this.powerNotificationWarningsGoogleImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1389);
        this.powerUIProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1388);
        this.rearDisplayDialogControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1392);
        this.privacyDotViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1394);
        this.privacyDotDecorProviderFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1395);
        this.provideScreenDecorationsLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1398);
        this.screenDecorationsLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1397);
        this.faceScanningProviderFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1396);
        this.screenDecorationsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1393);
        this.provideToastLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1400);
        this.toastUIProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1399);
        this.configurationControllerStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1386);
        this.falsingCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1401);
        this.featureFlagsReleaseStartableProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1402, i2);
        this.flagDependenciesNotifierProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1404);
        this.flagDependenciesProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1403);
        this.motionToolStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1405);
        this.qSFragmentLegacyProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1407, i2);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1409, 1));
        this.factoryProvider77 = provider;
        this.qSFragmentComposeProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1408, i2);
        this.qSFragmentStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1406);
        this.carrierConfigCoreStartableProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1410, i2);
        this.adapterProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1412, i2);
        this.userSwitcherDialogCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1411);
        this.biometricNotificationDialogFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1415);
        this.biometricNotificationBroadcastReceiverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1414);
        this.provideFingerprintReEnrollNotificationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1416);
        this.biometricNotificationServiceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1413);
        this.clipboardOverlayControllerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1418, i2);
        this.clipboardListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1417);
    }

    public final void initialize16() {
        Provider provider;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        int i = 1;
        this.instantAppNotifierProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1419, i));
        this.keyboardUIProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1420);
        this.taskSwitchInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1423);
        this.taskSwitcherNotificationCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1422);
        this.mediaProjectionTaskSwitcherCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1421);
        this.keyguardBiometricLockoutLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1424);
        this.latencyTesterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1425);
        this.displaySwitchLatencyTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1426);
        this.immersiveModeConfirmationProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1427, i);
        this.ringtonePlayerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1428);
        this.gesturePointerEventDetectorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1430);
        this.gesturePointerEventListenerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1429, i);
        this.shortcutKeyDispatcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1431);
        this.sliceBroadcastRelayHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1432);
        this.storageNotificationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1433);
        this.themeOverlayApplierProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1435);
        this.themeOverlayControllerGoogleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1434);
        this.mediaOutputSwitcherDialogUIProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1436);
        this.modeSwitchesControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1438);
        this.magnificationImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1437);
        this.wMShellProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1439);
        this.provideMediaTttSenderLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1442);
        this.mediaTttSenderLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1441);
        this.mediaTttFlagsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1443);
        this.mediaTttSenderUiEventLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1444);
        this.mediaTttSenderCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1440);
        this.provideMediaTttReceiverLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1447);
        this.mediaTttReceiverLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1446);
        this.mediaTttReceiverUiEventLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1448);
        this.mediaTttChipControllerReceiverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1445);
        this.mediaTttCommandLineHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1449);
        this.stylusUsiPowerUIProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1451);
        this.stylusUsiPowerStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1450);
        this.keyboardBacklightInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1455);
        this.backlightDialogViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1454);
        this.keyboardBacklightDialogCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1453);
        this.stickyKeyDialogFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1457);
        this.userAwareSecureSettingsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1459);
        this.provideKeyboardLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1460);
        this.stickyKeysRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1458);
        this.stickyKeysIndicatorCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1456);
        this.keyboardDockingIndicationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1463);
        this.keyboardDockingIndicationViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1462);
        this.keyboardDockingIndicationViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1461);
        this.physicalKeyboardCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1452);
        this.muteQuickAffordanceCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1464);
        this.provideMonitorTableLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1467);
        this.provideSystemUserMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1466);
        this.restartDozeListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1468);
        this.dreamMonitorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1465, i);
        this.assistantAttentionMonitorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1469, i);
        this.statusBarHeadsUpChangeListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1470);
        this.keyguardDismissActionBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1471);
        this.keyguardDismissBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1472);
        this.homeControlsDreamStartableProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1473, i);
        this.batteryControllerStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1474);
        this.controlsStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1475);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1478, 1));
        this.factoryProvider78 = provider;
        this.wakeModeProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1479);
        this.assistInvocationEffectProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1482);
        this.cameraActionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1481);
        this.cameraVisibilityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1480);
        this.chargingStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1483);
        this.usbStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1484);
        this.launchOpaProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1487);
        this.settingsActionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1486);
        this.setupWizardProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1485);
        this.setupWizardActionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1488);
        this.gestureConfigurationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1491);
        this.jNIGestureSensorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1490);
        this.snapshotConfigurationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1493);
        this.cHREGestureSensorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1492);
        this.providesGestureSensorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1489);
        this.serviceConfigurationGoogleProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1477, i);
        this.provideQuickTapActionsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1497);
        this.provideQuickTapWakeLockProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1498);
        this.noOpGestureSensorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1501);
        this.provideGestureSensorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1500);
        this.gateFetcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1502);
        this.gestureControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1499);
        this.actionFetcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1503);
        this.columbusManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1496);
        this.broadcastFetcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1506);
        this.userFetcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1505);
        this.contentFetcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1507);
        this.backupManagerProxyProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1508);
        this.columbusSettingsFetcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1504);
        this.columbusStarterImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1495);
        this.silenceAlertsDisabledProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1514);
        this.dismissTimerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1513);
        this.snoozeAlarmProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1515);
        this.silenceCallProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1516);
        this.settingsActionProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1517);
        this.provideFullscreenActionsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1512);
        this.unpinNotificationsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1518);
        this.assistInvocationEffectProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1522);
        this.launchOpaProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1521);
        this.manageMediaProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1523);
        this.takeScreenshotProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1524);
        this.launchOverviewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1525);
    }

    public final void initialize17() {
        Provider provider;
        Provider provider2;
        Provider provider3;
        Provider provider4;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        int i = 1;
        this.openNotificationShadeProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1526, i));
        this.keyguardVisibilityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1528);
        this.launchAppProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1527);
        this.toggleFlashlightProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1529);
        this.provideUserSelectedActionsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1520);
        this.powerManagerWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1530);
        this.userSelectedActionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1519);
        this.provideColumbusActionsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1511);
        this.hapticClickProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1531);
        this.userActivityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1532);
        this.flagEnabledProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1533);
        this.proximityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1535);
        this.keyguardProximityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1534);
        this.setupWizardProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1536);
        this.telephonyActivityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1537);
        this.powerStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1539);
        this.cameraVisibilityProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1538);
        this.powerSaveStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1540);
        this.sensorConfigurationProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1547);
        this.lowSensitivitySettingAdjustmentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1546);
        this.provideGestureAdjustmentsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1545);
        this.gestureConfigurationProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1544);
        this.cHREGestureSensorProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1543);
        this.aiAiCHREGestureSensorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1549);
        this.cHREGestureSensorDelegatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1548);
        this.gestureSensorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1550);
        this.provideGestureSensorProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1542);
        this.chargingStateProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1551);
        this.usbStateProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1552);
        this.systemKeyPressProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1553);
        this.provideInputMonitorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1555, i);
        this.screenTouchProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1554);
        this.gestureControllerProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1541);
        this.columbusServiceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1510);
        this.columbusServiceWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1509);
        this.provideColumbusStarterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1494);
        this.googleServicesProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1476);
        this.vpnNetworkMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1557);
        this.vpnPackageMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1558);
        this.adaptivePPNServiceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1556);
        this.activeUnlockChipbarManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1559);
        this.refreshRateInteractorProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1561, i);
        this.refreshRateRequesterBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1560);
        this.ambientIndicationCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1562);
        this.contextualTipsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1564);
        this.setupWizardRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1565);
        this.contextualTipsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1563);
        this.notificationMemoryDumperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1567);
        this.notificationMemoryLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1568);
        this.notificationMemoryMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1566);
        this.sideFpsProgressBarProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1570);
        this.sideFpsProgressBarViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1569);
        this.bouncerMessageAuditLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1571);
        this.liftToRunFaceAuthBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1572);
        this.fromDreamingLockscreenHostedTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1574);
        this.fromGlanceableHubTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1575);
        this.keyguardTransitionAuditLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1576);
        this.keyguardTransitionBootInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1577);
        this.deviceConfigRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1580);
        this.deviceConfigInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1579);
        this.navigationRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1582);
        this.navigationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1581);
        this.statusBarDisableFlagsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1578);
        this.keyguardTransitionCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1573);
        this.lockscreenSceneTransitionRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1584);
        this.lockscreenSceneTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1583);
        this.resourceTrimmerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1585);
        this.bouncerLoggerStartableProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1586, i);
        this.collapsedStatusBarFragmentStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1587);
        this.limitedEdgeToEdgeProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1589, i);
        this.connectingDisplayViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1588);
        this.tutorialSchedulerInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1592);
        this.tutorialNotificationCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1591);
        this.keyboardTouchpadTutorialCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1590);
        this.keyguardBlueprintCommandListenerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1593, i);
        this.mediaMuteAwaitConnectionCliProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1594);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1598, 1));
        this.factoryProvider79 = provider;
        this.accessibilityQsShortcutsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1597);
        this.accessibilityTilesInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1596);
        provider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1600, 1));
        this.factoryProvider80 = provider2;
        provider3 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1601, 1));
        this.factoryProvider81 = provider3;
        this.castAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1602);
        this.dataSaverAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1603);
        this.deviceControlsControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1605);
        this.deviceControlsAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1604);
        this.hotspotAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1606);
        this.nightDisplayAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1607);
        this.reduceBrightColorsAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1608);
        this.walletControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1610);
        this.walletAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1609);
        this.workTileRestoreProcessorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1612);
        this.workTileAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1611);
        this.reverseChargingAutoAddableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1613);
        provider4 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1615, 1));
        this.factoryProvider82 = provider4;
        this.autoAddSettingRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1614);
        this.autoAddInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1599);
        this.qSSettingsRestoredBroadcastRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1617);
        this.restoreReconciliationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1616);
        this.qSPipelineCoreStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1595);
        this.sceneContainerStartableProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 1618);
    }

    public final void initialize5() {
        Provider provider;
        Provider provider2;
        this.provideShadeLockscreenInteractorProvider = new DelegateFactory();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.naturalScrollingSettingObserverProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 420, 1));
        this.providesQSMediaHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 423);
        this.providesQuickQSMediaHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 424);
        this.provideQSFragmentDisableLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 425);
        this.qSPipelineFlagsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 427);
        this.provideQuickSettingsLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 426);
        this.provideQSConfigLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 428);
        this.fgsManagerControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 430);
        this.footerActionsControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 429);
        this.securityControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 434);
        this.qSSecurityFooterUtilsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 433);
        this.securityRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 435);
        this.foregroundServicesRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 436);
        this.userInfoControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 438);
        this.userSwitcherRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 437);
        this.footerActionsInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 432);
        this.extensionControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 441);
        this.globalActionsDialogLiteProvider = new DelegateFactory();
        int i = 1;
        this.globalActionsImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 442, i);
        this.globalActionsComponentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 440);
        this.sysuiColorExtractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 443);
        this.providesStatusBarWindowViewInflaterProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 446, i));
        this.sysUICutoutProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 448);
        this.statusBarContentInsetsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 447);
        this.fragmentServiceProvider = new DelegateFactory();
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 450, 1));
        this.factoryProvider23 = provider;
        DelegateFactory.setDelegate(this.fragmentServiceProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 449, 1)));
        provider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 445, 1));
        this.factoryProvider24 = provider2;
        this.statusBarWindowControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 444);
        this.ringerModeTrackerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 451);
        this.provideShadeControllerProvider = new DelegateFactory();
        this.globalActionsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 453);
        this.globalActionsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 452);
        DelegateFactory.setDelegate(this.globalActionsDialogLiteProvider, new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 439, 1));
        this.isPMLiteEnabledProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 454);
        this.factoryProvider25 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 431);
        int i2 = 1;
        this.qSImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 422, i2);
        this.qSSceneAdapterImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 421);
        this.lockscreenShadeTransitionControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 375);
        this.pulseExpansionHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 374);
        this.dynamicPrivacyControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 455);
        this.provideShadeLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 456);
        this.builderProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 457, i2);
        this.unlockedScreenOffAnimationControllerProvider = new DelegateFactory();
        this.statusBarTouchableRegionManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 458);
        this.bindEventManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 460);
        this.conversationNotificationManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 459);
        this.notificationPersonExtractorPluginBoundaryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 464);
        this.peopleNotificationIdentifierImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 463);
        this.highPriorityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 462);
        this.peopleSpaceWidgetManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 465);
        this.channelEditorDialogControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 466);
        this.assistantFeedbackControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 467);
        this.provideNotificationInterruptLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 471);
        this.keyguardNotificationVisibilityProviderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 472);
        int i3 = 1;
        this.bindEventLogProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 473, i3));
        this.notificationInterruptStateProviderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 470);
        this.avalancheProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 475);
        this.provideSecureSettingsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 478);
        this.provideSystemSettingsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 479);
        this.provideNotificationSettingsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 477);
        this.provideNotificationSettingsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 476);
        this.visualInterruptionDecisionProviderImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 474, i3);
        this.provideVisualInterruptionDecisionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 469);
        this.zenModeControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 480);
        this.provideSensitiveNotificationProtectionLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 482);
        this.sensitiveNotificationProtectionControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 481);
        this.provideBubblesManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 468);
        this.shadeAnimationRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 487);
        this.shadeAnimationInteractorSceneContainerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 486);
        this.shadeAnimationInteractorLegacyImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 488);
        this.provideShadeAnimationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 485);
        this.seenNotificationsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 489);
        this.visibilityLocationProviderDelegatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 490);
        this.provideVisualStabilityLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 491);
        this.visualStabilityCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 484);
        this.onUserInteractionCallbackImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 483);
        this.windowRootViewVisibilityRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 493);
        this.windowRootViewVisibilityInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 492);
        this.notificationGutsManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 461);
        this.providesNotificationsQuickSettingsContainerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 495);
        this.providesShadeHeaderViewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 497);
        this.provideStatusBarIconListProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 499);
        this.statusBarPipelineFlagsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 500);
        this.provideDeviceBasedSatelliteInputLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 507);
        this.provideVerboseDeviceBasedSatelliteInputLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 508);
        this.deviceBasedSatelliteRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 506);
        this.demoDeviceBasedSatelliteDataSourceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 510);
        this.demoDeviceBasedSatelliteRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 509);
        this.deviceBasedSatelliteRepositorySwitcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 505);
        this.carrierConfigTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 512);
        this.mobileIconsInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 511);
        this.wifiInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 513);
        this.provideDeviceBasedSatelliteTableLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 514);
        this.deviceBasedSatelliteInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 504);
        this.deviceBasedSatelliteViewModelImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 503);
        this.deviceBasedSatelliteBindableIconProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 502);
    }

    public final void initialize6() {
        Provider provider;
        Provider provider2;
        Provider provider3;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.bindableIconsRegistryImplProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 501, 1));
        this.statusBarIconControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 498);
        this.airplaneModeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 519);
        this.airplaneModeViewModelImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 518);
        this.provideMobileViewLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 523);
        this.mobileViewLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 522);
        this.provideVerboseMobileViewLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 525);
        this.verboseMobileViewLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 524);
        this.connectivityConstantsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 526);
        this.mobileIconsViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 521);
        this.provideFirstMobileSubShowingNetworkTypeIconProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 520);
        this.wifiConstantsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 527);
        this.wifiViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 517);
        this.wifiUiAdapterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 516);
        this.mobileUiAdapterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 528);
        this.callbackHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 531);
        this.provideAccessPointControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 532);
        this.mobileSignalControllerFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 533);
        this.internetDialogManagerProvider = new DelegateFactory();
        this.toastFactoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 536);
        this.provideIndividualSensorPrivacyControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 539);
        this.appOpsControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 538);
        this.locationControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 537);
        this.wifiStateWorkerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 540);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 535, 1));
        this.factoryProvider26 = provider;
        DelegateFactory.setDelegate(this.internetDialogManagerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 534, 1)));
        this.provideStatusBarNetworkControllerBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 541);
        this.networkControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 530);
        this.mobileContextProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 529);
        this.factoryProvider27 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 515);
        this.privacyConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 543);
        this.providePrivacyLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 545);
        this.appOpsPrivacyItemMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 544);
        this.privacyItemControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 542);
        this.providesOngoingPrivacyChipProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 546);
        this.privacyDialogControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 547);
        this.privacyDialogControllerV2Provider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 548);
        this.providesStatusIconContainerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 549);
        this.provideTimeTickHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 550);
        this.providesBatteryMeterViewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 552);
        this.providesBatteryMeterViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 551);
        this.provideShadeCarrierLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 554);
        this.shadeCarrierGroupControllerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 553);
        this.provideCarrierTextManagerLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 555);
        this.subscriptionManagerSlotIndexResolverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 556);
        this.providesCombinedShadeHeadersConstraintManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 557);
        this.nextAlarmControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 558);
        this.shadeHeaderControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 496);
        this.providesNotificationStackScrollLayoutProvider = new DelegateFactory();
        this.provideAllowNotificationLongPressProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 560);
        this.notificationListenerSettingsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 564);
        this.notificationListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 563);
        this.targetSdkResolverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 565);
        this.notifCoordinatorsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 567);
        this.notifInflationErrorManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 569);
        this.provideNotifInflationLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 570);
        this.notifInflaterImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 568);
        this.sectionHeaderVisibilityProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 572);
        this.notifViewBarnProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 573);
        provider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 571, 1));
        this.shadeViewManagerFactoryProvider = provider2;
        this.notifPipelineInitializerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 566);
        this.notifBindPipelineProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 574);
        int i = 1;
        this.provideNotifRemoteViewCacheProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 578, i));
        this.provideSmartReplyControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 580);
        this.remoteInputUriControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 581);
        this.provideNotificationRemoteInputLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 583);
        this.remoteInputControllerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 582);
        this.provideNotifInteractionLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 584);
        this.notificationRemoteInputManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 579);
        this.provideNotifInflationLooperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 586);
        this.provideNotifInflationExecutorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 585);
        this.smartReplyConstantsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 587);
        this.provideActivityManagerWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 588);
        this.provideDevicePolicyManagerWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 589);
        this.keyguardDismissUtilProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 590);
        this.notificationStackInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 595);
        this.notificationViewFlipperViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 594);
        this.notificationViewFlipperFactoryProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 593, i);
        this.provideNotifRemoteViewsFactoryContainerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 592, i));
        provider3 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 591, 1));
        this.providerProvider = provider3;
        this.provideHeadsUpStyleManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 596, i));
        this.notificationContentInflaterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 577);
        this.richOngoingNotificationContentExtractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 599);
        this.provideRonContentExtractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 598);
        this.richOngoingNotificationViewInflaterImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 600);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 597);
        this.provideNotificationRowContentBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 576);
        this.rowContentBindStageProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 575);
        this.provideNotificationPanelLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 602);
        this.provideLegacyLoggerOptionalProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 601);
        this.rowInflaterTaskProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 604, i);
        this.iconManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 605);
        this.notificationRowBinderImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 603);
        this.headsUpViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 606);
        this.animatedImageNotificationManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 607);
        this.notificationsControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 562);
        this.notificationsControllerStubProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 608, i);
    }

    public final void initialize7() {
        Provider provider;
        Provider provider2;
        Provider provider3;
        Provider provider4;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        int i = 1;
        this.provideNotificationsControllerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 561, i));
        this.colorUpdateLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 609);
        this.groupExpansionManagerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 610);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 612, 1));
        this.factoryProvider28 = provider;
        this.provideGlobalConfigurationStateProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 611);
        this.notificationShelfInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 614);
        this.accessibilityInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 615);
        this.notificationShelfViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 613);
        this.unfoldTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 618);
        this.hideNotificationsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 617);
        this.hideListViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 616);
        this.provideOptionalProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 619);
        new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 620, i);
        this.remoteInputRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 622);
        this.remoteInputInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 621);
        this.provideZenModeRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 623);
        this.provideZenIconLoaderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 624);
        this.notificationStatsLoggerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 625);
        this.headsUpNotificationIconViewStateRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 626);
        this.statusBarIconViewBindingFailureTrackerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 627);
        this.provideAssistUtilsProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 630);
        this.assistManagerGoogleProvider = new DelegateFactory();
        this.timeoutManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 632);
        this.assistantPresenceHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 633);
        this.optionalOfCentralSurfacesProvider = new DelegateFactory();
        this.phoneStateMonitorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 636);
        this.googleAssistLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 635);
        this.touchInsideHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 634);
        this.overlayUiHostProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 637);
        this.provideParentViewGroupProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 639);
        this.keyguardBottomAreaInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 641);
        this.overlappedElementControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 640);
        this.lightnessProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 642);
        this.scrimControllerProvider2 = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 638);
        this.flingVelocityWrapperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 643);
        this.navigationBarControllerImplProvider = new DelegateFactory();
        this.navBarFaderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 645);
        this.navBarFadeControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 644);
        this.ngaUiControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 631);
        this.opaEnabledReceiverProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 646);
        this.centralSurfacesGoogleProvider = new DelegateFactory();
        this.capabilitiesCallbackHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 648);
        this.goBackHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 649);
        this.swipeHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 650);
        this.ngaMessageHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 647);
        this.defaultUiControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 651);
        this.googleDefaultUiControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 652);
        this.assistRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 654);
        this.assistInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 653);
        DelegateFactory.setDelegate(this.assistManagerGoogleProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 629, 1)));
        this.statusBarRemoteInputCallbackProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 655);
        this.shadeSurfaceImplProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 658, 1);
        this.notificationPanelViewControllerProvider = new DelegateFactory();
        this.provideShadeSurfaceProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 657);
        this.panelExpansionInteractorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 660);
        this.providePanelExpansionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 659);
        this.quickSettingsControllerSceneImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 662);
        this.provideDisplayMetricsRepoLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 666);
        this.displayMetricsRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 665);
        this.qsFrameTranslateImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 664);
        this.notificationStackScrollLayoutControllerProvider = new DelegateFactory();
        this.screenCaptureDevicePolicyResolverProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 668, 1);
        this.provideRecordingControllerLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 670);
        this.recordingControllerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 669);
        this.mediaProjectionMetricsLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 671);
        provider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 672, 1));
        this.factoryProvider29 = provider2;
        provider3 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 673, 1));
        this.factoryProvider30 = provider3;
        this.recordingControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 667);
        this.provideCastControllerLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 676);
        this.castControllerLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 675);
        this.castControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 674);
        this.quickSettingsControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 663);
        this.provideQuickSettingsControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 661);
        this.notificationAlertsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 677);
        this.shadeEventCoordinatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 678);
        this.initControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 679);
        this.provideListContainerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 680);
        this.statusBarNotificationPresenterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 656);
        this.provideActivityTransitionAnimatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 681);
        this.notificationLaunchAnimationRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 684);
        this.notificationLaunchAnimationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 683);
        this.provideNotificationTransitionAnimatorControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 682);
        this.launchFullScreenIntentProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 685);
        this.statusBarNotificationActivityStarterProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 628);
        this.providesWindowRootViewProvider = new DelegateFactory();
        this.provideNotificationRenderLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 686);
        this.notificationStackSizeCalculatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 687);
        this.notificationTargetsHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 688);
        DelegateFactory.setDelegate(this.notificationStackScrollLayoutControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 559, 1)));
        this.notificationsQSContainerControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 494);
        this.providesTapAgainViewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 690);
        this.tapAgainViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 689);
        this.shadeExpansionStateManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 691);
        provider4 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 694, 1));
        this.factoryProvider31 = provider4;
        this.provideDateSmartspaceDataPluginProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 695);
        this.provideWeatherSmartspaceDataPluginProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 696);
        this.provideBcSmartspaceDataPluginProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 697);
        this.provideBcSmartspaceConfigPluginProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 698);
        this.lockscreenSmartspaceControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 693);
        this.keyguardBottomAreaViewControllerProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 692, 1);
    }

    public final void initialize8() {
        Provider provider;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.keyguardQuickAffordanceLocalUserSelectionManagerProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 701, 1));
        this.keyguardQuickAffordanceRemoteUserSelectionManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 702);
        this.keyguardQuickAffordanceLegacySettingSyncerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 703);
        this.cameraGestureHelperProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 705);
        this.cameraQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 704);
        this.doNotDisturbQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 706);
        this.flashlightControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 708);
        this.flashlightQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 707);
        this.providesControlsFeatureEnabledProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 711);
        this.controlsControllerImplProvider = new DelegateFactory();
        this.controlsListingControllerImplProvider = new DelegateFactory();
        this.controlsMetricsLoggerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 715);
        this.controlsSettingsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 716);
        this.controlActionCoordinatorImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 714);
        this.customIconCacheProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 717);
        this.selectedComponentRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 718);
        this.controlsUiControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 713);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 720, 1));
        this.factoryProvider32 = provider;
        this.controlsBindingControllerImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 719);
        DelegateFactory.setDelegate(this.controlsControllerImplProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 712, 1)));
        this.controlsComponentProvider = new DelegateFactory();
        this.googleControlsTileResourceConfigurationImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 721);
        DelegateFactory.setDelegate(this.controlsComponentProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 710, 1)));
        this.homeControlsKeyguardQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 709);
        this.muteQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 722);
        this.provideQuickAccessWalletClientProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 725);
        this.quickAccessWalletControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 724);
        this.quickAccessWalletKeyguardQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 723);
        this.qRCodeScannerControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 727);
        this.qrCodeScannerKeyguardQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 726);
        this.videoCameraQuickAffordanceConfigProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 728);
        this.noteTaskBubblesControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 730);
        this.noteTaskControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 729);
        this.stylusManagerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 731);
        DelegateFactory delegateFactory = new DelegateFactory();
        this.keyguardQuickAffordanceRepositoryProvider = delegateFactory;
        DelegateFactory.setDelegate(delegateFactory, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 700, 1)));
        this.provideKeyguardQuickAffordancesLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 732);
        this.keyguardQuickAffordanceInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 699);
        this.dozeInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 736);
        this.pulsingGestureListenerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 735);
        this.keyguardTouchHandlingInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 734);
        this.keyguardTouchHandlingViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 733);
        this.provideKeyguardClockLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 740);
        this.provideKeyguardSmallClockLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 741);
        this.provideKeyguardLargeClockLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 742);
        this.getClockRegistryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 739);
        this.keyguardClockRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 738);
        this.keyguardClockInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 737);
        this.dreamingToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 743);
        this.occludedToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 744);
        this.lockscreenToDreamingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 745);
        this.goneToDreamingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 746);
        this.goneToDreamingLockscreenHostedTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 747);
        this.lockscreenToOccludedTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 748);
        this.providesKeyguardRootViewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 750);
        this.notificationShadeWindowModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 752);
        this.notificationIconContainerAlwaysOnDisplayViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 753);
        this.alternateBouncerToAodTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 754);
        this.alternateBouncerToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 755);
        this.alternateBouncerToOccludedTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 756);
        this.aodToGoneTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 757);
        this.aodToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 758);
        this.aodToOccludedTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 759);
        this.dozingToGoneTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 760);
        this.dozingToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 761);
        this.dozingToOccludedTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 762);
        this.dreamingToAodTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 763);
        this.dreamingToGoneTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 764);
        this.goneToAodTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 765);
        this.goneToDozingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 766);
        this.goneToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 767);
        this.lockscreenToAodTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 768);
        this.lockscreenToDozingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 769);
        this.lockscreenToGoneTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 770);
        this.lockscreenToPrimaryBouncerTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 771);
        this.occludedToAlternateBouncerTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 772);
        this.occludedToAodTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 773);
        this.occludedToDozingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 774);
        this.primaryBouncerToAodTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 775);
        this.primaryBouncerToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 776);
        this.burnInInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 778);
        this.keyguardClockViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 779);
        this.aodBurnInViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 777);
        this.aodAlphaViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 780);
        this.keyguardRootViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 751);
        this.dreamingHostedToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 782);
        this.offToLockscreenTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 783);
        this.lockscreenToDreamingHostedTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 784);
        this.provideKeyguardQuickAffordancesCombinedViewModelProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 781, 1));
        this.occludingAppDeviceEntryInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 786);
        this.occludingAppDeviceEntryMessageViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 785);
        this.provideChipbarLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 789);
        this.chipbarLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 788);
        this.chipbarAnimatorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 790);
        this.provideSwipeUpLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 793);
        this.swipeUpGestureLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 792);
        this.swipeChipbarAwayGestureHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 791);
        this.viewUtilProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 794);
        this.temporaryViewUiEventLoggerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 795);
    }

    public final void initialize9() {
        Provider provider;
        Provider provider2;
        Provider provider3;
        Provider provider4;
        Provider provider5;
        Provider provider6;
        Provider provider7;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl = this.sysUIGoogleSysUIComponentImpl;
        this.chipbarCoordinatorProvider = DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 787, 1));
        this.alternateBouncerToDozingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 800);
        this.alternateBouncerToPrimaryBouncerTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 801);
        this.aodToPrimaryBouncerTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 802);
        this.dozingToPrimaryBouncerTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 803);
        this.primaryBouncerToDozingTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 804);
        this.glanceableHubToOccludedTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 805);
        this.occludedToGlanceableHubTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 806);
        this.goneToGlanceableHubTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 807);
        this.primaryBouncerToGlanceableHubTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 808);
        this.dozingToGlanceableHubTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 809);
        this.deviceEntrySourceInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 810);
        this.deviceEntryIconViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 799);
        this.deviceEntryForegroundViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 811);
        this.deviceEntryBackgroundViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 812);
        this.providesLongPressTouchLogProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 813);
        this.keyguardBlueprintInteractorProvider = new DelegateFactory();
        this.keyguardQuickAffordanceViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 814);
        this.ambientIndicationRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 816);
        this.ambientIndicationInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 815);
        this.keyguardViewConfiguratorProvider = new DelegateFactory();
        this.providesSharedNotificationContainerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 817);
        this.notificationViewHeightRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 820);
        this.notificationPlaceholderRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 821);
        this.notificationStackAppearanceInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 819);
        this.occludedToGoneTransitionViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 822);
        this.sharedNotificationContainerViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 818);
        provider = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 825, 1));
        this.factoryProvider33 = provider;
        this.notificationScrollViewBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 824);
        this.sharedNotificationContainerBinderProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 823);
        this.keyguardSmartspaceRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 829);
        this.keyguardSmartspaceInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 828);
        this.keyguardSmartspaceViewModelProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 827);
        this.clockSectionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 826);
        this.smartspaceSectionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 830);
        this.defaultKeyguardBlueprintProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 798);
        this.splitShadeKeyguardBlueprintProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 831);
        this.providesThreadAssertProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 832);
        this.keyguardBlueprintRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 797);
        DelegateFactory.setDelegate(this.keyguardBlueprintInteractorProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 796, 1)));
        this.legacyLockIconViewControllerProvider = new DelegateFactory();
        this.deviceEntryBiometricAuthInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 834);
        this.keyEventRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 836);
        this.keyEventInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 835);
        this.deviceEntryHapticsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 833);
        this.keyguardOcclusionRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 840);
        this.internalKeyguardTransitionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 841);
        this.keyguardOcclusionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 839);
        this.sceneContainerOcclusionInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 838);
        provider2 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 837, 1));
        this.factoryProvider34 = provider2;
        provider3 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 845, 1));
        this.factoryProvider35 = provider3;
        this.notificationSectionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 844);
        this.warpLockscreenRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 846);
        this.strideLockscreenRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 848);
        this.strideAreaSectionProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 847);
        int i = 1;
        this.catwalkKeyguardBlueprintProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 843, i);
        this.defaultBlueprintProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 849, i);
        this.providesLockscreenBlueprintsProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 842, i);
        this.deviceEntryUnlockTrackerRepositoryGoogleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 852);
        this.deviceEntryUnlockTrackerInteractorGoogleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 851);
        this.deviceEntryUnlockTrackerViewBinderGoogleProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 850);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 749, this.keyguardViewConfiguratorProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 364, this.notificationPanelViewControllerProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 362, this.provideShadeLockscreenInteractorProvider);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 361, this.unlockedScreenOffAnimationControllerProvider);
        int i2 = 1;
        DelegateFactory.setDelegate(this.screenOffAnimationControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 358, i2)));
        this.biometricUnlockInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 853);
        DelegateFactory.setDelegate(this.biometricUnlockControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 353, i2)));
        this.provideScrimLogBufferProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 855);
        this.providesLightRevealScrimProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 854);
        this.authRippleInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 856);
        this.providesAuthRippleViewProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 857);
        this.authRippleControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 352);
        DelegateFactory.setDelegate(this.legacyLockIconViewControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 350, i2)));
        this.emptyLockIconViewControllerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 858);
        DelegateFactory.setDelegate(this.provideLockIconViewControllerProvider, DoubleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 349, i2)));
        this.uiBgDispatcherProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 860);
        this.uiBgCoroutineContextProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 859);
        this.provideTaskStackChangeListenersProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 863);
        this.usageStatsRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 865);
        this.usageStatsInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 864);
        this.widgetTrampolineInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 862);
        this.widgetInteractionHandlerProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 861);
        provider4 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 866, 1));
        this.factoryProvider36 = provider4;
        this.communalSceneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 243);
        provider5 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 868, 1));
        this.factoryProvider37 = provider5;
        this.goneSceneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 867);
        provider6 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 870, 1));
        this.factoryProvider38 = provider6;
        this.providesLockscreenContentProvider = new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 871, 1);
        this.lockscreenSceneProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 869);
        this.provideShadeSessionStorageProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 873);
        provider7 = SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 874, 1));
        this.factoryProvider39 = provider7;
        this.brightnessMirrorShowingRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 878);
        this.brightnessMirrorShowingInteractorProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 877);
        SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 876, 1));
        this.privacyChipRepositoryImplProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 881);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 880);
        this.shadeHeaderClockRepositoryProvider = DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 883);
        DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl$$ExternalSyntheticOutline0.m(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 882);
        SingleCheck.provider(new DaggerSysUIGoogleGlobalRootComponent$WMComponentImpl.SwitchingProvider(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl, daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl, 879, 1));
    }

    @Override // com.google.android.systemui.dagger.SysUIGoogleSysUIComponent, com.android.systemui.dagger.SysUIComponent
    public void inject(SystemUIAppComponentFactoryBase systemUIAppComponentFactoryBase) {
        systemUIAppComponentFactoryBase.componentHelper = (ContextComponentResolver) this.contextComponentResolverProvider.get();
    }

    public final InputDeviceTutorialLogger inputDeviceTutorialLogger() {
        return new InputDeviceTutorialLogger((LogBuffer) this.provideInputDeviceTutorialLogBufferProvider.get());
    }

    public final InternetTileDataInteractor internetTileDataInteractor() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new InternetTileDataInteractor(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.mainCoroutineContextProvider.get(), (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (AirplaneModeRepository) this.airplaneModeRepositoryImplProvider.get(), (ConnectivityRepositoryImpl) this.connectivityRepositoryImplProvider.get(), (EthernetInteractor) this.ethernetInteractorProvider.get(), (MobileIconsInteractorImpl) this.mobileIconsInteractorImplProvider.get(), (WifiInteractorImpl) this.wifiInteractorImplProvider.get());
    }

    public final KeyguardAmbientIndicationViewModel keyguardAmbientIndicationViewModel() {
        return new KeyguardAmbientIndicationViewModel((AmbientIndicationInteractor) this.ambientIndicationInteractorProvider.get(), (BurnInInteractor) this.burnInInteractorProvider.get(), (KeyguardTransitionInteractor) this.keyguardTransitionInteractorProvider.get(), (CoroutineContext) this.bgCoroutineContextProvider.get(), (CoroutineDispatcher) this.sysUIGoogleGlobalRootComponentImpl.mainDispatcherProvider.get());
    }

    public final KeyguardBottomAreaViewModel keyguardBottomAreaViewModel() {
        return new KeyguardBottomAreaViewModel((KeyguardInteractor) this.keyguardInteractorProvider.get(), (KeyguardQuickAffordanceInteractor) this.keyguardQuickAffordanceInteractorProvider.get(), (KeyguardBottomAreaInteractor) this.keyguardBottomAreaInteractorProvider.get(), new BurnInHelperWrapper(), (KeyguardTouchHandlingViewModel) this.keyguardTouchHandlingViewModelProvider.get(), new KeyguardSettingsMenuViewModel((KeyguardTouchHandlingInteractor) this.keyguardTouchHandlingInteractorProvider.get()));
    }

    public final KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel() {
        KeyguardInteractor keyguardInteractor = (KeyguardInteractor) this.keyguardInteractorProvider.get();
        return new KeyguardIndicationAreaViewModel(keyguardInteractor, keyguardBottomAreaViewModel(), new BurnInHelperWrapper(), (BurnInInteractor) this.burnInInteractorProvider.get(), (KeyguardQuickAffordancesCombinedViewModel) this.provideKeyguardQuickAffordancesCombinedViewModelProvider.get(), (ConfigurationInteractor) this.configurationInteractorProvider.get(), (KeyguardTransitionInteractor) this.keyguardTransitionInteractorProvider.get(), (CommunalSceneInteractor) this.communalSceneInteractorProvider.get(), (CoroutineDispatcher) this.bgDispatcherProvider.get(), (CoroutineDispatcher) this.sysUIGoogleGlobalRootComponentImpl.mainDispatcherProvider.get());
    }

    public final KeyguardQuickAffordancesCombinedViewModel keyguardQuickAffordancesCombinedViewModel() {
        return new KeyguardQuickAffordancesCombinedViewModel((CoroutineScope) this.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (KeyguardQuickAffordanceInteractor) this.keyguardQuickAffordanceInteractorProvider.get(), (KeyguardInteractor) this.keyguardInteractorProvider.get(), (ShadeInteractor) this.shadeInteractorImplProvider.get(), (AodToLockscreenTransitionViewModel) this.aodToLockscreenTransitionViewModelProvider.get(), (DozingToLockscreenTransitionViewModel) this.dozingToLockscreenTransitionViewModelProvider.get(), (DreamingHostedToLockscreenTransitionViewModel) this.dreamingHostedToLockscreenTransitionViewModelProvider.get(), (DreamingToLockscreenTransitionViewModel) this.dreamingToLockscreenTransitionViewModelProvider.get(), (GoneToLockscreenTransitionViewModel) this.goneToLockscreenTransitionViewModelProvider.get(), (OccludedToLockscreenTransitionViewModel) this.occludedToLockscreenTransitionViewModelProvider.get(), (OffToLockscreenTransitionViewModel) this.offToLockscreenTransitionViewModelProvider.get(), (PrimaryBouncerToLockscreenTransitionViewModel) this.primaryBouncerToLockscreenTransitionViewModelProvider.get(), (GlanceableHubToLockscreenTransitionViewModel) this.glanceableHubToLockscreenTransitionViewModelProvider.get(), (LockscreenToAodTransitionViewModel) this.lockscreenToAodTransitionViewModelProvider.get(), (LockscreenToDozingTransitionViewModel) this.lockscreenToDozingTransitionViewModelProvider.get(), (LockscreenToDreamingHostedTransitionViewModel) this.lockscreenToDreamingHostedTransitionViewModelProvider.get(), (LockscreenToDreamingTransitionViewModel) this.lockscreenToDreamingTransitionViewModelProvider.get(), (LockscreenToGoneTransitionViewModel) this.lockscreenToGoneTransitionViewModelProvider.get(), (LockscreenToOccludedTransitionViewModel) this.lockscreenToOccludedTransitionViewModelProvider.get(), (LockscreenToPrimaryBouncerTransitionViewModel) this.lockscreenToPrimaryBouncerTransitionViewModelProvider.get(), (LockscreenToGlanceableHubTransitionViewModel) this.lockscreenToGlanceableHubTransitionViewModelProvider.get(), (KeyguardTransitionInteractor) this.keyguardTransitionInteractorProvider.get());
    }

    public final KeyguardVisibility keyguardVisibility() {
        return new KeyguardVisibility((Executor) this.sysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (KeyguardStateController) this.keyguardStateControllerImplProvider.get());
    }

    public final LockSection lockSection() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new LockSection((CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), (WindowManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideWindowManagerProvider.get(), (AuthController) this.authControllerProvider.get(), (FeatureFlagsClassic) this.featureFlagsClassicReleaseProvider.get(), DoubleCheck.lazy(this.provideLockIconViewControllerProvider), DoubleCheck.lazy(this.deviceEntryIconViewModelProvider), DoubleCheck.lazy(this.deviceEntryForegroundViewModelProvider), DoubleCheck.lazy(this.deviceEntryBackgroundViewModelProvider), DoubleCheck.lazy(this.falsingManagerProxyProvider), DoubleCheck.lazy(this.vibratorHelperProvider), (LogBuffer) this.providesLongPressTouchLogProvider.get());
    }

    public final MediaOutputDialogManager mediaOutputDialogManager() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new MediaOutputDialogManager(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (BroadcastSender) this.broadcastSenderProvider.get(), (UiEventLogger) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiEventLoggerProvider.get(), (DialogTransitionAnimator) this.provideDialogTransitionAnimatorProvider.get(), (DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$12) this.factoryProvider15.get());
    }

    public final Set namedSetOfAction() {
        ArrayList arrayList = new ArrayList(3);
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        arrayList.add(new DismissTimer(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get()));
        arrayList.add(new SnoozeAlarm(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get()));
        arrayList.add(new SilenceCall(daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context, (Executor) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideMainExecutorProvider.get(), (TelephonyListenerManager) this.telephonyListenerManagerProvider.get()));
        return arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList));
    }

    public final NightDisplayListenerModule$Builder nightDisplayListenerModuleBuilder() {
        return new NightDisplayListenerModule$Builder(this.sysUIGoogleGlobalRootComponentImpl.context, (Handler) this.provideBgHandlerProvider.get());
    }

    public final NightDisplayRepository nightDisplayRepository() {
        CoroutineContext coroutineContext = (CoroutineContext) this.bgCoroutineContextProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new NightDisplayRepository(coroutineContext, (CoroutineScope) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get(), globalSettingsImpl(), (SecureSettings) this.secureSettingsImplProvider.get(), nightDisplayListenerModuleBuilder(), (UserScopedServiceImpl) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideScopedColorDisplayManagerProvider.get(), (LocationController) this.locationControllerImplProvider.get());
    }

    public final boolean noteTaskEnabledKeyBoolean() {
        return ((RoleManager) this.sysUIGoogleGlobalRootComponentImpl.provideRoleManagerProvider.get()).isRoleAvailable("android.app.role.NOTES") && ((FeatureFlagsClassicRelease) ((FeatureFlags) this.featureFlagsClassicReleaseProvider.get())).isEnabled(Flags.NOTE_TASKS);
    }

    public final NotifPipelineFlags notifPipelineFlags() {
        return new NotifPipelineFlags((FeatureFlags) this.featureFlagsClassicReleaseProvider.get());
    }

    public final NotificationIconsInteractor notificationIconsInteractor() {
        return new NotificationIconsInteractor((ActiveNotificationsInteractor) this.activeNotificationsInteractorProvider.get(), this.setBubbles, new HeadsUpNotificationIconInteractor((HeadsUpNotificationIconViewStateRepository) this.headsUpNotificationIconViewStateRepositoryProvider.get()), (NotificationsKeyguardViewStateRepository) this.notificationsKeyguardViewStateRepositoryProvider.get());
    }

    public final NotificationListViewModel notificationListViewModel() {
        NotificationShelfViewModel notificationShelfViewModel = (NotificationShelfViewModel) this.notificationShelfViewModelProvider.get();
        HideListViewModel hideListViewModel = (HideListViewModel) this.hideListViewModelProvider.get();
        Optional optional = (Optional) this.provideOptionalProvider.get();
        Optional empty = Optional.empty();
        Intrinsics.checkNotNull(empty);
        return new NotificationListViewModel(notificationShelfViewModel, hideListViewModel, optional, empty, (ActiveNotificationsInteractor) this.activeNotificationsInteractorProvider.get(), (NotificationStackInteractor) this.notificationStackInteractorProvider.get(), headsUpNotificationInteractor(), (RemoteInputInteractor) this.remoteInputInteractorProvider.get(), (SeenNotificationsInteractor) this.seenNotificationsInteractorProvider.get(), (ShadeInteractor) this.shadeInteractorImplProvider.get(), new UserSetupInteractor((UserSetupRepositoryImpl) this.userSetupRepositoryImplProvider.get()), zenModeInteractor(), (CoroutineDispatcher) this.bgDispatcherProvider.get(), (DumpManager) this.sysUIGoogleGlobalRootComponentImpl.dumpManagerProvider.get());
    }

    public final Context overlayWindowContextContext() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        DisplayManager displayManager = (DisplayManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideDisplayManagerProvider.get();
        DisplayTracker displayTracker = (DisplayTracker) this.provideDisplayTrackerProvider.get();
        Context context = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context;
        displayTracker.getClass();
        Context createWindowContext = context.createWindowContext(displayManager.getDisplay(0), 2036, null);
        Preconditions.checkNotNullFromProvides(createWindowContext);
        return createWindowContext;
    }

    public final PaginatedGridLayout paginatedGridLayout() {
        return new PaginatedGridLayout((PaginatedGridViewModel) this.paginatedGridViewModelProvider.get(), (InfiniteGridLayout) this.infiniteGridLayoutProvider.get());
    }

    public final QRCodeScannerTileDataInteractor qRCodeScannerTileDataInteractor() {
        CoroutineContext coroutineContext = (CoroutineContext) this.bgCoroutineContextProvider.get();
        return new QRCodeScannerTileDataInteractor(coroutineContext, (QRCodeScannerController) this.qRCodeScannerControllerProvider.get());
    }

    public final QSLogger qSLogger() {
        return new QSLogger((LogBuffer) this.provideQuickSettingsLogBufferProvider.get(), (LogBuffer) this.provideQSConfigLogBufferProvider.get());
    }

    public final QSTileCoroutineScopeFactory qSTileCoroutineScopeFactory() {
        return new QSTileCoroutineScopeFactory((CoroutineScope) this.sysUIGoogleGlobalRootComponentImpl.applicationScopeProvider.get());
    }

    public final ReduceBrightColorsTileDataInteractor reduceBrightColorsTileDataInteractor() {
        return new ReduceBrightColorsTileDataInteractor((CoroutineContext) this.bgCoroutineContextProvider.get(), ((Boolean) this.isReduceBrightColorsAvailableProvider.get()).booleanValue(), (ReduceBrightColorsController) this.reduceBrightColorsControllerImplProvider.get());
    }

    public final RotationLockTileDataInteractor rotationLockTileDataInteractor() {
        RotationLockController rotationLockController = (RotationLockController) this.rotationLockControllerImplProvider.get();
        BatteryController batteryController = (BatteryController) this.provideBatteryControllerProvider.get();
        CameraAutoRotateRepositoryImpl cameraAutoRotateRepositoryImpl = (CameraAutoRotateRepositoryImpl) this.cameraAutoRotateRepositoryImplProvider.get();
        CameraSensorPrivacyRepositoryImpl cameraSensorPrivacyRepositoryImpl = (CameraSensorPrivacyRepositoryImpl) this.cameraSensorPrivacyRepositoryImplProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        PackageManager packageManager = (PackageManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.providePackageManagerProvider.get();
        Resources resources = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.context.getResources();
        Preconditions.checkNotNullFromProvides(resources);
        return new RotationLockTileDataInteractor(rotationLockController, batteryController, cameraAutoRotateRepositoryImpl, cameraSensorPrivacyRepositoryImpl, packageManager, resources);
    }

    public final Set setOfPairOfGridLayoutTypeAndGridLayout() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new Pair(InfiniteGridLayoutType.INSTANCE, (InfiniteGridLayout) this.infiniteGridLayoutProvider.get()));
        arrayList.add(new Pair(PaginatedGridLayoutType.INSTANCE, paginatedGridLayout()));
        return arrayList.isEmpty() ? Collections.emptySet() : arrayList.size() == 1 ? Collections.singleton(arrayList.get(0)) : Collections.unmodifiableSet(new HashSet(arrayList));
    }

    public final SubscriptionManagerProxyImpl subscriptionManagerProxyImpl() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new SubscriptionManagerProxyImpl((CoroutineDispatcher) this.bgDispatcherProvider.get(), (SubscriptionManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideSubscriptionManagerProvider.get());
    }

    public final SystemBarUtilsState systemBarUtilsState() {
        CoroutineContext coroutineContext = (CoroutineContext) this.bgCoroutineContextProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new SystemBarUtilsState(coroutineContext, (CoroutineContext) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.mainCoroutineContextProvider.get(), (ConfigurationController) this.provideGlobalConfigurationControllerProvider.get(), new SystemBarUtilsProxyImpl((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get()));
    }

    public final SystemUIDialogFactory systemUIDialogFactory() {
        return new SystemUIDialogFactory((Context) this.sysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (SystemUIDialogManager) this.systemUIDialogManagerProvider.get(), (SysUiState) this.provideSysUiStateProvider.get(), (BroadcastDispatcher) this.broadcastDispatcherProvider.get(), (DialogTransitionAnimator) this.provideDialogTransitionAnimatorProvider.get());
    }

    public final Resources.Theme theme() {
        return this.sysUIGoogleGlobalRootComponentImpl.context.getTheme();
    }

    public final UiModeNightTileDataInteractor uiModeNightTileDataInteractor() {
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        return new UiModeNightTileDataInteractor((Context) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideApplicationContextProvider.get(), (ConfigurationController) this.provideGlobalConfigurationControllerProvider.get(), (UiModeManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideUiModeManagerProvider.get(), (BatteryController) this.provideBatteryControllerProvider.get(), (LocationController) this.locationControllerImplProvider.get(), dateFormatUtil());
    }

    public final VolumePanelLogger volumePanelLogger() {
        return new VolumePanelLogger((LogBuffer) this.provideVolumeLogBufferProvider.get());
    }

    public final WakeLockLogger wakeLockLogger() {
        return new WakeLockLogger((LogBuffer) this.provideWakeLockLogProvider.get());
    }

    public final ZenModeInteractor zenModeInteractor() {
        return new ZenModeInteractor(this.sysUIGoogleGlobalRootComponentImpl.context, (ZenModeRepositoryImpl) this.provideZenModeRepositoryProvider.get(), (NotificationSettingsRepository) this.provideNotificationSettingsRepositoryProvider.get(), (CoroutineDispatcher) this.bgDispatcherProvider.get(), (ZenIconLoader) this.provideZenIconLoaderProvider.get(), new DeviceProvisioningRepositoryImpl((DeviceProvisionedController) this.provideDeviceProvisionedControllerProvider.get()), (UserSetupRepositoryImpl) this.userSetupRepositoryImplProvider.get());
    }

    @Override // com.google.android.systemui.dagger.SysUIGoogleSysUIComponent, com.android.systemui.dagger.SysUIComponent
    public void inject(KeyguardSliceProvider keyguardSliceProvider) {
        keyguardSliceProvider.mDozeParameters = (DozeParameters) this.dozeParametersProvider.get();
        keyguardSliceProvider.mZenModeController = (ZenModeController) this.zenModeControllerImplProvider.get();
        keyguardSliceProvider.mNextAlarmController = (NextAlarmController) this.nextAlarmControllerImplProvider.get();
        DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl = this.sysUIGoogleGlobalRootComponentImpl;
        keyguardSliceProvider.mAlarmManager = (AlarmManager) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideAlarmManagerProvider.get();
        keyguardSliceProvider.mContentResolver = (ContentResolver) daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl.provideContentResolverProvider.get();
        keyguardSliceProvider.mMediaManager = (NotificationMediaManager) this.provideNotificationMediaManagerProvider.get();
        keyguardSliceProvider.mStatusBarStateController = (StatusBarStateController) this.statusBarStateControllerImplProvider.get();
        keyguardSliceProvider.mKeyguardBypassController = (KeyguardBypassController) this.keyguardBypassControllerProvider.get();
        keyguardSliceProvider.mKeyguardUpdateMonitor = (KeyguardUpdateMonitor) this.keyguardUpdateMonitorProvider.get();
        keyguardSliceProvider.mUserTracker = (UserTracker) this.provideUserTrackerProvider.get();
        keyguardSliceProvider.mWakeLockLogger = wakeLockLogger();
        keyguardSliceProvider.mBgHandler = (Handler) this.provideBgHandlerProvider.get();
    }

    @Override // com.google.android.systemui.dagger.SysUIGoogleSysUIComponent, com.android.systemui.dagger.SysUIComponent
    public void inject(PeopleProvider peopleProvider) {
        peopleProvider.mPeopleSpaceWidgetManager = (PeopleSpaceWidgetManager) this.peopleSpaceWidgetManagerProvider.get();
    }

    @Override // com.google.android.systemui.dagger.SysUIGoogleSysUIComponent
    public void inject(CustomizationProvider customizationProvider) {
        customizationProvider.interactor = (KeyguardQuickAffordanceInteractor) this.keyguardQuickAffordanceInteractorProvider.get();
        customizationProvider.previewManager = (KeyguardRemotePreviewManager) this.keyguardRemotePreviewManagerProvider.get();
        customizationProvider.mainDispatcher = (CoroutineDispatcher) this.sysUIGoogleGlobalRootComponentImpl.mainDispatcherProvider.get();
    }
}

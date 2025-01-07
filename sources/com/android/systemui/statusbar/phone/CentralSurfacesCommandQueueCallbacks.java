package com.android.systemui.statusbar.phone;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.os.SystemClock;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import com.android.internal.logging.MetricsLogger;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.assist.AssistDisclosure;
import com.android.systemui.doze.DozeHost$Callback;
import com.android.systemui.emergency.EmergencyGestureModule$emergencyGestureIntentFactory$1;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.shared.model.CameraLaunchSourceModel;
import com.android.systemui.keyguard.shared.model.CameraLaunchType;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.qs.QSHost;
import com.android.systemui.qs.QSHostAdapter;
import com.android.systemui.qs.QSPanelController;
import com.android.systemui.qs.QSPanelControllerBase;
import com.android.systemui.qs.external.CustomTile;
import com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.recents.ScreenPinningRequest;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.BaseShadeControllerImpl;
import com.android.systemui.shade.CameraLauncher;
import com.android.systemui.shade.QuickSettingsController;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.ShadeHeaderController;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.CentralSurfaces;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.RemoteInputQuickSettingsDisabler;
import com.android.systemui.util.Assert;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.MessageRouterImpl;
import com.android.wm.shell.R;
import com.google.android.systemui.assist.AssistManagerGoogle;
import com.google.android.systemui.statusbar.phone.CentralSurfacesGoogle;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import kotlin.jvm.internal.ArrayIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CentralSurfacesCommandQueueCallbacks implements CommandQueue.Callbacks {
    public static final VibrationAttributes HARDWARE_FEEDBACK_VIBRATION_ATTRIBUTES = VibrationAttributes.createForUsage(50);
    public final ActivityStarter mActivityStarter;
    public final AssistManagerGoogle mAssistManager;
    public final VibrationEffect mCameraLaunchGestureVibrationEffect;
    public final Lazy mCameraLauncherLazy;
    public final CentralSurfaces mCentralSurfaces;
    public final CommandQueue mCommandQueue;
    public final Context mContext;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public int mDisabled1;
    public int mDisabled2;
    public final int mDisplayId;
    public final DozeServiceHost mDozeServiceHost;
    public final EmergencyGestureModule$emergencyGestureIntentFactory$1 mEmergencyGestureIntentFactory;
    public final HeadsUpManager mHeadsUpManager;
    public final KeyguardInteractor mKeyguardInteractor;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final MetricsLogger mMetricsLogger;
    public final NotificationStackScrollLayoutController mNotificationStackScrollLayoutController;
    public final PanelExpansionInteractor mPanelExpansionInteractor;
    public final PowerManager mPowerManager;
    public final QSHost mQSHost;
    public final QuickSettingsController mQsController;
    public final RemoteInputQuickSettingsDisabler mRemoteInputQuickSettingsDisabler;
    public final ScreenPinningRequest mScreenPinningRequest;
    public final ShadeController mShadeController;
    public final ShadeHeaderController mShadeHeaderController;
    public final Lazy mShadeInteractorLazy;
    public final StatusBarHideIconsForBouncerManager mStatusBarHideIconsForBouncerManager;
    public final StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public final UserTracker mUserTracker;
    public final boolean mVibrateOnOpening;
    public final Optional mVibratorOptional;
    public final WakefulnessLifecycle mWakefulnessLifecycle;

    public CentralSurfacesCommandQueueCallbacks(CentralSurfaces centralSurfaces, QuickSettingsController quickSettingsController, Context context, Resources resources, ScreenPinningRequest screenPinningRequest, ShadeController shadeController, CommandQueue commandQueue, PanelExpansionInteractor panelExpansionInteractor, Lazy lazy, ShadeHeaderController shadeHeaderController, RemoteInputQuickSettingsDisabler remoteInputQuickSettingsDisabler, MetricsLogger metricsLogger, KeyguardUpdateMonitor keyguardUpdateMonitor, KeyguardStateController keyguardStateController, HeadsUpManager headsUpManager, WakefulnessLifecycle wakefulnessLifecycle, DeviceProvisionedController deviceProvisionedController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, AssistManagerGoogle assistManagerGoogle, DozeServiceHost dozeServiceHost, NotificationStackScrollLayoutController notificationStackScrollLayoutController, StatusBarHideIconsForBouncerManager statusBarHideIconsForBouncerManager, PowerManager powerManager, Optional optional, int i, Lazy lazy2, UserTracker userTracker, QSHost qSHost, ActivityStarter activityStarter, KeyguardInteractor keyguardInteractor, EmergencyGestureModule$emergencyGestureIntentFactory$1 emergencyGestureModule$emergencyGestureIntentFactory$1) {
        VibrationEffect createWaveform;
        this.mCentralSurfaces = centralSurfaces;
        this.mQsController = quickSettingsController;
        this.mContext = context;
        this.mScreenPinningRequest = screenPinningRequest;
        this.mShadeController = shadeController;
        this.mCommandQueue = commandQueue;
        this.mPanelExpansionInteractor = panelExpansionInteractor;
        this.mShadeInteractorLazy = lazy;
        this.mShadeHeaderController = shadeHeaderController;
        this.mRemoteInputQuickSettingsDisabler = remoteInputQuickSettingsDisabler;
        this.mMetricsLogger = metricsLogger;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mKeyguardStateController = keyguardStateController;
        this.mHeadsUpManager = headsUpManager;
        this.mWakefulnessLifecycle = wakefulnessLifecycle;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        this.mAssistManager = assistManagerGoogle;
        this.mDozeServiceHost = dozeServiceHost;
        this.mNotificationStackScrollLayoutController = notificationStackScrollLayoutController;
        this.mStatusBarHideIconsForBouncerManager = statusBarHideIconsForBouncerManager;
        this.mPowerManager = powerManager;
        this.mVibratorOptional = optional;
        this.mDisplayId = i;
        this.mCameraLauncherLazy = lazy2;
        this.mUserTracker = userTracker;
        this.mQSHost = qSHost;
        this.mKeyguardInteractor = keyguardInteractor;
        this.mVibrateOnOpening = resources.getBoolean(R.bool.config_vibrateOnIconAnimation);
        if (optional.isPresent() && ((Vibrator) optional.get()).areAllPrimitivesSupported(4, 1)) {
            createWaveform = VibrationEffect.startComposition().addPrimitive(4).addPrimitive(1, 1.0f, 50).compose();
        } else if (optional.isPresent() && ((Vibrator) optional.get()).hasAmplitudeControl()) {
            createWaveform = VibrationEffect.createWaveform(CentralSurfaces.CAMERA_LAUNCH_GESTURE_VIBRATION_TIMINGS, CentralSurfaces.CAMERA_LAUNCH_GESTURE_VIBRATION_AMPLITUDES, -1);
        } else {
            int[] intArray = resources.getIntArray(R.array.config_cameraLaunchGestureVibePattern);
            long[] jArr = new long[intArray.length];
            for (int i2 = 0; i2 < intArray.length; i2++) {
                jArr[i2] = intArray[i2];
            }
            createWaveform = VibrationEffect.createWaveform(jArr, -1);
        }
        this.mCameraLaunchGestureVibrationEffect = createWaveform;
        this.mActivityStarter = activityStarter;
        this.mEmergencyGestureIntentFactory = emergencyGestureModule$emergencyGestureIntentFactory$1;
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void addQsTileToFrontOrEnd(ComponentName componentName, boolean z) {
        ((QSHostAdapter) this.mQSHost).addTile(componentName, z);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void animateCollapsePanels(int i, boolean z) {
        this.mShadeController.animateCollapseShade(i, z, false, 1.0f);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void animateExpandNotificationsPanel() {
        ((BaseShadeControllerImpl) this.mShadeController).animateExpandShade();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void animateExpandSettingsPanel(String str) {
        ((BaseShadeControllerImpl) this.mShadeController).animateExpandQs();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void clickTile(ComponentName componentName) {
        QSPanelController qSPanelController = ((CentralSurfacesImpl) this.mCentralSurfaces).mQSPanelController;
        if (qSPanelController != null) {
            String spec = CustomTile.toSpec(componentName);
            Iterator it = qSPanelController.mRecords.iterator();
            while (it.hasNext()) {
                QSPanelControllerBase.TileRecord tileRecord = (QSPanelControllerBase.TileRecord) it.next();
                if (tileRecord.tile.getTileSpec().equals(spec)) {
                    tileRecord.tile.click(null);
                    return;
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void disable(int i, int i2, int i3, boolean z) {
        if (i != this.mDisplayId) {
            return;
        }
        int i4 = this.mDisabled1 ^ i2;
        this.mDisabled1 = i2;
        int adjustDisableFlags = this.mRemoteInputQuickSettingsDisabler.adjustDisableFlags(i3);
        int i5 = this.mDisabled2 ^ adjustDisableFlags;
        this.mDisabled2 = adjustDisableFlags;
        int i6 = i4 & 65536;
        ShadeController shadeController = this.mShadeController;
        if (i6 != 0 && (65536 & i2) != 0) {
            shadeController.animateCollapseShade(0);
        }
        if ((i4 & 262144) != 0 && (i2 & 262144) != 0) {
            ((BaseHeadsUpManager) this.mHeadsUpManager).releaseAllImmediately();
        }
        if ((i5 & 4) != 0 && (adjustDisableFlags & 4) != 0) {
            shadeController.animateCollapseShade(0);
        }
        ShadeHeaderController shadeHeaderController = this.mShadeHeaderController;
        shadeHeaderController.getClass();
        boolean z2 = (adjustDisableFlags & 1) != 0;
        if (z2 == shadeHeaderController.qsDisabled) {
            return;
        }
        shadeHeaderController.qsDisabled = z2;
        shadeHeaderController.updateVisibility$2();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void dismissKeyboardShortcutsMenu() {
        MessageRouterImpl messageRouterImpl = ((CentralSurfacesImpl) this.mCentralSurfaces).mMessageRouter;
        messageRouterImpl.cancelMessages(1027);
        messageRouterImpl.sendMessageDelayed(0L, 1027);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void handleSystemKey(KeyEvent keyEvent) {
        if (this.mCommandQueue.panelsEnabled() && this.mKeyguardUpdateMonitor.mDeviceInteractive) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.mKeyguardStateController;
            if ((!keyguardStateControllerImpl.mShowing || keyguardStateControllerImpl.mOccluded) && ((DeviceProvisionedControllerImpl) this.mDeviceProvisionedController).isCurrentUserSetup()) {
                int keyCode = keyEvent.getKeyCode();
                ShadeController shadeController = this.mShadeController;
                if (280 == keyCode) {
                    this.mMetricsLogger.action(493);
                    shadeController.animateCollapseShade(0);
                    return;
                }
                if (281 == keyEvent.getKeyCode()) {
                    this.mMetricsLogger.action(494);
                    if (!this.mPanelExpansionInteractor.isFullyCollapsed()) {
                        if (this.mQsController.getExpanded() || shadeController.isExpandingOrCollapsing()) {
                            return;
                        }
                        ((BaseShadeControllerImpl) shadeController).animateExpandQs();
                        this.mMetricsLogger.count("panel_open_qs", 1);
                        return;
                    }
                    if (this.mVibrateOnOpening) {
                        vibrateOnNavigationKeyDown();
                    }
                    ((BaseShadeControllerImpl) shadeController).animateExpandShade();
                    this.mNotificationStackScrollLayoutController.mView.mWillExpand = true;
                    ((HeadsUpManagerPhone) this.mHeadsUpManager).unpinAll();
                    this.mMetricsLogger.count("panel_open", 1);
                }
            }
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void onCameraLaunchGestureDetected(int i) {
        CameraLaunchType cameraLaunchType;
        CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) this.mCentralSurfaces;
        centralSurfacesImpl.mLastCameraLaunchSource = i;
        if (centralSurfacesImpl.isGoingToSleep()) {
            centralSurfacesImpl.mLaunchCameraOnFinishedGoingToSleep = true;
            return;
        }
        Lazy lazy = this.mCameraLauncherLazy;
        CameraLauncher cameraLauncher = (CameraLauncher) lazy.get();
        PanelExpansionInteractor panelExpansionInteractor = this.mPanelExpansionInteractor;
        if (cameraLauncher.mCameraGestureHelper.canCameraGestureBeLaunched(panelExpansionInteractor.getBarState())) {
            StateFlowImpl stateFlowImpl = this.mKeyguardInteractor.repository.onCameraLaunchDetected;
            if (i == 0) {
                cameraLaunchType = CameraLaunchType.WIGGLE;
            } else if (i == 1) {
                cameraLaunchType = CameraLaunchType.POWER_DOUBLE_TAP;
            } else if (i == 2) {
                cameraLaunchType = CameraLaunchType.LIFT_TRIGGER;
            } else {
                if (i != 3) {
                    throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Invalid CameraLaunchType value: "));
                }
                cameraLaunchType = CameraLaunchType.QUICK_AFFORDANCE;
            }
            CameraLaunchSourceModel cameraLaunchSourceModel = new CameraLaunchSourceModel(cameraLaunchType, 2);
            stateFlowImpl.getClass();
            String str = null;
            stateFlowImpl.updateState(null, cameraLaunchSourceModel);
            if (!centralSurfacesImpl.mDeviceInteractive) {
                this.mPowerManager.wakeUp(SystemClock.uptimeMillis(), 5, "com.android.systemui:CAMERA_GESTURE");
            }
            this.mVibratorOptional.ifPresent(new Consumer() { // from class: com.android.systemui.statusbar.phone.CentralSurfacesCommandQueueCallbacks$$ExternalSyntheticLambda0
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Vibrator) obj).vibrate(CentralSurfacesCommandQueueCallbacks.this.mCameraLaunchGestureVibrationEffect, CentralSurfacesCommandQueueCallbacks.HARDWARE_FEEDBACK_VIBRATION_ATTRIBUTES);
                }
            });
            if (i == 1) {
                KeyguardUpdateMonitor keyguardUpdateMonitor = this.mKeyguardUpdateMonitor;
                keyguardUpdateMonitor.mSecureCameraLaunched = true;
                keyguardUpdateMonitor.updateFingerprintListeningState(2);
            }
            if (((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing) {
                if (!centralSurfacesImpl.mDeviceInteractive) {
                    centralSurfacesImpl.mGestureWakeLock.acquire(6000L);
                }
                int i2 = this.mWakefulnessLifecycle.mWakefulness;
                if (i2 != 2 && i2 != 1) {
                    centralSurfacesImpl.mLaunchCameraWhenFinishedWaking = true;
                    return;
                }
                StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.mStatusBarKeyguardViewManager;
                if (statusBarKeyguardViewManager.isBouncerShowing()) {
                    statusBarKeyguardViewManager.reset(true, false);
                }
                centralSurfacesImpl.mMessageRouter.sendMessageDelayed(5000L, 1003);
                CameraLauncher cameraLauncher2 = (CameraLauncher) lazy.get();
                if (!panelExpansionInteractor.isFullyCollapsed()) {
                    cameraLauncher2.mKeyguardBypassController.launchingAffordance = true;
                }
                cameraLauncher2.mCameraGestureHelper.launchCamera(i);
                centralSurfacesImpl.updateScrimController();
                return;
            }
            Context context = this.mContext;
            UserTrackerImpl userTrackerImpl = (UserTrackerImpl) this.mUserTracker;
            int userId = userTrackerImpl.getUserId();
            Intent intent = new Intent("android.media.action.STILL_IMAGE_CAMERA");
            String string = context.getResources().getString(R.string.config_cameraGesturePackage);
            Intrinsics.checkNotNull(string);
            try {
                if (!TextUtils.isEmpty(string)) {
                    if (context.getPackageManager().getApplicationInfoAsUser(string, 0, userId).enabled) {
                        str = string;
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.w("CameraIntents", "Missing cameraGesturePackage ".concat(string), e);
            }
            if (str != null) {
                intent.setPackage(str);
            }
            intent.putExtra("com.android.systemui.camera_launch_source", i);
            this.mActivityStarter.startActivityDismissingKeyguard(intent, false, true, true, null, 0, null, userTrackerImpl.getUserHandle());
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void onEmergencyActionLaunchGestureDetected() {
        Intent invoke = this.mEmergencyGestureIntentFactory.invoke();
        if (invoke == null) {
            Log.wtf("CentralSurfaces", "Couldn't find an app to process the emergency intent.");
            return;
        }
        WakefulnessLifecycle wakefulnessLifecycle = this.mWakefulnessLifecycle;
        int i = wakefulnessLifecycle.mWakefulness;
        CentralSurfaces centralSurfaces = this.mCentralSurfaces;
        if (i == 3) {
            ((CentralSurfacesImpl) centralSurfaces).mLaunchEmergencyActionOnFinishedGoingToSleep = true;
            return;
        }
        CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) centralSurfaces;
        if (!centralSurfacesImpl.mDeviceInteractive) {
            this.mPowerManager.wakeUp(SystemClock.uptimeMillis(), 4, "com.android.systemui:EMERGENCY_GESTURE");
        }
        boolean z = ((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing;
        UserTracker userTracker = this.mUserTracker;
        if (!z) {
            this.mActivityStarter.startActivityDismissingKeyguard(invoke, false, true, true, null, 0, null, ((UserTrackerImpl) userTracker).getUserHandle());
            return;
        }
        if (!centralSurfacesImpl.mDeviceInteractive) {
            centralSurfacesImpl.mGestureWakeLock.acquire(6000L);
        }
        int i2 = wakefulnessLifecycle.mWakefulness;
        if (i2 != 2 && i2 != 1) {
            centralSurfacesImpl.mLaunchEmergencyActionWhenFinishedWaking = true;
            return;
        }
        StatusBarKeyguardViewManager statusBarKeyguardViewManager = this.mStatusBarKeyguardViewManager;
        if (statusBarKeyguardViewManager.isBouncerShowing()) {
            statusBarKeyguardViewManager.reset(true, false);
        }
        this.mContext.startActivityAsUser(invoke, ((UserTrackerImpl) userTracker).getUserHandle());
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void onRecentsAnimationStateChanged(boolean z) {
        ((CentralSurfacesImpl) this.mCentralSurfaces).setInteracting(2, z);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void remQsTile(ComponentName componentName) {
        QSHostAdapter qSHostAdapter = (QSHostAdapter) this.mQSHost;
        qSHostAdapter.getClass();
        ((CurrentTilesInteractorImpl) qSHostAdapter.interactor).removeTiles(Collections.singletonList(new TileSpec.CustomTileSpec(componentName, CustomTile.toSpec(componentName))));
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void setQsTiles(String[] strArr) {
        QSHostAdapter qSHostAdapter = (QSHostAdapter) this.mQSHost;
        qSHostAdapter.getSpecs();
        qSHostAdapter.changeTilesByUser(Arrays.stream(strArr).toList());
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void setTopAppHidesStatusBar(boolean z) {
        StatusBarHideIconsForBouncerManager statusBarHideIconsForBouncerManager = this.mStatusBarHideIconsForBouncerManager;
        statusBarHideIconsForBouncerManager.topAppHidesStatusBar = z;
        if (!z && statusBarHideIconsForBouncerManager.wereIconsJustHidden) {
            statusBarHideIconsForBouncerManager.wereIconsJustHidden = false;
            statusBarHideIconsForBouncerManager.commandQueue.recomputeDisableFlags(statusBarHideIconsForBouncerManager.displayId, true);
        }
        statusBarHideIconsForBouncerManager.updateHideIconsForBouncer(true);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void showAssistDisclosure() {
        AssistDisclosure assistDisclosure = this.mAssistManager.mAssistDisclosure;
        AssistDisclosure.AnonymousClass1 anonymousClass1 = assistDisclosure.mShowRunnable;
        Handler handler = assistDisclosure.mHandler;
        handler.removeCallbacks(anonymousClass1);
        handler.post(assistDisclosure.mShowRunnable);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void showScreenPinningRequest(int i) {
        if (((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing) {
            return;
        }
        this.mScreenPinningRequest.showPrompt(i, true);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void showWirelessChargingAnimation(int i) {
        CentralSurfacesGoogle centralSurfacesGoogle = (CentralSurfacesGoogle) this.mCentralSurfaces;
        if (CentralSurfacesGoogle.DEBUG) {
            centralSurfacesGoogle.getClass();
            Log.d("CentralSurfacesGoogle", "showWirelessChargingAnimation()");
        }
        centralSurfacesGoogle.mChargingAnimShown = true;
        centralSurfacesGoogle.showChargingAnimation(i, -1, 0L);
        centralSurfacesGoogle.mAnimStartTime = SystemClock.uptimeMillis();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void startAssist(Bundle bundle) {
        this.mAssistManager.startAssist(bundle);
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void suppressAmbientDisplay(boolean z) {
        DozeServiceHost dozeServiceHost = this.mDozeServiceHost;
        if (z == dozeServiceHost.mAlwaysOnSuppressed) {
            return;
        }
        dozeServiceHost.mAlwaysOnSuppressed = z;
        Assert.isMainThread();
        Iterator it = dozeServiceHost.mCallbacks.iterator();
        while (true) {
            ArrayIterator arrayIterator = (ArrayIterator) it;
            if (!arrayIterator.hasNext()) {
                return;
            } else {
                ((DozeHost$Callback) arrayIterator.next()).onAlwaysOnSuppressedChanged(z);
            }
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void toggleKeyboardShortcutsMenu(int i) {
        CentralSurfaces centralSurfaces = this.mCentralSurfaces;
        final CentralSurfaces.KeyboardShortcutsMessage keyboardShortcutsMessage = new CentralSurfaces.KeyboardShortcutsMessage(i);
        CentralSurfacesImpl centralSurfacesImpl = (CentralSurfacesImpl) centralSurfaces;
        MessageRouterImpl messageRouterImpl = centralSurfacesImpl.mMessageRouter;
        synchronized (messageRouterImpl.mDataMessageCancelers) {
            try {
                if (messageRouterImpl.mDataMessageCancelers.containsKey(CentralSurfaces.KeyboardShortcutsMessage.class)) {
                    Iterator it = ((List) messageRouterImpl.mDataMessageCancelers.get(CentralSurfaces.KeyboardShortcutsMessage.class)).iterator();
                    while (it.hasNext()) {
                        ((Runnable) it.next()).run();
                    }
                    messageRouterImpl.mDataMessageCancelers.remove(CentralSurfaces.KeyboardShortcutsMessage.class);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        final MessageRouterImpl messageRouterImpl2 = centralSurfacesImpl.mMessageRouter;
        messageRouterImpl2.getClass();
        ExecutorImpl.ExecutionToken executeDelayed = messageRouterImpl2.mDelayableExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.util.concurrency.MessageRouterImpl$$ExternalSyntheticLambda0
            /* JADX WARN: Removed duplicated region for block: B:9:0x0025 A[Catch: all -> 0x004f, TryCatch #1 {all -> 0x004f, blocks: (B:4:0x0007, B:6:0x0011, B:7:0x001f, B:9:0x0025, B:10:0x002d, B:21:0x0030, B:22:0x0035, B:11:0x0036, B:18:0x0043, B:14:0x0049, B:25:0x0051), top: B:3:0x0007 }] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() {
                /*
                    r6 = this;
                    com.android.systemui.util.concurrency.MessageRouterImpl r0 = com.android.systemui.util.concurrency.MessageRouterImpl.this
                    com.android.systemui.statusbar.phone.CentralSurfaces$KeyboardShortcutsMessage r6 = r2
                    java.util.Map r1 = r0.mDataMessageListenerMap
                    monitor-enter(r1)
                    java.util.Map r2 = r0.mDataMessageListenerMap     // Catch: java.lang.Throwable -> L4f
                    java.lang.Class<com.android.systemui.statusbar.phone.CentralSurfaces$KeyboardShortcutsMessage> r3 = com.android.systemui.statusbar.phone.CentralSurfaces.KeyboardShortcutsMessage.class
                    boolean r2 = r2.containsKey(r3)     // Catch: java.lang.Throwable -> L4f
                    if (r2 == 0) goto L51
                    java.util.Map r2 = r0.mDataMessageListenerMap     // Catch: java.lang.Throwable -> L4f
                    java.lang.Class<com.android.systemui.statusbar.phone.CentralSurfaces$KeyboardShortcutsMessage> r3 = com.android.systemui.statusbar.phone.CentralSurfaces.KeyboardShortcutsMessage.class
                    java.lang.Object r2 = r2.get(r3)     // Catch: java.lang.Throwable -> L4f
                    java.util.List r2 = (java.util.List) r2     // Catch: java.lang.Throwable -> L4f
                    java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L4f
                L1f:
                    boolean r3 = r2.hasNext()     // Catch: java.lang.Throwable -> L4f
                    if (r3 == 0) goto L51
                    java.lang.Object r3 = r2.next()     // Catch: java.lang.Throwable -> L4f
                    com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda7 r3 = (com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda7) r3     // Catch: java.lang.Throwable -> L4f
                    int r4 = r3.$r8$classId     // Catch: java.lang.Throwable -> L4f
                    switch(r4) {
                        case 0: goto L36;
                        default: goto L30;
                    }     // Catch: java.lang.Throwable -> L4f
                L30:
                    java.lang.ClassCastException r6 = new java.lang.ClassCastException     // Catch: java.lang.Throwable -> L4f
                    r6.<init>()     // Catch: java.lang.Throwable -> L4f
                    throw r6     // Catch: java.lang.Throwable -> L4f
                L36:
                    com.android.systemui.statusbar.phone.CentralSurfacesImpl r3 = r3.f$0     // Catch: java.lang.Throwable -> L4f
                    r3.getClass()     // Catch: java.lang.Throwable -> L4f
                    boolean r4 = r3.shouldUseTabletKeyboardShortcuts()     // Catch: java.lang.Throwable -> L4f
                    int r5 = r6.mDeviceId     // Catch: java.lang.Throwable -> L4f
                    if (r4 == 0) goto L49
                    android.content.Context r3 = r3.mContext     // Catch: java.lang.Throwable -> L4f
                    com.android.systemui.statusbar.KeyboardShortcutListSearch.toggle(r5, r3)     // Catch: java.lang.Throwable -> L4f
                    goto L1f
                L49:
                    android.content.Context r3 = r3.mContext     // Catch: java.lang.Throwable -> L4f
                    com.android.systemui.statusbar.KeyboardShortcuts.toggle(r5, r3)     // Catch: java.lang.Throwable -> L4f
                    goto L1f
                L4f:
                    r6 = move-exception
                    goto L9b
                L51:
                    monitor-exit(r1)     // Catch: java.lang.Throwable -> L4f
                    java.util.Map r6 = r0.mDataMessageCancelers
                    monitor-enter(r6)
                    java.util.Map r1 = r0.mDataMessageCancelers     // Catch: java.lang.Throwable -> L95
                    java.lang.Class<com.android.systemui.statusbar.phone.CentralSurfaces$KeyboardShortcutsMessage> r2 = com.android.systemui.statusbar.phone.CentralSurfaces.KeyboardShortcutsMessage.class
                    boolean r1 = r1.containsKey(r2)     // Catch: java.lang.Throwable -> L95
                    if (r1 == 0) goto L97
                    java.util.Map r1 = r0.mDataMessageCancelers     // Catch: java.lang.Throwable -> L95
                    java.lang.Class<com.android.systemui.statusbar.phone.CentralSurfaces$KeyboardShortcutsMessage> r2 = com.android.systemui.statusbar.phone.CentralSurfaces.KeyboardShortcutsMessage.class
                    java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L95
                    java.util.List r1 = (java.util.List) r1     // Catch: java.lang.Throwable -> L95
                    boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L95
                    if (r1 != 0) goto L97
                    java.util.Map r1 = r0.mDataMessageCancelers     // Catch: java.lang.Throwable -> L95
                    java.lang.Class<com.android.systemui.statusbar.phone.CentralSurfaces$KeyboardShortcutsMessage> r2 = com.android.systemui.statusbar.phone.CentralSurfaces.KeyboardShortcutsMessage.class
                    java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L95
                    java.util.List r1 = (java.util.List) r1     // Catch: java.lang.Throwable -> L95
                    r2 = 0
                    r1.remove(r2)     // Catch: java.lang.Throwable -> L95
                    java.util.Map r1 = r0.mDataMessageCancelers     // Catch: java.lang.Throwable -> L95
                    java.lang.Class<com.android.systemui.statusbar.phone.CentralSurfaces$KeyboardShortcutsMessage> r2 = com.android.systemui.statusbar.phone.CentralSurfaces.KeyboardShortcutsMessage.class
                    java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L95
                    java.util.List r1 = (java.util.List) r1     // Catch: java.lang.Throwable -> L95
                    boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L95
                    if (r1 == 0) goto L97
                    java.util.Map r0 = r0.mDataMessageCancelers     // Catch: java.lang.Throwable -> L95
                    java.lang.Class<com.android.systemui.statusbar.phone.CentralSurfaces$KeyboardShortcutsMessage> r1 = com.android.systemui.statusbar.phone.CentralSurfaces.KeyboardShortcutsMessage.class
                    r0.remove(r1)     // Catch: java.lang.Throwable -> L95
                    goto L97
                L95:
                    r0 = move-exception
                    goto L99
                L97:
                    monitor-exit(r6)     // Catch: java.lang.Throwable -> L95
                    return
                L99:
                    monitor-exit(r6)     // Catch: java.lang.Throwable -> L95
                    throw r0
                L9b:
                    monitor-exit(r1)     // Catch: java.lang.Throwable -> L4f
                    throw r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.util.concurrency.MessageRouterImpl$$ExternalSyntheticLambda0.run():void");
            }
        }, 0L);
        synchronized (messageRouterImpl2.mDataMessageCancelers) {
            ((HashMap) messageRouterImpl2.mDataMessageCancelers).putIfAbsent(CentralSurfaces.KeyboardShortcutsMessage.class, new ArrayList());
            ((List) messageRouterImpl2.mDataMessageCancelers.get(CentralSurfaces.KeyboardShortcutsMessage.class)).add(executeDelayed);
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void toggleNotificationsPanel() {
        boolean booleanValue = ((Boolean) ((ShadeInteractorImpl) ((ShadeInteractor) this.mShadeInteractorLazy.get())).baseShadeInteractor.isAnyExpanded().getValue()).booleanValue();
        ShadeController shadeController = this.mShadeController;
        if (booleanValue) {
            shadeController.animateCollapseShade(0);
        } else {
            ((BaseShadeControllerImpl) shadeController).animateExpandShade();
        }
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void toggleQuickSettingsPanel() {
        boolean booleanValue = ((Boolean) ((ShadeInteractorImpl) ((ShadeInteractor) this.mShadeInteractorLazy.get())).baseShadeInteractor.isQsExpanded().getValue()).booleanValue();
        ShadeController shadeController = this.mShadeController;
        if (booleanValue) {
            shadeController.animateCollapseShade(0);
        } else {
            ((BaseShadeControllerImpl) shadeController).animateExpandQs();
        }
    }

    public void vibrateOnNavigationKeyDown() {
        this.mShadeController.performHapticFeedback();
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void appTransitionCancelled(int i) {
    }

    @Override // com.android.systemui.statusbar.CommandQueue.Callbacks
    public final void appTransitionFinished(int i) {
    }
}

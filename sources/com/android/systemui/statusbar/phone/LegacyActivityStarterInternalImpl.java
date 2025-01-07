package com.android.systemui.statusbar.phone;

import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.IActivityTaskManager;
import android.app.IApplicationThread;
import android.app.PendingIntent;
import android.app.ProfilerInfo;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.view.RemoteAnimationAdapter;
import android.view.View;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.ActivityIntentHelper;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.animation.DelegateTransitionAnimatorController;
import com.android.systemui.animation.TransitionAnimator;
import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.domain.interactor.CommunalSettingsInteractor;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.keyguard.shared.model.BiometricUnlockSource;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.shade.domain.interactor.ShadeAnimationInteractor;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.window.StatusBarWindowControllerImpl;
import com.android.systemui.util.concurrency.DelayableExecutor;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.wm.shell.R;
import com.google.android.systemui.assist.AssistManagerGoogle;
import dagger.Lazy;
import java.util.Optional;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LegacyActivityStarterInternalImpl implements ActivityStarterInternal {
    public final ActivityIntentHelper activityIntentHelper;
    public final ActivityTransitionAnimator activityTransitionAnimator;
    public final Lazy assistManagerLazy;
    public final Lazy biometricUnlockControllerLazy;
    public final Lazy centralSurfacesOptLazy;
    public final CommandQueue commandQueue;
    public final CommunalSceneInteractor communalSceneInteractor;
    public final CommunalSettingsInteractor communalSettingsInteractor;
    public final Context context;
    public final DeviceProvisionedController deviceProvisionedController;
    public final int displayId;
    public final Lazy dozeServiceHostLazy;
    public final KeyguardStateController keyguardStateController;
    public final KeyguardUpdateMonitor keyguardUpdateMonitor;
    public final Lazy keyguardViewMediatorLazy;
    public final NotificationLockscreenUserManager lockScreenUserManager;
    public final DelayableExecutor mainExecutor;
    public final Lazy notifShadeWindowControllerLazy;
    public final ShadeAnimationInteractor shadeAnimationInteractor;
    public final Lazy shadeControllerLazy;
    public final Lazy statusBarKeyguardViewManagerLazy;
    public final SysuiStatusBarStateController statusBarStateController;
    public final StatusBarWindowControllerImpl statusBarWindowController;
    public final UserTracker userTracker;
    public final WakefulnessLifecycle wakefulnessLifecycle;

    public LegacyActivityStarterInternalImpl(Lazy lazy, KeyguardStateController keyguardStateController, SysuiStatusBarStateController sysuiStatusBarStateController, Lazy lazy2, Lazy lazy3, Lazy lazy4, Lazy lazy5, Lazy lazy6, CommandQueue commandQueue, ShadeAnimationInteractor shadeAnimationInteractor, Lazy lazy7, Lazy lazy8, ActivityTransitionAnimator activityTransitionAnimator, Context context, int i, NotificationLockscreenUserManager notificationLockscreenUserManager, StatusBarWindowControllerImpl statusBarWindowControllerImpl, WakefulnessLifecycle wakefulnessLifecycle, KeyguardUpdateMonitor keyguardUpdateMonitor, DeviceProvisionedController deviceProvisionedController, UserTracker userTracker, ActivityIntentHelper activityIntentHelper, DelayableExecutor delayableExecutor, CommunalSceneInteractor communalSceneInteractor, CommunalSettingsInteractor communalSettingsInteractor) {
        this.centralSurfacesOptLazy = lazy;
        this.keyguardStateController = keyguardStateController;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.assistManagerLazy = lazy2;
        this.dozeServiceHostLazy = lazy3;
        this.biometricUnlockControllerLazy = lazy4;
        this.keyguardViewMediatorLazy = lazy5;
        this.shadeControllerLazy = lazy6;
        this.commandQueue = commandQueue;
        this.shadeAnimationInteractor = shadeAnimationInteractor;
        this.statusBarKeyguardViewManagerLazy = lazy7;
        this.notifShadeWindowControllerLazy = lazy8;
        this.activityTransitionAnimator = activityTransitionAnimator;
        this.context = context;
        this.displayId = i;
        this.lockScreenUserManager = notificationLockscreenUserManager;
        this.statusBarWindowController = statusBarWindowControllerImpl;
        this.wakefulnessLifecycle = wakefulnessLifecycle;
        this.keyguardUpdateMonitor = keyguardUpdateMonitor;
        this.deviceProvisionedController = deviceProvisionedController;
        this.userTracker = userTracker;
        this.activityIntentHelper = activityIntentHelper;
        this.mainExecutor = delayableExecutor;
        this.communalSceneInteractor = communalSceneInteractor;
        this.communalSettingsInteractor = communalSettingsInteractor;
    }

    @Override // com.android.systemui.statusbar.phone.ActivityStarterInternal
    public final void dismissKeyguardThenExecute(ActivityStarter.OnDismissAction onDismissAction, Runnable runnable, boolean z, String str) {
        CentralSurfaces centralSurfaces$1;
        Log.i("LegacyActivityStarterInternalImpl", "Invoking dismissKeyguardThenExecute, afterKeyguardGone: " + z);
        boolean willRunAnimationOnKeyguard = onDismissAction.willRunAnimationOnKeyguard();
        KeyguardStateController keyguardStateController = this.keyguardStateController;
        if (!willRunAnimationOnKeyguard && this.wakefulnessLifecycle.mWakefulness == 0 && ((KeyguardStateControllerImpl) keyguardStateController).mCanDismissLockScreen && !((StatusBarStateControllerImpl) this.statusBarStateController).mLeaveOpenOnKeyguardHide && ((DozeServiceHost) this.dozeServiceHostLazy.get()).mPulsing) {
            ((BiometricUnlockController) this.biometricUnlockControllerLazy.get()).startWakeAndUnlock(2, (BiometricUnlockSource) null);
        }
        if (((KeyguardStateControllerImpl) keyguardStateController).mShowing) {
            ((StatusBarKeyguardViewManager) this.statusBarKeyguardViewManagerLazy.get()).dismissWithAction(onDismissAction, runnable, z, str);
            return;
        }
        if (this.keyguardUpdateMonitor.mIsDreaming && (centralSurfaces$1 = getCentralSurfaces$1()) != null) {
            ((CentralSurfacesImpl) centralSurfaces$1).awakenDreams();
        }
        onDismissAction.onDismiss();
    }

    @Override // com.android.systemui.statusbar.phone.ActivityStarterInternal
    public final void executeRunnableDismissingKeyguard(final Runnable runnable, Runnable runnable2, final boolean z, boolean z2, final boolean z3, final boolean z4, String str) {
        dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.statusbar.phone.LegacyActivityStarterInternalImpl$executeRunnableDismissingKeyguard$onDismissAction$1
            @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
            public final boolean onDismiss() {
                Runnable runnable3 = runnable;
                boolean z5 = z;
                LegacyActivityStarterInternalImpl legacyActivityStarterInternalImpl = this;
                if (runnable3 != null) {
                    boolean z6 = legacyActivityStarterInternalImpl.communalSettingsInteractor.isCommunalFlagEnabled() && ((Boolean) ((StateFlowImpl) legacyActivityStarterInternalImpl.communalSceneInteractor.isCommunalVisible.$$delegate_0).getValue()).booleanValue() && z5;
                    KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) legacyActivityStarterInternalImpl.keyguardStateController;
                    if (!keyguardStateControllerImpl.mShowing || !keyguardStateControllerImpl.mOccluded || legacyActivityStarterInternalImpl.isCommunalWidgetLaunch() || z6) {
                        ((ExecutorImpl) legacyActivityStarterInternalImpl.mainExecutor).execute(runnable);
                    } else {
                        ((StatusBarKeyguardViewManager) legacyActivityStarterInternalImpl.statusBarKeyguardViewManagerLazy.get()).mAfterKeyguardGoneRunnables.add(runnable);
                    }
                }
                if (z5) {
                    ((ShadeController) legacyActivityStarterInternalImpl.shadeControllerLazy.get()).collapseShadeForActivityStart();
                }
                return z3;
            }

            @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
            public final boolean willRunAnimationOnKeyguard() {
                return z4;
            }
        }, runnable2, z2, str);
    }

    public final UserHandle getActivityUserHandle$1(Intent intent) {
        for (String str : this.context.getResources().getStringArray(R.array.system_ui_packages)) {
            ComponentName component = intent.getComponent();
            if (component == null) {
                break;
            }
            if (Intrinsics.areEqual(str, component.getPackageName())) {
                return new UserHandle(UserHandle.myUserId());
            }
        }
        return ((UserTrackerImpl) this.userTracker).getUserHandle();
    }

    public final CentralSurfaces getCentralSurfaces$1() {
        return (CentralSurfaces) ((Optional) this.centralSurfacesOptLazy.get()).orElse(null);
    }

    public final boolean isCommunalWidgetLaunch() {
        if (this.communalSettingsInteractor.isCommunalFlagEnabled()) {
            CommunalSceneInteractor communalSceneInteractor = this.communalSceneInteractor;
            if (((Boolean) ((StateFlowImpl) communalSceneInteractor.isCommunalVisible.$$delegate_0).getValue()).booleanValue() && ((Boolean) ((StateFlowImpl) communalSceneInteractor.isLaunchingWidget.$$delegate_0).getValue()).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.systemui.statusbar.phone.ActivityStarterInternal
    public final boolean shouldAnimateLaunch(boolean z) {
        return shouldAnimateLaunch$1(z, false);
    }

    public final boolean shouldAnimateLaunch$1(boolean z, boolean z2) {
        boolean z3 = z2 || isCommunalWidgetLaunch();
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
        if (keyguardStateControllerImpl.mOccluded && !z3) {
            return false;
        }
        if (z2 || !keyguardStateControllerImpl.mShowing) {
            return true;
        }
        return z;
    }

    @Override // com.android.systemui.statusbar.phone.ActivityStarterInternal
    public final void startActivity(final Intent intent, boolean z, ActivityTransitionAnimator.Controller controller, boolean z2, UserHandle userHandle) {
        CentralSurfaces centralSurfaces$1;
        final UserHandle activityUserHandle$1 = userHandle == null ? getActivityUserHandle$1(intent) : userHandle;
        if (this.keyguardStateController.isUnlocked() || !z2) {
            startActivityDismissingKeyguard(intent, z, (r23 & 4) != 0 ? false : false, (r23 & 8) != 0 ? null : null, (r23 & 16) != 0 ? 0 : 0, (r23 & 32) != 0 ? null : controller, (r23 & 64) != 0 ? null : null, (r23 & 128) != 0 ? false : false, (r23 & 256) != 0 ? null : activityUserHandle$1);
            return;
        }
        boolean z3 = controller != null && shouldAnimateLaunch$1(true, z2);
        LegacyActivityStarterInternalImpl$wrapAnimationControllerForLockscreen$1$1 legacyActivityStarterInternalImpl$wrapAnimationControllerForLockscreen$1$1 = null;
        if (z3) {
            ActivityTransitionAnimator.Controller wrapAnimationControllerForShadeOrStatusBar$1 = wrapAnimationControllerForShadeOrStatusBar$1(controller, z, true);
            if (wrapAnimationControllerForShadeOrStatusBar$1 != null) {
                legacyActivityStarterInternalImpl$wrapAnimationControllerForLockscreen$1$1 = new LegacyActivityStarterInternalImpl$wrapAnimationControllerForLockscreen$1$1(wrapAnimationControllerForShadeOrStatusBar$1, this, z);
            }
        } else if (z) {
            ((ShadeController) this.shadeControllerLazy.get()).cancelExpansionAndCollapseShade();
        }
        LegacyActivityStarterInternalImpl$wrapAnimationControllerForLockscreen$1$1 legacyActivityStarterInternalImpl$wrapAnimationControllerForLockscreen$1$12 = legacyActivityStarterInternalImpl$wrapAnimationControllerForLockscreen$1$1;
        if (this.keyguardUpdateMonitor.mIsDreaming && (centralSurfaces$1 = getCentralSurfaces$1()) != null) {
            ((CentralSurfacesImpl) centralSurfaces$1).awakenDreams();
        }
        this.activityTransitionAnimator.startIntentWithAnimation(legacyActivityStarterInternalImpl$wrapAnimationControllerForLockscreen$1$12, z3, intent.getPackage(), z2, new Function1() { // from class: com.android.systemui.statusbar.phone.LegacyActivityStarterInternalImpl$startActivity$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(TaskStackBuilder.create(LegacyActivityStarterInternalImpl.this.context).addNextIntent(intent).startActivities(CentralSurfaces.getActivityOptions(LegacyActivityStarterInternalImpl.this.displayId, (RemoteAnimationAdapter) obj), activityUserHandle$1));
            }
        });
    }

    @Override // com.android.systemui.statusbar.phone.ActivityStarterInternal
    public final void startActivityDismissingKeyguard(final Intent intent, boolean z, boolean z2, final ActivityStarter.Callback callback, final int i, ActivityTransitionAnimator.Controller controller, String str, final boolean z3, UserHandle userHandle) {
        final UserHandle activityUserHandle$1 = userHandle == null ? getActivityUserHandle$1(intent) : userHandle;
        if (!z2 || ((DeviceProvisionedControllerImpl) this.deviceProvisionedController).deviceProvisioned.get()) {
            boolean z4 = this.activityIntentHelper.getTargetActivityInfo(intent, ((NotificationLockscreenUserManagerImpl) this.lockScreenUserManager).mCurrentUserId, false) == null;
            boolean z5 = (controller == null || z4 || !shouldAnimateLaunch$1(true, false)) ? false : true;
            final ActivityTransitionAnimator.Controller wrapAnimationControllerForShadeOrStatusBar$1 = wrapAnimationControllerForShadeOrStatusBar$1(controller, z, true);
            boolean z6 = z && wrapAnimationControllerForShadeOrStatusBar$1 == null;
            final boolean z7 = z5;
            Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.phone.LegacyActivityStarterInternalImpl$startActivityDismissingKeyguard$runnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    ((AssistManagerGoogle) LegacyActivityStarterInternalImpl.this.assistManagerLazy.get()).hideAssist();
                    Intent intent2 = intent;
                    intent2.setFlags((intent2.getFlags() & 131072) != 0 ? 268435456 : 335544320);
                    intent.addFlags(i);
                    final int[] iArr = {-96};
                    ActivityTransitionAnimator activityTransitionAnimator = LegacyActivityStarterInternalImpl.this.activityTransitionAnimator;
                    ActivityTransitionAnimator.Controller controller2 = wrapAnimationControllerForShadeOrStatusBar$1;
                    boolean z8 = z7;
                    String str2 = intent.getPackage();
                    final LegacyActivityStarterInternalImpl legacyActivityStarterInternalImpl = LegacyActivityStarterInternalImpl.this;
                    final boolean z9 = z3;
                    final Intent intent3 = intent;
                    final UserHandle userHandle2 = activityUserHandle$1;
                    Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.phone.LegacyActivityStarterInternalImpl$startActivityDismissingKeyguard$runnable$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            String action;
                            ActivityOptions activityOptions = new ActivityOptions(CentralSurfaces.getActivityOptions(LegacyActivityStarterInternalImpl.this.displayId, (RemoteAnimationAdapter) obj));
                            activityOptions.setDismissKeyguardIfInsecure();
                            activityOptions.setDisallowEnterPictureInPictureWhileLaunching(z9);
                            Intent intent4 = intent3;
                            if ((intent4 == null || (action = intent4.getAction()) == null) ? false : action.equals("android.media.action.STILL_IMAGE_CAMERA")) {
                                activityOptions.setRotationAnimationHint(3);
                            }
                            if ("android.settings.panel.action.VOLUME".equals(intent3.getAction())) {
                                activityOptions.setDisallowEnterPictureInPictureWhileLaunching(true);
                            }
                            try {
                                int[] iArr2 = iArr;
                                IActivityTaskManager service = ActivityTaskManager.getService();
                                String basePackageName = LegacyActivityStarterInternalImpl.this.context.getBasePackageName();
                                String attributionTag = LegacyActivityStarterInternalImpl.this.context.getAttributionTag();
                                Intent intent5 = intent3;
                                iArr2[0] = service.startActivityAsUser((IApplicationThread) null, basePackageName, attributionTag, intent5, intent5.resolveTypeIfNeeded(LegacyActivityStarterInternalImpl.this.context.getContentResolver()), (IBinder) null, (String) null, 0, 268435456, (ProfilerInfo) null, activityOptions.toBundle(), userHandle2.getIdentifier());
                            } catch (RemoteException e) {
                                Log.w("LegacyActivityStarterInternalImpl", "Unable to start activity", e);
                            }
                            return Integer.valueOf(iArr[0]);
                        }
                    };
                    TransitionAnimator.Timings timings = ActivityTransitionAnimator.TIMINGS;
                    activityTransitionAnimator.startIntentWithAnimation(controller2, z8, str2, false, function1);
                    ActivityStarter.Callback callback2 = callback;
                    if (callback2 != null) {
                        callback2.onActivityStarted(iArr[0]);
                    }
                }
            };
            Runnable runnable2 = new Runnable() { // from class: com.android.systemui.statusbar.phone.LegacyActivityStarterInternalImpl$startActivityDismissingKeyguard$cancelRunnable$1
                @Override // java.lang.Runnable
                public final void run() {
                    ActivityStarter.Callback callback2 = ActivityStarter.Callback.this;
                    if (callback2 != null) {
                        callback2.onActivityStarted(-96);
                    }
                }
            };
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
            executeRunnableDismissingKeyguard(runnable, runnable2, z6, z4, !(keyguardStateControllerImpl.mShowing && keyguardStateControllerImpl.mOccluded) || (this.communalSettingsInteractor.isCommunalFlagEnabled() && ((Boolean) ((StateFlowImpl) this.communalSceneInteractor.isCommunalVisible.$$delegate_0).getValue()).booleanValue() && z6), z5, str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0, types: [com.android.systemui.statusbar.phone.LegacyActivityStarterInternalImpl$startPendingIntentDismissingKeyguard$runnable$1, java.lang.Runnable] */
    @Override // com.android.systemui.statusbar.phone.ActivityStarterInternal
    public final void startPendingIntentDismissingKeyguard(final PendingIntent pendingIntent, boolean z, final Runnable runnable, View view, ActivityTransitionAnimator.Controller controller, boolean z2, boolean z3, final Intent intent, final Bundle bundle, final String str) {
        ActivityTransitionAnimator.Controller controller2;
        final ActivityTransitionAnimator.Controller controller3;
        if (view instanceof ExpandableNotificationRow) {
            CentralSurfaces centralSurfaces$1 = getCentralSurfaces$1();
            controller2 = centralSurfaces$1 != null ? ((CentralSurfacesImpl) centralSurfaces$1).mNotificationAnimationProvider.getAnimatorController((ExpandableNotificationRow) view) : null;
        } else {
            controller2 = controller;
        }
        boolean isActivity = pendingIntent.isActivity();
        NotificationLockscreenUserManager notificationLockscreenUserManager = this.lockScreenUserManager;
        ActivityIntentHelper activityIntentHelper = this.activityIntentHelper;
        boolean z4 = isActivity && activityIntentHelper.getPendingTargetActivityInfo(pendingIntent, ((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager).mCurrentUserId) == null;
        boolean z5 = z2 && pendingIntent.isActivity() && (z3 || activityIntentHelper.wouldPendingShowOverLockscreen(pendingIntent, ((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager).mCurrentUserId));
        boolean z6 = (z4 || controller2 == null || !shouldAnimateLaunch$1(pendingIntent.isActivity(), z5)) ? false : true;
        ActivityTransitionAnimator.Controller wrapAnimationControllerForShadeOrStatusBar$1 = wrapAnimationControllerForShadeOrStatusBar$1(controller2, z, pendingIntent.isActivity());
        if (z5) {
            controller3 = wrapAnimationControllerForShadeOrStatusBar$1 != null ? new LegacyActivityStarterInternalImpl$wrapAnimationControllerForLockscreen$1$1(wrapAnimationControllerForShadeOrStatusBar$1, this, z) : null;
        } else {
            controller3 = wrapAnimationControllerForShadeOrStatusBar$1;
        }
        boolean z7 = isCommunalWidgetLaunch() && !z5;
        boolean z8 = (z || z7) && !z6;
        final boolean z9 = z6;
        final boolean z10 = z5;
        final boolean z11 = z8;
        final ?? r9 = new Runnable() { // from class: com.android.systemui.statusbar.phone.LegacyActivityStarterInternalImpl$startPendingIntentDismissingKeyguard$runnable$1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    ActivityTransitionAnimator activityTransitionAnimator = LegacyActivityStarterInternalImpl.this.activityTransitionAnimator;
                    ActivityTransitionAnimator.Controller controller4 = controller3;
                    boolean z12 = z9;
                    String creatorPackage = pendingIntent.getCreatorPackage();
                    boolean z13 = z10;
                    final LegacyActivityStarterInternalImpl legacyActivityStarterInternalImpl = LegacyActivityStarterInternalImpl.this;
                    final PendingIntent pendingIntent2 = pendingIntent;
                    final Intent intent2 = intent;
                    final Bundle bundle2 = bundle;
                    activityTransitionAnimator.startPendingIntentWithAnimation(controller4, z12, creatorPackage, z13, new ActivityTransitionAnimator.PendingIntentStarter() { // from class: com.android.systemui.statusbar.phone.LegacyActivityStarterInternalImpl$startPendingIntentDismissingKeyguard$runnable$1.1
                        @Override // com.android.systemui.animation.ActivityTransitionAnimator.PendingIntentStarter
                        public final int startPendingIntent(RemoteAnimationAdapter remoteAnimationAdapter) {
                            LegacyActivityStarterInternalImpl legacyActivityStarterInternalImpl2 = LegacyActivityStarterInternalImpl.this;
                            Bundle activityOptions = CentralSurfaces.getActivityOptions(legacyActivityStarterInternalImpl2.displayId, remoteAnimationAdapter);
                            Bundle bundle3 = bundle2;
                            if (bundle3 != null) {
                                activityOptions.putAll(bundle3);
                            }
                            ActivityOptions activityOptions2 = new ActivityOptions(activityOptions);
                            activityOptions2.setEligibleForLegacyPermissionPrompt(true);
                            activityOptions2.setPendingIntentBackgroundActivityStartMode(1);
                            return pendingIntent2.sendAndReturnResult(legacyActivityStarterInternalImpl2.context, 0, intent2, null, null, null, activityOptions2.toBundle());
                        }
                    });
                } catch (PendingIntent.CanceledException e) {
                    Log.w("LegacyActivityStarterInternalImpl", "Sending intent failed: " + e);
                    if (!z11) {
                        ((ShadeController) LegacyActivityStarterInternalImpl.this.shadeControllerLazy.get()).collapseOnMainThread();
                    }
                }
                if (pendingIntent.isActivity()) {
                    ((AssistManagerGoogle) LegacyActivityStarterInternalImpl.this.assistManagerLazy.get()).hideAssist();
                }
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    LegacyActivityStarterInternalImpl.this.mainExecutor.executeDelayed(runnable2, 0);
                }
            }
        };
        DelayableExecutor delayableExecutor = this.mainExecutor;
        if (z5) {
            delayableExecutor.executeDelayed(r9, 0);
            return;
        }
        final boolean z12 = z8;
        final boolean z13 = z4;
        final boolean z14 = z7;
        final boolean z15 = z6;
        delayableExecutor.executeDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.LegacyActivityStarterInternalImpl$startPendingIntentDismissingKeyguard$1
            @Override // java.lang.Runnable
            public final void run() {
                LegacyActivityStarterInternalImpl.this.executeRunnableDismissingKeyguard(r9, (r18 & 2) != 0 ? null : null, (r18 & 4) != 0 ? false : z12, (r18 & 8) != 0 ? false : z13, (r18 & 16) != 0 ? false : z14, (r18 & 32) != 0 ? false : z15, (r18 & 64) != 0 ? null : str);
            }
        }, 0);
    }

    public final ActivityTransitionAnimator.Controller wrapAnimationControllerForShadeOrStatusBar$1(ActivityTransitionAnimator.Controller controller, boolean z, boolean z2) {
        Optional of;
        if (controller == null) {
            return null;
        }
        View rootView = controller.getTransitionContainer().getRootView();
        Intrinsics.checkNotNull(rootView);
        final StatusBarWindowControllerImpl statusBarWindowControllerImpl = this.statusBarWindowController;
        if (rootView != statusBarWindowControllerImpl.mStatusBarWindowView) {
            of = Optional.empty();
        } else {
            controller.setTransitionContainer(statusBarWindowControllerImpl.mLaunchAnimationContainer);
            of = Optional.of(new DelegateTransitionAnimatorController(controller) { // from class: com.android.systemui.statusbar.window.StatusBarWindowControllerImpl.1
                public AnonymousClass1(ActivityTransitionAnimator.Controller controller2) {
                    super(controller2);
                }

                @Override // com.android.systemui.animation.DelegateTransitionAnimatorController, com.android.systemui.animation.TransitionAnimator.Controller
                public final void onTransitionAnimationEnd(boolean z3) {
                    this.delegate.onTransitionAnimationEnd(z3);
                    StatusBarWindowControllerImpl statusBarWindowControllerImpl2 = StatusBarWindowControllerImpl.this;
                    State state = statusBarWindowControllerImpl2.mCurrentState;
                    if (state.mIsLaunchAnimationRunning) {
                        state.mIsLaunchAnimationRunning = false;
                        statusBarWindowControllerImpl2.apply(state);
                    }
                }

                @Override // com.android.systemui.animation.DelegateTransitionAnimatorController, com.android.systemui.animation.TransitionAnimator.Controller
                public final void onTransitionAnimationStart(boolean z3) {
                    this.delegate.onTransitionAnimationStart(z3);
                    StatusBarWindowControllerImpl statusBarWindowControllerImpl2 = StatusBarWindowControllerImpl.this;
                    State state = statusBarWindowControllerImpl2.mCurrentState;
                    if (true == state.mIsLaunchAnimationRunning) {
                        return;
                    }
                    state.mIsLaunchAnimationRunning = true;
                    statusBarWindowControllerImpl2.apply(state);
                }
            });
        }
        if (of.isPresent()) {
            return (ActivityTransitionAnimator.Controller) of.get();
        }
        if (getCentralSurfaces$1() == null || !z) {
            return controller2;
        }
        return new StatusBarTransitionAnimatorController(controller2, this.shadeAnimationInteractor, (ShadeController) this.shadeControllerLazy.get(), (NotificationShadeWindowController) this.notifShadeWindowControllerLazy.get(), this.commandQueue, this.displayId, z2);
    }
}

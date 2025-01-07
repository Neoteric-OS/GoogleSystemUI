package com.android.systemui.keyguard;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Debug;
import android.os.IBinder;
import android.os.Message;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.Trace;
import android.util.ArrayMap;
import android.util.Log;
import android.util.RotationUtils;
import android.util.Slog;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.RemoteAnimationAdapter;
import android.view.RemoteAnimationDefinition;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.view.WindowManagerPolicyConstants;
import android.window.IRemoteTransitionFinishedCallback;
import android.window.RemoteTransitionStub;
import android.window.TransitionInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.foldables.FoldGracePeriodProvider;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.internal.policy.IKeyguardDrawnCallback;
import com.android.internal.policy.IKeyguardExitCallback;
import com.android.internal.policy.IKeyguardService;
import com.android.internal.policy.IKeyguardStateCallback;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitor$$ExternalSyntheticLambda3;
import com.android.keyguard.mediator.ScreenOnCoordinator;
import com.android.systemui.SystemUIApplication;
import com.android.systemui.animation.RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.keyguard.KeyguardService;
import com.android.systemui.keyguard.KeyguardViewMediator;
import com.android.systemui.keyguard.KeyguardViewMediator.AnonymousClass8;
import com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl;
import com.android.systemui.keyguard.domain.interactor.KeyguardDismissInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardEnabledInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardStateCallbackInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardWakeDirectlyToGoneInteractor;
import com.android.systemui.keyguard.domain.interactor.ToAodFoldTransitionInteractor;
import com.android.systemui.keyguard.ui.binder.KeyguardSurfaceBehindParamsApplier;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardSurfaceBehindViewModel;
import com.android.systemui.keyguard.ui.viewmodel.WindowManagerLockscreenVisibilityViewModel;
import com.android.systemui.power.data.repository.PowerRepositoryImpl;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.power.shared.model.ScreenPowerState;
import com.android.systemui.power.shared.model.WakeSleepReason;
import com.android.systemui.power.shared.model.WakefulnessModel;
import com.android.systemui.power.shared.model.WakefulnessState;
import com.android.systemui.settings.DisplayTracker;
import com.android.systemui.shade.NotificationPanelView;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.ScreenOffAnimation;
import com.android.systemui.unfold.FoldAodAnimationController;
import com.android.systemui.unfold.FoldAodAnimationController$onScreenTurnedOn$1;
import com.android.systemui.unfold.FullscreenLightRevealAnimation;
import com.android.systemui.util.concurrency.ExecutorImpl;
import com.android.systemui.util.concurrency.PendingTasksContainer;
import com.android.systemui.util.concurrency.PendingTasksContainer$registerTask$1;
import com.android.systemui.util.time.SystemClockImpl;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.CounterRotator;
import com.android.wm.shell.shared.ShellTransitions;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.transition.Transitions;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class KeyguardService extends Service {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final DisplayTracker mDisplayTracker;
    public final KeyguardEnabledInteractor mKeyguardEnabledInteractor;
    public final KeyguardInteractor mKeyguardInteractor;
    public final KeyguardLifecyclesDispatcher mKeyguardLifecyclesDispatcher;
    public final KeyguardViewMediator mKeyguardViewMediator;
    public final KeyguardWakeDirectlyToGoneInteractor mKeyguardWakeDirectlyToGoneInteractor;
    public final PowerInteractor mPowerInteractor;
    public final ScreenOnCoordinator mScreenOnCoordinator;
    public final ShellTransitions mShellTransitions;
    public final AnonymousClass2 mFoldGracePeriodProvider = new AnonymousClass2();
    public final AnonymousClass3 mBinder = new IKeyguardService.Stub() { // from class: com.android.systemui.keyguard.KeyguardService.3

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.KeyguardService$3$1, reason: invalid class name */
        public final class AnonymousClass1 implements Runnable {
            public boolean mInvoked;
            public final /* synthetic */ IKeyguardDrawnCallback val$callback;
            public final /* synthetic */ int val$traceCookie;

            public AnonymousClass1(IKeyguardDrawnCallback iKeyguardDrawnCallback, int i) {
                this.val$callback = iKeyguardDrawnCallback;
                this.val$traceCookie = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                if (this.val$callback == null) {
                    return;
                }
                if (this.mInvoked) {
                    Log.w("KeyguardService", "KeyguardDrawnCallback#onDrawn() invoked > 1 times");
                    return;
                }
                this.mInvoked = true;
                try {
                    Trace.endAsyncSection("Waiting for KeyguardDrawnCallback#onDrawn", this.val$traceCookie);
                    this.val$callback.onDrawn();
                } catch (RemoteException e) {
                    Log.w("KeyguardService", "Exception calling onDrawn():", e);
                }
            }
        }

        public static void trace(String str) {
            Trace.asyncTraceForTrackEnd(4096L, "IKeyguardService", 0);
            Trace.asyncTraceForTrackBegin(4096L, "IKeyguardService", str, 0);
        }

        public final void addStateMonitorCallback(IKeyguardStateCallback iKeyguardStateCallback) {
            trace("addStateMonitorCallback");
            KeyguardService.this.checkPermission();
            KeyguardViewMediator keyguardViewMediator = KeyguardService.this.mKeyguardViewMediator;
            synchronized (keyguardViewMediator) {
                keyguardViewMediator.mKeyguardStateCallbacks.add(iKeyguardStateCallback);
                try {
                    iKeyguardStateCallback.onSimSecureStateChanged(keyguardViewMediator.mUpdateMonitor.isSimPinSecure());
                    iKeyguardStateCallback.onShowingStateChanged(keyguardViewMediator.mShowing, keyguardViewMediator.mSelectedUserInteractor.getSelectedUserId());
                    iKeyguardStateCallback.onInputRestrictedStateChanged(keyguardViewMediator.mInputRestricted);
                    iKeyguardStateCallback.onTrustedChanged(keyguardViewMediator.mUpdateMonitor.getUserHasTrust(keyguardViewMediator.mSelectedUserInteractor.getSelectedUserId()));
                } catch (RemoteException e) {
                    Slog.w("KeyguardViewMediator", "Failed to call to IKeyguardStateCallback", e);
                }
            }
        }

        public final void dismiss(IKeyguardDismissCallback iKeyguardDismissCallback, CharSequence charSequence) {
            trace("dismiss message=" + ((Object) charSequence));
            KeyguardService.this.checkPermission();
            KeyguardViewMediator keyguardViewMediator = KeyguardService.this.mKeyguardViewMediator;
            if (keyguardViewMediator.mIgnoreDismiss) {
                Log.i("KeyguardViewMediator", "Ignoring request to dismiss (user switch in progress?)");
            } else {
                keyguardViewMediator.mHandler.obtainMessage(11, new KeyguardViewMediator.DismissMessage(iKeyguardDismissCallback, charSequence)).sendToTarget();
            }
        }

        public final void dismissKeyguardToLaunch(Intent intent) {
            trace("dismissKeyguardToLaunch");
            KeyguardService.this.checkPermission();
            Slog.d("KeyguardService", "Ignoring dismissKeyguardToLaunch " + intent);
        }

        public final void doKeyguardTimeout(Bundle bundle) {
            trace("doKeyguardTimeout");
            KeyguardService.this.checkPermission();
            KeyguardViewMediator.AnonymousClass13 anonymousClass13 = KeyguardService.this.mKeyguardViewMediator.mHandler;
            anonymousClass13.removeMessages(10);
            anonymousClass13.sendMessageAtFrontOfQueue(anonymousClass13.obtainMessage(10, bundle));
        }

        public final void onBootCompleted() {
            trace("onBootCompleted");
            KeyguardService.this.checkPermission();
            KeyguardService.this.mKeyguardViewMediator.onBootCompleted();
        }

        public final void onDreamingStarted() {
            trace("onDreamingStarted");
            KeyguardService.this.checkPermission();
            KeyguardService.this.mKeyguardWakeDirectlyToGoneInteractor.getClass();
            KeyguardRepositoryImpl keyguardRepositoryImpl = KeyguardService.this.mKeyguardInteractor.repository;
            Boolean bool = Boolean.TRUE;
            StateFlowImpl stateFlowImpl = keyguardRepositoryImpl.isDreaming;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, bool);
            KeyguardViewMediator keyguardViewMediator = KeyguardService.this.mKeyguardViewMediator;
            KeyguardUpdateMonitor.AnonymousClass13 anonymousClass13 = keyguardViewMediator.mUpdateMonitor.mHandler;
            anonymousClass13.sendMessage(anonymousClass13.obtainMessage(333, 1, 0));
            synchronized (keyguardViewMediator) {
                if (keyguardViewMediator.mDeviceInteractive) {
                    long lockTimeout = keyguardViewMediator.getLockTimeout(keyguardViewMediator.mSelectedUserInteractor.getSelectedUserId());
                    if (lockTimeout == 0) {
                        keyguardViewMediator.doKeyguardLocked(null);
                    } else {
                        keyguardViewMediator.doKeyguardLaterLocked(lockTimeout);
                    }
                }
            }
        }

        public final void onDreamingStopped() {
            trace("onDreamingStopped");
            KeyguardService.this.checkPermission();
            KeyguardService keyguardService = KeyguardService.this;
            KeyguardWakeDirectlyToGoneInteractor keyguardWakeDirectlyToGoneInteractor = keyguardService.mKeyguardWakeDirectlyToGoneInteractor;
            if (keyguardWakeDirectlyToGoneInteractor.isAwake) {
                keyguardWakeDirectlyToGoneInteractor.timeoutCounter++;
            }
            KeyguardRepositoryImpl keyguardRepositoryImpl = keyguardService.mKeyguardInteractor.repository;
            Boolean bool = Boolean.FALSE;
            StateFlowImpl stateFlowImpl = keyguardRepositoryImpl.isDreaming;
            stateFlowImpl.getClass();
            stateFlowImpl.updateState(null, bool);
            KeyguardViewMediator keyguardViewMediator = KeyguardService.this.mKeyguardViewMediator;
            KeyguardUpdateMonitor.AnonymousClass13 anonymousClass13 = keyguardViewMediator.mUpdateMonitor.mHandler;
            anonymousClass13.sendMessage(anonymousClass13.obtainMessage(333, 0, 0));
            synchronized (keyguardViewMediator) {
                if (keyguardViewMediator.mDeviceInteractive) {
                    keyguardViewMediator.mDelayedShowingSequence++;
                }
            }
        }

        public final void onFinishedGoingToSleep(int i, boolean z) {
            boolean z2;
            boolean z3;
            trace("onFinishedGoingToSleep pmSleepReason=" + i + " cameraGestureTriggered=" + z);
            KeyguardService.this.checkPermission();
            KeyguardViewMediator keyguardViewMediator = KeyguardService.this.mKeyguardViewMediator;
            int translateSleepReasonToOffReason = WindowManagerPolicyConstants.translateSleepReasonToOffReason(i);
            if (KeyguardViewMediator.DEBUG) {
                keyguardViewMediator.getClass();
                Log.d("KeyguardViewMediator", "onFinishedGoingToSleep(" + translateSleepReasonToOffReason + ")");
            }
            synchronized (keyguardViewMediator) {
                try {
                    keyguardViewMediator.mDeviceInteractive = false;
                    keyguardViewMediator.mGoingToSleep = false;
                    keyguardViewMediator.mWakeAndUnlocking = false;
                    List list = keyguardViewMediator.mDozeParameters.mScreenOffAnimationController.animations;
                    if (list == null || !list.isEmpty()) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            if (!((ScreenOffAnimation) it.next()).shouldAnimateDozingChange()) {
                                z2 = false;
                                break;
                            }
                        }
                    }
                    z2 = true;
                    keyguardViewMediator.mAnimatingScreenOff = z2;
                    keyguardViewMediator.resetKeyguardDonePendingLocked();
                    keyguardViewMediator.mHideAnimationRun = false;
                    boolean z4 = KeyguardViewMediator.DEBUG;
                    if (z4) {
                        Log.d("KeyguardViewMediator", "notifyFinishedGoingToSleep");
                    }
                    keyguardViewMediator.mHandler.sendEmptyMessage(5);
                    if (z) {
                        PowerManager powerManager = (PowerManager) keyguardViewMediator.mContext.getSystemService(PowerManager.class);
                        ((SystemClockImpl) keyguardViewMediator.mSystemClock).getClass();
                        powerManager.wakeUp(SystemClock.uptimeMillis(), 5, "com.android.systemui:CAMERA_GESTURE_PREVENT_LOCK");
                        keyguardViewMediator.setPendingLock(false);
                        keyguardViewMediator.mPendingReset = false;
                        keyguardViewMediator.mPowerGestureIntercepted = true;
                        if (z4) {
                            Log.d("KeyguardViewMediator", "cameraGestureTriggered=" + z + ",mPowerGestureIntercepted=" + keyguardViewMediator.mPowerGestureIntercepted);
                        }
                    }
                    if (keyguardViewMediator.mPendingReset) {
                        keyguardViewMediator.resetStateLocked(true);
                        keyguardViewMediator.mPendingReset = false;
                    }
                    keyguardViewMediator.maybeHandlePendingLock();
                    if (!keyguardViewMediator.mLockLater && !z) {
                        keyguardViewMediator.doKeyguardForChildProfilesLocked();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            KeyguardUpdateMonitor keyguardUpdateMonitor = keyguardViewMediator.mUpdateMonitor;
            synchronized (keyguardUpdateMonitor) {
                keyguardUpdateMonitor.mDeviceInteractive = false;
            }
            KeyguardUpdateMonitor.AnonymousClass13 anonymousClass13 = keyguardUpdateMonitor.mHandler;
            anonymousClass13.sendMessage(anonymousClass13.obtainMessage(320, translateSleepReasonToOffReason, 0));
            PowerInteractor powerInteractor = KeyguardService.this.mPowerInteractor;
            if (z) {
                powerInteractor.getClass();
            } else if (!((WakefulnessModel) ((StateFlowImpl) powerInteractor.repository.wakefulness.$$delegate_0).getValue()).powerButtonLaunchGestureTriggered) {
                z3 = false;
                PowerRepositoryImpl.updateWakefulness$default(powerInteractor.repository, WakefulnessState.ASLEEP, null, null, z3, 6);
                KeyguardService.this.mKeyguardLifecyclesDispatcher.mHandler.obtainMessage(7).sendToTarget();
            }
            z3 = true;
            PowerRepositoryImpl.updateWakefulness$default(powerInteractor.repository, WakefulnessState.ASLEEP, null, null, z3, 6);
            KeyguardService.this.mKeyguardLifecyclesDispatcher.mHandler.obtainMessage(7).sendToTarget();
        }

        public final void onFinishedWakingUp() {
            trace("onFinishedWakingUp");
            Trace.beginSection("KeyguardService.mBinder#onFinishedWakingUp");
            KeyguardService.this.checkPermission();
            PowerInteractor powerInteractor = KeyguardService.this.mPowerInteractor;
            powerInteractor.getClass();
            PowerRepositoryImpl.updateWakefulness$default(powerInteractor.repository, WakefulnessState.AWAKE, null, null, false, 14);
            KeyguardService.this.mKeyguardLifecyclesDispatcher.mHandler.obtainMessage(5).sendToTarget();
            Trace.endSection();
        }

        public final void onScreenTurnedOff() {
            trace("onScreenTurnedOff");
            KeyguardService.this.checkPermission();
            KeyguardService.this.mPowerInteractor.onScreenPowerStateUpdated(ScreenPowerState.SCREEN_OFF);
            KeyguardService.this.mKeyguardViewMediator.mUpdateMonitor.mHandler.sendEmptyMessage(332);
            KeyguardService.this.mKeyguardLifecyclesDispatcher.mHandler.obtainMessage(3).sendToTarget();
            PendingTasksContainer pendingTasksContainer = KeyguardService.this.mScreenOnCoordinator.pendingTasks;
            pendingTasksContainer.getClass();
            pendingTasksContainer.completionCallback = new AtomicReference();
            pendingTasksContainer.pendingTasksCount = new AtomicInteger(0);
        }

        public final void onScreenTurnedOn() {
            trace("onScreenTurnedOn");
            Trace.beginSection("KeyguardService.mBinder#onScreenTurnedOn");
            KeyguardService.this.checkPermission();
            KeyguardService.this.mPowerInteractor.onScreenPowerStateUpdated(ScreenPowerState.SCREEN_ON);
            KeyguardService.this.mKeyguardLifecyclesDispatcher.mHandler.obtainMessage(1).sendToTarget();
            FoldAodAnimationController foldAodAnimationController = KeyguardService.this.mScreenOnCoordinator.foldAodAnimationController;
            if (foldAodAnimationController != null) {
                ((ExecutorImpl) foldAodAnimationController.mainExecutor).execute(new FoldAodAnimationController$onScreenTurnedOn$1(foldAodAnimationController, 0));
            }
            Trace.endSection();
        }

        public final void onScreenTurningOff() {
            trace("onScreenTurningOff");
            KeyguardService.this.checkPermission();
            KeyguardService.this.mPowerInteractor.onScreenPowerStateUpdated(ScreenPowerState.SCREEN_TURNING_OFF);
            KeyguardService.this.mKeyguardLifecyclesDispatcher.mHandler.obtainMessage(2).sendToTarget();
        }

        public final void onScreenTurningOn(IKeyguardDrawnCallback iKeyguardDrawnCallback) {
            Runnable runnable;
            trace("onScreenTurningOn");
            Trace.beginSection("KeyguardService.mBinder#onScreenTurningOn");
            KeyguardService.this.checkPermission();
            KeyguardService.this.mPowerInteractor.onScreenPowerStateUpdated(ScreenPowerState.SCREEN_TURNING_ON);
            KeyguardService.this.mKeyguardLifecyclesDispatcher.mHandler.obtainMessage(0, iKeyguardDrawnCallback).sendToTarget();
            int identityHashCode = System.identityHashCode(iKeyguardDrawnCallback);
            Trace.beginAsyncSection("Waiting for KeyguardDrawnCallback#onDrawn", identityHashCode);
            final ScreenOnCoordinator screenOnCoordinator = KeyguardService.this.mScreenOnCoordinator;
            final AnonymousClass1 anonymousClass1 = new AnonymousClass1(iKeyguardDrawnCallback, identityHashCode);
            screenOnCoordinator.getClass();
            Trace.beginSection("ScreenOnCoordinator#onScreenTurningOn");
            PendingTasksContainer pendingTasksContainer = screenOnCoordinator.pendingTasks;
            pendingTasksContainer.getClass();
            pendingTasksContainer.completionCallback = new AtomicReference();
            pendingTasksContainer.pendingTasksCount = new AtomicInteger(0);
            final FoldAodAnimationController foldAodAnimationController = screenOnCoordinator.foldAodAnimationController;
            if (foldAodAnimationController != null) {
                PendingTasksContainer pendingTasksContainer2 = screenOnCoordinator.pendingTasks;
                pendingTasksContainer2.pendingTasksCount.incrementAndGet();
                Trace.beginAsyncSection("PendingTasksContainer#".concat("fold-to-aod"), 0);
                final PendingTasksContainer$registerTask$1 pendingTasksContainer$registerTask$1 = new PendingTasksContainer$registerTask$1("fold-to-aod", pendingTasksContainer2);
                ((ExecutorImpl) foldAodAnimationController.mainExecutor).execute(new Runnable() { // from class: com.android.systemui.unfold.FoldAodAnimationController$onScreenTurningOn$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        FoldAodAnimationController foldAodAnimationController2 = FoldAodAnimationController.this;
                        if (foldAodAnimationController2.shouldPlayAnimation) {
                            if (foldAodAnimationController2.isScrimOpaque) {
                                pendingTasksContainer$registerTask$1.run();
                            } else {
                                foldAodAnimationController2.pendingScrimReadyCallback = pendingTasksContainer$registerTask$1;
                            }
                        } else if (foldAodAnimationController2.isFolded && !foldAodAnimationController2.isFoldHandled && foldAodAnimationController2.alwaysOnEnabled && ((Boolean) ((KeyguardInteractor) foldAodAnimationController2.keyguardInteractor.get()).isDozing.getValue()).booleanValue()) {
                            FoldAodAnimationController.this.setAnimationState(true);
                            NotificationPanelViewController.ShadeFoldAnimatorImpl shadeFoldAnimatorImpl = ((ToAodFoldTransitionInteractor) FoldAodAnimationController.this.foldTransitionInteractor.get()).foldAnimator.this$0.parentAnimator;
                            if (shadeFoldAnimatorImpl != null) {
                                NotificationPanelViewController notificationPanelViewController = NotificationPanelViewController.this;
                                notificationPanelViewController.setDozing(true, false);
                                NotificationPanelView notificationPanelView = notificationPanelViewController.mView;
                                notificationPanelView.setTranslationX(-notificationPanelView.getResources().getDimensionPixelSize(R.dimen.below_clock_padding_start));
                                notificationPanelView.setAlpha(0.0f);
                            }
                            pendingTasksContainer$registerTask$1.run();
                        } else {
                            pendingTasksContainer$registerTask$1.run();
                        }
                        FoldAodAnimationController foldAodAnimationController3 = FoldAodAnimationController.this;
                        if (foldAodAnimationController3.isFolded) {
                            foldAodAnimationController3.isFoldHandled = true;
                        }
                    }
                });
            }
            Set<FullscreenLightRevealAnimation> set = screenOnCoordinator.fullScreenLightRevealAnimations;
            if (set != null) {
                for (FullscreenLightRevealAnimation fullscreenLightRevealAnimation : set) {
                    PendingTasksContainer pendingTasksContainer3 = screenOnCoordinator.pendingTasks;
                    String simpleName = fullscreenLightRevealAnimation.getClass().getSimpleName();
                    pendingTasksContainer3.pendingTasksCount.incrementAndGet();
                    Trace.beginAsyncSection("PendingTasksContainer#".concat(simpleName), 0);
                    fullscreenLightRevealAnimation.onScreenTurningOn(new PendingTasksContainer$registerTask$1(simpleName, pendingTasksContainer3));
                }
            }
            PendingTasksContainer pendingTasksContainer4 = screenOnCoordinator.pendingTasks;
            pendingTasksContainer4.completionCallback.set(new Runnable(screenOnCoordinator) { // from class: com.android.keyguard.mediator.ScreenOnCoordinator$onScreenTurningOn$2
                @Override // java.lang.Runnable
                public final void run() {
                    KeyguardService.AnonymousClass3.AnonymousClass1.this.run();
                }
            });
            if (pendingTasksContainer4.pendingTasksCount.get() == 0 && (runnable = (Runnable) pendingTasksContainer4.completionCallback.getAndSet(null)) != null) {
                runnable.run();
            }
            Trace.endSection();
            Trace.endSection();
        }

        public final void onShortPowerPressedGoHome() {
            trace("onShortPowerPressedGoHome");
            KeyguardService.this.checkPermission();
            KeyguardService.this.mKeyguardViewMediator.getClass();
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x00a1 A[Catch: all -> 0x005f, TryCatch #0 {all -> 0x005f, blocks: (B:7:0x003f, B:9:0x0054, B:13:0x0063, B:15:0x0074, B:17:0x007c, B:18:0x009d, B:20:0x00a1, B:21:0x00a6, B:44:0x008c, B:47:0x0092, B:49:0x009a), top: B:6:0x003f }] */
        /* JADX WARN: Removed duplicated region for block: B:49:0x009a A[Catch: all -> 0x005f, TryCatch #0 {all -> 0x005f, blocks: (B:7:0x003f, B:9:0x0054, B:13:0x0063, B:15:0x0074, B:17:0x007c, B:18:0x009d, B:20:0x00a1, B:21:0x00a6, B:44:0x008c, B:47:0x0092, B:49:0x009a), top: B:6:0x003f }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void onStartedGoingToSleep(int r14) {
            /*
                Method dump skipped, instructions count: 274
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.KeyguardService.AnonymousClass3.onStartedGoingToSleep(int):void");
        }

        public final void onStartedWakingUp(int i, boolean z) {
            boolean z2;
            trace("onStartedWakingUp pmWakeReason=" + i + " cameraGestureTriggered=" + z);
            Trace.beginSection("KeyguardService.mBinder#onStartedWakingUp");
            KeyguardService.this.checkPermission();
            KeyguardViewMediator keyguardViewMediator = KeyguardService.this.mKeyguardViewMediator;
            keyguardViewMediator.getClass();
            Trace.beginSection("KeyguardViewMediator#onStartedWakingUp");
            synchronized (keyguardViewMediator) {
                try {
                    keyguardViewMediator.mDeviceInteractive = true;
                    if (keyguardViewMediator.mPendingLock && !z && !keyguardViewMediator.mWakeAndUnlocking) {
                        keyguardViewMediator.doKeyguardLocked(null);
                    }
                    keyguardViewMediator.mAnimatingScreenOff = false;
                    keyguardViewMediator.mDelayedShowingSequence++;
                    keyguardViewMediator.mDelayedProfileShowingSequence++;
                    if (z) {
                        keyguardViewMediator.mPowerGestureIntercepted = true;
                    }
                    boolean z3 = KeyguardViewMediator.DEBUG;
                    if (z3) {
                        Log.d("KeyguardViewMediator", "onStartedWakingUp, seq = " + keyguardViewMediator.mDelayedShowingSequence + ", mPowerGestureIntercepted = " + keyguardViewMediator.mPowerGestureIntercepted);
                    }
                    if (z3) {
                        Log.d("KeyguardViewMediator", "notifyStartedWakingUp");
                    }
                    keyguardViewMediator.mHandler.sendEmptyMessage(14);
                } catch (Throwable th) {
                    throw th;
                }
            }
            keyguardViewMediator.mUiEventLogger.logWithInstanceIdAndPosition(BiometricUnlockController.BiometricUiEvent.STARTED_WAKING_UP, 0, (String) null, keyguardViewMediator.mSessionTracker.getSessionId(1), i);
            KeyguardUpdateMonitor keyguardUpdateMonitor = keyguardViewMediator.mUpdateMonitor;
            synchronized (keyguardUpdateMonitor) {
                keyguardUpdateMonitor.mDeviceInteractive = true;
            }
            KeyguardUpdateMonitor.AnonymousClass13 anonymousClass13 = keyguardUpdateMonitor.mHandler;
            anonymousClass13.sendMessage(anonymousClass13.obtainMessage(319, i, 0));
            keyguardViewMediator.maybeSendUserPresentBroadcast();
            Trace.endSection();
            PowerInteractor powerInteractor = KeyguardService.this.mPowerInteractor;
            if (z) {
                powerInteractor.getClass();
            } else if (!((WakefulnessModel) ((StateFlowImpl) powerInteractor.repository.wakefulness.$$delegate_0).getValue()).powerButtonLaunchGestureTriggered) {
                z2 = false;
                WakefulnessState wakefulnessState = WakefulnessState.STARTING_TO_WAKE;
                WakeSleepReason.Companion.getClass();
                PowerRepositoryImpl.updateWakefulness$default(powerInteractor.repository, wakefulnessState, WakeSleepReason.Companion.fromPowerManagerWakeReason(i), null, z2, 4);
                Message obtainMessage = KeyguardService.this.mKeyguardLifecyclesDispatcher.mHandler.obtainMessage(4);
                obtainMessage.arg1 = i;
                obtainMessage.sendToTarget();
                Trace.endSection();
            }
            z2 = true;
            WakefulnessState wakefulnessState2 = WakefulnessState.STARTING_TO_WAKE;
            WakeSleepReason.Companion.getClass();
            PowerRepositoryImpl.updateWakefulness$default(powerInteractor.repository, wakefulnessState2, WakeSleepReason.Companion.fromPowerManagerWakeReason(i), null, z2, 4);
            Message obtainMessage2 = KeyguardService.this.mKeyguardLifecyclesDispatcher.mHandler.obtainMessage(4);
            obtainMessage2.arg1 = i;
            obtainMessage2.sendToTarget();
            Trace.endSection();
        }

        public final void onSystemKeyPressed(int i) {
            trace("onSystemKeyPressed keycode=" + i);
            KeyguardService.this.checkPermission();
            KeyguardService.this.mKeyguardViewMediator.getClass();
        }

        public final void onSystemReady() {
            trace("onSystemReady");
            Trace.beginSection("KeyguardService.mBinder#onSystemReady");
            KeyguardService.this.checkPermission();
            KeyguardService.this.mKeyguardViewMediator.mHandler.obtainMessage(18).sendToTarget();
            Trace.endSection();
        }

        public final void setCurrentUser(int i) {
            trace("Deprecated/NOT USED: setCurrentUser userId=" + i);
            KeyguardService.this.checkPermission();
        }

        public final void setKeyguardEnabled(boolean z) {
            boolean z2;
            trace("setKeyguardEnabled enabled" + z);
            KeyguardService.this.checkPermission();
            AuthContainerView$$ExternalSyntheticOutline0.m(z, KeyguardService.this.mKeyguardEnabledInteractor.repository._isKeyguardEnabled, null);
            KeyguardViewMediator keyguardViewMediator = KeyguardService.this.mKeyguardViewMediator;
            synchronized (keyguardViewMediator) {
                try {
                    z2 = KeyguardViewMediator.DEBUG;
                    if (z2) {
                        Log.d("KeyguardViewMediator", "setKeyguardEnabled(" + z + ")");
                    }
                    keyguardViewMediator.mExternallyEnabled = z;
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                } finally {
                }
                if (z || !keyguardViewMediator.mShowing) {
                    if (z && keyguardViewMediator.mNeedToReshowWhenReenabled) {
                        if (z2) {
                            Log.d("KeyguardViewMediator", "previously hidden, reshowing, reenabling status bar expansion");
                        }
                        keyguardViewMediator.mNeedToReshowWhenReenabled = false;
                        keyguardViewMediator.updateInputRestrictedLocked();
                        keyguardViewMediator.showKeyguard(null);
                        keyguardViewMediator.mWaitingUntilKeyguardVisible = true;
                        keyguardViewMediator.mHandler.sendEmptyMessageDelayed(8, 2000L);
                        if (z2) {
                            Log.d("KeyguardViewMediator", "waiting until mWaitingUntilKeyguardVisible is false");
                        }
                        while (keyguardViewMediator.mWaitingUntilKeyguardVisible) {
                            keyguardViewMediator.wait();
                        }
                        if (KeyguardViewMediator.DEBUG) {
                            Log.d("KeyguardViewMediator", "done waiting for mWaitingUntilKeyguardVisible");
                        }
                    }
                } else {
                    if (keyguardViewMediator.mLockPatternUtils.isUserInLockdown(keyguardViewMediator.mSelectedUserInteractor.getSelectedUserId())) {
                        Log.d("KeyguardViewMediator", "keyguardEnabled(false) overridden by user lockdown");
                        return;
                    }
                    if (z2) {
                        Log.d("KeyguardViewMediator", "remembering to reshow, hiding keyguard, disabling status bar expansion");
                    }
                    keyguardViewMediator.mNeedToReshowWhenReenabled = true;
                    keyguardViewMediator.updateInputRestrictedLocked();
                    Trace.beginSection("KeyguardViewMediator#hideLocked");
                    if (z2) {
                        Log.d("KeyguardViewMediator", "hideLocked");
                    }
                    KeyguardViewMediator.AnonymousClass13 anonymousClass13 = keyguardViewMediator.mHandler;
                    anonymousClass13.sendMessage(anonymousClass13.obtainMessage(2));
                    Trace.endSection();
                }
            }
        }

        public final void setOccluded(boolean z, boolean z2) {
            trace("setOccluded isOccluded=" + z + " animate=" + z2);
            StringBuilder sb = new StringBuilder("setOccluded(");
            sb.append(z);
            sb.append(")");
            Log.d("KeyguardService", sb.toString());
            Trace.beginSection("KeyguardService.mBinder#setOccluded");
            KeyguardService.this.checkPermission();
            KeyguardService.this.mKeyguardViewMediator.setOccluded(z, z2);
            Trace.endSection();
        }

        public final void setSwitchingUser(boolean z) {
            trace("setSwitchingUser switching=" + z);
            KeyguardService.this.checkPermission();
            KeyguardUpdateMonitor keyguardUpdateMonitor = KeyguardService.this.mKeyguardViewMediator.mUpdateMonitor;
            if (z) {
                keyguardUpdateMonitor.mLogger.logUserSwitching(keyguardUpdateMonitor.mSelectedUserInteractor.getSelectedUserId(), "from setSwitchingUser");
            } else {
                keyguardUpdateMonitor.mLogger.logUserSwitchComplete(keyguardUpdateMonitor.mSelectedUserInteractor.getSelectedUserId(), "from setSwitchingUser");
            }
            keyguardUpdateMonitor.mSwitchingUser = z;
            keyguardUpdateMonitor.mHandler.post(new KeyguardUpdateMonitor$$ExternalSyntheticLambda3(keyguardUpdateMonitor, 5));
        }

        public final void showDismissibleKeyguard() {
            trace("showDismissibleKeyguard");
            KeyguardService.this.checkPermission();
            KeyguardService.this.mFoldGracePeriodProvider.getClass();
            if (new FoldGracePeriodProvider().isEnabled()) {
                KeyguardRepositoryImpl keyguardRepositoryImpl = KeyguardService.this.mKeyguardInteractor.repository;
                StateFlowImpl stateFlowImpl = keyguardRepositoryImpl.showDismissibleKeyguard;
                ((SystemClockImpl) keyguardRepositoryImpl.systemClock).getClass();
                Long valueOf = Long.valueOf(SystemClock.uptimeMillis());
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, valueOf);
            }
            KeyguardViewMediator keyguardViewMediator = KeyguardService.this.mKeyguardViewMediator;
            if (!keyguardViewMediator.mFoldGracePeriodProvider.isEnabled()) {
                Log.e("KeyguardViewMediator", "fold grace period feature isn't enabled, but showKeyguard() method is being called", new Throwable());
            } else {
                if (!keyguardViewMediator.mUpdateMonitor.mDeviceProvisioned) {
                    Log.d("KeyguardViewMediator", "Device not provisioned, so ignore request to show keyguard.");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean("show_dismissible", true);
                keyguardViewMediator.showKeyguard(bundle);
            }
        }

        public final void startKeyguardExitAnimation(long j, long j2) {
            trace("startKeyguardExitAnimation startTime=" + j + " fadeoutDuration=" + j2);
            Trace.beginSection("KeyguardService.mBinder#startKeyguardExitAnimation");
            KeyguardService.this.checkPermission();
            KeyguardService.this.mKeyguardViewMediator.startKeyguardExitAnimation(j, j2, null, null, null, null);
            Trace.endSection();
        }

        public final void verifyUnlock(IKeyguardExitCallback iKeyguardExitCallback) {
            trace("verifyUnlock");
            Trace.beginSection("KeyguardService.mBinder#verifyUnlock");
            KeyguardService.this.checkPermission();
            KeyguardViewMediator keyguardViewMediator = KeyguardService.this.mKeyguardViewMediator;
            keyguardViewMediator.getClass();
            Trace.beginSection("KeyguardViewMediator#verifyUnlock");
            synchronized (keyguardViewMediator) {
                try {
                    boolean z = KeyguardViewMediator.DEBUG;
                    if (z) {
                        Log.d("KeyguardViewMediator", "verifyUnlock");
                    }
                    if (keyguardViewMediator.shouldWaitForProvisioning()) {
                        if (z) {
                            Log.d("KeyguardViewMediator", "ignoring because device isn't provisioned");
                        }
                        try {
                            iKeyguardExitCallback.onKeyguardExitResult(false);
                        } catch (RemoteException e) {
                            Slog.w("KeyguardViewMediator", "Failed to call onKeyguardExitResult(false)", e);
                        }
                    } else if (keyguardViewMediator.mExternallyEnabled) {
                        Log.w("KeyguardViewMediator", "verifyUnlock called when not externally disabled");
                        try {
                            iKeyguardExitCallback.onKeyguardExitResult(false);
                        } catch (RemoteException e2) {
                            Slog.w("KeyguardViewMediator", "Failed to call onKeyguardExitResult(false)", e2);
                        }
                    } else if (keyguardViewMediator.isSecure()) {
                        try {
                            iKeyguardExitCallback.onKeyguardExitResult(false);
                        } catch (RemoteException e3) {
                            Slog.w("KeyguardViewMediator", "Failed to call onKeyguardExitResult(false)", e3);
                        }
                    } else {
                        keyguardViewMediator.mExternallyEnabled = true;
                        keyguardViewMediator.mNeedToReshowWhenReenabled = false;
                        synchronized (keyguardViewMediator) {
                            keyguardViewMediator.updateInputRestrictedLocked();
                            try {
                                iKeyguardExitCallback.onKeyguardExitResult(true);
                            } catch (RemoteException e4) {
                                Slog.w("KeyguardViewMediator", "Failed to call onKeyguardExitResult(true)", e4);
                            }
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
                throw th;
            }
            Trace.endSection();
            Trace.endSection();
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.KeyguardService$1, reason: invalid class name */
    public final class AnonymousClass1 extends RemoteTransitionStub {
        public final /* synthetic */ KeyguardViewMediator val$keyguardViewMediator;
        public final /* synthetic */ KeyguardViewMediator.AnonymousClass8 val$runner;
        public final ArrayMap mLeashMap = new ArrayMap();
        public final CounterRotator mCounterRotator = new CounterRotator();
        public final Map mFinishCallbacks = new WeakHashMap();

        public AnonymousClass1(KeyguardViewMediator.AnonymousClass8 anonymousClass8, KeyguardViewMediator keyguardViewMediator) {
            this.val$runner = anonymousClass8;
            this.val$keyguardViewMediator = keyguardViewMediator;
        }

        public final void finish(IBinder iBinder) {
            SurfaceControl.Transaction transaction;
            IRemoteTransitionFinishedCallback iRemoteTransitionFinishedCallback;
            synchronized (this.mLeashMap) {
                try {
                    SurfaceControl surfaceControl = this.mCounterRotator.mSurface;
                    if (surfaceControl == null || !surfaceControl.isValid()) {
                        transaction = null;
                    } else {
                        transaction = new SurfaceControl.Transaction();
                        SurfaceControl surfaceControl2 = this.mCounterRotator.mSurface;
                        if (surfaceControl2 != null) {
                            transaction.remove(surfaceControl2);
                        }
                    }
                    this.mLeashMap.clear();
                    iRemoteTransitionFinishedCallback = (IRemoteTransitionFinishedCallback) this.mFinishCallbacks.remove(iBinder);
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (iRemoteTransitionFinishedCallback != null) {
                iRemoteTransitionFinishedCallback.onTransitionFinished((WindowContainerTransaction) null, transaction);
            } else if (transaction != null) {
                transaction.apply();
            }
        }

        public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, IRemoteTransitionFinishedCallback iRemoteTransitionFinishedCallback) {
            if ((transitionInfo.getFlags() & 2048) == 0) {
                try {
                    this.val$runner.onAnimationCancelled();
                    finish(iBinder2);
                    return;
                } catch (RemoteException unused) {
                    return;
                }
            }
            this.val$keyguardViewMediator.setPendingLock(true);
            KeyguardViewMediator keyguardViewMediator = this.val$keyguardViewMediator;
            keyguardViewMediator.getClass();
            Trace.beginSection("KeyguardViewMediator#cancelKeyguardExitAnimation");
            KeyguardViewMediator.AnonymousClass13 anonymousClass13 = keyguardViewMediator.mHandler;
            anonymousClass13.sendMessage(anonymousClass13.obtainMessage(19));
            Trace.endSection();
        }

        public final void startAnimation(final IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IRemoteTransitionFinishedCallback iRemoteTransitionFinishedCallback) {
            RemoteAnimationTarget[] m817$$Nest$smwrap;
            RemoteAnimationTarget[] m817$$Nest$smwrap2;
            ActivityManager.RunningTaskInfo runningTaskInfo;
            Slog.d("KeyguardService", "Starts IRemoteAnimationRunner: info=" + transitionInfo);
            int i = 0;
            RemoteAnimationTarget[] remoteAnimationTargetArr = new RemoteAnimationTarget[0];
            synchronized (this.mLeashMap) {
                m817$$Nest$smwrap = KeyguardService.m817$$Nest$smwrap(transitionInfo, false, transaction, this.mLeashMap, this.mCounterRotator);
                m817$$Nest$smwrap2 = KeyguardService.m817$$Nest$smwrap(transitionInfo, true, transaction, this.mLeashMap, this.mCounterRotator);
                this.mFinishCallbacks.put(iBinder, iRemoteTransitionFinishedCallback);
            }
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                if (TransitionInfo.isIndependent(change, transitionInfo)) {
                    transaction.setAlpha(change.getLeash(), 1.0f);
                }
            }
            for (RemoteAnimationTarget remoteAnimationTarget : m817$$Nest$smwrap) {
                if (remoteAnimationTarget.mode == 0) {
                    transaction.setAlpha(remoteAnimationTarget.leash, 0.0f);
                }
            }
            for (RemoteAnimationTarget remoteAnimationTarget2 : m817$$Nest$smwrap2) {
                if (remoteAnimationTarget2.mode == 0) {
                    transaction.setAlpha(remoteAnimationTarget2.leash, 0.0f);
                }
            }
            if ((transitionInfo.getFlags() & 256) != 0) {
                int length = m817$$Nest$smwrap.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    RemoteAnimationTarget remoteAnimationTarget3 = m817$$Nest$smwrap[i2];
                    ActivityManager.RunningTaskInfo runningTaskInfo2 = remoteAnimationTarget3.taskInfo;
                    if (runningTaskInfo2 != null && runningTaskInfo2.getActivityType() == 5 && remoteAnimationTarget3.mode == 1) {
                        transaction.hide(remoteAnimationTarget3.leash);
                        break;
                    }
                    i2++;
                }
            }
            transaction.apply();
            KeyguardViewMediator.AnonymousClass8 anonymousClass8 = this.val$runner;
            int type = transitionInfo.getType();
            int flags = transitionInfo.getFlags();
            if (type == 7 || (flags & 256) != 0) {
                i = m817$$Nest$smwrap.length == 0 ? 21 : 20;
            } else if (type == 8) {
                i = (m817$$Nest$smwrap.length <= 0 || (runningTaskInfo = m817$$Nest$smwrap[0].taskInfo) == null || runningTaskInfo.topActivityType != 5) ? 22 : 33;
            } else if (type == 9) {
                i = 23;
            } else {
                Slog.d("KeyguardService", "Unexpected transit type: " + type);
            }
            anonymousClass8.onAnimationStart(i, m817$$Nest$smwrap, m817$$Nest$smwrap2, remoteAnimationTargetArr, new IRemoteAnimationFinishedCallback.Stub() { // from class: com.android.systemui.keyguard.KeyguardService.1.1
                public final void onAnimationFinished() {
                    Slog.d("KeyguardService", "Finish IRemoteAnimationRunner.");
                    AnonymousClass1.this.finish(iBinder);
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.KeyguardService$2, reason: invalid class name */
    public final class AnonymousClass2 implements Lazy {
        @Override // dagger.Lazy
        public final Object get() {
            return new FoldGracePeriodProvider();
        }
    }

    /* renamed from: -$$Nest$smwrap, reason: not valid java name */
    public static RemoteAnimationTarget[] m817$$Nest$smwrap(TransitionInfo transitionInfo, boolean z, SurfaceControl.Transaction transaction, ArrayMap arrayMap, CounterRotator counterRotator) {
        RemoteAnimationTarget remoteAnimationTarget;
        int deltaRotation;
        TransitionInfo.Change change;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
            boolean z2 = (((TransitionInfo.Change) transitionInfo.getChanges().get(i)).getFlags() & 2) != 0;
            if (z == z2) {
                TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
                int i2 = change2.getTaskInfo() != null ? change2.getTaskInfo().taskId : -1;
                if ((i2 == -1 || change2.getParent() == null || (change = transitionInfo.getChange(change2.getParent())) == null || change.getTaskInfo() == null) && (i2 >= 0 || z)) {
                    RemoteAnimationTarget newTarget = TransitionUtil.newTarget(change2, RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, i), (change2.getFlags() & 1) != 0, transitionInfo, transaction, arrayMap);
                    if (!z2 || (deltaRotation = RotationUtils.deltaRotation(change2.getStartRotation(), change2.getEndRotation())) == 0 || change2.getParent() == null || change2.getMode() != 4) {
                        remoteAnimationTarget = newTarget;
                    } else {
                        TransitionInfo.Change change3 = transitionInfo.getChange(change2.getParent());
                        if (change3 != null) {
                            remoteAnimationTarget = newTarget;
                            counterRotator.setup(transaction, change3.getLeash(), deltaRotation, change3.getEndAbsBounds().width(), change3.getEndAbsBounds().height());
                        } else {
                            remoteAnimationTarget = newTarget;
                        }
                        SurfaceControl surfaceControl = counterRotator.mSurface;
                        if (surfaceControl != null) {
                            transaction.setLayer(surfaceControl, -1);
                            SurfaceControl surfaceControl2 = (SurfaceControl) arrayMap.get(change2.getLeash());
                            SurfaceControl surfaceControl3 = counterRotator.mSurface;
                            if (surfaceControl3 != null) {
                                transaction.reparent(surfaceControl2, surfaceControl3);
                            }
                        }
                    }
                    arrayList.add(remoteAnimationTarget);
                }
            }
        }
        return (RemoteAnimationTarget[]) arrayList.toArray(new RemoteAnimationTarget[arrayList.size()]);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.keyguard.KeyguardService$3] */
    public KeyguardService(KeyguardViewMediator keyguardViewMediator, KeyguardLifecyclesDispatcher keyguardLifecyclesDispatcher, ScreenOnCoordinator screenOnCoordinator, ShellTransitions shellTransitions, DisplayTracker displayTracker, WindowManagerLockscreenVisibilityViewModel windowManagerLockscreenVisibilityViewModel, WindowManagerLockscreenVisibilityManager windowManagerLockscreenVisibilityManager, KeyguardSurfaceBehindViewModel keyguardSurfaceBehindViewModel, KeyguardSurfaceBehindParamsApplier keyguardSurfaceBehindParamsApplier, CoroutineScope coroutineScope, FeatureFlags featureFlags, PowerInteractor powerInteractor, WindowManagerOcclusionManager windowManagerOcclusionManager, Lazy lazy, Executor executor, KeyguardInteractor keyguardInteractor, KeyguardEnabledInteractor keyguardEnabledInteractor, Lazy lazy2, KeyguardWakeDirectlyToGoneInteractor keyguardWakeDirectlyToGoneInteractor, KeyguardDismissInteractor keyguardDismissInteractor, Lazy lazy3, KeyguardStateCallbackInteractor keyguardStateCallbackInteractor) {
        this.mKeyguardViewMediator = keyguardViewMediator;
        this.mKeyguardLifecyclesDispatcher = keyguardLifecyclesDispatcher;
        this.mScreenOnCoordinator = screenOnCoordinator;
        this.mShellTransitions = shellTransitions;
        this.mDisplayTracker = displayTracker;
        this.mPowerInteractor = powerInteractor;
        this.mKeyguardInteractor = keyguardInteractor;
        this.mKeyguardEnabledInteractor = keyguardEnabledInteractor;
        this.mKeyguardWakeDirectlyToGoneInteractor = keyguardWakeDirectlyToGoneInteractor;
    }

    public final void checkPermission() {
        if (Binder.getCallingUid() == 1000 || getBaseContext().checkCallingOrSelfPermission("android.permission.CONTROL_KEYGUARD") == 0) {
            return;
        }
        Log.w("KeyguardService", "Caller needs permission 'android.permission.CONTROL_KEYGUARD' to call " + Debug.getCaller());
        throw new SecurityException("Access denied to process: " + Binder.getCallingPid() + ", must have permission android.permission.CONTROL_KEYGUARD");
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public final void onCreate() {
        ((SystemUIApplication) getApplication()).startSystemUserServicesIfNeeded();
        if (this.mShellTransitions == null || !Transitions.ENABLE_SHELL_TRANSITIONS) {
            RemoteAnimationDefinition remoteAnimationDefinition = new RemoteAnimationDefinition();
            KeyguardViewMediator keyguardViewMediator = this.mKeyguardViewMediator;
            keyguardViewMediator.getClass();
            RemoteAnimationAdapter remoteAnimationAdapter = new RemoteAnimationAdapter(keyguardViewMediator.new AnonymousClass8(keyguardViewMediator.mExitAnimationRunner), 0L, 0L);
            remoteAnimationDefinition.addRemoteAnimation(20, remoteAnimationAdapter);
            remoteAnimationDefinition.addRemoteAnimation(21, remoteAnimationAdapter);
            KeyguardViewMediator keyguardViewMediator2 = this.mKeyguardViewMediator;
            keyguardViewMediator2.getClass();
            remoteAnimationDefinition.addRemoteAnimation(22, new RemoteAnimationAdapter(keyguardViewMediator2.new AnonymousClass8(keyguardViewMediator2.mOccludeAnimationRunner), 0L, 0L));
            KeyguardViewMediator keyguardViewMediator3 = this.mKeyguardViewMediator;
            keyguardViewMediator3.getClass();
            remoteAnimationDefinition.addRemoteAnimation(33, new RemoteAnimationAdapter(keyguardViewMediator3.new AnonymousClass8(keyguardViewMediator3.mOccludeByDreamAnimationRunner), 0L, 0L));
            KeyguardViewMediator keyguardViewMediator4 = this.mKeyguardViewMediator;
            keyguardViewMediator4.getClass();
            remoteAnimationDefinition.addRemoteAnimation(23, new RemoteAnimationAdapter(keyguardViewMediator4.new AnonymousClass8(keyguardViewMediator4.mUnoccludeAnimationRunner), 0L, 0L));
            ActivityTaskManager activityTaskManager = ActivityTaskManager.getInstance();
            this.mDisplayTracker.getClass();
            activityTaskManager.registerRemoteAnimationsForDisplay(0, remoteAnimationDefinition);
        }
    }
}

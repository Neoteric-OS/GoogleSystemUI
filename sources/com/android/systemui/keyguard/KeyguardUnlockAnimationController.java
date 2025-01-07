package com.android.systemui.keyguard;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.Trace;
import android.util.Log;
import android.view.KeyEvent;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import com.android.app.animation.Interpolators;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.plugins.BcSmartspaceDataPlugin;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shared.recents.utilities.Utilities;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.smartspace.ILauncherUnlockAnimationController$Stub$Proxy;
import com.android.systemui.shared.system.smartspace.ISysuiUnlockAnimationController;
import com.android.systemui.shared.system.smartspace.SmartspaceState;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.phone.BiometricUnlockController;
import com.android.systemui.statusbar.phone.StatusBarKeyguardViewManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardUnlockAnimationController extends ISysuiUnlockAnimationController.Stub implements KeyguardStateController.Callback {
    public final Lazy biometricUnlockControllerLazy;
    public RemoteAnimationTarget[] closingWallpaperTargets;
    public boolean dismissAmountThresholdsReached;
    public final FeatureFlags featureFlags;
    public final Handler handler;
    public final KeyguardStateController keyguardStateController;
    public final StatusBarKeyguardViewManager keyguardViewController;
    public final Lazy keyguardViewMediator;
    public String launcherActivityClass;
    public boolean launcherPreparedForUnlock;
    public SmartspaceState launcherSmartspaceState;
    public ILauncherUnlockAnimationController$Stub$Proxy launcherUnlockController;
    public View lockscreenSmartspace;
    public final NotificationShadeWindowController notificationShadeWindowController;
    public RemoteAnimationTarget[] openingWallpaperTargets;
    public boolean playingCannedUnlockAnimation;
    public final PowerManager powerManager;
    public final Resources resources;
    public final float roundedCornerRadius;
    public final SysuiStatusBarStateController statusBarStateController;
    public final ValueAnimator surfaceBehindAlphaAnimator;
    public final ValueAnimator surfaceBehindEntryAnimator;
    public final Matrix surfaceBehindMatrix;
    public long surfaceBehindRemoteAnimationStartTime;
    public RemoteAnimationTarget[] surfaceBehindRemoteAnimationTargets;
    public SyncRtSurfaceTransactionApplier surfaceTransactionApplier;
    public final float[] tmpFloat;
    public final ValueAnimator wallpaperCannedUnlockAnimator;
    public final ValueAnimator wallpaperFadeOutUnlockAnimator;
    public boolean willUnlockWithInWindowLauncherAnimations;
    public boolean willUnlockWithSmartspaceTransition;
    public final WindowManager windowManager;
    public final ArrayList listeners = new ArrayList();
    public float surfaceBehindAlpha = 1.0f;

    public KeyguardUnlockAnimationController(WindowManager windowManager, Resources resources, KeyguardStateController keyguardStateController, Lazy lazy, StatusBarKeyguardViewManager statusBarKeyguardViewManager, FeatureFlags featureFlags, Lazy lazy2, SysuiStatusBarStateController sysuiStatusBarStateController, NotificationShadeWindowController notificationShadeWindowController, PowerManager powerManager) {
        final int i = 1;
        this.windowManager = windowManager;
        this.resources = resources;
        this.keyguardStateController = keyguardStateController;
        this.keyguardViewMediator = lazy;
        this.keyguardViewController = statusBarKeyguardViewManager;
        this.featureFlags = featureFlags;
        this.biometricUnlockControllerLazy = lazy2;
        this.statusBarStateController = sysuiStatusBarStateController;
        this.notificationShadeWindowController = notificationShadeWindowController;
        this.powerManager = powerManager;
        final int i2 = 2;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.surfaceBehindAlphaAnimator = ofFloat;
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.wallpaperCannedUnlockAnimator = ofFloat2;
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(1.0f, 0.0f);
        this.wallpaperFadeOutUnlockAnimator = ofFloat3;
        this.surfaceBehindMatrix = new Matrix();
        ValueAnimator ofFloat4 = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.surfaceBehindEntryAnimator = ofFloat4;
        this.handler = new Handler();
        this.tmpFloat = new float[9];
        ofFloat.setDuration(175L);
        Interpolator interpolator = Interpolators.LINEAR;
        ofFloat.setInterpolator(interpolator);
        final int i3 = 0;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.systemui.keyguard.KeyguardUnlockAnimationController$1$1
            public final /* synthetic */ KeyguardUnlockAnimationController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                switch (i3) {
                    case 0:
                        this.this$0.surfaceBehindAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        this.this$0.updateSurfaceBehindAppearAmount();
                        break;
                    case 1:
                        this.this$0.setWallpaperAppearAmount(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.this$0.openingWallpaperTargets);
                        break;
                    case 2:
                        this.this$0.setWallpaperAppearAmount(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.this$0.closingWallpaperTargets);
                        break;
                    default:
                        this.this$0.surfaceBehindAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        this.this$0.setSurfaceBehindAppearAmount(((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
                        break;
                }
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter(this) { // from class: com.android.systemui.keyguard.KeyguardUnlockAnimationController$1$2
            public final /* synthetic */ KeyguardUnlockAnimationController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i3) {
                    case 0:
                        float f = this.this$0.surfaceBehindAlpha;
                        if (f != 0.0f) {
                            Log.d("KeyguardUnlock", "skip finishSurfaceBehindRemoteAnimation surfaceBehindAlpha=" + f);
                            break;
                        } else {
                            Log.d("KeyguardUnlock", "surfaceBehindAlphaAnimator#onAnimationEnd");
                            KeyguardUnlockAnimationController keyguardUnlockAnimationController = this.this$0;
                            keyguardUnlockAnimationController.surfaceBehindRemoteAnimationTargets = null;
                            keyguardUnlockAnimationController.openingWallpaperTargets = null;
                            keyguardUnlockAnimationController.closingWallpaperTargets = null;
                            ((KeyguardViewMediator) keyguardUnlockAnimationController.keyguardViewMediator.get()).finishSurfaceBehindRemoteAnimation(false);
                            break;
                        }
                    case 1:
                        Log.d("KeyguardUnlock", "wallpaperCannedUnlockAnimator#onAnimationEnd");
                        ((KeyguardViewMediator) this.this$0.keyguardViewMediator.get()).exitKeyguardAndFinishSurfaceBehindRemoteAnimation();
                        Trace.asyncTraceEnd(4096L, "WallpaperAlphaAnimation", 0);
                        break;
                    default:
                        Log.d("KeyguardUnlock", "surfaceBehindEntryAnimator#onAnimationEnd");
                        KeyguardUnlockAnimationController keyguardUnlockAnimationController2 = this.this$0;
                        keyguardUnlockAnimationController2.playingCannedUnlockAnimation = false;
                        ((KeyguardViewMediator) keyguardUnlockAnimationController2.keyguardViewMediator.get()).exitKeyguardAndFinishSurfaceBehindRemoteAnimation();
                        break;
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                switch (i3) {
                    case 1:
                        super.onAnimationStart(animator);
                        Trace.asyncTraceBegin(4096L, "WallpaperAlphaAnimation", 0);
                        break;
                    default:
                        super.onAnimationStart(animator);
                        break;
                }
            }
        });
        ofFloat2.setDuration(300L);
        ofFloat2.setInterpolator(interpolator);
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.systemui.keyguard.KeyguardUnlockAnimationController$1$1
            public final /* synthetic */ KeyguardUnlockAnimationController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                switch (i) {
                    case 0:
                        this.this$0.surfaceBehindAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        this.this$0.updateSurfaceBehindAppearAmount();
                        break;
                    case 1:
                        this.this$0.setWallpaperAppearAmount(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.this$0.openingWallpaperTargets);
                        break;
                    case 2:
                        this.this$0.setWallpaperAppearAmount(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.this$0.closingWallpaperTargets);
                        break;
                    default:
                        this.this$0.surfaceBehindAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        this.this$0.setSurfaceBehindAppearAmount(((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
                        break;
                }
            }
        });
        ofFloat2.addListener(new AnimatorListenerAdapter(this) { // from class: com.android.systemui.keyguard.KeyguardUnlockAnimationController$1$2
            public final /* synthetic */ KeyguardUnlockAnimationController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i) {
                    case 0:
                        float f = this.this$0.surfaceBehindAlpha;
                        if (f != 0.0f) {
                            Log.d("KeyguardUnlock", "skip finishSurfaceBehindRemoteAnimation surfaceBehindAlpha=" + f);
                            break;
                        } else {
                            Log.d("KeyguardUnlock", "surfaceBehindAlphaAnimator#onAnimationEnd");
                            KeyguardUnlockAnimationController keyguardUnlockAnimationController = this.this$0;
                            keyguardUnlockAnimationController.surfaceBehindRemoteAnimationTargets = null;
                            keyguardUnlockAnimationController.openingWallpaperTargets = null;
                            keyguardUnlockAnimationController.closingWallpaperTargets = null;
                            ((KeyguardViewMediator) keyguardUnlockAnimationController.keyguardViewMediator.get()).finishSurfaceBehindRemoteAnimation(false);
                            break;
                        }
                    case 1:
                        Log.d("KeyguardUnlock", "wallpaperCannedUnlockAnimator#onAnimationEnd");
                        ((KeyguardViewMediator) this.this$0.keyguardViewMediator.get()).exitKeyguardAndFinishSurfaceBehindRemoteAnimation();
                        Trace.asyncTraceEnd(4096L, "WallpaperAlphaAnimation", 0);
                        break;
                    default:
                        Log.d("KeyguardUnlock", "surfaceBehindEntryAnimator#onAnimationEnd");
                        KeyguardUnlockAnimationController keyguardUnlockAnimationController2 = this.this$0;
                        keyguardUnlockAnimationController2.playingCannedUnlockAnimation = false;
                        ((KeyguardViewMediator) keyguardUnlockAnimationController2.keyguardViewMediator.get()).exitKeyguardAndFinishSurfaceBehindRemoteAnimation();
                        break;
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                switch (i) {
                    case 1:
                        super.onAnimationStart(animator);
                        Trace.asyncTraceBegin(4096L, "WallpaperAlphaAnimation", 0);
                        break;
                    default:
                        super.onAnimationStart(animator);
                        break;
                }
            }
        });
        ofFloat3.setDuration(150L);
        ofFloat3.setStartDelay(150L);
        ofFloat3.setInterpolator(interpolator);
        ofFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.systemui.keyguard.KeyguardUnlockAnimationController$1$1
            public final /* synthetic */ KeyguardUnlockAnimationController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                switch (i2) {
                    case 0:
                        this.this$0.surfaceBehindAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        this.this$0.updateSurfaceBehindAppearAmount();
                        break;
                    case 1:
                        this.this$0.setWallpaperAppearAmount(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.this$0.openingWallpaperTargets);
                        break;
                    case 2:
                        this.this$0.setWallpaperAppearAmount(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.this$0.closingWallpaperTargets);
                        break;
                    default:
                        this.this$0.surfaceBehindAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        this.this$0.setSurfaceBehindAppearAmount(((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
                        break;
                }
            }
        });
        ofFloat4.setDuration(300L);
        ofFloat4.setStartDelay(67L);
        ofFloat4.setInterpolator(Interpolators.TOUCH_RESPONSE);
        final int i4 = 3;
        ofFloat4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.android.systemui.keyguard.KeyguardUnlockAnimationController$1$1
            public final /* synthetic */ KeyguardUnlockAnimationController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                switch (i4) {
                    case 0:
                        this.this$0.surfaceBehindAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        this.this$0.updateSurfaceBehindAppearAmount();
                        break;
                    case 1:
                        this.this$0.setWallpaperAppearAmount(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.this$0.openingWallpaperTargets);
                        break;
                    case 2:
                        this.this$0.setWallpaperAppearAmount(((Float) valueAnimator.getAnimatedValue()).floatValue(), this.this$0.closingWallpaperTargets);
                        break;
                    default:
                        this.this$0.surfaceBehindAlpha = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        this.this$0.setSurfaceBehindAppearAmount(((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
                        break;
                }
            }
        });
        ofFloat4.addListener(new AnimatorListenerAdapter(this) { // from class: com.android.systemui.keyguard.KeyguardUnlockAnimationController$1$2
            public final /* synthetic */ KeyguardUnlockAnimationController this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i2) {
                    case 0:
                        float f = this.this$0.surfaceBehindAlpha;
                        if (f != 0.0f) {
                            Log.d("KeyguardUnlock", "skip finishSurfaceBehindRemoteAnimation surfaceBehindAlpha=" + f);
                            break;
                        } else {
                            Log.d("KeyguardUnlock", "surfaceBehindAlphaAnimator#onAnimationEnd");
                            KeyguardUnlockAnimationController keyguardUnlockAnimationController = this.this$0;
                            keyguardUnlockAnimationController.surfaceBehindRemoteAnimationTargets = null;
                            keyguardUnlockAnimationController.openingWallpaperTargets = null;
                            keyguardUnlockAnimationController.closingWallpaperTargets = null;
                            ((KeyguardViewMediator) keyguardUnlockAnimationController.keyguardViewMediator.get()).finishSurfaceBehindRemoteAnimation(false);
                            break;
                        }
                    case 1:
                        Log.d("KeyguardUnlock", "wallpaperCannedUnlockAnimator#onAnimationEnd");
                        ((KeyguardViewMediator) this.this$0.keyguardViewMediator.get()).exitKeyguardAndFinishSurfaceBehindRemoteAnimation();
                        Trace.asyncTraceEnd(4096L, "WallpaperAlphaAnimation", 0);
                        break;
                    default:
                        Log.d("KeyguardUnlock", "surfaceBehindEntryAnimator#onAnimationEnd");
                        KeyguardUnlockAnimationController keyguardUnlockAnimationController2 = this.this$0;
                        keyguardUnlockAnimationController2.playingCannedUnlockAnimation = false;
                        ((KeyguardViewMediator) keyguardUnlockAnimationController2.keyguardViewMediator.get()).exitKeyguardAndFinishSurfaceBehindRemoteAnimation();
                        break;
                }
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                switch (i2) {
                    case 1:
                        super.onAnimationStart(animator);
                        Trace.asyncTraceBegin(4096L, "WallpaperAlphaAnimation", 0);
                        break;
                    default:
                        super.onAnimationStart(animator);
                        break;
                }
            }
        });
        ((KeyguardStateControllerImpl) keyguardStateController).addCallback(this);
        this.roundedCornerRadius = resources.getDimensionPixelSize(R.dimen.search_view_preferred_height);
    }

    public final void finishKeyguardExitRemoteAnimationIfReachThreshold() {
        if (((KeyguardStateControllerImpl) this.keyguardStateController).mShowing && !this.dismissAmountThresholdsReached && ((KeyguardViewMediator) this.keyguardViewMediator.get()).mSurfaceBehindRemoteAnimationRequested && ((KeyguardViewMediator) this.keyguardViewMediator.get()).isAnimatingBetweenKeyguardAndSurfaceBehindOrWillBe()) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
            float f = keyguardStateControllerImpl.mDismissAmount;
            if (f >= 1.0f || (keyguardStateControllerImpl.mDismissingFromTouch && !keyguardStateControllerImpl.mFlingingToDismissKeyguardDuringSwipeGesture && f >= 0.3f)) {
                setSurfaceBehindAppearAmount(1.0f, true);
                this.dismissAmountThresholdsReached = true;
                ((KeyguardViewMediator) this.keyguardViewMediator.get()).exitKeyguardAndFinishSurfaceBehindRemoteAnimation();
            }
        }
    }

    public final boolean isSupportedLauncherUnderneath() {
        ComponentName componentName;
        String className;
        String str = this.launcherActivityClass;
        if (str != null) {
            ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.sInstance.getRunningTask();
            Boolean valueOf = (runningTask == null || (componentName = runningTask.topActivity) == null || (className = componentName.getClassName()) == null) ? null : Boolean.valueOf(className.equals(str));
            if (valueOf != null) {
                return valueOf.booleanValue();
            }
        }
        return false;
    }

    public final void notifyFinishedKeyguardExitAnimation(boolean z) {
        View view;
        this.handler.removeCallbacksAndMessages(null);
        View view2 = this.lockscreenSmartspace;
        if (view2 != null && view2.getVisibility() == 4 && (view = this.lockscreenSmartspace) != null) {
            view.setVisibility(0);
        }
        if (!z) {
            this.surfaceBehindAlpha = 1.0f;
            setSurfaceBehindAppearAmount(1.0f, true);
            try {
                ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy = this.launcherUnlockController;
                if (iLauncherUnlockAnimationController$Stub$Proxy != null) {
                    iLauncherUnlockAnimationController$Stub$Proxy.setUnlockAmount(false);
                }
            } catch (RemoteException e) {
                Log.e("KeyguardUnlock", "Remote exception in notifyFinishedKeyguardExitAnimation", e);
            }
        }
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((KeyguardUnlockAnimationListener) it.next()).onUnlockAnimationFinished();
        }
        this.surfaceBehindAlphaAnimator.cancel();
        this.surfaceBehindEntryAnimator.cancel();
        this.wallpaperCannedUnlockAnimator.cancel();
        this.wallpaperFadeOutUnlockAnimator.cancel();
        this.surfaceBehindRemoteAnimationTargets = null;
        this.openingWallpaperTargets = null;
        this.closingWallpaperTargets = null;
        this.playingCannedUnlockAnimation = false;
        this.dismissAmountThresholdsReached = false;
        this.willUnlockWithInWindowLauncherAnimations = false;
        this.willUnlockWithSmartspaceTransition = false;
    }

    public final void notifyStartSurfaceBehindRemoteAnimation(RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, long j, boolean z) {
        if (this.surfaceTransactionApplier == null) {
            this.surfaceTransactionApplier = new SyncRtSurfaceTransactionApplier(this.keyguardViewController.getViewRootImpl().getView());
        }
        this.surfaceBehindRemoteAnimationTargets = remoteAnimationTargetArr;
        this.openingWallpaperTargets = remoteAnimationTargetArr2;
        this.closingWallpaperTargets = remoteAnimationTargetArr3;
        this.surfaceBehindRemoteAnimationStartTime = j;
        if (z) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
            if (keyguardStateControllerImpl.mFlingingToDismissKeyguard) {
                playCannedUnlockAnimation();
            } else if (keyguardStateControllerImpl.mDismissingFromTouch && this.willUnlockWithInWindowLauncherAnimations) {
                this.surfaceBehindAlpha = 1.0f;
                setSurfaceBehindAppearAmount(1.0f, true);
                try {
                    ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy = this.launcherUnlockController;
                    if (iLauncherUnlockAnimationController$Stub$Proxy != null) {
                        iLauncherUnlockAnimationController$Stub$Proxy.playUnlockAnimation(325L, 0L);
                    }
                } catch (DeadObjectException unused) {
                    Log.e("KeyguardUnlock", "launcherUnlockAnimationController was dead, but non-null. Catching exception as this should mean Launcher is in the process of being destroyed, but the IPC to System UI telling us hasn't arrived yet.");
                }
                this.launcherPreparedForUnlock = false;
            } else {
                Log.d("KeyguardUnlock", "fadeInSurfaceBehind");
                this.surfaceBehindAlphaAnimator.cancel();
                this.surfaceBehindAlphaAnimator.start();
            }
        } else {
            playCannedUnlockAnimation();
        }
        boolean z2 = ((BiometricUnlockController) this.biometricUnlockControllerLazy.get()).isWakeAndUnlock() && ((BiometricUnlockController) this.biometricUnlockControllerLazy.get()).mMode != 6;
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((KeyguardUnlockAnimationListener) it.next()).onUnlockAnimationStarted(this.playingCannedUnlockAnimation, z2);
        }
        if (this.playingCannedUnlockAnimation) {
            return;
        }
        finishKeyguardExitRemoteAnimationIfReachThreshold();
    }

    @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
    public final void onKeyguardDismissAmountChanged() {
        if (!((KeyguardStateControllerImpl) this.keyguardStateController).mShowing || this.playingCannedUnlockAnimation) {
            return;
        }
        if (((FeatureFlagsClassicRelease) this.featureFlags).isEnabled(Flags.NEW_UNLOCK_SWIPE_ANIMATION) && !this.playingCannedUnlockAnimation && !this.dismissAmountThresholdsReached) {
            KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
            if (keyguardStateControllerImpl.mShowing) {
                float f = keyguardStateControllerImpl.mDismissAmount;
                if (f >= 0.15f && !((KeyguardViewMediator) this.keyguardViewMediator.get()).mSurfaceBehindRemoteAnimationRequested) {
                    ((KeyguardViewMediator) this.keyguardViewMediator.get()).showSurfaceBehindKeyguard();
                } else if (f < 0.15f && ((KeyguardViewMediator) this.keyguardViewMediator.get()).mSurfaceBehindRemoteAnimationRequested) {
                    KeyguardViewMediator keyguardViewMediator = (KeyguardViewMediator) this.keyguardViewMediator.get();
                    keyguardViewMediator.mSurfaceBehindRemoteAnimationRequested = false;
                    ((KeyguardStateControllerImpl) keyguardViewMediator.mKeyguardStateController).notifyKeyguardGoingAway(false);
                    if (keyguardViewMediator.mShowing) {
                        keyguardViewMediator.setShowingLocked("hideSurfaceBehindKeyguard", true, true);
                    }
                    Log.d("KeyguardUnlock", "fadeOutSurfaceBehind");
                    this.surfaceBehindAlphaAnimator.cancel();
                    this.surfaceBehindAlphaAnimator.reverse();
                }
                finishKeyguardExitRemoteAnimationIfReachThreshold();
            }
        }
        if ((((KeyguardViewMediator) this.keyguardViewMediator.get()).mSurfaceBehindRemoteAnimationRequested || ((KeyguardViewMediator) this.keyguardViewMediator.get()).isAnimatingBetweenKeyguardAndSurfaceBehindOrWillBe()) && !this.playingCannedUnlockAnimation) {
            updateSurfaceBehindAppearAmount();
        }
    }

    @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
    public final void onKeyguardGoingAwayChanged() {
        if (((KeyguardStateControllerImpl) this.keyguardStateController).mKeyguardGoingAway && !((StatusBarStateControllerImpl) this.statusBarStateController).mLeaveOpenOnKeyguardHide) {
            boolean z = (!isSupportedLauncherUnderneath() || ((NotificationShadeWindowControllerImpl) this.notificationShadeWindowController).mCurrentState.launchingActivityFromNotification || this.launcherUnlockController == null) ? false : true;
            this.willUnlockWithInWindowLauncherAnimations = z;
            if (z) {
                this.willUnlockWithSmartspaceTransition = shouldPerformSmartspaceTransition();
                Rect rect = new Rect();
                if (this.willUnlockWithSmartspaceTransition) {
                    rect = new Rect();
                    View view = this.lockscreenSmartspace;
                    Intrinsics.checkNotNull(view);
                    view.getBoundsOnScreen(rect);
                    View view2 = this.lockscreenSmartspace;
                    Intrinsics.checkNotNull(view2);
                    int paddingLeft = view2.getPaddingLeft();
                    View view3 = this.lockscreenSmartspace;
                    Intrinsics.checkNotNull(view3);
                    rect.offset(paddingLeft, view3.getPaddingTop());
                    KeyEvent.Callback callback = this.lockscreenSmartspace;
                    BcSmartspaceDataPlugin.SmartspaceView smartspaceView = callback instanceof BcSmartspaceDataPlugin.SmartspaceView ? (BcSmartspaceDataPlugin.SmartspaceView) callback : null;
                    rect.offset(0, smartspaceView != null ? smartspaceView.getCurrentCardTopPadding() : 0);
                }
                BcSmartspaceDataPlugin.SmartspaceView smartspaceView2 = (BcSmartspaceDataPlugin.SmartspaceView) this.lockscreenSmartspace;
                int selectedPage = smartspaceView2 != null ? smartspaceView2.getSelectedPage() : -1;
                try {
                    ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy = this.launcherUnlockController;
                    if (iLauncherUnlockAnimationController$Stub$Proxy != null) {
                        iLauncherUnlockAnimationController$Stub$Proxy.prepareForUnlock(this.willUnlockWithSmartspaceTransition, selectedPage, rect);
                    }
                    this.launcherPreparedForUnlock = true;
                } catch (RemoteException e) {
                    Log.e("KeyguardUnlock", "Remote exception in prepareForInWindowUnlockAnimations.", e);
                }
            }
        }
        if (((KeyguardStateControllerImpl) this.keyguardStateController).mKeyguardGoingAway || !this.willUnlockWithInWindowLauncherAnimations) {
            return;
        }
        try {
            ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy2 = this.launcherUnlockController;
            if (iLauncherUnlockAnimationController$Stub$Proxy2 != null) {
                iLauncherUnlockAnimationController$Stub$Proxy2.setUnlockAmount(((BiometricUnlockController) this.biometricUnlockControllerLazy.get()).isWakeAndUnlock());
            }
        } catch (DeadObjectException unused) {
            Log.e("KeyguardUnlock", "launcherUnlockAnimationController was dead, but non-null in onKeyguardGoingAwayChanged(). Catching exception as this should mean Launcher is in the process of being destroyed, but the IPC to System UI telling us hasn't arrived yet.");
        }
    }

    @Override // com.android.systemui.shared.system.smartspace.ISysuiUnlockAnimationController
    public final void onLauncherSmartspaceStateUpdated(SmartspaceState smartspaceState) {
        this.launcherSmartspaceState = smartspaceState;
    }

    public final void playCannedUnlockAnimation() {
        Log.d("KeyguardUnlock", "playCannedUnlockAnimation");
        this.playingCannedUnlockAnimation = true;
        if (this.willUnlockWithInWindowLauncherAnimations) {
            Log.d("KeyguardUnlock", "playCannedUnlockAnimation, unlockToLauncherWithInWindowAnimations");
            unlockToLauncherWithInWindowAnimations();
        } else if (((BiometricUnlockController) this.biometricUnlockControllerLazy.get()).isWakeAndUnlock()) {
            Log.d("KeyguardUnlock", "playCannedUnlockAnimation, isWakeAndUnlock");
            setSurfaceBehindAppearAmount(1.0f, true);
            ((KeyguardViewMediator) this.keyguardViewMediator.get()).exitKeyguardAndFinishSurfaceBehindRemoteAnimation();
        } else {
            Log.d("KeyguardUnlock", "playCannedUnlockAnimation, surfaceBehindEntryAnimator#start");
            this.surfaceBehindEntryAnimator.start();
        }
        if (!this.launcherPreparedForUnlock || this.willUnlockWithInWindowLauncherAnimations) {
            return;
        }
        Log.wtf("KeyguardUnlock", "Launcher is prepared for unlock, so we should have started the in-window animation, however we apparently did not.");
        Log.wtf("KeyguardUnlock", "canPerformInWindowLauncherAnimations expected all of these to be true: ");
        Log.wtf("KeyguardUnlock", "  isNexusLauncherUnderneath: " + isSupportedLauncherUnderneath());
        Log.wtf("KeyguardUnlock", "  !notificationShadeWindowController.isLaunchingActivity: " + (((NotificationShadeWindowControllerImpl) this.notificationShadeWindowController).mCurrentState.launchingActivityFromNotification ^ true));
        Log.wtf("KeyguardUnlock", "  launcherUnlockController != null: " + (this.launcherUnlockController != null));
        Log.wtf("KeyguardUnlock", "  !isFoldable(context): " + (this.resources.getIntArray(R.array.config_fontManagerServiceCerts).length == 0));
    }

    @Override // com.android.systemui.shared.system.smartspace.ISysuiUnlockAnimationController
    public final void setLauncherUnlockController(String str, ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy) {
        this.launcherActivityClass = str;
        this.launcherUnlockController = iLauncherUnlockAnimationController$Stub$Proxy;
    }

    public final void setSurfaceBehindAppearAmount(float f, boolean z) {
        float f2 = 0.0f;
        float f3 = ((KeyguardStateControllerImpl) this.keyguardStateController).mSnappingKeyguardBackAfterSwipe ? f : !this.powerManager.isInteractive() ? 0.0f : this.surfaceBehindAlpha;
        RemoteAnimationTarget[] remoteAnimationTargetArr = this.surfaceBehindRemoteAnimationTargets;
        if (remoteAnimationTargetArr != null) {
            int length = remoteAnimationTargetArr.length;
            int i = 0;
            while (i < length) {
                RemoteAnimationTarget remoteAnimationTarget = remoteAnimationTargetArr[i];
                int height = remoteAnimationTarget.screenSpaceBounds.height();
                float f4 = ((f < f2 ? f2 : f > 1.0f ? 1.0f : f) * 0.050000012f) + 0.95f;
                if (((KeyguardStateControllerImpl) this.keyguardStateController).mDismissingFromTouch && this.willUnlockWithInWindowLauncherAnimations) {
                    f4 = 1.0f;
                }
                Matrix matrix = this.surfaceBehindMatrix;
                Rect rect = remoteAnimationTarget.screenSpaceBounds;
                float f5 = height;
                matrix.setTranslate(rect.left, AndroidFlingSpline$$ExternalSyntheticOutline0.m(1.0f, f, f5 * 0.05f, rect.top));
                this.surfaceBehindMatrix.postScale(f4, f4, this.keyguardViewController.getViewRootImpl().getWidth() / 2.0f, f5 * 0.66f);
                SurfaceControl surfaceControl = remoteAnimationTarget.leash;
                View view = this.keyguardViewController.getViewRootImpl().getView();
                if ((view == null || view.getVisibility() != 0) && surfaceControl != null && surfaceControl.isValid()) {
                    SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
                    transaction.setMatrix(surfaceControl, this.surfaceBehindMatrix, this.tmpFloat);
                    transaction.setCornerRadius(surfaceControl, this.roundedCornerRadius);
                    transaction.setAlpha(surfaceControl, f3);
                    transaction.apply();
                } else {
                    SyncRtSurfaceTransactionApplier.SurfaceParams build = new SyncRtSurfaceTransactionApplier.SurfaceParams.Builder(remoteAnimationTarget.leash).withMatrix(this.surfaceBehindMatrix).withCornerRadius(this.roundedCornerRadius).withAlpha(f3).build();
                    SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier = this.surfaceTransactionApplier;
                    Intrinsics.checkNotNull(syncRtSurfaceTransactionApplier);
                    syncRtSurfaceTransactionApplier.scheduleApply(new SyncRtSurfaceTransactionApplier.SurfaceParams[]{build});
                }
                i++;
                f2 = 0.0f;
            }
        }
        if (z) {
            float f6 = 25 / 325.0f;
            float max = Math.max(0.0f, (f - f6) / (1.0f - f6));
            float f7 = 150 / 325.0f;
            float coerceIn = RangesKt.coerceIn((f - f7) / ((f7 + f7) - f7), 0.0f, 1.0f);
            setWallpaperAppearAmount(max, this.openingWallpaperTargets);
            setWallpaperAppearAmount(1 - coerceIn, this.closingWallpaperTargets);
        }
    }

    public final void setWallpaperAppearAmount(float f, RemoteAnimationTarget[] remoteAnimationTargetArr) {
        if (remoteAnimationTargetArr != null) {
            for (RemoteAnimationTarget remoteAnimationTarget : remoteAnimationTargetArr) {
                SurfaceControl surfaceControl = remoteAnimationTarget.leash;
                View view = this.keyguardViewController.getViewRootImpl().getView();
                if ((view == null || view.getVisibility() != 0) && surfaceControl != null && surfaceControl.isValid()) {
                    SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
                    transaction.setAlpha(surfaceControl, f);
                    transaction.apply();
                } else {
                    SyncRtSurfaceTransactionApplier.SurfaceParams build = new SyncRtSurfaceTransactionApplier.SurfaceParams.Builder(remoteAnimationTarget.leash).withAlpha(f).build();
                    SyncRtSurfaceTransactionApplier syncRtSurfaceTransactionApplier = this.surfaceTransactionApplier;
                    Intrinsics.checkNotNull(syncRtSurfaceTransactionApplier);
                    syncRtSurfaceTransactionApplier.scheduleApply(new SyncRtSurfaceTransactionApplier.SurfaceParams[]{build});
                }
            }
        }
    }

    public final boolean shouldPerformSmartspaceTransition() {
        SmartspaceState smartspaceState;
        if (!((FeatureFlagsClassicRelease) this.featureFlags).isEnabled(Flags.SMARTSPACE_SHARED_ELEMENT_TRANSITION_ENABLED) || this.launcherUnlockController == null || this.lockscreenSmartspace == null || (smartspaceState = this.launcherSmartspaceState) == null || !smartspaceState.visibleOnScreen || !isSupportedLauncherUnderneath() || ((BiometricUnlockController) this.biometricUnlockControllerLazy.get()).isWakeAndUnlock()) {
            return false;
        }
        if (!((KeyguardStateControllerImpl) this.keyguardStateController).mCanDismissLockScreen) {
            BiometricUnlockController biometricUnlockController = (BiometricUnlockController) this.biometricUnlockControllerLazy.get();
            if (!biometricUnlockController.isWakeAndUnlock() && biometricUnlockController.mMode != 5) {
                return false;
            }
        }
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
        if (keyguardStateControllerImpl.mPrimaryBouncerShowing || keyguardStateControllerImpl.mFlingingToDismissKeyguardDuringSwipeGesture || keyguardStateControllerImpl.mDismissingFromTouch) {
            return false;
        }
        return !Utilities.isLargeScreen(this.resources, this.windowManager);
    }

    public final void unlockToLauncherWithInWindowAnimations() {
        View view;
        View view2;
        this.surfaceBehindAlpha = 1.0f;
        setSurfaceBehindAppearAmount(1.0f, false);
        try {
            ILauncherUnlockAnimationController$Stub$Proxy iLauncherUnlockAnimationController$Stub$Proxy = this.launcherUnlockController;
            if (iLauncherUnlockAnimationController$Stub$Proxy != null) {
                iLauncherUnlockAnimationController$Stub$Proxy.playUnlockAnimation(633L, 25L);
            }
        } catch (DeadObjectException unused) {
            Log.e("KeyguardUnlock", "launcherUnlockAnimationController was dead, but non-null. Catching exception as this should mean Launcher is in the process of being destroyed, but the IPC to System UI telling us hasn't arrived yet.");
        }
        this.launcherPreparedForUnlock = false;
        if (shouldPerformSmartspaceTransition() && (view = this.lockscreenSmartspace) != null && view.getVisibility() == 0 && (view2 = this.lockscreenSmartspace) != null) {
            view2.setVisibility(4);
        }
        RemoteAnimationTarget[] remoteAnimationTargetArr = this.openingWallpaperTargets;
        if (remoteAnimationTargetArr != null) {
            if (!(remoteAnimationTargetArr.length == 0)) {
                Log.d("KeyguardUnlock", "fadeOutWallpaper");
                this.wallpaperFadeOutUnlockAnimator.cancel();
                this.wallpaperFadeOutUnlockAnimator.start();
            }
        }
        this.handler.postDelayed(new Runnable() { // from class: com.android.systemui.keyguard.KeyguardUnlockAnimationController$unlockToLauncherWithInWindowAnimations$1
            @Override // java.lang.Runnable
            public final void run() {
                if (((KeyguardViewMediator) KeyguardUnlockAnimationController.this.keyguardViewMediator.get()).isShowingAndNotOccluded() && !((KeyguardStateControllerImpl) KeyguardUnlockAnimationController.this.keyguardStateController).mKeyguardGoingAway) {
                    Log.e("KeyguardUnlock", "Finish keyguard exit animation delayed Runnable ran, but we are showing and not going away.");
                    return;
                }
                KeyguardUnlockAnimationController keyguardUnlockAnimationController = KeyguardUnlockAnimationController.this;
                RemoteAnimationTarget[] remoteAnimationTargetArr2 = keyguardUnlockAnimationController.openingWallpaperTargets;
                if (remoteAnimationTargetArr2 != null) {
                    if (!(remoteAnimationTargetArr2.length == 0)) {
                        Log.d("KeyguardUnlock", "fadeInWallpaper");
                        keyguardUnlockAnimationController.wallpaperCannedUnlockAnimator.cancel();
                        keyguardUnlockAnimationController.wallpaperCannedUnlockAnimator.start();
                        KeyguardUnlockAnimationController keyguardUnlockAnimationController2 = KeyguardUnlockAnimationController.this;
                        if (((KeyguardStateControllerImpl) keyguardUnlockAnimationController2.keyguardStateController).mShowing) {
                            keyguardUnlockAnimationController2.keyguardViewController.hide(keyguardUnlockAnimationController2.surfaceBehindRemoteAnimationStartTime, 0L);
                            return;
                        } else {
                            Log.i("KeyguardUnlock", "#hideKeyguardViewAfterRemoteAnimation called when keyguard view is not showing. Ignoring...");
                            return;
                        }
                    }
                }
                ((KeyguardViewMediator) keyguardUnlockAnimationController.keyguardViewMediator.get()).exitKeyguardAndFinishSurfaceBehindRemoteAnimation();
            }
        }, 25L);
    }

    public final void updateSurfaceBehindAppearAmount() {
        if (this.surfaceBehindRemoteAnimationTargets == null || this.playingCannedUnlockAnimation) {
            return;
        }
        KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) this.keyguardStateController;
        if (keyguardStateControllerImpl.mFlingingToDismissKeyguard) {
            setSurfaceBehindAppearAmount(keyguardStateControllerImpl.mDismissAmount, true);
        } else if (keyguardStateControllerImpl.mDismissingFromTouch || keyguardStateControllerImpl.mSnappingKeyguardBackAfterSwipe) {
            setSurfaceBehindAppearAmount((keyguardStateControllerImpl.mDismissAmount - 0.15f) / 0.15f, true);
        }
    }

    public static /* synthetic */ void getSurfaceBehindAlphaAnimator$annotations() {
    }

    public static /* synthetic */ void getSurfaceBehindEntryAnimator$annotations() {
    }

    public static /* synthetic */ void getSurfaceTransactionApplier$annotations() {
    }

    public static /* synthetic */ void getWillUnlockWithInWindowLauncherAnimations$annotations() {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface KeyguardUnlockAnimationListener {
        void onUnlockAnimationFinished();

        default void onUnlockAnimationStarted(boolean z, boolean z2) {
        }
    }
}

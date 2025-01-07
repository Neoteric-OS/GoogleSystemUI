package com.android.systemui.statusbar.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.Display;
import android.view.WindowManager;
import com.android.app.animation.Interpolators;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.DejankUtils;
import com.android.systemui.keyguard.WakefulnessLifecycle;
import com.android.systemui.shade.NotificationShadeWindowControllerImpl;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.shade.domain.interactor.PanelExpansionInteractor;
import com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor;
import com.android.systemui.statusbar.CircleReveal;
import com.android.systemui.statusbar.LightRevealScrim;
import com.android.systemui.statusbar.NotificationShadeWindowController;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.Assert;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.GlobalSettingsImpl;
import dagger.Lazy;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnlockedScreenOffAnimationController implements WakefulnessLifecycle.Observer, ScreenOffAnimation {
    public float animatorDurationScale = 1.0f;
    public final UnlockedScreenOffAnimationController$animatorDurationScaleObserver$1 animatorDurationScaleObserver;
    public CentralSurfacesImpl centralSurfaces;
    public final Context context;
    public Boolean decidedToAnimateGoingToSleep;
    public final Lazy dozeParameters;
    public final GlobalSettings globalSettings;
    public final Handler handler;
    public boolean initialized;
    public final InteractionJankMonitor interactionJankMonitor;
    public final KeyguardStateController keyguardStateController;
    public final Lazy keyguardViewMediatorLazy;
    public boolean lightRevealAnimationPlaying;
    public final ValueAnimator lightRevealAnimator;
    public LightRevealScrim lightRevealScrim;
    public final Lazy notifShadeWindowControllerLazy;
    public final Lazy panelExpansionInteractorLazy;
    public final PowerManager powerManager;
    public final Lazy shadeLockscreenInteractorLazy;
    public boolean shouldAnimateInKeyguard;
    public final UnlockedScreenOffAnimationController$special$$inlined$namedRunnable$1 startLightRevealCallback;
    public final StatusBarStateControllerImpl statusBarStateControllerImpl;
    public final WakefulnessLifecycle wakefulnessLifecycle;

    /* JADX WARN: Type inference failed for: r1v6, types: [com.android.systemui.statusbar.phone.UnlockedScreenOffAnimationController$animatorDurationScaleObserver$1] */
    public UnlockedScreenOffAnimationController(Context context, WakefulnessLifecycle wakefulnessLifecycle, StatusBarStateControllerImpl statusBarStateControllerImpl, Lazy lazy, KeyguardStateController keyguardStateController, Lazy lazy2, GlobalSettings globalSettings, Lazy lazy3, InteractionJankMonitor interactionJankMonitor, PowerManager powerManager, Lazy lazy4, Lazy lazy5, Handler handler) {
        this.context = context;
        this.wakefulnessLifecycle = wakefulnessLifecycle;
        this.statusBarStateControllerImpl = statusBarStateControllerImpl;
        this.keyguardStateController = keyguardStateController;
        this.dozeParameters = lazy2;
        this.globalSettings = globalSettings;
        this.notifShadeWindowControllerLazy = lazy3;
        this.interactionJankMonitor = interactionJankMonitor;
        this.powerManager = powerManager;
        this.shadeLockscreenInteractorLazy = lazy4;
        this.panelExpansionInteractorLazy = lazy5;
        this.handler = handler;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat.setDuration(500L);
        ofFloat.setInterpolator(Interpolators.LINEAR);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.phone.UnlockedScreenOffAnimationController$lightRevealAnimator$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                LightRevealScrim lightRevealScrim = UnlockedScreenOffAnimationController.this.lightRevealScrim;
                if (!((lightRevealScrim == null ? null : lightRevealScrim).revealEffect instanceof CircleReveal)) {
                    if (lightRevealScrim == null) {
                        lightRevealScrim = null;
                    }
                    lightRevealScrim.setRevealAmount(((Float) valueAnimator.getAnimatedValue()).floatValue());
                }
                UnlockedScreenOffAnimationController unlockedScreenOffAnimationController = UnlockedScreenOffAnimationController.this;
                LightRevealScrim lightRevealScrim2 = unlockedScreenOffAnimationController.lightRevealScrim;
                if ((lightRevealScrim2 != null ? lightRevealScrim2 : null).interpolatedRevealAmount >= 0.1f || !unlockedScreenOffAnimationController.interactionJankMonitor.isInstrumenting(40)) {
                    return;
                }
                UnlockedScreenOffAnimationController.this.interactionJankMonitor.end(40);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.phone.UnlockedScreenOffAnimationController$lightRevealAnimator$1$2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                LightRevealScrim lightRevealScrim = UnlockedScreenOffAnimationController.this.lightRevealScrim;
                if ((lightRevealScrim == null ? null : lightRevealScrim).revealEffect instanceof CircleReveal) {
                    return;
                }
                if (lightRevealScrim == null) {
                    lightRevealScrim = null;
                }
                lightRevealScrim.setRevealAmount(1.0f);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                UnlockedScreenOffAnimationController unlockedScreenOffAnimationController = UnlockedScreenOffAnimationController.this;
                unlockedScreenOffAnimationController.lightRevealAnimationPlaying = false;
                unlockedScreenOffAnimationController.interactionJankMonitor.end(40);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                UnlockedScreenOffAnimationController unlockedScreenOffAnimationController = UnlockedScreenOffAnimationController.this;
                unlockedScreenOffAnimationController.interactionJankMonitor.begin(((NotificationShadeWindowControllerImpl) ((NotificationShadeWindowController) unlockedScreenOffAnimationController.notifShadeWindowControllerLazy.get())).mWindowRootView, 40);
            }
        });
        this.lightRevealAnimator = ofFloat;
        this.startLightRevealCallback = new UnlockedScreenOffAnimationController$special$$inlined$namedRunnable$1(this);
        this.animatorDurationScaleObserver = new ContentObserver() { // from class: com.android.systemui.statusbar.phone.UnlockedScreenOffAnimationController$animatorDurationScaleObserver$1
            {
                super(null);
            }

            @Override // android.database.ContentObserver
            public final void onChange(boolean z) {
                UnlockedScreenOffAnimationController unlockedScreenOffAnimationController = UnlockedScreenOffAnimationController.this;
                String string = ((GlobalSettingsImpl) unlockedScreenOffAnimationController.globalSettings).getString("animator_duration_scale");
                float f = 1.0f;
                if (string != null) {
                    try {
                        f = Float.parseFloat(string);
                    } catch (NumberFormatException unused) {
                    }
                }
                unlockedScreenOffAnimationController.animatorDurationScale = WindowManager.fixScale(f);
            }
        };
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final void initialize(CentralSurfaces centralSurfaces, ShadeViewController shadeViewController, LightRevealScrim lightRevealScrim) {
        this.initialized = true;
        this.lightRevealScrim = lightRevealScrim;
        this.centralSurfaces = (CentralSurfacesImpl) centralSurfaces;
        GlobalSettings globalSettings = this.globalSettings;
        String string = ((GlobalSettingsImpl) globalSettings).getString("animator_duration_scale");
        float f = 1.0f;
        if (string != null) {
            try {
                f = Float.parseFloat(string);
            } catch (NumberFormatException unused) {
            }
        }
        this.animatorDurationScale = WindowManager.fixScale(f);
        globalSettings.registerContentObserverSync(Settings.Global.getUriFor("animator_duration_scale"), false, this.animatorDurationScaleObserver);
        this.wakefulnessLifecycle.mObservers.add(this);
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean isAnimationPlaying() {
        return this.lightRevealAnimationPlaying;
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean isKeyguardShowDelayed() {
        return this.lightRevealAnimationPlaying;
    }

    @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
    public final void onFinishedWakingUp() {
        if (((DozeParameters) this.dozeParameters.get()).canControlUnlockedScreenOff()) {
            CentralSurfacesImpl centralSurfacesImpl = this.centralSurfaces;
            if (centralSurfacesImpl == null) {
                centralSurfacesImpl = null;
            }
            centralSurfacesImpl.updateIsKeyguard(true);
        }
    }

    @Override // com.android.systemui.keyguard.WakefulnessLifecycle.Observer
    public final void onStartedWakingUp() {
        this.decidedToAnimateGoingToSleep = null;
        this.shouldAnimateInKeyguard = false;
        boolean z = DejankUtils.STRICT_MODE_ENABLED;
        Assert.isMainThread();
        ArrayList arrayList = DejankUtils.sPendingRunnables;
        UnlockedScreenOffAnimationController$special$$inlined$namedRunnable$1 unlockedScreenOffAnimationController$special$$inlined$namedRunnable$1 = this.startLightRevealCallback;
        arrayList.remove(unlockedScreenOffAnimationController$special$$inlined$namedRunnable$1);
        DejankUtils.sHandler.removeCallbacks(unlockedScreenOffAnimationController$special$$inlined$namedRunnable$1);
        this.lightRevealAnimator.cancel();
        this.handler.removeCallbacksAndMessages(null);
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean overrideNotificationsDozeAmount() {
        return shouldPlayUnlockedScreenOffAnimation() && this.lightRevealAnimationPlaying;
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean shouldDelayDisplayDozeTransition() {
        return shouldPlayUnlockedScreenOffAnimation();
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean shouldDelayKeyguardShow() {
        return shouldPlayUnlockedScreenOffAnimation();
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean shouldHideScrimOnWakeUp() {
        return this.lightRevealAnimationPlaying;
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean shouldPlayAnimation() {
        return shouldPlayUnlockedScreenOffAnimation();
    }

    public final boolean shouldPlayUnlockedScreenOffAnimation() {
        if (!this.initialized || !((DozeParameters) this.dozeParameters.get()).canControlUnlockedScreenOff() || Intrinsics.areEqual(this.decidedToAnimateGoingToSleep, Boolean.FALSE) || Intrinsics.areEqual(Settings.Global.getString(this.context.getContentResolver(), "animator_duration_scale"), "0") || this.statusBarStateControllerImpl.mState != 0) {
            return false;
        }
        if ((this.centralSurfaces == null || ((PanelExpansionInteractor) this.panelExpansionInteractorLazy.get()).isPanelExpanded()) && !this.lightRevealAnimationPlaying) {
            return false;
        }
        if (((KeyguardStateControllerImpl) this.keyguardStateController).isKeyguardScreenRotationAllowed()) {
            return true;
        }
        Display display = this.context.getDisplay();
        return display != null && display.getRotation() == 0;
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean shouldShowAodIconsWhenShade() {
        return this.lightRevealAnimationPlaying;
    }

    @Override // com.android.systemui.statusbar.phone.ScreenOffAnimation
    public final boolean startAnimation() {
        if (!shouldPlayUnlockedScreenOffAnimation()) {
            this.decidedToAnimateGoingToSleep = Boolean.FALSE;
            return false;
        }
        this.decidedToAnimateGoingToSleep = Boolean.TRUE;
        this.shouldAnimateInKeyguard = true;
        DejankUtils.postAfterTraversal(this.startLightRevealCallback);
        this.handler.postDelayed(new Runnable() { // from class: com.android.systemui.statusbar.phone.UnlockedScreenOffAnimationController$startAnimation$1
            @Override // java.lang.Runnable
            public final void run() {
                if (UnlockedScreenOffAnimationController.this.powerManager.isInteractive(0)) {
                    return;
                }
                UnlockedScreenOffAnimationController unlockedScreenOffAnimationController = UnlockedScreenOffAnimationController.this;
                if (unlockedScreenOffAnimationController.shouldAnimateInKeyguard) {
                    ((ShadeLockscreenInteractor) unlockedScreenOffAnimationController.shadeLockscreenInteractorLazy.get()).showAodUi();
                }
            }
        }, (long) (600 * this.animatorDurationScale));
        return true;
    }
}

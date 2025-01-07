package com.android.systemui.statusbar.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.AlarmManager;
import android.graphics.Color;
import android.os.Handler;
import android.os.Trace;
import android.util.Log;
import android.util.MathUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.android.internal.colorextraction.ColorExtractor;
import com.android.internal.graphics.ColorUtils;
import com.android.internal.util.ContrastColorUtil;
import com.android.keyguard.BouncerPanelExpansionCalculator;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.keyguard.KeyguardUpdateMonitorCallback;
import com.android.settingslib.Utils;
import com.android.systemui.CoreStartable;
import com.android.systemui.DejankUtils;
import com.android.systemui.Dumpable;
import com.android.systemui.animation.ShadeInterpolation;
import com.android.systemui.dock.DockManager;
import com.android.systemui.keyguard.KeyguardUnlockAnimationController;
import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.ui.viewmodel.AlternateBouncerToGoneTransitionViewModel;
import com.android.systemui.keyguard.ui.viewmodel.PrimaryBouncerToGoneTransitionViewModel;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.scrim.ScrimView;
import com.android.systemui.scrim.ScrimView$$ExternalSyntheticLambda4;
import com.android.systemui.shade.transition.LargeScreenShadeInterpolator;
import com.android.systemui.statusbar.notification.stack.ViewState;
import com.android.systemui.statusbar.phone.ScrimState;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.util.AlarmTimeout;
import com.android.systemui.util.kotlin.JavaAdapter;
import com.android.systemui.util.wakelock.DelayedWakeLock;
import com.android.systemui.wallpapers.data.repository.WallpaperRepositoryImpl;
import com.android.wm.shell.R;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17;
import com.google.android.systemui.dreamliner.DockObserver;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.function.Consumer;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScrimController implements ViewTreeObserver.OnPreDrawListener, Dumpable, CoreStartable {
    public final AlternateBouncerToGoneTransitionViewModel mAlternateBouncerToGoneTransitionViewModel;
    public boolean mAnimateChange;
    public boolean mAnimatingPanelExpansionOnUnlock;
    public long mAnimationDelay;
    public Animator.AnimatorListener mAnimatorListener;
    public int mBehindTint;
    public boolean mBlankScreen;
    public ScrimController$$ExternalSyntheticLambda0 mBlankingTransitionRunnable;
    Consumer mBouncerToGoneTransition;
    public Callback mCallback;
    public boolean mClipsQsScrim;
    public final ColorExtractor.GradientColors mColors;
    public boolean mDarkenWhileDragging;
    public final float mDefaultScrimAlpha;
    public final DockManager mDockManager;
    public final DozeParameters mDozeParameters;
    public final Handler mHandler;
    public int mInFrontTint;
    public final JavaAdapter mJavaAdapter;
    public final KeyguardInteractor mKeyguardInteractor;
    public boolean mKeyguardOccluded;
    public final KeyguardStateController mKeyguardStateController;
    public final KeyguardTransitionInteractor mKeyguardTransitionInteractor;
    public final KeyguardUnlockAnimationController mKeyguardUnlockAnimationController;
    public final KeyguardUpdateMonitor mKeyguardUpdateMonitor;
    public final KeyguardVisibilityCallback mKeyguardVisibilityCallback;
    public final LargeScreenShadeInterpolator mLargeScreenShadeInterpolator;
    public final CoroutineDispatcher mMainDispatcher;
    public boolean mNeedsDrawableColorUpdate;
    public ScrimView mNotificationsScrim;
    public int mNotificationsTint;
    public float mPanelScrimMinFraction;
    public ScrimController$$ExternalSyntheticLambda0 mPendingFrameCallback;
    public final PrimaryBouncerToGoneTransitionViewModel mPrimaryBouncerToGoneTransitionViewModel;
    public boolean mQsBottomVisible;
    public float mQsExpansion;
    public float mRawPanelExpansionFraction;
    public boolean mScreenBlankingCallbackCalled;
    public final ScreenOffAnimationController mScreenOffAnimationController;
    public boolean mScreenOn;
    public ScrimView mScrimBehind;
    public ScrimView mScrimInFront;
    public final ScrimController$$ExternalSyntheticLambda5 mScrimStateListener;
    public CentralSurfacesImpl$$ExternalSyntheticLambda2 mScrimVisibleListener;
    public int mScrimsVisibility;
    public final StatusBarKeyguardViewManager mStatusBarKeyguardViewManager;
    public final AlarmTimeout mTimeTicker;
    public float mTransitionToFullShadeProgress;
    public float mTransitionToLockScreenFullShadeNotificationsProgress;
    public boolean mTransitioningToFullShade;
    public boolean mTransparentScrimBackground;
    public boolean mUpdatePending;
    public final DelayedWakeLock mWakeLock;
    public boolean mWakeLockHeld;
    public final WallpaperRepositoryImpl mWallpaperRepository;
    public boolean mWallpaperSupportsAmbientMode;
    public boolean mWallpaperVisibilityTimedOut;
    public static final boolean DEBUG = Log.isLoggable("ScrimController", 3);
    public static final int TAG_KEY_ANIM = R.id.scrim;
    public static final int TAG_START_ALPHA = R.id.scrim_alpha_start;
    public static final int TAG_END_ALPHA = R.id.scrim_alpha_end;
    public boolean mOccludeAnimationPlaying = false;
    public float mBouncerHiddenFraction = 1.0f;
    public ScrimState mState = ScrimState.UNINITIALIZED;
    public float mScrimBehindAlphaKeyguard = 0.2f;
    public float mPanelExpansionFraction = 1.0f;
    public boolean mExpansionAffectsAlpha = true;
    public long mAnimationDuration = -1;
    public final Interpolator mInterpolator = new DecelerateInterpolator();
    public float mInFrontAlpha = -1.0f;
    public float mBehindAlpha = -1.0f;
    public float mNotificationsAlpha = -1.0f;
    public boolean mIsBouncerToGoneTransitionRunning = false;
    public final ScrimController$$ExternalSyntheticLambda1 mScrimAlphaConsumer = new ScrimController$$ExternalSyntheticLambda1(this, 3);
    public final ScrimController$$ExternalSyntheticLambda1 mGlanceableHubConsumer = new ScrimController$$ExternalSyntheticLambda1(this, 1);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class KeyguardVisibilityCallback extends KeyguardUpdateMonitorCallback {
        public KeyguardVisibilityCallback() {
        }

        @Override // com.android.keyguard.KeyguardUpdateMonitorCallback
        public final void onKeyguardVisibilityChanged(boolean z) {
            ScrimController scrimController = ScrimController.this;
            scrimController.mNeedsDrawableColorUpdate = true;
            scrimController.scheduleUpdate$1();
        }
    }

    public ScrimController(LightBarController lightBarController, DozeParameters dozeParameters, AlarmManager alarmManager, final KeyguardStateController keyguardStateController, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17, Handler handler, KeyguardUpdateMonitor keyguardUpdateMonitor, DockManager dockManager, ConfigurationController configurationController, JavaAdapter javaAdapter, ScreenOffAnimationController screenOffAnimationController, KeyguardUnlockAnimationController keyguardUnlockAnimationController, StatusBarKeyguardViewManager statusBarKeyguardViewManager, PrimaryBouncerToGoneTransitionViewModel primaryBouncerToGoneTransitionViewModel, AlternateBouncerToGoneTransitionViewModel alternateBouncerToGoneTransitionViewModel, KeyguardTransitionInteractor keyguardTransitionInteractor, KeyguardInteractor keyguardInteractor, WallpaperRepositoryImpl wallpaperRepositoryImpl, CoroutineDispatcher coroutineDispatcher, LargeScreenShadeInterpolator largeScreenShadeInterpolator) {
        Objects.requireNonNull(lightBarController);
        this.mScrimStateListener = new ScrimController$$ExternalSyntheticLambda5(lightBarController);
        this.mLargeScreenShadeInterpolator = largeScreenShadeInterpolator;
        this.mDefaultScrimAlpha = 1.0f;
        this.mKeyguardStateController = keyguardStateController;
        this.mDarkenWhileDragging = !r3.mCanDismissLockScreen;
        this.mKeyguardUpdateMonitor = keyguardUpdateMonitor;
        this.mKeyguardVisibilityCallback = new KeyguardVisibilityCallback();
        this.mHandler = handler;
        this.mJavaAdapter = javaAdapter;
        this.mScreenOffAnimationController = screenOffAnimationController;
        this.mTimeTicker = new AlarmTimeout(alarmManager, new AlarmManager.OnAlarmListener() { // from class: com.android.systemui.statusbar.phone.ScrimController$$ExternalSyntheticLambda6
            @Override // android.app.AlarmManager.OnAlarmListener
            public final void onAlarm() {
                ScrimController.this.onHideWallpaperTimeout();
            }
        }, "hide_aod_wallpaper", handler);
        this.mWakeLock = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleSysUIComponentImpl$SwitchingProvider$17.create("Scrims");
        this.mDozeParameters = dozeParameters;
        this.mDockManager = dockManager;
        this.mKeyguardUnlockAnimationController = keyguardUnlockAnimationController;
        ((KeyguardStateControllerImpl) keyguardStateController).addCallback(new KeyguardStateController.Callback() { // from class: com.android.systemui.statusbar.phone.ScrimController.1
            @Override // com.android.systemui.statusbar.policy.KeyguardStateController.Callback
            public final void onKeyguardFadingAwayChanged() {
                KeyguardStateControllerImpl keyguardStateControllerImpl = (KeyguardStateControllerImpl) keyguardStateController;
                boolean z = keyguardStateControllerImpl.mKeyguardFadingAway;
                long j = keyguardStateControllerImpl.mKeyguardFadingAwayDuration;
                ScrimController.this.getClass();
                for (ScrimState scrimState : ScrimState.values()) {
                    scrimState.mKeyguardFadingAway = z;
                    scrimState.mKeyguardFadingAwayDuration = j;
                }
            }
        });
        this.mStatusBarKeyguardViewManager = statusBarKeyguardViewManager;
        ((ConfigurationControllerImpl) configurationController).addCallback(new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.phone.ScrimController.2
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                ScrimController scrimController = ScrimController.this;
                scrimController.updateThemeColors();
                scrimController.scheduleUpdate$1();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onUiModeChanged() {
                ScrimController scrimController = ScrimController.this;
                scrimController.updateThemeColors();
                scrimController.scheduleUpdate$1();
            }
        });
        this.mColors = new ColorExtractor.GradientColors();
        this.mPrimaryBouncerToGoneTransitionViewModel = primaryBouncerToGoneTransitionViewModel;
        this.mAlternateBouncerToGoneTransitionViewModel = alternateBouncerToGoneTransitionViewModel;
        this.mKeyguardTransitionInteractor = keyguardTransitionInteractor;
        this.mKeyguardInteractor = keyguardInteractor;
        this.mWallpaperRepository = wallpaperRepositoryImpl;
        this.mMainDispatcher = coroutineDispatcher;
    }

    public static boolean isAnimating(View view) {
        return (view == null || view.getTag(TAG_KEY_ANIM) == null) ? false : true;
    }

    public final void applyAndDispatchState() {
        applyState$1$1();
        if (this.mUpdatePending) {
            return;
        }
        setOrAdaptCurrentAnimation(this.mScrimBehind);
        setOrAdaptCurrentAnimation(this.mNotificationsScrim);
        setOrAdaptCurrentAnimation(this.mScrimInFront);
        dispatchBackScrimState(this.mScrimBehind.mViewAlpha);
        if (this.mWallpaperVisibilityTimedOut) {
            this.mWallpaperVisibilityTimedOut = false;
            DejankUtils.postAfterTraversal(new ScrimController$$ExternalSyntheticLambda0(2, this));
        }
    }

    public final void applyState$1$1() {
        ScrimState scrimState = this.mState;
        this.mInFrontTint = scrimState.mFrontTint;
        this.mBehindTint = scrimState.mBehindTint;
        this.mNotificationsTint = scrimState.mNotifTint;
        this.mInFrontAlpha = scrimState.mFrontAlpha;
        this.mBehindAlpha = scrimState.mBehindAlpha;
        this.mNotificationsAlpha = scrimState.mNotifAlpha;
        assertAlphasValid();
        if (this.mExpansionAffectsAlpha) {
            ScrimState scrimState2 = this.mState;
            ScrimState scrimState3 = ScrimState.UNLOCKED;
            ScrimState scrimState4 = ScrimState.GLANCEABLE_HUB_OVER_DREAM;
            ScrimState scrimState5 = ScrimState.DREAMING;
            if (scrimState2 == scrimState3 || scrimState2 == scrimState5 || scrimState2 == scrimState4) {
                if (!this.mOccludeAnimationPlaying && !scrimState2.mLaunchingAffordanceWithPreview) {
                    r4 = false;
                }
                if (!this.mScreenOffAnimationController.shouldExpandNotifications() && !this.mAnimatingPanelExpansionOnUnlock && !r4) {
                    if (this.mTransparentScrimBackground) {
                        this.mBehindAlpha = 0.0f;
                        this.mNotificationsAlpha = 0.0f;
                    } else if (this.mClipsQsScrim) {
                        float pow = (float) Math.pow(getInterpolatedFraction(), 0.800000011920929d);
                        this.mBehindAlpha = 1.0f;
                        this.mNotificationsAlpha = pow * this.mDefaultScrimAlpha;
                    } else {
                        this.mBehindAlpha = this.mLargeScreenShadeInterpolator.getBehindScrimAlpha(this.mPanelExpansionFraction * this.mDefaultScrimAlpha);
                        this.mNotificationsAlpha = this.mLargeScreenShadeInterpolator.getNotificationScrimAlpha(this.mPanelExpansionFraction);
                    }
                    this.mBehindTint = this.mState.mBehindTint;
                    this.mInFrontAlpha = 0.0f;
                }
                ScrimState scrimState6 = this.mState;
                if (scrimState6 == scrimState5 || scrimState6 == scrimState4) {
                    float f = this.mBouncerHiddenFraction;
                    if (f != 1.0f) {
                        float aboutToShowBouncerProgress = BouncerPanelExpansionCalculator.aboutToShowBouncerProgress(f);
                        this.mBehindAlpha = MathUtils.lerp(this.mDefaultScrimAlpha, this.mBehindAlpha, aboutToShowBouncerProgress);
                        this.mBehindTint = ColorUtils.blendARGB(ScrimState.BOUNCER.mBehindTint, this.mBehindTint, aboutToShowBouncerProgress);
                    }
                }
            } else if (scrimState2 == ScrimState.AUTH_SCRIMMED_SHADE) {
                this.mNotificationsAlpha = (float) Math.pow(getInterpolatedFraction(), 0.800000011920929d);
            } else {
                ScrimState scrimState7 = ScrimState.KEYGUARD;
                ScrimState scrimState8 = ScrimState.GLANCEABLE_HUB;
                ScrimState scrimState9 = ScrimState.SHADE_LOCKED;
                if (scrimState2 == scrimState7 || scrimState2 == scrimState9 || scrimState2 == ScrimState.PULSING || scrimState2 == scrimState8) {
                    Pair calculateBackStateForState = calculateBackStateForState(scrimState2);
                    int intValue = ((Integer) calculateBackStateForState.first).intValue();
                    float floatValue = ((Float) calculateBackStateForState.second).floatValue();
                    float f2 = this.mTransitionToFullShadeProgress;
                    if (f2 > 0.0f) {
                        Pair calculateBackStateForState2 = calculateBackStateForState(scrimState9);
                        floatValue = MathUtils.lerp(floatValue, ((Float) calculateBackStateForState2.second).floatValue(), this.mTransitionToFullShadeProgress);
                        intValue = ColorUtils.blendARGB(intValue, ((Integer) calculateBackStateForState2.first).intValue(), this.mTransitionToFullShadeProgress);
                    } else if (this.mState == scrimState8 && f2 == 0.0f && this.mBouncerHiddenFraction == 1.0f) {
                        floatValue = 0.0f;
                    }
                    ScrimState scrimState10 = this.mState;
                    this.mInFrontAlpha = scrimState10.mFrontAlpha;
                    if (this.mClipsQsScrim) {
                        this.mNotificationsAlpha = floatValue;
                        this.mNotificationsTint = intValue;
                        this.mBehindAlpha = 1.0f;
                        this.mBehindTint = DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT;
                    } else {
                        this.mBehindAlpha = floatValue;
                        if (scrimState10 == scrimState7 && this.mTransitionToFullShadeProgress > 0.0f) {
                            this.mNotificationsAlpha = MathUtils.saturate(this.mTransitionToLockScreenFullShadeNotificationsProgress);
                        } else if (scrimState10 == scrimState9) {
                            this.mNotificationsAlpha = getInterpolatedFraction();
                        } else if (scrimState10 == scrimState8 && this.mTransitionToFullShadeProgress == 0.0f) {
                            this.mNotificationsAlpha = 0.0f;
                        } else {
                            this.mNotificationsAlpha = Math.max(1.0f - getInterpolatedFraction(), this.mQsExpansion);
                        }
                        this.mNotificationsTint = this.mState.mNotifTint;
                        this.mBehindTint = intValue;
                    }
                    r4 = this.mState == scrimState7 && this.mTransitionToFullShadeProgress == 0.0f && this.mQsExpansion == 0.0f && !this.mClipsQsScrim;
                    if (this.mKeyguardOccluded || r4) {
                        this.mNotificationsAlpha = 0.0f;
                    }
                }
            }
            if (this.mState != scrimState3) {
                this.mAnimatingPanelExpansionOnUnlock = false;
            }
            assertAlphasValid();
        }
    }

    public final void assertAlphasValid() {
        if (Float.isNaN(this.mBehindAlpha) || Float.isNaN(this.mInFrontAlpha) || Float.isNaN(this.mNotificationsAlpha)) {
            throw new IllegalStateException("Scrim opacity is NaN for state: " + this.mState + ", front: " + this.mInFrontAlpha + ", back: " + this.mBehindAlpha + ", notif: " + this.mNotificationsAlpha);
        }
    }

    public final void calculateAndUpdatePanelExpansion() {
        float f = this.mRawPanelExpansionFraction;
        float f2 = this.mPanelScrimMinFraction;
        if (f2 < 1.0f) {
            f = Math.max((f - f2) / (1.0f - f2), 0.0f);
        }
        if (this.mPanelExpansionFraction != f) {
            ScrimState scrimState = ScrimState.UNLOCKED;
            if (f != 0.0f && this.mKeyguardUnlockAnimationController.playingCannedUnlockAnimation && this.mState != scrimState) {
                this.mAnimatingPanelExpansionOnUnlock = true;
            } else if (f == 0.0f) {
                this.mAnimatingPanelExpansionOnUnlock = false;
            }
            this.mPanelExpansionFraction = f;
            ScrimState scrimState2 = this.mState;
            if ((scrimState2 == scrimState || scrimState2 == ScrimState.KEYGUARD || scrimState2 == ScrimState.DREAMING || scrimState2 == ScrimState.GLANCEABLE_HUB_OVER_DREAM || scrimState2 == ScrimState.SHADE_LOCKED || scrimState2 == ScrimState.PULSING) && this.mExpansionAffectsAlpha && !this.mAnimatingPanelExpansionOnUnlock) {
                applyAndDispatchState();
            }
        }
    }

    public final Pair calculateBackStateForState(ScrimState scrimState) {
        float interpolatedFraction = getInterpolatedFraction();
        float f = this.mClipsQsScrim ? scrimState.mNotifAlpha : scrimState.mBehindAlpha;
        int i = scrimState.mBehindTint;
        float lerp = this.mDarkenWhileDragging ? MathUtils.lerp(this.mDefaultScrimAlpha, f, interpolatedFraction) : MathUtils.lerp(0.0f, f, interpolatedFraction);
        if (this.mStatusBarKeyguardViewManager.mPrimaryBouncerInteractor.isInTransit()) {
            boolean z = this.mClipsQsScrim;
            ScrimState scrimState2 = ScrimState.BOUNCER;
            i = z ? ColorUtils.blendARGB(scrimState2.mNotifTint, scrimState.mNotifTint, interpolatedFraction) : ColorUtils.blendARGB(scrimState2.mBehindTint, scrimState.mBehindTint, interpolatedFraction);
        }
        float f2 = this.mQsExpansion;
        if (f2 > 0.0f) {
            lerp = MathUtils.lerp(lerp, this.mDefaultScrimAlpha, f2);
            float f3 = this.mQsExpansion;
            if (this.mStatusBarKeyguardViewManager.mPrimaryBouncerInteractor.isInTransit()) {
                f3 = BouncerPanelExpansionCalculator.showBouncerProgress(this.mPanelExpansionFraction);
            }
            boolean z2 = this.mClipsQsScrim;
            ScrimState scrimState3 = ScrimState.SHADE_LOCKED;
            i = ColorUtils.blendARGB(i, z2 ? scrimState3.mNotifTint : scrimState3.mBehindTint, f3);
        }
        return new Pair(Integer.valueOf(i), Float.valueOf(((KeyguardStateControllerImpl) this.mKeyguardStateController).mKeyguardGoingAway ? 0.0f : lerp));
    }

    public final void dispatchBackScrimState(float f) {
        if (this.mClipsQsScrim && this.mQsBottomVisible) {
            f = this.mNotificationsAlpha;
        }
        this.mScrimStateListener.accept(this.mState, Float.valueOf(f), this.mColors);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void dispatchScrimsVisible() {
        /*
            r4 = this;
            boolean r0 = r4.mClipsQsScrim
            if (r0 == 0) goto L7
            com.android.systemui.scrim.ScrimView r0 = r4.mNotificationsScrim
            goto L9
        L7:
            com.android.systemui.scrim.ScrimView r0 = r4.mScrimBehind
        L9:
            com.android.systemui.scrim.ScrimView r1 = r4.mScrimInFront
            float r1 = r1.mViewAlpha
            r2 = 1065353216(0x3f800000, float:1.0)
            int r3 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r3 == 0) goto L27
            float r0 = r0.mViewAlpha
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 != 0) goto L1a
            goto L27
        L1a:
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 != 0) goto L25
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L25
            r0 = 0
            goto L28
        L25:
            r0 = 1
            goto L28
        L27:
            r0 = 2
        L28:
            int r1 = r4.mScrimsVisibility
            if (r1 == r0) goto L37
            r4.mScrimsVisibility = r0
            com.android.systemui.statusbar.phone.CentralSurfacesImpl$$ExternalSyntheticLambda2 r4 = r4.mScrimVisibleListener
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r4.accept(r0)
        L37:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.ScrimController.dispatchScrimsVisible():void");
    }

    public void doOnTheNextFrame(Runnable runnable) {
        this.mScrimBehind.postOnAnimationDelayed(runnable, 32L);
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println(" ScrimController: ");
        printWriter.print("  state: ");
        printWriter.println(this.mState);
        printWriter.println("    mClipQsScrim = " + this.mState.mClipQsScrim);
        printWriter.print("  frontScrim:");
        printWriter.print(" viewAlpha=");
        printWriter.print(this.mScrimInFront.mViewAlpha);
        printWriter.print(" alpha=");
        printWriter.print(this.mInFrontAlpha);
        printWriter.print(" tint=0x");
        printWriter.println(Integer.toHexString(this.mScrimInFront.mTintColor));
        printWriter.print("  behindScrim:");
        printWriter.print(" viewAlpha=");
        printWriter.print(this.mScrimBehind.mViewAlpha);
        printWriter.print(" alpha=");
        printWriter.print(this.mBehindAlpha);
        printWriter.print(" tint=0x");
        printWriter.println(Integer.toHexString(this.mScrimBehind.mTintColor));
        printWriter.print("  notificationsScrim:");
        printWriter.print(" viewAlpha=");
        printWriter.print(this.mNotificationsScrim.mViewAlpha);
        printWriter.print(" alpha=");
        printWriter.print(this.mNotificationsAlpha);
        printWriter.print(" tint=0x");
        printWriter.println(Integer.toHexString(this.mNotificationsScrim.mTintColor));
        printWriter.print(" expansionProgress=");
        printWriter.println(this.mTransitionToLockScreenFullShadeNotificationsProgress);
        printWriter.print("  mDefaultScrimAlpha=");
        printWriter.println(this.mDefaultScrimAlpha);
        printWriter.print("  mPanelExpansionFraction=");
        printWriter.println(this.mPanelExpansionFraction);
        printWriter.print("  mExpansionAffectsAlpha=");
        printWriter.println(this.mExpansionAffectsAlpha);
        printWriter.print("  mState.getMaxLightRevealScrimAlpha=");
        printWriter.println(this.mState.getMaxLightRevealScrimAlpha());
    }

    public boolean getClipQsScrim() {
        return this.mClipsQsScrim;
    }

    public final float getCurrentScrimAlpha(View view) {
        if (view == this.mScrimInFront) {
            return this.mInFrontAlpha;
        }
        if (view == this.mScrimBehind) {
            return this.mBehindAlpha;
        }
        if (view == this.mNotificationsScrim) {
            return this.mNotificationsAlpha;
        }
        throw new IllegalArgumentException("Unknown scrim view");
    }

    public final int getCurrentScrimTint(View view) {
        if (view == this.mScrimInFront) {
            return this.mInFrontTint;
        }
        if (view == this.mScrimBehind) {
            return this.mBehindTint;
        }
        if (view == this.mNotificationsScrim) {
            return this.mNotificationsTint;
        }
        throw new IllegalArgumentException("Unknown scrim view");
    }

    public final float getInterpolatedFraction() {
        return this.mStatusBarKeyguardViewManager.mPrimaryBouncerInteractor.isInTransit() ? BouncerPanelExpansionCalculator.aboutToShowBouncerProgress(this.mPanelExpansionFraction) : ShadeInterpolation.getNotificationScrimAlpha(this.mPanelExpansionFraction);
    }

    public final String getScrimName(ScrimView scrimView) {
        return scrimView == this.mScrimInFront ? "front_scrim" : scrimView == this.mScrimBehind ? "behind_scrim" : scrimView == this.mNotificationsScrim ? "notifications_scrim" : "unknown_scrim";
    }

    public final void internalTransitionTo(Callback callback, ScrimState scrimState) {
        if (this.mIsBouncerToGoneTransitionRunning) {
            Log.i("ScrimController", "Skipping transition to: " + scrimState + " while mIsBouncerToGoneTransitionRunning");
            return;
        }
        if (scrimState == this.mState) {
            if (callback == null || this.mCallback == callback) {
                return;
            }
            callback.onFinished();
            return;
        }
        if (DEBUG) {
            Log.d("ScrimController", "State changed to: " + scrimState);
        }
        if (scrimState == ScrimState.UNINITIALIZED) {
            throw new IllegalArgumentException("Cannot change to UNINITIALIZED.");
        }
        ScrimState scrimState2 = this.mState;
        this.mState = scrimState;
        Trace.traceCounter(4096L, "scrim_state", scrimState.ordinal());
        Callback callback2 = this.mCallback;
        if (callback2 != null) {
            callback2.onCancelled();
        }
        this.mCallback = callback;
        scrimState.prepare(scrimState2);
        this.mScreenBlankingCallbackCalled = false;
        this.mAnimationDelay = 0L;
        this.mBlankScreen = scrimState.mBlankScreen;
        this.mAnimateChange = scrimState.mAnimateChange;
        this.mAnimationDuration = scrimState.mAnimationDuration;
        if (this.mState == ScrimState.GLANCEABLE_HUB_OVER_DREAM) {
            this.mPanelExpansionFraction = 0.0f;
        }
        applyState$1$1();
        this.mScrimInFront.mBlendWithMainColor = !(scrimState instanceof ScrimState.AnonymousClass9);
        ScrimController$$ExternalSyntheticLambda0 scrimController$$ExternalSyntheticLambda0 = this.mPendingFrameCallback;
        if (scrimController$$ExternalSyntheticLambda0 != null) {
            this.mScrimBehind.removeCallbacks(scrimController$$ExternalSyntheticLambda0);
            this.mPendingFrameCallback = null;
        }
        if (this.mHandler.hasCallbacks(this.mBlankingTransitionRunnable)) {
            this.mHandler.removeCallbacks(this.mBlankingTransitionRunnable);
            this.mBlankingTransitionRunnable = null;
        }
        this.mNeedsDrawableColorUpdate = scrimState != ScrimState.BRIGHTNESS_MIRROR;
        if (this.mState.isLowPowerState() && !this.mWakeLockHeld) {
            DelayedWakeLock delayedWakeLock = this.mWakeLock;
            if (delayedWakeLock != null) {
                this.mWakeLockHeld = true;
                delayedWakeLock.acquire("ScrimController");
            } else {
                Log.w("ScrimController", "Cannot hold wake lock, it has not been set yet");
            }
        }
        this.mWallpaperVisibilityTimedOut = false;
        boolean z = this.mWallpaperSupportsAmbientMode;
        ScrimState scrimState3 = ScrimState.AOD;
        if (z && this.mState == scrimState3 && (this.mDozeParameters.getAlwaysOn() || ((DockObserver) this.mDockManager).isDocked())) {
            DejankUtils.postAfterTraversal(new ScrimController$$ExternalSyntheticLambda0(3, this));
        } else {
            AlarmTimeout alarmTimeout = this.mTimeTicker;
            Objects.requireNonNull(alarmTimeout);
            DejankUtils.postAfterTraversal(new ScrimController$$ExternalSyntheticLambda0(4, alarmTimeout));
        }
        boolean z2 = this.mKeyguardUpdateMonitor.mNeedsSlowUnlockTransition;
        ScrimState scrimState4 = ScrimState.UNLOCKED;
        if (z2 && this.mState == scrimState4) {
            this.mAnimationDelay = 100L;
            scheduleUpdate$1();
        } else if (((scrimState2 == scrimState3 || scrimState2 == ScrimState.PULSING) && (!this.mDozeParameters.getAlwaysOn() || this.mState == scrimState4)) || (this.mState == scrimState3 && !this.mDozeParameters.getDisplayNeedsBlanking())) {
            onPreDraw();
        } else {
            scheduleUpdate$1();
        }
        dispatchBackScrimState(this.mScrimBehind.mViewAlpha);
    }

    public final void onFinished(Callback callback, ScrimState scrimState) {
        if (this.mPendingFrameCallback != null) {
            return;
        }
        if (isAnimating(this.mScrimBehind) || isAnimating(this.mNotificationsScrim) || isAnimating(this.mScrimInFront)) {
            if (callback == null || callback == this.mCallback) {
                return;
            }
            callback.onFinished();
            return;
        }
        if (this.mWakeLockHeld) {
            this.mWakeLock.release("ScrimController");
            this.mWakeLockHeld = false;
        }
        if (callback != null) {
            callback.onFinished();
            if (callback == this.mCallback) {
                this.mCallback = null;
            }
        }
        if (scrimState == ScrimState.UNLOCKED) {
            this.mInFrontTint = 0;
            ScrimState scrimState2 = this.mState;
            this.mBehindTint = scrimState2.mBehindTint;
            this.mNotificationsTint = scrimState2.mNotifTint;
            updateScrimColor(this.mScrimInFront, 0, this.mInFrontAlpha);
            updateScrimColor(this.mScrimBehind, this.mBehindTint, this.mBehindAlpha);
            updateScrimColor(this.mNotificationsScrim, this.mNotificationsTint, this.mNotificationsAlpha);
        }
    }

    public void onHideWallpaperTimeout() {
        ScrimState scrimState = this.mState;
        if (scrimState == ScrimState.AOD || scrimState == ScrimState.PULSING) {
            if (!this.mWakeLockHeld) {
                DelayedWakeLock delayedWakeLock = this.mWakeLock;
                if (delayedWakeLock != null) {
                    this.mWakeLockHeld = true;
                    delayedWakeLock.acquire("ScrimController");
                } else {
                    Log.w("ScrimController", "Cannot hold wake lock, it has not been set yet");
                }
            }
            this.mWallpaperVisibilityTimedOut = true;
            this.mAnimateChange = true;
            this.mAnimationDuration = this.mDozeParameters.mAlwaysOnPolicy.wallpaperFadeOutDuration;
            scheduleUpdate$1();
        }
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        this.mScrimBehind.getViewTreeObserver().removeOnPreDrawListener(this);
        this.mUpdatePending = false;
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.getClass();
        }
        updateScrims();
        return true;
    }

    public final void scheduleUpdate$1() {
        ScrimView scrimView;
        if (this.mUpdatePending || (scrimView = this.mScrimBehind) == null) {
            return;
        }
        scrimView.invalidate();
        this.mScrimBehind.getViewTreeObserver().addOnPreDrawListener(this);
        this.mUpdatePending = true;
    }

    public void setAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.mAnimatorListener = animatorListener;
    }

    public final void setClipsQsScrim(boolean z) {
        if (z == this.mClipsQsScrim) {
            return;
        }
        this.mClipsQsScrim = z;
        for (ScrimState scrimState : ScrimState.values()) {
            scrimState.mClipQsScrim = this.mClipsQsScrim;
        }
        ScrimView scrimView = this.mScrimBehind;
        if (scrimView != null) {
            scrimView.enableBottomEdgeConcave(this.mClipsQsScrim);
        }
        ScrimState scrimState2 = this.mState;
        if (scrimState2 != ScrimState.UNINITIALIZED) {
            scrimState2.prepare(scrimState2);
            applyAndDispatchState();
        }
    }

    public final void setOccludeAnimationPlaying(boolean z) {
        this.mOccludeAnimationPlaying = z;
        for (ScrimState scrimState : ScrimState.values()) {
            scrimState.mOccludeAnimationPlaying = z;
        }
        applyAndDispatchState();
    }

    public final void setOrAdaptCurrentAnimation(View view) {
        if (view == null) {
            return;
        }
        float currentScrimAlpha = getCurrentScrimAlpha(view);
        boolean z = view == this.mScrimBehind && this.mQsBottomVisible;
        if (!isAnimating(view) || z) {
            updateScrimColor(view, getCurrentScrimTint(view), currentScrimAlpha);
            return;
        }
        ValueAnimator valueAnimator = (ValueAnimator) view.getTag(TAG_KEY_ANIM);
        int i = TAG_END_ALPHA;
        float floatValue = ((Float) view.getTag(i)).floatValue();
        int i2 = TAG_START_ALPHA;
        view.setTag(i2, Float.valueOf((currentScrimAlpha - floatValue) + ((Float) view.getTag(i2)).floatValue()));
        view.setTag(i, Float.valueOf(currentScrimAlpha));
        valueAnimator.setCurrentPlayTime(valueAnimator.getCurrentPlayTime());
    }

    public final void setScrimAlpha(final ScrimView scrimView, float f) {
        Callback callback;
        int i = 0;
        if (f == 0.0f) {
            scrimView.setClickable(false);
        } else {
            scrimView.setClickable(this.mState != ScrimState.AOD);
        }
        float f2 = scrimView.mViewAlpha;
        int i2 = TAG_KEY_ANIM;
        ViewState.AnonymousClass1 anonymousClass1 = ViewState.NO_NEW_ANIMATIONS;
        ValueAnimator valueAnimator = (ValueAnimator) scrimView.getTag(i2);
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (this.mPendingFrameCallback != null) {
            return;
        }
        if (this.mBlankScreen) {
            updateScrimColor(this.mScrimInFront, DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT, 1.0f);
            ScrimController$$ExternalSyntheticLambda0 scrimController$$ExternalSyntheticLambda0 = new ScrimController$$ExternalSyntheticLambda0(i, this);
            this.mPendingFrameCallback = scrimController$$ExternalSyntheticLambda0;
            doOnTheNextFrame(scrimController$$ExternalSyntheticLambda0);
            return;
        }
        if (!this.mScreenBlankingCallbackCalled && (callback = this.mCallback) != null) {
            callback.onDisplayBlanked();
            this.mScreenBlankingCallbackCalled = true;
        }
        if (scrimView == this.mScrimBehind) {
            dispatchBackScrimState(f);
        }
        boolean z = f != f2;
        boolean z2 = scrimView.mTintColor != getCurrentScrimTint(scrimView);
        if (z || z2) {
            if (!this.mAnimateChange) {
                updateScrimColor(scrimView, getCurrentScrimTint(scrimView), f);
                return;
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            Animator.AnimatorListener animatorListener = this.mAnimatorListener;
            if (animatorListener != null) {
                ofFloat.addListener(animatorListener);
            }
            final int i3 = scrimView.mTintColor;
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.phone.ScrimController$$ExternalSyntheticLambda2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    ScrimController scrimController = ScrimController.this;
                    ScrimView scrimView2 = scrimView;
                    int i4 = i3;
                    scrimController.getClass();
                    float floatValue = ((Float) scrimView2.getTag(ScrimController.TAG_START_ALPHA)).floatValue();
                    float floatValue2 = ((Float) valueAnimator2.getAnimatedValue()).floatValue();
                    int currentScrimTint = scrimController.getCurrentScrimTint(scrimView2);
                    scrimController.updateScrimColor(scrimView2, ColorUtils.blendARGB(i4, currentScrimTint, floatValue2), MathUtils.constrain(MathUtils.lerp(floatValue, scrimController.getCurrentScrimAlpha(scrimView2), floatValue2), 0.0f, 1.0f));
                    scrimController.dispatchScrimsVisible();
                }
            });
            ofFloat.setInterpolator(this.mInterpolator);
            ofFloat.setStartDelay(this.mAnimationDelay);
            ofFloat.setDuration(this.mAnimationDuration);
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.phone.ScrimController.3
                public final Callback mLastCallback;
                public final ScrimState mLastState;

                {
                    this.mLastState = ScrimController.this.mState;
                    this.mLastCallback = ScrimController.this.mCallback;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    scrimView.setTag(ScrimController.TAG_KEY_ANIM, null);
                    ScrimController.this.onFinished(this.mLastCallback, this.mLastState);
                    ScrimController.this.dispatchScrimsVisible();
                }
            });
            scrimView.setTag(TAG_START_ALPHA, Float.valueOf(f2));
            scrimView.setTag(TAG_END_ALPHA, Float.valueOf(getCurrentScrimAlpha(scrimView)));
            scrimView.setTag(i2, ofFloat);
            ofFloat.start();
        }
    }

    public final void setWakeLockScreenSensorActive(boolean z) {
        for (ScrimState scrimState : ScrimState.values()) {
            scrimState.mWakeLockScreenSensorActive = z;
        }
        ScrimState scrimState2 = this.mState;
        if (scrimState2 == ScrimState.PULSING) {
            float f = scrimState2.mBehindAlpha;
            if (this.mBehindAlpha != f) {
                this.mBehindAlpha = f;
                if (!Float.isNaN(f)) {
                    updateScrims();
                    return;
                }
                throw new IllegalStateException("Scrim opacity is NaN for state: " + this.mState + ", back: " + this.mBehindAlpha);
            }
        }
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        this.mJavaAdapter.alwaysCollectFlow(this.mWallpaperRepository.wallpaperSupportsAmbientMode, new ScrimController$$ExternalSyntheticLambda1(this, 0));
    }

    public final void updateScrimColor(View view, int i, float f) {
        float max = Math.max(0.0f, Math.min(1.0f, f));
        if (view instanceof ScrimView) {
            ScrimView scrimView = (ScrimView) view;
            Trace.traceCounter(4096L, getScrimName(scrimView).concat("_alpha"), (int) (255.0f * max));
            Trace.traceCounter(4096L, getScrimName(scrimView).concat("_tint"), Color.alpha(i));
            scrimView.getClass();
            scrimView.executeOnExecutor(new ScrimView$$ExternalSyntheticLambda4(scrimView, i));
            if (!this.mIsBouncerToGoneTransitionRunning) {
                scrimView.setViewAlpha(max);
            }
        } else {
            view.setAlpha(max);
        }
        dispatchScrimsVisible();
    }

    public final void updateScrims() {
        if (this.mNeedsDrawableColorUpdate) {
            this.mNeedsDrawableColorUpdate = false;
            ScrimView scrimView = this.mScrimInFront;
            boolean z = (scrimView.mViewAlpha == 0.0f || this.mBlankScreen) ? false : true;
            boolean z2 = (this.mScrimBehind.mViewAlpha == 0.0f || this.mBlankScreen) ? false : true;
            boolean z3 = (this.mNotificationsScrim.mViewAlpha == 0.0f || this.mBlankScreen) ? false : true;
            scrimView.setColors(this.mColors, z);
            this.mScrimBehind.setColors(this.mColors, z2);
            this.mNotificationsScrim.setColors(this.mColors, z3);
            dispatchBackScrimState(this.mScrimBehind.mViewAlpha);
        }
        ScrimState scrimState = this.mState;
        ScrimState scrimState2 = ScrimState.AOD;
        ScrimState scrimState3 = ScrimState.PULSING;
        boolean z4 = (scrimState == scrimState2 || scrimState == scrimState3) && this.mWallpaperVisibilityTimedOut;
        boolean z5 = (scrimState == scrimState3 || scrimState == scrimState2) && this.mKeyguardOccluded;
        if (z4 || z5) {
            this.mBehindAlpha = 1.0f;
        }
        if (((KeyguardStateControllerImpl) this.mKeyguardStateController).mKeyguardGoingAway) {
            this.mNotificationsAlpha = 0.0f;
        }
        if (this.mKeyguardOccluded && (scrimState == ScrimState.KEYGUARD || scrimState == ScrimState.SHADE_LOCKED)) {
            this.mBehindAlpha = 0.0f;
            this.mNotificationsAlpha = 0.0f;
        }
        setScrimAlpha(this.mScrimInFront, this.mInFrontAlpha);
        setScrimAlpha(this.mScrimBehind, this.mBehindAlpha);
        setScrimAlpha(this.mNotificationsScrim, this.mNotificationsAlpha);
        onFinished(this.mCallback, this.mState);
        dispatchScrimsVisible();
    }

    public final void updateThemeColors() {
        ScrimView scrimView = this.mScrimBehind;
        if (scrimView == null) {
            return;
        }
        int defaultColor = Utils.getColorAttr(android.R.^attr-private.materialColorSurfaceDim, scrimView.getContext()).getDefaultColor();
        int defaultColor2 = Utils.getColorAttr(android.R.^attr-private.materialColorPrimary, this.mScrimBehind.getContext()).getDefaultColor();
        this.mColors.setMainColor(defaultColor);
        this.mColors.setSecondaryColor(defaultColor2);
        this.mColors.setSupportsDarkText(!ContrastColorUtil.isColorDark(defaultColor));
        int defaultColor3 = Utils.getColorAttr(android.R.^attr-private.materialColorSurface, this.mScrimBehind.getContext()).getDefaultColor();
        for (ScrimState scrimState : ScrimState.values()) {
            scrimState.setSurfaceColor(defaultColor3);
        }
        this.mNeedsDrawableColorUpdate = true;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        void onFinished();

        default void onCancelled() {
        }

        default void onDisplayBlanked() {
        }
    }
}

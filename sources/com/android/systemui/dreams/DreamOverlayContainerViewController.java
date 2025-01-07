package com.android.systemui.dreams;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.DreamManager;
import android.content.res.Resources;
import android.graphics.Region;
import android.os.Handler;
import android.util.MathUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewRootImpl;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.android.app.animation.Interpolators;
import com.android.compose.animation.scene.SceneKey;
import com.android.dream.lowlight.LowLightTransitionCoordinator;
import com.android.keyguard.BouncerPanelExpansionCalculator;
import com.android.systemui.ambient.statusbar.ui.AmbientStatusBarViewController;
import com.android.systemui.ambient.touch.scrim.BouncerlessScrimController;
import com.android.systemui.ambient.touch.scrim.BouncerlessScrimController$$ExternalSyntheticLambda4;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor;
import com.android.systemui.communal.domain.interactor.CommunalInteractor;
import com.android.systemui.complication.ComplicationHostViewController;
import com.android.systemui.complication.ComplicationLayoutParams;
import com.android.systemui.dreams.DreamOverlayStateController;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt;
import com.android.systemui.lifecycle.RepeatWhenAttachedKt$repeatWhenAttached$1;
import com.android.systemui.log.core.Logger;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.shade.domain.interactor.ShadeInteractor;
import com.android.systemui.shade.domain.interactor.ShadeInteractorImpl;
import com.android.systemui.statusbar.BlurUtils;
import com.android.systemui.touch.TouchInsetManager;
import com.android.systemui.touch.TouchInsetManager$TouchInsetSession$$ExternalSyntheticLambda1;
import com.android.systemui.util.ViewController;
import com.android.systemui.util.kotlin.JavaAdapterKt;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DreamOverlayContainerViewController extends ViewController {
    public boolean mAnyBouncerShowing;
    public final CoroutineDispatcher mBackgroundDispatcher;
    public final BlurUtils mBlurUtils;
    public boolean mBouncerAnimating;
    public final AnonymousClass2 mBouncerExpansionCallback;
    public final AnonymousClass1 mBouncerlessExpansionCallback;
    public final BouncerlessScrimController mBouncerlessScrimController;
    public final long mBurnInProtectionUpdateInterval;
    public final CommunalInteractor mCommunalInteractor;
    public final ComplicationHostViewController mComplicationHostViewController;
    public final DreamManager mDreamManager;
    public final DreamOverlayAnimationsController mDreamOverlayAnimationsController;
    public final int mDreamOverlayMaxTranslationY;
    public final DreamOverlayStateController.Callback mDreamOverlayStateCallback;
    public boolean mExitingLowLight;
    public RepeatWhenAttachedKt$repeatWhenAttached$1 mFlowHandle;
    public final Handler mHandler;
    public long mJitterStartTimeMillis;
    public final KeyguardTransitionInteractor mKeyguardTransitionInteractor;
    public final LowLightTransitionCoordinator mLowLightTransitionCoordinator;
    public final int mMaxBurnInOffset;
    public final long mMillisUntilFullJitter;
    public final PrimaryBouncerCallbackInteractor mPrimaryBouncerCallbackInteractor;
    public final ShadeInteractor mShadeInteractor;
    public final DreamOverlayStateController mStateController;
    public final AmbientStatusBarViewController mStatusBarViewController;
    public final TouchInsetManager.TouchInsetSession mTouchInsetSession;
    public boolean mWakingUpFromSwipe;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.dreams.DreamOverlayContainerViewController$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public AnonymousClass1() {
        }
    }

    /* renamed from: -$$Nest$mupdateTransitionState, reason: not valid java name */
    public static void m803$$Nest$mupdateTransitionState(DreamOverlayContainerViewController dreamOverlayContainerViewController, float f) {
        dreamOverlayContainerViewController.getClass();
        Iterator it = Arrays.asList(1, 2).iterator();
        while (true) {
            float f2 = 1.0f;
            if (!it.hasNext()) {
                ViewRootImpl viewRootImpl = ((DreamOverlayContainerView) dreamOverlayContainerViewController.mView).getViewRootImpl();
                float aboutToShowBouncerProgress = 1.0f - BouncerPanelExpansionCalculator.aboutToShowBouncerProgress(f);
                BlurUtils blurUtils = dreamOverlayContainerViewController.mBlurUtils;
                blurUtils.applyBlur(viewRootImpl, (int) blurUtils.blurRadiusOfRatio(aboutToShowBouncerProgress), false);
                return;
            }
            int intValue = ((Integer) it.next()).intValue();
            PathInterpolator pathInterpolator = (PathInterpolator) Interpolators.LINEAR_OUT_SLOW_IN;
            final float interpolation = pathInterpolator.getInterpolation(intValue == 1 ? MathUtils.constrain((f - 0.94f) / 0.06f, 0.0f, 1.0f) : BouncerPanelExpansionCalculator.aboutToShowBouncerProgress(f + 0.03f));
            if (intValue != 1) {
                f2 = BouncerPanelExpansionCalculator.aboutToShowBouncerProgress(0.03f + f);
            } else if (f < 0.98f) {
                f2 = ((double) f) < 0.93d ? 0.0f : (f - 0.93f) / 0.05f;
            }
            final float lerp = MathUtils.lerp(-dreamOverlayContainerViewController.mDreamOverlayMaxTranslationY, 0, pathInterpolator.getInterpolation(f2));
            dreamOverlayContainerViewController.mComplicationHostViewController.getViewsAtPosition(intValue).forEach(new Consumer() { // from class: com.android.systemui.dreams.DreamOverlayContainerViewController$$ExternalSyntheticLambda3
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    float f3 = interpolation;
                    float f4 = lerp;
                    View view = (View) obj;
                    view.setAlpha(f3);
                    view.setTranslationY(f4);
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.android.systemui.dreams.DreamOverlayContainerViewController$2] */
    public DreamOverlayContainerViewController(DreamOverlayContainerView dreamOverlayContainerView, ComplicationHostViewController complicationHostViewController, ViewGroup viewGroup, AmbientStatusBarViewController ambientStatusBarViewController, LowLightTransitionCoordinator lowLightTransitionCoordinator, TouchInsetManager.TouchInsetSession touchInsetSession, BlurUtils blurUtils, Handler handler, CoroutineDispatcher coroutineDispatcher, Resources resources, int i, long j, long j2, PrimaryBouncerCallbackInteractor primaryBouncerCallbackInteractor, DreamOverlayAnimationsController dreamOverlayAnimationsController, DreamOverlayStateController dreamOverlayStateController, BouncerlessScrimController bouncerlessScrimController, KeyguardTransitionInteractor keyguardTransitionInteractor, ShadeInteractor shadeInteractor, CommunalInteractor communalInteractor, DreamManager dreamManager) {
        super(dreamOverlayContainerView);
        this.mBouncerlessExpansionCallback = new AnonymousClass1();
        this.mBouncerExpansionCallback = new PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback() { // from class: com.android.systemui.dreams.DreamOverlayContainerViewController.2
            @Override // com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback
            public final void onExpansionChanged(float f) {
                DreamOverlayContainerViewController dreamOverlayContainerViewController = DreamOverlayContainerViewController.this;
                if (dreamOverlayContainerViewController.mBouncerAnimating) {
                    DreamOverlayContainerViewController.m803$$Nest$mupdateTransitionState(dreamOverlayContainerViewController, f);
                }
            }

            @Override // com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback
            public final void onFullyHidden() {
                DreamOverlayContainerViewController.this.mBouncerAnimating = false;
            }

            @Override // com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback
            public final void onFullyShown() {
                DreamOverlayContainerViewController.this.mBouncerAnimating = false;
            }

            @Override // com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback
            public final void onStartingToHide() {
                DreamOverlayContainerViewController.this.mBouncerAnimating = true;
            }

            @Override // com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback
            public final void onStartingToShow() {
                DreamOverlayContainerViewController.this.mBouncerAnimating = true;
            }

            @Override // com.android.systemui.bouncer.domain.interactor.PrimaryBouncerCallbackInteractor.PrimaryBouncerExpansionCallback
            public final void onVisibilityChanged(boolean z) {
                if (z) {
                    return;
                }
                DreamOverlayContainerViewController.m803$$Nest$mupdateTransitionState(DreamOverlayContainerViewController.this, 1.0f);
            }
        };
        this.mDreamOverlayStateCallback = new DreamOverlayStateController.Callback() { // from class: com.android.systemui.dreams.DreamOverlayContainerViewController.3
            @Override // com.android.systemui.dreams.DreamOverlayStateController.Callback
            public final void onExitLowLight() {
                DreamOverlayContainerViewController.this.mExitingLowLight = true;
            }
        };
        this.mStatusBarViewController = ambientStatusBarViewController;
        this.mTouchInsetSession = touchInsetSession;
        this.mBlurUtils = blurUtils;
        this.mDreamOverlayAnimationsController = dreamOverlayAnimationsController;
        this.mStateController = dreamOverlayStateController;
        this.mCommunalInteractor = communalInteractor;
        this.mLowLightTransitionCoordinator = lowLightTransitionCoordinator;
        this.mBouncerlessScrimController = bouncerlessScrimController;
        this.mKeyguardTransitionInteractor = keyguardTransitionInteractor;
        this.mShadeInteractor = shadeInteractor;
        this.mComplicationHostViewController = complicationHostViewController;
        this.mDreamOverlayMaxTranslationY = resources.getDimensionPixelSize(R.dimen.dream_overlay_y_offset);
        viewGroup.addView(complicationHostViewController.mView, new ViewGroup.LayoutParams(-1, -1));
        this.mHandler = handler;
        this.mBackgroundDispatcher = coroutineDispatcher;
        this.mMaxBurnInOffset = i;
        this.mBurnInProtectionUpdateInterval = j;
        this.mMillisUntilFullJitter = j2;
        this.mPrimaryBouncerCallbackInteractor = primaryBouncerCallbackInteractor;
        this.mDreamManager = dreamManager;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onInit() {
        this.mStateController.addCallback(this.mDreamOverlayStateCallback);
        this.mStatusBarViewController.init$9();
        this.mComplicationHostViewController.init$9();
        View view = this.mView;
        DreamOverlayAnimationsController dreamOverlayAnimationsController = this.mDreamOverlayAnimationsController;
        dreamOverlayAnimationsController.view = view;
        DreamOverlayAnimationsController$init$1 dreamOverlayAnimationsController$init$1 = new DreamOverlayAnimationsController$init$1(dreamOverlayAnimationsController, null);
        CoroutineContext coroutineContext = RepeatWhenAttachedKt.MAIN_DISPATCHER_SINGLETON;
        dreamOverlayAnimationsController.mLifecycleFlowHandle = RepeatWhenAttachedKt.repeatWhenAttached(view, EmptyCoroutineContext.INSTANCE, dreamOverlayAnimationsController$init$1);
        this.mLowLightTransitionCoordinator.getClass();
    }

    /* JADX WARN: Type inference failed for: r9v0, types: [com.android.systemui.dreams.DreamOverlayContainerViewController$$ExternalSyntheticLambda1] */
    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        int i = 1;
        final int i2 = 0;
        this.mWakingUpFromSwipe = false;
        this.mJitterStartTimeMillis = System.currentTimeMillis();
        this.mHandler.postDelayed(new DreamOverlayContainerViewController$$ExternalSyntheticLambda0(this), this.mBurnInProtectionUpdateInterval);
        PrimaryBouncerCallbackInteractor primaryBouncerCallbackInteractor = this.mPrimaryBouncerCallbackInteractor;
        ArrayList arrayList = primaryBouncerCallbackInteractor.expansionCallbacks;
        AnonymousClass2 anonymousClass2 = this.mBouncerExpansionCallback;
        if (!arrayList.contains(anonymousClass2)) {
            primaryBouncerCallbackInteractor.expansionCallbacks.add(anonymousClass2);
        }
        BouncerlessScrimController bouncerlessScrimController = this.mBouncerlessScrimController;
        bouncerlessScrimController.mExecutor.execute(new BouncerlessScrimController$$ExternalSyntheticLambda4(bouncerlessScrimController, this.mBouncerlessExpansionCallback, i));
        Region obtain = Region.obtain();
        ((DreamOverlayContainerView) this.mView).getRootSurfaceControl().setTouchableRegion(obtain);
        obtain.recycle();
        View view = this.mView;
        SceneKey sceneKey = Scenes.Bouncer;
        KeyguardState keyguardState = KeyguardState.PRIMARY_BOUNCER;
        KeyguardTransitionInteractor keyguardTransitionInteractor = this.mKeyguardTransitionInteractor;
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(JavaAdapterKt.combineFlows(keyguardTransitionInteractor.isFinishedIn(keyguardState), keyguardTransitionInteractor.isFinishedIn$1(KeyguardState.ALTERNATE_BOUNCER), ((ShadeInteractorImpl) this.mShadeInteractor).baseShadeInteractor.isAnyExpanded(), this.mCommunalInteractor.isCommunalShowing, new Function4() { // from class: com.android.systemui.dreams.DreamOverlayContainerViewController$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                Boolean bool = (Boolean) obj2;
                Boolean bool2 = (Boolean) obj3;
                Boolean bool3 = (Boolean) obj4;
                DreamOverlayContainerViewController dreamOverlayContainerViewController = DreamOverlayContainerViewController.this;
                dreamOverlayContainerViewController.getClass();
                boolean z = true;
                boolean z2 = ((Boolean) obj).booleanValue() || bool.booleanValue();
                dreamOverlayContainerViewController.mAnyBouncerShowing = z2;
                if (!z2 && !bool2.booleanValue() && !bool3.booleanValue()) {
                    z = false;
                }
                return Boolean.valueOf(z);
            }
        }));
        final DreamManager dreamManager = this.mDreamManager;
        Objects.requireNonNull(dreamManager);
        this.mFlowHandle = JavaAdapterKt.collectFlow$default(view, distinctUntilChanged, new Consumer() { // from class: com.android.systemui.dreams.DreamOverlayContainerViewController$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                dreamManager.setDreamIsObscured(((Boolean) obj).booleanValue());
            }
        }, this.mBackgroundDispatcher, 16);
        if (this.mStateController.containsState(2)) {
            return;
        }
        boolean z = this.mExitingLowLight;
        DreamOverlayAnimationsController$startEntryAnimations$1 dreamOverlayAnimationsController$startEntryAnimations$1 = new Function0() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController$startEntryAnimations$1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new AnimatorSet();
            }
        };
        final DreamOverlayAnimationsController dreamOverlayAnimationsController = this.mDreamOverlayAnimationsController;
        dreamOverlayAnimationsController.cancelAnimations();
        dreamOverlayAnimationsController$startEntryAnimations$1.getClass();
        AnimatorSet animatorSet = new AnimatorSet();
        Animator[] animatorArr = new Animator[3];
        final View view2 = dreamOverlayAnimationsController.view;
        if (view2 == null) {
            view2 = null;
        }
        float f = dreamOverlayAnimationsController.mDreamBlurRadius;
        Interpolator interpolator = Interpolators.EMPHASIZED_DECELERATE;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f, 0.0f);
        ofFloat.setDuration(dreamOverlayAnimationsController.mDreamInBlurAnimDurationMs);
        ofFloat.setStartDelay(0L);
        ofFloat.setInterpolator(interpolator);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController$blurAnimator$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                DreamOverlayAnimationsController.this.mCurrentBlurRadius = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                DreamOverlayAnimationsController.this.mBlurUtils.applyBlur(view2.getViewRootImpl(), (int) DreamOverlayAnimationsController.this.mCurrentBlurRadius, false);
            }
        });
        animatorArr[0] = ofFloat;
        Interpolator interpolator2 = Interpolators.LINEAR;
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat2.setDuration(dreamOverlayAnimationsController.mDreamInComplicationsAnimDurationMs);
        ofFloat2.setStartDelay(0L);
        ofFloat2.setInterpolator(interpolator2);
        ofFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController$alphaAnimator$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(final ValueAnimator valueAnimator) {
                switch (i2) {
                    case 0:
                        final DreamOverlayAnimationsController dreamOverlayAnimationsController2 = dreamOverlayAnimationsController;
                        final int i3 = 0;
                        ComplicationLayoutParams.iteratePositions(new Consumer() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController$alphaAnimator$1$1.1
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                switch (i3) {
                                    case 0:
                                        DreamOverlayAnimationsController.access$setElementsAlphaAtPosition(dreamOverlayAnimationsController2, ((Float) valueAnimator.getAnimatedValue()).floatValue(), ((Number) obj).intValue(), false);
                                        break;
                                    default:
                                        DreamOverlayAnimationsController.access$setElementsTranslationYAtPosition(dreamOverlayAnimationsController2, ((Float) valueAnimator.getAnimatedValue()).floatValue(), ((Number) obj).intValue());
                                        break;
                                }
                            }
                        });
                        break;
                    default:
                        final DreamOverlayAnimationsController dreamOverlayAnimationsController3 = dreamOverlayAnimationsController;
                        final int i4 = 1;
                        ComplicationLayoutParams.iteratePositions(new Consumer() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController$alphaAnimator$1$1.1
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                switch (i4) {
                                    case 0:
                                        DreamOverlayAnimationsController.access$setElementsAlphaAtPosition(dreamOverlayAnimationsController3, ((Float) valueAnimator.getAnimatedValue()).floatValue(), ((Number) obj).intValue(), false);
                                        break;
                                    default:
                                        DreamOverlayAnimationsController.access$setElementsTranslationYAtPosition(dreamOverlayAnimationsController3, ((Float) valueAnimator.getAnimatedValue()).floatValue(), ((Number) obj).intValue());
                                        break;
                                }
                            }
                        });
                        break;
                }
            }
        });
        animatorArr[1] = ofFloat2;
        final int i3 = 1;
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(dreamOverlayAnimationsController.mDreamInTranslationYDistance * (z ? -1 : 1), 0.0f);
        ofFloat3.setDuration(dreamOverlayAnimationsController.mDreamInTranslationYDurationMs);
        ofFloat3.setStartDelay(0L);
        ofFloat3.setInterpolator(interpolator);
        ofFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController$alphaAnimator$1$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(final ValueAnimator valueAnimator) {
                switch (i3) {
                    case 0:
                        final DreamOverlayAnimationsController dreamOverlayAnimationsController2 = dreamOverlayAnimationsController;
                        final int i32 = 0;
                        ComplicationLayoutParams.iteratePositions(new Consumer() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController$alphaAnimator$1$1.1
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                switch (i32) {
                                    case 0:
                                        DreamOverlayAnimationsController.access$setElementsAlphaAtPosition(dreamOverlayAnimationsController2, ((Float) valueAnimator.getAnimatedValue()).floatValue(), ((Number) obj).intValue(), false);
                                        break;
                                    default:
                                        DreamOverlayAnimationsController.access$setElementsTranslationYAtPosition(dreamOverlayAnimationsController2, ((Float) valueAnimator.getAnimatedValue()).floatValue(), ((Number) obj).intValue());
                                        break;
                                }
                            }
                        });
                        break;
                    default:
                        final DreamOverlayAnimationsController dreamOverlayAnimationsController3 = dreamOverlayAnimationsController;
                        final int i4 = 1;
                        ComplicationLayoutParams.iteratePositions(new Consumer() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController$alphaAnimator$1$1.1
                            @Override // java.util.function.Consumer
                            public final void accept(Object obj) {
                                switch (i4) {
                                    case 0:
                                        DreamOverlayAnimationsController.access$setElementsAlphaAtPosition(dreamOverlayAnimationsController3, ((Float) valueAnimator.getAnimatedValue()).floatValue(), ((Number) obj).intValue(), false);
                                        break;
                                    default:
                                        DreamOverlayAnimationsController.access$setElementsTranslationYAtPosition(dreamOverlayAnimationsController3, ((Float) valueAnimator.getAnimatedValue()).floatValue(), ((Number) obj).intValue());
                                        break;
                                }
                            }
                        });
                        break;
                }
            }
        });
        animatorArr[2] = ofFloat3;
        animatorSet.playTogether(animatorArr);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnEnd$1
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                switch (i2) {
                    case 0:
                        break;
                    default:
                        Logger.d$default(dreamOverlayAnimationsController.logger, "Dream overlay entry animations canceled.", null, 2, null);
                        break;
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i2) {
                    case 0:
                        DreamOverlayAnimationsController dreamOverlayAnimationsController2 = dreamOverlayAnimationsController;
                        dreamOverlayAnimationsController2.mAnimator = null;
                        dreamOverlayAnimationsController2.mOverlayStateController.modifyState(2, 4);
                        Logger.d$default(dreamOverlayAnimationsController.logger, "Dream overlay entry animations finished.", null, 2, null);
                        break;
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationRepeat(Animator animator) {
                int i4 = i2;
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                int i4 = i2;
            }

            private final void onAnimationCancel$com$android$systemui$dreams$DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnEnd$1(Animator animator) {
            }

            private final void onAnimationEnd$com$android$systemui$dreams$DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnCancel$1(Animator animator) {
            }

            private final void onAnimationRepeat$com$android$systemui$dreams$DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnCancel$1(Animator animator) {
            }

            private final void onAnimationRepeat$com$android$systemui$dreams$DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnEnd$1(Animator animator) {
            }

            private final void onAnimationStart$com$android$systemui$dreams$DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnCancel$1(Animator animator) {
            }

            private final void onAnimationStart$com$android$systemui$dreams$DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnEnd$1(Animator animator) {
            }
        });
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.android.systemui.dreams.DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnEnd$1
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
                switch (i3) {
                    case 0:
                        break;
                    default:
                        Logger.d$default(dreamOverlayAnimationsController.logger, "Dream overlay entry animations canceled.", null, 2, null);
                        break;
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                switch (i3) {
                    case 0:
                        DreamOverlayAnimationsController dreamOverlayAnimationsController2 = dreamOverlayAnimationsController;
                        dreamOverlayAnimationsController2.mAnimator = null;
                        dreamOverlayAnimationsController2.mOverlayStateController.modifyState(2, 4);
                        Logger.d$default(dreamOverlayAnimationsController.logger, "Dream overlay entry animations finished.", null, 2, null);
                        break;
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationRepeat(Animator animator) {
                int i4 = i3;
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                int i4 = i3;
            }

            private final void onAnimationCancel$com$android$systemui$dreams$DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnEnd$1(Animator animator) {
            }

            private final void onAnimationEnd$com$android$systemui$dreams$DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnCancel$1(Animator animator) {
            }

            private final void onAnimationRepeat$com$android$systemui$dreams$DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnCancel$1(Animator animator) {
            }

            private final void onAnimationRepeat$com$android$systemui$dreams$DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnEnd$1(Animator animator) {
            }

            private final void onAnimationStart$com$android$systemui$dreams$DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnCancel$1(Animator animator) {
            }

            private final void onAnimationStart$com$android$systemui$dreams$DreamOverlayAnimationsController$startEntryAnimations$lambda$2$$inlined$doOnEnd$1(Animator animator) {
            }
        });
        animatorSet.start();
        Logger.d$default(dreamOverlayAnimationsController.logger, "Dream overlay entry animations started.", null, 2, null);
        dreamOverlayAnimationsController.mAnimator = animatorSet;
        this.mExitingLowLight = false;
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        RepeatWhenAttachedKt$repeatWhenAttached$1 repeatWhenAttachedKt$repeatWhenAttached$1 = this.mFlowHandle;
        if (repeatWhenAttachedKt$repeatWhenAttached$1 != null) {
            repeatWhenAttachedKt$repeatWhenAttached$1.dispose();
            this.mFlowHandle = null;
        }
        this.mHandler.removeCallbacksAndMessages(null);
        this.mPrimaryBouncerCallbackInteractor.expansionCallbacks.remove(this.mBouncerExpansionCallback);
        BouncerlessScrimController bouncerlessScrimController = this.mBouncerlessScrimController;
        bouncerlessScrimController.mExecutor.execute(new BouncerlessScrimController$$ExternalSyntheticLambda4(bouncerlessScrimController, this.mBouncerlessExpansionCallback, 0));
        TouchInsetManager.TouchInsetSession touchInsetSession = this.mTouchInsetSession;
        touchInsetSession.mExecutor.execute(new TouchInsetManager$TouchInsetSession$$ExternalSyntheticLambda1(touchInsetSession, 0));
        this.mDreamOverlayAnimationsController.cancelAnimations();
    }
}

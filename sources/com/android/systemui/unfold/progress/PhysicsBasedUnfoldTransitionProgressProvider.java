package com.android.systemui.unfold.progress;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Trace;
import android.util.AndroidRuntimeException;
import android.util.FloatProperty;
import android.util.Log;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.dynamicanimation.animation.AnimationHandler;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.unfold.updates.DeviceFoldStateProvider;
import com.android.systemui.unfold.updates.DeviceFoldStateProviderKt;
import com.android.systemui.unfold.updates.FoldStateProvider$FoldUpdatesListener;
import com.google.android.systemui.dagger.DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.text.StringsKt__IndentKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PhysicsBasedUnfoldTransitionProgressProvider implements UnfoldTransitionProgressProvider, FoldStateProvider$FoldUpdatesListener, DynamicAnimation.OnAnimationEndListener {
    public ValueAnimator cannedAnimator;
    public final Interpolator emphasizedInterpolator;
    public final DeviceFoldStateProvider foldStateProvider;
    public boolean isAnimatedCancelRunning;
    public boolean isTransitionRunning;
    public final List listeners;
    public final Handler progressHandler;
    public final DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4 schedulerFactory;
    public final SpringAnimation springAnimation;
    public float transitionProgress;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimationProgressProperty extends FloatProperty {
        public static final AnimationProgressProperty INSTANCE = new AnimationProgressProperty("animation_progress");

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((PhysicsBasedUnfoldTransitionProgressProvider) obj).transitionProgress);
        }

        @Override // android.util.FloatProperty
        public final void setValue(Object obj, float f) {
            ((PhysicsBasedUnfoldTransitionProgressProvider) obj).setTransitionProgress$1(f);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CannedAnimationListener extends AnimatorListenerAdapter {
        public CannedAnimationListener() {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            PhysicsBasedUnfoldTransitionProgressProvider.this.cancelTransition(1.0f, false);
            Trace.endAsyncSection("PhysicsBasedUnfoldTransitionProgressProvider#cannedAnimatorRunning", 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            Trace.beginAsyncSection("PhysicsBasedUnfoldTransitionProgressProvider#cannedAnimatorRunning", 0);
        }
    }

    public PhysicsBasedUnfoldTransitionProgressProvider(Context context, DaggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4 daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4, DeviceFoldStateProvider deviceFoldStateProvider, Handler handler) {
        this.schedulerFactory = daggerSysUIGoogleGlobalRootComponent$SysUIGoogleGlobalRootComponentImpl$SwitchingProvider$4;
        this.foldStateProvider = deviceFoldStateProvider;
        this.progressHandler = handler;
        this.emphasizedInterpolator = AnimationUtils.loadInterpolator(context, R.interpolator.fast_out_extra_slow_in);
        AnimationProgressProperty animationProgressProperty = AnimationProgressProperty.INSTANCE;
        animationProgressProperty.getName();
        SpringAnimation springAnimation = new SpringAnimation(this, new FloatPropertyCompat.AnonymousClass1(animationProgressProperty));
        springAnimation.addEndListener(this);
        this.springAnimation = springAnimation;
        this.listeners = new ArrayList();
        handler.post(new Runnable() { // from class: com.android.systemui.unfold.progress.PhysicsBasedUnfoldTransitionProgressProvider.1
            @Override // java.lang.Runnable
            public final void run() {
                PhysicsBasedUnfoldTransitionProgressProvider physicsBasedUnfoldTransitionProgressProvider = PhysicsBasedUnfoldTransitionProgressProvider.this;
                SpringAnimation springAnimation2 = physicsBasedUnfoldTransitionProgressProvider.springAnimation;
                physicsBasedUnfoldTransitionProgressProvider.schedulerFactory.getClass();
                UnfoldFrameCallbackScheduler unfoldFrameCallbackScheduler = new UnfoldFrameCallbackScheduler();
                AnimationHandler animationHandler = springAnimation2.mAnimationHandler;
                if (animationHandler == null || animationHandler.mScheduler != unfoldFrameCallbackScheduler) {
                    if (springAnimation2.mRunning) {
                        throw new AndroidRuntimeException("Animations are still running and the animationhandler should not be set at this timming");
                    }
                    springAnimation2.mAnimationHandler = new AnimationHandler(unfoldFrameCallbackScheduler);
                }
                PhysicsBasedUnfoldTransitionProgressProvider physicsBasedUnfoldTransitionProgressProvider2 = PhysicsBasedUnfoldTransitionProgressProvider.this;
                physicsBasedUnfoldTransitionProgressProvider2.foldStateProvider.addCallback(physicsBasedUnfoldTransitionProgressProvider2);
                PhysicsBasedUnfoldTransitionProgressProvider.this.foldStateProvider.start();
            }
        });
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void addCallback(Object obj) {
        this.progressHandler.post(new PhysicsBasedUnfoldTransitionProgressProvider$addCallback$1(this, (UnfoldTransitionProgressProvider.TransitionProgressListener) obj, 0));
    }

    public final void assertInProgressThread() {
        Handler handler = this.progressHandler;
        if (handler.getLooper().isCurrentThread()) {
            return;
        }
        Thread thread = handler.getLooper().getThread();
        Thread currentThread = Thread.currentThread();
        throw new IllegalStateException(StringsKt__IndentKt.trimMargin$default("should be called from the progress thread.\n                progressThread=" + thread + " tid=" + thread.getId() + "\n                Thread.currentThread()=" + currentThread + " tid=" + currentThread.getId()).toString());
    }

    public final void cancelTransition(float f, boolean z) {
        assertInProgressThread();
        boolean z2 = this.isTransitionRunning;
        SpringAnimation springAnimation = this.springAnimation;
        if (!z2 || !z) {
            setTransitionProgress$1(f);
            this.isAnimatedCancelRunning = false;
            this.isTransitionRunning = false;
            springAnimation.cancel();
            ValueAnimator valueAnimator = this.cannedAnimator;
            if (valueAnimator != null) {
                valueAnimator.removeAllListeners();
            }
            ValueAnimator valueAnimator2 = this.cannedAnimator;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
            this.cannedAnimator = null;
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((UnfoldTransitionProgressProvider.TransitionProgressListener) it.next()).onTransitionFinished();
            }
            Log.d("PhysicsBasedUnfoldTransitionProgressProvider", "onTransitionFinished");
            return;
        }
        if (f == 1.0f && !this.isAnimatedCancelRunning) {
            Iterator it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                ((UnfoldTransitionProgressProvider.TransitionProgressListener) it2.next()).onTransitionFinishing();
            }
        }
        this.isAnimatedCancelRunning = true;
        assertInProgressThread();
        ValueAnimator valueAnimator3 = this.cannedAnimator;
        if (valueAnimator3 != null) {
            valueAnimator3.cancel();
        }
        this.cannedAnimator = null;
        springAnimation.removeEndListener(this);
        springAnimation.cancel();
        springAnimation.addEndListener(this);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, AnimationProgressProperty.INSTANCE, this.transitionProgress, 1.0f);
        ofFloat.addListener(new CannedAnimationListener());
        ofFloat.setDuration((long) ((1.0f - this.transitionProgress) * 1000.0f));
        ofFloat.setInterpolator(this.emphasizedInterpolator);
        ofFloat.start();
        this.cannedAnimator = ofFloat;
    }

    @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
    public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
        if (this.isAnimatedCancelRunning) {
            cancelTransition(f, false);
        }
    }

    @Override // com.android.systemui.unfold.updates.FoldStateProvider$FoldUpdatesListener
    public final void onFoldUpdate(int i) {
        assertInProgressThread();
        if (i != 1) {
            if (i == 2 || i == 3) {
                if (this.isTransitionRunning) {
                    cancelTransition(1.0f, true);
                }
            } else if (i == 4) {
                cancelTransition(0.0f, false);
            }
        } else if (!this.isTransitionRunning) {
            startTransition(1.0f);
        } else if (this.isAnimatedCancelRunning) {
            this.isAnimatedCancelRunning = false;
            this.springAnimation.animateToFinalPosition(1.0f);
            ValueAnimator valueAnimator = this.cannedAnimator;
            if (valueAnimator != null) {
                valueAnimator.removeAllListeners();
            }
            ValueAnimator valueAnimator2 = this.cannedAnimator;
            if (valueAnimator2 != null) {
                valueAnimator2.cancel();
            }
            this.cannedAnimator = null;
        }
        Log.d("PhysicsBasedUnfoldTransitionProgressProvider", "onFoldUpdate = ".concat(DeviceFoldStateProviderKt.name(i)));
        Trace.setCounter("fold_update", i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001a, code lost:
    
        if (r3 > 1.0f) goto L9;
     */
    @Override // com.android.systemui.unfold.updates.FoldStateProvider$FoldUpdatesListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onHingeAngleUpdate(float r3) {
        /*
            r2 = this;
            r2.assertInProgressThread()
            boolean r0 = r2.isTransitionRunning
            if (r0 == 0) goto L22
            boolean r0 = r2.isAnimatedCancelRunning
            if (r0 == 0) goto Lc
            goto L22
        Lc:
            r0 = 1126498304(0x43250000, float:165.0)
            float r3 = r3 / r0
            r0 = 0
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 >= 0) goto L16
        L14:
            r3 = r0
            goto L1d
        L16:
            r0 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r3 > r0 ? 1 : (r3 == r0 ? 0 : -1))
            if (r1 <= 0) goto L1d
            goto L14
        L1d:
            androidx.dynamicanimation.animation.SpringAnimation r2 = r2.springAnimation
            r2.animateToFinalPosition(r3)
        L22:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.unfold.progress.PhysicsBasedUnfoldTransitionProgressProvider.onHingeAngleUpdate(float):void");
    }

    @Override // com.android.systemui.unfold.updates.FoldStateProvider$FoldUpdatesListener
    public final void onUnfoldedScreenAvailable() {
        Integer num;
        startTransition(0.0f);
        DeviceFoldStateProvider deviceFoldStateProvider = this.foldStateProvider;
        if (deviceFoldStateProvider.isFolded) {
            return;
        }
        Integer num2 = deviceFoldStateProvider.lastFoldUpdate;
        if ((num2 != null && num2.intValue() == 3) || ((num = deviceFoldStateProvider.lastFoldUpdate) != null && num.intValue() == 2)) {
            cancelTransition(1.0f, true);
        }
    }

    @Override // com.android.systemui.unfold.util.CallbackController
    public final void removeCallback(Object obj) {
        this.progressHandler.post(new PhysicsBasedUnfoldTransitionProgressProvider$addCallback$1(this, (UnfoldTransitionProgressProvider.TransitionProgressListener) obj, 1));
    }

    public final void setTransitionProgress$1(float f) {
        assertInProgressThread();
        if (this.isTransitionRunning) {
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((UnfoldTransitionProgressProvider.TransitionProgressListener) it.next()).onTransitionProgress(f);
            }
        }
        this.transitionProgress = f;
    }

    public final void startTransition(float f) {
        assertInProgressThread();
        if (!this.isTransitionRunning) {
            Trace.beginSection("PhysicsBasedUnfoldTransitionProgressProvider#onStartTransition");
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((UnfoldTransitionProgressProvider.TransitionProgressListener) it.next()).onTransitionStarted();
            }
            Trace.endSection();
            this.isTransitionRunning = true;
            Log.d("PhysicsBasedUnfoldTransitionProgressProvider", "onTransitionStarted");
        }
        SpringForce springForce = new SpringForce();
        springForce.mFinalPosition = f;
        springForce.setDampingRatio(1.0f);
        springForce.setStiffness(600.0f);
        SpringAnimation springAnimation = this.springAnimation;
        springAnimation.mSpring = springForce;
        springAnimation.setMinimumVisibleChange(0.001f);
        springAnimation.mValue = f;
        springAnimation.mStartValueIsSet = true;
        springAnimation.mMinValue = 0.0f;
        springAnimation.mMaxValue = 1.0f;
        springAnimation.start();
    }
}

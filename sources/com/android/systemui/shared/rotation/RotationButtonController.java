package com.android.systemui.shared.rotation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemProperties;
import android.provider.Settings;
import android.view.IRotationWatcher;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.core.view.OneShotPreDrawListener;
import com.android.internal.logging.UiEventLogger;
import com.android.internal.logging.UiEventLoggerImpl;
import com.android.internal.view.RotationPolicy;
import com.android.systemui.navigationbar.views.NavigationBarView$$ExternalSyntheticLambda2;
import com.android.systemui.shared.recents.utilities.ViewRippler;
import com.android.systemui.shared.rotation.RotationButtonController;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.shared.system.TaskStackChangeListener;
import com.android.wm.shell.R;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RotationButtonController {
    public final AccessibilityManager mAccessibilityManager;
    public Executor mBgExecutor;
    public final Context mContext;
    public final int mDarkIconColor;
    public boolean mDocked;
    public boolean mHomeRotationEnabled;
    public boolean mHoveringRotationSuggestion;
    public boolean mIsNavigationBarShowing;
    public boolean mIsRecentsAnimationRunning;
    public int mLastRotationSuggestion;
    public final int mLightIconColor;
    public int mNavBarMode;
    public boolean mPendingRotationSuggestion;
    public Animator mRotateHideAnimator;
    public FloatingRotationButton mRotationButton;
    public boolean mSkipOverrideUserLockPrefsOnce;
    public final NavigationBarView$$ExternalSyntheticLambda2 mWindowRotationProvider;
    public static final boolean OEM_DISALLOW_ROTATION_IN_SUW = SystemProperties.getBoolean("ro.setupwizard.rotation_locked", false);
    public static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public final Handler mMainThreadHandler = new Handler(Looper.getMainLooper());
    public final UiEventLogger mUiEventLogger = new UiEventLoggerImpl();
    public final ViewRippler mViewRippler = new ViewRippler();
    public boolean mListenersRegistered = false;
    public final boolean mRotationWatcherRegistered = false;
    public int mBehavior = 1;
    public final RotationButtonController$$ExternalSyntheticLambda0 mRemoveRotationProposal = new RotationButtonController$$ExternalSyntheticLambda0(0, this);
    public final RotationButtonController$$ExternalSyntheticLambda0 mCancelPendingRotationProposal = new RotationButtonController$$ExternalSyntheticLambda0(1, this);
    public final AnonymousClass1 mDockedReceiver = new BroadcastReceiver() { // from class: com.android.systemui.shared.rotation.RotationButtonController.1
        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            RotationButtonController rotationButtonController = RotationButtonController.this;
            rotationButtonController.getClass();
            if (intent == null) {
                return;
            }
            rotationButtonController.mDocked = intent.getIntExtra("android.intent.extra.DOCK_STATE", 0) != 0;
        }
    };
    public final AnonymousClass2 mRotationWatcher = new AnonymousClass2();
    public final int mIconCcwStart0ResId = R.drawable.ic_sysbar_rotate_button_ccw_start_0;
    public final int mIconCcwStart90ResId = R.drawable.ic_sysbar_rotate_button_ccw_start_90;
    public final int mIconCwStart0ResId = R.drawable.ic_sysbar_rotate_button_cw_start_0;
    public final int mIconCwStart90ResId = R.drawable.ic_sysbar_rotate_button_cw_start_90;
    public int mIconResId = R.drawable.ic_sysbar_rotate_button_ccw_start_90;
    public final TaskStackListenerImpl mTaskStackListener = new TaskStackListenerImpl();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.shared.rotation.RotationButtonController$2, reason: invalid class name */
    public final class AnonymousClass2 extends IRotationWatcher.Stub {
        public AnonymousClass2() {
        }

        public final void onRotationChanged(final int i) {
            final Boolean isRotationLocked = RotationPolicyUtil.isRotationLocked(RotationButtonController.this.mContext);
            RotationButtonController.this.mMainThreadHandler.postAtFrontOfQueue(new Runnable() { // from class: com.android.systemui.shared.rotation.RotationButtonController$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    RotationButtonController.AnonymousClass2 anonymousClass2 = RotationButtonController.AnonymousClass2.this;
                    RotationButtonController.this.onRotationWatcherChanged(i, isRotationLocked);
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    enum RotationButtonEvent implements UiEventLogger.UiEventEnum {
        ROTATION_SUGGESTION_SHOWN(206),
        ROTATION_SUGGESTION_ACCEPTED(207);

        private final int mId;

        RotationButtonEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TaskStackListenerImpl implements TaskStackChangeListener {
        public TaskStackListenerImpl() {
        }

        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public final void onActivityRequestedOrientationChanged(final int i) {
            RotationButtonController.this.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.shared.rotation.RotationButtonController$TaskStackListenerImpl$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    final RotationButtonController.TaskStackListenerImpl taskStackListenerImpl = RotationButtonController.TaskStackListenerImpl.this;
                    final int i2 = i;
                    taskStackListenerImpl.getClass();
                    Optional.ofNullable(ActivityManagerWrapper.sInstance).map(new RotationButtonController$TaskStackListenerImpl$$ExternalSyntheticLambda0()).ifPresent(new Consumer() { // from class: com.android.systemui.shared.rotation.RotationButtonController$TaskStackListenerImpl$$ExternalSyntheticLambda1
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            RotationButtonController.TaskStackListenerImpl taskStackListenerImpl2 = RotationButtonController.TaskStackListenerImpl.this;
                            int i3 = i2;
                            taskStackListenerImpl2.getClass();
                            if (((ActivityManager.RunningTaskInfo) obj).id == i3) {
                                RotationButtonController.this.mMainThreadHandler.post(new RotationButtonController$$ExternalSyntheticLambda0(4, taskStackListenerImpl2));
                            }
                        }
                    });
                }
            });
        }

        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public final void onTaskMovedToFront() {
            RotationButtonController.this.setRotateSuggestionButtonState(false, false);
        }

        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public final void onTaskRemoved() {
            RotationButtonController.this.setRotateSuggestionButtonState(false, false);
        }

        @Override // com.android.systemui.shared.system.TaskStackChangeListener
        public final void onTaskStackChanged() {
            RotationButtonController.this.setRotateSuggestionButtonState(false, false);
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.android.systemui.shared.rotation.RotationButtonController$1] */
    public RotationButtonController(Context context, int i, int i2, NavigationBarView$$ExternalSyntheticLambda2 navigationBarView$$ExternalSyntheticLambda2) {
        this.mContext = context;
        this.mLightIconColor = i;
        this.mDarkIconColor = i2;
        this.mAccessibilityManager = AccessibilityManager.getInstance(context);
        this.mWindowRotationProvider = navigationBarView$$ExternalSyntheticLambda2;
        this.mBgExecutor = context.getMainExecutor();
    }

    public boolean canShowRotationButton() {
        return this.mIsNavigationBarShowing || this.mBehavior == 1 || QuickStepContract.isGesturalMode(this.mNavBarMode);
    }

    public final void onRotationWatcherChanged(int i, Boolean bool) {
        if (this.mListenersRegistered && bool != null) {
            if (bool.booleanValue() || this.mRotationButton.mIsShowing) {
                if (this.mSkipOverrideUserLockPrefsOnce) {
                    this.mSkipOverrideUserLockPrefsOnce = false;
                } else if (i == 0 && bool.booleanValue() && !this.mDocked) {
                    RotationPolicy.setRotationLockAtAngle(this.mContext, true, i, "RotationButtonController#onRotationWatcherChanged");
                }
                setRotateSuggestionButtonState(false, true);
            }
        }
    }

    public final void rescheduleRotationTimeout(boolean z) {
        Animator animator;
        if (!z || (((animator = this.mRotateHideAnimator) == null || !animator.isRunning()) && this.mRotationButton.mIsShowing)) {
            Handler handler = this.mMainThreadHandler;
            RotationButtonController$$ExternalSyntheticLambda0 rotationButtonController$$ExternalSyntheticLambda0 = this.mRemoveRotationProposal;
            handler.removeCallbacks(rotationButtonController$$ExternalSyntheticLambda0);
            handler.postDelayed(rotationButtonController$$ExternalSyntheticLambda0, this.mAccessibilityManager.getRecommendedTimeoutMillis(this.mHoveringRotationSuggestion ? 16000 : 5000, 4));
        }
    }

    public final void setRotateSuggestionButtonState(boolean z, boolean z2) {
        FloatingRotationButton floatingRotationButton;
        FloatingRotationButtonView floatingRotationButtonView;
        AnimatedVectorDrawable animatedVectorDrawable;
        if ((!z && !this.mRotationButton.mIsShowing) || (floatingRotationButtonView = (floatingRotationButton = this.mRotationButton).mKeyButtonView) == null || (animatedVectorDrawable = floatingRotationButton.mAnimatedDrawable) == null) {
            return;
        }
        this.mPendingRotationSuggestion = false;
        this.mMainThreadHandler.removeCallbacks(this.mCancelPendingRotationProposal);
        ViewRippler viewRippler = this.mViewRippler;
        if (!z) {
            View view = viewRippler.mRoot;
            if (view != null) {
                view.removeCallbacks(viewRippler.mRipple);
            }
            if (z2) {
                Animator animator = this.mRotateHideAnimator;
                if (animator != null && animator.isRunning()) {
                    this.mRotateHideAnimator.pause();
                }
                this.mRotationButton.hide();
                return;
            }
            Animator animator2 = this.mRotateHideAnimator;
            if (animator2 == null || !animator2.isRunning()) {
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(floatingRotationButtonView, "alpha", 0.0f);
                ofFloat.setDuration(100L);
                ofFloat.setInterpolator(LINEAR_INTERPOLATOR);
                ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.shared.rotation.RotationButtonController.3
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator3) {
                        RotationButtonController.this.mRotationButton.hide();
                    }
                });
                this.mRotateHideAnimator = ofFloat;
                ofFloat.start();
                return;
            }
            return;
        }
        Animator animator3 = this.mRotateHideAnimator;
        if (animator3 != null && animator3.isRunning()) {
            this.mRotateHideAnimator.cancel();
        }
        this.mRotateHideAnimator = null;
        floatingRotationButtonView.setAlpha(1.0f);
        animatedVectorDrawable.reset();
        animatedVectorDrawable.start();
        if (Settings.Secure.getInt(this.mContext.getContentResolver(), "num_rotation_suggestions_accepted", 0) < 3) {
            View view2 = viewRippler.mRoot;
            ViewRippler.AnonymousClass1 anonymousClass1 = viewRippler.mRipple;
            if (view2 != null) {
                view2.removeCallbacks(anonymousClass1);
            }
            viewRippler.mRoot = floatingRotationButtonView;
            floatingRotationButtonView.postOnAnimationDelayed(anonymousClass1, 50L);
            viewRippler.mRoot.postOnAnimationDelayed(anonymousClass1, 2000L);
            viewRippler.mRoot.postOnAnimationDelayed(anonymousClass1, 4000L);
            viewRippler.mRoot.postOnAnimationDelayed(anonymousClass1, 6000L);
            viewRippler.mRoot.postOnAnimationDelayed(anonymousClass1, 8000L);
        }
        FloatingRotationButton floatingRotationButton2 = this.mRotationButton;
        if (floatingRotationButton2.mIsShowing) {
            return;
        }
        floatingRotationButton2.mIsShowing = true;
        floatingRotationButton2.mWindowManager.addView(floatingRotationButton2.mKeyButtonContainer, floatingRotationButton2.adjustViewPositionAndCreateLayoutParams());
        AnimatedVectorDrawable animatedVectorDrawable2 = floatingRotationButton2.mAnimatedDrawable;
        if (animatedVectorDrawable2 != null) {
            animatedVectorDrawable2.reset();
            floatingRotationButton2.mAnimatedDrawable.start();
        }
        FloatingRotationButton$$ExternalSyntheticLambda0 floatingRotationButton$$ExternalSyntheticLambda0 = new FloatingRotationButton$$ExternalSyntheticLambda0(floatingRotationButton2, 1);
        FloatingRotationButtonView floatingRotationButtonView2 = floatingRotationButton2.mKeyButtonView;
        if (floatingRotationButtonView2 == null) {
            throw new NullPointerException("view == null");
        }
        OneShotPreDrawListener oneShotPreDrawListener = new OneShotPreDrawListener(floatingRotationButtonView2, floatingRotationButton$$ExternalSyntheticLambda0);
        floatingRotationButtonView2.getViewTreeObserver().addOnPreDrawListener(oneShotPreDrawListener);
        floatingRotationButtonView2.addOnAttachStateChangeListener(oneShotPreDrawListener);
    }

    public final void showAndLogRotationSuggestion() {
        setRotateSuggestionButtonState(true, false);
        rescheduleRotationTimeout(false);
        this.mUiEventLogger.log(RotationButtonEvent.ROTATION_SUGGESTION_SHOWN);
    }
}

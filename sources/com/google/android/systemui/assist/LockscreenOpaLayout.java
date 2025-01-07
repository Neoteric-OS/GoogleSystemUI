package com.google.android.systemui.assist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import com.android.app.animation.Interpolators;
import com.android.wm.shell.R;
import com.google.android.systemui.elmyra.feedback.FeedbackEffect;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class LockscreenOpaLayout extends FrameLayout implements FeedbackEffect {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Interpolator INTERPOLATOR_5_100;
    public final ArrayList mAnimatedViews;
    public View mBlue;
    public AnimatorSet mCannedAnimatorSet;
    public final ArraySet mCurrentAnimators;
    public AnimatorSet mGestureAnimatorSet;
    public int mGestureState;
    public View mGreen;
    public AnimatorSet mLineAnimatorSet;
    public View mRed;
    public Resources mResources;
    public View mYellow;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.assist.LockscreenOpaLayout$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimatorListenerAdapter {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass1(int i, Object obj) {
            this.$r8$classId = i;
            this.this$0 = obj;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            switch (this.$r8$classId) {
                case 0:
                    LockscreenOpaLayout lockscreenOpaLayout = (LockscreenOpaLayout) this.this$0;
                    lockscreenOpaLayout.mGestureState = 1;
                    lockscreenOpaLayout.mGestureAnimatorSet = LockscreenOpaLayout.m912$$Nest$mgetLineAnimatorSet(lockscreenOpaLayout);
                    ((LockscreenOpaLayout) this.this$0).mGestureAnimatorSet.setCurrentPlayTime(0L);
                    break;
                case 1:
                    ((LockscreenOpaLayout) this.this$0).mCurrentAnimators.clear();
                    ((LockscreenOpaLayout) this.this$0).skipToStartingValue();
                    LockscreenOpaLayout lockscreenOpaLayout2 = (LockscreenOpaLayout) this.this$0;
                    lockscreenOpaLayout2.mGestureState = 0;
                    lockscreenOpaLayout2.mGestureAnimatorSet = null;
                    break;
                case 2:
                    ((LockscreenOpaLayout) this.this$0).mCurrentAnimators.clear();
                    LockscreenOpaLayout lockscreenOpaLayout3 = (LockscreenOpaLayout) this.this$0;
                    lockscreenOpaLayout3.mGestureAnimatorSet = null;
                    lockscreenOpaLayout3.mGestureState = 0;
                    lockscreenOpaLayout3.skipToStartingValue();
                    break;
                case 3:
                    LockscreenOpaLayout lockscreenOpaLayout4 = (LockscreenOpaLayout) this.this$0;
                    int i = LockscreenOpaLayout.$r8$clinit;
                    lockscreenOpaLayout4.startRetractAnimation();
                    break;
                case 4:
                    LockscreenOpaLayout lockscreenOpaLayout5 = (LockscreenOpaLayout) this.this$0;
                    lockscreenOpaLayout5.mGestureAnimatorSet = LockscreenOpaLayout.m912$$Nest$mgetLineAnimatorSet(lockscreenOpaLayout5);
                    ((LockscreenOpaLayout) this.this$0).mGestureAnimatorSet.removeAllListeners();
                    ((LockscreenOpaLayout) this.this$0).mGestureAnimatorSet.addListener(new AnonymousClass1(6, this));
                    ((LockscreenOpaLayout) this.this$0).mGestureAnimatorSet.end();
                    break;
                case 5:
                    LockscreenOpaLayout.m913$$Nest$mstartCollapseAnimation((LockscreenOpaLayout) this.this$0);
                    break;
                default:
                    LockscreenOpaLayout.m913$$Nest$mstartCollapseAnimation((LockscreenOpaLayout) ((AnonymousClass1) this.this$0).this$0);
                    break;
            }
        }
    }

    /* renamed from: -$$Nest$mgetLineAnimatorSet, reason: not valid java name */
    public static AnimatorSet m912$$Nest$mgetLineAnimatorSet(LockscreenOpaLayout lockscreenOpaLayout) {
        AnimatorSet animatorSet = lockscreenOpaLayout.mLineAnimatorSet;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            lockscreenOpaLayout.mLineAnimatorSet.cancel();
            return lockscreenOpaLayout.mLineAnimatorSet;
        }
        AnimatorSet animatorSet2 = new AnimatorSet();
        lockscreenOpaLayout.mLineAnimatorSet = animatorSet2;
        View view = lockscreenOpaLayout.mRed;
        Interpolator interpolator = lockscreenOpaLayout.INTERPOLATOR_5_100;
        Resources resources = lockscreenOpaLayout.mResources;
        Interpolator interpolator2 = OpaUtils.INTERPOLATOR_40_40;
        animatorSet2.play(OpaUtils.getTranslationObjectAnimatorX(view, interpolator, -resources.getDimensionPixelOffset(R.dimen.opa_lockscreen_translation_ry), lockscreenOpaLayout.mRed.getX(), 366)).with(OpaUtils.getTranslationObjectAnimatorX(lockscreenOpaLayout.mYellow, lockscreenOpaLayout.INTERPOLATOR_5_100, lockscreenOpaLayout.mResources.getDimensionPixelOffset(R.dimen.opa_lockscreen_translation_ry), lockscreenOpaLayout.mYellow.getX(), 366)).with(OpaUtils.getTranslationObjectAnimatorX(lockscreenOpaLayout.mGreen, lockscreenOpaLayout.INTERPOLATOR_5_100, lockscreenOpaLayout.mResources.getDimensionPixelOffset(R.dimen.opa_lockscreen_translation_bg), lockscreenOpaLayout.mGreen.getX(), 366)).with(OpaUtils.getTranslationObjectAnimatorX(lockscreenOpaLayout.mBlue, lockscreenOpaLayout.INTERPOLATOR_5_100, -lockscreenOpaLayout.mResources.getDimensionPixelOffset(R.dimen.opa_lockscreen_translation_bg), lockscreenOpaLayout.mBlue.getX(), 366));
        return lockscreenOpaLayout.mLineAnimatorSet;
    }

    /* renamed from: -$$Nest$mstartCollapseAnimation, reason: not valid java name */
    public static void m913$$Nest$mstartCollapseAnimation(LockscreenOpaLayout lockscreenOpaLayout) {
        if (!lockscreenOpaLayout.isAttachedToWindow()) {
            lockscreenOpaLayout.skipToStartingValue();
            return;
        }
        lockscreenOpaLayout.mCurrentAnimators.clear();
        ArraySet arraySet = lockscreenOpaLayout.mCurrentAnimators;
        ArraySet arraySet2 = new ArraySet();
        View view = lockscreenOpaLayout.mRed;
        Interpolator interpolator = OpaUtils.INTERPOLATOR_40_OUT;
        arraySet2.add(OpaUtils.getTranslationAnimatorX(133, view, interpolator));
        arraySet2.add(OpaUtils.getTranslationAnimatorX(150, lockscreenOpaLayout.mBlue, interpolator));
        arraySet2.add(OpaUtils.getTranslationAnimatorX(133, lockscreenOpaLayout.mYellow, interpolator));
        arraySet2.add(OpaUtils.getTranslationAnimatorX(150, lockscreenOpaLayout.mGreen, interpolator));
        OpaUtils.getLongestAnim(arraySet2).addListener(new AnonymousClass1(2, lockscreenOpaLayout));
        arraySet.addAll(arraySet2);
        ArraySet arraySet3 = lockscreenOpaLayout.mCurrentAnimators;
        for (int size = arraySet3.size() - 1; size >= 0; size--) {
            ((Animator) arraySet3.valueAt(size)).start();
        }
        lockscreenOpaLayout.mGestureState = 2;
    }

    public LockscreenOpaLayout(Context context) {
        super(context);
        this.INTERPOLATOR_5_100 = new PathInterpolator(1.0f, 0.0f, 0.95f, 1.0f);
        this.mGestureState = 0;
        this.mCurrentAnimators = new ArraySet();
        this.mAnimatedViews = new ArrayList();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.mResources = getResources();
        this.mBlue = findViewById(R.id.blue);
        this.mRed = findViewById(R.id.red);
        this.mYellow = findViewById(R.id.yellow);
        this.mGreen = findViewById(R.id.green);
        this.mAnimatedViews.add(this.mBlue);
        this.mAnimatedViews.add(this.mRed);
        this.mAnimatedViews.add(this.mYellow);
        this.mAnimatedViews.add(this.mGreen);
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onProgress(int i, float f) {
        AnimatorSet animatorSet;
        int i2 = 0;
        int i3 = this.mGestureState;
        if (i3 == 2) {
            return;
        }
        if (i3 == 4) {
            if (!this.mCurrentAnimators.isEmpty()) {
                for (int size = this.mCurrentAnimators.size() - 1; size >= 0; size--) {
                    Animator animator = (Animator) this.mCurrentAnimators.valueAt(size);
                    animator.removeAllListeners();
                    animator.end();
                }
                this.mCurrentAnimators.clear();
            }
            this.mGestureState = 0;
        }
        if (f == 0.0f) {
            this.mGestureState = 0;
            return;
        }
        long max = Math.max(0L, ((long) (f * 533.0f)) - 167);
        int i4 = this.mGestureState;
        if (i4 != 0) {
            if (i4 == 1) {
                this.mGestureAnimatorSet.setCurrentPlayTime(max);
                return;
            }
            if (i4 == 3 && max >= 167) {
                this.mGestureAnimatorSet.end();
                if (this.mGestureState == 1) {
                    this.mGestureAnimatorSet.setCurrentPlayTime(max);
                    return;
                }
                return;
            }
            return;
        }
        if (!isAttachedToWindow()) {
            skipToStartingValue();
            return;
        }
        skipToStartingValue();
        this.mGestureState = 3;
        AnimatorSet animatorSet2 = this.mCannedAnimatorSet;
        if (animatorSet2 != null) {
            animatorSet2.removeAllListeners();
            this.mCannedAnimatorSet.cancel();
            animatorSet = this.mCannedAnimatorSet;
        } else {
            this.mCannedAnimatorSet = new AnimatorSet();
            View view = this.mRed;
            Interpolator interpolator = OpaUtils.INTERPOLATOR_40_40;
            ObjectAnimator translationObjectAnimatorX = OpaUtils.getTranslationObjectAnimatorX(view, interpolator, -this.mResources.getDimensionPixelOffset(R.dimen.opa_lockscreen_canned_ry), this.mRed.getX(), 83);
            translationObjectAnimatorX.setStartDelay(17L);
            ObjectAnimator translationObjectAnimatorX2 = OpaUtils.getTranslationObjectAnimatorX(this.mYellow, interpolator, this.mResources.getDimensionPixelOffset(R.dimen.opa_lockscreen_canned_ry), this.mYellow.getX(), 83);
            translationObjectAnimatorX2.setStartDelay(17L);
            AnimatorSet.Builder with = this.mCannedAnimatorSet.play(translationObjectAnimatorX).with(translationObjectAnimatorX2).with(OpaUtils.getTranslationObjectAnimatorX(this.mBlue, interpolator, -this.mResources.getDimensionPixelOffset(R.dimen.opa_lockscreen_canned_bg), this.mBlue.getX(), 167)).with(OpaUtils.getTranslationObjectAnimatorX(this.mGreen, interpolator, this.mResources.getDimensionPixelOffset(R.dimen.opa_lockscreen_canned_bg), this.mGreen.getX(), 167));
            View view2 = this.mRed;
            Interpolator interpolator2 = Interpolators.LINEAR;
            with.with(OpaUtils.getAlphaObjectAnimator(130, view2, interpolator2)).with(OpaUtils.getAlphaObjectAnimator(130, this.mYellow, interpolator2)).with(OpaUtils.getAlphaObjectAnimator(113, this.mBlue, interpolator2)).with(OpaUtils.getAlphaObjectAnimator(113, this.mGreen, interpolator2));
            animatorSet = this.mCannedAnimatorSet;
        }
        this.mGestureAnimatorSet = animatorSet;
        animatorSet.addListener(new AnonymousClass1(i2, this));
        this.mGestureAnimatorSet.start();
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onRelease() {
        int i = this.mGestureState;
        if (i == 2 || i == 4) {
            return;
        }
        if (i != 3) {
            if (i == 1) {
                startRetractAnimation();
            }
        } else if (this.mGestureAnimatorSet.isRunning()) {
            this.mGestureAnimatorSet.removeAllListeners();
            this.mGestureAnimatorSet.addListener(new AnonymousClass1(3, this));
        } else {
            this.mGestureState = 4;
            startRetractAnimation();
        }
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        int i = this.mGestureState;
        if (i == 4 || i == 2) {
            return;
        }
        if (i == 3) {
            this.mGestureState = 2;
            this.mGestureAnimatorSet.removeAllListeners();
            this.mGestureAnimatorSet.addListener(new AnonymousClass1(4, this));
            return;
        }
        AnimatorSet animatorSet = this.mGestureAnimatorSet;
        if (animatorSet != null) {
            this.mGestureState = 2;
            animatorSet.removeAllListeners();
            this.mGestureAnimatorSet.addListener(new AnonymousClass1(5, this));
            if (this.mGestureAnimatorSet.isStarted()) {
                return;
            }
            this.mGestureAnimatorSet.start();
        }
    }

    public final void skipToStartingValue() {
        int size = this.mAnimatedViews.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.mAnimatedViews.get(i);
            view.setAlpha(0.0f);
            view.setTranslationX(0.0f);
        }
    }

    public final void startRetractAnimation() {
        if (!isAttachedToWindow()) {
            skipToStartingValue();
            return;
        }
        AnimatorSet animatorSet = this.mGestureAnimatorSet;
        if (animatorSet != null) {
            animatorSet.removeAllListeners();
            this.mGestureAnimatorSet.cancel();
        }
        this.mCurrentAnimators.clear();
        ArraySet arraySet = this.mCurrentAnimators;
        ArraySet arraySet2 = new ArraySet();
        View view = this.mRed;
        Interpolator interpolator = OpaUtils.INTERPOLATOR_40_OUT;
        arraySet2.add(OpaUtils.getTranslationAnimatorX(190, view, interpolator));
        arraySet2.add(OpaUtils.getTranslationAnimatorX(190, this.mBlue, interpolator));
        arraySet2.add(OpaUtils.getTranslationAnimatorX(190, this.mGreen, interpolator));
        arraySet2.add(OpaUtils.getTranslationAnimatorX(190, this.mYellow, interpolator));
        OpaUtils.getLongestAnim(arraySet2).addListener(new AnonymousClass1(1, this));
        arraySet.addAll(arraySet2);
        ArraySet arraySet3 = this.mCurrentAnimators;
        for (int size = arraySet3.size() - 1; size >= 0; size--) {
            ((Animator) arraySet3.valueAt(size)).start();
        }
        this.mGestureState = 4;
    }

    public LockscreenOpaLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.INTERPOLATOR_5_100 = new PathInterpolator(1.0f, 0.0f, 0.95f, 1.0f);
        this.mGestureState = 0;
        this.mCurrentAnimators = new ArraySet();
        this.mAnimatedViews = new ArrayList();
    }

    public LockscreenOpaLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.INTERPOLATOR_5_100 = new PathInterpolator(1.0f, 0.0f, 0.95f, 1.0f);
        this.mGestureState = 0;
        this.mCurrentAnimators = new ArraySet();
        this.mAnimatedViews = new ArrayList();
    }

    public LockscreenOpaLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.INTERPOLATOR_5_100 = new PathInterpolator(1.0f, 0.0f, 0.95f, 1.0f);
        this.mGestureState = 0;
        this.mCurrentAnimators = new ArraySet();
        this.mAnimatedViews = new ArrayList();
    }
}

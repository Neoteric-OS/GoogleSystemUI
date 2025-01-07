package com.android.wm.shell.onehanded;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.view.SurfaceControl;
import android.view.animation.BaseInterpolator;
import android.window.WindowContainerToken;
import androidx.core.view.ViewCompat$$ExternalSyntheticLambda0;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OneHandedAnimationController {
    public final HashMap mAnimatorMap = new HashMap();
    public final OneHandedInterpolator mInterpolator = new OneHandedInterpolator();
    public final OneHandedSurfaceTransactionHelper mSurfaceTransactionHelper;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OneHandedInterpolator extends BaseInterpolator {
        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            return (float) ((Math.sin((((f - 4.0f) / 4.0f) * 6.283185307179586d) / 4.0d) * Math.pow(2.0d, (-10.0f) * f)) + 1.0d);
        }
    }

    public OneHandedAnimationController(Context context) {
        this.mSurfaceTransactionHelper = new OneHandedSurfaceTransactionHelper(context);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class OneHandedTransitionAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
        public float mCurrentValue;
        public float mEndValue;
        public final SurfaceControl mLeash;
        public final List mOneHandedAnimationCallbacks = new ArrayList();
        public final float mStartValue;
        public final ViewCompat$$ExternalSyntheticLambda0 mSurfaceControlTransactionFactory;
        public OneHandedSurfaceTransactionHelper mSurfaceTransactionHelper;
        public final WindowContainerToken mToken;
        public int mTransitionDirection;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.wm.shell.onehanded.OneHandedAnimationController$OneHandedTransitionAnimator$1, reason: invalid class name */
        public final class AnonymousClass1 extends OneHandedTransitionAnimator {
            public final Rect mTmpRect;

            public AnonymousClass1(WindowContainerToken windowContainerToken, SurfaceControl surfaceControl, float f, float f2, Rect rect) {
                super(windowContainerToken, surfaceControl, f, f2);
                this.mTmpRect = new Rect(rect);
            }
        }

        public OneHandedTransitionAnimator(WindowContainerToken windowContainerToken, SurfaceControl surfaceControl, float f, float f2) {
            this.mLeash = surfaceControl;
            this.mToken = windowContainerToken;
            this.mStartValue = f;
            this.mEndValue = f2;
            addListener(this);
            addUpdateListener(this);
            this.mSurfaceControlTransactionFactory = new ViewCompat$$ExternalSyntheticLambda0();
            this.mTransitionDirection = 0;
        }

        public static OneHandedTransitionAnimator ofYOffset(WindowContainerToken windowContainerToken, SurfaceControl surfaceControl, float f, float f2, Rect rect) {
            return new AnonymousClass1(windowContainerToken, surfaceControl, f, f2, rect);
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            this.mCurrentValue = this.mEndValue;
            ((ArrayList) this.mOneHandedAnimationCallbacks).forEach(new OneHandedAnimationController$OneHandedTransitionAnimator$$ExternalSyntheticLambda0(this, 0));
            this.mOneHandedAnimationCallbacks.clear();
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            this.mCurrentValue = this.mEndValue;
            getClass();
            SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
            ((ArrayList) this.mOneHandedAnimationCallbacks).forEach(new OneHandedAnimationController$OneHandedTransitionAnimator$$ExternalSyntheticLambda2(this, transaction, 0));
            this.mOneHandedAnimationCallbacks.clear();
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            this.mCurrentValue = this.mStartValue;
            ((ArrayList) this.mOneHandedAnimationCallbacks).forEach(new OneHandedAnimationController$OneHandedTransitionAnimator$$ExternalSyntheticLambda0(this, 1));
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
            getClass();
            SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
            ((ArrayList) this.mOneHandedAnimationCallbacks).forEach(new OneHandedAnimationController$OneHandedTransitionAnimator$$ExternalSyntheticLambda2(this, transaction, 1));
            SurfaceControl surfaceControl = this.mLeash;
            float animatedFraction = valueAnimator.getAnimatedFraction();
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) this;
            float f = anonymousClass1.mStartValue;
            float f2 = (anonymousClass1.mEndValue * animatedFraction) + ((1.0f - animatedFraction) * f) + 0.5f;
            Rect rect = anonymousClass1.mTmpRect;
            int i = rect.left;
            int round = Math.round(f2) + rect.top;
            Rect rect2 = anonymousClass1.mTmpRect;
            rect.set(i, round, rect2.right, Math.round(f2) + rect2.bottom);
            anonymousClass1.mCurrentValue = f2;
            OneHandedSurfaceTransactionHelper oneHandedSurfaceTransactionHelper = anonymousClass1.mSurfaceTransactionHelper;
            Rect rect3 = anonymousClass1.mTmpRect;
            oneHandedSurfaceTransactionHelper.getClass();
            transaction.setWindowCrop(surfaceControl, rect3.width(), rect3.height());
            if (oneHandedSurfaceTransactionHelper.mEnableCornerRadius) {
                transaction.setCornerRadius(surfaceControl, oneHandedSurfaceTransactionHelper.mCornerRadius);
            }
            transaction.setPosition(surfaceControl, 0.0f, f2);
            transaction.apply();
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }
    }
}

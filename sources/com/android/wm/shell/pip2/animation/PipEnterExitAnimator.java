package com.android.wm.shell.pip2.animation;

import android.animation.Animator;
import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.view.Choreographer;
import android.view.SurfaceControl;
import com.android.wm.shell.R;
import com.android.wm.shell.pip2.PipSurfaceTransactionHelper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipEnterExitAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    public final Rect mAnimatedRect;
    public Runnable mAnimationEndCallback;
    public final Rect mBaseBounds;
    public final int mDirection;
    public final SurfaceControl.Transaction mFinishTransaction;
    public final RectEvaluator mInsetEvaluator;
    public final SurfaceControl mLeash;
    public final PipSurfaceTransactionHelper mPipSurfaceTransactionHelper;
    public final Rect mSourceRectHint;
    public final Rect mSourceRectHintInsets;
    public final SurfaceControl.Transaction mStartTransaction;
    public final PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory mSurfaceControlTransactionFactory;
    public final Rect mZeroInsets;

    public PipEnterExitAnimator(Context context, SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Rect rect, Rect rect2, Rect rect3, Rect rect4, int i) {
        Rect rect5 = new Rect();
        this.mBaseBounds = rect5;
        Rect rect6 = new Rect();
        Rect rect7 = new Rect();
        Rect rect8 = new Rect();
        this.mSourceRectHintInsets = rect8;
        this.mZeroInsets = new Rect(0, 0, 0, 0);
        Rect rect9 = new Rect();
        this.mAnimatedRect = rect9;
        this.mLeash = surfaceControl;
        this.mStartTransaction = transaction;
        this.mFinishTransaction = transaction2;
        rect5.set(rect);
        rect6.set(rect2);
        rect9.set(rect2);
        rect7.set(rect3);
        RectEvaluator rectEvaluator = new RectEvaluator(rect9);
        this.mInsetEvaluator = new RectEvaluator(new Rect());
        this.mPipSurfaceTransactionHelper = new PipSurfaceTransactionHelper(context);
        this.mDirection = i;
        Rect rect10 = rect4 != null ? new Rect(rect4) : null;
        this.mSourceRectHint = rect10;
        if (rect10 != null) {
            rect8.set(rect10.left - rect5.left, rect10.top - rect5.top, rect5.right - rect10.right, rect5.bottom - rect10.bottom);
        }
        this.mSurfaceControlTransactionFactory = new PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory();
        int integer = context.getResources().getInteger(R.integer.config_pipEnterAnimationDuration);
        setObjectValues(rect2, rect3);
        setDuration(integer);
        setEvaluator(rectEvaluator);
        addListener(this);
        addUpdateListener(this);
    }

    public final boolean isInPipDirection() {
        return this.mDirection == 0;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        SurfaceControl.Transaction transaction = this.mFinishTransaction;
        if (transaction != null) {
            this.mPipSurfaceTransactionHelper.scaleAndCrop(transaction, this.mLeash, this.mSourceRectHint, this.mBaseBounds, this.mAnimatedRect, this.mInsetEvaluator.evaluate(1.0f, isInPipDirection() ? this.mZeroInsets : this.mSourceRectHintInsets, isInPipDirection() ? this.mSourceRectHintInsets : this.mZeroInsets), isInPipDirection(), 1.0f);
            this.mFinishTransaction.setCornerRadius(this.mLeash, isInPipDirection() ? r10.mCornerRadius : 0.0f);
            this.mFinishTransaction.setShadowRadius(this.mLeash, isInPipDirection() ? r10.mShadowRadius : 0.0f);
        }
        Runnable runnable = this.mAnimationEndCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        SurfaceControl.Transaction transaction = this.mStartTransaction;
        if (transaction != null) {
            transaction.apply();
        }
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.mSurfaceControlTransactionFactory.getClass();
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        transaction.setFrameTimelineVsync(Choreographer.getInstance().getVsyncId());
        float animatedFraction = getAnimatedFraction();
        Rect evaluate = this.mInsetEvaluator.evaluate(animatedFraction, isInPipDirection() ? this.mZeroInsets : this.mSourceRectHintInsets, isInPipDirection() ? this.mSourceRectHintInsets : this.mZeroInsets);
        this.mPipSurfaceTransactionHelper.scaleAndCrop(transaction, this.mLeash, this.mSourceRectHint, this.mBaseBounds, this.mAnimatedRect, evaluate, isInPipDirection(), animatedFraction);
        transaction.setCornerRadius(this.mLeash, isInPipDirection() ? r9.mCornerRadius : 0.0f);
        transaction.setShadowRadius(this.mLeash, isInPipDirection() ? r9.mShadowRadius : 0.0f);
        transaction.apply();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }
}

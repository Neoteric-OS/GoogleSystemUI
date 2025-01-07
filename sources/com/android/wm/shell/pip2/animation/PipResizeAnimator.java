package com.android.wm.shell.pip2.animation;

import android.animation.Animator;
import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.Choreographer;
import android.view.SurfaceControl;
import com.android.wm.shell.pip2.PipSurfaceTransactionHelper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipResizeAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    public final Rect mAnimatedRect;
    public Runnable mAnimationEndCallback;
    public final Rect mBaseBounds;
    public final float mDelta;
    public final Rect mEndBounds;
    public final SurfaceControl.Transaction mFinishTx;
    public final SurfaceControl mLeash;
    public final Rect mStartBounds;
    public final SurfaceControl.Transaction mStartTx;
    public final PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory mSurfaceControlTransactionFactory;

    public PipResizeAnimator(SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Rect rect, Rect rect2, Rect rect3, int i, float f) {
        Rect rect4 = new Rect();
        this.mBaseBounds = rect4;
        Rect rect5 = new Rect();
        this.mStartBounds = rect5;
        Rect rect6 = new Rect();
        this.mEndBounds = rect6;
        Rect rect7 = new Rect();
        this.mAnimatedRect = rect7;
        this.mLeash = surfaceControl;
        this.mStartTx = transaction;
        this.mFinishTx = transaction2;
        this.mSurfaceControlTransactionFactory = new PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory();
        rect4.set(rect);
        rect5.set(rect2);
        rect7.set(rect2);
        rect6.set(rect3);
        this.mDelta = f;
        RectEvaluator rectEvaluator = new RectEvaluator(rect7);
        setObjectValues(rect2, rect3);
        addListener(this);
        addUpdateListener(this);
        setEvaluator(rectEvaluator);
        setDuration(i);
    }

    public static void setBoundsAndRotation(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, Rect rect, Rect rect2, float f) {
        Matrix matrix = new Matrix();
        matrix.setScale(rect2.width() / rect.width(), rect2.height() / rect.height());
        matrix.postTranslate(rect2.left, rect2.top);
        matrix.postRotate(f, rect2.centerX(), rect2.centerY());
        transaction.setMatrix(surfaceControl, matrix, new float[9]);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        SurfaceControl.Transaction transaction = this.mFinishTx;
        if (transaction != null) {
            setBoundsAndRotation(transaction, this.mLeash, this.mBaseBounds, this.mEndBounds, 0.0f);
        }
        Runnable runnable = this.mAnimationEndCallback;
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        SurfaceControl.Transaction transaction = this.mStartTx;
        if (transaction != null) {
            setBoundsAndRotation(transaction, this.mLeash, this.mBaseBounds, this.mStartBounds, this.mDelta);
            this.mStartTx.apply();
        }
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.mSurfaceControlTransactionFactory.getClass();
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        transaction.setFrameTimelineVsync(Choreographer.getInstance().getVsyncId());
        setBoundsAndRotation(transaction, this.mLeash, this.mBaseBounds, this.mAnimatedRect, (1.0f - getAnimatedFraction()) * this.mDelta);
        transaction.apply();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }
}

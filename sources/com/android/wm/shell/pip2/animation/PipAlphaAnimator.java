package com.android.wm.shell.pip2.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.Choreographer;
import android.view.SurfaceControl;
import com.android.wm.shell.R;
import com.android.wm.shell.pip2.PipSurfaceTransactionHelper;
import com.android.wm.shell.pip2.phone.PipTransition$$ExternalSyntheticLambda0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipAlphaAnimator extends ValueAnimator implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {
    public PipTransition$$ExternalSyntheticLambda0 mAnimationEndCallback;
    public final SurfaceControl mLeash;
    public final SurfaceControl.Transaction mStartTransaction;
    public final PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory mSurfaceControlTransactionFactory;

    public PipAlphaAnimator(Context context, SurfaceControl surfaceControl, SurfaceControl.Transaction transaction) {
        this.mLeash = surfaceControl;
        this.mStartTransaction = transaction;
        setFloatValues(0.0f, 1.0f);
        this.mSurfaceControlTransactionFactory = new PipSurfaceTransactionHelper.VsyncSurfaceControlTransactionFactory();
        setDuration(context.getResources().getInteger(R.integer.config_pipEnterAnimationDuration));
        addListener(this);
        addUpdateListener(this);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        PipTransition$$ExternalSyntheticLambda0 pipTransition$$ExternalSyntheticLambda0 = this.mAnimationEndCallback;
        if (pipTransition$$ExternalSyntheticLambda0 != null) {
            pipTransition$$ExternalSyntheticLambda0.run();
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
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        this.mSurfaceControlTransactionFactory.getClass();
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        transaction.setFrameTimelineVsync(Choreographer.getInstance().getVsyncId());
        transaction.setAlpha(this.mLeash, floatValue).apply();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }
}

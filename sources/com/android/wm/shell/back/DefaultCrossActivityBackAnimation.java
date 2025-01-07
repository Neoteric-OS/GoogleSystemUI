package com.android.wm.shell.back;

import android.content.Context;
import android.graphics.RectF;
import android.os.Handler;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;
import com.android.wm.shell.R;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.back.CrossActivityBackAnimation;
import com.android.wm.shell.shared.animation.Interpolators;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DefaultCrossActivityBackAnimation extends CrossActivityBackAnimation {
    public final boolean allowEnteringYShift;
    public final float enteringStartOffset;
    public final Interpolator postCommitInterpolator;

    public DefaultCrossActivityBackAnimation(Context context, BackAnimationBackground backAnimationBackground, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, Handler handler) {
        super(context, backAnimationBackground, rootTaskDisplayAreaOrganizer, new SurfaceControl.Transaction(), handler);
        this.postCommitInterpolator = Interpolators.EMPHASIZED;
        this.enteringStartOffset = context.getResources().getDimension(R.dimen.cross_activity_back_entering_start_offset);
        this.allowEnteringYShift = true;
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final boolean getAllowEnteringYShift() {
        return this.allowEnteringYShift;
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final long getPostCommitAnimationDuration() {
        return 450L;
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final void onGestureCommitted(float f) {
        this.startClosingRect.set(this.currentClosingRect);
        this.startEnteringRect.set(this.currentEnteringRect);
        this.targetEnteringRect.set(this.backAnimRect);
        this.targetClosingRect.set(this.backAnimRect);
        this.targetClosingRect.offset(this.currentClosingRect.left + this.enteringStartOffset, 0.0f);
        super.onGestureCommitted(f);
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final void onPostCommitProgress(float f) {
        SurfaceControl surfaceControl = this.scrimLayer;
        if (surfaceControl != null) {
            this.transaction.setAlpha(surfaceControl, (1.0f - f) * this.maxScrimAlpha);
        }
        float max = Math.max(1.0f - (5 * f), 0.0f);
        float interpolation = ((PathInterpolator) this.postCommitInterpolator).getInterpolation(f);
        CrossActivityBackAnimationKt.setInterpolatedRectF(this.currentClosingRect, this.startClosingRect, this.targetClosingRect, interpolation);
        RemoteAnimationTarget remoteAnimationTarget = this.closingTarget;
        SurfaceControl surfaceControl2 = remoteAnimationTarget != null ? remoteAnimationTarget.leash : null;
        RectF rectF = this.currentClosingRect;
        CrossActivityBackAnimation.FlingMode flingMode = CrossActivityBackAnimation.FlingMode.NO_FLING;
        CrossActivityBackAnimation.applyTransform$default(this, surfaceControl2, rectF, max, null, 8);
        CrossActivityBackAnimationKt.setInterpolatedRectF(this.currentEnteringRect, this.startEnteringRect, this.targetEnteringRect, interpolation);
        RemoteAnimationTarget remoteAnimationTarget2 = this.enteringTarget;
        CrossActivityBackAnimation.applyTransform$default(this, remoteAnimationTarget2 != null ? remoteAnimationTarget2.leash : null, this.currentEnteringRect, 1.0f, null, 8);
        applyTransaction();
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final void preparePreCommitClosingRectMovement(int i) {
        this.startClosingRect.set(this.backAnimRect);
        this.targetClosingRect.set(this.startClosingRect);
        CrossActivityBackAnimationKt.scaleCentered$default(this.targetClosingRect, 0.9f);
        if (i != 1) {
            RectF rectF = this.targetClosingRect;
            rectF.offset((this.startClosingRect.right - rectF.right) - this.displayBoundsMargin, 0.0f);
        }
    }

    @Override // com.android.wm.shell.back.CrossActivityBackAnimation
    public final void preparePreCommitEnteringRectMovement() {
        this.startEnteringRect.set(this.startClosingRect);
        this.startEnteringRect.offset(-this.enteringStartOffset, 0.0f);
        this.targetEnteringRect.set(this.startEnteringRect);
        CrossActivityBackAnimationKt.scaleCentered$default(this.targetEnteringRect, 0.9f);
    }
}

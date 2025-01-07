package com.android.wm.shell.pip2.phone;

import android.app.ActivityManager;
import android.app.PictureInPictureParams;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceControl;
import com.android.internal.util.Preconditions;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.pip.PipBoundsAlgorithm;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipUtils;
import com.android.wm.shell.pip2.animation.PipResizeAnimator;
import com.android.wm.shell.pip2.phone.PipTransitionState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipTaskListener implements ShellTaskOrganizer.TaskListener, PipTransitionState.PipTransitionStateChangedListener {
    public final Context mContext;
    public final PipBoundsAlgorithm mPipBoundsAlgorithm;
    public final PipBoundsState mPipBoundsState;
    public final PipScheduler mPipScheduler;
    public final PipTransitionState mPipTransitionState;
    public final PictureInPictureParams mPictureInPictureParams = new PictureInPictureParams.Builder().build();
    public boolean mWaitingForAspectRatioChange = false;

    public PipTaskListener(Context context, ShellTaskOrganizer shellTaskOrganizer, PipTransitionState pipTransitionState, PipScheduler pipScheduler, PipBoundsState pipBoundsState, PipBoundsAlgorithm pipBoundsAlgorithm, ShellExecutor shellExecutor) {
        this.mPipTransitionState = pipTransitionState;
        this.mPipScheduler = pipScheduler;
        this.mPipBoundsState = pipBoundsState;
        this.mPipBoundsAlgorithm = pipBoundsAlgorithm;
        pipTransitionState.addPipTransitionStateChangedListener(this);
        if (PipUtils.isPip2ExperimentEnabled()) {
            ((HandlerExecutor) shellExecutor).execute(new PipTaskListener$$ExternalSyntheticLambda1(this, shellTaskOrganizer, 0));
        }
    }

    @Override // com.android.wm.shell.pip2.phone.PipTransitionState.PipTransitionStateChangedListener
    public final void onPipTransitionStateChanged(int i, int i2, Bundle bundle) {
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        if (i2 == 4) {
            boolean z = bundle.getBoolean("animating_aspect_ratio_change");
            this.mWaitingForAspectRatioChange = z;
            if (z) {
                this.mPipScheduler.scheduleAnimateResizePip(false, 250, this.mPipBoundsAlgorithm.transformBoundsToAspectRatioIfValid(pipBoundsState.getBounds(), pipBoundsState.mAspectRatio, true, false));
                return;
            }
            return;
        }
        if (i2 != 5) {
            return;
        }
        SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) bundle.getParcelable("pip_start_tx", SurfaceControl.Transaction.class);
        SurfaceControl.Transaction transaction2 = (SurfaceControl.Transaction) bundle.getParcelable("pip_finish_tx", SurfaceControl.Transaction.class);
        Rect rect = (Rect) bundle.getParcelable("pip_dest_bounds", Rect.class);
        int i3 = bundle.getInt("animating_bounds_change_duration", 0);
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        Preconditions.checkNotNull(pipTransitionState.mPinnedTaskLeash, "Leash is null for bounds transition.");
        if (this.mWaitingForAspectRatioChange) {
            PipResizeAnimator pipResizeAnimator = new PipResizeAnimator(pipTransitionState.mPinnedTaskLeash, transaction, transaction2, rect, pipBoundsState.getBounds(), rect, i3, 0.0f);
            pipResizeAnimator.mAnimationEndCallback = new PipTaskListener$$ExternalSyntheticLambda1(this, rect, 1);
            pipResizeAnimator.start();
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        PictureInPictureParams pictureInPictureParams = runningTaskInfo.pictureInPictureParams;
        if (this.mPictureInPictureParams.equals(pictureInPictureParams)) {
            return;
        }
        if (!this.mPictureInPictureParams.equals(pictureInPictureParams)) {
            PictureInPictureParams pictureInPictureParams2 = this.mPictureInPictureParams;
            if (pictureInPictureParams == null) {
                pictureInPictureParams = new PictureInPictureParams.Builder().build();
            }
            pictureInPictureParams2.copyOnlySet(pictureInPictureParams);
        }
        final float aspectRatioFloat = this.mPictureInPictureParams.getAspectRatioFloat();
        if (Math.abs(aspectRatioFloat - this.mPipBoundsState.mAspectRatio) > 1.0E-7d) {
            Runnable runnable = new Runnable() { // from class: com.android.wm.shell.pip2.phone.PipTaskListener$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PipTaskListener pipTaskListener = PipTaskListener.this;
                    float f = aspectRatioFloat;
                    PipBoundsState pipBoundsState = pipTaskListener.mPipBoundsState;
                    pipBoundsState.setAspectRatio(f);
                    if (pipTaskListener.mPipBoundsAlgorithm.transformBoundsToAspectRatioIfValid(pipBoundsState.getBounds(), pipBoundsState.mAspectRatio, true, false).equals(pipBoundsState.getBounds())) {
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("animating_aspect_ratio_change", true);
                    pipTaskListener.mPipTransitionState.setState(4, bundle);
                }
            };
            PipTransitionState pipTransitionState = this.mPipTransitionState;
            pipTransitionState.mOnIdlePipTransitionStateRunnable = runnable;
            int i = pipTransitionState.mState;
            if (i == 3 || i == 6) {
                runnable.run();
                pipTransitionState.mOnIdlePipTransitionStateRunnable = null;
            }
        }
    }
}

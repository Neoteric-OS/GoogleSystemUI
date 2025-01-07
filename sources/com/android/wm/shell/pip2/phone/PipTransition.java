package com.android.wm.shell.pip2.phone;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.PictureInPictureParams;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import com.android.internal.util.Preconditions;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.pip.PipBoundsAlgorithm;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipUtils;
import com.android.wm.shell.pip.PipTransitionController;
import com.android.wm.shell.pip2.animation.PipAlphaAnimator;
import com.android.wm.shell.pip2.animation.PipEnterExitAnimator;
import com.android.wm.shell.pip2.phone.PipTransitionState;
import com.android.wm.shell.shared.pip.PipContentOverlay;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import java.util.Iterator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipTransition extends PipTransitionController implements PipTransitionState.PipTransitionStateChangedListener {
    public int mBoundsChangeDuration;
    public final Context mContext;
    public IBinder mEnterTransition;
    public IBinder mExitViaExpandTransition;
    public Transitions.TransitionFinishCallback mFinishCallback;
    public final PipScheduler mPipScheduler;
    public final PipTaskListener mPipTaskListener;
    public final PipTransitionState mPipTransitionState;
    public IBinder mResizeTransition;

    public PipTransition(Context context, ShellInit shellInit, ShellTaskOrganizer shellTaskOrganizer, Transitions transitions, PipBoundsState pipBoundsState, PipBoundsAlgorithm pipBoundsAlgorithm, PipTaskListener pipTaskListener, PipScheduler pipScheduler, PipTransitionState pipTransitionState) {
        super(shellInit, shellTaskOrganizer, transitions, pipBoundsState, null, pipBoundsAlgorithm);
        this.mBoundsChangeDuration = 0;
        this.mContext = context;
        this.mPipTaskListener = pipTaskListener;
        this.mPipScheduler = pipScheduler;
        pipScheduler.mPipTransitionController = this;
        this.mPipTransitionState = pipTransitionState;
        pipTransitionState.addPipTransitionStateChangedListener(this);
    }

    public static TransitionInfo.Change getChangeByToken(TransitionInfo transitionInfo, WindowContainerToken windowContainerToken) {
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getTaskInfo() != null && change.getTaskInfo().getToken().equals(windowContainerToken)) {
                return change;
            }
        }
        return null;
    }

    public static TransitionInfo.Change getPipChange$1(TransitionInfo transitionInfo) {
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if (change.getTaskInfo() != null && change.getTaskInfo().getWindowingMode() == 2) {
                return change;
            }
        }
        return null;
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void augmentRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo, WindowContainerTransaction windowContainerTransaction) {
        ActivityManager.RunningTaskInfo pipTask = transitionRequestInfo.getPipTask();
        if ((pipTask != null && pipTask.pictureInPictureParams != null && transitionRequestInfo.getType() == 1 && pipTask.pictureInPictureParams.isAutoEnterEnabled()) || transitionRequestInfo.getType() == 10) {
            windowContainerTransaction.merge(getEnterPipTransaction(transitionRequestInfo), true);
            this.mEnterTransition = iBinder;
        }
    }

    public final WindowContainerTransaction getEnterPipTransaction(TransitionRequestInfo transitionRequestInfo) {
        ActivityManager.RunningTaskInfo pipTask = transitionRequestInfo.getPipTask();
        PictureInPictureParams pictureInPictureParams = pipTask.pictureInPictureParams;
        PipTaskListener pipTaskListener = this.mPipTaskListener;
        if (!pipTaskListener.mPictureInPictureParams.equals(pictureInPictureParams)) {
            pipTaskListener.mPictureInPictureParams.copyOnlySet(pictureInPictureParams != null ? pictureInPictureParams : new PictureInPictureParams.Builder().build());
        }
        ComponentName componentName = pipTask.topActivity;
        ActivityInfo activityInfo = pipTask.topActivityInfo;
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        PipBoundsAlgorithm pipBoundsAlgorithm = this.mPipBoundsAlgorithm;
        pipBoundsState.setBoundsStateForEntry(componentName, activityInfo, pictureInPictureParams, pipBoundsAlgorithm);
        Rect entryDestinationBounds = pipBoundsAlgorithm.getEntryDestinationBounds();
        pipBoundsState.setBounds(entryDestinationBounds);
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        windowContainerTransaction.movePipActivityToPinnedRootTask(pipTask.token, entryDestinationBounds);
        windowContainerTransaction.deferConfigToTransitionEnd(pipTask.token);
        return windowContainerTransaction;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        ActivityManager.RunningTaskInfo pipTask = transitionRequestInfo.getPipTask();
        if ((pipTask == null || pipTask.pictureInPictureParams == null || transitionRequestInfo.getType() != 1 || !pipTask.pictureInPictureParams.isAutoEnterEnabled()) && transitionRequestInfo.getType() != 10) {
            return null;
        }
        this.mEnterTransition = iBinder;
        return getEnterPipTransaction(transitionRequestInfo);
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final boolean isInSwipePipToHomeTransition() {
        return this.mPipTransitionState.mInSwipePipToHomeTransition;
    }

    public final void onClientDrawAtTransitionEnd() {
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        if (pipTransitionState.mSwipePipToHomeOverlay == null) {
            if (pipTransitionState.mState == 2) {
                pipTransitionState.setState(3, null);
            }
        } else {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
            ofFloat.setDuration(400L);
            ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.pip2.phone.PipTransition.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
                    transaction.remove(PipTransition.this.mPipTransitionState.mSwipePipToHomeOverlay);
                    transaction.apply();
                    PipTransition.this.mPipTransitionState.setState(3, null);
                }
            });
            ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.pip2.phone.PipTransition$$ExternalSyntheticLambda4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PipTransition pipTransition = PipTransition.this;
                    pipTransition.getClass();
                    new SurfaceControl.Transaction().setAlpha(pipTransition.mPipTransitionState.mSwipePipToHomeOverlay, ((Float) valueAnimator.getAnimatedValue()).floatValue()).apply();
                }
            });
            ofFloat.start();
        }
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void onInit() {
        if (PipUtils.isPip2ExperimentEnabled()) {
            this.mTransitions.addHandler(this);
        }
    }

    @Override // com.android.wm.shell.pip2.phone.PipTransitionState.PipTransitionStateChangedListener
    public final void onPipTransitionStateChanged(int i, int i2, Bundle bundle) {
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        if (i2 != 2) {
            if (i2 != 8) {
                return;
            }
            pipTransitionState.mPipTaskToken = null;
            pipTransitionState.mPinnedTaskLeash = null;
            return;
        }
        boolean z = false;
        Preconditions.checkState(bundle != null, "No extra bundle for " + pipTransitionState);
        pipTransitionState.mPipTaskToken = (WindowContainerToken) bundle.getParcelable("pip_task_token", WindowContainerToken.class);
        SurfaceControl surfaceControl = (SurfaceControl) bundle.getParcelable("pip_task_leash", SurfaceControl.class);
        pipTransitionState.mPinnedTaskLeash = surfaceControl;
        if (pipTransitionState.mPipTaskToken != null && surfaceControl != null) {
            z = true;
        }
        Preconditions.checkState(z, "Unexpected bundle for " + pipTransitionState);
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        WindowContainerToken container;
        TransitionInfo.Change change;
        Rect rect;
        Rect sourceRectHint;
        TransitionInfo.Change changeByToken;
        IBinder iBinder2 = this.mEnterTransition;
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        if (iBinder != iBinder2 && transitionInfo.getType() != 10) {
            if (iBinder != this.mExitViaExpandTransition) {
                if (iBinder != this.mResizeTransition) {
                    WindowContainerToken windowContainerToken = pipTransitionState.mPipTaskToken;
                    if (windowContainerToken != null && (change = transitionInfo.getChange(windowContainerToken)) != null) {
                        boolean z = transitionInfo.getType() == 4 && change.getMode() == 4;
                        boolean z2 = transitionInfo.getType() == 2 && change.getMode() == 2;
                        if (z || z2) {
                            transaction.apply();
                            transitionFinishCallback.onTransitionFinished(null);
                            pipTransitionState.setState(8, null);
                            return true;
                        }
                    }
                    return false;
                }
                this.mResizeTransition = null;
                TransitionInfo.Change pipChange$1 = getPipChange$1(transitionInfo);
                if (pipChange$1 == null) {
                    return false;
                }
                transaction.setWindowCrop(pipChange$1.getLeash(), pipChange$1.getEndAbsBounds().width(), pipChange$1.getEndAbsBounds().height());
                Bundle bundle = new Bundle();
                bundle.putParcelable("pip_start_tx", transaction);
                bundle.putParcelable("pip_finish_tx", transaction2);
                bundle.putParcelable("pip_dest_bounds", pipChange$1.getEndAbsBounds());
                int i = this.mBoundsChangeDuration;
                if (i > 0) {
                    bundle.putInt("animating_bounds_change_duration", i);
                    this.mBoundsChangeDuration = 0;
                }
                this.mFinishCallback = transitionFinishCallback;
                pipTransitionState.setState(5, bundle);
                return true;
            }
            this.mExitViaExpandTransition = null;
            pipTransitionState.setState(7, null);
            WindowContainerToken windowContainerToken2 = pipTransitionState.mPipTaskToken;
            TransitionInfo.Change changeByToken2 = getChangeByToken(transitionInfo, windowContainerToken2);
            if (changeByToken2 == null) {
                Iterator it = transitionInfo.getChanges().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    TransitionInfo.Change change2 = (TransitionInfo.Change) it.next();
                    if (change2.getTaskInfo() == null && change2.getLastParent() != null && change2.getLastParent().equals(windowContainerToken2)) {
                        changeByToken2 = change2;
                        break;
                    }
                }
                if (changeByToken2 == null) {
                    return false;
                }
            }
            if (changeByToken2.getTaskInfo() == null && (changeByToken = getChangeByToken(transitionInfo, changeByToken2.getParent())) != null) {
                transaction.setLayer(changeByToken.getLeash(), 2147483646);
            }
            Rect startAbsBounds = changeByToken2.getStartAbsBounds();
            Rect endAbsBounds = changeByToken2.getEndAbsBounds();
            SurfaceControl leash = changeByToken2.getLeash();
            Preconditions.checkNotNull(leash, "Leash is null for exit transition.");
            if (changeByToken2.getTaskInfo() == null || changeByToken2.getTaskInfo().pictureInPictureParams == null) {
                PipTaskListener pipTaskListener = this.mPipTaskListener;
                if (!pipTaskListener.mPictureInPictureParams.hasSourceBoundsHint()) {
                    rect = null;
                    PipEnterExitAnimator pipEnterExitAnimator = new PipEnterExitAnimator(this.mContext, leash, transaction, transaction2, endAbsBounds, startAbsBounds, endAbsBounds, rect, 1);
                    pipEnterExitAnimator.mAnimationEndCallback = new PipTransition$$ExternalSyntheticLambda0(this, transitionFinishCallback, 0);
                    pipEnterExitAnimator.start();
                    return true;
                }
                sourceRectHint = pipTaskListener.mPictureInPictureParams.getSourceRectHint();
            } else {
                sourceRectHint = changeByToken2.getTaskInfo().pictureInPictureParams.getSourceRectHint();
            }
            rect = sourceRectHint;
            PipEnterExitAnimator pipEnterExitAnimator2 = new PipEnterExitAnimator(this.mContext, leash, transaction, transaction2, endAbsBounds, startAbsBounds, endAbsBounds, rect, 1);
            pipEnterExitAnimator2.mAnimationEndCallback = new PipTransition$$ExternalSyntheticLambda0(this, transitionFinishCallback, 0);
            pipEnterExitAnimator2.start();
            return true;
        }
        this.mEnterTransition = null;
        TransitionInfo.Change pipChange$12 = getPipChange$1(transitionInfo);
        if (pipChange$12 == null) {
            return false;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("pip_task_token", pipChange$12.getContainer());
        bundle2.putParcelable("pip_task_leash", pipChange$12.getLeash());
        pipTransitionState.setState(2, bundle2);
        boolean z3 = pipTransitionState.mInSwipePipToHomeTransition;
        PipScheduler pipScheduler = this.mPipScheduler;
        if (z3) {
            TransitionInfo.Change pipChange$13 = getPipChange$1(transitionInfo);
            if (pipChange$13 != null) {
                WindowContainerToken container2 = pipChange$13.getContainer();
                SurfaceControl leash2 = pipChange$13.getLeash();
                if (container2 != null && leash2 != null) {
                    SurfaceControl surfaceControl = pipTransitionState.mSwipePipToHomeOverlay;
                    PictureInPictureParams pictureInPictureParams = pipChange$13.getTaskInfo().pictureInPictureParams;
                    Rect rect2 = pipTransitionState.mSwipePipToHomeAppBounds;
                    Rect endAbsBounds2 = pipChange$13.getEndAbsBounds();
                    Rect sourceRectHint2 = surfaceControl == null ? pictureInPictureParams.getSourceRectHint() : PipUtils.getEnterPipWithOverlaySrcRectHint(rect2, pipChange$13.getTaskInfo().pictureInPictureParams.getAspectRatioFloat());
                    WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                    SurfaceControl.Transaction transaction3 = new SurfaceControl.Transaction();
                    float width = endAbsBounds2.width() / sourceRectHint2.width();
                    transaction.setWindowCrop(leash2, sourceRectHint2);
                    transaction.setPosition(leash2, endAbsBounds2.left - (sourceRectHint2.left * width), endAbsBounds2.top - (sourceRectHint2.top * width));
                    transaction.setScale(leash2, width, width);
                    if (surfaceControl != null) {
                        int overlaySize = PipContentOverlay.PipAppIconOverlay.getOverlaySize(pipTransitionState.mSwipePipToHomeAppBounds, endAbsBounds2);
                        transaction3.setScale(surfaceControl, 1.0f, 1.0f);
                        transaction3.setPosition(surfaceControl, (endAbsBounds2.width() - overlaySize) / 2.0f, (endAbsBounds2.height() - overlaySize) / 2.0f);
                    }
                    transaction.apply();
                    transaction3.addTransactionCommittedListener(pipScheduler.mMainExecutor, new SurfaceControl.TransactionCommittedListener() { // from class: com.android.wm.shell.pip2.phone.PipTransition$$ExternalSyntheticLambda2
                        @Override // android.view.SurfaceControl.TransactionCommittedListener
                        public final void onTransactionCommitted() {
                            PipTransition.this.onClientDrawAtTransitionEnd();
                        }
                    });
                    windowContainerTransaction.setBoundsChangeTransaction(container2, transaction3);
                    transitionFinishCallback.onTransitionFinished(windowContainerTransaction.isEmpty() ? null : windowContainerTransaction);
                    return true;
                }
            }
            return false;
        }
        TransitionInfo.Change pipChange$14 = getPipChange$1(transitionInfo);
        if (pipChange$14 != null && transitionInfo.getChanges().size() == 1 && (pipChange$14.getMode() == 3 || pipChange$14.getMode() == 1)) {
            TransitionInfo.Change pipChange$15 = getPipChange$1(transitionInfo);
            if (pipChange$15 == null) {
                return false;
            }
            Rect endAbsBounds3 = pipChange$15.getEndAbsBounds();
            SurfaceControl surfaceControl2 = pipTransitionState.mPinnedTaskLeash;
            Preconditions.checkNotNull(surfaceControl2, "Leash is null for alpha transition.");
            transaction.setPosition(surfaceControl2, endAbsBounds3.left, endAbsBounds3.top).setWindowCrop(surfaceControl2, endAbsBounds3.width(), endAbsBounds3.height()).setAlpha(surfaceControl2, 0.0f);
            PipAlphaAnimator pipAlphaAnimator = new PipAlphaAnimator(this.mContext, surfaceControl2, transaction);
            pipAlphaAnimator.mAnimationEndCallback = new PipTransition$$ExternalSyntheticLambda0(this, transitionFinishCallback, 1);
            pipAlphaAnimator.start();
            return true;
        }
        TransitionInfo.Change pipChange$16 = getPipChange$1(transitionInfo);
        if (pipChange$16 == null || (container = pipChange$16.getContainer()) == null) {
            return false;
        }
        WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
        SurfaceControl.Transaction transaction4 = new SurfaceControl.Transaction();
        Rect startAbsBounds2 = pipChange$16.getStartAbsBounds();
        Rect endAbsBounds4 = pipChange$16.getEndAbsBounds();
        SurfaceControl surfaceControl3 = pipTransitionState.mPinnedTaskLeash;
        Preconditions.checkNotNull(surfaceControl3, "Leash is null for bounds transition.");
        PipEnterExitAnimator pipEnterExitAnimator3 = new PipEnterExitAnimator(this.mContext, surfaceControl3, transaction, transaction2, startAbsBounds2, startAbsBounds2, endAbsBounds4, (pipChange$16.getTaskInfo() == null || pipChange$16.getTaskInfo().pictureInPictureParams == null) ? null : pipChange$16.getTaskInfo().pictureInPictureParams.getSourceRectHint(), 0);
        transaction4.addTransactionCommittedListener(pipScheduler.mMainExecutor, new SurfaceControl.TransactionCommittedListener() { // from class: com.android.wm.shell.pip2.phone.PipTransition$$ExternalSyntheticLambda2
            @Override // android.view.SurfaceControl.TransactionCommittedListener
            public final void onTransactionCommitted() {
                PipTransition.this.onClientDrawAtTransitionEnd();
            }
        });
        windowContainerTransaction2.setBoundsChangeTransaction(container, transaction4);
        pipEnterExitAnimator3.mAnimationEndCallback = new PipTransition$$ExternalSyntheticLambda0(transitionFinishCallback, windowContainerTransaction2);
        pipEnterExitAnimator3.start();
        return true;
    }

    @Override // com.android.wm.shell.pip.PipTransitionController
    public final void startExitTransition(int i, WindowContainerTransaction windowContainerTransaction, Rect rect) {
        IBinder startTransition = this.mTransitions.startTransition(i, windowContainerTransaction, this);
        if (i == 1001) {
            this.mExitViaExpandTransition = startTransition;
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback) {
    }
}

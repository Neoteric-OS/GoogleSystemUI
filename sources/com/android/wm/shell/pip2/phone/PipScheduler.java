package com.android.wm.shell.pip2.phone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.SurfaceControl;
import android.window.WindowContainerTransaction;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipUtils;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipScheduler {
    public final ShellExecutor mMainExecutor;
    public final PipBoundsState mPipBoundsState;
    public final PhonePipMenuController mPipMenuController;
    public PipTransition mPipTransitionController;
    public final PipTransitionState mPipTransitionState;
    public PipTouchHandler$$ExternalSyntheticLambda2 mUpdateMovementBoundsRunnable;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PipSchedulerReceiver extends BroadcastReceiver {
        public PipSchedulerReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("cuj_code_extra", 0);
            if (intExtra == 0) {
                PipScheduler.this.scheduleExitPipViaExpand();
            } else {
                if (intExtra != 1) {
                    throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(intExtra, "unexpected CUJ code="));
                }
                PipScheduler.this.getClass();
            }
        }
    }

    public PipScheduler(Context context, PipBoundsState pipBoundsState, PhonePipMenuController phonePipMenuController, ShellExecutor shellExecutor, PipTransitionState pipTransitionState) {
        this.mPipBoundsState = pipBoundsState;
        this.mPipMenuController = phonePipMenuController;
        this.mMainExecutor = shellExecutor;
        this.mPipTransitionState = pipTransitionState;
        if (PipUtils.isPip2ExperimentEnabled()) {
            context.registerReceiver(new PipSchedulerReceiver(), new IntentFilter("com.android.wm.shell.pip2.phone.PipScheduler"), null, null, 2);
        }
    }

    public final void scheduleAnimateResizePip(boolean z, int i, Rect rect) {
        int i2;
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        if (pipTransitionState.mPipTaskToken == null || (i2 = pipTransitionState.mState) <= 2 || i2 >= 7) {
            return;
        }
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        windowContainerTransaction.setBounds(pipTransitionState.mPipTaskToken, rect);
        if (z) {
            windowContainerTransaction.deferConfigToTransitionEnd(pipTransitionState.mPipTaskToken);
        }
        PipTransition pipTransition = this.mPipTransitionController;
        pipTransition.mResizeTransition = pipTransition.mTransitions.startTransition(1016, windowContainerTransaction, pipTransition);
        pipTransition.mBoundsChangeDuration = i;
    }

    public final void scheduleExitPipViaExpand() {
        PipTransitionState pipTransitionState = this.mPipTransitionState;
        final WindowContainerTransaction windowContainerTransaction = null;
        if (pipTransitionState.mPipTaskToken != null) {
            WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
            windowContainerTransaction2.setBounds(pipTransitionState.mPipTaskToken, (Rect) null);
            windowContainerTransaction2.setWindowingMode(pipTransitionState.mPipTaskToken, 0);
            windowContainerTransaction = windowContainerTransaction2;
        }
        if (windowContainerTransaction != null) {
            ((HandlerExecutor) this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.pip2.phone.PipScheduler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PipScheduler pipScheduler = PipScheduler.this;
                    pipScheduler.mPipTransitionController.startExitTransition(1001, windowContainerTransaction, null);
                }
            });
        }
    }

    public final void scheduleFinishResizePip(Rect rect, boolean z) {
        SurfaceControl.Transaction transaction;
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        if (!pipBoundsState.getBounds().equals(rect)) {
            pipBoundsState.setBounds(rect);
            PhonePipMenuController phonePipMenuController = this.mPipMenuController;
            if (phonePipMenuController.isMenuVisible()) {
                phonePipMenuController.mPipMenuView.mPipMenuIconsAlgorithm.getClass();
            }
            PipTouchHandler$$ExternalSyntheticLambda2 pipTouchHandler$$ExternalSyntheticLambda2 = this.mUpdateMovementBoundsRunnable;
            if (pipTouchHandler$$ExternalSyntheticLambda2 != null) {
                pipTouchHandler$$ExternalSyntheticLambda2.run();
            }
        }
        WindowContainerTransaction windowContainerTransaction = null;
        if (z) {
            transaction = new SurfaceControl.Transaction();
            transaction.addTransactionCommittedListener(this.mMainExecutor, new SurfaceControl.TransactionCommittedListener() { // from class: com.android.wm.shell.pip2.phone.PipScheduler$$ExternalSyntheticLambda1
                @Override // android.view.SurfaceControl.TransactionCommittedListener
                public final void onTransactionCommitted() {
                    PipScheduler.this.mPipTransitionState.setState(6, null);
                }
            });
        } else {
            this.mPipTransitionState.setState(6, null);
            transaction = null;
        }
        PipTransition pipTransition = this.mPipTransitionController;
        if (transaction != null) {
            PipTransitionState pipTransitionState = pipTransition.mPipTransitionState;
            if (pipTransitionState.mPipTaskToken != null) {
                windowContainerTransaction = new WindowContainerTransaction();
                windowContainerTransaction.setBoundsChangeTransaction(pipTransitionState.mPipTaskToken, transaction);
            }
        }
        Transitions.TransitionFinishCallback transitionFinishCallback = pipTransition.mFinishCallback;
        if (transitionFinishCallback != null) {
            transitionFinishCallback.onTransitionFinished(windowContainerTransaction);
        }
    }

    public final void scheduleUserResizePip(Rect rect, float f) {
        if (rect.isEmpty()) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[3]) {
                ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 2719140251693035698L, 0, "PipScheduler");
                return;
            }
            return;
        }
        SurfaceControl surfaceControl = this.mPipTransitionState.mPinnedTaskLeash;
        SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
        Matrix matrix = new Matrix();
        float width = rect.width() / this.mPipBoundsState.getBounds().width();
        matrix.setScale(width, width);
        matrix.postTranslate(rect.left, rect.top);
        matrix.postRotate(f, rect.centerX(), rect.centerY());
        transaction.setMatrix(surfaceControl, matrix, new float[9]);
        transaction.apply();
    }
}

package com.android.wm.shell.pip;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.PictureInPictureUiState;
import android.app.TaskInfo;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.pip.PipBoundsAlgorithm;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.split.SplitScreenUtils;
import com.android.wm.shell.pip.PipAnimationController;
import com.android.wm.shell.pip.PipTransitionController;
import com.android.wm.shell.pip.phone.PhonePipMenuController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.DefaultMixedHandler;
import com.android.wm.shell.transition.Transitions;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class PipTransitionController implements Transitions.TransitionHandler {
    public DefaultMixedHandler mMixedHandler;
    public final PipBoundsAlgorithm mPipBoundsAlgorithm;
    public final PipBoundsState mPipBoundsState;
    public final PhonePipMenuController mPipMenuController;
    public PipTaskOrganizer mPipOrganizer;
    public final ShellTaskOrganizer mShellTaskOrganizer;
    public final Transitions mTransitions;
    public final Map mPipTransitionCallbacks = new HashMap();
    public final AnonymousClass1 mPipAnimationCallback = new PipAnimationController.PipAnimationCallback() { // from class: com.android.wm.shell.pip.PipTransitionController.1
        @Override // com.android.wm.shell.pip.PipAnimationController.PipAnimationCallback
        public final void onPipAnimationCancel(PipAnimationController.PipTransitionAnimator pipTransitionAnimator) {
            PipTaskOrganizer pipTaskOrganizer;
            SurfaceControl surfaceControl;
            boolean isInPipDirection = PipAnimationController.isInPipDirection(pipTransitionAnimator.getTransitionDirection());
            PipTransitionController pipTransitionController = PipTransitionController.this;
            if (isInPipDirection && (surfaceControl = (pipTaskOrganizer = pipTransitionController.mPipOrganizer).mPipOverlay) != null) {
                pipTaskOrganizer.fadeOutAndRemoveOverlay(surfaceControl, true);
            }
            pipTransitionController.sendOnPipTransitionCancelled(pipTransitionAnimator.getTransitionDirection());
        }

        @Override // com.android.wm.shell.pip.PipAnimationController.PipAnimationCallback
        public final void onPipAnimationEnd(TaskInfo taskInfo, SurfaceControl.Transaction transaction, PipAnimationController.PipTransitionAnimator pipTransitionAnimator) {
            PipTaskOrganizer pipTaskOrganizer;
            SurfaceControl surfaceControl;
            int transitionDirection = pipTransitionAnimator.getTransitionDirection();
            PipTransitionController pipTransitionController = PipTransitionController.this;
            pipTransitionController.mPipBoundsState.setBounds(pipTransitionAnimator.mDestinationBounds);
            if (transitionDirection == 5) {
                return;
            }
            if (PipAnimationController.isInPipDirection(transitionDirection) && (surfaceControl = (pipTaskOrganizer = pipTransitionController.mPipOrganizer).mPipOverlay) != null) {
                pipTaskOrganizer.fadeOutAndRemoveOverlay(surfaceControl, true);
            }
            pipTransitionController.onFinishResize(taskInfo, pipTransitionAnimator.mDestinationBounds, transitionDirection, transaction);
            pipTransitionController.sendOnPipTransitionFinished(transitionDirection);
        }

        @Override // com.android.wm.shell.pip.PipAnimationController.PipAnimationCallback
        public final void onPipAnimationStart(PipAnimationController.PipTransitionAnimator pipTransitionAnimator) {
            PipTransitionController.this.sendOnPipTransitionStarted(pipTransitionAnimator.getTransitionDirection());
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface PipTransitionCallback {
        void onPipTransitionCanceled(int i);

        void onPipTransitionFinished(int i);

        void onPipTransitionStarted(int i, Rect rect);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.wm.shell.pip.PipTransitionController$1] */
    public PipTransitionController(ShellInit shellInit, ShellTaskOrganizer shellTaskOrganizer, Transitions transitions, PipBoundsState pipBoundsState, PhonePipMenuController phonePipMenuController, PipBoundsAlgorithm pipBoundsAlgorithm) {
        this.mPipBoundsState = pipBoundsState;
        this.mPipMenuController = phonePipMenuController;
        this.mShellTaskOrganizer = shellTaskOrganizer;
        this.mPipBoundsAlgorithm = pipBoundsAlgorithm;
        this.mTransitions = transitions;
        if (Transitions.ENABLE_SHELL_TRANSITIONS) {
            shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.pip.PipTransitionController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    PipTransitionController.this.onInit();
                }
            }, this);
        }
    }

    public abstract void augmentRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo, WindowContainerTransaction windowContainerTransaction);

    public void end() {
    }

    public boolean handleRotateDisplay(int i, int i2, WindowContainerTransaction windowContainerTransaction) {
        return false;
    }

    public boolean isEnteringPip(TransitionInfo.Change change, int i) {
        return false;
    }

    public abstract boolean isInSwipePipToHomeTransition();

    public final boolean isPackageActiveInPip(String str) {
        PipTaskOrganizer pipTaskOrganizer = this.mPipOrganizer;
        ActivityManager.RunningTaskInfo runningTaskInfo = pipTaskOrganizer.mTaskInfo;
        return str != null && runningTaskInfo != null && pipTaskOrganizer.isInPip() && str.equals(SplitScreenUtils.getPackageName(((TaskInfo) runningTaskInfo).baseIntent));
    }

    public abstract void onInit();

    public final void sendOnPipTransitionCancelled(int i) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -356640916490602654L, 1, Long.valueOf(i));
        }
        for (Map.Entry entry : ((HashMap) this.mPipTransitionCallbacks).entrySet()) {
            ((Executor) entry.getValue()).execute(new PipTransitionController$$ExternalSyntheticLambda1(entry, i, 1));
        }
    }

    public final void sendOnPipTransitionFinished(int i) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -1875374233534254061L, 1, Long.valueOf(i));
        }
        for (Map.Entry entry : ((HashMap) this.mPipTransitionCallbacks).entrySet()) {
            ((Executor) entry.getValue()).execute(new PipTransitionController$$ExternalSyntheticLambda1(entry, i, 0));
        }
        if (PipAnimationController.isInPipDirection(i)) {
            try {
                ActivityTaskManager.getService().onPictureInPictureUiStateChanged(new PictureInPictureUiState.Builder().setTransitioningToPip(false).build());
            } catch (RemoteException | IllegalStateException unused) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                    ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -8189372673897383003L, 0, null);
                }
            }
        }
    }

    public final void sendOnPipTransitionStarted(final int i) {
        final Rect bounds = this.mPipBoundsState.getBounds();
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -2546532866731920044L, 1, Long.valueOf(i), String.valueOf(bounds));
        }
        for (final Map.Entry entry : ((HashMap) this.mPipTransitionCallbacks).entrySet()) {
            ((Executor) entry.getValue()).execute(new Runnable() { // from class: com.android.wm.shell.pip.PipTransitionController$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    Map.Entry entry2 = entry;
                    ((PipTransitionController.PipTransitionCallback) entry2.getKey()).onPipTransitionStarted(i, bounds);
                }
            });
        }
        if (!PipAnimationController.isInPipDirection(i) || isInSwipePipToHomeTransition()) {
            return;
        }
        try {
            ActivityTaskManager.getService().onPictureInPictureUiStateChanged(new PictureInPictureUiState.Builder().setTransitioningToPip(true).build());
        } catch (RemoteException | IllegalStateException unused) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -8189372673897383003L, 0, null);
            }
        }
    }

    public abstract void startExitTransition(int i, WindowContainerTransaction windowContainerTransaction, Rect rect);

    public void end(PipTaskOrganizer$$ExternalSyntheticLambda11 pipTaskOrganizer$$ExternalSyntheticLambda11) {
    }

    public void forceFinishTransition() {
    }

    public void onFixedRotationFinished$1() {
    }

    public void onFixedRotationStarted() {
    }

    public void setEnterAnimationType(int i) {
    }

    public void dump$1(PrintWriter printWriter, String str) {
    }

    public void syncPipSurfaceState(TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
    }

    public void onFinishResize(TaskInfo taskInfo, Rect rect, int i, SurfaceControl.Transaction transaction) {
    }

    public void startEnterAnimation(TransitionInfo.Change change, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
    }
}

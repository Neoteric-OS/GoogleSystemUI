package com.android.wm.shell.desktopmode;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.Slog;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.shared.desktopmode.DesktopModeTransitionSource;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EnterDesktopTaskTransitionHandler implements Transitions.TransitionHandler {
    public final InteractionJankMonitor mInteractionJankMonitor;
    public DesktopModeWindowDecorViewModel.DragStartListenerImpl mOnTaskResizeAnimationListener;
    public final List mPendingTransitionTokens;
    public final EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0 mTransactionSupplier;
    public final Transitions mTransitions;

    public EnterDesktopTaskTransitionHandler(Transitions transitions, InteractionJankMonitor interactionJankMonitor) {
        EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0 enterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0 = new EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0();
        this.mPendingTransitionTokens = new ArrayList();
        this.mTransitions = transitions;
        this.mInteractionJankMonitor = interactionJankMonitor;
        this.mTransactionSupplier = enterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        return null;
    }

    public final IBinder moveToDesktop(WindowContainerTransaction windowContainerTransaction, DesktopModeTransitionSource desktopModeTransitionSource) {
        int ordinal = desktopModeTransitionSource.ordinal();
        IBinder startTransition = this.mTransitions.startTransition(ordinal != 1 ? ordinal != 2 ? ordinal != 3 ? 1104 : 1103 : 1101 : 1102, windowContainerTransaction, this);
        this.mPendingTransitionTokens.add(startTransition);
        return startTransition;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, final Transitions.TransitionFinishCallback transitionFinishCallback) {
        ActivityManager.RunningTaskInfo taskInfo;
        boolean z;
        boolean z2 = false;
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if ((change.getFlags() & 2) == 0 && (taskInfo = change.getTaskInfo()) != null && taskInfo.taskId != -1 && change.getMode() == 6) {
                int type = transitionInfo.getType();
                if (this.mPendingTransitionTokens.contains(iBinder)) {
                    ActivityManager.RunningTaskInfo taskInfo2 = change.getTaskInfo();
                    if (CollectionsKt__CollectionsKt.listOf(1101, 1102, 1103, 1104).contains(Integer.valueOf(type)) && taskInfo2.getWindowingMode() == 5) {
                        final SurfaceControl leash = change.getLeash();
                        Rect startAbsBounds = change.getStartAbsBounds();
                        final ActivityManager.RunningTaskInfo taskInfo3 = change.getTaskInfo();
                        if (this.mOnTaskResizeAnimationListener == null) {
                            Slog.e("EnterDesktopTaskTransitionHandler", "onTaskResizeAnimationListener is not available for this transition");
                        } else {
                            transaction.setPosition(leash, startAbsBounds.left, startAbsBounds.top).setWindowCrop(leash, startAbsBounds.width(), startAbsBounds.height()).show(leash);
                            this.mOnTaskResizeAnimationListener.onAnimationStart(taskInfo3.taskId, transaction, startAbsBounds);
                            final ValueAnimator ofObject = ValueAnimator.ofObject(new RectEvaluator(), change.getStartAbsBounds(), change.getEndAbsBounds());
                            ofObject.setDuration(336L);
                            this.mTransactionSupplier.getClass();
                            final SurfaceControl.Transaction transaction3 = new SurfaceControl.Transaction();
                            ofObject.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.desktopmode.EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda1
                                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    EnterDesktopTaskTransitionHandler enterDesktopTaskTransitionHandler = EnterDesktopTaskTransitionHandler.this;
                                    ValueAnimator valueAnimator2 = ofObject;
                                    SurfaceControl.Transaction transaction4 = transaction3;
                                    SurfaceControl surfaceControl = leash;
                                    ActivityManager.RunningTaskInfo runningTaskInfo = taskInfo3;
                                    enterDesktopTaskTransitionHandler.getClass();
                                    Rect rect = (Rect) valueAnimator2.getAnimatedValue();
                                    transaction4.setPosition(surfaceControl, rect.left, rect.top).setWindowCrop(surfaceControl, rect.width(), rect.height()).show(surfaceControl);
                                    enterDesktopTaskTransitionHandler.mOnTaskResizeAnimationListener.onBoundsChange(runningTaskInfo.taskId, transaction4, rect);
                                }
                            });
                            ofObject.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.desktopmode.EnterDesktopTaskTransitionHandler.1
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public final void onAnimationEnd(Animator animator) {
                                    EnterDesktopTaskTransitionHandler.this.mOnTaskResizeAnimationListener.onAnimationEnd(taskInfo3.taskId);
                                    ShellExecutor shellExecutor = EnterDesktopTaskTransitionHandler.this.mTransitions.mMainExecutor;
                                    final Transitions.TransitionFinishCallback transitionFinishCallback2 = transitionFinishCallback;
                                    ((HandlerExecutor) shellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.desktopmode.EnterDesktopTaskTransitionHandler$1$$ExternalSyntheticLambda0
                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            Transitions.TransitionFinishCallback.this.onTransitionFinished(null);
                                        }
                                    });
                                    EnterDesktopTaskTransitionHandler.this.mInteractionJankMonitor.end(112);
                                }
                            });
                            ofObject.start();
                            z = true;
                            z2 = z | z2;
                        }
                    }
                }
                z = false;
                z2 = z | z2;
            }
        }
        this.mPendingTransitionTokens.remove(iBinder);
        return z2;
    }
}

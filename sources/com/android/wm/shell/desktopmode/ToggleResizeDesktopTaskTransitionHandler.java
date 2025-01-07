package com.android.wm.shell.desktopmode;

import android.animation.Animator;
import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ToggleResizeDesktopTaskTransitionHandler implements Transitions.TransitionHandler {
    public Animator boundsAnimator;
    public Rect initialBounds;
    public final InteractionJankMonitor interactionJankMonitor;
    public DesktopModeWindowDecorViewModel.DragStartListenerImpl onTaskResizeAnimationListener;
    public final RectEvaluator rectEvaluator = new RectEvaluator(new Rect());
    public final Transitions transitions;

    public ToggleResizeDesktopTaskTransitionHandler(Transitions transitions, InteractionJankMonitor interactionJankMonitor) {
        this.transitions = transitions;
        this.interactionJankMonitor = interactionJankMonitor;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        return null;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, final SurfaceControl.Transaction transaction, final SurfaceControl.Transaction transaction2, final Transitions.TransitionFinishCallback transitionFinishCallback) {
        ActivityManager.RunningTaskInfo taskInfo;
        List changes = transitionInfo.getChanges();
        ArrayList arrayList = new ArrayList();
        for (Object obj : changes) {
            TransitionInfo.Change change = (TransitionInfo.Change) obj;
            Intrinsics.checkNotNull(change);
            if ((change.getFlags() & 2) == 0 && change.getTaskInfo() != null && ((taskInfo = change.getTaskInfo()) == null || taskInfo.taskId != -1)) {
                if (change.getMode() == 6) {
                    arrayList.add(obj);
                }
            }
        }
        if (arrayList.size() != 1) {
            throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(arrayList.size(), "Expected 1 relevant change but found: "));
        }
        TransitionInfo.Change change2 = (TransitionInfo.Change) CollectionsKt.first((List) arrayList);
        final SurfaceControl leash = change2.getLeash();
        ActivityManager.RunningTaskInfo taskInfo2 = change2.getTaskInfo();
        if (taskInfo2 == null) {
            throw new IllegalStateException("Required value was null.");
        }
        final int i = taskInfo2.taskId;
        Rect rect = this.initialBounds;
        if (rect == null) {
            rect = change2.getStartAbsBounds();
        }
        final Rect rect2 = rect;
        final Rect endAbsBounds = change2.getEndAbsBounds();
        final SurfaceControl.Transaction transaction3 = new SurfaceControl.Transaction();
        Animator animator = this.boundsAnimator;
        if (animator != null) {
            animator.cancel();
        }
        ValueAnimator duration = ValueAnimator.ofObject(this.rectEvaluator, rect2, endAbsBounds).setDuration(300L);
        Intrinsics.checkNotNull(duration);
        duration.addListener(new Animator.AnimatorListener() { // from class: com.android.wm.shell.desktopmode.ToggleResizeDesktopTaskTransitionHandler$startAnimation$lambda$2$$inlined$addListener$default$1
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator2) {
                SurfaceControl.Transaction transaction4 = transaction2;
                SurfaceControl surfaceControl = leash;
                Rect rect3 = endAbsBounds;
                transaction4.setPosition(surfaceControl, rect3.left, rect3.top).setWindowCrop(leash, endAbsBounds.width(), endAbsBounds.height()).show(leash);
                DesktopModeWindowDecorViewModel.DragStartListenerImpl dragStartListenerImpl = this.onTaskResizeAnimationListener;
                if (dragStartListenerImpl == null) {
                    dragStartListenerImpl = null;
                }
                dragStartListenerImpl.onAnimationEnd(i);
                transitionFinishCallback.onTransitionFinished(null);
                ToggleResizeDesktopTaskTransitionHandler toggleResizeDesktopTaskTransitionHandler = this;
                toggleResizeDesktopTaskTransitionHandler.initialBounds = null;
                toggleResizeDesktopTaskTransitionHandler.boundsAnimator = null;
                toggleResizeDesktopTaskTransitionHandler.interactionJankMonitor.end(104);
                this.interactionJankMonitor.end(118);
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator2) {
                SurfaceControl.Transaction transaction4 = transaction;
                SurfaceControl surfaceControl = leash;
                Rect rect3 = rect2;
                transaction4.setPosition(surfaceControl, rect3.left, rect3.top).setWindowCrop(leash, rect2.width(), rect2.height()).show(leash);
                DesktopModeWindowDecorViewModel.DragStartListenerImpl dragStartListenerImpl = this.onTaskResizeAnimationListener;
                if (dragStartListenerImpl == null) {
                    dragStartListenerImpl = null;
                }
                dragStartListenerImpl.onAnimationStart(i, transaction, rect2);
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator2) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationRepeat(Animator animator2) {
            }
        });
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.desktopmode.ToggleResizeDesktopTaskTransitionHandler$startAnimation$1$3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                Rect rect3 = (Rect) valueAnimator.getAnimatedValue();
                transaction3.setPosition(leash, rect3.left, rect3.top).setWindowCrop(leash, rect3.width(), rect3.height()).show(leash);
                DesktopModeWindowDecorViewModel.DragStartListenerImpl dragStartListenerImpl = this.onTaskResizeAnimationListener;
                if (dragStartListenerImpl == null) {
                    dragStartListenerImpl = null;
                }
                dragStartListenerImpl.onBoundsChange(i, transaction3, rect3);
            }
        });
        duration.start();
        this.boundsAnimator = duration;
        return true;
    }
}

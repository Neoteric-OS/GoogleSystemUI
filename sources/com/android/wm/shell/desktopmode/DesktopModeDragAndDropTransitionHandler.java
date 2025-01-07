package com.android.wm.shell.desktopmode;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeDragAndDropTransitionHandler implements Transitions.TransitionHandler {
    public final List pendingTransitionTokens = new ArrayList();
    public final Transitions transitions;

    public DesktopModeDragAndDropTransitionHandler(Transitions transitions) {
        this.transitions = transitions;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        return null;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, final Transitions.TransitionFinishCallback transitionFinishCallback) {
        ActivityManager.RunningTaskInfo taskInfo;
        if (!this.pendingTransitionTokens.contains(iBinder)) {
            return false;
        }
        List changes = transitionInfo.getChanges();
        ArrayList arrayList = new ArrayList();
        for (Object obj : changes) {
            TransitionInfo.Change change = (TransitionInfo.Change) obj;
            Intrinsics.checkNotNull(change);
            if (change.getTaskInfo() != null && ((taskInfo = change.getTaskInfo()) == null || taskInfo.taskId != -1)) {
                if (change.getMode() == 1) {
                    arrayList.add(obj);
                }
            }
        }
        if (arrayList.size() != 1) {
            throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(arrayList.size(), "Expected 1 relevant change but found: "));
        }
        TransitionInfo.Change change2 = (TransitionInfo.Change) CollectionsKt.first((List) arrayList);
        final SurfaceControl leash = change2.getLeash();
        Rect endAbsBounds = change2.getEndAbsBounds();
        transaction.hide(leash).setWindowCrop(leash, endAbsBounds.width(), endAbsBounds.height()).apply();
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setFloatValues(0.0f, 1.0f);
        valueAnimator.setDuration(300L);
        final SurfaceControl.Transaction transaction3 = new SurfaceControl.Transaction();
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.desktopmode.DesktopModeDragAndDropTransitionHandler$startAnimation$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                transitionFinishCallback.onTransitionFinished(null);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                transaction3.show(leash);
                transaction3.apply();
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.desktopmode.DesktopModeDragAndDropTransitionHandler$startAnimation$2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                transaction3.setAlpha(leash, valueAnimator2.getAnimatedFraction());
                transaction3.apply();
            }
        });
        valueAnimator.start();
        this.pendingTransitionTokens.remove(iBinder);
        return true;
    }
}

package com.android.wm.shell.activityembedding;

import android.animation.Animator;
import android.content.Context;
import android.os.IBinder;
import android.util.ArrayMap;
import android.util.Log;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActivityEmbeddingController implements Transitions.TransitionHandler {
    final ActivityEmbeddingAnimationRunner mAnimationRunner;
    public final ArrayMap mTransitionCallbacks = new ArrayMap();
    final Transitions mTransitions;

    public ActivityEmbeddingController(Context context, ShellInit shellInit, Transitions transitions) {
        Objects.requireNonNull(context);
        Objects.requireNonNull(transitions);
        this.mTransitions = transitions;
        this.mAnimationRunner = new ActivityEmbeddingAnimationRunner(context, this);
        shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.activityembedding.ActivityEmbeddingController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityEmbeddingController activityEmbeddingController = ActivityEmbeddingController.this;
                activityEmbeddingController.mTransitions.addHandler(activityEmbeddingController);
            }
        }, this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x009c, code lost:
    
        return false;
     */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0107 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:? A[LOOP:5: B:62:0x00c1->B:72:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean shouldAnimate(android.window.TransitionInfo r8) {
        /*
            Method dump skipped, instructions count: 265
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.activityembedding.ActivityEmbeddingController.shouldAnimate(android.window.TransitionInfo):boolean");
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        return null;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        Animator animator = this.mAnimationRunner.mActiveAnimator;
        if (animator == null) {
            Log.e("ActivityEmbeddingAnimR", "No active ActivityEmbedding animator running but mergeAnimation is trying to cancel one.");
        } else {
            animator.end();
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void setAnimScaleSetting(float f) {
        this.mAnimationRunner.mAnimationSpec.mTransitionAnimationScaleSetting = f;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(final IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        if (!shouldAnimate(transitionInfo)) {
            return false;
        }
        this.mTransitionCallbacks.put(iBinder, transitionFinishCallback);
        final ActivityEmbeddingAnimationRunner activityEmbeddingAnimationRunner = this.mAnimationRunner;
        activityEmbeddingAnimationRunner.getClass();
        ArrayList arrayList = new ArrayList();
        Animator createAnimator = activityEmbeddingAnimationRunner.createAnimator(transitionInfo, transaction, transaction2, new Runnable() { // from class: com.android.wm.shell.activityembedding.ActivityEmbeddingAnimationRunner$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                ActivityEmbeddingAnimationRunner activityEmbeddingAnimationRunner2 = ActivityEmbeddingAnimationRunner.this;
                Transitions.TransitionFinishCallback transitionFinishCallback2 = (Transitions.TransitionFinishCallback) activityEmbeddingAnimationRunner2.mController.mTransitionCallbacks.remove(iBinder);
                if (transitionFinishCallback2 == null) {
                    throw new IllegalStateException("No finish callback found");
                }
                transitionFinishCallback2.onTransitionFinished(null);
            }
        }, arrayList);
        activityEmbeddingAnimationRunner.mActiveAnimator = createAnimator;
        if (arrayList.isEmpty()) {
            transaction.apply();
            createAnimator.start();
        } else {
            transaction.apply(true);
            SurfaceControl.Transaction transaction3 = new SurfaceControl.Transaction();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Consumer) it.next()).accept(transaction3);
            }
            transaction3.apply();
            createAnimator.start();
        }
        return true;
    }
}

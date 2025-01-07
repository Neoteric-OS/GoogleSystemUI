package com.android.wm.shell.transition;

import android.view.SurfaceControl;
import android.window.TransitionInfo;
import com.android.wm.shell.keyguard.KeyguardTransitionHandler;
import com.android.wm.shell.pip.PipTransitionController;
import com.android.wm.shell.transition.DefaultMixedHandler;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class MixedTransitionHelper {
    /* JADX WARN: Removed duplicated region for block: B:57:0x0133 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0135 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean animateEnterPipFromSplit(final com.android.wm.shell.transition.DefaultMixedHandler.MixedTransition r18, android.window.TransitionInfo r19, android.view.SurfaceControl.Transaction r20, android.view.SurfaceControl.Transaction r21, final com.android.wm.shell.transition.Transitions.TransitionFinishCallback r22, com.android.wm.shell.transition.Transitions r23, com.android.wm.shell.transition.DefaultMixedHandler r24, com.android.wm.shell.pip.PipTransitionController r25, final com.android.wm.shell.splitscreen.StageCoordinator r26, boolean r27) {
        /*
            Method dump skipped, instructions count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.transition.MixedTransitionHelper.animateEnterPipFromSplit(com.android.wm.shell.transition.DefaultMixedHandler$MixedTransition, android.window.TransitionInfo, android.view.SurfaceControl$Transaction, android.view.SurfaceControl$Transaction, com.android.wm.shell.transition.Transitions$TransitionFinishCallback, com.android.wm.shell.transition.Transitions, com.android.wm.shell.transition.DefaultMixedHandler, com.android.wm.shell.pip.PipTransitionController, com.android.wm.shell.splitscreen.StageCoordinator, boolean):boolean");
    }

    public static boolean animateKeyguard(DefaultMixedHandler.MixedTransition mixedTransition, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback, KeyguardTransitionHandler keyguardTransitionHandler, PipTransitionController pipTransitionController) {
        if (mixedTransition.mFinishT == null) {
            mixedTransition.mFinishT = transaction2;
            mixedTransition.mFinishCB = transitionFinishCallback;
        }
        if (pipTransitionController != null) {
            pipTransitionController.syncPipSurfaceState(transitionInfo, transaction, transaction2);
        }
        return mixedTransition.startSubAnimation(keyguardTransitionHandler, transitionInfo, transaction, transaction2);
    }
}

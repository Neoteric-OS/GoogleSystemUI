package com.android.wm.shell.transition;

import android.animation.Animator;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.IBinder;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import com.android.wm.shell.activityembedding.ActivityEmbeddingController;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.keyguard.KeyguardTransitionHandler;
import com.android.wm.shell.pip.PipTransitionController;
import com.android.wm.shell.splitscreen.SplitScreenTransitions;
import com.android.wm.shell.splitscreen.SplitScreenTransitions$$ExternalSyntheticLambda0;
import com.android.wm.shell.splitscreen.StageCoordinator;
import com.android.wm.shell.transition.DefaultMixedHandler;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.unfold.UnfoldTransitionHandler;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DefaultMixedTransition extends DefaultMixedHandler.MixedTransition {
    public final ActivityEmbeddingController mActivityEmbeddingController;
    public final UnfoldTransitionHandler mUnfoldHandler;

    public DefaultMixedTransition(int i, IBinder iBinder, Transitions transitions, DefaultMixedHandler defaultMixedHandler, PipTransitionController pipTransitionController, StageCoordinator stageCoordinator, KeyguardTransitionHandler keyguardTransitionHandler, UnfoldTransitionHandler unfoldTransitionHandler, ActivityEmbeddingController activityEmbeddingController) {
        super(i, iBinder, transitions, defaultMixedHandler, pipTransitionController, stageCoordinator, keyguardTransitionHandler);
        this.mUnfoldHandler = unfoldTransitionHandler;
        this.mActivityEmbeddingController = activityEmbeddingController;
        if (i != 8) {
            return;
        }
        this.mLeftoversHandler = unfoldTransitionHandler;
    }

    @Override // com.android.wm.shell.transition.DefaultMixedHandler.MixedTransition
    public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        PipTransitionController pipTransitionController = this.mPipHandler;
        int i = this.mType;
        if (i == 1) {
            if (this.mAnimType != 1) {
                pipTransitionController.end();
                return;
            }
            SplitScreenTransitions splitScreenTransitions = this.mSplitHandler.mSplitTransitions;
            if (splitScreenTransitions.mActiveRemoteHandler != null) {
                return;
            }
            for (int size = splitScreenTransitions.mAnimations.size() - 1; size >= 0; size--) {
                Animator animator = (Animator) splitScreenTransitions.mAnimations.get(size);
                ShellExecutor shellExecutor = splitScreenTransitions.mTransitions.mAnimExecutor;
                Objects.requireNonNull(animator);
                ((HandlerExecutor) shellExecutor).execute(new SplitScreenTransitions$$ExternalSyntheticLambda0(3, animator));
            }
            pipTransitionController.end();
            Transitions.TransitionHandler transitionHandler = this.mLeftoversHandler;
            if (transitionHandler != null) {
                transitionHandler.mergeAnimation(iBinder, transitionInfo, transaction, iBinder2, transitionFinishCallback);
                return;
            }
            return;
        }
        if (i != 2) {
            if (i == 3) {
                pipTransitionController.end();
                Transitions.TransitionHandler transitionHandler2 = this.mLeftoversHandler;
                if (transitionHandler2 != null) {
                    transitionHandler2.mergeAnimation(iBinder, transitionInfo, transaction, iBinder2, transitionFinishCallback);
                    return;
                }
                return;
            }
            if (i == 5) {
                this.mKeyguardHandler.mergeAnimation(iBinder, transitionInfo, transaction, iBinder2, transitionFinishCallback);
                return;
            }
            if (i != 11) {
                if (i == 8) {
                    this.mUnfoldHandler.mergeAnimation(iBinder, transitionInfo, transaction, iBinder2, transitionFinishCallback);
                } else {
                    if (i != 9) {
                        throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Playing a default mixed transition with unknown or illegal type: "));
                    }
                    pipTransitionController.end();
                    this.mActivityEmbeddingController.mergeAnimation(iBinder, transitionInfo, transaction, iBinder2, transitionFinishCallback);
                }
            }
        }
    }

    @Override // com.android.wm.shell.transition.DefaultMixedHandler.MixedTransition
    public final void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
        PipTransitionController pipTransitionController = this.mPipHandler;
        int i = this.mType;
        if (i == 1) {
            pipTransitionController.onTransitionConsumed(iBinder, z, transaction);
        } else if (i == 3) {
            this.mLeftoversHandler.onTransitionConsumed(iBinder, z, transaction);
        } else if (i == 5) {
            this.mKeyguardHandler.onTransitionConsumed(iBinder, z, transaction);
        } else if (i == 8) {
            this.mUnfoldHandler.getClass();
        } else if (i == 9) {
            pipTransitionController.onTransitionConsumed(iBinder, z, transaction);
            this.mActivityEmbeddingController.getClass();
        }
        if (this.mHasRequestToRemote) {
            this.mPlayer.mRemoteTransitionHandler.onTransitionConsumed(iBinder, z, transaction);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x01b2, code lost:
    
        if (r1.startAnimation(r16.mTransition, r18, r19, r20, r14) != false) goto L76;
     */
    @Override // com.android.wm.shell.transition.DefaultMixedHandler.MixedTransition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean startAnimation(android.os.IBinder r17, android.window.TransitionInfo r18, android.view.SurfaceControl.Transaction r19, android.view.SurfaceControl.Transaction r20, final com.android.wm.shell.transition.Transitions.TransitionFinishCallback r21) {
        /*
            Method dump skipped, instructions count: 578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.transition.DefaultMixedTransition.startAnimation(android.os.IBinder, android.window.TransitionInfo, android.view.SurfaceControl$Transaction, android.view.SurfaceControl$Transaction, com.android.wm.shell.transition.Transitions$TransitionFinishCallback):boolean");
    }
}

package com.android.wm.shell.transition;

import android.window.WindowContainerTransaction;
import com.android.wm.shell.transition.DefaultMixedHandler;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DefaultMixedHandler$$ExternalSyntheticLambda4 implements Transitions.TransitionFinishCallback {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DefaultMixedHandler f$0;
    public final /* synthetic */ DefaultMixedHandler.MixedTransition f$1;
    public final /* synthetic */ Transitions.TransitionFinishCallback f$2;

    public /* synthetic */ DefaultMixedHandler$$ExternalSyntheticLambda4(DefaultMixedHandler defaultMixedHandler, DefaultMixedHandler.MixedTransition mixedTransition, Transitions.TransitionFinishCallback transitionFinishCallback, int i) {
        this.$r8$classId = i;
        this.f$0 = defaultMixedHandler;
        this.f$1 = mixedTransition;
        this.f$2 = transitionFinishCallback;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionFinishCallback
    public final void onTransitionFinished(WindowContainerTransaction windowContainerTransaction) {
        switch (this.$r8$classId) {
            case 0:
                DefaultMixedHandler defaultMixedHandler = this.f$0;
                defaultMixedHandler.getClass();
                DefaultMixedTransition defaultMixedTransition = (DefaultMixedTransition) this.f$1;
                defaultMixedTransition.mInFlightSubAnimations--;
                defaultMixedTransition.joinFinishArgs(windowContainerTransaction);
                if (defaultMixedTransition.mInFlightSubAnimations <= 0) {
                    defaultMixedHandler.mActiveTransitions.remove(defaultMixedTransition);
                    this.f$2.onTransitionFinished(defaultMixedTransition.mFinishWCT);
                    break;
                }
                break;
            case 1:
                this.f$0.mActiveTransitions.remove((DefaultMixedTransition) this.f$1);
                this.f$2.onTransitionFinished(windowContainerTransaction);
                break;
            case 2:
                DefaultMixedHandler defaultMixedHandler2 = this.f$0;
                defaultMixedHandler2.getClass();
                DefaultMixedTransition defaultMixedTransition2 = (DefaultMixedTransition) this.f$1;
                defaultMixedTransition2.mInFlightSubAnimations--;
                defaultMixedTransition2.joinFinishArgs(windowContainerTransaction);
                if (defaultMixedTransition2.mInFlightSubAnimations <= 0) {
                    defaultMixedHandler2.mActiveTransitions.remove(defaultMixedTransition2);
                    this.f$2.onTransitionFinished(defaultMixedTransition2.mFinishWCT);
                    break;
                }
                break;
            case 3:
                this.f$0.mActiveTransitions.remove((DefaultMixedTransition) this.f$1);
                this.f$2.onTransitionFinished(windowContainerTransaction);
                break;
            default:
                this.f$0.mActiveTransitions.remove(this.f$1);
                this.f$2.onTransitionFinished(windowContainerTransaction);
                break;
        }
    }
}

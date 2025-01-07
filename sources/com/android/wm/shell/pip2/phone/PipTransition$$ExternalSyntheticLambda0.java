package com.android.wm.shell.pip2.phone;

import android.window.WindowContainerTransaction;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PipTransition$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Transitions.TransitionFinishCallback f$1;

    public /* synthetic */ PipTransition$$ExternalSyntheticLambda0(PipTransition pipTransition, Transitions.TransitionFinishCallback transitionFinishCallback, int i) {
        this.$r8$classId = i;
        this.f$0 = pipTransition;
        this.f$1 = transitionFinishCallback;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                PipTransition pipTransition = (PipTransition) this.f$0;
                Transitions.TransitionFinishCallback transitionFinishCallback = this.f$1;
                pipTransition.mPipTransitionState.setState(8, null);
                transitionFinishCallback.onTransitionFinished(null);
                break;
            case 1:
                PipTransition pipTransition2 = (PipTransition) this.f$0;
                Transitions.TransitionFinishCallback transitionFinishCallback2 = this.f$1;
                pipTransition2.getClass();
                transitionFinishCallback2.onTransitionFinished(null);
                pipTransition2.onClientDrawAtTransitionEnd();
                break;
            default:
                this.f$1.onTransitionFinished((WindowContainerTransaction) this.f$0);
                break;
        }
    }

    public /* synthetic */ PipTransition$$ExternalSyntheticLambda0(Transitions.TransitionFinishCallback transitionFinishCallback, WindowContainerTransaction windowContainerTransaction) {
        this.$r8$classId = 2;
        this.f$1 = transitionFinishCallback;
        this.f$0 = windowContainerTransaction;
    }
}

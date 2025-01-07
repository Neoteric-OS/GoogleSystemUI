package com.android.wm.shell.transition;

import android.window.TransitionInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.transition.DefaultMixedHandler;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DefaultMixedHandler$$ExternalSyntheticLambda5 implements Transitions.TransitionFinishCallback {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ DefaultMixedHandler$$ExternalSyntheticLambda5(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionFinishCallback
    public final void onTransitionFinished(WindowContainerTransaction windowContainerTransaction) {
        switch (this.$r8$classId) {
            case 0:
                ((DefaultMixedHandler) this.f$0).mPipHandler.mShellTaskOrganizer.applyTransaction(windowContainerTransaction);
                ((DefaultMixedHandler$$ExternalSyntheticLambda4) this.f$1).onTransitionFinished(null);
                break;
            default:
                TransitionInfo transitionInfo = (TransitionInfo) this.f$1;
                DefaultMixedHandler.MixedTransition mixedTransition = (DefaultMixedHandler.MixedTransition) this.f$0;
                mixedTransition.mInFlightSubAnimations--;
                if (mixedTransition.mInfo != null && ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -1915658714613334671L, 21, Long.valueOf(r1.getDebugId()), Long.valueOf(transitionInfo.getDebugId()), Long.valueOf(mixedTransition.mInFlightSubAnimations));
                }
                mixedTransition.joinFinishArgs(windowContainerTransaction);
                if (mixedTransition.mInFlightSubAnimations == 0) {
                    mixedTransition.mFinishCB.onTransitionFinished(mixedTransition.mFinishWCT);
                    break;
                }
                break;
        }
    }
}

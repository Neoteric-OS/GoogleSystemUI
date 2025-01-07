package com.android.wm.shell.transition;

import android.util.Pair;
import android.window.RemoteTransition;
import com.android.wm.shell.transition.RemoteTransitionHandler;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class RemoteTransitionHandler$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ RemoteTransitionHandler$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((Transitions.TransitionFinishCallback) obj).onTransitionFinished(null);
                break;
            default:
                RemoteTransitionHandler.RemoteDeathHandler remoteDeathHandler = (RemoteTransitionHandler.RemoteDeathHandler) obj;
                for (int size = remoteDeathHandler.this$0.mFilters.size() - 1; size >= 0; size--) {
                    if (remoteDeathHandler.mRemote.equals(((RemoteTransition) ((Pair) remoteDeathHandler.this$0.mFilters.get(size)).second).asBinder())) {
                        remoteDeathHandler.this$0.mFilters.remove(size);
                    }
                }
                for (int size2 = remoteDeathHandler.this$0.mRequestedRemotes.size() - 1; size2 >= 0; size2--) {
                    if (remoteDeathHandler.mRemote.equals(((RemoteTransition) remoteDeathHandler.this$0.mRequestedRemotes.valueAt(size2)).asBinder())) {
                        remoteDeathHandler.this$0.mRequestedRemotes.removeAt(size2);
                    }
                }
                for (int size3 = remoteDeathHandler.mPendingFinishCallbacks.size() - 1; size3 >= 0; size3--) {
                    ((Transitions.TransitionFinishCallback) remoteDeathHandler.mPendingFinishCallbacks.get(size3)).onTransitionFinished(null);
                }
                remoteDeathHandler.mPendingFinishCallbacks.clear();
                break;
        }
    }
}

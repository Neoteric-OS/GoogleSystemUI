package com.android.wm.shell.recents;

import android.app.ActivityTaskManager;
import android.os.RemoteException;
import android.util.Slog;
import android.view.RemoteAnimationTarget;
import android.window.WindowAnimationState;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.recents.RecentsTransitionHandler;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class RecentsTransitionHandler$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ RecentsTransitionHandler$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                RecentsTransitionHandler recentsTransitionHandler = (RecentsTransitionHandler) obj;
                recentsTransitionHandler.mRecentTasksController.mTransitionHandler = recentsTransitionHandler;
                Transitions transitions = recentsTransitionHandler.mTransitions;
                transitions.addHandler(recentsTransitionHandler);
                transitions.registerObserver(recentsTransitionHandler);
                break;
            case 1:
                RecentsTransitionHandler.RecentsController recentsController = (RecentsTransitionHandler.RecentsController) obj;
                if (recentsController.mTransition != null) {
                    try {
                        ActivityTaskManager.getService().detachNavigationBarFromApp(recentsController.mTransition);
                        break;
                    } catch (RemoteException e) {
                        Slog.e("RecentsTransitionHandler", "Failed to detach the navigation bar from app", e);
                        return;
                    }
                }
                break;
            case 2:
                int i2 = RecentsTransitionHandler.RecentsController.$r8$clinit;
                ((RecentsTransitionHandler.RecentsController) obj).getClass();
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, 7080950910277792428L, 1, Long.valueOf(r5.mInstanceId));
                }
                Slog.e("RecentsTransitionHandler", "Tried to hand off an animation without a valid takeover handler.");
                break;
            default:
                int i3 = RecentsTransitionHandler.RecentsController.$r8$clinit;
                ((RecentsTransitionHandler.RecentsController) obj).finishInner(true, false, null);
                break;
        }
    }

    public /* synthetic */ RecentsTransitionHandler$$ExternalSyntheticLambda0(RecentsTransitionHandler.RecentsController recentsController, RemoteAnimationTarget[] remoteAnimationTargetArr, WindowAnimationState[] windowAnimationStateArr) {
        this.$r8$classId = 2;
        this.f$0 = recentsController;
    }
}

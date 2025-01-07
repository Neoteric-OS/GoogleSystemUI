package com.android.wm.shell.taskview;

import android.app.ActivityManager;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.ArrayMap;
import android.view.SurfaceControl;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TaskViewTransitions implements Transitions.TransitionHandler {
    public final Transitions mTransitions;
    public final ArrayMap mTaskViews = new ArrayMap();
    public final ArrayList mPending = new ArrayList();
    public final boolean[] mRegistered = {false};

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class PendingTransition {
        public IBinder mClaimed;
        public final IBinder mLaunchCookie;
        public final TaskViewTaskController mTaskView;
        public final int mType;
        public final WindowContainerTransaction mWct;

        public PendingTransition(int i, WindowContainerTransaction windowContainerTransaction, TaskViewTaskController taskViewTaskController, IBinder iBinder) {
            this.mType = i;
            this.mWct = windowContainerTransaction;
            this.mTaskView = taskViewTaskController;
            this.mLaunchCookie = iBinder;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TaskViewRequestedState {
        public Rect mBounds;
        public boolean mVisible;
    }

    public TaskViewTransitions(Transitions transitions) {
        this.mTransitions = transitions;
    }

    public final PendingTransition findPending(IBinder iBinder) {
        for (int i = 0; i < this.mPending.size(); i++) {
            if (((PendingTransition) this.mPending.get(i)).mClaimed == iBinder) {
                return (PendingTransition) this.mPending.get(i);
            }
        }
        return null;
    }

    public PendingTransition findPendingOpeningTransition(TaskViewTaskController taskViewTaskController) {
        for (int size = this.mPending.size() - 1; size >= 0; size--) {
            if (((PendingTransition) this.mPending.get(size)).mTaskView == taskViewTaskController && TransitionUtil.isOpeningType(((PendingTransition) this.mPending.get(size)).mType)) {
                return (PendingTransition) this.mPending.get(size);
            }
        }
        return null;
    }

    public final TaskViewTaskController findTaskView(ActivityManager.RunningTaskInfo runningTaskInfo) {
        for (int i = 0; i < this.mTaskViews.size(); i++) {
            if (((TaskViewTaskController) this.mTaskViews.keyAt(i)).mTaskInfo != null && runningTaskInfo.token.equals(((TaskViewTaskController) this.mTaskViews.keyAt(i)).mTaskInfo.token)) {
                return (TaskViewTaskController) this.mTaskViews.keyAt(i);
            }
        }
        return null;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        TaskViewTaskController findTaskView;
        ActivityManager.RunningTaskInfo triggerTask = transitionRequestInfo.getTriggerTask();
        if (triggerTask == null || (findTaskView = findTaskView(triggerTask)) == null || !TransitionUtil.isClosingType(transitionRequestInfo.getType())) {
            return null;
        }
        PendingTransition pendingTransition = new PendingTransition(transitionRequestInfo.getType(), null, findTaskView, null);
        pendingTransition.mClaimed = iBinder;
        this.mPending.add(pendingTransition);
        return new WindowContainerTransaction();
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
        PendingTransition findPending;
        if (z && (findPending = findPending(iBinder)) != null) {
            this.mPending.remove(findPending);
            startNextTransition();
        }
    }

    public final void setTaskViewVisible(TaskViewTaskController taskViewTaskController, boolean z) {
        if (this.mTaskViews.get(taskViewTaskController) == null || ((TaskViewRequestedState) this.mTaskViews.get(taskViewTaskController)).mVisible == z || taskViewTaskController.mTaskInfo == null) {
            return;
        }
        ((TaskViewRequestedState) this.mTaskViews.get(taskViewTaskController)).mVisible = z;
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        windowContainerTransaction.setHidden(taskViewTaskController.mTaskInfo.token, !z);
        windowContainerTransaction.setBounds(taskViewTaskController.mTaskInfo.token, ((TaskViewRequestedState) this.mTaskViews.get(taskViewTaskController)).mBounds);
        this.mPending.add(new PendingTransition(z ? 3 : 4, windowContainerTransaction, taskViewTaskController, null));
        startNextTransition();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x01bf  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x021c  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01a7  */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.app.ActivityManager$RunningTaskInfo, android.view.SurfaceControl] */
    /* JADX WARN: Type inference failed for: r8v3 */
    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean startAnimation(android.os.IBinder r19, android.window.TransitionInfo r20, final android.view.SurfaceControl.Transaction r21, android.view.SurfaceControl.Transaction r22, com.android.wm.shell.transition.Transitions.TransitionFinishCallback r23) {
        /*
            Method dump skipped, instructions count: 735
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.taskview.TaskViewTransitions.startAnimation(android.os.IBinder, android.window.TransitionInfo, android.view.SurfaceControl$Transaction, android.view.SurfaceControl$Transaction, com.android.wm.shell.transition.Transitions$TransitionFinishCallback):boolean");
    }

    public final void startNextTransition() {
        if (this.mPending.isEmpty()) {
            return;
        }
        PendingTransition pendingTransition = (PendingTransition) this.mPending.get(0);
        if (pendingTransition.mClaimed != null) {
            return;
        }
        pendingTransition.mClaimed = this.mTransitions.startTransition(pendingTransition.mType, pendingTransition.mWct, this);
    }
}

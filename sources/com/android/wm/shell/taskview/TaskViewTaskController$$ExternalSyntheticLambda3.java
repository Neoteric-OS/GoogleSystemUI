package com.android.wm.shell.taskview;

import android.graphics.Rect;
import android.os.Binder;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.taskview.TaskViewTransitions;
import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TaskViewTaskController$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TaskViewTaskController f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ TaskViewTaskController$$ExternalSyntheticLambda3(TaskViewTaskController taskViewTaskController, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = taskViewTaskController;
        this.f$1 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                TaskViewTaskController taskViewTaskController = this.f$0;
                Rect rect = (Rect) this.f$1;
                TaskViewTransitions taskViewTransitions = taskViewTaskController.mTaskViewTransitions;
                TaskViewTransitions.TaskViewRequestedState taskViewRequestedState = (TaskViewTransitions.TaskViewRequestedState) taskViewTransitions.mTaskViews.get(taskViewTaskController);
                if (taskViewRequestedState == null || Objects.equals(rect, taskViewRequestedState.mBounds)) {
                    return;
                }
                taskViewRequestedState.mBounds.set(rect);
                if (taskViewRequestedState.mVisible && taskViewTransitions.mPending.isEmpty()) {
                    WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                    windowContainerTransaction.setBounds(taskViewTaskController.mTaskInfo.token, rect);
                    taskViewTransitions.mPending.add(new TaskViewTransitions.PendingTransition(6, windowContainerTransaction, taskViewTaskController, null));
                    taskViewTransitions.startNextTransition();
                    return;
                }
                return;
            default:
                TaskViewTaskController taskViewTaskController2 = this.f$0;
                Binder binder = (Binder) this.f$1;
                ShellTaskOrganizer shellTaskOrganizer = taskViewTaskController2.mTaskOrganizer;
                synchronized (shellTaskOrganizer.mLock) {
                    shellTaskOrganizer.mLaunchCookieToListener.put(binder, taskViewTaskController2);
                }
                return;
        }
    }
}

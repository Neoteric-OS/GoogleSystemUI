package com.android.wm.shell.taskview;

import android.app.ActivityManager;
import android.graphics.Rect;
import android.util.ArrayMap;
import android.util.Log;
import android.view.SurfaceControl;
import android.window.TaskAppearedInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.taskview.TaskViewTransitions;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class TaskViewTaskController$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TaskViewTaskController f$0;

    public /* synthetic */ TaskViewTaskController$$ExternalSyntheticLambda2(TaskViewTaskController taskViewTaskController, int i) {
        this.$r8$classId = i;
        this.f$0 = taskViewTaskController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        TaskViewTaskController taskViewTaskController = this.f$0;
        switch (i) {
            case 0:
                taskViewTaskController.getClass();
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                windowContainerTransaction.removeTask(taskViewTaskController.mTaskToken);
                TaskViewTransitions taskViewTransitions = taskViewTaskController.mTaskViewTransitions;
                TaskViewTransitions.TaskViewRequestedState taskViewRequestedState = (TaskViewTransitions.TaskViewRequestedState) taskViewTransitions.mTaskViews.get(taskViewTaskController);
                if (taskViewRequestedState != null) {
                    taskViewRequestedState.mVisible = false;
                }
                taskViewTransitions.mPending.add(new TaskViewTransitions.PendingTransition(2, windowContainerTransaction, taskViewTaskController, null));
                taskViewTransitions.startNextTransition();
                return;
            case 1:
                taskViewTaskController.mListener.onReleased();
                return;
            case 2:
                taskViewTaskController.mListener.onInitialized();
                return;
            case 3:
                TaskViewTransitions taskViewTransitions2 = taskViewTaskController.mTaskViewTransitions;
                if (taskViewTransitions2 != null) {
                    synchronized (taskViewTransitions2.mRegistered) {
                        try {
                            boolean[] zArr = taskViewTransitions2.mRegistered;
                            if (!zArr[0]) {
                                zArr[0] = true;
                                taskViewTransitions2.mTransitions.addHandler(taskViewTransitions2);
                            }
                        } finally {
                        }
                    }
                    ArrayMap arrayMap = taskViewTransitions2.mTaskViews;
                    TaskViewTransitions.TaskViewRequestedState taskViewRequestedState2 = new TaskViewTransitions.TaskViewRequestedState();
                    taskViewRequestedState2.mBounds = new Rect();
                    arrayMap.put(taskViewTaskController, taskViewRequestedState2);
                    return;
                }
                return;
            case 4:
                if (taskViewTaskController.mTaskToken == null) {
                    return;
                }
                if (taskViewTaskController.isUsingShellTransitions()) {
                    taskViewTaskController.mTaskViewTransitions.setTaskViewVisible(taskViewTaskController, true);
                    return;
                } else {
                    taskViewTaskController.mTransaction.reparent(taskViewTaskController.mTaskLeash, taskViewTaskController.mSurfaceControl).show(taskViewTaskController.mTaskLeash).apply();
                    taskViewTaskController.updateTaskVisibility();
                    return;
                }
            case 5:
                if (taskViewTaskController.mTaskToken == null) {
                    return;
                }
                if (taskViewTaskController.isUsingShellTransitions()) {
                    taskViewTaskController.mTaskViewTransitions.setTaskViewVisible(taskViewTaskController, false);
                    return;
                } else {
                    taskViewTaskController.mTransaction.reparent(taskViewTaskController.mTaskLeash, null).apply();
                    taskViewTaskController.updateTaskVisibility();
                    return;
                }
            default:
                TaskViewTransitions taskViewTransitions3 = taskViewTaskController.mTaskViewTransitions;
                if (taskViewTransitions3 != null) {
                    taskViewTransitions3.mTaskViews.remove(taskViewTaskController);
                }
                ShellTaskOrganizer shellTaskOrganizer = taskViewTaskController.mTaskOrganizer;
                synchronized (shellTaskOrganizer.mLock) {
                    try {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
                            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, -2293990469534138854L, 0, String.valueOf(taskViewTaskController));
                        }
                        if (shellTaskOrganizer.mTaskListeners.indexOfValue(taskViewTaskController) == -1) {
                            Log.w("ShellTaskOrganizer", "No registered listener found");
                        } else {
                            ArrayList arrayList = new ArrayList();
                            for (int size = shellTaskOrganizer.mTasks.size() - 1; size >= 0; size--) {
                                TaskAppearedInfo taskAppearedInfo = (TaskAppearedInfo) shellTaskOrganizer.mTasks.valueAt(size);
                                if (shellTaskOrganizer.getTaskListener(taskAppearedInfo.getTaskInfo(), false) == taskViewTaskController) {
                                    arrayList.add(taskAppearedInfo);
                                }
                            }
                            for (int size2 = shellTaskOrganizer.mTaskListeners.size() - 1; size2 >= 0; size2--) {
                                if (shellTaskOrganizer.mTaskListeners.valueAt(size2) == taskViewTaskController) {
                                    shellTaskOrganizer.mTaskListeners.removeAt(size2);
                                }
                            }
                            for (int size3 = arrayList.size() - 1; size3 >= 0; size3--) {
                                TaskAppearedInfo taskAppearedInfo2 = (TaskAppearedInfo) arrayList.get(size3);
                                ActivityManager.RunningTaskInfo taskInfo = taskAppearedInfo2.getTaskInfo();
                                SurfaceControl leash = taskAppearedInfo2.getLeash();
                                ShellTaskOrganizer.TaskListener taskListener = shellTaskOrganizer.getTaskListener(taskAppearedInfo2.getTaskInfo(), false);
                                if (taskListener != null && taskListener != null) {
                                    taskListener.onTaskAppeared(taskInfo, leash);
                                }
                            }
                        }
                    } finally {
                    }
                }
                taskViewTaskController.resetTaskInfo();
                return;
        }
    }
}

package com.android.wm.shell.desktopmode;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.IBinder;
import android.util.ArraySet;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.WindowContainerTransaction;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.protolog.ProtoLog;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopTasksLimiter {
    public final Context context;
    public final Handler handler;
    public final InteractionJankMonitor interactionJankMonitor;
    public final int maxTasksLimit;
    public final MinimizeTransitionObserver minimizeTransitionObserver;
    public final ShellTaskOrganizer shellTaskOrganizer;
    public final DesktopModeTaskRepository taskRepository;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LeftoverMinimizedTasksRemover implements DesktopModeTaskRepository.ActiveTasksListener {
        public LeftoverMinimizedTasksRemover() {
        }

        @Override // com.android.wm.shell.desktopmode.DesktopModeTaskRepository.ActiveTasksListener
        public final void onActiveTasksChanged(int i) {
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            DesktopTasksLimiter desktopTasksLimiter = DesktopTasksLimiter.this;
            if (desktopTasksLimiter.taskRepository.getActiveNonMinimizedOrderedTasks(i).isEmpty()) {
                DesktopModeTaskRepository desktopModeTaskRepository = desktopTasksLimiter.taskRepository;
                desktopModeTaskRepository.getClass();
                DesktopModeTaskRepository.DesktopTaskData desktopTaskData = (DesktopModeTaskRepository.DesktopTaskData) desktopModeTaskRepository.desktopTaskDataByDisplayId.get(i);
                ArraySet arraySet = new ArraySet(desktopTaskData != null ? desktopTaskData.minimizedTasks : null);
                if (!arraySet.isEmpty()) {
                    ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopTasksLimiter: removing leftover minimized tasks: %s", new Object[]{arraySet});
                    Iterator it = arraySet.iterator();
                    while (it.hasNext()) {
                        Integer num = (Integer) it.next();
                        Intrinsics.checkNotNull(num);
                        ActivityManager.RunningTaskInfo runningTaskInfo = desktopTasksLimiter.shellTaskOrganizer.getRunningTaskInfo(num.intValue());
                        if (runningTaskInfo != null) {
                            windowContainerTransaction.removeTask(runningTaskInfo.token);
                        }
                    }
                }
            }
            desktopTasksLimiter.shellTaskOrganizer.applyTransaction(windowContainerTransaction);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MinimizeTransitionObserver implements Transitions.TransitionObserver {
        public final Map pendingTransitionTokensAndTasks = new LinkedHashMap();
        public final Map activeTransitionTokensAndTasks = new LinkedHashMap();

        public MinimizeTransitionObserver() {
        }

        @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
        public final void onTransitionFinished(IBinder iBinder, boolean z) {
            ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopTasksLimiter: transition %s finished", new Object[]{iBinder});
            if (this.activeTransitionTokensAndTasks.remove(iBinder) != null) {
                DesktopTasksLimiter desktopTasksLimiter = DesktopTasksLimiter.this;
                if (z) {
                    desktopTasksLimiter.interactionJankMonitor.cancel(109);
                } else {
                    desktopTasksLimiter.interactionJankMonitor.end(109);
                }
            }
            this.pendingTransitionTokensAndTasks.remove(iBinder);
        }

        @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
        public final void onTransitionMerged(IBinder iBinder, IBinder iBinder2) {
            if (this.activeTransitionTokensAndTasks.remove(iBinder) != null) {
                DesktopTasksLimiter.this.interactionJankMonitor.end(109);
            }
            TaskDetails taskDetails = (TaskDetails) this.pendingTransitionTokensAndTasks.remove(iBinder);
            if (taskDetails != null) {
                this.pendingTransitionTokensAndTasks.put(iBinder2, taskDetails);
            }
        }

        @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
        public final void onTransitionReady(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
            Object obj;
            TaskDetails taskDetails = (TaskDetails) this.pendingTransitionTokensAndTasks.remove(iBinder);
            if (taskDetails == null) {
                return;
            }
            DesktopTasksLimiter desktopTasksLimiter = DesktopTasksLimiter.this;
            DesktopModeTaskRepository desktopModeTaskRepository = desktopTasksLimiter.taskRepository;
            int i = taskDetails.taskId;
            if (desktopModeTaskRepository.isActiveTask(i)) {
                Iterator it = transitionInfo.getChanges().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    ActivityManager.RunningTaskInfo taskInfo = ((TransitionInfo.Change) obj).getTaskInfo();
                    if (taskInfo != null && taskInfo.taskId == i) {
                        break;
                    }
                }
                TransitionInfo.Change change = (TransitionInfo.Change) obj;
                DesktopModeTaskRepository desktopModeTaskRepository2 = desktopTasksLimiter.taskRepository;
                boolean z = true;
                if (change == null) {
                    z = true ^ desktopModeTaskRepository2.isVisibleTask(i);
                } else if (change.getMode() != 4) {
                    z = false;
                }
                if (!z) {
                    ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopTasksLimiter: task %d is not reordered to back nor invis", new Object[]{Integer.valueOf(i)});
                    return;
                }
                taskDetails.transitionInfo = transitionInfo;
                this.activeTransitionTokensAndTasks.put(iBinder, taskDetails);
                ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopTasksLimiter: marking %d as minimized", new Object[]{Integer.valueOf(i)});
                int i2 = taskDetails.displayId;
                Object[] objArr = {Integer.valueOf(i2), Integer.valueOf(i)};
                desktopModeTaskRepository2.getClass();
                DesktopModeTaskRepository.logD("Minimize Task: display=%d, task=%d", objArr);
                desktopModeTaskRepository2.desktopTaskDataByDisplayId.getOrCreate(i2).minimizedTasks.add(Integer.valueOf(i));
            }
        }

        @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
        public final void onTransitionStarting(IBinder iBinder) {
            TransitionInfo transitionInfo;
            TaskDetails taskDetails = (TaskDetails) this.activeTransitionTokensAndTasks.get(iBinder);
            if (taskDetails == null || (transitionInfo = taskDetails.transitionInfo) == null) {
                return;
            }
            DesktopTasksLimiter desktopTasksLimiter = DesktopTasksLimiter.this;
            desktopTasksLimiter.interactionJankMonitor.begin(transitionInfo != null ? transitionInfo.getRootLeash() : null, desktopTasksLimiter.context, desktopTasksLimiter.handler, 109);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TaskDetails {
        public final int displayId;
        public final int taskId;
        public TransitionInfo transitionInfo = null;

        public TaskDetails(int i, int i2) {
            this.displayId = i;
            this.taskId = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TaskDetails)) {
                return false;
            }
            TaskDetails taskDetails = (TaskDetails) obj;
            return this.displayId == taskDetails.displayId && this.taskId == taskDetails.taskId && Intrinsics.areEqual(this.transitionInfo, taskDetails.transitionInfo);
        }

        public final int hashCode() {
            int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.taskId, Integer.hashCode(this.displayId) * 31, 31);
            TransitionInfo transitionInfo = this.transitionInfo;
            return m + (transitionInfo == null ? 0 : transitionInfo.hashCode());
        }

        public final String toString() {
            return "TaskDetails(displayId=" + this.displayId + ", taskId=" + this.taskId + ", transitionInfo=" + this.transitionInfo + ")";
        }
    }

    public DesktopTasksLimiter(Transitions transitions, DesktopModeTaskRepository desktopModeTaskRepository, ShellTaskOrganizer shellTaskOrganizer, int i, InteractionJankMonitor interactionJankMonitor, Context context, Handler handler) {
        this.taskRepository = desktopModeTaskRepository;
        this.shellTaskOrganizer = shellTaskOrganizer;
        this.maxTasksLimit = i;
        this.interactionJankMonitor = interactionJankMonitor;
        this.context = context;
        this.handler = handler;
        MinimizeTransitionObserver minimizeTransitionObserver = new MinimizeTransitionObserver();
        this.minimizeTransitionObserver = minimizeTransitionObserver;
        LeftoverMinimizedTasksRemover leftoverMinimizedTasksRemover = new LeftoverMinimizedTasksRemover();
        if (i <= 0) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("DesktopTasksLimiter should not be created with a maxTasksLimit at 0 or less. Current value: ", ".", i).toString());
        }
        transitions.registerObserver(minimizeTransitionObserver);
        desktopModeTaskRepository.activeTasksListeners.add(leftoverMinimizedTasksRemover);
        ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopTasksLimiter: starting limiter with a maximum of %d tasks", new Object[]{Integer.valueOf(i)});
    }

    public static List createOrderedTaskListWithGivenTaskInFront(int i, List list) {
        List singletonList = Collections.singletonList(Integer.valueOf(i));
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((Number) obj).intValue() != i) {
                arrayList.add(obj);
            }
        }
        return CollectionsKt.plus((Iterable) arrayList, (Collection) singletonList);
    }

    public final ActivityManager.RunningTaskInfo getTaskToMinimizeIfNeeded(List list) {
        if (((ArrayList) list).size() <= this.maxTasksLimit) {
            ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopTasksLimiter: no need to minimize; tasks below limit", new Object[0]);
            return null;
        }
        int intValue = ((Number) CollectionsKt.last(list)).intValue();
        ActivityManager.RunningTaskInfo runningTaskInfo = this.shellTaskOrganizer.getRunningTaskInfo(intValue);
        if (runningTaskInfo != null) {
            return runningTaskInfo;
        }
        ProtoLog.e(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopTasksLimiter: taskToMinimize(taskId = %d) == null", new Object[]{Integer.valueOf(intValue)});
        return null;
    }

    public final Transitions.TransitionObserver getTransitionObserver() {
        return this.minimizeTransitionObserver;
    }

    public static /* synthetic */ void getLeftoverMinimizedTasksRemover$annotations() {
    }
}

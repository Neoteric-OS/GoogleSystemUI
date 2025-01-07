package com.android.wm.shell.desktopmode;

import android.app.ActivityManager;
import android.app.TaskInfo;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.IBinder;
import android.os.SystemProperties;
import android.os.Trace;
import android.util.SparseArray;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import androidx.core.util.SparseArrayKt;
import com.android.internal.logging.InstanceId;
import com.android.internal.logging.InstanceIdSequence;
import com.android.internal.protolog.ProtoLog;
import com.android.internal.util.FrameworkStatsLog;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeLoggerTransitionObserver implements Transitions.TransitionObserver {
    public final DesktopModeEventLogger desktopModeEventLogger;
    public final Lazy idSequence$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.wm.shell.desktopmode.DesktopModeLoggerTransitionObserver$idSequence$2
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new InstanceIdSequence(Integer.MAX_VALUE);
        }
    });
    public InstanceId loggerInstanceId;
    public final SparseArray tasksSavedForRecents;
    public final Transitions transitions;
    public final SparseArray visibleFreeformTaskInfos;
    public boolean wasPreviousTransitionExitByScreenOff;
    public boolean wasPreviousTransitionExitToOverview;

    public DesktopModeLoggerTransitionObserver(Context context, ShellInit shellInit, Transitions transitions, DesktopModeEventLogger desktopModeEventLogger) {
        this.transitions = transitions;
        this.desktopModeEventLogger = desktopModeEventLogger;
        if (Transitions.ENABLE_SHELL_TRANSITIONS && DesktopModeStatus.canEnterDesktopMode(context)) {
            shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.desktopmode.DesktopModeLoggerTransitionObserver.1
                @Override // java.lang.Runnable
                public final void run() {
                    DesktopModeLoggerTransitionObserver desktopModeLoggerTransitionObserver = DesktopModeLoggerTransitionObserver.this;
                    desktopModeLoggerTransitionObserver.transitions.registerObserver(desktopModeLoggerTransitionObserver);
                }
            }, this);
        }
        this.visibleFreeformTaskInfos = new SparseArray();
        this.tasksSavedForRecents = new SparseArray();
    }

    public static DesktopModeEventLogger$Companion$TaskUpdate buildTaskUpdateForTask(TaskInfo taskInfo, int i) {
        Rect bounds = taskInfo.configuration.windowConfiguration.getBounds();
        Point point = taskInfo.positionInParent;
        return new DesktopModeEventLogger$Companion$TaskUpdate(taskInfo.taskId, taskInfo.effectiveUid, bounds.height(), bounds.width(), point.x, point.y, i);
    }

    public static boolean isExitToRecentsTransition(TransitionInfo transitionInfo) {
        return transitionInfo.getType() == 3 && transitionInfo.getFlags() == 128;
    }

    public static ActivityManager.RunningTaskInfo requireTaskInfo(TransitionInfo.Change change) {
        ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
        if (taskInfo != null) {
            return taskInfo;
        }
        throw new IllegalStateException("Expected TaskInfo in the Change");
    }

    public final void addTaskInfosToCachedMap(TaskInfo taskInfo) {
        this.visibleFreeformTaskInfos.set(taskInfo.taskId, taskInfo);
    }

    public final Integer getLoggerSessionId() {
        InstanceId instanceId = this.loggerInstanceId;
        if (instanceId != null) {
            return Integer.valueOf(instanceId.getId());
        }
        return null;
    }

    public final void identifyAndLogTaskUpdates(int i, SparseArray sparseArray, SparseArray sparseArray2) {
        DesktopModeEventLogger desktopModeEventLogger;
        int size = sparseArray2.size();
        int i2 = 0;
        while (true) {
            desktopModeEventLogger = this.desktopModeEventLogger;
            if (i2 >= size) {
                break;
            }
            int keyAt = sparseArray2.keyAt(i2);
            DesktopModeEventLogger$Companion$TaskUpdate buildTaskUpdateForTask = buildTaskUpdateForTask((TaskInfo) sparseArray2.valueAt(i2), sparseArray2.size());
            TaskInfo taskInfo = (TaskInfo) sparseArray.get(keyAt);
            int i3 = buildTaskUpdateForTask.instanceId;
            if (taskInfo == null) {
                desktopModeEventLogger.getClass();
                ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopModeLogger: Logging task added, session: %s taskId: %s", new Object[]{Integer.valueOf(i), Integer.valueOf(i3)});
                DesktopModeEventLogger.logTaskUpdate(1, i, buildTaskUpdateForTask);
                Trace.setCounter(32L, "desktop_mode_visible_tasks", sparseArray2.size());
                SystemProperties.set("debug.tracing.desktop_mode_visible_tasks", String.valueOf(sparseArray2.size()));
            } else if (!buildTaskUpdateForTask(taskInfo, sparseArray2.size()).equals(buildTaskUpdateForTask)) {
                desktopModeEventLogger.getClass();
                ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopModeLogger: Logging task info changed, session: %s taskId: %s", new Object[]{Integer.valueOf(i), Integer.valueOf(i3)});
                DesktopModeEventLogger.logTaskUpdate(3, i, buildTaskUpdateForTask);
            }
            i2++;
        }
        int size2 = sparseArray.size();
        for (int i4 = 0; i4 < size2; i4++) {
            int keyAt2 = sparseArray.keyAt(i4);
            TaskInfo taskInfo2 = (TaskInfo) sparseArray.valueAt(i4);
            if (sparseArray2.indexOfKey(keyAt2) < 0) {
                DesktopModeEventLogger$Companion$TaskUpdate buildTaskUpdateForTask2 = buildTaskUpdateForTask(taskInfo2, sparseArray2.size());
                desktopModeEventLogger.getClass();
                ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopModeLogger: Logging task remove, session: %s taskId: %s", new Object[]{Integer.valueOf(i), Integer.valueOf(buildTaskUpdateForTask2.instanceId)});
                DesktopModeEventLogger.logTaskUpdate(2, i, buildTaskUpdateForTask2);
                Trace.setCounter(32L, "desktop_mode_visible_tasks", sparseArray2.size());
                SystemProperties.set("debug.tracing.desktop_mode_visible_tasks", String.valueOf(sparseArray2.size()));
            }
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionReady(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        SparseArray sparseArray;
        DesktopModeEventLogger$Companion$EnterReason desktopModeEventLogger$Companion$EnterReason;
        InstanceId instanceId;
        DesktopModeEventLogger$Companion$ExitReason desktopModeEventLogger$Companion$ExitReason;
        if (isExitToRecentsTransition(transitionInfo) && this.tasksSavedForRecents.size() == 0) {
            ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopModeLogger: Recents animation running, saving tasks for later", new Object[0]);
            SparseArrayKt.putAll(this.tasksSavedForRecents, this.visibleFreeformTaskInfos);
        }
        if (transitionInfo.getType() == 12) {
            sparseArray = new SparseArray();
        } else {
            List changes = transitionInfo.getChanges();
            ArrayList arrayList = new ArrayList();
            for (Object obj : changes) {
                TransitionInfo.Change change = (TransitionInfo.Change) obj;
                if (change.getTaskInfo() != null && requireTaskInfo(change).taskId != -1) {
                    arrayList.add(obj);
                }
            }
            ArrayList<TransitionInfo.Change> arrayList2 = new ArrayList();
            for (Object obj2 : arrayList) {
                TransitionInfo.Change change2 = (TransitionInfo.Change) obj2;
                Intrinsics.checkNotNull(change2);
                if (requireTaskInfo(change2).getWindowingMode() == 5 || this.visibleFreeformTaskInfos.indexOfKey(requireTaskInfo(change2).taskId) >= 0) {
                    arrayList2.add(obj2);
                }
            }
            SparseArray sparseArray2 = new SparseArray();
            SparseArrayKt.putAll(sparseArray2, this.visibleFreeformTaskInfos);
            for (TransitionInfo.Change change3 : arrayList2) {
                Intrinsics.checkNotNull(change3);
                ActivityManager.RunningTaskInfo requireTaskInfo = requireTaskInfo(change3);
                if (this.visibleFreeformTaskInfos.indexOfKey(requireTaskInfo.taskId) >= 0 && ((TaskInfo) this.visibleFreeformTaskInfos.get(requireTaskInfo.taskId)).getWindowingMode() == 5 && requireTaskInfo.getWindowingMode() != 5) {
                    sparseArray2.remove(requireTaskInfo.taskId);
                } else if (!TransitionUtil.isOpeningType(change3.getMode()) && (TransitionUtil.isClosingType(change3.getMode()) || change3.getMode() != 6)) {
                    sparseArray2.remove(requireTaskInfo.taskId);
                } else {
                    sparseArray2.put(requireTaskInfo.taskId, requireTaskInfo);
                }
            }
            ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopModeLogger: taskInfo map after processing changes %s", new Object[]{Integer.valueOf(sparseArray2.size())});
            sparseArray = sparseArray2;
        }
        if (transitionInfo.getType() == 0 && transitionInfo.getFlags() == 0 && this.tasksSavedForRecents.size() != 0) {
            ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopModeLogger: Canceled recents animation, restoring tasks", new Object[0]);
            SparseArray sparseArray3 = this.tasksSavedForRecents;
            SparseArray sparseArray4 = new SparseArray(sparseArray3.size() + sparseArray.size());
            SparseArrayKt.putAll(sparseArray4, sparseArray);
            SparseArrayKt.putAll(sparseArray4, sparseArray3);
            this.tasksSavedForRecents.clear();
            sparseArray = sparseArray4;
        }
        SparseArray sparseArray5 = this.visibleFreeformTaskInfos;
        int size = sparseArray.size();
        DesktopModeEventLogger desktopModeEventLogger = this.desktopModeEventLogger;
        if (size == 0 && sparseArray5.size() != 0 && (instanceId = this.loggerInstanceId) != null) {
            Intrinsics.checkNotNull(instanceId);
            identifyAndLogTaskUpdates(instanceId.getId(), sparseArray5, sparseArray);
            InstanceId instanceId2 = this.loggerInstanceId;
            Intrinsics.checkNotNull(instanceId2);
            int id = instanceId2.getId();
            if (transitionInfo.getType() == 12) {
                this.wasPreviousTransitionExitByScreenOff = true;
                desktopModeEventLogger$Companion$ExitReason = DesktopModeEventLogger$Companion$ExitReason.SCREEN_OFF;
            } else if (transitionInfo.getType() == 2) {
                desktopModeEventLogger$Companion$ExitReason = DesktopModeEventLogger$Companion$ExitReason.TASK_FINISHED;
            } else if (transitionInfo.getType() == 1105) {
                desktopModeEventLogger$Companion$ExitReason = DesktopModeEventLogger$Companion$ExitReason.DRAG_TO_EXIT;
            } else if (transitionInfo.getType() == 1106) {
                desktopModeEventLogger$Companion$ExitReason = DesktopModeEventLogger$Companion$ExitReason.APP_HANDLE_MENU_BUTTON_EXIT;
            } else if (transitionInfo.getType() == 1107) {
                desktopModeEventLogger$Companion$ExitReason = DesktopModeEventLogger$Companion$ExitReason.KEYBOARD_SHORTCUT_EXIT;
            } else if (isExitToRecentsTransition(transitionInfo)) {
                desktopModeEventLogger$Companion$ExitReason = DesktopModeEventLogger$Companion$ExitReason.RETURN_HOME_OR_OVERVIEW;
            } else {
                ProtoLog.w(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "Unknown exit reason for transition type: %s", new Object[]{Integer.valueOf(transitionInfo.getType())});
                desktopModeEventLogger$Companion$ExitReason = DesktopModeEventLogger$Companion$ExitReason.UNKNOWN_EXIT;
            }
            desktopModeEventLogger.getClass();
            ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopModeLogger: Logging session exit, session: %s reason: %s", new Object[]{Integer.valueOf(id), desktopModeEventLogger$Companion$ExitReason.name()});
            FrameworkStatsLog.write(818, 2, 0, desktopModeEventLogger$Companion$ExitReason.getReason(), id);
            this.loggerInstanceId = null;
        } else if (sparseArray.size() != 0 && sparseArray5.size() == 0 && this.loggerInstanceId == null) {
            InstanceId newInstanceId = ((InstanceIdSequence) this.idSequence$delegate.getValue()).newInstanceId();
            this.loggerInstanceId = newInstanceId;
            Intrinsics.checkNotNull(newInstanceId);
            int id2 = newInstanceId.getId();
            if (transitionInfo.getType() == 11 || (transitionInfo.getType() == 4 && this.wasPreviousTransitionExitByScreenOff)) {
                desktopModeEventLogger$Companion$EnterReason = DesktopModeEventLogger$Companion$EnterReason.SCREEN_ON;
            } else if (transitionInfo.getType() == 1011) {
                desktopModeEventLogger$Companion$EnterReason = DesktopModeEventLogger$Companion$EnterReason.APP_HANDLE_DRAG;
            } else if (transitionInfo.getType() == 1101) {
                desktopModeEventLogger$Companion$EnterReason = DesktopModeEventLogger$Companion$EnterReason.APP_HANDLE_MENU_BUTTON;
            } else if (transitionInfo.getType() == 1102) {
                desktopModeEventLogger$Companion$EnterReason = DesktopModeEventLogger$Companion$EnterReason.APP_FROM_OVERVIEW;
            } else if (transitionInfo.getType() == 1103) {
                desktopModeEventLogger$Companion$EnterReason = DesktopModeEventLogger$Companion$EnterReason.KEYBOARD_SHORTCUT_ENTER;
            } else if (transitionInfo.getType() == 3) {
                desktopModeEventLogger$Companion$EnterReason = DesktopModeEventLogger$Companion$EnterReason.OVERVIEW;
            } else if (this.wasPreviousTransitionExitToOverview) {
                desktopModeEventLogger$Companion$EnterReason = DesktopModeEventLogger$Companion$EnterReason.OVERVIEW;
            } else if (transitionInfo.getType() == 1) {
                desktopModeEventLogger$Companion$EnterReason = DesktopModeEventLogger$Companion$EnterReason.APP_FREEFORM_INTENT;
            } else {
                ProtoLog.w(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "Unknown enter reason for transition type: %s", new Object[]{Integer.valueOf(transitionInfo.getType())});
                desktopModeEventLogger$Companion$EnterReason = DesktopModeEventLogger$Companion$EnterReason.UNKNOWN_ENTER;
            }
            this.wasPreviousTransitionExitByScreenOff = false;
            desktopModeEventLogger.getClass();
            ProtoLog.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopModeLogger: Logging session enter, session: %s reason: %s", new Object[]{Integer.valueOf(id2), desktopModeEventLogger$Companion$EnterReason.name()});
            FrameworkStatsLog.write(818, 1, desktopModeEventLogger$Companion$EnterReason.getReason(), 0, id2);
            InstanceId instanceId3 = this.loggerInstanceId;
            Intrinsics.checkNotNull(instanceId3);
            identifyAndLogTaskUpdates(instanceId3.getId(), sparseArray5, sparseArray);
        } else {
            InstanceId instanceId4 = this.loggerInstanceId;
            if (instanceId4 != null) {
                Intrinsics.checkNotNull(instanceId4);
                identifyAndLogTaskUpdates(instanceId4.getId(), sparseArray5, sparseArray);
            }
        }
        this.visibleFreeformTaskInfos.clear();
        SparseArrayKt.putAll(this.visibleFreeformTaskInfos, sparseArray);
        this.wasPreviousTransitionExitToOverview = isExitToRecentsTransition(transitionInfo);
    }

    public final void setLoggerSessionId(int i) {
        this.loggerInstanceId = InstanceId.fakeInstanceId(i);
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionStarting(IBinder iBinder) {
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionFinished(IBinder iBinder, boolean z) {
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionMerged(IBinder iBinder, IBinder iBinder2) {
    }
}

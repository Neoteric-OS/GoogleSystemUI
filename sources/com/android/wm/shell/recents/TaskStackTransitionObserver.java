package com.android.wm.shell.recents;

import android.app.ActivityManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Slog;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.flags.DesktopModeFlags;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TaskStackTransitionObserver implements Transitions.TransitionObserver {
    public final Lazy transitions;
    public final Map transitionToTransitionChanges = new LinkedHashMap();
    public final ArrayMap taskStackTransitionObserverListeners = new ArrayMap();

    public TaskStackTransitionObserver(Lazy lazy, ShellInit shellInit) {
        this.transitions = lazy;
        if (Transitions.ENABLE_SHELL_TRANSITIONS) {
            shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.recents.TaskStackTransitionObserver.1
                @Override // java.lang.Runnable
                public final void run() {
                    TaskStackTransitionObserver taskStackTransitionObserver = TaskStackTransitionObserver.this;
                    ((Transitions) taskStackTransitionObserver.transitions.get()).registerObserver(taskStackTransitionObserver);
                }
            }, this);
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionFinished(IBinder iBinder, boolean z) {
        List<ActivityManager.RunningTaskInfo> list = ((TransitionChanges) this.transitionToTransitionChanges.getOrDefault(iBinder, new TransitionChanges())).taskInfoList;
        List list2 = ((TransitionChanges) this.transitionToTransitionChanges.getOrDefault(iBinder, new TransitionChanges())).transitionTypeList;
        this.transitionToTransitionChanges.remove(iBinder);
        int i = 0;
        for (final ActivityManager.RunningTaskInfo runningTaskInfo : list) {
            int i2 = i + 1;
            if (TransitionUtil.isOpeningType(((Number) ((ArrayList) list2).get(i)).intValue()) && runningTaskInfo.getWindowingMode() == 5) {
                for (Map.Entry entry : this.taskStackTransitionObserverListeners.entrySet()) {
                    final RecentTasksController recentTasksController = (RecentTasksController) entry.getKey();
                    ((Executor) entry.getValue()).execute(new Runnable() { // from class: com.android.wm.shell.recents.TaskStackTransitionObserver$notifyTaskStackTransitionObserverListeners$1$1
                        @Override // java.lang.Runnable
                        public final void run() {
                            RecentTasksController recentTasksController2 = RecentTasksController.this;
                            ActivityManager.RunningTaskInfo runningTaskInfo2 = runningTaskInfo;
                            if (recentTasksController2.mListener == null || !DesktopModeFlags.ENABLE_TASK_STACK_OBSERVER_IN_SHELL.isTrue() || runningTaskInfo2.realActivity == null) {
                                return;
                            }
                            try {
                                recentTasksController2.mListener.onTaskMovedToFront(runningTaskInfo2);
                            } catch (RemoteException e) {
                                Slog.w("RecentTasksController", "Failed call onTaskMovedToFront", e);
                            }
                        }
                    });
                }
            }
            i = i2;
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionMerged(IBinder iBinder, IBinder iBinder2) {
        TransitionChanges transitionChanges = (TransitionChanges) this.transitionToTransitionChanges.get(iBinder);
        if (transitionChanges == null) {
            return;
        }
        this.transitionToTransitionChanges.remove(iBinder);
        TransitionChanges transitionChanges2 = (TransitionChanges) this.transitionToTransitionChanges.get(iBinder2);
        if (transitionChanges2 == null) {
            this.transitionToTransitionChanges.put(iBinder2, transitionChanges);
        } else {
            transitionChanges2.taskInfoList.addAll(transitionChanges.taskInfoList);
            transitionChanges2.transitionTypeList.addAll(transitionChanges.transitionTypeList);
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionReady(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        ActivityManager.RunningTaskInfo taskInfo;
        if (DesktopModeFlags.ENABLE_TASK_STACK_OBSERVER_IN_SHELL.isTrue()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                if ((change.getFlags() & 2) == 0 && (taskInfo = change.getTaskInfo()) != null && taskInfo.taskId != -1 && change.getMode() == 1) {
                    ActivityManager.RunningTaskInfo taskInfo2 = change.getTaskInfo();
                    if (taskInfo2 != null) {
                        arrayList.add(taskInfo2);
                    }
                    arrayList2.add(Integer.valueOf(change.getMode()));
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            this.transitionToTransitionChanges.put(iBinder, new TransitionChanges(arrayList, arrayList2));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransitionChanges {
        public final List taskInfoList;
        public final List transitionTypeList;

        public TransitionChanges(List list, List list2) {
            this.taskInfoList = list;
            this.transitionTypeList = list2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TransitionChanges)) {
                return false;
            }
            TransitionChanges transitionChanges = (TransitionChanges) obj;
            return Intrinsics.areEqual(this.taskInfoList, transitionChanges.taskInfoList) && Intrinsics.areEqual(this.transitionTypeList, transitionChanges.transitionTypeList);
        }

        public final int hashCode() {
            return this.transitionTypeList.hashCode() + (this.taskInfoList.hashCode() * 31);
        }

        public final String toString() {
            return "TransitionChanges(taskInfoList=" + this.taskInfoList + ", transitionTypeList=" + this.transitionTypeList + ")";
        }

        public /* synthetic */ TransitionChanges() {
            this(new ArrayList(), new ArrayList());
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionStarting(IBinder iBinder) {
    }
}

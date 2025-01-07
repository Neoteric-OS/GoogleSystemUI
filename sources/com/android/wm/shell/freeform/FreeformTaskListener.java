package com.android.wm.shell.freeform;

import android.app.ActivityManager;
import android.content.Context;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.util.SparseArray;
import android.view.SurfaceControl;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.LaunchAdjacentController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.WindowDecorViewModel;
import java.io.PrintWriter;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FreeformTaskListener implements ShellTaskOrganizer.TaskListener, ShellTaskOrganizer.FocusListener {
    public final Context mContext;
    public final Optional mDesktopModeTaskRepository;
    public final LaunchAdjacentController mLaunchAdjacentController;
    public final ShellTaskOrganizer mShellTaskOrganizer;
    public final SparseArray mTasks = new SparseArray();
    public final WindowDecorViewModel mWindowDecorationViewModel;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class State {
        public SurfaceControl mLeash;
        public ActivityManager.RunningTaskInfo mTaskInfo;
    }

    public FreeformTaskListener(Context context, ShellInit shellInit, ShellTaskOrganizer shellTaskOrganizer, Optional optional, LaunchAdjacentController launchAdjacentController, WindowDecorViewModel windowDecorViewModel) {
        this.mContext = context;
        this.mShellTaskOrganizer = shellTaskOrganizer;
        this.mWindowDecorationViewModel = windowDecorViewModel;
        this.mDesktopModeTaskRepository = optional;
        this.mLaunchAdjacentController = launchAdjacentController;
        if (shellInit != null) {
            shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.freeform.FreeformTaskListener$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    FreeformTaskListener freeformTaskListener = FreeformTaskListener.this;
                    ShellTaskOrganizer shellTaskOrganizer2 = freeformTaskListener.mShellTaskOrganizer;
                    shellTaskOrganizer2.addListenerForType(freeformTaskListener, -5);
                    if (DesktopModeStatus.canEnterDesktopMode(freeformTaskListener.mContext)) {
                        synchronized (shellTaskOrganizer2.mLock) {
                            try {
                                shellTaskOrganizer2.mFocusListeners.add(freeformTaskListener);
                                ActivityManager.RunningTaskInfo runningTaskInfo = shellTaskOrganizer2.mLastFocusedTaskInfo;
                                if (runningTaskInfo != null) {
                                    freeformTaskListener.onFocusTaskChanged(runningTaskInfo);
                                }
                            } finally {
                            }
                        }
                    }
                }
            }, this);
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void attachChildSurfaceToTask(int i, SurfaceControl.Builder builder) {
        builder.setParent(findTaskSurface(i));
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void dump$1(PrintWriter printWriter, String str) {
        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "  ");
        printWriter.println(str + this);
        printWriter.println(m + this.mTasks.size() + " tasks");
    }

    public final SurfaceControl findTaskSurface(int i) {
        if (this.mTasks.contains(i)) {
            return ((State) this.mTasks.get(i)).mLeash;
        }
        throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "There is no surface for taskId="));
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.FocusListener
    public final void onFocusTaskChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (runningTaskInfo.getWindowingMode() != 5) {
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, -7439399594908437276L, 13, Long.valueOf(runningTaskInfo.taskId), Boolean.valueOf(runningTaskInfo.isFocused));
        }
        if (DesktopModeStatus.canEnterDesktopMode(this.mContext) && runningTaskInfo.isFocused) {
            this.mDesktopModeTaskRepository.ifPresent(new FreeformTaskListener$$ExternalSyntheticLambda1(runningTaskInfo, 3));
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
        if (this.mTasks.get(runningTaskInfo.taskId) != null) {
            throw new IllegalStateException("Task appeared more than once: #" + runningTaskInfo.taskId);
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, -522657126447760400L, 1, Long.valueOf(runningTaskInfo.taskId));
        }
        State state = new State();
        state.mTaskInfo = runningTaskInfo;
        state.mLeash = surfaceControl;
        this.mTasks.put(runningTaskInfo.taskId, state);
        if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
            SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
            this.mWindowDecorationViewModel.onTaskOpening(runningTaskInfo, surfaceControl, transaction, transaction);
            transaction.apply();
        }
        if (DesktopModeStatus.canEnterDesktopMode(this.mContext)) {
            this.mDesktopModeTaskRepository.ifPresent(new FreeformTaskListener$$ExternalSyntheticLambda1(runningTaskInfo, 1));
        }
        updateLaunchAdjacentController();
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        State state = (State) this.mTasks.get(runningTaskInfo.taskId);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, -6374475520259048639L, 1, Long.valueOf(runningTaskInfo.taskId));
        }
        this.mWindowDecorationViewModel.onTaskInfoChanged(runningTaskInfo);
        state.mTaskInfo = runningTaskInfo;
        if (DesktopModeStatus.canEnterDesktopMode(this.mContext)) {
            this.mDesktopModeTaskRepository.ifPresent(new FreeformTaskListener$$ExternalSyntheticLambda1(runningTaskInfo, 0));
        }
        updateLaunchAdjacentController();
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, -4742266005662387936L, 1, Long.valueOf(runningTaskInfo.taskId));
        }
        this.mTasks.remove(runningTaskInfo.taskId);
        if (DesktopModeStatus.canEnterDesktopMode(this.mContext)) {
            this.mDesktopModeTaskRepository.ifPresent(new FreeformTaskListener$$ExternalSyntheticLambda1(runningTaskInfo, 2));
        }
        WindowDecorViewModel windowDecorViewModel = this.mWindowDecorationViewModel;
        windowDecorViewModel.onTaskVanished(runningTaskInfo);
        if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
            windowDecorViewModel.destroyWindowDecoration(runningTaskInfo);
        }
        updateLaunchAdjacentController();
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void reparentChildSurfaceToTask(int i, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
        transaction.reparent(surfaceControl, findTaskSurface(i));
    }

    public final String toString() {
        return "FreeformTaskListener";
    }

    public final void updateLaunchAdjacentController() {
        int i = 0;
        while (true) {
            int size = this.mTasks.size();
            LaunchAdjacentController launchAdjacentController = this.mLaunchAdjacentController;
            if (i >= size) {
                launchAdjacentController.setLaunchAdjacentEnabled(true);
                return;
            } else {
                if (((State) this.mTasks.valueAt(i)).mTaskInfo.isVisible) {
                    launchAdjacentController.setLaunchAdjacentEnabled(false);
                    return;
                }
                i++;
            }
        }
    }
}

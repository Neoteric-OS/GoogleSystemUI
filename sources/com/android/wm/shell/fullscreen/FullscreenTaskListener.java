package com.android.wm.shell.fullscreen;

import android.app.ActivityManager;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Point;
import android.util.SparseArray;
import android.view.SurfaceControl;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.fullscreen.FullscreenTaskListener;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.WindowDecorViewModel;
import java.io.PrintWriter;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FullscreenTaskListener implements ShellTaskOrganizer.TaskListener {
    public final Optional mRecentTasksOptional;
    public final ShellTaskOrganizer mShellTaskOrganizer;
    public final SyncTransactionQueue mSyncQueue;
    public final SparseArray mTasks = new SparseArray();
    public final Optional mWindowDecorViewModelOptional;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class State {
        public SurfaceControl mLeash;
        public ActivityManager.RunningTaskInfo mTaskInfo;
    }

    public FullscreenTaskListener(ShellInit shellInit, ShellTaskOrganizer shellTaskOrganizer, SyncTransactionQueue syncTransactionQueue, Optional optional, Optional optional2) {
        this.mShellTaskOrganizer = shellTaskOrganizer;
        this.mSyncQueue = syncTransactionQueue;
        this.mRecentTasksOptional = optional;
        this.mWindowDecorViewModelOptional = optional2;
        if (shellInit != null) {
            shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.fullscreen.FullscreenTaskListener$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    FullscreenTaskListener fullscreenTaskListener = FullscreenTaskListener.this;
                    fullscreenTaskListener.mShellTaskOrganizer.addListenerForType(fullscreenTaskListener, -2);
                }
            }, this);
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void attachChildSurfaceToTask(int i, SurfaceControl.Builder builder) {
        builder.setParent(findTaskSurface$1(i));
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void dump$1(PrintWriter printWriter, String str) {
        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "  ");
        printWriter.println(str + this);
        printWriter.println(m + this.mTasks.size() + " Tasks");
    }

    public final SurfaceControl findTaskSurface$1(int i) {
        if (this.mTasks.contains(i)) {
            return ((State) this.mTasks.get(i)).mLeash;
        }
        throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "There is no surface for taskId="));
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskAppeared(final ActivityManager.RunningTaskInfo runningTaskInfo, final SurfaceControl surfaceControl) {
        boolean z;
        if (this.mTasks.get(runningTaskInfo.taskId) != null) {
            throw new IllegalStateException("Task appeared more than once: #" + runningTaskInfo.taskId);
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, 1427696159067159186L, 1, Long.valueOf(runningTaskInfo.taskId));
        }
        final Point point = runningTaskInfo.positionInParent;
        State state = new State();
        state.mLeash = surfaceControl;
        state.mTaskInfo = runningTaskInfo;
        this.mTasks.put(runningTaskInfo.taskId, state);
        if (Transitions.ENABLE_SHELL_TRANSITIONS) {
            return;
        }
        this.mRecentTasksOptional.ifPresent(new FullscreenTaskListener$$ExternalSyntheticLambda0(runningTaskInfo, 1));
        if (this.mWindowDecorViewModelOptional.isPresent()) {
            SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
            z = ((WindowDecorViewModel) this.mWindowDecorViewModelOptional.get()).onTaskOpening(runningTaskInfo, surfaceControl, transaction, transaction);
            transaction.apply();
        } else {
            z = false;
        }
        if (z) {
            return;
        }
        this.mSyncQueue.runInSync(new SyncTransactionQueue.TransactionRunnable() { // from class: com.android.wm.shell.fullscreen.FullscreenTaskListener$$ExternalSyntheticLambda3
            @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
            public final void runWithTransaction(SurfaceControl.Transaction transaction2) {
                SurfaceControl surfaceControl2 = surfaceControl;
                Point point2 = point;
                ActivityManager.RunningTaskInfo runningTaskInfo2 = runningTaskInfo;
                if (surfaceControl2.isValid()) {
                    transaction2.setWindowCrop(surfaceControl2, null);
                    transaction2.setPosition(surfaceControl2, point2.x, point2.y);
                    transaction2.setAlpha(surfaceControl2, 1.0f);
                    transaction2.setMatrix(surfaceControl2, 1.0f, 0.0f, 0.0f, 1.0f);
                    if (runningTaskInfo2.isVisible) {
                        transaction2.show(surfaceControl2);
                    }
                }
            }
        });
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        final State state = (State) this.mTasks.get(runningTaskInfo.taskId);
        ActivityManager.RunningTaskInfo runningTaskInfo2 = state.mTaskInfo;
        Point point = runningTaskInfo2.positionInParent;
        boolean z = runningTaskInfo2.isVisible;
        if (this.mWindowDecorViewModelOptional.isPresent()) {
            ((WindowDecorViewModel) this.mWindowDecorViewModelOptional.get()).onTaskInfoChanged(runningTaskInfo);
        }
        state.mTaskInfo = runningTaskInfo;
        if (Transitions.ENABLE_SHELL_TRANSITIONS) {
            return;
        }
        this.mRecentTasksOptional.ifPresent(new FullscreenTaskListener$$ExternalSyntheticLambda0(runningTaskInfo, 1));
        final Point point2 = state.mTaskInfo.positionInParent;
        boolean equals = point.equals(point2);
        final boolean z2 = !z && state.mTaskInfo.isVisible;
        if (z2 || !equals) {
            this.mSyncQueue.runInSync(new SyncTransactionQueue.TransactionRunnable() { // from class: com.android.wm.shell.fullscreen.FullscreenTaskListener$$ExternalSyntheticLambda2
                @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
                public final void runWithTransaction(SurfaceControl.Transaction transaction) {
                    Point point3 = point2;
                    FullscreenTaskListener.State state2 = FullscreenTaskListener.State.this;
                    if (state2.mLeash.isValid()) {
                        if (z2) {
                            transaction.show(state2.mLeash);
                        }
                        transaction.setPosition(state2.mLeash, point3.x, point3.y);
                    }
                }
            });
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, 2639626852593250505L, 1, Long.valueOf(runningTaskInfo.taskId));
        }
        this.mTasks.remove(runningTaskInfo.taskId);
        this.mWindowDecorViewModelOptional.ifPresent(new FullscreenTaskListener$$ExternalSyntheticLambda0(runningTaskInfo, 0));
        if (!Transitions.ENABLE_SHELL_TRANSITIONS && this.mWindowDecorViewModelOptional.isPresent()) {
            ((WindowDecorViewModel) this.mWindowDecorViewModelOptional.get()).destroyWindowDecoration(runningTaskInfo);
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void reparentChildSurfaceToTask(int i, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
        transaction.reparent(surfaceControl, findTaskSurface$1(i));
    }

    public final String toString() {
        return "FullscreenTaskListener:" + ShellTaskOrganizer.taskListenerTypeToString(-2);
    }
}

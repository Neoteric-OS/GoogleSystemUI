package com.android.wm.shell.taskview;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Rect;
import android.os.Binder;
import android.util.CloseGuard;
import android.view.SurfaceControl;
import android.view.WindowInsets;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.taskview.TaskView;
import com.android.wm.shell.taskview.TaskViewTransitions;
import java.io.PrintWriter;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TaskViewTaskController implements ShellTaskOrganizer.TaskListener {
    public Rect mCaptionInsets;
    public final Binder mCaptionInsetsOwner;
    public final Context mContext;
    public final CloseGuard mGuard;
    public final boolean mHideTaskWithSurface;
    public TaskView.Listener mListener;
    public Executor mListenerExecutor;
    public boolean mNotifiedForInitialized;
    public ActivityManager.RunningTaskInfo mPendingInfo;
    public final Executor mShellExecutor;
    public SurfaceControl mSurfaceControl;
    public boolean mSurfaceCreated;
    public final SyncTransactionQueue mSyncQueue;
    public ActivityManager.RunningTaskInfo mTaskInfo;
    public SurfaceControl mTaskLeash;
    public boolean mTaskNotFound;
    public final ShellTaskOrganizer mTaskOrganizer;
    public WindowContainerToken mTaskToken;
    public TaskView mTaskViewBase;
    public final TaskViewTransitions mTaskViewTransitions;
    public final SurfaceControl.Transaction mTransaction;

    public TaskViewTaskController(Context context, ShellTaskOrganizer shellTaskOrganizer, TaskViewTransitions taskViewTransitions, SyncTransactionQueue syncTransactionQueue) {
        CloseGuard closeGuard = new CloseGuard();
        this.mGuard = closeGuard;
        this.mTransaction = new SurfaceControl.Transaction();
        this.mCaptionInsetsOwner = new Binder();
        this.mHideTaskWithSurface = true;
        this.mContext = context;
        this.mTaskOrganizer = shellTaskOrganizer;
        Executor executor = shellTaskOrganizer.getExecutor();
        this.mShellExecutor = executor;
        this.mSyncQueue = syncTransactionQueue;
        this.mTaskViewTransitions = taskViewTransitions;
        executor.execute(new TaskViewTaskController$$ExternalSyntheticLambda2(this, 3));
        closeGuard.open("release");
    }

    public final void applyCaptionInsetsIfNeeded() {
        if (this.mTaskToken == null) {
            return;
        }
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        if (this.mCaptionInsets != null) {
            windowContainerTransaction.addInsetsSource(this.mTaskToken, this.mCaptionInsetsOwner, 0, WindowInsets.Type.captionBar(), this.mCaptionInsets, (Rect[]) null, 0);
        } else {
            windowContainerTransaction.removeInsetsSource(this.mTaskToken, this.mCaptionInsetsOwner, 0, WindowInsets.Type.captionBar());
        }
        this.mTaskOrganizer.applyTransaction(windowContainerTransaction);
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void attachChildSurfaceToTask(int i, SurfaceControl.Builder builder) {
        builder.setParent(findTaskSurface$4(i));
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void dump$1(PrintWriter printWriter, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("  ");
        printWriter.println(str + this);
    }

    public final void finalize() {
        try {
            CloseGuard closeGuard = this.mGuard;
            if (closeGuard != null) {
                closeGuard.warnIfOpen();
                performRelease();
            }
        } finally {
            super.finalize();
        }
    }

    public final SurfaceControl findTaskSurface$4(int i) {
        SurfaceControl surfaceControl;
        ActivityManager.RunningTaskInfo runningTaskInfo = this.mTaskInfo;
        if (runningTaskInfo == null || (surfaceControl = this.mTaskLeash) == null || runningTaskInfo.taskId != i) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "There is no surface for taskId="));
        }
        return surfaceControl;
    }

    public final void handleAndNotifyTaskRemoval(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (runningTaskInfo != null) {
            if (this.mListener != null) {
                this.mListenerExecutor.execute(new TaskViewTaskController$$ExternalSyntheticLambda8(this, runningTaskInfo.taskId, 0));
            }
            this.mTaskViewBase.getClass();
        }
    }

    public final boolean isUsingShellTransitions() {
        TaskViewTransitions taskViewTransitions = this.mTaskViewTransitions;
        return taskViewTransitions != null && taskViewTransitions.mTransitions.mIsRegistered;
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) {
        WindowContainerToken windowContainerToken = this.mTaskToken;
        if (windowContainerToken == null || !windowContainerToken.equals(runningTaskInfo.token) || this.mListener == null) {
            return;
        }
        this.mListenerExecutor.execute(new TaskViewTaskController$$ExternalSyntheticLambda8(this, runningTaskInfo.taskId, 1));
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskAppeared(final ActivityManager.RunningTaskInfo runningTaskInfo, final SurfaceControl surfaceControl) {
        if (isUsingShellTransitions()) {
            this.mPendingInfo = runningTaskInfo;
            if (this.mTaskNotFound) {
                if (runningTaskInfo != null) {
                    handleAndNotifyTaskRemoval(runningTaskInfo);
                    WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                    windowContainerTransaction.removeTask(runningTaskInfo.token);
                    TaskViewTransitions taskViewTransitions = this.mTaskViewTransitions;
                    TaskViewTransitions.TaskViewRequestedState taskViewRequestedState = (TaskViewTransitions.TaskViewRequestedState) taskViewTransitions.mTaskViews.get(this);
                    if (taskViewRequestedState != null) {
                        taskViewRequestedState.mVisible = false;
                    }
                    taskViewTransitions.mPending.add(new TaskViewTransitions.PendingTransition(2, windowContainerTransaction, this, null));
                    taskViewTransitions.startNextTransition();
                }
                resetTaskInfo();
                return;
            }
            return;
        }
        this.mTaskInfo = runningTaskInfo;
        this.mTaskToken = runningTaskInfo.token;
        this.mTaskLeash = surfaceControl;
        if (this.mSurfaceCreated) {
            this.mTransaction.reparent(surfaceControl, this.mSurfaceControl).show(this.mTaskLeash).apply();
        } else {
            updateTaskVisibility();
        }
        this.mTaskOrganizer.setInterceptBackPressedOnTaskRoot(this.mTaskToken, true);
        this.mSyncQueue.runInSync(new SyncTransactionQueue.TransactionRunnable(runningTaskInfo, surfaceControl) { // from class: com.android.wm.shell.taskview.TaskViewTaskController$$ExternalSyntheticLambda4
            public final /* synthetic */ ActivityManager.RunningTaskInfo f$1;
            public final /* synthetic */ SurfaceControl f$2;

            @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
            public final void runWithTransaction(SurfaceControl.Transaction transaction) {
                ActivityManager.RunningTaskInfo runningTaskInfo2 = this.f$1;
                TaskView taskView = TaskViewTaskController.this.mTaskViewBase;
                if (taskView.mTaskViewTaskController.isUsingShellTransitions()) {
                    return;
                }
                taskView.onLocationChanged();
                ActivityManager.TaskDescription taskDescription = runningTaskInfo2.taskDescription;
                if (taskDescription != null) {
                    taskView.runOnViewThread(new TaskView$$ExternalSyntheticLambda0(taskView, taskDescription.getBackgroundColor(), 1));
                }
            }
        });
        if (this.mListener != null) {
            final int i = runningTaskInfo.taskId;
            final ComponentName componentName = runningTaskInfo.baseActivity;
            this.mListenerExecutor.execute(new Runnable() { // from class: com.android.wm.shell.taskview.TaskViewTaskController$$ExternalSyntheticLambda5
                @Override // java.lang.Runnable
                public final void run() {
                    TaskViewTaskController taskViewTaskController = TaskViewTaskController.this;
                    taskViewTaskController.mListener.onTaskCreated(i, componentName);
                }
            });
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        TaskView taskView = this.mTaskViewBase;
        taskView.getClass();
        ActivityManager.TaskDescription taskDescription = runningTaskInfo.taskDescription;
        if (taskDescription != null) {
            taskView.runOnViewThread(new TaskView$$ExternalSyntheticLambda0(taskView, taskDescription.getBackgroundColor(), 0));
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        WindowContainerToken windowContainerToken = this.mTaskToken;
        if (windowContainerToken == null || !windowContainerToken.equals(runningTaskInfo.token)) {
            return;
        }
        SurfaceControl surfaceControl = this.mTaskLeash;
        handleAndNotifyTaskRemoval(this.mTaskInfo);
        this.mTransaction.reparent(surfaceControl, null).apply();
        resetTaskInfo();
    }

    public final void performRelease() {
        this.mShellExecutor.execute(new TaskViewTaskController$$ExternalSyntheticLambda2(this, 6));
        this.mGuard.close();
        if (this.mListener == null || !this.mNotifiedForInitialized) {
            return;
        }
        this.mListenerExecutor.execute(new TaskViewTaskController$$ExternalSyntheticLambda2(this, 1));
        this.mNotifiedForInitialized = false;
    }

    public final void prepareActivityOptions(ActivityOptions activityOptions, Rect rect) {
        Binder binder = new Binder();
        this.mShellExecutor.execute(new TaskViewTaskController$$ExternalSyntheticLambda3(this, binder, 1));
        activityOptions.setLaunchBounds(rect);
        activityOptions.setLaunchCookie(binder);
        activityOptions.setLaunchWindowingMode(6);
        activityOptions.setRemoveWithTaskOrganizer(true);
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void reparentChildSurfaceToTask(int i, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
        transaction.reparent(surfaceControl, findTaskSurface$4(i));
    }

    public final void resetTaskInfo() {
        this.mTaskInfo = null;
        this.mTaskToken = null;
        this.mTaskLeash = null;
        this.mPendingInfo = null;
        this.mTaskNotFound = false;
    }

    public final void setWindowBounds(Rect rect) {
        if (this.mTaskToken == null) {
            return;
        }
        if (isUsingShellTransitions()) {
            this.mShellExecutor.execute(new TaskViewTaskController$$ExternalSyntheticLambda3(this, rect, 0));
            return;
        }
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        windowContainerTransaction.setBounds(this.mTaskToken, rect);
        this.mSyncQueue.queue(windowContainerTransaction);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TaskViewTaskController:");
        ActivityManager.RunningTaskInfo runningTaskInfo = this.mTaskInfo;
        sb.append(runningTaskInfo != null ? Integer.valueOf(runningTaskInfo.taskId) : "null");
        return sb.toString();
    }

    public final void updateTaskVisibility() {
        boolean z = this.mSurfaceCreated;
        if (z || this.mHideTaskWithSurface) {
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            windowContainerTransaction.setHidden(this.mTaskToken, !z);
            if (!z) {
                windowContainerTransaction.reorder(this.mTaskToken, false);
            }
            SyncTransactionQueue syncTransactionQueue = this.mSyncQueue;
            syncTransactionQueue.queue(windowContainerTransaction);
            if (this.mListener == null) {
                return;
            }
            final int i = this.mTaskInfo.taskId;
            syncTransactionQueue.runInSync(new SyncTransactionQueue.TransactionRunnable() { // from class: com.android.wm.shell.taskview.TaskViewTaskController$$ExternalSyntheticLambda12
                @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
                public final void runWithTransaction(SurfaceControl.Transaction transaction) {
                    TaskViewTaskController taskViewTaskController = TaskViewTaskController.this;
                    taskViewTaskController.mListenerExecutor.execute(new TaskViewTaskController$$ExternalSyntheticLambda8(taskViewTaskController, i, 2));
                }
            });
        }
    }
}

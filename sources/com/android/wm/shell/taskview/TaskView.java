package com.android.wm.shell.taskview;

import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.graphics.Insets;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Handler;
import android.os.IBinder;
import android.util.Slog;
import android.view.SurfaceControl;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.taskview.TaskViewTransitions;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TaskView extends SurfaceView implements SurfaceHolder.Callback, ViewTreeObserver.OnComputeInternalInsetsListener {
    public final Rect mBoundsOnScreen;
    public Insets mCaptionInsets;
    public Handler mHandler;
    public Region mObscuredTouchRegion;
    public final TaskViewTaskController mTaskViewTaskController;
    public final int[] mTmpLocation;
    public final Rect mTmpRect;
    public final Rect mTmpRootRect;

    public TaskView(Context context, TaskViewTaskController taskViewTaskController) {
        super(context, null, 0, 0, true);
        this.mTmpRect = new Rect();
        this.mTmpRootRect = new Rect();
        this.mTmpLocation = new int[2];
        this.mBoundsOnScreen = new Rect();
        this.mTaskViewTaskController = taskViewTaskController;
        taskViewTaskController.mTaskViewBase = this;
        this.mHandler = Handler.getMain();
        getHolder().addCallback(this);
    }

    @Override // android.view.SurfaceView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnComputeInternalInsetsListener(this);
        this.mHandler = getHandler();
    }

    public final void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo internalInsetsInfo) {
        if (internalInsetsInfo.touchableRegion.isEmpty()) {
            internalInsetsInfo.setTouchableInsets(3);
            View rootView = getRootView();
            rootView.getLocationInWindow(this.mTmpLocation);
            Rect rect = this.mTmpRootRect;
            int[] iArr = this.mTmpLocation;
            rect.set(iArr[0], iArr[1], rootView.getWidth(), rootView.getHeight());
            internalInsetsInfo.touchableRegion.set(this.mTmpRootRect);
        }
        getLocationInWindow(this.mTmpLocation);
        Rect rect2 = this.mTmpRect;
        int[] iArr2 = this.mTmpLocation;
        int i = iArr2[0];
        rect2.set(i, iArr2[1], getWidth() + i, getHeight() + this.mTmpLocation[1]);
        Insets insets = this.mCaptionInsets;
        if (insets != null) {
            this.mTmpRect.inset(insets);
            getBoundsOnScreen(this.mBoundsOnScreen);
            TaskViewTaskController taskViewTaskController = this.mTaskViewTaskController;
            Rect rect3 = this.mBoundsOnScreen;
            Rect rect4 = new Rect(rect3.left, rect3.top, getWidth() + rect3.right, this.mBoundsOnScreen.top + this.mCaptionInsets.top);
            Rect rect5 = taskViewTaskController.mCaptionInsets;
            if (rect5 == null || !rect5.equals(rect4)) {
                taskViewTaskController.mCaptionInsets = rect4;
                taskViewTaskController.applyCaptionInsetsIfNeeded();
            }
        }
        internalInsetsInfo.touchableRegion.op(this.mTmpRect, Region.Op.DIFFERENCE);
        Region region = this.mObscuredTouchRegion;
        if (region != null) {
            internalInsetsInfo.touchableRegion.op(region, Region.Op.UNION);
        }
    }

    @Override // android.view.SurfaceView, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnComputeInternalInsetsListener(this);
        this.mHandler = Handler.getMain();
    }

    public final void onLocationChanged() {
        getBoundsOnScreen(this.mTmpRect);
        this.mTaskViewTaskController.setWindowBounds(this.mTmpRect);
    }

    public final void removeTask() {
        TaskViewTaskController taskViewTaskController = this.mTaskViewTaskController;
        if (taskViewTaskController.mTaskToken == null) {
            Slog.w("TaskViewTaskController", "Trying to remove a task that was never added? (no taskToken)");
        } else {
            taskViewTaskController.mShellExecutor.execute(new TaskViewTaskController$$ExternalSyntheticLambda2(taskViewTaskController, 0));
        }
    }

    public final void runOnViewThread(Runnable runnable) {
        if (this.mHandler.getLooper().isCurrentThread()) {
            runnable.run();
        } else {
            this.mHandler.post(runnable);
        }
    }

    public void setHandler(Handler handler) {
        this.mHandler = handler;
    }

    public final void setListener(Executor executor, Listener listener) {
        TaskViewTaskController taskViewTaskController = this.mTaskViewTaskController;
        if (taskViewTaskController.mListener != null) {
            throw new IllegalStateException("Trying to set a listener when one has already been set");
        }
        taskViewTaskController.mListener = listener;
        taskViewTaskController.mListenerExecutor = executor;
    }

    public final void startActivity(final PendingIntent pendingIntent, final Intent intent, final ActivityOptions activityOptions, Rect rect) {
        final TaskViewTaskController taskViewTaskController = this.mTaskViewTaskController;
        taskViewTaskController.prepareActivityOptions(activityOptions, rect);
        if (taskViewTaskController.isUsingShellTransitions()) {
            taskViewTaskController.mShellExecutor.execute(new Runnable() { // from class: com.android.wm.shell.taskview.TaskViewTaskController$$ExternalSyntheticLambda14
                @Override // java.lang.Runnable
                public final void run() {
                    TaskViewTaskController taskViewTaskController2 = TaskViewTaskController.this;
                    PendingIntent pendingIntent2 = pendingIntent;
                    Intent intent2 = intent;
                    ActivityOptions activityOptions2 = activityOptions;
                    taskViewTaskController2.getClass();
                    WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                    windowContainerTransaction.sendPendingIntent(pendingIntent2, intent2, activityOptions2.toBundle());
                    IBinder launchCookie = activityOptions2.getLaunchCookie();
                    TaskViewTransitions taskViewTransitions = taskViewTaskController2.mTaskViewTransitions;
                    TaskViewTransitions.TaskViewRequestedState taskViewRequestedState = (TaskViewTransitions.TaskViewRequestedState) taskViewTransitions.mTaskViews.get(taskViewTaskController2);
                    if (taskViewRequestedState != null) {
                        taskViewRequestedState.mVisible = true;
                    }
                    taskViewTransitions.mPending.add(new TaskViewTransitions.PendingTransition(1, windowContainerTransaction, taskViewTaskController2, launchCookie));
                    taskViewTransitions.startNextTransition();
                }
            });
            return;
        }
        try {
            pendingIntent.send(taskViewTaskController.mContext, 0, intent, null, null, null, activityOptions.toBundle());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public final void startShortcutActivity(final ShortcutInfo shortcutInfo, final ActivityOptions activityOptions, Rect rect) {
        final TaskViewTaskController taskViewTaskController = this.mTaskViewTaskController;
        taskViewTaskController.prepareActivityOptions(activityOptions, rect);
        LauncherApps launcherApps = (LauncherApps) taskViewTaskController.mContext.getSystemService(LauncherApps.class);
        if (taskViewTaskController.isUsingShellTransitions()) {
            taskViewTaskController.mShellExecutor.execute(new Runnable() { // from class: com.android.wm.shell.taskview.TaskViewTaskController$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    TaskViewTaskController taskViewTaskController2 = TaskViewTaskController.this;
                    ShortcutInfo shortcutInfo2 = shortcutInfo;
                    ActivityOptions activityOptions2 = activityOptions;
                    taskViewTaskController2.getClass();
                    WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                    windowContainerTransaction.startShortcut(taskViewTaskController2.mContext.getPackageName(), shortcutInfo2, activityOptions2.toBundle());
                    IBinder launchCookie = activityOptions2.getLaunchCookie();
                    TaskViewTransitions taskViewTransitions = taskViewTaskController2.mTaskViewTransitions;
                    TaskViewTransitions.TaskViewRequestedState taskViewRequestedState = (TaskViewTransitions.TaskViewRequestedState) taskViewTransitions.mTaskViews.get(taskViewTaskController2);
                    if (taskViewRequestedState != null) {
                        taskViewRequestedState.mVisible = true;
                    }
                    taskViewTransitions.mPending.add(new TaskViewTransitions.PendingTransition(1, windowContainerTransaction, taskViewTaskController2, launchCookie));
                    taskViewTransitions.startNextTransition();
                }
            });
            return;
        }
        try {
            launcherApps.startShortcut(shortcutInfo, null, activityOptions.toBundle());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        getBoundsOnScreen(this.mTmpRect);
        this.mTaskViewTaskController.setWindowBounds(this.mTmpRect);
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        TaskViewTaskController taskViewTaskController = this.mTaskViewTaskController;
        SurfaceControl surfaceControl = getSurfaceControl();
        taskViewTaskController.mSurfaceCreated = true;
        taskViewTaskController.mSurfaceControl = surfaceControl;
        if (surfaceControl != null) {
            taskViewTaskController.mTransaction.setTrustedOverlay(surfaceControl, 1).apply();
        }
        if (taskViewTaskController.mListener != null && !taskViewTaskController.mNotifiedForInitialized) {
            taskViewTaskController.mNotifiedForInitialized = true;
            taskViewTaskController.mListenerExecutor.execute(new TaskViewTaskController$$ExternalSyntheticLambda2(taskViewTaskController, 2));
        }
        taskViewTaskController.mShellExecutor.execute(new TaskViewTaskController$$ExternalSyntheticLambda2(taskViewTaskController, 4));
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        TaskViewTaskController taskViewTaskController = this.mTaskViewTaskController;
        taskViewTaskController.mSurfaceCreated = false;
        taskViewTaskController.mSurfaceControl = null;
        taskViewTaskController.mShellExecutor.execute(new TaskViewTaskController$$ExternalSyntheticLambda2(taskViewTaskController, 5));
    }

    @Override // android.view.View
    public final String toString() {
        return this.mTaskViewTaskController.toString();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Listener {
        void onBackPressedOnTaskRoot(int i);

        void onInitialized();

        void onTaskCreated(int i, ComponentName componentName);

        void onTaskRemovalStarted(int i);

        default void onReleased() {
        }

        default void onTaskVisibilityChanged(int i, boolean z) {
        }
    }
}

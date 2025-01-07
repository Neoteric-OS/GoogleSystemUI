package com.android.wm.shell;

import android.app.ActivityManager;
import android.app.TaskInfo;
import android.content.LocusId;
import android.content.pm.ActivityInfo;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.Binder;
import android.os.IBinder;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.SparseArray;
import android.view.SurfaceControl;
import android.window.ITaskOrganizerController;
import android.window.StartingWindowInfo;
import android.window.StartingWindowRemovalInfo;
import android.window.TaskAppearedInfo;
import android.window.TaskOrganizer;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.internal.util.FrameworkStatsLog;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.compatui.CompatUIController;
import com.android.wm.shell.compatui.api.CompatUIHandler;
import com.android.wm.shell.compatui.api.CompatUIInfo;
import com.android.wm.shell.compatui.impl.CompatUIEvents;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.startingsurface.StartingWindowController;
import com.android.wm.shell.startingsurface.StartingWindowController$$ExternalSyntheticLambda0;
import com.android.wm.shell.startingsurface.StartingWindowController$$ExternalSyntheticLambda3;
import com.android.wm.shell.startingsurface.StartingWindowController$$ExternalSyntheticLambda4;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.unfold.UnfoldAnimationController;
import com.android.wm.shell.unfold.animation.UnfoldTaskAnimator;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class ShellTaskOrganizer extends TaskOrganizer {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final CompatUIHandler mCompatUI;
    public final ArraySet mFocusListeners;
    public final SurfaceControl mHomeTaskOverlayContainer;
    public ActivityManager.RunningTaskInfo mLastFocusedTaskInfo;
    public final ArrayMap mLaunchCookieToListener;
    public final Object mLock;
    public final ArraySet mLocusIdListeners;
    public final Optional mRecentTasks;
    public final ShellCommandHandler mShellCommandHandler;
    public StartingWindowController mStartingWindow;
    public final SparseArray mTaskListeners;
    public final ArraySet mTaskVanishedListeners;
    public final SparseArray mTasks;
    public final UnfoldAnimationController mUnfoldAnimationController;
    public final SparseArray mVisibleTasksWithLocusId;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface FocusListener {
        void onFocusTaskChanged(ActivityManager.RunningTaskInfo runningTaskInfo);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface LocusIdListener {
        void onVisibilityChanged(int i, LocusId locusId, boolean z);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public @interface TaskListenerType {
    }

    public ShellTaskOrganizer(ShellInit shellInit, ShellCommandHandler shellCommandHandler, ITaskOrganizerController iTaskOrganizerController, CompatUIHandler compatUIHandler, Optional optional, Optional optional2, ShellExecutor shellExecutor) {
        super(iTaskOrganizerController, shellExecutor);
        this.mTaskListeners = new SparseArray();
        this.mTasks = new SparseArray();
        this.mLaunchCookieToListener = new ArrayMap();
        this.mVisibleTasksWithLocusId = new SparseArray();
        this.mLocusIdListeners = new ArraySet();
        this.mFocusListeners = new ArraySet();
        this.mTaskVanishedListeners = new ArraySet();
        this.mLock = new Object();
        this.mHomeTaskOverlayContainer = new SurfaceControl.Builder().setName("home_task_overlay_container").setContainerLayer().setHidden(false).setCallsite("ShellTaskOrganizer.mHomeTaskOverlayContainer").build();
        this.mShellCommandHandler = shellCommandHandler;
        this.mCompatUI = compatUIHandler;
        this.mRecentTasks = optional2;
        this.mUnfoldAnimationController = (UnfoldAnimationController) optional.orElse(null);
        if (shellInit != null) {
            shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.ShellTaskOrganizer$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    final ShellTaskOrganizer shellTaskOrganizer = ShellTaskOrganizer.this;
                    shellTaskOrganizer.mShellCommandHandler.addDumpCallback(new BiConsumer() { // from class: com.android.wm.shell.ShellTaskOrganizer$$ExternalSyntheticLambda4
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            ShellTaskOrganizer shellTaskOrganizer2 = ShellTaskOrganizer.this;
                            PrintWriter printWriter = (PrintWriter) obj;
                            String str = (String) obj2;
                            synchronized (shellTaskOrganizer2.mLock) {
                                try {
                                    String str2 = str + "  ";
                                    String str3 = str2 + "  ";
                                    printWriter.println(str + "ShellTaskOrganizer");
                                    printWriter.println(str2 + shellTaskOrganizer2.mTaskListeners.size() + " Listeners");
                                    for (int size = shellTaskOrganizer2.mTaskListeners.size() + (-1); size >= 0; size += -1) {
                                        int keyAt = shellTaskOrganizer2.mTaskListeners.keyAt(size);
                                        ShellTaskOrganizer.TaskListener taskListener = (ShellTaskOrganizer.TaskListener) shellTaskOrganizer2.mTaskListeners.valueAt(size);
                                        printWriter.println(str2 + "#" + size + " " + ShellTaskOrganizer.taskListenerTypeToString(keyAt));
                                        taskListener.dump$1(printWriter, str3);
                                    }
                                    printWriter.println();
                                    printWriter.println(str2 + shellTaskOrganizer2.mTasks.size() + " Tasks");
                                    for (int size2 = shellTaskOrganizer2.mTasks.size() + (-1); size2 >= 0; size2 += -1) {
                                        int keyAt2 = shellTaskOrganizer2.mTasks.keyAt(size2);
                                        TaskAppearedInfo taskAppearedInfo = (TaskAppearedInfo) shellTaskOrganizer2.mTasks.valueAt(size2);
                                        ShellTaskOrganizer.TaskListener taskListener2 = shellTaskOrganizer2.getTaskListener(taskAppearedInfo.getTaskInfo(), false);
                                        int windowingMode = taskAppearedInfo.getTaskInfo().getWindowingMode();
                                        String str4 = "";
                                        if (taskAppearedInfo.getTaskInfo().baseActivity != null) {
                                            str4 = taskAppearedInfo.getTaskInfo().baseActivity.getPackageName();
                                        }
                                        printWriter.println(str2 + "#" + size2 + " task=" + keyAt2 + " listener=" + taskListener2 + " wmMode=" + windowingMode + " pkg=" + str4 + " bounds=" + taskAppearedInfo.getTaskInfo().getConfiguration().windowConfiguration.getBounds() + " running=" + taskAppearedInfo.getTaskInfo().isRunning + " visible=" + taskAppearedInfo.getTaskInfo().isVisible + " focused=" + taskAppearedInfo.getTaskInfo().isFocused);
                                    }
                                    printWriter.println();
                                    printWriter.println(str2 + shellTaskOrganizer2.mLaunchCookieToListener.size() + " Launch Cookies");
                                    for (int size3 = shellTaskOrganizer2.mLaunchCookieToListener.size() + (-1); size3 >= 0; size3 += -1) {
                                        printWriter.println(str2 + "#" + size3 + " cookie=" + ((IBinder) shellTaskOrganizer2.mLaunchCookieToListener.keyAt(size3)) + " listener=" + ((ShellTaskOrganizer.TaskListener) shellTaskOrganizer2.mLaunchCookieToListener.valueAt(size3)));
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                        }
                    }, shellTaskOrganizer);
                    CompatUIHandler compatUIHandler2 = shellTaskOrganizer.mCompatUI;
                    if (compatUIHandler2 != null) {
                        ((CompatUIController) compatUIHandler2).mCallback = new ShellTaskOrganizer$$ExternalSyntheticLambda0(1, shellTaskOrganizer);
                    }
                    shellTaskOrganizer.registerOrganizer();
                }
            }, this);
        }
    }

    public static int taskInfoToTaskListenerType(ActivityManager.RunningTaskInfo runningTaskInfo) {
        int windowingMode = runningTaskInfo.getWindowingMode();
        if (windowingMode == 1) {
            return -2;
        }
        if (windowingMode == 2) {
            return -4;
        }
        if (windowingMode != 5) {
            return windowingMode != 6 ? -1 : -3;
        }
        return -5;
    }

    public static String taskListenerTypeToString(int i) {
        return i != -5 ? i != -4 ? i != -3 ? i != -2 ? i != -1 ? AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "taskId#") : "TASK_LISTENER_TYPE_UNDEFINED" : "TASK_LISTENER_TYPE_FULLSCREEN" : "TASK_LISTENER_TYPE_MULTI_WINDOW" : "TASK_LISTENER_TYPE_PIP" : "TASK_LISTENER_TYPE_FREEFORM";
    }

    public final void addListenerForType(TaskListener taskListener, int... iArr) {
        synchronized (this.mLock) {
            try {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, -3661524279245587623L, 0, String.valueOf(Arrays.toString(iArr)), String.valueOf(taskListener));
                }
                for (int i : iArr) {
                    if (this.mTaskListeners.get(i) != null) {
                        throw new IllegalArgumentException("Listener for listenerType=" + i + " already exists");
                    }
                    this.mTaskListeners.put(i, taskListener);
                }
                for (int size = this.mTasks.size() - 1; size >= 0; size--) {
                    TaskAppearedInfo taskAppearedInfo = (TaskAppearedInfo) this.mTasks.valueAt(size);
                    if (getTaskListener(taskAppearedInfo.getTaskInfo(), false) == taskListener) {
                        taskListener.onTaskAppeared(taskAppearedInfo.getTaskInfo(), taskAppearedInfo.getLeash());
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void addStartingWindow(StartingWindowInfo startingWindowInfo) {
        StartingWindowController startingWindowController = this.mStartingWindow;
        if (startingWindowController != null) {
            ((HandlerExecutor) startingWindowController.mSplashScreenExecutor).execute(new StartingWindowController$$ExternalSyntheticLambda3(0, startingWindowController, startingWindowInfo));
        }
    }

    public final void copySplashScreenView(int i) {
        StartingWindowController startingWindowController = this.mStartingWindow;
        if (startingWindowController != null) {
            ((HandlerExecutor) startingWindowController.mSplashScreenExecutor).execute(new StartingWindowController$$ExternalSyntheticLambda0(startingWindowController, i, 0));
        }
    }

    public final void createRootTask(int i, TaskListener taskListener) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, -8442159261225566629L, 5, Long.valueOf(0), Long.valueOf(i), String.valueOf(taskListener.toString()));
        }
        Binder binder = new Binder();
        synchronized (this.mLock) {
            this.mLaunchCookieToListener.put(binder, taskListener);
        }
        super.createRootTask(0, i, binder, false);
    }

    public final ActivityManager.RunningTaskInfo getRunningTaskInfo(int i) {
        ActivityManager.RunningTaskInfo taskInfo;
        synchronized (this.mLock) {
            try {
                TaskAppearedInfo taskAppearedInfo = (TaskAppearedInfo) this.mTasks.get(i);
                taskInfo = taskAppearedInfo != null ? taskAppearedInfo.getTaskInfo() : null;
            } catch (Throwable th) {
                throw th;
            }
        }
        return taskInfo;
    }

    public final ArrayList getRunningTasks(int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mTasks.size(); i2++) {
            ActivityManager.RunningTaskInfo taskInfo = ((TaskAppearedInfo) this.mTasks.valueAt(i2)).getTaskInfo();
            if (taskInfo.displayId == i) {
                arrayList.add(taskInfo);
            }
        }
        return arrayList;
    }

    public final TaskListener getTaskListener(ActivityManager.RunningTaskInfo runningTaskInfo, boolean z) {
        TaskListener taskListener;
        int i = runningTaskInfo.taskId;
        ArrayList arrayList = runningTaskInfo.launchCookies;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            IBinder iBinder = (IBinder) arrayList.get(size);
            TaskListener taskListener2 = (TaskListener) this.mLaunchCookieToListener.get(iBinder);
            if (taskListener2 != null) {
                if (z) {
                    this.mLaunchCookieToListener.remove(iBinder);
                    this.mTaskListeners.put(i, taskListener2);
                }
                return taskListener2;
            }
        }
        TaskListener taskListener3 = (TaskListener) this.mTaskListeners.get(i);
        return taskListener3 != null ? taskListener3 : (!runningTaskInfo.hasParentTask() || (taskListener = (TaskListener) this.mTaskListeners.get(runningTaskInfo.parentTaskId)) == null) ? (TaskListener) this.mTaskListeners.get(taskInfoToTaskListenerType(runningTaskInfo)) : taskListener;
    }

    public final void notifyCompatUI(ActivityManager.RunningTaskInfo runningTaskInfo, TaskListener taskListener) {
        if (this.mCompatUI == null) {
            return;
        }
        if (taskListener != null && taskListener.supportCompatUI() && runningTaskInfo.appCompatTaskInfo.hasCompatUI() && runningTaskInfo.isVisible) {
            ((CompatUIController) this.mCompatUI).onCompatInfoChanged(new CompatUIInfo(runningTaskInfo, taskListener));
        } else {
            ((CompatUIController) this.mCompatUI).onCompatInfoChanged(new CompatUIInfo(runningTaskInfo, null));
        }
    }

    public final void notifyLocusIdChange(int i, LocusId locusId, boolean z) {
        for (int i2 = 0; i2 < this.mLocusIdListeners.size(); i2++) {
            ((LocusIdListener) this.mLocusIdListeners.valueAt(i2)).onVisibilityChanged(i, locusId, z);
        }
    }

    public final void notifyLocusVisibilityIfNeeded(TaskInfo taskInfo) {
        int i = taskInfo.taskId;
        LocusId locusId = (LocusId) this.mVisibleTasksWithLocusId.get(i);
        boolean equals = Objects.equals(locusId, taskInfo.mTopActivityLocusId);
        if (locusId == null) {
            LocusId locusId2 = taskInfo.mTopActivityLocusId;
            if (locusId2 == null || !taskInfo.isVisible) {
                return;
            }
            this.mVisibleTasksWithLocusId.put(i, locusId2);
            notifyLocusIdChange(i, taskInfo.mTopActivityLocusId, true);
            return;
        }
        if (equals && !taskInfo.isVisible) {
            this.mVisibleTasksWithLocusId.remove(i);
            notifyLocusIdChange(i, taskInfo.mTopActivityLocusId, false);
        } else {
            if (equals) {
                return;
            }
            if (!taskInfo.isVisible) {
                this.mVisibleTasksWithLocusId.remove(taskInfo.taskId);
                notifyLocusIdChange(i, locusId, false);
            } else {
                this.mVisibleTasksWithLocusId.put(i, taskInfo.mTopActivityLocusId);
                notifyLocusIdChange(i, locusId, false);
                notifyLocusIdChange(i, taskInfo.mTopActivityLocusId, true);
            }
        }
    }

    public final void onAppSplashScreenViewRemoved(int i) {
        StartingWindowController startingWindowController = this.mStartingWindow;
        if (startingWindowController != null) {
            ((HandlerExecutor) startingWindowController.mSplashScreenExecutor).execute(new StartingWindowController$$ExternalSyntheticLambda0(startingWindowController, i, 2));
        }
    }

    public final void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) {
        synchronized (this.mLock) {
            try {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, 6500052048103815784L, 1, Long.valueOf(runningTaskInfo.taskId));
                }
                TaskListener taskListener = getTaskListener(runningTaskInfo, false);
                if (taskListener != null) {
                    taskListener.onBackPressedOnTaskRoot(runningTaskInfo);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void onImeDrawnOnTask(int i) {
        StartingWindowController startingWindowController = this.mStartingWindow;
        if (startingWindowController != null) {
            ((HandlerExecutor) startingWindowController.mSplashScreenExecutor).execute(new StartingWindowController$$ExternalSyntheticLambda0(startingWindowController, i, 1));
        }
    }

    public void onSizeCompatRestartButtonAppeared(CompatUIEvents.SizeCompatRestartButtonAppeared sizeCompatRestartButtonAppeared) {
        TaskAppearedInfo taskAppearedInfo;
        ActivityInfo activityInfo;
        int i = sizeCompatRestartButtonAppeared.taskId;
        synchronized (this.mLock) {
            taskAppearedInfo = (TaskAppearedInfo) this.mTasks.get(i);
        }
        if (taskAppearedInfo == null || (activityInfo = taskAppearedInfo.getTaskInfo().topActivityInfo) == null) {
            return;
        }
        FrameworkStatsLog.write(387, activityInfo.applicationInfo.uid, 1);
    }

    public void onSizeCompatRestartButtonClicked(CompatUIEvents.SizeCompatRestartButtonClicked sizeCompatRestartButtonClicked) {
        TaskAppearedInfo taskAppearedInfo;
        int i = sizeCompatRestartButtonClicked.taskId;
        synchronized (this.mLock) {
            taskAppearedInfo = (TaskAppearedInfo) this.mTasks.get(i);
        }
        if (taskAppearedInfo == null) {
            return;
        }
        ActivityInfo activityInfo = taskAppearedInfo.getTaskInfo().topActivityInfo;
        if (activityInfo != null) {
            FrameworkStatsLog.write(387, activityInfo.applicationInfo.uid, 2);
        }
        restartTaskTopActivityProcessIfVisible(taskAppearedInfo.getTaskInfo().token);
    }

    public final void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
        if (surfaceControl != null) {
            surfaceControl.setUnreleasedWarningCallSite("ShellTaskOrganizer.onTaskAppeared");
        }
        synchronized (this.mLock) {
            onTaskAppeared(new TaskAppearedInfo(runningTaskInfo, surfaceControl));
        }
    }

    public final void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        boolean z;
        synchronized (this.mLock) {
            try {
                boolean z2 = true;
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, -6559915013568054037L, 1, Long.valueOf(runningTaskInfo.taskId));
                }
                UnfoldAnimationController unfoldAnimationController = this.mUnfoldAnimationController;
                if (unfoldAnimationController != null) {
                    unfoldAnimationController.onTaskInfoChanged(runningTaskInfo);
                }
                TaskAppearedInfo taskAppearedInfo = (TaskAppearedInfo) this.mTasks.get(runningTaskInfo.taskId);
                TaskListener taskListener = getTaskListener(taskAppearedInfo.getTaskInfo(), false);
                TaskListener taskListener2 = getTaskListener(runningTaskInfo, false);
                this.mTasks.put(runningTaskInfo.taskId, new TaskAppearedInfo(runningTaskInfo, taskAppearedInfo.getLeash()));
                SurfaceControl leash = taskAppearedInfo.getLeash();
                if (taskListener == taskListener2) {
                    z = false;
                } else {
                    if (taskListener != null) {
                        taskListener.onTaskVanished(runningTaskInfo);
                    }
                    if (taskListener2 != null) {
                        taskListener2.onTaskAppeared(runningTaskInfo, leash);
                    }
                    z = true;
                }
                if (!z && taskListener2 != null) {
                    taskListener2.onTaskInfoChanged(runningTaskInfo);
                }
                notifyLocusVisibilityIfNeeded(runningTaskInfo);
                if (z || !runningTaskInfo.equalsForCompatUi(taskAppearedInfo.getTaskInfo())) {
                    notifyCompatUI(runningTaskInfo, taskListener2);
                }
                boolean z3 = taskAppearedInfo.getTaskInfo().getWindowingMode() != runningTaskInfo.getWindowingMode();
                boolean z4 = taskAppearedInfo.getTaskInfo().isVisible != runningTaskInfo.isVisible;
                if (z3 || (runningTaskInfo.getWindowingMode() == 5 && z4)) {
                    this.mRecentTasks.ifPresent(new ShellTaskOrganizer$$ExternalSyntheticLambda1(runningTaskInfo, 0));
                }
                if (!runningTaskInfo.isFocused && (runningTaskInfo.topActivityType != 2 || !runningTaskInfo.isVisible)) {
                    z2 = false;
                }
                ActivityManager.RunningTaskInfo runningTaskInfo2 = this.mLastFocusedTaskInfo;
                if ((runningTaskInfo2 == null || runningTaskInfo2.taskId != runningTaskInfo.taskId || runningTaskInfo2.getWindowingMode() != runningTaskInfo.getWindowingMode()) && z2) {
                    for (int i = 0; i < this.mFocusListeners.size(); i++) {
                        ((FocusListener) this.mFocusListeners.valueAt(i)).onFocusTaskChanged(runningTaskInfo);
                    }
                    this.mLastFocusedTaskInfo = runningTaskInfo;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        synchronized (this.mLock) {
            try {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, 8756077958776566595L, 1, Long.valueOf(runningTaskInfo.taskId));
                }
                UnfoldAnimationController unfoldAnimationController = this.mUnfoldAnimationController;
                if (unfoldAnimationController != null) {
                    unfoldAnimationController.mTaskSurfaces.remove(runningTaskInfo.taskId);
                    UnfoldTaskAnimator unfoldTaskAnimator = (UnfoldTaskAnimator) unfoldAnimationController.mAnimatorsByTaskId.get(runningTaskInfo.taskId);
                    if (unfoldTaskAnimator != null) {
                        if (unfoldAnimationController.mIsInStageChange) {
                            TransactionPool transactionPool = unfoldAnimationController.mTransactionPool;
                            SurfaceControl.Transaction acquire = transactionPool.acquire();
                            unfoldTaskAnimator.resetSurface(runningTaskInfo, acquire);
                            acquire.apply();
                            transactionPool.release(acquire);
                        }
                        unfoldTaskAnimator.onTaskVanished(runningTaskInfo);
                        unfoldAnimationController.mAnimatorsByTaskId.remove(runningTaskInfo.taskId);
                    }
                }
                int i = runningTaskInfo.taskId;
                TaskAppearedInfo taskAppearedInfo = (TaskAppearedInfo) this.mTasks.get(i);
                TaskListener taskListener = getTaskListener(taskAppearedInfo.getTaskInfo(), false);
                this.mTasks.remove(i);
                if (taskListener != null) {
                    taskListener.onTaskVanished(runningTaskInfo);
                }
                notifyLocusVisibilityIfNeeded(runningTaskInfo);
                notifyCompatUI(runningTaskInfo, null);
                this.mRecentTasks.ifPresent(new ShellTaskOrganizer$$ExternalSyntheticLambda1(runningTaskInfo, 1));
                if (runningTaskInfo.getActivityType() == 2) {
                    SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
                    transaction.reparent(this.mHomeTaskOverlayContainer, null);
                    transaction.apply();
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, 2257855729973411439L, 0, null);
                    }
                }
                Iterator it = this.mTaskVanishedListeners.iterator();
                while (it.hasNext()) {
                    ((TaskVanishedListener) it.next()).onTaskVanished(runningTaskInfo);
                }
                if (!Transitions.ENABLE_SHELL_TRANSITIONS && taskAppearedInfo.getLeash() != null) {
                    taskAppearedInfo.getLeash().release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final List registerOrganizer() {
        List registerOrganizer;
        synchronized (this.mLock) {
            try {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, 471160942078541602L, 0, null);
                }
                registerOrganizer = super.registerOrganizer();
                for (int i = 0; i < registerOrganizer.size(); i++) {
                    TaskAppearedInfo taskAppearedInfo = (TaskAppearedInfo) registerOrganizer.get(i);
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, -3643791260082359448L, 1, Long.valueOf(taskAppearedInfo.getTaskInfo().taskId), String.valueOf(taskAppearedInfo.getTaskInfo().baseIntent));
                    }
                    onTaskAppeared(taskAppearedInfo);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return registerOrganizer;
    }

    public final void removeStartingWindow(final StartingWindowRemovalInfo startingWindowRemovalInfo) {
        final StartingWindowController startingWindowController = this.mStartingWindow;
        if (startingWindowController != null) {
            final int i = 0;
            Runnable runnable = new Runnable() { // from class: com.android.wm.shell.startingsurface.StartingWindowController$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i) {
                        case 0:
                            StartingWindowController startingWindowController2 = startingWindowController;
                            StartingWindowRemovalInfo startingWindowRemovalInfo2 = startingWindowRemovalInfo;
                            StartingSurfaceDrawer startingSurfaceDrawer = startingWindowController2.mStartingSurfaceDrawer;
                            startingSurfaceDrawer.getClass();
                            if (startingWindowRemovalInfo2.windowlessSurface) {
                                startingSurfaceDrawer.mWindowlessRecords.removeWindow(startingWindowRemovalInfo2, startingWindowRemovalInfo2.removeImmediately);
                                return;
                            }
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
                                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, 4806354277159419601L, 1, Long.valueOf(startingWindowRemovalInfo2.taskId));
                            }
                            startingSurfaceDrawer.mWindowRecords.removeWindow(startingWindowRemovalInfo2, startingWindowRemovalInfo2.removeImmediately);
                            return;
                        default:
                            StartingWindowController startingWindowController3 = startingWindowController;
                            StartingWindowRemovalInfo startingWindowRemovalInfo3 = startingWindowRemovalInfo;
                            synchronized (startingWindowController3.mTaskBackgroundColors) {
                                startingWindowController3.mTaskBackgroundColors.delete(startingWindowRemovalInfo3.taskId);
                            }
                            return;
                    }
                }
            };
            HandlerExecutor handlerExecutor = (HandlerExecutor) startingWindowController.mSplashScreenExecutor;
            handlerExecutor.execute(runnable);
            if (startingWindowRemovalInfo.windowlessSurface) {
                return;
            }
            final int i2 = 1;
            handlerExecutor.executeDelayed(new Runnable() { // from class: com.android.wm.shell.startingsurface.StartingWindowController$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i2) {
                        case 0:
                            StartingWindowController startingWindowController2 = startingWindowController;
                            StartingWindowRemovalInfo startingWindowRemovalInfo2 = startingWindowRemovalInfo;
                            StartingSurfaceDrawer startingSurfaceDrawer = startingWindowController2.mStartingSurfaceDrawer;
                            startingSurfaceDrawer.getClass();
                            if (startingWindowRemovalInfo2.windowlessSurface) {
                                startingSurfaceDrawer.mWindowlessRecords.removeWindow(startingWindowRemovalInfo2, startingWindowRemovalInfo2.removeImmediately);
                                return;
                            }
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_STARTING_WINDOW_enabled[1]) {
                                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_STARTING_WINDOW, 4806354277159419601L, 1, Long.valueOf(startingWindowRemovalInfo2.taskId));
                            }
                            startingSurfaceDrawer.mWindowRecords.removeWindow(startingWindowRemovalInfo2, startingWindowRemovalInfo2.removeImmediately);
                            return;
                        default:
                            StartingWindowController startingWindowController3 = startingWindowController;
                            StartingWindowRemovalInfo startingWindowRemovalInfo3 = startingWindowRemovalInfo;
                            synchronized (startingWindowController3.mTaskBackgroundColors) {
                                startingWindowController3.mTaskBackgroundColors.delete(startingWindowRemovalInfo3.taskId);
                            }
                            return;
                    }
                }
            }, 5000L);
        }
    }

    public final void reparentChildSurfaceToTask(int i, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
        TaskListener taskListener;
        synchronized (this.mLock) {
            try {
                taskListener = this.mTasks.contains(i) ? getTaskListener(((TaskAppearedInfo) this.mTasks.get(i)).getTaskInfo(), false) : null;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (taskListener != null) {
            taskListener.reparentChildSurfaceToTask(i, transaction, surfaceControl);
        } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[3]) {
            ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_TASK_ORG, -2252708287258544819L, 1, Long.valueOf(i));
        }
    }

    public final void setTaskSurfaceVisibility(int i, boolean z) {
        synchronized (this.mLock) {
            try {
                TaskAppearedInfo taskAppearedInfo = (TaskAppearedInfo) this.mTasks.get(i);
                if (taskAppearedInfo != null) {
                    SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
                    transaction.setVisibility(taskAppearedInfo.getLeash(), z);
                    transaction.apply();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void unregisterOrganizer() {
        super.unregisterOrganizer();
        StartingWindowController startingWindowController = this.mStartingWindow;
        if (startingWindowController != null) {
            ((HandlerExecutor) startingWindowController.mSplashScreenExecutor).execute(new StartingWindowController$$ExternalSyntheticLambda4(startingWindowController, 0));
        }
    }

    public final void onTaskAppeared(TaskAppearedInfo taskAppearedInfo) {
        int i = taskAppearedInfo.getTaskInfo().taskId;
        this.mTasks.put(i, taskAppearedInfo);
        TaskListener taskListener = getTaskListener(taskAppearedInfo.getTaskInfo(), true);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, -3692726879013162739L, 1, Long.valueOf(i), String.valueOf(taskListener));
        }
        if (taskListener != null) {
            taskListener.onTaskAppeared(taskAppearedInfo.getTaskInfo(), taskAppearedInfo.getLeash());
        }
        UnfoldAnimationController unfoldAnimationController = this.mUnfoldAnimationController;
        if (unfoldAnimationController != null) {
            ActivityManager.RunningTaskInfo taskInfo = taskAppearedInfo.getTaskInfo();
            SurfaceControl leash = taskAppearedInfo.getLeash();
            unfoldAnimationController.mTaskSurfaces.put(taskInfo.taskId, leash);
            int i2 = 0;
            while (true) {
                if (i2 >= ((ArrayList) unfoldAnimationController.mAnimators).size()) {
                    break;
                }
                UnfoldTaskAnimator unfoldTaskAnimator = (UnfoldTaskAnimator) ((ArrayList) unfoldAnimationController.mAnimators).get(i2);
                if (unfoldTaskAnimator.isApplicableTask(taskInfo)) {
                    unfoldAnimationController.mAnimatorsByTaskId.put(taskInfo.taskId, unfoldTaskAnimator);
                    unfoldTaskAnimator.onTaskAppeared(taskInfo, leash);
                    break;
                }
                i2++;
            }
        }
        if (taskAppearedInfo.getTaskInfo().getActivityType() == 2) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TASK_ORG_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TASK_ORG, 4851942932365456466L, 0, null);
            }
            SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
            transaction.setLayer(this.mHomeTaskOverlayContainer, Integer.MAX_VALUE);
            transaction.reparent(this.mHomeTaskOverlayContainer, taskAppearedInfo.getLeash());
            transaction.apply();
        }
        notifyLocusVisibilityIfNeeded(taskAppearedInfo.getTaskInfo());
        notifyCompatUI(taskAppearedInfo.getTaskInfo(), taskListener);
        this.mRecentTasks.ifPresent(new ShellTaskOrganizer$$ExternalSyntheticLambda0(0, taskAppearedInfo));
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TaskListener {
        default void attachChildSurfaceToTask(int i, SurfaceControl.Builder builder) {
            throw new IllegalStateException("This task listener doesn't support child surface attachment.");
        }

        default void reparentChildSurfaceToTask(int i, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
            throw new IllegalStateException("This task listener doesn't support child surface reparent.");
        }

        default boolean supportCompatUI() {
            return true;
        }

        default void onBackPressedOnTaskRoot(ActivityManager.RunningTaskInfo runningTaskInfo) {
        }

        default void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        }

        default void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        }

        default void dump$1(PrintWriter printWriter, String str) {
        }

        default void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TaskVanishedListener {
        default void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        }
    }
}

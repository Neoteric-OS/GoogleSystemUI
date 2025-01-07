package com.android.wm.shell.splitscreen;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.PendingIntent;
import android.app.TaskInfo;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LauncherApps;
import android.content.pm.ShortcutInfo;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Rect;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Slog;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.IRemoteAnimationRunner;
import android.view.RemoteAnimationAdapter;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.widget.Toast;
import android.window.RemoteTransition;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import com.android.app.viewcapture.data.ViewNode;
import com.android.internal.logging.InstanceId;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.launcher3.icons.IconProvider;
import com.android.systemui.wmshell.WMShell;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayImeController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.ExternalInterfaceBinder;
import com.android.wm.shell.common.LaunchAdjacentController;
import com.android.wm.shell.common.MultiInstanceHelper;
import com.android.wm.shell.common.RemoteCallable;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.common.split.SplitLayout;
import com.android.wm.shell.common.split.SplitScreenUtils;
import com.android.wm.shell.desktopmode.DesktopTasksController;
import com.android.wm.shell.draganddrop.DragAndDropController;
import com.android.wm.shell.draganddrop.SplitDragPolicy;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.splitscreen.SplitScreen;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.splitscreen.StageCoordinator;
import com.android.wm.shell.splitscreen.StageCoordinator.AnonymousClass2;
import com.android.wm.shell.sysui.KeyguardChangeListener;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.WindowDecorViewModel;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplitScreenController implements SplitDragPolicy.Starter, RemoteCallable, KeyguardChangeListener {
    public final Context mContext;
    public final Optional mDesktopTasksController;
    public final DisplayController mDisplayController;
    public final DisplayImeController mDisplayImeController;
    public final DisplayInsetsController mDisplayInsetsController;
    public final DragAndDropController mDragAndDropController;
    public final IconProvider mIconProvider;
    public final LaunchAdjacentController mLaunchAdjacentController;
    public final LauncherApps mLauncherApps;
    public final ShellExecutor mMainExecutor;
    public final Handler mMainHandler;
    public final MultiInstanceHelper mMultiInstanceHelpher;
    public final Optional mRecentTasksOptional;
    public final ShellCommandHandler mShellCommandHandler;
    public final ShellController mShellController;
    public final SyncTransactionQueue mSyncQueue;
    public final ShellTaskOrganizer mTaskOrganizer;
    public final TransactionPool mTransactionPool;
    public final Transitions mTransitions;
    public final Optional mWindowDecorViewModel;
    public final SplitScreenImpl mImpl = new SplitScreenImpl();
    StageCoordinator mStageCoordinator = null;
    public final SplitScreenShellCommandHandler mSplitScreenShellCommandHandler = new SplitScreenShellCommandHandler(this);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SplitScreenImpl implements SplitScreen {
        public final ArrayMap mExecutors = new ArrayMap();
        public final AnonymousClass1 mListener = new AnonymousClass1();

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.wm.shell.splitscreen.SplitScreenController$SplitScreenImpl$1, reason: invalid class name */
        public final class AnonymousClass1 implements SplitScreen.SplitScreenListener {
            public AnonymousClass1() {
            }

            @Override // com.android.wm.shell.splitscreen.SplitScreen.SplitScreenListener
            public final void onSplitBoundsChanged(final Rect rect, final Rect rect2, final Rect rect3) {
                int i = 0;
                while (true) {
                    SplitScreenImpl splitScreenImpl = SplitScreenImpl.this;
                    if (i >= splitScreenImpl.mExecutors.size()) {
                        return;
                    }
                    final int i2 = i;
                    ((Executor) splitScreenImpl.mExecutors.valueAt(i)).execute(new Runnable() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$SplitScreenImpl$1$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            SplitScreenController.SplitScreenImpl.AnonymousClass1 anonymousClass1 = SplitScreenController.SplitScreenImpl.AnonymousClass1.this;
                            int i3 = i2;
                            ((SplitScreen.SplitScreenListener) SplitScreenController.SplitScreenImpl.this.mExecutors.keyAt(i3)).onSplitBoundsChanged(rect, rect2, rect3);
                        }
                    });
                    i++;
                }
            }

            @Override // com.android.wm.shell.splitscreen.SplitScreen.SplitScreenListener
            public final void onSplitVisibilityChanged(final boolean z) {
                final int i = 0;
                while (true) {
                    SplitScreenImpl splitScreenImpl = SplitScreenImpl.this;
                    if (i >= splitScreenImpl.mExecutors.size()) {
                        return;
                    }
                    ((Executor) splitScreenImpl.mExecutors.valueAt(i)).execute(new Runnable() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$SplitScreenImpl$1$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            SplitScreenController.SplitScreenImpl.AnonymousClass1 anonymousClass1 = SplitScreenController.SplitScreenImpl.AnonymousClass1.this;
                            int i2 = i;
                            ((SplitScreen.SplitScreenListener) SplitScreenController.SplitScreenImpl.this.mExecutors.keyAt(i2)).onSplitVisibilityChanged(z);
                        }
                    });
                    i++;
                }
            }

            @Override // com.android.wm.shell.splitscreen.SplitScreen.SplitScreenListener
            public final void onStagePositionChanged(final int i, final int i2) {
                final int i3 = 0;
                while (true) {
                    SplitScreenImpl splitScreenImpl = SplitScreenImpl.this;
                    if (i3 >= splitScreenImpl.mExecutors.size()) {
                        return;
                    }
                    ((Executor) splitScreenImpl.mExecutors.valueAt(i3)).execute(new Runnable() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$SplitScreenImpl$1$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            SplitScreenController.SplitScreenImpl.AnonymousClass1 anonymousClass1 = SplitScreenController.SplitScreenImpl.AnonymousClass1.this;
                            int i4 = i3;
                            ((SplitScreen.SplitScreenListener) SplitScreenController.SplitScreenImpl.this.mExecutors.keyAt(i4)).onStagePositionChanged(i, i2);
                        }
                    });
                    i3++;
                }
            }

            @Override // com.android.wm.shell.splitscreen.SplitScreen.SplitScreenListener
            public final void onTaskStageChanged(final int i, final int i2, final boolean z) {
                int i3 = 0;
                while (true) {
                    SplitScreenImpl splitScreenImpl = SplitScreenImpl.this;
                    if (i3 >= splitScreenImpl.mExecutors.size()) {
                        return;
                    }
                    final int i4 = i3;
                    ((Executor) splitScreenImpl.mExecutors.valueAt(i3)).execute(new Runnable() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$SplitScreenImpl$1$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            SplitScreenController.SplitScreenImpl.AnonymousClass1 anonymousClass1 = SplitScreenController.SplitScreenImpl.AnonymousClass1.this;
                            int i5 = i4;
                            ((SplitScreen.SplitScreenListener) SplitScreenController.SplitScreenImpl.this.mExecutors.keyAt(i5)).onTaskStageChanged(i, i2, z);
                        }
                    });
                    i3++;
                }
            }
        }

        public SplitScreenImpl() {
        }

        public final void registerSplitAnimationListener(WMShell.AnonymousClass9 anonymousClass9, Executor executor) {
            StageCoordinator stageCoordinator = SplitScreenController.this.mStageCoordinator;
            stageCoordinator.mSplitInvocationListener = anonymousClass9;
            stageCoordinator.mSplitInvocationListenerExecutor = executor;
            SplitScreenTransitions splitScreenTransitions = stageCoordinator.mSplitTransitions;
            splitScreenTransitions.mSplitInvocationListener = anonymousClass9;
            splitScreenTransitions.mSplitInvocationListenerExecutor = executor;
        }
    }

    public SplitScreenController(Context context, ShellInit shellInit, ShellCommandHandler shellCommandHandler, ShellController shellController, ShellTaskOrganizer shellTaskOrganizer, SyncTransactionQueue syncTransactionQueue, DisplayController displayController, DisplayImeController displayImeController, DisplayInsetsController displayInsetsController, DragAndDropController dragAndDropController, Transitions transitions, TransactionPool transactionPool, IconProvider iconProvider, Optional optional, LaunchAdjacentController launchAdjacentController, Optional optional2, Optional optional3, MultiInstanceHelper multiInstanceHelper, ShellExecutor shellExecutor, Handler handler) {
        this.mShellCommandHandler = shellCommandHandler;
        this.mShellController = shellController;
        this.mTaskOrganizer = shellTaskOrganizer;
        this.mSyncQueue = syncTransactionQueue;
        this.mContext = context;
        this.mLauncherApps = (LauncherApps) context.getSystemService(LauncherApps.class);
        this.mMainExecutor = shellExecutor;
        this.mMainHandler = handler;
        this.mDisplayController = displayController;
        this.mDisplayImeController = displayImeController;
        this.mDisplayInsetsController = displayInsetsController;
        this.mDragAndDropController = dragAndDropController;
        this.mTransitions = transitions;
        this.mTransactionPool = transactionPool;
        this.mIconProvider = iconProvider;
        this.mRecentTasksOptional = optional;
        this.mLaunchAdjacentController = launchAdjacentController;
        this.mWindowDecorViewModel = optional2;
        this.mDesktopTasksController = optional3;
        this.mMultiInstanceHelpher = multiInstanceHelper;
        if (ActivityTaskManager.supportsSplitScreenMultiWindow(context)) {
            shellInit.addInitCallback(new SplitScreenController$$ExternalSyntheticLambda0(this, 0), this);
        }
    }

    public static String exitReasonToString(int i) {
        switch (i) {
            case 0:
                return "UNKNOWN_EXIT";
            case 1:
                return "APP_DOES_NOT_SUPPORT_MULTIWINDOW";
            case 2:
                return "APP_FINISHED";
            case 3:
                return "DEVICE_FOLDED";
            case 4:
                return "DRAG_DIVIDER";
            case 5:
                return "RETURN_HOME";
            case 6:
                return "ROOT_TASK_VANISHED";
            case 7:
                return "SCREEN_LOCKED";
            case 8:
                return "SCREEN_LOCKED_SHOW_ON_TOP";
            case 9:
                return "CHILD_TASK_ENTER_PIP";
            case 10:
                return "RECREATE_SPLIT";
            case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
            default:
                return AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "unknown reason, reason int = ");
            case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                return "DESKTOP_MODE";
            case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                return "FULLSCREEN_REQUEST";
        }
    }

    public final int determineNewInstancePosition(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (runningTaskInfo.getWindowingMode() != 1) {
            if (this.mStageCoordinator.getSplitPosition(runningTaskInfo.taskId) != 0) {
                return 0;
            }
        }
        return 1;
    }

    public final void exitSplitScreen(int i, int i2) {
        StageCoordinator stageCoordinator = this.mStageCoordinator;
        if (stageCoordinator.mMainStage.mIsActive) {
            int stageOfTask = stageCoordinator.getStageOfTask(i);
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            stageCoordinator.prepareExitSplitScreen(stageOfTask, windowContainerTransaction);
            stageCoordinator.mSplitTransitions.startDismissTransition(windowContainerTransaction, stageCoordinator, stageOfTask, i2);
            stageCoordinator.logExit(i2);
        }
    }

    public final void finishEnterSplitScreen(SurfaceControl.Transaction transaction) {
        this.mStageCoordinator.finishEnterSplitScreen(transaction);
    }

    public final int getActivateSplitPosition(TaskInfo taskInfo) {
        return this.mStageCoordinator.getActivateSplitPosition(taskInfo);
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final Context getContext() {
        return this.mContext;
    }

    public final String getPackageName(int i, WindowContainerToken windowContainerToken) {
        ActivityManager.RunningTaskInfo runningTaskInfo;
        if (this.mStageCoordinator.isSplitScreenVisible()) {
            runningTaskInfo = getTaskInfo(i);
        } else {
            runningTaskInfo = (ActivityManager.RunningTaskInfo) this.mRecentTasksOptional.map(new SplitScreenController$$ExternalSyntheticLambda5(0, windowContainerToken)).orElse(null);
            if (!SplitScreenUtils.isValidToSplit(runningTaskInfo)) {
                return null;
            }
        }
        if (runningTaskInfo != null) {
            return SplitScreenUtils.getPackageName(runningTaskInfo.baseIntent);
        }
        return null;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final ShellExecutor getRemoteCallExecutor() {
        return this.mMainExecutor;
    }

    public final int getSplitPosition(int i) {
        return this.mStageCoordinator.getSplitPosition(i);
    }

    public final void getStageBounds(Rect rect, Rect rect2) {
        StageCoordinator stageCoordinator = this.mStageCoordinator;
        rect.set(stageCoordinator.mSplitLayout.getBounds1());
        rect2.set(stageCoordinator.mSplitLayout.getBounds2());
    }

    public final int getStageOfTask(int i) {
        return this.mStageCoordinator.getStageOfTask(i);
    }

    public final ActivityManager.RunningTaskInfo getTaskInfo(int i) {
        if (!this.mStageCoordinator.isSplitScreenVisible()) {
            return null;
        }
        int i2 = -1;
        if (i == -1) {
            return null;
        }
        StageCoordinator stageCoordinator = this.mStageCoordinator;
        if (i == -1) {
            stageCoordinator.getClass();
        } else {
            i2 = stageCoordinator.mSideStagePosition == i ? stageCoordinator.mSideStage.getTopVisibleChildTaskId() : stageCoordinator.mMainStage.getTopVisibleChildTaskId();
        }
        return this.mTaskOrganizer.getRunningTaskInfo(i2);
    }

    public final StageCoordinator getTransitionHandler() {
        return this.mStageCoordinator;
    }

    public final int getUserId(int i, WindowContainerToken windowContainerToken) {
        ActivityManager.RunningTaskInfo runningTaskInfo;
        if (this.mStageCoordinator.isSplitScreenVisible()) {
            runningTaskInfo = getTaskInfo(i);
        } else {
            runningTaskInfo = (ActivityManager.RunningTaskInfo) this.mRecentTasksOptional.map(new SplitScreenController$$ExternalSyntheticLambda5(1, windowContainerToken)).orElse(null);
            if (!SplitScreenUtils.isValidToSplit(runningTaskInfo)) {
                return -1;
            }
        }
        if (runningTaskInfo != null) {
            return runningTaskInfo.userId;
        }
        return -1;
    }

    public final boolean isLaunchToSplit(TaskInfo taskInfo) {
        return this.mStageCoordinator.getActivateSplitPosition(taskInfo) != -1;
    }

    public final boolean isLeftRightSplit() {
        SplitLayout splitLayout = this.mStageCoordinator.mSplitLayout;
        return splitLayout != null && splitLayout.mIsLeftRightSplit;
    }

    public final boolean isSplitScreenVisible() {
        return this.mStageCoordinator.isSplitScreenVisible();
    }

    public final boolean isTaskInSplitScreen(int i) {
        return this.mStageCoordinator.getStageOfTask(i) != -1;
    }

    public final boolean isTaskRootOrStageRoot(int i) {
        StageCoordinator stageCoordinator = this.mStageCoordinator;
        ActivityManager.RunningTaskInfo runningTaskInfo = stageCoordinator.mRootTaskInfo;
        if (runningTaskInfo != null && runningTaskInfo.taskId == i) {
            return true;
        }
        ActivityManager.RunningTaskInfo runningTaskInfo2 = stageCoordinator.mMainStage.mRootTaskInfo;
        if (runningTaskInfo2 != null && runningTaskInfo2.taskId == i) {
            return true;
        }
        ActivityManager.RunningTaskInfo runningTaskInfo3 = stageCoordinator.mSideStage.mRootTaskInfo;
        return runningTaskInfo3 != null && runningTaskInfo3.taskId == i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0040, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0034, code lost:
    
        if (r5.mSideStagePosition == 1) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0024, code lost:
    
        if (r5.mSideStagePosition == 0) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0026, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0037, code lost:
    
        r5.mSplitLayout.flingDividerToDismiss(12, !r1);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void moveTaskToFullscreen(int r6) {
        /*
            r5 = this;
            com.android.wm.shell.splitscreen.StageCoordinator r5 = r5.mStageCoordinator
            r5.getClass()
            boolean[] r0 = com.android.internal.protolog.ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled
            r1 = 0
            boolean r0 = r0[r1]
            if (r0 == 0) goto L17
            com.android.wm.shell.protolog.ShellProtoLogGroup r0 = com.android.wm.shell.protolog.ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN
            r2 = -4495304678011028405(0xc19d783666003c4b, double:-1.2360437750023E8)
            r4 = 0
            com.android.internal.protolog.ProtoLogImpl_411527699.d(r0, r2, r1, r4)
        L17:
            com.android.wm.shell.splitscreen.StageTaskListener r0 = r5.mMainStage
            android.util.SparseArray r0 = r0.mChildrenTaskInfo
            boolean r0 = r0.contains(r6)
            r2 = 1
            if (r0 == 0) goto L28
            int r6 = r5.mSideStagePosition
            if (r6 != 0) goto L37
        L26:
            r1 = r2
            goto L37
        L28:
            com.android.wm.shell.splitscreen.StageTaskListener r0 = r5.mSideStage
            android.util.SparseArray r0 = r0.mChildrenTaskInfo
            boolean r6 = r0.contains(r6)
            if (r6 == 0) goto L40
            int r6 = r5.mSideStagePosition
            if (r6 != r2) goto L37
            goto L26
        L37:
            com.android.wm.shell.common.split.SplitLayout r5 = r5.mSplitLayout
            r6 = r1 ^ 1
            r0 = 12
            r5.flingDividerToDismiss(r0, r6)
        L40:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.splitscreen.SplitScreenController.moveTaskToFullscreen(int):void");
    }

    public final void moveToStage(int i, int i2, WindowContainerTransaction windowContainerTransaction) {
        ActivityManager.RunningTaskInfo runningTaskInfo = this.mTaskOrganizer.getRunningTaskInfo(i);
        if (runningTaskInfo == null) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unknown taskId"));
        }
        if (isTaskInSplitScreen(i)) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "taskId is in split"));
        }
        StageCoordinator stageCoordinator = this.mStageCoordinator;
        stageCoordinator.getClass();
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 1563569262687696354L, 5, Long.valueOf(runningTaskInfo.taskId), Long.valueOf(i2));
        }
        stageCoordinator.prepareEnterSplitScreen(windowContainerTransaction, runningTaskInfo, i2, false);
        stageCoordinator.mSplitTransitions.startEnterTransition(windowContainerTransaction, null, stageCoordinator, stageCoordinator.isSplitScreenVisible() ? 1005 : 1004, !stageCoordinator.mIsDropEntering);
        stageCoordinator.mIsDropEntering = false;
        stageCoordinator.mSkipEvictingMainStageChildren = false;
    }

    public final void onDroppedToSplit(int i, InstanceId instanceId) {
        StageCoordinator stageCoordinator = this.mStageCoordinator;
        stageCoordinator.getClass();
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 3250015241683482964L, 1, Long.valueOf(i));
        }
        if (!stageCoordinator.isSplitScreenVisible()) {
            stageCoordinator.mIsDropEntering = true;
            stageCoordinator.mSkipEvictingMainStageChildren = true;
        }
        SplitscreenEventLogger splitscreenEventLogger = stageCoordinator.mLogger;
        splitscreenEventLogger.mDragEnterPosition = i;
        splitscreenEventLogger.mEnterSessionId = instanceId;
        splitscreenEventLogger.mEnterReason = 2;
    }

    public void onInit() {
        BiConsumer biConsumer = new BiConsumer() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                SplitScreenController splitScreenController = SplitScreenController.this;
                PrintWriter printWriter = (PrintWriter) obj;
                String str = (String) obj2;
                splitScreenController.getClass();
                printWriter.println(str + "SplitScreenController");
                StageCoordinator stageCoordinator = splitScreenController.mStageCoordinator;
                if (stageCoordinator != null) {
                    stageCoordinator.dump$1(printWriter, str);
                }
            }
        };
        ShellCommandHandler shellCommandHandler = this.mShellCommandHandler;
        shellCommandHandler.addDumpCallback(biConsumer, this);
        shellCommandHandler.addCommandCallback("splitscreen", this.mSplitScreenShellCommandHandler, this);
        ShellController shellController = this.mShellController;
        shellController.addKeyguardChangeListener(this);
        shellController.addExternalInterface("extra_shell_split_screen", new Supplier() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$$ExternalSyntheticLambda2
            @Override // java.util.function.Supplier
            public final Object get() {
                return new SplitScreenController.ISplitScreenImpl(SplitScreenController.this);
            }
        }, this);
        if (this.mStageCoordinator == null) {
            Context context = this.mContext;
            Optional optional = this.mRecentTasksOptional;
            Optional optional2 = this.mWindowDecorViewModel;
            this.mStageCoordinator = new StageCoordinator(context, this.mSyncQueue, this.mTaskOrganizer, this.mDisplayController, this.mDisplayImeController, this.mDisplayInsetsController, this.mTransitions, this.mTransactionPool, this.mIconProvider, this.mMainExecutor, this.mMainHandler, optional, this.mLaunchAdjacentController, optional2);
        }
        DragAndDropController dragAndDropController = this.mDragAndDropController;
        if (dragAndDropController != null) {
            dragAndDropController.mSplitScreen = this;
        }
        final int i = 0;
        this.mWindowDecorViewModel.ifPresent(new Consumer(this) { // from class: com.android.wm.shell.splitscreen.SplitScreenController$$ExternalSyntheticLambda3
            public final /* synthetic */ SplitScreenController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i2 = i;
                SplitScreenController splitScreenController = this.f$0;
                switch (i2) {
                    case 0:
                        ((WindowDecorViewModel) obj).setSplitScreenController(splitScreenController);
                        break;
                    default:
                        DesktopTasksController desktopTasksController = (DesktopTasksController) obj;
                        splitScreenController.getClass();
                        desktopTasksController.splitScreenController = splitScreenController;
                        desktopTasksController.dragToDesktopTransitionHandler.splitScreenController = splitScreenController;
                        break;
                }
            }
        });
        final int i2 = 1;
        this.mDesktopTasksController.ifPresent(new Consumer(this) { // from class: com.android.wm.shell.splitscreen.SplitScreenController$$ExternalSyntheticLambda3
            public final /* synthetic */ SplitScreenController f$0;

            {
                this.f$0 = this;
            }

            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                int i22 = i2;
                SplitScreenController splitScreenController = this.f$0;
                switch (i22) {
                    case 0:
                        ((WindowDecorViewModel) obj).setSplitScreenController(splitScreenController);
                        break;
                    default:
                        DesktopTasksController desktopTasksController = (DesktopTasksController) obj;
                        splitScreenController.getClass();
                        desktopTasksController.splitScreenController = splitScreenController;
                        desktopTasksController.dragToDesktopTransitionHandler.splitScreenController = splitScreenController;
                        break;
                }
            }
        });
    }

    @Override // com.android.wm.shell.sysui.KeyguardChangeListener
    public final void onKeyguardVisibilityChanged(boolean z, boolean z2, boolean z3) {
        StageCoordinator stageCoordinator = this.mStageCoordinator;
        stageCoordinator.mKeyguardActive = z;
        if (stageCoordinator.mMainStage.mIsActive) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -468740076483298004L, 15, Boolean.valueOf(z), Boolean.valueOf(z2));
            }
            stageCoordinator.setDividerVisibility(null, !stageCoordinator.mKeyguardActive);
            if (z && z2) {
                stageCoordinator.dismissSplitKeepingLastActiveStage(8);
            }
        }
    }

    public final void onPipExpandToSplit(WindowContainerTransaction windowContainerTransaction, ActivityManager.RunningTaskInfo runningTaskInfo) {
        StageCoordinator stageCoordinator = this.mStageCoordinator;
        stageCoordinator.getClass();
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 241889366381180208L, 0, String.valueOf(runningTaskInfo));
        }
        stageCoordinator.prepareEnterSplitScreen(windowContainerTransaction, runningTaskInfo, stageCoordinator.getActivateSplitPosition(runningTaskInfo), false);
        if (!stageCoordinator.isSplitScreenVisible() || stageCoordinator.mSplitRequest == null) {
            return;
        }
        (SplitScreenUtils.reverseSplitPosition(stageCoordinator.mSideStagePosition) == stageCoordinator.mSplitRequest.mActivatePosition ? stageCoordinator.mMainStage : stageCoordinator.mSideStage).evictOtherChildren(runningTaskInfo.taskId, windowContainerTransaction);
    }

    public final void prepareExitSplitScreen(int i, int i2, WindowContainerTransaction windowContainerTransaction) {
        this.mStageCoordinator.prepareExitSplitScreen(i, windowContainerTransaction);
        this.mStageCoordinator.clearSplitPairedInRecents(i2);
    }

    public final void registerSplitScreenListener(SplitScreen.SplitScreenListener splitScreenListener) {
        StageCoordinator stageCoordinator = this.mStageCoordinator;
        if (stageCoordinator.mListeners.contains(splitScreenListener)) {
            return;
        }
        stageCoordinator.mListeners.add(splitScreenListener);
        stageCoordinator.sendStatusToListener(splitScreenListener);
    }

    public final boolean removeFromSideStage(int i) {
        StageCoordinator stageCoordinator = this.mStageCoordinator;
        stageCoordinator.getClass();
        boolean z = false;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 8700684296505867281L, 1, Long.valueOf(i));
        }
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        StageTaskListener stageTaskListener = stageCoordinator.mMainStage;
        WindowContainerToken windowContainerToken = stageTaskListener.mIsActive ? stageTaskListener.mRootTaskInfo.token : null;
        ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) stageCoordinator.mSideStage.mChildrenTaskInfo.get(i);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 3759293393874466908L, 13, Long.valueOf(i), Boolean.valueOf(runningTaskInfo != null));
        }
        if (runningTaskInfo != null) {
            windowContainerTransaction.reparent(runningTaskInfo.token, windowContainerToken, false);
            z = true;
        }
        stageCoordinator.mTaskOrganizer.applyTransaction(windowContainerTransaction);
        return z;
    }

    public final void requestEnterSplitSelect(int i, ActivityManager.RunningTaskInfo runningTaskInfo, Rect rect, WindowContainerTransaction windowContainerTransaction) {
        this.mStageCoordinator.requestEnterSplitSelect(i, runningTaskInfo, rect, windowContainerTransaction);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:35:? A[RETURN, SYNTHETIC] */
    @Override // com.android.wm.shell.draganddrop.SplitDragPolicy.Starter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void startIntent(android.app.PendingIntent r23, final int r24, android.content.Intent r25, int r26, android.os.Bundle r27, final android.window.WindowContainerToken r28) {
        /*
            Method dump skipped, instructions count: 596
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.splitscreen.SplitScreenController.startIntent(android.app.PendingIntent, int, android.content.Intent, int, android.os.Bundle, android.window.WindowContainerToken):void");
    }

    @Override // com.android.wm.shell.draganddrop.SplitDragPolicy.Starter
    public final void startShortcut(String str, String str2, int i, Bundle bundle, UserHandle userHandle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        ActivityOptions fromBundle = ActivityOptions.fromBundle(bundle);
        if (MultiInstanceHelper.samePackage(str, userHandle.getIdentifier(), getUserId(SplitScreenUtils.reverseSplitPosition(i), null), getPackageName(SplitScreenUtils.reverseSplitPosition(i), null))) {
            LauncherApps launcherApps = this.mLauncherApps;
            LauncherApps.ShortcutQuery shortcutQuery = new LauncherApps.ShortcutQuery();
            shortcutQuery.setPackage(str);
            shortcutQuery.setShortcutIds(Arrays.asList(str2));
            shortcutQuery.setQueryFlags(1051);
            List<ShortcutInfo> shortcuts = launcherApps.getShortcuts(shortcutQuery, userHandle);
            ShortcutInfo shortcutInfo = (shortcuts == null || shortcuts.size() <= 0) ? null : shortcuts.get(0);
            if (!this.mMultiInstanceHelpher.supportsMultiInstanceSplit(shortcutInfo != null ? shortcutInfo.getActivity() : null)) {
                if (this.mStageCoordinator.isSplitScreenVisible()) {
                    this.mStageCoordinator.switchSplitPosition("startShortcut");
                    return;
                }
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 2175723388761225132L, 0, null);
                }
                Log.w("SplitScreenController", SplitScreenUtils.splitFailureMessage("startShortcut", "app package " + str + " does not support multi-instance"));
                Toast.makeText(this.mContext, R.string.dock_multi_instances_not_supported_text, 0).show();
                return;
            }
            fromBundle.setApplyMultipleTaskFlagForShortcut(true);
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 2564351321352908886L, 0, null);
            }
        }
        StageCoordinator stageCoordinator = this.mStageCoordinator;
        Bundle bundle2 = fromBundle.toBundle();
        stageCoordinator.getClass();
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -5167067216468757684L, 80, String.valueOf(str), String.valueOf(str2), Long.valueOf(i), Long.valueOf(userHandle.getIdentifier()));
        }
        StageCoordinator.AnonymousClass2 anonymousClass2 = stageCoordinator.new AnonymousClass2(!stageCoordinator.mMainStage.mIsActive, i);
        Bundle resolveStartStage = stageCoordinator.resolveStartStage(-1, i, bundle2, null);
        RemoteAnimationAdapter remoteAnimationAdapter = new RemoteAnimationAdapter(anonymousClass2, 0L, 0L);
        ActivityOptions fromBundle2 = ActivityOptions.fromBundle(resolveStartStage);
        fromBundle2.setApplyNoUserActionFlagForShortcut(true);
        fromBundle2.update(ActivityOptions.makeRemoteAnimation(remoteAnimationAdapter));
        try {
            ((LauncherApps) stageCoordinator.mContext.getSystemService(LauncherApps.class)).startShortcut(str, str2, null, fromBundle2.toBundle(), userHandle);
        } catch (ActivityNotFoundException e) {
            Slog.e("StageCoordinator", "Failed to launch shortcut", e);
        }
    }

    @Override // com.android.wm.shell.draganddrop.SplitDragPolicy.Starter
    public final void startTask(int i, final int i2, Bundle bundle, WindowContainerToken windowContainerToken) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, 3393513473091973382L, 0, null);
        }
        final int[] iArr = new int[1];
        IRemoteAnimationRunner.Stub stub = new IRemoteAnimationRunner.Stub() { // from class: com.android.wm.shell.splitscreen.SplitScreenController.1
            public final void onAnimationCancelled() {
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                StageCoordinator stageCoordinator = SplitScreenController.this.mStageCoordinator;
                stageCoordinator.mMainStage.evictInvisibleChildren(windowContainerTransaction);
                stageCoordinator.mSideStage.evictInvisibleChildren(windowContainerTransaction);
                SplitScreenController.this.mSyncQueue.queue(windowContainerTransaction);
            }

            public final void onAnimationStart(int i3, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
                try {
                    iRemoteAnimationFinishedCallback.onAnimationFinished();
                } catch (RemoteException e) {
                    Slog.e("SplitScreenController", "Failed to invoke onAnimationFinished", e);
                }
                int i4 = iArr[0];
                if (i4 == 0 || i4 == 2) {
                    WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                    StageCoordinator stageCoordinator = SplitScreenController.this.mStageCoordinator;
                    if (i2 == stageCoordinator.mSideStagePosition) {
                        stageCoordinator.mSideStage.evictNonOpeningChildren(remoteAnimationTargetArr, windowContainerTransaction);
                    } else {
                        stageCoordinator.mMainStage.evictNonOpeningChildren(remoteAnimationTargetArr, windowContainerTransaction);
                    }
                    SplitScreenController.this.mSyncQueue.queue(windowContainerTransaction);
                }
            }
        };
        Bundle resolveStartStage = this.mStageCoordinator.resolveStartStage(-1, i2, bundle, null);
        RemoteAnimationAdapter remoteAnimationAdapter = new RemoteAnimationAdapter(stub, 0L, 0L);
        ActivityOptions fromBundle = ActivityOptions.fromBundle(resolveStartStage);
        fromBundle.update(ActivityOptions.makeRemoteAnimation(remoteAnimationAdapter));
        try {
            iArr[0] = ActivityTaskManager.getService().startActivityFromRecents(i, fromBundle.toBundle());
        } catch (RemoteException e) {
            Slog.e("SplitScreenController", "Failed to launch task", e);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ISplitScreenImpl extends Binder implements ExternalInterfaceBinder, IInterface {
        public SplitScreenController mController;
        public final SingleInstanceRemoteListener mListener;
        public final SingleInstanceRemoteListener mSelectListener;
        public final AnonymousClass1 mSplitScreenListener;
        public final AnonymousClass2 mSplitSelectListener;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$2, reason: invalid class name */
        public final class AnonymousClass2 {
            public AnonymousClass2() {
            }
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$1] */
        public ISplitScreenImpl(SplitScreenController splitScreenController) {
            attachInterface(this, "com.android.wm.shell.splitscreen.ISplitScreen");
            this.mSplitScreenListener = new SplitScreen.SplitScreenListener() { // from class: com.android.wm.shell.splitscreen.SplitScreenController.ISplitScreenImpl.1
                @Override // com.android.wm.shell.splitscreen.SplitScreen.SplitScreenListener
                public final void onStagePositionChanged(final int i, final int i2) {
                    ISplitScreenImpl.this.mListener.call(new SingleInstanceRemoteListener.RemoteCall() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$1$$ExternalSyntheticLambda1
                        @Override // com.android.wm.shell.common.SingleInstanceRemoteListener.RemoteCall
                        public final void accept(Object obj) {
                            int i3 = i;
                            int i4 = i2;
                            ISplitScreenListener$Stub$Proxy iSplitScreenListener$Stub$Proxy = (ISplitScreenListener$Stub$Proxy) obj;
                            Parcel obtain = Parcel.obtain(iSplitScreenListener$Stub$Proxy.mRemote);
                            try {
                                obtain.writeInterfaceToken("com.android.wm.shell.splitscreen.ISplitScreenListener");
                                obtain.writeInt(i3);
                                obtain.writeInt(i4);
                                iSplitScreenListener$Stub$Proxy.mRemote.transact(1, obtain, null, 1);
                            } finally {
                                obtain.recycle();
                            }
                        }
                    });
                }

                @Override // com.android.wm.shell.splitscreen.SplitScreen.SplitScreenListener
                public final void onTaskStageChanged(final int i, final int i2, final boolean z) {
                    ISplitScreenImpl.this.mListener.call(new SingleInstanceRemoteListener.RemoteCall() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$1$$ExternalSyntheticLambda0
                        @Override // com.android.wm.shell.common.SingleInstanceRemoteListener.RemoteCall
                        public final void accept(Object obj) {
                            int i3 = i;
                            int i4 = i2;
                            boolean z2 = z;
                            ISplitScreenListener$Stub$Proxy iSplitScreenListener$Stub$Proxy = (ISplitScreenListener$Stub$Proxy) obj;
                            Parcel obtain = Parcel.obtain(iSplitScreenListener$Stub$Proxy.mRemote);
                            try {
                                obtain.writeInterfaceToken("com.android.wm.shell.splitscreen.ISplitScreenListener");
                                obtain.writeInt(i3);
                                obtain.writeInt(i4);
                                obtain.writeBoolean(z2);
                                iSplitScreenListener$Stub$Proxy.mRemote.transact(2, obtain, null, 1);
                            } finally {
                                obtain.recycle();
                            }
                        }
                    });
                }
            };
            this.mSplitSelectListener = new AnonymousClass2();
            this.mController = splitScreenController;
            this.mListener = new SingleInstanceRemoteListener(splitScreenController, new SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda4(this, 5), new SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda4(this, 0));
            this.mSelectListener = new SingleInstanceRemoteListener(splitScreenController, new SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda4(this, 1), new SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda4(this, 2));
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder
        public final void invalidate() {
            this.mController = null;
            this.mListener.unregister();
            this.mSelectListener.unregister();
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IInterface queryLocalInterface;
            IInterface queryLocalInterface2;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.wm.shell.splitscreen.ISplitScreen");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.wm.shell.splitscreen.ISplitScreen");
                return true;
            }
            ISplitScreenListener$Stub$Proxy iSplitScreenListener$Stub$Proxy = null;
            ISplitSelectListener$Stub$Proxy iSplitSelectListener$Stub$Proxy = null;
            switch (i) {
                case 2:
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder != null) {
                        IInterface queryLocalInterface3 = readStrongBinder.queryLocalInterface("com.android.wm.shell.splitscreen.ISplitScreenListener");
                        if (queryLocalInterface3 == null || !(queryLocalInterface3 instanceof ISplitScreenListener$Stub$Proxy)) {
                            iSplitScreenListener$Stub$Proxy = new ISplitScreenListener$Stub$Proxy();
                            iSplitScreenListener$Stub$Proxy.mRemote = readStrongBinder;
                        } else {
                            iSplitScreenListener$Stub$Proxy = (ISplitScreenListener$Stub$Proxy) queryLocalInterface3;
                        }
                    }
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "registerSplitScreenListener", new SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda1(this, iSplitScreenListener$Stub$Proxy), false);
                    return true;
                case 3:
                    IBinder readStrongBinder2 = parcel.readStrongBinder();
                    if (readStrongBinder2 != null && (queryLocalInterface = readStrongBinder2.queryLocalInterface("com.android.wm.shell.splitscreen.ISplitScreenListener")) != null && (queryLocalInterface instanceof ISplitScreenListener$Stub$Proxy)) {
                    }
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "unregisterSplitScreenListener", new SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda4(this, 3), false);
                    return true;
                case 4:
                case ViewNode.TRANSLATIONY_FIELD_NUMBER /* 12 */:
                case ViewNode.SCALEX_FIELD_NUMBER /* 13 */:
                case ViewNode.SCALEY_FIELD_NUMBER /* 14 */:
                case 15:
                case 16:
                case ViewNode.ELEVATION_FIELD_NUMBER /* 19 */:
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
                case 5:
                    final int readInt = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    final int i3 = 1;
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "removeFromSideStage", new Consumer() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda10
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            int i4 = i3;
                            int i5 = readInt;
                            SplitScreenController splitScreenController = (SplitScreenController) obj;
                            switch (i4) {
                                case 0:
                                    splitScreenController.exitSplitScreen(i5, 0);
                                    break;
                                default:
                                    splitScreenController.removeFromSideStage(i5);
                                    break;
                            }
                        }
                    }, false);
                    return true;
                case 6:
                    final int readInt2 = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    final int i4 = 0;
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "exitSplitScreen", new Consumer() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda10
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            int i42 = i4;
                            int i5 = readInt2;
                            SplitScreenController splitScreenController = (SplitScreenController) obj;
                            switch (i42) {
                                case 0:
                                    splitScreenController.exitSplitScreen(i5, 0);
                                    break;
                                default:
                                    splitScreenController.removeFromSideStage(i5);
                                    break;
                            }
                        }
                    }, false);
                    return true;
                case 7:
                    final boolean readBoolean = parcel.readBoolean();
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "exitSplitScreenOnHide", new Consumer() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda9
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((SplitScreenController) obj).mStageCoordinator.mExitSplitScreenOnHide = readBoolean;
                        }
                    }, false);
                    return true;
                case 8:
                    final int readInt3 = parcel.readInt();
                    final int readInt4 = parcel.readInt();
                    final Bundle bundle = (Bundle) parcel.readTypedObject(Bundle.CREATOR);
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "startTask", new Consumer() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda16
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((SplitScreenController) obj).startTask(readInt3, readInt4, bundle, null);
                        }
                    }, false);
                    return true;
                case 9:
                    final String readString = parcel.readString();
                    final String readString2 = parcel.readString();
                    final int readInt5 = parcel.readInt();
                    final Bundle bundle2 = (Bundle) parcel.readTypedObject(Bundle.CREATOR);
                    final UserHandle userHandle = (UserHandle) parcel.readTypedObject(UserHandle.CREATOR);
                    final InstanceId instanceId = (InstanceId) parcel.readTypedObject(InstanceId.CREATOR);
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "startShortcut", new Consumer() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda17
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            String str = readString;
                            String str2 = readString2;
                            int i5 = readInt5;
                            Bundle bundle3 = bundle2;
                            UserHandle userHandle2 = userHandle;
                            InstanceId instanceId2 = instanceId;
                            SplitScreenController splitScreenController = (SplitScreenController) obj;
                            splitScreenController.getClass();
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -7987057462806301533L, 1, 3L);
                            }
                            SplitscreenEventLogger splitscreenEventLogger = splitScreenController.mStageCoordinator.mLogger;
                            splitscreenEventLogger.mEnterSessionId = instanceId2;
                            splitscreenEventLogger.mEnterReason = 3;
                            splitScreenController.startShortcut(str, str2, i5, bundle3, userHandle2);
                        }
                    }, false);
                    return true;
                case 10:
                    final PendingIntent pendingIntent = (PendingIntent) parcel.readTypedObject(PendingIntent.CREATOR);
                    final int readInt6 = parcel.readInt();
                    final Intent intent = (Intent) parcel.readTypedObject(Intent.CREATOR);
                    final int readInt7 = parcel.readInt();
                    final Bundle bundle3 = (Bundle) parcel.readTypedObject(Bundle.CREATOR);
                    final InstanceId instanceId2 = (InstanceId) parcel.readTypedObject(InstanceId.CREATOR);
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "startIntent", new Consumer() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda2
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            PendingIntent pendingIntent2 = pendingIntent;
                            int i5 = readInt6;
                            Intent intent2 = intent;
                            int i6 = readInt7;
                            Bundle bundle4 = bundle3;
                            InstanceId instanceId3 = instanceId2;
                            SplitScreenController splitScreenController = (SplitScreenController) obj;
                            splitScreenController.getClass();
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -1752260962322402115L, 1, 3L);
                            }
                            SplitscreenEventLogger splitscreenEventLogger = splitScreenController.mStageCoordinator.mLogger;
                            splitscreenEventLogger.mEnterSessionId = instanceId3;
                            splitscreenEventLogger.mEnterReason = 3;
                            splitScreenController.startIntent(pendingIntent2, i5, intent2, i6, bundle4, null);
                        }
                    }, false);
                    return true;
                case ViewNode.TRANSLATIONX_FIELD_NUMBER /* 11 */:
                    final int readInt8 = parcel.readInt();
                    Parcelable.Creator creator = Bundle.CREATOR;
                    final Bundle bundle4 = (Bundle) parcel.readTypedObject(creator);
                    final int readInt9 = parcel.readInt();
                    final Bundle bundle5 = (Bundle) parcel.readTypedObject(creator);
                    final int readInt10 = parcel.readInt();
                    final int readInt11 = parcel.readInt();
                    final RemoteTransition remoteTransition = (RemoteTransition) parcel.readTypedObject(RemoteTransition.CREATOR);
                    final InstanceId instanceId3 = (InstanceId) parcel.readTypedObject(InstanceId.CREATOR);
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "startTasks", new Consumer() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda8
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((SplitScreenController) obj).mStageCoordinator.startTasks(readInt8, bundle4, readInt9, bundle5, readInt10, readInt11, remoteTransition, instanceId3);
                        }
                    }, false);
                    return true;
                case ViewNode.CLIPCHILDREN_FIELD_NUMBER /* 17 */:
                    final PendingIntent pendingIntent2 = (PendingIntent) parcel.readTypedObject(PendingIntent.CREATOR);
                    final int readInt12 = parcel.readInt();
                    Parcelable.Creator creator2 = Bundle.CREATOR;
                    final Bundle bundle6 = (Bundle) parcel.readTypedObject(creator2);
                    final int readInt13 = parcel.readInt();
                    final Bundle bundle7 = (Bundle) parcel.readTypedObject(creator2);
                    final int readInt14 = parcel.readInt();
                    final int readInt15 = parcel.readInt();
                    final RemoteTransition remoteTransition2 = (RemoteTransition) parcel.readTypedObject(RemoteTransition.CREATOR);
                    final InstanceId instanceId4 = (InstanceId) parcel.readTypedObject(InstanceId.CREATOR);
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "startIntentAndTask", new Consumer() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda18
                        /* JADX WARN: Removed duplicated region for block: B:25:0x00ea  */
                        /* JADX WARN: Removed duplicated region for block: B:28:0x012d  */
                        /* JADX WARN: Removed duplicated region for block: B:46:0x0179  */
                        /* JADX WARN: Removed duplicated region for block: B:50:0x017d  */
                        /* JADX WARN: Removed duplicated region for block: B:52:0x0143  */
                        /* JADX WARN: Removed duplicated region for block: B:53:0x0117  */
                        @Override // java.util.function.Consumer
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final void accept(java.lang.Object r19) {
                            /*
                                Method dump skipped, instructions count: 399
                                To view this dump add '--comments-level debug' option
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda18.accept(java.lang.Object):void");
                        }
                    }, false);
                    return true;
                case ViewNode.VISIBILITY_FIELD_NUMBER /* 18 */:
                    final ShortcutInfo shortcutInfo = (ShortcutInfo) parcel.readTypedObject(ShortcutInfo.CREATOR);
                    Parcelable.Creator creator3 = Bundle.CREATOR;
                    final Bundle bundle8 = (Bundle) parcel.readTypedObject(creator3);
                    final int readInt16 = parcel.readInt();
                    final Bundle bundle9 = (Bundle) parcel.readTypedObject(creator3);
                    final int readInt17 = parcel.readInt();
                    final int readInt18 = parcel.readInt();
                    final RemoteTransition remoteTransition3 = (RemoteTransition) parcel.readTypedObject(RemoteTransition.CREATOR);
                    final InstanceId instanceId5 = (InstanceId) parcel.readTypedObject(InstanceId.CREATOR);
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "startShortcutAndTask", new Consumer() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda7
                        /* JADX WARN: Removed duplicated region for block: B:26:0x00ce  */
                        /* JADX WARN: Removed duplicated region for block: B:29:0x00fd  */
                        /* JADX WARN: Removed duplicated region for block: B:35:0x0119  */
                        /* JADX WARN: Removed duplicated region for block: B:40:0x00f4  */
                        @Override // java.util.function.Consumer
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final void accept(java.lang.Object r18) {
                            /*
                                Method dump skipped, instructions count: 319
                                To view this dump add '--comments-level debug' option
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda7.accept(java.lang.Object):void");
                        }
                    }, false);
                    return true;
                case 20:
                    Parcelable.Creator creator4 = PendingIntent.CREATOR;
                    final PendingIntent pendingIntent3 = (PendingIntent) parcel.readTypedObject(creator4);
                    final int readInt19 = parcel.readInt();
                    Parcelable.Creator creator5 = ShortcutInfo.CREATOR;
                    final ShortcutInfo shortcutInfo2 = (ShortcutInfo) parcel.readTypedObject(creator5);
                    Parcelable.Creator creator6 = Bundle.CREATOR;
                    final Bundle bundle10 = (Bundle) parcel.readTypedObject(creator6);
                    final PendingIntent pendingIntent4 = (PendingIntent) parcel.readTypedObject(creator4);
                    final int readInt20 = parcel.readInt();
                    final ShortcutInfo shortcutInfo3 = (ShortcutInfo) parcel.readTypedObject(creator5);
                    final Bundle bundle11 = (Bundle) parcel.readTypedObject(creator6);
                    final int readInt21 = parcel.readInt();
                    final int readInt22 = parcel.readInt();
                    final RemoteTransition remoteTransition4 = (RemoteTransition) parcel.readTypedObject(RemoteTransition.CREATOR);
                    final InstanceId instanceId6 = (InstanceId) parcel.readTypedObject(InstanceId.CREATOR);
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "startIntents", new Consumer() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda14
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ShortcutInfo shortcutInfo4;
                            PendingIntent pendingIntent5;
                            boolean z;
                            Intent intent2;
                            Intent intent3;
                            InstanceId instanceId7;
                            RemoteTransition remoteTransition5;
                            Intent intent4;
                            Intent intent5;
                            Intent intent6;
                            Intent intent7;
                            PendingIntent pendingIntent6 = pendingIntent3;
                            int i5 = readInt19;
                            ShortcutInfo shortcutInfo5 = shortcutInfo2;
                            Bundle bundle12 = bundle10;
                            PendingIntent pendingIntent7 = pendingIntent4;
                            int i6 = readInt20;
                            ShortcutInfo shortcutInfo6 = shortcutInfo3;
                            Bundle bundle13 = bundle11;
                            int i7 = readInt21;
                            int i8 = readInt22;
                            RemoteTransition remoteTransition6 = remoteTransition4;
                            InstanceId instanceId8 = instanceId6;
                            SplitScreenController splitScreenController = (SplitScreenController) obj;
                            splitScreenController.getClass();
                            String packageName = SplitScreenUtils.getPackageName(pendingIntent6);
                            String packageName2 = SplitScreenUtils.getPackageName(pendingIntent7);
                            ActivityOptions fromBundle = bundle12 != null ? ActivityOptions.fromBundle(bundle12) : ActivityOptions.makeBasic();
                            ActivityOptions fromBundle2 = bundle13 != null ? ActivityOptions.fromBundle(bundle13) : ActivityOptions.makeBasic();
                            if (MultiInstanceHelper.samePackage(packageName, i5, i6, packageName2)) {
                                if (splitScreenController.mMultiInstanceHelpher.supportsMultiInstanceSplit((pendingIntent6 == null || (intent7 = pendingIntent6.getIntent()) == null) ? null : intent7.getComponent())) {
                                    Intent intent8 = new Intent();
                                    intent8.addFlags(134217728);
                                    if (shortcutInfo5 != null) {
                                        fromBundle.setApplyMultipleTaskFlagForShortcut(true);
                                    }
                                    if (shortcutInfo6 != null) {
                                        fromBundle2.setApplyMultipleTaskFlagForShortcut(true);
                                    }
                                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[1]) {
                                        shortcutInfo4 = shortcutInfo6;
                                        intent6 = intent8;
                                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 2564351321352908886L, 0, null);
                                    } else {
                                        intent6 = intent8;
                                        shortcutInfo4 = shortcutInfo6;
                                    }
                                    pendingIntent5 = pendingIntent7;
                                    z = true;
                                    intent2 = intent6;
                                } else {
                                    shortcutInfo4 = shortcutInfo6;
                                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[1]) {
                                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 2175723388761225132L, 0, null);
                                    }
                                    Log.w("SplitScreenController", SplitScreenUtils.splitFailureMessage("startIntents", "app package " + packageName + " does not support multi-instance"));
                                    Toast.makeText(splitScreenController.mContext, R.string.dock_multi_instances_not_supported_text, 0).show();
                                    z = false;
                                    intent2 = null;
                                    pendingIntent5 = null;
                                }
                            } else {
                                shortcutInfo4 = shortcutInfo6;
                                pendingIntent5 = pendingIntent7;
                                z = false;
                                intent2 = null;
                            }
                            if (bundle13 != null) {
                                Intent intent9 = (Intent) bundle13.getParcelable("key_extra_widget_intent", Intent.class);
                                if (z && intent9 != null) {
                                    intent9.addFlags(134217728);
                                } else if (intent9 == null) {
                                    if (z) {
                                        intent5 = new Intent();
                                        intent5.addFlags(134217728);
                                    } else {
                                        intent5 = null;
                                    }
                                    intent3 = intent5;
                                }
                                intent5 = intent9;
                                intent3 = intent5;
                            } else {
                                intent3 = null;
                            }
                            StageCoordinator stageCoordinator = splitScreenController.mStageCoordinator;
                            Bundle bundle14 = fromBundle.toBundle();
                            Bundle bundle15 = fromBundle2.toBundle();
                            stageCoordinator.getClass();
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                                intent4 = intent3;
                                remoteTransition5 = remoteTransition6;
                                instanceId7 = instanceId8;
                                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -3036901654114583224L, 80, String.valueOf(pendingIntent6.getIntent()), String.valueOf(pendingIntent5 != null ? pendingIntent5.getIntent() : "null"), Long.valueOf(i7), Long.valueOf(i8));
                            } else {
                                instanceId7 = instanceId8;
                                remoteTransition5 = remoteTransition6;
                                intent4 = intent3;
                            }
                            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                            if (pendingIntent5 == null) {
                                if (bundle14 == null) {
                                    bundle14 = new Bundle();
                                }
                                StageCoordinator.addActivityOptions(bundle14, null);
                                if (shortcutInfo5 != null) {
                                    windowContainerTransaction.startShortcut(stageCoordinator.mContext.getPackageName(), shortcutInfo5, bundle14);
                                } else {
                                    windowContainerTransaction.sendPendingIntent(pendingIntent6, intent2, bundle14);
                                }
                                stageCoordinator.mSplitTransitions.startFullscreenTransition(windowContainerTransaction, remoteTransition5);
                                return;
                            }
                            RemoteTransition remoteTransition7 = remoteTransition5;
                            boolean isIntentInPip = stageCoordinator.mMixedHandler.isIntentInPip(pendingIntent6);
                            boolean isIntentInPip2 = stageCoordinator.mMixedHandler.isIntentInPip(pendingIntent5);
                            if (isIntentInPip || isIntentInPip2) {
                                ShortcutInfo shortcutInfo7 = shortcutInfo4;
                                Intent intent10 = intent4;
                                if (!isIntentInPip2) {
                                    bundle14 = bundle15;
                                }
                                if (bundle14 == null) {
                                    bundle14 = new Bundle();
                                }
                                StageCoordinator.addActivityOptions(bundle14, null);
                                if (shortcutInfo5 != null || shortcutInfo7 != null) {
                                    if (!isIntentInPip2) {
                                        shortcutInfo5 = shortcutInfo7;
                                    }
                                    windowContainerTransaction.startShortcut(stageCoordinator.mContext.getPackageName(), shortcutInfo5, bundle14);
                                    stageCoordinator.mSplitTransitions.startFullscreenTransition(windowContainerTransaction, remoteTransition7);
                                    return;
                                }
                                if (!isIntentInPip2) {
                                    pendingIntent6 = pendingIntent5;
                                }
                                if (!isIntentInPip2) {
                                    intent2 = intent10;
                                }
                                StageCoordinator.addActivityOptions(bundle14, null);
                                windowContainerTransaction.sendPendingIntent(pendingIntent6, intent2, bundle14);
                                stageCoordinator.mSplitTransitions.startFullscreenTransition(windowContainerTransaction, remoteTransition7);
                                return;
                            }
                            StageTaskListener stageTaskListener = stageCoordinator.mMainStage;
                            if (!stageTaskListener.mIsActive) {
                                stageCoordinator.activateSplit(windowContainerTransaction, false);
                            }
                            stageCoordinator.setSideStagePosition(i7, windowContainerTransaction);
                            stageCoordinator.mSplitLayout.setDivideRatio(i8);
                            stageCoordinator.updateWindowBounds(stageCoordinator.mSplitLayout, windowContainerTransaction);
                            windowContainerTransaction.reorder(stageCoordinator.mRootTaskInfo.token, true);
                            windowContainerTransaction.setReparentLeafTaskIfRelaunch(stageCoordinator.mRootTaskInfo.token, false);
                            stageCoordinator.setRootForceTranslucent(windowContainerTransaction, false);
                            if (bundle14 == null) {
                                bundle14 = new Bundle();
                            }
                            StageCoordinator.addActivityOptions(bundle14, stageCoordinator.mSideStage);
                            if (shortcutInfo5 != null) {
                                windowContainerTransaction.startShortcut(stageCoordinator.mContext.getPackageName(), shortcutInfo5, bundle14);
                            } else {
                                windowContainerTransaction.sendPendingIntent(pendingIntent6, intent2, bundle14);
                            }
                            if (bundle15 == null) {
                                bundle15 = new Bundle();
                            }
                            StageCoordinator.addActivityOptions(bundle15, stageTaskListener);
                            if (shortcutInfo4 != null) {
                                windowContainerTransaction.startShortcut(stageCoordinator.mContext.getPackageName(), shortcutInfo4, bundle15);
                            } else {
                                windowContainerTransaction.sendPendingIntent(pendingIntent5, intent4, bundle15);
                            }
                            stageCoordinator.mSplitTransitions.startEnterTransition(windowContainerTransaction, remoteTransition7, stageCoordinator, 1004, false);
                            if (instanceId7 != null) {
                                SplitscreenEventLogger splitscreenEventLogger = stageCoordinator.mLogger;
                                splitscreenEventLogger.mEnterSessionId = instanceId7;
                                splitscreenEventLogger.mEnterReason = 3;
                            }
                        }
                    }, false);
                    return true;
                case 21:
                    IBinder readStrongBinder3 = parcel.readStrongBinder();
                    if (readStrongBinder3 != null) {
                        IInterface queryLocalInterface4 = readStrongBinder3.queryLocalInterface("com.android.wm.shell.splitscreen.ISplitSelectListener");
                        if (queryLocalInterface4 == null || !(queryLocalInterface4 instanceof ISplitSelectListener$Stub$Proxy)) {
                            iSplitSelectListener$Stub$Proxy = new ISplitSelectListener$Stub$Proxy();
                            iSplitSelectListener$Stub$Proxy.mRemote = readStrongBinder3;
                        } else {
                            iSplitSelectListener$Stub$Proxy = (ISplitSelectListener$Stub$Proxy) queryLocalInterface4;
                        }
                    }
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "registerSplitSelectListener", new SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda1(this, iSplitSelectListener$Stub$Proxy), false);
                    return true;
                case 22:
                    IBinder readStrongBinder4 = parcel.readStrongBinder();
                    if (readStrongBinder4 != null && (queryLocalInterface2 = readStrongBinder4.queryLocalInterface("com.android.wm.shell.splitscreen.ISplitSelectListener")) != null && (queryLocalInterface2 instanceof ISplitSelectListener$Stub$Proxy)) {
                    }
                    parcel.enforceNoDataAvail();
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "unregisterSplitSelectListener", new SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda4(this, 4), false);
                    return true;
                case 23:
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "switchSplitPosition", new SplitScreenController$ISplitScreenImpl$$ExternalSyntheticLambda11(), false);
                    return true;
            }
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder, android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}

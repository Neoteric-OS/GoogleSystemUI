package com.android.wm.shell.splitscreen;

import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.SparseArray;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.view.WindowManager;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.internal.util.ArrayUtils;
import com.android.launcher3.icons.IconProvider;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.SurfaceUtils;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.common.split.SplitDecorManager;
import com.android.wm.shell.common.split.SplitScreenUtils;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.split.SplitScreenConstants;
import com.android.wm.shell.splitscreen.SplitScreen;
import com.android.wm.shell.splitscreen.StageCoordinator;
import com.android.wm.shell.transition.Transitions;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StageTaskListener implements ShellTaskOrganizer.TaskListener {
    public final StageCoordinator.StageListenerImpl mCallbacks;
    public final Context mContext;
    public SurfaceControl mDimLayer;
    public final IconProvider mIconProvider;
    public boolean mIsActive;
    public SurfaceControl mRootLeash;
    public ActivityManager.RunningTaskInfo mRootTaskInfo;
    public SplitDecorManager mSplitDecorManager;
    public final SyncTransactionQueue mSyncQueue;
    public final Optional mWindowDecorViewModel;
    public final SparseArray mChildrenTaskInfo = new SparseArray();
    public final SparseArray mChildrenLeashes = new SparseArray();

    public StageTaskListener(Context context, ShellTaskOrganizer shellTaskOrganizer, StageCoordinator.StageListenerImpl stageListenerImpl, SyncTransactionQueue syncTransactionQueue, IconProvider iconProvider, Optional optional) {
        this.mContext = context;
        this.mCallbacks = stageListenerImpl;
        this.mSyncQueue = syncTransactionQueue;
        this.mIconProvider = iconProvider;
        this.mWindowDecorViewModel = optional;
        shellTaskOrganizer.createRootTask(6, this);
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void attachChildSurfaceToTask(int i, SurfaceControl.Builder builder) {
        builder.setParent(findTaskSurface$3(i));
    }

    public final boolean contains(Predicate predicate) {
        ActivityManager.RunningTaskInfo runningTaskInfo = this.mRootTaskInfo;
        return (runningTaskInfo != null && predicate.test(runningTaskInfo)) || getChildTaskInfo(predicate) != null;
    }

    public final void doForAllChildTasks(Consumer consumer) {
        for (int size = this.mChildrenTaskInfo.size() - 1; size >= 0; size--) {
            consumer.accept(Integer.valueOf(((ActivityManager.RunningTaskInfo) this.mChildrenTaskInfo.valueAt(size)).taskId));
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void dump$1(PrintWriter printWriter, String str) {
        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "  "), "  ");
        if (this.mChildrenTaskInfo.size() > 0) {
            printWriter.println(str + "Children list:");
            for (int size = this.mChildrenTaskInfo.size() + (-1); size >= 0; size += -1) {
                ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) this.mChildrenTaskInfo.valueAt(size);
                printWriter.println(m + "Task#" + size + " taskID=" + runningTaskInfo.taskId + " baseActivity=" + runningTaskInfo.baseActivity);
            }
        }
    }

    public final void evictAllChildren(WindowContainerTransaction windowContainerTransaction) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 1471757429785376235L, 0, null);
        }
        for (int size = this.mChildrenTaskInfo.size() - 1; size >= 0; size--) {
            windowContainerTransaction.reparent(((ActivityManager.RunningTaskInfo) this.mChildrenTaskInfo.valueAt(size)).token, (WindowContainerToken) null, false);
        }
    }

    public final void evictChildren(int i, WindowContainerTransaction windowContainerTransaction) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -3990678864717923825L, 1, Long.valueOf(i));
        }
        ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) this.mChildrenTaskInfo.get(i);
        if (runningTaskInfo != null) {
            windowContainerTransaction.reparent(runningTaskInfo.token, (WindowContainerToken) null, false);
        }
    }

    public final void evictInvisibleChildren(WindowContainerTransaction windowContainerTransaction) {
        for (int size = this.mChildrenTaskInfo.size() - 1; size >= 0; size--) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) this.mChildrenTaskInfo.valueAt(size);
            if (!runningTaskInfo.isVisible) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 1686723743452379969L, 1, Long.valueOf(runningTaskInfo.taskId));
                }
                windowContainerTransaction.reparent(runningTaskInfo.token, (WindowContainerToken) null, false);
            }
        }
    }

    public final void evictNonOpeningChildren(RemoteAnimationTarget[] remoteAnimationTargetArr, WindowContainerTransaction windowContainerTransaction) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 2166271960635161072L, 0, null);
        }
        SparseArray clone = this.mChildrenTaskInfo.clone();
        for (RemoteAnimationTarget remoteAnimationTarget : remoteAnimationTargetArr) {
            if (remoteAnimationTarget.mode == 0) {
                clone.remove(remoteAnimationTarget.taskId);
            }
        }
        for (int size = clone.size() - 1; size >= 0; size--) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) clone.valueAt(size);
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -6410374724342172915L, 1, Long.valueOf(runningTaskInfo.taskId));
            }
            windowContainerTransaction.reparent(runningTaskInfo.token, (WindowContainerToken) null, false);
        }
    }

    public final void evictOtherChildren(int i, WindowContainerTransaction windowContainerTransaction) {
        for (int size = this.mChildrenTaskInfo.size() - 1; size >= 0; size--) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) this.mChildrenTaskInfo.valueAt(size);
            if (i != runningTaskInfo.taskId) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -376587444656459700L, 1, Long.valueOf(i));
                }
                windowContainerTransaction.reparent(runningTaskInfo.token, (WindowContainerToken) null, false);
            }
        }
    }

    public final SurfaceControl findTaskSurface$3(int i) {
        if (this.mRootTaskInfo.taskId == i) {
            return this.mRootLeash;
        }
        if (this.mChildrenLeashes.contains(i)) {
            return (SurfaceControl) this.mChildrenLeashes.get(i);
        }
        throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "There is no surface for taskId="));
    }

    public final ActivityManager.RunningTaskInfo getChildTaskInfo(Predicate predicate) {
        for (int size = this.mChildrenTaskInfo.size() - 1; size >= 0; size--) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) this.mChildrenTaskInfo.valueAt(size);
            if (predicate.test(runningTaskInfo)) {
                return runningTaskInfo;
            }
        }
        return null;
    }

    public final int getTopChildTaskUid() {
        ActivityManager.RunningTaskInfo childTaskInfo = getChildTaskInfo(new StageTaskListener$$ExternalSyntheticLambda2(0));
        if (childTaskInfo != null) {
            return childTaskInfo.topActivityInfo.applicationInfo.uid;
        }
        return 0;
    }

    public final int getTopVisibleChildTaskId() {
        ActivityManager.RunningTaskInfo childTaskInfo = getChildTaskInfo(new StageTaskListener$$ExternalSyntheticLambda2(1));
        if (childTaskInfo != null) {
            return childTaskInfo.taskId;
        }
        return -1;
    }

    public final void onResizing(Rect rect, Rect rect2, SurfaceControl.Transaction transaction, int i, int i2, boolean z) {
        ActivityManager.RunningTaskInfo runningTaskInfo;
        ActivityInfo activityInfo;
        ValueAnimator valueAnimator;
        SplitDecorManager splitDecorManager = this.mSplitDecorManager;
        if (splitDecorManager == null || (runningTaskInfo = this.mRootTaskInfo) == null || splitDecorManager.mVeilIconView == null) {
            return;
        }
        if (!splitDecorManager.mIsCurrentlyChanging) {
            splitDecorManager.mIsCurrentlyChanging = true;
            splitDecorManager.mOldMainBounds.set(rect);
            splitDecorManager.mOldSideBounds.set(rect2);
        }
        splitDecorManager.mInstantaneousBounds.set(rect);
        splitDecorManager.mOffsetX = i;
        splitDecorManager.mOffsetY = i2;
        boolean z2 = (splitDecorManager.mOldSideBounds.width() <= 1 || splitDecorManager.mOldSideBounds.height() <= 1) || (rect.width() > splitDecorManager.mOldMainBounds.width() || rect.height() > splitDecorManager.mOldMainBounds.height());
        boolean z3 = z2 != splitDecorManager.mShown;
        if (z3 && (valueAnimator = splitDecorManager.mFadeAnimator) != null && valueAnimator.isRunning()) {
            splitDecorManager.mFadeAnimator.cancel();
        }
        if (splitDecorManager.mBackgroundLeash == null) {
            SurfaceControl makeColorLayer = SurfaceUtils.makeColorLayer(splitDecorManager.mHostLeash, "ResizingBackground");
            splitDecorManager.mBackgroundLeash = makeColorLayer;
            transaction.setColor(makeColorLayer, SplitDecorManager.getResizingBackgroundColor(runningTaskInfo)).setLayer(splitDecorManager.mBackgroundLeash, 2147483646);
        }
        if (splitDecorManager.mGapBackgroundLeash == null && !z) {
            boolean z4 = rect.height() == rect2.height();
            int width = z4 ? splitDecorManager.mOldMainBounds.width() : 0;
            int height = z4 ? 0 : splitDecorManager.mOldMainBounds.height();
            SurfaceControl makeColorLayer2 = SurfaceUtils.makeColorLayer(splitDecorManager.mHostLeash, "GapBackground");
            splitDecorManager.mGapBackgroundLeash = makeColorLayer2;
            transaction.setColor(makeColorLayer2, SplitDecorManager.getResizingBackgroundColor(runningTaskInfo)).setLayer(splitDecorManager.mGapBackgroundLeash, 2147483645).setPosition(splitDecorManager.mGapBackgroundLeash, width, height).setWindowCrop(splitDecorManager.mGapBackgroundLeash, rect2.width(), rect2.height());
        }
        if (splitDecorManager.mIcon == null && (activityInfo = runningTaskInfo.topActivityInfo) != null) {
            Drawable icon = splitDecorManager.mIconProvider.getIcon(activityInfo);
            splitDecorManager.mIcon = icon;
            splitDecorManager.mVeilIconView.setImageDrawable(icon);
            splitDecorManager.mVeilIconView.setVisibility(0);
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) splitDecorManager.mViewHost.getView().getLayoutParams();
            int i3 = splitDecorManager.mIconSize;
            layoutParams.width = i3;
            layoutParams.height = i3;
            splitDecorManager.mViewHost.relayout(layoutParams);
            transaction.setLayer(splitDecorManager.mIconLeash, Integer.MAX_VALUE);
        }
        transaction.setPosition(splitDecorManager.mIconLeash, (rect.width() / 2) - (splitDecorManager.mIconSize / 2), (rect.height() / 2) - (splitDecorManager.mIconSize / 2));
        if (z3) {
            if (z) {
                transaction.setAlpha(splitDecorManager.mBackgroundLeash, z2 ? 1.0f : 0.0f);
                transaction.setVisibility(splitDecorManager.mBackgroundLeash, z2);
                transaction.setAlpha(splitDecorManager.mIconLeash, z2 ? 1.0f : 0.0f);
                transaction.setVisibility(splitDecorManager.mIconLeash, z2);
            } else {
                splitDecorManager.startFadeAnimation(z2, false, null, false);
            }
            splitDecorManager.mShown = z2;
        }
    }

    public final void onSplitScreenListenerRegistered(SplitScreen.SplitScreenListener splitScreenListener, int i) {
        for (int size = this.mChildrenTaskInfo.size() - 1; size >= 0; size--) {
            int keyAt = this.mChildrenTaskInfo.keyAt(size);
            splitScreenListener.onTaskStageChanged(keyAt, i, ((ActivityManager.RunningTaskInfo) this.mChildrenTaskInfo.get(keyAt)).isVisible);
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -4559936762896106090L, 21, Long.valueOf(runningTaskInfo.taskId), Long.valueOf(runningTaskInfo.parentTaskId), Long.valueOf(this.mRootTaskInfo != null ? r0.taskId : -1L), String.valueOf(runningTaskInfo.baseActivity));
        }
        ActivityManager.RunningTaskInfo runningTaskInfo2 = this.mRootTaskInfo;
        SyncTransactionQueue syncTransactionQueue = this.mSyncQueue;
        StageCoordinator.StageListenerImpl stageListenerImpl = this.mCallbacks;
        if (runningTaskInfo2 == null) {
            this.mRootLeash = surfaceControl;
            this.mRootTaskInfo = runningTaskInfo;
            this.mSplitDecorManager = new SplitDecorManager(this.mRootTaskInfo.configuration, this.mIconProvider);
            stageListenerImpl.mHasRootTask = true;
            StageCoordinator.this.onRootTaskAppeared();
            sendStatusChanged();
            syncTransactionQueue.runInSync(new StageTaskListener$$ExternalSyntheticLambda1(this, 0));
            return;
        }
        if (runningTaskInfo.parentTaskId != runningTaskInfo2.taskId) {
            throw new IllegalArgumentException(this + "\n Unknown task: " + runningTaskInfo + "\n mRootTaskInfo: " + this.mRootTaskInfo);
        }
        int i = runningTaskInfo.taskId;
        this.mChildrenLeashes.put(i, surfaceControl);
        this.mChildrenTaskInfo.put(i, runningTaskInfo);
        stageListenerImpl.onChildTaskStatusChanged(i, true, runningTaskInfo.isVisible && runningTaskInfo.isVisibleRequested);
        if (Transitions.ENABLE_SHELL_TRANSITIONS) {
            return;
        }
        syncTransactionQueue.runInSync(new StageTaskListener$$ExternalSyntheticLambda9(surfaceControl, runningTaskInfo, runningTaskInfo.positionInParent, true));
        StageCoordinator stageCoordinator = StageCoordinator.this;
        stageCoordinator.getClass();
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 4048273335442947181L, 7, Boolean.valueOf(stageListenerImpl == stageCoordinator.mMainStageListener), Long.valueOf(i));
        }
        if (stageListenerImpl == stageCoordinator.mSideStageListener && !stageCoordinator.isSplitScreenVisible()) {
            StageTaskListener stageTaskListener = stageCoordinator.mMainStage;
            if (stageTaskListener.mIsActive && stageCoordinator.mSplitRequest == null) {
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                stageCoordinator.prepareEnterSplitScreen(windowContainerTransaction);
                stageTaskListener.evictAllChildren(windowContainerTransaction);
                stageCoordinator.mSideStage.evictOtherChildren(i, windowContainerTransaction);
                SyncTransactionQueue syncTransactionQueue2 = stageCoordinator.mSyncQueue;
                syncTransactionQueue2.queue(windowContainerTransaction);
                syncTransactionQueue2.runInSync(new StageCoordinator$$ExternalSyntheticLambda3(2, stageCoordinator));
            }
        }
        sendStatusChanged();
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 4796484936032465800L, 1, Long.valueOf(runningTaskInfo.taskId), String.valueOf(runningTaskInfo.baseActivity));
        }
        this.mWindowDecorViewModel.ifPresent(new StageTaskListener$$ExternalSyntheticLambda3(runningTaskInfo, 0));
        ActivityManager.RunningTaskInfo runningTaskInfo2 = this.mRootTaskInfo;
        int i = runningTaskInfo2.taskId;
        int i2 = runningTaskInfo.taskId;
        SyncTransactionQueue syncTransactionQueue = this.mSyncQueue;
        if (i == i2) {
            if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
                boolean z = runningTaskInfo2.isVisible;
                boolean z2 = runningTaskInfo.isVisible;
                if (z != z2) {
                    if (z2) {
                        this.mSplitDecorManager.inflate(this.mContext, this.mRootLeash);
                    } else {
                        syncTransactionQueue.runInSync(new StageTaskListener$$ExternalSyntheticLambda1(this, 1));
                    }
                }
            }
            this.mRootTaskInfo = runningTaskInfo;
        } else {
            if (runningTaskInfo.parentTaskId != i) {
                throw new IllegalArgumentException(this + "\n Unknown task: " + runningTaskInfo + "\n mRootTaskInfo: " + this.mRootTaskInfo);
            }
            boolean z3 = runningTaskInfo.supportsMultiWindow;
            StageCoordinator.StageListenerImpl stageListenerImpl = this.mCallbacks;
            if (!z3 || !ArrayUtils.contains(SplitScreenConstants.CONTROLLED_ACTIVITY_TYPES, runningTaskInfo.getActivityType()) || !ArrayUtils.contains(SplitScreenConstants.CONTROLLED_WINDOWING_MODES_WHEN_ACTIVE, runningTaskInfo.getWindowingMode())) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -113865461001536373L, 1, Long.valueOf(runningTaskInfo.taskId));
                }
                stageListenerImpl.getClass();
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 8876191938084552299L, 0, String.valueOf(runningTaskInfo));
                }
                StageCoordinator stageCoordinator = StageCoordinator.this;
                if (stageCoordinator.mMainStage.mIsActive) {
                    int i3 = stageCoordinator.mMainStageListener == stageListenerImpl ? 1 : 0;
                    boolean isSplitScreenVisible = stageCoordinator.isSplitScreenVisible();
                    int i4 = isSplitScreenVisible ? i3 ^ 1 : -1;
                    WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                    stageCoordinator.prepareExitSplitScreen(i4, windowContainerTransaction);
                    stageCoordinator.clearSplitPairedInRecents(1);
                    stageCoordinator.mSplitTransitions.startDismissTransition(windowContainerTransaction, stageCoordinator, i4, 1);
                    Log.w("StageCoordinator", SplitScreenUtils.splitFailureMessage("onNoLongerSupportMultiWindow", "app package " + runningTaskInfo.baseIntent.getComponent() + " does not support splitscreen, or is a controlled activity type"));
                    if (isSplitScreenVisible) {
                        stageCoordinator.mSplitUnsupportedToast.show();
                        stageCoordinator.notifySplitAnimationFinished();
                        return;
                    }
                    return;
                }
                return;
            }
            this.mChildrenTaskInfo.put(runningTaskInfo.taskId, runningTaskInfo);
            stageListenerImpl.onChildTaskStatusChanged(runningTaskInfo.taskId, true, runningTaskInfo.isVisible && runningTaskInfo.isVisibleRequested);
            if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
                syncTransactionQueue.runInSync(new StageTaskListener$$ExternalSyntheticLambda9((SurfaceControl) this.mChildrenLeashes.get(runningTaskInfo.taskId), runningTaskInfo, runningTaskInfo.positionInParent, false));
            }
        }
        if (Transitions.ENABLE_SHELL_TRANSITIONS) {
            return;
        }
        sendStatusChanged();
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -1056659881185624550L, 1, Long.valueOf(runningTaskInfo.taskId));
        }
        int i = runningTaskInfo.taskId;
        this.mWindowDecorViewModel.ifPresent(new StageTaskListener$$ExternalSyntheticLambda3(runningTaskInfo, 1));
        int i2 = this.mRootTaskInfo.taskId;
        StageCoordinator.StageListenerImpl stageListenerImpl = this.mCallbacks;
        if (i2 == i) {
            stageListenerImpl.mHasRootTask = false;
            stageListenerImpl.mVisible = false;
            stageListenerImpl.mHasChildren = false;
            StageCoordinator.this.onRootTaskVanished();
            this.mRootTaskInfo = null;
            this.mRootLeash = null;
            this.mSyncQueue.runInSync(new StageTaskListener$$ExternalSyntheticLambda1(this, 2));
            return;
        }
        if (this.mChildrenTaskInfo.contains(i)) {
            this.mChildrenTaskInfo.remove(i);
            this.mChildrenLeashes.remove(i);
            stageListenerImpl.onChildTaskStatusChanged(i, false, runningTaskInfo.isVisible);
            if (Transitions.ENABLE_SHELL_TRANSITIONS) {
                return;
            }
            sendStatusChanged();
            return;
        }
        throw new IllegalArgumentException(this + "\n Unknown task: " + runningTaskInfo + "\n mRootTaskInfo: " + this.mRootTaskInfo);
    }

    public final void removeAllTasks(WindowContainerTransaction windowContainerTransaction, boolean z) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 4354007016852765196L, 13, Long.valueOf(this.mChildrenTaskInfo.size()), Boolean.valueOf(z));
        }
        if (this.mChildrenTaskInfo.size() == 0) {
            return;
        }
        windowContainerTransaction.reparentTasks(this.mRootTaskInfo.token, (WindowContainerToken) null, (int[]) null, (int[]) null, z);
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void reparentChildSurfaceToTask(int i, SurfaceControl.Transaction transaction, SurfaceControl surfaceControl) {
        transaction.reparent(surfaceControl, findTaskSurface$3(i));
    }

    public final void sendStatusChanged() {
        boolean z = this.mRootTaskInfo.isVisible;
        boolean z2 = this.mChildrenTaskInfo.size() > 0;
        StageCoordinator.StageListenerImpl stageListenerImpl = this.mCallbacks;
        if (stageListenerImpl.mHasRootTask) {
            boolean z3 = stageListenerImpl.mHasChildren;
            StageCoordinator stageCoordinator = StageCoordinator.this;
            if (z3 != z2) {
                stageListenerImpl.mHasChildren = z2;
                stageCoordinator.getClass();
                boolean z4 = ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0];
                StageCoordinator.StageListenerImpl stageListenerImpl2 = stageCoordinator.mMainStageListener;
                if (z4) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -3319647324580990011L, 3, Boolean.valueOf(stageListenerImpl == stageListenerImpl2));
                }
                boolean z5 = stageListenerImpl.mHasChildren;
                StageCoordinator.StageListenerImpl stageListenerImpl3 = stageCoordinator.mSideStageListener;
                boolean z6 = stageListenerImpl == stageListenerImpl3;
                StageTaskListener stageTaskListener = stageCoordinator.mMainStage;
                if (z5 || stageCoordinator.mIsExiting || !stageTaskListener.mIsActive) {
                    if (z6 && z5 && !stageTaskListener.mIsActive) {
                        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                        stageCoordinator.prepareEnterSplitScreen(windowContainerTransaction);
                        SyncTransactionQueue syncTransactionQueue = stageCoordinator.mSyncQueue;
                        syncTransactionQueue.queue(windowContainerTransaction);
                        syncTransactionQueue.runInSync(new StageCoordinator$$ExternalSyntheticLambda3(3, stageCoordinator));
                    }
                } else if (z6 && stageListenerImpl2.mVisible) {
                    stageCoordinator.mSplitLayout.flingDividerToDismiss(2, stageCoordinator.mSideStagePosition == 1);
                } else if (!z6 && stageListenerImpl3.mVisible) {
                    stageCoordinator.mSplitLayout.flingDividerToDismiss(2, stageCoordinator.mSideStagePosition != 1);
                } else if (!stageCoordinator.isSplitScreenVisible() && stageCoordinator.mSplitRequest == null) {
                    stageCoordinator.exitSplitScreen(null, 2);
                }
                if (stageListenerImpl2.mHasChildren && stageListenerImpl3.mHasChildren) {
                    stageCoordinator.mShouldUpdateRecents = true;
                    stageCoordinator.clearRequestIfPresented();
                    stageCoordinator.updateRecentTasksSplitPair();
                    SplitscreenEventLogger splitscreenEventLogger = stageCoordinator.mLogger;
                    if (splitscreenEventLogger.mLoggerSessionId == null && splitscreenEventLogger.mEnterSessionId == null) {
                        splitscreenEventLogger.mEnterSessionId = null;
                        splitscreenEventLogger.mEnterReason = 1;
                    }
                    stageCoordinator.mLogger.logEnter(stageCoordinator.mSplitLayout.getDividerPositionAsFraction(), SplitScreenUtils.reverseSplitPosition(stageCoordinator.mSideStagePosition), stageTaskListener.getTopChildTaskUid(), stageCoordinator.mSideStagePosition, stageCoordinator.mSideStage.getTopChildTaskUid(), stageCoordinator.mSplitLayout.mIsLeftRightSplit);
                }
            }
            if (stageListenerImpl.mVisible != z) {
                stageListenerImpl.mVisible = z;
                if (stageCoordinator.mMainStage.mIsActive) {
                    boolean z7 = stageCoordinator.mSideStageListener.mVisible;
                    boolean z8 = stageCoordinator.mMainStageListener.mVisible;
                    if (z8 != z7) {
                        return;
                    }
                    if (!z8 && stageCoordinator.mExitSplitScreenOnHide) {
                        stageCoordinator.exitSplitScreen(null, 5);
                        return;
                    }
                    WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
                    if (z8) {
                        stageCoordinator.clearRequestIfPresented();
                        windowContainerTransaction2.setReparentLeafTaskIfRelaunch(stageCoordinator.mRootTaskInfo.token, false);
                        stageCoordinator.setRootForceTranslucent(windowContainerTransaction2, false);
                    } else {
                        windowContainerTransaction2.setReparentLeafTaskIfRelaunch(stageCoordinator.mRootTaskInfo.token, true);
                        stageCoordinator.setRootForceTranslucent(windowContainerTransaction2, true);
                    }
                    stageCoordinator.mSyncQueue.queue(windowContainerTransaction2);
                    stageCoordinator.setDividerVisibility(null, z8);
                }
            }
        }
    }
}

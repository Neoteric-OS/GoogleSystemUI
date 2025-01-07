package com.android.wm.shell.splitscreen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.TaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.graphics.Rect;
import android.hardware.devicestate.DeviceStateManager;
import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.IntArray;
import android.util.Log;
import android.util.Slog;
import android.view.Choreographer;
import android.view.IRemoteAnimationFinishedCallback;
import android.view.IRemoteAnimationRunner;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.widget.Toast;
import android.window.DisplayAreaInfo;
import android.window.RemoteTransition;
import android.window.TransitionInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import com.android.internal.logging.InstanceId;
import com.android.internal.policy.FoldLockSettingsObserver;
import com.android.internal.protolog.ProtoLog;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.internal.util.FrameworkStatsLog;
import com.android.keyguard.KeyguardClockSwitchController$$ExternalSyntheticOutline0;
import com.android.launcher3.icons.IconProvider;
import com.android.systemui.animation.RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0;
import com.android.systemui.wmshell.WMShell;
import com.android.wm.shell.R;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.back.BackAnimationController$$ExternalSyntheticOutline0;
import com.android.wm.shell.common.DisplayChangeController;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayImeController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.LaunchAdjacentController;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.common.split.DividerView;
import com.android.wm.shell.common.split.SplitDecorManager;
import com.android.wm.shell.common.split.SplitLayout;
import com.android.wm.shell.common.split.SplitScreenUtils;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.recents.RecentTasksController;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.shared.split.SplitScreenConstants;
import com.android.wm.shell.splitscreen.SplitScreen;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.splitscreen.SplitScreenTransitions;
import com.android.wm.shell.transition.DefaultMixedHandler;
import com.android.wm.shell.transition.OneShotRemoteHandler;
import com.android.wm.shell.transition.Transitions;
import dalvik.annotation.optimization.NeverCompile;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class StageCoordinator implements DisplayController.OnDisplaysChangedListener, Transitions.TransitionHandler, ShellTaskOrganizer.TaskListener {
    public boolean mBreakOnNextWake;
    public final Context mContext;
    public final DisplayController mDisplayController;
    public final int mDisplayId;
    public final DisplayImeController mDisplayImeController;
    public final DisplayInsetsController mDisplayInsetsController;
    public ValueAnimator mDividerFadeInAnimator;
    public boolean mDividerVisible;
    public boolean mExitSplitScreenOnHide;
    public final FoldLockSettingsObserver mFoldLockSettingsObserver;
    public boolean mIsDropEntering;
    public boolean mIsExiting;
    public boolean mIsRootTranslucent;
    public boolean mKeyguardActive;
    int mLastActiveStage;
    public final LaunchAdjacentController mLaunchAdjacentController;
    public final List mListeners;
    public final SplitscreenEventLogger mLogger;
    public final ShellExecutor mMainExecutor;
    public final Handler mMainHandler;
    public final StageTaskListener mMainStage;
    public final StageListenerImpl mMainStageListener;
    public DefaultMixedHandler mMixedHandler;
    public final AnonymousClass1 mParentContainerCallbacks;
    public final ArrayList mPausingTasks;
    public final Optional mRecentTasks;
    ActivityManager.RunningTaskInfo mRootTaskInfo;
    public SurfaceControl mRootTaskLeash;
    public final Set mSelectListeners;
    public boolean mShouldUpdateRecents;
    public boolean mShowDecorImmediately;
    public final StageTaskListener mSideStage;
    public final StageListenerImpl mSideStageListener;
    public int mSideStagePosition;
    public boolean mSkipEvictingMainStageChildren;
    public WMShell.AnonymousClass9 mSplitInvocationListener;
    public Executor mSplitInvocationListenerExecutor;
    public SplitLayout mSplitLayout;
    public SplitRequest mSplitRequest;
    public SplitScreenTransitions mSplitTransitions;
    public final Toast mSplitUnsupportedToast;
    public final SyncTransactionQueue mSyncQueue;
    public final ShellTaskOrganizer mTaskOrganizer;
    public final Rect mTempRect1;
    public final Rect mTempRect2;
    public final TransactionPool mTransactionPool;
    public final Optional mWindowDecorViewModel;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.splitscreen.StageCoordinator$1, reason: invalid class name */
    public final class AnonymousClass1 {
        public AnonymousClass1() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.splitscreen.StageCoordinator$2, reason: invalid class name */
    public final class AnonymousClass2 extends IRemoteAnimationRunner.Stub {
        public final /* synthetic */ boolean val$isEnteringSplit;
        public final /* synthetic */ int val$position;

        public AnonymousClass2(boolean z, int i) {
            this.val$isEnteringSplit = z;
            this.val$position = i;
        }

        public final void onAnimationCancelled() {
            if (this.val$isEnteringSplit) {
                ((HandlerExecutor) StageCoordinator.this.mMainExecutor).execute(new StageCoordinator$2$$ExternalSyntheticLambda0(this, 0));
            }
        }

        public final void onAnimationStart(int i, RemoteAnimationTarget[] remoteAnimationTargetArr, RemoteAnimationTarget[] remoteAnimationTargetArr2, RemoteAnimationTarget[] remoteAnimationTargetArr3, IRemoteAnimationFinishedCallback iRemoteAnimationFinishedCallback) {
            if (this.val$isEnteringSplit && StageCoordinator.this.mSideStage.mChildrenTaskInfo.size() == 0) {
                ((HandlerExecutor) StageCoordinator.this.mMainExecutor).execute(new StageCoordinator$2$$ExternalSyntheticLambda0(this, 1));
                Log.w("StageCoordinator", SplitScreenUtils.splitFailureMessage("startShortcut", "side stage was not populated"));
                StageCoordinator stageCoordinator = StageCoordinator.this;
                stageCoordinator.mSplitUnsupportedToast.show();
                stageCoordinator.notifySplitAnimationFinished();
            }
            if (iRemoteAnimationFinishedCallback != null) {
                try {
                    iRemoteAnimationFinishedCallback.onAnimationFinished();
                } catch (RemoteException e) {
                    Slog.e("StageCoordinator", "Error finishing legacy transition: ", e);
                }
            }
            if (this.val$isEnteringSplit || remoteAnimationTargetArr == null) {
                return;
            }
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            StageCoordinator stageCoordinator2 = StageCoordinator.this;
            if (this.val$position == stageCoordinator2.mSideStagePosition) {
                stageCoordinator2.mSideStage.evictNonOpeningChildren(remoteAnimationTargetArr, windowContainerTransaction);
            } else {
                stageCoordinator2.mMainStage.evictNonOpeningChildren(remoteAnimationTargetArr, windowContainerTransaction);
            }
            StageCoordinator.this.mSyncQueue.queue(windowContainerTransaction);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SplitRequest {
        public int mActivatePosition;
        public int mActivateTaskId;
        public Intent mStartIntent;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StageChangeRecord {
        public boolean mContainShowFullscreenChange = false;
        public final ArrayMap mChanges = new ArrayMap();

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class StageChange {
            public final IntArray mAddedTaskId = new IntArray();
            public final IntArray mRemovedTaskId = new IntArray();
            public final StageTaskListener mStageTaskListener;

            public StageChange(StageTaskListener stageTaskListener) {
                this.mStageTaskListener = stageTaskListener;
            }
        }

        public final void addRecord(StageTaskListener stageTaskListener, boolean z, int i) {
            StageChange stageChange;
            if (this.mChanges.containsKey(stageTaskListener)) {
                stageChange = (StageChange) this.mChanges.get(stageTaskListener);
            } else {
                stageChange = new StageChange(stageTaskListener);
                this.mChanges.put(stageTaskListener, stageChange);
            }
            if (z) {
                stageChange.mAddedTaskId.add(i);
            } else {
                stageChange.mRemovedTaskId.add(i);
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StageListenerImpl {
        public boolean mHasRootTask = false;
        public boolean mVisible = false;
        public boolean mHasChildren = false;

        public StageListenerImpl() {
        }

        public final void dump(PrintWriter printWriter, String str) {
            KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, "mHasRootTask="), this.mHasRootTask, printWriter, str, "mVisible="), this.mVisible, printWriter, str, "mHasChildren="), this.mHasChildren, printWriter);
        }

        public final void onChildTaskStatusChanged(int i, boolean z, boolean z2) {
            StageCoordinator stageCoordinator = StageCoordinator.this;
            int i2 = z ? this == stageCoordinator.mSideStageListener ? 1 : 0 : -1;
            SplitscreenEventLogger splitscreenEventLogger = stageCoordinator.mLogger;
            if (i2 == 0) {
                int reverseSplitPosition = SplitScreenUtils.reverseSplitPosition(stageCoordinator.mSideStagePosition);
                int topChildTaskUid = stageCoordinator.mMainStage.getTopChildTaskUid();
                boolean z3 = stageCoordinator.mSplitLayout.mIsLeftRightSplit;
                if (splitscreenEventLogger.mLoggerSessionId != null && splitscreenEventLogger.updateMainStageState(SplitscreenEventLogger.getMainStagePositionFromSplitPosition(reverseSplitPosition, z3), topChildTaskUid)) {
                    FrameworkStatsLog.write(388, 3, 0, 0, 0.0f, splitscreenEventLogger.mLastMainStagePosition, splitscreenEventLogger.mLastMainStageUid, 0, 0, 0, splitscreenEventLogger.mLoggerSessionId.getId());
                }
            } else if (i2 == 1) {
                int i3 = stageCoordinator.mSideStagePosition;
                int topChildTaskUid2 = stageCoordinator.mSideStage.getTopChildTaskUid();
                boolean z4 = stageCoordinator.mSplitLayout.mIsLeftRightSplit;
                if (splitscreenEventLogger.mLoggerSessionId != null && splitscreenEventLogger.updateSideStageState(SplitscreenEventLogger.getSideStagePositionFromSplitPosition(i3, z4), topChildTaskUid2)) {
                    FrameworkStatsLog.write(388, 3, 0, 0, 0.0f, 0, 0, splitscreenEventLogger.mLastSideStagePosition, splitscreenEventLogger.mLastSideStageUid, 0, splitscreenEventLogger.mLoggerSessionId.getId());
                }
            }
            if (z) {
                stageCoordinator.updateRecentTasksSplitPair();
            }
            for (int size = ((ArrayList) stageCoordinator.mListeners).size() - 1; size >= 0; size--) {
                ((SplitScreen.SplitScreenListener) ((ArrayList) stageCoordinator.mListeners).get(size)).onTaskStageChanged(i, i2, z2);
            }
        }
    }

    public StageCoordinator(Context context, SyncTransactionQueue syncTransactionQueue, ShellTaskOrganizer shellTaskOrganizer, DisplayController displayController, DisplayImeController displayImeController, DisplayInsetsController displayInsetsController, Transitions transitions, TransactionPool transactionPool, IconProvider iconProvider, ShellExecutor shellExecutor, Handler handler, Optional optional, LaunchAdjacentController launchAdjacentController, Optional optional2) {
        StageListenerImpl stageListenerImpl = new StageListenerImpl();
        this.mMainStageListener = stageListenerImpl;
        StageListenerImpl stageListenerImpl2 = new StageListenerImpl();
        this.mSideStageListener = stageListenerImpl2;
        this.mSideStagePosition = 1;
        this.mListeners = new ArrayList();
        this.mSelectListeners = new HashSet();
        this.mPausingTasks = new ArrayList();
        this.mTempRect1 = new Rect();
        this.mTempRect2 = new Rect();
        this.mShouldUpdateRecents = true;
        this.mParentContainerCallbacks = new AnonymousClass1();
        this.mContext = context;
        this.mDisplayId = 0;
        this.mSyncQueue = syncTransactionQueue;
        this.mTaskOrganizer = shellTaskOrganizer;
        this.mLogger = new SplitscreenEventLogger();
        this.mMainExecutor = shellExecutor;
        this.mMainHandler = handler;
        this.mRecentTasks = optional;
        this.mLaunchAdjacentController = launchAdjacentController;
        this.mWindowDecorViewModel = optional2;
        shellTaskOrganizer.createRootTask(1, this);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 1564738155237815538L, 0, null);
        }
        this.mMainStage = new StageTaskListener(context, shellTaskOrganizer, stageListenerImpl, syncTransactionQueue, iconProvider, optional2);
        this.mSideStage = new StageTaskListener(context, shellTaskOrganizer, stageListenerImpl2, syncTransactionQueue, iconProvider, optional2);
        this.mDisplayController = displayController;
        this.mDisplayImeController = displayImeController;
        this.mDisplayInsetsController = displayInsetsController;
        this.mTransactionPool = transactionPool;
        ((DeviceStateManager) context.getSystemService(DeviceStateManager.class)).registerCallback(shellTaskOrganizer.getExecutor(), new DeviceStateManager.FoldStateListener(context, new StageCoordinator$$ExternalSyntheticLambda5(this, 1)));
        this.mSplitTransitions = new SplitScreenTransitions(transactionPool, transitions, new StageCoordinator$$ExternalSyntheticLambda1(this, 2), this);
        displayController.addDisplayWindowListener(this);
        transitions.addHandler(this);
        this.mSplitUnsupportedToast = Toast.makeText(context, R.string.dock_non_resizeble_failed_to_dock_text, 0);
        FoldLockSettingsObserver foldLockSettingsObserver = new FoldLockSettingsObserver(handler, context);
        this.mFoldLockSettingsObserver = foldLockSettingsObserver;
        foldLockSettingsObserver.register();
    }

    public static void addActivityOptions(Bundle bundle, StageTaskListener stageTaskListener) {
        ActivityOptions fromBundle = ActivityOptions.fromBundle(bundle);
        if (stageTaskListener != null) {
            fromBundle.setLaunchRootTask(stageTaskListener.mRootTaskInfo.token);
        }
        fromBundle.setPendingIntentBackgroundActivityStartMode(3);
        fromBundle.setDisallowEnterPictureInPictureWhileLaunching(true);
        bundle.putAll(fromBundle.toBundle());
    }

    public static boolean shouldBreakPairedTaskInRecents(int i) {
        return i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 8 || i == 9 || i == 11 || i == 12;
    }

    public final void activateSplit(WindowContainerTransaction windowContainerTransaction, boolean z) {
        StageTaskListener stageTaskListener = this.mMainStage;
        if (stageTaskListener.mIsActive) {
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 4254554668131694525L, 3, Boolean.valueOf(z));
        }
        if (z) {
            windowContainerTransaction.reparentTasks((WindowContainerToken) null, stageTaskListener.mRootTaskInfo.token, SplitScreenConstants.CONTROLLED_WINDOWING_MODES, SplitScreenConstants.CONTROLLED_ACTIVITY_TYPES, true, true);
        }
        stageTaskListener.mIsActive = true;
    }

    public final void addDividerBarToTransition(TransitionInfo transitionInfo, boolean z) {
        SurfaceControl dividerLeash = this.mSplitLayout.getDividerLeash();
        if (dividerLeash == null || !dividerLeash.isValid()) {
            Slog.w("StageCoordinator", "addDividerBarToTransition but leash was released or not be created");
            return;
        }
        TransitionInfo.Change change = new TransitionInfo.Change((WindowContainerToken) null, dividerLeash);
        this.mSplitLayout.getRefDividerBounds(this.mTempRect1);
        change.setParent(this.mRootTaskInfo.token);
        change.setStartAbsBounds(this.mTempRect1);
        change.setEndAbsBounds(this.mTempRect1);
        change.setMode(z ? 3 : 4);
        change.setFlags(8388608);
        transitionInfo.addChange(change);
    }

    public final void applyDividerVisibility(SurfaceControl.Transaction transaction) {
        final SurfaceControl dividerLeash = this.mSplitLayout.getDividerLeash();
        if (dividerLeash == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -2891277396196116082L, 0, null);
                return;
            }
            return;
        }
        ValueAnimator valueAnimator = this.mDividerFadeInAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.mDividerFadeInAnimator.cancel();
        }
        this.mSplitLayout.getRefDividerBounds(this.mTempRect1);
        if (transaction != null) {
            transaction.setVisibility(dividerLeash, this.mDividerVisible);
            transaction.setLayer(dividerLeash, Integer.MAX_VALUE);
            Rect rect = this.mTempRect1;
            transaction.setPosition(dividerLeash, rect.left, rect.top);
            return;
        }
        boolean z = this.mDividerVisible;
        TransactionPool transactionPool = this.mTransactionPool;
        if (!z) {
            SurfaceControl.Transaction acquire = transactionPool.acquire();
            acquire.hide(dividerLeash);
            acquire.apply();
            transactionPool.release(acquire);
            return;
        }
        final SurfaceControl.Transaction acquire2 = transactionPool.acquire();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mDividerFadeInAnimator = ofFloat;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.splitscreen.StageCoordinator$$ExternalSyntheticLambda9
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                StageCoordinator stageCoordinator = StageCoordinator.this;
                SurfaceControl surfaceControl = dividerLeash;
                SurfaceControl.Transaction transaction2 = acquire2;
                if (surfaceControl != null) {
                    stageCoordinator.getClass();
                    if (surfaceControl.isValid()) {
                        transaction2.setFrameTimelineVsync(Choreographer.getInstance().getVsyncId());
                        transaction2.setAlpha(surfaceControl, ((Float) valueAnimator2.getAnimatedValue()).floatValue());
                        transaction2.apply();
                        return;
                    }
                }
                stageCoordinator.mDividerFadeInAnimator.cancel();
            }
        });
        this.mDividerFadeInAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.splitscreen.StageCoordinator.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                SurfaceControl surfaceControl = dividerLeash;
                if (surfaceControl != null && surfaceControl.isValid()) {
                    acquire2.setAlpha(dividerLeash, 1.0f);
                    acquire2.apply();
                }
                StageCoordinator.this.mTransactionPool.release(acquire2);
                StageCoordinator.this.mDividerFadeInAnimator = null;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                SurfaceControl surfaceControl = dividerLeash;
                if (surfaceControl == null || !surfaceControl.isValid()) {
                    StageCoordinator.this.mDividerFadeInAnimator.cancel();
                    return;
                }
                StageCoordinator stageCoordinator = StageCoordinator.this;
                stageCoordinator.mSplitLayout.getRefDividerBounds(stageCoordinator.mTempRect1);
                acquire2.show(dividerLeash);
                acquire2.setAlpha(dividerLeash, 0.0f);
                acquire2.setLayer(dividerLeash, Integer.MAX_VALUE);
                SurfaceControl.Transaction transaction2 = acquire2;
                SurfaceControl surfaceControl2 = dividerLeash;
                Rect rect2 = StageCoordinator.this.mTempRect1;
                transaction2.setPosition(surfaceControl2, rect2.left, rect2.top);
                acquire2.apply();
            }
        });
        this.mDividerFadeInAnimator.start();
    }

    public final void applyExitSplitScreen(final StageTaskListener stageTaskListener, WindowContainerTransaction windowContainerTransaction, int i) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -8832527832122182701L, 0, String.valueOf(SplitScreenController.exitReasonToString(i)));
        }
        StageTaskListener stageTaskListener2 = this.mMainStage;
        if (!stageTaskListener2.mIsActive || this.mIsExiting) {
            return;
        }
        clearSplitPairedInRecents(i);
        this.mShouldUpdateRecents = false;
        this.mSplitRequest = null;
        this.mTempRect1.set(this.mSplitLayout.mInvisibleBounds);
        if (stageTaskListener == null || stageTaskListener.getTopVisibleChildTaskId() == -1) {
            StageTaskListener stageTaskListener3 = this.mSideStage;
            stageTaskListener3.removeAllTasks(windowContainerTransaction, false);
            deactivateSplit(windowContainerTransaction, false);
            windowContainerTransaction.reorder(this.mRootTaskInfo.token, false);
            setRootForceTranslucent(windowContainerTransaction, true);
            windowContainerTransaction.setBounds(stageTaskListener3.mRootTaskInfo.token, this.mTempRect1);
            onTransitionAnimationComplete();
        } else {
            this.mIsExiting = true;
            windowContainerTransaction.setBounds(stageTaskListener.mRootTaskInfo.token, (Rect) null);
            windowContainerTransaction.setAppBounds(stageTaskListener.mRootTaskInfo.token, (Rect) null);
            windowContainerTransaction.setSmallestScreenWidthDp(stageTaskListener.mRootTaskInfo.token, 0);
            windowContainerTransaction.reorder(stageTaskListener.mRootTaskInfo.token, true);
        }
        windowContainerTransaction.setReparentLeafTaskIfRelaunch(this.mRootTaskInfo.token, false);
        SyncTransactionQueue syncTransactionQueue = this.mSyncQueue;
        syncTransactionQueue.queue(windowContainerTransaction);
        syncTransactionQueue.runInSync(new SyncTransactionQueue.TransactionRunnable() { // from class: com.android.wm.shell.splitscreen.StageCoordinator$$ExternalSyntheticLambda7
            @Override // com.android.wm.shell.common.SyncTransactionQueue.TransactionRunnable
            public final void runWithTransaction(SurfaceControl.Transaction transaction) {
                final StageCoordinator stageCoordinator = StageCoordinator.this;
                StageTaskListener stageTaskListener4 = stageCoordinator.mMainStage;
                SurfaceControl.Transaction windowCrop = transaction.setWindowCrop(stageTaskListener4.mRootLeash, null);
                StageTaskListener stageTaskListener5 = stageCoordinator.mSideStage;
                windowCrop.setWindowCrop(stageTaskListener5.mRootLeash, null);
                transaction.hide(stageTaskListener4.mDimLayer).hide(stageTaskListener5.mDimLayer);
                stageCoordinator.setDividerVisibility(transaction, false);
                final StageTaskListener stageTaskListener6 = stageTaskListener;
                if (stageTaskListener6 == null) {
                    SurfaceControl surfaceControl = stageTaskListener5.mRootLeash;
                    Rect rect = stageCoordinator.mTempRect1;
                    transaction.setPosition(surfaceControl, rect.left, rect.right);
                } else {
                    Runnable runnable = new Runnable() { // from class: com.android.wm.shell.splitscreen.StageCoordinator$$ExternalSyntheticLambda14
                        @Override // java.lang.Runnable
                        public final void run() {
                            StageCoordinator stageCoordinator2 = StageCoordinator.this;
                            StageTaskListener stageTaskListener7 = stageTaskListener6;
                            stageCoordinator2.getClass();
                            WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
                            stageCoordinator2.mIsExiting = false;
                            stageCoordinator2.deactivateSplit(windowContainerTransaction2, stageTaskListener7 == stageCoordinator2.mMainStage);
                            StageTaskListener stageTaskListener8 = stageCoordinator2.mSideStage;
                            stageTaskListener8.removeAllTasks(windowContainerTransaction2, stageTaskListener7 == stageTaskListener8);
                            windowContainerTransaction2.reorder(stageCoordinator2.mRootTaskInfo.token, false);
                            stageCoordinator2.setRootForceTranslucent(windowContainerTransaction2, true);
                            windowContainerTransaction2.setBounds(stageTaskListener8.mRootTaskInfo.token, stageCoordinator2.mTempRect1);
                            SyncTransactionQueue syncTransactionQueue2 = stageCoordinator2.mSyncQueue;
                            syncTransactionQueue2.queue(windowContainerTransaction2);
                            syncTransactionQueue2.runInSync(new StageCoordinator$$ExternalSyntheticLambda3(1, stageCoordinator2));
                            stageCoordinator2.onTransitionAnimationComplete();
                        }
                    };
                    SplitDecorManager splitDecorManager = stageTaskListener6.mSplitDecorManager;
                    if (splitDecorManager != null) {
                        splitDecorManager.fadeOutDecor(runnable, false);
                    } else {
                        runnable.run();
                    }
                }
            }
        });
        if (stageTaskListener != null) {
            logExitToStage(i, stageTaskListener == stageTaskListener2);
        } else {
            logExit(i);
        }
    }

    public final void clearRequestIfPresented() {
        boolean z;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 4039662621566186722L, 0, null);
        }
        StageListenerImpl stageListenerImpl = this.mSideStageListener;
        if (stageListenerImpl.mVisible && (z = stageListenerImpl.mHasChildren) && this.mMainStageListener.mVisible && z) {
            this.mSplitRequest = null;
        }
    }

    public final void clearSplitPairedInRecents(int i) {
        if (shouldBreakPairedTaskInRecents(i) && this.mShouldUpdateRecents) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 6140256617638804326L, 0, String.valueOf(SplitScreenController.exitReasonToString(i)));
            }
            this.mRecentTasks.ifPresent(new StageCoordinator$$ExternalSyntheticLambda5(this, 0));
            logExit(i);
        }
    }

    public final void deactivateSplit(WindowContainerTransaction windowContainerTransaction, boolean z) {
        StageTaskListener stageTaskListener = this.mMainStage;
        if (stageTaskListener.mIsActive) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -3870597107973605968L, 3, Boolean.valueOf(z), String.valueOf(stageTaskListener.mRootTaskInfo));
            }
            stageTaskListener.mIsActive = false;
            ActivityManager.RunningTaskInfo runningTaskInfo = stageTaskListener.mRootTaskInfo;
            if (runningTaskInfo == null) {
                return;
            }
            windowContainerTransaction.reparentTasks(runningTaskInfo.token, (WindowContainerToken) null, (int[]) null, (int[]) null, z);
        }
    }

    public final void dismissSplitKeepingLastActiveStage(int i) {
        if (!this.mMainStage.mIsActive || this.mLastActiveStage == -1) {
            return;
        }
        clearSplitPairedInRecents(i);
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        prepareExitSplitScreen(this.mLastActiveStage, windowContainerTransaction);
        this.mSplitTransitions.startDismissTransition(windowContainerTransaction, this, this.mLastActiveStage, i);
        setSplitsVisible(false);
        this.mBreakOnNextWake = false;
        logExit(i);
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    @NeverCompile
    public final void dump$1(PrintWriter printWriter, String str) {
        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "  ");
        String m2 = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(m, "  ");
        StringBuilder m3 = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str, "StageCoordinator mDisplayId=");
        m3.append(this.mDisplayId);
        printWriter.println(m3.toString());
        StringBuilder sb = new StringBuilder();
        sb.append(m);
        sb.append("mDividerVisible=");
        StringBuilder m4 = BackAnimationController$$ExternalSyntheticOutline0.m(sb, this.mDividerVisible, printWriter, m, "isSplitActive=");
        StageTaskListener stageTaskListener = this.mMainStage;
        StringBuilder m5 = BackAnimationController$$ExternalSyntheticOutline0.m(m4, stageTaskListener.mIsActive, printWriter, m, "isSplitVisible=");
        m5.append(isSplitScreenVisible());
        printWriter.println(m5.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(m);
        sb2.append("isLeftRightSplit=");
        SplitLayout splitLayout = this.mSplitLayout;
        sb2.append(splitLayout != null ? Boolean.valueOf(splitLayout.mIsLeftRightSplit) : "null");
        printWriter.println(sb2.toString());
        printWriter.println(m + "MainStage");
        StringBuilder sb3 = new StringBuilder();
        sb3.append(m2);
        sb3.append("stagePosition=");
        int reverseSplitPosition = SplitScreenUtils.reverseSplitPosition(this.mSideStagePosition);
        sb3.append(reverseSplitPosition != -1 ? reverseSplitPosition != 0 ? reverseSplitPosition != 1 ? "UNKNOWN" : "SPLIT_POSITION_BOTTOM_OR_RIGHT" : "SPLIT_POSITION_TOP_OR_LEFT" : "SPLIT_POSITION_UNDEFINED");
        printWriter.println(sb3.toString());
        StringBuilder sb4 = new StringBuilder();
        sb4.append(m2);
        sb4.append("isActive=");
        KeyguardClockSwitchController$$ExternalSyntheticOutline0.m(sb4, stageTaskListener.mIsActive, printWriter);
        stageTaskListener.dump$1(printWriter, m2);
        printWriter.println(m + "MainStageListener");
        this.mMainStageListener.dump(printWriter, m2);
        printWriter.println(m + "SideStage");
        StringBuilder sb5 = new StringBuilder();
        sb5.append(m2);
        sb5.append("stagePosition=");
        int i = this.mSideStagePosition;
        sb5.append(i != -1 ? i != 0 ? i != 1 ? "UNKNOWN" : "SPLIT_POSITION_BOTTOM_OR_RIGHT" : "SPLIT_POSITION_TOP_OR_LEFT" : "SPLIT_POSITION_UNDEFINED");
        printWriter.println(sb5.toString());
        this.mSideStage.dump$1(printWriter, m2);
        printWriter.println(m + "SideStageListener");
        this.mSideStageListener.dump(printWriter, m2);
        SplitLayout splitLayout2 = this.mSplitLayout;
        if (splitLayout2 != null) {
            String m6 = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(m2, "\t");
            printWriter.println(m2 + "SplitLayout:");
            StringBuilder sb6 = new StringBuilder();
            sb6.append(m6);
            sb6.append("mAllowLeftRightSplitInPortrait=");
            StringBuilder m7 = BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(BackAnimationController$$ExternalSyntheticOutline0.m(sb6, splitLayout2.mAllowLeftRightSplitInPortrait, printWriter, m6, "mIsLeftRightSplit="), splitLayout2.mIsLeftRightSplit, printWriter, m6, "mFreezeDividerWindow="), splitLayout2.mFreezeDividerWindow, printWriter, m6, "mDimNonImeSide="), splitLayout2.mDimNonImeSide, printWriter, m6, "mDividerPosition=");
            m7.append(splitLayout2.mDividerPosition);
            printWriter.println(m7.toString());
            printWriter.println(m6 + "bounds1=" + splitLayout2.mBounds1.toShortString());
            printWriter.println(m6 + "dividerBounds=" + splitLayout2.mDividerBounds.toShortString());
            printWriter.println(m6 + "bounds2=" + splitLayout2.mBounds2.toShortString());
        }
        if (this.mPausingTasks.isEmpty()) {
            return;
        }
        StringBuilder m8 = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(m2, "mPausingTasks=");
        m8.append(this.mPausingTasks);
        printWriter.println(m8.toString());
    }

    public final void exitSplitScreen(StageTaskListener stageTaskListener, int i) {
        boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0];
        StageTaskListener stageTaskListener2 = this.mMainStage;
        if (z) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 153037047750734517L, 51, Boolean.valueOf(stageTaskListener == stageTaskListener2), String.valueOf(SplitScreenController.exitReasonToString(i)), Boolean.valueOf(stageTaskListener2.mIsActive));
        }
        if (stageTaskListener2.mIsActive) {
            applyExitSplitScreen(stageTaskListener, new WindowContainerTransaction(), i);
        }
    }

    public final void finishEnterSplitScreen(SurfaceControl.Transaction transaction) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -3613640443892843881L, 0, null);
        }
        this.mSplitLayout.update(null, true);
        StageTaskListener stageTaskListener = this.mMainStage;
        stageTaskListener.mSplitDecorManager.inflate(this.mContext, stageTaskListener.mRootLeash);
        StageTaskListener stageTaskListener2 = this.mSideStage;
        stageTaskListener2.mSplitDecorManager.inflate(this.mContext, stageTaskListener2.mRootLeash);
        setDividerVisibility(transaction, true);
        transaction.reparent(this.mSplitLayout.getDividerLeash(), this.mRootTaskLeash);
        updateSurfaceBounds(this.mSplitLayout, transaction, false);
        transaction.show(this.mRootTaskLeash);
        setSplitsVisible(true);
        this.mIsDropEntering = false;
        this.mSkipEvictingMainStageChildren = false;
        this.mSplitRequest = null;
        updateRecentTasksSplitPair();
        this.mLogger.logEnter(this.mSplitLayout.getDividerPositionAsFraction(), SplitScreenUtils.reverseSplitPosition(this.mSideStagePosition), stageTaskListener.getTopChildTaskUid(), this.mSideStagePosition, stageTaskListener2.getTopChildTaskUid(), this.mSplitLayout.mIsLeftRightSplit);
    }

    public final int getActivateSplitPosition(TaskInfo taskInfo) {
        SplitRequest splitRequest = this.mSplitRequest;
        if (splitRequest != null && taskInfo != null) {
            if (splitRequest.mActivateTaskId != 0) {
                splitRequest.getClass();
                if (taskInfo.taskId == 0) {
                    return this.mSplitRequest.mActivatePosition;
                }
            }
            SplitRequest splitRequest2 = this.mSplitRequest;
            if (splitRequest2.mActivateTaskId == taskInfo.taskId) {
                return splitRequest2.mActivatePosition;
            }
            String packageName = SplitScreenUtils.getPackageName(splitRequest2.mStartIntent);
            String packageName2 = SplitScreenUtils.getPackageName(taskInfo.baseIntent);
            if (packageName != null && packageName.equals(packageName2)) {
                return this.mSplitRequest.mActivatePosition;
            }
            this.mSplitRequest.getClass();
        }
        return -1;
    }

    public final int getSplitItemPosition(WindowContainerToken windowContainerToken) {
        if (windowContainerToken == null) {
            return -1;
        }
        StageTaskListener stageTaskListener = this.mMainStage;
        stageTaskListener.getClass();
        if (stageTaskListener.contains(new StageTaskListener$$ExternalSyntheticLambda0(1, windowContainerToken))) {
            return SplitScreenUtils.reverseSplitPosition(this.mSideStagePosition);
        }
        StageTaskListener stageTaskListener2 = this.mSideStage;
        stageTaskListener2.getClass();
        if (stageTaskListener2.contains(new StageTaskListener$$ExternalSyntheticLambda0(1, windowContainerToken))) {
            return this.mSideStagePosition;
        }
        return -1;
    }

    public final int getSplitPosition(int i) {
        if (this.mSideStage.getTopVisibleChildTaskId() == i) {
            return this.mSideStagePosition;
        }
        if (this.mMainStage.getTopVisibleChildTaskId() == i) {
            return SplitScreenUtils.reverseSplitPosition(this.mSideStagePosition);
        }
        return -1;
    }

    public SplitScreenTransitions getSplitTransitions() {
        return this.mSplitTransitions;
    }

    public final int getStageOfTask(int i) {
        if (this.mMainStage.mChildrenTaskInfo.contains(i)) {
            return 0;
        }
        return this.mSideStage.mChildrenTaskInfo.contains(i) ? 1 : -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:106:0x01dd, code lost:
    
        if (r8.mChildrenTaskInfo.size() == 1) goto L87;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.window.WindowContainerTransaction handleRequest(android.os.IBinder r18, android.window.TransitionRequestInfo r19) {
        /*
            Method dump skipped, instructions count: 683
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.splitscreen.StageCoordinator.handleRequest(android.os.IBinder, android.window.TransitionRequestInfo):android.window.WindowContainerTransaction");
    }

    public final boolean isSplitScreenVisible() {
        return this.mSideStageListener.mVisible && this.mMainStageListener.mVisible;
    }

    public final void logExit(int i) {
        this.mLogger.logExit(i, -1, 0, -1, 0, this.mSplitLayout.mIsLeftRightSplit);
    }

    public final void logExitToStage(int i, boolean z) {
        this.mLogger.logExit(i, z ? SplitScreenUtils.reverseSplitPosition(this.mSideStagePosition) : -1, z ? this.mMainStage.getTopChildTaskUid() : 0, z ? -1 : this.mSideStagePosition, !z ? this.mSideStage.getTopChildTaskUid() : 0, this.mSplitLayout.mIsLeftRightSplit);
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -7527927886439366740L, 1, Long.valueOf(transitionInfo.getDebugId()));
        }
        SplitScreenTransitions splitScreenTransitions = this.mSplitTransitions;
        if (iBinder2 != splitScreenTransitions.mAnimatingTransition) {
            return;
        }
        OneShotRemoteHandler oneShotRemoteHandler = splitScreenTransitions.mActiveRemoteHandler;
        if (oneShotRemoteHandler != null) {
            oneShotRemoteHandler.mergeAnimation(iBinder, transitionInfo, transaction, iBinder2, transitionFinishCallback);
            return;
        }
        for (int size = splitScreenTransitions.mAnimations.size() - 1; size >= 0; size--) {
            Animator animator = (Animator) splitScreenTransitions.mAnimations.get(size);
            ShellExecutor shellExecutor = splitScreenTransitions.mTransitions.mAnimExecutor;
            Objects.requireNonNull(animator);
            ((HandlerExecutor) shellExecutor).execute(new SplitScreenTransitions$$ExternalSyntheticLambda0(3, animator));
        }
    }

    public final void notifySplitAnimationFinished() {
        Executor executor;
        if (this.mSplitInvocationListener == null || (executor = this.mSplitInvocationListenerExecutor) == null) {
            return;
        }
        executor.execute(new StageCoordinator$$ExternalSyntheticLambda1(this, 1));
    }

    @Override // com.android.wm.shell.common.DisplayController.OnDisplaysChangedListener
    public final void onDisplayAdded(int i) {
        if (i != 0) {
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 4807260383806698559L, 1, Long.valueOf(i));
        }
        this.mDisplayController.addDisplayChangingController(new DisplayChangeController.OnDisplayChangingListener() { // from class: com.android.wm.shell.splitscreen.StageCoordinator$$ExternalSyntheticLambda6
            @Override // com.android.wm.shell.common.DisplayChangeController.OnDisplayChangingListener
            public final void onDisplayChange$1(int i2, int i3, int i4, DisplayAreaInfo displayAreaInfo, WindowContainerTransaction windowContainerTransaction) {
                StageCoordinator stageCoordinator = StageCoordinator.this;
                if (i2 != 0) {
                    stageCoordinator.getClass();
                    return;
                }
                if (stageCoordinator.mMainStage.mIsActive) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -6802425196666471292L, 21, Long.valueOf(i2), Long.valueOf(i3), Long.valueOf(i4), String.valueOf(displayAreaInfo != null ? displayAreaInfo.configuration : null));
                    }
                    stageCoordinator.mSplitLayout.rotateTo(i4);
                    if (displayAreaInfo != null) {
                        stageCoordinator.mSplitLayout.updateConfiguration(displayAreaInfo.configuration);
                    }
                    stageCoordinator.updateWindowBounds(stageCoordinator.mSplitLayout, windowContainerTransaction);
                    stageCoordinator.sendOnBoundsChanged();
                }
            }
        });
    }

    public void onFoldedStateChanged(boolean z) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -665186314396288994L, 3, Boolean.valueOf(z));
        }
        if (!z) {
            this.mBreakOnNextWake = false;
        } else {
            recordLastActiveStage();
            this.mBreakOnNextWake = willSleepOnFold();
        }
    }

    public final void onLayoutPositionChanging(SplitLayout splitLayout) {
        TransactionPool transactionPool = this.mTransactionPool;
        SurfaceControl.Transaction acquire = transactionPool.acquire();
        acquire.setFrameTimelineVsync(Choreographer.getInstance().getVsyncId());
        updateSurfaceBounds(splitLayout, acquire, false);
        acquire.apply();
        transactionPool.release(acquire);
    }

    public final void onLayoutSizeChanged(SplitLayout splitLayout) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 4982149234074334951L, 0, null);
        }
        this.mShowDecorImmediately = false;
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        boolean updateWindowBounds = updateWindowBounds(splitLayout, windowContainerTransaction);
        StageTaskListener stageTaskListener = this.mSideStage;
        StageTaskListener stageTaskListener2 = this.mMainStage;
        if (!updateWindowBounds) {
            TransactionPool transactionPool = this.mTransactionPool;
            SurfaceControl.Transaction acquire = transactionPool.acquire();
            SplitDecorManager splitDecorManager = stageTaskListener2.mSplitDecorManager;
            if (splitDecorManager != null) {
                splitDecorManager.onResized(acquire, null);
            }
            SplitDecorManager splitDecorManager2 = stageTaskListener.mSplitDecorManager;
            if (splitDecorManager2 != null) {
                splitDecorManager2.onResized(acquire, null);
            }
            transactionPool.release(acquire);
            return;
        }
        sendOnBoundsChanged();
        DividerView dividerView = this.mSplitLayout.mSplitWindowManager.mDividerView;
        if (dividerView != null) {
            dividerView.setInteractive("onSplitResizeStart", false, false);
        }
        SplitScreenTransitions splitScreenTransitions = this.mSplitTransitions;
        StageCoordinator$$ExternalSyntheticLambda11 stageCoordinator$$ExternalSyntheticLambda11 = new StageCoordinator$$ExternalSyntheticLambda11(this, 0);
        StageCoordinator$$ExternalSyntheticLambda11 stageCoordinator$$ExternalSyntheticLambda112 = new StageCoordinator$$ExternalSyntheticLambda11(this, 2);
        SplitDecorManager splitDecorManager3 = stageTaskListener2.mSplitDecorManager;
        SplitDecorManager splitDecorManager4 = stageTaskListener.mSplitDecorManager;
        splitScreenTransitions.getClass();
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 4324715850741661479L, 0, null);
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 8318624295462056497L, 3, Boolean.valueOf(splitScreenTransitions.mPendingResize != null));
        }
        SplitScreenTransitions.TransitSession transitSession = splitScreenTransitions.mPendingResize;
        if (transitSession != null) {
            transitSession.mCanceled = true;
            transitSession.mFinishedCallback = null;
            splitDecorManager3.cancelRunningAnimations();
            splitDecorManager4.cancelRunningAnimations();
            splitScreenTransitions.mAnimations.clear();
            splitScreenTransitions.onFinish(null);
        }
        splitScreenTransitions.mPendingResize = new SplitScreenTransitions.TransitSession(splitScreenTransitions, splitScreenTransitions.mTransitions.startTransition(6, windowContainerTransaction, this), stageCoordinator$$ExternalSyntheticLambda11, stageCoordinator$$ExternalSyntheticLambda112);
        float dividerPositionAsFraction = this.mSplitLayout.getDividerPositionAsFraction();
        SplitscreenEventLogger splitscreenEventLogger = this.mLogger;
        if (splitscreenEventLogger.mLoggerSessionId == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -4109533377578191407L, 0, null);
                return;
            }
            return;
        }
        if (dividerPositionAsFraction <= 0.0f || dividerPositionAsFraction >= 1.0f) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -1401793980569207330L, 0, null);
            }
        } else if (Float.compare(splitscreenEventLogger.mLastSplitRatio, dividerPositionAsFraction) == 0) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -4749391843597404276L, 0, null);
            }
        } else {
            splitscreenEventLogger.mLastSplitRatio = dividerPositionAsFraction;
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -3839017821800023881L, 6, Double.valueOf(dividerPositionAsFraction), Long.valueOf(splitscreenEventLogger.mLoggerSessionId.getId()));
            }
            FrameworkStatsLog.write(388, 4, 0, 0, splitscreenEventLogger.mLastSplitRatio, splitscreenEventLogger.mLastMainStagePosition, splitscreenEventLogger.mLastMainStageUid, splitscreenEventLogger.mLastSideStagePosition, splitscreenEventLogger.mLastSideStageUid, 0, splitscreenEventLogger.mLoggerSessionId.getId());
        }
    }

    public final void onRecentsInSplitAnimationCanceled() {
        this.mPausingTasks.clear();
        setSplitsVisible(false);
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        windowContainerTransaction.setReparentLeafTaskIfRelaunch(this.mRootTaskInfo.token, true);
        this.mTaskOrganizer.applyTransaction(windowContainerTransaction);
    }

    public final void onRecentsInSplitAnimationFinish(WindowContainerTransaction windowContainerTransaction, SurfaceControl.Transaction transaction) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 2599161363156908327L, 0, null);
        }
        this.mPausingTasks.clear();
        for (int i = 0; i < windowContainerTransaction.getHierarchyOps().size(); i++) {
            WindowContainerTransaction.HierarchyOp hierarchyOp = (WindowContainerTransaction.HierarchyOp) windowContainerTransaction.getHierarchyOps().get(i);
            IBinder container = hierarchyOp.getContainer();
            if (hierarchyOp.getType() == 1 && hierarchyOp.getToTop()) {
                StageTaskListener stageTaskListener = this.mMainStage;
                stageTaskListener.getClass();
                if (!stageTaskListener.contains(new StageTaskListener$$ExternalSyntheticLambda0(0, container))) {
                    StageTaskListener stageTaskListener2 = this.mSideStage;
                    stageTaskListener2.getClass();
                    if (stageTaskListener2.contains(new StageTaskListener$$ExternalSyntheticLambda0(0, container))) {
                    }
                }
                updateSurfaceBounds(this.mSplitLayout, transaction, false);
                transaction.reparent(this.mSplitLayout.getDividerLeash(), this.mRootTaskLeash);
                setDividerVisibility(transaction, true);
                return;
            }
        }
        setSplitsVisible(false);
        windowContainerTransaction.setReparentLeafTaskIfRelaunch(this.mRootTaskInfo.token, true);
    }

    public void onRootTaskAppeared() {
        boolean z = ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0];
        StageListenerImpl stageListenerImpl = this.mSideStageListener;
        StageListenerImpl stageListenerImpl2 = this.mMainStageListener;
        if (z) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 3196764427582911387L, 60, String.valueOf(this.mRootTaskInfo), Boolean.valueOf(stageListenerImpl2.mHasRootTask), Boolean.valueOf(stageListenerImpl.mHasRootTask));
        }
        if (this.mRootTaskInfo != null && stageListenerImpl2.mHasRootTask && stageListenerImpl.mHasRootTask) {
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            StageTaskListener stageTaskListener = this.mMainStage;
            windowContainerTransaction.reparent(stageTaskListener.mRootTaskInfo.token, this.mRootTaskInfo.token, true);
            StageTaskListener stageTaskListener2 = this.mSideStage;
            windowContainerTransaction.reparent(stageTaskListener2.mRootTaskInfo.token, this.mRootTaskInfo.token, true);
            windowContainerTransaction.setAdjacentRoots(stageTaskListener.mRootTaskInfo.token, stageTaskListener2.mRootTaskInfo.token);
            setRootForceTranslucent(windowContainerTransaction, true);
            this.mTempRect1.set(this.mSplitLayout.mInvisibleBounds);
            windowContainerTransaction.setBounds(stageTaskListener2.mRootTaskInfo.token, this.mTempRect1);
            SyncTransactionQueue syncTransactionQueue = this.mSyncQueue;
            syncTransactionQueue.queue(windowContainerTransaction);
            syncTransactionQueue.runInSync(new StageCoordinator$$ExternalSyntheticLambda3(0, this));
            WindowContainerToken windowContainerToken = stageTaskListener2.mRootTaskInfo.token;
            LaunchAdjacentController launchAdjacentController = this.mLaunchAdjacentController;
            launchAdjacentController.getClass();
            ShellProtoLogGroup shellProtoLogGroup = ShellProtoLogGroup.WM_SHELL_TASK_ORG;
            ProtoLog.d(shellProtoLogGroup, "set new launch adjacent flag root container", new Object[0]);
            launchAdjacentController.container = windowContainerToken;
            if (launchAdjacentController.launchAdjacentEnabled) {
                ProtoLog.v(shellProtoLogGroup, "enable launch adjacent flag root container", new Object[0]);
                WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
                windowContainerTransaction2.setLaunchAdjacentFlagRoot(windowContainerToken);
                launchAdjacentController.syncQueue.queue(windowContainerTransaction2);
            }
        }
    }

    public final void onRootTaskVanished() {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -8718789868823431286L, 0, null);
        }
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        LaunchAdjacentController launchAdjacentController = this.mLaunchAdjacentController;
        launchAdjacentController.getClass();
        ShellProtoLogGroup shellProtoLogGroup = ShellProtoLogGroup.WM_SHELL_TASK_ORG;
        ProtoLog.d(shellProtoLogGroup, "clear launch adjacent flag root container", new Object[0]);
        WindowContainerToken windowContainerToken = launchAdjacentController.container;
        if (windowContainerToken != null) {
            ProtoLog.v(shellProtoLogGroup, "disable launch adjacent flag root container", new Object[0]);
            WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
            windowContainerTransaction2.clearLaunchAdjacentFlagRoot(windowContainerToken);
            launchAdjacentController.syncQueue.queue(windowContainerTransaction2);
            launchAdjacentController.container = null;
        }
        applyExitSplitScreen(null, windowContainerTransaction, 6);
        SplitLayout splitLayout = this.mSplitLayout;
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.mDisplayInsetsController.mListeners.get(this.mDisplayId);
        if (copyOnWriteArrayList == null) {
            return;
        }
        copyOnWriteArrayList.remove(splitLayout);
    }

    public final void onSnappedToDismiss(int i, boolean z) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -5678120486275894442L, 3, Boolean.valueOf(z), String.valueOf(SplitScreenController.exitReasonToString(i)));
        }
        int i2 = (!z ? this.mSideStagePosition == 0 : this.mSideStagePosition == 1) ? 0 : 1;
        StageTaskListener stageTaskListener = i2 != 0 ? this.mMainStage : this.mSideStage;
        int i3 = 1 ^ i2;
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        windowContainerTransaction.setBounds(stageTaskListener.mRootTaskInfo.token, (Rect) null);
        windowContainerTransaction.setAppBounds(stageTaskListener.mRootTaskInfo.token, (Rect) null);
        windowContainerTransaction.setSmallestScreenWidthDp(stageTaskListener.mRootTaskInfo.token, 0);
        prepareExitSplitScreen(i3, windowContainerTransaction);
        ActivityManager.RunningTaskInfo runningTaskInfo = this.mRootTaskInfo;
        if (runningTaskInfo != null) {
            windowContainerTransaction.setDoNotPip(runningTaskInfo.token);
        }
        this.mSplitTransitions.startDismissTransition(windowContainerTransaction, this, i3, 4);
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl) {
        if (this.mRootTaskInfo != null || runningTaskInfo.hasParentTask()) {
            throw new IllegalArgumentException(this + "\n Unknown task appeared: " + runningTaskInfo);
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 141305615486892765L, 0, String.valueOf(runningTaskInfo));
        }
        this.mRootTaskInfo = runningTaskInfo;
        this.mRootTaskLeash = surfaceControl;
        if (this.mSplitLayout == null) {
            SplitLayout splitLayout = new SplitLayout(this.mContext, this.mRootTaskInfo.configuration, this, this.mParentContainerCallbacks, this.mDisplayController, this.mDisplayImeController, this.mTaskOrganizer, this.mMainHandler);
            this.mSplitLayout = splitLayout;
            this.mDisplayInsetsController.addInsetsChangedListener(this.mDisplayId, splitLayout);
        }
        onRootTaskAppeared();
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        ActivityManager.RunningTaskInfo runningTaskInfo2 = this.mRootTaskInfo;
        if (runningTaskInfo2 == null || runningTaskInfo2.taskId != runningTaskInfo.taskId) {
            throw new IllegalArgumentException(this + "\n Unknown task info changed: " + runningTaskInfo);
        }
        this.mRootTaskInfo = runningTaskInfo;
        SplitLayout splitLayout = this.mSplitLayout;
        if (splitLayout != null && splitLayout.updateConfiguration(runningTaskInfo.configuration) && this.mMainStage.mIsActive) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 808996695524260386L, 1, Long.valueOf(runningTaskInfo.taskId));
            }
            this.mSplitLayout.update(null, false);
            onLayoutSizeChanged(this.mSplitLayout);
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 6301854730259805020L, 0, String.valueOf(runningTaskInfo));
        }
        if (this.mRootTaskInfo == null) {
            throw new IllegalArgumentException(this + "\n Unknown task vanished: " + runningTaskInfo);
        }
        onRootTaskVanished();
        SplitLayout splitLayout = this.mSplitLayout;
        if (splitLayout != null) {
            splitLayout.release(null);
            this.mSplitLayout = null;
        }
        this.mRootTaskInfo = null;
        this.mRootTaskLeash = null;
        this.mIsRootTranslucent = false;
    }

    public final void onTransitionAnimationComplete() {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 3546642264633455342L, 0, null);
        }
        if (this.mMainStage.mIsActive || this.mIsExiting) {
            return;
        }
        this.mSplitLayout.release(null);
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 5115115808305329563L, 0, null);
        }
        SplitScreenTransitions splitScreenTransitions = this.mSplitTransitions;
        if (splitScreenTransitions.isPendingEnter(iBinder)) {
            StageCoordinator stageCoordinator = splitScreenTransitions.mStageCoordinator;
            if (!z) {
                stageCoordinator.finishEnterSplitScreen(transaction);
            }
            splitScreenTransitions.mPendingEnter.onConsumed();
            splitScreenTransitions.mPendingEnter = null;
            stageCoordinator.notifySplitAnimationFinished();
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -6820546117650467136L, 0, null);
            }
        } else if (splitScreenTransitions.isPendingDismiss(iBinder)) {
            splitScreenTransitions.mPendingDismiss.onConsumed();
            splitScreenTransitions.mPendingDismiss = null;
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 5648275469297727409L, 0, null);
            }
        } else if (splitScreenTransitions.isPendingResize(iBinder)) {
            splitScreenTransitions.mPendingResize.onConsumed();
            splitScreenTransitions.mPendingResize = null;
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -555665995816094727L, 0, null);
            }
        } else if (splitScreenTransitions.isPendingPassThrough(iBinder)) {
            splitScreenTransitions.mPendingRemotePassthrough.onConsumed();
            splitScreenTransitions.mPendingRemotePassthrough.mRemoteHandler.onTransitionConsumed(iBinder, z, transaction);
            splitScreenTransitions.mPendingRemotePassthrough = null;
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 3690820204750712967L, 0, null);
            }
        }
        OneShotRemoteHandler oneShotRemoteHandler = splitScreenTransitions.mActiveRemoteHandler;
        if (oneShotRemoteHandler != null) {
            oneShotRemoteHandler.onTransitionConsumed(iBinder, z, transaction);
        }
    }

    public final void prepareDismissAnimation(int i, int i2, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        int i3 = 0;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -1807079196441233509L, 5, Long.valueOf(transitionInfo.getDebugId()), Long.valueOf(i), String.valueOf(SplitScreenController.exitReasonToString(i2)));
        }
        StageTaskListener stageTaskListener = this.mSideStage;
        StageTaskListener stageTaskListener2 = this.mMainStage;
        if (i == -1) {
            if (stageTaskListener2.mChildrenTaskInfo.size() != 0) {
                StringBuilder sb = new StringBuilder();
                while (i3 < stageTaskListener2.mChildrenTaskInfo.size()) {
                    sb.append(i3 != 0 ? ", " : "");
                    sb.append(stageTaskListener2.mChildrenTaskInfo.keyAt(i3));
                    i3++;
                }
                Log.w("StageCoordinator", "Expected onTaskVanished on " + stageTaskListener2 + " to have been called with [" + sb.toString() + "] before startAnimation().");
            }
            if (stageTaskListener.mChildrenTaskInfo.size() != 0) {
                StringBuilder sb2 = new StringBuilder();
                int i4 = 0;
                while (i4 < stageTaskListener.mChildrenTaskInfo.size()) {
                    sb2.append(i4 != 0 ? ", " : "");
                    sb2.append(stageTaskListener.mChildrenTaskInfo.keyAt(i4));
                    i4++;
                }
                Log.w("StageCoordinator", "Expected onTaskVanished on " + stageTaskListener + " to have been called with [" + sb2.toString() + "] before startAnimation().");
            }
        }
        ArrayMap arrayMap = new ArrayMap();
        for (int m = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, 1); m >= 0; m--) {
            TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(m);
            ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
            if (taskInfo != null && (getStageOfTask(taskInfo) != null || getSplitItemPosition(change.getLastParent()) != -1)) {
                arrayMap.put(Integer.valueOf(taskInfo.taskId), change.getLeash());
            }
        }
        if (shouldBreakPairedTaskInRecents(i2)) {
            this.mRecentTasks.ifPresent(new StageCoordinator$$ExternalSyntheticLambda8(2, arrayMap));
        }
        this.mSplitRequest = null;
        setSplitsVisible(false);
        transaction.setCrop(stageTaskListener2.mRootLeash, null);
        transaction.setCrop(stageTaskListener.mRootLeash, null);
        if (i != -1) {
            transaction.hide(i == 0 ? stageTaskListener.mRootLeash : stageTaskListener2.mRootLeash);
            transaction.setPosition(i == 0 ? stageTaskListener2.mRootLeash : stageTaskListener.mRootLeash, 0.0f, 0.0f);
        } else {
            for (int size = arrayMap.keySet().size() - 1; size >= 0; size--) {
                transaction2.hide((SurfaceControl) arrayMap.valueAt(size));
            }
        }
        if (i == -1) {
            logExit(i2);
        } else {
            logExitToStage(i2, i == 0);
        }
        setDividerVisibility(transaction, false);
        transaction2.hide(stageTaskListener2.mDimLayer);
        transaction2.hide(stageTaskListener.mDimLayer);
    }

    public final void prepareEnterSplitScreen(WindowContainerTransaction windowContainerTransaction) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 2994550463633307045L, 0, null);
        }
        prepareEnterSplitScreen(windowContainerTransaction, null, -1, !this.mIsDropEntering);
    }

    public final void prepareExitSplitScreen(int i, WindowContainerTransaction windowContainerTransaction) {
        if (this.mMainStage.mIsActive) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 6755764515826776545L, 1, Long.valueOf(i));
            }
            this.mSideStage.removeAllTasks(windowContainerTransaction, i == 1);
            deactivateSplit(windowContainerTransaction, i == 0);
        }
    }

    public final void prepareSplitLayout(WindowContainerTransaction windowContainerTransaction, boolean z) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 4545601228927874522L, 3, Boolean.valueOf(z));
        }
        if (z) {
            this.mSplitLayout.setDividerAtBorder(this.mSideStagePosition == 0);
        } else {
            this.mSplitLayout.resetDividerPosition();
        }
        updateWindowBounds(this.mSplitLayout, windowContainerTransaction);
        if (z) {
            windowContainerTransaction.setSmallestScreenWidthDp(this.mMainStage.mRootTaskInfo.token, 0);
            this.mTempRect1.set(this.mSplitLayout.mInvisibleBounds);
            this.mSplitLayout.setTaskBounds(windowContainerTransaction, this.mSideStage.mRootTaskInfo, this.mTempRect1);
        }
        windowContainerTransaction.reorder(this.mRootTaskInfo.token, true);
        setRootForceTranslucent(windowContainerTransaction, false);
    }

    public final void prepareTasksForSplitScreen(int[] iArr, WindowContainerTransaction windowContainerTransaction) {
        for (int i : iArr) {
            ActivityManager.RunningTaskInfo runningTaskInfo = this.mTaskOrganizer.getRunningTaskInfo(i);
            if (runningTaskInfo != null) {
                windowContainerTransaction.setWindowingMode(runningTaskInfo.token, 0).setBounds(runningTaskInfo.token, (Rect) null);
            }
        }
    }

    public final void recordLastActiveStage() {
        StageTaskListener stageTaskListener = this.mMainStage;
        if (!stageTaskListener.mIsActive || !isSplitScreenVisible()) {
            this.mLastActiveStage = -1;
            return;
        }
        if (stageTaskListener.contains(new StageTaskListener$$ExternalSyntheticLambda2(2))) {
            this.mLastActiveStage = 0;
            return;
        }
        StageTaskListener stageTaskListener2 = this.mSideStage;
        stageTaskListener2.getClass();
        if (stageTaskListener2.contains(new StageTaskListener$$ExternalSyntheticLambda2(2))) {
            this.mLastActiveStage = 1;
        }
    }

    public final void requestEnterSplitSelect(final int i, final ActivityManager.RunningTaskInfo runningTaskInfo, final Rect rect, WindowContainerTransaction windowContainerTransaction) {
        Iterator it = ((HashSet) this.mSelectListeners).iterator();
        boolean z = false;
        while (it.hasNext()) {
            SplitScreenController.ISplitScreenImpl.AnonymousClass2 anonymousClass2 = (SplitScreenController.ISplitScreenImpl.AnonymousClass2) it.next();
            anonymousClass2.getClass();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            SplitScreenController.ISplitScreenImpl.this.mSelectListener.call(new SingleInstanceRemoteListener.RemoteCall() { // from class: com.android.wm.shell.splitscreen.SplitScreenController$ISplitScreenImpl$2$$ExternalSyntheticLambda0
                @Override // com.android.wm.shell.common.SingleInstanceRemoteListener.RemoteCall
                public final void accept(Object obj) {
                    AtomicBoolean atomicBoolean2 = atomicBoolean;
                    ActivityManager.RunningTaskInfo runningTaskInfo2 = runningTaskInfo;
                    int i2 = i;
                    Rect rect2 = rect;
                    ISplitSelectListener$Stub$Proxy iSplitSelectListener$Stub$Proxy = (ISplitSelectListener$Stub$Proxy) obj;
                    Parcel obtain = Parcel.obtain(iSplitSelectListener$Stub$Proxy.mRemote);
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.android.wm.shell.splitscreen.ISplitSelectListener");
                        obtain.writeTypedObject(runningTaskInfo2, 0);
                        obtain.writeInt(i2);
                        obtain.writeTypedObject(rect2, 0);
                        iSplitSelectListener$Stub$Proxy.mRemote.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        boolean readBoolean = obtain2.readBoolean();
                        obtain2.recycle();
                        obtain.recycle();
                        atomicBoolean2.set(readBoolean);
                    } catch (Throwable th) {
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                }
            });
            z |= atomicBoolean.get();
        }
        if (z) {
            this.mTaskOrganizer.applyTransaction(windowContainerTransaction);
        }
    }

    public final Bundle resolveStartStage(int i, int i2, Bundle bundle, WindowContainerTransaction windowContainerTransaction) {
        if (i == -1) {
            if (i2 == -1) {
                Slog.w("StageCoordinator", "No stage type nor split position specified to resolve start stage");
                return bundle;
            }
            if (isSplitScreenVisible()) {
                return resolveStartStage(i2 != this.mSideStagePosition ? 0 : 1, i2, bundle, windowContainerTransaction);
            }
            return resolveStartStage(1, i2, bundle, windowContainerTransaction);
        }
        StageTaskListener stageTaskListener = this.mMainStage;
        StageTaskListener stageTaskListener2 = this.mSideStage;
        if (i == 0) {
            if (i2 != -1) {
                setSideStagePosition(SplitScreenUtils.reverseSplitPosition(i2), windowContainerTransaction);
            } else {
                i2 = SplitScreenUtils.reverseSplitPosition(this.mSideStagePosition);
            }
            if (bundle == null) {
                bundle = new Bundle();
            }
            if (i2 == this.mSideStagePosition) {
                stageTaskListener = stageTaskListener2;
            }
            addActivityOptions(bundle, stageTaskListener);
            return bundle;
        }
        if (i != 1) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Unknown stage="));
        }
        if (i2 != -1) {
            setSideStagePosition(i2, windowContainerTransaction);
        } else {
            i2 = this.mSideStagePosition;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (i2 == this.mSideStagePosition) {
            stageTaskListener = stageTaskListener2;
        }
        addActivityOptions(bundle, stageTaskListener);
        return bundle;
    }

    public final void sendOnBoundsChanged() {
        if (this.mSplitLayout == null) {
            return;
        }
        for (int size = ((ArrayList) this.mListeners).size() - 1; size >= 0; size--) {
            SplitScreen.SplitScreenListener splitScreenListener = (SplitScreen.SplitScreenListener) ((ArrayList) this.mListeners).get(size);
            SplitLayout splitLayout = this.mSplitLayout;
            splitLayout.getClass();
            splitScreenListener.onSplitBoundsChanged(new Rect(splitLayout.mRootBounds), this.mSideStagePosition == 0 ? this.mSplitLayout.getBounds2() : this.mSplitLayout.getBounds1(), this.mSideStagePosition == 0 ? this.mSplitLayout.getBounds1() : this.mSplitLayout.getBounds2());
        }
    }

    public final void sendStatusToListener(SplitScreen.SplitScreenListener splitScreenListener) {
        splitScreenListener.onStagePositionChanged(0, SplitScreenUtils.reverseSplitPosition(this.mSideStagePosition));
        splitScreenListener.onStagePositionChanged(1, this.mSideStagePosition);
        splitScreenListener.onSplitVisibilityChanged(isSplitScreenVisible());
        SplitLayout splitLayout = this.mSplitLayout;
        if (splitLayout != null) {
            splitScreenListener.onSplitBoundsChanged(new Rect(splitLayout.mRootBounds), this.mSideStagePosition == 0 ? this.mSplitLayout.getBounds2() : this.mSplitLayout.getBounds1(), this.mSideStagePosition == 0 ? this.mSplitLayout.getBounds1() : this.mSplitLayout.getBounds2());
        }
        this.mSideStage.onSplitScreenListenerRegistered(splitScreenListener, 1);
        this.mMainStage.onSplitScreenListenerRegistered(splitScreenListener, 0);
    }

    public final void setDividerVisibility(SurfaceControl.Transaction transaction, boolean z) {
        if (z == this.mDividerVisible) {
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -4204423971804924690L, 63, Boolean.valueOf(z), Boolean.valueOf(this.mKeyguardActive), Boolean.FALSE, String.valueOf(Debug.getCaller()));
        }
        if (z && this.mKeyguardActive) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 4019041459758970273L, 0, null);
                return;
            }
            return;
        }
        this.mDividerVisible = z;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -1023677597576446492L, 3, Boolean.valueOf(z));
        }
        for (int size = ((ArrayList) this.mListeners).size() - 1; size >= 0; size--) {
            ((SplitScreen.SplitScreenListener) ((ArrayList) this.mListeners).get(size)).onSplitVisibilityChanged(this.mDividerVisible);
        }
        sendOnBoundsChanged();
        applyDividerVisibility(transaction);
    }

    public final void setLayoutOffsetTarget(int i, SplitLayout splitLayout) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 786816716454508474L, 5, Long.valueOf(0), Long.valueOf(i));
        }
        int i2 = this.mSideStagePosition;
        StageTaskListener stageTaskListener = this.mMainStage;
        StageTaskListener stageTaskListener2 = this.mSideStage;
        StageTaskListener stageTaskListener3 = i2 == 0 ? stageTaskListener2 : stageTaskListener;
        if (i2 != 0) {
            stageTaskListener = stageTaskListener2;
        }
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        ActivityManager.RunningTaskInfo runningTaskInfo = stageTaskListener3.mRootTaskInfo;
        ActivityManager.RunningTaskInfo runningTaskInfo2 = stageTaskListener.mRootTaskInfo;
        splitLayout.getClass();
        if (i == 0) {
            windowContainerTransaction.setBounds(runningTaskInfo.token, splitLayout.mBounds1);
            windowContainerTransaction.setScreenSizeDp(runningTaskInfo.token, 0, 0);
            windowContainerTransaction.setBounds(runningTaskInfo2.token, splitLayout.mBounds2);
            windowContainerTransaction.setScreenSizeDp(runningTaskInfo2.token, 0, 0);
        } else {
            splitLayout.mTempRect.set(splitLayout.mBounds1);
            splitLayout.mTempRect.offset(0, i);
            windowContainerTransaction.setBounds(runningTaskInfo.token, splitLayout.mTempRect);
            WindowContainerToken windowContainerToken = runningTaskInfo.token;
            Configuration configuration = runningTaskInfo.configuration;
            windowContainerTransaction.setScreenSizeDp(windowContainerToken, configuration.screenWidthDp, configuration.screenHeightDp);
            splitLayout.mTempRect.set(splitLayout.mBounds2);
            splitLayout.mTempRect.offset(0, i);
            windowContainerTransaction.setBounds(runningTaskInfo2.token, splitLayout.mTempRect);
            WindowContainerToken windowContainerToken2 = runningTaskInfo2.token;
            Configuration configuration2 = runningTaskInfo2.configuration;
            windowContainerTransaction.setScreenSizeDp(windowContainerToken2, configuration2.screenWidthDp, configuration2.screenHeightDp);
        }
        this.mTaskOrganizer.applyTransaction(windowContainerTransaction);
    }

    public final void setRootForceTranslucent(WindowContainerTransaction windowContainerTransaction, boolean z) {
        if (this.mIsRootTranslucent == z) {
            return;
        }
        this.mIsRootTranslucent = z;
        windowContainerTransaction.setForceTranslucent(this.mRootTaskInfo.token, z);
    }

    public final void setSideStagePosition(int i, WindowContainerTransaction windowContainerTransaction) {
        if (this.mSideStagePosition == i) {
            return;
        }
        this.mSideStagePosition = i;
        for (int size = ((ArrayList) this.mListeners).size() - 1; size >= 0; size--) {
            SplitScreen.SplitScreenListener splitScreenListener = (SplitScreen.SplitScreenListener) ((ArrayList) this.mListeners).get(size);
            splitScreenListener.onStagePositionChanged(0, SplitScreenUtils.reverseSplitPosition(this.mSideStagePosition));
            splitScreenListener.onStagePositionChanged(1, this.mSideStagePosition);
        }
        if (this.mSideStageListener.mVisible) {
            if (windowContainerTransaction == null) {
                onLayoutSizeChanged(this.mSplitLayout);
            } else {
                updateWindowBounds(this.mSplitLayout, windowContainerTransaction);
                sendOnBoundsChanged();
            }
        }
    }

    public void setSplitTransitions(SplitScreenTransitions splitScreenTransitions) {
        this.mSplitTransitions = splitScreenTransitions;
    }

    public final void setSplitsVisible(boolean z) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -7171744665051645980L, 3, Boolean.valueOf(z));
        }
        StageListenerImpl stageListenerImpl = this.mSideStageListener;
        stageListenerImpl.mVisible = z;
        StageListenerImpl stageListenerImpl2 = this.mMainStageListener;
        stageListenerImpl2.mVisible = z;
        stageListenerImpl.mHasChildren = z;
        stageListenerImpl2.mHasChildren = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:197:0x0417, code lost:
    
        if (r7.size() == 1) goto L178;
     */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0332  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x035b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0326  */
    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean startAnimation(android.os.IBinder r21, android.window.TransitionInfo r22, android.view.SurfaceControl.Transaction r23, android.view.SurfaceControl.Transaction r24, com.android.wm.shell.transition.Transitions.TransitionFinishCallback r25) {
        /*
            Method dump skipped, instructions count: 1119
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.splitscreen.StageCoordinator.startAnimation(android.os.IBinder, android.window.TransitionInfo, android.view.SurfaceControl$Transaction, android.view.SurfaceControl$Transaction, com.android.wm.shell.transition.Transitions$TransitionFinishCallback):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x0681, code lost:
    
        if (r7.mPendingDismiss.mReason == 4) goto L246;
     */
    /* JADX WARN: Removed duplicated region for block: B:141:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x016b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean startPendingAnimation(android.os.IBinder r25, android.window.TransitionInfo r26, android.view.SurfaceControl.Transaction r27, android.view.SurfaceControl.Transaction r28, com.android.wm.shell.transition.Transitions.TransitionFinishCallback r29) {
        /*
            Method dump skipped, instructions count: 1790
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.splitscreen.StageCoordinator.startPendingAnimation(android.os.IBinder, android.window.TransitionInfo, android.view.SurfaceControl$Transaction, android.view.SurfaceControl$Transaction, com.android.wm.shell.transition.Transitions$TransitionFinishCallback):boolean");
    }

    public final void startSingleTask(int i, Bundle bundle, WindowContainerTransaction windowContainerTransaction, RemoteTransition remoteTransition) {
        if (this.mMainStage.mChildrenTaskInfo.contains(i) || this.mSideStage.mChildrenTaskInfo.contains(i)) {
            prepareExitSplitScreen(-1, windowContainerTransaction);
        }
        if (this.mRecentTasks.isPresent()) {
            ((RecentTasksController) this.mRecentTasks.get()).removeSplitPair(i);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        addActivityOptions(bundle, null);
        windowContainerTransaction.startTask(i, bundle);
        this.mSplitTransitions.startFullscreenTransition(windowContainerTransaction, remoteTransition);
    }

    public final void startTasks(int i, Bundle bundle, int i2, Bundle bundle2, int i3, int i4, RemoteTransition remoteTransition, InstanceId instanceId) {
        Bundle bundle3 = bundle;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -6325240002302691464L, 85, Long.valueOf(i), Long.valueOf(i2), Long.valueOf(i3), Long.valueOf(i4));
        }
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        if (i2 == -1) {
            startSingleTask(i, bundle3, windowContainerTransaction, remoteTransition);
            return;
        }
        setSideStagePosition(i3, windowContainerTransaction);
        if (bundle3 == null) {
            bundle3 = new Bundle();
        }
        addActivityOptions(bundle3, this.mSideStage);
        prepareTasksForSplitScreen(new int[]{i, i2}, windowContainerTransaction);
        windowContainerTransaction.startTask(i, bundle3);
        startWithTask(windowContainerTransaction, i2, bundle2, i4, remoteTransition, instanceId);
    }

    public final void startWithTask(WindowContainerTransaction windowContainerTransaction, int i, Bundle bundle, int i2, RemoteTransition remoteTransition, InstanceId instanceId) {
        StageTaskListener stageTaskListener = this.mMainStage;
        if (!stageTaskListener.mIsActive) {
            activateSplit(windowContainerTransaction, false);
        }
        this.mSplitLayout.setDivideRatio(i2);
        updateWindowBounds(this.mSplitLayout, windowContainerTransaction);
        windowContainerTransaction.reorder(this.mRootTaskInfo.token, true);
        windowContainerTransaction.setReparentLeafTaskIfRelaunch(this.mRootTaskInfo.token, false);
        setRootForceTranslucent(windowContainerTransaction, false);
        if (bundle == null) {
            bundle = new Bundle();
        }
        addActivityOptions(bundle, stageTaskListener);
        windowContainerTransaction.startTask(i, bundle);
        if (this.mPausingTasks.contains(Integer.valueOf(i))) {
            this.mPausingTasks.clear();
        }
        this.mSplitTransitions.startEnterTransition(windowContainerTransaction, remoteTransition, this, 1004, false);
        if (instanceId != null) {
            SplitscreenEventLogger splitscreenEventLogger = this.mLogger;
            splitscreenEventLogger.mEnterSessionId = instanceId;
            splitscreenEventLogger.mEnterReason = 3;
        }
    }

    @Override // com.android.wm.shell.ShellTaskOrganizer.TaskListener
    public final boolean supportCompatUI() {
        return false;
    }

    public final void switchSplitPosition(String str) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 1399060535595712440L, 0, null);
        }
        SurfaceControl.Transaction acquire = this.mTransactionPool.acquire();
        this.mTempRect1.setEmpty();
        int i = this.mSideStagePosition;
        StageTaskListener stageTaskListener = this.mMainStage;
        StageTaskListener stageTaskListener2 = this.mSideStage;
        StageTaskListener stageTaskListener3 = i == 0 ? stageTaskListener2 : stageTaskListener;
        StageTaskListener stageTaskListener4 = i == 0 ? stageTaskListener : stageTaskListener2;
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        windowContainerTransaction.setFocusable(this.mRootTaskInfo.token, false);
        this.mSyncQueue.queue(windowContainerTransaction);
        this.mSplitLayout.playSwapAnimation(acquire, stageTaskListener3, stageTaskListener4, new StageCoordinator$$ExternalSyntheticLambda13(this, stageTaskListener3, stageTaskListener4));
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 8065000900149852489L, 0, str);
        }
        int reverseSplitPosition = SplitScreenUtils.reverseSplitPosition(this.mSideStagePosition);
        int topChildTaskUid = stageTaskListener.getTopChildTaskUid();
        int i2 = this.mSideStagePosition;
        int topChildTaskUid2 = stageTaskListener2.getTopChildTaskUid();
        boolean z = this.mSplitLayout.mIsLeftRightSplit;
        SplitscreenEventLogger splitscreenEventLogger = this.mLogger;
        if (splitscreenEventLogger.mLoggerSessionId == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 7942520113985172790L, 0, null);
            }
        } else {
            splitscreenEventLogger.updateMainStageState(SplitscreenEventLogger.getMainStagePositionFromSplitPosition(reverseSplitPosition, z), topChildTaskUid);
            splitscreenEventLogger.updateSideStageState(SplitscreenEventLogger.getSideStagePositionFromSplitPosition(i2, z), topChildTaskUid2);
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 7611404824230378646L, 341, Long.valueOf(splitscreenEventLogger.mLastMainStagePosition), Long.valueOf(splitscreenEventLogger.mLastMainStageUid), Long.valueOf(splitscreenEventLogger.mLastSideStagePosition), Long.valueOf(splitscreenEventLogger.mLastSideStageUid), Long.valueOf(splitscreenEventLogger.mLoggerSessionId.getId()));
            }
            FrameworkStatsLog.write(388, 5, 0, 0, 0.0f, splitscreenEventLogger.mLastMainStagePosition, splitscreenEventLogger.mLastMainStageUid, splitscreenEventLogger.mLastSideStagePosition, splitscreenEventLogger.mLastSideStageUid, 0, splitscreenEventLogger.mLoggerSessionId.getId());
        }
    }

    public final void updateRecentTasksSplitPair() {
        if (this.mShouldUpdateRecents && this.mPausingTasks.isEmpty()) {
            this.mRecentTasks.ifPresent(new StageCoordinator$$ExternalSyntheticLambda5(this, 2));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01ba A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateSurfaceBounds(com.android.wm.shell.common.split.SplitLayout r16, android.view.SurfaceControl.Transaction r17, boolean r18) {
        /*
            Method dump skipped, instructions count: 518
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.splitscreen.StageCoordinator.updateSurfaceBounds(com.android.wm.shell.common.split.SplitLayout, android.view.SurfaceControl$Transaction, boolean):void");
    }

    public final boolean updateWindowBounds(SplitLayout splitLayout, WindowContainerTransaction windowContainerTransaction) {
        boolean z;
        int i = this.mSideStagePosition;
        StageTaskListener stageTaskListener = this.mMainStage;
        StageTaskListener stageTaskListener2 = this.mSideStage;
        StageTaskListener stageTaskListener3 = i == 0 ? stageTaskListener2 : stageTaskListener;
        if (i != 0) {
            stageTaskListener = stageTaskListener2;
        }
        ActivityManager.RunningTaskInfo runningTaskInfo = stageTaskListener3.mRootTaskInfo;
        ActivityManager.RunningTaskInfo runningTaskInfo2 = stageTaskListener.mRootTaskInfo;
        boolean z2 = true;
        if (splitLayout.mBounds1.equals(splitLayout.mWinBounds1) && runningTaskInfo.token.equals(splitLayout.mWinToken1)) {
            z = false;
        } else {
            splitLayout.setTaskBounds(windowContainerTransaction, runningTaskInfo, splitLayout.mBounds1);
            splitLayout.mWinBounds1.set(splitLayout.mBounds1);
            splitLayout.mWinToken1 = runningTaskInfo.token;
            z = true;
        }
        if (splitLayout.mBounds2.equals(splitLayout.mWinBounds2) && runningTaskInfo2.token.equals(splitLayout.mWinToken2)) {
            z2 = z;
        } else {
            splitLayout.setTaskBounds(windowContainerTransaction, runningTaskInfo2, splitLayout.mBounds2);
            splitLayout.mWinBounds2.set(splitLayout.mBounds2);
            splitLayout.mWinToken2 = runningTaskInfo2.token;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -2337050910502079842L, 0, String.valueOf(splitLayout.getBounds1()), String.valueOf(splitLayout.getBounds2()));
        }
        return z2;
    }

    public boolean willSleepOnFold() {
        FoldLockSettingsObserver foldLockSettingsObserver = this.mFoldLockSettingsObserver;
        return foldLockSettingsObserver != null && foldLockSettingsObserver.isSleepOnFold();
    }

    public final void prepareEnterSplitScreen(WindowContainerTransaction windowContainerTransaction, ActivityManager.RunningTaskInfo runningTaskInfo, int i, boolean z) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 5089466645438215981L, 13, Long.valueOf(i), Boolean.valueOf(z));
        }
        windowContainerTransaction.setReparentLeafTaskIfRelaunch(this.mRootTaskInfo.token, false);
        StageTaskListener stageTaskListener = this.mMainStage;
        if (stageTaskListener.mIsActive) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 4010958692492917319L, 13, Long.valueOf(runningTaskInfo != null ? runningTaskInfo.taskId : -1L), Boolean.valueOf(isSplitScreenVisible()));
            }
            if (runningTaskInfo != null) {
                windowContainerTransaction.startTask(runningTaskInfo.taskId, resolveStartStage(-1, i, null, windowContainerTransaction));
            }
            if (isSplitScreenVisible()) {
                return;
            }
            if (!this.mSkipEvictingMainStageChildren) {
                stageTaskListener.evictAllChildren(windowContainerTransaction);
            }
            windowContainerTransaction.reparentTasks((WindowContainerToken) null, stageTaskListener.mRootTaskInfo.token, SplitScreenConstants.CONTROLLED_WINDOWING_MODES, SplitScreenConstants.CONTROLLED_ACTIVITY_TYPES, true, true);
            prepareSplitLayout(windowContainerTransaction, z);
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 2300292853844685151L, 13, Long.valueOf(runningTaskInfo != null ? runningTaskInfo.taskId : -1L), Boolean.valueOf(isSplitScreenVisible()));
        }
        setSplitsVisible(false);
        if (runningTaskInfo != null) {
            setSideStagePosition(i, windowContainerTransaction);
            StageTaskListener stageTaskListener2 = this.mSideStage;
            stageTaskListener2.getClass();
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 5914549992674768572L, 1, Long.valueOf(runningTaskInfo.taskId));
            }
            windowContainerTransaction.setWindowingMode(runningTaskInfo.token, 0).setBounds(runningTaskInfo.token, (Rect) null);
            windowContainerTransaction.reparent(runningTaskInfo.token, stageTaskListener2.mRootTaskInfo.token, true);
        }
        activateSplit(windowContainerTransaction, true);
        prepareSplitLayout(windowContainerTransaction, z);
    }

    public final StageTaskListener getStageOfTask(ActivityManager.RunningTaskInfo runningTaskInfo) {
        StageTaskListener stageTaskListener = this.mMainStage;
        ActivityManager.RunningTaskInfo runningTaskInfo2 = stageTaskListener.mRootTaskInfo;
        if (runningTaskInfo2 != null && runningTaskInfo.parentTaskId == runningTaskInfo2.taskId) {
            return stageTaskListener;
        }
        StageTaskListener stageTaskListener2 = this.mSideStage;
        ActivityManager.RunningTaskInfo runningTaskInfo3 = stageTaskListener2.mRootTaskInfo;
        if (runningTaskInfo3 == null || runningTaskInfo.parentTaskId != runningTaskInfo3.taskId) {
            return null;
        }
        return stageTaskListener2;
    }

    public StageCoordinator(Context context, int i, SyncTransactionQueue syncTransactionQueue, ShellTaskOrganizer shellTaskOrganizer, StageTaskListener stageTaskListener, StageTaskListener stageTaskListener2, DisplayController displayController, DisplayImeController displayImeController, DisplayInsetsController displayInsetsController, SplitLayout splitLayout, Transitions transitions, TransactionPool transactionPool, ShellExecutor shellExecutor, Handler handler, Optional optional, LaunchAdjacentController launchAdjacentController, Optional optional2) {
        this.mMainStageListener = new StageListenerImpl();
        this.mSideStageListener = new StageListenerImpl();
        this.mSideStagePosition = 1;
        this.mListeners = new ArrayList();
        this.mSelectListeners = new HashSet();
        this.mPausingTasks = new ArrayList();
        this.mTempRect1 = new Rect();
        this.mTempRect2 = new Rect();
        this.mShouldUpdateRecents = true;
        this.mParentContainerCallbacks = new AnonymousClass1();
        this.mContext = context;
        this.mDisplayId = i;
        this.mSyncQueue = syncTransactionQueue;
        this.mTaskOrganizer = shellTaskOrganizer;
        this.mMainStage = stageTaskListener;
        this.mSideStage = stageTaskListener2;
        this.mDisplayController = displayController;
        this.mDisplayImeController = displayImeController;
        this.mDisplayInsetsController = displayInsetsController;
        this.mTransactionPool = transactionPool;
        this.mSplitLayout = splitLayout;
        this.mSplitTransitions = new SplitScreenTransitions(transactionPool, transitions, new StageCoordinator$$ExternalSyntheticLambda1(this, 2), this);
        this.mLogger = new SplitscreenEventLogger();
        this.mMainExecutor = shellExecutor;
        this.mMainHandler = handler;
        this.mRecentTasks = optional;
        this.mLaunchAdjacentController = launchAdjacentController;
        this.mWindowDecorViewModel = optional2;
        displayController.addDisplayWindowListener(this);
        transitions.addHandler(this);
        this.mSplitUnsupportedToast = Toast.makeText(context, R.string.dock_non_resizeble_failed_to_dock_text, 0);
        FoldLockSettingsObserver foldLockSettingsObserver = new FoldLockSettingsObserver(context.getMainThreadHandler(), context);
        this.mFoldLockSettingsObserver = foldLockSettingsObserver;
        foldLockSettingsObserver.register();
    }
}

package com.android.wm.shell.transition;

import android.app.ActivityManager;
import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.os.IBinder;
import android.util.ArrayMap;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.policy.ScreenDecorationsUtils;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.systemui.animation.RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0;
import com.android.wm.shell.desktopmode.DesktopTasksController;
import com.android.wm.shell.keyguard.KeyguardTransitionHandler;
import com.android.wm.shell.pip.PipTransitionController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.recents.RecentsTransitionHandler;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import com.android.wm.shell.splitscreen.StageCoordinator;
import com.android.wm.shell.splitscreen.StageTaskListener;
import com.android.wm.shell.transition.DefaultMixedHandler;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RecentsMixedTransition extends DefaultMixedHandler.MixedTransition {
    public final DesktopTasksController mDesktopTasksController;
    public final RecentsTransitionHandler mRecentsHandler;

    public RecentsMixedTransition(int i, IBinder iBinder, Transitions transitions, DefaultMixedHandler defaultMixedHandler, PipTransitionController pipTransitionController, StageCoordinator stageCoordinator, KeyguardTransitionHandler keyguardTransitionHandler, RecentsTransitionHandler recentsTransitionHandler, DesktopTasksController desktopTasksController) {
        super(i, iBinder, transitions, defaultMixedHandler, pipTransitionController, stageCoordinator, keyguardTransitionHandler);
        this.mRecentsHandler = recentsTransitionHandler;
        this.mDesktopTasksController = desktopTasksController;
        this.mLeftoversHandler = recentsTransitionHandler;
    }

    @Override // com.android.wm.shell.transition.DefaultMixedHandler.MixedTransition
    public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        int i = this.mType;
        if (i == 4) {
            if (this.mSplitHandler.mSplitTransitions.isPendingEnter(iBinder)) {
                this.mAnimType = 1;
            }
            this.mLeftoversHandler.mergeAnimation(iBinder, transitionInfo, transaction, iBinder2, transitionFinishCallback);
            return;
        }
        if (i != 6) {
            if (i != 7) {
                throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Playing a Recents mixed transition with unknown or illegal type: "));
            }
            this.mLeftoversHandler.mergeAnimation(iBinder, transitionInfo, transaction, iBinder2, transitionFinishCallback);
            return;
        }
        if ((transitionInfo.getFlags() & 8192) != 0) {
            TransitionInfo transitionInfo2 = this.mInfo;
            for (int rootCount = transitionInfo.getRootCount() - 1; rootCount >= 0; rootCount--) {
                transaction.show(transitionInfo.getRoot(rootCount).getLeash());
            }
            ArrayMap arrayMap = new ArrayMap();
            for (TransitionInfo.Change change : transitionInfo2.getChanges()) {
                if (change.getContainer() != null) {
                    arrayMap.put(change.getContainer(), change);
                }
            }
            for (TransitionInfo.Change change2 : transitionInfo.getChanges()) {
                if (arrayMap.containsKey(change2.getContainer())) {
                    TransitionInfo.Change change3 = (TransitionInfo.Change) arrayMap.get(change2.getContainer());
                    transaction.reparent(change2.getLeash(), null);
                    change2.setLeash(change3.getLeash());
                }
            }
            if (MixedTransitionHelper.animateKeyguard(this, transitionInfo, transaction, this.mFinishT, this.mFinishCB, this.mKeyguardHandler, this.mPipHandler)) {
                transitionFinishCallback.onTransitionFinished(null);
            }
        }
        this.mLeftoversHandler.mergeAnimation(iBinder, transitionInfo, transaction, iBinder2, transitionFinishCallback);
    }

    @Override // com.android.wm.shell.transition.DefaultMixedHandler.MixedTransition
    public final void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
        int i = this.mType;
        if (i == 4 || i == 6 || i == 7) {
            this.mLeftoversHandler.onTransitionConsumed(iBinder, z, transaction);
        }
        if (this.mHasRequestToRemote) {
            this.mPlayer.mRemoteTransitionHandler.onTransitionConsumed(iBinder, z, transaction);
        }
    }

    @Override // com.android.wm.shell.transition.DefaultMixedHandler.MixedTransition
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, final SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        boolean startAnimation;
        int i;
        int i2 = this.mType;
        if (i2 == 4) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -7057538322415863605L, 1, Long.valueOf(transitionInfo.getDebugId()));
            }
            int m = RemoteAnimationRunnerCompat$1$$ExternalSyntheticOutline0.m(transitionInfo, 1);
            while (true) {
                StageCoordinator stageCoordinator = this.mSplitHandler;
                if (m >= 0) {
                    TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(m);
                    if (this.mPipHandler.isEnteringPip(change, transitionInfo.getType()) && stageCoordinator.getSplitItemPosition(change.getLastParent()) != -1) {
                        startAnimation = MixedTransitionHelper.animateEnterPipFromSplit(this, transitionInfo, transaction, transaction2, transitionFinishCallback, this.mPlayer, this.mMixedHandler, this.mPipHandler, this.mSplitHandler, false);
                        break;
                    }
                    m--;
                } else {
                    final DefaultMixedHandler$$ExternalSyntheticLambda4 defaultMixedHandler$$ExternalSyntheticLambda4 = (DefaultMixedHandler$$ExternalSyntheticLambda4) transitionFinishCallback;
                    Transitions.TransitionFinishCallback transitionFinishCallback2 = new Transitions.TransitionFinishCallback() { // from class: com.android.wm.shell.transition.RecentsMixedTransition$$ExternalSyntheticLambda1
                        @Override // com.android.wm.shell.transition.Transitions.TransitionFinishCallback
                        public final void onTransitionFinished(WindowContainerTransaction windowContainerTransaction) {
                            SurfaceControl.Transaction transaction3 = transaction2;
                            RecentsMixedTransition recentsMixedTransition = RecentsMixedTransition.this;
                            recentsMixedTransition.mInFlightSubAnimations = 0;
                            if (windowContainerTransaction == null) {
                                windowContainerTransaction = new WindowContainerTransaction();
                            }
                            int i3 = recentsMixedTransition.mAnimType;
                            StageCoordinator stageCoordinator2 = recentsMixedTransition.mSplitHandler;
                            if (i3 != 1) {
                                stageCoordinator2.onRecentsInSplitAnimationFinish(windowContainerTransaction, transaction3);
                            } else {
                                stageCoordinator2.getClass();
                                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 3838958226342231619L, 0, null);
                                }
                                for (int size = stageCoordinator2.mPausingTasks.size() - 1; size >= 0; size--) {
                                    int intValue = ((Integer) stageCoordinator2.mPausingTasks.get(size)).intValue();
                                    StageTaskListener stageTaskListener = stageCoordinator2.mMainStage;
                                    if (stageTaskListener.mChildrenTaskInfo.contains(intValue)) {
                                        stageTaskListener.evictChildren(intValue, windowContainerTransaction);
                                    } else {
                                        StageTaskListener stageTaskListener2 = stageCoordinator2.mSideStage;
                                        if (stageTaskListener2.mChildrenTaskInfo.contains(intValue)) {
                                            stageTaskListener2.evictChildren(intValue, windowContainerTransaction);
                                        }
                                    }
                                }
                                if (stageCoordinator2.mSplitTransitions.mPendingEnter == null) {
                                    stageCoordinator2.mPausingTasks.clear();
                                    stageCoordinator2.updateRecentTasksSplitPair();
                                }
                            }
                            stageCoordinator2.onTransitionAnimationComplete();
                            defaultMixedHandler$$ExternalSyntheticLambda4.onTransitionFinished(windowContainerTransaction);
                        }
                    };
                    this.mInFlightSubAnimations = 1;
                    stageCoordinator.getClass();
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -5213330322118069807L, 1, Long.valueOf(transitionInfo.getDebugId()));
                    }
                    if (stageCoordinator.isSplitScreenVisible()) {
                        for (int i3 = 0; i3 < transitionInfo.getChanges().size(); i3++) {
                            TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo.getChanges().get(i3);
                            if (TransitionUtil.isClosingType(change2.getMode()) && change2.getTaskInfo() != null && (stageCoordinator.mMainStage.getTopVisibleChildTaskId() == (i = change2.getTaskInfo().taskId) || stageCoordinator.mSideStage.getTopVisibleChildTaskId() == i)) {
                                stageCoordinator.mPausingTasks.add(Integer.valueOf(i));
                            }
                        }
                    }
                    stageCoordinator.addDividerBarToTransition(transitionInfo, false);
                    startAnimation = this.mLeftoversHandler.startAnimation(this.mTransition, transitionInfo, transaction, transaction2, transitionFinishCallback2);
                    if (!startAnimation) {
                        stageCoordinator.onRecentsInSplitAnimationCanceled();
                    }
                }
            }
            return startAnimation;
        }
        if (i2 == 6) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -7297861622461350897L, 1, Long.valueOf(transitionInfo.getDebugId()));
            }
            if (this.mInfo == null) {
                this.mInfo = transitionInfo;
                this.mFinishT = transaction2;
                this.mFinishCB = transitionFinishCallback;
            }
            return startSubAnimation(this.mRecentsHandler, transitionInfo, transaction, transaction2);
        }
        if (i2 != 7) {
            throw new IllegalStateException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i2, "Starting Recents mixed animation with unknown or illegal type: "));
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -4327489982751689646L, 1, Long.valueOf(transitionInfo.getDebugId()));
        }
        if (this.mInfo == null) {
            this.mInfo = transitionInfo;
            this.mFinishT = transaction2;
            this.mFinishCB = transitionFinishCallback;
        }
        final DefaultMixedHandler$$ExternalSyntheticLambda4 defaultMixedHandler$$ExternalSyntheticLambda42 = (DefaultMixedHandler$$ExternalSyntheticLambda4) transitionFinishCallback;
        Transitions.TransitionFinishCallback transitionFinishCallback3 = new Transitions.TransitionFinishCallback() { // from class: com.android.wm.shell.transition.RecentsMixedTransition$$ExternalSyntheticLambda0
            @Override // com.android.wm.shell.transition.Transitions.TransitionFinishCallback
            public final void onTransitionFinished(WindowContainerTransaction windowContainerTransaction) {
                RecentsMixedTransition recentsMixedTransition = RecentsMixedTransition.this;
                int i4 = recentsMixedTransition.mInFlightSubAnimations - 1;
                recentsMixedTransition.mInFlightSubAnimations = i4;
                if (i4 == 0) {
                    defaultMixedHandler$$ExternalSyntheticLambda42.onTransitionFinished(windowContainerTransaction);
                }
            }
        };
        this.mInFlightSubAnimations++;
        if (this.mRecentsHandler.startAnimation(this.mTransition, transitionInfo, transaction, transaction2, transitionFinishCallback3)) {
            DesktopTasksController desktopTasksController = this.mDesktopTasksController;
            if (desktopTasksController != null) {
                if (!DesktopModeStatus.USE_ROUNDED_CORNERS) {
                    return true;
                }
                float windowCornerRadius = ScreenDecorationsUtils.getWindowCornerRadius(desktopTasksController.context);
                List changes = transitionInfo.getChanges();
                ArrayList arrayList = new ArrayList();
                for (Object obj : changes) {
                    ActivityManager.RunningTaskInfo taskInfo = ((TransitionInfo.Change) obj).getTaskInfo();
                    if (taskInfo != null && taskInfo.getWindowingMode() == 5) {
                        arrayList.add(obj);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    transaction2.setCornerRadius(((TransitionInfo.Change) it.next()).getLeash(), windowCornerRadius);
                }
                return true;
            }
        } else {
            this.mInFlightSubAnimations--;
        }
        return false;
    }
}

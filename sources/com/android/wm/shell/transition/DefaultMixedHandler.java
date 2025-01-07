package com.android.wm.shell.transition;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.os.IBinder;
import android.util.Pair;
import android.view.SurfaceControl;
import android.window.RemoteTransition;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.activityembedding.ActivityEmbeddingController;
import com.android.wm.shell.common.split.SplitScreenUtils;
import com.android.wm.shell.desktopmode.DesktopTasksController;
import com.android.wm.shell.keyguard.KeyguardTransitionHandler;
import com.android.wm.shell.pip.PipTransitionController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.recents.RecentsTransitionHandler;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.splitscreen.StageCoordinator;
import com.android.wm.shell.splitscreen.StageCoordinator$$ExternalSyntheticLambda8;
import com.android.wm.shell.splitscreen.StageTaskListener;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.unfold.UnfoldTransitionHandler;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DefaultMixedHandler implements Transitions.TransitionHandler {
    public final ArrayList mActiveTransitions = new ArrayList();
    public ActivityEmbeddingController mActivityEmbeddingController;
    public DesktopTasksController mDesktopTasksController;
    public final KeyguardTransitionHandler mKeyguardHandler;
    public PipTransitionController mPipHandler;
    public final Transitions mPlayer;
    public RecentsTransitionHandler mRecentsHandler;
    public StageCoordinator mSplitHandler;
    public UnfoldTransitionHandler mUnfoldHandler;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class MixedTransition {
        public boolean mHasRequestToRemote;
        public final KeyguardTransitionHandler mKeyguardHandler;
        public final DefaultMixedHandler mMixedHandler;
        public final PipTransitionController mPipHandler;
        public final Transitions mPlayer;
        public final StageCoordinator mSplitHandler;
        public final IBinder mTransition;
        public final int mType;
        public int mAnimType = 0;
        public Transitions.TransitionHandler mLeftoversHandler = null;
        public TransitionInfo mInfo = null;
        public WindowContainerTransaction mFinishWCT = null;
        public SurfaceControl.Transaction mFinishT = null;
        public Transitions.TransitionFinishCallback mFinishCB = null;
        public int mInFlightSubAnimations = 0;

        public MixedTransition(int i, IBinder iBinder, Transitions transitions, DefaultMixedHandler defaultMixedHandler, PipTransitionController pipTransitionController, StageCoordinator stageCoordinator, KeyguardTransitionHandler keyguardTransitionHandler) {
            this.mType = i;
            this.mTransition = iBinder;
            this.mPlayer = transitions;
            this.mMixedHandler = defaultMixedHandler;
            this.mPipHandler = pipTransitionController;
            this.mSplitHandler = stageCoordinator;
            this.mKeyguardHandler = keyguardTransitionHandler;
        }

        public final void joinFinishArgs(WindowContainerTransaction windowContainerTransaction) {
            if (windowContainerTransaction != null) {
                WindowContainerTransaction windowContainerTransaction2 = this.mFinishWCT;
                if (windowContainerTransaction2 == null) {
                    this.mFinishWCT = windowContainerTransaction;
                } else {
                    windowContainerTransaction2.merge(windowContainerTransaction, true);
                }
            }
        }

        public abstract void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback);

        public abstract void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction);

        public abstract boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback);

        public final boolean startSubAnimation(Transitions.TransitionHandler transitionHandler, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
            if (this.mInfo != null && ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 2342603963533375943L, 5, Long.valueOf(r0.getDebugId()), Long.valueOf(transitionInfo.getDebugId()));
            }
            this.mInFlightSubAnimations++;
            if (transitionHandler.startAnimation(this.mTransition, transitionInfo, transaction, transaction2, new DefaultMixedHandler$$ExternalSyntheticLambda5(1, this, transitionInfo))) {
                return true;
            }
            this.mInFlightSubAnimations--;
            return false;
        }
    }

    public DefaultMixedHandler(ShellInit shellInit, Transitions transitions, final Optional optional, final PipTransitionController pipTransitionController, final Optional optional2, KeyguardTransitionHandler keyguardTransitionHandler, final Optional optional3, final Optional optional4, final Optional optional5) {
        this.mPlayer = transitions;
        this.mKeyguardHandler = keyguardTransitionHandler;
        if (Transitions.ENABLE_SHELL_TRANSITIONS && pipTransitionController != null && optional.isPresent()) {
            shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.transition.DefaultMixedHandler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultMixedHandler defaultMixedHandler = DefaultMixedHandler.this;
                    PipTransitionController pipTransitionController2 = pipTransitionController;
                    Optional optional6 = optional;
                    Optional optional7 = optional2;
                    Optional optional8 = optional3;
                    Optional optional9 = optional4;
                    Optional optional10 = optional5;
                    defaultMixedHandler.mPipHandler = pipTransitionController2;
                    pipTransitionController2.mMixedHandler = defaultMixedHandler;
                    defaultMixedHandler.mSplitHandler = ((SplitScreenController) optional6.get()).getTransitionHandler();
                    defaultMixedHandler.mPlayer.addHandler(defaultMixedHandler);
                    StageCoordinator stageCoordinator = defaultMixedHandler.mSplitHandler;
                    if (stageCoordinator != null) {
                        stageCoordinator.mMixedHandler = defaultMixedHandler;
                    }
                    RecentsTransitionHandler recentsTransitionHandler = (RecentsTransitionHandler) optional7.orElse(null);
                    defaultMixedHandler.mRecentsHandler = recentsTransitionHandler;
                    if (recentsTransitionHandler != null) {
                        recentsTransitionHandler.mMixers.add(defaultMixedHandler);
                    }
                    defaultMixedHandler.mDesktopTasksController = (DesktopTasksController) optional8.orElse(null);
                    defaultMixedHandler.mUnfoldHandler = (UnfoldTransitionHandler) optional9.orElse(null);
                    defaultMixedHandler.mActivityEmbeddingController = (ActivityEmbeddingController) optional10.orElse(null);
                }
            }, this);
        }
    }

    public static TransitionInfo subCopy(TransitionInfo transitionInfo, int i, boolean z) {
        TransitionInfo transitionInfo2 = new TransitionInfo(i, z ? transitionInfo.getFlags() : 0);
        transitionInfo2.setTrack(transitionInfo.getTrack());
        transitionInfo2.setDebugId(transitionInfo.getDebugId());
        if (z) {
            for (int i2 = 0; i2 < transitionInfo.getChanges().size(); i2++) {
                transitionInfo2.getChanges().add((TransitionInfo.Change) transitionInfo.getChanges().get(i2));
            }
        }
        for (int i3 = 0; i3 < transitionInfo.getRootCount(); i3++) {
            transitionInfo2.addRoot(transitionInfo.getRoot(i3));
        }
        transitionInfo2.setAnimationOptions(transitionInfo.getAnimationOptions());
        return transitionInfo2;
    }

    public final DefaultMixedTransition createDefaultMixedTransition(IBinder iBinder, int i) {
        return new DefaultMixedTransition(i, iBinder, this.mPlayer, this, this.mPipHandler, this.mSplitHandler, this.mKeyguardHandler, this.mUnfoldHandler, this.mActivityEmbeddingController);
    }

    public final RecentsMixedTransition createRecentsMixedTransition(IBinder iBinder, int i) {
        return new RecentsMixedTransition(i, iBinder, this.mPlayer, this, this.mPipHandler, this.mSplitHandler, this.mKeyguardHandler, this.mRecentsHandler, this.mDesktopTasksController);
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        StageCoordinator stageCoordinator = this.mSplitHandler;
        StageTaskListener stageTaskListener = stageCoordinator.mMainStage;
        int i = 0;
        if (stageTaskListener.mIsActive) {
            stageCoordinator.mMixedHandler.mPipHandler.getClass();
            if (transitionRequestInfo.getType() == 10 && ((transitionRequestInfo.getTriggerTask() != null && stageCoordinator.getSplitPosition(transitionRequestInfo.getTriggerTask().taskId) != -1) || stageTaskListener.mChildrenTaskInfo.size() == 0 || stageCoordinator.mSideStage.mChildrenTaskInfo.size() == 0)) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -2686821093972033600L, 0, null);
                }
                if (transitionRequestInfo.getRemoteTransition() != null) {
                    throw new IllegalStateException("Unexpected remote transition inpip-enter-from-split request");
                }
                this.mActiveTransitions.add(createDefaultMixedTransition(iBinder, 1));
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                this.mPipHandler.augmentRequest(iBinder, transitionRequestInfo, windowContainerTransaction);
                StageCoordinator stageCoordinator2 = this.mSplitHandler;
                stageCoordinator2.getClass();
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 2903785196408028946L, 1, Long.valueOf(transitionRequestInfo.getDebugId()));
                }
                ActivityManager.RunningTaskInfo triggerTask = transitionRequestInfo.getTriggerTask();
                if (triggerTask == null || triggerTask.displayId == stageCoordinator2.mDisplayId) {
                    int type = transitionRequestInfo.getType();
                    StageTaskListener stageTaskListener2 = stageCoordinator2.mMainStage;
                    if (stageTaskListener2.mIsActive && !TransitionUtil.isOpeningType(type)) {
                        int size = stageTaskListener2.mChildrenTaskInfo.size();
                        StageTaskListener stageTaskListener3 = stageCoordinator2.mSideStage;
                        if (size == 0 || stageTaskListener3.mChildrenTaskInfo.size() == 0) {
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 5247503639166465764L, 5, Long.valueOf(stageTaskListener2.mChildrenTaskInfo.size()), Long.valueOf(stageTaskListener3.mChildrenTaskInfo.size()));
                            }
                            if (triggerTask != null) {
                                stageCoordinator2.mRecentTasks.ifPresent(new StageCoordinator$$ExternalSyntheticLambda8(1, triggerTask));
                                stageCoordinator2.logExit(9);
                            }
                            if (stageCoordinator2.isSplitScreenVisible()) {
                                if (stageTaskListener2.mChildrenTaskInfo.size() == 0 || stageTaskListener3.mChildrenTaskInfo.size() != 0) {
                                    if (stageTaskListener3.mChildrenTaskInfo.size() != 0 && stageTaskListener2.mChildrenTaskInfo.size() == 0) {
                                        i = 1;
                                    }
                                }
                                stageCoordinator2.prepareExitSplitScreen(i, windowContainerTransaction);
                            }
                            i = -1;
                            stageCoordinator2.prepareExitSplitScreen(i, windowContainerTransaction);
                        }
                    }
                }
                return windowContainerTransaction;
            }
        }
        if (transitionRequestInfo.getType() == 10 && (transitionRequestInfo.getFlags() & 512) != 0 && this.mActivityEmbeddingController != null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 8328820818454394116L, 0, null);
            }
            this.mActiveTransitions.add(createDefaultMixedTransition(iBinder, 9));
            return new WindowContainerTransaction();
        }
        RemoteTransition remoteTransition = transitionRequestInfo.getRemoteTransition();
        Transitions transitions = this.mPlayer;
        if (remoteTransition != null && TransitionUtil.isOpeningType(transitionRequestInfo.getType()) && (transitionRequestInfo.getTriggerTask() == null || (transitionRequestInfo.getTriggerTask().topActivityType != 2 && transitionRequestInfo.getTriggerTask().topActivityType != 3))) {
            Pair dispatchRequest = transitions.dispatchRequest(iBinder, transitionRequestInfo, this);
            if (dispatchRequest == null) {
                return null;
            }
            DefaultMixedTransition createDefaultMixedTransition = createDefaultMixedTransition(iBinder, 3);
            createDefaultMixedTransition.mLeftoversHandler = (Transitions.TransitionHandler) dispatchRequest.first;
            this.mActiveTransitions.add(createDefaultMixedTransition);
            Transitions.TransitionHandler transitionHandler = createDefaultMixedTransition.mLeftoversHandler;
            RemoteTransitionHandler remoteTransitionHandler = transitions.mRemoteTransitionHandler;
            if (transitionHandler != remoteTransitionHandler) {
                createDefaultMixedTransition.mHasRequestToRemote = true;
                remoteTransitionHandler.handleRequest(iBinder, transitionRequestInfo);
            }
            return (WindowContainerTransaction) dispatchRequest.second;
        }
        if (!this.mSplitHandler.isSplitScreenVisible() || !TransitionUtil.isOpeningType(transitionRequestInfo.getType()) || transitionRequestInfo.getTriggerTask() == null || transitionRequestInfo.getTriggerTask().getWindowingMode() != 1 || transitionRequestInfo.getTriggerTask().getActivityType() != 2) {
            if (this.mUnfoldHandler == null || !UnfoldTransitionHandler.shouldPlayUnfoldAnimation(transitionRequestInfo)) {
                return null;
            }
            WindowContainerTransaction handleRequest = this.mUnfoldHandler.handleRequest(iBinder, transitionRequestInfo);
            if (handleRequest != null) {
                this.mActiveTransitions.add(createDefaultMixedTransition(iBinder, 8));
            }
            return handleRequest;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 533703778315874542L, 0, null);
        }
        Pair dispatchRequest2 = transitions.dispatchRequest(iBinder, transitionRequestInfo, this);
        if (dispatchRequest2 == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -1432596578455044936L, 0, null);
            }
            dispatchRequest2 = new Pair(transitions.mRemoteTransitionHandler, new WindowContainerTransaction());
        }
        RecentsMixedTransition createRecentsMixedTransition = createRecentsMixedTransition(iBinder, 4);
        createRecentsMixedTransition.mLeftoversHandler = (Transitions.TransitionHandler) dispatchRequest2.first;
        this.mActiveTransitions.add(createRecentsMixedTransition);
        return (WindowContainerTransaction) dispatchRequest2.second;
    }

    public final boolean isIntentInPip(PendingIntent pendingIntent) {
        PipTransitionController pipTransitionController = this.mPipHandler;
        if (pipTransitionController != null) {
            return pipTransitionController.isPackageActiveInPip(SplitScreenUtils.getPackageName(pendingIntent.getIntent()));
        }
        return false;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        for (int i = 0; i < this.mActiveTransitions.size(); i++) {
            if (((MixedTransition) this.mActiveTransitions.get(i)).mTransition == iBinder2) {
                MixedTransition mixedTransition = (MixedTransition) this.mActiveTransitions.get(i);
                if (mixedTransition.mInFlightSubAnimations <= 0) {
                    return;
                } else {
                    mixedTransition.mergeAnimation(iBinder, transitionInfo, transaction, iBinder2, transitionFinishCallback);
                }
            }
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
        MixedTransition mixedTransition;
        int size = this.mActiveTransitions.size() - 1;
        while (true) {
            if (size < 0) {
                mixedTransition = null;
                break;
            } else {
                if (((MixedTransition) this.mActiveTransitions.get(size)).mTransition == iBinder) {
                    mixedTransition = (MixedTransition) this.mActiveTransitions.remove(size);
                    break;
                }
                size--;
            }
        }
        if (mixedTransition != null) {
            mixedTransition.onTransitionConsumed(iBinder, z, transaction);
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        MixedTransition mixedTransition;
        int size = this.mActiveTransitions.size() - 1;
        while (true) {
            if (size < 0) {
                mixedTransition = null;
                break;
            }
            if (((MixedTransition) this.mActiveTransitions.get(size)).mTransition == iBinder) {
                mixedTransition = (MixedTransition) this.mActiveTransitions.get(size);
                break;
            }
            size--;
        }
        if (KeyguardTransitionHandler.handles(transitionInfo)) {
            if (mixedTransition == null || mixedTransition.mType == 5) {
                PipTransitionController pipTransitionController = this.mPipHandler;
                if (pipTransitionController != null) {
                    pipTransitionController.syncPipSurfaceState(transitionInfo, transaction, transaction2);
                }
            } else {
                DefaultMixedTransition createDefaultMixedTransition = createDefaultMixedTransition(iBinder, 5);
                this.mActiveTransitions.add(createDefaultMixedTransition);
                DefaultMixedHandler$$ExternalSyntheticLambda4 defaultMixedHandler$$ExternalSyntheticLambda4 = new DefaultMixedHandler$$ExternalSyntheticLambda4(this, createDefaultMixedTransition, transitionFinishCallback, 1);
                PipTransitionController pipTransitionController2 = this.mPipHandler;
                if (createDefaultMixedTransition.mFinishT == null) {
                    createDefaultMixedTransition.mFinishT = transaction2;
                    createDefaultMixedTransition.mFinishCB = defaultMixedHandler$$ExternalSyntheticLambda4;
                }
                if (pipTransitionController2 != null) {
                    pipTransitionController2.syncPipSurfaceState(transitionInfo, transaction, transaction2);
                }
                if (createDefaultMixedTransition.startSubAnimation(this.mKeyguardHandler, transitionInfo, transaction, transaction2)) {
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[3]) {
                        ProtoLogImpl_411527699.w(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 2769322071729320114L, 0, null);
                    }
                    this.mActiveTransitions.remove(mixedTransition);
                    mixedTransition.onTransitionConsumed(iBinder, false, null);
                    return true;
                }
                this.mActiveTransitions.remove(createDefaultMixedTransition);
            }
        }
        if (mixedTransition == null) {
            return false;
        }
        boolean startAnimation = mixedTransition.startAnimation(iBinder, transitionInfo, transaction, transaction2, new DefaultMixedHandler$$ExternalSyntheticLambda4(this, mixedTransition, transitionFinishCallback, 4));
        if (!startAnimation) {
            this.mActiveTransitions.remove(mixedTransition);
        }
        return startAnimation;
    }
}

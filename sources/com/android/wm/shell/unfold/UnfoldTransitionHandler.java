package com.android.wm.shell.unfold;

import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.os.IBinder;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.unfold.ShellUnfoldProgressProvider;
import com.android.wm.shell.unfold.animation.FullscreenUnfoldTaskAnimator;
import com.android.wm.shell.unfold.animation.SplitTaskUnfoldAnimator;
import com.android.wm.shell.unfold.animation.UnfoldTaskAnimator;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UnfoldTransitionHandler implements Transitions.TransitionHandler, ShellUnfoldProgressProvider.UnfoldListener {
    public boolean mAnimationFinished = false;
    public final List mAnimators;
    public final ShellExecutor mExecutor;
    public Transitions.TransitionFinishCallback mFinishCallback;
    public final TransactionPool mTransactionPool;
    public IBinder mTransition;
    public final Transitions mTransitions;
    public final ShellUnfoldProgressProvider mUnfoldProgressProvider;

    public UnfoldTransitionHandler(ShellInit shellInit, ShellUnfoldProgressProvider shellUnfoldProgressProvider, FullscreenUnfoldTaskAnimator fullscreenUnfoldTaskAnimator, SplitTaskUnfoldAnimator splitTaskUnfoldAnimator, TransactionPool transactionPool, ShellExecutor shellExecutor, Transitions transitions) {
        ArrayList arrayList = new ArrayList();
        this.mAnimators = arrayList;
        this.mUnfoldProgressProvider = shellUnfoldProgressProvider;
        this.mTransitions = transitions;
        this.mTransactionPool = transactionPool;
        this.mExecutor = shellExecutor;
        arrayList.add(splitTaskUnfoldAnimator);
        arrayList.add(fullscreenUnfoldTaskAnimator);
        if (shellUnfoldProgressProvider == ShellUnfoldProgressProvider.NO_PROVIDER || !Transitions.ENABLE_SHELL_TRANSITIONS) {
            return;
        }
        shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.unfold.UnfoldTransitionHandler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UnfoldTransitionHandler unfoldTransitionHandler = UnfoldTransitionHandler.this;
                for (int i = 0; i < ((ArrayList) unfoldTransitionHandler.mAnimators).size(); i++) {
                    ((UnfoldTaskAnimator) ((ArrayList) unfoldTransitionHandler.mAnimators).get(i)).init();
                }
                unfoldTransitionHandler.mTransitions.addHandler(unfoldTransitionHandler);
                unfoldTransitionHandler.mUnfoldProgressProvider.addListener(unfoldTransitionHandler.mExecutor, unfoldTransitionHandler);
            }
        }, this);
    }

    public static boolean shouldPlayUnfoldAnimation(TransitionRequestInfo transitionRequestInfo) {
        if (!ValueAnimator.areAnimatorsEnabled() || transitionRequestInfo.getType() != 6 || transitionRequestInfo.getDisplayChange() == null) {
            return false;
        }
        TransitionRequestInfo.DisplayChange displayChange = transitionRequestInfo.getDisplayChange();
        if (!displayChange.isPhysicalDisplayChanged() || displayChange.getStartAbsBounds() == null || displayChange.getEndAbsBounds() == null) {
            return false;
        }
        return displayChange.getEndAbsBounds().height() * displayChange.getEndAbsBounds().width() > displayChange.getStartAbsBounds().height() * displayChange.getStartAbsBounds().width();
    }

    public final void finishTransitionIfNeeded() {
        if (this.mFinishCallback == null) {
            return;
        }
        for (int i = 0; i < ((ArrayList) this.mAnimators).size(); i++) {
            UnfoldTaskAnimator unfoldTaskAnimator = (UnfoldTaskAnimator) ((ArrayList) this.mAnimators).get(i);
            unfoldTaskAnimator.clearTasks();
            unfoldTaskAnimator.stop();
        }
        this.mFinishCallback.onTransitionFinished(null);
        this.mFinishCallback = null;
        this.mTransition = null;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        if (!shouldPlayUnfoldAnimation(transitionRequestInfo)) {
            return null;
        }
        this.mTransition = iBinder;
        return new WindowContainerTransaction();
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        if (transitionInfo.getType() == 6 && (transitionInfo.getFlags() & 14592) == 0) {
            for (int i = 0; i < transitionInfo.getChanges().size(); i++) {
                ActivityManager.RunningTaskInfo taskInfo = ((TransitionInfo.Change) transitionInfo.getChanges().get(i)).getTaskInfo();
                if (taskInfo != null && taskInfo.configuration.windowConfiguration.isAlwaysOnTop()) {
                    return;
                }
            }
            transaction.apply();
            transitionFinishCallback.onTransitionFinished(null);
        }
    }

    @Override // com.android.wm.shell.unfold.ShellUnfoldProgressProvider.UnfoldListener
    public final void onFoldStateChanged(boolean z) {
        if (z) {
            this.mAnimationFinished = false;
            finishTransitionIfNeeded();
        }
    }

    @Override // com.android.wm.shell.unfold.ShellUnfoldProgressProvider.UnfoldListener
    public final void onStateChangeFinished() {
        this.mAnimationFinished = true;
        finishTransitionIfNeeded();
    }

    @Override // com.android.wm.shell.unfold.ShellUnfoldProgressProvider.UnfoldListener
    public final void onStateChangeProgress(float f) {
        TransactionPool transactionPool;
        if (this.mTransition == null) {
            return;
        }
        SurfaceControl.Transaction transaction = null;
        int i = 0;
        while (true) {
            int size = ((ArrayList) this.mAnimators).size();
            transactionPool = this.mTransactionPool;
            if (i >= size) {
                break;
            }
            UnfoldTaskAnimator unfoldTaskAnimator = (UnfoldTaskAnimator) ((ArrayList) this.mAnimators).get(i);
            if (unfoldTaskAnimator.hasActiveTasks()) {
                if (transaction == null) {
                    transaction = transactionPool.acquire();
                }
                unfoldTaskAnimator.applyAnimationProgress(f, transaction);
            }
            i++;
        }
        if (transaction != null) {
            transaction.apply();
            transactionPool.release(transaction);
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        if (ValueAnimator.areAnimatorsEnabled() && (transitionInfo.getFlags() & 16384) != 0) {
            int i = 0;
            while (true) {
                if (i >= transitionInfo.getChanges().size()) {
                    break;
                }
                TransitionInfo.Change change = (TransitionInfo.Change) transitionInfo.getChanges().get(i);
                if ((change.getFlags() & 32) != 0 && change.getEndAbsBounds() != null && change.getStartAbsBounds() != null) {
                    if (change.getEndAbsBounds().height() * change.getEndAbsBounds().width() > change.getStartAbsBounds().height() * change.getStartAbsBounds().width()) {
                        if (iBinder != this.mTransition) {
                            this.mTransition = iBinder;
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -7309021625482923802L, 0, null);
                            }
                        }
                    }
                }
                i++;
            }
        }
        if (iBinder != this.mTransition) {
            return false;
        }
        for (int i2 = 0; i2 < ((ArrayList) this.mAnimators).size(); i2++) {
            final UnfoldTaskAnimator unfoldTaskAnimator = (UnfoldTaskAnimator) ((ArrayList) this.mAnimators).get(i2);
            unfoldTaskAnimator.clearTasks();
            transitionInfo.getChanges().forEach(new Consumer() { // from class: com.android.wm.shell.unfold.UnfoldTransitionHandler$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    UnfoldTaskAnimator unfoldTaskAnimator2 = UnfoldTaskAnimator.this;
                    TransitionInfo.Change change2 = (TransitionInfo.Change) obj;
                    if (change2.getTaskInfo() != null && ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 7334717703168078630L, 0, String.valueOf(change2.getTaskInfo()), String.valueOf(TransitionInfo.modeToString(change2.getMode())), String.valueOf(unfoldTaskAnimator2.isApplicableTask(change2.getTaskInfo())));
                    }
                    if (change2.getTaskInfo() != null) {
                        if ((change2.getMode() == 6 || TransitionUtil.isOpeningType(change2.getMode())) && unfoldTaskAnimator2.isApplicableTask(change2.getTaskInfo())) {
                            unfoldTaskAnimator2.onTaskAppeared(change2.getTaskInfo(), change2.getLeash());
                        }
                    }
                }
            });
            if (unfoldTaskAnimator.hasActiveTasks()) {
                unfoldTaskAnimator.prepareStartTransaction(transaction);
                unfoldTaskAnimator.prepareFinishTransaction(transaction2);
                unfoldTaskAnimator.start();
            }
        }
        transaction.apply();
        this.mFinishCallback = transitionFinishCallback;
        if (this.mAnimationFinished) {
            finishTransitionIfNeeded();
        }
        return true;
    }
}

package com.android.wm.shell.splitscreen;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.IBinder;
import android.view.SurfaceControl;
import android.window.RemoteTransition;
import android.window.WindowContainerTransaction;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.systemui.accessibility.floatingmenu.MenuAnimationController$$ExternalSyntheticLambda2;
import com.android.systemui.wmshell.WMShell;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.split.DividerView;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransactionPool;
import com.android.wm.shell.shared.animation.Interpolators;
import com.android.wm.shell.splitscreen.SplitScreenTransitions;
import com.android.wm.shell.transition.OneShotRemoteHandler;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplitScreenTransitions {
    public SurfaceControl.Transaction mFinishTransaction;
    public final Runnable mOnFinish;
    public WMShell.AnonymousClass9 mSplitInvocationListener;
    public Executor mSplitInvocationListenerExecutor;
    public final StageCoordinator mStageCoordinator;
    public final TransactionPool mTransactionPool;
    public final Transitions mTransitions;
    public DismissSession mPendingDismiss = null;
    public EnterSession mPendingEnter = null;
    public TransitSession mPendingResize = null;
    public TransitSession mPendingRemotePassthrough = null;
    public IBinder mAnimatingTransition = null;
    public OneShotRemoteHandler mActiveRemoteHandler = null;
    public final SplitScreenTransitions$$ExternalSyntheticLambda2 mRemoteFinishCB = new Transitions.TransitionFinishCallback() { // from class: com.android.wm.shell.splitscreen.SplitScreenTransitions$$ExternalSyntheticLambda2
        @Override // com.android.wm.shell.transition.Transitions.TransitionFinishCallback
        public final void onTransitionFinished(WindowContainerTransaction windowContainerTransaction) {
            SplitScreenTransitions.this.onFinish(windowContainerTransaction);
        }
    };
    public final ArrayList mAnimations = new ArrayList();
    public Transitions.TransitionFinishCallback mFinishCallback = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.splitscreen.SplitScreenTransitions$1, reason: invalid class name */
    public final class AnonymousClass1 extends AnimatorListenerAdapter {
        public final /* synthetic */ float val$end;
        public final /* synthetic */ SurfaceControl val$leash;
        public final /* synthetic */ ValueAnimator val$va;

        public AnonymousClass1(SurfaceControl surfaceControl, float f, ValueAnimator valueAnimator) {
            this.val$leash = surfaceControl;
            this.val$end = f;
            this.val$va = valueAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            SurfaceControl.Transaction acquire = SplitScreenTransitions.this.mTransactionPool.acquire();
            acquire.setAlpha(this.val$leash, this.val$end);
            acquire.apply();
            SplitScreenTransitions.this.mTransactionPool.release(acquire);
            ShellExecutor shellExecutor = SplitScreenTransitions.this.mTransitions.mMainExecutor;
            final ValueAnimator valueAnimator = this.val$va;
            ((HandlerExecutor) shellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.splitscreen.SplitScreenTransitions$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    SplitScreenTransitions.AnonymousClass1 anonymousClass1 = SplitScreenTransitions.AnonymousClass1.this;
                    SplitScreenTransitions.this.mAnimations.remove(valueAnimator);
                    SplitScreenTransitions.this.onFinish(null);
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DismissSession extends TransitSession {
        public final int mDismissTop;
        public final int mReason;

        public DismissSession(SplitScreenTransitions splitScreenTransitions, IBinder iBinder, int i, int i2) {
            super(splitScreenTransitions, iBinder, null, null);
            this.mReason = i;
            this.mDismissTop = i2;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EnterSession extends TransitSession {
        public final boolean mResizeAnim;

        public EnterSession(SplitScreenTransitions splitScreenTransitions, IBinder iBinder, RemoteTransition remoteTransition, int i, boolean z) {
            super(splitScreenTransitions, iBinder, null, null, remoteTransition, i);
            this.mResizeAnim = z;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class TransitSession {
        public boolean mCanceled;
        public final StageCoordinator$$ExternalSyntheticLambda11 mConsumedCallback;
        public final int mExtraTransitType;
        public TransitionFinishedCallback mFinishedCallback;
        public final OneShotRemoteHandler mRemoteHandler;
        public final IBinder mTransition;

        public TransitSession(SplitScreenTransitions splitScreenTransitions, IBinder iBinder, StageCoordinator$$ExternalSyntheticLambda11 stageCoordinator$$ExternalSyntheticLambda11, StageCoordinator$$ExternalSyntheticLambda11 stageCoordinator$$ExternalSyntheticLambda112) {
            this(splitScreenTransitions, iBinder, stageCoordinator$$ExternalSyntheticLambda11, stageCoordinator$$ExternalSyntheticLambda112, null, 0);
        }

        public final void onConsumed() {
            DividerView dividerView;
            StageCoordinator$$ExternalSyntheticLambda11 stageCoordinator$$ExternalSyntheticLambda11 = this.mConsumedCallback;
            if (stageCoordinator$$ExternalSyntheticLambda11 == null || (dividerView = stageCoordinator$$ExternalSyntheticLambda11.f$0.mSplitLayout.mSplitWindowManager.mDividerView) == null) {
                return;
            }
            dividerView.setInteractive("onSplitResizeConsumed", true, false);
        }

        public TransitSession(SplitScreenTransitions splitScreenTransitions, IBinder iBinder, StageCoordinator$$ExternalSyntheticLambda11 stageCoordinator$$ExternalSyntheticLambda11, StageCoordinator$$ExternalSyntheticLambda11 stageCoordinator$$ExternalSyntheticLambda112, RemoteTransition remoteTransition, int i) {
            this.mTransition = iBinder;
            this.mConsumedCallback = stageCoordinator$$ExternalSyntheticLambda11;
            this.mFinishedCallback = stageCoordinator$$ExternalSyntheticLambda112;
            if (remoteTransition != null) {
                OneShotRemoteHandler oneShotRemoteHandler = new OneShotRemoteHandler(splitScreenTransitions.mTransitions.mMainExecutor, remoteTransition);
                this.mRemoteHandler = oneShotRemoteHandler;
                oneShotRemoteHandler.mTransition = iBinder;
            }
            this.mExtraTransitType = i;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface TransitionFinishedCallback {
        void onFinished(WindowContainerTransaction windowContainerTransaction, SurfaceControl.Transaction transaction);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.android.wm.shell.splitscreen.SplitScreenTransitions$$ExternalSyntheticLambda2] */
    public SplitScreenTransitions(TransactionPool transactionPool, Transitions transitions, Runnable runnable, StageCoordinator stageCoordinator) {
        this.mTransactionPool = transactionPool;
        this.mTransitions = transitions;
        this.mOnFinish = runnable;
        this.mStageCoordinator = stageCoordinator;
    }

    public final TransitSession getPendingTransition(IBinder iBinder) {
        if (isPendingEnter(iBinder)) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 3929944391949955522L, 0, null);
            }
            return this.mPendingEnter;
        }
        if (isPendingDismiss(iBinder)) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -1314223692559991014L, 0, null);
            }
            return this.mPendingDismiss;
        }
        if (isPendingResize(iBinder)) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -7711313386716908935L, 0, null);
            }
            return this.mPendingResize;
        }
        if (!isPendingPassThrough(iBinder)) {
            return null;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 1627459327008717711L, 0, null);
        }
        return this.mPendingRemotePassthrough;
    }

    public final boolean isPendingDismiss(IBinder iBinder) {
        DismissSession dismissSession = this.mPendingDismiss;
        return dismissSession != null && dismissSession.mTransition == iBinder;
    }

    public final boolean isPendingEnter(IBinder iBinder) {
        EnterSession enterSession = this.mPendingEnter;
        return enterSession != null && enterSession.mTransition == iBinder;
    }

    public final boolean isPendingPassThrough(IBinder iBinder) {
        TransitSession transitSession = this.mPendingRemotePassthrough;
        return transitSession != null && transitSession.mTransition == iBinder;
    }

    public final boolean isPendingResize(IBinder iBinder) {
        TransitSession transitSession = this.mPendingResize;
        return transitSession != null && transitSession.mTransition == iBinder;
    }

    public final void onFinish(WindowContainerTransaction windowContainerTransaction) {
        if (this.mAnimations.isEmpty()) {
            if (windowContainerTransaction == null) {
                windowContainerTransaction = new WindowContainerTransaction();
            }
            if (isPendingEnter(this.mAnimatingTransition)) {
                EnterSession enterSession = this.mPendingEnter;
                SurfaceControl.Transaction transaction = this.mFinishTransaction;
                TransitionFinishedCallback transitionFinishedCallback = enterSession.mFinishedCallback;
                if (transitionFinishedCallback != null) {
                    transitionFinishedCallback.onFinished(windowContainerTransaction, transaction);
                }
                this.mPendingEnter = null;
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 619325938945047095L, 0, null);
                }
            } else if (isPendingDismiss(this.mAnimatingTransition)) {
                DismissSession dismissSession = this.mPendingDismiss;
                SurfaceControl.Transaction transaction2 = this.mFinishTransaction;
                TransitionFinishedCallback transitionFinishedCallback2 = dismissSession.mFinishedCallback;
                if (transitionFinishedCallback2 != null) {
                    transitionFinishedCallback2.onFinished(windowContainerTransaction, transaction2);
                }
                this.mPendingDismiss = null;
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 3953471079119598503L, 0, null);
                }
            } else if (isPendingResize(this.mAnimatingTransition)) {
                TransitSession transitSession = this.mPendingResize;
                SurfaceControl.Transaction transaction3 = this.mFinishTransaction;
                TransitionFinishedCallback transitionFinishedCallback3 = transitSession.mFinishedCallback;
                if (transitionFinishedCallback3 != null) {
                    transitionFinishedCallback3.onFinished(windowContainerTransaction, transaction3);
                }
                this.mPendingResize = null;
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -5975192824424219031L, 0, null);
                }
            } else if (isPendingPassThrough(this.mAnimatingTransition)) {
                TransitSession transitSession2 = this.mPendingRemotePassthrough;
                SurfaceControl.Transaction transaction4 = this.mFinishTransaction;
                TransitionFinishedCallback transitionFinishedCallback4 = transitSession2.mFinishedCallback;
                if (transitionFinishedCallback4 != null) {
                    transitionFinishedCallback4.onFinished(windowContainerTransaction, transaction4);
                }
                this.mPendingRemotePassthrough = null;
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, 6666552884816591818L, 0, null);
                }
            }
            this.mActiveRemoteHandler = null;
            this.mAnimatingTransition = null;
            this.mOnFinish.run();
            Transitions.TransitionFinishCallback transitionFinishCallback = this.mFinishCallback;
            if (transitionFinishCallback != null) {
                this.mFinishCallback = null;
                transitionFinishCallback.onTransitionFinished(windowContainerTransaction);
            }
        }
    }

    public final void setDismissTransition(IBinder iBinder, int i, int i2) {
        this.mPendingDismiss = new DismissSession(this, iBinder, i2, i);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -2018661040764077535L, 0, String.valueOf(SplitScreenController.exitReasonToString(i2)), String.valueOf(SplitScreen.stageTypeToString(i)));
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -8087615531821482454L, 0, String.valueOf(SplitScreenController.exitReasonToString(i2)), String.valueOf(SplitScreen.stageTypeToString(i)));
        }
    }

    public final void setEnterTransition(IBinder iBinder, RemoteTransition remoteTransition, int i, boolean z) {
        this.mPendingEnter = new EnterSession(this, iBinder, remoteTransition, i, z);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 7174451032024956397L, 0, null);
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_SPLIT_SCREEN_enabled[0]) {
            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_SPLIT_SCREEN, -2436541215950487753L, 13, Long.valueOf(i), Boolean.valueOf(z));
        }
    }

    public final void startDismissTransition(WindowContainerTransaction windowContainerTransaction, Transitions.TransitionHandler transitionHandler, int i, int i2) {
        if (this.mPendingDismiss == null) {
            setDismissTransition(this.mTransitions.startTransition(i2 == 4 ? 1006 : 1007, windowContainerTransaction, transitionHandler), i, i2);
        } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -954926772565557968L, 0, String.valueOf(SplitScreenController.exitReasonToString(i2)));
        }
    }

    public final void startEnterTransition(WindowContainerTransaction windowContainerTransaction, RemoteTransition remoteTransition, Transitions.TransitionHandler transitionHandler, int i, boolean z) {
        if (this.mPendingEnter != null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -6020188994636268501L, 0, null);
            }
        } else {
            Executor executor = this.mSplitInvocationListenerExecutor;
            if (executor != null && this.mSplitInvocationListener != null) {
                executor.execute(new SplitScreenTransitions$$ExternalSyntheticLambda0(2, this));
            }
            setEnterTransition(this.mTransitions.startTransition(3, windowContainerTransaction, transitionHandler), remoteTransition, i, z);
        }
    }

    public final void startFadeAnimation(final SurfaceControl surfaceControl) {
        final float f = 1.0f;
        final float f2 = 0.0f;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat.setDuration(133L);
        ofFloat.setInterpolator(Interpolators.ALPHA_OUT);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.splitscreen.SplitScreenTransitions$$ExternalSyntheticLambda5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                SplitScreenTransitions splitScreenTransitions = SplitScreenTransitions.this;
                SurfaceControl surfaceControl2 = surfaceControl;
                float f3 = f;
                float f4 = f2;
                splitScreenTransitions.getClass();
                float animatedFraction = valueAnimator.getAnimatedFraction();
                TransactionPool transactionPool = splitScreenTransitions.mTransactionPool;
                SurfaceControl.Transaction acquire = transactionPool.acquire();
                acquire.setAlpha(surfaceControl2, (f4 * animatedFraction) + ((1.0f - animatedFraction) * f3));
                acquire.apply();
                transactionPool.release(acquire);
            }
        });
        ofFloat.addListener(new AnonymousClass1(surfaceControl, 0.0f, ofFloat));
        this.mAnimations.add(ofFloat);
        ((HandlerExecutor) this.mTransitions.mAnimExecutor).execute(new MenuAnimationController$$ExternalSyntheticLambda2(ofFloat));
    }

    public final void startFullscreenTransition(WindowContainerTransaction windowContainerTransaction, RemoteTransition remoteTransition) {
        Transitions transitions = this.mTransitions;
        OneShotRemoteHandler oneShotRemoteHandler = new OneShotRemoteHandler(transitions.mMainExecutor, remoteTransition);
        oneShotRemoteHandler.mTransition = transitions.startTransition(1, windowContainerTransaction, oneShotRemoteHandler);
    }
}

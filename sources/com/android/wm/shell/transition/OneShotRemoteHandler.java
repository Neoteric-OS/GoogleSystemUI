package com.android.wm.shell.transition;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.SurfaceControl;
import android.window.IRemoteTransitionFinishedCallback;
import android.window.RemoteTransition;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.transition.OneShotRemoteHandler;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OneShotRemoteHandler implements Transitions.TransitionHandler {
    public final ShellExecutor mMainExecutor;
    public RemoteTransition mRemote;
    public IBinder mTransition = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.transition.OneShotRemoteHandler$2, reason: invalid class name */
    public final class AnonymousClass2 extends IRemoteTransitionFinishedCallback.Stub {
        public final /* synthetic */ Transitions.TransitionFinishCallback val$finishCallback;
        public final /* synthetic */ SurfaceControl.Transaction val$finishTransaction;
        public final /* synthetic */ TransitionInfo val$info;
        public final /* synthetic */ OneShotRemoteHandler$$ExternalSyntheticLambda0 val$remoteDied;

        public AnonymousClass2(TransitionInfo transitionInfo, OneShotRemoteHandler$$ExternalSyntheticLambda0 oneShotRemoteHandler$$ExternalSyntheticLambda0, SurfaceControl.Transaction transaction, Transitions.TransitionFinishCallback transitionFinishCallback) {
            this.val$info = transitionInfo;
            this.val$remoteDied = oneShotRemoteHandler$$ExternalSyntheticLambda0;
            this.val$finishTransaction = transaction;
            this.val$finishCallback = transitionFinishCallback;
        }

        public final void onTransitionFinished(final WindowContainerTransaction windowContainerTransaction, SurfaceControl.Transaction transaction) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -1920116838089606609L, 4, String.valueOf(OneShotRemoteHandler.this.mRemote), Long.valueOf(this.val$info.getDebugId()));
            }
            if (OneShotRemoteHandler.this.mRemote.asBinder() != null) {
                OneShotRemoteHandler.this.mRemote.asBinder().unlinkToDeath(this.val$remoteDied, 0);
            }
            SurfaceControl.Transaction transaction2 = this.val$finishTransaction;
            if (transaction2 != null && transaction != null) {
                transaction2.merge(transaction);
            }
            ShellExecutor shellExecutor = OneShotRemoteHandler.this.mMainExecutor;
            final Transitions.TransitionFinishCallback transitionFinishCallback = this.val$finishCallback;
            ((HandlerExecutor) shellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.transition.OneShotRemoteHandler$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    OneShotRemoteHandler.AnonymousClass2 anonymousClass2 = OneShotRemoteHandler.AnonymousClass2.this;
                    Transitions.TransitionFinishCallback transitionFinishCallback2 = transitionFinishCallback;
                    WindowContainerTransaction windowContainerTransaction2 = windowContainerTransaction;
                    anonymousClass2.getClass();
                    transitionFinishCallback2.onTransitionFinished(windowContainerTransaction2);
                    OneShotRemoteHandler.this.mRemote = null;
                }
            });
        }
    }

    public OneShotRemoteHandler(ShellExecutor shellExecutor, RemoteTransition remoteTransition) {
        this.mMainExecutor = shellExecutor;
        this.mRemote = remoteTransition;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        RemoteTransition remoteTransition = transitionRequestInfo.getRemoteTransition();
        if ((remoteTransition != null ? remoteTransition.getRemoteTransition() : null) != this.mRemote.getRemoteTransition()) {
            return null;
        }
        this.mTransition = iBinder;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 7971331547267873781L, 0, String.valueOf(iBinder), String.valueOf(remoteTransition));
        }
        return new WindowContainerTransaction();
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void mergeAnimation(IBinder iBinder, final TransitionInfo transitionInfo, final SurfaceControl.Transaction transaction, IBinder iBinder2, final Transitions.TransitionFinishCallback transitionFinishCallback) {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -8058552917378778392L, 4, String.valueOf(this.mRemote), Long.valueOf(transitionInfo.getDebugId()));
        }
        IRemoteTransitionFinishedCallback.Stub stub = new IRemoteTransitionFinishedCallback.Stub() { // from class: com.android.wm.shell.transition.OneShotRemoteHandler.1
            public final void onTransitionFinished(final WindowContainerTransaction windowContainerTransaction, SurfaceControl.Transaction transaction2) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -705170416625176567L, 4, String.valueOf(OneShotRemoteHandler.this.mRemote), Long.valueOf(transitionInfo.getDebugId()));
                }
                transaction.clear();
                ShellExecutor shellExecutor = OneShotRemoteHandler.this.mMainExecutor;
                final Transitions.TransitionFinishCallback transitionFinishCallback2 = transitionFinishCallback;
                ((HandlerExecutor) shellExecutor).execute(new Runnable() { // from class: com.android.wm.shell.transition.OneShotRemoteHandler$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        Transitions.TransitionFinishCallback.this.onTransitionFinished(windowContainerTransaction);
                    }
                });
            }
        };
        try {
            SurfaceControl.Transaction copyIfLocal = RemoteTransitionHandler.copyIfLocal(transaction, this.mRemote.getRemoteTransition());
            this.mRemote.getRemoteTransition().mergeAnimation(iBinder, copyIfLocal == transaction ? transitionInfo : transitionInfo.localRemoteCopy(), copyIfLocal, iBinder2, stub);
        } catch (RemoteException e) {
            Log.e("ShellTransitions", "Error merging remote transition.", e);
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
        try {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[0]) {
                ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -3477481236100005912L, 0, String.valueOf(this.mRemote));
            }
            this.mRemote.getRemoteTransition().onTransitionConsumed(iBinder, z);
        } catch (RemoteException e) {
            Log.e("ShellTransitions", "Error calling onTransitionConsumed()", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [android.os.IBinder$DeathRecipient, com.android.wm.shell.transition.OneShotRemoteHandler$$ExternalSyntheticLambda0] */
    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, final Transitions.TransitionFinishCallback transitionFinishCallback) {
        if (this.mTransition != iBinder) {
            return false;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 4224371256409735028L, 4, String.valueOf(this.mRemote), Long.valueOf(transitionInfo.getDebugId()));
        }
        ?? r12 = new IBinder.DeathRecipient() { // from class: com.android.wm.shell.transition.OneShotRemoteHandler$$ExternalSyntheticLambda0
            @Override // android.os.IBinder.DeathRecipient
            public final void binderDied() {
                OneShotRemoteHandler oneShotRemoteHandler = OneShotRemoteHandler.this;
                final Transitions.TransitionFinishCallback transitionFinishCallback2 = transitionFinishCallback;
                oneShotRemoteHandler.getClass();
                Log.e("ShellTransitions", "Remote transition died, finishing");
                ((HandlerExecutor) oneShotRemoteHandler.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.transition.OneShotRemoteHandler$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Transitions.TransitionFinishCallback.this.onTransitionFinished(null);
                    }
                });
            }
        };
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(transitionInfo, r12, transaction2, transitionFinishCallback);
        Transitions.setRunningRemoteTransitionDelegate(this.mRemote.getAppThread());
        try {
            if (this.mRemote.asBinder() != null) {
                this.mRemote.asBinder().linkToDeath(r12, 0);
            }
            SurfaceControl.Transaction copyIfLocal = RemoteTransitionHandler.copyIfLocal(transaction, this.mRemote.getRemoteTransition());
            this.mRemote.getRemoteTransition().startAnimation(iBinder, copyIfLocal == transaction ? transitionInfo : transitionInfo.localRemoteCopy(), copyIfLocal, anonymousClass2);
            transaction.clear();
        } catch (RemoteException e) {
            Log.e("ShellTransitions", "Error running remote transition.", e);
            if (this.mRemote.asBinder() != null) {
                this.mRemote.asBinder().unlinkToDeath(r12, 0);
            }
            transitionFinishCallback.onTransitionFinished(null);
            this.mRemote = null;
        }
        return true;
    }

    public final String toString() {
        return "OneShotRemoteHandler:" + this.mRemote.getDebugName() + ":" + this.mRemote.getRemoteTransition();
    }
}

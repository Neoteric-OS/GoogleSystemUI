package com.android.wm.shell.transition;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import android.util.Slog;
import android.view.SurfaceControl;
import android.window.IRemoteTransition;
import android.window.IRemoteTransitionFinishedCallback;
import android.window.RemoteTransition;
import android.window.TransitionFilter;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.transition.Transitions;
import java.io.PrintWriter;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RemoteTransitionHandler implements Transitions.TransitionHandler {
    public final ShellExecutor mMainExecutor;
    public final ArrayMap mRequestedRemotes = new ArrayMap();
    public final ArrayList mFilters = new ArrayList();
    public final ArrayList mTakeoverFilters = new ArrayList();
    public final ArrayMap mDeathHandlers = new ArrayMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.transition.RemoteTransitionHandler$1, reason: invalid class name */
    public final class AnonymousClass1 extends IRemoteTransitionFinishedCallback.Stub {
        public final /* synthetic */ Transitions.TransitionFinishCallback val$finishCallback;
        public final /* synthetic */ SurfaceControl.Transaction val$finishTransaction;
        public final /* synthetic */ RemoteTransition val$remote;
        public final /* synthetic */ IBinder val$transition;

        public AnonymousClass1(RemoteTransition remoteTransition, Transitions.TransitionFinishCallback transitionFinishCallback, SurfaceControl.Transaction transaction, IBinder iBinder) {
            this.val$remote = remoteTransition;
            this.val$finishCallback = transitionFinishCallback;
            this.val$finishTransaction = transaction;
            this.val$transition = iBinder;
        }

        public final void onTransitionFinished(WindowContainerTransaction windowContainerTransaction, SurfaceControl.Transaction transaction) {
            RemoteTransitionHandler.this.unhandleDeath(this.val$remote.asBinder(), this.val$finishCallback);
            if (transaction != null) {
                this.val$finishTransaction.merge(transaction);
            }
            ((HandlerExecutor) RemoteTransitionHandler.this.mMainExecutor).execute(new RemoteTransitionHandler$1$$ExternalSyntheticLambda0(this, this.val$transition, this.val$finishCallback, windowContainerTransaction));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.transition.RemoteTransitionHandler$2, reason: invalid class name */
    public final class AnonymousClass2 extends IRemoteTransitionFinishedCallback.Stub {
        public final /* synthetic */ Transitions.TransitionFinishCallback val$finishCallback;
        public final /* synthetic */ IBinder val$mergeTarget;
        public final /* synthetic */ SurfaceControl.Transaction val$t;

        public AnonymousClass2(SurfaceControl.Transaction transaction, IBinder iBinder, Transitions.TransitionFinishCallback transitionFinishCallback) {
            this.val$t = transaction;
            this.val$mergeTarget = iBinder;
            this.val$finishCallback = transitionFinishCallback;
        }

        public final void onTransitionFinished(WindowContainerTransaction windowContainerTransaction, SurfaceControl.Transaction transaction) {
            this.val$t.clear();
            ((HandlerExecutor) RemoteTransitionHandler.this.mMainExecutor).execute(new RemoteTransitionHandler$1$$ExternalSyntheticLambda0(this, this.val$mergeTarget, this.val$finishCallback, windowContainerTransaction));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RemoteDeathHandler implements IBinder.DeathRecipient {
        public final IBinder mRemote;
        public final ArrayList mPendingFinishCallbacks = new ArrayList();
        public int mUsers = 0;

        public RemoteDeathHandler(IBinder iBinder) {
            this.mRemote = iBinder;
        }

        @Override // android.os.IBinder.DeathRecipient
        public final void binderDied() {
            ((HandlerExecutor) RemoteTransitionHandler.this.mMainExecutor).execute(new RemoteTransitionHandler$$ExternalSyntheticLambda0(1, this));
        }
    }

    public RemoteTransitionHandler(ShellExecutor shellExecutor) {
        this.mMainExecutor = shellExecutor;
    }

    public static SurfaceControl.Transaction copyIfLocal(SurfaceControl.Transaction transaction, IRemoteTransition iRemoteTransition) {
        if (iRemoteTransition.asBinder().queryLocalInterface("android.window.IRemoteTransition") == null) {
            return transaction;
        }
        Parcel obtain = Parcel.obtain();
        try {
            transaction.writeToParcel(obtain, 0);
            obtain.setDataPosition(0);
            return (SurfaceControl.Transaction) SurfaceControl.Transaction.CREATOR.createFromParcel(obtain);
        } finally {
            obtain.recycle();
        }
    }

    public static void dumpRemote(PrintWriter printWriter, String str, RemoteTransition remoteTransition) {
        printWriter.print(str);
        printWriter.print(remoteTransition.getDebugName());
        printWriter.println(" (" + Integer.toHexString(System.identityHashCode(remoteTransition)) + ")");
    }

    public final void handleDeath(IBinder iBinder, Transitions.TransitionFinishCallback transitionFinishCallback) {
        synchronized (this.mDeathHandlers) {
            try {
                RemoteDeathHandler remoteDeathHandler = (RemoteDeathHandler) this.mDeathHandlers.get(iBinder);
                if (remoteDeathHandler == null) {
                    remoteDeathHandler = new RemoteDeathHandler(iBinder);
                    try {
                        iBinder.linkToDeath(remoteDeathHandler, 0);
                        this.mDeathHandlers.put(iBinder, remoteDeathHandler);
                    } catch (RemoteException unused) {
                        Slog.e("RemoteTransitionHandler", "Failed to link to death");
                        return;
                    }
                }
                if (transitionFinishCallback != null) {
                    remoteDeathHandler.mPendingFinishCallbacks.add(transitionFinishCallback);
                }
                remoteDeathHandler.mUsers++;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        RemoteTransition remoteTransition = transitionRequestInfo.getRemoteTransition();
        if (remoteTransition == null) {
            return null;
        }
        this.mRequestedRemotes.put(iBinder, remoteTransition);
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 4921319494350418912L, 1, Long.valueOf(transitionRequestInfo.getDebugId()), String.valueOf(iBinder), String.valueOf(remoteTransition));
        }
        return new WindowContainerTransaction();
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        RemoteTransition remoteTransition = (RemoteTransition) this.mRequestedRemotes.get(iBinder2);
        if (remoteTransition == null) {
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -3133681306894255105L, 0, String.valueOf(remoteTransition));
        }
        IRemoteTransition remoteTransition2 = remoteTransition.getRemoteTransition();
        if (remoteTransition2 == null) {
            return;
        }
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(transaction, iBinder2, transitionFinishCallback);
        try {
            SurfaceControl.Transaction copyIfLocal = copyIfLocal(transaction, remoteTransition2);
            remoteTransition2.mergeAnimation(iBinder, copyIfLocal == transaction ? transitionInfo : transitionInfo.localRemoteCopy(), copyIfLocal, iBinder2, anonymousClass2);
        } catch (RemoteException e) {
            Log.e("ShellTransitions", "Error attempting to merge remote transition.", e);
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
        RemoteTransition remoteTransition = (RemoteTransition) this.mRequestedRemotes.remove(iBinder);
        if (remoteTransition == null) {
            return;
        }
        try {
            remoteTransition.getRemoteTransition().onTransitionConsumed(iBinder, z);
        } catch (RemoteException e) {
            Log.e("RemoteTransitionHandler", "Error delegating onTransitionConsumed()", e);
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        if (!Transitions.SHELL_TRANSITIONS_ROTATION && TransitionUtil.hasDisplayChange(transitionInfo)) {
            this.mRequestedRemotes.remove(iBinder);
            return false;
        }
        RemoteTransition remoteTransition = (RemoteTransition) this.mRequestedRemotes.get(iBinder);
        if (remoteTransition == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -9146248499991136778L, 0, String.valueOf(transitionInfo));
            }
            int size = this.mFilters.size() - 1;
            while (true) {
                if (size < 0) {
                    break;
                }
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, 7551344402092077994L, 0, String.valueOf(this.mFilters.get(size)));
                }
                if (((TransitionFilter) ((Pair) this.mFilters.get(size)).first).matches(transitionInfo)) {
                    Slog.d("RemoteTransitionHandler", "Found filter" + this.mFilters.get(size));
                    remoteTransition = (RemoteTransition) ((Pair) this.mFilters.get(size)).second;
                    this.mRequestedRemotes.put(iBinder, remoteTransition);
                    break;
                }
                size--;
            }
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_TRANSITIONS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_TRANSITIONS, -957464960872531856L, 1, Long.valueOf(transitionInfo.getDebugId()), String.valueOf(remoteTransition));
        }
        if (remoteTransition == null) {
            return false;
        }
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(remoteTransition, transitionFinishCallback, transaction2, iBinder);
        SurfaceControl.Transaction copyIfLocal = copyIfLocal(transaction, remoteTransition.getRemoteTransition());
        if (copyIfLocal != transaction) {
            transitionInfo = transitionInfo.localRemoteCopy();
        }
        try {
            handleDeath(remoteTransition.asBinder(), transitionFinishCallback);
            remoteTransition.getRemoteTransition().startAnimation(iBinder, transitionInfo, copyIfLocal, anonymousClass1);
            transaction.clear();
            Transitions.setRunningRemoteTransitionDelegate(remoteTransition.getAppThread());
        } catch (RemoteException e) {
            Log.e("ShellTransitions", "Error running remote transition.", e);
            if (copyIfLocal != transaction) {
                copyIfLocal.close();
            }
            transaction.apply();
            unhandleDeath(remoteTransition.asBinder(), transitionFinishCallback);
            this.mRequestedRemotes.remove(iBinder);
            ((HandlerExecutor) this.mMainExecutor).execute(new RemoteTransitionHandler$$ExternalSyntheticLambda0(0, transitionFinishCallback));
        }
        return true;
    }

    public final void unhandleDeath(IBinder iBinder, Transitions.TransitionFinishCallback transitionFinishCallback) {
        synchronized (this.mDeathHandlers) {
            try {
                RemoteDeathHandler remoteDeathHandler = (RemoteDeathHandler) this.mDeathHandlers.get(iBinder);
                if (remoteDeathHandler == null) {
                    return;
                }
                if (transitionFinishCallback != null) {
                    remoteDeathHandler.mPendingFinishCallbacks.remove(transitionFinishCallback);
                }
                int i = remoteDeathHandler.mUsers - 1;
                remoteDeathHandler.mUsers = i;
                if (i == 0) {
                    if (!remoteDeathHandler.mPendingFinishCallbacks.isEmpty()) {
                        throw new IllegalStateException("Unhandling death for binder that still has pending finishCallback(s).");
                    }
                    iBinder.unlinkToDeath(remoteDeathHandler, 0);
                    this.mDeathHandlers.remove(iBinder);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

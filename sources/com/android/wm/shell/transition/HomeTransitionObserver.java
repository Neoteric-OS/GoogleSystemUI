package com.android.wm.shell.transition;

import android.app.ActivityManager;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Slog;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import com.android.wm.shell.common.RemoteCallable;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.shared.IHomeTransitionListener$Stub$Proxy;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HomeTransitionObserver implements Transitions.TransitionObserver, RemoteCallable {
    public final Context mContext;
    public SingleInstanceRemoteListener mListener;
    public final ShellExecutor mMainExecutor;

    public HomeTransitionObserver(Context context, ShellExecutor shellExecutor) {
        this.mContext = context;
        this.mMainExecutor = shellExecutor;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final Context getContext() {
        return this.mContext;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final ShellExecutor getRemoteCallExecutor() {
        return this.mMainExecutor;
    }

    public final void notifyHomeVisibilityChanged(boolean z) {
        SingleInstanceRemoteListener singleInstanceRemoteListener = this.mListener;
        if (singleInstanceRemoteListener != null) {
            IInterface iInterface = singleInstanceRemoteListener.mListener;
            if (iInterface == null) {
                Slog.e("SingleInstanceRemoteListener", "Failed remote call on null listener");
                return;
            }
            try {
                IHomeTransitionListener$Stub$Proxy iHomeTransitionListener$Stub$Proxy = (IHomeTransitionListener$Stub$Proxy) iInterface;
                Parcel obtain = Parcel.obtain(iHomeTransitionListener$Stub$Proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.wm.shell.shared.IHomeTransitionListener");
                    obtain.writeBoolean(z);
                    iHomeTransitionListener$Stub$Proxy.mRemote.transact(1, obtain, null, 1);
                    obtain.recycle();
                } catch (Throwable th) {
                    obtain.recycle();
                    throw th;
                }
            } catch (RemoteException e) {
                Slog.e("SingleInstanceRemoteListener", "Failed remote call", e);
            }
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionReady(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
            if (transitionInfo.getType() != 1010 && taskInfo != null && taskInfo.displayId == 0 && taskInfo.taskId != -1 && taskInfo.isRunning) {
                int mode = change.getMode();
                boolean hasFlags = change.hasFlags(131072);
                if (taskInfo.getActivityType() == 2) {
                    boolean z = hasFlags && TransitionUtil.isClosingType(transitionInfo.getType());
                    if (z || (!hasFlags && TransitionUtil.isOpenOrCloseMode(mode))) {
                        notifyHomeVisibilityChanged(z || TransitionUtil.isOpeningType(mode));
                    }
                }
            }
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionStarting(IBinder iBinder) {
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionFinished(IBinder iBinder, boolean z) {
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionMerged(IBinder iBinder, IBinder iBinder2) {
    }
}

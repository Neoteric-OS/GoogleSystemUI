package com.android.wm.shell.recents;

import android.app.ActivityTaskManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Slog;
import android.view.RemoteAnimationTarget;
import android.window.PictureInPictureSurfaceTransaction;
import android.window.TaskSnapshot;
import android.window.WindowAnimationState;
import com.android.internal.os.IResultReceiver;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.recents.RecentsTransitionHandler;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface IRecentsAnimationController extends IInterface {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Stub extends Binder implements IRecentsAnimationController {
        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            TaskSnapshot taskSnapshot;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.wm.shell.recents.IRecentsAnimationController");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.wm.shell.recents.IRecentsAnimationController");
                return true;
            }
            switch (i) {
                case 1:
                    int readInt = parcel.readInt();
                    parcel.enforceNoDataAvail();
                    RecentsTransitionHandler.RecentsController recentsController = (RecentsTransitionHandler.RecentsController) this;
                    try {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -4858663406018152262L, 5, Long.valueOf(recentsController.mInstanceId), Long.valueOf(readInt));
                        }
                        taskSnapshot = ActivityTaskManager.getService().takeTaskSnapshot(readInt, true);
                    } catch (RemoteException e) {
                        Slog.e("RecentsTransitionHandler", "Failed to screenshot task", e);
                        taskSnapshot = null;
                    }
                    parcel2.writeNoException();
                    parcel2.writeTypedObject(taskSnapshot, 1);
                    return true;
                case 2:
                    final int readInt2 = parcel.readInt();
                    final PictureInPictureSurfaceTransaction pictureInPictureSurfaceTransaction = (PictureInPictureSurfaceTransaction) parcel.readTypedObject(PictureInPictureSurfaceTransaction.CREATOR);
                    parcel.enforceNoDataAvail();
                    final RecentsTransitionHandler.RecentsController recentsController2 = (RecentsTransitionHandler.RecentsController) this;
                    ((HandlerExecutor) RecentsTransitionHandler.this.mExecutor).execute(new Runnable() { // from class: com.android.wm.shell.recents.RecentsTransitionHandler$RecentsController$$ExternalSyntheticLambda5
                        @Override // java.lang.Runnable
                        public final void run() {
                            RecentsTransitionHandler.RecentsController recentsController3 = RecentsTransitionHandler.RecentsController.this;
                            int i3 = readInt2;
                            PictureInPictureSurfaceTransaction pictureInPictureSurfaceTransaction2 = pictureInPictureSurfaceTransaction;
                            int i4 = RecentsTransitionHandler.RecentsController.$r8$clinit;
                            recentsController3.getClass();
                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, 518525023057359864L, 53, Long.valueOf(recentsController3.mInstanceId), Long.valueOf(i3), Boolean.valueOf(recentsController3.mFinishCB != null));
                            }
                            if (recentsController3.mFinishCB == null) {
                                return;
                            }
                            recentsController3.mPipTransaction = pictureInPictureSurfaceTransaction2;
                            recentsController3.mPipTaskId = i3;
                        }
                    });
                    parcel2.writeNoException();
                    return true;
                case 3:
                    final boolean readBoolean = parcel.readBoolean();
                    final boolean readBoolean2 = parcel.readBoolean();
                    final IResultReceiver asInterface = IResultReceiver.Stub.asInterface(parcel.readStrongBinder());
                    parcel.enforceNoDataAvail();
                    final RecentsTransitionHandler.RecentsController recentsController3 = (RecentsTransitionHandler.RecentsController) this;
                    ((HandlerExecutor) RecentsTransitionHandler.this.mExecutor).execute(new Runnable() { // from class: com.android.wm.shell.recents.RecentsTransitionHandler$RecentsController$$ExternalSyntheticLambda3
                        @Override // java.lang.Runnable
                        public final void run() {
                            RecentsTransitionHandler.RecentsController recentsController4 = RecentsTransitionHandler.RecentsController.this;
                            boolean z = readBoolean;
                            boolean z2 = readBoolean2;
                            IResultReceiver iResultReceiver = asInterface;
                            int i3 = RecentsTransitionHandler.RecentsController.$r8$clinit;
                            recentsController4.finishInner(z, z2, iResultReceiver);
                        }
                    });
                    parcel2.writeNoException();
                    return true;
                case 4:
                    final boolean readBoolean3 = parcel.readBoolean();
                    parcel.enforceNoDataAvail();
                    final RecentsTransitionHandler.RecentsController recentsController4 = (RecentsTransitionHandler.RecentsController) this;
                    final int i3 = 0;
                    ((HandlerExecutor) RecentsTransitionHandler.this.mExecutor).execute(new Runnable() { // from class: com.android.wm.shell.recents.RecentsTransitionHandler$RecentsController$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i3) {
                                case 0:
                                    RecentsTransitionHandler.RecentsController recentsController5 = recentsController4;
                                    boolean z = readBoolean3;
                                    Transitions.TransitionFinishCallback transitionFinishCallback = recentsController5.mFinishCB;
                                    if (transitionFinishCallback != null && z) {
                                        int displayId = recentsController5.mInfo.getRootCount() > 0 ? recentsController5.mInfo.getRoot(0).getDisplayId() : 0;
                                        try {
                                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                                                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -4169896698222200348L, 1, Long.valueOf(recentsController5.mInstanceId));
                                            }
                                            ActivityTaskManager.getService().focusTopTask(displayId);
                                            break;
                                        } catch (RemoteException e2) {
                                            Slog.e("RecentsTransitionHandler", "Failed to set focused task", e2);
                                            return;
                                        }
                                    } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -7556875887646265289L, 15, Boolean.valueOf(transitionFinishCallback != null), Boolean.valueOf(z));
                                        break;
                                    }
                                    break;
                                default:
                                    recentsController4.mWillFinishToHome = readBoolean3;
                                    break;
                            }
                        }
                    });
                    parcel2.writeNoException();
                    return true;
                case 5:
                    final boolean readBoolean4 = parcel.readBoolean();
                    parcel.enforceNoDataAvail();
                    final RecentsTransitionHandler.RecentsController recentsController5 = (RecentsTransitionHandler.RecentsController) this;
                    final int i4 = 1;
                    ((HandlerExecutor) RecentsTransitionHandler.this.mExecutor).execute(new Runnable() { // from class: com.android.wm.shell.recents.RecentsTransitionHandler$RecentsController$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i4) {
                                case 0:
                                    RecentsTransitionHandler.RecentsController recentsController52 = recentsController5;
                                    boolean z = readBoolean4;
                                    Transitions.TransitionFinishCallback transitionFinishCallback = recentsController52.mFinishCB;
                                    if (transitionFinishCallback != null && z) {
                                        int displayId = recentsController52.mInfo.getRootCount() > 0 ? recentsController52.mInfo.getRoot(0).getDisplayId() : 0;
                                        try {
                                            if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                                                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -4169896698222200348L, 1, Long.valueOf(recentsController52.mInstanceId));
                                            }
                                            ActivityTaskManager.getService().focusTopTask(displayId);
                                            break;
                                        } catch (RemoteException e2) {
                                            Slog.e("RecentsTransitionHandler", "Failed to set focused task", e2);
                                            return;
                                        }
                                    } else if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -7556875887646265289L, 15, Boolean.valueOf(transitionFinishCallback != null), Boolean.valueOf(z));
                                        break;
                                    }
                                    break;
                                default:
                                    recentsController5.mWillFinishToHome = readBoolean4;
                                    break;
                            }
                        }
                    });
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.readBoolean();
                    parcel.enforceNoDataAvail();
                    RecentsTransitionHandler.RecentsController recentsController6 = (RecentsTransitionHandler.RecentsController) this;
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -8743103462975981981L, 1, Long.valueOf(recentsController6.mInstanceId));
                    }
                    ((HandlerExecutor) RecentsTransitionHandler.this.mExecutor).execute(new RecentsTransitionHandler$$ExternalSyntheticLambda0(1, recentsController6));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    RemoteAnimationTarget[] remoteAnimationTargetArr = (RemoteAnimationTarget[]) parcel.createTypedArray(RemoteAnimationTarget.CREATOR);
                    WindowAnimationState[] windowAnimationStateArr = (WindowAnimationState[]) parcel.createTypedArray(WindowAnimationState.CREATOR);
                    parcel.enforceNoDataAvail();
                    RecentsTransitionHandler.RecentsController recentsController7 = (RecentsTransitionHandler.RecentsController) this;
                    ((HandlerExecutor) RecentsTransitionHandler.this.mExecutor).execute(new RecentsTransitionHandler$$ExternalSyntheticLambda0(recentsController7, remoteAnimationTargetArr, windowAnimationStateArr));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}

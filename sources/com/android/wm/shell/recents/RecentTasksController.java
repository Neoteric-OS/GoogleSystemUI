package com.android.wm.shell.recents;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.IApplicationThread;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Slog;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.window.WindowContainerToken;
import android.window.flags.DesktopModeFlags;
import androidx.compose.ui.text.input.EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.ExternalInterfaceBinder;
import com.android.wm.shell.common.RemoteCallable;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.common.TaskStackListenerCallback;
import com.android.wm.shell.common.TaskStackListenerImpl;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.recents.RecentTasksController;
import com.android.wm.shell.shared.GroupedRecentTaskInfo;
import com.android.wm.shell.shared.GroupedRecentTaskInfo$$ExternalSyntheticLambda1;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import com.android.wm.shell.shared.split.SplitBounds;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RecentTasksController implements TaskStackListenerCallback, RemoteCallable, DesktopModeTaskRepository.ActiveTasksListener {
    public final ActivityTaskManager mActivityTaskManager;
    public final Context mContext;
    public final Optional mDesktopModeTaskRepository;
    public IRecentTasksListener mListener;
    public final ShellExecutor mMainExecutor;
    public final boolean mPcFeatureEnabled;
    public final ShellCommandHandler mShellCommandHandler;
    public final ShellController mShellController;
    public final TaskStackListenerImpl mTaskStackListener;
    public final TaskStackTransitionObserver mTaskStackTransitionObserver;
    public final RecentTasksImpl mImpl = new RecentTasksImpl();
    public RecentsTransitionHandler mTransitionHandler = null;
    public final SparseIntArray mSplitTasks = new SparseIntArray();
    public final Map mTaskSplitBoundsMap = new HashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class RecentTasksImpl implements RecentTasks {
        public RecentTasksImpl() {
        }
    }

    public RecentTasksController(Context context, ShellInit shellInit, ShellController shellController, ShellCommandHandler shellCommandHandler, TaskStackListenerImpl taskStackListenerImpl, ActivityTaskManager activityTaskManager, Optional optional, TaskStackTransitionObserver taskStackTransitionObserver, ShellExecutor shellExecutor) {
        this.mContext = context;
        this.mShellController = shellController;
        this.mShellCommandHandler = shellCommandHandler;
        this.mActivityTaskManager = activityTaskManager;
        this.mPcFeatureEnabled = context.getPackageManager().hasSystemFeature("android.hardware.type.pc");
        this.mTaskStackListener = taskStackListenerImpl;
        this.mDesktopModeTaskRepository = optional;
        this.mTaskStackTransitionObserver = taskStackTransitionObserver;
        this.mMainExecutor = shellExecutor;
        shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.recents.RecentTasksController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                final RecentTasksController recentTasksController = RecentTasksController.this;
                recentTasksController.getClass();
                recentTasksController.mShellController.addExternalInterface("extra_shell_recent_tasks", new Supplier() { // from class: com.android.wm.shell.recents.RecentTasksController$$ExternalSyntheticLambda1
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        RecentTasksController recentTasksController2 = RecentTasksController.this;
                        recentTasksController2.getClass();
                        return new RecentTasksController.IRecentTasksImpl(recentTasksController2);
                    }
                }, recentTasksController);
                recentTasksController.mShellCommandHandler.addDumpCallback(new BiConsumer() { // from class: com.android.wm.shell.recents.RecentTasksController$$ExternalSyntheticLambda2
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        RecentTasksController recentTasksController2 = RecentTasksController.this;
                        PrintWriter printWriter = (PrintWriter) obj;
                        String str = (String) obj2;
                        recentTasksController2.getClass();
                        String str2 = str + "  ";
                        printWriter.println(str + "RecentTasksController");
                        printWriter.println(str + " mListener=" + recentTasksController2.mListener);
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        sb.append("Tasks:");
                        printWriter.println(sb.toString());
                        ArrayList recentTasks = recentTasksController2.getRecentTasks(Integer.MAX_VALUE, 2, ActivityManager.getCurrentUser());
                        for (int i = 0; i < recentTasks.size(); i++) {
                            StringBuilder m = EditProcessor$generateBatchErrorMessage$1$1$$ExternalSyntheticOutline0.m(str2);
                            m.append(recentTasks.get(i));
                            printWriter.println(m.toString());
                        }
                    }
                }, recentTasksController);
                recentTasksController.mTaskStackListener.addListener(recentTasksController);
                recentTasksController.mDesktopModeTaskRepository.ifPresent(new RecentTasksController$$ExternalSyntheticLambda3(0, recentTasksController));
                boolean z = Transitions.ENABLE_SHELL_TRANSITIONS;
                ShellExecutor shellExecutor2 = recentTasksController.mMainExecutor;
                if (z) {
                    recentTasksController.mTaskStackTransitionObserver.taskStackTransitionObserverListeners.put(recentTasksController, shellExecutor2);
                }
                ((KeyguardManager) recentTasksController.mContext.getSystemService(KeyguardManager.class)).addKeyguardLockedStateListener(shellExecutor2, new KeyguardManager.KeyguardLockedStateListener() { // from class: com.android.wm.shell.recents.RecentTasksController$$ExternalSyntheticLambda4
                    @Override // android.app.KeyguardManager.KeyguardLockedStateListener
                    public final void onKeyguardLockedStateChanged(boolean z2) {
                        RecentTasksController.this.notifyRecentTasksChanged();
                    }
                });
            }
        }, this);
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final Context getContext() {
        return this.mContext;
    }

    public ArrayList getRecentTasks(int i, int i2, int i3) {
        List recentTasks = this.mActivityTaskManager.getRecentTasks(i, i2, i3);
        SparseArray sparseArray = new SparseArray();
        for (int i4 = 0; i4 < recentTasks.size(); i4++) {
            ActivityManager.RecentTaskInfo recentTaskInfo = (ActivityManager.RecentTaskInfo) recentTasks.get(i4);
            sparseArray.put(recentTaskInfo.taskId, recentTaskInfo);
        }
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        int i5 = Integer.MAX_VALUE;
        int i6 = 0;
        int i7 = Integer.MAX_VALUE;
        while (i6 < recentTasks.size()) {
            ActivityManager.RecentTaskInfo recentTaskInfo2 = (ActivityManager.RecentTaskInfo) recentTasks.get(i6);
            if (sparseArray.contains(recentTaskInfo2.taskId)) {
                if (DesktopModeStatus.canEnterDesktopMode(this.mContext) && this.mDesktopModeTaskRepository.isPresent() && ((DesktopModeTaskRepository) this.mDesktopModeTaskRepository.get()).isActiveTask(recentTaskInfo2.taskId)) {
                    if (i7 == i5) {
                        i7 = arrayList2.size();
                    }
                    arrayList.add(recentTaskInfo2);
                    if (((DesktopModeTaskRepository) this.mDesktopModeTaskRepository.get()).isMinimizedTask(recentTaskInfo2.taskId)) {
                        hashSet.add(Integer.valueOf(recentTaskInfo2.taskId));
                    }
                } else {
                    int i8 = this.mSplitTasks.get(recentTaskInfo2.taskId, -1);
                    if (i8 == -1 || !sparseArray.contains(i8)) {
                        arrayList2.add(new GroupedRecentTaskInfo(new ActivityManager.RecentTaskInfo[]{recentTaskInfo2}, null, 1, null));
                    } else {
                        ActivityManager.RecentTaskInfo recentTaskInfo3 = (ActivityManager.RecentTaskInfo) sparseArray.get(i8);
                        sparseArray.remove(i8);
                        arrayList2.add(new GroupedRecentTaskInfo(new ActivityManager.RecentTaskInfo[]{recentTaskInfo2, recentTaskInfo3}, (SplitBounds) this.mTaskSplitBoundsMap.get(Integer.valueOf(i8)), 2, null));
                    }
                }
            }
            i6++;
            i5 = Integer.MAX_VALUE;
        }
        if (!arrayList.isEmpty()) {
            arrayList2.add(i7, new GroupedRecentTaskInfo((ActivityManager.RecentTaskInfo[]) arrayList.toArray(new ActivityManager.RecentTaskInfo[0]), null, 3, hashSet.stream().mapToInt(new GroupedRecentTaskInfo$$ExternalSyntheticLambda1()).toArray()));
        }
        return arrayList2;
    }

    @Override // com.android.wm.shell.common.RemoteCallable
    public final ShellExecutor getRemoteCallExecutor() {
        return this.mMainExecutor;
    }

    public final ActivityManager.RunningTaskInfo getTopRunningTask(WindowContainerToken windowContainerToken) {
        List tasks = this.mActivityTaskManager.getTasks(2, false);
        for (int i = 0; i < tasks.size(); i++) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) tasks.get(i);
            if (!runningTaskInfo.token.equals(windowContainerToken)) {
                return runningTaskInfo;
            }
        }
        return null;
    }

    public boolean hasRecentTasksListener() {
        return this.mListener != null;
    }

    public void notifyRecentTasksChanged() {
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENT_TASKS_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENT_TASKS, -1654962621925607965L, 0, null);
        }
        IRecentTasksListener iRecentTasksListener = this.mListener;
        if (iRecentTasksListener == null) {
            return;
        }
        try {
            iRecentTasksListener.onRecentTasksChanged();
        } catch (RemoteException e) {
            Slog.w("RecentTasksController", "Failed call notifyRecentTasksChanged", e);
        }
    }

    @Override // com.android.wm.shell.desktopmode.DesktopModeTaskRepository.ActiveTasksListener
    public final void onActiveTasksChanged(int i) {
        notifyRecentTasksChanged();
    }

    @Override // com.android.wm.shell.common.TaskStackListenerCallback
    public final void onRecentTaskListUpdated() {
        notifyRecentTasksChanged();
    }

    @Override // com.android.wm.shell.common.TaskStackListenerCallback
    public final void onTaskStackChanged() {
        notifyRecentTasksChanged();
    }

    public void registerRecentTasksListener(IRecentTasksListener iRecentTasksListener) {
        this.mListener = iRecentTasksListener;
    }

    public final void removeSplitPair(int i) {
        int i2 = this.mSplitTasks.get(i, -1);
        if (i2 != -1) {
            this.mSplitTasks.delete(i);
            this.mSplitTasks.delete(i2);
            this.mTaskSplitBoundsMap.remove(Integer.valueOf(i));
            this.mTaskSplitBoundsMap.remove(Integer.valueOf(i2));
            notifyRecentTasksChanged();
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENT_TASKS_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENT_TASKS, 6995785658172846580L, 5, Long.valueOf(i), Long.valueOf(i2));
            }
        }
    }

    public final boolean shouldEnableRunningTasksForDesktopMode() {
        return this.mPcFeatureEnabled || (DesktopModeStatus.canEnterDesktopMode(this.mContext) && DesktopModeFlags.ENABLE_DESKTOP_WINDOWING_TASKBAR_RUNNING_APPS.isTrue());
    }

    public void unregisterRecentTasksListener() {
        this.mListener = null;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IRecentTasksImpl extends Binder implements ExternalInterfaceBinder, IInterface {
        public RecentTasksController mController;
        public final SingleInstanceRemoteListener mListener;
        public final AnonymousClass1 mRecentTasksListener;

        public IRecentTasksImpl(RecentTasksController recentTasksController) {
            attachInterface(this, "com.android.wm.shell.recents.IRecentTasks");
            this.mRecentTasksListener = new AnonymousClass1();
            this.mController = recentTasksController;
            this.mListener = new SingleInstanceRemoteListener(recentTasksController, new RecentTasksController$$ExternalSyntheticLambda3(1, this), new RecentTasksController$IRecentTasksImpl$$ExternalSyntheticLambda3());
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder
        public final void invalidate() {
            this.mController = null;
            this.mListener.unregister();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v9, types: [com.android.wm.shell.recents.IRecentsAnimationRunner] */
        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IInterface queryLocalInterface;
            GroupedRecentTaskInfo[] groupedRecentTaskInfoArr;
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("com.android.wm.shell.recents.IRecentTasks");
            }
            if (i == 1598968902) {
                parcel2.writeString("com.android.wm.shell.recents.IRecentTasks");
                return true;
            }
            final IRecentTasksListener iRecentTasksListener = null;
            IRecentsAnimationRunner$Stub$Proxy iRecentsAnimationRunner$Stub$Proxy = null;
            if (i == 2) {
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder != null) {
                    IInterface queryLocalInterface2 = readStrongBinder.queryLocalInterface("com.android.wm.shell.recents.IRecentTasksListener");
                    if (queryLocalInterface2 == null || !(queryLocalInterface2 instanceof IRecentTasksListener)) {
                        IRecentTasksListener$Stub$Proxy iRecentTasksListener$Stub$Proxy = new IRecentTasksListener$Stub$Proxy();
                        iRecentTasksListener$Stub$Proxy.mRemote = readStrongBinder;
                        iRecentTasksListener = iRecentTasksListener$Stub$Proxy;
                    } else {
                        iRecentTasksListener = (IRecentTasksListener) queryLocalInterface2;
                    }
                }
                parcel.enforceNoDataAvail();
                ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "registerRecentTasksListener", new Consumer() { // from class: com.android.wm.shell.recents.RecentTasksController$IRecentTasksImpl$$ExternalSyntheticLambda5
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RecentTasksController.IRecentTasksImpl iRecentTasksImpl = RecentTasksController.IRecentTasksImpl.this;
                        iRecentTasksImpl.mListener.register(iRecentTasksListener);
                    }
                }, false);
            } else if (i == 3) {
                IBinder readStrongBinder2 = parcel.readStrongBinder();
                if (readStrongBinder2 != null && (queryLocalInterface = readStrongBinder2.queryLocalInterface("com.android.wm.shell.recents.IRecentTasksListener")) != null && (queryLocalInterface instanceof IRecentTasksListener)) {
                }
                parcel.enforceNoDataAvail();
                ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "unregisterRecentTasksListener", new RecentTasksController$$ExternalSyntheticLambda3(2, this), false);
            } else if (i == 4) {
                final int readInt = parcel.readInt();
                final int readInt2 = parcel.readInt();
                final int readInt3 = parcel.readInt();
                parcel.enforceNoDataAvail();
                RecentTasksController recentTasksController = this.mController;
                if (recentTasksController == null) {
                    groupedRecentTaskInfoArr = new GroupedRecentTaskInfo[0];
                } else {
                    final GroupedRecentTaskInfo[][] groupedRecentTaskInfoArr2 = {null};
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(recentTasksController, "getRecentTasks", new Consumer() { // from class: com.android.wm.shell.recents.RecentTasksController$IRecentTasksImpl$$ExternalSyntheticLambda4
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            groupedRecentTaskInfoArr2[0] = (GroupedRecentTaskInfo[]) ((RecentTasksController) obj).getRecentTasks(readInt, readInt2, readInt3).toArray(new GroupedRecentTaskInfo[0]);
                        }
                    }, true);
                    groupedRecentTaskInfoArr = groupedRecentTaskInfoArr2[0];
                }
                parcel2.writeNoException();
                parcel2.writeTypedArray(groupedRecentTaskInfoArr, 1);
            } else if (i == 5) {
                final int readInt4 = parcel.readInt();
                parcel.enforceNoDataAvail();
                final ActivityManager.RunningTaskInfo[][] runningTaskInfoArr = {null};
                ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(this.mController, "getRunningTasks", new Consumer() { // from class: com.android.wm.shell.recents.RecentTasksController$IRecentTasksImpl$$ExternalSyntheticLambda1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        runningTaskInfoArr[0] = (ActivityManager.RunningTaskInfo[]) ActivityTaskManager.getInstance().getTasks(readInt4).toArray(new ActivityManager.RunningTaskInfo[0]);
                    }
                }, true);
                ActivityManager.RunningTaskInfo[] runningTaskInfoArr2 = runningTaskInfoArr[0];
                parcel2.writeNoException();
                parcel2.writeTypedArray(runningTaskInfoArr2, 1);
            } else {
                if (i != 6) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                final PendingIntent pendingIntent = (PendingIntent) parcel.readTypedObject(PendingIntent.CREATOR);
                final Intent intent = (Intent) parcel.readTypedObject(Intent.CREATOR);
                final Bundle bundle = (Bundle) parcel.readTypedObject(Bundle.CREATOR);
                final IApplicationThread asInterface = IApplicationThread.Stub.asInterface(parcel.readStrongBinder());
                IBinder readStrongBinder3 = parcel.readStrongBinder();
                if (readStrongBinder3 != null) {
                    IInterface queryLocalInterface3 = readStrongBinder3.queryLocalInterface("com.android.wm.shell.recents.IRecentsAnimationRunner");
                    if (queryLocalInterface3 == null || !(queryLocalInterface3 instanceof IRecentsAnimationRunner)) {
                        IRecentsAnimationRunner$Stub$Proxy iRecentsAnimationRunner$Stub$Proxy2 = new IRecentsAnimationRunner$Stub$Proxy();
                        iRecentsAnimationRunner$Stub$Proxy2.mRemote = readStrongBinder3;
                        iRecentsAnimationRunner$Stub$Proxy = iRecentsAnimationRunner$Stub$Proxy2;
                    } else {
                        iRecentsAnimationRunner$Stub$Proxy = (IRecentsAnimationRunner) queryLocalInterface3;
                    }
                }
                final IRecentsAnimationRunner$Stub$Proxy iRecentsAnimationRunner$Stub$Proxy3 = iRecentsAnimationRunner$Stub$Proxy;
                parcel.enforceNoDataAvail();
                RecentTasksController recentTasksController2 = this.mController;
                if (recentTasksController2.mTransitionHandler == null) {
                    Slog.e("RecentTasksController", "Used shell-transitions startRecentsTransition without shell-transitions");
                } else {
                    ExternalInterfaceBinder.executeRemoteCallWithTaskPermission(recentTasksController2, "startRecentsTransition", new Consumer() { // from class: com.android.wm.shell.recents.RecentTasksController$IRecentTasksImpl$$ExternalSyntheticLambda0
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ((RecentTasksController) obj).mTransitionHandler.startRecentsTransition(pendingIntent, intent, bundle, asInterface, iRecentsAnimationRunner$Stub$Proxy3);
                        }
                    }, false);
                }
            }
            return true;
        }

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.wm.shell.recents.RecentTasksController$IRecentTasksImpl$1, reason: invalid class name */
        public final class AnonymousClass1 extends Binder implements IRecentTasksListener {
            public AnonymousClass1() {
                attachInterface(this, "com.android.wm.shell.recents.IRecentTasksListener");
            }

            @Override // com.android.wm.shell.recents.IRecentTasksListener
            public final void onRecentTasksChanged() {
                IRecentTasksImpl.this.mListener.call(new RecentTasksController$IRecentTasksImpl$1$$ExternalSyntheticLambda0());
            }

            @Override // com.android.wm.shell.recents.IRecentTasksListener
            public final void onRunningTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo) {
                IInterface iInterface = IRecentTasksImpl.this.mListener.mListener;
                if (iInterface == null) {
                    Slog.e("SingleInstanceRemoteListener", "Failed remote call on null listener");
                    return;
                }
                try {
                    ((IRecentTasksListener) iInterface).onRunningTaskAppeared(runningTaskInfo);
                } catch (RemoteException e) {
                    Slog.e("SingleInstanceRemoteListener", "Failed remote call", e);
                }
            }

            @Override // com.android.wm.shell.recents.IRecentTasksListener
            public final void onRunningTaskChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
                IInterface iInterface = IRecentTasksImpl.this.mListener.mListener;
                if (iInterface == null) {
                    Slog.e("SingleInstanceRemoteListener", "Failed remote call on null listener");
                    return;
                }
                try {
                    ((IRecentTasksListener) iInterface).onRunningTaskChanged(runningTaskInfo);
                } catch (RemoteException e) {
                    Slog.e("SingleInstanceRemoteListener", "Failed remote call", e);
                }
            }

            @Override // com.android.wm.shell.recents.IRecentTasksListener
            public final void onRunningTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
                IInterface iInterface = IRecentTasksImpl.this.mListener.mListener;
                if (iInterface == null) {
                    Slog.e("SingleInstanceRemoteListener", "Failed remote call on null listener");
                    return;
                }
                try {
                    ((IRecentTasksListener) iInterface).onRunningTaskVanished(runningTaskInfo);
                } catch (RemoteException e) {
                    Slog.e("SingleInstanceRemoteListener", "Failed remote call", e);
                }
            }

            @Override // com.android.wm.shell.recents.IRecentTasksListener
            public final void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
                IInterface iInterface = IRecentTasksImpl.this.mListener.mListener;
                if (iInterface == null) {
                    Slog.e("SingleInstanceRemoteListener", "Failed remote call on null listener");
                    return;
                }
                try {
                    ((IRecentTasksListener) iInterface).onTaskMovedToFront(runningTaskInfo);
                } catch (RemoteException e) {
                    Slog.e("SingleInstanceRemoteListener", "Failed remote call", e);
                }
            }

            @Override // android.os.Binder
            public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
                if (i >= 1 && i <= 16777215) {
                    parcel.enforceInterface("com.android.wm.shell.recents.IRecentTasksListener");
                }
                if (i == 1598968902) {
                    parcel2.writeString("com.android.wm.shell.recents.IRecentTasksListener");
                    return true;
                }
                if (i == 1) {
                    onRecentTasksChanged();
                } else if (i == 2) {
                    ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) parcel.readTypedObject(ActivityManager.RunningTaskInfo.CREATOR);
                    parcel.enforceNoDataAvail();
                    onRunningTaskAppeared(runningTaskInfo);
                } else if (i == 3) {
                    ActivityManager.RunningTaskInfo runningTaskInfo2 = (ActivityManager.RunningTaskInfo) parcel.readTypedObject(ActivityManager.RunningTaskInfo.CREATOR);
                    parcel.enforceNoDataAvail();
                    onRunningTaskVanished(runningTaskInfo2);
                } else if (i == 4) {
                    ActivityManager.RunningTaskInfo runningTaskInfo3 = (ActivityManager.RunningTaskInfo) parcel.readTypedObject(ActivityManager.RunningTaskInfo.CREATOR);
                    parcel.enforceNoDataAvail();
                    onRunningTaskChanged(runningTaskInfo3);
                } else {
                    if (i != 5) {
                        return super.onTransact(i, parcel, parcel2, i2);
                    }
                    ActivityManager.RunningTaskInfo runningTaskInfo4 = (ActivityManager.RunningTaskInfo) parcel.readTypedObject(ActivityManager.RunningTaskInfo.CREATOR);
                    parcel.enforceNoDataAvail();
                    onTaskMovedToFront(runningTaskInfo4);
                }
                return true;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this;
            }
        }

        @Override // com.android.wm.shell.common.ExternalInterfaceBinder, android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}

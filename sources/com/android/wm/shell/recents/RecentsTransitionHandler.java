package com.android.wm.shell.recents;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.IApplicationThread;
import android.graphics.Color;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.ArrayMap;
import android.util.IntArray;
import android.util.Pair;
import android.util.Slog;
import android.util.SparseBooleanArray;
import android.view.RemoteAnimationTarget;
import android.view.SurfaceControl;
import android.window.PictureInPictureSurfaceTransaction;
import android.window.TaskSnapshot;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import androidx.appcompat.view.menu.CascadingMenuPopup$$ExternalSyntheticOutline0;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.recents.IRecentsAnimationController;
import com.android.wm.shell.recents.RecentsTransitionHandler;
import com.android.wm.shell.shared.TransitionUtil;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.HomeTransitionObserver;
import com.android.wm.shell.transition.Transitions;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RecentsTransitionHandler implements Transitions.TransitionHandler, Transitions.TransitionObserver {
    public static final IBinder SYNTHETIC_TRANSITION = new Binder();
    public Color mBackgroundColor;
    public final ShellExecutor mExecutor;
    public final HomeTransitionObserver mHomeTransitionObserver;
    public final RecentTasksController mRecentTasksController;
    public final ShellTaskOrganizer mShellTaskOrganizer;
    public final Transitions mTransitions;
    public IApplicationThread mAnimApp = null;
    public final ArrayList mControllers = new ArrayList();
    public final ArrayList mStateListeners = new ArrayList();
    public final ArrayList mMixers = new ArrayList();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class RecentsController extends IRecentsAnimationController.Stub {
        public static final /* synthetic */ int $r8$clinit = 0;
        public ArrayList mClosingTasks;
        public RecentsTransitionHandler$RecentsController$$ExternalSyntheticLambda7 mDeathHandler;
        public Transitions.TransitionFinishCallback mFinishCB;
        public SurfaceControl.Transaction mFinishTransaction;
        public TransitionInfo mInfo;
        public final int mInstanceId;
        public boolean mKeyguardLocked;
        public ArrayMap mLeashMap;
        public IRecentsAnimationRunner mListener;
        public boolean mOpeningSeparateHome;
        public ArrayList mOpeningTasks;
        public boolean mPausingSeparateHome;
        public ArrayList mPausingTasks;
        public Pair mPendingPauseSnapshotsForCancel;
        public WindowContainerToken mPipTask;
        public int mPipTaskId;
        public PictureInPictureSurfaceTransaction mPipTransaction;
        public WindowContainerToken mRecentsTask;
        public int mRecentsTaskId;
        public int mState;
        public IBinder mTransition;
        public boolean mWillFinishToHome;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1, types: [android.os.IBinder$DeathRecipient, com.android.wm.shell.recents.RecentsTransitionHandler$RecentsController$$ExternalSyntheticLambda7] */
        public RecentsController(IRecentsAnimationRunner iRecentsAnimationRunner) {
            attachInterface(this, "com.android.wm.shell.recents.IRecentsAnimationController");
            this.mFinishCB = null;
            this.mFinishTransaction = null;
            this.mPausingTasks = null;
            this.mClosingTasks = null;
            this.mOpeningTasks = null;
            this.mPipTask = null;
            this.mPipTaskId = -1;
            this.mRecentsTask = null;
            this.mRecentsTaskId = -1;
            this.mInfo = null;
            this.mOpeningSeparateHome = false;
            this.mPausingSeparateHome = false;
            this.mLeashMap = null;
            this.mPipTransaction = null;
            this.mTransition = null;
            this.mKeyguardLocked = false;
            this.mWillFinishToHome = false;
            this.mState = 0;
            this.mInstanceId = System.identityHashCode(this);
            this.mListener = iRecentsAnimationRunner;
            ?? r1 = new IBinder.DeathRecipient() { // from class: com.android.wm.shell.recents.RecentsTransitionHandler$RecentsController$$ExternalSyntheticLambda7
                @Override // android.os.IBinder.DeathRecipient
                public final void binderDied() {
                    RecentsTransitionHandler.RecentsController recentsController = RecentsTransitionHandler.RecentsController.this;
                    int i = RecentsTransitionHandler.RecentsController.$r8$clinit;
                    recentsController.getClass();
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -8322929235470962491L, 1, Long.valueOf(recentsController.mInstanceId));
                    }
                    recentsController.finishInner(recentsController.mWillFinishToHome, false, null);
                }
            };
            this.mDeathHandler = r1;
            try {
                ((IRecentsAnimationRunner$Stub$Proxy) iRecentsAnimationRunner).mRemote.linkToDeath(r1, 0);
            } catch (RemoteException e) {
                Slog.e("RecentsTransitionHandler", "RecentsController: failed to link to death", e);
                this.mListener = null;
            }
        }

        public final void cancel(String str) {
            cancel(str, true, false);
        }

        public final void cleanUp() {
            RecentsTransitionHandler$RecentsController$$ExternalSyntheticLambda7 recentsTransitionHandler$RecentsController$$ExternalSyntheticLambda7;
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, 3856935944170387493L, 1, Long.valueOf(this.mInstanceId));
            }
            IRecentsAnimationRunner iRecentsAnimationRunner = this.mListener;
            if (iRecentsAnimationRunner != null && (recentsTransitionHandler$RecentsController$$ExternalSyntheticLambda7 = this.mDeathHandler) != null) {
                ((IRecentsAnimationRunner$Stub$Proxy) iRecentsAnimationRunner).mRemote.unlinkToDeath(recentsTransitionHandler$RecentsController$$ExternalSyntheticLambda7, 0);
                this.mDeathHandler = null;
            }
            this.mListener = null;
            this.mFinishCB = null;
            if (this.mLeashMap != null) {
                for (int i = 0; i < this.mLeashMap.size(); i++) {
                    ((SurfaceControl) this.mLeashMap.valueAt(i)).release();
                }
                this.mLeashMap = null;
            }
            this.mFinishTransaction = null;
            this.mPausingTasks = null;
            this.mClosingTasks = null;
            this.mOpeningTasks = null;
            this.mInfo = null;
            this.mTransition = null;
            this.mPendingPauseSnapshotsForCancel = null;
            RecentsTransitionHandler.this.mControllers.remove(this);
            for (int i2 = 0; i2 < RecentsTransitionHandler.this.mStateListeners.size(); i2++) {
                ((RecentsTransitionStateListener) RecentsTransitionHandler.this.mStateListeners.get(i2)).onAnimationStateChanged(false);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:31:0x0061  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00a3  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00a7  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00ac  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0305  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x030b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:69:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:70:0x00ef  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void finishInner(boolean r24, boolean r25, com.android.internal.os.IResultReceiver r26) {
            /*
                Method dump skipped, instructions count: 851
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.recents.RecentsTransitionHandler.RecentsController.finishInner(boolean, boolean, com.android.internal.os.IResultReceiver):void");
        }

        public final Pair getSnapshotsForPausingTasks() {
            TaskSnapshot[] taskSnapshotArr;
            ArrayList arrayList = this.mPausingTasks;
            int[] iArr = null;
            if (arrayList != null && arrayList.size() > 0) {
                int[] iArr2 = new int[this.mPausingTasks.size()];
                taskSnapshotArr = new TaskSnapshot[this.mPausingTasks.size()];
                for (int i = 0; i < this.mPausingTasks.size(); i++) {
                    try {
                        TaskState taskState = (TaskState) this.mPausingTasks.get(0);
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -564434088454636251L, 5, Long.valueOf(this.mInstanceId), Long.valueOf(taskState.mTaskInfo.taskId));
                        }
                        taskSnapshotArr[i] = ActivityTaskManager.getService().takeTaskSnapshot(taskState.mTaskInfo.taskId, true);
                    } catch (RemoteException unused) {
                    }
                }
                iArr = iArr2;
                return new Pair(iArr, taskSnapshotArr);
            }
            taskSnapshotArr = null;
            return new Pair(iArr, taskSnapshotArr);
        }

        public final boolean sendCancel(int[] iArr, TaskSnapshot[] taskSnapshotArr) {
            String str = taskSnapshotArr != null ? "with snapshots" : "";
            try {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -9022140943350351976L, 1, Long.valueOf(this.mInstanceId), str);
                }
                ((IRecentsAnimationRunner$Stub$Proxy) this.mListener).onAnimationCanceled(iArr, taskSnapshotArr);
                return true;
            } catch (RemoteException e) {
                Slog.e("RecentsTransitionHandler", "Error canceling recents animation", e);
                return false;
            }
        }

        public final void cancel(String str, boolean z, boolean z2) {
            if (this.mTransition == RecentsTransitionHandler.SYNTHETIC_TRANSITION) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -9186933329219668104L, 1, Long.valueOf(this.mInstanceId), String.valueOf(str));
                }
                try {
                    ((IRecentsAnimationRunner$Stub$Proxy) this.mListener).onAnimationCanceled(new int[0], new TaskSnapshot[0]);
                } catch (RemoteException e) {
                    Slog.e("RecentsTransitionHandler", "Error canceling previous recents animation", e);
                }
                cleanUp();
                return;
            }
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -9089009824156010496L, 13, Long.valueOf(this.mInstanceId), Boolean.valueOf(z), String.valueOf(str));
            }
            if (this.mListener != null) {
                if (z2) {
                    Pair pair = this.mPendingPauseSnapshotsForCancel;
                    if (pair == null) {
                        pair = getSnapshotsForPausingTasks();
                    }
                    sendCancel((int[]) pair.first, (TaskSnapshot[]) pair.second);
                } else {
                    sendCancel(null, null);
                }
            }
            if (this.mFinishCB != null) {
                finishInner(z, false, null);
            } else {
                cleanUp();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TaskState {
        public final boolean mIsTranslucent;
        public final SurfaceControl mLeash;
        public final ActivityManager.RunningTaskInfo mTaskInfo;
        public final SurfaceControl mTaskSurface;
        public final WindowContainerToken mToken;

        public TaskState(TransitionInfo.Change change, SurfaceControl surfaceControl) {
            this.mToken = change.getContainer();
            this.mTaskInfo = change.getTaskInfo();
            this.mTaskSurface = change.getLeash();
            this.mIsTranslucent = (change.getFlags() & 4) != 0;
            this.mLeash = surfaceControl;
        }

        public static int indexOf(ArrayList arrayList, TransitionInfo.Change change) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (((TaskState) arrayList.get(size)).mToken.equals(change.getContainer())) {
                    return size;
                }
            }
            return -1;
        }

        public final String toString() {
            return "" + this.mToken + " : " + this.mLeash;
        }
    }

    public RecentsTransitionHandler(ShellInit shellInit, ShellTaskOrganizer shellTaskOrganizer, Transitions transitions, RecentTasksController recentTasksController, HomeTransitionObserver homeTransitionObserver) {
        this.mShellTaskOrganizer = shellTaskOrganizer;
        this.mTransitions = transitions;
        this.mExecutor = transitions.mMainExecutor;
        this.mRecentTasksController = recentTasksController;
        this.mHomeTransitionObserver = homeTransitionObserver;
        if (Transitions.ENABLE_SHELL_TRANSITIONS && recentTasksController != null) {
            shellInit.addInitCallback(new RecentsTransitionHandler$$ExternalSyntheticLambda0(0, this), this);
        }
    }

    public RecentsController findController(IBinder iBinder) {
        for (int size = this.mControllers.size() - 1; size >= 0; size--) {
            RecentsController recentsController = (RecentsController) this.mControllers.get(size);
            if (recentsController.mTransition == iBinder) {
                return recentsController;
            }
        }
        return null;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        if (this.mControllers.isEmpty()) {
            return null;
        }
        RecentsController recentsController = (RecentsController) CascadingMenuPopup$$ExternalSyntheticOutline0.m(this.mControllers, 1);
        recentsController.getClass();
        if (transitionRequestInfo.getType() == 6 && transitionRequestInfo.getDisplayChange() != null) {
            TransitionRequestInfo.DisplayChange displayChange = transitionRequestInfo.getDisplayChange();
            if (displayChange.getStartRotation() != displayChange.getEndRotation()) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, 2330586253165148821L, 1, Long.valueOf(recentsController.mInstanceId));
                }
                recentsController.mPendingPauseSnapshotsForCancel = recentsController.getSnapshotsForPausingTasks();
            }
        }
        return null;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void mergeAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, IBinder iBinder2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        boolean z;
        int i;
        boolean z2;
        RemoteAnimationTarget[] remoteAnimationTargetArr;
        int i2;
        String str;
        ArrayList arrayList;
        ArrayList arrayList2;
        RecentsController recentsController;
        boolean z3;
        int i3;
        int i4;
        RecentsController findController = findController(iBinder2);
        boolean z4 = false;
        boolean z5 = true;
        if (findController == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -9101066424534024548L, 0, null);
                return;
            }
            return;
        }
        if (findController.mFinishCB == null) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -7300394669171000003L, 1, Long.valueOf(findController.mInstanceId));
                return;
            }
            return;
        }
        if (transitionInfo.getType() == 12) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -4265385298039391224L, 1, Long.valueOf(findController.mInstanceId));
            }
            findController.cancel("transit_sleep");
            return;
        }
        if (findController.mKeyguardLocked || (transitionInfo.getFlags() & 14592) != 0) {
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, 1951248095232933372L, 1, Long.valueOf(findController.mInstanceId));
            }
            findController.cancel("keyguard_locked", true, false);
            return;
        }
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, 1317693307849553962L, 1, Long.valueOf(findController.mInstanceId));
        }
        findController.mOpeningSeparateHome = false;
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        TransitionInfo.Change change = null;
        ArrayList arrayList3 = null;
        ArrayList arrayList4 = null;
        IntArray intArray = null;
        int i5 = 0;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        while (i5 < transitionInfo.getChanges().size()) {
            TransitionInfo.Change change2 = (TransitionInfo.Change) transitionInfo.getChanges().get(i5);
            ActivityManager.RunningTaskInfo taskInfo = change2.getTaskInfo();
            if (taskInfo != null && taskInfo.configuration.windowConfiguration.isAlwaysOnTop()) {
                findController.cancel(PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("task #"), taskInfo.taskId, " is always_on_top"), z4, z4);
                return;
            }
            boolean z9 = (taskInfo == null || !TransitionInfo.isIndependent(change2, transitionInfo)) ? z4 : z5;
            WindowContainerToken windowContainerToken = findController.mRecentsTask;
            boolean z10 = windowContainerToken != null && windowContainerToken.equals(change2.getContainer());
            boolean z11 = z8 || z9;
            ActivityManager.RunningTaskInfo taskInfo2 = change2.getTaskInfo();
            if (taskInfo2 == null) {
                z3 = z11;
                i4 = 0;
            } else {
                boolean z12 = sparseBooleanArray.get(taskInfo2.taskId);
                if (taskInfo2.hasParentTask()) {
                    z3 = z11;
                    i3 = 1;
                    sparseBooleanArray.put(taskInfo2.parentTaskId, true);
                } else {
                    z3 = z11;
                    i3 = 1;
                }
                i4 = (z12 ? 1 : 0) ^ i3;
            }
            if (TransitionUtil.isOpeningType(change2.getMode()) || TransitionUtil.isOrderOnly(change2)) {
                if (z10) {
                    change = change2;
                } else if (z9 || i4 != 0) {
                    if (i4 != 0 && taskInfo.topActivityType == 2) {
                        findController.mOpeningSeparateHome = true;
                    }
                    if (arrayList4 == null) {
                        arrayList4 = new ArrayList();
                        intArray = new IntArray();
                    }
                    arrayList4.add(change2);
                    intArray.add(i4);
                }
            } else if (TransitionUtil.isClosingType(change2.getMode())) {
                if (z10) {
                    z7 = true;
                } else if (z9 || i4 != 0) {
                    if (arrayList3 == null) {
                        arrayList3 = new ArrayList();
                    }
                    arrayList3.add(change2);
                }
            } else if (change2.getMode() != 6) {
                continue;
            } else {
                if (change2.hasFlags(32) && transitionInfo.getType() == 6) {
                    findController.cancel("display change", findController.mWillFinishToHome, true);
                    return;
                }
                if (!TransitionUtil.isOrderOnly(change2) && i4 != 0) {
                    if ((change2.getFlags() & 1048576) != 0 && taskInfo != null && taskInfo.getWindowingMode() == 1) {
                        if (arrayList4 == null) {
                            arrayList4 = new ArrayList();
                            intArray = new IntArray();
                        }
                        arrayList4.add(change2);
                        intArray.add(1);
                    }
                    z6 = true;
                } else if (i4 != 0 && taskInfo.topActivityType == 2 && !z10) {
                    if (arrayList4 == null) {
                        arrayList4 = new ArrayList();
                        intArray = new IntArray();
                    }
                    arrayList4.add(change2);
                    intArray.add(1);
                }
            }
            i5++;
            z8 = z3;
            z4 = false;
            z5 = true;
        }
        if (z6 && z7) {
            Pair pair = findController.mPendingPauseSnapshotsForCancel;
            if (pair == null) {
                pair = findController.getSnapshotsForPausingTasks();
            }
            findController.sendCancel((int[]) pair.first, (TaskSnapshot[]) pair.second);
            ((HandlerExecutor) RecentsTransitionHandler.this.mExecutor).executeDelayed(new RecentsTransitionHandler$$ExternalSyntheticLambda0(3, findController), 0L);
            return;
        }
        if (change != null) {
            if (findController.mState == 0) {
                Slog.e("RecentsTransitionHandler", "Returning to recents while recents is already idle.");
            }
            if (arrayList3 == null || arrayList3.size() == 0) {
                Slog.e("RecentsTransitionHandler", "Returning to recents without closing any opening tasks.");
            }
            transaction.show(change.getLeash());
            transaction.setAlpha(change.getLeash(), 1.0f);
            findController.mState = 0;
        }
        String str2 = "";
        if (arrayList3 != null) {
            int i6 = 0;
            z = false;
            while (i6 < arrayList3.size()) {
                TransitionInfo.Change change3 = (TransitionInfo.Change) arrayList3.get(i6);
                int indexOf = TaskState.indexOf(findController.mPausingTasks, change3);
                if (indexOf >= 0) {
                    findController.mClosingTasks.add((TaskState) findController.mPausingTasks.remove(indexOf));
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                        recentsController = findController;
                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -8004858446668286751L, 1, Long.valueOf(change3.getTaskInfo().taskId));
                    } else {
                        recentsController = findController;
                    }
                    findController = recentsController;
                    z = true;
                } else {
                    int indexOf2 = TaskState.indexOf(findController.mOpeningTasks, change3);
                    if (indexOf2 < 0) {
                        Slog.w("RecentsTransitionHandler", "Closing a task that wasn't opening, this may be split or something unexpected: " + change3.getTaskInfo().taskId);
                    } else {
                        TaskState taskState = (TaskState) findController.mOpeningTasks.remove(indexOf2);
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                            arrayList2 = arrayList3;
                            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, 1357141655006297500L, 4, taskState.mLeash != null ? "leaf " : "", Long.valueOf(taskState.mTaskInfo.taskId));
                        } else {
                            arrayList2 = arrayList3;
                        }
                        findController.mPausingTasks.add(taskState);
                        z = true;
                        i6++;
                        arrayList3 = arrayList2;
                    }
                }
                arrayList2 = arrayList3;
                i6++;
                arrayList3 = arrayList2;
            }
        } else {
            z = false;
        }
        if (arrayList4 == null || arrayList4.size() <= 0) {
            i = 1;
            z2 = z;
            remoteAnimationTargetArr = null;
        } else {
            int size = findController.mInfo.getChanges().size() * 3;
            int i7 = 0;
            for (int i8 = 0; i8 < intArray.size(); i8++) {
                i7 += intArray.get(i8);
            }
            RemoteAnimationTarget[] remoteAnimationTargetArr2 = i7 > 0 ? new RemoteAnimationTarget[i7] : null;
            int i9 = 0;
            int i10 = 0;
            while (i9 < arrayList4.size()) {
                TransitionInfo.Change change4 = (TransitionInfo.Change) arrayList4.get(i9);
                boolean z13 = intArray.get(i9) == 1;
                int indexOf3 = TaskState.indexOf(findController.mClosingTasks, change4);
                if (indexOf3 >= 0) {
                    findController.mClosingTasks.remove(indexOf3);
                }
                int indexOf4 = TaskState.indexOf(findController.mPausingTasks, change4);
                if (indexOf4 >= 0) {
                    if (z13) {
                        remoteAnimationTargetArr2[i10] = TransitionUtil.newTarget(change4, size, ((TaskState) findController.mPausingTasks.get(indexOf4)).mLeash, false);
                        i10++;
                    }
                    TaskState taskState2 = (TaskState) findController.mPausingTasks.remove(indexOf4);
                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                        i2 = i9;
                        str = str2;
                        ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, 251565571246734032L, 4, z13 ? "leaf " : str2, Long.valueOf(taskState2.mTaskInfo.taskId));
                    } else {
                        i2 = i9;
                        str = str2;
                    }
                    findController.mOpeningTasks.add(taskState2);
                    transaction.show(change4.getLeash());
                    transaction.setAlpha(change4.getLeash(), 1.0f);
                    arrayList = arrayList4;
                } else {
                    i2 = i9;
                    str = str2;
                    if (z13) {
                        arrayList = arrayList4;
                        RemoteAnimationTarget newTarget = TransitionUtil.newTarget(change4, size, false, transitionInfo, transaction, findController.mLeashMap);
                        int i11 = i10 + 1;
                        remoteAnimationTargetArr2[i10] = newTarget;
                        int rootIndexFor = TransitionUtil.rootIndexFor(change4, findController.mInfo);
                        boolean z14 = indexOf3 >= 0;
                        transaction.reparent(newTarget.leash, findController.mInfo.getRoot(rootIndexFor).getLeash());
                        transaction.setLayer(newTarget.leash, size);
                        if (z14) {
                            transaction.show(newTarget.leash);
                            transaction.setAlpha(newTarget.leash, 1.0f);
                            transaction.setAlpha(change4.getLeash(), 1.0f);
                        } else {
                            transaction.hide(newTarget.leash);
                        }
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -3925432882013980076L, 13, Long.valueOf(newTarget.taskId), Boolean.valueOf(z14));
                        }
                        findController.mOpeningTasks.add(new TaskState(change4, newTarget.leash));
                        i10 = i11;
                    } else {
                        arrayList = arrayList4;
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, 381285223370931163L, 1, Long.valueOf(change4.getTaskInfo().taskId));
                        }
                        transaction.setLayer(change4.getLeash(), size);
                        transaction.show(change4.getLeash());
                        findController.mOpeningTasks.add(new TaskState(change4, null));
                    }
                }
                i9 = i2 + 1;
                str2 = str;
                arrayList4 = arrayList;
            }
            i = 1;
            findController.mState = 1;
            z2 = true;
            remoteAnimationTargetArr = remoteAnimationTargetArr2;
        }
        if (findController.mPausingTasks.isEmpty() && ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[i]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, -1544826506645848243L, i, Long.valueOf(findController.mInstanceId));
        }
        if (!z8) {
            Slog.d("RecentsTransitionHandler", "Got an activity only transition during recents, so apply directly");
            for (int i12 = 0; i12 < transitionInfo.getChanges().size(); i12++) {
                TransitionInfo.Change change5 = (TransitionInfo.Change) transitionInfo.getChanges().get(i12);
                if (TransitionUtil.isOpeningType(change5.getMode())) {
                    transaction.show(change5.getLeash());
                    transaction.setAlpha(change5.getLeash(), 1.0f);
                } else if (TransitionUtil.isClosingType(change5.getMode())) {
                    transaction.hide(change5.getLeash());
                }
            }
        } else if (!z2) {
            Slog.w("RecentsTransitionHandler", "Don't know how to merge this transition, foundRecentsClosing=" + z7 + " recentsTaskId=" + findController.mRecentsTaskId);
            if (z7 || findController.mRecentsTaskId < 0) {
                findController.mWillFinishToHome = false;
                findController.cancel("didn't merge", false, false);
                return;
            }
            return;
        }
        transaction.apply();
        transitionInfo.releaseAnimSurfaces();
        if (remoteAnimationTargetArr != null) {
            try {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_RECENTS_TRANSITION_enabled[1]) {
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_RECENTS_TRANSITION, 833931798120511255L, 1, Long.valueOf(findController.mInstanceId));
                }
                IRecentsAnimationRunner$Stub$Proxy iRecentsAnimationRunner$Stub$Proxy = (IRecentsAnimationRunner$Stub$Proxy) findController.mListener;
                Parcel obtain = Parcel.obtain(iRecentsAnimationRunner$Stub$Proxy.mRemote);
                try {
                    obtain.writeInterfaceToken("com.android.wm.shell.recents.IRecentsAnimationRunner");
                    obtain.writeTypedArray(remoteAnimationTargetArr, 0);
                    iRecentsAnimationRunner$Stub$Proxy.mRemote.transact(4, obtain, null, 1);
                } finally {
                    obtain.recycle();
                }
            } catch (RemoteException e) {
                Slog.e("RecentsTransitionHandler", "Error sending appeared tasks to recents animation", e);
            }
        }
        transitionFinishCallback.onTransitionFinished(null);
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
        for (int size = this.mControllers.size() - 1; size >= 0; size--) {
            ((RecentsController) this.mControllers.get(size)).cancel("onTransitionConsumed");
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionReady(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        RecentsController findController = findController(SYNTHETIC_TRANSITION);
        if (findController != null) {
            findController.cancel("incoming_transition");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x027b, code lost:
    
        if (r9.topActivityType != 2) goto L92;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r11v12 */
    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean startAnimation(android.os.IBinder r27, final android.window.TransitionInfo r28, final android.view.SurfaceControl.Transaction r29, android.view.SurfaceControl.Transaction r30, com.android.wm.shell.transition.Transitions.TransitionFinishCallback r31) {
        /*
            Method dump skipped, instructions count: 1276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.recents.RecentsTransitionHandler.startAnimation(android.os.IBinder, android.window.TransitionInfo, android.view.SurfaceControl$Transaction, android.view.SurfaceControl$Transaction, com.android.wm.shell.transition.Transitions$TransitionFinishCallback):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0167 A[LOOP:1: B:39:0x0116->B:48:0x0167, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x015d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.os.IBinder startRecentsTransition(android.app.PendingIntent r11, android.content.Intent r12, android.os.Bundle r13, android.app.IApplicationThread r14, com.android.wm.shell.recents.IRecentsAnimationRunner r15) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.recents.RecentsTransitionHandler.startRecentsTransition(android.app.PendingIntent, android.content.Intent, android.os.Bundle, android.app.IApplicationThread, com.android.wm.shell.recents.IRecentsAnimationRunner):android.os.IBinder");
    }
}

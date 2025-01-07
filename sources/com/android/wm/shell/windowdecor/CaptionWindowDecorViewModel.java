package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.app.WindowConfiguration;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseArray;
import android.view.Choreographer;
import android.view.ISystemGestureExclusionListener;
import android.view.IWindowManager;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewConfiguration;
import android.window.DisplayAreaInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.R;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.desktopmode.EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0;
import com.android.wm.shell.freeform.FreeformTaskTransitionHandler;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.CaptionWindowDecorViewModel;
import com.android.wm.shell.windowdecor.DragDetector;
import com.android.wm.shell.windowdecor.extension.TaskInfoKt;
import com.android.wm.shell.windowdecor.viewhost.WindowDecorViewHostSupplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CaptionWindowDecorViewModel implements WindowDecorViewModel {
    public final ShellExecutor mBgExecutor;
    public final Context mContext;
    public final DisplayController mDisplayController;
    public final InputManager mInputManager;
    public final Choreographer mMainChoreographer;
    public final ShellExecutor mMainExecutor;
    public final Handler mMainHandler;
    public final RootTaskDisplayAreaOrganizer mRootTaskDisplayAreaOrganizer;
    public boolean mShouldPilferCaptionEvents;
    public final SyncTransactionQueue mSyncQueue;
    public TaskOperations mTaskOperations;
    public final ShellTaskOrganizer mTaskOrganizer;
    public final Transitions mTransitions;
    public final WindowDecorViewHostSupplier mWindowDecorViewHostSupplier;
    public final IWindowManager mWindowManager;
    public final Region mExclusionRegion = Region.obtain();
    public final SparseArray mWindowDecorByTaskId = new SparseArray();
    public final AnonymousClass1 mGestureExclusionListener = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.windowdecor.CaptionWindowDecorViewModel$1, reason: invalid class name */
    public final class AnonymousClass1 extends ISystemGestureExclusionListener.Stub {
        public AnonymousClass1() {
        }

        public final void onSystemGestureExclusionChanged(int i, final Region region, Region region2) {
            if (CaptionWindowDecorViewModel.this.mContext.getDisplayId() != i) {
                return;
            }
            ((HandlerExecutor) CaptionWindowDecorViewModel.this.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.windowdecor.CaptionWindowDecorViewModel$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    CaptionWindowDecorViewModel.AnonymousClass1 anonymousClass1 = CaptionWindowDecorViewModel.AnonymousClass1.this;
                    CaptionWindowDecorViewModel.this.mExclusionRegion.set(region);
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CaptionTouchEventListener implements View.OnClickListener, View.OnTouchListener, DragDetector.MotionEventHandler {
        public final int mDisplayId;
        public final DragDetector mDragDetector;
        public int mDragPointerId = -1;
        public final FluidResizeTaskPositioner mDragPositioningCallback;
        public boolean mIsDragging;
        public final int mTaskId;
        public final WindowContainerToken mTaskToken;

        public CaptionTouchEventListener(ActivityManager.RunningTaskInfo runningTaskInfo, FluidResizeTaskPositioner fluidResizeTaskPositioner) {
            this.mTaskId = runningTaskInfo.taskId;
            this.mTaskToken = runningTaskInfo.token;
            this.mDragPositioningCallback = fluidResizeTaskPositioner;
            this.mDragDetector = new DragDetector(this, ViewConfiguration.get(CaptionWindowDecorViewModel.this.mContext).getScaledTouchSlop());
            this.mDisplayId = runningTaskInfo.displayId;
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
        
            if (r0 != 3) goto L21;
         */
        @Override // com.android.wm.shell.windowdecor.DragDetector.MotionEventHandler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean handleMotionEvent(android.view.View r11, android.view.MotionEvent r12) {
            /*
                Method dump skipped, instructions count: 335
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.windowdecor.CaptionWindowDecorViewModel.CaptionTouchEventListener.handleMotionEvent(android.view.View, android.view.MotionEvent):boolean");
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            int i;
            int id = view.getId();
            if (id == R.id.close_window) {
                TaskOperations taskOperations = CaptionWindowDecorViewModel.this.mTaskOperations;
                WindowContainerToken windowContainerToken = this.mTaskToken;
                taskOperations.getClass();
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                windowContainerTransaction.removeTask(windowContainerToken);
                if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
                    taskOperations.mSyncQueue.queue(windowContainerTransaction);
                    return;
                } else {
                    FreeformTaskTransitionHandler freeformTaskTransitionHandler = taskOperations.mTransitionStarter;
                    freeformTaskTransitionHandler.mPendingTransitionTokens.add(freeformTaskTransitionHandler.mTransitions.startTransition(2, windowContainerTransaction, freeformTaskTransitionHandler));
                    return;
                }
            }
            if (id == R.id.back_button) {
                TaskOperations taskOperations2 = CaptionWindowDecorViewModel.this.mTaskOperations;
                int i2 = this.mDisplayId;
                taskOperations2.sendBackEvent(0, i2);
                taskOperations2.sendBackEvent(1, i2);
                return;
            }
            if (id == R.id.minimize_window) {
                TaskOperations taskOperations3 = CaptionWindowDecorViewModel.this.mTaskOperations;
                WindowContainerToken windowContainerToken2 = this.mTaskToken;
                taskOperations3.getClass();
                taskOperations3.minimizeTask(windowContainerToken2, new WindowContainerTransaction());
                return;
            }
            if (id == R.id.maximize_window) {
                ActivityManager.RunningTaskInfo runningTaskInfo = CaptionWindowDecorViewModel.this.mTaskOrganizer.getRunningTaskInfo(this.mTaskId);
                DisplayAreaInfo displayAreaInfo = (DisplayAreaInfo) CaptionWindowDecorViewModel.this.mRootTaskDisplayAreaOrganizer.mDisplayAreasInfo.get(runningTaskInfo.displayId);
                TaskOperations taskOperations4 = CaptionWindowDecorViewModel.this.mTaskOperations;
                int windowingMode = displayAreaInfo.configuration.windowConfiguration.getWindowingMode();
                taskOperations4.getClass();
                WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
                int i3 = runningTaskInfo.getWindowingMode() != 1 ? 1 : 5;
                windowContainerTransaction2.setWindowingMode(runningTaskInfo.token, i3 != windowingMode ? i3 : 0);
                if (i3 == 1) {
                    windowContainerTransaction2.setBounds(runningTaskInfo.token, (Rect) null);
                }
                if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
                    taskOperations4.mSyncQueue.queue(windowContainerTransaction2);
                    return;
                }
                FreeformTaskTransitionHandler freeformTaskTransitionHandler2 = taskOperations4.mTransitionStarter;
                freeformTaskTransitionHandler2.getClass();
                if (i3 == 1) {
                    i = 1008;
                } else {
                    if (i3 != 5) {
                        throw new IllegalArgumentException("Unexpected target windowing mode " + WindowConfiguration.windowingModeToString(i3));
                    }
                    i = 1009;
                }
                freeformTaskTransitionHandler2.mPendingTransitionTokens.add(freeformTaskTransitionHandler2.mTransitions.startTransition(i, windowContainerTransaction2, freeformTaskTransitionHandler2));
            }
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            if (view.getId() != R.id.caption) {
                return false;
            }
            if (motionEvent.getAction() == 0 && !CaptionWindowDecorViewModel.this.mTaskOrganizer.getRunningTaskInfo(this.mTaskId).isFocused) {
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                windowContainerTransaction.reorder(this.mTaskToken, true);
                CaptionWindowDecorViewModel.this.mSyncQueue.queue(windowContainerTransaction);
            }
            CaptionWindowDecoration captionWindowDecoration = (CaptionWindowDecoration) CaptionWindowDecorViewModel.this.mWindowDecorByTaskId.get(this.mTaskId);
            int actionMasked = motionEvent.getActionMasked();
            boolean z = actionMasked == 0;
            boolean z2 = actionMasked == 3 || actionMasked == 1;
            if (z) {
                boolean contains = captionWindowDecoration.mResult.mCustomizableCaptionRegion.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                boolean contains2 = CaptionWindowDecorViewModel.this.mExclusionRegion.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                boolean isTransparentCaptionBarAppearance = TaskInfoKt.isTransparentCaptionBarAppearance(captionWindowDecoration.mTaskInfo);
                int[] iArr = new int[2];
                view.getLocationInWindow(iArr);
                Point point = new Point(iArr[0], iArr[1]);
                DragResizeInputListener dragResizeInputListener = captionWindowDecoration.mDragResizeListener;
                boolean z3 = dragResizeInputListener != null && dragResizeInputListener.mInputEventReceiver.mDragResizeWindowGeometry.shouldHandleEvent(motionEvent, point);
                CaptionWindowDecorViewModel.this.mShouldPilferCaptionEvents = ((contains && contains2 && isTransparentCaptionBarAppearance) || z3) ? false : true;
            }
            CaptionWindowDecorViewModel captionWindowDecorViewModel = CaptionWindowDecorViewModel.this;
            if (!captionWindowDecorViewModel.mShouldPilferCaptionEvents) {
                return false;
            }
            InputManager inputManager = captionWindowDecorViewModel.mInputManager;
            if (inputManager != null) {
                inputManager.pilferPointers(view.getViewRootImpl().getInputToken());
            }
            if (z2) {
                CaptionWindowDecorViewModel.this.mShouldPilferCaptionEvents = false;
            }
            return this.mDragDetector.onMotionEvent(null, motionEvent);
        }
    }

    public CaptionWindowDecorViewModel(Context context, Handler handler, ShellExecutor shellExecutor, ShellExecutor shellExecutor2, Choreographer choreographer, IWindowManager iWindowManager, ShellInit shellInit, ShellTaskOrganizer shellTaskOrganizer, DisplayController displayController, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, SyncTransactionQueue syncTransactionQueue, Transitions transitions, WindowDecorViewHostSupplier windowDecorViewHostSupplier) {
        this.mContext = context;
        this.mMainExecutor = shellExecutor2;
        this.mMainHandler = handler;
        this.mBgExecutor = shellExecutor;
        this.mWindowManager = iWindowManager;
        this.mMainChoreographer = choreographer;
        this.mTaskOrganizer = shellTaskOrganizer;
        this.mDisplayController = displayController;
        this.mRootTaskDisplayAreaOrganizer = rootTaskDisplayAreaOrganizer;
        this.mSyncQueue = syncTransactionQueue;
        this.mTransitions = transitions;
        this.mWindowDecorViewHostSupplier = windowDecorViewHostSupplier;
        if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
            this.mTaskOperations = new TaskOperations(null, context, syncTransactionQueue);
        }
        this.mInputManager = (InputManager) context.getSystemService(InputManager.class);
        shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.windowdecor.CaptionWindowDecorViewModel$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CaptionWindowDecorViewModel captionWindowDecorViewModel = CaptionWindowDecorViewModel.this;
                captionWindowDecorViewModel.getClass();
                try {
                    captionWindowDecorViewModel.mWindowManager.registerSystemGestureExclusionListener(captionWindowDecorViewModel.mGestureExclusionListener, captionWindowDecorViewModel.mContext.getDisplayId());
                } catch (RemoteException e) {
                    Log.e("CaptionWindowDecorViewModel", "Failed to register window manager callbacks", e);
                }
            }
        }, this);
    }

    public final void createWindowDecoration(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        CaptionWindowDecoration captionWindowDecoration = (CaptionWindowDecoration) this.mWindowDecorByTaskId.get(runningTaskInfo.taskId);
        if (captionWindowDecoration != null) {
            captionWindowDecoration.close();
        }
        Context context = this.mContext;
        Context createContextAsUser = context.createContextAsUser(UserHandle.of(runningTaskInfo.userId), 0);
        Choreographer choreographer = this.mMainChoreographer;
        CaptionWindowDecoration captionWindowDecoration2 = new CaptionWindowDecoration(context, createContextAsUser, this.mDisplayController, this.mTaskOrganizer, runningTaskInfo, surfaceControl, this.mMainHandler, this.mBgExecutor, choreographer, this.mWindowDecorViewHostSupplier);
        this.mWindowDecorByTaskId.put(runningTaskInfo.taskId, captionWindowDecoration2);
        FluidResizeTaskPositioner$$ExternalSyntheticLambda0 fluidResizeTaskPositioner$$ExternalSyntheticLambda0 = new FluidResizeTaskPositioner$$ExternalSyntheticLambda0();
        EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0 enterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0 = new EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0();
        FluidResizeTaskPositioner fluidResizeTaskPositioner = new FluidResizeTaskPositioner(this.mTaskOrganizer, this.mTransitions, captionWindowDecoration2, this.mDisplayController, fluidResizeTaskPositioner$$ExternalSyntheticLambda0, enterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0);
        CaptionTouchEventListener captionTouchEventListener = new CaptionTouchEventListener(runningTaskInfo, fluidResizeTaskPositioner);
        captionWindowDecoration2.mOnCaptionButtonClickListener = captionTouchEventListener;
        captionWindowDecoration2.mOnCaptionTouchListener = captionTouchEventListener;
        captionWindowDecoration2.mDragPositioningCallback = fluidResizeTaskPositioner;
        captionWindowDecoration2.mTaskDragResizer = fluidResizeTaskPositioner;
        captionWindowDecoration2.relayout(runningTaskInfo, transaction, transaction2, false, false);
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void destroyWindowDecoration(ActivityManager.RunningTaskInfo runningTaskInfo) {
        CaptionWindowDecoration captionWindowDecoration = (CaptionWindowDecoration) this.mWindowDecorByTaskId.removeReturnOld(runningTaskInfo.taskId);
        if (captionWindowDecoration == null) {
            return;
        }
        captionWindowDecoration.close();
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void onTaskChanging(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        CaptionWindowDecoration captionWindowDecoration = (CaptionWindowDecoration) this.mWindowDecorByTaskId.get(runningTaskInfo.taskId);
        if (!shouldShowWindowDecor(runningTaskInfo)) {
            if (captionWindowDecoration != null) {
                destroyWindowDecoration(runningTaskInfo);
            }
        } else if (captionWindowDecoration == null) {
            createWindowDecoration(runningTaskInfo, surfaceControl, transaction, transaction2);
        } else {
            captionWindowDecoration.relayout(runningTaskInfo, transaction, transaction2, false, false);
        }
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void onTaskClosing(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        CaptionWindowDecoration captionWindowDecoration = (CaptionWindowDecoration) this.mWindowDecorByTaskId.get(runningTaskInfo.taskId);
        if (captionWindowDecoration == null) {
            return;
        }
        captionWindowDecoration.relayout(runningTaskInfo, transaction, transaction2, false, false);
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        CaptionWindowDecoration captionWindowDecoration = (CaptionWindowDecoration) this.mWindowDecorByTaskId.get(runningTaskInfo.taskId);
        if (captionWindowDecoration == null) {
            return;
        }
        if (shouldShowWindowDecor(runningTaskInfo)) {
            captionWindowDecoration.relayout(runningTaskInfo);
        } else {
            destroyWindowDecoration(runningTaskInfo);
        }
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final boolean onTaskOpening(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        if (!shouldShowWindowDecor(runningTaskInfo)) {
            return false;
        }
        createWindowDecoration(runningTaskInfo, surfaceControl, transaction, transaction2);
        return true;
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (this.mTaskOrganizer.getRunningTaskInfo(runningTaskInfo.taskId) == null) {
            destroyWindowDecoration(runningTaskInfo);
        }
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void setFreeformTaskTransitionStarter(FreeformTaskTransitionHandler freeformTaskTransitionHandler) {
        this.mTaskOperations = new TaskOperations(freeformTaskTransitionHandler, this.mContext, this.mSyncQueue);
    }

    public final boolean shouldShowWindowDecor(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (runningTaskInfo.getWindowingMode() == 5) {
            return true;
        }
        if (runningTaskInfo.getWindowingMode() == 2 || runningTaskInfo.getActivityType() != 1) {
            return false;
        }
        DisplayAreaInfo displayAreaInfo = (DisplayAreaInfo) this.mRootTaskDisplayAreaOrganizer.mDisplayAreasInfo.get(runningTaskInfo.displayId);
        if (displayAreaInfo != null) {
            return displayAreaInfo.configuration.windowConfiguration.getWindowingMode() == 5;
        }
        if (this.mContext.getPackageManager().hasSystemFeature("android.hardware.type.pc")) {
            return true;
        }
        return (runningTaskInfo.displayId == 0 || Settings.Global.getInt(this.mContext.getContentResolver(), "force_desktop_mode_on_external_displays", 0) == 0) ? false : true;
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void setSplitScreenController(SplitScreenController splitScreenController) {
    }
}

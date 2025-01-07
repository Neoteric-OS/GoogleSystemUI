package com.android.wm.shell.windowdecor;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.IActivityTaskManager;
import android.content.ComponentName;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.util.Slog;
import android.util.SparseArray;
import android.view.Choreographer;
import android.view.GestureDetector;
import android.view.ISystemGestureExclusionListener;
import android.view.IWindowManager;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.InputMonitor;
import android.view.InsetsSource;
import android.view.InsetsState;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowInsets;
import android.widget.Toast;
import android.window.DisplayAreaInfo;
import android.window.WindowContainerToken;
import android.window.WindowContainerTransaction;
import android.window.flags.DesktopModeFlags;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.R;
import com.android.wm.shell.RootTaskDisplayAreaOrganizer;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.apptoweb.AppToWebGenericLinksParser;
import com.android.wm.shell.apptoweb.AssistContentRequester;
import com.android.wm.shell.back.BackAnimationController$$ExternalSyntheticOutline0;
import com.android.wm.shell.common.DisplayChangeController;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.common.DisplayInsetsController;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.MultiInstanceHelper;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.SyncTransactionQueue;
import com.android.wm.shell.compatui.AppCompatUtils;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import com.android.wm.shell.desktopmode.DesktopModeVisualIndicator;
import com.android.wm.shell.desktopmode.DesktopTasksController;
import com.android.wm.shell.desktopmode.DesktopTasksController$dragToDesktopStateListener$1;
import com.android.wm.shell.desktopmode.DesktopWallpaperActivity;
import com.android.wm.shell.desktopmode.DragToDesktopTransitionHandler;
import com.android.wm.shell.desktopmode.EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0;
import com.android.wm.shell.desktopmode.WindowDecorCaptionHandleRepository;
import com.android.wm.shell.freeform.FreeformTaskTransitionHandler;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import com.android.wm.shell.shared.desktopmode.DesktopModeTransitionSource;
import com.android.wm.shell.splitscreen.SplitScreen;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.sysui.KeyguardChangeListener;
import com.android.wm.shell.sysui.ShellCommandHandler;
import com.android.wm.shell.sysui.ShellController;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel.DesktopModeOnInsetsChangedListener;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel.DragStartListenerImpl;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecoration;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecoration$$ExternalSyntheticLambda12;
import com.android.wm.shell.windowdecor.DragDetector;
import com.android.wm.shell.windowdecor.DragPositioningCallbackUtility;
import com.android.wm.shell.windowdecor.extension.TaskInfoKt;
import com.android.wm.shell.windowdecor.viewholder.AppHeaderViewHolder;
import com.android.wm.shell.windowdecor.viewholder.WindowDecorationViewHolder;
import com.android.wm.shell.windowdecor.viewhost.WindowDecorViewHostSupplier;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class DesktopModeWindowDecorViewModel implements WindowDecorViewModel {
    public final Optional mActivityOrientationChangeHandler;
    public final ActivityTaskManager mActivityTaskManager;
    public final AppHeaderViewHolder.Factory mAppHeaderViewHolderFactory;
    public final AssistContentRequester mAssistContentRequester;
    public final ShellExecutor mBgExecutor;
    public final Context mContext;
    public final DesktopModeKeyguardChangeListener mDesktopModeKeyguardChangeListener;
    public final DesktopModeWindowDecoration.Factory mDesktopModeWindowDecorFactory;
    public final DesktopTasksController mDesktopTasksController;
    public final Optional mDesktopTasksLimiter;
    public final DisplayController mDisplayController;
    public final DisplayInsetsController mDisplayInsetsController;
    public final DragStartListenerImpl mDragStartListener;
    public final Rect mDragToDesktopAnimationStartBounds;
    public final SparseArray mEventReceiversByDisplay;
    public final Region mExclusionRegion;
    public final DragStartListenerImpl mExclusionRegionListener;
    public final AppToWebGenericLinksParser mGenericLinksParser;
    public final AnonymousClass1 mGestureExclusionListener;
    public boolean mInImmersiveMode;
    public final InputManager mInputManager;
    public final InputMonitorFactory mInputMonitorFactory;
    public final InteractionJankMonitor mInteractionJankMonitor;
    public final Choreographer mMainChoreographer;
    public final ShellExecutor mMainExecutor;
    public final Handler mMainHandler;
    public MoveToDesktopAnimator mMoveToDesktopAnimator;
    public final MultiInstanceHelper mMultiInstanceHelper;
    public final DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda0 mOnDisplayChangingListener;
    public final RootTaskDisplayAreaOrganizer mRootTaskDisplayAreaOrganizer;
    public final ShellCommandHandler mShellCommandHandler;
    public final ShellController mShellController;
    public SplitScreenController mSplitScreenController;
    public final SyncTransactionQueue mSyncQueue;
    public TaskOperations mTaskOperations;
    public final ShellTaskOrganizer mTaskOrganizer;
    public final TaskPositionerFactory mTaskPositionerFactory;
    public final Supplier mTransactionFactory;
    public boolean mTransitionDragActive;
    public final Transitions mTransitions;
    public final SparseArray mWindowDecorByTaskId;
    public final WindowDecorCaptionHandleRepository mWindowDecorCaptionHandleRepository;
    public final WindowDecorViewHostSupplier mWindowDecorViewHostSupplier;
    public final IWindowManager mWindowManager;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel$1, reason: invalid class name */
    public final class AnonymousClass1 extends ISystemGestureExclusionListener.Stub {
        public AnonymousClass1() {
        }

        public final void onSystemGestureExclusionChanged(int i, final Region region, Region region2) {
            if (DesktopModeWindowDecorViewModel.this.mContext.getDisplayId() != i) {
                return;
            }
            DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel = DesktopModeWindowDecorViewModel.this;
            ((HandlerExecutor) desktopModeWindowDecorViewModel.mMainExecutor).execute(new Runnable() { // from class: com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DesktopModeWindowDecorViewModel.AnonymousClass1 anonymousClass1 = DesktopModeWindowDecorViewModel.AnonymousClass1.this;
                    DesktopModeWindowDecorViewModel.this.mExclusionRegion.set(region);
                }
            });
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DesktopModeKeyguardChangeListener implements KeyguardChangeListener {
        public DesktopModeKeyguardChangeListener() {
        }

        @Override // com.android.wm.shell.sysui.KeyguardChangeListener
        public final void onKeyguardVisibilityChanged(boolean z, boolean z2, boolean z3) {
            DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel = DesktopModeWindowDecorViewModel.this;
            for (int size = desktopModeWindowDecorViewModel.mWindowDecorByTaskId.size() - 1; size >= 0; size--) {
                DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) desktopModeWindowDecorViewModel.mWindowDecorByTaskId.valueAt(size);
                if (desktopModeWindowDecoration != null) {
                    boolean z4 = desktopModeWindowDecoration.mIsKeyguardVisibleAndOccluded;
                    boolean z5 = z && z2;
                    desktopModeWindowDecoration.mIsKeyguardVisibleAndOccluded = z5;
                    if (z4 != z5) {
                        desktopModeWindowDecoration.relayout(desktopModeWindowDecoration.mTaskInfo);
                    }
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class DesktopModeOnInsetsChangedListener implements DisplayInsetsController.OnInsetsChangedListener {
        public DesktopModeOnInsetsChangedListener() {
        }

        @Override // com.android.wm.shell.common.DisplayInsetsController.OnInsetsChangedListener
        public final void insetsChanged$1(InsetsState insetsState) {
            DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel = DesktopModeWindowDecorViewModel.this;
            for (int size = desktopModeWindowDecorViewModel.mWindowDecorByTaskId.size() - 1; size >= 0; size--) {
                DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) desktopModeWindowDecorViewModel.mWindowDecorByTaskId.valueAt(size);
                if (desktopModeWindowDecoration != null) {
                    int i = desktopModeWindowDecoration.mTaskInfo.displayId;
                    int statusBars = WindowInsets.Type.statusBars();
                    int sourceSize = insetsState.sourceSize();
                    boolean z = false;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= sourceSize) {
                            break;
                        }
                        InsetsSource sourceAt = insetsState.sourceAt(i2);
                        if (sourceAt.getType() == statusBars) {
                            z = sourceAt.isVisible();
                            break;
                        }
                        i2++;
                    }
                    desktopModeWindowDecorViewModel.mInImmersiveMode = !z;
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DesktopModeTouchEventListener extends GestureDetector.SimpleOnGestureListener implements View.OnClickListener, View.OnTouchListener, View.OnLongClickListener, View.OnGenericMotionListener, DragDetector.MotionEventHandler {
        public final int mDisplayId;
        public final TaskPositioner mDragPositioningCallback;
        public final GestureDetector mGestureDetector;
        public final DragDetector mHandleDragDetector;
        public boolean mHasLongClicked;
        public final DragDetector mHeaderDragDetector;
        public boolean mIsDragging;
        public boolean mShouldPilferCaptionEvents;
        public final int mTaskId;
        public final WindowContainerToken mTaskToken;
        public boolean mTouchscreenInUse;
        public final Rect mOnDragStartInitialBounds = new Rect();
        public int mDragPointerId = -1;

        public DesktopModeTouchEventListener(ActivityManager.RunningTaskInfo runningTaskInfo, TaskPositioner taskPositioner) {
            this.mTaskId = runningTaskInfo.taskId;
            this.mTaskToken = runningTaskInfo.token;
            this.mDragPositioningCallback = taskPositioner;
            int scaledTouchSlop = ViewConfiguration.get(DesktopModeWindowDecorViewModel.this.mContext).getScaledTouchSlop();
            this.mHandleDragDetector = new DragDetector(this, scaledTouchSlop);
            this.mHeaderDragDetector = new DragDetector(this, scaledTouchSlop);
            this.mGestureDetector = new GestureDetector(DesktopModeWindowDecorViewModel.this.mContext, this);
            this.mDisplayId = runningTaskInfo.displayId;
        }

        @Override // com.android.wm.shell.windowdecor.DragDetector.MotionEventHandler
        public final boolean handleMotionEvent(View view, MotionEvent motionEvent) {
            DesktopModeVisualIndicator desktopModeVisualIndicator;
            DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) DesktopModeWindowDecorViewModel.this.mWindowDecorByTaskId.get(this.mTaskId);
            ActivityManager.RunningTaskInfo runningTaskInfo = desktopModeWindowDecoration.mTaskInfo;
            boolean z = true;
            if (DesktopModeStatus.canEnterDesktopMode(DesktopModeWindowDecorViewModel.this.mContext) && !runningTaskInfo.isFreeform()) {
                if (view.getId() != R.id.caption_handle) {
                    return false;
                }
                DesktopModeWindowDecorViewModel.this.handleCaptionThroughStatusBar(motionEvent, desktopModeWindowDecoration);
                boolean z2 = this.mIsDragging;
                updateDragStatus(motionEvent.getActionMasked());
                if (motionEvent.getActionMasked() != 1 && motionEvent.getActionMasked() != 3) {
                    z = false;
                }
                if (z2 && z) {
                    view.setPressed(false);
                }
                return z2;
            }
            int id = view.getId();
            if (this.mGestureDetector.onTouchEvent(motionEvent)) {
                return true;
            }
            boolean z3 = id == R.id.close_window || id == R.id.maximize_window || id == R.id.open_menu_button || id == R.id.minimize_window;
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                this.mDragPointerId = motionEvent.getPointerId(0);
                Rect onDragPositioningStart = this.mDragPositioningCallback.onDragPositioningStart(0, motionEvent.getRawX(0), motionEvent.getRawY(0));
                updateDragStatus(motionEvent.getActionMasked());
                this.mOnDragStartInitialBounds.set(onDragPositioningStart);
                this.mHasLongClicked = false;
                return true ^ z3;
            }
            if (actionMasked != 1) {
                if (actionMasked == 2) {
                    DragResizeInputListener dragResizeInputListener = desktopModeWindowDecoration.mDragResizeListener;
                    if (dragResizeInputListener != null && dragResizeInputListener.mInputEventReceiver.mShouldHandleEvents) {
                        return true;
                    }
                    desktopModeWindowDecoration.closeMaximizeMenu();
                    if (motionEvent.findPointerIndex(this.mDragPointerId) == -1) {
                        this.mDragPointerId = motionEvent.getPointerId(0);
                    }
                    int findPointerIndex = motionEvent.findPointerIndex(this.mDragPointerId);
                    Rect onDragPositioningMove = this.mDragPositioningCallback.onDragPositioningMove(motionEvent.getRawX(findPointerIndex), motionEvent.getRawY(findPointerIndex));
                    DesktopTasksController desktopTasksController = DesktopModeWindowDecorViewModel.this.mDesktopTasksController;
                    SurfaceControl surfaceControl = desktopModeWindowDecoration.mTaskSurface;
                    float rawX = motionEvent.getRawX(findPointerIndex);
                    desktopTasksController.getClass();
                    if (runningTaskInfo.getWindowingMode() == 5) {
                        desktopTasksController.updateVisualIndicator(runningTaskInfo, surfaceControl, rawX, onDragPositioningMove.top, DesktopModeVisualIndicator.DragStartState.FROM_FREEFORM);
                    }
                    if (!this.mIsDragging && onDragPositioningMove.equals(this.mOnDragStartInitialBounds)) {
                        return true;
                    }
                    updateDragStatus(motionEvent.getActionMasked());
                    return true;
                }
                if (actionMasked != 3) {
                    return true;
                }
            }
            if (!this.mIsDragging) {
                return false;
            }
            if (motionEvent.findPointerIndex(this.mDragPointerId) == -1) {
                this.mDragPointerId = motionEvent.getPointerId(0);
            }
            int findPointerIndex2 = motionEvent.findPointerIndex(this.mDragPointerId);
            Point point = new Point((int) (motionEvent.getRawX(findPointerIndex2) - motionEvent.getX(findPointerIndex2)), (int) (motionEvent.getRawY(findPointerIndex2) - motionEvent.getY(findPointerIndex2)));
            Rect onDragPositioningEnd = this.mDragPositioningCallback.onDragPositioningEnd(motionEvent.getRawX(findPointerIndex2), motionEvent.getRawY(findPointerIndex2));
            DesktopTasksController desktopTasksController2 = DesktopModeWindowDecorViewModel.this.mDesktopTasksController;
            SurfaceControl surfaceControl2 = desktopModeWindowDecoration.mTaskSurface;
            PointF pointF = new PointF(motionEvent.getRawX(findPointerIndex2), motionEvent.getRawY(findPointerIndex2));
            Rect calculateValidDragArea = desktopModeWindowDecoration.calculateValidDragArea();
            Rect rect = new Rect(this.mOnDragStartInitialBounds);
            desktopTasksController2.getClass();
            if (runningTaskInfo.configuration.windowConfiguration.getWindowingMode() == 5 && (desktopModeVisualIndicator = desktopTasksController2.visualIndicator) != null) {
                int ordinal = desktopModeVisualIndicator.updateIndicatorType(new PointF(pointF.x, onDragPositioningEnd.top)).ordinal();
                if (ordinal == 0) {
                    DragPositioningCallbackUtility.snapTaskBoundsIfNecessary(onDragPositioningEnd, calculateValidDragArea);
                    if (!onDragPositioningEnd.equals(rect)) {
                        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                        windowContainerTransaction.setBounds(runningTaskInfo.token, onDragPositioningEnd);
                        desktopTasksController2.transitions.startTransition(6, windowContainerTransaction, null);
                        desktopTasksController2.releaseVisualIndicator();
                    }
                } else {
                    if (ordinal == 1) {
                        throw new IllegalArgumentException("Should not be receiving TO_DESKTOP_INDICATOR for a freeform task.");
                    }
                    if (ordinal == 2) {
                        desktopTasksController2.moveToFullscreenWithAnimation(runningTaskInfo, point, DesktopModeTransitionSource.TASK_DRAG);
                    } else if (ordinal == 3) {
                        desktopTasksController2.handleSnapResizingTask(runningTaskInfo, DesktopTasksController.SnapPosition.LEFT, surfaceControl2, onDragPositioningEnd, rect);
                    } else if (ordinal == 4) {
                        desktopTasksController2.handleSnapResizingTask(runningTaskInfo, DesktopTasksController.SnapPosition.RIGHT, surfaceControl2, onDragPositioningEnd, rect);
                    }
                }
                desktopTasksController2.releaseVisualIndicator();
                DesktopTasksController$dragToDesktopStateListener$1 desktopTasksController$dragToDesktopStateListener$1 = desktopTasksController2.taskbarDesktopTaskListener;
                if (desktopTasksController$dragToDesktopStateListener$1 != null) {
                    desktopTasksController$dragToDesktopStateListener$1.onTaskbarCornerRoundingUpdate(desktopTasksController2.doesAnyTaskRequireTaskbarRounding(runningTaskInfo.displayId, null));
                }
            }
            if (z3 && !this.mHasLongClicked) {
                return false;
            }
            updateDragStatus(motionEvent.getActionMasked());
            return true;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (this.mIsDragging) {
                this.mIsDragging = false;
                return;
            }
            DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) DesktopModeWindowDecorViewModel.this.mWindowDecorByTaskId.get(this.mTaskId);
            int id = view.getId();
            if (id == R.id.close_window) {
                DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel = DesktopModeWindowDecorViewModel.this;
                int i = this.mTaskId;
                SplitScreenController splitScreenController = desktopModeWindowDecorViewModel.mSplitScreenController;
                if (splitScreenController != null && splitScreenController.isTaskInSplitScreen(i)) {
                    DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel2 = DesktopModeWindowDecorViewModel.this;
                    SplitScreenController splitScreenController2 = desktopModeWindowDecorViewModel2.mSplitScreenController;
                    splitScreenController2.moveTaskToFullscreen(desktopModeWindowDecorViewModel2.mSplitScreenController.getTaskInfo(splitScreenController2.getSplitPosition(this.mTaskId) == 1 ? 0 : 1).taskId);
                    return;
                }
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                DesktopTasksController desktopTasksController = DesktopModeWindowDecorViewModel.this.mDesktopTasksController;
                int i2 = this.mDisplayId;
                int i3 = this.mTaskId;
                DesktopModeTaskRepository desktopModeTaskRepository = desktopTasksController.taskRepository;
                if (desktopModeTaskRepository.isOnlyVisibleNonClosingTask(i3)) {
                    desktopTasksController.removeWallpaperActivity(windowContainerTransaction);
                }
                desktopModeTaskRepository.addClosingTask(i2, i3);
                TaskOperations taskOperations = DesktopModeWindowDecorViewModel.this.mTaskOperations;
                WindowContainerToken windowContainerToken = this.mTaskToken;
                taskOperations.getClass();
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
                TaskOperations taskOperations2 = DesktopModeWindowDecorViewModel.this.mTaskOperations;
                int i4 = this.mDisplayId;
                taskOperations2.sendBackEvent(0, i4);
                taskOperations2.sendBackEvent(1, i4);
                return;
            }
            if (id != R.id.caption_handle && id != R.id.open_menu_button) {
                if (id == R.id.maximize_window) {
                    DesktopModeWindowDecorViewModel.this.onMaximizeOrRestore(desktopModeWindowDecoration.mTaskInfo.taskId, "caption_bar_button");
                    return;
                }
                if (id == R.id.minimize_window) {
                    WindowContainerTransaction windowContainerTransaction2 = new WindowContainerTransaction();
                    DesktopTasksController desktopTasksController2 = DesktopModeWindowDecorViewModel.this.mDesktopTasksController;
                    if (desktopTasksController2.taskRepository.isOnlyVisibleNonClosingTask(this.mTaskId)) {
                        desktopTasksController2.removeWallpaperActivity(windowContainerTransaction2);
                    }
                    DesktopModeWindowDecorViewModel.this.mDesktopTasksLimiter.ifPresent(new DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda12(r0, this, DesktopModeWindowDecorViewModel.this.mTaskOperations.minimizeTask(this.mTaskToken, windowContainerTransaction2)));
                    return;
                }
                return;
            }
            if (desktopModeWindowDecoration.isHandleMenuActive()) {
                return;
            }
            ActivityManager.RunningTaskInfo runningTaskInfo = desktopModeWindowDecoration.mTaskInfo;
            if (!runningTaskInfo.isFocused) {
                DesktopModeWindowDecorViewModel.this.mDesktopTasksController.moveTaskToFront(runningTaskInfo);
            }
            DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel3 = DesktopModeWindowDecorViewModel.this;
            final ActivityManager.RunningTaskInfo runningTaskInfo2 = desktopModeWindowDecoration.mTaskInfo;
            desktopModeWindowDecorViewModel3.getClass();
            try {
                ActivityTaskManager.getService().getRecentTasks(Integer.MAX_VALUE, 1, ActivityManager.getService().getCurrentUserId()).getList().stream().filter(new Predicate() { // from class: com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda14
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        ComponentName componentName;
                        ActivityManager.RunningTaskInfo runningTaskInfo3 = runningTaskInfo2;
                        ActivityManager.RecentTaskInfo recentTaskInfo = (ActivityManager.RecentTaskInfo) obj;
                        return (recentTaskInfo.taskId == runningTaskInfo3.taskId || (componentName = recentTaskInfo.baseActivity) == null || !componentName.getPackageName().equals(runningTaskInfo3.baseActivity.getPackageName())) ? false : true;
                    }
                }).toList().size();
                final AssistContentRequester assistContentRequester = desktopModeWindowDecoration.mAssistContentRequester;
                final int i5 = desktopModeWindowDecoration.mTaskInfo.taskId;
                final DesktopModeWindowDecoration$$ExternalSyntheticLambda12 desktopModeWindowDecoration$$ExternalSyntheticLambda12 = new DesktopModeWindowDecoration$$ExternalSyntheticLambda12(desktopModeWindowDecoration);
                ((HandlerExecutor) assistContentRequester.systemInteractionExecutor).execute(new Runnable() { // from class: com.android.wm.shell.apptoweb.AssistContentRequester$requestAssistContent$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        try {
                            IActivityTaskManager iActivityTaskManager = AssistContentRequester.this.activityTaskManager;
                            AssistContentRequester.AssistDataReceiver assistDataReceiver = new AssistContentRequester.AssistDataReceiver(desktopModeWindowDecoration$$ExternalSyntheticLambda12, AssistContentRequester.this);
                            int i6 = i5;
                            AssistContentRequester assistContentRequester2 = AssistContentRequester.this;
                            if (iActivityTaskManager.requestAssistDataForTask(assistDataReceiver, i6, assistContentRequester2.packageName, assistContentRequester2.attributionTag, false)) {
                                return;
                            }
                            AssistContentRequester assistContentRequester3 = AssistContentRequester.this;
                            final DesktopModeWindowDecoration$$ExternalSyntheticLambda12 desktopModeWindowDecoration$$ExternalSyntheticLambda122 = desktopModeWindowDecoration$$ExternalSyntheticLambda12;
                            ((HandlerExecutor) assistContentRequester3.callBackExecutor).execute(new Runnable() { // from class: com.android.wm.shell.apptoweb.AssistContentRequester$requestAssistContent$1.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    DesktopModeWindowDecoration$$ExternalSyntheticLambda12.this.f$0.onAssistContentReceived(null);
                                }
                            });
                        } catch (RemoteException e) {
                            Slog.e("AssistContentRequester", "Requesting assist content failed for task: " + i5, e);
                        }
                    }
                });
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public final boolean onDoubleTapEvent(MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (this.mIsDragging) {
                return false;
            }
            if (actionMasked != 1 && actionMasked != 3) {
                return false;
            }
            DesktopModeWindowDecorViewModel.this.onMaximizeOrRestore(this.mTaskId, "double_tap");
            return true;
        }

        @Override // android.view.View.OnGenericMotionListener
        public final boolean onGenericMotion(View view, MotionEvent motionEvent) {
            final int i = 1;
            final int i2 = 0;
            DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) DesktopModeWindowDecorViewModel.this.mWindowDecorByTaskId.get(this.mTaskId);
            int id = view.getId();
            if (motionEvent.getAction() != 9 || id != R.id.maximize_window) {
                if (motionEvent.getAction() != 10 || id != R.id.maximize_window) {
                    return false;
                }
                desktopModeWindowDecoration.mIsAppHeaderMaximizeButtonHovered = false;
                desktopModeWindowDecoration.onMaximizeHoverStateChanged();
                desktopModeWindowDecoration.onMaximizeHoverStateChanged();
                if (!desktopModeWindowDecoration.isMaximizeMenuActive()) {
                    WindowDecorationViewHolder windowDecorationViewHolder = desktopModeWindowDecoration.mWindowDecorViewHolder;
                    (windowDecorationViewHolder instanceof AppHeaderViewHolder ? (AppHeaderViewHolder) windowDecorationViewHolder : null).maximizeButtonView.cancelHoverAnimation();
                }
                return true;
            }
            desktopModeWindowDecoration.mIsAppHeaderMaximizeButtonHovered = true;
            desktopModeWindowDecoration.onMaximizeHoverStateChanged();
            if (!desktopModeWindowDecoration.isMaximizeMenuActive()) {
                WindowDecorationViewHolder windowDecorationViewHolder2 = desktopModeWindowDecoration.mWindowDecorViewHolder;
                final MaximizeButtonView maximizeButtonView = (windowDecorationViewHolder2 instanceof AppHeaderViewHolder ? (AppHeaderViewHolder) windowDecorationViewHolder2 : null).maximizeButtonView;
                if (!maximizeButtonView.hoverDisabled) {
                    if (maximizeButtonView.hoverProgressAnimatorSet.isRunning()) {
                        maximizeButtonView.cancelHoverAnimation();
                    }
                    maximizeButtonView.maximizeWindow.getBackground().setAlpha(0);
                    AnimatorSet animatorSet = maximizeButtonView.hoverProgressAnimatorSet;
                    final ValueAnimator duration = ValueAnimator.ofInt(0, 255).setDuration(50L);
                    duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.wm.shell.windowdecor.MaximizeButtonView$startHoverAnimation$1$1
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            MaximizeButtonView.this.maximizeWindow.getBackground().setAlpha(((Integer) duration.getAnimatedValue()).intValue());
                        }
                    });
                    ObjectAnimator duration2 = ObjectAnimator.ofInt(maximizeButtonView.progressBar, "progress", 100).setDuration(350L);
                    Intrinsics.checkNotNull(duration2);
                    duration2.addListener(new Animator.AnimatorListener() { // from class: com.android.wm.shell.windowdecor.MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnEnd$1
                        @Override // android.animation.Animator.AnimatorListener
                        public final void onAnimationCancel(Animator animator) {
                            int i3 = i;
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            switch (i) {
                                case 0:
                                    maximizeButtonView.progressBar.setVisibility(4);
                                    DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = maximizeButtonView.onHoverAnimationFinishedListener;
                                    if (desktopModeWindowDecoration$$ExternalSyntheticLambda3 == null) {
                                        desktopModeWindowDecoration$$ExternalSyntheticLambda3 = null;
                                    }
                                    desktopModeWindowDecoration$$ExternalSyntheticLambda3.invoke();
                                    break;
                            }
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public final void onAnimationRepeat(Animator animator) {
                            int i3 = i;
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public final void onAnimationStart(Animator animator) {
                            switch (i) {
                                case 0:
                                    break;
                                default:
                                    maximizeButtonView.progressBar.setProgress(0, false);
                                    maximizeButtonView.progressBar.setVisibility(0);
                                    break;
                            }
                        }

                        private final void onAnimationCancel$com$android$wm$shell$windowdecor$MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnEnd$1(Animator animator) {
                        }

                        private final void onAnimationCancel$com$android$wm$shell$windowdecor$MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnStart$1(Animator animator) {
                        }

                        private final void onAnimationEnd$com$android$wm$shell$windowdecor$MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnStart$1(Animator animator) {
                        }

                        private final void onAnimationRepeat$com$android$wm$shell$windowdecor$MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnEnd$1(Animator animator) {
                        }

                        private final void onAnimationRepeat$com$android$wm$shell$windowdecor$MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnStart$1(Animator animator) {
                        }

                        private final void onAnimationStart$com$android$wm$shell$windowdecor$MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnEnd$1(Animator animator) {
                        }
                    });
                    duration2.addListener(new Animator.AnimatorListener() { // from class: com.android.wm.shell.windowdecor.MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnEnd$1
                        @Override // android.animation.Animator.AnimatorListener
                        public final void onAnimationCancel(Animator animator) {
                            int i3 = i2;
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            switch (i2) {
                                case 0:
                                    maximizeButtonView.progressBar.setVisibility(4);
                                    DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3 = maximizeButtonView.onHoverAnimationFinishedListener;
                                    if (desktopModeWindowDecoration$$ExternalSyntheticLambda3 == null) {
                                        desktopModeWindowDecoration$$ExternalSyntheticLambda3 = null;
                                    }
                                    desktopModeWindowDecoration$$ExternalSyntheticLambda3.invoke();
                                    break;
                            }
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public final void onAnimationRepeat(Animator animator) {
                            int i3 = i2;
                        }

                        @Override // android.animation.Animator.AnimatorListener
                        public final void onAnimationStart(Animator animator) {
                            switch (i2) {
                                case 0:
                                    break;
                                default:
                                    maximizeButtonView.progressBar.setProgress(0, false);
                                    maximizeButtonView.progressBar.setVisibility(0);
                                    break;
                            }
                        }

                        private final void onAnimationCancel$com$android$wm$shell$windowdecor$MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnEnd$1(Animator animator) {
                        }

                        private final void onAnimationCancel$com$android$wm$shell$windowdecor$MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnStart$1(Animator animator) {
                        }

                        private final void onAnimationEnd$com$android$wm$shell$windowdecor$MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnStart$1(Animator animator) {
                        }

                        private final void onAnimationRepeat$com$android$wm$shell$windowdecor$MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnEnd$1(Animator animator) {
                        }

                        private final void onAnimationRepeat$com$android$wm$shell$windowdecor$MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnStart$1(Animator animator) {
                        }

                        private final void onAnimationStart$com$android$wm$shell$windowdecor$MaximizeButtonView$startHoverAnimation$lambda$3$$inlined$doOnEnd$1(Animator animator) {
                        }
                    });
                    animatorSet.playSequentially(duration, duration2);
                    maximizeButtonView.hoverProgressAnimatorSet.start();
                }
            }
            return true;
        }

        @Override // android.view.View.OnLongClickListener
        public final boolean onLongClick(View view) {
            if (view.getId() != R.id.maximize_window || !this.mTouchscreenInUse) {
                return false;
            }
            DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) DesktopModeWindowDecorViewModel.this.mWindowDecorByTaskId.get(this.mTaskId);
            ActivityManager.RunningTaskInfo runningTaskInfo = desktopModeWindowDecoration.mTaskInfo;
            if (!runningTaskInfo.isFocused) {
                DesktopModeWindowDecorViewModel.this.mDesktopTasksController.moveTaskToFront(runningTaskInfo);
            }
            if (desktopModeWindowDecoration.isMaximizeMenuActive()) {
                desktopModeWindowDecoration.closeMaximizeMenu();
            } else {
                this.mHasLongClicked = true;
                desktopModeWindowDecoration.createMaximizeMenu();
            }
            return true;
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            int id = view.getId();
            DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) DesktopModeWindowDecorViewModel.this.mWindowDecorByTaskId.get(this.mTaskId);
            if ((motionEvent.getSource() & 4098) == 4098) {
                this.mTouchscreenInUse = (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3) ? false : true;
            }
            if (id != R.id.caption_handle && id != R.id.desktop_mode_caption && id != R.id.open_menu_button && id != R.id.close_window && id != R.id.maximize_window && id != R.id.minimize_window) {
                return false;
            }
            boolean isFreeform = ((DesktopModeWindowDecoration) DesktopModeWindowDecorViewModel.this.mWindowDecorByTaskId.get(this.mTaskId)).mTaskInfo.isFreeform();
            int actionMasked = motionEvent.getActionMasked();
            boolean z = actionMasked == 0;
            boolean z2 = actionMasked == 3 || actionMasked == 1;
            if (z) {
                ActivityManager.RunningTaskInfo runningTaskInfo = desktopModeWindowDecoration.mTaskInfo;
                if (!runningTaskInfo.isFocused) {
                    DesktopModeWindowDecorViewModel.this.mDesktopTasksController.moveTaskToFront(runningTaskInfo);
                }
                boolean contains = desktopModeWindowDecoration.mResult.mCustomizableCaptionRegion.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                boolean contains2 = DesktopModeWindowDecorViewModel.this.mExclusionRegion.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
                boolean isTransparentCaptionBarAppearance = TaskInfoKt.isTransparentCaptionBarAppearance(desktopModeWindowDecoration.mTaskInfo);
                int[] iArr = new int[2];
                view.getLocationInWindow(iArr);
                Point point = new Point(iArr[0], iArr[1]);
                DragResizeInputListener dragResizeInputListener = desktopModeWindowDecoration.mDragResizeListener;
                this.mShouldPilferCaptionEvents = ((contains && contains2 && isTransparentCaptionBarAppearance) || (dragResizeInputListener != null && dragResizeInputListener.mInputEventReceiver.mDragResizeWindowGeometry.shouldHandleEvent(motionEvent, point))) ? false : true;
            }
            if (!this.mShouldPilferCaptionEvents) {
                return false;
            }
            InputManager inputManager = DesktopModeWindowDecorViewModel.this.mInputManager;
            if (inputManager != null) {
                inputManager.pilferPointers(view.getViewRootImpl().getInputToken());
            }
            if (z2) {
                this.mShouldPilferCaptionEvents = false;
            }
            return !isFreeform ? this.mHandleDragDetector.onMotionEvent(view, motionEvent) : this.mHeaderDragDetector.onMotionEvent(view, motionEvent);
        }

        public final void updateDragStatus(int i) {
            if (i != 0 && i != 1) {
                if (i == 2) {
                    this.mIsDragging = true;
                    return;
                } else if (i != 3) {
                    return;
                }
            }
            this.mIsDragging = false;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DragStartListenerImpl implements DragPositioningCallbackUtility.DragStartListener {
        public /* synthetic */ DragStartListenerImpl() {
        }

        public void onAnimationEnd(int i) {
            DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) DesktopModeWindowDecorViewModel.this.mWindowDecorByTaskId.get(i);
            if (desktopModeWindowDecoration == null) {
                return;
            }
            desktopModeWindowDecoration.hideResizeVeil();
            desktopModeWindowDecoration.setAnimatingTaskResizeOrReposition(false);
        }

        public void onAnimationStart(int i, SurfaceControl.Transaction transaction, Rect rect) {
            DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) DesktopModeWindowDecorViewModel.this.mWindowDecorByTaskId.get(i);
            if (desktopModeWindowDecoration == null) {
                transaction.apply();
                return;
            }
            if (desktopModeWindowDecoration.mResizeVeil == null) {
                desktopModeWindowDecoration.loadAppInfoIfNeeded();
                desktopModeWindowDecoration.mResizeVeil = new ResizeVeil(desktopModeWindowDecoration.mContext, desktopModeWindowDecoration.mDisplayController, desktopModeWindowDecoration.mResizeVeilBitmap, desktopModeWindowDecoration.mTaskSurface, desktopModeWindowDecoration.mSurfaceControlTransactionSupplier, desktopModeWindowDecoration.mTaskInfo);
            }
            desktopModeWindowDecoration.mResizeVeil.showVeil(transaction, desktopModeWindowDecoration.mTaskSurface, rect, desktopModeWindowDecoration.mTaskInfo, false);
            desktopModeWindowDecoration.setAnimatingTaskResizeOrReposition(true);
        }

        public void onBoundsChange(int i, SurfaceControl.Transaction transaction, Rect rect) {
            DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) DesktopModeWindowDecorViewModel.this.mWindowDecorByTaskId.get(i);
            if (desktopModeWindowDecoration == null) {
                return;
            }
            desktopModeWindowDecoration.mResizeVeil.updateResizeVeil(rect, transaction);
        }

        @Override // com.android.wm.shell.windowdecor.DragPositioningCallbackUtility.DragStartListener
        public void onDragStart(int i) {
            ((DesktopModeWindowDecoration) DesktopModeWindowDecorViewModel.this.mWindowDecorByTaskId.get(i)).closeHandleMenu();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EventReceiver extends InputEventReceiver {
        public InputMonitor mInputMonitor;
        public int mTasksOnDisplay;

        public EventReceiver(InputMonitor inputMonitor, InputChannel inputChannel, Looper looper) {
            super(inputChannel, looper);
            this.mInputMonitor = inputMonitor;
            this.mTasksOnDisplay = 1;
        }

        public final void dispose() {
            InputMonitor inputMonitor = this.mInputMonitor;
            if (inputMonitor != null) {
                inputMonitor.dispose();
                this.mInputMonitor = null;
            }
            super.dispose();
        }

        public final void onInputEvent(InputEvent inputEvent) {
            DesktopModeWindowDecoration focusedDecor;
            boolean z = false;
            if (inputEvent instanceof MotionEvent) {
                DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel = DesktopModeWindowDecorViewModel.this;
                MotionEvent motionEvent = (MotionEvent) inputEvent;
                InputMonitor inputMonitor = this.mInputMonitor;
                DragToDesktopTransitionHandler.TransitionState transitionState = desktopModeWindowDecorViewModel.mDesktopTasksController.dragToDesktopTransitionHandler.transitionState;
                int draggedTaskId = transitionState != null ? transitionState.getDraggedTaskId() : -1;
                if (draggedTaskId != -1) {
                    focusedDecor = (DesktopModeWindowDecoration) desktopModeWindowDecorViewModel.mWindowDecorByTaskId.get(draggedTaskId);
                } else {
                    DesktopModeWindowDecoration focusedDecor2 = desktopModeWindowDecorViewModel.getFocusedDecor();
                    DesktopModeWindowDecoration desktopModeWindowDecoration = null;
                    if (focusedDecor2 != null) {
                        SplitScreenController splitScreenController = desktopModeWindowDecorViewModel.mSplitScreenController;
                        boolean z2 = splitScreenController != null && splitScreenController.isSplitScreenVisible();
                        SplitScreenController splitScreenController2 = desktopModeWindowDecorViewModel.mSplitScreenController;
                        boolean z3 = splitScreenController2 != null && splitScreenController2.isTaskInSplitScreen(focusedDecor2.mTaskInfo.taskId);
                        if (z2 && z3) {
                            ActivityManager.RunningTaskInfo taskInfo = desktopModeWindowDecorViewModel.mSplitScreenController.getTaskInfo(0);
                            ActivityManager.RunningTaskInfo taskInfo2 = desktopModeWindowDecorViewModel.mSplitScreenController.getTaskInfo(1);
                            if (taskInfo != null && taskInfo.getConfiguration().windowConfiguration.getBounds().contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                                desktopModeWindowDecoration = (DesktopModeWindowDecoration) desktopModeWindowDecorViewModel.mWindowDecorByTaskId.get(taskInfo.taskId);
                            } else if (taskInfo2 != null && taskInfo2.getConfiguration().windowConfiguration.getBounds().contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                                Rect bounds = taskInfo2.getConfiguration().windowConfiguration.getBounds();
                                motionEvent.offsetLocation(-bounds.left, -bounds.top);
                                desktopModeWindowDecoration = (DesktopModeWindowDecoration) desktopModeWindowDecorViewModel.mWindowDecorByTaskId.get(taskInfo2.taskId);
                            }
                            if (desktopModeWindowDecoration == null) {
                                focusedDecor = desktopModeWindowDecorViewModel.getFocusedDecor();
                            }
                        } else {
                            focusedDecor = desktopModeWindowDecorViewModel.getFocusedDecor();
                        }
                    }
                    focusedDecor = desktopModeWindowDecoration;
                }
                if (DesktopModeStatus.canEnterDesktopMode(desktopModeWindowDecorViewModel.mContext) && !desktopModeWindowDecorViewModel.mInImmersiveMode && (focusedDecor == null || focusedDecor.mTaskInfo.getWindowingMode() != 5 || desktopModeWindowDecorViewModel.mTransitionDragActive)) {
                    desktopModeWindowDecorViewModel.handleCaptionThroughStatusBar(motionEvent, focusedDecor);
                }
                if (focusedDecor != null && !focusedDecor.checkTouchEventInCaption(motionEvent)) {
                    focusedDecor.updateHoverAndPressStatus(motionEvent);
                    int actionMasked = motionEvent.getActionMasked();
                    if ((actionMasked == 1 || actionMasked == 3) && !desktopModeWindowDecorViewModel.mTransitionDragActive) {
                        focusedDecor.closeHandleMenuIfNeeded(motionEvent);
                    }
                }
                if (DesktopModeStatus.canEnterDesktopMode(desktopModeWindowDecorViewModel.mContext) && desktopModeWindowDecorViewModel.mTransitionDragActive) {
                    inputMonitor.pilferPointers();
                }
                z = true;
            }
            finishInputEvent(inputEvent, z);
        }

        public final String toString() {
            return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(new StringBuilder("EventReceiver{tasksOnDisplay="), this.mTasksOnDisplay, "}");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InputMonitorFactory {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class TaskPositionerFactory {
    }

    public DesktopModeWindowDecorViewModel(Context context, Handler handler, Choreographer choreographer, IWindowManager iWindowManager, InteractionJankMonitor interactionJankMonitor, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, ShellTaskOrganizer shellTaskOrganizer, AppToWebGenericLinksParser appToWebGenericLinksParser, AssistContentRequester assistContentRequester, DisplayController displayController, DisplayInsetsController displayInsetsController, MultiInstanceHelper multiInstanceHelper, ShellExecutor shellExecutor, ShellExecutor shellExecutor2, SyncTransactionQueue syncTransactionQueue, WindowDecorCaptionHandleRepository windowDecorCaptionHandleRepository, ShellCommandHandler shellCommandHandler, ShellController shellController, ShellInit shellInit, Transitions transitions, WindowDecorViewHostSupplier windowDecorViewHostSupplier, Optional optional, Optional optional2, Optional optional3) {
        this(context, shellExecutor, handler, choreographer, shellExecutor2, shellInit, shellCommandHandler, iWindowManager, shellTaskOrganizer, displayController, shellController, displayInsetsController, syncTransactionQueue, transitions, optional, appToWebGenericLinksParser, assistContentRequester, multiInstanceHelper, windowDecorViewHostSupplier, new DesktopModeWindowDecoration.Factory(), new InputMonitorFactory(), new EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0(), new AppHeaderViewHolder.Factory(), rootTaskDisplayAreaOrganizer, new SparseArray(), interactionJankMonitor, optional2, windowDecorCaptionHandleRepository, optional3, new TaskPositionerFactory());
    }

    public final void createWindowDecoration$1(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) this.mWindowDecorByTaskId.get(runningTaskInfo.taskId);
        if (desktopModeWindowDecoration != null) {
            desktopModeWindowDecoration.close();
        }
        Context context = this.mContext;
        Context createContextAsUser = context.createContextAsUser(UserHandle.of(runningTaskInfo.userId), 0);
        SplitScreenController splitScreenController = this.mSplitScreenController;
        Choreographer choreographer = this.mMainChoreographer;
        this.mDesktopModeWindowDecorFactory.getClass();
        DesktopModeWindowDecoration desktopModeWindowDecoration2 = new DesktopModeWindowDecoration(context, createContextAsUser, this.mDisplayController, splitScreenController, this.mTaskOrganizer, runningTaskInfo, surfaceControl, this.mMainHandler, this.mBgExecutor, choreographer, this.mSyncQueue, this.mAppHeaderViewHolderFactory, this.mRootTaskDisplayAreaOrganizer, this.mGenericLinksParser, this.mAssistContentRequester, this.mMultiInstanceHelper, this.mWindowDecorCaptionHandleRepository, this.mWindowDecorViewHostSupplier);
        this.mWindowDecorByTaskId.put(runningTaskInfo.taskId, desktopModeWindowDecoration2);
        InteractionJankMonitor interactionJankMonitor = this.mInteractionJankMonitor;
        Supplier supplier = this.mTransactionFactory;
        this.mTaskPositionerFactory.getClass();
        boolean z = DesktopModeStatus.IS_VEILED_RESIZE_ENABLED;
        ShellTaskOrganizer shellTaskOrganizer = this.mTaskOrganizer;
        DisplayController displayController = this.mDisplayController;
        DragStartListenerImpl dragStartListenerImpl = this.mDragStartListener;
        Transitions transitions = this.mTransitions;
        TaskPositioner veiledResizeTaskPositioner = z ? new VeiledResizeTaskPositioner(shellTaskOrganizer, desktopModeWindowDecoration2, displayController, dragStartListenerImpl, transitions, interactionJankMonitor, this.mMainHandler) : new FluidResizeTaskPositioner(shellTaskOrganizer, transitions, desktopModeWindowDecoration2, displayController, dragStartListenerImpl, supplier);
        if (DesktopModeFlags.ENABLE_WINDOWING_SCALED_RESIZING.isTrue()) {
            veiledResizeTaskPositioner = new FixedAspectRatioTaskPositionerDecorator(desktopModeWindowDecoration2, veiledResizeTaskPositioner);
        }
        desktopModeWindowDecoration2.mTaskDragResizer = veiledResizeTaskPositioner;
        DesktopModeTouchEventListener desktopModeTouchEventListener = new DesktopModeTouchEventListener(runningTaskInfo, veiledResizeTaskPositioner);
        desktopModeWindowDecoration2.mOnMaximizeOrRestoreClickListener = new DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3(this, runningTaskInfo, 0);
        desktopModeWindowDecoration2.mOnLeftSnapClickListener = new DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3(this, runningTaskInfo, 2);
        desktopModeWindowDecoration2.mOnRightSnapClickListener = new DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3(this, runningTaskInfo, 3);
        desktopModeWindowDecoration2.mOnToDesktopClickListener = new DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda6(this, runningTaskInfo, 0);
        desktopModeWindowDecoration2.mOnToFullscreenClickListener = new DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3(this, runningTaskInfo, 4);
        desktopModeWindowDecoration2.mOnToSplitscreenClickListener = new DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3(this, runningTaskInfo, 5);
        desktopModeWindowDecoration2.mOpenInBrowserClickListener = new DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda6(this, runningTaskInfo, 1);
        desktopModeWindowDecoration2.mOnNewWindowClickListener = new DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3(this, runningTaskInfo, 1);
        desktopModeWindowDecoration2.mOnManageWindowsClickListener = new DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3(this, desktopModeWindowDecoration2, 6);
        desktopModeWindowDecoration2.mOnCaptionButtonClickListener = desktopModeTouchEventListener;
        desktopModeWindowDecoration2.mOnCaptionTouchListener = desktopModeTouchEventListener;
        desktopModeWindowDecoration2.mOnCaptionLongClickListener = desktopModeTouchEventListener;
        desktopModeWindowDecoration2.mOnCaptionGenericMotionListener = desktopModeTouchEventListener;
        desktopModeWindowDecoration2.mExclusionRegionListener = this.mExclusionRegionListener;
        desktopModeWindowDecoration2.mDragPositioningCallback = veiledResizeTaskPositioner;
        desktopModeWindowDecoration2.relayout(runningTaskInfo, transaction, transaction2, false, false);
        incrementEventReceiverTasks(runningTaskInfo.displayId);
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void destroyWindowDecoration(ActivityManager.RunningTaskInfo runningTaskInfo) {
        DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) this.mWindowDecorByTaskId.get(runningTaskInfo.taskId);
        if (desktopModeWindowDecoration == null) {
            return;
        }
        desktopModeWindowDecoration.close();
        int i = runningTaskInfo.displayId;
        if (this.mEventReceiversByDisplay.contains(i)) {
            removeTaskFromEventReceiver(i);
        }
        this.mWindowDecorByTaskId.remove(runningTaskInfo.taskId);
    }

    public final DesktopModeWindowDecoration getFocusedDecor() {
        for (int size = this.mWindowDecorByTaskId.size() - 1; size >= 0; size--) {
            DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) this.mWindowDecorByTaskId.valueAt(size);
            if (desktopModeWindowDecoration != null && desktopModeWindowDecoration.mTaskInfo.isFocused) {
                return desktopModeWindowDecoration;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x0250  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void handleCaptionThroughStatusBar(android.view.MotionEvent r19, com.android.wm.shell.windowdecor.DesktopModeWindowDecoration r20) {
        /*
            Method dump skipped, instructions count: 728
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel.handleCaptionThroughStatusBar(android.view.MotionEvent, com.android.wm.shell.windowdecor.DesktopModeWindowDecoration):void");
    }

    public final void incrementEventReceiverTasks(int i) {
        if (this.mEventReceiversByDisplay.contains(i)) {
            ((EventReceiver) this.mEventReceiversByDisplay.get(i)).mTasksOnDisplay++;
            return;
        }
        InputManager inputManager = (InputManager) this.mContext.getSystemService(InputManager.class);
        this.mInputMonitorFactory.getClass();
        InputMonitor monitorGestureInput = inputManager.monitorGestureInput("caption-touch", i);
        this.mEventReceiversByDisplay.put(i, new EventReceiver(monitorGestureInput, monitorGestureInput.getInputChannel(), Looper.myLooper()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0060, code lost:
    
        if (r5.height() == r2.height()) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x007a, code lost:
    
        if (r5.height() != r2.height()) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMaximizeOrRestore(int r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 380
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel.onMaximizeOrRestore(int, java.lang.String):void");
    }

    public final void onSnapResize(int i, boolean z) {
        DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) this.mWindowDecorByTaskId.get(i);
        if (desktopModeWindowDecoration == null) {
            return;
        }
        if (desktopModeWindowDecoration.mTaskInfo.isResizeable || !DesktopModeFlags.DISABLE_NON_RESIZABLE_APP_SNAP_RESIZE.isTrue()) {
            this.mInteractionJankMonitor.begin(desktopModeWindowDecoration.mTaskSurface, this.mContext, this.mMainHandler, 118, "maximize_menu_resizable");
            ActivityManager.RunningTaskInfo runningTaskInfo = desktopModeWindowDecoration.mTaskInfo;
            this.mDesktopTasksController.snapToHalfScreen(runningTaskInfo, desktopModeWindowDecoration.mTaskSurface, runningTaskInfo.configuration.windowConfiguration.getBounds(), z ? DesktopTasksController.SnapPosition.LEFT : DesktopTasksController.SnapPosition.RIGHT);
        } else {
            Toast.makeText(this.mContext, R.string.desktop_mode_non_resizable_snap_text, 0).show();
        }
        desktopModeWindowDecoration.closeHandleMenu();
        desktopModeWindowDecoration.closeMaximizeMenu();
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void onTaskChanging(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) this.mWindowDecorByTaskId.get(runningTaskInfo.taskId);
        if (!shouldShowWindowDecor$1(runningTaskInfo)) {
            if (desktopModeWindowDecoration != null) {
                destroyWindowDecoration(runningTaskInfo);
            }
        } else if (desktopModeWindowDecoration == null) {
            createWindowDecoration$1(runningTaskInfo, surfaceControl, transaction, transaction2);
        } else {
            desktopModeWindowDecoration.relayout(runningTaskInfo, transaction, transaction2, false, false);
        }
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void onTaskClosing(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) this.mWindowDecorByTaskId.get(runningTaskInfo.taskId);
        if (desktopModeWindowDecoration == null) {
            return;
        }
        desktopModeWindowDecoration.relayout(runningTaskInfo, transaction, transaction2, false, false);
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo) {
        DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) this.mWindowDecorByTaskId.get(runningTaskInfo.taskId);
        if (desktopModeWindowDecoration == null) {
            return;
        }
        ActivityManager.RunningTaskInfo runningTaskInfo2 = desktopModeWindowDecoration.mTaskInfo;
        int i = runningTaskInfo.displayId;
        int i2 = runningTaskInfo2.displayId;
        if (i != i2) {
            removeTaskFromEventReceiver(i2);
            incrementEventReceiverTasks(runningTaskInfo.displayId);
        }
        desktopModeWindowDecoration.relayout(runningTaskInfo);
        this.mActivityOrientationChangeHandler.ifPresent(new DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda12(0, runningTaskInfo2, runningTaskInfo));
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final boolean onTaskOpening(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        if (!shouldShowWindowDecor$1(runningTaskInfo)) {
            return false;
        }
        createWindowDecoration$1(runningTaskInfo, surfaceControl, transaction, transaction2);
        return true;
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo) {
        boolean z = this.mTaskOrganizer.getRunningTaskInfo(runningTaskInfo.taskId) == null;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_DESKTOP_MODE_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, -2894319029625802502L, 13, Long.valueOf(runningTaskInfo.taskId), Boolean.valueOf(z));
        }
        if (z) {
            destroyWindowDecoration(runningTaskInfo);
        }
    }

    public final void removeTaskFromEventReceiver(int i) {
        EventReceiver eventReceiver;
        EventReceiver eventReceiver2;
        if (this.mEventReceiversByDisplay.contains(i) && (eventReceiver = (EventReceiver) this.mEventReceiversByDisplay.get(i)) != null) {
            int i2 = eventReceiver.mTasksOnDisplay - 1;
            eventReceiver.mTasksOnDisplay = i2;
            if (i2 != 0 || (eventReceiver2 = (EventReceiver) this.mEventReceiversByDisplay.removeReturnOld(i)) == null) {
                return;
            }
            eventReceiver2.dispose();
        }
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void setFreeformTaskTransitionStarter(FreeformTaskTransitionHandler freeformTaskTransitionHandler) {
        this.mTaskOperations = new TaskOperations(freeformTaskTransitionHandler, this.mContext, this.mSyncQueue);
    }

    @Override // com.android.wm.shell.windowdecor.WindowDecorViewModel
    public final void setSplitScreenController(SplitScreenController splitScreenController) {
        this.mSplitScreenController = splitScreenController;
        splitScreenController.registerSplitScreenListener(new SplitScreen.SplitScreenListener() { // from class: com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel.2
            @Override // com.android.wm.shell.splitscreen.SplitScreen.SplitScreenListener
            public final void onTaskStageChanged(int i, int i2, boolean z) {
                if (!z || i2 == -1) {
                    return;
                }
                DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel = DesktopModeWindowDecorViewModel.this;
                DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) desktopModeWindowDecorViewModel.mWindowDecorByTaskId.get(i);
                if (desktopModeWindowDecoration == null || !DesktopModeStatus.canEnterDesktopMode(desktopModeWindowDecorViewModel.mContext)) {
                    return;
                }
                ActivityManager.RunningTaskInfo runningTaskInfo = desktopModeWindowDecoration.mTaskInfo;
                DesktopTasksController desktopTasksController = desktopModeWindowDecorViewModel.mDesktopTasksController;
                desktopTasksController.getClass();
                DesktopTasksController.logV("moveToSplit taskId=%s", Integer.valueOf(runningTaskInfo.taskId));
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                windowContainerTransaction.setBounds(runningTaskInfo.token, new Rect());
                windowContainerTransaction.setWindowingMode(runningTaskInfo.token, 0);
                if (Transitions.ENABLE_SHELL_TRANSITIONS) {
                    desktopTasksController.transitions.startTransition(6, windowContainerTransaction, null);
                } else {
                    desktopTasksController.shellTaskOrganizer.applyTransaction(windowContainerTransaction);
                }
            }
        });
    }

    public final boolean shouldShowWindowDecor$1(ActivityManager.RunningTaskInfo runningTaskInfo) {
        if (runningTaskInfo.getWindowingMode() == 5) {
            return true;
        }
        SplitScreenController splitScreenController = this.mSplitScreenController;
        if (splitScreenController != null && splitScreenController.isTaskRootOrStageRoot(runningTaskInfo.taskId)) {
            return false;
        }
        if (DesktopModeFlags.ENABLE_DESKTOP_WINDOWING_MODALS_POLICY.isTrue() && AppCompatUtils.isTopActivityExemptFromDesktopWindowing(this.mContext, runningTaskInfo)) {
            return false;
        }
        if (DesktopModeStatus.canEnterDesktopMode(this.mContext)) {
            ComponentName componentName = DesktopWallpaperActivity.wallpaperActivityComponent;
            ComponentName component = runningTaskInfo.baseIntent.getComponent();
            if (!(component != null ? component.equals(DesktopWallpaperActivity.wallpaperActivityComponent) : false) && runningTaskInfo.getWindowingMode() != 2 && runningTaskInfo.getActivityType() == 1 && !runningTaskInfo.configuration.windowConfiguration.isAlwaysOnTop()) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda0] */
    public DesktopModeWindowDecorViewModel(Context context, ShellExecutor shellExecutor, Handler handler, Choreographer choreographer, ShellExecutor shellExecutor2, ShellInit shellInit, ShellCommandHandler shellCommandHandler, IWindowManager iWindowManager, ShellTaskOrganizer shellTaskOrganizer, DisplayController displayController, ShellController shellController, DisplayInsetsController displayInsetsController, SyncTransactionQueue syncTransactionQueue, Transitions transitions, Optional optional, AppToWebGenericLinksParser appToWebGenericLinksParser, AssistContentRequester assistContentRequester, MultiInstanceHelper multiInstanceHelper, WindowDecorViewHostSupplier windowDecorViewHostSupplier, DesktopModeWindowDecoration.Factory factory, InputMonitorFactory inputMonitorFactory, Supplier supplier, AppHeaderViewHolder.Factory factory2, RootTaskDisplayAreaOrganizer rootTaskDisplayAreaOrganizer, SparseArray sparseArray, InteractionJankMonitor interactionJankMonitor, Optional optional2, WindowDecorCaptionHandleRepository windowDecorCaptionHandleRepository, Optional optional3, TaskPositionerFactory taskPositionerFactory) {
        this.mEventReceiversByDisplay = new SparseArray();
        this.mExclusionRegionListener = new DragStartListenerImpl();
        this.mDragStartListener = new DragStartListenerImpl();
        this.mDragToDesktopAnimationStartBounds = new Rect();
        this.mDesktopModeKeyguardChangeListener = new DesktopModeKeyguardChangeListener();
        this.mExclusionRegion = Region.obtain();
        this.mGestureExclusionListener = new AnonymousClass1();
        this.mContext = context;
        this.mMainExecutor = shellExecutor;
        this.mMainHandler = handler;
        this.mMainChoreographer = choreographer;
        this.mBgExecutor = shellExecutor2;
        this.mActivityTaskManager = (ActivityTaskManager) context.getSystemService(ActivityTaskManager.class);
        this.mTaskOrganizer = shellTaskOrganizer;
        this.mShellController = shellController;
        this.mDisplayController = displayController;
        this.mDisplayInsetsController = displayInsetsController;
        this.mSyncQueue = syncTransactionQueue;
        this.mTransitions = transitions;
        this.mDesktopTasksController = (DesktopTasksController) optional.get();
        this.mMultiInstanceHelper = multiInstanceHelper;
        this.mShellCommandHandler = shellCommandHandler;
        this.mWindowManager = iWindowManager;
        this.mWindowDecorViewHostSupplier = windowDecorViewHostSupplier;
        this.mDesktopModeWindowDecorFactory = factory;
        this.mInputMonitorFactory = inputMonitorFactory;
        this.mTransactionFactory = supplier;
        this.mAppHeaderViewHolderFactory = factory2;
        this.mRootTaskDisplayAreaOrganizer = rootTaskDisplayAreaOrganizer;
        this.mGenericLinksParser = appToWebGenericLinksParser;
        this.mInputManager = (InputManager) context.getSystemService(InputManager.class);
        this.mWindowDecorByTaskId = sparseArray;
        context.getResources().getString(android.R.string.config_systemUi);
        this.mInteractionJankMonitor = interactionJankMonitor;
        this.mDesktopTasksLimiter = optional2;
        this.mWindowDecorCaptionHandleRepository = windowDecorCaptionHandleRepository;
        this.mActivityOrientationChangeHandler = optional3;
        this.mAssistContentRequester = assistContentRequester;
        this.mOnDisplayChangingListener = new DisplayChangeController.OnDisplayChangingListener() { // from class: com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda0
            @Override // com.android.wm.shell.common.DisplayChangeController.OnDisplayChangingListener
            public final void onDisplayChange$1(int i, int i2, int i3, DisplayAreaInfo displayAreaInfo, WindowContainerTransaction windowContainerTransaction) {
                int i4 = 0;
                while (true) {
                    DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel = DesktopModeWindowDecorViewModel.this;
                    if (i4 >= desktopModeWindowDecorViewModel.mWindowDecorByTaskId.size()) {
                        return;
                    }
                    DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) desktopModeWindowDecorViewModel.mWindowDecorByTaskId.valueAt(i4);
                    if (desktopModeWindowDecoration != null) {
                        ActivityManager.RunningTaskInfo runningTaskInfo = desktopModeWindowDecoration.mTaskInfo;
                        if (i == runningTaskInfo.displayId && runningTaskInfo.isFreeform() && i2 % 2 != i3 % 2) {
                            Rect rect = new Rect(runningTaskInfo.configuration.windowConfiguration.getBounds());
                            if (DragPositioningCallbackUtility.snapTaskBoundsIfNecessary(rect, desktopModeWindowDecoration.calculateValidDragArea())) {
                                windowContainerTransaction.setBounds(runningTaskInfo.token, rect);
                            }
                        }
                    }
                    i4++;
                }
            }
        };
        this.mTaskPositionerFactory = taskPositionerFactory;
        shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                final DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel = DesktopModeWindowDecorViewModel.this;
                desktopModeWindowDecorViewModel.mShellController.addKeyguardChangeListener(desktopModeWindowDecorViewModel.mDesktopModeKeyguardChangeListener);
                desktopModeWindowDecorViewModel.mShellCommandHandler.addDumpCallback(new BiConsumer() { // from class: com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda2
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel2 = DesktopModeWindowDecorViewModel.this;
                        PrintWriter printWriter = (PrintWriter) obj;
                        String str = (String) obj2;
                        String m = DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m(str, "  ");
                        printWriter.println(str + "DesktopModeWindowDecorViewModel");
                        printWriter.println(m + "DesktopModeStatus=" + DesktopModeStatus.canEnterDesktopMode(desktopModeWindowDecorViewModel2.mContext));
                        StringBuilder sb = new StringBuilder();
                        sb.append(m);
                        sb.append("mTransitionDragActive=");
                        StringBuilder m2 = BackAnimationController$$ExternalSyntheticOutline0.m(sb, desktopModeWindowDecorViewModel2.mTransitionDragActive, printWriter, m, "mEventReceiversByDisplay=");
                        m2.append(desktopModeWindowDecorViewModel2.mEventReceiversByDisplay);
                        printWriter.println(m2.toString());
                        printWriter.println(m + "mWindowDecorByTaskId=" + desktopModeWindowDecorViewModel2.mWindowDecorByTaskId);
                    }
                }, desktopModeWindowDecorViewModel);
                DesktopModeWindowDecorViewModel.DesktopModeOnInsetsChangedListener desktopModeOnInsetsChangedListener = desktopModeWindowDecorViewModel.new DesktopModeOnInsetsChangedListener();
                DisplayInsetsController displayInsetsController2 = desktopModeWindowDecorViewModel.mDisplayInsetsController;
                if (!displayInsetsController2.mGlobalListeners.contains(desktopModeOnInsetsChangedListener)) {
                    displayInsetsController2.mGlobalListeners.add(desktopModeOnInsetsChangedListener);
                }
                DesktopModeWindowDecorViewModel.DragStartListenerImpl dragStartListenerImpl = desktopModeWindowDecorViewModel.new DragStartListenerImpl();
                DesktopTasksController desktopTasksController = desktopModeWindowDecorViewModel.mDesktopTasksController;
                desktopTasksController.toggleResizeDesktopTaskTransitionHandler.onTaskResizeAnimationListener = dragStartListenerImpl;
                desktopTasksController.enterDesktopTaskTransitionHandler.mOnTaskResizeAnimationListener = dragStartListenerImpl;
                desktopTasksController.dragToDesktopTransitionHandler.onTaskResizeAnimationListener = dragStartListenerImpl;
                desktopTasksController.returnToDragStartAnimator.taskRepositionAnimationListener = desktopModeWindowDecorViewModel.new DragStartListenerImpl();
                desktopModeWindowDecorViewModel.mDisplayController.addDisplayChangingController(desktopModeWindowDecorViewModel.mOnDisplayChangingListener);
                try {
                    desktopModeWindowDecorViewModel.mWindowManager.registerSystemGestureExclusionListener(desktopModeWindowDecorViewModel.mGestureExclusionListener, desktopModeWindowDecorViewModel.mContext.getDisplayId());
                } catch (RemoteException e) {
                    Log.e("DesktopModeWindowDecorViewModel", "Failed to register window manager callbacks", e);
                }
            }
        }, this);
    }
}

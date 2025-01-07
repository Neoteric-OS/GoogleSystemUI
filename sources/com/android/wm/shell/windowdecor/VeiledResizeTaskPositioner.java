package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.Choreographer;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.desktopmode.EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.DragPositioningCallbackUtility;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VeiledResizeTaskPositioner implements TaskPositioner, Transitions.TransitionHandler {
    public int mCtrlType;
    public final DesktopModeWindowDecoration mDesktopWindowDecoration;
    public final DisplayController mDisplayController;
    public final DragPositioningCallbackUtility.DragStartListener mDragStartListener;
    public final Handler mHandler;
    public final InteractionJankMonitor mInteractionJankMonitor;
    public boolean mIsResizingOrAnimatingResize;
    public final PointF mRepositionStartPoint;
    public final Rect mRepositionTaskBounds;
    public int mRotation;
    public final Rect mStableBounds;
    public final Rect mTaskBoundsAtDragStart;
    public final ShellTaskOrganizer mTaskOrganizer;
    public final EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0 mTransactionSupplier;
    public final Transitions mTransitions;

    public VeiledResizeTaskPositioner(ShellTaskOrganizer shellTaskOrganizer, DesktopModeWindowDecoration desktopModeWindowDecoration, DisplayController displayController, DragPositioningCallbackUtility.DragStartListener dragStartListener, Transitions transitions, InteractionJankMonitor interactionJankMonitor, Handler handler) {
        EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0 enterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0 = new EnterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0();
        this.mStableBounds = new Rect();
        this.mTaskBoundsAtDragStart = new Rect();
        this.mRepositionStartPoint = new PointF();
        this.mRepositionTaskBounds = new Rect();
        this.mDesktopWindowDecoration = desktopModeWindowDecoration;
        this.mTaskOrganizer = shellTaskOrganizer;
        this.mDisplayController = displayController;
        this.mDragStartListener = dragStartListener;
        this.mTransactionSupplier = enterDesktopTaskTransitionHandler$$ExternalSyntheticLambda0;
        this.mTransitions = transitions;
        this.mInteractionJankMonitor = interactionJankMonitor;
        this.mHandler = handler;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        return null;
    }

    public final boolean isResizing$1$1() {
        int i = this.mCtrlType;
        return ((i & 4) == 0 && (i & 8) == 0 && (i & 1) == 0 && (i & 2) == 0) ? false : true;
    }

    @Override // com.android.wm.shell.windowdecor.TaskPositioner
    public final boolean isResizingOrAnimating() {
        return this.mIsResizingOrAnimatingResize;
    }

    @Override // com.android.wm.shell.windowdecor.TaskPositioner
    public final Rect onDragPositioningEnd(float f, float f2) {
        PointF calculateDelta = DragPositioningCallbackUtility.calculateDelta(f, f2, this.mRepositionStartPoint);
        if (isResizing$1$1()) {
            if (!this.mTaskBoundsAtDragStart.equals(this.mRepositionTaskBounds)) {
                DragPositioningCallbackUtility.changeBounds(this.mCtrlType, this.mRepositionTaskBounds, this.mTaskBoundsAtDragStart, this.mStableBounds, calculateDelta, this.mDisplayController, this.mDesktopWindowDecoration);
                Rect rect = this.mRepositionTaskBounds;
                DesktopModeWindowDecoration desktopModeWindowDecoration = this.mDesktopWindowDecoration;
                ResizeVeil resizeVeil = desktopModeWindowDecoration.mResizeVeil;
                if (resizeVeil.isVisible) {
                    resizeVeil.updateResizeVeil(rect, (SurfaceControl.Transaction) resizeVeil.surfaceControlTransactionSupplier.get());
                }
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                windowContainerTransaction.setBounds(desktopModeWindowDecoration.mTaskInfo.token, this.mRepositionTaskBounds);
                this.mTransitions.startTransition(6, windowContainerTransaction, this);
            } else if (this.mIsResizingOrAnimatingResize) {
                this.mDesktopWindowDecoration.hideResizeVeil();
                this.mIsResizingOrAnimatingResize = false;
            }
            this.mInteractionJankMonitor.end(106);
        } else {
            DragPositioningCallbackUtility.updateTaskBounds(this.mRepositionTaskBounds, this.mTaskBoundsAtDragStart, this.mRepositionStartPoint, f, f2);
            this.mInteractionJankMonitor.end(110);
        }
        this.mCtrlType = 0;
        this.mTaskBoundsAtDragStart.setEmpty();
        this.mRepositionStartPoint.set(0.0f, 0.0f);
        return new Rect(this.mRepositionTaskBounds);
    }

    @Override // com.android.wm.shell.windowdecor.TaskPositioner
    public final Rect onDragPositioningMove(float f, float f2) {
        Looper myLooper = Looper.myLooper();
        Handler handler = this.mHandler;
        if (myLooper != handler.getLooper()) {
            throw new IllegalStateException("This method must run on the shell main thread.");
        }
        PointF calculateDelta = DragPositioningCallbackUtility.calculateDelta(f, f2, this.mRepositionStartPoint);
        boolean isResizing$1$1 = isResizing$1$1();
        DesktopModeWindowDecoration desktopModeWindowDecoration = this.mDesktopWindowDecoration;
        if (isResizing$1$1 && DragPositioningCallbackUtility.changeBounds(this.mCtrlType, this.mRepositionTaskBounds, this.mTaskBoundsAtDragStart, this.mStableBounds, calculateDelta, this.mDisplayController, desktopModeWindowDecoration)) {
            if (this.mIsResizingOrAnimatingResize) {
                Rect rect = this.mRepositionTaskBounds;
                ResizeVeil resizeVeil = desktopModeWindowDecoration.mResizeVeil;
                if (resizeVeil.isVisible) {
                    resizeVeil.updateResizeVeil(rect, (SurfaceControl.Transaction) resizeVeil.surfaceControlTransactionSupplier.get());
                }
            } else {
                Rect rect2 = this.mRepositionTaskBounds;
                if (desktopModeWindowDecoration.mResizeVeil == null) {
                    desktopModeWindowDecoration.loadAppInfoIfNeeded();
                    desktopModeWindowDecoration.mResizeVeil = new ResizeVeil(desktopModeWindowDecoration.mContext, desktopModeWindowDecoration.mDisplayController, desktopModeWindowDecoration.mResizeVeilBitmap, desktopModeWindowDecoration.mTaskSurface, desktopModeWindowDecoration.mSurfaceControlTransactionSupplier, desktopModeWindowDecoration.mTaskInfo);
                }
                ResizeVeil resizeVeil2 = desktopModeWindowDecoration.mResizeVeil;
                SurfaceControl surfaceControl = desktopModeWindowDecoration.mTaskSurface;
                ActivityManager.RunningTaskInfo runningTaskInfo = desktopModeWindowDecoration.mTaskInfo;
                if (resizeVeil2.viewHost != null && !resizeVeil2.isVisible) {
                    resizeVeil2.showVeil((SurfaceControl.Transaction) resizeVeil2.surfaceControlTransactionSupplier.get(), surfaceControl, rect2, runningTaskInfo, true);
                }
                this.mIsResizingOrAnimatingResize = true;
            }
        } else if (this.mCtrlType == 0) {
            this.mInteractionJankMonitor.begin(desktopModeWindowDecoration.mTaskSurface, desktopModeWindowDecoration.mContext, handler, 110);
            this.mTransactionSupplier.getClass();
            SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
            Rect rect3 = this.mRepositionTaskBounds;
            Rect rect4 = this.mTaskBoundsAtDragStart;
            PointF pointF = this.mRepositionStartPoint;
            DesktopModeWindowDecoration desktopModeWindowDecoration2 = this.mDesktopWindowDecoration;
            DragPositioningCallbackUtility.updateTaskBounds(rect3, rect4, pointF, f, f2);
            transaction.setPosition(desktopModeWindowDecoration2.mTaskSurface, rect3.left, rect3.top);
            transaction.setFrameTimeline(Choreographer.getInstance().getVsyncId());
            transaction.apply();
        }
        return new Rect(this.mRepositionTaskBounds);
    }

    @Override // com.android.wm.shell.windowdecor.TaskPositioner
    public final Rect onDragPositioningStart(int i, float f, float f2) {
        this.mCtrlType = i;
        Rect rect = this.mTaskBoundsAtDragStart;
        DesktopModeWindowDecoration desktopModeWindowDecoration = this.mDesktopWindowDecoration;
        rect.set(desktopModeWindowDecoration.mTaskInfo.configuration.windowConfiguration.getBounds());
        this.mRepositionStartPoint.set(f, f2);
        if (isResizing$1$1()) {
            this.mInteractionJankMonitor.begin(desktopModeWindowDecoration.mTaskSurface, desktopModeWindowDecoration.mContext, this.mHandler, 106);
            if (!desktopModeWindowDecoration.mTaskInfo.isFocused) {
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                windowContainerTransaction.reorder(desktopModeWindowDecoration.mTaskInfo.token, true);
                this.mTaskOrganizer.applyTransaction(windowContainerTransaction);
            }
        }
        this.mDragStartListener.onDragStart(desktopModeWindowDecoration.mTaskInfo.taskId);
        this.mRepositionTaskBounds.set(this.mTaskBoundsAtDragStart);
        int displayRotation = desktopModeWindowDecoration.mTaskInfo.configuration.windowConfiguration.getDisplayRotation();
        if (this.mStableBounds.isEmpty() || this.mRotation != displayRotation) {
            this.mRotation = displayRotation;
            this.mDisplayController.getDisplayLayout(desktopModeWindowDecoration.mDisplay.getDisplayId()).getStableBounds(this.mStableBounds);
        }
        return new Rect(this.mRepositionTaskBounds);
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final boolean startAnimation(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2, Transitions.TransitionFinishCallback transitionFinishCallback) {
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            SurfaceControl leash = change.getLeash();
            Rect endAbsBounds = change.getEndAbsBounds();
            Point endRelOffset = change.getEndRelOffset();
            transaction.setWindowCrop(leash, endAbsBounds.width(), endAbsBounds.height()).setPosition(leash, endRelOffset.x, endRelOffset.y);
            transaction2.setWindowCrop(leash, endAbsBounds.width(), endAbsBounds.height()).setPosition(leash, endRelOffset.x, endRelOffset.y);
        }
        transaction.apply();
        if (this.mIsResizingOrAnimatingResize) {
            this.mDesktopWindowDecoration.hideResizeVeil();
            this.mIsResizingOrAnimatingResize = false;
        }
        this.mCtrlType = 0;
        transitionFinishCallback.onTransitionFinished(null);
        this.mIsResizingOrAnimatingResize = false;
        this.mInteractionJankMonitor.end(110);
        return true;
    }
}

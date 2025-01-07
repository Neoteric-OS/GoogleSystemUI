package com.android.wm.shell.windowdecor;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.IBinder;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.TransitionRequestInfo;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.DragPositioningCallbackUtility;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FluidResizeTaskPositioner implements TaskPositioner, Transitions.TransitionHandler {
    public int mCtrlType;
    public final DisplayController mDisplayController;
    public IBinder mDragResizeEndTransition;
    public final DragPositioningCallbackUtility.DragStartListener mDragStartListener;
    public boolean mHasDragResized;
    public boolean mIsResizingOrAnimatingResize;
    public int mRotation;
    public final ShellTaskOrganizer mTaskOrganizer;
    public final Supplier mTransactionSupplier;
    public final Transitions mTransitions;
    public final WindowDecoration mWindowDecoration;
    public final Rect mStableBounds = new Rect();
    public final Rect mTaskBoundsAtDragStart = new Rect();
    public final PointF mRepositionStartPoint = new PointF();
    public final Rect mRepositionTaskBounds = new Rect();

    public FluidResizeTaskPositioner(ShellTaskOrganizer shellTaskOrganizer, Transitions transitions, WindowDecoration windowDecoration, DisplayController displayController, DragPositioningCallbackUtility.DragStartListener dragStartListener, Supplier supplier) {
        this.mTaskOrganizer = shellTaskOrganizer;
        this.mTransitions = transitions;
        this.mWindowDecoration = windowDecoration;
        this.mDisplayController = displayController;
        this.mDragStartListener = dragStartListener;
        this.mTransactionSupplier = supplier;
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final WindowContainerTransaction handleRequest(IBinder iBinder, TransitionRequestInfo transitionRequestInfo) {
        return null;
    }

    public final boolean isResizing$1() {
        int i = this.mCtrlType;
        return ((i & 4) == 0 && (i & 8) == 0 && (i & 1) == 0 && (i & 2) == 0) ? false : true;
    }

    @Override // com.android.wm.shell.windowdecor.TaskPositioner
    public final boolean isResizingOrAnimating() {
        return this.mIsResizingOrAnimatingResize;
    }

    @Override // com.android.wm.shell.windowdecor.TaskPositioner
    public final Rect onDragPositioningEnd(float f, float f2) {
        if (isResizing$1() && this.mHasDragResized) {
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            WindowDecoration windowDecoration = this.mWindowDecoration;
            windowContainerTransaction.setDragResizing(windowDecoration.mTaskInfo.token, false);
            if (DragPositioningCallbackUtility.changeBounds(this.mCtrlType, this.mRepositionTaskBounds, this.mTaskBoundsAtDragStart, this.mStableBounds, DragPositioningCallbackUtility.calculateDelta(f, f2, this.mRepositionStartPoint), this.mDisplayController, this.mWindowDecoration)) {
                windowContainerTransaction.setBounds(windowDecoration.mTaskInfo.token, this.mRepositionTaskBounds);
            }
            this.mDragResizeEndTransition = this.mTransitions.startTransition(6, windowContainerTransaction, this);
        } else if (this.mCtrlType == 0) {
            DragPositioningCallbackUtility.updateTaskBounds(this.mRepositionTaskBounds, this.mTaskBoundsAtDragStart, this.mRepositionStartPoint, f, f2);
        }
        this.mTaskBoundsAtDragStart.setEmpty();
        this.mRepositionStartPoint.set(0.0f, 0.0f);
        this.mCtrlType = 0;
        this.mHasDragResized = false;
        return new Rect(this.mRepositionTaskBounds);
    }

    @Override // com.android.wm.shell.windowdecor.TaskPositioner
    public final Rect onDragPositioningMove(float f, float f2) {
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        PointF calculateDelta = DragPositioningCallbackUtility.calculateDelta(f, f2, this.mRepositionStartPoint);
        boolean isResizing$1 = isResizing$1();
        WindowDecoration windowDecoration = this.mWindowDecoration;
        if (isResizing$1 && DragPositioningCallbackUtility.changeBounds(this.mCtrlType, this.mRepositionTaskBounds, this.mTaskBoundsAtDragStart, this.mStableBounds, calculateDelta, this.mDisplayController, this.mWindowDecoration)) {
            if (!this.mHasDragResized) {
                windowContainerTransaction.setDragResizing(windowDecoration.mTaskInfo.token, true);
            }
            windowContainerTransaction.setBounds(windowDecoration.mTaskInfo.token, this.mRepositionTaskBounds);
            this.mTaskOrganizer.applyTransaction(windowContainerTransaction);
            this.mHasDragResized = true;
            this.mIsResizingOrAnimatingResize = true;
        } else if (this.mCtrlType == 0) {
            SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) this.mTransactionSupplier.get();
            DragPositioningCallbackUtility.updateTaskBounds(this.mRepositionTaskBounds, this.mTaskBoundsAtDragStart, this.mRepositionStartPoint, f, f2);
            transaction.setPosition(windowDecoration.mTaskSurface, r1.left, r1.top);
            transaction.apply();
        }
        return new Rect(this.mRepositionTaskBounds);
    }

    @Override // com.android.wm.shell.windowdecor.TaskPositioner
    public final Rect onDragPositioningStart(int i, float f, float f2) {
        this.mCtrlType = i;
        Rect rect = this.mTaskBoundsAtDragStart;
        WindowDecoration windowDecoration = this.mWindowDecoration;
        rect.set(windowDecoration.mTaskInfo.configuration.windowConfiguration.getBounds());
        this.mRepositionStartPoint.set(f, f2);
        this.mDragStartListener.onDragStart(windowDecoration.mTaskInfo.taskId);
        if (this.mCtrlType != 0 && !windowDecoration.mTaskInfo.isFocused) {
            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
            windowContainerTransaction.reorder(windowDecoration.mTaskInfo.token, true);
            this.mTaskOrganizer.applyTransaction(windowContainerTransaction);
        }
        this.mRepositionTaskBounds.set(this.mTaskBoundsAtDragStart);
        int displayRotation = windowDecoration.mTaskInfo.configuration.windowConfiguration.getDisplayRotation();
        if (this.mStableBounds.isEmpty() || this.mRotation != displayRotation) {
            this.mRotation = displayRotation;
            this.mDisplayController.getDisplayLayout(windowDecoration.mDisplay.getDisplayId()).getStableBounds(this.mStableBounds);
        }
        return new Rect(this.mRepositionTaskBounds);
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionHandler
    public final void onTransitionConsumed(IBinder iBinder, boolean z, SurfaceControl.Transaction transaction) {
        if (iBinder.equals(this.mDragResizeEndTransition)) {
            this.mIsResizingOrAnimatingResize = false;
            this.mDragResizeEndTransition = null;
        }
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
        if (iBinder.equals(this.mDragResizeEndTransition)) {
            this.mIsResizingOrAnimatingResize = false;
            this.mDragResizeEndTransition = null;
        }
        transitionFinishCallback.onTransitionFinished(null);
        return true;
    }
}

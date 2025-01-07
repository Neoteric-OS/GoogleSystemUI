package com.android.wm.shell.windowdecor;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.input.InputManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Size;
import android.view.Choreographer;
import android.view.IWindowSession;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputEventReceiver;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManagerGlobal;
import android.window.InputTransferToken;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.DisplayController;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.windowdecor.DragDetector;
import com.android.wm.shell.windowdecor.DragResizeInputListener;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DragResizeInputListener implements AutoCloseable {
    public final IBinder mClientToken;
    public final SurfaceControl mDecorationSurface;
    public final DisplayController mDisplayController;
    public final int mDisplayId;
    public final InputChannel mInputChannel;
    public final TaskResizeInputEventReceiver mInputEventReceiver;
    public final SurfaceControl mInputSinkSurface;
    public final IBinder mSinkClientToken;
    public final InputChannel mSinkInputChannel;
    public final Supplier mSurfaceControlTransactionSupplier;
    public final Region mTouchRegion;
    public final IWindowSession mWindowSession;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TaskResizeInputEventReceiver extends InputEventReceiver implements DragDetector.MotionEventHandler {
        public final TaskPositioner mCallback;
        public final Choreographer mChoreographer;
        public final DragResizeInputListener$TaskResizeInputEventReceiver$$ExternalSyntheticLambda0 mConsumeBatchEventRunnable;
        public boolean mConsumeBatchEventScheduled;
        public final Context mContext;
        public final DragResizeInputListener$$ExternalSyntheticLambda0 mDisplayLayoutSizeSupplier;
        public final DragDetector mDragDetector;
        public int mDragPointerId;
        public DragResizeWindowGeometry mDragResizeWindowGeometry;
        public Rect mDragStartTaskBounds;
        public final InputChannel mInputChannel;
        public final InputManager mInputManager;
        public int mLastCursorType;
        public boolean mShouldHandleEvents;
        public final Rect mTmpRect;
        public Region mTouchRegion;
        public final DragResizeInputListener$$ExternalSyntheticLambda1 mTouchRegionConsumer;

        /* JADX WARN: Type inference failed for: r2v1, types: [com.android.wm.shell.windowdecor.DragResizeInputListener$TaskResizeInputEventReceiver$$ExternalSyntheticLambda0] */
        public TaskResizeInputEventReceiver(Context context, InputChannel inputChannel, TaskPositioner taskPositioner, Handler handler, Choreographer choreographer, DragResizeInputListener$$ExternalSyntheticLambda0 dragResizeInputListener$$ExternalSyntheticLambda0, DragResizeInputListener$$ExternalSyntheticLambda1 dragResizeInputListener$$ExternalSyntheticLambda1) {
            super(inputChannel, handler.getLooper());
            this.mTmpRect = new Rect();
            this.mLastCursorType = 1000;
            this.mDragPointerId = -1;
            this.mContext = context;
            this.mInputManager = (InputManager) context.getSystemService(InputManager.class);
            this.mInputChannel = inputChannel;
            this.mCallback = taskPositioner;
            this.mChoreographer = choreographer;
            this.mConsumeBatchEventRunnable = new Runnable() { // from class: com.android.wm.shell.windowdecor.DragResizeInputListener$TaskResizeInputEventReceiver$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DragResizeInputListener.TaskResizeInputEventReceiver taskResizeInputEventReceiver = DragResizeInputListener.TaskResizeInputEventReceiver.this;
                    taskResizeInputEventReceiver.mConsumeBatchEventScheduled = false;
                    if (!taskResizeInputEventReceiver.consumeBatchedInputEvents(taskResizeInputEventReceiver.mChoreographer.getFrameTimeNanos()) || taskResizeInputEventReceiver.mConsumeBatchEventScheduled) {
                        return;
                    }
                    taskResizeInputEventReceiver.mChoreographer.postCallback(0, taskResizeInputEventReceiver.mConsumeBatchEventRunnable, null);
                    taskResizeInputEventReceiver.mConsumeBatchEventScheduled = true;
                }
            };
            this.mDragDetector = new DragDetector(this, ViewConfiguration.get(context).getScaledTouchSlop());
            this.mDisplayLayoutSizeSupplier = dragResizeInputListener$$ExternalSyntheticLambda0;
            this.mTouchRegionConsumer = dragResizeInputListener$$ExternalSyntheticLambda1;
        }

        @Override // com.android.wm.shell.windowdecor.DragDetector.MotionEventHandler
        public final boolean handleMotionEvent(View view, MotionEvent motionEvent) {
            int i;
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked != 2) {
                        if (actionMasked != 3) {
                            if (actionMasked == 7 || actionMasked == 9) {
                                int displayId = motionEvent.getDisplayId();
                                int deviceId = motionEvent.getDeviceId();
                                int pointerId = motionEvent.getPointerId(0);
                                switch (this.mDragResizeWindowGeometry.calculateCtrlType(motionEvent.getX(), motionEvent.getY(), false, true)) {
                                    case 1:
                                    case 2:
                                        i = 1014;
                                        break;
                                    case 3:
                                    case 7:
                                    default:
                                        i = 1000;
                                        break;
                                    case 4:
                                    case 8:
                                        i = 1015;
                                        break;
                                    case 5:
                                    case 10:
                                        i = 1017;
                                        break;
                                    case 6:
                                    case 9:
                                        i = 1016;
                                        break;
                                }
                                int i2 = this.mLastCursorType;
                                if (i2 != i || i != 1000) {
                                    if (ProtoLogImpl_411527699.Cache.WM_SHELL_DESKTOP_MODE_enabled[0]) {
                                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, 2613953847014407996L, 20, "DragResizeInputListener", Long.valueOf(i2), Long.valueOf(i));
                                    }
                                    this.mInputManager.setPointerIcon(PointerIcon.getSystemIcon(this.mContext, i), displayId, deviceId, pointerId, this.mInputChannel.getToken());
                                    this.mLastCursorType = i;
                                }
                            } else if (actionMasked != 10) {
                                return false;
                            }
                        }
                    } else {
                        if (!this.mShouldHandleEvents) {
                            return false;
                        }
                        this.mInputManager.pilferPointers(this.mInputChannel.getToken());
                        int findPointerIndex = motionEvent.findPointerIndex(this.mDragPointerId);
                        if (findPointerIndex < 0) {
                            if (!ProtoLogImpl_411527699.Cache.WM_SHELL_DESKTOP_MODE_enabled[0]) {
                                return false;
                            }
                            ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, 8943025630346689205L, 0, "DragResizeInputListener");
                            return false;
                        }
                        updateInputSinkRegionForDrag(this.mCallback.onDragPositioningMove(motionEvent.getRawX(findPointerIndex), motionEvent.getRawY(findPointerIndex)));
                    }
                }
                if (this.mShouldHandleEvents) {
                    int findPointerIndex2 = motionEvent.findPointerIndex(this.mDragPointerId);
                    if (findPointerIndex2 < 0) {
                        if (!ProtoLogImpl_411527699.Cache.WM_SHELL_DESKTOP_MODE_enabled[0]) {
                            return false;
                        }
                        ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, 6603006212180425160L, 4, "DragResizeInputListener", Long.valueOf(motionEvent.getActionMasked()));
                        return false;
                    }
                    if (this.mCallback.onDragPositioningEnd(motionEvent.getRawX(findPointerIndex2), motionEvent.getRawY(findPointerIndex2)).equals(this.mDragStartTaskBounds)) {
                        this.mTouchRegionConsumer.accept(this.mTouchRegion);
                    }
                }
                this.mShouldHandleEvents = false;
                this.mDragPointerId = -1;
            } else {
                boolean shouldHandleEvent = this.mDragResizeWindowGeometry.shouldHandleEvent(motionEvent, new Point());
                this.mShouldHandleEvents = shouldHandleEvent;
                if (!shouldHandleEvent) {
                    if (!ProtoLogImpl_411527699.Cache.WM_SHELL_DESKTOP_MODE_enabled[0]) {
                        return false;
                    }
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, -1822972204400427909L, 0, "DragResizeInputListener");
                    return false;
                }
                this.mDragPointerId = motionEvent.getPointerId(0);
                float x = motionEvent.getX(0);
                float y = motionEvent.getY(0);
                float rawX = motionEvent.getRawX(0);
                float rawY = motionEvent.getRawY(0);
                int calculateCtrlType = this.mDragResizeWindowGeometry.calculateCtrlType(x, y, (motionEvent.getSource() & 4098) == 4098, DragResizeWindowGeometry.isEdgeResizePermitted(motionEvent));
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_DESKTOP_MODE_enabled[0]) {
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, 2008231537477103770L, 4, "DragResizeInputListener", Long.valueOf(calculateCtrlType));
                }
                Rect onDragPositioningStart = this.mCallback.onDragPositioningStart(calculateCtrlType, rawX, rawY);
                this.mDragStartTaskBounds = onDragPositioningStart;
                updateInputSinkRegionForDrag(onDragPositioningStart);
            }
            return true;
        }

        public final void onBatchedInputEventPending(int i) {
            if (this.mConsumeBatchEventScheduled) {
                return;
            }
            this.mChoreographer.postCallback(0, this.mConsumeBatchEventRunnable, null);
            this.mConsumeBatchEventScheduled = true;
        }

        public final void onInputEvent(InputEvent inputEvent) {
            finishInputEvent(inputEvent, !(inputEvent instanceof MotionEvent) ? false : this.mDragDetector.onMotionEvent(null, (MotionEvent) inputEvent));
        }

        public final void updateInputSinkRegionForDrag(Rect rect) {
            this.mTmpRect.set(rect);
            Size size = (Size) this.mDisplayLayoutSizeSupplier.get();
            int i = -rect.left;
            Region region = new Region(i, -rect.top, size.getWidth() + i, size.getHeight() + (-rect.top));
            this.mTmpRect.offsetTo(0, 0);
            region.op(this.mTmpRect, Region.Op.DIFFERENCE);
            this.mTouchRegionConsumer.accept(region);
        }
    }

    public DragResizeInputListener(Context context, Handler handler, Choreographer choreographer, int i, SurfaceControl surfaceControl, TaskPositioner taskPositioner, Supplier supplier, Supplier supplier2, DisplayController displayController) {
        IWindowSession windowSession = WindowManagerGlobal.getWindowSession();
        this.mWindowSession = windowSession;
        this.mTouchRegion = new Region();
        this.mSurfaceControlTransactionSupplier = supplier2;
        this.mDisplayId = i;
        this.mDecorationSurface = surfaceControl;
        this.mDisplayController = displayController;
        Binder binder = new Binder();
        this.mClientToken = binder;
        InputTransferToken inputTransferToken = new InputTransferToken();
        InputChannel inputChannel = new InputChannel();
        this.mInputChannel = inputChannel;
        try {
            windowSession.grantInputChannel(i, surfaceControl, binder, (InputTransferToken) null, 8388616, 536870912, 4, 2, (IBinder) null, inputTransferToken, "DragResizeInputListener of " + surfaceControl.toString(), inputChannel);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
        TaskResizeInputEventReceiver taskResizeInputEventReceiver = new TaskResizeInputEventReceiver(context, this.mInputChannel, taskPositioner, handler, choreographer, new DragResizeInputListener$$ExternalSyntheticLambda0(this), new DragResizeInputListener$$ExternalSyntheticLambda1(this));
        this.mInputEventReceiver = taskResizeInputEventReceiver;
        taskResizeInputEventReceiver.mDragDetector.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        SurfaceControl build = ((SurfaceControl.Builder) supplier.get()).setName("TaskInputSink of " + surfaceControl).setContainerLayer().setParent(this.mDecorationSurface).setCallsite("DragResizeInputListener.constructor").build();
        this.mInputSinkSurface = build;
        ((SurfaceControl.Transaction) this.mSurfaceControlTransactionSupplier.get()).setLayer(build, -2).show(build).apply();
        Binder binder2 = new Binder();
        this.mSinkClientToken = binder2;
        InputChannel inputChannel2 = new InputChannel();
        this.mSinkInputChannel = inputChannel2;
        try {
            this.mWindowSession.grantInputChannel(this.mDisplayId, build, binder2, (InputTransferToken) null, 8, 0, 1, 2022, (IBinder) null, inputTransferToken, "TaskInputSink of " + surfaceControl, inputChannel2);
        } catch (RemoteException e2) {
            e2.rethrowFromSystemServer();
        }
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        this.mInputEventReceiver.dispose();
        this.mInputChannel.dispose();
        try {
            this.mWindowSession.remove(this.mClientToken);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
        this.mSinkInputChannel.dispose();
        try {
            this.mWindowSession.remove(this.mSinkClientToken);
        } catch (RemoteException e2) {
            e2.rethrowFromSystemServer();
        }
        ((SurfaceControl.Transaction) this.mSurfaceControlTransactionSupplier.get()).remove(this.mInputSinkSurface).apply();
    }

    public final boolean setGeometry(DragResizeWindowGeometry dragResizeWindowGeometry, int i) {
        if (dragResizeWindowGeometry.equals(this.mInputEventReceiver.mDragResizeWindowGeometry)) {
            return false;
        }
        this.mInputEventReceiver.mDragDetector.mTouchSlop = i;
        this.mTouchRegion.setEmpty();
        dragResizeWindowGeometry.union(this.mTouchRegion);
        TaskResizeInputEventReceiver taskResizeInputEventReceiver = this.mInputEventReceiver;
        taskResizeInputEventReceiver.mDragResizeWindowGeometry = dragResizeWindowGeometry;
        taskResizeInputEventReceiver.mTouchRegion = this.mTouchRegion;
        try {
            this.mWindowSession.updateInputChannel(this.mInputChannel.getToken(), this.mDisplayId, this.mDecorationSurface, 8, 536870912, 4, this.mTouchRegion);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
        Size size = dragResizeWindowGeometry.mTaskSize;
        ((SurfaceControl.Transaction) this.mSurfaceControlTransactionSupplier.get()).setWindowCrop(this.mInputSinkSurface, size.getWidth(), size.getHeight()).apply();
        this.mTouchRegion.op(0, 0, size.getWidth(), size.getHeight(), Region.Op.DIFFERENCE);
        updateSinkInputChannel(this.mTouchRegion);
        return true;
    }

    public final void updateSinkInputChannel(Region region) {
        try {
            this.mWindowSession.updateInputChannel(this.mSinkInputChannel.getToken(), this.mDisplayId, this.mInputSinkSurface, 8, 0, 1, region);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }
}

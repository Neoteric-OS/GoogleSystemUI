package com.android.wm.shell.pip.phone;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.hardware.input.InputManager;
import android.os.Looper;
import android.view.BatchedInputEventReceiver;
import android.view.Choreographer;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputMonitor;
import android.view.MotionEvent;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.pip.PipBoundsAlgorithm;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipPerfHintController;
import com.android.wm.shell.common.pip.PipPinchResizingAlgorithm;
import com.android.wm.shell.common.pip.PipUiEventLogger;
import com.android.wm.shell.pip.PipTaskOrganizer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipResizeGestureHandler {
    public boolean mAllowGesture;
    public final Context mContext;
    public final int mDisplayId;
    public boolean mEnablePinchResize;
    public PipResizeInputEventReceiver mInputEventReceiver;
    public InputMonitor mInputMonitor;
    public boolean mIsAttached;
    public boolean mIsEnabled;
    public boolean mIsSysUiStateValid;
    public final ShellExecutor mMainExecutor;
    public final PipMotionHelper mMotionHelper;
    public int mOhmOffset;
    public final PhonePipMenuController mPhonePipMenuController;
    public final PipBoundsAlgorithm mPipBoundsAlgorithm;
    public final PipBoundsState mPipBoundsState;
    public final PipDismissTargetHandler mPipDismissTargetHandler;
    public PipPerfHintController.PipHighPerfSession mPipHighPerfSession;
    public final PipPerfHintController mPipPerfHintController;
    public final PipTaskOrganizer mPipTaskOrganizer;
    public final PipTouchState mPipTouchState;
    public final PipUiEventLogger mPipUiEventLogger;
    public boolean mThresholdCrossed;
    public float mTouchSlop;
    public final PipTouchHandler$$ExternalSyntheticLambda0 mUpdateMovementBoundsRunnable;
    public final PointF mDownPoint = new PointF();
    public final PointF mDownSecondPoint = new PointF();
    public final PointF mLastPoint = new PointF();
    public final PointF mLastSecondPoint = new PointF();
    public final Point mMaxSize = new Point();
    public final Point mMinSize = new Point();
    public final Rect mLastResizeBounds = new Rect();
    public final Rect mUserResizeBounds = new Rect();
    public final Rect mDownBounds = new Rect();
    public boolean mOngoingPinchToResize = false;
    public float mAngle = 0.0f;
    public int mFirstIndex = -1;
    public int mSecondIndex = -1;
    public final PipPinchResizingAlgorithm mPinchResizingAlgorithm = new PipPinchResizingAlgorithm();
    public final PipResizeGestureHandler$$ExternalSyntheticLambda0 mUpdateResizeBoundsCallback = new PipResizeGestureHandler$$ExternalSyntheticLambda0(this, 1);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PipResizeInputEventReceiver extends BatchedInputEventReceiver {
        public PipResizeInputEventReceiver(InputChannel inputChannel, Looper looper) {
            super(inputChannel, looper, Choreographer.getInstance());
        }

        public final void onInputEvent(InputEvent inputEvent) {
            PipResizeGestureHandler.this.onInputEvent(inputEvent);
            finishInputEvent(inputEvent, true);
        }
    }

    public PipResizeGestureHandler(Context context, PipBoundsAlgorithm pipBoundsAlgorithm, PipBoundsState pipBoundsState, PipMotionHelper pipMotionHelper, PipTouchState pipTouchState, PipTaskOrganizer pipTaskOrganizer, PipDismissTargetHandler pipDismissTargetHandler, PipTouchHandler$$ExternalSyntheticLambda0 pipTouchHandler$$ExternalSyntheticLambda0, PipUiEventLogger pipUiEventLogger, PhonePipMenuController phonePipMenuController, ShellExecutor shellExecutor, PipPerfHintController pipPerfHintController) {
        this.mContext = context;
        this.mDisplayId = context.getDisplayId();
        this.mMainExecutor = shellExecutor;
        this.mPipPerfHintController = pipPerfHintController;
        this.mPipBoundsAlgorithm = pipBoundsAlgorithm;
        this.mPipBoundsState = pipBoundsState;
        this.mMotionHelper = pipMotionHelper;
        this.mPipTouchState = pipTouchState;
        this.mPipTaskOrganizer = pipTaskOrganizer;
        this.mPipDismissTargetHandler = pipDismissTargetHandler;
        this.mUpdateMovementBoundsRunnable = pipTouchHandler$$ExternalSyntheticLambda0;
        this.mPhonePipMenuController = phonePipMenuController;
        this.mPipUiEventLogger = pipUiEventLogger;
    }

    public Rect getLastResizeBounds() {
        return this.mLastResizeBounds;
    }

    public void onInputEvent(InputEvent inputEvent) {
        if (this.mEnablePinchResize && this.mPipTouchState.mAllowInputEvents) {
            PipBoundsState pipBoundsState = this.mPipBoundsState;
            if (!pipBoundsState.isStashed() && (inputEvent instanceof MotionEvent)) {
                MotionEvent motionEvent = (MotionEvent) inputEvent;
                int actionMasked = motionEvent.getActionMasked();
                Rect bounds = pipBoundsState.getBounds();
                if ((actionMasked == 1 || actionMasked == 3) && !bounds.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                    PhonePipMenuController phonePipMenuController = this.mPhonePipMenuController;
                    if (phonePipMenuController.isMenuVisible()) {
                        phonePipMenuController.hideMenu();
                    }
                }
                if (this.mEnablePinchResize && this.mOngoingPinchToResize) {
                    onPinchResize(motionEvent);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0251, code lost:
    
        if (((float) java.lang.Math.hypot(r2.x - r1.x, r2.y - r1.y)) > r25.mTouchSlop) goto L67;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onPinchResize(android.view.MotionEvent r26) {
        /*
            Method dump skipped, instructions count: 691
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.pip.phone.PipResizeGestureHandler.onPinchResize(android.view.MotionEvent):void");
    }

    public void pilferPointers() {
        this.mInputMonitor.pilferPointers();
    }

    public final void setUserResizeBounds(Rect rect) {
        this.mUserResizeBounds.set(rect);
    }

    public final void updateIsEnabled() {
        boolean z = this.mIsAttached;
        if (z == this.mIsEnabled) {
            return;
        }
        this.mIsEnabled = z;
        PipResizeInputEventReceiver pipResizeInputEventReceiver = this.mInputEventReceiver;
        if (pipResizeInputEventReceiver != null) {
            pipResizeInputEventReceiver.dispose();
            this.mInputEventReceiver = null;
        }
        InputMonitor inputMonitor = this.mInputMonitor;
        if (inputMonitor != null) {
            inputMonitor.dispose();
            this.mInputMonitor = null;
        }
        if (this.mIsEnabled) {
            this.mInputMonitor = ((InputManager) this.mContext.getSystemService(InputManager.class)).monitorGestureInput("pip-resize", this.mDisplayId);
            try {
                this.mMainExecutor.executeBlocking(new PipResizeGestureHandler$$ExternalSyntheticLambda2(this, 1));
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to create input event receiver", e);
            }
        }
    }

    public void updateMaxSize(int i, int i2) {
        this.mMaxSize.set(i, i2);
    }

    public void updateMinSize(int i, int i2) {
        this.mMinSize.set(i, i2);
    }
}

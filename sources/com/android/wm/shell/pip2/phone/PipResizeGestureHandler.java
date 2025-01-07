package com.android.wm.shell.pip2.phone;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.BatchedInputEventReceiver;
import android.view.Choreographer;
import android.view.InputChannel;
import android.view.InputEvent;
import android.view.InputMonitor;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import com.android.internal.util.Preconditions;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.common.pip.PipBoundsAlgorithm;
import com.android.wm.shell.common.pip.PipBoundsState;
import com.android.wm.shell.common.pip.PipPerfHintController;
import com.android.wm.shell.common.pip.PipPinchResizingAlgorithm;
import com.android.wm.shell.common.pip.PipUiEventLogger;
import com.android.wm.shell.pip2.animation.PipResizeAnimator;
import com.android.wm.shell.pip2.phone.PipTransitionState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipResizeGestureHandler implements PipTransitionState.PipTransitionStateChangedListener {
    public boolean mAllowGesture;
    public final Context mContext;
    public final int mDisplayId;
    public boolean mEnablePinchResize;
    public PipResizeInputEventReceiver mInputEventReceiver;
    public InputMonitor mInputMonitor;
    public boolean mIsAttached;
    public boolean mIsEnabled;
    public final ShellExecutor mMainExecutor;
    public final PhonePipMenuController mPhonePipMenuController;
    public final PipPinchResizingAlgorithm mPinchResizingAlgorithm;
    public final PipBoundsAlgorithm mPipBoundsAlgorithm;
    public final PipBoundsState mPipBoundsState;
    public PipPerfHintController.PipHighPerfSession mPipHighPerfSession;
    public final PipPerfHintController mPipPerfHintController;
    public final PipScheduler mPipScheduler;
    public final PipTouchState mPipTouchState;
    public final PipTransitionState mPipTransitionState;
    public final PipUiEventLogger mPipUiEventLogger;
    public boolean mThresholdCrossed;
    public float mTouchSlop;
    public final PointF mDownPoint = new PointF();
    public final PointF mDownSecondPoint = new PointF();
    public final PointF mLastPoint = new PointF();
    public final PointF mLastSecondPoint = new PointF();
    public final Point mMaxSize = new Point();
    public final Point mMinSize = new Point();
    public final Rect mLastResizeBounds = new Rect();
    public final Rect mUserResizeBounds = new Rect();
    public final Rect mDownBounds = new Rect();
    public final Rect mStartBoundsAfterRelease = new Rect();
    public boolean mOngoingPinchToResize = false;
    public boolean mWaitingForBoundsChangeTransition = false;
    public float mAngle = 0.0f;
    public int mFirstIndex = -1;
    public int mSecondIndex = -1;

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

    public PipResizeGestureHandler(Context context, PipBoundsAlgorithm pipBoundsAlgorithm, PipBoundsState pipBoundsState, PipTouchState pipTouchState, PipScheduler pipScheduler, PipTransitionState pipTransitionState, PipUiEventLogger pipUiEventLogger, PhonePipMenuController phonePipMenuController, ShellExecutor shellExecutor, PipPerfHintController pipPerfHintController) {
        this.mContext = context;
        this.mDisplayId = context.getDisplayId();
        this.mMainExecutor = shellExecutor;
        this.mPipPerfHintController = pipPerfHintController;
        this.mPipBoundsAlgorithm = pipBoundsAlgorithm;
        this.mPipBoundsState = pipBoundsState;
        this.mPipTouchState = pipTouchState;
        this.mPipScheduler = pipScheduler;
        this.mPipTransitionState = pipTransitionState;
        pipTransitionState.addPipTransitionStateChangedListener(this);
        this.mPhonePipMenuController = phonePipMenuController;
        this.mPipUiEventLogger = pipUiEventLogger;
        this.mPinchResizingAlgorithm = new PipPinchResizingAlgorithm();
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
                if (this.mOngoingPinchToResize) {
                    onPinchResize(motionEvent);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x01e3, code lost:
    
        if (((float) java.lang.Math.hypot(r0.x - r15.x, r0.y - r15.y)) > r14.mTouchSlop) goto L55;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onPinchResize(android.view.MotionEvent r15) {
        /*
            Method dump skipped, instructions count: 563
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.wm.shell.pip2.phone.PipResizeGestureHandler.onPinchResize(android.view.MotionEvent):void");
    }

    @Override // com.android.wm.shell.pip2.phone.PipTransitionState.PipTransitionStateChangedListener
    public final void onPipTransitionStateChanged(int i, int i2, Bundle bundle) {
        PipBoundsState pipBoundsState = this.mPipBoundsState;
        if (i2 == 4) {
            if (bundle.getBoolean("resize_bounds_change")) {
                if (pipBoundsState.getBounds().equals(this.mLastResizeBounds)) {
                    Rect bounds = pipBoundsState.getBounds();
                    PipBoundsAlgorithm pipBoundsAlgorithm = this.mPipBoundsAlgorithm;
                    double snapFraction = pipBoundsAlgorithm.mSnapAlgorithm.getSnapFraction(0, bounds, pipBoundsAlgorithm.getMovementBounds(bounds, true));
                    this.mLastResizeBounds.offset(0, (snapFraction < 1.5d || snapFraction > 3.5d) ? 1 : -1);
                }
                this.mWaitingForBoundsChangeTransition = true;
                this.mPipScheduler.scheduleAnimateResizePip(true, 250, this.mLastResizeBounds);
                return;
            }
            return;
        }
        if (i2 == 5 && this.mWaitingForBoundsChangeTransition) {
            this.mWaitingForBoundsChangeTransition = false;
            PipTransitionState pipTransitionState = this.mPipTransitionState;
            SurfaceControl surfaceControl = pipTransitionState.mPinnedTaskLeash;
            Preconditions.checkState(surfaceControl != null, "No leash cached by mPipTransitionState=" + pipTransitionState);
            SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) bundle.getParcelable("pip_start_tx", SurfaceControl.Transaction.class);
            SurfaceControl.Transaction transaction2 = (SurfaceControl.Transaction) bundle.getParcelable("pip_finish_tx", SurfaceControl.Transaction.class);
            int i3 = bundle.getInt("animating_bounds_change_duration", 0);
            transaction.setWindowCrop(surfaceControl, pipBoundsState.getBounds().width(), pipBoundsState.getBounds().height());
            PipResizeAnimator pipResizeAnimator = new PipResizeAnimator(surfaceControl, transaction, transaction2, pipBoundsState.getBounds(), this.mStartBoundsAfterRelease, this.mLastResizeBounds, i3, this.mAngle);
            pipResizeAnimator.mAnimationEndCallback = new PipResizeGestureHandler$$ExternalSyntheticLambda1(this, 1);
            pipResizeAnimator.start();
        }
    }

    public void pilferPointers() {
        this.mInputMonitor.pilferPointers();
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
                this.mMainExecutor.executeBlocking(new PipResizeGestureHandler$$ExternalSyntheticLambda1(this, 0));
            } catch (InterruptedException e) {
                throw new RuntimeException("Failed to create input event receiver", e);
            }
        }
    }
}

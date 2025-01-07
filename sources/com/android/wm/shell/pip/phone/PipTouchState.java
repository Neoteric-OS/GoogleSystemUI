package com.android.wm.shell.pip.phone;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.HandlerExecutor;
import com.android.wm.shell.common.ShellExecutor;
import com.android.wm.shell.protolog.ShellProtoLogGroup;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PipTouchState {
    public static final long DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    public int mActivePointerId;
    public final PipTouchHandler$$ExternalSyntheticLambda0 mDoubleTapTimeoutCallback;
    public final PipTouchHandler$$ExternalSyntheticLambda1 mHoverExitTimeoutCallback;
    public final ShellExecutor mMainExecutor;
    public VelocityTracker mVelocityTracker;
    public final ViewConfiguration mViewConfig;
    public long mDownTouchTime = 0;
    public long mLastDownTouchTime = 0;
    public long mUpTouchTime = 0;
    public final PointF mDownTouch = new PointF();
    public final PointF mDownDelta = new PointF();
    public final PointF mLastTouch = new PointF();
    public final PointF mLastDelta = new PointF();
    public final PointF mVelocity = new PointF();
    public boolean mAllowTouches = true;
    public boolean mAllowInputEvents = true;
    public boolean mIsUserInteracting = false;
    public boolean mIsDoubleTap = false;
    public boolean mIsWaitingForDoubleTap = false;
    public boolean mIsDragging = false;
    public boolean mPreviouslyDragging = false;
    public boolean mStartedDragging = false;
    public boolean mAllowDraggingOffscreen = false;
    public int mLastTouchDisplayId = -1;

    public PipTouchState(ViewConfiguration viewConfiguration, PipTouchHandler$$ExternalSyntheticLambda0 pipTouchHandler$$ExternalSyntheticLambda0, PipTouchHandler$$ExternalSyntheticLambda1 pipTouchHandler$$ExternalSyntheticLambda1, ShellExecutor shellExecutor) {
        this.mViewConfig = viewConfiguration;
        this.mDoubleTapTimeoutCallback = pipTouchHandler$$ExternalSyntheticLambda0;
        this.mHoverExitTimeoutCallback = pipTouchHandler$$ExternalSyntheticLambda1;
        this.mMainExecutor = shellExecutor;
    }

    public final void addMovementToVelocityTracker(MotionEvent motionEvent) {
        if (this.mVelocityTracker == null) {
            return;
        }
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        motionEvent.offsetLocation(rawX, rawY);
        this.mVelocityTracker.addMovement(motionEvent);
        motionEvent.offsetLocation(-rawX, -rawY);
    }

    public long getDoubleTapTimeoutCallbackDelay() {
        if (this.mIsWaitingForDoubleTap) {
            return Math.max(0L, DOUBLE_TAP_TIMEOUT - (this.mUpTouchTime - this.mDownTouchTime));
        }
        return -1L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void onTouchEvent(MotionEvent motionEvent) {
        this.mLastTouchDisplayId = motionEvent.getDisplayId();
        int actionMasked = motionEvent.getActionMasked();
        ShellExecutor shellExecutor = this.mMainExecutor;
        boolean z = false;
        z = false;
        z = false;
        if (actionMasked == 0) {
            if (this.mAllowTouches) {
                VelocityTracker velocityTracker = this.mVelocityTracker;
                if (velocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                } else {
                    velocityTracker.clear();
                }
                addMovementToVelocityTracker(motionEvent);
                this.mActivePointerId = motionEvent.getPointerId(0);
                this.mLastTouch.set(motionEvent.getRawX(), motionEvent.getRawY());
                this.mDownTouch.set(this.mLastTouch);
                this.mAllowDraggingOffscreen = true;
                this.mIsUserInteracting = true;
                long eventTime = motionEvent.getEventTime();
                this.mDownTouchTime = eventTime;
                this.mIsDoubleTap = !this.mPreviouslyDragging && eventTime - this.mLastDownTouchTime < DOUBLE_TAP_TIMEOUT;
                this.mIsWaitingForDoubleTap = false;
                this.mIsDragging = false;
                this.mLastDownTouchTime = eventTime;
                PipTouchHandler$$ExternalSyntheticLambda0 pipTouchHandler$$ExternalSyntheticLambda0 = this.mDoubleTapTimeoutCallback;
                if (pipTouchHandler$$ExternalSyntheticLambda0 != null) {
                    ((HandlerExecutor) shellExecutor).removeCallbacks(pipTouchHandler$$ExternalSyntheticLambda0);
                    return;
                }
                return;
            }
            return;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                if (this.mIsUserInteracting) {
                    addMovementToVelocityTracker(motionEvent);
                    int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                    if (findPointerIndex == -1) {
                        if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                            ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, 6468678591909016462L, 4, "PipTouchState", Long.valueOf(this.mActivePointerId));
                            return;
                        }
                        return;
                    }
                    float rawX = motionEvent.getRawX(findPointerIndex);
                    float rawY = motionEvent.getRawY(findPointerIndex);
                    PointF pointF = this.mLastDelta;
                    PointF pointF2 = this.mLastTouch;
                    pointF.set(rawX - pointF2.x, rawY - pointF2.y);
                    PointF pointF3 = this.mDownDelta;
                    PointF pointF4 = this.mDownTouch;
                    pointF3.set(rawX - pointF4.x, rawY - pointF4.y);
                    Object[] objArr = this.mDownDelta.length() > ((float) this.mViewConfig.getScaledTouchSlop());
                    if (this.mIsDragging) {
                        this.mStartedDragging = false;
                    } else if (objArr != false) {
                        this.mIsDragging = true;
                        this.mStartedDragging = true;
                    }
                    this.mLastTouch.set(rawX, rawY);
                    return;
                }
                return;
            }
            if (actionMasked != 3) {
                if (actionMasked != 6) {
                    if (actionMasked != 11) {
                        return;
                    }
                    ((HandlerExecutor) shellExecutor).removeCallbacks(this.mHoverExitTimeoutCallback);
                    return;
                } else {
                    if (this.mIsUserInteracting) {
                        addMovementToVelocityTracker(motionEvent);
                        int actionIndex = motionEvent.getActionIndex();
                        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
                            int i = actionIndex == 0 ? 1 : 0;
                            this.mActivePointerId = motionEvent.getPointerId(i);
                            this.mLastTouch.set(motionEvent.getRawX(i), motionEvent.getRawY(i));
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
        } else {
            if (!this.mIsUserInteracting) {
                return;
            }
            addMovementToVelocityTracker(motionEvent);
            this.mVelocityTracker.computeCurrentVelocity(1000, this.mViewConfig.getScaledMaximumFlingVelocity());
            this.mVelocity.set(this.mVelocityTracker.getXVelocity(), this.mVelocityTracker.getYVelocity());
            int findPointerIndex2 = motionEvent.findPointerIndex(this.mActivePointerId);
            if (findPointerIndex2 == -1) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_PICTURE_IN_PICTURE_enabled[4]) {
                    ProtoLogImpl_411527699.e(ShellProtoLogGroup.WM_SHELL_PICTURE_IN_PICTURE, -2717992408921393635L, 4, "PipTouchState", Long.valueOf(this.mActivePointerId));
                    return;
                }
                return;
            }
            this.mUpTouchTime = motionEvent.getEventTime();
            this.mLastTouch.set(motionEvent.getRawX(findPointerIndex2), motionEvent.getRawY(findPointerIndex2));
            boolean z2 = this.mIsDragging;
            this.mPreviouslyDragging = z2;
            if (!this.mIsDoubleTap && !z2 && this.mUpTouchTime - this.mDownTouchTime < DOUBLE_TAP_TIMEOUT) {
                z = true;
            }
            this.mIsWaitingForDoubleTap = z;
        }
        VelocityTracker velocityTracker2 = this.mVelocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
            this.mVelocityTracker = null;
        }
    }

    public final void reset() {
        this.mAllowDraggingOffscreen = false;
        this.mIsDragging = false;
        this.mStartedDragging = false;
        this.mIsUserInteracting = false;
        this.mLastTouchDisplayId = -1;
    }

    public void scheduleHoverExitTimeoutCallback() {
        HandlerExecutor handlerExecutor = (HandlerExecutor) this.mMainExecutor;
        PipTouchHandler$$ExternalSyntheticLambda1 pipTouchHandler$$ExternalSyntheticLambda1 = this.mHoverExitTimeoutCallback;
        handlerExecutor.removeCallbacks(pipTouchHandler$$ExternalSyntheticLambda1);
        handlerExecutor.executeDelayed(pipTouchHandler$$ExternalSyntheticLambda1, 50L);
    }
}

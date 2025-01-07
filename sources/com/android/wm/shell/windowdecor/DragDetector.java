package com.android.wm.shell.windowdecor;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DragDetector {
    public int mDragPointerId;
    public final MotionEventHandler mEventHandler;
    public final PointF mInputDownPoint;
    public boolean mIsDragEvent;
    public boolean mResultOfDownAction;
    public int mTouchSlop;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface MotionEventHandler {
        boolean handleMotionEvent(View view, MotionEvent motionEvent);
    }

    public DragDetector(MotionEventHandler motionEventHandler, int i) {
        PointF pointF = new PointF();
        this.mInputDownPoint = pointF;
        this.mDragPointerId = -1;
        this.mIsDragEvent = false;
        pointF.set(0.0f, 0.0f);
        this.mDragPointerId = -1;
        this.mResultOfDownAction = false;
        this.mEventHandler = motionEventHandler;
        this.mTouchSlop = i;
    }

    public static MotionEvent getSinglePointerEvent(MotionEvent motionEvent, int i) {
        return motionEvent.getPointerCount() > 1 ? motionEvent.split(1 << i) : motionEvent;
    }

    public final boolean onMotionEvent(View view, MotionEvent motionEvent) {
        int source = motionEvent.getSource() & 4098;
        MotionEventHandler motionEventHandler = this.mEventHandler;
        if (source != 4098) {
            return motionEventHandler.handleMotionEvent(view, motionEvent);
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mDragPointerId = motionEvent.getPointerId(0);
            this.mInputDownPoint.set(motionEvent.getRawX(0), motionEvent.getRawY(0));
            boolean handleMotionEvent = motionEventHandler.handleMotionEvent(view, motionEvent);
            this.mResultOfDownAction = handleMotionEvent;
            return handleMotionEvent;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                int i = this.mDragPointerId;
                if (i == -1) {
                    return this.mResultOfDownAction;
                }
                int findPointerIndex = motionEvent.findPointerIndex(i);
                if (findPointerIndex == -1) {
                    throw new IllegalStateException("Failed to find primary pointer!");
                }
                if (!this.mIsDragEvent) {
                    float rawX = motionEvent.getRawX(findPointerIndex) - this.mInputDownPoint.x;
                    float rawY = motionEvent.getRawY(findPointerIndex) - this.mInputDownPoint.y;
                    motionEvent.getEventTime();
                    motionEvent.getDownTime();
                    this.mIsDragEvent = Math.hypot((double) rawX, (double) rawY) > ((double) this.mTouchSlop);
                }
                return !this.mIsDragEvent ? this.mResultOfDownAction : motionEventHandler.handleMotionEvent(view, getSinglePointerEvent(motionEvent, this.mDragPointerId));
            }
            if (actionMasked != 3) {
                if (actionMasked != 6) {
                    return (actionMasked == 7 || actionMasked == 9 || actionMasked == 10) ? motionEventHandler.handleMotionEvent(view, getSinglePointerEvent(motionEvent, this.mDragPointerId)) : this.mResultOfDownAction;
                }
                int i2 = this.mDragPointerId;
                if (i2 == -1) {
                    return this.mResultOfDownAction;
                }
                if (i2 != motionEvent.getPointerId(motionEvent.getActionIndex())) {
                    return this.mResultOfDownAction;
                }
                int i3 = this.mDragPointerId;
                this.mDragPointerId = -1;
                return motionEventHandler.handleMotionEvent(view, getSinglePointerEvent(motionEvent, i3));
            }
        }
        int i4 = this.mDragPointerId;
        this.mIsDragEvent = false;
        this.mInputDownPoint.set(0.0f, 0.0f);
        this.mDragPointerId = -1;
        this.mResultOfDownAction = false;
        if (i4 == -1) {
            return false;
        }
        return motionEventHandler.handleMotionEvent(view, getSinglePointerEvent(motionEvent, i4));
    }
}

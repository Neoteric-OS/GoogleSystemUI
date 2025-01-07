package com.android.wm.shell.bubbles;

import android.R;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.bubbles.BubbleStackView;
import com.android.wm.shell.protolog.ShellProtoLogGroup;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubblesNavBarMotionEventHandler {
    public boolean mInterceptingTouches;
    public final BubbleStackView.AnonymousClass4 mMotionEventListener;
    public final BubblesNavBarGestureTracker$$ExternalSyntheticLambda0 mOnInterceptTouch;
    public final BubblePositioner mPositioner;
    public final PointF mTouchDown = new PointF();
    public final int mTouchSlop;
    public boolean mTrackingTouches;
    public VelocityTracker mVelocityTracker;

    public BubblesNavBarMotionEventHandler(Context context, BubblePositioner bubblePositioner, BubblesNavBarGestureTracker$$ExternalSyntheticLambda0 bubblesNavBarGestureTracker$$ExternalSyntheticLambda0, BubbleStackView.AnonymousClass4 anonymousClass4) {
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mPositioner = bubblePositioner;
        this.mOnInterceptTouch = bubblesNavBarGestureTracker$$ExternalSyntheticLambda0;
        this.mMotionEventListener = anonymousClass4;
    }

    public final boolean onMotionEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX() - this.mTouchDown.x;
        float y = motionEvent.getY() - this.mTouchDown.y;
        int action = motionEvent.getAction();
        BubbleStackView.AnonymousClass4 anonymousClass4 = this.mMotionEventListener;
        if (action == 0) {
            BubblePositioner bubblePositioner = this.mPositioner;
            int dimensionPixelSize = bubblePositioner.mContext.getResources().getDimensionPixelSize(R.dimen.navigation_edge_action_progress_threshold);
            Rect rect = bubblePositioner.mScreenRect;
            int i = rect.left;
            int i2 = rect.bottom;
            if (new Rect(i, i2 - dimensionPixelSize, rect.right, i2).contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_BUBBLES_enabled[0]) {
                    long x2 = (int) motionEvent.getX();
                    long y2 = (int) motionEvent.getY();
                    int dimensionPixelSize2 = bubblePositioner.mContext.getResources().getDimensionPixelSize(R.dimen.navigation_edge_action_progress_threshold);
                    Rect rect2 = bubblePositioner.mScreenRect;
                    int i3 = rect2.left;
                    int i4 = rect2.bottom;
                    ProtoLogImpl_411527699.d(ShellProtoLogGroup.WM_SHELL_BUBBLES, -3823162406471254633L, 5, Long.valueOf(x2), Long.valueOf(y2), String.valueOf(new Rect(i3, i4 - dimensionPixelSize2, rect2.right, i4)));
                }
                this.mTouchDown.set(motionEvent.getX(), motionEvent.getY());
                motionEvent.getX();
                motionEvent.getY();
                anonymousClass4.getClass();
                this.mTrackingTouches = true;
                return true;
            }
        } else if (action != 1) {
            if (action != 2) {
                if (action == 3 && this.mTrackingTouches) {
                    anonymousClass4.this$0.mExpandedViewAnimationController.animateBackToExpanded();
                    this.mTouchDown.set(0.0f, 0.0f);
                    this.mTrackingTouches = false;
                    this.mInterceptingTouches = false;
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    if (velocityTracker != null) {
                        velocityTracker.recycle();
                        this.mVelocityTracker = null;
                    }
                    return true;
                }
            } else if (this.mTrackingTouches) {
                if (!this.mInterceptingTouches && Math.hypot(x, y) > this.mTouchSlop) {
                    this.mInterceptingTouches = true;
                    this.mOnInterceptTouch.run();
                }
                if (this.mInterceptingTouches) {
                    if (this.mVelocityTracker == null) {
                        this.mVelocityTracker = VelocityTracker.obtain();
                    }
                    this.mVelocityTracker.addMovement(motionEvent);
                    anonymousClass4.onMove(y);
                }
                return true;
            }
        } else if (this.mTrackingTouches) {
            if (this.mInterceptingTouches) {
                if (this.mVelocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                }
                this.mVelocityTracker.computeCurrentVelocity(1000);
                if (this.mVelocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                }
                this.mVelocityTracker.getXVelocity();
                if (this.mVelocityTracker == null) {
                    this.mVelocityTracker = VelocityTracker.obtain();
                }
                anonymousClass4.onUp(this.mVelocityTracker.getYVelocity());
            }
            this.mTouchDown.set(0.0f, 0.0f);
            this.mTrackingTouches = false;
            this.mInterceptingTouches = false;
            VelocityTracker velocityTracker2 = this.mVelocityTracker;
            if (velocityTracker2 != null) {
                velocityTracker2.recycle();
                this.mVelocityTracker = null;
            }
            return true;
        }
        return false;
    }
}

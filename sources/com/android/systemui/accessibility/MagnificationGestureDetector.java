package com.android.systemui.accessibility;

import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MagnificationGestureDetector {
    public final MagnificationGestureDetector$$ExternalSyntheticLambda0 mCancelTapGestureRunnable;
    public final Handler mHandler;
    public final OnGestureListener mOnGestureListener;
    public final int mTouchSlopSquare;
    public final PointF mPointerDown = new PointF();
    public final PointF mPointerLocation = new PointF(Float.NaN, Float.NaN);
    public boolean mDetectSingleTap = true;
    public boolean mDraggingDetected = false;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface OnGestureListener {
        boolean onDrag(View view, float f, float f2);

        boolean onFinish();

        void onSingleTap(View view);

        void onStart();
    }

    /* JADX WARN: Type inference failed for: r3v4, types: [com.android.systemui.accessibility.MagnificationGestureDetector$$ExternalSyntheticLambda0] */
    public MagnificationGestureDetector(Context context, Handler handler, OnGestureListener onGestureListener) {
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mTouchSlopSquare = scaledTouchSlop * scaledTouchSlop;
        this.mHandler = handler;
        this.mOnGestureListener = onGestureListener;
        this.mCancelTapGestureRunnable = new Runnable() { // from class: com.android.systemui.accessibility.MagnificationGestureDetector$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MagnificationGestureDetector.this.mDetectSingleTap = false;
            }
        };
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        int actionMasked = motionEvent.getActionMasked();
        MagnificationGestureDetector$$ExternalSyntheticLambda0 magnificationGestureDetector$$ExternalSyntheticLambda0 = this.mCancelTapGestureRunnable;
        Handler handler = this.mHandler;
        OnGestureListener onGestureListener = this.mOnGestureListener;
        if (actionMasked == 0) {
            this.mPointerDown.set(rawX, rawY);
            handler.postAtTime(magnificationGestureDetector$$ExternalSyntheticLambda0, motionEvent.getDownTime() + ViewConfiguration.getLongPressTimeout());
            onGestureListener.onStart();
            return true;
        }
        if (actionMasked != 1) {
            if (actionMasked == 2) {
                stopSingleTapDetectionIfNeeded(rawX, rawY);
                if (this.mDraggingDetected) {
                    PointF pointF = this.mPointerLocation;
                    if (Float.isNaN(pointF.x) || Float.isNaN(pointF.y)) {
                        this.mPointerLocation.set(this.mPointerDown);
                    }
                    PointF pointF2 = this.mPointerLocation;
                    float f = rawX - pointF2.x;
                    float f2 = rawY - pointF2.y;
                    pointF2.set(rawX, rawY);
                    return onGestureListener.onDrag(view, f, f2);
                }
            } else if (actionMasked != 3) {
                if (actionMasked == 5) {
                    handler.removeCallbacks(magnificationGestureDetector$$ExternalSyntheticLambda0);
                    this.mDetectSingleTap = false;
                }
            }
            return false;
        }
        stopSingleTapDetectionIfNeeded(rawX, rawY);
        if (this.mDetectSingleTap) {
            onGestureListener.onSingleTap(view);
            z = true;
            boolean onFinish = z | onGestureListener.onFinish();
            PointF pointF3 = this.mPointerDown;
            pointF3.x = Float.NaN;
            pointF3.y = Float.NaN;
            PointF pointF4 = this.mPointerLocation;
            pointF4.x = Float.NaN;
            pointF4.y = Float.NaN;
            handler.removeCallbacks(magnificationGestureDetector$$ExternalSyntheticLambda0);
            this.mDetectSingleTap = true;
            this.mDraggingDetected = false;
            return onFinish;
        }
        z = false;
        boolean onFinish2 = z | onGestureListener.onFinish();
        PointF pointF32 = this.mPointerDown;
        pointF32.x = Float.NaN;
        pointF32.y = Float.NaN;
        PointF pointF42 = this.mPointerLocation;
        pointF42.x = Float.NaN;
        pointF42.y = Float.NaN;
        handler.removeCallbacks(magnificationGestureDetector$$ExternalSyntheticLambda0);
        this.mDetectSingleTap = true;
        this.mDraggingDetected = false;
        return onFinish2;
    }

    public final void stopSingleTapDetectionIfNeeded(float f, float f2) {
        if (this.mDraggingDetected) {
            return;
        }
        PointF pointF = this.mPointerDown;
        if (Float.isNaN(pointF.x) || Float.isNaN(pointF.y)) {
            return;
        }
        PointF pointF2 = this.mPointerDown;
        int i = (int) (pointF2.x - f);
        int i2 = (int) (pointF2.y - f2);
        if ((i2 * i2) + (i * i) > this.mTouchSlopSquare) {
            this.mDraggingDetected = true;
            this.mHandler.removeCallbacks(this.mCancelTapGestureRunnable);
            this.mDetectSingleTap = false;
        }
    }
}

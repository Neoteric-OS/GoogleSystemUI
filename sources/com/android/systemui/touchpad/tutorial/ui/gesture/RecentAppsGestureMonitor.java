package com.android.systemui.touchpad.tutorial.ui.gesture;

import android.view.MotionEvent;
import androidx.compose.ui.input.pointer.util.VelocityTracker1D;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RecentAppsGestureMonitor implements TouchpadGestureMonitor {
    public final int gestureDistanceThresholdPx;
    public final Function1 gestureStateChangedCallback;
    public final float velocityThresholdPxPerMs;
    public final VelocityTracker1D velocityTracker;
    public float yStart;

    public RecentAppsGestureMonitor(int i, Function1 function1, float f) {
        VelocityTracker1D velocityTracker1D = new VelocityTracker1D(VelocityTracker1D.Strategy.Impulse);
        this.gestureDistanceThresholdPx = i;
        this.gestureStateChangedCallback = function1;
        this.velocityThresholdPxPerMs = f;
        this.velocityTracker = velocityTracker1D;
    }

    @Override // com.android.systemui.touchpad.tutorial.ui.gesture.TouchpadGestureMonitor
    public final void processTouchpadEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        long eventTime = motionEvent.getEventTime();
        float y = motionEvent.getY();
        VelocityTracker1D velocityTracker1D = this.velocityTracker;
        velocityTracker1D.addDataPoint(y, eventTime);
        Function1 function1 = this.gestureStateChangedCallback;
        if (actionMasked == 0) {
            if (TouchpadGestureMonitorKt.isThreeFingerTouchpadSwipe(motionEvent)) {
                motionEvent.getX();
                this.yStart = motionEvent.getY();
                function1.invoke(GestureState.IN_PROGRESS);
                return;
            }
            return;
        }
        if (actionMasked != 1) {
            if (actionMasked != 3) {
                return;
            }
            velocityTracker1D.resetTracking();
            return;
        }
        if (TouchpadGestureMonitorKt.isThreeFingerTouchpadSwipe(motionEvent)) {
            float y2 = this.yStart - motionEvent.getY();
            float calculateVelocity = velocityTracker1D.calculateVelocity() / 1000;
            if (y2 >= this.gestureDistanceThresholdPx && Math.abs(calculateVelocity) <= this.velocityThresholdPxPerMs) {
                function1.invoke(GestureState.FINISHED);
                velocityTracker1D.resetTracking();
            }
        }
        function1.invoke(GestureState.NOT_STARTED);
        velocityTracker1D.resetTracking();
    }
}

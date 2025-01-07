package com.android.systemui.touchpad.tutorial.ui.gesture;

import android.view.MotionEvent;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ThreeFingerDistanceBasedGestureMonitor implements TouchpadGestureMonitor {
    public final GestureDonePredicate donePredicate;
    public final Function1 gestureStateChangedCallback;
    public float xStart;
    public float yStart;

    public ThreeFingerDistanceBasedGestureMonitor(Function1 function1, GestureDonePredicate gestureDonePredicate) {
        this.gestureStateChangedCallback = function1;
        this.donePredicate = gestureDonePredicate;
    }

    @Override // com.android.systemui.touchpad.tutorial.ui.gesture.TouchpadGestureMonitor
    public final void processTouchpadEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        Function1 function1 = this.gestureStateChangedCallback;
        if (actionMasked == 0) {
            if (TouchpadGestureMonitorKt.isThreeFingerTouchpadSwipe(motionEvent)) {
                this.xStart = motionEvent.getX();
                this.yStart = motionEvent.getY();
                function1.invoke(GestureState.IN_PROGRESS);
                return;
            }
            return;
        }
        if (actionMasked == 1 && TouchpadGestureMonitorKt.isThreeFingerTouchpadSwipe(motionEvent)) {
            if (this.donePredicate.wasGestureDone(this.xStart, this.yStart, motionEvent.getX(), motionEvent.getY())) {
                function1.invoke(GestureState.FINISHED);
            } else {
                function1.invoke(GestureState.NOT_STARTED);
            }
        }
    }
}

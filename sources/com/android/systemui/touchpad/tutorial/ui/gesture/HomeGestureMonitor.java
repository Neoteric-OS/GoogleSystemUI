package com.android.systemui.touchpad.tutorial.ui.gesture;

import android.view.MotionEvent;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HomeGestureMonitor implements TouchpadGestureMonitor {
    public final /* synthetic */ ThreeFingerDistanceBasedGestureMonitor $$delegate_0;

    public HomeGestureMonitor(final int i, Function1 function1) {
        this.$$delegate_0 = new ThreeFingerDistanceBasedGestureMonitor(function1, new GestureDonePredicate() { // from class: com.android.systemui.touchpad.tutorial.ui.gesture.HomeGestureMonitor.1
            @Override // com.android.systemui.touchpad.tutorial.ui.gesture.GestureDonePredicate
            public final boolean wasGestureDone(float f, float f2, float f3, float f4) {
                return f2 - f4 >= ((float) i);
            }
        });
    }

    @Override // com.android.systemui.touchpad.tutorial.ui.gesture.TouchpadGestureMonitor
    public final void processTouchpadEvent(MotionEvent motionEvent) {
        this.$$delegate_0.processTouchpadEvent(motionEvent);
    }
}

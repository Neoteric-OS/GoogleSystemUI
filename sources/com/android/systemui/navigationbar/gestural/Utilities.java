package com.android.systemui.navigationbar.gestural;

import android.view.MotionEvent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Utilities {
    public static boolean isTrackpadScroll(MotionEvent motionEvent) {
        return motionEvent.getClassification() == 3;
    }

    public static boolean isTrackpadThreeFingerSwipe(MotionEvent motionEvent) {
        return motionEvent.getClassification() == 4 && motionEvent.getAxisValue(53) == 3.0f;
    }
}

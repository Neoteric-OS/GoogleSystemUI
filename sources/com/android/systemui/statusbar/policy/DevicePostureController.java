package com.android.systemui.statusbar.policy;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface DevicePostureController extends CallbackController {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface Callback {
        void onPostureChanged(int i);
    }

    static String devicePostureToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "UNSUPPORTED POSTURE posture=") : "DEVICE_POSTURE_FLIPPED" : "DEVICE_POSTURE_OPENED" : "DEVICE_POSTURE_HALF_OPENED" : "DEVICE_POSTURE_CLOSED" : "DEVICE_POSTURE_UNKNOWN";
    }
}

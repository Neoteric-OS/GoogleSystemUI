package com.google.android.systemui.columbus.legacy.feedback;

import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface FeedbackEffect {
    void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties);
}

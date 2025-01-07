package com.google.android.systemui.elmyra.feedback;

import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface FeedbackEffect {
    void onProgress(int i, float f);

    void onRelease();

    void onResolve(GestureSensor.DetectionProperties detectionProperties);
}

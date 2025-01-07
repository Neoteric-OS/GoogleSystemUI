package com.google.android.systemui.columbus.legacy.feedback;

import android.os.PowerManager;
import android.os.SystemClock;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserActivity implements FeedbackEffect {
    public final Lazy powerManager;

    public UserActivity(Lazy lazy) {
        this.powerManager = lazy;
    }

    @Override // com.google.android.systemui.columbus.legacy.feedback.FeedbackEffect
    public final void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties) {
        if (i != 0) {
            ((PowerManager) this.powerManager.get()).userActivity(SystemClock.uptimeMillis(), 0, 0);
        }
    }
}

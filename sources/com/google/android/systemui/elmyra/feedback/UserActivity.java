package com.google.android.systemui.elmyra.feedback;

import android.os.PowerManager;
import android.os.SystemClock;
import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.google.android.systemui.elmyra.sensors.GestureSensor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserActivity implements FeedbackEffect {
    public final KeyguardStateController mKeyguardStateController;
    public final PowerManager mPowerManager;
    public int mTriggerCount = 0;
    public int mLastStage = 0;

    public UserActivity(KeyguardStateController keyguardStateController, PowerManager powerManager) {
        this.mKeyguardStateController = keyguardStateController;
        this.mPowerManager = powerManager;
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onProgress(int i, float f) {
        PowerManager powerManager;
        if (i != this.mLastStage && i == 2 && !((KeyguardStateControllerImpl) this.mKeyguardStateController).mShowing && (powerManager = this.mPowerManager) != null) {
            powerManager.userActivity(SystemClock.uptimeMillis(), 0, 0);
            this.mTriggerCount++;
        }
        this.mLastStage = i;
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        this.mTriggerCount--;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [mTriggerCount -> ");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.mTriggerCount, "]");
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onRelease() {
    }
}

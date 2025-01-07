package com.google.android.systemui.columbus.legacy.feedback;

import android.media.AudioAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import com.google.android.systemui.columbus.legacy.sensors.GestureSensor;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HapticClick implements FeedbackEffect {
    public static final VibrationEffect GESTURE_DETECTED_VIBRATION_EFFECT = VibrationEffect.get(5);
    public static final AudioAttributes SONIFICATION_AUDIO_ATTRIBUTES = new AudioAttributes.Builder().setContentType(4).setUsage(13).build();
    public final Lazy vibrator;

    public HapticClick(Lazy lazy) {
        this.vibrator = lazy;
    }

    @Override // com.google.android.systemui.columbus.legacy.feedback.FeedbackEffect
    public final void onGestureDetected(int i, GestureSensor.DetectionProperties detectionProperties) {
        if ((detectionProperties == null || !detectionProperties.isHapticConsumed) && i == 1) {
            ((Vibrator) this.vibrator.get()).vibrate(GESTURE_DETECTED_VIBRATION_EFFECT, SONIFICATION_AUDIO_ATTRIBUTES);
        }
    }
}

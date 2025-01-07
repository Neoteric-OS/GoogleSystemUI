package com.google.android.systemui.elmyra.feedback;

import android.content.res.Resources;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import com.android.wm.shell.R;
import com.google.android.systemui.elmyra.sensors.GestureSensor;
import java.util.Optional;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HapticClick implements FeedbackEffect {
    public static final VibrationAttributes TOUCH_VIBRATION_ATTRIBUTES = new VibrationAttributes.Builder().setUsage(34).build();
    public int mLastGestureStage;
    public final Optional mVibratorOptional;
    public final VibrationEffect mResolveVibrationEffect = VibrationEffect.get(0);
    public final VibrationEffect mProgressVibrationEffect = VibrationEffect.get(5);

    public HapticClick(final Resources resources, Optional optional) {
        this.mVibratorOptional = optional;
        optional.ifPresent(new Consumer() { // from class: com.google.android.systemui.elmyra.feedback.HapticClick$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                HapticClick hapticClick = HapticClick.this;
                Resources resources2 = resources;
                Vibrator vibrator = (Vibrator) obj;
                hapticClick.getClass();
                try {
                    vibrator.setAlwaysOnEffect(resources2.getInteger(R.integer.elmyra_progress_always_on_vibration), hapticClick.mProgressVibrationEffect, HapticClick.TOUCH_VIBRATION_ATTRIBUTES);
                } catch (Resources.NotFoundException unused) {
                }
                try {
                    vibrator.setAlwaysOnEffect(resources2.getInteger(R.integer.elmyra_resolve_always_on_vibration), hapticClick.mResolveVibrationEffect, HapticClick.TOUCH_VIBRATION_ATTRIBUTES);
                } catch (Resources.NotFoundException unused2) {
                }
            }
        });
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onProgress(int i, float f) {
        if (this.mLastGestureStage != 2 && i == 2) {
            this.mVibratorOptional.ifPresent(new HapticClick$$ExternalSyntheticLambda0(this, 0));
        }
        this.mLastGestureStage = i;
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onResolve(GestureSensor.DetectionProperties detectionProperties) {
        if (detectionProperties == null || !detectionProperties.mHapticConsumed) {
            this.mVibratorOptional.ifPresent(new HapticClick$$ExternalSyntheticLambda0(this, 1));
        }
    }

    @Override // com.google.android.systemui.elmyra.feedback.FeedbackEffect
    public final void onRelease() {
    }
}

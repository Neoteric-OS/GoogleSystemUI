package com.android.systemui.statusbar;

import android.media.AudioAttributes;
import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.os.Vibrator;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class VibratorHelper {
    public static final VibrationEffect BIOMETRIC_ERROR_VIBRATION_EFFECT = null;
    public static final VibrationEffect BIOMETRIC_SUCCESS_VIBRATION_EFFECT = null;
    public static final VibrationAttributes COMMUNICATION_REQUEST_VIBRATION_ATTRIBUTES = null;
    public final Executor mExecutor;
    public final Vibrator mVibrator;

    static {
        VibrationAttributes.createForUsage(18);
        VibrationEffect.get(0);
        VibrationEffect.get(1);
        VibrationAttributes.createForUsage(50);
        VibrationAttributes.createForUsage(65);
    }

    public VibratorHelper(Vibrator vibrator) {
        this(vibrator, Executors.newSingleThreadExecutor());
    }

    public final boolean hasVibrator() {
        Vibrator vibrator = this.mVibrator;
        return vibrator != null && vibrator.hasVibrator();
    }

    public final void vibrate(VibrationEffect vibrationEffect, AudioAttributes audioAttributes) {
        if (hasVibrator()) {
            this.mExecutor.execute(new VibratorHelper$$ExternalSyntheticLambda0(this, vibrationEffect, audioAttributes));
        }
    }

    public VibratorHelper(Vibrator vibrator, Executor executor) {
        this.mExecutor = executor;
        this.mVibrator = vibrator;
    }

    public final void vibrate(final VibrationEffect vibrationEffect) {
        if (hasVibrator()) {
            this.mExecutor.execute(new Runnable() { // from class: com.android.systemui.statusbar.VibratorHelper$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    VibratorHelper vibratorHelper = VibratorHelper.this;
                    vibratorHelper.mVibrator.vibrate(vibrationEffect);
                }
            });
        }
    }
}

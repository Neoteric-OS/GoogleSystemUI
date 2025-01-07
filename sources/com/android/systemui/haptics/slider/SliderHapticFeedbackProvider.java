package com.android.systemui.haptics.slider;

import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import android.view.VelocityTracker;
import android.view.animation.AccelerateInterpolator;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.VibratorHelper$$ExternalSyntheticLambda0;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliderHapticFeedbackProvider implements SliderStateListener {
    public static final VibrationAttributes VIBRATION_ATTRIBUTES_PIPELINING = new VibrationAttributes.Builder().setUsage(18).setFlags(8).build();
    public final SystemClock clock;
    public final SliderHapticFeedbackConfig config;
    public boolean hasVibratedAtLowerBookend;
    public boolean hasVibratedAtUpperBookend;
    public final float thresholdUntilNextDragCallMillis;
    public final VelocityTracker velocityTracker;
    public final VibratorHelper vibratorHelper;
    public final AccelerateInterpolator velocityAccelerateInterpolator = new AccelerateInterpolator(1.0f);
    public final AccelerateInterpolator positionAccelerateInterpolator = new AccelerateInterpolator(1.0f);
    public long dragTextureLastTime = android.os.SystemClock.elapsedRealtime();
    public float dragTextureLastProgress = -1.0f;

    public SliderHapticFeedbackProvider(VibratorHelper vibratorHelper, VelocityTracker velocityTracker, SliderHapticFeedbackConfig sliderHapticFeedbackConfig, SystemClock systemClock) {
        this.vibratorHelper = vibratorHelper;
        this.velocityTracker = velocityTracker;
        this.config = sliderHapticFeedbackConfig;
        this.clock = systemClock;
        this.thresholdUntilNextDragCallMillis = (vibratorHelper.mVibrator.getPrimitiveDurations(8)[0] * sliderHapticFeedbackConfig.numberOfLowTicks) + 0.0f;
    }

    public final float getTrackedVelocity() {
        VelocityTracker velocityTracker = this.velocityTracker;
        SliderHapticFeedbackConfig sliderHapticFeedbackConfig = this.config;
        velocityTracker.computeCurrentVelocity(1000, sliderHapticFeedbackConfig.maxVelocityToScale);
        VelocityTracker velocityTracker2 = this.velocityTracker;
        int i = sliderHapticFeedbackConfig.velocityAxis;
        if (velocityTracker2.isAxisSupported(i)) {
            return this.velocityTracker.getAxisVelocity(i);
        }
        return 0.0f;
    }

    public final void onProgress(float f) {
        float abs = Math.abs(getTrackedVelocity());
        ((SystemClockImpl) this.clock).getClass();
        long elapsedRealtime = android.os.SystemClock.elapsedRealtime();
        if (elapsedRealtime - this.dragTextureLastTime >= this.thresholdUntilNextDragCallMillis) {
            float abs2 = Math.abs(f - this.dragTextureLastProgress);
            SliderHapticFeedbackConfig sliderHapticFeedbackConfig = this.config;
            if (abs2 >= sliderHapticFeedbackConfig.deltaProgressForDragThreshold) {
                float scaleOnDragTexture = scaleOnDragTexture(abs, f);
                VibrationEffect.Composition startComposition = VibrationEffect.startComposition();
                for (int i = 0; i < sliderHapticFeedbackConfig.numberOfLowTicks; i++) {
                    startComposition.addPrimitive(8, scaleOnDragTexture);
                }
                VibrationEffect compose = startComposition.compose();
                VibrationAttributes vibrationAttributes = VIBRATION_ATTRIBUTES_PIPELINING;
                VibratorHelper vibratorHelper = this.vibratorHelper;
                if (vibratorHelper.hasVibrator()) {
                    vibratorHelper.mExecutor.execute(new VibratorHelper$$ExternalSyntheticLambda0(vibratorHelper, compose, vibrationAttributes));
                }
                this.dragTextureLastTime = elapsedRealtime;
                this.dragTextureLastProgress = f;
            }
        }
        this.hasVibratedAtUpperBookend = false;
        this.hasVibratedAtLowerBookend = false;
    }

    public final float scaleOnDragTexture(float f, float f2) {
        float interpolation = this.velocityAccelerateInterpolator.getInterpolation(Math.min(f / this.config.maxVelocityToScale, 1.0f));
        return (float) Math.pow((interpolation * r1.additionalVelocityMaxBump) + (this.positionAccelerateInterpolator.getInterpolation(f2) * 0.2f) + 0.0f, 1.1235955f);
    }

    public final float scaleOnEdgeCollision(float f) {
        return (float) Math.pow((this.velocityAccelerateInterpolator.getInterpolation(Math.min(f / this.config.maxVelocityToScale, 1.0f)) * 0.95f) + 0.05f, 1.1235955f);
    }
}

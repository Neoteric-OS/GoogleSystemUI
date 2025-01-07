package com.android.systemui.haptics.slider;

import android.os.VibrationAttributes;
import android.os.VibrationEffect;
import androidx.lifecycle.LifecycleCoroutineScopeImpl;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.statusbar.VibratorHelper$$ExternalSyntheticLambda0;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliderStateTracker extends SliderTracker {
    public final SeekableSliderTrackerConfig config;
    public float latestProgress;
    public StandaloneCoroutine timerJob;

    public SliderStateTracker(SliderStateListener sliderStateListener, SliderStateProducer sliderStateProducer, LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImpl, SeekableSliderTrackerConfig seekableSliderTrackerConfig) {
        super(lifecycleCoroutineScopeImpl, sliderStateListener, sliderStateProducer);
        this.config = seekableSliderTrackerConfig;
    }

    public final boolean bookendReached(float f) {
        SeekableSliderTrackerConfig seekableSliderTrackerConfig = this.config;
        return f >= seekableSliderTrackerConfig.upperBookendThreshold || f <= seekableSliderTrackerConfig.lowerBookendThreshold;
    }

    public final void executeOnBookend() {
        float f = this.latestProgress;
        float f2 = this.config.upperBookendThreshold;
        SliderStateListener sliderStateListener = this.sliderListener;
        if (f >= f2) {
            SliderHapticFeedbackProvider sliderHapticFeedbackProvider = (SliderHapticFeedbackProvider) sliderStateListener;
            if (sliderHapticFeedbackProvider.hasVibratedAtUpperBookend) {
                return;
            }
            VibrationEffect compose = VibrationEffect.startComposition().addPrimitive(1, sliderHapticFeedbackProvider.scaleOnEdgeCollision(Math.abs(sliderHapticFeedbackProvider.getTrackedVelocity()))).compose();
            VibrationAttributes vibrationAttributes = SliderHapticFeedbackProvider.VIBRATION_ATTRIBUTES_PIPELINING;
            VibratorHelper vibratorHelper = sliderHapticFeedbackProvider.vibratorHelper;
            if (vibratorHelper.hasVibrator()) {
                vibratorHelper.mExecutor.execute(new VibratorHelper$$ExternalSyntheticLambda0(vibratorHelper, compose, vibrationAttributes));
            }
            sliderHapticFeedbackProvider.hasVibratedAtUpperBookend = true;
            return;
        }
        SliderHapticFeedbackProvider sliderHapticFeedbackProvider2 = (SliderHapticFeedbackProvider) sliderStateListener;
        if (sliderHapticFeedbackProvider2.hasVibratedAtLowerBookend) {
            return;
        }
        VibrationEffect compose2 = VibrationEffect.startComposition().addPrimitive(1, sliderHapticFeedbackProvider2.scaleOnEdgeCollision(Math.abs(sliderHapticFeedbackProvider2.getTrackedVelocity()))).compose();
        VibrationAttributes vibrationAttributes2 = SliderHapticFeedbackProvider.VIBRATION_ATTRIBUTES_PIPELINING;
        VibratorHelper vibratorHelper2 = sliderHapticFeedbackProvider2.vibratorHelper;
        if (vibratorHelper2.hasVibrator()) {
            vibratorHelper2.mExecutor.execute(new VibratorHelper$$ExternalSyntheticLambda0(vibratorHelper2, compose2, vibrationAttributes2));
        }
        sliderHapticFeedbackProvider2.hasVibratedAtLowerBookend = true;
    }

    @Override // com.android.systemui.haptics.slider.SliderTracker
    public final void executeOnState(SliderState sliderState) {
        int ordinal = sliderState.ordinal();
        SliderStateListener sliderStateListener = this.sliderListener;
        switch (ordinal) {
            case 2:
                sliderStateListener.getClass();
                break;
            case 3:
                ((SliderHapticFeedbackProvider) sliderStateListener).dragTextureLastProgress = -1.0f;
                StandaloneCoroutine standaloneCoroutine = this.timerJob;
                if (standaloneCoroutine != null) {
                    standaloneCoroutine.cancel(null);
                }
                this.timerJob = null;
                this.currentState = SliderState.IDLE;
                break;
            case 4:
                ((SliderHapticFeedbackProvider) sliderStateListener).onProgress(this.latestProgress);
                break;
            case 5:
                executeOnBookend();
                break;
            case 6:
                sliderStateListener.getClass();
                break;
            case 8:
                sliderStateListener.getClass();
                break;
            case 9:
                ((SliderHapticFeedbackProvider) sliderStateListener).onProgress(this.latestProgress);
                break;
            case 10:
                executeOnBookend();
                StandaloneCoroutine standaloneCoroutine2 = this.timerJob;
                if (standaloneCoroutine2 != null) {
                    standaloneCoroutine2.cancel(null);
                }
                this.timerJob = null;
                this.currentState = SliderState.IDLE;
                break;
        }
    }

    public final void setState(SliderState sliderState) {
        this.currentState = sliderState;
    }
}

package com.android.systemui.haptics.slider;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.SeekBar;
import androidx.lifecycle.LifecycleCoroutineScopeImpl;
import com.android.systemui.statusbar.VibratorHelper;
import com.android.systemui.util.time.SystemClock;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SeekbarHapticPlugin {
    public StandaloneCoroutine keyUpJob;
    public LifecycleCoroutineScopeImpl pluginScope;
    public final SliderStateProducer sliderEventProducer;
    public final SliderHapticFeedbackProvider sliderHapticFeedbackProvider;
    public SliderStateTracker sliderTracker;
    public final SeekableSliderTrackerConfig sliderTrackerConfig;
    public final VelocityTracker velocityTracker;

    public SeekbarHapticPlugin(VibratorHelper vibratorHelper, SystemClock systemClock, SliderHapticFeedbackConfig sliderHapticFeedbackConfig, SeekableSliderTrackerConfig seekableSliderTrackerConfig) {
        this.sliderTrackerConfig = seekableSliderTrackerConfig;
        VelocityTracker obtain = VelocityTracker.obtain();
        this.velocityTracker = obtain;
        this.sliderEventProducer = new SliderStateProducer();
        this.sliderHapticFeedbackProvider = new SliderHapticFeedbackProvider(vibratorHelper, obtain, sliderHapticFeedbackConfig, systemClock);
    }

    public static float normalizeProgress(SeekBar seekBar, int i) {
        if (seekBar.getMax() == seekBar.getMin()) {
            return 1.0f;
        }
        return (i - seekBar.getMin()) / (seekBar.getMax() - seekBar.getMin());
    }

    public final boolean isTracking() {
        StandaloneCoroutine standaloneCoroutine;
        SliderStateTracker sliderStateTracker = this.sliderTracker;
        return (sliderStateTracker == null || (standaloneCoroutine = sliderStateTracker.job) == null || !standaloneCoroutine.isActive()) ? false : true;
    }

    public final void onKeyDown() {
        StandaloneCoroutine standaloneCoroutine;
        if (isTracking()) {
            StandaloneCoroutine standaloneCoroutine2 = this.keyUpJob;
            if (standaloneCoroutine2 != null && standaloneCoroutine2.isActive() && (standaloneCoroutine = this.keyUpJob) != null) {
                standaloneCoroutine.cancel(null);
            }
            LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImpl = this.pluginScope;
            this.keyUpJob = lifecycleCoroutineScopeImpl != null ? BuildersKt.launch$default(lifecycleCoroutineScopeImpl, null, null, new SeekbarHapticPlugin$onKeyDown$1(this, null), 3) : null;
        }
    }

    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (isTracking()) {
            SliderStateTracker sliderStateTracker = this.sliderTracker;
            SliderState sliderState = sliderStateTracker != null ? sliderStateTracker.currentState : null;
            SliderState sliderState2 = SliderState.IDLE;
            SliderStateProducer sliderStateProducer = this.sliderEventProducer;
            if (sliderState != sliderState2 || z) {
                float normalizeProgress = normalizeProgress(seekBar, i);
                sliderStateProducer.getClass();
                SliderEvent sliderEvent = new SliderEvent(z ? SliderEventType.PROGRESS_CHANGE_BY_USER : SliderEventType.PROGRESS_CHANGE_BY_PROGRAM, normalizeProgress);
                StateFlowImpl stateFlowImpl = sliderStateProducer._currentEvent;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, sliderEvent);
                return;
            }
            float normalizeProgress2 = normalizeProgress(seekBar, i);
            StateFlowImpl stateFlowImpl2 = sliderStateProducer._currentEvent;
            SliderEvent sliderEvent2 = new SliderEvent(SliderEventType.NOTHING, normalizeProgress2);
            stateFlowImpl2.getClass();
            stateFlowImpl2.updateState(null, sliderEvent2);
            sliderStateProducer.onStartTracking(false);
        }
    }

    public final void onTouchEvent(MotionEvent motionEvent) {
        Integer valueOf = motionEvent != null ? Integer.valueOf(motionEvent.getActionMasked()) : null;
        if ((valueOf != null && valueOf.intValue() == 1) || (valueOf != null && valueOf.intValue() == 3)) {
            this.velocityTracker.clear();
        } else if ((valueOf != null && valueOf.intValue() == 0) || (valueOf != null && valueOf.intValue() == 2)) {
            this.velocityTracker.addMovement(motionEvent);
        }
    }

    public static /* synthetic */ void isKeyUpTimerWaiting$annotations() {
    }
}

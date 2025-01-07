package com.android.systemui.haptics.slider;

import androidx.lifecycle.LifecycleCoroutineScopeImpl;
import kotlinx.coroutines.StandaloneCoroutine;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SliderTracker {
    public SliderState currentState = SliderState.IDLE;
    public final SliderStateProducer eventProducer;
    public StandaloneCoroutine job;
    public final LifecycleCoroutineScopeImpl scope;
    public final SliderStateListener sliderListener;

    public SliderTracker(LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImpl, SliderStateListener sliderStateListener, SliderStateProducer sliderStateProducer) {
        this.scope = lifecycleCoroutineScopeImpl;
        this.sliderListener = sliderStateListener;
        this.eventProducer = sliderStateProducer;
    }

    public abstract void executeOnState(SliderState sliderState);
}

package com.android.systemui.haptics.slider;

import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SliderStateProducer {
    public final StateFlowImpl _currentEvent = StateFlowKt.MutableStateFlow(new SliderEvent(SliderEventType.NOTHING, 0.0f));

    public final void onStartTracking(boolean z) {
        StateFlowImpl stateFlowImpl;
        Object value;
        SliderEventType sliderEventType = z ? SliderEventType.STARTED_TRACKING_TOUCH : SliderEventType.STARTED_TRACKING_PROGRAM;
        do {
            stateFlowImpl = this._currentEvent;
            value = stateFlowImpl.getValue();
        } while (!stateFlowImpl.compareAndSet(value, new SliderEvent(sliderEventType, ((SliderEvent) value).currentProgress)));
    }

    public final void onStopTracking(boolean z) {
        StateFlowImpl stateFlowImpl;
        Object value;
        SliderEventType sliderEventType = z ? SliderEventType.STOPPED_TRACKING_TOUCH : SliderEventType.STOPPED_TRACKING_PROGRAM;
        do {
            stateFlowImpl = this._currentEvent;
            value = stateFlowImpl.getValue();
        } while (!stateFlowImpl.compareAndSet(value, new SliderEvent(sliderEventType, ((SliderEvent) value).currentProgress)));
    }
}

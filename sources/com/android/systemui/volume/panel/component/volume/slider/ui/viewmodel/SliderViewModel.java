package com.android.systemui.volume.panel.component.volume.slider.ui.viewmodel;

import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SliderViewModel {
    ReadonlyStateFlow getSlider();

    void onValueChangeFinished();

    void onValueChanged(SliderState sliderState, float f);

    void toggleMuted(SliderState sliderState);
}

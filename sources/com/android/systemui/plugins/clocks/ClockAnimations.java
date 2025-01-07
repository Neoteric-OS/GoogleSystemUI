package com.android.systemui.plugins.clocks;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ClockAnimations {
    void charge();

    void doze(float f);

    void enter();

    void fold(float f);

    void onPickerCarouselSwiping(float f);

    void onPositionUpdated(float f, float f2);

    void onPositionUpdated(int i, int i2, float f);
}

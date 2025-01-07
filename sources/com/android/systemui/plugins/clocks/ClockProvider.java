package com.android.systemui.plugins.clocks;

import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ClockProvider {
    ClockController createClock(ClockSettings clockSettings);

    ClockPickerConfig getClockPickerConfig(String str);

    List getClocks();

    void initialize(ClockMessageBuffers clockMessageBuffers);
}

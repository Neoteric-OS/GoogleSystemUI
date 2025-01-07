package com.android.systemui.plugins.clocks;

import android.graphics.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ClockFaceEvents {
    void onFontSettingChanged(float f);

    void onRegionDarknessChanged(boolean z);

    void onSecondaryDisplayChanged(boolean z);

    void onTargetRegionChanged(Rect rect);

    void onTimeTick();
}

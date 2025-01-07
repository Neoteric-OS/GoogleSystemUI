package com.android.systemui.settings.brightness;

import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public enum BrightnessSliderEvent implements UiEventLogger.UiEventEnum {
    BRIGHTNESS_SLIDER_STARTED_TRACKING_TOUCH(1472),
    BRIGHTNESS_SLIDER_STOPPED_TRACKING_TOUCH(1473);

    private final int mId;

    BrightnessSliderEvent(int i) {
        this.mId = i;
    }

    public final int getId() {
        return this.mId;
    }
}

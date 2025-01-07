package com.android.systemui.controls;

import com.android.internal.logging.UiEventLogger;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ControlsMetricsLogger$ControlsEvents implements UiEventLogger.UiEventEnum {
    public static final /* synthetic */ ControlsMetricsLogger$ControlsEvents[] $VALUES;
    public static final ControlsMetricsLogger$ControlsEvents CONTROL_DRAG;
    public static final ControlsMetricsLogger$ControlsEvents CONTROL_LONG_PRESS;
    public static final ControlsMetricsLogger$ControlsEvents CONTROL_REFRESH_BEGIN;
    public static final ControlsMetricsLogger$ControlsEvents CONTROL_REFRESH_END;
    public static final ControlsMetricsLogger$ControlsEvents CONTROL_TOUCH;
    private final int metricId;

    static {
        ControlsMetricsLogger$ControlsEvents controlsMetricsLogger$ControlsEvents = new ControlsMetricsLogger$ControlsEvents("CONTROL_TOUCH", 0, 714);
        CONTROL_TOUCH = controlsMetricsLogger$ControlsEvents;
        ControlsMetricsLogger$ControlsEvents controlsMetricsLogger$ControlsEvents2 = new ControlsMetricsLogger$ControlsEvents("CONTROL_DRAG", 1, 713);
        CONTROL_DRAG = controlsMetricsLogger$ControlsEvents2;
        ControlsMetricsLogger$ControlsEvents controlsMetricsLogger$ControlsEvents3 = new ControlsMetricsLogger$ControlsEvents("CONTROL_LONG_PRESS", 2, 715);
        CONTROL_LONG_PRESS = controlsMetricsLogger$ControlsEvents3;
        ControlsMetricsLogger$ControlsEvents controlsMetricsLogger$ControlsEvents4 = new ControlsMetricsLogger$ControlsEvents("CONTROL_REFRESH_BEGIN", 3, 716);
        CONTROL_REFRESH_BEGIN = controlsMetricsLogger$ControlsEvents4;
        ControlsMetricsLogger$ControlsEvents controlsMetricsLogger$ControlsEvents5 = new ControlsMetricsLogger$ControlsEvents("CONTROL_REFRESH_END", 4, 717);
        CONTROL_REFRESH_END = controlsMetricsLogger$ControlsEvents5;
        ControlsMetricsLogger$ControlsEvents[] controlsMetricsLogger$ControlsEventsArr = {controlsMetricsLogger$ControlsEvents, controlsMetricsLogger$ControlsEvents2, controlsMetricsLogger$ControlsEvents3, controlsMetricsLogger$ControlsEvents4, controlsMetricsLogger$ControlsEvents5};
        $VALUES = controlsMetricsLogger$ControlsEventsArr;
        EnumEntriesKt.enumEntries(controlsMetricsLogger$ControlsEventsArr);
    }

    public ControlsMetricsLogger$ControlsEvents(String str, int i, int i2) {
        this.metricId = i2;
    }

    public static ControlsMetricsLogger$ControlsEvents valueOf(String str) {
        return (ControlsMetricsLogger$ControlsEvents) Enum.valueOf(ControlsMetricsLogger$ControlsEvents.class, str);
    }

    public static ControlsMetricsLogger$ControlsEvents[] values() {
        return (ControlsMetricsLogger$ControlsEvents[]) $VALUES.clone();
    }

    public final int getId() {
        return this.metricId;
    }
}

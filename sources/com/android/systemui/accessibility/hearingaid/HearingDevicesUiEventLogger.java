package com.android.systemui.accessibility.hearingaid;

import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HearingDevicesUiEventLogger {
    public final UiEventLogger uiEventLogger;

    public HearingDevicesUiEventLogger(UiEventLogger uiEventLogger) {
        this.uiEventLogger = uiEventLogger;
    }

    public final void log(HearingDevicesUiEvent hearingDevicesUiEvent, int i, String str) {
        this.uiEventLogger.log(hearingDevicesUiEvent, i, str);
    }
}

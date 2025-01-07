package com.android.systemui.screenshot.appclips;

import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
enum AppClipsEvent implements UiEventLogger.UiEventEnum {
    SCREENSHOT_FOR_NOTE_TRIGGERED(1308),
    SCREENSHOT_FOR_NOTE_ACCEPTED(1309),
    SCREENSHOT_FOR_NOTE_CANCELLED(1310);

    private final int mId;

    AppClipsEvent(int i) {
        this.mId = i;
    }

    public final int getId() {
        return this.mId;
    }
}

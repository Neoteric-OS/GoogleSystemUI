package com.android.systemui.power;

import com.android.internal.logging.UiEventLogger;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public enum BatteryWarningEvents$LowBatteryWarningEvent implements UiEventLogger.UiEventEnum {
    LOW_BATTERY_NOTIFICATION(1048),
    LOW_BATTERY_NOTIFICATION_TURN_ON(1049),
    LOW_BATTERY_NOTIFICATION_CANCEL(1050),
    LOW_BATTERY_NOTIFICATION_SETTINGS(1051),
    SAVER_CONFIRM_DIALOG(1052),
    SAVER_CONFIRM_OK(1053),
    SAVER_CONFIRM_CANCEL(1054),
    SAVER_CONFIRM_DISMISS(1055);

    private final int mId;

    BatteryWarningEvents$LowBatteryWarningEvent(int i) {
        this.mId = i;
    }

    public final int getId() {
        return this.mId;
    }
}

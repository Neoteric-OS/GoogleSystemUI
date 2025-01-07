package com.android.systemui.statusbar.notification;

import com.android.systemui.log.LogBuffer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationWakeUpCoordinatorLogger {
    public final LogBuffer buffer;
    public boolean lastOnDozeAmountChangedLogWasFractional;
    public boolean lastSetDelayDozeAmountOverrideLogWasFractional;
    public boolean lastSetDozeAmountLogDelayWasFractional;
    public boolean lastSetDozeAmountLogInputWasFractional;
    public Float lastSetHardOverride;
    public boolean lastSetHideAmountLogWasFractional;
    public boolean lastSetVisibilityAmountLogWasFractional;
    public int lastSetDozeAmountLogState = -1;
    public float lastSetHideAmount = -1.0f;

    public NotificationWakeUpCoordinatorLogger(LogBuffer logBuffer) {
        this.buffer = logBuffer;
    }
}

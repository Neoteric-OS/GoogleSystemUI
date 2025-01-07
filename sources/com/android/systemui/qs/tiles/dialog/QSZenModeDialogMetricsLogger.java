package com.android.systemui.qs.tiles.dialog;

import android.content.Context;
import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.notification.modes.ZenModeDialogMetricsLogger;
import com.android.systemui.qs.QSDndEvent;
import com.android.systemui.qs.QSEvents;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSZenModeDialogMetricsLogger extends ZenModeDialogMetricsLogger {
    public final UiEventLogger mUiEventLogger;

    public QSZenModeDialogMetricsLogger(Context context) {
        super(context);
        UiEventLogger uiEventLogger = QSEvents.qsUiEventsLogger;
        this.mUiEventLogger = QSEvents.qsUiEventsLogger;
    }

    @Override // com.android.settingslib.notification.modes.ZenModeDialogMetricsLogger
    public final void logOnClickTimeButton(boolean z) {
        super.logOnClickTimeButton(z);
        this.mUiEventLogger.log(z ? QSDndEvent.QS_DND_TIME_UP : QSDndEvent.QS_DND_TIME_DOWN);
    }

    @Override // com.android.settingslib.notification.modes.ZenModeDialogMetricsLogger
    public final void logOnConditionSelected() {
        super.logOnConditionSelected();
        this.mUiEventLogger.log(QSDndEvent.QS_DND_CONDITION_SELECT);
    }

    @Override // com.android.settingslib.notification.modes.ZenModeDialogMetricsLogger
    public final void logOnEnableZenModeForever() {
        super.logOnEnableZenModeForever();
        this.mUiEventLogger.log(QSDndEvent.QS_DND_DIALOG_ENABLE_FOREVER);
    }

    @Override // com.android.settingslib.notification.modes.ZenModeDialogMetricsLogger
    public final void logOnEnableZenModeUntilAlarm() {
        super.logOnEnableZenModeUntilAlarm();
        this.mUiEventLogger.log(QSDndEvent.QS_DND_DIALOG_ENABLE_UNTIL_ALARM);
    }

    @Override // com.android.settingslib.notification.modes.ZenModeDialogMetricsLogger
    public final void logOnEnableZenModeUntilCountdown() {
        super.logOnEnableZenModeUntilCountdown();
        this.mUiEventLogger.log(QSDndEvent.QS_DND_DIALOG_ENABLE_UNTIL_COUNTDOWN);
    }
}

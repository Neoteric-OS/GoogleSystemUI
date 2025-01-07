package com.google.android.systemui.elmyra.actions;

import android.content.Intent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SnoozeAlarm extends DeskClockAction {
    @Override // com.google.android.systemui.elmyra.actions.DeskClockAction
    public final Intent createDismissIntent() {
        return new Intent("android.intent.action.SNOOZE_ALARM");
    }

    @Override // com.google.android.systemui.elmyra.actions.DeskClockAction
    public final String getAlertAction() {
        return "com.google.android.deskclock.action.ALARM_ALERT";
    }

    @Override // com.google.android.systemui.elmyra.actions.DeskClockAction
    public final String getDoneAction() {
        return "com.google.android.deskclock.action.ALARM_DONE";
    }
}

package com.google.android.systemui.columbus.legacy.actions;

import android.app.IActivityManager;
import android.content.Context;
import android.content.Intent;
import com.google.android.systemui.columbus.legacy.gates.SilenceAlertsDisabled;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DismissTimer extends DeskClockAction {
    public final String tag;

    public DismissTimer(Context context, SilenceAlertsDisabled silenceAlertsDisabled, IActivityManager iActivityManager) {
        super(context, silenceAlertsDisabled, iActivityManager);
        this.tag = "Columbus/DismissTimer";
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.DeskClockAction
    public final Intent createDismissIntent() {
        return new Intent("android.intent.action.DISMISS_TIMER");
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.DeskClockAction
    public final String getAlertAction() {
        return "com.google.android.deskclock.action.TIMER_ALERT";
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.DeskClockAction
    public final String getDoneAction() {
        return "com.google.android.deskclock.action.TIMER_DONE";
    }

    @Override // com.google.android.systemui.columbus.legacy.actions.Action
    public final String getTag$vendor__unbundled_google__packages__SystemUIGoogle__android_common__sysuig() {
        return this.tag;
    }
}

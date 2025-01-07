package com.android.keyguard;

import android.app.AlarmManager;
import com.android.systemui.plugins.clocks.AlarmData;
import com.android.systemui.plugins.clocks.ZenData;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.statusbar.policy.ZenModeControllerImpl;
import com.android.systemui.util.concurrency.ExecutorImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClockEventController$zenModeCallback$1 implements ZenModeController.Callback {
    public final /* synthetic */ ClockEventController this$0;

    public ClockEventController$zenModeCallback$1(ClockEventController clockEventController) {
        this.this$0 = clockEventController;
    }

    @Override // com.android.systemui.statusbar.policy.ZenModeController.Callback
    public final void onNextAlarmChanged() {
        ClockEventController clockEventController = this.this$0;
        ZenModeControllerImpl zenModeControllerImpl = (ZenModeControllerImpl) clockEventController.zenModeController;
        AlarmManager.AlarmClockInfo nextAlarmClock = zenModeControllerImpl.mAlarmManager.getNextAlarmClock(zenModeControllerImpl.mUserId);
        long triggerTime = nextAlarmClock != null ? nextAlarmClock.getTriggerTime() : 0L;
        AlarmData alarmData = new AlarmData(triggerTime > 0 ? Long.valueOf(triggerTime) : null, "status_bar_alarm");
        ((ExecutorImpl) clockEventController.mainExecutor).execute(new ClockEventController$zenModeCallback$1$onZenChanged$1$1(clockEventController, alarmData, 1));
        clockEventController.alarmData = alarmData;
    }

    @Override // com.android.systemui.statusbar.policy.ZenModeController.Callback
    public final void onZenChanged(int i) {
        ZenData.ZenMode fromInt = ZenData.ZenMode.Companion.fromInt(i);
        if (fromInt == null) {
            ClockEventController$zenModeCallback$1$$ExternalSyntheticOutline0.m("Failed to get zen mode from int: ", "ClockEventController", i);
            return;
        }
        ZenData zenData = new ZenData(fromInt, fromInt == ZenData.ZenMode.OFF ? "dnd_is_off" : "dnd_is_on");
        ClockEventController clockEventController = this.this$0;
        ((ExecutorImpl) clockEventController.mainExecutor).execute(new ClockEventController$zenModeCallback$1$onZenChanged$1$1(clockEventController, zenData, 0));
        clockEventController.zenData = zenData;
    }
}

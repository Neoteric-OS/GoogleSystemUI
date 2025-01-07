package com.android.keyguard;

import com.android.systemui.plugins.clocks.AlarmData;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.plugins.clocks.ZenData;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ClockEventController$zenModeCallback$1$onZenChanged$1$1 implements Runnable {
    public final /* synthetic */ Object $data;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ClockEventController this$0;

    public /* synthetic */ ClockEventController$zenModeCallback$1$onZenChanged$1$1(ClockEventController clockEventController, Object obj, int i) {
        this.$r8$classId = i;
        this.this$0 = clockEventController;
        this.$data = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ClockController clockController = this.this$0.clock;
                if (clockController != null) {
                    clockController.getEvents().onZenDataChanged((ZenData) this.$data);
                    break;
                }
                break;
            default:
                ClockController clockController2 = this.this$0.clock;
                if (clockController2 != null) {
                    clockController2.getEvents().onAlarmDataChanged((AlarmData) this.$data);
                    break;
                }
                break;
        }
    }
}

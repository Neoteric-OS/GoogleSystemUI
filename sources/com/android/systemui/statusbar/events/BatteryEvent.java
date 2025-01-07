package com.android.systemui.statusbar.events;

import android.content.Context;
import com.android.systemui.statusbar.BatteryStatusChip;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryEvent implements StatusEvent {
    public final int batteryLevel;
    public final Function1 viewCreator = new Function1() { // from class: com.android.systemui.statusbar.events.BatteryEvent$viewCreator$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            BatteryStatusChip batteryStatusChip = new BatteryStatusChip((Context) obj);
            int i = BatteryEvent.this.batteryLevel;
            batteryStatusChip.batteryMeterView.setPercentShowMode(1);
            batteryStatusChip.batteryMeterView.onBatteryLevelChanged(i, true);
            return batteryStatusChip;
        }
    };

    public BatteryEvent(int i) {
        this.batteryLevel = i;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final String getContentDescription() {
        return "";
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final boolean getForceVisible() {
        return false;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final int getPriority() {
        return 50;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final boolean getShouldAnnounceAccessibilityEvent() {
        return false;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final boolean getShowAnimation() {
        return true;
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final Function1 getViewCreator() {
        return this.viewCreator;
    }

    public final String toString() {
        return "BatteryEvent";
    }

    @Override // com.android.systemui.statusbar.events.StatusEvent
    public final void setForceVisible() {
    }
}

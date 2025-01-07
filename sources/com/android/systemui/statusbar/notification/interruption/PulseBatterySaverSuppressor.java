package com.android.systemui.statusbar.notification.interruption;

import com.android.systemui.statusbar.policy.BatteryController;
import com.android.systemui.statusbar.policy.BatteryControllerImpl;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PulseBatterySaverSuppressor extends VisualInterruptionCondition {
    public final BatteryController batteryController;

    public PulseBatterySaverSuppressor(BatteryController batteryController) {
        super("pulse disabled by battery saver", Collections.singleton(VisualInterruptionType.PULSE));
        this.batteryController = batteryController;
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition
    public final boolean shouldSuppress() {
        return ((BatteryControllerImpl) this.batteryController).mAodPowerSave;
    }
}

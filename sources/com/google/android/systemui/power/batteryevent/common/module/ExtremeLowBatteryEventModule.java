package com.google.android.systemui.power.batteryevent.common.module;

import com.android.settingslib.fuelgauge.BatteryStatus;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.data.EventData;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.Collections;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ExtremeLowBatteryEventModule extends BaseBatteryEventModule {
    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final List getEventDataTypes() {
        return EmptyList.INSTANCE;
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final List getIntentActions() {
        return Collections.singletonList("android.intent.action.BATTERY_CHANGED");
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final BatteryEventType getModuleType() {
        return BatteryEventType.EXTREME_LOW_BATTERY;
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final boolean validate(SystemEventData systemEventData) {
        EventData eventData = systemEventData.batteryLevel;
        boolean z = eventData.isChanged;
        EventData eventData2 = systemEventData.batteryScale;
        EventData eventData3 = systemEventData.plugged;
        if (z || eventData3.isChanged || eventData2.isChanged) {
            int batteryLevel = BatteryStatus.getBatteryLevel(((Number) eventData.value).intValue(), ((Number) eventData2.value).intValue());
            boolean z2 = false;
            boolean z3 = batteryLevel <= 3;
            boolean isPluggedIn = BatteryStatus.isPluggedIn(((Number) eventData3.value).intValue());
            if (z3 && !isPluggedIn) {
                z2 = true;
            }
            this.lastValidation = z2;
        }
        return this.lastValidation;
    }
}

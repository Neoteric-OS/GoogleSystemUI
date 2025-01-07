package com.google.android.systemui.power.batteryevent.common.module;

import android.content.Context;
import com.android.settingslib.fuelgauge.BatteryStatus;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.data.EventData;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.Collections;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SlowChargingEventModule extends BaseBatteryEventModule {
    public final Context context;

    public SlowChargingEventModule(Context context) {
        this.context = context;
    }

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
        return BatteryEventType.SLOW_CHARGING;
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final boolean validate(SystemEventData systemEventData) {
        boolean z;
        EventData eventData = systemEventData.plugged;
        boolean z2 = eventData.isChanged;
        EventData eventData2 = systemEventData.maxChargingCurrent;
        EventData eventData3 = systemEventData.maxChargingVoltage;
        if (z2 || eventData2.isChanged || eventData3.isChanged) {
            if (BatteryStatus.isPluggedIn(((Number) eventData.value).intValue())) {
                if (BatteryStatus.calculateChargingSpeed(((Number) eventData2.value).intValue(), ((Number) eventData3.value).intValue(), this.context) == 0) {
                    z = true;
                    this.lastValidation = z;
                }
            }
            z = false;
            this.lastValidation = z;
        }
        return this.lastValidation;
    }
}

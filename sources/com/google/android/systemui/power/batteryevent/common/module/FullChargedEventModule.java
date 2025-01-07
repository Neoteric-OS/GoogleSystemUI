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
public final class FullChargedEventModule extends BaseBatteryEventModule {
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
        return BatteryEventType.FULL_CHARGED;
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final boolean validate(SystemEventData systemEventData) {
        EventData eventData = systemEventData.plugged;
        boolean z = eventData.isChanged;
        EventData eventData2 = systemEventData.batteryStatus;
        EventData eventData3 = systemEventData.batteryScale;
        EventData eventData4 = systemEventData.batteryLevel;
        if (z || eventData2.isChanged || eventData3.isChanged || eventData4.isChanged) {
            this.lastValidation = BatteryStatus.isPluggedIn(((Number) eventData.value).intValue()) && BatteryStatus.isCharged(((Number) eventData2.value).intValue(), BatteryStatus.getBatteryLevel(((Number) eventData4.value).intValue(), ((Number) eventData3.value).intValue()));
        }
        return this.lastValidation;
    }
}

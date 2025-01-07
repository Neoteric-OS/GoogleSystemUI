package com.google.android.systemui.power.batteryevent.common.module;

import androidx.exifinterface.media.ExifInterface$$ExternalSyntheticOutline0;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.SettingsDataType;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ChargingLimitEventModule extends BaseBatteryEventModule {
    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final List getEventDataTypes() {
        return Collections.singletonList(SettingsDataType.CHARGING_LIMIT_SETTINGS);
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final List getIntentActions() {
        return Collections.singletonList("android.intent.action.BATTERY_CHANGED");
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final BatteryEventType getModuleType() {
        return BatteryEventType.CHARGING_LIMIT;
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final boolean validate(SystemEventData systemEventData) {
        int intValue = ((Number) systemEventData.chargingStatus.value).intValue();
        if (!(intValue == 4)) {
            ExifInterface$$ExternalSyntheticOutline0.m("validate()=false, chargingStatus=", "ChargingLimitEventModule", intValue);
            return false;
        }
        int intValue2 = ((Number) systemEventData.settingsEventData.chargingLimitSettings.value).intValue();
        if (intValue2 == 1) {
            return true;
        }
        ExifInterface$$ExternalSyntheticOutline0.m("validate()=false, chargingLimitSettings=", "ChargingLimitEventModule", intValue2);
        return false;
    }
}

package com.google.android.systemui.power.batteryevent.common.module;

import android.util.Log;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.HalDataType;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.Collections;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TempDefendBatteryModule extends BaseBatteryEventModule {
    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final List getEventDataTypes() {
        return Collections.singletonList(HalDataType.GOOGLE_BATTERY_TEMP_DEFEND_STATUS);
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final List getIntentActions() {
        return Collections.singletonList("android.intent.action.BATTERY_CHANGED");
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final BatteryEventType getModuleType() {
        return BatteryEventType.TEMP_DEFEND_BATTERY;
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final boolean validate(SystemEventData systemEventData) {
        boolean booleanValue = ((Boolean) systemEventData.halEventData.tempDefendEventData.value).booleanValue();
        Log.d("TempDefendBatteryModule", "validate: " + this.lastValidation + " -> " + booleanValue);
        this.lastValidation = booleanValue;
        return booleanValue;
    }
}

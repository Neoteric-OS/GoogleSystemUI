package com.google.android.systemui.power.batteryevent.common.module;

import android.content.Context;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import java.util.Collections;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RegularChargingEventModule extends BaseBatteryEventModule {
    public final Context context;

    public RegularChargingEventModule(Context context) {
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
        return BatteryEventType.REGULAR_CHARGING;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0037, code lost:
    
        if (com.android.settingslib.fuelgauge.BatteryStatus.calculateChargingSpeed(((java.lang.Number) r2.value).intValue(), ((java.lang.Number) r4.value).intValue(), r3.context) == 1) goto L14;
     */
    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean validate(com.google.android.systemui.power.batteryevent.common.data.SystemEventData r4) {
        /*
            r3 = this;
            com.google.android.systemui.power.batteryevent.common.data.EventData r0 = r4.plugged
            boolean r1 = r0.isChanged
            com.google.android.systemui.power.batteryevent.common.data.EventData r2 = r4.maxChargingCurrent
            com.google.android.systemui.power.batteryevent.common.data.EventData r4 = r4.maxChargingVoltage
            if (r1 != 0) goto L12
            boolean r1 = r2.isChanged
            if (r1 != 0) goto L12
            boolean r1 = r4.isChanged
            if (r1 == 0) goto L3d
        L12:
            java.lang.Object r0 = r0.value
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            boolean r0 = com.android.settingslib.fuelgauge.BatteryStatus.isPluggedIn(r0)
            if (r0 == 0) goto L3a
            android.content.Context r0 = r3.context
            java.lang.Object r1 = r2.value
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            java.lang.Object r4 = r4.value
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            int r4 = com.android.settingslib.fuelgauge.BatteryStatus.calculateChargingSpeed(r1, r4, r0)
            r0 = 1
            if (r4 != r0) goto L3a
            goto L3b
        L3a:
            r0 = 0
        L3b:
            r3.lastValidation = r0
        L3d:
            boolean r3 = r3.lastValidation
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.systemui.power.batteryevent.common.module.RegularChargingEventModule.validate(com.google.android.systemui.power.batteryevent.common.data.SystemEventData):boolean");
    }
}

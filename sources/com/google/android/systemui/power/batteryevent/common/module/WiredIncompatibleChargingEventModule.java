package com.google.android.systemui.power.batteryevent.common.module;

import android.content.Context;
import com.android.settingslib.Utils;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WiredIncompatibleChargingEventModule extends BaseBatteryEventModule {
    public final Context context;
    public final WiredIncompatibleChargingUtilImpl utils;

    public WiredIncompatibleChargingEventModule(Context context, WiredIncompatibleChargingUtilImpl wiredIncompatibleChargingUtilImpl) {
        this.context = context;
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final List getEventDataTypes() {
        return EmptyList.INSTANCE;
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final List getIntentActions() {
        return CollectionsKt__CollectionsKt.listOf("android.hardware.usb.action.USB_PORT_COMPLIANCE_CHANGED", "android.intent.action.BATTERY_CHANGED");
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final BatteryEventType getModuleType() {
        return BatteryEventType.WIRED_INCOMPATIBLE_CHARGING;
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final boolean validate(SystemEventData systemEventData) {
        if (Intrinsics.areEqual(systemEventData.intentAction, "android.hardware.usb.action.USB_PORT_COMPLIANCE_CHANGED")) {
            this.lastValidation = Utils.containsIncompatibleChargers(this.context, "WiredIncompatibleEvent");
        }
        return this.lastValidation;
    }
}

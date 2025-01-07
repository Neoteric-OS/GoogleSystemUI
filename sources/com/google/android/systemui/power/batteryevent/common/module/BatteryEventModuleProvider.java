package com.google.android.systemui.power.batteryevent.common.module;

import android.content.Context;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryEventModuleProvider {
    public final List eventModuleList;

    public BatteryEventModuleProvider(Context context) {
        this.eventModuleList = CollectionsKt__CollectionsKt.listOf(new ChargingLimitEventModule(), new DwellDefendBatteryModule(), new DndOnEventModule(), new DockDefendBatteryModule(), new ExtremeLowBatteryEventModule(), new FullChargedEventModule(), new LowBatteryEventModule(), new NotChargingEventModule(), new AirplaneOnEventModule(), new ScreenOnEventModule(), new SevereLowBatteryEventModule(), new TempDefendBatteryModule(), new RegularChargingEventModule(context), new SlowChargingEventModule(context), new FastChargingEventModule(context), new WiredIncompatibleChargingEventModule(context, new WiredIncompatibleChargingUtilImpl()));
    }
}

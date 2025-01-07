package com.google.android.systemui.power.batteryevent.common.module;

import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ScreenOnEventModule extends BaseBatteryEventModule {
    public boolean lastValidated;

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final List getEventDataTypes() {
        return EmptyList.INSTANCE;
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final List getIntentActions() {
        return CollectionsKt__CollectionsKt.listOf("android.intent.action.SCREEN_ON", "android.intent.action.SCREEN_OFF");
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final BatteryEventType getModuleType() {
        return BatteryEventType.SCREEN_ON;
    }

    @Override // com.google.android.systemui.power.batteryevent.common.module.BaseBatteryEventModule
    public final boolean validate(SystemEventData systemEventData) {
        String str = systemEventData.intentAction;
        boolean z = Intrinsics.areEqual(str, "android.intent.action.SCREEN_ON") ? true : Intrinsics.areEqual(str, "android.intent.action.SCREEN_OFF") ? false : this.lastValidated;
        this.lastValidated = z;
        return z;
    }
}

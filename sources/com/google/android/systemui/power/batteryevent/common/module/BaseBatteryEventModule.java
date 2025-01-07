package com.google.android.systemui.power.batteryevent.common.module;

import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import com.google.android.systemui.power.batteryevent.common.data.SystemEventData;
import java.util.List;
import kotlin.collections.EmptyList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class BaseBatteryEventModule {
    public boolean lastValidation;

    public BaseBatteryEventModule() {
        EmptyList emptyList = EmptyList.INSTANCE;
    }

    public abstract List getEventDataTypes();

    public abstract List getIntentActions();

    public abstract BatteryEventType getModuleType();

    public abstract boolean validate(SystemEventData systemEventData);
}

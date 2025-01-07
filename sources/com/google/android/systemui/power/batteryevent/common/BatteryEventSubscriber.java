package com.google.android.systemui.power.batteryevent.common;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import com.google.android.systemui.power.batteryevent.aidl.BatteryEventType;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryEventSubscriber {
    public final List actions;
    public final BatteryEventType batteryEventType;
    public final List eventDataType;

    public BatteryEventSubscriber(BatteryEventType batteryEventType, List list, List list2) {
        this.batteryEventType = batteryEventType;
        this.actions = list;
        this.eventDataType = list2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BatteryEventSubscriber)) {
            return false;
        }
        BatteryEventSubscriber batteryEventSubscriber = (BatteryEventSubscriber) obj;
        return this.batteryEventType == batteryEventSubscriber.batteryEventType && Intrinsics.areEqual(this.actions, batteryEventSubscriber.actions) && Intrinsics.areEqual(this.eventDataType, batteryEventSubscriber.eventDataType);
    }

    public final int hashCode() {
        return this.eventDataType.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.batteryEventType.hashCode() * 31, 31, this.actions);
    }

    public final String toString() {
        return "BatteryEventSubscriber(batteryEventType=" + this.batteryEventType + ", actions=" + this.actions + ", eventDataType=" + this.eventDataType + ")";
    }
}

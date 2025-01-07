package com.google.android.systemui.power.batteryevent.common;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryEvents {
    public final int batteryLevel;
    public final Set eventTypes;
    public final int pluggedType;

    public BatteryEvents(Set set, int i, int i2) {
        this.eventTypes = set;
        this.batteryLevel = i;
        this.pluggedType = i2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BatteryEvents)) {
            return false;
        }
        BatteryEvents batteryEvents = (BatteryEvents) obj;
        return Intrinsics.areEqual(this.eventTypes, batteryEvents.eventTypes) && this.batteryLevel == batteryEvents.batteryLevel && this.pluggedType == batteryEvents.pluggedType;
    }

    public final int hashCode() {
        return Integer.hashCode(this.pluggedType) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.batteryLevel, this.eventTypes.hashCode() * 31, 31);
    }

    public final String toString() {
        Set set = this.eventTypes;
        StringBuilder sb = new StringBuilder("BatteryEvents(eventTypes=");
        sb.append(set);
        sb.append(", batteryLevel=");
        sb.append(this.batteryLevel);
        sb.append(", pluggedType=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.pluggedType, ")");
    }
}

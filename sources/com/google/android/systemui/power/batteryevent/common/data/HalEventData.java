package com.google.android.systemui.power.batteryevent.common.data;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HalEventData {
    public final EventData dockDefendStatus;
    public final EventData dwellDefendEventData;
    public final EventData tempDefendEventData;

    public HalEventData(EventData eventData, EventData eventData2, EventData eventData3) {
        this.dockDefendStatus = eventData;
        this.tempDefendEventData = eventData2;
        this.dwellDefendEventData = eventData3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HalEventData)) {
            return false;
        }
        HalEventData halEventData = (HalEventData) obj;
        return Intrinsics.areEqual(this.dockDefendStatus, halEventData.dockDefendStatus) && Intrinsics.areEqual(this.tempDefendEventData, halEventData.tempDefendEventData) && Intrinsics.areEqual(this.dwellDefendEventData, halEventData.dwellDefendEventData);
    }

    public final int hashCode() {
        return this.dwellDefendEventData.hashCode() + ((this.tempDefendEventData.hashCode() + (this.dockDefendStatus.hashCode() * 31)) * 31);
    }

    public final String toString() {
        return "HalEventData(dockDefendStatus=" + this.dockDefendStatus + ", tempDefendEventData=" + this.tempDefendEventData + ", dwellDefendEventData=" + this.dwellDefendEventData + ")";
    }
}

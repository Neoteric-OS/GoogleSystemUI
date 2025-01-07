package com.google.android.systemui.power.batteryevent.common.data;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FrameworkApiEventData {
    public final EventData batterySaverState;
    public final EventData extremeBatterySaverState;

    public FrameworkApiEventData(EventData eventData, EventData eventData2) {
        this.batterySaverState = eventData;
        this.extremeBatterySaverState = eventData2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FrameworkApiEventData)) {
            return false;
        }
        FrameworkApiEventData frameworkApiEventData = (FrameworkApiEventData) obj;
        return Intrinsics.areEqual(this.batterySaverState, frameworkApiEventData.batterySaverState) && Intrinsics.areEqual(this.extremeBatterySaverState, frameworkApiEventData.extremeBatterySaverState);
    }

    public final int hashCode() {
        return this.extremeBatterySaverState.hashCode() + (this.batterySaverState.hashCode() * 31);
    }

    public final String toString() {
        return "FrameworkApiEventData(batterySaverState=" + this.batterySaverState + ", extremeBatterySaverState=" + this.extremeBatterySaverState + ")";
    }
}

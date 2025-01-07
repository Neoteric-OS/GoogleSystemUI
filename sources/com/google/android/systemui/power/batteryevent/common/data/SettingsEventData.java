package com.google.android.systemui.power.batteryevent.common.data;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SettingsEventData {
    public final EventData airplaneState;
    public final EventData chargingLimitSettings;
    public final EventData dndState;
    public final EventData dockDefenderBypass;

    public SettingsEventData(EventData eventData, EventData eventData2, EventData eventData3, EventData eventData4) {
        this.dockDefenderBypass = eventData;
        this.chargingLimitSettings = eventData2;
        this.dndState = eventData3;
        this.airplaneState = eventData4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SettingsEventData)) {
            return false;
        }
        SettingsEventData settingsEventData = (SettingsEventData) obj;
        return Intrinsics.areEqual(this.dockDefenderBypass, settingsEventData.dockDefenderBypass) && Intrinsics.areEqual(this.chargingLimitSettings, settingsEventData.chargingLimitSettings) && Intrinsics.areEqual(this.dndState, settingsEventData.dndState) && Intrinsics.areEqual(this.airplaneState, settingsEventData.airplaneState);
    }

    public final int hashCode() {
        return this.airplaneState.hashCode() + ((this.dndState.hashCode() + ((this.chargingLimitSettings.hashCode() + (this.dockDefenderBypass.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "SettingsEventData(dockDefenderBypass=" + this.dockDefenderBypass + ", chargingLimitSettings=" + this.chargingLimitSettings + ", dndState=" + this.dndState + ", airplaneState=" + this.airplaneState + ")";
    }
}

package com.google.android.systemui.power.batteryevent.common.data;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemEventData {
    public final EventData batteryLevel;
    public final EventData batteryScale;
    public final EventData batteryStatus;
    public final EventData chargingStatus;
    public final FrameworkApiEventData frameworkApiEventData;
    public final HalEventData halEventData;
    public final String intentAction;
    public final EventData maxChargingCurrent;
    public final EventData maxChargingVoltage;
    public final EventData plugged;
    public final SettingsEventData settingsEventData;

    public SystemEventData(String str, EventData eventData, EventData eventData2, EventData eventData3, EventData eventData4, EventData eventData5, EventData eventData6, EventData eventData7, HalEventData halEventData, SettingsEventData settingsEventData, FrameworkApiEventData frameworkApiEventData) {
        this.intentAction = str;
        this.plugged = eventData;
        this.batteryScale = eventData2;
        this.batteryLevel = eventData3;
        this.chargingStatus = eventData4;
        this.maxChargingCurrent = eventData5;
        this.maxChargingVoltage = eventData6;
        this.batteryStatus = eventData7;
        this.halEventData = halEventData;
        this.settingsEventData = settingsEventData;
        this.frameworkApiEventData = frameworkApiEventData;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SystemEventData)) {
            return false;
        }
        SystemEventData systemEventData = (SystemEventData) obj;
        return Intrinsics.areEqual(this.intentAction, systemEventData.intentAction) && Intrinsics.areEqual(this.plugged, systemEventData.plugged) && Intrinsics.areEqual(this.batteryScale, systemEventData.batteryScale) && Intrinsics.areEqual(this.batteryLevel, systemEventData.batteryLevel) && Intrinsics.areEqual(this.chargingStatus, systemEventData.chargingStatus) && Intrinsics.areEqual(this.maxChargingCurrent, systemEventData.maxChargingCurrent) && Intrinsics.areEqual(this.maxChargingVoltage, systemEventData.maxChargingVoltage) && Intrinsics.areEqual(this.batteryStatus, systemEventData.batteryStatus) && Intrinsics.areEqual(this.halEventData, systemEventData.halEventData) && Intrinsics.areEqual(this.settingsEventData, systemEventData.settingsEventData) && Intrinsics.areEqual(this.frameworkApiEventData, systemEventData.frameworkApiEventData);
    }

    public final int hashCode() {
        return this.frameworkApiEventData.hashCode() + ((this.settingsEventData.hashCode() + ((this.halEventData.hashCode() + ((this.batteryStatus.hashCode() + ((this.maxChargingVoltage.hashCode() + ((this.maxChargingCurrent.hashCode() + ((this.chargingStatus.hashCode() + ((this.batteryLevel.hashCode() + ((this.batteryScale.hashCode() + ((this.plugged.hashCode() + (this.intentAction.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "SystemEventData(intentAction=" + this.intentAction + ", plugged=" + this.plugged + ", batteryScale=" + this.batteryScale + ", batteryLevel=" + this.batteryLevel + ", chargingStatus=" + this.chargingStatus + ", maxChargingCurrent=" + this.maxChargingCurrent + ", maxChargingVoltage=" + this.maxChargingVoltage + ", batteryStatus=" + this.batteryStatus + ", halEventData=" + this.halEventData + ", settingsEventData=" + this.settingsEventData + ", frameworkApiEventData=" + this.frameworkApiEventData + ")";
    }
}

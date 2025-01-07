package com.google.android.systemui.power.batteryevent.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BatteryEventType implements Parcelable {
    public static final /* synthetic */ EnumEntries $ENTRIES;
    public static final /* synthetic */ BatteryEventType[] $VALUES;
    public static final BatteryEventType AIRPLANE_ON;
    public static final BatteryEventType CHARGING_LIMIT;
    public static final CREATOR CREATOR;
    public static final BatteryEventType DND_ON;
    public static final BatteryEventType DOCK_DEFEND_BATTERY;
    public static final BatteryEventType DWELL_DEFEND_BATTERY;
    public static final BatteryEventType EXTREME_LOW_BATTERY;
    public static final BatteryEventType FAST_CHARGING;
    public static final BatteryEventType FULL_CHARGED;
    public static final BatteryEventType LOW_BATTERY;
    public static final BatteryEventType NOT_CHARGING;
    public static final BatteryEventType REGULAR_CHARGING;
    public static final BatteryEventType SCREEN_ON;
    public static final BatteryEventType SEVERE_LOW_BATTERY;
    public static final BatteryEventType SLOW_CHARGING;
    public static final BatteryEventType TEMP_DEFEND_BATTERY;
    public static final BatteryEventType UNKNOWN;
    public static final BatteryEventType WIRED_INCOMPATIBLE_CHARGING;
    private final String typeName;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CREATOR implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            BatteryEventType batteryEventType = null;
            String readString = parcel != null ? parcel.readString() : null;
            if (readString == null) {
                Log.w("BatteryEventType", "null parameter for createFromParcel");
                return BatteryEventType.UNKNOWN;
            }
            BatteryEventType[] values = BatteryEventType.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                BatteryEventType batteryEventType2 = values[i];
                if (Intrinsics.areEqual(batteryEventType2.getTypeName(), readString)) {
                    batteryEventType = batteryEventType2;
                    break;
                }
                i++;
            }
            return batteryEventType == null ? BatteryEventType.UNKNOWN : batteryEventType;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new BatteryEventType[i];
        }
    }

    static {
        BatteryEventType batteryEventType = new BatteryEventType("UNKNOWN", "unknown", 0);
        UNKNOWN = batteryEventType;
        BatteryEventType batteryEventType2 = new BatteryEventType("FULL_CHARGED", "full_charged", 1);
        FULL_CHARGED = batteryEventType2;
        BatteryEventType batteryEventType3 = new BatteryEventType("BATTERY_IN_USE", "battery_in_use", 2);
        BatteryEventType batteryEventType4 = new BatteryEventType("LOW_BATTERY", "low_battery", 3);
        LOW_BATTERY = batteryEventType4;
        BatteryEventType batteryEventType5 = new BatteryEventType("EXTREME_LOW_BATTERY", "extreme_low_battery", 4);
        EXTREME_LOW_BATTERY = batteryEventType5;
        BatteryEventType batteryEventType6 = new BatteryEventType("SEVERE_LOW_BATTERY", "severe_low_battery", 5);
        SEVERE_LOW_BATTERY = batteryEventType6;
        BatteryEventType batteryEventType7 = new BatteryEventType("REGULAR_CHARGING", "regular_charging", 6);
        REGULAR_CHARGING = batteryEventType7;
        BatteryEventType batteryEventType8 = new BatteryEventType("FAST_CHARGING", "fast_charging", 7);
        FAST_CHARGING = batteryEventType8;
        BatteryEventType batteryEventType9 = new BatteryEventType("SLOW_CHARGING", "slow_charging", 8);
        SLOW_CHARGING = batteryEventType9;
        BatteryEventType batteryEventType10 = new BatteryEventType("DISCHARGING", "discharging", 9);
        BatteryEventType batteryEventType11 = new BatteryEventType("NOT_CHARGING", "not_charging", 10);
        NOT_CHARGING = batteryEventType11;
        BatteryEventType batteryEventType12 = new BatteryEventType("TEMP_DEFEND_BATTERY", "temp_defend_battery", 11);
        TEMP_DEFEND_BATTERY = batteryEventType12;
        BatteryEventType batteryEventType13 = new BatteryEventType("DWELL_DEFEND_BATTERY", "dwell_defend_battery", 12);
        DWELL_DEFEND_BATTERY = batteryEventType13;
        BatteryEventType batteryEventType14 = new BatteryEventType("DOCK_DEFEND_BATTERY", "dock_defend_battery", 13);
        DOCK_DEFEND_BATTERY = batteryEventType14;
        BatteryEventType batteryEventType15 = new BatteryEventType("WIRED_INCOMPATIBLE_CHARGING", "wired_incompatible_charging", 14);
        WIRED_INCOMPATIBLE_CHARGING = batteryEventType15;
        BatteryEventType batteryEventType16 = new BatteryEventType("OVERHEATED_BATTERY", "overheated_battery", 15);
        BatteryEventType batteryEventType17 = new BatteryEventType("COLD_BATTERY", "cold_battery", 16);
        BatteryEventType batteryEventType18 = new BatteryEventType("CHARGING_LIMIT", "charging_limit", 17);
        CHARGING_LIMIT = batteryEventType18;
        BatteryEventType batteryEventType19 = new BatteryEventType("SCREEN_ON", "screen_on", 18);
        SCREEN_ON = batteryEventType19;
        BatteryEventType batteryEventType20 = new BatteryEventType("AIRPLANE_ON", "airplane_on", 19);
        AIRPLANE_ON = batteryEventType20;
        BatteryEventType batteryEventType21 = new BatteryEventType("DND_ON", "dnd_on", 20);
        DND_ON = batteryEventType21;
        BatteryEventType[] batteryEventTypeArr = {batteryEventType, batteryEventType2, batteryEventType3, batteryEventType4, batteryEventType5, batteryEventType6, batteryEventType7, batteryEventType8, batteryEventType9, batteryEventType10, batteryEventType11, batteryEventType12, batteryEventType13, batteryEventType14, batteryEventType15, batteryEventType16, batteryEventType17, batteryEventType18, batteryEventType19, batteryEventType20, batteryEventType21};
        $VALUES = batteryEventTypeArr;
        $ENTRIES = EnumEntriesKt.enumEntries(batteryEventTypeArr);
        CREATOR = new CREATOR();
    }

    public BatteryEventType(String str, String str2, int i) {
        this.typeName = str2;
    }

    public static BatteryEventType valueOf(String str) {
        return (BatteryEventType) Enum.valueOf(BatteryEventType.class, str);
    }

    public static BatteryEventType[] values() {
        return (BatteryEventType[]) $VALUES.clone();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String getTypeName() {
        return this.typeName;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.typeName);
    }
}

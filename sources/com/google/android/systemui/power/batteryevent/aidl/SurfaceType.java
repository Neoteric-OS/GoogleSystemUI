package com.google.android.systemui.power.batteryevent.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SurfaceType implements Parcelable {
    public static final /* synthetic */ SurfaceType[] $VALUES;
    public static final CREATOR CREATOR;
    public static final SurfaceType NOTIFICATION;
    public static final SurfaceType UNKNOWN;
    private final String typeName;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CREATOR implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            SurfaceType surfaceType = null;
            String readString = parcel != null ? parcel.readString() : null;
            if (readString == null) {
                Log.w("SurfaceType", "null parameter for createFromParcel");
                return SurfaceType.UNKNOWN;
            }
            SurfaceType[] values = SurfaceType.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                SurfaceType surfaceType2 = values[i];
                if (Intrinsics.areEqual(surfaceType2.getTypeName(), readString)) {
                    surfaceType = surfaceType2;
                    break;
                }
                i++;
            }
            return surfaceType == null ? SurfaceType.UNKNOWN : surfaceType;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new SurfaceType[i];
        }
    }

    static {
        SurfaceType surfaceType = new SurfaceType("UNKNOWN", "unknown", 0);
        UNKNOWN = surfaceType;
        SurfaceType surfaceType2 = new SurfaceType("BATTERY_WIDGET", "battery_widget", 1);
        SurfaceType surfaceType3 = new SurfaceType("NOTIFICATION", "notification", 2);
        NOTIFICATION = surfaceType3;
        SurfaceType[] surfaceTypeArr = {surfaceType, surfaceType2, surfaceType3, new SurfaceType("SYSTEM_DIALOG", "system_dialog", 3), new SurfaceType("BATTERY_BANNER_TIPS", "battery_banner_tips", 4), new SurfaceType("BATTERY_STATE_SUBSTRING", "battery_state_substring", 5), new SurfaceType("KEYGUARD_AOD", "keyguard_aod", 6), new SurfaceType("STATUS_BAR", "status_bar", 7), new SurfaceType("QUICK_SETTINGS", "quick_settings", 8)};
        $VALUES = surfaceTypeArr;
        EnumEntriesKt.enumEntries(surfaceTypeArr);
        CREATOR = new CREATOR();
    }

    public SurfaceType(String str, String str2, int i) {
        this.typeName = str2;
    }

    public static SurfaceType valueOf(String str) {
        return (SurfaceType) Enum.valueOf(SurfaceType.class, str);
    }

    public static SurfaceType[] values() {
        return (SurfaceType[]) $VALUES.clone();
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

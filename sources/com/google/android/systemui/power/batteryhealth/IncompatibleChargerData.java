package com.google.android.systemui.power.batteryhealth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import com.google.android.systemui.power.DumpUtils;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IncompatibleChargerData implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR();
    public final boolean isIncompatibleCharger;
    public final long lastCompatibleChargerTime;
    public final long lastIncompatibleChargerTime;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CREATOR implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            return new IncompatibleChargerData(parcel.readLong(), parcel.readLong(), parcel.readBoolean());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new IncompatibleChargerData[i];
        }
    }

    public IncompatibleChargerData(long j, long j2, boolean z) {
        this.isIncompatibleCharger = z;
        this.lastCompatibleChargerTime = j;
        this.lastIncompatibleChargerTime = j2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IncompatibleChargerData)) {
            return false;
        }
        IncompatibleChargerData incompatibleChargerData = (IncompatibleChargerData) obj;
        return this.isIncompatibleCharger == incompatibleChargerData.isIncompatibleCharger && this.lastCompatibleChargerTime == incompatibleChargerData.lastCompatibleChargerTime && this.lastIncompatibleChargerTime == incompatibleChargerData.lastIncompatibleChargerTime;
    }

    public final int hashCode() {
        return Long.hashCode(this.lastIncompatibleChargerTime) + Scale$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.isIncompatibleCharger) * 31, 31, this.lastCompatibleChargerTime);
    }

    public final String toString() {
        return "isIncompatibleCharger: " + this.isIncompatibleCharger + ", lastCompatibleChargerTime: " + DumpUtils.toReadableDateTime(this.lastCompatibleChargerTime) + ", lastIncompatibleChargerTime: " + DumpUtils.toReadableDateTime(this.lastIncompatibleChargerTime);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeBoolean(this.isIncompatibleCharger);
        parcel.writeLong(this.lastCompatibleChargerTime);
        parcel.writeLong(this.lastIncompatibleChargerTime);
    }
}

package com.google.android.systemui.power.batteryhealth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.foundation.text.ValidatingOffsetMappingKt$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HealthData implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR();
    public final int capacityIndex;
    public final int healthIndex;
    public final int healthStatus;
    public final int performanceIndex;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class CREATOR implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            return new HealthData(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new HealthData[i];
        }
    }

    public HealthData(int i, int i2, int i3, int i4) {
        this.healthIndex = i;
        this.performanceIndex = i2;
        this.capacityIndex = i3;
        this.healthStatus = i4;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HealthData)) {
            return false;
        }
        HealthData healthData = (HealthData) obj;
        return this.healthIndex == healthData.healthIndex && this.performanceIndex == healthData.performanceIndex && this.capacityIndex == healthData.capacityIndex && this.healthStatus == healthData.healthStatus;
    }

    public final int hashCode() {
        return Integer.hashCode(this.healthStatus) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.capacityIndex, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.performanceIndex, Integer.hashCode(this.healthIndex) * 31, 31), 31);
    }

    public final String toString() {
        int i = this.healthIndex;
        int i2 = this.performanceIndex;
        int i3 = this.capacityIndex;
        int i4 = this.healthStatus;
        StringBuilder m = ValidatingOffsetMappingKt$$ExternalSyntheticOutline0.m(i, i2, "hi: ", ", pi: ", ", ci: ");
        m.append(i3);
        m.append(", hs: ");
        m.append(i4);
        return m.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.healthIndex);
        parcel.writeInt(this.performanceIndex);
        parcel.writeInt(this.capacityIndex);
        parcel.writeInt(this.healthStatus);
    }
}

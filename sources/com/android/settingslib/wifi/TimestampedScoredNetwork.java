package com.android.settingslib.wifi;

import android.net.ScoredNetwork;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TimestampedScoredNetwork implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass1();
    public ScoredNetwork mScore;
    public long mUpdatedTimestampMillis;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.settingslib.wifi.TimestampedScoredNetwork$1, reason: invalid class name */
    public final class AnonymousClass1 implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            TimestampedScoredNetwork timestampedScoredNetwork = new TimestampedScoredNetwork();
            timestampedScoredNetwork.mScore = parcel.readParcelable(ScoredNetwork.class.getClassLoader());
            timestampedScoredNetwork.mUpdatedTimestampMillis = parcel.readLong();
            return timestampedScoredNetwork;
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new TimestampedScoredNetwork[i];
        }
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mScore, i);
        parcel.writeLong(this.mUpdatedTimestampMillis);
    }
}

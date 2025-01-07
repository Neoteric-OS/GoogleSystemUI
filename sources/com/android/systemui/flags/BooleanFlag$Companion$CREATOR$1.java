package com.android.systemui.flags;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BooleanFlag$Companion$CREATOR$1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        parcel.readInt();
        String readString = parcel.readString();
        String str = readString == null ? "" : readString;
        String readString2 = parcel.readString();
        return new BooleanFlag$Companion$CREATOR$1$createFromParcel$1(str, readString2 == null ? "" : readString2, parcel.readBoolean(), parcel.readBoolean(), parcel.readBoolean());
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new BooleanFlag[i];
    }
}

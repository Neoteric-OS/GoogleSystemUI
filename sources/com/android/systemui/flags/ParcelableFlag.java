package com.android.systemui.flags;

import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ParcelableFlag extends Flag, Parcelable {
    @Override // android.os.Parcelable
    default int describeContents() {
        return 0;
    }
}

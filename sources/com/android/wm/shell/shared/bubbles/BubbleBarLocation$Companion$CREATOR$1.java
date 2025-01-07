package com.android.wm.shell.shared.bubbles;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleBarLocation$Companion$CREATOR$1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        BubbleBarLocation valueOf;
        String readString = parcel.readString();
        return (readString == null || (valueOf = BubbleBarLocation.valueOf(readString)) == null) ? BubbleBarLocation.DEFAULT : valueOf;
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new BubbleBarLocation[i];
    }
}

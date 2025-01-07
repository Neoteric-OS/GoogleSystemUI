package com.android.wm.shell.shared.bubbles;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleBarLocation implements Parcelable {
    public static final /* synthetic */ BubbleBarLocation[] $VALUES;
    public static final Parcelable.Creator CREATOR;
    public static final BubbleBarLocation DEFAULT;
    public static final BubbleBarLocation LEFT;
    public static final BubbleBarLocation RIGHT;

    static {
        BubbleBarLocation bubbleBarLocation = new BubbleBarLocation("DEFAULT", 0);
        DEFAULT = bubbleBarLocation;
        BubbleBarLocation bubbleBarLocation2 = new BubbleBarLocation("LEFT", 1);
        LEFT = bubbleBarLocation2;
        BubbleBarLocation bubbleBarLocation3 = new BubbleBarLocation("RIGHT", 2);
        RIGHT = bubbleBarLocation3;
        BubbleBarLocation[] bubbleBarLocationArr = {bubbleBarLocation, bubbleBarLocation2, bubbleBarLocation3};
        $VALUES = bubbleBarLocationArr;
        EnumEntriesKt.enumEntries(bubbleBarLocationArr);
        CREATOR = new BubbleBarLocation$Companion$CREATOR$1();
    }

    public static BubbleBarLocation valueOf(String str) {
        return (BubbleBarLocation) Enum.valueOf(BubbleBarLocation.class, str);
    }

    public static BubbleBarLocation[] values() {
        return (BubbleBarLocation[]) $VALUES.clone();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
